package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2300 {
    int JIBS_tmp_int;
    String CPN_S_CASH_SUSPENSE = "BP-S-CASH-SUSPENSE  ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_I = 0;
    double WS_GD_AMT = 0;
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCSSP BPCSCSSP = new BPCSCSSP();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPB2300_AWA_2300 BPB2300_AWA_2300;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2300 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2300_AWA_2300>");
        BPB2300_AWA_2300 = (BPB2300_AWA_2300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCSSP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            B020_CHECK_TLR_FOR_CN();
            if (BPB2300_AWA_2300.BOX_FLG == '1' 
                || BPB2300_AWA_2300.BOX_FLG == '2') {
                B030_DATA_TRANSFER();
            }
            B040_DRAW_CASH_PROC_FOR_CN();
        } else {
            B010_CHECK_INPUT();
            B020_CHECK_TLR();
            if (BPB2300_AWA_2300.BOX_FLG == '1' 
                || BPB2300_AWA_2300.BOX_FLG == '2') {
                B030_DATA_TRANSFER();
            }
            B040_DRAW_CASH_PROC();
        }
        BPB2300_AWA_2300.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2300_AWA_2300.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2300_AWA_2300.BOX_FLG == '1' 
            || BPB2300_AWA_2300.BOX_FLG == '2') {
            B010_01_CHECK_DETAILS_BESEQ();
            B010_02_CHECK_DETAILS_SYNCHRO();
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB2300_AWA_2300.BOX_FLG == '1' 
            || BPB2300_AWA_2300.BOX_FLG == '2') {
            B010_01_CHECK_DETAILS_BESEQ();
            B010_02_CHECK_DETAILS_SYNCHRO();
        }
    }
    public void B010_01_CHECK_DETAILS_BESEQ() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = 2; WS_INFO_CNT <= 12; WS_INFO_CNT += 1) {
            if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT - 1-1].P_PVAL == 0 
                && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT - 1-1].P_NUM == 0 
                && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT - 1-1].P_MFLG == ' ') {
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_02_CHECK_DETAILS_SYNCHRO() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = 1; WS_INFO_CNT <= 12; WS_INFO_CNT += 1) {
            if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0 
                || BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM != 0 
                || BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL == 0) {
                    CEP.TRC(SCCGWA, "AWA-P-PVAL(WS-INFO-CNT)");
                    CEP.TRC(SCCGWA, WS_INFO_CNT);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG == ' ') {
                    CEP.TRC(SCCGWA, "AWA-P-MFLG(WS-INFO-CNT)");
                    CEP.TRC(SCCGWA, WS_INFO_CNT);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPB2300_AWA_2300.BOX_FLG == '1' 
            || BPB2300_AWA_2300.BOX_FLG == '2') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB2300_AWA_2300.BOX_FLG == '3' 
            || BPB2300_AWA_2300.BOX_FLG == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_CHECK_TLR_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPB2300_AWA_2300.BOX_FLG == '1' 
            || BPB2300_AWA_2300.BOX_FLG == '2' 
            || BPB2300_AWA_2300.BOX_FLG == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB2300_AWA_2300.BOX_FLG == '3' 
            || BPB2300_AWA_2300.BOX_FLG == '4' 
            || BPB2300_AWA_2300.BOX_FLG == '6') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_DATA_TRANSFER() throws IOException,SQLException,Exception {
        WS_GD_AMT = 0;
        for (WS_INFO_CNT = 1; WS_INFO_CNT <= 12 
            && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            WS_GD_AMT = WS_GD_AMT + BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL * BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSCSSP.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_VAL = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSCSSP.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_NUM = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSCSSP.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_MFLG = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG;
            BPCSCSSP.CNT = WS_INFO_CNT;
        }
    }
    public void B040_DRAW_CASH_PROC_FOR_CN() throws IOException,SQLException,Exception {
        BPCSCSSP.SUSP_TYPE = BPB2300_AWA_2300.SUSP_TYP;
        CEP.TRC(SCCGWA, BPCSCSSP.SUSP_TYPE);
        BPCSCSSP.TOTAL_AMT = BPB2300_AWA_2300.AMT;
        BPCSCSSP.CASH_TYP = BPB2300_AWA_2300.CASH_TYP;
        BPCSCSSP.CCY = BPB2300_AWA_2300.CCY;
        BPCSCSSP.ML_OPT = BPB2300_AWA_2300.ML_OPT;
        BPCSCSSP.BOX_FLG = BPB2300_AWA_2300.BOX_FLG;
        BPCSCSSP.CS_KIND = BPB2300_AWA_2300.CS_KIND;
        BPCSCSSP.REMARK = BPB2300_AWA_2300.REMARK;
        BPCSCSSP.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZSCSSP();
    }
    public void B040_DRAW_CASH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.AMT);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.CCY);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.ML_OPT);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.BOX_FLG);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.CS_KIND);
        BPCSCSSP.TOTAL_AMT = BPB2300_AWA_2300.AMT;
        BPCSCSSP.CASH_TYP = BPB2300_AWA_2300.CASH_TYP;
        BPCSCSSP.CCY = BPB2300_AWA_2300.CCY;
        BPCSCSSP.ML_OPT = BPB2300_AWA_2300.ML_OPT;
        BPCSCSSP.BOX_FLG = BPB2300_AWA_2300.BOX_FLG;
        BPCSCSSP.CS_KIND = BPB2300_AWA_2300.CS_KIND;
        BPCSCSSP.REMARK = BPB2300_AWA_2300.REMARK;
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.AMT);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.REMARK);
        CEP.TRC(SCCGWA, BPCSCSSP.REMARK);
        CEP.TRC(SCCGWA, BPCSCSSP);
        S000_CALL_BPZSCSSP();
    }
    public void S000_CALL_BPZSCSSP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASH_SUSPENSE, BPCSCSSP);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = 0;
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
