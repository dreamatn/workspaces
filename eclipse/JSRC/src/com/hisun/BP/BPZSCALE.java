package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSCALE {
    boolean pgmRtn = false;
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_CMP_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String K_CMP_CALL_BPZITHOL = "BP-I-INQ-THOL    ";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "CALENDER MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHCAL";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PARM_TYPE = "CALEN";
    String K_MONTH_NAME = "JANFEBMARAPRMAYJUNJULAUGSEPOCTNOVDEC";
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    BPZSCALE_WS_RC WS_RC = new BPZSCALE_WS_RC();
    short WS_K = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_II = 0;
    short WS_III = 0;
    short WS_IDX = 0;
    short WS_MON = 0;
    int WS_NEXT_WORK_DAY = 0;
    int WS_COMP_DATE = 0;
    BPZSCALE_WS_GET_DAYS_DATA WS_GET_DAYS_DATA = new BPZSCALE_WS_GET_DAYS_DATA();
    String WS_CAL_CODE = " ";
    BPZSCALE_WS_CAL_KEY WS_CAL_KEY = new BPZSCALE_WS_CAL_KEY();
    BPZSCALE_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSCALE_WS_OUTPUT_DATA();
    BPZSCALE_WS_MONTH_INFO WS_MONTH_INFO = new BPZSCALE_WS_MONTH_INFO();
    BPZSCALE_WS_OD_CAL_DETAIL WS_OD_CAL_DETAIL = new BPZSCALE_WS_OD_CAL_DETAIL();
    BPZSCALE_WS_TXN_HIS_DATA WS_TXN_HIS_DATA = new BPZSCALE_WS_TXN_HIS_DATA();
    char WS_DAY_TYP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    char WS_AUTOGEN_FLG = ' ';
    char WS_MON_DIFF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCQ01 BPCQ01 = new BPCQ01();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHCAL BPCOHCAL = new BPCHCAL();
    BPCHCAL BPCNHCAL = new BPCHCAL();
    BPCITHOL BPCITHOL = new BPCITHOL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    BPCSCALE BPCSCALE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCALE BPCSCALE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCALE = BPCSCALE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCALE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_RC);
        IBS.init(SCCGWA, BPCOQCAL);
        WS_RC.WS_RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCALE);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSCALE.FUNC == 'Q'
            || BPCSCALE.FUNC == 'A'
            || BPCSCALE.FUNC == 'U'
            || BPCSCALE.FUNC == 'C'
            || BPCSCALE.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCALE.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCALE.FUNC == 'I') {
            B230_INITIALIZE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCALE.OUTPUT_FLG);
        if (!(BPCSCALE.OUTPUT_FLG == 'Y' 
            || BPCSCALE.OUTPUT_FLG == 'N')) {
            CEP.TRC(SCCGWA, "1111111");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSCALE.CAL_CODE);
        CEP.TRC(SCCGWA, BPCSCALE.YEAR);
        CEP.TRC(SCCGWA, "YWSSHURU");
        if (BPCSCALE.FUNC == 'Q' 
            || BPCSCALE.FUNC == 'C' 
            || BPCSCALE.FUNC == 'I') {
            if (BPCSCALE.CAL_CODE.trim().length() == 0 
                || BPCSCALE.YEAR == 0) {
                CEP.TRC(SCCGWA, "2222222");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSCALE.FUNC == 'A' 
            && (BPCSCALE.YEAR == 0 
            || BPCSCALE.EFF_DATE == 0 
            || BPCSCALE.EXP_DATE == 0) 
            && (BPCSCALE.CAL_CODE.trim().length() == 0 
            && BPCSCALE.CAL_DETAIL.CNTYS_CD.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "33333333");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
        if (BPCSCALE.FUNC == 'U') {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL);
            if (BPCSCALE.CAL_CODE.trim().length() == 0 
                || BPCSCALE.YEAR == 0 
                || BPCSCALE.EFF_DATE == 0 
                || BPCSCALE.EXP_DATE == 0 
                || JIBS_tmp_str[1].trim().length() == 0) {
                CEP.TRC(SCCGWA, "44444444");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_CHECK_DETAIL_INFO();
            if (pgmRtn) return;
        }
        if (BPCSCALE.FUNC == 'B') {
            WS_OUTPUT_DATA.WS_OT_CNTYS_CD = BPCSCALE.CAL_DETAIL.CNTYS_CD;
            WS_OUTPUT_DATA.WS_OT_CITY_CD = BPCSCALE.CAL_DETAIL.CITY_CD;
            WS_OUTPUT_DATA.WS_OT_CAL_CODE = BPCSCALE.CAL_CODE;
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, K_PARM_TYPE);
        CEP.TRC(SCCGWA, BPCSCALE.CAL_CODE);
        CEP.TRC(SCCGWA, BPCSCALE.YEAR);
        CEP.TRC(SCCGWA, WS_CAL_KEY);
        CEP.TRC(SCCGWA, BPCSCALE.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSCALE.EXP_DATE);
        IBS.init(SCCGWA, BPCRCALN);
        IBS.init(SCCGWA, BPRCALN);
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCSCALE.CAL_CODE;
        BPRCALN.KEY.YEAR = BPCSCALE.YEAR;
        BPRCALN.EFF_DATE = BPCSCALE.EFF_DATE;
        BPRCALN.EXP_DATE = BPCSCALE.EXP_DATE;
        CEP.TRC(SCCGWA, BPRCALN.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCALN.EXP_DATE);
        BPRCALN.CDESC = BPCSCALE.CAL_NAME;
        BPRCALN.DATA = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL.CALENDAR);
        BPRCALN.CNTY_CODE = BPCSCALE.CAL_DETAIL.CNTYS_CD;
        BPRCALN.CITY_CODE = BPCSCALE.CAL_DETAIL.CITY_CD;
        BPRCALN.UPD_DT = BPCSCALE.CAL_DETAIL.LST_DATE;
        BPRCALN.UPD_TLR = BPCSCALE.CAL_DETAIL.LST_TLT;
        if (BPCSCALE.FUNC == 'Q'
            || BPCSCALE.FUNC == 'C') {
            BPCRCALN.INFO.FUNC = 'R';
        } else if (BPCSCALE.FUNC == 'A') {
            BPCRCALN.INFO.FUNC = 'C';
        } else if (BPCSCALE.FUNC == 'U'
            || BPCSCALE.FUNC == 'D') {
            BPCRCALN.INFO.FUNC = 'U';
        }
        CEP.TRC(SCCGWA, "START  MAINTIAN");
        if (BPCSCALE.FUNC == 'A') {
            R000_GET_HOLIDAY();
            if (pgmRtn) return;
            for (BPCSCALE.YEAR = BPCSCALE.YEAR; BPCSCALE.YEAR <= BPCSCALE.END_YEAR; BPCSCALE.YEAR += 1) {
                CEP.TRC(SCCGWA, BPCSCALE.YEAR);
                B230_INITIALIZE_PROCESS();
                if (pgmRtn) return;
                BPCRCALN.INFO.FUNC = 'C';
                BPCSCALE.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSCALE.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
                BPRCALN.CDESC = BPCSCALE.CAL_NAME;
                BPRCALN.DATA = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL.CALENDAR);
                BPRCALN.CNTY_CODE = BPCSCALE.CAL_DETAIL.CNTYS_CD;
                BPRCALN.CITY_CODE = BPCSCALE.CAL_DETAIL.CITY_CD;
                BPRCALN.UPD_DT = BPCSCALE.CAL_DETAIL.LST_DATE;
                BPRCALN.UPD_TLR = BPCSCALE.CAL_DETAIL.LST_TLT;
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
                R000_CHECK_RETURN();
                if (pgmRtn) return;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
        }
        if (BPCSCALE.FUNC == 'U' 
            || BPCSCALE.FUNC == 'D' 
            || BPCSCALE.FUNC == 'Q') {
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            R000_CHECK_RETURN();
            if (pgmRtn) return;
            if (BPCSCALE.FUNC == 'D') {
                BPCRCALN.INFO.FUNC = 'D';
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
            if (BPCSCALE.FUNC == 'U') {
                BPCRCALN.INFO.FUNC = 'W';
                if (BPRCALN.DATA == null) BPRCALN.DATA = "";
                JIBS_tmp_int = BPRCALN.DATA.length();
                for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
                IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), WS_OD_CAL_DETAIL);
                WS_OD_CAL_DETAIL.WS_CNTYS_CD = BPRCALN.CNTY_CODE;
                WS_OD_CAL_DETAIL.WS_CITY_CD = BPRCALN.CITY_CODE;
                WS_OD_CAL_DETAIL.WS_OD_LST_DATE = BPRCALN.UPD_DT;
                WS_OD_CAL_DETAIL.WS_OD_LST_TLT = BPRCALN.UPD_TLR;
                WS_TXN_HIS_DATA.WS_OLD_EFF_DATE = BPRCALN.EFF_DATE;
                WS_TXN_HIS_DATA.WS_OLD_EXP_DATE = BPRCALN.EXP_DATE;
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
                        for (WS_K = 1; WS_K <= 31; WS_K += 1) {
                            if (WS_TXN_HIS_DATA.WS_NEW_MONTH.WS_NEW_TYPE[WS_K-1] != WS_TXN_HIS_DATA.WS_OLD_MONTH.WS_OLD_TYPE[WS_K-1]) {
                                WS_COMP_DATE = BPCSCALE.YEAR * 10000 + WS_I * 100 + WS_K;
                                if (WS_COMP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BACK_DATE_CHANGE, WS_RC);
                                    S000_ERR_MSG_PROC();
                                    if (pgmRtn) return;
                                }
                                R000_GET_NEXT_WORK_DAY();
                                if (pgmRtn) return;
                                if (WS_COMP_DATE <= WS_NEXT_WORK_DAY) {
                                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHG_NEXT_WORKDAY_ERR, WS_RC);
                                    S000_ERR_MSG_PROC();
                                    if (pgmRtn) return;
                                }
                            }
                        }
                        R000_TXN_HIS_PROCESS();
                        if (pgmRtn) return;
                    }
                }
                if (WS_MON_DIFF_FLG == 'N') {
                    R000_TXN_HIS_PROCESS();
                    if (pgmRtn) return;
                }
                BPCSCALE.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSCALE.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
                BPRCALN.EFF_DATE = BPCSCALE.EFF_DATE;
                BPRCALN.EXP_DATE = BPCSCALE.EXP_DATE;
                BPRCALN.DATA = IBS.CLS2CPY(SCCGWA, BPCSCALE.CAL_DETAIL.CALENDAR);
                BPRCALN.CNTY_CODE = BPCSCALE.CAL_DETAIL.CNTYS_CD;
                BPRCALN.CITY_CODE = BPCSCALE.CAL_DETAIL.CITY_CD;
                BPRCALN.UPD_DT = BPCSCALE.CAL_DETAIL.LST_DATE;
                BPRCALN.UPD_TLR = BPCSCALE.CAL_DETAIL.LST_TLT;
                BPRCALN.CDESC = BPCSCALE.CAL_NAME;
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
            }
        }
        if (BPRCALN.DATA == null) BPRCALN.DATA = "";
        JIBS_tmp_int = BPRCALN.DATA.length();
        for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
        IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPCSCALE.CAL_DETAIL.CALENDAR);
        BPCSCALE.CAL_DETAIL.CNTYS_CD = BPRCALN.CNTY_CODE;
        BPCSCALE.CAL_DETAIL.CITY_CD = BPRCALN.CITY_CODE;
        BPCSCALE.CAL_DETAIL.LST_DATE = BPRCALN.UPD_DT;
        BPCSCALE.CAL_DETAIL.LST_TLT = BPRCALN.UPD_TLR;
        BPCSCALE.EFF_DATE = BPRCALN.EFF_DATE;
        BPCSCALE.EXP_DATE = BPRCALN.EXP_DATE;
        BPCSCALE.CAL_NAME = BPRCALN.CDESC;
        if (BPCSCALE.FUNC == 'A' 
            || BPCSCALE.FUNC == 'D' 
            || BPCSCALE.FUNC == 'U') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            if (BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] == 0) {
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSCALE.YEAR;
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
                R000_GET_MONTH_INFO();
                if (pgmRtn) return;
                BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            }
        }
        if (BPCSCALE.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCALE.CAL_DETAIL.CNTYS_CD.trim().length() > 0) {
            R000_GET_CITY_CAL_CODE();
            if (pgmRtn) return;
            BPCSCALE.CAL_CODE = BPCOQCAL.CAL_CODE;
            CEP.TRC(SCCGWA, BPCOQCAL.CAL_CODE);
        }
        IBS.init(SCCGWA, BPRCALN);
        IBS.init(SCCGWA, BPCRCALN);
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCSCALE.CAL_CODE;
        BPRCALN.KEY.YEAR = BPCSCALE.YEAR;
        BPCRCALN.INFO.FUNC = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCSCALE.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCRCALN);
            BPCRCALN.INFO.FUNC = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCRCALN.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, BPRCALN.KEY);
                R000_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPRCALN.DATA == null) BPRCALN.DATA = "";
                JIBS_tmp_int = BPRCALN.DATA.length();
                for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
                IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPCSCALE.CAL_DETAIL.CALENDAR);
                BPCSCALE.CAL_DETAIL.CNTYS_CD = BPRCALN.CNTY_CODE;
                BPCSCALE.CAL_DETAIL.CITY_CD = BPRCALN.CITY_CODE;
                BPCSCALE.CAL_DETAIL.LST_DATE = BPRCALN.UPD_DT;
                BPCSCALE.CAL_DETAIL.LST_TLT = BPRCALN.UPD_TLR;
                if (BPCSCALE.OUTPUT_FLG == 'Y' 
                    && WS_FIT_COND_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCRCALN);
        BPCRCALN.INFO.FUNC = 'E';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 95;
            SCCMPAG.SCR_ROW_CNT = 65;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSCALE.CAL_DETAIL.CNTYS_CD);
        CEP.TRC(SCCGWA, BPCSCALE.CAL_DETAIL.CITY_CD);
        CEP.TRC(SCCGWA, BPCSCALE.CAL_DETAIL);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_CAL_CODE = BPRCALN.KEY.CODE;
            WS_OUTPUT_DATA.WS_OT_YEAR = BPRCALN.KEY.YEAR;
            WS_OUTPUT_DATA.WS_OT_CAL_NAME = BPRCALN.DESC;
            WS_OUTPUT_DATA.WS_OT_EFF_DATE = BPRCALN.EFF_DATE;
            WS_OUTPUT_DATA.WS_OT_EXP_DATE = BPRCALN.EXP_DATE;
            WS_OUTPUT_DATA.WS_OT_CNTYS_CD = BPCSCALE.CAL_DETAIL.CNTYS_CD;
            WS_OUTPUT_DATA.WS_OT_CITY_CD = BPCSCALE.CAL_DETAIL.CITY_CD;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 95;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B230_INITIALIZE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCALN);
        IBS.init(SCCGWA, WS_CAL_KEY);
        if (BPCSCALE.CAL_CODE.trim().length() == 0) {
            if (BPCSCALE.CAL_DETAIL.CNTYS_CD.trim().length() == 0) {
                R000_GET_BANK_CAL_CODE();
                if (pgmRtn) return;
            } else {
                R000_GET_CITY_CAL_CODE();
                if (pgmRtn) return;
                WS_CAL_CODE = BPCOQCAL.CAL_CODE;
                CEP.TRC(SCCGWA, BPCOQCAL.CAL_CODE);
            }
        } else {
            WS_CAL_CODE = BPCSCALE.CAL_CODE;
        }
        CEP.TRC(SCCGWA, WS_CAL_CODE);
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCSCALE.CAL_CODE;
        BPRCALN.KEY.YEAR = BPCSCALE.YEAR;
        BPRCALN.EFF_DATE = BPCSCALE.EFF_DATE;
        BPRCALN.EXP_DATE = BPCSCALE.EXP_DATE;
        CEP.TRC(SCCGWA, BPRCALN.KEY);
        BPCRCALN.INFO.FUNC = 'R';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCRCALN.RETURN_INFO == 'N') {
            B231_GEN_CAL_DATA();
            if (pgmRtn) return;
        } else {
            if (BPRCALN.DATA == null) BPRCALN.DATA = "";
            JIBS_tmp_int = BPRCALN.DATA.length();
            for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
            IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPCSCALE.CAL_DETAIL.CALENDAR);
            BPCSCALE.CAL_DETAIL.CNTYS_CD = BPRCALN.CNTY_CODE;
            BPCSCALE.CAL_DETAIL.CITY_CD = BPRCALN.CITY_CODE;
            BPCSCALE.CAL_DETAIL.LST_DATE = BPRCALN.UPD_DT;
            BPCSCALE.CAL_DETAIL.LST_TLT = BPRCALN.UPD_TLR;
        }
        BPCSCALE.CAL_CODE = WS_CAL_CODE;
        CEP.TRC(SCCGWA, BPCSCALE.CAL_DETAIL);
        BPCSCALE.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSCALE.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B231_GEN_CAL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE.CAL_DETAIL.CALENDAR);
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSCALE.YEAR;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
            R000_GET_MONTH_INFO();
            if (pgmRtn) return;
            BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            for (WS_II = 1; WS_II <= WS_GET_DAYS_DATA.WS_MONTH_DAYS; WS_II += 1) {
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = WS_II;
                R000_SET_DAY_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_DETAIL_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSCALE.YEAR;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
            R000_GET_MONTH_INFO();
            if (pgmRtn) return;
            BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            for (WS_II = 1; WS_II <= 31; WS_II += 1) {
                WS_DAY_TYP_FLG = BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1];
                if ((WS_DAY_TYP_FLG != 'W' 
                    && WS_DAY_TYP_FLG != 'S' 
                    && WS_DAY_TYP_FLG != 'H')) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] == ' ') {
                    if (WS_II < WS_GET_DAYS_DATA.WS_MONTH_DAYS) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_GET_MONTH_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
        SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_RC.WS_RC_MMO = "SC";
            WS_RC.WS_RC_CODE = SCCCKDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_GET_DAYS_DATA.WS_MONTH_DAYS = SCCCKDT.MTH_DAYS;
            WS_GET_DAYS_DATA.WS_WEEK_NO = SCCCKDT.WEEK_NO;
        }
    }
    public void R000_GET_BANK_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '7';
        BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
    }
    public void R000_GET_SYSTEM_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '8';
        BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
    }
    public void R000_GET_CITY_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '9';
        BPCOQCAL.CITY_NO.CNTYS_CD = BPCSCALE.CAL_DETAIL.CNTYS_CD;
        BPCOQCAL.CITY_NO.CITY_CD = BPCSCALE.CAL_DETAIL.CITY_CD;
        S000_CALL_BPZPQCAL();
        if (pgmRtn) return;
    }
    public void R000_CHECK_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCCAL);
        BPCOCCAL.CAL_CODE = BPCSCALE.CAL_CODE;
        S000_CALL_CMP_CHECK_CAL();
        if (pgmRtn) return;
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQ01);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 10;
        SCCMPAG.MAX_COL_NO = 99;
        SCCMPAG.SCR_ROW_CNT = 46;
        SCCMPAG.SCR_COL_CNT = 99;
        B_MPAG();
        if (pgmRtn) return;
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        R000_OUTPUT_CALEN_DATA();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.6);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.2.2_CALCD = BPCSCALE.CAL_CODE;
        BPCQ01.2.2_CALNE = BPCSCALE.CAL_NAME;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.2);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.3.3_YEAR = BPCSCALE.YEAR;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.3);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSCALE.EFF_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.11.11_EFF_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.11.11_EFF_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.11.11_EFF_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        IBS.CPY2CLS(SCCGWA, BPCSCALE.EXP_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.11.11_EXP_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.11.11_EXP_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.11.11_EXP_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.11);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSCALE.CAL_DETAIL.LST_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.12.12_LST_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.12.12_LST_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.12.12_LST_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        BPCQ01.12.12_LST_TLT = BPCSCALE.CAL_DETAIL.LST_TLT;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.12);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.6);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.4);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.5);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.4);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.6);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_CALEN_DATA() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, K_MONTH_NAME, WS_MONTH_INFO);
        for (WS_I = 1; WS_I <= 12 
            && SCCMPAG.FUNC != 'E'; WS_I += 1) {
            BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.7);
            S000_WRITE_TSQ_OUTPUT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCQ01.8);
            BPCQ01.8.8_MON = WS_MONTH_INFO.WS_MONTH_NAME[WS_I-1];
            BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.8);
            S000_WRITE_TSQ_OUTPUT();
            if (pgmRtn) return;
            BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.7);
            S000_WRITE_TSQ_OUTPUT();
            if (pgmRtn) return;
            if (BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] == 7) {
                WS_IDX = 1;
            } else {
                WS_IDX = (short) (BPCSCALE.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] + 1);
            }
            WS_III = 1;
            WS_STOP_FLG = 'N';
            while (WS_STOP_FLG != 'Y') {
                IBS.init(SCCGWA, BPCQ01.9);
                IBS.init(SCCGWA, BPCQ01.10);
                for (WS_II = WS_IDX; (WS_II <= 21) 
                    && WS_STOP_FLG != 'Y'; WS_II += 1) {
                    if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_III-1] != ' ') {
                        JIBS_tmp_str[0] = "" + WS_III;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        BPCQ01.9.9_DAY_INFO[WS_II-1].9_DT = JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1);
                        BPCQ01.10.10_DAY_INFO[WS_II-1].10_TYP = BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_III-1];
                    }
                    if (BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_III-1] == ' ' 
                        || WS_III >= 31) {
                        WS_STOP_FLG = 'Y';
                    }
                    WS_III = (short) (WS_III + 1);
                }
                WS_IDX = 1;
                BPCQ01.REC = " ";
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQ01.9);
                BPCQ01.REC = JIBS_tmp_str[0];
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 38 - 1) + "   " + BPCQ01.REC.substring(38 + 3 - 1);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQ01.9);
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 41 - 1) + JIBS_tmp_str[0].substring(38 - 1, 38 + 28 - 1) + BPCQ01.REC.substring(41 + 28 - 1);
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 69 - 1) + "   " + BPCQ01.REC.substring(69 + 3 - 1);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQ01.9);
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 72 - 1) + JIBS_tmp_str[0].substring(66 - 1, 66 + 28 - 1) + BPCQ01.REC.substring(72 + 28 - 1);
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = " ";
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQ01.10);
                BPCQ01.REC = JIBS_tmp_str[0];
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 38 - 1) + "   " + BPCQ01.REC.substring(38 + 3 - 1);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQ01.10);
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 41 - 1) + JIBS_tmp_str[0].substring(38 - 1, 38 + 28 - 1) + BPCQ01.REC.substring(41 + 28 - 1);
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 69 - 1) + "   " + BPCQ01.REC.substring(69 + 3 - 1);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQ01.10);
                if (BPCQ01.REC == null) BPCQ01.REC = "";
                JIBS_tmp_int = BPCQ01.REC.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) BPCQ01.REC += " ";
                BPCQ01.REC = BPCQ01.REC.substring(0, 72 - 1) + JIBS_tmp_str[0].substring(66 - 1, 66 + 28 - 1) + BPCQ01.REC.substring(72 + 28 - 1);
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.6);
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_SET_DAY_TYPE() throws IOException,SQLException,Exception {
        BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'W';
        for (WS_J = 1; WS_J <= 50; WS_J += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1).equalsIgnoreCase(BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_DATA[WS_J-1].HOL_DT) 
                && (JIBS_tmp_str[1].compareTo(BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_EFF_DT) > 0 
                && (JIBS_tmp_str[2].compareTo(BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_EFF_DT_2) < 0 
                || BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_EFF_DT_2 == 0)) 
                && BPCITHOL.OUPUT_DAT.HOL_TXT.HOL_DATA[WS_J-1].HOL_TPY == '0') {
                BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1).equalsIgnoreCase(BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_DATA_2[WS_J-1].HOL_DT_2) 
                && JIBS_tmp_str[2].compareTo(BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_EFF_DT_2) > 0 
                && BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_DATA_2[WS_J-1].HOL_TPY_2 == '0') {
                BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
            }
        }
        for (WS_J = 1; WS_J <= 7; WS_J += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            if (WS_GET_DAYS_DATA.WS_WEEK_NO == WS_J 
                && BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND == 0 
                && (JIBS_tmp_str[0].compareTo(BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_EFF_DT) > 0 
                && (JIBS_tmp_str[1].compareTo(BPCITHOL.OUPUT_DAT_2.WEEKND_TXT_2.WEEKND_EFF_DT_2) < 0 
                || BPCITHOL.OUPUT_DAT_2.HOL_TXT_2.HOL_EFF_DT_2 == 0))) {
                BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
            }
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_GET_DAYS_DATA.WS_INPUT_DATE);
            if (WS_GET_DAYS_DATA.WS_WEEK_NO == WS_J 
                && BPCITHOL.OUPUT_DAT.WEEKND_TXT.WEEKND_DATA[WS_J-1].WEEKND == 0 
                && JIBS_tmp_str[1].compareTo(BPCITHOL.OUPUT_DAT_2.WEEKND_TXT_2.WEEKND_EFF_DT_2) > 0) {
                BPCSCALE.CAL_DETAIL.CALENDAR.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
            }
        }
        WS_GET_DAYS_DATA.WS_WEEK_NO += 1;
        if (WS_GET_DAYS_DATA.WS_WEEK_NO > 7) {
            WS_GET_DAYS_DATA.WS_WEEK_NO = 1;
        }
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_FIT_COND_FLG = 'Y';
        CEP.TRC(SCCGWA, BPRCALN.KEY);
        CEP.TRC(SCCGWA, BPCSCALE.CAL_CODE);
        CEP.TRC(SCCGWA, BPCSCALE.YEAR);
        if (BPCSCALE.CAL_CODE.trim().length() > 0 
            && !BPCSCALE.CAL_CODE.equalsIgnoreCase(BPRCALN.KEY.CODE)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSCALE.YEAR != 0 
            && BPCSCALE.YEAR != BPRCALN.KEY.YEAR) {
            WS_FIT_COND_FLG = 'N';
        }
    }
    public void R000_CHECK_RETURN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ZHAOBUDAO");
        if (BPCRCALN.RETURN_INFO == 'N') {
            if (BPCSCALE.FUNC == 'Q' 
                || BPCSCALE.FUNC == 'U' 
                || BPCSCALE.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_NOTFND, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSCALE.FUNC == 'C') {
                BPCSCALE.CHECK_RESULT = 'N';
            }
        }
        if (BPCRCALN.RETURN_INFO == 'F') {
            if (BPCSCALE.FUNC == 'A') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_ALREADY_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSCALE.FUNC == 'C') {
                BPCSCALE.CHECK_RESULT = 'E';
            }
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSCALE.FUNC == 'A' 
            || BPCSCALE.FUNC == 'I') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSCALE.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSCALE.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_CODE = BPCSCALE.CAL_CODE;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_YEAR = BPCSCALE.YEAR;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_MON = WS_MON;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_HIS_REF);
        if (BPCSCALE.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHCAL);
            BPCOHCAL.NAME = WS_TXN_HIS_DATA.WS_OLD_NAME;
            BPCOHCAL.EFF_DATE = WS_TXN_HIS_DATA.WS_OLD_EFF_DATE;
            BPCOHCAL.EXP_DATE = WS_TXN_HIS_DATA.WS_OLD_EXP_DATE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_OLD_MONTH);
