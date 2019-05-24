package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3520 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_BV_MOV_OUT = "BP-S-BV-MOV-OUT  ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    int K_CNT = 10;
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    char WS_SL_BVOW_FLG = ' ';
    int WS_UP_BR = 0;
    char WS_OUT_ATTR = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTBV BPRTBV = new BPRTBV();
    BPCSBVMO BPCSBVMO = new BPCSBVMO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCRTBV BPCRTBV = new BPCRTBV();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3520_AWA_3520 BPB3520_AWA_3520;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3520 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3520_AWA_3520>");
        BPB3520_AWA_3520 = (BPB3520_AWA_3520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CH();
            if (pgmRtn) return;
            B020_CHECK_ORG_FOR_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "1234");
        B020_BV_MOVE_OUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3520_AWA_3520.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_J = 1; WS_J <= 4 
            && BPB3520_AWA_3520.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3520_AWA_3520.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_COMP_BEGNO == 0 
                    || WS_COMP_ENDNO == 0 
                    || BPB3520_AWA_3520.BV_DATA[WS_J-1].NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].NUM_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB3520_AWA_3520.TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            WS_FLD_NO = BPB3520_AWA_3520.TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = BPB3520_AWA_3520.BR;
        BPRVHPB.CUR_TLR = BPB3520_AWA_3520.TLR;
        BPRVHPB.POLL_BOX_IND = '1';
        BPRVHPB.STS = 'N';
        CEP.TRC(SCCGWA, BPRVHPB.BR);
        CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.STS);
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        if (BPCRVHPB.RETURN_INFO == 'F') {
        } else {
            CEP.TRC(SCCGWA, BPRVHPB.BR);
            CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.STS);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRVHPB.INFO.FUNC = 'E';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGST);
        if (BPB3520_AWA_3520.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_J = 1; WS_J <= 10 
            && BPB3520_AWA_3520.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3520_AWA_3520.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO == null) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3520_AWA_3520.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_COMP_BEGNO == 0 
                    || WS_COMP_ENDNO == 0 
                    || BPB3520_AWA_3520.BV_DATA[WS_J-1].NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                    WS_FLD_NO = BPB3520_AWA_3520.BV_DATA[WS_J-1].NUM_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, "12345");
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB3520_AWA_3520.TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.TLR_BR != BPB3520_AWA_3520.BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOT_BELONG_TO_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_STSW_TLR;
            WS_FLD_NO = BPB3520_AWA_3520.TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_STSW_TLR;
            WS_FLD_NO = BPB3520_AWA_3520.TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        if (BPB3520_AWA_3520.OUT_TYP == '1') {
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '1';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            if (BPCRVHPB.RETURN_INFO == 'F') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '0';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            if (BPCRVHPB.RETURN_INFO == 'F') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BBOX_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB3520_AWA_3520.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB3520_AWA_3520.BR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPQORG.ORG_STS != 'O') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SIGN_OFF;
            WS_FLD_NO = BPB3520_AWA_3520.BR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_ORG_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB3520_AWA_3520.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_SAME_NOTALLOWED);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_UP_BR = BPCPQORG.SUPR_BR;
        WS_OUT_ATTR = BPCPQORG.ATTR;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, WS_UP_BR);
        if (WS_OUT_ATTR != '2' 
            && WS_OUT_ATTR != '3') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR77);
        } else {
            if (WS_OUT_ATTR == '2') {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPB3520_AWA_3520.BR;
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                if (BPCPQORG.ATTR != '3') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR78);
                }
                if (BPCPQORG.SUPR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR79);
                }
            }
            if (WS_OUT_ATTR == '3') {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPB3520_AWA_3520.BR;
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                if (BPCPQORG.ATTR != '2' 
                    && BPCPQORG.ATTR != '3') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR80);
                } else {
                    if (BPCPQORG.ATTR == '2') {
                        if (WS_UP_BR != BPB3520_AWA_3520.BR) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR81);
                        }
                    }
                    if (BPCPQORG.ATTR == '3') {
                        if (BPCPQORG.SUPR_BR != WS_UP_BR) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR82);
                        }
                    }
                }
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB3520_AWA_3520.BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.ORG_STS != 'O') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR73);
        }
    }
    public void B020_BV_MOVE_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVMO);
        BPCSBVMO.IN_BR = BPB3520_AWA_3520.BR;
        BPCSBVMO.IN_TLR = BPB3520_AWA_3520.TLR;
        BPCSBVMO.BV_STS = BPB3520_AWA_3520.BV_STS;
        BPCSBVMO.CNT = K_CNT;
        BPCSBVMO.PB_FLG = BPB3520_AWA_3520.OUT_TYP;
        BPCSBVMO.BR_FLG = 'Y';
        BPCSBVMO.OUT_TYP = BPB3520_AWA_3520.OUT_TYP;
        BPCSBVMO.ONWAY_DT = 0;
        for (WS_I = 1; WS_I <= K_CNT; WS_I += 1) {
            BPCSBVMO.BV_DATA[WS_I-1].BV_CODE = BPB3520_AWA_3520.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVMO.BV_DATA[WS_I-1].BV_NAME = BPB3520_AWA_3520.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVMO.BV_DATA[WS_I-1].HEAD_NO = BPB3520_AWA_3520.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVMO.BV_DATA[WS_I-1].BEG_NO = BPB3520_AWA_3520.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVMO.BV_DATA[WS_I-1].END_NO = BPB3520_AWA_3520.BV_DATA[WS_I-1].END_NO;
            BPCSBVMO.BV_DATA[WS_I-1].NUM = BPB3520_AWA_3520.BV_DATA[WS_I-1].NUM;
        }
        S000_CALL_BPZSBVMO();
        if (pgmRtn) return;
        BPB3520_AWA_3520.MOV_DT = BPCSBVMO.MOV_DT;
        BPB3520_AWA_3520.CONF_NO = BPCSBVMO.CONF_NO;
        CEP.TRC(SCCGWA, BPB3520_AWA_3520.MOV_DT);
        CEP.TRC(SCCGWA, BPB3520_AWA_3520.CONF_NO);
        BPB3520_AWA_3520.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPB3520_AWA_3520.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZSBVMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_MOV_OUT, BPCSBVMO);
        if (BPCSBVMO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVMO.RC);
            WS_FLD_NO = BPB3520_AWA_3520.MOV_DT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
