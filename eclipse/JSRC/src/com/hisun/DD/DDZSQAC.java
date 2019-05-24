package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQAC {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    brParm DDTCCY_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_A_INF = "DD910";
    String K_FMT_B_INF = "DD911";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    char WS_AGN_FLG = ' ';
    short WS_PRT_LINE = 0;
    short WS_PRINT_PAGES = 0;
    short WS_P_LINES = 0;
    double WS_AVL_BAL = 0;
    short WS_CNT = 0;
    int WS_P_ROW = 0;
    int WS_L_CNT = 0;
    int WS_L_ROW = 0;
    int WS_T_PAGE = 0;
    int WS_P_NUM = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    short WS_NUM1 = 0;
    double WS_INT_RATE = 0;
    String WS_REL_AC = " ";
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACAC CICQACAC = new CICQACAC();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    DDCOQACA DDCOQACA = new DDCOQACA();
    DDCOQACB DDCOQACB = new DDCOQACB();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCPBKS DDCPBKS = new DDCPBKS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    CICQACRL CICQACRL = new CICQACRL();
    String WS_CUS_AC = " ";
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSQAC DDCSQAC;
    public void MP(SCCGWA SCCGWA, DDCSQAC DDCSQAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQAC = DDCSQAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "22222");
        CEP.TRC(SCCGWA, DDCSQAC.INPUT_INFO.FUNC);
        CEP.TRC(SCCGWA, DDCSQAC.INPUT_INFO.AC_NO);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQAC.INPUT_INFO.FUNC);
        CEP.TRC(SCCGWA, DDCSQAC.INPUT_INFO.AC_NO);
        if (DDCSQAC.INPUT_INFO.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQAC.INPUT_INFO.FUNC != 'A' 
            && DDCSQAC.INPUT_INFO.FUNC != 'B' 
            && DDCSQAC.INPUT_INFO.FUNC != 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQAC.INPUT_INFO.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "33333");
        B020_GET_CI_INFO();
        if (pgmRtn) return;
        if (DDCSQAC.INPUT_INFO.FUNC == 'A') {
            B030_GET_ACAC_INFO();
            if (pgmRtn) return;
        } else if (DDCSQAC.INPUT_INFO.FUNC == 'B') {
            B030_GET_BV_INFO();
            if (pgmRtn) return;
        } else if (DDCSQAC.INPUT_INFO.FUNC == 'C') {
            B030_GET_BV_INFO();
            if (pgmRtn) return;
            B030_GET_ACAC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSQAC.INPUT_INFO.AC_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR);
    }
    public void B030_GET_BV_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GET BV INFO...");
        B030_GET_MST_VCH_PROC();
        if (pgmRtn) return;
        B030_GET_AGN_FLG();
        if (pgmRtn) return;
        B030_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_LIST_B();
        if (pgmRtn) return;
    }
    public void B030_GET_MST_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSQAC.INPUT_INFO.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSQAC.INPUT_INFO.AC_NO;
        T000_READ_DDTVCH();
        if (pgmRtn) return;
    }
    public void B030_GET_AGN_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOCAC);
        BPCPOCAC.INFO.FUNC = 'I';
        BPCPOCAC.DATA_INFO.AC = DDCSQAC.INPUT_INFO.AC_NO;
        BPCPOCAC.DATA_INFO.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPOCAC.DATA_INFO.STS = 'N';
        S000_CALL_BPZPOCAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.ID_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.ID_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CI_CNM);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OTH_PRT_NM);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OTH_ID_NO);
        if (BPCPOCAC.DATA_INFO.OTH_ID_NO.trim().length() > 0) {
            if (!BPCPOCAC.DATA_INFO.OTH_ID_NO.equalsIgnoreCase(BPCPOCAC.DATA_INFO.ID_NO) 
                || !BPCPOCAC.DATA_INFO.OTH_ID_TYP.equalsIgnoreCase(BPCPOCAC.DATA_INFO.ID_TYP) 
                || !BPCPOCAC.DATA_INFO.OTH_PRT_NM.equalsIgnoreCase(BPCPOCAC.DATA_INFO.CI_CNM)) {
                WS_AGN_FLG = '1';
            } else {
                WS_AGN_FLG = '0';
            }
        } else {
            WS_AGN_FLG = '0';
        }
    }
    public void B030_GET_PASSBOOK_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPBKS);
        DDCPBKS.KEY.TYP = "PDD01";
        DDCPBKS.KEY.CD = "PASSBOOK";
        BPCPRMR.DAT_PTR = DDCPBKS;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_PAGE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.PAGE_LINE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_LINE);
        CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
        WS_PRT_LINE = DDRVCH.PRT_LINE;
        WS_P_LINES = (short) (WS_PRT_LINE % DDCPBKS.DATA_TXT.PAGE_LINE);
        WS_PRINT_PAGES = (short) ((WS_PRT_LINE - WS_P_LINES) / DDCPBKS.DATA_TXT.PAGE_LINE);
        if (WS_P_LINES > 0) {
            WS_PRINT_PAGES = (short) (WS_PRINT_PAGES + 1);
        }
        CEP.TRC(SCCGWA, WS_PRINT_PAGES);
    }
    public void B030_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        if (DDCSQAC.PAGE_ROW == 0) {
            WS_P_ROW = 10;
        } else {
            if (DDCSQAC.PAGE_ROW > 10) {
                WS_P_ROW = 10;
            } else {
                WS_P_ROW = DDCSQAC.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
        IBS.init(SCCGWA, DDRCCY);
        IBS.init(SCCGWA, DDCOQACA);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DDCSQAC.INPUT_INFO.AC_NO;
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_REL_AC = DDCSQAC.INPUT_INFO.AC_NO;
        }
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
        }
        WS_CUS_AC = WS_REL_AC;
        CEP.TRC(SCCGWA, WS_CUS_AC);
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        for (WS_I = 1; WS_CCY_FLG != 'N'; WS_I += 1) {
            if (DDRCCY.AC_TYPE != '6') {
                R000_GET_PRD_RATE_INFO();
                if (pgmRtn) return;
            }
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].PROD_CD = DDRCCY.PROD_CODE;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE = DDRCCY.AC_TYPE;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].CCY = DDRCCY.CCY;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].CCY_TYPE = DDRCCY.CCY_TYPE;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].A_OPEN_DT = DDRCCY.OPEN_DATE;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].A_OPEN_BR = DDRCCY.OPEN_DP;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].A_OPEN_TLR = DDRCCY.OPEN_TLR;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].ACO_NO = DDRCCY.KEY.AC;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].OPEN_BAL = 0;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].CURR_BAL = DDRCCY.CURR_BAL;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].FRZ_BAL = DDRCCY.HOLD_BAL;
            WS_AVL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AVL_BAL = 0;
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AVL_BAL = WS_AVL_BAL;
            }
            if (WS_AVL_BAL < 0) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AVL_BAL = WS_AVL_BAL;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].DAC_FLG = '1';
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].DAC_FLG = '0';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].OFFICE_FRZ_FLG = '1';
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].OFFICE_FRZ_FLG = '0';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].BANK_FRZ_FLG = '1';
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].BANK_FRZ_FLG = '0';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].OFFICE_FBD_FLG = '1';
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].OFFICE_FBD_FLG = '0';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].BANK_FBD_FLG = '1';
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].BANK_FBD_FLG = '0';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(65 - 1, 65 + 1 - 1).equalsIgnoreCase("1")) {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].UCON_ZF_FLG = '1';
            } else {
                DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].UCON_ZF_FLG = '0';
            }
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].INT_DT = DDRCCY.OPEN_DATE;
            DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].RATE = WS_INT_RATE;
            CEP.TRC(SCCGWA, "5555555");
            WS_L_CNT += 1;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (WS_L_CNT != 0) {
            DDCOQACA.TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = DDCOQACA.TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((DDCOQACA.TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, DDCOQACA.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                DDCOQACA.TOTAL_PAGE = WS_T_PAGE;
                WS_L_ROW = WS_P_ROW;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                DDCOQACA.TOTAL_PAGE = WS_T_PAGE + 1;
                CEP.TRC(SCCGWA, DDCOQACA.TOTAL_PAGE);
            }
            CEP.TRC(SCCGWA, DDCSQAC.PAGE_NUM);
            CEP.TRC(SCCGWA, DDCOQACA.TOTAL_PAGE);
            if (DDCSQAC.PAGE_NUM == 0) {
                DDCSQAC.PAGE_NUM += 1;
                CEP.TRC(SCCGWA, DDCSQAC.PAGE_NUM);
            }
            if (DDCSQAC.PAGE_NUM != 0) {
                if (DDCSQAC.PAGE_NUM >= DDCOQACA.TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    DDCOQACA.CURR_PAGE = DDCOQACA.TOTAL_PAGE;
                    DDCOQACA.LAST_PAGE = 'Y';
                    DDCOQACA.PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    DDCOQACA.CURR_PAGE = DDCSQAC.PAGE_NUM;
                    DDCOQACA.LAST_PAGE = 'N';
                    DDCOQACA.PAGE_ROW = WS_P_ROW;
                }
            }
            if (DDCSQAC.PAGE_NUM == 0) {
                DDCOQACA.CURR_PAGE = 1;
                if (DDCOQACA.TOTAL_PAGE == 1) {
                    DDCOQACA.LAST_PAGE = 'Y';
                    DDCOQACA.PAGE_ROW = WS_L_ROW;
                } else {
                    DDCOQACA.LAST_PAGE = 'N';
                    DDCOQACA.PAGE_ROW = WS_P_ROW;
                }
            }
            WS_P_NUM = DDCOQACA.CURR_PAGE - 1;
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            WS_END_NUM = DDCOQACA.CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, DDCOQACA.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, DDCOQACA.TOTAL_PAGE);
            CEP.TRC(SCCGWA, DDCOQACA.PAGE_ROW);
            CEP.TRC(SCCGWA, DDCOQACA.CURR_PAGE);
            CEP.TRC(SCCGWA, DDCOQACA.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            IBS.init(SCCGWA, DDRMST);
            IBS.init(SCCGWA, DDRCCY);
            DDRMST.KEY.CUS_AC = DDCSQAC.INPUT_INFO.AC_NO;
            WS_CUS_AC = WS_REL_AC;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            CEP.TRC(SCCGWA, WS_CUS_AC);
            T000_READ_DDTMST1();
            if (pgmRtn) return;
            T000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            WS_I = 0;
            for (WS_NUM1 = 1; WS_CCY_FLG != 'N'; WS_NUM1 += 1) {
                if (WS_NUM1 > WS_STR_NUM 
                    && WS_NUM1 <= WS_END_NUM) {
                    CEP.TRC(SCCGWA, "FIND BUG1");
                    CEP.TRC(SCCGWA, WS_I);
                    WS_I = (short) (WS_I + 1);
                    if (DDRCCY.AC_TYPE != '6') {
                        R000_GET_PRD_RATE_INFO();
                        if (pgmRtn) return;
                    }
                    IBS.init(SCCGWA, DDCOQACA.ACAC_INFO[WS_I-1]);
                    DDCOQACA.ACAC_INFO[WS_I-1].AC_NO = DDRCCY.CUS_AC;
                    DDCOQACA.ACAC_INFO[WS_I-1].PROD_CD = DDRCCY.PROD_CODE;
                    DDCOQACA.ACAC_INFO[WS_I-1].AC_TYPE = DDRCCY.AC_TYPE;
                    if (DDRCCY.AC_TYPE == '1') {
                        if (DDRMST.AC_TYPE == 'A' 
                            && DDRCCY.CCY.equalsIgnoreCase("156")) {
                            DDCOQACA.ACAC_INFO[WS_I-1].AC_TYPE = '1';
                        } else {
                            if (!DDRCCY.CCY.equalsIgnoreCase("156")) {
                                DDCOQACA.ACAC_INFO[WS_I-1].AC_TYPE = '4';
                            } else {
                                DDCOQACA.ACAC_INFO[WS_I-1].AC_TYPE = 'B';
                            }
                        }
                    }
                    if (DDRCCY.AC_TYPE == '2' 
                        && DDRCCY.CCY.equalsIgnoreCase("156")) {
                        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
                        CEP.TRC(SCCGWA, DCCUCINF.AC_TYP);
                        DDCOQACA.ACAC_INFO[WS_I-1].AC_TYPE = DCCUCINF.AC_TYP;
                    }
                    if (DDRCCY.AC_TYPE == '2' 
                        && !DDRCCY.CCY.equalsIgnoreCase("156")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].AC_TYPE = '4';
                    }
                    DDCOQACA.ACAC_INFO[WS_I-1].CCY = DDRCCY.CCY;
                    DDCOQACA.ACAC_INFO[WS_I-1].CCY_TYPE = DDRCCY.CCY_TYPE;
                    DDCOQACA.ACAC_INFO[WS_I-1].OPEN_DT = DDRCCY.OPEN_DATE;
                    DDCOQACA.ACAC_INFO[WS_I-1].OPEN_BR = DDRCCY.OPEN_DP;
                    DDCOQACA.ACAC_INFO[WS_I-1].OPEN_TLR = DDRCCY.OPEN_TLR;
                    DDCOQACA.ACAC_INFO[WS_I-1].ACO_NO = DDRCCY.KEY.AC;
                    DDCOQACA.ACAC_INFO[WS_I-1].OPEN_BAL = 0;
                    DDCOQACA.ACAC_INFO[WS_I-1].CURR_BAL = DDRCCY.CURR_BAL;
                    DDCOQACA.ACAC_INFO[WS_I-1].FRZ_BAL = DDRCCY.HOLD_BAL;
                    WS_AVL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].AVL_BAL = 0;
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].AVL_BAL = WS_AVL_BAL;
                    }
                    if (WS_AVL_BAL < 0) {
                        DDCOQACA.ACAC_INFO[WS_I-1].AVL_BAL = WS_AVL_BAL;
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].DAC_FLG = '1';
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].DAC_FLG = '0';
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].OFFICE_FRZ_FLG = '1';
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].OFFICE_FRZ_FLG = '0';
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].BANK_FRZ_FLG = '1';
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].BANK_FRZ_FLG = '0';
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].OFFICE_FBD_FLG = '1';
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].OFFICE_FBD_FLG = '0';
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].BANK_FBD_FLG = '1';
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].BANK_FBD_FLG = '0';
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(65 - 1, 65 + 1 - 1).equalsIgnoreCase("1")) {
                        DDCOQACA.ACAC_INFO[WS_I-1].UCON_ZF_FLG = '1';
                    } else {
                        DDCOQACA.ACAC_INFO[WS_I-1].UCON_ZF_FLG = '0';
                    }
                    DDCOQACA.ACAC_INFO[WS_I-1].INT_DT = DDRCCY.OPEN_DATE;
                    DDCOQACA.ACAC_INFO[WS_I-1].RATE = WS_INT_RATE;
                }
                T000_READNEXT_DDTCCY();
                if (pgmRtn) return;
            }
        } else {
            DDCOQACA.TOTAL_NUM = 0;
            DDCOQACA.TOTAL_PAGE = 0;
            DDCOQACA.CURR_PAGE = 0;
            DDCOQACA.LAST_PAGE = 'Y';
            DDCOQACA.PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_A_INF;
        SCCFMT.DATA_PTR = DDCOQACA;
        SCCFMT.DATA_LEN = 1872;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_PRD_RATE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDRCCY.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMRAT.VAL.TIER[1-1].FIX_RATE);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
        BPCCINTI.BASE_INFO.CCY = DDRCCY.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].INT_RBAS;
        BPCCINTI.BASE_INFO.TENOR = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].INT_RCD;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        WS_INT_RATE = BPCCINTI.BASE_INFO.RATE;
        CEP.TRC(SCCGWA, WS_INT_RATE);
    }
    public void R000_DATA_OUTPUT_LIST_B() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOQACB);
        IBS.init(SCCGWA, SCCFMT);
        DDCOQACB.BV_INFO.AC_NO = DDRMST.KEY.CUS_AC;
        DDCSQAC.INPUT_INFO.AC_NO = DDRMST.KEY.CUS_AC;
        if (DDRMST.CARD_FLG == 'K') {
            DDCOQACB.BV_INFO.BV_TYP = "003";
            DDCSQAC.OUTPUT_DATA.BV_INFO.BV_TYP = "003";
        } else {
            if (DDRVCH.VCH_TYPE == '1') {
                DDCOQACB.BV_INFO.BV_TYP = "001";
                DDCSQAC.OUTPUT_DATA.BV_INFO.BV_TYP = "001";
            } else {
                DDCOQACB.BV_INFO.BV_TYP = "002";
                DDCSQAC.OUTPUT_DATA.BV_INFO.BV_TYP = "002";
            }
        }
        if (DDRVCH.KEY.BV_TYPE == 'B' 
            && DDRMST.CARD_FLG != 'Y') {
            DDCOQACB.BV_INFO.BV_TYP = "004";
            DDCSQAC.OUTPUT_DATA.BV_INFO.BV_TYP = "004";
        }
        DDCOQACB.BV_INFO.BV_NO = DDRVCH.PSBK_NO;
        DDCSQAC.OUTPUT_DATA.BV_INFO.BV_NO = DDRVCH.PSBK_NO;
        DDCOQACB.BV_INFO.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCSQAC.OUTPUT_DATA.BV_INFO.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCOQACB.BV_INFO.OPEN_DT = DDRMST.OPEN_DATE;
        DDCSQAC.OUTPUT_DATA.BV_INFO.B_OPEN_DT = DDRMST.OPEN_DATE;
        DDCOQACB.BV_INFO.OPEN_BR = DDRMST.OPEN_DP;
        DDCSQAC.OUTPUT_DATA.BV_INFO.B_OPEN_BR = DDRMST.OPEN_DP;
        DDCOQACB.BV_INFO.OPEN_TLR = DDRMST.OPEN_TLR;
        DDCSQAC.OUTPUT_DATA.BV_INFO.B_OPEN_TLR = DDRMST.OPEN_TLR;
        DDCOQACB.BV_INFO.CRS_DR_FLG = DDRMST.CROS_DR_FLG;
        DDCSQAC.OUTPUT_DATA.BV_INFO.CRS_DR_FLG = DDRMST.CROS_DR_FLG;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOQACB.BV_INFO.PSBK_STS1 = '1';
        } else {
            DDCOQACB.BV_INFO.PSBK_STS1 = '0';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOQACB.BV_INFO.PSBK_STS2 = '1';
        } else {
            DDCOQACB.BV_INFO.PSBK_STS2 = '0';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOQACB.BV_INFO.PSBK_STS3 = '1';
        } else {
            DDCOQACB.BV_INFO.PSBK_STS3 = '0';
        }
        DDCSQAC.OUTPUT_DATA.BV_INFO.PSBK_STS1 = DDCOQACB.BV_INFO.PSBK_STS1;
        DDCSQAC.OUTPUT_DATA.BV_INFO.PSBK_STS2 = DDCOQACB.BV_INFO.PSBK_STS2;
        DDCSQAC.OUTPUT_DATA.BV_INFO.PSBK_STS3 = DDCOQACB.BV_INFO.PSBK_STS3;
        DDCOQACB.BV_INFO.AGN_FLG = WS_AGN_FLG;
        DDCSQAC.OUTPUT_DATA.BV_INFO.AGN_FLG = WS_AGN_FLG;
        DDCOQACB.BV_INFO.PRINT_PAGS = WS_PRINT_PAGES;
        DDCSQAC.OUTPUT_DATA.BV_INFO.PRINT_PAGS = WS_PRINT_PAGES;
        DDCOQACB.BV_INFO.PRINT_ROWS = DDRVCH.PRT_LINE;
        DDCSQAC.OUTPUT_DATA.BV_INFO.PRINT_ROWS = DDRVCH.PRT_LINE;
        DDCOQACB.BV_INFO.UPT_CNT = DDRVCH.UPT_CNT;
        DDCSQAC.OUTPUT_DATA.BV_INFO.UPT_CNT = DDRVCH.UPT_CNT;
        SCCFMT.FMTID = K_FMT_B_INF;
        SCCFMT.DATA_PTR = DDCOQACB;
        SCCFMT.DATA_LEN = 96;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST1() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :WS_CUS_AC";
        DDTCCY_BR.rp.order = "PROD_CODE,OPEN_DATE DESC";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CCY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_CCY_FLG);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CCY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_CCY_FLG);
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        DCCUCINF.CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-OCAC-INFO", BPCPOCAC);
        if (BPCPOCAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOCAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
