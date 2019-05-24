package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9130 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFSCH BPCSFSCH = new BPCSFSCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9130_AWA_9130 BPB9130_AWA_9130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9130_AWA_9130>");
        BPB9130_AWA_9130 = (BPB9130_AWA_9130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.CTRT_NO);
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.RCTRT_NO);
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.FEE_TYPE);
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.CI_NO);
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.STA_DATE);
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.MAT_DATE);
        CEP.TRC(SCCGWA, BPB9130_AWA_9130.FEE_STS);
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFSCH);
        BPCSFSCH.FUNC = '1';
        BPCSFSCH.KEY.CTRT_NO = BPB9130_AWA_9130.CTRT_NO;
        BPCSFSCH.INF.REL_CTRT_NO = BPB9130_AWA_9130.RCTRT_NO;
        BPCSFSCH.INF.FEE_TYPE = BPB9130_AWA_9130.FEE_TYPE;
        BPCSFSCH.INF.CI_NO = BPB9130_AWA_9130.CI_NO;
        BPCSFSCH.INF.START_DATE = BPB9130_AWA_9130.STA_DATE;
        BPCSFSCH.INF.MATURITY_DATE = BPB9130_AWA_9130.MAT_DATE;
        BPCSFSCH.INF.FEE_STATUS = BPB9130_AWA_9130.FEE_STS;
        S00_CALL_BPZSFSCH();
    }
    public void S00_CALL_BPZSFSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-FEESCH", BPCSFSCH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
