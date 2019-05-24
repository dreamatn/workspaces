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

public class AIZSPAI3 {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI03";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 3 MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INF = " ";
    AIZSPAI3_WS_DATA WS_DATA = new AIZSPAI3_WS_DATA();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQITM AICQITM = new AICPQITM();
    AIRPAI3 AIRPAI3 = new AIRPAI3();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI3 AICHPAI3 = new AICHPAI3();
    AICHPAI3 AICHAI3N = new AICHPAI3();
    AICHPAI3 AICHAI3O = new AICHPAI3();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    AICSPAI3 AICSPAI3;
    public void MP(SCCGWA SCCGWA, AICSPAI3 AICSPAI3) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI3 = AICSPAI3;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI3 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPAI3.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI3.INFO.FUNC == 'A') {
            CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.REDEFINES13.FLG);
            CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.REDEFINES13.CCY);
            CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK);
            B030_10_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI3.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI3.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSPAI3.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.REDEFINES13.FLG);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.DESC);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.CDESC);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.CD);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.REDEFINES13.CCY);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.DATA_TXT.ITM_CUR);
        CEP.TRC(SCCGWA, AICSPAI3.DATA.DATA_TXT.ITM_CUR_HKD);
        CEP.TRC(SCCGWA, AICSPAI3.INFO);
        if (AICSPAI3.INFO.FUNC == 'A' 
            || AICSPAI3.INFO.FUNC == 'M' 
            || AICSPAI3.INFO.FUNC == 'D' 
            || AICSPAI3.INFO.FUNC == 'I') {
            if (AICSPAI3.DATA.KEY.REDEFINES13.CCY.trim().length() == 0 
                || AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK.trim().length() == 0 
                || AICSPAI3.DATA.KEY.REDEFINES13.FLG == ' ') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PAI3_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSPAI3.INFO.FUNC == 'A' 
            || AICSPAI3.INFO.FUNC == 'M') {
            if (AICSPAI3.DATA.KEY.REDEFINES13.FLG != '1' 
                && AICSPAI3.DATA.KEY.REDEFINES13.FLG != '2') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.TYPE_INVALID;
                WS_ERR_INF = "ONLY INPUT 1 OR 2!";
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSPAI3.DATA.EFF_DATE > AICSPAI3.DATA.EXP_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_GT_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSPAI3.DATA.DATA_TXT.ITM_SEQ1 == 0 
                || AICSPAI3.DATA.DATA_TXT.ITM_SEQ2 == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!AICSPAI3.DATA.KEY.REDEFINES13.CCY.equalsIgnoreCase("999")) {
                IBS.init(SCCGWA, BPCSIC);
                BPCSIC.FUNC = "03";
                BPCSIC.CCY = AICSPAI3.DATA.KEY.REDEFINES13.CCY;
                S000_CALL_BPZSIC();
                if (pgmRtn) return;
            }
            if (AICSPAI3.DATA.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && AICSPAI3.INFO.FUNC == 'A') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_DAYS_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
            AICQITM.INPUT_DATA.NO = AICSPAI3.DATA.DATA_TXT.ITM_CUR;
            if (AICSPAI3.DATA.DATA_TXT.ITM_CUR.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AICSPAI3.DATA.DATA_TXT.ITM_CUR);
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICQITM.RC.RTNCODE == 0) {
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ITM_LVL);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6") 
                    || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("7")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMR_TYPE_NO_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.STS);
                if (AICQITM.OUTPUT_DATA.STS != 'A') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
                if ("999999".trim().length() == 0) AIRCMIB.KEY.BR = 0;
                else AIRCMIB.KEY.BR = Integer.parseInt("999999");
                AIRCMIB.KEY.ITM = AICSPAI3.DATA.DATA_TXT.ITM_CUR;
                AIRCMIB.KEY.SEQ = AICSPAI3.DATA.DATA_TXT.ITM_SEQ1;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AICPCMIB.RC.RC_CODE != 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.EXP_DATE);
                    if (AIRCMIB.EXP_DATE < AICSPAI3.DATA.EXP_DATE) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.EXPDT_GT_CMIB_EXPDT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.BOOK_FLG = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
            AICQITM.INPUT_DATA.NO = AICSPAI3.DATA.DATA_TXT.ITM_CUR_HKD;
            if (AICSPAI3.DATA.DATA_TXT.ITM_CUR_HKD.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AICSPAI3.DATA.DATA_TXT.ITM_CUR_HKD);
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICQITM.RC.RTNCODE == 0) {
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ITM_LVL);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6") 
                    || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("7")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMR_TYPE_NO_MATCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICSPAI3.DATA.DATA_TXT.ITM_CUR_HKD);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.STS);
                if (AICQITM.OUTPUT_DATA.STS != 'A') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
                if ("999999".trim().length() == 0) AIRCMIB.KEY.BR = 0;
                else AIRCMIB.KEY.BR = Integer.parseInt("999999");
                AIRCMIB.KEY.ITM = AICSPAI3.DATA.DATA_TXT.ITM_CUR_HKD;
                AIRCMIB.KEY.SEQ = AICSPAI3.DATA.DATA_TXT.ITM_SEQ2;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AICPCMIB.RC.RC_CODE != 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.EXP_DATE);
                    if (AIRCMIB.EXP_DATE < AICSPAI3.DATA.EXP_DATE) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.EXPDT_GT_CMIB_EXPDT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B030_10_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI3);
        CEP.TRC(SCCGWA, "LBY2-------------");
        AIRPAI3.KEY.TYP = "PAI03";
        AIRPAI3.KEY.REDEFINES5.FLG = AICSPAI3.DATA.KEY.REDEFINES13.FLG;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        AIRPAI3.KEY.REDEFINES5.CCY = AICSPAI3.DATA.KEY.REDEFINES13.CCY;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        AIRPAI3.KEY.REDEFINES5.GLBOOK = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PAI3_ALREADY_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI3);
        AIRPAI3.KEY.TYP = "PAI03";
        BPCPRMM.EFF_DT = AICSPAI3.DATA.EFF_DATE;
        AIRPAI3.KEY.REDEFINES5.FLG = AICSPAI3.DATA.KEY.REDEFINES13.FLG;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        AIRPAI3.KEY.REDEFINES5.CCY = AICSPAI3.DATA.KEY.REDEFINES13.CCY;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        AIRPAI3.KEY.REDEFINES5.GLBOOK = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI3);
        AIRPAI3.KEY.TYP = "PAI03";
        BPCPRMM.EFF_DT = AICSPAI3.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI3.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI3);
        IBS.init(SCCGWA, AICHAI3O);
        IBS.init(SCCGWA, AICHAI3N);
        AIRPAI3.KEY.TYP = "PAI03";
        AIRPAI3.KEY.REDEFINES5.FLG = AICSPAI3.DATA.KEY.REDEFINES13.FLG;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        AIRPAI3.KEY.REDEFINES5.CCY = AICSPAI3.DATA.KEY.REDEFINES13.CCY;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        AIRPAI3.KEY.REDEFINES5.GLBOOK = AICSPAI3.DATA.KEY.REDEFINES13.GLBOOK;
        AIRPAI3.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI3.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSPAI3.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI3.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI3.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI3.DATA.DESC.equalsIgnoreCase(AIRPAI3.DESC) 
            && AICSPAI3.DATA.CDESC.equalsIgnoreCase(AIRPAI3.CDESC) 
            && AICSPAI3.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI3O.KEY.TYP = "PAI03";
        AICHAI3O.KEY.FLG = AIRPAI3.KEY.REDEFINES5.FLG;
        AICHAI3O.KEY.CCY = AIRPAI3.KEY.REDEFINES5.CCY;
        AICHAI3O.KEY.GLBOOK = AIRPAI3.KEY.REDEFINES5.GLBOOK;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI3.DATA_TXT);
