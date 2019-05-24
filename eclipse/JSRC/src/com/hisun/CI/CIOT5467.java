package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5467 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICSVER CICSVER = new CICSVER();
    SCCGWA SCCGWA;
    CIB5467_AWA_5467 CIB5467_AWA_5467;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5467 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5467_AWA_5467>");
        CIB5467_AWA_5467 = (CIB5467_AWA_5467) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        CICSVER.CI_DATE.CI_NO = CIB5467_AWA_5467.CI_NO;
        CICSVER.CI_DATE.ID_TYP = CIB5467_AWA_5467.ID_TYP;
        CICSVER.CI_DATE.ID_NO = CIB5467_AWA_5467.ID_NO;
        CICSVER.CI_DATE.CI_NM = CIB5467_AWA_5467.CI_NM;
        CICSVER.CI_DATE.AC_NO = CIB5467_AWA_5467.AC_NO;
        CICSVER.FUNC = 'I';
        B020_CALL_CIZSVER();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
        if (CIB5467_AWA_5467.CI_NO.trim().length() == 0 
            && CIB5467_AWA_5467.ID_NO.trim().length() == 0 
            && CIB5467_AWA_5467.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if ((CIB5467_AWA_5467.CI_NO.trim().length() > 0 
            && CIB5467_AWA_5467.ID_TYP.trim().length() == 0) 
            || (CIB5467_AWA_5467.CI_NO.trim().length() == 0 
            && CIB5467_AWA_5467.ID_TYP.trim().length() > 0)) {
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_CALL_CIZSVER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-VER-CIZSVER", CICSVER);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
