package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2530 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP278";
    char K_ATM_OUT = '5';
    char K_MOVE_STS_UNCONF = '1';
    int K_MAX_CCY_CNT = 20;
    int K_MAX_PAR_CNT = 12;
    String CPN_S_IN_STORE = "BP-S-IN-STORE       ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String CPN_U_CMOV_CONFIRM = "BP-U-CMOV-CONFIRM   ";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INDEX = 0;
    int WS_CONF_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_CCY = 0;
    BPOT2530_WS_SUM_ATM_AMT[] WS_SUM_ATM_AMT = new BPOT2530_WS_SUM_ATM_AMT[999];
    BPOT2530_WS_ERR_INFO WS_ERR_INFO = new BPOT2530_WS_ERR_INFO();
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    char WS_ONWAY_INFO_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCSISTO BPCSISTO = new BPCSISTO();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCUCMOV BPCUCMOV = new BPCUCMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCOQAMO BPCOQAMO = new BPCOQAMO();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    SCCGWA SCCGWA;
    BPB2530_AWA_2530 BPB2530_AWA_2530;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPOT2530() {
        for (int i=0;i<999;i++) WS_SUM_ATM_AMT[i] = new BPOT2530_WS_SUM_ATM_AMT();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2530 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2530_AWA_2530>");
        BPB2530_AWA_2530 = (BPB2530_AWA_2530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSISTO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CONF_NO);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].MOVE_DT);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[3-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[4-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[5-1].CCY);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            B020_CHECK_ORG_TLR_FOR_CN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_ON_WAY_INFO_FOR_CN();
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO == 0) {
                    BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO = WS_CONF_NO;
                }
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B040_PROCESS_CASH_IN_FOR_CN();
            B060_TRANS_DATA_OUTPUT_FOR_CN();
        } else {
            B010_CHECK_INPUT();
            B020_CHECK_ORG();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER();
            }
            B040_PROCESS_CASH_IN();
            B060_TRANS_DATA_OUTPUT();
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB2530_AWA_2530.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPB2530_AWA_2530.CCY_INFO[1-1].CCY_AMT == 0 
            || BPB2530_AWA_2530.CCY_INFO[1-1].CASH_TYP.trim().length() == 0 
            || BPB2530_AWA_2530.CCY_INFO[1-1].ATM.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].CCY);
                    if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].CASH_TYP) 
                        && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].ATM)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[3-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[4-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[5-1].CCY);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[1-1].CASH_TYP);
        B010_03_CHECK_CCY_INFO();
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].CCY);
                    if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2530_AWA_2530.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB2530_AWA_2530.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPB2530_AWA_2530.CCY_INFO[1-1].CCY_AMT == 0 
            || BPB2530_AWA_2530.CCY_INFO[1-1].CASH_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_ORG_TLR_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR112);
        }
        if (BPRTLVB.PLBOX_TP == '4') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR113);
        }
        if (BPRTLVB.PLBOX_TP == '3' 
            || BPRTLVB.PLBOX_TP == '6' 
            || BPRTLVB.PLBOX_TP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
            }
        }
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASHLIB_TLR);
            }
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() != 0; WS_CCY_CNT += 1) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                && !BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2") 
                && !BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
                S000_ERR_MSG_PROC();
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_ON_WAY_INFO_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO == 0) {
            IBS.init(SCCGWA, BPCTMOVB);
            IBS.init(SCCGWA, BPRCMOV);
            BPRCMOV.KEY.MOV_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
            BPRCMOV.KEY.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPRCMOV.KEY.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPRCMOV.MOV_TYP = K_ATM_OUT;
            BPRCMOV.MOV_STS = K_MOVE_STS_UNCONF;
            BPRCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCMOV.OUT_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            BPCTMOVB.FUNC = 'O';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            BPCTMOVB.FUNC = 'R';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            WS_ONWAY_INFO_FLAG = 'N';
            while (BPCTMOVB.RETURN_INFO != 'N' 
                && WS_ONWAY_INFO_FLAG != 'Y') {
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == BPRCMOV.AMT) {
                    WS_ONWAY_INFO_FLAG = 'Y';
                    WS_CONF_NO = BPRCMOV.KEY.CONF_NO;
                } else {
                    BPCTMOVB.FUNC = 'R';
                    BPCTMOVB.POINTER = BPRCMOV;
                    BPCTMOVB.DATA_LEN = 228;
                    S000_CALL_BPZTMOVB();
                }
            }
            BPCTMOVB.FUNC = 'E';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            if (WS_ONWAY_INFO_FLAG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            IBS.init(SCCGWA, BPCPQMOV);
            BPCPQMOV.DATA_INFO.MOV_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
            BPCPQMOV.DATA_INFO.CONF_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
            BPCPQMOV.DATA_INFO.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCPQMOV.DATA_INFO.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            S000_CALL_BPZPQMOV();
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_TYP);
            if (BPCPQMOV.DATA_INFO.MOV_TYP != '5') {
                CEP.TRC(SCCGWA, "CMOV-MOV-TYP NOT = 5");
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR173);
            }
            if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
                CEP.TRC(SCCGWA, "DEV");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC();
            }
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
                CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-IN-BR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.OUT_BR) {
                CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-OUT-BR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
                CEP.TRC(SCCGWA, "AWA-CCY-AMT(WS-CCY-CNT) NOT = PQMOV-AMT");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                WS_ERR_INFO.WS_ERR_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (!BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.equalsIgnoreCase(BPCPQMOV.DATA_INFO.OUT_TLR)) {
                CEP.TRC(SCCGWA, "AWA-ATM(WS-CCY-CNT) NOT = PQMOV-OUT-TLR");
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
            }
        }
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '3';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQBOX();
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_DT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CONF_NO);
        S000_CALL_BPZPQMOV();
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_STS);
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-IN-BR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.OUT_BR) {
            CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-OUT-BR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.TRC(SCCGWA, "AWA-CCY-AMT(WS-CCY-CNT) NOT = PQMOV-AMT");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B040_PROCESS_CASH_IN_FOR_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCUCSIN);
                BPCUCSIN.VB_FLG = '0';
                BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.PLBOX_TYP = BPRTLVB.PLBOX_TP;
                CEP.TRC(SCCGWA, BPCUCSIN.PLBOX_TYP);
                if (BPCUCSIN.PLBOX_TYP == '3' 
                    || BPCUCSIN.PLBOX_TYP == '6') {
                    BPCUCSIN.VB_FLG = '0';
                    BPCUCSIN.CASH_STAT = '0';
                } else {
                    BPCUCSIN.VB_FLG = '1';
                    BPCUCSIN.CASH_STAT = '1';
                }
                BPCUCSIN.CS_KIND = '0';
                BPCUCSIN.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSIN.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSIN.TX_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                S000_CALL_BPZUCSIN();
                B050_ON_WAY_PROC();
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) WS_CCY = 0;
                else WS_CCY = Integer.parseInt(BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
                WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                WS_SUM_ATM_AMT[WS_CCY-1].WS_CONF_SEQ = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
                WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT + BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            }
        }
        CEP.TRC(SCCGWA, "WRITE HISTORY!!");
        for (WS_CCY = 1; WS_CCY <= 999; WS_CCY += 1) {
            CEP.TRC(SCCGWA, "DEV1");
            CEP.TRC(SCCGWA, WS_CCY);
            if (WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY.trim().length() > 0 
                && WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT != 0) {
                CEP.TRC(SCCGWA, "DEV2");
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPCHIS.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPCHIS.CASH_TYP = WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP;
                BPCPCHIS.CCY = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY;
                BPCPCHIS.AMT = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT;
                CEP.TRC(SCCGWA, WS_SUM_ATM_AMT[WS_CCY-1].WS_CONF_SEQ);
                BPCPCHIS.CONF_SEQ = WS_SUM_ATM_AMT[WS_CCY-1].WS_CONF_SEQ;
                if (BPRTLVB.PLBOX_TP == '3' 
                    || BPRTLVB.PLBOX_TP == '6') {
                    BPCPCHIS.VB_FLG = '0';
                } else {
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2' 
                        || BPRTLVB.PLBOX_TP == '5') {
                        BPCPCHIS.VB_FLG = '1';
                    }
                }
                BPCPCHIS.IN_OUT = 'D';
                BPCPCHIS.CS_KIND = '0';
                CEP.TRC(SCCGWA, "DEV3");
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B040_PROCESS_CASH_IN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCUCSIN);
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_FLG = '0';
                BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUCSIN.PLBOX_TYP = '3';
                BPCUCSIN.CS_KIND = '0';
                BPCUCSIN.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSIN.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSIN.TX_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                S000_CALL_BPZUCSIN();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPCHIS.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                BPCPCHIS.VB_FLG = '0';
                BPCPCHIS.IN_OUT = 'D';
                BPCPCHIS.CS_KIND = '0';
                S000_CALL_BPZPCHIS();
                B050_ON_WAY_PROC();
            }
        }
    }
    public void B050_ON_WAY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "123123");
        IBS.init(SCCGWA, BPCUCMOV);
        CEP.TRC(SCCGWA, "1231231");
        BPCUCMOV.MOVE_DATE = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
        CEP.TRC(SCCGWA, "1231232");
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPCUCMOV.CASH_TYP);
        BPCUCMOV.CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        CEP.TRC(SCCGWA, "1231233");
        BPCUCMOV.CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, "121212");
        BPCUCMOV.TOTAL_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCUCMOV.CONF_SEQ = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
        BPCUCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, "1234567");
        BPCUCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUCMOV.MOV_TYPE = '5';
        CEP.TRC(SCCGWA, "111111");
        S000_CALL_BPZUCMOV();
    }
    public void B060_TRANS_DATA_OUTPUT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQAMO);
        BPCOQAMO.TO_TLR = SCCGWA.COMM_AREA.TL_ID;
        WS_PAR_CNT = 1;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].ATM_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].CONF_SEQ = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].MOVE_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
        }
        BPCOQAMO.CCY_CNT = WS_CCY_CNT - 1;
        CEP.TRC(SCCGWA, BPCOQAMO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOQAMO;
        SCCFMT.DATA_LEN = 933;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQAMO);
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].CONF_SEQ = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
            BPCOQAMO.CCY_INFO[WS_CCY_CNT-1].MOVE_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
        }
        BPCOQAMO.CCY_CNT = WS_CCY_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOQAMO;
        SCCFMT.DATA_LEN = 933;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_GET_LONGTOU_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TLVB_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_IN, BPCUCSIN);
        if (BPCUCSIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSIN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSISTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_IN_STORE, BPCSISTO);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_MOVD, BPCPQMOV);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCPQMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQMOV.RC);
            WS_FLD_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZUCMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_CONFIRM, BPCUCMOV);
        if (BPCUCMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCMOV.RC);
            S000_ERR_MSG_PROC();
        }
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
