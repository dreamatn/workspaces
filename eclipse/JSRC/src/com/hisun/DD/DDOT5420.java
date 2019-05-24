package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5420 {
    String K_LOC_CCY = "CNY";
    String CPN_DD_S_YCHQ_PROC = "DD-S-YCHQ-PROC";
    String WS_ERR_MSG = " ";
    short WS_RET = 0;
    short WS_RMDR = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSYCHQ DDCSYCHQ = new DDCSYCHQ();
    SCCGWA SCCGWA;
    DDB5420_AWA_5420 DDB5420_AWA_5420;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5420 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5420_AWA_5420>");
        DDB5420_AWA_5420 = (DDB5420_AWA_5420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_QUERY_CHQB_INF_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5420_AWA_5420.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5420_AWA_5420.CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5420_AWA_5420.AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5420_AWA_5420.STS == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_QUERY_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSYCHQ);
        DDCSYCHQ.AC_NO = DDB5420_AWA_5420.AC_NO;
        DDCSYCHQ.CHQ_NO = DDB5420_AWA_5420.CHQ_NO;
        DDCSYCHQ.AMT = DDB5420_AWA_5420.AMT;
        DDCSYCHQ.STS = DDB5420_AWA_5420.STS;
        DDCSYCHQ.TXN_NARR = DDB5420_AWA_5420.TXN_NARR;
        DDCSYCHQ.REMARK = DDB5420_AWA_5420.REMARK;
        CEP.TRC(SCCGWA, DDCSYCHQ.CHQ_NO);
        S000_CALL_DDZSYCHQ();
    }
    public void S000_CALL_DDZSYCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_YCHQ_PROC, DDCSYCHQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
