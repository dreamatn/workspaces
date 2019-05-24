package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2150 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_S_CONF_DRAW_CS = "BP-S-CONF-DRAW-CS  ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String K_HIS_REMARKS = "LING XIAN QUE REN";
    String CPN_S_CASAPP_MAINTAIN = "BP-S-CASAPP-MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_COUNT = 0;
    double WS_GD_AMT = 0;
    int WS_INDEX = 0;
    int WS_APP_NO = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCFDR BPCSCFDR = new BPCSCFDR();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSARAP BPCSARAP = new BPCSARAP();
    SCCGWA SCCGWA;
    BPB2100_AWA_2100 BPB2100_AWA_2100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2150 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2100_AWA_2100>");
        BPB2100_AWA_2100 = (BPB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCFDR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.MOVE_DT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CONF_NO);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CS_KIND);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[1-1].P_PVAL);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[1-1].P_NUM);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[1-1].P_MFLG);
        B010_CHECK_INPUT();
        B020_CHECK_ORG();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            B030_CHECK_TELLER();
        }
        B030_HISTORY_RECORD();
        B050_CASH_APP_INFO();
        B040_IN_STORE_PROCESS();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P917";
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
    public void B050_CASH_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSARAP);
        BPCSARAP.INFO.FUNC = 'R';
        BPCSARAP.CONF_NO = BPB2100_AWA_2100.CONF_NO;
        CEP.TRC(SCCGWA, BPCSARAP.CONF_NO);
        S000_CALL_BPZSARAP();
        if (BPCSARAP.RETURN_INFO == 'F') {
            WS_APP_NO = BPCSARAP.APP_NO;
            CEP.TRC(SCCGWA, "NCB032201");
            CEP.TRC(SCCGWA, WS_APP_NO);
            IBS.init(SCCGWA, BPCSARAP);
            BPCSARAP.INFO.FUNC = 'U';
            BPCSARAP.APP_NO = WS_APP_NO;
            BPCSARAP.APP_STS = '2';
            S000_CALL_BPZSARAP();
            BPCSARAP.APP_STS = '3';
            BPCSARAP.INFO.FUNC = 'M';
            S000_CALL_BPZSARAP();
        }
    }
    public void S000_CALL_BPZSARAP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASAPP_MAINTAIN, BPCSARAP);
        if (BPCSARAP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSARAP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CS_KIND = BPB2100_AWA_2100.CS_KIND;
        B010_03_CHECK_CCY_INFO();
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "2");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                CEP.TRC(SCCGWA, "3");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, "HONG");
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    CEP.TRC(SCCGWA, "4");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "6");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, "HUI");
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2' 
                || BPRTLVB.PLBOX_TP == '5') {
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                WS_CNT = 1001;
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.PLBOX_NO);
        S000_CALL_BPZPQBOX();
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2100_AWA_2100.MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2100_AWA_2100.CONF_NO;
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQMOV();
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_TYP);
        if (BPCPQMOV.DATA_INFO.MOV_TYP != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MOV_DRAW;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.OUT_BR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.OUT_TLR);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            CEP.TRC(SCCGWA, "11");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        BPCSCFDR.OUT_BR = BPCPQMOV.DATA_INFO.OUT_BR;
        BPCSCFDR.OUT_TLR = BPCPQMOV.DATA_INFO.OUT_TLR;
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            CEP.TRC(SCCGWA, "13");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
        if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.TRC(SCCGWA, "16");
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        BPCSCFDR.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSCFDR.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSCFDR.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
    }
    public void B040_IN_STORE_PROCESS() throws IOException,SQLException,Exception {
        BPCSCFDR.MOVE_DT = BPB2100_AWA_2100.MOVE_DT;
        BPCSCFDR.CONF_NO = BPB2100_AWA_2100.CONF_NO;
        BPCSCFDR.CS_KIND = BPB2100_AWA_2100.CS_KIND;
        BPCSCFDR.APP_NO = WS_APP_NO;
        S000_CALL_BPZSCFDR();
    }
    public void S000_CALL_BPZSCFDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_CONF_DRAW_CS;
        SCCCALL.COMMAREA_PTR = BPCSCFDR;
        SCCCALL.ERR_FLDNO = BPB2100_AWA_2100.OUT_BR_NO;
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
            WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
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
