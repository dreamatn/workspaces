package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCDDT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WK_1 = 1;
    short WK_7 = 7;
    short WK_10 = 10;
    short WK_MMDD = 0229;
    int WS_WORK_DATE1 = 0;
    int WS_WORK_DATE2 = 0;
    short WS_DD1 = 0;
    short WS_DD2 = 0;
    short WS_DD3 = 0;
    short WS_DD_10 = 0;
    short WS_DD_20 = 0;
    short WS_I = 0;
    BPZPCDDT_WS_TMP_DATE WS_TMP_DATE = new BPZPCDDT_WS_TMP_DATE();
    short WS_MULI_DAY_1 = 0;
    short WS_MULI_DAY_2 = 0;
    short WS_RESI_DAY_1 = 0;
    short WS_RESI_DAY_2 = 0;
    short WS_WEEK_DAY1 = 0;
    short WS_WEEK_DAY2 = 0;
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FUNC = ' ';
    char WS_LEAP_YEAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCPCDDT BPCPCDDT;
    public void MP(SCCGWA SCCGWA, BPCPCDDT BPCPCDDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCDDT = BPCPCDDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCDDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, SCCCKDT);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPCDDT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCPCDDT.INPUT.DELE_FRY == ' ' 
            || BPCPCDDT.INPUT.DELE_FRY == '0') 
            || (BPCPCDDT.INPUT.DELE_CYC == ' ' 
            || BPCPCDDT.INPUT.DELE_CYC == 0) 
            || (BPCPCDDT.INPUT.AC_DATE == ' ' 
            || BPCPCDDT.INPUT.AC_DATE == 0) 
            || (BPCPCDDT.INPUT.LAST_DATE == ' ' 
            || BPCPCDDT.INPUT.LAST_DATE == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPCDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        BPCPCDDT.OUTPUT.DELE_FLAG = 'N';
        if (BPCPCDDT.INPUT.DELE_FRY == 'D') {
            B210_DAY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'W') {
            B220_WEEK_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'T') {
            B230_TEN_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'M') {
            B240_MONTH_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'Q') {
            B250_QUART_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'H') {
            B260_HALFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'Y') {
            B270_YEAR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCDDT.INPUT.DELE_FRY == 'S') {
            B280_SPEC_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPCDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_DAY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE2 = BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + SCCCLDT.DATE1;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SCCCLDT.DATE1;
        JIBS_NumStr = JIBS_tmp_str[0].substring(0, 4) + JIBS_NumStr.substring(4);
        SCCCLDT.DATE1 = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + SCCCLDT.DATE1;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SCCCLDT.DATE1;
        JIBS_tmp_str[1] = "" + 101;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 4 - 1);
        SCCCLDT.DATE1 = Integer.parseInt(JIBS_NumStr);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_RESI_DAY_1 = (short) (SCCCLDT.DAYS % BPCPCDDT.INPUT.DELE_CYC);
        WS_MULI_DAY_1 = (short) ((SCCCLDT.DAYS - WS_RESI_DAY_1) / BPCPCDDT.INPUT.DELE_CYC);
        if (WS_RESI_DAY_1 == 0) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        }
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (JIBS_tmp_str[0].substring(0, 4).compareTo(JIBS_tmp_str[1].substring(0, 4)) < 0 
            && WS_MULI_DAY_1 > 1) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        }
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4)) 
            && BPCPCDDT.OUTPUT.DELE_FLAG == 'N') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE2 = BPCPCDDT.INPUT.LAST_DATE;
            JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + SCCCLDT.DATE1;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + SCCCLDT.DATE1;
            JIBS_NumStr = JIBS_tmp_str[0].substring(0, 4) + JIBS_NumStr.substring(4);
            SCCCLDT.DATE1 = Integer.parseInt(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + SCCCLDT.DATE1;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + SCCCLDT.DATE1;
            JIBS_tmp_str[1] = "" + 101;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 4 - 1);
            SCCCLDT.DATE1 = Integer.parseInt(JIBS_NumStr);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_RESI_DAY_2 = (short) (SCCCLDT.DAYS % BPCPCDDT.INPUT.DELE_CYC);
            WS_MULI_DAY_2 = (short) ((SCCCLDT.DAYS - WS_RESI_DAY_2) / BPCPCDDT.INPUT.DELE_CYC);
            if (WS_MULI_DAY_1 > WS_MULI_DAY_2) {
                BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
            }
        }
    }
    public void B220_WEEK_PROCESS() throws IOException,SQLException,Exception {
        SCCCKDT.DATE = BPCPCDDT.INPUT.AC_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        WS_WEEK_DAY1 = SCCCKDT.WEEK_NO;
        if (WS_WEEK_DAY1 == BPCPCDDT.INPUT.DELE_CYC) {
            CEP.TRC(SCCGWA, "B2201111111");
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        } else {
            SCCCLDT.DATE2 = BPCPCDDT.INPUT.AC_DATE;
            SCCCLDT.DATE1 = BPCPCDDT.INPUT.LAST_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            if (SCCCLDT.DAYS >= WK_7) {
                CEP.TRC(SCCGWA, "B2202222222");
                BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
            } else {
                SCCCKDT.DATE = BPCPCDDT.INPUT.LAST_DATE;
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                WS_WEEK_DAY2 = SCCCKDT.WEEK_NO;
                if (WS_WEEK_DAY2 > WS_WEEK_DAY1) {
                    if (WS_WEEK_DAY2 < BPCPCDDT.INPUT.DELE_CYC 
                        || WS_WEEK_DAY1 > BPCPCDDT.INPUT.DELE_CYC) {
                        CEP.TRC(SCCGWA, "B2203333333");
                        BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                    }
                } else {
                    if (WS_WEEK_DAY2 < BPCPCDDT.INPUT.DELE_CYC 
                        && BPCPCDDT.INPUT.DELE_CYC < WS_WEEK_DAY1) {
                        CEP.TRC(SCCGWA, "B2204444444");
                        BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                    }
                }
            }
        }
    }
    public void B230_TEN_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_DD2 = 0;
        else WS_DD2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCCKDT.DATE = BPCPCDDT.INPUT.AC_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_DD1 = 0;
        else WS_DD1 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        WS_DD_10 = (short) (BPCPCDDT.INPUT.DELE_CYC + 10);
        WS_DD_20 = (short) (BPCPCDDT.INPUT.DELE_CYC + 20);
        if (SCCCKDT.MTH_DAYS == WS_DD2 
            && BPCPCDDT.INPUT.DELE_CYC == 10) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        } else {
            if (WS_DD2 == BPCPCDDT.INPUT.DELE_CYC 
                || WS_DD2 == WS_DD_10 
                || WS_DD2 == WS_DD_20) {
                if (WS_DD2 == 30) {
                    BPCPCDDT.OUTPUT.DELE_FLAG = 'N';
                } else {
                    BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                }
            } else {
                SCCCLDT.DATE2 = BPCPCDDT.INPUT.AC_DATE;
                SCCCLDT.DATE1 = BPCPCDDT.INPUT.LAST_DATE;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                if (SCCCLDT.DAYS >= WK_10) {
                    BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                } else {
                    if (WS_DD1 > WS_DD2) {
                        if (BPCPCDDT.INPUT.DELE_CYC == 10) {
                            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                        } else {
                            if (WS_DD1 < WS_DD_20 
                                || BPCPCDDT.INPUT.DELE_CYC <= WS_DD2) {
                                BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                            }
                        }
                    } else {
                        if ((WS_DD1 < BPCPCDDT.INPUT.DELE_CYC 
                            && BPCPCDDT.INPUT.DELE_CYC < WS_DD2) 
                            || (WS_DD1 < WS_DD_10 
                            && WS_DD_10 < WS_DD2) 
                            || (WS_DD1 < WS_DD_20 
                            && WS_DD_20 < WS_DD2)) {
                            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                        }
                    }
                }
            }
        }
    }
    public void B240_MONTH_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_DD2 = 0;
        else WS_DD2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCCKDT.DATE = BPCPCDDT.INPUT.AC_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (WS_DD2 == BPCPCDDT.INPUT.DELE_CYC 
            || (WS_DD2 == SCCCKDT.MTH_DAYS 
            && SCCCKDT.MTH_DAYS < BPCPCDDT.INPUT.DELE_CYC)) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        } else {
            WS_STOP_FLG = 'N';
            for (WS_TMP_DATE.WS_MMDD.WS_INPUT_MM = 1; WS_TMP_DATE.WS_MMDD.WS_INPUT_MM <= 12 
                && WS_STOP_FLG != 'Y'; WS_TMP_DATE.WS_MMDD.WS_INPUT_MM += 1) {
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD.WS_INPUT_MM);
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.DELE_CYC;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TMP_DATE.WS_MMDD.WS_INPUT_DD = 0;
                else WS_TMP_DATE.WS_MMDD.WS_INPUT_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                R000_CHECK_DELE_DATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B250_QUART_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_DD2 = 0;
        else WS_DD2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCCKDT.DATE = BPCPCDDT.INPUT.AC_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (WS_DD2 == BPCPCDDT.INPUT.DELE_CYC 
            || (WS_DD2 == SCCCKDT.MTH_DAYS 
            && SCCCKDT.MTH_DAYS < BPCPCDDT.INPUT.DELE_CYC)) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        } else {
            for (WS_TMP_DATE.WS_MMDD.WS_INPUT_MM = 1; WS_TMP_DATE.WS_MMDD.WS_INPUT_MM <= 12 
                && WS_STOP_FLG != 'Y'; WS_TMP_DATE.WS_MMDD.WS_INPUT_MM += 1) {
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD.WS_INPUT_MM);
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.DELE_CYC;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TMP_DATE.WS_MMDD.WS_INPUT_DD = 0;
                else WS_TMP_DATE.WS_MMDD.WS_INPUT_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
            }
        }
    }
    public void B260_HALFY_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_DD2 = 0;
        else WS_DD2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCCKDT.DATE = BPCPCDDT.INPUT.AC_DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (WS_DD2 == BPCPCDDT.INPUT.DELE_CYC 
            || (WS_DD2 == SCCCKDT.MTH_DAYS 
            && SCCCKDT.MTH_DAYS < BPCPCDDT.INPUT.DELE_CYC)) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        } else {
            for (WS_TMP_DATE.WS_MMDD.WS_INPUT_MM = 1; WS_TMP_DATE.WS_MMDD.WS_INPUT_MM <= 12 
                && WS_STOP_FLG != 'Y'; WS_TMP_DATE.WS_MMDD.WS_INPUT_MM += 1) {
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD.WS_INPUT_MM);
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.DELE_CYC;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TMP_DATE.WS_MMDD.WS_INPUT_DD = 0;
                else WS_TMP_DATE.WS_MMDD.WS_INPUT_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
                R000_CHECK_DELE_DATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B270_YEAR_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1).equalsIgnoreCase(BPCPCDDT.INPUT.DELE_CYC+"")) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        } else {
            if (BPCPCDDT.INPUT.DELE_CYC == 229) {
                JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TMP_DATE.WS_YYYY = 0;
                else WS_TMP_DATE.WS_YYYY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
                WS_STOP_FLG = 'N';
                JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                for (WS_I = WS_TMP_DATE.WS_YYYY; WS_I <= Short.parseShort(JIBS_tmp_str[0].substring(0, 4)) 
                    && WS_STOP_FLG != 'Y'; WS_I += 1) {
                    B270_1_CHK_LEAP_YEAR();
                    if (pgmRtn) return;
                    B270_2_COMPARE_DATE();
                    if (pgmRtn) return;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, BPCPCDDT.INPUT.DELE_CYC+"", WS_TMP_DATE.WS_MMDD);
                R000_CHECK_DELE_DATE();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_DELE_DATE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_WORK_DATE1;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORK_DATE1;
        JIBS_NumStr = JIBS_tmp_str[0].substring(0, 4) + JIBS_NumStr.substring(4);
        WS_WORK_DATE1 = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_WORK_DATE1;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORK_DATE1;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE.WS_MMDD);
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 4 - 1);
        WS_WORK_DATE1 = Integer.parseInt(JIBS_NumStr);
        CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TMP_DATE.WS_YYYY = 0;
        else WS_TMP_DATE.WS_YYYY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        WS_TMP_DATE.WS_YYYY = (short) (WS_TMP_DATE.WS_YYYY + 1);
        JIBS_tmp_str[0] = "" + WS_WORK_DATE2;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORK_DATE2;
        JIBS_tmp_str[1] = "" + WS_TMP_DATE.WS_YYYY;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
        WS_WORK_DATE2 = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_WORK_DATE2;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORK_DATE2;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE.WS_MMDD);
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 4 - 1);
        WS_WORK_DATE2 = Integer.parseInt(JIBS_NumStr);
        CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
        if (BPCPCDDT.INPUT.DELE_FRY == 'M' 
            || BPCPCDDT.INPUT.DELE_FRY == 'Q' 
            || BPCPCDDT.INPUT.DELE_FRY == 'H') {
            JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = JIBS_tmp_str[0].substring(0, 4);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE.WS_MMDD);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            if (JIBS_tmp_str[0] == null) JIBS_tmp_str[0] = "";
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] += " ";
            JIBS_tmp_str[1] = JIBS_tmp_str[1].substring(0, 5 - 1) + JIBS_tmp_str[0] + JIBS_tmp_str[1].substring(5 + 2 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            JIBS_tmp_str[1] = "" + 1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(7 + 2 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
            CEP.TRC(SCCGWA, WS_TMP_DATE);
            CEP.TRC(SCCGWA, WS_TMP_DATE.WS_MMDD);
            CEP.TRC(SCCGWA, SCCCKDT.DATE);
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (SCCCKDT.MTH_DAYS < BPCPCDDT.INPUT.DELE_CYC) {
                JIBS_tmp_str[0] = "" + WS_WORK_DATE1;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_WORK_DATE1;
                JIBS_tmp_str[1] = "" + SCCCKDT.MTH_DAYS;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                WS_WORK_DATE1 = Integer.parseInt(JIBS_NumStr);
            }
            JIBS_tmp_str[1] = "" + WS_TMP_DATE.WS_YYYY;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[1];
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE.WS_MMDD);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            if (JIBS_tmp_str[0] == null) JIBS_tmp_str[0] = "";
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] += " ";
            JIBS_tmp_str[1] = JIBS_tmp_str[1].substring(0, 5 - 1) + JIBS_tmp_str[0] + JIBS_tmp_str[1].substring(5 + 2 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            JIBS_tmp_str[1] = "" + 1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(7 + 2 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (SCCCKDT.MTH_DAYS < BPCPCDDT.INPUT.DELE_CYC) {
                JIBS_tmp_str[0] = "" + WS_WORK_DATE2;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_WORK_DATE2;
                JIBS_tmp_str[1] = "" + SCCCKDT.MTH_DAYS;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                WS_WORK_DATE2 = Integer.parseInt(JIBS_NumStr);
            }
        }
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.LAST_DATE);
        CEP.TRC(SCCGWA, WS_WORK_DATE1);
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.AC_DATE);
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.LAST_DATE);
        CEP.TRC(SCCGWA, WS_WORK_DATE2);
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.AC_DATE);
        if ((BPCPCDDT.INPUT.LAST_DATE < WS_WORK_DATE1 
            && WS_WORK_DATE1 < BPCPCDDT.INPUT.AC_DATE) 
            || (BPCPCDDT.INPUT.LAST_DATE < WS_WORK_DATE2 
            && WS_WORK_DATE2 < BPCPCDDT.INPUT.AC_DATE)) {
            if (BPCPCDDT.INPUT.DELE_CYC != WK_MMDD) {
                CEP.TRC(SCCGWA, "111111111111");
                BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                WS_STOP_FLG = 'Y';
            } else {
                R000_CHK_LEAP_YEAR();
                if (pgmRtn) return;
            }
        }
    }
    public void B270_1_CHK_LEAP_YEAR() throws IOException,SQLException,Exception {
        WS_LEAP_YEAR_FLG = 'N';
        WS_RESI_DAY_1 = (short) (WS_I % 4);
        WS_MULI_DAY_1 = (short) ((WS_I - WS_RESI_DAY_1) / 4);
        if (WS_RESI_DAY_1 == 0) {
            WS_LEAP_YEAR_FLG = 'Y';
        }
    }
    public void B270_2_COMPARE_DATE() throws IOException,SQLException,Exception {
        if (WS_LEAP_YEAR_FLG == 'Y') {
            JIBS_tmp_str[1] = "" + WS_I;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[1];
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            JIBS_tmp_str[1] = "" + 229;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(5 + 4 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TMP_DATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_TMP_DATE);
            if (BPCPCDDT.INPUT.LAST_DATE < JIBS_tmp_str[0] 
                && JIBS_tmp_str[2].compareTo(BPCPCDDT.INPUT.AC_DATE) <= 0) {
                BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                WS_STOP_FLG = 'Y';
            }
        }
    }
    public void R000_CHK_LEAP_YEAR() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCPCDDT.INPUT.LAST_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_TMP_DATE.WS_YYYY = 0;
        else WS_TMP_DATE.WS_YYYY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        if (BPCPCDDT.INPUT.DELE_FRY == 'Y') {
            if ((BPCPCDDT.INPUT.LAST_DATE < WS_WORK_DATE1 
                && WS_WORK_DATE1 < BPCPCDDT.INPUT.AC_DATE)) {
                WS_RESI_DAY_1 = (short) (WS_TMP_DATE.WS_YYYY % 4);
                WS_MULI_DAY_1 = (short) ((WS_TMP_DATE.WS_YYYY - WS_RESI_DAY_1) / 4);
                if (WS_RESI_DAY_1 == 0) {
                    BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                }
            }
            if ((BPCPCDDT.INPUT.LAST_DATE < WS_WORK_DATE2 
                && WS_WORK_DATE2 < BPCPCDDT.INPUT.AC_DATE)) {
                WS_TMP_DATE.WS_YYYY = (short) (WS_TMP_DATE.WS_YYYY + 1);
                WS_RESI_DAY_2 = (short) (WS_TMP_DATE.WS_YYYY % 4);
                WS_MULI_DAY_2 = (short) ((WS_TMP_DATE.WS_YYYY - WS_RESI_DAY_2) / 4);
                if (WS_RESI_DAY_2 == 0) {
                    BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
                }
            }
        }
    }
    public void B280_SPEC_PROCESS() throws IOException,SQLException,Exception {
        if ((BPCPCDDT.INPUT.LAST_DATE < BPCPCDDT.INPUT.DELE_CYC 
            && BPCPCDDT.INPUT.DELE_CYC <= BPCPCDDT.INPUT.AC_DATE)) {
            BPCPCDDT.OUTPUT.DELE_FLAG = 'Y';
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (0 != 0 
            || SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "CALL SCSSCLDT ERROR");
            CEP.TRC(SCCGWA, SCCCLDT.RC);
            BPCPCDDT.RC.RC_MMO = "SC";
            BPCPCDDT.RC.RC_CODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (0 != 0 
            || SCCCKDT.RC != 0) {
            CEP.TRC(SCCGWA, "CALL SCSSCKDT ERROR");
            CEP.TRC(SCCGWA, SCCCKDT.RC);
            BPCPCDDT.RC.RC_MMO = "SC";
            BPCPCDDT.RC.RC_CODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCDDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCDDT = ");
            CEP.TRC(SCCGWA, BPCPCDDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
