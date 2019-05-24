package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT0137 {
    String CPN_DD_S_ACHQ_PROC = "DD-S-ACHQ-PROC";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSACHQ DDCSACHQ = new DDCSACHQ();
    SCCGWA SCCGWA;
    DDB0137_AWA_0137 DDB0137_AWA_0137;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT0137 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB0137_AWA_0137>");
        DDB0137_AWA_0137 = (DDB0137_AWA_0137) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_QUERY_CHQB_INF_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.CHQ_OPT);
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.AC_NO);
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.STR_DT);
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.END_DT);
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.CHQ_STS);
        if (DDB0137_AWA_0137.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0137_AWA_0137.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0137_AWA_0137.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0137_AWA_0137.STR_DT == 0) {
            CEP.TRC(SCCGWA, "00000000");
            if ("20000101".trim().length() == 0) DDB0137_AWA_0137.STR_DT = 0;
            else DDB0137_AWA_0137.STR_DT = Integer.parseInt("20000101");
        }
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.STR_DT);
        if (DDB0137_AWA_0137.END_DT == 0) {
            CEP.TRC(SCCGWA, "99999999");
            if ("99991231".trim().length() == 0) DDB0137_AWA_0137.END_DT = 0;
            else DDB0137_AWA_0137.END_DT = Integer.parseInt("99991231");
        }
        CEP.TRC(SCCGWA, DDB0137_AWA_0137.END_DT);
        if (DDB0137_AWA_0137.STR_DT > DDB0137_AWA_0137.END_DT) {
            CEP.TRC(SCCGWA, "STR DATE GT END DATE");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ED_DT_LESSTHAN_ST_DT;
            S000_ERR_MSG_PROC();
        }
        if (DDB0137_AWA_0137.CHQ_STS != '0' 
            && DDB0137_AWA_0137.CHQ_STS != '7') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_QUERY_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSACHQ);
        DDCSACHQ.OPT = DDB0137_AWA_0137.CHQ_OPT;
        DDCSACHQ.AC_NO = DDB0137_AWA_0137.AC_NO;
        DDCSACHQ.STR_CHQ_DT = DDB0137_AWA_0137.STR_DT;
        DDCSACHQ.END_CHQ_DT = DDB0137_AWA_0137.END_DT;
        DDCSACHQ.CHQ_STS = DDB0137_AWA_0137.CHQ_STS;
        DDCSACHQ.CCY = DDB0137_AWA_0137.CCY;
        DDCSACHQ.CCY_TYPE = DDB0137_AWA_0137.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCSACHQ.OPT);
        CEP.TRC(SCCGWA, DDCSACHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCSACHQ.STR_CHQ_DT);
        CEP.TRC(SCCGWA, DDCSACHQ.END_CHQ_DT);
        CEP.TRC(SCCGWA, DDCSACHQ.CHQ_STS);
        S000_CALL_DDZSACHQ();
    }
    public void S000_CALL_DDZSACHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_ACHQ_PROC, DDCSACHQ);
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
