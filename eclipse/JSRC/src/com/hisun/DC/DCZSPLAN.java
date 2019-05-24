package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPLAN {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTBINPM_RD;
    DBParm DCTPDBIN_RD;
    DBParm DCTDCSEQ_RD;
    DBParm DCTBRARC_RD;
    DBParm DCTCDORD_RD;
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARK = "THE CARD PRODUCT PLAN MAINTAIN";
    String K_HIS_COPYBOOK = "DCRBINPM";
    String CPN_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String K_OUTPUT_FMT = "DC072";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    short WS_CARD_LEN = 0;
    short WS_CARD_SEG1_LEN = 0;
    short WS_TEMP_SEG1_LEN = 0;
    long WS_OLD_S_SEQ = 0;
    long WS_OLD_E_SEQ = 0;
    DCZSPLAN_WS_GENERATE WS_GENERATE = new DCZSPLAN_WS_GENERATE();
    DCZSPLAN_WS_OUTPUT WS_OUTPUT = new DCZSPLAN_WS_OUTPUT();
    int WS_CNT = 0;
    long WS_CNT_CHK = 0;
    String WS_CHK_CARD_BIN = " ";
    long WS_CHK_CARD_SEG1 = 0;
    long WS_CHK_SEQ = 0;
    long WS_CHK_S_SEQ = 0;
    long WS_CHK_E_SEQ = 0;
    String WS_CHK_S_CARD_NO = " ";
    String WS_CHK_E_CARD_NO = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPDBIN DCRPDBIN = new DCRPDBIN();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRCDORD DCRCDORD = new DCRCDORD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHPLAN DCCHPLAN = new DCCHPLAN();
    DCCHPLAN DCCOPLAN = new DCCHPLAN();
    DCCHPLAN DCCNPLAN = new DCCHPLAN();
    DCRPDBIN DCRPDBIA = new DCRPDBIN();
    DCRPDBIN DCRPDBIB = new DCRPDBIN();
    DCRDCSEQ DCRDCSEQ = new DCRDCSEQ();
    DCRBRARC DCRBRARC = new DCRBRARC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPLAN DCCSPLAN;
    public void MP(SCCGWA SCCGWA, DCCSPLAN DCCSPLAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPLAN = DCCSPLAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPLAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSPLAN.FUNC);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCSPLAN.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (DCCSPLAN.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (DCCSPLAN.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B060_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B070_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSPLAN.CARD_PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_MISSING);
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCSPLAN.CARD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.STOP_SOLD_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE > BPCPQPRD.EXP_DATE) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_EXPIRED);
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        if (DCCSPLAN.CARD_BIN.equalsIgnoreCase("623521") 
            || DCCSPLAN.CARD_BIN.equalsIgnoreCase("622855") 
            || DCCSPLAN.CARD_BIN.equalsIgnoreCase("621241") 
            || DCCSPLAN.CARD_BIN.equalsIgnoreCase("621461")) {
            if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
            } else {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CBIN_MATCH_CPROD);
            }
        }
        if (DCCSPLAN.CARD_BIN.equalsIgnoreCase("960670") 
            || DCCSPLAN.CARD_BIN.equalsIgnoreCase("623265")) {
            if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'C') {
            } else {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BIN_NOT_MATCH_PROD);
            }
        }
        if (DCCSPLAN.CARD_SEG1_RULE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_SEG1_RULE_MISSING);
        }
        if (DCCSPLAN.FUNC == 'A') {
            IBS.init(SCCGWA, DCRPDBIN);
            DCRPDBIN.KEY.CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
            DCRPDBIN.KEY.CARD_BIN = DCCSPLAN.CARD_BIN;
            T000_READ_DCTPDBIN_CK();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                if (!DCRPDBIN.CARD_SEG1_RUL.equalsIgnoreCase(DCCSPLAN.CARD_SEG1_RULE)) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_SEG1_RULE_ERROR);
                }
                if (DCRPDBIN.KEY.CARD_SEG1 != DCCSPLAN.CARD_SEG1) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_S_SEG1_ERROR);
                }
            }
        }
        IBS.init(SCCGWA, DCRBINPM);
        DCRBINPM.KEY.BIN = DCCSPLAN.CARD_BIN;
        CEP.TRC(SCCGWA, DCRBINPM);
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        WS_CARD_LEN = DCRBINPM.CARD_LEN;
        if (DCCSPLAN.S_SEQ > DCCSPLAN.E_SEQ) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_S_SEQ_GT_E_SEQ);
        }
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        if (DCCSPLAN.CARD_SEG1_RULE.equalsIgnoreCase("00")) {
            IBS.init(SCCGWA, DCRBRARC);
            DCRBRARC.AREA_NO = (short) DCCSPLAN.CARD_SEG1;
            T000_READ_DCTBRARC_FIRST();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_S_SEG1_ERROR);
            }
        }
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_SEG1);
        WS_CHK_CARD_BIN = DCCSPLAN.CARD_BIN;
        WS_CHK_CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        WS_CHK_S_SEQ = DCCSPLAN.S_SEQ;
        WS_CHK_E_SEQ = DCCSPLAN.E_SEQ;
        T000_CHECK_CARDSEQ_ACORSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT_CHK);
        if (WS_CNT_CHK != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_SEQ_ALREADY_PLANNED);
        }
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_SEG1);
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = DCCSPLAN.CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        T000_READ_DCTPDBIN_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.SEQ);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCCSPLAN.SEQ = (short) (DCRPDBIN.KEY.SEQ + 1);
        } else {
            DCCSPLAN.SEQ = 1;
        }
        CEP.TRC(SCCGWA, DCCSPLAN.SEQ);
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = DCCSPLAN.CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        DCRPDBIN.KEY.SEQ = DCCSPLAN.SEQ;
        DCRPDBIN.CARD_SEG1_RUL = DCCSPLAN.CARD_SEG1_RULE;
        DCRPDBIN.S_SEQ = DCCSPLAN.S_SEQ;
        DCRPDBIN.E_SEQ = DCCSPLAN.E_SEQ;
        DCRPDBIN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPDBIN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRPDBIN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPDBIN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSPLAN.SEQ);
        CEP.TRC(SCCGWA, DCCSPLAN.S_SEQ);
        CEP.TRC(SCCGWA, DCCSPLAN.E_SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        T000_ADD_DCTPDBIN();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRPDBIN, DCRPDBIB);
    }
    public void B040_MODIFY_PROC() throws IOException,SQLException,Exception {
        if (DCCSPLAN.CARD_SEG1_RULE.equalsIgnoreCase("00")) {
            IBS.init(SCCGWA, DCRBRARC);
            DCRBRARC.AREA_NO = (short) DCCSPLAN.CARD_SEG1;
            T000_READ_DCTBRARC_FIRST();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_S_SEG1_ERROR);
            }
        }
        WS_CHK_CARD_BIN = DCCSPLAN.CARD_BIN;
        WS_CHK_CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        WS_CHK_SEQ = DCCSPLAN.SEQ;
        WS_CHK_S_SEQ = DCCSPLAN.S_SEQ;
        WS_CHK_E_SEQ = DCCSPLAN.E_SEQ;
        T001_CHECK_CARDSEQ_ACORSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT_CHK);
        if (WS_CNT_CHK != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQ_ALREADY_PLANNED;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SEQ_ALREADY_PLANNED, DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = DCCSPLAN.CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        DCRPDBIN.KEY.SEQ = DCCSPLAN.SEQ;
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSPLAN.CARD_SEG1);
        CEP.TRC(SCCGWA, DCCSPLAN.SEQ);
        T000_READUPD_DCTPDBIN();
        if (pgmRtn) return;
        WS_OLD_S_SEQ = DCRPDBIN.S_SEQ;
        WS_OLD_E_SEQ = DCRPDBIN.E_SEQ;
        if (DCCSPLAN.E_SEQ < DCRPDBIN.E_SEQ) {
            if (DCCSPLAN.CARD_SEG1 == 0 
                || DCCSPLAN.CARD_SEG1 == 9999) {
                IBS.init(SCCGWA, DCRDCSEQ);
                DCRDCSEQ.KEY.CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
                DCRDCSEQ.KEY.CARD_BIN = DCCSPLAN.CARD_BIN;
                DCRDCSEQ.KEY.CARD_SEG1 = DCCSPLAN.CARD_SEG1;
                T000_READ_DCTDCSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCRDCSEQ.CUR_SEQ);
                if (DCRDCSEQ.CUR_SEQ > DCCSPLAN.E_SEQ) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD);
                }
            } else {
                WS_CHK_E_SEQ = DCCSPLAN.E_SEQ;
                WS_CHK_CARD_BIN = DCCSPLAN.CARD_BIN;
                T000_GROUP_CHECK1_DCTDCSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_CNT_CHK);
                if (WS_CNT_CHK != 0) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD);
                }
            }
        }
        IBS.CLONE(SCCGWA, DCRPDBIN, DCRPDBIA);
        DCRPDBIN.S_SEQ = DCCSPLAN.S_SEQ;
        DCRPDBIN.E_SEQ = DCCSPLAN.E_SEQ;
        DCRPDBIN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPDBIN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTPDBIN();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRPDBIN, DCRPDBIB);
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        WS_CHK_S_SEQ = DCCSPLAN.S_SEQ;
        WS_CHK_CARD_BIN = DCCSPLAN.CARD_BIN;
        T000_GROUP_CHECK2_DCTDCSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT_CHK);
        if (WS_CNT_CHK != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD);
        }
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = DCCSPLAN.CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        DCRPDBIN.KEY.SEQ = DCCSPLAN.SEQ;
        T000_READUPD_DCTPDBIN();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRPDBIN, DCRPDBIA);
        T000_DELETE_DCTPDBIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_PROD_CD = DCCSPLAN.CARD_PROD_CD;
        WS_OUTPUT.WS_CARD_BIN = DCCSPLAN.CARD_BIN;
        WS_OUTPUT.WS_CARD_SEG1 = DCCSPLAN.CARD_SEG1;
        WS_OUTPUT.WS_CARD_SEG = DCCSPLAN.SEQ;
        WS_OUTPUT.WS_CARD_SEG1_RULE = DCCSPLAN.CARD_SEG1_RULE;
        WS_OUTPUT.WS_S_SEQ = DCCSPLAN.S_SEQ;
        WS_OUTPUT.WS_E_SEQ = DCCSPLAN.E_SEQ;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CARD_PROD_CD);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CARD_BIN);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CARD_SEG1);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CARD_SEG);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_S_SEQ);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_E_SEQ);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_LAST_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_LAST_USER);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 85;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 127;
        if (DCCSPLAN.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRPDBIB;
        }
        if (DCCSPLAN.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRPDBIA;
        }
        if (DCCSPLAN.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRPDBIA;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRPDBIB;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B041_CHECK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_GENERATE);
        WS_GENERATE.WS_I_SEQ = DCCSPLAN.E_SEQ + 1;
        R001_GENERATE_CARD_NO();
        if (pgmRtn) return;
        WS_CHK_S_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO;
        IBS.init(SCCGWA, WS_GENERATE);
        WS_GENERATE.WS_I_SEQ = DCRPDBIN.E_SEQ;
        R001_GENERATE_CARD_NO();
        if (pgmRtn) return;
        WS_CHK_E_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO;
        IBS.init(SCCGWA, DCRCDORD);
        WS_CNT_CHK = 0;
        if (WS_CHK_S_CARD_NO == null) WS_CHK_S_CARD_NO = "";
        JIBS_tmp_int = WS_CHK_S_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CHK_S_CARD_NO += " ";
        JIBS_tmp_str[0] = "" + 0;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CHK_S_CARD_NO = WS_CHK_S_CARD_NO.substring(0, WS_CARD_LEN - 1) + JIBS_tmp_str[0] + WS_CHK_S_CARD_NO.substring(WS_CARD_LEN + 1 - 1);
        if (WS_CHK_E_CARD_NO == null) WS_CHK_E_CARD_NO = "";
        JIBS_tmp_int = WS_CHK_E_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CHK_E_CARD_NO += " ";
        JIBS_tmp_str[0] = "" + 9;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CHK_E_CARD_NO = WS_CHK_E_CARD_NO.substring(0, WS_CARD_LEN - 1) + JIBS_tmp_str[0] + WS_CHK_E_CARD_NO.substring(WS_CARD_LEN + 1 - 1);
        CEP.TRC(SCCGWA, WS_CHK_S_CARD_NO);
        CEP.TRC(SCCGWA, WS_CHK_E_CARD_NO);
        T000_GROUP_DCTCDORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT_CHK);
        if (WS_CNT_CHK != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD, DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B042_CHECK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_GENERATE);
        WS_GENERATE.WS_I_SEQ = DCRPDBIN.S_SEQ;
        R001_GENERATE_CARD_NO();
        if (pgmRtn) return;
        WS_CHK_S_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO;
        IBS.init(SCCGWA, WS_GENERATE);
        WS_GENERATE.WS_I_SEQ = DCCSPLAN.S_SEQ - 1;
        R001_GENERATE_CARD_NO();
        if (pgmRtn) return;
        WS_CHK_E_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO;
        IBS.init(SCCGWA, DCRCDORD);
        WS_CNT_CHK = 0;
        if (WS_CHK_S_CARD_NO == null) WS_CHK_S_CARD_NO = "";
        JIBS_tmp_int = WS_CHK_S_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CHK_S_CARD_NO += " ";
        JIBS_tmp_str[0] = "" + 0;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CHK_S_CARD_NO = WS_CHK_S_CARD_NO.substring(0, WS_CARD_LEN - 1) + JIBS_tmp_str[0] + WS_CHK_S_CARD_NO.substring(WS_CARD_LEN + 1 - 1);
        if (WS_CHK_E_CARD_NO == null) WS_CHK_E_CARD_NO = "";
        JIBS_tmp_int = WS_CHK_E_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CHK_E_CARD_NO += " ";
        JIBS_tmp_str[0] = "" + 9;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CHK_E_CARD_NO = WS_CHK_E_CARD_NO.substring(0, WS_CARD_LEN - 1) + JIBS_tmp_str[0] + WS_CHK_E_CARD_NO.substring(WS_CARD_LEN + 1 - 1);
        CEP.TRC(SCCGWA, WS_CHK_S_CARD_NO);
        CEP.TRC(SCCGWA, WS_CHK_E_CARD_NO);
        T000_GROUP_DCTCDORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT_CHK);
        if (WS_CNT_CHK != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_OVER_PLAN_H_USED_CAD, DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R001_GENERATE_CARD_NO() throws IOException,SQLException,Exception {
        if (WS_GENERATE.WS_GEN_CARD_NO == null) WS_GENERATE.WS_GEN_CARD_NO = "";
        JIBS_tmp_int = WS_GENERATE.WS_GEN_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_GENERATE.WS_GEN_CARD_NO += " ";
        if (DCCSPLAN.CARD_BIN == null) DCCSPLAN.CARD_BIN = "";
        JIBS_tmp_int = DCCSPLAN.CARD_BIN.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) DCCSPLAN.CARD_BIN += " ";
        WS_GENERATE.WS_GEN_CARD_NO = DCCSPLAN.CARD_BIN + WS_GENERATE.WS_GEN_CARD_NO.substring(6);
        if (DCRBINPM.SEG_NUM == 1) {
            CEP.TRC(SCCGWA, "KIA = SEG1");
            WS_GENERATE.WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - 1;
            WS_GENERATE.WS_START = 15 - WS_GENERATE.WS_SEQ_LEN + 1;
            WS_GENERATE.WS_SEQNO = WS_GENERATE.WS_I_SEQ;
            JIBS_tmp_str[0] = "" + WS_GENERATE.WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_GENERATE.WS_GEN_CARD_NO == null) WS_GENERATE.WS_GEN_CARD_NO = "";
            JIBS_tmp_int = WS_GENERATE.WS_GEN_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) WS_GENERATE.WS_GEN_CARD_NO += " ";
            WS_GENERATE.WS_GEN_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_GENERATE.WS_START - 1, WS_GENERATE.WS_START + WS_GENERATE.WS_SEQ_LEN - 1) + WS_GENERATE.WS_GEN_CARD_NO.substring(7 + WS_GENERATE.WS_SEQ_LEN - 1);
        } else {
            CEP.TRC(SCCGWA, "KIA = SEG2");
            WS_GENERATE.WS_SEG1_LEN = DCRBINPM.SEG1_LEN;
            WS_GENERATE.WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - DCRBINPM.SEG1_LEN - 1;
            WS_GENERATE.WS_START = 15 - DCRBINPM.SEG1_LEN + 1;
            CEP.TRC(SCCGWA, DCCSPLAN.CARD_SEG1);
            CEP.TRC(SCCGWA, WS_GENERATE.WS_START);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            JIBS_tmp_str[0] = "" + DCCSPLAN.CARD_SEG1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_GENERATE.WS_GEN_CARD_NO == null) WS_GENERATE.WS_GEN_CARD_NO = "";
            JIBS_tmp_int = WS_GENERATE.WS_GEN_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) WS_GENERATE.WS_GEN_CARD_NO += " ";
            WS_GENERATE.WS_GEN_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_GENERATE.WS_START - 1, WS_GENERATE.WS_START + DCRBINPM.SEG1_LEN - 1) + WS_GENERATE.WS_GEN_CARD_NO.substring(7 + DCRBINPM.SEG1_LEN - 1);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            CEP.TRC(SCCGWA, WS_GENERATE.WS_GEN_CARD_NO);
            WS_GENERATE.WS_START = 15 - WS_GENERATE.WS_SEQ_LEN + 1;
            WS_GENERATE.WS_SEQNO = WS_GENERATE.WS_I_SEQ;
            WS_GENERATE.WS_PTR = 6 + DCRBINPM.SEG1_LEN + 1;
            JIBS_tmp_str[0] = "" + WS_GENERATE.WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_GENERATE.WS_GEN_CARD_NO == null) WS_GENERATE.WS_GEN_CARD_NO = "";
            JIBS_tmp_int = WS_GENERATE.WS_GEN_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) WS_GENERATE.WS_GEN_CARD_NO += " ";
            WS_GENERATE.WS_GEN_CARD_NO = WS_GENERATE.WS_GEN_CARD_NO.substring(0, WS_GENERATE.WS_PTR - 1) + JIBS_tmp_str[0].substring(WS_GENERATE.WS_START - 1, WS_GENERATE.WS_START + WS_GENERATE.WS_SEQ_LEN - 1) + WS_GENERATE.WS_GEN_CARD_NO.substring(WS_GENERATE.WS_PTR + WS_GENERATE.WS_SEQ_LEN - 1);
        }
        CEP.TRC(SCCGWA, WS_GENERATE.WS_GEN_CARD_NO);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CEID_EXP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BINF_NOTFND;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_BINF_NOTFND, DCCSPLAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTPDBIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.SEQ);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, SEQ, CARD_SEG1_RUL, S_SEQ, E_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPDBIN_RD.upd = true;
        IBS.READ(SCCGWA, DCRPDBIN, DCTPDBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_UPDATE_DCTPDBIN() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.col = "CARD_SEG1_RUL, S_SEQ, E_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1 "
            + "AND SEQ = :DCRPDBIN.KEY.SEQ";
        IBS.REWRITE(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_DELETE_DCTPDBIN() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        IBS.DELETE(SCCGWA, DCRPDBIN, DCTPDBIN_RD);
    }
    public void T000_ADD_DCTPDBIN() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        IBS.WRITE(SCCGWA, DCRPDBIN, DCTPDBIN_RD);
    }
    public void T000_READ_DCTDCSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRDCSEQ.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRDCSEQ.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRDCSEQ.KEY.CARD_SEG1);
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUPD_DCTDCSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRDCSEQ.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRDCSEQ.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRDCSEQ.KEY.CARD_SEG1);
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDCSEQ_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_UPDATE_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDCSEQ_RD.where = "CARD_PROD_CD = :DCRDCSEQ.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRDCSEQ.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRDCSEQ.KEY.CARD_SEG1";
        IBS.REWRITE(SCCGWA, DCRDCSEQ, this, DCTDCSEQ_RD);
    }
    public void T000_CHECK_CARDSEQ_ACORSS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHK_CARD_BIN);
        CEP.TRC(SCCGWA, WS_CHK_CARD_SEG1);
        CEP.TRC(SCCGWA, WS_CHK_S_SEQ);
        CEP.TRC(SCCGWA, WS_CHK_E_SEQ);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.set = "WS-CNT-CHK=COUNT(*)";
        DCTPDBIN_RD.where = "CARD_BIN = :WS_CHK_CARD_BIN "
            + "AND CARD_SEG1 = :WS_CHK_CARD_SEG1 "
            + "AND ( ( S_SEQ <= :WS_CHK_S_SEQ "
            + "AND S_SEQ <= :WS_CHK_E_SEQ "
            + "AND E_SEQ >= :WS_CHK_E_SEQ ) "
            + "OR ( S_SEQ <= :WS_CHK_E_SEQ "
            + "AND S_SEQ <= :WS_CHK_S_SEQ "
            + "AND E_SEQ >= :WS_CHK_S_SEQ "
            + "AND E_SEQ <= :WS_CHK_E_SEQ ) "
            + "OR ( S_SEQ >= :WS_CHK_S_SEQ "
            + "AND S_SEQ <= :WS_CHK_E_SEQ "
            + "AND E_SEQ >= :WS_CHK_S_SEQ "
            + "AND E_SEQ <= :WS_CHK_E_SEQ ) "
            + "OR ( S_SEQ >= :WS_CHK_S_SEQ "
            + "AND S_SEQ <= :WS_CHK_E_SEQ "
            + "AND E_SEQ >= :WS_CHK_E_SEQ ) )";
        IBS.GROUP(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T001_CHECK_CARDSEQ_ACORSS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHK_CARD_BIN);
        CEP.TRC(SCCGWA, WS_CHK_CARD_SEG1);
        CEP.TRC(SCCGWA, WS_CHK_S_SEQ);
        CEP.TRC(SCCGWA, WS_CHK_E_SEQ);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.set = "WS-CNT-CHK=COUNT(*)";
        DCTPDBIN_RD.where = "CARD_BIN = :WS_CHK_CARD_BIN "
            + "AND CARD_SEG1 = :WS_CHK_CARD_SEG1 "
            + "AND ( ( S_SEQ <= :WS_CHK_S_SEQ "
            + "AND S_SEQ <= :WS_CHK_E_SEQ "
            + "AND E_SEQ >= :WS_CHK_E_SEQ ) "
            + "OR ( S_SEQ <= :WS_CHK_E_SEQ "
            + "AND S_SEQ <= :WS_CHK_S_SEQ "
            + "AND E_SEQ >= :WS_CHK_S_SEQ "
            + "AND E_SEQ <= :WS_CHK_E_SEQ ) "
            + "OR ( S_SEQ >= :WS_CHK_S_SEQ "
            + "AND S_SEQ <= :WS_CHK_E_SEQ "
            + "AND E_SEQ >= :WS_CHK_S_SEQ "
            + "AND E_SEQ <= :WS_CHK_E_SEQ ) "
            + "OR ( S_SEQ >= :WS_CHK_S_SEQ "
            + "AND S_SEQ <= :WS_CHK_E_SEQ "
            + "AND E_SEQ >= :WS_CHK_E_SEQ ) ) "
            + "AND ( S_SEQ < > :WS_CHK_S_SEQ "
            + "AND E_SEQ < > :WS_CHK_E_SEQ )";
        IBS.GROUP(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_GROUP_CHECK1_DCTDCSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHK_CARD_BIN);
        CEP.TRC(SCCGWA, WS_CHK_E_SEQ);
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.set = "WS-CNT-CHK=COUNT(*)";
        DCTDCSEQ_RD.where = "CARD_BIN = :WS_CHK_CARD_BIN "
            + "AND CUR_SEQ >= :WS_CHK_E_SEQ";
        IBS.GROUP(SCCGWA, DCRDCSEQ, this, DCTDCSEQ_RD);
    }
    public void T000_GROUP_CHECK2_DCTDCSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHK_CARD_BIN);
        CEP.TRC(SCCGWA, WS_CHK_S_SEQ);
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.set = "WS-CNT-CHK=COUNT(*)";
        DCTDCSEQ_RD.where = "CARD_BIN = :WS_CHK_CARD_BIN "
            + "AND CUR_SEQ >= :WS_CHK_S_SEQ";
        IBS.GROUP(SCCGWA, DCRDCSEQ, this, DCTDCSEQ_RD);
    }
    public void T000_GROUP_DCTPDBIN() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.set = "WS-CNT=COUNT(*)";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        IBS.GROUP(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_READ_DCTPDBIN_FIRST() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_READ_DCTPDBIN_CK() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTPDBIN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTBRARC_FIRST() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        DCTBRARC_RD.where = "AREA_NO = :DCRBRARC.AREA_NO";
        DCTBRARC_RD.fst = true;
        DCTBRARC_RD.order = "BR_NO";
        IBS.READ(SCCGWA, DCRBRARC, this, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTBRARC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DCTCDORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT_CHK);
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.set = "WS-CNT-CHK=COUNT(*)";
        DCTCDORD_RD.where = "CARD_NO BETWEEN :WS_CHK_S_CARD_NO "
            + "AND :WS_CHK_E_CARD_NO";
        IBS.GROUP(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
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
        if (DCCSPLAN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCSPLAN=");
            CEP.TRC(SCCGWA, DCCSPLAN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
