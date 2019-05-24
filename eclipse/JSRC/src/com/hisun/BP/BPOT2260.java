package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2260 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP129";
    int K_MAX_PAR_CNT = 12;
    int K_PBOX_CNT = 20;
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_S_CASHEX_MAINTAIN = "BP-S-CASHEX-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT_I = 0;
    double WS_TMP_AMT_O = 0;
    int WS_PAR_CNT = 0;
    int WS_PB_CNT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSCASH BPCSCASH = new BPCSCASH();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    SCCGWA SCCGWA;
    BPB2260_AWA_2260 BPB2260_AWA_2260;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2260 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2260_AWA_2260>");
        BPB2260_AWA_2260 = (BPB2260_AWA_2260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_TLR_INFO();
        B020_CHECK_PVAL_INPUT();
        B030_CASH_EXCHANGE();
    }
    public void B010_CHECK_TLR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPB2260_AWA_2260.PLBOX_TP == '1' 
            || BPB2260_AWA_2260.PLBOX_TP == '2' 
            || BPB2260_AWA_2260.PLBOX_TP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB2260_AWA_2260.PLBOX_TP == '3' 
            || BPB2260_AWA_2260.PLBOX_TP == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_PVAL_INPUT() throws IOException,SQLException,Exception {
        if (BPB2260_AWA_2260.EXCH_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXCH_AMT_IS_ZERO;
            WS_FLD_NO = BPB2260_AWA_2260.EXCH_AMT_NO;
            S000_ERR_MSG_PROC();
        }
        WS_TMP_AMT_I = 0;
        WS_CS_KIND = BPB2260_AWA_2260.IN_CS_KD;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT 
            && BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_PVAL != 0; WS_PAR_CNT += 1) {
            if (BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_NUM > 0) {
                if ((WS_CS_KIND == '0' 
                        || WS_CS_KIND == '2')) {
                    WS_TMP_AMT_I = WS_TMP_AMT_I + BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_PVAL * BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_NUM;
                } else if (WS_CS_KIND == '3') {
                    WS_TMP_AMT_I = WS_TMP_AMT_I + BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_PVAL * BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_NUM / 2;
                } else {
                }
            }
        }
        if (WS_TMP_AMT_I != BPB2260_AWA_2260.EXCH_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IN_PVAL_NOTMATCH_AMT;
            WS_FLD_NO = BPB2260_AWA_2260.EXCH_AMT_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = BPB2260_AWA_2260.PLBOX_TP;
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TLVB_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            if (BPB2260_AWA_2260.PLBOX_TP == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_F_LIB;
            }
            if (BPB2260_AWA_2260.PLBOX_TP == '2') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_Z_LIB;
            }
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2260_AWA_2260.OT_CS_TP;
        BPCPQBOX.DATA_INFO.CCY = BPB2260_AWA_2260.CCY;
        S000_CALL_BPZPQBOX();
        WS_TMP_AMT_O = 0;
        WS_CS_KIND = BPB2260_AWA_2260.OT_CS_KD;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT 
            && BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL != 0; WS_PAR_CNT += 1) {
            if (BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM > 0) {
                WS_MATCH_FLAG = 'F';
                for (WS_PB_CNT = 1; WS_PB_CNT <= K_PBOX_CNT 
                    && WS_MATCH_FLAG != 'T'; WS_PB_CNT += 1) {
                    B050_MATCH_CALC_OUT_PVAL();
                }
                if (WS_MATCH_FLAG == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BOX_NO_THIS_PVAL;
                    if (BPB2260_AWA_2260.PLBOX_TP == '1' 
                        || BPB2260_AWA_2260.PLBOX_TP == '2') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    }
                    WS_FLD_NO = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (WS_TMP_AMT_O != BPB2260_AWA_2260.EXCH_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OT_PVAL_NOTMATCH_AMT;
            WS_FLD_NO = BPB2260_AWA_2260.EXCH_AMT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CASH_EXCHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCASH);
        BPCSCASH.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSCASH();
    }
    public void B050_MATCH_CALC_OUT_PVAL() throws IOException,SQLException,Exception {
        if (BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL == BPCPQBOX.DATA_INFO.CLBI_INF[WS_PB_CNT-1].PAR_VAL 
            && BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_M_FLG == BPCPQBOX.DATA_INFO.CLBI_INF[WS_PB_CNT-1].M_FLG) {
            WS_MATCH_FLAG = 'T';
            if (WS_CS_KIND == '0') {
                if (BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM > BPCPQBOX.DATA_INFO.CLBI_INF[WS_PB_CNT-1].GD_NUM) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                    WS_FLD_NO = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM_NO;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_TMP_AMT_O = WS_TMP_AMT_O + BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL * BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM;
                }
            } else if (WS_CS_KIND == '2') {
                if (BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM > BPCPQBOX.DATA_INFO.CLBI_INF[WS_PB_CNT-1].BD_NUM) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                    WS_FLD_NO = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM_NO;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_TMP_AMT_O = WS_TMP_AMT_O + BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL * BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM;
                }
            } else if (WS_CS_KIND == '3') {
                if (BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM > BPCPQBOX.DATA_INFO.CLBI_INF[WS_PB_CNT-1].HBD_NUM) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                    WS_FLD_NO = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM_NO;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_TMP_AMT_O = WS_TMP_AMT_O + BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL * BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM / 2;
                }
            } else {
            }
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPB2260_AWA_2260.PLBOX_TP == '1' 
            || BPB2260_AWA_2260.PLBOX_TP == '2' 
            || BPB2260_AWA_2260.PLBOX_TP == '5') {
            BPCSCASH.VB_FLAG = '0';
        } else {
            BPCSCASH.VB_FLAG = '1';
        }
        BPCSCASH.PLBOX_TYPE = BPB2260_AWA_2260.PLBOX_TP;
        BPCSCASH.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSCASH.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSCASH.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSCASH.CCY = BPB2260_AWA_2260.CCY;
        BPCSCASH.EXCH_AMT = BPB2260_AWA_2260.EXCH_AMT;
        BPCSCASH.IN_CASH_TYPE = BPB2260_AWA_2260.IN_CS_TP;
        BPCSCASH.IN_CS_KIND = BPB2260_AWA_2260.IN_CS_KD;
        BPCSCASH.OUT_CASH_TYPE = BPB2260_AWA_2260.OT_CS_TP;
        BPCSCASH.OUT_CS_KIND = BPB2260_AWA_2260.OT_CS_KD;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            BPCSCASH.IN_PVAL_INFO[WS_PAR_CNT-1].IN_PVAL = BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_PVAL;
            BPCSCASH.IN_PVAL_INFO[WS_PAR_CNT-1].IN_NUM = BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_NUM;
            BPCSCASH.IN_PVAL_INFO[WS_PAR_CNT-1].IN_M_FLG = BPB2260_AWA_2260.EXIN_PAR[WS_PAR_CNT-1].IN_M_FLG;
            BPCSCASH.OUT_PVAL_INFO[WS_PAR_CNT-1].OUT_PVAL = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_PVAL;
            BPCSCASH.OUT_PVAL_INFO[WS_PAR_CNT-1].OUT_NUM = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_NUM;
            BPCSCASH.OUT_PVAL_INFO[WS_PAR_CNT-1].OUT_M_FLG = BPB2260_AWA_2260.EXOT_PAR[WS_PAR_CNT-1].OT_M_FLG;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSCASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_CASHEX_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSCASH;
        SCCCALL.ERR_FLDNO = BPB2260_AWA_2260.PLBOX_TP_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSCASH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSCASH.RC);
            WS_FLD_NO = BPB2260_AWA_2260.PLBOX_TP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB2260_AWA_2260.PLBOX_TP_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB2260_AWA_2260.PLBOX_TP_NO;
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
