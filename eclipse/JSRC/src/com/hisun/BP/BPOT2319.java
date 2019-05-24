package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2319 {
    String CPN_S_C_SUS_WRITE_OFF = "BP-S-C-SUS-WRITE-OFF";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCSWO BPCSCSWO = new BPCSCSWO();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB2300_AWA_2300 BPB2300_AWA_2300;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2319 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2300_AWA_2300>");
        BPB2300_AWA_2300 = (BPB2300_AWA_2300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCSWO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CHECK_ORG();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPB2300_AWA_2300.BOX_FLG == '1' 
                || BPB2300_AWA_2300.BOX_FLG == '2') {
                B030_CHECK_TELLER();
            }
        } else {
            if (BPB2300_AWA_2300.BOX_FLG == '1' 
                || BPB2300_AWA_2300.BOX_FLG == '2') {
                B030_CHECK_TELLER();
            }
        }
        B040_SUSP_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CS_KIND = BPB2300_AWA_2300.CS_KIND;
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        if (!BPB2300_AWA_2300.TR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2300_AWA_2300.TR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = 1; WS_INFO_CNT <= 12 
            && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            BPCSCSWO.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_VAL = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSCSWO.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_NUM = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSCSWO.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_MFLG = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG;
            BPCSCSWO.DT_CNT = WS_INFO_CNT;
        }
    }
    public void B040_SUSP_PROC() throws IOException,SQLException,Exception {
        BPCSCSWO.CS_KIND = BPB2300_AWA_2300.CS_KIND;
        BPCSCSWO.ML_OPT = BPB2300_AWA_2300.ML_OPT;
        BPCSCSWO.BOX_FLG = BPB2300_AWA_2300.BOX_FLG;
        BPCSCSWO.ACCT_CODE = BPB2300_AWA_2300.ACCT_CD;
        BPCSCSWO.CASH_TYP = BPB2300_AWA_2300.CASH_TYP;
        BPCSCSWO.CCY = BPB2300_AWA_2300.CCY;
        BPCSCSWO.TOTAL_AMT = BPB2300_AWA_2300.AMT;
        BPCSCSWO.METHOD = BPB2300_AWA_2300.METHOD;
        BPCSCSWO.BR = BPB2300_AWA_2300.TRANS_BR;
        BPCSCSWO.AC_NO = BPB2300_AWA_2300.AC_NO;
        BPCSCSWO.OTH_ACCT_CD = BPB2300_AWA_2300.TRA_ACCT;
        BPCSCSWO.REV_NO = BPB2300_AWA_2300.REV_NO;
        BPCSCSWO.AC_TYPE = BPB2300_AWA_2300.AC_TYPE;
        BPCSCSWO.SUSP_TYPE = BPB2300_AWA_2300.SUSP_TYP;
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.AMT);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.AMT);
        S000_CALL_BPZSCSWO();
    }
    public void S000_CALL_BPZSCSWO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_C_SUS_WRITE_OFF, BPCSCSWO);
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
