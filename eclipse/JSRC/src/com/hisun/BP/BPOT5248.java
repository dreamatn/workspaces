package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5248 {
    int BP_BR = 999999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBRTH BPCSBRTH = new BPCSBRTH();
    SCCGWA SCCGWA;
    BPB5248_AWA_5248 BPB5248_AWA_5248;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5248 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5248_AWA_5248>");
        BPB5248_AWA_5248 = (BPB5248_AWA_5248) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBRTH);
        B100_CHECK_INPUT();
        BPCSBRTH.START_DT = BPB5248_AWA_5248.STARTD;
        BPCSBRTH.END_DT = BPB5248_AWA_5248.ENDD;
        BPCSBRTH.TYPE = "B";
        BPCSBRTH.BR = BP_BR;
        BPCSBRTH.BASE_TYP = BPB5248_AWA_5248.RATE_TYP;
        BPCSBRTH.CCY = BPB5248_AWA_5248.CCY;
        CEP.TRC(SCCGWA, BPB5248_AWA_5248.STARTD);
        CEP.TRC(SCCGWA, BPB5248_AWA_5248.ENDD);
        CEP.TRC(SCCGWA, BPB5248_AWA_5248.RATE_TYP);
        CEP.TRC(SCCGWA, BPB5248_AWA_5248.CCY);
        S000_CALL_BPZSBRRT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5248_AWA_5248.STARTD > BPB5248_AWA_5248.ENDD) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_S_DATE_GT_E_DATE;
            WS_FLD_NO = BPB5248_AWA_5248.STARTD_NO;
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
