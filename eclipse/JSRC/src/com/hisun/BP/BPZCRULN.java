package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCRULN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CALL_BPZPQPCD = "BP-P-INQ-PC     ";
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_CNT2 = 0;
    int WS_I = 0;
    String WS_ERR_MSG = " ";
    char WS_COND_FLG0 = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCNRULE BPCNRULE = new BPCNRULE();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCRCKPM BPCRCKPM;
    public void MP(SCCGWA SCCGWA, BPCRCKPM BPCRCKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCKPM = BPCRCKPM;
        CEP.TRC(SCCGWA);
        if (BPCRCKPM.FUNC == 'D') {
            CEP.TRC(SCCGWA, "BPZCRULN return!");
            Z_RET();
            if (pgmRtn) return;
        }
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_COND_FLG0 = ' ';
        IBS.init(SCCGWA, BPCNRULE);
        IBS.init(SCCGWA, BPCOQPCD);
        CEP.TRC(SCCGWA, BPCRCKPM.LEN);
        IBS.CPY2CLS(SCCGWA, BPCRCKPM.VAL, BPCNRULE.DATA);
        CEP.TRC(SCCGWA, BPCRCKPM.VAL);
        CEP.TRC(SCCGWA, BPCNRULE.DATA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CODE_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        BPCNRULE.TYP = BPCRCKPM.TYPE;
        BPCNRULE.CD = BPCRCKPM.CODE;
        IBS.CPY2CLS(SCCGWA, BPCNRULE.CD, BPCNRULE.REDEFINES3);
        CEP.TRC(SCCGWA, BPCRCKPM.TYPE);
        CEP.TRC(SCCGWA, BPCRCKPM.CODE);
        CEP.TRC(SCCGWA, BPCRCKPM.VAL);
        CEP.TRC(SCCGWA, BPCNRULE.TYP);
        CEP.TRC(SCCGWA, BPCNRULE.REDEFINES3.FLT_CD);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[1-1].FLT_ITEM);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[1-1].USE_FLG);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[1-1].NOR_RULE);
        if (BPCNRULE.TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RULE_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCNRULE.REDEFINES3.FLT_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_CODE_MUST_INPUT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLT_CODE_MUST_INPUT, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_CHECK_FLT_CD();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCNRULE.DATA);
        if (JIBS_tmp_str[0].trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_DATA_MUST_INPUT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLT_DATA_MUST_INPUT, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CODE_INFO() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE);
            if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM.trim().length() > 0) {
                T000_CHECK_NRULE_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_CHECK_NRULE_DATA() throws IOException,SQLException,Exception {
        T000_GET_PARMC_INFO();
        if (pgmRtn) return;
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG != 'Y' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG != 'N' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG != ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USE_FLG_ERR;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_USE_FLG_ERR, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE != 'B' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE != 'D' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE != 'T' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE != ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NRULE_ERR;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NRULE_ERR, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE != 'B' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE != 'D' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE != 'T' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE != ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SRULE_ERR;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SRULE_ERR, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE == 'T' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM1.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ITEM_MUST_INPUT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ITEM_MUST_INPUT, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE == 'T' 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM2.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ITEM_MUST_INPUT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ITEM_MUST_INPUT, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE);
        CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE);
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG == 'Y' 
            && (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE == ' ' 
            || BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE == ' ')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RULE_MUST_INPUT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RULE_MUST_INPUT, BPCRCKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_CNT1 = 1;
            while (BPCNRULE.DATA.DAT_TXT[WS_CNT1-1].FLT_ITEM.trim().length() != 0 
                && WS_CNT1 <= 10) {
                CEP.TRC(SCCGWA, WS_CNT1);
                if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM1.equalsIgnoreCase(BPCNRULE.DATA.DAT_TXT[WS_CNT1-1].FLT_ITEM)) {
                    if (BPCNRULE.DATA.DAT_TXT[WS_CNT1-1].USE_FLG != 'Y') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TO_ITEM_ERR;
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TO_ITEM_ERR, BPCRCKPM.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (WS_CNT1 == 10) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TO_ITEM_ERR;
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TO_ITEM_ERR, BPCRCKPM.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                WS_CNT1 = WS_CNT1 + 1;
            }
        }
        if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG == ' ' 
            || BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM.trim().length() == 0) {
            IBS.init(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1]);
        }
    }
    public void T000_CHECK_FLT_CD() throws IOException,SQLException,Exception {
        BPCOQPCD.INPUT_DATA.TYPE = "FLT-T";
        BPCOQPCD.INPUT_DATA.CODE = BPCNRULE.REDEFINES3.FLT_CD;
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.TYPE);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
    }
    public void T000_GET_PARMC_INFO() throws IOException,SQLException,Exception {
        BPCOQPCD.INPUT_DATA.TYPE = "FLT-D";
        BPCOQPCD.INPUT_DATA.CODE = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM;
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.TYPE);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZPQPCD, BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRCKPM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
