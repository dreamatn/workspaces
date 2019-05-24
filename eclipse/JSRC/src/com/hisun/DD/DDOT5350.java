package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5350 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSAPPR DDCSAPPR = new DDCSAPPR();
    SCCGWA SCCGWA;
    DDB5350_AWA_5350 DDB5350_AWA_5350;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5350 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5350_AWA_5350>");
        DDB5350_AWA_5350 = (DDB5350_AWA_5350) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_CALL_APPR_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5350_AWA_5350.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5350_AWA_5350.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CALL_APPR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5350_AWA_5350.FUNC);
        CEP.TRC(SCCGWA, DDB5350_AWA_5350.AC);
        CEP.TRC(SCCGWA, DDB5350_AWA_5350.REMARK);
        IBS.init(SCCGWA, DDCSAPPR);
        DDCSAPPR.FUNC = DDB5350_AWA_5350.FUNC;
        DDCSAPPR.AC = DDB5350_AWA_5350.AC;
        DDCSAPPR.LIC_TYPE = DDB5350_AWA_5350.LIC_TYPE;
        DDCSAPPR.LIC_NO = DDB5350_AWA_5350.LIC_NO;
        DDCSAPPR.REMARK = DDB5350_AWA_5350.REMARK;
        S000_CALL_DDZSAPPR();
    }
    public void S000_CALL_DDZSAPPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSAPPR", DDCSAPPR);
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
