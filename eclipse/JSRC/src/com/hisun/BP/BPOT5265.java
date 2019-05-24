package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5265 {
    int BP_BR = 999999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBRTA BPCSBRTA = new BPCSBRTA();
    SCCGWA SCCGWA;
    BPB5265_AWA_5265 BPB5265_AWA_5265;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5265 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5265_AWA_5265>");
        BPB5265_AWA_5265 = (BPB5265_AWA_5265) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBRTA);
        B100_CHECK_INPUT();
        BPCSBRTA.BR = BP_BR;
        BPCSBRTA.START_DT = BPB5265_AWA_5265.STARTD;
        BPCSBRTA.END_DT = BPB5265_AWA_5265.ENDD;
        BPCSBRTA.BASE_TYP = BPB5265_AWA_5265.RATE_TYP;
        BPCSBRTA.CCY = BPB5265_AWA_5265.CCY;
        BPCSBRTA.TENOR = BPB5265_AWA_5265.TENOR;
        S000_CALL_BPZSBRTA();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5265_AWA_5265.STARTD > BPB5265_AWA_5265.ENDD) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_S_DATE_GT_E_DATE;
            WS_FLD_NO = BPB5265_AWA_5265.STARTD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZSBRTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BRW-RATE-ALL", BPCSBRTA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
