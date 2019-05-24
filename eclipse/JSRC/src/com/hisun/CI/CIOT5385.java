package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5385 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSCRC CICSCRC = new CICSCRC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5385_AWA_5385 CIB5385_AWA_5385;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5385 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5385_AWA_5385>");
        CIB5385_AWA_5385 = (CIB5385_AWA_5385) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICSCRC);
        CICSCRC.DATA.CI_NO = CIB5385_AWA_5385.CI_NO;
        CICSCRC.DATA.ID_TYPE = CIB5385_AWA_5385.ID_TYPE;
        CICSCRC.DATA.ID_NO = CIB5385_AWA_5385.ID_NO;
        CICSCRC.DATA.CI_NM = CIB5385_AWA_5385.CI_NM;
        S000_CALL_CIZSCRC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5385_AWA_5385.CI_NO);
        CEP.TRC(SCCGWA, CIB5385_AWA_5385.ID_TYPE);
        CEP.TRC(SCCGWA, CIB5385_AWA_5385.ID_NO);
        CEP.TRC(SCCGWA, CIB5385_AWA_5385.CI_NM);
        if (CIB5385_AWA_5385.CI_NO.trim().length() == 0 
            || CIB5385_AWA_5385.ID_TYPE.trim().length() == 0 
            || CIB5385_AWA_5385.ID_NO.trim().length() == 0 
            || CIB5385_AWA_5385.CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-TYPE MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户号以及客户三要素必须输入");
        }
    }
    public void S000_CALL_CIZSCRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CLOSE-RSV-CI-NO", CICSCRC);
        if (CICSCRC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSCRC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
