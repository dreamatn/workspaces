package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3150 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP163";
    String CPN_S_BV_ULOS = "BP-S-CUS-BV-ULOS ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCBVU BPCSCBVU = new BPCSCBVU();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3140_AWA_3140 BPB3140_AWA_3140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3150 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3140_AWA_3140>");
        BPB3140_AWA_3140 = (BPB3140_AWA_3140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_CBVU_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3140_AWA_3140.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_REC;
            WS_FLD_NO = BPB3140_AWA_3140.BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3140_AWA_3140.CONT_MTH == '0') {
            if (BPB3140_AWA_3140.BV_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_NO_MUST_INPUT;
                WS_FLD_NO = BPB3140_AWA_3140.BV_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3140_AWA_3140.BEG_NO.trim().length() > 0 
                || BPB3140_AWA_3140.END_NO.trim().length() > 0 
                || BPB3140_AWA_3140.NUM != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_CANTIN;
                WS_FLD_NO = BPB3140_AWA_3140.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (BPB3140_AWA_3140.BV_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_NO_CANT_INPUT;
                WS_FLD_NO = BPB3140_AWA_3140.BV_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3140_AWA_3140.BEG_NO.trim().length() == 0 
                || BPB3140_AWA_3140.END_NO.trim().length() == 0 
                || BPB3140_AWA_3140.NUM == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_INPUT;
                WS_FLD_NO = BPB3140_AWA_3140.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3140_AWA_3140.BV_NO;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, BPB3140_AWA_3140.BEG_NO);
        CEP.TRC(SCCGWA, BPB3140_AWA_3140.END_NO);
        if (BPB3140_AWA_3140.BEG_NO.trim().length() > 0) {
            if (BPB3140_AWA_3140.BEG_NO == null) BPB3140_AWA_3140.BEG_NO = "";
            JIBS_tmp_int = BPB3140_AWA_3140.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3140_AWA_3140.BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3140_AWA_3140.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, BPB3140_AWA_3140.BEG_NO.substring(WS_I-1));
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3140_AWA_3140.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3140_AWA_3140.END_NO.trim().length() > 0) {
            if (BPB3140_AWA_3140.END_NO == null) BPB3140_AWA_3140.END_NO = "";
            JIBS_tmp_int = BPB3140_AWA_3140.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3140_AWA_3140.END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3140_AWA_3140.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, BPB3140_AWA_3140.END_NO.substring(WS_I-1));
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3140_AWA_3140.END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3140_AWA_3140.BEG_NO.trim().length() > 0 
                || BPB3140_AWA_3140.END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3140_AWA_3140.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPB3140_AWA_3140.BEG_NO == null) BPB3140_AWA_3140.BEG_NO = "";
            JIBS_tmp_int = BPB3140_AWA_3140.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3140_AWA_3140.BEG_NO += " ";
            if (BPB3140_AWA_3140.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3140_AWA_3140.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3140_AWA_3140.END_NO == null) BPB3140_AWA_3140.END_NO = "";
            JIBS_tmp_int = BPB3140_AWA_3140.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3140_AWA_3140.END_NO += " ";
            if (BPB3140_AWA_3140.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3140_AWA_3140.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3140_AWA_3140.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_COMP_BEGNO == 0 
                || WS_COMP_ENDNO == 0 
                || BPB3140_AWA_3140.NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                WS_FLD_NO = BPB3140_AWA_3140.NUM_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_LINK_CBVU_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCBVU);
        BPCSCBVU.LOST_NO = BPB3140_AWA_3140.LOST_NO;
        BPCSCBVU.LOST_MAN = BPB3140_AWA_3140.LOST_MAN;
        BPCSCBVU.LOST_DT = BPB3140_AWA_3140.LOST_DT;
        BPCSCBVU.LOST_ADD = BPB3140_AWA_3140.LOST_ADD;
        BPCSCBVU.LOST_REA = BPB3140_AWA_3140.LOST_REA;
        BPCSCBVU.BV_CODE = BPB3140_AWA_3140.BV_CODE;
        BPCSCBVU.OWNE_IND = BPB3140_AWA_3140.OWNE_IND;
        BPCSCBVU.BR = BPB3140_AWA_3140.BR;
        BPCSCBVU.CONT_MTH = BPB3140_AWA_3140.CONT_MTH;
        BPCSCBVU.BV_NO = BPB3140_AWA_3140.BV_NO;
        BPCSCBVU.BEG_NO = BPB3140_AWA_3140.BEG_NO;
        BPCSCBVU.END_NO = BPB3140_AWA_3140.END_NO;
        BPCSCBVU.NUM = BPB3140_AWA_3140.NUM;
        BPCSCBVU.TYPE = BPB3140_AWA_3140.TYPE;
        BPCSCBVU.TRAN_IND = BPB3140_AWA_3140.TRAN_IND;
        BPCSCBVU.AMT = BPB3140_AWA_3140.AMT;
        BPCSCBVU.ISSU_DT = BPB3140_AWA_3140.ISSU_DT;
        BPCSCBVU.APP_AC = BPB3140_AWA_3140.APP_AC;
        BPCSCBVU.APP_CNM = BPB3140_AWA_3140.APP_CNM;
        BPCSCBVU.OUT_AC = BPB3140_AWA_3140.OUT_AC;
        BPCSCBVU.OUT_CNM = BPB3140_AWA_3140.OUT_CNM;
        BPCSCBVU.PAY_DT = BPB3140_AWA_3140.PAY_DT;
        BPCSCBVU.REMARK = BPB3140_AWA_3140.REMARK;
        S000_CALL_BPZSCBVU();
    }
    public void S000_CALL_BPZSCBVU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_ULOS, BPCSCBVU);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
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
