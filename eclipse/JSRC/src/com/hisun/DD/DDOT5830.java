package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5830 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSVALD DDCSVALD = new DDCSVALD();
    SCCGWA SCCGWA;
    DDB5830_AWA_5830 DDB5830_AWA_5830;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5830 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5830_AWA_5830>");
        DDB5830_AWA_5830 = (DDB5830_AWA_5830) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B030_TRANS_MAIN_PROC();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5830_AWA_5830.ACNO);
        if (DDB5830_AWA_5830.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB5830_AWA_5830.ACNO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5830_AWA_5830.VAL_RLT != 'Y' 
            && DDB5830_AWA_5830.VAL_RLT != 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_RLT_INVALID;
            WS_FLD_NO = DDB5830_AWA_5830.VAL_RLT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSVALD);
        DDCSVALD.ACNO = DDB5830_AWA_5830.ACNO;
        DDCSVALD.VAL_RLT = DDB5830_AWA_5830.VAL_RLT;
        S000_CALL_DDZSVALD();
    }
    public void S000_CALL_DDZSVALD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSVALD", DDCSVALD);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
