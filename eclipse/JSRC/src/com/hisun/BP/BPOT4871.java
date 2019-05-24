package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4871 {
    String CPN_S_RULE = "BP-S-MGM-RULE    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_CNT = 0;
    char WS_END_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSRULE BPCSRULE = new BPCSRULE();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCFTRT BPCFTRT = new BPCFTRT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4870_AWA_4870 BPB4870_AWA_4870;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4871 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4870_AWA_4870>");
        BPB4870_AWA_4870 = (BPB4870_AWA_4870) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPCFTRT.KEY.TYP = "TRT";
        BPCFTRT.KEY.CD = BPB4870_AWA_4870.TX_CODE;
        S000_CALL_BPZPRMR();
        if (!(BPB4870_AWA_4870.CTRL_TYP == 'M' 
            || BPB4870_AWA_4870.CTRL_TYP == 'B')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_FLD_NO = BPB4870_AWA_4870.CTRL_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (!(BPB4870_AWA_4870.MSG_TYPE == 'E' 
            || BPB4870_AWA_4870.MSG_TYPE == 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_FLD_NO = BPB4870_AWA_4870.MSG_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4870_AWA_4870.MSG_TYPE == 'A') {
            if (BPB4870_AWA_4870.ATH_LVL.trim().length() == 0 
                || (!IBS.isNumeric(BPB4870_AWA_4870.ATH_LVL))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB4870_AWA_4870.ATH_LVL_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (!(BPB4870_AWA_4870.AUTO_IND == 'Y' 
            || BPB4870_AWA_4870.AUTO_IND == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_FLD_NO = BPB4870_AWA_4870.AUTO_IND_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[2-1].OFFSET);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[3-1].OFFSET);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[4-1].OFFSET);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[5-1].OFFSET);
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA == ' ') {
                if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM.trim().length() > 0 
                    || !(BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET == ' ' 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET == 000) 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].LOGICAL != ' ' 
                    || !(BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA == ' ' 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA == '0') 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM2.trim().length() > 0 
                    || !(BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET2 == ' ' 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET2 == 000) 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].VALUE.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_END_FLAG = 'Y';
            } else {
                if (WS_END_FLAG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (!(BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA == '0' 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA == '1')) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM.trim().length() == 0 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].LOGICAL == ' ' 
                    || BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA2 == ' ' 
                    || (BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM2.trim().length() == 0 
                    && BPB4870_AWA_4870.RULE_ARR[WS_I-1].VALUE.trim().length() == 0)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA2 == '2') {
                    if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM2.trim().length() > 0 
                        || !(BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET2 == 0 
                        || BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET2 == ' ') 
                        || BPB4870_AWA_4870.RULE_ARR[WS_I-1].VALUE.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                        WS_FLD_NO = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA2_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                } else {
                    if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM2.trim().length() == 0 
                        || BPB4870_AWA_4870.RULE_ARR[WS_I-1].VALUE.trim().length() > 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                        WS_FLD_NO = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA2_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
                WS_CNT = WS_I;
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRULE);
        BPCSRULE.FUNC = 'A';
        BPCSRULE.KEY.TX_CODE = BPB4870_AWA_4870.TX_CODE;
        BPCSRULE.KEY.CHNL_NO = BPB4870_AWA_4870.CHNL_NO;
        BPCSRULE.KEY.SERV_CODE = BPB4870_AWA_4870.SRV_CODE;
        BPCSRULE.RULE_STS = 'N';
        BPCSRULE.DESC = BPB4870_AWA_4870.DESC;
        BPCSRULE.CTRL_TYPE = BPB4870_AWA_4870.CTRL_TYP;
        BPCSRULE.MSG_CODE = BPB4870_AWA_4870.MSG_CODE;
        BPCSRULE.MSG_DESC = BPB4870_AWA_4870.MSG_DESC;
        BPCSRULE.MSG_TYPE = BPB4870_AWA_4870.MSG_TYPE;
        BPCSRULE.ATH_LVL = BPB4870_AWA_4870.ATH_LVL;
        BPCSRULE.AUTO_IND = BPB4870_AWA_4870.AUTO_IND;
        BPCSRULE.LINES_CNT = WS_CNT;
        CEP.TRC(SCCGWA, BPCSRULE.LINES_CNT);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].FR_AREA);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].ITEM);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].OFFSET);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].LOGICAL);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].FR_AREA2);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].ITEM2);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].OFFSET2);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[1-1].VALUE);
        for (WS_I = 1; WS_I <= BPCSRULE.LINES_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET);
            BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].FR_AREA = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA;
            BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].ITEM = BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM;
            BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].OFFSET = BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET;
            BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].LOGICAL = BPB4870_AWA_4870.RULE_ARR[WS_I-1].LOGICAL;
            BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].FR_AREA2 = BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA2;
            if (BPB4870_AWA_4870.RULE_ARR[WS_I-1].FR_AREA2 == '2') {
                BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].VALUE = BPB4870_AWA_4870.RULE_ARR[WS_I-1].VALUE;
            } else {
                BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].ITEM2 = BPB4870_AWA_4870.RULE_ARR[WS_I-1].ITEM2;
                BPCSRULE.ITEM_LST.ITEM_LST_DATA[WS_I-1].OFFSET2 = BPB4870_AWA_4870.RULE_ARR[WS_I-1].OFFSET2;
            }
        }
        S000_CALL_BPZSRULE();
    }
    public void S000_CALL_BPZSRULE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_RULE, BPCSRULE);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPCFTRT;
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            WS_FLD_NO = BPB4870_AWA_4870.TX_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
