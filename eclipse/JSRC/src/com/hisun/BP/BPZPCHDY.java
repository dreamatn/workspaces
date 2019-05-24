package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZPCHDY {
    boolean pgmRtn = false;
    String K_CMP_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String K_CMP_BPRMBPM = "BP-R-MBRW-PARM   ";
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CALENDER";
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_CMP_MAIN_BPTCALR = "BP-R-MAINT-CALR";
    String K_HIS_REMARKS = "CALENDER MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHCAL";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PARM_TYPE = "CALEN";
    BPZPCHDY_WS_ERR_MSG WS_ERR_MSG = new BPZPCHDY_WS_ERR_MSG();
    short WS_J = 0;
    short WS_I = 0;
    short WS_II = 0;
    short WS_MON = 0;
    BPZPCHDY_WS_GET_DAYS_DATA WS_GET_DAYS_DATA = new BPZPCHDY_WS_GET_DAYS_DATA();
    String WS_CAL_CODE = " ";
    short WS_WEEK_NO = 0;
    BPZPCHDY_WS_CAL_KEY WS_CAL_KEY = new BPZPCHDY_WS_CAL_KEY();
    BPZPCHDY_WS_OD_CAL_DETAIL WS_OD_CAL_DETAIL = new BPZPCHDY_WS_OD_CAL_DETAIL();
    BPZPCHDY_WS_TXN_HIS_DATA WS_TXN_HIS_DATA = new BPZPCHDY_WS_TXN_HIS_DATA();
    char WS_FOUND_FLG = ' ';
    char WS_AUTOGEN_FLG = ' ';
    char WS_MON_DIFF_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_HOL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCSCALE BPCSCALE = new BPCSCALE();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCHCAL BPCOHCAL = new BPCHCAL();
    BPCHCAL BPCNHCAL = new BPCHCAL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTCALR BPCTCALR = new BPCTCALR();
    BPRCALR BPRCALR = new BPRCALR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCBCHDY BPCBCHDY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, BPCBCHDY BPCBCHDY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBCHDY = BPCBCHDY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCHDY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBCHDY.HOL_TXT.HOL_EFF_DT);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOQCAL);
        IBS.init(SCCGWA, BPCSCALE);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCOHCAL);
        IBS.init(SCCGWA, BPCNHCAL);
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCTCALR);
        IBS.init(SCCGWA, BPRCALR);
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCCALL);
        IBS.init(SCCGWA, SCCCKDT);
        WS_ERR_MSG.WS_RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBCHDY);
        CEP.TRC(SCCGWA, BPCBCHDY.HOL_TXT.HOL_EFF_DT);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAINTAIN_CAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCBCHDY.CNTYS_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CNTY_CD, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAINTAIN_CAL() throws IOException,SQLException,Exception {
        B021_GET_CITY_CAL_CODE();
        if (pgmRtn) return;
        B022_REWRITE_CALENDAR_CODE();
        if (pgmRtn) return;
    }
    public void B021_GET_CITY_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '9';
        BPCOQCAL.CITY_NO.CNTYS_CD = BPCBCHDY.CNTYS_CD;
        BPCOQCAL.CITY_NO.CITY_CD = BPCBCHDY.CITY_CD;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
    }
    public void B022_REWRITE_CALENDAR_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = K_PARM_TYPE;
        WS_CAL_KEY.WS_KEY_CAL_CODE = BPCOQCAL.CAL_CODE;
        BPRPARM.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_CAL_KEY);
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (WS_FOUND_FLG != 'N') {
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            CEP.TRC(SCCGWA, WS_CAL_KEY);
            IBS.CPY2CLS(SCCGWA, BPRPARM.KEY.CODE, WS_CAL_KEY);
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_CAL_KEY.WS_KEY_CAL_CODE.equalsIgnoreCase(BPCOQCAL.CAL_CODE) 
                && WS_CAL_KEY.WS_KEY_YEAR >= Short.parseShort(JIBS_tmp_str[0].substring(0, 4))) {
                BPCSCALE.EFF_DATE = BPRPARM.EFF_DATE;
                IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPCSCALE.CAL_DETAIL);
                R000_EXCHANGE_SPE_CALENDAR();
                if (pgmRtn) return;
                R000_GEN_CAL_DATA();
                if (pgmRtn) return;
                R000_UPDATE_CALENDAR();
                if (pgmRtn) return;
            }
            if (!WS_CAL_KEY.WS_KEY_CAL_CODE.equalsIgnoreCase(BPCOQCAL.CAL_CODE)) {
                WS_FOUND_FLG = 'N';
            }
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void R000_EXCHANGE_SPE_CALENDAR() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            if (BPCBCHDY.HOL_TXT.HOL_DATA[WS_I-1].HOL_TPY == '1') {
            }
        }
    }
    public void R000_GEN_CAL_DATA() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = WS_CAL_KEY.WS_KEY_YEAR;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
            R000_GET_MONTH_INFO();
            if (pgmRtn) return;
            BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_WEEK_NO;
            for (WS_II = 1; WS_II <= WS_GET_DAYS_DATA.WS_MONTH_DAYS; WS_II += 1) {
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = WS_II;
                R000_SET_DAY_TYPE();
                if (pgmRtn) return;
                WS_WEEK_NO += 1;
                if (WS_WEEK_NO > 7) {
                    WS_WEEK_NO = 1;
                }
            }
        }
    }
    public void R000_UPDATE_CALENDAR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = K_PARM_TYPE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CAL_KEY);
        BPCPRMM.EFF_DT = BPCSCALE.EFF_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_OD_CAL_DETAIL);
        WS_TXN_HIS_DATA.WS_OLD_NAME = BPRPRMT.DESC;
        BPCSCALE.CAL_NAME = BPRPRMT.DESC;
        WS_TXN_HIS_DATA.WS_OLD_EFF_DATE = BPCPRMM.EFF_DT;
        BPCSCALE.EFF_DATE = BPCPRMM.EFF_DT;
        WS_TXN_HIS_DATA.WS_OLD_EXP_DATE = BPCPRMM.EXP_DT;
        BPCSCALE.EXP_DATE = BPCPRMM.EXP_DT;
        WS_MON_DIFF_FLG = 'N';
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OD_CAL_DETAIL.WS_OD_MONTH[WS_I-1]);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1])) {
                WS_MON_DIFF_FLG = 'Y';
                WS_MON = WS_I;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TXN_HIS_DATA.WS_NEW_MONTH);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OD_CAL_DETAIL.WS_OD_MONTH[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TXN_HIS_DATA.WS_OLD_MONTH);
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
        }
        if (WS_MON_DIFF_FLG == 'N') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        BPCPRMM.FUNC = '2';
        BPCSCALE.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSCALE.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPRPRMT.DESC = BPCSCALE.CAL_NAME;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        WS_AUTOGEN_FLG = 'U';
        R000_AUTOGEN_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_GET_MONTH_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
        SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_RC_MMO = "SC";
            WS_ERR_MSG.WS_RC_CODE = SCCCKDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_GET_DAYS_DATA.WS_MONTH_DAYS = SCCCKDT.MTH_DAYS;
            WS_WEEK_NO = SCCCKDT.WEEK_NO;
        }
    }
    public void R000_SET_DAY_TYPE() throws IOException,SQLException,Exception {
        WS_HOL_FLG = 'N';
        for (WS_J = 1; WS_J <= 50; WS_J += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1).equalsIgnoreCase(BPCBCHDY.HOL_TXT.HOL_DATA[WS_J-1].HOL_DT) 
                && JIBS_tmp_str[2].compareTo(BPCBCHDY.HOL_TXT.HOL_EFF_DT) >= 0) {
                CEP.TRC(SCCGWA, BPCBCHDY.HOL_TXT.HOL_DATA[WS_J-1].HOL_DT);
                CEP.TRC(SCCGWA, BPCBCHDY.HOL_TXT.HOL_DATA[WS_J-1].HOL_OPT);
                WS_HOL_FLG = 'Y';
                if (BPCBCHDY.HOL_TXT.HOL_DATA[WS_J-1].HOL_OPT == 'A') {
                    BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
                }
                if (BPCBCHDY.HOL_TXT.HOL_DATA[WS_J-1].HOL_OPT == 'D') {
                    WS_HOL_FLG = 'N';
                    BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'W';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_HOL_FLG);
        for (WS_J = 1; WS_J <= 7; WS_J += 1) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            if (WS_WEEK_NO == WS_J 
                && BPCBCHDY.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND == 1 
                && JIBS_tmp_str[1].compareTo(BPCBCHDY.WEEKND_TXT.WEEKND_EFF_DT) >= 0) {
                CEP.TRC(SCCGWA, WS_WEEK_NO);
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, WS_HOL_FLG);
                CEP.TRC(SCCGWA, BPCBCHDY.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND);
                CEP.TRC(SCCGWA, BPCBCHDY.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND_OPT);
                if (BPCBCHDY.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND_OPT == 'A') {
                    BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
                }
                if (BPCBCHDY.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND_OPT == 'D' 
                    && WS_HOL_FLG == 'N') {
                    BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'W';
                }
                if (BPCBCHDY.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND_OPT == 'N' 
                    && WS_HOL_FLG == 'N') {
                    BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
                }
            }
        }
    }
    public void R000_AUTOGEN_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALR);
        BPRCALR.BASE_CODE = BPCSCALE.CAL_CODE;
        BPRCALR.BASE_YEAR = BPCSCALE.YEAR;
        IBS.init(SCCGWA, BPCTCALR);
        BPCTCALR.INFO.FUNC = 'B';
        BPCTCALR.INFO.OPT = 'S';
        BPCTCALR.INFO.INDEX_FLG = '2';
        S000_CALL_BPZTCALR();
        if (pgmRtn) return;
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPCTCALR);
            BPCTCALR.INFO.FUNC = 'B';
            BPCTCALR.INFO.OPT = 'N';
            S000_CALL_BPZTCALR();
            if (pgmRtn) return;
            if (BPCTCALR.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPRCALR.BASE_CODE.equalsIgnoreCase(BPCSCALE.CAL_CODE) 
                    && BPRCALR.BASE_YEAR == BPCSCALE.YEAR) {
                    if (WS_AUTOGEN_FLG == 'C') {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DELETE_AUTOGEN, WS_ERR_MSG);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        IBS.init(SCCGWA, BPCTCALR);
                        BPCTCALR.INFO.FUNC = 'R';
                        S000_CALL_BPZTCALR();
                        if (pgmRtn) return;
                        BPRCALR.LAST_BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        IBS.init(SCCGWA, BPCTCALR);
                        BPCTCALR.INFO.FUNC = 'U';
                        S000_CALL_BPZTCALR();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_STOP_FLG = 'Y';
                }
            }
        }
        IBS.init(SCCGWA, BPCTCALR);
        BPCTCALR.INFO.FUNC = 'B';
        BPCTCALR.INFO.OPT = 'E';
        S000_CALL_BPZTCALR();
        if (pgmRtn) return;
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_CODE = BPCSCALE.CAL_CODE;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_YEAR = BPCSCALE.YEAR;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_MON = WS_MON;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_HIS_REF);
        IBS.init(SCCGWA, BPCOHCAL);
        BPCOHCAL.NAME = WS_TXN_HIS_DATA.WS_OLD_NAME;
        BPCOHCAL.EFF_DATE = WS_TXN_HIS_DATA.WS_OLD_EFF_DATE;
        BPCOHCAL.EXP_DATE = WS_TXN_HIS_DATA.WS_OLD_EXP_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_OLD_MONTH);
