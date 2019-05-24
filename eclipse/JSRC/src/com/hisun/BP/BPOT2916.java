package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2916 {
    int JIBS_tmp_int;
    DBParm BPTTBVD_RD;
    DBParm BPTVHPB_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP259";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    char K_RUN_MODE = 'O';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "CASHMOVE";
    String K_SEQ_TYPE = "CMOVE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_I = 0;
    String WS_POOL_BOX_NO = " ";
    char WS_FLG = ' ';
    int WS_TOTAL_NUM = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO1 = 0;
    long WS_COMP_ENDNO1 = 0;
    long WS_COMP_BEGNO2 = 0;
    long WS_COMP_ENDNO2 = 0;
    long WS_COMP_BEGNO3 = 0;
    long WS_COMP_ENDNO3 = 0;
    long WS_COMP_BEGNO4 = 0;
    long WS_COMP_ENDNO4 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB2916_AWA_2916 BPB2916_AWA_2916;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2916 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2916_AWA_2916>");
        BPB2916_AWA_2916 = (BPB2916_AWA_2916) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_UPD_BV_APP();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR35);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.SEQ);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.APP_NO);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.APP_BR);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.BR);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.BV_CODE);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.OUT_NUM);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.HEAD_NO1);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO1);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO1);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.NUM1);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.HEAD_NO2);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO2);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO2);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.NUM2);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.HEAD_NO3);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO3);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO3);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.NUM3);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.HEAD_NO4);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO4);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO4);
        CEP.TRC(SCCGWA, BPB2916_AWA_2916.NUM4);
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB2916_AWA_2916.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPB2916_AWA_2916.BEG_NO1.trim().length() > 0) {
            if (BPB2916_AWA_2916.BEG_NO1 == null) BPB2916_AWA_2916.BEG_NO1 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.BEG_NO1.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.BEG_NO1 += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB2916_AWA_2916.BEG_NO1.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                if (BPB2916_AWA_2916.BEG_NO1.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPB2916_AWA_2916.BEG_NO1);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB2916_AWA_2916.END_NO1.trim().length() > 0) {
            if (BPB2916_AWA_2916.END_NO1 == null) BPB2916_AWA_2916.END_NO1 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.END_NO1.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.END_NO1 += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB2916_AWA_2916.END_NO1.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                if (BPB2916_AWA_2916.END_NO1.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPB2916_AWA_2916.END_NO1);
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPB2916_AWA_2916.BEG_NO1.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO1.trim().length() > 0) {
            if (BPB2916_AWA_2916.BEG_NO1 == null) BPB2916_AWA_2916.BEG_NO1 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.BEG_NO1.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.BEG_NO1 += " ";
            if (BPB2916_AWA_2916.BEG_NO1.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO1 = 0;
            else WS_COMP_BEGNO1 = Long.parseLong(BPB2916_AWA_2916.BEG_NO1.substring(0, WS_BVNO_LEN));
            if (BPB2916_AWA_2916.END_NO1 == null) BPB2916_AWA_2916.END_NO1 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.END_NO1.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.END_NO1 += " ";
            if (BPB2916_AWA_2916.END_NO1.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO1 = 0;
            else WS_COMP_ENDNO1 = Long.parseLong(BPB2916_AWA_2916.END_NO1.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO1 > WS_COMP_ENDNO1) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END, BPB2916_AWA_2916.BEG_NO1);
            }
            if (WS_COMP_BEGNO1 == 0 
                || WS_COMP_ENDNO1 == 0 
                || BPB2916_AWA_2916.NUM1 != WS_COMP_ENDNO1 - WS_COMP_BEGNO1 + 1) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END_NUM, BPB2916_AWA_2916.BEG_NO1);
            }
        }
        if (BPB2916_AWA_2916.BEG_NO2.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO2.trim().length() > 0) {
            if (BPB2916_AWA_2916.BEG_NO2 == null) BPB2916_AWA_2916.BEG_NO2 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.BEG_NO2.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.BEG_NO2 += " ";
            if (BPB2916_AWA_2916.BEG_NO2.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO2 = 0;
            else WS_COMP_BEGNO2 = Long.parseLong(BPB2916_AWA_2916.BEG_NO2.substring(0, WS_BVNO_LEN));
            if (BPB2916_AWA_2916.END_NO2 == null) BPB2916_AWA_2916.END_NO2 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.END_NO2.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.END_NO2 += " ";
            if (BPB2916_AWA_2916.END_NO2.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO2 = 0;
            else WS_COMP_ENDNO2 = Long.parseLong(BPB2916_AWA_2916.END_NO2.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO2 > WS_COMP_ENDNO2) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END, BPB2916_AWA_2916.BEG_NO2);
            }
            if (WS_COMP_BEGNO2 == 0 
                || WS_COMP_ENDNO2 == 0 
                || BPB2916_AWA_2916.NUM2 != WS_COMP_ENDNO2 - WS_COMP_BEGNO2 + 1) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END_NUM, BPB2916_AWA_2916.BEG_NO2);
            }
        }
        if (BPB2916_AWA_2916.BEG_NO3.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO3.trim().length() > 0) {
            if (BPB2916_AWA_2916.BEG_NO3 == null) BPB2916_AWA_2916.BEG_NO3 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.BEG_NO3.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.BEG_NO3 += " ";
            if (BPB2916_AWA_2916.BEG_NO3.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO3 = 0;
            else WS_COMP_BEGNO3 = Long.parseLong(BPB2916_AWA_2916.BEG_NO3.substring(0, WS_BVNO_LEN));
            if (BPB2916_AWA_2916.END_NO3 == null) BPB2916_AWA_2916.END_NO3 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.END_NO3.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.END_NO3 += " ";
            if (BPB2916_AWA_2916.END_NO3.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO3 = 0;
            else WS_COMP_ENDNO3 = Long.parseLong(BPB2916_AWA_2916.END_NO3.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO3 > WS_COMP_ENDNO3) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END, BPB2916_AWA_2916.BEG_NO3);
            }
            if (WS_COMP_BEGNO3 == 0 
                || WS_COMP_ENDNO3 == 0 
                || BPB2916_AWA_2916.NUM3 != WS_COMP_ENDNO3 - WS_COMP_BEGNO3 + 1) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END_NUM, BPB2916_AWA_2916.BEG_NO3);
            }
        }
        if (BPB2916_AWA_2916.BEG_NO4.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO4.trim().length() > 0) {
            if (BPB2916_AWA_2916.BEG_NO4 == null) BPB2916_AWA_2916.BEG_NO4 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.BEG_NO4.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.BEG_NO4 += " ";
            if (BPB2916_AWA_2916.BEG_NO4.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO4 = 0;
            else WS_COMP_BEGNO4 = Long.parseLong(BPB2916_AWA_2916.BEG_NO4.substring(0, WS_BVNO_LEN));
            if (BPB2916_AWA_2916.END_NO4 == null) BPB2916_AWA_2916.END_NO4 = "";
            JIBS_tmp_int = BPB2916_AWA_2916.END_NO4.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB2916_AWA_2916.END_NO4 += " ";
            if (BPB2916_AWA_2916.END_NO4.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO4 = 0;
            else WS_COMP_ENDNO4 = Long.parseLong(BPB2916_AWA_2916.END_NO4.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO4 > WS_COMP_ENDNO4) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END, BPB2916_AWA_2916.BEG_NO4);
            }
            if (WS_COMP_BEGNO4 == 0 
                || WS_COMP_ENDNO4 == 0 
                || BPB2916_AWA_2916.NUM4 != WS_COMP_ENDNO4 - WS_COMP_BEGNO4 + 1) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END_NUM, BPB2916_AWA_2916.BEG_NO4);
            }
        }
        if (BPB2916_AWA_2916.APP_NO != 0) {
            WS_FLG = '1';
        } else {
            WS_FLG = '2';
        }
        if (BPB2916_AWA_2916.SEQ == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR61);
        }
        if (BPB2916_AWA_2916.APP_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR54);
        } else {
            CEP.TRC(SCCGWA, "DEV1");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB2916_AWA_2916.APP_BR;
            S000_CALL_BPZPQORG();
        }
        if (BPB2916_AWA_2916.BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR55);
        } else {
            CEP.TRC(SCCGWA, "DEV2");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB2916_AWA_2916.BR;
            S000_CALL_BPZPQORG();
        }
        if (BPB2916_AWA_2916.BV_CODE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_BV_CODE_EMPTY);
        }
        if (BPB2916_AWA_2916.OUT_NUM == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_NUM_ZERO);
        }
        if (WS_FLG == '1' 
            && BPB2916_AWA_2916.APP_NO == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR133);
        }
        WS_TOTAL_NUM = BPB2916_AWA_2916.NUM1 + BPB2916_AWA_2916.NUM2 + BPB2916_AWA_2916.NUM3 + BPB2916_AWA_2916.NUM4;
        if (WS_TOTAL_NUM != BPB2916_AWA_2916.OUT_NUM) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_NOT_EQ_OUTNUM);
        }
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = "09";
        BPCPQORR.BR = BPB2916_AWA_2916.APP_BR;
        CEP.TRC(SCCGWA, BPCPQORR.BR);
        CEP.TRC(SCCGWA, BPCPQORR.TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        S000_CALL_BPZPQORR();
        CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
        if (BPCPQORR.REL_BR != BPB2916_AWA_2916.BR 
            || BPCPQORR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR137);
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2916_AWA_2916.TR_TLR;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        if (BPCFTLRQ.INFO.TLR_BR != BPB2916_AWA_2916.BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR138);
        }
        if (BPB2916_AWA_2916.APP_NO == 0) {
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = K_SEQ_TYPE;
            BPCSGSEQ.CODE = K_BPFBAS_SEQ;
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = K_RUN_MODE;
            BPCSGSEQ.NUM = K_NUM;
            S000_CALL_BPZSGSEQ();
            BPB2916_AWA_2916.APP_NO = (int) BPCSGSEQ.SEQ;
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.APP_NO);
        }
        IBS.init(SCCGWA, BPRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.POLL_BOX_IND = '1';
        BPRVHPB.STS = 'N';
        T000_READ_BPTVHPB();
        WS_POOL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        IBS.init(SCCGWA, BPRTBVD);
        if (BPB2916_AWA_2916.BEG_NO1.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO1.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO1);
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO1);
            BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTBVD.KEY.STS = '0';
            BPRTBVD.KEY.BV_CODE = BPB2916_AWA_2916.BV_CODE;
            BPRTBVD.KEY.HEAD_NO = BPB2916_AWA_2916.HEAD_NO1;
            BPRTBVD.BEG_NO = BPB2916_AWA_2916.BEG_NO1;
            BPRTBVD.KEY.END_NO = BPB2916_AWA_2916.END_NO1;
            T000_READ_BPTTBVD();
        } else {
            BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTBVD.KEY.STS = '0';
            BPRTBVD.KEY.BV_CODE = BPB2916_AWA_2916.BV_CODE;
            T000_READ_BPTTBVD();
            if (BPB2916_AWA_2916.OUT_NUM > BPRTBVD.NUM) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OUT_NUM_LESS_NUM);
            }
        }
        if (BPB2916_AWA_2916.BEG_NO2.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO2.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO2);
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO2);
            BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTBVD.KEY.STS = '0';
            BPRTBVD.KEY.BV_CODE = BPB2916_AWA_2916.BV_CODE;
            BPRTBVD.KEY.HEAD_NO = BPB2916_AWA_2916.HEAD_NO2;
            BPRTBVD.BEG_NO = BPB2916_AWA_2916.BEG_NO2;
            BPRTBVD.KEY.END_NO = BPB2916_AWA_2916.END_NO2;
            T000_READ_BPTTBVD();
        }
        if (BPB2916_AWA_2916.BEG_NO3.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO3.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO3);
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO3);
            BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTBVD.KEY.STS = '0';
            BPRTBVD.KEY.BV_CODE = BPB2916_AWA_2916.BV_CODE;
            BPRTBVD.KEY.HEAD_NO = BPB2916_AWA_2916.HEAD_NO3;
            BPRTBVD.BEG_NO = BPB2916_AWA_2916.BEG_NO3;
            BPRTBVD.KEY.END_NO = BPB2916_AWA_2916.END_NO3;
            T000_READ_BPTTBVD();
        }
        if (BPB2916_AWA_2916.BEG_NO4.trim().length() > 0 
            && BPB2916_AWA_2916.END_NO4.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.BEG_NO4);
            CEP.TRC(SCCGWA, BPB2916_AWA_2916.END_NO4);
            BPRTBVD.KEY.PL_BOX_NO = WS_POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTBVD.KEY.STS = '0';
            BPRTBVD.KEY.BV_CODE = BPB2916_AWA_2916.BV_CODE;
            BPRTBVD.KEY.HEAD_NO = BPB2916_AWA_2916.HEAD_NO4;
            BPRTBVD.BEG_NO = BPB2916_AWA_2916.BEG_NO4;
            BPRTBVD.KEY.END_NO = BPB2916_AWA_2916.END_NO4;
            T000_READ_BPTTBVD();
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
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-REL", BPCPQORR);
    }
    public void B020_UPD_BV_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'M';
        BPCOAPLL.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOAPLL.MODIFY_STS = 'Q';
        BPCOAPLL.FLG = WS_FLG;
        BPCOAPLL.SEQ = BPB2916_AWA_2916.SEQ;
        BPCOAPLL.BR = BPB2916_AWA_2916.BR;
        BPCOAPLL.TR_TLR = BPB2916_AWA_2916.TR_TLR;
        BPCOAPLL.APP_NO = BPB2916_AWA_2916.APP_NO;
        BPCOAPLL.APP_BR = BPB2916_AWA_2916.APP_BR;
        BPCOAPLL.BR = BPB2916_AWA_2916.BR;
        BPCOAPLL.APP_TYPE = '0';
        BPCOAPLL.BV_INFO[1-1].BV_CODE = BPB2916_AWA_2916.BV_CODE;
        BPCOAPLL.BV_INFO[1-1].OUT_NUM = BPB2916_AWA_2916.OUT_NUM;
        BPCOAPLL.BV_INFO[1-1].HEAD_NO1 = BPB2916_AWA_2916.HEAD_NO1;
        BPCOAPLL.BV_INFO[1-1].BEG_NO1 = BPB2916_AWA_2916.BEG_NO1;
        BPCOAPLL.BV_INFO[1-1].END_NO1 = BPB2916_AWA_2916.END_NO1;
        BPCOAPLL.BV_INFO[1-1].NUM1 = BPB2916_AWA_2916.NUM1;
        BPCOAPLL.BV_INFO[1-1].HEAD_NO2 = BPB2916_AWA_2916.HEAD_NO2;
        BPCOAPLL.BV_INFO[1-1].BEG_NO2 = BPB2916_AWA_2916.BEG_NO2;
        BPCOAPLL.BV_INFO[1-1].END_NO2 = BPB2916_AWA_2916.END_NO2;
        BPCOAPLL.BV_INFO[1-1].NUM2 = BPB2916_AWA_2916.NUM2;
        BPCOAPLL.BV_INFO[1-1].HEAD_NO3 = BPB2916_AWA_2916.HEAD_NO3;
        BPCOAPLL.BV_INFO[1-1].BEG_NO3 = BPB2916_AWA_2916.BEG_NO3;
        BPCOAPLL.BV_INFO[1-1].END_NO3 = BPB2916_AWA_2916.END_NO3;
        BPCOAPLL.BV_INFO[1-1].NUM3 = BPB2916_AWA_2916.NUM3;
        BPCOAPLL.BV_INFO[1-1].HEAD_NO4 = BPB2916_AWA_2916.HEAD_NO4;
        BPCOAPLL.BV_INFO[1-1].BEG_NO4 = BPB2916_AWA_2916.BEG_NO4;
        BPCOAPLL.BV_INFO[1-1].END_NO4 = BPB2916_AWA_2916.END_NO4;
        BPCOAPLL.BV_INFO[1-1].NUM4 = BPB2916_AWA_2916.NUM4;
        S000_CALL_BPZSAPLL();
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC.RC_CODE);
        }
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
