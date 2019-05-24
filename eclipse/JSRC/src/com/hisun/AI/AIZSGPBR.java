package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
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

public class AIZSGPBR {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "GPBR";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "BR GROUP MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    int WS_M = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    AIZSGPBR_WS_DATA WS_DATA = new AIZSGPBR_WS_DATA();
    char WS_FND_FLG = ' ';
    char WS_BR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCSIC BPCSIC = new BPCSIC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRGPBR AIRGPBR = new AIRGPBR();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHGPBR AICHGPBR = new AICHGPBR();
    AICHGPBR AICHBRN = new AICHGPBR();
    AICHGPBR AICHBRO = new AICHGPBR();
    SCCGWA SCCGWA;
    AICSGPBR AICSGPBR;
    public void MP(SCCGWA SCCGWA, AICSGPBR AICSGPBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSGPBR = AICSGPBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSGPBR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSGPBR.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSGPBR.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSGPBR.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSGPBR.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSGPBR);
        CEP.TRC(SCCGWA, AICSGPBR.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSGPBR.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSGPBR.DATA.KEY.REDEFINES13.BR_GUP);
        CEP.TRC(SCCGWA, AICSGPBR.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSGPBR.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSGPBR.DATA.DATA_TXT.DATA_INF[1-1].BR);
        if (AICSGPBR.INFO.FUNC == 'A' 
            || AICSGPBR.INFO.FUNC == 'M' 
            || AICSGPBR.INFO.FUNC == 'D' 
            || AICSGPBR.INFO.FUNC == 'I') {
            if (AICSGPBR.DATA.KEY.REDEFINES13.BR_GUP.trim().length() == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_GROUP_BR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSGPBR.INFO.FUNC == 'A' 
            || AICSGPBR.INFO.FUNC == 'M') {
            if (AICSGPBR.DATA.EFF_DATE == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_EFF_DAYS_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSGPBR.DATA.EFF_DATE > AICSGPBR.DATA.EXP_DATE) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.EFF_DT_MUST_GT_EXP_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_BR_FLG = 'Y';
            for (WS_I = 1; WS_I <= 40; WS_I += 1) {
                if (AICSGPBR.DATA.DATA_TXT.DATA_INF[WS_I-1].BR != 0) {
                    WS_BR_FLG = 'N';
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = AICSGPBR.DATA.DATA_TXT.DATA_INF[WS_I-1].BR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    if (BPCPQORG.ATTR != '2') {
                        WS_MSG_ERR = AICMSG_ERROR_MSG.CENTRE_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (WS_BR_FLG == 'Y') {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_BR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRGPBR);
        AIRGPBR.KEY.TYP = "GPBR";
        BPCPRMM.EFF_DT = AICSGPBR.DATA.EFF_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        AIRGPBR.KEY.CD = AICSGPBR.DATA.KEY.REDEFINES13.BR_GUP;
        IBS.CPY2CLS(SCCGWA, AIRGPBR.KEY.CD, AIRGPBR.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRGPBR);
        AIRGPBR.KEY.TYP = "GPBR";
        BPCPRMM.EFF_DT = AICSGPBR.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSGPBR.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRGPBR);
        IBS.init(SCCGWA, AICHBRO);
        IBS.init(SCCGWA, AICHBRN);
        AIRGPBR.KEY.TYP = "GPBR";
        AIRGPBR.KEY.CD = AICSGPBR.DATA.KEY.REDEFINES13.BR_GUP;
        IBS.CPY2CLS(SCCGWA, AIRGPBR.KEY.CD, AIRGPBR.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSGPBR.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSGPBR.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRGPBR.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSGPBR.DATA.DESC.equalsIgnoreCase(AIRGPBR.DESC) 
            && AICSGPBR.DATA.CDESC.equalsIgnoreCase(AIRGPBR.CDESC) 
            && AICSGPBR.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHBRO.KEY.TYP = "GPBR";
        AICHBRO.KEY.BR_GUP = AIRGPBR.KEY.REDEFINES5.BR_GUP;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRGPBR.DATA_TXT);
