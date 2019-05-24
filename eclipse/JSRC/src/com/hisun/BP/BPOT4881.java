package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4881 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_TAMT = "BP-S-MGM-TAMT    ";
    String CPN_F_RISK_CONTROL = "BP-F-RISK-CONTROL";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_CNT = 0;
    short WS_CNT2 = 0;
    char WS_END_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTAMT BPCSTAMT = new BPCSTAMT();
    BPCFCTRL BPCFCTRL = new BPCFCTRL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCFMSG BPCFMSG = new BPCFMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4880_AWA_4880 BPB4880_AWA_4880;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4881 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4880_AWA_4880>");
        BPB4880_AWA_4880 = (BPB4880_AWA_4880) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B011_CHECK_CTRL();
        B020_ADD_REC_PROC();
    }
    public void B011_CHECK_CTRL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        IBS.init(SCCGWA, BPCFCTRL);
        S000_CALL_BPZFCTRL();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.EFFDTE);
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.EXPDTE);
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.FLAG);
        if (BPB4880_AWA_4880.EFFDTE >= BPB4880_AWA_4880.EXPDTE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            WS_FLD_NO = BPB4880_AWA_4880.EFFDTE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (!(BPB4880_AWA_4880.FLAG.equalsIgnoreCase(">=") 
            || BPB4880_AWA_4880.FLAG.equalsIgnoreCase("<="))) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_FLD_NO = BPB4880_AWA_4880.FLAG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[1-1].LIMIT);
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[1-1].LVL);
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[1-1].REASON);
        for (WS_I = 1; WS_I <= 6; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT);
            CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LVL);
            CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[WS_I-1].REASON);
            if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT < 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT == 0) {
                if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LVL.trim().length() > 0 
                    || BPB4880_AWA_4880.TAMT_ARR[WS_I-1].REASON.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LVL_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_END_FLAG = 'Y';
            } else {
                if (WS_END_FLAG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LVL.trim().length() == 0 
                    || BPB4880_AWA_4880.TAMT_ARR[WS_I-1].REASON.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_LVL;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB4880_AWA_4880.FLAG.equalsIgnoreCase(">=")) {
                    if (WS_I > 1 
                        && BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT < BPB4880_AWA_4880.TAMT_ARR[WS_I - 1-1].LIMIT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                        WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                } else {
                    if (WS_I > 1 
                        && BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT > BPB4880_AWA_4880.TAMT_ARR[WS_I - 1-1].LIMIT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                        WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
                IBS.init(SCCGWA, BPCFMSG);
                IBS.init(SCCGWA, BPCPRMR);
                BPCFMSG.KEY.TYP = "MSG";
                BPCFMSG.KEY.CD = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].REASON;
                S000_CALL_BPZPRMR();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].REASON_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_CNT = WS_I;
            }
        }
        WS_END_FLAG = ' ';
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[1-1].U_LIMIT);
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[1-1].U_LVL);
        CEP.TRC(SCCGWA, BPB4880_AWA_4880.TAMT_ARR[1-1].U_REASON);
        for (WS_I = 1; WS_I <= 6; WS_I += 1) {
            if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT < 0) {
                CEP.TRC(SCCGWA, "EEEEEEEEEEEEEEEEEEEE");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT == 0) {
                if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LVL.trim().length() > 0 
                    || BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_REASON.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFFFFFFF");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_END_FLAG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "GGGGGGGGGGGGGGGGGGGG");
                if (WS_END_FLAG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LVL.trim().length() == 0 
                    || BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_REASON.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB4880_AWA_4880.FLAG.equalsIgnoreCase(">=")) {
                    if (WS_I > 1 
                        && BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT < BPB4880_AWA_4880.TAMT_ARR[WS_I - 1-1].U_LIMIT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                        WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                } else {
                    if (WS_I > 1 
                        && BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT > BPB4880_AWA_4880.TAMT_ARR[WS_I - 1-1].U_LIMIT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                        WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
                if (!IBS.isNumeric(BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LVL)) {
                    CEP.TRC(SCCGWA, "TTTTTTTTTTTTTTTTTTTT");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LVL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                IBS.init(SCCGWA, BPCFMSG);
                IBS.init(SCCGWA, BPCPRMR);
                BPCFMSG.KEY.TYP = "MSG";
                BPCFMSG.KEY.CD = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_REASON;
                S000_CALL_BPZPRMR();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                    CEP.TRC(SCCGWA, "SSSSSSSSSSSSSSSSSSSS");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_REASON_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                WS_CNT2 = WS_I;
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB4880_AWA_4880.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB4880_AWA_4880.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "ZZZZZZZZZZZZZZZZZZZZ");
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTAMT);
        BPCSTAMT.FUNC = 'A';
        BPCSTAMT.TAMT_APP = BPB4880_AWA_4880.AP_MMO;
        BPCSTAMT.TBL_NO = BPB4880_AWA_4880.TBL_NO;
        BPCSTAMT.CHNL = BPB4880_AWA_4880.CHNL;
        BPCSTAMT.BR = BPB4880_AWA_4880.BR;
        BPCSTAMT.TAMT_STS = 'N';
        BPCSTAMT.EX_TYPE = BPB4880_AWA_4880.EXTYPE;
        BPCSTAMT.DESC = BPB4880_AWA_4880.DESC;
        BPCSTAMT.EFF_DATE = BPB4880_AWA_4880.EFFDTE;
        BPCSTAMT.EXP_DATE = BPB4880_AWA_4880.EXPDTE;
        BPCSTAMT.FLAG = BPB4880_AWA_4880.FLAG;
        BPCSTAMT.FLD_CNT = WS_CNT;
        BPCSTAMT.U_FLD_CNT = WS_CNT2;
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            BPCSTAMT.TAMT_ARR[WS_I-1].LIMIT = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LIMIT;
            BPCSTAMT.TAMT_ARR[WS_I-1].LVL = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].LVL;
            BPCSTAMT.TAMT_ARR[WS_I-1].REASON = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].REASON;
        }
        for (WS_I = 1; WS_I <= WS_CNT2; WS_I += 1) {
            BPCSTAMT.U_TAMT_ARR[WS_I-1].U_LIMIT = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LIMIT;
            BPCSTAMT.U_TAMT_ARR[WS_I-1].U_LVL = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_LVL;
            BPCSTAMT.U_TAMT_ARR[WS_I-1].U_REASON = BPB4880_AWA_4880.TAMT_ARR[WS_I-1].U_REASON;
        }
        S000_CALL_BPZSTAMT();
    }
    public void S000_CALL_BPZSTAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TAMT, BPCSTAMT);
    }
    public void S000_CALL_BPZFCTRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_RISK_CONTROL, BPCFCTRL);
        if (BPCFCTRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCTRL.RC);
            WS_FLD_NO = BPB4880_AWA_4880.AP_MMO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPCFMSG;
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
