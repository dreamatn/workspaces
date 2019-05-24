package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1230 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCTDHD TDCTDHD = new TDCTDHD();
    SCCGWA SCCGWA;
    TDB1230_AWA_1230 TDB1230_AWA_1230;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1230_AWA_1230>");
        TDB1230_AWA_1230 = (TDB1230_AWA_1230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB1230_AWA_1230.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.ID_TYP.equalsIgnoreCase("0")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_ID_TYPE_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.ID_NO.equalsIgnoreCase("0")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_ID_NO_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.CCY.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.DRAW_MTH == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.TXN_AMT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_IPT;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.VAL_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
            S000_ERR_MSG_PROC();
        }
        if (TDB1230_AWA_1230.CT_FLG == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1230_AWA_1230.NAME);
        IBS.init(SCCGWA, TDCTDHD);
        TDCTDHD.BV_CD = TDB1230_AWA_1230.BV_CD;
        TDCTDHD.BV_TYP = TDB1230_AWA_1230.BV_TYP;
        TDCTDHD.BV_NO = TDB1230_AWA_1230.BV_NO;
        TDCTDHD.MAIN_AC = TDB1230_AWA_1230.MAIN_AC;
        TDCTDHD.CARD_NO = TDB1230_AWA_1230.CARD_NO;
        TDCTDHD.CVV = TDB1230_AWA_1230.CVV;
        TDCTDHD.AC_SEQ = TDB1230_AWA_1230.AC_SEQ;
        TDCTDHD.AC = TDB1230_AWA_1230.AC;
        TDCTDHD.NAME = TDB1230_AWA_1230.NAME;
        TDCTDHD.ADDR = TDB1230_AWA_1230.ADDR;
        TDCTDHD.CCY = TDB1230_AWA_1230.CCY;
        TDCTDHD.CCY_TYP = TDB1230_AWA_1230.CCY_TYP;
        TDCTDHD.FC_CD = TDB1230_AWA_1230.FC_CD;
        TDCTDHD.TXN_AMT = TDB1230_AWA_1230.TXN_AMT;
        TDCTDHD.DRAW_MTH = TDB1230_AWA_1230.DRAW_MTH;
        TDCTDHD.PSW = TDB1230_AWA_1230.PSW;
        TDCTDHD.ID_TYP = TDB1230_AWA_1230.ID_TYP;
        TDCTDHD.ID_NO = TDB1230_AWA_1230.ID_NO;
        TDCTDHD.INT_FLG = TDB1230_AWA_1230.INT_FLG;
        TDCTDHD.VAL_DT = TDB1230_AWA_1230.VAL_DT;
        TDCTDHD.CT_FLG = TDB1230_AWA_1230.CT_FLG;
        TDCTDHD.PRT_FLG = TDB1230_AWA_1230.PRT_FLG;
        TDCTDHD.OPP_AC = TDB1230_AWA_1230.OPP_AC;
        TDCTDHD.OPP_BV_NO = TDB1230_AWA_1230.OPP_BVNO;
        TDCTDHD.OPP_CARD_NO = TDB1230_AWA_1230.OPP_CANO;
        TDCTDHD.OPP_CVV = TDB1230_AWA_1230.OPP_CVV;
        TDCTDHD.OPP_NAME = TDB1230_AWA_1230.OPP_NAME;
        TDCTDHD.TXN_CHNL = TDB1230_AWA_1230.TXN_CHNL;
        TDCTDHD.TXN_PNT = TDB1230_AWA_1230.TXN_PNT;
        TDCTDHD.DRAW_TOT_AMT = TDB1230_AWA_1230.TOT_AMT;
        TDCTDHD.CREV_NO = TDB1230_AWA_1230.CREV_NO;
        TDCTDHD.AC_TRK2 = TDB1230_AWA_1230.AC_TRK2;
        TDCTDHD.AC_TRK3 = TDB1230_AWA_1230.AC_TRK3;
        TDCTDHD.OPP_TRK2 = TDB1230_AWA_1230.OPP_TRK2;
        TDCTDHD.OPP_TRK3 = TDB1230_AWA_1230.OPP_TRK3;
        CEP.TRC(SCCGWA, TDB1230_AWA_1230.AC_TRK2);
        CEP.TRC(SCCGWA, TDB1230_AWA_1230.AC_TRK3);
        CEP.TRC(SCCGWA, TDB1230_AWA_1230.OPP_TRK2);
        CEP.TRC(SCCGWA, TDB1230_AWA_1230.OPP_TRK3);
        S000_CALL_TDZTDHD();
    }
    public void S000_CALL_TDZTDHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDH-DR", TDCTDHD);
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
