package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCRULT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_PARM = "BP-PARM-READ        ";
    String K_PARM_TYPH = "TYPH ";
    String K_PARM_FLT_T = "FLT-T";
    String K_PARM_FLT_D = "FLT-D";
    String WS_ERR_MSG = " ";
    int WS_CNT_A = 0;
    int WS_CNT_B = 0;
    char WS_FROM_ITEM_USE_FLG = ' ';
    char WS_TO_ITEM_USE_FLG = ' ';
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
    BPCRCKPM BPCRCKPM;
    public void MP(SCCGWA SCCGWA, BPCRCKPM BPCRCKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCKPM = BPCRCKPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZCRULT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRCKPM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PARM_DATA();
        B020_CHECK_INPUT_DATA();
    }
    public void B010_CHECK_PARM_DATA() throws IOException,SQLException,Exception {
        if (BPCRCKPM.TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCRCKPM.CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_TYPE_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_TYPH;
        if (BPCRCKPM.CODE == null) BPCRCKPM.CODE = "";
        JIBS_tmp_int = BPCRCKPM.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCRCKPM.CODE += " ";
        BPCOQPCD.INPUT_DATA.CODE = BPCRCKPM.CODE.substring(0, 10);
        S000_CALL_BPZPQPCD();
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TYPH_CODE_NOTFND;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_FLT_T;
        if (BPCRCKPM.CODE == null) BPCRCKPM.CODE = "";
        JIBS_tmp_int = BPCRCKPM.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCRCKPM.CODE += " ";
        BPCOQPCD.INPUT_DATA.CODE = BPCRCKPM.CODE.substring(11 - 1, 11 + 10 - 1);
        S000_CALL_BPZPQPCD();
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_CODE_NOTFND;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCRCKPM.VAL.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_RULE_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRULE);
        BPRTRULE.KEY.CD = BPCRCKPM.CODE;
        BPRTRULE.DATA_LEN = 340;
        if (BPCRCKPM.VAL == null) BPCRCKPM.VAL = "";
        JIBS_tmp_int = BPCRCKPM.VAL.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPCRCKPM.VAL += " ";
        IBS.CPY2CLS(SCCGWA, BPCRCKPM.VAL.substring(0, BPRTRULE.DATA_LEN), BPRTRULE.DATA_TXT);
        CEP.TRC(SCCGWA, BPRTRULE.DATA_TXT);
        for (WS_CNT_A = 1; WS_CNT_A <= 10; WS_CNT_A += 1) {
            CEP.TRC(SCCGWA, BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_ITEM);
            CEP.TRC(SCCGWA, BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].USE_FLG);
            if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_ITEM.trim().length() > 0) {
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].USE_FLG != 'Y' 
                    && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].USE_FLG != 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USE_FLG_ERROR;
                    S000_ERR_MSG_PROC();
                }
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].USE_FLG == 'Y' 
                    && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_RULE == ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_RULE_USE;
                    S000_ERR_MSG_PROC();
                }
                WS_FROM_ITEM_USE_FLG = 'N';
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].USE_FLG == 'Y') {
                    IBS.init(SCCGWA, BPCPRMR);
                    IBS.init(SCCGWA, BPRPRMT);
                    IBS.init(SCCGWA, BPCNRULE);
                    if (BPRTRULE.KEY.CD == null) BPRTRULE.KEY.CD = "";
                    JIBS_tmp_int = BPRTRULE.KEY.CD.length();
                    for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRULE.KEY.CD += " ";
                    BPRPRMT.KEY.CD = BPRTRULE.KEY.CD.substring(11 - 1, 11 + 10 - 1);
                    BPRPRMT.KEY.TYP = "NRULE";
                    BPCPRMR.DAT_PTR = BPRPRMT;
                    S000_CALL_BPZPRMR();
                    if (BPCPRMR.RC.RC_RTNCODE != 0) {
                        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                        S000_ERR_MSG_PROC();
                    }
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCNRULE);
                    for (WS_CNT_B = 1; WS_CNT_B <= 10; WS_CNT_B += 1) {
                        if ((BPCNRULE.DATA.DAT_TXT[WS_CNT_B-1].FLT_ITEM.equalsIgnoreCase(BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_ITEM)) 
                            && BPCNRULE.DATA.DAT_TXT[WS_CNT_B-1].USE_FLG == 'Y') {
                            WS_FROM_ITEM_USE_FLG = 'Y';
                        }
                    }
                    if (WS_FROM_ITEM_USE_FLG == 'N') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_NOTUSE_IN_NORMAL;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_RULE != 'B' 
                    && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_RULE != 'D' 
                    && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_RULE != 'T' 
                    && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_RULE != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_RULE_ERROR;
                    S000_ERR_MSG_PROC();
                }
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_RULE == 'T' 
                    && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].TO_ITEM.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_POINT_TO_ITEM;
                    S000_ERR_MSG_PROC();
                }
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_ITEM.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCOQPCD);
                    BPCOQPCD.INPUT_DATA.TYPE = K_PARM_FLT_D;
                    BPCOQPCD.INPUT_DATA.CODE = BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].FLT_ITEM;
                    S000_CALL_BPZPQPCD();
                    if (BPCOQPCD.RC.RC_CODE != 0) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_ITEM_NOTFND;
                            S000_ERR_MSG_PROC();
                        } else {
                            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                            S000_ERR_MSG_PROC();
                        }
                    }
                }
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].TO_ITEM.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCOQPCD);
                    BPCOQPCD.INPUT_DATA.TYPE = K_PARM_FLT_D;
                    BPCOQPCD.INPUT_DATA.CODE = BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].TO_ITEM;
                    S000_CALL_BPZPQPCD();
                    if (BPCOQPCD.RC.RC_CODE != 0) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TO_ITEM_NOTFND;
                            S000_ERR_MSG_PROC();
                        } else {
                            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                            S000_ERR_MSG_PROC();
                        }
                    }
                }
                if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].TO_ITEM.trim().length() > 0) {
                    WS_TO_ITEM_USE_FLG = 'N';
                    for (WS_CNT_B = 1; WS_CNT_B <= 10; WS_CNT_B += 1) {
                        if ((BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_B-1].FLT_ITEM.equalsIgnoreCase(BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_A-1].TO_ITEM)) 
                            && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT_B-1].USE_FLG == 'Y') {
                            WS_TO_ITEM_USE_FLG = 'Y';
                        }
                    }
                    if (WS_TO_ITEM_USE_FLG == 'N') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TO_ITEM_NOTUSE;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PARM, BPCPRMR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCKPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCKPM = ");
            CEP.TRC(SCCGWA, BPCRCKPM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
