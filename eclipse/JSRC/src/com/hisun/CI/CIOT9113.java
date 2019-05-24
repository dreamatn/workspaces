package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9113 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSQFC2 CICSQFC2 = new CICSQFC2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9113_AWA_9113 CIB9113_AWA_9113;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9113 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9113_AWA_9113>");
        CIB9113_AWA_9113 = (CIB9113_AWA_9113) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQFC2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_SQFC2_CI_NO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9113_AWA_9113.CI_NO.trim().length() == 0 
            && (CIB9113_AWA_9113.ID_TYPE.trim().length() == 0 
            || CIB9113_AWA_9113.ID_NO.trim().length() == 0 
            || CIB9113_AWA_9113.CI_NM.trim().length() == 0) 
            && CIB9113_AWA_9113.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_SQFC2_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSQFC2);
        CICSQFC2.DATA.CI_NO = CIB9113_AWA_9113.CI_NO;
        CICSQFC2.DATA.AGR_NO = CIB9113_AWA_9113.AGR_NO;
        CICSQFC2.DATA.ID_TYPE = CIB9113_AWA_9113.ID_TYPE;
        CICSQFC2.DATA.ID_NO = CIB9113_AWA_9113.ID_NO;
        CICSQFC2.DATA.CI_NM = CIB9113_AWA_9113.CI_NM;
        S000_CALL_CIZSQFC2();
    }
    public void S000_CALL_CIZSQFC2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-FIN2-CI", CICSQFC2);
        if (CICSQFC2.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSQFC2.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
