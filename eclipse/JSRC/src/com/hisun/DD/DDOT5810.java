package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5810 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSATAC DDCSATAC = new DDCSATAC();
    SCCGWA SCCGWA;
    DDB5810_AWA_5810 DDB5810_AWA_5810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5810 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5810_AWA_5810>");
        DDB5810_AWA_5810 = (DDB5810_AWA_5810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5810_AWA_5810.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5810_AWA_5810.EFF_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5810_AWA_5810.AC);
        CEP.TRC(SCCGWA, DDB5810_AWA_5810.EFF_DATE);
        CEP.TRC(SCCGWA, DDB5810_AWA_5810.CHK_DATE);
        CEP.TRC(SCCGWA, DDB5810_AWA_5810.OPEN_NO);
        IBS.init(SCCGWA, DDCSATAC);
        DDCSATAC.AC = DDB5810_AWA_5810.AC;
        DDCSATAC.EFF_DATE = DDB5810_AWA_5810.EFF_DATE;
        DDCSATAC.EXP_DATE = DDB5810_AWA_5810.EXP_DATE;
        DDCSATAC.CHK_DATE = DDB5810_AWA_5810.CHK_DATE;
        DDCSATAC.OPEN_NO = DDB5810_AWA_5810.OPEN_NO;
        S000_CALL_DDZSATAC();
    }
    public void S000_CALL_DDZSATAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-AC-ACTIVE", DDCSATAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
