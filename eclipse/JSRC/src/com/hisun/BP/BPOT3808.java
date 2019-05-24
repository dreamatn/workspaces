package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT3808 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP174";
    String CPN_S_BV_USE_OUT = "BP-S-BV-USE-OUT ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSBVUO BPCSBVUO = new BPCSBVUO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCO174 BPCO174 = new BPCO174();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3808_AWA_3808 BPB3808_AWA_3808;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3808 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3808_AWA_3808>");
        BPB3808_AWA_3808 = (BPB3808_AWA_3808) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.trim().length() != 0 
            && WS_CNT <= 5; WS_CNT += 1) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_BV_PAY_OUT();
            if (pgmRtn) return;
            R030_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        B040_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3808_AWA_3808.BV_CODE);
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3808_AWA_3808.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.trim().length() > 0) {
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO == null) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO = "";
            JIBS_tmp_int = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.trim().length() > 0) {
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO == null) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO = "";
            JIBS_tmp_int = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].HEAD_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.trim().length() > 0 
                || BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO == null) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO = "";
            JIBS_tmp_int = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO += " ";
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO == null) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO = "";
            JIBS_tmp_int = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO += " ";
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPB3808_AWA_3808.BV_CODE;
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO == null) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO = "";
            JIBS_tmp_int = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO += " ";
            BPCSNOCK.BEG_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO.substring(0, WS_BVNO_LEN);
            if (BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO == null) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO = "";
            JIBS_tmp_int = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO += " ";
            BPCSNOCK.END_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO.substring(0, WS_BVNO_LEN);
            BPCSNOCK.NUM = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].NUM;
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
            if (pgmRtn) return;
        }
    }
    public void B020_BV_PAY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVUO);
        BPCSBVUO.PAY_TYP = BPB3808_AWA_3808.PAY_TYPE;
        BPCSBVUO.BV_CODE = BPB3808_AWA_3808.BV_CODE;
        BPCSBVUO.BV_NAME = BPB3808_AWA_3808.BV_NAME;
        BPCSBVUO.HEAD_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].HEAD_NO;
        BPCSBVUO.BEG_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].BEG_NO;
        BPCSBVUO.END_NO = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].END_NO;
        BPCSBVUO.NUM = BPB3808_AWA_3808.BV_INFO[WS_CNT-1].NUM;
        BPCSBVUO.REMARK = BPB3808_AWA_3808.REMARK;
        BPCSBVUO.DRW_NM = BPB3808_AWA_3808.DRW_NM;
        BPCSBVUO.DRW_IDT = BPB3808_AWA_3808.DRW_IDT;
        BPCSBVUO.DRW_IDN = BPB3808_AWA_3808.DRW_IDN;
        S000_CALL_BPZSBVUO();
        if (pgmRtn) return;
        BPB3808_AWA_3808.MOV_DT = BPCSBVUO.MOV_DT;
        BPB3808_AWA_3808.CONF_NO = BPCSBVUO.CONF_NO;
        BPB3808_AWA_3808.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPB3808_AWA_3808.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R030_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO174);
        if (BPB3808_AWA_3808.PAY_TYPE == '0') {
            BPCO174.PAY_TYP = BPB3808_AWA_3808.PAY_TYPE;
        } else {
            BPCO174.PAY_TYP = '1';
        }
        BPCO174.BV_CODE = BPCSBVUO.BV_CODE;
        BPCO174.BV_NAME = BPCSBVUO.BV_NAME;
        BPCO174.BV_INFO[WS_CNT-1].HEAD_NO = BPCSBVUO.HEAD_NO;
        BPCO174.BV_INFO[WS_CNT-1].BEG_NO = BPCSBVUO.BEG_NO;
        BPCO174.BV_INFO[WS_CNT-1].END_NO = BPCSBVUO.END_NO;
        BPCO174.BV_INFO[WS_CNT-1].NUM = BPCSBVUO.NUM;
        BPCO174.REMARK = BPCSBVUO.REMARK;
        BPCO174.DRW_NM = BPCSBVUO.DRW_NM;
        BPCO174.DRW_IDT = BPCSBVUO.DRW_IDT;
        BPCO174.DRW_IDN = BPCSBVUO.DRW_IDN;
    }
    public void B040_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO174;
