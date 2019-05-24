package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3811 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP170";
    String CPN_BV_HIS_BRS = "BP-BV-HIS-BRS    ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSBVBQ BPCSBVBQ = new BPCSBVBQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3810_AWA_3810 BPB3810_AWA_3810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3811 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3810_AWA_3810>");
        BPB3810_AWA_3810 = (BPB3810_AWA_3810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_BV_HISTORY();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3810_AWA_3810.BV_CODE;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, BPB3810_AWA_3810.BEG_NO);
        CEP.TRC(SCCGWA, BPB3810_AWA_3810.END_NO);
        if (BPB3810_AWA_3810.BEG_NO.trim().length() > 0) {
            if (BPB3810_AWA_3810.BEG_NO == null) BPB3810_AWA_3810.BEG_NO = "";
            JIBS_tmp_int = BPB3810_AWA_3810.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3810_AWA_3810.BEG_NO += " ";
            for (WS_I = 1; IBS.isNumeric(BPB3810_AWA_3810.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1 
                || BPB3810_AWA_3810.BEG_NO.substring(WS_I-1).trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB3810_AWA_3810.BEG_NO.substring(WS_I-1));
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3810_AWA_3810.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3810_AWA_3810.END_NO.trim().length() > 0) {
            if (BPB3810_AWA_3810.END_NO == null) BPB3810_AWA_3810.END_NO = "";
            JIBS_tmp_int = BPB3810_AWA_3810.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3810_AWA_3810.END_NO += " ";
            for (WS_I = 1; IBS.isNumeric(BPB3810_AWA_3810.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1 
                || BPB3810_AWA_3810.END_NO.substring(WS_I-1).trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB3810_AWA_3810.END_NO.substring(WS_I-1));
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3810_AWA_3810.END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3810_AWA_3810.HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3810_AWA_3810.HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3810_AWA_3810.BEG_NO.trim().length() > 0 
                || BPB3810_AWA_3810.END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3810_AWA_3810.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPB3810_AWA_3810.BEG_NO == null) BPB3810_AWA_3810.BEG_NO = "";
            JIBS_tmp_int = BPB3810_AWA_3810.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3810_AWA_3810.BEG_NO += " ";
            if (BPB3810_AWA_3810.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3810_AWA_3810.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3810_AWA_3810.END_NO == null) BPB3810_AWA_3810.END_NO = "";
            JIBS_tmp_int = BPB3810_AWA_3810.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3810_AWA_3810.END_NO += " ";
            if (BPB3810_AWA_3810.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3810_AWA_3810.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3810_AWA_3810.BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_BV_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVBQ);
        BPCSBVBQ.FUNC = 'I';
        BPCSBVBQ.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBVBQ.CODE = BPB3810_AWA_3810.BV_CODE;
        CEP.TRC(SCCGWA, BPCSBVBQ.CODE);
        BPCSBVBQ.HEAD_NO = BPB3810_AWA_3810.HEAD_NO;
        BPCSBVBQ.BEG_NO = BPB3810_AWA_3810.BEG_NO;
        BPCSBVBQ.END_NO = BPB3810_AWA_3810.END_NO;
        BPCSBVBQ.BR = BPB3810_AWA_3810.BR;
        BPCSBVBQ.TX_DT = BPB3810_AWA_3810.TX_DT;
        BPCSBVBQ.TX_JRN = BPB3810_AWA_3810.TX_JRN;
        CEP.TRC(SCCGWA, BPB3810_AWA_3810.TX_JRN);
        S00_CALL_BPZSBVBQ();
    }
    public void S00_CALL_BPZSBVBQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BV_HIS_BRS, BPCSBVBQ);
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
