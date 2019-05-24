package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4943 {
    int JIBS_tmp_int;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRB BPCOTLRB = new BPCOTLRB();
    SCCGWA SCCGWA;
    BPB4942_AWA_4942 BPB4942_AWA_4942;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4943 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4942_AWA_4942>");
        BPB4942_AWA_4942 = (BPB4942_AWA_4942) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TELLER_INFO_QUERY();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4942_AWA_4942.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4942_AWA_4942.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TELLER_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB4942_AWA_4942.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.SIGN_STS == 'O') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ALREADY_SINGON;
            WS_FLD_NO = BPB4942_AWA_4942.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLRB);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLRB;
        SCCFMT.DATA_LEN = 545;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOTLRB.TLR = BPCFTLRQ.INFO.TLR;
        BPCOTLRB.TLR_BR = BPCFTLRQ.INFO.TLR_BR;
        BPCOTLRB.TLR_TKS = BPCFTLRQ.INFO.TLR_TKS;
        BPCOTLRB.STAF_NO = BPCFTLRQ.INFO.STAF_NO;
        BPCOTLRB.TLR_CN_NM = BPCFTLRQ.INFO.TLR_CN_NM;
        BPCOTLRB.TLR_EN_NM = BPCFTLRQ.INFO.TLR_EN_NM;
        BPCOTLRB.EFF_DT = BPCFTLRQ.INFO.EFF_DT;
        BPCOTLRB.EXP_DT = BPCFTLRQ.INFO.EXP_DT;
        BPCOTLRB.UPD_DT = BPCFTLRQ.INFO.UPD_DT;
        BPCOTLRB.UPD_TLR = BPCFTLRQ.INFO.UPD_TLR;
        BPCOTLRB.TLR_TYP = BPCFTLRQ.INFO.TLR_TYP;
        BPCOTLRB.TLR_LVL = BPCFTLRQ.INFO.TLR_LVL;
        BPCOTLRB.TX_LVL = BPCFTLRQ.INFO.TX_LVL;
        BPCOTLRB.ATH_LVL = BPCFTLRQ.INFO.ATH_LVL;
        BPCOTLRB.ATH_RGN = BPCFTLRQ.INFO.ATH_RGN;
        BPCOTLRB.TX_MODE = BPCFTLRQ.INFO.TX_MODE;
        BPCOTLRB.SIGN_STS = BPCFTLRQ.INFO.SIGN_STS;
        BPCOTLRB.TLR_STS = BPCFTLRQ.INFO.TLR_STS;
        BPCOTLRB.SIGN_DT = BPCFTLRQ.INFO.SIGN_DT;
        BPCOTLRB.CRO_BR_SIGN = BPCFTLRQ.INFO.CRO_BR_SIGN;
        BPCOTLRB.SIGN_TIMES = BPCFTLRQ.INFO.SIGN_TIMES;
        BPCOTLRB.PSW_RETRY = BPCFTLRQ.INFO.PSW_RETRY;
        BPCOTLRB.TRM_TYP = BPCFTLRQ.INFO.TRM_TYP;
        BPCOTLRB.SIGN_TRM = BPCFTLRQ.INFO.SIGN_TRM;
        BPCOTLRB.PRT_IP = BPCFTLRQ.INFO.PRT_IP;
        BPCOTLRB.TLR_STSW = BPCFTLRQ.INFO.TLR_STSW;
        if (BPCOTLRB.TLR_STSW == null) BPCOTLRB.TLR_STSW = "";
        JIBS_tmp_int = BPCOTLRB.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCOTLRB.TLR_STSW += " ";
        IBS.CPY2CLS(SCCGWA, BPCOTLRB.TLR_STSW.substring(0, 12), BPCOTLRB.TLR_SUB_STW);
        BPCOTLRB.LAST_JRN = BPCFTLRQ.INFO.LAST_JRN;
        BPCOTLRB.ACC_VCH_NO = BPCFTLRQ.INFO.ACC_VCH_NO;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB4942_AWA_4942.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB4942_AWA_4942.TLR_NO;
            S000_ERR_MSG_PROC();
        }
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
