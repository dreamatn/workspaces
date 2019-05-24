package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3029 {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTAPLI_RD;
    brParm BPTADTL_BR = new brParm();
    String S_BV_MOV_OUT = "BP-S-BV-MOV-OUT  ";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    int CNT = 10;
    BPOT3029_WS_VARIABLES WS_VARIABLES = new BPOT3029_WS_VARIABLES();
    BPOT3029_WS_CONF_INFO WS_CONF_INFO = new BPOT3029_WS_CONF_INFO();
    int WS_A = 0;
    int WS_CNT1 = 0;
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVMO BPCSBVMO = new BPCSBVMO();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPRADTL BPRADTL = new BPRADTL();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB3020_AWA_3020 AWA_3020;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        IBS.CPY2CLS(SCCGWA, AWA_3020.APP_NO_G, WS_VARIABLES.WS_APP_INFO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 2 
            && (JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0")); WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_CNT1-1]);
            CEP.TRC(SCCGWA, "TEST999");
        }
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, "TEST888");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_A-1]);
        for (WS_A = 1; WS_A <= 200 
            && (JIBS_tmp_str[0].trim().length() != 0 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0")); WS_A += 1) {
            CEP.TRC(SCCGWA, "TST00000");
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, WS_CONF_INFO.WS_CONF_G[WS_A-1].CONF_N);
            WS_VARIABLES.CONF_NO = WS_CONF_INFO.WS_CONF_G[WS_A-1].CONF_N;
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, WS_VARIABLES.CONF_NO);
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
            }
            B000_MAIN_PROC();
            IBS.init(SCCGWA, WS_VARIABLES);
            IBS.CPY2CLS(SCCGWA, AWA_3020.CONF_NOG, WS_CONF_INFO);
        }
        CEP.TRC(SCCGWA, "BPOT3029 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_3020 = new BPB3020_AWA_3020();
        IBS.init(SCCGWA, AWA_3020);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_3020);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPCSBVMO);
        IBS.CPY2CLS(SCCGWA, AWA_3020.CONF_NOG, WS_CONF_INFO);
        CEP.TRC(SCCGWA, "TEST3333");
        CEP.TRC(SCCGWA, WS_CONF_INFO);
    }
    public void A000_REDF_AWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPRADTL);
        CEP.TRC(SCCGWA, "TEST777");
        CEP.TRC(SCCGWA, WS_VARIABLES.CONF_NO);
        BPRAPLI.CONF_NO = WS_VARIABLES.CONF_NO;
        T000_READ_BPTAPLI();
        CEP.TRC(SCCGWA, BPRAPLI.KEY.APP_NO);
        BPRADTL.KEY.APP_NO = BPRAPLI.KEY.APP_NO;
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        T000_STARTBR_BPTADTL();
        T000_READNEXT_BPTADTL();
        for (WS_VARIABLES.J = 1; WS_VARIABLES.TBL_FLAG != 'N'; WS_VARIABLES.J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPRADTL.KEY.BV_CODE;
            S000_CALL_BPZFBVQU();
            CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
            if (BPRAPLI.APP_TYPE == '0') {
                AWA_3020.BR = BPRAPLI.APP_BR;
                AWA_3020.BV_STS = '0';
            } else {
                if (BPRAPLI.APP_TYPE == '1') {
                    AWA_3020.BR = BPRAPLI.UP_BR;
                    AWA_3020.BV_STS = BPRADTL.STS;
                }
            }
            AWA_3020.MOV_DT = BPRAPLI.OUT_DT;
            AWA_3020.CONF_NO = BPRAPLI.CONF_NO;
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
            for (WS_VARIABLES.I = 1; WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].NUM != 0 
                && WS_VARIABLES.I <= 4; WS_VARIABLES.I += 1) {
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_CODE = WS_VARIABLES.BV_CODE;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_NAME = WS_VARIABLES.BV_NAME;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].HEAD_NO = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].HEAD_NO;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].BEG_NO = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].BEG_NO;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].END_NO = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].END_NO;
                AWA_3020.BV_DATA[WS_VARIABLES.I-1].NUM = WS_VARIABLES.WS_BV_INFO[WS_VARIABLES.I-1].NUM;
            }
            T000_READNEXT_BPTADTL();
        }
        T000_ENDBR_BPTADTL();
    }
    public void T000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "CONF_NO = :BPRAPLI.CONF_NO";
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void T000_STARTBR_BPTADTL() throws IOException,SQLException,Exception {
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
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_DRAW_CASH_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!AWA_3020.TR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (AWA_3020.TR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DRAW_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVMO);
        BPCSBVMO.IN_BR = AWA_3020.BR;
        BPCSBVMO.IN_TLR = AWA_3020.TLR;
        BPCSBVMO.BV_STS = AWA_3020.BV_STS;
        BPCSBVMO.CNT = CNT;
        BPCSBVMO.PB_FLG = AWA_3020.BV_FUNC;
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= CNT; WS_VARIABLES.I += 1) {
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BV_CODE = AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_CODE;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BV_NAME = AWA_3020.BV_DATA[WS_VARIABLES.I-1].BV_NAME;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].HEAD_NO = AWA_3020.BV_DATA[WS_VARIABLES.I-1].HEAD_NO;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].BEG_NO = AWA_3020.BV_DATA[WS_VARIABLES.I-1].BEG_NO;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].END_NO = AWA_3020.BV_DATA[WS_VARIABLES.I-1].END_NO;
            BPCSBVMO.BV_DATA[WS_VARIABLES.I-1].NUM = AWA_3020.BV_DATA[WS_VARIABLES.I-1].NUM;
        }
        CEP.TRC(SCCGWA, AWA_3020.CONF_NOG);
        BPCSBVMO.MOV_DT = AWA_3020.MOV_DT;
        BPCSBVMO.CONF_NO = WS_VARIABLES.CONF_NO;
        CEP.TRC(SCCGWA, AWA_3020.MOV_DT);
        CEP.TRC(SCCGWA, AWA_3020.CONF_NO);
        CEP.TRC(SCCGWA, BPCSBVMO.CONF_NO);
        S000_CALL_BPZSBVMO();
    }
    public void S000_CALL_BPZSBVMO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = S_BV_MOV_OUT;
        SCCCALL.COMMAREA_PTR = BPCSBVMO;
        SCCCALL.ERR_FLDNO = AWA_3020.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
