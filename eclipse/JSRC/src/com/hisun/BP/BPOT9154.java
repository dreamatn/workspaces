package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9154 {
    String CPN_S_BPZFCPHM = "BP-S-MGM-CAL-PARM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCPHM BPCFCPHM = new BPCFCPHM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9152_AWA_9152 BPB9152_AWA_9152;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9154 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9152_AWA_9152>");
        BPB9152_AWA_9152 = (BPB9152_AWA_9152) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCPHM);
        BPCFCPHM.CTRT_NO = BPB9152_AWA_9152.CTRT_NO;
        BPCFCPHM.VALUE_DATE = BPB9152_AWA_9152.VAL_DT;
        BPCFCPHM.PRICE_MTH = BPB9152_AWA_9152.PRC_MTH;
        BPCFCPHM.INT_BAS = BPB9152_AWA_9152.INT_BAS;
        BPCFCPHM.MULTI = BPB9152_AWA_9152.MULTI;
        BPCFCPHM.AGGR_TYPE = BPB9152_AWA_9152.AGGR_TYP;
        BPCFCPHM.REF_CCY = BPB9152_AWA_9152.REF_CCY;
        BPCFCPHM.REF_MTH = BPB9152_AWA_9152.REF_MTH;
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            BPCFCPHM.RATE_TBL[WS_IDX-1].UP_AMT = BPB9152_AWA_9152.RATE_TBL[WS_IDX-1].UP_AMT;
            BPCFCPHM.RATE_TBL[WS_IDX-1].UP_PCT = BPB9152_AWA_9152.RATE_TBL[WS_IDX-1].UP_PCT;
            BPCFCPHM.RATE_TBL[WS_IDX-1].FEE_AMT = BPB9152_AWA_9152.RATE_TBL[WS_IDX-1].FEE_AMT;
            BPCFCPHM.RATE_TBL[WS_IDX-1].FEE_RATE = BPB9152_AWA_9152.RATE_TBL[WS_IDX-1].FEE_RATE;
        }
        BPCFCPHM.REMARK = BPB9152_AWA_9152.REMARK;
        CEP.TRC(SCCGWA, "BPCFCPHM");
        CEP.TRC(SCCGWA, BPCFCPHM);
        BPCFCPHM.FUNC = 'D';
        BPCFCPHM.FUNCI = ' ';
        S00_CALL_BPZFCPHM();
    }
    public void S00_CALL_BPZFCPHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFCPHM, BPCFCPHM);
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
