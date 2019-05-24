package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.GD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSWRTF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTBALZ_RD;
    DBParm LNTWOFF_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_MMO_P = "12010008";
    String K_MMO_I = "12010009";
    String K_MMO_PI = "12010027";
    String K_MMO_CANCEL = "12018888";
    String WS_RC_APP = " ";
    short WS_RC_RTNCODE = 0;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    double WS_UNDUE_TOT_AMT = 0;
    double WS_DUE_TOT_AMT = 0;
    double WS_TOT_AMT = 0;
    long WS_DISB_REF = 0;
    int WS_SUF_NO = 0;
    int WS_LC_BK_SUF_NO = 0;
    double WS_BOR_PRIN = 0;
    double WS_BOR_BAL = 0;
    double WS_AMT = 0;
    double WS_NP_AMT = 0;
    double WS_OP_AMT = 0;
    double WS_NI_AMT = 0;
    double WS_OI_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_NP_AMT_TOT = 0;
    double WS_OP_AMT_TOT = 0;
    double WS_NI_AMT_TOT = 0;
    double WS_OI_AMT_TOT = 0;
    double WS_O_AMT_TOT = 0;
    double WS_L_AMT_TOT = 0;
    double WS_O_TOT_AMT = 0;
    double WS_P_AMT = 0;
    double WS_I_AMT = 0;
    char WS_LN_STS = ' ';
    String WS_CTL_STSW = " ";
    char WS_CAL_END_FLAG = ' ';
    char WS_WROF_TP = ' ';
    char WS_ATTR_FLG = ' ';
    double WS_WROF_TOT = 0;
    double WS_UNPAID_TOT_AMT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCIHDCK LNCIHDCK = new LNCIHDCK();
    LNCP01 LNCP01 = new LNCP01();
    LNCY05 LNCY05 = new LNCY05();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCSPREQ LNCSPREQ = new LNCSPREQ();
    LNCSRPO LNCSRPO = new LNCSRPO();
    LNCSERRP LNCSERRP = new LNCSERRP();
    LNCSBALQ LNCSBALQ = new LNCSBALQ();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCRWOFF LNCRWOFF = new LNCRWOFF();
    LNCUWOF LNCUWOF = new LNCUWOF();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNCSNACR LNCSNACR = new LNCSNACR();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSWRTF LNCSWRTF;
    public void MP(SCCGWA SCCGWA, LNCSWRTF LNCSWRTF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSWRTF = LNCSWRTF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSWRTF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        LNCSWRTF.RC.RC_APP = "LN";
        LNCSWRTF.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B030_MAIN_REV_PROC();
            if (pgmRtn) return;
        } else {
            B010_CHECK_PROC();
            if (pgmRtn) return;
            B020_MAIN_PROC();
            if (pgmRtn) return;
            C000_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCSWRTF.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_ATTR_FLG = LNCSWRTF.ATTR_FLG;
        if ((WS_ATTR_FLG != '1' 
            && WS_ATTR_FLG != '2')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ATTR_FLG_VAL_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_WROF_TOT = LNCSWRTF.TOT_AMTS.WRF_NP_AMT + LNCSWRTF.TOT_AMTS.WRF_NI_AMT + LNCSWRTF.TOT_AMTS.WRF_OP_AMT + LNCSWRTF.TOT_AMTS.WRF_OI_AMT + LNCSWRTF.TOT_AMTS.WRF_O_AMT + LNCSWRTF.TOT_AMTS.WRF_L_AMT;
        if (WS_WROF_TOT != LNCSWRTF.TOT_AMTS.WROF_TOT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_TOT_AMT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_ICTL_INF();
        if (pgmRtn) return;
        WS_CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONT_HAVE_CANNEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CANNOT_WOFF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
            && LNCSWRTF.WROF_TP == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_SYN_I_CANNOT_PWOF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_WROF_TP = LNCSWRTF.WROF_TP;
        if ((WS_WROF_TP != '1' 
            && WS_WROF_TP != '2')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_TP_NOTMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSWRTF.WROF_TP == '1') {
            if (WS_ATTR_FLG != '1') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ATTR_FLG_MUST_Y;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_LN_STS = '2';
        } else {
            WS_LN_STS = '1';
            if (LNCSWRTF.WROF_TP == '1') {
                IBS.init(SCCGWA, LNCSNACR);
                LNCSNACR.DATA.CTA_NO = LNCSWRTF.CTA_NO;
                LNCSNACR.DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
                LNCSNACR.DATA.ACRU_STS = 'Y';
                S000_CALL_LNZSNACR();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNCPPMQ);
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_MODULE_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_COMMIT_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_CONTRACT_PARM = 'Y';
        LNCPPMQ.KEY.KEY_METH = 'C';
        LNCPPMQ.KEY.KEY_VALUE.LEVEL = 'D';
        LNCPPMQ.KEY.KEY_VALUE.CONTRACT_NO = LNCSWRTF.CTA_NO;
        S000_CALL_LNZPPMQ();
        if (pgmRtn) return;
        if (LNCPPMQ.DATA_TXT.CAP_FROM == '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CAP_FROM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0) {
            IBS.init(SCCGWA, GDCIQLDR);
            GDCIQLDR.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
            S000_CALL_GDZIQLDR();
            if (pgmRtn) return;
            if (GDCIQLDR.TAMT > 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_BAL_GREATER_THEN_ZER;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCSWRTF.DOMI_BR != 0) {
            IBS.init(SCCGWA, LNCPCFTA);
            LNCPCFTA.BR_GP[1-1].BR = LNCSWRTF.DOMI_BR;
        } else {
            LNCPCFTA.BR_GP[1-1].BR = LNCCONTM.REC_DATA.DOMI_BR;
        }
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCSWRTF.DOMI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_FTA_MUST_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            && SCCGWA.COMM_AREA.AC_DATE < LNCCONTM.REC_DATA.MAT_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_YSX_CANNOT_WOFF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCCONTM.REC_DATA.START_DATE < LNCCONTM.REC_DATA.CRT_DATE 
            && LNCCONTM.REC_DATA.CRT_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_DATE_CANNOT_WOFF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDP")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CLDP_NOT_HEXIAO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CLGU_NOT_HEXIAO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL") 
            && !LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CLDL_ONLY_OVE_HEXIAO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_SUF_NO = 0;
        B040_GET_ALL_WOFF_AMT();
        if (pgmRtn) return;
        B210_CALL_LNZUWOF();
        if (pgmRtn) return;
        R000_GENERATE_WRITE_OFF_REC();
        if (pgmRtn) return;
        R000_GENERATE_HIS();
        if (pgmRtn) return;
    }
    public void B030_MAIN_REV_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        B210_CALL_LNZUWOF();
        if (pgmRtn) return;
        if (LNCUWOF.COMM_DATA.LOAN_STSW == null) LNCUWOF.COMM_DATA.LOAN_STSW = "";
        JIBS_tmp_int = LNCUWOF.COMM_DATA.LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCUWOF.COMM_DATA.LOAN_STSW += " ";
        if (LNCUWOF.COMM_DATA.LOAN_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0") 
            && LNCSWRTF.WROF_TP == '1') {
            IBS.init(SCCGWA, LNCSNACR);
            LNCSNACR.DATA.CTA_NO = LNCSWRTF.CTA_NO;
            LNCSNACR.DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
            LNCSNACR.DATA.ACRU_STS = 'N';
            S000_CALL_LNZSNACR();
            if (pgmRtn) return;
        }
        R000_GENERATE_HIS_RVS();
        if (pgmRtn) return;
    }
    public void B040_GET_ALL_WOFF_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        T000_READ_LNTBALZ();
        if (pgmRtn) return;
        WS_NP_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05;
        WS_OP_AMT = LNRBALZ.LOAN_BALL06;
        WS_OP_AMT_TOT = LNRBALZ.LOAN_BALL06;
        WS_NI_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20;
        WS_OI_AMT = LNRBALZ.LOAN_BALL22;
        WS_OI_AMT_TOT = LNRBALZ.LOAN_BALL22;
        WS_O_AMT = LNRBALZ.LOAN_BALL42;
        WS_O_AMT_TOT = LNRBALZ.LOAN_BALL42;
        WS_L_AMT = LNRBALZ.LOAN_BALL52;
        WS_L_AMT_TOT = LNRBALZ.LOAN_BALL52;
        IBS.init(SCCGWA, LNRWOFF);
        LNRWOFF.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        LNRWOFF.KEY.TYPE = '1';
        T000_READ_LNTWOFF();
        if (pgmRtn) return;
        WS_NP_AMT -= LNRWOFF.WOF_NP_AMT;
        WS_OP_AMT -= LNRWOFF.WOF_OP_AMT;
        WS_NI_AMT -= LNRWOFF.WOF_NI_AMT;
        WS_OI_AMT -= LNRWOFF.WOF_OI_AMT;
        WS_O_AMT -= LNRWOFF.WOF_O_AMT;
        WS_L_AMT -= LNRWOFF.WOF_L_AMT;
        WS_UNPAID_TOT_AMT = WS_NP_AMT + WS_OP_AMT + WS_NI_AMT + WS_OI_AMT + WS_O_AMT + WS_L_AMT;
        WS_O_TOT_AMT = WS_OP_AMT + WS_OI_AMT + WS_O_AMT + WS_L_AMT;
        WS_P_AMT = LNCSWRTF.TOT_AMTS.WRF_NP_AMT + LNCSWRTF.TOT_AMTS.WRF_OP_AMT;
        WS_I_AMT = LNCSWRTF.TOT_AMTS.WRF_NI_AMT + LNCSWRTF.TOT_AMTS.WRF_OI_AMT + LNCSWRTF.TOT_AMTS.WRF_O_AMT + LNCSWRTF.TOT_AMTS.WRF_L_AMT;
        if (WS_WROF_TP == '1' 
            && (LNCSWRTF.TOT_AMTS.WRF_NP_AMT > WS_NP_AMT 
            || LNCSWRTF.TOT_AMTS.WRF_OP_AMT > WS_OP_AMT 
            || LNCSWRTF.TOT_AMTS.WRF_NI_AMT > WS_NI_AMT 
            || LNCSWRTF.TOT_AMTS.WRF_OI_AMT > WS_OI_AMT 
            || LNCSWRTF.TOT_AMTS.WRF_O_AMT > WS_O_AMT 
            || LNCSWRTF.TOT_AMTS.WRF_L_AMT > WS_L_AMT 
            || LNCSWRTF.TOT_AMTS.WROF_TOT == WS_UNPAID_TOT_AMT)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WROF_M_LT_UNPAID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "全额核销");
        CEP.TRC(SCCGWA, WS_NP_AMT);
        CEP.TRC(SCCGWA, WS_OP_AMT);
        CEP.TRC(SCCGWA, WS_NI_AMT);
        CEP.TRC(SCCGWA, WS_OI_AMT);
        CEP.TRC(SCCGWA, WS_O_AMT);
        CEP.TRC(SCCGWA, WS_L_AMT);
        if (WS_WROF_TP == '2') {
            LNCSWRTF.TOT_AMTS.WRF_NP_AMT = WS_NP_AMT;
            LNCSWRTF.TOT_AMTS.WRF_OP_AMT = WS_OP_AMT;
            LNCSWRTF.TOT_AMTS.WRF_NI_AMT = WS_NI_AMT;
            LNCSWRTF.TOT_AMTS.WRF_OI_AMT = WS_OI_AMT;
            LNCSWRTF.TOT_AMTS.WRF_O_AMT = WS_O_AMT;
            LNCSWRTF.TOT_AMTS.WRF_L_AMT = WS_L_AMT;
            LNCSWRTF.TOT_AMTS.WROF_TOT = WS_NP_AMT + WS_OP_AMT + WS_NI_AMT + WS_OI_AMT + WS_O_AMT + WS_L_AMT;
        }
    }
    public void B210_CALL_LNZUWOF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUWOF);
        LNCUWOF.COMM_DATA.LN_AC = LNCSWRTF.CTA_NO;
        CEP.TRC(SCCGWA, "LNZSWRTF  B210-CALL-LNZUWOF");
        CEP.TRC(SCCGWA, LNCSWRTF.CTA_NO);
        CEP.TRC(SCCGWA, LNCUWOF.COMM_DATA.LN_AC);
        LNCUWOF.COMM_DATA.SUF_NO = "" + WS_SUF_NO;
        JIBS_tmp_int = LNCUWOF.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCUWOF.COMM_DATA.SUF_NO = "0" + LNCUWOF.COMM_DATA.SUF_NO;
        LNCUWOF.COMM_DATA.TR_VAL_DATE = LNCSWRTF.VAL_DTE;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_TOT_AMT = LNCSWRTF.TOT_AMTS.WROF_TOT;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NP_AMT = LNCSWRTF.TOT_AMTS.WRF_NP_AMT;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NI_AMT = LNCSWRTF.TOT_AMTS.WRF_NI_AMT;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OP_AMT = LNCSWRTF.TOT_AMTS.WRF_OP_AMT;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OI_AMT = LNCSWRTF.TOT_AMTS.WRF_OI_AMT;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OLC_AMT = LNCSWRTF.TOT_AMTS.WRF_O_AMT;
        LNCUWOF.COMM_DATA.TOT_AMTS.WRF_LLC_AMT = LNCSWRTF.TOT_AMTS.WRF_L_AMT;
        LNCUWOF.COMM_DATA.ATTR_FLG = LNCSWRTF.ATTR_FLG;
        LNCUWOF.COMM_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCUWOF.COMM_DATA.WROF_TP = LNCSWRTF.WROF_TP;
        S000_CALL_LNZUWOF();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUWOF.COMM_DATA.TOT_AMTS);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSWRTF.TOT_AMTS);
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSLNQ);
        LNCSLNQ.COMM_DATA.LN_AC = LNCSWRTF.CTA_NO;
        LNCSLNQ.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.SUF_NO = "0" + LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZSLNQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCP01);
        LNCP01.CTA_NO = LNCSWRTF.CTA_NO;
        LNCP01.BOOK_BR = LNCSWRTF.DOMI_BR;
        LNCP01.CI_NO = LNCSWRTF.CI_NO;
        LNCP01.CI_CNM = LNCSWRTF.CI_CNM;
        LNCP01.PROD_CD = LNCSWRTF.PROD_CD;
        LNCP01.DRAW_MTH = LNCCONTM.REC_DATA.DRAWDOWN_MTH;
        LNCP01.CCY = LNCSWRTF.CCY;
        LNCP01.LON_PRIN = LNCSWRTF.PRIN_AMT;
        LNCP01.LON_BAL = LNCSWRTF.BAL_AMT;
        LNCP01.VAL_DT = LNCCONTM.REC_DATA.START_DATE;
        LNCP01.DUE_DT = LNCSWRTF.DUE_DT;
        LNCP01.STS = LNCSWRTF.LN_STS;
        LNCP01.TOT_UDU = LNCSWRTF.TOT_AMTS.WRF_NP_AMT;
        LNCP01.PRIN_ARER = LNCSWRTF.TOT_AMTS.WRF_OP_AMT;
        LNCP01.INT_ACCR = LNCSWRTF.TOT_AMTS.WRF_NI_AMT;
        LNCP01.INT_ARER = LNCSWRTF.TOT_AMTS.WRF_OI_AMT;
        LNCP01.PRIN_LC = LNCSWRTF.TOT_AMTS.WRF_O_AMT;
        LNCP01.INT_LC = LNCSWRTF.TOT_AMTS.WRF_L_AMT;
        LNCP01.WROF_TP = LNCSWRTF.WROF_TP;
        LNCP01.ATTR_FLG = LNCSWRTF.ATTR_FLG;
        LNCP01.VAL_DTE = LNCSWRTF.VAL_DTE;
        LNCP01.P_TOT = WS_P_AMT;
        LNCP01.I_TOT = WS_I_AMT;
        LNCP01.WROF_TOT = LNCSWRTF.TOT_AMTS.WROF_TOT;
        LNCP01.RMK1 = LNCSWRTF.RMK1;
        LNCP01.RMK2 = LNCSWRTF.RMK2;
        LNCP01.RMK3 = LNCSWRTF.RMK3;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN607";
        SCCFMT.DATA_PTR = LNCP01;
        SCCFMT.DATA_LEN = 886;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void R000_GENERATE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.AC = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSWRTF.VAL_DTE;
        BPCPFHIS.DATA.TX_CCY = LNCSWRTF.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSWRTF.TOT_AMTS.WROF_TOT;
        BPCPFHIS.DATA.ACO_AC = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.OTH_AC = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.CI_NO = LNCSWRTF.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCSWRTF.PROD_CD;
        if (WS_P_AMT == 0) {
            BPCPFHIS.DATA.TX_MMO = K_MMO_I;
        } else {
            if (WS_I_AMT == 0) {
                BPCPFHIS.DATA.TX_MMO = K_MMO_P;
            } else {
                BPCPFHIS.DATA.TX_MMO = K_MMO_PI;
            }
        }
        IBS.init(SCCGWA, LNCY05);
        LNCY05.CTA_NO = LNCSWRTF.CTA_NO;
        LNCY05.CI_NO = LNCSWRTF.CI_NO;
        LNCY05.CI_CNM = LNCSWRTF.CI_CNM;
        LNCY05.PROD_CD = LNCSWRTF.PROD_CD;
        LNCY05.CCY = LNCSWRTF.CCY;
        LNCY05.ATTR_FLG = LNCSWRTF.ATTR_FLG;
        LNCY05.LON_PRIN = LNCSWRTF.PRIN_AMT;
        LNCY05.LON_BAL = LNCSWRTF.BAL_AMT;
        LNCY05.VAL_DTE = LNCSWRTF.VAL_DTE;
        LNCY05.INT_ACCR = LNCSWRTF.TOT_AMTS.WRF_NI_AMT;
        LNCY05.PIA_AMT = WS_P_AMT;
        LNCY05.IIA_AMT = WS_I_AMT;
        LNCY05.PRIN_LC = LNCSWRTF.TOT_AMTS.WRF_O_AMT;
        LNCY05.INT_LC = LNCSWRTF.TOT_AMTS.WRF_L_AMT;
        LNCY05.WROF_TOT = LNCSWRTF.TOT_AMTS.WROF_TOT;
        LNCY05.RMK1 = LNCSWRTF.RMK1;
        LNCY05.RMK2 = LNCSWRTF.RMK2;
        LNCY05.RMK3 = LNCSWRTF.RMK3;
        BPCPFHIS.DATA.FMT_CODE = "LNY05";
        BPCPFHIS.DATA.FMT_LEN = 816;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCY05);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void R000_GENERATE_HIS_RVS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        IBS.init(SCCGWA, LNCTRANM);
        IBS.init(SCCGWA, LNCSLNQ);
        IBS.init(SCCGWA, LNCY05);
        LNCTRANM.FUNC = '3';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCSWRTF.TOT_AMTS.WROF_TOT = LNCTRANM.REC_DATA.P_AMT + LNCTRANM.REC_DATA.I_AMT + LNCTRANM.REC_DATA.O_AMT + LNCTRANM.REC_DATA.L_AMT;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.AC = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSWRTF.VAL_DTE;
        BPCPFHIS.DATA.ACO_AC = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.OTH_AC = LNCSWRTF.CTA_NO;
        BPCPFHIS.DATA.TX_CCY = LNCSWRTF.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSWRTF.TOT_AMTS.WROF_TOT;
        BPCPFHIS.DATA.CI_NO = LNCSWRTF.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCSWRTF.PROD_CD;
        BPCPFHIS.DATA.TX_MMO = K_MMO_CANCEL;
        LNCY05.CTA_NO = LNCSWRTF.CTA_NO;
        LNCY05.BOOK_BR = LNCSWRTF.DOMI_BR;
        LNCY05.CI_NO = LNCSWRTF.CI_NO;
        LNCY05.CI_CNM = LNCSWRTF.CI_CNM;
        LNCY05.PROD_CD = LNCSWRTF.PROD_CD;
        LNCY05.CCY = LNCSWRTF.CCY;
        LNCY05.INT_ACCR = WS_NI_AMT;
        LNCY05.PIA_AMT = LNCTRANM.REC_DATA.P_AMT;
        LNCY05.IIA_AMT = LNCTRANM.REC_DATA.I_AMT;
        LNCY05.PRIN_LC = LNCTRANM.REC_DATA.O_AMT;
        LNCY05.INT_LC = LNCTRANM.REC_DATA.L_AMT;
        LNCY05.WROF_TOT = LNCSWRTF.TOT_AMTS.WROF_TOT;
        LNCY05.RMK1 = LNCSWRTF.RMK1;
        LNCY05.RMK2 = LNCSWRTF.RMK2;
        LNCY05.RMK3 = LNCSWRTF.RMK3;
        BPCPFHIS.DATA.FMT_CODE = "LNY05";
        BPCPFHIS.DATA.FMT_LEN = 816;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCY05);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void R000_GENERATE_WRITE_OFF_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRWOFF);
        IBS.init(SCCGWA, LNRWOFF);
        LNCRWOFF.FUNC = 'R';
        LNRWOFF.KEY.TYPE = '1';
        LNRWOFF.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        LNCRWOFF.REC_PTR = LNRWOFF;
        LNCRWOFF.REC_LEN = 275;
        S000_CALL_LNZRWOFF();
        if (pgmRtn) return;
        if (LNCRWOFF.RC.RC_CODE != 0) {
            if (LNCRWOFF.RETURN_INFO == 'N') {
                LNCRWOFF.FUNC = 'A';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRWOFF.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            LNCRWOFF.FUNC = 'U';
        }
        LNRWOFF.KEY.TYPE = LNCSWRTF.WROF_TP;
        LNRWOFF.KEY.CONTRACT_NO = LNCSWRTF.CTA_NO;
        LNRWOFF.ATTR_FLG = LNCSWRTF.ATTR_FLG;
        LNRWOFF.BK = LNCSWRTF.DOMI_BR;
        if (LNCSWRTF.WROF_TP == '1') {
            LNRWOFF.WOF_NP_AMT += LNCSWRTF.TOT_AMTS.WRF_NP_AMT;
            LNRWOFF.WOF_OP_AMT += LNCSWRTF.TOT_AMTS.WRF_OP_AMT;
            LNRWOFF.WOF_NI_AMT += LNCSWRTF.TOT_AMTS.WRF_NI_AMT;
            LNRWOFF.WOF_OI_AMT += LNCSWRTF.TOT_AMTS.WRF_OI_AMT;
            LNRWOFF.WOF_O_AMT += LNCSWRTF.TOT_AMTS.WRF_O_AMT;
            LNRWOFF.WOF_L_AMT += LNCSWRTF.TOT_AMTS.WRF_L_AMT;
        } else {
            LNCRWOFF.FUNC = 'A';
            LNRWOFF.KEY.TYPE = '2';
            LNRWOFF.WOF_NP_AMT = WS_NP_AMT_TOT;
            LNRWOFF.WOF_OP_AMT = WS_OP_AMT_TOT;
            LNRWOFF.WOF_NI_AMT = WS_NI_AMT_TOT;
            LNRWOFF.WOF_OI_AMT = WS_OI_AMT_TOT;
            LNRWOFF.WOF_O_AMT = WS_O_AMT_TOT;
            LNRWOFF.WOF_L_AMT = WS_L_AMT_TOT;
        }
        LNRWOFF.ATTR_FLG = LNCSWRTF.ATTR_FLG;
        LNRWOFF.LN_STS = WS_LN_STS;
        LNRWOFF.CAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRWOFF.CAN_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRWOFF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRWOFF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRWOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRWOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRWOFF.REC_PTR = LNRWOFF;
        LNCRWOFF.REC_LEN = 275;
        S000_CALL_LNZRWOFF();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIHDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-HOLDAY-CHK", LNCIHDCK);
        if (LNCIHDCK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIHDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRWOFF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTWOFF", LNCRWOFF);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPPMQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSNACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-LN-NON-ACRU", LNCSNACR);
        if (LNCSNACR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSNACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUWOF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-WRITEOFF", LNCUWOF);
        if (LNCUWOF.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCUWOF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_BALL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTWOFF() throws IOException,SQLException,Exception {
        LNTWOFF_RD = new DBParm();
        LNTWOFF_RD.TableName = "LNTWOFF";
        IBS.READ(SCCGWA, LNRWOFF, LNTWOFF_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSWRTF.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSWRTF=");
            CEP.TRC(SCCGWA, LNCSWRTF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
