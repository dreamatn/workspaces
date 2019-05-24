package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6023 {
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
        CEP.TRC(SCCGWA, "DDOT6023 return!");
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
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.FUNC);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.AC);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.CCY);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.STS);
        CEP.TRC(SCCGWA, DDB6020_AWA_6020.DOM_FLG);
        if (DDB6020_AWA_6020.FUNC != 'A' 
            && DDB6020_AWA_6020.FUNC != 'M' 
            && DDB6020_AWA_6020.FUNC != 'D' 
            && DDB6020_AWA_6020.FUNC != 'I' 
            && DDB6020_AWA_6020.FUNC != 'S') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACTION_INVALID;
            WS_FLD_NO = DDB6020_AWA_6020.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6020_AWA_6020.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB6020_AWA_6020.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6020_AWA_6020.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB6020_AWA_6020.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6020_AWA_6020.CCY_TYPE != '1' 
            && DDB6020_AWA_6020.CCY_TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            WS_FLD_NO = DDB6020_AWA_6020.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6020_AWA_6020.FUNC == 'D' 
            && DDB6020_AWA_6020.STS != '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_AC_NOT_REMOVE;
            WS_FLD_NO = DDB6020_AWA_6020.STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDREG);
        DDCSDREG.FUNC = DDB6020_AWA_6020.FUNC;
        DDCSDREG.AC = DDB6020_AWA_6020.AC;
        DDCSDREG.CCY = DDB6020_AWA_6020.CCY;
        DDCSDREG.CCY_TYPE = DDB6020_AWA_6020.CCY_TYPE;
        DDCSDREG.STS = DDB6020_AWA_6020.STS;
        DDCSDREG.DOM_FLG = DDB6020_AWA_6020.DOM_FLG;
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
