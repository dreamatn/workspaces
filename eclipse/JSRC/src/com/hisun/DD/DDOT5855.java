package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5855 {
    String K_OUT_FMT = "DD855";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDOT5855_WS_OUTPUT WS_OUTPUT = new DDOT5855_WS_OUTPUT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUGCBL DDCUGCBL = new DDCUGCBL();
    SCCGWA SCCGWA;
    DDB5855_AWA_5855 DDB5855_AWA_5855;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5855 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5855_AWA_5855>");
        DDB5855_AWA_5855 = (DDB5855_AWA_5855) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_BAL_PROC();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5855_AWA_5855.AC);
        CEP.TRC(SCCGWA, DDB5855_AWA_5855.CCY);
        CEP.TRC(SCCGWA, DDB5855_AWA_5855.CCY_TYPE);
        if (DDB5855_AWA_5855.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            WS_FLD_NO = DDB5855_AWA_5855.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5855_AWA_5855.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5855_AWA_5855.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_INQ_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGCBL);
        DDCUGCBL.INPUT.AC = DDB5855_AWA_5855.AC;
        DDCUGCBL.INPUT.CCY = DDB5855_AWA_5855.CCY;
        CEP.TRC(SCCGWA, DDB5855_AWA_5855.CCY);
        if (DDB5855_AWA_5855.CCY.equalsIgnoreCase("156")) {
            DDCUGCBL.INPUT.CCY_TYPE = '1';
        } else {
            DDCUGCBL.INPUT.CCY_TYPE = DDB5855_AWA_5855.CCY_TYPE;
        }
        S000_CALL_DDZUGCBL();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_OUTPUT.WS_AC = DDCUGCBL.INPUT.AC;
        WS_OUTPUT.WS_AC_NM = DDCUGCBL.OUTPUT.AC_NM;
        WS_OUTPUT.WS_CI_NO = DDCUGCBL.OUTPUT.CI_NO;
        WS_OUTPUT.WS_OPEN_DP = DDCUGCBL.OUTPUT.OPEN_DP;
        WS_OUTPUT.WS_CCY = DDCUGCBL.INPUT.CCY;
        WS_OUTPUT.WS_CCY_TYPE = DDCUGCBL.INPUT.CCY_TYPE;
        WS_OUTPUT.WS_CURR_BAL = DDCUGCBL.OUTPUT.CURR_BAL;
        WS_OUTPUT.WS_HOLD_BAL = DDCUGCBL.OUTPUT.HOLD_BAL;
        WS_OUTPUT.WS_AVL_BAL = DDCUGCBL.OUTPUT.AVL_BAL;
        WS_OUTPUT.WS_CNTL_BAL = DDCUGCBL.OUTPUT.CNTL_BAL;
        WS_OUTPUT.WS_COLLECT_BAL = DDCUGCBL.OUTPUT.COLLECT_BAL;
        WS_OUTPUT.WS_SUB_AC_FLG = DDCUGCBL.OUTPUT.SUB_AC_FLG;
        WS_OUTPUT.WS_UP_AC = DDCUGCBL.OUTPUT.UP_AC;
        WS_OUTPUT.WS_UP_AC_NM = DDCUGCBL.OUTPUT.UP_AC_NM;
        WS_OUTPUT.WS_POOL_TYPE = DDCUGCBL.OUTPUT.POOL_TYPE;
        WS_OUTPUT.WS_PAY_MTH = DDCUGCBL.OUTPUT.PAY_MTH;
        WS_OUTPUT.WS_CTRL_MTH = DDCUGCBL.OUTPUT.CTRL_MTH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 681;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZUGCBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-BAL-INQ", DDCUGCBL);
        CEP.TRC(SCCGWA, DDCUGCBL.RC);
        if (DDCUGCBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGCBL.RC);
            S000_ERR_MSG_PROC();
        }
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
