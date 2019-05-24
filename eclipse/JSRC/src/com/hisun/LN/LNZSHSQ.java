package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSHSQ {
    brParm LNTTRAN_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String FMT_ID = "LN420";
    short PAGE_ROW = 10;
    short TR_CODE_RPN = 4000;
    short TR_CODE_RPN9 = 4009;
    short TR_CODE_ERP = 4100;
    short TR_CODE_ERP9 = 4109;
    short TR_CODE_DPO = 2270;
    short TR_CODE_FKC = 2000;
    short TR_CODE_FKR = 2300;
    LNZSHSQ_WS_VARIABLES WS_VARIABLES = new LNZSHSQ_WS_VARIABLES();
    LNZSHSQ_WS_OUT_RECODE WS_OUT_RECODE = new LNZSHSQ_WS_OUT_RECODE();
    LNZSHSQ_WS_COND_FLG WS_COND_FLG = new LNZSHSQ_WS_COND_FLG();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCPACTY_WS_DB_VARS WS_DB_VARS = new DCCPACTY_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNCSHSQ LNCSHSQ;
    public void MP(SCCGWA SCCGWA, LNCSHSQ LNCSHSQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSHSQ = LNCSHSQ;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSHSQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE);
        IBS.init(SCCGWA, WS_COND_FLG);
        LNCSHSQ.RC.RC_APP = "LN";
        LNCSHSQ.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO);
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE);
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE);
        if (LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, LNCSHSQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_DB_VARS.BEG_DTE = LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE;
        WS_DB_VARS.END_DTE = LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE;
        if (LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE == 0 
            && LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE == 0) {
            WS_COND_FLG.TXN_ENQ_TYP = '1';
        } else {
            WS_COND_FLG.TXN_ENQ_TYP = '4';
            if (LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE == 0) {
                WS_COND_FLG.TXN_ENQ_TYP = '2';
            }
            if (LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE == 0) {
                WS_COND_FLG.TXN_ENQ_TYP = '3';
            }
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.TXN_ENQ_TYP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B300_BROWSE_INIT();
        if (pgmRtn) return;
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        if (LNCSHSQ.FUNC_CODE != 'S') {
            B200_GET_TRAN_INF();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B300_BROWSE_INIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_NUM == 0) {
            WS_VARIABLES.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.CURR_PAGE = (short) LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_VARIABLES.LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_ROW);
        if (LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_ROW == 0) {
            WS_VARIABLES.PAGE_ROW = 10;
        } else {
            if (LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_ROW > 10) {
                WS_VARIABLES.PAGE_ROW = 10;
            } else {
                WS_VARIABLES.PAGE_ROW = (short) LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        WS_VARIABLES.NEXT_START_NUM = ( ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW ) + 1;
        CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        B150_GET_CTA_INF();
        if (pgmRtn) return;
    }
    public void B150_GET_CTA_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO;
        LNCCLNQ.PROD_FLG = 'Y';
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        LNCSHSQ.COMM_INFO.CTA_INFO.BR = LNCCLNQ.DATA.DOMI_BR;
        LNCSHSQ.COMM_INFO.CTA_INFO.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCSHSQ.COMM_INFO.CTA_INFO.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCSHSQ.COMM_INFO.CTA_INFO.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCSHSQ.COMM_INFO.CTA_INFO.CCY = LNCCLNQ.DATA.CCY;
        LNCSHSQ.COMM_INFO.CTA_INFO.LON_PRIN = LNCCLNQ.DATA.AMT;
        LNCSHSQ.COMM_INFO.CTA_INFO.LON_BAL = LNCCLNQ.DATA.TOT_BAL;
        LNCSHSQ.COMM_INFO.CTA_INFO.PROD_DE = LNCCLNQ.DATA.PROD_NAME;
    }
    public void B200_GET_TRAN_INF() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        WS_COND_FLG.START_FLG = ' ';
        IBS.init(SCCGWA, WS_VARIABLES.WS_HISTORY_DETAIL);
        WS_COND_FLG.START_FLG = 'Y';
        WS_COND_FLG.FOUND_FLG = 'N';
        R000_STARTBR_LNTTRAN_PROC();
        if (pgmRtn) return;
        T01_READNEXT_LNTTRAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COND_FLG.FOUND_FLG);
        if (WS_COND_FLG.FOUND_FLG != 'N') {
            WS_VARIABLES.IDX = 0;
            WS_VARIABLES.TOTAL_NUM = 0;
            while (WS_COND_FLG.FOUND_FLG != 'N') {
                if (LNRTRAN.TR_VAL_DTE != WS_VARIABLES.WS_HISTORY_DETAIL.TR_VALDT 
                    || LNRTRAN.KEY.TR_DATE != WS_VARIABLES.WS_HISTORY_DETAIL.TR_DTE 
                    || LNRTRAN.KEY.TR_JRN_NO != WS_VARIABLES.WS_HISTORY_DETAIL.TR_JRNNO 
                    || LNRTRAN.DUE_DT != WS_VARIABLES.WS_HISTORY_DETAIL.DUE_DT 
                    || LNRTRAN.KEY.TXN_TERM != WS_VARIABLES.WS_HISTORY_DETAIL.TXN_TERM) {
                    if (WS_COND_FLG.START_FLG == 'Y') {
                        WS_VARIABLES.TOTAL_NUM += 1;
                        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                        CEP.TRC(SCCGWA, "111");
                        WS_COND_FLG.START_FLG = 'N';
                    } else {
                        R000_TRANS_DATA_OUTPUT();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, WS_VARIABLES.WS_HISTORY_DETAIL);
                        WS_VARIABLES.TOTAL_NUM += 1;
                        CEP.TRC(SCCGWA, "222");
                        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                    }
                    if (LNRTRAN.TRAN_STS == 'N') {
                        WS_VARIABLES.WS_HISTORY_DETAIL.DEL_FLG = 'N';
                    } else {
                        WS_VARIABLES.WS_HISTORY_DETAIL.DEL_FLG = 'Y';
                    }
                }
                B210_TERM_TRAN_PROC();
                if (pgmRtn) return;
                T01_READNEXT_LNTTRAN();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
            WS_VARIABLES.BAL_CNT = WS_VARIABLES.TOTAL_NUM % WS_VARIABLES.PAGE_ROW;
            WS_VARIABLES.TOTAL_PAGE = (short) ((WS_VARIABLES.TOTAL_NUM - WS_VARIABLES.BAL_CNT) / WS_VARIABLES.PAGE_ROW);
            if (WS_VARIABLES.BAL_CNT != 0) {
                WS_VARIABLES.TOTAL_PAGE += 1;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.CURR_PAGE);
            CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_PAGE);
            if (WS_VARIABLES.CURR_PAGE == WS_VARIABLES.TOTAL_PAGE) {
                WS_VARIABLES.LAST_PAGE = 'Y';
                if (WS_VARIABLES.BAL_CNT != 0) {
                    WS_VARIABLES.PAGE_ROW = (short) WS_VARIABLES.BAL_CNT;
                }
            }
            if (WS_COND_FLG.START_FLG != 'Y' 
                && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
                R000_TRANS_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "333");
            WS_VARIABLES.TOTAL_PAGE = 1;
            WS_VARIABLES.TOTAL_NUM = 0;
            WS_VARIABLES.LAST_PAGE = 'Y';
            WS_VARIABLES.PAGE_ROW = 0;
        }
        T01_ENDBR_LNTTRAN();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        R000_OUTPUT_WRITE();
        if (pgmRtn) return;
        if (WS_COND_FLG.START_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_NO_TXNHIS, LNCSHSQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_TERM_TRAN_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_HISTORY_DETAIL.TR_VALDT = LNRTRAN.TR_VAL_DTE;
        WS_VARIABLES.WS_HISTORY_DETAIL.TR_DTE = LNRTRAN.KEY.TR_DATE;
        WS_VARIABLES.WS_HISTORY_DETAIL.TR_JRNNO = LNRTRAN.KEY.TR_JRN_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_HISTORY_DETAIL.TR_JRNNO);
        WS_VARIABLES.WS_HISTORY_DETAIL.DUE_DT = LNRTRAN.DUE_DT;
        WS_VARIABLES.WS_HISTORY_DETAIL.TXN_TERM = LNRTRAN.KEY.TXN_TERM;
        if (LNRTRAN.TR_CODE == TR_CODE_FKC 
            || LNRTRAN.TR_CODE == TR_CODE_FKR) {
            WS_VARIABLES.WS_HISTORY_DETAIL.I_AMT += LNRTRAN.I_AMT;
        } else {
            WS_VARIABLES.WS_HISTORY_DETAIL.P_AMT += LNRTRAN.P_AMT;
            WS_VARIABLES.WS_HISTORY_DETAIL.I_AMT += LNRTRAN.I_AMT;
            WS_VARIABLES.WS_HISTORY_DETAIL.O_AMT += LNRTRAN.O_AMT;
            WS_VARIABLES.WS_HISTORY_DETAIL.L_AMT += LNRTRAN.L_AMT;
            WS_VARIABLES.WS_HISTORY_DETAIL.WO_AMT += LNRTRAN.O_WAV_AMT;
            WS_VARIABLES.WS_HISTORY_DETAIL.WL_AMT += LNRTRAN.L_WAV_AMT;
            WS_VARIABLES.WS_HISTORY_DETAIL.PC_AMT += LNRTRAN.PREPAY_CHG;
            WS_VARIABLES.WS_HISTORY_DETAIL.F_AMT = LNRTRAN.F_AMT;
        }
        WS_VARIABLES.WS_HISTORY_DETAIL.OS_BAL = LNRTRAN.OS_BAL;
        WS_VARIABLES.WS_HISTORY_DETAIL.INT_RAT = LNRTRAN.ALL_IN_RATE;
        WS_VARIABLES.WS_HISTORY_DETAIL.REQ_FRM_NO = LNRTRAN.REQ_FRM_NO;
        WS_VARIABLES.WS_HISTORY_DETAIL.TXN_TYP = LNRTRAN.KEY.TXN_TYP;
        WS_VARIABLES.WS_HISTORY_DETAIL.TR_BR = LNRTRAN.TR_BR;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[1-1].AC_FLG = LNRTRAN.AC_FLG1;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[1-1].AC_TYP = LNRTRAN.AC_TYP1;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[1-1].PAY_AC = LNRTRAN.PAY_AC1;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[2-1].AC_FLG = LNRTRAN.AC_FLG2;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[2-1].AC_TYP = LNRTRAN.AC_TYP2;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[2-1].PAY_AC = LNRTRAN.PAY_AC2;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[3-1].AC_FLG = LNRTRAN.AC_FLG3;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[3-1].AC_TYP = LNRTRAN.AC_TYP3;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[3-1].PAY_AC = LNRTRAN.PAY_AC3;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[4-1].AC_FLG = LNRTRAN.AC_FLG4;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[4-1].AC_TYP = LNRTRAN.AC_TYP4;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[4-1].PAY_AC = LNRTRAN.PAY_AC4;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[5-1].AC_FLG = LNRTRAN.AC_FLG5;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[5-1].AC_TYP = LNRTRAN.AC_TYP5;
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[5-1].PAY_AC = LNRTRAN.PAY_AC5;
        for (WS_VARIABLES.X = 1; WS_VARIABLES.X <= 3 
            && WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC.trim().length() != 0; WS_VARIABLES.X += 1) {
            if (WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].AC_TYP.equalsIgnoreCase("01")
                || WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].AC_TYP.equalsIgnoreCase("04")
                || WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].AC_TYP.equalsIgnoreCase("05")
                || WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].AC_TYP.equalsIgnoreCase("06")) {
                B910_GET_DD_INFO();
                if (pgmRtn) return;
            } else if (WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].AC_TYP.equalsIgnoreCase("02")) {
                B920_GET_SUSP_AC_INFO();
                if (pgmRtn) return;
            } else if (WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].AC_TYP.equalsIgnoreCase("03")) {
                B930_GET_NOS_AC_INFO();
                if (pgmRtn) return;
            } else {
            }
        }
    }
    public void B910_GET_DD_INFO() throws IOException,SQLException,Exception {
        WS_VARIABLES.AC_NO = WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_VARIABLES.AC_NO;
        CICACCU.DATA.ENTY_TYP = '1';
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        if (CICACCU.DATA.AGR_NO.trim().length() > 0) {
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
        }
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC_NAM = CICACCU.DATA.CI_CNM;
    }
    public void B920_GET_SUSP_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CHS_NM);
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC_NAM = AICPQMIB.OUTPUT_DATA.CHS_NM;
    }
    public void B930_GET_NOS_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.NOSTRO_CD = WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC;
        IBCQINF.INPUT_DATA.CCY = LNCCLNQ.DATA.CCY;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_CHN_NAME);
        WS_VARIABLES.WS_HISTORY_DETAIL.WS_PAY_AC_INFO[WS_VARIABLES.X-1].PAY_AC_NAM = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TOTAL_NUM >= WS_VARIABLES.NEXT_START_NUM 
            && WS_VARIABLES.IDX < WS_VARIABLES.PAGE_ROW) {
            WS_VARIABLES.IDX += 1;
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1]);
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_DEL_FLG = WS_VARIABLES.WS_HISTORY_DETAIL.DEL_FLG;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_VALDT = WS_VARIABLES.WS_HISTORY_DETAIL.TR_VALDT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_DTE = WS_VARIABLES.WS_HISTORY_DETAIL.TR_DTE;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_JRNNO = WS_VARIABLES.WS_HISTORY_DETAIL.TR_JRNNO;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_JRNNO);
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_DUE_DT = WS_VARIABLES.WS_HISTORY_DETAIL.DUE_DT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TXN_TERM = WS_VARIABLES.WS_HISTORY_DETAIL.TXN_TERM;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_P_AMT = WS_VARIABLES.WS_HISTORY_DETAIL.P_AMT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_I_AMT = WS_VARIABLES.WS_HISTORY_DETAIL.I_AMT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_O_AMT = WS_VARIABLES.WS_HISTORY_DETAIL.O_AMT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_L_AMT = WS_VARIABLES.WS_HISTORY_DETAIL.L_AMT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_PC_AMT = WS_VARIABLES.WS_HISTORY_DETAIL.PC_AMT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_F_AMT = WS_VARIABLES.WS_HISTORY_DETAIL.F_AMT;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_OS_BAL = WS_VARIABLES.WS_HISTORY_DETAIL.OS_BAL;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_REQ_FRM_NO = WS_VARIABLES.WS_HISTORY_DETAIL.REQ_FRM_NO;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TXN_TYP = WS_VARIABLES.WS_HISTORY_DETAIL.TXN_TYP;
            WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_BR = WS_VARIABLES.WS_HISTORY_DETAIL.TR_BR;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_DEL_FLG);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_VALDT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_DTE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TR_JRNNO);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_DUE_DT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_P_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_I_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_O_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_L_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_PC_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_F_AMT);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_OS_BAL);
        }
    }
    public void R000_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        CEP.TRC(SCCGWA, "20180524TEST");
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        SCCFMT.DATA_LEN = 1679;
        CEP.TRC(SCCGWA, "20180524TEST-01");
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_STARTBR_LNTTRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO);
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.CTA_INFO.CTA_SEQ);
        LNRTRAN.KEY.CONTRACT_NO = LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO;
        if (LNCSHSQ.COMM_INFO.CTA_INFO.CTA_SEQ.trim().length() == 0) LNRTRAN.KEY.SUB_CTA_NO = 0;
        else LNRTRAN.KEY.SUB_CTA_NO = Integer.parseInt(LNCSHSQ.COMM_INFO.CTA_INFO.CTA_SEQ);
        WS_DB_VARS.TR_CODE_RPN = TR_CODE_RPN;
        WS_DB_VARS.TR_CODE_ERP = TR_CODE_ERP;
        WS_DB_VARS.TR_CODE_RPN9 = TR_CODE_RPN9;
        WS_DB_VARS.TR_CODE_ERP9 = TR_CODE_ERP9;
        WS_DB_VARS.TR_CODE_DPO = TR_CODE_DPO;
        WS_DB_VARS.TR_CODE_FKC = TR_CODE_FKC;
        WS_DB_VARS.TR_CODE_FKR = TR_CODE_FKR;
        CEP.TRC(SCCGWA, WS_COND_FLG.TXN_ENQ_TYP);
        if (WS_COND_FLG.TXN_ENQ_TYP == '2') {
            T01_STARTBR_LNTTRAN_TO();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.TXN_ENQ_TYP == '3') {
            T01_STARTBR_LNTTRAN_FR();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.TXN_ENQ_TYP == '4') {
            T01_STARTBR_LNTTRAN_DTD();
            if (pgmRtn) return;
        } else {
            T01_STARTBR_LNTTRAN_ALL();
            if (pgmRtn) return;
        }
    }
    public void T01_STARTBR_LNTTRAN_ALL() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TRAN_FLG = 'C' "
            + "AND ( ( ( TR_CODE = 4000 "
            + "OR TR_CODE = 4009 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 "
            + "OR TR_CODE = 4700 ) "
            + "AND ( TXN_TYP = 'P' "
            + "OR TXN_TYP = 'I' "
            + "OR TXN_TYP = 'C' ) "
            + "AND REC_FLAG = ' ' ) "
            + "OR ( ( TR_CODE = 4100 "
            + "OR TR_CODE = 4109 ) "
            + "AND REC_FLAG = 'B' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE, TR_SEQ, ACTL_DATE, ACTL_TIME, TR_JRN_NO, DUE_DT, TXN_TYP";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T01_STARTBR_LNTTRAN_DTD() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TR_DATE >= :WS_DB_VARS.BEG_DTE "
            + "AND TR_DATE <= :WS_DB_VARS.END_DTE "
            + "AND TRAN_FLG = 'C' "
            + "AND ( ( ( TR_CODE = 4000 "
            + "OR TR_CODE = 4009 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 "
            + "OR TR_CODE = 4700 ) "
            + "AND ( TXN_TYP = 'P' "
            + "OR TXN_TYP = 'I' "
            + "OR TXN_TYP = 'C' ) "
            + "AND REC_FLAG = ' ' ) "
            + "OR ( ( TR_CODE = 4100 "
            + "OR TR_CODE = 4109 ) "
            + "AND REC_FLAG = 'B' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE, TR_SEQ, ACTL_DATE, ACTL_TIME, TR_JRN_NO, DUE_DT, TXN_TYP";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T01_STARTBR_LNTTRAN_TO() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TR_DATE <= :WS_DB_VARS.END_DTE "
            + "AND TRAN_FLG = 'C' "
            + "AND ( ( ( TR_CODE = 4000 "
            + "OR TR_CODE = 4009 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 "
            + "OR TR_CODE = 4700 ) "
            + "AND ( TXN_TYP = 'P' "
            + "OR TXN_TYP = 'I' "
            + "OR TXN_TYP = 'C' ) "
            + "AND REC_FLAG = ' ' ) "
            + "OR ( ( TR_CODE = 4100 "
            + "OR TR_CODE = 4109 ) "
            + "AND REC_FLAG = 'B' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE, TR_SEQ, ACTL_DATE, ACTL_TIME, TR_JRN_NO, DUE_DT, TXN_TYP";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T01_STARTBR_LNTTRAN_FR() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TR_DATE >= :WS_DB_VARS.BEG_DTE "
            + "AND TRAN_FLG = 'C' "
            + "AND ( ( ( TR_CODE = 4000 "
            + "OR TR_CODE = 4009 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 "
            + "OR TR_CODE = 4700 ) "
            + "AND ( TXN_TYP = 'P' "
            + "OR TXN_TYP = 'I' "
            + "OR TXN_TYP = 'C' ) ) "
            + "OR ( ( TR_CODE = 4100 "
            + "OR TR_CODE = 4109 ) "
            + "AND REC_FLAG = 'B' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE, TR_SEQ, ACTL_DATE, ACTL_TIME, TR_JRN_NO, DUE_DT, TXN_TYP";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T01_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            WS_COND_FLG.FOUND_FLG = 'N';
        }
    }
    public void T01_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSHSQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSHSQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSHSQ=");
            CEP.TRC(SCCGWA, LNCSHSQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
