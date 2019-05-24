package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2240 {
    int JIBS_tmp_int;
    brParm BPTTLVB_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP124";
    char K_STSW_FLG_Y = '1';
    int K_MAX_PAR_CNT = 12;
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_S_CASH_ALTERNATE = "BP-S-CASH-ALTERNATE ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TMP_AMT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_CNT = 0;
    String WS_SAVE_PLBOX_NO = " ";
    char WS_PLBOX_TP = ' ';
    char WS_FND_PAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCSALCS BPCSALCS = new BPCSALCS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    SCCGWA SCCGWA;
    BPB2240_AWA_2240 BPB2240_AWA_2240;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2240 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2240_AWA_2240>");
        BPB2240_AWA_2240 = (BPB2240_AWA_2240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2240_AWA_2240);
        CEP.TRC(SCCGWA, BPB2240_AWA_2240.RCV_TLR);
        CEP.TRC(SCCGWA, BPB2240_AWA_2240.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2240_AWA_2240.CCY);
        CEP.TRC(SCCGWA, BPB2240_AWA_2240.GD_AMT);
        CEP.TRC(SCCGWA, BPB2240_AWA_2240.BD_AMT);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            if (pgmRtn) return;
            B020_ALTERNATE_LIB_FOR_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_ALTERNATE_LIB();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2240_AWA_2240.RCV_TLR) 
            || BPB2240_AWA_2240.OUT_TLR.equalsIgnoreCase(BPB2240_AWA_2240.RCV_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_HANDOVER_SELF;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        if (BPB2240_AWA_2240.OUT_TLR.trim().length() == 0) {
            BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPCFTLRQ.INFO.TLR = BPB2240_AWA_2240.OUT_TLR;
        }
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2240_AWA_2240.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCV_TLR_BR_ERR;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.CRNT_TLR = BPB2240_AWA_2240.RCV_TLR;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTTLVB();
        if (pgmRtn) return;
        T000_READNEXT_BPTTLVB();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (BPRTLVB.PLBOX_TP == '3' 
                    || BPRTLVB.PLBOX_TP == '4' 
                    || BPRTLVB.PLBOX_TP == '6') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR97);
                }
            }
            IBS.init(SCCGWA, BPRTLVB);
            T000_READNEXT_BPTTLVB();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTTLVB();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '1';
        if (BPB2240_AWA_2240.OUT_TLR.trim().length() == 0) {
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPRTLVB.CRNT_TLR = BPB2240_AWA_2240.OUT_TLR;
        }
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            BPRTLVB.PLBOX_TP = '2';
            if (BPB2240_AWA_2240.OUT_TLR.trim().length() == 0) {
                BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                BPRTLVB.CRNT_TLR = BPB2240_AWA_2240.OUT_TLR;
            }
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            if (BPCTLVBF.RETURN_INFO == 'N') {
                BPRTLVB.PLBOX_TP = '5';
                if (BPB2240_AWA_2240.OUT_TLR.trim().length() == 0) {
                    BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
                } else {
                    BPRTLVB.CRNT_TLR = BPB2240_AWA_2240.OUT_TLR;
                }
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (pgmRtn) return;
                if (BPCTLVBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_HAV_CASHLIB;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR);
                }
            }
        }
        WS_SAVE_PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
    }
    public void T000_STARTBR_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND ( CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
    }
    public void T000_READNEXT_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTLVB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLVB_BR);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPB2240_AWA_2240.RCV_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_HANDOVER_SELF;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2240_AWA_2240.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            WS_FLD_NO = BPB2240_AWA_2240.RCV_TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQBOX);
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2') {
                BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                WS_CNT = 1001;
                if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_SAVE_PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_ALTERNATE_LIB_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSALCS);
        BPCSALCS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSALCS.MGR_TLR = BPB2240_AWA_2240.RCV_TLR;
        BPCSALCS.PLBOX_NO = WS_SAVE_PLBOX_NO;
        BPCSALCS.PLBOX_TP = WS_PLBOX_TP;
        BPCSALCS.OUT_TLR = BPB2240_AWA_2240.OUT_TLR;
        S000_CALL_BPZSALCS();
        if (pgmRtn) return;
    }
    public void B020_ALTERNATE_LIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSALCS);
        BPCSALCS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSALCS.MGR_TLR = BPB2240_AWA_2240.RCV_TLR;
        S000_CALL_BPZSALCS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSKPSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_K_PSW_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCFKPSW;
        SCCCALL.ERR_FLDNO = BPB2240_AWA_2240.TLR_PSW;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            WS_FLD_NO = BPB2240_AWA_2240.TLR_PSW_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2240_AWA_2240.CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSALCS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASH_ALTERNATE, BPCSALCS);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
