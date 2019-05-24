package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8640 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICBLRG CICBLRG = new CICBLRG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8640_AWA_8640 CIB8640_AWA_8640;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8640 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8640_AWA_8640>");
        CIB8640_AWA_8640 = (CIB8640_AWA_8640) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBLRG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_BROWSE_CI_FLRG();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8640_AWA_8640.CI_NO.trim().length() == 0 
            && CIB8640_AWA_8640.ADC_NO.trim().length() == 0 
            && CIB8640_AWA_8640.AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_BROWSE_CI_FLRG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICBLRG);
        CICBLRG.DATA.CI_NO = CIB8640_AWA_8640.CI_NO;
        CICBLRG.DATA.SVS_ADC_NO = CIB8640_AWA_8640.ADC_NO;
        CICBLRG.DATA.AGR_NO = CIB8640_AWA_8640.AGR_NO;
        CEP.TRC(SCCGWA, CIB8640_AWA_8640.CI_NO);
        CEP.TRC(SCCGWA, CICBLRG.DATA.CI_NO);
        S000_CALL_CIZBLRG();
    }
    public void S000_CALL_CIZBLRG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BROW-PROC-FLRG", CICBLRG);
        if (CICBLRG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICBLRG.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
