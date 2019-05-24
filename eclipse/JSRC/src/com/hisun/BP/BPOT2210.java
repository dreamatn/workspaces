package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2210 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTTLVB_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP120";
    char K_STSW_FLG_Y = '1';
    char K_LONGTOU_BOX_FLAG = '5';
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_S_CASH_CARRY = "BP-S-CASH-CARRY     ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String K_HIS_REMARKS = "BIND THE BOX TO TELLER";
    String K_HIS_REMARKS1 = "EXTEND CASH TO  CASHBOX";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_CNT = 0;
    char WS_SAVE_PLBOX_TYP = ' ';
    int WS_SAVE_RCV_BOX_BR = 0;
    int WS_SAVE_TR_LIB_BR = 0;
    char WS_PLBOX_TP = ' ';
    char WS_FND_PAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCSDNCS BPCSDNCS = new BPCSDNCS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2210_AWA_2210 BPB2210_AWA_2210;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2210 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2210_AWA_2210>");
        BPB2210_AWA_2210 = (BPB2210_AWA_2210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2210_AWA_2210);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PLBOX_NO);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.RCV_TLR);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.LAST_TLR);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[1-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[1-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[1-1].M_FLG);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[2-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[2-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[2-1].M_FLG);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[2-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[2-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[13-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[13-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[13-1].M_FLG);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[14-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[14-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PAR_INFO[14-1].M_FLG);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPB2210_AWA_2210.TR_FUNC == '1') {
                B010_CHECK_BOX_INPUT_FOR_CN();
                if (pgmRtn) return;
                B040_CHECK_BOX_TLR_FOR_CN();
                if (pgmRtn) return;
                B020_CHECK_TLR_INPUT_FOR_CN();
                if (pgmRtn) return;
            }
            if (BPB2210_AWA_2210.TR_FUNC == '2') {
                B020_CHECK_TLR_INPUT_FOR_CN();
                if (pgmRtn) return;
                B050_CHECK_CASH_TLR_FOR_CN();
                if (pgmRtn) return;
                B030_CHECK_CCY_INFO_FOR_CN();
                if (pgmRtn) return;
            }
            B020_EXTEND_CASH_FOR_CN();
            if (pgmRtn) return;
            B030_HISTORY_RECORD();
            if (pgmRtn) return;
        } else {
            if (BPB2210_AWA_2210.TR_FUNC == '1') {
                B010_CHECK_BOX_INPUT();
                if (pgmRtn) return;
                B020_CHECK_TLR_INPUT();
                if (pgmRtn) return;
            }
            if (BPB2210_AWA_2210.TR_FUNC == '2') {
                B020_CHECK_TLR_INPUT();
                if (pgmRtn) return;
                B030_CHECK_CCY_INFO();
                if (pgmRtn) return;
            }
            B020_EXTEND_CASH();
            if (pgmRtn) return;
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPB2210_AWA_2210.TR_FUNC == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P904";
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        } else {
            if (BPB2210_AWA_2210.TR_FUNC == '2') {
                BPCPNHIS.INFO.TX_TYP_CD = "P906";
                BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS1;
            }
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_CHECK_BOX_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.PLBOX_NO = BPB2210_AWA_2210.PLBOX_NO;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        BPCTLVBF.INFO.FUNC = 'Q';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
            WS_FLD_NO = BPB2210_AWA_2210.PLBOX_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLVB.CRNT_TLR.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED;
            WS_FLD_NO = BPB2210_AWA_2210.PLBOX_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!BPB2210_AWA_2210.RCV_TLR.equalsIgnoreCase(BPRTLVB.LAST_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_TLR_WRG;
            WS_FLD_NO = BPB2210_AWA_2210.LAST_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_TLR_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.PLBOX_TP = ALL.charAt(0);
        BPRTLVB.CRNT_TLR = BPB2210_AWA_2210.RCV_TLR;
        BPCRTLVB.INFO.FUNC = 'T';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2' 
                || BPRTLVB.PLBOX_TP == '4') {
            } else {
                if (BPB2210_AWA_2210.TR_FUNC == '1' 
                    && BPRTLVB.RLTD_FLG == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_PLBOX_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPB2210_AWA_2210.TR_FUNC == '2' 
                    && BPRTLVB.RLTD_FLG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2210_AWA_2210.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB2210_AWA_2210.RCV_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IMPOWER;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_CNT = WS_CUR_CNT; WS_CNT <= K_MAX_CCY_CNT; WS_CNT += 1) {
                    if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2210_AWA_2210.CCY_INFO[WS_CNT-1].CCY) 
                        && BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2210_AWA_2210.CCY_INFO[WS_CNT-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2210_AWA_2210.CCY_INFO[WS_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
                WS_TMP_AMT = 0;
                WS_PAR_CNT = 1;
                WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                while (WS_PAR_CNT <= K_MAX_PAR_CNT 
                    && BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_VAL > 0 
                    && BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM > -1) {
                    WS_TMP_AMT = WS_TMP_AMT + BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_VAL * BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                    WS_PAR_CNT = WS_PAR_CNT + 1;
                    WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                }
                if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != WS_TMP_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
                    WS_FLD_NO = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCPQBOX);
                IBS.init(SCCGWA, BPRTLVB);
                BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
                BPCRTLVB.INFO.FUNC = 'Q';
                BPCRTLVB.INFO.LEN = 187;
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                BPCRTLVB.INFO.FUNC = 'N';
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                WS_CNT = 0;
                while (BPCRTLVB.RETURN_INFO != 'N' 
                    && WS_CNT <= 1000) {
                    WS_CNT += 1;
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2' 
                        || BPRTLVB.PLBOX_TP == '5') {
                        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                        WS_CNT = 1001;
                        if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                    BPCRTLVB.INFO.FUNC = 'N';
                    BPCRTLVB.INFO.POINTER = BPRTLVB;
                    BPCRTLVB.INFO.LEN = 187;
                    S000_CALL_BPZRTLVB();
                    if (pgmRtn) return;
                }
                BPCRTLVB.INFO.FUNC = 'E';
                BPCRTLVB.INFO.LEN = 187;
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                if (BPCRTLVB.RETURN_INFO != 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPQBOX.DATA_INFO.CCY = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPQBOX.DATA_INFO.CASH_TYP = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                CEP.TRC(SCCGWA, BPCPQBOX);
                S000_CALL_BPZPQBOX();
                if (pgmRtn) return;
                if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLIB_MGR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_PAR_CNT = 1;
                WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                while (WS_PAR_CNT <= K_MAX_PAR_CNT 
                    && BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_VAL > 0) {
                    if (BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM > 0) {
                        WS_FND_PAR_FLG = 'N';
                        for (WS_CNT = 1; WS_CNT <= BPCPQBOX.DATA_INFO.CNT 
                            && WS_FND_PAR_FLG != 'Y'; WS_CNT += 1) {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].PAR_VAL == BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_VAL 
                                && BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].M_FLG == BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].M_FLG) {
                                WS_FND_PAR_FLG = 'Y';
                                if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_CNT-1].GD_NUM < BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM) {
                                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                    WS_FLD_NO = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM_NO;
                                    S000_ERR_MSG_PROC();
                                    if (pgmRtn) return;
                                }
                            }
                        }
                    }
                    WS_PAR_CNT = WS_PAR_CNT + 1;
                    WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                }
            }
        }
    }
    public void B010_CHECK_BOX_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.PLBOX_NO = BPB2210_AWA_2210.PLBOX_NO;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCTLVBF.INFO.FUNC = 'Q';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
            WS_FLD_NO = BPB2210_AWA_2210.PLBOX_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLVB.PLBOX_TP != '6') {
            if (BPRTLVB.CRNT_TLR.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED;
                WS_FLD_NO = BPB2210_AWA_2210.PLBOX_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPRTLVB.CRNT_TLR.trim().length() > 0 
                && BPRTLVB.CRNT_TLR1.trim().length() > 0 
                && BPRTLVB.CRNT_TLR2.trim().length() > 0 
                && BPRTLVB.CRNT_TLR3.trim().length() > 0 
                && BPRTLVB.CRNT_TLR4.trim().length() > 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED);
            }
        }
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_EXTEND_CASH_LIB;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLVB.PLBOX_TP == '4') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANT_USE_ATM_BOX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_SAVE_PLBOX_TYP = BPRTLVB.PLBOX_TP;
        if (BPRTLVB.PLBOX_TP != '6') {
            if (BPB2210_AWA_2210.RCV_TLR.equalsIgnoreCase(BPRTLVB.LAST_TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_TLR_SAME_WRG;
                WS_FLD_NO = BPB2210_AWA_2210.LAST_TLR_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CHECK_TLR_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_READ_BPTTLVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR122);
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2210_AWA_2210.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.RCV_TLR);
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCV_TLR_BR_ERR;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_SAVE_PLBOX_TYP == K_LONGTOU_BOX_FLAG) {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB2210_AWA_2210.RCV_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
            && BPB2210_AWA_2210.TR_FUNC == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SELF_EXTD_MUST_IMPOW;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB2210_AWA_2210.RCV_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
            && BPB2210_AWA_2210.TR_FUNC == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SELF_CASH_MUST_IMPOW;
            WS_FLD_NO = BPB2210_AWA_2210.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND ( CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        BPTTLVB_RD.upd = true;
        BPTTLVB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR123);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_CCY_INFO_FOR_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_CNT = WS_CUR_CNT; WS_CNT <= K_MAX_CCY_CNT; WS_CNT += 1) {
                    if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2210_AWA_2210.CCY_INFO[WS_CNT-1].CCY) 
                        && BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2210_AWA_2210.CCY_INFO[WS_CNT-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2210_AWA_2210.CCY_INFO[WS_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, BPCPQBOX);
                IBS.init(SCCGWA, BPRTLVB);
                BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
                BPCRTLVB.INFO.FUNC = 'Q';
                BPCRTLVB.INFO.LEN = 187;
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                BPCRTLVB.INFO.FUNC = 'N';
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                WS_CNT = 0;
                while (BPCRTLVB.RETURN_INFO != 'N' 
                    && WS_CNT <= 1000) {
                    WS_CNT += 1;
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2' 
                        || BPRTLVB.PLBOX_TP == '5') {
                        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                        WS_CNT = 1001;
                        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
                        if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_HAV_CASHLIB;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        } else {
                            WS_SAVE_TR_LIB_BR = BPRTLVB.KEY.BR;
                        }
                    }
                    BPCRTLVB.INFO.FUNC = 'N';
                    BPCRTLVB.INFO.POINTER = BPRTLVB;
                    BPCRTLVB.INFO.LEN = 187;
                    S000_CALL_BPZRTLVB();
                    if (pgmRtn) return;
                }
                BPCRTLVB.INFO.FUNC = 'E';
                BPCRTLVB.INFO.LEN = 187;
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                if (BPCRTLVB.RETURN_INFO != 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPQBOX.DATA_INFO.CCY = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPQBOX.DATA_INFO.CASH_TYP = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                CEP.TRC(SCCGWA, BPCPQBOX);
                S000_CALL_BPZPQBOX();
                if (pgmRtn) return;
                if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLIB_MGR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_SAVE_RCV_BOX_BR);
                CEP.TRC(SCCGWA, WS_SAVE_TR_LIB_BR);
                if (WS_SAVE_RCV_BOX_BR != WS_SAVE_TR_LIB_BR) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_IN_BR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BAL);
                CEP.TRC(SCCGWA, BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                if (BPCPQBOX.DATA_INFO.BAL < BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_AMT) {
                    CEP.TRC(SCCGWA, "DEV");
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
                }
            }
        }
    }
    public void B020_EXTEND_CASH_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDNCS);
        BPCSDNCS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSDNCS.PLBOX_NO = BPB2210_AWA_2210.PLBOX_NO;
        BPCSDNCS.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.FROM_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSDNCS.TO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.TO_TLR = BPB2210_AWA_2210.RCV_TLR;
        if (BPB2210_AWA_2210.TR_FUNC == '1') {
            BPCSDNCS.FUNC = '4';
        }
        if (BPB2210_AWA_2210.TR_FUNC == '2') {
            BPCSDNCS.PLBOX_TP2 = WS_PLBOX_TP;
            BPCSDNCS.FUNC = '0';
            BPCSDNCS.CS_KIND = '0';
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
                if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                    && BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                    for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                        WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                        BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_VAL;
                        BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                        BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].M_FLG;
                    }
                }
            }
        }
        S000_CALL_BPZSDNCS();
        if (pgmRtn) return;
    }
    public void B020_EXTEND_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDNCS);
        BPCSDNCS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSDNCS.PLBOX_NO = BPB2210_AWA_2210.PLBOX_NO;
        BPCSDNCS.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.FROM_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSDNCS.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.TO_TLR = BPB2210_AWA_2210.RCV_TLR;
        if (BPB2210_AWA_2210.TR_FUNC == '1') {
            BPCSDNCS.FUNC = '4';
        }
        if (BPB2210_AWA_2210.TR_FUNC == '2') {
            BPCSDNCS.FUNC = '0';
            BPCSDNCS.CS_KIND = '0';
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
                if (BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                    && BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                    for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                        WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                        BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_VAL;
                        BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                        BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG = BPB2210_AWA_2210.PAR_INFO[WS_CUR_CNT-1].M_FLG;
                    }
                }
            }
        }
        S000_CALL_BPZSDNCS();
        if (pgmRtn) return;
    }
    public void B040_CHECK_BOX_TLR_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.CRNT_TLR = BPB2210_AWA_2210.RCV_TLR;
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_PLBOX_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_CHECK_CASH_TLR_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.CRNT_TLR = BPB2210_AWA_2210.RCV_TLR;
        CEP.TRC(SCCGWA, BPB2210_AWA_2210.RCV_TLR);
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REV_TLR_NOT_CASHBOX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPRTLVB.PLBOX_TP != '3' 
                && BPRTLVB.PLBOX_TP != '6') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR76);
            } else {
                WS_SAVE_RCV_BOX_BR = BPRTLVB.KEY.BR;
                WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
            }
        }
    }
    public void B060_GET_LONGTOU_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = BPB2210_AWA_2210.RCV_TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_PLBOX_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BOX, BPCPQBOX);
        CEP.TRC(SCCGWA, BPCPQBOX.RC.RC_CODE);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSKPSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_K_PSW_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCFKPSW;
        SCCCALL.ERR_FLDNO = BPB2210_AWA_2210.TLR_PSW;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            WS_FLD_NO = BPB2210_AWA_2210.TLR_PSW_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2210_AWA_2210.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSDNCS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASH_CARRY, BPCSDNCS);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
