package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1540 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSLZM DDCSLZM = new DDCSLZM();
    SCCGWA SCCGWA;
    DDB1540_AWA_1540 DDB1540_AWA_1540;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1540 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1540_AWA_1540>");
        DDB1540_AWA_1540 = (DDB1540_AWA_1540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_030_INQ_CCZM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_030_INQ_CCZM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLZM);
        DDCSLZM.INPUT_DATA.IZM_CI_NO = DDB1540_AWA_1540.CI_NO;
        DDCSLZM.INPUT_DATA.IZM_OPEN_DT = DDB1540_AWA_1540.OPEN_DT;
        DDCSLZM.INPUT_DATA.IZM_OPEN_BV = DDB1540_AWA_1540.OPEN_BV;
        DDCSLZM.INPUT_DATA.IZM_PAGE_ROW = DDB1540_AWA_1540.PAGE_ROW;
        DDCSLZM.INPUT_DATA.IZM_PAGE_NUM = DDB1540_AWA_1540.PAGE_NUM;
        DDCSLZM.INPUT_DATA.IZM_STS = DDB1540_AWA_1540.STS;
        DDCSLZM.INPUT_DATA.IZM_START_DT = DDB1540_AWA_1540.START_DT;
        DDCSLZM.INPUT_DATA.IZM_END_DT = DDB1540_AWA_1540.END_DT;
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_CI_NO);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_OPEN_DT);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_OPEN_BV);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_PAGE_ROW);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_PAGE_NUM);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_STS);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_START_DT);
        CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_END_DT);
        S000_CALL_DDZSLZM();
    }
    public void S000_CALL_DDZSLZM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-LS-CCZM", DDCSLZM);
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
