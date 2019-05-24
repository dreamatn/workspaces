package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICMSG_ERROR_MSG;
import com.hisun.AI.AICPQITM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSASMT {
    boolean pgmRtn = false;
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP241";
    String K_PARM_TYPE = "AMBKP";
    char WS_FND_FLG = ' ';
    char WS_BROWSE_FLG = ' ';
    int WS_CNT = 0;
    int WS_I = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPZSASMT_WS_TEMP_DATE WS_TEMP_DATE = new BPZSASMT_WS_TEMP_DATE();
    BPZSASMT_WS_TEMP1_DATE WS_TEMP1_DATE = new BPZSASMT_WS_TEMP1_DATE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRBKPM BPRBKPM = new BPRBKPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPITM BPCPITM = new BPCPITM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOASMT BPCOASMT = new BPCOASMT();
    AICPQITM AICQITM = new AICPQITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    SCCGWA SCCGWA;
    BPCSASMT BPCSASMT;
    public void MP(SCCGWA SCCGWA, BPCSASMT BPCSASMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSASMT = BPCSASMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSASMT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSASMT.INFO.FUNC);
        if (BPCSASMT.INFO.FUNC == 'A' 
            || BPCSASMT.INFO.FUNC == 'U') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
        if (BPCSASMT.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSASMT.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSASMT.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSASMT.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSASMT.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            if (BPCSASMT.INFO.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+BPCSASMT.INFO.FUNC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY999999999-----");
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LBY555-------------");
        if (BPCSASMT.DAT.KEY.BOOK_FLG.trim().length() == 0 
            || BPCSASMT.DAT.KEY.BOOK_FLG.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            S000_CHECK_BOOK_FLG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY50-------------");
        if (BPCSASMT.DAT.DAT_TXT.BOOK_TYP == ' ' 
            || BPCSASMT.DAT.DAT_TXT.BOOK_TYP == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9401;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY51-------------");
        if (BPCSASMT.DAT.DAT_TXT.COA_FLG.trim().length() == 0 
            || BPCSASMT.DAT.DAT_TXT.COA_FLG.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9403;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY52------------");
        if (BPCSASMT.DAT.DAT_TXT.ITM_LEN == ' ' 
            || BPCSASMT.DAT.DAT_TXT.ITM_LEN == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9402;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY53-------------");
        if (BPCSASMT.DAT.DAT_TXT.STS == ' ' 
            || BPCSASMT.DAT.DAT_TXT.STS == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9403;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSASMT.DAT.DAT_TXT.PL_ITM.trim().length() == 0 
            || BPCSASMT.DAT.DAT_TXT.PL_ITM.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9407;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY54-------------");
        if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM.trim().length() == 0 
            || BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9408;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "LBY55-------------");
        if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_9436;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.PL_ITM);
        CEP.TRC(SCCGWA, "LBY38-------------");
        if (BPCSASMT.DAT.DAT_TXT.COA_FLG.trim().length() > 0 
            && BPCSASMT.DAT.DAT_TXT.PL_ITM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPITM);
            BPCPITM.INPUT_DATA.COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
            BPCPITM.INPUT_DATA.NO = BPCSASMT.DAT.DAT_TXT.PL_ITM;
            S000_CALL_BPZPITM();
            if (pgmRtn) return;
            if (BPCPITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_TYPE_NO_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "LBY32-------------");
        if (BPCSASMT.DAT.DAT_TXT.COA_FLG.trim().length() > 0 
            && BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPITM);
            BPCPITM.INPUT_DATA.COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
            BPCPITM.INPUT_DATA.NO = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM;
            S000_CALL_BPZPITM();
            if (pgmRtn) return;
            if (BPCPITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_TYPE_NO_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "LBY31-------------");
        if (BPCSASMT.DAT.DAT_TXT.COA_FLG.trim().length() > 0 
            && BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPITM);
            BPCPITM.INPUT_DATA.COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
            BPCPITM.INPUT_DATA.NO = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M;
            S000_CALL_BPZPITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPITM.OUTPUT_DATA.TYPE);
            if (BPCPITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_TYPE_NO_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "LBY599-------------");
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
        AICQITM.INPUT_DATA.NO = BPCSASMT.DAT.DAT_TXT.PL_ITM;
        if (BPCSASMT.DAT.DAT_TXT.PL_ITM.trim().length() == 0) WS_FLD_NO = 0;
        else WS_FLD_NO = Short.parseShort(BPCSASMT.DAT.DAT_TXT.PL_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.PL_ITM);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.EXP_DATE);
        if (AICQITM.OUTPUT_DATA.EXP_DATE != 99991231) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERRO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICQITM.OUTPUT_DATA.STS != 'A') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
        }
        if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
            && BPCSASMT.DAT.DAT_TXT.SEQ3 == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
        }
        if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
            && BPCSASMT.DAT.DAT_TXT.SEQ3 != 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
        }
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
        AICQITM.INPUT_DATA.NO = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM;
        if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM.trim().length() == 0) WS_FLD_NO = 0;
        else WS_FLD_NO = Short.parseShort(BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM);
        CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.EXP_DATE);
        if (AICQITM.OUTPUT_DATA.EXP_DATE != 99991231) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERRO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICQITM.OUTPUT_DATA.STS != 'A') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
        }
        if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
            && BPCSASMT.DAT.DAT_TXT.SEQ4 == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
        }
        if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
            && BPCSASMT.DAT.DAT_TXT.SEQ4 != 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
        }
        if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M.trim().length() > 0) {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
            AICQITM.INPUT_DATA.NO = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M;
            if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M);
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M);
            CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.EXP_DATE);
            if (AICQITM.OUTPUT_DATA.EXP_DATE != 99991231) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERRO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.STS != 'A') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MIBFLG_INVALID);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && BPCSASMT.DAT.DAT_TXT.SEQ5 == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_Y_INVALID_SEQ);
            }
            if (AICQITM.OUTPUT_DATA.MIB_FLG == 'G' 
                && BPCSASMT.DAT.DAT_TXT.SEQ5 != 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIBFLG_G_INVALID_SEQ);
            }
        }
        CEP.TRC(SCCGWA, "TEST 003");
        if (BPCSASMT.DAT.DAT_TXT.PL_ITM.trim().length() > 0 
            && BPCSASMT.DAT.DAT_TXT.SEQ3 != 0) {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AICRCMIB.FUNC = 'Q';
            AIRCMIB.KEY.GL_BOOK = BPCSASMT.DAT.KEY.BOOK_FLG;
            AIRCMIB.KEY.ITM = BPCSASMT.DAT.DAT_TXT.PL_ITM;
            AIRCMIB.KEY.SEQ = BPCSASMT.DAT.DAT_TXT.SEQ3;
            AIRCMIB.KEY.BR = 999999;
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.PL_ITM);
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.SEQ3);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            if (AICRCMIB.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AIRCMIB.STS);
            if (AIRCMIB.STS != 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "TEST 005");
        if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M.trim().length() > 0 
            && BPCSASMT.DAT.DAT_TXT.SEQ5 != 0) {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AICRCMIB.FUNC = 'Q';
            AIRCMIB.KEY.GL_BOOK = BPCSASMT.DAT.KEY.BOOK_FLG;
            AIRCMIB.KEY.ITM = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M;
            AIRCMIB.KEY.SEQ = BPCSASMT.DAT.DAT_TXT.SEQ5;
            AIRCMIB.KEY.BR = 999999;
            CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M);
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.SEQ5);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            if (AICRCMIB.RETURN_INFO == 'N') {
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
        }
        CEP.TRC(SCCGWA, "TEST 006");
        if (BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM.trim().length() > 0 
            && BPCSASMT.DAT.DAT_TXT.SEQ4 != 0) {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AICRCMIB.FUNC = 'Q';
            AIRCMIB.KEY.GL_BOOK = BPCSASMT.DAT.KEY.BOOK_FLG;
            AIRCMIB.KEY.ITM = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM;
            AIRCMIB.KEY.SEQ = BPCSASMT.DAT.DAT_TXT.SEQ4;
            AIRCMIB.KEY.BR = 999999;
            CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM);
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.SEQ4);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            if (AICRCMIB.RETURN_INFO == 'N') {
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
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        S010_BROWSE_DATA_PROCESS();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            CEP.TRC(SCCGWA, "121212121212121212");
            if (BPCSASMT.DAT.KEY.BOOK_FLG.equalsIgnoreCase(BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG)) {
                R000_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "11100000");
                R000_FMT_DATA_PROCESS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "22200000");
                Z_RET();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "33300000");
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 10) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GL_BOOK_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LBY1-----------------");
        S010_BROWSE_DATA_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LBY2-----------------");
        if (WS_BROWSE_FLG == 'N') {
            IBS.init(SCCGWA, BPRBKPM);
            IBS.init(SCCGWA, BPCPRMM);
            BPRBKPM.KEY.TYPE = "AMBKP";
            BPRBKPM.KEY.REDEFINES6.CNT = 10;
            BPRBKPM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRBKPM.KEY.REDEFINES6);
            BPCPRMM.EFF_DT = BPCSASMT.EFF_DT;
            BPCPRMM.EXP_DT = BPCSASMT.EXP_DT;
            CEP.TRC(SCCGWA, "111111111111111111111111111111111");
            CEP.TRC(SCCGWA, BPCSASMT.EFF_DT);
            CEP.TRC(SCCGWA, BPCSASMT.EXP_DT);
            WS_CNT = BPCSASMT.DAT.DAT_TXT.BOOK_TYP;
            CEP.TRC(SCCGWA, BPCSASMT.DAT.DAT_TXT.BOOK_TYP);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "111100001");
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG = BPCSASMT.DAT.KEY.BOOK_FLG;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_TYP = BPCSASMT.DAT.DAT_TXT.BOOK_TYP;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG = BPCSASMT.DAT.DAT_TXT.COA_FLG;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].ITM_LEN = BPCSASMT.DAT.DAT_TXT.ITM_LEN;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS = BPCSASMT.DAT.DAT_TXT.STS;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].REAL_SUS_ITM = BPCSASMT.DAT.DAT_TXT.REAL_SUS_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].MEMO_SUS_ITM = BPCSASMT.DAT.DAT_TXT.MEMO_SUS_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].PL_ITM = BPCSASMT.DAT.DAT_TXT.PL_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM_M = BPCSASMT.DAT.DAT_TXT.CRS_BR_ITM_M;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].UPD_TEL = BPCSASMT.DAT.DAT_TXT.UPD_TEL;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].UPD_DATE = BPCSASMT.DAT.DAT_TXT.UPD_DATE;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].LR_ITM = BPCSASMT.DAT.DAT_TXT.LR_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].JS_ITM = BPCSASMT.DAT.DAT_TXT.JS_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].DH_ITM = BPCSASMT.DAT.DAT_TXT.DH_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].WH_ITM = BPCSASMT.DAT.DAT_TXT.WH_ITM;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].FZ_DATE = BPCSASMT.DAT.DAT_TXT.FZ_DATE;
            CEP.TRC(SCCGWA, "11110000");
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ1 = BPCSASMT.DAT.DAT_TXT.SEQ1;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ2 = BPCSASMT.DAT.DAT_TXT.SEQ2;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ3 = BPCSASMT.DAT.DAT_TXT.SEQ3;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ4 = BPCSASMT.DAT.DAT_TXT.SEQ4;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ5 = BPCSASMT.DAT.DAT_TXT.SEQ5;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ6 = BPCSASMT.DAT.DAT_TXT.SEQ6;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ7 = BPCSASMT.DAT.DAT_TXT.SEQ7;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ8 = BPCSASMT.DAT.DAT_TXT.SEQ8;
            BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ9 = BPCSASMT.DAT.DAT_TXT.SEQ9;
            BPCPRMM.FUNC = '0';
            S010_CALL_BPZPRMM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "LBY90-------");
            CEP.TRC(SCCGWA, BPCPRMM.RC.RC_RTNCODE);
            if (BPCPRMM.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            R000_FMT_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_CNT = BPCSASMT.DAT.DAT_TXT.BOOK_TYP;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG);
