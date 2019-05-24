package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5380 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSORC CICSORC = new CICSORC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5380_AWA_5380 CIB5380_AWA_5380;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5380 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5380_AWA_5380>");
        CIB5380_AWA_5380 = (CIB5380_AWA_5380) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICSORC);
        CICSORC.DATA.CI_TYP = CIB5380_AWA_5380.CI_TYP;
        CICSORC.DATA.ID_TYPE = CIB5380_AWA_5380.ID_TYPE;
        CICSORC.DATA.ID_NO = CIB5380_AWA_5380.ID_NO;
        CICSORC.DATA.CI_NM = CIB5380_AWA_5380.CI_NM;
        S000_CALL_CIZSORC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5380_AWA_5380.CI_TYP);
        CEP.TRC(SCCGWA, CIB5380_AWA_5380.ID_TYPE);
        CEP.TRC(SCCGWA, CIB5380_AWA_5380.ID_NO);
        CEP.TRC(SCCGWA, CIB5380_AWA_5380.CI_NM);
        if (CIB5380_AWA_5380.CI_TYP == ' ') {
            CEP.TRC(SCCGWA, "CI-TYPE MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_MUST_INPUT);
        }
        if (CIB5380_AWA_5380.ID_TYPE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ID-TYPE MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_TYP_MUST_INPUT);
        }
        if (CIB5380_AWA_5380.ID_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ID-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NO_MUST_INPUT);
        }
        if (CIB5380_AWA_5380.CI_NM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NM MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户姓名必须输入");
        }
    }
    public void S000_CALL_CIZSORC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-RSV-CI-NO", CICSORC);
        if (CICSORC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSORC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
