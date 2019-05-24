package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5061 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    short WS_CNT = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCMANBR IBCMANBR = new IBCMANBR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5060_AWA_5060 IBB5060_AWA_5060;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5061 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5060_AWA_5060>");
        IBB5060_AWA_5060 = (IBB5060_AWA_5060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.STR_DATE);
        if (IBB5060_AWA_5060.STR_DATE == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.SDATE_MUST_INPUT, IBB5060_AWA_5060.STR_DATE_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.END_DATE);
        if (IBB5060_AWA_5060.END_DATE == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.EDATE_MUST_INPUT, IBB5060_AWA_5060.END_DATE_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.BR);
        if (IBB5060_AWA_5060.BR == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT, IBB5060_AWA_5060.BR_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.STS);
        if (IBB5060_AWA_5060.STS == ' ') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.STS_MUST_INPUT, IBB5060_AWA_5060.STS_NO);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCMANBR);
        IBCMANBR.START_DATE = IBB5060_AWA_5060.STR_DATE;
        IBCMANBR.END_DATE = IBB5060_AWA_5060.END_DATE;
        IBCMANBR.BR = IBB5060_AWA_5060.BR;
        IBCMANBR.STS = IBB5060_AWA_5060.STS;
        S000_CALL_IBZMANBR();
    }
    public void S000_CALL_IBZMANBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-MANUAL-DRCR-BRW", IBCMANBR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
