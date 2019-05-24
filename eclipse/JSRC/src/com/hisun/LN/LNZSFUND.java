package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSFUND {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    LNZSFUND_WS_DATA_OUTPUT WS_DATA_OUTPUT;
    brParm LNTFUND_BR = new brParm();
    DBParm LNTFUND_RD;
    DBParm LNTLOAN_RD;
    DBParm LNTICTL_RD;
    brParm LNTLOAN_BR = new brParm();
    boolean pgmRtn = false;
    LNZSFUND_WS_VARIABLES WS_VARIABLES = new LNZSFUND_WS_VARIABLES();
    LNZSFUND_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new LNZSFUND_WS_BROWSE_OUTPUT();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNRRELA LNRRELA = new LNRRELA();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRICTL_WS_DB_VARS WS_DB_VARS = new LNRICTL_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSFUND LNCSFUND;
    public void MP(SCCGWA SCCGWA, LNCSFUND LNCSFUND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSFUND = LNCSFUND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSFUND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRFUND.RC.RC_MMO = "LN";
        LNCRFUND.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSFUND.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSFUND.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSFUND.FUNC == 'M') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSFUND.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSFUND.FUNC == 'B') {
            B050_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSFUND.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_QUERY_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = LNRFUND.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "LNFUND";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_VARIABLES.WS_SFUND_PROJ_NO.SFUND_PROJ_YYYY = 0;
        else WS_VARIABLES.WS_SFUND_PROJ_NO.SFUND_PROJ_YYYY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1).trim().length() == 0) WS_VARIABLES.WS_SFUND_PROJ_NO.SFUND_PROJ_NNNNN = 0;
        else WS_VARIABLES.WS_SFUND_PROJ_NO.SFUND_PROJ_NNNNN = Integer.parseInt(JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_SFUND_PROJ_NO);
        LNCSFUND.DATA.KEY.PROJ_NO = Integer.parseInt(JIBS_tmp_str[0]);
        B020_CHECK_ADD_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'A';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        LNRFUND.PROJ_NM = LNCSFUND.PROJ_NM;
        LNRFUND.CI_NO = LNCSFUND.CI_NO;
        LNRFUND.FUND_AC = LNCSFUND.FUND_AC;
        LNRFUND.PAY_P_AC = LNCSFUND.P_AC;
        LNRFUND.PAY_I_AC = LNCSFUND.I_AC;
        LNRFUND.STATUS = 'A';
        LNRFUND.BOOK_BR = LNCSFUND.BOOK_BR;
        LNRFUND.PAY_FLG = LNCSFUND.PAY_FLG;
        LNRFUND.TRANS_FREQ = LNCSFUND.TRANS_FREQ;
        LNRFUND.TOT_PAY_P = LNCSFUND.TOT_PAY_P;
        LNRFUND.TOT_PAY_I = LNCSFUND.TOT_PAY_I;
        LNRFUND.REMARK = LNCSFUND.SMR;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        B030_CHECK_UPDATE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'R';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        WS_VARIABLES.FUND_STATUS = LNRFUND.STATUS;
        WS_VARIABLES.FUND_CRT_DATE = LNRFUND.CRT_DATE;
        WS_VARIABLES.FUND_CRT_TLR = LNRFUND.CRT_TLR;
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'U';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        LNRFUND.PROJ_NM = LNCSFUND.PROJ_NM;
        LNRFUND.CI_NO = LNCSFUND.CI_NO;
        LNRFUND.FUND_AC = LNCSFUND.FUND_AC;
        LNRFUND.PAY_P_AC = LNCSFUND.P_AC;
        LNRFUND.PAY_I_AC = LNCSFUND.I_AC;
        LNRFUND.STATUS = 'M';
        LNRFUND.BOOK_BR = LNCSFUND.BOOK_BR;
        LNRFUND.PAY_FLG = LNCSFUND.PAY_FLG;
        LNRFUND.TRANS_FREQ = LNCSFUND.TRANS_FREQ;
        LNRFUND.TOT_PAY_P = LNCSFUND.TOT_PAY_P;
        LNRFUND.TOT_PAY_I = LNCSFUND.TOT_PAY_I;
        LNRFUND.REMARK = LNCSFUND.SMR;
        LNRFUND.CRT_DATE = WS_VARIABLES.FUND_CRT_DATE;
        LNRFUND.CRT_TLR = WS_VARIABLES.FUND_CRT_TLR;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        B040_CHECK_DELETE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'R';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        LNCRFUND.FUNC = 'U';
        LNRFUND.STATUS = 'D';
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD);
        if (LNCSFUND.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATE.CURR_PAGE = (short) LNCSFUND.PAGE_NUM;
        }
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
        WS_VARIABLES.WS_DATE.LAST_PAGE = 'N';
        WS_VARIABLES.WS_DATE.PAGE_ROW = (short) LNCSFUND.PAGE_ROW;
        WS_DATA_OUTPUT = new LNZSFUND_WS_DATA_OUTPUT();
        WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAGE_ROW);
        WS_VARIABLES.WS_DATE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        B050_CHECK_BROWSE_DATA();
        if (pgmRtn) return;
        if (LNCSFUND.DATA.KEY.PROJ_NO == 0 
            && LNCSFUND.CI_NO.trim().length() == 0) {
            B050_BROWSE_RECORD_BY_ALL();
            if (pgmRtn) return;
        }
        if (LNCSFUND.DATA.KEY.PROJ_NO != 0) {
            B050_BROWSE_RECORD_BY_KEY();
            if (pgmRtn) return;
        }
        if (LNCSFUND.CI_NO.trim().length() > 0 
            && LNCSFUND.DATA.KEY.PROJ_NO == 0) {
            B050_BROWSE_RECORD_BY_CI_NO();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_QUERY_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNCRFUND.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_FUND_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_ADD_DATA() throws IOException,SQLException,Exception {
        B050_CHECK_BROWSE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNCRFUND.RETURN_INFO == 'F') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_FUND_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_UPDATE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNCRFUND.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_FUND_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSFUND.PAY_FLG == 'N' 
            && LNRFUND.PAY_FLG == 'Y' 
            && (LNRFUND.TOT_PAY_P > 0 
            || LNRFUND.TOT_PAY_P > LNRFUND.TOT_PAY_I)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAY_NOT_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B050_CHECK_BROWSE_DATA();
        if (pgmRtn) return;
    }
    public void B040_CHECK_DELETE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRFUND);
        IBS.init(SCCGWA, LNRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNCRFUND.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_FUND_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.LOOP_FLG = ' ';
        WS_VARIABLES.LOAN_FLG = ' ';
        IBS.init(SCCGWA, LNRLOAN);
        WS_VARIABLES.LOOP_FLG = 'Y';
        WS_VARIABLES.LOAN_FLG = 'N';
        LNRLOAN.PD_PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        T000_STARTBR_LNTLOAN();
        if (pgmRtn) return;
        T000_READNEXT_LNTLOAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, " ");
        while (WS_VARIABLES.LOOP_FLG != 'N') {
            CEP.TRC(SCCGWA, " ");
            IBS.init(SCCGWA, LNRICTL);
            LNRICTL.KEY.CONTRACT_NO = LNRLOAN.KEY.CONTRACT_NO;
            T000_READ_LNTICTL();
            if (pgmRtn) return;
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (!LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && !LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, " ");
                WS_VARIABLES.LOAN_FLG = 'Y';
                WS_VARIABLES.LOOP_FLG = 'N';
            }
            T000_READNEXT_LNTLOAN();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTLOAN();
        if (pgmRtn) return;
        if (WS_VARIABLES.LOAN_FLG == 'Y') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_SURPLUS_RELATE_CTA;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_CHECK_BROWSE_DATA() throws IOException,SQLException,Exception {
        if (LNCSFUND.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = LNCSFUND.CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
    }
    public void B050_BROWSE_RECORD_BY_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_ALL();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (WS_VARIABLES.REC_FUND_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            for (WS_VARIABLES.WS_DATE.IDX = 1; WS_VARIABLES.REC_FUND_FLG != 'N' 
                && (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW); WS_VARIABLES.WS_DATE.IDX += 1) {
                B300_OUTPUT_MPAG_PROCESS();
                if (pgmRtn) return;
                WS_DB_VARS.START_NUM += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.REC_FUND_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_DATA_OUTPUT = new LNZSFUND_WS_DATA_OUTPUT();
                WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
            } else {
                R000_GROUP_ALL();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_DATA_OUTPUT = new LNZSFUND_WS_DATA_OUTPUT();
            WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN531";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 9814;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_RECORD_BY_KEY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRFUND);
        IBS.init(SCCGWA, LNRFUND);
        LNRFUND.KEY.PROJ_NO = LNCSFUND.DATA.KEY.PROJ_NO;
        LNCRFUND.FUNC = 'I';
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (!LNCSFUND.CI_NO.equalsIgnoreCase(LNRFUND.CI_NO) 
            && LNCSFUND.CI_NO.trim().length() > 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.INPUT_CINO_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = LNRFUND.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NM);
        LNCSFUND.CI_CNM = CICCUST.DATA.CI_NM;
        WS_VARIABLES.WS_DATE.PAGE_ROW = 1;
        WS_DATA_OUTPUT = new LNZSFUND_WS_DATA_OUTPUT();
        WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        WS_VARIABLES.WS_DATE.IDX = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = 'Y';
        B300_OUTPUT_MPAG_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN531";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 9814;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_RECORD_BY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = LNCSFUND.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NM);
        LNCSFUND.CI_CNM = CICCUST.DATA.CI_NM;
        LNRFUND.CI_NO = LNCSFUND.CI_NO;
        T000_STARTBR_PROC_BY_CI_NO();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (WS_VARIABLES.REC_FUND_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            for (WS_VARIABLES.WS_DATE.IDX = 1; WS_VARIABLES.REC_FUND_FLG != 'N' 
                && (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW); WS_VARIABLES.WS_DATE.IDX += 1) {
                B300_OUTPUT_MPAG_PROCESS();
                if (pgmRtn) return;
                WS_DB_VARS.START_NUM += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.REC_FUND_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_DATA_OUTPUT = new LNZSFUND_WS_DATA_OUTPUT();
                WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
            } else {
                R000_GROUP_ALL();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_DATA_OUTPUT = new LNZSFUND_WS_DATA_OUTPUT();
            WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN531";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 9814;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_VARIABLES.WS_OUTPUT.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        WS_VARIABLES.WS_OUTPUT.PROJ_NO = LNRFUND.KEY.PROJ_NO;
        WS_VARIABLES.WS_OUTPUT.PROJ_NM = LNRFUND.PROJ_NM;
        WS_VARIABLES.WS_OUTPUT.CI_NO = LNRFUND.CI_NO;
        WS_VARIABLES.WS_OUTPUT.MBANK_BR = LNRFUND.BOOK_BR;
        WS_VARIABLES.WS_OUTPUT.FUND_AC = LNRFUND.FUND_AC;
        WS_VARIABLES.WS_OUTPUT.P_AC = LNRFUND.PAY_P_AC;
        WS_VARIABLES.WS_OUTPUT.I_AC = LNRFUND.PAY_I_AC;
        WS_VARIABLES.WS_OUTPUT.PA_MIDFG = LNRFUND.PAY_FLG;
        WS_VARIABLES.WS_OUTPUT.STS = LNRFUND.STATUS;
        WS_VARIABLES.WS_OUTPUT.PA_MIDTP = LNRFUND.TRANS_FREQ;
        WS_VARIABLES.WS_OUTPUT.SMR = LNRFUND.REMARK;
        SCCFMT.FMTID = "LN530";
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_OUTPUT;
        SCCFMT.DATA_LEN = 621;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.PROJ_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.PROJ_NM);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CI_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CI_CNM);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.FUND_AC);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.P_AC);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.I_AC);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.SMR);
    }
    public void B300_OUTPUT_MPAG_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA_OUTPUT);
        WS_DATA_OUTPUT.B_PROJ_NO = LNRFUND.KEY.PROJ_NO;
        WS_DATA_OUTPUT.B_PROJ_NM = LNRFUND.PROJ_NM;
        WS_DATA_OUTPUT.B_CI_NO = LNRFUND.CI_NO;
        WS_DATA_OUTPUT.B_CI_CNM = LNCSFUND.CI_CNM;
        WS_DATA_OUTPUT.B_FUND_AC = LNRFUND.FUND_AC;
        WS_DATA_OUTPUT.B_P_AC = LNRFUND.PAY_P_AC;
        WS_DATA_OUTPUT.B_I_AC = LNRFUND.PAY_I_AC;
        WS_DATA_OUTPUT.B_STATUS = LNRFUND.STATUS;
        WS_DATA_OUTPUT.B_BOOK_BR = LNRFUND.BOOK_BR;
        WS_DATA_OUTPUT.B_PAY_FLG = LNRFUND.PAY_FLG;
        WS_DATA_OUTPUT.B_TRANS_FREQ = LNRFUND.TRANS_FREQ;
        WS_DATA_OUTPUT.B_TOT_PAY_P = LNRFUND.TOT_PAY_P;
        WS_DATA_OUTPUT.B_TOT_PAY_I = LNRFUND.TOT_PAY_I;
        WS_DATA_OUTPUT.B_SMR = LNRFUND.REMARK;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_PROJ_NO);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_PROJ_NM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_CI_NO);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_CI_CNM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_FUND_AC);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_P_AC);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_I_AC);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.get(WS_VARIABLES.WS_DATE.IDX-1).B_SMR);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        if (LNCSFUND.CI_NO.trim().length() > 0) {
            T000_01_GROUP_REC_PROC();
            if (pgmRtn) return;
        } else {
            T000_02_GROUP_REC_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.TOTAL_NUM);
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTFUND_BR.rp = new DBParm();
        LNTFUND_BR.rp.TableName = "LNTFUND";
        LNTFUND_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRFUND, LNTFUND_BR);
    }
    public void T000_STARTBR_PROC_BY_CI_NO() throws IOException,SQLException,Exception {
        LNTFUND_BR.rp = new DBParm();
        LNTFUND_BR.rp.TableName = "LNTFUND";
        LNTFUND_BR.rp.where = "CI_NO = :LNRFUND.CI_NO";
        LNTFUND_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRFUND, this, LNTFUND_BR);
    }
    public void T000_01_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        LNTFUND_RD.set = "WS-CNT=COUNT(*)";
        LNTFUND_RD.where = "CI_NO = :LNRFUND.CI_NO";
        IBS.GROUP(SCCGWA, LNRFUND, this, LNTFUND_RD);
    }
    public void T000_02_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        LNTFUND_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, LNRFUND, this, LNTFUND_RD);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRFUND, this, LNTFUND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.REC_FUND_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTFUND_BR);
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRFUND.KEY.PROJ_NO);
        CEP.TRC(SCCGWA, LNCRFUND);
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 582;
        CEP.TRC(SCCGWA, LNRFUND);
        CEP.TRC(SCCGWA, LNCRFUND);
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F' 
            && LNCRFUND.RETURN_INFO != 'E' 
            && LNCRFUND.RETURN_INFO != 'N') {
            WS_VARIABLES.ERR_MSG = "" + LNCRFUND.RC.RC_CODE;
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG = "0" + WS_VARIABLES.ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        LNTLOAN_RD.where = "PD_PROJ_NO = :LNRLOAN.PD_PROJ_NO";
        LNTLOAN_RD.fst = true;
        IBS.READ(SCCGWA, LNRLOAN, this, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "11111");
            WS_VARIABLES.REC_LOAN_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "22222");
            WS_VARIABLES.REC_LOAN_FLG = 'N';
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_VARIABLES.ERR_MSG = ERROR_MSG.ICTL_NFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_BR.rp = new DBParm();
        LNTLOAN_BR.rp.TableName = "LNTLOAN";
        LNTLOAN_BR.rp.where = "PD_PROJ_NO = :LNRLOAN.PD_PROJ_NO";
        LNTLOAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRLOAN, this, LNTLOAN_BR);
    }
    public void T000_READNEXT_LNTLOAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRLOAN, this, LNTLOAN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_VARIABLES.LOOP_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTLOAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTLOAN_BR);
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
