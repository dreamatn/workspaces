package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2500 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP275";
    char K_STSW_FLG_X = '0';
    char K_STSW_FLG_Y = '1';
    char K_STSW_FLG_Z = '2';
    int K_MAX_CCY_CNT = 20;
    int K_LAMI_CCY = 1;
    int K_MAX_PAR_CNT = 12;
    char K_MOVE_STS_UNCONF = '1';
    char K_ATM_IN = '4';
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX         ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_S_CASH_CARRY = "BP-S-CASH-CARRY     ";
    String CPN_S_BOX_TO_FROM_LAT = "BP-S-BOX-TO-FROM-LAT";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    String K_HIS_REMARKS = "ATM-CS-MOV-OUT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_TEMP_CNT = 0;
    char WS_PLBOX_TYPE = ' ';
    int WS_SAVE_BR = 0;
    String WS_SAVE_PLBOX = " ";
    int WS_CCY = 0;
    BPOT2500_WS_ERR_INFO WS_ERR_INFO = new BPOT2500_WS_ERR_INFO();
    BPOT2500_WS_SUM_ATM_AMT[] WS_SUM_ATM_AMT = new BPOT2500_WS_SUM_ATM_AMT[999];
    BPOT2500_WS_CONF_TXT WS_CONF_TXT = new BPOT2500_WS_CONF_TXT();
    BPOT2500_WS_DATA_OUTPUT WS_DATA_OUTPUT = new BPOT2500_WS_DATA_OUTPUT();
    char WS_FND_PAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCSLAMI BPCSLAMI = new BPCSLAMI();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2530_AWA_2530 BPB2530_AWA_2530;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPOT2500() {
        for (int i=0;i<999;i++) WS_SUM_ATM_AMT[i] = new BPOT2500_WS_SUM_ATM_AMT();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2500 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2530_AWA_2530>");
        BPB2530_AWA_2530 = (BPB2530_AWA_2530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2530_AWA_2530);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.TLR);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].ATM);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[2-1].ATM);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[2-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[2-1].CCY_AMT);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            B040_CHECK_ATM_ONWAY_REC_CN();
        } else {
            B010_CHECK_INPUT();
        }
        B020_TRANSFER_CASH_TO_ATM();
        B030_HISTORY_RECORD();
        B050_TRANS_DATA_OUTPUT();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
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
        if (!BPB2530_AWA_2530.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_SAME_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.TLR;
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR112);
        }
        WS_PLBOX_TYPE = BPRTLVB.PLBOX_TP;
        WS_SAVE_BR = BPRTLVB.KEY.BR;
        WS_SAVE_PLBOX = BPRTLVB.KEY.PLBOX_NO;
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2530_AWA_2530.TLR;
        S000_CALL_BPZFTLRQ();
        if (WS_PLBOX_TYPE == '4') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR113);
        }
        if (WS_PLBOX_TYPE == '3' 
            || WS_PLBOX_TYPE == '6' 
            || WS_PLBOX_TYPE == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                WS_FLD_NO = BPB2530_AWA_2530.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_PLBOX_TYPE == '1' 
            || WS_PLBOX_TYPE == '2' 
            || WS_PLBOX_TYPE == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASHLIB_TLR);
            }
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Z+"") 
            || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
            WS_FLD_NO = BPB2530_AWA_2530.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() != 0; WS_CCY_CNT += 1) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC();
            }
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
                && !BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Z+"") 
                && !BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC();
            }
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRTLVB);
            IBS.init(SCCGWA, BPCTLVBF);
            BPRTLVB.PLBOX_TP = '4';
            BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_NOT_HAVE_ATMBOX;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC();
            }
            WS_CUR_CNT = WS_CCY_CNT + 1;
            for (WS_TEMP_CNT = WS_CUR_CNT; WS_TEMP_CNT <= K_MAX_CCY_CNT; WS_TEMP_CNT += 1) {
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].CCY) 
                    && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].CASH_TYP) 
                    && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].ATM)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                    WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].CCY_NO;
                    WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) WS_CCY = 0;
            else WS_CCY = Integer.parseInt(BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
            WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT + BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            IBS.init(SCCGWA, BPCPQBOX);
            BPRTLVB.PLBOX_TP = '4';
            BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.CCY_INFO[1-1].ATM;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_NOT_HAVE_ATMBOX;
                S000_ERR_MSG_PROC();
            }
            BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
            BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            BPCPQBOX.DATA_INFO.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCPQBOX.DATA_INFO.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            S000_CALL_BPZPQBOX();
            WS_TMP_AMT = BPCPQBOX.DATA_INFO.BAL + BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            if (WS_TMP_AMT > BPCPQBOX.DATA_INFO.LMT_U) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_EXCEED_LIMIT;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_TMP_AMT < BPCPQBOX.DATA_INFO.LMT_L) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_LOWER_LIMIT;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        for (WS_CCY = 1; WS_CCY <= 999; WS_CCY += 1) {
            if (WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY.trim().length() > 0 
                && WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT != 0) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY;
                S000_CALL_BPZQCCY();
                IBS.init(SCCGWA, BPCPQBOX);
                BPCPQBOX.DATA_INFO.VB_TLR = BPB2530_AWA_2530.TLR;
                BPCPQBOX.DATA_INFO.CCY = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY;
                BPCPQBOX.DATA_INFO.CASH_TYP = WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP;
                BPCPQBOX.DATA_INFO.VB_BR = WS_SAVE_BR;
                BPCPQBOX.DATA_INFO.PLBOX_NO = WS_SAVE_PLBOX;
                S000_CALL_BPZPQBOX();
                if (BPCPQBOX.DATA_INFO.BAL < WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2530_AWA_2530.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2530_AWA_2530.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
            WS_FLD_NO = BPB2530_AWA_2530.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2530_AWA_2530.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_TEMP_CNT = WS_CUR_CNT; WS_TEMP_CNT <= K_MAX_CCY_CNT; WS_TEMP_CNT += 1) {
                    if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].CCY) 
                        && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].CASH_TYP) 
                        && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].ATM)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_TEMP_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                IBS.init(SCCGWA, BPCPQBOX);
                IBS.init(SCCGWA, BPRTLVB);
                IBS.init(SCCGWA, BPCTLVBF);
                BPRTLVB.PLBOX_TP = '4';
                BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
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
                IBS.init(SCCGWA, BPCPQBOX);
                IBS.init(SCCGWA, BPRTLVB);
                IBS.init(SCCGWA, BPCTLVBF);
                BPRTLVB.PLBOX_TP = '3';
                BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.TLR;
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
            }
        }
    }
    public void B020_TRANSFER_CASH_TO_ATM() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
                CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
                CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                IBS.init(SCCGWA, BPCSLAMI);
                BPCSLAMI.FUNC = '0';
                BPCSLAMI.FROM_TLR = BPB2530_AWA_2530.TLR;
                BPCSLAMI.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCSLAMI.TO_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                BPCSLAMI.TO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCSLAMI.PLBOX_TYP = WS_PLBOX_TYPE;
                BPCSLAMI.CS_KIND = '0';
                BPCSLAMI.CCY_INFO[K_LAMI_CCY-1].CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSLAMI.CCY_INFO[K_LAMI_CCY-1].CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSLAMI.CCY_INFO[K_LAMI_CCY-1].CCY_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                S000_CALL_BPZSLAMI();
                WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_CONF_NO = BPCSLAMI.CONF_NO;
                WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_MOVE_DT = BPCSLAMI.MOVE_DT;
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                CEP.TRC(SCCGWA, WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_CONF_NO);
                CEP.TRC(SCCGWA, WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_MOVE_DT);
            }
        }
        BPB2530_AWA_2530.CONF_INF = IBS.CLS2CPY(SCCGWA, WS_CONF_TXT);
        for (WS_CCY = 1; WS_CCY <= 999; WS_CCY += 1) {
            if (WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY.trim().length() > 0 
                && WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT != 0) {
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPCHIS.PLBOX_NO = WS_SAVE_PLBOX;
                BPCPCHIS.CCY = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY;
                BPCPCHIS.CASH_TYP = WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP;
                BPCPCHIS.AMT = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT;
                if (WS_PLBOX_TYPE == '3' 
                    || WS_PLBOX_TYPE == '6') {
                    BPCPCHIS.VB_FLG = '0';
                } else {
                    if (WS_PLBOX_TYPE == '1' 
                        || WS_PLBOX_TYPE == '2' 
                        || WS_PLBOX_TYPE == '5') {
                        BPCPCHIS.VB_FLG = '1';
                    }
                }
                BPCPCHIS.IN_OUT = 'C';
                BPCPCHIS.CS_KIND = BPCSLAMI.CS_KIND;
                CEP.TRC(SCCGWA, BPCSLAMI.CONF_NO);
                BPCPCHIS.CONF_SEQ = BPCSLAMI.CONF_NO;
                S000_CALL_BPZPCHIS();
            }
        }
        BPB2530_AWA_2530.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2530_AWA_2530.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B030_GET_LONGTOU_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TLVB_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CASH_BOX;
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_CHECK_ATM_ONWAY_REC_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() > 0) {
                IBS.init(SCCGWA, BPCTMOVB);
                IBS.init(SCCGWA, BPRCMOV);
                BPRCMOV.KEY.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPRCMOV.KEY.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPRCMOV.MOV_TYP = K_ATM_IN;
                BPRCMOV.MOV_STS = K_MOVE_STS_UNCONF;
                BPRCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRCMOV.IN_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                BPCTMOVB.FUNC = 'C';
                BPCTMOVB.POINTER = BPRCMOV;
                BPCTMOVB.DATA_LEN = 228;
                S000_CALL_BPZTMOVB();
                BPCTMOVB.FUNC = 'R';
                BPCTMOVB.POINTER = BPRCMOV;
                BPCTMOVB.DATA_LEN = 228;
                S000_CALL_BPZTMOVB();
                if (BPCTMOVB.RETURN_INFO == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_HAD_CMOV_REC;
                    S000_ERR_MSG_PROC();
                }
                BPCTMOVB.FUNC = 'E';
                BPCTMOVB.POINTER = BPRCMOV;
                BPCTMOVB.DATA_LEN = 228;
                S000_CALL_BPZTMOVB();
            }
        }
    }
    public void B050_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        WS_DATA_OUTPUT.WS_OUT_FROM_TLR = BPB2530_AWA_2530.TLR;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() != 0; WS_CCY_CNT += 1) {
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_CS_TP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_TO_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_MV_DT = WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_MOVE_DT;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_CONF = WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_CONF_NO;
        }
        WS_DATA_OUTPUT.WS_OUT_DT_CNT = WS_CCY_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_DATA_OUTPUT;
        SCCFMT.DATA_LEN = 932067;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CASH_HIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
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
    public void S000_CALL_BPZSKPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_K_PSW_MAINTAIN, BPCFKPSW);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSLAMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BOX_TO_FROM_LAT, BPCSLAMI);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
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
