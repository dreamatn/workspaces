package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2741 {
    String K_OUTPUT_FMT = "BP146";
    int K_NUM_1 = 1;
    char K_TRAN_REGST = '1';
    char K_TRAN_RECALL_STS = 'N';
    char K_TRAN_RECALL_FLG = '4';
    String CPN_R_ADW_FLSH = "BP-R-ADW-FLSH       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFLSH BPCRFLSH = new BPCRFLSH();
    BPCOFLSB BPCOFLSB = new BPCOFLSB();
    BPRFLSH BPRFLSH = new BPRFLSH();
    SCCGWA SCCGWA;
    BPB2741_AWA_2741 BPB2741_AWA_2741;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2741 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2741_AWA_2741>");
        BPB2741_AWA_2741 = (BPB2741_AWA_2741) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQUIRY_FLSH_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2741_AWA_2741.FUNC);
        CEP.TRC(SCCGWA, BPB2741_AWA_2741.TRAN_DT);
        CEP.TRC(SCCGWA, BPB2741_AWA_2741.JRNNO);
        WS_FUNC_FLG = BPB2741_AWA_2741.FUNC;
        if (WS_FUNC_FLG != 'I') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2741_AWA_2741.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQUIRY_FLSH_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLSH);
        BPRFLSH.KEY.DATE = BPB2741_AWA_2741.TRAN_DT;
        BPRFLSH.KEY.JRNNO = BPB2741_AWA_2741.JRNNO;
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'I';
        S000_CALL_BPZRFLSH();
        if (BPCRFLSH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCRFLSH.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSH.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRFLSH);
        R000_TRANS_OUTPUT_FLSH();
    }
    public void R000_TRANS_OUTPUT_FLSH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFLSB);
        BPCOFLSB.TRAN_DT = BPRFLSH.KEY.DATE;
        BPCOFLSB.JRNNO = BPRFLSH.KEY.JRNNO;
        BPCOFLSB.CAP_NO = BPRFLSH.CAP_NO;
        if (BPRFLSH.STS == K_TRAN_RECALL_STS) {
            BPCOFLSB.TRAN_TYP = K_TRAN_RECALL_FLG;
        } else {
            BPCOFLSB.TRAN_TYP = BPRFLSH.FLS_TYP;
        }
        BPCOFLSB.BR = BPRFLSH.BR;
        BPCOFLSB.TLR = BPRFLSH.UPD_TLR;
        BPCOFLSB.HLD_IDTP = BPRFLSH.HLD_IDTP;
        BPCOFLSB.HLD_IDNO = BPRFLSH.HLD_IDNO;
        BPCOFLSB.HLD_NM = BPRFLSH.HLD_NM;
        BPCOFLSB.HLD_PHN = BPRFLSH.HLD_PHN;
        BPCOFLSB.HLD_ADD = BPRFLSH.HLD_ADD;
        BPCOFLSB.HLD_EML = BPRFLSH.HLD_EML;
        BPCOFLSB.REC_NUM = BPRFLSH.REC_NUM;
        for (WS_CCY = K_NUM_1; WS_CCY <= BPRFLSH.REC_NUM; WS_CCY += K_NUM_1) {
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].FLS_CCY = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].FLS_VAL = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].FLS_VER = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].FLS_HDNO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_HDNO;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].BEG_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].BEG_NO;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].END_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].END_NO;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].FLS_NUM = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
            BPCOFLSB.FLS_DATA.CCYS[WS_CCY-1].FLS_SRC = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_SRC;
        }
        CEP.TRC(SCCGWA, BPCOFLSB);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOFLSB;
        SCCFMT.DATA_LEN = 3528;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRFLSH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_FLSH, BPCRFLSH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
