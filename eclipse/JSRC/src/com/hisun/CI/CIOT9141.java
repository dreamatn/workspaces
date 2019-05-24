package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9141 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSQJI CICSQJI = new CICSQJI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9141_AWA_9141 CIB9141_AWA_9141;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9141 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9141_AWA_9141>");
        CIB9141_AWA_9141 = (CIB9141_AWA_9141) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICSQJI);
        CICSQJI.DATA.CI_NO = CIB9141_AWA_9141.CI_NO;
        CICSQJI.DATA.JCI_NO = CIB9141_AWA_9141.JCI_NO;
        CICSQJI.DATA.AGR_NO = CIB9141_AWA_9141.AGR_NO;
        CICSQJI.FUNC = CIB9141_AWA_9141.FUNC;
        CICSQJI.DATA.PAGE_NUM = CIB9141_AWA_9141.PAGE_NUM;
        CICSQJI.DATA.PAGE_ROW = CIB9141_AWA_9141.PAGE_ROW;
        S000_CALL_CIZSQJI();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_CIZSQJI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-JRL-INF", CICSQJI);
        if (CICSQJI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSQJI.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
