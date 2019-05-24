package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPBL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN";
    String K_CPN_R_BPZRMBPM = "BP-R-MBRW-PARM";
    String K_HIS_COPYBOOK_NAME = "BPRPBL";
    String K_PARM_TYPE = "AMPBL";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP213";
    String K_HIS_REMARKS = "PUBLIC BOOK LEDGER MAINTENACNE";
    String K_AMACM_TYPE = "AMACM";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    int WS_CNT1 = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_L = 0;
    short WS_K = 0;
    short WS_SEQ_CNT = 0;
    String WS_ACMOD_NAME = " ";
    String WS_CNTR_TYPE1 = " ";
    String WS_PROD_TYPE1 = " ";
    int WS_BR1 = 0;
    String WS_MOD_NO_OLD = " ";
    String WS_MOD_NO_NEW = " ";
    String WS_MOD_NO_TMP = " ";
    short WS_REQID1 = 0;
    short WS_REQID2 = 0;
    char WS_RUN_TYP = ' ';
    BPZSPBL_WS_HISO_AMT_PNT WS_HISO_AMT_PNT = new BPZSPBL_WS_HISO_AMT_PNT();
    BPZSPBL_WS_HISN_AMT_PNT WS_HISN_AMT_PNT = new BPZSPBL_WS_HISN_AMT_PNT();
    BPZSPBL_WS_OUTPUT_KEY WS_OUTPUT_KEY = new BPZSPBL_WS_OUTPUT_KEY();
    BPZSPBL_WS_OUTPUT_DETAIL WS_OUTPUT_DETAIL = new BPZSPBL_WS_OUTPUT_DETAIL();
    BPZSPBL_WS_KEY WS_KEY = new BPZSPBL_WS_KEY();
    BPZSPBL_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSPBL_WS_OUTPUT_DATA();
    BPZSPBL_WS_OUTPUT_BRW WS_OUTPUT_BRW = new BPZSPBL_WS_OUTPUT_BRW();
    BPZSPBL_WS_ACM_KEY WS_ACM_KEY = new BPZSPBL_WS_ACM_KEY();
    BPZSPBL_WS_ACM_VAL WS_ACM_VAL = new BPZSPBL_WS_ACM_VAL();
    char WS_DATA_FLG = ' ';
    char WS_FUNC_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_FOUND_FLG2 = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPBL BPRPBL = new BPRPBL();
    BPRACM BPRACM = new BPRACM();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHPBL BPCHPBL = new BPCHPBL();
    BPCHPBL BPCHPBLO = new BPCHPBL();
    BPCHPBL BPCHPBLN = new BPCHPBL();
    BPCOPBL BPCOPBL = new BPCOPBL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQITM AICQITM = new AICPQITM();
    AICSCMIB AICSCMIB = new AICSCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    SCCGWA SCCGWA;
    BPCSPBL BPCSPBL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSPBL BPCSPBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPBL = BPCSPBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, BPCHPBLO);
        IBS.init(SCCGWA, BPCHPBLN);
        IBS.init(SCCGWA, BPCOPBL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPBL.KEY);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSPBL.KEY);
        if (BPCSPBL.FUNC == 'Q') {
            B010_QUERY_PBL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPBL.FUNC == 'A') {
            B020_ADD_PBL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPBL.FUNC == 'U') {
            B030_UPDATE_PBL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPBL.FUNC == 'D') {
            B040_DELETE_PBL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPBL.FUNC == 'B') {
            B050_BROWSE_PBL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPBL.FUNC == 'T') {
            B060_DELETE_PBL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPBL.FUNC == 'I') {
            B070_QUERY_PBL_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSPBL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPBL.FUNC);
        CEP.TRC(SCCGWA, BPCSPBL.KEY.MOD_NO);
        CEP.TRC(SCCGWA, BPCSPBL.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, BPCSPBL.DATA.CNTR_TYP);
        CEP.TRC(SCCGWA, BPCSPBL.DATA.PROD_TYP);
        CEP.TRC(SCCGWA, BPCSPBL.DATA.BR);
        if (BPCSPBL.FUNC == 'Q' 
            || BPCSPBL.FUNC == 'A' 
            || BPCSPBL.FUNC == 'U' 
            || BPCSPBL.FUNC == 'I' 
            || BPCSPBL.FUNC == 'T' 
            || BPCSPBL.FUNC == 'D') {
            if (BPCSPBL.KEY.MOD_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSPBL.KEY.BOOK_FLG.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSPBL.FUNC == 'Q' 
            || BPCSPBL.FUNC == 'U' 
            || BPCSPBL.FUNC == 'T') {
            if (BPCSPBL.CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNT_MUST_GT_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSPBL.KEY.BOOK_FLG);
        if ((BPCSPBL.FUNC == 'A' 
            || BPCSPBL.FUNC == 'U') 
            && BPCSPBL.DATA.ITM_PNT.equalsIgnoreCase("S")) {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = BPCSPBL.DATA.ITM_NO;
            AICQITM.INPUT_DATA.BOOK_FLG = "BK001";
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.STS != 'A') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_STS_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && BPCSPBL.DATA.ITM_SEQ == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && BPCSPBL.DATA.ITM_SEQ != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N' 
                && BPCSPBL.DATA.ITM_SEQ != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_N_INVALID_SEQ);
            }
            if ((BPCSPBL.DATA.ITM_SEQ != 0) 
                && (BPCSPBL.DATA.ITM_SEQ != ' ')) {
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AICRCMIB.FUNC = 'F';
                AIRCMIB.KEY.GL_BOOK = "BK001";
                AIRCMIB.KEY.ITM = BPCSPBL.DATA.ITM_NO;
                AIRCMIB.KEY.SEQ = BPCSPBL.DATA.ITM_SEQ;
                AIRCMIB.KEY.BR = 999999;
                AICRCMIB.POINTER = AIRCMIB;
                AICRCMIB.REC_LEN = 407;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AIRCMIB.STS == 'P' 
                    || AIRCMIB.STS == 'S' 
                    || AIRCMIB.STS == 'H') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_QUERY_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        BPCPRMM.FUNC = '3';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PBL_CODE_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        CEP.TRC(SCCGWA, WS_KEY);
        CEP.TRC(SCCGWA, BPCSPBL.EFF_DT);
        BPCPRMM.FUNC = '3';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "DATA NOT FOUND");
            BPCSPBL.CNT = 1;
            CEP.TRC(SCCGWA, BPCSPBL.CNT);
            WS_SEQ_CNT = BPCSPBL.CNT;
            WS_RUN_TYP = BPCSPBL.RUN_TYP;
            R000_INPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCPRMM.FUNC = '0';
            S010_CALL_BPZPRMM();
            if (pgmRtn) return;
            if (BPCPRMM.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            S020_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
            BPCSPBL.CNT = BPRPBL.TEXT.CNT;
            BPCSPBL.CNT = (short) (BPCSPBL.CNT + 1);
            WS_FUNC_FLG = 'A';
            CEP.TRC(SCCGWA, BPCSPBL.CNT);
            BPCSPBL.FUNC = 'U';
            B030_UPDATE_PBL_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCHPBLO);
        IBS.init(SCCGWA, BPCHPBLN);
        WS_MOD_NO_NEW = BPCSPBL.KEY.MOD_NO;
        WS_MOD_NO_TMP = BPCSPBL.KEY.MOD_NO;
        R000_GET_ACMOD_INF();
        if (pgmRtn) return;
        BPCHPBLN.CNTR_TYPE = WS_CNTR_TYPE1;
        BPCHPBLN.PROD_TYPE = WS_PROD_TYPE1;
        BPCHPBLN.ACCT_CTR = WS_BR1;
        BPCHPBLN.BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPCHPBLN.ITM_PNT = BPCSPBL.DATA.ITM_PNT;
        BPCHPBLN.ITM_NO = BPCSPBL.DATA.ITM_NO;
        BPCHPBLN.ITM_SEQ = BPCSPBL.DATA.ITM_SEQ;
        BPCHPBLN.REMARK = BPCSPBL.DATA.REMARK;
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCHPBLN.AMT_PNT[WS_I-1].AMT_FLG = BPCSPBL.DATA.AMT_PNT[WS_I-1].AMT_FLG;
            WS_HISN_AMT_PNT.WS_HISO_AMT_NEW[WS_I-1].WS_AMT_FLG_NEW = BPCSPBL.DATA.AMT_PNT[WS_I-1].AMT_FLG;
        }
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        BPCPRMM.FUNC = '4';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRPBL.TEXT.CNT);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, WS_KEY);
        WS_MOD_NO_OLD = WS_KEY.WS_MOD_NO;
        WS_MOD_NO_TMP = WS_KEY.WS_MOD_NO;
        CEP.TRC(SCCGWA, WS_KEY.WS_MOD_NO);
        R000_GET_ACMOD_INF();
        if (pgmRtn) return;
        BPCHPBLO.CNTR_TYPE = WS_CNTR_TYPE1;
        BPCHPBLO.PROD_TYPE = WS_PROD_TYPE1;
        BPCHPBLO.ACCT_CTR = WS_BR1;
        BPCHPBLO.BOOK_FLG = WS_KEY.WS_BOOK_FLG;
        CEP.TRC(SCCGWA, BPRPBL.TEXT.CNT);
        CEP.TRC(SCCGWA, BPCSPBL.CNT);
        CEP.TRC(SCCGWA, BPCSPBL.DATA.ITM_PNT);
        WS_FOUND_FLG2 = 'F';
        for (WS_I = 1; WS_I <= BPRPBL.TEXT.CNT 
            && WS_FOUND_FLG2 != 'N'; WS_I += 1) {
            if (BPCSPBL.DATA.ITM_PNT.equalsIgnoreCase(BPRPBL.TEXT.DATA[WS_I-1].ITM_PNT) 
                && BPCSPBL.DATA.ITM_NO.equalsIgnoreCase(BPRPBL.TEXT.DATA[WS_I-1].ITM_NO)) {
                CEP.TRC(SCCGWA, WS_I);
                BPCSPBL.CNT = WS_I;
                WS_FOUND_FLG2 = 'N';
            }
        }
        BPCHPBLO.ITM_PNT = BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].ITM_PNT;
        BPCHPBLO.ITM_NO = BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].ITM_NO;
        BPCHPBLO.ITM_SEQ = BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].ITM_SEQ;
        BPCHPBLO.REMARK = BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].REMARK;
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCHPBLO.AMT_PNT[WS_I-1].AMT_FLG = BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].AMT_PNT[WS_I-1].AMT_FLG;
            WS_HISO_AMT_PNT.WS_HISO_AMT_OLD[WS_I-1].WS_AMT_FLG_OLD = BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].AMT_PNT[WS_I-1].AMT_FLG;
        }
        WS_SEQ_CNT = BPRPBL.TEXT.CNT;
        CEP.TRC(SCCGWA, WS_SEQ_CNT);
        CEP.TRC(SCCGWA, WS_MOD_NO_OLD);
        CEP.TRC(SCCGWA, WS_MOD_NO_NEW);
        CEP.TRC(SCCGWA, BPCHPBLO.ITM_SEQ);
        CEP.TRC(SCCGWA, BPCHPBLN.ITM_SEQ);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_HISO_AMT_PNT);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_HISN_AMT_PNT);
        if (WS_MOD_NO_OLD.equalsIgnoreCase(WS_MOD_NO_NEW) 
            && BPCHPBLO.BOOK_FLG.equalsIgnoreCase(BPCHPBLN.BOOK_FLG) 
            && BPCHPBLO.ITM_PNT.equalsIgnoreCase(BPCHPBLN.ITM_PNT) 
            && BPCHPBLO.ITM_NO.equalsIgnoreCase(BPCHPBLN.ITM_NO) 
            && BPCHPBLO.ITM_SEQ == BPCHPBLN.ITM_SEQ 
            && BPCHPBLO.REMARK.equalsIgnoreCase(BPCHPBLN.REMARK) 
            && JIBS_tmp_str[2].equalsIgnoreCase(JIBS_tmp_str[1])) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_NOT_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '2';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        BPCPRMM.FUNC = '4';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '1';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        BPCSPBL.CNT = 1;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 100;
        SCCMPAG.SCR_ROW_CNT = 10;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSPBL.DATA.CNTR_TYP);
        CEP.TRC(SCCGWA, BPCSPBL.DATA.PROD_TYP);
        CEP.TRC(SCCGWA, BPCSPBL.DATA.BR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMB);
        BPRPRMT.KEY.TYP = K_AMACM_TYPE;
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        WS_REQID1 = BPCPRMB.REQID;
        CEP.TRC(SCCGWA, " START BR");
        CEP.TRC(SCCGWA, BPCPRMB.REQID);
        CEP.TRC(SCCGWA, WS_REQID1);
        BPCPRMB.FUNC = '1';
        BPCPRMB.REQID = WS_REQID1;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        while (WS_FOUND_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, WS_ACM_VAL);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.KEY.CD, WS_ACM_KEY);
            CEP.TRC(SCCGWA, WS_ACM_VAL.WS_MOD_NO_AC);
            CEP.TRC(SCCGWA, WS_ACM_KEY);
            CEP.TRC(SCCGWA, WS_ACM_VAL);
            CEP.TRC(SCCGWA, BPCSPBL.DATA.CNTR_TYP);
            CEP.TRC(SCCGWA, WS_ACM_KEY.WS_CNTR_TYPE);
            CEP.TRC(SCCGWA, BPCSPBL.DATA.PROD_TYP);
            CEP.TRC(SCCGWA, WS_ACM_KEY.WS_PROD_TYPE);
            CEP.TRC(SCCGWA, BPCSPBL.DATA.BR);
            CEP.TRC(SCCGWA, WS_ACM_KEY.WS_BR_AC);
            CEP.TRC(SCCGWA, BPCSPBL.KEY);
            if ((BPCSPBL.DATA.CNTR_TYP.trim().length() == 0 
                || WS_ACM_KEY.WS_CNTR_TYPE.equalsIgnoreCase(BPCSPBL.DATA.CNTR_TYP)) 
                && (BPCSPBL.DATA.PROD_TYP.trim().length() == 0 
                || WS_ACM_KEY.WS_PROD_TYPE.equalsIgnoreCase(BPCSPBL.DATA.PROD_TYP)) 
                && (BPCSPBL.DATA.BR == 0 
                || WS_ACM_KEY.WS_BR_AC == BPCSPBL.DATA.BR)) {
                BPCSPBL.KEY.MOD_NO = WS_ACM_VAL.WS_MOD_NO_AC;
                B050_02_BROWSE_PBL_PROCESS();
                if (pgmRtn) return;
            }
            BPCPRMB.FUNC = '1';
            CEP.TRC(SCCGWA, "READ-MIDDLE");
            BPCPRMB.REQID = WS_REQID1;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "READ-AFTER");
        }
        BPCPRMB.FUNC = '2';
        BPCPRMB.REQID = WS_REQID1;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        if (WS_CNT1 == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_01_BROWSE_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 203;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        BPCPRMM.FUNC = '3';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUTPUT_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, WS_KEY);
        WS_OUTPUT_KEY.WS_OPBL_MOD_NO = WS_KEY.WS_MOD_NO;
        WS_MOD_NO_TMP = WS_KEY.WS_MOD_NO;
        R000_GET_ACMOD_INF();
        if (pgmRtn) return;
        WS_OUTPUT_KEY.WS_OPBL_MOD_NAME = WS_ACMOD_NAME;
        WS_OUTPUT_KEY.WS_OPBL_CNTR_TYP = WS_CNTR_TYPE1;
        if (WS_PROD_TYPE1.trim().length() == 0) {
            WS_OUTPUT_KEY.WS_OPBL_PROD_TYP = "*";
        } else {
            WS_OUTPUT_KEY.WS_OPBL_PROD_TYP = WS_PROD_TYPE1;
        }
        if (WS_BR1 == 0) {
            WS_OUTPUT_KEY.WS_OPBL_BR = 999999;
        } else {
            WS_OUTPUT_KEY.WS_OPBL_BR = WS_BR1;
        }
        WS_OUTPUT_KEY.WS_OPBL_BOOK_FLG = WS_KEY.WS_BOOK_FLG;
        WS_OUTPUT_KEY.WS_OPBL_EFF_DT = BPCPRMM.EFF_DT;
        WS_OUTPUT_KEY.WS_OPBL_RUN_FLG = BPRPBL.DESC.charAt(0);
        CEP.TRC(SCCGWA, BPRPBL.DESC);
        CEP.TRC(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_KEY);
        SCCMPAG.DATA_LEN = 162;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TTTTTTTTTTT");
        WS_I = 0;
        WS_J = 0;
        WS_CNT = 0;
        for (WS_I = 1; WS_I <= BPRPBL.TEXT.CNT; WS_I += 1) {
            IBS.init(SCCGWA, WS_OUTPUT_DETAIL);
            WS_OUTPUT_DETAIL.WS_OPBL_CNT = WS_I;
            WS_OUTPUT_DETAIL.WS_OPBL_ITM_PNT = BPRPBL.TEXT.DATA[WS_I-1].ITM_PNT;
            WS_OUTPUT_DETAIL.WS_OPBL_ITM_NO = BPRPBL.TEXT.DATA[WS_I-1].ITM_NO;
            WS_OUTPUT_DETAIL.WS_OPBL_ITM_SEQ = BPRPBL.TEXT.DATA[WS_I-1].ITM_SEQ;
            WS_OUTPUT_DETAIL.WS_OPBL_REMARK = BPRPBL.TEXT.DATA[WS_I-1].REMARK;
            CEP.TRC(SCCGWA, "TTTTTT22222");
            WS_CNT = 0;
            for (WS_J = 1; WS_J <= 76; WS_J += 1) {
                if (BPRPBL.TEXT.DATA[WS_I-1].AMT_PNT[WS_J-1].AMT_FLG == '+' 
                    || BPRPBL.TEXT.DATA[WS_I-1].AMT_PNT[WS_J-1].AMT_FLG == '-') {
                    CEP.TRC(SCCGWA, "TTTTTT33333");
                    CEP.TRC(SCCGWA, WS_J);
                    CEP.TRC(SCCGWA, BPRPBL.TEXT.DATA[WS_I-1].AMT_PNT[WS_J-1].AMT_FLG);
                    WS_CNT += 1;
                    WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_CNT = "" + WS_J;
                    JIBS_tmp_int = WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_CNT.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_CNT = "0" + WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_CNT;
                    WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_FLG = BPRPBL.TEXT.DATA[WS_I-1].AMT_PNT[WS_J-1].AMT_FLG;
                    CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_CNT);
                    CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_OPBL_AMT_PNT[WS_CNT-1].WS_OPBL_AMT_FLG);
                    CEP.TRC(SCCGWA, BPRPBL.TEXT.DATA[WS_I-1].AMT_PNT[WS_J-1].AMT_FLG);
                    CEP.TRC(SCCGWA, WS_J);
                    CEP.TRC(SCCGWA, WS_I);
                    CEP.TRC(SCCGWA, WS_CNT);
                }
            }
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DETAIL);
            SCCMPAG.DATA_LEN = 203;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B050_02_BROWSE_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMB);
        BPRPRMT.KEY.TYP = K_PARM_TYPE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSPBL.KEY);
        CEP.TRC(SCCGWA, BPCSPBL.KEY);
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        WS_REQID2 = BPCPRMB.REQID;
        CEP.TRC(SCCGWA, " START BR2");
        CEP.TRC(SCCGWA, BPCPRMB.REQID);
        CEP.TRC(SCCGWA, WS_REQID2);
        BPCPRMB.FUNC = '1';
        BPCPRMB.REQID = WS_REQID2;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        while (WS_FOUND_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.KEY.CD, WS_KEY);
            CEP.TRC(SCCGWA, WS_KEY);
            CEP.TRC(SCCGWA, BPCPRMB.EFF_DT);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, WS_OUTPUT_DATA);
            if (BPCSPBL.KEY.MOD_NO.equalsIgnoreCase(WS_KEY.WS_MOD_NO) 
                && (BPCSPBL.KEY.BOOK_FLG.trim().length() == 0 
                || BPCSPBL.KEY.BOOK_FLG.equalsIgnoreCase(WS_KEY.WS_BOOK_FLG))) {
                R010_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            }
            BPCPRMB.FUNC = '1';
            BPCPRMB.REQID = WS_REQID2;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
        }
        BPCPRMB.FUNC = '2';
        BPCPRMB.REQID = WS_REQID2;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        WS_FOUND_FLG = 'F';
    }
    public void B060_DELETE_PBL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        BPCPRMM.FUNC = '4';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CNT = BPCSPBL.CNT;
        CEP.TRC(SCCGWA, BPRPBL.TEXT.CNT);
        CEP.TRC(SCCGWA, BPCSPBL.CNT);
        WS_FOUND_FLG = 'N';
        for (WS_J = 1; WS_J <= BPRPBL.TEXT.CNT 
            && WS_FOUND_FLG != 'F'; WS_J += 1) {
            if (BPRPBL.TEXT.DATA[WS_J-1].ITM_PNT.equalsIgnoreCase(BPCSPBL.DATA.ITM_PNT)) {
                WS_FOUND_FLG = 'F';
                CEP.TRC(SCCGWA, WS_J);
            }
        }
        if (WS_FOUND_FLG == 'F') {
            WS_CNT = (short) (WS_J - 1);
            for (WS_K = WS_J; WS_K <= BPRPBL.TEXT.CNT; WS_K += 1) {
                CEP.TRC(SCCGWA, WS_K);
                CEP.TRC(SCCGWA, WS_CNT);
                BPRPBL.TEXT.DATA[WS_CNT-1].ITM_PNT = BPRPBL.TEXT.DATA[WS_K-1].ITM_PNT;
                BPRPBL.TEXT.DATA[WS_CNT-1].ITM_NO = BPRPBL.TEXT.DATA[WS_K-1].ITM_NO;
                BPRPBL.TEXT.DATA[WS_CNT-1].ITM_SEQ = BPRPBL.TEXT.DATA[WS_K-1].ITM_SEQ;
                BPRPBL.TEXT.DATA[WS_CNT-1].REMARK = BPRPBL.TEXT.DATA[WS_K-1].REMARK;
                for (WS_I = 1; WS_I <= 76; WS_I += 1) {
                    BPRPBL.TEXT.DATA[WS_CNT-1].AMT_PNT[WS_I-1].AMT_FLG = BPRPBL.TEXT.DATA[WS_K-1].AMT_PNT[WS_I-1].AMT_FLG;
                }
                WS_CNT = (short) (WS_CNT + 1);
            }
            CEP.TRC(SCCGWA, WS_K);
            if (WS_K > BPRPBL.TEXT.CNT) {
                WS_L = (short) (WS_K - 1);
                CEP.TRC(SCCGWA, WS_L);
                BPRPBL.TEXT.DATA[WS_L-1].ITM_PNT = " ";
                BPRPBL.TEXT.DATA[WS_L-1].ITM_NO = " ";
                BPRPBL.TEXT.DATA[WS_L-1].REMARK = " ";
                BPRPBL.TEXT.DATA[WS_L-1].ITM_SEQ = 0;
                for (WS_I = 1; WS_I <= 76; WS_I += 1) {
                    BPRPBL.TEXT.DATA[WS_L-1].AMT_PNT[WS_I-1].AMT_FLG = ' ';
                }
            }
        }
        WS_SEQ_CNT = BPRPBL.TEXT.CNT;
        WS_SEQ_CNT = (short) (WS_SEQ_CNT - 1);
        BPRPBL.TEXT.CNT = WS_SEQ_CNT;
        CEP.TRC(SCCGWA, BPRPBL.TEXT.CNT);
        CEP.TRC(SCCGWA, WS_SEQ_CNT);
        if (BPRPBL.TEXT.CNT == 0) {
            BPCPRMM.FUNC = '1';
            S010_CALL_BPZPRMM();
            if (pgmRtn) return;
        } else {
            BPCPRMM.FUNC = '2';
            S010_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOPBL);
        BPCOPBL.CNTR_TYP = BPCSPBL.DATA.CNTR_TYP;
        BPCOPBL.PROD_TYP = BPCSPBL.DATA.PROD_TYP;
        BPCOPBL.BR = BPCSPBL.DATA.BR;
        BPCOPBL.MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_MOD_NO_TMP = BPCSPBL.KEY.MOD_NO;
        R000_GET_ACMOD_INF();
        if (pgmRtn) return;
        BPCOPBL.MOD_NAME = WS_ACMOD_NAME;
        BPCOPBL.CNTR_TYP = WS_CNTR_TYPE1;
        if (WS_PROD_TYPE1.trim().length() == 0) {
            BPCOPBL.PROD_TYP = "*";
        } else {
            BPCOPBL.PROD_TYP = WS_PROD_TYPE1;
        }
        if (WS_BR1 == 0) {
            BPCOPBL.BR = 999999;
        } else {
            BPCOPBL.BR = WS_BR1;
        }
        BPCOPBL.BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPCOPBL.CNT = BPCSPBL.CNT;
        BPCOPBL.ITM_PNT = BPCSPBL.DATA.ITM_PNT;
        BPCOPBL.ITM_NO = BPCSPBL.DATA.ITM_NO;
        BPCOPBL.ITM_SEQ = BPCSPBL.DATA.ITM_SEQ;
        BPCOPBL.REMARK = BPCSPBL.DATA.REMARK;
        BPCOPBL.EFF_DT = BPCPRMM.EFF_DT;
        BPCOPBL.EXP_DT = BPCPRMM.EXP_DT;
        WS_I = 0;
        WS_J = 0;
        WS_J = 1;
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            if (BPCSPBL.DATA.AMT_PNT[WS_I-1].AMT_FLG != ' ') {
                BPCOPBL.AMT_PNT[WS_J-1].AMT_CNT = WS_I;
                BPCOPBL.AMT_PNT[WS_J-1].AMT_FLG = BPCSPBL.DATA.AMT_PNT[WS_I-1].AMT_FLG;
                WS_J = (short) (WS_J + 1);
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, WS_I);
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_2;
        SCCFMT.DATA_PTR = BPCOPBL;
        SCCFMT.DATA_LEN = 372;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_QUERY_PBL_PROCESS() throws IOException,SQLException,Exception {
        B050_01_BROWSE_PBL_PROCESS();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC.RC_CODE);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
        if (AICPCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        CEP.TRC(SCCGWA, AICQITM.RC);
        if (AICQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSPBL.FUNC == 'A' 
            || BPCSPBL.FUNC == 'D' 
            || BPCSPBL.FUNC == 'T') {
            S020_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSPBL.FUNC == 'U') {
            S020_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S020_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSPBL.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSPBL.FUNC == 'D' 
            || BPCSPBL.FUNC == 'T') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPCHPBL";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 240;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCHPBLO;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCHPBLN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S020_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPCHPBL";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 240;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCHPBLO;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCHPBLN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPRPBL.KEY.TYPE = "AMPBL";
        WS_KEY.WS_MOD_NO = BPCSPBL.KEY.MOD_NO;
        WS_KEY.WS_BOOK_FLG = BPCSPBL.KEY.BOOK_FLG;
        BPRPBL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = BPCSPBL.EFF_DT;
        BPCPRMM.EXP_DT = BPCSPBL.EXP_DT;
        BPRPBL.DESC = "" + WS_RUN_TYP;
        JIBS_tmp_int = BPRPBL.DESC.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPRPBL.DESC = "0" + BPRPBL.DESC;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        if (WS_FUNC_FLG == 'A') {
            BPRPBL.TEXT.CNT = BPCSPBL.CNT;
        } else {
            BPRPBL.TEXT.CNT = WS_SEQ_CNT;
        }
        CEP.TRC(SCCGWA, WS_SEQ_CNT);
        CEP.TRC(SCCGWA, BPRPBL.TEXT.CNT);
        BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].ITM_PNT = BPCSPBL.DATA.ITM_PNT;
        BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].ITM_NO = BPCSPBL.DATA.ITM_NO;
        BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].ITM_SEQ = BPCSPBL.DATA.ITM_SEQ;
        BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].REMARK = BPCSPBL.DATA.REMARK;
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCSPBL.DATA.AMT_PNT[WS_I-1].AMT_FLG);
            BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].AMT_PNT[WS_I-1].AMT_FLG = BPCSPBL.DATA.AMT_PNT[WS_I-1].AMT_FLG;
            CEP.TRC(SCCGWA, BPRPBL.TEXT.DATA[BPCSPBL.CNT-1].AMT_PNT[WS_I-1].AMT_FLG);
        }
        BPRPBL.TEXT.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRPBL.TEXT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPBL.TEXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRPBL.TEXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRPBL.TEXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPRPBL);
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPBL);
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, WS_KEY);
        BPCOPBL.MOD_NO = WS_KEY.WS_MOD_NO;
        WS_MOD_NO_TMP = WS_KEY.WS_MOD_NO;
        R000_GET_ACMOD_INF();
        if (pgmRtn) return;
        BPCOPBL.MOD_NAME = WS_ACMOD_NAME;
        BPCOPBL.CNTR_TYP = WS_CNTR_TYPE1;
        if (WS_PROD_TYPE1.trim().length() == 0) {
            BPCOPBL.PROD_TYP = "*";
        } else {
            BPCOPBL.PROD_TYP = WS_PROD_TYPE1;
        }
        if (WS_BR1 == 0) {
            BPCOPBL.BR = 999999;
        } else {
            BPCOPBL.BR = WS_BR1;
        }
        BPCOPBL.BOOK_FLG = WS_KEY.WS_BOOK_FLG;
        BPCOPBL.MOD_NAME = BPCSPBL.MOD_NAME;
        BPCOPBL.CNT = BPCSPBL.CNT;
        BPCOPBL.ITM_PNT = BPRPBL.TEXT.DATA[BPCOPBL.CNT-1].ITM_PNT;
        BPCOPBL.ITM_NO = BPRPBL.TEXT.DATA[BPCOPBL.CNT-1].ITM_NO;
        BPCOPBL.ITM_SEQ = BPRPBL.TEXT.DATA[BPCOPBL.CNT-1].ITM_SEQ;
        BPCOPBL.REMARK = BPRPBL.TEXT.DATA[BPCOPBL.CNT-1].REMARK;
        BPCOPBL.EFF_DT = BPCPRMM.EFF_DT;
        BPCOPBL.EXP_DT = BPCPRMM.EXP_DT;
        BPCOPBL.RUN_FLG = BPRPBL.DESC.charAt(0);
        WS_I = 0;
        WS_J = 0;
        WS_J = 1;
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            if (BPRPBL.TEXT.DATA[BPCOPBL.CNT-1].AMT_PNT[WS_I-1].AMT_FLG != ' ') {
                BPCOPBL.AMT_PNT[WS_J-1].AMT_CNT = WS_I;
                BPCOPBL.AMT_PNT[WS_J-1].AMT_FLG = BPRPBL.TEXT.DATA[BPCOPBL.CNT-1].AMT_PNT[WS_I-1].AMT_FLG;
                WS_J = (short) (WS_J + 1);
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, WS_I);
            }
        }
        CEP.TRC(SCCGWA, WS_J);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSPBL.FUNC == 'Q') {
            SCCFMT.FMTID = K_FMT_CD_1;
        } else {
            SCCFMT.FMTID = K_FMT_CD_2;
        }
        SCCFMT.DATA_PTR = BPCOPBL;
        SCCFMT.DATA_LEN = 372;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_ACMOD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQAMO);
        WS_ACMOD_NAME = " ";
        BPCPQAMO.FUNC = 'B';
        S000_CALL_BPZPQAMO();
        if (pgmRtn) return;
        WS_END_FLG = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_9383) 
            && WS_END_FLG != 'Y') {
            IBS.init(SCCGWA, BPCPQAMO);
            BPCPQAMO.FUNC = 'N';
            S000_CALL_BPZPQAMO();
            if (pgmRtn) return;
            if (BPCPQAMO.RC.RC_CODE == 0 
                && WS_MOD_NO_TMP.equalsIgnoreCase(BPCPQAMO.DATA_INFO.MOD_NO)) {
                WS_END_FLG = 'Y';
                WS_ACMOD_NAME = BPCPQAMO.DATA_INFO.MOD_NAME;
                WS_CNTR_TYPE1 = BPCPQAMO.DATA_INFO.CNTR_TYPE;
                WS_PROD_TYPE1 = BPCPQAMO.DATA_INFO.PROD_TYPE;
                WS_BR1 = BPCPQAMO.DATA_INFO.BR;
            }
        }
        IBS.init(SCCGWA, BPCPQAMO);
        BPCPQAMO.FUNC = 'E';
        S000_CALL_BPZPQAMO();
        if (pgmRtn) return;
    }
    public void R010_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        WS_CNT1 = WS_CNT1 + 1;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, WS_KEY);
        IBS.init(SCCGWA, WS_OUTPUT_BRW);
        WS_OUTPUT_BRW.WS_OUT_MOD_NO = WS_KEY.WS_MOD_NO;
        WS_OUTPUT_BRW.WS_OUT_CNTR_TYP = WS_ACM_KEY.WS_CNTR_TYPE;
        if (WS_ACM_KEY.WS_PROD_TYPE.trim().length() == 0) {
            WS_OUTPUT_BRW.WS_OUT_PROD_TYP = "*";
        } else {
            WS_OUTPUT_BRW.WS_OUT_PROD_TYP = WS_ACM_KEY.WS_PROD_TYPE;
        }
        if (WS_ACM_KEY.WS_BR_AC == 0) {
            WS_OUTPUT_BRW.WS_OUT_BR = 999999;
        } else {
            WS_OUTPUT_BRW.WS_OUT_BR = WS_ACM_KEY.WS_BR_AC;
        }
        WS_OUTPUT_BRW.WS_OUT_BOOK_FLG = WS_KEY.WS_BOOK_FLG;
        WS_OUTPUT_BRW.WS_OUT_CNT = WS_OUTPUT_DATA.WS_OUTPUT_CNT;
        CEP.TRC(SCCGWA, BPCPRMB.EFF_DT);
        WS_OUTPUT_BRW.WS_OUT_EFF_DT = BPCPRMB.EFF_DT;
        WS_OUTPUT_BRW.WS_OUT_EXP_DT = 99991231;
        WS_OUTPUT_BRW.WS_OUT_RUN_FLG = BPRPRMT.DESC.charAt(0);
        CEP.TRC(SCCGWA, BPRPRMT.DESC);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_BRW);
        SCCMPAG.DATA_LEN = 51;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD  ", BPCPQAMO);
        CEP.TRC(SCCGWA, BPCPQAMO.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
        if (BPCPQAMO.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_9383)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S010_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPRPBL.DATA_LEN = 9341;
        CEP.TRC(SCCGWA, BPRPBL.DATA_LEN);
        BPCPRMM.DAT_PTR = BPRPBL;
        CEP.TRC(SCCGWA, BPRPBL);
        CEP.TRC(SCCGWA, "CALL BP-PARM-MAINTAIN");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE", BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_FOUND_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
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
