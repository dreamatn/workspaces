package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZLCCM {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTINTA_RD;
    brParm LNTINTA_BR = new brParm();
    DBParm LNTRCVD_RD;
    DBParm LNTIRUL_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_NORMAL_RATE = 'N';
    char K_MONTH = 'M';
    double WS_SUM_O_AMT = 0;
    double WS_SUM_L_AMT = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_IDY = 0;
    short WS_III = 0;
    short WS_JJJ = 0;
    short WS_XXX = 0;
    short WS_DAYS = 0;
    short WS_R_DAYS = 0;
    int WS_GRACE_DAYS = 0;
    double WS_RATE = 0;
    int WS_DATE = 0;
    int WS_DATE1 = 0;
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    int WS_DAYS_BAS = 0;
    int WS_DAYS_CNT = 0;
    int WS_LC_BEG_DATE = 0;
    int WS_LC_END_DATE = 0;
    int WS_NEXT_WK_DATE = 0;
    char WS_RATE_TYPE = ' ';
    int WS_NEXT_RATE_DATE = 0;
    double WS_LC_UNPAY_AMT = 0;
    int WS_ADJ_VAL_DT = 0;
    double WS_ADJ_LC_AMT = 0;
    double WS_ADJ_O_AMT = 0;
    double WS_ADJ_L_AMT = 0;
    double WS_ICUT_ADJ_AMT = 0;
    int WS_RCVD_DUE_DT = 0;
    double WS_INT_AMT = 0;
    long WS_LOW_CCY_INT_AMT = 0;
    double WS_LOW_CCY_INT_AMT1 = 0;
    double WS_LOW_CCY_INT_AMT2 = 0;
    double WS_BAL = 0;
    LNZLCCM_WS_IRUL_KEY WS_IRUL_KEY = new LNZLCCM_WS_IRUL_KEY();
    int WS_KEY_LEN = 0;
    char WS_FST_ONE_ID = ' ';
    char WS_LST_ONE_ID = ' ';
    double WS_INT_AMT1 = 0;
    double WS_LCCM_LC_AMT = 0;
    double WS_LCCM_LC_AMT_L = 0;
    double WS_TEMP_CAL_L_AMT = 0;
    double WS_TEMP_CAL_L_AMT_L = 0;
    double WS_CAL_OL_AMT = 0;
    double WS_CAL_O_AMT = 0;
    double WS_CAL_L_AMT = 0;
    double WS_ROUND_AMOUNT = 0;
    double WS_OUTPUT_O_AMT = 0;
    double WS_OUTPUT_L_AMT = 0;
    double WS_ROUND_OUT_AMT = 0;
    double WS_CTNR_AMT = 0;
    double WS_CTNR_INT = 0;
    double WS_CTNR_INT_L = 0;
    short WS_NO = 0;
    LNZLCCM_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZLCCM_WS_LOAN_CONT_AREA();
    int WS_CAL_LC_BEG_DATE = 0;
    int WS_CAL_LC_END_DATE = 0;
    double WS_CAL_LC_AMT = 0;
    int WS_CAL_INT_BEG_DT = 0;
    int WS_CAL_INT_END_DT = 0;
    double WS_I_O_AMT = 0;
    double WS_I_L_AMT = 0;
    short WS_DIVID_I = 0;
    short WS_DIVID_II = 0;
    int WS_STOP_DUE_DATE = 0;
    int WS_STOP_VAL_DATE = 0;
    int WS_LCCM_BEG_DATE = 0;
    int WS_LCCM_END_DATE = 0;
    int WS_LCCM_BEG_DATE_IN = 0;
    int WS_LCCM_BEG_DATE_F = 0;
    int WS_LCCM_END_DATE_F = 0;
    int WS_SLCCM_BEG_DATE = 0;
    int WS_SLCCM_END_DATE = 0;
    int WS_LCCM_BEG_DATE_H = 0;
    int WS_LCCM_END_DATE_H = 0;
    int WS_CAL_END_DATE = 0;
    int WS_OCAL_END_DT = 0;
    double WS_LCCM_O_AMT = 0;
    double WS_LCCM_L_AMT = 0;
    double WS_LCCM_O_AMT_TOL = 0;
    double WS_LCCM_L_AMT_TOL = 0;
    double WS_LCCM_AMT_TMP = 0;
    double WS_O_LST_CAL_AMT = 0;
    double WS_L_LST_CAL_AMT = 0;
    char WS_CAL_UNIT = ' ';
    short WS_CAL_PERD = 0;
    short WS_OCAL_TIMES = 0;
    int WS_NEXT_LC_CAL_DAT = 0;
    short WS_IC_CAL_TERM = 0;
    short WS_P_CAL_TERM = 0;
    double WS_O_LST_PST_AMT = 0;
    double WS_L_LST_PST_AMT = 0;
    int WS_YYYYMMDD = 0;
    LNZLCCM_REDEFINES101 REDEFINES101 = new LNZLCCM_REDEFINES101();
    LNZLCCM_WS_FROM_NOS[] WS_FROM_NOS = new LNZLCCM_WS_FROM_NOS[10];
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_INTA_FOUND_FLG = ' ';
    String WS_FORM_CODE = " ";
    char WS_HGRD_PROC_MTH = ' ';
    char WS_USE_GRACE_FLG = ' ';
    char WS_CAL_BEGIN_FLG = ' ';
    char WS_CAL_INTEREST_TYP = ' ';
    char WS_CAL_RAT = ' ';
    char WS_INTAO_FOUND_FLG = ' ';
    char WS_INTAL_FOUND_FLG = ' ';
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRINTA LNRINTA = new LNRINTA();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCINTDM LNCINTDM = new LNCINTDM();
    LNCIRULB LNCIRULB = new LNCIRULB();
    LNCIRULB LNCIRULC = new LNCIRULB();
    LNCRATB LNCRATB = new LNCRATB();
    LNCRATB LNCRATC = new LNCRATB();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCBALLM LNCBALLM = new LNCBALLM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCIDAY BPCIDAY = new BPCIDAY();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    LNRIRUL LNRIRUL = new LNRIRUL();
    SCCGWA SCCGWA;
    LNCLCCM LNCLCCM;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCBATH SCCBATH;
    public LNZLCCM() {
        for (int i=0;i<10;i++) WS_FROM_NOS[i] = new LNZLCCM_WS_FROM_NOS();
    }
    public void MP(SCCGWA SCCGWA, LNCLCCM LNCLCCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCLCCM = LNCLCCM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        if (LNCLCCM.COMM_DATA.BEG_DATE != LNCLCCM.COMM_DATA.END_DATE) {
            B00_MAIN_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LNZLCCM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        LNCLCCM.RC.RC_APP = "LN";
        LNCLCCM.RC.RC_RTNCODE = 0;
        WS_KEY_LEN = 10;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.AMT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
        B100_PRE_LCCM_PRC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCLCCM.COMM_DATA.FUNC_CODE != 'I' 
            && LNCLCCM.COMM_DATA.FUNC_CODE != 'U' 
            && LNCLCCM.COMM_DATA.FUNC_CODE != 'A') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCLCCM.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.ACCEPT_FLG == 'Y') {
            LNCCONTM.REC_DATA.CONTRACT_TYPE = LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CONTRACT_TYPE;
            LNCCONTM.REC_DATA.CCY = LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CCY;
            LNCCONTM.REC_DATA.MAT_DATE = LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_MAT_DATE;
        } else {
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        WS_STOP_DUE_DATE = LNCLOANM.REC_DATA.STOP_DUE_DT;
        WS_STOP_VAL_DATE = LNCLOANM.REC_DATA.STOP_VAL_DT;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCCONTM.REC_DATA.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'A') {
            LNCICTLM.FUNC = '4';
        } else {
            LNCICTLM.FUNC = '3';
        }
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B100_PRE_LCCM_PRC() throws IOException,SQLException,Exception {
        T00_READ_LNTRCVD1();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND, LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_RCVD_DUE_DT = LNRRCVD.DUE_DT;
            if (LNRRCVD.LAST_LC_CAL_DAT < WS_RCVD_DUE_DT) {
                WS_NEXT_LC_CAL_DAT = WS_RCVD_DUE_DT;
            } else {
                WS_NEXT_LC_CAL_DAT = LNRRCVD.LAST_LC_CAL_DAT;
            }
            CEP.TRC(SCCGWA, WS_RCVD_DUE_DT);
            CEP.TRC(SCCGWA, WS_NEXT_LC_CAL_DAT);
            if (LNRRCVD.PI_STOP_DT != 0) {
                WS_RCVD_DUE_DT = LNRRCVD.PI_STOP_DT;
            }
        }
        CEP.TRC(SCCGWA, "LCCM-TYPE201806270001");
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        if (LNCLCCM.COMM_DATA.AMT == 0) {
            if (LNCLCCM.COMM_DATA.TYPE == 'P') {
                WS_I_O_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
                if (LNRRCVD.O_LST_CAL_AMT > LNRRCVD.O_PAY_AMT + LNRRCVD.O_WAV_AMT) {
                    WS_I_L_AMT = LNRRCVD.O_LST_CAL_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT;
                }
                if (LNRRCVD.L_LST_CAL_AMT > LNRRCVD.L_PAY_AMT + LNRRCVD.L_WAV_AMT) {
                    WS_I_L_AMT = LNRRCVD.L_LST_CAL_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + WS_I_L_AMT;
                }
                WS_LC_UNPAY_AMT = LNRRCVD.I_REC_AMT + LNRRCVD.O_LST_CAL_AMT + LNRRCVD.O_LST_PST_AMT + LNRRCVD.L_LST_CAL_AMT + LNRRCVD.L_LST_PST_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.I_WAV_AMT - LNRRCVD.O_WAV_AMT - LNRRCVD.L_WAV_AMT;
            } else if (LNCLCCM.COMM_DATA.TYPE == 'I') {
                if (LNRRCVD.L_LST_CAL_AMT > LNRRCVD.L_PAY_AMT + LNRRCVD.L_WAV_AMT) {
                    LNCLCCM.COMM_DATA.AMT = LNRRCVD.L_LST_CAL_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNCLCCM.COMM_DATA.AMT;
                }
                WS_LC_UNPAY_AMT = LNRRCVD.I_REC_AMT + LNRRCVD.L_LST_CAL_AMT + LNRRCVD.L_LST_PST_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.I_WAV_AMT - LNRRCVD.L_WAV_AMT;
                LNCLCCM.COMM_DATA.AMT = LNCLCCM.COMM_DATA.AMT + LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                WS_I_L_AMT = LNCLCCM.COMM_DATA.AMT;
            } else {
                WS_I_O_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
                if (LNRRCVD.O_LST_CAL_AMT > LNRRCVD.O_PAY_AMT + LNRRCVD.O_WAV_AMT) {
                    WS_I_L_AMT = LNRRCVD.O_LST_CAL_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT;
                }
                if (LNRRCVD.L_LST_CAL_AMT > LNRRCVD.L_PAY_AMT + LNRRCVD.L_WAV_AMT) {
                    WS_I_L_AMT = LNRRCVD.L_LST_CAL_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + WS_I_L_AMT;
                }
                WS_LC_UNPAY_AMT = LNRRCVD.I_REC_AMT + LNRRCVD.O_LST_CAL_AMT + LNRRCVD.O_LST_PST_AMT + LNRRCVD.L_LST_CAL_AMT + LNRRCVD.L_LST_PST_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.I_WAV_AMT - LNRRCVD.O_WAV_AMT - LNRRCVD.L_WAV_AMT;
                WS_I_L_AMT = WS_I_L_AMT + LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
            }
        }
        WS_LCCM_AMT_TMP = LNCLCCM.COMM_DATA.AMT;
        WS_O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT;
        WS_L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT;
        WS_O_LST_PST_AMT = LNRRCVD.O_LST_PST_AMT;
        WS_L_LST_PST_AMT = LNRRCVD.L_LST_PST_AMT;
        CEP.TRC(SCCGWA, "LCCM-TYPE2018062700002");
        CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.P_PAY_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.O_PAY_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.I_WAV_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.L_PAY_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.L_LST_CAL_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.L_LST_PST_AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.AMT);
        CEP.TRC(SCCGWA, WS_I_L_AMT);
        CEP.TRC(SCCGWA, WS_I_O_AMT);
        CEP.TRC(SCCGWA, WS_LC_UNPAY_AMT);
        CEP.TRC(SCCGWA, "YYYYY1");
        CEP.TRC(SCCGWA, WS_CAL_OL_AMT);
        WS_CAL_OL_AMT = WS_I_L_AMT;
        if (LNCLCCM.COMM_DATA.TYPE == 'I') {
            WS_CAL_OL_AMT = LNCLCCM.COMM_DATA.AMT;
        }
        WS_LCCM_BEG_DATE_IN = LNCLCCM.COMM_DATA.BEG_DATE;
        if (LNCLCCM.COMM_DATA.BEG_DATE == 0) {
            if (LNRRCVD.PI_STOP_DT == 0) {
                LNCLCCM.COMM_DATA.BEG_DATE = LNRRCVD.DUE_DT;
            } else {
                LNCLCCM.COMM_DATA.BEG_DATE = LNRRCVD.PI_STOP_DT;
            }
        } else {
            if (LNCLCCM.COMM_DATA.BEG_DATE < WS_RCVD_DUE_DT) {
                LNCLCCM.COMM_DATA.BEG_DATE = WS_RCVD_DUE_DT;
            }
        }
        CEP.TRC(SCCGWA, LNRRCVD.LAST_LC_CAL_DAT);
        CEP.TRC(SCCGWA, LNRRCVD.OVD_DT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, "DFDFDFDFDFDFDFDF");
        CEP.TRC(SCCGWA, LNRRCVD.CLR_TYP);
        if ((LNRRCVD.LAST_LC_CAL_DAT < LNRRCVD.OVD_DT 
            && LNCLCCM.COMM_DATA.END_DATE >= LNRRCVD.OVD_DT 
            && LNRRCVD.LAST_LC_CAL_DAT > LNRRCVD.DUE_DT 
            && LNRRCVD.OVD_DT != LNRRCVD.DUE_DT) 
            || LNRRCVD.CLR_TYP == 1) {
            LNRRCVD.O_LST_CAL_AMT = 0;
            LNRRCVD.O_LST_PST_AMT = 0;
            LNRRCVD.L_LST_CAL_AMT = 0;
            LNRRCVD.L_LST_PST_AMT = 0;
            WS_O_LST_CAL_AMT = 0;
            WS_L_LST_CAL_AMT = 0;
            WS_I_L_AMT = 0;
            LNCICTLM.REC_DATA.NEXT_LC_CAL_DAT = 0;
            WS_I_L_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
            WS_CAL_OL_AMT = WS_I_L_AMT;
            LNCLCCM.COMM_DATA.BEG_DATE = LNRRCVD.DUE_DT;
            WS_NEXT_LC_CAL_DAT = LNRRCVD.VAL_DT;
            CEP.TRC(SCCGWA, "D-1");
        } else {
            CEP.TRC(SCCGWA, "D-2");
            if (LNRRCVD.LAST_LC_CAL_DAT > LNCLCCM.COMM_DATA.BEG_DATE) {
                CEP.TRC(SCCGWA, "D-3");
                LNCLCCM.COMM_DATA.BEG_DATE = LNRRCVD.LAST_LC_CAL_DAT;
            }
        }
        if (LNCLCCM.COMM_DATA.BEG_DATE == LNCLCCM.COMM_DATA.END_DATE 
            && LNCLCCM.COMM_DATA.BEG_DATE == LNRRCVD.PI_STOP_DT) {
            WS_LCCM_O_AMT_TOL = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT;
            WS_LCCM_L_AMT_TOL = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
            B100_UPD_ICTL_RCVD_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNRRCVD.LAST_LC_CAL_DAT);
        if (LNCLCCM.COMM_DATA.BEG_DATE == LNCLCCM.COMM_DATA.END_DATE 
            && LNCLCCM.COMM_DATA.BEG_DATE == LNRRCVD.LAST_LC_CAL_DAT) {
            WS_LCCM_O_AMT_TOL = LNRRCVD.O_LST_CAL_AMT - LNRRCVD.O_PAY_AMT;
            WS_LCCM_L_AMT_TOL = LNRRCVD.L_LST_CAL_AMT - LNRRCVD.L_PAY_AMT;
            B100_UPD_ICTL_RCVD_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCLCCM.COMM_DATA.END_DATE == 0) {
            LNCLCCM.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        if (LNCLCCM.COMM_DATA.BEG_DATE > LNCLCCM.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ERR_OL_BEG_DT, LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BS01_CCB_INTDAY_PROC();
        if (pgmRtn) return;
        WS_LCCM_BEG_DATE = LNCLCCM.COMM_DATA.BEG_DATE;
        WS_LCCM_BEG_DATE_F = LNCLCCM.COMM_DATA.BEG_DATE;
        WS_LCCM_END_DATE = LNCLCCM.COMM_DATA.END_DATE;
        WS_LCCM_END_DATE_F = LNCLCCM.COMM_DATA.END_DATE;
        if (LNCLCCM.COMM_DATA.BEG_DATE < LNCCONTM.REC_DATA.MAT_DATE) {
            CEP.TRC(SCCGWA, "ZHENGCHANG ZHOUQI START");
            if (WS_LCCM_END_DATE > LNCCONTM.REC_DATA.MAT_DATE) {
                WS_LCCM_END_DATE = LNCCONTM.REC_DATA.MAT_DATE;
            }
            CEP.TRC(SCCGWA, WS_LCCM_BEG_DATE);
            CEP.TRC(SCCGWA, WS_LCCM_END_DATE);
            WS_CAL_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
            WS_CAL_PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            B100_CAL_PERD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "ZHENGCHANG ZHOUQI END");
            CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
            CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_PST_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_CAL_AMT);
            if (WS_LCCM_END_DATE_F > WS_LCCM_END_DATE) {
                CEP.TRC(SCCGWA, "DAOQI ZHOUQI START");
                WS_CAL_UNIT = LNCAPRDM.REC_DATA.OCAL_PERD_UNIT;
                WS_CAL_PERD = LNCAPRDM.REC_DATA.OCAL_PERD;
                WS_LCCM_BEG_DATE = WS_LCCM_END_DATE;
                LNCLCCM.COMM_DATA.BEG_DATE = WS_LCCM_END_DATE;
                WS_LCCM_END_DATE = WS_LCCM_END_DATE_F;
                LNCLCCM.COMM_DATA.END_DATE = WS_LCCM_END_DATE_F;
                B100_CAL_PERD_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "DAOQI ZHOUQI END");
                CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
                CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
            }
        } else {
            CEP.TRC(SCCGWA, "DAOQI ZHOUQI START");
            WS_CAL_UNIT = LNCAPRDM.REC_DATA.OCAL_PERD_UNIT;
            WS_CAL_PERD = LNCAPRDM.REC_DATA.OCAL_PERD;
            B100_CAL_PERD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DAOQI ZHOUQI END");
        }
        CEP.TRC(SCCGWA, "ZZZZZZ");
        CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.PI_STOP_DT);
        CEP.TRC(SCCGWA, WS_LCCM_BEG_DATE_F);
        if (LNRRCVD.PI_STOP_DT < WS_LCCM_BEG_DATE_F) {
            WS_LCCM_O_AMT_TOL = WS_LCCM_O_AMT_TOL + WS_O_LST_CAL_AMT - LNRRCVD.O_PAY_AMT;
            bigD = new BigDecimal(WS_LCCM_O_AMT_TOL);
            WS_LCCM_O_AMT_TOL = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            WS_LCCM_L_AMT_TOL = WS_LCCM_L_AMT_TOL + WS_L_LST_CAL_AMT - LNRRCVD.L_PAY_AMT;
            bigD = new BigDecimal(WS_LCCM_L_AMT_TOL);
            WS_LCCM_L_AMT_TOL = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            WS_LCCM_O_AMT_TOL = WS_LCCM_O_AMT_TOL + LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT;
            bigD = new BigDecimal(WS_LCCM_O_AMT_TOL);
            WS_LCCM_O_AMT_TOL = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            WS_LCCM_L_AMT_TOL = WS_LCCM_L_AMT_TOL + LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT;
            bigD = new BigDecimal(WS_LCCM_L_AMT_TOL);
            WS_LCCM_L_AMT_TOL = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        B100_UPD_ICTL_RCVD_PROC();
        if (pgmRtn) return;
    }
    public void B100_UPD_ICTL_RCVD_PROC() throws IOException,SQLException,Exception {
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'A') {
            WS_IC_CAL_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
            WS_P_CAL_TERM = (short) (LNCICTLM.REC_DATA.P_CAL_TERM - 1);
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
            CEP.TRC(SCCGWA, WS_IC_CAL_TERM);
            CEP.TRC(SCCGWA, WS_P_CAL_TERM);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CUR_TERM);
            CEP.TRC(SCCGWA, WS_P_CAL_TERM);
            if ((LNCLCCM.COMM_DATA.TYPE != 'P' 
                && WS_IC_CAL_TERM == LNRRCVD.KEY.TERM) 
                || (LNCLCCM.COMM_DATA.TYPE == 'P' 
                && LNCICTLM.REC_DATA.IC_CAL_TERM == LNCICTLM.REC_DATA.IC_CUR_TERM 
                && WS_P_CAL_TERM == LNRRCVD.KEY.TERM)) {
                LNCICTLM.FUNC = '2';
                LNCICTLM.REC_DATA.NEXT_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.CLR_TYP);
            CEP.TRC(SCCGWA, "DEV5");
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_PST_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_LST_CAL_AMT);
            LNRRCVD.LAST_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
            LNRRCVD.CLR_TYP = 0;
            CEP.TRC(SCCGWA, LNRRCVD.CLR_TYP);
            T000_REWRITE_LNTRCVD1();
            if (pgmRtn) return;
        }
        B400_ADJ_LC_PROC();
        if (pgmRtn) return;
        B500_ADJ_WAV_PROC();
        if (pgmRtn) return;
        WS_ROUND_AMOUNT = WS_LCCM_O_AMT_TOL;
        R00_ROUND_PROCESS();
        if (pgmRtn) return;
        LNCLCCM.COMM_DATA.O_AMT = WS_ROUND_OUT_AMT;
        WS_ROUND_AMOUNT = WS_LCCM_L_AMT_TOL;
        R00_ROUND_PROCESS();
        if (pgmRtn) return;
        LNCLCCM.COMM_DATA.L_AMT = WS_ROUND_OUT_AMT;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
    }
    public void B100_CAL_PERD_PROC() throws IOException,SQLException,Exception {
        if (WS_CAL_UNIT != ' ' 
            && WS_CAL_PERD != 0) {
            WS_OCAL_END_DT = 0;
            CEP.TRC(SCCGWA, "OCAL-START");
            while (WS_OCAL_END_DT < WS_LCCM_END_DATE 
                && WS_OCAL_TIMES <= 1500) {
                CEP.TRC(SCCGWA, "OCAL-FENDUAN-START");
                B100_OCAL_PERD_PROC();
                if (pgmRtn) return;
                if (WS_OCAL_END_DT >= LNCLCCM.COMM_DATA.BEG_DATE) {
                    WS_OCAL_TIMES += 1;
                    WS_SLCCM_BEG_DATE = LNCLCCM.COMM_DATA.BEG_DATE;
                    if (WS_OCAL_END_DT > WS_LCCM_END_DATE) {
                        WS_SLCCM_END_DATE = WS_LCCM_END_DATE;
                        if (WS_LCCM_END_DATE == LNCCONTM.REC_DATA.MAT_DATE) {
                            WS_LCCM_END_DATE = WS_OCAL_END_DT;
                            WS_SLCCM_END_DATE = WS_OCAL_END_DT;
                            if (WS_LCCM_END_DATE_F >= WS_LCCM_END_DATE) {
                                WS_NEXT_LC_CAL_DAT = WS_OCAL_END_DT;
                            } else {
                                WS_SLCCM_END_DATE = WS_LCCM_END_DATE_F;
                            }
                        }
                    } else {
                        WS_SLCCM_END_DATE = WS_OCAL_END_DT;
                        WS_NEXT_LC_CAL_DAT = WS_OCAL_END_DT;
                    }
                    CEP.TRC(SCCGWA, WS_SLCCM_BEG_DATE);
                    CEP.TRC(SCCGWA, WS_SLCCM_END_DATE);
                    CEP.TRC(SCCGWA, LNRRCVD.LAST_LC_CAL_DAT);
                    if (LNCAPRDM.REC_DATA.RCMP_INT == 'Y') {
                        WS_CAL_INTEREST_TYP = 'C';
                    }
                    B100_STOP_INT_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                    WS_LCCM_O_AMT_TOL = WS_LCCM_O_AMT + WS_LCCM_O_AMT_TOL;
                    bigD = new BigDecimal(WS_LCCM_O_AMT_TOL);
                    WS_LCCM_O_AMT_TOL = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                    WS_LCCM_L_AMT_TOL = WS_LCCM_L_AMT + WS_LCCM_L_AMT_TOL;
                    bigD = new BigDecimal(WS_LCCM_L_AMT_TOL);
                    WS_LCCM_L_AMT_TOL = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                    CEP.TRC(SCCGWA, WS_OCAL_END_DT);
                    CEP.TRC(SCCGWA, WS_LCCM_END_DATE);
                    CEP.TRC(SCCGWA, WS_LCCM_END_DATE_F);
                    CEP.TRC(SCCGWA, LNRRCVD.PI_STOP_DT);
                    CEP.TRC(SCCGWA, WS_LCCM_BEG_DATE);
                    CEP.TRC(SCCGWA, WS_OCAL_TIMES);
                    CEP.TRC(SCCGWA, "DEV1");
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                    if (WS_OCAL_END_DT <= WS_LCCM_END_DATE 
                        && WS_LCCM_END_DATE_F >= WS_LCCM_END_DATE) {
                        if (LNRRCVD.PI_STOP_DT == WS_LCCM_BEG_DATE 
                            && WS_OCAL_TIMES <= 1) {
                            CEP.TRC(SCCGWA, "TEST1");
                            LNRRCVD.O_LST_CAL_AMT = LNRRCVD.O_REC_AMT + WS_LCCM_O_AMT;
                            bigD = new BigDecimal(LNRRCVD.O_LST_CAL_AMT);
                            LNRRCVD.O_LST_CAL_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                            LNRRCVD.L_LST_CAL_AMT = LNRRCVD.L_REC_AMT + WS_LCCM_L_AMT;
                            bigD = new BigDecimal(LNRRCVD.L_LST_CAL_AMT);
                            LNRRCVD.L_LST_CAL_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            CEP.TRC(SCCGWA, "TEST2");
                            LNRRCVD.O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT + WS_LCCM_O_AMT;
                            bigD = new BigDecimal(LNRRCVD.O_LST_CAL_AMT);
                            LNRRCVD.O_LST_CAL_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                            LNRRCVD.L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT + WS_LCCM_L_AMT;
                            bigD = new BigDecimal(LNRRCVD.L_LST_CAL_AMT);
                            LNRRCVD.L_LST_CAL_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        LNRRCVD.O_LST_PST_AMT = 0;
                        LNRRCVD.L_LST_PST_AMT = 0;
                    } else {
                        CEP.TRC(SCCGWA, "TEST3");
                        CEP.TRC(SCCGWA, WS_LCCM_BEG_DATE);
                        CEP.TRC(SCCGWA, WS_OCAL_TIMES);
                        CEP.TRC(SCCGWA, "DEV2");
                        CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
                        CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
                        CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                        CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                        if (LNRRCVD.PI_STOP_DT == WS_LCCM_BEG_DATE 
                            && WS_OCAL_TIMES <= 1) {
                            CEP.TRC(SCCGWA, "TEST4");
                            LNRRCVD.O_LST_PST_AMT = LNRRCVD.O_REC_AMT - WS_O_LST_CAL_AMT + WS_LCCM_O_AMT;
                            bigD = new BigDecimal(LNRRCVD.O_LST_PST_AMT);
                            LNRRCVD.O_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                            LNRRCVD.L_LST_PST_AMT = LNRRCVD.L_REC_AMT - WS_L_LST_CAL_AMT + WS_LCCM_L_AMT;
                            bigD = new BigDecimal(LNRRCVD.L_LST_PST_AMT);
                            LNRRCVD.L_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            CEP.TRC(SCCGWA, "TEST5");
                            LNRRCVD.O_LST_PST_AMT = WS_LCCM_O_AMT + 0;
                            bigD = new BigDecimal(LNRRCVD.O_LST_PST_AMT);
                            LNRRCVD.O_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                            LNRRCVD.L_LST_PST_AMT = WS_LCCM_L_AMT + 0;
                            bigD = new BigDecimal(LNRRCVD.L_LST_PST_AMT);
                            LNRRCVD.L_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                    CEP.TRC(SCCGWA, WS_OCAL_END_DT);
                    CEP.TRC(SCCGWA, WS_LCCM_END_DATE);
                    CEP.TRC(SCCGWA, WS_LCCM_END_DATE_F);
                    if ((WS_OCAL_END_DT <= WS_LCCM_END_DATE 
                        && WS_LCCM_END_DATE_F >= WS_LCCM_END_DATE) 
                        && LNCAPRDM.REC_DATA.RCMP_INT == 'Y') {
                        CEP.TRC(SCCGWA, "YYYYYY2");
                        CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
                        CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
                        CEP.TRC(SCCGWA, WS_I_L_AMT);
                        CEP.TRC(SCCGWA, LNRRCVD.PI_STOP_DT);
                        CEP.TRC(SCCGWA, WS_LCCM_BEG_DATE_F);
                        WS_CAL_OL_AMT = WS_LCCM_O_AMT_TOL + WS_LCCM_L_AMT_TOL + WS_I_L_AMT;
                        bigD = new BigDecimal(WS_CAL_OL_AMT);
                        WS_CAL_OL_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        if (LNRRCVD.PI_STOP_DT == WS_LCCM_BEG_DATE_F) {
                            WS_CAL_OL_AMT = WS_LCCM_O_AMT_TOL + WS_LCCM_L_AMT_TOL + LNRRCVD.I_REC_AMT + LNRRCVD.O_REC_AMT + LNRRCVD.L_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.O_WAV_AMT - LNRRCVD.L_WAV_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                            bigD = new BigDecimal(WS_CAL_OL_AMT);
                            WS_CAL_OL_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        CEP.TRC(SCCGWA, WS_CAL_OL_AMT);
                        WS_CAL_INTEREST_TYP = 'C';
                    }
                    CEP.TRC(SCCGWA, WS_LCCM_END_DATE);
                    CEP.TRC(SCCGWA, WS_OCAL_END_DT);
                    CEP.TRC(SCCGWA, "LLLLL");
                    CEP.TRC(SCCGWA, WS_CAL_INTEREST_TYP);
                    CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
                    CEP.TRC(SCCGWA, "DEV3");
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT_TOL);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT_TOL);
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                    CEP.TRC(SCCGWA, WS_CAL_OL_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.L_LST_PST_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.L_LST_CAL_AMT);
                    CEP.TRC(SCCGWA, "OCAL-FENDUAN-END");
                    LNCLCCM.COMM_DATA.BEG_DATE = WS_SLCCM_END_DATE;
                    CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
                    LNCLCCM.COMM_DATA.AMT = WS_LCCM_AMT_TMP;
                    CEP.TRC(SCCGWA, WS_LCCM_AMT_TMP);
                    CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.AMT);
                    WS_LCCM_LC_AMT_L = 0;
                    WS_LCCM_O_AMT = 0;
                    WS_LCCM_L_AMT = 0;
                } else {
                    CEP.TRC(SCCGWA, "TEST6");
                    WS_NEXT_LC_CAL_DAT = WS_OCAL_END_DT;
                }
            }
            CEP.TRC(SCCGWA, "OCAL-END");
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_CAL_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.O_LST_PST_AMT);
        } else {
            CEP.TRC(SCCGWA, "TEST7");
            if (LNCAPRDM.REC_DATA.RCMP_INT == 'Y') {
                WS_CAL_INTEREST_TYP = 'C';
            }
            WS_SLCCM_BEG_DATE = WS_LCCM_BEG_DATE;
            WS_SLCCM_END_DATE = WS_LCCM_END_DATE;
            B100_STOP_INT_PROC();
            if (pgmRtn) return;
            if (LNRRCVD.PI_STOP_DT == WS_LCCM_BEG_DATE 
                && WS_OCAL_TIMES == 0) {
                LNRRCVD.O_LST_PST_AMT = LNRRCVD.O_REC_AMT - LNRRCVD.O_LST_CAL_AMT + WS_LCCM_O_AMT;
                bigD = new BigDecimal(LNRRCVD.O_LST_PST_AMT);
                LNRRCVD.O_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                LNRRCVD.L_LST_PST_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_LST_CAL_AMT + WS_LCCM_L_AMT;
                bigD = new BigDecimal(LNRRCVD.L_LST_PST_AMT);
                LNRRCVD.L_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                LNRRCVD.O_LST_PST_AMT = WS_LCCM_O_AMT + 0;
                bigD = new BigDecimal(LNRRCVD.O_LST_PST_AMT);
                LNRRCVD.O_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                LNRRCVD.L_LST_PST_AMT = WS_LCCM_L_AMT + 0;
                bigD = new BigDecimal(LNRRCVD.L_LST_PST_AMT);
                LNRRCVD.L_LST_PST_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            WS_LCCM_O_AMT_TOL = WS_LCCM_O_AMT + WS_LCCM_O_AMT_TOL;
            bigD = new BigDecimal(WS_LCCM_O_AMT_TOL);
            WS_LCCM_O_AMT_TOL = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_LCCM_L_AMT_TOL = WS_LCCM_L_AMT + WS_LCCM_L_AMT_TOL;
            bigD = new BigDecimal(WS_LCCM_L_AMT_TOL);
            WS_LCCM_L_AMT_TOL = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        }
    }
    public void B100_OCAL_PERD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, WS_NEXT_LC_CAL_DAT);
        SCCCLDT.DATE1 = WS_NEXT_LC_CAL_DAT;
        if (WS_CAL_UNIT == K_MONTH) {
            SCCCLDT.MTHS = WS_CAL_PERD;
        } else {
            SCCCLDT.DAYS = WS_CAL_PERD;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, WS_OCAL_END_DT);
        WS_YYYYMMDD = SCCCLDT.DATE2;
        IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD+"", REDEFINES101);
        if (WS_CAL_UNIT == K_MONTH) {
            if (LNCAPRDM.REC_DATA.PAY_DAY < 29) {
                if (LNCAPRDM.REC_DATA.PAY_DAY > 0) {
                    REDEFINES101.WS_DD = LNCAPRDM.REC_DATA.PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                    WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            } else {
                if (LNCAPRDM.REC_DATA.PAY_DAY == 29 
                    || LNCAPRDM.REC_DATA.PAY_DAY == 30) {
                    if (REDEFINES101.WS_MM == 2) {
                        if (REDEFINES101.WS_YYYY % 100 == 0) {
                            if (REDEFINES101.WS_YYYY % 400 == 0) {
                                REDEFINES101.WS_DD = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                REDEFINES101.WS_DD = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        } else {
                            if (REDEFINES101.WS_YYYY % 4 == 0) {
                                REDEFINES101.WS_DD = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                REDEFINES101.WS_DD = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        }
                    } else {
                        REDEFINES101.WS_DD = LNCAPRDM.REC_DATA.PAY_DAY;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
                if (LNCAPRDM.REC_DATA.PAY_DAY == 31) {
                    if (REDEFINES101.WS_MM == 2) {
                        if (REDEFINES101.WS_YYYY % 100 == 0) {
                            if (REDEFINES101.WS_YYYY % 400 == 0) {
                                REDEFINES101.WS_DD = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                REDEFINES101.WS_DD = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        } else {
                            if (REDEFINES101.WS_YYYY % 4 == 0) {
                                REDEFINES101.WS_DD = 29;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            } else {
                                REDEFINES101.WS_DD = 28;
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            }
                        }
                    } else {
                        if (REDEFINES101.WS_MM == 4 
                            || REDEFINES101.WS_MM == 6 
                            || REDEFINES101.WS_MM == 9 
                            || REDEFINES101.WS_MM == 11) {
                            REDEFINES101.WS_DD = 30;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            REDEFINES101.WS_DD = 31;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES101);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                }
            }
        }
        SCCCLDT.DATE2 = WS_YYYYMMDD;
        CEP.TRC(SCCGWA, "TEST");
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_OCAL_END_DT = SCCCLDT.DATE2;
    }
    public void B100_STOP_INT_PROC() throws IOException,SQLException,Exception {
        if (WS_STOP_VAL_DATE == 0) {
            LNCLCCM.COMM_DATA.BEG_DATE = WS_SLCCM_BEG_DATE;
            LNCLCCM.COMM_DATA.END_DATE = WS_SLCCM_END_DATE;
            B100_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            if (WS_SLCCM_BEG_DATE < WS_STOP_VAL_DATE) {
                if (WS_SLCCM_END_DATE <= WS_STOP_VAL_DATE) {
                    CEP.TRC(SCCGWA, "STOP001");
                    LNCLCCM.COMM_DATA.BEG_DATE = WS_SLCCM_BEG_DATE;
                    LNCLCCM.COMM_DATA.END_DATE = WS_SLCCM_END_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
                if ((WS_SLCCM_END_DATE > WS_STOP_VAL_DATE 
                    && WS_SLCCM_END_DATE <= WS_STOP_DUE_DATE 
                    && WS_STOP_DUE_DATE != 0) 
                    || (WS_SLCCM_END_DATE > WS_STOP_VAL_DATE 
                    && WS_STOP_DUE_DATE == 0)) {
                    CEP.TRC(SCCGWA, "STOP002");
                    LNCLCCM.COMM_DATA.BEG_DATE = WS_SLCCM_BEG_DATE;
                    LNCLCCM.COMM_DATA.END_DATE = WS_STOP_VAL_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
                if (WS_SLCCM_END_DATE > WS_STOP_DUE_DATE 
                    && WS_STOP_DUE_DATE != 0) {
                    CEP.TRC(SCCGWA, "STOP0031");
                    LNCLCCM.COMM_DATA.BEG_DATE = WS_SLCCM_BEG_DATE;
                    LNCLCCM.COMM_DATA.END_DATE = WS_STOP_VAL_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                    LNCLCCM.COMM_DATA.BEG_DATE = WS_STOP_DUE_DATE;
                    LNCLCCM.COMM_DATA.END_DATE = WS_SLCCM_END_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "STOP0032");
                }
            }
            if (WS_SLCCM_BEG_DATE >= WS_STOP_VAL_DATE 
                && WS_SLCCM_BEG_DATE < WS_STOP_DUE_DATE) {
                if ((WS_SLCCM_END_DATE <= WS_STOP_DUE_DATE 
                    && WS_STOP_DUE_DATE != 0) 
                    || WS_STOP_DUE_DATE == 0) {
                    CEP.TRC(SCCGWA, "STOP004");
                    LNCLCCM.COMM_DATA.LC_AMT = 0;
                }
                if (WS_SLCCM_END_DATE > WS_STOP_DUE_DATE 
                    && WS_STOP_DUE_DATE != 0) {
                    CEP.TRC(SCCGWA, "STOP005");
                    LNCLCCM.COMM_DATA.BEG_DATE = WS_STOP_DUE_DATE;
                    LNCLCCM.COMM_DATA.END_DATE = WS_SLCCM_END_DATE;
                    B100_MAIN_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_SLCCM_BEG_DATE >= WS_STOP_DUE_DATE 
                && WS_STOP_DUE_DATE != 0) {
                CEP.TRC(SCCGWA, "STOP006");
                LNCLCCM.COMM_DATA.BEG_DATE = WS_SLCCM_BEG_DATE;
                LNCLCCM.COMM_DATA.END_DATE = WS_SLCCM_END_DATE;
                B100_MAIN_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCLCCM.COMM_DATA.TYPE == 'P' 
            || LNCLCCM.COMM_DATA.TYPE == 'C') {
            LNCLCCM.COMM_DATA.LC_TYPE = 'O';
            LNCLCCM.COMM_DATA.AMT = WS_I_O_AMT;
            CEP.TRC(SCCGWA, WS_I_O_AMT);
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.AMT);
            B110_CCB_HGRD_PRC();
            if (pgmRtn) return;
            WS_LCCM_LC_AMT_L = 0;
            WS_TEMP_CAL_L_AMT_L = 0;
            CEP.TRC(SCCGWA, "YYYYYYYYYY");
            CEP.TRC(SCCGWA, WS_CAL_OL_AMT);
            if (WS_CAL_OL_AMT != 0 
                && WS_CAL_INTEREST_TYP == 'C') {
                LNCLCCM.COMM_DATA.LC_TYPE = 'L';
                LNCLCCM.COMM_DATA.AMT = WS_CAL_OL_AMT;
                WS_CAL_END_DATE = 0;
                B110_CCB_HGRD_PRC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_CAL_INTEREST_TYP == 'C') {
                LNCLCCM.COMM_DATA.LC_TYPE = 'L';
                LNCLCCM.COMM_DATA.AMT = WS_CAL_OL_AMT;
                B110_CCB_HGRD_PRC();
                if (pgmRtn) return;
            }
        }
    }
    public void B110_CCB_HGRD_PRC() throws IOException,SQLException,Exception {
        if (LNCLCCM.COMM_DATA.LC_TYPE == 'O') {
            if (LNCAPRDM.REC_DATA.PRIN_DOG_MTH == ' ') {
                WS_HGRD_PROC_MTH = '1';
            } else {
                WS_HGRD_PROC_MTH = LNCAPRDM.REC_DATA.PRIN_DOG_MTH;
            }
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                WS_HGRD_PROC_MTH = '2';
            }
        } else {
            if (LNCAPRDM.REC_DATA.INT_DOG_MTH == ' ') {
                WS_HGRD_PROC_MTH = '2';
            } else {
                WS_HGRD_PROC_MTH = LNCAPRDM.REC_DATA.INT_DOG_MTH;
            }
        }
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, WS_HGRD_PROC_MTH);
        CEP.TRC(SCCGWA, WS_RCVD_DUE_DT);
        CEP.TRC(SCCGWA, LNRRCVD.OVD_DT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        if (LNRRCVD.OVD_DT == 0 
            || LNRRCVD.OVD_DT == LNRRCVD.DUE_DT 
            || LNCLCCM.COMM_DATA.BEG_DATE >= LNRRCVD.OVD_DT) {
            CEP.TRC(SCCGWA, "NO-KUANXIAN");
            B200_MAIN_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
            CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
            if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                bigD = new BigDecimal(WS_LCCM_L_AMT);
                WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            } else {
                WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                bigD = new BigDecimal(WS_LCCM_O_AMT);
                WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            WS_LCCM_BEG_DATE_H = LNCLCCM.COMM_DATA.BEG_DATE;
            WS_LCCM_END_DATE_H = LNCLCCM.COMM_DATA.END_DATE;
            if (WS_LCCM_END_DATE_F <= LNRRCVD.OVD_DT) {
                if (WS_HGRD_PROC_MTH == '1' 
                    || WS_HGRD_PROC_MTH == '3') {
                    WS_CAL_RAT = 'N';
                    B200_MAIN_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
                    if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                        WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                        bigD = new BigDecimal(WS_LCCM_L_AMT);
                        WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                    } else {
                        WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                        bigD = new BigDecimal(WS_LCCM_O_AMT);
                        WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                    }
                    CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                    CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                }
            } else {
                if (LNCLCCM.COMM_DATA.BEG_DATE < LNRRCVD.OVD_DT 
                        && LNCLCCM.COMM_DATA.END_DATE > LNRRCVD.OVD_DT) {
                    CEP.TRC(SCCGWA, "KUANXIAN002");
                    if (WS_HGRD_PROC_MTH == '1' 
                        || WS_HGRD_PROC_MTH == '2') {
                        B200_MAIN_PROC();
                        if (pgmRtn) return;
                        if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                            WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_L_AMT);
                            WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_O_AMT);
                            WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                    if (WS_HGRD_PROC_MTH == '3') {
                        WS_CAL_RAT = 'N';
                        LNCLCCM.COMM_DATA.END_DATE = LNRRCVD.OVD_DT;
                        B200_MAIN_PROC();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, "KUANXIAN002-1");
                        WS_CAL_RAT = ' ';
                        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
                        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
                        if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                            WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_L_AMT);
                            WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_O_AMT);
                            WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        }
                        CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                        CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                        WS_CAL_RAT = 'O';
                        LNCLCCM.COMM_DATA.BEG_DATE = LNRRCVD.OVD_DT;
                        LNCLCCM.COMM_DATA.END_DATE = WS_LCCM_END_DATE_H;
                        CEP.TRC(SCCGWA, "KUANXIAN002-2");
                        B200_MAIN_PROC();
                        if (pgmRtn) return;
                        WS_CAL_RAT = ' ';
                        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
                        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
                        if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                            WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_L_AMT);
                            WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_O_AMT);
                            WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        }
                        CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
                        CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
                    }
                    if (WS_HGRD_PROC_MTH == '4') {
                        LNCLCCM.COMM_DATA.BEG_DATE = LNRRCVD.OVD_DT;
                        CEP.TRC(SCCGWA, "KUANXIAN003");
                        B200_MAIN_PROC();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
                        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
                        if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                            WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_L_AMT);
                            WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_O_AMT);
                            WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                } else {
                    if (WS_HGRD_PROC_MTH == '1' 
                        || WS_HGRD_PROC_MTH == '2') {
                        B200_MAIN_PROC();
                        if (pgmRtn) return;
                        if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                            WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_L_AMT);
                            WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_O_AMT);
                            WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                    if (WS_HGRD_PROC_MTH == '3') {
                        WS_CAL_RAT = 'N';
                        CEP.TRC(SCCGWA, "KUANXIAN004");
                        B200_MAIN_PROC();
                        if (pgmRtn) return;
                        if (LNCLCCM.COMM_DATA.LC_TYPE == 'L') {
                            WS_LCCM_L_AMT = WS_LCCM_L_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_L_AMT);
                            WS_LCCM_L_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        } else {
                            WS_LCCM_O_AMT = WS_LCCM_O_AMT + WS_LCCM_LC_AMT;
                            bigD = new BigDecimal(WS_LCCM_O_AMT);
                            WS_LCCM_O_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                }
            }
        }
    }
    public void B100_BRW_IRUL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRIRUL);
        WS_IRUL_KEY.WS_IRUL_CODE = "TERMLON";
        LNRIRUL.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        LNRIRUL.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_IRUL_KEY.WS_IRUL_TYPE = LNCLCCM.COMM_DATA.LC_TYPE;
        WS_IRUL_KEY.WS_IRUL_SEQ = 1;
        LNRIRUL.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_IRUL_KEY);
        CEP.TRC(SCCGWA, LNRIRUL.KEY.CODE);
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
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_BRW_IRUL_INF();
        if (pgmRtn) return;
        WS_LCCM_LC_AMT = 0;
        WS_CAL_LC_BEG_DATE = LNCLCCM.COMM_DATA.BEG_DATE;
        WS_CAL_LC_END_DATE = LNCLCCM.COMM_DATA.END_DATE;
        WS_CAL_LC_AMT = LNCLCCM.COMM_DATA.AMT;
        CEP.TRC(SCCGWA, WS_CAL_LC_BEG_DATE);
        CEP.TRC(SCCGWA, WS_CAL_LC_END_DATE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.AMT);
        CEP.TRC(SCCGWA, WS_CAL_LC_AMT);
        B100_BRW_RATE_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.ITEM_CNT);
        for (WS_JJJ = 1; WS_JJJ <= LNCRATB.COMM_DATA.ITEM_CNT; WS_JJJ += 1) {
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
            B100_OVERDUE_INT_CAL();
            if (pgmRtn) return;
        }
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'U') {
            B300_UPD_TMP_LCCNTL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.FUNC_CODE);
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'A') {
            WS_LCCM_LC_AMT = WS_LCCM_LC_AMT + 0;
            bigD = new BigDecimal(WS_LCCM_LC_AMT);
            WS_LCCM_LC_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        } else {
            WS_LCCM_LC_AMT = WS_LCCM_LC_AMT + 0;
            bigD = new BigDecimal(WS_LCCM_LC_AMT);
            WS_LCCM_LC_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        }
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'U') {
            B200_UPDT_RCVD();
            if (pgmRtn) return;
        }
    }
    public void B100_BRW_RATE_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_IRUL_KEY.WS_IRUL_TYPE);
        CEP.TRC(SCCGWA, "IRUL-TYPE20180627000003");
        IBS.init(SCCGWA, LNCRATB);
        LNCRATB.COMM_DATA.LN_AC = LNCLCCM.COMM_DATA.LN_AC;
        LNCRATB.COMM_DATA.SUF_NO = LNCLCCM.COMM_DATA.SUF_NO;
        LNCRATB.COMM_DATA.RATE_TYPE = WS_IRUL_KEY.WS_IRUL_TYPE;
        LNCRATB.COMM_DATA.BEG_DATE = WS_CAL_LC_BEG_DATE;
        LNCRATB.COMM_DATA.END_DATE = WS_CAL_LC_END_DATE;
        if (WS_CAL_RAT == 'N') {
            LNCRATB.COMM_DATA.RATE_TYPE = K_NORMAL_RATE;
        }
        CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.RATE_TYPE);
        S000_CALL_LNZRATB();
        if (pgmRtn) return;
    }
    public void B100_OVERDUE_INT_CAL() throws IOException,SQLException,Exception {
        WS_FORM_CODE = "01";
        if (WS_FORM_CODE.equalsIgnoreCase("01")) {
            WS_CAL_INT_BEG_DT = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE;
            WS_CAL_INT_END_DT = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].E_DATE;
            WS_RATE = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE;
            B100_INT_FM01();
            if (pgmRtn) return;
        } else {
            WS_CAL_INT_BEG_DT = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE;
            WS_CAL_INT_END_DT = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].E_DATE;
            WS_RATE = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE;
            B100_INT_OTHR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
        WS_LCCM_LC_AMT += WS_INT_AMT1;
        WS_CTNR_INT += WS_INT_AMT1;
        WS_LCCM_LC_AMT_L = WS_CAL_LC_AMT;
        CEP.TRC(SCCGWA, WS_INT_AMT1);
        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT);
        CEP.TRC(SCCGWA, WS_CAL_INTEREST_TYP);
        WS_TEMP_CAL_L_AMT = WS_LCCM_LC_AMT;
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'U') {
            B100_ADD_LC_LNTINTD();
            if (pgmRtn) return;
        }
    }
    public void B200_OVERDUE_INT_CAL_LC() throws IOException,SQLException,Exception {
        WS_FORM_CODE = "01";
        if (WS_FORM_CODE.equalsIgnoreCase("01")) {
            WS_CAL_INT_BEG_DT = LNCRATC.COMM_DATA.IETM_RATES[WS_XXX-1].B_DATE;
            WS_CAL_INT_END_DT = LNCRATC.COMM_DATA.IETM_RATES[WS_XXX-1].E_DATE;
            WS_RATE = LNCRATC.COMM_DATA.IETM_RATES[WS_XXX-1].RATE;
            B100_INT_FM01();
            if (pgmRtn) return;
        } else {
            WS_CAL_INT_BEG_DT = LNCRATC.COMM_DATA.IETM_RATES[WS_XXX-1].B_DATE;
            WS_CAL_INT_END_DT = LNCRATC.COMM_DATA.IETM_RATES[WS_XXX-1].E_DATE;
            WS_RATE = LNCRATC.COMM_DATA.IETM_RATES[WS_XXX-1].RATE;
            B100_INT_OTHR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
        WS_LCCM_LC_AMT_L += WS_INT_AMT1;
        WS_CTNR_INT_L += WS_INT_AMT1;
        CEP.TRC(SCCGWA, WS_INT_AMT1);
        CEP.TRC(SCCGWA, WS_LCCM_LC_AMT_L);
        WS_TEMP_CAL_L_AMT_L = WS_LCCM_LC_AMT_L;
        if (WS_LCCM_LC_AMT_L != 0) {
            WS_ROUND_AMOUNT = WS_LCCM_LC_AMT_L;
            R00_ROUND_PROCESS();
            if (pgmRtn) return;
            WS_LCCM_LC_AMT_L = WS_ROUND_OUT_AMT;
        }
    }
    public void B100_INT_FM01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE;
        SCCCLDT.DATE2 = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].E_DATE;
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_CAL_LC_AMT);
        CEP.TRC(SCCGWA, WS_RATE);
        CEP.TRC(SCCGWA, WS_DAYS);
        WS_INT_AMT1 = WS_CAL_LC_AMT * WS_RATE * WS_DAYS / ( 100 * WS_DAYS_BAS );
        bigD = new BigDecimal(WS_INT_AMT1);
        WS_INT_AMT1 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void B100_INT_OTHR() throws IOException,SQLException,Exception {
        WS_INT_AMT1 = 0;
        WS_BAL = WS_CAL_LC_AMT;
        WS_FST_ONE_ID = ' ';
        WS_LST_ONE_ID = ' ';
        LNRRCVD.KEY.TERM += 1;
        T00_READ_LNTRCVD();
        if (pgmRtn) return;
        for (WS_III = 1; LNRRCVD.DUE_DT < WS_CAL_INT_END_DT; WS_III += 1) {
            if (LNRRCVD.DUE_DT > WS_CAL_INT_BEG_DT) {
                WS_J += 1;
                if (WS_J == 1) {
                    if (WS_CAL_INT_BEG_DT > WS_BEG_DATE) {
                        WS_FST_ONE_ID = 'N';
                    }
                    WS_BEG_DATE = WS_CAL_INT_BEG_DT;
                }
                WS_END_DATE = LNRRCVD.DUE_DT;
                B101_CAL_LC();
                if (pgmRtn) return;
            }
            WS_BEG_DATE = LNRRCVD.DUE_DT;
            LNRRCVD.KEY.TERM += 1;
            T00_READ_LNTRCVD();
            if (pgmRtn) return;
        }
        WS_END_DATE = WS_CAL_INT_END_DT;
        if (LNRRCVD.DUE_DT > WS_CAL_INT_END_DT) {
            WS_LST_ONE_ID = 'N';
        }
        B101_CAL_LC();
        if (pgmRtn) return;
    }
    public void B101_CAL_LC() throws IOException,SQLException,Exception {
        if (WS_FORM_CODE.equalsIgnoreCase("02")) {
            B100_INT_FM02();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("03")) {
            B100_INT_FM03();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("21")) {
            B100_INT_FM21();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("22")) {
            B100_INT_FM22();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("23")) {
            B100_INT_FM23();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_INT_FM02() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_LC_AMT);
        CEP.TRC(SCCGWA, WS_RATE);
        CEP.TRC(SCCGWA, WS_DAYS);
        WS_INT_AMT = WS_CAL_LC_AMT * WS_RATE / 1200;
        bigD = new BigDecimal(WS_INT_AMT);
        WS_INT_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        if (WS_J == 1 
            && WS_FST_ONE_ID == 'N') {
            WS_INT_AMT1 += 0;
        } else {
            WS_INT_AMT1 += WS_INT_AMT;
        }
    }
    public void B100_INT_FM03() throws IOException,SQLException,Exception {
        if (WS_J == 1 
            && WS_FST_ONE_ID == 'N') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_BEG_DATE;
            SCCCLDT.DATE2 = WS_END_DATE;
            R00_CAL_DATE();
            if (pgmRtn) return;
            WS_DAYS = (short) SCCCLDT.DAYS;
            CEP.TRC(SCCGWA, WS_CAL_LC_AMT);
            CEP.TRC(SCCGWA, WS_RATE);
            CEP.TRC(SCCGWA, WS_DAYS);
            WS_INT_AMT = WS_CAL_LC_AMT * WS_RATE * WS_DAYS / ( 100 * WS_DAYS_BAS );
            bigD = new BigDecimal(WS_INT_AMT);
            WS_INT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        } else {
            if (WS_LST_ONE_ID == 'N') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_BEG_DATE;
                SCCCLDT.DATE2 = WS_END_DATE;
                R00_CAL_DATE();
                if (pgmRtn) return;
                WS_DAYS = (short) SCCCLDT.DAYS;
                CEP.TRC(SCCGWA, WS_CAL_LC_AMT);
                CEP.TRC(SCCGWA, WS_RATE);
                CEP.TRC(SCCGWA, WS_DAYS);
                WS_INT_AMT = WS_CAL_LC_AMT * WS_RATE * WS_DAYS / ( 100 * WS_DAYS_BAS );
                bigD = new BigDecimal(WS_INT_AMT);
                WS_INT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            } else {
                WS_INT_AMT = WS_CAL_LC_AMT * WS_RATE / 1200;
                bigD = new BigDecimal(WS_INT_AMT);
                WS_INT_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
        }
        WS_INT_AMT1 += WS_INT_AMT;
    }
    public void B100_INT_FM21() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_BEG_DATE;
        SCCCLDT.DATE2 = WS_END_DATE;
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_R_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_BAL);
        CEP.TRC(SCCGWA, WS_R_DAYS);
        WS_INT_AMT = WS_BAL * WS_RATE * WS_R_DAYS / ( 100 * WS_DAYS_BAS );
        bigD = new BigDecimal(WS_INT_AMT);
        WS_INT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        WS_BAL += WS_INT_AMT;
        WS_INT_AMT1 += WS_INT_AMT;
    }
    public void B100_INT_FM22() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_BEG_DATE;
        SCCCLDT.DATE2 = WS_END_DATE;
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_R_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_BAL);
        CEP.TRC(SCCGWA, WS_R_DAYS);
        WS_INT_AMT = WS_BAL * WS_RATE * WS_R_DAYS / ( 100 * WS_DAYS_BAS );
        bigD = new BigDecimal(WS_INT_AMT);
        WS_INT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        WS_BAL += WS_INT_AMT;
        WS_INT_AMT1 += WS_INT_AMT;
    }
    public void B100_INT_FM23() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_BEG_DATE;
        SCCCLDT.DATE2 = WS_END_DATE;
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_R_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_BAL);
        CEP.TRC(SCCGWA, WS_R_DAYS);
        WS_INT_AMT = WS_BAL * WS_RATE * WS_R_DAYS / ( 100 * WS_DAYS_BAS );
        bigD = new BigDecimal(WS_INT_AMT);
        WS_INT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        WS_BAL += WS_INT_AMT;
        WS_INT_AMT1 += WS_INT_AMT;
    }
    public void B100_ADD_LC_LNTINTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCINTDM);
        LNCINTDM.FUNC = '0';
        LNCINTDM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNCINTDM.REC_DATA.KEY.REPY_MTH = LNCLCCM.COMM_DATA.TYPE;
        LNCINTDM.REC_DATA.KEY.TERM = LNCLCCM.COMM_DATA.TERM;
        if (LNCLCCM.COMM_DATA.LC_TYPE == 'O') {
            LNCINTDM.REC_DATA.KEY.INT_TYP = 'O';
        } else {
            LNCINTDM.REC_DATA.KEY.INT_TYP = 'L';
        }
        LNCINTDM.REC_DATA.KEY.IRUL_SEQ = WS_IRUL_KEY.WS_IRUL_SEQ;
        LNCINTDM.REC_DATA.KEY.CTNR_NO = 0;
        LNCINTDM.REC_DATA.KEY.VAL_DT = WS_CAL_INT_BEG_DT;
        LNCINTDM.REC_DATA.CUT_OFF_DT = WS_CAL_INT_BEG_DT;
        LNCINTDM.REC_DATA.INT_AMT = WS_CAL_LC_AMT;
        LNCINTDM.REC_DATA.INT_RAT = WS_RATE;
        LNCLCCM.COMM_DATA.RATE = WS_RATE;
        LNCINTDM.REC_DATA.INT = WS_INT_AMT1;
        if (LNCINTDM.REC_DATA.INT != 0) {
            S000_CALL_LNZINTDM();
            if (pgmRtn) return;
        }
    }
    public void B200_UPDT_RCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNCLCCM.COMM_DATA.TYPE;
        LNCRCVDM.REC_DATA.KEY.TERM = LNCLCCM.COMM_DATA.TERM;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        LNCRCVDM.FUNC = '2';
        LNCRCVDM.REC_DATA.PI_STOP_DT = LNCLCCM.COMM_DATA.END_DATE;
        if (LNCLCCM.COMM_DATA.LC_TYPE == 'O') {
            LNCRCVDM.REC_DATA.O_REC_AMT += LNCLCCM.COMM_DATA.LC_AMT;
        } else {
            LNCRCVDM.REC_DATA.L_REC_AMT += LNCLCCM.COMM_DATA.LC_AMT;
        }
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
    }
    public void B300_UPD_TMP_LCCNTL() throws IOException,SQLException,Exception {
        R01_ROUND_PROCESS();
        if (pgmRtn) return;
        B310_UPD_CTNR_BALL();
        if (pgmRtn) return;
    }
    public void R01_ROUND_PROCESS() throws IOException,SQLException,Exception {
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_LOW_CCY_INT_AMT = WS_CTNR_INT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT);
            WS_LOW_CCY_INT_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_CTNR_AMT = WS_LOW_CCY_INT_AMT;
        }
        if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_LOW_CCY_INT_AMT1 = WS_CTNR_INT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT1);
            WS_LOW_CCY_INT_AMT1 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_CTNR_AMT = WS_LOW_CCY_INT_AMT1;
        }
        if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_LOW_CCY_INT_AMT2 = WS_CTNR_INT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT2);
            WS_LOW_CCY_INT_AMT2 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_CTNR_AMT = WS_LOW_CCY_INT_AMT2;
        }
    }
    public void B310_UPD_CTNR_BALL() throws IOException,SQLException,Exception {
        if (WS_IRUL_KEY.WS_IRUL_TYPE == 'L') {
            if ("27".trim().length() == 0) WS_NO = 0;
            else WS_NO = Short.parseShort("27");
        } else {
            if ("30".trim().length() == 0) WS_NO = 0;
            else WS_NO = Short.parseShort("30");
        }
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '4';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        LNCBALLM.FUNC = '2';
        WS_LOAN_CONT_AREA.WS_LOAN_CONT_AREA_TEXT1.WS_LOAN_CONT[WS_NO-1].WS_LOAN_BAL += WS_CTNR_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], LNCBALLM.REC_DATA.REDEFINES18);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        WS_CTNR_INT = 0;
    }
    public void B400_ADJ_LC_PROC() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(27 - 1, 27 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(28 - 1, 28 + 1 - 1));
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
        CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
        S00_READ_LNTINTA();
        if (pgmRtn) return;
        if (WS_INTAO_FOUND_FLG == 'Y' 
            || WS_INTAL_FOUND_FLG == 'Y') {
            if (LNCLCCM.COMM_DATA.TYPE == 'P') {
                IBS.init(SCCGWA, LNRINTA);
                LNRINTA.KEY.REPY_MTH = 'O';
                B410_ADJ_LC_PROCESS();
                if (pgmRtn) return;
                WS_ADJ_O_AMT = WS_ADJ_LC_AMT;
            } else if (LNCLCCM.COMM_DATA.TYPE == 'I') {
                IBS.init(SCCGWA, LNRINTA);
                LNRINTA.KEY.REPY_MTH = 'L';
                B410_ADJ_LC_PROCESS();
                if (pgmRtn) return;
                WS_ADJ_L_AMT = WS_ADJ_LC_AMT;
            } else {
                IBS.init(SCCGWA, LNRINTA);
                LNRINTA.KEY.REPY_MTH = 'O';
                B410_ADJ_LC_PROCESS();
                if (pgmRtn) return;
                WS_ADJ_O_AMT = WS_ADJ_LC_AMT;
                IBS.init(SCCGWA, LNRINTA);
                LNRINTA.KEY.REPY_MTH = 'L';
                B410_ADJ_LC_PROCESS();
                if (pgmRtn) return;
                WS_ADJ_L_AMT = WS_ADJ_LC_AMT;
            }
            CEP.TRC(SCCGWA, WS_LCCM_O_AMT);
            CEP.TRC(SCCGWA, WS_LCCM_L_AMT);
            CEP.TRC(SCCGWA, WS_ADJ_O_AMT);
            CEP.TRC(SCCGWA, WS_ADJ_L_AMT);
            if (WS_ADJ_O_AMT + WS_LCCM_O_AMT_TOL < 0) {
                WS_LCCM_O_AMT_TOL = 0;
            } else {
                WS_LCCM_O_AMT_TOL = WS_ADJ_O_AMT + WS_LCCM_O_AMT_TOL;
                bigD = new BigDecimal(WS_LCCM_O_AMT_TOL);
                WS_LCCM_O_AMT_TOL = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            }
            if (WS_ADJ_L_AMT + WS_LCCM_L_AMT_TOL < 0) {
                WS_LCCM_L_AMT_TOL = 0;
            } else {
                WS_LCCM_L_AMT_TOL = WS_ADJ_L_AMT + WS_LCCM_L_AMT_TOL;
                bigD = new BigDecimal(WS_LCCM_L_AMT_TOL);
                WS_LCCM_L_AMT_TOL = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            }
        }
    }
    public void S00_READ_LNTINTA() throws IOException,SQLException,Exception {
        WS_INTAO_FOUND_FLG = 'N';
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTA.KEY.SUB_CTA_NO = 0;
        else LNRINTA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRINTA.KEY.REPY_MTH = 'O';
        LNRINTA.KEY.REPY_TERM = LNCLCCM.COMM_DATA.TERM;
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND ADJ_SEQ > :LNRINTA.KEY.ADJ_SEQ";
        LNTINTA_RD.set = "WS-SUM-O-AMT=IFNULL(SUM(ADJ_AMT),0)";
        IBS.GROUP(SCCGWA, LNRINTA, this, LNTINTA_RD);
        CEP.TRC(SCCGWA, WS_SUM_O_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (WS_SUM_O_AMT > 0) {
                WS_INTAO_FOUND_FLG = 'Y';
            }
        }
        WS_INTAL_FOUND_FLG = 'N';
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTA.KEY.SUB_CTA_NO = 0;
        else LNRINTA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRINTA.KEY.REPY_MTH = 'L';
        LNRINTA.KEY.REPY_TERM = LNCLCCM.COMM_DATA.TERM;
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND ADJ_SEQ > :LNRINTA.KEY.ADJ_SEQ";
        LNTINTA_RD.set = "WS-SUM-L-AMT=IFNULL(SUM(ADJ_AMT),0)";
        IBS.GROUP(SCCGWA, LNRINTA, this, LNTINTA_RD);
        CEP.TRC(SCCGWA, WS_SUM_L_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (WS_SUM_L_AMT > 0) {
                WS_INTAL_FOUND_FLG = 'Y';
            }
        }
    }
    public void B500_ADJ_WAV_PROC() throws IOException,SQLException,Exception {
        if (LNRRCVD.O_WAV_AMT > 0) {
            WS_LCCM_O_AMT_TOL = WS_LCCM_O_AMT_TOL - LNRRCVD.O_WAV_AMT;
            bigD = new BigDecimal(WS_LCCM_O_AMT_TOL);
            WS_LCCM_O_AMT_TOL = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (WS_LCCM_O_AMT_TOL < 0) {
                WS_LCCM_O_AMT_TOL = 0;
            }
        }
        if (LNRRCVD.L_WAV_AMT > 0) {
            WS_LCCM_L_AMT_TOL = WS_LCCM_L_AMT_TOL - LNRRCVD.L_WAV_AMT;
            bigD = new BigDecimal(WS_LCCM_L_AMT_TOL);
            WS_LCCM_L_AMT_TOL = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (WS_LCCM_L_AMT_TOL < 0) {
                WS_LCCM_L_AMT_TOL = 0;
            }
        }
    }
    public void B410_ADJ_LC_PROCESS() throws IOException,SQLException,Exception {
        WS_ADJ_LC_AMT = 0;
        WS_ADJ_VAL_DT = LNCLCCM.COMM_DATA.BEG_DATE;
        WS_INTA_FOUND_FLG = 'N';
        T00_STARTBR_LNTINTA();
        if (pgmRtn) return;
        T00_READNEXT_LNTINTA();
        if (pgmRtn) return;
        while (WS_INTA_FOUND_FLG != 'N') {
            CEP.TRC(SCCGWA, LNRINTA.ADJ_VAL_DT);
            CEP.TRC(SCCGWA, WS_ADJ_VAL_DT);
            CEP.TRC(SCCGWA, LNRINTA.ADJ_AMT);
            WS_ADJ_LC_AMT += LNRINTA.ADJ_AMT;
            T00_READNEXT_LNTINTA();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTINTA();
        if (pgmRtn) return;
    }
    public void T00_STARTBR_LNTINTA() throws IOException,SQLException,Exception {
        LNRINTA.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTA.KEY.SUB_CTA_NO = 0;
        else LNRINTA.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRINTA.KEY.REPY_TERM = LNCLCCM.COMM_DATA.TERM;
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTA_FOUND_FLG = 'Y';
        } else {
            WS_INTA_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void BS01_CCB_INTDAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIDAY);
        BPCIDAY.I_CALR_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        BPCIDAY.I_BEGIN_DATE = LNCLCCM.COMM_DATA.BEG_DATE;
        BPCIDAY.I_END_DATE = LNCLCCM.COMM_DATA.END_DATE;
        S000_CALL_BPZCIDAY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHT000422");
        WS_DAYS_BAS = BPCIDAY.OUTPUT.O_STD_DAYS;
        WS_DAYS_CNT = BPCIDAY.OUTPUT.O_ORD_DAYS;
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_LEAP_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_ORD_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_STD_DAYS);
    }
    public void S000_CALL_BPZCIDAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CALC-INT-DAYS", BPCIDAY);
        if (BPCIDAY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTRCVD2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRRCVD.DUE_DT = WS_RCVD_DUE_DT;
        LNRRCVD.KEY.AMT_TYP = 'P';
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO,DUE_DT";
        LNTRCVD_RD.where = "AMT_TYP < > :LNRRCVD.KEY.AMT_TYP";
        LNTRCVD_RD.fst = true;
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            LNRRCVD.VAL_DT = WS_RCVD_DUE_DT;
        }
    }
    public void T00_READ_LNTRCVD3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRRCVD.KEY.TERM = LNCLCCM.COMM_DATA.TERM;
        LNRRCVD.KEY.AMT_TYP = LNCLCCM.COMM_DATA.TYPE;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUBS_PROJ_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY);
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T00_READ_LNTRCVD1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRRCVD.KEY.TERM = LNCLCCM.COMM_DATA.TERM;
        LNRRCVD.KEY.AMT_TYP = LNCLCCM.COMM_DATA.TYPE;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_TYPE);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUBS_PROJ_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.IBS_HASH);
        CEP.TRC(SCCGWA, LNRRCVD.KEY);
        if (LNCLCCM.COMM_DATA.FUNC_CODE == 'A') {
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            LNTRCVD_RD.upd = true;
            IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        } else {
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        }
        CEP.TRC(SCCGWA, LNRRCVD.CLR_TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T000_REWRITE_LNTRCVD1() throws IOException,SQLException,Exception {
        LNRRCVD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRCVD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T00_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNRRCVD.KEY.CONTRACT_NO = LNCLCCM.COMM_DATA.LN_AC;
        if (LNCLCCM.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCLCCM.COMM_DATA.SUF_NO);
        LNRRCVD.KEY.TERM = LNCLCCM.COMM_DATA.TERM;
        LNRRCVD.KEY.AMT_TYP = LNCLCCM.COMM_DATA.TYPE;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_I += 1;
            R00_CAL_NEXT_DUE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_ROUND_PROCESS() throws IOException,SQLException,Exception {
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_LOW_CCY_INT_AMT = WS_ROUND_AMOUNT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT);
            WS_LOW_CCY_INT_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_ROUND_OUT_AMT = WS_LOW_CCY_INT_AMT;
        }
        if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_LOW_CCY_INT_AMT1 = WS_ROUND_AMOUNT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT1);
            WS_LOW_CCY_INT_AMT1 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_ROUND_OUT_AMT = WS_LOW_CCY_INT_AMT1;
        }
        if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_LOW_CCY_INT_AMT2 = WS_ROUND_AMOUNT + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT2);
            WS_LOW_CCY_INT_AMT2 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_ROUND_OUT_AMT = WS_LOW_CCY_INT_AMT2;
        }
    }
    public void R00_CAL_NEXT_DUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNRRCVD.DUE_DT;
        if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
            SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
        } else {
            SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
        }
        if (SCCCLDT.DAYS == 0 
            && SCCCLDT.MTHS == 0) {
            LNRRCVD.DUE_DT = LNCLCCM.COMM_DATA.END_DATE;
        } else {
            R00_CAL_DATE();
            if (pgmRtn) return;
            LNRRCVD.DUE_DT = SCCCLDT.DATE2;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCLCCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCLCCM.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCLCCM.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZINTDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-INTD-MAINT", LNCINTDM);
        if (LNCINTDM.RC.RC_RTNCODE != 0) {
            LNCLCCM.RC.RC_APP = LNCINTDM.RC.RC_APP;
            LNCLCCM.RC.RC_RTNCODE = LNCINTDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCVDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        if (LNCRCVDM.RC.RC_RTNCODE != 0) {
            LNCLCCM.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCLCCM.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIRULB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-IRUL-BRWQ", LNCIRULB);
        if (LNCIRULB.RC.RC_RTNCODE != 0) {
            LNCLCCM.RC.RC_APP = LNCIRULB.RC.RC_APP;
            LNCLCCM.RC.RC_RTNCODE = LNCIRULB.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATH-BRWQ", LNCRATB);
        if (LNCRATB.RC.RC_RTNCODE != 0) {
            LNCLCCM.RC.RC_APP = LNCRATB.RC.RC_APP;
            LNCLCCM.RC.RC_RTNCODE = LNCRATB.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            LNCLCCM.RC.RC_APP = LNCBALLM.RC.RC_APP;
            LNCLCCM.RC.RC_RTNCODE = LNCBALLM.RC.RC_RTNCODE;
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
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, "=======SCSSCLDT-RC========");
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        CEP.TRC(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCLCCM.RC.RC_APP = "SC";
            LNCLCCM.RC.RC_RTNCODE = SCCCLDT.RC;
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
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCBATH.PROC_NAME == null) SCCBATH.PROC_NAME = "";
        JIBS_tmp_int = SCCBATH.PROC_NAME.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) SCCBATH.PROC_NAME += " ";
        if (LNCLCCM.RC.RC_RTNCODE != 0 
            && SCCBATH.PROC_NAME.substring(0, 5).equalsIgnoreCase("CNLND")) {
            CEP.TRC(SCCGWA, "LNCLCCM:");
            CEP.TRC(SCCGWA, LNCLCCM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
