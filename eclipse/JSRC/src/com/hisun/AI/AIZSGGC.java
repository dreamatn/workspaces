package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSGGC {
    AICOGGC_DATA DATA;
    brParm AITGINF_BR = new brParm();
    DBParm AITGINF_RD;
    brParm AITGRVS_BR = new brParm();
    brParm AITCRVS_BR = new brParm();
    DBParm AITGRVS_RD;
    DBParm AITCRVS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_RCD_SEQ = 0;
    int WS_RD_NUM = 0;
    short WS_RECORD_NUM = 0;
    short WS_RECORD = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_CNT2 = 0;
    int WS_CNT3 = 0;
    int WS_CNT4 = 0;
    int WS_CNT5 = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_SUS_TERM = 0;
    String WS_RVS_NO_X = " ";
    AIZSGGC_REDEFINES16 REDEFINES16 = new AIZSGGC_REDEFINES16();
    AIZSGGC_WS_RVS_INFO WS_RVS_INFO = new AIZSGGC_WS_RVS_INFO();
    int WS_SUS_DT = 0;
    char WS_SUS_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AICRGRVS AICRGRVS = new AICRGRVS();
    AIRCRVS AIRCRVS = new AIRCRVS();
    AICRCRVS AICRCRVS = new AICRCRVS();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICCUST CICCUST = new CICCUST();
    AICOGGC AICOGGC = new AICOGGC();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    int WS_COUNT_NO_G = 0;
    int WS_COUNT_NO_C = 0;
    double WS_DB_STR_AMT = 0;
    double WS_DB_END_AMT = 0;
    int WS_DB_STR_DATE = 0;
    int WS_DB_END_DATE = 0;
    String WS_DB_THEIR_AC = " ";
    int WS_DB_EXP_STDT = 0;
    int WS_DB_EXP_ENDT = 0;
    int WS_DB_SUS_STDT = 0;
    int WS_DB_SUS_ENDT = 0;
    int WS_SUS_DATE = 0;
    char WS_STS = ' ';
    char WS_GINF_FLG = ' ';
    char WS_GRVS_FLG = ' ';
    char WS_CRVS_FLG = ' ';
    char WS_END_FLG = ' ';
    SCCGWA SCCGWA;
    AICSGGC AICSGGC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, AICSGGC AICSGGC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSGGC = AICSGGC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSGGC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRGINF);
        IBS.init(SCCGWA, AICRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSGGC.FUNC == 'Q') {
            B020_BROWSE_GINF_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_GINF_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 804;
        SCCMPAG.SCR_ROW_CNT = 15;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRGINF);
        AIRGINF.BR = AICSGGC.BR;
        AIRGINF.ITM = AICSGGC.ITM;
        AIRGINF.SEQ = AICSGGC.SEQ;
        AIRGINF.CCY = AICSGGC.CCY;
        AIRGINF.KEY.RVS_NO = AICSGGC.RVS_NO;
        AIRGINF.STS = AICSGGC.STS;
        if (AICSGGC.STS == 'N') {
            WS_STS = 'P';
        } else {
            WS_STS = AICSGGC.STS;
        }
        WS_DB_EXP_STDT = AICSGGC.EXP_STDT;
        WS_DB_EXP_ENDT = AICSGGC.EXP_ENDT;
        WS_DB_SUS_STDT = AICSGGC.SUS_STDT;
        WS_DB_SUS_ENDT = AICSGGC.SUS_ENDT;
        WS_DB_STR_AMT = AICSGGC.STR_AMT;
        WS_DB_END_AMT = AICSGGC.END_AMT;
        CEP.TRC(SCCGWA, "LBY----------GINF1");
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.CCY);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        CEP.TRC(SCCGWA, WS_DB_EXP_STDT);
        CEP.TRC(SCCGWA, WS_DB_EXP_ENDT);
        CEP.TRC(SCCGWA, WS_DB_SUS_STDT);
        CEP.TRC(SCCGWA, WS_DB_SUS_ENDT);
        CEP.TRC(SCCGWA, AICSGGC.SUS_TERM);
        if (AICSGGC.SUS_TERM != 1 
            && AICSGGC.SUS_TERM != 0) {
            WS_SUS_TERM = 1 - AICSGGC.SUS_TERM;
            CEP.TRC(SCCGWA, WS_SUS_TERM);
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DAYS = WS_SUS_TERM;
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_SUS_DATE = SCCCLDT.DATE2;
        } else {
            WS_SUS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (AICSGGC.RVS_NO.trim().length() == 0 
            && (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSGGC.RVS_NO.trim().length() == 0) {
            T000_STARTBR_AITGINF_NO_RVS();
            if (pgmRtn) return;
            T000_READNEXT_AITGINF();
            if (pgmRtn) return;
            while (WS_GINF_FLG != 'N' 
                && SCCMPAG.FUNC != 'E' 
                && WS_END_FLG != 'Y') {
                WS_CNT3 = 0;
                WS_CNT4 = 0;
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B020_10_BROWSE_GRVS_PROCESS();
                    if (pgmRtn) return;
                    B020_10_BROWSE_CRVS_PROCESS();
                    if (pgmRtn) return;
                } else {
                    B020_20_BROWSE_GRVS_PROCESS();
                    if (pgmRtn) return;
                }
                T000_READNEXT_AITGINF();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITGINF();
            if (pgmRtn) return;
        } else {
            TOOO_READ_AITGINF_BY_RVS();
            if (pgmRtn) return;
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                B020_10_BROWSE_GRVS_PROCESS();
                if (pgmRtn) return;
                B020_10_BROWSE_CRVS_PROCESS();
                if (pgmRtn) return;
            } else {
                B020_20_BROWSE_GRVS_PROCESS();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_CNT2);
        CEP.TRC(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.OUTP_LEN);
    }
    public void S000_CHE_INPUT_FOR_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HELLO");
        WS_SUS_FLG = 'N';
        CEP.TRC(SCCGWA, AIRGINF.LST_G_DT);
        WS_SUS_DT = SCCGWA.COMM_AREA.AC_DATE - AIRGINF.LST_G_DT + 1;
        CEP.TRC(SCCGWA, WS_SUS_DT);
        CEP.TRC(SCCGWA, AICSGGC.SUS_TERM);
        CEP.TRC(SCCGWA, WS_CNT5);
        CEP.TRC(SCCGWA, AICSGGC.RCD_SEQ);
        if ((WS_SUS_DT > AICSGGC.SUS_TERM 
            || WS_SUS_DT == AICSGGC.SUS_TERM) 
            && WS_CNT5 >= AICSGGC.RCD_SEQ) {
            WS_SUS_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_SUS_FLG);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSGGC.BR == 0 
            && AICSGGC.RVS_NO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSGGC.CCY.equalsIgnoreCase("999")) {
            AICSGGC.CCY = "   ";
        }
        if (AICSGGC.SUS_STDT == 0) {
            AICSGGC.SUS_STDT = 0;
        }
        if (AICSGGC.SUS_STDT == 0 
            && AICSGGC.SUS_ENDT == 0) {
            AICSGGC.SUS_ENDT = 99991231;
        }
        if (AICSGGC.SUS_STDT != 0 
            && AICSGGC.SUS_ENDT == 0) {
            AICSGGC.SUS_ENDT = 99991231;
        }
        if (AICSGGC.EXP_STDT == 0) {
            AICSGGC.EXP_STDT = 0;
        }
        CEP.TRC(SCCGWA, AICSGGC.SUS_STDT);
        CEP.TRC(SCCGWA, AICSGGC.SUS_ENDT);
        if (AICSGGC.EXP_STDT == 0 
            && AICSGGC.EXP_ENDT == 0) {
            AICSGGC.EXP_ENDT = 99991231;
        }
        if (AICSGGC.EXP_STDT != 0 
            && AICSGGC.EXP_ENDT == 0) {
            AICSGGC.EXP_ENDT = AICSGGC.EXP_STDT;
        }
        CEP.TRC(SCCGWA, "FUCK");
        CEP.TRC(SCCGWA, AICSGGC.EXP_ENDT);
        if (AICSGGC.STR_AMT == 0) {
            AICSGGC.STR_AMT = 0;
        }
        if (AICSGGC.STR_AMT == 0 
            && AICSGGC.END_AMT == 0) {
            AICSGGC.END_AMT = 99999999999999999;
        }
        if (AICSGGC.STR_AMT != 0 
            && AICSGGC.END_AMT == 0) {
            AICSGGC.END_AMT = AICSGGC.STR_AMT;
        }
        CEP.TRC(SCCGWA, AICSGGC.EXP_STDT);
        CEP.TRC(SCCGWA, AICSGGC.EXP_ENDT);
        CEP.TRC(SCCGWA, AICSGGC.STR_AMT);
        CEP.TRC(SCCGWA, AICSGGC.END_AMT);
    }
    public void B020_10_BROWSE_GRVS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, WS_RVS_INFO);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        AIRGRVS.KEY.RVS_NO = AIRGINF.KEY.RVS_NO;
        WS_GRVS_FLG = ' ';
        WS_I = 0;
        for (WS_I = 1; WS_END_FLG != 'Y' 
            && WS_CNT3 != AIRGINF.G_MAX_NO; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_CNT3);
            CEP.TRC(SCCGWA, AIRGINF.G_MAX_NO);
            CEP.TRC(SCCGWA, WS_I);
            AIRGRVS.KEY.RVS_SEQ = WS_I;
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
            T000_READ_AITGRVS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_GRVS_FLG);
            if (WS_GRVS_FLG == 'Y') {
                WS_RVS_INFO.WS_O_FLG = '1';
                WS_RVS_INFO.WS_O_TX_DT = AIRGRVS.TX_DT;
                WS_RVS_INFO.WS_O_BR = AIRGRVS.BR;
                WS_RVS_INFO.WS_O_RVS_NO = AIRGRVS.KEY.RVS_NO;
                WS_RVS_INFO.WS_O_RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
                WS_RVS_INFO.WS_O_AC = AIRGRVS.AC;
                WS_RVS_INFO.WS_O_CCY = AIRGRVS.CCY;
                WS_RVS_INFO.WS_O_AMT = AIRGRVS.AMT;
                WS_RVS_INFO.WS_O_STS = AIRGRVS.STS;
                WS_RVS_INFO.WS_O_THEIR_AC = AIRGRVS.THEIR_AC;
                WS_RVS_INFO.WS_O_CI_NO = AIRGRVS.CI_NO;
                WS_RVS_INFO.WS_O_TR_BR = AIRGRVS.TR_BR;
                WS_RVS_INFO.WS_O_TR_TELLER = AIRGRVS.TR_TELLER;
                WS_RVS_INFO.WS_O_SET_NO = AIRGRVS.SET_NO;
                WS_RVS_INFO.WS_O_SET_SEQ = AIRGRVS.SET_SEQ;
                WS_RVS_INFO.WS_O_PART = AIRGRVS.PART;
                WS_RVS_INFO.WS_O_PAY_MAN = AIRGRVS.PAY_MAN;
                WS_RVS_INFO.WS_O_PAY_BR = AIRGRVS.PAY_BR;
                CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
                CEP.TRC(SCCGWA, AIRGRVS.TX_DT);
                CEP.TRC(SCCGWA, AIRGRVS.BR);
                CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
                CEP.TRC(SCCGWA, AIRGRVS.AC);
                CEP.TRC(SCCGWA, AIRGRVS.CCY);
                CEP.TRC(SCCGWA, AIRGRVS.AMT);
                CEP.TRC(SCCGWA, AIRGRVS.TR_BR);
                CEP.TRC(SCCGWA, AIRGRVS.TR_TELLER);
                CEP.TRC(SCCGWA, AIRGRVS.CI_NO);
                if ((AIRGRVS.CI_NO.trim().length() == 0)) {
                    WS_RVS_INFO.WS_O_CI_CNMS = " ";
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
                    CICCUST.FUNC = 'C';
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    WS_RVS_INFO.WS_O_CI_CNMS = CICCUST.O_DATA.O_CI_NM;
                }
                R000_OUTPUT_RVS_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_20_BROWSE_GRVS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, WS_RVS_INFO);
        AIRGRVS.KEY.RVS_NO = AIRGINF.KEY.RVS_NO;
        AIRCRVS.KEY.RVS_NO = AIRGINF.KEY.RVS_NO;
        AICRGRVS.INFO.STR_AMT = AICSGGC.STR_AMT;
        AICRGRVS.INFO.END_AMT = AICSGGC.END_AMT;
        CEP.TRC(SCCGWA, AICSGGC.RVS_NO);
        CEP.TRC(SCCGWA, AICSGGC.THEIR_AC);
        CEP.TRC(SCCGWA, AICRGRVS.RETURN_INFO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        CEP.TRC(SCCGWA, AICSGGC.RCD_SEQ);
        CEP.TRC(SCCGWA, K_MAX_ROW);
        if (AICSGGC.PAGE_ROW > K_MAX_ROW 
            || AICSGGC.PAGE_ROW == 0) {
            AICSGGC.PAGE_ROW = K_MAX_ROW;
        }
        if (AICSGGC.PAGE_NUM > 50) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.PAG_OUT_CAN_NOT_PAS_50;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSGGC.PAGE_NUM > 0) {
            AICSGGC.RCD_SEQ = ( AICSGGC.PAGE_NUM - 1 ) * AICSGGC.PAGE_ROW + 1;
        } else {
            AICSGGC.RCD_SEQ = 1;
        }
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        B020_03_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        WS_RD_NUM = 0;
        CEP.TRC(SCCGWA, WS_RD_NUM);
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        CEP.TRC(SCCGWA, AIRGINF.G_MAX_NO);
        CEP.TRC(SCCGWA, AIRGINF.C_MAX_NO);
        CEP.TRC(SCCGWA, AICOGGC.OUTPUT_TITLE.CURR_PAGE_ROW);
        AICOGGC.OUTPUT_TITLE.CURR_PAGE_ROW = K_MAX_ROW;
        DATA = new AICOGGC_DATA();
        AICOGGC.DATA.add(DATA);
        for (WS_I = 1; WS_GRVS_FLG != 'N' 
            && WS_RD_NUM <= AICSGGC.PAGE_ROW 
            && WS_CNT3 != AIRGINF.G_MAX_NO; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_CNT3);
            CEP.TRC(SCCGWA, AIRGINF.G_MAX_NO);
            CEP.TRC(SCCGWA, WS_I);
            AIRGRVS.KEY.RVS_SEQ = WS_I;
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
            T000_READ_AITGRVS();
            if (pgmRtn) return;
            S000_CHE_INPUT_FOR_OUTPUT();
            if (pgmRtn) return;
            if (WS_SUS_FLG == 'Y' 
                && WS_RD_NUM < AICSGGC.PAGE_ROW) {
                AICOGGC.OUTPUT_TITLE.LAST_FLG = 'N';
                B020_04_OUT_PAGE_DATA();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "TESTA");
            CEP.TRC(SCCGWA, AICOGGC.OUTPUT_TITLE.LAST_FLG);
        }
        CEP.TRC(SCCGWA, WS_RD_NUM);
        B020_20_BROWSE_CRVS_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICOGGC.OUTPUT_TITLE.LAST_FLG);
        if (AICSGGC.PAGE_NUM == 0) {
            AICOGGC.OUTPUT_TITLE.CURR_PAGE = 1;
        } else {
            AICOGGC.OUTPUT_TITLE.CURR_PAGE = AICSGGC.PAGE_NUM;
        }
        AICOGGC.OUTPUT_TITLE.CURR_PAGE_ROW = WS_RD_NUM;
        DATA = new AICOGGC_DATA();
        AICOGGC.DATA.add(DATA);
        CEP.TRC(SCCGWA, WS_RD_NUM);
        CEP.TRC(SCCGWA, AICOGGC.OUTPUT_TITLE.CURR_PAGE_ROW);
        WS_RECORD = (short) (AICOGGC.OUTPUT_TITLE.CURR_PAGE * AICOGGC.OUTPUT_TITLE.CURR_PAGE_ROW);
        if (WS_CNT3 < AIRGINF.G_MAX_NO 
            || WS_CNT4 < AIRGINF.C_MAX_NO) {
            AICOGGC.OUTPUT_TITLE.LAST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_RECORD);
        CEP.TRC(SCCGWA, AICOGGC.TOTAL_DATA.TOTAL_NUM);
        if (WS_RD_NUM < AICSGGC.PAGE_ROW 
            || (WS_CNT4 == AIRGINF.C_MAX_NO 
            && AICOGGC.OUTPUT_TITLE.LAST_FLG != 'N') 
            || (WS_CNT3 == AIRGINF.G_MAX_NO 
            && AICOGGC.OUTPUT_TITLE.LAST_FLG != 'N') 
            || WS_RECORD == AICOGGC.TOTAL_DATA.TOTAL_NUM) {
            AICOGGC.OUTPUT_TITLE.LAST_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, AICOGGC.OUTPUT_TITLE.LAST_FLG);
        CEP.TRC(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.OUTP_LEN);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            CEP.TRC(SCCGWA, "FUCK11111");
            CEP.TRC(SCCGWA, AICOGGC);
            SCCFMT.FMTID = "AIX01";
            SCCFMT.DATA_PTR = AICOGGC;
            SCCFMT.DATA_LEN = 9307;
            CEP.TRC(SCCGWA, "TEST0011");
            CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
            IBS.FMT(SCCGWA, SCCFMT);
        }
        CEP.TRC(SCCGWA, "LBY=========2");
        CEP.TRC(SCCGWA, AICRGRVS.RETURN_INFO);
    }
    public void B020_20_BROWSE_CRVS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, WS_RVS_INFO);
        AIRCRVS.KEY.RVS_NO = AIRGINF.KEY.RVS_NO;
        AICRCRVS.INFO.STR_AMT = AICSGGC.STR_AMT;
        AICRCRVS.INFO.END_AMT = AICSGGC.END_AMT;
        CEP.TRC(SCCGWA, AICSGGC.RVS_NO);
        CEP.TRC(SCCGWA, AICSGGC.THEIR_AC);
        CEP.TRC(SCCGWA, AICRGRVS.RETURN_INFO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        CEP.TRC(SCCGWA, K_MAX_ROW);
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RD_NUM);
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        for (WS_I = 1; WS_CRVS_FLG != 'N' 
            && WS_RD_NUM <= AICSGGC.PAGE_ROW 
            && WS_CNT4 != AIRGINF.C_MAX_NO; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_CNT4);
            CEP.TRC(SCCGWA, AIRGINF.C_MAX_NO);
            CEP.TRC(SCCGWA, WS_I);
            AIRCRVS.KEY.RVS_SEQ = WS_I;
            CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_SEQ);
            T000_READ_AITCRVS();
            if (pgmRtn) return;
            S000_CHE_INPUT_FOR_OUTPUT();
            if (pgmRtn) return;
            if (WS_SUS_FLG == 'Y' 
                && WS_RD_NUM < AICSGGC.PAGE_ROW) {
                CEP.TRC(SCCGWA, WS_RD_NUM);
                AICOGGC.OUTPUT_TITLE.LAST_FLG = 'N';
                B020_05_OUT_PAGE_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_10_BROWSE_CRVS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, WS_RVS_INFO);
        AIRCRVS.KEY.RVS_NO = AIRGINF.KEY.RVS_NO;
        WS_CRVS_FLG = ' ';
        WS_J = 0;
        for (WS_J = 1; WS_END_FLG != 'Y' 
            && WS_CNT4 != AIRGINF.C_MAX_NO; WS_J += 1) {
            CEP.TRC(SCCGWA, WS_CNT4);
            CEP.TRC(SCCGWA, AIRGINF.C_MAX_NO);
            CEP.TRC(SCCGWA, WS_J);
            AIRCRVS.KEY.RVS_SEQ = WS_J;
            T000_READ_AITCRVS();
            if (pgmRtn) return;
            if (WS_CRVS_FLG == 'Y') {
                WS_RVS_INFO.WS_O_FLG = '2';
                WS_RVS_INFO.WS_O_TX_DT = AIRCRVS.TX_DT;
                WS_RVS_INFO.WS_O_BR = AIRCRVS.BR;
                WS_RVS_INFO.WS_O_RVS_NO = AIRCRVS.KEY.RVS_NO;
                WS_RVS_INFO.WS_O_RVS_SEQ = AIRCRVS.KEY.RVS_SEQ;
                WS_RVS_INFO.WS_O_AC = AIRCRVS.AC;
                WS_RVS_INFO.WS_O_CCY = AIRCRVS.CCY;
                WS_RVS_INFO.WS_O_AMT = AIRCRVS.AMT;
                WS_RVS_INFO.WS_O_THEIR_AC = AIRCRVS.THEIR_AC;
                WS_RVS_INFO.WS_O_STS = AIRCRVS.STS;
                WS_RVS_INFO.WS_O_CI_NO = AIRCRVS.CI_NO;
                WS_RVS_INFO.WS_O_TR_BR = AIRCRVS.TR_BR;
                WS_RVS_INFO.WS_O_TR_TELLER = AIRCRVS.TR_TELLER;
                WS_RVS_INFO.WS_O_SET_NO = AIRCRVS.SET_NO;
                WS_RVS_INFO.WS_O_SET_SEQ = AIRCRVS.SET_SEQ;
                WS_RVS_INFO.WS_O_PART = AIRCRVS.PART;
                WS_RVS_INFO.WS_O_PAY_MAN = AIRCRVS.PAY_MAN;
                WS_RVS_INFO.WS_O_PAY_BR = AIRCRVS.PAY_BR;
                if ((AIRCRVS.CI_NO.trim().length() == 0)) {
                    WS_RVS_INFO.WS_O_CI_CNMS = " ";
                } else {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.DATA.CI_NO = AIRCRVS.CI_NO;
                    CICCUST.FUNC = 'C';
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    WS_RVS_INFO.WS_O_CI_CNMS = CICCUST.O_DATA.O_CI_NM;
                }
                R000_OUTPUT_RVS_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_BR() throws IOException,SQLException,Exception {
        REDEFINES16.WS_RVS_BR = AIRGINF.BR;
        WS_RVS_NO_X = IBS.CLS2CPY(SCCGWA, REDEFINES16);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != REDEFINES16.WS_RVS_BR) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            if (BPCPQORG.LVL == '2') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.THIS_TLT_CAN_NOT_INQ);
            } else if (BPCPQORG.LVL == '4') {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPRGST.BR1 = BPCPQORG.BR;
                BPCPRGST.BR2 = REDEFINES16.WS_RVS_BR;
                S000_CALL_BPZPRGST();
                if (pgmRtn) return;
                if (BPCPRGST.FLAG == 'Y' 
                    && (BPCPRGST.LVL_RELATION == 'A' 
                    || BPCPRGST.LVL_RELATION == 'B')) {
                } else {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.THIS_TLT_CAN_NOT_INQ);
                }
            } else if (BPCPQORG.LVL == '6') {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPRGST.BR1 = BPCPQORG.BR;
                BPCPRGST.BR2 = REDEFINES16.WS_RVS_BR;
                S000_CALL_BPZPRGST();
                if (pgmRtn) return;
                if (BPCPRGST.BRANCH_FLG == 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.THIS_TLT_CAN_NOT_INQ);
                }
            } else if (BPCPQORG.LVL == '7'
                || BPCPQORG.LVL == '1'
                || BPCPQORG.LVL == '9') {
            } else {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.THIS_TLT_CAN_NOT_INQ);
            }
        }
    }
    public void R000_OUTPUT_RVS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT--------------------");
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_FLG);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_TX_DT);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_BR);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_RVS_NO);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_RVS_SEQ);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_AC);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_CCY);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_AMT);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_THEIR_AC);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_STS);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_CI_NO);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_CI_CNMS);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_TR_BR);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_TR_TELLER);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_SET_NO);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_SET_SEQ);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_PART);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_PAY_MAN);
        CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_PAY_BR);
        WS_CNT1 = WS_CNT1 + 1;
        if (WS_CNT1 > 500) {
            WS_END_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_CNT1);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RVS_INFO);
        SCCMPAG.DATA_LEN = 804;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 804;
        SCCMPAG.SCR_ROW_CNT = 15;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_03_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        WS_COUNT_NO_G = 0;
        WS_COUNT_NO_C = 0;
        T000_COUNT_AITGRVS();
        if (pgmRtn) return;
        T000_COUNT_AITCRVS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT_NO_G);
        CEP.TRC(SCCGWA, WS_COUNT_NO_C);
        AICOGGC.TOTAL_DATA.TOTAL_NUM = WS_COUNT_NO_C + WS_COUNT_NO_G;
        CEP.TRC(SCCGWA, AICOGGC.TOTAL_DATA.TOTAL_NUM);
        CEP.TRC(SCCGWA, "WLL11");
        WS_RECORD_NUM = (short) (AICOGGC.TOTAL_DATA.TOTAL_NUM % AICSGGC.PAGE_ROW);
        AICOGGC.TOTAL_DATA.TOTAL_PAGE = (int) ((AICOGGC.TOTAL_DATA.TOTAL_NUM - WS_RECORD_NUM) / AICSGGC.PAGE_ROW);
        CEP.TRC(SCCGWA, "WLL66");
        CEP.TRC(SCCGWA, AICSGGC.PAGE_ROW);
        CEP.TRC(SCCGWA, "WLL88");
        CEP.TRC(SCCGWA, AICOGGC.TOTAL_DATA.TOTAL_NUM);
        CEP.TRC(SCCGWA, AICOGGC.TOTAL_DATA.TOTAL_PAGE);
        if (WS_RECORD_NUM > 0) {
            AICOGGC.TOTAL_DATA.TOTAL_PAGE = AICOGGC.TOTAL_DATA.TOTAL_PAGE + 1;
        }
        CEP.TRC(SCCGWA, AICOGGC.TOTAL_DATA.TOTAL_PAGE);
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        WS_RVS_INFO.WS_O_FLG = '1';
        WS_RVS_INFO.WS_O_TX_DT = AIRGRVS.TX_DT;
        WS_RVS_INFO.WS_O_BR = AIRGRVS.BR;
        WS_RVS_INFO.WS_O_RVS_NO = AIRGRVS.KEY.RVS_NO;
        WS_RVS_INFO.WS_O_RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
        WS_RVS_INFO.WS_O_AC = AIRGRVS.AC;
        WS_RVS_INFO.WS_O_CCY = AIRGRVS.CCY;
        WS_RVS_INFO.WS_O_AMT = AIRGRVS.AMT;
        WS_RVS_INFO.WS_O_STS = AIRGRVS.STS;
        WS_RVS_INFO.WS_O_THEIR_AC = AIRGRVS.THEIR_AC;
        WS_RVS_INFO.WS_O_CI_NO = AIRGRVS.CI_NO;
        WS_RVS_INFO.WS_O_TR_BR = AIRGRVS.TR_BR;
        WS_RVS_INFO.WS_O_TR_TELLER = AIRGRVS.TR_TELLER;
        WS_RVS_INFO.WS_O_SET_NO = AIRGRVS.SET_NO;
        WS_RVS_INFO.WS_O_SET_SEQ = AIRGRVS.SET_SEQ;
        WS_RVS_INFO.WS_O_PART = AIRGRVS.PART;
        WS_RVS_INFO.WS_O_PAY_MAN = AIRGRVS.PAY_MAN;
        WS_RVS_INFO.WS_O_PAY_BR = AIRGRVS.PAY_BR;
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.TX_DT);
        CEP.TRC(SCCGWA, AIRGRVS.BR);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.AC);
        CEP.TRC(SCCGWA, AIRGRVS.CCY);
        CEP.TRC(SCCGWA, AIRGRVS.AMT);
        CEP.TRC(SCCGWA, AIRGRVS.TR_BR);
        CEP.TRC(SCCGWA, AIRGRVS.TR_TELLER);
        CEP.TRC(SCCGWA, AIRGRVS.CI_NO);
        CEP.TRC(SCCGWA, AIRGRVS.PAY_MAN);
        CEP.TRC(SCCGWA, AIRGRVS.PAY_BR);
        if ((AIRGRVS.CI_NO.trim().length() == 0)) {
            WS_RVS_INFO.WS_O_CI_CNMS = " ";
        } else {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_RVS_INFO.WS_O_CI_CNMS = CICCUST.O_DATA.O_CI_NM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RVS_INFO);
        SCCMPAG.DATA_LEN = 804;
        B_MPAG();
        if (pgmRtn) return;
        WS_SUS_FLG = ' ';
    }
    public void B020_04_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        WS_RD_NUM += 1;
        CEP.TRC(SCCGWA, "1");
        DATA.FLG = '1';
        CEP.TRC(SCCGWA, "2");
        DATA.TX_DT = AIRGRVS.TX_DT;
        DATA.BR = AIRGRVS.BR;
        DATA.RVS_NO = AIRGRVS.KEY.RVS_NO;
        DATA.RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
        DATA.AC = AIRGRVS.AC;
        DATA.CCY = AIRGRVS.CCY;
        DATA.AMT = AIRGRVS.AMT;
        DATA.STS = AIRGRVS.STS;
        DATA.THEIR_AC = AIRGRVS.THEIR_AC;
        DATA.CI_NO = AIRGRVS.CI_NO;
        DATA.TR_BR = AIRGRVS.TR_BR;
        DATA.TR_TELLER = AIRGRVS.TR_TELLER;
        DATA.SET_NO = AIRGRVS.SET_NO;
        DATA.SET_SEQ = AIRGRVS.SET_SEQ;
        DATA.PART = AIRGRVS.PART;
        DATA.PAY_MAN = AIRGRVS.PAY_MAN;
        DATA.PAY_BR = AIRGRVS.PAY_BR;
        DATA.TR_CODE = AIRGRVS.TR_CODE;
        DATA.APP = AIRGRVS.APP;
        DATA.CHNL_NO = AIRGRVS.CHNL_NO;
        DATA.TX_SUP_TLR = AIRGRVS.TX_SUP_TLR;
        DATA.PROD_TYPE = AIRGRVS.PROD_TYPE;
        DATA.CNTR_TYPE = AIRGRVS.CNTR_TYPE;
        DATA.ITM_S = AIRGRVS.ITM_S;
        DATA.SEQ_S = AIRGRVS.SEQ_S;
        DATA.AC_NO = AIRGRVS.AC_NO;
        DATA.BR_S = AIRGRVS.BR_S;
        DATA.SIGN = AIRGRVS.SIGN;
        DATA.GLMST = AIRGRVS.GLMST;
        DATA.VAL_DT = AIRGRVS.VAL_DT;
        DATA.EVENT_TYPE = AIRGRVS.EVENT_TYPE;
        DATA.REF_NO = AIRGRVS.REF_NO;
        CEP.TRC(SCCGWA, WS_RD_NUM);
        CEP.TRC(SCCGWA, AICOGGC.DATA.get(WS_RD_NUM-1).RVS_NO);
        CEP.TRC(SCCGWA, AICOGGC.DATA.get(WS_RD_NUM-1).RVS_SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.TX_DT);
        CEP.TRC(SCCGWA, AIRGRVS.BR);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.AC);
        CEP.TRC(SCCGWA, AIRGRVS.CCY);
        CEP.TRC(SCCGWA, AIRGRVS.AMT);
        CEP.TRC(SCCGWA, AIRGRVS.TR_BR);
        CEP.TRC(SCCGWA, AIRGRVS.TR_TELLER);
        CEP.TRC(SCCGWA, AIRGRVS.CI_NO);
        CEP.TRC(SCCGWA, AIRGRVS.PAY_MAN);
        CEP.TRC(SCCGWA, AIRGRVS.PAY_BR);
        CEP.TRC(SCCGWA, AIRGRVS.TR_CODE);
        CEP.TRC(SCCGWA, AIRGRVS.APP);
        CEP.TRC(SCCGWA, AIRGRVS.CHNL_NO);
        CEP.TRC(SCCGWA, AIRGRVS.TX_SUP_TLR);
        CEP.TRC(SCCGWA, AIRGRVS.PROD_TYPE);
        CEP.TRC(SCCGWA, AIRGRVS.CNTR_TYPE);
        CEP.TRC(SCCGWA, AIRGRVS.ITM_S);
        CEP.TRC(SCCGWA, AIRGRVS.SEQ_S);
        CEP.TRC(SCCGWA, AIRGRVS.AC_NO);
        CEP.TRC(SCCGWA, AIRGRVS.BR_S);
        CEP.TRC(SCCGWA, AIRGRVS.SIGN);
        CEP.TRC(SCCGWA, AIRGRVS.GLMST);
        CEP.TRC(SCCGWA, AIRGRVS.VAL_DT);
        CEP.TRC(SCCGWA, AIRGRVS.EVENT_TYPE);
        CEP.TRC(SCCGWA, AIRGRVS.REF_NO);
    }
    public void B020_05_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        WS_RD_NUM += 1;
        DATA.FLG = '2';
        DATA.TX_DT = AIRCRVS.TX_DT;
        DATA.BR = AIRCRVS.BR;
        DATA.RVS_NO = AIRCRVS.KEY.RVS_NO;
        DATA.RVS_SEQ = AIRCRVS.KEY.RVS_SEQ;
        DATA.AC = AIRCRVS.AC;
        DATA.CCY = AIRCRVS.CCY;
        DATA.AMT = AIRCRVS.AMT;
        DATA.STS = AIRCRVS.STS;
        DATA.THEIR_AC = AIRCRVS.THEIR_AC;
        DATA.CI_NO = AIRCRVS.CI_NO;
        DATA.TR_BR = AIRCRVS.TR_BR;
        DATA.TR_TELLER = AIRCRVS.TR_TELLER;
        DATA.SET_NO = AIRCRVS.SET_NO;
        DATA.SET_SEQ = AIRCRVS.SET_SEQ;
        DATA.PART = AIRCRVS.PART;
        DATA.PAY_MAN = AIRCRVS.PAY_MAN;
        DATA.PAY_BR = AIRCRVS.PAY_BR;
        DATA.TR_CODE = AIRCRVS.TR_CODE;
        DATA.APP = AIRCRVS.APP;
        DATA.CHNL_NO = AIRCRVS.CHNL_NO;
        DATA.TX_SUP_TLR = AIRCRVS.TX_SUP_TLR;
        DATA.GLMST = AIRCRVS.GLMST;
        DATA.VAL_DT = AIRCRVS.VAL_DT;
        CEP.TRC(SCCGWA, WS_RD_NUM);
        CEP.TRC(SCCGWA, AICOGGC.DATA.get(WS_RD_NUM-1).RVS_NO);
        CEP.TRC(SCCGWA, AICOGGC.DATA.get(WS_RD_NUM-1).RVS_SEQ);
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRCRVS.TX_DT);
        CEP.TRC(SCCGWA, AIRCRVS.BR);
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_SEQ);
        CEP.TRC(SCCGWA, AIRCRVS.AC);
        CEP.TRC(SCCGWA, AIRCRVS.CCY);
        CEP.TRC(SCCGWA, AIRCRVS.AMT);
        CEP.TRC(SCCGWA, AIRCRVS.TR_BR);
        CEP.TRC(SCCGWA, AIRCRVS.TR_TELLER);
        CEP.TRC(SCCGWA, AIRCRVS.CI_NO);
        CEP.TRC(SCCGWA, AIRCRVS.PAY_MAN);
        CEP.TRC(SCCGWA, AIRCRVS.PAY_BR);
        CEP.TRC(SCCGWA, AIRCRVS.TR_CODE);
        CEP.TRC(SCCGWA, AIRCRVS.APP);
        CEP.TRC(SCCGWA, AIRCRVS.CHNL_NO);
        CEP.TRC(SCCGWA, AIRCRVS.TX_SUP_TLR);
        CEP.TRC(SCCGWA, AIRCRVS.GLMST);
        CEP.TRC(SCCGWA, AIRCRVS.VAL_DT);
    }
    public void T000_STARTBR_AITGINF_NO_RVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ( 0 = :AIRGINF.ITM "
            + "OR ITM = :AIRGINF.ITM ) "
            + "AND ( ' ' = :AIRGINF.SEQ "
            + "OR SEQ = :AIRGINF.SEQ ) "
            + "AND ( 0 = :AIRGINF.CCY "
            + "OR CCY = :AIRGINF.CCY ) "
            + "AND STS IN ( :AIRGINF.STS , :WS_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND LST_G_DT <= :WS_SUS_DATE";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITGINF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITGINF_BR);
    }
    public void TOOO_READ_AITGINF_BY_RVS() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.errhdl = true;
        IBS.READ(SCCGWA, AIRGINF, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
            R000_CHECK_BR();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AICRGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITGRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO "
            + "AND ( AMT BETWEEN :WS_DB_STR_AMT "
            + "AND :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT ) "
            + "AND ( 0 = :AIRGRVS.THEIR_AC "
            + "OR THEIR_AC = :AIRGRVS.THEIR_AC )";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
            CEP.TRC(SCCGWA, "NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRVS_FLG = 'N';
            CEP.TRC(SCCGWA, "NOTFND");
        } else {
        }
    }
    public void T000_READNEXT_AITGRVS_FIRST() throws IOException,SQLException,Exception {
        WS_RCD_SEQ = AICRGRVS.RCD_SEQ;
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRVS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGRVS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITCRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        AITCRVS_BR.rp = new DBParm();
        AITCRVS_BR.rp.TableName = "AITCRVS";
        AITCRVS_BR.rp.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO "
            + "AND ( AMT BETWEEN :WS_DB_STR_AMT "
            + "AND :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT ) "
            + "AND ( 0 = :AIRCRVS.THEIR_AC "
            + "OR THEIR_AC = :AIRCRVS.THEIR_AC )";
        IBS.STARTBR(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CRVS_FLG = 'Y';
            CEP.TRC(SCCGWA, "NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CRVS_FLG = 'N';
            CEP.TRC(SCCGWA, "NOTFND");
        } else {
        }
    }
    public void T000_READ_AITGRVS() throws IOException,SQLException,Exception {
        WS_DB_THEIR_AC = AICSGGC.THEIR_AC;
        null.col = "TX_DT,BR,RVS_NO,RVS_SEQ,AC,CCY,AMT,THEIR_AC, STS,CI_NO,TR_BR,TR_TELLER,SET_NO,SET_SEQ, PART,PAY_MAN,PAY_BR,VAL_DT";
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.where = "( RVS_NO = :AIRGRVS.KEY.RVS_NO ) "
            + "AND ( RVS_SEQ = :AIRGRVS.KEY.RVS_SEQ ) "
            + "AND ( AMT BETWEEN :WS_DB_STR_AMT "
            + "AND :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT ) "
            + "AND ( 0 = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.READ(SCCGWA, AIRGRVS, this, AITGRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRVS_FLG = 'N';
        } else {
        }
        WS_CNT2 = WS_CNT2 + 1;
        WS_CNT3 = WS_CNT3 + 1;
        WS_CNT5 = WS_CNT5 + 1;
    }
    public void T000_READ_AITCRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        CEP.TRC(SCCGWA, WS_DB_SUS_STDT);
        CEP.TRC(SCCGWA, WS_DB_SUS_ENDT);
        CEP.TRC(SCCGWA, AIRGRVS.THEIR_AC);
        WS_DB_THEIR_AC = AICSGGC.THEIR_AC;
        null.col = "TX_DT,BR,RVS_NO,RVS_SEQ,AC,CCY,AMT,THEIR_AC, STS,CI_NO,TR_BR,TR_TELLER,SET_NO,SET_SEQ, PART,PAY_MAN,PAY_BR,VAL_DT";
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.where = "( RVS_NO = :AIRCRVS.KEY.RVS_NO ) "
            + "AND ( RVS_SEQ = :AIRCRVS.KEY.RVS_SEQ ) "
            + "AND ( AMT BETWEEN :WS_DB_STR_AMT "
            + "AND :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT ) "
            + "AND ( 0 = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.READ(SCCGWA, AIRCRVS, this, AITCRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CRVS_FLG = 'N';
            CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_SEQ);
        } else {
        }
        WS_CNT2 = WS_CNT2 + 1;
        WS_CNT4 = WS_CNT4 + 1;
        WS_CNT5 = WS_CNT5 + 1;
    }
    public void T000_READNEXT_AITGRVS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
            CEP.TRC(SCCGWA, "NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRVS_FLG = 'N';
            CEP.TRC(SCCGWA, "NOTFND");
        } else {
        }
    }
    public void T000_ENDBR_AITGRVS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITGRVS_BR);
    }
    public void T000_READNEXT_AITCRVS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRCRVS, this, AITCRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CRVS_FLG = 'Y';
            CEP.TRC(SCCGWA, "NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CRVS_FLG = 'N';
            CEP.TRC(SCCGWA, "NOTFND");
        } else {
        }
    }
    public void T000_ENDBR_AITCRVS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITCRVS_BR);
    }
    public void T000_COUNT_AITGRVS() throws IOException,SQLException,Exception {
        WS_DB_STR_AMT = AICSGGC.STR_AMT;
        WS_DB_END_AMT = AICSGGC.END_AMT;
        WS_DB_THEIR_AC = AICSGGC.THEIR_AC;
        CEP.TRC(SCCGWA, "TEST88");
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_THEIR_AC);
        CEP.TRC(SCCGWA, "11");
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.set = "WS-COUNT-NO-G=COUNT(1)";
        AITGRVS_RD.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO "
            + "AND ( AMT >= :WS_DB_STR_AMT "
            + "AND AMT <= :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT ) "
            + "AND ( 0 = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.GROUP(SCCGWA, AIRGRVS, this, AITGRVS_RD);
        CEP.TRC(SCCGWA, WS_COUNT_NO_G);
    }
    public void T000_COUNT_AITCRVS() throws IOException,SQLException,Exception {
        WS_DB_STR_AMT = AICSGGC.STR_AMT;
        WS_DB_END_AMT = AICSGGC.END_AMT;
        WS_DB_THEIR_AC = AICSGGC.THEIR_AC;
        CEP.TRC(SCCGWA, "TEST88");
        CEP.TRC(SCCGWA, WS_DB_END_AMT);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, WS_DB_STR_AMT);
        CEP.TRC(SCCGWA, WS_DB_THEIR_AC);
        CEP.TRC(SCCGWA, "11");
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.set = "WS-COUNT-NO-C=COUNT(1)";
        AITCRVS_RD.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO "
            + "AND ( AMT >= :WS_DB_STR_AMT "
            + "AND AMT <= :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT ) "
            + "AND ( 0 = :WS_DB_THEIR_AC "
            + "OR THEIR_AC = :WS_DB_THEIR_AC )";
        IBS.GROUP(SCCGWA, AIRCRVS, this, AITCRVS_RD);
        CEP.TRC(SCCGWA, WS_COUNT_NO_C);
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        CEP.TRC(SCCGWA, AICRGINF.RC.RC_CODE);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRGRVS() throws IOException,SQLException,Exception {
        AICRGRVS.INFO.POINTER = AIRGRVS;
        AICRGRVS.INFO.REC_LEN = 1113;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GRVS", AICRGRVS);
        if (AICRGRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRCRVS() throws IOException,SQLException,Exception {
        AICRCRVS.INFO.POINTER = AIRCRVS;
        AICRCRVS.INFO.REC_LEN = 893;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CRVS", AICRCRVS);
        if (AICRCRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
