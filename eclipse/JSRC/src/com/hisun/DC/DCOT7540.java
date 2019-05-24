package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7540 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSKHOL DCCSKHOL = new DCCSKHOL();
    SCCGWA SCCGWA;
    DDB7540_AWA_7540 DDB7540_AWA_7540;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7540 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7540_AWA_7540>");
        DDB7540_AWA_7540 = (DDB7540_AWA_7540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB7540_AWA_7540.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            WS_FLD_NO = DDB7540_AWA_7540.HLD_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSKHOL);
        DCCSKHOL.DATA.HLD_NO = DDB7540_AWA_7540.HLD_NO;
        DCCSKHOL.DATA.CHG_NO = DDB7540_AWA_7540.CHG_NO;
        DCCSKHOL.DATA.SPR_NM = DDB7540_AWA_7540.SPR_NM;
        DCCSKHOL.DATA.NEW_AMT = DDB7540_AWA_7540.NEW_AMT;
        DCCSKHOL.DATA.NEW_DT = DDB7540_AWA_7540.NEW_DT;
        DCCSKHOL.DATA.RSN = DDB7540_AWA_7540.RSN;
        DCCSKHOL.DATA.RMK = DDB7540_AWA_7540.RMK;
        DCCSKHOL.DATA.CHG_BR = DDB7540_AWA_7540.CHG_BR;
        DCCSKHOL.DATA.LAW_NM1 = DDB7540_AWA_7540.LAW_NM1;
        DCCSKHOL.DATA.LAW_NO1 = DDB7540_AWA_7540.LAW_NO1;
        DCCSKHOL.DATA.LAW_NM2 = DDB7540_AWA_7540.LAW_NM2;
        DCCSKHOL.DATA.LAW_NO2 = DDB7540_AWA_7540.LAW_NO2;
        S000_CALL_DCZSKHOL();
    }
    public void S000_CALL_DCZSKHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-KHOLD", DCCSKHOL);
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
