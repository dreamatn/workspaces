package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5853 {
    String CDD_U_GPRS_UPD_NRA = "DD-U-GPRS-UPD-NRA";
    String K_OUTPUT_FMT = "DD853";
    String WS_MSG_ID = " ";
    short WS_ERR_FLD_NO = 0;
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUGNRA DDCUGNRA = new DDCUGNRA();
    DDCOOPVS DDCOOPVS = new DDCOOPVS();
    SCCGWA SCCGWA;
    DDB5853_AWA_5853 DDB5853_AWA_5853;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5853 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5853_AWA_5853>");
        DDB5853_AWA_5853 = (DDB5853_AWA_5853) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_LIMI_NRA_AMT();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DDB5853_AWA_5853.AC.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_ERR_FLD_NO = DDB5853_AWA_5853.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5853_AWA_5853.CCY.trim().length() == 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_ERR_FLD_NO = DDB5853_AWA_5853.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E' 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B200_LIMI_NRA_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGNRA);
        DDCUGNRA.AC = DDB5853_AWA_5853.AC;
        DDCUGNRA.CCY = DDB5853_AWA_5853.CCY;
        DDCUGNRA.NRA_LMT = DDB5853_AWA_5853.NRA_LMT;
        S000_CALL_DDZUGNRA();
    }
    public void S000_CALL_DDZUGNRA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_GPRS_UPD_NRA, DDCUGNRA);
        CEP.TRC(SCCGWA, DDCUGNRA.MSG_ID);
        if (DDCUGNRA.MSG_ID.RC != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCUGNRA.MSG_ID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSG_ID, WS_ERR_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
