package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5852 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUGBAL DDCUGBAL = new DDCUGBAL();
    SCCGWA SCCGWA;
    DDB5852_AWA_5852 DDB5852_AWA_5852;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5852 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5852_AWA_5852>");
        DDB5852_AWA_5852 = (DDB5852_AWA_5852) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PARM_DATA_TR();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5852_AWA_5852.AC);
        CEP.TRC(SCCGWA, DDB5852_AWA_5852.CCY);
        CEP.TRC(SCCGWA, DDB5852_AWA_5852.INT_AMT);
        if (DDB5852_AWA_5852.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            WS_FLD_NO = DDB5852_AWA_5852.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5852_AWA_5852.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5852_AWA_5852.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5852_AWA_5852.INT_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_CBAL_MST_IN;
            WS_FLD_NO = DDB5852_AWA_5852.INT_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_PARM_DATA_TR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGBAL);
        DDCUGBAL.INPUT.AC = DDB5852_AWA_5852.AC;
        DDCUGBAL.INPUT.CCY = DDB5852_AWA_5852.CCY;
        DDCUGBAL.INPUT.INT_AMT = DDB5852_AWA_5852.INT_AMT;
        S000_CALL_DDZUGBAL();
    }
    public void S000_CALL_DDZUGBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-UPD-BAL", DDCUGBAL);
        CEP.TRC(SCCGWA, DDCUGBAL.RC);
        if (DDCUGBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGBAL.RC);
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
