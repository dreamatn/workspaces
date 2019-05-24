package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8620 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSFEI CICSFEI = new CICSFEI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8620_AWA_8620 CIB8620_AWA_8620;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8620 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8620_AWA_8620>");
        CIB8620_AWA_8620 = (CIB8620_AWA_8620) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSFEI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_BROWSE_CI_FLMT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8620_AWA_8620.ADC_NO.trim().length() == 0 
            && CIB8620_AWA_8620.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "核准件号和客户号必输其一");
        }
    }
    public void B020_BROWSE_CI_FLMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSFEI);
        CICSFEI.DATA.SVS_ADC_NO = CIB8620_AWA_8620.ADC_NO;
        CICSFEI.DATA.CI_NO = CIB8620_AWA_8620.CI_NO;
        CICSFEI.FUNC = 'B';
        CEP.TRC(SCCGWA, CIB8620_AWA_8620.CI_NO);
        CEP.TRC(SCCGWA, CICSFEI.DATA.CI_NO);
        S000_CALL_CIZSFEI();
    }
    public void S000_CALL_CIZSFEI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-FEA-INF", CICSFEI);
        if (CICSFEI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSFEI.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
