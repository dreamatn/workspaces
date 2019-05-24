package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9002 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICBACAC CICBACAC = new CICBACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9002_AWA_9002 CIB9002_AWA_9002;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9002 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9002_AWA_9002>");
        CIB9002_AWA_9002 = (CIB9002_AWA_9002) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBACAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQUIRE_AC_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9002_AWA_9002.PAGE_NUM);
        CEP.TRC(SCCGWA, CIB9002_AWA_9002.PAGE_ROW);
        if (CIB9002_AWA_9002.PAGE_NUM == 0 
            || CIB9002_AWA_9002.PAGE_ROW == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_AC_INF() throws IOException,SQLException,Exception {
        CICBACAC.DATA.CI_NO = CIB9002_AWA_9002.CI_NO;
        CICBACAC.DATA.AGR_NO = CIB9002_AWA_9002.AGR_NO;
        CICBACAC.DATA.ENTY_TYP = CIB9002_AWA_9002.ENTY_TYP;
        CICBACAC.DATA.FRM_APP = CIB9002_AWA_9002.FRM_APP;
        CICBACAC.DATA.PROD_CD = CIB9002_AWA_9002.PROD_CD;
        CICBACAC.DATA.STS = CIB9002_AWA_9002.STS;
        CICBACAC.DATA.TRCT_TYP = CIB9002_AWA_9002.TRCT_TYP;
        CICBACAC.DATA.CCY = CIB9002_AWA_9002.CCY;
        CICBACAC.DATA.PAGE_NUM = CIB9002_AWA_9002.PAGE_NUM;
        CICBACAC.DATA.PAGE_ROW = CIB9002_AWA_9002.PAGE_ROW;
        CICBACAC.DATA.END_DAYS = CIB9002_AWA_9002.END_DAYS;
        S000_CALL_CIZBACAC();
    }
    public void S000_CALL_CIZBACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BRW-ACAC", CICBACAC);
        CEP.TRC(SCCGWA, CICBACAC.RC);
        if (CICBACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICBACAC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
