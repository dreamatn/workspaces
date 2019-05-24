package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5854 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDOT5854_WS_OUTPUT WS_OUTPUT = new DDOT5854_WS_OUTPUT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUGCTL DDCUGCTL = new DDCUGCTL();
    SCCGWA SCCGWA;
    DDB5854_AWA_5854 DDB5854_AWA_5854;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5854 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5854_AWA_5854>");
        DDB5854_AWA_5854 = (DDB5854_AWA_5854) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPDATE_DATA();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5854_AWA_5854.AC);
        CEP.TRC(SCCGWA, DDB5854_AWA_5854.CCY);
        if (DDB5854_AWA_5854.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            WS_FLD_NO = DDB5854_AWA_5854.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5854_AWA_5854.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5854_AWA_5854.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_UPDATE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGCTL);
        DDCUGCTL.INPUT.AC = DDB5854_AWA_5854.AC;
        DDCUGCTL.INPUT.CCY = DDB5854_AWA_5854.CCY;
        S000_CALL_DDZUGCTL();
    }
    public void S000_CALL_DDZUGCTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-CTL-BAL", DDCUGCTL);
        CEP.TRC(SCCGWA, DDCUGCTL.RC);
        if (DDCUGCTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGCTL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_OUTPUT.WS_CONTROL_BAL = DDCUGCTL.OUTPUT.CONTROL_BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 22;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
