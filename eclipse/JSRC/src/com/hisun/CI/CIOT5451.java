package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5451 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICCMCI CICCMCI = new CICCMCI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5451_AWA_5451 CIB5451_AWA_5451;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5451 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5451_AWA_5451>");
        CIB5451_AWA_5451 = (CIB5451_AWA_5451) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCMCI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_BROWSE_CI_FLRG();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5451_AWA_5451.CI_NO.trim().length() == 0 
            || CIB5451_AWA_5451.CI_NO1.trim().length() == 0 
            || CIB5451_AWA_5451.COMB_RES.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_BROWSE_CI_FLRG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCMCI);
        CICCMCI.DATA.MAIN_CI_NO = CIB5451_AWA_5451.CI_NO;
        CICCMCI.DATA.PRI_CI_NO = CIB5451_AWA_5451.CI_NO1;
        CICCMCI.DATA.COMB_TYP = CIB5451_AWA_5451.COMB_TYP;
        CICCMCI.DATA.COMB_RES = CIB5451_AWA_5451.COMB_RES.charAt(0);
        CICCMCI.DATA.ELSE_RES = CIB5451_AWA_5451.ELSE_RES.charAt(0);
        S000_CALL_CIZCMCI();
    }
    public void S000_CALL_CIZCMCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-COMB-PROC-CI", CICCMCI);
        if (CICCMCI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCMCI.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
