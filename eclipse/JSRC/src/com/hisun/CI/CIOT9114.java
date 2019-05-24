package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9114 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSPQC CICSPQC = new CICSPQC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9114_AWA_9114 CIB9114_AWA_9114;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9114 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9114_AWA_9114>");
        CIB9114_AWA_9114 = (CIB9114_AWA_9114) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSPQC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_SPQC_CI_NO();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9114_AWA_9114.PAGE_NUM == 0 
            || CIB9114_AWA_9114.PAGE_ROW == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_SPQC_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSPQC);
        CICSPQC.DATA.TEL_NO = CIB9114_AWA_9114.TEL_NO;
        CICSPQC.DATA.CONT_NO = CIB9114_AWA_9114.CONT_NO;
        CICSPQC.DATA.PAGE_NUM = CIB9114_AWA_9114.PAGE_NUM;
        CICSPQC.DATA.PAGE_ROW = CIB9114_AWA_9114.PAGE_ROW;
        S000_CALL_CIZSPQC();
    }
    public void S000_CALL_CIZSPQC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CINO-BY-MP", CICSPQC);
        if (CICSPQC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSPQC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
