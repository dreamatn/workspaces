package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5481 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSIDE CICSIDE = new CICSIDE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5480_AWA_5480 CIB5480_AWA_5480;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5481 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5480_AWA_5480>");
        CIB5480_AWA_5480 = (CIB5480_AWA_5480) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICSIDE);
        CICSIDE.DATA.CI_NO = CIB5480_AWA_5480.CI_NO;
        CICSIDE.DATA.IDE_STS_CODE = CIB5480_AWA_5480.STS_CD;
        CICSIDE.FUNC = 'A';
        S000_CALL_CIZSIDE();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5480_AWA_5480.CI_NO);
        CEP.TRC(SCCGWA, CIB5480_AWA_5480.STS_CD);
        if (CIB5480_AWA_5480.CI_NO.trim().length() == 0 
            || CIB5480_AWA_5480.STS_CD.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO AND STS_CD MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void S000_CALL_CIZSIDE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MOD-IDE-STS", CICSIDE);
        if (CICSIDE.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSIDE.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
