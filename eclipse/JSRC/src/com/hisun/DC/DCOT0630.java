package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0630 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSJSTC DCCSJSTC = new DCCSJSTC();
    SCCGWA SCCGWA;
    DCB0630_AWA_0630 DCB0630_AWA_0630;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0630 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0630_AWA_0630>");
        DCB0630_AWA_0630 = (DCB0630_AWA_0630) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0630_AWA_0630.CARD_NO);
        CEP.TRC(SCCGWA, DCB0630_AWA_0630.CARD_TYP);
        CEP.TRC(SCCGWA, DCB0630_AWA_0630.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB0630_AWA_0630.PAGE_ROW);
        if (DCB0630_AWA_0630.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_MUST_INPUT;
            WS_FLD_NO = DCB0630_AWA_0630.CARD_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSJSTC);
        DCCSJSTC.CARD_NO = DCB0630_AWA_0630.CARD_NO;
        DCCSJSTC.PAGE_ROW = DCB0630_AWA_0630.PAGE_ROW;
        DCCSJSTC.PAGE_NUM = DCB0630_AWA_0630.PAGE_NUM;
        CEP.TRC(SCCGWA, DCB0630_AWA_0630.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB0630_AWA_0630.PAGE_ROW);
        S000_CALL_DCZSJSTC();
    }
    public void S000_CALL_DCZSJSTC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-JST-BROW", DCCSJSTC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
