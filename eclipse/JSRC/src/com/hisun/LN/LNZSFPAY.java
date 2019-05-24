package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.IB.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSFPAY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_LN_CLDD = "CLDD";
    String K_LN_CLDL = "CLDL";
    String K_LN_CLGU = "CLGU";
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    double WS_TOT_AMT = 0;
    double WS_PAY_AMT = 0;
    double WS_KOU_KUAN_AMT = 0;
    String WS_RTE_AC_TYP = " ";
    String WS_IA_CR_CCY = " ";
    int[] WS_ACR_BBR = new int[20];
    int WS_BBR_I = 0;
    String WS_CR_TX_AC_TYP = " ";
    String WS_CR_TX_AC = " ";
    double WS_CR_TX_AMT = 0;
    String WS_DR_TX_AC_TYP = " ";
    String WS_DR_TX_AC = " ";
    double WS_DR_TX_AMT = 0;
    char WS_AC_FLG = ' ';
    String WS_AC = " ";
    String WS_AC_CCY = " ";
    double WS_PQMIB_CBAL = 0;
    String WS_P_TMP_AC = " ";
    String WS_I_TMP_AC = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    AICUUPIA AICUUPIA = new AICUUPIA();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    LNCUFPAY LNCUFPAY = new LNCUFPAY();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICPQIA AICPQIA = new AICPQIA();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCUABOX BPCUABOX = new BPCUABOX();
    DDCUCRDD DDCUCRDD = new DDCUCRDD();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    SCCBATH SCCBATH;
    LNCSFPAY LNCSFPAY;
    public void MP(SCCGWA SCCGWA, LNCSFPAY LNCSFPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSFPAY = LNCSFPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSFPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSFPAY.RC.RC_MMO = "LN";
        LNCSFPAY.RC.RC_CODE = 0;
        WS_BBR_I = 1;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_INPUT_VAILIDATION();
        if (pgmRtn) return;
        B100_MAIN_PROCESS();
        if (pgmRtn) return;
        B200_BP_HISTORY_GEN();
        if (pgmRtn) return;
    }
    public void B000_INPUT_VAILIDATION() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        B010_INQ_LOAN_INF();
        if (pgmRtn) return;
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B110_AMT_DEBIT_PROC();
        if (pgmRtn) return;
        B112_FUND_LOAN_PROC();
        if (pgmRtn) return;
        B113_CHK_FTA_TYP();
        if (pgmRtn) return;
        B114_REPAY_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSFPAY.DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSFPAY.DATA.TR_VAL_DATE == 0) {
            LNCSFPAY.DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (LNCSFPAY.DATA.REPAY_FLG != '1' 
            && LNCSFPAY.DATA.REPAY_FLG != '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PRE_REP_FLG, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TOT_AMT = LNCSFPAY.DATA.TOT_P_AMT + LNCSFPAY.DATA.TOT_I_AMT + LNCSFPAY.DATA.TOT_O_AMT + LNCSFPAY.DATA.TOT_L_AMT;
        if (WS_TOT_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_AMT, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_INQ_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSFPAY.DATA.LN_AC;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = LNCCLNQ.DATA.BOOK_BR;
        WS_BBR_I += 1;
        WS_ACR_BBR[WS_BBR_I-1] = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_BBR_I += 1;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CTL_STSW);
        if (LNCCLNQ.DATA.CTL_STSW == null) LNCCLNQ.DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCCLNQ.DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCCLNQ.DATA.CTL_STSW += " ";
        if (LNCCLNQ.DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_CLOSE, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSFPAY.DATA.TR_VAL_DATE);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.LAST_F_VAL_DATE);
        if (LNCSFPAY.DATA.TR_VAL_DATE < LNCCLNQ.DATA.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_LSTDT, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B101_GET_AC_INFO() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB")) {
            WS_RTE_AC_TYP = K_IB_AC;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            WS_RTE_AC_TYP = K_DD_AC;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            WS_RTE_AC_TYP = K_DC_AC;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("AI")) {
            WS_RTE_AC_TYP = K_INTERNAL;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NOEXIST_AC_TYP, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B110_AMT_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_PAY_AMT = WS_TOT_AMT;
        B111_AC_ROUTER_PROC();
        if (pgmRtn) return;
        if (WS_PAY_AMT != 0) {
            WS_KOU_KUAN_AMT = WS_PAY_AMT;
            if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("01) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")
                || LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("07) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")
                || LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("05) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")
                || LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("06) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
                B052_DD_DEBIT_PROC();
                if (pgmRtn) return;
            } else if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("02) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
                B052_GLSUS_DEBIT_PROC();
                if (pgmRtn) return;
            } else if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("03) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
                B052_NOSCD_DEBIT_PROC();
                if (pgmRtn) return;
            } else if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("04) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
                B052_GM_DEBIT_PROC();
                if (pgmRtn) return;
            } else if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("08) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
                B052_CASH_DEBIT_PROC();
                if (pgmRtn) return;
            } else {
            }
            CEP.TRC(SCCGWA, WS_KOU_KUAN_AMT);
        }
        if (WS_KOU_KUAN_AMT != WS_TOT_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_BAL_NOT_ENOUGH, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B111_AC_ROUTER_PROC() throws IOException,SQLException,Exception {
        if (LNCSFPAY.DATA.ACAMT.AC_FLG == ' ') {
            LNCSFPAY.DATA.ACAMT.AC_FLG = '0';
        }
        if (LNCSFPAY.DATA.ACAMT.AC_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_THEIR_FLG, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_AC_FLG = LNCSFPAY.DATA.ACAMT.AC_FLG;
        if (LNCSFPAY.DATA.ACAMT.REC_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_AC_M_I, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_AC_FLG == '0') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = LNCSFPAY.DATA.ACAMT.REC_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACAC.O_DATA.O_ACR_DATA.O_OWNER_BK_ACR;
            WS_BBR_I += 1;
            B101_GET_AC_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSFPAY.DATA.ACAMT.STL_MTH);
            CEP.TRC(SCCGWA, WS_RTE_AC_TYP);
            if (LNCSFPAY.DATA.ACAMT.STL_MTH.trim().length() > 0 
                && !LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(WS_RTE_AC_TYP)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REC_AC_TYP, LNCSFPAY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCSFPAY.DATA.ACAMT.STL_MTH = WS_RTE_AC_TYP;
            CEP.TRC(SCCGWA, LNCSFPAY.DATA.ACAMT.REC_CCY);
            if (LNCSFPAY.DATA.ACAMT.REC_CCY.trim().length() == 0) {
                LNCSFPAY.DATA.ACAMT.REC_CCY = CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR;
            }
        }
    }
    public void B112_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'D';
        AICPQIA.CD.BUSI_KND = "LNPYSUS";
        AICPQIA.BR = LNCCLNQ.DATA.BOOK_BR;
        AICPQIA.CCY = LNCCLNQ.DATA.CCY;
        AICPQIA.CD.AC_TYP = "3";
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        LNCSFPAY.DATA.ACAMT.STL_MTH = "02";
        LNCSFPAY.DATA.ACAMT.REC_AC = AICPQIA.AC;
        LNCSFPAY.DATA.ACAMT.REC_CCY = LNCCLNQ.DATA.CCY;
        CEP.TRC(SCCGWA, AICPQIA.AC);
    }
    public void B113_CHK_FTA_TYP() throws IOException,SQLException,Exception {
        if (WS_BBR_I > 21) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FTA_BR_GREATER_20, LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B042_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_AC;
        AICPQMIB.INPUT_DATA.CCY = WS_AC_CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.BAL_RFLG);
        if (AICPQMIB.OUTPUT_DATA.CBAL < 0) {
            WS_PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL * ( -1 );
        } else {
            WS_PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL;
        }
    }
    public void B052_DD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'Y';
        DDCUDRAC.TSTS_TABL = "0014";
        DDCUDRAC.BV_TYP = '3';
        if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("05) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(") 
            || LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("06) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
            DDCUDRAC.CARD_NO = LNCSFPAY.DATA.ACAMT.REC_AC;
        } else {
            DDCUDRAC.AC = LNCSFPAY.DATA.ACAMT.REC_AC;
        }
        DDCUDRAC.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        DDCUDRAC.TX_AMT = WS_KOU_KUAN_AMT;
        DDCUDRAC.VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        DDCUDRAC.TX_MMO = LNCSFPAY.DATA.MMO;
        DDCUDRAC.RLT_AC = LNCSFPAY.DATA.LN_AC;
        DDCUDRAC.OTHER_CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        DDCUDRAC.OTHER_AMT = LNCSFPAY.DATA.ACAMT.PAY_AMT;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        WS_KOU_KUAN_AMT = DDCUDRAC.TX_AMT;
    }
    public void B052_GLSUS_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_AC = LNCSFPAY.DATA.ACAMT.REC_AC;
        WS_AC_CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        B042_GET_SUSP_AC_AVABAL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = LNCSFPAY.DATA.ACAMT.REC_AC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        if (AICPQMIB.OUTPUT_DATA.BAL_RFLG == 'N' 
            && WS_PQMIB_CBAL < WS_KOU_KUAN_AMT) {
            AICUUPIA.DATA.AMT = WS_PQMIB_CBAL;
        } else {
            AICUUPIA.DATA.AMT = WS_KOU_KUAN_AMT;
        }
        AICUUPIA.DATA.VALUE_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = AICUUPIA.DATA.AMT;
    }
    public void B052_NOSCD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = LNCSFPAY.DATA.ACAMT.REC_AC;
        IBCPOSTA.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        IBCPOSTA.AMT = WS_KOU_KUAN_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.OUR_REF = LNCSFPAY.DATA.LN_AC;
        IBCPOSTA.THR_REF = LNCSFPAY.DATA.LN_AC;
        IBCPOSTA.TX_MMO = LNCSFPAY.DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = IBCPOSTA.AMT;
    }
    public void B052_GM_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_GDA_REY, LNCSFPAY.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void B052_CASH_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        BPCUABOX.CCY_TYP = '0';
        BPCUABOX.AMT = WS_KOU_KUAN_AMT;
        BPCUABOX.OPP_AC = LNCSFPAY.DATA.LN_AC;
        BPCUABOX.CASH_NO = "" + 117;
        JIBS_tmp_int = BPCUABOX.CASH_NO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCUABOX.CASH_NO = "0" + BPCUABOX.CASH_NO;
        BPCUABOX.RMK = "LOAN PRE-REPAYMENT";
        if (BPCUABOX.AMT != 0) {
            S000_CALL_BPZUABOX();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = BPCUABOX.AMT;
    }
    public void B112_FUND_LOAN_PROC() throws IOException,SQLException,Exception {
        B451_GET_FUND_SETL_INF();
        if (pgmRtn) return;
        B452_FUND_P_SETL_PROCESS();
        if (pgmRtn) return;
        B453_FUND_IOL_SETL_PROCESS();
        if (pgmRtn) return;
        B454_FUND_AMT_PROCESS();
        if (pgmRtn) return;
    }
    public void B451_GET_FUND_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNRFUND.PAY_FLG == 'Y') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.SIGN = 'C';
            AICPQIA.CD.BUSI_KND = "LN01";
            AICPQIA.BR = LNRFUND.BOOK_BR;
            AICPQIA.CCY = LNCCLNQ.DATA.CCY;
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            WS_P_TMP_AC = AICPQIA.AC;
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.SIGN = 'C';
            AICPQIA.CD.BUSI_KND = "LN02";
            AICPQIA.BR = LNRFUND.BOOK_BR;
            AICPQIA.CCY = LNCCLNQ.DATA.CCY;
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            WS_I_TMP_AC = AICPQIA.AC;
            WS_ACR_BBR[WS_BBR_I-1] = LNRFUND.BOOK_BR;
            WS_BBR_I += 1;
        }
    }
    public void B452_FUND_P_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (LNRFUND.PAY_FLG == 'Y') {
            WS_CR_TX_AC_TYP = K_INTERNAL;
            WS_CR_TX_AC = WS_P_TMP_AC;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = LNRFUND.PAY_P_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACAC.O_DATA.O_ACR_DATA.O_OWNER_BK_ACR;
            WS_BBR_I += 1;
            B101_GET_AC_INFO();
            if (pgmRtn) return;
            WS_CR_TX_AC_TYP = WS_RTE_AC_TYP;
            WS_CR_TX_AC = LNRFUND.PAY_P_AC;
        }
        WS_CR_TX_AMT = LNCSFPAY.DATA.TOT_P_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B453_FUND_IOL_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (LNRFUND.PAY_FLG == 'Y') {
            WS_CR_TX_AC_TYP = K_INTERNAL;
            WS_CR_TX_AC = WS_I_TMP_AC;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = LNRFUND.PAY_I_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACAC.O_DATA.O_ACR_DATA.O_OWNER_BK_ACR;
            WS_BBR_I += 1;
            B101_GET_AC_INFO();
            if (pgmRtn) return;
            WS_CR_TX_AC_TYP = WS_RTE_AC_TYP;
            WS_CR_TX_AC = LNRFUND.PAY_I_AC;
        }
        WS_CR_TX_AMT = LNCSFPAY.DATA.TOT_I_AMT + LNCSFPAY.DATA.TOT_O_AMT + LNCSFPAY.DATA.TOT_L_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B454_FUND_AMT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = LNRFUND.FUND_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACAC.O_DATA.O_ACR_DATA.O_OWNER_BK_ACR;
        WS_BBR_I += 1;
        IBS.init(SCCGWA, DDCUCRDD);
        DDCUCRDD.AC = LNRFUND.FUND_AC;
        DDCUCRDD.TX_AMT = LNCSFPAY.DATA.TOT_P_AMT;
        DDCUCRDD.CCY = CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR;
        DDCUCRDD.RLT_AC = LNCSFPAY.DATA.LN_AC;
        S000_CALL_DDZUCRDD();
        if (pgmRtn) return;
        B101_GET_AC_INFO();
        if (pgmRtn) return;
        WS_DR_TX_AC_TYP = WS_RTE_AC_TYP;
        WS_DR_TX_AC = LNRFUND.FUND_AC;
        WS_DR_TX_AMT = LNCSFPAY.DATA.TOT_P_AMT;
        B096_TX_AMT_DR_PROC();
        if (pgmRtn) return;
        if (LNRFUND.PAY_FLG == 'Y') {
            IBS.init(SCCGWA, LNRFUND);
            LNCRFUND.FUNC = 'R';
            LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
            S000_CALL_LNZRFUND();
            if (pgmRtn) return;
            LNRFUND.TOT_PAY_P += LNCSFPAY.DATA.TOT_P_AMT;
            LNRFUND.TOT_PAY_I += LNCSFPAY.DATA.TOT_I_AMT;
            LNRFUND.TOT_PAY_I += LNCSFPAY.DATA.TOT_O_AMT;
            LNRFUND.TOT_PAY_I += LNCSFPAY.DATA.TOT_L_AMT;
            LNCRFUND.FUNC = 'U';
            S000_CALL_LNZRFUND();
            if (pgmRtn) return;
        }
    }
    public void B114_REPAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUFPAY);
        LNCUFPAY.DATA.ACM_EVENT = "RLN";
        LNCUFPAY.DATA.LN_AC = LNCSFPAY.DATA.LN_AC;
        LNCUFPAY.DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCUFPAY.DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCUFPAY.DATA.SUF_NO = "0" + LNCUFPAY.DATA.SUF_NO;
        LNCUFPAY.DATA.TR_VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        LNCUFPAY.DATA.P_AMT = LNCSFPAY.DATA.P_AMT;
        LNCUFPAY.DATA.I_AMT = LNCSFPAY.DATA.I_AMT;
        LNCUFPAY.DATA.TOT_D_P_AMT = LNCSFPAY.DATA.TOT_P_AMT;
        LNCUFPAY.DATA.TOT_D_I_AMT = LNCSFPAY.DATA.TOT_I_AMT;
        LNCUFPAY.DATA.TOT_D_O_AMT = LNCSFPAY.DATA.TOT_O_AMT;
        LNCUFPAY.DATA.TOT_D_L_AMT = LNCSFPAY.DATA.TOT_L_AMT;
        LNCUFPAY.DATA.TERM = LNCSFPAY.DATA.TERM;
        LNCUFPAY.DATA.PAY_FLG = LNCSFPAY.DATA.REPAY_FLG;
        LNCUFPAY.DATA.MMO = LNCSFPAY.DATA.MMO;
        LNCUFPAY.DATA.ACAMT.STL_MTH2 = LNCSFPAY.DATA.ACAMT.STL_MTH;
        LNCUFPAY.DATA.ACAMT.REC_AC2 = LNCSFPAY.DATA.ACAMT.REC_AC;
        LNCUFPAY.DATA.ACAMT.AC_FLG2 = LNCSFPAY.DATA.ACAMT.AC_FLG;
        LNCUFPAY.DATA.ACAMT.PAY_AMT2 = LNCSFPAY.DATA.ACAMT.PAY_AMT;
        LNCUFPAY.DATA.ACAMT.AMT_FRM2 = LNCSFPAY.DATA.ACAMT.AMT_FRM;
        LNCUFPAY.DATA.ACAMT.REC_CCY2 = LNCSFPAY.DATA.ACAMT.REC_CCY;
        S000_CALL_LNZUFPAY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCUFPAY.RC);
    }
    public void B095_TX_AMT_CR_PROC() throws IOException,SQLException,Exception {
        if (WS_CR_TX_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B095_TX_IA_CR_PROC();
            if (pgmRtn) return;
        } else if (WS_CR_TX_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B095_TX_IB_CR_PROC();
            if (pgmRtn) return;
        } else {
            B095_TX_DD_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IA_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_CR_TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        AICUUPIA.DATA.AMT = WS_CR_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        AICUUPIA.DATA.RVS_NO = " ";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IB_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_CR_TX_AC;
        IBCPOSTA.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        IBCPOSTA.AMT = WS_CR_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.OUR_REF = LNCSFPAY.DATA.LN_AC;
        IBCPOSTA.THR_REF = LNCSFPAY.DATA.LN_AC;
        IBCPOSTA.TX_MMO = LNCSFPAY.DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZCRAC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_DD_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        if (WS_CR_TX_AC_TYP.equalsIgnoreCase(K_DC_AC) 
            || WS_CR_TX_AC_TYP.equalsIgnoreCase(K_EB_AC)) {
            DDCUCRAC.CARD_NO = WS_CR_TX_AC;
        } else {
            DDCUCRAC.AC = WS_CR_TX_AC;
        }
        DDCUCRAC.CCY = LNCSFPAY.DATA.ACAMT.REC_CCY;
        DDCUCRAC.TX_AMT = WS_CR_TX_AMT;
        DDCUCRAC.VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        DDCUCRAC.TX_MMO = LNCSFPAY.DATA.MMO;
        DDCUCRAC.RLT_AC = LNCSFPAY.DATA.LN_AC;
        DDCUCRAC.OTHER_CCY = LNCCLNQ.DATA.CCY;
        DDCUCRAC.OTHER_AMT = WS_CR_TX_AMT;
        if (DDCUCRAC.TX_AMT != 0) {
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_AMT_DR_PROC() throws IOException,SQLException,Exception {
        if (WS_DR_TX_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        } else if (WS_DR_TX_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B096_TX_IB_DR_PROC();
            if (pgmRtn) return;
        } else {
            B096_TX_DD_DR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_IA_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_DR_TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCCLNQ.DATA.CCY;
        AICUUPIA.DATA.AMT = WS_DR_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        AICUUPIA.DATA.RVS_NO = " ";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_IB_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_DR_TX_AC;
        IBCPOSTA.CCY = LNCCLNQ.DATA.CCY;
        IBCPOSTA.AMT = WS_DR_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.OUR_REF = LNCSFPAY.DATA.LN_AC;
        IBCPOSTA.THR_REF = LNCSFPAY.DATA.LN_AC;
        IBCPOSTA.TX_MMO = LNCSFPAY.DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_DD_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'N';
        DDCUDRAC.TSTS_TABL = "0014";
        if (WS_DR_TX_AC_TYP.equalsIgnoreCase(K_DC_AC) 
            || WS_DR_TX_AC_TYP.equalsIgnoreCase(K_EB_AC)) {
            DDCUDRAC.CARD_NO = WS_DR_TX_AC;
        } else {
            DDCUDRAC.AC = WS_DR_TX_AC;
        }
        DDCUDRAC.CCY = LNCCLNQ.DATA.CCY;
        DDCUDRAC.TX_AMT = WS_DR_TX_AMT;
        DDCUDRAC.VAL_DATE = LNCSFPAY.DATA.TR_VAL_DATE;
        DDCUDRAC.TX_MMO = LNCSFPAY.DATA.MMO;
        DDCUDRAC.RLT_AC = LNCSFPAY.DATA.LN_AC;
        DDCUDRAC.OTHER_CCY = LNCCLNQ.DATA.CCY;
        DDCUDRAC.OTHER_AMT = WS_DR_TX_AMT;
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
    }
    public void B200_BP_HISTORY_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = LNCSFPAY.DATA.LN_AC;
        BPCPFHIS.DATA.REF_NO = LNCSFPAY.DATA.LN_AC;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSFPAY.DATA.TR_VAL_DATE;
        BPCPFHIS.DATA.TX_CCY = LNCCLNQ.DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = WS_TOT_AMT;
        BPCPFHIS.DATA.TX_MMO = LNCSFPAY.DATA.MMO;
        BPCPFHIS.DATA.CI_NO = LNCCLNQ.DATA.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        if (LNCSFPAY.DATA.ACAMT.AC_FLG == '0') {
            BPCPFHIS.DATA.OTH_AC = LNCSFPAY.DATA.ACAMT.REC_AC;
        } else {
            BPCPFHIS.DATA.RLT_AC = LNCSFPAY.DATA.ACAMT.REC_AC;
        }
        if (LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("05) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(") 
            || LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase("06) LNCSFPAY.DATA.ACAMT.STL_MTH.equalsIgnoreCase(")) {
            BPCPFHIS.DATA.TX_TOOL = LNCSFPAY.DATA.ACAMT.REC_AC;
        }
        if (BPCPFHIS.DATA.TX_AMT != 0) {
            S000_CALL_BPZPFHIS();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
        CEP.TRC(SCCGWA, "DDZUDRAC SUCC");
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PD_PROJ_NO);
        CEP.TRC(SCCGWA, LNCRFUND);
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 456;
        CEP.TRC(SCCGWA, LNRFUND);
        CEP.TRC(SCCGWA, LNCRFUND);
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F' 
            && LNCRFUND.RETURN_INFO != 'E' 
            && LNCRFUND.RETURN_INFO != 'N') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRFUND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCFTA() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DDZUCRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC-DD", DDCUCRDD);
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK      ", DCCPCDCK);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUFPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-U-SPAY-MAIN", LNCUFPAY);
        if (!LNCUFPAY.RC.RC_MMO.equalsIgnoreCase("0")) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUFPAY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSFPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSFPAY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SFPAY=");
            CEP.TRC(SCCGWA, LNCSFPAY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
