package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8701 {
    String JIBS_tmp_str[] = new String[10];
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICCHCI CICCHCI = new CICCHCI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8701_AWA_8701 CIB8701_AWA_8701;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8701 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8701_AWA_8701>");
        CIB8701_AWA_8701 = (CIB8701_AWA_8701) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCHCI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_CALL_CIZCHCI();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8701_AWA_8701.AC_NO);
        if (CIB8701_AWA_8701.AC_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB8701_AWA_8701.OLD_CINO);
        CEP.TRC(SCCGWA, CIB8701_AWA_8701.NEW_CINO);
        if (CIB8701_AWA_8701.OLD_CINO.trim().length() == 0 
            || CIB8701_AWA_8701.NEW_CINO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
        CEP.ERR(SCCGWA);
    }
    public void B020_INQUIRE_CALL_CIZCHCI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8701_AWA_8701.AC_NO);
        CEP.TRC(SCCGWA, CIB8701_AWA_8701.OLD_CINO);
        CEP.TRC(SCCGWA, CIB8701_AWA_8701.NEW_CINO);
        IBS.init(SCCGWA, CICCHCI);
        CICCHCI.INPUT.AC_NO = CIB8701_AWA_8701.AC_NO;
        CICCHCI.INPUT.OLD_CINO = CIB8701_AWA_8701.OLD_CINO;
        CICCHCI.INPUT.NEW_CINO = CIB8701_AWA_8701.NEW_CINO;
        S000_CALL_CIZCHCI();
    }
    public void S000_CALL_CIZCHCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHCI", CICCHCI);
        if (CICCHCI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCHCI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
