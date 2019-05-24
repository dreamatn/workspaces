package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1120 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSZMQI DDCSZMQI = new DDCSZMQI();
    SCCGWA SCCGWA;
    DDB1120_AWA_1120 DDB1120_AWA_1120;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1120 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1120_AWA_1120>");
        DDB1120_AWA_1120 = (DDB1120_AWA_1120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.TR_DATE);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.TR_JRN);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.AI_ACNO);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.OUT_AC);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.OUT_ACNM);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.OUT_CCY);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.OUT_CYTP);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.IN_AMT);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.IN_AC);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.IN_ACNM);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.IN_CCY);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.IN_CYTP);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.TR_RSN);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.TR_DESC);
        CEP.TRC(SCCGWA, DDB1120_AWA_1120.TR_RMK);
        if (DDB1120_AWA_1120.TR_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_DATE_M_INPUT);
        }
        if (DDB1120_AWA_1120.TR_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_JRN_M_INPUT);
        }
        if (DDB1120_AWA_1120.IN_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TOAC_NO_M_INPUT);
        }
        if (DDB1120_AWA_1120.IN_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDB1120_AWA_1120.IN_CYTP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
        if (DDB1120_AWA_1120.IN_AMT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TRF_AMT_M_INPUT);
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSZMQI);
        DDCSZMQI.TR_DATE = DDB1120_AWA_1120.TR_DATE;
        DDCSZMQI.TR_JRN = DDB1120_AWA_1120.TR_JRN;
        DDCSZMQI.IN_AC = DDB1120_AWA_1120.IN_AC;
        DDCSZMQI.IN_CCY = DDB1120_AWA_1120.IN_CCY;
        DDCSZMQI.IN_CYTP = DDB1120_AWA_1120.IN_CYTP;
        DDCSZMQI.IN_AMT = DDB1120_AWA_1120.IN_AMT;
        CEP.TRC(SCCGWA, DDCSZMQI.TR_DATE);
        CEP.TRC(SCCGWA, DDCSZMQI.TR_JRN);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_AC);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_CCY);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_CYTP);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_AMT);
        S000_CALL_DDZSZMQI();
    }
    public void S000_CALL_DDZSZMQI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-ZMQI", DDCSZMQI);
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
