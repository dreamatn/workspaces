package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8621 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSFEI CICSFEI = new CICSFEI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8621_AWA_8621 CIB8621_AWA_8621;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8621 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8621_AWA_8621>");
        CIB8621_AWA_8621 = (CIB8621_AWA_8621) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSFEI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_ADD_CI_FLMT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8621_AWA_8621.CI_NO.trim().length() == 0 
            || CIB8621_AWA_8621.ADC_NO.trim().length() == 0 
            || CIB8621_AWA_8621.LMT_TYP == ' ' 
            || CIB8621_AWA_8621.LMT_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_ADD_CI_FLMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSFEI);
        CICSFEI.DATA.SVS_ADC_NO = CIB8621_AWA_8621.ADC_NO;
        CICSFEI.DATA.CI_NO = CIB8621_AWA_8621.CI_NO;
        CICSFEI.DATA.SVS_ORG_CLS = CIB8621_AWA_8621.ORG_CLS;
        CICSFEI.DATA.LMT_TYP = CIB8621_AWA_8621.LMT_TYP;
        CICSFEI.DATA.LMT_KND = CIB8621_AWA_8621.LMT_KND;
        CICSFEI.DATA.LMT_CCY = CIB8621_AWA_8621.LMT_CCY;
        CICSFEI.DATA.BAL_LMT = CIB8621_AWA_8621.BAL_LMT;
        CICSFEI.DATA.CR_TOT_LMT_AMT = CIB8621_AWA_8621.CR_LMT_A;
        CICSFEI.DATA.DR_TOT_LMT_AMT = CIB8621_AWA_8621.DR_LMT_A;
        CICSFEI.FUNC = 'A';
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
