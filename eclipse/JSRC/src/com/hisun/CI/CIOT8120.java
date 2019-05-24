package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8120 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSVCH CICSVCH = new CICSVCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8120_AWA_8120 CIB8120_AWA_8120;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8120_AWA_8120>");
        CIB8120_AWA_8120 = (CIB8120_AWA_8120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSVCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQ_VCH();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8120_AWA_8120.CI_NO);
        CEP.TRC(SCCGWA, CIB8120_AWA_8120.AGR_NO);
        CEP.TRC(SCCGWA, CIB8120_AWA_8120.ID_TYPE);
        CEP.TRC(SCCGWA, CIB8120_AWA_8120.ID_NO);
        CEP.TRC(SCCGWA, CIB8120_AWA_8120.CI_NM);
        if (CIB8120_AWA_8120.CI_NO.trim().length() == 0 
            && CIB8120_AWA_8120.AGR_NO.trim().length() == 0 
            && (CIB8120_AWA_8120.ID_TYPE.trim().length() == 0 
            || CIB8120_AWA_8120.ID_NO.trim().length() == 0 
            || CIB8120_AWA_8120.CI_NM.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQ_VCH() throws IOException,SQLException,Exception {
        CICSVCH.DATA.CI_NO = CIB8120_AWA_8120.CI_NO;
        CICSVCH.DATA.ENTY_TYP = CIB8120_AWA_8120.ENTY_TYP;
        CICSVCH.DATA.AGR_NO = CIB8120_AWA_8120.AGR_NO;
        CICSVCH.DATA.ID_TYPE = CIB8120_AWA_8120.ID_TYPE;
        CICSVCH.DATA.ID_NO = CIB8120_AWA_8120.ID_NO;
        CICSVCH.DATA.CI_NM = CIB8120_AWA_8120.CI_NM;
        CICSVCH.DATA.FRM_APP = CIB8120_AWA_8120.FRM_APP;
        CICSVCH.DATA.CEL_FLG = CIB8120_AWA_8120.CEL_FLG;
        S000_CALL_CIZSVCH();
    }
    public void S000_CALL_CIZSVCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-VCH", CICSVCH);
        if (CICSVCH.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSVCH.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
