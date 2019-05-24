package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8010 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICBACCK CICBACCK = new CICBACCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8010_AWA_8010 CIB8010_AWA_8010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8010_AWA_8010>");
        CIB8010_AWA_8010 = (CIB8010_AWA_8010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBACCK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_AC_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8010_AWA_8010.CI_NO.trim().length() == 0 
            && CIB8010_AWA_8010.AC_NO.trim().length() == 0 
            && (CIB8010_AWA_8010.ID_TYP.trim().length() == 0 
            || CIB8010_AWA_8010.ID_NO.trim().length() == 0 
            || CIB8010_AWA_8010.CI_NM.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "CI-NO AC ID MUST INPUT ONE ");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_AC_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8010_AWA_8010.CI_NO);
        CEP.TRC(SCCGWA, CIB8010_AWA_8010.AC_NO);
        CEP.TRC(SCCGWA, CIB8010_AWA_8010.ID_TYP);
        CEP.TRC(SCCGWA, CIB8010_AWA_8010.ID_NO);
        CEP.TRC(SCCGWA, CIB8010_AWA_8010.CI_NM);
        CICBACCK.DATA.CI_NO = CIB8010_AWA_8010.CI_NO;
        CICBACCK.DATA.AC_NO = CIB8010_AWA_8010.AC_NO;
        CICBACCK.DATA.ID_TYPE = CIB8010_AWA_8010.ID_TYP;
        CICBACCK.DATA.ID_NO = CIB8010_AWA_8010.ID_NO;
        CICBACCK.DATA.CI_NM = CIB8010_AWA_8010.CI_NM;
        CICBACCK.DATA.AC_TYPE = CIB8010_AWA_8010.AC_TYPE;
        CICBACCK.DATA.CHK_FLG = CIB8010_AWA_8010.CHK_FLG;
        S000_CALL_CIZBACCK();
    }
    public void S000_CALL_CIZBACCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BRW-ACCK", CICBACCK);
        if (CICBACCK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICBACCK.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
