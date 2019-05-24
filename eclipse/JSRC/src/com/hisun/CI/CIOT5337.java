package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5337 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSQFC CICSQFC = new CICSQFC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5337_AWA_5337 CIB5337_AWA_5337;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5337 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5337_AWA_5337>");
        CIB5337_AWA_5337 = (CIB5337_AWA_5337) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQFC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQ_FIN_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5337_AWA_5337.CI_NO);
        if (CIB5337_AWA_5337.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_INQ_FIN_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSQFC);
        CICSQFC.DATA.CI_NO = CIB5337_AWA_5337.CI_NO;
        S000_CALL_CIZSQFC();
    }
    public void S000_CALL_CIZSQFC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-FIN-CI", CICSQFC);
        if (CICSQFC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSQFC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}