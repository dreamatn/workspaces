package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8670 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICMGREX CICMGREX = new CICMGREX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8670_AWA_8670 CIB8670_AWA_8670;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8670 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8670_AWA_8670>");
        CIB8670_AWA_8670 = (CIB8670_AWA_8670) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMGREX);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_CITGREX_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8670_AWA_8670.GREX_NO);
        CEP.TRC(SCCGWA, CIB8670_AWA_8670.CI_NO);
        CEP.TRC(SCCGWA, CIB8670_AWA_8670.ID_NO);
        CEP.TRC(SCCGWA, CIB8670_AWA_8670.ID_TYPE);
        CEP.TRC(SCCGWA, CIB8670_AWA_8670.CI_NM);
        if (CIB8670_AWA_8670.GREX_NO.trim().length() == 0 
            && CIB8670_AWA_8670.CI_NO.trim().length() == 0 
            && CIB8670_AWA_8670.ID_NO.trim().length() == 0 
            && CIB8670_AWA_8670.ID_TYPE.trim().length() == 0 
            && CIB8670_AWA_8670.CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA MUST LOSE ONE");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_CITGREX_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMGREX);
        CICMGREX.DATA.GREX_NO = CIB8670_AWA_8670.GREX_NO;
        CICMGREX.DATA.CI_NM = CIB8670_AWA_8670.CI_NM;
        CICMGREX.DATA.ID_TYPE = CIB8670_AWA_8670.ID_TYPE;
        CICMGREX.DATA.ID_NO = CIB8670_AWA_8670.ID_NO;
        CICMGREX.DATA.CI_NO = CIB8670_AWA_8670.CI_NO;
        CICMGREX.FUNC = 'B';
        S000_CALL_CIZMGREX();
    }
    public void S000_CALL_CIZMGREX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-GREX-INF", CICMGREX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
