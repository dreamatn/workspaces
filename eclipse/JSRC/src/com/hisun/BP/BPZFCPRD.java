package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCPRD {
    int JIBS_tmp_int;
    String K_PGM_NAME = "BPZFCPRD";
    String CPN_GET_FEE_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String CPN_GET_TOT_INFO = "BP-F_F_GET_TOT_INFO ";
    String WS_TEMP_RECORD = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TX_AMT = 0;
    String WS_TX_PARM_CCY = " ";
    int WS_TX_CNT = 0;
    short WS_CNT1 = 0;
    short WS_LOC = 0;
    String WS_TXT = " ";
    String WS_QUEUE = " ";
    long WS_RESP = 0;
    String WS_TXT_AMT = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFX BPCFX = new BPCFX();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTOT BPCFFTOT = new BPCFFTOT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFSTD BPVFSTD = new BPVFSTD();
    SCCGWA SCCGWA;
    BPCFFCAL BPCFFCAL;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCUIFEE BPCUIFEE;
    public void MP(SCCGWA SCCGWA, BPCFFCAL BPCFFCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFCAL = BPCFFCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFCPRD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCUIFEE = (BPCUIFEE) BPCFFCAL.DATA.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCFFCAL.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFFCAL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        B020_PROC_AMT_DATA();
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIFEE.CHG_AC);
        CEP.TRC(SCCGWA, BPCUIFEE.CHG_CCY);
        CEP.TRC(SCCGWA, BPCUIFEE.CHG_AMT);
        if (BPCUIFEE.CHG_AC.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUIFEE.CHG_CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_CCY;
            S000_ERR_MSG_PROC();
        }
        if (BPCUIFEE.CHG_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROC_AMT_DATA() throws IOException,SQLException,Exception {
        BPCFFCAL.DATA.TX_AC = BPCUIFEE.CHG_AC;
        BPCFFCAL.DATA.CHG_CCY = BPCUIFEE.CHG_CCY;
        BPCFFCAL.DATA.CHG_AMT = BPCUIFEE.CHG_AMT;
    }
    public void B010_GET_FEE_STD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
        IBS.init(SCCGWA, BPVFSTD);
        BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
        BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
        BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
        BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
        BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
        R000_GET_STD_PARM();
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            CEP.TRC(SCCGWA, BPCFFCAL.DATA.FREE_FMT);
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSTD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            BPCFFCAL.DATA.FEE_CCY = BPVFSTD.VAL.FEE_CCY;
            BPCFFCAL.DATA.FEE_AMT = 0;
            BPCFFCAL.DATA.CHG_AMT = 0;
            BPCFFCAL.DATA.EXG_DATE = 0;
            BPCFFCAL.DATA.EXG_TIME = 0;
        }
    }
    public void B020_CHECK_DATA() throws IOException,SQLException,Exception {
        if (BPVFSTD.VAL.CAL_TYPE == '0') {
            if (BPCFFCAL.DATA.TX_AMT <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMT_NOTINP;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPVFSTD.VAL.CAL_TYPE == '1') {
            if (BPCFFCAL.DATA.TX_CNT <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCNT_NOTINP;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_PERPARE_CAL_DATA() throws IOException,SQLException,Exception {
        if (BPVFSTD.VAL.CAL_SOURCE == '0') {
            IBS.init(SCCGWA, BPCFFTOT);
            BPCFFTOT.DATA.SUR_CYC = BPVFSTD.VAL.CAL_CYC;
            BPCFFTOT.DATA.CYC_CNT = BPVFSTD.VAL.CYC_NUM;
            BPCFFTOT.DATA.CLASSFY_TYPE = BPVFSTD.VAL.CAL_TYPE;
            BPCFFTOT.DATA.AMT_SUR = BPVFSTD.VAL.CAL_SOURCE;
            BPCFFTOT.DATA.MST_SUR = BPVFSTD.VAL.AGR_TYPE;
            BPCFFTOT.DATA.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            JIBS_tmp_int = BPCFFTOT.DATA.TX_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) BPCFFTOT.DATA.TX_CODE = "0" + BPCFFTOT.DATA.TX_CODE;
            S000_CALL_BPZFFTOT();
            WS_TX_AMT = BPCFFTOT.DATA.TOT_AMT;
            WS_TX_CNT = BPCFFTOT.DATA.TOT_CNT;
        } else {
            WS_TX_AMT = BPCFFCAL.DATA.TX_AMT;
            WS_TX_CNT = BPCFFCAL.DATA.TX_CNT;
        }
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CNT_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if ((!BPCFFCAL.DATA.CNT_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) 
            && BPVFSTD.VAL.CAL_TYPE == '0') {
            T000_EXCHANGE_CURRENCY();
            CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
            CEP.TRC(SCCGWA, WS_TX_AMT);
        }
    }
    public void B040_CAL_FEE() throws IOException,SQLException,Exception {
        if (BPVFSTD.VAL.CAL_TYPE == '0'
            && BPVFSTD.VAL.PRICE_MTH == '0'
            && BPVFSTD.VAL.AGGR_TYPE == '0') {
            B041_CAL_FEE();
        } else if (BPVFSTD.VAL.CAL_TYPE == '0'
            && BPVFSTD.VAL.PRICE_MTH == '0'
            && BPVFSTD.VAL.AGGR_TYPE == '1') {
            B042_CAL_FEE();
        } else if (BPVFSTD.VAL.CAL_TYPE == '0'
            && BPVFSTD.VAL.PRICE_MTH == '1'
            && BPVFSTD.VAL.AGGR_TYPE == '0') {
            B043_CAL_FEE();
        } else if (BPVFSTD.VAL.CAL_TYPE == '0'
            && BPVFSTD.VAL.PRICE_MTH == '1'
            && BPVFSTD.VAL.AGGR_TYPE == '1') {
            B044_CAL_FEE();
        } else if (BPVFSTD.VAL.CAL_TYPE == '1'
            && BPVFSTD.VAL.PRICE_MTH == '0'
            && BPVFSTD.VAL.AGGR_TYPE == '0') {
            B045_CAL_FEE();
        } else if (BPVFSTD.VAL.CAL_TYPE == '1'
            && BPVFSTD.VAL.PRICE_MTH == '0'
            && BPVFSTD.VAL.AGGR_TYPE == '1') {
            B046_CAL_FEE();
        } else {
        }
        if (BPCFFCAL.DATA.FEE_AMT < BPVFSTD.VAL.MIN_AMT 
            && BPVFSTD.VAL.MIN_AMT > 0) {
            BPCFFCAL.DATA.FEE_AMT = BPVFSTD.VAL.MIN_AMT;
        } else {
            if (BPCFFCAL.DATA.FEE_AMT > BPVFSTD.VAL.MAX_AMT 
                && BPVFSTD.VAL.MAX_AMT > 0) {
                BPCFFCAL.DATA.FEE_AMT = BPVFSTD.VAL.MAX_AMT;
            }
        }
        BPCFFCAL.DATA.CHG_AMT = BPCFFCAL.DATA.FEE_AMT;
        if (BPCFFCAL.DATA.CHG_CCY.trim().length() == 0 
            || BPCFFCAL.DATA.CHG_CCY.charAt(0) == 0X00) {
            BPCFFCAL.DATA.CHG_CCY = BPCFFCAL.DATA.FEE_CCY;
        }
    }
    public void B041_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            BPCFFCAL.DATA.FEE_AMT = BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B042_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B043_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            BPCFFCAL.DATA.FEE_AMT = WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER / 1000;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B044_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC >= 1) {
            if (WS_LOC == 1) {
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER / 1000;
            } else {
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[1-1].UP_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER / 1000;
                for (WS_CNT1 = 2; WS_CNT1 <= WS_LOC - 1; WS_CNT1 += 1) {
                    BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT - BPVFSTD.VAL.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER / 1000;
                }
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPVFSTD.VAL.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER / 1000;
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B045_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            BPCFFCAL.DATA.FEE_AMT = BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B046_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                CEP.TRC(SCCGWA, BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT);
                CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void T000_EXCHANGE_CURRENCY() throws IOException,SQLException,Exception {
        R000_TRANS_BPCEX_IN();
        S000_CALL_BPZSFX();
        R000_TRANS_BPCEX_OUT();
    }
    public void R000_TRANS_BPCEX_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.SELL_AMT = WS_TX_AMT;
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCFX.SELL_CCY = BPCFFCAL.DATA.CNT_CCY;
    }
    public void R000_TRANS_BPCEX_OUT() throws IOException,SQLException,Exception {
        WS_TX_AMT = BPCFX.BUY_AMT;
    }
    public void R000_GET_STD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSTD ";
        BPCFPARM.INFO.POINTER = BPVFSTD;
        S000_CALL_BPZFPARM();
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GET_FEE_PARM, BPCFPARM);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFTOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GET_TOT_INFO, BPCFFTOT);
        if (BPCFFTOT.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCFFTOT.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFCAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFCAL = ");
            CEP.TRC(SCCGWA, BPCFFCAL);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
