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

public class AIZSAI10 {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI10";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 10 MAINTENANCE";
    int K_BR_CONS = 999999;
    int WS_I = 0;
    int WS_J = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INF = " ";
    AIZSAI10_WS_DATA WS_DATA = new AIZSAI10_WS_DATA();
    String WK_ITM_NAME = " ";
    char AIZSAI10_FILLER35 = 0X02;
    String WK_REV_NAME = " ";
    char AIZSAI10_FILLER37 = 0X02;
    String WK_PL_NAME = " ";
    char AIZSAI10_FILLER39 = 0X02;
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQITM AICQITM = new AICPQITM();
    AIRAI10 AIRAI10 = new AIRAI10();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHAI10 AICHAI10 = new AICHAI10();
    AICHAI10 AICHA10N = new AICHAI10();
    AICHAI10 AICHA10O = new AICHAI10();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    SCCGWA SCCGWA;
    AICSAI10 AICSAI10;
    public void MP(SCCGWA SCCGWA, AICSAI10 AICSAI10) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSAI10 = AICSAI10;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSAI10 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSAI10.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSAI10.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSAI10.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSAI10.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSAI10.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.REDEFINES13.GLBOOK);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.REDEFINES13.CCY);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.REDEFINES13.ITM);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.REDEFINES13.SEQ_ITM);
        CEP.TRC(SCCGWA, AICSAI10.DATA.DESC);
        CEP.TRC(SCCGWA, AICSAI10.DATA.CDESC);
        CEP.TRC(SCCGWA, AICSAI10.DATA.DATA_TXT);
        CEP.TRC(SCCGWA, AICSAI10.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSAI10.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSAI10.DATA.DATA_TXT.ITM_REV);
        CEP.TRC(SCCGWA, AICSAI10.DATA.DATA_TXT.ITM_PL);
        if (AICSAI10.INFO.FUNC == 'A' 
            || AICSAI10.INFO.FUNC == 'M' 
            || AICSAI10.INFO.FUNC == 'D' 
            || AICSAI10.INFO.FUNC == 'I') {
            if (AICSAI10.DATA.KEY.REDEFINES13.CCY.trim().length() == 0 
                || AICSAI10.DATA.KEY.REDEFINES13.GLBOOK.trim().length() == 0 
                || AICSAI10.DATA.KEY.REDEFINES13.ITM.equalsIgnoreCase("0") 
                || AICSAI10.DATA.KEY.REDEFINES13.SEQ_ITM == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AI10_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSAI10.INFO.FUNC == 'A' 
            || AICSAI10.INFO.FUNC == 'M') {
            if (AICSAI10.DATA.EFF_DATE > AICSAI10.DATA.EXP_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_GT_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSAI10.DATA.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && AICSAI10.INFO.FUNC == 'A') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_DAYS_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!AICSAI10.DATA.KEY.REDEFINES13.CCY.equalsIgnoreCase("999")) {
                IBS.init(SCCGWA, BPCSIC);
                BPCSIC.FUNC = "03";
                BPCSIC.CCY = AICSAI10.DATA.KEY.REDEFINES13.CCY;
                S000_CALL_BPZSIC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.BOOK_FLG = AICSAI10.DATA.KEY.REDEFINES13.GLBOOK;
            AICQITM.INPUT_DATA.NO = AICSAI10.DATA.KEY.REDEFINES13.ITM;
            WS_ERR_INF = "Changkou error!";
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICQITM.RC.RTNCODE == 0) {
                WK_ITM_NAME = AICQITM.OUTPUT_DATA.CHS_NM;
                CEP.TRC(SCCGWA, "LBY990============");
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ITM_LVL);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                WK_REV_NAME = AICQITM.OUTPUT_DATA.CHS_NM;
                if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6") 
                    || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("7")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMR_TYPE_NO_MATCH;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICQITM.OUTPUT_DATA.STS != 'A') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSAI10.DATA.KEY.REDEFINES13.GLBOOK;
                if ("999999".trim().length() == 0) AIRCMIB.KEY.BR = 0;
                else AIRCMIB.KEY.BR = Integer.parseInt("999999");
                AIRCMIB.KEY.ITM = AICSAI10.DATA.KEY.REDEFINES13.ITM;
                AIRCMIB.KEY.SEQ = AICSAI10.DATA.KEY.REDEFINES13.SEQ_ITM;
                CEP.TRC(SCCGWA, AIRCMIB);
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AICPCMIB.RC.RC_CODE != 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        WS_ERR_INF = "Changkou GL A/C error!";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.EXP_DATE);
                    if (AIRCMIB.EXP_DATE < AICSAI10.DATA.EXP_DATE) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.EXPDT_GT_CMIB_EXPDT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.BOOK_FLG = AICSAI10.DATA.KEY.REDEFINES13.GLBOOK;
            AICQITM.INPUT_DATA.NO = AICSAI10.DATA.DATA_TXT.ITM_REV;
            WS_ERR_INF = "Chonggu error!";
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICQITM.RC.RTNCODE == 0) {
                CEP.TRC(SCCGWA, "LBY991============");
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ITM_LVL);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                WK_REV_NAME = AICQITM.OUTPUT_DATA.CHS_NM;
                if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                    WS_ERR_INF = "Chonggu GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6") 
                    || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("7")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMR_TYPE_NO_MATCH;
                    WS_ERR_INF = "Chonggu GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICQITM.OUTPUT_DATA.STS != 'A') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                    WS_ERR_INF = "Chonggu GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSAI10.DATA.KEY.REDEFINES13.GLBOOK;
                if ("999999".trim().length() == 0) AIRCMIB.KEY.BR = 0;
                else AIRCMIB.KEY.BR = Integer.parseInt("999999");
                AIRCMIB.KEY.ITM = AICSAI10.DATA.DATA_TXT.ITM_REV;
                AIRCMIB.KEY.SEQ = AICSAI10.DATA.DATA_TXT.REV_SEQ;
                CEP.TRC(SCCGWA, AIRCMIB);
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AICPCMIB.RC.RC_CODE != 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                    WS_ERR_INF = "Chonggu GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        WS_ERR_INF = "Chonggu GL A/C error!";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.EXP_DATE);
                    if (AIRCMIB.EXP_DATE < AICSAI10.DATA.EXP_DATE) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.EXPDT_GT_CMIB_EXPDT;
                        WS_ERR_INF = "Chonggu GL A/C error!";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.BOOK_FLG = AICSAI10.DATA.KEY.REDEFINES13.GLBOOK;
            AICQITM.INPUT_DATA.NO = AICSAI10.DATA.DATA_TXT.ITM_PL;
            WS_ERR_INF = "Huidui error!";
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICQITM.RC.RTNCODE == 0) {
                CEP.TRC(SCCGWA, "LBY992============");
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ITM_LVL);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                WK_PL_NAME = AICQITM.OUTPUT_DATA.CHS_NM;
                if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                    WS_ERR_INF = "Huidui GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (!AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("5")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_SUN_YI;
                    WS_ERR_INF = "Huidui GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSAI10.DATA.KEY.REDEFINES13.GLBOOK;
                if ("999999".trim().length() == 0) AIRCMIB.KEY.BR = 0;
                else AIRCMIB.KEY.BR = Integer.parseInt("999999");
                AIRCMIB.KEY.ITM = AICSAI10.DATA.DATA_TXT.ITM_PL;
                AIRCMIB.KEY.SEQ = AICSAI10.DATA.DATA_TXT.PL_SEQ;
                CEP.TRC(SCCGWA, AIRCMIB);
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AICPCMIB.RC.RC_CODE != 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
                    WS_ERR_INF = "Huidui GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, AIRCMIB.STS);
                    if (AIRCMIB.STS != 'N') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_ERRO;
                        WS_ERR_INF = "Huidui GL A/C error!";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRCMIB.EXP_DATE);
                    if (AIRCMIB.EXP_DATE < AICSAI10.DATA.EXP_DATE) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.EXPDT_GT_CMIB_EXPDT;
                        WS_ERR_INF = "Huidui GL A/C error!";
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        if (AICRCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRAI10);
        AIRAI10.KEY.TYP = "PAI10";
        BPCPRMM.EFF_DT = AICSAI10.DATA.EFF_DATE;
        AIRAI10.KEY.CD = AICSAI10.DATA.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRAI10.KEY.CD, AIRAI10.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRAI10);
        AIRAI10.KEY.TYP = "PAI10";
        BPCPRMM.EFF_DT = AICSAI10.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSAI10.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LBY------------------");
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.GLBOOK);
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.CCY);
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.ITM);
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.ITM_SEQ);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.REDEFINES13.SEQ_ITM);
        CEP.TRC(SCCGWA, AICSAI10.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSAI10.DATA.EXP_DATE);
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRAI10);
        IBS.init(SCCGWA, AICHA10O);
        IBS.init(SCCGWA, AICHA10N);
        AIRAI10.KEY.TYP = "PAI10";
        AIRAI10.KEY.CD = AICSAI10.DATA.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRAI10.KEY.CD, AIRAI10.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSAI10.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        CEP.TRC(SCCGWA, "LBY------------------");
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.GLBOOK);
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.CCY);
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.ITM);
        CEP.TRC(SCCGWA, AIRAI10.KEY.REDEFINES5.ITM_SEQ);
        CEP.TRC(SCCGWA, AICSAI10.DATA.KEY.REDEFINES13.SEQ_ITM);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSAI10.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRAI10.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSAI10.DATA.DESC.equalsIgnoreCase(AIRAI10.DESC) 
            && AICSAI10.DATA.CDESC.equalsIgnoreCase(AIRAI10.CDESC) 
            && AICSAI10.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHA10O.KEY.TYP = "PAI10";
        AICHA10O.KEY.CD = AIRAI10.KEY.CD;
