package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCDTP {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_9 = "DC111";
    int MAX_COL = 99;
    int MAX_ROW = 50;
    int COL_CNT = 8;
    String HIS_REMARK = "CARD ATTRIBUTION MAINTAIN";
    String HIS_COPYBOOK = "DCRCDDAT";
    String TBL_CDDAT = "DCTCDDAT";
    DCZSCDTP_WS_VARIABLES WS_VARIABLES = new DCZSCDTP_WS_VARIABLES();
    DCZSCDTP_WS_SCDTP_OUTPUT WS_SCDTP_OUTPUT = new DCZSCDTP_WS_SCDTP_OUTPUT();
    DCZSCDTP_WS_CONDITION_FLAG WS_CONDITION_FLAG = new DCZSCDTP_WS_CONDITION_FLAG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSCDTP DCCSCDTP;
    public void MP(SCCGWA SCCGWA, DCCSCDTP DCCSCDTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCDTP = DCCSCDTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCDTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_SCDTP_OUTPUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCDTP);
        CEP.TRC(SCCGWA, DCCSCDTP.FUNC);
        if (DCCSCDTP.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSCDTP.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, ERROR_MSG.FUNC_FALSE, 1);
        }
        B080_HISTORY_PROCESS();
        if (pgmRtn) return;
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCDTP.CARD_NO);
        CEP.TRC(SCCGWA, DCCSCDTP.CARD_ATTRIBUTE);
        if (DCCSCDTP.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == ' ') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ATTRIBUTE);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCDTP.CARD_NO;
        T000_READ_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_CONDITION_FLAG.TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_EXIST);
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        if (DCRCDDAT.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(0, 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ALREADY_LOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(8 - 1, 8 + 1 - 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_CVN_ERR_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_PIN_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1));
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DCCUCDLP.CARD_NO = DCCSCDTP.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCDTP.CARD_ATTRIBUTE);
        if (DCCSCDTP.CARD_ATTRIBUTE == '1') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (!DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = "1" + DCRCDDAT.CARD_TYP.substring(1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '2') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (!DCRCDDAT.CARD_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 2 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(2 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '3') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (!DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 3 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(3 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '4') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (!DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 4 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(4 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '6') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (!DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 6 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(6 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '7') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (!DCRCDDAT.CARD_TYP.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 7 - 1) + "1" + DCRCDDAT.CARD_TYP.substring(7 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B020_DELETE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCDTP.CARD_ATTRIBUTE);
        if (DCCSCDTP.CARD_ATTRIBUTE == '1') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = "0" + DCRCDDAT.CARD_TYP.substring(1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '2') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 2 - 1) + "0" + DCRCDDAT.CARD_TYP.substring(2 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '3') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 3 - 1) + "0" + DCRCDDAT.CARD_TYP.substring(3 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '4') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 4 - 1) + "0" + DCRCDDAT.CARD_TYP.substring(4 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '6') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 6 - 1) + "0" + DCRCDDAT.CARD_TYP.substring(6 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSCDTP.CARD_ATTRIBUTE == '7') {
            if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
            JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
            if (DCRCDDAT.CARD_TYP.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                DCRCDDAT.CARD_TYP = DCRCDDAT.CARD_TYP.substring(0, 7 - 1) + "0" + DCRCDDAT.CARD_TYP.substring(7 + 1 - 1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ATTR_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B050_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCSCDTP.CARD_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSCDTP.CARD_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "PB11";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 489;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCDDAT;
        BPCPNHIS.INFO.OLD_DAT_PT = DCRCDDAT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_SCDTP_OUTPUT.CARD_NO = DCCSCDTP.CARD_NO;
        WS_SCDTP_OUTPUT.ATTRIBUTE = DCRCDDAT.CARD_TYP;
        SCCFMT.FMTID = OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_SCDTP_OUTPUT;
        SCCFMT.DATA_LEN = 39;
        CEP.TRC(SCCGWA, WS_SCDTP_OUTPUT);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_CONDITION_FLAG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
