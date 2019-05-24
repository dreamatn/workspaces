package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2004 {
    String K_OUTPUT_FMT = "BP109";
    String CPN_S_CASHBL_MAINTAIN = "BP-S-CASHBL-MAINTAIN";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_BR3 = 0;
    int WS_BR4 = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOLIBM BPCOLIBM = new BPCOLIBM();
    BPCOLVBM BPCOLVBM = new BPCOLVBM();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPB2000_AWA_2000 BPB2000_AWA_2000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2004 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2000_AWA_2000>");
        BPB2000_AWA_2000 = (BPB2000_AWA_2000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.FUNC);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.BR);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.TLR);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.PLBOX_NO);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.BIND_TYP);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.PLBOX_TP);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.INSR_CCY);
        CEP.TRC(SCCGWA, BPB2000_AWA_2000.UP_PBNO);
        B010_CHECK_INPUT();
        B020_QUERY_BOXL_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR);
        }
        WS_BR = BPCFTLRQ.INFO.TLR_BR;
        WS_BR3 = BPB2000_AWA_2000.BR;
        WS_BR4 = BPCFTLRQ.INFO.TLR_BR;
    }
    public void B020_QUERY_BOXL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBM);
        BPCOLIBM.FUNC = 'I';
        BPCOLIBM.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSLIBM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOLIBM.FUNC = BPB2000_AWA_2000.FUNC;
        BPCOLIBM.BR = BPB2000_AWA_2000.BR;
        BPCOLIBM.TLR = BPB2000_AWA_2000.TLR;
        BPCOLIBM.BIND_TYP = BPB2000_AWA_2000.BIND_TYP;
        BPCOLIBM.PLBOX_NO = BPB2000_AWA_2000.PLBOX_NO;
        BPCOLIBM.PLBOX_TP = BPB2000_AWA_2000.PLBOX_TP;
        BPCOLIBM.INSR_CCY = BPB2000_AWA_2000.INSR_CCY;
        BPCOLIBM.INSR_AMT = BPB2000_AWA_2000.INSR_AMT;
        BPCOLIBM.UP_PBNO = BPB2000_AWA_2000.UP_PBNO;
        BPCOLIBM.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOLIBM.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCOLIBM.CCYS[1-1].CCY);
    }
    public void S000_CALL_BPZSLIBM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_CASHBL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOLIBM;
        SCCCALL.ERR_FLDNO = BPB2000_AWA_2000.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCOLIBM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOLIBM.RC);
            WS_FLD_NO = BPB2000_AWA_2000.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB2000_AWA_2000.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB2000_AWA_2000.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB2000_AWA_2000.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCFTLRQ.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB2000_AWA_2000.TLR_NO;
            S000_ERR_MSG_PROC();
        }
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
