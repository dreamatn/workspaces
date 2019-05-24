package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT6040 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSBSAC DCCSBSAC = new DCCSBSAC();
    SCCGWA SCCGWA;
    DCB6040_AWA_6040 DCB6040_AWA_6040;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT6040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB6040_AWA_6040>");
        DCB6040_AWA_6040 = (DCB6040_AWA_6040) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB6040_AWA_6040.AC);
        CEP.TRC(SCCGWA, DCB6040_AWA_6040.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB6040_AWA_6040.PAGE_ROW);
        if (DCB6040_AWA_6040.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACNO_MUST_INPUT;
            WS_FLD_NO = DCB6040_AWA_6040.AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSBSAC);
        DCCSBSAC.AC = DCB6040_AWA_6040.AC;
        DCCSBSAC.PAGE_ROW = DCB6040_AWA_6040.PAGE_ROW;
        DCCSBSAC.PAGE_NUM = DCB6040_AWA_6040.PAGE_NUM;
        CEP.TRC(SCCGWA, DCB6040_AWA_6040.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB6040_AWA_6040.PAGE_ROW);
        S000_CALL_DCZSBSAC();
    }
    public void S000_CALL_DCZSBSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-SBS-AC", DCCSBSAC);
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
