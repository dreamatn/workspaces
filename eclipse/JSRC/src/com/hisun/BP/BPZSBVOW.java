package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVOW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_BMOV = "BP-R-BRW-BMOV ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSBVOW_WS_BVOW_HEAD WS_BVOW_HEAD = new BPZSBVOW_WS_BVOW_HEAD();
    BPZSBVOW_WS_BVOW_DETAIL WS_BVOW_DETAIL = new BPZSBVOW_WS_BVOW_DETAIL();
    BPZSBVOW_WS_TEMP_TOTAL WS_TEMP_TOTAL = new BPZSBVOW_WS_TEMP_TOTAL();
    int WS_TEST_BR = 0;
    char WS_TBL_BVOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPCRBMOB BPCRBMOB = new BPCRBMOB();
    SCCGWA SCCGWA;
    BPCSBVOW BPCSBVOW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBVOW BPCSBVOW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVOW = BPCSBVOW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVOW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRBMOB);
        IBS.init(SCCGWA, BPRBMOV);
        IBS.init(SCCGWA, BPCSBVOW.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVOW);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSBVOW.FUNC == 'B') {
                B010_BROWSE_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSBVOW.FUNC == 'B') {
                B010_BROWSE_PROCESS();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVOW.BR);
        CEP.TRC(SCCGWA, BPCSBVOW.TLR);
        CEP.TRC(SCCGWA, BPCSBVOW.CODE);
        CEP.TRC(SCCGWA, BPCSBVOW.MOV_STS);
        BPRBMOV.IN_BR = BPCSBVOW.BR;
        BPRBMOV.IN_TLR = BPCSBVOW.TLR;
        BPRBMOV.CODE1 = BPCSBVOW.CODE;
        BPRBMOV.MOV_STS = BPCSBVOW.MOV_STS;
        BPCRBMOB.INFO.FUNC = 'S';
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSBVOW.BR);
        CEP.TRC(SCCGWA, BPCSBVOW.TLR);
        BPCRBMOB.INFO.FUNC = 'N';
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBMOV.OUT_BR);
        CEP.TRC(SCCGWA, BPRBMOV.OUT_TLR);
        CEP.TRC(SCCGWA, BPRBMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPRBMOV.IN_BR);
        CEP.TRC(SCCGWA, BPRBMOV.IN_TLR);
        CEP.TRC(SCCGWA, BPRBMOV.KEY.MOV_DT);
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRBMOB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000; WS_CNT += 1) {
            B010_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCRBMOB.INFO.FUNC = 'N';
            S000_CALL_BPZRBMOB();
            if (pgmRtn) return;
        }
        BPCRBMOB.INFO.FUNC = 'E';
        BPCRBMOB.INFO.POINTER = BPCRBMOB;
        BPCRBMOB.INFO.LEN = 33;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_PROCESS_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVOW.BR);
        CEP.TRC(SCCGWA, BPCSBVOW.TLR);
        CEP.TRC(SCCGWA, BPCSBVOW.CODE);
        CEP.TRC(SCCGWA, BPCSBVOW.MOV_STS);
        CEP.TRC(SCCGWA, BPCSBVOW.INQ_TYP);
        CEP.TRC(SCCGWA, BPCSBVOW.TYPE);
        CEP.TRC(SCCGWA, BPCSBVOW.START_DT);
        CEP.TRC(SCCGWA, BPCSBVOW.END_DT);
        CEP.TRC(SCCGWA, BPCSBVOW.CONF_NO);
        CEP.TRC(SCCGWA, BPCSBVOW.OTH_BR);
        IBS.init(SCCGWA, WS_BVOW_DETAIL);
        if (BPCSBVOW.TYPE == '0') {
            BPRBMOV.IN_BR = BPCSBVOW.BR;
            BPRBMOV.OUT_BR = BPCSBVOW.OTH_BR;
            BPCRBMOB.INFO.FUNC = '8';
            BPRBMOV.IN_TLR = BPCSBVOW.TLR;
        } else if (BPCSBVOW.TYPE == '1') {
            BPRBMOV.OUT_BR = BPCSBVOW.BR;
            BPRBMOV.IN_BR = BPCSBVOW.OTH_BR;
            BPRBMOV.OUT_TLR = BPCSBVOW.TLR;
            BPCRBMOB.INFO.FUNC = '9';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRBMOV.CODE1 = BPCSBVOW.CODE;
        BPRBMOV.MOV_STS = BPCSBVOW.MOV_STS;
        BPRBMOV.KEY.CONF_NO = BPCSBVOW.CONF_NO;
        if (BPCSBVOW.START_DT == ' ') {
            BPCRBMOB.INFO.START_DT = 0;
        } else {
            BPCRBMOB.INFO.START_DT = BPCSBVOW.START_DT;
        }
        if (BPCSBVOW.END_DT == ' ') {
            BPCRBMOB.INFO.END_DT = 0;
        } else {
            BPCRBMOB.INFO.END_DT = BPCSBVOW.END_DT;
        }
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
        BPCRBMOB.INFO.FUNC = 'N';
        BPCRBMOB.INFO.POINTER = BPRBMOV;
        BPCRBMOB.INFO.LEN = 900;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        if (BPCSBVOW.INQ_TYP == '0') {
            for (WS_CNT = 1; BPCRBMOB.RETURN_INFO != 'N' 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                B010_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
                BPCRBMOB.INFO.FUNC = 'N';
                S000_CALL_BPZRBMOB();
                if (pgmRtn) return;
            }
        } else {
            WS_TEMP_TOTAL.WS_TEMP_OUT_BR = BPRBMOV.OUT_BR;
            WS_TEMP_TOTAL.WS_TEMP_IN_BR = BPRBMOV.IN_BR;
            WS_TEMP_TOTAL.WS_TEMP_CODE = BPRBMOV.CODE1;
            WS_TEMP_TOTAL.WS_TEMP_BV_STS = BPRBMOV.BV_STS;
            WS_TEMP_TOTAL.WS_TEMP_HEAD_NO = BPRBMOV.HEAD_NO1;
            WS_TEMP_TOTAL.WS_TEMP_MOV_STS = BPRBMOV.MOV_STS;
            WS_CNT = 0;
            WS_TEMP_TOTAL.WS_NUM = 0;
            for (WS_CNT = 1; BPCRBMOB.RETURN_INFO != 'N' 
                && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
                if ((BPRBMOV.OUT_BR != WS_TEMP_TOTAL.WS_TEMP_OUT_BR) 
                    || (BPRBMOV.IN_BR != WS_TEMP_TOTAL.WS_TEMP_IN_BR) 
                    || (!BPRBMOV.CODE1.equalsIgnoreCase(WS_TEMP_TOTAL.WS_TEMP_CODE)) 
                    || (BPRBMOV.BV_STS != WS_TEMP_TOTAL.WS_TEMP_BV_STS) 
                    || (!BPRBMOV.HEAD_NO1.equalsIgnoreCase(WS_TEMP_TOTAL.WS_TEMP_HEAD_NO)) 
                    || (BPRBMOV.MOV_STS != WS_TEMP_TOTAL.WS_TEMP_MOV_STS)) {
                    CEP.TRC(SCCGWA, "AAA");
                    B020_1_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_TEMP_TOTAL.WS_TEMP_OUT_BR = BPRBMOV.OUT_BR;
                    WS_TEMP_TOTAL.WS_TEMP_IN_BR = BPRBMOV.IN_BR;
                    WS_TEMP_TOTAL.WS_TEMP_CODE = BPRBMOV.CODE1;
                    WS_TEMP_TOTAL.WS_TEMP_BV_STS = BPRBMOV.BV_STS;
                    WS_TEMP_TOTAL.WS_TEMP_HEAD_NO = BPRBMOV.HEAD_NO1;
                    WS_TEMP_TOTAL.WS_TEMP_MOV_STS = BPRBMOV.MOV_STS;
                    WS_TEMP_TOTAL.WS_NUM = BPRBMOV.NUM1;
                } else {
                    CEP.TRC(SCCGWA, WS_TEMP_TOTAL.WS_NUM);
                    WS_TEMP_TOTAL.WS_NUM = WS_TEMP_TOTAL.WS_NUM + BPRBMOV.NUM1;
                }
                BPCRBMOB.INFO.FUNC = 'N';
                S000_CALL_BPZRBMOB();
                if (pgmRtn) return;
            }
            if (WS_CNT != 1) {
                CEP.TRC(SCCGWA, "BBB");
                B020_1_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        BPCRBMOB.INFO.FUNC = 'E';
        BPCRBMOB.INFO.POINTER = BPCRBMOB;
        BPCRBMOB.INFO.LEN = 33;
        S000_CALL_BPZRBMOB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 709;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 13;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BVOW_DETAIL);
        WS_BVOW_DETAIL.WS_BVOW_OUT_BR = BPRBMOV.OUT_BR;
        CEP.TRC(SCCGWA, BPRBMOV.OUT_BR);
        WS_BVOW_DETAIL.WS_BVOW_OUT_TLR = BPRBMOV.OUT_TLR;
        WS_BVOW_DETAIL.WS_BVOW_MOV_STS = BPRBMOV.MOV_STS;
        WS_BVOW_DETAIL.WS_BVOW_IN_BR = BPRBMOV.IN_BR;
        WS_BVOW_DETAIL.WS_BVOW_IN_TLR = BPRBMOV.IN_TLR;
        WS_BVOW_DETAIL.WS_BVOW_MOV_DT = BPRBMOV.KEY.MOV_DT;
        CEP.TRC(SCCGWA, BPRBMOV.KEY.MOV_DT);
        WS_BVOW_DETAIL.WS_BVOW_CONF_NO = BPRBMOV.KEY.CONF_NO;
        if (BPRBMOV.CODE1.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE1 = BPRBMOV.CODE1;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS1 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO1 = BPRBMOV.HEAD_NO1;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO1 = BPRBMOV.BEG_NO1;
            WS_BVOW_DETAIL.WS_BVOW_END_NO1 = BPRBMOV.END_NO1;
            WS_BVOW_DETAIL.WS_BVOW_NUM1 = BPRBMOV.NUM1;
        }
        if (BPRBMOV.CODE2.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE2 = BPRBMOV.CODE2;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS2 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO2 = BPRBMOV.HEAD_NO2;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO2 = BPRBMOV.BEG_NO2;
            WS_BVOW_DETAIL.WS_BVOW_END_NO2 = BPRBMOV.END_NO2;
            WS_BVOW_DETAIL.WS_BVOW_NUM2 = BPRBMOV.NUM2;
        }
        if (BPRBMOV.CODE3.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE3 = BPRBMOV.CODE3;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS3 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO3 = BPRBMOV.HEAD_NO3;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO3 = BPRBMOV.BEG_NO3;
            WS_BVOW_DETAIL.WS_BVOW_END_NO3 = BPRBMOV.END_NO3;
            WS_BVOW_DETAIL.WS_BVOW_NUM3 = BPRBMOV.NUM3;
        }
        if (BPRBMOV.CODE4.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE4 = BPRBMOV.CODE4;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS4 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO4 = BPRBMOV.HEAD_NO4;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO4 = BPRBMOV.BEG_NO4;
            WS_BVOW_DETAIL.WS_BVOW_END_NO4 = BPRBMOV.END_NO4;
            WS_BVOW_DETAIL.WS_BVOW_NUM4 = BPRBMOV.NUM4;
        }
        if (BPRBMOV.CODE5.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE5 = BPRBMOV.CODE5;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS5 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO5 = BPRBMOV.HEAD_NO5;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO5 = BPRBMOV.BEG_NO5;
            WS_BVOW_DETAIL.WS_BVOW_END_NO5 = BPRBMOV.END_NO5;
            WS_BVOW_DETAIL.WS_BVOW_NUM5 = BPRBMOV.NUM5;
        }
        if (BPRBMOV.CODE6.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE6 = BPRBMOV.CODE6;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS6 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO6 = BPRBMOV.HEAD_NO6;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO6 = BPRBMOV.BEG_NO6;
            WS_BVOW_DETAIL.WS_BVOW_END_NO6 = BPRBMOV.END_NO6;
            WS_BVOW_DETAIL.WS_BVOW_NUM6 = BPRBMOV.NUM6;
        }
        if (BPRBMOV.CODE7.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE7 = BPRBMOV.CODE7;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS7 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO7 = BPRBMOV.HEAD_NO7;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO7 = BPRBMOV.BEG_NO7;
            WS_BVOW_DETAIL.WS_BVOW_END_NO7 = BPRBMOV.END_NO7;
            WS_BVOW_DETAIL.WS_BVOW_NUM7 = BPRBMOV.NUM7;
        }
        if (BPRBMOV.CODE8.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE8 = BPRBMOV.CODE8;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS8 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO8 = BPRBMOV.HEAD_NO8;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO8 = BPRBMOV.BEG_NO8;
            WS_BVOW_DETAIL.WS_BVOW_END_NO8 = BPRBMOV.END_NO8;
            WS_BVOW_DETAIL.WS_BVOW_NUM8 = BPRBMOV.NUM8;
        }
        if (BPRBMOV.CODE9.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE9 = BPRBMOV.CODE9;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS9 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO9 = BPRBMOV.HEAD_NO9;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO9 = BPRBMOV.BEG_NO9;
            WS_BVOW_DETAIL.WS_BVOW_END_NO9 = BPRBMOV.END_NO9;
            WS_BVOW_DETAIL.WS_BVOW_NUM9 = BPRBMOV.NUM9;
        }
        if (BPRBMOV.CODE10.trim().length() > 0) {
            WS_BVOW_DETAIL.WS_BVOW_CODE10 = BPRBMOV.CODE10;
            WS_BVOW_DETAIL.WS_BVOW_BV_STS10 = BPRBMOV.BV_STS;
            WS_BVOW_DETAIL.WS_BVOW_HEAD_NO10 = BPRBMOV.HEAD_NO10;
            WS_BVOW_DETAIL.WS_BVOW_BEG_NO10 = BPRBMOV.BEG_NO10;
            WS_BVOW_DETAIL.WS_BVOW_END_NO10 = BPRBMOV.END_NO10;
            WS_BVOW_DETAIL.WS_BVOW_NUM10 = BPRBMOV.NUM10;
        }
        CEP.TRC(SCCGWA, WS_BVOW_DETAIL.WS_BVOW_HEAD_NO1);
        CEP.TRC(SCCGWA, WS_BVOW_DETAIL.WS_BVOW_BEG_NO1);
        CEP.TRC(SCCGWA, WS_BVOW_DETAIL.WS_BVOW_END_NO1);
        CEP.TRC(SCCGWA, WS_BVOW_DETAIL.WS_BVOW_NUM1);
        CEP.TRC(SCCGWA, WS_BVOW_DETAIL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BVOW_DETAIL);
        SCCMPAG.DATA_LEN = 709;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_1_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_BVOW_DETAIL.WS_BVOW_OUT_BR = WS_TEMP_TOTAL.WS_TEMP_OUT_BR;
        WS_BVOW_DETAIL.WS_BVOW_IN_BR = WS_TEMP_TOTAL.WS_TEMP_IN_BR;
        WS_BVOW_DETAIL.WS_BVOW_CODE1 = WS_TEMP_TOTAL.WS_TEMP_CODE;
        WS_BVOW_DETAIL.WS_BVOW_BV_STS1 = WS_TEMP_TOTAL.WS_TEMP_BV_STS;
        WS_BVOW_DETAIL.WS_BVOW_HEAD_NO1 = WS_TEMP_TOTAL.WS_TEMP_HEAD_NO;
        WS_BVOW_DETAIL.WS_BVOW_NUM1 = WS_TEMP_TOTAL.WS_NUM;
        WS_BVOW_DETAIL.WS_BVOW_MOV_STS = WS_TEMP_TOTAL.WS_TEMP_MOV_STS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BVOW_DETAIL);
        SCCMPAG.DATA_LEN = 709;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRBMOB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BMOV, BPCRBMOB);
        CEP.TRC(SCCGWA, BPCRBMOB.RC);
        CEP.TRC(SCCGWA, BPCRBMOB.RETURN_INFO);
        if (BPCRBMOB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBMOB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
