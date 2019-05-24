package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5510 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMID CICSMID = new CICSMID();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5510_AWA_5510 CIB5510_AWA_5510;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5510 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5510_AWA_5510>");
        CIB5510_AWA_5510 = (CIB5510_AWA_5510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMID);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_BRW_ID_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5510_AWA_5510.CI_NO);
        if (CIB5510_AWA_5510.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
    }
    public void B020_BRW_ID_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMID);
        CICSMID.DATA.CI_NO = CIB5510_AWA_5510.CI_NO;
        CICSMID.FUNC = 'B';
        S000_CALL_CIZSMID();
    }
    public void S000_CALL_CIZSMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-M-ID-INF", CICSMID);
        if (CICSMID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMID.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
