package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4993 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_AMTL_MAINTAIN = "BP-S-AMTL-MAINTAIN  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
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
    BPCSAMTL BPCSAMTL = new BPCSAMTL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB4900_AWA_4900 BPB4900_AWA_4900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4993 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4900_AWA_4900>");
        BPB4900_AWA_4900 = (BPB4900_AWA_4900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSAMTL);
        IBS.init(SCCGWA, BPCPQORG);
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
        BPCPQORG.BR = BPB4900_AWA_4900.TLR_BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TELLER_INFO_QUERY() throws IOException,SQLException,Exception {
        BPCSAMTL.BR = BPB4900_AWA_4900.TLR_BR;
        BPCSAMTL.MACH_NO = BPB4900_AWA_4900.TLR;
        BPCSAMTL.FUNC = 'I';
        S000_CALL_BPZSAMTL();
        IBS.init(SCCGWA, BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCSAMTL.TLR_NO);
        BPCFTLRQ.INFO.TLR = BPCSAMTL.TLR_NO;
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
        IBS.init(SCCGWA, BPCOTLRQ);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLRQ;
        SCCFMT.DATA_LEN = 835;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "=============");
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR);
        CEP.TRC(SCCGWA, BPCOTLRQ.STAF_NO);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_BR);
        CEP.TRC(SCCGWA, BPCOTLRQ.EFF_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.EXP_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_EN_NM);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_TYP);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_LVL);
        CEP.TRC(SCCGWA, BPCOTLRQ.ATH_LVL);
        CEP.TRC(SCCGWA, BPCOTLRQ.TRM_TYP);
        CEP.TRC(SCCGWA, BPCOTLRQ.TELE);
        CEP.TRC(SCCGWA, BPCOTLRQ.PST_ADDRESS);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STSW);
        CEP.TRC(SCCGWA, BPCOTLRQ.TLR_STS);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_STS);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_TIMES);
        CEP.TRC(SCCGWA, BPCOTLRQ.SIGN_TRM);
        CEP.TRC(SCCGWA, BPCOTLRQ.UPD_DT);
        CEP.TRC(SCCGWA, BPCOTLRQ.UPD_TLR);
        CEP.TRC(SCCGWA, BPCOTLRQ.LAST_JRN);
        CEP.TRC(SCCGWA, BPCOTLRQ.ACC_VCH_NO);
        CEP.TRC(SCCGWA, BPCOTLRQ.ACC_SEN_CUS);
        CEP.TRC(SCCGWA, BPCOTLRQ.ACC_SEN_GL);
        CEP.TRC(SCCGWA, BPCOTLRQ.SUPER_FLG);
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
    public void S000_CALL_BPZSAMTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_AMTL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSAMTL;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSAMTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSAMTL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
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