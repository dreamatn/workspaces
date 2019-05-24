package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class VTZSOTAX {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_BR_CNT = 0;
    int WS_BR_NOW = 0;
    char WS_BR_AUTH_NOW = ' ';
    short WS_I = 0;
    VTZSOTAX_WS_OUTPUT WS_OUTPUT = new VTZSOTAX_WS_OUTPUT();
    VTZSOTAX_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSOTAX_WS_BROWSE_OUTPUT();
    VTZSOTAX_WS_BR_TLR_INFO[] WS_BR_TLR_INFO = new VTZSOTAX_WS_BR_TLR_INFO[100];
    char WS_FND_FLG = ' ';
    char WS_SEARCH_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTROTAX VTROTAX = new VTROTAX();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTCROTAX VTCROTAX = new VTCROTAX();
    VTRHTAX VTRHTAX = new VTRHTAX();
    VTCRHTAX VTCRHTAX = new VTCRHTAX();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSOTAX VTCSOTAX;
    public VTZSOTAX() {
        for (int i=0;i<100;i++) WS_BR_TLR_INFO[i] = new VTZSOTAX_WS_BR_TLR_INFO();
    }
    public void MP(SCCGWA SCCGWA, VTCSOTAX VTCSOTAX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSOTAX = VTCSOTAX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSOTAX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSOTAX.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSOTAX.FUNC == 'Q') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCSOTAX.VAT_TYPE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 243;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        if (VTCSOTAX.START_DT != 0 
            && VTCSOTAX.START_DT < SCCGWA.COMM_AREA.AC_DATE) {
            if (VTCSOTAX.END_DT < SCCGWA.COMM_AREA.AC_DATE) {
                VTCRHTAX.START_DATE = VTCSOTAX.START_DT;
                VTCRHTAX.END_DATE = VTCSOTAX.END_DT;
                B030_BROWSE_HTAX_PROC();
                if (pgmRtn) return;
            } else {
                VTCRHTAX.START_DATE = VTCSOTAX.START_DT;
                VTCRHTAX.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                B030_BROWSE_HTAX_PROC();
                if (pgmRtn) return;
                VTCROTAX.START_DATE = SCCGWA.COMM_AREA.AC_DATE;
                VTCROTAX.END_DATE = VTCSOTAX.END_DT;
                B030_BROWSE_OTAX_PROC();
                if (pgmRtn) return;
            }
        } else {
            VTCROTAX.START_DATE = VTCSOTAX.START_DT;
            VTCROTAX.END_DATE = VTCSOTAX.END_DT;
            B030_BROWSE_OTAX_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_BROWSE_HTAX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRHTAX);
        VTCRHTAX.FUNC = 'B';
        VTCRHTAX.OPT = 'S';
        VTRHTAX.KEY.SET_NO = VTCSOTAX.SET_NO;
        VTRHTAX.KEY.SET_SEQ = VTCSOTAX.SET_SEQ;
        VTRHTAX.BR = VTCSOTAX.BR;
        VTRHTAX.BILL_NO = VTCSOTAX.BILL_NO;
        VTRHTAX.PROD_CD = VTCSOTAX.PROD_CD;
        VTRHTAX.ITM = VTCSOTAX.ITM;
        VTRHTAX.CI_NO = VTCSOTAX.CI_NO;
        VTRHTAX.CCY = VTCSOTAX.CCY;
        VTRHTAX.YJ_OBAL = VTCSOTAX.AMT;
        VTRHTAX.VAT_TYPE = VTCSOTAX.VAT_TYPE;
        VTCRHTAX.POINTER = VTRHTAX;
        VTCRHTAX.REC_LEN = 445;
        S000_CALL_VTZRHTAX();
        if (pgmRtn) return;
        VTCRHTAX.OPT = 'W';
        VTCRHTAX.POINTER = VTRHTAX;
        VTCRHTAX.REC_LEN = 445;
        S000_CALL_VTZRHTAX();
        if (pgmRtn) return;
        while (VTCRHTAX.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R00_BRW_OUTPUT_H();
            if (pgmRtn) return;
            VTCRHTAX.OPT = 'W';
            S000_CALL_VTZRHTAX();
            if (pgmRtn) return;
        }
        VTCRHTAX.OPT = 'E';
        S000_CALL_VTZRHTAX();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_OTAX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTROTAX);
        VTCROTAX.FUNC = 'B';
        VTCROTAX.OPT = 'S';
        VTROTAX.KEY.AC_DATE = VTCSOTAX.START_DT;
        VTROTAX.KEY.SET_NO = VTCSOTAX.SET_NO;
        VTROTAX.KEY.SET_SEQ = VTCSOTAX.SET_SEQ;
        VTROTAX.BR = VTCSOTAX.BR;
        VTROTAX.BILL_NO = VTCSOTAX.BILL_NO;
        VTROTAX.PROD_CD = VTCSOTAX.PROD_CD;
        VTROTAX.ITM = VTCSOTAX.ITM;
        VTROTAX.CI_NO = VTCSOTAX.CI_NO;
        VTROTAX.CCY = VTCSOTAX.CCY;
        VTROTAX.YJ_OBAL = VTCSOTAX.AMT;
        VTROTAX.VAT_TYPE = VTCSOTAX.VAT_TYPE;
        CEP.TRC(SCCGWA, VTCSOTAX.SPC_FLG);
        VTCROTAX.POINTER = VTROTAX;
        VTCROTAX.REC_LEN = 445;
        S000_CALL_VTZROTAX();
        if (pgmRtn) return;
        VTCROTAX.OPT = 'W';
        VTCROTAX.POINTER = VTROTAX;
        VTCROTAX.REC_LEN = 445;
        S000_CALL_VTZROTAX();
        if (pgmRtn) return;
        while (VTCROTAX.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCROTAX.OPT = 'W';
            S000_CALL_VTZROTAX();
            if (pgmRtn) return;
        }
        VTCROTAX.OPT = 'E';
        S000_CALL_VTZROTAX();
        if (pgmRtn) return;
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        if (VTCSOTAX.AC_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || VTCSOTAX.AC_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, VTROTAX);
            IBS.init(SCCGWA, VTCROTAX);
            VTROTAX.KEY.AC_DATE = VTCSOTAX.AC_DATE;
            VTROTAX.KEY.SET_NO = VTCSOTAX.SET_NO;
            VTROTAX.KEY.SET_SEQ = VTCSOTAX.SET_SEQ;
            VTCROTAX.FUNC = 'Q';
            VTCROTAX.POINTER = VTROTAX;
            VTCROTAX.REC_LEN = 445;
            S000_CALL_VTZROTAX();
            if (pgmRtn) return;
            if (VTCROTAX.RETURN_INFO == 'N') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OTAX_REC_NOT_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_OUTPUT.WS_DT = VTROTAX.KEY.AC_DATE;
            WS_OUTPUT.WS_SET_NO = VTROTAX.KEY.SET_NO;
            WS_OUTPUT.WS_SET_SEQ = VTROTAX.KEY.SET_SEQ;
            WS_OUTPUT.WS_CHNL_NO = VTROTAX.CHNL_NO;
            WS_OUTPUT.WS_TR_BR = VTROTAX.TR_BR;
            WS_OUTPUT.WS_BR = VTROTAX.BR;
            WS_OUTPUT.WS_PROD_CD = VTROTAX.PROD_CD;
            WS_OUTPUT.WS_ITM = VTROTAX.ITM;
            WS_OUTPUT.WS_AC_SEQ = VTROTAX.AC_SEQ;
            WS_OUTPUT.WS_CI_NO = VTROTAX.CI_NO;
            WS_OUTPUT.WS_CCY = VTROTAX.CCY;
            WS_OUTPUT.WS_YJ_OBAL = VTROTAX.YJ_OBAL;
            WS_OUTPUT.WS_YJ_BAL = VTROTAX.YJ_BAL;
            WS_OUTPUT.WS_YJ_TAX_BAL = VTROTAX.YJ_TAX_BAL;
            WS_OUTPUT.WS_MEMO_OBAL = VTROTAX.M_INCOME_OBAL;
            WS_OUTPUT.WS_MEMO_BAL = VTROTAX.M_INCOME_BAL;
            WS_OUTPUT.WS_MEMO_TAX_BAL = VTROTAX.M_TAX_BAL;
            WS_OUTPUT.WS_SH_OBAL = VTROTAX.SH_OBAL;
            WS_OUTPUT.WS_SH_BAL = VTROTAX.SH_BAL;
            WS_OUTPUT.WS_SH_TAX_BAL = VTROTAX.SH_TAX_BAL;
            WS_OUTPUT.WS_CODE = VTROTAX.CODE;
            WS_OUTPUT.WS_VAT_TYPE = VTROTAX.VAT_TYPE;
            WS_OUTPUT.WS_RATE = VTROTAX.RT;
            WS_OUTPUT.WS_EX_RATE = VTROTAX.EX_RAT;
            WS_OUTPUT.WS_BILL_NO = VTROTAX.BILL_NO;
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_BILL_NO);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_DT);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_SET_NO);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_SET_SEQ);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_CHNL_NO);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_TR_BR);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_BR);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_PROD_CD);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_ITM);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_AC_SEQ);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_CI_NO);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_CCY);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_YJ_OBAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_YJ_BAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_YJ_TAX_BAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_MEMO_OBAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_MEMO_BAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_MEMO_TAX_BAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_SH_OBAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_SH_BAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_SH_TAX_BAL);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_CODE);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_RATE);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_EX_RATE);
            WS_OUTPUT.WS_BILL_FLG = VTROTAX.BILL_FLG;
            WS_OUTPUT.WS_SPC_FLG = VTROTAX.SPC_TR_FLG;
            WS_OUTPUT.WS_EC_DATE = VTROTAX.EC_DATE;
            WS_OUTPUT.WS_EC_SET_NO = VTROTAX.EC_SET_NO;
            if (VTROTAX.TR_DATE != VTROTAX.KEY.AC_DATE 
                && !VTROTAX.TR_SET_NO.equalsIgnoreCase(VTROTAX.KEY.SET_NO)) {
                WS_OUTPUT.WS_TR_DATE = VTROTAX.TR_DATE;
                WS_OUTPUT.WS_TR_SET_NO = VTROTAX.TR_SET_NO;
            }
        } else {
            IBS.init(SCCGWA, VTRHTAX);
            IBS.init(SCCGWA, VTCRHTAX);
            VTRHTAX.KEY.AC_DATE = VTCSOTAX.AC_DATE;
            VTRHTAX.KEY.SET_NO = VTCSOTAX.SET_NO;
            VTRHTAX.KEY.SET_SEQ = VTCSOTAX.SET_SEQ;
            VTCRHTAX.FUNC = 'Q';
            VTCROTAX.POINTER = VTROTAX;
            VTCROTAX.REC_LEN = 445;
            S000_CALL_VTZRHTAX();
            if (pgmRtn) return;
            if (VTCRHTAX.RETURN_INFO == 'N') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OTAX_REC_NOT_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_OUTPUT.WS_DT = VTRHTAX.KEY.AC_DATE;
            WS_OUTPUT.WS_SET_NO = VTRHTAX.KEY.SET_NO;
            WS_OUTPUT.WS_SET_SEQ = VTRHTAX.KEY.SET_SEQ;
            WS_OUTPUT.WS_CHNL_NO = VTRHTAX.CHNL_NO;
            WS_OUTPUT.WS_TR_BR = VTRHTAX.TR_BR;
            WS_OUTPUT.WS_BR = VTRHTAX.BR;
            WS_OUTPUT.WS_PROD_CD = VTRHTAX.PROD_CD;
            WS_OUTPUT.WS_ITM = VTRHTAX.ITM;
            WS_OUTPUT.WS_AC_SEQ = VTRHTAX.AC_SEQ;
            WS_OUTPUT.WS_CI_NO = VTRHTAX.CI_NO;
            WS_OUTPUT.WS_CCY = VTRHTAX.CCY;
            WS_OUTPUT.WS_YJ_OBAL = VTRHTAX.YJ_OBAL;
            WS_OUTPUT.WS_YJ_BAL = VTRHTAX.YJ_BAL;
            WS_OUTPUT.WS_YJ_TAX_BAL = VTRHTAX.YJ_TAX_BAL;
            WS_OUTPUT.WS_MEMO_OBAL = VTRHTAX.M_INCOME_OBAL;
            WS_OUTPUT.WS_MEMO_BAL = VTRHTAX.M_INCOME_BAL;
            WS_OUTPUT.WS_MEMO_TAX_BAL = VTRHTAX.M_TAX_BAL;
            WS_OUTPUT.WS_SH_OBAL = VTRHTAX.SH_OBAL;
            WS_OUTPUT.WS_SH_BAL = VTRHTAX.SH_BAL;
            WS_OUTPUT.WS_SH_TAX_BAL = VTRHTAX.SH_TAX_BAL;
            WS_OUTPUT.WS_CODE = VTRHTAX.CODE;
            WS_OUTPUT.WS_VAT_TYPE = VTRHTAX.VAT_TYPE;
            WS_OUTPUT.WS_RATE = VTRHTAX.RT;
            WS_OUTPUT.WS_EX_RATE = VTRHTAX.EX_RAT;
            WS_OUTPUT.WS_BILL_NO = VTRHTAX.BILL_NO;
            WS_OUTPUT.WS_BILL_FLG = VTRHTAX.BILL_FLG;
            WS_OUTPUT.WS_SPC_FLG = VTRHTAX.SPC_TR_FLG;
            WS_OUTPUT.WS_EC_DATE = VTRHTAX.EC_DATE;
            WS_OUTPUT.WS_EC_SET_NO = VTRHTAX.EC_SET_NO;
            if (VTRHTAX.TR_DATE != VTRHTAX.KEY.AC_DATE 
                && !VTRHTAX.TR_SET_NO.equalsIgnoreCase(VTRHTAX.KEY.SET_NO)) {
                WS_OUTPUT.WS_TR_DATE = VTRHTAX.TR_DATE;
                WS_OUTPUT.WS_TR_SET_NO = VTRHTAX.TR_SET_NO;
            }
        }
        SCCFMT.FMTID = "VT050";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 396;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        WS_SEARCH_FLG = 'N';
        for (WS_I = 1; WS_SEARCH_FLG != 'Y' 
            && WS_I <= WS_BR_CNT; WS_I += 1) {
            if (WS_BR_TLR_INFO[WS_I-1].WS_BR_TLR == VTROTAX.BR) {
                CEP.TRC(SCCGWA, "FOUND");
                WS_SEARCH_FLG = 'Y';
                WS_BR_NOW = WS_BR_TLR_INFO[WS_I-1].WS_BR_TLR;
                WS_BR_AUTH_NOW = WS_BR_TLR_INFO[WS_I-1].WS_BR_AUTH;
            }
        }
        if (WS_SEARCH_FLG == 'N') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_BR_CNT += 1;
            IBS.init(SCCGWA, BPCFTLCM);
            BPCFTLCM.BR = VTROTAX.BR;
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZFTLCM();
            if (pgmRtn) return;
            WS_BR_NOW = BPCFTLCM.BR;
            WS_BR_TLR_INFO[WS_BR_CNT-1].WS_BR_TLR = BPCFTLCM.BR;
            WS_BR_AUTH_NOW = BPCFTLCM.AUTH_FLG;
            WS_BR_TLR_INFO[WS_BR_CNT-1].WS_BR_AUTH = BPCFTLCM.AUTH_FLG;
        }
        if (WS_BR_AUTH_NOW == 'N' 
            && VTCSOTAX.SET_NO.trim().length() > 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_BR_AUTH_NOW == 'Y') {
            IBS.init(SCCGWA, SCCMPAG);
            IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
            WS_BROWSE_OUTPUT.WS_BRW_DATE = VTROTAX.KEY.AC_DATE;
            WS_BROWSE_OUTPUT.WS_BRW_SET_NO = VTROTAX.KEY.SET_NO;
            WS_BROWSE_OUTPUT.WS_BRW_SET_SEQ = VTROTAX.KEY.SET_SEQ;
            WS_BROWSE_OUTPUT.WS_BRW_PROD_CD = VTROTAX.PROD_CD;
            WS_BROWSE_OUTPUT.WS_BRW_ITM = VTROTAX.ITM;
            WS_BROWSE_OUTPUT.WS_BRW_CI_NO = VTROTAX.CI_NO;
            WS_BROWSE_OUTPUT.WS_BRW_CCY = VTROTAX.CCY;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_CCY);
            WS_BROWSE_OUTPUT.WS_BRW_YJ_AMT = VTROTAX.YJ_OBAL;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_YJ_AMT);
            WS_BROWSE_OUTPUT.WS_BRW_MEMO_AMT = VTROTAX.M_INCOME_OBAL;
            WS_BROWSE_OUTPUT.WS_BRW_SH_AMT = VTROTAX.SH_OBAL;
            WS_BROWSE_OUTPUT.WS_BRW_CODE = VTROTAX.CODE;
            WS_BROWSE_OUTPUT.WS_BRW_VAT_TYPE = VTROTAX.VAT_TYPE;
            WS_BROWSE_OUTPUT.WS_BRW_RATE = VTROTAX.RT;
            WS_BROWSE_OUTPUT.WS_BRW_BILL_NO = VTROTAX.BILL_NO;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_BILL_NO);
            WS_BROWSE_OUTPUT.WS_BRW_EC_DATE = VTROTAX.EC_DATE;
            WS_BROWSE_OUTPUT.WS_BRW_EC_SET_NO = VTROTAX.EC_SET_NO;
            if (VTROTAX.TR_DATE != VTROTAX.KEY.AC_DATE 
                && !VTROTAX.TR_SET_NO.equalsIgnoreCase(VTROTAX.KEY.SET_NO)) {
                WS_BROWSE_OUTPUT.WS_BRW_TR_DATE = VTROTAX.TR_DATE;
                WS_BROWSE_OUTPUT.WS_BRW_TR_SET_NO = VTROTAX.TR_SET_NO;
            }
            WS_BROWSE_OUTPUT.WS_BRW_SPC_FLG = VTROTAX.SPC_TR_FLG;
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
            SCCMPAG.DATA_LEN = 243;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R00_BRW_OUTPUT_H() throws IOException,SQLException,Exception {
        WS_SEARCH_FLG = 'N';
        CEP.TRC(SCCGWA, WS_BR_CNT);
        CEP.TRC(SCCGWA, WS_I);
        for (WS_I = 1; WS_SEARCH_FLG != 'Y' 
            && WS_I <= WS_BR_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, "XUNHUAN");
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_BR_CNT);
            if (WS_BR_TLR_INFO[WS_I-1].WS_BR_TLR == VTRHTAX.BR) {
                CEP.TRC(SCCGWA, "FOUND");
                WS_SEARCH_FLG = 'Y';
                WS_BR_NOW = WS_BR_TLR_INFO[WS_I-1].WS_BR_TLR;
                WS_BR_AUTH_NOW = WS_BR_TLR_INFO[WS_I-1].WS_BR_AUTH;
            }
        }
        if (WS_SEARCH_FLG == 'N') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_BR_CNT += 1;
            IBS.init(SCCGWA, BPCFTLCM);
            BPCFTLCM.BR = VTRHTAX.BR;
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZFTLCM();
            if (pgmRtn) return;
            WS_BR_NOW = BPCFTLCM.BR;
            WS_BR_TLR_INFO[WS_BR_CNT-1].WS_BR_TLR = BPCFTLCM.BR;
            WS_BR_AUTH_NOW = BPCFTLCM.AUTH_FLG;
            WS_BR_TLR_INFO[WS_BR_CNT-1].WS_BR_AUTH = BPCFTLCM.AUTH_FLG;
        }
        if (WS_BR_AUTH_NOW == 'N' 
            && VTCSOTAX.SET_NO.trim().length() > 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_BR_AUTH_NOW == 'Y') {
            IBS.init(SCCGWA, SCCMPAG);
            IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
            WS_BROWSE_OUTPUT.WS_BRW_DATE = VTRHTAX.KEY.AC_DATE;
            WS_BROWSE_OUTPUT.WS_BRW_SET_NO = VTRHTAX.KEY.SET_NO;
            WS_BROWSE_OUTPUT.WS_BRW_SET_SEQ = VTRHTAX.KEY.SET_SEQ;
            WS_BROWSE_OUTPUT.WS_BRW_PROD_CD = VTRHTAX.PROD_CD;
            WS_BROWSE_OUTPUT.WS_BRW_ITM = VTRHTAX.ITM;
            WS_BROWSE_OUTPUT.WS_BRW_CI_NO = VTRHTAX.CI_NO;
            WS_BROWSE_OUTPUT.WS_BRW_CCY = VTRHTAX.CCY;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_CCY);
            WS_BROWSE_OUTPUT.WS_BRW_YJ_AMT = VTRHTAX.YJ_OBAL;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_YJ_AMT);
            WS_BROWSE_OUTPUT.WS_BRW_MEMO_AMT = VTRHTAX.M_INCOME_OBAL;
            WS_BROWSE_OUTPUT.WS_BRW_SH_AMT = VTRHTAX.SH_OBAL;
            WS_BROWSE_OUTPUT.WS_BRW_CODE = VTRHTAX.CODE;
            WS_BROWSE_OUTPUT.WS_BRW_VAT_TYPE = VTRHTAX.VAT_TYPE;
            WS_BROWSE_OUTPUT.WS_BRW_RATE = VTRHTAX.RT;
            WS_BROWSE_OUTPUT.WS_BRW_BILL_NO = VTRHTAX.BILL_NO;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_BILL_NO);
            WS_BROWSE_OUTPUT.WS_BRW_EC_DATE = VTRHTAX.EC_DATE;
            WS_BROWSE_OUTPUT.WS_BRW_EC_SET_NO = VTRHTAX.EC_SET_NO;
            if (VTRHTAX.TR_DATE != VTRHTAX.KEY.AC_DATE 
                && !VTRHTAX.TR_SET_NO.equalsIgnoreCase(VTRHTAX.KEY.SET_NO)) {
                WS_BROWSE_OUTPUT.WS_BRW_TR_DATE = VTRHTAX.TR_DATE;
                WS_BROWSE_OUTPUT.WS_BRW_TR_SET_NO = VTRHTAX.TR_SET_NO;
            }
            WS_BROWSE_OUTPUT.WS_BRW_SPC_FLG = VTRHTAX.SPC_TR_FLG;
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
            SCCMPAG.DATA_LEN = 243;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZROTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-OTAX-MAINTAIN", VTCROTAX);
    }
    public void S000_CALL_VTZRHTAX() throws IOException,SQLException,Exception {
        VTCRHTAX.POINTER = VTRHTAX;
        VTCRHTAX.REC_LEN = 445;
        IBS.CALLCPN(SCCGWA, "VT-R-HTAX-MAINTAIN", VTCRHTAX);
        if (VTCRHTAX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, VTCRHTAX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
