package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3219 {
    String CPN_S_BL_IN = "BP-S-BL-IN-V     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLIV BPCSBLIV = new BPCSBLIV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3210_AWA_3210 BPB3210_AWA_3210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3219 return!");
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
        B020_TLR_BV_IN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!BPB3210_AWA_3210.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB3210_AWA_3210.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_BV_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLIV);
        BPCSBLIV.BV_CODE = BPB3210_AWA_3210.BV_DATA[1-1].BV_CODE;
        BPCSBLIV.CNM = BPB3210_AWA_3210.BV_DATA[1-1].CNM;
        BPCSBLIV.CCY = BPB3210_AWA_3210.BV_DATA[1-1].CCY;
        BPCSBLIV.VALUE = BPB3210_AWA_3210.BV_DATA[1-1].VALUE;
        BPCSBLIV.HEAD_NO = BPB3210_AWA_3210.BV_DATA[1-1].HEAD_NO;
        BPCSBLIV.BEG_NO = BPB3210_AWA_3210.BV_DATA[1-1].BEG_NO;
        BPCSBLIV.END_NO = BPB3210_AWA_3210.BV_DATA[1-1].END_NO;
        BPCSBLIV.NUM = BPB3210_AWA_3210.BV_DATA[1-1].NUM;
        BPCSBLIV.AMT = BPB3210_AWA_3210.BV_DATA[1-1].AMT;
        BPCSBLIV.MOV_DT = BPB3210_AWA_3210.MOV_DT;
        BPCSBLIV.CNF_NO = (int) BPB3210_AWA_3210.CNF_NO;
        S00_CALL_BPZSBLIV();
    }
    public void S00_CALL_BPZSBLIV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BL_IN, BPCSBLIV);
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
