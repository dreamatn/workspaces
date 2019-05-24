package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5258 {
    int BP_BR = 999999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBRTH BPCSBRTH = new BPCSBRTH();
    SCCGWA SCCGWA;
    BPB5258_AWA_5258 BPB5258_AWA_5258;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5258 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5258_AWA_5258>");
        BPB5258_AWA_5258 = (BPB5258_AWA_5258) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBRTH);
        B100_CHECK_INPUT();
        BPCSBRTH.START_DT = BPB5258_AWA_5258.STARTD;
        BPCSBRTH.END_DT = BPB5258_AWA_5258.ENDD;
        BPCSBRTH.TYPE = "M";
        BPCSBRTH.BR = BP_BR;
        BPCSBRTH.BASE_TYP = BPB5258_AWA_5258.RATE_TYP;
        BPCSBRTH.CCY = BPB5258_AWA_5258.CCY;
        BPCSBRTH.TENOR = BPB5258_AWA_5258.TENOR;
        S000_CALL_BPZSBRRT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5258_AWA_5258.STARTD > BPB5258_AWA_5258.ENDD) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_S_DATE_GT_E_DATE;
            WS_FLD_NO = BPB5258_AWA_5258.STARTD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZSBRRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BRW-RATE-HIS", BPCSBRTH);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
