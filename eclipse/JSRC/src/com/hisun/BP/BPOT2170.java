package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2170 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP287";
    String CPN_S_QF_OUT = "BP-S-QF-OUT";
    String CPN_S_QF_IN = "BP-S-QF-IN";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INDEX = 0;
    int WS_I = 0;
    int WS_J = 0;
    double WS_GD_AMT = 0;
    int WS_COUNT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    char WS_CHECK_ORG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSQFOT BPCSQFOT = new BPCSQFOT();
    BPCSQFIN BPCSQFIN = new BPCSQFIN();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCOQFIO BPCOQFIO = new BPCOQFIO();
    SCCGWA SCCGWA;
    BPB2170_AWA_2170 BPB2170_AWA_2170;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2170 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2170_AWA_2170>");
        BPB2170_AWA_2170 = (BPB2170_AWA_2170) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSQFOT);
        IBS.init(SCCGWA, BPCSQFIN);
        WS_CHECK_ORG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPB2170_AWA_2170.MOVE_TYP == '0') {
            B010_CHECK_INPUT();
            B020_CHECK_ORG_FOR_CN();
            B060_GET_PLBOX_NO_FOR_CN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER_OUT();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B040_OUT_STORE_PROCESS_FOR_CN();
        } else {
            B010_CHECK_INPUT();
            B020_CHECK_ORG_FOR_CN();
            B060_GET_PLBOX_NO_FOR_CN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER_IN();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC_LAST();
            B050_IN_STORE_PROCESS_FOR_CN();
        }
        B080_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CS_KIND = BPB2170_AWA_2170.CS_KIND;
        B010_03_CHECK_CCY_INFO();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 2;
                B010_01_CHECK_DETAILS_BESEQ();
                WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
                B010_02_CHECK_DETAILS_SYNCHRO();
            }
        }
    }
    public void B010_01_CHECK_DETAILS_BESEQ() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ); WS_INFO_CNT += 1) {
            if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT - 1-1].P_PVAL == 0 
                && BPB2170_AWA_2170.P_INFO[WS_INFO_CNT - 1-1].P_NUM == 0 
                && BPB2170_AWA_2170.P_INFO[WS_INFO_CNT - 1-1].P_MFLG == ' ') {
                if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_02_CHECK_DETAILS_SYNCHRO() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ); WS_INFO_CNT += 1) {
            if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL != 0 
                || BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM != 0 
                || BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG == ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2170_AWA_2170.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2170_AWA_2170.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT - 1-1].CASH_TYP.equalsIgnoreCase("0") 
                && !BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT - 1-1].CCY_AMT == 0 
                && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
                if (BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
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
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.VB_BR);
        BPCPQBOX.DATA_INFO.CCY = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.CCY);
        S000_CALL_BPZPQBOX();
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.MGR_TLR);
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLIB_MGR;
            WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        B070_CHECK_PVAL_ENOUGH_FOR_CN();
    }
    public void B030_CHECK_TELLER_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2170_AWA_2170.MOVE_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2170_AWA_2170.CONF_NO;
        BPCPQMOV.DATA_INFO.CASH_TYP = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQMOV.DATA_INFO.CCY = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQMOV();
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
            WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.OUT_BR) {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.OUT_BR);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2170_AWA_2170.OUT_BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.TRC(SCCGWA, BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        WS_COUNT = 0;
        WS_GD_AMT = 0;
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
            && BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            WS_GD_AMT = WS_GD_AMT + BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL * BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM;
            WS_I = WS_INFO_CNT - WS_START_CNT + 1;
            BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG;
        }
        if (WS_CS_KIND == '3') {
            WS_GD_AMT = WS_GD_AMT / 2;
        }
        CEP.TRC(SCCGWA, "AMOUNT:");
        CEP.TRC(SCCGWA, WS_GD_AMT);
        CEP.TRC(SCCGWA, BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        if (WS_GD_AMT != BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
            WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
    }
    public void B040_OUT_STORE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        BPCSQFOT.CS_KIND = BPB2170_AWA_2170.CS_KIND;
        BPCSQFOT.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        S000_CALL_BPZSQFOT();
        BPB2170_AWA_2170.MOVE_DT = BPCSQFOT.MOVE_DT;
        BPB2170_AWA_2170.CONF_NO = BPCSQFOT.CONF_NO;
        BPB2170_AWA_2170.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2170_AWA_2170.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B050_IN_STORE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        BPCSQFIN.MOV_DT = BPB2170_AWA_2170.MOVE_DT;
        BPCSQFIN.CONF_NO = BPB2170_AWA_2170.CONF_NO;
        BPCSQFIN.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSQFIN.OUT_TLR = BPCPQMOV.DATA_INFO.OUT_TLR;
        BPCSQFIN.CS_KIND = BPB2170_AWA_2170.CS_KIND;
        BPCSQFIN.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        S000_CALL_BPZSQFIN();
    }
    public void B060_GET_PLBOX_NO_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '1';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            BPRTLVB.PLBOX_TP = '2';
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_HAV_CASHLIB;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B070_CHECK_PVAL_ENOUGH_FOR_CN() throws IOException,SQLException,Exception {
        WS_GD_AMT = 0;
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
            && BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            if (BPRTLVB.PLBOX_TP != '5' 
                && BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM > 0) {
                WS_MATCH_FLAG = 'F';
                for (WS_J = 1; WS_J <= 20 
                    && WS_MATCH_FLAG != 'T'; WS_J += 1) {
                    if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].PAR_VAL == BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL 
                        && BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].M_FLG == BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG) {
                        WS_MATCH_FLAG = 'T';
                        if (WS_CS_KIND == '0') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].GD_NUM < BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM) {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_CS_KIND == '2') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].BD_NUM < BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM) {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                S000_ERR_MSG_PROC();
                            }
                        } else if (WS_CS_KIND == '3') {
                            if (BPCPQBOX.DATA_INFO.CLBI_INF[WS_J-1].HBD_NUM < BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM) {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                                S000_ERR_MSG_PROC();
                            }
                        } else {
                        }
                    }
                }
                if (WS_MATCH_FLAG == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_GD_AMT = WS_GD_AMT + BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL * BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM;
            if (WS_CS_KIND == '3') {
                WS_GD_AMT = WS_GD_AMT / 2;
            }
            WS_I = WS_INFO_CNT - WS_START_CNT + 1;
            BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG;
        }
        CEP.TRC(SCCGWA, WS_GD_AMT);
        if (WS_GD_AMT != BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
            WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY;
    }
    public void B080_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQFIO);
        BPCOQFIO.MOVE_TYP = BPB2170_AWA_2170.MOVE_TYP;
        BPCOQFIO.CS_KIND = BPB2170_AWA_2170.CS_KIND;
        BPCOQFIO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOQFIO.CONF_SEQ = BPB2170_AWA_2170.CONF_NO;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOQFIO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOQFIO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOQFIO.CCY_INFO[WS_CCY_CNT-1].AMT = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        }
        for (WS_INFO_CNT = 1; WS_INFO_CNT <= 60; WS_INFO_CNT += 1) {
            BPCOQFIO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCOQFIO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCOQFIO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPB2170_AWA_2170.P_INFO[WS_INFO_CNT-1].P_MFLG;
        }
        BPCOQFIO.CCY_CNT = WS_CCY_CNT - 1;
        BPCOQFIO.DT_CNT = WS_INFO_CNT - 1;
        CEP.TRC(SCCGWA, BPCOQFIO.DT_CNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOQFIO;
        SCCFMT.DATA_LEN = 1699;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZSQFOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_QF_OUT;
        SCCCALL.COMMAREA_PTR = BPCSQFOT;
        SCCCALL.ERR_FLDNO = BPB2170_AWA_2170.IN_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPQMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_MOVD, BPCPQMOV);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCPQMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQMOV.RC);
            WS_FLD_NO = BPB2170_AWA_2170.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSQFIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_QF_IN;
        SCCCALL.COMMAREA_PTR = BPCSQFIN;
        SCCCALL.ERR_FLDNO = BPB2170_AWA_2170.OUT_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
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
