package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3030 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTAPLI_RD;
    brParm BPTADTL_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_S_BV_MOV_IN = "BP-S-BV-MOV-IN   ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    int K_CNT = 10;
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    String WS_BV_CODE = " ";
    String WS_BV_NAME = " ";
    BPOT3030_WS_BV_INFO[] WS_BV_INFO = new BPOT3030_WS_BV_INFO[4];
    BPOT3030_WS_APP_INFO WS_APP_INFO = new BPOT3030_WS_APP_INFO();
    int WS_APP_NO = 0;
    int WS_A = 0;
    int WS_CNT = 0;
    char WS_APP_TYPE = ' ';
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVMI BPCSBVMI = new BPCSBVMI();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPRADTL BPRADTL = new BPRADTL();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3030_AWA_3030 BPB3030_AWA_3030;
    public BPOT3030() {
        for (int i=0;i<4;i++) WS_BV_INFO[i] = new BPOT3030_WS_BV_INFO();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.APP_NO_G);
        IBS.CPY2CLS(SCCGWA, BPB3030_AWA_3030.APP_NO_G, WS_APP_INFO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT-1]);
        for (WS_CNT = 1; WS_CNT <= 2 
            && (JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0")); WS_CNT += 1) {
        }
        CEP.TRC(SCCGWA, WS_CNT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
        for (WS_A = 1; WS_A <= 200 
            && ((JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0")) 
            || WS_A <= 1); WS_A += 1) {
            CEP.TRC(SCCGWA, WS_A);
            if (WS_CNT == 1) {
                IBS.init(SCCGWA, BPRAPLI);
                BPRAPLI.CONF_NO = BPB3030_AWA_3030.CONF_NO;
                CEP.TRC(SCCGWA, BPRAPLI.CONF_NO);
                T000_READ1_BPTAPLI();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR155);
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
                A000_REDF_AWA();
                if (pgmRtn) return;
            }
            WS_APP_NO = 0;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
            if (JIBS_tmp_str[0].trim().length() == 0) {
                WS_APP_NO = 0;
            } else {
                WS_APP_NO = WS_APP_INFO.WS_APP_G[WS_A-1].WS_APP_N;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_A-1]);
            CEP.TRC(SCCGWA, WS_APP_NO);
            B000_MAIN_PROC();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPB3030_AWA_3030.APP_NO_G, WS_APP_INFO);
        }
        CEP.TRC(SCCGWA, "BPOT3030 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3030_AWA_3030>");
        BPB3030_AWA_3030 = (BPB3030_AWA_3030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPRADTL);
        BPRAPLI.KEY.APP_NO = WS_APP_INFO.WS_APP_G[WS_A-1].WS_APP_N;
        BPRADTL.KEY.APP_NO = WS_APP_INFO.WS_APP_G[WS_A-1].WS_APP_N;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        T000_READ_BPTAPLI();
        if (pgmRtn) return;
        BPB3030_AWA_3030.MOV_DT = BPRAPLI.OUT_DT;
        BPB3030_AWA_3030.CONF_NO = BPRAPLI.CONF_NO;
        CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
        if (BPRAPLI.APP_TYPE == '0') {
            BPB3030_AWA_3030.BR = BPRAPLI.UP_BR;
            BPB3030_AWA_3030.BV_STS = '0';
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                BPB3030_AWA_3030.BR = BPRAPLI.APP_BR;
            }
        }
        BPB3030_AWA_3030.TLR = BPRAPLI.OUT_TLR;
        BPB3030_AWA_3030.ACNO = " ";
        T000_STARTBR_BPTADTL_1();
        if (pgmRtn) return;
        T000_READNEXT_BPTADTL();
        if (pgmRtn) return;
        for (WS_J = 1; WS_J <= 4; WS_J += 1) {
            WS_BV_CODE = " ";
            WS_BV_NAME = " ";
            WS_BV_INFO[WS_J-1].WS_HEAD_NO = " ";
            WS_BV_INFO[WS_J-1].WS_BEG_NO = " ";
            WS_BV_INFO[WS_J-1].WS_END_NO = " ";
            WS_BV_INFO[WS_J-1].WS_NUM = 0;
        }
        for (WS_J = 1; WS_TBL_FLAG != 'N'; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPRADTL.KEY.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            BPB3030_AWA_3030.BV_STS = BPRADTL.STS;
            WS_BV_CODE = BPRADTL.KEY.BV_CODE;
            WS_BV_NAME = BPCFBVQU.TX_DATA.BV_CNM;
            WS_BV_INFO[1-1].WS_HEAD_NO = BPRADTL.HEAD_NO1;
            WS_BV_INFO[1-1].WS_BEG_NO = BPRADTL.BEG_NO1;
            WS_BV_INFO[1-1].WS_END_NO = BPRADTL.END_NO1;
            WS_BV_INFO[1-1].WS_NUM = BPRADTL.NUM1;
            WS_BV_INFO[2-1].WS_HEAD_NO = BPRADTL.HEAD_NO2;
            WS_BV_INFO[2-1].WS_BEG_NO = BPRADTL.BEG_NO2;
            WS_BV_INFO[2-1].WS_END_NO = BPRADTL.END_NO2;
            WS_BV_INFO[2-1].WS_NUM = BPRADTL.NUM2;
            WS_BV_INFO[3-1].WS_HEAD_NO = BPRADTL.HEAD_NO3;
            WS_BV_INFO[3-1].WS_BEG_NO = BPRADTL.BEG_NO3;
            WS_BV_INFO[3-1].WS_END_NO = BPRADTL.END_NO3;
            WS_BV_INFO[3-1].WS_NUM = BPRADTL.NUM3;
            WS_BV_INFO[4-1].WS_HEAD_NO = BPRADTL.HEAD_NO4;
            WS_BV_INFO[4-1].WS_BEG_NO = BPRADTL.BEG_NO4;
            WS_BV_INFO[4-1].WS_END_NO = BPRADTL.END_NO4;
            WS_BV_INFO[4-1].WS_NUM = BPRADTL.NUM4;
            for (WS_I = 1; WS_I <= 4; WS_I += 1) {
                BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_CODE = " ";
                BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_NAME = " ";
                BPB3030_AWA_3030.BV_DATA[WS_I-1].HEAD_NO = " ";
                BPB3030_AWA_3030.BV_DATA[WS_I-1].BEG_NO = " ";
                BPB3030_AWA_3030.BV_DATA[WS_I-1].END_NO = " ";
                BPB3030_AWA_3030.BV_DATA[WS_I-1].NUM = 0;
            }
            for (WS_I = 1; WS_BV_INFO[WS_I-1].WS_NUM != 0 
                && WS_I <= 4; WS_I += 1) {
                BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_CODE = WS_BV_CODE;
                BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_NAME = WS_BV_NAME;
                BPB3030_AWA_3030.BV_DATA[WS_I-1].HEAD_NO = WS_BV_INFO[WS_I-1].WS_HEAD_NO;
                BPB3030_AWA_3030.BV_DATA[WS_I-1].BEG_NO = WS_BV_INFO[WS_I-1].WS_BEG_NO;
                BPB3030_AWA_3030.BV_DATA[WS_I-1].END_NO = WS_BV_INFO[WS_I-1].WS_END_NO;
                BPB3030_AWA_3030.BV_DATA[WS_I-1].NUM = WS_BV_INFO[WS_I-1].WS_NUM;
            }
            T000_READNEXT_BPTADTL();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTADTL();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            B020_BV_MOVE_IN_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_BV_MOVE_IN();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3030_AWA_3030.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_J = 1; WS_J <= 4 
            && BPB3030_AWA_3030.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3030_AWA_3030.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_COMP_BEGNO == 0 
                    || WS_COMP_ENDNO == 0 
                    || BPB3030_AWA_3030.BV_DATA[WS_J-1].NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].NUM_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (WS_APP_NO != 0) {
            R000_CHECK_APP_INFO();
            if (pgmRtn) return;
        }
        if (BPB3030_AWA_3030.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_J = 1; WS_J <= 10 
            && BPB3030_AWA_3030.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3030_AWA_3030.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCSNOCK);
                BPCSNOCK.BV_CODE = BPB3030_AWA_3030.BV_DATA[WS_J-1].BV_CODE;
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO += " ";
                BPCSNOCK.BEG_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN);
                if (BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO == null) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO += " ";
                BPCSNOCK.END_NO = BPB3030_AWA_3030.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN);
                BPCSNOCK.FUNC = '1';
                S000_CALL_BPZSNOCK();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPRADTL);
        BPRAPLI.KEY.APP_NO = WS_APP_NO;
        BPRADTL.KEY.APP_NO = WS_APP_NO;
        CEP.TRC(SCCGWA, WS_APP_NO);
        CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        T000_READ_BPTAPLI();
        if (pgmRtn) return;
        WS_APP_TYPE = BPRAPLI.APP_TYPE;
        CEP.TRC(SCCGWA, WS_APP_TYPE);
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPRAPLI.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR41);
            }
            if (BPRAPLI.APP_STS != '5') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR46);
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR38);
                }
                if (BPRAPLI.APP_STS != '4') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR48);
                }
                if (BPRAPLI.ACP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR114);
                }
                if (BPRAPLI.ADT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR115);
                }
            }
        }
        if (BPRAPLI.CONF_NO != BPB3030_AWA_3030.CONF_NO) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR49);
        }
    }
    public void T000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "APP_NO = :BPRAPLI.KEY.APP_NO";
        BPTAPLI_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_READ1_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "CONF_NO = :BPRAPLI.CONF_NO";
        BPTAPLI_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTAPLI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTADTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
        BPTADTL_BR.rp = new DBParm();
        BPTADTL_BR.rp.TableName = "BPTADTL";
        BPTADTL_BR.rp.where = "APP_NO = :BPRADTL.KEY.APP_NO";
        IBS.STARTBR(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTADTL_1() throws IOException,SQLException,Exception {
        BPTADTL_BR.rp = new DBParm();
        BPTADTL_BR.rp.TableName = "BPTADTL";
        BPTADTL_BR.rp.where = "APP_NO = :BPRADTL.KEY.APP_NO";
        IBS.STARTBR(SCCGWA, BPRADTL, this, BPTADTL_BR);
    }
    public void T000_READNEXT_BPTADTL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTADTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void B020_BV_MOVE_IN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.MOV_DT);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.CONF_NO);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.BR);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.TLR);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.BV_STS);
        CEP.TRC(SCCGWA, WS_APP_NO);
        IBS.init(SCCGWA, BPCSBVMI);
        BPCSBVMI.MOV_DT = BPB3030_AWA_3030.MOV_DT;
        BPCSBVMI.CONF_NO = BPB3030_AWA_3030.CONF_NO;
        BPCSBVMI.OUT_BR = BPB3030_AWA_3030.BR;
        BPCSBVMI.OUT_TLR = BPB3030_AWA_3030.TLR;
        BPCSBVMI.BV_STS = BPB3030_AWA_3030.BV_STS;
        BPCSBVMI.CNT = K_CNT;
        BPCSBVMI.APP_NO = WS_APP_NO;
        CEP.TRC(SCCGWA, BPCSBVMI.APP_NO);
        BPCSBVMI.APP_TYPE = WS_APP_TYPE;
        for (WS_I = 1; WS_I <= K_CNT; WS_I += 1) {
            BPCSBVMI.BV_DATA[WS_I-1].BV_CODE = BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVMI.BV_DATA[WS_I-1].BV_NAME = BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO = BPB3030_AWA_3030.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVMI.BV_DATA[WS_I-1].BEG_NO = BPB3030_AWA_3030.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVMI.BV_DATA[WS_I-1].END_NO = BPB3030_AWA_3030.BV_DATA[WS_I-1].END_NO;
            BPCSBVMI.BV_DATA[WS_I-1].NUM = BPB3030_AWA_3030.BV_DATA[WS_I-1].NUM;
        }
        S000_CALL_BPZSBVMI();
        if (pgmRtn) return;
    }
    public void B020_BV_MOVE_IN_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.MOV_DT);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.CONF_NO);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.BR);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.TLR);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.BV_STS);
        IBS.init(SCCGWA, BPCSBVMI);
        if (WS_APP_NO == ' ' 
            || WS_APP_NO == 0) {
            BPCSBVMI.MOV_DT = BPB3030_AWA_3030.MOV_DT;
        } else {
            BPCSBVMI.MOV_DT = BPRAPLI.OUT_DT;
        }
        BPCSBVMI.CONF_NO = BPB3030_AWA_3030.CONF_NO;
        BPCSBVMI.OUT_BR = BPB3030_AWA_3030.BR;
        BPCSBVMI.OUT_TLR = BPB3030_AWA_3030.TLR;
        BPCSBVMI.BV_STS = BPB3030_AWA_3030.BV_STS;
        CEP.TRC(SCCGWA, BPCSBVMI.BV_STS);
        BPCSBVMI.CNT = K_CNT;
        BPCSBVMI.PB_FLG = '1';
        BPCSBVMI.APP_NO = WS_APP_NO;
        CEP.TRC(SCCGWA, BPCSBVMI.APP_NO);
        BPCSBVMI.APP_TYPE = WS_APP_TYPE;
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPB3030_AWA_3030.BV_DATA[2-1].BV_CODE);
        for (WS_I = 1; WS_I <= K_CNT 
            && BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            BPCSBVMI.BV_DATA[WS_I-1].BV_CODE = BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVMI.BV_DATA[WS_I-1].BV_NAME = BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO = BPB3030_AWA_3030.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVMI.BV_DATA[WS_I-1].BEG_NO = BPB3030_AWA_3030.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVMI.BV_DATA[WS_I-1].END_NO = BPB3030_AWA_3030.BV_DATA[WS_I-1].END_NO;
            BPCSBVMI.BV_DATA[WS_I-1].NUM = BPB3030_AWA_3030.BV_DATA[WS_I-1].NUM;
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].BEG_NO);
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].END_NO);
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].NUM);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB3030_AWA_3030.BV_DATA[WS_I-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].BV_CODE);
        }
        BPCSBVMI.FEE_ACNO = BPB3030_AWA_3030.ACNO;
        CEP.TRC(SCCGWA, WS_CNT);
        BPCSBVMI.CNT1 = WS_CNT;
        BPCSBVMI.BRFLG = 'N';
        S000_CALL_BPZSBVMI();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_MOV_IN, BPCSBVMI);
        if (BPCSBVMI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVMI.RC);
            WS_FLD_NO = BPB3030_AWA_3030.MOV_DT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
