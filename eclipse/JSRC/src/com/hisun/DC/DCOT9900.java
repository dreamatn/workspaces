package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT9900 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCUTBAL DCCUTBAL = new DCCUTBAL();
    SCCGWA SCCGWA;
    DCB9000_AWA_9000 DCB9000_AWA_9000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT9900 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB9000_AWA_9000>");
        DCB9000_AWA_9000 = (DCB9000_AWA_9000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB9000_AWA_9000.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUTBAL);
        DCCUTBAL.INPUT_DATA.AC = DCB9000_AWA_9000.AC;
        S000_CALL_DCZUTBAL();
    }
    public void S000_CALL_DCZUTBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZUTBAL", DCCUTBAL);
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