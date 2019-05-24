package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZMERGR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPN_R_ERGR_M = "BP-R-ERGR-M       ";
    String CPN_R_ERGR_B = "BP-R-ERGR-B       ";
    String K_OUTPUT_FMT = "BP277";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 99;
    int K_COL_CNT = 13;
    int K_MAX_ITEM = 20;
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    int K_Q_MAX_CNT = 1000;
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    String WS_CCY = " ";
    String WS_EXR_TYPE = " ";
    String WS_FWD_TENR = " ";
    int WS_TMP_SEQ = 0;
    short WS_EXIST_MAX_SEQ = 0;
    String WS_ERR_MSG = " ";
    int WS_MIN = 0;
    int WS_MAX = 0;
    int WS_CNT = 0;
    BPZMERGR_WS_RULE_DATA WS_RULE_DATA = new BPZMERGR_WS_RULE_DATA();
    short WS_MPAG_CNT = 0;
    BPZMERGR_WS_MPAG_DATA WS_MPAG_DATA = new BPZMERGR_WS_MPAG_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCO277 BPCO277 = new BPCO277();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTERGM BPCTERGM = new BPCTERGM();
    BPCRERGR BPCRERGR = new BPCRERGR();
    BPRERGR BPRERGR = new BPRERGR();
    BPRERGR BPRERGRO = new BPRERGR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCMERGR BPCMERGR;
    public void MP(SCCGWA SCCGWA, BPCMERGR BPCMERGR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMERGR = BPCMERGR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZMERGR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCMERGR.FUNC == 'I') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCMERGR.FUNC == 'A') {
            B020_CREATE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCMERGR.FUNC == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCMERGR.FUNC == 'B') {
            B040_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCMERGR.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCMERGR.FUNC == 'X') {
            B060_PRE_ADD_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCMERGR.FUNC == 'Y'
            || BPCMERGR.FUNC == 'Z') {
            B070_PRE_UPD_DEL();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCMERGR.FUNC != 'I' 
            && BPCMERGR.FUNC != 'A' 
            && BPCMERGR.FUNC != 'M' 
            && BPCMERGR.FUNC != 'D' 
            && BPCMERGR.FUNC != 'B' 
            && BPCMERGR.FUNC != 'X' 
            && BPCMERGR.FUNC != 'Y' 
            && BPCMERGR.FUNC != 'Z')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B001_01_ARRAY_PROC();
        if (pgmRtn) return;
        if (BPCMERGR.CCY.trim().length() > 0) {
            WS_CCY = BPCMERGR.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCMERGR.FUNC == 'A' 
            || BPCMERGR.FUNC == 'M' 
            || BPCMERGR.FUNC == 'D') {
            B001_03_ARRAY_CHK();
            if (pgmRtn) return;
        }
    }
    public void B001_01_ARRAY_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_MAX_ITEM; WS_I += 1) {
            if (BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ != 0) {
                WS_J += 1;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_SEQ = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_EXR_TYPE = BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_FWD_TENR = BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_SPRD_MTH = BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_CBAS_RAT = BPCMERGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_BASE_RAT = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_BASE_TNR = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_COMP_MTH = BPCMERGR.RULE_DATA.DATA[WS_I-1].COMP_MTH;
                WS_RULE_DATA.WS_DATA[WS_J-1].WS_D_SPRD_VAL = BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL;
            }
        }
        if (WS_J == 0 
            && (BPCMERGR.FUNC == 'A' 
            || BPCMERGR.FUNC == 'M' 
            || BPCMERGR.FUNC == 'D' 
            || BPCMERGR.FUNC == 'I')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_CNT = WS_J;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RULE_DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMERGR.RULE_DATA);
        }
        if (BPCMERGR.FUNC == 'A' 
            || BPCMERGR.FUNC == 'M' 
            || BPCMERGR.FUNC == 'D') {
            WS_MAX = 0;
            WS_MIN = 100;
            for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
                if (BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ < WS_MIN) {
                    WS_MIN = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
                }
                if (BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ > WS_MAX) {
                    WS_MAX = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
                }
            }
            WS_TMP_SEQ = WS_MAX - WS_MIN + 1;
            if (WS_TMP_SEQ != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SEQ_NOT_CONTINUE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            for (WS_I = 1; WS_I != WS_CNT; WS_I += 1) {
                WS_K = (short) (WS_I + 1);
                for (WS_J = WS_K; WS_J <= WS_CNT; WS_J += 1) {
                    if (BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ == BPCMERGR.RULE_DATA.DATA[WS_J-1].SEQ) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_SEQ;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B001_03_ARRAY_CHK() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            B001_03_01_CHK_RATE_TENOR();
            if (pgmRtn) return;
            B001_03_03_CHK_BASE_RATE_TENOR();
            if (pgmRtn) return;
        }
    }
    public void B001_03_01_CHK_RATE_TENOR() throws IOException,SQLException,Exception {
        WS_EXR_TYPE = BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
        WS_FWD_TENR = BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
        R000_CHK_TYPE_TENOR();
        if (pgmRtn) return;
    }
    public void B001_03_03_CHK_BASE_RATE_TENOR() throws IOException,SQLException,Exception {
        WS_EXR_TYPE = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
        WS_FWD_TENR = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
        R000_CHK_TYPE_TENOR();
        if (pgmRtn) return;
    }
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        if (BPCMERGR.CCY.trim().length() == 0 
            || BPCMERGR.WH_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCMERGR.CCY;
        BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
        BPRERGR.KEY.SQN = BPCMERGR.RULE_DATA.DATA[1-1].SEQ;
        BPCRERGR.INFO.FUNC = '1';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        if (BPCRERGR.INFO.RTN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCMERGR.EFF_DT = BPRERGR.EFF_DT;
        BPCMERGR.EFF_TM = BPRERGR.EFF_TM;
        WS_I = 0;
        while (BPCRERGR.INFO.RTN_INFO != 'N' 
            && WS_I <= K_MAX_ITEM) {
            WS_I += 1;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ = BPRERGR.KEY.SQN;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE = BPRERGR.EXR_TYP;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR = BPRERGR.FWD_TENOR;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH = BPRERGR.SPRD_METH;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT = BPRERGR.CMP_BASE;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_RAT = BPRERGR.BASE_EXR_TYP;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_TNR = BPRERGR.BASE_FWD_TENOR;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].COMP_MTH = BPRERGR.CMP_FLG;
            BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL = BPRERGR.AMEND_SP;
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCMERGR.CCY;
        BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
        BPCRERGR.INFO.FUNC = '2';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        if (BPCRERGR.INFO.RTN_INFO == 'Y') {
            WS_EXIST_MAX_SEQ = BPRERGR.KEY.SQN;
        } else {
            WS_EXIST_MAX_SEQ = 0;
        }
        WS_TMP_SEQ = WS_EXIST_MAX_SEQ + 1;
        if (WS_TMP_SEQ != WS_MIN) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SEQ_NOT_CONTINUE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCMERGR.CCY;
            BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
            BPRERGR.KEY.SQN = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.EXR_TYP = BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
            BPRERGR.FWD_TENOR = BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPCTERGM.INFO.FUNC = 'R';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            if (BPCTERGM.RETURN_INFO == 'F') {
                IBS.CLONE(SCCGWA, BPRERGR, BPRERGRO);
                BPCTERGM.INFO.FUNC = 'D';
                S000_CALL_BPZTERGM();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPRERGR);
                IBS.init(SCCGWA, BPCTERGM);
                IBS.CLONE(SCCGWA, BPRERGRO, BPRERGR);
                BPRERGR.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRERGR.KEY.EXP_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRERGR.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
                BPRERGR.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRERGR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRERGR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTERGM.INFO.FUNC = 'C';
                S000_CALL_BPZTERGM();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCMERGR.CCY;
            BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
            BPRERGR.KEY.SQN = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.EXR_TYP = BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
            BPRERGR.FWD_TENOR = BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPRERGR.KEY.EXP_TM = K_MAX_TIME;
            BPRERGR.SPRD_METH = BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH;
            BPRERGR.CMP_BASE = BPCMERGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT;
            BPRERGR.BASE_EXR_TYP = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
            BPRERGR.BASE_FWD_TENOR = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
            BPRERGR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRERGR.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRERGR.CMP_FLG = BPCMERGR.RULE_DATA.DATA[WS_I-1].COMP_MTH;
            BPRERGR.AMEND_SP = BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL;
            BPRERGR.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRERGR.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRERGR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRERGR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTERGM.INFO.FUNC = 'C';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
        }
        BPCMERGR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCMERGR.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCMERGR.CCY;
            BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
            BPRERGR.KEY.SQN = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.EXR_TYP = BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
            BPRERGR.FWD_TENOR = BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPCTERGM.INFO.FUNC = 'R';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            if (BPCTERGM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRERGR.SPRD_METH = BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH;
            BPRERGR.CMP_BASE = BPCMERGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT;
            BPRERGR.BASE_EXR_TYP = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
            BPRERGR.BASE_FWD_TENOR = BPCMERGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
            BPRERGR.CMP_FLG = BPCMERGR.RULE_DATA.DATA[WS_I-1].COMP_MTH;
            BPRERGR.AMEND_SP = BPCMERGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL;
            BPRERGR.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRERGR.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRERGR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRERGR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTERGM.INFO.FUNC = 'U';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCMERGR.CCY;
            BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
            BPRERGR.KEY.SQN = BPCMERGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.EXR_TYP = BPCMERGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
            BPRERGR.FWD_TENOR = BPCMERGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPCTERGM.INFO.FUNC = 'R';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            if (BPCTERGM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCTERGM.INFO.FUNC = 'D';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
        }
    }
    public void B040_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        if (BPCMERGR.CCY.trim().length() == 0) {
            BPRERGR.KEY.CCY = "%%%";
        } else {
            BPRERGR.KEY.CCY = BPCMERGR.CCY;
        }
        if (BPCMERGR.WH_FLG != ' ') {
            BPRERGR.KEY.HDAY_FLG = ALL.charAt(0);
        } else {
            BPRERGR.KEY.HDAY_FLG = BPCMERGR.WH_FLG;
        }
        BPRERGR.KEY.SQN = BPCMERGR.RULE_DATA.DATA[1-1].SEQ;
        BPRERGR.KEY.EXP_DT = K_MAX_DATE;
        BPCRERGR.INFO.FUNC = '3';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        if (BPCRERGR.INFO.RTN_INFO == 'Y') {
            R000_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRERGR.INFO.RTN_INFO != 'N' 
            && WS_MPAG_CNT <= K_Q_MAX_CNT 
            && SCCMPAG.FUNC != 'E') {
            R000_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        if (WS_MPAG_CNT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_PRE_ADD_PROC() throws IOException,SQLException,Exception {
    }
    public void B070_PRE_UPD_DEL() throws IOException,SQLException,Exception {
        B010_INQUIRE_PROC();
        if (pgmRtn) return;
    }
    public void R000_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 52;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        WS_MPAG_CNT += 1;
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_CCY = BPRERGR.KEY.CCY;
        WS_MPAG_DATA.WS_L_WH_FLG = BPRERGR.KEY.HDAY_FLG;
        WS_MPAG_DATA.WS_L_SEQ = BPRERGR.KEY.SQN;
        WS_MPAG_DATA.WS_L_EXR_TYPE = BPRERGR.EXR_TYP;
        WS_MPAG_DATA.WS_L_FWD_TENR = BPRERGR.FWD_TENOR;
        WS_MPAG_DATA.WS_L_SPRD_MTH = BPRERGR.SPRD_METH;
        WS_MPAG_DATA.WS_L_CBAS_RAT = BPRERGR.CMP_BASE;
        WS_MPAG_DATA.WS_L_BASE_RAT = BPRERGR.BASE_EXR_TYP;
        WS_MPAG_DATA.WS_L_BASE_TNR = BPRERGR.BASE_FWD_TENOR;
        WS_MPAG_DATA.WS_L_COMP_MTH = BPRERGR.CMP_FLG;
        WS_MPAG_DATA.WS_L_SPRD_VAL = BPRERGR.AMEND_SP;
        WS_MPAG_DATA.WS_L_EFF_DATE = BPRERGR.EFF_DT;
        WS_MPAG_DATA.WS_L_EFF_TIME = BPRERGR.EFF_TM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 52;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CHK_TYPE_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = WS_EXR_TYPE;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPREXRT.DAT_TXT.FWD_IND == '0' 
            && WS_FWD_TENR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPREXRT.DAT_TXT.FWD_IND == '1' 
            && WS_FWD_TENR.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_FWD_TENR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_FWD_TENOR;
            BPCOQPCD.INPUT_DATA.CODE = WS_FWD_TENR;
            S000_CALL_BPZQPCD();
            if (pgmRtn) return;
            if (BPCOQPCD.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_TENOR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B080_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCMERGR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCMERGR.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        if (BPCMERGR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = "EXCHANGE RATE TYPE MAINTAIN";
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (BPCMERGR.FUNC == 'X' 
            || BPCMERGR.FUNC == 'Y' 
            || BPCMERGR.FUNC == 'Z') {
            SCCFMT.FMTID = K_OUTPUT_FMT_X;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT;
        }
        IBS.init(SCCGWA, BPCO277);
        BPCO277.CCY = BPCMERGR.CCY;
        BPCO277.EFF_DT = BPCMERGR.EFF_DT;
        BPCO277.EFF_TM = BPCMERGR.EFF_TM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCMERGR.RULE_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCO277.RULE_DATA);
        SCCFMT.DATA_PTR = BPCO277;
        SCCFMT.DATA_LEN = 757;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTERGR() throws IOException,SQLException,Exception {
        BPCRERGR.INFO.POINTER = BPRERGR;
        BPCRERGR.INFO.LEN = 122;
        IBS.CALLCPN(SCCGWA, CPN_R_ERGR_B, BPCRERGR);
        if (BPCRERGR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRERGR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTERGM() throws IOException,SQLException,Exception {
        BPCTERGM.INFO.POINTER = BPRERGR;
        BPCTERGM.INFO.LEN = 122;
        IBS.CALLCPN(SCCGWA, CPN_R_ERGR_M, BPCTERGM);
        if (BPCTERGM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTERGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_CODE, BPCOQPCD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCMERGR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCMERGR = ");
            CEP.TRC(SCCGWA, BPCMERGR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
