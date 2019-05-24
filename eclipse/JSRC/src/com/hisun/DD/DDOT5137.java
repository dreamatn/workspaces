package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5137 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSBINT DDCSBINT = new DDCSBINT();
    SCCGWA SCCGWA;
    DDB5137_AWA_5137 DDB5137_AWA_5137;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5137 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5137_AWA_5137>");
        DDB5137_AWA_5137 = (DDB5137_AWA_5137) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5137_AWA_5137.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5137_AWA_5137.PROL_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4020;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5137_AWA_5137.BRK_RATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4023;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBINT);
        DDCSBINT.AC_NO = DDB5137_AWA_5137.AC_NO;
        DDCSBINT.PROL_NO = DDB5137_AWA_5137.PROL_NO;
        DDCSBINT.BRK_RATE = DDB5137_AWA_5137.BRK_RATE;
        DDCSBINT.CCY = DDB5137_AWA_5137.CCY;
        DDCSBINT.TERM = DDB5137_AWA_5137.TERM;
        DDCSBINT.TXN_AMT = DDB5137_AWA_5137.TXN_AMT;
        DDCSBINT.BAL = DDB5137_AWA_5137.BAL;
        DDCSBINT.VAL_DATE = DDB5137_AWA_5137.VAL_DATE;
        DDCSBINT.EXP_DATE = DDB5137_AWA_5137.EXP_DATE;
        DDCSBINT.TXN_DATE = DDB5137_AWA_5137.TXN_DATE;
        CEP.TRC(SCCGWA, DDCSBINT.AC_NO);
        CEP.TRC(SCCGWA, DDCSBINT.PROL_NO);
        CEP.TRC(SCCGWA, DDCSBINT.BRK_RATE);
        CEP.TRC(SCCGWA, DDCSBINT.CCY);
        CEP.TRC(SCCGWA, DDCSBINT.TERM);
        CEP.TRC(SCCGWA, DDCSBINT.TXN_AMT);
        CEP.TRC(SCCGWA, DDCSBINT.BAL);
        CEP.TRC(SCCGWA, DDCSBINT.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSBINT.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSBINT.TXN_DATE);
        S000_CALL_DDZSBINT();
    }
    public void S000_CALL_DDZSBINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-BINT", DDCSBINT);
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
