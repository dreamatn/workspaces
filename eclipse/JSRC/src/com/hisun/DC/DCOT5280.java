package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5280 {
    String CPN_SST_QUERY = "DC-S-DCZSSTIQ";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSSTIQ DCCSSTIQ = new DCCSSTIQ();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCB5280_AWA_5280 DCB5280_AWA_5280;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT5280 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5280_AWA_5280>");
        DCB5280_AWA_5280 = (DCB5280_AWA_5280) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB5280_AWA_5280.FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FLG_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5280_AWA_5280.FLG == '1' 
            && DCB5280_AWA_5280.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5280_AWA_5280.FLG == '2' 
            && DCB5280_AWA_5280.BR == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSSTIQ);
        DCCSSTIQ.FLG = DCB5280_AWA_5280.FLG;
        DCCSSTIQ.AC = DCB5280_AWA_5280.AC;
        DCCSSTIQ.BR = DCB5280_AWA_5280.BR;
        DCCSSTIQ.CCY = DCB5280_AWA_5280.CCY;
        DCCSSTIQ.STS = DCB5280_AWA_5280.STS;
        DCCSSTIQ.PAGE_ROW = DCB5280_AWA_5280.PAGE_ROW;
        DCCSSTIQ.PAGE_NUM = DCB5280_AWA_5280.PAGE_NUM;
        S000_CALL_DCZSSTIQ();
    }
    public void S000_CALL_DCZSSTIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SST_QUERY, DCCSSTIQ);
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
