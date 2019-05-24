package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2100 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String CPN_S_DRAW_CASH = "BP-S-DRAW-CASH      ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_CHECK_PAR_VALUE = "BP-P-CHECK-PAR-VALUE";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String K_HIS_REMARKS = "LING XIAN";
    String CPN_S_CASAPP_MAINTAIN = "BP-S-CASAPP-MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INDEX = 0;
    double WS_GD_AMT = 0;
    BPOT2100_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT2100_WS_TEMP_VARIABLE();
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSDRCS BPCSDRCS = new BPCSDRCS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQVAL BPCPQVAL = new BPCPQVAL();
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
        CEP.TRC(SCCGWA, "BPOT2100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2100_AWA_2100>");
        BPB2100_AWA_2100 = (BPB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSDRCS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.C_SWIFT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.BR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].BV_DATE);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.FLG);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.AC_NO);
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B020_CHECK_TLR_FOR_CN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_CODE.trim().length() > 0) {
                    if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_DT_GT_AC_DT);
                    }
                }
                B030_DATA_TRANSFER_FOR_CN();
            }
        } else {
            B020_CHECK_TLR();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_DATA_TRANSFER();
            }
        }
        B030_HISTORY_RECORD();
        B040_DRAW_CASH_PROC();
        CEP.TRC(SCCGWA, "NCB032305");
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.APP_NO);
        if (BPB2100_AWA_2100.APP_NO != 0) {
            IBS.init(SCCGWA, BPCSARAP);
            BPCSARAP.INFO.FUNC = 'U';
            BPCSARAP.APP_NO = BPB2100_AWA_2100.APP_NO;
            BPCSARAP.APP_STS = '1';
            S000_CALL_BPZSARAP();
            if (BPCSARAP.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, "NCB032301");
                BPCSARAP.APP_STS = '2';
                BPCSARAP.CONF_NO = BPCSDRCS.CONF_NO;
                CEP.TRC(SCCGWA, BPCSARAP.APP_STS);
                CEP.TRC(SCCGWA, BPCSARAP.CONF_NO);
                BPCSARAP.INFO.FUNC = 'M';
                S000_CALL_BPZSARAP();
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APP_NO_NOT_FND);
            }
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
        B010_03_CHECK_CCY_INFO();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P913";
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
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2100_AWA_2100.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2100_AWA_2100.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_TLR_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCPQBOX);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, "111111");
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_CSTLR_DRAW;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_TLR_NOT_CASHLIB;
            S000_ERR_MSG_PROC();
        }
        BPRTLVB.KEY.BR = BPB2100_AWA_2100.TR_BR;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPRTLVB.PLBOX_TP = ALL.charAt(0);
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        CEP.TRC(SCCGWA, "RTLVB-READNEXT");
        WS_TEMP_VARIABLE.WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_TEMP_VARIABLE.WS_CNT <= 1000) {
            CEP.TRC(SCCGWA, "1000");
            WS_TEMP_VARIABLE.WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2' 
                || BPRTLVB.PLBOX_TP == '5') {
                WS_TEMP_VARIABLE.WS_CNT = 1001;
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                CEP.TRC(SCCGWA, "1001");
                CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.PLBOX_NO);
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
        if (BPCPQBOX.DATA_INFO.PLBOX_NO.trim().length() > 0) {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
                if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                    BPCPQBOX.DATA_INFO.VB_BR = BPB2100_AWA_2100.TR_BR;
                    BPCPQBOX.DATA_INFO.CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                    BPCPQBOX.DATA_INFO.CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
                    IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
                    if (BPCPQBOX.RC.RC_CODE != 0) {
                        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOT_HAVE_LIB;
            WS_FLD_NO = (short) BPB2100_AWA_2100.TR_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCPQBOX);
        BPRTLVB.KEY.BR = BPB2100_AWA_2100.TR_BR;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPRTLVB.PLBOX_TP = ALL.charAt(0);
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        CEP.TRC(SCCGWA, "RTLVB-READNEXT");
        WS_TEMP_VARIABLE.WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_TEMP_VARIABLE.WS_CNT <= 1000) {
            CEP.TRC(SCCGWA, "1000");
            WS_TEMP_VARIABLE.WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2' 
                || BPRTLVB.PLBOX_TP == '5') {
                WS_TEMP_VARIABLE.WS_CNT = 1001;
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                CEP.TRC(SCCGWA, "1001");
                CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.PLBOX_NO);
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
        if (BPCPQBOX.DATA_INFO.PLBOX_NO.trim().length() > 0) {
            BPCPQBOX.DATA_INFO.VB_BR = BPB2100_AWA_2100.TR_BR;
            BPCPQBOX.DATA_INFO.CASH_TYP = BPB2100_AWA_2100.CCY_INFO[1-1].CASH_TYP;
            BPCPQBOX.DATA_INFO.CCY = BPB2100_AWA_2100.CCY_INFO[1-1].CCY;
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_BR);
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CASH_TYP);
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CCY);
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.PLBOX_NO);
            IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
            if (BPCPQBOX.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
                S000_ERR_MSG_PROC();
            }
            if (BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOT_HAVE_LIB;
            WS_FLD_NO = (short) BPB2100_AWA_2100.TR_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_DATA_TRANSFER_FOR_CN() throws IOException,SQLException,Exception {
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].FEE_FLG = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].FEE_FLG;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].FEE_CD = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].FEE_CD;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].FEE_AMT = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].FEE_AMT;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_CODE = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_CODE;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_DATE = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_DATE;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_HD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_HD_NO;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_NO;
    }
    public void B030_DATA_TRANSFER() throws IOException,SQLException,Exception {
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].FEE_FLG = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].FEE_FLG;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].FEE_CD = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].FEE_CD;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].FEE_AMT = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].FEE_AMT;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_CODE = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_CODE;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_DATE = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_DATE;
        BPCSDRCS.DATA_INFO[WS_CCY_CNT-1].BV_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].BV_NO;
    }
    public void B040_DRAW_CASH_PROC() throws IOException,SQLException,Exception {
        BPCSDRCS.SWIFT = BPB2100_AWA_2100.C_SWIFT;
        BPCSDRCS.BR = BPB2100_AWA_2100.TR_BR;
        JIBS_NumStr = "" + 0;
        BPCSDRCS.CS_KIND = JIBS_NumStr.charAt(0);
        BPCSDRCS.FLG = BPB2100_AWA_2100.FLG;
        BPCSDRCS.AC_NO = BPB2100_AWA_2100.AC_NO;
        BPCSDRCS.APP_NO = BPB2100_AWA_2100.APP_NO;
        CEP.TRC(SCCGWA, BPCSDRCS.SWIFT);
        CEP.TRC(SCCGWA, BPCSDRCS.CS_KIND);
        BPCSDRCS.DATA_INFO[1-1].BV_CODE = BPB2100_AWA_2100.CCY_INFO[1-1].BV_CODE;
        S000_CALL_BPZSDRCS();
        BPB2100_AWA_2100.MOVE_DT = BPCSDRCS.MOVE_DT;
        BPB2100_AWA_2100.CONF_NO = BPCSDRCS.CONF_NO;
        BPB2100_AWA_2100.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2100_AWA_2100.TR_BR = BPCSDRCS.BR;
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.MOVE_DT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CONF_NO);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_TLR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_BR);
    }
    public void S000_CALL_BPZSDRCS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_DRAW_CASH;
        SCCCALL.COMMAREA_PTR = BPCSDRCS;
        SCCCALL.ERR_FLDNO = BPB2100_AWA_2100.C_SWIFT_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
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
