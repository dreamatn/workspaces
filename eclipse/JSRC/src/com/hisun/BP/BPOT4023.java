package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4023 {
    BPCSMVHS_ITM ITM;
    String CPN_S_MAINT_VCHS = "BP-S-MAINT-VCHS     ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short K_MAX_CASE_SEQ = 99;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    int WS_CHK_DATE = 0;
    BPOT4023_WS_CASE_SEQ[] WS_CASE_SEQ = new BPOT4023_WS_CASE_SEQ[99];
    char WS_SORT_IND = ' ';
    char WS_SEQ_FND_FLG = ' ';
    char WS_SEQ_DUP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMVHS BPCSMVHS = new BPCSMVHS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB4000_AWA_4000 BPB4000_AWA_4000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPOT4023() {
        for (int i=0;i<99;i++) WS_CASE_SEQ[i] = new BPOT4023_WS_CASE_SEQ();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4023 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4000_AWA_4000>");
        BPB4000_AWA_4000 = (BPB4000_AWA_4000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CHK_DATE = BPB4000_AWA_4000.EFF_DATE;
        WS_FLD_NO = BPB4000_AWA_4000.EFF_DATE_NO;
        R000_CHECK_DATE();
        WS_CHK_DATE = BPB4000_AWA_4000.EXP_DATE;
        WS_FLD_NO = BPB4000_AWA_4000.EXP_DATE_NO;
        R000_CHECK_DATE();
        WS_SORT_IND = BPB4000_AWA_4000.SORT_IND;
        if (!(WS_SORT_IND == 'Y' 
            || WS_SORT_IND == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCHS_SORT_IND_ERR;
            WS_FLD_NO = BPB4000_AWA_4000.SORT_IND_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4000_AWA_4000.CNT <= 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNT_MUST_GT_ZERO;
            WS_FLD_NO = BPB4000_AWA_4000.CNT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4000_AWA_4000.CNT > K_MAX_CASE_SEQ) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCHS_CNT_EXCEED_MAX;
            WS_FLD_NO = BPB4000_AWA_4000.CNT_NO;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        for (WS_I = 1; WS_I <= BPB4000_AWA_4000.CNT; WS_I += 1) {
            if (BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SEQ_ZERO;
                WS_FLD_NO = BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ_NO;
                S000_ERR_MSG_PROC();
            }
            WS_CASE_SEQ[WS_I-1].WS_ORG_SEQ = BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ;
            for (WS_J = 1; WS_J != WS_I; WS_J += 1) {
                if (BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ == WS_CASE_SEQ[WS_J-1].WS_ORG_SEQ) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SEQ_DUP;
                    WS_FLD_NO = BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_SEQ_FND_FLG = 'N';
            for (WS_J = 1; WS_J <= BPB4000_AWA_4000.CNT 
                && WS_SEQ_FND_FLG != 'Y'; WS_J += 1) {
                if (BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ == BPB4000_AWA_4000.SEQ[WS_J-1].DST_SEQ) {
                    WS_SEQ_FND_FLG = 'Y';
                }
            }
            if (WS_SEQ_FND_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DST_SEQ_NOTFND;
                WS_FLD_NO = BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        BPCSMVHS.FUNC = 'U';
        BPCSMVHS.OUTPUT_FLG = 'Y';
        BPCSMVHS.AP_MMO = BPB4000_AWA_4000.AP_MMO;
        BPCSMVHS.TR_CD = BPB4000_AWA_4000.TR_CD;
        BPCSMVHS.CASE_NO = BPB4000_AWA_4000.CASE_NO;
        BPCSMVHS.EFF_DATE = BPB4000_AWA_4000.EFF_DATE;
        BPCSMVHS.EXP_DATE = BPB4000_AWA_4000.EXP_DATE;
        BPCSMVHS.DATA_TEXT.DESC = BPB4000_AWA_4000.DESC;
        BPCSMVHS.DATA_TEXT.SORT_IND = BPB4000_AWA_4000.SORT_IND;
        BPCSMVHS.DATA_TEXT.CNT = BPB4000_AWA_4000.CNT;
        ITM = new BPCSMVHS_ITM();
        BPCSMVHS.DATA_TEXT.INFO.ITM.add(ITM);
        for (WS_I = 1; WS_I <= BPCSMVHS.DATA_TEXT.CNT; WS_I += 1) {
            ITM.ORG_SEQ = BPB4000_AWA_4000.SEQ[WS_I-1].ORG_SEQ;
            ITM.DST_SEQ = BPB4000_AWA_4000.SEQ[WS_I-1].DST_SEQ;
        }
        S000_CALL_BPZSMVHS();
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_CHK_DATE;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSMVHS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAINT_VCHS, BPCSMVHS);
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
