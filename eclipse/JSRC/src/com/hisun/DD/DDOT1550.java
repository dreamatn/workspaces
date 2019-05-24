package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1550 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSQZM DDCSQZM = new DDCSQZM();
    SCCGWA SCCGWA;
    DDB1550_AWA_1550 DDB1550_AWA_1550;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "FIND-DDOT1550");
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1550 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1550_AWA_1550>");
        DDB1550_AWA_1550 = (DDB1550_AWA_1550) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_030_INQ_CCZM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1550_AWA_1550.OPEN_BV);
        CEP.TRC(SCCGWA, DDB1550_AWA_1550.REF_NO);
        if (DDB1550_AWA_1550.OPEN_BV.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB1550_AWA_1550.REF_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_030_INQ_CCZM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQZM);
        DDCSQZM.INPUT_DATA.IZM_OPEN_BV = DDB1550_AWA_1550.OPEN_BV;
        DDCSQZM.INPUT_DATA.IZM_REF_NO = DDB1550_AWA_1550.REF_NO;
        S000_CALL_DDZSQZM();
    }
    public void S000_CALL_DDZSQZM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-CCZM", DDCSQZM);
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
