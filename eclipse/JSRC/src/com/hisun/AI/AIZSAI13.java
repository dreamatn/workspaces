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

public class AIZSAI13 {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI13";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 13 MAINTENANCE";
    int K_BR_CONS = 999999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INF = " ";
    AIZSAI13_WS_DATA WS_DATA = new AIZSAI13_WS_DATA();
    String WK_ITM_NAME = " ";
    char AIZSAI13_FILLER25 = 0X02;
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQITM AICQITM = new AICPQITM();
    AIRAI13 AIRAI13 = new AIRAI13();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHAI13 AICHAI13 = new AICHAI13();
    AICHAI13 AICHA13N = new AICHAI13();
    AICHAI13 AICHA13O = new AICHAI13();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    AICSAI13 AICSAI13;
    public void MP(SCCGWA SCCGWA, AICSAI13 AICSAI13) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSAI13 = AICSAI13;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSAI13 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSAI13.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSAI13.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSAI13.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSAI13.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSAI13.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSAI13.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSAI13.DATA.KEY.REDEFINES13.GLBOOK);
        CEP.TRC(SCCGWA, AICSAI13.DATA.KEY.REDEFINES13.ITM_FLG);
        CEP.TRC(SCCGWA, AICSAI13.DATA.KEY.REDEFINES13.ITM);
        CEP.TRC(SCCGWA, AICSAI13.DATA.KEY.REDEFINES13.BAL_SIGN);
        CEP.TRC(SCCGWA, AICSAI13.DATA.DESC);
        CEP.TRC(SCCGWA, AICSAI13.DATA.CDESC);
        CEP.TRC(SCCGWA, AICSAI13.DATA.DATA_TXT);
        CEP.TRC(SCCGWA, AICSAI13.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSAI13.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSAI13.DATA.DATA_TXT.OPR_FLG);
        if (AICSAI13.INFO.FUNC == 'A' 
            || AICSAI13.INFO.FUNC == 'M' 
            || AICSAI13.INFO.FUNC == 'D' 
            || AICSAI13.INFO.FUNC == 'I') {
            if (AICSAI13.DATA.KEY.REDEFINES13.GLBOOK.trim().length() == 0 
                || AICSAI13.DATA.KEY.REDEFINES13.ITM_FLG.trim().length() == 0 
                || AICSAI13.DATA.KEY.REDEFINES13.ITM.equalsIgnoreCase("0") 
                || AICSAI13.DATA.KEY.REDEFINES13.BAL_SIGN == ' ') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AI13_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSAI13.INFO.FUNC == 'M') {
            if (AICSAI13.DATA.EFF_DATE > AICSAI13.DATA.EXP_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_GT_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSAI13.DATA.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXP_MUST_GT_ACDT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSAI13.INFO.FUNC == 'A') {
            if (AICSAI13.DATA.EFF_DATE > AICSAI13.DATA.EXP_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_GT_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSAI13.DATA.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFF_DAYS_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.BOOK_FLG = AICSAI13.DATA.KEY.REDEFINES13.GLBOOK;
            AICQITM.INPUT_DATA.NO = AICSAI13.DATA.KEY.REDEFINES13.ITM;
            WS_ERR_INF = "Changkou error!";
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICQITM.RC.RTNCODE == 0) {
                WK_ITM_NAME = AICQITM.OUTPUT_DATA.CHS_NM;
                CEP.TRC(SCCGWA, "LBY990============");
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ITM_LVL);
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.TYPE);
                if (AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6") 
                    || AICQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("7")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMR_TYPE_NO_MATCH;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.STS);
                if (AICQITM.OUTPUT_DATA.STS != 'A') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NO_ATV_STS;
                    WS_ERR_INF = "Changkou GL A/C error!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRAI13);
        AIRAI13.KEY.TYP = "PAI13";
        BPCPRMM.EFF_DT = AICSAI13.DATA.EFF_DATE;
        AIRAI13.KEY.CD = AICSAI13.DATA.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRAI13.KEY.CD, AIRAI13.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRAI13);
        AIRAI13.KEY.TYP = "PAI13";
        BPCPRMM.EFF_DT = AICSAI13.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSAI13.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LBY------------------");
        CEP.TRC(SCCGWA, AIRAI13.KEY.REDEFINES5.GLBOOK);
        CEP.TRC(SCCGWA, AIRAI13.KEY.REDEFINES5.ITM_FLG);
        CEP.TRC(SCCGWA, AIRAI13.KEY.REDEFINES5.ITM);
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRAI13);
        IBS.init(SCCGWA, AICHA13O);
        IBS.init(SCCGWA, AICHA13N);
        AIRAI13.KEY.TYP = "PAI13";
        AIRAI13.KEY.CD = AICSAI13.DATA.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRAI13.KEY.CD, AIRAI13.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSAI13.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        CEP.TRC(SCCGWA, "LBY------------------");
        CEP.TRC(SCCGWA, AIRAI13.KEY.REDEFINES5.GLBOOK);
        CEP.TRC(SCCGWA, AIRAI13.KEY.REDEFINES5.ITM_FLG);
        CEP.TRC(SCCGWA, AIRAI13.KEY.REDEFINES5.ITM);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSAI13.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRAI13.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSAI13.DATA.DESC.equalsIgnoreCase(AIRAI13.DESC) 
            && AICSAI13.DATA.CDESC.equalsIgnoreCase(AIRAI13.CDESC) 
            && AICSAI13.DATA.EFF_DATE == BPCPRMM.EFF_DT 
            && AICSAI13.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHA13O.KEY.TYP = "PAI13";
        AICHA13O.KEY.CD = AIRAI13.KEY.CD;
