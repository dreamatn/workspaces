package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2601 {
    String K_OUTPUT_FMT = "BP266";
    String CPN_S_BOOKING_MAINTAIN = "BP-S-BOKING-MAINTAIN";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_BR3 = 0;
    int WS_BR4 = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOPPDM BPCOPPDM = new BPCOPPDM();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPB2600_AWA_2600 BPB2600_AWA_2600;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2601 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2600_AWA_2600>");
        BPB2600_AWA_2600 = (BPB2600_AWA_2600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2600_AWA_2600);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_NO);
        B010_CHECK_INPUT();
        B020_ADD_BOOKING_CASH();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB2600_AWA_2600.FUNC;
        if (WS_FUNC_FLG != 'Q') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2600_AWA_2600.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_BOOKING_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPPDM);
        BPCOPPDM.FUNC = 'I';
        BPCOPPDM.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSPPDM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOPPDM.APT_NO = BPB2600_AWA_2600.APT_NO;
        BPCOPPDM.APT_CCY = BPB2600_AWA_2600.APT_CCY;
    }
    public void S000_CALL_BPZSPPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BOOKING_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOPPDM;
        SCCCALL.ERR_FLDNO = BPB2600_AWA_2600.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCOPPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOPPDM.RC);
            WS_FLD_NO = BPB2600_AWA_2600.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
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
