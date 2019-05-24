package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3240 {
    String CPN_S_BL_VTOB = "BP-S-BL-V-TO-B   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLVB BPCSBLVB = new BPCSBLVB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3210_AWA_3210 BPB3210_AWA_3210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3240 return!");
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
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLVB);
        BPCSBLVB.BV_CODE = BPB3210_AWA_3210.BV_DATA[1-1].BV_CODE;
        BPCSBLVB.CNM = BPB3210_AWA_3210.BV_DATA[1-1].CNM;
        BPCSBLVB.CCY = BPB3210_AWA_3210.BV_DATA[1-1].CCY;
        BPCSBLVB.VALUE = BPB3210_AWA_3210.BV_DATA[1-1].VALUE;
        BPCSBLVB.HEAD_NO = BPB3210_AWA_3210.BV_DATA[1-1].HEAD_NO;
        BPCSBLVB.BEG_NO = BPB3210_AWA_3210.BV_DATA[1-1].BEG_NO;
        BPCSBLVB.END_NO = BPB3210_AWA_3210.BV_DATA[1-1].END_NO;
        BPCSBLVB.NUM = BPB3210_AWA_3210.BV_DATA[1-1].NUM;
        BPCSBLVB.AMT = BPB3210_AWA_3210.BV_DATA[1-1].AMT;
        BPCSBLVB.RCV_TLR = BPB3210_AWA_3210.TLR;
        BPCSBLVB.PSW_TYP = BPB3210_AWA_3210.PSW_TYP;
        BPCSBLVB.TLRC_PSW = BPB3210_AWA_3210.TLRC_PSW;
        BPCSBLVB.TLRK_PSW = BPB3210_AWA_3210.TLRK_PSW;
        CEP.TRC(SCCGWA, BPCSBLVB);
        CEP.TRC(SCCGWA, BPCSBLVB.RCV_TLR);
        CEP.TRC(SCCGWA, BPCSBLVB.PSW_TYP);
        CEP.TRC(SCCGWA, BPCSBLVB.TLRK_PSW);
        S000_CALL_BPZSBLVB();
    }
    public void S000_CALL_BPZSBLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BL_VTOB, BPCSBLVB);
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
