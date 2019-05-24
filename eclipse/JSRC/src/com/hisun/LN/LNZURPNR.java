package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNZURPNR {
    boolean pgmRtn = false;
    char LNZURPNR_FILLER1 = ' ';
    int WS_I = 0;
    double WS_REPY_P = 0;
    double WS_REPY_I = 0;
    double WS_REPY_O = 0;
    double WS_REPY_L = 0;
    double WS_REPY_F = 0;
    double WS_TMP_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_LAST_F_VAL_DTE = 0;
    int WS_STOP_INT_DATE = 0;
    int WS_LAST_LC_CAL_DAT = 0;
    double WS_B_INT = 0;
    String WS_ERR_MSG = " ";
    double WS_DUE_P_AMT = 0;
    short WS_IC_CUR_TERM1 = 0;
    double WS_TOT_TRAN_F_AMT = 0;
    int WS_TOT_CNT = 0;
    double WS_WOF_TOT_P_AMT = 0;
    double WS_WOF_TOT_I_AMT = 0;
    String WS_LOAN_STSW = " ";
    double WS_PAY_OI_AMT = 0;
    int WS_CLR_DATA_LEN = 0;
    LNZURPNR_WS_CLR_DATA WS_CLR_DATA = new LNZURPNR_WS_CLR_DATA();
    int WS_WOFF_RCV_DATA_LEN = 0;
    LNZURPNR_WS_WOFF_RCV_DATA WS_WOFF_RCV_DATA = new LNZURPNR_WS_WOFF_RCV_DATA();
    double WS_I_NP_AMT = 0;
    double WS_O_NP_AMT = 0;
    double WS_L_NP_AMT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_WOFF_END_FLG = ' ';
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRINTD LNRINTD = new LNRINTD();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCUSTS LNCUSTS = new LNCUSTS();
    SCCGWA SCCGWA;
    LNCURPNR LNCURPNR;
    public void MP(SCCGWA SCCGWA, LNCURPNR LNCURPNR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCURPNR = LNCURPNR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZURPNR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCURPNR.RC.RC_APP = "LN";
        LNCURPNR.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        B444_LLL_UPDATE_ICTL();
        if (pgmRtn) return;
        B400_CTNR_UPD_PROCESS();
        if (pgmRtn) return;
        B410_ACCRUAL_SWITCH();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && LNCURPNR.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT != 0) {
            B400_CTNR_UPD_WOF_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCURPNR.COMM_DATA.CUR_TRM == 'Y') {
            B041_CURRENT_TERM_REV();
            if (pgmRtn) return;
        }
        B500_MOD_OLDTRAN();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCURPNR.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCURPNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_READ_LNTTRAN();
        if (pgmRtn) return;
        if (LNCTRANM.REC_DATA.TRAN_STS == 'R') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_CANCEL_REV, LNCURPNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B444_LLL_UPDATE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUSTS);
        LNCUSTS.COMM_DATA.LN_AC = LNCURPNR.COMM_DATA.LN_AC;
        LNCUSTS.COMM_DATA.SUF_NO = LNCURPNR.COMM_DATA.SUF_NO;
        S000_CALL_LNZUSTS();
        if (pgmRtn) return;
    }
    public void B010_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '3';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCURPNR.COMM_DATA.LN_AC;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCURPNR.COMM_DATA.TXN_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = LNCURPNR.COMM_DATA.JRNNO;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
