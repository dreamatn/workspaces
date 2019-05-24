package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8530 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICTACR CICTACR = new CICTACR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8530_AWA_8530 CIB8530_AWA_8530;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8530 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8530_AWA_8530>");
        CIB8530_AWA_8530 = (CIB8530_AWA_8530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICTACR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_AC_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8530_AWA_8530.SRCH_FLG);
        if (CIB8530_AWA_8530.SRCH_FLG == ' ') {
            CEP.TRC(SCCGWA, "SRCH-FLG MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_AC_INF() throws IOException,SQLException,Exception {
        CICTACR.DATA.CI_NO = CIB8530_AWA_8530.CI_NO;
        CICTACR.DATA.AGR_NO = CIB8530_AWA_8530.AGR_NO;
        CICTACR.FUNC = CIB8530_AWA_8530.SRCH_FLG;
        for (WS_I = 1; WS_I <= 30 
            && CIB8530_AWA_8530.REL_AREA[WS_I-1].REL_TYP.trim().length() != 0; WS_I += 1) {
            CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP = CIB8530_AWA_8530.REL_AREA[WS_I-1].REL_TYP;
        }
        CICTACR.DATA.ENTY_TYP = CIB8530_AWA_8530.ENTY_TYP;
        CICTACR.DATA.FRM_APP = CIB8530_AWA_8530.FRM_APP;
        CICTACR.DATA.CNTRCT_TYP = CIB8530_AWA_8530.CNTR_TYP;
        CICTACR.DATA.PROD_CD = CIB8530_AWA_8530.PROD_CD;
        S000_CALL_CIZTACR();
    }
    public void S000_CALL_CIZTACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BRW-CIZTACR", CICTACR);
        if (CICTACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICTACR.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
