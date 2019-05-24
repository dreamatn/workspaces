package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5700 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMAGT CICMAGT = new CICMAGT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5700_AWA_5700 CIB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5700 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5700_AWA_5700>");
        CIB5700_AWA_5700 = (CIB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.DATA.CI_NO = CIB5700_AWA_5700.CI_NO;
        CICMAGT.DATA.ENTY_TYP = CIB5700_AWA_5700.ENTY_TYP;
        CICMAGT.DATA.ENTY_NO = CIB5700_AWA_5700.ENTY_NO;
        CICMAGT.DATA.AGT_TYP = CIB5700_AWA_5700.AGT_TYP;
        CICMAGT.FUNC = 'B';
        S000_CALL_CIZMAGT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5700_AWA_5700.CI_NO);
        CEP.TRC(SCCGWA, CIB5700_AWA_5700.ENTY_TYP);
        CEP.TRC(SCCGWA, CIB5700_AWA_5700.ENTY_NO);
        CEP.TRC(SCCGWA, CIB5700_AWA_5700.AGT_TYP);
        if (CIB5700_AWA_5700.CI_NO.trim().length() == 0 
            && CIB5700_AWA_5700.AGT_TYP.trim().length() == 0 
            && (CIB5700_AWA_5700.ENTY_TYP == ' ' 
            || CIB5700_AWA_5700.ENTY_NO.trim().length() == 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMAGT.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
