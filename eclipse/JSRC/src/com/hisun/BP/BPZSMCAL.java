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

public class BPZSMCAL {
    boolean pgmRtn = false;
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_CMP_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String K_CMP_MAIN_BPTCALR = "BP-R-MAINT-CALR";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "CALENDER MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHCAL";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PARM_TYPE = "CALEN";
    String K_MONTH_NAME = "JANFEBMARAPRMAYJUNJULAUGSEPOCTNOVDEC";
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    BPZSMCAL_WS_RC WS_RC = new BPZSMCAL_WS_RC();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    short WS_IDX = 0;
    BPZSMCAL_WS_GET_DAYS_DATA WS_GET_DAYS_DATA = new BPZSMCAL_WS_GET_DAYS_DATA();
    String WS_BANK_CAL_CODE = " ";
    BPZSMCAL_WS_CAL_KEY WS_CAL_KEY = new BPZSMCAL_WS_CAL_KEY();
    BPZSMCAL_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMCAL_WS_OUTPUT_DATA();
    BPZSMCAL_WS_MONTH_INFO WS_MONTH_INFO = new BPZSMCAL_WS_MONTH_INFO();
    BPZSMCAL_WS_IN_CAL_DETAIL WS_IN_CAL_DETAIL = new BPZSMCAL_WS_IN_CAL_DETAIL();
    BPZSMCAL_WS_TXN_HIS_DATA WS_TXN_HIS_DATA = new BPZSMCAL_WS_TXN_HIS_DATA();
    char WS_DAY_TYP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    char WS_AUTOGEN_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCQ01 BPCQ01 = new BPCQ01();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCTCALR BPCTCALR = new BPCTCALR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHCAL BPCOHCAL = new BPCHCAL();
    BPCHCAL BPCNHCAL = new BPCHCAL();
    BPRCALR BPRCALR = new BPRCALR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    BPCSMCAL BPCSMCAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMCAL BPCSMCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMCAL = BPCSMCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMCAL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_RC);
        WS_RC.WS_RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMCAL);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMCAL.FUNC == 'Q'
            || BPCSMCAL.FUNC == 'A'
            || BPCSMCAL.FUNC == 'U'
            || BPCSMCAL.FUNC == 'C'
            || BPCSMCAL.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMCAL.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMCAL.FUNC == 'I') {
            B230_INITIALIZE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSMCAL.OUTPUT_FLG == 'Y' 
            || BPCSMCAL.OUTPUT_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMCAL.FUNC == 'Q' 
            || BPCSMCAL.FUNC == 'C' 
            || BPCSMCAL.FUNC == 'I') {
            if (BPCSMCAL.CAL_CODE.trim().length() == 0 
                || BPCSMCAL.YEAR == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMCAL.FUNC == 'I') {
                R000_CHECK_CAL_CODE();
                if (pgmRtn) return;
            }
        }
        if (BPCSMCAL.FUNC == 'A' 
            || BPCSMCAL.FUNC == 'U') {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCSMCAL.CAL_DETAIL);
            if (BPCSMCAL.CAL_CODE.trim().length() == 0 
                || BPCSMCAL.CAL_NAME.trim().length() == 0 
                || BPCSMCAL.YEAR == 0 
                || BPCSMCAL.EFF_DATE == 0 
                || BPCSMCAL.EXP_DATE == 0 
                || JIBS_tmp_str[1].trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_CHECK_CAL_CODE();
            if (pgmRtn) return;
            R000_CHECK_DETAIL_INFO();
            if (pgmRtn) return;
        }
        if (BPCSMCAL.FUNC == 'D') {
            WS_AUTOGEN_FLG = 'C';
            R000_AUTOGEN_DATA_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMCAL.FUNC == 'B') {
            if (BPCSMCAL.YEAR == 0 
                && BPCSMCAL.CAL_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCALN);
        IBS.init(SCCGWA, BPRCALN);
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCSMCAL.CAL_CODE;
        BPRCALN.KEY.YEAR = BPCSMCAL.YEAR;
        BPRCALN.EFF_DATE = BPCSMCAL.EFF_DATE;
        BPRCALN.EXP_DATE = BPCSMCAL.EXP_DATE;
        CEP.TRC(SCCGWA, BPRCALN.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCALN.EXP_DATE);
        BPRCALN.CDESC = BPCSMCAL.CAL_NAME;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMCAL.CAL_DETAIL);
        BPRCALN.DATA = JIBS_tmp_str[0];
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_IN_CAL_DETAIL);
        BPRCALN.UPD_DT = BPCSMCAL.CAL_DETAIL.LST_DATE;
        WS_IN_CAL_DETAIL.WS_IN_LST_DATE = BPCSMCAL.CAL_DETAIL.LST_DATE;
        BPRCALN.UPD_TLR = BPCSMCAL.CAL_DETAIL.LST_TLT;
        WS_IN_CAL_DETAIL.WS_IN_LST_TLT = BPCSMCAL.CAL_DETAIL.LST_TLT;
        if (BPCSMCAL.FUNC == 'Q'
            || BPCSMCAL.FUNC == 'C') {
            BPCRCALN.INFO.FUNC = 'R';
        } else if (BPCSMCAL.FUNC == 'A') {
            BPCRCALN.INFO.FUNC = 'C';
        } else if (BPCSMCAL.FUNC == 'U'
            || BPCSMCAL.FUNC == 'D') {
            BPCRCALN.INFO.FUNC = 'U';
        }
        if (BPCSMCAL.FUNC == 'A') {
            BPCSMCAL.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSMCAL.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        R000_CHECK_RETURN();
        if (pgmRtn) return;
        if (BPCSMCAL.FUNC == 'U' 
            || BPCSMCAL.FUNC == 'D') {
            if (BPCSMCAL.FUNC == 'D') {
                BPCRCALN.INFO.FUNC = 'D';
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
            }
            if (BPCSMCAL.FUNC == 'U') {
                BPCRCALN.INFO.FUNC = 'W';
                if (BPRCALN.DATA == null) BPRCALN.DATA = "";
                JIBS_tmp_int = BPRCALN.DATA.length();
                for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
                IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPCSMCAL.CAL_DETAIL);
                BPCSMCAL.CAL_DETAIL.LST_DATE = BPRCALN.UPD_DT;
                BPCSMCAL.CAL_DETAIL.LST_TLT = BPRCALN.UPD_TLR;
                WS_I = BPCSMCAL.MON_NO;
                WS_TXN_HIS_DATA.WS_OLD_NAME = BPRCALN.CDESC;
                WS_TXN_HIS_DATA.WS_OLD_EFF_DATE = BPRCALN.EFF_DATE;
                WS_TXN_HIS_DATA.WS_OLD_EXP_DATE = BPRCALN.EXP_DATE;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TXN_HIS_DATA.WS_OLD_MONTH);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_IN_CAL_DETAIL.WS_IN_MONTH[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TXN_HIS_DATA.WS_NEW_MONTH);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_IN_CAL_DETAIL.WS_IN_MONTH[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1]);
                BPCSMCAL.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSMCAL.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
                BPRCALN.EFF_DATE = BPCSMCAL.EFF_DATE;
                BPRCALN.EXP_DATE = BPCSMCAL.EXP_DATE;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMCAL.CAL_DETAIL);
                BPRCALN.DATA = JIBS_tmp_str[0];
                BPRCALN.UPD_DT = BPCSMCAL.CAL_DETAIL.LST_DATE;
                BPRCALN.UPD_TLR = BPCSMCAL.CAL_DETAIL.LST_TLT;
                BPRCALN.CDESC = BPCSMCAL.CAL_NAME;
                R000_PARM_DATA_PROCESS();
                if (pgmRtn) return;
                WS_AUTOGEN_FLG = 'U';
                R000_AUTOGEN_DATA_PROCESS();
                if (pgmRtn) return;
            }
        }
        if (BPRCALN.DATA == null) BPRCALN.DATA = "";
        JIBS_tmp_int = BPRCALN.DATA.length();
        for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
        IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPCSMCAL.CAL_DETAIL);
        BPCSMCAL.CAL_DETAIL.LST_DATE = BPRCALN.UPD_DT;
        BPCSMCAL.CAL_DETAIL.LST_TLT = BPRCALN.UPD_TLR;
        BPCSMCAL.EFF_DATE = BPRCALN.EFF_DATE;
        BPCSMCAL.EXP_DATE = BPRCALN.EXP_DATE;
        BPCSMCAL.CAL_NAME = BPRCALN.CDESC;
        if (BPCSMCAL.FUNC == 'A' 
            || BPCSMCAL.FUNC == 'D' 
            || BPCSMCAL.FUNC == 'U') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            if (BPCSMCAL.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] == 0) {
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSMCAL.YEAR;
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
                WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
                R000_GET_MONTH_INFO();
                if (pgmRtn) return;
                BPCSMCAL.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            }
        }
        if (BPCSMCAL.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALN);
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCSMCAL.CAL_CODE;
        BPRCALN.KEY.YEAR = BPCSMCAL.YEAR;
        BPCRCALN.INFO.FUNC = 'S';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCSMCAL.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRCALN);
            BPCRCALN.INFO.FUNC = 'N';
            R000_PARM_DATA_PROCESS();
            if (pgmRtn) return;
            if (BPCRCALN.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                R000_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPCSMCAL.OUTPUT_FLG == 'Y' 
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
            SCCMPAG.MAX_COL_NO = 85;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_CAL_CODE = BPRCALN.KEY.CODE;
            WS_OUTPUT_DATA.WS_OT_YEAR = BPRCALN.KEY.YEAR;
            WS_OUTPUT_DATA.WS_OT_CAL_NAME = BPRCALN.CDESC;
            WS_OUTPUT_DATA.WS_OT_EFF_DATE = BPRCALN.EFF_DATE;
            WS_OUTPUT_DATA.WS_OT_EXP_DATE = BPRCALN.EXP_DATE;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 85;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B230_INITIALIZE_PROCESS() throws IOException,SQLException,Exception {
        R000_GET_BANK_CAL_CODE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRCALN);
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCSMCAL.CAL_CODE;
        BPRCALN.KEY.YEAR = BPCSMCAL.YEAR;
        BPRCALN.EFF_DATE = BPCSMCAL.EFF_DATE;
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
            IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPCSMCAL.CAL_DETAIL);
            BPCSMCAL.CAL_DETAIL.LST_DATE = BPRCALN.UPD_DT;
            BPCSMCAL.CAL_DETAIL.LST_TLT = BPRCALN.UPD_TLR;
        }
        BPCSMCAL.CAL_DETAIL.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSMCAL.CAL_DETAIL.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMCAL.CAL_DETAIL);
        BPRCALN.DATA = JIBS_tmp_str[0];
        BPRCALN.UPD_DT = BPCSMCAL.CAL_DETAIL.LST_DATE;
        BPRCALN.UPD_TLR = BPCSMCAL.CAL_DETAIL.LST_TLT;
        BPRCALN.EFF_DATE = BPCSMCAL.EFF_DATE;
        BPRCALN.EXP_DATE = BPCSMCAL.EXP_DATE;
        BPRCALN.KEY.CODE = BPCSMCAL.CAL_CODE;
        BPCRCALN.INFO.FUNC = 'C';
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCRCALN.RETURN_INFO == 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_ALREADY_EXIST, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_TXN_HIS_PROCESS();
        if (pgmRtn) return;
        if (BPCSMCAL.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B231_GEN_CAL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCAL.CAL_DETAIL);
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSMCAL.YEAR;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
            R000_GET_MONTH_INFO();
            if (pgmRtn) return;
            BPCSMCAL.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            for (WS_II = 1; WS_II <= WS_GET_DAYS_DATA.WS_MONTH_DAYS; WS_II += 1) {
                R000_SET_DAY_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_DETAIL_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSMCAL.YEAR;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
            R000_GET_MONTH_INFO();
            if (pgmRtn) return;
            BPCSMCAL.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            if (BPCSMCAL.MON_NO == 0 
                || (BPCSMCAL.MON_NO != 0 
                && BPCSMCAL.MON_NO == WS_I)) {
                for (WS_II = 1; WS_II <= 31; WS_II += 1) {
                    WS_DAY_TYP_FLG = BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_II-1];
                    if ((WS_DAY_TYP_FLG != 'W' 
                        && WS_DAY_TYP_FLG != 'S' 
                        && WS_DAY_TYP_FLG != 'H')) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_II-1] == ' ') {
                        if (WS_II < WS_GET_DAYS_DATA.WS_MONTH_DAYS) {
                            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_RC);
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
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
    public void R000_CHECK_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCCAL);
        BPCOCCAL.CAL_CODE = BPCSMCAL.CAL_CODE;
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
        BPCQ01.2.2_CALCD = BPCSMCAL.CAL_CODE;
        BPCQ01.2.2_CALNE = BPCSMCAL.CAL_NAME;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.2);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.3.3_YEAR = BPCSMCAL.YEAR;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.3);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSMCAL.EFF_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.11.11_EFF_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.11.11_EFF_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.11.11_EFF_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        IBS.CPY2CLS(SCCGWA, BPCSMCAL.EXP_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.11.11_EXP_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.11.11_EXP_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.11.11_EXP_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.11);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSMCAL.CAL_DETAIL.LST_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.12.12_LST_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.12.12_LST_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.12.12_LST_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        BPCQ01.12.12_LST_TLT = BPCSMCAL.CAL_DETAIL.LST_TLT;
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
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
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
            if (BPCSMCAL.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] == 7) {
                WS_IDX = 1;
            } else {
                WS_IDX = (short) (BPCSMCAL.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] + 1);
            }
            WS_III = 1;
            WS_STOP_FLG = 'N';
            while (WS_STOP_FLG != 'Y') {
                IBS.init(SCCGWA, BPCQ01.9);
                IBS.init(SCCGWA, BPCQ01.10);
                for (WS_II = WS_IDX; (WS_II <= 21) 
                    && WS_STOP_FLG != 'Y'; WS_II += 1) {
                    if (BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_III-1] != ' ') {
                        JIBS_tmp_str[0] = "" + WS_III;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        BPCQ01.9.9_DAY_INFO[WS_II-1].9_DT = JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1);
                        BPCQ01.10.10_DAY_INFO[WS_II-1].10_TYP = BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_III-1];
                    }
                    if (BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_III-1] == ' ' 
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
        BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'W';
        if ((WS_GET_DAYS_DATA.WS_WEEK_NO == 6 
            || WS_GET_DAYS_DATA.WS_WEEK_NO == 7)) {
            BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
        }
        if (WS_I == 5 
            || WS_I == 10) {
            if (WS_II == 1 
                || WS_II == 2 
                || WS_II == 3) {
                BPCSMCAL.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_II-1] = 'H';
            }
        }
        WS_GET_DAYS_DATA.WS_WEEK_NO += 1;
        if (WS_GET_DAYS_DATA.WS_WEEK_NO > 7) {
            WS_GET_DAYS_DATA.WS_WEEK_NO = 1;
        }
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_CAL_KEY.WS_KEY_CAL_CODE = BPRCALN.KEY.CODE;
        WS_CAL_KEY.WS_KEY_YEAR = BPRCALN.KEY.YEAR;
        WS_FIT_COND_FLG = 'Y';
        if (BPCSMCAL.CAL_CODE.trim().length() > 0 
            && !BPCSMCAL.CAL_CODE.equalsIgnoreCase(WS_CAL_KEY.WS_KEY_CAL_CODE)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSMCAL.YEAR != 0 
            && BPCSMCAL.YEAR != WS_CAL_KEY.WS_KEY_YEAR) {
            WS_FIT_COND_FLG = 'N';
        }
    }
    public void R000_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCRCALN.RETURN_INFO == 'N') {
            if (BPCSMCAL.FUNC == 'Q' 
                || BPCSMCAL.FUNC == 'U' 
                || BPCSMCAL.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_NOTFND, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMCAL.FUNC == 'C') {
                BPCSMCAL.CHECK_RESULT = 'N';
            }
        }
        if (BPCRCALN.RETURN_INFO == 'F') {
            if (BPCSMCAL.FUNC == 'A') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_ALREADY_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMCAL.FUNC == 'C') {
                BPCSMCAL.CHECK_RESULT = 'E';
            }
        }
    }
    public void R000_AUTOGEN_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALR);
        BPRCALR.BASE_CODE = BPCSMCAL.CAL_CODE;
        BPRCALR.BASE_YEAR = BPCSMCAL.YEAR;
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
                if (BPRCALR.BASE_CODE.equalsIgnoreCase(BPCSMCAL.CAL_CODE) 
                    && BPRCALR.BASE_YEAR == BPCSMCAL.YEAR) {
                    if (WS_AUTOGEN_FLG == 'C') {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DELETE_AUTOGEN, WS_RC);
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
        if (BPCSMCAL.FUNC == 'A' 
            || BPCSMCAL.FUNC == 'I') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMCAL.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSMCAL.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_CODE = BPCSMCAL.CAL_CODE;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_YEAR = BPCSMCAL.YEAR;
        if (BPCSMCAL.FUNC == 'U') {
            WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_MON = BPCSMCAL.MON_NO;
        }
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_HIS_REF);
        if (BPCSMCAL.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHCAL);
            BPCOHCAL.NAME = WS_TXN_HIS_DATA.WS_OLD_NAME;
            BPCOHCAL.EFF_DATE = WS_TXN_HIS_DATA.WS_OLD_EFF_DATE;
            BPCOHCAL.EXP_DATE = WS_TXN_HIS_DATA.WS_OLD_EXP_DATE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_OLD_MONTH);
