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

public class BPZSBVBV {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP154";
    short K_CYC_TIMES = 4;
    short K_CYC_TIMES_10 = 10;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_U_BV_IN = "BP-U-TLR-BV-IN      ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    String BPZSBVBV_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVBV_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVBV_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVBV_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVBV_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    String BPZSBVBV_FILLER1 = "BOX NO:";
    String WS_HIS_PB_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCO154 BPCO154 = new BPCO154();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCFCPSW BPCFCPSW = new BPCFCPSW();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVBV BPCSBVBV;
    public void MP(SCCGWA SCCGWA, BPCSBVBV BPCSBVBV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVBV = BPCSBVBV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVBV return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHK_INPUT();
            if (pgmRtn) return;
            if (BPCSBVBV.BV_FUNC == '0') {
                B020_TLR_BV_OUT();
                if (pgmRtn) return;
                B030_TLR_BV_IN();
                if (pgmRtn) return;
            } else {
                B015_BOX_OUT_RELATED();
                if (pgmRtn) return;
            }
            B050_REC_NHIS_CN();
            if (pgmRtn) return;
            B060_DATA_OUTPUT_CN();
            if (pgmRtn) return;
        } else {
            B010_CHK_INPUT();
            if (pgmRtn) return;
            B020_TLR_BV_OUT();
            if (pgmRtn) return;
            B030_TLR_BV_IN();
            if (pgmRtn) return;
            B050_REC_NHIS();
            if (pgmRtn) return;
            B060_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSBVBV.RCV_TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVBV.CNT);
        for (WS_I = 1; WS_I <= BPCSBVBV.CNT 
            && BPCSBVBV.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCUBVOU);
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVBV.BV_DATA[WS_I-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (BPCSBVBV.REP_TLR.trim().length() > 0) {
                BPCUBVOU.TLR = BPCSBVBV.REP_TLR;
            } else {
                BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
            }
            BPCUBVOU.TYPE = BPCFBVQU.TX_DATA.TYPE;
            CEP.TRC(SCCGWA, BPCSBVBV.BV_DATA[WS_I-1].BV_CODE);
            BPCUBVOU.BV_CODE = BPCSBVBV.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVOU.HEAD_NO = BPCSBVBV.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVOU.BEG_NO = BPCSBVBV.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVOU.END_NO = BPCSBVBV.BV_DATA[WS_I-1].END_NO;
            BPCUBVOU.NUM = BPCSBVBV.BV_DATA[WS_I-1].NUM;
            BPCUBVOU.BV_STS = BPCSBVBV.BV_DATA[WS_I-1].BV_STS;
            BPCUBVOU.VB_FLG = '0';
            BPCUBVOU.AC_TYP = '1';
            BPCUBVOU.USECTL = 'N';
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            S000_CALL_BPZUBVOU();
            if (pgmRtn) return;
        }
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVBV.BV_DATA[WS_I-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.TYPE == '0' 
            && BPCFBVQU.TX_DATA.USE_MODE != '0') {
            B021_ADD_BUSE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVBV.BV_DATA[WS_I-1].BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVBV.BV_DATA[WS_I-1].HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVBV.BV_DATA[WS_I-1].BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVBV.BV_DATA[WS_I-1].END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '0';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B030_TLR_BV_IN() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= BPCSBVBV.CNT 
            && BPCSBVBV.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCUBVIN);
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVBV.BV_DATA[WS_I-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            BPCUBVIN.TYPE = BPCFBVQU.TX_DATA.TYPE;
            BPCUBVIN.TLR = BPCSBVBV.RCV_TLR;
            BPCUBVIN.BV_CODE = BPCSBVBV.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVIN.HEAD_NO = BPCSBVBV.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVIN.BEG_NO = BPCSBVBV.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVIN.END_NO = BPCSBVBV.BV_DATA[WS_I-1].END_NO;
            BPCUBVIN.NUM = BPCSBVBV.BV_DATA[WS_I-1].NUM;
            BPCUBVIN.BV_STS = BPCSBVBV.BV_DATA[WS_I-1].BV_STS;
            BPCUBVIN.VB_FLG = '1';
            BPCUBVIN.AC_TYP = '1';
            S000_CALL_BPZUBVIN();
            if (pgmRtn) return;
        }
    }
    public void B015_BOX_OUT_RELATED() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = BPCSBVBV.PB_NO;
        CEP.TRC(SCCGWA, BPCSBVBV.PB_NO);
        BPCRVHPB.INFO.FUNC = 'R';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSBVBV.PB_NO);
        BPRVHPB.LST_TLR = BPRVHPB.CUR_TLR;
        BPRVHPB.CUR_TLR = " ";
        BPRVHPB.RELATE_FLG = 'N';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRVHPB.INFO.FUNC = 'U';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRVHPB.RELATE_FLG);
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
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= K_CYC_TIMES 
            && BPCSBVBV.BV_DATA[WS_CNT-1].BV_CODE.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'O';
            BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            WS_HIS_BVCODE = BPCSBVBV.BV_DATA[WS_CNT-1].BV_CODE;
            WS_HIS_HEADNO = BPCSBVBV.BV_DATA[WS_CNT-1].HEAD_NO;
            WS_HIS_BEGNO = BPCSBVBV.BV_DATA[WS_CNT-1].BEG_NO;
            WS_HIS_ENDNO = BPCSBVBV.BV_DATA[WS_CNT-1].END_NO;
            WS_HIS_NUMNO = BPCSBVBV.BV_DATA[WS_CNT-1].NUM;
            BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVBV_WS2);
            S000_CALL_BPZPNHIS();
            if (pgmRtn) return;
        }
    }
    public void B050_REC_NHIS_CN() throws IOException,SQLException,Exception {
        if (BPCSBVBV.BV_FUNC == '1') {
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'O';
            BPCPNHIS.INFO.TX_TYP_CD = "P906";
            BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            WS_HIS_PB_NO = BPCSBVBV.PB_NO;
            BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVBV_WS3);
        } else {
            for (WS_CNT = 1; WS_CNT <= K_CYC_TIMES_10 
                && BPCSBVBV.BV_DATA[WS_CNT-1].BV_CODE.trim().length() != 0; WS_CNT += 1) {
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'O';
                BPCPNHIS.INFO.TX_TYP_CD = "P906";
                BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                WS_HIS_BVCODE = BPCSBVBV.BV_DATA[WS_CNT-1].BV_CODE;
                WS_HIS_HEADNO = BPCSBVBV.BV_DATA[WS_CNT-1].HEAD_NO;
                WS_HIS_BEGNO = BPCSBVBV.BV_DATA[WS_CNT-1].BEG_NO;
                WS_HIS_ENDNO = BPCSBVBV.BV_DATA[WS_CNT-1].END_NO;
                WS_HIS_NUMNO = BPCSBVBV.BV_DATA[WS_CNT-1].NUM;
                BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVBV_WS2);
                S000_CALL_BPZPNHIS();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO154;
