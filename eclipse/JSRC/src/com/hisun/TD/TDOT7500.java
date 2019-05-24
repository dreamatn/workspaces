package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT7500 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSLMCT TDCSLMCT = new TDCSLMCT();
    SCCGWA SCCGWA;
    TDB7500_AWA_7500 TDB7500_AWA_7500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT7500 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB7500_AWA_7500>");
        TDB7500_AWA_7500 = (TDB7500_AWA_7500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.FUNC);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.PROD_CD);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.STR_DATE);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.END_DATE);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.TERM);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.LM_POINT);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.BR);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.CHNL_NO);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.CI_LVL);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.TOT_BAL);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.DE_SEQ);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.PAGE_ROW);
        CEP.TRC(SCCGWA, TDB7500_AWA_7500.PAGE_NUM);
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB7500_AWA_7500.FUNC == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
        if (TDB7500_AWA_7500.PROD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRODUCT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSLMCT);
        TDCSLMCT.FUNC = TDB7500_AWA_7500.FUNC;
        TDCSLMCT.PROD_CD = TDB7500_AWA_7500.PROD_CD;
        TDCSLMCT.STR_DATE = TDB7500_AWA_7500.STR_DATE;
        TDCSLMCT.END_DATE = TDB7500_AWA_7500.END_DATE;
        TDCSLMCT.TERM = TDB7500_AWA_7500.TERM;
        TDCSLMCT.LM_POINT = TDB7500_AWA_7500.LM_POINT;
        TDCSLMCT.BR = TDB7500_AWA_7500.BR;
        TDCSLMCT.CHNL_NO = TDB7500_AWA_7500.CHNL_NO;
        TDCSLMCT.CI_LVL = TDB7500_AWA_7500.CI_LVL;
        TDCSLMCT.TOT_BAL = TDB7500_AWA_7500.TOT_BAL;
        TDCSLMCT.DE_SEQ = TDB7500_AWA_7500.DE_SEQ;
        TDCSLMCT.PAGE_ROW = TDB7500_AWA_7500.PAGE_ROW;
        TDCSLMCT.PAGE_NUM = TDB7500_AWA_7500.PAGE_NUM;
        S000_CALL_TDZSLMCT();
    }
    public void S000_CALL_TDZSLMCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-S-INQ-LMCT", TDCSLMCT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
