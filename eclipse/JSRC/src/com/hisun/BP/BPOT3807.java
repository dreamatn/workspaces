package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3807 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTVHPB_RD;
    brParm BPTTBVD_BR = new brParm();
    String K_OUTPUT_FMT = "BPA65";
    String CPN_S_TLR_VB_INQ = "BP-S-TLR-VB-INQ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    int WS_CNT = 0;
    String WS_PL_BOX_NO = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCSTBDB BPCSTBDB = new BPCSTBDB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCO173 BPCO173 = new BPCO173();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB3807_AWA_3807 BPB3807_AWA_3807;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3807 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3807_AWA_3807>");
        BPB3807_AWA_3807 = (BPB3807_AWA_3807) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_TBVD_RECORD();
        B030_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3807_AWA_3807.BR);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB3807_AWA_3807.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB3807_AWA_3807.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3807_AWA_3807.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
        } else {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPRGST.BR2 = BPB3807_AWA_3807.BR;
            S000_CALL_BPZPRGST();
            if (BPCPRGST.LVL_RELATION == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LOW_BR;
                WS_FLD_NO = BPB3807_AWA_3807.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3807_AWA_3807.TLR.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3807_AWA_3807.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                WS_FLD_NO = BPB3807_AWA_3807.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_TBVD_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3807_AWA_3807.BR);
        CEP.TRC(SCCGWA, BPB3807_AWA_3807.TLR);
        CEP.TRC(SCCGWA, BPB3807_AWA_3807.STS);
        IBS.init(SCCGWA, BPRVHPB);
        BPRVHPB.BR = BPB3807_AWA_3807.BR;
        BPRVHPB.POLL_BOX_IND = '0';
        BPRVHPB.CUR_TLR = BPB3807_AWA_3807.TLR;
        T000_READ_BPTVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        WS_PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = BPB3807_AWA_3807.BR;
        BPRTBVD.KEY.PL_BOX_NO = WS_PL_BOX_NO;
        BPRTBVD.KEY.STS = BPB3807_AWA_3807.STS;
        T000_STARTBR_BPTTBVD();
        T000_READNEXT_BPTTBVD();
        for (WS_CNT = 1; WS_TBL_FLAG != 'N' 
            && WS_CNT <= 20; WS_CNT += 1) {
            R100_TRANS_DATA_OUPUT();
            T000_READNEXT_BPTTBVD();
        }
        T000_ENDBR_BPTTBVD();
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        BPTVHPB_RD.where = "BR = :BPRVHPB.BR "
            + "AND POLL_BOX_IND = :BPRVHPB.POLL_BOX_IND "
            + "AND CUR_TLR = :BPRVHPB.CUR_TLR";
        BPTVHPB_RD.upd = true;
        IBS.READ(SCCGWA, BPRVHPB, this, BPTVHPB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VHPB_NOTFND);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_STARTBR_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.order = "BV_CODE,BEG_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTTBVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTBVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTBVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void R100_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DATA OUTPUT");
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPB3807_AWA_3807.TLR);
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        BPCO173.BR = BPRTBVD.KEY.BR;
        CEP.TRC(SCCGWA, BPCO173.BR);
        BPCO173.PB_NO = BPRTBVD.KEY.PL_BOX_NO;
        CEP.TRC(SCCGWA, BPCO173.PB_NO);
        BPCO173.TLR = BPB3807_AWA_3807.TLR;
        CEP.TRC(SCCGWA, BPCO173.TLR);
        BPCO173.VB_FLG = BPRVHPB.POLL_BOX_IND;
        CEP.TRC(SCCGWA, BPCO173.VB_FLG);
        CEP.TRC(SCCGWA, WS_CNT);
        BPCO173.VB_INFO[WS_CNT-1].BV_CODE = BPRTBVD.KEY.BV_CODE;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].BV_CODE);
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCO173.VB_INFO[WS_CNT-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.BV_CNM);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
        CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
        CEP.TRC(SCCGWA, BPRTBVD.NUM);
        BPCO173.VB_INFO[WS_CNT-1].BLL_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].BLL_CNM);
        BPCO173.VB_INFO[WS_CNT-1].STS = BPRTBVD.KEY.STS;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].STS);
        BPCO173.VB_INFO[WS_CNT-1].HEAD_NO = BPRTBVD.KEY.HEAD_NO;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].HEAD_NO);
        BPCO173.VB_INFO[WS_CNT-1].BEG_NO = BPRTBVD.BEG_NO;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].BEG_NO);
        BPCO173.VB_INFO[WS_CNT-1].END_NO = BPRTBVD.KEY.END_NO;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].END_NO);
        BPCO173.VB_INFO[WS_CNT-1].COPY_NUM = 0;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].COPY_NUM);
        BPCO173.VB_INFO[WS_CNT-1].NUM = BPRTBVD.NUM;
        CEP.TRC(SCCGWA, BPCO173.VB_INFO[WS_CNT-1].NUM);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCO173);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO173;
        SCCFMT.DATA_LEN = 4741;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB3807_AWA_3807.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB3807_AWA_3807.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSTBIV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_VB_INQ, BPCSTBDB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
