package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPGDIN {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_WEEKEND_CNT = 7;
    String WS_CAL_CD = " ";
    short WS_WEEK_NO = 0;
    int WS_CHK_DATE = 0;
    int WS_DAYE_DATE = 0;
    BPZPGDIN_WS_DATE WS_DATE = new BPZPGDIN_WS_DATE();
    BPZPGDIN_WS_EOF_DATE WS_EOF_DATE = new BPZPGDIN_WS_EOF_DATE();
    BPZPGDIN_WS_TOP_DATE WS_TOP_DATE = new BPZPGDIN_WS_TOP_DATE();
    short WS_MON_TOT = 0;
    short WS_CNT = 0;
    short WS_I = 0;
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPRDAYE BPRDAYE = new BPRDAYE();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCITHOL BPCITHOL = new BPCITHOL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCPGDIN BPCPGDIN;
    public void MP(SCCGWA SCCGWA, BPCPGDIN BPCPGDIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPGDIN = BPCPGDIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPGDIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPGDIN.RC);
        BPCPGDIN.RC.RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1");
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_CAL_CODE();
        if (pgmRtn) return;
        if (BPCPGDIN.INPUT_DATA.FUNC == '1') {
            B300_CHECK_DATE_PROC();
            if (pgmRtn) return;
        } else {
            B400_CAL_NXT_DATE_PROC();
            if (pgmRtn) return;
            B500_GET_DATE_INFO_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.DATE_1);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.DAYS);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.WDAYS);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CAL_CD);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CONTY_CD);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CITY_CD);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CCY);
        if (BPCPGDIN.INPUT_DATA.CAL_CD.trim().length() == 0 
            && BPCPGDIN.INPUT_DATA.CONTY_CD.trim().length() == 0 
            && BPCPGDIN.INPUT_DATA.CITY_CD.trim().length() == 0 
            && BPCPGDIN.INPUT_DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 1");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.INPUT_DATA.CAL_CD.trim().length() == 0 
            && BPCPGDIN.INPUT_DATA.CONTY_CD.trim().length() == 0 
            && BPCPGDIN.INPUT_DATA.CCY.trim().length() == 0 
            && BPCPGDIN.INPUT_DATA.CITY_CD.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 2");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.INPUT_DATA.FUNC != '1' 
            && BPCPGDIN.INPUT_DATA.FUNC != '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 3");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE != 'B' 
            && BPCPGDIN.INPUT_DATA.DATE_TYPE != 'H' 
            && BPCPGDIN.INPUT_DATA.DATE_TYPE != 'E') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 4");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.INPUT_DATA.DATE_1 == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 5");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.OUTPUT_DATA.DATE_2 != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 6");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.INPUT_DATA.FUNC == '2' 
            && BPCPGDIN.INPUT_DATA.DAYS == 0 
            && BPCPGDIN.INPUT_DATA.HDAYS == 0 
            && BPCPGDIN.INPUT_DATA.WDAYS == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 7");
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CNT = 0;
        if (BPCPGDIN.INPUT_DATA.DAYS != 0) {
            WS_CNT = (short) (WS_CNT + 1);
        }
        if (BPCPGDIN.INPUT_DATA.HDAYS != 0) {
            WS_CNT = (short) (WS_CNT + 1);
        }
        if (BPCPGDIN.INPUT_DATA.WDAYS != 0) {
            WS_CNT = (short) (WS_CNT + 1);
        }
        if (BPCPGDIN.INPUT_DATA.FUNC == '2' 
            && WS_CNT > 1) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 8");
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPGDIN.INPUT_DATA.FUNC == '2' 
            && BPCPGDIN.INPUT_DATA.DATE_TYPE != 'B' 
            && (BPCPGDIN.INPUT_DATA.HDAYS != 0 
            || BPCPGDIN.INPUT_DATA.DAYS != 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 9");
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_CAL_CODE() throws IOException,SQLException,Exception {
        WS_CAL_CD = BPCPGDIN.INPUT_DATA.CAL_CD;
        if (BPCPGDIN.INPUT_DATA.CAL_CD.trim().length() == 0) {
            if (BPCPGDIN.INPUT_DATA.CONTY_CD.trim().length() > 0) {
                B200_01_GET_CAL_BY_CNTY();
                if (pgmRtn) return;
            } else {
                B200_02_GET_CAL_BY_CCY();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_01_GET_CAL_BY_CNTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '9';
        BPCOQCAL.CITY_NO.CNTYS_CD = BPCPGDIN.INPUT_DATA.CONTY_CD;
        BPCOQCAL.CITY_NO.CITY_CD = BPCPGDIN.INPUT_DATA.CITY_CD;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
        WS_CAL_CD = BPCOQCAL.CAL_CODE;
    }
    public void B200_02_GET_CAL_BY_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '1';
        BPCOQCAL.CCY = BPCPGDIN.INPUT_DATA.CCY;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
        WS_CAL_CD = BPCOQCAL.CAL_CODE;
    }
    public void B300_CHECK_DATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCKWD);
        BPCOCKWD.DATE = BPCPGDIN.INPUT_DATA.DATE_1;
        BPCOCKWD.CAL_CODE = WS_CAL_CD;
        BPCOCKWD.CITY_CODE = BPCPGDIN.INPUT_DATA.CITY_CD;
        BPCOCKWD.DAYE_FLG = 'Y';
        BPCOCKWD.STAT_FLG = 'Y';
        S000_CALL_BPZPCKWD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCPGDIN.INPUT_DATA.DATE_1;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        BPCPGDIN.OUTPUT_DATA.WEEK_NO1 = SCCCKDT.WEEK_NO;
        if (BPCOCKWD.SPD_DAY[1-1] == 1) {
            BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'W';
        } else {
            BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'H';
        }
        if (BPCOCKWD.HALFDAY_FLG == 'Y') {
            BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'S';
        }
        IBS.CPY2CLS(SCCGWA, BPCPGDIN.INPUT_DATA.DATE_1+"", WS_DATE);
        IBS.CPY2CLS(SCCGWA, BPCPGDIN.INPUT_DATA.DATE_1+"", WS_EOF_DATE);
        WS_MON_TOT = (short) (BPCOCKWD.MON_DAYS[1-1] + BPCOCKWD.MON_DAYS[2-1]);
        WS_EOF_DATE.WS_EOF_DAY = WS_MON_TOT;
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'H') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EOF_DATE);
            if (WS_DATE.WS_DAY == 1 
                || BPCPGDIN.INPUT_DATA.DATE_1 == JIBS_tmp_str[0]) {
                BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'W';
            }
        } else if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'E') {
            WS_CHK_DATE = BPCPGDIN.INPUT_DATA.DATE_1;
            B600_CHECK_WEEKEND_PROC();
            if (pgmRtn) return;
            if (BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[BPCPGDIN.OUTPUT_DATA.WEEK_NO1-1].WEEKND == 0) {
                BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'H';
            }
        } else {
        }
        BPCPGDIN.OUTPUT_DATA.DATE1_CHAR = BPCOCKWD.DAY_CHAR;
        WS_DAYE_DATE = BPCPGDIN.INPUT_DATA.DATE_1;
        B300_01_GET_OTH_FLAG();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'F') {
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("1")) {
                BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = '1';
            }
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("2")) {
                BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = '2';
            }
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("3")) {
                BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = '3';
            }
            BPCPGDIN.OUTPUT_DATA.DATE1_EXG = BPRDAYE.EXCH_FLG.charAt(0);
        } else if (BPCTDAYE.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DAYE NOT FND,SET SPACE");
            BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = ' ';
            if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                CEP.TRC(SCCGWA, "HERE2");
                BPCPGDIN.OUTPUT_DATA.DATE1_EXG = 'N';
            } else {
                BPCPGDIN.OUTPUT_DATA.DATE1_EXG = 'Y';
            }
        }
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'E') {
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N') {
                CEP.TRC(SCCGWA, "HERE3");
                BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'H';
            } else {
                BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'W';
            }
        }
    }
    public void B300_01_GET_OTH_FLAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        BPCTDAYE.INFO.FUNC = 'Q';
        BPRDAYE.KEY.TYPE = 'C';
        BPRDAYE.KEY.DATE = WS_DAYE_DATE;
        BPRDAYE.KEY.RGN = BPCPGDIN.INPUT_DATA.CITY_CD;
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        CEP.TRC(SCCGWA, BPRDAYE.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.RGN);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.BCH);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.DATE);
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
    }
    public void B400_CAL_NXT_DATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = WS_CAL_CD;
        BPCOCLWD.DATE1 = BPCPGDIN.INPUT_DATA.DATE_1;
        BPCOCLWD.DAYE_FLG = 'Y';
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'B') {
            BPCOCLWD.DAYS = BPCPGDIN.INPUT_DATA.DAYS;
            BPCOCLWD.HDAYS = BPCPGDIN.INPUT_DATA.HDAYS;
            BPCOCLWD.WDAYS = BPCPGDIN.INPUT_DATA.WDAYS;
        } else {
            BPCOCLWD.DAYS = BPCPGDIN.INPUT_DATA.WDAYS;
        }
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        BPCPGDIN.OUTPUT_DATA.DATE_2 = BPCOCLWD.DATE2;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'H') {
            if (BPCOCLWD.DATE2_FLG == 'H') {
                IBS.CPY2CLS(SCCGWA, BPCOCLWD.DATE2+"", WS_DATE);
                B400_02_GET_EOFMON();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EOF_DATE);
                if (WS_DATE.WS_DAY == 1 
                    || BPCOCLWD.DATE2 == JIBS_tmp_str[0]) {
                } else {
                    B400_03_GET_NEXT_WDAY();
                    if (pgmRtn) return;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
                    JIBS_tmp_str[1] = "" + BPCOCLWD.DATE2;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    if (!JIBS_tmp_str[0].equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
                        if (JIBS_tmp_str[0].compareTo(BPCOCLWD.DATE2) < 0) {
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EOF_DATE);
                            BPCPGDIN.OUTPUT_DATA.DATE_2 = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
                            JIBS_tmp_str[1] = JIBS_tmp_str[0];
                            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_TOP_DATE);
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TOP_DATE);
                            BPCPGDIN.OUTPUT_DATA.DATE_2 = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    } else {
                        BPCPGDIN.OUTPUT_DATA.DATE_2 = BPCOCLWD.DATE2;
                    }
                }
            }
        } else if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'E') {
            CEP.TRC(SCCGWA, "1111");
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            if (BPCOCLWD.DATE2_FLG == 'H') {
                IBS.CPY2CLS(SCCGWA, BPCOCLWD.DATE2+"", WS_DATE);
                B400_03_GET_NEXT_WDAY();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "2222");
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCOCLWD.DATE2;
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            WS_WEEK_NO = SCCCKDT.WEEK_NO;
            WS_CHK_DATE = BPCOCLWD.DATE2;
            B600_CHECK_WEEKEND_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_WEEK_NO);
            CEP.TRC(SCCGWA, BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_WEEK_NO-1].WEEKND);
            BPCOCLWD.DATE2 = WS_CHK_DATE;
            if (BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_WEEK_NO-1].WEEKND == 0) {
                CEP.TRC(SCCGWA, "3333");
                IBS.CPY2CLS(SCCGWA, WS_CHK_DATE+"", WS_DATE);
                B400_04_GET_NEXT_WDAY();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = BPCOCLWD.DATE2;
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                WS_WEEK_NO = SCCCKDT.WEEK_NO;
                WS_CHK_DATE = BPCOCLWD.DATE2;
                CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.WEEK_NO2);
                B600_CHECK_WEEKEND_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_WEEK_NO-1].WEEKND);
                if (BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_WEEK_NO-1].WEEKND == 0) {
                    IBS.CPY2CLS(SCCGWA, WS_CHK_DATE+"", WS_DATE);
                    B400_04_GET_NEXT_WDAY();
                    if (pgmRtn) return;
                }
                BPCOCLWD.DATE2 = WS_CHK_DATE;
            }
            BPCPGDIN.OUTPUT_DATA.DATE_2 = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, "6666");
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        } else {
        }
    }
    public void B500_GET_DATE_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = WS_CAL_CD;
        BPCOCLWD.DATE1 = BPCPGDIN.INPUT_DATA.DATE_1;
        BPCOCLWD.DATE2 = BPCPGDIN.OUTPUT_DATA.DATE_2;
        BPCOCLWD.DAYE_FLG = 'Y';
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        BPCPGDIN.OUTPUT_DATA.WEEK_NO1 = BPCOCLWD.WEEK_NO1;
        BPCPGDIN.OUTPUT_DATA.WEEK_NO2 = BPCOCLWD.WEEK_NO2;
        BPCPGDIN.OUTPUT_DATA.DATE1_FLG = BPCOCLWD.DATE1_FLG;
        BPCPGDIN.OUTPUT_DATA.DATE1_CHAR = BPCOCLWD.DATE1_CHAR;
        WS_DAYE_DATE = BPCPGDIN.INPUT_DATA.DATE_1;
        B300_01_GET_OTH_FLAG();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'F') {
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("1")) {
                BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = '1';
            }
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("2")) {
                BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = '2';
            }
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("3")) {
                BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = '3';
            }
            BPCPGDIN.OUTPUT_DATA.DATE1_EXG = BPRDAYE.EXCH_FLG.charAt(0);
        } else if (BPCTDAYE.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DAYE NOT FND,SET SPACE");
            BPCPGDIN.OUTPUT_DATA.DATE1_TYPE = ' ';
            BPCPGDIN.OUTPUT_DATA.DATE1_EXG = 'Y';
        }
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'E') {
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N') {
                CEP.TRC(SCCGWA, "HERE1");
                BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'H';
            } else {
                BPCPGDIN.OUTPUT_DATA.DATE1_FLG = 'W';
            }
        }
        BPCPGDIN.OUTPUT_DATA.DATE2_FLG = BPCOCLWD.DATE2_FLG;
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE != 'B') {
            BPCPGDIN.OUTPUT_DATA.DATE2_FLG = 'W';
        }
        BPCPGDIN.OUTPUT_DATA.DATE2_CHAR = BPCOCLWD.DATE2_CHAR;
        WS_DAYE_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
        B300_01_GET_OTH_FLAG();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'F') {
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("1")) {
                BPCPGDIN.OUTPUT_DATA.DATE2_TYPE = '1';
            }
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("2")) {
                BPCPGDIN.OUTPUT_DATA.DATE2_TYPE = '2';
            }
            if (BPRDAYE.CHARACTER.equalsIgnoreCase("3")) {
                BPCPGDIN.OUTPUT_DATA.DATE2_TYPE = '3';
            }
            BPCPGDIN.OUTPUT_DATA.DATE2_EXG = BPRDAYE.EXCH_FLG.charAt(0);
        } else if (BPCTDAYE.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DAYE NOT FND,SET SPACE");
            BPCPGDIN.OUTPUT_DATA.DATE2_TYPE = ' ';
            BPCPGDIN.OUTPUT_DATA.DATE2_EXG = 'Y';
        }
        if (BPCPGDIN.INPUT_DATA.DATE_TYPE == 'E') {
            if (BPCPGDIN.OUTPUT_DATA.DATE2_EXG == 'N') {
                BPCPGDIN.OUTPUT_DATA.DATE2_FLG = 'H';
            } else {
                BPCPGDIN.OUTPUT_DATA.DATE2_FLG = 'W';
            }
        }
    }
    public void B400_02_GET_EOFMON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCKWD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
        BPCOCKWD.DATE = Integer.parseInt(JIBS_tmp_str[0]);
        BPCOCKWD.CAL_CODE = WS_CAL_CD;
        BPCOCKWD.CITY_CODE = BPCPGDIN.INPUT_DATA.CITY_CD;
        BPCOCKWD.STAT_FLG = 'Y';
        S000_CALL_BPZPCKWD();
        if (pgmRtn) return;
        WS_MON_TOT = (short) (BPCOCKWD.MON_DAYS[1-1] + BPCOCKWD.MON_DAYS[2-1]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EOF_DATE);
        WS_EOF_DATE.WS_EOF_DAY = WS_MON_TOT;
    }
    public void B400_03_GET_NEXT_WDAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        CEP.TRC(SCCGWA, WS_CAL_CD);
        CEP.TRC(SCCGWA, WS_DATE);
        BPCOCLWD.CAL_CODE = WS_CAL_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
        BPCOCLWD.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
        if (BPCPGDIN.INPUT_DATA.WDAYS > 0) {
            BPCOCLWD.WDAYS = 1;
        } else {
            BPCOCLWD.WDAYS = -1;
        }
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
    }
    public void B400_04_GET_NEXT_WDAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        CEP.TRC(SCCGWA, WS_CAL_CD);
        CEP.TRC(SCCGWA, WS_DATE);
        BPCOCLWD.CAL_CODE = WS_CAL_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
        BPCOCLWD.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
        if (BPCPGDIN.INPUT_DATA.WDAYS > 0) {
            BPCOCLWD.WDAYS = 2;
        } else {
            BPCOCLWD.WDAYS = -2;
        }
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
    }
    public void B600_CHECK_WEEKEND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        IBS.init(SCCGWA, BPCITHOL);
        CEP.TRC(SCCGWA, WS_CAL_CD);
        BPCITHOL.INPUT_DAT.CAL_CD = WS_CAL_CD;
        BPCITHOL.INPUT_DAT.INQUIRE_DATE = WS_CHK_DATE;
        BPCITHOL.INPUT_DAT.QUERY_BY_DT = 'Y';
        S000_CALL_BPZITHOL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCITHOL.RC);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCPGDIN.RC);
            CEP.TRC(SCCGWA, "INPUT ERROR 10");
            CEP.TRC(SCCGWA, SCCCKDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-DAYE", BPCTDAYE);
        if (BPCTDAYE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTDAYE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPGDIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPGDIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CAL-CODE", BPCOQCAL);
        if (BPCOQCAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPGDIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-WORK-DAY", BPCOCKWD);
        CEP.TRC(SCCGWA, BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPGDIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD.RC);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPGDIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZITHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-I-INQ-THOL", BPCITHOL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCITHOL.RC);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, BPCITHOL.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CNTY_CITY_HOL_NOTFND) 
            || JIBS_tmp_str[2].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_WEEKEND_CD_NOTFND)) {
        } else {
            if (BPCITHOL.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCITHOL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPGDIN.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
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
        if (BPCPGDIN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPGDIN = ");
            CEP.TRC(SCCGWA, BPCPGDIN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
