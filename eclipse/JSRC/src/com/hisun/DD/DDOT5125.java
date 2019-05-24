package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5125 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMTDR DDCSMTDR = new DDCSMTDR();
    SCCGWA SCCGWA;
    DDB5125_AWA_5125 DDB5125_AWA_5125;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5125 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5125_AWA_5125>");
        DDB5125_AWA_5125 = (DDB5125_AWA_5125) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5125_AWA_5125.B_DD_OPT);
        CEP.TRC(SCCGWA, DDB5125_AWA_5125.B_DD_AC);
        CEP.TRC(SCCGWA, DDB5125_AWA_5125.B_DD_CCY);
        IBS.init(SCCGWA, DDCSMTDR);
        DDCSMTDR.I_OPT = DDB5125_AWA_5125.B_DD_OPT;
        DDCSMTDR.I_DD_AC = DDB5125_AWA_5125.B_DD_AC;
        DDCSMTDR.I_CCY = DDB5125_AWA_5125.B_DD_CCY;
        DDCSMTDR.I_FLG = DDB5125_AWA_5125.B_DD_FLG;
        DDCSMTDR.I_STR_DT = DDB5125_AWA_5125.STR_DT;
        DDCSMTDR.I_END_DT = DDB5125_AWA_5125.END_DT;
        CEP.TRC(SCCGWA, DDCSMTDR.I_OPT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_DD_AC);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CCY);
        S000_CALL_DDZSMTDR();
    }
    public void S000_CALL_DDZSMTDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMTDR", DDCSMTDR);
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
