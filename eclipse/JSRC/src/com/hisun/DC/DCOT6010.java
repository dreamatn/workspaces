package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT6010 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSSPAC DCCSSPAC = new DCCSSPAC();
    SCCGWA SCCGWA;
    DCB6010_AWA_6010 DCB6010_AWA_6010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT6010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB6010_AWA_6010>");
        DCB6010_AWA_6010 = (DCB6010_AWA_6010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB6010_AWA_6010.AC);
        CEP.TRC(SCCGWA, DCB6010_AWA_6010.PAGE_ROW);
        CEP.TRC(SCCGWA, DCB6010_AWA_6010.PAGE_NUM);
        if (DCB6010_AWA_6010.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FREE_AC_MUST_INPUT;
            WS_FLD_NO = DCB6010_AWA_6010.AC_NO;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSSPAC);
        DCCSSPAC.FUNC.AC = DCB6010_AWA_6010.AC;
        DCCSSPAC.FUNC.PAGE_ROW = DCB6010_AWA_6010.PAGE_ROW;
        DCCSSPAC.FUNC.PAGE_NUM = DCB6010_AWA_6010.PAGE_NUM;
        S000_CALL_DCZSSPAC();
    }
    public void S000_CALL_DCZSSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC1", DCCSSPAC);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
