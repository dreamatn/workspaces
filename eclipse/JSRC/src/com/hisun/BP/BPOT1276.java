package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1276 {
    String CPN_S_BPZFFCLO = "BP-F-S-FEE-COLLECT-O";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFCLO BPCSFCLO = new BPCSFCLO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1276_AWA_1276 BPB1276_AWA_1276;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1276 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1276_AWA_1276>");
        BPB1276_AWA_1276 = (BPB1276_AWA_1276) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFCLO);
        CEP.TRC(SCCGWA, BPB1276_AWA_1276);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CLT_FEE_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CLT_FEE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFCLO);
        BPCSFCLO.CMMT_NO = BPB1276_AWA_1276.CMMT_NO;
        BPCSFCLO.AC = BPB1276_AWA_1276.AC;
        BPCSFCLO.PROC_DT = BPB1276_AWA_1276.PROC_DT;
        if (BPB1276_AWA_1276.FUNC == '0') {
            BPCSFCLO.FUNC = '0';
        } else if (BPB1276_AWA_1276.FUNC == '1') {
            BPCSFCLO.FUNC = '1';
            C021_CLT_DATA_MOVE();
        } else {
        }
        CEP.TRC(SCCGWA, BPCSFCLO.CMMT_NO);
        CEP.TRC(SCCGWA, BPCSFCLO.AC);
        CEP.TRC(SCCGWA, BPCSFCLO.PROC_DT);
        CEP.TRC(SCCGWA, BPB1276_AWA_1276.FUNC);
        S00_CALL_BPZFFCLO();
    }
    public void C021_CLT_DATA_MOVE() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 5 
            && BPB1276_AWA_1276.CLT_LOOP[WS_I-1].CLT_BR1 != ' '; WS_I += 1) {
            BPCSFCLO.CLT_LOOP[WS_I-1].CLT_BR1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].CLT_BR1;
            BPCSFCLO.CLT_LOOP[WS_I-1].FEE_CD1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].FEE_CD1;
            BPCSFCLO.CLT_LOOP[WS_I-1].F1_CNT1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].F1_CNT1;
            BPCSFCLO.CLT_LOOP[WS_I-1].F1_AMT1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].F1_AMT1;
            BPCSFCLO.CLT_LOOP[WS_I-1].FEE_CD2 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].FEE_CD2;
            BPCSFCLO.CLT_LOOP[WS_I-1].F2_CNT1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].F2_CNT1;
            BPCSFCLO.CLT_LOOP[WS_I-1].F2_AMT1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].F2_AMT1;
            BPCSFCLO.CLT_LOOP[WS_I-1].FEE_CD3 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].FEE_CD3;
            BPCSFCLO.CLT_LOOP[WS_I-1].F3_CNT1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].F3_CNT1;
            BPCSFCLO.CLT_LOOP[WS_I-1].F3_AMT1 = BPB1276_AWA_1276.CLT_LOOP[WS_I-1].F3_AMT1;
        }
    }
    public void S00_CALL_BPZFFCLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFCLO, BPCSFCLO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
