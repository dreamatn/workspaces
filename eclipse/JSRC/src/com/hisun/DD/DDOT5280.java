package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5280 {
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
        CEP.TRC(SCCGWA, "DDOT5280 return!");
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
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.FLG);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.AC);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.BR);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.PAGE_ROW);
        CEP.TRC(SCCGWA, DDB5270_AWA_5270.PAGE_NUM);
        if (DDB5270_AWA_5270.FLG != '1' 
            && DDB5270_AWA_5270.FLG != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDB5270_AWA_5270.FLG == '1' 
            && DDB5270_AWA_5270.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5270_AWA_5270.FLG == '2' 
            && DDB5270_AWA_5270.BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCMSCHQ);
        if (DDB5270_AWA_5270.FLG == '1') {
            DDCMSCHQ.FUNC = 'I';
        } else {
            DDCMSCHQ.FUNC = 'B';
        }
        DDCMSCHQ.AC = DDB5270_AWA_5270.AC;
        DDCMSCHQ.BR = DDB5270_AWA_5270.BR;
        if (DDB5270_AWA_5270.PAGE_ROW == 0 
            || DDB5270_AWA_5270.PAGE_ROW > 25) {
            DDCMSCHQ.PAGE_ROW = 25;
        } else {
            DDCMSCHQ.PAGE_ROW = DDB5270_AWA_5270.PAGE_ROW;
        }
        if (DDB5270_AWA_5270.PAGE_NUM < 0) {
            DDCMSCHQ.PAGE_NUM = 0;
        } else {
            DDCMSCHQ.PAGE_NUM = DDB5270_AWA_5270.PAGE_NUM;
        }
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
