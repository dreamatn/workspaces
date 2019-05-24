package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4905 {
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String K_OUTPUT_FMT = "BP557";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    SCCGWA SCCGWA;
    BPB4900_AWA_4900 BPB4900_AWA_4900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4905 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4900_AWA_4900>");
        BPB4900_AWA_4900 = (BPB4900_AWA_4900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TELLER_INFO_QUERY();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4900_AWA_4900.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TELLER_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB4900_AWA_4900.TLR;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLRQ);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLRQ;
        SCCFMT.DATA_LEN = 835;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOTLRQ.TLR = BPCFTLRQ.INFO.TLR;
        BPCOTLRQ.TLR_BR = BPCFTLRQ.INFO.TLR_BR;
        BPCOTLRQ.STAF_NO = BPCFTLRQ.INFO.STAF_NO;
        BPCOTLRQ.TLR_CN_NM = BPCFTLRQ.INFO.TLR_CN_NM;
        BPCOTLRQ.TLR_EN_NM = BPCFTLRQ.INFO.TLR_EN_NM;
        BPCOTLRQ.EFF_DT = BPCFTLRQ.INFO.EFF_DT;
        BPCOTLRQ.EXP_DT = BPCFTLRQ.INFO.EXP_DT;
        BPCOTLRQ.UPD_DT = BPCFTLRQ.INFO.UPD_DT;
        BPCOTLRQ.UPD_TLR = BPCFTLRQ.INFO.UPD_TLR;
        BPCOTLRQ.TLR_TYP = BPCFTLRQ.INFO.TLR_TYP;
        BPCOTLRQ.TLR_LVL = BPCFTLRQ.INFO.TLR_LVL;
        BPCOTLRQ.ATH_LVL = BPCFTLRQ.INFO.ATH_LVL;
        BPCOTLRQ.SIGN_STS = BPCFTLRQ.INFO.SIGN_STS;
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_STS);
        BPCOTLRQ.TLR_STS = BPCFTLRQ.INFO.TLR_STS;
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STS);
        BPCOTLRQ.SIGN_DT = BPCFTLRQ.INFO.SIGN_DT;
        BPCOTLRQ.SIGN_TIMES = BPCFTLRQ.INFO.SIGN_TIMES;
        BPCOTLRQ.TRM_TYP = BPCFTLRQ.INFO.TRM_TYP;
        BPCOTLRQ.SIGN_TRM = BPCFTLRQ.INFO.SIGN_TRM;
        BPCOTLRQ.TELE = BPCFTLRQ.INFO.TELE;
        BPCOTLRQ.PST_ADDRESS = BPCFTLRQ.INFO.PST_ADDRESS;
        BPCOTLRQ.TLR_STSW = BPCFTLRQ.INFO.TLR_STSW;
        BPCOTLRQ.LAST_JRN = BPCFTLRQ.INFO.LAST_JRN;
        BPCOTLRQ.ACC_VCH_NO = BPCFTLRQ.INFO.ACC_VCH_NO;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
