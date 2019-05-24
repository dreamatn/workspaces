package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3020 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTVHPB_RD;
    DBParm BPTTBVD_RD;
    int JIBS_tmp_int;
    DBParm BPTAPLI_RD;
    brParm BPTADTL_BR = new brParm();
    boolean pgmRtn = false;
    String S_BV_MOV_OUT = "BP-S-BV-MOV-OUT  ";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    int CNT = 10;
    String R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String P_INQ_ORG = "BP-P-INQ-ORG        ";
    String P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    String S_NUM_CHK = "BP-S-BV-NO-CHK";
    BPOT3020_WS_VARIABLES WS_VARIABLES = new BPOT3020_WS_VARIABLES();
    BPOT3020_WS_CONF_INFO WS_CONF_INFO = new BPOT3020_WS_CONF_INFO();
    int WS_A = 0;
    int WS_CNT1 = 0;
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTBV BPRTBV = new BPRTBV();
    BPCSBVMO BPCSBVMO = new BPCSBVMO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCRTBV BPCRTBV = new BPCRTBV();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPRADTL BPRADTL = new BPRADTL();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPB3020_AWA_3020 AWA_3020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "XGQ");
        CEP.TRC(SCCGWA, AWA_3020.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, AWA_3020.BV_DATA[1-1].BV_NAME);
        CEP.TRC(SCCGWA, AWA_3020.BV_DATA[1-1].HEAD_NO);
        CEP.TRC(SCCGWA, AWA_3020.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, AWA_3020.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, AWA_3020.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, AWA_3020.APP_NO_G);
        IBS.CPY2CLS(SCCGWA, AWA_3020.APP_NO_G, WS_VARIABLES.WS_APP_INFO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_APP_INFO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 2 
            && (JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0")); WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        }
        IBS.init(SCCGWA, WS_CONF_INFO);
        CEP.TRC(SCCGWA, WS_CNT1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1]);
        for (WS_A = 1; WS_A <= 200 
            && ((JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0")) 
            || WS_A <= 1); WS_A += 1) {
            CEP.TRC(SCCGWA, "TST00000");
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1]);
            if (JIBS_tmp_str[0].trim().length() == 0) {
                WS_VARIABLES.APP_NO = 0;
            } else {
                WS_VARIABLES.APP_NO = WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1].APP_N;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1]);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0") 
                && JIBS_tmp_str[0].trim().length() > 0) {
                A000_REDF_AWA();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1].APP_N);
            CEP.TRC(SCCGWA, WS_VARIABLES.APP_NO);
            B000_MAIN_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, WS_VARIABLES);
            IBS.CPY2CLS(SCCGWA, AWA_3020.APP_NO_G, WS_VARIABLES.WS_APP_INFO);
        }
        AWA_3020.CONF_NOG = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO);
        CEP.TRC(SCCGWA, AWA_3020.CONF_NOG);
        CEP.TRC(SCCGWA, "BPOT3020 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_3020 = new BPB3020_AWA_3020();
        IBS.init(SCCGWA, AWA_3020);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_3020);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPRADTL);
        BPRAPLI.KEY.APP_NO = WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1].APP_N;
        BPRADTL.KEY.APP_NO = WS_VARIABLES.WS_APP_INFO.WS_APP_G[WS_A-1].APP_N;
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
        CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        T000_READ_BPTAPLI();
        if (pgmRtn) return;
        T000_STARTBR_BPTADTL_1();
        if (pgmRtn) return;
        T000_READNEXT_BPTADTL();
        if (pgmRtn) return;
        for (WS_VARIABLES.J = 1; WS_VARIABLES.J <= 4; WS_VARIABLES.J += 1) {
            WS_VARIABLES.BV_CODE = " ";
            WS_VARIABLES.BV_NAME = " ";
            WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.J-1].HEAD_NO = " ";
            WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.J-1].BEG_NO = " ";
            WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.J-1].END_NO = " ";
            WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.J-1].NUM = 0;
        }
        for (WS_VARIABLES.J = 1; WS_VARIABLES.TBL_FLAG != 'N'; WS_VARIABLES.J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPRADTL.KEY.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
            if (BPRAPLI.APP_TYPE == '0') {
                AWA_3020.BR = BPRAPLI.APP_BR;
                AWA_3020.BV_STS = '0';
            } else {
                if (BPRAPLI.APP_TYPE == '1') {
                    AWA_3020.BR = BPRAPLI.UP_BR;
                    IBS.init(SCCGWA, BPRVHPB);
                    BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPRVHPB.POLL_BOX_IND = '1';
                    T000_READ_BPTVHPB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    IBS.init(SCCGWA, BPRTBVD);
                    BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPRTBVD.KEY.PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                    BPRTBVD.KEY.BV_CODE = BPRADTL.KEY.BV_CODE;
                    BPRTBVD.KEY.VALUE = 0;
                    BPRTBVD.KEY.HEAD_NO = " ";
                    BPRTBVD.BEG_NO = BPRADTL.BEG_NO1;
                    BPRTBVD.KEY.END_NO = BPRADTL.END_NO1;
                    CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
                    CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                    T000_READ_BPTTBVD1();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
                    if (BPRTBVD.KEY.STS != BPRADTL.STS) {
                        CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR182, BPRADTL.KEY.APP_NO);
                    }
                    AWA_3020.BV_STS = BPRADTL.STS;
                }
            }
            WS_VARIABLES.BV_CODE = BPRADTL.KEY.BV_CODE;
            WS_VARIABLES.BV_NAME = BPCFBVQU.TX_DATA.BV_CNM;
            WS_VARIABLES.WS_BV_INFO[1-1].HEAD_NO = BPRADTL.HEAD_NO1;
            WS_VARIABLES.WS_BV_INFO[1-1].BEG_NO = BPRADTL.BEG_NO1;
            WS_VARIABLES.WS_BV_INFO[1-1].END_NO = BPRADTL.END_NO1;
            WS_VARIABLES.WS_BV_INFO[1-1].NUM = BPRADTL.NUM1;
            WS_VARIABLES.WS_BV_INFO[2-1].HEAD_NO = BPRADTL.HEAD_NO2;
            WS_VARIABLES.WS_BV_INFO[2-1].BEG_NO = BPRADTL.BEG_NO2;
            WS_VARIABLES.WS_BV_INFO[2-1].END_NO = BPRADTL.END_NO2;
            WS_VARIABLES.WS_BV_INFO[2-1].NUM = BPRADTL.NUM2;
            WS_VARIABLES.WS_BV_INFO[3-1].HEAD_NO = BPRADTL.HEAD_NO3;
            WS_VARIABLES.WS_BV_INFO[3-1].BEG_NO = BPRADTL.BEG_NO3;
            WS_VARIABLES.WS_BV_INFO[3-1].END_NO = BPRADTL.END_NO3;
            WS_VARIABLES.WS_BV_INFO[3-1].NUM = BPRADTL.NUM3;
            WS_VARIABLES.WS_BV_INFO[4-1].HEAD_NO = BPRADTL.HEAD_NO4;
            WS_VARIABLES.WS_BV_INFO[4-1].BEG_NO = BPRADTL.BEG_NO4;
            WS_VARIABLES.WS_BV_INFO[4-1].END_NO = BPRADTL.END_NO4;
            WS_VARIABLES.WS_BV_INFO[4-1].NUM = BPRADTL.NUM4;
            CEP.TRC(SCCGWA, WS_VARIABLES.BV_CODE);
            CEP.TRC(SCCGWA, WS_VARIABLES.BV_NAME);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[1-1].HEAD_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[1-1].BEG_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[1-1].END_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[1-1].NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[2-1].HEAD_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[2-1].BEG_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[2-1].END_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[2-1].NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[3-1].HEAD_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[3-1].BEG_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[3-1].END_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[3-1].NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[4-1].HEAD_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[4-1].BEG_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[4-1].END_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_BV_INFO[4-1].NUM);
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 4; WS_VARIABLES.I += 1) {
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_CODE = " ";
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_NAME = " ";
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].HEAD_NO = " ";
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BEG_NO = " ";
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].END_NO = " ";
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].NUM = 0;
            }
            for (WS_VARIABLES.I = 1; WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].NUM != 0 
                && WS_VARIABLES.I <= 4; WS_VARIABLES.I += 1) {
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_CODE = WS_VARIABLES.BV_CODE;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_NAME = WS_VARIABLES.BV_NAME;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].HEAD_NO = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].HEAD_NO;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BEG_NO = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].BEG_NO;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].END_NO = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].END_NO;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].NUM = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].NUM;
                CEP.TRC(SCCGWA, "TEST0099");
                CEP.TRC(SCCGWA, WS_VARIABLES.I);
                CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_CODE);
                CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_NAME);
                CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.I-1].HEAD_NO);
                CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.I-1].BEG_NO);
                CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.I-1].END_NO);
                CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.I-1].NUM);
            }
            T000_READNEXT_BPTADTL();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTADTL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        BPTVHPB_RD.where = "BR = :BPRVHPB.BR "
            + "AND CUR_TLR = :BPRVHPB.CUR_TLR "
            + "AND POLL_BOX_IND = :BPRVHPB.POLL_BOX_IND";
        IBS.READ(SCCGWA, BPRVHPB, this, BPTVHPB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.BP_TLR_HVNT_BVP);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVHPB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTBVD1() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND HEAD_NO = :BPRTBVD.KEY.HEAD_NO "
            + "AND END_NO >= :BPRTBVD.BEG_NO "
            + "AND BEG_NO <= :BPRTBVD.KEY.END_NO "
            + "AND BEG_NO <= END_NO";
        IBS.READ(SCCGWA, BPRTBVD, this, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR179, BPRADTL.KEY.APP_NO);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTBVD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            CEP.TRC(SCCGWA, "TEST000");
            B010_CHECK_INPUT_CH();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "1234");
        B020_BV_MOVE_OUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AWA_3020.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_VARIABLES.J = 1; WS_VARIABLES.J <= 4 
            && AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE.trim().length() != 0; WS_VARIABLES.J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.trim().length() > 0) {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO += " ";
                for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                    && IBS.isNumeric(AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.trim().length() > 0) {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO += " ";
                for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                    && IBS.isNumeric(AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_VARIABLES.BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].HEAD_NO.trim().length() > 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_HEADNO;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.trim().length() > 0 
                    || AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.trim().length() > 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO += " ";
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_BEGNO = 0;
                else WS_VARIABLES.COMP_BEGNO = Long.parseLong(AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN));
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO += " ";
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_ENDNO = 0;
                else WS_VARIABLES.COMP_ENDNO = Long.parseLong(AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(0, WS_VARIABLES.BVNO_LEN));
                if (WS_VARIABLES.COMP_BEGNO > WS_VARIABLES.COMP_ENDNO) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_VARIABLES.COMP_BEGNO == 0 
                    || WS_VARIABLES.COMP_ENDNO == 0 
                    || AWA_3020.BV_DATA[WS_VARIABLES.J-1].NUM != WS_VARIABLES.COMP_ENDNO - WS_VARIABLES.COMP_BEGNO + 1) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END_NUM;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].NUM_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = AWA_3020.TLR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_BVLT_TLR;
            WS_VARIABLES.FLD_NO = AWA_3020.TLR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = AWA_3020.BR;
        BPRVHPB.CUR_TLR = AWA_3020.TLR;
        BPRVHPB.POLL_BOX_IND = '1';
        BPRVHPB.STS = 'N';
        CEP.TRC(SCCGWA, BPRVHPB.BR);
        CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.STS);
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        if (BPCRVHPB.RETURN_INFO == 'F') {
        } else {
            CEP.TRC(SCCGWA, BPRVHPB.BR);
            CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.STS);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRVHPB.INFO.FUNC = 'E';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_CH() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.APP_NO != 0) {
            R000_CHECK_APP_INFO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPRGST);
        if (AWA_3020.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0") 
            || AWA_3020.BV_DATA[1-1].BV_CODE.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_VARIABLES.J = 1; WS_VARIABLES.J <= 10 
            && AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE.trim().length() != 0; WS_VARIABLES.J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.trim().length() > 0) {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO += " ";
                for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                    && IBS.isNumeric(AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.trim().length() > 0) {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO += " ";
                for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
                    && IBS.isNumeric(AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1)); WS_VARIABLES.I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_VARIABLES.I - 1) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_VARIABLES.BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].HEAD_NO.trim().length() > 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_HEADNO;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.trim().length() > 0 
                    || AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.trim().length() > 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO += " ";
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_BEGNO = 0;
                else WS_VARIABLES.COMP_BEGNO = Long.parseLong(AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN));
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO += " ";
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(0, WS_VARIABLES.BVNO_LEN).trim().length() == 0) WS_VARIABLES.COMP_ENDNO = 0;
                else WS_VARIABLES.COMP_ENDNO = Long.parseLong(AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(0, WS_VARIABLES.BVNO_LEN));
                if (WS_VARIABLES.COMP_BEGNO > WS_VARIABLES.COMP_ENDNO) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END;
                    WS_VARIABLES.FLD_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCSNOCK);
                BPCSNOCK.BV_CODE = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE;
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO += " ";
                BPCSNOCK.BEG_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].BEG_NO.substring(0, WS_VARIABLES.BVNO_LEN);
                if (AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO == null) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO = "";
                JIBS_tmp_int = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO += " ";
                BPCSNOCK.END_NO = AWA_3020.BV_DATA[WS_VARIABLES.J-1].END_NO.substring(0, WS_VARIABLES.BVNO_LEN);
                BPCSNOCK.FUNC = '1';
                S000_CALL_BPZSNOCK();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "TEST001");
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = "09";
        BPCPQORR.BR = AWA_3020.BR;
        CEP.TRC(SCCGWA, "12345");
        CEP.TRC(SCCGWA, BPCPQORR.TYP);
        CEP.TRC(SCCGWA, BPCPQORR.BR);
        S000_CALL_BPZPQORR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPCPQORR.REL_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            || BPCPQORR.RC.RC_CODE == BP1505) {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = "09";
            BPCPQORR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
            CEP.TRC(SCCGWA, AWA_3020.BR);
            if (BPCPQORR.REL_BR == AWA_3020.BR) {
                WS_VARIABLES.SL_BVOW_FLG = 'Y';
                if (BPCPQORR.ONWAY_DAY.trim().length() == 0) WS_VARIABLES.ONWAY_DT = 0;
                else WS_VARIABLES.ONWAY_DT = Integer.parseInt(BPCPQORR.ONWAY_DAY);
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BR_NOT_MOV_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_VARIABLES.SL_BVOW_FLG = 'Y';
            if (BPCPQORR.ONWAY_DAY.trim().length() == 0) WS_VARIABLES.ONWAY_DT = 0;
            else WS_VARIABLES.ONWAY_DT = Integer.parseInt(BPCPQORR.ONWAY_DAY);
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.ONWAY_DT);
        if (WS_VARIABLES.SL_BVOW_FLG == 'Y') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AWA_3020.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BR_NOTFND;
                WS_VARIABLES.FLD_NO = AWA_3020.BR_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCPQORG.ORG_STS != 'O') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_ORG_SIGN_OFF;
                WS_VARIABLES.FLD_NO = AWA_3020.BR_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.where = "BR = :BPRTBVD.KEY.BR "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND END_NO >= :BPRTBVD.BEG_NO "
            + "AND BEG_NO <= :BPRTBVD.KEY.END_NO "
            + "AND BEG_NO <= END_NO";
        IBS.READ(SCCGWA, BPRTBVD, this, BPTTBVD_RD);
    }
    public void R000_CHECK_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPRADTL);
        CEP.TRC(SCCGWA, WS_VARIABLES.APP_NO);
        CEP.TRC(SCCGWA, "ABCDE");
        BPRAPLI.KEY.APP_NO = WS_VARIABLES.APP_NO;
        BPRADTL.KEY.APP_NO = WS_VARIABLES.APP_NO;
        CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        T000_READ_BPTAPLI();
        if (pgmRtn) return;
        WS_VARIABLES.APP_TYPE = BPRAPLI.APP_TYPE;
        CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
        if (BPRAPLI.APP_TYPE == '0') {
            if (BPRAPLI.UP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR38);
            }
            if (BPRAPLI.APP_STS != '4') {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR39);
            }
        } else {
            if (BPRAPLI.APP_TYPE == '1') {
                if (BPRAPLI.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR41);
                }
                if (BPRAPLI.APP_STS != '0') {
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR42);
                }
            }
        }
        T000_STARTBR_BPTADTL();
        if (pgmRtn) return;
        T000_READNEXT_BPTADTL();
        if (pgmRtn) return;
        for (WS_VARIABLES.J = 1; WS_VARIABLES.J <= 10 
            && BPRADTL.KEY.BV_CODE.trim().length() != 0 
            && !BPRADTL.KEY.BV_CODE.equalsIgnoreCase("0") 
            && BPRADTL.KEY.BV_CODE.charAt(0) != 0X00 
            && WS_VARIABLES.TBL_FLAG != 'N'; WS_VARIABLES.J += 1) {
            CEP.TRC(SCCGWA, AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE);
            CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
            if (!AWA_3020.BV_DATA[WS_VARIABLES.J-1].BV_CODE.equalsIgnoreCase(BPRADTL.KEY.BV_CODE)) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR);
            }
            T000_READNEXT_BPTADTL();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTADTL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "APP_NO = :BPRAPLI.KEY.APP_NO";
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_STARTBR_BPTADTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
        BPTADTL_BR.rp = new DBParm();
        BPTADTL_BR.rp.TableName = "BPTADTL";
        BPTADTL_BR.rp.where = "APP_NO = :BPRADTL.KEY.APP_NO "
            + "AND BV_CODE = :BPRADTL.KEY.BV_CODE";
        IBS.STARTBR(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.TBL_FLAG = 'N';
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
            WS_VARIABLES.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTADTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.TBL_FLAG = 'N';
        } else {
        }
    }
    public void B020_BV_MOVE_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVMO);
        BPCSBVMO.IN_BR = AWA_3020.BR;
        BPCSBVMO.IN_TLR = AWA_3020.TLR;
        BPCSBVMO.BV_STS = AWA_3020.BV_STS;
        BPCSBVMO.CNT = CNT;
        BPCSBVMO.PB_FLG = AWA_3020.BV_FUNC;
        BPCSBVMO.BR_FLG = 'N';
        BPCSBVMO.APP_NO = WS_VARIABLES.APP_NO;
        BPCSBVMO.ONWAY_DT = WS_VARIABLES.ONWAY_DT;
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        BPCSBVMO.VIL_TYP = BPCPQORG.VIL_TYP;
        CEP.TRC(SCCGWA, AWA_3020.BV_STS);
        CEP.TRC(SCCGWA, BPCSBVMO.BV_STS);
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= CNT; WS_VARIABLES.I += 1) {
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BV_CODE = AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_CODE;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BV_NAME = AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_NAME;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].HEAD_NO = AWA_3020.BV_DATA[WS_VARIABLES.I-1].HEAD_NO;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BEG_NO = AWA_3020.BV_DATA[WS_VARIABLES.I-1].BEG_NO;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].END_NO = AWA_3020.BV_DATA[WS_VARIABLES.I-1].END_NO;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].NUM = AWA_3020.BV_DATA[WS_VARIABLES.I-1].NUM;
            CEP.TRC(SCCGWA, "BVINFO");
            CEP.TRC(SCCGWA, BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BV_NAME);
            CEP.TRC(SCCGWA, BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].HEAD_NO);
            CEP.TRC(SCCGWA, BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BEG_NO);
            CEP.TRC(SCCGWA, BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].END_NO);
            CEP.TRC(SCCGWA, BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].NUM);
        }
        BPCSBVMO.CNT1 = WS_CNT1;
        S000_CALL_BPZSBVMO();
        if (pgmRtn) return;
        AWA_3020.MOV_DT = BPCSBVMO.MOV_DT;
        WS_CONF_INFO.WS_CONF_G[WS_A-1].CONF_N = BPCSBVMO.CONF_NO;
        CEP.TRC(SCCGWA, AWA_3020.MOV_DT);
        CEP.TRC(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_A-1].CONF_N);
        AWA_3020.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AWA_3020.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_BV_MOV_OUT, BPCSBVMO);
        if (BPCSBVMO.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVMO.RC);
            WS_VARIABLES.FLD_NO = AWA_3020.MOV_DT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 206;
        IBS.CALLCPN(SCCGWA, R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_INQ_ORG_REL, BPCPQORR);
        if (BPCPQORR.RC.RC_CODE != 0 
            && BPCPQORR.RC.RC_CODE == BP1505) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
