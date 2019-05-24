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

public class AIZSGPCY {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "GPCY";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "BR GROUP MAINTENANCE";
    int WS_I = 0;
    int WS_J = 0;
    int WS_M = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    AIZSGPCY_WS_DATA WS_DATA = new AIZSGPCY_WS_DATA();
    char WS_FND_FLG = ' ';
    char WS_CCY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCSIC BPCSIC = new BPCSIC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRGPCY AIRGPCY = new AIRGPCY();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHGPCY AICHGPCY = new AICHGPCY();
    AICHGPCY AICHCYN = new AICHGPCY();
    AICHGPCY AICHCYO = new AICHGPCY();
    SCCGWA SCCGWA;
    AICSGPCY AICSGPCY;
    public void MP(SCCGWA SCCGWA, AICSGPCY AICSGPCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSGPCY = AICSGPCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSGPCY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSGPCY.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSGPCY.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSGPCY.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSGPCY.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSGPCY);
        CEP.TRC(SCCGWA, AICSGPCY.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.KEY.CD);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.DESC);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.CDESC);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.KEY.REDEFINES13.CY_GUP);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSGPCY.DATA.DATA_TXT.DATA_INF[1-1].CCY);
        if (AICSGPCY.INFO.FUNC == 'A' 
            || AICSGPCY.INFO.FUNC == 'M' 
            || AICSGPCY.INFO.FUNC == 'D' 
            || AICSGPCY.INFO.FUNC == 'I') {
            if (AICSGPCY.DATA.KEY.REDEFINES13.CY_GUP.trim().length() == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_GROUP_CCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSGPCY.INFO.FUNC == 'A' 
            || AICSGPCY.INFO.FUNC == 'M') {
            WS_CCY_FLG = 'Y';
            for (WS_I = 1; WS_I <= 40; WS_I += 1) {
                if (AICSGPCY.DATA.DATA_TXT.DATA_INF[WS_I-1].CCY.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCSIC);
                    BPCSIC.FUNC = "03";
                    BPCSIC.CCY = AICSGPCY.DATA.DATA_TXT.DATA_INF[WS_I-1].CCY;
                    IBS.init(SCCGWA, SCCCALL);
                    SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
                    SCCCALL.COMMAREA_PTR = BPCSIC;
                    SCCCALL.ERR_FLDNO = 16;
                    SCCCALL.CONTINUE_FLG = 'Y';
                    IBS.CALL(SCCGWA, SCCCALL);
                    WS_CCY_FLG = 'N';
                }
            }
            if (WS_CCY_FLG == 'Y') {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_CCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRGPCY);
        AIRGPCY.KEY.TYP = "GPCY";
        BPCPRMM.EFF_DT = AICSGPCY.DATA.EFF_DATE;
        CEP.TRC(SCCGWA, AICSGPCY.DATA.EFF_DATE);
        AIRGPCY.KEY.CD = AICSGPCY.DATA.KEY.REDEFINES13.CY_GUP;
        IBS.CPY2CLS(SCCGWA, AIRGPCY.KEY.CD, AIRGPCY.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRGPCY);
        AIRGPCY.KEY.TYP = "GPCY";
        BPCPRMM.EFF_DT = AICSGPCY.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSGPCY.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRGPCY);
        IBS.init(SCCGWA, AICHCYO);
        IBS.init(SCCGWA, AICHCYN);
        AIRGPCY.KEY.TYP = "GPCY";
        AIRGPCY.KEY.CD = AICSGPCY.DATA.KEY.REDEFINES13.CY_GUP;
        IBS.CPY2CLS(SCCGWA, AIRGPCY.KEY.CD, AIRGPCY.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSGPCY.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSGPCY.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRGPCY.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSGPCY.DATA.DESC.equalsIgnoreCase(AIRGPCY.DESC) 
            && AICSGPCY.DATA.CDESC.equalsIgnoreCase(AIRGPCY.CDESC) 
            && AICSGPCY.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHCYO.KEY.TYP = "GPCY";
        AICHCYO.KEY.CY_GUP = AIRGPCY.KEY.REDEFINES5.CY_GUP;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRGPCY.DATA_TXT);
