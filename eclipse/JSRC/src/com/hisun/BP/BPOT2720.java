package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2720 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP143";
    int K_NUM_1 = 1;
    int K_NUM_20 = 20;
    char K_PLBOX_SBRCH_TYPE = '2';
    char K_PLBOX_BRCH_TYPE = '1';
    char K_STSW_POOL_FLG = '1';
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB       ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_S_FALSE_MAINT = "BP-S-FALSE-MAINT    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY = 0;
    int WS_REC_BR = 0;
    String WS_PAY_PBNO = " ";
    String WS_REC_PBNO = " ";
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOFLSM BPCOFLSM = new BPCOFLSM();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    SCCGWA SCCGWA;
    BPB2720_AWA_2720 BPB2720_AWA_2720;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2720 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2720_AWA_2720>");
        BPB2720_AWA_2720 = (BPB2720_AWA_2720) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_EXTRACT_FALSE_NOTE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2720_AWA_2720.FUNC);
        WS_FUNC_FLG = BPB2720_AWA_2720.FUNC;
        if (WS_FUNC_FLG != 'E') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2720_AWA_2720.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (!BPB2720_AWA_2720.REC_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_CRNT_TLR;
            WS_FLD_NO = BPB2720_AWA_2720.REC_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        WS_REC_BR = BPCFTLRQ.INFO.TLR_BR;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_POOL_FLG+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            S000_ERR_MSG_PROC();
        }
        WS_PAY_PBNO = " ";
        WS_REC_PBNO = " ";
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.PLBOX_TP = ALL.charAt(0);
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.FUNC = 'T';
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.FUNC = 'N';
        S000_CALL_BPZRTLVB();
        while (BPCRTLVB.RETURN_INFO != 'N') {
            if (BPRTLVB.PLBOX_TP == K_PLBOX_BRCH_TYPE 
                || BPRTLVB.PLBOX_TP == K_PLBOX_SBRCH_TYPE) {
                WS_PAY_PBNO = BPRTLVB.KEY.PLBOX_NO;
            }
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.FUNC = 'N';
            S000_CALL_BPZRTLVB();
        }
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.FUNC = 'E';
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (WS_PAY_PBNO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPB2720_AWA_2720.FLS_RECN > K_NUM_20) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_REC_INVALID;
            WS_FLD_NO = BPB2720_AWA_2720.FLS_RECN_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY = K_NUM_1; WS_CCY <= BPB2720_AWA_2720.FLS_RECN; WS_CCY += K_NUM_1) {
            if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_CCY_IS_SPACE;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_CCY_NO;
                S000_ERR_MSG_PROC();
            } else if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_VAL == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_VAL_IS_SPACE;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_VAL_NO;
                S000_ERR_MSG_PROC();
            } else if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_VER.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_VER_IS_SPACE;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_VER_NO;
                S000_ERR_MSG_PROC();
            } else if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_NUM == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_NUM_IS_ZERO;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_NUM_NO;
                S000_ERR_MSG_PROC();
            } else if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_HDNO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_HDNO_IS_SPACE;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_HDNO_NO;
                S000_ERR_MSG_PROC();
            } else if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_BGNO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_BGNO_IS_SPACE;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_BGNO_NO;
                S000_ERR_MSG_PROC();
            } else if (BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_EDNO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLS_EDNO_IS_SPACE;
                WS_FLD_NO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_EDNO_NO;
                S000_ERR_MSG_PROC();
            } else {
            }
        }
    }
    public void B020_EXTRACT_FALSE_NOTE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFLSM);
        BPCOFLSM.TX_TYP = '3';
        BPCOFLSM.STATUS = 'Y';
        BPCOFLSM.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARM();
        S000_CALL_BPZSFLSM();
    }
    public void R000_TRANS_DATA_PARM() throws IOException,SQLException,Exception {
        BPCOFLSM.FUNC = BPB2720_AWA_2720.FUNC;
        BPCOFLSM.IN_TLR = BPB2720_AWA_2720.REC_TLR;
        BPCOFLSM.REC_TLR = BPB2720_AWA_2720.REC_TLR;
        BPCOFLSM.TRAN_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFLSM.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOFLSM.REC_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOFLSM.PAY_PBNO = WS_PAY_PBNO;
        BPCOFLSM.REC_PBNO = WS_REC_PBNO;
        BPCOFLSM.REC_NUM = BPB2720_AWA_2720.FLS_RECN;
        for (WS_CCY = K_NUM_1; WS_CCY <= BPB2720_AWA_2720.FLS_RECN; WS_CCY += K_NUM_1) {
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_CCY = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_CCY;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VAL = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_VAL;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VER = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_VER;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_HDNO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_HDNO;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_BGNO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_BGNO;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_EDNO = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_EDNO;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_NUM = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_NUM;
            BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_SRC = BPB2720_AWA_2720.CCYS[WS_CCY-1].FLS_SRC;
        }
    }
    public void S000_CALL_BPZSFLSM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_FALSE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCOFLSM;
        SCCCALL.ERR_FLDNO = BPB2720_AWA_2720.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCOFLSM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOFLSM.RC);
            WS_FLD_NO = BPB2720_AWA_2720.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
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
