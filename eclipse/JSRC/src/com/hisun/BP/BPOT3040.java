package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3040 {
    int JIBS_tmp_int;
    int K_CYC_TIMES = 4;
    int K_CYC_TIMES_10 = 10;
    String K_OUTPUT_FMT = "BP151";
    String CPN_S_V_TO_B = "BP-S-BV-V-TO-B   ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVVB BPCSBVVB = new BPCSBVVB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3010_AWA_3010 BPB3010_AWA_3010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3010_AWA_3010>");
        BPB3010_AWA_3010 = (BPB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B015_CHECK_INPUT_CN();
            B020_LINK_BVVB_COMPONENT_CN();
        } else {
            B010_CHECK_INPUT();
            B020_LINK_BVVB_COMPONENT();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        for (WS_J = 1; WS_J <= 4 
            && BPB3010_AWA_3010.BV_DATA[WS_J-1].CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3010_AWA_3010.BV_DATA[WS_J-1].CODE;
            S000_CALL_BPZFBVQU();
            if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPCSNOCK);
                BPCSNOCK.BV_CODE = BPB3010_AWA_3010.BV_DATA[WS_J-1].CODE;
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO += " ";
                BPCSNOCK.BEG_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN);
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO += " ";
                BPCSNOCK.END_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN);
                BPCSNOCK.FUNC = '1';
                S000_CALL_BPZSNOCK();
            }
            CEP.TRC(SCCGWA, "111111");
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3010_AWA_3010.TLR;
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
            S000_CALL_BPZFTLRQ();
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
            if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
                && BPCFTLRQ.INFO.SIGN_STS != 'T') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
                S000_ERR_MSG_PROC();
            }
            if (BPB3010_AWA_3010.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_MUST_IMPOWER;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B015_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_FUNC);
        if (BPB3010_AWA_3010.BV_FUNC == '0') {
            for (WS_J = 1; WS_J <= K_CYC_TIMES_10 
                && BPB3010_AWA_3010.BV_DATA[WS_J-1].CODE.trim().length() != 0; WS_J += 1) {
                IBS.init(SCCGWA, BPCFBVQU);
                BPCFBVQU.TX_DATA.KEY.CODE = BPB3010_AWA_3010.BV_DATA[WS_J-1].CODE;
                S000_CALL_BPZFBVQU();
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO = "";
                    JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO += " ";
                    for (WS_I = 1; WS_I <= 20 
                        && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                    }
                    if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                        WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO = "";
                    JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO += " ";
                    for (WS_I = 1; WS_I <= 20 
                        && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                    }
                    if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                        WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
                if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                        WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].HEAD_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                        || BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                        WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                    || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO = "";
                    JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO += " ";
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                    else WS_COMP_BEGNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO = "";
                    JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO += " ";
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                    else WS_COMP_ENDNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                    if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                        WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                    IBS.init(SCCGWA, BPCSNOCK);
                    BPCSNOCK.BV_CODE = BPB3010_AWA_3010.BV_DATA[WS_J-1].CODE;
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO = "";
                    JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO += " ";
                    BPCSNOCK.BEG_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN);
                    if (BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO = "";
                    JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO += " ";
                    BPCSNOCK.END_NO = BPB3010_AWA_3010.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN);
                    BPCSNOCK.FUNC = '1';
                    S000_CALL_BPZSNOCK();
                }
            }
        } else {
            CEP.TRC(SCCGWA, "AWA-BV-FUNC IS 1");
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = BPB3010_AWA_3010.PB_NO;
            BPCRVHPB.INFO.FUNC = 'Q';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
                S000_ERR_MSG_PROC();
            }
            if (BPRVHPB.POLL_BOX_IND != '0') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BBOX_TLR;
                S000_ERR_MSG_PROC();
            }
            if (BPRVHPB.BV_CHK_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BOX_NOTBANL;
                S000_ERR_MSG_PROC();
            }
            if (BPRVHPB.RELATE_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVBOX_HAD_TLR;
                S000_ERR_MSG_PROC();
            }
            if (BPRVHPB.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '1';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (BPCRVHPB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
                if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
                S000_ERR_MSG_PROC();
            }
            BPRVHPB.CUR_TLR = BPB3010_AWA_3010.TLR;
            BPRVHPB.POLL_BOX_IND = '0';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
            CEP.TRC(SCCGWA, "CHECK BVBOX");
            CEP.TRC(SCCGWA, BPCRVHPB.RETURN_INFO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BVB_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB3010_AWA_3010.TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_TYP == 'S' 
            && BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("3")) {
        } else {
            if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
                && BPCFTLRQ.INFO.SIGN_STS != 'T') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
                S000_ERR_MSG_PROC();
            }
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
                S000_ERR_MSG_PROC();
            }
            if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_TYP == 'S' 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("3")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_TLR_STSW_NOT_VB);
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
    public void B020_LINK_BVVB_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVVB);
        for (WS_CNT = 1; WS_CNT <= K_CYC_TIMES; WS_CNT += 1) {
            if (BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CODE.trim().length() > 0) {
                BPCSBVVB.DATA[WS_CNT-1].CODE = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CODE;
                BPCSBVVB.DATA[WS_CNT-1].ENM = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].ENM;
                BPCSBVVB.DATA[WS_CNT-1].CNM = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CNM;
                BPCSBVVB.DATA[WS_CNT-1].HEAD_NO = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].HEAD_NO;
                BPCSBVVB.DATA[WS_CNT-1].BEG_NO = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].BEG_NO;
                BPCSBVVB.DATA[WS_CNT-1].END_NO = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].END_NO;
                BPCSBVVB.DATA[WS_CNT-1].NUM = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].NUM;
                BPCSBVVB.DATA[WS_CNT-1].TLR = BPB3010_AWA_3010.TLR;
                BPCSBVVB.COUNT += 1;
            }
        }
        BPCSBVVB.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.TLR);
        BPCSBVVB.PSW_TYP = BPB3010_AWA_3010.PSW_TYP;
        BPCSBVVB.PSW = BPB3010_AWA_3010.PSW;
        BPCSBVVB.CPSW = BPB3010_AWA_3010.CPSW;
        S00_CALL_BPZSBVVB();
    }
    public void B020_LINK_BVVB_COMPONENT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVVB);
        CEP.TRC(SCCGWA, BPCSBVVB.COUNT);
        if (BPB3010_AWA_3010.BV_FUNC == '0') {
            for (WS_CNT = 1; WS_CNT <= K_CYC_TIMES_10; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CODE);
                if (BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CODE.trim().length() > 0) {
                    BPCSBVVB.DATA[WS_CNT-1].CODE = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CODE;
                    BPCSBVVB.DATA[WS_CNT-1].ENM = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].ENM;
                    BPCSBVVB.DATA[WS_CNT-1].CNM = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].CNM;
                    BPCSBVVB.DATA[WS_CNT-1].HEAD_NO = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].HEAD_NO;
                    BPCSBVVB.DATA[WS_CNT-1].BEG_NO = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].BEG_NO;
                    BPCSBVVB.DATA[WS_CNT-1].END_NO = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].END_NO;
                    BPCSBVVB.DATA[WS_CNT-1].NUM = BPB3010_AWA_3010.BV_DATA[WS_CNT-1].NUM;
                    BPCSBVVB.DATA[WS_CNT-1].TLR = BPB3010_AWA_3010.TLR;
                    BPCSBVVB.COUNT += 1;
                }
            }
        } else {
            BPCSBVVB.REC_TLR = BPB3010_AWA_3010.TLR;
        }
        CEP.TRC(SCCGWA, BPCSBVVB.COUNT);
        BPCSBVVB.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBVVB.PSW_TYP = BPB3010_AWA_3010.PSW_TYP;
        BPCSBVVB.PSW = BPB3010_AWA_3010.PSW;
        BPCSBVVB.CPSW = BPB3010_AWA_3010.CPSW;
        BPCSBVVB.BV_FUNC = BPB3010_AWA_3010.BV_FUNC;
        BPCSBVVB.PB_NO = BPB3010_AWA_3010.PB_NO;
        S00_CALL_BPZSBVVB();
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S00_CALL_BPZSBVVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_V_TO_B, BPCSBVVB);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
