package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSSYSP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WK_NO = 99990000;
    String WS_ERR_MSG = " ";
    char LNZSSYSP_FILLER2 = ' ';
    int WS_I = 0;
    int WS_J = 0;
    int WS_IDX = 0;
    int WS_K = 0;
    int WS_REC_CNT = 20;
    double WS_PART_TOT = 0;
    double WS_PART_PRIN = 0;
    double WS_PART_INT = 0;
    double WS_PART_PFE = 0;
    double WS_PART_BFE = 0;
    LNZSSYSP_WS_DISB_INFO WS_DISB_INFO = new LNZSSYSP_WS_DISB_INFO();
    LNZSSYSP_WS_PART_DISB_INFO[] WS_PART_DISB_INFO = new LNZSSYSP_WS_PART_DISB_INFO[99];
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_EOF_FLG = ' ';
    LNRDISB LNRDISB = new LNRDISB();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCRDISB LNCRDISB = new LNCRDISB();
    LNCIPART LNCIPART = new LNCIPART();
    SCCGWA SCCGWA;
    LNCSSYSP LNCSSYSP;
    public LNZSSYSP() {
        for (int i=0;i<99;i++) WS_PART_DISB_INFO[i] = new LNZSSYSP_WS_PART_DISB_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCSSYSP LNCSSYSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSYSP = LNCSSYSP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSYSP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        for (WS_J = 1; WS_J <= 99; WS_J += 1) {
            WS_PART_DISB_INFO[WS_J-1].WS_PART_CTA_NO = " ";
            WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO = 0;
            WS_PART_DISB_INFO[WS_J-1].WS_PART_REL_TYP = ' ';
            WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_TOT = 0;
            WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_PRIN = 0;
            WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_INT = 0;
            WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_PFE = 0;
            WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_BFE = 0;
            LNCSSYSP.PART_INFO[WS_J-1].PART_FULL_NM = " ";
            LNCSSYSP.PART_INFO[WS_J-1].PART_CITY_CD = " ";
            LNCSSYSP.PART_INFO[WS_J-1].PART_REL_TY = ' ';
            LNCSSYSP.PART_INFO[WS_J-1].PART_SEQ_NO = 0;
            LNCSSYSP.PART_INFO[WS_J-1].PART_CI_TYP = ' ';
            LNCSSYSP.PART_INFO[WS_J-1].PART_CI_NO = " ";
            LNCSSYSP.PART_INFO[WS_J-1].PART_DISB_TOT = 0;
            LNCSSYSP.PART_INFO[WS_J-1].PART_PRIN = 0;
            LNCSSYSP.PART_INFO[WS_J-1].PART_INT = 0;
            LNCSSYSP.PART_INFO[WS_J-1].PART_PFE = 0;
            LNCSSYSP.PART_INFO[WS_J-1].PART_BFE = 0;
        }
        LNCSSYSP.RC.RC_APP = "LN";
        LNCSSYSP.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if ((LNCSSYSP.FUN_CODE != 'C' 
            && LNCSSYSP.FUN_CODE != 'B')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSSYSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSSYSP.DISB_REF == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APTREF_EMPTY, LNCSSYSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSSYSP.FUN_CODE == 'C') {
            B100_APT_RECORD_CHECK();
            if (pgmRtn) return;
        } else if (LNCSSYSP.FUN_CODE == 'B') {
            B200_APT_RECORD_BRW();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSSYSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_APT_RECORD_CHG() throws IOException,SQLException,Exception {
    }
    public void B100_APT_RECORD_DEL() throws IOException,SQLException,Exception {
    }
    public void B100_APT_RECORD_CHECK() throws IOException,SQLException,Exception {
        WS_EOF_FLG = 'N';
        B101_STARTBR_LNTDISB();
        if (pgmRtn) return;
        B105_READNEXT_LNTDISB();
        if (pgmRtn) return;
        while (WS_EOF_FLG != 'Y' 
            && LNRDISB.KEY.DISB_REF.equalsIgnoreCase(LNCSSYSP.DISB_REF+"")) {
            R000_COMPUTE_PARI_AMT();
            if (pgmRtn) return;
            if (LNRDISB.KEY.CTA_SEQ != 0) {
                R000_GET_COMM_DATA();
                if (pgmRtn) return;
            }
            B105_READNEXT_LNTDISB();
            if (pgmRtn) return;
        }
        B110_ENDBR_LNTDISB();
        if (pgmRtn) return;
        R000_CHECK_AMT_MATCH();
        if (pgmRtn) return;
    }
    public void B200_APT_RECORD_BRW() throws IOException,SQLException,Exception {
        WS_EOF_FLG = 'N';
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B101_STARTBR_LNTDISB();
        if (pgmRtn) return;
        B105_READNEXT_LNTDISB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSSYSP.DISB_REF);
        CEP.TRC(SCCGWA, LNRDISB.KEY.DISB_REF);
        CEP.TRC(SCCGWA, WS_EOF_FLG);
        while (WS_EOF_FLG != 'Y' 
            && LNRDISB.KEY.DISB_REF.equalsIgnoreCase(LNCSSYSP.DISB_REF+"")) {
            R000_COMPUTE_PARI_AMT();
            if (pgmRtn) return;
            B105_READNEXT_LNTDISB();
            if (pgmRtn) return;
        }
        B110_ENDBR_LNTDISB();
        if (pgmRtn) return;
        R000_CHECK_AMT_MATCH();
        if (pgmRtn) return;
        B210_PROCESS_OUTPUT();
        if (pgmRtn) return;
    }
    public void B101_STARTBR_LNTDISB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRDISB);
        IBS.init(SCCGWA, LNCRDISB);
        LNCRDISB.FUNC = 'B';
        LNCRDISB.OPT = 'A';
        LNRDISB.KEY.DISB_REF = "" + LNCSSYSP.DISB_REF;
        JIBS_tmp_int = LNRDISB.KEY.DISB_REF.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRDISB.KEY.DISB_REF = "0" + LNRDISB.KEY.DISB_REF;
        S000_CALL_LNZRDISB();
        if (pgmRtn) return;
    }
    public void B105_READNEXT_LNTDISB() throws IOException,SQLException,Exception {
        LNCRDISB.FUNC = 'B';
        LNCRDISB.OPT = 'R';
        S000_CALL_LNZRDISB();
        if (pgmRtn) return;
    }
    public void B110_ENDBR_LNTDISB() throws IOException,SQLException,Exception {
        LNCRDISB.FUNC = 'B';
        LNCRDISB.OPT = 'E';
        S000_CALL_LNZRDISB();
        if (pgmRtn) return;
    }
    public void B210_PROCESS_OUTPUT() throws IOException,SQLException,Exception {
        R000_GET_PART_INFO();
        if (pgmRtn) return;
        for (WS_J = 1; WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO != 0 
            && WS_J <= 99; WS_J += 1) {
            CEP.TRC(SCCGWA, WS_J);
            CEP.TRC(SCCGWA, WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO);
        }
        for (WS_I = 1; LNCIPART.GROUP.get(WS_I-1).SEQ_NO != 0 
            && WS_I <= LNCIPART.DATA.CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_I-1).PART_FNAME);
            CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_I-1).SEQ_NO);
        }
        for (WS_J = 1; WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO != 0 
            && WS_J <= 99; WS_J += 1) {
            if (WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO != WK_NO) {
                R000_TRANS_DATA_MPAGE_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_PART_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.DATA);
        IBS.init(SCCGWA, LNCIPART.RC);
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'R';
        CEP.TRC(SCCGWA, WS_PART_DISB_INFO[1-1].WS_PART_CTA_NO);
        LNCIPART.DATA.CONTRACT_NO = WS_PART_DISB_INFO[1-1].WS_PART_CTA_NO;
        if (LNCIPART.DATA.CONTRACT_NO.trim().length() > 0) {
            S000_CALL_LNZIPART();
            if (pgmRtn) return;
        }
    }
    public void R000_COMPUTE_PARI_AMT() throws IOException,SQLException,Exception {
        if (LNRDISB.KEY.CTA_SEQ == 0) {
            WS_DISB_INFO.WS_CUST_DISB_PRIN = LNRDISB.D_P_AMT;
            WS_DISB_INFO.WS_CUST_DISB_INT = LNRDISB.D_I_AMT;
            WS_DISB_INFO.WS_CUST_DISB_PFE = LNRDISB.PC_AMT;
            WS_DISB_INFO.WS_CUST_DISB_BFE = LNRDISB.BFC_AMT;
            WS_DISB_INFO.WS_CUST_DISB_TOT = WS_DISB_INFO.WS_CUST_DISB_PRIN + WS_DISB_INFO.WS_CUST_DISB_INT + WS_DISB_INFO.WS_CUST_DISB_PFE + WS_DISB_INFO.WS_CUST_DISB_BFE;
            LNCSSYSP.PART_TSQ_OP.TSQ_PRIN = LNRDISB.D_P_AMT;
            LNCSSYSP.PART_TSQ_OP.TSQ_INT = LNRDISB.D_I_AMT;
            LNCSSYSP.PART_TSQ_OP.TSQ_PFE = LNRDISB.PC_AMT;
            LNCSSYSP.PART_TSQ_OP.TSQ_BFE = LNRDISB.BFC_AMT;
            LNCSSYSP.PART_TSQ_OP.TSQ_TOT = WS_DISB_INFO.WS_CUST_DISB_TOT;
        }
        if (LNRDISB.KEY.CTA_SEQ != 0) {
            WS_IDX = WS_IDX + 1;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_CTA_NO = LNRDISB.KEY.CTA_NO;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_SEQ_NO = LNRDISB.KEY.CTA_SEQ;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_REL_TYP = LNRDISB.PART_ROLE;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_PRIN = LNRDISB.D_P_AMT;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_INT = LNRDISB.D_I_AMT;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_PFE = LNRDISB.PC_AMT;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_BFE = LNRDISB.BFC_AMT;
            WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_TOT = WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_PRIN + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_INT + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_PFE + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_BFE;
            if (LNRDISB.PART_ROLE != 'R') {
                WS_PART_PRIN = WS_PART_PRIN + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_PRIN;
                WS_PART_INT = WS_PART_INT + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_INT;
                WS_PART_BFE = WS_PART_BFE + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_BFE;
                WS_PART_PFE = WS_PART_PFE + WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_PFE;
                WS_PART_TOT = WS_PART_PRIN + WS_PART_INT + WS_PART_PFE + WS_PART_BFE;
            }
        }
    }
    public void R000_CHECK_AMT_MATCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DISB_INFO.WS_CUST_DISB_PRIN);
        CEP.TRC(SCCGWA, WS_DISB_INFO.WS_CUST_DISB_INT);
        CEP.TRC(SCCGWA, WS_DISB_INFO.WS_CUST_DISB_PFE);
        CEP.TRC(SCCGWA, WS_DISB_INFO.WS_CUST_DISB_BFE);
        CEP.TRC(SCCGWA, WS_DISB_INFO.WS_CUST_DISB_TOT);
        CEP.TRC(SCCGWA, WS_PART_PRIN);
        CEP.TRC(SCCGWA, WS_PART_INT);
        CEP.TRC(SCCGWA, WS_PART_PFE);
        CEP.TRC(SCCGWA, WS_PART_BFE);
        CEP.TRC(SCCGWA, WS_PART_TOT);
        if (WS_DISB_INFO.WS_CUST_DISB_PRIN != WS_PART_PRIN 
            || WS_DISB_INFO.WS_CUST_DISB_INT != WS_PART_INT 
            || WS_DISB_INFO.WS_CUST_DISB_PFE != WS_PART_PFE 
            || WS_DISB_INFO.WS_CUST_DISB_BFE != WS_PART_BFE 
            || WS_DISB_INFO.WS_CUST_DISB_TOT != WS_PART_TOT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.AMT_NOT_EQUAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 303;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        R000_TRANSFER_OUTPUT_DATA();
        if (pgmRtn) return;
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, LNCSSYSP.PART_TSQ_OP);
        SCCMPAG.DATA_LEN = 303;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_COMM_DATA() throws IOException,SQLException,Exception {
        WS_K = WS_K + 1;
        LNCSSYSP.PART_INFO[WS_K-1].PART_SEQ_NO = LNRDISB.KEY.CTA_SEQ;
        LNCSSYSP.PART_INFO[WS_K-1].PART_REL_TY = LNRDISB.PART_ROLE;
        LNCSSYSP.PART_INFO[WS_K-1].PART_CI_TYP = LNRDISB.LENDER_TYP;
        LNCSSYSP.PART_INFO[WS_K-1].PART_PRIN = LNRDISB.D_P_AMT;
        LNCSSYSP.PART_INFO[WS_K-1].PART_INT = LNRDISB.D_I_AMT;
        LNCSSYSP.PART_INFO[WS_K-1].PART_PFE = LNRDISB.PC_AMT;
        LNCSSYSP.PART_INFO[WS_K-1].PART_BFE = LNRDISB.BFC_AMT;
        if (LNCSSYSP.PART_INFO[WS_K-1].PART_REL_TY != 'R') {
            LNCSSYSP.PART_INFO[WS_K-1].PART_DISB_TOT = LNCSSYSP.PART_INFO[WS_K-1].PART_PRIN + LNCSSYSP.PART_INFO[WS_K-1].PART_INT + LNCSSYSP.PART_INFO[WS_K-1].PART_PFE + LNCSSYSP.PART_INFO[WS_K-1].PART_BFE;
        } else {
            LNCSSYSP.PART_INFO[WS_K-1].PART_DISB_TOT = LNCSSYSP.PART_INFO[WS_K-1].PART_INT + LNCSSYSP.PART_INFO[WS_K-1].PART_PFE + LNCSSYSP.PART_INFO[WS_K-1].PART_BFE;
        }
    }
    public void R000_TRANSFER_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSYSP.PART_TSQ_OP);
        R000_GET_PARTI_NAME();
        if (pgmRtn) return;
        LNCSSYSP.PART_TSQ_OP.TSQ_FULL_NM = LNCIPART.GROUP.get(WS_I - 1-1).PART_FNAME;
        LNCSSYSP.PART_TSQ_OP.TSQ_CITY_CD = LNCIPART.GROUP.get(WS_I - 1-1).PART_CITY_CD;
        LNCSSYSP.PART_TSQ_OP.TSQ_REL_TY = WS_PART_DISB_INFO[WS_J-1].WS_PART_REL_TYP;
        LNCSSYSP.PART_TSQ_OP.TSQ_PRIN = WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_PRIN;
        LNCSSYSP.PART_TSQ_OP.TSQ_INT = WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_INT;
        LNCSSYSP.PART_TSQ_OP.TSQ_PFE = WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_PFE;
        LNCSSYSP.PART_TSQ_OP.TSQ_BFE = WS_PART_DISB_INFO[WS_J-1].WS_PART_DISB_BFE;
        LNCSSYSP.PART_TSQ_OP.TSQ_TOT = LNCSSYSP.PART_TSQ_OP.TSQ_PRIN + LNCSSYSP.PART_TSQ_OP.TSQ_INT + LNCSSYSP.PART_TSQ_OP.TSQ_PFE + LNCSSYSP.PART_TSQ_OP.TSQ_BFE;
        R000_GET_LOCAL_BK();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            for (WS_IDX = 1; WS_IDX <= 99; WS_IDX += 1) {
                if (WS_PART_DISB_INFO[WS_IDX-1].WS_PART_SEQ_NO == WK_NO) {
                    LNCSSYSP.PART_TSQ_OP.TSQ_INT_FEE = WS_PART_DISB_INFO[WS_IDX-1].WS_PART_DISB_INT;
                    WS_IDX = WS_IDX + 99;
                }
            }
        }
    }
    public void R000_GET_PARTI_NAME() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = 'N';
        for (WS_I = 1; LNCIPART.GROUP.get(WS_I-1).SEQ_NO != 0 
            && WS_I <= LNCIPART.DATA.CNT 
            && WS_FOUND_FLG != 'Y'; WS_I += 1) {
            if (LNCIPART.GROUP.get(WS_I-1).SEQ_NO == WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO) {
                WS_FOUND_FLG = 'Y';
            }
        }
    }
    public void R000_GET_LOCAL_BK() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = 'N';
        for (WS_I = 1; LNCIPART.GROUP.get(WS_I-1).SEQ_NO != 0 
            && WS_I <= LNCIPART.DATA.CNT 
            && WS_FOUND_FLG != 'Y'; WS_I += 1) {
            if (LNCIPART.GROUP.get(WS_I-1).SEQ_NO == WS_PART_DISB_INFO[WS_J-1].WS_PART_SEQ_NO 
                && LNCIPART.GROUP.get(WS_I-1).LC_BK_FLG == 'Y') {
                WS_FOUND_FLG = 'Y';
            }
        }
        WS_I = WS_I - 1;
    }
    public void S000_CALL_LNZRDISB() throws IOException,SQLException,Exception {
        LNCRDISB.REC_PTR = LNRDISB;
        LNCRDISB.REC_LEN = 378;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTDISB", LNCRDISB);
        if (LNCRDISB.RETURN_INFO == 'E') {
            WS_EOF_FLG = 'Y';
        } else {
            if (LNCRDISB.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRDISB.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        CEP.TRC(SCCGWA, LNCIPART.RC);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
