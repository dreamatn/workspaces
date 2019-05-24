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

public class AIZSPAI2 {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI02";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 2 MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    AIZSPAI2_WS_DATA WS_DATA = new AIZSPAI2_WS_DATA();
    char WS_FND_FLG = ' ';
    char WS_LVL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRPAI2 AIRPAI2 = new AIRPAI2();
    BPCSIC BPCSIC = new BPCSIC();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI2 AICHPAI2 = new AICHPAI2();
    AICHPAI2 AICHAI2N = new AICHPAI2();
    AICHPAI2 AICHAI2O = new AICHPAI2();
    SCCGWA SCCGWA;
    AICSPAI2 AICSPAI2;
    public void MP(SCCGWA SCCGWA, AICSPAI2 AICSPAI2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI2 = AICSPAI2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI2 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPAI2.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI2.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI2.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI2.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSPAI2);
        CEP.TRC(SCCGWA, AICSPAI2);
        CEP.TRC(SCCGWA, AICSPAI2.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.KEY.CD);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.DESC);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.CDESC);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI2.DATA.DATA_TXT.LVL);
        if (AICSPAI2.INFO.FUNC == 'A' 
            || AICSPAI2.INFO.FUNC == 'M' 
            || AICSPAI2.INFO.FUNC == 'D' 
            || AICSPAI2.INFO.FUNC == 'I') {
            if (AICSPAI2.DATA.KEY.REDEFINES13.CODE.trim().length() == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI2_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSPAI2.INFO.FUNC == 'A' 
            || AICSPAI2.INFO.FUNC == 'M') {
            if (AICSPAI2.DATA.DATA_TXT.LVL == '0') {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_ENTRY_LVL_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI2);
        AIRPAI2.KEY.TYP = "PAI02";
        BPCPRMM.EFF_DT = AICSPAI2.DATA.EFF_DATE;
        AIRPAI2.KEY.CD = AICSPAI2.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI2.KEY.CD, AIRPAI2.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI2);
        AIRPAI2.KEY.TYP = "PAI02";
        BPCPRMM.EFF_DT = AICSPAI2.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI2.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI2);
        IBS.init(SCCGWA, AICHAI2O);
        IBS.init(SCCGWA, AICHAI2N);
        AIRPAI2.KEY.TYP = "PAI02";
        AIRPAI2.KEY.CD = AICSPAI2.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI2.KEY.CD, AIRPAI2.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSPAI2.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI2.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI2.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI2.DATA.DESC.equalsIgnoreCase(AIRPAI2.DESC) 
            && AICSPAI2.DATA.CDESC.equalsIgnoreCase(AIRPAI2.CDESC) 
            && AICSPAI2.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI2O.KEY.TYP = "PAI02";
        AICHAI2O.KEY.CODE = AIRPAI2.KEY.REDEFINES5.CODE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI2.DATA_TXT);
