package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZDEGR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPN_R_ERGR_M = "BP-R-ERGR-M       ";
    String CPN_R_ERGR_B = "BP-R-ERGR-B       ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPY_BPRERGR = "BPRERGR ";
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
    String WS_T_SPRD_VAL = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_L = 0;
    short WS_F = 0;
    short WS_FN = 0;
    short WS_F_SEQ = 0;
    String WS_CCY = " ";
    String WS_EXR_TYPE = " ";
    String WS_FWD_TENR = " ";
    int WS_TMP_SEQ = 0;
    short WS_EXIST_MAX_SEQ = 0;
    String WS_ERR_MSG = " ";
    BPZDEGR_WS_HIS_REF WS_HIS_REF = new BPZDEGR_WS_HIS_REF();
    int WS_MIN = 0;
    int WS_MAX = 0;
    int WS_CNT = 0;
    BPZDEGR_WS_RULE_DATA WS_RULE_DATA = new BPZDEGR_WS_RULE_DATA();
    short WS_MPAG_CNT = 0;
    BPZDEGR_WS_MPAG_DATA WS_MPAG_DATA = new BPZDEGR_WS_MPAG_DATA();
    BPZDEGR_WS_SEQS[] WS_SEQS = new BPZDEGR_WS_SEQS[99];
    char WS_HIS_FLG = ' ';
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
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPRERGR BPRERGR = new BPRERGR();
    BPRERGR BPRERGRO = new BPRERGR();
    BPRERGR BPRERGRN = new BPRERGR();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCDEGR BPCDEGR;
    public BPZDEGR() {
        for (int i=0;i<99;i++) WS_SEQS[i] = new BPZDEGR_WS_SEQS();
    }
    public void MP(SCCGWA SCCGWA, BPCDEGR BPCDEGR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCDEGR = BPCDEGR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZDEGR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEVSOSCCY");
        CEP.TRC(SCCGWA, BPCDEGR.CCY);
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCDEGR.FUNC == 'I') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'A') {
            B020_CREATE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'B') {
            B040_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'X') {
            B060_PRE_ADD_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'Y') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDEGR.FUNC == 'Z') {
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
        if ((BPCDEGR.FUNC != 'I' 
            && BPCDEGR.FUNC != 'A' 
            && BPCDEGR.FUNC != 'M' 
            && BPCDEGR.FUNC != 'D' 
            && BPCDEGR.FUNC != 'B' 
            && BPCDEGR.FUNC != 'X' 
            && BPCDEGR.FUNC != 'Y' 
            && BPCDEGR.FUNC != 'Z')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCDEGR.FUNC != 'B') 
            && BPCDEGR.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B001_01_ARRAY_PROC();
        if (pgmRtn) return;
        if (BPCDEGR.CCY.trim().length() > 0) {
            WS_CCY = BPCDEGR.CCY;
            CEP.TRC(SCCGWA, BPCDEGR.CCY);
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCDEGR.FUNC == 'A' 
            || BPCDEGR.FUNC == 'M') {
            for (WS_F = 1; WS_F <= 19 
                && BPCDEGR.RULE_DATA.DATA[WS_F-1].EXR_TYPE.trim().length() != 0; WS_F += 1) {
                WS_FN = (short) (WS_F + 1);
                CEP.TRC(SCCGWA, WS_FN);
                CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_F-1].ITP_IND);
                CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_FN-1].ITP_IND);
                if (BPCDEGR.RULE_DATA.DATA[WS_FN-1].ITP_IND != ' ') {
                    if (BPCDEGR.RULE_DATA.DATA[WS_F-1].ITP_IND == 'Y' 
                        && BPCDEGR.RULE_DATA.DATA[WS_FN-1].ITP_IND == 'N') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FWD_IND_ERROR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[1-1].SEQ);
            if (BPCDEGR.RULE_DATA.DATA[1-1].SEQ > 2) {
                WS_F_SEQ = (short) (BPCDEGR.RULE_DATA.DATA[1-1].SEQ - 1);
                CEP.TRC(SCCGWA, WS_F_SEQ);
                IBS.init(SCCGWA, BPRERGR);
                IBS.init(SCCGWA, BPCRERGR);
                BPRERGR.KEY.CCY = BPCDEGR.CCY;
                BPCRERGR.INFO.FUNC = '2';
                S000_CALL_BPZTERGR();
                if (pgmRtn) return;
                BPCRERGR.INFO.FUNC = 'R';
                S000_CALL_BPZTERGR();
                if (pgmRtn) return;
                while (BPCRERGR.INFO.RTN_INFO != 'N') {
                    CEP.TRC(SCCGWA, WS_F_SEQ);
                    CEP.TRC(SCCGWA, BPRERGR.KEY.SQN);
                    if (WS_F_SEQ == BPRERGR.KEY.SQN) {
                        if (BPCDEGR.RULE_DATA.DATA[1-1].ITP_IND == 'N' 
                            && BPRERGR.ITP_IND == 'Y') {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FWD_IND_ERROR;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                    BPCRERGR.INFO.FUNC = 'R';
                    S000_CALL_BPZTERGR();
                    if (pgmRtn) return;
                }
                BPCRERGR.INFO.FUNC = 'E';
                S000_CALL_BPZTERGR();
                if (pgmRtn) return;
            }
        }
    }
    public void B001_01_ARRAY_PROC() throws IOException,SQLException,Exception {
        WS_J = 0;
        for (WS_I = 1; WS_I <= K_MAX_ITEM; WS_I += 1) {
            if (BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE.trim().length() > 0 
                || (BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ != 0 
                && BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ != ' ')) {
                WS_J += 1;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RULE_DATA.WS_DATA[WS_J-1]);
                CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ);
            }
        }
        CEP.TRC(SCCGWA, WS_J);
        if (WS_J == 0 
            && (BPCDEGR.FUNC == 'A' 
            || BPCDEGR.FUNC == 'M' 
            || BPCDEGR.FUNC == 'D')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_CNT = WS_J;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RULE_DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCDEGR.RULE_DATA);
        }
        if (BPCDEGR.FUNC == 'A' 
            || BPCDEGR.FUNC == 'M') {
            WS_MAX = 0;
            WS_MIN = 100;
            for (WS_I = 1; WS_I <= WS_CNT 
                && BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE.trim().length() != 0; WS_I += 1) {
                CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND);
                if (BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND == 'Y') {
                    CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT);
                    CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY);
                    CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR);
                    if (BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT.trim().length() > 0 
                        || BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY.trim().length() > 0 
                        || BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR.trim().length() > 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.FWD_IND);
                    if (BPREXRT.DAT_TXT.FWD_IND != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_FWRD_RATE_GROUP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    CEP.TRC(SCCGWA, "DEVSOS");
                }
                if (BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ < WS_MIN) {
                    WS_MIN = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
                }
                if (BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ > WS_MAX) {
                    WS_MAX = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
                }
            }
            WS_TMP_SEQ = WS_MAX - WS_MIN + 1;
            CEP.TRC(SCCGWA, WS_TMP_SEQ);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_MAX);
            CEP.TRC(SCCGWA, WS_MIN);
            for (WS_I = 1; WS_I != WS_CNT; WS_I += 1) {
                WS_K = (short) (WS_I + 1);
                for (WS_J = WS_K; WS_J <= WS_CNT; WS_J += 1) {
                    if (BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ == BPCDEGR.RULE_DATA.DATA[WS_J-1].SEQ) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_SEQ;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B001_01_01_CHK_RATE_TENOR() throws IOException,SQLException,Exception {
        WS_CCY = BPCDEGR.CCY;
        WS_EXR_TYPE = BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
        WS_FWD_TENR = BPCDEGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
    }
    public void B001_01_03_CHK_BASE_RATE_TENOR() throws IOException,SQLException,Exception {
        WS_CCY = BPCDEGR.CCY;
        WS_EXR_TYPE = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
        WS_FWD_TENR = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
    }
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        if (BPCDEGR.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCDEGR.CCY;
        BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[1-1].SEQ;
        BPCRERGR.INFO.FUNC = '1';
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        CEP.TRC(SCCGWA, BPCDEGR.CCY);
        CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[1-1].SEQ);
        CEP.TRC(SCCGWA, BPRERGR);
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        CEP.TRC(SCCGWA, BPCDEGR.CCY);
        CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[1-1].SEQ);
        CEP.TRC(SCCGWA, BPRERGR);
        if (BPCRERGR.INFO.RTN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCDEGR.EFF_DT = BPRERGR.EFF_DT;
        BPCDEGR.EFF_TM = BPRERGR.EFF_TM;
        WS_I = 0;
        WS_L = 0;
        WS_K = 0;
        while (BPCRERGR.INFO.RTN_INFO != 'N' 
            && WS_I <= K_MAX_ITEM) {
            WS_I += 1;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ = BPRERGR.KEY.SQN;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE = BPRERGR.EXR_TYP;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].FWD_TENR = BPRERGR.FWD_TENOR;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND = BPRERGR.ITP_IND;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH = BPRERGR.SPRD_METH;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT = BPRERGR.CMP_BASE;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT = BPRERGR.BASE_EXR_TYP;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY = BPRERGR.BASE_CCY;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR = BPRERGR.BASE_FWD_TENOR;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].COMP_MTH = BPRERGR.CMP_FLG;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL = BPRERGR.AMEND_SP;
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        if (WS_I < 20 
            && BPCDEGR.FUNC == 'Y') {
            for (WS_L = WS_I; WS_L <= 20; WS_L += 1) {
                WS_K = (short) (WS_L - WS_I);
                BPCDEGR.RULE_DATA.DATA[WS_L-1].SEQ = (short) (BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ + WS_K);
            }
        }
    }
    public void B020_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCDEGR.CCY;
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAAAAA");
        CEP.TRC(SCCGWA, BPCDEGR.CCY);
        BPCRERGR.INFO.FUNC = '2';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CALL-BPZTERGR");
        if (BPCRERGR.INFO.RTN_INFO == 'Y') {
            CEP.TRC(SCCGWA, BPRERGR.KEY.SQN);
            WS_EXIST_MAX_SEQ = BPRERGR.KEY.SQN;
        } else {
            CEP.TRC(SCCGWA, "CALLOTHER");
            WS_EXIST_MAX_SEQ = 0;
        }
        WS_TMP_SEQ = WS_EXIST_MAX_SEQ + 1;
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCDEGR.CCY;
            BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPRERGR.KEY.EXP_TM = K_MAX_TIME;
            BPCTERGM.INFO.FUNC = 'Q';
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            if (BPCTERGM.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCDEGR.CCY;
            BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.EXR_TYP = BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
            BPRERGR.FWD_TENOR = BPCDEGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
            BPRERGR.ITP_IND = BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPRERGR.KEY.EXP_TM = K_MAX_TIME;
            BPRERGR.SPRD_METH = BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH;
            BPRERGR.CMP_BASE = BPCDEGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT;
            BPRERGR.BASE_EXR_TYP = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
            BPRERGR.BASE_CCY = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY;
            BPRERGR.BASE_FWD_TENOR = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
            BPRERGR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRERGR.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRERGR.CMP_FLG = BPCDEGR.RULE_DATA.DATA[WS_I-1].COMP_MTH;
            BPRERGR.AMEND_SP = BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL;
            BPRERGR.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRERGR.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRERGR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRERGR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTERGM.INFO.FUNC = 'C';
            CEP.TRC(SCCGWA, "TTTTTTT2");
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            WS_HIS_FLG = 'A';
            WS_HIS_REF.WS_HIS_CCY = BPRERGR.KEY.CCY;
            WS_HIS_REF.WS_HIS_SQN = BPRERGR.KEY.SQN;
            WS_HIS_REF.WS_HIS_EXR_TYP = BPRERGR.EXR_TYP;
            WS_HIS_REF.WS_HIS_FWD_TENOR = BPRERGR.FWD_TENOR;
            R000_HISTORY_PROC();
            if (pgmRtn) return;
        }
        BPCDEGR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCDEGR.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCDEGR.CCY;
            BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPRERGR.KEY.EXP_TM = K_MAX_TIME;
            BPCTERGM.INFO.FUNC = 'R';
            CEP.TRC(SCCGWA, "TTTTTTT3");
            CEP.TRC(SCCGWA, BPRERGR);
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            if (BPCTERGM.RETURN_INFO == 'N') {
                IBS.init(SCCGWA, BPRERGR);
                IBS.init(SCCGWA, BPCTERGM);
                BPRERGR.KEY.CCY = BPCDEGR.CCY;
                BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
                BPRERGR.EXR_TYP = BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
                BPRERGR.FWD_TENOR = BPCDEGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
                BPRERGR.ITP_IND = BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND;
                BPRERGR.KEY.EXP_DT = K_MAX_DATE;
                BPRERGR.KEY.EXP_TM = K_MAX_TIME;
                BPRERGR.SPRD_METH = BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH;
                BPRERGR.CMP_BASE = BPCDEGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT;
                BPRERGR.BASE_EXR_TYP = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
                BPRERGR.BASE_CCY = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY;
                BPRERGR.BASE_FWD_TENOR = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
                BPRERGR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRERGR.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRERGR.CMP_FLG = BPCDEGR.RULE_DATA.DATA[WS_I-1].COMP_MTH;
                BPRERGR.AMEND_SP = BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL;
                BPRERGR.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
                BPRERGR.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRERGR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRERGR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTERGM.INFO.FUNC = 'C';
                CEP.TRC(SCCGWA, "TTTTTTT4");
                CEP.TRC(SCCGWA, BPRERGR);
                S000_CALL_BPZTERGM();
                if (pgmRtn) return;
                WS_HIS_FLG = 'A';
                WS_HIS_REF.WS_HIS_CCY = BPRERGR.KEY.CCY;
                WS_HIS_REF.WS_HIS_SQN = BPRERGR.KEY.SQN;
                WS_HIS_REF.WS_HIS_EXR_TYP = BPRERGR.EXR_TYP;
                WS_HIS_REF.WS_HIS_FWD_TENOR = BPRERGR.FWD_TENOR;
                R000_HISTORY_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CLONE(SCCGWA, BPRERGR, BPRERGRO);
                BPRERGR.EXR_TYP = BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE;
                BPRERGR.FWD_TENOR = BPCDEGR.RULE_DATA.DATA[WS_I-1].FWD_TENR;
                BPRERGR.ITP_IND = BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND;
                BPRERGR.SPRD_METH = BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH;
                BPRERGR.CMP_BASE = BPCDEGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT;
                BPRERGR.BASE_EXR_TYP = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT;
                BPRERGR.BASE_CCY = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY;
                BPRERGR.BASE_FWD_TENOR = BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR;
                BPRERGR.CMP_FLG = BPCDEGR.RULE_DATA.DATA[WS_I-1].COMP_MTH;
                BPRERGR.AMEND_SP = BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL;
                BPRERGR.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
                BPRERGR.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRERGR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRERGR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTERGM.INFO.FUNC = 'U';
                CEP.TRC(SCCGWA, "TTTTTTT5");
                CEP.TRC(SCCGWA, BPRERGR);
                S000_CALL_BPZTERGM();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, BPRERGR, BPRERGRN);
                WS_HIS_FLG = 'M';
                WS_HIS_REF.WS_HIS_CCY = BPRERGR.KEY.CCY;
                WS_HIS_REF.WS_HIS_SQN = BPRERGR.KEY.SQN;
                WS_HIS_REF.WS_HIS_EXR_TYP = BPRERGR.EXR_TYP;
                WS_HIS_REF.WS_HIS_FWD_TENOR = BPRERGR.FWD_TENOR;
                CEP.TRC(SCCGWA, BPRERGRN.KEY.CCY);
                CEP.TRC(SCCGWA, BPRERGRN.KEY.SQN);
                CEP.TRC(SCCGWA, BPRERGRN.EXR_TYP);
                CEP.TRC(SCCGWA, BPRERGRN.FWD_TENOR);
                CEP.TRC(SCCGWA, BPRERGRN.ITP_IND);
                CEP.TRC(SCCGWA, BPRERGRN.SPRD_METH);
                CEP.TRC(SCCGWA, BPRERGRN.CMP_BASE);
                CEP.TRC(SCCGWA, BPRERGRN.BASE_EXR_TYP);
                CEP.TRC(SCCGWA, BPRERGRN.BASE_CCY);
                CEP.TRC(SCCGWA, BPRERGRN.CMP_FLG);
                CEP.TRC(SCCGWA, BPRERGRN.AMEND_SP);
                CEP.TRC(SCCGWA, BPRERGRN.BASE_FWD_TENOR);
                CEP.TRC(SCCGWA, BPRERGRO.KEY.CCY);
                CEP.TRC(SCCGWA, BPRERGRO.KEY.SQN);
                CEP.TRC(SCCGWA, BPRERGRO.EXR_TYP);
                CEP.TRC(SCCGWA, BPRERGRO.FWD_TENOR);
                CEP.TRC(SCCGWA, BPRERGRO.ITP_IND);
                CEP.TRC(SCCGWA, BPRERGRO.SPRD_METH);
                CEP.TRC(SCCGWA, BPRERGRO.CMP_BASE);
                CEP.TRC(SCCGWA, BPRERGRO.BASE_EXR_TYP);
                CEP.TRC(SCCGWA, BPRERGRO.BASE_CCY);
                CEP.TRC(SCCGWA, BPRERGRO.CMP_FLG);
                CEP.TRC(SCCGWA, BPRERGRO.AMEND_SP);
                CEP.TRC(SCCGWA, BPRERGRO.BASE_FWD_TENOR);
                if (!BPRERGRN.KEY.CCY.equalsIgnoreCase(BPRERGRO.KEY.CCY) 
                    || BPRERGRN.KEY.SQN != BPRERGRO.KEY.SQN 
                    || !BPRERGRN.EXR_TYP.equalsIgnoreCase(BPRERGRO.EXR_TYP) 
                    || !BPRERGRN.FWD_TENOR.equalsIgnoreCase(BPRERGRO.FWD_TENOR) 
                    || BPRERGRN.ITP_IND != BPRERGRO.ITP_IND 
                    || BPRERGRN.SPRD_METH != BPRERGRO.SPRD_METH 
                    || !BPRERGRN.CMP_BASE.equalsIgnoreCase(BPRERGRO.CMP_BASE) 
                    || !BPRERGRN.BASE_EXR_TYP.equalsIgnoreCase(BPRERGRO.BASE_EXR_TYP) 
                    || !BPRERGRN.BASE_CCY.equalsIgnoreCase(BPRERGRO.BASE_CCY) 
                    || BPRERGRN.CMP_FLG != BPRERGRO.CMP_FLG 
                    || BPRERGRN.AMEND_SP != BPRERGRO.AMEND_SP 
                    || !BPRERGRN.BASE_FWD_TENOR.equalsIgnoreCase(BPRERGRO.BASE_FWD_TENOR)) {
                    R000_HISTORY_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPRERGR);
            IBS.init(SCCGWA, BPCTERGM);
            BPRERGR.KEY.CCY = BPCDEGR.CCY;
            BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ;
            BPRERGR.KEY.EXP_DT = K_MAX_DATE;
            BPRERGR.KEY.EXP_TM = K_MAX_TIME;
            BPCTERGM.INFO.FUNC = 'R';
            CEP.TRC(SCCGWA, "TTTTTTT6");
            CEP.TRC(SCCGWA, BPRERGR);
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            if (BPCTERGM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCTERGM.INFO.FUNC = 'D';
            CEP.TRC(SCCGWA, "TTTTTTT7");
            CEP.TRC(SCCGWA, BPRERGR);
            S000_CALL_BPZTERGM();
            if (pgmRtn) return;
            WS_HIS_FLG = 'D';
            WS_HIS_REF.WS_HIS_CCY = BPRERGR.KEY.CCY;
            WS_HIS_REF.WS_HIS_SQN = BPRERGR.KEY.SQN;
            WS_HIS_REF.WS_HIS_EXR_TYP = BPRERGR.EXR_TYP;
            WS_HIS_REF.WS_HIS_FWD_TENOR = BPRERGR.FWD_TENOR;
            R000_HISTORY_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCDEGR.CCY;
        BPRERGR.KEY.SQN = 0;
        BPCRERGR.INFO.FUNC = '1';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (BPCRERGR.INFO.RTN_INFO != 'N') {
            WS_CNT += 1;
            WS_SEQS[WS_CNT-1].WS_SEQ = BPRERGR.KEY.SQN;
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            if (WS_SEQS[WS_I-1].WS_SEQ != WS_I) {
                IBS.init(SCCGWA, BPRERGR);
                IBS.init(SCCGWA, BPCTERGM);
                BPRERGR.KEY.CCY = BPCDEGR.CCY;
                BPRERGR.KEY.SQN = WS_SEQS[WS_I-1].WS_SEQ;
                BPRERGR.KEY.EXP_DT = K_MAX_DATE;
                BPRERGR.KEY.EXP_TM = K_MAX_TIME;
                BPCTERGM.INFO.FUNC = 'R';
                S000_CALL_BPZTERGM();
                if (pgmRtn) return;
                if (BPCTERGM.RETURN_INFO == 'F') {
                    IBS.CLONE(SCCGWA, BPRERGR, BPRERGRO);
                    BPCTERGM.INFO.FUNC = 'D';
                    S000_CALL_BPZTERGM();
                    if (pgmRtn) return;
                    IBS.CLONE(SCCGWA, BPRERGRO, BPRERGR);
                    BPRERGR.KEY.SQN = WS_I;
                    BPCTERGM.INFO.FUNC = 'C';
                    S000_CALL_BPZTERGM();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B040_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[1-1].SEQ);
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        if (BPCDEGR.CCY.trim().length() == 0) {
            BPRERGR.KEY.CCY = "%%%";
        } else {
            BPRERGR.KEY.CCY = BPCDEGR.CCY;
        }
        BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[1-1].SEQ;
        CEP.TRC(SCCGWA, BPCDEGR.RULE_DATA.DATA[1-1].SEQ);
        CEP.TRC(SCCGWA, BPRERGR.KEY.SQN);
        CEP.TRC(SCCGWA, BPRERGR);
        BPCRERGR.INFO.FUNC = '3';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRERGR);
        if (BPCRERGR.INFO.RTN_INFO == 'Y') {
            B040_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRERGR.INFO.RTN_INFO != 'N' 
            && WS_MPAG_CNT <= K_Q_MAX_CNT 
            && SCCMPAG.FUNC != 'E') {
            B040_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
    }
    public void B060_PRE_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCDEGR.CCY;
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
        for (WS_L = 1; WS_L <= 20; WS_L += 1) {
            WS_EXIST_MAX_SEQ = (short) (WS_EXIST_MAX_SEQ + 1);
            BPCDEGR.RULE_DATA.DATA[WS_L-1].SEQ = WS_EXIST_MAX_SEQ;
        }
    }
    public void B070_PRE_UPD_DEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCTERGM);
        BPRERGR.KEY.CCY = BPCDEGR.CCY;
        BPRERGR.KEY.SQN = BPCDEGR.RULE_DATA.DATA[1-1].SEQ;
        BPRERGR.KEY.EXP_DT = K_MAX_DATE;
        BPRERGR.KEY.EXP_TM = K_MAX_TIME;
        BPCTERGM.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, "TTTTTTT6");
        CEP.TRC(SCCGWA, BPRERGR);
        S000_CALL_BPZTERGM();
        if (pgmRtn) return;
        if (BPCTERGM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCDEGR.RULE_DATA.DATA[1-1].SEQ = BPRERGR.KEY.SQN;
        BPCDEGR.RULE_DATA.DATA[1-1].EXR_TYPE = BPRERGR.EXR_TYP;
        BPCDEGR.RULE_DATA.DATA[1-1].FWD_TENR = BPRERGR.FWD_TENOR;
        BPCDEGR.RULE_DATA.DATA[1-1].ITP_IND = BPRERGR.ITP_IND;
        BPCDEGR.RULE_DATA.DATA[1-1].SPRD_MTH = BPRERGR.SPRD_METH;
        BPCDEGR.RULE_DATA.DATA[1-1].CBAS_RAT = BPRERGR.CMP_BASE;
        BPCDEGR.RULE_DATA.DATA[1-1].BASE_RAT = BPRERGR.BASE_EXR_TYP;
        BPCDEGR.RULE_DATA.DATA[1-1].BASE_CCY = BPRERGR.BASE_CCY;
        BPCDEGR.RULE_DATA.DATA[1-1].BASE_TNR = BPRERGR.BASE_FWD_TENOR;
        BPCDEGR.RULE_DATA.DATA[1-1].COMP_MTH = BPRERGR.CMP_FLG;
        BPCDEGR.RULE_DATA.DATA[1-1].SPRD_VAL = BPRERGR.AMEND_SP;
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 54;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        WS_MPAG_CNT += 1;
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_CCY = BPRERGR.KEY.CCY;
        WS_MPAG_DATA.WS_L_SEQ = BPRERGR.KEY.SQN;
        WS_MPAG_DATA.WS_L_EXR_TYPE = BPRERGR.EXR_TYP;
        WS_MPAG_DATA.WS_L_FWD_TENR = BPRERGR.FWD_TENOR;
        WS_MPAG_DATA.WS_L_ITP_IND = BPRERGR.ITP_IND;
        WS_MPAG_DATA.WS_L_SPRD_MTH = BPRERGR.SPRD_METH;
        WS_MPAG_DATA.WS_L_CBAS_RAT = BPRERGR.CMP_BASE;
        WS_MPAG_DATA.WS_L_BASE_RAT = BPRERGR.BASE_EXR_TYP;
        WS_MPAG_DATA.WS_L_BASE_CCY = BPRERGR.BASE_CCY;
        WS_MPAG_DATA.WS_L_BASE_TNR = BPRERGR.BASE_FWD_TENOR;
        WS_MPAG_DATA.WS_L_COMP_MTH = BPRERGR.CMP_FLG;
        WS_MPAG_DATA.WS_L_SPRD_VAL = BPRERGR.AMEND_SP;
        WS_MPAG_DATA.WS_L_EFF_DATE = BPRERGR.EFF_DT;
        WS_MPAG_DATA.WS_L_EFF_TIME = BPRERGR.EFF_TM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 54;
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
        if (BPREXRT.DAT_TXT.FWD_IND == '1' 
            && WS_FWD_TENR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPREXRT.DAT_TXT.FWD_IND == '0' 
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
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = WS_EXR_TYPE;
        BPREXRD.KEY.FWD_TENOR = WS_FWD_TENR;
        BPREXRD.KEY.CCY = WS_CCY;
        CEP.TRC(SCCGWA, BPREXRD.KEY);
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "NOT FOUND EXRD");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (WS_HIS_FLG == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (WS_HIS_FLG == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPRERGRO;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRERGRN;
        }
        if (WS_HIS_FLG == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF);
        BPCPNHIS.INFO.TX_RMK = "EXR RATE GEN RULE MAINTAIN";
        BPCPNHIS.INFO.FMT_ID = CPY_BPRERGR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (BPCDEGR.FUNC == 'X' 
            || BPCDEGR.FUNC == 'Y' 
            || BPCDEGR.FUNC == 'Z' 
            || BPCDEGR.FUNC == 'I') {
            SCCFMT.FMTID = K_OUTPUT_FMT_X;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT;
        }
        IBS.init(SCCGWA, BPCO277);
        BPCO277.CCY = BPCDEGR.CCY;
        BPCO277.EFF_DT = BPCDEGR.EFF_DT;
        BPCO277.EFF_TM = BPCDEGR.EFF_TM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCDEGR.RULE_DATA);
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
        CEP.TRC(SCCGWA, BPRERGR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRERGR.KEY.HDAY_FLG);
        CEP.TRC(SCCGWA, BPRERGR.KEY.SQN);
        BPCTERGM.INFO.POINTER = BPRERGR;
        BPCTERGM.INFO.LEN = 122;
        IBS.CALLCPN(SCCGWA, CPN_R_ERGR_M, BPCTERGM);
        if (BPCTERGM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTERGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRM, BPCTEXRM);
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
        CEP.TRC(SCCGWA, WS_ERR_MSG);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCDEGR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCDEGR = ");
            CEP.TRC(SCCGWA, BPCDEGR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
