package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCTRL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_RISK_CONTROL = "BP-F-RISK-CONTROL   ";
    String CPN_F_AMT_TBL_AUTH = "BP-F-AMT-TBL-AUTH   ";
    String CPN_F_STS_TBL_AUTH = "BP-F-STS-TBL-AUTH   ";
    String CPN_R_UNIT = "BP-R-MGM-UNIT    ";
    String CPN_R_UNIB = "BP-R-MGM-UNIB       ";
    String CPN_F_RW_PBDA = "BP-F-RW-PBDA        ";
    BPZFCTRL_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZFCTRL_WS_TEMP_VARIABLE();
    BPZFCTRL_WS_INDEX WS_INDEX = new BPZFCTRL_WS_INDEX();
    char WS_UNIT_FLAG = ' ';
    char WS_FIRST_CHAR_FLAG = ' ';
    char WS_STS_IDX_FLAG = ' ';
    char WS_OFFSET_FLAG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCRBNM SCCRBNM = new SCCRBNM();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCRUNIB BPCRUNIB = new BPCRUNIB();
    BPCRUNIT BPCRUNIT = new BPCRUNIT();
    BPRUNIT BPRUNIT = new BPRUNIT();
    BPCFRWDA BPCFRWDA = new BPCFRWDA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCFCTRL BPCFCTRL;
    public void MP(SCCGWA SCCGWA, BPCFCTRL BPCFCTRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCTRL = BPCFCTRL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCTRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRUNIT);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_RULES_CHECK();
        if (pgmRtn) return;
    }
    public void B10_RULES_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        IBS.init(SCCGWA, BPCRUNIB);
        IBS.init(SCCGWA, BPRUNIT);
        WS_TEMP_VARIABLE.WS_RESULT_RULE = 'T';
        BPCRUNIB.INFO.FUNC = '1';
        BPRUNIT.KEY.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRUNIT.KEY.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRUNIT.KEY.SERV_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        BPRUNIT.KEY.UNIT_NO = 1;
        S000_CALL_BPZRUNIB();
        if (pgmRtn) return;
        BPCRUNIB.INFO.FUNC = 'N';
        S000_CALL_BPZRUNIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "F1");
        while (BPCRUNIB.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRUNIT.LINES_CNT);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_RESULT_RULE);
            CEP.TRC(SCCGWA, BPCFCTRL.AUTO_IND);
            if (BPRUNIT.UNIT_STS == 'N' 
                && (BPCFCTRL.AUTO_IND == 'Y' 
                && BPCFCTRL.AUTO_IND == BPRUNIT.AUTO_IND 
                || (BPCFCTRL.AUTO_IND == ' ' 
                || BPCFCTRL.AUTO_IND == 'N') 
                && (BPRUNIT.AUTO_IND == ' ' 
                || BPRUNIT.AUTO_IND == 'N'))) {
                CEP.TRC(SCCGWA, "F2");
                for (WS_INDEX.WS_I = 1; WS_INDEX.WS_I <= BPRUNIT.LINES_CNT 
                    && WS_TEMP_VARIABLE.WS_RESULT_RULE != 'F'; WS_INDEX.WS_I += 1) {
                    WS_TEMP_VARIABLE.WS_LEN = 20;
                    CEP.TRC(SCCGWA, WS_INDEX.WS_I);
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_RESULT_RULE);
                    if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'Y' 
                        || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'D') {
                        WS_TEMP_VARIABLE.WS_RESULT_LINE = 'F';
                        CEP.TRC(SCCGWA, BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].START_IDX);
                        for (WS_INDEX.WS_K = BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].START_IDX; WS_INDEX.WS_K <= BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].END_IDX 
                            && WS_TEMP_VARIABLE.WS_RESULT_LINE != 'T'; WS_INDEX.WS_K += 1) {
                            CEP.TRC(SCCGWA, "F21");
                            B11_CHECK_EXPR();
                            if (pgmRtn) return;
                            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG == 'B') {
                                if (WS_TEMP_VARIABLE.WS_RESULT_EXPR == 'F') {
                                    WS_INDEX.WS_K += 1;
                                }
                            } else if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG == 'E'
                                || BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG == ' ') {
                                if (WS_TEMP_VARIABLE.WS_RESULT_EXPR == 'T') {
                                    WS_TEMP_VARIABLE.WS_RESULT_LINE = 'T';
                                }
                            }
                        }
                    } else {
                        WS_TEMP_VARIABLE.WS_RESULT_LINE = 'T';
                        for (WS_INDEX.WS_K = BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].START_IDX; WS_INDEX.WS_K <= BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].END_IDX 
                            && WS_TEMP_VARIABLE.WS_RESULT_LINE != 'F'; WS_INDEX.WS_K += 1) {
                            B11_CHECK_EXPR();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, "AFTER B11-CHECK");
                            CEP.TRC(SCCGWA, BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG);
                            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG == 'B') {
                                if (WS_TEMP_VARIABLE.WS_RESULT_EXPR == 'F') {
                                    WS_INDEX.WS_K += 1;
                                }
                            } else if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG == 'E'
                                || BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].RANGE_FLG == ' ') {
                                if (WS_TEMP_VARIABLE.WS_RESULT_EXPR == 'T') {
                                    WS_TEMP_VARIABLE.WS_RESULT_LINE = 'F';
                                    WS_TEMP_VARIABLE.WS_RESULT_RULE = 'F';
                                }
                            }
                        }
                    }
                    if (WS_TEMP_VARIABLE.WS_RESULT_LINE == 'F') {
                        WS_TEMP_VARIABLE.WS_RESULT_RULE = 'F';
                    }
                }
                if (WS_TEMP_VARIABLE.WS_RESULT_RULE == 'T') {
                    if (BPRUNIT.CTRL_TYPE == 'M') {
                        if (BPRUNIT.ATH_LVL.equalsIgnoreCase("99")) {
                            WS_TEMP_VARIABLE.WS_ERR_MSG = BPRUNIT.MSG_CODE;
                            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
                            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSG_TYPE);
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        } else {
                            WS_TEMP_VARIABLE.WS_ERR_MSG = BPRUNIT.MSG_CODE;
                            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
                            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSG_TYPE);
                            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_LVL1);
                            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_LVL2);
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                        BPCFCTRL.FIELD_BLOCK_IND = 'Y';
                    }
                }
            }
            BPCRUNIB.INFO.FUNC = 'N';
            S000_CALL_BPZRUNIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "F3");
            WS_TEMP_VARIABLE.WS_RESULT_RULE = 'T';
        }
        BPCRUNIB.INFO.FUNC = 'E';
        S000_CALL_BPZRUNIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FIELD BLOCKING");
        CEP.TRC(SCCGWA, BPCFCTRL.FIELD_BLOCK_IND);
    }
    public void B11_CHECK_EXPR() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_VALUE1 = " ";
        IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1, WS_TEMP_VARIABLE.WS_AMT_TEXT);
        WS_TEMP_VARIABLE.WS_VALUE2 = " ";
        WS_TEMP_VARIABLE.WS_FROM_AREA = BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].FROM_AREA;
        if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].ITEM == null) BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].ITEM = "";
        JIBS_tmp_int = BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].ITEM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].ITEM += " ";
        WS_TEMP_VARIABLE.WS_NAME = BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].ITEM.substring(0, WS_TEMP_VARIABLE.WS_LEN);
        if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].OFFSET > 1) {
            WS_INDEX.WS_IDX = BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].OFFSET;
        }
        S000_READ_RBNM();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_NAME_LEN = 30;
        WS_TEMP_VARIABLE.WS_TYPE = SCCRBNM.TYPE;
        if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'M'
            || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'S') {
            WS_STS_IDX_FLAG = 'T';
            if (SCCRBNM.VAL1 == null) SCCRBNM.VAL1 = "";
            JIBS_tmp_int = SCCRBNM.VAL1.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) SCCRBNM.VAL1 += " ";
            WS_TEMP_VARIABLE.WS_VALUE1 = SCCRBNM.VAL1.substring(0, WS_TEMP_VARIABLE.WS_NAME_LEN);
            IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1, WS_TEMP_VARIABLE.WS_AMT_TEXT);
        } else if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'D'
            || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'K') {
            WS_STS_IDX_FLAG = 'Y';
            if (SCCRBNM.VAL1 == null) SCCRBNM.VAL1 = "";
            JIBS_tmp_int = SCCRBNM.VAL1.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) SCCRBNM.VAL1 += " ";
            WS_TEMP_VARIABLE.WS_VALUE1 = SCCRBNM.VAL1.substring(BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_K-1].OFFSET - 1, BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_K-1].OFFSET + 1 - 1);
            IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1, WS_TEMP_VARIABLE.WS_AMT_TEXT);
            CEP.TRC(SCCGWA, BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_K-1].OFFSET);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1);
        } else if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'Y'
            || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'N') {
            WS_STS_IDX_FLAG = 'N';
            if (SCCRBNM.VAL1 == null) SCCRBNM.VAL1 = "";
            JIBS_tmp_int = SCCRBNM.VAL1.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) SCCRBNM.VAL1 += " ";
            WS_TEMP_VARIABLE.WS_VALUE1 = SCCRBNM.VAL1.substring(0, WS_TEMP_VARIABLE.WS_NAME_LEN);
            IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1, WS_TEMP_VARIABLE.WS_AMT_TEXT);
        }
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_LEN);
        CEP.TRC(SCCGWA, BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].ITEM);
        CEP.TRC(SCCGWA, BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL);
        if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'M') {
            CEP.TRC(SCCGWA, BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE);
            IBS.init(SCCGWA, BPCFAMTA);
            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE == null) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE = "";
            JIBS_tmp_int = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE += " ";
            BPCFAMTA.AP_MMO = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.substring(0, 2);
            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE == null) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE = "";
            JIBS_tmp_int = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE += " ";
            BPCFAMTA.TBL_NO = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.substring(3 - 1, 3 + 4 - 1);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1);
            BPCFAMTA.AMT = WS_TEMP_VARIABLE.WS_AMT_TEXT.WS_AMT_1 + WS_TEMP_VARIABLE.WS_AMT_TEXT.WS_AMT_2 / 100;
            BPCFAMTA.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPCFAMTA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFAMTA.CCY = BPCRBANK.LOC_CCY1;
            S000_CALL_BPZFAMTA();
            if (pgmRtn) return;
        } else if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'S') {
            IBS.init(SCCGWA, BPCFCSTS);
            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE == null) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE = "";
            JIBS_tmp_int = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE += " ";
            BPCFCSTS.AP_MMO = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.substring(0, 2);
            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE == null) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE = "";
            JIBS_tmp_int = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE += " ";
            BPCFCSTS.TBL_NO = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.substring(3 - 1, 3 + 4 - 1);
            IBS.init(SCCGWA, BPCFRWDA);
            if (BPCFCSTS.AP_MMO.equalsIgnoreCase("CI")) {
                CEP.TRC(SCCGWA, "1");
                CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
                BPCFRWDA.FUNC = 'S';
                if (WS_TEMP_VARIABLE.WS_VALUE1 == null) WS_TEMP_VARIABLE.WS_VALUE1 = "";
                JIBS_tmp_int = WS_TEMP_VARIABLE.WS_VALUE1.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_VALUE1 += " ";
                BPCFRWDA.INFO.PB_DATA.PB_AC = WS_TEMP_VARIABLE.WS_VALUE1.substring(0, 21);
            } else {
                CEP.TRC(SCCGWA, "2");
                CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
                BPCFRWDA.FUNC = 'A';
                if (WS_TEMP_VARIABLE.WS_VALUE1 == null) WS_TEMP_VARIABLE.WS_VALUE1 = "";
                JIBS_tmp_int = WS_TEMP_VARIABLE.WS_VALUE1.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_VALUE1 += " ";
                BPCFRWDA.INFO.PB_DATA.PB_AC = WS_TEMP_VARIABLE.WS_VALUE1.substring(0, 21);
            }
            S000_CALL_BPZFRWDA();
            if (pgmRtn) return;
            BPCFCSTS.STATUS_WORD = BPCFRWDA.INFO.PB_DATA.PB_STSW;
            S000_CALL_BPZFCSTS();
            if (pgmRtn) return;
        } else if (BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'D'
            || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'K'
            || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'Y'
            || BPRUNIT.REDEFINES20.LINES_LST_DATA[WS_INDEX.WS_I-1].LOGICAL == 'N') {
            CEP.TRC(SCCGWA, BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE);
            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE_TYPE == 'C') {
                if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE == null) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE = "";
                JIBS_tmp_int = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE += " ";
                WS_TEMP_VARIABLE.WS_VALUE2 = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.substring(0, WS_TEMP_VARIABLE.WS_LEN);
            } else {
                WS_TEMP_VARIABLE.WS_FROM_AREA = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE_TYPE;
                if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE == null) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE = "";
                JIBS_tmp_int = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE += " ";
                WS_TEMP_VARIABLE.WS_NAME = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE.substring(0, WS_TEMP_VARIABLE.WS_LEN);
                if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_I-1].OFFSET2 > 1) {
                    WS_INDEX.WS_IDX = BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_I-1].OFFSET2;
                }
                S000_READ_RBNM();
                if (pgmRtn) return;
                WS_TEMP_VARIABLE.WS_NAME_LEN = 30;
                if (SCCRBNM.VAL1 == null) SCCRBNM.VAL1 = "";
                JIBS_tmp_int = SCCRBNM.VAL1.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) SCCRBNM.VAL1 += " ";
                WS_TEMP_VARIABLE.WS_VALUE2 = SCCRBNM.VAL1.substring(0, WS_TEMP_VARIABLE.WS_NAME_LEN);
            }
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE2);
            S000_CMP_RBNM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "AAAAAAAA");
            CEP.TRC(SCCGWA, BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].VALUE_TYPE);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TYPE);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE1);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_VALUE2);
            CEP.TRC(SCCGWA, BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].OP);
            if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].OP.equalsIgnoreCase("1")) {
                if (SCCRBNM.RC == '2') {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'T';
                } else {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'F';
                }
            } else if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].OP.equalsIgnoreCase("2")) {
                if (SCCRBNM.RC == '4') {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'T';
                } else {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'F';
                }
            } else if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].OP.equalsIgnoreCase("3")) {
                if (SCCRBNM.RC == '3') {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'T';
                } else {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'F';
                }
            } else if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].OP.equalsIgnoreCase("4")) {
                if (SCCRBNM.RC == '4' 
                    || SCCRBNM.RC == '2') {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'T';
                } else {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'F';
                }
            } else if (BPRUNIT.REDEFINES32.REDEFINES34.UNIT_LST_DATA[WS_INDEX.WS_K-1].OP.equalsIgnoreCase("5")) {
                if (SCCRBNM.RC == '3' 
                    || SCCRBNM.RC == '2') {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'T';
                } else {
                    WS_TEMP_VARIABLE.WS_RESULT_EXPR = 'F';
                }
            }
        }
    }
    public void S000_CALL_BPZRUNIT() throws IOException,SQLException,Exception {
        BPCRUNIT.INFO.LEN = 183;
        BPCRUNIT.INFO.POINTER = BPRUNIT;
        IBS.CALLCPN(SCCGWA, CPN_R_UNIT, BPCRUNIT);
        CEP.TRC(SCCGWA, BPCRUNIT);
        if (BPCRUNIT.RETURN_INFO == 'F') {
        } else if (BPCRUNIT.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (BPCRUNIT.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBL_NOTFND, BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRUNIT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRUNIT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRUNIB() throws IOException,SQLException,Exception {
        WS_INDEX.WS_L += 1;
        BPCRUNIB.INFO.POINTER = BPRUNIT;
        BPCRUNIB.INFO.LEN = 183;
        IBS.CALLCPN(SCCGWA, CPN_R_UNIB, BPCRUNIB);
        CEP.TRC(SCCGWA, WS_INDEX.WS_L);
        CEP.TRC(SCCGWA, BPCRUNIB);
        CEP.TRC(SCCGWA, BPRUNIT);
        CEP.TRC(SCCGWA, BPCRUNIB.RETURN_INFO);
        CEP.TRC(SCCGWA, BPRUNIT.KEY.UNIT_NO);
        if (BPCRUNIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRUNIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_RBNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRBNM);
        SCCRBNM.FUNC = '0';
        if (WS_TEMP_VARIABLE.WS_FROM_AREA == '0') {
            SCCRBNM.COPYBOOK = GWA_SC_AREA.TR_ATTR_AREA.TR_BUFF_NAME;
        } else {
            SCCRBNM.COPYBOOK = "SCCGWA";
            SCCRBNM.DATA_PTR = SCCGWA;
        }
        SCCRBNM.VAR_NAME = WS_TEMP_VARIABLE.WS_NAME;
        CEP.TRC(SCCGWA, SCCRBNM.COPYBOOK);
        CEP.TRC(SCCGWA, SCCRBNM.VAR_NAME);
        CEP.TRC(SCCGWA, "END OF READ RBNM......");
        CEP.TRC(SCCGWA, SCCRBNM);
        CEP.TRC(SCCGWA, SCCRBNM.RC);
        CEP.TRC(SCCGWA, SCCRBNM.VAL1);
        CEP.TRC(SCCGWA, SCCRBNM.TYPE);
        if (SCCRBNM.RC == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CMP_RBNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRBNM);
        SCCRBNM.FUNC = '1';
        SCCRBNM.TYPE = WS_TEMP_VARIABLE.WS_TYPE;
        SCCRBNM.VAL1 = WS_TEMP_VARIABLE.WS_VALUE1;
        SCCRBNM.VAL2 = WS_TEMP_VARIABLE.WS_VALUE2;
        CEP.TRC(SCCGWA, SCCRBNM);
        CEP.TRC(SCCGWA, SCCRBNM.VAL1);
        CEP.TRC(SCCGWA, SCCRBNM.VAL2);
        CEP.TRC(SCCGWA, SCCRBNM.TYPE);
        CEP.TRC(SCCGWA, "END OF COMPARE RBNM......");
        CEP.TRC(SCCGWA, SCCRBNM);
    }
    public void S000_CALL_BPZFAMTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_AMT_TBL_AUTH, BPCFAMTA);
        if (BPCFAMTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFAMTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_STS_TBL_AUTH, BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFRWDA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_RW_PBDA, BPCFRWDA);
        if (BPCFRWDA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFRWDA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCTRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
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
        if (BPCFCTRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCTRL = ");
            CEP.TRC(SCCGWA, BPCFCTRL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
