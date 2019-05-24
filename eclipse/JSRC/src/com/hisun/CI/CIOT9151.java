package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9151 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICQONL CICQONL = new CICQONL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9151_AWA_9151 CIB9151_AWA_9151;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9151 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9151_AWA_9151>");
        CIB9151_AWA_9151 = (CIB9151_AWA_9151) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICQONL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_PER_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9151_AWA_9151.CI_NO);
        CEP.TRC(SCCGWA, CIB9151_AWA_9151.ID_TYPE);
        CEP.TRC(SCCGWA, CIB9151_AWA_9151.ID_NO);
        CEP.TRC(SCCGWA, CIB9151_AWA_9151.CI_NM);
        CEP.TRC(SCCGWA, CIB9151_AWA_9151.AGR_NO);
        if (CIB9151_AWA_9151.CI_NO.trim().length() == 0 
            && (CIB9151_AWA_9151.ID_TYPE.trim().length() == 0 
            || CIB9151_AWA_9151.ID_NO.trim().length() == 0) 
            && CIB9151_AWA_9151.CI_NM.trim().length() == 0 
            && CIB9151_AWA_9151.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_OPEN_PER_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQONL);
        CICQONL.DATA.CI_NO = CIB9151_AWA_9151.CI_NO;
        CICQONL.DATA.ID_TYPE = CIB9151_AWA_9151.ID_TYPE;
        CICQONL.DATA.ID_NO = CIB9151_AWA_9151.ID_NO;
        CICQONL.DATA.CI_NM = CIB9151_AWA_9151.CI_NM;
        CICQONL.DATA.AGR_NO = CIB9151_AWA_9151.AGR_NO;
        S000_CALL_CIZQONL();
    }
    public void S000_CALL_CIZQONL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ONL", CICQONL);
        if (CICQONL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQONL.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
