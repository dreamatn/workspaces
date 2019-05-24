package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9050 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICSPCD CICSPCD = new CICSPCD();
    BPRPARM BPRPARM = new BPRPARM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9050_AWA_9050 CIB9050_AWA_9050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9050_AWA_9050>");
        CIB9050_AWA_9050 = (CIB9050_AWA_9050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSPCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_XXT_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9050_AWA_9050.CI_NO_O);
        if (CIB9050_AWA_9050.CI_NO_O.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA-CI-NO-O INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9050_AWA_9050.CI_NO_N);
        if (CIB9050_AWA_9050.CI_NO_N.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA-CI-NO-N INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9050_AWA_9050.ID_TYPE);
        if (CIB9050_AWA_9050.ID_TYPE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA-ID-TYPE INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9050_AWA_9050.ID_NO);
        if (CIB9050_AWA_9050.ID_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA-ID-NO INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9050_AWA_9050.AGR_NO);
        if (CIB9050_AWA_9050.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA-AGR-NO INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9050_AWA_9050.AC_TYP);
        if (CIB9050_AWA_9050.AC_TYP == ' ') {
            CEP.TRC(SCCGWA, "AWA-AC-TYP INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.ERR(SCCGWA);
    }
    public void B020_INQUIRE_XXT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSPCD);
        CICSPCD.DATA.CI_NO_O = CIB9050_AWA_9050.CI_NO_O;
        CICSPCD.DATA.CI_NO_N = CIB9050_AWA_9050.CI_NO_N;
        CICSPCD.DATA.ID_TYPE = CIB9050_AWA_9050.ID_TYPE;
        CICSPCD.DATA.ID_NO = CIB9050_AWA_9050.ID_NO;
        CICSPCD.DATA.AGR_NO = CIB9050_AWA_9050.AGR_NO;
        CICSPCD.DATA.AC_TYP = CIB9050_AWA_9050.AC_TYP;
        S000_CALL_CIZSPCD();
    }
    public void S000_CALL_CIZSPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-PCD-INF", CICSPCD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
