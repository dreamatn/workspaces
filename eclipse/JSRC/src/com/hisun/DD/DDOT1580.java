package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1580 {
    String K_OUTPUT_FMT1 = "DD158";
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSQDP DDCSQDP = new DDCSQDP();
    SCCGWA SCCGWA;
    DDB1580_AWA_1580 DDB1580_AWA_1580;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1580 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1580_AWA_1580>");
        DDB1580_AWA_1580 = (DDB1580_AWA_1580) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_01_INQ_DP();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_01_INQ_DP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQDP);
        CEP.TRC(SCCGWA, DDB1580_AWA_1580.CI_NO);
        CEP.TRC(SCCGWA, DDB1580_AWA_1580.CK_FLG);
        CEP.TRC(SCCGWA, DDB1580_AWA_1580.BZ_FLG);
        DDCSQDP.INPUT_DATA.CI_NO = DDB1580_AWA_1580.CI_NO;
        DDCSQDP.INPUT_DATA.CK_FLG = DDB1580_AWA_1580.CK_FLG;
        DDCSQDP.INPUT_DATA.BZ_FLG = DDB1580_AWA_1580.BZ_FLG;
        S000_CALL_DDZSQDP();
    }
    public void S000_CALL_DDZSQDP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-INQ-DP", DDCSQDP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
