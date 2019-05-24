package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPREQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BigDecimal bigD;
    brParm LNTINTA_BR = new brParm();
    DBParm LNTCTPY_RD;
    DBParm LNTCONT_RD;
    DBParm LNTRELA_RD;
    DBParm LNTSUBS_RD;
    brParm LNTSETL_BR = new brParm();
    DBParm LNTRCVD_RD;
    DBParm LNTPAIP_RD;
    String WK_SEQ_TYPE = "SEQ ";
    String WK_SEQ_DETAIL_CD = "LN-APT-REF";
    String K_INT_DB_30E = "05";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_FMT_ID = "LN411";
    String WS_ERR_MSG = " ";
    double WS_TOT_PAY = 0;
    double WS_PC_RATE = 0;
    double WS_PC_RATE1 = 0;
    double WS_PC_AMT = 0;
    double WS_TOT_AMT = 0;
    double WS_TOT_AMT1 = 0;
    double WS_SPE_INT = 0;
    double WS_TOT_INT = 0;
    String WS_CTL_STSW = " ";
    short WS_TOT_TERM = 0;
    double WS_PROJ_INT_AMT = 0;
    double WS_JIE_INT_AMT = 0;
    short WS_DAYS = 0;
    short WS_INTBAS_DAYS = 0;
    double WS_SUBS_RAT = 0;
    char WS_AMT_TYP = ' ';
    double WS_SUBS_PCT = 0;
    double WS_FIXED_INT = 0;
    double WS_INST_AMT = 0;
    double WS_I_DIFF_AMT = 0;
    double WS_PROJ_PRIN = 0;
    double WS_OVD_AMT = 0;
    double WS_DUE_AMT = 0;
    short WS_IDXA = 0;
    double WS_BAL = 0;
    double WS_TMP_AMT = 0;
    long WS_TMP_AMT0 = 0;
    double WS_TMP_AMT1 = 0;
    double WS_TMP_AMT2 = 0;
    double WS_ROUNDED_AMT = 0;
    char WS_RND_MTH = ' ';
    short WS_IC_CAL_PHS_NO = 0;
    short WS_IC_CUR_TERM = 0;
    LNZSPREQ_WS_AC_INFO[] WS_AC_INFO = new LNZSPREQ_WS_AC_INFO[5];
    LNZSPREQ_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSPREQ_WS_LOAN_CONT_AREA();
    double WS_BAO_FEE = 0;
    int WS_NUM = 0;
    double WS_P = 0;
    double WS_I = 0;
    double WS_PC = 0;
    int WS_D1 = 0;
    int WS_D2 = 0;
    double WS_ADJ_INT_AMT = 0;
    int WS_ADJ_VAL_DT = 0;
    char WS_INT_MODE_FLAG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_READ_LNTRELA_FLG = ' ';
    char WS_SETL_FOUND_FLG = ' ';
    char WS_RCVD_OVER_DUE_FLG = ' ';
    char WS_SUBS_FLG = ' ';
    char WS_XB_FLG = ' ';
    char WS_GROUPLOAN_FLG = ' ';
    LNZSPREQ_WS_OUT_DATA WS_OUT_DATA = new LNZSPREQ_WS_OUT_DATA();
    char WS_INTA_FOUND_FLG = ' ';
    LNRICTL LNRICTL = new LNRICTL();
    LNCICUT LNCICUT = new LNCICUT();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRCTPY LNRCTPY = new LNRCTPY();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    BPCIDAY BPCIDAY = new BPCIDAY();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNRSETL LNRSETL = new LNRSETL();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNRRCVD LNRRCVD = new LNRRCVD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRPARS LNRPARS = new LNRPARS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNRINTA LNRINTA = new LNRINTA();
    short WS_SEQ_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSPREQ LNCSPREQ;
    public LNZSPREQ() {
        for (int i=0;i<5;i++) WS_AC_INFO[i] = new LNZSPREQ_WS_AC_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCSPREQ LNCSPREQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPREQ = LNCSPREQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZSPREQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSPREQ.RC.RC_APP = "LN";
        LNCSPREQ.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B050_BORR_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSPREQ.CTA);
        CEP.TRC(SCCGWA, LNCSPREQ.TR_VAL_DTE);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.PC_RATE);
        CEP.TRC(SCCGWA, LNCSPREQ.PC_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_AMT);
        if (LNCSPREQ.FUN_CODE == 'I'
            || LNCSPREQ.FUN_CODE == 'U') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = " INVALID FUNC(" + LNCSPREQ.FUN_CODE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (LNCSPREQ.CTA.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, LNCSPREQ.CTA);
        }
        WS_INT_MODE_FLAG = LNCSPREQ.INT_MODE;
        if ((WS_INT_MODE_FLAG != '0' 
            && WS_INT_MODE_FLAG != '1' 
            && WS_INT_MODE_FLAG != '2')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNCSPREQ.TR_VAL_DTE == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, LNCSPREQ.TR_VAL_DTE);
        }
        R000_CHECK_CTA_NO_VALID();
        R000_CHECK_CTA_STS();
        if (LNCSPREQ.SUB_TRAN == ' ') {
            B025_GET_CTPY_INFO();
        }
    }
    public void R000_CHECK_CTA_NO_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSPREQ.CTA;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.PROD_FLG = 'Y';
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.DOMI_BR);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CCY);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PROD_CD);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_CNAME);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PRIN);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.TOT_BAL);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PROD_NAME);
        LNCSPREQ.BR = LNCCLNQ.DATA.DOMI_BR;
        LNCSPREQ.CCY = LNCCLNQ.DATA.CCY;
        LNCSPREQ.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCSPREQ.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCSPREQ.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCSPREQ.PRINCIPA = LNCCLNQ.DATA.PRIN;
        LNCSPREQ.BALANCE = LNCCLNQ.DATA.TOT_BAL;
        LNCSPREQ.PROD_DE = LNCCLNQ.DATA.PROD_NAME;
        if (LNCCLNQ.DATA.DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ICTL_MAT;
            S000_ERR_MSG_PROC();
        }
        if (LNCCLNQ.DATA.DUE_DT <= LNCSPREQ.TR_VAL_DTE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BIG_LOAN_DUE_DT;
            S000_ERR_MSG_PROC();
        }
        if (LNCSPREQ.TR_VAL_DTE < LNCCLNQ.DATA.LAST_F_VAL_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_LSTDT;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_CTA_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        WS_CTL_STSW = LNRICTL.CTL_STSW;
        WS_IC_CAL_PHS_NO = LNRICTL.IC_CAL_PHS_NO;
        WS_IC_CUR_TERM = LNRICTL.IC_CUR_TERM;
        CEP.TRC(SCCGWA, WS_CTL_STSW);
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLOSED;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        S000_CALL_LNZAPRDM();
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0' 
            && LNCSPREQ.TR_VAL_DTE < LNRICTL.IC_CAL_VAL_DT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LT_INT_CUT_DT;
            S000_ERR_MSG_PROC();
        }
        if (LNCSPREQ.PC_RATE == 0 
            && LNCSPREQ.PC_AMT == 0) {
            LNCSPREQ.PC_RATE = LNCAPRDM.REC_DATA.PREP_CHG_RATE;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            && (LNCSPREQ.PC_AMT != 0 
            || LNCSPREQ.PC_RATE != 0)) {
            LNCSPREQ.PC_RATE = 0;
            LNCSPREQ.PC_AMT = 0;
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WAN_FEE_NOT_CHGED;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.REPY_STS = '2';
        T000_READ_LNTRCVD();
        if (WS_RCVD_OVER_DUE_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_P_IC_CUR_TERM;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        WS_BAL = WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[2-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[4-1].WS_LOAN_BAL;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_INT_MODE_FLAG != '1') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_XB_INT_MODE;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, LNCSPREQ.TOT_P_AMT);
            CEP.TRC(SCCGWA, WS_BAL);
            if (LNCSPREQ.TOT_P_AMT != WS_BAL) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_XB_PPAY;
                S000_ERR_MSG_PROC();
            }
            WS_XB_FLG = 'Y';
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS;
            S000_ERR_MSG_PROC();
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS;
            S000_ERR_MSG_PROC();
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRRELA);
            LNRRELA.KEY.CONTRACT_NO = LNCSPREQ.CTA;
            LNRRELA.KEY.TYPE = 'S';
            T000_READ_LNTRELA();
            IBS.init(SCCGWA, LNRSUBS);
            LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
            T000_READ_LNTSUBS();
            if (LNRSUBS.SUBS_MTH == '1' 
                || LNRSUBS.SUBS_MTH == '4' 
                || LNRSUBS.SUBS_MTH == '5') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SUBS_FIXINT;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            WS_GROUPLOAN_FLG = 'Y';
            CEP.TRC(SCCGWA, "YINTUAN");
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if ((WS_CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1") 
            || WS_GROUPLOAN_FLG == 'Y') 
            && (LNCSPREQ.PC_AMT != 0 
            || LNCSPREQ.PC_RATE != 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER_PC, LNCSPREQ.RC);
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCSPREQ.CCY;
        S000_CALL_BPZQCCY();
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
    }
    public void B025_GET_CTPY_INFO() throws IOException,SQLException,Exception {
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRCTPY);
            LNRCTPY.KEY.CONTRACT_NO = LNCSPREQ.CTA;
            LNRCTPY.KEY.TR_TYP = '1';
            T000_READ_LNTCTPY();
            LNCSPREQ.T_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
            LNCSPREQ.T_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
            LNCSPREQ.T_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        }
    }
    public void B050_BORR_PROCESS() throws IOException,SQLException,Exception {
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            B0500_00_SUBS_PROC();
        }
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
        } else {
            CEP.TRC(SCCGWA, LNCSPREQ.TOT_AMT);
            CEP.TRC(SCCGWA, LNCSPREQ.TOT_P_AMT);
            if (LNCSPREQ.TOT_AMT != 0 
                && LNCSPREQ.TOT_P_AMT == 0) {
                B050_04_COMPUTE_P_INT_AMT();
            } else {
                B050_01_COMPUTE_INT();
                B050_03_COMPUTE_FEE();
            }
        }
        CEP.TRC(SCCGWA, LNCSPREQ.PC_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_P_AMT);
        B050_05_COMPUTE_TOT_AMT();
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_AMT);
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            B050_06_COMP_TRUS_HRGTAX();
        }
        if (WS_GROUPLOAN_FLG == 'Y') {
            B050_08_AMT_DISTRIBUTION();
        }
        B050_07_BORR_MEMORY();
    }
    public void B0500_00_SUBS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNRRELA.KEY.TYPE = 'S';
        T000_READ_LNTRELA();
        if (WS_READ_LNTRELA_FLG == 'F') {
            WS_TOT_TERM = (short) (LNRRELA.ST_TERM + LNRRELA.TERM - 1);
            CEP.TRC(SCCGWA, LNRRELA.ST_TERM);
            CEP.TRC(SCCGWA, LNRRELA.TERM);
            CEP.TRC(SCCGWA, WS_TOT_TERM);
            if (LNRRELA.ST_TERM <= WS_IC_CUR_TERM 
                && WS_IC_CUR_TERM <= WS_TOT_TERM) {
                WS_SUBS_FLG = 'Y';
            }
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOFND_RELA;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_04_COMPUTE_P_INT_AMT() throws IOException,SQLException,Exception {
        if (LNCSPREQ.PC_AMT == 0) {
            WS_PC_RATE = LNCSPREQ.PC_RATE;
            WS_TOT_AMT = LNCSPREQ.TOT_AMT;
        } else {
            WS_PC_RATE = 0;
            WS_TOT_AMT = LNCSPREQ.TOT_AMT - LNCSPREQ.PC_AMT;
        }
        if (LNCSPREQ.INT_MODE == '0') {
            WS_TOT_PAY = WS_TOT_AMT / ( 1 + ( WS_PC_RATE / 100 ) );
            bigD = new BigDecimal(WS_TOT_PAY);
            WS_TOT_PAY = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            WS_TMP_AMT = WS_TOT_PAY;
            if (WS_TMP_AMT > 0) {
                R000_ROUND_PROCESS();
                WS_TOT_PAY = WS_ROUNDED_AMT;
            }
        }
        if (LNCSPREQ.INT_MODE == '1') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                WS_TOT_INT = 0;
            } else {
                IBS.init(SCCGWA, LNCICUT);
                LNCICUT.COMM_DATA.FUNC_CODE = 'I';
                LNCICUT.COMM_DATA.LN_AC = LNCSPREQ.CTA;
                LNCICUT.COMM_DATA.TYPE = 'I';
                LNCICUT.COMM_DATA.AMT = 0;
                LNCICUT.COMM_DATA.END_DATE = LNCSPREQ.TR_VAL_DTE;
                S000_CALL_LNZICUT();
                CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
                WS_TOT_INT = LNCICUT.COMM_DATA.INT_AMT;
                if (WS_TOT_INT != 0 
                    && WS_SUBS_FLG == 'Y') {
                    WS_JIE_INT_AMT = WS_TOT_INT;
                    R000_GET_PROJ_AMT();
                    WS_TOT_INT = WS_JIE_INT_AMT;
                    LNCSPREQ.PROJ_INT = WS_PROJ_INT_AMT;
                }
                WS_TMP_AMT = WS_TOT_INT;
                if (WS_TMP_AMT > 0) {
                    R000_ROUND_PROCESS();
                    WS_TOT_INT = WS_ROUNDED_AMT;
                }
            }
            WS_TOT_PAY = ( WS_TOT_AMT - WS_TOT_INT ) / ( 1 + ( WS_PC_RATE / 100 ) );
            bigD = new BigDecimal(WS_TOT_PAY);
            WS_TOT_PAY = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            WS_TMP_AMT = WS_TOT_PAY;
            if (WS_TMP_AMT > 0) {
                R000_ROUND_PROCESS();
                WS_TOT_PAY = WS_ROUNDED_AMT;
            }
        }
        if (LNCSPREQ.INT_MODE == '2') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                WS_SPE_INT = 0;
            } else {
                IBS.init(SCCGWA, LNCICUT);
                LNCICUT.COMM_DATA.FUNC_CODE = 'I';
                LNCICUT.COMM_DATA.LN_AC = LNCSPREQ.CTA;
                LNCICUT.COMM_DATA.TYPE = 'I';
                LNCICUT.COMM_DATA.AMT = 10000;
                LNCICUT.COMM_DATA.END_DATE = LNCSPREQ.TR_VAL_DTE;
                S000_CALL_LNZICUT();
                CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.SPE_INT);
                WS_SPE_INT = LNCICUT.COMM_DATA.SPE_INT;
            }
            WS_TOT_AMT1 = WS_TOT_AMT;
            WS_PC_RATE1 = WS_PC_RATE;
            WS_TOT_PAY = ( 10000 * WS_TOT_AMT1 ) / ( 10000 + WS_SPE_INT + ( WS_PC_RATE1 * 100 ) );
            bigD = new BigDecimal(WS_TOT_PAY);
            WS_TOT_PAY = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            if (WS_SPE_INT != 0 
                && WS_SUBS_FLG == 'Y') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INT_APART_SUBS;
                S000_ERR_MSG_PROC();
            }
            WS_TMP_AMT = WS_TOT_PAY;
            if (WS_TMP_AMT > 0) {
                R000_ROUND_PROCESS();
                WS_TOT_PAY = WS_ROUNDED_AMT;
            }
            WS_PC_AMT = WS_TOT_PAY * ( WS_PC_RATE / 100 );
            bigD = new BigDecimal(WS_PC_AMT);
            WS_PC_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            WS_TMP_AMT = WS_PC_AMT;
            if (WS_TMP_AMT > 0) {
                R000_ROUND_PROCESS();
                WS_PC_AMT = WS_ROUNDED_AMT;
            }
            WS_TOT_INT = WS_TOT_AMT - WS_TOT_PAY - WS_PC_AMT;
        }
        if (WS_PC_AMT == 0) {
            WS_PC_AMT = WS_TOT_PAY * ( WS_PC_RATE / 100 );
            bigD = new BigDecimal(WS_PC_AMT);
            WS_PC_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            WS_TMP_AMT = WS_PC_AMT;
            if (WS_TMP_AMT > 0) {
                R000_ROUND_PROCESS();
                WS_PC_AMT = WS_ROUNDED_AMT;
            }
        }
        LNCSPREQ.TOT_P_AMT = WS_TOT_PAY;
        LNCSPREQ.TOT_I_AMT = WS_TOT_INT;
        if (LNCSPREQ.PC_AMT == 0) {
            LNCSPREQ.PC_AMT = WS_PC_AMT;
        }
    }
    public void B050_06_COMP_TRUS_HRGTAX() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
            LNCSPREQ.HRG_AMT = LNCSPREQ.TOT_I_AMT * ( LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100 );
            bigD = new BigDecimal(LNCSPREQ.HRG_AMT);
            LNCSPREQ.HRG_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
        CEP.TRC(SCCGWA, LNCSPREQ.HRG_AMT);
        WS_TMP_AMT = LNCSPREQ.HRG_AMT;
        if (WS_TMP_AMT > 0) {
            R000_ROUND_PROCESS();
            LNCSPREQ.HRG_AMT = WS_ROUNDED_AMT;
        }
    }
    public void B050_01_COMPUTE_INT() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            || LNCSPREQ.INT_MODE == '0') {
            LNCSPREQ.TOT_I_AMT = 0;
        } else {
            IBS.init(SCCGWA, LNCICUT);
            LNCICUT.COMM_DATA.FUNC_CODE = 'I';
            LNCICUT.COMM_DATA.LN_AC = LNCSPREQ.CTA;
            LNCICUT.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
            LNCICUT.COMM_DATA.TYPE = 'I';
            LNCICUT.COMM_DATA.BEG_DATE = LNRICTL.INT_CUT_DT;
            LNCICUT.COMM_DATA.END_DATE = LNCSPREQ.TR_VAL_DTE;
            S000_CALL_LNZICUT();
            if (WS_INT_MODE_FLAG == '0') {
                LNCSPREQ.TOT_I_AMT = 0;
            } else if (WS_INT_MODE_FLAG == '2') {
                B051_PYIF_READ_PROCESS();
                CEP.TRC(SCCGWA, LNCPYIFM.REC_DATA.AMT);
                WS_BAL = LNCPYIFM.REC_DATA.AMT + WS_BAL;
                CEP.TRC(SCCGWA, WS_BAL);
                if (WS_BAL == 0) {
                    LNCSPREQ.TOT_I_AMT = LNCICUT.COMM_DATA.INT_AMT;
                } else {
                    LNCSPREQ.TOT_I_AMT = LNCICUT.COMM_DATA.SPE_INT * LNCSPREQ.TOT_P_AMT / WS_BAL;
                    bigD = new BigDecimal(LNCSPREQ.TOT_I_AMT);
                    LNCSPREQ.TOT_I_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
            } else if (WS_INT_MODE_FLAG == '1') {
                LNCSPREQ.TOT_I_AMT = LNCICUT.COMM_DATA.INT_AMT;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID INT MODE(" + WS_INT_MODE_FLAG + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
        if (WS_SUBS_FLG == 'Y' 
            && LNCSPREQ.TOT_I_AMT != 0) {
            WS_JIE_INT_AMT = LNCSPREQ.TOT_I_AMT;
            R000_GET_PROJ_AMT();
            LNCSPREQ.TOT_I_AMT = WS_JIE_INT_AMT;
            LNCSPREQ.PROJ_INT = WS_PROJ_INT_AMT;
        }
        if (LNCSPREQ.TOT_P_AMT == 0 
            && LNCSPREQ.INT_MODE == '2') {
            LNCSPREQ.TOT_I_AMT = 0;
        }
        WS_TMP_AMT = LNCSPREQ.TOT_I_AMT;
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNRINTA.KEY.SUB_CTA_NO = 0;
        LNRINTA.KEY.REPY_MTH = 'I';
        LNRINTA.KEY.REPY_TERM = LNRICTL.IC_CAL_TERM;
        WS_ADJ_VAL_DT = LNRICTL.INT_CUT_DT;
        WS_TMP_AMT = LNCSPREQ.TOT_I_AMT;
        if (WS_TMP_AMT > 0) {
            R000_ROUND_PROCESS();
            LNCSPREQ.TOT_I_AMT = WS_ROUNDED_AMT;
        }
    }
    public void B050_03_COMPUTE_FEE() throws IOException,SQLException,Exception {
        if (LNCSPREQ.PC_AMT == 0) {
            LNCSPREQ.PC_AMT = LNCSPREQ.TOT_P_AMT * ( LNCSPREQ.PC_RATE / 100 );
            bigD = new BigDecimal(LNCSPREQ.PC_AMT);
            LNCSPREQ.PC_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
        WS_TMP_AMT = LNCSPREQ.PC_AMT;
        if (WS_TMP_AMT > 0) {
            R000_ROUND_PROCESS();
            LNCSPREQ.PC_AMT = WS_ROUNDED_AMT;
        }
    }
    public void B050_05_COMPUTE_TOT_AMT() throws IOException,SQLException,Exception {
        LNCSPREQ.TOT_AMT = LNCSPREQ.TOT_P_AMT + LNCSPREQ.TOT_I_AMT + LNCSPREQ.PC_AMT + LNCSPREQ.TAX_AMT;
    }
    public void B050_07_BORR_MEMORY() throws IOException,SQLException,Exception {
        if (LNCSPREQ.FUN_CODE == 'I') {
            if (LNCSPREQ.SUB_TRAN == ' ') {
                B095_GET_AC_BAL();
                B095_GET_OUTPUT();
            }
        } else if (LNCSPREQ.FUN_CODE == 'U') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = " INVALID FUNC(" + LNCSPREQ.FUN_CODE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B050_08_AMT_DISTRIBUTION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPAMT);
        LNCIPAMT.CTA_NO = LNCSPREQ.CTA;
        LNCIPAMT.P_AMT = LNCSPREQ.TOT_P_AMT;
        LNCIPAMT.I_AMT = LNCSPREQ.TOT_I_AMT;
        LNCIPAMT.O_AMT = 0;
        LNCIPAMT.L_AMT = 0;
        LNCIPAMT.F_AMT = 0;
        S000_CALL_LNZIPAMT();
        for (WS_NUM = 1; WS_NUM <= 10 
            && LNCIPAMT.PART_DATA[WS_NUM-1].PART_CTA_NO.trim().length() != 0; WS_NUM += 1) {
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_AC = LNCIPAMT.PART_DATA[WS_NUM-1].PART_CTA_NO;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_JOIN_BR = LNCIPAMT.PART_DATA[WS_NUM-1].PART_BR;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_BR = LNCIPAMT.PART_DATA[WS_NUM-1].PART_BR;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_JOIN_NO = LNCIPAMT.PART_DATA[WS_NUM-1].PART_NO;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_SEQ = "" + LNCIPAMT.PART_DATA[WS_NUM-1].PART_NO;
            JIBS_tmp_int = LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_SEQ.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_SEQ = "0" + LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_SEQ;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_JOIN_NM = LNCIPAMT.PART_DATA[WS_NUM-1].PART_NM;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_NM = LNCIPAMT.PART_DATA[WS_NUM-1].PART_NM;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_SYLOAN_FLG = LNCIPAMT.PART_DATA[WS_NUM-1].LOCAL_BANK_FLAG;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_FLG = LNCIPAMT.PART_DATA[WS_NUM-1].LOCAL_BANK_FLAG;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_RLA_TYP = LNCIPAMT.PART_DATA[WS_NUM-1].REL_TYPE;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_TYP = LNCIPAMT.PART_DATA[WS_NUM-1].REL_TYPE;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_PY_P_AMT = LNCIPAMT.PART_DATA[WS_NUM-1].PART_P_AMT;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_P = LNCIPAMT.PART_DATA[WS_NUM-1].PART_P_AMT;
            WS_OUT_DATA.WS_O_JOIN_INFO[WS_NUM-1].WS_O_PY_I_AMT = LNCIPAMT.PART_DATA[WS_NUM-1].PART_I_AMT;
            LNCSPREQ.SYLOAN_INFO[WS_NUM-1].SYLOAN_I = LNCIPAMT.PART_DATA[WS_NUM-1].PART_I_AMT;
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_NUM-1].PART_BR);
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_NUM-1].PART_NO);
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_NUM-1].LOCAL_BANK_FLAG);
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_NUM-1].REL_TYPE);
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_NUM-1].PART_P_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_NUM-1].PART_I_AMT);
        }
    }
    public void B095_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.SETTLE_TYPE = '2';
        WS_SEQ_NO = 0;
        T000_STARTBR_FOR_SEQ_PROC();
        T000_READNEXT_SETL_PROC();
        for (WS_IDXA = 1; WS_IDXA <= 5 
            && WS_SETL_FOUND_FLG != 'N'; WS_IDXA += 1) {
            WS_AC_INFO[WS_IDXA-1].WS_AC_TYP = LNRSETL.AC_TYP;
            WS_AC_INFO[WS_IDXA-1].WS_PAY_AC = LNRSETL.AC;
            WS_AC_INFO[WS_IDXA-1].WS_PAY_CCY = LNRSETL.KEY.CCY;
            if (WS_AC_INFO[WS_IDXA-1].WS_AC_TYP.equalsIgnoreCase("01")
                || WS_AC_INFO[WS_IDXA-1].WS_AC_TYP.equalsIgnoreCase("05")
                || WS_AC_INFO[WS_IDXA-1].WS_AC_TYP.equalsIgnoreCase("06")) {
                B910_GET_DD_AVABAL();
            } else if (WS_AC_INFO[WS_IDXA-1].WS_AC_TYP.equalsIgnoreCase("02")) {
                B920_GET_SUSP_AC_AVABAL();
            } else if (WS_AC_INFO[WS_IDXA-1].WS_AC_TYP.equalsIgnoreCase("03")) {
                B930_GET_NOS_AC_AVABAL();
            } else {
            }
            T000_READNEXT_SETL_PROC();
        }
        T000_ENDBR_SETL_PROC();
    }
    public void B910_GET_DD_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_AC_INFO[WS_IDXA-1].WS_PAY_AC;
        DDCIQBAL.DATA.CCY = WS_AC_INFO[WS_IDXA-1].WS_PAY_CCY;
        S000_CALL_DDZIQBAL();
        WS_AC_INFO[WS_IDXA-1].WS_PAY_AC_BAL = DDCIQBAL.DATA.AVL_BAL;
    }
    public void B920_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_AC_INFO[WS_IDXA-1].WS_PAY_AC;
        AICPQMIB.INPUT_DATA.CCY = WS_AC_INFO[WS_IDXA-1].WS_PAY_CCY;
        S000_CALL_AIZPQMIB();
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
        WS_AC_INFO[WS_IDXA-1].WS_PAY_AC_BAL = AICPQMIB.OUTPUT_DATA.CBAL;
    }
    public void B930_GET_NOS_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.NOSTRO_CD = WS_AC_INFO[WS_IDXA-1].WS_PAY_AC;
        IBCQINF.INPUT_DATA.CCY = WS_AC_INFO[WS_IDXA-1].WS_PAY_CCY;
        S000_CALL_IBZQINF();
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.VALUE_BAL);
        WS_AC_INFO[WS_IDXA-1].WS_PAY_AC_BAL = IBCQINF.OUTPUT_DATA.VALUE_BAL;
    }
    public void B095_GET_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_DATA.WS_O_CTA = LNCSPREQ.CTA;
        WS_OUT_DATA.WS_O_BR = LNCSPREQ.BR;
        WS_OUT_DATA.WS_O_CI_NO = LNCSPREQ.CI_NO;
        WS_OUT_DATA.WS_O_CI_CNM = LNCSPREQ.CI_CNM;
        WS_OUT_DATA.WS_O_PROD_CD = LNCSPREQ.PROD_CD;
        WS_OUT_DATA.WS_O_PROD_DE = LNCSPREQ.PROD_DE;
        WS_OUT_DATA.WS_O_CCY = LNCSPREQ.CCY;
        WS_OUT_DATA.WS_O_PRINCIPAL = LNCSPREQ.PRINCIPA;
        WS_OUT_DATA.WS_O_BALANCE = LNCSPREQ.BALANCE;
        WS_OUT_DATA.WS_O_TR_VAL_DTE = LNCSPREQ.TR_VAL_DTE;
        WS_OUT_DATA.WS_O_TOT_P_AMT = LNCSPREQ.TOT_P_AMT;
        WS_OUT_DATA.WS_O_INT_MODE = LNCSPREQ.INT_MODE;
        WS_OUT_DATA.WS_O_TOT_I_AMT = LNCSPREQ.TOT_I_AMT;
        WS_OUT_DATA.WS_O_PC_RATE = LNCSPREQ.PC_RATE;
        WS_OUT_DATA.WS_O_PC_AMT = LNCSPREQ.PC_AMT;
        WS_OUT_DATA.WS_O_HRG_AMT = LNCSPREQ.HRG_AMT;
        WS_OUT_DATA.WS_O_PROJ_INT = LNCSPREQ.PROJ_INT;
        WS_OUT_DATA.WS_O_TOT_AMT = LNCSPREQ.TOT_AMT;
        WS_OUT_DATA.WS_O_T_I_AMT = LNCSPREQ.T_I_AMT;
        WS_OUT_DATA.WS_O_T_O_AMT = LNCSPREQ.T_O_AMT;
        WS_OUT_DATA.WS_O_T_L_AMT = LNCSPREQ.T_L_AMT;
        WS_OUT_DATA.WS_O_AC_TYP1 = WS_AC_INFO[1-1].WS_AC_TYP;
        WS_OUT_DATA.WS_O_PAY_AC1 = WS_AC_INFO[1-1].WS_PAY_AC;
        WS_OUT_DATA.WS_O_PAY_AC_BAL1 = WS_AC_INFO[1-1].WS_PAY_AC_BAL;
        WS_OUT_DATA.WS_O_AC_TYP2 = WS_AC_INFO[2-1].WS_AC_TYP;
        WS_OUT_DATA.WS_O_PAY_AC2 = WS_AC_INFO[2-1].WS_PAY_AC;
        WS_OUT_DATA.WS_O_PAY_AC_BAL2 = WS_AC_INFO[2-1].WS_PAY_AC_BAL;
        WS_OUT_DATA.WS_O_AC_TYP3 = WS_AC_INFO[3-1].WS_AC_TYP;
        WS_OUT_DATA.WS_O_PAY_AC3 = WS_AC_INFO[3-1].WS_PAY_AC;
        WS_OUT_DATA.WS_O_PAY_AC_BAL3 = WS_AC_INFO[3-1].WS_PAY_AC_BAL;
        WS_OUT_DATA.WS_O_AC_TYP4 = WS_AC_INFO[4-1].WS_AC_TYP;
        WS_OUT_DATA.WS_O_PAY_AC4 = WS_AC_INFO[4-1].WS_PAY_AC;
        WS_OUT_DATA.WS_O_PAY_AC_BAL4 = WS_AC_INFO[4-1].WS_PAY_AC_BAL;
        WS_OUT_DATA.WS_O_AC_TYP5 = WS_AC_INFO[5-1].WS_AC_TYP;
        WS_OUT_DATA.WS_O_PAY_AC5 = WS_AC_INFO[5-1].WS_PAY_AC;
        WS_OUT_DATA.WS_O_PAY_AC_BAL5 = WS_AC_INFO[5-1].WS_PAY_AC_BAL;
        SCCFMT.FMTID = K_FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 4060;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_PROJ_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
        T000_READ_LNTSUBS();
        R000_SUBSIDY_PROCESS();
        CEP.TRC(SCCGWA, WS_PROJ_INT_AMT);
        CEP.TRC(SCCGWA, WS_JIE_INT_AMT);
        if (WS_PROJ_INT_AMT < WS_JIE_INT_AMT) {
            WS_JIE_INT_AMT -= WS_PROJ_INT_AMT;
        } else {
            WS_PROJ_INT_AMT = WS_JIE_INT_AMT;
            WS_JIE_INT_AMT = 0;
        }
    }
    public void R000_SUBSIDY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIDAY);
        BPCIDAY.I_CALR_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        BPCIDAY.I_BEGIN_DATE = LNRICTL.INT_CUT_DT;
        BPCIDAY.I_END_DATE = LNCSPREQ.TR_VAL_DTE;
        S000_CALL_BPZCIDAY();
        WS_DAYS = BPCIDAY.OUTPUT.O_STD_DAYS;
        WS_INTBAS_DAYS = (short) BPCIDAY.OUTPUT.O_LEAP_DAYS;
        WS_INTBAS_DAYS += BPCIDAY.OUTPUT.O_ORD_DAYS;
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_STD_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_LEAP_DAYS);
        CEP.TRC(SCCGWA, BPCIDAY.OUTPUT.O_ORD_DAYS);
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, WS_INTBAS_DAYS);
        CEP.TRC(SCCGWA, LNRSUBS.TYP);
        if (LNRSUBS.TYP == '1') {
            R300_SUBSIDY_PROCESS();
        } else if (LNRSUBS.TYP == '2') {
        } else if (LNRSUBS.TYP == '3') {
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void R300_SUBSIDY_PROCESS() throws IOException,SQLException,Exception {
        WS_AMT_TYP = LNRSUBS.AMT_TYP;
        WS_SUBS_PCT = LNRSUBS.SUBS_PCT;
        WS_SUBS_RAT = LNRSUBS.SUBS_RAT;
        WS_FIXED_INT = LNRSUBS.FIXED_AMT;
        if (LNRSUBS.SUBS_MTH == '1') {
            B214_SUBP_MTH1();
        } else if (LNRSUBS.SUBS_MTH == '2') {
            B214_SUBP_MTH2();
        } else if (LNRSUBS.SUBS_MTH == '3') {
            B214_SUBP_MTH3();
        } else if (LNRSUBS.SUBS_MTH == '4') {
            B214_SUBP_MTH4();
        } else if (LNRSUBS.SUBS_MTH == '5') {
            B214_SUBP_MTH5();
        } else if (LNRSUBS.SUBS_MTH == '6') {
            B214_SUBP_MTH6();
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B214_SUBP_MTH1() throws IOException,SQLException,Exception {
        WS_PROJ_INT_AMT = WS_JIE_INT_AMT;
    }
    public void B214_SUBP_MTH2() throws IOException,SQLException,Exception {
        B2S4_INQ_SCH_BAL();
        WS_PROJ_INT_AMT = WS_PROJ_PRIN * WS_SUBS_RAT * WS_INTBAS_DAYS / ( 100 * WS_DAYS );
        bigD = new BigDecimal(WS_PROJ_INT_AMT);
        WS_PROJ_INT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void B214_SUBP_MTH3() throws IOException,SQLException,Exception {
        if (WS_AMT_TYP == '1') {
            WS_PROJ_INT_AMT = LNCSPREQ.BALANCE * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_PROJ_INT_AMT);
            WS_PROJ_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (WS_PROJ_INT_AMT > WS_JIE_INT_AMT) {
                WS_PROJ_INT_AMT = WS_JIE_INT_AMT;
            }
        } else if (WS_AMT_TYP == '2') {
            IBS.init(SCCGWA, LNRPAIP);
            LNRPAIP.KEY.CONTRACT_NO = LNCSPREQ.CTA;
            LNRPAIP.KEY.SUB_CTA_NO = 0;
            LNRPAIP.KEY.PHS_NO = WS_IC_CAL_PHS_NO;
            T00_READUP_LNTPAIP();
            WS_PROJ_INT_AMT = ( LNRPAIP.CUR_INST_AMT ) * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_PROJ_INT_AMT);
            WS_PROJ_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (WS_PROJ_INT_AMT > WS_JIE_INT_AMT) {
                WS_PROJ_INT_AMT = WS_JIE_INT_AMT;
            }
        } else if (WS_AMT_TYP == '3') {
            WS_PROJ_INT_AMT = WS_JIE_INT_AMT * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_PROJ_INT_AMT);
            WS_PROJ_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (WS_PROJ_INT_AMT > WS_JIE_INT_AMT) {
                WS_PROJ_INT_AMT = WS_JIE_INT_AMT;
            }
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B214_SUBP_MTH4() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[88-1].WS_LOAN_BAL);
        CEP.TRC(SCCGWA, WS_FIXED_INT);
        if (WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[88-1].WS_LOAN_BAL >= WS_FIXED_INT) {
            WS_FIXED_INT = 0;
        } else {
            WS_FIXED_INT -= WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[88-1].WS_LOAN_BAL;
        }
        CEP.TRC(SCCGWA, WS_FIXED_INT);
        if (WS_FIXED_INT < WS_JIE_INT_AMT) {
            WS_PROJ_INT_AMT = WS_FIXED_INT;
        } else {
            WS_PROJ_INT_AMT = WS_JIE_INT_AMT;
        }
    }
    public void B214_SUBP_MTH5() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[25-1].WS_LOAN_BAL);
        CEP.TRC(SCCGWA, WS_FIXED_INT);
        if (WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[25-1].WS_LOAN_BAL >= WS_FIXED_INT) {
            WS_FIXED_INT = 0;
        } else {
            WS_FIXED_INT -= WS_LOAN_CONT_AREA.REDEFINES43.WS_LOAN_CONT[25-1].WS_LOAN_BAL;
        }
        CEP.TRC(SCCGWA, WS_FIXED_INT);
        if (WS_FIXED_INT < WS_JIE_INT_AMT) {
            WS_PROJ_INT_AMT = WS_JIE_INT_AMT - WS_FIXED_INT;
        } else {
            WS_PROJ_INT_AMT = 0;
        }
    }
    public void B214_SUBP_MTH6() throws IOException,SQLException,Exception {
        B2S4_INQ_SCH_BAL();
        WS_I_DIFF_AMT = WS_PROJ_PRIN * WS_SUBS_RAT * WS_INTBAS_DAYS / ( 100 * WS_DAYS );
        bigD = new BigDecimal(WS_I_DIFF_AMT);
        WS_I_DIFF_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        WS_PROJ_INT_AMT = WS_JIE_INT_AMT - WS_I_DIFF_AMT;
    }
    public void B2S4_INQ_SCH_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSPREQ.CTA;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (LNCSPREQ.INT_MODE == '1') {
            WS_PROJ_PRIN = LNCCNEX.COMM_DATA.INQ_AMT;
        } else {
            WS_PROJ_PRIN = LNCSPREQ.TOT_P_AMT;
        }
    }
    public void T00_STARTBR_LNTINTA() throws IOException,SQLException,Exception {
        LNRINTA.KEY.ADJ_SEQ = 0;
        LNTINTA_BR.rp = new DBParm();
        LNTINTA_BR.rp.TableName = "LNTINTA";
        LNTINTA_BR.rp.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM "
            + "AND ADJ_SEQ > :LNRINTA.KEY.ADJ_SEQ";
        LNTINTA_BR.rp.order = "ADJ_SEQ";
        IBS.STARTBR(SCCGWA, LNRINTA, this, LNTINTA_BR);
    }
    public void T00_READNEXT_LNTINTA() throws IOException,SQLException,Exception {
        WS_INTA_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRINTA, this, LNTINTA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTA_FOUND_FLG = 'Y';
        } else {
            WS_INTA_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void R000_ROUND_PROCESS() throws IOException,SQLException,Exception {
        if (WS_TMP_AMT > 0) {
            JIBS_tmp_str[0] = "" + BPCQCCY.DATA.DEC_MTH;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_RND_MTH = JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).charAt(0);
            if (WS_RND_MTH == '0') {
                WS_TMP_AMT0 = WS_TMP_AMT + 0;
                bigD = new BigDecimal(WS_TMP_AMT0);
                WS_TMP_AMT0 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_ROUNDED_AMT = WS_TMP_AMT0;
            }
            if (WS_RND_MTH == '1') {
                WS_TMP_AMT1 = WS_TMP_AMT + 0;
                bigD = new BigDecimal(WS_TMP_AMT1);
                WS_TMP_AMT1 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_ROUNDED_AMT = WS_TMP_AMT1;
            }
            if (WS_RND_MTH == '2') {
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                WS_TMP_AMT2 = WS_TMP_AMT + 0;
                bigD = new BigDecimal(WS_TMP_AMT2);
                WS_TMP_AMT2 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_ROUNDED_AMT = WS_TMP_AMT2;
            }
        }
        CEP.TRC(SCCGWA, WS_ROUNDED_AMT);
    }
    public void T000_READ_LNTCTPY() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.where = "CONTRACT_NO = :LNRCTPY.KEY.CONTRACT_NO "
            + "AND TR_TYP = :LNRCTPY.KEY.TR_TYP";
        LNTCTPY_RD.fst = true;
        LNTCTPY_RD.order = "TRAN_DATE DESC";
        IBS.READ(SCCGWA, LNRCTPY, this, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTPY_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_CONT_PROC() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG = "" + SCCCLDT.RC;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            if (LNCRICTL.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B051_PYIF_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPYIFM);
        LNCPYIFM.FUNC = '5';
        LNCPYIFM.REC_DATA.KEY.CONTRACT_NO = LNCSPREQ.CTA;
        LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCPYIFM.REC_DATA.CUR_REPY_DT = LNCSPREQ.TR_VAL_DTE;
        S000_CALL_LNZPYIFM();
    }
    public void S000_CALL_LNZPYIFM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PYIF-MAINT", LNCPYIFM);
        if (LNCPYIFM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTRELA() throws IOException,SQLException,Exception {
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        LNTRELA_RD.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE";
        IBS.READ(SCCGWA, LNRRELA, this, LNTRELA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_LNTRELA_FLG = 'F';
        } else {
            WS_READ_LNTRELA_FLG = 'N';
        }
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCIDAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CALC-INT-DAYS", BPCIDAY);
        if (BPCIDAY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSLNQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_FOR_SEQ_PROC() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE "
            + "AND SEQ_NO >= :WS_SEQ_NO";
        LNTSETL_BR.rp.order = "SEQ_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_SETL_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SETL_FOUND_FLG = 'Y';
        } else {
            WS_SETL_FOUND_FLG = 'N';
        }
    }
    public void T000_ENDBR_SETL_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO";
        LNTRCVD_RD.where = "SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND REPY_STS < > :LNRRCVD.REPY_STS";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RCVD_OVER_DUE_FLG = 'Y';
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            BPCQCCY.DATA.DEC_MTH = 2;
        }
    }
    public void T00_READUP_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
