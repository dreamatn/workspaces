package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3260 {
    String CPN_S_BL_BTOB = "BP-S-BL-B-TO-B   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLBB BPCSBLBB = new BPCSBLBB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3210_AWA_3210 BPB3210_AWA_3210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3260 return!");
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
        B020_TLR_BV_TRANS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TLR_BV_TRANS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLBB);
        BPCSBLBB.BV_CODE = BPB3210_AWA_3210.BV_DATA[1-1].BV_CODE;
        BPCSBLBB.CNM = BPB3210_AWA_3210.BV_DATA[1-1].CNM;
        BPCSBLBB.CCY = BPB3210_AWA_3210.BV_DATA[1-1].CCY;
        BPCSBLBB.VALUE = BPB3210_AWA_3210.BV_DATA[1-1].VALUE;
        BPCSBLBB.HEAD_NO = BPB3210_AWA_3210.BV_DATA[1-1].HEAD_NO;
        BPCSBLBB.BEG_NO = BPB3210_AWA_3210.BV_DATA[1-1].BEG_NO;
        BPCSBLBB.END_NO = BPB3210_AWA_3210.BV_DATA[1-1].END_NO;
        BPCSBLBB.NUM = BPB3210_AWA_3210.BV_DATA[1-1].NUM;
        BPCSBLBB.AMT = BPB3210_AWA_3210.BV_DATA[1-1].AMT;
        BPCSBLBB.RCV_TLR = BPB3210_AWA_3210.TLR;
        BPCSBLBB.PSW_TYP = BPB3210_AWA_3210.PSW_TYP;
        BPCSBLBB.TLRC_PSW = BPB3210_AWA_3210.TLRC_PSW;
        BPCSBLBB.TLRK_PSW = BPB3210_AWA_3210.TLRK_PSW;
        CEP.TRC(SCCGWA, BPCSBLBB);
        CEP.TRC(SCCGWA, BPCSBLBB.RCV_TLR);
        CEP.TRC(SCCGWA, BPCSBLBB.PSW_TYP);
        CEP.TRC(SCCGWA, BPCSBLBB.TLRK_PSW);
        S000_CALL_BPZSBLBB();
    }
    public void S000_CALL_BPZSBLBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BL_BTOB, BPCSBLBB);
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
