package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5857 {
    String K_OUT_FMT = "DD857";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDOT5857_WS_OUTPUT WS_OUTPUT = new DDOT5857_WS_OUTPUT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUGMDR DDCUGMDR = new DDCUGMDR();
    SCCGWA SCCGWA;
    DDB5857_AWA_5857 DDB5857_AWA_5857;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5857 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5857_AWA_5857>");
        DDB5857_AWA_5857 = (DDB5857_AWA_5857) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_BAL_PROC();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5857_AWA_5857.AC);
        CEP.TRC(SCCGWA, DDB5857_AWA_5857.HOLD_TYP);
        CEP.TRC(SCCGWA, DDB5857_AWA_5857.TX_AMT);
        CEP.TRC(SCCGWA, DDB5857_AWA_5857.CCY);
        CEP.TRC(SCCGWA, DDB5857_AWA_5857.CCY_TYPE);
        if (DDB5857_AWA_5857.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            WS_FLD_NO = DDB5857_AWA_5857.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5857_AWA_5857.HOLD_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_TYPE_INVALID;
            WS_FLD_NO = DDB5857_AWA_5857.HOLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5857_AWA_5857.HOLD_TYP != '1' 
            && DDB5857_AWA_5857.HOLD_TYP != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_TYPE_INVALID;
            WS_FLD_NO = DDB5857_AWA_5857.HOLD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5857_AWA_5857.HOLD_TYP == '2' 
            && DDB5857_AWA_5857.TX_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            WS_FLD_NO = DDB5857_AWA_5857.TX_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5857_AWA_5857.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5857_AWA_5857.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5857_AWA_5857.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            WS_FLD_NO = DDB5857_AWA_5857.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            if (DDB5857_AWA_5857.CCY.equalsIgnoreCase("156") 
                && DDB5857_AWA_5857.CCY_TYPE != '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
                WS_FLD_NO = DDB5857_AWA_5857.CCY_TYPE_NO;
                S000_ERR_MSG_PROC_LAST();
            }
        }
    }
    public void B020_INQ_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGMDR);
        DDCUGMDR.INPUT.AC = DDB5857_AWA_5857.AC;
        DDCUGMDR.INPUT.HOLD_TYPE = DDB5857_AWA_5857.HOLD_TYP;
        DDCUGMDR.INPUT.TX_AMT = DDB5857_AWA_5857.TX_AMT;
        DDCUGMDR.INPUT.CCY = DDB5857_AWA_5857.CCY;
        CEP.TRC(SCCGWA, DDB5857_AWA_5857.CCY);
        if (DDB5857_AWA_5857.CCY.equalsIgnoreCase("156")) {
            DDCUGMDR.INPUT.CCY_TYPE = '1';
        } else {
            DDCUGMDR.INPUT.CCY_TYPE = DDB5857_AWA_5857.CCY_TYPE;
        }
        S000_CALL_DDZUGMDR();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_OUTPUT.WS_AC_FLG = DDCUGMDR.OUTPUT.AC_FLG;
        WS_OUTPUT.WS_AC = DDCUGMDR.INPUT.AC;
        WS_OUTPUT.WS_AC_NM = DDCUGMDR.OUTPUT.AC_NM;
        WS_OUTPUT.WS_UP_AC = DDCUGMDR.OUTPUT.UP_AC;
        WS_OUTPUT.WS_UP_AC_NM = DDCUGMDR.OUTPUT.UP_AC_NM;
        WS_OUTPUT.WS_CCY = DDCUGMDR.INPUT.CCY;
        WS_OUTPUT.WS_CCY_TYPE = DDCUGMDR.INPUT.CCY_TYPE;
        WS_OUTPUT.WS_TX_AMT = DDCUGMDR.INPUT.TX_AMT;
        WS_OUTPUT.WS_DONE_AMT = DDCUGMDR.OUTPUT.DONE_AMT;
        WS_OUTPUT.WS_UNDO_AMT = DDCUGMDR.OUTPUT.UNDO_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 626;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZUGMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-AMT-DR", DDCUGMDR);
        CEP.TRC(SCCGWA, DDCUGMDR.RC);
        if (DDCUGMDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGMDR.RC);
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
