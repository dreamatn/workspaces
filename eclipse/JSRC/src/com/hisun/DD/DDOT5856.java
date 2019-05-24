package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5856 {
    String K_OUT_FMT = "DD856";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDOT5856_WS_OUTPUT WS_OUTPUT = new DDOT5856_WS_OUTPUT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUGCHK DDCUGCHK = new DDCUGCHK();
    SCCGWA SCCGWA;
    DDB5856_AWA_5856 DDB5856_AWA_5856;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5856 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5856_AWA_5856>");
        DDB5856_AWA_5856 = (DDB5856_AWA_5856) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CHECK_DATA();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5856_AWA_5856.AC);
        if (DDB5856_AWA_5856.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            WS_FLD_NO = DDB5856_AWA_5856.AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGCHK);
        DDCUGCHK.INPUT.AC = DDB5856_AWA_5856.AC;
        S000_CALL_DDZUGCHK();
    }
    public void S000_CALL_DDZUGCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-AC-CHK", DDCUGCHK);
        CEP.TRC(SCCGWA, DDCUGCHK.RC);
        if (DDCUGCHK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGCHK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUGCHK.OUTPUT.AC_FLG);
        CEP.TRC(SCCGWA, DDCUGCHK.OUTPUT.PAY_TYPE);
        WS_OUTPUT.WS_AC_FLG = DDCUGCHK.OUTPUT.AC_FLG;
        WS_OUTPUT.WS_PAY_TYPE = DDCUGCHK.OUTPUT.PAY_TYPE;
        CEP.TRC(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 2;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
