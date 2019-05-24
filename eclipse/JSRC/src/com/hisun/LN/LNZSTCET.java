package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSTCET {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    brParm LNTTRAN_BR = new brParm();
    DBParm LNTRATN_RD;
    DBParm LNTRATL_RD;
    brParm LNTAGRE_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String SCSSCLDT = "SCSSCLDT";
    String WK_ACR_RC_MMO = "LN";
    char WK_TXN_TYP_T = 'T';
    LNZSTCET_WS_DB_VARS WS_DB_VARS = new LNZSTCET_WS_DB_VARS();
    short WS_COUNT = 0;
    LNZSTCET_WS_VARIABLES WS_VARIABLES = new LNZSTCET_WS_VARIABLES();
    LNZSTCET_WS_OUT_RECODE WS_OUT_RECODE = new LNZSTCET_WS_OUT_RECODE();
    LNZSTCET_WS_PAGE_INFO WS_PAGE_INFO = new LNZSTCET_WS_PAGE_INFO();
    LNZSTCET_WS_COND_FLAG WS_COND_FLAG = new LNZSTCET_WS_COND_FLAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    CICCUST CICCUST = new CICCUST();
    CICQACR CICQACR = new CICQACR();
    LNCUFACT LNCUFACT = new LNCUFACT();
    LNCUCMMT LNCUCMMT = new LNCUCMMT();
    LNCSTCLT LNCSTCLT = new LNCSTCLT();
    LNCSTCTR LNCSTCTR = new LNCSTCTR();
    LNCOTCTR LNCOTCTR = new LNCOTCTR();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCRTRAN LNCRTRAN = new LNCRTRAN();
    LNRSETL LNRSETL = new LNRSETL();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRICTL LNRICTL = new LNRICTL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCICUT LNCICUT = new LNCICUT();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNCICIQ LNCICIQ = new LNCICIQ();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    CIRACR CIRACR = new CIRACR();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    CICQCIAC CICQCIAC = new CICQCIAC();
    SCCGWA SCCGWA;
    LNCSTCET LNCSTCET;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSTCET LNCSTCET) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSTCET = LNCSTCET;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSTCET return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLAG);
        IBS.init(SCCGWA, WS_DB_VARS);
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        WS_COUNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_GET_PAGE_INFO();
        if (pgmRtn) return;
        B030_SET_FUNC_CD_PROC();
        if (pgmRtn) return;
        B070_CALL_SERVICE_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (LNCSTCET.CI_NO.trim().length() == 0 
            && LNCSTCET.CTA_NO.trim().length() == 0 
            && (LNCSTCET.TRAN_DTE == 0 
            || LNCSTCET.TRAN_JRNNO == 0)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DGDJSHDKSH");
    }
    public void B020_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        WS_PAGE_INFO.ROW_FLG = 'N';
        WS_PAGE_INFO.PAGE_FLG = 'N';
        WS_PAGE_INFO.TOTAL_NUM = 0;
        WS_PAGE_INFO.PAGE_IDX = 0;
        WS_PAGE_INFO.LAST_PAGE = 'N';
        WS_PAGE_INFO.PAGE_ROW = LNCSTCET.PAGE_ROW;
        WS_PAGE_INFO.CURR_PAGE = (short) LNCSTCET.PAGE_NUM;
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
    }
    public void B030_SET_FUNC_CD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSTCET.TRAN_DTE);
        CEP.TRC(SCCGWA, LNCSTCET.TRAN_JRNNO);
        CEP.TRC(SCCGWA, LNCSTCET.CTA_NO);
        CEP.TRC(SCCGWA, LNCSTCET.CI_NO);
        if (LNCSTCET.TRAN_DTE != 0 
            && LNCSTCET.TRAN_JRNNO != 0) {
            WS_VARIABLES.FUNC_FLG = 'A';
        } else {
            if (LNCSTCET.CTA_NO.trim().length() > 0) {
                WS_VARIABLES.FUNC_FLG = 'C';
            } else {
                if (LNCSTCET.CI_NO.trim().length() > 0) {
                    WS_VARIABLES.FUNC_FLG = 'B';
                }
            }
        }
    }
    public void B070_CALL_SERVICE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        if (WS_VARIABLES.FUNC_FLG == 'A') {
            CEP.TRC(SCCGWA, "WS-FUNC-JRN");
            LNRTRAN.KEY.TR_DATE = LNCSTCET.TRAN_DTE;
            LNRTRAN.KEY.TR_JRN_NO = LNCSTCET.TRAN_JRNNO;
            R000_BROWSE_TRAN_PROCESS();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.FUNC_FLG == 'B') {
            CEP.TRC(SCCGWA, "WS-FUNC-CINO");
            R000_INQUE_CI_NO();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.FUNC_FLG == 'C') {
            CEP.TRC(SCCGWA, "WS-FUNC-CONT");
            LNRTRAN.KEY.CONTRACT_NO = LNCSTCET.CTA_NO;
            R000_BROWSE_TRAN_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "WS-FUNC-OTHER");
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_OUTPUT_DATA_BEGIN();
        if (pgmRtn) return;
    }
    public void R000_INQUE_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CEP.TRC(SCCGWA, LNCSTCET.CI_NO);
        CICQCIAC.FUNC = '2';
        CICQCIAC.DATA.FRM_APP = "LN";
        CICQCIAC.DATA.CI_NO = LNCSTCET.CI_NO;
        CICQCIAC.DATA.CANCEL_INQ_FLG = 'Y';
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        CICQACR.CNT = 1;
        CEP.TRC(SCCGWA, CICQACR.CNT);
        CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO);
        while (CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO.trim().length() != 0 
            && CICQACR.CNT <= 100) {
            CEP.TRC(SCCGWA, CICQACR.CNT);
            CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO);
            R000_BROWSE_TRAN_PROCESS();
            if (pgmRtn) return;
            CICQACR.CNT += 1;
        }
    }
    public void R000_BROWSE_TRAN_PROCESS() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.FUNC_FLG == 'C') {
        } else {
            LNRTRAN.KEY.CONTRACT_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO;
        }
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.SUB_CTA_NO);
        IBS.init(SCCGWA, LNCSLNQ);
        CEP.TRC(SCCGWA, WS_VARIABLES.FUNC_FLG);
        if (WS_VARIABLES.FUNC_FLG == 'A') {
            T000_STARTBR_LNTTRAN_JRN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSTCET.STA_DT);
        CEP.TRC(SCCGWA, LNCSTCET.DUE_DT);
        if (WS_VARIABLES.FUNC_FLG != 'A') {
            if (LNCSTCET.STA_DT != 0 
                && LNCSTCET.DUE_DT != 0) {
                WS_DB_VARS.STR_DT = LNCSTCET.STA_DT;
                WS_DB_VARS.DUE_DT = LNCSTCET.DUE_DT;
                CEP.TRC(SCCGWA, "2018102488");
                T000_STARTBR_LNTTRAN_DT2();
                if (pgmRtn) return;
            }
            if (LNCSTCET.STA_DT != 0 
                && LNCSTCET.DUE_DT == 0) {
                LNRTRAN.KEY.TR_DATE = LNCSTCET.STA_DT;
                T000_STARTBR_LNTTRAN_DT1_STA();
                if (pgmRtn) return;
            }
            if (LNCSTCET.STA_DT == 0 
                && LNCSTCET.DUE_DT != 0) {
                LNRTRAN.KEY.TR_DATE = LNCSTCET.DUE_DT;
                T000_STARTBR_LNTTRAN_DT1_DUE();
                if (pgmRtn) return;
            }
            if (LNCSTCET.STA_DT == 0 
                && LNCSTCET.DUE_DT == 0) {
                T000_STARTBR_LNTTRAN_DT();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        T000_READNEXT_LNTTRAN();
        if (pgmRtn) return;
        for (WS_COUNT = 1; WS_COND_FLAG.TRAN_FLG != 'N' 
            && WS_COUNT != 650; WS_COUNT += 1) {
            CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
            CEP.TRC(SCCGWA, LNRTRAN.TR_CODE);
            CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
            CEP.TRC(SCCGWA, LNRTRAN.TR_CODE);
            CEP.TRC(SCCGWA, WS_COUNT);
            CEP.TRC(SCCGWA, LNCSTCET.TRAN_TYP);
            WS_COND_FLAG.OUT_FLG = 'N';
            if (LNCSTCET.TRAN_TYP == ' ') {
                if (LNRTRAN.TR_CODE == 2100 
                    || LNRTRAN.TR_CODE == 2000 
                    || LNRTRAN.TR_CODE == 2200 
                    || LNRTRAN.TR_CODE == 2300 
                    || LNRTRAN.TR_CODE == 6140) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if ((LNRTRAN.TR_CODE == 4000 
                    || LNRTRAN.TR_CODE == 4100 
                    || LNRTRAN.TR_CODE == 4500 
                    || LNRTRAN.TR_CODE == 4600 
                    || LNRTRAN.TR_CODE == 4700) 
                    && LNRTRAN.KEY.TXN_TYP == WK_TXN_TYP_T) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if ((LNRTRAN.TR_CODE == 6010 
                    || LNRTRAN.TR_CODE == 6120) 
                    && LNRTRAN.KEY.TXN_TYP == WK_TXN_TYP_T) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNRTRAN.TR_CODE == 6020) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNRTRAN.TR_CODE == 6060 
                    && LNRTRAN.KEY.TXN_TYP == WK_TXN_TYP_T) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNRTRAN.TR_CODE == 6070) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                WS_COND_FLAG.OUT_FLG = 'Y';
                if ((LNRTRAN.TR_CODE == 4000 
                    || LNRTRAN.TR_CODE == 4100 
                    || LNRTRAN.TR_CODE == 4500 
                    || LNRTRAN.TR_CODE == 4600 
                    || LNRTRAN.TR_CODE == 4700 
                    || LNRTRAN.TR_CODE == 6060) 
                    && LNRTRAN.KEY.TXN_TYP != WK_TXN_TYP_T) {
                    WS_COND_FLAG.OUT_FLG = 'N';
                }
            } else {
                if (LNCSTCET.TRAN_TYP == '1' 
                    && (LNRTRAN.TR_CODE == 2100 
                    || LNRTRAN.TR_CODE == 2000 
                    || LNRTRAN.TR_CODE == 2200 
                    || LNRTRAN.TR_CODE == 2300 
                    || LNRTRAN.TR_CODE == 6140)) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNCSTCET.TRAN_TYP == '2' 
                    && LNRTRAN.KEY.TXN_TYP == WK_TXN_TYP_T 
                    && (LNRTRAN.TR_CODE == 4000 
                    || LNRTRAN.TR_CODE == 4100 
                    || LNRTRAN.TR_CODE == 4500 
                    || LNRTRAN.TR_CODE == 4700 
                    || LNRTRAN.TR_CODE == 4600)) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNCSTCET.TRAN_TYP == '3' 
                    && (LNRTRAN.TR_CODE == 6010 
                    || LNRTRAN.TR_CODE == 6120) 
                    && LNRTRAN.KEY.TXN_TYP == WK_TXN_TYP_T) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNCSTCET.TRAN_TYP == '5' 
                    && LNRTRAN.TR_CODE == 6020) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNCSTCET.TRAN_TYP == '5' 
                    && (LNRTRAN.TR_CODE == 6060 
                    && LNRTRAN.KEY.TXN_TYP == WK_TXN_TYP_T)) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                if (LNCSTCET.TRAN_TYP == '6' 
                    && LNRTRAN.TR_CODE == 6070) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
                WS_COND_FLAG.TR_CODE = "" + LNRTRAN.TR_CODE;
                JIBS_tmp_int = WS_COND_FLAG.TR_CODE.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) WS_COND_FLAG.TR_CODE = "0" + WS_COND_FLAG.TR_CODE;
                if (LNCSTCET.TRAN_TYP == '7' 
                    && (!WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2100") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2000") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2200") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2300") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6140") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4000") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4100") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4500") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4600") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4700") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6010") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6120") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2131") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2941") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6020") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6060") 
                    && !WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6070"))) {
                    WS_COND_FLAG.OUT_FLG = 'Y';
                }
            }
            CEP.TRC(SCCGWA, WS_COND_FLAG.OUT_FLG);
            IBS.init(SCCGWA, LNRICTL);
            IBS.init(SCCGWA, LNCRICTL);
            LNRICTL.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            T000_READ_LNTICTL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
            if (WS_COND_FLAG.OUT_FLG == 'Y') {
                WS_PAGE_INFO.TOTAL_NUM += 1;
            }
            if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM 
                && WS_COND_FLAG.OUT_FLG == 'Y') {
                WS_PAGE_INFO.ROW_FLG = 'Y';
                WS_PAGE_INFO.PAGE_IDX += 1;
                R000_WRITE_QUEUE_PROCESS();
                if (pgmRtn) return;
            }
            T000_READNEXT_LNTTRAN();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTTRAN();
        if (pgmRtn) return;
    }
    public void R000_WRITE_QUEUE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCOTCTR);
        IBS.init(SCCGWA, LNRRATN);
        IBS.init(SCCGWA, LNRRATL);
        LNCOTCTR.DATA.TR_DT = LNRTRAN.KEY.TR_DATE;
        LNCOTCTR.DATA.CTA_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNCOTCTR.DATA.CTA_CCY = LNRTRAN.TXN_CCY;
        WS_COND_FLAG.EQU_CMMT_AMT = LNRTRAN.P_AMT;
        LNCOTCTR.DATA.TR_STS = LNRTRAN.TRAN_STS;
        LNCOTCTR.DATA.TR_CD = LNRTRAN.TR_CODE;
        LNCOTCTR.DATA.TR_JRN_NO = LNRTRAN.KEY.TR_JRN_NO;
        LNCOTCTR.DATA.PAY_AC = LNRTRAN.PAY_AC1;
        LNCOTCTR.DATA.LN_BAL = LNRTRAN.OS_BAL;
        WS_COND_FLAG.TR_CODE = "" + LNRTRAN.TR_CODE;
        JIBS_tmp_int = WS_COND_FLAG.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_COND_FLAG.TR_CODE = "0" + WS_COND_FLAG.TR_CODE;
        LNCOTCTR.DATA.DRW_AMT = LNRTRAN.P_AMT;
        LNCOTCTR.DATA.O_AMT = LNRTRAN.O_AMT;
        if (LNRTRAN.TR_CODE == '6060') {
            LNCOTCTR.DATA.DRW_AMT = LNRTRAN.P_WAV_AMT;
            LNCOTCTR.DATA.RPY_I_AMT = LNRTRAN.I_ADJ_AMT;
            if (LNCOTCTR.DATA.RPY_I_AMT == 0) {
                LNCOTCTR.DATA.RPY_I_AMT = LNRTRAN.I_WAV_AMT;
            }
            LNCOTCTR.DATA.O_AMT = LNRTRAN.O_WAV_AMT;
            LNCOTCTR.DATA.L_AMT = LNRTRAN.L_WAV_AMT;
        } else if (LNRTRAN.TR_CODE == '6080') {
            LNCOTCTR.DATA.DRW_AMT = LNRTRAN.P_AMT + LNRTRAN.P_WAV_AMT;
            LNCOTCTR.DATA.RPY_I_AMT = LNRTRAN.I_AMT + LNRTRAN.I_WAV_AMT;
            LNCOTCTR.DATA.O_AMT = LNRTRAN.O_AMT + LNRTRAN.O_WAV_AMT;
            LNCOTCTR.DATA.L_AMT = LNRTRAN.L_AMT + LNRTRAN.L_WAV_AMT;
        } else {
            LNCOTCTR.DATA.DRW_AMT = LNRTRAN.P_AMT;
            LNCOTCTR.DATA.DRW_AMT = LNCOTCTR.DATA.DRW_AMT - LNRTRAN.P_WAV_AMT;
            LNCOTCTR.DATA.RPY_I_AMT = LNRTRAN.I_AMT;
            LNCOTCTR.DATA.RPY_I_AMT = LNRTRAN.I_AMT - LNRTRAN.I_ADJ_AMT;
            LNCOTCTR.DATA.O_AMT = LNRTRAN.O_AMT;
            LNCOTCTR.DATA.O_AMT = LNCOTCTR.DATA.O_AMT - LNRTRAN.O_WAV_AMT;
            LNCOTCTR.DATA.L_AMT = LNRTRAN.L_AMT;
            LNCOTCTR.DATA.L_AMT = LNRTRAN.L_AMT - LNRTRAN.L_WAV_AMT;
        }
        LNCOTCTR.DATA.PREPAY_CHG = LNRTRAN.PREPAY_CHG;
        LNCOTCTR.DATA.F_AMT = LNRTRAN.F_AMT;
        LNRRATN.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNRRATN.KEY.ACTV_DT = LNRTRAN.KEY.TR_DATE;
        T000_READ_LNTRATN();
        if (pgmRtn) return;
        LNCOTCTR.DATA.INT_RAT = LNRRATN.ALL_IN_RATE;
        LNRRATL.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNRRATL.KEY.ACTV_DT = LNRTRAN.KEY.TR_DATE;
        LNRRATL.KEY.OVD_KND = 'O';
        T000_READ_LNTRATL();
        if (pgmRtn) return;
        LNCOTCTR.DATA.PO_RAT = LNRRATL.EFF_RAT;
        LNRRATL.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNRRATL.KEY.ACTV_DT = LNRTRAN.KEY.TR_DATE;
        LNRRATL.KEY.OVD_KND = 'L';
        T000_READ_LNTRATL();
        if (pgmRtn) return;
        LNCOTCTR.DATA.IO_RAT = LNRRATL.EFF_RAT;
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.DRW_AMT);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.RPY_I_AMT);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.L_AMT);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.INT_RAT);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.IO_RAT);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.PREPAY_CHG);
        CEP.TRC(SCCGWA, LNCOTCTR.DATA.F_AMT);
        if (((WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2100") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2000") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2200") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2300") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6140")))) {
            LNCOTCTR.DATA.TR_TY = '1';
        } else if (((WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4000") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4100") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4500") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4600") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("4700")))) {
            LNCOTCTR.DATA.TR_TY = '2';
        } else if (((WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6010") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6120")))) {
            LNCOTCTR.DATA.TR_TY = '3';
        } else if (((WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2131") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("2941")))) {
            LNCOTCTR.DATA.TR_TY = '4';
        } else if (((WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6020") 
                || WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6060")))) {
            LNCOTCTR.DATA.TR_TY = '5';
        } else if (WS_COND_FLAG.TR_CODE.equalsIgnoreCase("6070")) {
            LNCOTCTR.DATA.TR_TY = '6';
        } else {
            LNCOTCTR.DATA.TR_TY = '7';
        }
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1]);
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_TR_DT = LNCOTCTR.DATA.TR_DT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_CTA_NO = LNCOTCTR.DATA.CTA_NO;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_CTA_CCY = LNCOTCTR.DATA.CTA_CCY;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_TR_STS = LNCOTCTR.DATA.TR_STS;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_TR_CD = LNCOTCTR.DATA.TR_CD;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_TR_JRN_NO = LNCOTCTR.DATA.TR_JRN_NO;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_PAY_AC = LNCOTCTR.DATA.PAY_AC;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_TR_TY = LNCOTCTR.DATA.TR_TY;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_DRW_AMT = LNCOTCTR.DATA.DRW_AMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_INT_RAT = LNCOTCTR.DATA.INT_RAT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_LN_BAL = LNCOTCTR.DATA.LN_BAL;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_RPY_I_AMT = LNCOTCTR.DATA.RPY_I_AMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_O_AMT = LNCOTCTR.DATA.O_AMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_L_AMT = LNCOTCTR.DATA.L_AMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_PO_RAT = LNCOTCTR.DATA.PO_RAT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_IO_RAT = LNCOTCTR.DATA.IO_RAT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_F_AMT = LNCOTCTR.DATA.F_AMT;
        WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_OTCTR_PREPAY_CHG = LNCOTCTR.DATA.PREPAY_CHG;
    }
    public void R000_OUTPUT_DATA_BEGIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.BAL_CNT);
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.TOTAL_PAGE == WS_PAGE_INFO.CURR_PAGE) {
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                }
            }
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_IDX;
        } else {
            WS_PAGE_INFO.TOTAL_PAGE = 1;
            WS_PAGE_INFO.TOTAL_NUM = 0;
            WS_PAGE_INFO.LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
            WS_PAGE_INFO.CURR_PAGE = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 0;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        SCCFMT.FMTID = "LN803";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 2529;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void T000_STARTBR_LNTTRAN_DT() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO >= :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = 'B'";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_LNTTRAN_DT1_STA() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO >= :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = 'B' "
            + "AND TR_DATE >= :LNRTRAN.KEY.TR_DATE";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_LNTTRAN_DT1_DUE() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO >= :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = 'B' "
            + "AND TR_DATE <= :LNRTRAN.KEY.TR_DATE";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_LNTTRAN_DT2() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO >= :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = 'B' "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.DUE_DT";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_LNTTRAN_JRN() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TR_DATE = :LNRTRAN.KEY.TR_DATE "
            + "AND REC_FLAG = 'B' "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLAG.TRAN_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLAG.TRAN_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTTRAN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 605;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (LNCRICTL.RETURN_INFO == 'E') {
        } else {
            if (LNCRICTL.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
                CEP.TRC(SCCGWA, LNCRICTL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 713;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RETURN_INFO == 'E') {
        } else {
            if (LNCRRCVD.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
    }
    public void T000_READ_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATL.KEY.ACTV_DT "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 477;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_LINK_CIZQACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR", CICQACR, true);
        if (CICQACR.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CI-INQ-CUST-ACR";
        SCCCALL.COMMAREA_PTR = CICQCIAC;
        SCCCALL.NOFMT_FLG = 'Y';
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (CICQCIAC.RC.RC_CODE != 0 
            && CICQCIAC.RC.RC_CODE != 8051) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_01_GET_CONT_NO() throws IOException,SQLException,Exception {
        WS_COND_FLAG.FND_FLG = 'N';
        T000_01_STARTBR_LNTAGRE();
        if (pgmRtn) return;
        T000_01_READNEXT_LNTAGRE();
        if (pgmRtn) return;
        while (WS_COND_FLAG.FND_FLG != 'N') {
            R000_BROWSE_TRAN_PROCESS();
            if (pgmRtn) return;
            T000_01_READNEXT_LNTAGRE();
            if (pgmRtn) return;
        }
        T000_01_ENDBR_LNTAGRE();
        if (pgmRtn) return;
    }
    public void T000_01_STARTBR_LNTAGRE() throws IOException,SQLException,Exception {
        LNRAGRE.PAPER_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[CICQACR.CNT-1].ENTY_NO;
        CEP.TRC(SCCGWA, LNRAGRE.PAPER_NO);
        LNTAGRE_BR.rp = new DBParm();
        LNTAGRE_BR.rp.TableName = "LNTAGRE";
        LNTAGRE_BR.rp.where = "PAPER_NO = :LNRAGRE.PAPER_NO";
        IBS.STARTBR(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
    }
    public void T000_01_READNEXT_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLAG.FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLAG.FND_FLG = 'N';
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_01_ENDBR_LNTAGRE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTAGRE_BR);
    }
    public void S000_CALL_LNZUFACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-U-FACILITY-MAIN", LNCUFACT);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_VARIABLES.ERR_MSG == null) WS_VARIABLES.ERR_MSG = "";
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG += " ";
            WS_VARIABLES.ERR_MSG = "SC" + WS_VARIABLES.ERR_MSG.substring(2);
            if (WS_VARIABLES.ERR_MSG == null) WS_VARIABLES.ERR_MSG = "";
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_VARIABLES.ERR_MSG = WS_VARIABLES.ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_VARIABLES.ERR_MSG.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
