package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZPCKWD {
    boolean pgmRtn = false;
    String CPN_R_MAINT_DAYE = "BP-R-MAINT-DAYE     ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short K_HALF_YEAR_MMDD = 0630;
    int K_YEAR_DAYS = 365;
    String K_DAYS_OF_MON = "312831303130313130313031";
    BPZPCKWD_WS_DAYS_OF_MON WS_DAYS_OF_MON = new BPZPCKWD_WS_DAYS_OF_MON();
    int WS_YEAR_DAYS = 0;
    BPZPCKWD_WS_MMDD0 WS_MMDD0 = new BPZPCKWD_WS_MMDD0();
    BPZPCKWD_WS_MMDD WS_MMDD = new BPZPCKWD_WS_MMDD();
    int WS_I = 0;
    int WS_DAYE_DATE = 0;
    String WS_DAYE_CHAR = " ";
    String CPN_P_CAL_WORK_DAY = "BP-P-CAL-WORK-DAY";
    String BP_Q_WND_TWND = "BP-Q-WND-TWND";
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPRCAL BPRCAL = new BPRCAL();
    BPRDAYE BPRDAYE = new BPRDAYE();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCQTWND BPCQTWND = new BPCQTWND();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOCKWD BPCOCKWD;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCOCKWD BPCOCKWD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOCKWD = BPCOCKWD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCKWD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPCOCKWD.RC);
        WS_YEAR_DAYS = K_YEAR_DAYS;
        IBS.CPY2CLS(SCCGWA, K_DAYS_OF_MON, WS_DAYS_OF_MON);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CAL_DAYS();
        if (pgmRtn) return;
        B030_CAL_OTH_PARM();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCOCKWD.DATE;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        WS_YEAR_DAYS += SCCCKDT.LEAP_YEAR;
        if (BPCOCKWD.CAL_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRBANK.HCLS_TM);
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        CEP.TRC(SCCGWA, BPCRBANK.CITY_CD);
        if (BPCOCKWD.CITY_CODE.trim().length() == 0) {
            BPCOCKWD.CITY_CODE = BPCRBANK.CITY_CD;
            CEP.TRC(SCCGWA, BPCOCKWD.CITY_CODE);
        }
    }
    public void T050_GET_WEEK_DAY_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        BPCOCLWD.WDAYS = 2;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CAL_WORK_DAY, BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD.RC);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCOCLWD.WEEK_NO1);
    }
    public void S000_CALL_BPCQTWND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_Q_WND_TWND, BPCQTWND);
        CEP.TRC(SCCGWA, BPCQTWND);
        CEP.TRC(SCCGWA, BPCOCLWD.WEEK_NO1);
        CEP.TRC(SCCGWA, BPCQTWND.RC);
        CEP.TRC(SCCGWA, BPCQTWND.WND_DATA[BPCOCLWD.WEEK_NO1-1].WND_NO);
        if (BPCQTWND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQTWND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CAL_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALN);
        IBS.init(SCCGWA, BPCRCALN);
        IBS.init(SCCGWA, BPRCAL);
        BPCRCALN.INFO.FUNC = 'R';
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCOCKWD.CAL_CODE;
        JIBS_tmp_str[0] = "" + BPCOCKWD.DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) BPRCALN.KEY.YEAR = 0;
        else BPRCALN.KEY.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        CEP.TRC(SCCGWA, BPRCALN.KEY);
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCRCALN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_NOTFND, BPCOCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRCALN.DATA == null) BPRCALN.DATA = "";
        JIBS_tmp_int = BPRCALN.DATA.length();
        for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
        IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPRCAL.DATA_TEXT.DATA);
        BPRCAL.DATA_TEXT.DATA.CNTYS_CD = BPRCALN.CNTY_CODE;
        BPRCAL.DATA_TEXT.DATA.CITY_CD = BPRCALN.CITY_CODE;
        JIBS_tmp_str[0] = "" + BPCOCKWD.DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1), WS_MMDD0);
        CEP.TRC(SCCGWA, BPCOCKWD.DATE);
        CEP.TRC(SCCGWA, WS_MMDD0);
        CEP.TRC(SCCGWA, WS_MMDD0.WS_M0);
        CEP.TRC(SCCGWA, WS_MMDD0.WS_D0);
        CEP.TRC(SCCGWA, BPRCAL.DATA_TEXT.DATA.MM[WS_MMDD0.WS_M0-1].DD[WS_MMDD0.WS_D0-1].DAY_FLAG);
        BPCOCKWD.SPD_DAY[13-1] = 1;
        CEP.TRC(SCCGWA, BPCOCKWD.SPD_DAY[13-1]);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.DATE1 = BPCOCKWD.DATE;
        BPCOCLWD.CAL_CODE = BPCOCKWD.CAL_CODE;
        T050_GET_WEEK_DAY_NO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCQTWND);
        BPCQTWND.INPUT_DAT.CNTY_CODE = BPRCAL.DATA_TEXT.DATA.CNTYS_CD;
        BPCQTWND.INPUT_DAT.CITY_CODE = BPRCAL.DATA_TEXT.DATA.CITY_CD;
        BPCQTWND.INPUT_DAT.EFF_DATE = BPCOCKWD.DATE;
        BPCQTWND.INPUT_DAT.CAL_CODE = BPCOCKWD.CAL_CODE;
        CEP.TRC(SCCGWA, BPCOCKWD.CAL_CODE);
        CEP.TRC(SCCGWA, BPRCAL.DATA_TEXT.DATA.CITY_CD);
        CEP.TRC(SCCGWA, BPRCAL.DATA_TEXT.DATA.CNTYS_CD);
        S000_CALL_BPCQTWND();
        if (pgmRtn) return;
        BPCOCKWD.SPD_DAY[13-1] = BPCQTWND.WND_DATA[BPCOCLWD.WEEK_NO1-1].WND_NO;
        if (BPRCAL.DATA_TEXT.DATA.MM[WS_MMDD0.WS_M0-1].DD[WS_MMDD0.WS_D0-1].DAY_FLAG == 'W') {
            BPCOCKWD.SPD_DAY[1-1] = 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD0);
            BPCOCKWD.SPD_MMDD[1-1] = Short.parseShort(JIBS_tmp_str[0]);
        } else if (BPRCAL.DATA_TEXT.DATA.MM[WS_MMDD0.WS_M0-1].DD[WS_MMDD0.WS_D0-1].DAY_FLAG == 'S') {
            BPCOCKWD.HALFDAY_FLG = 'Y';
            BPCOCKWD.SPD_DAY[1-1] = 1;
        }
        CEP.TRC(SCCGWA, BPCOCKWD.SPD_DAY[13-1]);
        if (BPCOCKWD.DAYE_FLG == 'Y') {
            CEP.TRC(SCCGWA, BPCOCKWD.DATE);
            WS_DAYE_DATE = BPCOCKWD.DATE;
            B021_GET_DAY_CHARACTER();
            if (pgmRtn) return;
            BPCOCKWD.DAY_CHAR = WS_DAYE_CHAR;
            CEP.TRC(SCCGWA, BPCOCKWD.DAY_CHAR);
        }
        if (BPCOCKWD.STAT_FLG == 'Y') {
            for (WS_MMDD.WS_M = 1; WS_MMDD.WS_M <= 12; WS_MMDD.WS_M += 1) {
                for (WS_MMDD.WS_D = 1; WS_MMDD.WS_D <= WS_DAYS_OF_MON.WS_MON_DAYS[WS_MMDD.WS_M-1]; WS_MMDD.WS_D += 1) {
                    if (BPRCAL.DATA_TEXT.DATA.MM[WS_MMDD.WS_M-1].DD[WS_MMDD.WS_D-1].DAY_FLAG == 'W'
                        || BPRCAL.DATA_TEXT.DATA.MM[WS_MMDD.WS_M-1].DD[WS_MMDD.WS_D-1].DAY_FLAG == 'S') {
                        B022_CAL_WORK_DAYS();
                        if (pgmRtn) return;
                        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
                        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_MMDD0);
                    } else if (BPRCAL.DATA_TEXT.DATA.MM[WS_MMDD.WS_M-1].DD[WS_MMDD.WS_D-1].DAY_FLAG == 'H' 
                            && JIBS_tmp_str[2].compareTo(JIBS_tmp_str[1]) <= 0) {
                        BPCOCKWD.BOY_DAYS[2-1] += 1;
                        if (WS_MMDD.WS_M == WS_MMDD0.WS_M0) {
                            BPCOCKWD.BOM_DAYS[2-1] += 1;
                        }
                    }
                }
            }
        }
    }
    public void B021_GET_DAY_CHARACTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        BPCTDAYE.INFO.FUNC = 'Q';
        BPRDAYE.KEY.TYPE = 'C';
        BPRDAYE.KEY.RGN = BPCOCKWD.CITY_CODE;
        BPRDAYE.KEY.DATE = WS_DAYE_DATE;
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        if (BPCTDAYE.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPCTDAYE);
            IBS.init(SCCGWA, BPRDAYE);
            BPCTDAYE.INFO.POINTER = BPRDAYE;
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
    public void B022_CAL_WORK_DAYS() throws IOException,SQLException,Exception {
        BPCOCKWD.YEAR_DAYS[1-1] += 1;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD0);
        if (JIBS_tmp_str[1].compareTo(JIBS_tmp_str[0]) <= 0) {
            BPCOCKWD.BOY_DAYS[1-1] += 1;
        }
        if (BPCOCKWD.SPD_MMDD[2-1] == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
            BPCOCKWD.SPD_MMDD[2-1] = Short.parseShort(JIBS_tmp_str[0]);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
        BPCOCKWD.SPD_MMDD[3-1] = Short.parseShort(JIBS_tmp_str[0]);
        if (WS_MMDD.WS_M == WS_MMDD0.WS_M0) {
            BPCOCKWD.MON_DAYS[1-1] += 1;
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD0);
            if (JIBS_tmp_str[1].compareTo(JIBS_tmp_str[0]) <= 0) {
                BPCOCKWD.BOM_DAYS[1-1] += 1;
            }
            if (BPCOCKWD.SPD_MMDD[4-1] == 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
                BPCOCKWD.SPD_MMDD[4-1] = Short.parseShort(JIBS_tmp_str[0]);
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MMDD);
            BPCOCKWD.SPD_MMDD[5-1] = Short.parseShort(JIBS_tmp_str[0]);
