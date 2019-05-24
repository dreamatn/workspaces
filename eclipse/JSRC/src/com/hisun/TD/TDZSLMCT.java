package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSLMCT {
    int JIBS_tmp_int;
    DBParm TDTLMCT_RD;
    brParm TDTLMCT_BR = new brParm();
    String K_INQ_FMT = "TD500";
    String K_BRW_FMT = "TD502";
    int K_MAX_ROW = 20;
    int K_HEAD_BR = 100000;
    String WS_MSGID = " ";
    int WS_I = 0;
    int WS_BAL_CNT = 0;
    double WS_LMCT_TOT_BAL = 0;
    short WS_LM_LVL = 0;
    short WS_TMP_LVL = 0;
    String WS_TMP_POINT = " ";
    int WS_TMP_BR = 0;
    TDZSLMCT_WS_OUTPUT WS_OUTPUT = new TDZSLMCT_WS_OUTPUT();
    char WS_LMCT_FLG = ' ';
    char WS_HEADBR_FLG = ' ';
    char WS_SAMELVL_FLG = ' ';
    char WS_SAME_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDRLMCT TDRLMCT = new TDRLMCT();
    TDCOLMCT TDCOLMCT = new TDCOLMCT();
    int WS_HEADBR = 0;
    int WS_LMCT_CNT = 0;
    int WS_STR_ROW = 0;
    SCCGWA SCCGWA;
    TDCSLMCT TDCSLMCT;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSLMCT TDCSLMCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSLMCT = TDCSLMCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSLMCT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        WS_HEADBR = 100000;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (TDCSLMCT.FUNC == 'I') {
            B030_FUNC_INQUIRE();
            B200_OUTPUT_PROC();
        } else if (TDCSLMCT.FUNC == 'B') {
            B050_FUNC_BROWSE();
            B200_BROWSE_PROC();
        } else if (TDCSLMCT.FUNC == 'A') {
            B070_FUNC_WRITE();
        } else if (TDCSLMCT.FUNC == 'D') {
            B090_FUNC_DELETE();
        } else if (TDCSLMCT.FUNC == 'M') {
            B110_FUNC_MODIFY();
            B200_OUTPUT_PROC();
        } else if (TDCSLMCT.FUNC == 'Q') {
            B130_FUNC_INQ_DT();
            B200_OUTPUT_PROC();
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSLMCT.PROD_CD);
        CEP.TRC(SCCGWA, TDCSLMCT.STR_DATE);
        CEP.TRC(SCCGWA, TDCSLMCT.DE_SEQ);
        CEP.TRC(SCCGWA, TDCSLMCT.PAGE_ROW);
        CEP.TRC(SCCGWA, TDCSLMCT.PAGE_NUM);
        if (TDCSLMCT.LM_POINT == null) TDCSLMCT.LM_POINT = "";
        JIBS_tmp_int = TDCSLMCT.LM_POINT.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCSLMCT.LM_POINT += " ";
        if (TDCSLMCT.LM_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            if (TDCSLMCT.LM_POINT == null) TDCSLMCT.LM_POINT = "";
            JIBS_tmp_int = TDCSLMCT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCSLMCT.LM_POINT += " ";
            TDCSLMCT.LM_POINT = "1" + TDCSLMCT.LM_POINT.substring(1);
        }
        if (TDCSLMCT.PROD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRODUCT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDCSLMCT.PAGE_ROW == 0) {
            TDCSLMCT.PAGE_ROW = K_MAX_ROW;
        }
        if (TDCSLMCT.PAGE_NUM == 0) {
            TDCSLMCT.PAGE_NUM = 1;
        }
    }
    public void B030_FUNC_INQUIRE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
        TDRLMCT.KEY.END_DATE = TDCSLMCT.END_DATE;
        TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
        TDRLMCT.KEY.BR = TDCSLMCT.BR;
        TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
        TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
        T000_READ_TDTLMCT();
    }
    public void B050_FUNC_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSLMCT.PAGE_NUM);
        CEP.TRC(SCCGWA, TDCSLMCT.PAGE_ROW);
        WS_STR_ROW = ( TDCSLMCT.PAGE_NUM - 1 ) * TDCSLMCT.PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_STR_ROW);
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
        TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
        if (TDCSLMCT.STR_DATE != 0) {
            CEP.TRC(SCCGWA, "QUIRE BY PROD-CD + STR-DATE");
            R000_GET_PAGE_NUM_BYDT();
            T000_STARTBR_TDTLMCT_BYDT();
        } else {
            CEP.TRC(SCCGWA, "QUIRE BY PROD-CD + DE-SEQ");
            if (TDCSLMCT.DE_SEQ.trim().length() == 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ;
                S000_ERR_MSG_PROC();
            }
            R000_GET_PAGE_NUM_BYSEQ();
            T000_STARTBR_TDTLMCT_BYSEQ();
        }
        T000_READNEXT_TDTLMCT_STR();
        for (WS_I = 1; WS_LMCT_FLG != 'N' 
            && WS_I <= TDCSLMCT.PAGE_ROW; WS_I += 1) {
            R000_GET_OUTPUT_LIST();
            T000_READNEXT_TDTLMCT();
        }
        T000_ENDBR_TDTLMCT();
        WS_OUTPUT.WS_PAGE_INF.WS_PROD_CD = TDCSLMCT.PROD_CD;
    }
    public void R000_GET_OUTPUT_LIST() throws IOException,SQLException,Exception {
        WS_OUTPUT.WS_DATA[WS_I-1].WS_STR_DATE = TDRLMCT.KEY.STR_DATE;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_END_DATE = TDRLMCT.KEY.END_DATE;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_TERM = TDRLMCT.KEY.TERM;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_LM_POINT = TDRLMCT.LM_POINT;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_BR = TDRLMCT.KEY.BR;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_CHNL_NO = TDRLMCT.KEY.CHNL_NO;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_CI_LVL = TDRLMCT.KEY.CI_LVL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_TOT_BAL = TDRLMCT.TOT_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_AGN_BAL = TDRLMCT.AGN_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_AGN_USE_BAL = TDRLMCT.AGN_USE_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_AGN_CURR_BAL = TDRLMCT.AGN_CURR_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_UNAGN_BAL = TDRLMCT.UNAGN_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_UNAGN_USE_BAL = TDRLMCT.UNAGN_USE_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_UNAGN_CURR_BAL = TDRLMCT.UNAGN_CURR_BAL;
        WS_OUTPUT.WS_DATA[WS_I-1].WS_DE_SEQ = TDRLMCT.DE_SEQ;
        CEP.TRC(SCCGWA, "----LIST------");
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_END_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_TERM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_LM_POINT);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_CHNL_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_CI_LVL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_TOT_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_AGN_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_AGN_USE_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_AGN_CURR_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_UNAGN_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_UNAGN_USE_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_UNAGN_CURR_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_DATA[WS_I-1].WS_DE_SEQ);
    }
    public void R000_GET_PAGE_NUM_BYDT() throws IOException,SQLException,Exception {
        T000_COUNT_TDTLMCT_BYDT();
        WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM = WS_LMCT_CNT;
        WS_OUTPUT.WS_PAGE_INF.WS_CURR_PAGE = TDCSLMCT.PAGE_NUM;
        WS_BAL_CNT = WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM % TDCSLMCT.PAGE_ROW;
        WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE = (int) ((WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM - WS_BAL_CNT) / TDCSLMCT.PAGE_ROW);
        if (WS_BAL_CNT != 0) {
            WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE += 1;
        }
        if (WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM > TDCSLMCT.PAGE_ROW) {
            if (WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE == TDCSLMCT.PAGE_NUM) {
                WS_OUTPUT.WS_PAGE_INF.WS_LAST_FLG = 'Y';
                WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW = WS_BAL_CNT;
            } else {
                WS_OUTPUT.WS_PAGE_INF.WS_LAST_FLG = 'N';
                WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW = TDCSLMCT.PAGE_ROW;
            }
        } else {
            WS_OUTPUT.WS_PAGE_INF.WS_LAST_FLG = 'Y';
            WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW = WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM;
        }
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW);
    }
    public void R000_GET_PAGE_NUM_BYSEQ() throws IOException,SQLException,Exception {
        T000_COUNT_TDTLMCT_BYSEQ();
        WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM = WS_LMCT_CNT;
        WS_OUTPUT.WS_PAGE_INF.WS_CURR_PAGE = TDCSLMCT.PAGE_NUM;
        WS_BAL_CNT = WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM % TDCSLMCT.PAGE_ROW;
        WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE = (int) ((WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM - WS_BAL_CNT) / TDCSLMCT.PAGE_ROW);
        if (WS_BAL_CNT != 0) {
            WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE += 1;
        }
        if (WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM > TDCSLMCT.PAGE_ROW) {
            if (WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE == TDCSLMCT.PAGE_NUM) {
                WS_OUTPUT.WS_PAGE_INF.WS_LAST_FLG = 'Y';
                WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW = WS_BAL_CNT;
            } else {
                WS_OUTPUT.WS_PAGE_INF.WS_LAST_FLG = 'N';
                WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW = TDCSLMCT.PAGE_ROW;
            }
        } else {
            WS_OUTPUT.WS_PAGE_INF.WS_LAST_FLG = 'Y';
            WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW = WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM;
        }
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_TOT_PAGE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_TOT_NUM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_PAGE_INF.WS_PAGE_ROW);
    }
    public void B070_FUNC_WRITE() throws IOException,SQLException,Exception {
        if (TDCSLMCT.BR == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        WS_LM_LVL = 1;
        for (WS_I = 1; WS_I <= 8; WS_I += 1) {
            if (TDCSLMCT.LM_POINT == null) TDCSLMCT.LM_POINT = "";
            JIBS_tmp_int = TDCSLMCT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCSLMCT.LM_POINT += " ";
            if (TDCSLMCT.LM_POINT.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                WS_LM_LVL = (short) (WS_LM_LVL + 1);
            }
        }
        CEP.TRC(SCCGWA, TDCSLMCT.LM_POINT);
        CEP.TRC(SCCGWA, WS_LM_LVL);
        R000_GET_HEADBR_PROC();
        if (WS_HEADBR_FLG == 'Y') {
            if (TDCSLMCT.BR == K_HEAD_BR 
                && WS_LM_LVL == 1) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_HEADBR_REC_EXIST;
                S000_ERR_MSG_PROC();
            } else {
                CEP.TRC(SCCGWA, TDCSLMCT.DE_SEQ);
                CEP.TRC(SCCGWA, TDRLMCT.DE_SEQ);
                if (!TDCSLMCT.DE_SEQ.equalsIgnoreCase(TDRLMCT.DE_SEQ)) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_DE_SEQ_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
                if (TDCSLMCT.STR_DATE != TDRLMCT.KEY.STR_DATE) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_DE_STRDT_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
                if (TDCSLMCT.END_DATE != TDRLMCT.KEY.END_DATE) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_DE_ENDDT_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
                R000_CHECK_SAME_LVL_PROC();
                B071_WRITE_LMCT_PROC();
                B073_UPDATE_SUPLVL_PROC();
            }
        } else {
            if (TDCSLMCT.TERM.trim().length() == 0) {
                IBS.init(SCCGWA, TDRLMCT);
                TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
                TDRLMCT.KEY.BR = K_HEAD_BR;
                TDRLMCT.LM_LVL = 1;
                T000_READ_BLANK_TERM();
            }
            if (TDCSLMCT.BR == K_HEAD_BR) {
                B071_WRITE_LMCT_PROC();
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_HEADBR_REC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_GET_HEADBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
        TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
        TDRLMCT.KEY.BR = K_HEAD_BR;
        TDRLMCT.LM_LVL = 1;
        T000_READ_TDTLMCT_BYBR();
    }
    public void B071_WRITE_LMCT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
        TDRLMCT.KEY.END_DATE = TDCSLMCT.END_DATE;
        TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
        TDRLMCT.KEY.BR = TDCSLMCT.BR;
        TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
        TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
        T000_CHECK_TDTLMCT();
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
        TDRLMCT.KEY.END_DATE = TDCSLMCT.END_DATE;
        TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
        TDRLMCT.KEY.BR = TDCSLMCT.BR;
        TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
        TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
        TDRLMCT.LM_POINT = TDCSLMCT.LM_POINT;
        TDRLMCT.TOT_BAL = TDCSLMCT.TOT_BAL;
        TDRLMCT.AGN_BAL = 0;
        TDRLMCT.AGN_USE_BAL = 0;
        TDRLMCT.AGN_CURR_BAL = 0;
        TDRLMCT.UNAGN_BAL = TDCSLMCT.TOT_BAL;
        TDRLMCT.UNAGN_USE_BAL = 0;
        TDRLMCT.UNAGN_CURR_BAL = TDCSLMCT.TOT_BAL;
        TDRLMCT.LM_LVL = WS_LM_LVL;
        TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
        TDRLMCT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRLMCT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRLMCT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRLMCT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTLMCT();
    }
    public void B073_UPDATE_SUPLVL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_LM_LVL);
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
        TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
        WS_TMP_LVL = WS_LM_LVL;
        WS_TMP_LVL = (short) (WS_TMP_LVL - 1);
        TDRLMCT.LM_LVL = WS_TMP_LVL;
        CEP.TRC(SCCGWA, TDRLMCT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMCT.KEY.TERM);
        CEP.TRC(SCCGWA, TDRLMCT.DE_SEQ);
        CEP.TRC(SCCGWA, TDRLMCT.LM_LVL);
        T000_READ_SUP_RECORD_FIRST();
        CEP.TRC(SCCGWA, TDRLMCT.LM_POINT);
        WS_TMP_POINT = TDRLMCT.LM_POINT;
        if (WS_TMP_LVL > 1) {
            if (WS_TMP_POINT == null) WS_TMP_POINT = "";
            JIBS_tmp_int = WS_TMP_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_TMP_POINT += " ";
            if (TDCSLMCT.LM_POINT == null) TDCSLMCT.LM_POINT = "";
            JIBS_tmp_int = TDCSLMCT.LM_POINT.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) TDCSLMCT.LM_POINT += " ";
            if (WS_TMP_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                && TDCSLMCT.LM_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_TMP_BR = TDCSLMCT.BR;
                WS_SAME_FLG = 'N';
                IBS.init(SCCGWA, TDRLMCT);
                TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
                TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
                TDRLMCT.LM_LVL = WS_TMP_LVL;
                TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
                TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
                for (WS_I = 1; WS_I <= 4 
                    && WS_SAME_FLG != 'Y'; WS_I += 1) {
                    CEP.TRC(SCCGWA, "----YSY------");
                    CEP.TRC(SCCGWA, WS_I);
                    CEP.TRC(SCCGWA, WS_TMP_BR);
                    R000_GET_SUPBR();
                    CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                    TDRLMCT.KEY.BR = BPCPQORG.BRANCH_BR;
                    if (BPCPQORG.BRANCH_BR == WS_TMP_BR) {
                        WS_SAME_FLG = 'Y';
                    } else {
                        T000_READ_SUP_RECORD_BYBR();
                        WS_TMP_BR = BPCPQORG.BRANCH_BR;
                    }
                }
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_SUP_REC_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                }
            } else {
                IBS.init(SCCGWA, TDRLMCT);
                TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
                TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
                TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
                TDRLMCT.LM_LVL = WS_TMP_LVL;
                TDRLMCT.KEY.BR = TDCSLMCT.BR;
                TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
                CEP.TRC(SCCGWA, TDRLMCT.KEY.PROD_CD);
                CEP.TRC(SCCGWA, TDRLMCT.DE_SEQ);
                CEP.TRC(SCCGWA, TDRLMCT.KEY.TERM);
                CEP.TRC(SCCGWA, TDRLMCT.LM_LVL);
                CEP.TRC(SCCGWA, TDRLMCT.KEY.BR);
                CEP.TRC(SCCGWA, TDRLMCT.KEY.CI_LVL);
                T000_READ_SUP_RECORD_BYOTH();
            }
        } else {
            R000_GET_HEADBR_PROC();
        }
        T000_READUPD_TDTLMCT();
        CEP.TRC(SCCGWA, TDRLMCT.AGN_BAL);
        CEP.TRC(SCCGWA, TDRLMCT.AGN_CURR_BAL);
        CEP.TRC(SCCGWA, TDRLMCT.UNAGN_BAL);
        CEP.TRC(SCCGWA, TDRLMCT.UNAGN_CURR_BAL);
        TDRLMCT.AGN_BAL = TDRLMCT.AGN_BAL + TDCSLMCT.TOT_BAL;
        TDRLMCT.AGN_CURR_BAL = TDRLMCT.AGN_CURR_BAL + TDCSLMCT.TOT_BAL;
        TDRLMCT.UNAGN_BAL = TDRLMCT.UNAGN_BAL - TDCSLMCT.TOT_BAL;
        TDRLMCT.UNAGN_CURR_BAL = TDRLMCT.UNAGN_CURR_BAL - TDCSLMCT.TOT_BAL;
        if (TDRLMCT.UNAGN_CURR_BAL < 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_LMT_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
        }
        TDRLMCT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRLMCT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTLMCT();
    }
    public void R000_GET_SUPBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = WS_TMP_BR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
    }
    public void R000_CHECK_SAME_LVL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.STR_DATE = TDCSLMCT.STR_DATE;
        TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
        TDRLMCT.LM_POINT = TDCSLMCT.LM_POINT;
        TDRLMCT.LM_LVL = WS_LM_LVL;
        TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
        CEP.TRC(SCCGWA, TDCSLMCT.PROD_CD);
        CEP.TRC(SCCGWA, TDCSLMCT.STR_DATE);
        CEP.TRC(SCCGWA, TDCSLMCT.TERM);
        CEP.TRC(SCCGWA, TDCSLMCT.LM_POINT);
        CEP.TRC(SCCGWA, WS_LM_LVL);
        CEP.TRC(SCCGWA, TDCSLMCT.DE_SEQ);
        T000_READ_TDTLMCT_SAMELVL();
        if (WS_SAMELVL_FLG == 'Y') {
            CEP.TRC(SCCGWA, "WS-SAMELVL-FOUND1");
            WS_MSGID = TDCMSG_ERROR_MSG.TD_POINT_CANNOT_CROSS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B090_FUNC_DELETE() throws IOException,SQLException,Exception {
        B030_FUNC_INQUIRE();
        if (TDRLMCT.AGN_BAL > 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_SUB_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_LM_LVL = TDRLMCT.LM_LVL;
        T000_READUPD_TDTLMCT();
        T000_DELETE_TDTLMCT();
        if (WS_LM_LVL > 1) {
            CEP.TRC(SCCGWA, WS_LM_LVL);
            CEP.TRC(SCCGWA, TDCSLMCT.DE_SEQ);
            IBS.init(SCCGWA, TDRLMCT);
            TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
            TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
            TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
            WS_TMP_LVL = WS_LM_LVL;
            WS_TMP_LVL = (short) (WS_TMP_LVL - 1);
            TDRLMCT.LM_LVL = WS_TMP_LVL;
            T000_READ_SUP_RECORD_FIRST();
            CEP.TRC(SCCGWA, TDRLMCT.LM_POINT);
            WS_TMP_POINT = TDRLMCT.LM_POINT;
            if (WS_TMP_LVL > 1) {
                if (WS_TMP_POINT == null) WS_TMP_POINT = "";
                JIBS_tmp_int = WS_TMP_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) WS_TMP_POINT += " ";
                if (TDCSLMCT.LM_POINT == null) TDCSLMCT.LM_POINT = "";
                JIBS_tmp_int = TDCSLMCT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDCSLMCT.LM_POINT += " ";
                if (WS_TMP_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                    && TDCSLMCT.LM_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_TMP_BR = TDCSLMCT.BR;
                    WS_SAME_FLG = 'N';
                    IBS.init(SCCGWA, TDRLMCT);
                    TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                    TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
                    TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
                    TDRLMCT.LM_LVL = WS_TMP_LVL;
                    TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
                    TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
                    for (WS_I = 1; WS_I <= 4 
                        && WS_SAME_FLG != 'Y'; WS_I += 1) {
                        CEP.TRC(SCCGWA, "----YSY------");
                        CEP.TRC(SCCGWA, WS_I);
                        CEP.TRC(SCCGWA, WS_TMP_BR);
                        R000_GET_SUPBR();
                        TDRLMCT.KEY.BR = BPCPQORG.BRANCH_BR;
                        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                        if (BPCPQORG.BRANCH_BR == WS_TMP_BR) {
                            WS_SAME_FLG = 'Y';
                        } else {
                            T000_READ_SUP_RECORD_BYBR();
                            WS_TMP_BR = BPCPQORG.BRANCH_BR;
                        }
                    }
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_SUP_REC_NOT_EXIST;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    IBS.init(SCCGWA, TDRLMCT);
                    TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                    TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
                    TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
                    TDRLMCT.LM_LVL = WS_TMP_LVL;
                    TDRLMCT.KEY.BR = TDCSLMCT.BR;
                    TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
                    TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
                    T000_READ_SUP_RECORD_BYOTH();
                }
            } else {
                R000_GET_HEADBR_PROC();
            }
            T000_READUPD_TDTLMCT();
            TDRLMCT.AGN_BAL = TDRLMCT.AGN_BAL - TDCSLMCT.TOT_BAL;
            TDRLMCT.AGN_CURR_BAL = TDRLMCT.AGN_CURR_BAL - TDCSLMCT.TOT_BAL;
            TDRLMCT.UNAGN_BAL = TDRLMCT.UNAGN_BAL + TDCSLMCT.TOT_BAL;
            TDRLMCT.UNAGN_CURR_BAL = TDRLMCT.UNAGN_CURR_BAL + TDCSLMCT.TOT_BAL;
            TDRLMCT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRLMCT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTLMCT();
        }
    }
    public void B110_FUNC_MODIFY() throws IOException,SQLException,Exception {
        B030_FUNC_INQUIRE();
        WS_LMCT_TOT_BAL = TDRLMCT.TOT_BAL;
        WS_LMCT_TOT_BAL = TDCSLMCT.TOT_BAL - WS_LMCT_TOT_BAL;
        WS_TMP_LVL = TDRLMCT.LM_LVL;
        CEP.TRC(SCCGWA, WS_LMCT_TOT_BAL);
        CEP.TRC(SCCGWA, TDRLMCT.LM_LVL);
        CEP.TRC(SCCGWA, TDCSLMCT.DE_SEQ);
        T000_READUPD_TDTLMCT();
        if (TDCSLMCT.TOT_BAL == TDRLMCT.TOT_BAL) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_LMT_NOT_MODIFY;
            S000_ERR_MSG_PROC();
        }
        TDRLMCT.TOT_BAL = TDCSLMCT.TOT_BAL;
        TDRLMCT.UNAGN_BAL = TDRLMCT.UNAGN_BAL + WS_LMCT_TOT_BAL;
        TDRLMCT.UNAGN_CURR_BAL = TDRLMCT.UNAGN_CURR_BAL + WS_LMCT_TOT_BAL;
        if (TDRLMCT.UNAGN_CURR_BAL < 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_LMT_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
        }
        TDRLMCT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRLMCT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTLMCT();
        if (WS_TMP_LVL > 1) {
            IBS.init(SCCGWA, TDRLMCT);
            TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
            TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
            TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
            WS_TMP_LVL = (short) (WS_TMP_LVL - 1);
            TDRLMCT.LM_LVL = WS_TMP_LVL;
            CEP.TRC(SCCGWA, WS_TMP_LVL);
            CEP.TRC(SCCGWA, TDRLMCT.KEY.PROD_CD);
            CEP.TRC(SCCGWA, TDRLMCT.DE_SEQ);
            CEP.TRC(SCCGWA, TDRLMCT.KEY.TERM);
            T000_READ_SUP_RECORD_FIRST();
            CEP.TRC(SCCGWA, TDRLMCT.LM_POINT);
            WS_TMP_POINT = TDRLMCT.LM_POINT;
            if (WS_TMP_LVL > 1) {
                if (WS_TMP_POINT == null) WS_TMP_POINT = "";
                JIBS_tmp_int = WS_TMP_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) WS_TMP_POINT += " ";
                if (TDCSLMCT.LM_POINT == null) TDCSLMCT.LM_POINT = "";
                JIBS_tmp_int = TDCSLMCT.LM_POINT.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) TDCSLMCT.LM_POINT += " ";
                if (WS_TMP_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                    && TDCSLMCT.LM_POINT.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_TMP_BR = TDCSLMCT.BR;
                    WS_SAME_FLG = 'N';
                    IBS.init(SCCGWA, TDRLMCT);
                    TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                    TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
                    TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
                    TDRLMCT.LM_LVL = WS_TMP_LVL;
                    TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
                    TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
                    for (WS_I = 1; WS_I <= 4; WS_I += 1) {
                        CEP.TRC(SCCGWA, "----YSY------");
                        CEP.TRC(SCCGWA, WS_I);
                        CEP.TRC(SCCGWA, WS_TMP_BR);
                        R000_GET_SUPBR();
                        TDRLMCT.KEY.BR = BPCPQORG.BRANCH_BR;
                        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                        if (BPCPQORG.BRANCH_BR == WS_TMP_BR) {
                            WS_SAME_FLG = 'Y';
                        } else {
                            T000_READ_SUP_RECORD_BYBR();
                            WS_TMP_BR = BPCPQORG.BRANCH_BR;
                        }
                    }
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_SUP_REC_NOT_EXIST;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    IBS.init(SCCGWA, TDRLMCT);
                    TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
                    TDRLMCT.DE_SEQ = TDCSLMCT.DE_SEQ;
                    TDRLMCT.KEY.TERM = TDCSLMCT.TERM;
                    TDRLMCT.LM_LVL = WS_TMP_LVL;
                    TDRLMCT.KEY.BR = TDCSLMCT.BR;
                    TDRLMCT.KEY.CI_LVL = TDCSLMCT.CI_LVL;
                    TDRLMCT.KEY.CHNL_NO = TDCSLMCT.CHNL_NO;
                    T000_READ_SUP_RECORD_BYOTH();
                }
            } else {
                R000_GET_HEADBR_PROC();
            }
            T000_READUPD_TDTLMCT();
            TDRLMCT.AGN_BAL = TDRLMCT.AGN_BAL + WS_LMCT_TOT_BAL;
            TDRLMCT.AGN_CURR_BAL = TDRLMCT.AGN_CURR_BAL + WS_LMCT_TOT_BAL;
            TDRLMCT.UNAGN_BAL = TDRLMCT.UNAGN_BAL - WS_LMCT_TOT_BAL;
            TDRLMCT.UNAGN_CURR_BAL = TDRLMCT.UNAGN_CURR_BAL - WS_LMCT_TOT_BAL;
            if (TDRLMCT.UNAGN_CURR_BAL < 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_LMT_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
            }
            TDRLMCT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRLMCT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTLMCT();
        }
    }
    public void B130_FUNC_INQ_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMCT);
        TDRLMCT.KEY.PROD_CD = TDCSLMCT.PROD_CD;
        TDRLMCT.KEY.BR = K_HEAD_BR;
        TDRLMCT.DE_SEQ = TDRLMCT.DE_SEQ;
        T000_READ_DATE_BYSEQ();
    }
    public void B200_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOLMCT);
        TDCOLMCT.PROD_CD = TDRLMCT.KEY.PROD_CD;
        TDCOLMCT.STR_DATE = TDRLMCT.KEY.STR_DATE;
        TDCOLMCT.END_DATE = TDRLMCT.KEY.END_DATE;
        TDCOLMCT.TERM = TDRLMCT.KEY.TERM;
        TDCOLMCT.BR = TDRLMCT.KEY.BR;
        TDCOLMCT.CHNL_NO = TDRLMCT.KEY.CHNL_NO;
        TDCOLMCT.CI_LVL = TDRLMCT.KEY.CI_LVL;
        TDCOLMCT.LM_POINT = TDRLMCT.LM_POINT;
        TDCOLMCT.TOT_BAL = TDRLMCT.TOT_BAL;
        TDCOLMCT.AGN_BAL = TDRLMCT.AGN_BAL;
        TDCOLMCT.AGN_USE_BAL = TDRLMCT.AGN_USE_BAL;
        TDCOLMCT.AGN_CURR_BAL = TDRLMCT.AGN_CURR_BAL;
        TDCOLMCT.UNAGN_BAL = TDRLMCT.UNAGN_BAL;
        TDCOLMCT.UNAGN_USE_BAL = TDRLMCT.UNAGN_USE_BAL;
        TDCOLMCT.UNAGN_CURR_BAL = TDRLMCT.UNAGN_CURR_BAL;
        TDCOLMCT.DE_SEQ = TDRLMCT.DE_SEQ;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_FMT;
        SCCFMT.DATA_PTR = TDCOLMCT;
        SCCFMT.DATA_LEN = 193;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B200_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_BRW_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 3692;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_SUP_RECORD_FIRST() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND TERM = :TDRLMCT.KEY.TERM "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ "
            + "AND LM_LVL = :TDRLMCT.LM_LVL";
        TDTLMCT_RD.fst = true;
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_SUP_REC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_SUP_RECORD_BYOTH() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ "
            + "AND ( BR = :TDRLMCT.KEY.BR "
            + "OR BR = :WS_HEADBR ) "
            + "AND ( CHNL_NO = :TDRLMCT.KEY.CHNL_NO "
            + "OR CHNL_NO = ' ' ) "
            + "AND ( CI_LVL = :TDRLMCT.KEY.CI_LVL "
            + "OR CI_LVL = ' ' ) "
            + "AND LM_LVL = :TDRLMCT.LM_LVL "
            + "AND ( TERM = :TDRLMCT.KEY.TERM "
            + "OR TERM = ' ' )";
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_SUP_REC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_SUP_RECORD_BYBR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRLMCT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMCT.DE_SEQ);
        CEP.TRC(SCCGWA, TDRLMCT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMCT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, TDRLMCT.KEY.CI_LVL);
        CEP.TRC(SCCGWA, TDRLMCT.KEY.TERM);
        CEP.TRC(SCCGWA, TDRLMCT.LM_LVL);
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ "
            + "AND BR = :TDRLMCT.KEY.BR "
            + "AND CHNL_NO = :TDRLMCT.KEY.CHNL_NO "
            + "AND CI_LVL = :TDRLMCT.KEY.CI_LVL "
            + "AND LM_LVL = :TDRLMCT.LM_LVL "
            + "AND ( TERM = :TDRLMCT.KEY.TERM "
            + "OR TERM = ' ' )";
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_WRITE_TDTLMCT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        IBS.WRITE(SCCGWA, TDRLMCT, TDTLMCT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTLMCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTLMCT_SAMELVL() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND STR_DATE = :TDRLMCT.KEY.STR_DATE "
            + "AND TERM = :TDRLMCT.KEY.TERM "
            + "AND LM_POINT < > :TDRLMCT.LM_POINT "
            + "AND LM_LVL = :TDRLMCT.LM_LVL "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ";
        TDTLMCT_RD.fst = true;
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SAMELVL_FLG = 'Y';
        } else {
            WS_SAMELVL_FLG = 'N';
        }
    }
    public void T000_COUNT_TDTLMCT_BYDT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.set = "WS-LMCT-CNT=COUNT(*)";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND STR_DATE <= :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE >= :TDRLMCT.KEY.STR_DATE";
        IBS.GROUP(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
    }
    public void T000_COUNT_TDTLMCT_BYSEQ() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.set = "WS-LMCT-CNT=COUNT(*)";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ";
        IBS.GROUP(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
    }
    public void T000_READ_BLANK_TERM() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND TERM < > ' ' "
            + "AND STR_DATE <= :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE >= :TDRLMCT.KEY.STR_DATE "
            + "AND BR = :TDRLMCT.KEY.BR "
            + "AND LM_LVL = :TDRLMCT.LM_LVL";
        TDTLMCT_RD.fst = true;
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_DE_BLANK_TERM_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTLMCT_BYBR() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND ( TERM = :TDRLMCT.KEY.TERM "
            + "OR TERM = ' ' ) "
            + "AND STR_DATE <= :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE >= :TDRLMCT.KEY.STR_DATE "
            + "AND BR = :TDRLMCT.KEY.BR "
            + "AND LM_LVL = :TDRLMCT.LM_LVL";
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HEADBR_FLG = 'Y';
        } else {
            WS_HEADBR_FLG = 'N';
        }
    }
    public void T000_CHECK_TDTLMCT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND STR_DATE = :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE = :TDRLMCT.KEY.END_DATE "
            + "AND TERM = :TDRLMCT.KEY.TERM "
            + "AND BR = :TDRLMCT.KEY.BR "
            + "AND CHNL_NO = :TDRLMCT.KEY.CHNL_NO "
            + "AND CI_LVL = :TDRLMCT.KEY.CI_LVL";
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_LIMIT_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTLMCT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND STR_DATE = :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE = :TDRLMCT.KEY.END_DATE "
            + "AND TERM = :TDRLMCT.KEY.TERM "
            + "AND BR = :TDRLMCT.KEY.BR "
            + "AND CHNL_NO = :TDRLMCT.KEY.CHNL_NO "
            + "AND CI_LVL = :TDRLMCT.KEY.CI_LVL";
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DATE_BYSEQ() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND BR = :TDRLMCT.KEY.BR "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ";
        IBS.READ(SCCGWA, TDRLMCT, this, TDTLMCT_RD);
    }
    public void T000_STARTBR_TDTLMCT_BYDT() throws IOException,SQLException,Exception {
        TDTLMCT_BR.rp = new DBParm();
        TDTLMCT_BR.rp.TableName = "TDTLMCT";
        TDTLMCT_BR.rp.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND STR_DATE <= :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE >= :TDRLMCT.KEY.STR_DATE";
        TDTLMCT_BR.rp.order = "STR_DATE,LM_LVL";
        IBS.STARTBR(SCCGWA, TDRLMCT, this, TDTLMCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_TDTLMCT_BYSEQ() throws IOException,SQLException,Exception {
        TDTLMCT_BR.rp = new DBParm();
        TDTLMCT_BR.rp.TableName = "TDTLMCT";
        TDTLMCT_BR.rp.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND DE_SEQ = :TDRLMCT.DE_SEQ";
        TDTLMCT_BR.rp.order = "DE_SEQ DESC";
        IBS.STARTBR(SCCGWA, TDRLMCT, this, TDTLMCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_TDTLMCT() throws IOException,SQLException,Exception {
        WS_LMCT_FLG = 'N';
        TDTLMCT_BR.rp = new DBParm();
        TDTLMCT_BR.rp.TableName = "TDTLMCT";
        TDTLMCT_BR.rp.where = "PROD_CD = :TDRLMCT.KEY.PROD_CD "
            + "AND STR_DATE <= :TDRLMCT.KEY.STR_DATE "
            + "AND END_DATE >= :TDRLMCT.KEY.END_DATE";
        IBS.STARTBR(SCCGWA, TDRLMCT, this, TDTLMCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READNEXT_TDTLMCT_STR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRLMCT, this, TDTLMCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMCT_FLG = 'Y';
        } else {
            WS_LMCT_FLG = 'N';
        }
    }
    public void T000_READNEXT_TDTLMCT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRLMCT, this, TDTLMCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMCT_FLG = 'Y';
        } else {
            WS_LMCT_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTLMCT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTLMCT_BR);
    }
    public void T000_READUPD_TDTLMCT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        TDTLMCT_RD.upd = true;
        IBS.READ(SCCGWA, TDRLMCT, TDTLMCT_RD);
    }
    public void T000_REWRITE_TDTLMCT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        IBS.REWRITE(SCCGWA, TDRLMCT, TDTLMCT_RD);
    }
    public void T000_DELETE_TDTLMCT() throws IOException,SQLException,Exception {
        TDTLMCT_RD = new DBParm();
        TDTLMCT_RD.TableName = "TDTLMCT";
        IBS.DELETE(SCCGWA, TDRLMCT, TDTLMCT_RD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
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
