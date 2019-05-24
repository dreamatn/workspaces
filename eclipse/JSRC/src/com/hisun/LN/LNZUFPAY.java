package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUFPAY {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_REC_CNT = 50;
    char LNZUFPAY_FILLER1 = ' ';
    double WS_TOT_AMT = 0;
    String WS_CTL_STSW = " ";
    double WS_BAL_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_LST_F_VALDTE = 0;
    int WS_LST_TXDTE = 0;
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    short WS_IC_CUR_TERM1 = 0;
    String WS_ERR_MSG = " ";
    LNZUFPAY_WS_ACAMT WS_ACAMT = new LNZUFPAY_WS_ACAMT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    CICMACR CICMACR = new CICMACR();
    SCCGWA SCCGWA;
    LNCUFPAY LNCUFPAY;
    public void MP(SCCGWA SCCGWA, LNCUFPAY LNCUFPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUFPAY = LNCUFPAY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUFPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCUFPAY.RC.RC_MMO = "LN";
        LNCUFPAY.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B310_BASE_INFO_UPD_AND_CHECK();
        if (pgmRtn) return;
        B300_REPAY_MAIN_PROC();
        if (pgmRtn) return;
        B311_TRAN_ADD_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.ACM_EVENT);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.PAY_FLG);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.TR_VAL_DATE);
        if (LNCUFPAY.DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCUFPAY.DATA.PAY_FLG != '1' 
            && LNCUFPAY.DATA.PAY_FLG != '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PRE_REP_FLG, LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCUFPAY.DATA.TR_VAL_DATE == 0) {
            LNCUFPAY.DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        WS_TOT_AMT = LNCUFPAY.DATA.TOT_D_P_AMT + LNCUFPAY.DATA.TOT_D_I_AMT + LNCUFPAY.DATA.TOT_D_O_AMT + LNCUFPAY.DATA.TOT_D_L_AMT;
        if (WS_TOT_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_AMT, LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_READ_LNTLOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCUFPAY.DATA.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
    }
    public void B220_GET_LOAN_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQPBAL";
        LNCCNEX.COMM_DATA.LN_AC = LNCUFPAY.DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCUFPAY.DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_BAL_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
    }
    public void B300_REPAY_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCUFPAY.DATA.PAY_FLG == '2') {
            B070_WRITE_LNTPYIF();
            if (pgmRtn) return;
        }
        B400_CTNR_UPD_PROCESS();
        if (pgmRtn) return;
        WS_ACAMT.WS_AC_FLG2 = LNCUFPAY.DATA.ACAMT.AC_FLG2;
        WS_ACAMT.WS_STL_MTH2 = LNCUFPAY.DATA.ACAMT.STL_MTH2;
        WS_ACAMT.WS_REC_AC2 = LNCUFPAY.DATA.ACAMT.REC_AC2;
        WS_ACAMT.WS_PAY_AMT2 = LNCUFPAY.DATA.ACAMT.PAY_AMT2;
    }
    public void B310_BASE_INFO_UPD_AND_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUFPAY.DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        WS_LAST_TX_SEQ += 1;
        WS_LST_F_VALDTE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        WS_LST_TXDTE = LNCCONTM.REC_DATA.LAST_TX_DT;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONTRACT_TYPE_MCLDD, LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "START UPDATE");
        LNCCONTM.FUNC = '2';
        LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCUFPAY.DATA.TR_VAL_DATE;
        LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LAST_F_VAL_DATE);
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        B200_READ_LNTLOAN();
        if (pgmRtn) return;
        B220_GET_LOAN_BAL();
        if (pgmRtn) return;
        WS_BAL_AMT = 30000;
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.TOT_D_P_AMT);
        CEP.TRC(SCCGWA, WS_BAL_AMT);
        if (LNCUFPAY.DATA.TOT_D_P_AMT > WS_BAL_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GE_PAY_P_AMT, LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B311_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUFPAY.DATA.LN_AC;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = LNCUFPAY.DATA.TERM;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNCUFPAY.DATA.TR_VAL_DATE;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.P_AMT = LNCUFPAY.DATA.TOT_D_P_AMT;
        LNCTRANM.REC_DATA.I_AMT = LNCUFPAY.DATA.TOT_D_I_AMT;
        LNCTRANM.REC_DATA.O_AMT = LNCUFPAY.DATA.TOT_D_O_AMT;
        LNCTRANM.REC_DATA.L_AMT = LNCUFPAY.DATA.TOT_D_L_AMT;
        WS_BAL_AMT -= LNCUFPAY.DATA.TOT_D_P_AMT;
        LNCTRANM.REC_DATA.OS_BAL = WS_BAL_AMT;
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCTRANM.REC_DATA.TXN_CCY = LNCCONTM.REC_DATA.CCY;
        LNCTRANM.REC_DATA.P_REC_AMT = LNCUFPAY.DATA.P_AMT;
        LNCTRANM.REC_DATA.I_REC_AMT = LNCUFPAY.DATA.I_AMT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LST_F_VALDTE;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.TR_MMO = LNCUFPAY.DATA.MMO;
        LNCTRANM.REC_DATA.AC_FLG1 = WS_ACAMT.WS_AC_FLG2;
        LNCTRANM.REC_DATA.AC_TYP1 = WS_ACAMT.WS_STL_MTH2;
        LNCTRANM.REC_DATA.PAY_AC1 = WS_ACAMT.WS_REC_AC2;
        LNCTRANM.REC_DATA.PAY_AMT1 = WS_TOT_AMT;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        CEP.TRC(SCCGWA, "WRITE WHOLE DETAIL");
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        if (LNCUFPAY.DATA.TOT_D_I_AMT > 0) {
            LNCTRANM.REC_DATA.KEY.REC_FLAG = 'I';
            LNCTRANM.REC_DATA.KEY.TXN_TYP = 'I';
            CEP.TRC(SCCGWA, "WRITE INT DETAIL");
            S000_CALL_LNZTRANM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "WRITE TRAN FINISHED");
    }
    public void B400_CTNR_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "REPAYPR";
        LNCCNEV.COMM_DATA.ACM_EVENT = LNCUFPAY.DATA.ACM_EVENT;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.ACM_EVENT);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.TOT_D_P_AMT);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.TOT_D_I_AMT);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.TOT_D_O_AMT);
        CEP.TRC(SCCGWA, LNCUFPAY.DATA.TOT_D_L_AMT);
        LNCCNEV.COMM_DATA.LN_AC = LNCUFPAY.DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCUFPAY.DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCUFPAY.DATA.TR_VAL_DATE;
        LNCCNEV.COMM_DATA.P_AMT = LNCUFPAY.DATA.TOT_D_P_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = LNCUFPAY.DATA.TOT_D_P_AMT;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B070_WRITE_LNTPYIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPYIFM);
        LNCPYIFM.FUNC = '0';
        LNCPYIFM.REC_DATA.KEY.CONTRACT_NO = LNCUFPAY.DATA.LN_AC;
        if (LNCUFPAY.DATA.SUF_NO.trim().length() == 0) LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUFPAY.DATA.SUF_NO);
        LNCPYIFM.REC_DATA.KEY.TERM = LNCUFPAY.DATA.TERM;
        LNCPYIFM.REC_DATA.KEY.REPY_SEQ = (short) WS_LAST_TX_SEQ;
        LNCPYIFM.REC_DATA.REPY_MTH = '2';
        LNCPYIFM.REC_DATA.CUR_REPY_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCPYIFM.REC_DATA.REPY_TOT_AMT = WS_TOT_AMT;
        LNCPYIFM.REC_DATA.REIM_PRIN_AMT = LNCUFPAY.DATA.TOT_D_P_AMT;
        LNCPYIFM.REC_DATA.REIM_INT_AMT = LNCUFPAY.DATA.TOT_D_I_AMT;
        LNCPYIFM.REC_DATA.REIM_INT_AMT += LNCUFPAY.DATA.TOT_D_O_AMT;
        LNCPYIFM.REC_DATA.REIM_INT_AMT += LNCUFPAY.DATA.TOT_D_L_AMT;
        S000_CALL_LNZPYIFM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        CEP.TRC(SCCGWA, LNCLOANM.RC);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        CEP.TRC(SCCGWA, LNCCONTM.RC);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        CEP.TRC(SCCGWA, LNCICTLM.RC);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        CEP.TRC(SCCGWA, CICMACR.RC);
        if (CICMACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        CEP.TRC(SCCGWA, LNCCNEX.RC);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        CEP.TRC(SCCGWA, LNCCNEV.RC);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEV.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        CEP.TRC(SCCGWA, LNCTRANM.RC);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPYIFM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PYIF-MAINT", LNCPYIFM);
        CEP.TRC(SCCGWA, LNCPYIFM.RC);
        if (LNCPYIFM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPYIFM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCUFPAY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCUFPAY=");
            CEP.TRC(SCCGWA, LNCUFPAY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
