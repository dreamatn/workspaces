package com.hisun.VT;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class VTOT7010 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    VTOT7010_WS_OUT_DATA WS_OUT_DATA = new VTOT7010_WS_OUT_DATA();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    VTCROTAX VTCROTAX = new VTCROTAX();
    VTROTAX VTROTAX = new VTROTAX();
    VTCRHTAX VTCRHTAX = new VTCRHTAX();
    VTRHTAX VTRHTAX = new VTRHTAX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCI7010 VTCI7010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTOT7010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        VTCI7010 = new VTCI7010();
        IBS.init(SCCGWA, VTCI7010);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, VTCI7010);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCI7010.TR_DATE);
        CEP.TRC(SCCGWA, VTCI7010.SET_NO);
        if (VTCI7010.TR_DATE == 0) {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (VTCI7010.SET_NO.trim().length() == 0) {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (VTCI7010.TR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, VTCROTAX);
            IBS.init(SCCGWA, VTROTAX);
            VTCROTAX.FUNC = 'B';
            VTCROTAX.OPT = 'O';
            VTROTAX.TR_DATE = VTCI7010.TR_DATE;
            VTROTAX.KEY.SET_NO = VTCI7010.SET_NO;
            CEP.TRC(SCCGWA, VTROTAX.TR_DATE);
            CEP.TRC(SCCGWA, VTROTAX.TR_DATE);
            CEP.TRC(SCCGWA, VTROTAX.TR_SET_NO);
            CEP.TRC(SCCGWA, VTROTAX.KEY.SET_NO);
            CEP.TRC(SCCGWA, VTROTAX.CHNL_NO);
            S000_CALL_VTZROTAX();
            VTCROTAX.OPT = 'W';
            S000_CALL_VTZROTAX();
            WS_CNT = 0;
            IBS.init(SCCGWA, SCCFMT);
            IBS.init(SCCGWA, WS_OUT_DATA);
            SCCFMT.FMTID = "VT710";
            for (WS_CNT = 1; WS_CNT <= 20 
                && VTCROTAX.RETURN_INFO != 'N'; WS_CNT += 1) {
                CEP.TRC(SCCGWA, WS_CNT);
                WS_OUT_DATA.WS_NUM += 1;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_AC_DATE = VTROTAX.KEY.AC_DATE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SET_NO = VTROTAX.KEY.SET_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SET_SEQ = VTROTAX.KEY.SET_SEQ;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_CHNL_NO = VTROTAX.CHNL_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TR_BR = VTROTAX.TR_BR;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_BR = VTROTAX.BR;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_AC_SEQ = VTROTAX.AC_SEQ;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_ITM = VTROTAX.ITM;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_PROD_CD = VTROTAX.PROD_CD;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_CCY = VTROTAX.CCY;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_CI_NO = VTROTAX.CI_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_YJ_BAL = VTROTAX.YJ_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_YJ_TAX_BAL = VTROTAX.YJ_TAX_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SH_BAL = VTROTAX.SH_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SH_TAX_BAL = VTROTAX.SH_TAX_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_RT = VTROTAX.RT;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_BILL_FLG = VTROTAX.BILL_FLG;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TAX_FLG = VTROTAX.TAX_FLG;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TAX_TYPE = VTROTAX.TAX_TYPE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_FREE_TYPE = VTROTAX.FREE_TYPE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_BILL_LIM = VTROTAX.BILL_LIM;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_EC_SET_NO = VTROTAX.EC_SET_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_EC_DATE = VTROTAX.EC_DATE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TR_DATE = VTROTAX.TR_DATE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TR_SET_NO = VTROTAX.TR_SET_NO;
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_NUM);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SET_NO);
                VTCROTAX.OPT = 'W';
                S000_CALL_VTZROTAX();
            }
            VTCROTAX.OPT = 'E';
            S000_CALL_VTZROTAX();
            SCCFMT.DATA_PTR = WS_OUT_DATA;
            SCCFMT.DATA_LEN = 4466;
            IBS.FMT(SCCGWA, SCCFMT);
        }
        if (VTCI7010.TR_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, VTCRHTAX);
            IBS.init(SCCGWA, VTRHTAX);
            VTCRHTAX.FUNC = 'B';
            VTCRHTAX.OPT = 'O';
            VTRHTAX.TR_DATE = VTCI7010.TR_DATE;
            VTRHTAX.KEY.SET_NO = VTCI7010.SET_NO;
            CEP.TRC(SCCGWA, VTRHTAX.TR_DATE);
            CEP.TRC(SCCGWA, VTRHTAX.TR_SET_NO);
            CEP.TRC(SCCGWA, VTRHTAX.CHNL_NO);
            S000_CALL_VTZRHTAX();
            VTCRHTAX.OPT = 'W';
            S000_CALL_VTZRHTAX();
            WS_CNT = 0;
            IBS.init(SCCGWA, SCCFMT);
            IBS.init(SCCGWA, WS_OUT_DATA);
            SCCFMT.FMTID = "VT710";
            for (WS_CNT = 1; WS_CNT <= 20 
                && VTCRHTAX.RETURN_INFO != 'N'; WS_CNT += 1) {
                WS_OUT_DATA.WS_NUM += 1;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_AC_DATE = VTRHTAX.KEY.AC_DATE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SET_NO = VTRHTAX.KEY.SET_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SET_SEQ = VTRHTAX.KEY.SET_SEQ;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_CHNL_NO = VTRHTAX.CHNL_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TR_BR = VTRHTAX.TR_BR;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_BR = VTRHTAX.BR;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_AC_SEQ = VTRHTAX.AC_SEQ;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_ITM = VTRHTAX.ITM;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_PROD_CD = VTRHTAX.PROD_CD;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_CCY = VTRHTAX.CCY;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_CI_NO = VTRHTAX.CI_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_YJ_BAL = VTRHTAX.YJ_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_YJ_TAX_BAL = VTRHTAX.YJ_TAX_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SH_BAL = VTRHTAX.SH_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SH_TAX_BAL = VTRHTAX.SH_TAX_BAL;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_RT = VTRHTAX.RT;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_BILL_FLG = VTRHTAX.BILL_FLG;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TAX_FLG = VTRHTAX.TAX_FLG;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TAX_TYPE = VTRHTAX.TAX_TYPE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_FREE_TYPE = VTRHTAX.FREE_TYPE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_BILL_LIM = VTRHTAX.BILL_LIM;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_EC_SET_NO = VTRHTAX.EC_SET_NO;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_EC_DATE = VTRHTAX.EC_DATE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TR_DATE = VTRHTAX.TR_DATE;
                WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_TR_SET_NO = VTRHTAX.TR_SET_NO;
                VTCRHTAX.OPT = 'W';
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_NUM);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CCY_INFO[WS_CNT-1].WS_SET_NO);
                S000_CALL_VTZRHTAX();
            }
            VTCRHTAX.OPT = 'E';
            S000_CALL_VTZRHTAX();
            SCCFMT.DATA_PTR = WS_OUT_DATA;
            SCCFMT.DATA_LEN = 4466;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void S000_CALL_VTZROTAX() throws IOException,SQLException,Exception {
        VTCROTAX.POINTER = VTROTAX;
        VTCROTAX.REC_LEN = 445;
        IBS.CALLCPN(SCCGWA, "VT-R-OTAX-MAINTAIN", VTCROTAX);
        if (VTCROTAX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, VTCROTAX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_VTZRHTAX() throws IOException,SQLException,Exception {
        VTCRHTAX.POINTER = VTRHTAX;
        VTCRHTAX.REC_LEN = 445;
        CEP.TRC(SCCGWA, VTRHTAX.TR_DATE);
        IBS.CALLCPN(SCCGWA, "VT-R-HTAX-MAINTAIN", VTCRHTAX);
        if (VTCRHTAX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, VTCRHTAX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
