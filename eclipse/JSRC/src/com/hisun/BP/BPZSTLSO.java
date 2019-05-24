package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.AI.*;
import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLSO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    DecimalFormat df;
    String JIBS_NumStr;
    String JIBS_f0;
    public String K_PGM_NAME = "BPZSTLSO";
    public String PGM_SCSSCLDT = "SCSSCLDT";
    public String K_CMP_TXN_HIS = "BP-REC-NHIS";
    public String CPN_R_TLR_SIGN_HIS = "BP-TLR-SIGN-HIS-BRW";
    public String CPN_P_QUERY_BK_PSW = "BP-P-QUERY-BKPSW    ";
    public String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    public String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    public String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    public String CPN_R_TMUG = "BP-R-MGM-TMUG       ";
    public String CPN_R_TMUP = "BP-R-MGM-TMUP       ";
    public String CPN_F_PRIV_ERA = "BP-F-PRIV-ERA       ";
    public String CPN_R_GET_TERM_INFO = "SC-GET-TERM-INFO    ";
    public String CPN_F_TLR_ACCU_MGM = "BP-F-TLR-ACCU-MGM   ";
    public String CPN_ENCRYPT_PASSWORD = "SC-ENCRYPT-PASSWORD ";
    public String CPN_P_DOWN_ATH = "BP-P-DOWN-ATH       ";
    public String CPN_QUERY_TLT_HOLIDAY = "BP-P-QUERY-TLR-HOL  ";
    public String CPN_CHECK_TLR_PSW = "BP-F-CHECK-PASSWORD ";
    public String K_HIS_REMARKS = "TELLER SIGN ON SUCCESS  ";
    public int WS_PROMPT_DAY = 0;
    public double WS_TEMP_PVAL1 = 0;
    public double WS_TEMP_PVAL2 = 0;
    public String WS_NEW_PASSWORD = " ";
    public String WS_ERR_MSG = " ";
    public String WS_INFO = " ";
    public char WS_MSG_TYPE = ' ';
    public int WS_J = 0;
    public int WS_K = 0;
    public String WS_DAYS = " ";
    public short WS_PSW_LEN = 0;
    public int WS_DATE = 0;
    public int WS_TIME = 0;
    public BPZSTLSO_WS_PEND_INFO WS_PEND_INFO = new BPZSTLSO_WS_PEND_INFO();
    public int WS_PEND_CNT = 0;
    public BPZSTLSO_WS_OUT_FMT WS_OUT_FMT = new BPZSTLSO_WS_OUT_FMT();
    public BPZSTLSO_WS_COND_FLG WS_COND_FLG = new BPZSTLSO_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCRTMUG BPCRTMUG = new BPCRTMUG();
    BPCRTMUP BPCRTMUP = new BPCRTMUP();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTMUP BPRTMUP = new BPRTMUP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCFPREA BPCFPREA = new BPCFPREA();
    SCCTERM SCCTERM = new SCCTERM();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCFTPSW BPCFTPSW = new BPCFTPSW();
    BPCPDATH BPCPDATH = new BPCPDATH();
    BPCPQBPW BPCPQBPW = new BPCPQBPW();
    BPCPQTLH BPCPQTLH = new BPCPQTLH();
    BPCFCHPW BPCFCHPW = new BPCFCHPW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRHIST BPCRHIST = new BPCRHIST();
    BPCRPENM BPCRPENM = new BPCRPENM();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCRTDTL BPCRTDTL = new BPCRTDTL();
    BPRPEND BPRPEND = new BPRPEND();
    BPCPEND BPCPEND = new BPCPEND();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRMSG BPRMSG = new BPRMSG();
    BPRTDTL BPRTDTL = new BPRTDTL();
    BPCUTCVC BPCUTCVC = new BPCUTCVC();
    SCCGWA SCCGWA;
    BPCSTLSO BPCSTLSO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    AICPZMIB AICPZMIB;
    public void MP(SCCGWA SCCGWA, BPCSTLSO BPCSTLSO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLSO = BPCSTLSO;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTLSO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.init(SCCGWA, BPCRTMUG);
        IBS.init(SCCGWA, SCCTERM);
        IBS.init(SCCGWA, BPCPQBPW);
        IBS.init(SCCGWA, BPCFCHPW);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BANK-INFO");
        CEP.TRC(SCCGWA, BPCRBANK);
        CEP.TRC(SCCGWA, BPCRBANK.FX_RATE);
        CEP.TRC(SCCGWA, BPCRBANK.TOT_CCY);
        CEP.TRC(SCCGWA, BPCRBANK.TAI_FEN);
        CEP.TRC(SCCGWA, BPCRBANK.OPN_TM);
        CEP.TRC(SCCGWA, BPCRBANK.CLS_TM);
        CEP.TRC(SCCGWA, BPCRBANK.HOPN_TM);
        CEP.TRC(SCCGWA, BPCRBANK.HCLS_TM);
        CEP.TRC(SCCGWA, BPCRBANK.ERP_BRAN);
        CEP.TRC(SCCGWA, BPCRBANK.EX_RA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        BPCPQBPW.DATA_INFO.BNK = "999";
        B010_COMMON_CHECK();
        if (BPCSTLSO.OPT == '0'
            || BPCSTLSO.OPT_QUDAO == 'Q') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B020_SIGNON_SPEC_CHECK_CN();
            } else {
                B020_SIGNON_SPEC_CHECK();
            }
        } else if (BPCSTLSO.OPT == '1') {
            B030_AUTH_SIGNON_SPEC_CHECK();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        WS_COND_FLG.WS_RESET_LVL_FLAG = 'N';
        if (WS_COND_FLG.WS_FIRST_SIGN_ON_FLG == 'Y') {
            B050_CHECK_TLR_ATH_LVL();
        }
        if (BPCSTLSO.OPT == '0') {
            B060_PRIV_ERA_PROC();
        }
        B080_01_UPDATE_PROCESS();
        S000_HISTORY_PROCESS();
        if (BPCSTLSO.OPT != '1') {
            B090_OUTPUT_PROCESS();
        }
    }
    public void B010_COMMON_CHECK() throws IOException,SQLException,Exception {
        if (BPCSTLSO.OPT == '0'
            || BPCSTLSO.OPT == '1'
            || BPCSTLSO.OPT_QUDAO == 'Q') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B010_01_TLR_QUERY_PROC();
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (BPCPQORG.ATTR == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATTR_CAN_NOT_OPEN;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_NOT_EFF;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_ALREADY_EXP;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.TLR_STS != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQTLH);
        BPCPQTLH.INFO.TLR = BPCSTLSO.TLR;
        BPCPQTLH.INFO.TYPE = 'H';
        S000_CALL_BPZPQTLH();
        if (BPCPQTLH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQTLH.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "CCC");
            CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
            if (BPRTLT.TLR_STS == 'H') {
                if (BPCPQTLH.INFO.SIGN_FLG != 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_IN_HOLIDAY;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if ((BPRTLT.SIGN_STS == 'F' 
            && BPRTLT.SIGN_DT < SCCGWA.COMM_AREA.AC_DATE) 
            || (BPRTLT.SIGN_STS == 'C' 
            && BPRTLT.SIGN_DT < SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            WS_COND_FLG.WS_FIRST_SIGN_ON_FLG = 'Y';
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                || (BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")))) {
                WS_COND_FLG.WS_ACC_FLG = 'Y';
            }
        }
        if (BPRTLT.SIGN_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FIRST_SIGN_ON;
            S000_ERR_MSG_PROC();
        }
        B010_03_GET_ORGM_INFO();
        CEP.TRC(SCCGWA, BPCPQORG.INT_BR_FLG);
        if (WS_COND_FLG.WS_FIRST_SIGN_ON_FLG == 'Y' 
            && BPCPQORG.INT_BR_FLG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FIRST_SIGN_ON_NOVIR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_03_GET_ORGM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        if (BPCSTLSO.OPT_QUDAO != 'Q') {
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            BPCPQORG.BR = BPCSTLSO.NEW_BR;
        }
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.ORG_STS);
    }
    public void B010_02_ADD_PSW_RETRY_CNT() throws IOException,SQLException,Exception {
        BPCFTPSW.TLR = BPCSTLSO.TLR;
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "BPZFTPSW";
        WS_STAR_COMM.STAR_DATA = BPCFTPSW;
        IBS.START(SCCGWA, WS_STAR_COMM, false);
    }
    public void B010_04_GET_TERM_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GET-TERM-INFO");
        IBS.init(SCCGWA, SCCTERM);
        SCCTERM.FUNC = 'I';
        SCCTERM.NO = SCCGWA.COMM_AREA.TERM_ID;
        S000_CALL_SCCRTERM();
        CEP.TRC(SCCGWA, SCCTERM.USE_IN);
        CEP.TRC(SCCGWA, SCCTERM.TL_ID);
    }
    public void B020_SIGNON_SPEC_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        if (BPRTLT.SIGN_STS == 'O' 
            && (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ALREADY_SINGON;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.SIGN_STS == 'C') {
            if (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COMPULSORY_SIGN_OFF;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_C_SIGN_OFF_BEFORE;
                S000_ERR_MSG_PROC();
            }
        }
        if (!(BPRTLT.TLR_TYP == 'T' 
            || BPRTLT.TLR_TYP == 'C')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_COUNTER_TELLER;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            if (BPCPORUP.ATTR == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MANAGEMENT_ORGM;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPRTLT.TLR_LVL);
        CEP.TRC(SCCGWA, BPCPORUP.LVL);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            if (BPRTLT.TLR_LVL > BPCPORUP.LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_LVL_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPRTLT.TX_MODE);
        CEP.TRC(SCCGWA, BPCPQORG.ORG_STS);
        if (BPRTLT.TLR_TYP != 'C') {
            if (BPCPQORG.ORG_STS != 'O') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORGM_NOT_OPEN;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPRTLT.TLR_STS == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_OUT_OF_WORK;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_SIGNON_SPEC_CHECK_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        if (BPRTLT.SIGN_STS == 'O' 
            && (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ALREADY_SINGON;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.SIGN_STS == 'C') {
            if (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COMPULSORY_SIGN_OFF;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_C_SIGN_OFF_BEFORE;
                S000_ERR_MSG_PROC();
            }
        }
        if (!(BPRTLT.TLR_TYP == 'T' 
            || BPRTLT.TLR_TYP == 'C')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_COUNTER_TELLER;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPRTLT.TX_MODE);
        CEP.TRC(SCCGWA, BPCPQORG.ORG_STS);
        if (BPRTLT.TLR_TYP != 'C') {
            if (BPCPQORG.ORG_STS != 'O') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORGM_NOT_OPEN;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPRTLT.TLR_STS == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_OUT_OF_WORK;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLT.TLR_STS == 'S') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_NOT_IN_USE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_AUTH_SIGNON_SPEC_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.SIGN_STS == 'O' 
            && (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ALREADY_SINGON;
            S000_ERR_MSG_PROC();
        }
        if ((BPRTLT.SIGN_STS == 'C') 
            && (BPRTLT.SIGN_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COMPULSORY_SIGN_OFF;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRTLT.ATH_RGN);
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPRTLT.ATH_RGN == '0') {
            if (BPRTLT.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_THE_SAME_BRANCH;
                S000_ERR_MSG_PROC();
            }
        } else if (BPRTLT.ATH_RGN == '1'
            || BPRTLT.ATH_RGN == '2') {
            if (BPRTLT.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                B030_01_ORGM_COMPARE();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_01_ORGM_COMPARE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = BPRTLT.NEW_BR;
        BPCPRGST.BR2 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
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
    public void B030_02_BPTTDTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTDTL);
        CEP.TRC(SCCGWA, BPCSTLSO.TLR);
        BPRTDTL.KEY.TLR = BPCSTLSO.TLR;
        BPCRTDTL.INFO.FUNC = 'M';
        S000_CALL_BPZRTDTL();
        BPCRTDTL.INFO.FUNC = 'N';
        S000_CALL_BPZRTDTL();
        if (BPCRTDTL.RETURN_INFO == 'F') {
            if (BPRTDTL.END_DT < SCCGWA.COMM_AREA.AC_DATE 
                && BPRTLT.SIGN_DT <= BPRTDTL.END_DT) {
                if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, BPCUTCVC);
                    BPCUTCVC.TLR = BPCSTLSO.TLR;
                    BPCUTCVC.CASH_BV_FLG = '2';
                    BPCUTCVC.DEL_FLG = 'Y';
                    S000_CALL_BPZUTCVC();
                }
                if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                if (BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, BPCUTCVC);
                    BPCUTCVC.TLR = BPCSTLSO.TLR;
                    BPCUTCVC.CASH_BV_FLG = '3';
                    BPCUTCVC.DEL_FLG = 'Y';
                    S000_CALL_BPZUTCVC();
                }
                if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, BPCUTCVC);
                    BPCUTCVC.TLR = BPCSTLSO.TLR;
                    BPCUTCVC.CASH_BV_FLG = '0';
                    BPCUTCVC.DEL_FLG = 'Y';
                    S000_CALL_BPZUTCVC();
                }
                if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
                JIBS_tmp_int = BPRTLT.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
                if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, BPCUTCVC);
                    BPCUTCVC.TLR = BPCSTLSO.TLR;
                    BPCUTCVC.CASH_BV_FLG = '1';
                    BPCUTCVC.DEL_FLG = 'Y';
                    S000_CALL_BPZUTCVC();
                }
            }
        }
        BPCRTDTL.INFO.FUNC = 'E';
        S000_CALL_BPZRTDTL();
    }
    public void B040_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        if (BPRTLT.SIGN_DT != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPRTLT.KB_PSW_DT;
            SCCCLDT.DAYS = BPCPQBPW.DATA_INFO.TLR_PDAY;
            CEP.TRC(SCCGWA, "F1-SCSSCLDT");
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            CEP.TRC(SCCGWA, BPCPQBPW.DATA_INFO.TLR_PDAY);
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_TEMP_PVAL1 = (double)SCCCLDT.DATE2;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = (int) WS_TEMP_PVAL1;
            WS_PROMPT_DAY = BPCPQBPW.DATA_INFO.TLR_RDAY * ( -1 );
            SCCCLDT.DAYS = WS_PROMPT_DAY;
            CEP.TRC(SCCGWA, "F2-SCSSCLDT");
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            S000_CALL_SCSSCLDT();
            WS_TEMP_PVAL2 = (double)SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, BPCPQBPW.DATA_INFO.TLR_PDAY);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            if (WS_TEMP_PVAL1 > SCCGWA.COMM_AREA.AC_DATE) {
                if (WS_TEMP_PVAL2 <= SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = (int) WS_TEMP_PVAL1;
                    SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_SCSSCLDT();
                    CEP.TRC(SCCGWA, "F3-PS-EXPIRE DAYS");
                    CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PASSWORD_WILL_EXPIRE;
                    df = new DecimalFormat("#####0");
                    WS_DAYS = df.format(SCCCLDT.DAYS);
                    if (WS_INFO == null) WS_INFO = "";
                    JIBS_tmp_int = WS_INFO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
                    WS_INFO = "T" + WS_INFO.substring(1);
                    if (WS_INFO == null) WS_INFO = "";
                    JIBS_tmp_int = WS_INFO.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
                    if (WS_DAYS == null) WS_DAYS = "";
                    JIBS_tmp_int = WS_DAYS.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) WS_DAYS += " ";
                    WS_INFO = WS_INFO.substring(0, 2 - 1) + WS_DAYS + WS_INFO.substring(2 + 6 - 1);
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (WS_TEMP_PVAL1 == SCCGWA.COMM_AREA.AC_DATE) {
                    if (BPCSTLSO.NEW_PSW.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEW_PSW_MUST_INPUT;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    CEP.TRC(SCCGWA, "PASS WORD EXPIRE");
                    if (BPCSTLSO.NEW_PSW.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PASSWORD_EXPIRE;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if ((BPRTLT.SIGN_DT == 0) 
            || (BPRTLT.TLR_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("0"))) {
            if (BPCSTLSO.NEW_PSW.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEW_PSW_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCSTLSO.NEW_PSW.trim().length() > 0) 
            && (BPCSTLSO.CFM_PSW.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PASSWORD_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if ((BPCSTLSO.NEW_PSW.trim().length() == 0) 
            && (BPCSTLSO.CFM_PSW.trim().length() > 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PASSWORD_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if ((BPCSTLSO.NEW_PSW.trim().length() > 0) 
            && (BPCSTLSO.CFM_PSW.trim().length() > 0)) {
            if (!BPCSTLSO.NEW_PSW.equalsIgnoreCase(BPCSTLSO.CFM_PSW)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
            if (BPCSTLSO.NEW_PSW.equalsIgnoreCase(BPCSTLSO.OLD_PSW)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_OLD_PSWD;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCFCHPW);
            BPCFCHPW.TLR = BPCSTLSO.TLR;
            BPCFCHPW.PSW = BPCSTLSO.NEW_PSW;
            S000_CALL_BPZFCHPW();
        }
    }
    public void B050_CHECK_TLR_ATH_LVL() throws IOException,SQLException,Exception {
        if ((BPRTLT.TX_LVL != BPRTLT.TMP_TX_LVL) 
            || (BPRTLT.ATH_LVL != BPRTLT.TMP_ATH_LVL)) {
            if ((BPRTLT.TX_LVL < BPRTLT.TMP_TX_LVL) 
                || (BPRTLT.ATH_LVL < BPRTLT.TMP_ATH_LVL)) {
                WS_COND_FLG.WS_MUG_FLAG = 'I';
                B050_01_CHECK_LVL();
            }
            if ((BPRTLT.TX_LVL > BPRTLT.TMP_TX_LVL) 
                || (BPRTLT.ATH_LVL > BPRTLT.TMP_ATH_LVL)) {
                WS_COND_FLG.WS_MUG_FLAG = 'O';
                B050_01_CHECK_LVL();
            }
        }
    }
    public void B050_01_CHECK_LVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMUP);
        if (WS_COND_FLG.WS_MUG_FLAG == 'I') {
            BPCRTMUG.INFO.FUNC = '2';
            BPRTMUP.KEY.TLR_MOV_IN = BPCSTLSO.TLR;
        } else {
            BPCRTMUG.INFO.FUNC = '1';
            BPRTMUP.KEY.TLR_MOV_OUT = BPCSTLSO.TLR;
        }
        BPRTMUP.STS = 'Y';
        BPRTMUP.MOV_EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCRTMUG.INFO.LEN = 63;
        BPCRTMUG.INFO.POINTER = BPRTMUP;
        CEP.TRC(SCCGWA, BPRTMUP.KEY.TLR_MOV_IN);
        CEP.TRC(SCCGWA, BPRTMUP.KEY.TLR_MOV_OUT);
        CEP.TRC(SCCGWA, BPRTMUP.STS);
        CEP.TRC(SCCGWA, BPRTMUP.MOV_EXP_DT);
        S000_CALL_BPZRTMUG();
        if (BPCRTMUG.INFO.REC_COUNT == 0) {
            WS_COND_FLG.WS_RESET_LVL_FLAG = 'Y';
        }
    }
    public void B060_PRIV_ERA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPREA);
        BPCFPREA.ASSTYP = 'T';
        BPCFPREA.ASS_ID = BPCSTLSO.TLR;
        S000_CALL_BPZFPREA();
    }
    public void B080_01_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.WS_FIRST_SIGN_ON_FLG == 'Y') {
            B080_01_02_UPDATE_ACCU_INFO();
            B080_01_03_UPDATE_PEND_INFO();
        }
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCSTLSO.TLR;
        BPCRTLTM.INFO.LEN = 1404;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLRM();
        CEP.TRC(SCCGWA, BPRTLT.TM_OUT_LMT);
        R000_TRANS_DATA_PARAMETER();
        BPCRTLTM.INFO.LEN = 1404;
        BPCRTLTM.INFO.POINTER = BPRTLT;
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        BPCRTLTM.INFO.FUNC = 'M';
        BPRTLT.TX_LVL = BPCSTLSO.TX_LVL;
        BPRTLT.ATH_LVL = BPCSTLSO.AUTH_LVL;
        BPRTLT.TMP_TX_LVL = BPCSTLSO.TX_LVL;
        BPRTLT.TMP_ATH_LVL = BPCSTLSO.AUTH_LVL;
        BPRTLT.IDEN_DEV_ID = BPCSTLSO.DEV_ID;
        S000_CALL_BPZRTLRM();
    }
    public void B080_01_01_UPDATE_TERM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTERM);
        SCCTERM.FUNC = 'O';
        SCCTERM.NO = SCCGWA.COMM_AREA.TERM_ID;
        S000_CALL_SCCRTERM();
    }
    public void B080_01_02_UPDATE_ACCU_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLAM);
        BPCFTLAM.OP_CODE = 'C';
        BPCFTLAM.TLR = BPCSTLSO.TLR;
        S000_CALL_BPZFTLAM();
    }
    public void B080_01_03_UPDATE_PEND_INFO() throws IOException,SQLException,Exception {
        WS_COND_FLG.WS_TBL_FLAG = 'Y';
        B080_01_03_01_STARTBR_PROCESS();
        WS_PEND_CNT = 0;
        IBS.init(SCCGWA, WS_PEND_INFO);
        while (WS_COND_FLG.WS_TBL_FLAG != 'N') {
            B080_01_03_02_READNEXT_PROCESS();
            if (WS_COND_FLG.WS_TBL_FLAG == 'Y' 
                && (BPCSTLSO.TLR.equalsIgnoreCase(BPRPEND.KEY.TLR)) 
                && (BPRPEND.TX_COUNT > 0)) {
                WS_PEND_CNT = WS_PEND_CNT + 1;
                WS_PEND_INFO.WS_PEND_DATA[WS_PEND_CNT-1].WS_BUSS_TYPE = BPRPEND.KEY.BUSS_TYP;
            }
        }
        B080_01_03_03_ENDBR_PROCESS();
        CEP.TRC(SCCGWA, WS_PEND_CNT);
        for (WS_J = 1; WS_J <= WS_PEND_CNT; WS_J += 1) {
            IBS.init(SCCGWA, BPCPEND);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPEND.KEY.TYP = "PEND ";
            BPCPEND.KEY.CD = WS_PEND_INFO.WS_PEND_DATA[WS_J-1].WS_BUSS_TYPE;
            BPCPRMR.DAT_PTR = BPCPEND;
            S000_CALL_BPZPRMR();
            WS_PEND_INFO.WS_PEND_DATA[WS_J-1].WS_BUSS_CLR_FLG = BPCPEND.DATA_TXT.CLR_FLG;
            CEP.TRC(SCCGWA, BPCPEND.DATA_TXT.CLR_FLG);
        }
        for (WS_J = 1; WS_J <= WS_PEND_CNT; WS_J += 1) {
            if (WS_PEND_INFO.WS_PEND_DATA[WS_J-1].WS_BUSS_CLR_FLG == 'Y') {
                IBS.init(SCCGWA, BPCFTLPM);
                BPCFTLPM.OP_CODE = 'C';
                BPCFTLPM.BUSS_TYP = WS_PEND_INFO.WS_PEND_DATA[WS_J-1].WS_BUSS_TYPE;
                BPCFTLPM.TLR = BPCSTLSO.TLR;
                S000_CALL_BPZFTLPM();
            }
        }
    }
    public void B080_01_03_01_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = BPCSTLSO.TLR;
        BPCRPENM.INFO.FUNC = 'S';
        S000_CALL_BPZRPENM();
    }
    public void B080_01_03_02_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPCRPENM.INFO.FUNC = 'N';
        BPRPEND.KEY.TLR = BPCSTLSO.TLR;
        S000_CALL_BPZRPENM();
        if (BPCRPENM.RETURN_INFO == 'F') {
            WS_COND_FLG.WS_TBL_FLAG = 'Y';
        } else if (BPCRPENM.RETURN_INFO == 'N') {
            WS_COND_FLG.WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B080_01_03_03_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPCRPENM.INFO.FUNC = 'E';
        BPRPEND.KEY.TLR = BPCSTLSO.TLR;
        S000_CALL_BPZRPENM();
        if (BPCRPENM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_01_TLR_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.KEY.TLR = BPCSTLSO.TLR;
        BPCRTLTM.INFO.FUNC = 'Q';
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLRM();
    }
    public void B080_02_READ_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHIST);
        BPCRHIST.FUNC = 'O';
        BPCRHIST.TLR = BPCSTLSO.TLR;
        CEP.TRC(SCCGWA, BPCSTLSO.TLR);
        S000_CALL_BPZRHIST();
        CEP.TRC(SCCGWA, BPCRBANK.SG_IN_CNT);
        for (WS_J = 1; WS_J <= BPCRBANK.SG_IN_CNT 
            && WS_J <= 30 
            && BPCRHIST.INFO[WS_J-1].AC_DT != 0; WS_J += 1) {
            WS_INFO = " ";
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = "T" + WS_INFO.substring(1);
            WS_DATE = BPCRHIST.INFO[WS_J-1].AC_DT;
            WS_TIME = BPCRHIST.INFO[WS_J-1].TX_TM;
            JIBS_tmp_str[0] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 5 - 1) + JIBS_tmp_str[0].substring(0, 4) + WS_INFO.substring(5 + 4 - 1);
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 9 - 1) + "/" + WS_INFO.substring(9 + 1 - 1);
            JIBS_tmp_str[0] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 10 - 1) + JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1) + WS_INFO.substring(10 + 2 - 1);
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 12 - 1) + "/" + WS_INFO.substring(12 + 1 - 1);
            JIBS_tmp_str[0] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 13 - 1) + JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1) + WS_INFO.substring(13 + 2 - 1);
            JIBS_tmp_str[0] = "" + WS_TIME;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 18 - 1) + JIBS_tmp_str[0].substring(0, 2) + WS_INFO.substring(18 + 2 - 1);
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 20 - 1) + ":" + WS_INFO.substring(20 + 1 - 1);
            JIBS_tmp_str[0] = "" + WS_TIME;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 21 - 1) + JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1) + WS_INFO.substring(21 + 2 - 1);
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 23 - 1) + ":" + WS_INFO.substring(23 + 1 - 1);
            JIBS_tmp_str[0] = "" + WS_TIME;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 24 - 1) + JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1) + WS_INFO.substring(24 + 2 - 1);
            if (BPCRHIST.INFO[WS_J-1].RMK == null) BPCRHIST.INFO[WS_J-1].RMK = "";
            JIBS_tmp_int = BPCRHIST.INFO[WS_J-1].RMK.length();
            for (int i=0;i<240-JIBS_tmp_int;i++) BPCRHIST.INFO[WS_J-1].RMK += " ";
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = WS_INFO.substring(0, 39 - 1) + BPCRHIST.INFO[WS_J-1].RMK.substring(0, 30) + WS_INFO.substring(39 + 30 - 1);
            CEP.TRC(SCCGWA, "HIST");
            CEP.TRC(SCCGWA, WS_INFO);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_SIGN_INFO;
            if (WS_INFO == null) WS_INFO = "";
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_INFO += " ";
            WS_INFO = "T" + WS_INFO.substring(1);
            S000_ERR_MSG_PROC();
        }
        S000_HISTORY_PROCESS();
    }
    public void B090_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_FMT);
        WS_OUT_FMT.WS_FMT_TM_OUT_LMT = BPRTLT.TM_OUT_LMT;
        WS_OUT_FMT.WS_TLR = BPRTLT.KEY.TLR;
        WS_OUT_FMT.WS_TLR_TX_LVL = BPRTLT.TMP_TX_LVL;
        WS_OUT_FMT.WS_TLR_ACC_FLG = WS_COND_FLG.WS_ACC_FLG;
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + WS_OUT_FMT.WS_TLR_STSW;
        JIBS_f0 = "";
        for (int i=0;i<3-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_OUT_FMT.WS_TLR_STSW;
        JIBS_NumStr = BPRTLT.TLR_STSW.substring(6 - 1, 6 + 1 - 1) + JIBS_NumStr.substring(1);
        WS_OUT_FMT.WS_TLR_STSW = Short.parseShort(JIBS_NumStr);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + WS_OUT_FMT.WS_TLR_STSW;
        JIBS_f0 = "";
        for (int i=0;i<3-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_OUT_FMT.WS_TLR_STSW;
        JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + BPRTLT.TLR_STSW.substring(7 - 1, 7 + 1 - 1) + JIBS_NumStr.substring(2 + 1 - 1);
        WS_OUT_FMT.WS_TLR_STSW = Short.parseShort(JIBS_NumStr);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        JIBS_tmp_str[0] = "" + WS_OUT_FMT.WS_TLR_STSW;
        JIBS_f0 = "";
        for (int i=0;i<3-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_OUT_FMT.WS_TLR_STSW;
        JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + BPRTLT.TLR_STSW.substring(5 - 1, 5 + 1 - 1) + JIBS_NumStr.substring(3 + 1 - 1);
        WS_OUT_FMT.WS_TLR_STSW = Short.parseShort(JIBS_NumStr);
        IBS.init(SCCGWA, BPCPDATH);
        BPCPDATH.TLR_NO = BPCSTLSO.TLR;
        S000_CALL_BPZPDATH();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPDATH.ROLE_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_OUT_FMT.WS_ROLE_INFO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPDATH.TATH_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_OUT_FMT.WS_TATH_INFO);
        CEP.TRC(SCCGWA, WS_OUT_FMT);
        CEP.TRC(SCCGWA, WS_OUT_FMT.WS_FMT_TM_OUT_LMT);
        CEP.TRC(SCCGWA, WS_OUT_FMT.WS_TLR);
        CEP.TRC(SCCGWA, WS_OUT_FMT.WS_TLR_TX_LVL);
        CEP.TRC(SCCGWA, WS_OUT_FMT.WS_TLR_STSW);
        CEP.TRC(SCCGWA, WS_OUT_FMT.WS_ROLE_INFO);
        CEP.TRC(SCCGWA, WS_OUT_FMT.WS_TATH_INFO);
        CEP.TRC(SCCGWA, "111111111");
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "XXXXXXXXX");
        SCCFMT.FMTID = "03D11";
        CEP.TRC(SCCGWA, "YYYYYYYYY");
        SCCFMT.DATA_PTR = WS_OUT_FMT;
        CEP.TRC(SCCGWA, "ZZZZZZZZZ");
        SCCFMT.DATA_LEN = 1020339;
        CEP.TRC(SCCGWA, "JJJJJJJJJ");
        IBS.FMT(SCCGWA, SCCFMT);
        S000_CALL_BPZPOPTR();
    }
    public void S000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "BPVCNTY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.REF_NO = BPCSTLSO.TLR;
        BPCPNHIS.INFO.TX_TYP_CD = "PD03";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TERM_ID);
        if (BPCPNHIS.INFO.TX_RMK == null) BPCPNHIS.INFO.TX_RMK = "";
        JIBS_tmp_int = BPCPNHIS.INFO.TX_RMK.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPNHIS.INFO.TX_RMK += " ";
        if (K_HIS_REMARKS == null) K_HIS_REMARKS = "";
        JIBS_tmp_int = K_HIS_REMARKS.length();
        for (int i=0;i<240-JIBS_tmp_int;i++) K_HIS_REMARKS += " ";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS + BPCPNHIS.INFO.TX_RMK.substring(30);
        if (BPCPNHIS.INFO.TX_RMK == null) BPCPNHIS.INFO.TX_RMK = "";
        JIBS_tmp_int = BPCPNHIS.INFO.TX_RMK.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPNHIS.INFO.TX_RMK += " ";
        if (SCCGWA.COMM_AREA.TERM_ID == null) SCCGWA.COMM_AREA.TERM_ID = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.TERM_ID.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.TERM_ID += " ";
        BPCPNHIS.INFO.TX_RMK = BPCPNHIS.INFO.TX_RMK.substring(0, 31 - 1) + SCCGWA.COMM_AREA.TERM_ID + BPCPNHIS.INFO.TX_RMK.substring(31 + 12 - 1);
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_SIGN_HIS, BPCRHIST);
        CEP.TRC(SCCGWA, BPCRHIST.RC);
        if (BPCRHIST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHIST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        CEP.TRC(SCCGWA, BPCRTLTM.RC.RC_CODE);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
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
    public void S000_CALL_BPZRTMUG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TMUG, BPCRTMUG);
        CEP.TRC(SCCGWA, BPCRTMUG.RC.RC_CODE);
        if (BPCRTMUG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTMUG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTMUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TMUP, BPCRTMUP);
        CEP.TRC(SCCGWA, BPCRTMUP.RC.RC_CODE);
        if (BPCRTMUP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTMUP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFPREA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_PRIV_ERA, BPCFPREA);
        CEP.TRC(SCCGWA, BPCFPREA.RC.RC_CODE);
        if (BPCFPREA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFPREA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCCRTERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCTERM);
        IBS.CALLCPN(SCCGWA, CPN_R_GET_TERM_INFO, SCCTERM);
        CEP.TRC(SCCGWA, SCCTERM.RC);
        if (SCCTERM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCTERM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_ACCU_MGM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPDATH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_DOWN_ATH, BPCPDATH);
        if (BPCPDATH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPDATH.RC);
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
    public void S000_CALL_BPZPQBPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BK_PSW, BPCPQBPW);
        if (BPCPQBPW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBPW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQTLH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_QUERY_TLT_HOLIDAY, BPCPQTLH);
    }
    public void S000_CALL_BPZUTCVC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-CASH-BV-CHK", BPCUTCVC);
        if (BPCUTCVC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCUTCVC.RC);
        }
    }
    public void S000_CALL_BPZFCHPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CHECK_TLR_PSW, BPCFCHPW);
        CEP.TRC(SCCGWA, BPCFCHPW.RC.RC_CODE);
        if (BPCFCHPW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCHPW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM   ", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRPENM() throws IOException,SQLException,Exception {
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.LEN = 22;
        IBS.CALLCPN(SCCGWA, "BP-R-PEND-MAINTAIN  ", BPCRPENM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTDTL() throws IOException,SQLException,Exception {
        BPCRTDTL.INFO.POINTER = BPRTDTL;
        BPCRTDTL.INFO.LEN = 93;
        IBS.CALLCPN(SCCGWA, "BP-R-STARTBR-TDTL", BPCRTDTL);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPCSTLSO.OPT == '0'
            || BPCSTLSO.OPT_QUDAO == 'Q') {
            BPRTLT.SIGN_STS = 'O';
            BPRTLT.PSW_RETRY = 0;
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            JIBS_tmp_str[0] = "" + 1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPRTLT.TLR_STSW = BPRTLT.TLR_STSW.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPRTLT.TLR_STSW.substring(12 + 1 - 1);
            BPRTLT.SIGN_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (WS_COND_FLG.WS_FIRST_SIGN_ON_FLG == 'Y') {
                BPRTLT.SIGN_TIMES = 1;
            } else {
                BPRTLT.SIGN_TIMES = BPRTLT.SIGN_TIMES + 1;
            }
            BPRTLT.SIGN_TRM = SCCGWA.COMM_AREA.TERM_ID;
            if (BPCSTLSO.NEW_PSW.trim().length() > 0) {
                BPRTLT.KB_PSW = WS_NEW_PASSWORD;
                BPRTLT.KB_PSW_DT = SCCGWA.COMM_AREA.AC_DATE;
                if (BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[10-1].LAST_KBPSW.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "SSSSSSSSSSS");
                    for (WS_J = 1; WS_J <= 9; WS_J += 1) {
                        WS_K = WS_J + 1;
                        CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_J-1].LAST_KBPSW);
                        BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_J-1].LAST_KBPSW = BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_K-1].LAST_KBPSW;
                        BPRTLT.LAST_KB_PSW = IBS.CLS2CPY(SCCGWA, BPRTLT.REDEFINES96);
                    }
                    BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[10-1].LAST_KBPSW = BPRTLT.KB_PSW;
                    BPRTLT.LAST_KB_PSW = IBS.CLS2CPY(SCCGWA, BPRTLT.REDEFINES96);
                } else {
                    CEP.TRC(SCCGWA, "ZZZZZZZZZZZ");
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[1-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[2-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[3-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[4-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[5-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[6-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[7-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[8-1].LAST_KBPSW);
                    CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[9-1].LAST_KBPSW);
                    for (WS_J = 10; WS_J != 0 
                        && BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_J-1].LAST_KBPSW.trim().length() <= 0; WS_J += -1) {
                        CEP.TRC(SCCGWA, BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_J-1].LAST_KBPSW);
                    }
                    WS_K = WS_J + 1;
                    BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_K-1].LAST_KBPSW = BPRTLT.KB_PSW;
                    BPRTLT.LAST_KB_PSW = IBS.CLS2CPY(SCCGWA, BPRTLT.REDEFINES96);
                }
            }
            if (WS_COND_FLG.WS_RESET_LVL_FLAG == 'Y') {
                BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
                BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
            }
        } else if (BPCSTLSO.OPT == '1') {
            BPRTLT.SIGN_STS = 'O';
            BPRTLT.PSW_RETRY = 0;
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            JIBS_tmp_str[0] = "" + 1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPRTLT.TLR_STSW = BPRTLT.TLR_STSW.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPRTLT.TLR_STSW.substring(12 + 1 - 1);
            BPRTLT.SIGN_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (WS_COND_FLG.WS_FIRST_SIGN_ON_FLG == 'Y') {
                BPRTLT.SIGN_TIMES = 1;
            } else {
                BPRTLT.SIGN_TIMES = BPRTLT.SIGN_TIMES + 1;
            }
            BPRTLT.SIGN_TRM = "AUTH";
            if (BPCSTLSO.NEW_PSW.trim().length() > 0) {
                BPRTLT.KB_PSW = WS_NEW_PASSWORD;
                BPRTLT.KB_PSW_DT = SCCGWA.COMM_AREA.AC_DATE;
                if (BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[10-1].LAST_KBPSW.trim().length() > 0) {
                    for (WS_J = 1; WS_J <= 9; WS_J += 1) {
                        WS_K = WS_J + 1;
                        BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_J-1].LAST_KBPSW = BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_K-1].LAST_KBPSW;
                        BPRTLT.LAST_KB_PSW = IBS.CLS2CPY(SCCGWA, BPRTLT.REDEFINES96);
                    }
                    BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[10-1].LAST_KBPSW = BPRTLT.KB_PSW;
                    BPRTLT.LAST_KB_PSW = IBS.CLS2CPY(SCCGWA, BPRTLT.REDEFINES96);
                } else {
                    for (WS_J = 10; WS_J != 0 
                        && BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_J-1].LAST_KBPSW.trim().length() <= 0; WS_J += -1) {
                    }
                    WS_K = WS_J + 1;
                    BPRTLT.REDEFINES96.LAST_KB_PSW_DATA[WS_K-1].LAST_KBPSW = BPRTLT.KB_PSW;
                    BPRTLT.LAST_KB_PSW = IBS.CLS2CPY(SCCGWA, BPRTLT.REDEFINES96);
                }
            }
            if (WS_COND_FLG.WS_RESET_LVL_FLAG == 'Y') {
                BPRTLT.TMP_TX_LVL = BPRTLT.TX_LVL;
                BPRTLT.TMP_ATH_LVL = BPRTLT.ATH_LVL;
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (WS_COND_FLG.WS_FIRST_SIGN_ON_FLG == 'Y') {
            BPRTLT.LAST_JRN = 0;
        }
        if (BPCSTLSO.OPT_QUDAO != 'Q') {
            BPRTLT.NEW_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            BPRTLT.NEW_BR = BPCSTLSO.NEW_BR;
        }
    }
    public void S000_CALL_BPZPOPTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-PRINTER", BPCFTLPM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
