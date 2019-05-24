package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIXBSD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    short WS_CNT = 0;
    BPZIXBSD_WS_PROD_SUS_KEY WS_PROD_SUS_KEY = new BPZIXBSD_WS_PROD_SUS_KEY();
    int WS_TMP_DT = 0;
    char WS_LEAP_YEAR_FLG = ' ';
    char WS_FM_YEAR_LY = ' ';
    char WS_TO_YEAR_LY = ' ';
    short WS_YEAR = 0;
    int WS_SEQ_DATE = 0;
    short WS_DDD = 0;
    short WS_I = 0;
    short WS_R = 0;
    short WS_G = 0;
    int WS_TO_DATE = 0;
    short WS_TO_YEAR = 0;
    short WS_TO_MTH = 0;
    short WS_TO_DAY = 0;
    short WS_TOT_TO_DAYS = 0;
    int WS_FROM_DATE = 0;
    short WS_FROM_YEAR = 0;
    short WS_FROM_MTH = 0;
    short WS_FROM_DAY = 0;
    short WS_TOT_FROM_DAYS = 0;
    short WS_TOT_DIFF_DAYS = 0;
    short WS_DIFF_YEARS = 0;
    short WS_FM_DIFF_DAYS = 0;
    short WS_TO_DIFF_DAYS = 0;
    int WS_TOT_ACCU_DAYS = 0;
    int WS_FROM_STD_DATE = 0;
    int WS_TO_STD_DATE = 0;
    int WS_YEAR_ACT_DAYS = 0;
    short WS_YEAR_COUNT = 0;
    BPZIXBSD_WS1 BPZIXBSD_WS1 = new BPZIXBSD_WS1();
    BPZIXBSD_WS2 BPZIXBSD_WS2 = new BPZIXBSD_WS2();
    BPZIXBSD_WS3 BPZIXBSD_WS3 = new BPZIXBSD_WS3();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCIXBSD BPCIXBSD;
    public void MP(SCCGWA SCCGWA, BPCIXBSD BPCIXBSD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIXBSD = BPCIXBSD;
        CEP.TRC(SCCGWA);
        IBS.CPY2CLS(SCCGWA, "303030303030303030303030", BPZIXBSD_WS1);
        IBS.CPY2CLS(SCCGWA, "312931303130313130313031", BPZIXBSD_WS1);
        IBS.CPY2CLS(SCCGWA, "312831303130313130313031", BPZIXBSD_WS1);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZIXBSD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCIXBSD.RC.RC_MMO = "BP";
        BPCIXBSD.RC.RC_CODE = 0;
        if (BPCIXBSD.DATA.START_DT > BPCIXBSD.DATA.MATRUE_DT) {
            if ("2106".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
            else BPCIXBSD.RC.RC_CODE = Short.parseShort("2106");
            Z_RET();
            if (pgmRtn) return;
        }
        A010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCIXBSD.FUNC == 'B') {
            B011_QUERY_30_ACCRU_DAYS();
            if (pgmRtn) return;
        } else if (BPCIXBSD.FUNC == 'A') {
            B031_QUERY_30E_ACCRU_DAYS();
            if (pgmRtn) return;
        } else if (BPCIXBSD.FUNC == 'C'
            || BPCIXBSD.FUNC == 'D'
            || BPCIXBSD.FUNC == 'E') {
            B050_QUERY_A360_ACCRU_DAYS();
            if (pgmRtn) return;
        } else {
            if ("1210".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
            else BPCIXBSD.RC.RC_CODE = Short.parseShort("1210");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_30_ACCRU_DAYS() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_YEAR = 0;
        WS_TO_YEAR = 0;
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        WS_DIFF_YEARS = (short) (WS_FROM_YEAR - WS_TO_YEAR);
        if (WS_TO_YEAR - WS_FROM_YEAR >= 1) {
            B010_01_30_SPAN_YEAR_PROC();
            if (pgmRtn) return;
        } else {
            WS_FROM_DATE = BPCIXBSD.DATA.START_DT;
            WS_TO_DATE = BPCIXBSD.DATA.MATRUE_DT;
            S000_GET_30_DAYS_SEQ();
            if (pgmRtn) return;
            BPCIXBSD.DATA.DAYS = WS_TOT_DIFF_DAYS;
        }
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void B011_QUERY_30_ACCRU_DAYS() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_YEAR = 0;
        WS_FROM_MTH = 0;
        WS_FROM_DAY = 0;
        WS_TO_YEAR = 0;
        WS_TO_MTH = 0;
        WS_TO_DAY = 0;
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_FROM_MTH = 0;
        else WS_FROM_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FROM_DAY = 0;
        else WS_FROM_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TO_MTH = 0;
        else WS_TO_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TO_DAY = 0;
        else WS_TO_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        WS_DIFF_YEARS = (short) (WS_FROM_YEAR - WS_TO_YEAR);
        if (WS_TO_DAY == 31 
            && (WS_FROM_DAY == 31 
            || WS_FROM_DAY == 30)) {
            WS_TO_DAY = 30;
        }
        if (WS_FROM_DAY == 31) {
            WS_FROM_DAY = 30;
        }
        BPCIXBSD.DATA.DAYS = (short) (WS_DIFF_YEARS * 360 + ( WS_TO_MTH - WS_FROM_MTH ) * 30 + ( WS_TO_DAY - WS_FROM_DAY ));
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void B010_01_30_SPAN_YEAR_PROC() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_DATE = BPCIXBSD.DATA.START_DT;
        S000_SPLIT_FROM_DATE();
        if (pgmRtn) return;
        WS_FROM_DATE = WS_FROM_DATE;
        WS_TO_DATE = WS_FROM_STD_DATE;
        S000_GET_30_DAYS_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_DIFF_DAYS);
        WS_FM_DIFF_DAYS = WS_TOT_DIFF_DAYS;
        WS_FM_DIFF_DAYS += 1;
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_TO_DATE = BPCIXBSD.DATA.MATRUE_DT;
        CEP.TRC(SCCGWA, WS_TO_DATE);
        CEP.TRC(SCCGWA, WS_TO_YEAR);
        S000_SPLIT_TO_DATE();
        if (pgmRtn) return;
        WS_FROM_DATE = WS_TO_STD_DATE;
        WS_TO_DATE = WS_TO_DATE;
        S000_GET_30_DAYS_SEQ();
        if (pgmRtn) return;
        WS_TO_DIFF_DAYS = WS_TOT_DIFF_DAYS;
        CEP.TRC(SCCGWA, WS_TO_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_DIFF_YEARS);
        WS_TOT_ACCU_DAYS = WS_FM_DIFF_DAYS + WS_TO_DIFF_DAYS + ( WS_DIFF_YEARS - 1 ) * 360;
        BPCIXBSD.DATA.DAYS = (short) WS_TOT_ACCU_DAYS;
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void B030_01_30E_SPAN_YEAR_PROC() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_DATE = BPCIXBSD.DATA.START_DT;
        S000_SPLIT_FROM_DATE();
        if (pgmRtn) return;
        WS_FROM_DATE = WS_FROM_DATE;
        WS_TO_DATE = WS_FROM_STD_DATE;
        S000_GET_30E_DAYS_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_DIFF_DAYS);
        WS_FM_DIFF_DAYS = WS_TOT_DIFF_DAYS;
        WS_FM_DIFF_DAYS += 1;
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_TO_DATE = BPCIXBSD.DATA.MATRUE_DT;
        CEP.TRC(SCCGWA, WS_TO_DATE);
        CEP.TRC(SCCGWA, WS_TO_YEAR);
        S000_SPLIT_TO_DATE();
        if (pgmRtn) return;
        WS_FROM_DATE = WS_TO_STD_DATE;
        WS_TO_DATE = WS_TO_DATE;
        S000_GET_30E_DAYS_SEQ();
        if (pgmRtn) return;
        WS_TO_DIFF_DAYS = WS_TOT_DIFF_DAYS;
        CEP.TRC(SCCGWA, WS_TO_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_DIFF_YEARS);
        WS_TOT_ACCU_DAYS = WS_FM_DIFF_DAYS + WS_TO_DIFF_DAYS + ( WS_DIFF_YEARS - 1 ) * 360;
        BPCIXBSD.DATA.DAYS = (short) WS_TOT_ACCU_DAYS;
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void B050_01_SPAN_YEAR_PROC() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_DATE = BPCIXBSD.DATA.START_DT;
        S000_SPLIT_FROM_DATE();
        if (pgmRtn) return;
        WS_FROM_DATE = WS_FROM_DATE;
        WS_TO_DATE = WS_FROM_STD_DATE;
        S000_GET_ACTUAL_DAYS_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_DIFF_DAYS);
        WS_FM_DIFF_DAYS = WS_TOT_DIFF_DAYS;
        WS_FM_DIFF_DAYS += 1;
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_TO_DATE = BPCIXBSD.DATA.MATRUE_DT;
        CEP.TRC(SCCGWA, WS_TO_DATE);
        CEP.TRC(SCCGWA, WS_TO_YEAR);
        S000_SPLIT_TO_DATE();
        if (pgmRtn) return;
        WS_FROM_DATE = WS_TO_STD_DATE;
        WS_TO_DATE = WS_TO_DATE;
        S000_GET_ACTUAL_DAYS_SEQ();
        if (pgmRtn) return;
        WS_TO_DIFF_DAYS = WS_TOT_DIFF_DAYS;
        CEP.TRC(SCCGWA, WS_TO_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_DIFF_YEARS);
        WS_YEAR_ACT_DAYS = 0;
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        CEP.TRC(SCCGWA, WS_FROM_YEAR);
        CEP.TRC(SCCGWA, WS_TO_YEAR);
        WS_YEAR_COUNT = (short) (WS_TO_YEAR - WS_FROM_YEAR - 1);
        CEP.TRC(SCCGWA, WS_YEAR_COUNT);
        for (WS_I = 1; WS_I <= WS_YEAR_COUNT; WS_I += 1) {
            WS_YEAR = (short) (WS_I + WS_FROM_YEAR);
            CEP.TRC(SCCGWA, WS_YEAR);
            S000_LEAP_YEAR_CHECK();
            if (pgmRtn) return;
            if (WS_LEAP_YEAR_FLG == 'Y') {
                WS_YEAR_ACT_DAYS += 366;
                CEP.TRC(SCCGWA, WS_YEAR_ACT_DAYS);
            } else {
                WS_YEAR_ACT_DAYS += 365;
                CEP.TRC(SCCGWA, WS_YEAR_ACT_DAYS);
            }
        }
        CEP.TRC(SCCGWA, WS_FM_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_TO_DIFF_DAYS);
        CEP.TRC(SCCGWA, WS_YEAR_ACT_DAYS);
        WS_TOT_ACCU_DAYS = WS_FM_DIFF_DAYS + WS_TO_DIFF_DAYS + WS_YEAR_ACT_DAYS;
        BPCIXBSD.DATA.DAYS = (short) WS_TOT_ACCU_DAYS;
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void S000_SPLIT_FROM_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FROM_DATE);
        WS_FROM_STD_DATE = 0;
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_FROM_STD_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_FROM_STD_DATE;
        JIBS_NumStr = JIBS_tmp_str[0].substring(0, 4) + JIBS_NumStr.substring(4);
        WS_FROM_STD_DATE = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_FROM_STD_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_FROM_STD_DATE;
        JIBS_tmp_str[1] = "" + 12;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 2 - 1);
        WS_FROM_STD_DATE = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_FROM_STD_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_FROM_STD_DATE;
        JIBS_tmp_str[1] = "" + 31;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
        WS_FROM_STD_DATE = Integer.parseInt(JIBS_NumStr);
        CEP.TRC(SCCGWA, WS_FROM_STD_DATE);
    }
    public void S000_SPLIT_TO_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TO_DATE);
        WS_TO_STD_DATE = 0;
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_TO_STD_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_TO_STD_DATE;
        JIBS_NumStr = JIBS_tmp_str[0].substring(0, 4) + JIBS_NumStr.substring(4);
        WS_TO_STD_DATE = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_TO_STD_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_TO_STD_DATE;
        JIBS_tmp_str[1] = "" + 1;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 2 - 1);
        WS_TO_STD_DATE = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_TO_STD_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_TO_STD_DATE;
        JIBS_tmp_str[1] = "" + 1;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
        WS_TO_STD_DATE = Integer.parseInt(JIBS_NumStr);
        CEP.TRC(SCCGWA, WS_FROM_STD_DATE);
    }
    public void B030_QUERY_30E_ACCRU_DAYS() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_YEAR = 0;
        WS_TO_YEAR = 0;
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        WS_DIFF_YEARS = (short) (WS_FROM_YEAR - WS_TO_YEAR);
        if (WS_TO_YEAR - WS_FROM_YEAR >= 1) {
            B030_01_30E_SPAN_YEAR_PROC();
            if (pgmRtn) return;
        } else {
            WS_FROM_DATE = BPCIXBSD.DATA.START_DT;
            WS_TO_DATE = BPCIXBSD.DATA.MATRUE_DT;
            S000_GET_30E_DAYS_SEQ();
            if (pgmRtn) return;
            BPCIXBSD.DATA.DAYS = WS_TOT_DIFF_DAYS;
        }
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void B031_QUERY_30E_ACCRU_DAYS() throws IOException,SQLException,Exception {
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_YEAR = 0;
        WS_FROM_MTH = 0;
        WS_FROM_DAY = 0;
        WS_TO_YEAR = 0;
        WS_TO_MTH = 0;
        WS_TO_DAY = 0;
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_FROM_MTH = 0;
        else WS_FROM_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FROM_DAY = 0;
        else WS_FROM_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TO_MTH = 0;
        else WS_TO_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TO_DAY = 0;
        else WS_TO_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        WS_DIFF_YEARS = (short) (WS_FROM_YEAR - WS_TO_YEAR);
        if (WS_FROM_DAY == 31) {
            WS_FROM_DAY = 30;
        }
        if (WS_TO_DAY == 31) {
            WS_TO_DAY = 30;
        }
        BPCIXBSD.DATA.DAYS = (short) (WS_DIFF_YEARS * 360 + ( WS_TO_MTH - WS_FROM_MTH ) * 30 + ( WS_TO_DAY - WS_FROM_DAY ));
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void B050_QUERY_A360_ACCRU_DAYS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.START_DT);
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.MATRUE_DT);
        WS_FROM_DATE = 0;
        WS_TO_DATE = 0;
        WS_FROM_YEAR = 0;
        WS_TO_YEAR = 0;
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        WS_DIFF_YEARS = (short) (WS_FROM_YEAR - WS_TO_YEAR);
        if (WS_TO_YEAR - WS_FROM_YEAR >= 1) {
            B050_01_SPAN_YEAR_PROC();
            if (pgmRtn) return;
        } else {
            WS_FROM_DATE = BPCIXBSD.DATA.START_DT;
            WS_TO_DATE = BPCIXBSD.DATA.MATRUE_DT;
            S000_GET_ACTUAL_DAYS_SEQ();
            if (pgmRtn) return;
            BPCIXBSD.DATA.DAYS = WS_TOT_DIFF_DAYS;
        }
        CEP.TRC(SCCGWA, BPCIXBSD.DATA.DAYS);
    }
    public void A010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_tmp_str[2] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[2].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
        JIBS_tmp_str[3] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[3].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
        JIBS_tmp_str[4] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[4].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
        JIBS_tmp_str[5] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[5].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
        if (JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase("9999") 
            || JIBS_tmp_str[1].substring(0, 4).equalsIgnoreCase("9999") 
            || JIBS_tmp_str[2].substring(0, 4).compareTo("1900") < 0 
            || JIBS_tmp_str[3].substring(0, 4).compareTo("2100") > 0 
            || JIBS_tmp_str[4].substring(0, 4).compareTo("1900") < 0 
            || JIBS_tmp_str[5].substring(0, 4).compareTo("2100") > 0) {
            if ("0859".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
            else BPCIXBSD.RC.RC_CODE = Short.parseShort("0859");
            Z_RET();
            if (pgmRtn) return;
        }
        A010_01_CHECK_MONTH_DAYS();
        if (pgmRtn) return;
    }
    public void A010_01_CHECK_MONTH_DAYS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        if (WS_LEAP_YEAR_FLG == 'Y') {
            JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + BPCIXBSD.DATA.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + BPCIXBSD.DATA.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                && (JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo("29") > 0 
                || JIBS_tmp_str[2].substring(7 - 1, 7 + 2 - 1).compareTo("0") < 0)) {
                if ("0859".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
                else BPCIXBSD.RC.RC_CODE = Short.parseShort("0859");
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + BPCIXBSD.DATA.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + BPCIXBSD.DATA.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                && (JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo("28") > 0 
                || JIBS_tmp_str[2].substring(7 - 1, 7 + 2 - 1).compareTo("0") < 0)) {
                if ("0859".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
                else BPCIXBSD.RC.RC_CODE = Short.parseShort("0859");
                Z_RET();
                if (pgmRtn) return;
            }
        }
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        if (WS_LEAP_YEAR_FLG == 'Y') {
            JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + BPCIXBSD.DATA.MATRUE_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + BPCIXBSD.DATA.MATRUE_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                && (JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo("29") > 0 
                || JIBS_tmp_str[2].substring(7 - 1, 7 + 2 - 1).compareTo("0") < 0)) {
                if ("0859".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
                else BPCIXBSD.RC.RC_CODE = Short.parseShort("0859");
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.MATRUE_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + BPCIXBSD.DATA.MATRUE_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + BPCIXBSD.DATA.MATRUE_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                && (JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo("28") > 0 
                || JIBS_tmp_str[2].substring(7 - 1, 7 + 2 - 1).compareTo("0") < 0)) {
                if ("0859".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
                else BPCIXBSD.RC.RC_CODE = Short.parseShort("0859");
                Z_RET();
                if (pgmRtn) return;
            }
        }
        JIBS_tmp_str[0] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + BPCIXBSD.DATA.START_DT;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_tmp_str[2] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[2].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
        JIBS_tmp_str[3] = "" + BPCIXBSD.DATA.MATRUE_DT;
        JIBS_tmp_int = JIBS_tmp_str[3].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).compareTo("12") > 0 
            || JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).compareTo("0") < 0 
            || JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).compareTo("12") > 0 
            || JIBS_tmp_str[3].substring(5 - 1, 5 + 2 - 1).compareTo("0") < 0) {
            if ("0859".trim().length() == 0) BPCIXBSD.RC.RC_CODE = 0;
            else BPCIXBSD.RC.RC_CODE = Short.parseShort("0859");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LEAP_YEAR_CHECK() throws IOException,SQLException,Exception {
        WS_LEAP_YEAR_FLG = 'N';
        WS_G = 0;
        WS_R = 0;
        WS_R = (short) (WS_YEAR % 4);
        WS_G = (short) ((WS_YEAR - WS_R) / 4);
        if (WS_R == 0) {
            WS_R = (short) (WS_YEAR % 100);
            WS_G = (short) ((WS_YEAR - WS_R) / 100);
            if (WS_R != 0) {
                WS_LEAP_YEAR_FLG = 'Y';
            } else {
                WS_R = (short) (WS_YEAR % 400);
                WS_G = (short) ((WS_YEAR - WS_R) / 400);
                if (WS_R == 0) {
                    WS_LEAP_YEAR_FLG = 'Y';
                }
            }
        }
    }
    public void S000_GET_30_DAYS_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FROM_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        WS_TOT_DIFF_DAYS = 0;
        WS_TOT_FROM_DAYS = 0;
        WS_TOT_TO_DAYS = 0;
        WS_FROM_YEAR = 0;
        WS_FROM_MTH = 0;
        WS_FROM_DAY = 0;
        WS_TO_YEAR = 0;
        WS_TO_MTH = 0;
        WS_TO_DAY = 0;
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_FROM_MTH = 0;
        else WS_FROM_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FROM_DAY = 0;
        else WS_FROM_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TO_MTH = 0;
        else WS_TO_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TO_DAY = 0;
        else WS_TO_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        if (WS_FROM_DAY == 31) {
            WS_FROM_DAY = 30;
        }
        CEP.TRC(SCCGWA, WS_FROM_DAY);
        if (WS_TO_DAY == 31) {
            WS_TO_DAY = 30;
        }
        CEP.TRC(SCCGWA, WS_TO_DAY);
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        WS_FM_YEAR_LY = WS_LEAP_YEAR_FLG;
        CEP.TRC(SCCGWA, WS_FM_YEAR_LY);
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        WS_TO_YEAR_LY = WS_LEAP_YEAR_FLG;
        CEP.TRC(SCCGWA, WS_TO_YEAR_LY);
        WS_I = 0;
        for (WS_I = 1; WS_I < WS_FROM_MTH; WS_I += 1) {
            WS_TOT_FROM_DAYS += BPZIXBSD_WS1.WS_LY_MDAYS_30[WS_I-1];
        }
        CEP.TRC(SCCGWA, WS_TOT_FROM_DAYS);
        WS_TOT_FROM_DAYS += WS_FROM_DAY;
        CEP.TRC(SCCGWA, WS_TOT_FROM_DAYS);
        WS_I = 0;
        for (WS_I = 1; WS_I < WS_TO_MTH; WS_I += 1) {
            WS_TOT_TO_DAYS += BPZIXBSD_WS1.WS_LY_MDAYS_30[WS_I-1];
        }
        CEP.TRC(SCCGWA, WS_TOT_TO_DAYS);
        if (WS_TO_YEAR_LY == 'Y' 
            && WS_TO_MTH == 2 
            && WS_TO_DAY == 29) {
            WS_TOT_TO_DAYS += 30;
        } else {
            if (WS_TO_YEAR_LY == 'N' 
                && WS_TO_MTH == 2 
                && WS_TO_DAY == 28) {
                WS_TOT_TO_DAYS += 30;
            } else {
                WS_TOT_TO_DAYS += WS_TO_DAY;
            }
        }
        CEP.TRC(SCCGWA, WS_TOT_TO_DAYS);
        WS_TOT_DIFF_DAYS = (short) (WS_TOT_TO_DAYS - WS_TOT_FROM_DAYS);
        CEP.TRC(SCCGWA, WS_TOT_DIFF_DAYS);
    }
    public void S000_GET_30E_DAYS_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FROM_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        WS_TOT_DIFF_DAYS = 0;
        WS_TOT_FROM_DAYS = 0;
        WS_TOT_TO_DAYS = 0;
        WS_FROM_YEAR = 0;
        WS_FROM_MTH = 0;
        WS_FROM_DAY = 0;
        WS_TO_YEAR = 0;
        WS_TO_MTH = 0;
        WS_TO_DAY = 0;
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_FROM_MTH = 0;
        else WS_FROM_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FROM_DAY = 0;
        else WS_FROM_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TO_MTH = 0;
        else WS_TO_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TO_DAY = 0;
        else WS_TO_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        if (WS_FROM_DAY == 31) {
            WS_FROM_DAY = 30;
        }
        CEP.TRC(SCCGWA, WS_FROM_DAY);
        if (WS_TO_DAY == 31) {
            WS_TO_DAY = 30;
        }
        CEP.TRC(SCCGWA, WS_TO_DAY);
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        WS_FM_YEAR_LY = WS_LEAP_YEAR_FLG;
        CEP.TRC(SCCGWA, WS_FM_YEAR_LY);
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        WS_TO_YEAR_LY = WS_LEAP_YEAR_FLG;
        CEP.TRC(SCCGWA, WS_TO_YEAR_LY);
        WS_I = 0;
        for (WS_I = 1; WS_I < WS_FROM_MTH; WS_I += 1) {
            WS_TOT_FROM_DAYS += BPZIXBSD_WS1.WS_LY_MDAYS_30[WS_I-1];
        }
        WS_TOT_FROM_DAYS += WS_FROM_DAY;
        CEP.TRC(SCCGWA, WS_TOT_FROM_DAYS);
        WS_I = 0;
        for (WS_I = 1; WS_I < WS_TO_MTH; WS_I += 1) {
            WS_TOT_TO_DAYS += BPZIXBSD_WS1.WS_LY_MDAYS_30[WS_I-1];
        }
        CEP.TRC(SCCGWA, WS_TO_DAY);
        WS_TOT_TO_DAYS += WS_TO_DAY;
        CEP.TRC(SCCGWA, WS_TOT_TO_DAYS);
        WS_TOT_DIFF_DAYS = (short) (WS_TOT_TO_DAYS - WS_TOT_FROM_DAYS);
        CEP.TRC(SCCGWA, WS_TOT_DIFF_DAYS);
    }
    public void S000_GET_ACTUAL_DAYS_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FROM_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        WS_TOT_DIFF_DAYS = 0;
        WS_TOT_FROM_DAYS = 0;
        WS_TOT_TO_DAYS = 0;
        WS_FROM_YEAR = 0;
        WS_FROM_MTH = 0;
        WS_FROM_DAY = 0;
        WS_TO_YEAR = 0;
        WS_TO_MTH = 0;
        WS_TO_DAY = 0;
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_FROM_YEAR = 0;
        else WS_FROM_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_FROM_MTH = 0;
        else WS_FROM_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FROM_DAY = 0;
        else WS_FROM_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TO_YEAR = 0;
        else WS_TO_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TO_MTH = 0;
        else WS_TO_MTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TO_DAY = 0;
        else WS_TO_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_FROM_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        WS_FM_YEAR_LY = WS_LEAP_YEAR_FLG;
        CEP.TRC(SCCGWA, WS_FM_YEAR_LY);
        JIBS_tmp_str[0] = "" + WS_TO_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_LEAP_YEAR_CHECK();
        if (pgmRtn) return;
        WS_TO_YEAR_LY = WS_LEAP_YEAR_FLG;
        CEP.TRC(SCCGWA, WS_TO_YEAR_LY);
        WS_I = 0;
        for (WS_I = 1; WS_I < WS_FROM_MTH; WS_I += 1) {
            if (WS_I == 2 
                && WS_FM_YEAR_LY == 'Y') {
                WS_TOT_FROM_DAYS += 29;
            } else {
                if (WS_I == 2 
                    && WS_FM_YEAR_LY == 'N') {
                    WS_TOT_FROM_DAYS += 28;
                } else {
                    WS_TOT_FROM_DAYS += BPZIXBSD_WS2.WS_Y_MTH_DAYS[WS_I-1];
                }
            }
        }
        WS_TOT_FROM_DAYS += WS_FROM_DAY;
        CEP.TRC(SCCGWA, WS_TOT_FROM_DAYS);
        WS_I = 0;
        for (WS_I = 1; WS_I < WS_TO_MTH; WS_I += 1) {
            if (WS_I == 2 
                && WS_TO_YEAR_LY == 'Y') {
                WS_TOT_TO_DAYS += 29;
            } else {
                if (WS_I == 2 
                    && WS_TO_YEAR_LY == 'N') {
                    WS_TOT_TO_DAYS += 28;
                } else {
                    WS_TOT_TO_DAYS += BPZIXBSD_WS2.WS_Y_MTH_DAYS[WS_I-1];
                }
            }
        }
        WS_TOT_TO_DAYS += WS_TO_DAY;
        CEP.TRC(SCCGWA, WS_TOT_TO_DAYS);
        WS_TOT_DIFF_DAYS = (short) (WS_TOT_TO_DAYS - WS_TOT_FROM_DAYS);
        CEP.TRC(SCCGWA, WS_TOT_DIFF_DAYS);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIXBSD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIXBSD = ");
            CEP.TRC(SCCGWA, BPCIXBSD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
