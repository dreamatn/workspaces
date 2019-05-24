package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2520 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP277";
    char K_STSW_FLG_Y = '1';
    char K_STSW_FLG_Z = '2';
    char K_ATM_OUT = '5';
    char K_MOVE_STS_UNCONF = '1';
    char K_CASH_OUT = 'C';
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX         ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_S_CASH_CARRY = "BP-S-CASH-CARRY     ";
    String CPN_S_LAT_TO_FROM_BOX = "BP-S-LAT-TO-FROM-BOX";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_ADW_CMOV = "BP-R-ADW-CMOV       ";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String CPN_R_MAINT_CHIS = "BP-R-ADW-CHIS";
    String CPN_R_BRW_CHIS = "BP-R-BRW-CHIS ";
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_TEMP_CNT = 0;
    int WS_INDEX = 0;
    int WS_INFO_CNT = 0;
    BPOT2520_WS_SUSP_INFO[] WS_SUSP_INFO = new BPOT2520_WS_SUSP_INFO[5];
    BPOT2520_WS_SAVE_CMOV_INFO[] WS_SAVE_CMOV_INFO = new BPOT2520_WS_SAVE_CMOV_INFO[5];
    char WS_FND_PAR_FLG = ' ';
    char WS_AMT_OUT_ONWAY_FLG = ' ';
    char WS_ATM_OUT_CHIS_FLG = ' ';
    char WS_QBOX_BAL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCSLAMO BPCSLAMO = new BPCSLAMO();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPCRMOVF BPCRMOVF = new BPCRMOVF();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPCRCHIS BPCRCHIS = new BPCRCHIS();
    BPCTCHIB BPCTCHIB = new BPCTCHIB();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCOLAMO BPCOLAMO = new BPCOLAMO();
    SCCGWA SCCGWA;
    BPB2500_AWA_2500 BPB2500_AWA_2500;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public BPOT2520() {
        for (int i=0;i<5;i++) WS_SUSP_INFO[i] = new BPOT2520_WS_SUSP_INFO();
        for (int i=0;i<5;i++) WS_SAVE_CMOV_INFO[i] = new BPOT2520_WS_SAVE_CMOV_INFO();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2520 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2500_AWA_2500>");
        BPB2500_AWA_2500 = (BPB2500_AWA_2500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2500_AWA_2500);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.TLR);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.ATM);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[1-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[1-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[2-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[2-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[2-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[2-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[13-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[13-1].PAR_NUM);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[14-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.PAR_INFO[14-1].PAR_NUM);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
            if (SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
                BPB2500_AWA_2500.ATM = SCCGWA.COMM_AREA.TL_ID;
            }
            B030_GET_ATM_CASHBOX_CN();
            if (pgmRtn) return;
            B040_CHECK_ONWAY_CN();
            if (pgmRtn) return;
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            if (WS_AMT_OUT_ONWAY_FLG == 'Y') {
                if (WS_QBOX_BAL_FLG == 'Y') {
                    B050_RECALL_ONWAY_REC_CN();
                    if (pgmRtn) return;
                    B020_ATM_CASH_TO_TRANS_FOR_CN();
                    if (pgmRtn) return;
                } else {
                    B070_TRANS_DATA_OUTPUT();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                B020_ATM_CASH_TO_TRANS_FOR_CN();
                if (pgmRtn) return;
            }
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_ATM_CASH_TO_ATMTRANSFER();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2500_AWA_2500.ATM;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_TEMP_CNT = WS_CUR_CNT; WS_TEMP_CNT <= K_MAX_CCY_CNT; WS_TEMP_CNT += 1) {
                    if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_TEMP_CNT-1].CCY) 
                        && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_TEMP_CNT-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_TEMP_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCPQBOX);
                BPRTLVB.PLBOX_TP = '4';
                BPRTLVB.CRNT_TLR = BPB2500_AWA_2500.ATM;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (pgmRtn) return;
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
                CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
                BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPQBOX.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPQBOX.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZPQBOX();
                if (pgmRtn) return;
                WS_TMP_AMT = BPCPQBOX.DATA_INFO.BAL - BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT = BPCPQBOX.DATA_INFO.BAL - BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            }
        }
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CCY);
                    if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        CEP.TRC(SCCGWA, "333333333");
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "11");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "11111111111");
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "22");
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "33");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BPB2500_AWA_2500.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPB2500_AWA_2500.CCY_INFO[1-1].CASH_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "44");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "222222222");
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2500_AWA_2500.ATM;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1));
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
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                WS_CUR_CNT = WS_CCY_CNT + 1;
                for (WS_TEMP_CNT = WS_CUR_CNT; WS_TEMP_CNT <= K_MAX_CCY_CNT; WS_TEMP_CNT += 1) {
                    if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_TEMP_CNT-1].CCY) 
                        && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_TEMP_CNT-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_TEMP_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCPQBOX);
                BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPQBOX.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPQBOX.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                S000_CALL_BPZPQBOX();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
                JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL.substring(0, 3));
                if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
                JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
                if (!SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
                    WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT = BPCPQBOX.DATA_INFO.BAL - BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                    if (WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT != 0) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR146);
                    }
                } else {
                    BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPCPQBOX.DATA_INFO.BAL;
                    WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT = 0;
                }
                WS_SUSP_INFO[WS_CCY_CNT-1].WS_CCY_AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BAL);
                if (BPCPQBOX.DATA_INFO.BAL != 0) {
                    WS_QBOX_BAL_FLG = 'Y';
                }
            }
        }
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CCY);
                    if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        CEP.TRC(SCCGWA, "333333333");
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "11");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "11111111111");
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "22");
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "33");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BPB2500_AWA_2500.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPB2500_AWA_2500.CCY_INFO[1-1].CASH_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "44");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "222222222");
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_ATM_CASH_TO_TRANS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLAMO);
        BPCSLAMO.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSLAMO.FUNC = '0';
        BPCSLAMO.FROM_TLR = BPB2500_AWA_2500.ATM;
        BPCSLAMO.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL.substring(0, 3));
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.ATM);
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        if (!SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
            BPCSLAMO.TLR = BPB2500_AWA_2500.ATM;
        }
        BPCSLAMO.CS_KIND = '0';
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = WS_SUSP_INFO[WS_CCY_CNT-1].WS_CCY_AMT;
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].SUSP_AMT = WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT 
                    && (BPB2500_AWA_2500.PAR_INFO[WS_PAR_CNT-1].PAR_NUM != 0); WS_PAR_CNT += 1) {
                    WS_CUR_CNT = ( WS_CCY_CNT - 1 ) * K_MAX_PAR_CNT + WS_PAR_CNT;
                    BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL = BPB2500_AWA_2500.PAR_INFO[WS_CUR_CNT-1].PAR_VAL;
                    BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM = BPB2500_AWA_2500.PAR_INFO[WS_CUR_CNT-1].PAR_NUM;
                    BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG = '0';
                }
            }
        }
        S000_CALL_BPZSLAMO();
        if (pgmRtn) return;
    }
    public void B020_ATM_CASH_TO_ATMTRANSFER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLAMO);
        BPCSLAMO.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSLAMO.FUNC = '0';
        BPCSLAMO.FROM_TLR = BPB2500_AWA_2500.ATM;
        BPCSLAMO.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLAMO.CS_KIND = '0';
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        if (!SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
            BPCSLAMO.TLR = BPB2500_AWA_2500.ATM;
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
                CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
                CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            }
        }
        S000_CALL_BPZSLAMO();
        if (pgmRtn) return;
    }
    public void B030_GET_ATM_CASHBOX_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLVBF);
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.PLBOX_TP = '4';
        BPRTLVB.CRNT_TLR = BPB2500_AWA_2500.ATM;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
    }
    public void B040_CHECK_ONWAY_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                IBS.init(SCCGWA, BPCTMOVB);
                IBS.init(SCCGWA, BPRCMOV);
                BPRCMOV.KEY.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPRCMOV.KEY.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPRCMOV.MOV_TYP = K_ATM_OUT;
                BPRCMOV.MOV_STS = K_MOVE_STS_UNCONF;
                BPRCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRCMOV.OUT_TLR = BPB2500_AWA_2500.ATM;
                CEP.TRC(SCCGWA, BPRCMOV.KEY.CCY);
                CEP.TRC(SCCGWA, BPRCMOV.KEY.CASH_TYP);
                CEP.TRC(SCCGWA, BPRCMOV.MOV_TYP);
                CEP.TRC(SCCGWA, BPRCMOV.MOV_STS);
                CEP.TRC(SCCGWA, BPRCMOV.OUT_BR);
                CEP.TRC(SCCGWA, BPRCMOV.OUT_TLR);
                BPCTMOVB.FUNC = 'M';
                BPCTMOVB.POINTER = BPRCMOV;
                BPCTMOVB.DATA_LEN = 228;
                S000_CALL_BPZTMOVB();
                if (pgmRtn) return;
                BPCTMOVB.FUNC = 'R';
                BPCTMOVB.POINTER = BPRCMOV;
                BPCTMOVB.DATA_LEN = 228;
                S000_CALL_BPZTMOVB();
                if (pgmRtn) return;
                if (BPCTMOVB.RETURN_INFO == 'F') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR99);
                }
                if (BPCTMOVB.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, BPRCMOV.KEY.CONF_NO);
                    WS_AMT_OUT_ONWAY_FLG = 'Y';
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_MOV_DT = BPRCMOV.KEY.MOV_DT;
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CONF_NO = BPRCMOV.KEY.CONF_NO;
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY = BPRCMOV.KEY.CCY;
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CASH_TYP = BPRCMOV.KEY.CASH_TYP;
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT = BPRCMOV.AMT;
                }
                BPCTMOVB.FUNC = 'E';
                BPCTMOVB.POINTER = BPRCMOV;
                BPCTMOVB.DATA_LEN = 228;
                S000_CALL_BPZTMOVB();
                if (pgmRtn) return;
                if (WS_AMT_OUT_ONWAY_FLG == 'Y') {
                    IBS.init(SCCGWA, BPCTCHIB);
                    IBS.init(SCCGWA, BPRCHIS);
                    BPRCHIS.KEY.AC_DATE = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_MOV_DT;
                    BPRCHIS.KEY.TLR = BPB2500_AWA_2500.ATM;
                    BPRCHIS.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    BPRCHIS.KEY.CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
                    BPRCHIS.KEY.CASH_TYP = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CASH_TYP;
                    BPRCHIS.AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
                    BPRCHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPRCHIS.VB_FLG = '0';
                    BPRCHIS.IN_OUT = K_CASH_OUT;
                    BPRCHIS.STS = '0';
                    BPCTCHIB.INFO.FUNC = 'O';
                    BPCTCHIB.POINTER = BPRCHIS;
                    BPCTCHIB.LEN = 153;
                    S000_CALL_BPZTCHIB();
                    if (pgmRtn) return;
                    BPCTCHIB.INFO.FUNC = 'N';
                    BPCTCHIB.POINTER = BPRCHIS;
                    BPCTCHIB.LEN = 153;
                    S000_CALL_BPZTCHIB();
                    if (pgmRtn) return;
                    if (BPCTCHIB.RETURN_INFO == 'F') {
                        CEP.TRC(SCCGWA, "FIND BPTCHIS");
                        CEP.TRC(SCCGWA, BPRCHIS.KEY.JRN);
                        CEP.TRC(SCCGWA, BPRCHIS);
                        WS_ATM_OUT_CHIS_FLG = 'Y';
                        WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_JRN_NO = BPRCHIS.KEY.JRN;
                    }
                    BPCTCHIB.INFO.FUNC = 'E';
                    BPCTCHIB.POINTER = BPRCHIS;
                    BPCTCHIB.LEN = 153;
                    S000_CALL_BPZTCHIB();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B050_RECALL_ONWAY_REC_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00) {
                IBS.init(SCCGWA, BPCRMOVF);
                IBS.init(SCCGWA, BPRCMOV);
                BPRCMOV.KEY.MOV_DT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_MOV_DT;
                BPRCMOV.KEY.CONF_NO = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CONF_NO;
                BPRCMOV.KEY.CASH_TYP = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CASH_TYP;
                BPRCMOV.KEY.CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
                BPCRMOVF.LEN = 228;
                BPCRMOVF.INFO.FUNC = 'R';
                BPCRMOVF.POINTER = BPRCMOV;
                S000_CALL_BPZTMOVF();
                if (pgmRtn) return;
                if (BPRCMOV.MOV_STS != '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                BPRCMOV.MOV_STS = '3';
                BPCRMOVF.LEN = 228;
                BPCRMOVF.INFO.FUNC = 'U';
                BPCRMOVF.POINTER = BPRCMOV;
                S000_CALL_BPZTMOVF();
                if (pgmRtn) return;
                if (WS_ATM_OUT_CHIS_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "UPDATE BPTCHIS");
                    CEP.TRC(SCCGWA, WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_JRN_NO);
                    IBS.init(SCCGWA, BPCRCHIS);
                    IBS.init(SCCGWA, BPRCHIS);
                    BPRCHIS.KEY.AC_DATE = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_MOV_DT;
                    BPRCHIS.KEY.JRN = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_JRN_NO;
                    BPRCHIS.KEY.TLR = BPB2500_AWA_2500.ATM;
                    BPRCHIS.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    BPRCHIS.KEY.CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
                    BPRCHIS.KEY.CASH_TYP = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CASH_TYP;
                    BPCRCHIS.INFO.FUNC = 'R';
                    BPCRCHIS.POINTER = BPRCHIS;
                    BPCRCHIS.LEN = 153;
                    S000_CALL_BPZRCHIS();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCRCHIS.RC);
                    BPRCHIS.STS = '1';
                    BPCRCHIS.INFO.FUNC = 'U';
                    BPCRCHIS.POINTER = BPRCHIS;
                    BPCRCHIS.LEN = 153;
                    S000_CALL_BPZRCHIS();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCPOEWA);
                BPCPOEWA.DATA.CNTR_TYPE = "CAS";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
                    || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                    BPCPOEWA.DATA.EVENT_CODE = "CSATMCN";
                }
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Z+"")) {
                    BPCPOEWA.DATA.EVENT_CODE = "CSATMCF";
                }
                BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
                CEP.TRC(SCCGWA, WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT);
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.AC_DATE == WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_MOV_DT) {
                    B060_RETRUN_CASH_TO_ATM_CN();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, BPCPOEWA);
                    BPCPOEWA.DATA.CNTR_TYPE = "CAS";
                    BPCPOEWA.DATA.EVENT_CODE = "CSATMLE";
                    BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
                    BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
                    CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
                    CEP.TRC(SCCGWA, WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT);
                    S000_CALL_BPZPOEWA();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT);
                }
            }
        }
    }
    public void B060_RETRUN_CASH_TO_ATM_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
        BPRCLIB.KEY.CASH_TYP = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CASH_TYP;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
            BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
            BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        BPRCLIB.BAL = BPRCLIB.BAL + WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
        if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
        }
        BPRCLIB.GD_AMT = BPRCLIB.GD_AMT + WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
        if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
            BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = BPB2500_AWA_2500.ATM;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT = WS_SUSP_INFO[WS_CCY_CNT-1].WS_SUSP_AMT + WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
            BPCPOEWA.DATA.EVENT_CODE = "CSATMDN";
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Z+"")) {
            BPCPOEWA.DATA.EVENT_CODE = "CSATMDF";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_CMOV_AMT);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B070_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLAMO);
        BPCOLAMO.FROM_TLR = BPB2500_AWA_2500.ATM;
        BPCOLAMO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOLAMO.CONF_SEQ = BPRCMOV.KEY.CONF_NO;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOLAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOLAMO.CCY_INFO[WS_CCY_CNT-1].AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            BPCOLAMO.CCY_CNT = WS_CCY_CNT;
            for (WS_PAR_CNT = 1; WS_PAR_CNT <= 60 
                && BPB2500_AWA_2500.PAR_INFO[WS_PAR_CNT-1].PAR_VAL != 0; WS_PAR_CNT += 1) {
                BPCOLAMO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPB2500_AWA_2500.PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                BPCOLAMO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPB2500_AWA_2500.PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                BPCOLAMO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPB2500_AWA_2500.PAR_INFO[WS_PAR_CNT-1].M_FLG;
                BPCOLAMO.DT_CNT = WS_PAR_CNT;
            }
        }
        CEP.TRC(SCCGWA, BPCOLAMO);
        CEP.TRC(SCCGWA, BPCOLAMO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLAMO;
        SCCFMT.DATA_LEN = 1700;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
    }
    public void S000_CALL_BPZTCHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CHIS, BPCTCHIB);
    }
    public void S000_CALL_BPZTMOVF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CMOV, BPCRMOVF);
        if (BPCRMOVF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMOVF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CHIS, BPCRCHIS);
        if (BPCRCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BOX, BPCPQBOX);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
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
        SCCCALL.ERR_FLDNO = BPB2500_AWA_2500.TLR_PSW;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            WS_FLD_NO = BPB2500_AWA_2500.TLR_PSW_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSLAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_LAT_TO_FROM_BOX, BPCSLAMO);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
