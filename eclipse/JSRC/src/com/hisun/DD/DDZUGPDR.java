package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUGPDR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTGPRS_RD;
    DBParm DDTGPMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_K = 0;
    short WS_MAX = 0;
    String WS_TEMP_AC = " ";
    String WS_TEMP_UP_AC = " ";
    String WS_TEMP_TOP_AC = " ";
    double WS_TEMP_VAL_BAL = 0;
    double WS_TEMP_CAL_BAL1 = 0;
    double WS_TEMP_CAL_BAL2 = 0;
    double WS_TEMP_TOP_AVA_BAL = 0;
    double WS_TEMP_MIN_BAL = 0;
    String WS_CHK_BAL_AC = " ";
    String WS_CHK_GPRS_AC = " ";
    double WS_MIN_AVA_BAL = 0;
    double WS_GPRS_UP_AC_BAL = 0;
    DDZUGPDR_WS_GPRS_INFO_ARRAY[] WS_GPRS_INFO_ARRAY = new DDZUGPDR_WS_GPRS_INFO_ARRAY[5];
    char WS_DDTGPRS_COND = ' ';
    char WS_DDTGPMST_COND = ' ';
    char WS_GP_ALREADY_TOP_FLG = ' ';
    char WS_START_DRCR_LOOP_FLG = ' ';
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
    DDCUGPDR DDCUGPDR;
    public DDZUGPDR() {
        for (int i=0;i<5;i++) WS_GPRS_INFO_ARRAY[i] = new DDZUGPDR_WS_GPRS_INFO_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, DDCUGPDR DDCUGPDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUGPDR = DDCUGPDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUGPDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "======== INPUT DATA ========");
        CEP.TRC(SCCGWA, DDCUGPDR.AC);
        CEP.TRC(SCCGWA, DDCUGPDR.CCY);
        CEP.TRC(SCCGWA, DDCUGPDR.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUGPDR.RT_DR_AMT);
        CEP.TRC(SCCGWA, DDCUGPDR.REAL_TIME_WDR);
        CEP.TRC(SCCGWA, DDCUGPDR.TX_MMO);
        CEP.TRC(SCCGWA, DDCUGPDR.TX_REF);
        CEP.TRC(SCCGWA, DDCUGPDR.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUGPDR.REMARKS);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_LINKAGE_WDR();
        if (pgmRtn) return;
        B030_LINKAGE_WITHDRAW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "======== OUTPUT DATA ========");
        CEP.TRC(SCCGWA, DDCUGPDR.REAL_TIME_WDR);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUGPDR.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN, DDCUGPDR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGPDR.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCUGPDR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGPDR.RT_DR_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_WDR_AMT_IN, DDCUGPDR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_LINKAGE_WDR() throws IOException,SQLException,Exception {
        DDCUGPDR.REAL_TIME_WDR = 'Y';
        IBS.init(SCCGWA, DDRGPRS);
        CEP.TRC(SCCGWA, "***** FIRST TIME *****");
        CEP.TRC(SCCGWA, DDCUGPDR.AC);
        DDRGPRS.KEY.AC_NO = DDCUGPDR.AC;
        T000_READ_UPDATE_DDTGPRS();
        if (pgmRtn) return;
        if (WS_DDTGPRS_COND == 'N' 
            || DDRGPRS.REL_STS == 'F' 
            || DDRGPRS.REL_STS == 'D' 
            || DDRGPRS.TOP_FLG == 'Y') {
            CEP.TRC(SCCGWA, "1. CAN NOT WITHDRAW MONEY ! ");
            DDCUGPDR.REAL_TIME_WDR = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TEMP_AC = DDRGPRS.KEY.AC_NO;
        WS_TEMP_UP_AC = DDRGPRS.UP_AC;
        CEP.TRC(SCCGWA, WS_TEMP_AC);
        CEP.TRC(SCCGWA, WS_TEMP_UP_AC);
        IBS.init(SCCGWA, DDRGPMST);
        DDRGPMST.KEY.AC_NO = DDCUGPDR.AC;
        DDRGPMST.KEY.CCY = DDCUGPDR.CCY;
        T000_READ_UPDATE_DDTGPMST();
        if (pgmRtn) return;
        if (WS_DDTGPMST_COND == 'N' 
            || DDRGPMST.POOLING_TYP == 'A' 
            || (DDRGPMST.POOLING_TYP == '2' 
            && DDRGPMST.PAY_MTH == '2')) {
            CEP.TRC(SCCGWA, "2. CAN NOT WITHDRAW MONEY ! ");
            CEP.TRC(SCCGWA, DDRGPMST.POOLING_TYP);
            DDCUGPDR.REAL_TIME_WDR = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_I += 1;
            CEP.TRC(SCCGWA, WS_I);
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC = DDRGPRS.KEY.AC_NO;
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC = DDRGPRS.UP_AC;
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_CTRL_BAL = DDRGPMST.CTRL_BAL;
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_AUT_BAL = DDRGPMST.AUT_BAL;
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_POOLING_TYP = DDRGPMST.POOLING_TYP;
            WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_CTRL_MTH = DDRGPMST.CTRL_MTH;
            if (DDRGPMST.POOLING_TYP == '2' 
                && DDRGPMST.PAY_MTH == '1') {
                WS_GP_ALREADY_TOP_FLG = 'Y';
                WS_TEMP_TOP_AC = DDRGPRS.UP_AC;
                CEP.TRC(SCCGWA, "<< 0. FOUND TOP GROUP AC >>");
                CEP.TRC(SCCGWA, WS_TEMP_TOP_AC);
            }
            WS_CHK_GPRS_AC = DDRGPRS.KEY.AC_NO;
            B020_03_CHK_AC_STATUS();
            if (pgmRtn) return;
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
                CEP.TRC(SCCGWA, "2. CAN NOT WITHDRAW MONEY ! ");
                DDCUGPDR.REAL_TIME_WDR = 'N';
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DDRGPMST);
                DDRGPMST.KEY.AC_NO = WS_TEMP_UP_AC;
                DDRGPMST.KEY.CCY = DDCUGPDR.CCY;
                T000_READ_UPDATE_DDTGPMST();
                if (pgmRtn) return;
                if (WS_DDTGPMST_COND == 'N') {
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
                        CEP.TRC(SCCGWA, DDRGPMST.POOLING_TYP);
                        CEP.TRC(SCCGWA, WS_TEMP_TOP_AC);
                    } else {
                        WS_I += 1;
                        CEP.TRC(SCCGWA, WS_I);
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC = DDRGPRS.KEY.AC_NO;
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC = DDRGPRS.UP_AC;
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_CTRL_BAL = DDRGPMST.CTRL_BAL;
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_AUT_BAL = DDRGPMST.AUT_BAL;
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_POOLING_TYP = DDRGPMST.POOLING_TYP;
                        WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPMST_CTRL_MTH = DDRGPMST.CTRL_MTH;
                        WS_CHK_GPRS_AC = DDRGPRS.KEY.AC_NO;
                        B020_03_CHK_AC_STATUS();
                        if (pgmRtn) return;
                    }
                }
                if (WS_GP_ALREADY_TOP_FLG != 'Y') {
                    WS_TEMP_AC = DDRGPRS.KEY.AC_NO;
                    WS_TEMP_UP_AC = DDRGPRS.UP_AC;
                }
            }
        }
        WS_CHK_GPRS_AC = WS_TEMP_TOP_AC;
        B020_03_CHK_AC_STATUS();
        if (pgmRtn) return;
        if (WS_I > 5) {
            CEP.TRC(SCCGWA, "WS-I IS NOT GOOD");
            DDCUGPDR.REAL_TIME_WDR = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_MAX = WS_I;
        CEP.TRC(SCCGWA, WS_MAX);
        WS_CHK_BAL_AC = WS_TEMP_TOP_AC;
        B020_01_CHK_AC_AVA_BAL();
        if (pgmRtn) return;
        WS_TEMP_TOP_AVA_BAL = WS_TEMP_VAL_BAL;
        CEP.TRC(SCCGWA, WS_TEMP_TOP_AVA_BAL);
        if (WS_TEMP_TOP_AC.equalsIgnoreCase(WS_GPRS_INFO_ARRAY[1-1].WS_GPRS_AC)) {
            CEP.TRC(SCCGWA, "FIRST LEVEL ACCOUNT IS TOP ACCOUNT !");
            DDCUGPDR.REAL_TIME_WDR = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            for (WS_K = 1; WS_K <= WS_MAX; WS_K += 1) {
                CEP.TRC(SCCGWA, "***** CHECK MIN AVA BAL IN LOOP *****");
                CEP.TRC(SCCGWA, WS_K);
                B020_02_CAL_MIN_AVA_BAL();
                if (pgmRtn) return;
                if (WS_K == 1) {
                    WS_MIN_AVA_BAL = WS_TEMP_MIN_BAL;
                } else {
                    if (WS_TEMP_MIN_BAL < WS_MIN_AVA_BAL) {
                        WS_MIN_AVA_BAL = WS_TEMP_MIN_BAL;
                    }
                }
                CEP.TRC(SCCGWA, WS_MIN_AVA_BAL);
            }
        }
        if (WS_TEMP_TOP_AVA_BAL < WS_MIN_AVA_BAL) {
            WS_MIN_AVA_BAL = WS_TEMP_TOP_AVA_BAL;
        }
        if (WS_GP_ALREADY_TOP_FLG == 'Y' 
            && !DDCUGPDR.AC.equalsIgnoreCase(WS_TEMP_TOP_AC)) {
            if (WS_MIN_AVA_BAL < DDCUGPDR.RT_DR_AMT) {
                CEP.TRC(SCCGWA, "GROUP NET HAS NO ENOUGH BALANCE !");
                DDCUGPDR.REAL_TIME_WDR = 'N';
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_01_CHK_AC_AVA_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHK_BAL_AC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = WS_CHK_BAL_AC;
        DDRCCY.CCY = DDCUGPDR.CCY;
        DDRCCY.CCY_TYPE = DDCUGPDR.CCY_TYPE;
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
        WS_TEMP_VAL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        CEP.TRC(SCCGWA, WS_TEMP_VAL_BAL);
    }
    public void B020_02_CAL_MIN_AVA_BAL() throws IOException,SQLException,Exception {
        WS_CHK_BAL_AC = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPRS_UP_AC;
        B020_01_CHK_AC_AVA_BAL();
        if (pgmRtn) return;
        WS_GPRS_UP_AC_BAL = WS_TEMP_VAL_BAL;
        CEP.TRC(SCCGWA, WS_GPRS_UP_AC_BAL);
        if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_MTH == '1') {
            CEP.TRC(SCCGWA, "CTRL_MTH = 1 ");
            CEP.TRC(SCCGWA, WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL);
            if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL < 0) {
                WS_TEMP_MIN_BAL = 0;
            } else {
                if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_POOLING_TYP == '1') {
                    WS_TEMP_MIN_BAL = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL;
                } else {
                    if (WS_GPRS_UP_AC_BAL < WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL) {
                        WS_TEMP_MIN_BAL = WS_GPRS_UP_AC_BAL;
                    } else {
                        WS_TEMP_MIN_BAL = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL;
                    }
                }
            }
        } else if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_MTH == '2') {
            CEP.TRC(SCCGWA, "CTRL_MTH = 2 ");
            if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_POOLING_TYP == '1') {
                WS_TEMP_MIN_BAL = WS_TEMP_TOP_AVA_BAL;
            } else {
                WS_TEMP_MIN_BAL = WS_GPRS_UP_AC_BAL;
            }
        } else if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_MTH == '3') {
            CEP.TRC(SCCGWA, "CTRL_MTH = 3 ");
            if (null + null < 0) {
                WS_TEMP_MIN_BAL = 0;
            } else {
                if (WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_POOLING_TYP == '1') {
                    WS_TEMP_MIN_BAL = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL + WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_AUT_BAL;
                } else {
                    WS_TEMP_CAL_BAL1 = WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_CTRL_BAL + WS_GPRS_INFO_ARRAY[WS_K-1].WS_GPMST_AUT_BAL;
                    WS_TEMP_CAL_BAL2 = WS_GPRS_UP_AC_BAL;
                    CEP.TRC(SCCGWA, WS_TEMP_CAL_BAL1);
                    CEP.TRC(SCCGWA, WS_TEMP_CAL_BAL2);
                    if (WS_TEMP_CAL_BAL1 < WS_TEMP_CAL_BAL2) {
                        WS_TEMP_MIN_BAL = WS_TEMP_CAL_BAL1;
                    } else {
                        WS_TEMP_MIN_BAL = WS_TEMP_CAL_BAL2;
                    }
                }
            }
        } else {
            WS_TEMP_MIN_BAL = 0;
        }
        CEP.TRC(SCCGWA, WS_TEMP_MIN_BAL);
        if (WS_TEMP_MIN_BAL < 0) {
            WS_TEMP_MIN_BAL = 0;
        }
    }
    public void B020_03_CHK_AC_STATUS() throws IOException,SQLException,Exception {
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
        if ((DDRMST.AC_STS != 'N' 
            && DDRMST.AC_STS != 'W') 
            || DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "ACCOUNT STATUS NOT ALLOW DR TX !");
            DDCUGPDR.REAL_TIME_WDR = 'N';
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
            CEP.TRC(SCCGWA, "CI STATUS NOT ALLOW DR TX !");
            DDCUGPDR.REAL_TIME_WDR = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_LINKAGE_WITHDRAW() throws IOException,SQLException,Exception {
        WS_MAX = WS_I;
        CEP.TRC(SCCGWA, WS_MAX);
        WS_START_DRCR_LOOP_FLG = 'N';
        for (WS_K = 1; WS_K <= WS_MAX; WS_K += 1) {
            CEP.TRC(SCCGWA, "***** PROCESS DR<>CR IN LOOP *****");
            CEP.TRC(SCCGWA, WS_K);
            if (WS_TEMP_TOP_AC.equalsIgnoreCase(WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC)) {
                WS_START_DRCR_LOOP_FLG = 'Y';
            }
            if (WS_START_DRCR_LOOP_FLG == 'Y') {
                IBS.init(SCCGWA, DDCUDRDD);
                DDCUDRDD.TX_TYPE = 'T';
                DDCUDRDD.AC = WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC;
                DDCUDRDD.STD_AC = WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC;
                DDCUDRDD.CCY = DDCUGPDR.CCY;
                DDCUDRDD.CCY_TYPE = DDCUGPDR.CCY_TYPE;
                DDCUDRDD.TX_AMT = DDCUGPDR.RT_DR_AMT;
                DDCUDRDD.OTHER_AC = WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC;
                DDCUDRDD.OTH_STD_AC = WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC;
                DDCUDRDD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCUDRDD.NARRATIVE = DDCUGPDR.NARRATIVE;
                DDCUDRDD.REMARKS = DDCUGPDR.REMARKS;
                DDCUDRDD.TX_MMO = "X15";
                DDCUDRDD.BANK_DR_FLG = 'Y';
                S000_CALL_DDZUDRDD();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDCUCRDD);
                DDCUCRDD.TX_TYPE = 'T';
                DDCUCRDD.AC = WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_AC;
                DDCUCRDD.CCY_TYPE = DDCUGPDR.CCY_TYPE;
                DDCUCRDD.OTHER_AC = WS_GPRS_INFO_ARRAY[WS_I-1].WS_GPRS_UP_AC;
                DDCUCRDD.CCY = DDCUGPDR.CCY;
                DDCUCRDD.TX_AMT = DDCUGPDR.RT_DR_AMT;
                DDCUCRDD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCUCRDD.NARRATIVE = DDCUGPDR.NARRATIVE;
                DDCUCRDD.REMARKS = DDCUGPDR.REMARKS;
                DDCUCRDD.TX_MMO = "X15";
                DDCUCRDD.BANK_CR_FLG = 'Y';
                S000_CALL_DDZUCRDD();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_I);
            WS_I = (short) (WS_I - 1);
        }
    }
    public void S000_CALL_DDZUDRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC-DD", DDCUDRDD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUGPDR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC-DD", DDCUCRDD);
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
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_REC_NOTFND, DDCUGPDR.RC);
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
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND, DDCUGPDR.RC);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
