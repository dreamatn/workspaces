package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCALT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    char K_NEXT_TERM = '2';
    LNZSCALT_WS_ERR_MSG WS_ERR_MSG = new LNZSCALT_WS_ERR_MSG();
    int WS_I = 0;
    int WS_II = 0;
    short WS_PHS = 0;
    short WS_TERM = 0;
    short WS_MAX_PHS = 0;
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    char WS_CAL_PERU = ' ';
    int WS_CLDT_CAL = 0;
    short WS_CAL_DAY = 0;
    int WS_CAL_DATE = 0;
    int WS_WEEK_LAST_DAY = 0;
    int WS_WEEK_LAST_DAT = 0;
    short WS_WEEK_NO = 0;
    int WS_YYYYMMDD = 0;
    LNZSCALT_REDEFINES19 REDEFINES19 = new LNZSCALT_REDEFINES19();
    double WS_YYYY_1 = 0;
    short WS_YYYY_2 = 0;
    char WS_PHS_TERM_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    LNCSCALT LNCSCALT;
    public void MP(SCCGWA SCCGWA, LNCSCALT LNCSCALT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCALT = LNCSCALT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCALT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSCALT.RC.RC_APP = "LN";
        LNCSCALT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.PHS_FLG);
        if (LNCSCALT.INPUT.PHS_FLG != 'Y') {
            B200_GET_TOT_TERM();
            if (pgmRtn) return;
        } else {
            B300_GET_TERM_NUM();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.VAL_DT);
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.DUE_DT);
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_PERD);
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_FST_DT);
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_PAY_DAY);
        if (LNCSCALT.INPUT.VAL_DT == 0 
            || LNCSCALT.INPUT.DUE_DT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSCALT.INPUT.CAL_FST_DT == 0) {
            IBS.init(SCCGWA, SCCCLDT);
            if (LNCSCALT.INPUT.CAL_PERD_UNIT == 'M') {
                SCCCLDT.MTHS = (short) (LNCSCALT.INPUT.CAL_PERD);
            } else {
                SCCCLDT.DAYS = LNCSCALT.INPUT.CAL_PERD;
            }
            SCCCLDT.DATE1 = LNCSCALT.INPUT.VAL_DT;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "YE876");
            WS_CAL_DATE = SCCCLDT.DATE2;
            if (LNCSCALT.INPUT.CAL_PAY_DAY != 0) {
                R000_ADJ_DATE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_CAL_DATE);
            LNCSCALT.INPUT.CAL_FST_DT = WS_CAL_DATE;
        }
        if (LNCSCALT.INPUT.CAL_PAY_DAY == 0) {
            if (LNCSCALT.INPUT.CAL_FST_DT != 0) {
                JIBS_tmp_str[0] = "" + LNCSCALT.INPUT.CAL_FST_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNCSCALT.INPUT.CAL_PAY_DAY = 0;
                else LNCSCALT.INPUT.CAL_PAY_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
            }
        }
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.PROD_MOD);
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.REPY_MTH);
        if ((LNCSCALT.INPUT.PROD_MOD == 'C' 
            && LNCSCALT.INPUT.REPY_MTH == '2') 
            || (LNCSCALT.INPUT.PROD_MOD == 'C' 
            && LNCSCALT.INPUT.REPY_MTH == '5')) {
            LNCSCALT.INPUT.CAL_FST_FLG = '3';
        }
    }
    public void B200_GET_TOT_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        WS_I = 0;
        WS_STR_DT = 0;
        WS_END_DT = 0;
        WS_STR_DT = LNCSCALT.INPUT.CAL_FST_DT;
        WS_END_DT = LNCSCALT.INPUT.DUE_DT;
        if (LNCSCALT.INPUT.CAL_PERD_UNIT == 'M') {
            SCCCLDT.MTHS = (short) (LNCSCALT.INPUT.CAL_PERD);
        } else {
            SCCCLDT.DAYS = LNCSCALT.INPUT.CAL_PERD;
        }
        WS_CAL_PERU = LNCSCALT.INPUT.CAL_PERD_UNIT;
        SCCCLDT.DATE1 = WS_STR_DT;
        WS_I += 1;
        while (SCCCLDT.DATE1 < WS_END_DT) {
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            if (LNCSCALT.INPUT.CAL_PERD_UNIT == 'M') {
                BS07_MOD_NXT_PYDAT();
                if (pgmRtn) return;
            }
            if (SCCCLDT.DATE1 <= WS_END_DT 
                && SCCCLDT.DATE2 > WS_STR_DT) {
                WS_I += 1;
            }
            if (SCCCLDT.DATE2 > WS_END_DT) {
                WS_CLDT_CAL = SCCCLDT.DATE1;
            } else {
                WS_CLDT_CAL = SCCCLDT.DATE2;
            }
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, WS_CLDT_CAL);
            SCCCLDT.DATE1 = SCCCLDT.DATE2;
            SCCCLDT.DATE2 = 0;
        }
        LNCSCALT.OUTPUT.INST_TERM = WS_I;
    }
    public void B300_GET_TERM_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        WS_I = 0;
        WS_STR_DT = 0;
        WS_END_DT = 0;
        WS_STR_DT = LNCSCALT.INPUT.CAL_FST_DT;
        WS_END_DT = LNCSCALT.INPUT.DUE_DT;
        SCCCLDT.DATE1 = WS_STR_DT;
        WS_I += 1;
        WS_PHS += 1;
        WS_TERM += 2;
        WS_MAX_PHS = LNCSCALT.INPUT.PHS_NUM;
        CEP.TRC(SCCGWA, WS_MAX_PHS);
        while (WS_PHS <= WS_MAX_PHS 
            && SCCCLDT.DATE1 < WS_END_DT) {
            if (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU == 'M') {
                SCCCLDT.MTHS = (short) (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERD);
                SCCCLDT.DAYS = 0;
            }
            if (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU == 'Y') {
                SCCCLDT.MTHS = (short) (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERD * 12);
                SCCCLDT.DAYS = 0;
            }
            if (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU == 'D') {
                SCCCLDT.DAYS = LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERD;
                SCCCLDT.MTHS = 0;
            }
            if (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU == 'W') {
                SCCCLDT.DAYS = LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERD * 7;
                SCCCLDT.MTHS = 0;
            }
            CEP.TRC(SCCGWA, "@@@@@@@@@@@@@@@@@");
            CEP.TRC(SCCGWA, WS_PHS);
            CEP.TRC(SCCGWA, WS_END_DT);
            CEP.TRC(SCCGWA, LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU);
            WS_PHS_TERM_FLG = 'Y';
            if (WS_PHS == WS_MAX_PHS 
                && SCCCLDT.DATE1 >= WS_END_DT) {
                CEP.TRC(SCCGWA, WS_PHS);
                CEP.TRC(SCCGWA, WS_MAX_PHS);
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                CEP.TRC(SCCGWA, WS_END_DT);
                WS_PHS_TERM_FLG = 'N';
            }
            if (WS_PHS != WS_MAX_PHS 
                && WS_TERM > LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_TOT_TERM) {
                WS_PHS_TERM_FLG = 'N';
            }
            while (WS_PHS_TERM_FLG != 'N') {
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "****************");
                CEP.TRC(SCCGWA, WS_PHS);
                CEP.TRC(SCCGWA, WS_TERM);
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_TOT_TERM);
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU);
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                if (LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU == 'M' 
                    && SCCCLDT.DATE2 < WS_END_DT) {
                    BS07_MOD_NXT_PYDAT();
                    if (pgmRtn) return;
                }
                if (SCCCLDT.DATE1 <= WS_END_DT 
                    && SCCCLDT.DATE2 > WS_STR_DT) {
                    WS_I += 1;
                }
                CEP.TRC(SCCGWA, WS_I);
                if (SCCCLDT.DATE2 > WS_END_DT) {
                    WS_CLDT_CAL = SCCCLDT.DATE1;
                } else {
                    WS_CLDT_CAL = SCCCLDT.DATE2;
                }
                SCCCLDT.DATE1 = SCCCLDT.DATE2;
                SCCCLDT.DATE2 = 0;
                WS_TERM += 1;
                if (WS_PHS == WS_MAX_PHS 
                    && SCCCLDT.DATE1 >= WS_END_DT) {
                    CEP.TRC(SCCGWA, WS_PHS);
                    CEP.TRC(SCCGWA, WS_MAX_PHS);
                    CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                    CEP.TRC(SCCGWA, WS_END_DT);
                    WS_PHS_TERM_FLG = 'N';
                }
                if (WS_PHS != WS_MAX_PHS 
                    && WS_TERM > LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_TOT_TERM) {
                    WS_PHS_TERM_FLG = 'N';
                }
            }
            WS_CAL_PERU = LNCSCALT.INPUT.PHS_DATA[WS_PHS-1].PHS_PERU;
            WS_TERM = 0;
            WS_TERM += 1;
            WS_PHS += 1;
            CEP.TRC(SCCGWA, "@@@@@@@END-PHS@@@@@@");
        }
        CEP.TRC(SCCGWA, WS_I);
        LNCSCALT.OUTPUT.INST_TERM = WS_I;
        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
        CEP.TRC(SCCGWA, WS_CLDT_CAL);
        CEP.TRC(SCCGWA, WS_END_DT);
    }
    public void B400_GET_INST_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
        CEP.TRC(SCCGWA, WS_II);
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_CLDT_CAL);
        if ((LNCSCALT.INPUT.CAL_FST_FLG == K_NEXT_TERM 
            || LNCSCALT.INPUT.CAL_FST_FLG == ' ') 
            && SCCCLDT.DATE1 != WS_END_DT) {
            if (WS_CAL_PERU == 'D') {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = WS_CLDT_CAL;
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCKDT.WEEK_NO);
                if (SCCCKDT.WEEK_NO == 7) {
                    WS_WEEK_LAST_DAT = WS_CLDT_CAL;
                } else {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DAYS = 7 - SCCCKDT.WEEK_NO;
                    SCCCLDT.DATE1 = WS_CLDT_CAL;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                    WS_WEEK_LAST_DAT = SCCCLDT.DATE2;
                }
                if (WS_END_DT <= WS_WEEK_LAST_DAT) {
                    LNCSCALT.OUTPUT.INST_TERM = LNCSCALT.OUTPUT.INST_TERM - 1;
                }
            } else {
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.VAL_DT);
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.DUE_DT);
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_FST_DT);
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_PERD_UNIT);
                CEP.TRC(SCCGWA, LNCSCALT.INPUT.CAL_PERD);
                CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
                JIBS_tmp_str[0] = "" + WS_CLDT_CAL;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_END_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[0] = "" + WS_CLDT_CAL;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + WS_END_DT;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_tmp_str[2] = "" + WS_CLDT_CAL;
                JIBS_tmp_int = JIBS_tmp_str[2].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[2] = "0"+JIBS_tmp_str[2];
                JIBS_tmp_str[3] = "" + WS_END_DT;
                JIBS_tmp_int = JIBS_tmp_str[3].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[3] = "0"+JIBS_tmp_str[3];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase(JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1)) 
                    && JIBS_tmp_str[2].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[3].substring(0, 4))) {
                    LNCSCALT.OUTPUT.INST_TERM = LNCSCALT.OUTPUT.INST_TERM - 1;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
    }
    public void BS07_MOD_NXT_PYDAT() throws IOException,SQLException,Exception {
        WS_YYYYMMDD = SCCCLDT.DATE2;
        IBS.CPY2CLS(SCCGWA, WS_YYYYMMDD+"", REDEFINES19);
        if (LNCSCALT.INPUT.CAL_PAY_DAY < 29) {
            if (LNCSCALT.INPUT.CAL_PAY_DAY > 0) {
                REDEFINES19.WS_DD = LNCSCALT.INPUT.CAL_PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        } else {
            WS_YYYY_1 = REDEFINES19.WS_YYYY / 4;
            WS_YYYY_2 = (short) WS_YYYY_1;
            if (LNCSCALT.INPUT.CAL_PAY_DAY == 29 
                || LNCSCALT.INPUT.CAL_PAY_DAY == 30) {
                if (REDEFINES19.WS_MM == 2) {
                    if (WS_YYYY_1 == WS_YYYY_2) {
                        REDEFINES19.WS_DD = 29;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        REDEFINES19.WS_DD = 28;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                } else {
                    REDEFINES19.WS_DD = LNCSCALT.INPUT.CAL_PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                    WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (LNCSCALT.INPUT.CAL_PAY_DAY == 31) {
                if (REDEFINES19.WS_MM == 2) {
                    if (WS_YYYY_1 == WS_YYYY_2) {
                        REDEFINES19.WS_DD = 29;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        REDEFINES19.WS_DD = 28;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                } else {
                    if (REDEFINES19.WS_MM == 4 
                        || REDEFINES19.WS_MM == 6 
                        || REDEFINES19.WS_MM == 9 
                        || REDEFINES19.WS_MM == 11) {
                        REDEFINES19.WS_DD = 30;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        REDEFINES19.WS_DD = 31;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES19);
                        WS_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
            }
        }
        SCCCLDT.DATE2 = WS_YYYYMMDD;
    }
    public void R000_ADJ_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_DATE);
        WS_CAL_DAY = LNCSCALT.INPUT.CAL_PAY_DAY;
        if (WS_CAL_DAY <= 28) {
            JIBS_tmp_str[0] = "" + WS_CAL_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_CAL_DATE;
            JIBS_tmp_str[1] = "" + WS_CAL_DAY;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            WS_CAL_DATE = Integer.parseInt(JIBS_NumStr);
        } else if (WS_CAL_DAY > 28) {
            JIBS_tmp_str[0] = "" + WS_CAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("02")) {
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
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LXXXX");
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCSCALT.RC.RC_APP = "SC";
            LNCSCALT.RC.RC_CODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, "YE11111");
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "YE99999");
            LNCSCALT.RC.RC_APP = "SC";
            LNCSCALT.RC.RC_CODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "YE123654");
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
        SCSSCKDT3.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.WEEK_NO);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            LNCSCALT.RC.RC_APP = "SC";
            LNCSCALT.RC.RC_CODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
        if (LNCSCALT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCSCALT=");
            CEP.TRC(SCCGWA, LNCSCALT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
