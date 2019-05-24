package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNZURPN {
    boolean pgmRtn = false;
    int K_REC_CNT = 50;
    int WS_IDX = 0;
    double WS_SS_AMT = 0;
    char LNZURPN_FILLER3 = ' ';
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    char WS_TYPE = ' ';
    short WS_TERM = 0;
    int WS_DUE_DT = 0;
    double WS_TOT_AMT = 0;
    double WS_P_AMT = 0;
    double WS_DUE_P_AMT = 0;
    double WS_I_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_F_AMT = 0;
    String WS_CTL_STSW = " ";
    char WS_SETL_STS = ' ';
    double WS_TMP_AMT = 0;
    double WS_TMP_AMT1 = 0;
    double WS_BAL_AMT = 0;
    int WS_PI_STOP_DT = 0;
    double WS_P_REC_AMT = 0;
    double WS_I_REC_AMT = 0;
    double WS_O_REC_AMT = 0;
    double WS_L_REC_AMT = 0;
    double WS_T_P_REC_AMT = 0;
    double WS_T_I_REC_AMT = 0;
    double WS_T_O_REC_AMT = 0;
    double WS_T_L_REC_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_LST_F_VALDTE = 0;
    int WS_LST_TXDTE = 0;
    double WS_CUR_RATE = 0;
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    short WS_IC_CUR_TERM1 = 0;
    char WS_REC_FLAG = ' ';
    int WS_SUBS_NO = 0;
    double WS_B_INT = 0;
    double WS_CHG_AMT = 0;
    double WS_O_LST_CAL_AMT = 0;
    double WS_O_LST_PST_AMT = 0;
    double WS_L_LST_CAL_AMT = 0;
    double WS_L_LST_PST_AMT = 0;
    int WS_NEXT_LC_CAL_DAT = 0;
    String WS_ERR_MSG = " ";
    double WS_ADJ_O_AMT = 0;
    double WS_ADJ_L_AMT = 0;
    LNZURPN_WS_ACAMT[] WS_ACAMT = new LNZURPN_WS_ACAMT[5];
    double WS_DISB_T_BFC_AMT = 0;
    int WS_DISB_T_CNT = 0;
    double WS_WOF_TOT_P_AMT = 0;
    double WS_WOF_TOT_I_AMT = 0;
    int WS_CLR_DATA_LEN = 0;
    LNZURPN_WS_CLR_DATA WS_CLR_DATA = new LNZURPN_WS_CLR_DATA();
    int WS_WOFF_RCV_DATA_LEN = 0;
    LNZURPN_WS_WOFF_RCV_DATA WS_WOFF_RCV_DATA = new LNZURPN_WS_WOFF_RCV_DATA();
    double WS_NX_I_AMT = 0;
    double WS_NX_OLC_AMT = 0;
    double WS_NX_LLC_AMT = 0;
    double WS_TX_I_AMT = 0;
    double WS_TX_OLC_AMT = 0;
    double WS_TX_LLC_AMT = 0;
    double WS_ACCU_TERM = 0;
    double WS_I_NP_AMT = 0;
    double WS_O_NP_AMT = 0;
    double WS_L_NP_AMT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_SOLD_LOAN_FLG = 'N';
    char WS_UPDATE_STS = 'N';
    char WS_RCVD_FOUND_FLG = ' ';
    char WS_TRAN_FOUND_FLG = ' ';
    char WS_DISB_FOUND_FLG = ' ';
    char WS_WOFF_END_FLG = ' ';
    char WS_AMT_CHG_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRINTA LNRINTA = new LNRINTA();
    LNRDISB LNRDISB = new LNRDISB();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNRACRU LNRACRU = new LNRACRU();
    LNCRACRU LNCRACRU = new LNCRACRU();
    int WS_CNT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    LNCURPN LNCURPN;
    public LNZURPN() {
        for (int i=0;i<5;i++) WS_ACAMT[i] = new LNZURPN_WS_ACAMT();
    }
    public void MP(SCCGWA SCCGWA, LNCURPN LNCURPN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCURPN = LNCURPN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZURPN return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCURPN.RC.RC_APP = "LN";
        LNCURPN.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_NTTX_AMT();
        if (pgmRtn) return;
        B200_READ_LNTLOAN();
        if (pgmRtn) return;
        B300_MAIN_PROC();
        if (pgmRtn) return;
        B400_CTNR_UPD_PROCESS();
        if (pgmRtn) return;
        B410_UPD_CLN_CUT_PROCESS();
        if (pgmRtn) return;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            && LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT != 0) {
            B400_CTNR_UPD_PRP_PROCESS();
            if (pgmRtn) return;
        }
        B500_TRAN_ADD_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.B_AMTS.B_I_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.B_AMTS.B_O_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.B_AMTS.B_L_AMT);
        if (LNCURPN.COMM_DATA.B_AMTS.B_I_AMT == 0 
            && LNCURPN.COMM_DATA.B_AMTS.B_O_AMT == 0 
            && LNCURPN.COMM_DATA.B_AMTS.B_L_AMT == 0) {
        } else {
            WS_SOLD_LOAN_FLG = 'Y';
        }
    }
    public void B100_GET_NTTX_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT);
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        if (LNCURPN.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCURPN.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[24-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[48-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[58-1].LOAN_BAL);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        if (LNCURPN.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCURPN.COMM_DATA.SUF_NO);
        LNCICTLM.FUNC = '3';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "SHISHIS");
            IBS.init(SCCGWA, LNCSPDQ);
            LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCURPN.COMM_DATA.LN_AC;
            LNCSPDQ.COMM_DATA.TR_VAL_DATE = LNCURPN.COMM_DATA.TR_VAL_DATE;
            S000_CALL_LNZSPDQ();
            if (pgmRtn) return;
            WS_NX_I_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT;
            WS_NX_OLC_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT;
            WS_NX_LLC_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
            CEP.TRC(SCCGWA, WS_NX_I_AMT);
            CEP.TRC(SCCGWA, WS_NX_OLC_AMT);
            CEP.TRC(SCCGWA, WS_NX_LLC_AMT);
            if (LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[24-1].LOAN_BAL <= LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL) {
                WS_NX_I_AMT = WS_NX_I_AMT;
            } else {
                if (WS_NX_I_AMT > null - null) {
                    WS_NX_I_AMT = WS_NX_I_AMT - LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[24-1].LOAN_BAL + LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL;
                } else {
                    WS_NX_I_AMT = 0;
                }
            }
            if (WS_NX_OLC_AMT > LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[48-1].LOAN_BAL) {
                WS_NX_OLC_AMT = WS_NX_OLC_AMT - LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[48-1].LOAN_BAL;
            } else {
                WS_NX_OLC_AMT = 0;
            }
            if (WS_NX_LLC_AMT > LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[58-1].LOAN_BAL) {
                WS_NX_LLC_AMT = WS_NX_LLC_AMT - LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[58-1].LOAN_BAL;
            } else {
                WS_NX_LLC_AMT = 0;
            }
            CEP.TRC(SCCGWA, WS_NX_I_AMT);
            CEP.TRC(SCCGWA, WS_NX_OLC_AMT);
            CEP.TRC(SCCGWA, WS_NX_LLC_AMT);
            if (WS_NX_I_AMT > 0) {
                if (LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT > WS_NX_I_AMT) {
                    WS_TX_I_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT - WS_NX_I_AMT;
                } else {
                    WS_NX_I_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT;
                }
            } else {
                WS_TX_I_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT;
            }
            if (WS_NX_OLC_AMT > 0) {
                if (LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT > WS_NX_OLC_AMT) {
                    WS_TX_OLC_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT - WS_NX_OLC_AMT;
                } else {
                    WS_NX_OLC_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT;
                }
            } else {
                WS_TX_OLC_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT;
            }
            if (WS_NX_LLC_AMT > 0) {
                if (LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT > WS_NX_LLC_AMT) {
                    WS_TX_LLC_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT - WS_NX_LLC_AMT;
                } else {
                    WS_NX_LLC_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT;
                }
            } else {
                WS_TX_LLC_AMT = LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT;
            }
            CEP.TRC(SCCGWA, WS_NX_I_AMT);
            CEP.TRC(SCCGWA, WS_NX_OLC_AMT);
            CEP.TRC(SCCGWA, WS_NX_LLC_AMT);
            CEP.TRC(SCCGWA, WS_TX_I_AMT);
            CEP.TRC(SCCGWA, WS_TX_OLC_AMT);
            CEP.TRC(SCCGWA, WS_TX_LLC_AMT);
        }
    }
    public void S000_CALL_LNZRACRU() throws IOException,SQLException,Exception {
        LNCRACRU.REC_PTR = LNRACRU;
        LNCRACRU.REC_LEN = 207;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTACRU", LNCRACRU);
    }
    public void B200_READ_LNTLOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
        B220_GET_LOAN_BAL();
        if (pgmRtn) return;
    }
    public void B220_GET_LOAN_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQPBAL";
        LNCCNEX.COMM_DATA.LN_AC = LNCURPN.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCURPN.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_BAL_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
    }
    public void B300_MAIN_PROC() throws IOException,SQLException,Exception {
        B310_CONT_READUPD_PROC();
        if (pgmRtn) return;
        WS_P_CUR_TERM = 0;
        WS_IC_CUR_TERM = 0;
        WS_CHG_AMT = LNCURPN.COMM_DATA.HRG_AMT;
        for (WS_J = 1; WS_J <= 5 
            && (LNCURPN.COMM_DATA.ACAMT[WS_J-1].STL_MTH2.trim().length() != 0 
            || LNCURPN.COMM_DATA.ACAMT[WS_J-1].REC_AC2.trim().length() != 0); WS_J += 1) {
            WS_ACAMT[WS_J-1].WS_AC_FLG2 = LNCURPN.COMM_DATA.ACAMT[WS_J-1].AC_FLG2;
            WS_ACAMT[WS_J-1].WS_STL_MTH2 = LNCURPN.COMM_DATA.ACAMT[WS_J-1].STL_MTH2;
            WS_ACAMT[WS_J-1].WS_REC_AC2 = LNCURPN.COMM_DATA.ACAMT[WS_J-1].REC_AC2;
            WS_ACAMT[WS_J-1].WS_PAY_AMT2 = LNCURPN.COMM_DATA.ACAMT[WS_J-1].PAY_AMT2;
        }
        B320_ICTL_READUPD_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            T000_STARTBR_LNTDISB();
            if (pgmRtn) return;
            T000_READNEXT_LNTDISB();
            if (pgmRtn) return;
            while (WS_DISB_FOUND_FLG != 'N') {
                if (LNRDISB.D_P_AMT > 0 
                    || LNRDISB.D_I_AMT > 0 
                    || LNRDISB.D_O_AMT > 0 
                    || LNRDISB.D_L_AMT > 0 
                    || LNRDISB.BFC_AMT > 0) {
                    B310_RCVD_UPD_PROCESS();
                    if (pgmRtn) return;
                    B311_TRAN_ADD_PROCESS();
                    if (pgmRtn) return;
                }
                T000_READNEXT_LNTDISB();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTDISB();
            if (pgmRtn) return;
        }
        B310_RCVD_UPD_ZERO_PROCESS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B311_RCVD_UPD_ZERO_PROCESS();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            B330_WOFF_TBL_UPD();
            if (pgmRtn) return;
        }
        B320_ICTL_UPD_PROCESS();
        if (pgmRtn) return;
    }
    public void B310_CONT_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        WS_LAST_TX_SEQ += 1;
        WS_LST_F_VALDTE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        WS_LST_TXDTE = LNCCONTM.REC_DATA.LAST_TX_DT;
        LNCCONTM.FUNC = '2';
        LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCURPN.COMM_DATA.TR_VAL_DATE;
        LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B320_ICTL_READUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        if (LNCURPN.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCURPN.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_CUR_RATE = LNCICTLM.REC_DATA.CUR_RAT;
        WS_CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        WS_IC_CUR_TERM1 = LNCICTLM.REC_DATA.IC_CUR_TERM;
        WS_NEXT_LC_CAL_DAT = LNCICTLM.REC_DATA.NEXT_LC_CAL_DAT;
    }
    public void B310_RCVD_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        if (LNCURPN.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCURPN.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNRDISB.KEY.AMT_TYP;
        LNCRCVDM.REC_DATA.KEY.TERM = LNRDISB.KEY.TERM;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = LNRDISB.KEY.SUBS_PROJ_NO;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        LNCRCVDM.FUNC = '2';
        LNCRCVDM.REC_DATA.P_PAY_AMT += LNRDISB.D_P_AMT;
        LNCRCVDM.REC_DATA.I_PAY_AMT += LNRDISB.D_I_AMT;
        WS_P_REC_AMT = LNRDISB.P_AMT;
        WS_I_REC_AMT = LNRDISB.I_AMT;
        WS_O_REC_AMT = LNRDISB.O_AMT;
        WS_L_REC_AMT = LNRDISB.L_AMT;
        WS_T_P_REC_AMT += WS_P_REC_AMT;
        WS_T_I_REC_AMT += WS_I_REC_AMT;
        WS_T_O_REC_AMT += WS_O_REC_AMT;
        WS_T_L_REC_AMT += WS_L_REC_AMT;
        WS_O_LST_CAL_AMT = LNCRCVDM.REC_DATA.O_REC_AMT;
        WS_L_LST_CAL_AMT = LNCRCVDM.REC_DATA.L_REC_AMT;
        if (LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO == 0) {
            B000_GET_ADJ_AMT();
            if (pgmRtn) return;
        }
        LNCRCVDM.REC_DATA.O_REC_AMT = LNCRCVDM.REC_DATA.O_PAY_AMT + LNCRCVDM.REC_DATA.O_WAV_AMT + LNRDISB.O_AMT - WS_ADJ_O_AMT;
        LNCRCVDM.REC_DATA.O_PAY_AMT += LNRDISB.D_O_AMT;
        LNCRCVDM.REC_DATA.L_REC_AMT = LNCRCVDM.REC_DATA.L_PAY_AMT + LNCRCVDM.REC_DATA.L_WAV_AMT + LNRDISB.L_AMT - WS_ADJ_L_AMT;
        LNCRCVDM.REC_DATA.L_PAY_AMT += LNRDISB.D_L_AMT;
        WS_F_AMT = LNCRCVDM.REC_DATA.F_REC_AMT - LNCRCVDM.REC_DATA.F_PAY_AMT;
        if (WS_CHG_AMT >= WS_F_AMT) {
            WS_CHG_AMT -= WS_F_AMT;
            LNCRCVDM.REC_DATA.F_PAY_AMT += WS_F_AMT;
        } else {
            WS_F_AMT = WS_CHG_AMT;
            LNCRCVDM.REC_DATA.F_PAY_AMT += WS_F_AMT;
            WS_CHG_AMT = 0;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DISB_T_BFC_AMT += LNRDISB.BFC_AMT;
            if (WS_F_AMT != 0 
                && LNCRCVDM.REC_DATA.F_REC_AMT == LNCRCVDM.REC_DATA.F_PAY_AMT) {
                WS_DISB_T_CNT += 1;
            }
        }
        WS_TMP_AMT = LNCRCVDM.REC_DATA.P_REC_AMT - LNCRCVDM.REC_DATA.P_PAY_AMT + LNCRCVDM.REC_DATA.I_REC_AMT - LNCRCVDM.REC_DATA.I_PAY_AMT - LNCRCVDM.REC_DATA.I_WAV_AMT + LNCRCVDM.REC_DATA.O_REC_AMT - LNCRCVDM.REC_DATA.O_PAY_AMT - LNCRCVDM.REC_DATA.O_WAV_AMT + LNCRCVDM.REC_DATA.L_REC_AMT - LNCRCVDM.REC_DATA.L_PAY_AMT - LNCRCVDM.REC_DATA.L_WAV_AMT + LNCRCVDM.REC_DATA.F_REC_AMT - LNCRCVDM.REC_DATA.F_PAY_AMT - LNCRCVDM.REC_DATA.F_WAV_AMT + WS_ADJ_O_AMT + WS_ADJ_L_AMT;
        if (WS_TMP_AMT == 0) {
            LNCRCVDM.REC_DATA.REPY_STS = '2';
            if (LNRDISB.KEY.SUBS_PROJ_NO == 0) {
                if (LNCRCVDM.REC_DATA.KEY.AMT_TYP == 'P') {
                    WS_P_CUR_TERM += 1;
                } else {
                    WS_IC_CUR_TERM += 1;
                }
            }
            WS_UPDATE_STS = 'Y';
        } else {
            LNCRCVDM.REC_DATA.REPY_STS = '1';
        }
        WS_PI_STOP_DT = LNCRCVDM.REC_DATA.PI_STOP_DT;
        LNCRCVDM.REC_DATA.PI_STOP_DT = LNCURPN.COMM_DATA.TR_VAL_DATE;
        LNCRCVDM.REC_DATA.LAST_LC_CAL_DAT = LNCURPN.COMM_DATA.TR_VAL_DATE;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
    }
    public void B311_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCURPN.COMM_DATA.LN_AC;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = LNRDISB.KEY.AMT_TYP;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNRDISB.KEY.TERM;
        LNCTRANM.REC_DATA.SUBS_NO = LNRDISB.KEY.SUBS_PROJ_NO;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
