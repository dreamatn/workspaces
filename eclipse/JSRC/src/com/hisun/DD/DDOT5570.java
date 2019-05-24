package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5570 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQRPT DDCSQRPT = new DDCSQRPT();
    SCCGWA SCCGWA;
    DDB5570_AWA_5570 DDB5570_AWA_5570;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5570 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5570_AWA_5570>");
        DDB5570_AWA_5570 = (DDB5570_AWA_5570) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5570_AWA_5570.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5570_AWA_5570.PAGE != 0 
            && DDB5570_AWA_5570.LINE == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5570_AWA_5570.PAGE == 0 
            && DDB5570_AWA_5570.LINE != 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5570_AWA_5570.AC);
        CEP.TRC(SCCGWA, DDB5570_AWA_5570.PAGE);
        CEP.TRC(SCCGWA, DDB5570_AWA_5570.LINE);
        IBS.init(SCCGWA, DDCSQRPT);
        DDCSQRPT.AC = DDB5570_AWA_5570.AC;
        DDCSQRPT.PAGE = DDB5570_AWA_5570.PAGE;
        DDCSQRPT.LINE = DDB5570_AWA_5570.LINE;
        CEP.TRC(SCCGWA, DDCSQRPT.AC);
        CEP.TRC(SCCGWA, DDCSQRPT.PAGE);
        CEP.TRC(SCCGWA, DDCSQRPT.LINE);
        S000_CALL_DDZSQRPT();
    }
    public void S000_CALL_DDZSQRPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSQRPT", DDCSQRPT);
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
