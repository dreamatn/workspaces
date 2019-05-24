package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT3100 {
    boolean pgmRtn = false;
    int K_ZERO = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT3100_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT3100_WS_TEMP_VARIABLE();
    LNOT3100_WS_MSG_INFO WS_MSG_INFO = new LNOT3100_WS_MSG_INFO();
    LNOT3100_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNOT3100_WS_OUTPUT_LIST();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCENSCH LNCENSCH = new LNCENSCH();
    LNCMATIN LNCMATIN = new LNCMATIN();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPRPEND BPRPEND = new BPRPEND();
    BPCRPENM BPCRPENM = new BPCRPENM();
    SCCGWA SCCGWA;
    LNB3100_AWA_3100 LNB3100_AWA_3100;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT3100 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB3100_AWA_3100>");
        LNB3100_AWA_3100 = (LNB3100_AWA_3100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCENSCH.RC.RC_APP = "LN";
        LNCENSCH.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_MOVE_TO_INTERFACE();
        if (pgmRtn) return;
        if (LNB3100_AWA_3100.FUNC == 'C') {
            B400_REPY_PLAN_REDUCE_TODO();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB3100_AWA_3100.REPY_TYP == 'C') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_REPAY_TYPE;
            WS_FLD_NO = LNB3100_AWA_3100.REPY_TYP_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNB3100_AWA_3100.PFN_DAT > 0) 
            || (LNB3100_AWA_3100.PFR_TERM > 0) 
            || (LNB3100_AWA_3100.PFR_TYP > SPACE)) {
            if (LNB3100_AWA_3100.PFN_DAT <= 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB3100_AWA_3100.PFN_DAT_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB3100_AWA_3100.PFR_TERM <= 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB3100_AWA_3100.PFR_TERM_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB3100_AWA_3100.PFR_TYP <= SPACE) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB3100_AWA_3100.PFR_TYP_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((LNB3100_AWA_3100.IFN_DAT > 0) 
            || (LNB3100_AWA_3100.IFR_TERM > 0) 
            || (LNB3100_AWA_3100.IFR_TYP > SPACE)) {
            if (LNB3100_AWA_3100.IFN_DAT <= 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB3100_AWA_3100.PFN_DAT_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB3100_AWA_3100.IFR_TERM <= 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB3100_AWA_3100.PFR_TERM_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB3100_AWA_3100.IFR_TYP <= SPACE) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB3100_AWA_3100.PFR_TYP_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((LNB3100_AWA_3100.REPY_TYP == 'P') 
            && (LNB3100_AWA_3100.IFN_DAT > 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_ALLOW_INPUT;
            WS_FLD_NO = LNB3100_AWA_3100.IFN_DAT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNB3100_AWA_3100.REPY_TYP == 'I') 
            && (LNB3100_AWA_3100.PFN_DAT > 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_ALLOW_INPUT;
            WS_FLD_NO = LNB3100_AWA_3100.PFN_DAT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNB3100_AWA_3100.REPY_TYP == ' ') 
            && (LNB3100_AWA_3100.IFN_DAT > 0) 
            && (LNB3100_AWA_3100.IFR_TERM > 0) 
            && (LNB3100_AWA_3100.IFR_TYP > SPACE) 
            && (LNB3100_AWA_3100.PFR_TERM == 0) 
            && (LNB3100_AWA_3100.PFR_TYP == ' ') 
            && (LNB3100_AWA_3100.PFN_DAT == 0) 
            && (LNB3100_AWA_3100.REPY_AMT == 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
            WS_FLD_NO = LNB3100_AWA_3100.REPY_TYP_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNB3100_AWA_3100.REPY_TYP == ' ') 
            && (LNB3100_AWA_3100.IFN_DAT == 0) 
            && (LNB3100_AWA_3100.IFR_TERM == 0) 
            && (LNB3100_AWA_3100.IFR_TYP == ' ') 
            && (LNB3100_AWA_3100.PFR_TERM > 0) 
            && (LNB3100_AWA_3100.PFR_TYP > SPACE) 
            && (LNB3100_AWA_3100.PFN_DAT > 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
            WS_FLD_NO = LNB3100_AWA_3100.REPY_TYP_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB3100_AWA_3100.REPY_AMT < 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.E_LN_AMT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_MOVE_TO_INTERFACE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCMATIN);
        LNCMATIN.COMM_DATA.CTA_NO = LNB3100_AWA_3100.CTA_NO;
        LNCMATIN.COMM_DATA.CI_NO = LNB3100_AWA_3100.CI_NO;
        LNCMATIN.COMM_DATA.CI_S_NAM = LNB3100_AWA_3100.CI_S_NAM;
        LNCMATIN.COMM_DATA.PRD_TYP = LNB3100_AWA_3100.PRD_TYP;
        LNCMATIN.COMM_DATA.CCY = LNB3100_AWA_3100.CCY;
        LNCMATIN.COMM_DATA.PRIN_AMT = LNB3100_AWA_3100.PRIN_AMT;
        LNCMATIN.COMM_DATA.OS_BAL = LNB3100_AWA_3100.OS_BAL;
        LNCMATIN.COMM_DATA.REPY_TYP = LNB3100_AWA_3100.REPY_TYP;
        LNCMATIN.COMM_DATA.REPY_AMT = LNB3100_AWA_3100.REPY_AMT;
        LNCMATIN.COMM_DATA.PFN_DAT = LNB3100_AWA_3100.PFN_DAT;
        LNCMATIN.COMM_DATA.PFR_TERM = LNB3100_AWA_3100.PFR_TERM;
        LNCMATIN.COMM_DATA.PFR_TYP = LNB3100_AWA_3100.PFR_TYP;
        LNCMATIN.COMM_DATA.IFN_DAT = LNB3100_AWA_3100.IFN_DAT;
        LNCMATIN.COMM_DATA.SUB_CTA = LNB3100_AWA_3100.SUB_CTA;
        LNCMATIN.COMM_DATA.IFR_TERM = LNB3100_AWA_3100.IFR_TERM;
        LNCMATIN.COMM_DATA.IFR_TYP = LNB3100_AWA_3100.IFR_TYP;
        LNCMATIN.COMM_DATA.ALLIN_RA = LNB3100_AWA_3100.ALLIN_RA;
        LNCMATIN.COMM_DATA.TERM_NO = LNB3100_AWA_3100.TERM_NO;
        LNCMATIN.COMM_DATA.DUE_DAT = LNB3100_AWA_3100.DUE_DAT;
        LNCMATIN.COMM_DATA.VAL_DAT = LNB3100_AWA_3100.VAL_DAT;
        LNCMATIN.COMM_DATA.PBANK_NA = LNB3100_AWA_3100.PBANK_NA;
        LNCMATIN.COMM_DATA.P_CITYCD = LNB3100_AWA_3100.P_CITYCD;
        LNCMATIN.COMM_DATA.RMK = LNB3100_AWA_3100.RMK;
        LNCMATIN.COMM_DATA.PAY_AMTN = LNB3100_AWA_3100.PAY_AMTN;
        LNCMATIN.COMM_DATA.PAY_AMTO = LNB3100_AWA_3100.PAY_AMTO;
        LNCMATIN.COMM_DATA.TRAN_SEQ = LNB3100_AWA_3100.TRAN_SEQ;
        LNCMATIN.COMM_DATA.LIST_TYP = LNB3100_AWA_3100.LIST_TYP;
        LNCMATIN.COMM_DATA.RATE = LNB3100_AWA_3100.RATE;
        CEP.TRC(SCCGWA, LNB3100_AWA_3100.RATE);
        CEP.TRC(SCCGWA, LNCMATIN.COMM_DATA.RATE);
        LNCMATIN.COMM_DATA.CI_CNM = LNB3100_AWA_3100.CI_CNM;
        LNCMATIN.COMM_DATA.FUNC = LNB3100_AWA_3100.FUNC;
        LNCMATIN.COMM_DATA.REF_SEQ = LNB3100_AWA_3100.REF_SEQ;
        LNCMATIN.COMM_DATA.PAGE_ROW = LNB3100_AWA_3100.PAGE_ROW;
        LNCMATIN.COMM_DATA.PAGE_NUM = LNB3100_AWA_3100.PAGE_NUM;
        LNCMATIN.COMM_DATA.CONF_FLG = LNB3100_AWA_3100.CONF_FLG;
        S000_CALL_LNZMATIN();
        if (pgmRtn) return;
    }
    public void B400_REPY_PLAN_REDUCE_TODO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRPEND.KEY.BUSS_TYP = "LN";
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.FUNC = 'Q';
        BPCRPENM.INFO.LEN = 22;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
        if (BPCRPENM.RETURN_INFO == 'F') {
            IBS.init(SCCGWA, BPCFTLPM);
            BPCFTLPM.OP_CODE = 'D';
            BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
            if ("1".trim().length() == 0) BPCFTLPM.TX_COUNT = 0;
            else BPCFTLPM.TX_COUNT = Short.parseShort("1");
            BPCFTLPM.BUSS_TYP = "LN";
            S000_CALL_BPZFTLPM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "REPY-PLAN-MAINTAIN-COMPLETE");
        } else {
            CEP.TRC(SCCGWA, "NO TODO");
        }
    }
    public void S000_CALL_BPZRPENM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-PEND-MAINTAIN", BPCRPENM);
        if (BPCRPENM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRPENM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZMATIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-MATIN-N", LNCMATIN);
        CEP.TRC(SCCGWA, LNCMATIN.RC);
        if (LNCMATIN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCMATIN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
