package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLRQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTLT BPRTLT = new BPRTLT();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    SCCGWA SCCGWA;
    BPCFTLRQ BPCFTLRQ;
    public void MP(SCCGWA SCCGWA, BPCFTLRQ BPCFTLRQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLRQ = BPCFTLRQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLRQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCFTLRQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFTLRQ.INFO.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLRQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.KEY.TLR = BPCFTLRQ.INFO.TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        BPCRTLTM.INFO.FUNC = 'Q';
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLRQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCFTLRQ.INFO.TLR_BR = BPRTLT.TLR_BR;
        BPCFTLRQ.INFO.TLR_TKS = BPRTLT.TLR_TKS;
        BPCFTLRQ.INFO.STAF_NO = BPRTLT.STAF_NO;
        BPCFTLRQ.INFO.TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCFTLRQ.INFO.TLR_EN_NM = BPRTLT.TLR_EN_NM;
        BPCFTLRQ.INFO.EFF_DT = BPRTLT.EFF_DT;
        BPCFTLRQ.INFO.EXP_DT = BPRTLT.EXP_DT;
        BPCFTLRQ.INFO.UPD_DT = BPRTLT.UPD_DT;
        BPCFTLRQ.INFO.UPD_TLR = BPRTLT.UPD_TLR;
        BPCFTLRQ.INFO.TLR_TYP = BPRTLT.TLR_TYP;
        BPCFTLRQ.INFO.TLR_LVL = BPRTLT.TLR_LVL;
        BPCFTLRQ.INFO.TX_LVL = BPRTLT.TX_LVL;
        BPCFTLRQ.INFO.ATH_LVL = BPRTLT.ATH_LVL;
        BPCFTLRQ.INFO.ATH_RGN = BPRTLT.ATH_RGN;
        BPCFTLRQ.INFO.TMP_TX_LVL = BPRTLT.TMP_TX_LVL;
        BPCFTLRQ.INFO.TMP_ATH_LVL = BPRTLT.TMP_ATH_LVL;
        BPCFTLRQ.INFO.TX_MODE = BPRTLT.TX_MODE;
        BPCFTLRQ.INFO.SIGN_STS = BPRTLT.SIGN_STS;
        BPCFTLRQ.INFO.TLR_STS = BPRTLT.TLR_STS;
        BPCFTLRQ.INFO.SIGN_DT = BPRTLT.SIGN_DT;
        BPCFTLRQ.INFO.CRO_BR_SIGN = BPRTLT.CRO_BR_SIGN;
        BPCFTLRQ.INFO.SIGN_TIMES = BPRTLT.SIGN_TIMES;
        BPCFTLRQ.INFO.PSW_RETRY = BPRTLT.PSW_RETRY;
        BPCFTLRQ.INFO.TRM_TYP = BPRTLT.TRM_TYP;
        BPCFTLRQ.INFO.SIGN_TRM = BPRTLT.SIGN_TRM;
        BPCFTLRQ.INFO.PRT_IP = BPRTLT.PRT_IP;
        BPCFTLRQ.INFO.TLR_STSW = BPRTLT.TLR_STSW;
        BPCFTLRQ.INFO.LAST_JRN = BPRTLT.LAST_JRN;
        BPCFTLRQ.INFO.ACC_VCH_NO = BPRTLT.ACC_VCH_NO;
        BPCFTLRQ.INFO.TELE = BPRTLT.TELE;
        BPCFTLRQ.INFO.PST_ADDRESS = BPRTLT.PST_ADDRESS;
        BPCFTLRQ.INFO.SIGN_TRM_FLG = BPRTLT.SIGN_TRM_FLG;
        IBS.CPY2CLS(SCCGWA, BPRTLT.TRM_INFO, BPCFTLRQ.INFO.TRM_INFO);
        BPCFTLRQ.INFO.PSW_TYP = BPRTLT.PSW_TYP;
        BPCFTLRQ.INFO.IDEN_DEV_ID = BPRTLT.IDEN_DEV_ID;
        BPCFTLRQ.INFO.TM_OUT_LMT = BPRTLT.TM_OUT_LMT;
        BPCFTLRQ.INFO.ACC_SEN_CUS = BPRTLT.ACC_SEN_CUS;
        BPCFTLRQ.INFO.ACC_SEN_GL = BPRTLT.ACC_SEN_GL;
        BPCFTLRQ.INFO.SUPER_FLG = BPRTLT.SUPER_FLG;
        BPCFTLRQ.INFO.DFT_PRTR_NM = BPRTLT.PRT_NAME1;
        BPCFTLRQ.INFO.PAY_PRTR_NM = BPRTLT.PRT_NAME2;
        BPCFTLRQ.INFO.CNF_PRTR_NM = BPRTLT.PRT_NAME3;
        BPCFTLRQ.INFO.NEW_BR = BPRTLT.NEW_BR;
        BPCFTLRQ.INFO.CI_NO = BPRTLT.CI_NO;
        BPCFTLRQ.INFO.ID_TYP = BPRTLT.ID_TYP;
        BPCFTLRQ.INFO.ID_NO = BPRTLT.ID_NO;
        BPCFTLRQ.INFO.REG_TYP = BPRTLT.REG_TYP;
        BPCFTLRQ.INFO.TLR_CRD_NO = BPRTLT.TLR_CRD_NO;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLRQ = ");
            CEP.TRC(SCCGWA, BPCFTLRQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
