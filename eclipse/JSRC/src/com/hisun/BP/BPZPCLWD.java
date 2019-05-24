package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCLWD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String CPN_R_MAINT_DAYE = "BP-R-MAINT-DAYE     ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    int WS_NN1 = 0;
    int WS_NN2 = 0;
    int WS_DAYS = 0;
    int WS_WDAYS = 0;
    int WS_HDAYS = 0;
    int WS_TDAYS = 0;
    int WS_DAYS1 = 0;
    short WS_BM = 0;
    short WS_BD = 0;
    short WS_EM = 0;
    short WS_ED = 0;
    short WS_BM0 = 0;
    short WS_BD0 = 0;
    int WS_STEP = 0;
    char WS_FLAG1 = ' ';
    char WS_FLAG2 = ' ';
    char WS_FLAG3 = ' ';
    BPZPCLWD_WS_DATE WS_DATE = new BPZPCLWD_WS_DATE();
    BPZPCLWD_WS_DATE1 WS_DATE1 = new BPZPCLWD_WS_DATE1();
    int WS_DAYE_DATE = 0;
    String WS_DAYE_CHAR = " ";
    short WS_YEAR = 0;
    char WS_STOP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPRCAL BPRCAL = new BPRCAL();
    BPRDAYE BPRDAYE = new BPRDAYE();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOCLWD BPCOCLWD;
    public void MP(SCCGWA SCCGWA, BPCOCLWD BPCOCLWD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOCLWD = BPCOCLWD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCLWD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOCLWD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_SWITCH_PROCESS();
        if (pgmRtn) return;
        B030_DAY_CHAR_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        if (BPCOCLWD.CAL_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCOCLWD.DATE1 != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCOCLWD.DATE1;
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            WS_NN1 = SCCCKDT.SEQ_DATE;
            BPCOCLWD.WEEK_NO1 = SCCCKDT.WEEK_NO;
        }
    }
    public void B020_SWITCH_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.HDAYS);
        CEP.TRC(SCCGWA, "HSUN059SHUXING");
        if (BPCOCLWD.DATE2 != 0) {
            B021_A_DATE();
            if (pgmRtn) return;
        } else if (BPCOCLWD.DAYS != 0) {
            B022_DAYS();
            if (pgmRtn) return;
        } else if (BPCOCLWD.WDAYS != 0
            || BPCOCLWD.HDAYS != 0) {
            B023_WH_DAYS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_A_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCOCLWD.DATE2;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        WS_NN2 = SCCCKDT.SEQ_DATE;
        BPCOCLWD.WEEK_NO2 = SCCCKDT.WEEK_NO;
        WS_DAYS = WS_NN2 - WS_NN1;
        if (WS_DAYS < 0) {
            WS_DAYS -= 1;
        } else {
            WS_DAYS += 1;
        }
        BPCOCLWD.DAYS = WS_DAYS;
        R000_SCAN_CLD();
        if (pgmRtn) return;
        BPCOCLWD.WDAYS = WS_WDAYS;
        BPCOCLWD.HDAYS = WS_HDAYS;
    }
    public void B022_DAYS() throws IOException,SQLException,Exception {
        WS_DAYS = BPCOCLWD.DAYS;
        R000_SCAN_CLD();
        if (pgmRtn) return;
        BPCOCLWD.WDAYS = WS_WDAYS;
        BPCOCLWD.HDAYS = WS_HDAYS;
    }
    public void B023_WH_DAYS() throws IOException,SQLException,Exception {
        if (BPCOCLWD.WDAYS != 0) {
            WS_DAYS = BPCOCLWD.WDAYS;
        } else {
            WS_DAYS = BPCOCLWD.HDAYS;
        }
        R000_SCAN_CLD();
        if (pgmRtn) return;
        BPCOCLWD.DAYS = WS_DAYS;
        BPCOCLWD.WDAYS = WS_WDAYS;
        BPCOCLWD.HDAYS = WS_HDAYS;
    }
    public void B024_B_DATE() throws IOException,SQLException,Exception {
        if (BPCOCLWD.DATE1 == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCOCLWD.DATE1;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (SCCCKDT.WEEK_NO >= BPCOCLWD.WEEK_NO2) {
            WS_TDAYS = 7 - SCCCKDT.WEEK_NO + BPCOCLWD.WEEK_NO2;
        } else {
            WS_TDAYS = BPCOCLWD.WEEK_NO2 - SCCCKDT.WEEK_NO;
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BPCOCLWD.DATE1;
        SCCCLDT.DAYS = WS_TDAYS;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        BPCOCLWD.DATE2 = SCCCLDT.DATE2;
        JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_YEAR = 0;
        else WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        S000_READ_BPTCAL();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_BM = 0;
        else WS_BM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_BD = 0;
        else WS_BD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCAL.DATA_TEXT.DATA.MM[WS_BM-1].DD[WS_BD-1]);
        BPCOCLWD.DATE2_FLG = JIBS_tmp_str[0].charAt(0);
    }
    public void B030_DAY_CHAR_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOCLWD.DAYE_FLG == 'Y') {
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            WS_DAYE_DATE = BPCOCLWD.DATE1;
            B031_GET_DAY_CHARACTER();
            if (pgmRtn) return;
            BPCOCLWD.DATE1_CHAR = WS_DAYE_CHAR;
            WS_DAYE_DATE = BPCOCLWD.DATE2;
            B031_GET_DAY_CHARACTER();
            if (pgmRtn) return;
            BPCOCLWD.DATE2_CHAR = WS_DAYE_CHAR;
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1_CHAR);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_CHAR);
            CEP.TRC(SCCGWA, BPCOCLWD);
            CEP.TRC(SCCGWA, "HSUNQQ");
        }
    }
    public void B031_GET_DAY_CHARACTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        BPCTDAYE.INFO.FUNC = 'Q';
        BPRDAYE.KEY.TYPE = 'B';
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRDAYE.KEY.BCH = JIBS_tmp_str[0].substring(0, 3);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.BCH);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.BCH);
        BPRDAYE.KEY.DATE = WS_DAYE_DATE;
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPCTDAYE);
            IBS.init(SCCGWA, BPRDAYE);
            BPCTDAYE.INFO.FUNC = 'Q';
            BPRDAYE.KEY.TYPE = 'I';
            BPRDAYE.KEY.DATE = WS_DAYE_DATE;
            S000_CALL_BPZTDAYE();
            if (pgmRtn) return;
        }
        if (BPCTDAYE.RETURN_INFO == 'F') {
            WS_DAYE_CHAR = BPRDAYE.CHARACTER;
        } else if (BPCTDAYE.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DAYE NOT FND,SET SPACE");
            WS_DAYE_CHAR = " ";
        }
    }
    public void R000_SCAN_CLD() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCOCLWD.DATE1+"", WS_DATE1);
        if (WS_DAYS > 0) {
            WS_STEP = 1;
            WS_BM0 = 1;
            WS_BD0 = 1;
            WS_EM = 13;
            WS_ED = 32;
        } else {
            WS_STEP = -1;
            WS_BM0 = 12;
            WS_BD0 = 31;
            WS_EM = 0;
            WS_ED = 0;
        }
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, WS_STEP);
        CEP.TRC(SCCGWA, WS_BM0);
        CEP.TRC(SCCGWA, WS_BD0);
        CEP.TRC(SCCGWA, WS_EM);
        CEP.TRC(SCCGWA, WS_ED);
        WS_DAYS1 = 0;
        WS_DAYS = 0;
        WS_STOP = 'N';
        WS_BM = WS_DATE1.WS_MM1;
        WS_BD = WS_DATE1.WS_DD1;
        for (WS_DATE.WS_Y = WS_DATE1.WS_YYYY1; WS_STOP != 'Y' 
            && (WS_DATE.WS_Y >= 1900 
            && WS_DATE.WS_Y <= 2099); WS_DATE.WS_Y += WS_STEP) {
            WS_YEAR = WS_DATE.WS_Y;
            S000_READ_BPTCAL();
            if (pgmRtn) return;
            if (WS_DATE.WS_Y == WS_DATE1.WS_YYYY1) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCAL.DATA_TEXT.DATA.MM[WS_DATE1.WS_MM1-1].DD[WS_DATE1.WS_DD1-1]);
                BPCOCLWD.DATE1_FLG = JIBS_tmp_str[0].charAt(0);
            }
            for (WS_DATE.WS_M = WS_BM; WS_DATE.WS_M != WS_EM 
                && WS_STOP != 'Y'; WS_DATE.WS_M += WS_STEP) {
                CEP.TRC(SCCGWA, WS_BD);
                IBS.init(SCCGWA, SCCCKDT);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
                SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
                JIBS_tmp_str[0] = "" + SCCCKDT.DATE;
                JIBS_f0 = "";
                for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + SCCCKDT.DATE;
                JIBS_tmp_str[1] = "" + 1;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                SCCCKDT.DATE = Integer.parseInt(JIBS_NumStr);
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                if (WS_STEP == 1) {
                    WS_ED = SCCCKDT.MTH_DAYS;
                    WS_ED += 1;
                }
                if (WS_STEP == -1 
                    && ((WS_DATE1.WS_MM1 != WS_DATE.WS_M) 
                    || (WS_DATE1.WS_YYYY1 != WS_DATE.WS_Y))) {
                    WS_BD = SCCCKDT.MTH_DAYS;
                }
                CEP.TRC(SCCGWA, WS_BD);
                for (WS_DATE.WS_D = WS_BD; WS_DATE.WS_D != WS_ED 
                    && WS_STOP != 'Y'; WS_DATE.WS_D += WS_STEP) {
                    CEP.TRC(SCCGWA, "BEFORE JUDGE DAYE ");
                    CEP.TRC(SCCGWA, WS_BD);
                    CEP.TRC(SCCGWA, WS_DATE.WS_M);
                    CEP.TRC(SCCGWA, WS_DATE.WS_D);
                    CEP.TRC(SCCGWA, WS_WDAYS);
                    CEP.TRC(SCCGWA, WS_DAYS);
                    CEP.TRC(SCCGWA, WS_HDAYS);
                    if (BPRCAL.DATA_TEXT.DATA.MM[WS_DATE.WS_M-1].DD[WS_DATE.WS_D-1].DAY_FLAG == 'W' 
                        || BPRCAL.DATA_TEXT.DATA.MM[WS_DATE.WS_M-1].DD[WS_DATE.WS_D-1].DAY_FLAG == 'S') {
                        WS_WDAYS = WS_WDAYS + WS_STEP;
                        WS_DAYS = WS_DAYS + WS_STEP;
                    } else {
                        WS_HDAYS = WS_HDAYS + WS_STEP;
                        WS_DAYS = WS_DAYS + WS_STEP;
                    }
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCAL.DATA_TEXT.DATA.MM[WS_DATE.WS_M-1].DD[WS_DATE.WS_D-1]);
                    BPCOCLWD.DATE2_FLG = JIBS_tmp_str[0].charAt(0);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
                    BPCOCLWD.DATE2 = Integer.parseInt(JIBS_tmp_str[0]);
                    if (WS_WDAYS == BPCOCLWD.WDAYS 
                        && BPCOCLWD.WDAYS != 0) {
                        WS_STOP = 'Y';
                    }
                    if (WS_DAYS == BPCOCLWD.DAYS 
                        && BPCOCLWD.HDAYS != 0) {
                        WS_STOP = 'Y';
                    }
                    if (WS_DAYS == BPCOCLWD.DAYS 
                        && BPCOCLWD.DAYS != 0) {
                        WS_STOP = 'Y';
                    }
                }
                WS_BD = WS_BD0;
            }
            WS_BM = WS_BM0;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCOCLWD.DATE2;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        BPCOCLWD.WEEK_NO2 = SCCCKDT.WEEK_NO;
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_DAYE, BPCTDAYE);
        if (BPCTDAYE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTDAYE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_BPTCAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALN);
        IBS.init(SCCGWA, BPCRCALN);
        IBS.init(SCCGWA, BPRCAL);
        BPCRCALN.INFO.FUNC = 'R';
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCOCLWD.CAL_CODE;
        BPRCALN.KEY.YEAR = WS_YEAR;
        CEP.TRC(SCCGWA, BPRCALN.KEY);
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCRCALN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_NOTFND, BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRCALN.DATA == null) BPRCALN.DATA = "";
        JIBS_tmp_int = BPRCALN.DATA.length();
        for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
        IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPRCAL.DATA_TEXT.DATA);
        BPRCAL.DATA_TEXT.DATA.CNTYS_CD = BPRCALN.CNTY_CODE;
        BPRCAL.DATA_TEXT.DATA.CITY_CD = BPRCALN.CITY_CODE;
    }
    public void R000_PARM_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPRCALN.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCRCALN.INFO.POINTER = BPRCALN;
        BPCRCALN.INFO.LEN = 173;
        S000_CALL_BPZRCALN();
        if (pgmRtn) return;
        if (BPCRCALN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCALN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCLWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCALN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALN_READ, BPCRCALN);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOCLWD = ");
            CEP.TRC(SCCGWA, BPCOCLWD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
