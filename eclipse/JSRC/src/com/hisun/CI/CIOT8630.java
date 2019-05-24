package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8630 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMFRL CICMFRL = new CICMFRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8630_AWA_8630 CIB8630_AWA_8630;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8630 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8630_AWA_8630>");
        CIB8630_AWA_8630 = (CIB8630_AWA_8630) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMFRL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_BROWSE_CI_FLMT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8630_AWA_8630.ADC_NO.trim().length() == 0 
            && CIB8630_AWA_8630.CI_NO.trim().length() == 0 
            && CIB8630_AWA_8630.AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "核准件号/客户�?/客户账号必输其一");
        }
    }
    public void B020_BROWSE_CI_FLMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMFRL);
        CICMFRL.DATA.CI_NO = CIB8630_AWA_8630.CI_NO;
        CICMFRL.DATA.SVS_ADC_NO = CIB8630_AWA_8630.ADC_NO;
        CICMFRL.DATA.AGR_NO = CIB8630_AWA_8630.AGR_NO;
        CICMFRL.FUNC = 'B';
        S000_CALL_CIZMFRL();
    }
    public void S000_CALL_CIZMFRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PROC-FLRL", CICMFRL);
        if (CICMFRL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMFRL.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
