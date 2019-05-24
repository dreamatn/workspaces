package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1130 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSBZMQ DDCSBZMQ = new DDCSBZMQ();
    SCCGWA SCCGWA;
    DDB1130_AWA_1130 DDB1130_AWA_1130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1130 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1130_AWA_1130>");
        DDB1130_AWA_1130 = (DDB1130_AWA_1130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.FUNC);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.IN_BR);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.OUT_BR);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.STR_DATE);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.END_DATE);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.STS);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.IN_JRN);
        CEP.TRC(SCCGWA, DDB1130_AWA_1130.OUT_JRN);
        if (DDB1130_AWA_1130.FUNC == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT);
        }
        if (DDB1130_AWA_1130.FUNC != '1' 
            && DDB1130_AWA_1130.FUNC != '2' 
            && DDB1130_AWA_1130.FUNC != '3' 
            && DDB1130_AWA_1130.FUNC != '4') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_INVALID);
        }
        if (DDB1130_AWA_1130.FUNC == '1' 
            && DDB1130_AWA_1130.OUT_BR == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_BR_M_INPUT);
        }
        if (DDB1130_AWA_1130.FUNC == '2' 
            && DDB1130_AWA_1130.IN_BR == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_BR_M_INPUT);
        }
        if (DDB1130_AWA_1130.FUNC == '3' 
            && DDB1130_AWA_1130.IN_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_JRN_M_INPUT);
        }
        if (DDB1130_AWA_1130.FUNC == '4' 
            && DDB1130_AWA_1130.OUT_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_JRN_M_INPUT);
        }
        if (DDB1130_AWA_1130.STR_DATE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDATE_M_INPUT);
        }
        if (DDB1130_AWA_1130.END_DATE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ENDDATE_M_INPUT);
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBZMQ);
        DDCSBZMQ.FUNC = DDB1130_AWA_1130.FUNC;
        DDCSBZMQ.OUT_BR = DDB1130_AWA_1130.OUT_BR;
        DDCSBZMQ.IN_BR = DDB1130_AWA_1130.IN_BR;
        DDCSBZMQ.STR_DATE = DDB1130_AWA_1130.STR_DATE;
        DDCSBZMQ.END_DATE = DDB1130_AWA_1130.END_DATE;
        DDCSBZMQ.STS = DDB1130_AWA_1130.STS;
        DDCSBZMQ.IN_JRN = DDB1130_AWA_1130.IN_JRN;
        DDCSBZMQ.OUT_JRN = DDB1130_AWA_1130.OUT_JRN;
        CEP.TRC(SCCGWA, DDCSBZMQ.FUNC);
        CEP.TRC(SCCGWA, DDCSBZMQ.OUT_BR);
        CEP.TRC(SCCGWA, DDCSBZMQ.IN_BR);
        CEP.TRC(SCCGWA, DDCSBZMQ.STR_DATE);
        CEP.TRC(SCCGWA, DDCSBZMQ.END_DATE);
        CEP.TRC(SCCGWA, DDCSBZMQ.STS);
        CEP.TRC(SCCGWA, DDCSBZMQ.IN_JRN);
        CEP.TRC(SCCGWA, DDCSBZMQ.OUT_JRN);
        S000_CALL_DDZSBZMQ();
    }
    public void S000_CALL_DDZSBZMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-BZMQ", DDCSBZMQ);
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
