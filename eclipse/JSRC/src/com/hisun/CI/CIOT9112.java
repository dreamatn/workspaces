package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9112 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSQCC2 CICSQCC2 = new CICSQCC2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9112_AWA_9112 CIB9112_AWA_9112;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9112 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9112_AWA_9112>");
        CIB9112_AWA_9112 = (CIB9112_AWA_9112) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQCC2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_SQCC2_CI_NO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9112_AWA_9112.CI_NO.trim().length() == 0 
            && (CIB9112_AWA_9112.ID_TYPE.trim().length() == 0 
            || CIB9112_AWA_9112.ID_NO.trim().length() == 0 
            || CIB9112_AWA_9112.CI_NM.trim().length() == 0) 
            && CIB9112_AWA_9112.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_SQCC2_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSQCC2);
        CICSQCC2.DATA.CI_NO = CIB9112_AWA_9112.CI_NO;
        CICSQCC2.DATA.AGR_NO = CIB9112_AWA_9112.AGR_NO;
        CICSQCC2.DATA.ID_TYPE = CIB9112_AWA_9112.ID_TYPE;
        CICSQCC2.DATA.ID_NO = CIB9112_AWA_9112.ID_NO;
        CICSQCC2.DATA.CI_NM = CIB9112_AWA_9112.CI_NM;
        S000_CALL_CIZSQCC2();
    }
    public void S000_CALL_CIZSQCC2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-COM2-CI", CICSQCC2);
        if (CICSQCC2.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSQCC2.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
