package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQPDM;
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

public class AIZSPAI6 {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI06";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 5 MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    AIZSPAI6_WS_DATA WS_DATA = new AIZSPAI6_WS_DATA();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRPAI6 AIRPAI6 = new AIRPAI6();
    BPCSIC BPCSIC = new BPCSIC();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI6 AICHPAI6 = new AICHPAI6();
    AICHPAI6 AICHAI6N = new AICHPAI6();
    AICHPAI6 AICHAI6O = new AICHPAI6();
    SCCGWA SCCGWA;
    AICSPAI6 AICSPAI6;
    public void MP(SCCGWA SCCGWA, AICSPAI6 AICSPAI6) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI6 = AICSPAI6;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI6 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPAI6.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI6.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI6.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI6.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSPAI6.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.KEY.REDEFINES13.CODE);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.DATA_TXT.CNTR_FLG);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.DATA_TXT.APP);
        CEP.TRC(SCCGWA, AICSPAI6.DATA.DATA_TXT.GL_FLG);
        if (AICSPAI6.INFO.FUNC == 'A' 
            || AICSPAI6.INFO.FUNC == 'M' 
            || AICSPAI6.INFO.FUNC == 'D' 
            || AICSPAI6.INFO.FUNC == 'I') {
            if (AICSPAI6.DATA.KEY.REDEFINES13.CODE.trim().length() == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI6_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI6);
        AIRPAI6.KEY.TYP = "PAI06";
        BPCPRMM.EFF_DT = AICSPAI6.DATA.EFF_DATE;
        AIRPAI6.KEY.CD = AICSPAI6.DATA.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI6.KEY.CD, AIRPAI6.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI6);
        AIRPAI6.KEY.TYP = "PAI06";
        BPCPRMM.EFF_DT = AICSPAI6.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI6.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI6);
        IBS.init(SCCGWA, AICHAI6O);
        IBS.init(SCCGWA, AICHAI6N);
        AIRPAI6.KEY.TYP = "PAI06";
        AIRPAI6.KEY.CD = AICSPAI6.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI6.KEY.CD, AIRPAI6.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI6.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI6.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI6.DATA.DESC.equalsIgnoreCase(AIRPAI6.DESC) 
            && AICSPAI6.DATA.CDESC.equalsIgnoreCase(AIRPAI6.CDESC) 
            && AICSPAI6.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI6O.KEY.TYP = "PAI06";
        AICHAI6O.KEY.CODE = AIRPAI6.KEY.REDEFINES5.CODE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI6.DATA_TXT);
