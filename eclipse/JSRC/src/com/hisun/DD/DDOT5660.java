package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5660 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSQPOD DDCSQPOD = new DDCSQPOD();
    SCCGWA SCCGWA;
    DDB5660_AWA_5660 DDB5660_AWA_5660;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5660 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5660_AWA_5660>");
        DDB5660_AWA_5660 = (DDB5660_AWA_5660) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.TR_BR);
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.TR_DATE);
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.JRN_NO);
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.OUT_AC);
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.NOM_O_AC);
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.NOM_O_NE);
        CEP.TRC(SCCGWA, DDB5660_AWA_5660.IN_AC);
        if (DDB5660_AWA_5660.TR_BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5660_AWA_5660.TR_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5660_AWA_5660.JRN_NO == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5660_AWA_5660.OUT_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5660_AWA_5660.NOM_O_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5660_AWA_5660.NOM_O_NE.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5660_AWA_5660.IN_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQPOD);
        DDCSQPOD.TR_BR = DDB5660_AWA_5660.TR_BR;
        DDCSQPOD.TR_DATE = DDB5660_AWA_5660.TR_DATE;
        DDCSQPOD.JRN_NO = DDB5660_AWA_5660.JRN_NO;
        DDCSQPOD.OUT_AC = DDB5660_AWA_5660.OUT_AC;
        DDCSQPOD.NOM_OUT_AC = DDB5660_AWA_5660.NOM_O_AC;
        DDCSQPOD.NOM_OUT_NAME = DDB5660_AWA_5660.NOM_O_NE;
        DDCSQPOD.IN_AC = DDB5660_AWA_5660.IN_AC;
        CEP.TRC(SCCGWA, DDCSQPOD.TR_BR);
        CEP.TRC(SCCGWA, DDCSQPOD.TR_DATE);
        CEP.TRC(SCCGWA, DDCSQPOD.JRN_NO);
        CEP.TRC(SCCGWA, DDCSQPOD.OUT_AC);
        CEP.TRC(SCCGWA, DDCSQPOD.NOM_OUT_AC);
        CEP.TRC(SCCGWA, DDCSQPOD.NOM_OUT_NAME);
        CEP.TRC(SCCGWA, DDCSQPOD.IN_AC);
        S000_CALL_DDZSQPOD();
    }
    public void S000_CALL_DDZSQPOD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-RES-T-TRMP", DDCSQPOD);
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
