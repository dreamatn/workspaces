package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICUT {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm LNTINTA_BR = new brParm();
    DBParm LNTINTA_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTIRUL_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_ITEM_CNT = 50;
    short WS_NO2 = 02;
    String K_INT_DB_30E = "05";
    char K_INSTLMENT = '4';
    char K_MONTH = 'M';
    int WS_START_DATE = 0;
    int WS_MAT_DATE = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_JJJ = 0;
    short WS_KKK = 0;
    short WS_LLL = 0;
    short WS_YYY = 0;
    short WS_ZZZ = 0;
    short WS_TERM_DAYS = 0;
    int WS_DATE = 0;
    long WS_LOW_CCY_INT_AMT = 0;
    double WS_LOW_CCY_INT_AMT1 = 0;
    double WS_LOW_CCY_INT_AMT2 = 0;
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    int WS_INI_BEG_DATE = 0;
    int WS_INI_END_DATE = 0;
    int WS_TERM_BEG_DATE = 0;
    int WS_TERM_END_DATE = 0;
    int WS_TERM_DB = 0;
    int WS_TERM_DE = 0;
    int WS_F_TERM_FULL_DT = 0;
    int WS_F_TERM_FULL_DT1 = 0;
    short WS_TOT_TERM = 0;
    double WS_INST_AMT = 0;
    double WS_BAL = 0;
    LNZICUT_WS_IRUL_KEY WS_IRUL_KEY = new LNZICUT_WS_IRUL_KEY();
    String WS_DATE1 = " ";
    short WS_DATE1_YE = 0;
    short WS_DATE1_MO = 0;
    short WS_DATE1_DA = 0;
    String WS_DATE2 = " ";
    short WS_DATE2_YE = 0;
    short WS_DATE2_MO = 0;
    short WS_DATE2_DA = 0;
    short WS_MONTH_NO = 0;
    String WS_DATE3 = " ";
    String WS_DATE3_MODA = " ";
    String WS_DATE4 = " ";
    String WS_DATE4_MODA = " ";
    short WS_DAY_COOL = 0;
    int WS_YYYYMMDD2 = 0;
    LNZICUT_REDEFINES48 REDEFINES48 = new LNZICUT_REDEFINES48();
    int WS_YYYYMMDD1 = 0;
    LNZICUT_REDEFINES53 REDEFINES53 = new LNZICUT_REDEFINES53();
    short WS_APRD_PAY_DAY = 0;
    int WS_OUT_DATE = 0;
    int WS_KEY_LEN = 0;
    double WS_CTNR_AMT = 0;
    double WS_CTNR_INT = 0;
    double WS_ICUT_INT_AMT = 0;
    double WS_ICUT_SPE_INT = 0;
    double WS_PYIF_SPE_AMT = 0;
    double WS_ADJ_INT = 0;
    int WS_ADJ_VAL_DT = 0;
    double WS_ADJ_INT_AMT = 0;
    double WS_ICUT_ADJ_AMT = 0;
    double WS_PYIF_INT_AMT = 0;
    double WS_PYIF_INT_AMT1 = 0;
    double WS_ICUT_AMT = 0;
    short WS_NO = 0;
    LNZICUT_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZICUT_WS_LOAN_CONT_AREA();
    LNZICUT_WS_FROM_NOS[] WS_FROM_NOS = new LNZICUT_WS_FROM_NOS[10];
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_INTA_FOUND_FLG = ' ';
    char WS_F_TERM_STS = ' ';
    char WS_L_TERM_FULL = ' ';
    char WS_L_TERM_EQUL = ' ';
    char WS_BASE_30E = ' ';
    char WS_L_F_EQUAL = ' ';
    char WS_CHANGE_FLG = ' ';
    LNRINTA LNRINTA = new LNRINTA();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCINTDM LNCINTDM = new LNCINTDM();
    LNCIRULB LNCIRULB = new LNCIRULB();
    LNCHGRD LNCHGRD = new LNCHGRD();
    LNCRATB LNCRATB = new LNCRATB();
    LNCBALB LNCBALB = new LNCBALB();
    LNCINCM LNCINCM = new LNCINCM();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    BPCIDAY BPCIDAY = new BPCIDAY();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNRIRUL LNRIRUL = new LNRIRUL();
    SCCGWA SCCGWA;
    LNCICUT LNCICUT;
    public LNZICUT() {
        for (int i=0;i<10;i++) WS_FROM_NOS[i] = new LNZICUT_WS_FROM_NOS();
    }
    public void MP(SCCGWA SCCGWA, LNCICUT LNCICUT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICUT = LNCICUT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICUT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICUT.RC.RC_APP = "LN";
        LNCICUT.RC.RC_RTNCODE = 0;
        WS_KEY_LEN = 10;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.AMT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_PRE_CAL_PROC();
        if (pgmRtn) return;
        WS_INI_BEG_DATE = LNCICUT.COMM_DATA.BEG_DATE;
        WS_INI_END_DATE = LNCICUT.COMM_DATA.END_DATE;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_VAL_DT);
        CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_DUE_DT);
        WS_ICUT_SPE_INT = 0;
        WS_ICUT_INT_AMT = 0;
        WS_CTNR_AMT = 0;
        WS_ADJ_INT = LNCICUT.COMM_DATA.ADJ_INT;
        if (LNCLOANM.REC_DATA.STOP_VAL_DT == 0 
            || LNCLOANM.REC_DATA.STOP_VAL_DT == LNCLOANM.REC_DATA.STOP_DUE_DT) {
            B100_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            if (WS_INI_BEG_DATE < LNCLOANM.REC_DATA.STOP_VAL_DT) {
                if (WS_INI_END_DATE <= LNCLOANM.REC_DATA.STOP_VAL_DT) {
                    CEP.TRC(SCCGWA, "YCHSTOP001");
                    LNCICUT.COMM_DATA.BEG_DATE = WS_INI_BEG_DATE;
                    LNCICUT.COMM_DATA.END_DATE = WS_INI_END_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
                if ((WS_INI_END_DATE > LNCLOANM.REC_DATA.STOP_VAL_DT 
                    && WS_INI_END_DATE <= LNCLOANM.REC_DATA.STOP_DUE_DT 
                    && LNCLOANM.REC_DATA.STOP_DUE_DT != 0) 
                    || (WS_INI_END_DATE > LNCLOANM.REC_DATA.STOP_VAL_DT 
                    && LNCLOANM.REC_DATA.STOP_DUE_DT == 0)) {
                    CEP.TRC(SCCGWA, "YCHSTOP002");
                    LNCICUT.COMM_DATA.BEG_DATE = WS_INI_BEG_DATE;
                    LNCICUT.COMM_DATA.END_DATE = LNCLOANM.REC_DATA.STOP_VAL_DT;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
                if (WS_INI_END_DATE > LNCLOANM.REC_DATA.STOP_DUE_DT 
                    && LNCLOANM.REC_DATA.STOP_DUE_DT != 0) {
                    CEP.TRC(SCCGWA, "YCHSTOP003");
                    LNCICUT.COMM_DATA.BEG_DATE = WS_INI_BEG_DATE;
                    LNCICUT.COMM_DATA.END_DATE = LNCLOANM.REC_DATA.STOP_VAL_DT;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                    LNCICUT.COMM_DATA.BEG_DATE = LNCLOANM.REC_DATA.STOP_DUE_DT;
                    LNCICUT.COMM_DATA.END_DATE = WS_INI_END_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_INI_BEG_DATE >= LNCLOANM.REC_DATA.STOP_VAL_DT 
                && WS_INI_BEG_DATE < LNCLOANM.REC_DATA.STOP_DUE_DT) {
                if ((WS_INI_END_DATE <= LNCLOANM.REC_DATA.STOP_DUE_DT 
                    && LNCLOANM.REC_DATA.STOP_DUE_DT != 0) 
                    || LNCLOANM.REC_DATA.STOP_DUE_DT == 0) {
                    CEP.TRC(SCCGWA, "YCHSTOP004");
                    LNCICUT.COMM_DATA.INT_AMT = 0;
                    LNCICUT.COMM_DATA.ADJ_INT = 0;
                    LNCICUT.COMM_DATA.SPE_INT = 0;
                }
                if (WS_INI_END_DATE > LNCLOANM.REC_DATA.STOP_DUE_DT 
                    && LNCLOANM.REC_DATA.STOP_DUE_DT != 0) {
                    CEP.TRC(SCCGWA, "YCHSTOP005");
                    LNCICUT.COMM_DATA.BEG_DATE = LNCLOANM.REC_DATA.STOP_DUE_DT;
                    LNCICUT.COMM_DATA.END_DATE = WS_INI_END_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_INI_BEG_DATE >= LNCLOANM.REC_DATA.STOP_DUE_DT 
                && LNCLOANM.REC_DATA.STOP_DUE_DT != 0) {
                CEP.TRC(SCCGWA, "YCHSTOP006");
                LNCICUT.COMM_DATA.BEG_DATE = WS_INI_BEG_DATE;
                LNCICUT.COMM_DATA.END_DATE = WS_INI_END_DATE;
                B100_MAIN_PROC();
                if (pgmRtn) return;
            }
        }
        B200_ADD_ADJUST_INT();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCICUT.COMM_DATA.FUNC_CODE != 'I' 
            && LNCICUT.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICUT.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_PRE_CAL_PROC() throws IOException,SQLException,Exception {
        B110_GET_LOAN_INF();
        if (pgmRtn) return;
        B120_GET_PPMQ_INF();
        if (pgmRtn) return;
        B130_GET_ICTL_INF();
        if (pgmRtn) return;
        B140_ICUT_DATE_PRC();
        if (pgmRtn) return;
        WS_ICUT_AMT = LNCICUT.COMM_DATA.AMT;
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        WS_APRD_PAY_DAY = LNRAPRD.PAY_DAY;
        CEP.TRC(SCCGWA, LNRAPRD.INT_DBAS_STD);
        CEP.TRC(SCCGWA, LNRAPRD.PAY_DAY);
        CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
        CEP.TRC(SCCGWA, LNRAPRD.CAL_PERD_UNIT);
        CEP.TRC(SCCGWA, "APRD-CAL");
    }
    public void B110_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCCONTM.REC_DATA.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
    }
    public void B120_GET_PPMQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
    }
    public void B130_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        if (LNCICUT.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICUT.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
    }
    public void B140_ICUT_DATE_PRC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        if (LNCICUT.COMM_DATA.BEG_DATE == 0) {
            LNCICUT.COMM_DATA.BEG_DATE = LNCICTLM.REC_DATA.INT_CUT_DT;
        }
        if (LNCICUT.COMM_DATA.END_DATE == 0) {
            LNCICUT.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (LNCICUT.COMM_DATA.END_DATE > LNCCONTM.REC_DATA.MAT_DATE) {
            LNCICUT.COMM_DATA.END_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        }
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
        if ((LNCICUT.COMM_DATA.BEG_DATE > LNCICUT.COMM_DATA.END_DATE)) {
            CEP.TRC(SCCGWA, "YCH111");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICUT.COMM_DATA.FUNC_CODE != 'I' 
            && LNCICTLM.REC_DATA.IC_CAL_DUE_DT != 0 
            && LNCICUT.COMM_DATA.END_DATE > LNCICTLM.REC_DATA.IC_CAL_DUE_DT) {
            CEP.TRC(SCCGWA, "YCH222");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICUT.COMM_DATA.FUNC_CODE != 'I' 
            && LNCICUT.COMM_DATA.BEG_DATE < LNCICTLM.REC_DATA.INT_CUT_DT) {
            CEP.TRC(SCCGWA, "YCH333");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICUT.COMM_DATA.BEG_DATE == LNCICUT.COMM_DATA.END_DATE) {
            CEP.TRC(SCCGWA, "LX111");
            LNCICUT.COMM_DATA.INT_AMT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_BRW_IRUL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRIRUL);
        WS_IRUL_KEY.WS_IRUL_CODE = "TERMLON";
        LNRIRUL.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        LNRIRUL.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_IRUL_KEY.WS_IRUL_TYPE = LNCICUT.COMM_DATA.TYPE;
        WS_IRUL_KEY.WS_IRUL_SEQ = 1;
        LNRIRUL.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_IRUL_KEY);
        CEP.TRC(SCCGWA, LNRIRUL.KEY.CODE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TYPE);
        T000_READ_LNTIRUL();
        if (pgmRtn) return;
        WS_FROM_NOS[1-1].WS_FROM_NO = LNRIRUL.FROM_NO01;
        WS_FROM_NOS[2-1].WS_FROM_NO = LNRIRUL.FROM_NO02;
        WS_FROM_NOS[3-1].WS_FROM_NO = LNRIRUL.FROM_NO03;
        WS_FROM_NOS[4-1].WS_FROM_NO = LNRIRUL.FROM_NO04;
        WS_FROM_NOS[5-1].WS_FROM_NO = LNRIRUL.FROM_NO05;
        WS_FROM_NOS[6-1].WS_FROM_NO = LNRIRUL.FROM_NO06;
        WS_FROM_NOS[7-1].WS_FROM_NO = LNRIRUL.FROM_NO07;
        WS_FROM_NOS[8-1].WS_FROM_NO = LNRIRUL.FROM_NO08;
        WS_FROM_NOS[9-1].WS_FROM_NO = LNRIRUL.FROM_NO09;
        WS_FROM_NOS[10-1].WS_FROM_NO = LNRIRUL.FROM_NO10;
        CEP.TRC(SCCGWA, LNRIRUL.RATE_TYP);
        CEP.TRC(SCCGWA, LNCIRULB.REC_CNT);
    }
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
        B100_BRW_IRUL_INF();
        if (pgmRtn) return;
        if ("1".trim().length() == 0) WS_IDX = 0;
        else WS_IDX = Short.parseShort("1");
        if (LNCICUT.COMM_DATA.AMT != 0) {
            WS_BAL = LNCICUT.COMM_DATA.AMT;
            WS_BEG_DATE = LNCICUT.COMM_DATA.BEG_DATE;
            WS_END_DATE = LNCICUT.COMM_DATA.END_DATE;
            if (LNRAPRD.PAY_MTH == K_INSTLMENT 
                && LNRAPRD.CAL_PERD_UNIT == K_MONTH 
                && LNCCONTM.REC_DATA.CCY.equalsIgnoreCase("156") 
                && LNCSCKPD.PRODMO.equalsIgnoreCase("R")) {
                B200_DIVIDE_BY_MONTH();
                if (pgmRtn) return;
            } else {
                WS_TERM_DB = WS_BEG_DATE;
                WS_TERM_DE = WS_END_DATE;
                B200_CINT_BASE_IRULBAL();
                if (pgmRtn) return;
            }
        } else {
            for (WS_KKK = 1; WS_KKK <= 10; WS_KKK += 1) {
                if (WS_FROM_NOS[WS_KKK-1].WS_FROM_NO != 0) {
                    B100_BRW_BALH_INF();
                    if (pgmRtn) return;
                    for (WS_LLL = 1; WS_LLL <= LNCBALB.COMM_DATA.ITEM_CNT; WS_LLL += 1) {
                        WS_BAL = LNCBALB.COMM_DATA.IETM_RATES[WS_LLL-1].BAL;
                        WS_BEG_DATE = LNCBALB.COMM_DATA.IETM_RATES[WS_LLL-1].B_DATE;
                        WS_END_DATE = LNCBALB.COMM_DATA.IETM_RATES[WS_LLL-1].E_DATE;
                        CEP.TRC(SCCGWA, "156RRRR");
                        CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
                        CEP.TRC(SCCGWA, LNRAPRD.CAL_PERD_UNIT);
                        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CCY);
                        CEP.TRC(SCCGWA, LNCSCKPD.PRODMO);
                        CEP.TRC(SCCGWA, LNCSCKPD.PROD_MOD);
                        if (LNRAPRD.PAY_MTH == K_INSTLMENT 
                            && LNRAPRD.CAL_PERD_UNIT == K_MONTH 
                            && LNCCONTM.REC_DATA.CCY.equalsIgnoreCase("156") 
                            && LNCSCKPD.PROD_MOD == 'R') {
                            CEP.TRC(SCCGWA, "156RRRRrrrr");
                            B200_DIVIDE_BY_MONTH();
                            if (pgmRtn) return;
                        } else {
                            WS_TERM_DB = WS_BEG_DATE;
                            WS_TERM_DE = WS_END_DATE;
                            B200_CINT_BASE_IRULBAL();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, "20180714 444");
                        }
                    }
                }
            }
        }
        LNCICUT.COMM_DATA.AMT = WS_BAL;
        LNCICUT.COMM_DATA.RATE = LNCRATB.COMM_DATA.AVER_RATE;
        CEP.TRC(SCCGWA, "20180714 111");
        CEP.TRC(SCCGWA, WS_ICUT_SPE_INT);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        LNCICUT.COMM_DATA.INT_AMT = WS_ICUT_SPE_INT + 0;
        bigD = new BigDecimal(LNCICUT.COMM_DATA.INT_AMT);
        LNCICUT.COMM_DATA.INT_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, "20180714 222");
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_LOW_CCY_INT_AMT = LNCICUT.COMM_DATA.INT_AMT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT);
            WS_LOW_CCY_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            LNCICUT.COMM_DATA.INT_AMT = WS_LOW_CCY_INT_AMT;
        }
        if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_LOW_CCY_INT_AMT1 = LNCICUT.COMM_DATA.INT_AMT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT1);
            WS_LOW_CCY_INT_AMT1 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            LNCICUT.COMM_DATA.INT_AMT = WS_LOW_CCY_INT_AMT1;
        }
        if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_LOW_CCY_INT_AMT2 = LNCICUT.COMM_DATA.INT_AMT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT2);
            WS_LOW_CCY_INT_AMT2 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            LNCICUT.COMM_DATA.INT_AMT = WS_LOW_CCY_INT_AMT2;
        }
        CEP.TRC(SCCGWA, "20180714 333");
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        LNCICUT.COMM_DATA.SPE_INT = WS_ICUT_SPE_INT;
    }
    public void B200_ADD_ADJUST_INT() throws IOException,SQLException,Exception {
        T00_READ_LNTINTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INTA_FOUND_FLG);
        if (WS_INTA_FOUND_FLG == 'Y' 
            && WS_ICUT_AMT >= 0) {
            B400_ADJ_INT_PROCESS();
            if (pgmRtn) return;
            WS_ICUT_ADJ_AMT = LNCICUT.COMM_DATA.INT_AMT + WS_ADJ_INT_AMT;
            if (WS_ICUT_ADJ_AMT < 0) {
                if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NOT_LESS_ZERO, LNCICUT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    LNCICUT.COMM_DATA.INT_AMT = 0;
                }
            } else {
                LNCICUT.COMM_DATA.INT_AMT = WS_ICUT_ADJ_AMT;
            }
            WS_PYIF_SPE_AMT = LNCICUT.COMM_DATA.SPE_INT + WS_ADJ_INT_AMT;
            if (WS_PYIF_SPE_AMT < 0) {
                LNCICUT.COMM_DATA.SPE_INT = 0;
            } else {
                LNCICUT.COMM_DATA.SPE_INT = WS_PYIF_SPE_AMT;
            }
        }
        if (WS_PYIF_INT_AMT != 0) {
            WS_PYIF_INT_AMT1 = LNCICUT.COMM_DATA.INT_AMT - WS_PYIF_INT_AMT;
            if (WS_PYIF_INT_AMT1 < 0) {
                LNCICUT.COMM_DATA.INT_AMT = 0;
            } else {
                LNCICUT.COMM_DATA.INT_AMT = WS_PYIF_INT_AMT1;
            }
            WS_PYIF_SPE_AMT = LNCICUT.COMM_DATA.SPE_INT - WS_PYIF_INT_AMT;
            if (WS_PYIF_SPE_AMT < 0) {
                LNCICUT.COMM_DATA.SPE_INT = 0;
            } else {
                LNCICUT.COMM_DATA.SPE_INT = WS_PYIF_SPE_AMT;
            }
        }
    }
    public void B200_DIVIDE_BY_MONTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BEG_DATE);
        WS_F_TERM_FULL_DT1 = WS_BEG_DATE;
        for (WS_ZZZ = 1; WS_F_TERM_FULL_DT1 < WS_END_DATE; WS_ZZZ += 1) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_F_TERM_FULL_DT1;
            WS_F_TERM_FULL_DT = WS_F_TERM_FULL_DT1;
            WS_YYYYMMDD1 = WS_F_TERM_FULL_DT1;
            IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD1+"", REDEFINES53);
            SCCCLDT.MTHS = 1;
            R00_CAL_DATE();
            if (pgmRtn) return;
            WS_YYYYMMDD2 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD2+"", REDEFINES48);
            CEP.TRC(SCCGWA, WS_YYYYMMDD1);
            CEP.TRC(SCCGWA, WS_YYYYMMDD2);
            CEP.TRC(SCCGWA, WS_APRD_PAY_DAY);
            WS_CHANGE_FLG = 'N';
            if ((REDEFINES53.WS_MM1 == 1 
                && REDEFINES53.WS_DD1 == 31 
                || REDEFINES53.WS_MM1 == 3 
                && REDEFINES53.WS_DD1 == 31 
                || REDEFINES53.WS_MM1 == 4 
                && REDEFINES53.WS_DD1 == 30 
                || REDEFINES53.WS_MM1 == 5 
                && REDEFINES53.WS_DD1 == 31 
                || REDEFINES53.WS_MM1 == 6 
                && REDEFINES53.WS_DD1 == 30 
                || REDEFINES53.WS_MM1 == 7 
                && REDEFINES53.WS_DD1 == 31 
                || REDEFINES53.WS_MM1 == 8 
                && REDEFINES53.WS_DD1 == 31 
                || REDEFINES53.WS_MM1 == 9 
                && REDEFINES53.WS_DD1 == 30 
                || REDEFINES53.WS_MM1 == 10 
                && REDEFINES53.WS_DD1 == 31 
                || REDEFINES53.WS_MM1 == 11 
                && REDEFINES53.WS_DD1 == 30 
                || REDEFINES53.WS_MM1 == 12 
                && REDEFINES53.WS_DD1 == 31) 
                && REDEFINES48.WS_DD2 < WS_APRD_PAY_DAY 
                && WS_APRD_PAY_DAY > 28) {
                CEP.TRC(SCCGWA, "YCHC1");
                WS_CHANGE_FLG = 'Y';
            }
            if (REDEFINES53.WS_MM1 == 2 
                && REDEFINES48.WS_DD2 < WS_APRD_PAY_DAY 
                && WS_APRD_PAY_DAY > 28) {
                if (REDEFINES53.WS_YYYY1 % 100 == 0) {
                    if (REDEFINES53.WS_YYYY1 % 400 == 0) {
                        if (REDEFINES53.WS_DD1 == 29) {
                            CEP.TRC(SCCGWA, "YCHC3");
                            WS_CHANGE_FLG = 'Y';
                        }
                    } else {
                        if (REDEFINES53.WS_DD1 == 28) {
                            CEP.TRC(SCCGWA, "YCHC4");
                            WS_CHANGE_FLG = 'Y';
                        }
                    }
                } else {
                    if (REDEFINES53.WS_YYYY1 % 4 == 0) {
                        if (REDEFINES53.WS_DD1 == 29) {
                            CEP.TRC(SCCGWA, "YCHC5");
                            WS_CHANGE_FLG = 'Y';
                        }
                    } else {
                        if (REDEFINES53.WS_DD1 == 28) {
                            CEP.TRC(SCCGWA, "YCHC6");
                            WS_CHANGE_FLG = 'Y';
                        }
                    }
                }
            }
            if (WS_CHANGE_FLG == 'Y') {
                if (WS_APRD_PAY_DAY < 29) {
                } else {
                    if (WS_APRD_PAY_DAY == 29 
                        || WS_APRD_PAY_DAY == 30) {
                        if (REDEFINES48.WS_MM2 == 2) {
                            if (REDEFINES48.WS_YYYY2 % 100 == 0) {
                                if (REDEFINES48.WS_YYYY2 % 400 == 0) {
                                    REDEFINES48.WS_DD2 = 29;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                } else {
                                    REDEFINES48.WS_DD2 = 28;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                }
                            } else {
                                if (REDEFINES48.WS_YYYY2 % 4 == 0) {
                                    REDEFINES48.WS_DD2 = 29;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                } else {
                                    REDEFINES48.WS_DD2 = 28;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                }
                            }
                        } else {
                            REDEFINES48.WS_DD2 = WS_APRD_PAY_DAY;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                            WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                    if (WS_APRD_PAY_DAY == 31) {
                        if (REDEFINES48.WS_MM2 == 2) {
                            if (REDEFINES48.WS_YYYY2 % 100 == 0) {
                                if (REDEFINES48.WS_YYYY2 % 400 == 0) {
                                    REDEFINES48.WS_DD2 = 29;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                } else {
                                    REDEFINES48.WS_DD2 = 28;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                }
                            } else {
                                if (REDEFINES48.WS_YYYY2 % 4 == 0) {
                                    REDEFINES48.WS_DD2 = 29;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                } else {
                                    REDEFINES48.WS_DD2 = 28;
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                    WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                                }
                            }
                        } else {
                            if (REDEFINES48.WS_MM2 == 4 
                                || REDEFINES48.WS_MM2 == 6 
                                || REDEFINES48.WS_MM2 == 9 
                                || REDEFINES48.WS_MM2 == 11) {
                                REDEFINES48.WS_DD2 = 30;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                REDEFINES48.WS_DD2 = 31;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES48);
                                WS_YYYYMMDD2 = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        }
                    }
                }
            }
            CEP.TRC(SCCGWA, "LILIXIA");
            CEP.TRC(SCCGWA, WS_YYYYMMDD1);
            CEP.TRC(SCCGWA, WS_YYYYMMDD2);
            SCCCLDT.DATE2 = WS_YYYYMMDD2;
            WS_F_TERM_FULL_DT1 = SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, WS_F_TERM_FULL_DT1);
        }
        CEP.TRC(SCCGWA, WS_F_TERM_FULL_DT);
        CEP.TRC(SCCGWA, WS_END_DATE);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        if (SCCCLDT.DATE2 == WS_END_DATE) {
            CEP.TRC(SCCGWA, "SITUATION 1");
            WS_TERM_DB = WS_BEG_DATE;
            WS_TERM_DE = WS_END_DATE;
            WS_BASE_30E = 'Y';
            B200_CINT_BASE_IRULBAL();
            if (pgmRtn) return;
        }
        if (SCCCLDT.DATE2 > WS_END_DATE) {
            if (WS_F_TERM_FULL_DT > WS_BEG_DATE) {
                CEP.TRC(SCCGWA, "SITUATION 2");
                WS_TERM_DB = WS_BEG_DATE;
                WS_TERM_DE = WS_F_TERM_FULL_DT;
                WS_BASE_30E = 'Y';
                B200_CINT_BASE_IRULBAL();
                if (pgmRtn) return;
                WS_TERM_DB = WS_F_TERM_FULL_DT;
                WS_TERM_DE = WS_END_DATE;
                WS_BASE_30E = 'N';
                B200_CINT_BASE_IRULBAL();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "SITUATION 3");
                WS_TERM_DB = WS_BEG_DATE;
                WS_TERM_DE = WS_END_DATE;
                WS_BASE_30E = 'N';
                B200_CINT_BASE_IRULBAL();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CINT_BASE_IRULBAL() throws IOException,SQLException,Exception {
        if (LNCICUT.COMM_DATA.RATE != 0) {
            IBS.init(SCCGWA, LNCRATB);
            LNCRATB.COMM_DATA.LN_AC = LNCICUT.COMM_DATA.LN_AC;
            LNCRATB.COMM_DATA.SUF_NO = LNCICUT.COMM_DATA.SUF_NO;
            LNCRATB.COMM_DATA.RATE_TYPE = LNRIRUL.RATE_TYP;
            LNCRATB.COMM_DATA.BEG_DATE = WS_TERM_DB;
            LNCRATB.COMM_DATA.END_DATE = WS_TERM_DE;
            LNCRATB.COMM_DATA.ITEM_CNT = 1;
            LNCRATB.COMM_DATA.IETM_RATES[1-1].RATE = LNCICUT.COMM_DATA.RATE;
            LNCRATB.COMM_DATA.IETM_RATES[1-1].B_DATE = WS_TERM_DB;
            LNCRATB.COMM_DATA.IETM_RATES[1-1].E_DATE = WS_TERM_DE;
        } else {
            B100_BRW_RATH_INF();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "20180714 7777");
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.ITEM_CNT);
        for (WS_JJJ = 1; WS_JJJ <= LNCRATB.COMM_DATA.ITEM_CNT; WS_JJJ += 1) {
            CEP.TRC(SCCGWA, WS_JJJ);
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE);
            CEP.TRC(SCCGWA, LNCIRULB.REC_DATAS.REC_TXT[WS_IDX-1].DATA_TXT.INT_FML);
            CEP.TRC(SCCGWA, LNCIRULB.REC_DATAS.REC_TXT[WS_IDX-1].DATA_TXT.TO_CNTR.TO_NO);
            CEP.TRC(SCCGWA, WS_BAL);
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE);
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE);
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].E_DATE);
            WS_TERM_BEG_DATE = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE;
            WS_TERM_END_DATE = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].E_DATE;
            B210_NORMAL_INT_CAL();
            if (pgmRtn) return;
        }
    }
    public void B210_NORMAL_INT_CAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM_BEG_DATE);
        CEP.TRC(SCCGWA, WS_TERM_END_DATE);
        IBS.init(SCCGWA, LNCINCM);
        LNCINCM.COMM_DATA.FORM_CODE = LNRIRUL.INT_FML;
        LNCINCM.COMM_DATA.BAL = WS_BAL;
        LNCINCM.COMM_DATA.RATE = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE;
        LNCINCM.COMM_DATA.TERM = WS_TOT_TERM;
        CEP.TRC(SCCGWA, "XXXXX");
        LNCINCM.COMM_DATA.BEG_DATE = WS_TERM_BEG_DATE;
        LNCINCM.COMM_DATA.END_DATE = WS_TERM_END_DATE;
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.END_DATE);
        if (LNCICUT.COMM_DATA.CAL_TERM == 0) {
            LNCINCM.COMM_DATA.INT_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        } else {
            LNCINCM.COMM_DATA.INT_TERM = LNCICUT.COMM_DATA.CAL_TERM;
        }
        LNCINCM.COMM_DATA.TERM_DAYS = WS_TERM_DAYS;
        LNCINCM.COMM_DATA.PERIOD = LNRAPRD.CAL_PERD;
        LNCINCM.COMM_DATA.PERIOD_UNIT = LNRAPRD.CAL_PERD_UNIT;
        LNCINCM.COMM_DATA.HANDLING_PERC = 0;
        LNCINCM.COMM_DATA.ROUND_MODE = BPCQCCY.DATA.DEC_MTH;
        LNCINCM.COMM_DATA.INST_AMT = WS_INST_AMT;
        B001_CCB_INTDAY_PROC();
        if (pgmRtn) return;
        WS_DAY_COOL = 0;
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, WS_INI_BEG_DATE);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, WS_INI_END_DATE);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CCY);
        if (LNRAPRD.PAY_MTH == K_INSTLMENT 
            && LNRAPRD.CAL_PERD_UNIT == K_MONTH 
            && LNCCONTM.REC_DATA.CCY.equalsIgnoreCase("156") 
            && (LNCINCM.COMM_DATA.BEG_DATE == WS_INI_BEG_DATE 
            || LNCINCM.COMM_DATA.END_DATE == WS_INI_END_DATE) 
            && WS_BASE_30E == 'Y') {
            WS_DATE3 = "" + LNCINCM.COMM_DATA.BEG_DATE;
            JIBS_tmp_int = WS_DATE3.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_DATE3 = "0" + WS_DATE3;
            WS_DATE4 = "" + LNCINCM.COMM_DATA.END_DATE;
            JIBS_tmp_int = WS_DATE4.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_DATE4 = "0" + WS_DATE4;
            if (WS_DATE3 == null) WS_DATE3 = "";
            JIBS_tmp_int = WS_DATE3.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_DATE3 += " ";
            WS_DATE3_MODA = WS_DATE3.substring(5 - 1, 5 + 4 - 1);
            if (WS_DATE4 == null) WS_DATE4 = "";
            JIBS_tmp_int = WS_DATE4.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_DATE4 += " ";
            WS_DATE4_MODA = WS_DATE4.substring(5 - 1, 5 + 4 - 1);
            CEP.TRC(SCCGWA, WS_DATE3_MODA);
            CEP.TRC(SCCGWA, WS_DATE4_MODA);
            CEP.TRC(SCCGWA, LNRAPRD.PAY_DAY);
            CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.INT_TERM);
            if (WS_DATE3_MODA.equalsIgnoreCase("0228") 
                && LNCINCM.COMM_DATA.INT_TERM > 1) {
                if (LNRAPRD.PAY_DAY == 29) {
                    WS_DAY_COOL = -1;
                    CEP.TRC(SCCGWA, "DAY-COOL -1");
                }
                if (LNRAPRD.PAY_DAY == 30 
                    || LNRAPRD.PAY_DAY == 31) {
                    WS_DAY_COOL = -2;
                    CEP.TRC(SCCGWA, "DAY-COOL -2");
                }
            }
            if (WS_DATE3_MODA.equalsIgnoreCase("0229") 
                && LNCINCM.COMM_DATA.INT_TERM > 1) {
                if (LNRAPRD.PAY_DAY == 30 
                    || LNRAPRD.PAY_DAY == 31) {
                    WS_DAY_COOL = -1;
                    CEP.TRC(SCCGWA, "DAY-COOL -1P");
                }
            }
            if (WS_DATE4_MODA.equalsIgnoreCase("0228")) {
                if (LNRAPRD.PAY_DAY == 29) {
                    WS_DAY_COOL = 1;
                    CEP.TRC(SCCGWA, "DAY-COOL 1");
                }
                if (LNRAPRD.PAY_DAY == 30 
                    || LNRAPRD.PAY_DAY == 31) {
                    WS_DAY_COOL = 2;
                    CEP.TRC(SCCGWA, "DAY-COOL 2");
                }
            }
            if (WS_DATE4_MODA.equalsIgnoreCase("0229")) {
                if (LNRAPRD.PAY_DAY == 30 
                    || LNRAPRD.PAY_DAY == 31) {
                    WS_DAY_COOL = 1;
                    CEP.TRC(SCCGWA, "DAY-COOL 1P");
                }
            }
        }
        LNCINCM.COMM_DATA.BASDAYS = BPCIDAY.OUTPUT.O_STD_DAYS;
        LNCINCM.COMM_DATA.CCB_DAYS += BPCIDAY.OUTPUT.O_ORD_DAYS;
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_STD_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_LEAP_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_ORD_DAYS);
        CEP.TRC(SCCGWA, WS_DAY_COOL);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.CCB_DAYS);
        S000_CALL_LNZINCM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "20180714 555");
        CEP.TRC(SCCGWA, WS_ICUT_SPE_INT);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.SPE_INT);
        WS_ICUT_SPE_INT += LNCINCM.COMM_DATA.SPE_INT;
        CEP.TRC(SCCGWA, "20180714 666");
        CEP.TRC(SCCGWA, WS_ICUT_SPE_INT);
        WS_ICUT_INT_AMT += LNCINCM.COMM_DATA.INT_AMT;
        WS_CTNR_INT += LNCINCM.COMM_DATA.INT_AMT;
        if (LNCICUT.COMM_DATA.FUNC_CODE == 'U' 
            && LNCINCM.COMM_DATA.INT_AMT != 0) {
            B220_GEN_INT_LNTINTD();
            if (pgmRtn) return;
        }
    }
    public void B001_CCB_INTDAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIDAY);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CALR_STD);
        BS01_GET_CALR_STD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCIDAY.I_CALR_STD);
        CEP.TRC(SCCGWA, "20180714 888");
        BPCIDAY.I_BEGIN_DATE = LNCINCM.COMM_DATA.BEG_DATE;
        BPCIDAY.I_END_DATE = LNCINCM.COMM_DATA.END_DATE;
        S000_CALL_BPZCIDAY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_LEAP_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_ORD_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_STD_DAYS);
    }
    public void BS01_GET_CALR_STD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRAPRD.INT_DBAS_STD);
        CEP.TRC(SCCGWA, LNRAPRD.PAY_DAY);
        if (LNRAPRD.INT_DBAS_STD.trim().length() == 0) {
            BPCIDAY.I_CALR_STD = BPCQCCY.DATA.CALR_STD;
        } else {
            BPCIDAY.I_CALR_STD = LNRAPRD.INT_DBAS_STD;
        }
        CEP.TRC(SCCGWA, "IDAY-I-CALR-STD-001");
        CEP.TRC(SCCGWA, BPCIDAY.I_CALR_STD);
        if (WS_BASE_30E == 'Y') {
            BPCIDAY.I_CALR_STD = K_INT_DB_30E;
        }
        CEP.TRC(SCCGWA, "IDAY-I-CALR-STD-002");
        CEP.TRC(SCCGWA, BPCIDAY.I_CALR_STD);
        if (LNRAPRD.PAY_MTH == '4') {
            WS_START_DATE = 0;
            WS_START_DATE = LNCCONTM.REC_DATA.START_DATE;
            WS_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
            JIBS_tmp_str[0] = "" + WS_START_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_MAT_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if ((LNCCONTM.REC_DATA.START_DATE == LNCINCM.COMM_DATA.BEG_DATE 
                && LNRAPRD.PAY_DD_TYPE == '2' 
                && !JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase(LNRAPRD.PAY_DAY+"")) 
                || (LNCCONTM.REC_DATA.MAT_DATE == LNCINCM.COMM_DATA.END_DATE 
                && LNRAPRD.PAY_DD_TYPE == '2' 
                && !JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase(LNRAPRD.PAY_DAY+"")) 
                || !LNCCONTM.REC_DATA.CCY.equalsIgnoreCase("156")) {
                BPCIDAY.I_CALR_STD = LNRAPRD.INT_DBAS_STD;
            } else {
                BPCIDAY.I_CALR_STD = K_INT_DB_30E;
            }
        }
        CEP.TRC(SCCGWA, "IDAY-I-CALR-STD-003");
        CEP.TRC(SCCGWA, BPCIDAY.I_CALR_STD);
    }
    public void B211_RULE78_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '3';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCICTLM.REC_DATA.KEY.CONTRACT_NO;
        LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = LNCICTLM.REC_DATA.KEY.SUB_CTA_NO;
        LNCPAIPM.REC_DATA.KEY.PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        WS_TOT_TERM = LNCPAIPM.REC_DATA.PHS_TOT_TERM;
        WS_BAL = LNCPAIPM.REC_DATA.PHS_PRIN_AMT;
        WS_INST_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        SCCCLDT.DATE2 = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_TERM_DAYS = (short) SCCCLDT.DAYS;
    }
    public void B100_BRW_BALH_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALB);
        LNCBALB.COMM_DATA.LN_AC = LNCICUT.COMM_DATA.LN_AC;
        LNCBALB.COMM_DATA.SUF_NO = LNCICUT.COMM_DATA.SUF_NO;
        LNCBALB.COMM_DATA.CTNR_NO = "" + WS_FROM_NOS[WS_KKK-1].WS_FROM_NO;
        JIBS_tmp_int = LNCBALB.COMM_DATA.CTNR_NO.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) LNCBALB.COMM_DATA.CTNR_NO = "0" + LNCBALB.COMM_DATA.CTNR_NO;
        LNCBALB.COMM_DATA.BEG_DATE = LNCICUT.COMM_DATA.BEG_DATE;
        LNCBALB.COMM_DATA.END_DATE = LNCICUT.COMM_DATA.END_DATE;
        S000_CALL_LNZBALB();
        if (pgmRtn) return;
        WS_PYIF_INT_AMT = LNCBALB.COMM_DATA.PYIF_INT_AMT;
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.ITEM_CNT);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.IETM_RATES[1-1]);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.IETM_RATES[2-1]);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.IETM_RATES[3-1]);
    }
    public void B100_BRW_RATH_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATB);
        LNCRATB.COMM_DATA.LN_AC = LNCICUT.COMM_DATA.LN_AC;
        LNCRATB.COMM_DATA.SUF_NO = LNCICUT.COMM_DATA.SUF_NO;
        LNCRATB.COMM_DATA.RATE_TYPE = LNRIRUL.RATE_TYP;
        LNCRATB.COMM_DATA.BEG_DATE = WS_TERM_DB;
        LNCRATB.COMM_DATA.END_DATE = WS_TERM_DE;
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.END_DATE);
        S000_CALL_LNZRATB();
        if (pgmRtn) return;
    }
    public void B220_GEN_INT_LNTINTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCINTDM);
        LNCINTDM.FUNC = '0';
        LNCINTDM.REC_DATA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        if (LNCICUT.COMM_DATA.SUF_NO.trim().length() == 0) LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICUT.COMM_DATA.SUF_NO);
        if (LNRAPRD.PAY_MTH == '4') {
            LNCINTDM.REC_DATA.KEY.REPY_MTH = 'C';
        } else {
            LNCINTDM.REC_DATA.KEY.REPY_MTH = 'I';
        }
        LNCINTDM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNCINTDM.REC_DATA.KEY.INT_TYP = 'N';
        LNCINTDM.REC_DATA.KEY.IRUL_SEQ = WS_IRUL_KEY.WS_IRUL_SEQ;
        LNCINTDM.REC_DATA.KEY.CTNR_NO = WS_KKK;
        LNCINTDM.REC_DATA.KEY.VAL_DT = LNCINCM.COMM_DATA.BEG_DATE;
        LNCINTDM.REC_DATA.CUT_OFF_DT = LNCINCM.COMM_DATA.END_DATE;
        LNCINTDM.REC_DATA.INT_AMT = LNCINCM.COMM_DATA.BAL;
        LNCINTDM.REC_DATA.INT_RAT = LNCINCM.COMM_DATA.RATE;
        LNCINTDM.REC_DATA.INT = LNCINCM.COMM_DATA.INT_AMT;
        LNCINTDM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCINTDM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_LNZINTDM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCINTDM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_INTD_EXIST)) {
            IBS.init(SCCGWA, LNCINTDM);
            LNCINTDM.FUNC = '4';
            LNCINTDM.REC_DATA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
            if (LNCICUT.COMM_DATA.SUF_NO.trim().length() == 0) LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICUT.COMM_DATA.SUF_NO);
            if (LNRAPRD.PAY_MTH == '4') {
                LNCINTDM.REC_DATA.KEY.REPY_MTH = 'C';
            } else {
                LNCINTDM.REC_DATA.KEY.REPY_MTH = 'I';
            }
            LNCINTDM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            LNCINTDM.REC_DATA.KEY.INT_TYP = 'N';
            LNCINTDM.REC_DATA.KEY.IRUL_SEQ = WS_IRUL_KEY.WS_IRUL_SEQ;
            LNCINTDM.REC_DATA.KEY.CTNR_NO = WS_KKK;
            LNCINTDM.REC_DATA.KEY.VAL_DT = LNCINCM.COMM_DATA.BEG_DATE;
            S000_CALL_LNZINTDM();
            if (pgmRtn) return;
            LNCINTDM.FUNC = '2';
            LNCINTDM.REC_DATA.INT_AMT += LNCINCM.COMM_DATA.BAL;
            LNCINTDM.REC_DATA.INT += LNCINCM.COMM_DATA.INT_AMT;
            S000_CALL_LNZINTDM();
            if (pgmRtn) return;
        }
    }
    public void B330_UPD_ICTL_INTDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        if (LNCICUT.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICUT.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        LNCICTLM.REC_DATA.INT_CUT_DT = LNCICUT.COMM_DATA.END_DATE;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B400_ADJ_INT_PROCESS() throws IOException,SQLException,Exception {
        WS_ADJ_INT_AMT = 0;
        if (LNCICTLM.REC_DATA.INT_CUT_DT < WS_INI_BEG_DATE) {
            WS_ADJ_VAL_DT = WS_INI_BEG_DATE;
        } else {
            WS_ADJ_VAL_DT = LNCICTLM.REC_DATA.INT_CUT_DT;
        }
        WS_INTA_FOUND_FLG = 'N';
        T00_STARTBR_LNTINTA();
        if (pgmRtn) return;
        T00_READNEXT_LNTINTA();
        if (pgmRtn) return;
        while (WS_INTA_FOUND_FLG != 'N') {
            if (LNRINTA.ADJ_VAL_DT == WS_ADJ_VAL_DT) {
                WS_ADJ_INT_AMT += LNRINTA.ADJ_AMT;
            }
            T00_READNEXT_LNTINTA();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTINTA();
        if (pgmRtn) return;
    }
    public void T00_STARTBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        if (LNCICUT.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTA.KEY.SUB_CTA_NO = 0;
        else LNRINTA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICUT.COMM_DATA.SUF_NO);
        LNRINTA.KEY.REPY_MTH = 'I';
        if (LNCICUT.COMM_DATA.TERM != 0) {
            LNRINTA.KEY.REPY_TERM = LNCICUT.COMM_DATA.TERM;
        } else {
            LNRINTA.KEY.REPY_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        }
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_BR.rp = new DBParm();
        LNTINTA_BR.rp.TableName = "LNTINTA";
        LNTINTA_BR.rp.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND ADJ_SEQ > :LNRINTA.KEY.ADJ_SEQ";
        LNTINTA_BR.rp.order = "ADJ_SEQ";
        IBS.STARTBR(SCCGWA, LNRINTA, this, LNTINTA_BR);
    }
    public void T00_READNEXT_LNTINTA() throws IOException,SQLException,Exception {
        WS_INTA_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRINTA, this, LNTINTA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTA_FOUND_FLG = 'Y';
        } else {
            WS_INTA_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void T00_READ_LNTINTA() throws IOException,SQLException,Exception {
        WS_INTA_FOUND_FLG = 'N';
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCICUT.COMM_DATA.LN_AC;
        if (LNCICUT.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTA.KEY.SUB_CTA_NO = 0;
        else LNRINTA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICUT.COMM_DATA.SUF_NO);
        LNRINTA.KEY.REPY_MTH = 'I';
        if (LNCICUT.COMM_DATA.TERM != 0) {
            LNRINTA.KEY.REPY_TERM = LNCICUT.COMM_DATA.TERM;
        } else {
            LNRINTA.KEY.REPY_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        }
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        IBS.READ(SCCGWA, LNRINTA, LNTINTA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTA_FOUND_FLG = 'Y';
        } else {
            WS_INTA_FOUND_FLG = 'N';
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            LNRAPRD.INT_DBAS_STD = " ";
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZINTDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-INTD-MAINT", LNCINTDM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, LNCINTDM.RC);
        if (LNCINTDM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_INTD_EXIST)) {
            LNCICUT.RC.RC_APP = LNCINTDM.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCINTDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTL-MAINT", LNCCNTLM);
        if (LNCCNTLM.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCCNTLM.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCCNTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIRULB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-IRUL-BRWQ", LNCIRULB);
        if (LNCIRULB.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCIRULB.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCIRULB.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATH-BRWQ", LNCRATB);
        if (LNCRATB.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCRATB.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCRATB.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALH-BRWQ", LNCBALB);
        if (LNCBALB.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCBALB.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCBALB.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZINCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-FORMUAL", LNCINCM);
        if (LNCINCM.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCINCM.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCINCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCICNQ.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCICNQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCPAIPM.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCPAIPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTIRUL() throws IOException,SQLException,Exception {
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        LNTIRUL_RD.where = "EFF_DATE < :LNRIRUL.KEY.EFF_DATE "
            + "AND IBS_AC_BK = :LNRIRUL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRIRUL.KEY.CODE";
        IBS.READ(SCCGWA, LNRIRUL, this, LNTIRUL_RD);
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_BPZCIDAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CALC-INT-DAYS", BPCIDAY);
        if (BPCIDAY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            BPCQCCY.DATA.DEC_MTH = 2;
        }
    }
    public void R00_CHECK_DATE() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCICUT.RC.RC_APP = "SC";
            LNCICUT.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCICUT.RC.RC_APP = "SC";
            LNCICUT.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCICUT=");
            CEP.TRC(SCCGWA, LNCICUT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
