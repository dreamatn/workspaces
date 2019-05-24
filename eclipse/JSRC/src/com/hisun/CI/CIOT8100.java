package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8100 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICBACR CICBACR = new CICBACR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8100_AWA_8100 CIB8100_AWA_8100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8100_AWA_8100>");
        CIB8100_AWA_8100 = (CIB8100_AWA_8100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBACR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_AC_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8100_AWA_8100.CI_NO.trim().length() == 0 
            && CIB8100_AWA_8100.AGR_NO.trim().length() == 0 
            && (CIB8100_AWA_8100.ID_TYP.trim().length() == 0 
            || CIB8100_AWA_8100.ID_NO.trim().length() == 0 
            || CIB8100_AWA_8100.CI_NM.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "CI-NO AC ID MUST INPUT ONE ");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_AC_INF() throws IOException,SQLException,Exception {
        CICBACR.DATA.CI_NO = CIB8100_AWA_8100.CI_NO;
        CICBACR.DATA.ENTY_TYP = CIB8100_AWA_8100.ENTY_TYP;
        CICBACR.DATA.AGR_NO = CIB8100_AWA_8100.AGR_NO;
        CICBACR.DATA.ID_TYPE = CIB8100_AWA_8100.ID_TYP;
        CICBACR.DATA.ID_NO = CIB8100_AWA_8100.ID_NO;
        CICBACR.DATA.CI_NM = CIB8100_AWA_8100.CI_NM;
        CICBACR.DATA.FRM_APP = CIB8100_AWA_8100.FRM_APP;
        CICBACR.DATA.OPN_BR = CIB8100_AWA_8100.OPN_BR;
        CICBACR.DATA.ENTY_FLG = CIB8100_AWA_8100.ENTY_FLG;
        CICBACR.DATA.STS = CIB8100_AWA_8100.STS;
        S000_CALL_CIZBACR();
    }
    public void S000_CALL_CIZBACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BRW-ACR", CICBACR);
        if (CICBACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICBACR.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
