package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCSOCAC;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICMACR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNZUPRP {
    boolean pgmRtn = false;
    char K_UPDATE = 'U';
    String K_DY = "DY";
    char K_INT_ACCRUAL = 'A';
    String K_CNTR_CLDD = "CLDD";
    String K_CNTR_CLDL = "CLDL";
    char K_PRE_PAY_INT = '0';
    char K_OVE_P_ACCRUAL = 'B';
    char K_OVE_I_ACCRUAL = 'C';
    String WS_PAPER_NO = " ";
    int WS_TOTAL_NUM = 0;
    double WS_S_AMT = 0;
    String WS_ERR_MSG = " ";
    char LNZUPRP_FILLER5 = ' ';
    int WS_I = 0;
    char WS_TYPE = ' ';
    short WS_TERM = 0;
    double WS_P_AMT = 0;
    double WS_I_AMT = 0;
    double WS_F_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_PAY_N_BAL = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_LST_F_VALDTE = 0;
    double WS_CUR_RATE = 0;
    int WS_INT_CUT_DT = 0;
    String WS_LOAN_STSW = " ";
    char WS_REC_FLAG = ' ';
    double WS_WOF_REC_INT = 0;
    double WS_WOF_PAY_INT = 0;
    double WS_B_INT = 0;
    String WS_CONTRACT_TYPE = " ";
    String WS_LOAN_LAST_STSW = " ";
    short WS_IC_CUR_TERM = 0;
    int WS_IC_CAL_VAL_DT = 0;
    double WS_CUR_RAT = 0;
    short WS_IRUL_SEQ = 0;
    int WS_MAT_DATE = 0;
    String WS_CTL_STSW = " ";
    int WS_SUBS_NO = 0;
    int WS_WOFF_LEN = 0;
    LNZUPRP_WS_O_WOFF_AMT WS_O_WOFF_AMT = new LNZUPRP_WS_O_WOFF_AMT();
    double WS_TX_I_AMT = 0;
    double WS_LOAN_BALL24 = 0;
    double WS_LOAN_AMT = 0;
    char WS_SOLD_LOAN_FLG = ' ';
    char WS_ICTL_D17_FLG = ' ';
    char WS_WOFF_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNRDISC LNRDISC = new LNRDISC();
    LNCRDISC LNCRDISC = new LNCRDISC();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCRWOFF LNCRWOFF = new LNCRWOFF();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCINTDM LNCINTDM = new LNCINTDM();
    LNRINTD LNRINTD = new LNRINTD();
    CICMACR CICMACR = new CICMACR();
    LNCSRATE LNCSRATE = new LNCSRATE();
    LNRRELA LNRRELA = new LNRRELA();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    CIRACAC CIRACAC = new CIRACAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    LNRCONT LNRCONT = new LNRCONT();
    CICCUST CICCUST = new CICCUST();
    LNCBALLM LNCBALLM = new LNCBALLM();
    CICACCU CICACCU = new CICACCU();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    int WS_CNT = 0;
    int WS_COUNT_CNT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCUPRP LNCUPRP;
    public void MP(SCCGWA SCCGWA, LNCUPRP LNCUPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUPRP = LNCUPRP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUPRP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCUPRP.RC.RC_APP = "LN";
        LNCUPRP.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B200_GET_LOAN_INF();
        if (pgmRtn) return;
        B300_MAIN_PROC();
        if (pgmRtn) return;
        B400_CTNR_UPD_PROCESS();
        if (pgmRtn) return;
        B320_ICTL_UPD_PROCESS();
        if (pgmRtn) return;
        B300_CONT_UPD_PROCESS();
        if (pgmRtn) return;
        B500_TRAN_ADD_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCUPRP.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCUPRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCUPRP.COMM_DATA.TOT_AMTS.PAY_TOT_AMT == 0 
            && LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAY_P_TOT_ZERO, LNCUPRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCUPRP.B_AMTS.B_I_AMT);
        CEP.TRC(SCCGWA, LNCUPRP.B_AMTS.B_O_AMT);
        CEP.TRC(SCCGWA, LNCUPRP.B_AMTS.B_L_AMT);
        if (LNCUPRP.B_AMTS.B_I_AMT == 0 
            && LNCUPRP.B_AMTS.B_O_AMT == 0 
            && LNCUPRP.B_AMTS.B_L_AMT == 0) {
        } else {
            WS_SOLD_LOAN_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_SOLD_LOAN_FLG);
    }
    public void B200_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LAST_F_VAL_DATE);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LAST_TX_SEQ);
        WS_LST_F_VALDTE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        WS_LAST_TX_SEQ += 1;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.MAT_DATE);
        WS_CONTRACT_TYPE = LNCCONTM.REC_DATA.CONTRACT_TYPE;
        WS_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        CEP.TRC(SCCGWA, LNCUPRP.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCUPRP.COMM_DATA.SUF_NO);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        if (LNCUPRP.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUPRP.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCUPRP.COMM_DATA.LAST_CUT_DATE == 0) {
            LNCUPRP.COMM_DATA.LAST_CUT_DATE = LNCICTLM.REC_DATA.INT_CUT_DT;
        }
        WS_CUR_RATE = LNCICTLM.REC_DATA.CUR_RAT;
        WS_LOAN_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        WS_IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        WS_IC_CAL_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        WS_CUR_RAT = LNCICTLM.REC_DATA.CUR_RAT;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        B300_INQ_PAY_PRIN();
        if (pgmRtn) return;
    }
    public void B300_INQ_PAY_PRIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCUPRP.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCUPRP.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_PAY_N_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
        CEP.TRC(SCCGWA, WS_PAY_N_BAL);
        CEP.TRC(SCCGWA, LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT > WS_PAY_N_BAL) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GE_PAY_P_AMT, LNCUPRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B330_PYIF_ADD_PROCESS();
            if (pgmRtn) return;
        } else {
            B330_PYIF_DELETE_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCUPRP.COMM_DATA.TOT_AMTS.PAY_I_AMT != 0) {
            B340_INTD_ADD_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCUPRP.RATE_TYP.trim().length() > 0 
            || LNCUPRP.PEN_RATT.trim().length() > 0 
            || LNCUPRP.CPNDRATT.trim().length() > 0 
            || LNCUPRP.ABUSRATT.trim().length() > 0) {
            B345_ADD_RATE();
            if (pgmRtn) return;
        }
        if (LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT != 0) {
            B360_PLPI_ATUO_ADJUST();
            if (pgmRtn) return;
        }
        WS_WOFF_FLG = 'N';
        if (WS_LOAN_STSW == null) WS_LOAN_STSW = "";
        JIBS_tmp_int = WS_LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_LOAN_STSW += " ";
        if (WS_LOAN_STSW == null) WS_LOAN_STSW = "";
        JIBS_tmp_int = WS_LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_LOAN_STSW += " ";
        if (WS_LOAN_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || WS_LOAN_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            B380_WOF_UP_PROC();
            if (pgmRtn) return;
        }
    }
    public void B330_PYIF_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPYIFM);
        LNCPYIFM.FUNC = '0';
        LNCPYIFM.REC_DATA.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        if (LNCUPRP.COMM_DATA.SUF_NO.trim().length() == 0) LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUPRP.COMM_DATA.SUF_NO);
        LNCPYIFM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        LNCPYIFM.REC_DATA.KEY.REPY_SEQ = (short) WS_LAST_TX_SEQ;
        LNCPYIFM.REC_DATA.REPY_MTH = LNCUPRP.COMM_DATA.PRPY_INT_MTH;
        LNCPYIFM.REC_DATA.CUR_REPY_DT = LNCUPRP.COMM_DATA.TR_VAL_DATE;
        LNCPYIFM.REC_DATA.REPY_TOT_AMT = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_TOT_AMT;
        LNCPYIFM.REC_DATA.REIM_PRIN_AMT = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT;
        LNCPYIFM.REC_DATA.REIM_INT_AMT = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_I_AMT;
        LNCPYIFM.REC_DATA.PENALTY = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_F_AMT1;
        LNCPYIFM.REC_DATA.CTL_SEQ = LNCUPRP.RPSP_NO;
        LNCPYIFM.REC_DATA.SYN_FLG = LNCUPRP.COMM_DATA.SYN_FLG;
        LNCPYIFM.REC_DATA.EE_TERM = (double)LNCUPRP.S_TERM;
        S000_CALL_LNZPYIFM();
        if (pgmRtn) return;
    }
    public void B330_PYIF_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPYIFM);
        LNCPYIFM.FUNC = '1';
        LNCPYIFM.REC_DATA.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        if (LNCUPRP.COMM_DATA.SUF_NO.trim().length() == 0) LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUPRP.COMM_DATA.SUF_NO);
        S000_CALL_LNZPYIFM();
        if (pgmRtn) return;
    }
    public void B340_INTD_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRINTD);
        LNRINTD.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        if (LNCUPRP.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTD.KEY.SUB_CTA_NO = 0;
        else LNRINTD.KEY.SUB_CTA_NO = Integer.parseInt(LNCUPRP.COMM_DATA.SUF_NO);
        LNRINTD.KEY.REPY_MTH = 'P';
        LNRINTD.KEY.TERM = WS_IC_CUR_TERM;
        LNRINTD.KEY.INT_TYP = 'N';
        LNRINTD.KEY.CTNR_NO = 2;
        LNRINTD.KEY.VAL_DT = WS_IC_CAL_VAL_DT;
        S000_GET_GROUP();
        if (pgmRtn) return;
        WS_IRUL_SEQ = (short) WS_CNT;
        WS_IRUL_SEQ += 1;
        IBS.init(SCCGWA, LNCINTDM);
        LNCINTDM.FUNC = '0';
        LNCINTDM.REC_DATA.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        if (LNCUPRP.COMM_DATA.SUF_NO.trim().length() == 0) LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCINTDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUPRP.COMM_DATA.SUF_NO);
        LNCINTDM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCINTDM.REC_DATA.KEY.TERM = WS_IC_CUR_TERM;
        LNCINTDM.REC_DATA.KEY.INT_TYP = 'N';
        LNCINTDM.REC_DATA.KEY.IRUL_SEQ = WS_IRUL_SEQ;
        LNCINTDM.REC_DATA.KEY.CTNR_NO = 2;
        LNCINTDM.REC_DATA.KEY.VAL_DT = WS_IC_CAL_VAL_DT;
        LNCINTDM.REC_DATA.CUT_OFF_DT = LNCUPRP.COMM_DATA.TR_VAL_DATE;
        if (LNCUPRP.COMM_DATA.PRPY_INT_MTH == '1') {
            LNCINTDM.REC_DATA.INT_AMT = WS_PAY_N_BAL;
        }
        if (LNCUPRP.COMM_DATA.PRPY_INT_MTH == '2') {
            LNCINTDM.REC_DATA.INT_AMT = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT;
        }
        LNCINTDM.REC_DATA.INT_RAT = WS_CUR_RAT;
        LNCINTDM.REC_DATA.INT = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_I_AMT;
        S000_CALL_LNZINTDM();
        if (pgmRtn) return;
    }
    public void B345_ADD_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRATE);
        LNCSRATE.DATA.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        LNCSRATE.DATA.SUB_CTA_NO = 0;
        LNCSRATE.DATA.RAT_CHG_DT = LNCUPRP.COMM_DATA.TR_VAL_DATE;
        LNCSRATE.DATA.MTH = LNCUPRP.RAT_MTH;
        LNCSRATE.DATA.FLT_MTH_N = LNCUPRP.FLT_MTH;
        LNCSRATE.DATA.RATE_TYP_N = LNCUPRP.RATE_TYP;
        LNCSRATE.DATA.RATE_KIND_N = LNCUPRP.RATE_TYP.charAt(0);
        LNCSRATE.DATA.RATEPERD_N = LNCUPRP.RATE_PD;
        LNCSRATE.DATA.RATE_VAR_N = LNCUPRP.RATE_VAR;
        LNCSRATE.DATA.RATE_PCT_N = LNCUPRP.RATE_PCT;
        LNCSRATE.DATA.RATE_INT_N = LNCUPRP.RATE_INT;
        LNCSRATE.DATA.INT_RAT_N = LNCUPRP.INT_RATE;
        LNCSRATE.DATA.PEN_RRAT_O = LNCUPRP.PEN_RRAT;
        LNCSRATE.DATA.PEN_TYP_O = LNCUPRP.PEN_TYP;
        LNCSRATE.DATA.PEN_RATT_O = LNCUPRP.PEN_RATT;
        LNCSRATE.DATA.RATE_KIND_N = LNCUPRP.PEN_RATT.charAt(0);
        LNCSRATE.DATA.PEN_RATC_O = LNCUPRP.PEN_RATC;
        LNCSRATE.DATA.PEN_SPR_O = LNCUPRP.PEN_SPR;
        LNCSRATE.DATA.PEN_PCT_O = LNCUPRP.PEN_PCT;
        LNCSRATE.DATA.INT_RAT_O = LNCUPRP.PEN_IRAT;
        LNCSRATE.DATA.INT_MTH = LNCUPRP.INT_MTH;
        LNCSRATE.DATA.CPND_USE = LNCUPRP.CPND_USE;
        LNCSRATE.DATA.CPND_RTY_L = LNCUPRP.CPND_RTY;
        LNCSRATE.DATA.CPND_TYP_L = LNCUPRP.CPND_TYP;
        LNCSRATE.DATA.CPNDRATT_L = LNCUPRP.CPNDRATT;
        LNCSRATE.DATA.RATE_KIND_N = LNCUPRP.CPNDRATT.charAt(0);
        LNCSRATE.DATA.CPNDRATC_L = LNCUPRP.CPNDRATC;
        LNCSRATE.DATA.CPND_SPR_L = LNCUPRP.CPND_SPR;
        LNCSRATE.DATA.CPND_PCT_L = LNCUPRP.CPND_PCT;
        LNCSRATE.DATA.INT_RAT_L = LNCUPRP.CPNDRATE;
        LNCSRATE.DATA.ABUS_RAT_P = LNCUPRP.ABUS_RAT;
        LNCSRATE.DATA.ABUS_TYP_P = LNCUPRP.ABUS_TYP;
        LNCSRATE.DATA.ABUSRATT_P = LNCUPRP.ABUSRATT;
        LNCSRATE.DATA.RATE_KIND_N = LNCUPRP.ABUSRATT.charAt(0);
        LNCSRATE.DATA.ABUSRATC_P = LNCUPRP.ABUSRATC;
        LNCSRATE.DATA.ABUSSPR_P = LNCUPRP.ABUSSPR;
        LNCSRATE.DATA.ABUSPCT_P = LNCUPRP.ABUSPCT;
        LNCSRATE.DATA.INT_RAT_P = LNCUPRP.ABUSIRAT;
        LNCSRATE.FUNC_TYP = 'A';
        S000_CALL_LNZSRATE();
        if (pgmRtn) return;
    }
    public void B350_REUTRN_ADJ_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRDISC);
        IBS.init(SCCGWA, LNCRDISC);
        LNRDISC.KEY.CONTRACT_NO = LNCUPRP.COMM_DATA.LN_AC;
        LNCRDISC.FUNC = 'R';
        S000_CALL_LNZRDISC();
        if (pgmRtn) return;
        LNRDISC.INT_REV += LNCUPRP.COMM_DATA.RDI_AMT;
        LNCRDISC.FUNC = 'U';
        S000_CALL_LNZRDISC();
        if (pgmRtn) return;
    }
    public void B360_PLPI_ATUO_ADJUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLAJ);
        LNCPLAJ.COMM_DATA.ADJ_IND = 'P';
        LNCPLAJ.COMM_DATA.LN_AC = LNCUPRP.COMM_DATA.LN_AC;
        LNCPLAJ.COMM_DATA.SUF_NO = LNCUPRP.COMM_DATA.SUF_NO;
        LNCPLAJ.COMM_DATA.TR_VAL_DATE = LNCUPRP.COMM_DATA.TR_VAL_DATE;
        LNCPLAJ.COMM_DATA.PAY_P_AMT = LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT;
        LNCPLAJ.COMM_DATA.SYN_FLG = LNCUPRP.COMM_DATA.SYN_FLG;
