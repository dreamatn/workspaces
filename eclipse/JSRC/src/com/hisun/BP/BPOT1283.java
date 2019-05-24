package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1283 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFABF BPCSFABF = new BPCSFABF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    SCCAWAC SCCAWAC;
    BPB1283_AWA_1283 BPB1283_AWA_1283;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1283 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1283_AWA_1283>");
        BPB1283_AWA_1283 = (BPB1283_AWA_1283) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUIRE_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUIRE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFABF);
        BPCSFABF.KEY.AC = BPB1283_AWA_1283.AC;
        BPCSFABF.KEY.CCY = BPB1283_AWA_1283.CCY;
        BPCSFABF.KEY.CCY_TYPE = BPB1283_AWA_1283.CCY_TYPE;
        BPCSFABF.KEY.EFF_DATE = BPB1283_AWA_1283.EFF_DATE;
        CEP.TRC(SCCGWA, BPB1283_AWA_1283.AC);
        CEP.TRC(SCCGWA, BPB1283_AWA_1283.CCY);
        CEP.TRC(SCCGWA, BPB1283_AWA_1283.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB1283_AWA_1283.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.AC);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.CCY);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.EFF_DATE);
        BPCSFABF.FUNC = 'I';
        S000_CALL_BPZSFABF();
    }
    public void S000_CALL_BPZSFABF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-BRW-BPTFABF", BPCSFABF);
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
