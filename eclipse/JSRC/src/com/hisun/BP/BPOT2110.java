package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2110 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_HD_CASH = "BP-S-HD-CASH      ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_P_INQ_IBAC = "BP-P-INQ-IBAC       ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String K_HIS_REMARKS = "JIE XIAN";
    String CPN_P_IB_IBZQINF = "IB-IBZQINF";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_CNT = 0;
    int WS_INDEX = 0;
    int WS_I = 0;
    int WS_J = 0;
    double WS_GD_AMT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSHDCS BPCSHDCS = new BPCSHDCS();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQIBA BPCPQIBA = new BPCPQIBA();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    IBCQINF IBCQINF = new IBCQINF();
    SCCGWA SCCGWA;
    BPB2100_AWA_2100 BPB2100_AWA_2100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2100_AWA_2100>");
        BPB2100_AWA_2100 = (BPB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSHDCS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CHECK_ORG();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            B030_CHECK_TELLER();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        B030_HISTORY_RECORD();
        B040_OUT_STORE_PROCESS();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P915";
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
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[3-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[4-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[5-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[1-1].P_PVAL);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[1-1].P_NUM);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[2-1].P_PVAL);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[2-1].P_NUM);
        WS_CS_KIND = BPB2100_AWA_2100.CS_KIND;
        B010_03_CHECK_CCY_INFO();
        CEP.TRC(SCCGWA, "B010");
    }
    public void B010_01_CHECK_DETAILS_BESEQ() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ); WS_INFO_CNT += 1) {
            if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT - 1-1].P_PVAL == 0 
                && BPB2100_AWA_2100.P_INFO[WS_INFO_CNT - 1-1].P_NUM == 0 
                && BPB2100_AWA_2100.P_INFO[WS_INFO_CNT - 1-1].P_MFLG == ' ') {
                if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_02_CHECK_DETAILS_SYNCHRO() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ); WS_INFO_CNT += 1) {
            if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL != 0 
                || BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM != 0 
                || BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    CEP.TRC(SCCGWA, "111");
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                CEP.TRC(SCCGWA, "wlq 20110339 start");
                CEP.TRC(SCCGWA, "wlq 20110339 end");
                if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG == ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    CEP.TRC(SCCGWA, "333");
                    WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_INDEX-1].CCY);
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
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    CEP.TRC(SCCGWA, WS_CCY_CNT);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.trim().length() > 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.trim().length() == 0 
                && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
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
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        B030_01_GET_IBAC_INFO();
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
        if (BPCPQBOX.DATA_INFO.PLBOX_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_HAV_CASHLIB;
            S000_ERR_MSG_PROC();
        }
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, "123PQBOX");
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_BR);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.PLBOX_NO);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY);
        S000_CALL_BPZPQBOX();
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        WS_GD_AMT = 0;
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
            && BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            CEP.TRC(SCCGWA, WS_INFO_CNT);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM);
            if (BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM > 0) {
                WS_MATCH_FLAG = 'F';
                for (WS_J = 1; WS_J <= 20 
                    && WS_MATCH_FLAG != 'T'; WS_J += 1) {
                    CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].PAR_VAL);
                    CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL);
                    CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].M_FLG);
                    CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG);
                    if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].PAR_VAL == BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL 
                        && BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].M_FLG == BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG) {
                        WS_MATCH_FLAG = 'T';
                        if (WS_CS_KIND == '0') {
                            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].GD_NUM);
                            CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM);
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].GD_NUM < BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM) {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                                WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_CS_KIND == '2') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].BD_NUM < BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM) {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                                WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_CS_KIND == '3') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].HBD_NUM < BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM) {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                                WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                                S000_ERR_MSG_PROC();
                            }
                        } else {
                        }
                    }
                }
                if (WS_MATCH_FLAG == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    WS_FLD_NO = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_GD_AMT = WS_GD_AMT + BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL * BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM;
            WS_I = WS_INFO_CNT - WS_START_CNT + 1;
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, WS_INFO_CNT);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG);
            BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG;
        }
        BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        CEP.TRC(SCCGWA, "AMOUNT:");
        CEP.TRC(SCCGWA, WS_GD_AMT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
    }
    public void B030_01_GET_IBAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.NOSTRO_CD = BPB2100_AWA_2100.C_SWIFT;
        IBCQINF.INPUT_DATA.CCY = BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_IBZQINF();
        if (IBCQINF.OUTPUT_DATA.AC_STS == 'N') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.AC_STATUS_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_OUT_STORE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_BR);
        BPCSHDCS.CS_KIND = BPB2100_AWA_2100.CS_KIND;
        BPCSHDCS.TO_BANK = BPB2100_AWA_2100.C_SWIFT;
        BPCSHDCS.IN_BR = BPB2100_AWA_2100.TR_BR;
        S000_CALL_BPZSHDCS();
        BPB2100_AWA_2100.MOVE_DT = BPCSHDCS.MOVE_DT;
        BPB2100_AWA_2100.CONF_NO = BPCSHDCS.CONF_NO;
        BPB2100_AWA_2100.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2100_AWA_2100.TR_BR = BPCSHDCS.IN_BR;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2100_AWA_2100.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CCY1 = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].AMT1 = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CASH_TP1 = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.MOVE_DT);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CONF_NO);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_BR);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CCY1);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].AMT1);
            CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CASH_TP1);
        }
    }
    public void S000_CALL_BPZSHDCS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_HD_CASH, BPCSHDCS);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_IB_IBZQINF, IBCQINF);
        CEP.TRC(SCCGWA, IBCQINF.RC);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_STS);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQIBA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQIBA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_IBAC, BPCPQIBA);
        if (BPCPQIBA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQIBA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPQBOX.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
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
        CEP.TRC(SCCGWA, GWA_BP_AREA.VCH_AREA.VCH_CNT);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
