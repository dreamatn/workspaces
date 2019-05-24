package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8102 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQIAC DDCSQIAC = new DDCSQIAC();
    SCCGWA SCCGWA;
    DDB8102_AWA_8102 DDB8102_AWA_8102;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT8102 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8102_AWA_8102>");
        DDB8102_AWA_8102 = (DDB8102_AWA_8102) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8102_AWA_8102.BR);
        CEP.TRC(SCCGWA, DDB8102_AWA_8102.CCY);
        CEP.TRC(SCCGWA, DDB8102_AWA_8102.BUSI_KND);
        if (DDB8102_AWA_8102.BUSI_KND.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8102_AWA_8102.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8102_AWA_8102.BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQIAC);
        DDCSQIAC.BR = DDB8102_AWA_8102.BR;
        DDCSQIAC.CCY = DDB8102_AWA_8102.CCY;
        DDCSQIAC.BUSI_KND = DDB8102_AWA_8102.BUSI_KND;
        S000_CALL_DDZSQIAC();
    }
    public void S000_CALL_DDZSQIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-M-QIAC", DDCSQIAC);
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
