package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIPST {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTAPRD_RD;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_PRICIPAL = 'P';
    char K_INTEREST = 'I';
    char K_PANDINT = 'C';
    short WS_I = 0;
    short WS_NO = 0;
    short WS_IDX = 0;
    int WS_DATE = 0;
    int WS_VAL_DATE = 0;
    int WS_DUE_DATE = 0;
    short WS_IC_TERM = 0;
    int WS_IC_VAL_DT = 0;
    int WS_IC_DUE_DT = 0;
    int WS_IPST_BEG_DT = 0;
    int WS_IPST_END_DT = 0;
    int WS_RCVD_VAL_DT = 0;
    int WS_RCVD_DUE_DT = 0;
    int WS_STOP_INT_DATE = 0;
    int WS_STOP_DUE_DT = 0;
    double WS_BEG_INT = 0;
    double WS_PST_AMT_O = 0;
    double WS_PST_AMT_L = 0;
    double WS_PST_AMT = 0;
    double WS_PST_AMT_NORMAL = 0;
    double WS_PST_AMT_STOP = 0;
    LNZIPST_WS_POSTING_INFO WS_POSTING_INFO = new LNZIPST_WS_POSTING_INFO();
    LNZIPST_WS_DAYS_INFO WS_DAYS_INFO = new LNZIPST_WS_DAYS_INFO();
    LNZIPST_WS_ACCR_CTNR_ID[] WS_ACCR_CTNR_ID = new LNZIPST_WS_ACCR_CTNR_ID[3];
    LNZIPST_WS_CTNR_INFO[] WS_CTNR_INFO = new LNZIPST_WS_CTNR_INFO[3];
    LNZIPST_WS_CTNR_TMP WS_CTNR_TMP = new LNZIPST_WS_CTNR_TMP();
    double WS_TMP_AMT = 0;
    double WS_PENALTY_AMT_O = 0;
    double WS_PENALTY_AMT_L = 0;
    double WS_PENALTY_AMT = 0;
    double WS_PENALTY_AMT_NORMAL = 0;
    double WS_PENALTY_AMT_STOP = 0;
    LNZIPST_WS_TERM_INFO WS_TERM_INFO = new LNZIPST_WS_TERM_INFO();
    String WS_EVENT_CODE = "        ";
    String WS_INQ_ACCRU_CODE = "       ";
    String WS_INQ_POST_CODE = "       ";
    char WS_LCCM_TYPE = ' ';
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    LNZIPST_WS_ADJ_BK_AMT_INFO WS_ADJ_BK_AMT_INFO = new LNZIPST_WS_ADJ_BK_AMT_INFO();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_CONT_FLG = ' ';
    LNRBALH LNRBALH = new LNRBALH();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCICUT LNCICUT = new LNCICUT();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCICAL LNCICAL = new LNCICAL();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCLCCM LNCLCCM = new LNCLCCM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    SCCGWA SCCGWA;
    LNCIPST LNCIPST;
    SCCBATH SCCBATH;
    public LNZIPST() {
        for (int i=0;i<3;i++) WS_ACCR_CTNR_ID[i] = new LNZIPST_WS_ACCR_CTNR_ID();
        for (int i=0;i<3;i++) WS_CTNR_INFO[i] = new LNZIPST_WS_CTNR_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCIPST LNCIPST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIPST = LNCIPST;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIPST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        LNCIPST.RC.RC_APP = "LN";
        LNCIPST.RC.RC_RTNCODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIPST.COMM_DATA.AMT_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ADJ_BK_AMT_INFO);
        CEP.TRC(SCCGWA, LNCIPST.RC.RC_RTNCODE);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.ACCRU_TYPE);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.END_DATE);
        A010_GET_PPMQ_INF();
        if (pgmRtn) return;
    }
    public void A010_GET_PPMQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
        T000_READ_APRD_PROC();
        if (pgmRtn) return;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_DATA();
        if (pgmRtn) return;
        if (LNCLOANM.REC_DATA.STOP_VAL_DT != 0) {
            if (LNCLOANM.REC_DATA.STOP_DUE_DT == 0) {
                WS_STOP_DUE_DT = 99999999;
            } else {
                WS_STOP_DUE_DT = LNCLOANM.REC_DATA.STOP_DUE_DT;
            }
            CEP.TRC(SCCGWA, "LOANM-STOP-VAL-DT = ");
            CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_VAL_DT);
            CEP.TRC(SCCGWA, "WS-STOP-DUE-DT = ");
            CEP.TRC(SCCGWA, WS_STOP_DUE_DT);
            if (LNCLOANM.REC_DATA.STOP_VAL_DT <= SCCBATH.JPRM.AC_DATE 
                && WS_STOP_DUE_DT > SCCBATH.JPRM.AC_DATE) {
            } else {
                B200_MAIN_ACCRU_PROC();
                if (pgmRtn) return;
                B300_CALC_TODAY_ACCRU_AMT();
                if (pgmRtn) return;
                B400_CALC_TERM_ACCRU_INT();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "LOANM-STOP-VAL-DT = ");
            CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_VAL_DT);
            B200_MAIN_ACCRU_PROC();
            if (pgmRtn) return;
            B300_CALC_TODAY_ACCRU_AMT();
            if (pgmRtn) return;
            B400_CALC_TERM_ACCRU_INT();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCIPST.COMM_DATA.FUNC_CODE != 'I' 
            && LNCIPST.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCIPST.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCIPST.COMM_DATA.BEG_DATE == 0) {
            LNCIPST.COMM_DATA.BEG_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (LNCIPST.COMM_DATA.END_DATE == 0) {
            LNCIPST.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.END_DATE);
        if (LNCIPST.COMM_DATA.BEG_DATE > LNCIPST.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            WS_END_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        } else {
            WS_END_DATE = LNCIPST.COMM_DATA.END_DATE;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B110_GET_LOAN_INF();
        if (pgmRtn) return;
        B210_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B110_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
        if (LNCIPST.COMM_DATA.ACCEPT_FLG == 'Y') {
            LNCCONTM.REC_DATA.CONTRACT_TYPE = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CONTRACT_TYPE;
            LNCCONTM.REC_DATA.MAT_DATE = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_MAT_DATE;
        } else {
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        }
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '3';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
            WS_STOP_INT_DATE = LNCLOANM.REC_DATA.STOP_VAL_DT;
        }
    }
    public void B200_MAIN_ACCRU_PROC() throws IOException,SQLException,Exception {
        WS_EVENT_CODE = "POSTINT";
        B230_INIT_EVENT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.ACCRU_TYPE);
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'A' 
            || LNCIPST.COMM_DATA.ACCRU_TYPE == ' ') {
            WS_INQ_ACCRU_CODE = "INQACCR";
            B270_INIT_ACCRU_AMT();
            if (pgmRtn) return;
            B201_ACCRU_INT();
            if (pgmRtn) return;
            B300_CALC_TODAY_ACCRU_AMT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        WS_PENALTY_AMT_O = 0;
        WS_PENALTY_AMT_L = 0;
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B' 
            || LNCIPST.COMM_DATA.ACCRU_TYPE == ' ') {
            WS_INQ_ACCRU_CODE = "INQACPP";
            if (LNRAPRD.PAY_MTH == '4') {
                WS_LCCM_TYPE = K_PANDINT;
                WS_TERM_INFO.WS_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
                WS_TERM_INFO.WS_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
                CEP.TRC(SCCGWA, WS_TERM_INFO.WS_CAL_TERM);
                CEP.TRC(SCCGWA, "111");
            } else {
                WS_LCCM_TYPE = K_PRICIPAL;
                WS_TERM_INFO.WS_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
                WS_TERM_INFO.WS_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
                CEP.TRC(SCCGWA, WS_TERM_INFO.WS_CAL_TERM);
                B270_INIT_ACCRU_AMT();
                if (pgmRtn) return;
                B203_ACCRU_PENALTY();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_PST_AMT_O);
                CEP.TRC(SCCGWA, WS_PST_AMT_L);
                CEP.TRC(SCCGWA, WS_PENALTY_AMT_O);
                CEP.TRC(SCCGWA, WS_PENALTY_AMT_L);
                B300_CALC_TODAY_ACCRU_AMT();
                if (pgmRtn) return;
                WS_INQ_ACCRU_CODE = "INQACPI";
                WS_LCCM_TYPE = K_INTEREST;
                WS_TERM_INFO.WS_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
                WS_TERM_INFO.WS_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            }
            CEP.TRC(SCCGWA, WS_TERM_INFO.WS_CAL_TERM);
            B270_INIT_ACCRU_AMT();
            if (pgmRtn) return;
            B203_ACCRU_PENALTY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_PST_AMT_O);
            CEP.TRC(SCCGWA, WS_PST_AMT_L);
            CEP.TRC(SCCGWA, WS_PENALTY_AMT_O);
            CEP.TRC(SCCGWA, WS_PENALTY_AMT_L);
            B300_CALC_TODAY_ACCRU_AMT();
            if (pgmRtn) return;
        }
        B230_ACCRU_EVENT_PROC();
        if (pgmRtn) return;
    }
    public void B201_ACCRU_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = LNCIPST.COMM_DATA.ACCRU_TYPE;
        LNCICUT.COMM_DATA.END_DATE = LNCIPST.COMM_DATA.END_DATE;
        if (LNCIPST.COMM_DATA.END_DATE > LNCCONTM.REC_DATA.MAT_DATE) {
            LNCICUT.COMM_DATA.END_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.INT_CUT_DT > WS_END_DATE) {
            LNCICUT.COMM_DATA.INT_AMT = 0;
        } else {
            if (LNCIPST.COMM_DATA.END_DATE >= LNCICTLM.REC_DATA.IC_CAL_VAL_DT) {
                if (LNCICTLM.REC_DATA.INT_CUT_DT < LNCCONTM.REC_DATA.MAT_DATE) {
                    S000_CALL_LNZICUT();
                    if (pgmRtn) return;
                }
            }
        }
        WS_POSTING_INFO.WS_POSTING_INT = LNCICUT.COMM_DATA.INT_AMT;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQTMPI";
        LNCCNEX.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        WS_TMP_AMT = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT);
        WS_PST_AMT = LNCICUT.COMM_DATA.INT_AMT + LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT + LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_PST_AMT);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQPOST";
        LNCCNEX.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        S000_CALL_LNZINEX();
        if (pgmRtn) return;
        WS_TMP_AMT = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_ID);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT);
        WS_PST_AMT = WS_PST_AMT - LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_PST_AMT);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        WS_PST_AMT_NORMAL = WS_PST_AMT;
        WS_PST_AMT_STOP = 0;
        B230_ACCRU_EVENT_AMT_PROC();
        if (pgmRtn) return;
        LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.DAY_POSTING_INT = WS_PST_AMT;
        LNCIPST.COMM_DATA.AMT_INFO.AMT_NON_STOP_INFO.N_DAY_POSTING_INT = WS_PST_AMT_NORMAL;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CTNR_INFO[1-1].WS_CTNR_ID);
        CEP.TRC(SCCGWA, "B201-ACCRU-INT END");
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.DAY_POSTING_INT);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.AMT_INFO.AMT_NON_STOP_INFO.N_DAY_POSTING_INT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_ID);
        CEP.TRC(SCCGWA, WS_CTNR_INFO[1-1].WS_CTNR_ID);
    }
    public void B210_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
        if (LNCIPST.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCIPST.COMM_DATA.SUF_NO);
        if (LNCIPST.COMM_DATA.ACCEPT_FLG == 'Y') {
            LNCICTLM.REC_DATA.IC_CUR_TERM = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_IC_CUR_TERM;
            LNCICTLM.REC_DATA.IC_CAL_TERM = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_IC_CAL_TERM;
            LNCICTLM.REC_DATA.P_CUR_TERM = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_P_CUR_TERM;
            LNCICTLM.REC_DATA.P_CAL_TERM = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_P_CAL_TERM;
            LNCICTLM.REC_DATA.IC_CMP_TERM = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_IC_CMP_TERM;
            LNCICTLM.REC_DATA.IC_CAL_VAL_DT = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_IC_CAL_VAL_DT;
            LNCICTLM.REC_DATA.IC_CAL_DUE_DT = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_IC_CAL_DUE_DT;
            LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_IC_CMP_DUE_DT;
            LNCICTLM.REC_DATA.INT_CUT_DT = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_INT_CUT_DT;
            LNCICTLM.REC_DATA.CTL_STSW = LNCIPST.COMM_DATA.ACCEPT_DATA.ICTL_DATA.ICTL_CTL_STSW;
        } else {
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
        WS_IC_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        WS_IC_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        WS_IC_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        if (LNCIPST.COMM_DATA.BEG_DATE < WS_IC_VAL_DT) {
            WS_IPST_BEG_DT = WS_IC_VAL_DT;
        } else {
            WS_IPST_BEG_DT = LNCIPST.COMM_DATA.BEG_DATE;
        }
    }
    public void B230_INIT_EVENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = WS_EVENT_CODE;
        LNCCNEV.COMM_DATA.ACM_EVENT = LNCIPST.COMM_DATA.ACM_EVENT;
        LNCCNEV.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCIPST.COMM_DATA.BEG_DATE;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.ACM_EVENT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.VAL_DATE);
    }
    public void B230_ACCRU_EVENT_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.ACCRU_TYPE);
        CEP.TRC(SCCGWA, WS_PST_AMT);
        B230_ADJ_BK_AMT_PROC();
        if (pgmRtn) return;
        LNCCNEV.COMM_DATA.I_AMT = WS_PST_AMT;
        CEP.TRC(SCCGWA, "20180716 B230-ACCRU");
        CEP.TRC(SCCGWA, WS_PST_AMT);
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'A') {
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_PST_AMT_NORMAL;
            LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT = WS_PST_AMT_STOP;
            CEP.TRC(SCCGWA, "20180716 B230-ACCRU-EVENT-AMT-PROC A");
            CEP.TRC(SCCGWA, WS_PST_AMT_NORMAL);
            CEP.TRC(SCCGWA, WS_PST_AMT_STOP);
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
            CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT);
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = WS_PST_AMT_O;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_PST_AMT_L;
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'C') {
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_PST_AMT_L;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B230_ADJ_BK_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.ACCRU_TYPE);
        CEP.TRC(SCCGWA, WS_PST_AMT);
        if (LNCIPST.COMM_DATA.ADJ_BK_FLG == 'A') {
            if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'A') {
                WS_PST_AMT = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_INT;
                WS_PST_AMT_NORMAL = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_INT;
                WS_PST_AMT_STOP = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_INT - WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_INT;
                CEP.TRC(SCCGWA, "20180716  B230-ADJ-BK-AMT-PROC");
                CEP.TRC(SCCGWA, WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_INT);
                CEP.TRC(SCCGWA, WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_INT);
                CEP.TRC(SCCGWA, WS_PST_AMT_STOP);
            } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
                WS_PST_AMT = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_PENA_P;
                WS_PST_AMT_NORMAL = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_PENA_P;
                WS_PST_AMT_STOP = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_PENA_P - WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_PENA_P;
            } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'C') {
                WS_PST_AMT = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_PENA_I;
                WS_PST_AMT_NORMAL = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_PENA_I;
                WS_PST_AMT_STOP = WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NORMAL_INFO.WS_A_DAY_POSTING_PENA_I - WS_ADJ_BK_AMT_INFO.WS_ADJ_BK_AMT_NON_STOP_INFO.WS_A_N_DAY_POSTING_PENA_I;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B230_ACCRU_EVENT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_EVENT_CODE);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].OLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].LLC_AMT);
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        if (LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT != 0 
            || LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT != 0 
            || LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT != 0 
            || LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT != 0 
            || LNCCNEV.COMM_DATA.IETM_AMTS[2-1].OLC_AMT != 0 
            || LNCCNEV.COMM_DATA.IETM_AMTS[2-1].LLC_AMT != 0) {
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
        }
    }
    public void B203_ACCRU_PENALTY() throws IOException,SQLException,Exception {
        WS_PENALTY_AMT = 0;
        WS_PENALTY_AMT_NORMAL = 0;
        WS_PENALTY_AMT_STOP = 0;
        WS_DAYS_INFO.WS_DAYS_AMT = 0;
        CEP.TRC(SCCGWA, WS_TERM_INFO.WS_CUR_TERM);
        CEP.TRC(SCCGWA, WS_TERM_INFO.WS_CAL_TERM);
        for (WS_TERM_INFO.WS_TERM_IDX = WS_TERM_INFO.WS_CUR_TERM; WS_TERM_INFO.WS_TERM_IDX < WS_TERM_INFO.WS_CAL_TERM; WS_TERM_INFO.WS_TERM_IDX += 1) {
            CEP.TRC(SCCGWA, WS_TERM_INFO.WS_TERM_IDX);
            B203_ACCRU_PENALTY_BY_TERM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.NEXT_LC_CAL_DAT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_VAL_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        CEP.TRC(SCCGWA, WS_PENALTY_AMT);
        CEP.TRC(SCCGWA, WS_PENALTY_AMT_NORMAL);
        CEP.TRC(SCCGWA, WS_PENALTY_AMT_STOP);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDUEL";
        LNCCNEX.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        S000_CALL_LNZINEX();
        if (pgmRtn) return;
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            WS_PST_AMT_O = WS_PENALTY_AMT_O - LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
            WS_PST_AMT_L = WS_PENALTY_AMT_L - LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT;
        } else {
            WS_PST_AMT_L = WS_PENALTY_AMT_L - LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT;
        }
        CEP.TRC(SCCGWA, "XXX101");
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT);
        CEP.TRC(SCCGWA, WS_PENALTY_AMT_O);
        CEP.TRC(SCCGWA, WS_PENALTY_AMT_L);
        CEP.TRC(SCCGWA, WS_PST_AMT_O);
        CEP.TRC(SCCGWA, WS_PST_AMT_L);
        B230_ACCRU_EVENT_AMT_PROC();
        if (pgmRtn) return;
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            WS_POSTING_INFO.WS_POSTING_OLC = WS_PENALTY_AMT_O;
            WS_POSTING_INFO.WS_POSTING_LLC = WS_PENALTY_AMT_L;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CTNR_INFO[2-1].WS_CTNR_ID);
            WS_DAYS_INFO.WS_DAYS_OLC = WS_DAYS_INFO.WS_DAYS_AMT;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.DAY_POSTING_PENA_P = WS_PST_AMT_O;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NON_STOP_INFO.N_DAY_POSTING_PENA_P = WS_PST_AMT_O;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.DAY_POSTING_PENA_I = WS_PST_AMT_L;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NON_STOP_INFO.N_DAY_POSTING_PENA_I = WS_PST_AMT_L;
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'C') {
            WS_POSTING_INFO.WS_POSTING_LLC = WS_PENALTY_AMT_L;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CTNR_INFO[3-1].WS_CTNR_ID);
            WS_DAYS_INFO.WS_DAYS_LLC = WS_DAYS_INFO.WS_DAYS_AMT;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.DAY_POSTING_PENA_I = WS_PST_AMT_L;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NON_STOP_INFO.N_DAY_POSTING_PENA_I = WS_PST_AMT_L;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B203_ACCRU_PENALTY_BY_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_LCCM_TYPE);
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.FUNC_CODE = 'A';
        LNCLCCM.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCLCCM.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TERM = WS_TERM_INFO.WS_TERM_IDX;
        LNCLCCM.COMM_DATA.TYPE = WS_LCCM_TYPE;
        LNCLCCM.COMM_DATA.END_DATE = LNCIPST.COMM_DATA.END_DATE;
        S000_CALL_LNZLCCM();
        if (pgmRtn) return;
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            WS_PENALTY_AMT_O += LNCLCCM.COMM_DATA.O_AMT;
            WS_PENALTY_AMT_L += LNCLCCM.COMM_DATA.L_AMT;
        } else {
            WS_PENALTY_AMT_L += LNCLCCM.COMM_DATA.L_AMT;
        }
        CEP.TRC(SCCGWA, WS_PENALTY_AMT_O);
        CEP.TRC(SCCGWA, WS_PENALTY_AMT_L);
    }
    public void B270_INIT_ACCRU_AMT() throws IOException,SQLException,Exception {
        R000_INIT_ACCRUAL_BAL();
        if (pgmRtn) return;
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'A') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACCR_CTNR_ID[1-1].WS_ACCR_ID);
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACCR_CTNR_ID[2-1].WS_ACCR_ID);
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'C') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACCR_CTNR_ID[3-1].WS_ACCR_ID);
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_CALC_TODAY_ACCRU_AMT() throws IOException,SQLException,Exception {
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'A') {
            B301_CALC_TODAY_ACCRU_INT();
            if (pgmRtn) return;
            LNCCNBU.COMM_DATA.AMT = WS_DAYS_INFO.WS_DAYS_INT;
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            R000_SET_ACCRUAL_BAL();
            if (pgmRtn) return;
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'C') {
            R000_SET_ACCRUAL_BAL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.DAY_INT_NO_ADJ = WS_DAYS_INFO.WS_DAYS_INT;
    }
    public void B301_CALC_TODAY_ACCRU_INT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            IBS.init(SCCGWA, LNRCONT);
            LNRCONT.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
            T000_READ_LNTCONT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNRICTL);
            LNRICTL.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
            T000_READ_LNTICTL();
            if (pgmRtn) return;
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (WS_CONT_FLG == 'Y' 
                && LNRICTL.CTL_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                if (SCCGWA.COMM_AREA.AC_DATE == LNRCONT.CRT_DATE 
                    && SCCGWA.COMM_AREA.AC_DATE > LNRCONT.START_DATE) {
                    WS_BEG_DATE = LNRCONT.START_DATE;
                } else {
                    WS_BEG_DATE = SCCBATH.JPRM.AC_DATE;
                }
            } else {
                WS_BEG_DATE = SCCBATH.JPRM.AC_DATE;
            }
            WS_END_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        } else {
            WS_BEG_DATE = LNCIPST.COMM_DATA.BEG_DATE;
            WS_END_DATE = LNCIPST.COMM_DATA.END_DATE;
        }
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = LNCIPST.COMM_DATA.ACCRU_TYPE;
        LNCICUT.COMM_DATA.BEG_DATE = WS_BEG_DATE;
        LNCICUT.COMM_DATA.END_DATE = WS_END_DATE;
        LNCICUT.COMM_DATA.INCLUDE_ADJ_FLG = 'N';
        CEP.TRC(SCCGWA, WS_BEG_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (LNCICUT.COMM_DATA.END_DATE > LNCCONTM.REC_DATA.MAT_DATE) {
            LNCICUT.COMM_DATA.END_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.INT_CUT_DT > WS_END_DATE) {
            LNCICUT.COMM_DATA.INT_AMT = 0;
        } else {
            if (LNCICTLM.REC_DATA.INT_CUT_DT <= LNCCONTM.REC_DATA.MAT_DATE 
                && WS_BEG_DATE < LNCCONTM.REC_DATA.MAT_DATE) {
                S000_CALL_LNZICUT();
                if (pgmRtn) return;
            }
        }
        WS_DAYS_INFO.WS_DAYS_INT = LNCICUT.COMM_DATA.INT_AMT;
    }
    public void B400_CALC_TERM_ACCRU_INT() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.IC_CAL_TERM <= LNCICTLM.REC_DATA.IC_CMP_TERM) {
            IBS.init(SCCGWA, LNCICUT);
            LNCICUT.COMM_DATA.FUNC_CODE = 'I';
            LNCICUT.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
            LNCICUT.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
            LNCICUT.COMM_DATA.TYPE = 'I';
            LNCICUT.COMM_DATA.BEG_DATE = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
            LNCICUT.COMM_DATA.END_DATE = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
            CEP.TRC(SCCGWA, "TZKTZKTZK");
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_DUE_DT);
            if (LNCICTLM.REC_DATA.IC_CAL_TERM == LNCICTLM.REC_DATA.IC_CMP_TERM 
                && (LNCICTLM.REC_DATA.IC_CAL_DUE_DT == 0 
                || LNCICTLM.REC_DATA.IC_CMP_DUE_DT == 0)) {
                LNCICUT.COMM_DATA.END_DATE = LNCCONTM.REC_DATA.MAT_DATE;
            }
            CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.END_DATE);
            if (LNCICTLM.REC_DATA.INT_CUT_DT > LNCICTLM.REC_DATA.IC_CAL_VAL_DT 
                && LNCICTLM.REC_DATA.INT_CUT_DT < LNCICTLM.REC_DATA.IC_CAL_DUE_DT) {
                LNCICUT.COMM_DATA.BEG_DATE = LNCICTLM.REC_DATA.INT_CUT_DT;
            }
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.IC_CAL_VAL_DT >= LNCCONTM.REC_DATA.MAT_DATE 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.INT_CUT_DT > WS_END_DATE) {
                LNCICUT.COMM_DATA.INT_AMT = 0;
            } else {
                S000_CALL_LNZICUT();
                if (pgmRtn) return;
            }
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.ACCRUAL_TERM = LNCICUT.COMM_DATA.INT_AMT;
            CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.ACCRUAL_TERM);
            IBS.init(SCCGWA, LNCCNEX);
            LNCCNEX.COMM_DATA.INQ_CODE = "INQPOST";
            LNCCNEX.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
            LNCCNEX.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
            S000_CALL_LNZINEX();
            if (pgmRtn) return;
            LNCIPST.COMM_DATA.AMT_INFO.AMT_NORMAL_INFO.ACCRUAL_INT += LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        }
    }
    public void R000_INIT_ACCRUAL_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXX333");
        CEP.TRC(SCCGWA, WS_INQ_ACCRU_CODE);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = WS_INQ_ACCRU_CODE;
        LNCCNEX.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        S000_CALL_LNZINEX();
        if (pgmRtn) return;
        WS_TMP_AMT = 0;
        for (WS_IDX = 1; WS_IDX <= 10; WS_IDX += 1) {
            CEP.TRC(SCCGWA, WS_IDX);
            CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[WS_IDX-1].CTNR_ID);
            if (LNCCNEX.COMM_DATA.CTNR_DATA[WS_IDX-1].CTNR_ID.CTNR_TYP != ' ') {
                IBS.init(SCCGWA, LNCCNBU);
                LNCCNBU.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
                LNCCNBU.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
                LNCCNBU.COMM_DATA.VAL_DATE = LNCIPST.COMM_DATA.END_DATE;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[WS_IDX-1].CTNR_ID);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
                LNCCNBU.COMM_DATA.AMT = 0;
                LNCCNBU.COMM_DATA.AS_IND = 'I';
                LNCCNBU.COMM_DATA.RVS_IND = 'N';
                LNCCNBU.COMM_DATA.TXN_FLG = 'N';
                LNCCNBU.COMM_DATA.ACM_EVCD = "DY";
                S000_CALL_LNZCNBU();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_SET_ACCRUAL_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACCR_CTNR_ID[2-1].WS_ACCR_ID);
        CEP.TRC(SCCGWA, WS_ACCR_CTNR_ID[3-1].WS_ACCR_ID);
        IBS.init(SCCGWA, LNCCNBU);
        LNCCNBU.COMM_DATA.LN_AC = LNCIPST.COMM_DATA.LN_AC;
        LNCCNBU.COMM_DATA.SUF_NO = LNCIPST.COMM_DATA.SUF_NO;
        LNCCNBU.COMM_DATA.VAL_DATE = LNCIPST.COMM_DATA.END_DATE;
        if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'A') {
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'B') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACCR_CTNR_ID[2-1].WS_ACCR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
        } else if (LNCIPST.COMM_DATA.ACCRU_TYPE == 'C') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACCR_CTNR_ID[3-1].WS_ACCR_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCCNBU.COMM_DATA.AMT = WS_PST_AMT;
        LNCCNBU.COMM_DATA.AS_IND = 'I';
        LNCCNBU.COMM_DATA.RVS_IND = 'N';
        LNCCNBU.COMM_DATA.TXN_FLG = 'N';
        LNCCNBU.COMM_DATA.ACM_EVCD = "DY";
        S000_CALL_LNZCNBU();
        if (pgmRtn) return;
    }
    public void T000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONT_FLG = 'Y';
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCLOANM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCLOANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            CEP.TRC(SCCGWA, LNCIPST.RC.RC_RTNCODE);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCICUT.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCICUT.RC.RC_RTNCODE;
            CEP.TRC(SCCGWA, LNCIPST.RC.RC_RTNCODE);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLCCM() throws IOException,SQLException,Exception {
        if (LNCIPST.COMM_DATA.ACCEPT_FLG == 'Y') {
            LNCLCCM.COMM_DATA.ACCEPT_FLG = 'Y';
            LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CONTRACT_TYPE = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CONTRACT_TYPE;
            LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_MAT_DATE = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_MAT_DATE;
            LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CCY = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CCY;
            LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_SYS_FLG = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_SYS_FLG;
            LNCLCCM.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CTL_FLG = LNCIPST.COMM_DATA.ACCEPT_DATA.CONT_DATA.CONT_CTL_FLG;
        }
        IBS.CALLCPN(SCCGWA, "LN-FUN-LC-CMP", LNCLCCM);
        if (LNCLCCM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCLCCM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCLCCM.RC.RC_RTNCODE;
            CEP.TRC(SCCGWA, LNCLCCM.RC.RC_RTNCODE);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZINEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCICUT.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCICUT.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            CEP.TRC(SCCGWA, LNCIPST.RC.RC_RTNCODE);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIPST.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCIPST=");
            CEP.TRC(SCCGWA, LNCIPST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
