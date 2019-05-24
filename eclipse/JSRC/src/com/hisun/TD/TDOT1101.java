package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPOEWA;
import com.hisun.CI.CICACCU;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCSCINM;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDOT1101 {
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    TDOT1101_WS_OUTPUT_DATA WS_OUTPUT_DATA = new TDOT1101_WS_OUTPUT_DATA();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCTZZC TDCTZZC = new TDCTZZC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    TDCSFRZ TDCSFRZ = new TDCSFRZ();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DDCSCINM DDCSCINM = new DDCSCINM();
    TDRGPMP TDRGPMP = new TDRGPMP();
    TDRGRGP TDRGRGP = new TDRGRGP();
    SCCGWA SCCGWA;
    TDB1100_AWA_1100 TDB1100_AWA_1100;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT1101 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1100_AWA_1100>");
        TDB1100_AWA_1100 = (TDB1100_AWA_1100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_0_INFO();
        if (pgmRtn) return;
        B030_GET_LIST();
        if (pgmRtn) return;
        B050_WRITE_GRGP_PROC();
        if (pgmRtn) return;
        B070_GET_AC_BAL();
        if (pgmRtn) return;
        B080_WRT_VCH_EVENT();
        if (pgmRtn) return;
        B060_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB1100_AWA_1100.PROD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDB1100_AWA_1100.ID_TYP.equalsIgnoreCase("0")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_ID_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDB1100_AWA_1100.ID_NO.equalsIgnoreCase("0")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_ID_NO_MST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDB1100_AWA_1100.CCY.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDB1100_AWA_1100.TERM.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.DRAW_MTH);
        if (TDB1100_AWA_1100.DRAW_MTH == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_DRAW_MTH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDB1100_AWA_1100.VAL_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_0_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGPMP);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.COM_NO);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.PRD_NO);
        TDRGPMP.KEY.PROVD_NO = TDB1100_AWA_1100.COM_NO;
        TDRGPMP.KEY.PRD_NO = TDB1100_AWA_1100.PRD_NO;
        CEP.TRC(SCCGWA, TDRGPMP.KEY.PROVD_NO);
        CEP.TRC(SCCGWA, TDRGPMP.KEY.PRD_NO);
        S000_READ_TDTGPMP();
        if (pgmRtn) return;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTZZC);
        TDCTZZC.PROD_CD = TDB1100_AWA_1100.PROD_CD;
        TDCTZZC.BV_TYP = '4';
        TDCTZZC.PRT_FLG = '1';
        TDCTZZC.CARD_NO = TDB1100_AWA_1100.CARD_NO;
        TDCTZZC.ID_TYP = TDB1100_AWA_1100.ID_TYP;
        TDCTZZC.ID_NO = TDB1100_AWA_1100.ID_NO;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '2';
        CICACCU.DATA.AGR_NO = TDB1100_AWA_1100.OPP_CANO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        TDCTZZC.CI_NO = CICACCU.DATA.CI_NO;
        TDCTZZC.NAME = TDB1100_AWA_1100.NAME;
        TDCTZZC.CCY = TDB1100_AWA_1100.CCY;
        TDCTZZC.CCY_TYP = TDB1100_AWA_1100.CCY_TYP;
        TDCTZZC.TXN_AMT = TDRGPMP.AMT * TDB1100_AWA_1100.O_TOTAL;
        TDCTZZC.EXP_INT = TDRGPMP.INT_AMT * TDB1100_AWA_1100.O_TOTAL;
        CEP.TRC(SCCGWA, TDCTZZC.TXN_AMT);
        TDCTZZC.TERM = TDB1100_AWA_1100.TERM;
        TDCTZZC.DRAW_MTH = TDB1100_AWA_1100.DRAW_MTH;
        TDCTZZC.CUS_PSW_FLG = 'N';
        TDCTZZC.CROS_DR_FLG = '1';
        TDCTZZC.VAL_DT = TDB1100_AWA_1100.VAL_DT;
        TDCTZZC.CT_FLG = '2';
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '2';
        DCCPACTY.INPUT.AC = TDB1100_AWA_1100.OPP_CANO;
        S001_CALL_DCZPACTY();
        if (pgmRtn) return;
        TDCTZZC.OPP_AC = DCCPACTY.OUTPUT.STD_AC;
        TDCTZZC.OPP_CARD_NO = TDB1100_AWA_1100.OPP_CANO;
        TDCTZZC.OPP_NAME = TDB1100_AWA_1100.OPP_NAME;
        TDCTZZC.OPP_DRAW_MTH = TDB1100_AWA_1100.OPP_DWMH;
        TDCTZZC.OPP_ID_TYP = TDB1100_AWA_1100.OPP_IDTP;
        TDCTZZC.OPP_ID_NO = TDB1100_AWA_1100.OPP_ID;
        TDCTZZC.TXN_CHNL = TDB1100_AWA_1100.TXN_CHNL;
        TDCTZZC.TXN_PNT = TDB1100_AWA_1100.TXN_PNT;
        TDCTZZC.INSTR_MTH = TDB1100_AWA_1100.ISR_MTH;
        TDCTZZC.CALR_STD = "01";
        TDCTZZC.CHNL_FLG = 'Y';
        TDCTZZC.CHK_PSW_FLG = 'Y';
        TDCTZZC.STL_INT_AC = TDCTZZC.OPP_AC;
        TDCTZZC.INT_RAT = TDRGPMP.INT_RATE;
        CEP.TRC(SCCGWA, "AYAYAYAYAYAYAYAYAYAY");
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.PROD_CD);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.BV_CD);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.BV_TYP);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.PRT_FLG);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.CARD_NO);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.CI_NO);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.NAME);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.ADDR);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.TEL);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.CCY);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.CCY_TYP);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.FC_CD);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.TXN_AMT);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.TERM);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.DRAW_MTH);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.PSW_FLG);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.PSW);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.CS_D_FLG);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.VAL_DT);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.CT_FLG);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.OPP_CANO);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.OPP_NAME);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.OPP_DWMH);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.OPP_PSW);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.OPP_IDTP);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.OPP_ID);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.TXN_CHNL);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.TXN_PNT);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.ISR_MTH);
        CEP.TRC(SCCGWA, TDB1100_AWA_1100.O_TOTAL);
        S000_CALL_TDZTZZC();
        if (pgmRtn) return;
    }
    public void B050_CALL_AI_CR_UNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'C';
        AICPQIA.CD.AC_TYP = "1";
        AICPQIA.CD.BUSI_KND = "TD";
        AICPQIA.CCY = TDB1100_AWA_1100.CCY;
        AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S00_CALL_AIPINQIA();
        if (pgmRtn) return;
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = TDRGRGP.FEE_AMT + TDRGRGP.TOT_INT;
        AICUUPIA.DATA.CCY = TDB1100_AWA_1100.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AICUUPIA();
        if (pgmRtn) return;
    }
    public void B040_SPE_FRZ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
