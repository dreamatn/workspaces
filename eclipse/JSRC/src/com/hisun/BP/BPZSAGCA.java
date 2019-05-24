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

public class BPZSAGCA {
    boolean pgmRtn = false;
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CALENDER";
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_CMP_MAIN_BPTCALR = "BP-R-MAINT-CALR";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "CALENDER MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHAGC";
    String K_MONTH_NAME = "JANFEBMARAPRMAYJUNJULAUGSEPOCTNOVDEC";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPZSAGCA_WS_RC WS_RC = new BPZSAGCA_WS_RC();
    short WS_K = 0;
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    short WS_IDX = 0;
    short WS_IND = 0;
    int WS_COMP_DATE = 0;
    BPZSAGCA_WS_GET_DAYS_DATA WS_GET_DAYS_DATA = new BPZSAGCA_WS_GET_DAYS_DATA();
    int WS_LAST_DATE = 0;
    BPZSAGCA_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSAGCA_WS_OUTPUT_DATA();
    BPZSAGCA_WS_MONTH_INFO WS_MONTH_INFO = new BPZSAGCA_WS_MONTH_INFO();
    short WS_MON_NO = 0;
    BPZSAGCA_WS_OD_CAL_DETAIL WS_OD_CAL_DETAIL = new BPZSAGCA_WS_OD_CAL_DETAIL();
    String WS_CAL_CODE = " ";
    String WS_CAL_NAME = " ";
    BPZSAGCA_WS_Q_REC[] WS_Q_REC = new BPZSAGCA_WS_Q_REC[2];
    BPZSAGCA_WS_TXN_HIS_DATA WS_TXN_HIS_DATA = new BPZSAGCA_WS_TXN_HIS_DATA();
    char WS_DAY_TYP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    char WS_WRITE_FLG = ' ';
    char WS_MON_DIFF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCQ01 BPCQ01 = new BPCQ01();
    BPCTCALR BPCTCALR = new BPCTCALR();
    BPCSCALE BPCSCALE = new BPCSCALE();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHAGC BPCOHAGC = new BPCHAGC();
    BPCHAGC BPCNHAGC = new BPCHAGC();
    BPRCALR BPRCALR = new BPRCALR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCSAGCA BPCSAGCA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSAGCA() {
        for (int i=0;i<2;i++) WS_Q_REC[i] = new BPZSAGCA_WS_Q_REC();
    }
    public void MP(SCCGWA SCCGWA, BPCSAGCA BPCSAGCA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSAGCA = BPCSAGCA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSAGCA return!");
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
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSAGCA.FUNC == 'Q'
            || BPCSAGCA.FUNC == 'A'
            || BPCSAGCA.FUNC == 'U'
            || BPCSAGCA.FUNC == 'C'
            || BPCSAGCA.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSAGCA.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSAGCA.OUTPUT_FLG == 'Y' 
            || BPCSAGCA.OUTPUT_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSAGCA.FUNC == 'Q' 
            || BPCSAGCA.FUNC == 'C') {
            if (BPCSAGCA.CAL_CODE.trim().length() == 0 
                || BPCSAGCA.YEAR == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSAGCA.FUNC == 'A' 
            || BPCSAGCA.FUNC == 'U' 
            || BPCSAGCA.FUNC == 'D') {
            if (BPCSAGCA.CAL_CODE.trim().length() == 0 
                || BPCSAGCA.B_CAL_CODE.trim().length() == 0 
                || BPCSAGCA.YEAR == 0 
                || BPCSAGCA.EFF_DATE == 0 
                || BPCSAGCA.EXP_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSAGCA.FUNC == 'A' 
            || BPCSAGCA.FUNC == 'U' 
            || BPCSAGCA.FUNC == 'C') {
            WS_CAL_CODE = BPCSAGCA.CAL_CODE;
            R000_CHECK_CAL_CODE();
            if (pgmRtn) return;
            if (BPCSAGCA.FUNC == 'A' 
                || BPCSAGCA.FUNC == 'U') {
                R000_CHECK_DETAIL_INFO();
                if (pgmRtn) return;
            }
            if (BPCSAGCA.B_CAL_CODE.trim().length() > 0) {
                R000_CHECK_BASE_CAL();
                if (pgmRtn) return;
            }
        }
        if (BPCSAGCA.FUNC == 'B') {
            if (BPCSAGCA.YEAR == 0 
                && BPCSAGCA.CAL_CODE.trim().length() == 0 
                && BPCSAGCA.B_CAL_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        R000_SET_MONTH_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCALR);
        BPRCALR.KEY.CODE = BPCSAGCA.CAL_CODE;
        BPRCALR.BASE_CODE = BPCSAGCA.B_CAL_CODE;
        BPRCALR.KEY.YEAR = BPCSAGCA.YEAR;
        BPRCALR.BASE_YEAR = BPCSAGCA.YEAR;
        BPRCALR.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCALR.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPRCALR.EFT_DATE = BPCSAGCA.EFF_DATE;
        BPRCALR.EXP_DATE = BPCSAGCA.EXP_DATE;
        BPRCALR.REDEFINES14.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, BPCSAGCA.CAL_DETAIL);
        IBS.CPY2CLS(SCCGWA, BPRCALR.REDEFINES14.DATA_TEXT1, BPRCALR.REDEFINES14.REDEFINES16);
        BPRCALR.DATA = IBS.CLS2CPY(SCCGWA, BPRCALR.REDEFINES14);
        IBS.init(SCCGWA, BPCTCALR);
        if (BPCSAGCA.FUNC == 'Q'
            || BPCSAGCA.FUNC == 'C') {
            BPCTCALR.INFO.FUNC = 'Q';
        } else if (BPCSAGCA.FUNC == 'A') {
            BPCTCALR.INFO.FUNC = 'C';
        } else if (BPCSAGCA.FUNC == 'U'
            || BPCSAGCA.FUNC == 'D') {
            BPCTCALR.INFO.FUNC = 'R';
        }
        S000_CALL_BPZTCALR();
        if (pgmRtn) return;
        R000_CHECK_RETURN();
        if (pgmRtn) return;
        if (BPCSAGCA.FUNC == 'A') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSAGCA.FUNC == 'U' 
            || BPCSAGCA.FUNC == 'D') {
            if (BPCSAGCA.FUNC == 'D') {
                BPCTCALR.INFO.FUNC = 'D';
                S000_CALL_BPZTCALR();
                if (pgmRtn) return;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
            if (BPCSAGCA.FUNC == 'U') {
                BPCTCALR.INFO.FUNC = 'U';
                IBS.CPY2CLS(SCCGWA, BPRCALR.REDEFINES14.DATA_TEXT1, WS_OD_CAL_DETAIL);
                WS_TXN_HIS_DATA.WS_OLD_NAME = " ";
                WS_TXN_HIS_DATA.WS_OLD_BCAL_CODE = BPRCALR.BASE_CODE;
                WS_TXN_HIS_DATA.WS_OLD_EFF_DATE = BPRCALR.EFT_DATE;
                WS_TXN_HIS_DATA.WS_OLD_EXP_DATE = BPRCALR.EXP_DATE;
                WS_MON_DIFF_FLG = 'N';
                for (WS_I = 1; WS_I <= 12; WS_I += 1) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OD_CAL_DETAIL.WS_OD_MONTH[WS_I-1]);
                    if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCSAGCA.CAL_DETAIL.MONTH[WS_I-1])) {
                        WS_MON_DIFF_FLG = 'Y';
                        WS_MON_NO = WS_I;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSAGCA.CAL_DETAIL.MONTH[WS_I-1]);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TXN_HIS_DATA.WS_NEW_MONTH);
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OD_CAL_DETAIL.WS_OD_MONTH[WS_I-1]);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TXN_HIS_DATA.WS_OLD_MONTH);
                        for (WS_K = 1; WS_K <= 31; WS_K += 1) {
                            if (WS_TXN_HIS_DATA.WS_NEW_MONTH.WS_NEW_TYPE[WS_K-1] != WS_TXN_HIS_DATA.WS_OLD_MONTH.WS_OLD_TYPE[WS_K-1]) {
                                WS_COMP_DATE = BPCSAGCA.YEAR * 10000 + WS_I * 100 + WS_K;
                                if (WS_COMP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BACK_DATE_CHANGE, WS_RC);
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
                BPRCALR.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRCALR.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
                BPRCALR.BASE_CODE = BPCSAGCA.B_CAL_CODE;
                BPRCALR.EFT_DATE = BPCSAGCA.EFF_DATE;
                BPRCALR.EXP_DATE = BPCSAGCA.EXP_DATE;
                BPRCALR.REDEFINES14.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, BPCSAGCA.CAL_DETAIL);
                IBS.CPY2CLS(SCCGWA, BPRCALR.REDEFINES14.DATA_TEXT1, BPRCALR.REDEFINES14.REDEFINES16);
                BPRCALR.DATA = IBS.CLS2CPY(SCCGWA, BPRCALR.REDEFINES14);
                S000_CALL_BPZTCALR();
                if (pgmRtn) return;
            }
        }
        IBS.CPY2CLS(SCCGWA, BPRCALR.REDEFINES14.DATA_TEXT1, BPCSAGCA.CAL_DETAIL);
        BPCSAGCA.EFF_DATE = BPRCALR.EFT_DATE;
        BPCSAGCA.EXP_DATE = BPRCALR.EXP_DATE;
        BPCSAGCA.B_CAL_CODE = BPRCALR.BASE_CODE;
        if (BPCSAGCA.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALR);
        BPRCALR.KEY.CODE = BPCSAGCA.CAL_CODE;
        BPRCALR.BASE_CODE = BPCSAGCA.B_CAL_CODE;
        BPRCALR.KEY.YEAR = BPCSAGCA.YEAR;
        BPRCALR.BASE_YEAR = BPCSAGCA.YEAR;
        IBS.init(SCCGWA, BPCTCALR);
        BPCTCALR.INFO.FUNC = 'B';
        BPCTCALR.INFO.OPT = 'S';
        BPCTCALR.INFO.INDEX_FLG = '2';
        if (BPCSAGCA.CAL_CODE.trim().length() > 0 
            || (BPCSAGCA.CAL_CODE.trim().length() == 0 
            && BPCSAGCA.B_CAL_CODE.trim().length() == 0)) {
            BPCTCALR.INFO.INDEX_FLG = '1';
        }
        S000_CALL_BPZTCALR();
        if (pgmRtn) return;
        if (BPCSAGCA.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCTCALR);
            BPCTCALR.INFO.FUNC = 'B';
            BPCTCALR.INFO.OPT = 'N';
            S000_CALL_BPZTCALR();
            if (pgmRtn) return;
            if (BPCTCALR.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                R000_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPCSAGCA.OUTPUT_FLG == 'Y' 
                    && WS_FIT_COND_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTCALR);
        BPCTCALR.INFO.FUNC = 'B';
        BPCTCALR.INFO.OPT = 'E';
        S000_CALL_BPZTCALR();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 150;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_CAL_CD = BPRCALR.KEY.CODE;
            WS_CAL_CODE = BPRCALR.KEY.CODE;
            R000_CHECK_CAL_CODE();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_CAL_NE = WS_CAL_NAME;
            WS_OUTPUT_DATA.WS_OT_T_CAL_CD = BPRCALR.BASE_CODE;
            WS_CAL_CODE = BPRCALR.BASE_CODE;
            R000_CHECK_CAL_CODE();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_T_CAL_NE = WS_CAL_NAME;
            WS_OUTPUT_DATA.WS_OT_YEAR = BPRCALR.KEY.YEAR;
            WS_OUTPUT_DATA.WS_OT_EFF_DATE = BPRCALR.EFT_DATE;
            WS_OUTPUT_DATA.WS_OT_EXP_DATE = BPRCALR.EXP_DATE;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 150;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DETAIL_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            for (WS_II = 1; WS_II <= 31; WS_II += 1) {
                WS_DAY_TYP_FLG = BPCSAGCA.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_II-1];
                if ((WS_DAY_TYP_FLG != 'W' 
                    && WS_DAY_TYP_FLG != 'S' 
                    && WS_DAY_TYP_FLG != 'H')) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQ01);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 11;
        SCCMPAG.MAX_COL_NO = 99;
        SCCMPAG.SCR_ROW_CNT = 47;
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
        BPCQ01.2.2_CALCD = BPCSAGCA.CAL_CODE;
        BPCQ01.2.2_CALNE = BPCSAGCA.CAL_NAME;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.2);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.2_1.2_1_CALCD = BPCSAGCA.B_CAL_CODE;
        BPCQ01.2_1.2_1_CALNE = BPCSAGCA.B_CAL_NAME;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.2_1);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        BPCQ01.3.3_YEAR = BPCSAGCA.YEAR;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.3);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSAGCA.EFF_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.11.11_EFF_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.11.11_EFF_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.11.11_EFF_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        IBS.CPY2CLS(SCCGWA, BPCSAGCA.EXP_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.11.11_EXP_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.11.11_EXP_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.11.11_EXP_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.11);
        S000_WRITE_TSQ_OUTPUT();
        if (pgmRtn) return;
        WS_LAST_DATE = BPRCALR.LAST_DATE;
        IBS.CPY2CLS(SCCGWA, WS_LAST_DATE+"", WS_GET_DAYS_DATA.WS_INPUT_DATE);
        BPCQ01.12.12_LST_Y = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY;
        BPCQ01.12.12_LST_M = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM;
        BPCQ01.12.12_LST_D = WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD;
        BPCQ01.12.12_LST_TLT = BPRCALR.LAST_TELLER;
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
            if (BPCSAGCA.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] == 7) {
                WS_IDX = 1;
            } else {
                WS_IDX = (short) (BPCSAGCA.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] + 1);
            }
            WS_III = 1;
            WS_STOP_FLG = 'N';
            WS_WRITE_FLG = 'N';
            for (WS_IND = 1; WS_STOP_FLG != 'Y'; WS_IND += 1) {
                IBS.init(SCCGWA, BPCQ01.9);
                IBS.init(SCCGWA, BPCQ01.10);
                for (WS_II = WS_IDX; (WS_II <= 21) 
                    && WS_STOP_FLG != 'Y'; WS_II += 1) {
                    JIBS_tmp_str[0] = "" + WS_III;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    BPCQ01.9.9_DAY_INFO[WS_II-1].9_DT = JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1);
                    BPCQ01.10.10_DAY_INFO[WS_II-1].10_TYP = BPCSAGCA.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_III-1];
                    if (WS_III >= BPCSAGCA.CAL_WEEK_INFO.MON_DAYS[WS_I-1]) {
                        WS_STOP_FLG = 'Y';
                    }
                    if (BPCSAGCA.CAL_DETAIL.MONTH[WS_I-1].DAY_TYP[WS_III-1] != ' ') {
                        WS_WRITE_FLG = 'Y';
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
                WS_Q_REC[WS_IND-1].WS_Q_REC1 = BPCQ01.REC;
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
                WS_Q_REC[WS_IND-1].WS_Q_REC2 = BPCQ01.REC;
            }
            if (WS_WRITE_FLG == 'Y') {
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
                BPCQ01.REC = WS_Q_REC[1-1].WS_Q_REC1;
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = WS_Q_REC[1-1].WS_Q_REC2;
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.6);
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = WS_Q_REC[2-1].WS_Q_REC1;
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = WS_Q_REC[2-1].WS_Q_REC2;
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
                BPCQ01.REC = IBS.CLS2CPY(SCCGWA, BPCQ01.6);
                S000_WRITE_TSQ_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_FIT_COND_FLG = 'Y';
        if (BPCSAGCA.CAL_CODE.trim().length() > 0 
            && !BPCSAGCA.CAL_CODE.equalsIgnoreCase(BPRCALR.KEY.CODE)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSAGCA.B_CAL_CODE.trim().length() > 0 
            && !BPCSAGCA.B_CAL_CODE.equalsIgnoreCase(BPRCALR.BASE_CODE)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSAGCA.YEAR != 0 
            && BPCSAGCA.YEAR != BPRCALR.KEY.YEAR) {
            WS_FIT_COND_FLG = 'N';
        }
    }
    public void R000_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCTCALR.RETURN_INFO == 'N') {
            if (BPCSAGCA.FUNC == 'Q' 
                || BPCSAGCA.FUNC == 'U' 
                || BPCSAGCA.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_AUTOGEN_NOTFND, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSAGCA.FUNC == 'C') {
                BPCSAGCA.CHECK_RESULT = 'N';
            }
        }
        if (BPCTCALR.RETURN_INFO == 'F') {
            if (BPCSAGCA.FUNC == 'C') {
                BPCSAGCA.CHECK_RESULT = 'E';
            }
        }
        if (BPCTCALR.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_AUTOGEN_EXIST, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_SET_MONTH_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_YYYY = BPCSAGCA.YEAR;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_MM = WS_I;
            WS_GET_DAYS_DATA.WS_INPUT_DATE.WS_INPUT_DD = 1;
            R000_GET_MONTH_INFO();
            if (pgmRtn) return;
            BPCSAGCA.CAL_WEEK_INFO.MON_WK_NO[WS_I-1] = WS_GET_DAYS_DATA.WS_WEEK_NO;
            BPCSAGCA.CAL_WEEK_INFO.MON_DAYS[WS_I-1] = WS_GET_DAYS_DATA.WS_MONTH_DAYS;
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
    public void R000_CHECK_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCCAL);
        BPCOCCAL.CAL_CODE = WS_CAL_CODE;
        S000_CALL_CMP_CHECK_CAL();
        if (pgmRtn) return;
        WS_CAL_NAME = BPCOCCAL.CAL_NAME;
    }
    public void R000_CHECK_BASE_CAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE);
        BPCSCALE.CAL_CODE = BPCSAGCA.B_CAL_CODE;
        BPCSCALE.CAL_NAME = BPCSAGCA.B_CAL_NAME;
        BPCSCALE.YEAR = BPCSAGCA.YEAR;
        BPCSCALE.EFF_DATE = BPCSAGCA.EFF_DATE;
        BPCSCALE.FUNC = 'C';
        BPCSCALE.OUTPUT_FLG = 'N';
        S000_CALL_BPZSCALE();
        if (pgmRtn) return;
        if (BPCSCALE.CHECK_RESULT == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BASE_CAL_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSAGCA.EXP_DATE > BPCSCALE.EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_EXP_DATE_BIGGER, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSAGCA.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSAGCA.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSAGCA.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_CODE = BPCSAGCA.CAL_CODE;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_YEAR = BPCSAGCA.YEAR;
        WS_TXN_HIS_DATA.WS_HIS_REF.WS_HIS_MON = WS_MON_NO;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_HIS_REF);
        if (BPCSAGCA.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHAGC);
            BPCOHAGC.NAME = " ";
            BPCOHAGC.BCAL_CODE = WS_TXN_HIS_DATA.WS_OLD_BCAL_CODE;
            BPCOHAGC.EFF_DATE = WS_TXN_HIS_DATA.WS_OLD_EFF_DATE;
            BPCOHAGC.EXP_DATE = WS_TXN_HIS_DATA.WS_OLD_EXP_DATE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TXN_HIS_DATA.WS_OLD_MONTH);
