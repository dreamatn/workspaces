package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2310 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_C_SUS_WRITE_OFF = "BP-S-C-SUS-WRITE-OFF";
    String CPN_P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_I = 0;
    double WS_GD_AMT = 0;
    int WS_UP_BR = 0;
    char WS_OUT_ATTR = ' ';
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCSWO BPCSCSWO = new BPCSCSWO();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    SCCGWA SCCGWA;
    BPB2300_AWA_2300 BPB2300_AWA_2300;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2310 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2300_AWA_2300>");
        BPB2300_AWA_2300 = (BPB2300_AWA_2300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCSWO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.CCY);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.CS_KIND);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.ML_OPT);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.AMT);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.METHOD);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.REV_NO);
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.SUSP_TYP);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            B020_CHECK_TLR_FOR_CN();
            if (BPCSCSWO.METHOD == '1') {
                if (BPB2300_AWA_2300.BOX_FLG == '1' 
                    || BPB2300_AWA_2300.BOX_FLG == '2') {
                }
            }
        } else {
            B010_CHECK_INPUT();
            B020_CHECK_TLR();
            if (BPCSCSWO.METHOD == '1') {
                if (BPB2300_AWA_2300.BOX_FLG == '1' 
                    || BPB2300_AWA_2300.BOX_FLG == '2') {
                    B030_DATA_TRANSFER();
                }
            }
        }
        B040_SUSP_WRITE_OFF();
        BPB2300_AWA_2300.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPB2300_AWA_2300.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        BPCSCSWO.METHOD = BPB2300_AWA_2300.METHOD;
        if (BPCSCSWO.METHOD == '1') {
            if (BPB2300_AWA_2300.BOX_FLG == '1' 
                || BPB2300_AWA_2300.BOX_FLG == '2') {
                B010_01_CHECK_DETAILS_BESEQ();
                B010_02_CHECK_DETAILS_SYNCHRO();
            }
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB2300_AWA_2300.REV_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB2300_AWA_2300.TRANS_BR;
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            S000_CALL_BPZPQORG();
            WS_UP_BR = BPCPQORG.SUPR_BR;
            WS_OUT_ATTR = BPCPQORG.ATTR;
            CEP.TRC(SCCGWA, WS_UP_BR);
            CEP.TRC(SCCGWA, WS_OUT_ATTR);
            if (WS_OUT_ATTR != '3') {
                CEP.TRC(SCCGWA, BPB2300_AWA_2300.TRANS_BR);
                if (BPB2300_AWA_2300.REV_NO == null) BPB2300_AWA_2300.REV_NO = "";
                JIBS_tmp_int = BPB2300_AWA_2300.REV_NO.length();
                for (int i=0;i<21-JIBS_tmp_int;i++) BPB2300_AWA_2300.REV_NO += " ";
                CEP.TRC(SCCGWA, BPB2300_AWA_2300.REV_NO.substring(0, 9));
                if (BPB2300_AWA_2300.REV_NO == null) BPB2300_AWA_2300.REV_NO = "";
                JIBS_tmp_int = BPB2300_AWA_2300.REV_NO.length();
                for (int i=0;i<21-JIBS_tmp_int;i++) BPB2300_AWA_2300.REV_NO += " ";
                if (BPB2300_AWA_2300.TRANS_BR != Integer.parseInt(BPB2300_AWA_2300.REV_NO.substring(0, 6))) {
                    IBS.init(SCCGWA, BPCUCHBR);
                    if (BPB2300_AWA_2300.REV_NO == null) BPB2300_AWA_2300.REV_NO = "";
                    JIBS_tmp_int = BPB2300_AWA_2300.REV_NO.length();
                    for (int i=0;i<21-JIBS_tmp_int;i++) BPB2300_AWA_2300.REV_NO += " ";
                    if (BPB2300_AWA_2300.REV_NO.substring(0, 6).trim().length() == 0) BPCUCHBR.OLD_BR = 0;
                    else BPCUCHBR.OLD_BR = Integer.parseInt(BPB2300_AWA_2300.REV_NO.substring(0, 6));
                    BPCUCHBR.ORGI_FLG = '1';
                    BPCUCHBR.FUNC = 'F';
                    S000_CALL_BPZUCHBR();
                    CEP.TRC(SCCGWA, BPCUCHBR.RC);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCHBR.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REVNO_NOT_MATCH_BR;
                        WS_FLD_NO = BPB2300_AWA_2300.TRANS_BR_NO;
                        S000_ERR_MSG_PROC();
                    } else {
                        CEP.TRC(SCCGWA, BPCUCHBR.NEW_BR);
                        if (BPCUCHBR.NEW_BR != BPB2300_AWA_2300.TRANS_BR) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR184);
                        }
                    }
                }
            } else {
                if (BPB2300_AWA_2300.REV_NO == null) BPB2300_AWA_2300.REV_NO = "";
                JIBS_tmp_int = BPB2300_AWA_2300.REV_NO.length();
                for (int i=0;i<21-JIBS_tmp_int;i++) BPB2300_AWA_2300.REV_NO += " ";
                if (WS_UP_BR != Integer.parseInt(BPB2300_AWA_2300.REV_NO.substring(0, 6))) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR117);
                }
            }
        }
        BPCSCSWO.METHOD = BPB2300_AWA_2300.METHOD;
        if (BPCSCSWO.METHOD == '1') {
            if (BPB2300_AWA_2300.BOX_FLG == '1' 
                || BPB2300_AWA_2300.BOX_FLG == '2') {
                B010_01_CHECK_DETAILS_BESEQ();
                B010_02_CHECK_DETAILS_SYNCHRO();
            }
        }
    }
    public void S000_CALL_BPZUCHBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BPZUCHBR", BPCUCHBR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_01_CHECK_DETAILS_BESEQ() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = 2; WS_INFO_CNT <= 12; WS_INFO_CNT += 1) {
            if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT - 1-1].P_PVAL == 0 
                && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT - 1-1].P_NUM == 0 
                && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT - 1-1].P_MFLG == ' ') {
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_02_CHECK_DETAILS_SYNCHRO() throws IOException,SQLException,Exception {
        for (WS_INFO_CNT = 1; WS_INFO_CNT <= 12; WS_INFO_CNT += 1) {
            if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0 
                || BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM != 0 
                || BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG != ' ') {
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG == ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
                    WS_FLD_NO = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPB2300_AWA_2300.BOX_FLG == '1' 
            || BPB2300_AWA_2300.BOX_FLG == '2') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB2300_AWA_2300.BOX_FLG == '3' 
            || BPB2300_AWA_2300.BOX_FLG == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_CHECK_TLR_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2300_AWA_2300.BOX_FLG == '1' 
            || BPB2300_AWA_2300.BOX_FLG == '2' 
            || BPB2300_AWA_2300.BOX_FLG == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB2300_AWA_2300.BOX_FLG == '3' 
            || BPB2300_AWA_2300.BOX_FLG == '4' 
            || BPB2300_AWA_2300.BOX_FLG == '6') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_DATA_TRANSFER() throws IOException,SQLException,Exception {
        WS_GD_AMT = 0;
        for (WS_INFO_CNT = 1; WS_INFO_CNT <= 12 
            && BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            WS_GD_AMT = WS_GD_AMT + BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL * BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSCSWO.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_VAL = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSCSWO.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_NUM = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSCSWO.DATA_INFO.PVAL_INFO[WS_INFO_CNT-1].CCY_MFLG = BPB2300_AWA_2300.P_INFO[WS_INFO_CNT-1].P_MFLG;
            BPCSCSWO.DT_CNT = WS_INFO_CNT;
        }
        if (WS_GD_AMT != BPB2300_AWA_2300.AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NOTMATCH_PVAL;
            WS_FLD_NO = BPB2300_AWA_2300.AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B040_SUSP_WRITE_OFF() throws IOException,SQLException,Exception {
        BPCSCSWO.TOTAL_AMT = BPB2300_AWA_2300.AMT;
        BPCSCSWO.CCY = BPB2300_AWA_2300.CCY;
        BPCSCSWO.ML_OPT = BPB2300_AWA_2300.ML_OPT;
        BPCSCSWO.BOX_FLG = BPB2300_AWA_2300.BOX_FLG;
        BPCSCSWO.CS_KIND = BPB2300_AWA_2300.CS_KIND;
        BPCSCSWO.METHOD = BPB2300_AWA_2300.METHOD;
        BPCSCSWO.AC_NO = BPB2300_AWA_2300.AC_NO;
        BPCSCSWO.BR = BPB2300_AWA_2300.TRANS_BR;
        BPCSCSWO.REV_NO = BPB2300_AWA_2300.REV_NO;
        BPCSCSWO.CASH_TYP = BPB2300_AWA_2300.CASH_TYP;
        BPCSCSWO.AC_TYPE = BPB2300_AWA_2300.AC_TYPE;
        BPCSCSWO.SUSP_TYPE = BPB2300_AWA_2300.SUSP_TYP;
        CEP.TRC(SCCGWA, BPB2300_AWA_2300.AMT);
        S000_CALL_BPZSCSWO();
    }
    public void S000_CALL_BPZSCSWO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_C_SUS_WRITE_OFF, BPCSCSWO);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
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
