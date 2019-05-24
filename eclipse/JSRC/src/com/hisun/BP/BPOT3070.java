package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3070 {
    int JIBS_tmp_int;
    String CPN_S_BV_TO_DESTROY = "BP-S-BV-TO-DESTROY ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    int K_CNT = 4;
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
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
    BPCSBVTD BPCSBVTD = new BPCSBVTD();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3020_AWA_3020 BPB3020_AWA_3020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3070 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3020_AWA_3020>");
        BPB3020_AWA_3020 = (BPB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BV_TO_DESTROY();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, "AAAA");
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO);
        if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
            }
            CEP.TRC(SCCGWA, "12341234");
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].END_NO);
        if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0 
                || BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            BPCSNOCK.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN);
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
            BPCSNOCK.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN);
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
        }
        CEP.TRC(SCCGWA, "CCCCC");
    }
    public void B020_BV_TO_DESTROY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVTD);
        BPCSBVTD.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        BPCSBVTD.BV_NAME = BPB3020_AWA_3020.BV_DATA[1-1].BV_NAME;
        BPCSBVTD.HEAD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO;
        BPCSBVTD.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
        BPCSBVTD.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
        BPCSBVTD.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
        S000_CALL_BPZSBVTD();
        BPB3020_AWA_3020.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPB3020_AWA_3020.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_TO_DESTROY, BPCSBVTD);
        CEP.TRC(SCCGWA, BPCSBVTD.RC);
        if (BPCSBVTD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVTD.RC);
            WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        CEP.TRC(SCCGWA, BPCFBVQU.RC.RC_CODE);
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
