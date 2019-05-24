package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6042 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    short WS_CNT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSLNPY DDCSLNPY = new DDCSLNPY();
    SCCGWA SCCGWA;
    DDB6042_AWA_6042 DDB6042_AWA_6042;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT6042 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6042_AWA_6042>");
        DDB6042_AWA_6042 = (DDB6042_AWA_6042) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_LNPAY_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_LNPAY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6042_AWA_6042.LCNTR_NO);
        CEP.TRC(SCCGWA, DDB6042_AWA_6042.LNTX_SEQ);
        CEP.TRC(SCCGWA, DDB6042_AWA_6042.DD_AC);
        CEP.TRC(SCCGWA, DDB6042_AWA_6042.PAGE_ROW);
        CEP.TRC(SCCGWA, DDB6042_AWA_6042.PAGE_NUM);
        IBS.init(SCCGWA, DDCSLNPY);
        DDCSLNPY.FUNC = '5';
        DDCSLNPY.LNCNTR_NO = DDB6042_AWA_6042.LCNTR_NO;
        DDCSLNPY.LNTX_SEQ = DDB6042_AWA_6042.LNTX_SEQ;
        DDCSLNPY.ITEM_DATA[1-1].DD_AC = DDB6042_AWA_6042.DD_AC;
        DDCSLNPY.PAGE_ROW = DDB6042_AWA_6042.PAGE_ROW;
        DDCSLNPY.PAGE_NUM = DDB6042_AWA_6042.PAGE_NUM;
        S000_CALL_DDZSLNPY();
    }
    public void S000_CALL_DDZSLNPY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-MOD-L-PAY", DDCSLNPY);
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
