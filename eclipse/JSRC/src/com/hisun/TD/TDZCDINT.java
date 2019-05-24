package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DP.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class TDZCDINT {
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    BigDecimal bigD;
    int JIBS_tmp_int;
    brParm TDTCALL_BR = new brParm();
    DBParm TDTCALL_RD;
    DBParm TDTDINT_RD;
    DBParm TDTAINT_RD;
    brParm TDTIREV_BR = new brParm();
    DBParm TDTIREV_RD;
    brParm TDTINTC_BR = new brParm();
    brParm TDTDOCU_BR = new brParm();
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    int K_OLD_ZDATE = 19930301;
    int K_OLD_TDATE = 19981207;
    int K_05_DATE = 20050921;
    int K_09_DATE = 20090320;
    int K_TAX_DATE = 19991101;
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_HQ_BASE_TYP_TY = "F01";
    String K_HQ_BASE_TYP_DD = "A01";
    String K_HQ_TENOR = "D000";
    String K_TD_BASE_TYP = "A02";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_MSGID = " ";
    String WS_TERM = " ";
    TDZCDINT_REDEFINES5 REDEFINES5 = new TDZCDINT_REDEFINES5();
    String WS_IRAT_CD = " ";
    String WS_TERM2 = " ";
    TDZCDINT_REDEFINES10 REDEFINES10 = new TDZCDINT_REDEFINES10();
    char WS_XC_ALREADY = ' ';
    double WS_RAT_XC_T = 0;
    String WS_DISPLAY = " ";
    int WS_INDEX_DATE = 0;
    int WS_NEW_DATE1 = 0;
    double WS_DD_RAT = 0;
    double WS_RAT = 0;
    double WS_APPO_AMT = 0;
    double WS_AMT = 0;
    double WS_AMT_2 = 0;
    long WS_AMT_0 = 0;
    double WS_AMT_W = 0;
    double WS_INT_2 = 0;
    double WS_INT_3 = 0;
    double WS_INT_4 = 0;
    double WS_INT_5 = 0;
    double WS_AMT_3 = 0;
    double WS_AMT_4 = 0;
    double WS_PRV_INT = 0;
    double WS_TRANS_INT = 0;
    double WS_DUE_INT = 0;
    double WS_INT_TMP = 0;
    double WS_INT_TMP_1 = 0;
    double WS_INT_TMP_10 = 0;
    double WS_INT_TMP_LAST = 0;
    double WS_TRANS_3 = 0;
    double WS_TAXING_INT = 0;
    double WS_PAYING_TAX = 0;
    double WS_INT_ADD = 0;
    double WS_INT_TAX = 0;
    double WS_TAX_INT = 0;
    double WS_INT_TAX_ALL = 0;
    double WS_INT_TAX_5 = 0;
    double WS_INT_TAX_TIM = 0;
    double WS_TAX_INT_ALL = 0;
    double WS_DINT_BAL = 0;
    double WS_TAX_TMP = 0;
    double WS_NACCU = 0;
    double WS_SACCU = 0;
    double WS_LACCU = 0;
    double WS_SACCU_L = 0;
    double WS_LACCU_L = 0;
    double WS_NACCU_TAX = 0;
    double WS_SACCU_TAX = 0;
    double WS_LACCU_TAX = 0;
    double WS_ACCU = 0;
    int WS_APPO_DATE = 0;
    short WS_BASE_DAYS = 0;
    int WS_DAYS = 0;
    int WS_ALL_DAYS = 0;
    short WS_SEQ = 0;
    short WS_DEC = 0;
    short WS_LEN = 0;
    short WS_N = 0;
    short WS_Q = 0;
    short WS_P = 0;
    short WS_X = 0;
    short WS_MTHS = 0;
    TDZCDINT_WS_DOCU_INFO[] WS_DOCU_INFO = new TDZCDINT_WS_DOCU_INFO[30];
    TDZCDINT_WS_DOCU_INFO_TMP WS_DOCU_INFO_TMP = new TDZCDINT_WS_DOCU_INFO_TMP();
    double WS_TAX_VAL = 0;
    double WS_TAX_VAL_LAST = 0;
    int WS_FIRST_VAL_DATE = 0;
    int WS_TERM_DATE1 = 0;
    int WS_XC_DATE1 = 0;
    int WS_XC_DATE2 = 0;
    int WS_TAX_DATE1 = 0;
    int WS_TAX_DATE2 = 0;
    int WS_VAL_DATE = 0;
    TDZCDINT_REDEFINES102 REDEFINES102 = new TDZCDINT_REDEFINES102();
    int WS_TXN_DATE = 0;
    TDZCDINT_REDEFINES107 REDEFINES107 = new TDZCDINT_REDEFINES107();
    int WS_DATE3 = 0;
    int WS_DATE1 = 0;
    TDZCDINT_REDEFINES113 REDEFINES113 = new TDZCDINT_REDEFINES113();
    int WS_DATE2 = 0;
    TDZCDINT_REDEFINES118 REDEFINES118 = new TDZCDINT_REDEFINES118();
    int WS_DATE2_TMP = 0;
    int WS_DATE2_LAST = 0;
    short WS_YY = 0;
    short WS_MM = 0;
    int WS_DD = 0;
    double WS_YY_TACCU = 0;
    double WS_MM_TACCU = 0;
    double WS_DD_TACCU = 0;
    char WS_RULE = ' ';
    char WS_REG_FLG = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_TZ_TERM = 0;
    String WS_IRAT_CD2 = " ";
    char WS_MATCH = ' ';
    char WS_RESULT_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DOCU_FLG = ' ';
    char WS_IREV_FLG = ' ';
    char WS_ITAX_FLG = ' ';
    char WS_DINT_FLG = ' ';
    char WS_TERM_INF_FLG = ' ';
    char WS_TEMR_FND_FLG = ' ';
    char WS_FD_FLG = ' ';
    double WS_INT_TEMP = 0;
    double WS_TMP_CDINT_RAT = 0;
    int WS_TMP_EXP_DATE = 0;
    int WS_TMP_VAL_DATE = 0;
    char WS_START_BR_FLG = ' ';
    int WS_ZNT_ALL_DAYS = 0;
    int WS_ZNT_TD_DAYS = 0;
    int WS_ZNT_DD_DAYS = 0;
    int WS_ZNT_TD_EXP_DT = 0;
    char WS_CCY_FOUND = ' ';
    short WS_W = 0;
    double WS_MIN_DRAW_AMT = 0;
    short WS_CNT = 0;
    int WS_TMP_DAYS = 0;
    int WS_TMP_DATE1 = 0;
    int WS_TMP_DATE2 = 0;
    char WS_TAX_FLG = ' ';
    char WS_CALD_FLG = ' ';
    TDZCDINT_WS_SAVRT_CODE WS_SAVRT_CODE = new TDZCDINT_WS_SAVRT_CODE();
    double WS_EXC_RAT = 0;
    TDZCDINT_WS_PRDCODE WS_PRDCODE = new TDZCDINT_WS_PRDCODE();
    char WS_UNCOUNT_FLG = ' ';
    int WS_TOT_MTHS = 0;
    int WS_TOT_DAYS = 0;
    int WS_DAY_CNT = 0;
    int WS_MTH_CNT = 0;
    int WS_ZNT_TMP_DT = 0;
    int WS_MTH_TOT_CNT = 0;
    char WS_FIRST_FLG = ' ';
    char WS_FST_FLG = ' ';
    char WS_TQ_FLG = ' ';
    char WS_GRT_MAMT_FLG = ' ';
    String WS_ZNT_TERM = " ";
    String WS_ZNT_IRAT_CD = " ";
    double WS_ZNT_RATE = 0;
    short WS_SER_TIME = 0;
    double WS_MIN_DRAW_AMT_USD = 0;
    double WS_BUY_AMT = 0;
    double WS_SELL_AMT = 0;
    double WS_TTZ_DINT_BAL = 0;
    short WS_PLAN_NUM = 0;
    short WS_TERM_MTHS2 = 0;
    short WS_SHD_NUM = 0;
    double WS_NACCU_INTC = 0;
    int WS_D_I = 0;
    TDZCDINT_WS_DOCU[] WS_DOCU = new TDZCDINT_WS_DOCU[30];
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCITAXG BPCITAXG = new BPCITAXG();
    TDCCDS TDCCDS = new TDCCDS();
    TDRINTC TDRINTC = new TDRINTC();
    TDRCALL TDRCALL = new TDRCALL();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCSAVRT TDCSAVRT = new TDCSAVRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCUINTI BPCUINTI = new BPCUINTI();
    TDRAINT TDRAINT = new TDRAINT();
    TDRIREV TDRIREV = new TDRIREV();
    TDRDINT TDRDINT = new TDRDINT();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    TDCUIRUL TDCUIRUL = new TDCUIRUL();
    BPCFX BPCFX = new BPCFX();
    String WS_AC = " ";
    int WS_VAL = 0;
    SCCGWA SCCGWA;
    TDCCDINT TDCCDINT;
    TDCPIOD TDCPIOD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public TDZCDINT() {
        for (int i=0;i<30;i++) WS_DOCU_INFO[i] = new TDZCDINT_WS_DOCU_INFO();
        for (int i=0;i<30;i++) WS_DOCU[i] = new TDZCDINT_WS_DOCU();
    }
    public void MP(SCCGWA SCCGWA, TDCCDINT TDCCDINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCDINT = TDCCDINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCDINT.DAYS);
        CEP.TRC(SCCGWA, "OUTCDINT");
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        CEP.TRC(SCCGWA, TDCCDINT.CI_TYP);
        CEP.TRC(SCCGWA, TDCCDINT.DOCU_NO);
        CEP.TRC(SCCGWA, "TDZCDINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        TDCPIOD = (TDCPIOD) TDCCDINT.PRDP_PTR;
        CEP.TRC(SCCGWA, TDCPIOD);
        IBS.init(SCCGWA, BPCITAXG);
        WS_D_I = 0;
        for (WS_D_I = 1; TDCCDINT.DOCU_INF.DOCU[WS_D_I-1].D_TERM.trim().length() != 0; WS_D_I += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDINT.DOCU_INF.DOCU[WS_D_I-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DOCU[WS_D_I-1]);
        }
        if (TDCCDINT.CALR_STD.equalsIgnoreCase("01") 
            || TDCCDINT.CALR_STD.equalsIgnoreCase("1")) {
            WS_BASE_DAYS = 360;
        }
        if (TDCCDINT.CALR_STD.equalsIgnoreCase("02") 
            || TDCCDINT.CALR_STD.equalsIgnoreCase("2")) {
            WS_BASE_DAYS = 365;
        }
        if (TDCCDINT.CALR_STD.equalsIgnoreCase("03") 
            || TDCCDINT.CALR_STD.equalsIgnoreCase("3")) {
            WS_BASE_DAYS = 366;
        }
        if (WS_BASE_DAYS == 0) {
            WS_BASE_DAYS = 360;
        }
        CEP.TRC(SCCGWA, TDCCDINT.CALR_STD);
        CEP.TRC(SCCGWA, WS_BASE_DAYS);
        WS_TMP_CDINT_RAT = TDCCDINT.RAT;
        WS_TMP_VAL_DATE = TDCCDINT.VAL_DATE;
        WS_TMP_EXP_DATE = TDCCDINT.EXP_DATE;
        CEP.TRC(SCCGWA, TDCCDINT.IRAT_CD);
        WS_IRAT_CD = TDCCDINT.IRAT_CD;
        WS_SER_TIME = TDCCDINT.SER_TIME;
        TDCCDINT.SER_TIME = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        DPCPARMP.AC_TYPE = TDCCDINT.PRDAC_CD;
        if (TDCCDINT.CCY.equalsIgnoreCase("156")) {
            WS_AMT_0 = (long) TDCCDINT.TXN_AMT;
            WS_AMT_2 = WS_AMT_0;
        } else {
            WS_AMT_2 = TDCCDINT.TXN_AMT;
        }
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        CEP.TRC(SCCGWA, WS_CALD_FLG);
        CEP.TRC(SCCGWA, TDCCDINT.INT_STSW.FX_FLG);
        CEP.TRC(SCCGWA, TDCCDINT.AC);
        CEP.TRC(SCCGWA, TDCCDINT.INT_STSW);
        if ((TDCCDINT.RAT == 0 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            || TDCCDINT.INT_STSW.FX_FLG == '1' 
            || TDCCDINT.INT_STSW.DISJOIN_FLG != '0') {
            T000_STARTBR_TDTIREV();
            if (pgmRtn) return;
            T000_READNEXT_TDTIREV();
            if (pgmRtn) return;
            TDCCDINT.RAT = WS_TMP_CDINT_RAT;
        }
        CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.TAX_CD);
        CEP.TRC(SCCGWA, TDCCDINT.TAX_TYP);
        CEP.TRC(SCCGWA, TDCCDINT.REG_FLG);
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.TWAV_FLG);
        if ((TDCCDINT.TAX_TYP.trim().length() > 0 
            && !TDCCDINT.TAX_TYP.equalsIgnoreCase("0")) 
            && (TDCCDINT.REG_FLG == '1' 
            || TDCCDINT.REG_FLG == '2') 
            && TDCCDINT.OPT == '0') {
            R000_GET_TAX_INF();
            if (pgmRtn) return;
        }
        if (TDCCDINT.INT_STSW.HDRW_INT_FLG == '1') {
            R000_COMPUTE_DD_INT_HDRW();
            if (pgmRtn) return;
        } else {
            if ((TDCCDINT.OPT == '6' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")))
                || (TDCCDINT.OPT == '0' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")) 
                    && TDCCDINT.INT_STSW.FX_FLG != '1')
                || (TDCCDINT.OPT == '4' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")) 
                    && TDCCDINT.INT_STSW.FX_FLG != '1')) {
                WS_GRT_MAMT_FLG = 'Y';
                if (WS_GRT_MAMT_FLG == 'Y') {
                    if (TDCCDINT.EXP_DATE == 0) {
                        B200_CAL_DR_INT_ERLY();
                        if (pgmRtn) return;
                    } else {
                        if ((TDCCDINT.INSTR_MTH == '3' 
                            || TDCCDINT.INSTR_MTH == '6') 
                            && TDCCDINT.TXN_DATE >= TDCCDINT.EXP_DATE) {
                            B200_CAL_DR_INT_XC();
                            if (pgmRtn) return;
                        }
                        if (((TDCCDINT.XC_FLG != 'Y' 
                            && TDCCDINT.XC_FLG != 'C')) 
                            || (TDCCDINT.XC_FLG == 'C')) {
                            B200_CAL_DR_INT_TZZ();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    B200_CAL_INT_DD();
                    if (pgmRtn) return;
                }
            } else if ((TDCCDINT.OPT == '0' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")))) {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    if ((TDCCDINT.INSTR_MTH == '3' 
                        || TDCCDINT.INSTR_MTH == '6') 
                        && TDCCDINT.TXN_DATE >= TDCCDINT.EXP_DATE 
                        && TDCCDINT.TXN_AMT == TDCCDINT.BAL) {
                        B200_CAL_DR_INT_XC();
                        if (pgmRtn) return;
                    }
                    if ((TDCCDINT.XC_FLG != 'Y' 
                        && TDCCDINT.XC_FLG != 'C') 
                        || (TDCCDINT.SPE_FLG == '5' 
                        && (TDCCDINT.XC_FLG == 'Y' 
                        || TDCCDINT.XC_FLG == 'C'))) {
                        B200_CAL_DR_INT_TTZ();
                        if (pgmRtn) return;
                    }
                } else {
                    B400_CAL_DR_INT_TTZ_CANCEL();
                    if (pgmRtn) return;
                }
            } else if ((TDCCDINT.OPT == '4' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")))) {
                B200_CAL_DR_INT_TTZ_JT();
                if (pgmRtn) return;
            } else if ((TDCCDINT.OPT == '0' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")))
                || (TDCCDINT.OPT == '2' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")))
                || (TDCCDINT.OPT == '4' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")))) {
                CEP.TRC(SCCGWA, TDCCDINT.EDU_FLG);
                if (TDCCDINT.OPT == '0' 
                    && DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
                    && TDCCDINT.EDU_FLG == ' ') {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TJY_M_INP_EDU_FLG, TDCCDINT.RC_MSG);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
                    && TDCCDINT.EDU_FLG == '0') {
                    B200_CAL_DR_INT_TLZ_EDU();
                    if (pgmRtn) return;
                } else {
                    B200_CAL_DR_INT_TLZ();
                    if (pgmRtn) return;
                }
            } else if (TDCCDINT.OPT == '0' 
                    && DPCPARMP.AC_TYPE.equalsIgnoreCase("027")
                || TDCCDINT.OPT == '4' 
                    && DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) {
                B200_CAL_DR_INT_TFF_TCQ();
                if (pgmRtn) return;
            } else if ((TDCCDINT.OPT == '0' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
                    || (TDCCDINT.INT_STSW.FX_FLG == '1' 
                    && !DPCPARMP.AC_TYPE.equalsIgnoreCase("027"))))
                || (TDCCDINT.OPT == '4' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
                    || (TDCCDINT.INT_STSW.FX_FLG == '1' 
                    && !DPCPARMP.AC_TYPE.equalsIgnoreCase("027"))))) {
                CEP.TRC(SCCGWA, TDCCDINT.AC);
                IBS.init(SCCGWA, TDRAINT);
                if (TDCCDINT.AC.trim().length() > 0 
                    && TDCCDINT.AC.charAt(0) != 0X00) {
                    if (TDCCDINT.INT_STSW.TQ_INT_FLG == '1' 
                        || TDCCDINT.INT_STSW.YQ_INT_FLG == '1') {
                        TDRAINT.KEY.ACO_AC = TDCCDINT.AC;
                        T000_READ_TDTAINT();
                        if (pgmRtn) return;
                    }
                    if (TDRAINT.OVE_RAT == 0) {
                        R000_GET_DD_RAT();
                        if (pgmRtn) return;
                        TDRAINT.OVE_RAT = TDCCDINT.DD_RAT;
                    }
                    if (TDRAINT.PRV_RAT == 0) {
                        TDRAINT.PRV_RAT = TDCCDINT.DD_RAT;
                    }
                }
                B200_CAL_DR_INT_TFF_TXY();
                if (pgmRtn) return;
            } else if ((TDCCDINT.OPT == '0' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("030")))
                || (TDCCDINT.OPT == '4' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("030")))) {
                B200_CAL_DR_INT_TDH();
                if (pgmRtn) return;
            } else if (TDCCDINT.OPT == '3' 
                    && DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) {
                B300_CAL_PAYING_INT_TCQ();
                if (pgmRtn) return;
            } else if ((TDCCDINT.OPT == '3' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
                    || (TDCCDINT.INT_STSW.FX_FLG == '1' 
                    && !DPCPARMP.AC_TYPE.equalsIgnoreCase("027"))))) {
                IBS.init(SCCGWA, TDRAINT);
                if (TDCCDINT.AC.trim().length() > 0 
                    && TDCCDINT.AC.charAt(0) != 0X00) {
                    if (TDCCDINT.INT_STSW.TQ_INT_FLG == '1' 
                        || TDCCDINT.INT_STSW.YQ_INT_FLG == '1') {
                        TDRAINT.KEY.ACO_AC = TDCCDINT.AC;
                        T000_READ_TDTAINT();
                        if (pgmRtn) return;
                    }
                    if (TDRAINT.OVE_RAT == 0) {
                        R000_GET_DD_RAT();
                        if (pgmRtn) return;
                        TDRAINT.OVE_RAT = TDCCDINT.DD_RAT;
                    }
                }
                B300_CAL_PAYING_INT_TXY_2();
                if (pgmRtn) return;
            } else if ((TDCCDINT.OPT == '5' 
                    && (DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")))) {
                B300_CAL_GKING_AMT();
                if (pgmRtn) return;
            } else if (TDCCDINT.OPT == '0' 
                    && DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
                B200_CAL_DR_INT_TZL();
                if (pgmRtn) return;
            } else if (TDCCDINT.OPT == '4' 
                    && DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
                B200_CAL_DR_INT_TZL_JT();
                if (pgmRtn) return;
            } else if (TDCCDINT.OPT == '7') {
                B300_CAL_PAYING_AMT_TZL();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT, TDCCDINT.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B900_PROCESS_DEC();
            if (pgmRtn) return;
        }
        if (WS_START_BR_FLG == 'Y') {
            T000_ENDBR_TDTIREV();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "OUT-RAT-WSRAT");
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        CEP.TRC(SCCGWA, WS_RAT);
        CEP.TRC(SCCGWA, WS_EXC_RAT);
        if (WS_RAT != WS_EXC_RAT 
            && WS_EXC_RAT > 0) {
            TDCCDINT.RAT = WS_EXC_RAT;
        } else {
            if (WS_RAT > 0) {
                TDCCDINT.RAT = WS_RAT;
            }
        }
        if (WS_XC_ALREADY == 'Y') {
            TDCCDINT.RAT = WS_RAT_XC_T;
        }
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
    }
    public void B200_CAL_DR_INT_TZZ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, WS_AMT_2);
        if (TDCCDINT.TXN_AMT != TDCCDINT.BAL) {
            WS_INT_3 = TDCCDINT.PAYING_INT * TDCCDINT.TXN_AMT / TDCCDINT.BAL;
            WS_TRANS_3 = TDCCDINT.PAYING_INT - WS_INT_3;
        }
        CEP.TRC(SCCGWA, WS_INT_3);
        CEP.TRC(SCCGWA, WS_TRANS_3);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CALD_CD);
        if (BPCPORUP.DATA_INFO.CALD_CD.equalsIgnoreCase("0000")) {
            BPCPORUP.DATA_INFO.CALD_CD = "CN";
        }
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            IBS.init(SCCGWA, BPCOCLWD);
            if ((TDCPIOD.EXP_PRM.HLID_RUL == '1' 
                || TDCPIOD.EXP_PRM.HLID_RUL == '3') 
                && TDCCDINT.OPT != '4') {
                BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
                BPCOCLWD.DATE1 = TDCCDINT.TXN_DATE;
                BPCOCLWD.WDAYS = 2;
                S000_CALL_BPZPCLWD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            }
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            if (BPCOCLWD.DATE2 > TDCCDINT.EXP_DATE 
                && TDCCDINT.TXN_AMT == TDCCDINT.BAL 
                || TDCCDINT.OPT == '4') {
                WS_DATE1 = TDCCDINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                CEP.TRC(SCCGWA, "AFTER R000-GET-YMD");
                if ((TDCCDINT.OPT != '4') 
                    && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_HOLIDAY_PAY, TDCCDINT.RC_MSG);
                }
                if (TDCCDINT.INT_STSW.RULE != ' ') {
                    WS_RULE = TDCCDINT.INT_STSW.RULE;
                }
                if (TDCPIOD.INT_PRM.RAT_INX != '0') {
                    WS_INDEX_DATE = WS_DATE2;
                    WS_TERM = TDCCDINT.TERM;
                    IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
                    R000_GET_TD_RAT();
                    if (pgmRtn) return;
                }
                WS_RAT = TDCCDINT.RAT;
                WS_EXC_RAT = TDCCDINT.RAT;
                if (TDCCDINT.INT_STSW.DISJOIN_FLG == '1') {
                    R000_GET_DISJOIN_INT();
                    if (pgmRtn) return;
                } else {
                    R000_COMPUTE_INT_BY_RULE();
                    if (pgmRtn) return;
                }
            } else {
                B200_CAL_DR_INT_ERLY();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "CDINT-TXN-DATE < CDINT-EXP-DATE");
            CEP.TRC(SCCGWA, WS_INT_3);
        } else if (TDCCDINT.TXN_DATE >= TDCCDINT.EXP_DATE) {
            CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.HLID_RUL);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CALD_CD);
            CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
            WS_EXC_RAT = TDCCDINT.RAT;
            if (TDCCDINT.PAYING_INT == 0 
                || TDCPIOD.INT_PRM.RAT_INX != '0' 
                || ((TDCPIOD.EXP_PRM.HLID_RUL == '2' 
                || TDCPIOD.EXP_PRM.HLID_RUL == '3') 
                && TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE 
                && TDCCDINT.OPT != '4')) {
                if (TDCPIOD.INT_PRM.RAT_INX == '0' 
                    && TDCCDINT.RAT != 0) {
                } else {
                    if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                        WS_INDEX_DATE = TDCCDINT.VAL_DATE;
                    } else {
                        WS_INDEX_DATE = TDCCDINT.EXP_DATE;
                    }
                    WS_TERM = TDCCDINT.TERM;
                    IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
                    R000_GET_TD_RAT();
                    if (pgmRtn) return;
                }
                WS_DATE1 = TDCCDINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                if ((TDCPIOD.EXP_PRM.HLID_RUL == '2' 
                    || TDCPIOD.EXP_PRM.HLID_RUL == '3') 
                    && TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE 
                    && TDCCDINT.OPT != '4') {
                    IBS.init(SCCGWA, BPCOCLWD);
                    BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
                    BPCOCLWD.DATE1 = TDCCDINT.EXP_DATE;
                    BPCOCLWD.WDAYS = 1;
                    S000_CALL_BPZPCLWD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
                    if (BPCOCLWD.DATE2 == TDCCDINT.TXN_DATE) {
                        TDCCDINT.EXP_DATE = TDCCDINT.TXN_DATE;
                        WS_DATE1 = TDCCDINT.VAL_DATE;
                        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                        WS_DATE2 = TDCCDINT.TXN_DATE;
                        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                    }
                }
                if (TDCCDINT.INT_STSW.RULE != ' ') {
                    WS_RULE = TDCCDINT.INT_STSW.RULE;
                }
                WS_RAT = TDCCDINT.RAT;
                WS_EXC_RAT = TDCCDINT.RAT;
                WS_UNCOUNT_FLG = 'Y';
                if (TDCCDINT.INT_STSW.DISJOIN_FLG == '1') {
                    R000_GET_DISJOIN_INT();
                    if (pgmRtn) return;
                } else {
                    R000_COMPUTE_INT_BY_RULE();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCRDAMT);
                BPCRDAMT.CCY = TDCCDINT.CCY;
                BPCRDAMT.AMT = WS_INT_3;
                S00_CALL_BPZRDAMT();
                if (pgmRtn) return;
                TDCCDINT.PAYING_INT = BPCRDAMT.RESULT_AMT;
            }
            WS_INT_3 = TDCCDINT.PAYING_INT;
            WS_TAX_DATE1 = TDCCDINT.VAL_DATE;
            WS_TAX_DATE2 = TDCCDINT.EXP_DATE;
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = TDCCDINT.RAT;
            if (WS_UNCOUNT_FLG != 'Y') {
                if (WS_ITAX_FLG == 'N') {
                    R000_COMPUTE_TAX_INFO();
                    if (pgmRtn) return;
                } else {
                    if (TDCCDINT.REG_FLG == '1') {
                        WS_TAX_INT = WS_INT_3;
                        R000_REG_INT_TAX_INF();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.BAL_FLG);
            if (TDCPIOD.OTH_PRM.BAL_FLG == '1') {
                if (TDCPIOD.EXP_PRM.TWAV_FLG == 'Y' 
                    || TDCCDINT.OPT == '4') {
                    WS_TAXING_INT = 0;
                }
                CEP.TRC(SCCGWA, WS_TAXING_INT);
                if (WS_TAXING_INT > 0 
                    || WS_INT_TAX_ALL > 0) {
                    TDCCDINT.TAXING_INT = WS_TAXING_INT;
                    WS_PAYING_TAX = WS_TAXING_INT * TDCCDINT.TAX_RAT / 100;
                    IBS.init(SCCGWA, BPCRDAMT);
                    BPCRDAMT.CCY = TDCCDINT.CCY;
                    BPCRDAMT.AMT = WS_PAYING_TAX;
                    if (WS_PAYING_TAX == 0) {
                        BPCRDAMT.AMT = WS_INT_TAX_ALL;
                    }
                    S00_CALL_BPZRDAMT();
                    if (pgmRtn) return;
                    TDCCDINT.PAYING_TAX = BPCRDAMT.RESULT_AMT;
                } else {
                    TDCCDINT.TAXING_INT = 0;
                    TDCCDINT.PAYING_TAX = 0;
                }
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_TAX);
                CEP.TRC(SCCGWA, TDCCDINT.TAXING_INT);
                WS_AMT_2 = WS_AMT_2 + TDCCDINT.PAYING_INT - TDCCDINT.PAYING_TAX;
                TDCCDINT.TXN_AMT = WS_AMT_2;
                CEP.TRC(SCCGWA, WS_AMT_2);
                if (TDCCDINT.CCY.equalsIgnoreCase("156")) {
                    WS_AMT_0 = (long) WS_AMT_2;
                    WS_AMT_2 = WS_AMT_0;
                }
            }
            if (TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE 
                && TDCPIOD.EXP_PRM.DUE_FLG == 'Y') {
                if (TDCCDINT.INT_STSW.YQ_INT_FLG == '1') {
                    R000_GET_AINT_INFO();
                    if (pgmRtn) return;
                    if (TDRAINT.OVE_RAT != 0) {
                        WS_DD_RAT = TDRAINT.OVE_RAT;
                        TDCCDINT.DD_RAT = TDRAINT.OVE_RAT;
                    }
                }
                if (WS_DD_RAT == 0) {
                    R000_GET_DD_RAT();
                    if (pgmRtn) return;
                }
                WS_DATE1 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                WS_RAT = WS_DD_RAT;
                WS_RULE = '3';
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
                WS_INT_3 = WS_INT_3 + TDCCDINT.PAYING_INT;
            }
            CEP.TRC(SCCGWA, "CDINT-TXN-DATE >= CDINT-EXP-DATE");
            CEP.TRC(SCCGWA, WS_INT_3);
        }
        WS_TAXING_INT = WS_INT_3;
        CEP.TRC(SCCGWA, WS_TAXING_INT);
    }
    public void B200_CAL_DR_INT_ERLY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.ERLY_TYP);
        CEP.TRC(SCCGWA, TDCCDINT.INT_STSW.ERLY_FLG);
        if (TDCCDINT.INT_STSW.ERLY_FLG == '0' 
            || TDCCDINT.INT_STSW.ERLY_FLG == ' ') {
            WS_TERM_INF_FLG = TDCPIOD.EXP_PRM.ERLY_TYP;
        } else {
            WS_TERM_INF_FLG = TDCCDINT.INT_STSW.ERLY_FLG;
        }
        WS_FIRST_FLG = 'Y';
        WS_TQ_FLG = 'Y';
        if (WS_TERM_INF_FLG == '2') {
            R000_COMPUTE_TERM_INT_2();
            if (pgmRtn) return;
        } else if (WS_TERM_INF_FLG == '3') {
            R000_COMPUTE_TERM_INT_3();
            if (pgmRtn) return;
        } else if (WS_TERM_INF_FLG == '4') {
            R000_COMPUTE_TERM_INT_4();
            if (pgmRtn) return;
        } else if (WS_TERM_INF_FLG == '5'
            || WS_TERM_INF_FLG == '1') {
            R000_COMPUTE_DD_INT();
            if (pgmRtn) return;
        } else {
            R000_COMPUTE_DD_INT();
            if (pgmRtn) return;
        }
        WS_EXC_RAT = WS_ZNT_RATE;
    }
    public void R000_COMPUTE_TERM_INT_2() throws IOException,SQLException,Exception {
        R000_CAL_MTHS_DAYS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ALL DAYS");
        CEP.TRC(SCCGWA, TDCCDINT.DAYS);
        CEP.TRC(SCCGWA, WS_ZNT_ALL_DAYS);
        if (WS_TOT_DAYS != 0) {
            R000_GET_TERM_PROC();
            if (pgmRtn) return;
            WS_K = 1;
            while (WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM.trim().length() != 0 
                && WS_TEMR_FND_FLG != 'F') {
                WS_TERM2 = WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM_2;
                IBS.CPY2CLS(SCCGWA, WS_TERM2, REDEFINES10);
                if (REDEFINES10.WS_TERM2_TYP == 'M') {
                    if (WS_TOT_MTHS >= REDEFINES10.WS_TERM2_MTHS) {
                        WS_TEMR_FND_FLG = 'F';
                    }
                } else {
                    if (WS_TOT_DAYS >= REDEFINES10.WS_TERM2_MTHS) {
                        WS_TEMR_FND_FLG = 'F';
                    }
                }
                if (WS_TEMR_FND_FLG == 'F') {
                    WS_DATE1 = TDCCDINT.VAL_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                    WS_DATE2 = TDCCDINT.TXN_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                    R000_GET_TERM_INT();
                    if (pgmRtn) return;
                    WS_INT_TMP += WS_INT_3;
                }
                if (WS_TEMR_FND_FLG != 'F') {
                    WS_K += 1;
                }
            }
        }
        if (WS_TEMR_FND_FLG != 'F') {
            WS_TERM_DATE1 = TDCCDINT.VAL_DATE;
            R000_GET_TERM_DD_INT();
            if (pgmRtn) return;
            WS_INT_TMP += WS_INT_3;
        }
        WS_INT_3 = WS_INT_TMP;
    }
    public void R000_CAL_MTHS_DAYS() throws IOException,SQLException,Exception {
        WS_TXN_DATE = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        R000_GET_DEP_MTHS();
        if (pgmRtn) return;
        WS_TOT_MTHS = WS_MTHS;
        R000_CAL_VT1_DAYS();
        if (pgmRtn) return;
        WS_TOT_DAYS = TDCCDINT.DAYS;
        CEP.TRC(SCCGWA, WS_TOT_MTHS);
        CEP.TRC(SCCGWA, WS_TOT_DAYS);
    }
    public void R000_COMPUTE_TERM_INT_3() throws IOException,SQLException,Exception {
        R000_CAL_MTHS_DAYS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ALL DAYS");
        CEP.TRC(SCCGWA, TDCCDINT.DAYS);
        CEP.TRC(SCCGWA, WS_ZNT_ALL_DAYS);
        if (WS_TOT_DAYS != 0) {
            R000_GET_TERM_PROC();
            if (pgmRtn) return;
            WS_TERM_DATE1 = TDCCDINT.VAL_DATE;
            for (WS_K = 1; WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM.trim().length() != 0; WS_K += 1) {
                WS_TERM2 = WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM_2;
                IBS.CPY2CLS(SCCGWA, WS_TERM2, REDEFINES10);
                CEP.TRC(SCCGWA, WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM_2);
                if (REDEFINES10.WS_TERM2_TYP == 'M') {
                    while (WS_TOT_MTHS >= REDEFINES10.WS_TERM2_MTHS) {
                        WS_TOT_MTHS = WS_TOT_MTHS - REDEFINES10.WS_TERM2_MTHS;
                        WS_MTH_CNT += REDEFINES10.WS_TERM2_MTHS;
                        R000_GET_TERM_INT();
                        if (pgmRtn) return;
                        WS_INT_TMP += WS_INT_3;
                        CEP.TRC(SCCGWA, WS_INT_3);
                        WS_FST_FLG = 'Y';
                    }
                } else {
                    if (WS_MTH_CNT > 0 
                        && WS_FST_FLG == 'Y') {
                        R000_GET_NEW_DATE_SUB_MTH();
                        if (pgmRtn) return;
                        WS_FST_FLG = 'N';
                        WS_TERM_DATE1 = WS_ZNT_TMP_DT;
                        CEP.TRC(SCCGWA, WS_TOT_DAYS);
                    }
                    while (WS_TOT_DAYS >= REDEFINES10.WS_TERM2_MTHS) {
                        WS_TOT_DAYS = WS_TOT_DAYS - REDEFINES10.WS_TERM2_MTHS;
                        CEP.TRC(SCCGWA, WS_TOT_DAYS);
                        WS_DAY_CNT += REDEFINES10.WS_TERM2_MTHS;
                        R000_GET_TERM_INT();
                        if (pgmRtn) return;
                        WS_INT_TMP += WS_INT_3;
                    }
                }
            }
            if (REDEFINES10.WS_TERM2_TYP != 'D' 
                || REDEFINES10.WS_TERM2_MTHS != 1) {
                if (WS_ZNT_TMP_DT > 0) {
                    WS_TERM_DATE1 = WS_ZNT_TMP_DT;
                } else {
                    WS_TERM_DATE1 = TDCCDINT.VAL_DATE;
                }
                R000_GET_TERM_DD_INT();
                if (pgmRtn) return;
                WS_INT_TMP += WS_INT_3;
            }
        }
        WS_INT_3 = WS_INT_TMP;
    }
    public void R000_COMPUTE_TERM_INT_4() throws IOException,SQLException,Exception {
        R000_CAL_MTHS_DAYS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ALL DAYS");
        CEP.TRC(SCCGWA, TDCCDINT.DAYS);
        CEP.TRC(SCCGWA, WS_ZNT_ALL_DAYS);
        if (WS_TOT_DAYS != 0) {
            R000_GET_TERM_PROC();
            if (pgmRtn) return;
            for (WS_K = 1; WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM.trim().length() != 0 
                && WS_TEMR_FND_FLG != 'F'; WS_K += 1) {
                WS_TERM2 = WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM_2;
                IBS.CPY2CLS(SCCGWA, WS_TERM2, REDEFINES10);
                if (REDEFINES10.WS_TERM2_TYP == 'M') {
                    WS_TERM_DATE1 = TDCCDINT.VAL_DATE;
                    if (WS_TOT_MTHS >= REDEFINES10.WS_TERM2_MTHS) {
                        WS_TOT_MTHS = WS_TOT_MTHS - REDEFINES10.WS_TERM2_MTHS;
                        WS_MTH_CNT += REDEFINES10.WS_TERM2_MTHS;
                        R000_GET_TERM_INT();
                        if (pgmRtn) return;
                        WS_INT_TMP += WS_INT_3;
                        CEP.TRC(SCCGWA, WS_INT_3);
                        WS_TEMR_FND_FLG = 'F';
                    }
                    WS_FST_FLG = 'Y';
                } else {
                    if (WS_TOT_DAYS >= REDEFINES10.WS_TERM2_MTHS) {
                        WS_TOT_DAYS = WS_TOT_DAYS - REDEFINES10.WS_TERM2_MTHS;
                        WS_DAY_CNT += REDEFINES10.WS_TERM2_MTHS;
                        R000_GET_TERM_INT();
                        if (pgmRtn) return;
                        WS_INT_TMP += WS_INT_3;
                        WS_TEMR_FND_FLG = 'F';
                    }
                }
            }
        }
        if (REDEFINES10.WS_TERM2_TYP != 'D' 
            || REDEFINES10.WS_TERM2_MTHS != 1) {
            if (WS_ZNT_TMP_DT > 0) {
                WS_TERM_DATE1 = WS_ZNT_TMP_DT;
            } else {
                WS_TERM_DATE1 = TDCCDINT.VAL_DATE;
            }
            R000_GET_TERM_DD_INT();
            if (pgmRtn) return;
            WS_INT_TMP += WS_INT_3;
        }
        WS_INT_3 = WS_INT_TMP;
    }
    public void R000_COMPUTE_DD_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.INT_STSW.ZNT_FLG);
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        if (TDCCDINT.INT_STSW.TQ_INT_FLG == '1') {
            R000_GET_AINT_INFO();
            if (pgmRtn) return;
            if (TDRAINT.PRV_RAT != 0) {
                WS_DD_RAT = TDRAINT.PRV_RAT;
                TDCCDINT.DD_RAT = TDRAINT.PRV_RAT;
            }
        } else {
            if (TDCCDINT.INT_STSW.ZNT_FLG == '1' 
                && TDCCDINT.RAT > 0) {
                TDCCDINT.DD_RAT = TDCCDINT.RAT;
            }
        }
        if (WS_DD_RAT == 0) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
        }
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        TDCCDINT.RAT = WS_DD_RAT;
        WS_RAT = WS_DD_RAT;
        WS_EXC_RAT = WS_DD_RAT;
        WS_RULE = '3';
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
    }
    public void R000_COMPUTE_DD_INT_HDRW() throws IOException,SQLException,Exception {
        if (WS_DD_RAT == 0) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
        }
        WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        TDCCDINT.RAT = WS_DD_RAT;
        WS_RAT = WS_DD_RAT;
        WS_EXC_RAT = WS_DD_RAT;
        WS_RULE = '3';
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
    }
    public void B200_CAL_DR_INT_XC() throws IOException,SQLException,Exception {
        WS_INT_TMP = 0;
        WS_XC_DATE1 = TDCCDINT.VAL_DATE;
        WS_FIRST_VAL_DATE = TDCCDINT.VAL_DATE;
        WS_TERM = TDCCDINT.TERM;
        IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
        if (TDCCDINT.EXP_DATE == 0) {
            R000_GET_DATE2_BY_TERM();
            if (pgmRtn) return;
            TDCCDINT.EXP_DATE = WS_XC_DATE2;
        }
        WS_XC_DATE2 = TDCCDINT.EXP_DATE;
        WS_RESULT_FLG = 'Y';
        CEP.TRC(SCCGWA, WS_SER_TIME);
        for (WS_I = 1; (WS_XC_DATE2 <= TDCCDINT.TXN_DATE 
            && WS_I <= WS_SER_TIME); WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            TDCCDINT.VAL_DATE = WS_XC_DATE1;
            TDCCDINT.EXP_DATE = WS_XC_DATE2;
            if (WS_XC_DATE2 <= TDCCDINT.TXN_DATE) {
                R000_GET_NEW_EXP_INT();
                if (pgmRtn) return;
            }
            WS_XC_DATE1 = WS_XC_DATE2;
            R000_GET_DATE2_BY_TERM();
            if (pgmRtn) return;
        }
        WS_I = (short) (WS_I - 1);
        TDCCDINT.SER_TIME = WS_I;
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, WS_SER_TIME);
        if (WS_I > 0 
            && WS_I >= WS_SER_TIME) {
            WS_RAT_XC_T = TDCCDINT.RAT;
            WS_XC_ALREADY = 'Y';
        }
        CEP.TRC(SCCGWA, WS_RAT_XC_T);
        CEP.TRC(SCCGWA, WS_XC_ALREADY);
        WS_INT_3 = 0;
        if ((TDCCDINT.XC_FLG != 'Y' 
            && TDCCDINT.XC_FLG != 'C') 
            || (TDCCDINT.SPE_FLG == '5' 
            && (TDCCDINT.XC_FLG == 'Y' 
            || TDCCDINT.XC_FLG == 'C') 
            && (TDCCDINT.INSTR_MTH == '3' 
            && TDCCDINT.INSTR_MTH == '6') 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) {
            TDCCDINT.VAL_DATE = WS_XC_DATE1;
            TDCCDINT.EXP_DATE = WS_XC_DATE2;
        }
        if (TDCCDINT.CCY.equalsIgnoreCase("156")) {
            WS_AMT_0 = (long) TDCCDINT.TXN_AMT;
            WS_AMT_2 = WS_AMT_0;
        } else {
            WS_AMT_2 = TDCCDINT.TXN_AMT;
        }
        WS_INT_4 = WS_INT_TMP;
        WS_RESULT_FLG = ' ';
    }
    public void B200_CAL_DR_INT_TTZ() throws IOException,SQLException,Exception {
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCCDINT.CCY)) {
                WS_MIN_DRAW_AMT = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MDRW_AMT;
                WS_W = WS_CNT;
                WS_CALD_FLG = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].INT_RUL;
                WS_CCY_FOUND = 'Y';
            }
        }
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_W-1].MDRW_AMT);
        if (!TDCCDINT.CCY.equalsIgnoreCase("156") 
            && !TDCCDINT.CCY.equalsIgnoreCase("840")) {
            for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase("840")) {
                    WS_MIN_DRAW_AMT_USD = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MDRW_AMT;
                    WS_CNT = 99;
                }
            }
            if (WS_MIN_DRAW_AMT == 0 
                && WS_MIN_DRAW_AMT_USD != 0) {
                WS_BUY_AMT = WS_MIN_DRAW_AMT_USD;
                R000_AMT_EX_PROC();
                if (pgmRtn) return;
                WS_MIN_DRAW_AMT = WS_SELL_AMT;
            }
        }
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        B210_PROCESS_CALL_TBL();
        if (pgmRtn) return;
        WS_TZ_TERM = 0;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) {
            CEP.TRC(SCCGWA, "XXTRAT");
            CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
            CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
            SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
            SCCCLDT.DATE2 = TDCCDINT.TXN_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            if (SCCCLDT.DAYS > 0 
                && SCCCLDT.DAYS < 7) {
                TDCCDINT.TERM = "D001";
            }
        }
        if (TDCCDINT.TERM.equalsIgnoreCase("D001")) {
            WS_TZ_TERM = 1;
        }
        if (TDCCDINT.TERM.equalsIgnoreCase("D007")) {
            WS_TZ_TERM = 7;
        }
        CEP.TRC(SCCGWA, WS_TZ_TERM);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, WS_APPO_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, WS_MIN_DRAW_AMT);
        if ((WS_APPO_DATE == 0 
            || (TDCCDINT.TXN_AMT < WS_MIN_DRAW_AMT 
            && TDCCDINT.TXN_AMT != WS_APPO_AMT) 
            || (TDCCDINT.TXN_AMT < WS_APPO_AMT 
            && TDCCDINT.TXN_AMT < WS_MIN_DRAW_AMT) 
            || TDCCDINT.TXN_DATE != WS_APPO_DATE) 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && TDCCDINT.EXP_DATE != SCCGWA.COMM_AREA.AC_DATE 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            TDCCDINT.RAT = WS_DD_RAT;
            WS_RAT = WS_DD_RAT;
            WS_EXC_RAT = WS_DD_RAT;
            if (TDCCDINT.CALL_SRC == 'M' 
                && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && (TDCCDINT.EXP_DATE == 0 
                || TDCCDINT.EXP_DATE == 99991231)) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_TTZ_DRAW, TDCCDINT.RC_MSG);
            }
            CEP.TRC(SCCGWA, TDCCDINT.RC_MSG);
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "TD-RAT");
            CEP.TRC(SCCGWA, "KING5");
            CEP.TRC(SCCGWA, TDCCDINT.RAT);
            if (TDCCDINT.RAT == 0) {
                WS_TERM = TDCCDINT.TERM;
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
                if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                    WS_INDEX_DATE = TDCCDINT.VAL_DATE;
                } else {
                    WS_INDEX_DATE = TDCCDINT.TXN_DATE;
                }
                R000_GET_TD_RAT();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || TDCCDINT.EXP_DATE == SCCGWA.COMM_AREA.AC_DATE 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) {
                WS_APPO_AMT = TDCCDINT.TXN_AMT;
            }
            CEP.TRC(SCCGWA, "KING4");
            CEP.TRC(SCCGWA, WS_APPO_AMT);
            if (TDCCDINT.TXN_AMT > WS_APPO_AMT) {
                if (TDCCDINT.CALL_SRC == 'M' 
                    && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    && WS_APPO_AMT > 0) {
                    CEP.TRC(SCCGWA, "KING1");
                    CEP.TRC(SCCGWA, WS_APPO_AMT);
                    IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BREACH_PAY, TDCCDINT.RC_MSG);
                }
                R000_GET_DD_RAT();
                if (pgmRtn) return;
                WS_AMT = TDCCDINT.TXN_AMT - WS_APPO_AMT;
                if (TDCCDINT.CI_TYP == '1') {
                    WS_AMT_0 = (long) WS_AMT;
                    WS_AMT = WS_AMT_0;
                    WS_AMT_0 = (long) WS_APPO_AMT;
                    WS_AMT_2 = WS_AMT_0;
                } else {
                    WS_AMT_2 = WS_APPO_AMT;
                }
                WS_RAT = TDCCDINT.RAT;
                WS_EXC_RAT = TDCCDINT.RAT;
                WS_TTZ_DINT_BAL = WS_APPO_AMT;
                WS_RULE = '3';
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
                WS_DATE1 = TDCCDINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                WS_INT_TMP = WS_INT_3;
                WS_AMT_2 = WS_AMT;
                WS_RAT = WS_DD_RAT;
                WS_RULE = '3';
                WS_TTZ_DINT_BAL = WS_AMT;
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
                WS_INT_3 = WS_INT_3 + WS_INT_TMP;
            } else {
                CEP.TRC(SCCGWA, "KING10");
                WS_RAT = TDCCDINT.RAT;
                WS_EXC_RAT = TDCCDINT.RAT;
                if (TDCCDINT.CI_TYP == '1') {
                    WS_AMT_0 = (long) TDCCDINT.TXN_AMT;
                    WS_AMT_2 = WS_AMT_0;
                } else {
                    WS_AMT_2 = TDCCDINT.TXN_AMT;
                }
                CEP.TRC(SCCGWA, WS_RAT);
                CEP.TRC(SCCGWA, WS_EXC_RAT);
                WS_RULE = '3';
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
            }
        }
        WS_TAXING_INT = WS_INT_3;
        if (WS_INT_3 < 0) {
            WS_INT_3 = 0;
            WS_TAXING_INT = 0;
        }
    }
    public void B200_CAL_DR_INT_TZL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            TDCCDINT.RAT = WS_DD_RAT;
            WS_RAT = WS_DD_RAT;
            WS_EXC_RAT = WS_DD_RAT;
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_RAT = TDCCDINT.RAT;
            WS_EXC_RAT = TDCCDINT.RAT;
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        R000_GET_DEP_MTHS_2();
        if (pgmRtn) return;
        WS_TMP_VAL_DATE = TDCCDINT.VAL_DATE;
        for (WS_K = 1; WS_K <= WS_MTHS; WS_K += 1) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
            SCCCLDT.MTHS = WS_K;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE2 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_J = (short) (WS_K - 1);
            WS_AMT = WS_J % TDCCDINT.CD_PERD;
            WS_LEN = (short) ((WS_J - WS_AMT) / TDCCDINT.CD_PERD);
            WS_INT_3 = ( TDCCDINT.BAL - TDCCDINT.CD_AMT * WS_LEN ) * WS_RAT / 1200;
            CEP.TRC(SCCGWA, WS_INT_3);
            CEP.TRC(SCCGWA, TDCCDINT.BAL);
            CEP.TRC(SCCGWA, WS_LEN);
            WS_INT_TMP = WS_INT_3 + WS_INT_TMP;
            WS_INT_TMP_1 = WS_INT_TMP_1 + WS_INT_3;
            if (TDCCDINT.REG_FLG == '1' 
                || TDCCDINT.REG_FLG == '2') {
                if (WS_ITAX_FLG == 'N') {
                    WS_TAX_DATE1 = WS_DATE2;
                    JIBS_tmp_str[0] = "" + WS_TAX_DATE1;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_TAX_DATE1;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + "00" + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_TAX_DATE1 = Integer.parseInt(JIBS_NumStr);
                    R000_GET_TAX_RAT();
                    if (pgmRtn) return;
                    if (WS_K == 1) {
                        WS_TAX_VAL_LAST = WS_TAX_VAL;
                    }
                }
                CEP.TRC(SCCGWA, WS_TAX_VAL);
                if (WS_TAX_VAL != WS_TAX_VAL_LAST) {
                    WS_DATE1 = WS_TMP_VAL_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                    WS_TAX_TMP = WS_TAX_VAL;
                    WS_TAX_VAL = WS_TAX_VAL_LAST;
                    WS_DATE2_TMP = WS_DATE2;
                    WS_DATE2 = WS_DATE2_LAST;
                    IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                    WS_INT_TMP_1 = WS_INT_TMP_LAST;
                    if (TDCCDINT.REG_FLG == '1') {
                        WS_INT_TAX = WS_INT_TAX_TIM;
                        WS_TAX_INT = WS_INT_TMP_1;
                        R000_REG_INT_TAX_INF();
                        if (pgmRtn) return;
                    }
                    WS_TAX_VAL = WS_TAX_TMP;
                    WS_TAX_VAL_LAST = WS_TAX_VAL;
                    WS_INT_TAX_TIM = 0;
                    WS_INT_TMP_1 = WS_INT_3;
                }
                if (WS_ITAX_FLG == 'N') {
                    WS_INT_TAX = WS_INT_3 * WS_TAX_VAL;
                    WS_INT_TAX_ALL = WS_INT_TAX_ALL + WS_INT_TAX;
                    WS_INT_TAX_TIM = WS_INT_TAX_TIM + WS_INT_TAX;
                }
            }
            WS_DATE2_LAST = WS_DATE2;
            WS_INT_TMP_LAST = WS_INT_TMP_1;
        }
        WS_INT_3 = WS_INT_TMP;
        CEP.TRC(SCCGWA, WS_DD_RAT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, TDCCDINT.CD_AMT);
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, TDCCDINT.CD_PERD);
        CEP.TRC(SCCGWA, WS_MTHS);
        if (TDCCDINT.REG_FLG == '1') {
            WS_DATE1 = WS_TMP_VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_INT_TAX = WS_INT_TAX_TIM;
            WS_TAX_INT = WS_INT_TMP_1;
            R000_REG_INT_TAX_INF();
            if (pgmRtn) return;
        }
        if (TDCCDINT.EXP_DATE < TDCCDINT.TXN_DATE) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            WS_DATE1 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = WS_DD_RAT;
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            WS_INT_3 = WS_INT_3 + WS_INT_TMP;
        }
        WS_TAX_INT = WS_INT_3;
    }
    public void B200_CAL_DR_INT_TZL_JT() throws IOException,SQLException,Exception {
        WS_TXN_DATE = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        if (TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        R000_GET_DEP_MTHS_2();
        if (pgmRtn) return;
        WS_AMT = WS_MTHS % TDCCDINT.CD_PERD;
        WS_LEN = (short) ((WS_MTHS - WS_AMT) / TDCCDINT.CD_PERD);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, TDCCDINT.CD_AMT);
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        WS_RAT = TDCCDINT.RAT;
        WS_EXC_RAT = TDCCDINT.RAT;
        WS_INT_3 = TDCCDINT.RAT / 1200 * ( TDCCDINT.BAL * WS_LEN - ( TDCCDINT.CD_AMT * ( WS_LEN * ( WS_LEN - 1 ) / 2 ) ) ) * TDCCDINT.CD_PERD;
        if (WS_LEN > 0) {
            WS_MTHS = (short) (WS_LEN * TDCCDINT.CD_PERD);
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
            SCCCLDT.MTHS = WS_MTHS;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE1 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        } else {
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        }
        if (WS_DATE1 < WS_TXN_DATE) {
            WS_DAYS = ( REDEFINES107.WS_TXN_DATE_YY - REDEFINES113.WS_DATE1_YY ) * 360 + ( REDEFINES107.WS_TXN_DATE_MM - REDEFINES113.WS_DATE1_MM ) * 30 + REDEFINES107.WS_TXN_DATE_DD - REDEFINES113.WS_DATE1_DD;
            WS_INT_3 = WS_INT_3 + ( TDCCDINT.BAL - ( TDCCDINT.CD_AMT * WS_LEN ) ) * TDCCDINT.RAT * WS_DAYS / 36000;
        }
        if (WS_INT_3 < 0) {
            WS_INT_3 = 0;
        }
        if (TDCCDINT.EXP_DATE < TDCCDINT.TXN_DATE) {
            WS_INT_TMP = WS_INT_3;
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            WS_DATE1 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = WS_DD_RAT;
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            WS_INT_3 = WS_INT_3 + WS_INT_TMP;
        }
        WS_TAXING_INT = WS_INT_3;
    }
    public void B210_PROCESS_CALL_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDCCDINT.AC;
        WS_AMT_W = 0;
        T000_STARTBR_CALL_TABLE();
        if (pgmRtn) return;
        T000_READNEXT_CALL_TABLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (TDRCALL.APPO_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                if (TDRCALL.CALL_STS == '0') {
                    WS_APPO_AMT = TDRCALL.APPO_AMT;
                    WS_APPO_DATE = TDRCALL.APPO_DATE;
                    if (TDCCDINT.CALL_SRC == 'M') {
                        TDRCALL.CALL_STS = '1';
                        TDRCALL.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                        T000_REWRITE_CALL_TABLE();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (TDRCALL.CALL_STS == '2' 
                    || (TDCCDINT.TXN_DATE > TDRCALL.APPO_DATE 
                    && TDRCALL.CALL_STS == '0') 
                    || (TDRCALL.CALL_STS == '3' 
                    && TDRCALL.JRNNO == SCCGWA.COMM_AREA.JRN_NO)) {
                    WS_AMT_W = WS_AMT_W + TDRCALL.APPO_AMT;
                    if (TDCCDINT.CALL_SRC == 'M') {
                        TDRCALL.CALL_STS = '3';
                        TDRCALL.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                        T000_REWRITE_CALL_TABLE();
                        if (pgmRtn) return;
                    }
                }
            }
            T000_READNEXT_CALL_TABLE();
            if (pgmRtn) return;
        }
        T000_ENDBR_CALL_TABLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "KING6");
        if (TDCCDINT.INT_STSW.MTZ_FLG == '1') {
            WS_APPO_AMT = TDCCDINT.TXN_AMT;
            WS_APPO_DATE = TDCCDINT.TXN_DATE;
        }
        if (WS_APPO_DATE > TDCCDINT.VAL_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
            SCCCLDT.DATE2 = WS_APPO_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            if (SCCCLDT.DAYS < 7 
                && TDCCDINT.TERM.equalsIgnoreCase("D007")) {
                WS_APPO_DATE = 0;
            }
        } else {
            WS_APPO_DATE = 0;
        }
        CEP.TRC(SCCGWA, WS_APPO_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.CALL_NO);
        CEP.TRC(SCCGWA, TDRCALL.APPO_DATE);
        if (WS_APPO_DATE == 0 
            && TDCCDINT.CALL_NO != 0) {
            if (TDRCALL.APPO_DATE > 0) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_N_TZZ_DT, TDCCDINT.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CAL_DR_INT_TTZ_OLD() throws IOException,SQLException,Exception {
        if (TDCCDINT.RAT == 0) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_OLD_MUST_NOT_ZERO, TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_GET_DD_RAT();
        if (pgmRtn) return;
        WS_DATE1 = TDCCDINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_RAT = WS_DD_RAT;
        WS_EXC_RAT = WS_DD_RAT;
        if (TDCCDINT.INT_STSW.RULE != ' ') {
            WS_RULE = TDCCDINT.INT_STSW.RULE;
        }
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
        WS_TAXING_INT = WS_INT_3;
        WS_INT_3 = WS_AMT_2 * TDCCDINT.RAT / 100 * 1080 / WS_BASE_DAYS + WS_TAXING_INT;
    }
    public void B200_CAL_DR_INT_TTZ_JT() throws IOException,SQLException,Exception {
        WS_GRT_MAMT_FLG = 'Y';
        R000_CHK_IF_MLET_AMT();
        if (pgmRtn) return;
        if (WS_GRT_MAMT_FLG == 'Y') {
            if (TDCCDINT.RAT == 0) {
                WS_TERM = TDCCDINT.TERM;
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
                if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                    WS_INDEX_DATE = TDCCDINT.VAL_DATE;
                } else {
                    WS_INDEX_DATE = TDCCDINT.TXN_DATE;
                }
                R000_GET_TD_RAT();
                if (pgmRtn) return;
            }
        } else {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            TDCCDINT.RAT = WS_DD_RAT;
            WS_RAT = WS_DD_RAT;
            WS_EXC_RAT = WS_DD_RAT;
        }
        R000_CAL_VT1_DAYS();
        if (pgmRtn) return;
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_RAT = TDCCDINT.RAT;
        if (TDCCDINT.INT_STSW.RULE != ' ') {
            WS_RULE = TDCCDINT.INT_STSW.RULE;
        }
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
        WS_TAXING_INT = WS_INT_3;
    }
    public void R000_CHK_IF_MLET_AMT() throws IOException,SQLException,Exception {
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 12 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "TXT");
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCCDINT.CCY)) {
                CEP.TRC(SCCGWA, WS_CNT);
                WS_MIN_DRAW_AMT = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MLET_AMT;
                WS_W = WS_CNT;
                WS_CCY_FOUND = 'Y';
            }
        }
        CEP.TRC(SCCGWA, "XXXXXXXXX");
        if (!TDCCDINT.CCY.equalsIgnoreCase("156") 
            && !TDCCDINT.CCY.equalsIgnoreCase("840")) {
            for (WS_CNT = 1; WS_CNT <= 12; WS_CNT += 1) {
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase("840")) {
                    WS_MIN_DRAW_AMT_USD = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MLET_AMT;
                    WS_CNT = 99;
                }
            }
            if (WS_MIN_DRAW_AMT == 0 
                && WS_MIN_DRAW_AMT_USD != 0) {
                WS_BUY_AMT = WS_MIN_DRAW_AMT_USD;
                R000_AMT_EX_PROC();
                if (pgmRtn) return;
                WS_MIN_DRAW_AMT = WS_SELL_AMT;
            }
        }
        if (TDCCDINT.TXN_AMT < WS_MIN_DRAW_AMT) {
            WS_GRT_MAMT_FLG = 'N';
        }
    }
    public void B200_CAL_INT_DD() throws IOException,SQLException,Exception {
        R000_GET_DD_RAT();
        if (pgmRtn) return;
        TDCCDINT.RAT = WS_DD_RAT;
        WS_RAT = WS_DD_RAT;
        WS_EXC_RAT = WS_DD_RAT;
        R000_CAL_VT1_DAYS();
        if (pgmRtn) return;
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_RAT = TDCCDINT.RAT;
        if (TDCCDINT.INT_STSW.RULE != ' ') {
            WS_RULE = TDCCDINT.INT_STSW.RULE;
        }
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
        WS_TAXING_INT = WS_INT_3;
    }
    public void B200_CAL_DR_INT_TLZ() throws IOException,SQLException,Exception {
        R000_GET_DD_RAT();
        if (pgmRtn) return;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
            && TDCCDINT.TXN_DATE >= TDCCDINT.EXP_DATE) {
            if (TDCCDINT.TERM.compareTo("Y006") >= 0) {
                WS_TERM = "Y005";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else {
                WS_TERM = TDCCDINT.TERM;
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            }
            WS_IRAT_CD = TDCCDINT.NE_IRAT_CD;
            if (TDCCDINT.OPT == '4') {
                WS_IRAT_CD = TDCCDINT.PR_IRAT_CD;
            }
            if (WS_IRAT_CD.trim().length() == 0) {
                WS_IRAT_CD = TDCCDINT.IRAT_CD;
            }
            if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                WS_INDEX_DATE = TDCCDINT.VAL_DATE;
            } else {
                WS_INDEX_DATE = TDCCDINT.TXN_DATE;
            }
            R000_GET_TD_RAT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            R000_CAL_LT0_DAYS();
            if (pgmRtn) return;
            R000_CAL_LT1_DAYS();
            if (pgmRtn) return;
            if (TDCCDINT.OPT == '4') {
                R000_CAL_ACCU();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_NACCU);
                CEP.TRC(SCCGWA, TDCCDINT.RAT);
                CEP.TRC(SCCGWA, WS_LACCU);
                CEP.TRC(SCCGWA, WS_DD_RAT);
                CEP.TRC(SCCGWA, WS_BASE_DAYS);
                WS_INT_3 = WS_TAX_INT_ALL;
            } else {
                TDCCDINT.RAT = WS_DD_RAT;
                R000_CAL_ACCU2();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_SACCU_TAX);
                CEP.TRC(SCCGWA, WS_LACCU_TAX);
                CEP.TRC(SCCGWA, WS_SACCU);
                CEP.TRC(SCCGWA, WS_LACCU);
                CEP.TRC(SCCGWA, WS_DD_RAT);
                WS_INT_3 = ( WS_SACCU_TAX + WS_LACCU_TAX ) * TDCCDINT.RAT / 100 / WS_BASE_DAYS;
                WS_EXC_RAT = TDCCDINT.RAT;
            }
        } else if (TDCCDINT.TXN_DATE == TDCCDINT.EXP_DATE) {
            R000_CAL_LE0_DAYS();
            if (pgmRtn) return;
            R000_CAL_LE1_DAYS();
            if (pgmRtn) return;
            R000_CAL_ACCU2();
            if (pgmRtn) return;
            WS_EXC_RAT = TDCCDINT.RAT;
            WS_INT_3 = WS_TAX_INT_ALL;
        } else if (TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE) {
            R000_CAL_ACCU2();
            if (pgmRtn) return;
            WS_EXC_RAT = TDCCDINT.RAT;
            CEP.TRC(SCCGWA, WS_AMT_2);
            if (TDCCDINT.LAST_DEAL_DATE < TDCCDINT.EXP_DATE) {
                WS_DATE1 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            } else {
                WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            }
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = WS_DD_RAT;
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            WS_INT_3 = WS_TAX_INT_ALL + WS_INT_3;
        }
        WS_TAXING_INT = WS_INT_3;
    }
    public void B200_CAL_DR_INT_TLZ_EDU() throws IOException,SQLException,Exception {
        R000_GET_DD_RAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        R000_GET_DEP_MTHS_2();
        if (pgmRtn) return;
        if (WS_MTHS >= 3) {
            if (WS_MTHS < 6 
                    && WS_MTHS >= 3) {
                WS_TERM = "M003";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else if (WS_MTHS < 12 
                    && WS_MTHS >= 6) {
                WS_TERM = "M006";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else if (WS_MTHS < 24 
                    && WS_MTHS >= 12) {
                WS_TERM = "Y001";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else if (WS_MTHS < 36 
                    && WS_MTHS >= 24) {
                WS_TERM = "Y002";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else if (WS_MTHS < 60 
                    && WS_MTHS >= 36) {
                WS_TERM = "Y003";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else {
                WS_TERM = "Y005";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            }
            WS_IRAT_CD = K_TD_BASE_TYP;
            if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                WS_INDEX_DATE = TDCCDINT.VAL_DATE;
            } else {
                if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
                    WS_INDEX_DATE = TDCCDINT.TXN_DATE;
                } else {
                    WS_INDEX_DATE = TDCCDINT.EXP_DATE;
                }
            }
            R000_GET_TD_RAT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            R000_CAL_LT0_DAYS();
            if (pgmRtn) return;
            R000_CAL_LT1_DAYS();
            if (pgmRtn) return;
            R000_CAL_ACCU2();
            if (pgmRtn) return;
            if (TDCCDINT.OPT == '4') {
                WS_INT_3 = WS_TAX_INT_ALL;
            } else {
                if (WS_MTHS < 3) {
                    TDCCDINT.RAT = WS_DD_RAT;
                    WS_EXC_RAT = WS_DD_RAT;
                    CEP.TRC(SCCGWA, WS_SACCU_TAX);
                    CEP.TRC(SCCGWA, WS_LACCU_TAX);
                    CEP.TRC(SCCGWA, WS_SACCU);
                    CEP.TRC(SCCGWA, WS_LACCU);
                    CEP.TRC(SCCGWA, TDCCDINT.RAT);
                    WS_INT_3 = ( WS_SACCU_TAX + WS_LACCU_TAX ) * TDCCDINT.RAT / 100 / WS_BASE_DAYS;
                } else {
                    WS_EXC_RAT = TDCCDINT.RAT;
                    WS_INT_3 = WS_TAX_INT_ALL;
                }
            }
            WS_TAXING_INT = 0;
        } else if (TDCCDINT.TXN_DATE == TDCCDINT.EXP_DATE) {
            R000_CAL_LE0_DAYS();
            if (pgmRtn) return;
            R000_CAL_LE1_DAYS();
            if (pgmRtn) return;
            R000_CAL_ACCU2();
            if (pgmRtn) return;
            WS_EXC_RAT = TDCCDINT.RAT;
            WS_INT_3 = WS_TAX_INT_ALL;
            WS_TAXING_INT = 0;
        } else if (TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE) {
            R000_CAL_ACCU2();
            if (pgmRtn) return;
            WS_EXC_RAT = TDCCDINT.RAT;
            if (TDCCDINT.LAST_DEAL_DATE < TDCCDINT.EXP_DATE) {
                WS_DATE1 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            } else {
                WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            }
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = WS_DD_RAT;
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_AMT_2);
            WS_TAXING_INT = WS_INT_3;
            CEP.TRC(SCCGWA, WS_INT_3);
            WS_INT_3 = WS_TAX_INT_ALL + WS_INT_3;
        }
    }
    public void B200_CAL_DR_INT_TFF_TCQ() throws IOException,SQLException,Exception {
        if (TDCCDINT.TXN_AMT != TDCCDINT.BAL) {
            TDCCDINT.TRANS_INT = TDCCDINT.TRANS_INT * TDCCDINT.TXN_AMT / TDCCDINT.BAL;
        }
        if (TDCCDINT.PAYING_INT == 0) {
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = TDCCDINT.RAT;
            WS_EXC_RAT = TDCCDINT.RAT;
            if (TDCCDINT.INT_STSW.RULE != ' ') {
                WS_RULE = TDCCDINT.INT_STSW.RULE;
            }
            WS_REG_FLG = TDCCDINT.REG_FLG;
            TDCCDINT.REG_FLG = ' ';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            TDCCDINT.REG_FLG = WS_REG_FLG;
            TDCCDINT.PAYING_INT = WS_INT_3;
        }
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            if (TDCCDINT.OPT == '4') {
                WS_DATE1 = TDCCDINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                WS_RAT = TDCCDINT.RAT;
                if (TDCCDINT.INT_STSW.RULE != ' ') {
                    WS_RULE = TDCCDINT.INT_STSW.RULE;
                }
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
            } else {
                R000_GET_DD_RAT();
                if (pgmRtn) return;
                WS_DATE1 = TDCCDINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                TDCCDINT.RAT = WS_DD_RAT;
                WS_RAT = WS_DD_RAT;
                WS_EXC_RAT = WS_DD_RAT;
                WS_RULE = '3';
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
                if (TDCCDINT.REG_FLG == '1' 
                    && TDCCDINT.TRANS_INT > 0) {
                    R000_WRT_DRW_INT();
                    if (pgmRtn) return;
                }
            }
            WS_TAXING_INT = WS_INT_3;
        } else if (TDCCDINT.TXN_DATE == TDCCDINT.EXP_DATE) {
            WS_INT_3 = TDCCDINT.PAYING_INT - TDCCDINT.TRANS_INT;
            if (WS_INT_3 > 0 
                && TDCCDINT.REG_FLG == '1') {
                R000_REG_DINT_TCQ();
                if (pgmRtn) return;
            }
            WS_RAT = TDCCDINT.RAT;
            TDCCDINT.TRANS_INT = 0;
            WS_TAXING_INT = TDCCDINT.PAYING_INT;
            if (WS_ITAX_FLG == 'N') {
                WS_TAX_DATE1 = TDCCDINT.VAL_DATE;
                WS_TAX_FLG = 'Y';
                WS_TAX_DATE2 = TDCCDINT.EXP_DATE;
                WS_INT_TEMP = WS_INT_3;
                WS_INT_3 = TDCCDINT.PAYING_INT;
                R000_COMPUTE_TAX_INFO();
                if (pgmRtn) return;
                WS_INT_3 = WS_INT_TEMP;
            }
        } else if (TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE) {
            WS_INT_3 = TDCCDINT.PAYING_INT - TDCCDINT.TRANS_INT;
            if (WS_INT_3 > 0 
                && TDCCDINT.REG_FLG == '1') {
                R000_REG_DINT_TCQ();
                if (pgmRtn) return;
            }
            if (WS_ITAX_FLG == 'N') {
                WS_TAX_FLG = 'Y';
                WS_TAX_DATE1 = TDCCDINT.VAL_DATE;
                WS_TAX_DATE2 = TDCCDINT.EXP_DATE;
                WS_INT_TEMP = WS_INT_3;
                WS_INT_3 = TDCCDINT.PAYING_INT;
                R000_COMPUTE_TAX_INFO();
                if (pgmRtn) return;
                WS_INT_3 = WS_INT_TEMP;
            }
            WS_EXC_RAT = TDCCDINT.RAT;
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            WS_DATE1 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = WS_DD_RAT;
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            WS_INT_3 = WS_INT_3 + TDCCDINT.PAYING_INT;
            WS_TAXING_INT = WS_INT_3;
            WS_INT_3 = WS_INT_3 - TDCCDINT.TRANS_INT;
            TDCCDINT.TRANS_INT = 0;
        }
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void B200_CAL_DR_INT_TFF_TXY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        WS_TRANS_INT = TDCCDINT.TRANS_INT;
        if (TDCCDINT.TXN_AMT != TDCCDINT.BAL 
            && TDCCDINT.BAL != 0) {
            TDCCDINT.TRANS_INT = TDCCDINT.TRANS_INT * TDCCDINT.TXN_AMT / TDCCDINT.BAL;
            bigD = new BigDecimal(TDCCDINT.TRANS_INT);
            TDCCDINT.TRANS_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            if (TDCCDINT.OPT == '4') {
                if (TDCCDINT.PART_NUM > 0 
                    && TDRAINT.DUE_RAT != 0) {
                    if (TDCCDINT.VAL_DATE < TDCCDINT.LAST_DEAL_DATE) {
                        WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
                        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                    } else {
                        WS_DATE1 = TDCCDINT.VAL_DATE;
                        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                    }
                    WS_DATE2 = TDCCDINT.TXN_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                    WS_RULE = '3';
                    WS_RAT = TDRAINT.DUE_RAT;
                    R000_COMPUTE_INT_BY_RULE();
                    if (pgmRtn) return;
                } else {
                    R000_GET_TXY_NOR_INT();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
                if (TDCCDINT.TRANS_INT == 0) {
                    if (TDCCDINT.INT_STSW.ERLY_FLG == '2' 
                        || TDCCDINT.INT_STSW.ERLY_FLG == '3' 
                        || TDCCDINT.INT_STSW.ERLY_FLG == '4') {
                        B200_CAL_DR_INT_ERLY();
                        if (pgmRtn) return;
                    } else {
                        WS_DATE1 = TDCCDINT.VAL_DATE;
                        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                        WS_DATE2 = TDCCDINT.TXN_DATE;
                        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                        WS_RULE = '3';
                        WS_RAT = TDRAINT.PRV_RAT;
                        WS_EXC_RAT = TDRAINT.PRV_RAT;
                        R000_COMPUTE_INT_BY_RULE();
                        if (pgmRtn) return;
                    }
                } else {
                    R000_GET_PRV_AMT();
                    if (pgmRtn) return;
                }
                if (TDCCDINT.REG_FLG == '1' 
                    && TDCCDINT.TRANS_INT > 0) {
                    R000_WRT_DRW_INT();
                    if (pgmRtn) return;
                }
            }
        } else if (TDCCDINT.TXN_DATE == TDCCDINT.EXP_DATE) {
            CEP.TRC(SCCGWA, TDCCDINT.PART_NUM);
            if (TDCCDINT.PART_NUM > 0 
                && TDRAINT.DUE_RAT != 0) {
                R000_GET_TXY_DUE_INT();
                if (pgmRtn) return;
            } else {
                R000_GET_TXY_NOR_INT();
                if (pgmRtn) return;
            }
        } else if (TDCCDINT.TXN_DATE > TDCCDINT.EXP_DATE) {
            if (TDCCDINT.PART_NUM > 0 
                && TDRAINT.DUE_RAT != 0) {
                R000_GET_TXY_DUE_INT();
                if (pgmRtn) return;
            } else {
                R000_GET_TXY_NOR_INT();
                if (pgmRtn) return;
            }
            WS_INT_TMP = WS_INT_3;
            WS_DATE1 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RULE = '3';
            WS_RAT = TDRAINT.OVE_RAT;
            TDCCDINT.DD_RAT = TDRAINT.OVE_RAT;
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            WS_INT_3 = WS_INT_TMP + WS_INT_3;
        } else {
        }
    }
    public void R000_GET_PRV_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        CEP.TRC(SCCGWA, "DUE-INT");
        CEP.TRC(SCCGWA, TDCCDINT.LAST_DEAL_DATE);
        WS_DATE2 = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_AMT_2 = TDCCDINT.BAL - TDCCDINT.TXN_AMT;
        CEP.TRC(SCCGWA, WS_AMT_2);
        if (TDRAINT.DUE_RAT != 0) {
            WS_RAT = TDRAINT.DUE_RAT;
            WS_EXC_RAT = TDRAINT.DUE_RAT;
            WS_RULE = '3';
            WS_REG_FLG = TDCCDINT.REG_FLG;
            TDCCDINT.REG_FLG = ' ';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            TDCCDINT.REG_FLG = WS_REG_FLG;
            WS_DUE_INT = WS_INT_3;
            WS_AMT_3 = WS_DUE_INT;
            R000_GET_RESULT_AMT();
            if (pgmRtn) return;
            WS_DUE_INT = WS_AMT_3;
            CEP.TRC(SCCGWA, WS_DUE_INT);
            TDCCDINT.TRANS_INT = WS_TRANS_INT - WS_DUE_INT;
        } else {
            WS_DUE_INT = WS_TRANS_INT - TDCCDINT.TRANS_INT;
        }
        CEP.TRC(SCCGWA, TDCCDINT.BACK_INT);
        TDCCDINT.BACK_INT = WS_DUE_INT;
        CEP.TRC(SCCGWA, "PRV-INT");
        if ((TDCCDINT.INT_STSW.ERLY_FLG == '2' 
            || TDCCDINT.INT_STSW.ERLY_FLG == '3' 
            || TDCCDINT.INT_STSW.ERLY_FLG == '4') 
            || ((TDCCDINT.INT_STSW.ERLY_FLG == '0' 
            || TDCCDINT.INT_STSW.ERLY_FLG == ' ') 
            && TDCPIOD.EXP_PRM.ERLY_TYP == '2' 
            || TDCPIOD.EXP_PRM.ERLY_TYP == '3' 
            || TDCPIOD.EXP_PRM.ERLY_TYP == '4')) {
            B200_CAL_DR_INT_ERLY();
            if (pgmRtn) return;
        } else {
            WS_AMT_2 = TDCCDINT.TXN_AMT;
            CEP.TRC(SCCGWA, WS_AMT_2);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RULE = '3';
            WS_RAT = TDRAINT.PRV_RAT;
            WS_EXC_RAT = TDRAINT.PRV_RAT;
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
        }
        WS_PRV_INT = WS_INT_3;
        CEP.TRC(SCCGWA, WS_PRV_INT);
    }
    public void R000_GET_TXY_DUE_INT() throws IOException,SQLException,Exception {
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        if (TDCCDINT.VAL_DATE < TDCCDINT.LAST_DEAL_DATE) {
            WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        } else {
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        }
        CEP.TRC(SCCGWA, WS_DATE1);
        WS_DATE2 = TDCCDINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_RULE = '3';
        WS_RAT = TDRAINT.DUE_RAT;
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
    }
    public void R000_GET_TXY_NOR_INT() throws IOException,SQLException,Exception {
        WS_INT_TMP = 0;
        while (TDCCDINT.TXN_DATE > TDRIREV.KEY.STR_DATE 
            && TDCCDINT.EXP_DATE > TDRIREV.KEY.STR_DATE 
            && WS_IREV_FLG != 'N') {
            WS_DATE1 = WS_TMP_VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = WS_TMP_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            if (TDCCDINT.INT_STSW.RULE != ' ') {
                WS_RULE = TDCCDINT.INT_STSW.RULE;
            }
            WS_RAT = WS_TMP_CDINT_RAT;
            WS_EXC_RAT = WS_TMP_CDINT_RAT;
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "KINGKING");
            CEP.TRC(SCCGWA, WS_INT_3);
            WS_INT_TMP = WS_INT_TMP + WS_INT_3;
            CEP.TRC(SCCGWA, WS_TMP_VAL_DATE);
            CEP.TRC(SCCGWA, WS_TMP_EXP_DATE);
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, WS_RAT);
            CEP.TRC(SCCGWA, WS_INT_3);
            CEP.TRC(SCCGWA, WS_INT_TMP);
            T000_READNEXT_TDTIREV();
            if (pgmRtn) return;
        }
        WS_INT_3 = WS_INT_TMP;
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void R000_GET_TXY_DUE_INT_PI() throws IOException,SQLException,Exception {
        WS_DATE1 = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        if (TDCCDINT.VAL_DATE < TDCCDINT.LAST_DEAL_DATE) {
            WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        } else {
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        }
        CEP.TRC(SCCGWA, "XIANLISHI");
        CEP.TRC(SCCGWA, WS_DATE1);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_RULE = '3';
        WS_RAT = TDRAINT.DUE_RAT;
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
    }
    public void R000_GET_TXY_NOR_INT_PI() throws IOException,SQLException,Exception {
        WS_INT_TMP = 0;
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        WS_INT_TMP_1 = TDCCDINT.TRANS_INT;
        while (TDCCDINT.TXN_DATE > TDRIREV.KEY.STR_DATE 
            && TDCCDINT.EXP_DATE > TDRIREV.KEY.STR_DATE 
            && WS_IREV_FLG != 'N') {
            WS_DATE1 = WS_TMP_VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = WS_TMP_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            if (TDCCDINT.INT_STSW.RULE != ' ') {
                WS_RULE = TDCCDINT.INT_STSW.RULE;
            }
            WS_RAT = WS_TMP_CDINT_RAT;
            WS_REG_FLG = TDCCDINT.REG_FLG;
            TDCCDINT.REG_FLG = '0';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            TDCCDINT.REG_FLG = WS_REG_FLG;
            WS_INT_TMP = WS_INT_TMP + WS_INT_3;
            if (TDCCDINT.REG_FLG == '1' 
                && WS_TMP_EXP_DATE > TDCCDINT.LAST_DEAL_DATE) {
                if (WS_TMP_VAL_DATE > TDCCDINT.LAST_DEAL_DATE) {
                    WS_DATE1 = WS_TMP_VAL_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                } else {
                    WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                }
                WS_DATE2 = WS_TMP_EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                WS_TAX_VAL = 0;
                WS_TAX_INT = WS_INT_3;
                WS_TAX_INT = WS_INT_TMP - WS_INT_TMP_1;
                WS_INT_TMP_1 = WS_INT_TMP;
                R000_REG_INT_TAX_INF();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_TMP_VAL_DATE);
            CEP.TRC(SCCGWA, WS_TMP_EXP_DATE);
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, WS_RAT);
            CEP.TRC(SCCGWA, WS_INT_3);
            CEP.TRC(SCCGWA, WS_INT_TMP);
            T000_READNEXT_TDTIREV();
            if (pgmRtn) return;
        }
        WS_INT_3 = WS_INT_TMP;
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void R000_GET_DISJOIN_INT() throws IOException,SQLException,Exception {
        WS_INT_TMP_1 = 0;
        WS_INT_TAX_5 = 0;
        while (TDCCDINT.TXN_DATE > TDRIREV.KEY.STR_DATE 
            && TDCCDINT.EXP_DATE > TDRIREV.KEY.STR_DATE 
            && WS_IREV_FLG != 'N') {
            WS_DATE1 = WS_TMP_VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = WS_TMP_EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RULE = TDCCDINT.INT_STSW.RULE;
            WS_RAT = WS_TMP_CDINT_RAT;
            WS_EXC_RAT = WS_TMP_CDINT_RAT;
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_INT_3);
            WS_INT_TMP_1 = WS_INT_TMP_1 + WS_INT_3;
            WS_INT_TAX_5 = WS_INT_TAX_5 + WS_INT_TAX_TIM;
            CEP.TRC(SCCGWA, WS_TMP_VAL_DATE);
            CEP.TRC(SCCGWA, WS_TMP_EXP_DATE);
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, WS_RAT);
            CEP.TRC(SCCGWA, WS_INT_3);
            CEP.TRC(SCCGWA, WS_INT_TMP_1);
            CEP.TRC(SCCGWA, WS_INT_TAX_5);
            T000_READNEXT_TDTIREV();
            if (pgmRtn) return;
        }
        WS_INT_3 = WS_INT_TMP_1;
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void B200_CAL_DR_INT_TDH() throws IOException,SQLException,Exception {
        WS_TXN_DATE = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        R000_GET_DEP_MTHS();
        if (pgmRtn) return;
        R000_GET_DD_RAT();
        if (pgmRtn) return;
        if (WS_MTHS < 3) {
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            TDCCDINT.RAT = WS_DD_RAT;
            WS_RAT = TDCCDINT.RAT;
            WS_EXC_RAT = TDCCDINT.RAT;
            WS_RULE = '3';
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
        } else {
            if (WS_MTHS < 6 
                    && WS_MTHS >= 3) {
                WS_TERM = "M003";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else if (WS_MTHS < 12 
                    && WS_MTHS >= 6) {
                WS_TERM = "M006";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            } else {
                WS_TERM = "Y001";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            }
            CEP.TRC(SCCGWA, TDCPIOD.INT_PRM.RAT_INX);
            if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                WS_INDEX_DATE = TDCCDINT.VAL_DATE;
            } else {
                WS_INDEX_DATE = TDCCDINT.TXN_DATE;
            }
            R000_GET_TD_RAT();
            if (pgmRtn) return;
            if (TDCCDINT.RAT < WS_DD_RAT) {
                TDCCDINT.RAT = WS_DD_RAT;
            }
            WS_DATE1 = TDCCDINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_RAT = TDCCDINT.RAT;
            WS_EXC_RAT = TDCCDINT.RAT;
            if (TDCCDINT.INT_STSW.RULE != ' ') {
                WS_RULE = TDCCDINT.INT_STSW.RULE;
            }
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
        }
        WS_TAXING_INT = WS_INT_3;
    }
    public void B200_CAL_DAIFU_INT() throws IOException,SQLException,Exception {
        WS_INT_3 = TDCCDINT.PAYING_INT * TDCCDINT.TXN_AMT / TDCCDINT.BAL;
        WS_TAXING_INT = WS_INT_3;
    }
    public void B300_CAL_PAYING_INT_TCQ() throws IOException,SQLException,Exception {
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, WS_TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.CD_PERD);
        CEP.TRC(SCCGWA, TDCCDINT.PERD_TYP);
        if (TDCCDINT.PERD_TYP == '1' 
            || TDCCDINT.PERD_TYP == ' ') {
            R000_GET_DEP_MTHS();
            if (pgmRtn) return;
            WS_AMT = WS_MTHS % TDCCDINT.CD_PERD;
            WS_LEN = (short) ((WS_MTHS - WS_AMT) / TDCCDINT.CD_PERD);
        } else {
            if (TDCCDINT.PERD_TYP == '2') {
                WS_TMP_DATE1 = TDCCDINT.VAL_DATE;
                WS_TMP_DATE2 = TDCCDINT.TXN_DATE;
                R000_GET_TMP_DAYS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_TMP_DAYS);
                WS_LEN = (short) ((WS_TMP_DAYS - WS_TMP_DAYS % TDCCDINT.CD_PERD) / TDCCDINT.CD_PERD);
            }
        }
        if (WS_LEN <= 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_PAYING_INT);
        }
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_INT_3 = WS_LEN * TDCCDINT.CD_AMT - TDCCDINT.TRANS_INT;
        } else {
            WS_INT_3 = TDCCDINT.PAYING_INT - TDCCDINT.TRANS_INT;
        }
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, TDCCDINT.CD_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        CEP.TRC(SCCGWA, WS_INT_3);
        TDCCDINT.NOR_NUM = WS_LEN;
        TDCCDINT.TRANS_INT = 0;
        if (TDCCDINT.REG_FLG == '1' 
            || TDCCDINT.REG_FLG == '2') {
            WS_RAT = TDCCDINT.RAT;
            WS_TAX_VAL = 0;
            WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
            if (TDCCDINT.PERD_TYP == '1' 
                || TDCCDINT.PERD_TYP == ' ') {
                SCCCLDT.MTHS = (short) (WS_LEN * TDCCDINT.CD_PERD);
            } else {
                SCCCLDT.DAYS = WS_LEN * TDCCDINT.CD_PERD;
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE2 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_TAX_INT = WS_INT_3;
            if (TDCCDINT.REG_FLG == '1') {
                R000_REG_INT_TAX_INF();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_CAL_PAYING_AMT_TZL() throws IOException,SQLException,Exception {
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, WS_TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.CD_PERD);
        CEP.TRC(SCCGWA, TDCCDINT.PERD_TYP);
        if (TDCCDINT.PERD_TYP == '1' 
            || TDCCDINT.PERD_TYP == ' ') {
            R000_GET_DEP_MTHS();
            if (pgmRtn) return;
            WS_AMT = WS_MTHS % TDCCDINT.CD_PERD;
            WS_LEN = (short) ((WS_MTHS - WS_AMT) / TDCCDINT.CD_PERD);
        } else {
            if (TDCCDINT.PERD_TYP == '2') {
                WS_TMP_DATE1 = TDCCDINT.VAL_DATE;
                WS_TMP_DATE2 = TDCCDINT.TXN_DATE;
                R000_GET_TMP_DAYS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_TMP_DAYS);
                WS_LEN = (short) ((WS_TMP_DAYS - WS_TMP_DAYS % TDCCDINT.CD_PERD) / TDCCDINT.CD_PERD);
            }
        }
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            CEP.TRC(SCCGWA, WS_LEN);
            CEP.TRC(SCCGWA, TDCCDINT.NOR_NUM);
            WS_INT_3 = ( WS_LEN - TDCCDINT.NOR_NUM ) * TDCCDINT.CD_AMT;
        } else {
            WS_INT_3 = TDCCDINT.BAL - TDCCDINT.NOR_NUM * TDCCDINT.CD_AMT;
        }
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, TDCCDINT.CD_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        CEP.TRC(SCCGWA, WS_INT_3);
        TDCCDINT.NOR_NUM = WS_LEN;
        TDCCDINT.TRANS_INT = 0;
    }
    public void B300_CAL_PAYING_INT_TXY() throws IOException,SQLException,Exception {
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        R000_CAL_VT0_DAYS();
        if (pgmRtn) return;
        WS_INT_3 = WS_AMT_2 * TDCCDINT.RAT / 100 * TDCCDINT.DAYS / WS_BASE_DAYS - TDCCDINT.TRANS_INT;
        TDCCDINT.TRANS_INT = 0;
    }
    public void B300_CAL_PAYING_INT_TXY_2() throws IOException,SQLException,Exception {
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        CEP.TRC(SCCGWA, TDCCDINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, "XIANKAN");
        CEP.TRC(SCCGWA, TDCCDINT.PART_NUM);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        if (TDCCDINT.PART_NUM == 0 
            && DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && (TDCCDINT.TXN_AMT != TDCCDINT.BAL)) {
            TDCCDINT.PART_NUM += 1;
        }
        if (TDCCDINT.PART_NUM > 0 
            && TDRAINT.DUE_RAT != 0) {
            R000_GET_TXY_DUE_INT_PI();
            if (pgmRtn) return;
        } else {
            R000_GET_TXY_NOR_INT_PI();
            if (pgmRtn) return;
        }
        WS_INT_3 = WS_INT_3 - TDCCDINT.TRANS_INT;
        TDCCDINT.TRANS_INT = 0;
    }
    public void B300_CAL_GKING_AMT() throws IOException,SQLException,Exception {
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE) {
            WS_TXN_DATE = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        } else {
            WS_TXN_DATE = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES107);
        }
        R000_GET_DEP_MTHS_2();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCDINT.CD_PERD);
        WS_AMT = WS_MTHS % TDCCDINT.CD_PERD;
        WS_LEN = (short) ((WS_MTHS - WS_AMT) / TDCCDINT.CD_PERD);
        WS_SHD_NUM = WS_LEN;
        CEP.TRC(SCCGWA, WS_SHD_NUM);
        if (TDCCDINT.TERM == null) TDCCDINT.TERM = "";
        JIBS_tmp_int = TDCCDINT.TERM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) TDCCDINT.TERM += " ";
        if (TDCCDINT.TERM.substring(0, 1).equalsIgnoreCase("Y")) {
            if (TDCCDINT.TERM == null) TDCCDINT.TERM = "";
            JIBS_tmp_int = TDCCDINT.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCCDINT.TERM += " ";
            if (TDCCDINT.TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_TERM_MTHS2 = 0;
            else WS_TERM_MTHS2 = Short.parseShort(TDCCDINT.TERM.substring(2 - 1, 2 + 3 - 1));
            WS_TERM_MTHS2 = (short) (WS_TERM_MTHS2 * 12);
        }
        if (TDCCDINT.TERM == null) TDCCDINT.TERM = "";
        JIBS_tmp_int = TDCCDINT.TERM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) TDCCDINT.TERM += " ";
        if (TDCCDINT.TERM.substring(0, 1).equalsIgnoreCase("M")) {
            if (TDCCDINT.TERM == null) TDCCDINT.TERM = "";
            JIBS_tmp_int = TDCCDINT.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCCDINT.TERM += " ";
            if (TDCCDINT.TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_TERM_MTHS2 = 0;
            else WS_TERM_MTHS2 = Short.parseShort(TDCCDINT.TERM.substring(2 - 1, 2 + 3 - 1));
        }
        WS_AMT = WS_TERM_MTHS2 % TDCCDINT.CD_PERD;
        WS_PLAN_NUM = (short) ((WS_TERM_MTHS2 - WS_AMT) / TDCCDINT.CD_PERD);
        WS_SHD_NUM = (short) (WS_PLAN_NUM - WS_SHD_NUM);
        WS_LEN = (short) (WS_LEN - TDCCDINT.NOR_NUM - TDCCDINT.LOS_DNUM - TDCCDINT.LOS_NUM + 1);
        if (WS_LEN > 0 
            && WS_LEN < 3) {
            if (WS_LEN == 2 
                && WS_SHD_NUM != 1) {
                WS_LEN = (short) (WS_LEN + 1);
            }
            WS_INT_3 = WS_LEN * TDCCDINT.CD_AMT;
        }
    }
    public void B400_CAL_DR_INT_TTZ_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDCCDINT.AC;
        T000_STARTBR_CALL_TABLE();
        if (pgmRtn) return;
        T000_READNEXT_CALL_TABLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, "HAVE FOUND");
            if (TDCCDINT.CALL_NO == TDRCALL.KEY.CALL_NO) {
                TDRCALL.CALL_STS = '0';
                TDRCALL.JRNNO = 0;
                T000_REWRITE_CALL_TABLE();
                if (pgmRtn) return;
            } else {
                if (TDRCALL.CALL_STS == '3' 
                    && TDRCALL.JRNNO == SCCGWA.COMM_AREA.CANCEL_JRN_NO) {
                    if (TDRCALL.CAN_DATE > 0) {
                        TDRCALL.CALL_STS = '2';
                    } else {
                        TDRCALL.CALL_STS = '0';
                    }
                    TDRCALL.JRNNO = 0;
                    T000_REWRITE_CALL_TABLE();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CALL_TABLE();
            if (pgmRtn) return;
        }
        T000_ENDBR_CALL_TABLE();
        if (pgmRtn) return;
    }
    public void B900_PROCESS_DEC() throws IOException,SQLException,Exception {
        if (WS_INT_3 != 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = TDCCDINT.CCY;
            BPCRDAMT.AMT = WS_INT_3;
            CEP.TRC(SCCGWA, WS_INT_3);
            S00_CALL_BPZRDAMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
            TDCCDINT.PAYING_INT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        } else {
            TDCCDINT.PAYING_INT = WS_INT_3;
        }
        TDCCDINT.PAYING_INT += WS_INT_4;
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        if (TDCPIOD.EXP_PRM.TWAV_FLG == 'Y' 
            || TDCCDINT.OPT == '4') {
            WS_TAXING_INT = 0;
        }
        CEP.TRC(SCCGWA, WS_TAXING_INT);
        if (WS_TAXING_INT > 0 
            || WS_INT_TAX_ALL > 0) {
            TDCCDINT.TAXING_INT = WS_TAXING_INT;
            WS_PAYING_TAX = WS_TAXING_INT * TDCCDINT.TAX_RAT / 100;
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = TDCCDINT.CCY;
            BPCRDAMT.AMT = WS_PAYING_TAX;
            if (WS_PAYING_TAX == 0) {
                BPCRDAMT.AMT = WS_INT_TAX_ALL;
            }
            S00_CALL_BPZRDAMT();
            if (pgmRtn) return;
            TDCCDINT.PAYING_TAX = BPCRDAMT.RESULT_AMT;
        } else {
            TDCCDINT.TAXING_INT = 0;
            TDCCDINT.PAYING_TAX = 0;
        }
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_TAX);
        CEP.TRC(SCCGWA, TDCCDINT.TAXING_INT);
        CEP.TRC(SCCGWA, WS_TRANS_3);
        if (WS_TRANS_3 != 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = TDCCDINT.CCY;
            BPCRDAMT.AMT = WS_TRANS_3;
            S00_CALL_BPZRDAMT();
            if (pgmRtn) return;
            TDCCDINT.TRANS_INT = BPCRDAMT.RESULT_AMT;
        }
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        CEP.TRC(SCCGWA, TDCCDINT.TAXING_INT);
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_TAX);
    }
    public void R000_CAL_ACCU() throws IOException,SQLException,Exception {
        WS_SACCU_L = SCCCLDT.DAYS * ( TDCCDINT.TXN_AMT - TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM );
        WS_LACCU_L = SCCCLDT.DAYS * TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM;
        IBS.init(SCCGWA, TDRINTC);
        TDRINTC.KEY.ACO_AC = TDCCDINT.AC;
        T000_STARTBR_INTC();
        if (pgmRtn) return;
        T000_READNEXT_INTC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (TDRINTC.KEY.CLS == '4') {
                WS_DATE1 = TDRINTC.KEY.BDT;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDRINTC.EDT;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                R000_GET_YMD();
                if (pgmRtn) return;
                WS_YY_TACCU = WS_YY * TDRINTC.AMT + WS_YY_TACCU;
                WS_MM_TACCU = WS_MM * TDRINTC.AMT + WS_MM_TACCU;
                WS_DD_TACCU = WS_DD * TDRINTC.AMT + WS_DD_TACCU;
                WS_SACCU = WS_SACCU + TDRINTC.SACCU;
                WS_NACCU_TAX = TDRINTC.NACCU;
                WS_SACCU_TAX = WS_SACCU;
                WS_RAT = TDCCDINT.RAT;
                CEP.TRC(SCCGWA, WS_RAT);
            } else {
                WS_LACCU = WS_LACCU + TDRINTC.NACCU;
                WS_LACCU_TAX = WS_LACCU;
                WS_RAT = WS_DD_RAT;
            }
            WS_DATE1 = TDRINTC.KEY.BDT;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDRINTC.EDT;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_TAX_VAL = TDRINTC.TAX_RAT;
            WS_DINT_BAL = TDRINTC.AMT;
            R000_COMPUTE_TAX_LZ();
            if (pgmRtn) return;
            T000_READNEXT_INTC();
            if (pgmRtn) return;
        }
        T000_ENDBR_INTC();
        if (pgmRtn) return;
        WS_SACCU_TAX += WS_SACCU_L;
        WS_LACCU_TAX += WS_LACCU_L;
        WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        CEP.TRC(SCCGWA, TDCCDINT.LAST_DEAL_DATE);
        if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        } else {
            WS_DATE2 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        }
        R000_GET_YMD();
        if (pgmRtn) return;
        WS_YY_TACCU = WS_YY * ( TDCCDINT.TXN_AMT - TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM ) + WS_YY_TACCU;
        WS_MM_TACCU = WS_MM * ( TDCCDINT.TXN_AMT - TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM ) + WS_MM_TACCU;
        WS_DD_TACCU = WS_DD * ( TDCCDINT.TXN_AMT - TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM ) + WS_DD_TACCU;
        WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
            WS_DINT_BAL = TDCCDINT.TXN_AMT;
            WS_DATE2 = TDCCDINT.TXN_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        } else {
            WS_DINT_BAL = TDCCDINT.TXN_AMT;
            WS_DATE2 = TDCCDINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        }
        CEP.TRC(SCCGWA, WS_DATE1);
        if (TDCCDINT.EDU_FLG == '0' 
            && DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
            R000_COMPUTE_TAX_LZ();
            if (pgmRtn) return;
        } else {
            WS_REG_FLG = TDCCDINT.REG_FLG;
            TDCCDINT.REG_FLG = '0';
            R000_COMPUTE_TAX_LZ();
            if (pgmRtn) return;
            TDCCDINT.REG_FLG = WS_REG_FLG;
            WS_TAX_DATE1 = WS_DATE1;
            WS_TAX_DATE2 = WS_DATE2;
            WS_INT_3 = WS_TAX_INT;
            WS_RAT = TDCCDINT.RAT;
            if (WS_INT_3 != 0) {
                R000_COMPUTE_TAX_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CAL_ACCU2() throws IOException,SQLException,Exception {
        if (TDCCDINT.EXP_DATE > TDCCDINT.LAST_DEAL_DATE) {
            WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            } else {
                WS_DATE2 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            }
            WS_MM = (short) (( REDEFINES118.WS_DATE2_YY - REDEFINES113.WS_DATE1_YY ) * 12 + REDEFINES118.WS_DATE2_MM - REDEFINES113.WS_DATE1_MM);
            WS_SACCU_L = WS_MM * 30 * ( TDCCDINT.TXN_AMT - TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM );
            WS_LACCU_L = WS_MM * 30 * TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM;
            CEP.TRC(SCCGWA, WS_SACCU_L);
            CEP.TRC(SCCGWA, WS_LACCU_L);
        }
        IBS.init(SCCGWA, TDRINTC);
        TDRINTC.KEY.ACO_AC = TDCCDINT.AC;
        T000_STARTBR_INTC();
        if (pgmRtn) return;
        T000_READNEXT_INTC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_DATE1 = TDRINTC.KEY.BDT;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDRINTC.EDT;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_MM = (short) (( REDEFINES118.WS_DATE2_YY - REDEFINES113.WS_DATE1_YY ) * 12 + REDEFINES118.WS_DATE2_MM - REDEFINES113.WS_DATE1_MM);
            CEP.TRC(SCCGWA, WS_MM);
            if (TDRINTC.KEY.CLS == '4') {
                WS_MM_TACCU = WS_MM * TDRINTC.AMT + WS_MM_TACCU;
                CEP.TRC(SCCGWA, WS_MM_TACCU);
                CEP.TRC(SCCGWA, TDRINTC.AMT);
                if (TDRINTC.NACCU != 0 
                    || TDRINTC.SACCU != 0 
                    || TDRINTC.LACCU != 0) {
                    WS_NACCU_INTC = TDRINTC.NACCU;
                    WS_SACCU += TDRINTC.SACCU;
                    WS_SACCU = WS_SACCU + WS_NACCU_INTC;
                    WS_DD_TACCU += WS_NACCU_INTC;
                    WS_YY_TACCU = 0;
                    WS_MM_TACCU = 0;
                } else {
                    WS_NACCU_INTC = TDRINTC.AMT * WS_MM * 30;
                    CEP.TRC(SCCGWA, WS_NACCU_INTC);
                    WS_SACCU = WS_SACCU + WS_NACCU_INTC;
                    CEP.TRC(SCCGWA, WS_SACCU);
                }
                CEP.TRC(SCCGWA, WS_SACCU);
                WS_NACCU_TAX = WS_NACCU_INTC;
                WS_SACCU_TAX = WS_SACCU;
                WS_RAT = TDCCDINT.RAT;
                CEP.TRC(SCCGWA, WS_RAT);
            } else {
                if (TDRINTC.NACCU != 0 
                    || TDRINTC.SACCU != 0 
                    || TDRINTC.LACCU != 0) {
                    WS_NACCU_INTC = TDRINTC.NACCU;
                    WS_LACCU += TDRINTC.LACCU;
                    CEP.TRC(SCCGWA, WS_NACCU_INTC);
                    CEP.TRC(SCCGWA, WS_LACCU);
                    WS_LACCU = WS_LACCU + WS_NACCU_INTC;
                    WS_DD_TACCU += WS_NACCU_INTC;
                    WS_YY_TACCU = 0;
                    WS_MM_TACCU = 0;
                } else {
                    WS_NACCU_INTC = TDRINTC.AMT * WS_MM * 30;
                    CEP.TRC(SCCGWA, WS_NACCU_INTC);
                    WS_LACCU = WS_LACCU + WS_NACCU_INTC;
                }
                CEP.TRC(SCCGWA, WS_LACCU);
                WS_LACCU_TAX = WS_LACCU;
                WS_RAT = WS_DD_RAT;
            }
            WS_DATE1 = TDRINTC.KEY.BDT;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            WS_DATE2 = TDRINTC.EDT;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_TAX_VAL = TDRINTC.TAX_RAT;
            WS_DINT_BAL = TDRINTC.AMT;
            R000_COMPUTE_TAX_LZ();
            if (pgmRtn) return;
            T000_READNEXT_INTC();
            if (pgmRtn) return;
        }
        T000_ENDBR_INTC();
        if (pgmRtn) return;
        if (TDCCDINT.EXP_DATE > TDCCDINT.LAST_DEAL_DATE) {
            WS_SACCU_TAX += WS_SACCU_L;
            WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            CEP.TRC(SCCGWA, TDCCDINT.LAST_DEAL_DATE);
            if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            } else {
                WS_DATE2 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            }
            WS_MM = (short) (( REDEFINES118.WS_DATE2_YY - REDEFINES113.WS_DATE1_YY ) * 12 + REDEFINES118.WS_DATE2_MM - REDEFINES113.WS_DATE1_MM);
            CEP.TRC(SCCGWA, WS_MM);
            CEP.TRC(SCCGWA, WS_MM_TACCU);
            WS_MM_TACCU = WS_MM * ( TDCCDINT.TXN_AMT - TDCCDINT.CD_AMT * TDCCDINT.LOS_DNUM ) + WS_MM_TACCU;
            CEP.TRC(SCCGWA, WS_MM_TACCU);
            WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
                WS_DATE2 = TDCCDINT.TXN_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            } else {
                WS_DATE2 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            }
            WS_DINT_BAL = TDCCDINT.TXN_AMT - ( TDCCDINT.LOS_DNUM * TDCCDINT.CD_AMT );
            CEP.TRC(SCCGWA, WS_DINT_BAL);
            CEP.TRC(SCCGWA, WS_DATE1);
            if (TDCCDINT.EDU_FLG == '0' 
                && DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
                CEP.TRC(SCCGWA, "YYYYY");
                R000_COMPUTE_TAX_LZ();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "NNNNN");
                WS_REG_FLG = TDCCDINT.REG_FLG;
                TDCCDINT.REG_FLG = '0';
                R000_COMPUTE_TAX_LZ();
                if (pgmRtn) return;
                TDCCDINT.REG_FLG = WS_REG_FLG;
                WS_TAX_DATE1 = WS_DATE1;
                WS_TAX_DATE2 = WS_DATE2;
                WS_INT_3 = WS_TAX_INT;
                WS_RAT = TDCCDINT.RAT;
                if (WS_INT_3 != 0) {
                    R000_COMPUTE_TAX_INFO();
                    if (pgmRtn) return;
                }
            }
            WS_DINT_BAL = TDCCDINT.TXN_AMT;
            if (WS_LACCU_L > 0) {
                WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                if (TDCCDINT.EXP_DATE > TDCCDINT.TXN_DATE) {
                    WS_DINT_BAL = TDCCDINT.TXN_AMT;
                    WS_DATE2 = TDCCDINT.TXN_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                } else {
                    WS_DINT_BAL = TDCCDINT.TXN_AMT;
                    WS_DATE2 = TDCCDINT.EXP_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                }
                WS_LACCU_TAX += WS_LACCU_L;
                WS_RAT = WS_DD_RAT;
                CEP.TRC(SCCGWA, TDCCDINT.LOS_DNUM);
                CEP.TRC(SCCGWA, TDCCDINT.CD_AMT);
                WS_DINT_BAL = TDCCDINT.LOS_DNUM * TDCCDINT.CD_AMT;
                CEP.TRC(SCCGWA, WS_DINT_BAL);
                if (TDCCDINT.LOS_DNUM != 0) {
                    R000_COMPUTE_TAX_LZ();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_COMPUTE_TAX_LZ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        if (TDCCDINT.EDU_FLG == '0' 
            && DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
            WS_TAX_VAL = 0;
        } else {
            if (TDCCDINT.LAST_DEAL_DATE == WS_DATE1) {
                if (WS_ITAX_FLG == 'N') {
                    for (WS_I = 1; WS_I <= 12 
                        && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT <= WS_DATE1 
                        && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT != 0; WS_I += 1) {
                    }
                    CEP.TRC(SCCGWA, WS_I);
                    if (WS_I == 1) {
                        WS_TAX_VAL = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].TAX_VAL;
                    } else {
                        WS_TAX_VAL = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I - 1-1].TAX_VAL;
                    }
                    CEP.TRC(SCCGWA, WS_TAX_VAL);
                } else {
                    WS_TAX_VAL = 0;
                }
            }
        }
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        if (TDCCDINT.TXN_DATE < TDCCDINT.EXP_DATE 
                && TDCCDINT.OPT != '4' 
                && !(DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
                && TDCCDINT.EDU_FLG == '0' 
                && WS_MTHS >= 3)) {
            CEP.TRC(SCCGWA, "TST0001");
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
                && TDCCDINT.EDU_FLG == '0' 
                && WS_MTHS < 3) 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("025")) {
                TDCCDINT.RAT = WS_DD_RAT;
            }
            CEP.TRC(SCCGWA, TDCCDINT.RAT);
            WS_TAX_INT = ( WS_SACCU_TAX + WS_LACCU_TAX ) * TDCCDINT.RAT / 100 / WS_BASE_DAYS - WS_TAX_INT_ALL;
            WS_INT_TAX = WS_TAX_INT * WS_TAX_VAL;
        } else {
            CEP.TRC(SCCGWA, "TST0002");
            CEP.TRC(SCCGWA, TDCCDINT.RAT);
            CEP.TRC(SCCGWA, WS_YY_TACCU);
            CEP.TRC(SCCGWA, WS_MM_TACCU);
            CEP.TRC(SCCGWA, WS_DD_TACCU);
            WS_TAX_INT = WS_YY_TACCU * TDCCDINT.RAT / 100 + WS_MM_TACCU * TDCCDINT.RAT / 1200 + WS_DD_TACCU * TDCCDINT.RAT / 100 / WS_BASE_DAYS + WS_LACCU_TAX * WS_DD_RAT / 100 / WS_BASE_DAYS - WS_TAX_INT_ALL;
            CEP.TRC(SCCGWA, WS_TAX_INT);
            CEP.TRC(SCCGWA, WS_RAT);
            WS_INT_TAX = WS_TAX_INT * WS_TAX_VAL;
        }
        if (TDCCDINT.REG_FLG == '1') {
            CEP.TRC(SCCGWA, WS_RAT);
            R000_REG_INT_TAX_INF();
            if (pgmRtn) return;
        }
        if (TDCCDINT.REG_FLG == '1' 
            || TDCCDINT.REG_FLG == '2') {
            WS_INT_TAX_ALL = WS_INT_TAX_ALL + WS_INT_TAX;
        }
        WS_TAX_INT_ALL = WS_TAX_INT_ALL + WS_TAX_INT;
        CEP.TRC(SCCGWA, WS_TAX_INT);
        CEP.TRC(SCCGWA, WS_INT_TAX);
        CEP.TRC(SCCGWA, WS_INT_TAX_ALL);
        CEP.TRC(SCCGWA, WS_TAX_INT_ALL);
    }
    public void R000_GET_TD_RAT() throws IOException,SQLException,Exception {
        if (TDCCDINT.INT_SEL == '4' 
            && WS_TQ_FLG != 'Y') {
            if (TDCCDINT.INT_STSW.XC_INT_FLG == '1' 
                && TDCCDINT.AC.trim().length() > 0) {
                IBS.init(SCCGWA, TDRIREV);
                TDRIREV.KEY.ACO_AC = TDCCDINT.AC;
                TDRIREV.KEY.STR_DATE = WS_INDEX_DATE;
                T000_READ_TDTIREV();
                if (pgmRtn) return;
                TDCCDINT.RAT = TDRIREV.CON_RATE;
            } else {
            }
        } else {
            if (WS_FD_FLG == 'N') {
                R000_GET_TD_RAT_INFO_NFD();
                if (pgmRtn) return;
            } else {
                R000_GET_TD_RAT_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_TERM_PROC() throws IOException,SQLException,Exception {
        if (TDCCDINT.R_D_FLG == '1') {
            WS_D_I = 0;
            for (WS_D_I = 1; WS_DOCU[WS_D_I-1].WS_D_TERM.trim().length() != 0; WS_D_I += 1) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DOCU[WS_D_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DOCU_INFO[WS_D_I-1]);
            }
        } else {
            IBS.init(SCCGWA, TDRDOCU);
            WS_I = 0;
            TDRDOCU.KEY.DOCU_NO = TDCCDINT.DOCU_NO;
            T000_STARTBR_TDTDOCU();
            if (pgmRtn) return;
            T000_READNEXT_TDTDOCU();
            if (pgmRtn) return;
            while (WS_DOCU_FLG != 'N') {
                WS_I += 1;
                WS_DOCU_INFO[WS_I-1].WS_DOCU_NO = TDRDOCU.KEY.DOCU_NO;
                CEP.TRC(SCCGWA, TDRDOCU.KEY.DOCU_TERM);
                WS_DOCU_INFO[WS_I-1].WS_DOCU_TERM = TDRDOCU.KEY.DOCU_TERM;
                WS_TERM = TDRDOCU.KEY.DOCU_TERM;
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
                if (REDEFINES5.WS_TERM_TYP == 'Y') {
                    WS_MTHS = (short) (REDEFINES5.WS_TERM_MTHS * 12);
                    REDEFINES5.WS_TERM_TYP = 'M';
                    WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES5);
                    REDEFINES5.WS_TERM_MTHS = WS_MTHS;
                    WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES5);
                    WS_DOCU_INFO[WS_I-1].WS_DOCU_TERM_2 = WS_TERM;
                } else {
                    WS_DOCU_INFO[WS_I-1].WS_DOCU_TERM_2 = WS_DOCU_INFO[WS_I-1].WS_DOCU_TERM;
                }
                WS_DOCU_INFO[WS_I-1].WS_DOCU_IRAT_CD = TDRDOCU.IRAT_CD;
                WS_DOCU_INFO[WS_I-1].WS_REF_TERM = TDRDOCU.REF_TERM;
                WS_DOCU_INFO[WS_I-1].WS_TYP = TDRDOCU.TYP;
                WS_DOCU_INFO[WS_I-1].WS_FLG = TDRDOCU.FLG;
                WS_DOCU_INFO[WS_I-1].WS_SPRD_PCT = TDRDOCU.SPRD_PCT;
                WS_DOCU_INFO[WS_I-1].WS_DIS_SPRD = TDRDOCU.DIS_SPRD;
                WS_DOCU_INFO[WS_I-1].WS_CON_RATE = TDRDOCU.CON_RATE;
                if (WS_I > 1 
                    && WS_DOCU_INFO[WS_I-1].WS_DOCU_TERM_2.compareTo(WS_DOCU_INFO[WS_I - 1-1].WS_DOCU_TERM_2) > 0) {
                    R000_SORT_TERM();
                    if (pgmRtn) return;
                }
                T000_READNEXT_TDTDOCU();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTDOCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DOCU_INFO[1-1]);
            CEP.TRC(SCCGWA, WS_DOCU_INFO[2-1]);
            CEP.TRC(SCCGWA, WS_DOCU_INFO[3-1]);
            CEP.TRC(SCCGWA, WS_DOCU_INFO[4-1]);
            CEP.TRC(SCCGWA, WS_DOCU_INFO[5-1]);
            CEP.TRC(SCCGWA, WS_DOCU_INFO[6-1]);
        }
    }
    public void R000_SORT_TERM() throws IOException,SQLException,Exception {
        for (WS_J = WS_I; WS_J > 1; WS_J += -1) {
            if (WS_DOCU_INFO[WS_J-1].WS_DOCU_TERM_2.compareTo(WS_DOCU_INFO[WS_J - 1-1].WS_DOCU_TERM_2) > 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DOCU_INFO[WS_J - 1-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DOCU_INFO_TMP);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DOCU_INFO[WS_J-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DOCU_INFO[WS_J - 1-1]);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DOCU_INFO_TMP);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DOCU_INFO[WS_J-1]);
            }
        }
    }
    public void R000_GET_TERM_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_K);
        WS_IRAT_CD = WS_DOCU_INFO[WS_K-1].WS_DOCU_IRAT_CD;
        if (WS_IRAT_CD.trim().length() == 0) {
            WS_IRAT_CD = TDCCDINT.IRAT_CD;
        }
        if (WS_DOCU_INFO[WS_K-1].WS_TYP != ' ') {
            if (WS_DOCU_INFO[WS_K-1].WS_TYP == '0') {
                WS_INDEX_DATE = TDCCDINT.VAL_DATE;
            } else {
                WS_INDEX_DATE = TDCCDINT.TXN_DATE;
            }
        } else {
            if (TDCPIOD.INT_PRM.RAT_INX == '0') {
                WS_INDEX_DATE = TDCCDINT.VAL_DATE;
            } else {
                WS_INDEX_DATE = TDCCDINT.TXN_DATE;
            }
        }
        if (WS_TERM_INF_FLG == '3' 
            || WS_TERM_INF_FLG == '4') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_TERM_DATE1;
            if (REDEFINES10.WS_TERM2_TYP == 'D') {
                SCCCLDT.DAYS = WS_DAY_CNT;
            } else {
                SCCCLDT.MTHS = (short) WS_MTH_CNT;
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_ZNT_TMP_DT = SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, WS_ZNT_TMP_DT);
            if (WS_NEW_DATE1 != 0) {
                WS_DATE1 = WS_NEW_DATE1;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            } else {
                WS_DATE1 = WS_TERM_DATE1;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            }
            WS_DATE2 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            WS_NEW_DATE1 = WS_DATE2;
        }
        if (WS_DOCU_INFO[WS_K-1].WS_REF_TERM.trim().length() > 0) {
            WS_TERM = WS_DOCU_INFO[WS_K-1].WS_REF_TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
        } else {
            WS_TERM = WS_DOCU_INFO[WS_K-1].WS_DOCU_TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
        }
        CEP.TRC(SCCGWA, WS_TERM);
        CEP.TRC(SCCGWA, WS_DOCU_INFO[WS_K-1]);
        if (WS_DOCU_INFO[WS_K-1].WS_FLG == '0') {
            WS_FD_FLG = 'Y';
        } else {
            WS_FD_FLG = 'N';
        }
        R000_GET_TD_RAT();
        if (pgmRtn) return;
        WS_FD_FLG = ' ';
        WS_RAT = TDCCDINT.RAT;
        if (REDEFINES5.WS_TERM_TYP == 'D') {
            WS_RULE = '3';
        } else {
            WS_RULE = TDCCDINT.INT_STSW.RULE;
        }
        if (WS_FIRST_FLG == 'Y') {
            WS_ZNT_TERM = WS_TERM;
            WS_ZNT_RATE = WS_RAT;
            WS_ZNT_IRAT_CD = WS_IRAT_CD;
            WS_FIRST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, "---LIST---");
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        CEP.TRC(SCCGWA, WS_DATE1);
        CEP.TRC(SCCGWA, WS_DATE2);
        CEP.TRC(SCCGWA, WS_RULE);
        CEP.TRC(SCCGWA, WS_TERM);
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void R000_GET_TERM_DD_INT() throws IOException,SQLException,Exception {
        R000_GET_DD_RAT();
        if (pgmRtn) return;
        WS_RAT = WS_DD_RAT;
        WS_DATE1 = WS_TERM_DATE1;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_RULE = '3';
        if (WS_FIRST_FLG == 'Y') {
            WS_ZNT_TERM = WS_TERM;
            WS_ZNT_RATE = WS_RAT;
            WS_ZNT_IRAT_CD = WS_IRAT_CD;
            WS_FIRST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, "---LIST---");
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        CEP.TRC(SCCGWA, WS_DATE1);
        CEP.TRC(SCCGWA, WS_DATE2);
        CEP.TRC(SCCGWA, WS_RULE);
        CEP.TRC(SCCGWA, WS_TERM);
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void R000_GET_NEW_DATE_SUB_MTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ZNT_TMP_DT);
        if (SCCCLDT.DATE2 != TDCCDINT.TXN_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_ZNT_TMP_DT;
            SCCCLDT.DATE2 = TDCCDINT.TXN_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_TOT_DAYS = SCCCLDT.DAYS;
        } else {
            WS_TOT_DAYS = 0;
        }
    }
    public void R000_GET_NEW_DATE_SUB_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        if (WS_ZNT_TMP_DT > 0) {
            SCCCLDT.DATE1 = WS_ZNT_TMP_DT;
        } else {
            SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
        }
        SCCCLDT.DAYS = WS_DAY_CNT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        WS_ZNT_TMP_DT = SCCCLDT.DATE2;
    }
    public void R000_GET_TD_RAT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM);
        if (TDCCDINT.SPRD_PCT == 0 
            && TDCCDINT.SPRD_PNT == 0) {
        }
        CEP.TRC(SCCGWA, TDCCDINT.SPRD_PCT);
        if (!IBS.isNumeric(TDCCDINT.SPRD_PCT+"") 
            || !IBS.isNumeric(TDCCDINT.SPRD_PNT+"")) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_IR_DATA_BAD, TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_IRAT_CD);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.BASE_INFO.BASE_TYP = WS_IRAT_CD;
        if (TDCCDINT.SPRD_PCT != 0 
            || TDCCDINT.SPRD_PNT != 0) {
            if (BPCCINTI.BASE_INFO.BASE_TYP == null) BPCCINTI.BASE_INFO.BASE_TYP = "";
            JIBS_tmp_int = BPCCINTI.BASE_INFO.BASE_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCINTI.BASE_INFO.BASE_TYP += " ";
            if (BPCCINTI.BASE_INFO.BASE_TYP.substring(0, 1).equalsIgnoreCase("C") 
                || BPCCINTI.BASE_INFO.BASE_TYP.substring(0, 1).equalsIgnoreCase("D")) {
                if (BPCCINTI.BASE_INFO.BASE_TYP == null) BPCCINTI.BASE_INFO.BASE_TYP = "";
                JIBS_tmp_int = BPCCINTI.BASE_INFO.BASE_TYP.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPCCINTI.BASE_INFO.BASE_TYP += " ";
                BPCCINTI.BASE_INFO.BASE_TYP = "A" + BPCCINTI.BASE_INFO.BASE_TYP.substring(1);
            }
        }
        BPCCINTI.BASE_INFO.TENOR = WS_TERM;
        BPCCINTI.BASE_INFO.DT = WS_INDEX_DATE;
        BPCCINTI.BASE_INFO.CCY = TDCCDINT.CCY;
        BPCCINTI.BASE_INFO.BR = TDCCDINT.AC_BR;
        BPCCINTI.FUNC = 'I';
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        TDCCDINT.RAT = BPCCINTI.BASE_INFO.RATE;
        if (TDCCDINT.SPRD_PCT != 0) {
            TDCCDINT.RAT = TDCCDINT.RAT * ( 1 + TDCCDINT.SPRD_PCT / 100 );
        } else {
            if (TDCCDINT.SPRD_PNT != 0) {
                TDCCDINT.RAT = TDCCDINT.RAT + TDCCDINT.SPRD_PNT;
            }
        }
    }
    public void R000_GET_TD_RAT_INFO_NFD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM);
        CEP.TRC(SCCGWA, WS_IRAT_CD);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.BASE_INFO.BASE_TYP = WS_IRAT_CD;
        BPCCINTI.BASE_INFO.TENOR = WS_TERM;
        BPCCINTI.BASE_INFO.DT = WS_INDEX_DATE;
        BPCCINTI.BASE_INFO.CCY = TDCCDINT.CCY;
        BPCCINTI.BASE_INFO.BR = TDCCDINT.AC_BR;
        BPCCINTI.FUNC = 'I';
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        TDCCDINT.RAT = BPCCINTI.BASE_INFO.RATE;
    }
    public void R000_GET_RESULT_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = TDCCDINT.CCY;
        BPCRDAMT.AMT = WS_AMT_3;
        CEP.TRC(SCCGWA, WS_AMT_3);
        S00_CALL_BPZRDAMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        WS_AMT_3 = BPCRDAMT.RESULT_AMT;
    }
    public void R000_GET_DD_RAT() throws IOException,SQLException,Exception {
        if (TDCCDINT.DD_RAT == 0) {
            R000_GET_DD_RAT_INFO();
            if (pgmRtn) return;
        } else {
            WS_DD_RAT = TDCCDINT.DD_RAT;
        }
    }
    public void R000_GET_DD_RAT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        WS_CNT = 1;
        while (WS_CNT <= 12) {
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCCDINT.CCY)) {
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].OFRAT_CD.trim().length() > 0) {
                    BPCCINTI.BASE_INFO.BASE_TYP = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].OFRAT_CD;
                }
            }
            WS_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, TDCCDINT.CI_TYP);
        if (BPCCINTI.BASE_INFO.BASE_TYP.trim().length() == 0) {
            if (TDCCDINT.CI_TYP == '3') {
                BPCCINTI.BASE_INFO.BASE_TYP = K_HQ_BASE_TYP_TY;
            } else {
                BPCCINTI.BASE_INFO.BASE_TYP = K_HQ_BASE_TYP_DD;
            }
        }
        BPCCINTI.BASE_INFO.TENOR = K_HQ_TENOR;
        BPCCINTI.BASE_INFO.CCY = TDCCDINT.CCY;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = TDCCDINT.AC_BR;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        WS_DD_RAT = BPCCINTI.BASE_INFO.OWN_RATE;
        TDCCDINT.DD_RAT = BPCCINTI.BASE_INFO.OWN_RATE;
        CEP.TRC(SCCGWA, WS_DD_RAT);
    }
    public void R000_GET_TAX_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCITAXG);
        BPCITAXG.TAX_TYP = TDCCDINT.TAX_TYP;
        BPCITAXG.VAL_TYP = TDCCDINT.TAX_VAL_TYP;
        BPCITAXG.BR = TDCCDINT.AC_BR;
        BPCITAXG.BR = 999999999;
        if (BPCITAXG.BR == 0) {
            BPCITAXG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        BPCITAXG.CCY = TDCCDINT.CCY;
        BPCITAXG.ST_DT = TDCCDINT.VAL_DATE;
        BPCITAXG.EN_DT = TDCCDINT.TXN_DATE;
        BPCITAXG.RESIDENT = "A";
        S000_CALL_BPZITAXG();
        if (pgmRtn) return;
    }
    public void R000_GET_DEC_PNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = TDCCDINT.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        WS_DEC = BPCQCCY.DATA.DEC_MTH;
    }
    public void R000_GET_DEP_MTHS() throws IOException,SQLException,Exception {
        WS_VAL_DATE = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_VAL_DATE+"", REDEFINES102);
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        CEP.TRC(SCCGWA, REDEFINES107.WS_TXN_DATE_YY);
        CEP.TRC(SCCGWA, REDEFINES102.WS_VAL_DATE_YY);
        CEP.TRC(SCCGWA, REDEFINES107.WS_TXN_DATE_MM);
        CEP.TRC(SCCGWA, REDEFINES102.WS_VAL_DATE_MM);
        WS_MTHS = (short) (( REDEFINES107.WS_TXN_DATE_YY - REDEFINES102.WS_VAL_DATE_YY ) * 12 + REDEFINES107.WS_TXN_DATE_MM - REDEFINES102.WS_VAL_DATE_MM);
        CEP.TRC(SCCGWA, WS_MTHS);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_TXN_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (REDEFINES107.WS_TXN_DATE_DD < REDEFINES102.WS_VAL_DATE_DD 
            && REDEFINES107.WS_TXN_DATE_DD < SCCCKDT.MTH_DAYS) {
            WS_MTHS = (short) (WS_MTHS - 1);
        }
        CEP.TRC(SCCGWA, WS_MTHS);
        CEP.TRC(SCCGWA, REDEFINES107.WS_TXN_DATE_DD);
    }
    public void R000_GET_DEP_MTHS_2() throws IOException,SQLException,Exception {
        WS_VAL_DATE = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_VAL_DATE+"", REDEFINES102);
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        CEP.TRC(SCCGWA, REDEFINES107.WS_TXN_DATE_YY);
        CEP.TRC(SCCGWA, REDEFINES102.WS_VAL_DATE_YY);
        CEP.TRC(SCCGWA, REDEFINES107.WS_TXN_DATE_MM);
        CEP.TRC(SCCGWA, REDEFINES102.WS_VAL_DATE_MM);
        WS_MTHS = (short) (( REDEFINES107.WS_TXN_DATE_YY - REDEFINES102.WS_VAL_DATE_YY ) * 12 + REDEFINES107.WS_TXN_DATE_MM - REDEFINES102.WS_VAL_DATE_MM);
        CEP.TRC(SCCGWA, WS_MTHS);
        CEP.TRC(SCCGWA, WS_MTHS);
        CEP.TRC(SCCGWA, REDEFINES107.WS_TXN_DATE_DD);
    }
    public void R000_GET_DATE2_BY_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_XC_DATE1;
        if (REDEFINES5.WS_TERM_TYP == 'M' 
            || REDEFINES5.WS_TERM_TYP == 'Y') {
            if (REDEFINES5.WS_TERM_TYP == 'M') {
                SCCCLDT.MTHS = REDEFINES5.WS_TERM_MTHS;
            } else {
                SCCCLDT.MTHS = (short) (REDEFINES5.WS_TERM_MTHS * 12);
            }
        } else {
            SCCCLDT.DAYS = REDEFINES5.WS_TERM_MTHS;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_XC_DATE2 = SCCCLDT.DATE2;
    }
    public void R000_GET_NEW_EXP_INT() throws IOException,SQLException,Exception {
        if (WS_FIRST_VAL_DATE == WS_XC_DATE1 
            && TDCPIOD.INT_PRM.RAT_INX == '0') {
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")) 
                && TDCCDINT.PAYING_INT != 0 
                && TDCCDINT.INT_STSW.DISJOIN_FLG != '1') {
                WS_INT_3 = TDCCDINT.PAYING_INT;
                WS_TAX_DATE1 = TDCCDINT.VAL_DATE;
                WS_TAX_DATE2 = TDCCDINT.EXP_DATE;
                WS_DATE1 = TDCCDINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
                WS_DATE2 = TDCCDINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
                WS_RAT = TDCCDINT.RAT;
                if (WS_ITAX_FLG == 'N') {
                    R000_COMPUTE_TAX_INFO();
                    if (pgmRtn) return;
                    WS_AMT_3 = WS_INT_3;
                    WS_AMT_3 = WS_INT_3 - WS_INT_TAX_TIM;
                    WS_INT_TAX_TIM = 0;
                    R000_GET_RESULT_AMT();
                    if (pgmRtn) return;
                    WS_INT_5 = WS_AMT_3;
                } else {
                    if (TDCCDINT.REG_FLG == '1') {
                        WS_TAX_INT = WS_INT_3;
                        WS_INT_5 = WS_INT_3;
                        R000_REG_INT_TAX_INF();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (TDCCDINT.INT_STSW.DISJOIN_FLG == '1') {
                    WS_RESULT_FLG = ' ';
                    R000_GET_DISJOIN_INT();
                    if (pgmRtn) return;
                    WS_RESULT_FLG = 'Y';
                    WS_AMT_3 = WS_INT_3;
                    WS_AMT_4 = WS_INT_3;
                    TDCCDINT.RAT = WS_RAT;
                    R000_GET_RESULT_AMT();
                    if (pgmRtn) return;
                    WS_INT_3 = WS_AMT_3;
                    WS_AMT_3 = WS_AMT_4 - WS_INT_TAX_5;
                    R000_GET_RESULT_AMT();
                    if (pgmRtn) return;
                    WS_INT_5 = WS_AMT_3;
                } else {
                    R000_GET_INT();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (TDCPIOD.INT_PRM.RAT_INX != '0') {
                WS_INDEX_DATE = WS_XC_DATE2;
            } else {
                WS_INDEX_DATE = WS_XC_DATE1;
            }
            WS_TERM = TDCCDINT.TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES5);
            if (TDCCDINT.INT_STSW.XC_UP_FLG == '1') {
                TDCCDINT.SPRD_PNT = 0;
                TDCCDINT.SPRD_PCT = 0;
            }
            R000_GET_TD_RAT();
            if (pgmRtn) return;
            R000_GET_INT();
            if (pgmRtn) return;
        }
        WS_INT_TMP = WS_INT_TMP + WS_INT_3;
        if (TDCCDINT.INSTR_MTH == '3') {
            TDCCDINT.BAL = TDCCDINT.BAL + WS_INT_5;
            TDCCDINT.TXN_AMT = TDCCDINT.TXN_AMT + WS_INT_5;
            if (TDCCDINT.CCY.equalsIgnoreCase("156")) {
                WS_AMT_0 = (long) TDCCDINT.TXN_AMT;
                WS_AMT_2 = WS_AMT_0;
            } else {
                WS_AMT_2 = TDCCDINT.TXN_AMT;
            }
        }
    }
    public void R000_GET_INT() throws IOException,SQLException,Exception {
        WS_RAT = TDCCDINT.RAT;
        WS_EXC_RAT = TDCCDINT.RAT;
        WS_DATE1 = WS_XC_DATE1;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = WS_XC_DATE2;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        if (TDCCDINT.INT_STSW.RULE != ' ') {
            WS_RULE = TDCCDINT.INT_STSW.RULE;
        }
        if (TDCCDINT.CCY.equalsIgnoreCase("156") 
            && (DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("032"))) {
            if (WS_RULE == '1' 
                && WS_DATE1 >= K_05_DATE 
                && WS_DATE1 <= K_09_DATE) {
                WS_RULE = '3';
            } else {
                if (WS_RULE == '3' 
                    && WS_DATE1 > K_09_DATE) {
                    WS_RULE = '1';
                }
            }
        }
        R000_COMPUTE_INT_BY_RULE();
        if (pgmRtn) return;
        WS_AMT_3 = WS_INT_3;
        R000_GET_RESULT_AMT();
        if (pgmRtn) return;
        WS_INT_3 = WS_AMT_3;
    }
    public void R000_CAL_VE0_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDS);
        TDCCDS.BEG_DATE = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = TDCCDINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
        S000_CALL_TDZCDS();
        if (pgmRtn) return;
        TDCCDINT.DAYS = TDCCDS.DAYS;
    }
    public void R000_CAL_INT_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_TMP_VAL_DATE;
        SCCCLDT.DATE2 = WS_TMP_EXP_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_VT0_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDS);
        TDCCDS.BEG_DATE = TDCCDINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
        S000_CALL_TDZCDS();
        if (pgmRtn) return;
        TDCCDINT.DAYS = TDCCDS.DAYS;
    }
    public void R000_CAL_VT1_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCCDINT.VAL_DATE;
        SCCCLDT.DATE2 = TDCCDINT.TXN_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_IREV_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_TMP_VAL_DATE;
        SCCCLDT.DATE2 = WS_TMP_EXP_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_TE0_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDS);
        TDCCDS.BEG_DATE = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = TDCCDINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
        S000_CALL_TDZCDS();
        if (pgmRtn) return;
        TDCCDINT.DAYS = TDCCDS.DAYS;
    }
    public void R000_CAL_TE1_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCCDINT.TXN_DATE;
        SCCCLDT.DATE2 = TDCCDINT.EXP_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_LT0_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDS);
        TDCCDS.BEG_DATE = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = TDCCDINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
        S000_CALL_TDZCDS();
        if (pgmRtn) return;
        TDCCDINT.DAYS = TDCCDS.DAYS;
    }
    public void R000_CAL_LT1_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCCDINT.LAST_DEAL_DATE;
        SCCCLDT.DATE2 = TDCCDINT.TXN_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_LE0_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDS);
        TDCCDS.BEG_DATE = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = TDCCDINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
        S000_CALL_TDZCDS();
        if (pgmRtn) return;
        TDCCDINT.DAYS = TDCCDS.DAYS;
    }
    public void R000_CAL_LE1_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCCDINT.LAST_DEAL_DATE;
        SCCCLDT.DATE2 = TDCCDINT.EXP_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_ET1_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCCDINT.EXP_DATE;
        SCCCLDT.DATE2 = TDCCDINT.TXN_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_CAL_CLDT_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DATE2 = WS_DATE2;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCDINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_GET_TMP_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_TMP_DATE1;
        SCCCLDT.DATE2 = WS_TMP_DATE2;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_TMP_DAYS = SCCCLDT.DAYS;
    }
    public void R000_GET_YMD() throws IOException,SQLException,Exception {
        if (WS_DATE2 < WS_DATE1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT, TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_YY = 0;
        WS_MM = (short) (( REDEFINES118.WS_DATE2_YY - REDEFINES113.WS_DATE1_YY ) * 12 + REDEFINES118.WS_DATE2_MM - REDEFINES113.WS_DATE1_MM);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE2;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (REDEFINES118.WS_DATE2_DD < REDEFINES113.WS_DATE1_DD 
            && REDEFINES118.WS_DATE2_DD < SCCCKDT.MTH_DAYS) {
            WS_MM = (short) (WS_MM - 1);
        }
        if (WS_MM != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_DATE1;
            SCCCLDT.MTHS = WS_MM;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE3 = SCCCLDT.DATE2;
        } else {
            WS_DATE3 = WS_DATE1;
        }
        WS_DD = 0;
        if (WS_DATE3 != WS_DATE2) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_DATE3;
            SCCCLDT.DATE2 = WS_DATE2;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DD = SCCCLDT.DAYS;
        }
    }
    public void R000_GET_YD() throws IOException,SQLException,Exception {
        if (WS_DATE2 < WS_DATE1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT, TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_MM = (short) (( REDEFINES118.WS_DATE2_YY - REDEFINES113.WS_DATE1_YY ) * 12 + REDEFINES118.WS_DATE2_MM - REDEFINES113.WS_DATE1_MM);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE2;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (REDEFINES118.WS_DATE2_DD < REDEFINES113.WS_DATE1_DD 
            && REDEFINES118.WS_DATE2_DD < SCCCKDT.MTH_DAYS) {
            WS_MM = (short) (WS_MM - 1);
        }
        WS_YY = (short) (WS_MM / 12);
        WS_MM = (short) (WS_YY * 12);
        if (WS_MM != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_DATE1;
            SCCCLDT.MTHS = WS_MM;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE1 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DATE2 = WS_DATE2;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DD = SCCCLDT.DAYS;
    }
    public void R000_COMPUTE_INT_BY_RULE() throws IOException,SQLException,Exception {
        WS_INT_TAX_TIM = 0;
        CEP.TRC(SCCGWA, WS_RULE);
        if (WS_RULE == '1') {
            R000_GET_YMD();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_2 * WS_YY * WS_RAT / 100 + WS_AMT_2 * WS_MM * WS_RAT / 1200 + WS_AMT_2 * WS_DD * WS_RAT / 100 / WS_BASE_DAYS;
        } else if (WS_RULE == '2') {
            R000_GET_YD();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_2 * WS_YY * WS_RAT / 100 + WS_AMT_2 * WS_DD * WS_RAT / 100 / WS_BASE_DAYS;
        } else if (WS_RULE == '3') {
            R000_CAL_CLDT_DAYS();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_2 * WS_RAT / 100 * TDCCDINT.DAYS / WS_BASE_DAYS;
            CEP.TRC(SCCGWA, TDCCDINT.DAYS);
        } else if (WS_RULE == '4') {
            WS_INT_3 = WS_ACCU * WS_RAT / 100 / WS_BASE_DAYS;
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_COUNT_RULE_ERROR, TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_RESULT_FLG == 'Y') {
            WS_AMT_3 = WS_INT_3;
            WS_AMT_4 = WS_INT_3;
            R000_GET_RESULT_AMT();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_3;
        }
        if ((TDCCDINT.REG_FLG == '1' 
            || TDCCDINT.REG_FLG == '2') 
            && WS_INT_3 != 0) {
            if (WS_ITAX_FLG == 'N') {
                WS_TAX_DATE1 = WS_DATE1;
                WS_TAX_DATE2 = WS_DATE2;
                R000_COMPUTE_TAX_INFO();
                if (pgmRtn) return;
            } else {
                if (TDCCDINT.REG_FLG == '1') {
                    WS_TAX_INT = WS_INT_3;
                    R000_REG_INT_TAX_INF();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_RESULT_FLG == 'Y') {
            WS_AMT_3 = WS_AMT_4 - WS_INT_TAX_TIM;
            WS_INT_TAX_TIM = 0;
            R000_GET_RESULT_AMT();
            if (pgmRtn) return;
            WS_INT_5 = WS_AMT_3;
        }
    }
    public void R000_COMPUTE_TAX_INFO() throws IOException,SQLException,Exception {
        WS_INT_TAX_TIM = 0;
        WS_INT_ADD = 0;
        WS_DATE1 = WS_TAX_DATE1;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = WS_TAX_DATE2;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        R000_CAL_CLDT_DAYS();
        if (pgmRtn) return;
        WS_ALL_DAYS = TDCCDINT.DAYS;
        for (WS_I = 1; BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT <= WS_TAX_DATE1 
            && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT != 0; WS_I += 1) {
        }
        if (WS_I == 1) {
            WS_TAX_VAL = 0;
            WS_DATE1 = WS_TAX_DATE1;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        } else {
            WS_TAX_VAL = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I - 1-1].TAX_VAL;
            if (BPCITAXG.OUTPUT.TAXR_GROUP[WS_I - 1-1].EFF_DT < WS_TAX_DATE1) {
                WS_DATE1 = WS_TAX_DATE1;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            } else {
                WS_DATE1 = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I - 1-1].EFF_DT;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            }
        }
        if (BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT < WS_TAX_DATE2 
            && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT != 0) {
            WS_DATE2 = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        } else {
            WS_DATE2 = WS_TAX_DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        }
        R000_CAL_CLDT_DAYS();
        if (pgmRtn) return;
        WS_DAYS = TDCCDINT.DAYS;
        WS_TAX_INT = WS_INT_3 * WS_DAYS / WS_ALL_DAYS;
        WS_INT_ADD += WS_TAX_INT;
        WS_INT_TAX = WS_TAX_INT * WS_TAX_VAL;
        WS_INT_TAX_ALL = WS_INT_TAX_ALL + WS_INT_TAX;
        WS_INT_TAX_TIM = WS_INT_TAX_TIM + WS_INT_TAX;
        if (TDCCDINT.REG_FLG == '1') {
            R000_REG_INT_TAX_INF();
            if (pgmRtn) return;
        }
        while (BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT < WS_TAX_DATE2 
            && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT != 0) {
            WS_TAX_VAL = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].TAX_VAL;
            if (BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT < WS_TAX_DATE1) {
                WS_DATE1 = WS_TAX_DATE1;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            } else {
                WS_DATE1 = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
            }
            if (BPCITAXG.OUTPUT.TAXR_GROUP[WS_I + 1-1].EFF_DT < WS_TAX_DATE2 
                && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I + 1-1].EFF_DT != 0) {
                WS_DATE2 = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I + 1-1].EFF_DT;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            } else {
                WS_DATE2 = WS_TAX_DATE2;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
            }
            R000_CAL_CLDT_DAYS();
            if (pgmRtn) return;
            WS_DAYS = TDCCDINT.DAYS;
            if (BPCITAXG.OUTPUT.TAXR_GROUP[WS_I + 1-1].EFF_DT >= WS_TAX_DATE2 
                || BPCITAXG.OUTPUT.TAXR_GROUP[WS_I + 1-1].EFF_DT == 0) {
                WS_TAX_INT = WS_INT_3 - WS_INT_ADD;
            } else {
                WS_TAX_INT = WS_INT_3 * WS_DAYS / WS_ALL_DAYS;
                WS_INT_ADD += WS_TAX_INT;
            }
            WS_INT_TAX = WS_TAX_INT * WS_TAX_VAL;
            WS_INT_TAX_ALL = WS_INT_TAX_ALL + WS_INT_TAX;
            WS_INT_TAX_TIM = WS_INT_TAX_TIM + WS_INT_TAX;
            if (TDCCDINT.REG_FLG == '1') {
                if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
                    WS_DINT_BAL = WS_AMT_2;
                }
                R000_REG_INT_TAX_INF();
                if (pgmRtn) return;
            }
            WS_I += 1;
        }
    }
    public void R000_GET_TAX_RAT() throws IOException,SQLException,Exception {
        for (WS_I = 1; BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT <= WS_TAX_DATE1 
            && BPCITAXG.OUTPUT.TAXR_GROUP[WS_I-1].EFF_DT != 0; WS_I += 1) {
        }
        if (WS_I == 1) {
            WS_TAX_VAL = 0;
        } else {
            WS_TAX_VAL = BPCITAXG.OUTPUT.TAXR_GROUP[WS_I - 1-1].TAX_VAL;
        }
    }
    public void R000_REG_INT_TAX_INF() throws IOException,SQLException,Exception {
        if (WS_SEQ == 0) {
            IBS.init(SCCGWA, TDRDINT);
            TDRDINT.KEY.ACO_AC = TDCCDINT.AC;
            TDRDINT.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRDINT.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            T000_READ_TDTDINT_FIRST();
            if (pgmRtn) return;
            if (WS_DINT_FLG == 'N') {
                WS_SEQ = 0;
            } else {
                WS_SEQ = (short) TDRDINT.KEY.SEQ;
            }
        }
        IBS.init(SCCGWA, TDRDINT);
        TDRDINT.KEY.ACO_AC = TDCCDINT.AC;
        TDRDINT.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDINT.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_SEQ += 1;
        TDRDINT.KEY.SEQ = WS_SEQ;
        TDRDINT.BR = TDCCDINT.AC_BR;
        TDRDINT.CCY = TDCCDINT.CCY;
        TDRDINT.RAT_INT = WS_RAT;
        TDRDINT.TAX_RAT = WS_TAX_VAL;
        TDRDINT.BAL = TDCCDINT.TXN_AMT;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
            TDRDINT.BAL = WS_DINT_BAL;
            CEP.TRC(SCCGWA, "TLZ-TEST");
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && WS_TTZ_DINT_BAL != 0) {
            TDRDINT.BAL = WS_TTZ_DINT_BAL;
        }
        if (TDCCDINT.SPE_FLG == '6') {
            TDRDINT.BAL = WS_AMT_2;
        }
        CEP.TRC(SCCGWA, "TESTA1");
        TDRDINT.STR_DATE = WS_DATE1;
        TDRDINT.END_DATE = WS_DATE2;
        TDRDINT.AMT = WS_TAX_INT;
        TDRDINT.TAX_AMT = WS_INT_TAX;
        TDRDINT.VAL_DATE = TDCCDINT.VAL_DATE;
        TDRDINT.EXP_DATE = TDCCDINT.EXP_DATE;
        if (TDCCDINT.OPT == '3') {
            TDRDINT.INT_TYPE = '3';
        } else if (TDCCDINT.OPT == '0') {
            if (TDCCDINT.TXN_AMT < TDCCDINT.BAL) {
                TDRDINT.INT_TYPE = '2';
            } else {
                if ((TDCCDINT.XC_FLG == 'Y' 
                    || TDCCDINT.XC_FLG == 'C')) {
                    TDRDINT.INT_TYPE = '5';
                } else {
                    TDRDINT.INT_TYPE = '4';
                }
            }
        } else if (TDCCDINT.OPT == '4') {
            TDRDINT.INT_TYPE = '1';
        } else {
            TDRDINT.INT_TYPE = '2';
        }
        if (WS_TAX_FLG == 'Y') {
            TDRDINT.INT_TYPE = '7';
        }
        WS_TAX_FLG = ' ';
        TDRDINT.STATUS = 'N';
        TDRDINT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRDINT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDINT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_INSERT_TDTDINT();
        if (pgmRtn) return;
    }
    public void R000_WRT_DRW_INT() throws IOException,SQLException,Exception {
        if (WS_SEQ == 0) {
            IBS.init(SCCGWA, TDRDINT);
            TDRDINT.KEY.ACO_AC = TDCCDINT.AC;
            TDRDINT.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRDINT.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            T000_READ_TDTDINT_FIRST();
            if (pgmRtn) return;
            if (WS_DINT_FLG == 'N') {
                WS_SEQ = 0;
            } else {
                WS_SEQ = (short) TDRDINT.KEY.SEQ;
            }
        }
        IBS.init(SCCGWA, TDRDINT);
        TDRDINT.KEY.ACO_AC = TDCCDINT.AC;
        TDRDINT.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDINT.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_SEQ += 1;
        TDRDINT.KEY.SEQ = WS_SEQ;
        TDRDINT.BR = TDCCDINT.AC_BR;
        TDRDINT.CCY = TDCCDINT.CCY;
        TDRDINT.RAT_INT = 0;
        TDRDINT.TAX_RAT = 0;
        TDRDINT.BAL = TDCCDINT.TXN_AMT;
        CEP.TRC(SCCGWA, "TESTA2");
        TDRDINT.STR_DATE = TDCCDINT.VAL_DATE;
        TDRDINT.END_DATE = TDCCDINT.TXN_DATE;
        TDRDINT.AMT = -1 * TDCCDINT.TRANS_INT;
        TDRDINT.TAX_AMT = 0;
        TDRDINT.VAL_DATE = TDCCDINT.VAL_DATE;
        TDRDINT.EXP_DATE = TDCCDINT.EXP_DATE;
        TDRDINT.INT_TYPE = '6';
        TDRDINT.STATUS = 'N';
        TDRDINT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRDINT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDINT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_INSERT_TDTDINT();
        if (pgmRtn) return;
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BUY_CCY = "840";
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = TDCCDINT.CCY;
        S000_CALL_BPZSFX();
        if (pgmRtn) return;
        WS_SELL_AMT = BPCFX.SELL_AMT;
    }
    public void R000_GET_AINT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRAINT);
        TDRAINT.KEY.ACO_AC = TDCCDINT.AC;
        T000_READ_TDTAINT();
        if (pgmRtn) return;
    }
    public void R000_REG_DINT_TCQ() throws IOException,SQLException,Exception {
        WS_RAT = TDCCDINT.RAT;
        WS_TAX_VAL = 0;
        WS_DATE1 = TDCCDINT.LAST_DEAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES113);
        WS_DATE2 = TDCCDINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES118);
        WS_TAX_INT = WS_INT_3;
        R000_REG_INT_TAX_INF();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZCDS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-DAYS", TDCCDS);
        if (TDCCDS.RC_MSG.RC != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.RC_MSG);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZOCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-WORK-DAY", BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZITAXG() throws IOException,SQLException,Exception {
        WS_ITAX_FLG = 'Y';
        IBS.CALLCPN(SCCGWA, "BP-S-TAXR-GROUP-INQ", BPCITAXG);
        if (BPCITAXG.RETURN_INFO == 'F') {
            WS_ITAX_FLG = 'N';
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZUIRUL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-IRULP", TDCUIRUL);
        if (TDCUIRUL.RC_MSG.RC != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCUIRUL.RC_MSG);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCUIRUL.RTN_MATCH == 'Y') {
            if (TDCUIRUL.IRAT_CD.trim().length() > 0) {
                TDCCDINT.IRAT_CD = TDCUIRUL.IRAT_CD;
            }
            if (TDCUIRUL.SPRD_PNT != 0) {
                TDCCDINT.SPRD_PNT = TDCUIRUL.SPRD_PNT;
            }
            if (TDCUIRUL.SPRD_PCT != 0) {
                TDCCDINT.SPRD_PCT = TDCUIRUL.SPRD_PCT;
            }
        }
    }
    public void T000_STARTBR_CALL_TABLE() throws IOException,SQLException,Exception {
        TDTCALL_BR.rp = new DBParm();
        TDTCALL_BR.rp.TableName = "TDTCALL";
        TDTCALL_BR.rp.eqWhere = "ACO_AC";
        TDTCALL_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, TDRCALL, TDTCALL_BR);
    }
    public void T000_READNEXT_CALL_TABLE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRCALL, this, TDTCALL_BR);
    }
    public void T000_REWRITE_CALL_TABLE() throws IOException,SQLException,Exception {
        TDRCALL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCALL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCALL.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        IBS.REWRITE(SCCGWA, TDRCALL, TDTCALL_RD);
    }
    public void T000_INSERT_TDTDINT() throws IOException,SQLException,Exception {
        if (TDCCDINT.BUSI_CTLW == null) TDCCDINT.BUSI_CTLW = "";
        JIBS_tmp_int = TDCCDINT.BUSI_CTLW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCCDINT.BUSI_CTLW += " ";
        if (TDCCDINT.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            TDRDINT.STATUS = 'T';
        } else {
            TDRDINT.AMT += TDCCDINT.HOLD_INT_AMT;
            TDRDINT.TAX_AMT += TDCCDINT.HOLD_TAX_AMT;
        }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (!TDCCDINT.PRDAC_CD.equalsIgnoreCase("035")) {
            TDTDINT_RD = new DBParm();
            TDTDINT_RD.TableName = "TDTDINT";
            IBS.WRITE(SCCGWA, TDRDINT, TDTDINT_RD);
        }
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        TDTDINT_RD = new DBParm();
        TDTDINT_RD.TableName = "TDTDINT";
        IBS.WRITE(SCCGWA, TDRDINT, TDTDINT_RD);
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_TDTDINT_FIRST() throws IOException,SQLException,Exception {
        TDTDINT_RD = new DBParm();
        TDTDINT_RD.TableName = "TDTDINT";
        TDTDINT_RD.where = "ACO_AC = :TDRDINT.KEY.ACO_AC "
            + "AND AC_DATE = :TDRDINT.KEY.AC_DATE "
            + "AND JRNNO = :TDRDINT.KEY.JRNNO";
        TDTDINT_RD.fst = true;
        TDTDINT_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, TDRDINT, this, TDTDINT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DINT_FLG = 'Y';
        } else {
            WS_DINT_FLG = 'N';
        }
    }
    public void T000_ENDBR_CALL_TABLE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTCALL_BR);
    }
    public void T000_READ_TDTAINT() throws IOException,SQLException,Exception {
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        IBS.READ(SCCGWA, TDRAINT, TDTAINT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            if (TDCCDINT.SPE_FLG == '5') {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_NEED_PRV_RAT, TDCCDINT.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_FOUND_FLG = ' ';
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXY_COMPACT_NOT_F, TDCCDINT.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READNEXT_TDTIREV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRIREV, this, TDTIREV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TMP_CDINT_RAT = TDRIREV.CON_RATE;
            if (TDRIREV.END_DATE > TDCCDINT.TXN_DATE) {
                WS_TMP_EXP_DATE = TDCCDINT.TXN_DATE;
            } else {
                WS_TMP_EXP_DATE = TDRIREV.END_DATE;
            }
            if (TDCCDINT.EXP_DATE < WS_TMP_EXP_DATE) {
                WS_TMP_EXP_DATE = TDCCDINT.EXP_DATE;
            }
            CEP.TRC(SCCGWA, TDCCDINT.LAST_DEAL_DATE);
            CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
            CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
            CEP.TRC(SCCGWA, "DONGGONG");
            if (TDRIREV.KEY.STR_DATE <= TDCCDINT.TXN_DATE 
                && TDCCDINT.TXN_DATE != 0) {
                if (TDRIREV.KEY.STR_DATE < TDCCDINT.VAL_DATE) {
                    WS_TMP_VAL_DATE = TDCCDINT.VAL_DATE;
                } else {
                    WS_TMP_VAL_DATE = TDRIREV.KEY.STR_DATE;
                }
                WS_IREV_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "LISHI");
                WS_IREV_FLG = 'N';
            }
        } else {
            WS_IREV_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_TMP_VAL_DATE);
        CEP.TRC(SCCGWA, WS_TMP_EXP_DATE);
        CEP.TRC(SCCGWA, WS_TMP_CDINT_RAT);
    }
    public void T000_STARTBR_TDTIREV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDCCDINT.AC;
        WS_VAL = TDCCDINT.VAL_DATE;
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_VAL);
        WS_IREV_FLG = 'N';
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.eqWhere = "ACO_AC";
        TDTIREV_BR.rp.where = "( STR_DATE >= :WS_VAL "
            + "OR END_DATE > :WS_VAL )";
        TDTIREV_BR.rp.order = "ACO_AC , STR_DATE";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
        WS_START_BR_FLG = 'Y';
    }
    public void T000_ENDBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTIREV_BR);
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE > :TDRIREV.KEY.STR_DATE";
        TDTIREV_RD.fst = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_IREV_NOFND, TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_INTC() throws IOException,SQLException,Exception {
        TDTINTC_BR.rp = new DBParm();
        TDTINTC_BR.rp.TableName = "TDTINTC";
        TDTINTC_BR.rp.eqWhere = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRINTC, TDTINTC_BR);
    }
    public void T000_READNEXT_INTC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRINTC, this, TDTINTC_BR);
    }
    public void T000_ENDBR_INTC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTINTC_BR);
    }
    public void T000_STARTBR_TDTDOCU() throws IOException,SQLException,Exception {
        TDTDOCU_BR.rp = new DBParm();
        TDTDOCU_BR.rp.TableName = "TDTDOCU";
        TDTDOCU_BR.rp.eqWhere = "DOCU_NO";
        IBS.STARTBR(SCCGWA, TDRDOCU, TDTDOCU_BR);
    }
    public void T000_READNEXT_TDTDOCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRDOCU, this, TDTDOCU_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DOCU_FLG = 'Y';
        } else {
            WS_DOCU_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTDOCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTDOCU_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCCDINT.RC_MSG.RC != 0) {
            CEP.TRC(SCCGWA, "TDCCDINT=");
            CEP.TRC(SCCGWA, TDCCDINT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
