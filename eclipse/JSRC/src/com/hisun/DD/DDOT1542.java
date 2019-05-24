package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1542 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSCCZM DDCSCCZM = new DDCSCCZM();
    SCCGWA SCCGWA;
    DDB1542_AWA_1542 DDB1542_AWA_1542;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "FIND-DDOT1542");
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1542 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1542_AWA_1542>");
        DDB1542_AWA_1542 = (DDB1542_AWA_1542) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_030_INQ_CCZM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1542_AWA_1542.REF_NO);
        CEP.TRC(SCCGWA, DDB1542_AWA_1542.STR_DT);
        CEP.TRC(SCCGWA, DDB1542_AWA_1542.END_DT);
        CEP.TRC(SCCGWA, DDB1542_AWA_1542.STS);
        CEP.TRC(SCCGWA, DDB1542_AWA_1542.PAGE_NUM);
        CEP.TRC(SCCGWA, DDB1542_AWA_1542.PAGE_ROW);
        if (DDB1542_AWA_1542.REF_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB1542_AWA_1542.PAGE_ROW == 0 
            || DDB1542_AWA_1542.PAGE_ROW > 10) {
            DDB1542_AWA_1542.PAGE_ROW = 10;
        }
        if (DDB1542_AWA_1542.PAGE_NUM == 0) {
            DDB1542_AWA_1542.PAGE_NUM = 1;
        }
    }
    public void B010_030_INQ_CCZM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCCZM);
        DDCSCCZM.INPUT_DATA.IZM_REF_NO = DDB1542_AWA_1542.REF_NO;
        DDCSCCZM.INPUT_DATA.IZM_STR_DT = DDB1542_AWA_1542.STR_DT;
        DDCSCCZM.INPUT_DATA.IZM_END_DT = DDB1542_AWA_1542.END_DT;
        DDCSCCZM.INPUT_DATA.IZM_STS = DDB1542_AWA_1542.STS;
        DDCSCCZM.INPUT_DATA.IZM_PAGE_NUM = DDB1542_AWA_1542.PAGE_NUM;
        DDCSCCZM.INPUT_DATA.IZM_PAGE_ROW = DDB1542_AWA_1542.PAGE_ROW;
        CEP.TRC(SCCGWA, DDCSCCZM.INPUT_DATA.IZM_PAGE_NUM);
        S000_CALL_DDZSCCZM();
    }
    public void S000_CALL_DDZSCCZM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-CCZM-INF", DDCSCCZM);
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
