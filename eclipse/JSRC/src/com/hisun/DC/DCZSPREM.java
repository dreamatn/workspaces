package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DCZSPREM {
    boolean pgmRtn = false;
    String CPN_DCZPQPRD = "DC-P-QUERY-PROD";
    String CPN_BPZSGSEQ = "BP-S-GET-SEQ";
    String CPN_DCZSGSEQ = "DC-S-GET-SEQ";
    String CPN_DCZFCDGG = "DC-F-CHK-DIGIT-GEN";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_DCZPCDCD = "DC-GEN-COD-EN       ";
    String K_OUTPUT_FMT = "DC011";
    String WS_ERR_MSG = " ";
    String WS_CARDNO = " ";
    int WS_I = 0;
    int WS_J = 0;
    long WS_SEQNO = 0;
    long WS_BATCHNO = 0;
    DCZSPREM_WS_TEMP_BATNO WS_TEMP_BATNO = new DCZSPREM_WS_TEMP_BATNO();
    int WS_SEQ_LEN = 0;
    int WS_SEG1_LEN = 0;
    int WS_START = 0;
    int WS_PTR = 0;
    int WS_COUNT = 0;
    int WS_COUNT_PSWMT = 0;
    char WS_END_FLG = ' ';
    char WS_WRT_FLG = ' ';
    char WS_CHK_END_FLG = ' ';
    String WS_CHK_CARDNO = " ";
    char WS_END_PDBIN_FLG = ' ';
    String WS_SPREM_CARD_BIN = " ";
    long WS_SPREM_CARD_SEG1 = 0;
    long WS_SPREM_CARD_SEG2 = 0;
    String WS_CARD_SEG1_RUL = " ";
    char WS_SPREM_MIDA_TYP = ' ';
    String WS_SPREM_APP_TYP = " ";
    String WS_SPREM_BV_CD = " ";
    int WS_CEID_EXP_DT = 0;
    long WS_CUR_SEQ = 0;
    long WS_S_SEQNO = 0;
    long WS_E_SEQNO1 = 0;
    long WS_E_SEQNO = 0;
    int WS_CARD_SEG = 0;
    DCZSPREM_WS_OUTPUT WS_OUTPUT = new DCZSPREM_WS_OUTPUT();
    int WS_CNT_ORD = 0;
    int WS_CNT_BIN = 0;
    int WS_CNT_BIN2 = 0;
    long WS_AP_SEQ = 0;
    int WS_CNT_T = 0;
    long WS_MAX_BATCHNO = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCRCDAPP DCRCDAPP = new DCRCDAPP();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHPREM DCCHPREM = new DCCHPREM();
    DCCHPREM DCCOPREM = new DCCHPREM();
    DCCHPREM DCCNPREM = new DCCHPREM();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DCCSGSEQ DCCSDSEQ = new DCCSGSEQ();
    DCRDCCLS DCRDCCLS = new DCRDCCLS();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DCRDCSEQ DCRDCSEQ = new DCRDCSEQ();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRPDBIN DCRPDBIN = new DCRPDBIN();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRPSWMT DCRPSWMT = new DCRPSWMT();
    DCRBRARC DCRBRARC = new DCRBRARC();
    DCCPCDCD DCCPCDCD = new DCCPCDCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCRBRCLC DCRBRCLC = new DCRBRCLC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPREM DCCSPREM;
    DCCSCLCS DCCSCLCS;
    public void MP(SCCGWA SCCGWA, DCCSPREM DCCSPREM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPREM = DCCSPREM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPREM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_BATNO();
        if (pgmRtn) return;
        B021_GET_BIN_SEG1();
        if (pgmRtn) return;
        B022_GET_BV_CD_NO();
        if (pgmRtn) return;
        B030_ADD_PREMAKE_APP_RECORD();
        if (pgmRtn) return;
        B035_APP_NUM_PRECHECK();
        if (pgmRtn) return;
        B050_HISTORY_PROCESS();
        if (pgmRtn) return;
        B060_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPREM.CARD_PROD);
        CEP.TRC(SCCGWA, DCCSPREM.CARD_CLS_CD);
        CEP.TRC(SCCGWA, DCCSPREM.APP_NUM);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCSPREM.CARD_PROD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.STOP_SOLD_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE > BPCPQPRD.EXP_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_EXPIRED;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_EXPIRED, DCCSPREM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCDQPRD);
        DCCDQPRD.VAL.KEY.PROD_CD = DCCSPREM.CARD_PROD;
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = DCCSPREM.CARD_PROD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.IC_APP_TYP);
        WS_SPREM_APP_TYP = DCCDQPRD.VAL.DATA.IC_APP_TYP;
        if (DCCSPREM.APP_NUM < 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CD_NO_LT_ZERO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CD_NO_LT_ZERO, DCCSPREM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CARD_BATNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "DC";
        BPCSGSEQ.CODE = "BATNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.NUM = 1;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1).trim().length() == 0) WS_TEMP_BATNO.WS_TEMP_SEQ = 0;
        else WS_TEMP_BATNO.WS_TEMP_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1));
        WS_TEMP_BATNO.WS_TEMP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_BATNO);
        WS_BATCHNO = Long.parseLong(JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, WS_BATCHNO);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B021_GET_BIN_SEG1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPREM.CARD_PROD);
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSPREM.CARD_PROD;
        T000_READ_DCTPDBIN_FIRST();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            WS_SPREM_CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
            WS_SPREM_CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
            WS_CARD_SEG1_RUL = DCRPDBIN.CARD_SEG1_RUL;
        }
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_CARD_PROD_PLAN);
        }
    }
    public void B022_GET_BV_CD_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCCSPREM.CARD_CLS_CD);
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSPREM.CARD_PROD;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCSPREM.CARD_CLS_CD;
        T000_READ_DCTDCCLS_FIRST();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BV_NONE_ERR);
        }
        CEP.TRC(SCCGWA, DCRDCCLS.BV_CD_NO);
        if (WS_TBL_FLAG == 'Y') {
            WS_SPREM_BV_CD = DCRDCCLS.BV_CD_NO;
        }
    }
    public void B030_ADD_PREMAKE_APP_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPREM.CARD_PROD);
        CEP.TRC(SCCGWA, WS_SPREM_CARD_BIN);
        CEP.TRC(SCCGWA, WS_SPREM_CARD_SEG1);
        CEP.TRC(SCCGWA, DCRPDBIN.CARD_SEG1_RUL);
        CEP.TRC(SCCGWA, WS_BATCHNO);
        IBS.init(SCCGWA, DCRCDAPP);
        DCRCDAPP.KEY.APP_BAT_NO = "" + WS_BATCHNO;
        JIBS_tmp_int = DCRCDAPP.KEY.APP_BAT_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) DCRCDAPP.KEY.APP_BAT_NO = "0" + DCRCDAPP.KEY.APP_BAT_NO;
        DCRCDAPP.PROD_CD = DCCSPREM.CARD_PROD;
        DCRCDAPP.CARD_CLS_CD = DCCSPREM.CARD_CLS_CD;
        DCRCDAPP.BV_CD_NO = WS_SPREM_BV_CD;
        DCRCDAPP.CARD_BIN = WS_SPREM_CARD_BIN;
        if (WS_CARD_SEG1_RUL.equalsIgnoreCase("00")) {
            CEP.TRC(SCCGWA, WS_CARD_SEG1_RUL);
            DCRCDAPP.CARD_SEG = WS_SPREM_CARD_SEG1;
        } else {
            if (WS_SPREM_CARD_BIN.equalsIgnoreCase("623265")) {
                JIBS_tmp_str[0] = "" + WS_CARD_SEG;
                JIBS_f0 = "";
                for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_CARD_SEG;
                JIBS_tmp_str[1] = "" + 42;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(2);
                WS_CARD_SEG = Integer.parseInt(JIBS_NumStr);
                DCRBRARC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_READ_DCTBRARC_FIRST();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y') {
                    JIBS_tmp_str[0] = "" + WS_CARD_SEG;
                    JIBS_f0 = "";
                    for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_CARD_SEG;
                    JIBS_tmp_str[1] = "" + DCRBRARC.AREA_NO;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(3 + 4 - 1);
                    WS_CARD_SEG = Integer.parseInt(JIBS_NumStr);
                }
                DCRCDAPP.CARD_SEG = WS_CARD_SEG;
            } else {
                DCRBRARC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_READ_DCTBRARC_FIRST();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y') {
                    DCRCDAPP.CARD_SEG = DCRBRARC.AREA_NO;
                }
            }
        }
        DCRCDAPP.APP_TYP = WS_SPREM_APP_TYP;
        DCRCDAPP.APP_NUM = DCCSPREM.APP_NUM;
        DCRCDAPP.APP_STS = '0';
        DCRCDAPP.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDAPP.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDAPP.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDAPP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDAPP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDAPP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDAPP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DCRCDAPP.PROD_CD);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_BIN);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_SEG);
        T000_ADD_DCTCDAPP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDAPP.PROD_CD);
    }
    public void B035_APP_NUM_PRECHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPREM.CARD_PROD);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_BIN);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_SEG);
        IBS.init(SCCGWA, DCRDCSEQ);
        DCRDCSEQ.KEY.CARD_PROD_CD = DCCSPREM.CARD_PROD;
        DCRDCSEQ.KEY.CARD_BIN = DCRCDAPP.CARD_BIN;
        DCRDCSEQ.KEY.CARD_SEG1 = DCRCDAPP.CARD_SEG;
        T000_READ_DCTDCSEQ_FIRST();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_CUR_SEQ = 0;
        }
        if (WS_TBL_FLAG == 'Y') {
            WS_CUR_SEQ = DCRDCSEQ.CUR_SEQ;
        }
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSPREM.CARD_PROD;
        DCRPDBIN.KEY.CARD_BIN = DCRCDAPP.CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = WS_SPREM_CARD_SEG1;
        T000_READ_DCTPDBIN_W();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_PROD_BIN);
        }
        if (WS_CUR_SEQ == 0) {
            WS_CUR_SEQ = DCRPDBIN.S_SEQ;
        }
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.SEQ);
        CEP.TRC(SCCGWA, WS_CUR_SEQ);
        WS_E_SEQNO = WS_CUR_SEQ + DCCSPREM.APP_NUM - 1;
        CEP.TRC(SCCGWA, WS_E_SEQNO);
        CEP.TRC(SCCGWA, DCRPDBIN.E_SEQ);
        if (WS_E_SEQNO > DCRPDBIN.E_SEQ) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NUM_NOT_ENOUGH);
        }
    }
    public void B040_ADD_ORDER_LIST_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        DCRBINPM.KEY.BIN = WS_SPREM_CARD_BIN;
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        WS_CEID_EXP_DT = DCRBINPM.CEID_EXP;
        if (DCRBINPM.SEG_NUM == 1) {
            WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - 1;
        } else {
            WS_SEG1_LEN = DCRBINPM.SEG1_LEN;
            WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - DCRBINPM.SEG1_LEN - 1;
        }
        CEP.TRC(SCCGWA, WS_SEQ_LEN);
