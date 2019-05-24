package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DP.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZIINT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    DBParm TDTPHIS_RD;
    DBParm TDTCDI_RD;
    DBParm TDTIREV_RD;
    DBParm TDTINST_RD;
    boolean pgmRtn = false;
    String K_IRUL_TYP = "TIRUL";
    String K_PIOD_TYP = "PIODR";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_HQ_BASE_TYP = "A01";
    String K_TY_BASE_TYP = "F01";
    String K_HQ_TENOR = "D000";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_ERR_INF = " ";
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CODE = " ";
    String WS_PRDAC_CD = " ";
    char WS_CI_TYP = ' ';
    String WS_OPEN_BK = " ";
    double WS_TRANS_INT = 0;
    char WS_CI_TYP_T = ' ';
    double WS_PAYING_INT = 0;
    double WS_TAXING_INT = 0;
    double WS_PAYING_TAX = 0;
    double WS_TMP_AMT = 0;
    String WS_TERM = " ";
    TDZIINT_REDEFINES17 REDEFINES17 = new TDZIINT_REDEFINES17();
    int WS_VAL_DATE = 0;
    TDZIINT_REDEFINES21 REDEFINES21 = new TDZIINT_REDEFINES21();
    int WS_TXN_DATE = 0;
    TDZIINT_REDEFINES26 REDEFINES26 = new TDZIINT_REDEFINES26();
    short WS_MTHS = 0;
    int WS_TERM_DATE = 0;
    char WS_MTZ_FLG = ' ';
    short WS_TERM_DAYS = 0;
    short WS_CD_NUM = 0;
    double WS_NACCU = 0;
    double WS_INT = 0;
    int WS_TEMP_DATE = 0;
    double WS_DD_RAT = 0;
    String WS_CCY = " ";
    String WS_ACTI_NO = " ";
    String WS_DOCU_NO = " ";
    char WS_CCY_FOUND = ' ';
    short WS_W = 0;
    short WS_X = 0;
    String WS_RUL_CDC = " ";
    char WS_ITZ_FLG = ' ';
    char WS_GET_FLG = ' ';
    String WS_AC_NO = " ";
    char WS_CALD_FLG = ' ';
    int WS_DATE3 = 0;
    TDZIINT_REDEFINES53 REDEFINES53 = new TDZIINT_REDEFINES53();
    short WS_COUNT_NUM = 0;
    int WS_DATE1 = 0;
    TDZIINT_REDEFINES59 REDEFINES59 = new TDZIINT_REDEFINES59();
    short WS_MM = 0;
    int WS_DD = 0;
    String WS_IRAT_CD = " ";
    String WS_NE_IRAT_CD = " ";
    String WS_PR_IRAT_CD = " ";
    String WS_MIN_CCYC = " ";
    double WS_MIN_AMTC = 0;
    double WS_MIN_AMTC_USD = 0;
    double WS_BUY_AMT = 0;
    double WS_SELL_AMT = 0;
    String WS_FORMAL_TERM = " ";
    String WS_TERM_DESC = " ";
    TDZIINT_REDEFINES75 REDEFINES75 = new TDZIINT_REDEFINES75();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCPIOD TDCPIOD = new TDCPIOD();
    TDCCDINT TDCCDINT = new TDCCDINT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICACCU CICACCU = new CICACCU();
    TDCOINT TDCOINT = new TDCOINT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCIRULP TDCIRULP = new TDCIRULP();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    TDCSAVRT TDCSAVRT = new TDCSAVRT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPRMR BPCPRMR = new BPCPRMR();
    TDCCEINT TDCCEINT = new TDCCEINT();
    CICCUST CICCUST = new CICCUST();
    TDRPHIS TDRPHIS = new TDRPHIS();
    BPCEX BPCEX = new BPCEX();
    CICQACAC CICQACAC = new CICQACAC();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCDI TDRCDI = new TDRCDI();
    TDRINST TDRINST = new TDRINST();
    TDRIREV TDRIREV = new TDRIREV();
    SCCGWA SCCGWA;
    TDCIINT TDCIINT;
    public void MP(SCCGWA SCCGWA, TDCIINT TDCIINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCIINT = TDCIINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZIINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCIINT.FUNC);
        CEP.TRC(SCCGWA, TDCIINT.AC);
        CEP.TRC(SCCGWA, TDCIINT.AC_SEQ);
        CEP.TRC(SCCGWA, TDCIINT.PROD_CD);
        CEP.TRC(SCCGWA, TDCIINT.CCY);
        CEP.TRC(SCCGWA, TDCIINT.TERM);
        CEP.TRC(SCCGWA, TDCIINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCIINT.BAL);
        CEP.TRC(SCCGWA, TDCIINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCIINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCIINT.CALL_NO);
        CEP.TRC(SCCGWA, TDCIINT.NOR_NUM);
        CEP.TRC(SCCGWA, TDCIINT.LOS_NUM);
        CEP.TRC(SCCGWA, TDCIINT.LOS_DNUM);
        CEP.TRC(SCCGWA, TDCIINT.LAST_DEAL_DATE);
        CEP.TRC(SCCGWA, TDCIINT.EDU_FLG);
        CEP.TRC(SCCGWA, TDCIINT.CD_PERD);
        CEP.TRC(SCCGWA, TDCIINT.CD_AMT);
        CEP.TRC(SCCGWA, TDCIINT.RAT);
        CEP.TRC(SCCGWA, TDCIINT.CAL_TYPE);
        if (TDCIINT.FUNC == '5') {
            TDCIINT.FUNC = '2';
            WS_MTZ_FLG = 'Y';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCIINT.FUNC);
        CEP.TRC(SCCGWA, TDCIINT.PROD_CD);
        CEP.TRC(SCCGWA, TDCIINT.CCY);
        CEP.TRC(SCCGWA, TDCIINT.TERM);
        CEP.TRC(SCCGWA, TDCIINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCIINT.BAL);
        CEP.TRC(SCCGWA, TDCIINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCIINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCIINT.CALL_NO);
        CEP.TRC(SCCGWA, TDCIINT.NOR_NUM);
        CEP.TRC(SCCGWA, TDCIINT.LOS_DNUM);
        CEP.TRC(SCCGWA, TDCIINT.LAST_DEAL_DATE);
        CEP.TRC(SCCGWA, TDCIINT.EDU_FLG);
        CEP.TRC(SCCGWA, TDCIINT.CD_PERD);
        CEP.TRC(SCCGWA, TDCIINT.CD_AMT);
        CEP.TRC(SCCGWA, TDCIINT.RAT);
        CEP.TRC(SCCGWA, TDCIINT.AC);
        CEP.TRC(SCCGWA, WS_TRANS_INT);
        B010_CHECK_INPUT_INFO();
        if (pgmRtn) return;
        if (TDCIINT.FUNC == '1') {
            B010_GET_PROD_INFO();
            if (pgmRtn) return;
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
                B030_CAL_LZ_INT();
                if (pgmRtn) return;
            } else {
                B031_CAL_PRD_INT();
                if (pgmRtn) return;
            }
        } else if ((TDCIINT.FUNC == '2' 
                || TDCIINT.FUNC == '3')) {
            B020_GET_AC_INFO();
            if (pgmRtn) return;
            if (TDCIINT.FUNC == '2') {
                B030_TRANSFER_INFO();
                if (pgmRtn) return;
            } else {
                B030_CAL_PAYING_INT();
                if (pgmRtn) return;
            }
        } else if (TDCIINT.FUNC == '4') {
            B020_GET_AC_INFO();
            if (pgmRtn) return;
            B030_CAL_PAYING_INT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            WS_ERR_INF = "IINT FUNC ERR";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_OUTPUT_SUB_AC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCIINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        WS_AC_NO = TDCIINT.AC;
        if (TDCIINT.TXN_DATE == 0) {
            TDCIINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCIINT.EXP_DATE);
        if (TDCIINT.EXP_DATE == 0 
            && TDCIINT.FUNC == '1' 
            && TDCIINT.TERM.trim().length() > 0) {
            R000_GET_EXP_DATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCIINT.EXP_DATE);
        if (TDCIINT.FUNC == '1' 
            && TDCIINT.VAL_DATE == 0 
            && TDCIINT.TXN_DATE != 0) {
            TDCIINT.VAL_DATE = TDCIINT.TXN_DATE;
        }
        CEP.TRC(SCCGWA, TDCIINT.VAL_DATE);
        if (TDCIINT.VAL_DATE > TDCIINT.TXN_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT;
            WS_ERR_INF = " ";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDCIINT.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDCIINT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        R000_GET_PRODINF();
        if (pgmRtn) return;
        WS_CCY_FOUND = 'N';
        for (WS_X = 1; WS_X <= 20 
            && WS_CCY_FOUND != 'Y'; WS_X += 1) {
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].MIN_CCYC.equalsIgnoreCase(TDCIINT.CCY)) {
                WS_MIN_AMTC = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].MIN_AMTC;
                WS_CALD_FLG = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].INT_RUL;
                WS_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].RAT_CD;
                WS_FORMAL_TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].TERM;
                WS_I = WS_X;
                WS_CCY_FOUND = 'Y';
            }
        }
        WS_ITZ_FLG = 'Y';
        if (WS_CCY_FOUND != 'Y') {
            WS_ITZ_FLG = 'N';
        }
        if (!TDCIINT.CCY.equalsIgnoreCase("156") 
            && !TDCIINT.CCY.equalsIgnoreCase("840")) {
            for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase("840")) {
                    WS_MIN_AMTC_USD = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                    WS_CNT = 99;
                }
            }
            CEP.TRC(SCCGWA, WS_MIN_AMTC_USD);
            if (WS_MIN_AMTC == 0 
                && WS_MIN_AMTC_USD != 0) {
                CEP.TRC(SCCGWA, "COM-MIN-AMTC");
                WS_BUY_AMT = WS_MIN_AMTC_USD;
                R000_AMT_EX_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_SELL_AMT);
                WS_MIN_AMTC = WS_SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_MIN_AMTC);
        }
        if (TDCIINT.TXN_AMT < WS_MIN_AMTC) {
            CEP.TRC(SCCGWA, "AMT ERR");
            WS_ITZ_FLG = 'N';
        }
        DPCPARMP.AC_TYPE = BPCPQPRD.AC_TYPE;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")) 
            || (DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("027") 
            || (DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_ONE_DAY = "D001";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_SEVEN_DAY = "D007";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_ONE_MONTH = "M001";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_THREE_MONTH = "M003";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_SIX_MONTH = "M006";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_ONE_YEAR = "Y001";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_TWO_YEAR = "Y002";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_THREE_YEAR = "Y003";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_FIVE_YEAR = "Y005";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_SIX_YEAR = "Y006";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES75.WS_TERM_NOT_STANDARD = "S000";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES75);
            }
            if (!TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_ONE_DAY) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_SEVEN_DAY) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_ONE_MONTH) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_THREE_MONTH) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_SIX_MONTH) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_ONE_YEAR) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_TWO_YEAR) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_THREE_YEAR) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_FIVE_YEAR) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_SIX_YEAR) 
                && !TDCIINT.TERM.equalsIgnoreCase(REDEFINES75.WS_TERM_NOT_STANDARD)) {
                if (REDEFINES75.WS_TERM_NOT_STANDARD.equalsIgnoreCase("S000")) {
                    if (!TDCIINT.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_I-1].TERM1) 
                        && !TDCIINT.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_I-1].TERM2) 
                        && !TDCIINT.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_I-1].TERM3) 
                        && !TDCIINT.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_I-1].TERM4) 
                        && !TDCIINT.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_I-1].TERM5) 
                        && !TDCIINT.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_I-1].TERM6)) {
                        CEP.TRC(SCCGWA, "TERM ERR NOT STANDARD");
                        WS_ITZ_FLG = 'N';
                    } else {
                    }
                } else {
                    CEP.TRC(SCCGWA, "TERM ERR STANDARD");
                    WS_ITZ_FLG = 'N';
                }
            }
        }
        R000_GET_BASIC_DAY();
        if (pgmRtn) return;
        R000_TRAN_PROD_DATA();
        if (pgmRtn) return;
    }
    public void R000_GET_BASIC_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = TDCIINT.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
    }
    public void R000_TRAN_PROD_DATA() throws IOException,SQLException,Exception {
        TDCIINT.AC = " ";
        if (BPCPQPRD.CUS_PER_FLG == 'Y') {
            WS_CI_TYP = '1';
        }
        if (BPCPQPRD.CUS_COM_FLG == 'Y') {
            WS_CI_TYP = '2';
        }
        if (BPCPQPRD.CUS_FIN_FLG == 'Y') {
            WS_CI_TYP = '3';
        }
        WS_OPEN_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_PRDAC_CD = BPCPQPRD.AC_TYPE;
        DPCPARMP.AC_TYPE = BPCPQPRD.AC_TYPE;
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
            TDCIINT.NOR_NUM = 0;
            TDCIINT.LOS_NUM = 0;
            TDCIINT.LOS_DNUM = 0;
            TDCIINT.LAST_DEAL_DATE = 0;
            TDCIINT.EDU_FLG = ' ';
        } else {
            if (TDCIINT.LAST_DEAL_DATE == 0) {
                TDCIINT.LAST_DEAL_DATE = TDCIINT.VAL_DATE;
            }
        }
        CEP.TRC(SCCGWA, WS_AC_NO);
        TDCOINT.EXP_DATE = TDCIINT.EXP_DATE;
        CEP.TRC(SCCGWA, TDCOINT.EXP_DATE);
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        R000_GET_AC_INF();
        if (pgmRtn) return;
        R000_GET_CI_TYPE();
        if (pgmRtn) return;
        R000_GET_IRUL_PARM();
        if (pgmRtn) return;
        R000_TRAN_AC_DATA();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_INF() throws IOException,SQLException,Exception {
        if (TDCIINT.AC_SEQ == 0) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCIINT.AC;
            T000_READ_SMST();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'S';
            CICQACAC.DATA.AGR_NO = TDCIINT.AC;
            CICQACAC.DATA.AGR_SEQ = TDCIINT.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_SMSTACO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
        if (TDRSMST.ACO_STS != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            WS_ERR_INF = TDCIINT.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCIINT.TXN_AMT > TDRSMST.BAL) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BTA_TOO_LARGE;
            WS_ERR_INF = TDCIINT.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        R000_READ_TDTIREV();
        if (pgmRtn) return;
        if (TDRIREV.ACTI_NO.trim().length() > 0) {
            TDCPIOD.ACTI_NO = TDRIREV.ACTI_NO;
        }
        WS_BR = TDRSMST.CHE_BR;
        WS_TERM = TDRSMST.TERM;
        IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES17);
        WS_CCY = TDRSMST.CCY;
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("029")) {
            WS_GET_FLG = 'N';
        }
        if (TDRSMST.INSTR_MTH == '3' 
            || TDRSMST.INSTR_MTH == '6') {
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
            if (pgmRtn) return;
        }
        R000_GET_PRODINF();
        if (pgmRtn) return;
        WS_CCY_FOUND = 'N';
        for (WS_X = 1; WS_X <= 20 
            && WS_CCY_FOUND != 'Y'; WS_X += 1) {
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].MIN_CCYC.equalsIgnoreCase(TDRSMST.CCY)) {
                WS_RUL_CDC = TDRIREV.INT_RUL_CD;
                WS_CALD_FLG = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].INT_RUL;
                WS_CCY_FOUND = 'Y';
                WS_DOCU_NO = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].DOCU_NO;
                WS_NE_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].NERAT_CD;
                WS_PR_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_X-1].PRRAT_CD;
                WS_W = WS_X;
            }
        }
    }
    public void R000_GET_PRODINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPIOD);
        TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCPIOD.C_PROD_CD = TDCIINT.PROD_CD;
        CEP.TRC(SCCGWA, TDCIINT.PROD_CD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        TDCPIOD.ACTI_NO = TDRSMST.ACTI_NO;
        if (TDCPIOD.ACTI_NO.trim().length() == 0) {
            TDCPIOD.BR = WS_BR;
            TDCPIOD.INTERM = WS_TERM;
            TDCPIOD.CCY = WS_CCY;
        }
        if (WS_GET_FLG != ' ') {
            TDCPIOD.GET_FLG = WS_GET_FLG;
        }
        S000_CALL_TDZPROD();
        if (pgmRtn) return;
    }
    public void R000_GET_CI_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDRSMST.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_TYP_T = CICACCU.DATA.CI_TYP;
    }
    public void R000_READ_CDI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READ_CDI();
        if (pgmRtn) return;
    }
    public void R000_GET_IRUL_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCIRULP);
        CEP.TRC(SCCGWA, TDCIRULP);
    }
    public void R000_TRAN_AC_DATA() throws IOException,SQLException,Exception {
        WS_CI_TYP = CICACCU.DATA.CI_TYP;
        WS_PRDAC_CD = TDRSMST.PRDAC_CD;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        TDCIINT.PROD_CD = TDRSMST.PROD_CD;
        WS_TRANS_INT = TDRSMST.BAL_INT;
        WS_PAYING_INT = TDRSMST.EXP_INT;
        WS_TAXING_INT = 0;
        WS_PAYING_TAX = 0;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            R000_READ_CDI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRCDI.LAST_DEAL_DATE);
            TDCIINT.CD_PERD = TDRCDI.CD_PERD;
            TDCIINT.CD_AMT = TDRCDI.CD_AMT;
            TDCIINT.NOR_NUM = TDRCDI.NOR_NUM;
            TDCIINT.LOS_DNUM = TDRCDI.LOS_DNUM;
            TDCIINT.LAST_DEAL_DATE = TDRCDI.LAST_DEAL_DATE;
            CEP.TRC(SCCGWA, "XIANLIANGC");
        }
        CEP.TRC(SCCGWA, "XLC");
        TDCIINT.CCY = TDRSMST.CCY;
        TDCIINT.TERM = TDRSMST.TERM;
        TDCIINT.BAL = TDRSMST.BAL;
        TDCIINT.VAL_DATE = TDRSMST.VAL_DATE;
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDCIINT.EXP_DATE = TDRSMST.EXP_DATE;
        } else {
            if (TDCIINT.CAL_TYPE == '2') {
                TDCIINT.EXP_DATE = 0;
            }
        }
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) 
            && TDCIINT.EXP_DATE == 0 
            && TDCIINT.CAL_TYPE == '1') {
            TDCIINT.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B030_TRANSFER_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDINT);
        TDCCDINT.CALL_SRC = 'O';
        CEP.TRC(SCCGWA, TDCIINT.CAL_TYPE);
        CEP.TRC(SCCGWA, "KING123567");
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) 
            && TDCIINT.CAL_TYPE == '1') {
            TDCCDINT.OPT = '4';
        } else {
            TDCCDINT.OPT = '0';
        }
        TDCCDINT.PRDP_PTR = TDCPIOD;
        TDCCDINT.IRUL_PTR = TDCIRULP;
        if (TDCIINT.FUNC == '2') {
            TDCCDINT.AC = TDRSMST.KEY.ACO_AC;
        }
        CEP.TRC(SCCGWA, WS_CI_TYP);
        TDCCDINT.CI_TYP = WS_CI_TYP;
        TDCCDINT.OPEN_BK = WS_OPEN_BK;
        TDCCDINT.PROD_CD = TDCIINT.PROD_CD;
        TDCCDINT.PRDAC_CD = WS_PRDAC_CD;
        TDCCDINT.CCY = TDCIINT.CCY;
        TDCCDINT.TERM = TDCIINT.TERM;
        TDCCDINT.TXN_AMT = TDCIINT.TXN_AMT;
        TDCCDINT.BAL = TDCIINT.BAL;
        TDCCDINT.VAL_DATE = TDCIINT.VAL_DATE;
        TDCCDINT.EXP_DATE = TDCIINT.EXP_DATE;
        if (TDCIINT.CAL_TYPE == '1') {
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
                TDCCDINT.TXN_DATE = TDCIINT.EXP_DATE;
            } else {
                TDCCDINT.TXN_DATE = TDRSMST.EXP_DATE;
            }
        } else {
            if (TDCIINT.CAL_TYPE == '2') {
                TDCCDINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                TDCCDINT.TXN_DATE = TDCIINT.TXN_DATE;
            }
        }
        TDCCDINT.NOR_NUM = TDCIINT.NOR_NUM;
        TDCCDINT.LOS_NUM = TDCIINT.LOS_NUM;
        TDCCDINT.LOS_DNUM = TDCIINT.LOS_DNUM;
        TDCCDINT.LAST_DEAL_DATE = TDCIINT.LAST_DEAL_DATE;
        TDCCDINT.EDU_FLG = TDCIINT.EDU_FLG;
        TDCCDINT.CD_AMT = TDCIINT.CD_AMT;
        TDCCDINT.PERD_TYP = TDRCDI.PERD_TYP;
        TDCCDINT.CD_PERD = TDRCDI.CD_PERD;
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDCCDINT.RAT = TDRIREV.CON_RATE;
            CEP.TRC(SCCGWA, TDCIINT.RAT);
            if (TDCIINT.RAT != 0) {
                CEP.TRC(SCCGWA, "JFTEST1");
                TDCCDINT.RAT = TDCIINT.RAT;
                CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            }
            TDCCDINT.SPRD_PNT = TDRIREV.SPRD_PNT;
            TDCCDINT.SPRD_PCT = TDRIREV.SPRD_PCT;
        } else {
            TDCCDINT.SPRD_PNT = TDRIREV.SPRD_PNT;
            TDCCDINT.SPRD_PCT = TDRIREV.SPRD_PCT;
            if (TDRIREV.INT_SEL == '4') {
                TDCCDINT.RAT = TDRIREV.CON_RATE;
            }
        }
        TDCCDINT.TRANS_INT = WS_TRANS_INT;
        TDCCDINT.PAYING_INT = WS_PAYING_INT;
        TDCCDINT.TAXING_INT = WS_TAXING_INT;
        TDCCDINT.PAYING_TAX = WS_PAYING_TAX;
        TDCCDINT.CALL_NO = TDCIINT.CALL_NO;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
        if (TDCIINT.FUNC == '1') {
            if (BPCQCCY.DATA.STD_DAYS == 360) {
                TDCCDINT.CALR_STD = "1";
            } else if (BPCQCCY.DATA.STD_DAYS == 365) {
                TDCCDINT.CALR_STD = "2";
            } else if (BPCQCCY.DATA.STD_DAYS == 366) {
                TDCCDINT.CALR_STD = "3";
            }
        } else {
            TDCCDINT.CALR_STD = TDRSMST.CALR_STD;
        }
        TDCCDINT.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        IBS.CPY2CLS(SCCGWA, TDRSMST.STSW.substring(21 - 1, 21 + 20 - 1), TDCCDINT.INT_STSW);
        if (WS_MTZ_FLG == 'Y') {
            TDCCDINT.INT_STSW.MTZ_FLG = '1';
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if ((TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).trim().length() == 0 
            || TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0"))) {
            TDCCDINT.INT_STSW.RULE = WS_CALD_FLG;
        }
        TDCCDINT.INT_SEL = TDRIREV.INT_SEL;
        TDCCDINT.IRAT_CD = TDRIREV.RATE_TYPE;
        TDCCDINT.NE_IRAT_CD = WS_NE_IRAT_CD;
        TDCCDINT.PR_IRAT_CD = WS_PR_IRAT_CD;
        TDCCDINT.INT_SEL = TDRIREV.INT_SEL;
        TDCCDINT.AC_BR = TDRSMST.OWNER_BR;
        if (!TDRSMST.TERM.equalsIgnoreCase("S000")) {
            TDCCDINT.INSTR_MTH = TDRSMST.INSTR_MTH;
        }
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
            && TDCIINT.TXN_AMT < TDRSMST.BAL 
            && TDCIINT.TXN_DATE >= TDRSMST.EXP_DATE) {
            TDCCDINT.PAYING_INT = 0;
            TDCCDINT.TXN_AMT = TDCIINT.TXN_AMT;
            TDCCDINT.BAL = TDCIINT.TXN_AMT;
        }
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("024")) {
            TDCCDINT.BAL = TDRSMST.FBAL;
        }
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.OTH_FLG);
        if (TDRINST.SER_TIME != 0) {
            TDCCDINT.SER_TIME = (short) (TDRINST.SER_TIME - TDRSMST.SER_TIME);
        } else {
            if (TDCPIOD.OTH_PRM.PAY_GRCE == 0) {
                TDCCDINT.SER_TIME = 9999;
            } else {
                TDCCDINT.SER_TIME = (short) (TDCPIOD.OTH_PRM.PAY_GRCE - TDRSMST.SER_TIME);
            }
        }
        if (TDCIINT.CAL_TYPE == '1' 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDCCDINT.INSTR_MTH = '0';
        }
        TDCCDINT.DOCU_NO = WS_DOCU_NO;
        TDCCDINT.REG_FLG = '2';
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(39 - 1, 39 + 1 - 1).equalsIgnoreCase("1")) {
            TDCCDINT.LAST_DEAL_DATE = TDRSMST.LAST_HDRW_DATE;
            if (TDCCDINT.LAST_DEAL_DATE == 0) {
                TDCCDINT.LAST_DEAL_DATE = TDRSMST.VAL_DATE;
            }
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("024")) {
                TDCCDINT.BAL = TDRSMST.BAL;
                TDCCDINT.TXN_AMT = TDRSMST.BAL;
            }
        }
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        CEP.TRC(SCCGWA, TDCCDINT.PRDAC_CD);
        CEP.TRC(SCCGWA, TDRSMST.CALR_STD);
        CEP.TRC(SCCGWA, TDCCDINT.CALR_STD);
        CEP.TRC(SCCGWA, TDCCDINT.AC);
        CEP.TRC(SCCGWA, TDCCDINT.AC_BR);
        CEP.TRC(SCCGWA, TDCCDINT.CCY);
        CEP.TRC(SCCGWA, TDCCDINT.TERM);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        CEP.TRC(SCCGWA, TDCCDINT.LAST_DEAL_DATE);
        if (TDCIINT.PROD_CD.equalsIgnoreCase("9530000015") 
            || TDCIINT.PROD_CD.equalsIgnoreCase("9530000017")) {
            TDCCDINT.INT_STSW.ZNT_FLG = '1';
        }
        S000_CALL_TDZCDINT();
        if (pgmRtn) return;
    }
    public void R000_LST_DEAL_DATE_BAL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPHIS);
        TDRPHIS.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READ_TDTPHIS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCCDINT);
        TDCCDINT.CALL_SRC = 'O';
        TDCCDINT.OPT = '3';
        TDCCDINT.PRDP_PTR = TDCPIOD;
        TDCCDINT.IRUL_PTR = TDCIRULP;
        TDCCDINT.AC = TDRSMST.KEY.ACO_AC;
        TDCCDINT.CI_TYP = WS_CI_TYP;
        TDCCDINT.OPEN_BK = WS_OPEN_BK;
        TDCCDINT.PROD_CD = TDCIINT.PROD_CD;
        TDCCDINT.PRDAC_CD = WS_PRDAC_CD;
        TDCCDINT.CCY = TDRSMST.CCY;
        TDCCDINT.TERM = TDRSMST.TERM;
        TDCCDINT.TXN_AMT = TDRSMST.BAL;
        TDCCDINT.BAL = TDRSMST.BAL;
        TDCCDINT.VAL_DATE = TDRSMST.VAL_DATE;
        TDCCDINT.EXP_DATE = TDRSMST.EXP_DATE;
        TDCCDINT.PART_NUM = TDRSMST.PART_NUM;
        CEP.TRC(SCCGWA, TDCCDINT.PART_NUM);
        CEP.TRC(SCCGWA, "XIANKANHAO1");
        CEP.TRC(SCCGWA, TDCIINT.CAL_TYPE);
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        TDCCDINT.TXN_DATE = TDRCDI.LAST_DEAL_DATE;
        TDCCDINT.NOR_NUM = TDCIINT.NOR_NUM;
        TDCCDINT.LOS_NUM = TDCIINT.LOS_NUM;
        TDCCDINT.LOS_DNUM = TDCIINT.LOS_DNUM;
        TDCCDINT.EDU_FLG = TDCIINT.EDU_FLG;
        TDCCDINT.CD_AMT = TDRCDI.CD_AMT;
        TDCCDINT.PERD_TYP = TDRCDI.PERD_TYP;
        TDCCDINT.CD_PERD = TDRCDI.CD_PERD;
        TDCCDINT.RAT = TDRIREV.CON_RATE;
        TDCCDINT.TRANS_INT = 0;
        TDCCDINT.PAYING_INT = WS_PAYING_INT;
        TDCCDINT.TAXING_INT = WS_TAXING_INT;
        TDCCDINT.PAYING_TAX = WS_PAYING_TAX;
        TDCCDINT.CALL_NO = TDCIINT.CALL_NO;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
        TDCCDINT.CALR_STD = TDRSMST.CALR_STD;
        TDCCDINT.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).equalsIgnoreCase("2") 
            || TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).equalsIgnoreCase("3")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDCCDINT.INT_STSW.ZNT_FLG = TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).charAt(0);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).trim().length() > 0 
            && !TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDCCDINT.INT_STSW.RULE = TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).charAt(0);
        } else {
            TDCCDINT.INT_STSW.RULE = WS_CALD_FLG;
        }
        TDCCDINT.AC_BR = TDRSMST.OWNER_BR;
        if (!TDRSMST.TERM.equalsIgnoreCase("S000")) {
            TDCCDINT.INSTR_MTH = TDRSMST.INSTR_MTH;
        }
        TDCCDINT.DOCU_NO = WS_DOCU_NO;
        TDCCDINT.REG_FLG = '2';
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        CEP.TRC(SCCGWA, TDCCDINT.PRDAC_CD);
        CEP.TRC(SCCGWA, TDRSMST.CALR_STD);
        CEP.TRC(SCCGWA, TDCCDINT.CALR_STD);
        CEP.TRC(SCCGWA, TDCCDINT.AC);
        CEP.TRC(SCCGWA, TDCCDINT.AC_BR);
        CEP.TRC(SCCGWA, TDCCDINT.CCY);
        CEP.TRC(SCCGWA, TDCCDINT.TERM);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.CD_PERD);
        S000_CALL_TDZCDINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
        CEP.TRC(SCCGWA, "D");
        CEP.TRC(SCCGWA, WS_TRANS_INT);
    }
    public void B030_CAL_PAYING_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCDI.PERD_TYP);
        if (TDRCDI.PERD_TYP != '1' 
            && TDRCDI.PERD_TYP != '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_N_SUP_AUTO_INT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRPHIS);
        TDRPHIS.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        if (!DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
            T000_READ_TDTPHIS();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDCCDINT);
        TDCCDINT.CALL_SRC = 'O';
        TDCCDINT.OPT = '3';
        if (TDCIINT.FUNC == '4') {
            TDCCDINT.OPT = '7';
        }
        TDCCDINT.PRDP_PTR = TDCPIOD;
        TDCCDINT.IRUL_PTR = TDCIRULP;
        TDCCDINT.AC = TDRSMST.KEY.ACO_AC;
        TDCCDINT.CI_TYP = WS_CI_TYP;
        TDCCDINT.OPEN_BK = WS_OPEN_BK;
        TDCCDINT.PROD_CD = TDCIINT.PROD_CD;
        TDCCDINT.PRDAC_CD = WS_PRDAC_CD;
        TDCCDINT.CCY = TDRSMST.CCY;
        TDCCDINT.TERM = TDRSMST.TERM;
        TDCCDINT.TXN_AMT = TDCIINT.TXN_AMT;
        TDCCDINT.BAL = TDRSMST.BAL;
        TDCCDINT.VAL_DATE = TDRSMST.VAL_DATE;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            CEP.TRC(SCCGWA, TDCIINT.LAST_DEAL_DATE);
            CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
            if (TDCIINT.LAST_DEAL_DATE < TDRSMST.VAL_DATE) {
                TDCCDINT.VAL_DATE = TDRSMST.VAL_DATE;
            } else {
                TDCCDINT.VAL_DATE = TDCIINT.LAST_DEAL_DATE;
            }
        }
        CEP.TRC(SCCGWA, "1023");
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        TDCCDINT.EXP_DATE = TDRSMST.EXP_DATE;
        TDCCDINT.PART_NUM = TDRSMST.PART_NUM;
        CEP.TRC(SCCGWA, TDCCDINT.PART_NUM);
        CEP.TRC(SCCGWA, "XIANKANHAO");
        CEP.TRC(SCCGWA, TDCIINT.CAL_TYPE);
        CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
        B031_GET_TERM_PI_DT();
        if (pgmRtn) return;
        TDCCDINT.NOR_NUM = TDCIINT.NOR_NUM;
        TDCCDINT.LOS_NUM = TDCIINT.LOS_NUM;
        TDCCDINT.LOS_DNUM = TDCIINT.LOS_DNUM;
        TDCCDINT.LAST_DEAL_DATE = TDCIINT.LAST_DEAL_DATE;
        CEP.TRC(SCCGWA, "IINT-LAST-DEAL-DATE");
        CEP.TRC(SCCGWA, TDCIINT.LAST_DEAL_DATE);
        TDCCDINT.EDU_FLG = TDCIINT.EDU_FLG;
        TDCCDINT.CD_AMT = TDRCDI.CD_AMT;
        TDCCDINT.PERD_TYP = TDRCDI.PERD_TYP;
        TDCCDINT.CD_PERD = TDRCDI.CD_PERD;
        TDCCDINT.RAT = TDRIREV.CON_RATE;
        TDCCDINT.TRANS_INT = WS_TRANS_INT;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            TDCCDINT.TRANS_INT = 0;
        }
        TDCCDINT.PAYING_INT = WS_PAYING_INT;
        TDCCDINT.TAXING_INT = WS_TAXING_INT;
        TDCCDINT.PAYING_TAX = WS_PAYING_TAX;
        TDCCDINT.CALL_NO = TDCIINT.CALL_NO;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
        TDCCDINT.CALR_STD = TDRSMST.CALR_STD;
        TDCCDINT.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).equalsIgnoreCase("2") 
            || TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).equalsIgnoreCase("3")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDCCDINT.INT_STSW.ZNT_FLG = TDRSMST.STSW.substring(30 - 1, 30 + 1 - 1).charAt(0);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        IBS.CPY2CLS(SCCGWA, TDRSMST.STSW.substring(21 - 1, 21 + 20 - 1), TDCCDINT.INT_STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).trim().length() > 0 
            && !TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDCCDINT.INT_STSW.RULE = TDRSMST.STSW.substring(31 - 1, 31 + 1 - 1).charAt(0);
        } else {
            TDCCDINT.INT_STSW.RULE = WS_CALD_FLG;
        }
        TDCCDINT.AC_BR = TDRSMST.OWNER_BR;
        if (!TDRSMST.TERM.equalsIgnoreCase("S000")) {
            TDCCDINT.INSTR_MTH = TDRSMST.INSTR_MTH;
        }
        TDCCDINT.REG_FLG = '2';
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        CEP.TRC(SCCGWA, TDCCDINT.PRDAC_CD);
        CEP.TRC(SCCGWA, TDRSMST.CALR_STD);
        CEP.TRC(SCCGWA, TDCCDINT.CALR_STD);
        CEP.TRC(SCCGWA, TDCCDINT.AC);
        CEP.TRC(SCCGWA, TDCCDINT.AC_BR);
        CEP.TRC(SCCGWA, TDCCDINT.CCY);
        CEP.TRC(SCCGWA, TDCCDINT.TERM);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDCCDINT.BAL);
        CEP.TRC(SCCGWA, TDCCDINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
        CEP.TRC(SCCGWA, TDCCDINT.CD_PERD);
        S000_CALL_TDZCDINT();
        if (pgmRtn) return;
        if (TDCCDINT.PAYING_INT < 0) {
            TDCCDINT.PAYING_INT = 0;
        }
    }
    public void B031_GET_TERM_PI_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCDI.PERD_TYP);
        CEP.TRC(SCCGWA, TDRCDI.CD_PERD);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE < TDRSMST.VAL_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_DATE1 = TDRSMST.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES59);
        if (TDRCDI.FST_INT_DATE == 0) {
            TDRCDI.FST_INT_DATE = TDRSMST.VAL_DATE;
        }
        if (TDRCDI.FST_INT_DATE > 0) {
            WS_DATE1 = TDRCDI.FST_INT_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES59);
        }
        WS_DATE3 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE3+"", REDEFINES53);
        WS_MM = (short) (( REDEFINES53.WS_YYYY3 - REDEFINES59.WS_YYYY1 ) * 12 + REDEFINES53.WS_MM3 - REDEFINES59.WS_MM1);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE3;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "KING99");
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        CEP.TRC(SCCGWA, SCCCKDT.MTH_DAYS);
        if (REDEFINES53.WS_DD3 < REDEFINES59.WS_DD1 
            && REDEFINES53.WS_DD3 < SCCCKDT.MTH_DAYS) {
            WS_MM = (short) (WS_MM - 1);
        }
        CEP.TRC(SCCGWA, "3");
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DATE2 = WS_DATE3;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DD = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_DD);
        if (TDRCDI.PERD_TYP == '1') {
            WS_COUNT_NUM = (short) (WS_MM / TDRCDI.CD_PERD);
            if (WS_COUNT_NUM <= 0) {
                if (TDRCDI.FST_INT_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                    && ((TDRSMST.BAL_INT == 0 
                    && TDRCDI.FST_INT_DATE > 0))) {
                    TDCCDINT.TXN_DATE = TDRCDI.FST_INT_DATE;
                } else {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NO_PAYING_INT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                if (TDRCDI.FST_INT_DATE != 0) {
                    SCCCLDT.DATE1 = TDRCDI.FST_INT_DATE;
                } else {
                    SCCCLDT.DATE1 = WS_DATE1;
                }
                SCCCLDT.MTHS = (short) (TDRCDI.CD_PERD * WS_COUNT_NUM);
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                TDCCDINT.TXN_DATE = SCCCLDT.DATE2;
                if (TDCCDINT.TXN_DATE > TDRSMST.EXP_DATE) {
                    TDCCDINT.TXN_DATE = TDRSMST.EXP_DATE;
                }
            }
        }
        if (TDRCDI.PERD_TYP == '2') {
            WS_COUNT_NUM = (short) (WS_DD / TDRCDI.CD_PERD);
            if (WS_COUNT_NUM <= 0) {
                if (TDRCDI.FST_INT_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                    && (TDRSMST.BAL_INT == 0)) {
                    TDCCDINT.TXN_DATE = TDRCDI.FST_INT_DATE;
                } else {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NO_PAYING_INT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                if (TDRCDI.FST_INT_DATE != 0) {
                    SCCCLDT.DATE1 = TDRCDI.FST_INT_DATE;
                } else {
                    SCCCLDT.DATE1 = WS_DATE1;
                }
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                SCCCLDT.DAYS = WS_COUNT_NUM * TDRCDI.CD_PERD;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                TDCCDINT.TXN_DATE = SCCCLDT.DATE2;
                if (TDCCDINT.TXN_DATE > TDRSMST.EXP_DATE) {
                    TDCCDINT.TXN_DATE = TDRSMST.EXP_DATE;
                }
            }
        }
        if (TDCCDINT.TXN_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NO_PAYING_INT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "GET THE TXN-DATE");
        CEP.TRC(SCCGWA, TDCCDINT.TXN_DATE);
    }
    public void B031_CAL_PRD_INT() throws IOException,SQLException,Exception {
        if (TDCIINT.VAL_DATE == TDCIINT.TXN_DATE 
            && (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B031_TRANSFER_INFO_CEINT();
            if (pgmRtn) return;
            WS_INT = TDCCEINT.INT;
            TDCIINT.RAT = TDCCEINT.RAT;
        }
        if (TDCIINT.EXP_DATE == TDCIINT.TXN_DATE) {
            B031_TRANSFER_INFO_CEINT();
            if (pgmRtn) return;
            WS_INT = TDCCEINT.INT;
            TDCIINT.RAT = TDCCEINT.RAT;
        }
        if (TDCIINT.EXP_DATE < TDCIINT.TXN_DATE) {
            B031_TRANSFER_INFO_CEINT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCCEINT.INT);
            WS_INT = TDCCEINT.INT;
            TDCIINT.RAT = TDCCEINT.RAT;
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCIINT.EXP_DATE;
            SCCCLDT.DATE2 = TDCIINT.TXN_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_INT = TDCIINT.TXN_AMT * SCCCLDT.DAYS * WS_DD_RAT / 100 / BPCQCCY.DATA.STD_DAYS + WS_INT;
        }
        if (TDCIINT.EXP_DATE > TDCIINT.TXN_DATE 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCIINT.VAL_DATE;
            SCCCLDT.DATE2 = TDCIINT.TXN_DATE;
            CEP.TRC(SCCGWA, TDCIINT.VAL_DATE);
            CEP.TRC(SCCGWA, TDCIINT.TXN_DATE);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            WS_INT = TDCIINT.TXN_AMT * SCCCLDT.DAYS * WS_DD_RAT / 100 / BPCQCCY.DATA.STD_DAYS;
        }
        CEP.TRC(SCCGWA, WS_INT);
    }
    public void B031_TRANSFER_INFO_CEINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCEINT);
        TDCCEINT.OPT = '0';
        TDCCEINT.IRUL_PTR = TDCIRULP;
        TDCCEINT.CI_TYP = WS_CI_TYP;
        TDCCEINT.OPEN_BK = WS_OPEN_BK;
        TDCCEINT.PROD_CD = TDCIINT.PROD_CD;
        TDCCEINT.IRAT_CD = WS_IRAT_CD;
        TDCCEINT.PRDAC_CD = WS_PRDAC_CD;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDCCEINT.PRDAC_CD = "020";
        }
        TDCCEINT.CCY = TDCIINT.CCY;
        TDCCEINT.TERM = TDCIINT.TERM;
        TDCCEINT.TXN_AMT = TDCIINT.TXN_AMT;
        TDCCEINT.VAL_DATE = TDCIINT.VAL_DATE;
        TDCCEINT.EXP_DATE = TDCIINT.EXP_DATE;
        if (TDCIINT.CAL_TYPE == '2') {
            TDCCEINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            TDCCEINT.TXN_DATE = TDCIINT.TXN_DATE;
        }
        TDCCEINT.LOS_DNUM = TDCIINT.LOS_DNUM;
        TDCCEINT.CD_AMT = TDCIINT.CD_AMT;
        TDCCEINT.CD_PERD = TDCIINT.CD_PERD;
        TDCCEINT.RAT = TDCIINT.RAT;
        TDCCEINT.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
        if (BPCQCCY.DATA.STD_DAYS == 360) {
            TDCCEINT.CALR_STD = "1";
        } else if (BPCQCCY.DATA.STD_DAYS == 365) {
            TDCCEINT.CALR_STD = "2";
        } else if (BPCQCCY.DATA.STD_DAYS == 366) {
            TDCCEINT.CALR_STD = "3";
        }
        TDCCEINT.CALD_FLG = WS_CALD_FLG;
        S000_CALL_TDZCEINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        CEP.TRC(SCCGWA, TDCCEINT.INT);
    }
    public void R000_OUTPUT_SUB_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOINT);
        if (TDCIINT.FUNC == '1') {
            CEP.TRC(SCCGWA, WS_INT);
            TDCOINT.TOT_INT = WS_INT;
            TDCOINT.DRAW_TOT_AMT = TDCIINT.TXN_AMT + WS_INT;
            TDCOINT.TOT_INT = WS_INT;
            TDCOINT.TAX_INT = WS_INT;
            TDCOINT.INT_TAX = 0;
            TDCOINT.PROD_CD = TDCIINT.PROD_CD;
            TDCOINT.TD_TERM = TDCIINT.TERM;
            TDCOINT.TD_RAT = TDCIINT.RAT;
            TDCOINT.TX_ANT = TDCIINT.TXN_AMT;
            TDCOINT.EXP_DATE = TDCIINT.EXP_DATE;
        } else {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1")                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";

                || TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "F");
                TDCOINT.DRAW_TOT_AMT = TDCIINT.TXN_AMT + TDCCDINT.PAYING_INT - TDCCDINT.PAYING_TAX - TDCCDINT.TRANS_INT;
                CEP.TRC(SCCGWA, TDCIINT.TXN_AMT);
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_TAX);
                CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
                if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
                    CEP.TRC(SCCGWA, "F1");
                    if (TDCIINT.CAL_TYPE == '2') {
                        TDCOINT.DRAW_TOT_AMT = TDCIINT.TXN_AMT + TDCCDINT.PAYING_INT - TDCCDINT.PAYING_TAX - TDCCDINT.TRANS_INT;
                    } else {
                        CEP.TRC(SCCGWA, "F2");
                        TDCOINT.DRAW_TOT_AMT = TDCIINT.TXN_AMT + TDCCDINT.PAYING_INT - TDCCDINT.PAYING_TAX;
                    }
                }
                TDCOINT.TD_SAVE_DAY = TDCCDINT.DAYS;
                TDCCDINT.PAYING_INT = TDCCDINT.PAYING_INT - TDCCDINT.TRANS_INT - TDCCDINT.PAYING_TAX;
                TDCOINT.TOT_INT = TDCCDINT.PAYING_INT;
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
                CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
                CEP.TRC(SCCGWA, "HAIJING");
                TDCOINT.TD_TERM = TDCCDINT.TERM;
                TDCOINT.TD_RAT = TDCCDINT.RAT;
                CEP.TRC(SCCGWA, TDCOINT.TD_RAT);
                CEP.TRC(SCCGWA, "ZHONGGUO");
                TDCOINT.TD_SAVE_DAY = TDCCDINT.DAYS;
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
                TDCOINT.TAX_INT = TDCCDINT.PAYING_INT;
                TDCOINT.INT_TAX = TDCCDINT.PAYING_TAX;
                CEP.TRC(SCCGWA, TDCOINT.TAX_INT);
                TDCOINT.DRW_NUM = TDRCDI.NOR_NUM;
                if (TDRCDI.NOR_NUM > 0) {
                    TDCOINT.LST_DRW_DT = TDRCDI.LAST_DEAL_DATE;
                } else {
                    TDCOINT.LST_DRW_DT = 0;
                }
            } else {
                CEP.TRC(SCCGWA, "G");
                TDCOINT.DRAW_TOT_AMT = TDCIINT.TXN_AMT + TDCCDINT.PAYING_INT - TDCCDINT.PAYING_TAX;
                TDCOINT.TAX_INT = TDCCDINT.PAYING_INT;
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_TAX);
                CEP.TRC(SCCGWA, TDCCDINT.TAXING_INT);
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
                TDCOINT.TAX_INT = TDCCDINT.PAYING_INT;
                TDCOINT.INT_TAX = TDCCDINT.PAYING_TAX;
                TDCOINT.TOT_INT = TDCOINT.TAX_INT;
                TDCOINT.PROD_CD = TDCIINT.PROD_CD;
                TDCOINT.TD_TERM = TDCCDINT.TERM;
                TDCOINT.TD_RAT = TDCCDINT.RAT;
                TDCOINT.TX_ANT = TDCIINT.TXN_AMT;
                TDCOINT.TD_SAVE_DAY = TDCCDINT.DAYS;
                CEP.TRC(SCCGWA, TDCCDINT.INT_STSW.ZNT_FLG);
                if (TDCCDINT.INT_STSW.ZNT_FLG == '1') {
                    TDCOINT.TAX_INT = TDCOINT.TAX_INT - TDCCDINT.ZNT_DD_INT;
                    TDCOINT.TD_SAVE_DAY = TDCCDINT.DAYS;
                    TDCOINT.DD_SAVE_DAY = TDCCDINT.ZNT_DD_DAYS;
                    TDCOINT.DD_RAT = TDCCDINT.ZNT_DD_RAT;
                    TDCOINT.DD_TOT_INT = TDCCDINT.ZNT_DD_INT;
                }
                CEP.TRC(SCCGWA, TDCOINT.PROD_CD);
                CEP.TRC(SCCGWA, TDCOINT.TD_TERM);
                CEP.TRC(SCCGWA, TDCOINT.TD_SAVE_DAY);
                CEP.TRC(SCCGWA, TDCOINT.DD_SAVE_DAY);
                CEP.TRC(SCCGWA, TDCOINT.TD_RAT);
                CEP.TRC(SCCGWA, TDCOINT.DD_RAT);
                CEP.TRC(SCCGWA, TDCOINT.TX_ANT);
                CEP.TRC(SCCGWA, TDCOINT.DD_TOT_INT);
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
                CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
                CEP.TRC(SCCGWA, TDCOINT.TOT_INT);
                CEP.TRC(SCCGWA, TDCCDINT.PAYING_TAX);
                CEP.TRC(SCCGWA, "ALL AMT");
                CEP.TRC(SCCGWA, TDCOINT.DRAW_TOT_AMT);
            }
        }
        IBS.init(SCCGWA, SCCCLDT);
        if (TDCIINT.CAL_TYPE == '1') {
            SCCCLDT.DATE1 = TDRSMST.VAL_DATE;
            if (TDRSMST.EXP_DATE != 0) {
                SCCCLDT.DATE2 = TDRSMST.EXP_DATE;
            } else {
                SCCCLDT.DATE2 = TDCIINT.EXP_DATE;
            }
        } else {
            SCCCLDT.DATE1 = TDRSMST.VAL_DATE;
            SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCOINT.TD_SAVE_DAY = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, "TRC RAT");
        CEP.TRC(SCCGWA, TDCOINT.TD_RAT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD726";
        SCCFMT.DATA_PTR = TDCOINT;
        SCCFMT.DATA_LEN = 189;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        if (TDCIINT.PROD_CD.equalsIgnoreCase("9530000015") 
            || TDCIINT.PROD_CD.equalsIgnoreCase("9530000017")) {
            TDCIINT.CD_AMT = TDCOINT.TAX_INT;
        }
        CEP.TRC(SCCGWA, TDCIINT.CD_AMT);
    }
    public void R000_READ_TDTIREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        T000_READ_TDTIREV();
        if (pgmRtn) return;
    }
    public void B030_CAL_LZ_INT() throws IOException,SQLException,Exception {
        if (TDCIINT.EXP_DATE > TDCIINT.TXN_DATE 
            && TDCIINT.EDU_FLG != '0') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCIINT.VAL_DATE;
            SCCCLDT.DATE2 = TDCIINT.TXN_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_INT = TDCIINT.TXN_AMT * SCCCLDT.DAYS * TDCIINT.RAT / 100 / BPCQCCY.DATA.STD_DAYS;
        } else {
            R000_GET_DEP_MTHS();
            if (pgmRtn) return;
            WS_CD_NUM = (short) (WS_MTHS / TDCIINT.CD_PERD);
            WS_TERM_DATE = TDCIINT.VAL_DATE;
            for (WS_I = 1; WS_I <= WS_CD_NUM; WS_I += 1) {
                R000_CAL_TERM_DAYS();
                if (pgmRtn) return;
                WS_NACCU = WS_TERM_DAYS * TDCIINT.CD_AMT * WS_I + WS_NACCU;
                CEP.TRC(SCCGWA, WS_NACCU);
            }
            WS_INT = ( WS_NACCU * TDCIINT.RAT ) / 100 / BPCQCCY.DATA.STD_DAYS;
        }
        if (TDCIINT.EXP_DATE < TDCIINT.TXN_DATE) {
            R000_GET_DD_RAT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCIINT.EXP_DATE;
            SCCCLDT.DATE2 = TDCIINT.TXN_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_INT = TDCIINT.TXN_AMT * SCCCLDT.DAYS * WS_DD_RAT / 100 / BPCQCCY.DATA.STD_DAYS + WS_INT;
        }
    }
    public void R000_CAL_TERM_DAYS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        CEP.TRC(SCCGWA, WS_TERM_DATE);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_TERM_DATE;
        WS_TEMP_DATE = WS_TERM_DATE;
        SCCCLDT.MTHS = TDCIINT.CD_PERD;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "22222222");
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_TERM_DATE = SCCCLDT.DATE2;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_TEMP_DATE;
        SCCCLDT.DATE2 = WS_TERM_DATE;
        CEP.TRC(SCCGWA, WS_TEMP_DATE);
        CEP.TRC(SCCGWA, WS_TERM_DATE);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_TERM_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
    }
    public void R000_GET_DEP_MTHS() throws IOException,SQLException,Exception {
        WS_VAL_DATE = TDCIINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_VAL_DATE+"", REDEFINES21);
        WS_TXN_DATE = TDCIINT.TXN_DATE;
        IBS.CPY2CLS(SCCGWA, WS_TXN_DATE+"", REDEFINES26);
        CEP.TRC(SCCGWA, WS_VAL_DATE);
        CEP.TRC(SCCGWA, REDEFINES26.WS_TXN_DATE_YY);
        CEP.TRC(SCCGWA, REDEFINES21.WS_VAL_DATE_YY);
        CEP.TRC(SCCGWA, REDEFINES26.WS_TXN_DATE_MM);
        CEP.TRC(SCCGWA, REDEFINES21.WS_VAL_DATE_MM);
        WS_MTHS = (short) (( REDEFINES26.WS_TXN_DATE_YY - REDEFINES21.WS_VAL_DATE_YY ) * 12 + REDEFINES26.WS_TXN_DATE_MM - REDEFINES21.WS_VAL_DATE_MM);
        CEP.TRC(SCCGWA, WS_MTHS);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_TXN_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (REDEFINES26.WS_TXN_DATE_DD < REDEFINES21.WS_VAL_DATE_DD 
            && REDEFINES26.WS_TXN_DATE_DD < SCCCKDT.MTH_DAYS) {
            WS_MTHS = (short) (WS_MTHS - 1);
        }
    }
    public void R000_GET_DD_RAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        if (WS_CI_TYP_T == '3') {
            BPCCINTI.BASE_INFO.BASE_TYP = K_TY_BASE_TYP;
        } else {
            BPCCINTI.BASE_INFO.BASE_TYP = K_HQ_BASE_TYP;
        }
        BPCCINTI.BASE_INFO.TENOR = K_HQ_TENOR;
        BPCCINTI.BASE_INFO.CCY = TDCIINT.CCY;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, TDCIINT.CCY);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        WS_DD_RAT = BPCCINTI.BASE_INFO.OWN_RATE;
        CEP.TRC(SCCGWA, WS_DD_RAT);
    }
    public void R001_GET_CARD_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void R000_GET_EXP_DATE() throws IOException,SQLException,Exception {
        if (TDCIINT.TERM.equalsIgnoreCase("S000") 
            && TDCIINT.EXP_DATE == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCIINT.TXN_DATE;
        WS_TERM = TDCIINT.TERM;
        IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES17);
        if (REDEFINES17.WS_TERM_TYP == 'Y' 
            || REDEFINES17.WS_TERM_TYP == 'M') {
            if (REDEFINES17.WS_TERM_TYP == 'Y') {
                SCCCLDT.MTHS = (short) (REDEFINES17.WS_TERM_MTHS * 12);
            } else {
                SCCCLDT.MTHS = REDEFINES17.WS_TERM_MTHS;
            }
        } else {
            SCCCLDT.DAYS = REDEFINES17.WS_TERM_MTHS;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCIINT.EXP_DATE = SCCCLDT.DATE2;
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            WS_ERR_INF = "CALL SCSSCLDT ERRO";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            WS_ERR_INF = "PRODUCT MOD NOT FOUND";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            WS_ERR_INF = "CALL BPZPQPRD ERRO";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_TDZCDINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-DRW-INT", TDCCDINT);
        CEP.TRC(SCCGWA, TDCCDINT.RC_MSG);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDINT.RC_MSG);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("TD1008")) {
            if (TDCCDINT.RC_MSG.RC != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCCDINT.RC_MSG);
                WS_ERR_INF = "CALL TDZCDINT ERRO";
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + CICQACAC.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
        if (TDCPIOD.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPIOD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZCEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-EXP-INT", TDCCEINT);
        CEP.TRC(SCCGWA, TDCCEINT.RC_MSG);
        if (TDCCEINT.RC_MSG.RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCCEINT.RC_MSG);
            WS_ERR_INF = "CALL TDZCEINT ERRO";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_ERR_INF = "CALL TDZCDINT ERRO";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void S00_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCDINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEX);
        BPCEX.BUY_CCY = "840";
        BPCEX.BUY_AMT = WS_BUY_AMT;
        BPCEX.SELL_CCY = TDCIINT.CCY;
        CEP.TRC(SCCGWA, WS_MIN_AMTC);
        CEP.TRC(SCCGWA, TDCIINT.CCY);
        S000_CALL_BPZSEX();
        if (pgmRtn) return;
        WS_SELL_AMT = BPCEX.SELL_AMT;
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            WS_ERR_INF = "READ SMST " + TDCIINT.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SMSTACO() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            WS_ERR_INF = "READ SMST " + TDCIINT.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTPHIS() throws IOException,SQLException,Exception {
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        TDTPHIS_RD.where = "ACO_AC = :TDRPHIS.KEY.ACO_AC";
        TDTPHIS_RD.fst = true;
        TDTPHIS_RD.order = "SETT_DATE DESC";
        IBS.READ(SCCGWA, TDRPHIS, this, TDTPHIS_RD);
    }
    public void T000_READ_CDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            WS_ERR_INF = "TDTCDI:" + TDCIINT.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND ( END_DATE > :TDRIREV.KEY.STR_DATE "
            + "OR END_DATE = 0 )";
        TDTIREV_RD.fst = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.KEY.STR_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        TDTINST_RD.eqWhere = "ACO_AC";
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
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
