package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1140 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSZMQB DDCSZMQB = new DDCSZMQB();
    SCCGWA SCCGWA;
    DDB1140_AWA_1140 DDB1140_AWA_1140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1140 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1140_AWA_1140>");
        DDB1140_AWA_1140 = (DDB1140_AWA_1140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1140_AWA_1140.TR_DATE);
        CEP.TRC(SCCGWA, DDB1140_AWA_1140.TR_JRN);
        CEP.TRC(SCCGWA, DDB1140_AWA_1140.TR_RMK);
        if (DDB1140_AWA_1140.TR_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_DATE_M_INPUT);
        }
        if (DDB1140_AWA_1140.TR_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_JRN_M_INPUT);
        }
        if (DDB1140_AWA_1140.TR_RMK.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_RMK_M_INPUT);
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSZMQB);
        DDCSZMQB.TR_DATE = DDB1140_AWA_1140.TR_DATE;
        DDCSZMQB.TR_JRN = DDB1140_AWA_1140.TR_JRN;
        DDCSZMQB.TR_RMK = DDB1140_AWA_1140.TR_RMK;
        CEP.TRC(SCCGWA, DDCSZMQB.TR_DATE);
        CEP.TRC(SCCGWA, DDCSZMQB.TR_JRN);
        CEP.TRC(SCCGWA, DDCSZMQB.TR_RMK);
        S000_CALL_DDZSZMQB();
    }
    public void S000_CALL_DDZSZMQB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-ZMQB", DDCSZMQB);
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
