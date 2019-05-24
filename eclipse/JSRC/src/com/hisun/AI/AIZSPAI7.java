package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCRMBPM;
import com.hisun.BP.BPCSIC;
import com.hisun.BP.BPRPARM;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class AIZSPAI7 {
    boolean pgmRtn = false;
    String BSL_RTC_FLAG = "SIT_GN_20150603_V1";
    String K_PARM_TYPE = "PAI07";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 3 MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AIZSPAI7_WS_DATA WS_DATA = new AIZSPAI7_WS_DATA();
    char WS_FND_FLG = ' ';
    char WS_RM_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQITM AICQITM = new AICPQITM();
    AICSCMIB AICSCMIB = new AICSCMIB();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI7 AICHPAI7 = new AICHPAI7();
    AICHPAI7 AICHAI7N = new AICHPAI7();
    AICHPAI7 AICHAI7O = new AICHPAI7();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    SCCGWA SCCGWA;
    AICSPAI7 AICSPAI7;
    public void MP(SCCGWA SCCGWA, AICSPAI7 AICSPAI7) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI7 = AICSPAI7;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI7 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICSPAI7.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI7.INFO.FUNC == 'A') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI7.INFO.FUNC == 'M') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI7.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSPAI7.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.KEY.CD);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.KEY.REDEFINES13.BUSI_KND);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.KEY.REDEFINES13.TR_TYP);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.FLG);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_R_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_R_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_M_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_M_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_R_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_R_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_M_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_M_D);
        if ((AICSPAI7.INFO.FUNC == 'A' 
            || AICSPAI7.INFO.FUNC == 'M' 
            || AICSPAI7.INFO.FUNC == 'D' 
            || AICSPAI7.INFO.FUNC == 'I') 
            && (AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK.trim().length() == 0 
            || AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.trim().length() == 0)) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PAI7_CODE_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("1") 
            || AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("3")) 
            && AICSPAI7.DATA.KEY.REDEFINES13.BUSI_KND.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PAI7_BIZKND_MUST_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("3")) {
            if (AICSPAI7.DATA.DATA_TXT.ITM_R_C.trim().length() > 0) {
                IBS.init(SCCGWA, AICQITM);
                AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
                AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_R_C == 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_R_C != 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
                }
            }
            if (AICSPAI7.DATA.DATA_TXT.ITM_M_C.trim().length() > 0) {
                IBS.init(SCCGWA, AICQITM);
                AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
                AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_M_C == 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_M_C != 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
                }
            }
            if (AICSPAI7.DATA.DATA_TXT.ITM_R_D.trim().length() > 0) {
                IBS.init(SCCGWA, AICQITM);
                AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
                AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_R_D == 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_R_D != 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
                }
            }
            if (AICSPAI7.DATA.DATA_TXT.ITM_M_D.trim().length() > 0) {
                IBS.init(SCCGWA, AICQITM);
                AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
                AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_M_D == 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
                }
                if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                    && AICSPAI7.DATA.DATA_TXT.SEQ_M_D != 0) {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
                }
            }
            if ((AICSPAI7.INFO.FUNC == 'A' 
                || AICSPAI7.INFO.FUNC == 'M')) {
                if (AICSPAI7.DATA.DATA_TXT.SEQ_R_C != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_R_C;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS == ' ') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (AIRCMIB.STS == 'P' 
                        || AIRCMIB.STS == 'S' 
                        || AIRCMIB.STS == 'H') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_M_C != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_M_C;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS == ' ') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (AIRCMIB.STS == 'P' 
                        || AIRCMIB.STS == 'S' 
                        || AIRCMIB.STS == 'H') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_R_D != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_R_D;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS == ' ') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (AIRCMIB.STS == 'P' 
                        || AIRCMIB.STS == 'S' 
                        || AIRCMIB.STS == 'H') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_M_D != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_M_D;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS == ' ') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
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
        if (AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_R;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_C == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_C != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (!AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                && !AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_M;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_C == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_C != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_R;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_D == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_D != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (!AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                && !AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_M;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_D == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_D != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
        }
        if (AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("2")) {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_I;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_C == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_C != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_I;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_C == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_C != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_I;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_D == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_R_D != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
            if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_I;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_D == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && AICSPAI7.DATA.DATA_TXT.SEQ_M_D != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
        }
        if (!AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("3")) {
            if ((AICSPAI7.INFO.FUNC == 'A' 
                || AICSPAI7.INFO.FUNC == 'M')) {
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_R_C);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_R_C);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_M_C);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_M_C);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_R_D);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_R_D);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_M_D);
                CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_M_D);
                if (AICSPAI7.DATA.DATA_TXT.ITM_R_C.trim().length() > 0) {
                    IBS.init(SCCGWA, AICQITM);
                    IBS.init(SCCGWA, AICSCMIB);
                    WS_RM_FLG = ' ';
                    AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
                    AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AICSCMIB.ITM = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
                    AICSCMIB.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_R_C;
                    WS_RM_FLG = 'R';
                    B020_DEAL_ITM_CMIB();
                    if (pgmRtn) return;
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_R_C != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_R_C;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_R_C;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    if (AICPCMIB.RETURN_INFO == 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
                    if (AIRCMIB.RVS_TYP != 'C') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AICSPAI7.DATA.DATA_TXT.ITM_R_D.trim().length() > 0) {
                    IBS.init(SCCGWA, AICQITM);
                    IBS.init(SCCGWA, AICSCMIB);
                    WS_RM_FLG = ' ';
                    AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
                    AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AICSCMIB.ITM = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
                    AICSCMIB.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_R_D;
                    WS_RM_FLG = 'R';
                    B020_DEAL_ITM_CMIB();
                    if (pgmRtn) return;
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_R_D != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_R_D;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_R_D;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    if (AICPCMIB.RETURN_INFO == 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
                    if (AIRCMIB.RVS_TYP != 'D') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AICSPAI7.DATA.DATA_TXT.ITM_M_C.trim().length() > 0) {
                    IBS.init(SCCGWA, AICQITM);
                    IBS.init(SCCGWA, AICSCMIB);
                    WS_RM_FLG = ' ';
                    AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
                    AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AICSCMIB.ITM = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
                    AICSCMIB.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_M_C;
                    WS_RM_FLG = 'M';
                    B020_DEAL_ITM_CMIB();
                    if (pgmRtn) return;
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_M_C != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_M_C;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_M_C;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    if (AICPCMIB.RETURN_INFO == 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
                    if (AIRCMIB.RVS_TYP != 'C') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AICSPAI7.DATA.DATA_TXT.ITM_M_D.trim().length() > 0) {
                    IBS.init(SCCGWA, AICQITM);
                    IBS.init(SCCGWA, AICSCMIB);
                    WS_RM_FLG = ' ';
                    AICQITM.INPUT_DATA.NO = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
                    AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AICSCMIB.ITM = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
                    AICSCMIB.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_M_D;
                    WS_RM_FLG = 'M';
                    B020_DEAL_ITM_CMIB();
                    if (pgmRtn) return;
                }
                if (AICSPAI7.DATA.DATA_TXT.SEQ_M_D != 0) {
                    IBS.init(SCCGWA, AIRCMIB);
                    IBS.init(SCCGWA, AICRCMIB);
                    WS_RM_FLG = ' ';
                    IBS.init(SCCGWA, AICPCMIB);
                    AICRCMIB.FUNC = 'F';
                    AIRCMIB.KEY.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
                    AIRCMIB.KEY.ITM = AICSPAI7.DATA.DATA_TXT.ITM_M_D;
                    AIRCMIB.KEY.SEQ = AICSPAI7.DATA.DATA_TXT.SEQ_M_D;
                    AIRCMIB.KEY.BR = 999999;
                    AICRCMIB.POINTER = AIRCMIB;
                    AICRCMIB.REC_LEN = 407;
                    S000_CALL_AIZPCMIB();
                    if (pgmRtn) return;
                    if (AICPCMIB.RETURN_INFO == 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
                    if (AIRCMIB.RVS_TYP != 'D') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B020_DEAL_ITM_CMIB() throws IOException,SQLException,Exception {
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
        if (AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP.equalsIgnoreCase("2")) {
            if (AICQITM.OUTPUT_DATA.STS != 'A') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_RM_FLG == 'R' 
                && (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9"))) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMR_TYPE_NO_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_RM_FLG == 'M' 
                && (!AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8") 
                && !AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("9"))) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMM_TYPE_NO_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSPAI7.DATA.EXP_DATE > AICQITM.OUTPUT_DATA.EXP_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXP_DATE_LATE_ITM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        BPCPRMM.EFF_DT = AICSPAI7.DATA.EFF_DATE;
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.AC_TYP = AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = AICSPAI7.DATA.KEY.REDEFINES13.BUSI_KND;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.TR_TYP = AICSPAI7.DATA.KEY.REDEFINES13.TR_TYP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        BPCPRMM.EFF_DT = AICSPAI7.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI7.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI7);
        IBS.init(SCCGWA, AICHAI7O);
        IBS.init(SCCGWA, AICHAI7N);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = AICSPAI7.DATA.KEY.REDEFINES13.AC_TYP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = AICSPAI7.DATA.KEY.REDEFINES13.BUSI_KND;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.TR_TYP = AICSPAI7.DATA.KEY.REDEFINES13.TR_TYP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = AICSPAI7.DATA.KEY.REDEFINES13.GL_BOOK;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.FLG);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_R_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_R_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_M_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_M_D);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_R_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_R_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.ITM_M_C);
        CEP.TRC(SCCGWA, AICSPAI7.DATA.DATA_TXT.SEQ_M_C);
        BPCPRMM.EFF_DT = AICSPAI7.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI7.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI7.DATA.DESC.equalsIgnoreCase(AIRPAI7.DESC) 
            && AICSPAI7.DATA.CDESC.equalsIgnoreCase(AIRPAI7.CDESC) 
            && AICSPAI7.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI7O.KEY.TYP = "PAI07";
        AICHAI7O.KEY.HAI7O_REDEFINES72.AC_TYP = AIRPAI7.KEY.REDEFINES6.AC_TYP;
