package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2230 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP120";
    char K_STSW_FLG_Y = '1';
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_S_CASH_CARRY = "BP-S-CASH-CARRY     ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String K_HIS_REMARKS = "CASH FROM BOX TO BOX";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_CNT = 0;
    String WS_SAVE_PLBOX_NO = " ";
    char WS_PLBOX_TP1 = ' ';
    char WS_PLBOX_TP2 = ' ';
    char WS_FND_PAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCSDNCS BPCSDNCS = new BPCSDNCS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2230_AWA_2230 BPB2230_AWA_2230;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2230_AWA_2230>");
        BPB2230_AWA_2230 = (BPB2230_AWA_2230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            B020_TRANSFER_CASH_FOR_CN();
            B030_HISTORY_RECORD();
        } else {
            B010_CHECK_INPUT();
            B020_TRANSFER_CASH();
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P906";
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2230_AWA_2230.RCV_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_HANDOVER_SELF;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2230_AWA_2230.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCV_TLR_BR_ERR;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_CNT = WS_CUR_CNT; WS_CNT <= K_MAX_CCY_CNT; WS_CNT += 1) {
                    if (BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2230_AWA_2230.CCY_INFO[WS_CNT-1].CCY) 
                        && BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2230_AWA_2230.CCY_INFO[WS_CNT-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2230_AWA_2230.CCY_INFO[WS_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                IBS.init(SCCGWA, BPRTLVB);
                IBS.init(SCCGWA, BPCTLVBF);
                BPRTLVB.CRNT_TLR = BPB2230_AWA_2230.RCV_TLR;
                BPCTLVBF.INFO.FUNC = 'T';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (BPCTLVBF.RETURN_INFO == 'N' 
                    || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND);
                } else {
                    if (BPRTLVB.PLBOX_TP != '3' 
                        && BPRTLVB.PLBOX_TP != '6') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR76);
                    } else {
                        WS_PLBOX_TP2 = BPRTLVB.PLBOX_TP;
                        WS_SAVE_PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    }
                }
                IBS.init(SCCGWA, BPRTLVB);
                IBS.init(SCCGWA, BPCTLVBF);
                BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTLVBF.INFO.FUNC = 'T';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (BPCTLVBF.RETURN_INFO == 'N' 
                    || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND);
                } else {
                    if (BPRTLVB.PLBOX_TP != '3' 
                        && BPRTLVB.PLBOX_TP != '6') {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR100);
                    } else {
                        WS_PLBOX_TP1 = BPRTLVB.PLBOX_TP;
                        WS_SAVE_PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    }
                }
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2230_AWA_2230.RCV_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_HANDOVER_SELF;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2230_AWA_2230.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            WS_FLD_NO = BPB2230_AWA_2230.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_CNT = WS_CUR_CNT; WS_CNT <= K_MAX_CCY_CNT; WS_CNT += 1) {
                    if (BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2230_AWA_2230.CCY_INFO[WS_CNT-1].CCY) 
                        && BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2230_AWA_2230.CCY_INFO[WS_CNT-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2230_AWA_2230.CCY_INFO[WS_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                IBS.init(SCCGWA, BPCPQBOX);
                IBS.init(SCCGWA, BPRTLVB);
                IBS.init(SCCGWA, BPCTLVBF);
                BPRTLVB.PLBOX_TP = '3';
                BPRTLVB.CRNT_TLR = BPB2230_AWA_2230.RCV_TLR;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                    S000_ERR_MSG_PROC();
                }
                WS_SAVE_PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            }
        }
    }
    public void B020_TRANSFER_CASH_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDNCS);
        BPCSDNCS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSDNCS.FUNC = '3';
        BPCSDNCS.PLBOX_NO = WS_SAVE_PLBOX_NO;
        BPCSDNCS.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.FROM_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSDNCS.TO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.TO_TLR = BPB2230_AWA_2230.RCV_TLR;
        BPCSDNCS.CS_KIND = BPB2230_AWA_2230.CS_KIND;
        BPCSDNCS.PLBOX_TP2 = WS_PLBOX_TP2;
        BPCSDNCS.PLBOX_TP1 = WS_PLBOX_TP1;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL = BPB2230_AWA_2230.PAR_INFO[WS_CUR_CNT-1].PAR_VAL;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM = BPB2230_AWA_2230.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG = BPB2230_AWA_2230.PAR_INFO[WS_CUR_CNT-1].M_FLG;
                }
            }
        }
        S000_CALL_BPZSDNCS();
    }
    public void B020_TRANSFER_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDNCS);
        BPCSDNCS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSDNCS.FUNC = '3';
        BPCSDNCS.PLBOX_NO = WS_SAVE_PLBOX_NO;
        BPCSDNCS.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.FROM_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSDNCS.TO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSDNCS.TO_TLR = BPB2230_AWA_2230.RCV_TLR;
        BPCSDNCS.CS_KIND = BPB2230_AWA_2230.CS_KIND;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            }
        }
        S000_CALL_BPZSDNCS();
    }
    public void B030_GET_LONGTOU_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = BPB2230_AWA_2230.RCV_TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N' 
            || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSKPSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_K_PSW_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCFKPSW;
        SCCCALL.ERR_FLDNO = BPB2230_AWA_2230.TLR_PSW;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            WS_FLD_NO = BPB2230_AWA_2230.TLR_PSW_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2230_AWA_2230.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
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
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
