package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4942 {
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCO4900 BPCO4900 = new BPCO4900();
    SCCGWA SCCGWA;
    BPB4900_AWA_4900 BPB4900_AWA_4900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4942 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4900_AWA_4900>");
        BPB4900_AWA_4900 = (BPB4900_AWA_4900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPB4900_AWA_4900.FUNC != 'A') {
            B010_CHECK_INPUT();
            B020_TELLER_INFO_QUERY();
            B030_OUTPUT_DATA();
        }
        B040_SET_SUB_TRN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4900_AWA_4900.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4900_AWA_4900.FUNC == 'M' 
            || BPB4900_AWA_4900.FUNC == 'D') {
            if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB4900_AWA_4900.TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MODIFY_ONESEL;
                WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TELLER_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        CEP.TRC(SCCGWA, BPB4900_AWA_4900.TLR);
        BPCFTLRQ.INFO.TLR = BPB4900_AWA_4900.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPB4900_AWA_4900.FUNC == 'M' 
            || BPB4900_AWA_4900.FUNC == 'D') {
            if (BPCFTLRQ.INFO.SIGN_STS == 'O') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_ALREADY_SINGON;
                WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO4900);
        R010_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO4900;
        SCCFMT.DATA_LEN = 440;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "=============");
        CEP.TRC(SCCGWA, BPCO4900.ID_TYP);
        CEP.TRC(SCCGWA, BPCO4900.ID_NO);
        CEP.TRC(SCCGWA, BPCO4900.CI_NO);
        CEP.TRC(SCCGWA, BPCO4900.TLR);
        CEP.TRC(SCCGWA, BPCO4900.TLR_BR);
        CEP.TRC(SCCGWA, BPCO4900.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCO4900.EFF_DT);
        CEP.TRC(SCCGWA, BPCO4900.EXP_DT);
        CEP.TRC(SCCGWA, BPCO4900.TLR_LVL);
        CEP.TRC(SCCGWA, BPCO4900.REG_TYP);
        CEP.TRC(SCCGWA, BPCO4900.CRD_NO);
        CEP.TRC(SCCGWA, BPCO4900.NEW_BR);
        CEP.TRC(SCCGWA, BPCO4900.TLR_STSW);
        CEP.TRC(SCCGWA, BPCO4900.TELE);
        CEP.TRC(SCCGWA, BPCO4900.PST_ADDRESS);
    }
    public void B040_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPB4900_AWA_4900.FUNC == 'I') {
            SCCSUBS.TR_CODE = 4903;
        } else if (BPB4900_AWA_4900.FUNC == 'A') {
            SCCSUBS.TR_CODE = 4900;
        } else if (BPB4900_AWA_4900.FUNC == 'M') {
            SCCSUBS.TR_CODE = 4901;
        } else if (BPB4900_AWA_4900.FUNC == 'D') {
            SCCSUBS.TR_CODE = 4902;
        } else {
            SCCSUBS.TR_CODE = 4903;
        }
        S000_SET_SUBS_TRN();
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
        BPCOTLRQ.TX_LVL = BPCFTLRQ.INFO.TX_LVL;
        BPCOTLRQ.ATH_LVL = BPCFTLRQ.INFO.ATH_LVL;
        BPCOTLRQ.SIGN_STS = BPCFTLRQ.INFO.SIGN_STS;
        BPCOTLRQ.TLR_STS = BPCFTLRQ.INFO.TLR_STS;
        BPCOTLRQ.SIGN_DT = BPCFTLRQ.INFO.SIGN_DT;
        BPCOTLRQ.SIGN_TIMES = BPCFTLRQ.INFO.SIGN_TIMES;
        BPCOTLRQ.TRM_TYP = BPCFTLRQ.INFO.TRM_TYP;
        BPCOTLRQ.SIGN_TRM = BPCFTLRQ.INFO.SIGN_TRM;
        BPCOTLRQ.TELE = BPCFTLRQ.INFO.TELE;
        BPCOTLRQ.PST_ADDRESS = BPCFTLRQ.INFO.PST_ADDRESS;
        BPCOTLRQ.TLR_STSW = BPCFTLRQ.INFO.TLR_STSW;
        CEP.TRC(SCCGWA, "==================");
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STSW);
        BPCOTLRQ.LAST_JRN = BPCFTLRQ.INFO.LAST_JRN;
        BPCOTLRQ.ACC_VCH_NO = BPCFTLRQ.INFO.ACC_VCH_NO;
        BPCOTLRQ.ACC_SEN_CUS = BPCFTLRQ.INFO.ACC_SEN_CUS;
        BPCOTLRQ.ACC_SEN_GL = BPCFTLRQ.INFO.ACC_SEN_GL;
        BPCOTLRQ.SUPER_FLG = BPCFTLRQ.INFO.SUPER_FLG;
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STS);
    }
    public void R010_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCO4900.ID_TYP = BPCFTLRQ.INFO.ID_TYP;
        BPCO4900.ID_NO = BPCFTLRQ.INFO.ID_NO;
        BPCO4900.CI_NO = BPCFTLRQ.INFO.CI_NO;
        BPCO4900.TLR = BPCFTLRQ.INFO.TLR;
        BPCO4900.TLR_BR = BPCFTLRQ.INFO.TLR_BR;
        BPCO4900.TLR_CN_NM = BPCFTLRQ.INFO.TLR_CN_NM;
        BPCO4900.EFF_DT = BPCFTLRQ.INFO.EFF_DT;
        BPCO4900.EXP_DT = BPCFTLRQ.INFO.EXP_DT;
        BPCO4900.TLR_LVL = BPCFTLRQ.INFO.TLR_LVL;
        BPCO4900.REG_TYP = BPCFTLRQ.INFO.REG_TYP;
        BPCO4900.CRD_NO = BPCFTLRQ.INFO.TLR_CRD_NO;
        BPCO4900.NEW_BR = BPCFTLRQ.INFO.NEW_BR;
        BPCO4900.TLR_STSW = BPCFTLRQ.INFO.TLR_STSW;
        BPCO4900.TELE = BPCFTLRQ.INFO.TELE;
        BPCO4900.PST_ADDRESS = BPCFTLRQ.INFO.PST_ADDRESS;
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
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_SBUS_TRN, SCCSUBS);
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
