package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9159 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSSETT BPCSSETT = new BPCSSETT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9140_AWA_9140 BPB9140_AWA_9140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9159 return!");
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
        B020_SET_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_SET_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9140_AWA_9140.CTRT_NO);
        CEP.TRC(SCCGWA, BPB9140_AWA_9140.SETT_DT);
        IBS.init(SCCGWA, BPCSSETT);
        BPCSSETT.FUNC = 'R';
        BPCSSETT.KEY.CTRT_NO = BPB9140_AWA_9140.CTRT_NO;
        BPCSSETT.KEY.SETTLE_DATE = BPB9140_AWA_9140.SETT_DT;
        BPCSSETT.INF.REL_CTRT_NO = BPB9140_AWA_9140.RCTRT_NO;
        BPCSSETT.INF.CI_NO = BPB9140_AWA_9140.CI_NO;
        BPCSSETT.INF.ABBR_NAME = BPB9140_AWA_9140.AB_NAME;
        BPCSSETT.INF.PRD_TYPE = BPB9140_AWA_9140.PROD_TYP;
        BPCSSETT.INF.FEE_TYPE = BPB9140_AWA_9140.FEE_TYPE;
        BPCSSETT.INF.PAY_IND = BPB9140_AWA_9140.PAY_IND;
        BPCSSETT.INF.CHARGE_CCY = BPB9140_AWA_9140.CHG_CCY;
        BPCSSETT.INF.CHARGE_AMT = BPB9140_AWA_9140.CHG_AMT;
        BPCSSETT.INF.TICKET = BPB9140_AWA_9140.TICKET;
        BPCSSETT.INF.AMT_REAL = BPB9140_AWA_9140.AMT_REAL;
        BPCSSETT.INF.CCY_REAL = BPB9140_AWA_9140.CCY_REAL;
        BPCSSETT.INF.RATE = BPB9140_AWA_9140.RATE;
        BPCSSETT.INF.EXG_DT = BPB9140_AWA_9140.EXG_DT;
        BPCSSETT.INF.EXG_TM = BPB9140_AWA_9140.EXG_TM;
        BPCSSETT.INF.REMARK = BPB9140_AWA_9140.REMARK;
        S00_CALL_BPZSSETT();
    }
    public void S00_CALL_BPZSSETT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FEE-SETTLE", BPCSSETT);
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
