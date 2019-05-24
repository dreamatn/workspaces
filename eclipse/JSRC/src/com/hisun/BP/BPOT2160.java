package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2160 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_S_CONF_HD_STORE = "BP-S-CONF-HD-STORE  ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String K_HIS_REMARKS = "JIE XIAN QUE REN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_CNT = 0;
    int WS_INDEX = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_COUNT = 0;
    int WS_RTN_CNT = 0;
    double WS_GD_AMT = 0;
    double WS_TMP_AMT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCFHD BPCSCFHD = new BPCSCFHD();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2160_AWA_2160 BPB2160_AWA_2160;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2160 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2160_AWA_2160>");
        BPB2160_AWA_2160 = (BPB2160_AWA_2160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCFHD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.MOVE_DT);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CONF_NO);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CS_KIND);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.C_SWIFT);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[1-1].CCY_AMT);
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
        } else {
            B020_CHECK_ORG();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            B030_CHECK_TELLER();
        }
        if (BPB2160_AWA_2160.CON_KIND == '1' 
            && WS_RTN_CNT == WS_CCY_CNT - 1) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RTN_NOT_ALL_ZER;
            S000_ERR_MSG_PROC();
        }
        B030_HISTORY_RECORD();
        B040_CONF_HANDIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CS_KIND = BPB2160_AWA_2160.CS_KIND;
        B010_03_CHECK_CCY_INFO();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P914";
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
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_CCY_CNT);
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[2-1].CCY);
                    CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[1-1].CCY);
                    if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2160_AWA_2160.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2160_AWA_2160.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "1");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                CEP.TRC(SCCGWA, "2");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    CEP.TRC(SCCGWA, "3");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
            }
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.trim().length() > 0 
                && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "4");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.trim().length() == 0 
                && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "5");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY);
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "6");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_ORG_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_TLR_NOT_CASHLIB;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2160_AWA_2160.MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2160_AWA_2160.CONF_NO;
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQMOV();
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_TYP);
        if (BPCPQMOV.DATA_INFO.MOV_TYP != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MOV_HANDIN;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_BR_NOT_HDCS_BR;
            S000_ERR_MSG_PROC();
        }
        BPCSCFHD.OUT_BR = BPCPQMOV.DATA_INFO.OUT_BR;
        BPCSCFHD.OUT_TLR = BPCPQMOV.DATA_INFO.OUT_TLR;
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_STS);
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2160_AWA_2160.CS_KIND != BPCPQMOV.DATA_INFO.CS_KIND) {
            CEP.TRC(SCCGWA, "1");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2160_AWA_2160.CS_KIND_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.C_SWIFT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.TO_BANK);
        if (!BPB2160_AWA_2160.C_SWIFT.equalsIgnoreCase(BPCPQMOV.DATA_INFO.TO_BANK)) {
            CEP.TRC(SCCGWA, "2");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2160_AWA_2160.C_SWIFT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_TMP_AMT = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CONF_AMT + BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].RTN_AMT;
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != WS_TMP_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
            WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].RTN_AMT == 0) {
            WS_RTN_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
        if (BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.TRC(SCCGWA, "5");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].CONF_AMT = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CONF_AMT;
        BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].RTN_AMT = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].RTN_AMT;
        if (BPB2160_AWA_2160.CON_KIND == '1') {
            WS_GD_AMT = 0;
            WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
            for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
                && BPB2160_AWA_2160.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
                WS_I = WS_INFO_CNT - WS_START_CNT + 1;
                BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2160_AWA_2160.P_INFO[WS_INFO_CNT-1].P_PVAL;
                BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2160_AWA_2160.P_INFO[WS_INFO_CNT-1].P_NUM;
                BPCSCFHD.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2160_AWA_2160.P_INFO[WS_INFO_CNT-1].P_MFLG;
                WS_GD_AMT = WS_GD_AMT + BPB2160_AWA_2160.P_INFO[WS_INFO_CNT-1].P_PVAL * BPB2160_AWA_2160.P_INFO[WS_INFO_CNT-1].P_NUM;
                if (WS_CS_KIND == '3') {
                    WS_GD_AMT = WS_GD_AMT / 2;
                }
            }
            if (WS_GD_AMT != BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].RTN_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
                WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B040_CONF_HANDIN_PROC() throws IOException,SQLException,Exception {
        BPCSCFHD.MOVE_DT = BPB2160_AWA_2160.MOVE_DT;
        BPCSCFHD.CONF_NO = BPB2160_AWA_2160.CONF_NO;
        BPCSCFHD.CS_KIND = BPB2160_AWA_2160.CS_KIND;
        BPCSCFHD.SWIFT = BPB2160_AWA_2160.C_SWIFT;
        BPCSCFHD.CONF_KIND = BPB2160_AWA_2160.CON_KIND;
        S000_CALL_BPZSCFHD();
    }
    public void S000_CALL_BPZSCFHD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_CONF_HD_STORE;
        SCCCALL.COMMAREA_PTR = BPCSCFHD;
        SCCCALL.ERR_FLDNO = BPB2160_AWA_2160.OUT_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
        if (BPCPQMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQMOV.RC);
            WS_FLD_NO = BPB2160_AWA_2160.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
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
