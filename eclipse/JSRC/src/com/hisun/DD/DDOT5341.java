package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5341 {
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSSACF DDCSSACF = new DDCSSACF();
    SCCGWA SCCGWA;
    DDB5341_AWA_5341 DDB5341_AWA_5341;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5341 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5341_AWA_5341>");
        DDB5341_AWA_5341 = (DDB5341_AWA_5341) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5341_AWA_5341.CI_NO);
        CEP.TRC(SCCGWA, DDB5341_AWA_5341.ID_TYPE);
        CEP.TRC(SCCGWA, DDB5341_AWA_5341.ID_NO);
        IBS.init(SCCGWA, DDCSSACF);
        DDCSSACF.CI_NO = DDB5341_AWA_5341.CI_NO;
        DDCSSACF.ID_TYPE = DDB5341_AWA_5341.ID_TYPE;
        DDCSSACF.ID_NO = DDB5341_AWA_5341.ID_NO;
        CEP.TRC(SCCGWA, DDCSSACF.CI_NO);
        CEP.TRC(SCCGWA, DDCSSACF.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSSACF.ID_NO);
        S000_CALL_DDZSSACF();
    }
    public void S000_CALL_DDZSSACF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSSACF", DDCSSACF);
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
