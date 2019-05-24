package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5270 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCMSCHQ DDCMSCHQ = new DDCMSCHQ();
    SCCGWA SCCGWA;
    DDB5270_AWA_5270 DDB5270_AWA_5270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5270 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5270_AWA_5270>");
        DDB5270_AWA_5270 = (DDB5270_AWA_5270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.FUNC);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.AC);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.AMT);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.CON_NO);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.REMARK);
        if (DDB5270_AWA_5270.FUNC != 'A' 
            && DDB5270_AWA_5270.FUNC != 'U' 
            && DDB5270_AWA_5270.FUNC != 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDB5270_AWA_5270.FUNC == 'A' 
            && DDB5270_AWA_5270.CON_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.CON_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5270_AWA_5270.FUNC == 'A' 
            && DDB5270_AWA_5270.AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCMSCHQ);
        DDCMSCHQ.FUNC = DDB5270_AWA_5270.FUNC;
        DDCMSCHQ.AC = DDB5270_AWA_5270.AC;
        DDCMSCHQ.AMT = DDB5270_AWA_5270.AMT;
        DDCMSCHQ.CON_NO = DDB5270_AWA_5270.CON_NO;
        DDCMSCHQ.REMARK = DDB5270_AWA_5270.REMARK;
        S000_CALL_DDZMSCHQ();
    }
    public void S000_CALL_DDZMSCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-M-SCHQ", DDCMSCHQ);
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
