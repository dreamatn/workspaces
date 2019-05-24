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

public class VTOT7013 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    VTOT7013_WS_OUT_PUT WS_OUT_PUT = new VTOT7013_WS_OUT_PUT();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    VTCROTAX VTCROTAX = new VTCROTAX();
    VTROTAX VTROTAX = new VTROTAX();
    VTRHTAX VTRHTAX = new VTRHTAX();
    VTCRHTAX VTCRHTAX = new VTCRHTAX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCI7013 VTCI7013;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "TEST");
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTOT7013 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        VTCI7013 = new VTCI7013();
        IBS.init(SCCGWA, VTCI7013);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, VTCI7013);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCI7013.TR_DATE == 0 
            || VTCI7013.SET_NO.trim().length() == 0) {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCI7013.TR_DATE);
        CEP.TRC(SCCGWA, VTCI7013.SET_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (VTCI7013.TR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, VTCROTAX);
            IBS.init(SCCGWA, VTROTAX);
            VTROTAX.KEY.AC_DATE = VTCI7013.TR_DATE;
            VTROTAX.KEY.SET_NO = VTCI7013.SET_NO;
            VTCROTAX.FUNC = 'Q';
            VTCROTAX.FUNC = 'B';
            VTCROTAX.OPT = 'Z';
            CEP.TRC(SCCGWA, VTROTAX.KEY.AC_DATE);
            CEP.TRC(SCCGWA, VTROTAX.KEY.SET_NO);
            S000_CALL_VTZROTAX();
            if (VTCROTAX.RETURN_INFO == 'N') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OTAX_REC_NOT_FOUND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, VTROTAX.CCY);
            WS_OUT_PUT.WS_TAX_AMT = VTROTAX.SH_OBAL;
            WS_OUT_PUT.WS_CCY = VTROTAX.CCY;
        }
        if (VTCI7013.TR_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, VTRHTAX);
            IBS.init(SCCGWA, VTCRHTAX);
            VTRHTAX.KEY.AC_DATE = VTCI7013.TR_DATE;
            VTRHTAX.KEY.SET_NO = VTCI7013.SET_NO;
            VTCRHTAX.FUNC = 'Q';
            VTCRHTAX.FUNC = 'B';
            VTCRHTAX.OPT = 'Z';
            S000_CALL_VTZRHTAX();
            if (VTCRHTAX.RETURN_INFO == 'N') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OTAX_REC_NOT_FOUND;
                S000_ERR_MSG_PROC();
            }
            WS_OUT_PUT.WS_TAX_AMT = VTRHTAX.SH_OBAL;
            WS_OUT_PUT.WS_CCY = VTRHTAX.CCY;
        }
        S000_OUTPUT_FMT();
    }
    public void S000_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "VT713";
        SCCFMT.DATA_PTR = WS_OUT_PUT;
        SCCFMT.DATA_LEN = 21;
        IBS.FMT(SCCGWA, SCCFMT);
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
        IBS.CALLCPN(SCCGWA, "VT-R-HTAX-MAINTAIN", VTCRHTAX);
        if (VTCRHTAX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, VTCRHTAX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
