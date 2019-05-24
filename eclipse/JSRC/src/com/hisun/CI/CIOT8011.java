package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8011 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICUACCK CICUACCK = new CICUACCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8011_AWA_8011 CIB8011_AWA_8011;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8011 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8011_AWA_8011>");
        CIB8011_AWA_8011 = (CIB8011_AWA_8011) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICUACCK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_AC_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8011_AWA_8011.CI_NO.trim().length() == 0 
            && CIB8011_AWA_8011.AC_NO.trim().length() == 0 
            && (CIB8011_AWA_8011.ID_TYP.trim().length() == 0 
            || CIB8011_AWA_8011.ID_NO.trim().length() == 0 
            || CIB8011_AWA_8011.CI_NM.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "CI-NO AC ID MUST INPUT ONE ");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_AC_INF() throws IOException,SQLException,Exception {
        CICUACCK.DATA.INQ_FLG = CIB8011_AWA_8011.INQ_FLG;
        CICUACCK.DATA.AC_NO = CIB8011_AWA_8011.AC_NO;
        CICUACCK.DATA.CI_NO = CIB8011_AWA_8011.CI_NO;
        CICUACCK.DATA.ID_TYPE = CIB8011_AWA_8011.ID_TYP;
        CICUACCK.DATA.ID_NO = CIB8011_AWA_8011.ID_NO;
        CICUACCK.DATA.CI_NM = CIB8011_AWA_8011.CI_NM;
        CICUACCK.DATA.OPEN_DT = CIB8011_AWA_8011.OPEN_DT;
        CICUACCK.DATA.AC_TYPE = CIB8011_AWA_8011.AC_TYPE;
        CICUACCK.DATA.CHK_FLG = CIB8011_AWA_8011.CHK_FLG;
        CICUACCK.DATA.DOUBT_TP = CIB8011_AWA_8011.DOUBT_TP;
        CICUACCK.DATA.TREAT_TP = CIB8011_AWA_8011.TREAT_TP;
        S000_CALL_CIZUACCK();
    }
    public void S000_CALL_CIZUACCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-UPD-ACCK", CICUACCK);
        if (CICUACCK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICUACCK.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
