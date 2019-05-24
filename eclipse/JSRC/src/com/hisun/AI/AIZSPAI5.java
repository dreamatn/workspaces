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

public class AIZSPAI5 {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI05";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 5 MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    AIZSPAI5_WS_DATA WS_DATA = new AIZSPAI5_WS_DATA();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRPAI5 AIRPAI5 = new AIRPAI5();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI5 AICHPAI5 = new AICHPAI5();
    AICHPAI5 AICHAI5N = new AICHPAI5();
    AICHPAI5 AICHAI5O = new AICHPAI5();
    SCCGWA SCCGWA;
    AICSPAI5 AICSPAI5;
    public void MP(SCCGWA SCCGWA, AICSPAI5 AICSPAI5) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI5 = AICSPAI5;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI5 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPAI5.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI5.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI5.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI5.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSPAI5.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.KEY.REDEFINES13.CODE);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.KEY.REDEFINES13.COA_FLG);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.DATA_TXT.COA_FR);
        CEP.TRC(SCCGWA, AICSPAI5.DATA.DATA_TXT.COA_TO);
        if (AICSPAI5.INFO.FUNC == 'A' 
            || AICSPAI5.INFO.FUNC == 'M' 
            || AICSPAI5.INFO.FUNC == 'D' 
            || AICSPAI5.INFO.FUNC == 'I') {
            if (AICSPAI5.DATA.KEY.REDEFINES13.CODE.trim().length() == 0 
                || AICSPAI5.DATA.KEY.REDEFINES13.COA_FLG.trim().length() == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI5_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSPAI5.INFO.FUNC == 'A' 
            || AICSPAI5.INFO.FUNC == 'M') {
            if ((AICSPAI5.DATA.DATA_TXT.COA_FR.compareTo(AICSPAI5.DATA.DATA_TXT.COA_TO) > 0 
                || AICSPAI5.DATA.DATA_TXT.COA_TO.trim().length() == 0)) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_COA_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI5);
        AIRPAI5.KEY.TYP = "PAI05";
        BPCPRMM.EFF_DT = AICSPAI5.DATA.EFF_DATE;
        AIRPAI5.KEY.CD = AICSPAI5.DATA.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI5.KEY.CD, AIRPAI5.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI5);
        AIRPAI5.KEY.TYP = "PAI05";
        BPCPRMM.EFF_DT = AICSPAI5.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI5.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI5);
        IBS.init(SCCGWA, AICHAI5O);
        IBS.init(SCCGWA, AICHAI5N);
        AIRPAI5.KEY.TYP = "PAI05";
        AIRPAI5.KEY.REDEFINES5.CODE = AICSPAI5.DATA.KEY.REDEFINES13.CODE;
        AIRPAI5.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI5.KEY.REDEFINES5);
        AIRPAI5.KEY.REDEFINES5.COA_FLG = AICSPAI5.DATA.KEY.REDEFINES13.COA_FLG;
        AIRPAI5.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI5.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI5.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI5.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI5.DATA.DESC.equalsIgnoreCase(AIRPAI5.DESC) 
            && AICSPAI5.DATA.CDESC.equalsIgnoreCase(AIRPAI5.CDESC) 
            && AICSPAI5.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI5O.KEY.TYP = "PAI05";
        AICHAI5O.KEY.CODE = AIRPAI5.KEY.REDEFINES5.CODE;
        AICHAI5O.KEY.COA_FLG = AIRPAI5.KEY.REDEFINES5.COA_FLG;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI5.DATA_TXT);
