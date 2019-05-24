package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7800 {
    String CPN_BAL_QUERY = "DC-S-DCZSCBAL";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSCBAL DCCSCBAL = new DCCSCBAL();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCB7800_AWA_7800 DCB7800_AWA_7800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7800 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7800_AWA_7800>");
        DCB7800_AWA_7800 = (DCB7800_AWA_7800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB7800_AWA_7800.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSCBAL);
        DCCSCBAL.AC = DCB7800_AWA_7800.AC;
        DCCSCBAL.CCY = DCB7800_AWA_7800.CCY;
        DCCSCBAL.PAGE_ROW = DCB7800_AWA_7800.ROWS;
        DCCSCBAL.PAGE_NUM = DCB7800_AWA_7800.PAGE_NUM;
        S000_CALL_DCZSCBAL();
    }
    public void S000_CALL_DCZSCBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BAL_QUERY, DCCSCBAL);
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
