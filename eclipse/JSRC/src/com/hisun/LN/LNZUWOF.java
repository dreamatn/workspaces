package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUWOF {
    int JIBS_tmp_int;
    DBParm LNTBALZ_RD;
    DBParm LNTSETL_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTWOFF_RD;
    boolean pgmRtn = false;
    char LNZUWOF_FILLER1 = ' ';
    int WS_I = 0;
    char WS_TYPE = ' ';
    short WS_TERM = 0;
    double WS_TOT_LON_BAL = 0;
    double WS_WRF_TOT_AMT = 0;
    double WS_WRF_NP_AMT = 0;
    double WS_WRF_NI_AMT = 0;
    double WS_WRF_OP_AMT = 0;
    double WS_WRF_OI_AMT = 0;
    double WS_WRF_OLC_AMT = 0;
    double WS_WRF_LLC_AMT = 0;
    int WS_IC_VAL_DT = 0;
    int WS_IC_DUE_DT = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_LST_F_VALDTE = 0;
    String WS_LOAN_STSW = " ";
    double WS_REPY_NI = 0;
    double WS_REPY_OI = 0;
    double WS_REPY_O = 0;
    double WS_REPY_L = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNRSETL LNRSETL = new LNRSETL();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    CICMACR CICMACR = new CICMACR();
    LNRWOFF LNRWOFF = new LNRWOFF();
    CICSACR CICSACR = new CICSACR();
    SCCGWA SCCGWA;
    LNCUWOF LNCUWOF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCUWOF LNCUWOF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUWOF = LNCUWOF;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUWOF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCUWOF.RC.RC_APP = "LN";
        LNCUWOF.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_GET_LOAN_INF();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B300_MAIN_PROC_REV();
            if (pgmRtn) return;
            B400_CTNR_UPD_PROCESS();
            if (pgmRtn) return;
            if (LNCUWOF.COMM_DATA.WROF_TP == '2' 
                && SCCGWA.COMM_AREA.AC_DATE != LNCTRANM.REC_DATA.TR_VAL_DTE 
                && LNCUWOF.COMM_DATA.ATTR_FLG == '1') {
                B410_ACCRUAL_SWITCH();
                if (pgmRtn) return;
            }
            B500_TRAN_MOD_PROCESS();
            if (pgmRtn) return;
        } else {
            B300_MAIN_PROC();
            if (pgmRtn) return;
            B400_CTNR_UPD_PROCESS();
            if (pgmRtn) return;
            B500_TRAN_ADD_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCUWOF.COMM_DATA.SUF_NO.equalsIgnoreCase("0")) {
            if (LNCUWOF.COMM_DATA.TR_VAL_DATE < LNCCONTM.REC_DATA.LAST_F_VAL_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_LSTDT, LNCUWOF.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_LST_F_VALDTE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        }
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '3';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B300_MAIN_PROC() throws IOException,SQLException,Exception {
        B330_CONT_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B300_MAIN_PROC_REV() throws IOException,SQLException,Exception {
        B010_READ_LNTTRAN();
        if (pgmRtn) return;
        if (LNCTRANM.REC_DATA.TRAN_STS == 'R') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_CANCEL_REV, LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_READ_LNTCONT();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.LAST_TX_SEQ != LNCTRANM.REC_DATA.TR_SEQ) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCUWOF.COMM_DATA.LOAN_STSW = LNCTRANM.REC_DATA.LOAN_STSW;
        IBS.init(SCCGWA, LNRWOFF);
        LNRWOFF.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        LNRWOFF.KEY.TYPE = LNCUWOF.COMM_DATA.WROF_TP;
        T000_READUPD_LNTWOFF();
        if (pgmRtn) return;
        WS_WRF_NI_AMT = LNRWOFF.WOF_NI_AMT;
        WS_WRF_OI_AMT = LNRWOFF.WOF_OI_AMT;
        WS_WRF_OLC_AMT = LNRWOFF.WOF_O_AMT;
        WS_WRF_LLC_AMT = LNRWOFF.WOF_L_AMT;
        if (LNCTRANM.REC_DATA.LOAN_STSW == null) LNCTRANM.REC_DATA.LOAN_STSW = "";
        JIBS_tmp_int = LNCTRANM.REC_DATA.LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCTRANM.REC_DATA.LOAN_STSW += " ";
        if (LNCUWOF.COMM_DATA.WROF_TP == '1' 
            && LNCTRANM.REC_DATA.LOAN_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            LNRWOFF.KEY.TYPE = '1';
            LNRWOFF.ATTR_FLG = '1';
            LNRWOFF.WOF_NP_AMT -= LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NP_AMT;
            LNRWOFF.WOF_OP_AMT -= LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OP_AMT;
            LNRWOFF.WOF_NI_AMT -= LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NI_AMT;
            LNRWOFF.WOF_OI_AMT -= LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OI_AMT;
            LNRWOFF.WOF_O_AMT -= LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OLC_AMT;
            LNRWOFF.WOF_L_AMT -= LNCUWOF.COMM_DATA.TOT_AMTS.WRF_LLC_AMT;
            LNRWOFF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRWOFF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_LNTWOFF();
            if (pgmRtn) return;
        } else {
            T000_DELETE_LNTWOFF();
            if (pgmRtn) return;
        }
        B330_CONT_REVUPD_PROC();
        if (pgmRtn) return;
    }
    public void B330_CONT_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) {
            LNCCONTM.FUNC = '4';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
            WS_LAST_TX_SEQ += 1;
            LNCCONTM.FUNC = '2';
            LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCUWOF.COMM_DATA.TR_VAL_DATE;
            LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
            if (LNCUWOF.COMM_DATA.WROF_TP == '2' 
                && LNCUWOF.COMM_DATA.ATTR_FLG == '2') {
                LNCCONTM.REC_DATA.CTA_STS = '4';
            }
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        } else {
            LNCCONTM.FUNC = '3';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCUWOF.COMM_DATA.WROF_TP == '1') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 9 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(9 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 17 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(17 + 1 - 1);
        }
        if (LNCUWOF.COMM_DATA.WROF_TP == '2') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 8 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(8 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 9 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(9 + 1 - 1);
            if (LNCUWOF.COMM_DATA.ATTR_FLG == '2') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 10 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(10 + 1 - 1);
            }
        }
        B020_READ_LNTCONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            && SCCGWA.COMM_AREA.AC_DATE < LNCCONTM.REC_DATA.MAT_DATE) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 22 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(22 + 1 - 1);
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B330_CONT_REVUPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) {
            LNCCONTM.FUNC = '4';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
            WS_LAST_TX_SEQ -= 1;
            LNCCONTM.FUNC = '2';
            LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
            LNCCONTM.REC_DATA.CTA_STS = '0';
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        } else {
            LNCCONTM.FUNC = '3';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        LNCICTLM.REC_DATA.CTL_STSW = LNCTRANM.REC_DATA.LOAN_STSW;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B400_CTNR_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "WRITOFF";
        LNCCNEV.COMM_DATA.ACM_EVENT = "WF";
        LNCCNEV.COMM_DATA.LN_AC = LNCUWOF.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCUWOF.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCUWOF.COMM_DATA.TR_VAL_DATE;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NP_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OP_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NI_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OI_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OLC_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_LLC_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNCCNEV.COMM_DATA.RVS_IND = 'Y';
        } else {
            LNCCNEV.COMM_DATA.RVS_IND = 'N';
        }
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B500_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        LNRSETL.KEY.SETTLE_TYPE = '2';
        T000_READ_LNTSETL();
        if (pgmRtn) return;
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNCUWOF.COMM_DATA.TR_VAL_DATE;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.PAY_AC1 = LNRSETL.AC;
        LNCTRANM.REC_DATA.P_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NP_AMT;
        LNCTRANM.REC_DATA.I_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NI_AMT;
        LNCTRANM.REC_DATA.P_AMT += LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OP_AMT;
        LNCTRANM.REC_DATA.I_AMT += LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OI_AMT;
        LNCTRANM.REC_DATA.O_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OLC_AMT;
        LNCTRANM.REC_DATA.L_AMT = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_LLC_AMT;
        LNCTRANM.REC_DATA.ALL_IN_RATE = LNCICTLM.REC_DATA.CUR_RAT;
        LNCTRANM.REC_DATA.OS_BAL = LNCUWOF.COMM_DATA.TOT_AMTS.WRF_NP_AMT + LNCUWOF.COMM_DATA.TOT_AMTS.WRF_OP_AMT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LST_F_VALDTE;
        LNCTRANM.REC_DATA.TXN_CCY = LNCCONTM.REC_DATA.CCY;
        LNCTRANM.REC_DATA.LOAN_STSW = LNCUWOF.COMM_DATA.LOAN_STSW;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCUWOF.COMM_DATA.WROF_TP == '2' 
            && LNCUWOF.COMM_DATA.ATTR_FLG == '2') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 3 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(3 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 4 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(4 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 5 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(5 + 1 - 1);
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNCRAGRE);
            IBS.init(SCCGWA, LNRAGRE);
            LNCRAGRE.FUNC = 'I';
            LNRAGRE.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZRAGRE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICSACR);
            CICSACR.FUNC = 'D';
            CICSACR.DATA.ENTY_TYP = '1';
            CICSACR.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            S000_CALL_CIZSACR();
            if (pgmRtn) return;
        }
    }
    public void B500_TRAN_MOD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '4';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCTRANM.FUNC = '2';
        LNCTRANM.REC_DATA.TRAN_STS = 'R';
        LNCTRANM.REC_DATA.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        if (LNCUWOF.COMM_DATA.WROF_TP == '2' 
            && LNCUWOF.COMM_DATA.ATTR_FLG == '2') {
            IBS.init(SCCGWA, LNCRAGRE);
            IBS.init(SCCGWA, LNRAGRE);
            LNCRAGRE.FUNC = 'I';
            LNRAGRE.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
            S000_CALL_LNZRAGRE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICSACR);
            CICSACR.FUNC = 'D';
            CICSACR.DATA.ENTY_TYP = '1';
            CICSACR.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            S000_CALL_CIZSACR();
            if (pgmRtn) return;
        }
    }
    public void B410_ACCRUAL_SWITCH() throws IOException,SQLException,Exception {
        R010_READUPD_LNTBALZ();
        if (pgmRtn) return;
        WS_REPY_NI = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 - WS_WRF_NI_AMT;
        WS_REPY_OI = LNRBALZ.LOAN_BALL22 + LNRBALZ.LOAN_BALL42 - WS_WRF_OI_AMT - WS_WRF_OLC_AMT;
        WS_REPY_L = LNRBALZ.LOAN_BALL52 - WS_WRF_LLC_AMT;
        if (WS_LOAN_STSW == null) WS_LOAN_STSW = "";
        JIBS_tmp_int = WS_LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_LOAN_STSW += " ";
        if (WS_LOAN_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0")) {
            LNRBALZ.LOAN_BALL91 = WS_REPY_NI;
            LNRBALZ.LOAN_BALL92 = WS_REPY_OI;
            LNRBALZ.LOAN_BALL93 = WS_REPY_L;
        }
        if (WS_LOAN_STSW == null) WS_LOAN_STSW = "";
        JIBS_tmp_int = WS_LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_LOAN_STSW += " ";
        if (WS_LOAN_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            LNRBALZ.LOAN_BALL93 = WS_REPY_NI;
            LNRBALZ.LOAN_BALL93 += WS_REPY_OI;
            LNRBALZ.LOAN_BALL93 += WS_REPY_L;
        }
        R000_ICTL_UPDATE();
        if (pgmRtn) return;
        R020_REWRITE_LNTBALZ();
        if (pgmRtn) return;
    }
    public void R000_ICTL_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 49 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(49 + 1 - 1);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void R010_READUPD_LNTBALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND, LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R020_REWRITE_LNTBALZ() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void B010_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '3';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        if (LNCUWOF.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUWOF.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        WS_LOAN_STSW = LNCTRANM.REC_DATA.LOAN_STSW;
    }
    public void B020_READ_LNTCONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUWOF.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCUWOF.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCUWOF.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            LNCUWOF.RC.RC_APP = LNCLOANM.RC.RC_APP;
            LNCUWOF.RC.RC_RTNCODE = LNCLOANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCUWOF.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCUWOF.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            LNCUWOF.RC.RC_APP = LNCTRANM.RC.RC_APP;
            LNCUWOF.RC.RC_RTNCODE = LNCTRANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCUWOF.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCUWOF.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            LNCUWOF.RC.RC_APP = LNCAPRDM.RC.RC_APP;
            LNCUWOF.RC.RC_RTNCODE = LNCAPRDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_LNTWOFF() throws IOException,SQLException,Exception {
        LNTWOFF_RD = new DBParm();
        LNTWOFF_RD.TableName = "LNTWOFF";
        LNTWOFF_RD.upd = true;
        IBS.READ(SCCGWA, LNRWOFF, LNTWOFF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_WOFF_NOTFND, LNCUWOF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTWOFF() throws IOException,SQLException,Exception {
        LNTWOFF_RD = new DBParm();
        LNTWOFF_RD.TableName = "LNTWOFF";
        IBS.REWRITE(SCCGWA, LNRWOFF, LNTWOFF_RD);
    }
    public void T000_DELETE_LNTWOFF() throws IOException,SQLException,Exception {
        LNTWOFF_RD = new DBParm();
        LNTWOFF_RD.TableName = "LNTWOFF";
        IBS.DELETE(SCCGWA, LNRWOFF, LNTWOFF_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCUWOF.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCUWOF=");
            CEP.TRC(SCCGWA, LNCUWOF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
