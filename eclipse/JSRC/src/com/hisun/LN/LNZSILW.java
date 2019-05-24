package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSILW {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm LNTRCVD_BR = new brParm();
    DBParm LNTBALZ_RD;
    brParm LNTINTA_BR = new brParm();
    DBParm LNTTRAN_RD;
    brParm LNTTRAN_BR = new brParm();
    DBParm LNTRCVD_RD;
    DBParm LNTCTPY_RD;
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 1000;
    String K_HIS_REMARKS = "CONTRACT INTEREST CHANGE";
    String K_HIS_RMKS = "INTEREST CHANGE";
    String K_FMT_ID = "LN606";
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    int K_MAX_BRW_NUM = 1000;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNZSILW_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSILW_WS_TEMP_VARIABLE();
    short WS_MAX_COL_NO = 0;
    short WS_TS_CNT = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    int WS_RESP = 0;
    LNZSILW_WS_TS_CNTL WS_TS_CNTL = new LNZSILW_WS_TS_CNTL();
    LNZSILW_WS_SPE_OUT WS_SPE_OUT = new LNZSILW_WS_SPE_OUT();
    String WS_PROD_CD = " ";
    double WS_TOT_WAV = 0;
    double WS_TOT_WAV2 = 0;
    double WS_WAV_INT = 0;
    double WS_WAV_LC = 0;
    double WS_WAV_RINT = 0;
    double WS_TOT_L_WAV = 0;
    double WS_TOT_O_WAV = 0;
    double WS_TOT_I_WAV = 0;
    double WS_TOT_I_WAV2 = 0;
    String WS_TERM = " ";
    char WS_AMT_TYP = ' ';
    double WS_TOT_D_I_AMT = 0;
    double WS_TOT_D_O_AMT = 0;
    double WS_TOT_D_L_AMT = 0;
    double WS_TOT_W_I_LC = 0;
    double WS_TOT_W_I_LC2 = 0;
    double WS_TOT_W_O_LC = 0;
    double WS_TOT_W_L_LC = 0;
    double WS_TOT_W_I = 0;
    double WS_ALL_INT = 0;
    double WS_OVD_INT = 0;
    LNZSILW_WS_TMP_VAR WS_TMP_VAR = new LNZSILW_WS_TMP_VAR();
    LNZSILW_WS_TERM_AMT WS_TERM_AMT = new LNZSILW_WS_TERM_AMT();
    char WS_INTA_FOUND_FLG = ' ';
    short WS_IDX = 0;
    double WS_DUEINT = 0;
    double WS_INQ_PPEP = 0;
    double WS_INQ_PPEI = 0;
    String WS_CTL_STSW = " ";
    double WS_TMP_AMT = 0;
    double WS_RCVD_O_AMT = 0;
    double WS_RCVD_L_AMT = 0;
    double WS_ADJ_LC_AMT = 0;
    double WS_ADJ_O_AMT = 0;
    double WS_ADJ_L_AMT = 0;
    int WS_NEXT_LC_CAL_DAT = 0;
    int WS_NEXT_LC_CAL_DAT2 = 0;
    int WS_LAST_F_VAL_DATE = 0;
    LNZSILW_WS_REV_QUERY WS_REV_QUERY = new LNZSILW_WS_REV_QUERY();
    LNZSILW_WS_DUL_OUTPUT WS_DUL_OUTPUT = new LNZSILW_WS_DUL_OUTPUT();
    LNZSILW_WS_FMT_OUTPUT WS_FMT_OUTPUT = new LNZSILW_WS_FMT_OUTPUT();
    double WS_TOT_AMT = 0;
    double WS_I_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_OI_AMT = 0;
    double WS_OO_AMT = 0;
    double WS_OL_AMT = 0;
    double WS_BALL24 = 0;
    double WS_TOT_WAV_INT = 0;
    double WS_TOT_WAV_LC = 0;
    double WS_TOT_WAV_RINT = 0;
    double WS_ACCU_TERM = 0;
    char WS_EOF_FLG = ' ';
    char WS_CONT_FLG = ' ';
    char WS_RCVD_FLG = ' ';
    char WS_STOP = ' ';
    char WS_READ_TRAN = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRINTA LNRINTA = new LNRINTA();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNRRELA LNRRELA = new LNRRELA();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCLCCM LNCLCCM = new LNCLCCM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNRACRU LNRACRU = new LNRACRU();
    LNCRACRU LNCRACRU = new LNCRACRU();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    LNCSILW LNCSILW;
    public void MP(SCCGWA SCCGWA, LNCSILW LNCSILW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSILW = LNCSILW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSILW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSILW.RC.RC_APP = "LN";
        LNCSILW.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B000_CHECK_PROC();
            if (pgmRtn) return;
            B040_GET_INFO();
            if (pgmRtn) return;
            B010_MAIN_PROC();
            if (pgmRtn) return;
            B020_UPDATE_LNTCONT_INFO();
            if (pgmRtn) return;
            B020_TOT_TRAN_PROC();
            if (pgmRtn) return;
            B030_INTLC_WAV_PROC();
            if (pgmRtn) return;
            B050_WRITE_BPTPNHIS();
            if (pgmRtn) return;
        } else {
            B040_GET_INFO();
            if (pgmRtn) return;
            B190_UPD_TRAN_CONT_CANCEL();
            if (pgmRtn) return;
            T000_STARTBR_TRAN_CANCEL();
            if (pgmRtn) return;
            T000_READNEXT_LNTTRAN();
            if (pgmRtn) return;
            while (WS_READ_TRAN != 'N') {
                T000_UPDATE_LNTTRAN_CANCEL();
                if (pgmRtn) return;
                T000_REWRITE_TRAN_CANCEL();
                if (pgmRtn) return;
                B210_UPDATE_RCVD_CANCEL();
                if (pgmRtn) return;
                T000_READNEXT_LNTTRAN();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTTRAN();
            if (pgmRtn) return;
            B030_INTLC_WAV_PROC();
            if (pgmRtn) return;
        }
    }
    public void B190_UPD_TRAN_CONT_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        LNCRCONT.FUNC = 'R';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        T000_READ_LNTTRAN_CANCEL_T();
        if (pgmRtn) return;
        T000_UPDATE_LNTTRAN_CANCEL();
        if (pgmRtn) return;
        T000_REWRITE_TRAN_CANCEL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRTRAN.TR_SEQ);
        WS_TOT_I_WAV = LNRTRAN.I_WAV_AMT;
        WS_TOT_I_WAV2 = LNRTRAN.I_ADJ_AMT;
        WS_TOT_O_WAV = LNRTRAN.O_WAV_AMT;
        WS_TOT_L_WAV = LNRTRAN.L_WAV_AMT;
        WS_NEXT_LC_CAL_DAT2 = LNRTRAN.LAST_LC_CAL_DAT;
        if (LNRCONT.LAST_TX_SEQ != LNRTRAN.TR_SEQ) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNRCONT.LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ - 1;
        LNRCONT.LAST_F_VAL_DATE = LNRTRAN.LAST_F_VAL_DATE;
        CEP.TRC(SCCGWA, LNRCONT.LAST_TX_SEQ);
        LNCRCONT.FUNC = 'U';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        LNCRICTL.FUNC = 'U';
        LNRICTL.NEXT_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT2;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void B210_UPDATE_RCVD_CANCEL() throws IOException,SQLException,Exception {
        T000_UPDATE_RCVD_CANCEL();
        if (pgmRtn) return;
        LNRRCVD.I_WAV_AMT = LNRRCVD.I_WAV_AMT - LNRTRAN.I_WAV_AMT - LNRTRAN.I_ADJ_AMT;
        LNRRCVD.O_WAV_AMT = LNRRCVD.O_WAV_AMT - LNRTRAN.O_WAV_AMT;
        LNRRCVD.L_WAV_AMT = LNRRCVD.L_WAV_AMT - LNRTRAN.L_WAV_AMT;
        WS_TMP_AMT = 0;
        WS_TMP_AMT = LNRRCVD.I_PAY_AMT + LNRRCVD.O_PAY_AMT + LNRRCVD.L_PAY_AMT + LNRRCVD.F_PAY_AMT;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        if (WS_TMP_AMT > 0) {
            LNRRCVD.REPY_STS = '1';
        } else {
            LNRRCVD.REPY_STS = '0';
        }
        LNRRCVD.PI_STOP_DT = LNRTRAN.PI_STOP_DT;
        LNRRCVD.O_LST_CAL_AMT = LNRTRAN.O_LST_CAL_AMT;
        LNRRCVD.O_LST_PST_AMT = LNRTRAN.O_LST_PST_AMT;
        LNRRCVD.L_LST_CAL_AMT = LNRTRAN.L_LST_CAL_AMT;
        LNRRCVD.L_LST_PST_AMT = LNRTRAN.L_LST_PST_AMT;
        LNRRCVD.O_REC_AMT = LNRTRAN.O_AMT;
        LNRRCVD.L_REC_AMT = LNRTRAN.L_AMT;
        T000_REWRITE_RCVD_CANCEL();
        if (pgmRtn) return;
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        WS_NEXT_LC_CAL_DAT = LNRICTL.NEXT_LC_CAL_DAT;
        WS_CTL_STSW = LNRICTL.CTL_STSW;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_0433;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSILW.TOT_WAV == 0 
            && LNCSILW.WAV_INT == 0 
            && LNCSILW.WAV_LC == 0 
            && LNCSILW.WAV_RINT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT_EXEMPT_AMT, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSILW.TOT_WAV != 0 
            && (LNCSILW.WAV_INT != 0 
            || LNCSILW.WAV_LC != 0 
            || LNCSILW.WAV_RINT != 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT_WAV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNCSILW.BR_NO;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FTA_NOT_SAME, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            R000_INQ_RCVD();
            if (pgmRtn) return;
        }
        if (LNCSILW.TOT_WAV != 0) {
            B011_MAIN_PROC();
            if (pgmRtn) return;
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(39 - 1, 39 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_STS_TURN, LNCSILW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCSILW.WAV_INT != 0 
            || LNCSILW.WAV_LC != 0 
            || LNCSILW.WAV_RINT != 0) {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAA");
            B012_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_TOT_WAV = LNCSILW.TOT_WAV;
        if (WS_TOT_WAV < 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_INPUTERROR, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_IDX = 0;
        B011_STARTBR_LNTRCVD();
        if (pgmRtn) return;
        B012_READNEXT_LNTRCVD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_RCVD_FLG != 'N' 
            && WS_EOF_FLG != 'Y' 
            && WS_STOP != 'Y') {
            if (LNRRCVD.REPY_STS != '2') {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
                WS_AMT_TYP = LNRRCVD.KEY.AMT_TYP;
                WS_TERM = "" + LNRRCVD.KEY.TERM;
                JIBS_tmp_int = WS_TERM.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) WS_TERM = "0" + WS_TERM;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_TERM = LNRRCVD.KEY.TERM;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = 0;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = 0;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = 0;
                B400_GET_OL_REC_AMT();
                if (pgmRtn) return;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT = LNRRCVD.I_REC_AMT;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC = WS_TERM_AMT.WS_TERM_O_AMT;
                WS_TMP_VAR.WS_O_REC_AMT = WS_TERM_AMT.WS_TERM_O_AMT;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT = WS_TERM_AMT.WS_TERM_L_AMT;
                WS_TMP_VAR.WS_L_REC_AMT = WS_TERM_AMT.WS_TERM_L_AMT;
                CEP.TRC(SCCGWA, WS_TOT_WAV);
                CEP.TRC(SCCGWA, WS_TERM_AMT.WS_TERM_O_AMT);
                CEP.TRC(SCCGWA, WS_TERM_AMT.WS_TERM_L_AMT);
                if (WS_TOT_WAV > 0) {
                    B110_WAV_L_PERFORM();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_TOT_WAV);
                    if (WS_TOT_WAV > 0) {
                        B110_WAV_O_PERFORM();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, WS_TOT_WAV);
                    if (WS_TOT_WAV > 0) {
                        WS_TMP_VAR.WS_I_REC_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                        B110_WAV_I_PERFORM();
                        if (pgmRtn) return;
                    }
                }
                B023_TRAN_ADD_PROCESS();
                if (pgmRtn) return;
                B021_UPDATE_RCVD();
                if (pgmRtn) return;
                B500_PRINT_PEROCESS();
                if (pgmRtn) return;
                C000_OUT_TRC_PROC();
                if (pgmRtn) return;
                if (WS_TOT_WAV <= 0) {
                    WS_STOP = 'Y';
                }
            }
            WS_TOT_WAV_INT += WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
            WS_TOT_WAV_LC += WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC;
            WS_TOT_WAV_RINT += WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT;
            B012_READNEXT_LNTRCVD();
            if (pgmRtn) return;
        }
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = WS_TOT_WAV_INT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = WS_TOT_WAV_LC;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = WS_TOT_WAV_RINT;
        B013_ENDBR_LNTRCVD();
        if (pgmRtn) return;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B012_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK CONTRACT-NO EFFECTIVE");
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        LNCPCFTA.BR_GP[1-1].BR = LNCCONTM.REC_DATA.DOMI_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_GET_APRD_INFO();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            && LNCSILW.WAV_INT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_1680, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_WAV_INT = LNCSILW.WAV_INT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = LNCSILW.WAV_INT;
        WS_WAV_LC = LNCSILW.WAV_LC;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = LNCSILW.WAV_LC;
        WS_TOT_O_WAV = LNCSILW.WAV_LC;
        WS_WAV_RINT = LNCSILW.WAV_RINT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = LNCSILW.WAV_RINT;
        WS_TOT_L_WAV = LNCSILW.WAV_RINT;
        WS_TOT_WAV = LNCSILW.WAV_INT + LNCSILW.WAV_LC + LNCSILW.WAV_RINT;
        if (WS_TOT_WAV > WS_ALL_INT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_INPUTERROR, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B011_STARTBR_LNTRCVD();
        if (pgmRtn) return;
        B012_READNEXT_LNTRCVD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_RCVD_FLG != 'N' 
            && WS_EOF_FLG != 'Y' 
            && WS_STOP != 'Y') {
            CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
            if (LNRRCVD.REPY_STS != '2') {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
                WS_AMT_TYP = LNRRCVD.KEY.AMT_TYP;
                WS_TERM = "" + LNRRCVD.KEY.TERM;
                JIBS_tmp_int = WS_TERM.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) WS_TERM = "0" + WS_TERM;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_TERM = LNRRCVD.KEY.TERM;
                B400_GET_OL_REC_AMT();
                if (pgmRtn) return;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC = WS_TERM_AMT.WS_TERM_O_AMT;
                WS_TMP_VAR.WS_O_REC_AMT = WS_TERM_AMT.WS_TERM_O_AMT;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT = WS_TERM_AMT.WS_TERM_L_AMT;
                WS_TMP_VAR.WS_L_REC_AMT = WS_TERM_AMT.WS_TERM_L_AMT;
                WS_TMP_VAR.WS_I_REC_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT = WS_TMP_VAR.WS_I_REC_AMT;
                B120_WAV_I_PERFORM();
                if (pgmRtn) return;
                B120_WAV_O_PERFORM();
                if (pgmRtn) return;
                B120_WAV_L_PERFORM();
                if (pgmRtn) return;
                B023_TRAN_ADD_PROCESS();
                if (pgmRtn) return;
                B021_UPDATE_RCVD();
                if (pgmRtn) return;
                B500_PRINT_PEROCESS();
                if (pgmRtn) return;
                C000_OUT_TRC_PROC();
                if (pgmRtn) return;
                if (WS_WAV_RINT <= 0 
                    && WS_WAV_LC <= 0 
                    && WS_WAV_INT <= 0) {
                    WS_STOP = 'Y';
                }
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = 0;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = 0;
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = 0;
            }
            B012_READNEXT_LNTRCVD();
            if (pgmRtn) return;
        }
        B013_ENDBR_LNTRCVD();
        if (pgmRtn) return;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = LNCSILW.WAV_INT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = LNCSILW.WAV_LC;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = LNCSILW.WAV_RINT;
        R020_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B110_WAV_L_PERFORM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TMP_VAR.WS_L_REC_AMT);
        if (WS_TMP_VAR.WS_L_REC_AMT != 0) {
            WS_TOT_WAV = WS_TOT_WAV - WS_TMP_VAR.WS_L_REC_AMT;
            CEP.TRC(SCCGWA, WS_TOT_WAV);
            if (WS_TOT_WAV < 0) {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = WS_TOT_WAV + WS_TMP_VAR.WS_L_REC_AMT;
                CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT);
                WS_TOT_L_WAV = WS_TOT_L_WAV + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT;
                CEP.TRC(SCCGWA, WS_TOT_L_WAV);
            } else {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = WS_TMP_VAR.WS_L_REC_AMT;
                CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT);
                WS_TOT_L_WAV = WS_TOT_L_WAV + WS_TMP_VAR.WS_L_REC_AMT;
                CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT);
            }
        }
    }
    public void B110_WAV_O_PERFORM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TOT_WAV);
        CEP.TRC(SCCGWA, WS_TMP_VAR.WS_O_REC_AMT);
        if (WS_TMP_VAR.WS_O_REC_AMT > 0) {
            WS_TOT_WAV = WS_TOT_WAV - WS_TMP_VAR.WS_O_REC_AMT;
            CEP.TRC(SCCGWA, WS_TOT_WAV);
            if (WS_TOT_WAV < 0) {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = WS_TOT_WAV + WS_TMP_VAR.WS_O_REC_AMT;
                CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC);
                WS_TOT_O_WAV = WS_TOT_O_WAV + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC;
                CEP.TRC(SCCGWA, WS_TOT_O_WAV);
            } else {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = WS_TMP_VAR.WS_O_REC_AMT;
                WS_TOT_O_WAV = WS_TOT_O_WAV + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC;
                CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC);
                CEP.TRC(SCCGWA, WS_TOT_O_WAV);
            }
        }
    }
    public void B110_WAV_I_PERFORM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TMP_VAR.WS_I_REC_AMT);
        WS_TOT_WAV = WS_TOT_WAV - WS_TMP_VAR.WS_I_REC_AMT;
        CEP.TRC(SCCGWA, WS_TOT_WAV);
        if (WS_TOT_WAV < 0) {
            WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = WS_TOT_WAV + WS_TMP_VAR.WS_I_REC_AMT;
            CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT);
            WS_TOT_I_WAV2 = WS_TOT_I_WAV2 + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
            CEP.TRC(SCCGWA, WS_TOT_I_WAV);
            CEP.TRC(SCCGWA, WS_TOT_I_WAV2);
        } else {
            WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = WS_TMP_VAR.WS_I_REC_AMT;
            CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT);
            WS_TOT_I_WAV2 = WS_TOT_I_WAV2 + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
            CEP.TRC(SCCGWA, WS_TOT_I_WAV);
            CEP.TRC(SCCGWA, WS_TOT_I_WAV2);
        }
    }
    public void B120_WAV_I_PERFORM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_WAV_INT);
        if (WS_WAV_INT > 0) {
            WS_WAV_INT = WS_WAV_INT - WS_TMP_VAR.WS_I_REC_AMT;
            if (WS_WAV_INT < 0) {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = WS_WAV_INT + WS_TMP_VAR.WS_I_REC_AMT;
            } else {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT = WS_TMP_VAR.WS_I_REC_AMT;
            }
            WS_TOT_I_WAV2 = WS_TOT_I_WAV2 + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
            CEP.TRC(SCCGWA, WS_TOT_I_WAV);
        }
        CEP.TRC(SCCGWA, WS_WAV_INT);
    }
    public void B120_WAV_O_PERFORM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_WAV_LC);
        CEP.TRC(SCCGWA, WS_TMP_VAR.WS_O_REC_AMT);
        if (WS_WAV_LC > 0) {
            WS_WAV_LC = WS_WAV_LC - WS_TMP_VAR.WS_O_REC_AMT;
            if (WS_WAV_LC < 0) {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = WS_WAV_LC + WS_TMP_VAR.WS_O_REC_AMT;
            } else {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC = WS_TMP_VAR.WS_O_REC_AMT;
            }
            CEP.TRC(SCCGWA, WS_WAV_LC);
            CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC);
        }
    }
    public void B120_WAV_L_PERFORM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_WAV_RINT);
        CEP.TRC(SCCGWA, WS_TMP_VAR.WS_L_REC_AMT);
        if (WS_WAV_RINT > 0) {
            WS_WAV_RINT = WS_WAV_RINT - WS_TMP_VAR.WS_L_REC_AMT;
            if (WS_WAV_RINT < 0) {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = WS_WAV_RINT + WS_TMP_VAR.WS_L_REC_AMT;
            } else {
                WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT = WS_TMP_VAR.WS_L_REC_AMT;
            }
            CEP.TRC(SCCGWA, WS_WAV_RINT);
            CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT);
        }
    }
    public void B021_UPDATE_RCVD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSILW.CTA_NO);
        CEP.TRC(SCCGWA, WS_AMT_TYP);
        CEP.TRC(SCCGWA, WS_TERM);
        LNCRRCVD.FUNC = 'R';
        LNRRCVD.KEY.CONTRACT_NO = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_CONTRACT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.AMT_TYP = WS_AMT_TYP;
        if (WS_TERM.trim().length() == 0) LNRRCVD.KEY.TERM = 0;
        else LNRRCVD.KEY.TERM = Short.parseShort(WS_TERM);
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ADJ_O_AMT);
        CEP.TRC(SCCGWA, WS_ADJ_L_AMT);
        LNCRRCVD.FUNC = 'U';
        LNRRCVD.I_WAV_AMT = LNRRCVD.I_WAV_AMT + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
        LNRRCVD.O_WAV_AMT = LNRRCVD.O_WAV_AMT + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC;
        LNRRCVD.L_WAV_AMT = LNRRCVD.L_WAV_AMT + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT;
        LNRRCVD.O_REC_AMT = WS_RCVD_O_AMT;
        LNRRCVD.L_REC_AMT = WS_RCVD_L_AMT;
        WS_TMP_AMT = 0;
        WS_TMP_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT + LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT + LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT + LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNRRCVD.F_REC_AMT - LNRRCVD.F_PAY_AMT - LNRRCVD.F_WAV_AMT + WS_ADJ_O_AMT + WS_ADJ_L_AMT;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        if (WS_TMP_AMT == 0) {
            LNRRCVD.REPY_STS = '2';
        }
        LNRRCVD.PI_STOP_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRRCVD.I_WAV_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.O_WAV_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.L_WAV_AMT);
    }
    public void B023_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'I';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNRRCVD.KEY.AMT_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNRRCVD.KEY.TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.DUE_DT = LNRRCVD.DUE_DT;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TXN_CCY = LNCSPDQ.COMM_DATA.CTA_INFO.CCY;
        LNCTRANM.REC_DATA.TR_MMO = "X7X";
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, WS_NEXT_LC_CAL_DAT);
        CEP.TRC(SCCGWA, WS_LAST_F_VAL_DATE);
        LNCTRANM.REC_DATA.LAST_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LAST_F_VAL_DATE;
        CEP.TRC(SCCGWA, "123");
        LNCTRANM.REC_DATA.PI_STOP_DT = LNRRCVD.PI_STOP_DT;
        LNCTRANM.REC_DATA.O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT;
        LNCTRANM.REC_DATA.O_LST_PST_AMT = LNRRCVD.O_LST_PST_AMT;
        LNCTRANM.REC_DATA.L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT;
        LNCTRANM.REC_DATA.L_LST_PST_AMT = LNRRCVD.L_LST_PST_AMT;
        LNCTRANM.REC_DATA.I_AMT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.I_ADJ_AMT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
        LNCTRANM.REC_DATA.O_AMT = LNRRCVD.O_REC_AMT;
        LNCTRANM.REC_DATA.O_WAV_AMT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC;
        LNCTRANM.REC_DATA.L_AMT = LNRRCVD.L_REC_AMT;
        LNCTRANM.REC_DATA.L_WAV_AMT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        WS_TOT_D_I_AMT = WS_TOT_D_I_AMT + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT;
        WS_TOT_D_O_AMT = WS_TOT_D_O_AMT + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC;
        WS_TOT_D_L_AMT = WS_TOT_D_L_AMT + WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT;
    }
    public void B020_UPDATE_LNTCONT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        LNCRCONT.FUNC = 'R';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNRCONT.LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ + 1;
        LNRCONT.LAST_F_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, LNRCONT.LAST_TX_SEQ);
        LNCRCONT.FUNC = 'U';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B020_TOT_TRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.DUE_DT = 0;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TXN_CCY = LNCSPDQ.COMM_DATA.CTA_INFO.CCY;
        LNCTRANM.REC_DATA.TR_MMO = "X7X";
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.LAST_LC_CAL_DAT = WS_NEXT_LC_CAL_DAT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LAST_F_VAL_DATE;
        LNCTRANM.REC_DATA.I_AMT = WS_TOT_D_I_AMT;
        LNCTRANM.REC_DATA.O_AMT = WS_TOT_D_O_AMT;
        LNCTRANM.REC_DATA.L_AMT = WS_TOT_D_L_AMT;
        LNCTRANM.REC_DATA.I_WAV_AMT = WS_TOT_I_WAV;
        LNCTRANM.REC_DATA.I_ADJ_AMT = WS_TOT_I_WAV2;
        LNCTRANM.REC_DATA.O_WAV_AMT = WS_TOT_O_WAV;
        LNCTRANM.REC_DATA.L_WAV_AMT = WS_TOT_L_WAV;
        LNCTRANM.REC_DATA.TR_SEQ = LNRCONT.LAST_TX_SEQ;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LAST_F_VAL_DATE;
        CEP.TRC(SCCGWA, LNRCONT.LAST_TX_SEQ);
        LNCTRANM.REC_DATA.P_AMT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PRIN;
        LNCTRANM.REC_DATA.OS_BAL = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BAL;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B030_INTLC_WAV_PROC() throws IOException,SQLException,Exception {
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(37 - 1, 37 + 1 - 1));
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, WS_TOT_L_WAV);
        CEP.TRC(SCCGWA, WS_TOT_O_WAV);
        CEP.TRC(SCCGWA, WS_TOT_I_WAV);
        CEP.TRC(SCCGWA, WS_TOT_I_WAV2);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.RVS_IND);
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "WAIVINT";
        LNCCNEV.COMM_DATA.LN_AC = LNCSILW.CTA_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCCNEV.COMM_DATA.ACM_EVENT = "IA";
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_TOT_I_WAV;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_TOT_I_WAV2;
        WS_TOT_W_I = WS_TOT_I_WAV + WS_TOT_I_WAV2;
        LNCCNEV.COMM_DATA.I_AMT += WS_TOT_W_I;
        LNCCNEV.COMM_DATA.O_AMT = WS_TOT_O_WAV;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = WS_TOT_O_WAV;
        LNCCNEV.COMM_DATA.L_AMT = WS_TOT_L_WAV;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_TOT_L_WAV;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNCCNEV.COMM_DATA.RVS_IND = 'Y';
        } else {
            LNCCNEV.COMM_DATA.RVS_IND = 'N';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            R000_GET_BAL();
            if (pgmRtn) return;
        }
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B040_GET_INFO() throws IOException,SQLException,Exception {
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BR_NO = 0;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_STS = ' ';
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PRIN = 0;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BAL = 0;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PROC_CD = " ";
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSILW.CTA_NO;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_STS = LNCSPDQ.COMM_DATA.CTA_INFO.LN_STS;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BR_NO = LNCSPDQ.COMM_DATA.CTA_INFO.BR;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PRIN = LNCSPDQ.COMM_DATA.CTA_INFO.LON_PRIN;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BAL = LNCSPDQ.COMM_DATA.CTA_INFO.LON_BAL;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PROC_CD = LNCSPDQ.COMM_DATA.CTA_INFO.PROD_CD;
        WS_DUEINT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT;
        WS_INQ_PPEP = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT;
        WS_INQ_PPEI = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
        CEP.TRC(SCCGWA, WS_DUEINT);
        CEP.TRC(SCCGWA, WS_INQ_PPEP);
        CEP.TRC(SCCGWA, WS_INQ_PPEI);
        WS_ALL_INT = WS_DUEINT + WS_INQ_PPEP + WS_INQ_PPEI;
        CEP.TRC(SCCGWA, WS_ALL_INT);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (LNCSILW.WAV_INT > WS_DUEINT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT_OVER, LNCSILW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSILW.WAV_LC > WS_INQ_PPEP) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT_OVER_O, LNCSILW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSILW.WAV_RINT > WS_INQ_PPEI) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT_OVER_L, LNCSILW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSILW.TOT_WAV > WS_ALL_INT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_INPUTERROR, LNCSILW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_INQ_RCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSILW.CTA_NO;
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        WS_I_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT;
        WS_O_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT;
        WS_L_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
    }
    public void R000_GET_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.L_AMT);
        CEP.TRC(SCCGWA, WS_I_AMT);
        CEP.TRC(SCCGWA, WS_O_AMT);
        CEP.TRC(SCCGWA, WS_L_AMT);
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        T000_READ_LNTBALZ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL24);
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL48);
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL58);
        WS_BALL24 = LNRBALZ.LOAN_BALL24 - LNRBALZ.LOAN_BALL15;
        if (LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT > 0) {
            if (WS_I_AMT > WS_BALL24) {
                WS_I_AMT = WS_I_AMT - WS_BALL24;
                if (WS_I_AMT < LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT) {
                    WS_OI_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT - WS_I_AMT;
                } else {
                    WS_I_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                }
            } else {
                WS_OI_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
                WS_I_AMT = 0;
            }
        } else {
            WS_I_AMT = 0;
        }
        if (LNCCNEV.COMM_DATA.O_AMT > 0) {
            if (WS_O_AMT > LNRBALZ.LOAN_BALL48) {
                WS_O_AMT = WS_O_AMT - LNRBALZ.LOAN_BALL48;
                if (WS_O_AMT < LNCCNEV.COMM_DATA.O_AMT) {
                    WS_OO_AMT = LNCCNEV.COMM_DATA.O_AMT - WS_O_AMT;
                } else {
                    WS_O_AMT = LNCCNEV.COMM_DATA.O_AMT;
                }
            } else {
                WS_OO_AMT = LNCCNEV.COMM_DATA.O_AMT;
                WS_O_AMT = 0;
            }
        } else {
            WS_O_AMT = 0;
        }
        if (LNCCNEV.COMM_DATA.L_AMT > 0) {
            if (WS_L_AMT > LNRBALZ.LOAN_BALL58) {
                WS_L_AMT = WS_L_AMT - LNRBALZ.LOAN_BALL58;
                if (WS_L_AMT < LNCCNEV.COMM_DATA.L_AMT) {
                    WS_OL_AMT = LNCCNEV.COMM_DATA.L_AMT - WS_L_AMT;
                } else {
                    WS_L_AMT = LNCCNEV.COMM_DATA.L_AMT;
                }
            } else {
                WS_OL_AMT = LNCCNEV.COMM_DATA.L_AMT;
                WS_L_AMT = 0;
            }
        } else {
            WS_L_AMT = 0;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNRBALZ.LOAN_BALL24 = LNRBALZ.LOAN_BALL24 - WS_OI_AMT;
            LNRBALZ.LOAN_BALL48 = LNRBALZ.LOAN_BALL48 - WS_OO_AMT;
            LNRBALZ.LOAN_BALL58 = LNRBALZ.LOAN_BALL58 - WS_OL_AMT;
        } else {
            LNRBALZ.LOAN_BALL24 = LNRBALZ.LOAN_BALL24 + WS_OI_AMT;
            LNRBALZ.LOAN_BALL48 = LNRBALZ.LOAN_BALL48 + WS_OO_AMT;
            LNRBALZ.LOAN_BALL58 = LNRBALZ.LOAN_BALL58 + WS_OL_AMT;
        }
        T000_REWRITE_LNTBALZ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_I_AMT);
        CEP.TRC(SCCGWA, WS_O_AMT);
        CEP.TRC(SCCGWA, WS_L_AMT);
        LNCCNEV.COMM_DATA.NX_I_AMT = WS_I_AMT;
        LNCCNEV.COMM_DATA.TX_LLC_AMT += WS_OI_AMT;
        LNCCNEV.COMM_DATA.NX_OLC_AMT = WS_O_AMT;
        LNCCNEV.COMM_DATA.TX_LLC_AMT += WS_OO_AMT;
        LNCCNEV.COMM_DATA.NX_LLC_AMT = WS_L_AMT;
        LNCCNEV.COMM_DATA.TX_LLC_AMT += WS_OL_AMT;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.TX_LLC_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.L_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.O_AMT);
    }
    public void S000_CALL_LNZRACRU() throws IOException,SQLException,Exception {
        LNCRACRU.REC_PTR = LNRACRU;
        LNCRACRU.REC_LEN = 207;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTACRU", LNCRACRU);
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B400_GET_OL_REC_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        WS_TERM_AMT.WS_TERM_O_AMT = 0;
        WS_TERM_AMT.WS_TERM_L_AMT = 0;
        WS_RCVD_O_AMT = 0;
        WS_RCVD_L_AMT = 0;
        WS_ADJ_O_AMT = 0;
        WS_ADJ_L_AMT = 0;
        B212_LC_COMP();
        if (pgmRtn) return;
        WS_TERM_AMT.WS_TERM_O_AMT += LNCLCCM.COMM_DATA.O_AMT;
        WS_TERM_AMT.WS_TERM_L_AMT += LNCLCCM.COMM_DATA.L_AMT;
        R000_GET_ADJ_AMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
        CEP.TRC(SCCGWA, WS_ADJ_O_AMT);
        CEP.TRC(SCCGWA, WS_ADJ_L_AMT);
        WS_RCVD_O_AMT = LNCLCCM.COMM_DATA.O_AMT + LNRRCVD.O_PAY_AMT + LNRRCVD.O_WAV_AMT - WS_ADJ_O_AMT;
        WS_RCVD_L_AMT = LNCLCCM.COMM_DATA.L_AMT + LNRRCVD.L_PAY_AMT + LNRRCVD.L_WAV_AMT - WS_ADJ_L_AMT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC = WS_TERM_AMT.WS_TERM_O_AMT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT = WS_TERM_AMT.WS_TERM_L_AMT;
        CEP.TRC(SCCGWA, WS_RCVD_O_AMT);
        CEP.TRC(SCCGWA, WS_RCVD_L_AMT);
    }
    public void B212_LC_COMP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNCSILW.CTA_NO;
        LNCLCCM.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCLCCM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCLCCM.COMM_DATA.SUF_NO = "0" + LNCLCCM.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TYPE = LNRRCVD.KEY.AMT_TYP;
        LNCLCCM.COMM_DATA.TERM = LNRRCVD.KEY.TERM;
        LNCLCCM.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.END_DATE);
        S000_CALL_LNZLCCM();
        if (pgmRtn) return;
    }
    public void R000_GET_ADJ_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        if (LNRRCVD.KEY.AMT_TYP == 'P') {
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'O';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_O_AMT = WS_ADJ_LC_AMT;
        } else if (LNRRCVD.KEY.AMT_TYP == 'I') {
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'L';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_L_AMT = WS_ADJ_LC_AMT;
        } else {
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'O';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_O_AMT = WS_ADJ_LC_AMT;
            IBS.init(SCCGWA, LNRINTA);
            LNRINTA.KEY.REPY_MTH = 'L';
            B410_ADJ_LC_PROCESS();
            if (pgmRtn) return;
            WS_ADJ_L_AMT = WS_ADJ_LC_AMT;
        }
    }
    public void C000_OUT_TRC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_TERM);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_CONTRACT_NO);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BR_NO);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_STS);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PRIN);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BAL);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT);
    }
    public void B500_PRINT_PEROCESS() throws IOException,SQLException,Exception {
        WS_IDX += 1;
        if (WS_IDX <= 30) {
            if (WS_TERM.trim().length() == 0) WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_TERM = 0;
            else WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_TERM = Short.parseShort(WS_TERM);
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_CONTRACT_NO = LNCSILW.CTA_NO;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_PROC_CD = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PROC_CD;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_BR_NO = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BR_NO;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_STS = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_STS;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_PRIN = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PRIN;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_BAL = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_BAL;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_RCV_INT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_WAV_INT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_INT;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_RCV_LC = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_WAV_LC = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_LC;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_RCV_RINT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT;
            WS_FMT_OUTPUT.WS_OUTPUT_DATA[WS_IDX-1].WS_OUT_WAV_RINT = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_WAV_RINT;
        }
    }
    public void R020_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSILW.CTA_NO;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_INT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_LC = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT;
        WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_RCV_RINT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA);
        SCCMPAG.DATA_LEN = 181;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_PFHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSILW.CTA_NO;
        BPCPFHIS.DATA.AC = LNCSILW.CTA_NO;
        BPCPFHIS.DATA.CI_NO = LNCSPDQ.COMM_DATA.CTA_INFO.CI_NO;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = LNCSPDQ.COMM_DATA.CTA_INFO.CCY;
        BPCPFHIS.DATA.TX_MMO = "LN606";
        BPCPFHIS.DATA.TX_AMT = LNCSILW.TOT_WAV + LNCSILW.WAV_INT + LNCSILW.WAV_LC + LNCSILW.WAV_RINT;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
        CEP.TRC(SCCGWA, WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PROC_CD);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        BPCPFHIS.DATA.PROD_CD = WS_DUL_OUTPUT.WS_OUT_DATA.WS_O_PROC_CD;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARKS;
        BPCPFHIS.DATA.FMT_CODE = "LN606";
        BPCPFHIS.DATA.FMT_LEN = 465;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCSILW);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B050_WRITE_BPTPNHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE-BPTPNHIS");
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = LNCSILW.CTA_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = " ";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TOOL);
        BPCPNHIS.INFO.TX_TYP_CD = "P129";
        WS_REV_QUERY.WS_Q_CI_NO = LNCSILW.CI_NO;
        WS_REV_QUERY.WS_Q_CI_NM = LNCSILW.CI_CNM;
        WS_REV_QUERY.WS_Q_CTA_NO = LNCSILW.CTA_NO;
        WS_REV_QUERY.WS_Q_PROD_CD = LNRCONT.PROD_CD;
        WS_REV_QUERY.WS_Q_CCY = LNRCONT.CCY;
        if (LNCSILW.TOT_WAV != 0) {
            WS_REV_QUERY.WS_Q_TX_AMT = LNCSILW.TOT_WAV;
        } else {
            WS_REV_QUERY.WS_Q_TX_AMT = LNCSILW.WAV_INT + LNCSILW.WAV_LC + LNCSILW.WAV_RINT;
        }
        BPCPNHIS.INFO.FMT_ID = "LNCSILW";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 325;
        BPCPNHIS.INFO.NEW_DAT_PT = WS_REV_QUERY;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LINK-BPZPNHIS-OK");
    }
    public void R000_GET_APRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZLCCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LC-CMP", LNCLCCM, true);
        CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.LC_AMT);
        if (LNCLCCM.RC.RC_RTNCODE != 0) {
            LNCSILW.RC.RC_APP = LNCLCCM.RC.RC_APP;
            LNCSILW.RC.RC_RTNCODE = LNCLCCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCSILW.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCSILW.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B011_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.order = "VAL_DT,AMT_TYP";
        IBS.STARTBR(SCCGWA, LNRRCVD, LNTRCVD_BR);
    }
    public void B012_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RCVD_FLG = 'N';
        }
    }
    public void B013_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
            + "AND REPY_STS < > '2'";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_READNT_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void T000_REWRITE_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void B410_ADJ_LC_PROCESS() throws IOException,SQLException,Exception {
        WS_ADJ_LC_AMT = 0;
        WS_INTA_FOUND_FLG = 'N';
        T00_STARTBR_LNTINTA();
        if (pgmRtn) return;
        T00_READNEXT_LNTINTA();
        if (pgmRtn) return;
        while (WS_INTA_FOUND_FLG != 'N') {
            WS_ADJ_LC_AMT += LNRINTA.ADJ_AMT;
            T00_READNEXT_LNTINTA();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTINTA();
        if (pgmRtn) return;
    }
    public void T00_STARTBR_LNTINTA() throws IOException,SQLException,Exception {
        LNRINTA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNRINTA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNRINTA.KEY.REPY_TERM = LNRRCVD.KEY.TERM;
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTA_FOUND_FLG = 'Y';
        } else {
            WS_INTA_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTINTA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void T000_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.REC_FLAG);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void T000_READ_LNTTRAN_CANCEL_T() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REC_FLAG, TR_JRN_NO,TXN_TYP";
        LNTTRAN_RD.fst = true;
        IBS.READ(SCCGWA, LNRTRAN, LNTTRAN_RD);
    }
    public void T000_UPDATE_LNTTRAN_CANCEL() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.upd = true;
        IBS.READ(SCCGWA, LNRTRAN, LNTTRAN_RD);
    }
    public void T000_REWRITE_TRAN_CANCEL() throws IOException,SQLException,Exception {
        LNRTRAN.TRAN_STS = 'R';
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.REWRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TRAN_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSILW.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'I';
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REC_FLAG, TR_JRN_NO";
        LNTTRAN_BR.rp.where = "TXN_TYP < > :LNRTRAN.KEY.TXN_TYP";
        LNTTRAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_UPDATE_RCVD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = LNRTRAN.KEY.SUB_CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNRTRAN.KEY.TXN_TYP;
        LNRRCVD.KEY.TERM = LNRTRAN.KEY.TXN_TERM;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_RCVD_CANCEL() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 477;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void T000_READ_LNTCTPY_PROC() throws IOException,SQLException,Exception {
        LNRCTPY.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        IBS.READ(SCCGWA, LNRCTPY, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCSILW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
        if (LNCSILW.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSILW=");
            CEP.TRC(SCCGWA, LNCSILW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
