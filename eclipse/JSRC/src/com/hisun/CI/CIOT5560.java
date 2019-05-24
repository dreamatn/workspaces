package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5560 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSARC CICSARC = new CICSARC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5560_AWA_5560 CIB5560_AWA_5560;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5560 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5560_AWA_5560>");
        CIB5560_AWA_5560 = (CIB5560_AWA_5560) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSARC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_ADD_REL_CRS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5560_AWA_5560.REL_NAME);
        CEP.TRC(SCCGWA, CIB5560_AWA_5560.REL_IDTP);
        CEP.TRC(SCCGWA, CIB5560_AWA_5560.REL_IDNO);
    }
    public void B020_ADD_REL_CRS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSARC);
        CICSARC.DATA.REL_NAME = CIB5560_AWA_5560.REL_NAME;
        CICSARC.DATA.REL_IDTP = CIB5560_AWA_5560.REL_IDTP;
        CICSARC.DATA.REL_IDNO = CIB5560_AWA_5560.REL_IDNO;
        CICSARC.DATA.CRS_TYPE = CIB5560_AWA_5560.CRS_TYPE;
        CICSARC.DATA.CRS_DESC = CIB5560_AWA_5560.CRS_DESC;
        CICSARC.DATA.PROOF_DT = CIB5560_AWA_5560.PROOF_DT;
        CICSARC.DATA.PROOF_CH = CIB5560_AWA_5560.PROOF_CH;
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            CICSARC.DATA.CRS_AREA[WS_I-1].CRS_ADR = CIB5560_AWA_5560.CRS_AREA[WS_I-1].CRS_ADR;
            CICSARC.DATA.CRS_AREA[WS_I-1].CRS_DSNO = CIB5560_AWA_5560.CRS_AREA[WS_I-1].CRS_DSNO;
            CICSARC.DATA.CRS_AREA[WS_I-1].CRS_NDCD = CIB5560_AWA_5560.CRS_AREA[WS_I-1].CRS_NDCD;
            CICSARC.DATA.CRS_AREA[WS_I-1].CRS_NDRE = CIB5560_AWA_5560.CRS_AREA[WS_I-1].CRS_NDRE;
        }
        S000_CALL_CIZSARC();
    }
    public void S000_CALL_CIZSARC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-ADD-REL-CRS", CICSARC);
        if (CICSARC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSARC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
