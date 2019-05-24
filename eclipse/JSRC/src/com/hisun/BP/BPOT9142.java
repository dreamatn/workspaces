package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9142 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSSCHD BPCSSCHD = new BPCSSCHD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9140_AWA_9140 BPB9140_AWA_9140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9142 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9140_AWA_9140>");
        BPB9140_AWA_9140 = (BPB9140_AWA_9140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9140_AWA_9140.SETT_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB9140_AWA_9140.SETT_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSSCHD);
        BPCSSCHD.KEY.CTRT_NO = BPB9140_AWA_9140.CTRT_NO;
        BPCSSCHD.KEY.SETTLE_DATE = BPB9140_AWA_9140.SETT_DT;
        BPCSSCHD.INF.REL_CTRT_NO = BPB9140_AWA_9140.RCTRT_NO;
        BPCSSCHD.INF.CI_NO = BPB9140_AWA_9140.CI_NO;
        BPCSSCHD.INF.ABBR_NAME = BPB9140_AWA_9140.AB_NAME;
        BPCSSCHD.INF.PRD_TYPE = BPB9140_AWA_9140.PROD_TYP;
        BPCSSCHD.INF.FEE_TYPE = BPB9140_AWA_9140.FEE_TYPE;
        BPCSSCHD.INF.PAY_IND = BPB9140_AWA_9140.PAY_IND;
        BPCSSCHD.INF.CHARGE_CCY = BPB9140_AWA_9140.CHG_CCY;
        BPCSSCHD.INF.CHARGE_AMT = BPB9140_AWA_9140.CHG_AMT;
        BPCSSCHD.INF.ADJUST_AMT = BPB9140_AWA_9140.ADJ_AMT;
        BPCSSCHD.INF.REMARK = BPB9140_AWA_9140.REMARK;
        BPCSSCHD.INF.CHG_AMT_REAL = BPB9140_AWA_9140.AMT_REAL;
        BPCSSCHD.INF.CHG_CCY_REAL = BPB9140_AWA_9140.CCY_REAL;
        BPCSSCHD.INF.RATE = BPB9140_AWA_9140.RATE;
        BPCSSCHD.FUNC = 'A';
        S00_CALL_BPZSSCHD();
    }
    public void S00_CALL_BPZSSCHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-FEESCHD", BPCSSCHD);
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
