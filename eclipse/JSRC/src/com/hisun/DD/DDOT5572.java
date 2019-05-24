package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5572 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSRPPB DDCSRPPB = new DDCSRPPB();
    SCCGWA SCCGWA;
    DDB5572_AWA_5572 DDB5572_AWA_5572;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5572 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5572_AWA_5572>");
        DDB5572_AWA_5572 = (DDB5572_AWA_5572) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5572_AWA_5572.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB5572_AWA_5572.RPT_NUM);
        if (DDB5572_AWA_5572.RPT_NUM == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5572_AWA_5572.AC);
        CEP.TRC(SCCGWA, DDB5572_AWA_5572.OLD_PAGE);
        CEP.TRC(SCCGWA, DDB5572_AWA_5572.OLD_LINE);
        CEP.TRC(SCCGWA, DDB5572_AWA_5572.RPT_NUM);
        IBS.init(SCCGWA, DDCSRPPB);
        DDCSRPPB.AC = DDB5572_AWA_5572.AC;
        DDCSRPPB.OLD_PAGE = DDB5572_AWA_5572.OLD_PAGE;
        DDCSRPPB.OLD_LINE = DDB5572_AWA_5572.OLD_LINE;
        DDCSRPPB.RPT_NUM = DDB5572_AWA_5572.RPT_NUM;
        S000_CALL_DDZSRPPB();
    }
    public void S000_CALL_DDZSRPPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSRPPB", DDCSRPPB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
