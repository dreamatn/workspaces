package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3229 {
    String CPN_S_MOV_OUT = "BP-S-BL-MOV-OUT  ";
    int K_CNT = 4;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BV_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLMO BPCSBLMO = new BPCSBLMO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3210_AWA_3210 BPB3210_AWA_3210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3229 return!");
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
        if (!BPB3210_AWA_3210.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB3210_AWA_3210.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLMO);
        BPCSBLMO.RCV_BR = BPB3210_AWA_3210.BR;
        BPCSBLMO.RCV_TLR = BPB3210_AWA_3210.TLR;
        BPCSBLMO.CNF_NO = BPB3210_AWA_3210.CNF_NO;
        BPCSBLMO.MOV_DT = BPB3210_AWA_3210.MOV_DT;
        BPCSBLMO.CNT = WS_BV_CNT;
        for (WS_I = 1; WS_I <= WS_BV_CNT; WS_I += 1) {
            BPCSBLMO.BV_DATA[WS_I-1].BV_CODE = BPB3210_AWA_3210.BV_DATA[WS_I-1].BV_CODE;
            BPCSBLMO.BV_DATA[WS_I-1].CNM = BPB3210_AWA_3210.BV_DATA[WS_I-1].CNM;
            BPCSBLMO.BV_DATA[WS_I-1].CCY = BPB3210_AWA_3210.BV_DATA[WS_I-1].CCY;
            BPCSBLMO.BV_DATA[WS_I-1].VALUE = BPB3210_AWA_3210.BV_DATA[WS_I-1].VALUE;
            BPCSBLMO.BV_DATA[WS_I-1].HEAD_NO = BPB3210_AWA_3210.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBLMO.BV_DATA[WS_I-1].BEG_NO = BPB3210_AWA_3210.BV_DATA[WS_I-1].BEG_NO;
            BPCSBLMO.BV_DATA[WS_I-1].END_NO = BPB3210_AWA_3210.BV_DATA[WS_I-1].END_NO;
            BPCSBLMO.BV_DATA[WS_I-1].NUM = BPB3210_AWA_3210.BV_DATA[WS_I-1].NUM;
            BPCSBLMO.BV_DATA[WS_I-1].AMT = BPB3210_AWA_3210.BV_DATA[WS_I-1].AMT;
        }
        BPB3210_AWA_3210.MOV_DT = (int) BPCSBLMO.CNF_NO;
        BPB3210_AWA_3210.CNF_NO = BPCSBLMO.MOV_DT;
        S000_CALL_BPZSBLMO();
    }
    public void S000_CALL_BPZSBLMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MOV_OUT, BPCSBLMO);
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
