package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3160 {
    int JIBS_tmp_int;
    String CPN_S_BV_B_TO_T = "BP-S-BV-B-TO-T   ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    int K_CNT = 10;
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    int WS_J = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRTBVD BPRTEMP = new BPRTBVD();
    BPCSBVBT BPCSBVBT = new BPCSBVBT();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3160_AWA_3160 BPB3160_AWA_3160;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3160 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3160_AWA_3160>");
        BPB3160_AWA_3160 = (BPB3160_AWA_3160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3160_AWA_3160.MOV_FLG);
        if (BPB3160_AWA_3160.MOV_FLG == 'Y') {
            B030_BV_TRANS_CH();
        } else {
            B010_CHECK_INPUT();
            B020_TLR_BV_TRANS();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3160_AWA_3160);
        for (WS_J = 1; WS_J <= K_CNT 
            && BPB3160_AWA_3160.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3160_AWA_3160.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO == null) BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO == null) BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO == null) BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO == null) BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPCSNOCK);
                BPCSNOCK.BV_CODE = BPB3160_AWA_3160.BV_DATA[WS_J-1].BV_CODE;
                BPCSNOCK.BEG_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].BEG_NO;
                BPCSNOCK.END_NO = BPB3160_AWA_3160.BV_DATA[WS_J-1].END_NO;
                BPCSNOCK.NUM = BPB3160_AWA_3160.BV_DATA[WS_J-1].NUM;
                BPCSNOCK.FUNC = '1';
                S000_CALL_BPZSNOCK();
            }
        }
    }
    public void B020_TLR_BV_TRANS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVBT);
        for (WS_I = 1; WS_I <= K_CNT; WS_I += 1) {
            BPCSBVBT.BV_DATA[WS_I-1].BV_CODE = BPB3160_AWA_3160.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVBT.BV_DATA[WS_I-1].BV_NAME = BPB3160_AWA_3160.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVBT.BV_DATA[WS_I-1].BV_STS = BPB3160_AWA_3160.BV_DATA[WS_I-1].BV_STS;
            BPCSBVBT.BV_DATA[WS_I-1].HEAD_NO = BPB3160_AWA_3160.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVBT.BV_DATA[WS_I-1].BEG_NO = BPB3160_AWA_3160.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVBT.BV_DATA[WS_I-1].END_NO = BPB3160_AWA_3160.BV_DATA[WS_I-1].END_NO;
            BPCSBVBT.BV_DATA[WS_I-1].NUM = BPB3160_AWA_3160.BV_DATA[WS_I-1].NUM;
        }
        BPCSBVBT.RCV_TLR = BPB3160_AWA_3160.TLR;
        BPCSBVBT.PSW_TYP = BPB3160_AWA_3160.PSW_TYP;
        BPCSBVBT.TLRC_PSW = BPB3160_AWA_3160.TLRC_PSW;
        BPCSBVBT.TLRK_PSW = BPB3160_AWA_3160.TLRK_PSW;
        CEP.TRC(SCCGWA, BPCSBVBT);
        CEP.TRC(SCCGWA, BPCSBVBT.RCV_TLR);
        CEP.TRC(SCCGWA, BPCSBVBT.PSW_TYP);
        CEP.TRC(SCCGWA, BPCSBVBT.TLRK_PSW);
        CEP.TRC(SCCGWA, BPB3160_AWA_3160.PSW_TYP);
        CEP.TRC(SCCGWA, BPB3160_AWA_3160.TLRC_PSW);
        CEP.TRC(SCCGWA, BPB3160_AWA_3160.TLRK_PSW);
        S000_CALL_BPZSBVBT();
    }
    public void B030_BV_TRANS_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        IBS.init(SCCGWA, BPCRTBDB);
        IBS.init(SCCGWA, BPRTBVD);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.POLL_BOX_IND = '0';
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRVHPB.INFO.FUNC = 'B';
        S000_CALL_BPZRVHPB();
        BPCRVHPB.INFO.FUNC = 'N';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BBOX_TLR;
            S000_ERR_MSG_PROC();
        }
        BPCRVHPB.INFO.FUNC = 'E';
        S000_CALL_BPZRVHPB();
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3160_AWA_3160.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTBVD.KEY.PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        BPRTBVD.KEY.BV_CODE = BPB3160_AWA_3160.BV_DATA[1-1].BV_CODE;
        BPRTBVD.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCRTBDB.INFO.FUNC = 'P';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        BPCRTBDB.INFO.FUNC = 'M';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
        if (BPCRTBDB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 1; BPCRTBDB.RETURN_INFO != 'N' 
            && WS_I <= 1000; WS_I += 1) {
            IBS.init(SCCGWA, BPCSBVBT);
            BPCSBVBT.BV_DATA[1-1].BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSBVBT.BV_DATA[1-1].BV_NAME = BPCFBVQU.TX_DATA.BV_CNM;
            BPCSBVBT.BV_DATA[1-1].BV_STS = BPRTBVD.KEY.STS;
            BPCSBVBT.BV_DATA[1-1].HEAD_NO = BPRTBVD.KEY.HEAD_NO;
            BPCSBVBT.BV_DATA[1-1].BEG_NO = BPRTBVD.BEG_NO;
            BPCSBVBT.BV_DATA[1-1].END_NO = BPRTBVD.KEY.END_NO;
            BPCSBVBT.BV_DATA[1-1].NUM = BPRTBVD.NUM;
            BPCSBVBT.RCV_TLR = BPB3160_AWA_3160.TLR;
            BPCSBVBT.MOV_FLG = BPB3160_AWA_3160.MOV_FLG;
            CEP.TRC(SCCGWA, BPCSBVBT);
            CEP.TRC(SCCGWA, BPCSBVBT.RCV_TLR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            S000_CALL_BPZSBVBT();
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.TYPE);
            BPCRTBDB.INFO.FUNC = 'M';
            S000_CALL_BPZRTBDB();
        }
        BPCRTBDB.INFO.FUNC = 'F';
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        S000_CALL_BPZRTBDB();
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVBT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_B_TO_T, BPCSBVBT);
        if (BPCSBVBT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVBT.RC);
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
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
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
