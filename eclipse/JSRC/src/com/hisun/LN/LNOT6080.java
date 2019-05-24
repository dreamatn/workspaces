package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.GD.*;
import com.hisun.DD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6080 {
    int JIBS_tmp_int;
    DBParm LNTAGRE_RD;
    DBParm LNTRCVD_RD;
    brParm LNTRCVD_BR = new brParm();
    String JIBS_NumStr;
    brParm LNTPLPI_BR = new brParm();
    DBParm LNTPLPI_RD;
    brParm LNTTRAN_BR = new brParm();
    DBParm CITACAC_RD;
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "LOAN PLEAD IGNORANCE";
    String K_PART_WOFF = "LOAN PART WRITE OFF";
    char K_STATUS = '7';
    String K_WARN_RMK = "�?次�?�还本息 未结息不能撇�?";
    double WS_TOT_PLPI_AMT = 0;
    int WS_AC_DATE = 0;
    int WS_COUNT_CNT = 0;
    double WS_SUM_P_AMT = 0;
    double WS_SUM_REC_AMT = 0;
    double WS_SUM_INT_AMT = 0;
    double WS_SUM_L_AMT = 0;
    String WS_ERR_MSG = " ";
    short WS_CHAR_CNT = 0;
    String WS_FEE_CONT_NO = " ";
    String WS_FEE_ACCT_NO = " ";
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    int WS_LAST_TX_DT = 0;
    String WS_TRUS_W_AC = " ";
    double WS_TOT_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    String WS_CTL_STSW = " ";
    char WS_CTA_STS = ' ';
    double WS_I_101 = 0;
    double WS_I_201 = 0;
    double WS_I_203 = 0;
    double WS_O_101 = 0;
    double WS_L_101 = 0;
    double WS_BALL_L_AMT = 0;
    double WS_BALL_O_AMT = 0;
    double WS_BALL_I_AMT = 0;
    double WS_BALL_P_AMT = 0;
    double WS_BALL_P1_AMT = 0;
    char WS_READ_TRAN = ' ';
    int WS_TRAN_CTL_SEQ = 0;
    double WS_CUR_BAL = 0;
    double WS_TRAN_O_AMT = 0;
    double WS_L_CALC = 0;
    double WS_O_CALC = 0;
    double WS_I_CALC = 0;
    double WS_P_CALC = 0;
    char WS_SV_AMT_TYP = ' ';
    short WS_SV_TERM = 0;
    double WS_CALC_P_AMT = 0;
    double WS_CALC_I_AMT = 0;
    double WS_CALC_O_AMT = 0;
    double WS_CALC_L_AMT = 0;
    double WS_L_WAV_AMT = 0;
    double WS_O_WAV_AMT = 0;
    double WS_I_WAV_AMT = 0;
    double WS_P_WAV_AMT = 0;
    double WS_TMP_CALC = 0;
    char WS_REPY_STS = ' ';
    double WS_SV_CAL_AMT = 0;
    double WS_PRIN_AMT = 0;
    double WS_TOT_P_AMT = 0;
    double WS_TOT_I_AMT = 0;
    double WS_TOT_O_AMT = 0;
    double WS_TOT_L_AMT = 0;
    double WS_TOT_REPY_AMT = 0;
    double WS_TOT_INT_AMT = 0;
    double WS_TOT_NOPAY_AMT = 0;
    double WS_LN_TOT_AMT = 0;
    double WS_INT_AMT = 0;
    double WS_SV_P_AMT = 0;
    double WS_DUE_REPY_AMT = 0;
    double WS_PLPI_PRIN_AMT = 0;
    char WS_REC_STS = ' ';
    char WS_PAY_MTH = ' ';
    char WS_RCVD_UPD_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_CMP_FLG = ' ';
    char WS_SV_REPY_MTH = ' ';
    int WS_TOTAL_NUM = 0;
    LNOT6080_WS_TR_DATA WS_TR_DATA = new LNOT6080_WS_TR_DATA();
    LNOT6080_WS_OUT_DATA WS_OUT_DATA = new LNOT6080_WS_OUT_DATA();
    char WS_READ_LNTPLPI_FLG = ' ';
    char WS_READ_LNTRCVD_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCRTRAN LNCRTRAN = new LNCRTRAN();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    BPCCTRST BPCCTRST = new BPCCTRST();
    LNCAMDB LNCAMDB = new LNCAMDB();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNRSETL LNRSETL = new LNRSETL();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNRPLPI LNRPLPI = new LNRPLPI();
    CICSACR CICSACR = new CICSACR();
    CIRACAC CIRACAC = new CIRACAC();
    CICSACAC CICSACAC = new CICSACAC();
    LNCURPN LNCURPN = new LNCURPN();
    SCCGWA SCCGWA;
    LNB6080_AWA_6080 LNB6080_AWA_6080;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6080 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6080_AWA_6080>");
        LNB6080_AWA_6080 = (LNB6080_AWA_6080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
        }
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6080_AWA_6080.CONT_NO);
        if (LNB6080_AWA_6080.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB6080_AWA_6080.CONT_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        S000_CALL_LNZSSTBL();
        if (pgmRtn) return;
        WS_LAST_TX_DT = LNCCLNQ.DATA.LAST_F_VAL_DATE;
        WS_LAST_TX_SEQ = LNCCLNQ.DATA.LAST_TX_SEQ;
        WS_LAST_TX_SEQ += 1;
        WS_CTL_STSW = LNCCLNQ.DATA.CTL_STSW;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ICTL_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        CEP.TRC(SCCGWA, WS_CTL_STSW.substring(71 - 1, 71 + 1 - 1));
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (!WS_CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_ENTRUST_LN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (!WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_ENTRUST_LN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNB6080_AWA_6080.CONT_NO;
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "" + 0;
        JIBS_tmp_int = LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "0" + LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        WS_PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCSPDQ.FUNC_CODE = 'E';
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        if (LNCSPDQ.COMM_DATA.TOT_AMTS.THRG_AMT != 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_EXIST_FEE_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB6080_AWA_6080.CAL_TYP);
        if (LNB6080_AWA_6080.CAL_TYP != '1' 
            && LNB6080_AWA_6080.CAL_TYP != '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CALTYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB6080_AWA_6080.CAL_AMT);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT);
        WS_TOT_INT_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT + LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT + LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
        WS_TOT_REPY_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_P_AMT + LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT + LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT + LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
        CEP.TRC(SCCGWA, WS_TOT_REPY_AMT);
        WS_TOT_NOPAY_AMT = WS_TOT_REPY_AMT;
        CEP.TRC(SCCGWA, WS_TOT_NOPAY_AMT);
        if (LNB6080_AWA_6080.CAL_TYP == '2') {
            if (LNB6080_AWA_6080.CAL_AMT == 0 
                || LNB6080_AWA_6080.CAL_AMT > WS_TOT_NOPAY_AMT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CALAMT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB6080_AWA_6080.CAL_AMT == WS_TOT_NOPAY_AMT) {
            LNB6080_AWA_6080.CAL_TYP = '1';
        }
    }
    public void B120_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNB6080_AWA_6080.CAL_TYP == '1') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B120_CALL_LNZICLNQ();
                if (pgmRtn) return;
            }
            B210_GET_INFO();
            if (pgmRtn) return;
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (!WS_CTL_STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                B310_GET_TRUS_SETL_INF();
                if (pgmRtn) return;
            } else {
                B330_GET_FUND_AC_INF();
                if (pgmRtn) return;
            }
            WS_TOT_AMT = LNCCLNQ.DATA.TOT_BAL;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B230_SAV_LNTBALZ();
                if (pgmRtn) return;
                B230_LOAN_ACCOUNT_EVENT();
                if (pgmRtn) return;
            } else {
                B20R_READ_LNTTRAN();
                if (pgmRtn) return;
                B230_LOAN_ACCOUNT_EVENT();
                if (pgmRtn) return;
                B21R_UP_LNTBALZ();
                if (pgmRtn) return;
            }
            B250_CI_CLOSE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.CHG_DB_FLG);
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
                && (LNCAPRDM.REC_DATA.CHG_DB_FLG == '1' 
                || LNCAPRDM.REC_DATA.CHG_DB_FLG == '2')) {
                B260_CAL_FEE();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B220_UP_LOAN();
                if (pgmRtn) return;
                B270_TRAN_HISTORY();
                if (pgmRtn) return;
            } else {
                B22R_UP_LOAN();
                if (pgmRtn) return;
                R010_REVERSE_LNTTRAN();
                if (pgmRtn) return;
                R011_REVERSE_P_LNTTRAN();
                if (pgmRtn) return;
                R020_FINANCE_HIS();
                if (pgmRtn) return;
            }
        }
        if (LNB6080_AWA_6080.CAL_TYP == '2') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B070_CUST_REPAY_PROC();
                if (pgmRtn) return;
                R020_FINANCE_HIS();
                if (pgmRtn) return;
            } else {
                B070_CUST_REPAY_PROC();
                if (pgmRtn) return;
                R010_REVERSE_LNTTRAN();
                if (pgmRtn) return;
                R020_FINANCE_HIS();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDELI";
        LNCCNEX.COMM_DATA.LN_AC = LNB6080_AWA_6080.CONT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_I_101 = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        WS_I_201 = LNCCNEX.COMM_DATA.CTNR_DATA[5-1].CTNR_AMT;
        WS_I_203 = LNCCNEX.COMM_DATA.CTNR_DATA[7-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_I_101);
        CEP.TRC(SCCGWA, WS_I_201);
        CEP.TRC(SCCGWA, WS_I_203);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDUEL";
        LNCCNEX.COMM_DATA.LN_AC = LNB6080_AWA_6080.CONT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_O_101 = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        WS_L_101 = LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_O_101);
        CEP.TRC(SCCGWA, WS_L_101);
    }
    public void B20R_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        LNCRTRAN.FUNC = 'I';
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.KEY.CTL_SEQ = 0;
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, LNRTRAN.TR_DATA, WS_TR_DATA);
    }
    public void B21R_UP_LNTBALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '4';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        LNCBALLM.FUNC = '2';
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT01;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT02;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT05;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT06;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT08;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT20;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT22;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[25-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT25;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[44-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT44;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[54-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT54;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B220_UP_LOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.FUNC = 'R';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = "0" + LNRICTL.CTL_STSW.substring(1);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 2 - 1) + "0" + LNRICTL.CTL_STSW.substring(2 + 1 - 1);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 3 - 1) + "0" + LNRICTL.CTL_STSW.substring(3 + 1 - 1);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 4 - 1) + "1" + LNRICTL.CTL_STSW.substring(4 + 1 - 1);
        WS_P_CUR_TERM = LNRICTL.P_CUR_TERM;
        WS_IC_CUR_TERM = LNRICTL.IC_CUR_TERM;
        LNRICTL.P_CUR_TERM = LNRICTL.P_CAL_TERM;
        LNRICTL.IC_CUR_TERM = LNRICTL.IC_CAL_TERM;
        LNCRICTL.FUNC = 'U';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCRCONT.FUNC = 'R';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        LNRCONT.LAST_F_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_TX_SEQ = WS_LAST_TX_SEQ;
        LNRCONT.CTA_STS = K_STATUS;
        LNCRCONT.FUNC = 'U';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B22R_UP_LOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (WS_TR_DATA.WS_ICTL_STSW == null) WS_TR_DATA.WS_ICTL_STSW = "";
        JIBS_tmp_int = WS_TR_DATA.WS_ICTL_STSW.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_TR_DATA.WS_ICTL_STSW += " ";
        LNRICTL.CTL_STSW = WS_TR_DATA.WS_ICTL_STSW + LNRICTL.CTL_STSW.substring(4);
        LNRICTL.P_CUR_TERM = WS_P_CUR_TERM;
        LNRICTL.IC_CUR_TERM = WS_IC_CUR_TERM;
        LNCRICTL.FUNC = 'U';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCRCONT.FUNC = 'R';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        LNRCONT.LAST_F_VAL_DATE = WS_TR_DATA.WS_CONT_VAL_DATE;
        LNRCONT.LAST_TX_DT = WS_TR_DATA.WS_CONT_TX_DT;
        LNRCONT.LAST_TX_SEQ = WS_TR_DATA.WS_CONT_TX_SEQ;
        LNRCONT.CTA_STS = WS_TR_DATA.WS_CONT_CTA_STS;
        LNCRCONT.FUNC = 'U';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B230_LOAN_ACCOUNT_EVENT() throws IOException,SQLException,Exception {
        B120_CALL_LNZICLNQ();
        if (pgmRtn) return;
        B210_GET_INFO();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
        } else {
            LNCCLNQ.DATA.N1_BAL = LNRTRAN.P_AMT;
            LNCCLNQ.DATA.N2_BAL = LNRTRAN.P_WAV_AMT;
            LNCCLNQ.DATA.O_BAL = LNRTRAN.F_AMT;
            LNCCLNQ.DATA.O_BAL = LNRTRAN.P_WAV_AMT;
            WS_I_101 = LNRTRAN.I_AMT;
            WS_I_201 = LNRTRAN.I_ADJ_AMT;
            WS_I_203 = LNRTRAN.I_WAV_AMT;
            WS_O_101 = LNRTRAN.O_WAV_AMT;
            WS_L_101 = LNRTRAN.L_WAV_AMT;
        }
        IBS.init(SCCGWA, LNCCNEV);
        if (LNB6080_AWA_6080.CAL_TYP == '1') {
            LNCCNEV.COMM_DATA.EVENT_CODE = "KOPPRIN";
            LNCCNEV.COMM_DATA.LN_AC = LNB6080_AWA_6080.CONT_NO;
            LNCCNEV.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
            LNCCNEV.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = LNCCLNQ.DATA.N1_BAL;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = LNCCLNQ.DATA.N2_BAL;
            LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT = LNCCLNQ.DATA.O_BAL;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_I_101;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_I_201;
            LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT = WS_I_203;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = WS_O_101;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_L_101;
        } else {
            LNCCNEV.COMM_DATA.EVENT_CODE = "REPAYNM";
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_BALL_P_AMT;
            LNCCNEV.COMM_DATA.P_AMT = WS_BALL_P_AMT;
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (WS_CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT = WS_BALL_P_AMT;
            }
            LNCCNEV.COMM_DATA.I_AMT = WS_BALL_I_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_BALL_I_AMT;
            LNCCNEV.COMM_DATA.O_AMT = WS_BALL_O_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].OLC_AMT = WS_BALL_O_AMT;
            LNCCNEV.COMM_DATA.L_AMT = WS_BALL_L_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].LLC_AMT = WS_BALL_L_AMT;
        }
        LNCCNEV.COMM_DATA.ACM_EVENT = "RLN";
        LNCCNEV.COMM_DATA.LN_AC = LNB6080_AWA_6080.CONT_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B230_SAV_LNTBALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '4';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT01 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT02 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT05 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT06 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT08 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT20 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT22 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT25 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[25-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT44 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[44-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT54 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[54-1].LOAN_BAL;
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        WS_TR_DATA.WS_ICTL_STSW = LNRICTL.CTL_STSW.substring(0, 4);
        WS_TR_DATA.WS_ICTL_P_CUR_TERM = LNRICTL.P_CUR_TERM;
        WS_TR_DATA.WS_ICTL_IC_CUR_TERM = LNRICTL.IC_CUR_TERM;
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_TR_DATA.WS_CONT_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        WS_TR_DATA.WS_CONT_TX_DT = LNRCONT.LAST_TX_DT;
        WS_TR_DATA.WS_CONT_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        WS_TR_DATA.WS_CONT_CTA_STS = LNRCONT.CTA_STS;
    }
    public void B250_CI_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, CICSACAC);
            CICSACAC.FUNC = 'D';
            CICSACAC.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            CICSACAC.DATA.ACAC_NO = LNB6080_AWA_6080.CONT_NO;
            CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
            S000_CALL_CIZSACAC();
            if (pgmRtn) return;
            T000_GROUP_CITACAC();
            if (pgmRtn) return;
            if (WS_TOTAL_NUM > 0) {
            } else if (WS_TOTAL_NUM == 0) {
                IBS.init(SCCGWA, CICSACR);
                CICSACR.FUNC = 'D';
                CICSACR.DATA.AGR_NO = LNRAGRE.PAPER_NO;
                CICSACR.DATA.ENTY_TYP = '1';
                S000_CALL_CIZSACR();
                if (pgmRtn) return;
            } else {
            }
            WS_TR_DATA.WS_TMP_TOT_NUM = WS_TOTAL_NUM;
        } else {
            if (WS_TR_DATA.WS_TMP_TOT_NUM > 0) {
            }
            if (WS_TR_DATA.WS_TMP_TOT_NUM == 0) {
                IBS.init(SCCGWA, CICSACR);
                CICSACR.FUNC = 'D';
                CICSACR.DATA.AGR_NO = LNRAGRE.PAPER_NO;
                CICSACR.DATA.ENTY_TYP = '1';
                S000_CALL_CIZSACR();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICSACAC);
            CICSACAC.FUNC = 'D';
            CICSACAC.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            CICSACAC.DATA.ACAC_NO = LNB6080_AWA_6080.CONT_NO;
            CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
            S000_CALL_CIZSACAC();
            if (pgmRtn) return;
        }
    }
    public void B260_CAL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AGRE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        WS_FEE_CONT_NO = LNRAGRE.DRAW_NO;
        WS_FEE_ACCT_NO = LNRAGRE.PAPER_NO;
        WS_CHAR_CNT = WS_FEE_CONT_NO.trim().length();
        WS_CHAR_CNT += 1;
        IBS.init(SCCGWA, BPCCTRST);
        BPCCTRST.LN_CTRT_NO = WS_FEE_CONT_NO;
        BPCCTRST.SETTLE_FLG = 'Y';
        S000_CALL_BPZFCTRM();
        if (pgmRtn) return;
    }
    public void B270_TRAN_HISTORY() throws IOException,SQLException,Exception {
        R010_WRITE_LNTTRAN();
        if (pgmRtn) return;
        R011_WRITE_T_LNTTRAN();
        if (pgmRtn) return;
        R020_FINANCE_HIS();
        if (pgmRtn) return;
    }
    public void R010_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        LNCRTRAN.FUNC = 'A';
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'P';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.TRAN_STS = 'N';
        LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNRTRAN.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.DUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_SEQ = WS_LAST_TX_SEQ;
        if (LNB6080_AWA_6080.CAL_TYP == '1') {
            LNRTRAN.PAY_AMT1 = LNCCLNQ.DATA.TOT_BAL;
            LNRTRAN.P_AMT = LNCCLNQ.DATA.N1_BAL;
            LNRTRAN.P_WAV_AMT = LNCCLNQ.DATA.N2_BAL;
            LNRTRAN.P_WAV_AMT = LNCCLNQ.DATA.O_BAL;
            LNRTRAN.I_AMT = WS_I_101;
            LNRTRAN.I_ADJ_AMT = WS_I_201;
            LNRTRAN.I_WAV_AMT = WS_I_203;
            LNRTRAN.O_WAV_AMT = WS_O_101;
            LNRTRAN.L_WAV_AMT = WS_L_101;
            LNRTRAN.OS_BAL = WS_CUR_BAL;
        } else {
            LNRTRAN.PAY_AMT1 = LNB6080_AWA_6080.CAL_AMT;
            LNRTRAN.P_AMT = WS_SUM_P_AMT;
            LNRTRAN.P_WAV_AMT = WS_BALL_P_AMT;
            LNRTRAN.I_AMT = WS_SUM_INT_AMT;
            LNRTRAN.I_ADJ_AMT = WS_BALL_I_AMT;
            LNRTRAN.I_WAV_AMT = 0;
            LNRTRAN.O_AMT = WS_SUM_REC_AMT;
            LNRTRAN.O_WAV_AMT = WS_BALL_O_AMT;
            LNRTRAN.L_AMT = WS_SUM_L_AMT;
            LNRTRAN.L_WAV_AMT = WS_BALL_L_AMT;
            LNRTRAN.OS_BAL = WS_CUR_BAL;
            CEP.TRC(SCCGWA, WS_SUM_P_AMT);
            CEP.TRC(SCCGWA, WS_SUM_INT_AMT);
            CEP.TRC(SCCGWA, WS_SUM_REC_AMT);
            CEP.TRC(SCCGWA, WS_SUM_L_AMT);
        }
        CEP.TRC(SCCGWA, "R010-WRITE-LNTTRAN");
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.N1_BAL);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.N2_BAL);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.O_BAL);
        CEP.TRC(SCCGWA, WS_I_101);
        CEP.TRC(SCCGWA, WS_I_201);
        CEP.TRC(SCCGWA, WS_I_203);
        CEP.TRC(SCCGWA, WS_O_101);
        CEP.TRC(SCCGWA, WS_L_101);
        LNRTRAN.LAST_F_VAL_DATE = WS_LAST_TX_DT;
        LNRTRAN.TXN_CCY = LNCCLNQ.DATA.CCY;
        LNRTRAN.LOAN_STSW = WS_CTL_STSW;
        LNRTRAN.TR_MMO = "12010024";
        LNRTRAN.ACTL_DATE = WS_P_CUR_TERM;
        LNRTRAN.ACTL_TIME = WS_IC_CUR_TERM;
        LNRTRAN.TR_DATA = IBS.CLS2CPY(SCCGWA, WS_TR_DATA);
        IBS.init(SCCGWA, WS_TR_DATA.WS_RCVD_DATA);
        IBS.init(SCCGWA, WS_TR_DATA.WS_PLPI_DATA);
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void R011_WRITE_T_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        LNCRTRAN.FUNC = 'A';
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.TRAN_STS = 'N';
        LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNRTRAN.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.DUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_SEQ = WS_LAST_TX_SEQ;
        if (LNB6080_AWA_6080.CAL_TYP == '1') {
            LNRTRAN.PAY_AMT1 = LNCCLNQ.DATA.TOT_BAL;
            LNRTRAN.P_AMT = LNCCLNQ.DATA.N1_BAL;
            LNRTRAN.P_WAV_AMT = LNCCLNQ.DATA.N2_BAL;
            LNRTRAN.P_WAV_AMT = LNCCLNQ.DATA.O_BAL;
            LNRTRAN.I_AMT = WS_I_101;
            LNRTRAN.I_ADJ_AMT = WS_I_201;
            LNRTRAN.I_WAV_AMT = WS_I_203;
            LNRTRAN.O_WAV_AMT = WS_O_101;
            LNRTRAN.L_WAV_AMT = WS_L_101;
            LNRTRAN.OS_BAL = WS_CUR_BAL;
        } else {
            LNRTRAN.PAY_AMT1 = LNB6080_AWA_6080.CAL_AMT;
            LNRTRAN.P_AMT = WS_SUM_P_AMT;
            LNRTRAN.P_WAV_AMT = WS_BALL_P_AMT;
            LNRTRAN.I_AMT = WS_SUM_INT_AMT;
            LNRTRAN.I_ADJ_AMT = WS_BALL_I_AMT;
            LNRTRAN.I_WAV_AMT = 0;
            LNRTRAN.O_AMT = WS_SUM_REC_AMT;
            LNRTRAN.O_WAV_AMT = WS_BALL_O_AMT;
            LNRTRAN.L_AMT = WS_SUM_L_AMT;
            LNRTRAN.L_WAV_AMT = WS_BALL_L_AMT;
            LNRTRAN.OS_BAL = WS_CUR_BAL;
            CEP.TRC(SCCGWA, WS_SUM_P_AMT);
            CEP.TRC(SCCGWA, WS_SUM_INT_AMT);
            CEP.TRC(SCCGWA, WS_SUM_REC_AMT);
            CEP.TRC(SCCGWA, WS_SUM_L_AMT);
        }
        CEP.TRC(SCCGWA, "R011-WRITE-T-LNTTRAN");
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.N1_BAL);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.N2_BAL);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.O_BAL);
        CEP.TRC(SCCGWA, WS_I_101);
        CEP.TRC(SCCGWA, WS_I_201);
        CEP.TRC(SCCGWA, WS_I_203);
        CEP.TRC(SCCGWA, WS_O_101);
        CEP.TRC(SCCGWA, WS_L_101);
        LNRTRAN.LAST_F_VAL_DATE = WS_LAST_TX_DT;
        LNRTRAN.TXN_CCY = LNCCLNQ.DATA.CCY;
        LNRTRAN.LOAN_STSW = WS_CTL_STSW;
        LNRTRAN.TR_MMO = "12010024";
        LNRTRAN.ACTL_DATE = WS_P_CUR_TERM;
        LNRTRAN.ACTL_TIME = WS_IC_CUR_TERM;
        LNRTRAN.TR_DATA = IBS.CLS2CPY(SCCGWA, WS_TR_DATA);
        IBS.init(SCCGWA, WS_TR_DATA.WS_RCVD_DATA);
        IBS.init(SCCGWA, WS_TR_DATA.WS_PLPI_DATA);
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void R010_WRITE_TRAN_DTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        LNCRTRAN.FUNC = 'A';
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = ' ';
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        if (LNRRCVD.KEY.AMT_TYP != ' ') {
            LNRTRAN.KEY.TXN_TYP = LNRRCVD.KEY.AMT_TYP;
        } else {
            if (LNRPLPI.KEY.REPY_MTH != ' ') {
                LNRTRAN.KEY.TXN_TYP = LNRPLPI.KEY.REPY_MTH;
            }
        }
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.KEY.CTL_SEQ = (short) WS_TRAN_CTL_SEQ;
        WS_TRAN_CTL_SEQ = WS_TRAN_CTL_SEQ + 1;
        LNRTRAN.TRAN_STS = 'N';
        LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNRTRAN.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.DUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_SEQ = WS_LAST_TX_SEQ;
        LNRTRAN.PAY_AMT1 = LNCCLNQ.DATA.TOT_BAL;
        if (LNB6080_AWA_6080.CAL_TYP == '2') {
            LNRTRAN.PAY_AMT1 = LNB6080_AWA_6080.CAL_AMT;
        }
        LNRTRAN.LAST_F_VAL_DATE = WS_LAST_TX_DT;
        LNRTRAN.TXN_CCY = LNCCLNQ.DATA.CCY;
        LNRTRAN.TR_MMO = "12010024";
        LNRTRAN.TR_DATA = IBS.CLS2CPY(SCCGWA, WS_TR_DATA);
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void R020_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_MMO = "12010024";
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPCPFHIS.DATA.TX_AMT = LNCCLNQ.DATA.TOT_BAL;
        } else {
            CEP.TRC(SCCGWA, LNRTRAN.PAY_AMT1);
            BPCPFHIS.DATA.TX_AMT = LNRTRAN.PAY_AMT1;
        }
        BPCPFHIS.DATA.AC = LNB6080_AWA_6080.CONT_NO;
        BPCPFHIS.DATA.TX_CCY = LNCCLNQ.DATA.CCY;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCPFHIS.DATA.TX_CCY = LNRTRAN.TXN_CCY;
        }
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.CI_NO = LNCCLNQ.DATA.CI_NO;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARKS;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        BPCPFHIS.DATA.FMT_CODE = "LN608";
        WS_OUT_DATA.WS_CONT_NO = LNB6080_AWA_6080.CONT_NO;
        WS_OUT_DATA.WS_BR = LNCCLNQ.DATA.DOMI_BR;
        WS_OUT_DATA.WS_CI_NO = LNCCLNQ.DATA.CI_NO;
        WS_OUT_DATA.WS_CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        WS_OUT_DATA.WS_PROD_CD = LNCCLNQ.DATA.PROD_CD;
        WS_OUT_DATA.WS_LN_STS = LNCCLNQ.DATA.STS;
        WS_OUT_DATA.WS_CCY = LNCCLNQ.DATA.CCY;
        WS_OUT_DATA.WS_LN_AMT = LNCCLNQ.DATA.AMT;
        WS_OUT_DATA.WS_LN_BAL = LNCCLNQ.DATA.TOT_BAL;
        if (LNB6080_AWA_6080.CAL_TYP == '2') {
            WS_OUT_DATA.WS_LN_BAL = LNB6080_AWA_6080.CAL_AMT;
            BPCPFHIS.DATA.TX_AMT = LNB6080_AWA_6080.CAL_AMT;
            BPCPFHIS.DATA.REMARK = K_PART_WOFF;
            WS_OUT_DATA.WS_CAL_TYP = LNB6080_AWA_6080.CAL_TYP;
            WS_OUT_DATA.WS_CAL_AMT = LNB6080_AWA_6080.CAL_AMT;
            WS_OUT_DATA.WS_CAL_RSN = LNB6080_AWA_6080.CAL_RSN;
        }
        WS_OUT_DATA.WS_OWE_INT = LNB6080_AWA_6080.OWE_INT;
        WS_OUT_DATA.WS_OWE_CINT = LNB6080_AWA_6080.OWE_CINT;
        WS_OUT_DATA.WS_OWE_MINT = LNB6080_AWA_6080.OWE_MINT;
        WS_OUT_DATA.WS_CAL_FLG = LNB6080_AWA_6080.CAL_FLG;
        BPCPFHIS.DATA.FMT_LEN = 527;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        BPCPFHIS.DATA.ACO_AC = LNB6080_AWA_6080.CONT_NO;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B070_CUST_REPAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAMDB);
        LNCAMDB.COMM_DATA.FUNC_CODE = 'U';
        LNCAMDB.COMM_DATA.LN_AC = LNB6080_AWA_6080.CONT_NO;
        LNCAMDB.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCAMDB.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCAMDB.COMM_DATA.SUF_NO = "0" + LNCAMDB.COMM_DATA.SUF_NO;
        LNCAMDB.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCAMDB.COMM_DATA.MAX_PAY_AMT = LNB6080_AWA_6080.CAL_AMT;
        S000_CALL_LNZAMDB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_AMT);
        IBS.init(SCCGWA, LNCURPN);
        LNCURPN.COMM_DATA.ACM_EVENT = "RLN";
        LNCURPN.COMM_DATA.LN_AC = LNB6080_AWA_6080.CONT_NO;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_AMT = LNB6080_AWA_6080.CAL_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT;
        LNCURPN.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCURPN.COMM_DATA.APT_REF = LNCAMDB.COMM_DATA.APT_REF;
        S000_CALL_LNZURPN();
        if (pgmRtn) return;
    }
    public void R010_REVERSE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        LNCRTRAN.FUNC = 'R';
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.KEY.CTL_SEQ = 0;
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
        LNCRTRAN.FUNC = 'U';
        LNRTRAN.TRAN_STS = 'R';
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_REV_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void R011_REVERSE_P_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        IBS.init(SCCGWA, LNCRTRAN);
        LNCRTRAN.FUNC = 'R';
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'P';
        LNRTRAN.KEY.TXN_TERM = 0;
        LNRTRAN.KEY.TRAN_FLG = 'C';
        LNRTRAN.KEY.CTL_SEQ = 0;
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
        LNCRTRAN.FUNC = 'U';
        LNRTRAN.TRAN_STS = 'R';
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.TR_REV_JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        S000_CALL_LNZRTRAN();
        if (pgmRtn) return;
    }
    public void B310_GET_TRUS_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        LNRSETL.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCCLNQ.DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        WS_TRUS_W_AC = LNRSETL.AC;
    }
    public void B330_GET_FUND_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        WS_TRUS_W_AC = LNRFUND.FUND_AC;
    }
    public void B400_UPDATE_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCRCONT.FUNC = 'R';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_TR_DATA.WS_CONT.WS_CONT_SEQ = LNRCONT.LAST_TX_SEQ;
        }
        LNRCONT.LAST_F_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNRCONT.LAST_TX_SEQ = WS_LAST_TX_SEQ;
        } else {
            LNRCONT.LAST_TX_SEQ = WS_TR_DATA.WS_CONT.WS_CONT_SEQ;
        }
        LNCRCONT.FUNC = 'U';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B410_UPDATE_BALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '4';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT01 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT02 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT05 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT06 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT08 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT15 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT20 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT22 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT25 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[25-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT42 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[42-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT44 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[44-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT52 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[52-1].LOAN_BAL;
        WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT54 = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[54-1].LOAN_BAL;
        WS_CUR_BAL = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL + LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL + LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL;
        LNCBALLM.FUNC = '2';
        CEP.TRC(SCCGWA, WS_BALL_L_AMT);
        CEP.TRC(SCCGWA, WS_BALL_O_AMT);
        CEP.TRC(SCCGWA, WS_BALL_I_AMT);
        CEP.TRC(SCCGWA, WS_BALL_P_AMT);
        CEP.TRC(SCCGWA, WS_BALL_P1_AMT);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[3-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[4-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[42-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[44-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[52-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[55-1].LOAN_BAL);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL -= WS_BALL_P_AMT;
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[05-1].LOAN_BAL += WS_BALL_P_AMT;
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL += WS_BALL_I_AMT;
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL += WS_BALL_I_AMT;
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[42-1].LOAN_BAL += WS_BALL_O_AMT;
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[52-1].LOAN_BAL += WS_BALL_L_AMT;
        CEP.TRC(SCCGWA, "AFTER UPDATE LNTBALZ");
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[3-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[4-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[42-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[44-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[52-1].LOAN_BAL);
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[55-1].LOAN_BAL);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B410_REVERSE_BALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '4';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        LNCBALLM.FUNC = '2';
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT01;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[2-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT02;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[5-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT05;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[6-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT06;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT08;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT15;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT20;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[22-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT22;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[25-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT25;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[42-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT42;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[44-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT44;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[52-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT52;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[54-1].LOAN_BAL = WS_TR_DATA.WS_BALLM_AMT_TMP_AREA.WS_TMP_AMT54;
        LNCBALLM.REC_DATA.REDEFINES18.LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B420_READ_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_LN_TOT_AMT = LNRCONT.LN_TOT_AMT;
    }
    public void B430_READ_RCVD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6080_AWA_6080.CONT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        LNRRCVD.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND REPY_STS < > '2'";
        LNTRCVD_RD.set = "WS-SUM-P-AMT=IFNULL(SUM(P_REC_AMT-P_PAY_AMT-P_WAV_AMT),0),WS-SUM-INT-AMT=IFNULL(SUM(I_REC_AMT-I_PAY_AMT-I_WAV_AMT),0),WS-SUM-REC-AMT=IFNULL(SUM(O_REC_AMT-O_PAY_AMT-O_WAV_AMT+O_LST_PST_AMT),0),WS-SUM-L-AMT=IFNULL(SUM(L_REC_AMT-L_PAY_AMT-L_WAV_AMT+L_LST_PST_AMT),0)";
        IBS.GROUP(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_SUM_P_AMT);
        CEP.TRC(SCCGWA, WS_SUM_REC_AMT);
        CEP.TRC(SCCGWA, WS_SUM_INT_AMT);
        CEP.TRC(SCCGWA, WS_SUM_L_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TRAN_O_AMT = 0;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B430_UPDATE_RCVD() throws IOException,SQLException,Exception {
        if (WS_PAY_MTH == '1') {
            WS_SV_AMT_TYP = 'I';
            WS_SV_TERM = 1;
            T02_UPDATE_LNTRCVD();
            if (pgmRtn) return;
            WS_SV_AMT_TYP = 'P';
            WS_SV_TERM = 1;
            T02_UPDATE_LNTRCVD();
            if (pgmRtn) return;
        } else if (WS_PAY_MTH == '3'
            || WS_PAY_MTH == '4'
            || WS_PAY_MTH == '5') {
            WS_FOUND_FLG = 'Y';
            T01_STARTBR_LNTRCVD();
            if (pgmRtn) return;
            T01_READNEXT_LNTRCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_FOUND_FLG);
            CEP.TRC(SCCGWA, WS_END_FLG);
            WS_SV_CAL_AMT = LNB6080_AWA_6080.CAL_AMT;
            WS_END_FLG = 'N';
            while (WS_FOUND_FLG != 'N' 
                && WS_END_FLG != 'Y') {
                WS_INT_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNRRCVD.L_LST_PST_AMT + LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT + LNRRCVD.O_LST_PST_AMT + LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                WS_SV_P_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT - LNRRCVD.P_WAV_AMT;
                CEP.TRC(SCCGWA, WS_SV_P_AMT);
                WS_PRIN_AMT = WS_SV_P_AMT + WS_INT_AMT;
                CEP.TRC(SCCGWA, WS_INT_AMT);
                CEP.TRC(SCCGWA, WS_PRIN_AMT);
                CEP.TRC(SCCGWA, "B430-11111");
                if (WS_SV_CAL_AMT > WS_PRIN_AMT) {
                    WS_TMP_CALC = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNRRCVD.L_LST_PST_AMT;
                    LNRRCVD.L_WAV_AMT += WS_TMP_CALC;
                    WS_BALL_L_AMT += WS_TMP_CALC;
                    WS_TMP_CALC = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT + LNRRCVD.O_LST_PST_AMT;
                    LNRRCVD.O_WAV_AMT += WS_TMP_CALC;
                    WS_BALL_O_AMT += WS_TMP_CALC;
                    CEP.TRC(SCCGWA, WS_BALL_O_AMT);
                    WS_TMP_CALC = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                    LNRRCVD.I_WAV_AMT += WS_TMP_CALC;
                    WS_BALL_I_AMT += WS_TMP_CALC;
                    LNRRCVD.P_WAV_AMT += WS_SV_P_AMT;
                    WS_BALL_P_AMT += WS_SV_P_AMT;
                    CEP.TRC(SCCGWA, "B430-22222");
                    WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_PRIN_AMT;
                    LNRRCVD.REPY_STS = '2';
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.L_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.I_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
                } else {
                    CEP.TRC(SCCGWA, WS_CALC_L_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.L_WAV_AMT);
                    WS_CALC_L_AMT = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNRRCVD.L_LST_PST_AMT;
                    CEP.TRC(SCCGWA, "CALC L-AMT");
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    CEP.TRC(SCCGWA, WS_CALC_L_AMT);
                    if (WS_SV_CAL_AMT <= WS_CALC_L_AMT) {
                        LNRRCVD.L_WAV_AMT += WS_SV_CAL_AMT;
                        WS_BALL_L_AMT += WS_SV_CAL_AMT;
                        WS_SV_CAL_AMT = 0;
                    } else {
                        WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_CALC_L_AMT;
                        LNRRCVD.L_WAV_AMT += WS_CALC_L_AMT;
                        WS_BALL_L_AMT += WS_CALC_L_AMT;
                        WS_CALC_O_AMT = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT + LNRRCVD.O_LST_PST_AMT;
                        CEP.TRC(SCCGWA, "CALC O-AMT");
                        CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                        CEP.TRC(SCCGWA, WS_CALC_O_AMT);
                        if (WS_SV_CAL_AMT <= WS_CALC_O_AMT) {
                            LNRRCVD.O_WAV_AMT += WS_SV_CAL_AMT;
                            WS_BALL_O_AMT += WS_SV_CAL_AMT;
                            WS_SV_CAL_AMT = 0;
                            CEP.TRC(SCCGWA, WS_BALL_O_AMT);
                        } else {
                            WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_CALC_O_AMT;
                            LNRRCVD.O_WAV_AMT += WS_CALC_O_AMT;
                            WS_BALL_O_AMT += WS_CALC_O_AMT;
                            WS_CALC_I_AMT = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                            CEP.TRC(SCCGWA, "CALC I-AMT");
                            CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                            CEP.TRC(SCCGWA, WS_CALC_I_AMT);
                            if (WS_SV_CAL_AMT <= WS_CALC_I_AMT) {
                                LNRRCVD.I_WAV_AMT += WS_SV_CAL_AMT;
                                WS_BALL_I_AMT += WS_SV_CAL_AMT;
                                WS_SV_CAL_AMT = 0;
                            } else {
                                WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_CALC_I_AMT;
                                LNRRCVD.I_WAV_AMT += WS_CALC_I_AMT;
                                WS_BALL_I_AMT += WS_CALC_I_AMT;
                                WS_CALC_P_AMT = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT - LNRRCVD.P_WAV_AMT;
                                CEP.TRC(SCCGWA, "CALC P-AMT");
                                CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                                CEP.TRC(SCCGWA, WS_CALC_P_AMT);
                                if (WS_SV_CAL_AMT <= WS_CALC_P_AMT) {
                                    LNRRCVD.P_WAV_AMT += WS_SV_CAL_AMT;
                                    WS_BALL_P_AMT += WS_SV_CAL_AMT;
                                    if (WS_SV_CAL_AMT == WS_CALC_P_AMT) {
                                        LNRRCVD.REPY_STS = '2';
                                    }
                                    WS_SV_CAL_AMT = 0;
                                }
                            }
                        }
                    }
                    LNRRCVD.REPY_STS = '1';
                    CEP.TRC(SCCGWA, "B430-33333");
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.L_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.I_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.REPY_STS);
                    WS_END_FLG = 'Y';
                }
                WS_L_WAV_AMT = LNRRCVD.L_WAV_AMT;
                WS_O_WAV_AMT = LNRRCVD.O_WAV_AMT;
                WS_I_WAV_AMT = LNRRCVD.I_WAV_AMT;
                WS_P_WAV_AMT = LNRRCVD.P_WAV_AMT;
                WS_REPY_STS = LNRRCVD.REPY_STS;
                T01_UPDATE_LNTRCVD();
                if (pgmRtn) return;
                T01_READNEXT_LNTRCVD();
                if (pgmRtn) return;
            }
            T01_ENDBR_LNTRCVD();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B440_UPDATE_PLPI() throws IOException,SQLException,Exception {
        WS_BALL_P1_AMT += WS_BALL_P_AMT;
        CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
        if (WS_RCVD_UPD_FLG == 'N') {
            WS_SV_CAL_AMT = WS_TOT_AMT;
            LNRRCVD.KEY.TERM = 0;
            CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
        }
        if (WS_PAY_MTH == '1') {
        } else if (WS_PAY_MTH == '2'
            || WS_PAY_MTH == '3'
            || WS_PAY_MTH == '4'
            || WS_PAY_MTH == '5') {
            WS_FOUND_FLG = 'Y';
            T01_STARTBR_LNTPLPI();
            if (pgmRtn) return;
            T01_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_FOUND_FLG);
            CEP.TRC(SCCGWA, WS_END_FLG);
            WS_PRIN_AMT = LNRPLPI.DUE_REPY_AMT;
            WS_END_FLG = 'N';
            while (WS_FOUND_FLG != 'N' 
                && WS_END_FLG != 'Y') {
                CEP.TRC(SCCGWA, "B440-11111");
                if (WS_SV_CAL_AMT >= LNRPLPI.PRIN_AMT) {
                    WS_DUE_REPY_AMT = 0;
                    WS_PLPI_PRIN_AMT = 0;
                    WS_REC_STS = '1';
                    CEP.TRC(SCCGWA, "B440-22222");
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    WS_SV_CAL_AMT = WS_SV_CAL_AMT - LNRPLPI.PRIN_AMT;
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    if (WS_SV_CAL_AMT == 0) {
                        WS_END_FLG = 'Y';
                    }
                } else {
                    WS_PLPI_PRIN_AMT = LNRPLPI.PRIN_AMT - WS_SV_CAL_AMT;
                    WS_DUE_REPY_AMT = WS_PLPI_PRIN_AMT;
                    CEP.TRC(SCCGWA, "B440-33333");
                    CEP.TRC(SCCGWA, WS_DUE_REPY_AMT);
                    CEP.TRC(SCCGWA, WS_PLPI_PRIN_AMT);
                    CEP.TRC(SCCGWA, WS_INT_AMT);
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    WS_END_FLG = 'Y';
                    WS_SV_CAL_AMT = 0;
                }
                T01_UPDATE_LNTPLPI();
                if (pgmRtn) return;
                T01_READNEXT_LNTPLPI();
                if (pgmRtn) return;
            }
            T01_ENDBR_LNTPLPI();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T01_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.SUBS_PROJ_NO = 0;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND SUBS_PROJ_NO = :LNRRCVD.KEY.SUBS_PROJ_NO";
        LNTRCVD_BR.rp.order = "AMT_TYP , TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T01_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.P_PAY_AMT);
        CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
        WS_SV_AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        WS_SV_TERM = LNRRCVD.KEY.TERM;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
        }
    }
    public void T01_UPDATE_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        IBS.init(SCCGWA, LNRRCVD);
        LNCRCVDM.FUNC = '4';
        LNRRCVD.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.SUBS_PROJ_NO = 0;
        if (WS_PAY_MTH == '1') {
            LNRRCVD.KEY.AMT_TYP = WS_SV_AMT_TYP;
            LNRRCVD.KEY.TERM = WS_SV_TERM;
        } else if (WS_PAY_MTH == '4'
            || WS_PAY_MTH == '5') {
            LNRRCVD.KEY.AMT_TYP = WS_SV_AMT_TYP;
            LNRRCVD.KEY.TERM = WS_SV_TERM;
        } else {
        }
        CEP.TRC(SCCGWA, "LNTRCVD 1111");
        WS_RCVD_UPD_FLG = 'Y';
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == 0) {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND;
            WS_RCVD_UPD_FLG = 'N';
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCVDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_RCVD_UPD_FLG == 'Y') {
            CEP.TRC(SCCGWA, "LNTRCVD 2222");
            T02_SAV_LNTRCVD();
            if (pgmRtn) return;
            LNCRCVDM.FUNC = '2';
            LNRRCVD.L_WAV_AMT = WS_L_WAV_AMT;
            LNRRCVD.O_WAV_AMT = WS_O_WAV_AMT;
            LNRRCVD.I_WAV_AMT = WS_I_WAV_AMT;
            LNRRCVD.P_WAV_AMT = WS_P_WAV_AMT;
            LNRRCVD.REPY_STS = WS_REPY_STS;
            if (WS_PAY_MTH == '1') {
                WS_L_CALC = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNRRCVD.L_LST_PST_AMT;
                WS_I_CALC = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                if (LNB6080_AWA_6080.CAL_AMT <= WS_L_CALC) {
                    LNRRCVD.L_WAV_AMT += LNB6080_AWA_6080.CAL_AMT;
                    WS_BALL_L_AMT = LNB6080_AWA_6080.CAL_AMT;
                } else {
                    WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_L_CALC;
                    if (WS_SV_CAL_AMT <= WS_I_CALC) {
                        LNRRCVD.I_WAV_AMT += WS_SV_CAL_AMT;
                        WS_BALL_I_AMT = WS_SV_CAL_AMT;
                    } else {
                        LNRRCVD.I_WAV_AMT += WS_I_CALC;
                        WS_BALL_I_AMT = WS_I_CALC;
                        WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_I_CALC;
                    }
                }
            }
            if (WS_PAY_MTH == '1') {
                WS_O_CALC = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT + LNRRCVD.L_LST_PST_AMT;
                WS_P_CALC = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT - LNRRCVD.P_WAV_AMT;
                if (WS_SV_CAL_AMT <= WS_O_CALC) {
                    LNRRCVD.O_WAV_AMT += WS_SV_CAL_AMT;
                    WS_BALL_O_AMT = WS_SV_CAL_AMT;
                } else {
                    WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_O_CALC;
                    if (WS_SV_CAL_AMT < WS_P_CALC) {
                        LNRRCVD.P_WAV_AMT += WS_SV_CAL_AMT;
                        WS_BALL_P_AMT = WS_SV_CAL_AMT;
                    }
                }
            }
            if (WS_REPY_STS == '2') {
                JIBS_NumStr = "" + 0;
                LNRRCVD.TERM_STS = JIBS_NumStr.charAt(0);
            }
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
            CEP.TRC(SCCGWA, "LNTRCVD 44444");
            CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCVDM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "LNTRCVD 3333");
    }
    public void T02_UPDATE_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        IBS.init(SCCGWA, LNRRCVD);
        LNCRCVDM.FUNC = '4';
        LNRRCVD.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.SUBS_PROJ_NO = 0;
        LNRRCVD.KEY.AMT_TYP = WS_SV_AMT_TYP;
        LNRRCVD.KEY.TERM = WS_SV_TERM;
        CEP.TRC(SCCGWA, "LNTRCVD 1111");
        WS_RCVD_UPD_FLG = 'Y';
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == 0) {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND;
            WS_RCVD_UPD_FLG = 'N';
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCVDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_RCVD_UPD_FLG == 'Y') {
            CEP.TRC(SCCGWA, "LNTRCVD 2222");
            T02_SAV_LNTRCVD();
            if (pgmRtn) return;
            LNCRCVDM.FUNC = '2';
            LNRRCVD.REPY_STS = WS_REPY_STS;
            if (WS_SV_AMT_TYP == 'I') {
                WS_L_CALC = LNRRCVD.L_REC_AMT - LNRRCVD.L_PAY_AMT - LNRRCVD.L_WAV_AMT + LNRRCVD.L_LST_PST_AMT;
                WS_I_CALC = LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                CEP.TRC(SCCGWA, LNB6080_AWA_6080.CAL_AMT);
                CEP.TRC(SCCGWA, WS_L_CALC);
                CEP.TRC(SCCGWA, WS_BALL_L_AMT);
                if (LNB6080_AWA_6080.CAL_AMT <= WS_L_CALC) {
                    LNRRCVD.L_WAV_AMT += LNB6080_AWA_6080.CAL_AMT;
                    WS_BALL_L_AMT = LNB6080_AWA_6080.CAL_AMT;
                    CEP.TRC(SCCGWA, WS_BALL_L_AMT);
                } else {
                    WS_SV_CAL_AMT = WS_SV_CAL_AMT + LNB6080_AWA_6080.CAL_AMT - WS_L_CALC;
                    LNRRCVD.L_WAV_AMT += WS_L_CALC;
                    WS_BALL_L_AMT = WS_L_CALC;
                    CEP.TRC(SCCGWA, WS_BALL_L_AMT);
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    CEP.TRC(SCCGWA, WS_I_CALC);
                    CEP.TRC(SCCGWA, WS_BALL_I_AMT);
                    if (WS_SV_CAL_AMT <= WS_I_CALC) {
                        LNRRCVD.I_WAV_AMT += WS_SV_CAL_AMT;
                        WS_BALL_I_AMT = WS_SV_CAL_AMT;
                        WS_SV_CAL_AMT = 0;
                        CEP.TRC(SCCGWA, WS_BALL_I_AMT);
                        CEP.TRC(SCCGWA, LNRRCVD.I_WAV_AMT);
                    } else {
                        LNRRCVD.I_WAV_AMT += WS_I_CALC;
                        WS_BALL_I_AMT = WS_I_CALC;
                        WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_I_CALC;
                        CEP.TRC(SCCGWA, WS_BALL_I_AMT);
                        CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    }
                }
            }
            if (WS_SV_AMT_TYP == 'P') {
                WS_O_CALC = LNRRCVD.O_REC_AMT - LNRRCVD.O_PAY_AMT - LNRRCVD.O_WAV_AMT + LNRRCVD.O_LST_PST_AMT;
                WS_P_CALC = LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT - LNRRCVD.P_WAV_AMT;
                CEP.TRC(SCCGWA, "PPPPPPPP");
                CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                CEP.TRC(SCCGWA, WS_O_CALC);
                CEP.TRC(SCCGWA, WS_BALL_O_AMT);
                if (WS_SV_CAL_AMT <= WS_O_CALC) {
                    LNRRCVD.O_WAV_AMT += WS_SV_CAL_AMT;
                    WS_BALL_O_AMT = WS_SV_CAL_AMT;
                    WS_SV_CAL_AMT = 0;
                    CEP.TRC(SCCGWA, WS_BALL_O_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_WAV_AMT);
                } else {
                    WS_SV_CAL_AMT = WS_SV_CAL_AMT - WS_O_CALC;
                    LNRRCVD.O_WAV_AMT += WS_O_CALC;
                    WS_BALL_O_AMT += WS_O_CALC;
                    CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                    CEP.TRC(SCCGWA, LNRRCVD.O_WAV_AMT);
                    CEP.TRC(SCCGWA, WS_P_CALC);
                    CEP.TRC(SCCGWA, WS_BALL_P_AMT);
                    if (WS_SV_CAL_AMT < WS_P_CALC) {
                        LNRRCVD.P_WAV_AMT += WS_SV_CAL_AMT;
                        WS_BALL_P_AMT = WS_SV_CAL_AMT;
                        CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                        WS_SV_CAL_AMT = 0;
                        CEP.TRC(SCCGWA, WS_SV_CAL_AMT);
                        CEP.TRC(SCCGWA, WS_BALL_P_AMT);
                        CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
                    }
                }
            }
            if (WS_REPY_STS == '2') {
                JIBS_NumStr = "" + 0;
                LNRRCVD.TERM_STS = JIBS_NumStr.charAt(0);
            }
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
            CEP.TRC(SCCGWA, "LNTRCVD 44444");
            CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCVDM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "LNTRCVD 3333");
    }
    public void T01_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T02_SAV_LNTRCVD() throws IOException,SQLException,Exception {
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_CTA_NO = LNRRCVD.KEY.CONTRACT_NO;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_SUB_NO = LNRRCVD.KEY.SUB_CTA_NO;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_TERM = LNRRCVD.KEY.TERM;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_PROJ_NO = LNRRCVD.KEY.SUBS_PROJ_NO;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_TERM_STS = LNRRCVD.TERM_STS;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_REPY_STS = LNRRCVD.REPY_STS;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_P_REC_AMT = LNRRCVD.P_REC_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_P_PAY_AMT = LNRRCVD.P_PAY_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_P_WAV_AMT = LNRRCVD.P_WAV_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_I_REC_AMT = LNRRCVD.I_REC_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_I_PAY_AMT = LNRRCVD.I_PAY_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_I_WAV_AMT = LNRRCVD.I_WAV_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_O_REC_AMT = LNRRCVD.O_REC_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_O_PAY_AMT = LNRRCVD.O_PAY_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_O_WAV_AMT = LNRRCVD.O_WAV_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_L_REC_AMT = LNRRCVD.L_REC_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_L_PAY_AMT = LNRRCVD.L_PAY_AMT;
        WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_L_WAV_AMT = LNRRCVD.L_WAV_AMT;
        R010_WRITE_TRAN_DTL();
        if (pgmRtn) return;
    }
    public void T01_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.TERM = LNRRCVD.KEY.TERM;
        LNRPLPI.KEY.TERM += 1;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "REPY_MTH, TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T01_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNRPLPI.DUE_REPY_AMT);
        CEP.TRC(SCCGWA, LNRPLPI.PRIN_AMT);
        WS_SV_REPY_MTH = LNRPLPI.KEY.REPY_MTH;
        WS_SV_TERM = LNRPLPI.KEY.TERM;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
        }
    }
    public void T01_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
    }
    public void T01_UPDATE_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        IBS.init(SCCGWA, LNRPLPI);
        LNCPLPIM.FUNC = '4';
        LNRPLPI.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        if (WS_PAY_MTH == '1'
            || WS_PAY_MTH == '2'
            || WS_PAY_MTH == '3'
            || WS_PAY_MTH == '4'
            || WS_PAY_MTH == '5') {
            LNRPLPI.KEY.REPY_MTH = WS_SV_REPY_MTH;
            LNRPLPI.KEY.TERM = WS_SV_TERM;
        } else {
        }
        CEP.TRC(SCCGWA, "LNTPLPI 1111");
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.upd = true;
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T01_SAV_LNTPLPI();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPLPIM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LNTPLPI 2222");
        LNCPLPIM.FUNC = '2';
        LNRPLPI.DUE_REPY_AMT = WS_DUE_REPY_AMT;
        LNRPLPI.PRIN_AMT = WS_PLPI_PRIN_AMT;
        if (WS_REC_STS != LNRPLPI.REC_STS) {
            LNRPLPI.REC_STS = WS_REC_STS;
        }
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.REWRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
        CEP.TRC(SCCGWA, "LNTPLPI 44444");
        CEP.TRC(SCCGWA, LNRPLPI.DUE_REPY_AMT);
        CEP.TRC(SCCGWA, LNRPLPI.PRIN_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPLPIM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LNTPLPI 3333");
    }
    public void T01_SAV_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TR_DATA.WS_RCVD_DATA);
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_CTA_NO = LNRPLPI.KEY.CONTRACT_NO;
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_SUB_NO = LNRPLPI.KEY.SUB_CTA_NO;
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_REPY_MTH = LNRPLPI.KEY.REPY_MTH;
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_TERM = LNRPLPI.KEY.TERM;
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_REC_STS = LNRPLPI.REC_STS;
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_DUE_REPY_AMT = LNRPLPI.DUE_REPY_AMT;
        WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_PRIN = LNRPLPI.PRIN_AMT;
        R010_WRITE_TRAN_DTL();
        if (pgmRtn) return;
    }
    public void B440_REVERSE_PLPI() throws IOException,SQLException,Exception {
        if (WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_CTA_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNCPLPIM);
            IBS.init(SCCGWA, LNRPLPI);
            LNCPLPIM.FUNC = '4';
            LNRPLPI.KEY.CONTRACT_NO = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_CTA_NO;
            LNRPLPI.KEY.SUB_CTA_NO = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_SUB_NO;
            LNRPLPI.KEY.REPY_MTH = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_REPY_MTH;
            LNRPLPI.KEY.TERM = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_TERM;
            CEP.TRC(SCCGWA, "B440-REVERSE-PLPI");
            CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
            CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
            LNTPLPI_RD = new DBParm();
            LNTPLPI_RD.TableName = "LNTPLPI";
            LNTPLPI_RD.upd = true;
            IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
            LNCPLPIM.FUNC = '2';
            LNRPLPI.REC_STS = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_REC_STS;
            LNRPLPI.DUE_REPY_AMT = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_DUE_REPY_AMT;
            LNRPLPI.PRIN_AMT = WS_TR_DATA.WS_PLPI_DATA.WS_PLPI_PRIN;
            LNTPLPI_RD = new DBParm();
            LNTPLPI_RD.TableName = "LNTPLPI";
            IBS.REWRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
            CEP.TRC(SCCGWA, LNRPLPI.PRIN_AMT);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPLPIM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B430_REVERSE_RCVD() throws IOException,SQLException,Exception {
        if (WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_CTA_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNCRCVDM);
            IBS.init(SCCGWA, LNRRCVD);
            LNCRCVDM.FUNC = '4';
            LNRRCVD.KEY.CONTRACT_NO = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_SUB_NO;
            LNRRCVD.KEY.SUBS_PROJ_NO = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_PROJ_NO;
            LNRRCVD.KEY.AMT_TYP = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_AMT_TYP;
            LNRRCVD.KEY.TERM = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_TERM;
            CEP.TRC(SCCGWA, "B430-REVERSE-RCVD");
            CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.SUBS_PROJ_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            LNTRCVD_RD.upd = true;
            IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == 0) {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCVDM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            LNCRCVDM.FUNC = '2';
            LNRRCVD.TERM_STS = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_TERM_STS;
            LNRRCVD.REPY_STS = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_REPY_STS;
            LNRRCVD.P_REC_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_P_REC_AMT;
            LNRRCVD.P_PAY_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_P_PAY_AMT;
            LNRRCVD.P_WAV_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_P_WAV_AMT;
            LNRRCVD.I_REC_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_I_REC_AMT;
            LNRRCVD.I_PAY_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_I_PAY_AMT;
            LNRRCVD.I_WAV_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_I_WAV_AMT;
            LNRRCVD.O_REC_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_O_REC_AMT;
            LNRRCVD.O_PAY_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_O_PAY_AMT;
            LNRRCVD.O_WAV_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_O_WAV_AMT;
            LNRRCVD.L_REC_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_L_REC_AMT;
            LNRRCVD.L_PAY_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_L_PAY_AMT;
            LNRRCVD.L_WAV_AMT = WS_TR_DATA.WS_RCVD_DATA.WS_RCVD_L_WAV_AMT;
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
            CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.P_WAV_AMT);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCVDM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        if (LNCRSETL.RETURN_INFO != 'F' 
            && LNCRSETL.RETURN_INFO != 'E') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCRSETL.RETURN_INFO);
        CEP.TRC(SCCGWA, LNCRSETL.RC);
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 456;
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRFUND.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNTAGRE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AGRE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TRAN_REV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNB6080_AWA_6080.CONT_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = ' ';
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TXN_TYP < > :LNRTRAN.KEY.TXN_TYP "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO";
        LNTTRAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
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
            IBS.CPY2CLS(SCCGWA, LNRTRAN.TR_DATA, WS_TR_DATA);
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEV.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRTRAN() throws IOException,SQLException,Exception {
        LNCRTRAN.REC_PTR = LNRTRAN;
        LNCRTRAN.REC_LEN = 1035;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTTRAN", LNCRTRAN);
        if (LNCRTRAN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRTRAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = LNCAPRDM.RC.RC_APP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void T000_GROUP_CITACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = LNRAGRE.PAPER_NO;
        JIBS_NumStr = "" + 0;
        CIRACAC.ACAC_STS = JIBS_NumStr.charAt(0);
        CIRACAC.AID = " ";
        CIRACAC.FRM_APP = "LN";
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.set = "WS-COUNT-CNT=COUNT(*)";
        CITACAC_RD.where = "AGR_NO = :CIRACAC.AGR_NO "
            + "AND ACAC_STS = :CIRACAC.ACAC_STS";
        IBS.GROUP(SCCGWA, CIRACAC, this, CITACAC_RD);
        WS_TOTAL_NUM = WS_COUNT_CNT;
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
    }
    public void S000_CALL_BPZFCTRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-CTRT-MAINT", BPCCTRST);
        if (BPCCTRST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCTRST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAMDB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-AMOUNT-DISB", LNCAMDB);
        if (LNCAMDB.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAMDB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZURPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REPAY-NORMAL", LNCURPN);
        CEP.TRC(SCCGWA, LNCURPN.RC);
        if (LNCURPN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCURPN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
