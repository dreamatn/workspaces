package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT2110 {
    String K_OUTPUT_FMT = "TD211";
    String WS_MSGID = " ";
    TDOT2110_WS_OUTDPUT_DATE WS_OUTDPUT_DATE = new TDOT2110_WS_OUTDPUT_DATE();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCTTZC TDCTTZC = new TDCTTZC();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    TDB1110_AWA_1110 TDB1110_AWA_1110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT2110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1110_AWA_1110>");
        TDB1110_AWA_1110 = (TDB1110_AWA_1110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB1110_AWA_1110.PROD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDB1110_AWA_1110.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        if (TDB1110_AWA_1110.CCY.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDB1110_AWA_1110.TERM.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (TDB1110_AWA_1110.DRAW_MTH == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, TDB1110_AWA_1110.CHNL_FLG);
        if (TDB1110_AWA_1110.VAL_DT == 0 
            && TDB1110_AWA_1110.CHNL_FLG != 'Y') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
            S000_ERR_MSG_PROC();
        }
        if (TDB1110_AWA_1110.CT_FLG == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_CT_FLG;
            S000_ERR_MSG_PROC();
        }
        if (TDB1110_AWA_1110.CARD_NO.trim().length() > 0) {
            TDCTTZC.BV_TYP = '4';
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        R000_GET_AC_NM();
        IBS.init(SCCGWA, TDCTTZC);
        TDCTTZC.PROD_CD = TDB1110_AWA_1110.PROD_CD;
        TDCTTZC.BV_CD = TDB1110_AWA_1110.BV_CD;
        TDCTTZC.BV_TYP = TDB1110_AWA_1110.BV_TYP;
        TDCTTZC.PRT_FLG = TDB1110_AWA_1110.PRT_FLG;
        TDCTTZC.BV_NO = TDB1110_AWA_1110.BV_NO;
        TDCTTZC.MAIN_AC = TDB1110_AWA_1110.MAIN_AC;
        TDCTTZC.CARD_NO = TDB1110_AWA_1110.CARD_NO;
        TDCTTZC.CVV = TDB1110_AWA_1110.CVV;
        TDCTTZC.ID_TYP = TDB1110_AWA_1110.ID_TYP;
        TDCTTZC.ID_NO = TDB1110_AWA_1110.ID_NO;
        TDCTTZC.CI_NO = TDB1110_AWA_1110.CI_NO;
        TDCTTZC.NAME = TDB1110_AWA_1110.NAME;
        TDCTTZC.ADDR = TDB1110_AWA_1110.ADDR;
        TDCTTZC.TEL = TDB1110_AWA_1110.TEL;
        TDCTTZC.CCY = TDB1110_AWA_1110.CCY;
        TDCTTZC.CCY_TYP = TDB1110_AWA_1110.CCY_TYP;
        TDCTTZC.FC_CD = TDB1110_AWA_1110.FC_CD;
        TDCTTZC.TXN_AMT = TDB1110_AWA_1110.TXN_AMT;
        TDCTTZC.TERM = TDB1110_AWA_1110.TERM;
        TDCTTZC.DRAW_MTH = TDB1110_AWA_1110.DRAW_MTH;
        TDCTTZC.CUS_PSW_FLG = TDB1110_AWA_1110.PSW_FLG;
        TDCTTZC.PSW = TDB1110_AWA_1110.PSW;
        TDCTTZC.CROS_DR_FLG = TDB1110_AWA_1110.CS_D_FLG;
        TDCTTZC.VAL_DT = TDB1110_AWA_1110.VAL_DT;
        if (TDB1110_AWA_1110.VAL_DT == 0 
            && TDB1110_AWA_1110.CHNL_FLG == 'Y') {
            TDCTTZC.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        TDCTTZC.CT_FLG = TDB1110_AWA_1110.CT_FLG;
        TDCTTZC.OPP_AC = TDB1110_AWA_1110.OPP_AC;
        TDCTTZC.OPP_BV_NO = TDB1110_AWA_1110.OPP_BVNO;
        TDCTTZC.OPP_CARD_NO = TDB1110_AWA_1110.OPP_CANO;
        TDCTTZC.OPP_NAME = TDB1110_AWA_1110.OPP_NAME;
        TDCTTZC.OPP_REV_NO = TDB1110_AWA_1110.OPP_RVNO;
        TDCTTZC.OPP_DRAW_MTH = TDB1110_AWA_1110.OPP_DWMH;
        TDCTTZC.OPP_PSW = TDB1110_AWA_1110.OPP_PSW;
        TDCTTZC.OPP_ID_TYP = TDB1110_AWA_1110.OPP_IDTP;
        TDCTTZC.OPP_ID_NO = TDB1110_AWA_1110.OPP_ID;
        TDCTTZC.CHQ_TYPE = TDB1110_AWA_1110.CHQ_TYPE;
        TDCTTZC.CHQ_NO = TDB1110_AWA_1110.CHQ_NO;
        TDCTTZC.CHQ_DATE = TDB1110_AWA_1110.CHQ_DATE;
        TDCTTZC.TXN_CHNL = TDB1110_AWA_1110.TXN_CHNL;
        TDCTTZC.CHNL_FLG = TDB1110_AWA_1110.CHNL_FLG;
        TDCTTZC.TXN_PNT = TDB1110_AWA_1110.TXN_PNT;
        TDCTTZC.AC = TDB1110_AWA_1110.AC;
        TDCTTZC.INSTR_MTH = TDB1110_AWA_1110.ISR_MTH;
        TDCTTZC.CALR_STD = TDB1110_AWA_1110.CALR_STD;
        TDCTTZC.CHK_PSW_FLG = TDB1110_AWA_1110.CHK_PSW;
        TDCTTZC.CHNL_FLG = 'Y';
        TDCTTZC.MMO = TDB1110_AWA_1110.MMO;
        TDCTTZC.REMARK = TDB1110_AWA_1110.REMARK;
        S000_CALL_TDZTTZC();
        TDB1110_AWA_1110.AC = TDCTTZC.AC;
    }
    public void R000_GET_AC_NM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        if (TDB1110_AWA_1110.CARD_NO.trim().length() > 0) {
            CICACCU.DATA.AGR_NO = TDB1110_AWA_1110.CARD_NO;
        } else {
            CICACCU.DATA.AGR_NO = TDB1110_AWA_1110.MAIN_AC;
        }
        S000_CALL_CIZACCU();
        if (TDB1110_AWA_1110.CI_NO.trim().length() == 0) {
            TDB1110_AWA_1110.CI_NO = CICACCU.DATA.CI_NO;
        }
        if (TDB1110_AWA_1110.NAME.trim().length() == 0) {
            TDB1110_AWA_1110.NAME = CICACCU.DATA.CI_CNM;
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZTTZC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TTZ-CR", TDCTTZC);
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
