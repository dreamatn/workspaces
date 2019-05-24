package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4916 {
    String CPN_TLR_STS_MAINTAIN = "BP-S-TLR-STS-M      ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_OPT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLSM BPCSTLSM = new BPCSTLSM();
    SCCGWA SCCGWA;
    BPB4915_AWA_4915 BPB4915_AWA_4915;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4916 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4915_AWA_4915>");
        BPB4915_AWA_4915 = (BPB4915_AWA_4915) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TLR_STATUS_MAINTAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.TLR);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.OUT_BR);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.TLR_NM);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.IN_BR);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.TLR_LVL);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.BEGIN_DT);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.END_DT);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.BEGIN_TM);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.END_TM);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.CHK_FLG);
        if (BPB4915_AWA_4915.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_MUST_INPUT;
            WS_FLD_NO = BPB4915_AWA_4915.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4915_AWA_4915.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
            WS_FLD_NO = BPB4915_AWA_4915.BEGIN_DT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4915_AWA_4915.END_DT < BPB4915_AWA_4915.BEGIN_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
            WS_FLD_NO = BPB4915_AWA_4915.END_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_STATUS_MAINTAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSM);
        R000_TRANS_DATA_PARAMETER_CN();
        S000_CALL_BPZSTLSM();
    }
    public void R000_TRANS_DATA_PARAMETER_CN() throws IOException,SQLException,Exception {
        BPCSTLSM.TLR = BPB4915_AWA_4915.TLR;
        BPCSTLSM.OUT_BR = BPB4915_AWA_4915.OUT_BR;
        BPCSTLSM.TLR_NM = BPB4915_AWA_4915.TLR_NM;
        BPCSTLSM.IN_BR = BPB4915_AWA_4915.IN_BR;
        BPCSTLSM.TLR_LVL = BPB4915_AWA_4915.TLR_LVL;
        BPCSTLSM.BEGIN_DT = BPB4915_AWA_4915.BEGIN_DT;
        BPCSTLSM.END_DT = BPB4915_AWA_4915.END_DT;
        BPCSTLSM.BEGIN_TIME = BPB4915_AWA_4915.BEGIN_TM;
        BPCSTLSM.END_TIME = BPB4915_AWA_4915.END_TM;
        BPCSTLSM.IN_BR_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSTLSM.CHK_FLG = BPB4915_AWA_4915.CHK_FLG;
        BPCSTLSM.OPT = 'T';
        BPCSTLSM.OPT_T = 'P';
        BPCSTLSM.TMP_FUND = 'A';
    }
    public void S000_CALL_BPZSTLSM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_STS_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSTLSM;
        SCCCALL.ERR_FLDNO = BPB4915_AWA_4915.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
