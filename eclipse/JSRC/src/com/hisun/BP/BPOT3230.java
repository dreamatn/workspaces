package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3230 {
    String CPN_S_MOV_IN = "BP-S-BL-MOV-IN   ";
    int K_CNT = 4;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BV_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLMI BPCSBLMI = new BPCSBLMI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3210_AWA_3210 BPB3210_AWA_3210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3210_AWA_3210>");
        BPB3210_AWA_3210 = (BPB3210_AWA_3210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TLR_BV_OUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_CNT 
            && BPB3210_AWA_3210.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            WS_BV_CNT = WS_I;
        }
        CEP.TRC(SCCGWA, WS_BV_CNT);
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLMI);
        BPCSBLMI.MOV_DT = BPB3210_AWA_3210.MOV_DT;
        BPCSBLMI.CNF_NO = BPB3210_AWA_3210.CNF_NO;
        BPCSBLMI.OUT_BR = BPB3210_AWA_3210.BR;
        BPCSBLMI.OUT_TLR = BPB3210_AWA_3210.TLR;
        BPCSBLMI.CNT = WS_BV_CNT;
        for (WS_I = 1; WS_I <= WS_BV_CNT; WS_I += 1) {
            BPCSBLMI.BV_DATA[WS_I-1].BV_CODE = BPB3210_AWA_3210.BV_DATA[WS_I-1].BV_CODE;
            BPCSBLMI.BV_DATA[WS_I-1].CNM = BPB3210_AWA_3210.BV_DATA[WS_I-1].CNM;
            BPCSBLMI.BV_DATA[WS_I-1].CCY = BPB3210_AWA_3210.BV_DATA[WS_I-1].CCY;
            BPCSBLMI.BV_DATA[WS_I-1].VALUE = BPB3210_AWA_3210.BV_DATA[WS_I-1].VALUE;
            BPCSBLMI.BV_DATA[WS_I-1].HEAD_NO = BPB3210_AWA_3210.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBLMI.BV_DATA[WS_I-1].BEG_NO = BPB3210_AWA_3210.BV_DATA[WS_I-1].BEG_NO;
            BPCSBLMI.BV_DATA[WS_I-1].END_NO = BPB3210_AWA_3210.BV_DATA[WS_I-1].END_NO;
            BPCSBLMI.BV_DATA[WS_I-1].NUM = BPB3210_AWA_3210.BV_DATA[WS_I-1].NUM;
            BPCSBLMI.BV_DATA[WS_I-1].AMT = BPB3210_AWA_3210.BV_DATA[WS_I-1].AMT;
        }
        S000_CALL_BPZSBLMI();
    }
    public void S000_CALL_BPZSBLMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MOV_IN, BPCSBLMI);
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
