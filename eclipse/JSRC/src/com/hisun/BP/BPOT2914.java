package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2914 {
    int JIBS_tmp_int;
    DBParm BPTADTL_RD;
    DBParm BPTVHPB_RD;
    DBParm BPTTBVD_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP257";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_CNT2 = 0;
    int WS_I = 0;
    String WS_POOL_BOX_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRADTL BPRADTL = new BPRADTL();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCOPLIR BPCOPLIR = new BPCOPLIR();
    SCCGWA SCCGWA;
    BPB2914_AWA_2914 BPB2914_AWA_2914;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2914 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2914_AWA_2914>");
        BPB2914_AWA_2914 = (BPB2914_AWA_2914) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPD_BV_APP();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2914_AWA_2914.APP_NO == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR53);
        }
        IBS.init(SCCGWA, BPRADTL);
        BPRADTL.KEY.APP_NO = BPB2914_AWA_2914.APP_NO;
        for (WS_I = 1; BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE.trim().length() != 0 
            && WS_I <= 10; WS_I += 1) {
            BPRADTL.KEY.BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE;
            T000_READ_BPTADTL();
            WS_CNT = BPRADTL.NUM1 + BPRADTL.NUM2 + BPRADTL.NUM3 + BPRADTL.NUM4;
            WS_CNT2 = BPB2914_AWA_2914.BV_INFO[WS_I-1].NUM1 + BPB2914_AWA_2914.BV_INFO[WS_I-1].NUM2 + BPB2914_AWA_2914.BV_INFO[WS_I-1].NUM3 + BPB2914_AWA_2914.BV_INFO[WS_I-1].NUM4;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_CNT2);
            if (WS_CNT2 != WS_CNT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_NOT_SAME;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        IBS.init(SCCGWA, BPRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.POLL_BOX_IND = '1';
        BPRVHPB.STS = 'N';
        T000_READ_BPTVHPB();
        WS_POOL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        for (WS_I = 1; BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE.trim().length() != 0 
            && WS_I <= 10; WS_I += 1) {
            IBS.init(SCCGWA, BPRTBVD);
            if (BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO1.trim().length() > 0 
                && BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO1.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO1);
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO1);
                BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.STS = '0';
                BPRTBVD.KEY.BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE;
                BPRTBVD.KEY.HEAD_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].HEAD_NO1;
                BPRTBVD.BEG_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO1;
                BPRTBVD.KEY.END_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO1;
                T000_READ_BPTTBVD();
            } else {
                BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.STS = '0';
                BPRTBVD.KEY.BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE;
                T000_READ_BPTTBVD();
                if (BPB2914_AWA_2914.BV_INFO[1-1].OUT_NUM > BPRTBVD.NUM) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OUT_NUM_LESS_NUM);
                }
            }
            if (BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO2.trim().length() > 0 
                && BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO2.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO2);
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO2);
                BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.STS = '0';
                BPRTBVD.KEY.BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE;
                BPRTBVD.KEY.HEAD_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].HEAD_NO2;
                BPRTBVD.BEG_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO2;
                BPRTBVD.KEY.END_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO2;
                T000_READ_BPTTBVD();
            }
            if (BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO3.trim().length() > 0 
                && BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO3.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO3);
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO3);
                BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.STS = '0';
                BPRTBVD.KEY.BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE;
                BPRTBVD.KEY.HEAD_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].HEAD_NO3;
                BPRTBVD.BEG_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO3;
                BPRTBVD.KEY.END_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO3;
                T000_READ_BPTTBVD();
            }
            if (BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO4.trim().length() > 0 
                && BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO4.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO4);
                CEP.TRC(SCCGWA, BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO4);
                BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.STS = '0';
                BPRTBVD.KEY.BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_I-1].BV_CODE;
                BPRTBVD.KEY.HEAD_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].HEAD_NO4;
                BPRTBVD.BEG_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].BEG_NO4;
                BPRTBVD.KEY.END_NO = BPB2914_AWA_2914.BV_INFO[WS_I-1].END_NO4;
                T000_READ_BPTTBVD();
            }
        }
    }
    public void T000_READ_BPTADTL() throws IOException,SQLException,Exception {
        BPTADTL_RD = new DBParm();
        BPTADTL_RD.TableName = "BPTADTL";
        BPTADTL_RD.where = "APP_NO = :BPRADTL.KEY.APP_NO "
            + "AND BV_CODE = :BPRADTL.KEY.BV_CODE";
        IBS.READ(SCCGWA, BPRADTL, this, BPTADTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ADTL_NOTFND);
        }
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        BPTVHPB_RD.where = "BR = :BPRVHPB.BR "
            + "AND POLL_BOX_IND = :BPRVHPB.POLL_BOX_IND "
            + "AND STS = :BPRVHPB.STS";
        IBS.READ(SCCGWA, BPRVHPB, this, BPTVHPB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VHPB_NOTFND);
        }
    }
    public void T000_READ_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.where = "PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BR = :BPRTBVD.KEY.BR "
            + "AND STS = :BPRTBVD.KEY.STS "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND HEAD_NO = :BPRTBVD.KEY.HEAD_NO "
            + "AND END_NO >= :BPRTBVD.KEY.END_NO "
            + "AND BEG_NO <= :BPRTBVD.BEG_NO "
            + "AND :BPRTBVD.BEG_NO <= :BPRTBVD.KEY.END_NO";
        IBS.READ(SCCGWA, BPRTBVD, this, BPTTBVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TBVD_BEG_NOTFND, BPRTBVD.BEG_NO);
        }
    }
    public void B020_UPD_BV_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'M';
        BPCOAPLL.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOAPLL.APP_NO = BPB2914_AWA_2914.APP_NO;
        BPCOAPLL.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOAPLL.MODIFY_STS = 'Q';
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPB2914_AWA_2914.BV_INFO[WS_CNT-1].BV_CODE.trim().length() != 0; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].BV_CODE;
            BPCOAPLL.BV_INFO[WS_CNT-1].OUT_NUM = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].OUT_NUM;
            BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO1 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].HEAD_NO1;
            BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO1 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].BEG_NO1;
            BPCOAPLL.BV_INFO[WS_CNT-1].END_NO1 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].END_NO1;
            BPCOAPLL.BV_INFO[WS_CNT-1].NUM1 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].NUM1;
            BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO2 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].HEAD_NO2;
            BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO2 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].BEG_NO2;
            BPCOAPLL.BV_INFO[WS_CNT-1].END_NO2 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].END_NO2;
            BPCOAPLL.BV_INFO[WS_CNT-1].NUM2 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].NUM2;
            BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO3 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].HEAD_NO3;
            BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO3 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].BEG_NO3;
            BPCOAPLL.BV_INFO[WS_CNT-1].END_NO3 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].END_NO3;
            BPCOAPLL.BV_INFO[WS_CNT-1].NUM3 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].NUM3;
            BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO4 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].HEAD_NO4;
            BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO4 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].BEG_NO4;
            BPCOAPLL.BV_INFO[WS_CNT-1].END_NO4 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].END_NO4;
            BPCOAPLL.BV_INFO[WS_CNT-1].NUM4 = BPB2914_AWA_2914.BV_INFO[WS_CNT-1].NUM4;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].OUT_NUM);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO1);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO1);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].END_NO1);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].NUM1);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO2);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO2);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].END_NO2);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].NUM2);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO3);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO3);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].END_NO3);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].NUM3);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].HEAD_NO4);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO4);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].END_NO4);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[WS_CNT-1].NUM4);
        }
        S000_CALL_BPZSAPLL();
    }
    public void S000_CALL_BPZSAPLL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BVAPP_MAINTAIN, BPCOAPLL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
