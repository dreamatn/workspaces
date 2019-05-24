package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3901 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP164";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTBVO BPCOTBVO = new BPCOTBVO();
    SCCGWA SCCGWA;
    BPB3900_AWA_3900 BPB3900_AWA_3900;
    BPCSMTBV BPCSMTBV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3901 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3900_AWA_3900>");
        BPB3900_AWA_3900 = (BPB3900_AWA_3900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_TLR_NAME();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_TLR_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB3900_AWA_3900.TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        BPCOTBVO.TLR_CN_NM = BPCFTLRQ.INFO.TLR_CN_NM;
        BPCOTBVO.TLR_EN_NM = BPCFTLRQ.INFO.TLR_EN_NM;
        CEP.TRC(SCCGWA, BPCOTBVO.TLR_EN_NM);
        CEP.TRC(SCCGWA, BPCOTBVO.TLR_CN_NM);
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTBVO;
        SCCFMT.DATA_LEN = 222;
        CEP.TRC(SCCGWA, BPCOTBVO.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCOTBVO.TLR_EN_NM);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB3900_AWA_3900.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB3900_AWA_3900.TLR_NO;
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
