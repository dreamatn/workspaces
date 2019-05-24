package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6020 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDREG DDCSDREG = new DDCSDREG();
    SCCGWA SCCGWA;
    DDB6020_AWA_6020 DDB6020_AWA_6020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT6020 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6020_AWA_6020>");
        DDB6020_AWA_6020 = (DDB6020_AWA_6020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.BR);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.STS);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.AC);
        if (DDB6020_AWA_6020.BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            WS_FLD_NO = DDB6020_AWA_6020.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDREG);
        DDCSDREG.BR = DDB6020_AWA_6020.BR;
        DDCSDREG.AC_NO = DDB6020_AWA_6020.AC;
        DDCSDREG.FUNC = 'B';
        S000_CALL_DDZSDREG();
    }
    public void S000_CALL_DDZSDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSDREG", DDCSDREG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
