package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6001 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSCLDD DDCSCLDD = new DDCSCLDD();
    SCCGWA SCCGWA;
    DDB6001_AWA_6001 DDB6001_AWA_6001;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT6001 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6001_AWA_6001>");
        DDB6001_AWA_6001 = (DDB6001_AWA_6001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB6001_AWA_6001.BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6001_AWA_6001.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6001_AWA_6001.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6001_AWA_6001.STR_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DATE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6001_AWA_6001.END_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_DATE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6001_AWA_6001.ITM_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ITM_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCLDD);
        DDCSCLDD.BR = DDB6001_AWA_6001.BR;
        DDCSCLDD.CCY = DDB6001_AWA_6001.CCY;
        DDCSCLDD.CCY_TYPE = DDB6001_AWA_6001.CCY_TYPE;
        DDCSCLDD.STR_DATE = DDB6001_AWA_6001.STR_DATE;
        DDCSCLDD.END_DATE = DDB6001_AWA_6001.END_DATE;
        DDCSCLDD.ITM_NO = DDB6001_AWA_6001.ITM_NO;
        DDCSCLDD.DRCR_FLG = DDB6001_AWA_6001.DC_FLG;
        S000_CALL_DDZSCLDD();
        CEP.TRC(SCCGWA, DDCSCLDD.BR);
        CEP.TRC(SCCGWA, DDCSCLDD.CCY);
        CEP.TRC(SCCGWA, DDCSCLDD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCLDD.STR_DATE);
        CEP.TRC(SCCGWA, DDCSCLDD.END_DATE);
        CEP.TRC(SCCGWA, DDCSCLDD.ITM_NO);
    }
    public void S000_CALL_DDZSCLDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-CLDD", DDCSCLDD);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
