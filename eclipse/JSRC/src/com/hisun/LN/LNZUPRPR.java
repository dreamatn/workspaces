package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCSOCAC;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNZUPRPR {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_LAST_TX_SEQ = 0;
    int WS_TRANM_TR_SEQ = 0;
    double WS_PROJ_INT = 0;
    double WS_B_INT = 0;
    String WS_CONTRACT_TYPE = " ";
    int WS_IC_CAL_DUE_DT = 0;
    int WS_P_CAL_DUE_DT = 0;
    char WS_CTL_FLG = ' ';
    short WS_IC_CUR_TERM1 = 0;
    String WS_LOAN_STSW = " ";
    double WS_REPY_I = 0;
    int WS_WOFF_LEN = 0;
    LNZUPRPR_WS_O_WOFF_AMT WS_O_WOFF_AMT = new LNZUPRPR_WS_O_WOFF_AMT();
    int WS_TOTAL_NUM = 0;
    String WS_PAPER_NO = " ";
    String WS_CTL_STSW = " ";
    double WS_TX_I_AMT = 0;
    double WS_LOAN_BALL24 = 0;
    double WS_LOAN_AMT = 0;
    char WS_JQ_EXIST_FLG = ' ';
    char WS_AGRE_FOUND_FLG = ' ';
    char WS_ICTL_D17_FLG = ' ';
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCRWOFF LNCRWOFF = new LNCRWOFF();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNRDISC LNRDISC = new LNRDISC();
    LNCRDISC LNCRDISC = new LNCRDISC();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNRINTD LNRINTD = new LNRINTD();
    LNCICAL LNCICAL = new LNCICAL();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCSRATE LNCSRATE = new LNCSRATE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNRAGRE LNRAGRE = new LNRAGRE();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    CIRACAC CIRACAC = new CIRACAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    LNRCONT LNRCONT = new LNRCONT();
    CICCUST CICCUST = new CICCUST();
    LNCBALLM LNCBALLM = new LNCBALLM();
    CICACCU CICACCU = new CICACCU();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    int WS_VAT_DT = 0;
    int WS_COUNT_CNT = 0;
    SCCGWA SCCGWA;
    LNCUPRP LNCUPRPR;
    public void MP(SCCGWA SCCGWA, LNCUPRP LNCUPRPR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUPRPR = LNCUPRPR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUPRPR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCUPRPR.RC.RC_APP = "LN";
        LNCUPRPR.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        T000_READ_LNTRCVD();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            B100_REV_CAL_PROC();
            if (pgmRtn) return;
        }
        B200_MAIN_PROC();
        if (pgmRtn) return;
        if (LNCUPRPR.COMM_DATA.ACM_EVENT.equalsIgnoreCase("MA") 
            && WS_CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            B430_CLDLCTNR_UPD_PROCESS();
            if (pgmRtn) return;
        } else {
            B400_CTNR_UPD_PROCESS();
            if (pgmRtn) return;
        }
        B233_DEL_PYIF_REC();
        if (pgmRtn) return;
        B410_ACCRUAL_SWITCH();
        if (pgmRtn) return;
        B500_MOD_OLDTRAN();
        if (pgmRtn) return;
        B100_R_REV_ICAL_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCUPRPR.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCUPRPR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_READ_LNTTRAN();
        if (pgmRtn) return;
        if (LNCTRANM.REC_DATA.TRAN_STS == 'R') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_CANCEL_REV, LNCUPRPR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_READ_LNTCONT();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.LAST_TX_SEQ != WS_TRANM_TR_SEQ) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCUPRPR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_READ_LNTAPRD();
        if (pgmRtn) return;
    }
    public void B010_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '3';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUPRPR.COMM_DATA.LN_AC;
        if (LNCUPRPR.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUPRPR.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCUPRPR.COMM_DATA.TXN_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = LNCUPRPR.COMM_DATA.JRNNO;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCUPRPR.COMM_DATA.TOT_AMTS.PAY_P_AMT = LNCTRANM.REC_DATA.P_AMT;
        LNCUPRPR.COMM_DATA.TOT_AMTS.PAY_I_AMT = LNCTRANM.REC_DATA.I_AMT;
        WS_LOAN_AMT = LNCTRANM.REC_DATA.I_ADJ_AMT * ( -1 );
        LNCUPRPR.COMM_DATA.TOT_AMTS.PAY_F_AMT1 = LNCTRANM.REC_DATA.PREPAY_CHG;
        WS_TRANM_TR_SEQ = LNCTRANM.REC_DATA.TR_SEQ;
        LNCUPRPR.COMM_DATA.HRG_AMT = LNCTRANM.REC_DATA.F_AMT;
        WS_PROJ_INT = LNCTRANM.REC_DATA.I_WAV_AMT;
        WS_LOAN_STSW = LNCTRANM.REC_DATA.LOAN_STSW;
        WS_REPY_I = LNCTRANM.REC_DATA.I_AMT;
        WS_WOFF_LEN = 32;
        if (LNCTRANM.REC_DATA.TR_DATA == null) LNCTRANM.REC_DATA.TR_DATA = "";
        JIBS_tmp_int = LNCTRANM.REC_DATA.TR_DATA.length();
        for (int i=0;i<1024-JIBS_tmp_int;i++) LNCTRANM.REC_DATA.TR_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LNCTRANM.REC_DATA.TR_DATA.substring(0, WS_WOFF_LEN), WS_O_WOFF_AMT);
