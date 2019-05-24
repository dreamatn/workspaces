package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2400 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP140";
    char K_STSW_FLG_Y = '1';
    char K_STSW_FLG_Z = '2';
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX         ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_S_CASH_CARRY = "BP-S-CASH-CARRY     ";
    String CPN_S_BOX_TO_FROM_ATM = "BP-S-BOX-TO-FROM-ATM";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_TEMP_CNT = 0;
    char WS_FND_PAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCSATMI BPCSATMI = new BPCSATMI();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPB2400_AWA_2400 BPB2400_AWA_2400;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2400_AWA_2400>");
        BPB2400_AWA_2400 = (BPB2400_AWA_2400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2400_AWA_2400);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.TLR);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.ATM);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.PAR_INFO[1-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.PAR_INFO[1-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2400_AWA_2400.PAR_INFO[1-1].M_FLG);
        B010_CHECK_INPUT();
        B020_TRANSFER_CASH_TO_ATM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2400_AWA_2400.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2400_AWA_2400.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Z+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
            WS_FLD_NO = BPB2400_AWA_2400.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2400_AWA_2400.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2400_AWA_2400.ATM;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2400_AWA_2400.ATM_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
            WS_FLD_NO = BPB2400_AWA_2400.ATM_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2400_AWA_2400.ATM_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.SIGN_STS == 'O') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_OFFICIAL_SIGN_OF;
            WS_FLD_NO = BPB2400_AWA_2400.ATM_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00 
                && (!IBS.isNumeric(BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY))) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_TEMP_CNT = WS_CUR_CNT; WS_TEMP_CNT <= K_MAX_CCY_CNT; WS_TEMP_CNT += 1) {
                    if (BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2400_AWA_2400.CCY_INFO[WS_TEMP_CNT-1].CCY)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2400_AWA_2400.CCY_INFO[WS_TEMP_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                WS_TMP_AMT = 0;
                WS_PAR_CNT = 1;
                WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                while (WS_PAR_CNT <= K_MAX_PAR_CNT 
                    && BPB2400_AWA_2400.PAR_INFO[WS_CUR_CNT-1].PAR_VAL > 0 
                    && BPB2400_AWA_2400.PAR_INFO[WS_CUR_CNT-1].PAR_NUM > 0) {
                    WS_TMP_AMT = WS_TMP_AMT + BPB2400_AWA_2400.PAR_INFO[WS_CUR_CNT-1].PAR_VAL * BPB2400_AWA_2400.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                    WS_PAR_CNT = WS_PAR_CNT + 1;
                    WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                }
                if (BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != WS_TMP_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
                    WS_FLD_NO = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPCPQBOX);
                IBS.init(SCCGWA, BPRTLVB);
                BPRTLVB.PLBOX_TP = '4';
                BPRTLVB.CRNT_TLR = BPB2400_AWA_2400.ATM;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
                CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                    S000_ERR_MSG_PROC();
                }
                BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPQBOX.DATA_INFO.CASH_TYP = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPQBOX.DATA_INFO.CCY = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZPQBOX();
                if (BPCPQBOX.DATA_INFO.STS == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                    WS_FLD_NO = BPB2400_AWA_2400.ATM_NO;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPCPQBOX);
                BPRTLVB.PLBOX_TP = '3';
                BPRTLVB.CRNT_TLR = BPB2400_AWA_2400.TLR;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
                CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                    S000_ERR_MSG_PROC();
                }
                BPCPQBOX.DATA_INFO.VB_TLR = BPB2400_AWA_2400.TLR;
                BPCPQBOX.DATA_INFO.CASH_TYP = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPQBOX.DATA_INFO.CCY = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZPQBOX();
                if (BPCPQBOX.DATA_INFO.STS == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                    WS_FLD_NO = BPB2400_AWA_2400.TLR_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B020_TRANSFER_CASH_TO_ATM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSATMI);
        BPCSATMI.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSATMI.FUNC = '0';
        BPCSATMI.FROM_TLR = BPB2400_AWA_2400.TLR;
        BPCSATMI.FROM_TYP = '3';
        BPCSATMI.TO_TLR = BPB2400_AWA_2400.ATM;
        BPCSATMI.TO_TYP = '4';
        BPCSATMI.CS_KIND = '0';
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, BPB2400_AWA_2400.CCY_INFO[1-1].CCY);
            if (BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                CEP.TRC(SCCGWA, BPCSATMI.CCY_INFO[1-1].CASH_TYP);
                CEP.TRC(SCCGWA, BPCSATMI.CCY_INFO[1-1].CCY);
                CEP.TRC(SCCGWA, BPCSATMI.CCY_INFO[1-1].CCY_AMT);
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT 
                    && (BPB2400_AWA_2400.PAR_INFO[WS_PAR_CNT-1].PAR_NUM != 0); WS_PAR_CNT += 1) {
                    WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                    BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL = BPB2400_AWA_2400.PAR_INFO[WS_CUR_CNT-1].PAR_VAL;
                    BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM = BPB2400_AWA_2400.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                    BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG = '0';
                }
            }
        }
        S000_CALL_BPZSATMI();
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BOX, BPCPQBOX);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
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
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2400_AWA_2400.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSATMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BOX_TO_FROM_ATM, BPCSATMI);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
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
