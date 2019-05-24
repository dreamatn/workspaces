package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class LNZ2281 {
    boolean pgmRtn = false;
    String K_CCY = "156";
    String WS_ACCOUNT_AC = " ";
    String WS_THR_AC = " ";
    double WS_TX_AMT = 0;
    int WS_LENGTH = 0;
    LNZ2281_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZ2281_WS_TEMP_VARIABLE();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    AICUUPIA AICUUPIA = new AICUUPIA();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRFUND LNRFUND = new LNRFUND();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCI2281 LNCI2281;
    public void MP(SCCGWA SCCGWA, LNCI2281 LNCI2281) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCI2281 = LNCI2281;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        A100_CHECK_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZ2281 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCI2281 = new LNCI2281();
        IBS.init(SCCGWA, LNCI2281);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, LNCI2281);
    }
    public void A100_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (LNCI2281.PROJ_NO == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROJ_NO_M_INPUT, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, LNRTRAN);
            LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            LNRTRAN.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            LNRTRAN.KEY.REC_FLAG = 'B';
            LNRTRAN.KEY.TXN_TYP = 'T';
            T000_READ_LNTTRAN();
            if (pgmRtn) return;
            if (LNRTRAN.P_AMT != LNCI2281.P_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CAN_AMT_NM, WS_TEMP_VARIABLE.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNRTRAN.I_AMT != LNCI2281.I_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CAN_AMT_NM, WS_TEMP_VARIABLE.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        LNRFUND.KEY.PROJ_NO = LNCI2281.PROJ_NO;
        T000_READUP_LNTFUND();
        if (pgmRtn) return;
        B100_CAP_MERGE_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNRFUND.TOT_PAY_P = LNRFUND.TOT_PAY_P + LNCI2281.P_AMT;
            LNRFUND.TOT_PAY_I = LNRFUND.TOT_PAY_I + LNCI2281.I_AMT;
        } else {
            LNRFUND.TOT_PAY_P = LNRFUND.TOT_PAY_P - LNCI2281.P_AMT;
            LNRFUND.TOT_PAY_I = LNRFUND.TOT_PAY_I - LNCI2281.I_AMT;
        }
        T000_REWRITE_LNTFUND();
        if (pgmRtn) return;
        B500_TRAN_ADD_PROC();
        if (pgmRtn) return;
    }
    public void B100_CAP_MERGE_PROC() throws IOException,SQLException,Exception {
        if (LNCI2281.P_AMT > 0) {
            WS_ACCOUNT_AC = LNCI2281.P_G_AC;
            WS_THR_AC = LNCI2281.P_AC;
            WS_TX_AMT = LNCI2281.P_AMT;
            B400_AI_IN_AC_D();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = LNCI2281.P_AC;
            WS_THR_AC = LNCI2281.P_G_AC;
            WS_TX_AMT = LNCI2281.P_AMT;
            B200_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
        }
        if (LNCI2281.I_AMT > 0) {
            WS_ACCOUNT_AC = LNCI2281.I_G_AC;
            WS_THR_AC = LNCI2281.I_AC;
            WS_TX_AMT = LNCI2281.I_AMT;
            B400_AI_IN_AC_D();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = LNCI2281.I_AC;
            WS_THR_AC = LNCI2281.I_G_AC;
            WS_TX_AMT = LNCI2281.I_AMT;
            B200_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_ROUTE_AND_ACCOUNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = WS_ACCOUNT_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            B400_IB_AC_C();
            if (pgmRtn) return;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")
            || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            B400_DD_AC_C();
            if (pgmRtn) return;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            B400_AI_IN_AC_C();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B400_AI_IN_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_ACCOUNT_AC;
        AICUUPIA.DATA.CCY = K_CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B400_IB_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ACT_CTR = LNRFUND.BOOK_BR;
        IBCPOSTA.NOSTRO_CD = WS_ACCOUNT_AC;
        IBCPOSTA.CCY = K_CCY;
        IBCPOSTA.AMT = WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.OUR_REF = WS_THR_AC;
        IBCPOSTA.THR_REF = WS_THR_AC;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B400_DD_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.CARD_NO = WS_ACCOUNT_AC;
        DDCUCRAC.CCY = K_CCY;
        DDCUCRAC.TX_AMT = WS_TX_AMT;
        DDCUCRAC.OTHER_AC = WS_THR_AC;
        DDCUCRAC.OTHER_CCY = K_CCY;
        DDCUCRAC.OTHER_AMT = WS_TX_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B400_DD_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_AMT = WS_TX_AMT;
        DDCUDRAC.CARD_NO = WS_ACCOUNT_AC;
        DDCUDRAC.CCY = K_CCY;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.OTHER_AC = WS_THR_AC;
        DDCUDRAC.OTHER_CCY = K_CCY;
        DDCUDRAC.OTHER_AMT = WS_TX_AMT;
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B400_AI_IN_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_ACCOUNT_AC;
        AICUUPIA.DATA.CCY = K_CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B400_IB_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ACT_CTR = LNRFUND.BOOK_BR;
        IBCPOSTA.NOSTRO_CD = WS_ACCOUNT_AC;
        IBCPOSTA.CCY = K_CCY;
        IBCPOSTA.AMT = WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.OUR_REF = WS_THR_AC;
        IBCPOSTA.THR_REF = WS_THR_AC;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B500_TRAN_ADD_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, LNRTRAN);
            LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            LNRTRAN.KEY.REC_FLAG = 'B';
            LNRTRAN.KEY.TXN_TYP = 'T';
            T000_READUP_LNTTRAN();
            if (pgmRtn) return;
            LNRTRAN.TRAN_STS = 'R';
            LNRTRAN.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRTRAN.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_LNTTRAN();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNRTRAN);
            LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            LNRTRAN.KEY.TXN_TYP = 'T';
            LNRTRAN.KEY.REC_FLAG = 'B';
            LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            LNRTRAN.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            LNRTRAN.TRAN_STS = 'N';
            LNRTRAN.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
            LNRTRAN.P_AMT = LNCI2281.P_AMT;
            LNRTRAN.I_AMT = LNCI2281.I_AMT;
            LNRTRAN.TXN_CCY = K_CCY;
            WS_LENGTH = 181;
