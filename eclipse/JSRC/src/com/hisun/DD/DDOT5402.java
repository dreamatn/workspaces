package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5402 {
    String K_LOC_CCY = "CNY";
    String CPN_DD_S_ICHQ_PROC = "DD-S-ICHQ-PROC";
    String WS_ERR_MSG = " ";
    short WS_RET = 0;
    short WS_RMDR = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSICHQ DDCSICHQ = new DDCSICHQ();
    SCCGWA SCCGWA;
    DDB5402_AWA_5402 DDB5402_AWA_5402;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5402 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5402_AWA_5402>");
        DDB5402_AWA_5402 = (DDB5402_AWA_5402) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_QUERY_CHQB_INF_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5402_AWA_5402.CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_QUERY_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSICHQ);
        DDCSICHQ.AC = DDB5402_AWA_5402.AC;
        DDCSICHQ.CHQ_NO = DDB5402_AWA_5402.CHQ_NO;
        CEP.TRC(SCCGWA, DDCSICHQ.CHQ_NO);
        S000_CALL_DDZSICHQ();
    }
    public void S000_CALL_DDZSICHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_ICHQ_PROC, DDCSICHQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
