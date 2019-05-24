package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3060 {
    int JIBS_tmp_int;
    String CPN_S_BV_B_TO_B = "BP-S-BV-B-TO-B   ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD ";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    String WS_TEMP_PLBOX_NO = " ";
    char WS_TURE = ' ';
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCSBVBB BPCSBVBB = new BPCSBVBB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
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
        CEP.TRC(SCCGWA, "BPOT3060 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3020_AWA_3020>");
        BPB3020_AWA_3020 = (BPB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_TLR_BV_TRANS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        IBS.init(SCCGWA, BPRTBVD);
        IBS.init(SCCGWA, BPCRTBDB);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
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
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.POLL_BOX_IND = '1';
        BPCRVHPB.INFO.FUNC = 'B';
        S000_CALL_BPZRVHPB();
        BPCRVHPB.INFO.FUNC = 'N';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000; WS_CNT += 1) {
            WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
        }
        BPCRVHPB.INFO.FUNC = 'E';
        S000_CALL_BPZRVHPB();
        BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (WS_TEMP_PLBOX_NO.trim().length() == 0) {
            BPRTBVD.KEY.PL_BOX_NO = "%%%%%%";
        } else {
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        }
        CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
        if (BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE.trim().length() == 0) {
            BPRTBVD.KEY.BV_CODE = "%%%%%%";
        } else {
            BPRTBVD.KEY.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        }
        BPRTBVD.TYPE = ALL.charAt(0);
        if (BPB3020_AWA_3020.BV_STS == ' ') {
            BPCRTBDB.INFO.FUNC = 'S';
        } else {
            BPRTBVD.KEY.STS = BPB3020_AWA_3020.BV_STS;
            BPCRTBDB.INFO.FUNC = 'G';
        }
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        BPCRTBDB.INFO.FUNC = 'N';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "111111");
        for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000; WS_CNT += 1) {
            if (BPRTBVD.BEG_NO.equalsIgnoreCase(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO) 
                && BPRTBVD.KEY.END_NO.equalsIgnoreCase(BPB3020_AWA_3020.BV_DATA[1-1].END_NO)) {
                WS_TURE = '1';
            }
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
        }
        BPCRTBDB.INFO.FUNC = 'E';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (WS_TURE != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEGEND_END_ERR;
            WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_BV_TRANS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVBB);
        BPCSBVBB.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        BPCSBVBB.BV_NAME = BPB3020_AWA_3020.BV_DATA[1-1].BV_NAME;
        BPCSBVBB.BV_STS = BPB3020_AWA_3020.BV_STS;
        BPCSBVBB.HEAD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO;
        BPCSBVBB.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
        BPCSBVBB.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
        BPCSBVBB.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
        BPCSBVBB.RCV_TLR = BPB3020_AWA_3020.TLR;
        BPCSBVBB.PSW_TYP = BPB3020_AWA_3020.PSW_TYP;
        BPCSBVBB.TLRC_PSW = BPB3020_AWA_3020.TLRC_PSW;
        BPCSBVBB.TLRK_PSW = BPB3020_AWA_3020.TLRK_PSW;
        BPCSBVBB.REP_TLR = BPB3020_AWA_3020.REP_TLR;
        CEP.TRC(SCCGWA, BPCSBVBB.REP_TLR);
        CEP.TRC(SCCGWA, "1111111111111111");
        CEP.TRC(SCCGWA, BPCSBVBB);
        CEP.TRC(SCCGWA, BPCSBVBB.RCV_TLR);
        CEP.TRC(SCCGWA, BPCSBVBB.PSW_TYP);
        CEP.TRC(SCCGWA, BPCSBVBB.TLRK_PSW);
        S000_CALL_BPZSBVBB();
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_B_TO_B, BPCSBVBB);
        if (BPCSBVBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVBB.RC);
            WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE_NO;
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
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
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
