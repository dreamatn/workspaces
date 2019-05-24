package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLRM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP552";
    String K_OUTPUT_FMT_2 = "BP557";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_F_RANDOM_VALUE = "SC-RANDOM-VALUE     ";
    String CPN_U_CASH_BV_CHK = "BP-U-CASH-BV-CHK    ";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_F_PRIV_RVK = "BP-F-PRIV-RVK       ";
    String CPN_ENCRYPT_PASSWORD = "SC-ENCRYPT-PASSWORD ";
    String CPN_P_QUERY_BK_PSW = "BP-P-QUERY-BKPSW    ";
    String K_HIS_REMARKS = "TLR INFOMATION MAINTAIN";
    String K_CPY_BPRTLT = "BPRTLT  ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String WS_ERR_MSG = " ";
    BPZSTLRM_WS_HANDLED_TLR_INFO WS_HANDLED_TLR_INFO = new BPZSTLRM_WS_HANDLED_TLR_INFO();
    String WS_PSWD_OUT = " ";
    short WS_PSWD_LEN = 0;
    short WS_I = 0;
    char WS_END = ' ';
    int WS_CNT = 0;
    int WS_CNT_AFTER = 0;
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCRAND SCCRAND = new SCCRAND();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCPASS SCCPASS = new SCCPASS();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTLT BPROTLT = new BPRTLT();
    SCCTERM SCCTERM = new SCCTERM();
    BPCOTLRM BPCOTLRM = new BPCOTLRM();
    BPCUTCVC BPCUTCVC = new BPCUTCVC();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFPRRV BPCFPRRV = new BPCFPRRV();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCPQBPW BPCPQBPW = new BPCPQBPW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRT BPCPQPRT = new BPCPQPRT();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPCSTLRM BPCSTLRM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLR;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCSTLRM BPCSTLRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLRM = BPCSTLRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        A010_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTLRM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLR = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        CEP.TRC(SCCGWA, BPRTLR.TLR_EN_NM);
    }
    public void A010_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSWD_LEN = 20;
        CEP.TRC(SCCGWA, WS_PSWD_LEN);
        CEP.TRC(SCCGWA, BPRTLR.TLR_BR);
        if (BPCSTLRM.CHK_FLG == 'Y') {
            if (BPCSTLRM.FUNC == 'A') {
                B050_02_TLR_INFO_CHECK_CH();
            }
            if (BPCSTLRM.FUNC == 'U') {
                B050_02_TLR_INFO_CHECK_CH();
                B050_01_UPDATE_CHECK();
            }
        } else {
            B000_MAIN_PROC();
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSWD_LEN = 20;
        CEP.TRC(SCCGWA, WS_PSWD_LEN);
        CEP.TRC(SCCGWA, BPRTLR.TLR_BR);
        if (BPCSTLRM.FUNC == 'A') {
            B010_CREATE_PROCESS();
        } else if (BPCSTLRM.FUNC == 'U') {
            B020_MODIFY_PROCESS();
        } else if (BPCSTLRM.FUNC == 'D') {
            B030_DELETE_PROCESS();
        } else if (BPCSTLRM.FUNC == 'Q') {
            B040_QUERY_PROCESS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCSTLRM.FUNC == 'A') {
            if (BPCSTLRM.KPSW_FLG == 'Y') {
                B090_OUTPUT_PROCESS();
            }
        }
        if (BPCSTLRM.FUNC == 'D') {
            B100_OUTPUT_TELLER_INFO();
        }
    }
    public void B050_OPERATION_AUTH_CHECK() throws IOException,SQLException,Exception {
        if (BPCPORUP.DATA_INFO.ATTR != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MANAGEMENT_ORGM;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_01_UPDATE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCSTLRM.TLR;
        S000_CALL_BPZRTLRM();
        IBS.CLONE(SCCGWA, BPRTLT, BPROTLT);
        CEP.TRC(SCCGWA, BPCSTLRM.EFF_DATE);
        CEP.TRC(SCCGWA, BPRTLT.EFF_DT);
        if (BPRTLT.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
            && BPCSTLRM.EFF_DATE != BPRTLT.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MODIFY_EFF_DA;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.TLR_STS == 'L') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_STS;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
        if (BPRTLT.SIGN_STS == 'O' 
            || BPRTLT.SIGN_STS == 'T') {
            if (BPRTLT.TLR_TYP == 'T') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USER_NOT_SIGN_OFF;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPRTLT.TLR_BR != BPCSTLRM.TLR_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MODIFY_FLD_BR;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && (BPCSTLRM.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
            || BPCSTLRM.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "CHECK CASH BOX");
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '0';
            S000_CALL_BPZUTCVC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && (BPCSTLRM.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0") 
            || BPCSTLRM.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "CHECK CASH VAULT");
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '1';
            S000_CALL_BPZUTCVC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW.substring(0, 1));
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPCSTLRM.TLR_STSW.substring(0, 1));
        CEP.TRC(SCCGWA, BPCSTLRM.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && (BPCSTLRM.TLR_STSW.substring(0, 1).equalsIgnoreCase("0") 
            || BPCSTLRM.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "CHECK BV BOX");
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '2';
            CEP.TRC(SCCGWA, BPCUTCVC);
            S000_CALL_BPZUTCVC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW.substring(0, 1));
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPCSTLRM.TLR_STSW.substring(0, 1));
        CEP.TRC(SCCGWA, BPCSTLRM.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && (BPCSTLRM.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            || BPCSTLRM.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "CHECK BV VAULT");
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '3';
            CEP.TRC(SCCGWA, BPCUTCVC);
            S000_CALL_BPZUTCVC();
        }
    }
    public void B050_01_01_CHECK_CASH_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUTCVC);
        BPCUTCVC.TLR = BPCSTLRM.TLR;
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        if (BPCSTLRM.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0")) {
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '0';
        }
        if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
        JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
        if (BPCSTLRM.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '1';
        }
        S000_CALL_BPZUTCVC();
    }
    public void B050_02_TLR_INFO_CHECK() throws IOException,SQLException,Exception {
        if (BPCSTLRM.FUNC == 'A') {
            if (BPCSTLRM.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLRM.EXP_DATE < BPCSTLRM.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCSTLRM.FUNC == 'A' 
            || BPCSTLRM.FUNC == 'U') {
            if (BPCSTLRM.EFF_DATE > BPCSTLRM.EXP_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if ((BPCSTLRM.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || BPCSTLRM.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2"))) {
                if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
                JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
                if (!BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ACCOUNT_TELLER;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
                JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
                if (BPCSTLRM.TLR_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ACC_STS_WORD_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if ((BPCSTLRM.CBR_SIGN == '1') 
                && (BPCSTLRM.TLR_LVL != '9')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CBR_SIGN_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPCSTLRM.TLR_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            CEP.TRC(SCCGWA, BPCPQORG.ATH_MAX);
            CEP.TRC(SCCGWA, BPCPQORG.TLR_MAX);
            if (BPCSTLRM.TX_LVL > BPCSTLRM.TLR_LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXNLVL_MST_LESS_TLVL;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.AUTH_LVL > BPCSTLRM.TLR_LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATHLVL_MST_LESS_TLVL;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.TX_LVL > BPCPQORG.TLR_MAX) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLRLVL_LAR_THAN_MAX;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.AUTH_LVL > BPCPQORG.ATH_MAX) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATHLVL_LAR_THAN_MAX;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.TLR_LVL > BPCPQORG.LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_LVL_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1") 
                && BPCSTLRM.TLR_LVL > BPCPQORG.LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_LVL_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
            if (BPCPQORG.ATTR != '2') {
                if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
                JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
                if (BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLEARORG_SET_ACC;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCSTLRM.SIGN_TRM_FLG == 'Y') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSTLRM.TRM_INFO);
                if (JIBS_tmp_str[0].trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_TERM;
                    S000_ERR_MSG_PROC();
                }
            }
            for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                if (BPCSTLRM.TRM_INFO.TRM_NUM[WS_I-1].trim().length() > 0) {
                    IBS.init(SCCGWA, SCCTERM);
                    SCCTERM.FUNC = 'I';
                    SCCTERM.NO = BPCSTLRM.TRM_INFO.TRM_NUM[WS_I-1];
                    S000_CALL_SCCRTERM();
                }
            }
            IBS.init(SCCGWA, BPCPQBPW);
            BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQBPW.DATA_INFO.PSW_TYP = BPCSTLRM.PSW_TYP;
        }
        CEP.TRC(SCCGWA, WS_HANDLED_TLR_INFO.WS_HANDLED_BR);
        if (BPRTLR.TLR_BR != WS_HANDLED_TLR_INFO.WS_HANDLED_BR) {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = BPRTLR.TLR_BR;
            BPCPRGST.BR2 = WS_HANDLED_TLR_INFO.WS_HANDLED_BR;
            S000_CALL_BPZPRGST();
            if (BPCPRGST.FLAG != 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_RELATED_BRANCH;
                S000_ERR_MSG_PROC();
            }
            if (BPCPRGST.LVL_RELATION == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LVL_RELATION_LOW;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B050_02_TLR_INFO_CHECK_CH() throws IOException,SQLException,Exception {
        WS_HANDLED_TLR_INFO.WS_HANDLED_BR = BPCSTLRM.TLR_BR;
        WS_HANDLED_TLR_INFO.WS_HANDLED_TLR_LVL = BPCSTLRM.TLR_LVL;
        if (BPCSTLRM.FUNC == 'A') {
            if (BPCSTLRM.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSTLRM.EXP_DATE < BPCSTLRM.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCSTLRM.FUNC == 'A' 
            || BPCSTLRM.FUNC == 'U') {
            if (BPCSTLRM.EFF_DATE > BPCSTLRM.EXP_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if ((BPCSTLRM.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPCSTLRM.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || BPCSTLRM.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2"))) {
                if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
                JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
                if (!BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ACCOUNT_TELLER;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
            JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
            if (BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
                JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
                if (BPCSTLRM.TLR_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ACC_STS_WORD_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if ((BPCSTLRM.CBR_SIGN == '1') 
                && (BPCSTLRM.TLR_LVL != '9')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CBR_SIGN_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPCSTLRM.NEW_BR;
            S000_CALL_BPZPQORG();
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPCSTLRM.TLR_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            CEP.TRC(SCCGWA, BPCPQORG.ATH_MAX);
            CEP.TRC(SCCGWA, BPCPQORG.TLR_MAX);
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            if (BPCPQORG.ATTR == '0') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_CANNOT_BR;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.TX_LVL > BPCSTLRM.TLR_LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXNLVL_MST_LESS_TLVL;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLRM.AUTH_LVL > BPCSTLRM.TLR_LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATHLVL_MST_LESS_TLVL;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR == '0' 
                || BPCPQORG.ATTR == '1') {
                if (BPCSTLRM.TLR_STSW == null) BPCSTLRM.TLR_STSW = "";
                JIBS_tmp_int = BPCSTLRM.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCSTLRM.TLR_STSW += " ";
                if (BPCSTLRM.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLEARORG_SET_ACC;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCSTLRM.SIGN_TRM_FLG == 'Y') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSTLRM.TRM_INFO);
                if (JIBS_tmp_str[0].trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_TERM;
                    S000_ERR_MSG_PROC();
                }
            }
            for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                if (BPCSTLRM.TRM_INFO.TRM_NUM[WS_I-1].trim().length() > 0) {
                    IBS.init(SCCGWA, SCCTERM);
                    SCCTERM.FUNC = 'I';
                    SCCTERM.NO = BPCSTLRM.TRM_INFO.TRM_NUM[WS_I-1];
                    S000_CALL_SCCRTERM();
                }
            }
            IBS.init(SCCGWA, BPCPQBPW);
            BPCPQBPW.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQBPW.DATA_INFO.PSW_TYP = BPCSTLRM.PSW_TYP;
        }
        CEP.TRC(SCCGWA, WS_HANDLED_TLR_INFO.WS_HANDLED_BR);
        CEP.TRC(SCCGWA, BPRTLR.TLR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != WS_HANDLED_TLR_INFO.WS_HANDLED_BR 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("CSP")) {
            if (BPCPORUP.DATA_INFO.LVL == '9') {
                IBS.init(SCCGWA, BPCPQBNK);
                BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                S000_CALL_BPZPQBNK();
                WS_BR = BPCPQBNK.DATA_INFO.HEAD_BR;
            }
            if (BPCPORUP.DATA_INFO.LVL == '2') {
                WS_BR = BPCPORUP.DATA_INFO.BR;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            }
            if (BPCPORUP.DATA_INFO.LVL == '4' 
                || BPCPORUP.DATA_INFO.LVL == '6') {
                WS_END = 'N';
                for (WS_CNT = 1; BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_BR != 0 
                    && WS_END != 'Y'; WS_CNT += 1) {
                    CEP.TRC(SCCGWA, WS_CNT);
                    if (WS_CNT == 1) {
                        if (BPCPORUP.DATA_INFO.LVL != BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_LVL) {
                            CEP.TRC(SCCGWA, "11");
                            WS_BR = BPCPORUP.DATA_INFO.BR;
                            WS_END = 'Y';
                        }
                    } else {
                        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_LVL);
                        WS_CNT_AFTER = WS_CNT - 1;
                        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT_AFTER-1].SUPR_LVL);
                        if (BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT_AFTER-1].SUPR_LVL != BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_LVL) {
                            WS_BR = BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT_AFTER-1].SUPR_BR;
                            WS_END = 'Y';
                        }
                    }
                }
            }
            IBS.init(SCCGWA, BPCPRGST);
        }
        BPCFTLRQ.INFO.TLR = BPCSTLRM.TLR;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.EFF_DT);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCSTLRM.FUNC == 'U') {
            if (SCCGWA.COMM_AREA.AC_DATE >= BPCFTLRQ.INFO.EFF_DT) {
                if (BPCFTLRQ.INFO.SIGN_STS != 'F' 
                    && BPCFTLRQ.INFO.TLR_TYP == 'T') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_TLR_ERR);
                }
            }
        }
    }
    public void S000_CALL_BPZPQPRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRT", BPCPQPRT);
        CEP.TRC(SCCGWA, BPCPQPRT.RC);
        if (BPCPQPRT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_03_DELETE_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.SIGN_STS == 'O' 
            || BPRTLT.SIGN_STS == 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USER_NOT_SIGN_OFF;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '0';
            S000_CALL_BPZUTCVC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '1';
            S000_CALL_BPZUTCVC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "CHECK BV BOX");
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '2';
            CEP.TRC(SCCGWA, BPCUTCVC);
            S000_CALL_BPZUTCVC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "CHECK BV VAULT");
            IBS.init(SCCGWA, BPCUTCVC);
            BPCUTCVC.TLR = BPCSTLRM.TLR;
            BPCUTCVC.DEL_FLG = 'Y';
            BPCUTCVC.CASH_BV_FLG = '3';
            CEP.TRC(SCCGWA, BPCUTCVC);
            S000_CALL_BPZUTCVC();
        }
    }
    public void B050_04_DELETE_PRRV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPRRV);
        BPCFPRRV.ASS_ID = BPCSTLRM.TLR;
        BPCFPRRV.ASSTYP = 'T';
        S000_CALL_BPZFPRRV();
    }
    public void B010_CREATE_PROCESS() throws IOException,SQLException,Exception {
        WS_HANDLED_TLR_INFO.WS_HANDLED_BR = BPCSTLRM.TLR_BR;
        WS_HANDLED_TLR_INFO.WS_HANDLED_TLR_LVL = BPCSTLRM.TLR_LVL;
        CEP.TRC(SCCGWA, BPRTLR.TLR_BR);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B050_02_TLR_INFO_CHECK_CH();
        } else {
            B050_02_TLR_INFO_CHECK();
        }
        if (BPCSTLRM.KPSW_FLG == 'Y') {
            B010_01_CREATE_PASSWORD();
        }
        IBS.init(SCCGWA, BPRTLT);
        R000_TRANS_DATA_PARAMETER();
        BPCRTLTM.INFO.FUNC = 'C';
        B010_02_HISTORY_RECORD();
        S000_CALL_BPZRTLRM();
    }
    public void B010_01_CREATE_PASSWORD() throws IOException,SQLException,Exception {
        if (BPCPQBPW.DATA_INFO.RAND_PSW_FLG == 'Y') {
            IBS.init(SCCGWA, SCCRAND);
            S000_CALL_SCZRAND();
            WS_PSWD_OUT = SCCRAND.VALUE;
        } else {
            WS_PSWD_OUT = BPCPQBPW.DATA_INFO.INIT_PSW;
        }
        IBS.init(SCCGWA, SCCPASS);
        SCCPASS.FUNC = '2';
        SCCPASS.LEN = WS_PSWD_LEN;
        SCCPASS.KEY = BPCSTLRM.TLR;
        SCCPASS.DATA = WS_PSWD_OUT;
        S000_CALL_BPZPASS();
    }
    public void B010_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "PD09";
        S000_CALL_BPZPNHIS();
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        WS_HANDLED_TLR_INFO.WS_HANDLED_BR = BPCSTLRM.TLR_BR;
        WS_HANDLED_TLR_INFO.WS_HANDLED_TLR_LVL = BPCSTLRM.TLR_LVL;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B050_02_TLR_INFO_CHECK_CH();
        } else {
            B050_02_TLR_INFO_CHECK();
        }
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCSTLRM.TLR;
        S000_CALL_BPZRTLRM();
        IBS.CLONE(SCCGWA, BPRTLT, BPROTLT);
        B050_01_UPDATE_CHECK();
        if (BPCSTLRM.EXP_DATE != BPRTLT.EXP_DT) {
            B050_04_DELETE_PRRV();
        }
        R000_TRANS_DATA_PARAMETER();
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLRM();
        B020_01_HISTORY_RECORD();
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLT;
        BPCPNHIS.INFO.TX_TYP_CD = "PD10";
        BPCPNHIS.INFO.OLD_DAT_PT = BPROTLT;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRTLT;
        S000_CALL_BPZPNHIS();
        CEP.TRC(SCCGWA, "HISTORY SUCCESS");
    }
    public void B030_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCSTLRM.TLR;
        S000_CALL_BPZRTLRM();
        B030_01_HISTORY_RECORD();
        WS_HANDLED_TLR_INFO.WS_HANDLED_BR = BPRTLT.TLR_BR;
        WS_HANDLED_TLR_INFO.WS_HANDLED_TLR_LVL = BPRTLT.TLR_LVL;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B050_02_TLR_INFO_CHECK_CH();
        } else {
            B050_02_TLR_INFO_CHECK();
        }
        B050_03_DELETE_CHECK();
        B050_04_DELETE_PRRV();
        BPCRTLTM.INFO.FUNC = 'D';
        S000_CALL_BPZRTLRM();
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.KEY.TLR = BPCSTLRM.TLR;
        BPCRTLTM.INFO.FUNC = 'Q';
        S000_CALL_BPZRTLRM();
    }
    public void B090_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLRM);
        BPCOTLRM.TLR = BPCSTLRM.TLR;
        BPCOTLRM.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOTLRM.NEW_PSW = WS_PSWD_OUT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLRM;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B100_OUTPUT_TELLER_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLRQ);
        R100_TRANS_DATA_OUTPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_2;
        SCCFMT.DATA_PTR = BPCOTLRQ;
        SCCFMT.DATA_LEN = 835;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R100_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOTLRQ.TLR = BPCSTLRM.TLR;
        BPCOTLRQ.STAF_NO = BPRTLT.STAF_NO;
        BPCOTLRQ.TLR_BR = BPRTLT.TLR_BR;
        BPCOTLRQ.EFF_DT = BPRTLT.EFF_DT;
        BPCOTLRQ.EXP_DT = BPRTLT.EFF_DT;
        BPCOTLRQ.TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCOTLRQ.TLR_EN_NM = BPRTLT.TLR_EN_NM;
        BPCOTLRQ.TLR_TYP = BPRTLT.TLR_TYP;
        BPCOTLRQ.TLR_LVL = BPRTLT.TLR_LVL;
        BPCOTLRQ.TX_LVL = BPRTLT.TX_LVL;
        BPCOTLRQ.ATH_LVL = BPRTLT.ATH_LVL;
        BPCOTLRQ.TRM_TYP = BPRTLT.TRM_TYP;
        BPCOTLRQ.TELE = BPRTLT.TELE;
        BPCOTLRQ.PST_ADDRESS = BPRTLT.PST_ADDRESS;
        BPCOTLRQ.TLR_STSW = BPRTLT.TLR_STSW;
        BPCOTLRQ.TLR_STS = BPRTLT.TLR_STS;
        BPCOTLRQ.SIGN_STS = BPRTLT.SIGN_STS;
        BPCOTLRQ.SIGN_DT = BPRTLT.SIGN_DT;
        BPCOTLRQ.SIGN_TIMES = BPRTLT.SIGN_TIMES;
        BPCOTLRQ.SIGN_TRM = BPRTLT.SIGN_TRM;
        BPCOTLRQ.UPD_DT = BPRTLT.UPD_DT;
        BPCOTLRQ.UPD_TLR = BPRTLT.UPD_TLR;
        BPCOTLRQ.LAST_JRN = BPRTLT.LAST_JRN;
        BPCOTLRQ.ACC_VCH_NO = BPRTLT.ACC_VCH_NO;
        BPCOTLRQ.ACC_SEN_CUS = BPRTLT.ACC_SEN_CUS;
        BPCOTLRQ.ACC_SEN_GL = BPRTLT.ACC_SEN_GL;
        BPCOTLRQ.SUPER_FLG = BPRTLT.SUPER_FLG;
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR);
        CEP.TRC(SCCGWA, BPCOTLRQ.STAF_NO);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_BR);
        CEP.TRC(SCCGWA, BPCOTLRQ.EFF_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.EXP_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_EN_NM);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_TYP);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_LVL);
        CEP.TRC(SCCGWA, BPCOTLRQ.ATH_LVL);
        CEP.TRC(SCCGWA, BPCOTLRQ.TRM_TYP);
        CEP.TRC(SCCGWA, BPCOTLRQ.TELE);
        CEP.TRC(SCCGWA, BPCOTLRQ.PST_ADDRESS);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STSW);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STS);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_STS);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_TIMES);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_TRM);
        CEP.TRC(SCCGWA, BPCOTLRQ.UPD_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.UPD_TLR);
        CEP.TRC(SCCGWA, BPCOTLRQ.LAST_JRN);
        CEP.TRC(SCCGWA, BPCOTLRQ.ACC_SEN_CUS);
        CEP.TRC(SCCGWA, BPCOTLRQ.ACC_SEN_GL);
        CEP.TRC(SCCGWA, BPCOTLRQ.SUPER_FLG);
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZRAND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_RANDOM_VALUE, SCCRAND);
        CEP.TRC(SCCGWA, SCCRAND.RC.RC_CODE);
        CEP.TRC(SCCGWA, SCCRAND.VALUE);
        if (SCCRAND.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCRAND.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUTCVC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_BV_CHK, BPCUTCVC);
        if (BPCUTCVC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCUTCVC.RC);
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ORG_STATION, BPCPRGST);
        CEP.TRC(SCCGWA, BPCPRGST.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFPRRV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_PRIV_RVK, BPCFPRRV);
        if (BPCFPRRV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFPRRV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCCENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ENCRYPT_PASSWORD, SCCENPSW);
        CEP.TRC(SCCGWA, SCCENPSW.RC.RC_CODE);
        if (SCCENPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCENPSW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPASS() throws IOException,SQLException,Exception {
        SCZPASS SCZPASS = new SCZPASS();
        SCZPASS.MP(SCCGWA, SCCPASS);
        CEP.TRC(SCCGWA, SCCPASS.DATA);
    }
    public void S000_CALL_BPZPQBPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BK_PSW, BPCPQBPW);
        if (BPCPQBPW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBPW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCCRTERM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-GET-TERM-INFO    ", SCCTERM);
        CEP.TRC(SCCGWA, SCCTERM.RC);
        if (SCCTERM.RC.RC_CODE != 0) {
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRTLT.KEY.TLR = BPCSTLRM.TLR;
        BPRTLT.STAF_NO = BPCSTLRM.STAFNO;
        BPRTLT.TLR_BR = BPCSTLRM.TLR_BR;
        BPRTLT.NEW_BR = BPCSTLRM.TLR_BR;
        BPRTLT.TLR_TKS = BPCSTLRM.TLR_TKS;
        BPRTLT.EFF_DT = BPCSTLRM.EFF_DATE;
        BPRTLT.EXP_DT = BPCSTLRM.EXP_DATE;
        BPRTLT.TLR_CN_NM = BPCSTLRM.TLR_CNAME;
        BPRTLT.TLR_EN_NM = BPCSTLRM.TLR_ENAME;
        BPRTLT.TLR_TYP = BPCSTLRM.TLR_TYPE;
        BPRTLT.TLR_LVL = BPCSTLRM.TLR_LVL;
        BPRTLT.TX_LVL = BPCSTLRM.TX_LVL;
        BPRTLT.ATH_LVL = BPCSTLRM.AUTH_LVL;
        if (BPCSTLRM.FUNC == 'A' 
            || BPCSTLRM.FUNC == 'U') {
            BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
            BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
        }
        BPRTLT.ATH_RGN = BPCSTLRM.AUTH_REG;
        BPRTLT.CRO_BR_SIGN = BPCSTLRM.CBR_SIGN;
        if (BPCSTLRM.FUNC == 'A') {
            BPRTLT.SIGN_STS = 'F';
            if (BPCSTLRM.TLR_TYPE == 'S') {
                BPRTLT.SIGN_STS = 'O';
            }
            BPRTLT.TLR_STS = 'N';
            if (BPCSTLRM.KPSW_FLG == 'Y') {
                BPRTLT.KB_PSW = SCCPASS.DATA;
                BPRTLT.KB_PSW_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        BPRTLT.TRM_TYP = BPCSTLRM.TRM_TYPE;
        BPRTLT.TLR_STSW = BPCSTLRM.TLR_STSW;
        BPRTLT.TX_MODE = BPCSTLRM.TX_MODE;
        BPRTLT.PRT_IP = BPCSTLRM.PRT_IP;
        BPRTLT.TELE = BPCSTLRM.TELE;
        BPRTLT.PST_ADDRESS = BPCSTLRM.PST_ADDRESS;
        BPRTLT.SIGN_TRM_FLG = BPCSTLRM.SIGN_TRM_FLG;
        BPRTLT.TRM_INFO = IBS.CLS2CPY(SCCGWA, BPCSTLRM.TRM_INFO);
        IBS.CPY2CLS(SCCGWA, BPRTLT.TRM_INFO, BPRTLT.REDEFINES103);
        BPRTLT.PSW_TYP = BPCSTLRM.PSW_TYP;
        BPRTLT.IDEN_DEV_ID = BPCSTLRM.IDEN_DEV_ID;
        BPRTLT.TM_OUT_LMT = BPCSTLRM.TM_OUT_LMT;
        BPRTLT.ACC_SEN_CUS = BPCSTLRM.ACC_SEN_CUS;
        BPRTLT.ACC_SEN_GL = BPCSTLRM.ACC_SEN_GL;
        BPRTLT.SUPER_FLG = BPCSTLRM.SUPER_FLG;
        BPRTLT.PRT_NAME1 = BPCSTLRM.DFT_PRTR_NM;
        BPRTLT.PRT_NAME2 = BPCSTLRM.PAY_PRTR_NM;
        BPRTLT.PRT_NAME3 = BPCSTLRM.CNF_PRTR_NM;
        BPRTLT.TM_OUT_LMT = BPCSTLRM.TM_OUT_LMT;
        BPRTLT.ACC_SEN_CUS = BPCSTLRM.ACC_SEN_CUS;
        BPRTLT.ACC_SEN_GL = BPCSTLRM.ACC_SEN_GL;
        BPRTLT.SUPER_FLG = BPCSTLRM.SUPER_FLG;
        BPRTLT.PRT_NAME1 = BPCSTLRM.DFT_PRTR_NM;
        BPRTLT.PRT_NAME2 = BPCSTLRM.PAY_PRTR_NM;
        BPRTLT.PRT_NAME3 = BPCSTLRM.CNF_PRTR_NM;
        BPRTLT.ID_TYP = BPCSTLRM.ID_TYP;
        BPRTLT.ID_NO = BPCSTLRM.ID_NO;
        BPRTLT.CI_NO = BPCSTLRM.CI_NO;
        BPRTLT.REG_TYP = BPCSTLRM.REG_TYP;
        BPRTLT.TLR_CRD_NO = BPCSTLRM.CRD_NO;
        BPRTLT.NEW_BR = BPCSTLRM.TLR_BR;
        BPRTLT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRTLT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
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
