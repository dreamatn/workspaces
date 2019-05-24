package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5360 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSOOC CICSOOC = new CICSOOC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5360_AWA_5360 CIB5360_AWA_5360;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5360 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5360_AWA_5360>");
        CIB5360_AWA_5360 = (CIB5360_AWA_5360) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSOOC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_ONC_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5360_AWA_5360.ID_TYPE);
        CEP.TRC(SCCGWA, CIB5360_AWA_5360.ID_NO);
        CEP.TRC(SCCGWA, CIB5360_AWA_5360.CI_NM);
    }
    public void B020_OPEN_ONC_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSOOC);
        CICSOOC.DATA.CI_NM = CIB5360_AWA_5360.CI_NM;
        CICSOOC.DATA.ID_TYPE = CIB5360_AWA_5360.ID_TYPE;
        CICSOOC.DATA.ID_NO = CIB5360_AWA_5360.ID_NO;
        S000_CALL_CIZSOOC();
    }
    public void S000_CALL_CIZSOOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-ONC-CI", CICSOOC);
        if (CICSOOC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOOC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
