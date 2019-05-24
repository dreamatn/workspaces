package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3530 {
    int JIBS_tmp_int;
    String CPN_S_BV_MOV_IN = "BP-S-BV-MOV-IN   ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    int K_CNT = 10;
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVMI BPCSBVMI = new BPCSBVMI();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3530_AWA_3530 BPB3530_AWA_3530;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3530 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3530_AWA_3530>");
        BPB3530_AWA_3530 = (BPB3530_AWA_3530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            B020_BV_MOVE_IN_CN();
        } else {
            B010_CHECK_INPUT();
            B020_BV_MOVE_IN();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3530_AWA_3530.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_J = 1; WS_J <= 4 
            && BPB3530_AWA_3530.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3530_AWA_3530.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (WS_COMP_BEGNO == 0 
                    || WS_COMP_ENDNO == 0 
                    || BPB3530_AWA_3530.BV_DATA[WS_J-1].NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].NUM_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB3530_AWA_3530.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_J = 1; WS_J <= 10 
            && BPB3530_AWA_3530.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3530_AWA_3530.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO == null) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3530_AWA_3530.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (WS_COMP_BEGNO == 0 
                    || WS_COMP_ENDNO == 0 
                    || BPB3530_AWA_3530.BV_DATA[WS_J-1].NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                    WS_FLD_NO = BPB3530_AWA_3530.BV_DATA[WS_J-1].NUM_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.IN_TYP);
        if (BPB3530_AWA_3530.IN_TYP == '1') {
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '1';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (BPCRVHPB.RETURN_INFO == 'F') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "AWA-IN-TYP");
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '0';
            BPRVHPB.STS = 'N';
            CEP.TRC(SCCGWA, "987654");
            CEP.TRC(SCCGWA, BPRVHPB.BR);
            CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.STS);
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (BPCRVHPB.RETURN_INFO == 'F') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BBOX_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BV_MOVE_IN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.MOV_DT);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.CONF_NO);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.BR);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.TLR);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.BV_STS);
        IBS.init(SCCGWA, BPCSBVMI);
        BPCSBVMI.MOV_DT = BPB3530_AWA_3530.MOV_DT;
        BPCSBVMI.CONF_NO = BPB3530_AWA_3530.CONF_NO;
        BPCSBVMI.OUT_BR = BPB3530_AWA_3530.BR;
        BPCSBVMI.OUT_TLR = BPB3530_AWA_3530.TLR;
        BPCSBVMI.BV_STS = BPB3530_AWA_3530.BV_STS;
        BPCSBVMI.CNT = K_CNT;
        for (WS_I = 1; WS_I <= K_CNT; WS_I += 1) {
            BPCSBVMI.BV_DATA[WS_I-1].BV_CODE = BPB3530_AWA_3530.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVMI.BV_DATA[WS_I-1].BV_NAME = BPB3530_AWA_3530.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO = BPB3530_AWA_3530.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVMI.BV_DATA[WS_I-1].BEG_NO = BPB3530_AWA_3530.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVMI.BV_DATA[WS_I-1].END_NO = BPB3530_AWA_3530.BV_DATA[WS_I-1].END_NO;
            BPCSBVMI.BV_DATA[WS_I-1].NUM = BPB3530_AWA_3530.BV_DATA[WS_I-1].NUM;
        }
        S000_CALL_BPZSBVMI();
    }
    public void B020_BV_MOVE_IN_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.MOV_DT);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.CONF_NO);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.BR);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.TLR);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.BV_STS);
        IBS.init(SCCGWA, BPCSBVMI);
        BPCSBVMI.MOV_DT = BPB3530_AWA_3530.MOV_DT;
        BPCSBVMI.CONF_NO = BPB3530_AWA_3530.CONF_NO;
        BPCSBVMI.OUT_BR = BPB3530_AWA_3530.BR;
        BPCSBVMI.OUT_TLR = BPB3530_AWA_3530.TLR;
        BPCSBVMI.BV_STS = BPB3530_AWA_3530.BV_STS;
        BPCSBVMI.CNT = K_CNT;
        BPCSBVMI.PB_FLG = BPB3530_AWA_3530.IN_TYP;
        BPCSBVMI.IN_TYP = BPB3530_AWA_3530.IN_TYP;
        BPCSBVMI.BR_FLG = 'Y';
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPB3530_AWA_3530.BV_DATA[2-1].BV_CODE);
        for (WS_I = 1; WS_I <= K_CNT 
            && BPB3530_AWA_3530.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            BPCSBVMI.BV_DATA[WS_I-1].BV_CODE = BPB3530_AWA_3530.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVMI.BV_DATA[WS_I-1].BV_NAME = BPB3530_AWA_3530.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO = BPB3530_AWA_3530.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVMI.BV_DATA[WS_I-1].BEG_NO = BPB3530_AWA_3530.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVMI.BV_DATA[WS_I-1].END_NO = BPB3530_AWA_3530.BV_DATA[WS_I-1].END_NO;
            BPCSBVMI.BV_DATA[WS_I-1].NUM = BPB3530_AWA_3530.BV_DATA[WS_I-1].NUM;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB3530_AWA_3530.BV_DATA[WS_I-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].BV_CODE);
        }
        BPCSBVMI.FEE_ACNO = BPB3530_AWA_3530.FEE_ACNO;
        S000_CALL_BPZSBVMI();
    }
    public void S000_CALL_BPZSBVMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_MOV_IN, BPCSBVMI);
        if (BPCSBVMI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVMI.RC);
            WS_FLD_NO = BPB3530_AWA_3530.MOV_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
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
