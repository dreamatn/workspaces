package com.hisun.BP;

import com.hisun.SC.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFCAL {
    BigDecimal bigD;
    String K_PGM_NAME = "BPZFFCAL";
    String CPN_GET_FEE_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String CPN_GET_TOT_INFO = "BP-F-F-GET-TOT-INFO ";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
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
    String WS_BUY_CCY = " ";
    String WS_SELL_CCY = " ";
    String WS_REF_CCY = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFX BPCFX = new BPCFX();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTOT BPCFFTOT = new BPCFFTOT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFSTD BPVFSTD = new BPVFSTD();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCFFCAL BPCFFCAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFFCAL BPCFFCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFCAL = BPCFFCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFFCAL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCFFCAL.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFFCAL.RC.RC_CODE = 0;
        if (BPCFFCAL.DATA.STD_REF_FLG == ' ') {
            BPCFFCAL.DATA.STD_REF_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, "COUNT1");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_GET_FEE_STD_INFO_CN();
        } else {
            B010_GET_FEE_STD_INFO();
        }
        B020_CHECK_DATA();
        B030_PERPARE_CAL_DATA();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCFFCAL.DATA.STD_REF_FLG == 'Y') {
                B040_CAL_FEE_CN();
            } else {
                B040_CAL_FEE_BYMODREF_CN();
            }
        } else {
            B040_CAL_FEE();
        }
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
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
    public void B010_GET_FEE_STD_INFO_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
        IBS.init(SCCGWA, BPVFSTD);
        BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
        BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
        BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
        BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
        BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
        R000_GET_STD_PARM();
        CEP.TRC(SCCGWA, "READ1");
        CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPCTFBAS);
            IBS.init(SCCGWA, BPRFBAS);
            BPRFBAS.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CODE);
            CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
            BPCTFBAS.INFO.REC_LEN = 312;
            BPCTFBAS.INFO.FUNC = 'Q';
            BPCTFBAS.INFO.POINTER = BPRFBAS;
            S000_CALL_BPZTFBAS();
            CEP.TRC(SCCGWA, BPRFBAS.EXG_TYP);
            if (BPRFBAS.EXG_TYP == '0') {
                WS_REF_CCY = BPCRBANK.LOC_CCY1;
            } else {
                WS_REF_CCY = BPCRBANK.EVA_CCY;
            }
            CEP.TRC(SCCGWA, WS_REF_CCY);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ2");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ3");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCFFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ4");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ5");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = BPCFFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ6");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCFFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ7");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCFFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            CEP.TRC(SCCGWA, BPCFFCAL.DATA.FREE_FMT);
            BPVFSTD.KEY.FREE_FMT = BPCFFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
            R000_GET_STD_PARM();
            CEP.TRC(SCCGWA, "READ8");
            CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
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
        CEP.TRC(SCCGWA, "KKKKK");
        CEP.TRC(SCCGWA, BPVFSTD.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPVFSTD.KEY.REG_CODE);
        CEP.TRC(SCCGWA, BPVFSTD.KEY.CHN_NO);
        CEP.TRC(SCCGWA, BPVFSTD.KEY.FREE_FMT);
        CEP.TRC(SCCGWA, BPVFSTD.KEY.REF_CCY);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.FIX_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.FEE_CCY);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.MIN_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.MAX_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CAL_SOURCE);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CAL_CYC);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CYC_NUM);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.AGR_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.PRICE_MTH);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD.DATE.EFF_DATE);
        CEP.TRC(SCCGWA, BPVFSTD.DATE.EXP_DATE);
    }
    public void B020_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_CNT);
        if (BPVFSTD.VAL.CAL_TYPE == '0') {
            CEP.TRC(SCCGWA, "CHARGE BY AMT");
            if (BPCFFCAL.DATA.TX_AMT < 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMT_NOTINP;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPVFSTD.VAL.CAL_TYPE == '1') {
            CEP.TRC(SCCGWA, "CHARGE BY CNT");
            if (BPCFFCAL.DATA.TX_CNT <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCNT_NOTINP;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_PERPARE_CAL_DATA() throws IOException,SQLException,Exception {
        WS_TX_AMT = BPCFFCAL.DATA.TX_AMT;
        WS_TX_CNT = BPCFFCAL.DATA.TX_CNT;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CNT_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if (!BPCFFCAL.DATA.CNT_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY) 
            && BPVFSTD.VAL.CAL_TYPE == '0' 
            && BPCFFCAL.DATA.CNT_CCY.trim().length() > 0) {
            WS_SELL_CCY = BPCFFCAL.DATA.CNT_CCY;
            WS_BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
            T000_EXCHANGE_CURRENCY();
            CEP.TRC(SCCGWA, BPCFFCAL.DATA.TX_AMT);
            CEP.TRC(SCCGWA, WS_TX_AMT);
        }
    }
    public void B040_CAL_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.PRICE_MTH);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.AGGR_TYPE);
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
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) {
            WS_SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
            WS_BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            WS_TX_AMT = BPCFFCAL.DATA.FEE_AMT;
            CEP.TRC(SCCGWA, WS_TX_AMT);
            if (WS_TX_AMT > 0) {
                T000_EXCHANGE_CURRENCY();
            }
            CEP.TRC(SCCGWA, WS_TX_AMT);
            BPCFFCAL.DATA.CHG_AMT = WS_TX_AMT;
        }
    }
    public void B040_CAL_FEE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFSTD.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.PRICE_MTH);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.AGGR_TYPE);
        if (BPVFSTD.VAL.CAL_TYPE == '0'
            && BPVFSTD.VAL.AGGR_TYPE == '0') {
            B041_CAL_FEE_CN();
        } else if (BPVFSTD.VAL.CAL_TYPE == '0'
            && BPVFSTD.VAL.AGGR_TYPE == '1') {
            B042_CAL_FEE_CN();
        } else if (BPVFSTD.VAL.CAL_TYPE == '1'
            && BPVFSTD.VAL.AGGR_TYPE == '0') {
            B043_CAL_FEE_CN();
        } else if (BPVFSTD.VAL.CAL_TYPE == '1'
            && BPVFSTD.VAL.AGGR_TYPE == '1') {
            B044_CAL_FEE_CN();
        } else {
        }
        CEP.TRC(SCCGWA, "987789");
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.MIN_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.MAX_AMT);
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
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) {
            WS_SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
            WS_BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            WS_TX_AMT = BPCFFCAL.DATA.FEE_AMT;
            CEP.TRC(SCCGWA, WS_TX_AMT);
            if (WS_TX_AMT > 0) {
                T000_EXCHANGE_CURRENCY();
            }
            CEP.TRC(SCCGWA, WS_TX_AMT);
            BPCFFCAL.DATA.CHG_AMT = WS_TX_AMT;
            BPCFFCAL.DATA.FEE_AMT = WS_TX_AMT;
        }
    }
    public void B040_CAL_FEE_BYMODREF_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CAL_TYPE);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.AGGR_TYPE);
        if (BPCFFCAL.DATA.CAL_TYPE == '0'
            && BPCFFCAL.DATA.AGGR_TYPE == '0') {
            B041_CAL_FEE_BYMODREF_CN();
        } else if (BPCFFCAL.DATA.CAL_TYPE == '0'
            && BPCFFCAL.DATA.AGGR_TYPE == '1') {
            B042_CAL_FEE_BYMODREF_CN();
        } else if (BPCFFCAL.DATA.CAL_TYPE == '1'
            && BPCFFCAL.DATA.AGGR_TYPE == '0') {
            B043_CAL_FEE_BYMODREF_CN();
        } else if (BPCFFCAL.DATA.CAL_TYPE == '1'
            && BPCFFCAL.DATA.AGGR_TYPE == '1') {
            B044_CAL_FEE_BYMODREF_CN();
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
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) {
            WS_SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
            WS_BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            WS_TX_AMT = BPCFFCAL.DATA.FEE_AMT;
            CEP.TRC(SCCGWA, WS_TX_AMT);
            if (WS_TX_AMT > 0) {
                T000_EXCHANGE_CURRENCY();
            }
            CEP.TRC(SCCGWA, WS_TX_AMT);
            BPCFFCAL.DATA.CHG_AMT = WS_TX_AMT;
        }
    }
    public void B041_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT < BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
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
    public void B041_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_TX_AMT);
            CEP.TRC(SCCGWA, BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT);
            CEP.TRC(SCCGWA, WS_LOC);
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, "END-111");
            if (WS_TX_AMT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            CEP.TRC(SCCGWA, BPVFSTD.VAL.FEE_DATA[WS_LOC-1].AGG_MTH);
            if (BPVFSTD.VAL.FEE_DATA[WS_LOC-1].AGG_MTH == '1') {
                BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT = WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER;
                bigD = new BigDecimal(BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT);
                BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            BPCFFCAL.DATA.FEE_AMT = BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, WS_LOC);
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPVFSTD.VAL.FIX_AMT);
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public void B041_CAL_FEE_BYMODREF_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT > 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            if (BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].AGG_MTH == '1') {
                BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT = WS_TX_AMT * BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_PER;
                bigD = new BigDecimal(BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT);
                BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
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
    public void B042_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                if (WS_LOC == 1) {
                    if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                    } else {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER;
                        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                } else {
                    if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                    } else {
                        if (WS_CNT1 == 1) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[1-1].UP_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT - BPVFSTD.VAL.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 == WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPVFSTD.VAL.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                }
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public void B042_CAL_FEE_BYMODREF_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT > 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                if (WS_LOC == 1) {
                    if (BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].RFEE_AMT;
                    } else {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_AMT * BPCFFCAL.DATA.FEE_DATA[1-1].RFEE_PER;
                        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                } else {
                    if (BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].RFEE_AMT;
                    } else {
                        if (WS_CNT1 == 1) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPCFFCAL.DATA.FEE_DATA[1-1].UP_AMT * BPCFFCAL.DATA.FEE_DATA[1-1].RFEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT - BPCFFCAL.DATA.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].RFEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 == WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPCFFCAL.DATA.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                }
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
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
            BPCFFCAL.DATA.FEE_AMT = WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER / 100;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B043_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT > 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            if (BPVFSTD.VAL.FEE_DATA[WS_LOC-1].AGG_MTH == '1') {
                BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT = WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER;
                bigD = new BigDecimal(BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT);
                BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            if (BPVFSTD.VAL.FEE_DATA[WS_LOC-1].AGG_MTH == '2') {
                BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT = WS_TX_CNT * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
                bigD = new BigDecimal(BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT);
                BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            }
            BPCFFCAL.DATA.FEE_AMT = BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public void B043_CAL_FEE_BYMODREF_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT > 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            if (BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].AGG_MTH == '1') {
                BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT = WS_TX_AMT * BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_PER;
                bigD = new BigDecimal(BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT);
                BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_AMT;
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
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
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER / 100;
            } else {
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[1-1].UP_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER / 100;
                for (WS_CNT1 = 2; WS_CNT1 <= WS_LOC - 1; WS_CNT1 += 1) {
                    BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT - BPVFSTD.VAL.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER / 100;
                }
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPVFSTD.VAL.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER / 100;
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B044_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT > 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        CEP.TRC(SCCGWA, WS_LOC);
        if (WS_LOC > 0) {
            if (WS_LOC == 1) {
                CEP.TRC(SCCGWA, WS_CNT1);
                WS_CNT1 = WS_LOC;
                CEP.TRC(SCCGWA, "SET WS-CNT1 TO  WS-LOC WHEN WS-LOC = 1");
                CEP.TRC(SCCGWA, WS_CNT1);
                if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                    BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                } else if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '1') {
                    BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER;
                    bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                    BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '2') {
                    BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_CNT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_AMT;
                    bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                    BPCFFCAL.DATA.FEE_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
                } else {
                }
            } else {
                for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                    if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                    } else if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '1') {
                        if (WS_CNT1 == 1) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[1-1].UP_AMT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT - BPVFSTD.VAL.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 == WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPVFSTD.VAL.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                    } else if (BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH == '2') {
                        if (WS_CNT1 == 1) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FEE_DATA[1-1].UP_CNT * BPVFSTD.VAL.FEE_DATA[1-1].FEE_AMT;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT - BPVFSTD.VAL.FEE_DATA[WS_CNT1 - 1-1].UP_CNT ) * BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 == WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_CNT - BPVFSTD.VAL.FEE_DATA[WS_LOC - 1-1].UP_CNT ) * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
                        }
                    } else {
                    }
                }
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public void B044_CAL_FEE_BYMODREF_CN() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT > 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                if (WS_LOC == 1) {
                    if (BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].RFEE_AMT;
                    } else {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_TX_AMT * BPCFFCAL.DATA.FEE_DATA[1-1].RFEE_PER;
                        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                } else {
                    if (BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].AGG_MTH == '0') {
                        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].RFEE_AMT;
                    } else {
                        if (WS_CNT1 == 1) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPCFFCAL.DATA.FEE_DATA[1-1].UP_AMT * BPCFFCAL.DATA.FEE_DATA[1-1].RFEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT - BPCFFCAL.DATA.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * BPCFFCAL.DATA.FEE_DATA[WS_CNT1-1].RFEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                        if (WS_CNT1 == WS_LOC) {
                            BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPCFFCAL.DATA.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * BPCFFCAL.DATA.FEE_DATA[WS_LOC-1].RFEE_PER;
                            bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
                            BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        }
                    }
                }
            }
        } else {
            BPCFFCAL.DATA.FEE_AMT = 0;
        }
        BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + BPVFSTD.VAL.FIX_AMT;
        bigD = new BigDecimal(BPCFFCAL.DATA.FEE_AMT);
        BPCFFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
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
    public void B047_CAL_FEE() throws IOException,SQLException,Exception {
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
            BPCFFCAL.DATA.FEE_AMT = WS_TX_AMT * BPVFSTD.VAL.FEE_DATA[WS_LOC-1].FEE_PER / 100;
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
        BPCFX.EXR_TYPE = BPCFFCAL.DATA.EXG_GROUP;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.BUY_CCY = WS_BUY_CCY;
        BPCFX.SELL_CCY = WS_SELL_CCY;
        BPCFX.SELL_AMT = WS_TX_AMT;
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
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTOT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_T_FEE_INFO, BPCTFBAS);
        if (BPCTFBAS.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTFBAS.RC);
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
