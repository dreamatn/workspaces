package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSBIHS {
    LNZSBIHS_WS_OUT_DATA WS_OUT_DATA;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char REPY_INT = 'I';
    char INT_TYP = 'N';
    short MAX_150 = 150;
    String FMT_ID = "LN804";
    LNZSBIHS_WS_VARIABLES WS_VARIABLES = new LNZSBIHS_WS_VARIABLES();
    LNZSBIHS_WS_PAGE_INFO WS_PAGE_INFO = new LNZSBIHS_WS_PAGE_INFO();
    LNZSBIHS_WS_OUT_RECODE WS_OUT_RECODE = new LNZSBIHS_WS_OUT_RECODE();
    LNZSBIHS_WS_COND_FLG WS_COND_FLG = new LNZSBIHS_WS_COND_FLG();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRINTD LNRINTD = new LNRINTD();
    LNCRINTD LNCRINTD = new LNCRINTD();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCGWA SCCGWA;
    LNCSBIHS LNCSBIHS;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSBIHS LNCSBIHS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSBIHS = LNCSBIHS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSBIHS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.PAGE_NUM);
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.PAGE_ROW);
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, SCCFMT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.PAGE_NUM);
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.PAGE_ROW);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSBIHS.DATA_AREA.CTA_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B001_CHECK_CONT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_CONT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.CTA_NO);
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSBIHS.DATA_AREA.CTA_NO;
        LNCRCONT.FUNC = 'I';
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 1304;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B000_OUTPUT_FIELD();
        if (pgmRtn) return;
        B001_GET_PAGE_INFO();
        if (pgmRtn) return;
        B002_READ_LNTICTL();
        if (pgmRtn) return;
        WS_COND_FLG.EOF_INTD_FLG = 'N';
        B100_STARTBR_LNTINTD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AFTER STARTBR-LNTINTD");
        CEP.TRC(SCCGWA, WS_COND_FLG.EOF_INTD_FLG);
        B100_READNEXT_LNTINTD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AFTER 1ST READNEXT-LNTINTD");
        CEP.TRC(SCCGWA, WS_COND_FLG.EOF_INTD_FLG);
        while (WS_COND_FLG.EOF_INTD_FLG != 'Y') {
            CEP.TRC(SCCGWA, "OUT TIMES");
            IBS.init(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST);
            B021_GET_INT_BAS();
            if (pgmRtn) return;
            B022_CAL_STS_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRINTD.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.STA_DT);
            CEP.TRC(SCCGWA, LNRINTD.CUT_OFF_DT);
            CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.DUE_DT);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.T_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
            if (LNCSBIHS.DATA_AREA.STA_DT != 0 
                && LNCSBIHS.DATA_AREA.DUE_DT != 0) {
                CEP.TRC(SCCGWA, "NOTNOT");
                if (LNRINTD.KEY.VAL_DT >= LNCSBIHS.DATA_AREA.STA_DT 
                    && LNRINTD.CUT_OFF_DT <= LNCSBIHS.DATA_AREA.DUE_DT) {
                    WS_PAGE_INFO.T_TOTAL_NUM += 1;
                    if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                        && WS_PAGE_INFO.T_TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                        WS_PAGE_INFO.PAGE_IDX += 1;
                        B000_OUTPUT_LIST();
                        if (pgmRtn) return;
                    }
                }
            }
            if (LNCSBIHS.DATA_AREA.STA_DT == 0 
                && LNCSBIHS.DATA_AREA.DUE_DT != 0) {
                CEP.TRC(SCCGWA, "NOT1");
                if (LNRINTD.CUT_OFF_DT <= LNCSBIHS.DATA_AREA.DUE_DT) {
                    WS_PAGE_INFO.T_TOTAL_NUM += 1;
                    if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                        && WS_PAGE_INFO.T_TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                        WS_PAGE_INFO.PAGE_IDX += 1;
                        B000_OUTPUT_LIST();
                        if (pgmRtn) return;
                    }
                }
            }
            if (LNCSBIHS.DATA_AREA.STA_DT != 0 
                && LNCSBIHS.DATA_AREA.DUE_DT == 0) {
                CEP.TRC(SCCGWA, "NOT2");
                if (LNRINTD.KEY.VAL_DT >= LNCSBIHS.DATA_AREA.STA_DT) {
                    WS_PAGE_INFO.T_TOTAL_NUM += 1;
                    if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                        && WS_PAGE_INFO.T_TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                        WS_PAGE_INFO.PAGE_IDX += 1;
                        B000_OUTPUT_LIST();
                        if (pgmRtn) return;
                    }
                }
            }
            if (LNCSBIHS.DATA_AREA.STA_DT == 0 
                && LNCSBIHS.DATA_AREA.DUE_DT == 0) {
                CEP.TRC(SCCGWA, "XXXXXXXX");
                CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
                CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
                CEP.TRC(SCCGWA, WS_PAGE_INFO.T_TOTAL_NUM);
                CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
                WS_PAGE_INFO.T_TOTAL_NUM += 1;
                if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                    && WS_PAGE_INFO.T_TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                    CEP.TRC(SCCGWA, "XXXXXXXX1");
                    WS_PAGE_INFO.PAGE_IDX += 1;
                    B000_OUTPUT_LIST();
                    if (pgmRtn) return;
                }
            }
            B100_READNEXT_LNTINTD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
            CEP.TRC(SCCGWA, LNRINTD.KEY.TERM);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
            CEP.TRC(SCCGWA, LNRINTD.CUT_OFF_DT);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_VAL_DT);
            if (LNCICTLM.REC_DATA.IC_CAL_TERM == LNRINTD.KEY.TERM 
                && LNCICTLM.REC_DATA.IC_CAL_DUE_DT > LNRINTD.CUT_OFF_DT 
                && LNCICTLM.REC_DATA.IC_CAL_VAL_DT < LNRINTD.CUT_OFF_DT) {
                B100_READNEXT_LNTINTD();
                if (pgmRtn) return;
            }
        }
        B003_OUTPUT_DATA_REAL();
        if (pgmRtn) return;
        B100_ENDBR_LNTINTD();
        if (pgmRtn) return;
    }
    public void B000_OUTPUT_FIELD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSBIHS.DATA_AREA.CTA_NO;
        LNCCLNQ.FUNC = '2';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_VARIABLES.WS_BASE_INFO);
        WS_VARIABLES.TS_REC = " ";
        WS_VARIABLES.WS_BASE_INFO.FMT_CD = '0X03';
        WS_VARIABLES.WS_BASE_INFO.T_CTA_NO = LNCCLNQ.DATA.CONT_NO;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_NO);
        WS_VARIABLES.WS_BASE_INFO.T_CI_NO = LNCCLNQ.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_BASE_INFO.T_CI_NO);
        WS_VARIABLES.WS_BASE_INFO.T_CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        WS_VARIABLES.WS_BASE_INFO.CN_FLG = 0X02;
    }
    public void B000_OUTPUT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRINTD.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNRINTD.CUT_OFF_DT);
        CEP.TRC(SCCGWA, LNRINTD.INT_RAT);
        CEP.TRC(SCCGWA, LNRINTD.INT_AMT);
        WS_VARIABLES.WS_INT_HIS_LIST.T_VAL_DT = LNRINTD.KEY.VAL_DT;
        WS_VARIABLES.WS_INT_HIS_LIST.T_CUT_OFF_DT = LNRINTD.CUT_OFF_DT;
        WS_VARIABLES.WS_INT_HIS_LIST.T_INT_RAT = LNRINTD.INT_RAT;
        WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT = LNRINTD.INT_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT);
        if (WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT < 0) {
            WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT = 0 - WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT);
        WS_VARIABLES.WS_INT_HIS_LIST.T_INT = LNRINTD.INT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_BASE_INFO.T_CTA_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_BASE_INFO.T_CI_CNM);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_VAL_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_CUT_OFF_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT_RAT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT_BAS);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_CAL_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_REPAY_STS);
        CEP.TRC(SCCGWA, WS_VARIABLES.TS_REC);
        CEP.TRC(SCCGWA, WS_VARIABLES.REC_LEN);
        WS_PAGE_INFO.ROW_FLG = 'Y';
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, ));
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.CTA_NO = WS_VARIABLES.WS_BASE_INFO.T_CTA_NO;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.CI_NO = WS_VARIABLES.WS_BASE_INFO.T_CI_NO;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.CI_CNM = WS_VARIABLES.WS_BASE_INFO.T_CI_CNM;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.VAL_DT = WS_VARIABLES.WS_INT_HIS_LIST.T_VAL_DT;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.CUT_OFF_DT = WS_VARIABLES.WS_INT_HIS_LIST.T_CUT_OFF_DT;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.INT_RAT = WS_VARIABLES.WS_INT_HIS_LIST.T_INT_RAT;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.INT_BAS = WS_VARIABLES.WS_INT_HIS_LIST.T_INT_BAS;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.INT_AMT = WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.INT = WS_VARIABLES.WS_INT_HIS_LIST.T_INT;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.CAL_AMT = WS_VARIABLES.WS_INT_HIS_LIST.T_CAL_AMT;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        WS_OUT_DATA = WS_OUT_RECODE.WS_OUT_DATA.get(WS_PAGE_INFO.PAGE_IDX-1);
        WS_OUT_DATA.REPAY_STS = WS_VARIABLES.WS_INT_HIS_LIST.T_REPAY_STS;
        WS_OUT_RECODE.WS_OUT_DATA.set(WS_PAGE_INFO.PAGE_IDX-1, WS_OUT_DATA);
        CEP.TRC(SCCGWA, "OUT-INFO");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_INT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_INT_HIS_LIST.T_CAL_AMT);
    }
    public void B002_READ_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSBIHS.DATA_AREA.CTA_NO;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B100_STARTBR_LNTINTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTD);
        IBS.init(SCCGWA, LNCRINTD);
        LNCRINTD.FUNC = 'B';
        LNCRINTD.OPT = 'T';
        LNRINTD.KEY.CONTRACT_NO = LNCSBIHS.DATA_AREA.CTA_NO;
        LNRINTD.KEY.SUB_CTA_NO = 0;
        LNRINTD.KEY.REPY_MTH = REPY_INT;
        LNRINTD.KEY.INT_TYP = INT_TYP;
        CEP.TRC(SCCGWA, LNRINTD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRINTD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRINTD.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNRINTD.KEY.INT_TYP);
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.DUE_DT);
        if (LNCSBIHS.DATA_AREA.DUE_DT == 0) {
            LNRINTD.CUT_OFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            LNRINTD.CUT_OFF_DT = LNCSBIHS.DATA_AREA.DUE_DT;
        }
        CEP.TRC(SCCGWA, LNRINTD.CUT_OFF_DT);
        LNCRINTD.REC_PTR = LNRINTD;
        LNCRINTD.REC_LEN = 1690;
        S000_CALL_LNZRINTD();
        if (pgmRtn) return;
    }
    public void B100_READNEXT_LNTINTD() throws IOException,SQLException,Exception {
        LNCRINTD.FUNC = 'B';
        LNCRINTD.OPT = 'R';
        S000_CALL_LNZRINTD();
        if (pgmRtn) return;
    }
    public void B100_ENDBR_LNTINTD() throws IOException,SQLException,Exception {
        LNCRINTD.FUNC = 'B';
        LNCRINTD.OPT = 'E';
        S000_CALL_LNZRINTD();
        if (pgmRtn) return;
    }
    public void B021_GET_INT_BAS() throws IOException,SQLException,Exception {
        B021_00_GET_APRD_INFO();
        if (pgmRtn) return;
        WS_VARIABLES.CALR_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.INT_DBAS_STD);
        CEP.TRC(SCCGWA, WS_VARIABLES.CALR_STD);
        if (WS_VARIABLES.CALR_STD.equalsIgnoreCase("01")
            || WS_VARIABLES.CALR_STD.equalsIgnoreCase("04")
            || WS_VARIABLES.CALR_STD.equalsIgnoreCase("05")) {
            WS_VARIABLES.WS_INT_HIS_LIST.T_INT_BAS = 360;
        } else if (WS_VARIABLES.CALR_STD.equalsIgnoreCase("02")) {
            WS_VARIABLES.WS_INT_HIS_LIST.T_INT_BAS = 365;
        } else if (WS_VARIABLES.CALR_STD.equalsIgnoreCase("03")) {
            WS_VARIABLES.WS_INT_HIS_LIST.T_INT_BAS = 366;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID INTEREST RATE(" + WS_VARIABLES.CALR_STD + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B021_00_GET_APRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSBIHS.DATA_AREA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B022_CAL_STS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRINTD.KEY.TERM);
        CEP.TRC(SCCGWA, WS_VARIABLES.TERM);
        if (LNRINTD.KEY.TERM != WS_VARIABLES.TERM) {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                WS_VARIABLES.INT_FORMAT = 'C';
            } else {
                WS_VARIABLES.INT_FORMAT = 'I';
            }
            WS_VARIABLES.TERM = LNRINTD.KEY.TERM;
            CEP.TRC(SCCGWA, WS_VARIABLES.TERM);
            CEP.TRC(SCCGWA, WS_VARIABLES.INT_FORMAT);
            B022_00_CALL_RCVD_PROC();
            if (pgmRtn) return;
            WS_VARIABLES.BAL_AMT = LNRRCVD.I_PAY_AMT;
        }
        WS_VARIABLES.BAL_AMT = WS_VARIABLES.BAL_AMT - LNRINTD.INT;
        CEP.TRC(SCCGWA, WS_VARIABLES.BAL_AMT);
        CEP.TRC(SCCGWA, LNRINTD.INT);
        if (WS_VARIABLES.BAL_AMT >= 0) {
            WS_VARIABLES.WS_INT_HIS_LIST.T_CAL_AMT = LNRINTD.INT;
            WS_VARIABLES.WS_INT_HIS_LIST.T_REPAY_STS = '0';
            CEP.TRC(SCCGWA, "YCH01");
        } else {
            WS_VARIABLES.WS_INT_HIS_LIST.T_CAL_AMT = WS_VARIABLES.BAL_AMT;
            WS_VARIABLES.WS_INT_HIS_LIST.T_REPAY_STS = '1';
            CEP.TRC(SCCGWA, "YCH02");
        }
    }
    public void B022_00_CALL_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        IBS.init(SCCGWA, LNCRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSBIHS.DATA_AREA.CTA_NO;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.AMT_TYP = WS_VARIABLES.INT_FORMAT;
        LNRRCVD.KEY.TERM = WS_VARIABLES.TERM;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        LNCRRCVD.FUNC = 'I';
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 1893;
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void B003_OUTPUT_DATA_REAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.ROW_FLG);
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.T_TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.T_TOTAL_PAGE = (short) ((WS_PAGE_INFO.T_TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.T_TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.T_TOTAL_PAGE == WS_PAGE_INFO.T_CURR_PAGE) {
                WS_PAGE_INFO.T_LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                    WS_OUT_DATA = new LNZSBIHS_WS_OUT_DATA();
                    WS_OUT_RECODE.WS_OUT_DATA.add(WS_OUT_DATA);
                }
            }
            WS_OUT_RECODE.WS_OUT_HEAD.CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_IDX;
        } else {
            WS_PAGE_INFO.T_TOTAL_PAGE = 1;
            WS_PAGE_INFO.T_TOTAL_NUM = 0;
            WS_PAGE_INFO.T_LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
            WS_OUT_DATA = new LNZSBIHS_WS_OUT_DATA();
            WS_OUT_RECODE.WS_OUT_DATA.add(WS_OUT_DATA);
            WS_PAGE_INFO.T_CURR_PAGE = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.CURR_PAGE_ROW = 0;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.TOTAL_PAGE = WS_PAGE_INFO.T_TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.TOTAL_NUM = WS_PAGE_INFO.T_TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.CURR_PAGE = WS_PAGE_INFO.T_CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.LAST_PAGE = WS_PAGE_INFO.T_LAST_PAGE;
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 9469;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B001_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        WS_PAGE_INFO.ROW_FLG = 'N';
        WS_PAGE_INFO.T_TOTAL_NUM = 0;
        WS_PAGE_INFO.PAGE_IDX = 0;
        WS_PAGE_INFO.T_LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.PAGE_NUM);
        CEP.TRC(SCCGWA, LNCSBIHS.DATA_AREA.PAGE_ROW);
        WS_PAGE_INFO.PAGE_ROW = LNCSBIHS.DATA_AREA.PAGE_ROW;
        WS_OUT_DATA = new LNZSBIHS_WS_OUT_DATA();
        WS_OUT_RECODE.WS_OUT_DATA.add(WS_OUT_DATA);
        WS_PAGE_INFO.T_CURR_PAGE = (short) LNCSBIHS.DATA_AREA.PAGE_NUM;
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.T_CURR_PAGE);
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.T_CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = "" + LNCICTLM.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG = "0" + WS_VARIABLES.ERR_MSG;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            if (LNCRCONT.RETURN_INFO == 'N') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CONT_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRINTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTINTD", LNCRINTD);
        if (LNCRINTD.RETURN_INFO == 'E') {
            WS_COND_FLG.EOF_INTD_FLG = 'Y';
        } else {
            if (LNCRINTD.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRINTD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
