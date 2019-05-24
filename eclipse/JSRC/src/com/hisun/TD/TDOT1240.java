package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1240 {
    String WS_MSGID = " ";
    TDOT1240_WS_TERM1 WS_TERM1 = new TDOT1240_WS_TERM1();
    TDOT1240_WS_TERM2 WS_TERM2 = new TDOT1240_WS_TERM2();
    TDOT1240_WS_TERM3 WS_TERM3 = new TDOT1240_WS_TERM3();
    TDOT1240_WS_TERM4 WS_TERM4 = new TDOT1240_WS_TERM4();
    TDOT1240_WS_TERM5 WS_TERM5 = new TDOT1240_WS_TERM5();
    TDOT1240_WS_TERM6 WS_TERM6 = new TDOT1240_WS_TERM6();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCTCQD TDCTCQD = new TDCTCQD();
    SCCGWA SCCGWA;
    TDB1240_AWA_1240 TDB1240_AWA_1240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1240 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1240_AWA_1240>");
        TDB1240_AWA_1240 = (TDB1240_AWA_1240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTCQD);
        TDCTCQD.BV_CD = TDB1240_AWA_1240.BV_CD;
        TDCTCQD.BV_TYP = TDB1240_AWA_1240.BV_TYP;
        TDCTCQD.BV_NO = TDB1240_AWA_1240.BV_NO;
        TDCTCQD.CARD_NO = TDB1240_AWA_1240.CARD_NO;
        TDCTCQD.CVV = TDB1240_AWA_1240.CVV;
        TDCTCQD.AC_SEQ = TDB1240_AWA_1240.AC_SEQ;
        TDCTCQD.AC = TDB1240_AWA_1240.AC;
        TDCTCQD.NAME = TDB1240_AWA_1240.NAME;
        TDCTCQD.ADDR = TDB1240_AWA_1240.ADDR;
        TDCTCQD.CCY = TDB1240_AWA_1240.CCY;
        TDCTCQD.CCY_TYP = TDB1240_AWA_1240.CCY_TYP;
        TDCTCQD.FC_CD = TDB1240_AWA_1240.FC_CD;
        TDCTCQD.PAYED_INT = TDB1240_AWA_1240.PAYED_IN;
        TDCTCQD.TXN_AMT = TDB1240_AWA_1240.TXN_AMT;
        TDCTCQD.DRAW_MTH = TDB1240_AWA_1240.DRAW_MTH;
        TDCTCQD.PSW = TDB1240_AWA_1240.PSW;
        TDCTCQD.SEAL_NO = TDB1240_AWA_1240.SEAL_NO;
        TDCTCQD.ID_TYP = TDB1240_AWA_1240.ID_TYP;
        TDCTCQD.ID_NO = TDB1240_AWA_1240.ID_NO;
        TDCTCQD.INT_FLG = TDB1240_AWA_1240.INT_FLG;
        TDCTCQD.VAL_DT = TDB1240_AWA_1240.VAL_DT;
        TDCTCQD.PREV_FLG = TDB1240_AWA_1240.PREV_FLG;
        TDCTCQD.CT_FLG = TDB1240_AWA_1240.CT_FLG;
        TDCTCQD.PRT_FLG = TDB1240_AWA_1240.PRT_FLG;
        TDCTCQD.OPP_AC = TDB1240_AWA_1240.OPP_AC;
        TDCTCQD.OPP_BV_NO = TDB1240_AWA_1240.OPP_BVNO;
        TDCTCQD.OPP_CARD_NO = TDB1240_AWA_1240.OPP_CANO;
        TDCTCQD.OPP_CVV = TDB1240_AWA_1240.OPP_CVV;
        TDCTCQD.OPP_NAME = TDB1240_AWA_1240.OPP_NAME;
        TDCTCQD.ATTY_ID_TYP = TDB1240_AWA_1240.ATTY_ITP;
        TDCTCQD.ATTY_ID_NO = TDB1240_AWA_1240.ATTY_INO;
        TDCTCQD.ATTY_NAME = TDB1240_AWA_1240.ATTY_NAM;
        TDCTCQD.TXN_CHNL = TDB1240_AWA_1240.TXN_CHNL;
        TDCTCQD.TXN_PNT = TDB1240_AWA_1240.TXN_PNT;
        TDCTCQD.DRAW_TOT_AMT = TDB1240_AWA_1240.DRAW_TAM;
        TDCTCQD.PBAL = TDB1240_AWA_1240.PBAL;
        TDCTCQD.PVAL_DT = TDB1240_AWA_1240.PVAL_DT;
        TDCTCQD.CREV_NO = TDB1240_AWA_1240.CREV_NO;
        TDCTCQD.AC_TRK2 = TDB1240_AWA_1240.AC_TRK2;
        TDCTCQD.AC_TRK3 = TDB1240_AWA_1240.AC_TRK3;
        TDCTCQD.OPP_TRK2 = TDB1240_AWA_1240.OPP_TRK2;
        TDCTCQD.OPP_TRK3 = TDB1240_AWA_1240.OPP_TRK3;
        CEP.TRC(SCCGWA, TDB1240_AWA_1240.AC_TRK2);
        CEP.TRC(SCCGWA, TDB1240_AWA_1240.AC_TRK3);
        CEP.TRC(SCCGWA, TDB1240_AWA_1240.OPP_TRK2);
        CEP.TRC(SCCGWA, TDB1240_AWA_1240.OPP_TRK3);
        CEP.TRC(SCCGWA, TDCTCQD.CREV_NO);
        S000_CALL_TDZTCQD();
    }
    public void S000_CALL_TDZTCQD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TCQ-DR", TDCTCQD);
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