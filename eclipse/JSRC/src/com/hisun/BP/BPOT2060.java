package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2060 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BPX01";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_BP_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_S_BRE_LIB = "BP-S-BRE-LIB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSBLIB BPCSBLIB = new BPCSBLIB();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2060_AWA_2060 BPB2060_AWA_2060;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2060 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2060_AWA_2060>");
        BPB2060_AWA_2060 = (BPB2060_AWA_2060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2060_AWA_2060.BR);
        CEP.TRC(SCCGWA, BPB2060_AWA_2060.PLBOX_NO);
        CEP.TRC(SCCGWA, BPB2060_AWA_2060.VB_FLG);
        CEP.TRC(SCCGWA, BPB2060_AWA_2060.TLR);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B100_CHECK_INPUT_FOR_CN1();
        } else {
            B100_CHECK_INPUT();
        }
        B200_QUIRE_BRINF();
    }
    public void B100_CHECK_INPUT_FOR_CN1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2060_AWA_2060.VB_FLG);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_LVL == '0' 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_ERR);
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TX_LVL != '0' 
            || BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
        } else {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2060_AWA_2060.TLR)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_OLY_QURY_SELF);
                }
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
            }
        }
        if (BPB2060_AWA_2060.BR != 0) {
            if (BPB2060_AWA_2060.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCFTLCM.BR = BPB2060_AWA_2060.BR;
                S000_CALL_BPZFTLCM();
                if (BPCFTLCM.AUTH_FLG != 'Y') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NO_AUTH_TO_BR);
                }
            }
        }
        if (BPB2060_AWA_2060.TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB2060_AWA_2060.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
            }
            if (BPB2060_AWA_2060.BR != ' ') {
                if (BPCFTLRQ.INFO.NEW_BR != BPB2060_AWA_2060.BR) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_BELONG_TO_BR);
                }
            }
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2060_AWA_2060.VB_FLG);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPB2060_AWA_2060.VB_FLG == '3'
            || BPB2060_AWA_2060.VB_FLG == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                    S000_ERR_MSG_PROC();
                }
            }
        } else if (BPB2060_AWA_2060.VB_FLG == '1'
            || BPB2060_AWA_2060.VB_FLG == '2') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
            if (BPB2060_AWA_2060.BR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBTLR_INPUT;
                WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
                CEP.TRC(SCCGWA, "STOP1");
                CEP.TRC(SCCGWA, BPB2060_AWA_2060.BR);
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPB2060_AWA_2060.BR;
            CEP.TRC(SCCGWA, "STOP2");
            S000_CALL_BPZPQORG();
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPRGST.BR2 = BPB2060_AWA_2060.BR;
            if (BPCPRGST.BR1 != BPCPRGST.BR2) {
                S000_CALL_BPZPRGST();
                if (BPCPRGST.FLAG == 'Y') {
                    if (BPCPRGST.LVL_RELATION == 'C') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OPEDTBR_UPP_OPETBR;
                        WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
                        CEP.TRC(SCCGWA, "STOP3");
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_VB_FLAG;
            WS_FLD_NO = BPB2060_AWA_2060.VB_FLG_NO;
            CEP.TRC(SCCGWA, "STOP4");
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPB2060_AWA_2060.VB_FLG == '3'
            || BPB2060_AWA_2060.VB_FLG == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                    S000_ERR_MSG_PROC();
                }
            }
        } else if (BPB2060_AWA_2060.VB_FLG == '1'
            || BPB2060_AWA_2060.VB_FLG == '2'
            || BPB2060_AWA_2060.VB_FLG == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB2060_AWA_2060.BR;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_VB_FLAG;
            WS_FLD_NO = BPB2060_AWA_2060.VB_FLG_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_QUIRE_BRINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLIB);
        BPCSBLIB.FUNC = 'B';
        BPCSBLIB.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBLIB.BR = BPB2060_AWA_2060.BR;
        BPCSBLIB.PLBOX_NO = BPB2060_AWA_2060.PLBOX_NO;
        BPCSBLIB.TLR = BPB2060_AWA_2060.TLR;
        BPCSBLIB.VB_FLG = BPB2060_AWA_2060.VB_FLG;
        S000_CALL_BPZSBLIB();
    }
    public void S000_CALL_BPZSBLIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BRE_LIB;
        SCCCALL.COMMAREA_PTR = BPCSBLIB;
        SCCCALL.ERR_FLDNO = BPB2060_AWA_2060.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSBLIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBLIB.RC);
            WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_BP_P_INQ_ORG;
        SCCCALL.COMMAREA_PTR = BPCPQORG;
        SCCCALL.ERR_FLDNO = BPB2060_AWA_2060.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB2060_AWA_2060.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB2060_AWA_2060.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB2060_AWA_2060.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-TLR-QRY-BR-CHK";
        SCCCALL.COMMAREA_PTR = BPCFTLCM;
        SCCCALL.ERR_FLDNO = BPB2060_AWA_2060.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
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
