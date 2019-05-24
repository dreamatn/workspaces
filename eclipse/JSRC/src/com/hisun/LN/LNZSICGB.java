package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.SM.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSICGB {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm LNTTRAN_BR = new brParm();
    DBParm LNTINTA_RD;
    boolean pgmRtn = false;
    char WS_INTA_FOUND_FLG = ' ';
    String K_MMO = "S202";
    LNZSICGB_WS_ERR_MSG WS_ERR_MSG = new LNZSICGB_WS_ERR_MSG();
    int WS_LAST_F_VAL_DATE = 0;
    String WS_SETL_AC_TYP = " ";
    short WS_SV_ADJ_SEQ = 0;
    char WS_CNT_INTA = ' ';
    char WS_TMP_REPY_MTH = ' ';
    String WS_AC_TYP = " ";
    LNZSICGB_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSICGB_WS_LOAN_CONT_AREA();
    double WS_IC_AMT = 0;
    double WS_LOAN_I_AMT = 0;
    double WS_LOAN_O_AMT = 0;
    double WS_LOAN_L_AMT = 0;
    double WS_LOAN_SUM_AMT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRTRAN LNRTRAN = new LNRTRAN();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCONTM LNCCONTM = new LNCCONTM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRINTA LNRINTA = new LNRINTA();
    LNCRINTA LNCRINTA = new LNCRINTA();
    CICQACRI CICQACRI = new CICQACRI();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNRAGRE LNRAGRE = new LNRAGRE();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    LNCSICGB LNCSICGB;
    public void MP(SCCGWA SCCGWA, LNCSICGB LNCSICGB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSICGB = LNCSICGB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSICGB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        LNCSICGB.RC.RC_APP = "LN";
        LNCSICGB.RC.RC_RTNCODE = 0;
        WS_SV_ADJ_SEQ = 0;
        T00_READ_LNTINTA();
        if (pgmRtn) return;
        WS_SV_ADJ_SEQ = LNRINTA.KEY.ADJ_SEQ;
        CEP.TRC(SCCGWA, WS_SV_ADJ_SEQ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B010_AC_CHECK();
        if (pgmRtn) return;
        B001_CHK_FTA_TYP();
        if (pgmRtn) return;
        B100_REVD_OVDPI_PROC();
        if (pgmRtn) return;
        B200_DEPOSIT_AMT();
        if (pgmRtn) return;
        B300_WRITE_LNTTRAN();
        if (pgmRtn) return;
        B400_ADD_LNTINTA();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK CONTRACT-NO MUST INPUT");
        if (LNCSICGB.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK SICGB-IC-AMT GT ZERO");
        if (LNCSICGB.IC_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IC_AMT_ERR, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK CONTRACT-NO EFFECTIVE");
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK I-AMT ");
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_IC_AMT = LNCSICGB.IC_AMT;
            if (LNCSICGB.RINT_TYP == '1') {
                if (WS_IC_AMT > WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[25-1].WS_LOAN_BAL) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_I_AMT_OVER, LNCSICGB.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_LOAN_I_AMT = WS_IC_AMT;
                }
            } else if (LNCSICGB.RINT_TYP == '2') {
                if (WS_IC_AMT > WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[44-1].WS_LOAN_BAL) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_O_AMT_OVER, LNCSICGB.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_LOAN_O_AMT = WS_IC_AMT;
                }
            } else if (LNCSICGB.RINT_TYP == '3') {
                if (WS_IC_AMT > WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[54-1].WS_LOAN_BAL) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_L_AMT_OVER, LNCSICGB.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_LOAN_L_AMT = WS_IC_AMT;
                }
            } else {
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNCCONTM.REC_DATA.LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ + 1;
            WS_LAST_F_VAL_DATE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
            LNCCONTM.REC_DATA.LAST_F_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            T000_STARTBR_LNTTRAN();
            if (pgmRtn) return;
            T000_READNEXT_LNTTRAN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LAST_TX_SEQ);
            CEP.TRC(SCCGWA, LNRTRAN.TR_SEQ);
            if (LNCCONTM.REC_DATA.LAST_TX_SEQ != LNRTRAN.TR_SEQ) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCSICGB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNRTRAN.ACTL_DATE;
            T000_ENDBR_LNTTRAN();
            if (pgmRtn) return;
            LNCCONTM.REC_DATA.LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ - 1;
            WS_LOAN_I_AMT = LNRTRAN.I_AMT;
            WS_LOAN_O_AMT = LNRTRAN.O_AMT;
            WS_LOAN_L_AMT = LNRTRAN.L_AMT;
        }
        LNCCONTM.FUNC = '2';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLGU") 
            || LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_1673, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLOSED, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN0396, LNCSICGB.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_0433, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_0435, LNCSICGB.RC);
        }
        CEP.TRC(SCCGWA, "CHECK CCY");
        if (LNCSICGB.CCY.trim().length() > 0 
            && !LNCSICGB.CCY.equalsIgnoreCase(LNCCONTM.REC_DATA.CCY)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT_CCY, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            LNCSICGB.CCY = LNCCONTM.REC_DATA.CCY;
        }
        if (LNCSICGB.TRAN_VDT == 0) {
            LNCSICGB.TRAN_VDT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B010_AC_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNCSICGB.DD_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_AC_TYP = "01";
            WS_SETL_AC_TYP = WS_AC_TYP;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_AC_TYP = "05";
            WS_SETL_AC_TYP = WS_AC_TYP;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void B001_CHK_FTA_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNCSICGB.BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNCPCFTA.BR_GP[3-1].BR = CICQACRI.O_DATA.O_OWNER_BK;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[3-1].BR);
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FTA_NOT_SAME, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_REVD_OVDPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.ACM_EVENT = "RPI";
        LNCCNEV.COMM_DATA.EVENT_CODE = "RVPYINT";
        LNCCNEV.COMM_DATA.LN_AC = LNCSICGB.CTA_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCSICGB.TRAN_VDT;
        CEP.TRC(SCCGWA, WS_LOAN_I_AMT);
        CEP.TRC(SCCGWA, WS_LOAN_O_AMT);
        CEP.TRC(SCCGWA, WS_LOAN_L_AMT);
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_LOAN_I_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = WS_LOAN_O_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_LOAN_L_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].RINT_TYP = LNCSICGB.RINT_TYP;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNCCNEV.COMM_DATA.RVS_IND = 'N';
        } else {
            LNCCNEV.COMM_DATA.RVS_IND = 'Y';
        }
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B200_DEPOSIT_AMT() throws IOException,SQLException,Exception {
        if (WS_SETL_AC_TYP.equalsIgnoreCase("01")
            || WS_SETL_AC_TYP.equalsIgnoreCase("05")) {
            R000_DD_DEBIT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_TYPE, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_AC_TYPE() throws IOException,SQLException,Exception {
        if (WS_SETL_AC_TYP.equalsIgnoreCase("01")) {
            if (LNCSICGB.AC_TYP != '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_TYP, LNCSICGB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_SETL_AC_TYP.equalsIgnoreCase("05")) {
            if (LNCSICGB.AC_TYP != '5') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_TYP, LNCSICGB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_DD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        if (WS_SETL_AC_TYP.equalsIgnoreCase("05")) {
            DDCUCRAC.CARD_NO = LNCSICGB.DD_AC;
        }
        if (WS_SETL_AC_TYP.equalsIgnoreCase("01")) {
            DDCUCRAC.CARD_NO = LNCSICGB.DD_AC;
        }
        DDCUCRAC.CCY = LNCSICGB.CCY;
        if (LNCSICGB.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        DDCUCRAC.TX_AMT = LNCSICGB.IC_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.TX_MMO = K_MMO;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        DDCUDRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B300_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LAST_TX_SEQ);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, LNCTRANM);
            LNCTRANM.FUNC = '4';
            LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
            LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
            LNCTRANM.REC_DATA.KEY.TR_DATE = LNRTRAN.KEY.TR_DATE;
            LNCTRANM.REC_DATA.KEY.TR_JRN_NO = LNRTRAN.KEY.TR_JRN_NO;
            LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
            LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
            LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
            S000_CALL_LNZTRANM();
            if (pgmRtn) return;
            LNCTRANM.FUNC = '2';
            LNCTRANM.REC_DATA.TRAN_STS = 'R';
            LNCTRANM.REC_DATA.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNCTRANM.REC_DATA.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            S000_CALL_LNZTRANM();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNCTRANM);
            LNCTRANM.FUNC = '0';
            LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
            LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
            LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
            LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
            LNCTRANM.REC_DATA.TRAN_STS = 'N';
            LNCTRANM.REC_DATA.TXN_CCY = LNCSICGB.CCY;
            LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
            LNCTRANM.REC_DATA.I_AMT = WS_LOAN_I_AMT;
            LNCTRANM.REC_DATA.O_AMT = WS_LOAN_O_AMT;
            LNCTRANM.REC_DATA.L_AMT = WS_LOAN_L_AMT;
            LNCTRANM.REC_DATA.TR_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
            LNCTRANM.REC_DATA.ACTL_DATE = WS_LAST_F_VAL_DATE;
            S000_CALL_LNZTRANM();
            if (pgmRtn) return;
        }
    }
    public void B400_ADD_LNTINTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTA);
        IBS.init(SCCGWA, LNCRINTA);
        LNCRINTA.FUNC = 'R';
        LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        LNRINTA.KEY.SUB_CTA_NO = 0;
        LNRINTA.KEY.REPY_MTH = WS_TMP_REPY_MTH;
        CEP.TRC(SCCGWA, LNRINTA.KEY.REPY_MTH);
        LNRINTA.KEY.REPY_TERM = 0;
        CEP.TRC(SCCGWA, WS_SV_ADJ_SEQ);
        if (WS_SV_ADJ_SEQ > 0) {
            WS_SV_ADJ_SEQ = (short) (WS_SV_ADJ_SEQ + 1);
            LNRINTA.KEY.ADJ_SEQ = WS_SV_ADJ_SEQ;
        } else {
            LNRINTA.KEY.ADJ_SEQ = 0;
        }
        S000_CALL_LNZRINTA();
        if (pgmRtn) return;
        if (WS_SV_ADJ_SEQ == 0) {
            IBS.init(SCCGWA, LNRINTA);
            IBS.init(SCCGWA, LNCRINTA);
            LNCRINTA.FUNC = 'A';
            CEP.TRC(SCCGWA, LNRINTA.KEY.ADJ_SEQ);
            LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNRINTA.KEY.SUB_CTA_NO = 0;
            LNRINTA.KEY.REPY_MTH = WS_TMP_REPY_MTH;
            LNRINTA.KEY.REPY_TERM = 0;
            LNRINTA.KEY.ADJ_SEQ = 0;
            LNRINTA.ADJ_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNRINTA.ADJ_AMT = LNCSICGB.IC_AMT;
            LNRINTA.ADJ_RSN = LNCSICGB.RMK;
            S000_CALL_LNZRINTA();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNRINTA);
            IBS.init(SCCGWA, LNCRINTA);
            LNCRINTA.FUNC = 'A';
            LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNRINTA.KEY.SUB_CTA_NO = 0;
            LNRINTA.KEY.REPY_MTH = WS_TMP_REPY_MTH;
            LNRINTA.KEY.REPY_TERM = 0;
            LNRINTA.KEY.ADJ_SEQ += 1;
            CEP.TRC(SCCGWA, LNRINTA.KEY.ADJ_SEQ);
            LNRINTA.ADJ_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNRINTA.ADJ_AMT = LNCSICGB.IC_AMT;
            LNRINTA.ADJ_RSN = LNCSICGB.RMK;
            S000_CALL_LNZRINTA();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNRINTA);
            IBS.init(SCCGWA, LNCRINTA);
            LNCRINTA.FUNC = 'R';
            LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNRINTA.KEY.SUB_CTA_NO = 0;
            LNRINTA.KEY.REPY_MTH = WS_TMP_REPY_MTH;
            CEP.TRC(SCCGWA, LNRINTA.KEY.REPY_MTH);
            LNRINTA.KEY.REPY_TERM = 0;
            LNRINTA.KEY.ADJ_SEQ = 0;
            S000_CALL_LNZRINTA();
            if (pgmRtn) return;
            LNCRINTA.FUNC = 'U';
            CEP.TRC(SCCGWA, "UPDATE LNTINTA TOTAL ROW");
            CEP.TRC(SCCGWA, LNRINTA.KEY.ADJ_SEQ);
            LNRINTA.ADJ_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNRINTA.KEY.SUB_CTA_NO = 0;
            LNRINTA.KEY.REPY_MTH = WS_TMP_REPY_MTH;
            LNRINTA.KEY.REPY_TERM = 0;
            LNRINTA.KEY.ADJ_SEQ = 0;
            CEP.TRC(SCCGWA, LNCSICGB.IC_AMT);
            LNRINTA.ADJ_AMT = LNRINTA.ADJ_AMT + LNCSICGB.IC_AMT;
            LNRINTA.ADJ_RSN = LNCSICGB.RMK;
            S000_CALL_LNZRINTA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRINTA.ADJ_AMT);
            IBS.init(SCCGWA, LNRINTA);
            IBS.init(SCCGWA, LNCRINTA);
            LNCRINTA.FUNC = 'A';
            LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
            LNRINTA.KEY.SUB_CTA_NO = 0;
            LNRINTA.KEY.REPY_MTH = WS_TMP_REPY_MTH;
            LNRINTA.KEY.REPY_TERM = 0;
            LNRINTA.KEY.ADJ_SEQ = WS_SV_ADJ_SEQ;
            CEP.TRC(SCCGWA, "INSERT NEW DETAIL IN THE LNTINTA");
            CEP.TRC(SCCGWA, LNRINTA.KEY.ADJ_SEQ);
            LNRINTA.ADJ_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNRINTA.ADJ_AMT = LNCSICGB.IC_AMT;
            LNRINTA.ADJ_RSN = LNCSICGB.RMK;
            S000_CALL_LNZRINTA();
            if (pgmRtn) return;
        }
    }
    public void B600_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.OTH_AC = LNCSICGB.DD_AC;
        BPCPFHIS.DATA.AC = LNCSICGB.CTA_NO;
        BPCPFHIS.DATA.CI_NO = LNCSICGB.CI_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSICGB.TRAN_VDT;
        BPCPFHIS.DATA.TX_CCY = LNCSICGB.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSICGB.IC_AMT;
        BPCPFHIS.DATA.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        BPCPFHIS.DATA.REMARK = LNCSICGB.RMK;
        BPCPFHIS.DATA.TX_MMO = K_MMO;
        BPCPFHIS.DATA.FMT_CODE = "LNP40";
        BPCPFHIS.DATA.FMT_LEN = 787;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCSICGB);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.JRNNO);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
        CEP.TRC(SCCGWA, LNCSICGB);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.REC_FLAG);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
        CEP.TRC(SCCGWA, LNRTRAN.KEY.TXN_TYP);
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "CONTRACT_NO = :LNRTRAN.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = :LNRTRAN.KEY.REC_FLAG "
            + "AND TR_DATE = :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TXN_TYP = :LNRTRAN.KEY.TXN_TYP";
        LNTTRAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_NOTFND, LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCSICGB.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCSICGB.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.TR_DATE);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.TR_JRN_NO);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.TXN_TYP);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.TXN_TERM);
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.KEY.TRAN_FLG);
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        if (DDCUCRAC.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSICGB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRINTA() throws IOException,SQLException,Exception {
        WS_CNT_INTA = ' ';
        LNCRINTA.REC_PTR = LNRINTA;
        LNCRINTA.REC_LEN = 267;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTINTA", LNCRINTA);
        CEP.TRC(SCCGWA, "1111111111111");
        CEP.TRC(SCCGWA, LNRINTA.KEY.CONTRACT_NO);
        if (LNCRINTA.RETURN_INFO != 'F') {
            if (LNCRINTA.RETURN_INFO == 'E' 
                || LNCRINTA.RETURN_INFO == 'N') {
                WS_CNT_INTA = 'Y';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRINTA.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_CNT_INTA);
    }
    public void T00_READ_LNTINTA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTA);
        LNRINTA.KEY.CONTRACT_NO = LNCSICGB.CTA_NO;
        LNRINTA.KEY.SUB_CTA_NO = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0136020")) {
            if (LNCSICGB.RINT_TYP == '1') {
                LNRINTA.KEY.REPY_MTH = 'R';
            } else if (LNCSICGB.RINT_TYP == '2') {
                LNRINTA.KEY.REPY_MTH = 'D';
            } else if (LNCSICGB.RINT_TYP == '3') {
                LNRINTA.KEY.REPY_MTH = 'C';
            }
            WS_TMP_REPY_MTH = LNRINTA.KEY.REPY_MTH;
            LNRINTA.KEY.REPY_TERM = 0;
            LNRINTA.KEY.ADJ_SEQ = 0;
            LNTINTA_RD = new DBParm();
            LNTINTA_RD.TableName = "LNTINTA";
            LNTINTA_RD.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
                + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
                + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
                + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM";
            LNTINTA_RD.fst = true;
            LNTINTA_RD.order = "ADJ_SEQ DESC";
            IBS.READ(SCCGWA, LNRINTA, this, LNTINTA_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                LNRINTA.KEY.ADJ_SEQ = 0;
            }
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
        if (LNCSICGB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSICGB=");
            CEP.TRC(SCCGWA, LNCSICGB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
