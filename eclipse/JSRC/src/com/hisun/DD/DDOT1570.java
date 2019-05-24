package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1570 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSCZM DDCSCZM = new DDCSCZM();
    SCCGWA SCCGWA;
    DDB1570_AWA_1570 DDB1570_AWA_1570;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1570 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1570_AWA_1570>");
        DDB1570_AWA_1570 = (DDB1570_AWA_1570) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_030_INQ_CCZM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB1570_AWA_1570.OPEN_BV.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCZM_BV_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_030_INQ_CCZM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1570_AWA_1570.FUNC);
        CEP.TRC(SCCGWA, DDB1570_AWA_1570.OPEN_BV);
        CEP.TRC(SCCGWA, DDB1570_AWA_1570.REF_NO);
        IBS.init(SCCGWA, DDCSCZM);
        DDCSCZM.INPUT_DATA.IZM_OPEN_BV = DDB1570_AWA_1570.OPEN_BV;
        DDCSCZM.INPUT_DATA.IZM_FUNC = DDB1570_AWA_1570.FUNC;
        DDCSCZM.IZM_BV_CODE = DDB1570_AWA_1570.BV_CODE;
        S000_CALL_DDZSCZM();
    }
    public void S000_CALL_DDZSCZM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-UPD-CCZM", DDCSCZM);
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
