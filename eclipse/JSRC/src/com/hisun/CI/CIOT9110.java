package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9110 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICDIST2 CICDIST2 = new CICDIST2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9110_AWA_9110 CIB9110_AWA_9110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9110_AWA_9110>");
        CIB9110_AWA_9110 = (CIB9110_AWA_9110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICDIST2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DIST2_CI_NO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9110_AWA_9110.CI_NO);
        CEP.TRC(SCCGWA, CIB9110_AWA_9110.ID_TYPE);
        if (CIB9110_AWA_9110.PAGE_NUM == 0 
            || CIB9110_AWA_9110.PAGE_ROW == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_DIST2_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICDIST2);
        CICDIST2.DATA.CI_NO = CIB9110_AWA_9110.CI_NO;
        CICDIST2.DATA.AGR_NO = CIB9110_AWA_9110.AGR_NO;
        CICDIST2.DATA.ID_TYPE = CIB9110_AWA_9110.ID_TYPE;
        CICDIST2.DATA.ID_NO = CIB9110_AWA_9110.ID_NO;
        CICDIST2.DATA.CI_NM = CIB9110_AWA_9110.CI_NM;
        CICDIST2.DATA.PAGE_NUM = CIB9110_AWA_9110.PAGE_NUM;
        CICDIST2.DATA.PAGE_ROW = CIB9110_AWA_9110.PAGE_ROW;
        S000_CALL_CIZDIST2();
    }
    public void S000_CALL_CIZDIST2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-DIST2-CI-NO", CICDIST2);
        if (CICDIST2.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICDIST2.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
