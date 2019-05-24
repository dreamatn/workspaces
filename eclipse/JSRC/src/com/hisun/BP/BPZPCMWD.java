package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCMWD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_CALL_BPZQCNCI = "BP-Q-CNTY-CITY-IFO  ";
    String K_CALL_BPZPGDIN = "BP-P-GET-DT-INFO    ";
    short K_CHECK_DAYS = 60;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_I = 0;
    String[] WS_CAL_CD = new String[7];
    String[] WS_CITY_CD = new String[7];
    char WS_ALL_WDAY = ' ';
    char WS_FLG = ' ';
    int WS_DATE = 0;
    int WS_CHECK_DATE = 0;
    char WS_HOLIDAY = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    SCCGWA SCCGWA;
    BPCPCMWD BPCPCMWD;
    public void MP(SCCGWA SCCGWA, BPCPCMWD BPCPCMWD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCMWD = BPCPCMWD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAINTAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCMWD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_CNTY_CITY_CAL_CODE();
        if (pgmRtn) return;
        B030_CHECK_MULTI_CAL_HOLIDAY();
        if (pgmRtn) return;
        B040_GET_HOLIDAY_FLAG_ALL();
        if (pgmRtn) return;
        if (BPCPCMWD.FUNC_FLAG != 'C') {
            B050_GET_NX_LS_WORK_DAY_ALL();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCMWD.FUNC_FLAG);
        CEP.TRC(SCCGWA, BPCPCMWD.CHECK_DATE);
        CEP.TRC(SCCGWA, BPCPCMWD.DATE_TYPE);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[1-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[1-1].CITY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[2-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[2-1].CITY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[3-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[3-1].CITY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[4-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[4-1].CITY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[5-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[5-1].CITY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[6-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[6-1].CITY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[7-1].CNTY_CD);
        CEP.TRC(SCCGWA, BPCPCMWD.CAL_CODE[7-1].CITY_CD);
        if (BPCPCMWD.CHECK_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEK_DT_MUST_INPUT, BPCPCMWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPCMWD.CHECK_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCPCMWD.CHECK_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCPCMWD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCPCMWD.CALCD[1-1].trim().length() == 0 
            && BPCPCMWD.CAL_CODE[1-1].CNTY_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT, BPCPCMWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CNTY_CITY_CAL_CODE() throws IOException,SQLException,Exception {
        WS_CNT = 1;
        for (WS_I = 1; WS_I <= 7; WS_I += 1) {
            if (BPCPCMWD.CALCD[WS_I-1].trim().length() > 0) {
                WS_CAL_CD[WS_CNT-1] = BPCPCMWD.CALCD[WS_I-1];
                WS_CITY_CD[WS_CNT-1] = BPCPCMWD.CAL_CODE[WS_I-1].CITY_CD;
                WS_CNT += 1;
            } else {
                if (BPCPCMWD.CAL_CODE[WS_I-1].CNTY_CD.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCQCNCI);
                    BPCQCNCI.INPUT_DAT.CNTY_CD = BPCPCMWD.CAL_CODE[WS_I-1].CNTY_CD;
                    BPCQCNCI.INPUT_DAT.CITY_CD = BPCPCMWD.CAL_CODE[WS_I-1].CITY_CD;
                    S000_CALL_BPZQCNCI();
                    if (pgmRtn) return;
                    WS_CAL_CD[WS_CNT-1] = BPCQCNCI.OUTPUT_CAL_CODE;
                    WS_CNT += 1;
                    WS_CITY_CD[WS_CNT-1] = BPCPCMWD.CAL_CODE[WS_I-1].CITY_CD;
                }
            }
        }
    }
    public void B030_CHECK_MULTI_CAL_HOLIDAY() throws IOException,SQLException,Exception {
        BPCPCMWD.HOLIDAY_FLG_ALL = 'N';
        for (WS_I = 1; WS_I <= 7; WS_I += 1) {
            IBS.init(SCCGWA, BPCPGDIN);
            CEP.TRC(SCCGWA, WS_CAL_CD[WS_I-1]);
            if (WS_CAL_CD[WS_I-1].trim().length() > 0) {
                BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
                BPCPGDIN.INPUT_DATA.FUNC = '1';
                if (BPCPCMWD.DATE_TYPE == 'H') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
                } else if (BPCPCMWD.DATE_TYPE == 'E') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
                } else {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                }
                BPCPGDIN.INPUT_DATA.CITY_CD = WS_CITY_CD[WS_I-1];
                CEP.TRC(SCCGWA, BPCPCMWD.CHECK_DATE);
                CEP.TRC(SCCGWA, BPCPGDIN);
                BPCPGDIN.INPUT_DATA.DATE_1 = BPCPCMWD.CHECK_DATE;
                S000_CALL_BPZPGDIN();
                if (pgmRtn) return;
                if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                    BPCPCMWD.HOLIDAY_FLG[WS_I-1] = 'Y';
                    WS_ALL_WDAY = 'N';
                } else {
                    BPCPCMWD.HOLIDAY_FLG[WS_I-1] = 'N';
                }
                if (BPCPCMWD.FUNC_FLAG != 'C') {
                    B030_01_GET_NEXT_WDAY();
                    if (pgmRtn) return;
                    B030_02_GET_LAST_WDAY();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B030_01_GET_NEXT_WDAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.DATE_1 = BPCPCMWD.CHECK_DATE;
        BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
        BPCPGDIN.INPUT_DATA.CITY_CD = WS_CITY_CD[WS_I-1];
        if (BPCPCMWD.DATE_TYPE == 'H') {
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
        } else if (BPCPCMWD.DATE_TYPE == 'E') {
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
        } else {
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        }
        if (BPCPCMWD.HOLIDAY_FLG[WS_I-1] == 'Y') {
            BPCPGDIN.INPUT_DATA.WDAYS = 1;
        } else {
            BPCPGDIN.INPUT_DATA.WDAYS = 2;
        }
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        CEP.TRC(SCCGWA, BPCPGDIN);
        S000_CALL_BPZPGDIN();
        if (pgmRtn) return;
        BPCPCMWD.NEXT_WORK_DAY[WS_I-1] = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void B030_02_GET_LAST_WDAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.DATE_1 = BPCPCMWD.CHECK_DATE;
        BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
        BPCPGDIN.INPUT_DATA.CITY_CD = WS_CITY_CD[WS_I-1];
        if (BPCPCMWD.DATE_TYPE == 'H') {
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
        } else if (BPCPCMWD.DATE_TYPE == 'E') {
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
        } else {
            BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        }
        if (BPCPCMWD.HOLIDAY_FLG[WS_I-1] == 'Y') {
            BPCPGDIN.INPUT_DATA.WDAYS = -1;
        } else {
            BPCPGDIN.INPUT_DATA.WDAYS = -2;
        }
        BPCPGDIN.INPUT_DATA.FUNC = '2';
        CEP.TRC(SCCGWA, BPCPGDIN);
        S000_CALL_BPZPGDIN();
        if (pgmRtn) return;
        BPCPCMWD.LAST_WORK_DAY[WS_I-1] = BPCPGDIN.OUTPUT_DATA.DATE_2;
    }
    public void B040_GET_HOLIDAY_FLAG_ALL() throws IOException,SQLException,Exception {
        if (WS_ALL_WDAY == 'N') {
            BPCPCMWD.HOLIDAY_FLG_ALL = 'Y';
        }
    }
    public void B050_GET_NX_LS_WORK_DAY_ALL() throws IOException,SQLException,Exception {
        BPCPCMWD.NEXT_WORK_DAY_ALL = 0;
        WS_DATE = BPCPCMWD.CHECK_DATE;
        WS_CNT = 1;
        WS_FLG = 'N';
        while (WS_FLG != 'Y' 
            && WS_CNT <= K_CHECK_DAYS) {
            IBS.init(SCCGWA, BPCPGDIN);
            if (BPCPCMWD.DATE_TYPE == 'H') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
            } else if (BPCPCMWD.DATE_TYPE == 'E') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
            } else {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
            }
            BPCPGDIN.INPUT_DATA.DATE_1 = WS_DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[1-1];
            BPCPGDIN.INPUT_DATA.CITY_CD = WS_CITY_CD[1-1];
            CEP.TRC(SCCGWA, WS_CITY_CD[1-1]);
            CEP.TRC(SCCGWA, WS_CAL_CD[1-1]);
            BPCPGDIN.INPUT_DATA.WDAYS = 2;
            if (BPCPCMWD.HOLIDAY_FLG[1-1] == 'Y' 
                && WS_CNT == 1) {
                BPCPGDIN.INPUT_DATA.WDAYS = 1;
            }
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            CEP.TRC(SCCGWA, BPCPGDIN);
            S000_CALL_BPZPGDIN();
            if (pgmRtn) return;
            WS_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            CEP.TRC(SCCGWA, WS_DATE);
            WS_CHECK_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            T001_CHECK_HOLIDAY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            WS_CNT += 1;
            if (WS_ALL_WDAY == 'Y') {
                WS_FLG = 'Y';
                BPCPCMWD.NEXT_WORK_DAY_ALL = WS_DATE;
                CEP.TRC(SCCGWA, "FOUND NEXT WRKDAY");
                CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            } else {
                if (WS_CNT > K_CHECK_DAYS) {
                    CEP.TRC(SCCGWA, "CAN NOT FIND NEXT WORKING DAY");
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NEXT_WORK_DAY_ERR, BPCPCMWD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                }
            }
        }
        BPCPCMWD.LAST_WORK_DAY_ALL = 0;
        WS_DATE = BPCPCMWD.CHECK_DATE;
        WS_CNT = 1;
        WS_FLG = 'N';
        while (WS_FLG != 'Y' 
            && WS_CNT <= K_CHECK_DAYS) {
            IBS.init(SCCGWA, BPCPGDIN);
            if (BPCPCMWD.DATE_TYPE == 'H') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
            } else if (BPCPCMWD.DATE_TYPE == 'E') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
            } else {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
            }
            BPCPGDIN.INPUT_DATA.DATE_1 = WS_DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[1-1];
            BPCPGDIN.INPUT_DATA.CITY_CD = WS_CITY_CD[1-1];
            BPCPGDIN.INPUT_DATA.WDAYS = -2;
            if (BPCPCMWD.HOLIDAY_FLG[1-1] == 'Y' 
                && WS_CNT == 1) {
                BPCPGDIN.INPUT_DATA.WDAYS = -1;
            }
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            CEP.TRC(SCCGWA, BPCPGDIN);
            S000_CALL_BPZPGDIN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            WS_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            WS_CHECK_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            T001_CHECK_HOLIDAY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DATE);
            CEP.TRC(SCCGWA, WS_CNT);
            WS_CNT += 1;
            if (WS_ALL_WDAY == 'Y') {
                WS_FLG = 'Y';
                BPCPCMWD.LAST_WORK_DAY_ALL = WS_DATE;
                CEP.TRC(SCCGWA, "FOUND LAST WRKDAY");
                CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            } else {
                if (WS_CNT > K_CHECK_DAYS) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LAST_WORK_DAY_ERR, BPCPCMWD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                }
            }
        }
    }
    public void T001_CHECK_HOLIDAY() throws IOException,SQLException,Exception {
        WS_ALL_WDAY = 'Y';
        for (WS_I = 1; WS_I <= 7 
            && WS_ALL_WDAY != 'N'; WS_I += 1) {
            IBS.init(SCCGWA, BPCPGDIN);
            if (WS_CAL_CD[WS_I-1].trim().length() > 0) {
                BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
                BPCPGDIN.INPUT_DATA.CITY_CD = WS_CITY_CD[WS_I-1];
                CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CITY_CD);
                BPCPGDIN.INPUT_DATA.FUNC = '1';
                if (BPCPCMWD.DATE_TYPE == 'H') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
                } else if (BPCPCMWD.DATE_TYPE == 'E') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
                } else {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                }
                BPCPGDIN.INPUT_DATA.DATE_1 = WS_CHECK_DATE;
                CEP.TRC(SCCGWA, BPCPGDIN);
                S000_CALL_BPZPGDIN();
                if (pgmRtn) return;
                if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                    WS_ALL_WDAY = 'N';
                }
            }
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQCNCI, BPCQCNCI);
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZPGDIN, BPCPGDIN);
        CEP.TRC(SCCGWA, BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCMWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCMWD = ");
            CEP.TRC(SCCGWA, BPCPCMWD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
