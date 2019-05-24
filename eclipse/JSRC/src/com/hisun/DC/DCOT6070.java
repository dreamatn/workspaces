package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT6070 {
    String CPN_DC_S_QSAC_PROC = "DC-S-QSAC-PROC";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSQSAC DCCSQSAC = new DCCSQSAC();
    SCCGWA SCCGWA;
    DCB6070_AWA_6070 DCB6070_AWA_6070;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT6070 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB6070_AWA_6070>");
        DCB6070_AWA_6070 = (DCB6070_AWA_6070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_QUERY_DEPOSIT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB6070_AWA_6070.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB6070_AWA_6070.PAGE_ROW);
        if (DCB6070_AWA_6070.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCB6070_AWA_6070.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB6070_AWA_6070.PAGE_ROW);
    }
    public void B020_QUERY_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQSAC);
        DCCSQSAC.AC_NO = DCB6070_AWA_6070.AC_NO;
        DCCSQSAC.PAGE_NUM = DCB6070_AWA_6070.PAGE_NUM;
        DCCSQSAC.PAGE_ROW = DCB6070_AWA_6070.PAGE_ROW;
        CEP.TRC(SCCGWA, DCCSQSAC.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSQSAC.PAGE_ROW);
        S000_CALL_DCZSQSAC();
    }
    public void S000_CALL_DCZSQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DC_S_QSAC_PROC, DCCSQSAC);
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
