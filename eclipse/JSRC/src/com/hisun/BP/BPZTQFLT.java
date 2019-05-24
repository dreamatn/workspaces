package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZTQFLT {
    boolean pgmRtn = false;
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_PARM = "BP-PARM-READ        ";
    String K_PARM_TYPH = "TYPH ";
    String K_PARM_FLT_T = "FLT-T";
    String WS_ERR_MSG = " ";
    int WS_CNT_A = 0;
    int WS_CNT_B = 0;
    char WS_TRULE_FLT_ITEM_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRTRULE BPRTRULE = new BPRTRULE();
    BPCNRULE BPCNRULE = new BPCNRULE();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCTQFLT BPCTQFLT;
    public void MP(SCCGWA SCCGWA, BPCTQFLT BPCTQFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTQFLT = BPCTQFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTQFLT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTQFLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCTQFLT.FUNC == 'A') {
            B020_INQUIRE_FOR_ADD();
            if (pgmRtn) return;
        } else if (BPCTQFLT.FUNC == 'M') {
            B030_INQUIRE_FOR_MODIFY();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCTQFLT.DAT);
        B040_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCTQFLT.FUNC != 'A' 
            && BPCTQFLT.FUNC != 'M') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCTQFLT.TYP.trim().length() == 0) 
            || (BPCTQFLT.TYPH_CD.trim().length() == 0) 
            || (BPCTQFLT.FLT_CD.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_TYPH;
        BPCOQPCD.INPUT_DATA.CODE = BPCTQFLT.TYPH_CD;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TYPH_CODE_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_FLT_T;
        BPCOQPCD.INPUT_DATA.CODE = BPCTQFLT.FLT_CD;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_CODE_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_INQUIRE_FOR_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (BPCTQFLT.TYPH_CD == null) BPCTQFLT.TYPH_CD = "";
        JIBS_tmp_int = BPCTQFLT.TYPH_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCTQFLT.TYPH_CD += " ";
        BPRPRMT.KEY.CD = BPCTQFLT.TYPH_CD + BPRPRMT.KEY.CD.substring(10);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (BPCTQFLT.FLT_CD == null) BPCTQFLT.FLT_CD = "";
        JIBS_tmp_int = BPCTQFLT.FLT_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCTQFLT.FLT_CD += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 11 - 1) + BPCTQFLT.FLT_CD + BPRPRMT.KEY.CD.substring(11 + 10 - 1);
        BPRPRMT.KEY.TYP = "TRULE";
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (BPCPRMR.RC.RC_RTNCODE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_RULE_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCTQFLT.FLT_CD);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCNRULE);
        BPRPRMT.KEY.CD = BPCTQFLT.FLT_CD;
        BPRPRMT.KEY.TYP = "NRULE";
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
