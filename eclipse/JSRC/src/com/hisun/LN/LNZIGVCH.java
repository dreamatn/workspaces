package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIGVCH {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_MAX_AMT = 60;
    String K_DAILY_ACCRUAL = "LN01";
    String K_OVD_PROC = "LN02";
    String K_DAILY_REVERSAL = "LN03";
    String K_TONOACR_TNA = "LN04";
    String K_TONOACR_TAC = "LN05";
    String K_AP_MMO = "LN";
    String K_CPN_RSC_LNZRCMMT = "LN-RSC-LNTCMMT";
    short K_RATE_DIF_INCOME = 9999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IND = 0;
    short WS_CNT = 0;
    double WS_BAL_NORMAL = 0;
    double WS_TMP_AMT = 0;
    double WS_TMP_BLOCK_AMT = 0;
    LNZIGVCH_WS_SUB_CTA_NO WS_SUB_CTA_NO = new LNZIGVCH_WS_SUB_CTA_NO();
    LNZIGVCH_WS_MSG_INFO WS_MSG_INFO = new LNZIGVCH_WS_MSG_INFO();
    LNZIGVCH_WS_AMT_INFO[] WS_AMT_INFO = new LNZIGVCH_WS_AMT_INFO[60];
    char WS_TRAN_FLG = ' ';
    char WS_PLPI_FLG = ' ';
    char WS_RLN_FLG = ' ';
    char WS_CMMT_FLG = ' ';
    char WS_CMMT_AVAIL_FLG = ' ';
    char WS_VCH_FLG = ' ';
    char WS_AMT_UPD_FLG = ' ';
    char WS_AMT_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCUGMC BPCUGMC = new BPCUGMC();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCRTRAN LNCRTRAN = new LNCRTRAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCIPART LNCIPART = new LNCIPART();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    SCCBATH SCCBATH;
    LNCIGVCH LNCIGVCH;
    public LNZIGVCH() {
        for (int i=0;i<60;i++) WS_AMT_INFO[i] = new LNZIGVCH_WS_AMT_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCIGVCH LNCIGVCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIGVCH = LNCIGVCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIGVCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GEN_VCH();
        if (pgmRtn) return;
        B030_CLCM_REVERSAL_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CNTR_TYPE);
        if (!LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLCM") 
            && !LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            && !LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLGU") 
            && !LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLDL") 
            && !LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("IDC")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONTRACT_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.PROD_CODE_OLD);
        if (LNCIGVCH.DATA.PROD_CODE_OLD.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CTA_NO);
        if (LNCIGVCH.DATA.CTA_NO.equalsIgnoreCase("0")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EVENT_CODE);
        if (LNCIGVCH.DATA.EVENT_CODE.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_EVN_CODE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BR_OLD);
        if (LNCIGVCH.DATA.BR_OLD == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CCY_INFO[1-1].CCY);
        if (LNCIGVCH.DATA.CCY_INFO[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.VALUE_DATE);
        if (LNCIGVCH.DATA.VALUE_DATE == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_VALUE_DATE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GEN_VCH() throws IOException,SQLException,Exception {
        for (WS_IND = 1; WS_IND <= 60; WS_IND += 1) {
            CEP.TRC(SCCGWA, WS_IND);
            CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[WS_IND-1].AMT);
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B020_01_GEN_BAT_VCH();
            if (pgmRtn) return;
        } else {
            B020_02_GEN_ONL_VCH();
            if (pgmRtn) return;
        }
    }
    public void B020_01_GEN_BAT_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBWEVT);
        BPCBWEVT.INFO.AP_MMO = " ";
        BPCBWEVT.INFO.TR_CODE = " ";
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        if (JIBS_sdf.format(JIBS_date).substring(0, 8).trim().length() == 0) BPCBWEVT.INFO.TR_DATE = 0;
        else BPCBWEVT.INFO.TR_DATE = Integer.parseInt(JIBS_sdf.format(JIBS_date).substring(0, 8));
        if (JIBS_sdf.format(JIBS_date).substring(8, 16).trim().length() == 0) BPCBWEVT.INFO.TR_TIME = 0;
        else BPCBWEVT.INFO.TR_TIME = Integer.parseInt(JIBS_sdf.format(JIBS_date).substring(8, 16));
        CEP.TRC(SCCGWA, "111");
        BPCBWEVT.INFO.TR_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPCBWEVT.INFO.TR_BR = LNCIGVCH.DATA.BR_OLD;
        BPCBWEVT.INFO.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCBWEVT.INFO.EVENT.CNTR_TYPE = LNCIGVCH.DATA.CNTR_TYPE;
        BPCBWEVT.INFO.EVENT.PROD_CODE = LNCIGVCH.DATA.PROD_CODE_OLD;
        BPCBWEVT.INFO.EVENT.AC_NO = LNCIGVCH.DATA.CTA_NO;
        BPCBWEVT.INFO.EVENT.EVENT_CODE = LNCIGVCH.DATA.EVENT_CODE;
        BPCBWEVT.INFO.EVENT.BR_OLD = LNCIGVCH.DATA.BR_OLD;
        BPCBWEVT.INFO.EVENT.EVENT_CCY[1-1].CCY = LNCIGVCH.DATA.CCY_INFO[1-1].CCY;
        BPCBWEVT.INFO.EVENT.VAL_DATE = LNCIGVCH.DATA.VALUE_DATE;
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            BPCBWEVT.INFO.EVENT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCBWEVT.INFO.EVENT.CI_NO = LNCIGVCH.DATA.CI_NO;
        CEP.TRC(SCCGWA, "222");
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.SUB_CTA_NO);
        BPCBWEVT.INFO.EVENT.REF_NO = " ";
        CEP.TRC(SCCGWA, BPCBWEVT.INFO.EVENT.REF_NO);
        CEP.TRC(SCCGWA, "223");
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.PORT);
        BPCBWEVT.INFO.EVENT.PORTFO_CD = LNCIGVCH.DATA.PORT;
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EFF_DAYS);
        BPCBWEVT.INFO.EVENT.EFF_DAYS = LNCIGVCH.DATA.EFF_DAYS;
        CEP.TRC(SCCGWA, "224");
        BPCBWEVT.CLO_FILE_FLG = LNCIGVCH.DATA.CLO_FILE_FLG;
        CEP.TRC(SCCGWA, "333");
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            if (LNCIGVCH.DATA.STATUS.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("0")) {
                BPCBWEVT.INFO.SET_NO.BFVCH_CD = K_DAILY_ACCRUAL;
            } else {
                BPCBWEVT.INFO.SET_NO.BFVCH_CD = K_DAILY_REVERSAL;
            }
            R000_EFF_DAYS_PROC();
            if (pgmRtn) return;
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("NO")) {
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = K_OVD_PROC;
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("TNA")) {
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = K_TONOACR_TNA;
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("TAC")) {
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = K_TONOACR_TAC;
        }
        R000_TRANS_AMT_TO_AC_MODEL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_NORMAL);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_OVERDUE_MANUAL);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_OVERDUE);
        CEP.TRC(SCCGWA, "444");
        WS_AMT_FLG = 'N';
        for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
            BPCBWEVT.INFO.EVENT.EVENT_AMT[WS_IND-1].AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
            if (BPCBWEVT.INFO.EVENT.EVENT_AMT[WS_IND-1].AMT > 0) {
                WS_AMT_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_IND);
            CEP.TRC(SCCGWA, WS_AMT_INFO[WS_IND-1].WS_AMT);
        }
        CEP.TRC(SCCGWA, "555");
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            BPCBWEVT.INFO.EVENT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCBWEVT.INFO.EVENT.AC_NO_REL = LNCIGVCH.DATA.CTA_NO;
        if (WS_AMT_FLG == 'Y') {
            S000_CALL_BPZBWEVT();
            if (pgmRtn) return;
        }
    }
    public void B020_02_GEN_ONL_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = LNCIGVCH.DATA.CNTR_TYPE;
        BPCPOEWA.DATA.PROD_CODE = LNCIGVCH.DATA.PROD_CODE_OLD;
        BPCPOEWA.DATA.AC_NO = LNCIGVCH.DATA.CTA_NO;
        BPCPOEWA.DATA.EVENT_CODE = LNCIGVCH.DATA.EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = LNCIGVCH.DATA.BR_OLD;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = LNCIGVCH.DATA.CCY_INFO[1-1].CCY;
        BPCPOEWA.DATA.VALUE_DATE = LNCIGVCH.DATA.VALUE_DATE;
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CI_NO);
        BPCPOEWA.DATA.CI_NO = LNCIGVCH.DATA.CI_NO;
        BPCPOEWA.DATA.REF_NO = " ";
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.REF_NO);
        BPCPOEWA.DATA.PORT = LNCIGVCH.DATA.PORT;
        BPCPOEWA.DATA.EFF_DAYS = 0;
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            if (LNCIGVCH.DATA.STATUS.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                BPCPOEWA.DATA.EVENT_CODE = "MA";
            }
        }
        R000_TRANS_AMT_TO_AC_MODEL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_NORMAL);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_OVERDUE_MANUAL);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_OVERDUE);
        WS_AMT_FLG = 'N';
        for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
            BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
            if (BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT > 0) {
                WS_AMT_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_IND);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT);
        }
        BPCPOEWA.DATA.AC_NO_REL = LNCIGVCH.DATA.CTA_NO;
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("GC") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("BC")) {
            IBS.init(SCCGWA, BPCUGMC);
            BPCUGMC.INFO.CNTR_TYPE = LNCIGVCH.DATA.CNTR_TYPE;
            BPCUGMC.INFO.CCY_INFO[1-1].CCY = LNCIGVCH.DATA.CCY_INFO[1-1].CCY;
            BPCUGMC.INFO.CI_NO = LNCIGVCH.DATA.CI_NO;
            BPCUGMC.INFO.AC_NO = LNCIGVCH.DATA.CTA_NO;
            IBS.init(SCCGWA, BPCACLDD);
            BPCACLDD.PROD_CD = LNCIGVCH.DATA.PROD_CODE_OLD;
            BPCUGMC.INFO.OTH_PTR_LEN = 31;
            BPCUGMC.INFO.OTH_PTR_OLD = BPCACLDD;
            IBS.init(SCCGWA, BPCACLDD);
            BPCACLDD.PROD_CD = LNCIGVCH.DATA.PROD_CODE_OLD;
            BPCUGMC.INFO.OTH_PTR_NEW = BPCACLDD;
            for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
                BPCUGMC.INFO.AMTS[WS_IND-1].AMT = BPCPOEWA.DATA.AMT_INFO[WS_IND-1].AMT;
                CEP.TRC(SCCGWA, WS_AMT_INFO[WS_IND-1].WS_AMT);
            }
            S000_CALL_BPZUGMC();
            if (pgmRtn) return;
        } else {
            if (WS_AMT_FLG == 'Y') {
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_CLCM_REVERSAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLM") 
            && LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RP") 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, LNRCMMT);
            IBS.init(SCCGWA, LNCRCMMT);
            LNRCMMT.KEY.CONTRACT_NO = LNCIGVCH.DATA.CTA_NO;
            LNCRCMMT.FUNC = 'R';
            S000_CALL_LNZRCMMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CMMT_FLG);
            if (WS_CMMT_FLG == 'Y') {
                if (LNRCMMT.AVAIL_END_DATE > SCCGWA.COMM_AREA.AC_DATE 
                    || (LNRCMMT.AVAIL_END_DATE == SCCGWA.COMM_AREA.AC_DATE)) {
                    WS_CMMT_AVAIL_FLG = 'N';
                } else {
                    WS_CMMT_AVAIL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, WS_CMMT_AVAIL_FLG);
                CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
                CEP.TRC(SCCGWA, "AFT VWA-CNT");
                WS_VCH_FLG = 'N';
                for (WS_CNT = 1; WS_CNT <= BPRVWA.BASIC_AREA.CNT; WS_CNT += 1) {
                    if (BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.AC_NO.equalsIgnoreCase(LNCIGVCH.DATA.CTA_NO) 
                        && BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.EVENT_CODE.equalsIgnoreCase("RP")) {
                        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.AMT);
                        WS_TMP_BLOCK_AMT = BPRVWA.VCH_AREA.get(WS_CNT-1).PARTB.AMT;
                        WS_VCH_FLG = 'Y';
                    }
                }
                CEP.TRC(SCCGWA, WS_VCH_FLG);
                CEP.TRC(SCCGWA, WS_CMMT_AVAIL_FLG);
                CEP.TRC(SCCGWA, LNRCMMT.REVOLVING_FLAG);
                CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[1-1].AMT);
                CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[2-1].AMT);
                WS_AMT_UPD_FLG = 'N';
                if ((WS_VCH_FLG == 'Y' 
                    && WS_CMMT_AVAIL_FLG == 'Y') 
                    || (WS_VCH_FLG == 'N' 
                    && WS_CMMT_AVAIL_FLG == 'N')) {
                    if (LNCIGVCH.DATA.AMT_INFO[1-1].AMT != 0 
                        && (LNRCMMT.REVOLVING_FLAG != 'N' 
                        || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 4019)) {
                        if (WS_VCH_FLG == 'Y') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                        if (WS_VCH_FLG == 'N') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                    }
                    if (LNCIGVCH.DATA.AMT_INFO[2-1].AMT != 0) {
                        CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[2-1].AMT);
                        CEP.TRC(SCCGWA, WS_TMP_BLOCK_AMT);
                        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 2229) {
                            if (WS_TMP_BLOCK_AMT > 0) {
                                LNCIGVCH.DATA.AMT_INFO[2-1].AMT = WS_TMP_BLOCK_AMT;
                            }
                        }
                        CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[2-1].AMT);
                        if (WS_VCH_FLG == 'Y') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                        if (WS_VCH_FLG == 'N') {
                            WS_AMT_UPD_FLG = 'Y';
                        }
                    }
                    CEP.TRC(SCCGWA, WS_AMT_UPD_FLG);
                    if (WS_AMT_UPD_FLG == 'Y') {
                        LNCRCMMT.FUNC = 'U';
                        S000_CALL_LNZRCMMT();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_TRANS_AMT_TO_AC_MODEL() throws IOException,SQLException,Exception {
        if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLCM")) {
            R000_TRANS_CLCM_AMT();
            if (pgmRtn) return;
        } else if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLDD")) {
            R000_TRANS_CLDD_AMT();
            if (pgmRtn) return;
            R000_NPL_AMT_PROC();
            if (pgmRtn) return;
            R000_SYNLOAN_AMT_PROC();
            if (pgmRtn) return;
        } else if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLGU")) {
            R000_TRANS_CLGU_AMT();
            if (pgmRtn) return;
        } else if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLDL")) {
            R000_TRANS_CLDD_AMT();
            if (pgmRtn) return;
            R000_NPL_AMT_PROC();
            if (pgmRtn) return;
        } else if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("IDC")) {
            R000_TRANS_CLDD_AMT();
            if (pgmRtn) return;
            R000_NPL_AMT_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONTRACT_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_CLCM_AMT() throws IOException,SQLException,Exception {
        WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
        WS_BAL_NORMAL = LNCIGVCH.DATA.BAL_NORMAL;
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RP")) {
            if (LNCIGVCH.DATA.BAL_NORMAL < 0) {
                LNCIGVCH.DATA.BAL_NORMAL = 0;
            }
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL - LNCIGVCH.DATA.AMT_INFO[1-1].AMT + LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
            if (LNCIGVCH.DATA.AMT_INFO[1-1].AMT > 0) {
                if (LNCIGVCH.DATA.AMT_INFO[1-1].AMT > LNCIGVCH.DATA.BAL_NORMAL) {
                    WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL;
                    WS_AMT_INFO[2-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL + LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
                } else {
                    WS_AMT_INFO[2-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL;
                    WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL - LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
                }
            }
            if (LNCIGVCH.DATA.AMT_INFO[2-1].AMT > 0) {
                WS_AMT_INFO[2-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL;
                WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL + LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
            }
        }
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, WS_BAL_NORMAL);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[2-1].AMT);
        if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RP")) {
            if (LNCIGVCH.DATA.AMT_INFO[1-1].AMT < 0 
                || LNCIGVCH.DATA.AMT_INFO[2-1].AMT < 0) {
                WS_AMT_INFO[1-1].WS_AMT = 0;
                WS_AMT_INFO[2-1].WS_AMT = 0;
            }
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RP")) {
            if (WS_BAL_NORMAL < 0) {
                if (LNCIGVCH.DATA.AMT_INFO[1-1].AMT > 0) {
                    WS_AMT_INFO[1-1].WS_AMT = 0;
                    WS_AMT_INFO[2-1].WS_AMT = 0;
                }
                if (LNCIGVCH.DATA.AMT_INFO[2-1].AMT > 0) {
                    WS_TMP_AMT = WS_BAL_NORMAL * -1;
                    if (WS_TMP_AMT > LNCIGVCH.DATA.AMT_INFO[2-1].AMT) {
                        WS_AMT_INFO[1-1].WS_AMT = 0;
                    } else {
                        WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[2-1].AMT - WS_TMP_AMT;
                    }
                    WS_AMT_INFO[2-1].WS_AMT = 0;
                }
            }
            if (WS_BAL_NORMAL >= 0) {
                if (LNCIGVCH.DATA.AMT_INFO[1-1].AMT > 0) {
                    if (WS_BAL_NORMAL - null < 0) {
                        WS_AMT_INFO[2-1].WS_AMT = WS_BAL_NORMAL;
                        WS_AMT_INFO[1-1].WS_AMT = 0;
                    } else {
                        WS_AMT_INFO[2-1].WS_AMT = WS_BAL_NORMAL;
                        WS_AMT_INFO[1-1].WS_AMT = WS_BAL_NORMAL - LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
                    }
                }
                if (LNCIGVCH.DATA.AMT_INFO[2-1].AMT > 0) {
                    WS_AMT_INFO[1-1].WS_AMT = WS_BAL_NORMAL + LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
                    WS_AMT_INFO[2-1].WS_AMT = WS_BAL_NORMAL;
                }
            }
            WS_AMT_INFO[2-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
        }
    }
    public void R000_TRANS_CLDD_AMT() throws IOException,SQLException,Exception {
        WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
        WS_AMT_INFO[5-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
        WS_AMT_INFO[13-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[6-1].AMT;
        WS_AMT_INFO[15-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[7-1].AMT;
        WS_AMT_INFO[17-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[8-1].AMT;
        WS_AMT_INFO[17-1].WS_AMT += LNCIGVCH.DATA.AMT_INFO[9-1].AMT;
        WS_AMT_INFO[12-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[12-1].AMT;
        WS_AMT_INFO[23-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[13-1].AMT;
        WS_AMT_INFO[27-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[14-1].AMT;
        WS_AMT_INFO[31-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[15-1].AMT;
        WS_AMT_INFO[35-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[16-1].AMT;
        WS_AMT_INFO[39-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[20-1].AMT;
        WS_AMT_INFO[41-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[21-1].AMT;
        WS_AMT_INFO[55-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[23-1].AMT;
        WS_AMT_INFO[56-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[24-1].AMT;
        WS_AMT_INFO[53-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[53-1].AMT;
        WS_AMT_INFO[54-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[54-1].AMT;
        WS_AMT_INFO[58-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[58-1].AMT;
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.STATUS);
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            if (LNCIGVCH.DATA.STATUS.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                BPCPOEWA.DATA.EVENT_CODE = "MA";
                LNCIGVCH.DATA.EVENT_CODE = "MA";
            }
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL;
            WS_AMT_INFO[3-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL + LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[5-1].WS_AMT = LNCIGVCH.DATA.BAL_OVERDUE_MANUAL + LNCIGVCH.DATA.BAL_OVERDUE;
            WS_AMT_INFO[7-1].WS_AMT = LNCIGVCH.DATA.BAL_OVERDUE_MANUAL + LNCIGVCH.DATA.BAL_OVERDUE + LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RP") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RPC")) {
            WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL;
            WS_AMT_INFO[3-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL - LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
            WS_AMT_INFO[5-1].WS_AMT = LNCIGVCH.DATA.BAL_OVERDUE_MANUAL - LNCIGVCH.DATA.BAL_OVERDUE;
            WS_AMT_INFO[7-1].WS_AMT = LNCIGVCH.DATA.BAL_OVERDUE_MANUAL - LNCIGVCH.DATA.BAL_OVERDUE - LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY")) {
            R000_INIT_WS_AMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[6-1].AMT);
            if (LNCIGVCH.DATA.AMT_INFO[6-1].AMT > 0) {
                WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[6-1].AMT;
            } else {
                WS_AMT_INFO[2-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[6-1].AMT * -1;
            }
            if (LNCIGVCH.DATA.AMT_INFO[13-1].AMT > 0) {
                WS_AMT_INFO[3-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[13-1].AMT;
            } else {
                WS_AMT_INFO[4-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[13-1].AMT * -1;
            }
            if (LNCIGVCH.DATA.AMT_INFO[14-1].AMT > 0) {
                WS_AMT_INFO[5-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[14-1].AMT;
            } else {
                WS_AMT_INFO[6-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[14-1].AMT * -1;
            }
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            R000_INIT_WS_AMT();
            if (pgmRtn) return;
            if (LNCIGVCH.DATA.AMT_INFO[18-1].AMT > 0) {
                WS_AMT_INFO[7-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[18-1].AMT;
            } else {
                WS_AMT_INFO[8-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[18-1].AMT * -1;
            }
        }
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("OJ")) {
            if (WS_AMT_INFO[15-1].WS_AMT > 0) {
                WS_AMT_INFO[25-1].WS_AMT = WS_AMT_INFO[15-1].WS_AMT;
            } else {
                WS_AMT_INFO[26-1].WS_AMT = WS_AMT_INFO[15-1].WS_AMT * -1;
            }
            WS_AMT_INFO[15-1].WS_AMT = 0;
            LNCIGVCH.DATA.EVENT_CODE = "DY";
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = K_OVD_PROC;
            BPCBWEVT.INFO.EVENT.EVENT_CODE = "DY";
        }
    }
    public void R000_TRANS_CLGU_AMT() throws IOException,SQLException,Exception {
        WS_AMT_INFO[1-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[1-1].AMT;
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RP")) {
            WS_AMT_INFO[2-1].WS_AMT = LNCIGVCH.DATA.BAL_NORMAL;
        }
    }
    public void R000_NPL_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLD") 
            || LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("LDC")) {
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            if (LNCIGVCH.DATA.STATUS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "WRITE OFF PROCESS.");
                R000_WRTOFF_AMT_PROC();
                if (pgmRtn) return;
            } else {
                if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
                if (LNCIGVCH.DATA.STATUS.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, "NON-ACCU PROCESS.");
                    R000_NONACCU_AMT_PROC();
                    if (pgmRtn) return;
                }
                if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
                if (LNCIGVCH.DATA.STATUS.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, "CAP-OTHER PROCESS.");
                    R000_CAPOTHER_AMT_PROC();
                    if (pgmRtn) return;
                }
                if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
                if (LNCIGVCH.DATA.STATUS.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, "DOUBLEFUL PROCESS.");
                    R000_DOUBLE_AMT_PROC();
                    if (pgmRtn) return;
                } else {
                    if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
                    JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
                    if (LNCIGVCH.DATA.STATUS.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.TRC(SCCGWA, "LOST PROCESS.");
                        R000_LOST_AMT_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_WRTOFF_AMT_PROC() throws IOException,SQLException,Exception {
        if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            && !LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("WF")) {
                WS_AMT_INFO[57-1].WS_AMT = LNCIGVCH.DATA.AMT_INFO[1-1].AMT + LNCIGVCH.DATA.AMT_INFO[2-1].AMT;
                WS_AMT_INFO[60-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT + WS_AMT_INFO[17-1].WS_AMT + WS_AMT_INFO[19-1].WS_AMT + WS_AMT_INFO[21-1].WS_AMT + WS_AMT_INFO[23-1].WS_AMT + WS_AMT_INFO[25-1].WS_AMT + WS_AMT_INFO[27-1].WS_AMT + WS_AMT_INFO[29-1].WS_AMT;
            } else {
                WS_AMT_INFO[43-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT;
                WS_AMT_INFO[45-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[7-1].WS_AMT;
                WS_AMT_INFO[1-1].WS_AMT = 0;
                WS_AMT_INFO[5-1].WS_AMT = 0;
                WS_AMT_INFO[3-1].WS_AMT = 0;
                WS_AMT_INFO[7-1].WS_AMT = 0;
            }
        }
    }
    public void R000_NONACCU_AMT_PROC() throws IOException,SQLException,Exception {
        if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            && !LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("TNA")) {
                WS_AMT_INFO[19-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT;
                WS_AMT_INFO[13-1].WS_AMT = 0;
                WS_AMT_INFO[15-1].WS_AMT = 0;
                WS_AMT_INFO[21-1].WS_AMT = WS_AMT_INFO[17-1].WS_AMT;
                WS_AMT_INFO[17-1].WS_AMT = 0;
                WS_AMT_INFO[25-1].WS_AMT = WS_AMT_INFO[23-1].WS_AMT;
                WS_AMT_INFO[29-1].WS_AMT = WS_AMT_INFO[27-1].WS_AMT;
                WS_AMT_INFO[33-1].WS_AMT = WS_AMT_INFO[31-1].WS_AMT;
                WS_AMT_INFO[37-1].WS_AMT = WS_AMT_INFO[35-1].WS_AMT;
                WS_AMT_INFO[23-1].WS_AMT = 0;
                WS_AMT_INFO[27-1].WS_AMT = 0;
                WS_AMT_INFO[31-1].WS_AMT = 0;
                WS_AMT_INFO[35-1].WS_AMT = 0;
            }
        } else {
            WS_AMT_INFO[13-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[25-1].WS_AMT;
            WS_AMT_INFO[1-1].WS_AMT = 0;
            WS_AMT_INFO[25-1].WS_AMT = 0;
            WS_AMT_INFO[14-1].WS_AMT = WS_AMT_INFO[2-1].WS_AMT + WS_AMT_INFO[26-1].WS_AMT;
            WS_AMT_INFO[2-1].WS_AMT = 0;
            WS_AMT_INFO[26-1].WS_AMT = 0;
            WS_AMT_INFO[15-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT;
            WS_AMT_INFO[3-1].WS_AMT = 0;
            WS_AMT_INFO[16-1].WS_AMT = WS_AMT_INFO[4-1].WS_AMT;
            WS_AMT_INFO[4-1].WS_AMT = 0;
            WS_AMT_INFO[17-1].WS_AMT = WS_AMT_INFO[5-1].WS_AMT;
            WS_AMT_INFO[5-1].WS_AMT = 0;
            WS_AMT_INFO[18-1].WS_AMT = WS_AMT_INFO[6-1].WS_AMT;
            WS_AMT_INFO[6-1].WS_AMT = 0;
        }
    }
    public void R000_CAPOTHER_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RLN") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("MA") 
            || LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("IS")) {
            WS_AMT_INFO[42-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT + WS_AMT_INFO[17-1].WS_AMT + WS_AMT_INFO[19-1].WS_AMT + WS_AMT_INFO[21-1].WS_AMT + WS_AMT_INFO[23-1].WS_AMT + WS_AMT_INFO[25-1].WS_AMT + WS_AMT_INFO[27-1].WS_AMT + WS_AMT_INFO[29-1].WS_AMT;
            WS_AMT_INFO[39-1].WS_AMT = 0;
            WS_AMT_INFO[41-1].WS_AMT = 0;
        }
    }
    public void R000_DOUBLE_AMT_PROC() throws IOException,SQLException,Exception {
        if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            && !LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA") 
            && !LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("NC")) {
            WS_AMT_INFO[9-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT;
            WS_AMT_INFO[1-1].WS_AMT = 0;
            WS_AMT_INFO[5-1].WS_AMT = 0;
            WS_AMT_INFO[11-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[7-1].WS_AMT;
            WS_AMT_INFO[3-1].WS_AMT = 0;
            WS_AMT_INFO[7-1].WS_AMT = 0;
        }
    }
    public void R000_LOST_AMT_PROC() throws IOException,SQLException,Exception {
        if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
            && !LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
            if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("TB")) {
                WS_AMT_INFO[43-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT;
                WS_AMT_INFO[45-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[7-1].WS_AMT;
            } else {
                WS_AMT_INFO[9-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT;
                WS_AMT_INFO[11-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[7-1].WS_AMT;
            }
            WS_AMT_INFO[1-1].WS_AMT = 0;
            WS_AMT_INFO[5-1].WS_AMT = 0;
            WS_AMT_INFO[3-1].WS_AMT = 0;
            WS_AMT_INFO[7-1].WS_AMT = 0;
        }
    }
    public void R000_CLASSIF_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLD") 
            || LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("LDC")) {
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            if (LNCIGVCH.DATA.STATUS.substring(35 - 1, 35 + 1 - 1).equalsIgnoreCase("1")) {
                if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY")) {
                    WS_AMT_INFO[9-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT;
                    WS_AMT_INFO[1-1].WS_AMT = 0;
                    WS_AMT_INFO[5-1].WS_AMT = 0;
                    WS_AMT_INFO[11-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[7-1].WS_AMT;
                    WS_AMT_INFO[3-1].WS_AMT = 0;
                    WS_AMT_INFO[7-1].WS_AMT = 0;
                    WS_AMT_INFO[19-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT;
                    WS_AMT_INFO[13-1].WS_AMT = 0;
                    WS_AMT_INFO[15-1].WS_AMT = 0;
                    WS_AMT_INFO[21-1].WS_AMT = WS_AMT_INFO[17-1].WS_AMT;
                    WS_AMT_INFO[17-1].WS_AMT = 0;
                    WS_AMT_INFO[25-1].WS_AMT = WS_AMT_INFO[23-1].WS_AMT;
                    WS_AMT_INFO[29-1].WS_AMT = WS_AMT_INFO[27-1].WS_AMT;
                    WS_AMT_INFO[33-1].WS_AMT = WS_AMT_INFO[31-1].WS_AMT;
                    WS_AMT_INFO[37-1].WS_AMT = WS_AMT_INFO[35-1].WS_AMT;
                    WS_AMT_INFO[23-1].WS_AMT = 0;
                    WS_AMT_INFO[27-1].WS_AMT = 0;
                    WS_AMT_INFO[31-1].WS_AMT = 0;
                    WS_AMT_INFO[35-1].WS_AMT = 0;
                    WS_AMT_INFO[57-1].WS_AMT = WS_AMT_INFO[55-1].WS_AMT;
                    WS_AMT_INFO[58-1].WS_AMT = WS_AMT_INFO[56-1].WS_AMT;
                    WS_AMT_INFO[55-1].WS_AMT = 0;
                    WS_AMT_INFO[56-1].WS_AMT = 0;
                } else {
                    WS_AMT_INFO[13-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[25-1].WS_AMT;
                    WS_AMT_INFO[1-1].WS_AMT = 0;
                    WS_AMT_INFO[25-1].WS_AMT = 0;
                    WS_AMT_INFO[14-1].WS_AMT = WS_AMT_INFO[2-1].WS_AMT + WS_AMT_INFO[26-1].WS_AMT;
                    WS_AMT_INFO[2-1].WS_AMT = 0;
                    WS_AMT_INFO[26-1].WS_AMT = 0;
                    WS_AMT_INFO[15-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT;
                    WS_AMT_INFO[3-1].WS_AMT = 0;
                    WS_AMT_INFO[16-1].WS_AMT = WS_AMT_INFO[4-1].WS_AMT;
                    WS_AMT_INFO[4-1].WS_AMT = 0;
                    WS_AMT_INFO[17-1].WS_AMT = WS_AMT_INFO[5-1].WS_AMT;
                    WS_AMT_INFO[5-1].WS_AMT = 0;
                    WS_AMT_INFO[18-1].WS_AMT = WS_AMT_INFO[6-1].WS_AMT;
                    WS_AMT_INFO[6-1].WS_AMT = 0;
                }
            }
        }
    }
    public void R000_SYNLOAN_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGVCH.DATA.CNTR_TYPE.equalsIgnoreCase("CLD")) {
            R000_SYNLOAN_CHECK_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCIPART.DATA.CNT);
            CEP.TRC(SCCGWA, LNCIGVCH.DATA.SUB_CTA_NO);
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            CEP.TRC(SCCGWA, LNCIGVCH.DATA.STATUS.substring(71 - 1, 71 + 1 - 1));
            if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
            JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
            CEP.TRC(SCCGWA, LNCIGVCH.DATA.STATUS.substring(73 - 1, 73 + 1 - 1));
            if (LNCIPART.DATA.CNT > 0) {
                if (LNCIGVCH.DATA.SUB_CTA_NO == 0) {
                    for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
                        WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
                    }
                } else {
                    if (!LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DY") 
                        && !LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("DA")) {
                        R000_SYNLOAN_NOTDY_AMT_PROC();
                        if (pgmRtn) return;
                    } else {
                        R000_SYNLOAN_DY_AMT_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_SYNLOAN_NOTDY_AMT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
        if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
        if (LNCIGVCH.DATA.STATUS.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("0") 
            && LNCIGVCH.DATA.STATUS.substring(73 - 1, 73 + 1 - 1).equalsIgnoreCase("0")) {
            for (WS_IND = 1; WS_IND <= 45; WS_IND += 2) {
                WS_AMT_INFO[WS_IND + 1-1].WS_AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
                WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
            }
        }
        IBS.CPY2CLS(SCCGWA, LNCIGVCH.DATA.SUB_CTA_NO+"", WS_SUB_CTA_NO);
        if (WS_SUB_CTA_NO.WS_SUB_PART == K_RATE_DIF_INCOME) {
            WS_AMT_INFO[47-1].WS_AMT = WS_AMT_INFO[14-1].WS_AMT;
            WS_AMT_INFO[14-1].WS_AMT = 0;
            WS_AMT_INFO[48-1].WS_AMT = WS_AMT_INFO[16-1].WS_AMT;
            WS_AMT_INFO[16-1].WS_AMT = 0;
            WS_AMT_INFO[53-1].WS_AMT = WS_AMT_INFO[18-1].WS_AMT;
            WS_AMT_INFO[18-1].WS_AMT = 0;
            WS_AMT_INFO[49-1].WS_AMT = WS_AMT_INFO[20-1].WS_AMT;
            WS_AMT_INFO[20-1].WS_AMT = 0;
            WS_AMT_INFO[54-1].WS_AMT = WS_AMT_INFO[22-1].WS_AMT;
            WS_AMT_INFO[22-1].WS_AMT = 0;
        }
        if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
        if (LNCIGVCH.DATA.STATUS.substring(79 - 1, 79 + 1 - 1).equalsIgnoreCase("1")) {
            WS_AMT_INFO[51-1].WS_AMT = WS_AMT_INFO[14-1].WS_AMT;
            WS_AMT_INFO[14-1].WS_AMT = 0;
            WS_AMT_INFO[52-1].WS_AMT = WS_AMT_INFO[16-1].WS_AMT;
            WS_AMT_INFO[16-1].WS_AMT = 0;
            WS_AMT_INFO[51-1].WS_AMT += WS_AMT_INFO[18-1].WS_AMT;
            WS_AMT_INFO[18-1].WS_AMT = 0;
            WS_AMT_INFO[51-1].WS_AMT += WS_AMT_INFO[20-1].WS_AMT;
            WS_AMT_INFO[20-1].WS_AMT = 0;
            WS_AMT_INFO[51-1].WS_AMT += WS_AMT_INFO[22-1].WS_AMT;
            WS_AMT_INFO[22-1].WS_AMT = 0;
        }
    }
    public void R000_SYNLOAN_DY_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, LNCIGVCH.DATA.SUB_CTA_NO+"", WS_SUB_CTA_NO);
        if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
        if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
        JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
        if (LNCIGVCH.DATA.STATUS.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("0") 
            && LNCIGVCH.DATA.STATUS.substring(73 - 1, 73 + 1 - 1).equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, WS_SUB_CTA_NO.WS_SUB_PART);
            if (WS_SUB_CTA_NO.WS_SUB_PART == K_RATE_DIF_INCOME) {
                CEP.TRC(SCCGWA, "RATE DIF");
                WS_AMT_INFO[33-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT;
                WS_AMT_INFO[1-1].WS_AMT = 0;
                WS_AMT_INFO[34-1].WS_AMT = WS_AMT_INFO[2-1].WS_AMT;
                WS_AMT_INFO[2-1].WS_AMT = 0;
                WS_AMT_INFO[35-1].WS_AMT = WS_AMT_INFO[13-1].WS_AMT;
                WS_AMT_INFO[13-1].WS_AMT = 0;
                WS_AMT_INFO[36-1].WS_AMT = WS_AMT_INFO[14-1].WS_AMT;
                WS_AMT_INFO[14-1].WS_AMT = 0;
                WS_AMT_INFO[37-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT;
                WS_AMT_INFO[3-1].WS_AMT = 0;
                WS_AMT_INFO[5-1].WS_AMT = 0;
                WS_AMT_INFO[38-1].WS_AMT = WS_AMT_INFO[4-1].WS_AMT + WS_AMT_INFO[6-1].WS_AMT;
                WS_AMT_INFO[4-1].WS_AMT = 0;
                WS_AMT_INFO[6-1].WS_AMT = 0;
                WS_AMT_INFO[39-1].WS_AMT = WS_AMT_INFO[15-1].WS_AMT + WS_AMT_INFO[17-1].WS_AMT;
                WS_AMT_INFO[15-1].WS_AMT = 0;
                WS_AMT_INFO[17-1].WS_AMT = 0;
                WS_AMT_INFO[40-1].WS_AMT = WS_AMT_INFO[16-1].WS_AMT + WS_AMT_INFO[18-1].WS_AMT;
                WS_AMT_INFO[16-1].WS_AMT = 0;
                WS_AMT_INFO[18-1].WS_AMT = 0;
                WS_AMT_INFO[41-1].WS_AMT = WS_AMT_INFO[25-1].WS_AMT;
                WS_AMT_INFO[25-1].WS_AMT = 0;
                WS_AMT_INFO[42-1].WS_AMT = WS_AMT_INFO[26-1].WS_AMT;
                WS_AMT_INFO[26-1].WS_AMT = 0;
                WS_AMT_INFO[43-1].WS_AMT = WS_AMT_INFO[29-1].WS_AMT;
                WS_AMT_INFO[29-1].WS_AMT = 0;
                WS_AMT_INFO[44-1].WS_AMT = WS_AMT_INFO[30-1].WS_AMT;
                WS_AMT_INFO[30-1].WS_AMT = 0;
            } else {
                if (LNCIGVCH.DATA.STATUS == null) LNCIGVCH.DATA.STATUS = "";
                JIBS_tmp_int = LNCIGVCH.DATA.STATUS.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCIGVCH.DATA.STATUS += " ";
                if (LNCIGVCH.DATA.STATUS.substring(79 - 1, 79 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_AMT_INFO[45-1].WS_AMT = WS_AMT_INFO[1-1].WS_AMT + WS_AMT_INFO[13-1].WS_AMT;
                    WS_AMT_INFO[1-1].WS_AMT = 0;
                    WS_AMT_INFO[13-1].WS_AMT = 0;
                    WS_AMT_INFO[46-1].WS_AMT = WS_AMT_INFO[2-1].WS_AMT + WS_AMT_INFO[14-1].WS_AMT;
                    WS_AMT_INFO[2-1].WS_AMT = 0;
                    WS_AMT_INFO[14-1].WS_AMT = 0;
                    WS_AMT_INFO[47-1].WS_AMT = WS_AMT_INFO[3-1].WS_AMT + WS_AMT_INFO[5-1].WS_AMT + WS_AMT_INFO[15-1].WS_AMT + WS_AMT_INFO[17-1].WS_AMT;
                    WS_AMT_INFO[3-1].WS_AMT = 0;
                    WS_AMT_INFO[5-1].WS_AMT = 0;
                    WS_AMT_INFO[15-1].WS_AMT = 0;
                    WS_AMT_INFO[17-1].WS_AMT = 0;
                    WS_AMT_INFO[48-1].WS_AMT = WS_AMT_INFO[4-1].WS_AMT + WS_AMT_INFO[6-1].WS_AMT + WS_AMT_INFO[16-1].WS_AMT + WS_AMT_INFO[18-1].WS_AMT;
                    WS_AMT_INFO[4-1].WS_AMT = 0;
                    WS_AMT_INFO[6-1].WS_AMT = 0;
                    WS_AMT_INFO[16-1].WS_AMT = 0;
                    WS_AMT_INFO[18-1].WS_AMT = 0;
                } else {
                    for (WS_IND = 1; WS_IND <= 6; WS_IND += 1) {
                        WS_AMT_INFO[WS_IND + 6-1].WS_AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
                        WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
                    }
                    for (WS_IND = 13; WS_IND <= 18; WS_IND += 1) {
                        WS_AMT_INFO[WS_IND + 6-1].WS_AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
                        WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
                    }
                    for (WS_IND = 25; WS_IND <= 26; WS_IND += 1) {
                        WS_AMT_INFO[WS_IND + 2-1].WS_AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
                        WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
                    }
                    for (WS_IND = 29; WS_IND <= 30; WS_IND += 1) {
                        WS_AMT_INFO[WS_IND + 2-1].WS_AMT = WS_AMT_INFO[WS_IND-1].WS_AMT;
                        WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
                    }
                }
            }
        }
    }
    public void R000_SYNLOAN_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.RC);
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.CONTRACT_NO = LNCIGVCH.DATA.CTA_NO;
        LNCIPART.DATA.REL_TYPE_IN = 'P';
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
    }
    public void R000_CLDD_EVENT_CODE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 2210) {
            CEP.TRC(SCCGWA, LNCIGVCH.DATA.EVENT_CODE);
            if (LNCIGVCH.DATA.EVENT_CODE.equalsIgnoreCase("RLN")) {
                BPCPOEWA.DATA.EVENT_CODE = "IS";
                LNCIGVCH.DATA.EVENT_CODE = "IS";
                WS_TRAN_FLG = 'N';
                WS_RLN_FLG = 'N';
                S000_STARTBR_TRAN_BY_PRINCIPAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
                S000_READNEXT_TRAN();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
                if (WS_TRAN_FLG == 'N' 
                    && LNRTRAN.KEY.CONTRACT_NO.equalsIgnoreCase(LNCIGVCH.DATA.CTA_NO) 
                    && LNRTRAN.KEY.TR_DATE == SCCGWA.COMM_AREA.AC_DATE 
                    && LNRTRAN.KEY.TR_JRN_NO == SCCGWA.COMM_AREA.JRN_NO) {
                    BPCPOEWA.DATA.EVENT_CODE = "RLN";
                    LNCIGVCH.DATA.EVENT_CODE = "RLN";
                    WS_RLN_FLG = 'Y';
                }
                S000_ENDBR_TRAN();
                if (pgmRtn) return;
                if (WS_RLN_FLG == 'N') {
                    WS_TRAN_FLG = 'N';
                    S000_STARTBR_TRAN_BY_INTEREST();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
                    S000_READNEXT_TRAN();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
                    CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
                    while (WS_TRAN_FLG != 'Y' 
                        && WS_RLN_FLG != 'Y') {
                        if (LNRTRAN.KEY.CONTRACT_NO.equalsIgnoreCase(LNCIGVCH.DATA.CTA_NO) 
                            && LNRTRAN.KEY.TR_DATE == SCCGWA.COMM_AREA.AC_DATE 
                            && LNRTRAN.KEY.TR_JRN_NO == SCCGWA.COMM_AREA.JRN_NO) {
                            CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
                            CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
                            CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
                            CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
                            CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TERM);
                            CEP.TRC(SCCGWA, LNRTRAN.KEY.TRAN_FLG);
                            WS_PLPI_FLG = 'N';
                            S000_STARTBR_PLPI();
                            if (pgmRtn) return;
                            if (WS_PLPI_FLG == 'N') {
                                BPCPOEWA.DATA.EVENT_CODE = "RLN";
                                LNCIGVCH.DATA.EVENT_CODE = "RLN";
                                WS_RLN_FLG = 'Y';
                            }
                        }
                        S000_READNEXT_TRAN();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_EFF_DAYS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCIGVCH.DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.ACCRUAL_TYPE == 'L') {
            BPCBWEVT.INFO.EVENT.EFF_DAYS = 1;
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            BPCBWEVT.INFO.EVENT.VAL_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
            BPCBWEVT.INFO.TR_CODE = "" + 0;
            JIBS_tmp_int = BPCBWEVT.INFO.TR_CODE.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) BPCBWEVT.INFO.TR_CODE = "0" + BPCBWEVT.INFO.TR_CODE;
        }
    }
    public void R000_INIT_WS_AMT() throws IOException,SQLException,Exception {
        for (WS_IND = 1; WS_IND <= K_MAX_AMT; WS_IND += 1) {
            WS_AMT_INFO[WS_IND-1].WS_AMT = 0;
        }
    }
    public void S000_CALL_BPZBWEVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BAT-EVENT", BPCBWEVT);
        if (BPCBWEVT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCBWEVT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUGMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-GLM-CHANGE", BPCUGMC);
        if (BPCUGMC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUGMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRTRAN() throws IOException,SQLException,Exception {
        LNCRTRAN.REC_PTR = LNRTRAN;
        LNCRTRAN.REC_LEN = 1035;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTTRAN", LNCRTRAN);
        if (LNCRTRAN.RETURN_INFO == 'E') {
            WS_TRAN_FLG = 'Y';
        } else {
            if (LNCRTRAN.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRTRAN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_STARTBR_TRAN_BY_PRINCIPAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTRAN);
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCIGVCH.DATA.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'P';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'D';
        LNCRTRAN.OPT = 'A';
        LNCRTRAN.FUNC = 'B';
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void S000_STARTBR_TRAN_BY_INTEREST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTRAN);
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCIGVCH.DATA.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'I';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'D';
        LNCRTRAN.OPT = 'A';
        LNCRTRAN.FUNC = 'B';
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void S000_READNEXT_TRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNCRTRAN.FUNC = 'B';
        LNCRTRAN.OPT = 'R';
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRTRAN.RETURN_INFO);
        if (LNCRTRAN.RETURN_INFO == 'E') {
            IBS.init(SCCGWA, LNRTRAN);
        }
    }
    public void S000_ENDBR_TRAN() throws IOException,SQLException,Exception {
        LNCRTRAN.FUNC = 'B';
        LNCRTRAN.OPT = 'E';
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RETURN_INFO == 'E' 
            || LNCRPLPI.RETURN_INFO == 'N') {
            WS_PLPI_FLG = 'Y';
        } else {
            if (LNCRPLPI.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_STARTBR_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCIGVCH.DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.DUE_DT = LNRTRAN.DUE_DT;
        LNCRPLPI.OPT = 'U';
        LNCRPLPI.FUNC = 'B';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void S000_READNEXT_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'R';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRPLPI.RETURN_INFO);
        if (LNCRPLPI.RETURN_INFO == 'E') {
            IBS.init(SCCGWA, LNRPLPI);
        }
    }
    public void S000_ENDBR_PLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'E';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1235;
        IBS.CALLCPN(SCCGWA, K_CPN_RSC_LNZRCMMT, LNCRCMMT);
        CEP.TRC(SCCGWA, LNCRCMMT.RC);
        if (LNCRCMMT.RETURN_INFO != 'F') {
            if (LNCRCMMT.RETURN_INFO == 'E' 
                || LNCRCMMT.RETURN_INFO == 'N') {
                WS_CMMT_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_CMMT_FLG = 'Y';
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIGVCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCIGVCH=");
            CEP.TRC(SCCGWA, LNCIGVCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
