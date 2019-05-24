package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9111 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSQPC2 CICSQPC2 = new CICSQPC2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9111_AWA_9111 CIB9111_AWA_9111;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9111 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9111_AWA_9111>");
        CIB9111_AWA_9111 = (CIB9111_AWA_9111) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQPC2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_SQPC2_CI_NO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9111_AWA_9111.CI_NO.trim().length() == 0 
            && (CIB9111_AWA_9111.ID_TYPE.trim().length() == 0 
            || CIB9111_AWA_9111.ID_NO.trim().length() == 0 
            || CIB9111_AWA_9111.CI_NM.trim().length() == 0) 
            && CIB9111_AWA_9111.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_SQPC2_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSQPC2);
        CICSQPC2.DATA.CI_NO = CIB9111_AWA_9111.CI_NO;
        CICSQPC2.DATA.AGR_NO = CIB9111_AWA_9111.AGR_NO;
        CICSQPC2.DATA.ID_TYPE = CIB9111_AWA_9111.ID_TYPE;
        CICSQPC2.DATA.ID_NO = CIB9111_AWA_9111.ID_NO;
        CICSQPC2.DATA.CI_NM = CIB9111_AWA_9111.CI_NM;
        S000_CALL_CIZSQPC2();
    }
    public void S000_CALL_CIZSQPC2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-PER2-CI", CICSQPC2);
        if (CICSQPC2.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSQPC2.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
