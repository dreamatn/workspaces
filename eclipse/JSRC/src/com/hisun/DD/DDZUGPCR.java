package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUGPCR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTGPRS_RD;
    DBParm DDTGPMST_RD;
    DBParm DDTMST_RD;
    brParm DDTGPRS_BR = new brParm();
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_K = 0;
    short WS_MAX = 0;
    String WS_CHK_GPRS_AC = " ";
    String WS_TEMP_AC = " ";
    char WS_NRA_FLG = ' ';
    char WS_GPRS_EOF_FLG = ' ';
    double WS_NRA_LEFT_AMT = 0;
    String WS_TEMP_UP_AC = " ";
    String WS_TEMP_TOP_AC = " ";
    double WS_TEMP_VAL_BAL = 0;
    double WS_TEMP_GP_NRA_LMT = 0;
    double WS_TEMP_GP_CTL_AMT = 0;
    double WS_TEMP_RT_TX_AMT = 0;
    DDZUGPCR_WS_GPRS_INFO_ARRAY[] WS_GPRS_INFO_ARRAY = new DDZUGPCR_WS_GPRS_INFO_ARRAY[5];
    char WS_DDTGPRS_COND = ' ';
    char WS_DDTGPMST_COND = ' ';
    char WS_GP_ALREADY_TOP_FLG = ' ';
    char WS_GP_NRA_LMT_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUDRDD DDCUDRDD = new DDCUDRDD();
    DDCUCRDD DDCUCRDD = new DDCUCRDD();
    CICACCU CICACCU = new CICACCU();
    DDRGPMST DDRGPMST = new DDRGPMST();
    DDRGPRS DDRGPRS = new DDRGPRS();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUGPCR DDCUGPCR;
    public DDZUGPCR() {
        for (int i=0;i<5;i++) WS_GPRS_INFO_ARRAY[i] = new DDZUGPCR_WS_GPRS_INFO_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, DDCUGPCR DDCUGPCR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUGPCR = DDCUGPCR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUGPCR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "======== INPUT DATA ========");
        CEP.TRC(SCCGWA, DDCUGPCR.AC);
        CEP.TRC(SCCGWA, DDCUGPCR.CCY);
        CEP.TRC(SCCGWA, DDCUGPCR.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUGPCR.RT_TX_AMT);
        CEP.TRC(SCCGWA, DDCUGPCR.REAL_TIME_CR);
        CEP.TRC(SCCGWA, DDCUGPCR.TX_MMO);
        CEP.TRC(SCCGWA, DDCUGPCR.TX_REF);
        CEP.TRC(SCCGWA, DDCUGPCR.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUGPCR.REMARKS);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_COLLECTION();
        if (pgmRtn) return;
        B030_COLLECTION_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "======== OUTPUT DATA ========");
        CEP.TRC(SCCGWA, DDCUGPCR.REAL_TIME_CR);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUGPCR.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN, DDCUGPCR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGPCR.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCUGPCR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGPCR.RT_TX_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_WDR_AMT_IN, DDCUGPCR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_COLLECTION() throws IOException,SQLException,Exception {
        DDCUGPCR.REAL_TIME_CR = 'Y';
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUGPCR.AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.FRG_IND.equalsIgnoreCase("NRA")) {
            WS_NRA_FLG = 'Y';
        }
        IBS.init(SCCGWA, DDRGPRS);
        CEP.TRC(SCCGWA, "***** FIRST TIME *****");
        CEP.TRC(SCCGWA, DDCUGPCR.AC);
        DDRGPRS.KEY.AC_NO = DDCUGPCR.AC;
        T000_READ_UPDATE_DDTGPRS();
        if (pgmRtn) return;
        if (WS_DDTGPRS_COND == 'N' 
            || DDRGPRS.REL_STS == 'F' 
            || DDRGPRS.REL_STS == 'D' 
            || DDRGPRS.TOP_FLG == 'Y') {
            CEP.TRC(SCCGWA, "1. NO NEED AMT COLLECTION ! ");
            DDCUGPCR.REAL_TIME_CR = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TEMP_AC = DDRGPRS.KEY.AC_NO;
        WS_TEMP_UP_AC = DDRGPRS.UP_AC;
        CEP.TRC(SCCGWA, WS_TEMP_AC);
        CEP.TRC(SCCGWA, WS_TEMP_UP_AC);
        IBS.init(SCCGWA, DDRGPMST);
        DDRGPMST.KEY.AC_NO = DDCUGPCR.AC;
        DDRGPMST.KEY.CCY = DDCUGPCR.CCY;
        T000_READ_UPDATE_DDTGPMST();
        if (pgmRtn) return;
        if (WS_DDTGPMST_COND == 'N' 
            || DDRGPMST.POOLING_TYP == 'A' 
            || DDRGPMST.POOLING_TYP == '2') {
            CEP.TRC(SCCGWA, "2. NO NEED AMT COLLECTION ! ");
            DDCUGPCR.REAL_TIME_CR = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_I += 1;
            CEP.TRC(SCCGWA, WS_I);
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC = DDRGPRS.KEY.AC_NO;
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC = DDRGPRS.UP_AC;
            WS_CHK_GPRS_AC = DDRGPRS.KEY.AC_NO;
            B020_01_CHK_AC_STATUS();
            if (pgmRtn) return;
            WS_TEMP_GP_CTL_AMT = DDRGPMST.CTRL_BAL;
        }
        while (WS_GP_ALREADY_TOP_FLG != 'Y' 
            && WS_I <= 5) {
            CEP.TRC(SCCGWA, "***** PROCESS CHECKING IN LOOP *****");
            IBS.init(SCCGWA, DDRGPRS);
            CEP.TRC(SCCGWA, WS_TEMP_UP_AC);
            DDRGPRS.KEY.AC_NO = WS_TEMP_UP_AC;
            T000_READ_UPDATE_DDTGPRS();
            if (pgmRtn) return;
            if (WS_DDTGPRS_COND == 'N' 
                || DDRGPRS.REL_STS == 'F' 
                || DDRGPRS.REL_STS == 'D') {
                CEP.TRC(SCCGWA, "2. NO NEED AMT COLLECTION ! ");
                DDCUGPCR.REAL_TIME_CR = 'N';
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DDRGPMST);
                DDRGPMST.KEY.AC_NO = WS_TEMP_UP_AC;
                DDRGPMST.KEY.CCY = DDCUGPCR.CCY;
                T000_READ_UPDATE_DDTGPMST();
                if (pgmRtn) return;
                if (WS_DDTGPMST_COND == 'N') {
                    CEP.TRC(SCCGWA, "<< AC NOT FOUND IN DDTGPMST >>");
                    WS_GP_ALREADY_TOP_FLG = 'Y';
                    WS_TEMP_TOP_AC = WS_TEMP_AC;
                    CEP.TRC(SCCGWA, "<< 2. FOUND TOP GROUP AC >>");
                    CEP.TRC(SCCGWA, WS_TEMP_TOP_AC);
                } else {
                    if (DDRGPRS.TOP_FLG == 'Y' 
                        || DDRGPMST.POOLING_TYP == 'A' 
                        || DDRGPMST.POOLING_TYP == '2') {
                        WS_GP_ALREADY_TOP_FLG = 'Y';
                        WS_TEMP_TOP_AC = WS_TEMP_UP_AC;
                        CEP.TRC(SCCGWA, "<< 3. FOUND TOP GROUP AC >>");
                        CEP.TRC(SCCGWA, WS_TEMP_TOP_AC);
                    } else {
                        WS_I += 1;
                        CEP.TRC(SCCGWA, WS_I);
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC = DDRGPRS.KEY.AC_NO;
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC = DDRGPRS.UP_AC;
                        WS_CHK_GPRS_AC = DDRGPRS.KEY.AC_NO;
                        B020_01_CHK_AC_STATUS();
                        if (pgmRtn) return;
                    }
                    if (DDRGPMST.NRA_LMT > 0 
                        && WS_GP_ALREADY_TOP_FLG == 'Y') {
                        CEP.TRC(SCCGWA, "NEED NRA AMOUNT LIMIT PROCESS");
                        WS_GP_NRA_LMT_FLG = 'Y';
                        WS_TEMP_GP_NRA_LMT = DDRGPMST.NRA_LMT;
                    }
                }
                if (WS_GP_ALREADY_TOP_FLG != 'Y') {
                    WS_TEMP_AC = DDRGPRS.KEY.AC_NO;
                    WS_TEMP_UP_AC = DDRGPRS.UP_AC;
                }
            }
        }
        if (WS_GP_ALREADY_TOP_FLG == 'Y' 
            && WS_NRA_FLG == 'Y') {
            WS_TEMP_GP_CTL_AMT = 0;
            IBS.init(SCCGWA, DDRGPRS);
            DDRGPRS.UP_AC = WS_TEMP_TOP_AC;
            T000_STARTBR_DDTGPRS();
            if (pgmRtn) return;
            T000_READNEXT_DDTGPRS();
            if (pgmRtn) return;
            while (WS_GPRS_EOF_FLG != 'Y') {
                if (DDRGPRS.TOP_FLG != 'Y') {
                    IBS.init(SCCGWA, DDRGPMST);
                    DDRGPMST.KEY.AC_NO = DDRGPRS.KEY.AC_NO;
                    DDRGPMST.KEY.CCY = DDCUGPCR.CCY;
                    T000_READ_DDTGPMST();
                    if (pgmRtn) return;
                    WS_TEMP_GP_CTL_AMT += DDRGPMST.CTRL_BAL;
                }
                T000_READNEXT_DDTGPRS();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTGPRS();
            if (pgmRtn) return;
        }
        WS_CHK_GPRS_AC = WS_TEMP_TOP_AC;
        B020_01_CHK_AC_STATUS();
        if (pgmRtn) return;
        if (WS_I > 5) {
            CEP.TRC(SCCGWA, "WS-I IS NOT GOOD");
            DDCUGPCR.REAL_TIME_CR = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TEMP_GP_NRA_LMT);
        CEP.TRC(SCCGWA, WS_TEMP_GP_CTL_AMT);
        CEP.TRC(SCCGWA, DDCUGPCR.RT_TX_AMT);
        if (WS_GP_NRA_LMT_FLG == 'Y' 
            && WS_NRA_FLG == 'Y') {
            CEP.TRC(SCCGWA, "NRA AMOUNT LIMIT (YES)");
            WS_NRA_LEFT_AMT = WS_TEMP_GP_NRA_LMT - WS_TEMP_GP_CTL_AMT;
            if (WS_NRA_LEFT_AMT > 0) {
                if (WS_NRA_LEFT_AMT >= DDCUGPCR.RT_TX_AMT) {
                    WS_TEMP_RT_TX_AMT = DDCUGPCR.RT_TX_AMT;
                } else {
                    WS_TEMP_RT_TX_AMT = WS_NRA_LEFT_AMT;
                }
            } else {
                CEP.TRC(SCCGWA, "3. NO NEED AMT COLLECTION ! (NRA AMT LIMIT)");
                DDCUGPCR.REAL_TIME_CR = 'N';
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "NRA AMOUNT LIMIT (NO!)");
            WS_TEMP_RT_TX_AMT = DDCUGPCR.RT_TX_AMT;
        }
        CEP.TRC(SCCGWA, WS_TEMP_RT_TX_AMT);
    }
    public void B020_01_CHK_AC_STATUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = WS_CHK_GPRS_AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (((DDRMST.AC_STS != 'N') 
            && (DDRMST.AC_STS != 'W')) 
            || DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "GROUP ACCOUNT STATUS NOT ALLOW ");
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_CHK_GPRS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || CICACCU.DATA.CI_STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1") 
            || CICACCU.DATA.CI_STSW.substring(28 - 1, 28 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "CI STATUS NOT ALLOW CR TX !");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_COLLECTION_PROC() throws IOException,SQLException,Exception {
        WS_MAX = WS_I;
        CEP.TRC(SCCGWA, WS_MAX);
        for (WS_K = 1; WS_K <= WS_MAX; WS_K += 1) {
            CEP.TRC(SCCGWA, "***** PROCESS DR<>CR IN LOOP *****");
            CEP.TRC(SCCGWA, WS_K);
            if (!WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_AC.equalsIgnoreCase(WS_TEMP_TOP_AC)) {
                IBS.init(SCCGWA, DDCUDRDD);
                DDCUDRDD.TX_TYPE = 'T';
                DDCUDRDD.AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_AC;
                DDCUDRDD.STD_AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_AC;
                DDCUDRDD.CCY = DDCUGPCR.CCY;
                DDCUDRDD.CCY_TYPE = DDCUGPCR.CCY_TYPE;
                DDCUDRDD.TX_AMT = WS_TEMP_RT_TX_AMT;
                DDCUDRDD.OTHER_AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_UP_AC;
                DDCUDRDD.OTH_STD_AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_UP_AC;
                DDCUDRDD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCUDRDD.NARRATIVE = DDCUGPCR.NARRATIVE;
                DDCUDRDD.REMARKS = DDCUGPCR.REMARKS;
                DDCUDRDD.TX_MMO = "X16";
                DDCUDRDD.BANK_DR_FLG = 'Y';
                CEP.TRC(SCCGWA, "*---- BEGIN-CALL-DDZUDRDD ----*");
                CEP.TRC(SCCGWA, DDCUDRDD.AC);
                CEP.TRC(SCCGWA, DDCUDRDD.CCY);
                CEP.TRC(SCCGWA, DDCUDRDD.CCY_TYPE);
                CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
                CEP.TRC(SCCGWA, DDCUDRDD.OTHER_AC);
                CEP.TRC(SCCGWA, DDCUDRDD.VAL_DATE);
                CEP.TRC(SCCGWA, DDCUDRDD.NARRATIVE);
                CEP.TRC(SCCGWA, DDCUDRDD.REMARKS);
                CEP.TRC(SCCGWA, DDCUDRDD.TX_MMO);
                S000_CALL_DDZUDRDD();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDCUCRDD);
                DDCUCRDD.TX_TYPE = 'T';
                DDCUCRDD.AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_UP_AC;
                DDCUCRDD.CCY_TYPE = DDCUGPCR.CCY_TYPE;
                DDCUCRDD.OTHER_AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_AC;
                DDCUCRDD.CCY = DDCUGPCR.CCY;
                DDCUCRDD.TX_AMT = WS_TEMP_RT_TX_AMT;
                DDCUCRDD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCUCRDD.NARRATIVE = DDCUGPCR.NARRATIVE;
                DDCUCRDD.REMARKS = DDCUGPCR.REMARKS;
                DDCUCRDD.TX_MMO = "X16";
                DDCUCRDD.BANK_CR_FLG = 'Y';
                CEP.TRC(SCCGWA, "*---- BEGIN-CALL-DDZUCRDD ----*");
                CEP.TRC(SCCGWA, DDCUCRDD.AC);
                CEP.TRC(SCCGWA, DDCUCRDD.CCY_TYPE);
                CEP.TRC(SCCGWA, DDCUCRDD.OTHER_AC);
                CEP.TRC(SCCGWA, DDCUCRDD.CCY);
                CEP.TRC(SCCGWA, DDCUCRDD.TX_AMT);
                CEP.TRC(SCCGWA, DDCUCRDD.VAL_DATE);
                CEP.TRC(SCCGWA, DDCUCRDD.NARRATIVE);
                CEP.TRC(SCCGWA, DDCUCRDD.REMARKS);
                CEP.TRC(SCCGWA, DDCUCRDD.TX_MMO);
                S000_CALL_DDZUCRDD();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DDZUDRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC-DD", DDCUDRDD);
    }
    public void S000_CALL_DDZUCRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC-DD", DDCUCRDD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUGPCR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTGPRS() throws IOException,SQLException,Exception {
        DDTGPRS_RD = new DBParm();
        DDTGPRS_RD.TableName = "DDTGPRS";
        DDTGPRS_RD.upd = true;
        IBS.READ(SCCGWA, DDRGPRS, DDTGPRS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTGPRS_COND = 'N';
            CEP.TRC(SCCGWA, "DDTGPRS: GROUP ACCOUNT RECORD NOT FOUND");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTGPMST() throws IOException,SQLException,Exception {
        null.upd = true;
        DDTGPMST_RD = new DBParm();
        DDTGPMST_RD.TableName = "DDTGPMST";
        IBS.READ(SCCGWA, DDRGPMST, DDTGPMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTGPMST_COND = 'N';
            CEP.TRC(SCCGWA, "DDTGPMST: GROUP ACCOUNT RECORD NOT FOUND");
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTGPMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND, DDCUGPCR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTCCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTGPRS() throws IOException,SQLException,Exception {
        DDTGPRS_BR.rp = new DBParm();
        DDTGPRS_BR.rp.TableName = "DDTGPRS";
        DDTGPRS_BR.rp.where = "UP_AC = :DDRGPRS.UP_AC";
        IBS.STARTBR(SCCGWA, DDRGPRS, this, DDTGPRS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTGPRS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRGPRS, this, DDTGPRS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GPRS_EOF_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GPRS_EOF_FLG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPRS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTGPRS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTGPRS_BR);
    }
    public void T000_READ_DDTGPMST() throws IOException,SQLException,Exception {
        DDTGPMST_RD = new DBParm();
        DDTGPMST_RD.TableName = "DDTGPMST";
        IBS.READ(SCCGWA, DDRGPMST, DDTGPMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTGPMST_COND = 'N';
            CEP.TRC(SCCGWA, "DDTGPMST: GROUP ACCOUNT RECORD NOT FOUND");
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTGPMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
