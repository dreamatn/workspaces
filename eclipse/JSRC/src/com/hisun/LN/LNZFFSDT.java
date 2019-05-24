package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZFFSDT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    DBParm LNTAGRE_RD;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    char K_REPY_INT_ONE = '1';
    char K_REPY_INT_TWO = '2';
    char K_REPY_INT_THR = '3';
    char K_REPY_INT_FOR = '4';
    char K_REPY_INT_FIV = '5';
    char K_REPY_INT_SIX = '6';
    char K_REPY_INT_SEV = '7';
    char K_REPY_INT_ENG = '8';
    char K_FIXED_DAY = '2';
    char K_MONTH = 'M';
    char K_DAY = 'D';
    short K_ONE_WEEK = 7;
    short K_TWO_WEEK = 14;
    short K_ONE_MONTH = 1;
    short K_ONE_SEASON = 3;
    short K_HALF_YEAR = 6;
    short K_ONE_YEAR = 12;
    short K_FIRST_M = 1;
    short K_SECON_M = 2;
    short K_THIRD_M = 3;
    short K_FORTH_M = 4;
    short K_FIFTH_M = 5;
    short K_SIXTH_M = 6;
    short K_SEVTH_M = 7;
    short K_EIGTH_M = 8;
    short K_NINTH_M = 9;
    short K_TENTH_M = 10;
    short K_ELEVT_M = 11;
    short K_TWLTH_M = 12;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_CAL_DDD = 0;
    short WS_CAL_WEEKNO = 0;
    short WS_STARTDT_WEEKNO = 0;
    short WS_WEEK_CAL = 0;
    short WS_T_MTHS = 0;
    short WS_MTHS = 0;
    short WS_MTHS1 = 0;
    short WS_MTHS_TMP = 0;
    int WS_CAL_DATE = 0;
    String WS_FATHER_CONTRACT = " ";
    int WS_CAL_DATE1 = 0;
    int WS_CAL_DATE2 = 0;
    short WS_FLT_DAY1 = 0;
    short WS_FLT_DAY2 = 0;
    short WS_TEM_DAY = 0;
    short WS_CAL_DAY = 0;
    short WS_VALID_DAY = 0;
    int WS_AC_DATE = 0;
    int WS_CLDT_DATE2 = 0;
    int WS_DATE = 0;
    int WS_GET_DATE = 0;
    char WS_GET_UNIT = ' ';
    short WS_GET_PERIOD = 0;
    double WS_YYYY_1 = 0;
    short WS_YYYY_2 = 0;
    int WS_YYYYMMDD = 0;
    LNZFFSDT_REDEFINES29 REDEFINES29 = new LNZFFSDT_REDEFINES29();
    int WS_YYYYMMDD1 = 0;
    LNZFFSDT_REDEFINES34 REDEFINES34 = new LNZFFSDT_REDEFINES34();
    int WS_YYYYMMDD2 = 0;
    LNZFFSDT_REDEFINES39 REDEFINES39 = new LNZFFSDT_REDEFINES39();
    int WS_YYYYMMDD3 = 0;
    LNZFFSDT_REDEFINES44 REDEFINES44 = new LNZFFSDT_REDEFINES44();
    int WS_YYYYMMDD4 = 0;
    LNZFFSDT_REDEFINES49 REDEFINES49 = new LNZFFSDT_REDEFINES49();
    int WS_YYYYMMDD5 = 0;
    LNZFFSDT_REDEFINES54 REDEFINES54 = new LNZFFSDT_REDEFINES54();
    short WS_PAY_DTEM = 0;
    double WS_TIME_1 = 0;
    short WS_TIME_2 = 0;
    short WS_7_TIME = 0;
    char WS_FLG_DT = ' ';
    short WS_CMSF_MM = 0;
    short WS_CMSF_MM1 = 0;
    int WS_APRD_DAY = 0;
    LNZFFSDT_REDEFINES68 REDEFINES68 = new LNZFFSDT_REDEFINES68();
    short WS_CMSF_PAY_DAY = 0;
    short WS_PAY_DTEM1 = 0;
    char WS_RATE_FLT_FLG = ' ';
    short WS_DATE_DAY_TYPE = 0;
    short WS_HALF_YEA_TYPE = 0;
    short WS_ONE_YEA_TYPE = 0;
    char WS_PAYI_PERD = ' ';
    char WS_PERFORM_FLG = ' ';
    char WS_CAL_FFLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    LNCFFSDT LNCFFSDT;
    public void MP(SCCGWA SCCGWA, LNCFFSDT LNCFFSDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCFFSDT = LNCFFSDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZFFSDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        LNCFFSDT.RC.RC_APP = "LN";
        LNCFFSDT.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (LNCFFSDT.FUNC == 'R') {
            R000_GET_REPAY_MTH();
            if (pgmRtn) return;
        } else if (LNCFFSDT.FUNC == 'C') {
            B210_CHECK_PROC();
            if (pgmRtn) return;
            if (LNCFFSDT.PAY_DTYP == K_FIXED_DAY) {
                R000_GET_FSTDT();
                if (pgmRtn) return;
            } else {
                R100_GET_FSTDT_NORMAL();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCFFSDT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B210_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.CAL_PERU);
        CEP.TRC(SCCGWA, LNCFFSDT.CAL_PERD);
        WS_VALID_DAY = LNCFFSDT.CAL_DAY;
        if (((WS_VALID_DAY != 1 
            && WS_VALID_DAY != 2 
            && WS_VALID_DAY != 3 
            && WS_VALID_DAY != 4 
            && WS_VALID_DAY != 5 
            && WS_VALID_DAY != 6 
            && WS_VALID_DAY != 7 
            && WS_VALID_DAY != 8 
            && WS_VALID_DAY != 9 
            && WS_VALID_DAY != 10 
            && WS_VALID_DAY != 11 
            && WS_VALID_DAY != 12 
            && WS_VALID_DAY != 13 
            && WS_VALID_DAY != 14 
            && WS_VALID_DAY != 15 
            && WS_VALID_DAY != 16 
            && WS_VALID_DAY != 17 
            && WS_VALID_DAY != 18 
            && WS_VALID_DAY != 19 
            && WS_VALID_DAY != 20 
            && WS_VALID_DAY != 21 
            && WS_VALID_DAY != 22 
            && WS_VALID_DAY != 23 
            && WS_VALID_DAY != 24 
            && WS_VALID_DAY != 25 
            && WS_VALID_DAY != 26 
            && WS_VALID_DAY != 27 
            && WS_VALID_DAY != 28 
            && WS_VALID_DAY != 29 
            && WS_VALID_DAY != 30 
            && WS_VALID_DAY != 31) 
            && LNCFFSDT.PAY_DTYP == '2' 
            && (LNCFFSDT.PAYI_PERD != '2' 
            && LNCFFSDT.PAYI_PERD != '3')) 
            || ((WS_VALID_DAY != 1 
            && WS_VALID_DAY != 2 
            && WS_VALID_DAY != 3 
            && WS_VALID_DAY != 4 
            && WS_VALID_DAY != 5 
            && WS_VALID_DAY != 6 
            && WS_VALID_DAY != 7) 
            && LNCFFSDT.PAY_DTYP == '2' 
            && (LNCFFSDT.PAYI_PERD == '2' 
            || LNCFFSDT.PAYI_PERD == '3'))) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APPINTED_DAY, LNCFFSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.REPAY_MTH != '1' 
            && LNCFFSDT.REPAY_MTH != '0') {
            if (LNCFFSDT.CAL_PERU != 'M' 
                && LNCFFSDT.CAL_PERU != 'D' 
                && LNCFFSDT.REPAY_MTH != '5') {
            }
            if (LNCFFSDT.CAL_PERD == 0 
                && LNCFFSDT.REPAY_MTH != '5') {
            }
        } else {
            if (LNCFFSDT.CAL_PERU != ' ' 
                && LNCFFSDT.CAL_PERD != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CAL_PERD_CNNT_I, LNCFFSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_REPAY_MTH() throws IOException,SQLException,Exception {
        if (LNCFFSDT.BREP_MTH == '0') {
            LNCFFSDT.REPAY_MTH = '0';
            LNCFFSDT.PAYI_PERD = K_REPY_INT_ONE;
        } else if (LNCFFSDT.BREP_MTH == '1') {
            LNCFFSDT.REPAY_MTH = '1';
            LNCFFSDT.PAYI_PERD = K_REPY_INT_ONE;
        } else if (LNCFFSDT.BREP_MTH == '2') {
            LNCFFSDT.REPAY_MTH = '2';
        } else if (LNCFFSDT.BREP_MTH == '3') {
            LNCFFSDT.REPAY_MTH = '3';
        } else if (LNCFFSDT.BREP_MTH == '8'
            || LNCFFSDT.BREP_MTH == '4') {
            LNCFFSDT.REPAY_MTH = '4';
            LNCFFSDT.INST_MTH = '1';
        } else if (LNCFFSDT.BREP_MTH == '9'
            || LNCFFSDT.BREP_MTH == '5') {
            LNCFFSDT.REPAY_MTH = '4';
            LNCFFSDT.INST_MTH = '2';
        } else if (LNCFFSDT.BREP_MTH == '6') {
            LNCFFSDT.REPAY_MTH = '5';
        } else if (LNCFFSDT.BREP_MTH == '7') {
            LNCFFSDT.REPAY_MTH = '4';
        }
        if (LNCFFSDT.PAYI_PERD == K_REPY_INT_ONE) {
            LNCFFSDT.CAL_FSDT = LNCFFSDT.MATU_DT;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_TWO) {
            LNCFFSDT.CAL_PERD = K_ONE_WEEK;
            LNCFFSDT.CAL_PERU = K_DAY;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_THR) {
            LNCFFSDT.CAL_PERD = K_TWO_WEEK;
            LNCFFSDT.CAL_PERU = K_DAY;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_FOR) {
            LNCFFSDT.CAL_PERD = K_ONE_MONTH;
            LNCFFSDT.CAL_PERU = K_MONTH;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_FIV) {
            LNCFFSDT.CAL_PERD = K_ONE_SEASON;
            LNCFFSDT.CAL_PERU = K_MONTH;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_SIX) {
            LNCFFSDT.CAL_PERD = K_HALF_YEAR;
            LNCFFSDT.CAL_PERU = K_MONTH;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_SEV) {
            LNCFFSDT.CAL_PERD = K_ONE_YEAR;
            LNCFFSDT.CAL_PERU = K_MONTH;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_ENG) {
        } else if (LNCFFSDT.PAYI_PERD == ) {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INTREP_PERDMST, LNCFFSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCFFSDT.PYP_CIRC);
        if (LNCFFSDT.PYP_CIRC != ' ') {
            R000_MAPPING_PRIN_PERD();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_FSTDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.CAL_FFLG);
        CEP.TRC(SCCGWA, LNCFFSDT.PAYI_PERD);
        if (LNCFFSDT.PAYI_PERD == K_REPY_INT_ONE) {
            LNCFFSDT.CAL_FSDT = LNCFFSDT.MATU_DT;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_TWO) {
            if (LNCFFSDT.PAY_DTEM != 0 
                && LNCFFSDT.PAY_DTEM != 1) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_INVALID, LNCFFSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFFSDT.PAY_DTEM != 1) {
                LNCFFSDT.PAY_DTEM = 1;
            }
            WS_WEEK_CAL = 7;
            R000_CAL_BY_WEEK();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_THR) {
            if (LNCFFSDT.PAY_DTEM != 0 
                && LNCFFSDT.PAY_DTEM != 1 
                && LNCFFSDT.PAY_DTEM != 2) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_INVALID, LNCFFSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFFSDT.PAY_DTEM == 0) {
                LNCFFSDT.PAY_DTEM = 1;
            }
            WS_WEEK_CAL = 14;
            R000_CAL_BY_WEEK();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_FOR) {
            if (LNCFFSDT.PAY_DTEM != 0 
                && LNCFFSDT.PAY_DTEM != 1) {
                LNCFFSDT.PAY_DTEM = 0;
            }
            JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") >= 0) {
                LNCFFSDT.PAY_DTEM = 1;
            } else {
                JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (LNCFFSDT.PAY_DTEM == 1 
                    && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0) {
                    LNCFFSDT.PAY_DTEM = 0;
                }
            }
            CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
            WS_MTHS = 1;
            WS_MTHS1 = 1;
            R000_CAL_MONTH();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_FIV) {
            CEP.TRC(SCCGWA, "5");
            CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
            if (LNCFFSDT.PAY_DTEM != 0 
                && LNCFFSDT.PAY_DTEM != 1 
                && LNCFFSDT.PAY_DTEM != 2 
                && LNCFFSDT.PAY_DTEM != 3) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_INVALID, LNCFFSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "51");
            JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            JIBS_tmp_str[3] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[3].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
            JIBS_tmp_str[4] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[4].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
            JIBS_tmp_str[5] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[5].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
            if ((LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") >= 0) 
                || (LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                && (!JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3") 
                && !JIBS_tmp_str[3].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6") 
                && !JIBS_tmp_str[4].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9") 
                && !JIBS_tmp_str[5].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")))) {
                WS_PAY_DTEM1 = 1;
            } else {
                JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_tmp_str[2] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[2].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
                JIBS_tmp_str[3] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[3].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
                JIBS_tmp_str[4] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[4].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
                JIBS_tmp_str[5] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[5].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
                JIBS_tmp_str[6] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[6].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[6] = "0"+JIBS_tmp_str[6];
                JIBS_tmp_str[7] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[7].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[7] = "0"+JIBS_tmp_str[7];
                JIBS_tmp_str[8] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[8].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[8] = "0"+JIBS_tmp_str[8];
                JIBS_tmp_str[9] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[9].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[9] = "0"+JIBS_tmp_str[9];
                JIBS_tmp_str[10] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[10].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[10] = "0"+JIBS_tmp_str[10];
                JIBS_tmp_str[11] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[11].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[11] = "0"+JIBS_tmp_str[11];
                JIBS_tmp_str[12] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[12].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[12] = "0"+JIBS_tmp_str[12];
                JIBS_tmp_str[13] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[13].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[13] = "0"+JIBS_tmp_str[13];
                JIBS_tmp_str[14] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[14].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[14] = "0"+JIBS_tmp_str[14];
                if ((LNCFFSDT.PAY_DTEM == 1 
                    && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1") 
                    || JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4") 
                    || JIBS_tmp_str[3].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7") 
                    || JIBS_tmp_str[4].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10"))) 
                    || (LNCFFSDT.PAY_DTEM == 2 
                    && JIBS_tmp_str[5].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[6].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                    || JIBS_tmp_str[7].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5") 
                    || JIBS_tmp_str[8].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8") 
                    || JIBS_tmp_str[9].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11"))) 
                    || (LNCFFSDT.PAY_DTEM == 3 
                    && JIBS_tmp_str[10].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[11].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3") 
                    || JIBS_tmp_str[12].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6") 
                    || JIBS_tmp_str[13].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9") 
                    || JIBS_tmp_str[14].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")))) {
                    WS_PAY_DTEM1 = 0;
                } else {
                    WS_PAY_DTEM1 = LNCFFSDT.PAY_DTEM;
                }
            }
            CEP.TRC(SCCGWA, WS_PAY_DTEM1);
            CEP.TRC(SCCGWA, "52");
            LNCFFSDT.PAY_DTEM = WS_PAY_DTEM1;
            CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
            WS_MTHS = LNCFFSDT.PAY_DTEM;
            WS_MTHS1 = 3;
            R000_CAL_MONTH();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_SIX) {
            if (LNCFFSDT.PAY_DTEM != 0 
                && LNCFFSDT.PAY_DTEM != 1 
                && LNCFFSDT.PAY_DTEM != 2 
                && LNCFFSDT.PAY_DTEM != 3 
                && LNCFFSDT.PAY_DTEM != 4 
                && LNCFFSDT.PAY_DTEM != 5 
                && LNCFFSDT.PAY_DTEM != 6) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_INVALID, LNCFFSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            JIBS_tmp_str[3] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[3].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
            if ((LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") >= 0) 
                || (LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                && (!JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6") 
                && !JIBS_tmp_str[3].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")))) {
                LNCFFSDT.PAY_DTEM = 1;
            } else {
                JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_tmp_str[2] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[2].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
                JIBS_tmp_str[3] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[3].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
                JIBS_tmp_str[4] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[4].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
                JIBS_tmp_str[5] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[5].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
                JIBS_tmp_str[6] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[6].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[6] = "0"+JIBS_tmp_str[6];
                JIBS_tmp_str[7] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[7].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[7] = "0"+JIBS_tmp_str[7];
                JIBS_tmp_str[8] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[8].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[8] = "0"+JIBS_tmp_str[8];
                JIBS_tmp_str[9] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[9].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[9] = "0"+JIBS_tmp_str[9];
                JIBS_tmp_str[10] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[10].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[10] = "0"+JIBS_tmp_str[10];
                JIBS_tmp_str[11] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[11].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[11] = "0"+JIBS_tmp_str[11];
                JIBS_tmp_str[12] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[12].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[12] = "0"+JIBS_tmp_str[12];
                JIBS_tmp_str[13] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[13].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[13] = "0"+JIBS_tmp_str[13];
                JIBS_tmp_str[14] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[14].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[14] = "0"+JIBS_tmp_str[14];
                JIBS_tmp_str[15] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[15].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[15] = "0"+JIBS_tmp_str[15];
                JIBS_tmp_str[16] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[16].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[16] = "0"+JIBS_tmp_str[16];
                JIBS_tmp_str[17] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[17].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[17] = "0"+JIBS_tmp_str[17];
                if ((LNCFFSDT.PAY_DTEM == 1 
                    && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1") 
                    || JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7"))) 
                    || (LNCFFSDT.PAY_DTEM == 2 
                    && JIBS_tmp_str[3].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[4].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                    || JIBS_tmp_str[5].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8"))) 
                    || (LNCFFSDT.PAY_DTEM == 3 
                    && JIBS_tmp_str[6].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[7].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3") 
                    || JIBS_tmp_str[8].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9"))) 
                    || (LNCFFSDT.PAY_DTEM == 4 
                    && JIBS_tmp_str[9].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[10].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4") 
                    || JIBS_tmp_str[11].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10"))) 
                    || (LNCFFSDT.PAY_DTEM == 5 
                    && JIBS_tmp_str[12].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[13].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5") 
                    || JIBS_tmp_str[14].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11"))) 
                    || (LNCFFSDT.PAY_DTEM == 6 
                    && JIBS_tmp_str[15].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && (JIBS_tmp_str[16].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6") 
                    || JIBS_tmp_str[17].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")))) {
                    LNCFFSDT.PAY_DTEM = 0;
                }
            }
            WS_MTHS = LNCFFSDT.PAY_DTEM;
            WS_MTHS1 = 6;
            R000_CAL_MONTH();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
            CEP.TRC(SCCGWA, "222333");
            CEP.TRC(SCCGWA, LNCFFSDT.CAL_FSDT);
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_SEV) {
            if (LNCFFSDT.PAY_DTEM != 0 
                && LNCFFSDT.PAY_DTEM != 1 
                && LNCFFSDT.PAY_DTEM != 2 
                && LNCFFSDT.PAY_DTEM != 3 
                && LNCFFSDT.PAY_DTEM != 4 
                && LNCFFSDT.PAY_DTEM != 5 
                && LNCFFSDT.PAY_DTEM != 6 
                && LNCFFSDT.PAY_DTEM != 7 
                && LNCFFSDT.PAY_DTEM != 8 
                && LNCFFSDT.PAY_DTEM != 9 
                && LNCFFSDT.PAY_DTEM != 10 
                && LNCFFSDT.PAY_DTEM != 11 
                && LNCFFSDT.PAY_DTEM != 12) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_INVALID, LNCFFSDT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[2] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[2].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
            if ((LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") >= 0) 
                || (LNCFFSDT.PAY_DTEM == 0 
                && JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                && (!JIBS_tmp_str[2].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")))) {
                LNCFFSDT.PAY_DTEM = 1;
            } else {
                JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_tmp_str[2] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[2].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
                JIBS_tmp_str[3] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[3].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
                JIBS_tmp_str[4] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[4].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[4] = "0"+JIBS_tmp_str[4];
                JIBS_tmp_str[5] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[5].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[5] = "0"+JIBS_tmp_str[5];
                JIBS_tmp_str[6] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[6].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[6] = "0"+JIBS_tmp_str[6];
                JIBS_tmp_str[7] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[7].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[7] = "0"+JIBS_tmp_str[7];
                JIBS_tmp_str[8] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[8].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[8] = "0"+JIBS_tmp_str[8];
                JIBS_tmp_str[9] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[9].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[9] = "0"+JIBS_tmp_str[9];
                JIBS_tmp_str[10] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[10].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[10] = "0"+JIBS_tmp_str[10];
                JIBS_tmp_str[11] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[11].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[11] = "0"+JIBS_tmp_str[11];
                JIBS_tmp_str[12] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[12].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[12] = "0"+JIBS_tmp_str[12];
                JIBS_tmp_str[13] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[13].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[13] = "0"+JIBS_tmp_str[13];
                JIBS_tmp_str[14] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[14].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[14] = "0"+JIBS_tmp_str[14];
                JIBS_tmp_str[15] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[15].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[15] = "0"+JIBS_tmp_str[15];
                JIBS_tmp_str[16] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[16].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[16] = "0"+JIBS_tmp_str[16];
                JIBS_tmp_str[17] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[17].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[17] = "0"+JIBS_tmp_str[17];
                JIBS_tmp_str[18] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[18].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[18] = "0"+JIBS_tmp_str[18];
                JIBS_tmp_str[19] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[19].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[19] = "0"+JIBS_tmp_str[19];
                JIBS_tmp_str[20] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[20].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[20] = "0"+JIBS_tmp_str[20];
                JIBS_tmp_str[21] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[21].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[21] = "0"+JIBS_tmp_str[21];
                JIBS_tmp_str[22] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[22].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[22] = "0"+JIBS_tmp_str[22];
                JIBS_tmp_str[23] = "" + LNCFFSDT.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[23].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[23] = "0"+JIBS_tmp_str[23];
                if ((LNCFFSDT.PAY_DTEM == 1 
                    && JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1")) 
                    || (LNCFFSDT.PAY_DTEM == 2 
                    && JIBS_tmp_str[2].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[3].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2")) 
                    || (LNCFFSDT.PAY_DTEM == 3 
                    && JIBS_tmp_str[4].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[5].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3")) 
                    || (LNCFFSDT.PAY_DTEM == 4 
                    && JIBS_tmp_str[6].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[7].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4")) 
                    || (LNCFFSDT.PAY_DTEM == 5 
                    && JIBS_tmp_str[8].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[9].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5")) 
                    || (LNCFFSDT.PAY_DTEM == 6 
                    && JIBS_tmp_str[10].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[11].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6")) 
                    || (LNCFFSDT.PAY_DTEM == 7 
                    && JIBS_tmp_str[12].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[13].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7")) 
                    || (LNCFFSDT.PAY_DTEM == 8 
                    && JIBS_tmp_str[14].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[15].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8")) 
                    || (LNCFFSDT.PAY_DTEM == 9 
                    && JIBS_tmp_str[16].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[17].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9")) 
                    || (LNCFFSDT.PAY_DTEM == 10 
                    && JIBS_tmp_str[18].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[19].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10")) 
                    || (LNCFFSDT.PAY_DTEM == 11 
                    && JIBS_tmp_str[20].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[21].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11")) 
                    || (LNCFFSDT.PAY_DTEM == 12 
                    && JIBS_tmp_str[22].substring(7 - 1, 7 + 2 - 1).compareTo(LNCFFSDT.CAL_DAY+"") < 0 
                    && JIBS_tmp_str[23].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12"))) {
                    LNCFFSDT.PAY_DTEM = 0;
                }
            }
            WS_MTHS = LNCFFSDT.PAY_DTEM;
            WS_MTHS1 = 12;
            R000_CAL_MONTH();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        } else if (LNCFFSDT.PAYI_PERD == K_REPY_INT_ENG) {
            R000_CAL_OTHER_RCS();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        } else {
            R000_CAL_OTHER_RCS();
            if (pgmRtn) return;
            LNCFFSDT.CAL_FSDT = WS_CAL_DATE2;
        }
    }
    public void R100_GET_FSTDT_NORMAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCFFSDT.START_DT;
        if (LNCFFSDT.CAL_PERU == 'M') {
            SCCCLDT.MTHS = (short) (LNCFFSDT.CAL_PERD);
        } else {
            SCCCLDT.DAYS = LNCFFSDT.CAL_PERD;
        }
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        R000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        LNCFFSDT.CAL_FSDT = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, LNCFFSDT.CAL_FSDT);
    }
    public void R000_MAPPING_PRIN_PERD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.PYP_CIRC);
        if (LNCFFSDT.PYP_CIRC == K_REPY_INT_TWO) {
            LNCFFSDT.PYP_PERD = K_ONE_WEEK;
            LNCFFSDT.PYP_PERU = K_DAY;
        } else if (LNCFFSDT.PYP_CIRC == K_REPY_INT_THR) {
            LNCFFSDT.PYP_PERD = K_TWO_WEEK;
            LNCFFSDT.PYP_PERU = K_DAY;
        } else if (LNCFFSDT.PYP_CIRC == K_REPY_INT_FOR) {
            LNCFFSDT.PYP_PERD = K_ONE_MONTH;
            LNCFFSDT.PYP_PERU = K_MONTH;
        } else if (LNCFFSDT.PYP_CIRC == K_REPY_INT_FIV) {
            LNCFFSDT.PYP_PERD = K_ONE_SEASON;
            LNCFFSDT.PYP_PERU = K_MONTH;
        } else if (LNCFFSDT.PYP_CIRC == K_REPY_INT_SIX) {
            LNCFFSDT.PYP_PERD = K_HALF_YEAR;
            LNCFFSDT.PYP_PERU = K_MONTH;
        } else if (LNCFFSDT.PYP_CIRC == K_REPY_INT_SEV) {
            LNCFFSDT.PYP_PERD = K_ONE_YEAR;
            LNCFFSDT.PYP_PERU = K_MONTH;
        } else {
        }
        CEP.TRC(SCCGWA, LNCFFSDT.PYP_PERU);
        CEP.TRC(SCCGWA, LNCFFSDT.PYP_PERD);
    }
    public void R000_CAL_BY_WEEK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, LNCFFSDT.START_DT);
        SCCCKDT.DATE = LNCFFSDT.START_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        R000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        WS_STARTDT_WEEKNO = SCCCKDT.WEEK_NO;
        CEP.TRC(SCCGWA, SCCCKDT.WEEK_NO);
        CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTYP);
        if (LNCFFSDT.PAY_DTYP == K_FIXED_DAY) {
            WS_CAL_WEEKNO = LNCFFSDT.CAL_DAY;
        } else {
            WS_CAL_WEEKNO = WS_STARTDT_WEEKNO;
        }
        CEP.TRC(SCCGWA, WS_CAL_WEEKNO);
        if (WS_CAL_WEEKNO != 1 
            && WS_CAL_WEEKNO != 2 
            && WS_CAL_WEEKNO != 3 
            && WS_CAL_WEEKNO != 4 
            && WS_CAL_WEEKNO != 5 
            && WS_CAL_WEEKNO != 6 
            && WS_CAL_WEEKNO != 7) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APPINTED_DAY, LNCFFSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, WS_CAL_DDD);
        WS_CAL_DDD = WS_CAL_WEEKNO - WS_STARTDT_WEEKNO + WS_WEEK_CAL;
        CEP.TRC(SCCGWA, "2");
        CEP.TRC(SCCGWA, WS_CAL_DDD);
        CEP.TRC(SCCGWA, LNCFFSDT.CAL_FFLG);
        if (WS_CAL_DDD <= 0) {
            WS_CAL_DDD += K_ONE_WEEK;
        }
        CEP.TRC(SCCGWA, "3");
        CEP.TRC(SCCGWA, WS_CAL_DDD);
        if (LNCFFSDT.CAL_FFLG == '3' 
            && WS_CAL_DDD < WS_WEEK_CAL) {
            WS_CAL_DDD = K_ONE_WEEK + WS_CAL_DDD;
        }
        CEP.TRC(SCCGWA, "4");
        CEP.TRC(SCCGWA, WS_CAL_DDD);
        CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
        if (LNCFFSDT.PAY_DTEM == K_SECON_M) {
            WS_CAL_DDD += K_ONE_WEEK;
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCFFSDT.START_DT;
        CEP.TRC(SCCGWA, LNCFFSDT.START_DT);
        SCCCLDT.DAYS = WS_CAL_DDD;
        R000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_CAL_DATE1 = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_CAL_DATE1);
        if (WS_CAL_DATE1 < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_CAL_DATE1;
            SCCCLDT.DAYS = WS_WEEK_CAL;
            R000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_CAL_DATE2 = SCCCLDT.DATE2;
        } else {
            WS_CAL_DATE2 = WS_CAL_DATE1;
        }
        CEP.TRC(SCCGWA, WS_CAL_DATE2);
    }
    public void R000_CAL_MONTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTYP);
        if (LNCFFSDT.PAY_DTYP == K_FIXED_DAY 
            && LNCFFSDT.CAL_DAY != 0) {
            WS_TEM_DAY = LNCFFSDT.CAL_DAY;
        } else {
            JIBS_tmp_str[0] = "" + LNCFFSDT.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_TEM_DAY = 0;
            else WS_TEM_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        WS_VALID_DAY = WS_TEM_DAY;
        if ((WS_VALID_DAY != 1 
            && WS_VALID_DAY != 2 
            && WS_VALID_DAY != 3 
            && WS_VALID_DAY != 4 
            && WS_VALID_DAY != 5 
            && WS_VALID_DAY != 6 
            && WS_VALID_DAY != 7 
            && WS_VALID_DAY != 8 
            && WS_VALID_DAY != 9 
            && WS_VALID_DAY != 10 
            && WS_VALID_DAY != 11 
            && WS_VALID_DAY != 12 
            && WS_VALID_DAY != 13 
            && WS_VALID_DAY != 14 
            && WS_VALID_DAY != 15 
            && WS_VALID_DAY != 16 
            && WS_VALID_DAY != 17 
            && WS_VALID_DAY != 18 
            && WS_VALID_DAY != 19 
            && WS_VALID_DAY != 20 
            && WS_VALID_DAY != 21 
            && WS_VALID_DAY != 22 
            && WS_VALID_DAY != 23 
            && WS_VALID_DAY != 24 
            && WS_VALID_DAY != 25 
            && WS_VALID_DAY != 26 
            && WS_VALID_DAY != 27 
            && WS_VALID_DAY != 28 
            && WS_VALID_DAY != 29 
            && WS_VALID_DAY != 30 
            && WS_VALID_DAY != 31)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APPINTED_DAY, LNCFFSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CAL_FFLG = LNCFFSDT.CAL_FFLG;
        WS_DATE = LNCFFSDT.START_DT;
        WS_PERFORM_FLG = 'B';
        WS_PAYI_PERD = LNCFFSDT.PAYI_PERD;
        JIBS_tmp_str[0] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_DATE_DAY_TYPE = 0;
        else WS_DATE_DAY_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
        else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
        else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        if (WS_PAYI_PERD == '4'
            || (WS_PAYI_PERD == '5' 
                && (WS_DATE_DAY_TYPE == 1 
                || WS_DATE_DAY_TYPE == 4 
                || WS_DATE_DAY_TYPE == 7 
                || WS_DATE_DAY_TYPE == 10))
            || (WS_PAYI_PERD == '6' 
                && (WS_HALF_YEA_TYPE == 1 
                || WS_HALF_YEA_TYPE == 7))
            || WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 1) {
            WS_PAY_DTEM = K_FIRST_M;
        } else if ((WS_PAYI_PERD == '5' 
                && (WS_DATE_DAY_TYPE == 2 
                || WS_DATE_DAY_TYPE == 5 
                || WS_DATE_DAY_TYPE == 8 
                || WS_DATE_DAY_TYPE == 11))
            || (WS_PAYI_PERD == '6' 
                && (WS_HALF_YEA_TYPE == 2 
                || WS_HALF_YEA_TYPE == 8))
            || WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 2) {
            WS_PAY_DTEM = K_SECON_M;
        } else if ((WS_PAYI_PERD == '5' 
                && (WS_DATE_DAY_TYPE == 3 
                || WS_DATE_DAY_TYPE == 6 
                || WS_DATE_DAY_TYPE == 9 
                || WS_DATE_DAY_TYPE == 12))
            || (WS_PAYI_PERD == '6' 
                && (WS_HALF_YEA_TYPE == 3 
                || WS_HALF_YEA_TYPE == 9))
            || WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 3) {
            WS_PAY_DTEM = K_THIRD_M;
        } else if ((WS_PAYI_PERD == '6' 
                && (WS_HALF_YEA_TYPE == 4 
                || WS_HALF_YEA_TYPE == 10))
            || WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 4) {
            WS_PAY_DTEM = K_FORTH_M;
        } else if ((WS_PAYI_PERD == '6' 
                && (WS_HALF_YEA_TYPE == 5 
                || WS_HALF_YEA_TYPE == 11))
            || WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 5) {
            WS_PAY_DTEM = K_FIFTH_M;
        } else if ((WS_PAYI_PERD == '6' 
                && (WS_HALF_YEA_TYPE == 6 
                || WS_HALF_YEA_TYPE == 12))
            || WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 6) {
            WS_PAY_DTEM = K_SIXTH_M;
        } else if (WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 7) {
            WS_PAY_DTEM = K_SEVTH_M;
        } else if (WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 8) {
            WS_PAY_DTEM = K_EIGTH_M;
        } else if (WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 9) {
            WS_PAY_DTEM = K_NINTH_M;
        } else if (WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 10) {
            WS_PAY_DTEM = K_TENTH_M;
        } else if (WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 11) {
            WS_PAY_DTEM = K_ELEVT_M;
        } else if (WS_PAYI_PERD == '7' 
                && WS_ONE_YEA_TYPE == 12) {
            WS_PAY_DTEM = K_TWLTH_M;
        } else {
        }
        CEP.TRC(SCCGWA, WS_DATE);
        CEP.TRC(SCCGWA, LNCFFSDT.PAYI_PERD);
        CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
        CEP.TRC(SCCGWA, "FFSDT MONT");
        if (LNCFFSDT.PAY_DTEM == K_FIRST_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_DATE = SCCCLDT.DATE2;
                WS_YYYYMMDD1 = SCCCLDT.DATE2;
                IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD1+"", REDEFINES34);
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_DATE_DAY_TYPE = 0;
                else WS_DATE_DAY_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
                else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                CEP.TRC(SCCGWA, WS_YYYYMMDD1);
                CEP.TRC(SCCGWA, LNCFFSDT.CAL_FFLG);
                CEP.TRC(SCCGWA, WS_TEM_DAY);
                if ((WS_PAYI_PERD == '4' 
                    || (WS_PAYI_PERD == '5' 
                    && (WS_DATE_DAY_TYPE == 3 
                    || WS_DATE_DAY_TYPE == 6 
                    || WS_DATE_DAY_TYPE == 9 
                    || WS_DATE_DAY_TYPE == 12)) 
                    || (WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 6 
                    || WS_HALF_YEA_TYPE == 12)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 12))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
            WS_YYYYMMDD4 = LNCFFSDT.START_DT;
            IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD4+"", REDEFINES49);
            WS_YYYYMMDD5 = WS_DATE;
            IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD5+"", REDEFINES54);
            WS_T_MTHS = (short) (( REDEFINES54.WS_YYYY5 - REDEFINES49.WS_YYYY4 ) * 12 + ( REDEFINES54.WS_MM5 - REDEFINES49.WS_MM4 ));
            CEP.TRC(SCCGWA, "AAAAAAAAAAA");
            CEP.TRC(SCCGWA, WS_DATE);
            CEP.TRC(SCCGWA, LNCFFSDT.START_DT);
            CEP.TRC(SCCGWA, WS_T_MTHS);
            if (REDEFINES34.WS_DD1 < WS_TEM_DAY 
                && ((WS_PAYI_PERD == '5' 
                && WS_T_MTHS < 3) 
                || (WS_PAYI_PERD == '6' 
                && WS_T_MTHS < 6) 
                || (WS_PAYI_PERD == '7' 
                && WS_T_MTHS < 12)) 
                && LNCFFSDT.CAL_FFLG == '3' 
                && LNCFFSDT.PAY_DTYP == '2') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_DATE = SCCCLDT.DATE2;
            }
        } else if (LNCFFSDT.PAY_DTEM == K_SECON_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_DATE_DAY_TYPE = 0;
                else WS_DATE_DAY_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
                else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((((WS_PAYI_PERD == '5' 
                    && (WS_DATE_DAY_TYPE == 2 
                    || WS_DATE_DAY_TYPE == 5 
                    || WS_DATE_DAY_TYPE == 8 
                    || WS_DATE_DAY_TYPE == 11)) 
                    || (WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 2 
                    || WS_HALF_YEA_TYPE == 8)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 2)) 
                    && WS_CAL_FFLG == '2') 
                    || (((WS_PAYI_PERD == '5' 
                    && (WS_DATE_DAY_TYPE == 1 
                    || WS_DATE_DAY_TYPE == 4 
                    || WS_DATE_DAY_TYPE == 7 
                    || WS_DATE_DAY_TYPE == 10)) 
                    || (WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 1 
                    || WS_HALF_YEA_TYPE == 7)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1)) 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_THIRD_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_DATE_DAY_TYPE = 0;
                else WS_DATE_DAY_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
                else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((((WS_PAYI_PERD == '5' 
                    && (WS_DATE_DAY_TYPE == 3 
                    || WS_DATE_DAY_TYPE == 6 
                    || WS_DATE_DAY_TYPE == 9 
                    || WS_DATE_DAY_TYPE == 12)) 
                    || (WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 3 
                    || WS_HALF_YEA_TYPE == 9)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 3)) 
                    && WS_CAL_FFLG == '2') 
                    || (((WS_PAYI_PERD == '5' 
                    && (WS_DATE_DAY_TYPE == 1 
                    || WS_DATE_DAY_TYPE == 4 
                    || WS_DATE_DAY_TYPE == 7 
                    || WS_DATE_DAY_TYPE == 10)) 
                    || (WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 1 
                    || WS_HALF_YEA_TYPE == 7)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1)) 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_FORTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
                else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((((WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 4 
                    || WS_HALF_YEA_TYPE == 10)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 4)) 
                    && WS_CAL_FFLG == '2') 
                    || (((WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 1 
                    || WS_HALF_YEA_TYPE == 7)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1)) 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_FIFTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
                else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((((WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 5 
                    || WS_HALF_YEA_TYPE == 11)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 5)) 
                    && WS_CAL_FFLG == '2') 
                    || (((WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 1 
                    || WS_HALF_YEA_TYPE == 7)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1)) 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_SIXTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_HALF_YEA_TYPE = 0;
                else WS_HALF_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((((WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 6 
                    || WS_HALF_YEA_TYPE == 12)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 6)) 
                    && WS_CAL_FFLG == '2') 
                    || (((WS_PAYI_PERD == '6' 
                    && (WS_HALF_YEA_TYPE == 1 
                    || WS_HALF_YEA_TYPE == 7)) 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1)) 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_SEVTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 7 
                    && WS_CAL_FFLG == '2') 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_EIGTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 8 
                    && WS_CAL_FFLG == '2') 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_NINTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 9 
                    && WS_CAL_FFLG == '2') 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_TENTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 10 
                    && WS_CAL_FFLG == '2') 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_ELEVT_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 11 
                    && WS_CAL_FFLG == '2') 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else if (LNCFFSDT.PAY_DTEM == K_TWLTH_M) {
            while (WS_PERFORM_FLG != 'E') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DATE;
                SCCCLDT.MTHS = K_ONE_MONTH;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE = SCCCLDT.DATE2;
                JIBS_tmp_str[0] = "" + WS_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_ONE_YEA_TYPE = 0;
                else WS_ONE_YEA_TYPE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                CEP.TRC(SCCGWA, WS_DATE);
                if (((WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 12 
                    && WS_CAL_FFLG == '2') 
                    || (WS_PAYI_PERD == '7' 
                    && WS_ONE_YEA_TYPE == 1 
                    && WS_CAL_FFLG == '3'))) {
                    WS_PERFORM_FLG = 'E';
                }
            }
        } else {
        }
        CEP.TRC(SCCGWA, "END-P");
        CEP.TRC(SCCGWA, WS_DATE);
        WS_CAL_DATE = WS_DATE;
        WS_CAL_DATE1 = WS_DATE;
        WS_CAL_DAY = WS_TEM_DAY;
        R000_ADJ_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCFFSDT.CAL_FFLG);
        CEP.TRC(SCCGWA, WS_MTHS1);
        CEP.TRC(SCCGWA, WS_CAL_DATE1);
        CEP.TRC(SCCGWA, WS_CAL_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (LNCFFSDT.CAL_FFLG == '3') {
            WS_YYYYMMDD2 = LNCFFSDT.START_DT;
            IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD2+"", REDEFINES39);
            WS_YYYYMMDD1 = WS_CAL_DATE1;
            IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD1+"", REDEFINES34);
            WS_MTHS_TMP = (short) (( REDEFINES34.WS_YYYY1 - REDEFINES39.WS_YYYY2 ) * 12 + ( REDEFINES34.WS_MM1 - REDEFINES39.WS_MM2 ));
            if (LNCFFSDT.PAY_DTEM == 0) {
                LNCFFSDT.PAY_DTEM = 1;
            }
            CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
            CEP.TRC(SCCGWA, WS_MTHS1);
            CEP.TRC(SCCGWA, WS_MTHS_TMP);
            CEP.TRC(SCCGWA, REDEFINES34.WS_DD1);
            CEP.TRC(SCCGWA, WS_CAL_DAY);
            if (WS_MTHS_TMP < WS_MTHS1 
                && WS_CAL_DAY >= REDEFINES34.WS_DD1) {
                CEP.TRC(SCCGWA, "2323");
                CEP.TRC(SCCGWA, WS_CAL_DATE);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_CAL_DATE;
                SCCCLDT.MTHS = (short) (WS_MTHS1 - WS_MTHS_TMP + LNCFFSDT.PAY_DTEM - 1);
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_CAL_DATE2 = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                CEP.TRC(SCCGWA, WS_CAL_DATE2);
            }
            if ((WS_MTHS_TMP == WS_MTHS1 
                && WS_CAL_DAY < REDEFINES34.WS_DD1) 
                || (WS_MTHS_TMP < WS_MTHS1 
                && WS_CAL_DAY < REDEFINES34.WS_DD1)) {
                CEP.TRC(SCCGWA, "23231");
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_CAL_DATE;
                SCCCLDT.MTHS = (short) (WS_MTHS1 - WS_MTHS_TMP + LNCFFSDT.PAY_DTEM);
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_CAL_DATE2 = SCCCLDT.DATE2;
            }
            if (WS_MTHS_TMP == WS_MTHS1 
                && WS_CAL_DAY >= REDEFINES34.WS_DD1) {
                WS_CAL_DATE2 = WS_CAL_DATE;
            }
            if (WS_MTHS_TMP > WS_MTHS1) {
                WS_CAL_DATE2 = WS_CAL_DATE;
            }
        } else {
            CEP.TRC(SCCGWA, WS_PAY_DTEM);
            CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
            WS_CAL_DATE2 = WS_CAL_DATE;
        }
        CEP.TRC(SCCGWA, "23232");
        CEP.TRC(SCCGWA, WS_CAL_DATE2);
    }
    public void R000_ADJ_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_DAY);
        CEP.TRC(SCCGWA, WS_CAL_DATE);
        if (WS_CAL_DAY < 29) {
            CEP.TRC(SCCGWA, "LX1");
            JIBS_tmp_str[0] = "" + WS_CAL_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
            JIBS_tmp_str[1] = "" + WS_CAL_DAY;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
        } else if (WS_CAL_DAY >= 29) {
            CEP.TRC(SCCGWA, "LX2");
            JIBS_tmp_str[0] = "" + WS_CAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
            JIBS_tmp_str[0] = "" + WS_CAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("02")) {
                CEP.TRC(SCCGWA, "LX3");
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = WS_CAL_DATE;
                R000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                if (SCCCKDT.LEAP_YEAR == 1) {
                    JIBS_tmp_str[0] = "" + WS_CAL_DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
                    JIBS_tmp_str[1] = "" + 29;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
                } else {
                    JIBS_tmp_str[0] = "" + WS_CAL_DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
                    JIBS_tmp_str[1] = "" + 28;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
                }
            } else {
                CEP.TRC(SCCGWA, "LX4");
                if (WS_CAL_DAY <= 30) {
                    JIBS_tmp_str[0] = "" + WS_CAL_DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
                    JIBS_tmp_str[1] = "" + WS_CAL_DAY;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
                } else {
                    CEP.TRC(SCCGWA, "LX5");
                    JIBS_tmp_str[0] = "" + WS_CAL_DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("04") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("06") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("09") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11")) {
                        JIBS_tmp_str[0] = "" + WS_CAL_DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
                        JIBS_tmp_str[1] = "" + 30;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                        WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
                    } else {
                        JIBS_tmp_str[0] = "" + WS_CAL_DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
                        JIBS_tmp_str[1] = "" + 31;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                        WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
                    }
                }
            }
        } else {
        }
    }
    public void R000_CAL_OTHER_RCS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        if (LNCFFSDT.CAL_FSDT == 0 
            && LNCFFSDT.CAL_PERD != 0 
            && LNCFFSDT.CAL_PERU != ' ') {
            if (LNCFFSDT.CAL_PERU == K_MONTH) {
                SCCCLDT.MTHS = LNCFFSDT.CAL_PERD;
            } else {
                SCCCLDT.DAYS = LNCFFSDT.CAL_PERD;
            }
            SCCCLDT.DATE1 = LNCFFSDT.START_DT;
            R000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_CAL_DATE = SCCCLDT.DATE2;
            WS_CAL_DATE1 = SCCCLDT.DATE2;
            WS_CAL_DAY = LNCFFSDT.CAL_DAY;
            if (LNCFFSDT.CAL_PERU == K_MONTH) {
                R000_ADJ_DATE();
                if (pgmRtn) return;
            }
            WS_CAL_DATE2 = WS_CAL_DATE;
        }
        CEP.TRC(SCCGWA, WS_CAL_DATE2);
    }
    public void R000_CAL_OTHER() throws IOException,SQLException,Exception {
        WS_CAL_DATE = LNCFFSDT.START_DT;
        WS_CAL_DAY = LNCFFSDT.CAL_DAY;
        if (LNCFFSDT.CAL_PERU == K_MONTH) {
            R000_ADJ_DATE();
            if (pgmRtn) return;
        }
        WS_CAL_DATE2 = WS_CAL_DATE;
        CEP.TRC(SCCGWA, WS_CAL_DATE2);
        if (WS_CAL_DATE2 <= LNCFFSDT.START_DT 
            && LNCFFSDT.CAL_PERD == 1 
            && LNCFFSDT.CAL_PERU == K_MONTH) {
            IBS.init(SCCGWA, SCCCLDT);
            if (LNCFFSDT.CAL_FSDT == 0 
                && LNCFFSDT.CAL_PERD != 0 
                && LNCFFSDT.CAL_PERU != ' ') {
                if (LNCFFSDT.CAL_PERU == K_MONTH) {
                    CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
                    SCCCLDT.MTHS = 1;
                } else {
                    SCCCLDT.DAYS = LNCFFSDT.CAL_PERD;
                }
                SCCCLDT.DATE1 = WS_CAL_DATE2;
                R000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_CAL_DATE2 = SCCCLDT.DATE2;
            }
        }
        if (LNCFFSDT.CAL_PERD == 3 
            && LNCFFSDT.CAL_PERU == K_MONTH) {
            WS_APRD_DAY = LNCFFSDT.START_DT;
            IBS.CPY2CLS(SCCGWA, WS_APRD_DAY+"", REDEFINES68);
            if (REDEFINES68.WS_YM.WS_M == 3 
                || REDEFINES68.WS_YM.WS_M == 6 
                || REDEFINES68.WS_YM.WS_M == 9 
                || REDEFINES68.WS_YM.WS_M == 12) {
                if (REDEFINES68.WS_D < WS_CMSF_PAY_DAY) {
                    REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                    WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                    WS_CAL_DATE2 = WS_APRD_DAY;
                } else {
                    IBS.init(SCCGWA, SCCCLDT);
                    if (LNCFFSDT.CAL_FSDT == 0 
                        && LNCFFSDT.CAL_PERD != 0 
                        && LNCFFSDT.CAL_PERU != ' ') {
                        if (LNCFFSDT.CAL_PERU == K_MONTH) {
                            CEP.TRC(SCCGWA, LNCFFSDT.PAY_DTEM);
                            SCCCLDT.MTHS = 3;
                        } else {
                            SCCCLDT.DAYS = LNCFFSDT.CAL_PERD;
                        }
                        SCCCLDT.DATE1 = WS_CAL_DATE2;
                        R000_CALL_SCSSCLDT();
                        if (pgmRtn) return;
                        WS_CAL_DATE2 = SCCCLDT.DATE2;
                    }
                }
            } else {
                if (REDEFINES68.WS_YM.WS_M > 3 
                        && REDEFINES68.WS_YM.WS_M <= 6) {
                    WS_CMSF_MM = 6;
                } else if (REDEFINES68.WS_YM.WS_M > 6 
                        && REDEFINES68.WS_YM.WS_M <= 9) {
                    WS_CMSF_MM = 9;
                } else if (REDEFINES68.WS_YM.WS_M > 9 
                        && REDEFINES68.WS_YM.WS_M <= 12) {
                    WS_CMSF_MM = 12;
                } else if (REDEFINES68.WS_YM.WS_M <= 3) {
                    WS_CMSF_MM = 3;
                } else {
                }
                REDEFINES68.WS_YM.WS_M = WS_CMSF_MM;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                WS_CAL_DATE2 = WS_APRD_DAY;
            }
        }
        if (LNCFFSDT.CAL_PERD == 6 
            && LNCFFSDT.CAL_PERU == K_MONTH) {
            WS_APRD_DAY = LNCFFSDT.START_DT;
            IBS.CPY2CLS(SCCGWA, WS_APRD_DAY+"", REDEFINES68);
            if (REDEFINES68.WS_YM.WS_M == 6 
                || REDEFINES68.WS_YM.WS_M == 12) {
                if (REDEFINES68.WS_D < WS_CMSF_PAY_DAY) {
                    REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                    WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                    WS_CAL_DATE2 = WS_APRD_DAY;
                } else {
                    if (REDEFINES68.WS_YM.WS_M == 6) {
                        REDEFINES68.WS_YM.WS_M = 12;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                        WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                    if (REDEFINES68.WS_YM.WS_M == 12) {
                        REDEFINES68.WS_YM.WS_M = 6;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                        WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                        REDEFINES68.WS_YM.WS_Y += 1;
                    }
                    REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                    WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                    WS_CAL_DATE2 = WS_APRD_DAY;
                }
            } else {
                if (REDEFINES68.WS_YM.WS_M > 6 
                        && REDEFINES68.WS_YM.WS_M <= 12) {
                    WS_CMSF_MM = 12;
                } else if (REDEFINES68.WS_YM.WS_M <= 6) {
                    WS_CMSF_MM = 6;
                } else {
                }
                REDEFINES68.WS_YM.WS_M = WS_CMSF_MM;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                WS_CAL_DATE2 = WS_APRD_DAY;
            }
        }
        if (LNCFFSDT.CAL_PERD == 12 
            && LNCFFSDT.CAL_PERU == K_MONTH) {
            WS_APRD_DAY = LNCFFSDT.START_DT;
            IBS.CPY2CLS(SCCGWA, WS_APRD_DAY+"", REDEFINES68);
            if (REDEFINES68.WS_YM.WS_M == 12 
                && REDEFINES68.WS_D >= 21) {
                REDEFINES68.WS_YM.WS_M = 12;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                REDEFINES68.WS_YM.WS_Y += 1;
                WS_CAL_DATE2 = WS_APRD_DAY;
            } else {
                REDEFINES68.WS_YM.WS_M = 12;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                REDEFINES68.WS_D = WS_CMSF_PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES68);
                WS_APRD_DAY = Integer.parseInt(JIBS_tmp_str[0]);
                WS_CAL_DATE2 = WS_APRD_DAY;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_DATE2);
    }
    public void R000_GET_INT_FS_FL_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            LNRCONT.KEY.CONTRACT_NO = LNCFFSDT.CONT_NO;
            T000_READ_LNTCONT();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.FLT_DAY == 42) {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.KEY.CONTRACT_NO = LNCFFSDT.CONT_NO;
            LNTAGRE_RD = new DBParm();
            LNTAGRE_RD.TableName = "LNTAGRE";
            IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
            CEP.TRC(SCCGWA, LNRAGRE.DRAW_NO);
            LNTAGRE_RD = new DBParm();
            LNTAGRE_RD.TableName = "LNTAGRE";
            LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO";
            LNTAGRE_RD.fst = true;
            LNTAGRE_RD.order = "CONTRACT_NO";
            IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
            LNRCONT.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
            CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
            T000_READ_LNTCONT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCFFSDT.RATE_FLT_FLG);
        if (LNCFFSDT.RATE_FLT_FLG == '0') {
            LNCFFSDT.FST_FLT_DT = 0;
        } else if (LNCFFSDT.RATE_FLT_FLG == '2') {
            R000_DAILY_FSDT();
            if (pgmRtn) return;
        } else if (LNCFFSDT.RATE_FLT_FLG == '1') {
            R000_PERIOD_FSDT();
            if (pgmRtn) return;
        } else if (LNCFFSDT.RATE_FLT_FLG == '3') {
            R000_INTPERD_FSDT();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R000_DAILY_FSDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.FIRST_FLT_FLG);
        WS_GET_DATE = LNCFFSDT.ACTV_DT;
        WS_GET_PERIOD = 1;
        CEP.TRC(SCCGWA, WS_GET_DATE);
        CEP.TRC(SCCGWA, WS_GET_PERIOD);
        S000_CALL_SCSSCLDT2();
        if (pgmRtn) return;
        LNCFFSDT.FST_FLT_DT = SCCCLDT.DATE2;
    }
    public void R000_PERIOD_FSDT() throws IOException,SQLException,Exception {
        WS_FLT_DAY1 = LNCFFSDT.FLT_DAY;
        CEP.TRC(SCCGWA, WS_FLT_DAY1);
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_GAP_PERD);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_PERD);
        if (LNCFFSDT.FLT_PERD_UNIT == 'D') {
            if (LNCFFSDT.FLT_DAY == 41 
                || LNCFFSDT.FLT_DAY == 42) {
                WS_GET_DATE = LNRCONT.START_DATE;
            } else {
                WS_GET_DATE = LNCFFSDT.ACTV_DT;
            }
            CEP.TRC(SCCGWA, WS_GET_DATE);
            CEP.TRC(SCCGWA, WS_GET_UNIT);
            CEP.TRC(SCCGWA, WS_GET_PERIOD);
            WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
            WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
            while (WS_GET_DATE <= LNCFFSDT.ACTV_DT 
                || WS_GET_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                R000_GET_DT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "+R000-GET-DT1+");
            CEP.TRC(SCCGWA, WS_GET_DATE);
            CEP.TRC(SCCGWA, WS_CLDT_DATE2);
            LNCFFSDT.FST_FLT_DT = WS_GET_DATE;
            CEP.TRC(SCCGWA, LNCFFSDT.FST_FLT_DT);
        }
        if (LNCFFSDT.FLT_PERD_UNIT == 'M') {
            R000_GET_FSDT_M();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.FLT_PERD_UNIT == 'W') {
            R000_GET_FSDT_W();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.FLT_PERD_UNIT == 'B') {
            R000_GET_FSDT_B();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.FLT_PERD_UNIT == 'Q') {
            R000_GET_FSDT_Q();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.FLT_PERD_UNIT == 'H') {
            R000_GET_FSDT_H();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.FLT_PERD_UNIT == 'Y') {
            R000_GET_FSDT_Y();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_PERD);
    }
    public void R000_GET_FSDT_W() throws IOException,SQLException,Exception {
        LNCFFSDT.FLT_PERD_UNIT = 'D';
        if ("7".trim().length() == 0) LNCFFSDT.FLT_PERD = 0;
        else LNCFFSDT.FLT_PERD = Short.parseShort("7");
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            WS_GET_DATE = LNRCONT.START_DATE;
        } else {
            WS_GET_DATE = LNCFFSDT.ACTV_DT;
        }
        CEP.TRC(SCCGWA, WS_GET_DATE);
        CEP.TRC(SCCGWA, WS_GET_UNIT);
        CEP.TRC(SCCGWA, WS_GET_PERIOD);
        WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
        WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
        while (WS_GET_DATE <= LNCFFSDT.ACTV_DT 
            || WS_GET_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            R000_GET_DT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "+R000-GET-DT1+");
        CEP.TRC(SCCGWA, WS_GET_DATE);
        CEP.TRC(SCCGWA, WS_CLDT_DATE2);
        LNCFFSDT.FST_FLT_DT = WS_GET_DATE;
        CEP.TRC(SCCGWA, LNCFFSDT.FST_FLT_DT);
    }
    public void R000_GET_FSDT_B() throws IOException,SQLException,Exception {
        LNCFFSDT.FLT_PERD_UNIT = 'D';
        if ("7".trim().length() == 0) LNCFFSDT.FLT_PERD = 0;
        else LNCFFSDT.FLT_PERD = Short.parseShort("7");
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            WS_GET_DATE = LNRCONT.START_DATE;
        } else {
            WS_GET_DATE = LNCFFSDT.ACTV_DT;
        }
        CEP.TRC(SCCGWA, WS_GET_DATE);
        WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
        WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
        WS_TIME_1 = 1;
        CEP.TRC(SCCGWA, WS_GET_UNIT);
        CEP.TRC(SCCGWA, WS_GET_PERIOD);
        while (WS_GET_DATE <= LNCFFSDT.ACTV_DT 
            || WS_GET_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            || WS_TIME_1 != WS_TIME_2) {
            WS_7_TIME += 1;
            R000_GET_DT();
            if (pgmRtn) return;
            if (LNCFFSDT.FLT_GAP_PERD == 1) {
                WS_TIME_1 = ( WS_7_TIME + 1 ) / 2;
            } else {
                WS_TIME_1 = WS_7_TIME / 2;
            }
            WS_TIME_2 = (short) WS_TIME_1;
            CEP.TRC(SCCGWA, WS_TIME_1);
            CEP.TRC(SCCGWA, WS_TIME_2);
        }
        CEP.TRC(SCCGWA, "+R000-GET-DT1+");
        CEP.TRC(SCCGWA, WS_GET_DATE);
        CEP.TRC(SCCGWA, WS_CLDT_DATE2);
        LNCFFSDT.FST_FLT_DT = WS_GET_DATE;
        CEP.TRC(SCCGWA, LNCFFSDT.FST_FLT_DT);
    }
    public void R000_GET_FSDT_M() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_DAY);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_PERD);
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_GAP_PERD);
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        CEP.TRC(SCCGWA, LNCFFSDT.ACTV_DT);
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            WS_GET_DATE = LNRCONT.START_DATE;
            JIBS_tmp_str[0] = "" + WS_GET_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FLT_DAY1 = 0;
            else WS_FLT_DAY1 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        } else {
            WS_GET_DATE = LNCFFSDT.ACTV_DT;
            CEP.TRC(SCCGWA, WS_GET_DATE);
        }
        CEP.TRC(SCCGWA, WS_GET_DATE);
        CEP.TRC(SCCGWA, LNCFFSDT.ACTV_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_GET_DATE);
        WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
        WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
        while (WS_GET_DATE <= LNCFFSDT.ACTV_DT 
            || WS_GET_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            R000_GET_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, LNCFFSDT.ACTV_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        }
        CEP.TRC(SCCGWA, "+R000-GET-DT+");
        CEP.TRC(SCCGWA, WS_GET_DATE);
        WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
        WS_GET_PERIOD = (short) (LNCFFSDT.FLT_GAP_PERD - 1);
        IBS.init(SCCGWA, SCCCLDT);
        if (WS_GET_PERIOD > 0) {
            R000_GET_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        }
        WS_CLDT_DATE2 = WS_GET_DATE;
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            JIBS_tmp_str[0] = "" + WS_CLDT_DATE2;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNCFFSDT.FLT_DAY = 0;
            else LNCFFSDT.FLT_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_DAY);
        BS07_MOD_NXT_PYDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CLDT_DATE2);
        LNCFFSDT.FST_FLT_DT = WS_CLDT_DATE2;
    }
    public void R000_GET_FSDT_Q() throws IOException,SQLException,Exception {
        LNCFFSDT.FLT_PERD_UNIT = 'M';
        if ("3".trim().length() == 0) LNCFFSDT.FLT_PERD = 0;
        else LNCFFSDT.FLT_PERD = Short.parseShort("3");
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_DAY);
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            WS_GET_DATE = LNRCONT.START_DATE;
            JIBS_tmp_str[0] = "" + WS_GET_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FLT_DAY1 = 0;
            else WS_FLT_DAY1 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        } else {
            WS_GET_DATE = LNCFFSDT.ACTV_DT;
            CEP.TRC(SCCGWA, WS_GET_DATE);
        }
        CEP.TRC(SCCGWA, WS_FLT_DAY1);
        CEP.TRC(SCCGWA, WS_GET_DATE);
        S000_GET_WS_MM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_GET_DATE);
        WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
        WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
        CEP.TRC(SCCGWA, REDEFINES29.WS_MM);
        JIBS_tmp_str[0] = "" + WS_GET_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
        JIBS_tmp_str[1] = "" + REDEFINES29.WS_MM;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 2 - 1);
        WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
        CEP.TRC(SCCGWA, WS_GET_DATE);
        JIBS_tmp_str[0] = "" + WS_GET_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
        JIBS_tmp_str[1] = "" + WS_FLT_DAY1;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
        WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
        CEP.TRC(SCCGWA, WS_GET_DATE);
        CEP.TRC(SCCGWA, WS_FLT_DAY1);
        CEP.TRC(SCCGWA, WS_FLT_DAY2);
        WS_FLG_DT = 'Y';
        CEP.TRC(SCCGWA, WS_FLG_DT);
        while (WS_GET_DATE <= LNCFFSDT.ACTV_DT 
            || WS_GET_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, WS_GET_DATE);
            CEP.TRC(SCCGWA, WS_FLG_DT);
            if (WS_FLG_DT == 'Y') {
                JIBS_tmp_str[0] = "" + WS_GET_DATE;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + "28" + JIBS_NumStr.substring(7 + 2 - 1);
                WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
                WS_FLG_DT = 'N';
            } else {
                JIBS_tmp_str[0] = "" + WS_GET_DATE;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
                JIBS_tmp_str[1] = "" + WS_FLT_DAY2;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
            }
            CEP.TRC(SCCGWA, WS_GET_DATE);
            R000_GET_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_GET_DATE);
            JIBS_tmp_str[0] = "" + WS_GET_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
            JIBS_tmp_str[1] = "" + WS_FLT_DAY1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
            CEP.TRC(SCCGWA, WS_GET_DATE);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, LNCFFSDT.ACTV_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        }
        CEP.TRC(SCCGWA, WS_FLT_DAY2);
        R000_FIRST_FLT_FLG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCFFSDT.FIRST_FLT_FLG);
        CEP.TRC(SCCGWA, REDEFINES44.WS_MM3);
        JIBS_tmp_str[0] = "" + WS_GET_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, WS_FLT_DAY1);
        CEP.TRC(SCCGWA, WS_FLT_DAY2);
        JIBS_tmp_str[0] = "" + WS_GET_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_GET_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (LNCFFSDT.FIRST_FLT_FLG == '1' 
            && REDEFINES44.WS_MM3 >= Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1)) 
            && REDEFINES44.WS_YYYY3 >= Short.parseShort(JIBS_tmp_str[1].substring(0, 4))) {
            WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
            WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
            CEP.TRC(SCCGWA, WS_GET_DATE);
            CEP.TRC(SCCGWA, WS_FLG_DT);
            if (WS_FLG_DT == 'Y') {
                JIBS_tmp_str[0] = "" + WS_GET_DATE;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + "28" + JIBS_NumStr.substring(7 + 2 - 1);
                WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
                WS_FLG_DT = 'N';
            } else {
                JIBS_tmp_str[0] = "" + WS_GET_DATE;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
                JIBS_tmp_str[1] = "" + WS_FLT_DAY2;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
            }
            CEP.TRC(SCCGWA, WS_GET_DATE);
            R000_GET_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_GET_DATE);
            JIBS_tmp_str[0] = "" + WS_GET_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
            JIBS_tmp_str[1] = "" + WS_FLT_DAY1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
            CEP.TRC(SCCGWA, WS_GET_DATE);
        }
        CEP.TRC(SCCGWA, WS_GET_DATE);
        WS_CLDT_DATE2 = WS_GET_DATE;
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_DAY);
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            JIBS_tmp_str[0] = "" + WS_CLDT_DATE2;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNCFFSDT.FLT_DAY = 0;
            else LNCFFSDT.FLT_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        BS07_MOD_NXT_PYDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CLDT_DATE2);
        LNCFFSDT.FST_FLT_DT = WS_CLDT_DATE2;
    }
    public void R000_GET_FSDT_H() throws IOException,SQLException,Exception {
        LNCFFSDT.FLT_PERD_UNIT = 'M';
        if ("6".trim().length() == 0) LNCFFSDT.FLT_PERD = 0;
        else LNCFFSDT.FLT_PERD = Short.parseShort("6");
        R000_GET_FSDT_M();
        if (pgmRtn) return;
    }
    public void R000_GET_FSDT_Y() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_DAY);
        if (LNCFFSDT.FLT_DAY == 41 
            || LNCFFSDT.FLT_DAY == 42) {
            WS_GET_DATE = LNRCONT.START_DATE;
            JIBS_tmp_str[0] = "" + WS_GET_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FLT_DAY1 = 0;
            else WS_FLT_DAY1 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNCFFSDT.FLT_DAY = 0;
            else LNCFFSDT.FLT_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        } else {
            WS_GET_DATE = LNCFFSDT.ACTV_DT;
            CEP.TRC(SCCGWA, WS_GET_DATE);
            JIBS_tmp_str[0] = "" + WS_GET_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
            JIBS_tmp_str[1] = "" + LNCFFSDT.FLT_DAY;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
        }
        JIBS_tmp_str[0] = "" + WS_GET_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_GET_DATE;
        JIBS_tmp_str[1] = "" + LNCFFSDT.FLT_GAP_PERD;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 2 - 1);
        WS_GET_DATE = Integer.parseInt(JIBS_NumStr);
        WS_CLDT_DATE2 = WS_GET_DATE;
        CEP.TRC(SCCGWA, WS_GET_DATE);
        BS07_MOD_NXT_PYDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_GET_DATE);
        WS_GET_DATE = WS_CLDT_DATE2;
        if (WS_GET_DATE < SCCGWA.COMM_AREA.AC_DATE 
            || LNCFFSDT.FIRST_FLT_FLG == '1') {
            WS_GET_UNIT = 'M';
            WS_GET_PERIOD = 12;
            R000_GET_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "FOR UAT TEST");
            CEP.TRC(SCCGWA, WS_GET_DATE);
        }
        LNCFFSDT.FST_FLT_DT = WS_GET_DATE;
    }
    public void R000_INTPERD_FSDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INTPERD");
        T02_READ_LNTICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRICTL.IC_CAL_VAL_DT);
        WS_GET_DATE = LNRICTL.IC_CAL_DUE_DT;
        if (WS_GET_DATE < LNCFFSDT.ACTV_DT) {
            IBS.init(SCCGWA, LNCAPRDM);
            LNCAPRDM.FUNC = '3';
            LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCFFSDT.CONT_NO;
            S000_CALL_LNZAPRDM();
            if (pgmRtn) return;
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'Y') {
                WS_GET_UNIT = 'M';
                WS_GET_PERIOD = (short) (LNCAPRDM.REC_DATA.CAL_PERD * 12);
            } else if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'H') {
                WS_GET_UNIT = 'M';
                WS_GET_PERIOD = (short) (LNCAPRDM.REC_DATA.CAL_PERD * 6);
            } else if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'Q') {
                WS_GET_UNIT = 'M';
                WS_GET_PERIOD = (short) (LNCAPRDM.REC_DATA.CAL_PERD * 3);
            } else if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                WS_GET_UNIT = 'M';
                WS_GET_PERIOD = LNCAPRDM.REC_DATA.CAL_PERD;
            } else if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
                WS_GET_UNIT = 'D';
                WS_GET_PERIOD = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            while (WS_GET_DATE <= LNCFFSDT.ACTV_DT 
                || WS_GET_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                R000_GET_DT();
                if (pgmRtn) return;
            }
        }
        LNCFFSDT.FST_FLT_DT = WS_GET_DATE;
    }
    public void R000_GET_DT() throws IOException,SQLException,Exception {
        S000_CALL_SCSSCLDT2();
        if (pgmRtn) return;
        WS_GET_DATE = SCCCLDT.DATE2;
        JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FLT_DAY2 = 0;
        else WS_FLT_DAY2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        WS_GET_UNIT = LNCFFSDT.FLT_PERD_UNIT;
        WS_GET_PERIOD = LNCFFSDT.FLT_PERD;
    }
    public void S000_GET_WS_MM() throws IOException,SQLException,Exception {
        WS_YYYYMMDD = WS_GET_DATE;
        IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD+"", REDEFINES29);
        if (LNCFFSDT.FLT_PERD == 12) {
            REDEFINES29.WS_MM = LNCFFSDT.FLT_GAP_PERD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (LNCFFSDT.FLT_PERD == 6) {
            if (REDEFINES29.WS_MM < 7) {
                REDEFINES29.WS_MM = LNCFFSDT.FLT_GAP_PERD;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            } else {
                REDEFINES29.WS_MM = (short) (LNCFFSDT.FLT_GAP_PERD + 6);
            }
        }
        if (LNCFFSDT.FLT_PERD == 3) {
            if (REDEFINES29.WS_MM <= 3) {
                REDEFINES29.WS_MM = LNCFFSDT.FLT_GAP_PERD;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
            if (REDEFINES29.WS_MM >= 4 
                && REDEFINES29.WS_MM <= 6) {
                REDEFINES29.WS_MM = (short) (LNCFFSDT.FLT_GAP_PERD + 3);
            }
            if (REDEFINES29.WS_MM >= 7 
                && REDEFINES29.WS_MM <= 9) {
                REDEFINES29.WS_MM = (short) (LNCFFSDT.FLT_GAP_PERD + 6);
            }
            if (REDEFINES29.WS_MM >= 10 
                && REDEFINES29.WS_MM <= 12) {
                REDEFINES29.WS_MM = (short) (LNCFFSDT.FLT_GAP_PERD + 9);
            }
        }
    }
    public void R000_FIRST_FLT_FLG() throws IOException,SQLException,Exception {
        WS_YYYYMMDD3 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD3+"", REDEFINES44);
        if (REDEFINES44.WS_MM3 <= 3) {
            REDEFINES44.WS_MM3 = 3;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES44);
            WS_YYYYMMDD3 = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (REDEFINES44.WS_MM3 >= 4 
            && REDEFINES44.WS_MM3 <= 6) {
            REDEFINES44.WS_MM3 = 6;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES44);
            WS_YYYYMMDD3 = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (REDEFINES44.WS_MM3 >= 7 
            && REDEFINES44.WS_MM3 <= 9) {
            REDEFINES44.WS_MM3 = 9;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES44);
            WS_YYYYMMDD3 = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (REDEFINES44.WS_MM3 >= 10) {
            REDEFINES44.WS_MM3 = 12;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES44);
            WS_YYYYMMDD3 = Integer.parseInt(JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_GET_DATE;
        if (WS_GET_UNIT == 'M') {
            SCCCLDT.MTHS = WS_GET_PERIOD;
        } else {
            SCCCLDT.DAYS = WS_GET_PERIOD;
        }
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCFFSDT.RC.RC_APP = "SC";
            LNCFFSDT.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT1() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, SCCCLDT.RC+"", LNCFFSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_FLT_DAY2 = 0;
        else WS_FLT_DAY2 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
    }
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LXXXX");
        SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
        SCSSCKDT3.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCFFSDT.RC.RC_APP = "SC";
            LNCFFSDT.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT4 = new SCSSCLDT();
        SCSSCLDT4.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, "SCSSCLDT START");
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "SCSSCLDT ERROR");
            LNCFFSDT.RC.RC_APP = "SC";
            LNCFFSDT.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTCONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.FATHER_CONTRACT = WS_FATHER_CONTRACT;
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT";
        LNTCONT_RD.fst = true;
        LNTCONT_RD.order = "CONTRACT_NO";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.eqWhere = "CONTRACT_NO";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T02_READ_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCFFSDT.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.eqWhere = "CONTRACT_NO";
        LNTICTL_RD.where = "SUB_CTA_NO >= :LNRICTL.KEY.SUB_CTA_NO";
        LNTICTL_RD.fst = true;
        LNTICTL_RD.order = "SUB_CTA_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCFFSDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void BS07_MOD_NXT_PYDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFFSDT.FLT_DAY);
        if (LNCFFSDT.FLT_DAY > 31 
            || LNCFFSDT.FLT_DAY < 1) {
            LNCFFSDT.FLT_DAY = 31;
        }
        WS_YYYYMMDD = WS_CLDT_DATE2;
        IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD+"", REDEFINES29);
        if (LNCFFSDT.FLT_DAY < 29) {
            if (LNCFFSDT.FLT_DAY > 0) {
                REDEFINES29.WS_DD = LNCFFSDT.FLT_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        } else {
            WS_YYYY_1 = REDEFINES29.WS_YYYY / 4;
            WS_YYYY_2 = (short) WS_YYYY_1;
            if (LNCFFSDT.FLT_DAY == 29) {
                if (REDEFINES29.WS_MM == 2) {
                    if (WS_YYYY_1 == WS_YYYY_2) {
                        REDEFINES29.WS_DD = 29;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        if (LNCFFSDT.CHN_TYP == '2') {
                            REDEFINES29.WS_MM = 3;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                            REDEFINES29.WS_DD = 1;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            REDEFINES29.WS_DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    REDEFINES29.WS_DD = LNCFFSDT.FLT_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                    WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (LNCFFSDT.FLT_DAY == 30) {
                if (REDEFINES29.WS_MM == 2) {
                    if (LNCFFSDT.CHN_TYP == '2') {
                        REDEFINES29.WS_MM = 3;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        REDEFINES29.WS_DD = 1;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        if (WS_YYYY_1 == WS_YYYY_2) {
                            REDEFINES29.WS_DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            REDEFINES29.WS_DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    REDEFINES29.WS_DD = 30;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                    WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (LNCFFSDT.FLT_DAY == 31) {
                CEP.TRC(SCCGWA, "T31");
                if (REDEFINES29.WS_MM == 2) {
                    if (LNCFFSDT.CHN_TYP == '2') {
                        REDEFINES29.WS_MM = 3;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        REDEFINES29.WS_DD = 1;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        if (WS_YYYY_1 == WS_YYYY_2) {
                            REDEFINES29.WS_DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            REDEFINES29.WS_DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    if (REDEFINES29.WS_MM == 4 
                        || REDEFINES29.WS_MM == 6 
                        || REDEFINES29.WS_MM == 9 
                        || REDEFINES29.WS_MM == 11) {
                        CEP.TRC(SCCGWA, "46911");
                        CEP.TRC(SCCGWA, LNCFFSDT.CHN_TYP);
                        if (LNCFFSDT.CHN_TYP == '2') {
                            REDEFINES29.WS_MM += 1;
                            REDEFINES29.WS_DD = 1;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            REDEFINES29.WS_DD = 30;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                            WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    } else {
                        REDEFINES29.WS_DD = 31;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
            }
        }
        WS_CLDT_DATE2 = WS_YYYYMMDD;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCFFSDT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCFFSDT=");
            CEP.TRC(SCCGWA, LNCFFSDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
