package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSGSEQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTDCSEQ_RD;
    DBParm DCTPDBIN_RD;
    brParm DCTPDBIN_BR = new brParm();
    DBParm DCTBINPM_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    boolean pgmRtn = false;
    String CPN_DCZPQPRD = "DC-P-QUERY-PROD";
    String CPN_DCZFCDGG = "DC-F-CHK-DIGIT-GEN";
    String WS_ERR_MSG = " ";
    String WS_CARDNO = " ";
    int WS_I = 0;
    long WS_CUR_SEQ = 0;
    long WS_S_SEQNO = 0;
    long WS_E_SEQNO1 = 0;
    long WS_E_SEQNO = 0;
    int WS_LEFT_APP_NUM = 0;
    int WS_NUM = 0;
    long WS_SEQNO = 0;
    int WS_SEQ_LEN = 0;
    int WS_SEG1_LEN = 0;
    int WS_START = 0;
    int WS_PTR = 0;
    char WS_FIRST_FLAG = ' ';
    char WS_NEED_MORE_SEQ_FLAG = ' ';
    char WS_END_FLAG = ' ';
    char WS_CHK_END_FLAG = ' ';
    String WS_INFO = " ";
    String WS_CHK_CARDNO = " ";
    long WS_CARD_SEG1 = 0;
    int WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPQPRD DCCPQPRD = new DCCPQPRD();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRPDBIN DCRPDBIN = new DCRPDBIN();
    DCRDCSEQ DCRDCSEQ = new DCRDCSEQ();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSGSEQ DCCSGSEQ;
    public void MP(SCCGWA SCCGWA, DCCSGSEQ DCCSGSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSGSEQ = DCCSGSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSGSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_UPD_CARD_SEQNO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSGSEQ.INP_DATA.APP_NUM < 0) {
            WS_ERR_MSG = "21";
            IBS.CPY2CLS(SCCGWA, "21", DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_PROD_CD);
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSGSEQ.INP_DATA.CARD_PROD_CD;
        T000_READ_DCTPDBIN_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
        }
        CEP.TRC(SCCGWA, WS_CARD_SEG1);
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSGSEQ.INP_DATA.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = DCCSGSEQ.INP_DATA.CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = WS_CARD_SEG1;
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        T000_GROUP_DCTPDBIN();
        if (pgmRtn) return;
        if (WS_CNT <= 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_CARD_PROD_PLAN;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_CARD_PROD_PLAN, DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_UPD_CARD_SEQNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_SEG1);
        IBS.init(SCCGWA, DCRDCSEQ);
        DCRDCSEQ.KEY.CARD_PROD_CD = DCCSGSEQ.INP_DATA.CARD_PROD_CD;
        DCRDCSEQ.KEY.CARD_BIN = DCCSGSEQ.INP_DATA.CARD_BIN;
        DCRDCSEQ.KEY.CARD_SEG1 = DCCSGSEQ.INP_DATA.CARD_SEG1;
        T000_READUPD_DCTDCSEQ();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT SEQ RIGHT NOW");
            CEP.TRC(SCCGWA, WS_S_SEQNO);
            WS_CUR_SEQ = WS_S_SEQNO;
            R010_GET_CARD_SEQGRP();
            if (pgmRtn) return;
            DCRDCSEQ.CUR_SEQ = WS_CUR_SEQ;
            DCRDCSEQ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCSEQ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRDCSEQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCSEQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_ADD_DCTDCSEQ();
            if (pgmRtn) return;
        } else {
            WS_CUR_SEQ = DCRDCSEQ.CUR_SEQ;
            CEP.TRC(SCCGWA, "SEQ ALREADY EXISTS");
            CEP.TRC(SCCGWA, WS_CUR_SEQ);
            R010_GET_CARD_SEQGRP();
            if (pgmRtn) return;
            R020_AUTO_CHECK_CUR_SEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CUR_SEQ);
            CEP.TRC(SCCGWA, DCRDCSEQ.CUR_SEQ);
            DCRDCSEQ.CUR_SEQ = WS_CUR_SEQ;
            DCRDCSEQ.CUR_SEQ = WS_CUR_SEQ;
            DCRDCSEQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCSEQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTDCSEQ();
            if (pgmRtn) return;
        }
    }
    public void R010_GET_CARD_SEQGRP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_SEG1);
        WS_I = 1;
        WS_FIRST_FLAG = 'Y';
        WS_NEED_MORE_SEQ_FLAG = 'N';
        WS_END_FLAG = 'N';
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSGSEQ.INP_DATA.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = DCCSGSEQ.INP_DATA.CARD_BIN;
        DCRPDBIN.E_SEQ = WS_CUR_SEQ;
        DCRPDBIN.KEY.CARD_SEG1 = WS_CARD_SEG1;
        CEP.TRC(SCCGWA, "CFX001");
        CEP.TRC(SCCGWA, DCRPDBIN.E_SEQ);
        T000_STARTBR_DCTPDBIN();
        if (pgmRtn) return;
        T000_READNEXT_DCTPDBIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CFX003");
        CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.E_SEQ);
        CEP.TRC(SCCGWA, "KIA IS HERE");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_PROD_BIN;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_PROD_BIN, DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_END_FLAG != 'Y') {
            CEP.TRC(SCCGWA, "CAN KIA BE HERE");
            if (WS_FIRST_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "FIRST");
                CEP.TRC(SCCGWA, WS_CUR_SEQ);
                if (WS_CUR_SEQ == 0) {
                    CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
                    WS_CUR_SEQ = DCRPDBIN.S_SEQ;
                }
                WS_LEFT_APP_NUM = DCCSGSEQ.INP_DATA.APP_NUM;
                CEP.TRC(SCCGWA, WS_CUR_SEQ);
                CEP.TRC(SCCGWA, WS_LEFT_APP_NUM);
            }
            CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
            if (WS_CUR_SEQ < DCRPDBIN.S_SEQ) {
                T000_READNEXT_DCTPDBIN();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAASDFASDF");
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                CEP.TRC(SCCGWA, WS_CUR_SEQ);
                CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_END_FLAG = 'Y';
                }
                CEP.TRC(SCCGWA, DCCSGSEQ.OUT_DATA[1-1].CUR_SEQ);
                if (DCCSGSEQ.OUT_DATA[1-1].CUR_SEQ == 0) {
                    DCCSGSEQ.OUT_DATA[1-1].CUR_SEQ = WS_CUR_SEQ;
                }
            } else {
                CEP.TRC(SCCGWA, ">>>>>>>>");
                CEP.TRC(SCCGWA, "CFX002  WS-E-SEQNO");
                CEP.TRC(SCCGWA, WS_E_SEQNO);
                CEP.TRC(SCCGWA, "CFX002  WS-LEFT-APP-NUM");
                CEP.TRC(SCCGWA, WS_LEFT_APP_NUM);
                WS_E_SEQNO = WS_CUR_SEQ + WS_LEFT_APP_NUM - 1;
                CEP.TRC(SCCGWA, WS_E_SEQNO);
                CEP.TRC(SCCGWA, DCRPDBIN.E_SEQ);
                if (WS_E_SEQNO > DCRPDBIN.E_SEQ) {
                    CEP.TRC(SCCGWA, WS_CUR_SEQ);
                    CEP.TRC(SCCGWA, "AAAA");
                    CEP.TRC(SCCGWA, WS_I);
                    CEP.TRC(SCCGWA, DCCSGSEQ.OUT_DATA[WS_I-1].CUR_SEQ);
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NUM_NOT_ENOUGH);
                } else {
                    CEP.TRC(SCCGWA, "KIA OVER");
                    DCCSGSEQ.OUT_DATA[WS_I-1].CUR_SEQ = WS_CUR_SEQ;
                    DCCSGSEQ.OUT_DATA[WS_I-1].SEQ_NUM = (short) WS_LEFT_APP_NUM;
                    WS_CUR_SEQ = WS_CUR_SEQ + WS_LEFT_APP_NUM;
                    CEP.TRC(SCCGWA, WS_I);
                    CEP.TRC(SCCGWA, DCCSGSEQ.OUT_DATA[WS_I-1].CUR_SEQ);
                    CEP.TRC(SCCGWA, DCCSGSEQ.OUT_DATA[WS_I-1].SEQ_NUM);
                    WS_END_FLAG = 'Y';
                }
                if (WS_END_FLAG == 'N') {
                    T000_READNEXT_DCTPDBIN();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
                    CEP.TRC(SCCGWA, WS_CUR_SEQ);
                    if (DCRPDBIN.S_SEQ > WS_CUR_SEQ) {
                        WS_CUR_SEQ = DCRPDBIN.S_SEQ;
                    }
                }
            }
            WS_FIRST_FLAG = 'N';
        }
        T000_ENDBR_DCTPDBIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CUR_SEQ);
        if (WS_END_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ENOUGH_SEQNO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_ENOUGH_SEQNO, DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            WS_INFO = " ";
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = "T" + WS_INFO.substring(1);
            JIBS_tmp_str[0] = "" + WS_LEFT_APP_NUM;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 2 - 1) + JIBS_tmp_str[0].substring(0, 4) + WS_INFO.substring(2 + 4 - 1);
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 5 - 1) + "SEQ NO NEEDED" + WS_INFO.substring(5 + 2 - 1);
            IBS.CPY2CLS(SCCGWA, WS_INFO, DCCSGSEQ.RC);
            S000_ERR_MSG_PROC1();
            if (pgmRtn) return;
        }
    }
    public void R020_AUTO_CHECK_CUR_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CUR_SEQ);
        for (WS_CUR_SEQ = WS_CUR_SEQ; WS_CHK_END_FLAG != 'Y'; WS_CUR_SEQ += 1) {
            CEP.TRC(SCCGWA, WS_CUR_SEQ);
            R021_AUTO_TRY_GEN_CARDNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CHK_CARDNO);
            CEP.TRC(SCCGWA, DCRPDBIN.E_SEQ);
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = WS_CHK_CARDNO;
            DCRCDORD.KEY.EXC_CARD_TMS = 0;
            T000_READ_DCTCDORD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (WS_CUR_SEQ > DCRPDBIN.E_SEQ) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_CARD_PROD_PLAN;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_CARD_PROD_PLAN, DCCSGSEQ.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                WS_CHK_END_FLAG = 'Y';
                WS_CUR_SEQ = WS_CUR_SEQ - 1;
            }
        }
    }
    public void R021_AUTO_TRY_GEN_CARDNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'G';
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        if (DCCSGSEQ.INP_DATA.CARD_BIN == null) DCCSGSEQ.INP_DATA.CARD_BIN = "";
        JIBS_tmp_int = DCCSGSEQ.INP_DATA.CARD_BIN.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) DCCSGSEQ.INP_DATA.CARD_BIN += " ";
        DCCFCDGG.VAL.CARD_NO = DCCSGSEQ.INP_DATA.CARD_BIN + DCCFCDGG.VAL.CARD_NO.substring(6);
        IBS.init(SCCGWA, DCRBINPM);
        CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_BIN);
        DCRBINPM.KEY.BIN = DCCSGSEQ.INP_DATA.CARD_BIN;
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        if (DCRBINPM.SEG_NUM == 1) {
            CEP.TRC(SCCGWA, "KIA = SEG1");
            WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - 1;
            WS_START = 15 - WS_SEQ_LEN + 1;
            WS_SEQNO = WS_CUR_SEQ;
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(7 + WS_SEQ_LEN - 1);
        } else {
            CEP.TRC(SCCGWA, "KIA = SEG2");
            WS_SEG1_LEN = DCRBINPM.SEG1_LEN;
            WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - DCRBINPM.SEG1_LEN - 1;
            WS_START = 15 - DCRBINPM.SEG1_LEN + 1;
            CEP.TRC(SCCGWA, DCCSGSEQ.INP_DATA.CARD_SEG1);
            CEP.TRC(SCCGWA, WS_START);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            JIBS_tmp_str[0] = "" + DCCSGSEQ.INP_DATA.CARD_SEG1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + DCRBINPM.SEG1_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(7 + DCRBINPM.SEG1_LEN - 1);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
            WS_START = 15 - WS_SEQ_LEN + 1;
            WS_SEQNO = WS_CUR_SEQ;
            WS_PTR = 6 + DCRBINPM.SEG1_LEN + 1;
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, WS_PTR - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(WS_PTR + WS_SEQ_LEN - 1);
        }
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
        WS_CHK_CARDNO = DCCFCDGG.VAL.CARD_NO_GEN;
        CEP.TRC(SCCGWA, WS_CHK_CARDNO);
    }
    public void T000_READUPD_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDCSEQ_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
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
    public void T000_ADD_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        IBS.WRITE(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
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
    public void T000_STARTBR_DCTPDBIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.E_SEQ);
        DCTPDBIN_BR.rp = new DBParm();
        DCTPDBIN_BR.rp.TableName = "DCTPDBIN";
        DCTPDBIN_BR.rp.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND E_SEQ >= :DCRPDBIN.E_SEQ "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        DCTPDBIN_BR.rp.order = "S_SEQ";
        IBS.STARTBR(SCCGWA, DCRPDBIN, this, DCTPDBIN_BR);
        CEP.TRC(SCCGWA, "BR-BY-S-SEQ");
    }
    public void T000_READNEXT_DCTPDBIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRPDBIN, this, DCTPDBIN_BR);
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAASDFASDF");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_CUR_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
    }
    public void T000_ENDBR_DCTPDBIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTPDBIN_BR);
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CEID_EXP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BINF_NOTFND;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_BINF_NOTFND, DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO, ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.col = "CARD_NO, EXC_CARD_TMS, APP_BAT_NO, CARD_PROD, CARD_CLS_CD, BV_CD_NO, EMBOSS_NAME, EMBS_TYP, CRT_STS, CRT_DT, TRAN_PIN_DAT, QURY_PIN_DAT, SF_FLG, APP_TYP, CERT_EXP_DATE, APP_TELLER, APP_DT, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZPQPRD, DCCPQPRD);
        if (DCCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZFCDGG, DCCFCDGG);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTPDBIN_FIRST() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC1() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_INFO);
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
