package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT0135 {
    String K_LOC_CCY = "156";
    String CPN_DD_S_RCHQ_PROC = "DD-S-RCHQ-PROC";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSRCHQ DDCSRCHQ = new DDCSRCHQ();
    SCCGWA SCCGWA;
    DDB0135_AWA_0135 DDB0135_AWA_0135;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT0135 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB0135_AWA_0135>");
        DDB0135_AWA_0135 = (DDB0135_AWA_0135) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DISCARD_CHQB_INF_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.AC_NO);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.CCY);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.CHQ_TYP);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.STR_NO);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.END_NO);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.REMARKS);
        if (DDB0135_AWA_0135.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0135_AWA_0135.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0135_AWA_0135.STR_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0135_AWA_0135.END_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DISCARD_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSRCHQ);
        DDCSRCHQ.AC_NO = DDB0135_AWA_0135.AC_NO;
        DDCSRCHQ.CCY = DDB0135_AWA_0135.CCY;
        DDCSRCHQ.CCY_TYPE = DDB0135_AWA_0135.CCY_TYPE;
        DDCSRCHQ.CHQ_TYP = DDB0135_AWA_0135.CHQ_TYP;
        DDCSRCHQ.STR_CHQ_NO = DDB0135_AWA_0135.STR_NO;
        DDCSRCHQ.END_CHQ_NO = DDB0135_AWA_0135.END_NO;
        DDCSRCHQ.REMARKS = DDB0135_AWA_0135.REMARKS;
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.STR_NO);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.END_NO);
        CEP.TRC(SCCGWA, DDB0135_AWA_0135.CCY);
        S000_CALL_DDZSRCHQ();
    }
    public void S000_CALL_DDZSRCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_RCHQ_PROC, DDCSRCHQ);
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
