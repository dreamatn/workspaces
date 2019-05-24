package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2510 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP276";
    char K_ATM_IN = '4';
    char K_MOVE_STS_UNCONF = '1';
    String CPN_S_IN_STORE = "BP-S-IN-STORE       ";
    String CPN_P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String CPN_U_CMOV_CONFIRM = "BP-U-CMOV-CONFIRM   ";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String CPN_S_CASH_SUSPENSE = "BP-S-CASH-SUSPENSE  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INDEX = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_COUNT = 0;
    int WS_CONF_NO = 0;
    BPOT2510_WS_SAVE_CMOV_INFO[] WS_SAVE_CMOV_INFO = new BPOT2510_WS_SAVE_CMOV_INFO[5];
    BPOT2510_WS_SUSP_CMOV_INFO[] WS_SUSP_CMOV_INFO = new BPOT2510_WS_SUSP_CMOV_INFO[5];
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
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCUCMOV BPCUCMOV = new BPCUCMOV();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCOZAMO BPCOZAMO = new BPCOZAMO();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPCSCSSP BPCSCSSP = new BPCSCSSP();
    SCCGWA SCCGWA;
    BPB2500_AWA_2500 BPB2500_AWA_2500;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPOT2510() {
        for (int i=0;i<5;i++) WS_SAVE_CMOV_INFO[i] = new BPOT2510_WS_SAVE_CMOV_INFO();
        for (int i=0;i<5;i++) WS_SUSP_CMOV_INFO[i] = new BPOT2510_WS_SUSP_CMOV_INFO();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2510 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2500_AWA_2500>");
        BPB2500_AWA_2500 = (BPB2500_AWA_2500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSISTO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CONF_NO);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.MOVE_DT);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.ATM);
        B010_CHECK_INPUT();
        B020_CHECK_ORG();
        B070_GET_PLBOX_NO();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                    B030_CHECK_TELLER_FOR_CN();
                }
            }
            if (BPB2500_AWA_2500.CONF_NO == 0) {
                BPB2500_AWA_2500.CONF_NO = WS_CONF_NO;
            }
            B040_PROCESS_CASH_IN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                    B050_ON_WAY_PROC_FOR_CN();
                }
            }
        } else {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                    B030_CHECK_TELLER();
                }
            }
            B040_PROCESS_CASH_IN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                    B050_ON_WAY_PROC();
                }
            }
        }
        B060_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[3-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[4-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[5-1].CCY);
        B010_03_CHECK_CCY_INFO();
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL.substring(0, 3));
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        if (SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
            BPB2500_AWA_2500.ATM = SCCGWA.COMM_AREA.TL_ID;
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
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB2500_AWA_2500.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPB2500_AWA_2500.CCY_INFO[1-1].CASH_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
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
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2500_AWA_2500.ATM;
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
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQBOX();
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(BPB2500_AWA_2500.ATM)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2500_AWA_2500.CONF_NO == 0) {
            IBS.init(SCCGWA, BPCTMOVB);
            IBS.init(SCCGWA, BPRCMOV);
            BPRCMOV.KEY.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPRCMOV.KEY.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPRCMOV.MOV_TYP = K_ATM_IN;
            BPRCMOV.MOV_STS = K_MOVE_STS_UNCONF;
            BPRCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCMOV.IN_TLR = BPB2500_AWA_2500.ATM;
            BPCTMOVB.FUNC = 'I';
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
                if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == BPRCMOV.AMT) {
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_AMT = BPRCMOV.AMT;
                    CEP.TRC(SCCGWA, WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_AMT);
                    WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_CMOV_DT = BPRCMOV.KEY.MOV_DT;
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
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            IBS.init(SCCGWA, BPCPQMOV);
            BPCPQMOV.DATA_INFO.MOV_DT = BPB2500_AWA_2500.MOVE_DT;
            BPCPQMOV.DATA_INFO.CONF_NO = BPB2500_AWA_2500.CONF_NO;
            BPCPQMOV.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCPQMOV.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_DT);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CONF_NO);
            S000_CALL_BPZPQMOV();
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_STS);
            if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_TLR);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (!BPB2500_AWA_2500.ATM.equalsIgnoreCase(BPCPQMOV.DATA_INFO.IN_TLR)) {
                CEP.TRC(SCCGWA, "GWA-TL-ID NOT = PQMOV-IN-TLR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
                CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-IN-B");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
                CEP.TRC(SCCGWA, "AWA-CCY-AMT(WS-CCY-CNT) NOT = PQMOV-AMT");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_CMOV_DT = BPB2500_AWA_2500.MOVE_DT;
            WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '4';
        BPRTLVB.CRNT_TLR = BPB2500_AWA_2500.ATM;
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
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQBOX();
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(BPB2500_AWA_2500.ATM)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2500_AWA_2500.MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2500_AWA_2500.CONF_NO;
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_DT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CONF_NO);
        S000_CALL_BPZPQMOV();
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_STS);
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_TLR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (!BPB2500_AWA_2500.ATM.equalsIgnoreCase(BPCPQMOV.DATA_INFO.IN_TLR)) {
            CEP.TRC(SCCGWA, "GWA-TL-ID NOT = PQMOV-IN-TLR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-IN-BR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
        if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.TRC(SCCGWA, "AWA-CCY-AMT(WS-CCY-CNT) NOT = PQMOV-AMT");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B040_PROCESS_CASH_IN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCUCSIN);
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_FLG = '0';
                BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSIN.VB_TLR = BPB2500_AWA_2500.ATM;
                BPCUCSIN.PLBOX_TYP = '4';
                BPCUCSIN.CS_KIND = '0';
                BPCUCSIN.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSIN.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSIN.TX_AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_AMT;
                CEP.TRC(SCCGWA, BPCUCSIN.TX_AMT);
                S000_CALL_BPZUCSIN();
                WS_SUSP_CMOV_INFO[WS_CCY_CNT-1].WS_SUSP_AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_AMT - BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                CEP.TRC(SCCGWA, WS_SUSP_CMOV_INFO[WS_CCY_CNT-1].WS_SUSP_AMT);
                if (WS_SUSP_CMOV_INFO[WS_CCY_CNT-1].WS_SUSP_AMT != 0) {
                    IBS.init(SCCGWA, BPCSCSSP);
                    BPCSCSSP.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                    BPCSCSSP.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                    BPCSCSSP.BOX_FLG = '4';
                    if (WS_SUSP_CMOV_INFO[WS_CCY_CNT-1].WS_SUSP_AMT > 0) {
                        BPCSCSSP.TOTAL_AMT = WS_SUSP_CMOV_INFO[WS_CCY_CNT-1].WS_SUSP_AMT;
                        BPCSCSSP.ML_OPT = '1';
                    } else {
                        BPCSCSSP.TOTAL_AMT = BPCSCSSP.TOTAL_AMT - WS_SUSP_CMOV_INFO[WS_CCY_CNT-1].WS_SUSP_AMT;
                        BPCSCSSP.ML_OPT = '0';
                    }
                    BPCSCSSP.CS_KIND = '0';
                    BPCSCSSP.SUSP_TYPE = '2';
                    BPCSCSSP.TLR = SCCGWA.COMM_AREA.TL_ID;
                    S000_CALL_BPZSCSSP();
                }
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = BPB2500_AWA_2500.ATM;
                BPCPCHIS.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                BPCPCHIS.VB_FLG = '0';
                BPCPCHIS.IN_OUT = 'D';
                BPCPCHIS.CS_KIND = '0';
                CEP.TRC(SCCGWA, BPB2500_AWA_2500.CONF_NO);
                BPCPCHIS.CONF_SEQ = BPB2500_AWA_2500.CONF_NO;
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B050_ON_WAY_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCMOV);
        BPCUCMOV.MOVE_DATE = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_CMOV_DT;
        BPCUCMOV.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCMOV.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCUCMOV.TOTAL_AMT = WS_SAVE_CMOV_INFO[WS_CCY_CNT-1].WS_SAVE_AMT;
        BPCUCMOV.CONF_SEQ = BPB2500_AWA_2500.CONF_NO;
        BPCUCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_TLR = BPB2500_AWA_2500.ATM;
        BPCUCMOV.MOV_TYPE = '4';
        CEP.TRC(SCCGWA, BPCUCMOV.MOVE_DATE);
        CEP.TRC(SCCGWA, BPCUCMOV.CASH_TYP);
        CEP.TRC(SCCGWA, BPCUCMOV.CCY);
        CEP.TRC(SCCGWA, BPCUCMOV.TOTAL_AMT);
        S000_CALL_BPZUCMOV();
    }
    public void B050_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCMOV);
        BPCUCMOV.MOVE_DATE = BPB2500_AWA_2500.MOVE_DT;
        BPCUCMOV.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCMOV.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCUCMOV.TOTAL_AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCUCMOV.CONF_SEQ = BPB2500_AWA_2500.CONF_NO;
        BPCUCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_TLR = BPB2500_AWA_2500.ATM;
        CEP.TRC(SCCGWA, BPCUCMOV.MOVE_DATE);
        CEP.TRC(SCCGWA, BPCUCMOV.CASH_TYP);
        CEP.TRC(SCCGWA, BPCUCMOV.CCY);
        CEP.TRC(SCCGWA, BPCUCMOV.TOTAL_AMT);
        S000_CALL_BPZUCMOV();
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOZAMO);
        BPCOZAMO.TO_TLR = BPB2500_AWA_2500.ATM;
        BPCOZAMO.CONF_SEQ = BPB2500_AWA_2500.CONF_NO;
        BPCOZAMO.MOVE_DT = BPB2500_AWA_2500.MOVE_DT;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
            BPCOZAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOZAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOZAMO.CCY_INFO[WS_CCY_CNT-1].AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            BPCOZAMO.CCY_CNT = WS_CCY_CNT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOZAMO;
        SCCFMT.DATA_LEN = 135;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_GET_PLBOX_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '4';
        BPRTLVB.CRNT_TLR = BPB2500_AWA_2500.ATM;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSCSSP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASH_SUSPENSE, BPCSCSSP);
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
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_CONFIRM, BPCUCMOV);
        if (BPCUCMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCMOV.RC);
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
