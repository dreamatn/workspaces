package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.LN.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUFCAL {
    BigDecimal bigD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZUFCAL";
    String CPN_GET_FEE_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String WS_TEMP_RECORD = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TX_AMT = 0;
    double WS_REF_PCT = 0;
    int WS_TX_CNT = 0;
    short WS_CNT1 = 0;
    short WS_LOC = 0;
    String WS_TXT = " ";
    String WS_QUEUE = " ";
    long WS_RESP = 0;
    short WS_INT_BAS_DAYS = 0;
    short WS_TOTAL_DAYS = 0;
    String WS_BUY_CCY = " ";
    String WS_SELL_CCY = " ";
    double WS_ADJ_AMT = 0;
    BPZUFCAL_WS_FEE_PER_ARRAY WS_FEE_PER_ARRAY = new BPZUFCAL_WS_FEE_PER_ARRAY();
    char WS_FOUND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCEX BPCEX = new BPCEX();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTOT BPCFFTOT = new BPCFFTOT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFSTD BPVFSTD = new BPVFSTD();
    BPVFSTD1 BPVFSTD1 = new BPVFSTD1();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    LNCIAMT LNCIAMT = new LNCIAMT();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUFCAL BPCUFCAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCUFCAL BPCUFCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUFCAL = BPCUFCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUFCAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCUFCAL.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCUFCAL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_GET_FEE_BAS_INFO();
        if (pgmRtn) return;
        B015_TRANS_DATA();
        if (pgmRtn) return;
        B020_CHECK_DATA();
        if (pgmRtn) return;
        B030_PERPARE_CAL_DATA();
        if (pgmRtn) return;
        B040_CAL_FEE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_AMT);
    }
    public void B005_GET_FEE_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPRFBAS.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
    }
    public void B010_GET_FEE_STD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.TX_AMT);
        IBS.init(SCCGWA, BPVFSTD);
        BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
        BPVFSTD.KEY.REG_CODE = BPCUFCAL.DATA.REG_CODE;
        BPVFSTD.KEY.CHN_NO = BPCUFCAL.DATA.CHNL_NO;
        BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
        BPVFSTD.KEY.REF_CCY = BPCUFCAL.DATA.CNT_CCY;
        R000_GET_STD_PARM();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCUFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = BPCUFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCUFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCUFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = BPCUFCAL.DATA.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = BPCUFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCUFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = BPCUFCAL.DATA.CHNL_NO;
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCUFCAL.DATA.CNT_CCY;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFSTD);
            BPVFSTD.KEY.FEE_CODE = BPCUFCAL.DATA.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            CEP.TRC(SCCGWA, BPCUFCAL.DATA.FREE_FMT);
            BPVFSTD.KEY.FREE_FMT = BPCUFCAL.DATA.FREE_FMT;
            BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
            R000_GET_STD_PARM();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSTD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            BPCUFCAL.DATA.FEE_CCY = BPVFSTD.VAL.FEE_CCY;
            BPCUFCAL.DATA.FEE_AMT = 0;
            BPCUFCAL.DATA.CHG_AMT = 0;
            BPCUFCAL.DATA.EXG_DATE = 0;
            BPCUFCAL.DATA.EXG_TIME = 0;
            for (WS_CNT1 = 1; WS_CNT1 <= 5; WS_CNT1 += 1) {
                WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_CNT1-1] = BPVFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER;
            }
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_CCY);
    }
    public void B015_TRANS_DATA() throws IOException,SQLException,Exception {
        BPVFSTD1.VAL.CAL_TYPE = '0';
        BPVFSTD1.VAL.REFER_MTH = BPCUFCAL.DATA.REFER_MTH;
        BPVFSTD1.VAL.PRICE_MTH = BPCUFCAL.DATA.PRICE_MTH;
        BPVFSTD1.VAL.AGGR_TYPE = BPCUFCAL.DATA.AGGR_TYPE;
        for (WS_CNT1 = 1; WS_CNT1 <= 5; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT = BPCUFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_AMT;
            BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT = BPCUFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_PCT;
            BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_CNT = BPCUFCAL.DATA.FEE_DATA[WS_CNT1-1].UP_CNT;
            BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT = BPCUFCAL.DATA.FEE_DATA[WS_CNT1-1].REF_FEE_AMT;
            WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_CNT1-1] = BPCUFCAL.DATA.FEE_DATA[WS_CNT1-1].REF_FEE_PER;
        }
    }
    public void B020_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.AMT_TYPE);
        if (BPCUFCAL.DATA.AMT_TYPE != 0 
            && BPCUFCAL.DATA.REL_CTRT_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REL_CTR_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUFCAL.DATA.AMT_TYPE == 0 
            && BPCUFCAL.DATA.TX_AMT == 0) {
            BPCUFCAL.DATA.TX_AMT = 1;
        }
        if (BPCUFCAL.DATA.REL_CTRT_NO.trim().length() > 0 
            && BPCUFCAL.DATA.AMT_TYPE != 0) {
            IBS.init(SCCGWA, LNCIAMT);
            LNCIAMT.CTA_NO = BPCUFCAL.DATA.REL_CTRT_NO;
            LNCIAMT.AMT_TYP = BPCUFCAL.DATA.AMT_TYPE;
            S000_CALL_LNZIAMT();
            if (pgmRtn) return;
            BPCUFCAL.DATA.TX_AMT = LNCIAMT.OUTPUT[1-1].AMT;
            WS_REF_PCT = LNCIAMT.OUTPUT[1-1].PCT;
            CEP.TRC(SCCGWA, LNCIAMT.OUTPUT[1-1].AMT);
            CEP.TRC(SCCGWA, LNCIAMT.OUTPUT[1-1].PCT);
        }
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.PRICE_MTH);
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.TX_AMT);
        if (BPVFSTD1.VAL.CAL_TYPE == '0') {
            if (BPCUFCAL.DATA.TX_AMT <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMT_NOTINP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPVFSTD1.VAL.CAL_TYPE == '1') {
            if (BPCUFCAL.DATA.TX_CNT <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCNT_NOTINP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_PERPARE_CAL_DATA() throws IOException,SQLException,Exception {
        if (BPCUFCAL.DATA.MULTI != 0) {
            BPCUFCAL.DATA.TX_AMT = BPCUFCAL.DATA.TX_AMT * BPCUFCAL.DATA.MULTI / 100;
        }
        CEP.TRC(SCCGWA, "==========");
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.TX_AMT);
        WS_TX_AMT = BPCUFCAL.DATA.TX_AMT;
        WS_TX_CNT = BPCUFCAL.DATA.TX_CNT;
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.TX_AMT);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CNT_CCY);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_CCY);
        if (BPCUFCAL.DATA.CHG_CCY.trim().length() > 0) {
            if (BPCUFCAL.DATA.FEE_CCY.trim().length() == 0) {
                BPCUFCAL.DATA.FEE_CCY = BPCUFCAL.DATA.CHG_CCY;
            }
            if (BPCUFCAL.DATA.CNT_CCY.trim().length() == 0) {
                BPCUFCAL.DATA.CNT_CCY = BPCUFCAL.DATA.CHG_CCY;
            }
        }
        if (BPCUFCAL.DATA.CNT_CCY.trim().length() == 0) {
            BPCUFCAL.DATA.CNT_CCY = BPCUFCAL.DATA.FEE_CCY;
        }
        if (!BPCUFCAL.DATA.CNT_CCY.equalsIgnoreCase(BPCUFCAL.DATA.FEE_CCY) 
            && BPVFSTD1.VAL.CAL_TYPE == '0' 
            && BPCUFCAL.DATA.CNT_CCY.trim().length() > 0) {
            WS_SELL_CCY = BPCUFCAL.DATA.CNT_CCY;
            WS_BUY_CCY = BPCUFCAL.DATA.FEE_CCY;
            T000_EXCHANGE_CURRENCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCUFCAL.DATA.TX_AMT);
            CEP.TRC(SCCGWA, WS_TX_AMT);
        }
        if (BPCUFCAL.DATA.START_DATE == 0) {
            BPCUFCAL.DATA.START_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCUFCAL.DATA.END_DATE == 0) {
            BPCUFCAL.DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        WS_INT_BAS_DAYS = 365;
        if (BPCUFCAL.DATA.INT_BAS.equalsIgnoreCase("1")) {
            WS_INT_BAS_DAYS = 360;
        }
        if (BPCUFCAL.DATA.INT_BAS.equalsIgnoreCase("3")) {
            WS_INT_BAS_DAYS = 366;
        }
        WS_TOTAL_DAYS = 0;
    }
    public void B040_CAL_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.CAL_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.PRICE_MTH);
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.REFER_MTH);
        if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '0'
            && BPVFSTD1.VAL.AGGR_TYPE == '0'
            && BPVFSTD1.VAL.REFER_MTH == '0') {
            CEP.TRC(SCCGWA, "B041");
            B041_CAL_FEE();
            if (pgmRtn) return;
        } else if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '0'
            && BPVFSTD1.VAL.AGGR_TYPE == '1'
            && BPVFSTD1.VAL.REFER_MTH == '0') {
            B042_CAL_FEE();
            if (pgmRtn) return;
        } else if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '1'
            && BPVFSTD1.VAL.AGGR_TYPE == '0'
            && BPVFSTD1.VAL.REFER_MTH == '0') {
            B043_CAL_FEE();
            if (pgmRtn) return;
        } else if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '1'
            && BPVFSTD1.VAL.AGGR_TYPE == '1'
            && BPVFSTD1.VAL.REFER_MTH == '0') {
            B044_CAL_FEE();
            if (pgmRtn) return;
        } else if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '0'
            && BPVFSTD1.VAL.AGGR_TYPE == '0'
            && BPVFSTD1.VAL.REFER_MTH == '1') {
            CEP.TRC(SCCGWA, "B047");
            B047_CAL_FEE();
            if (pgmRtn) return;
        } else if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '0'
            && BPVFSTD1.VAL.AGGR_TYPE == '1'
            && BPVFSTD1.VAL.REFER_MTH == '1') {
            B048_CAL_FEE();
            if (pgmRtn) return;
        } else if (BPVFSTD1.VAL.CAL_TYPE == '0'
            && BPVFSTD1.VAL.PRICE_MTH == '1'
            && BPVFSTD1.VAL.AGGR_TYPE == '0'
            && BPVFSTD1.VAL.REFER_MTH == '1') {
            B049_CAL_FEE();
            if (pgmRtn) return;
        } else {
        }
        if (BPCUFCAL.DATA.FROM_FLG != '1') {
            if (BPCUFCAL.DATA.FEE_AMT < BPVFSTD1.VAL.MIN_AMT 
                && BPVFSTD1.VAL.MIN_AMT > 0) {
                BPCUFCAL.DATA.FEE_AMT = BPVFSTD1.VAL.MIN_AMT;
            } else {
                if (BPCUFCAL.DATA.FEE_AMT > BPVFSTD1.VAL.MAX_AMT 
                    && BPVFSTD1.VAL.MAX_AMT > 0) {
                    BPCUFCAL.DATA.FEE_AMT = BPVFSTD1.VAL.MAX_AMT;
                }
            }
        }
        BPCUFCAL.DATA.CHG_AMT = BPCUFCAL.DATA.FEE_AMT;
        if (BPCUFCAL.DATA.CHG_CCY.trim().length() == 0 
            || BPCUFCAL.DATA.CHG_CCY.charAt(0) == 0X00) {
            BPCUFCAL.DATA.CHG_CCY = BPCUFCAL.DATA.FEE_CCY;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_CCY);
        if (!BPCUFCAL.DATA.CHG_CCY.equalsIgnoreCase(BPCUFCAL.DATA.FEE_CCY)) {
            WS_SELL_CCY = BPCUFCAL.DATA.FEE_CCY;
            WS_BUY_CCY = BPCUFCAL.DATA.CHG_CCY;
            WS_TX_AMT = BPCUFCAL.DATA.FEE_AMT;
            T000_EXCHANGE_CURRENCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
            CEP.TRC(SCCGWA, WS_TX_AMT);
            BPCUFCAL.DATA.CHG_AMT = WS_TX_AMT;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_AMT);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHG_AMT);
    }
    public void B041_CAL_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPVFSTD1.VAL.FEE_DATA[1-1].UP_AMT);
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        if (WS_LOC > 0) {
            BPCUFCAL.DATA.FEE_AMT = BPVFSTD1.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void B042_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
            }
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void B043_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        CEP.TRC(SCCGWA, WS_TOTAL_DAYS);
        CEP.TRC(SCCGWA, WS_INT_BAS_DAYS);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_LOC-1]);
        if (WS_LOC > 0) {
            if (WS_TOTAL_DAYS == 0) {
                BPCUFCAL.DATA.FEE_AMT = WS_TX_AMT * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_LOC-1] / 1000;
                bigD = new BigDecimal(BPCUFCAL.DATA.FEE_AMT);
                BPCUFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                BPCUFCAL.DATA.FEE_AMT = WS_TX_AMT * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_LOC-1] / 1000 * WS_TOTAL_DAYS / WS_INT_BAS_DAYS;
                bigD = new BigDecimal(BPCUFCAL.DATA.FEE_AMT);
                BPCUFCAL.DATA.FEE_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void B044_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_AMT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT > 0 
                && WS_TX_AMT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        CEP.TRC(SCCGWA, WS_TOTAL_DAYS);
        CEP.TRC(SCCGWA, WS_INT_BAS_DAYS);
        if (WS_LOC >= 1) {
            if (WS_LOC == 1) {
                BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + WS_TX_AMT * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[1-1] / 1000 * WS_TOTAL_DAYS / WS_INT_BAS_DAYS;
            } else {
                BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + BPVFSTD1.VAL.FEE_DATA[1-1].UP_AMT * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[1-1] / 1000 * WS_TOTAL_DAYS / WS_INT_BAS_DAYS;
                for (WS_CNT1 = 2; WS_CNT1 <= WS_LOC - 1; WS_CNT1 += 1) {
                    BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + ( BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_AMT - BPVFSTD1.VAL.FEE_DATA[WS_CNT1 - 1-1].UP_AMT ) * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_CNT1-1] / 1000 * WS_TOTAL_DAYS / WS_INT_BAS_DAYS;
                }
                BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + ( WS_TX_AMT - BPVFSTD1.VAL.FEE_DATA[WS_LOC - 1-1].UP_AMT ) * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_LOC-1] / 1000 * WS_TOTAL_DAYS / WS_INT_BAS_DAYS;
            }
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void B045_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            BPCUFCAL.DATA.FEE_AMT = BPVFSTD1.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B046_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_TX_CNT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_CNT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_CNT > 0 
                && WS_TX_CNT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
                CEP.TRC(SCCGWA, BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT);
                CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
            }
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
    }
    public void B047_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_REF_PCT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT > 0 
                && WS_REF_PCT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        if (WS_LOC > 0) {
            BPCUFCAL.DATA.FEE_AMT = BPVFSTD1.VAL.FEE_DATA[WS_LOC-1].FEE_AMT;
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void B048_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_REF_PCT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT > 0 
                && WS_REF_PCT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        if (WS_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_LOC; WS_CNT1 += 1) {
                BPCUFCAL.DATA.FEE_AMT = BPCUFCAL.DATA.FEE_AMT + BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT;
            }
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void B049_CAL_FEE() throws IOException,SQLException,Exception {
        WS_LOC = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_LOC <= 0; WS_CNT1 += 1) {
            if (WS_REF_PCT <= BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT 
                && BPVFSTD1.VAL.FEE_DATA[WS_CNT1-1].UP_PCT > 0 
                && WS_REF_PCT >= 0) {
                WS_LOC = WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_LOC);
        CEP.TRC(SCCGWA, WS_TOTAL_DAYS);
        CEP.TRC(SCCGWA, WS_INT_BAS_DAYS);
        if (WS_LOC > 0) {
            if (WS_TOTAL_DAYS == 0) {
                BPCUFCAL.DATA.FEE_AMT = WS_TX_AMT * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_LOC-1] / 1000;
                bigD = new BigDecimal(BPCUFCAL.DATA.FEE_AMT);
                BPCUFCAL.DATA.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                BPCUFCAL.DATA.FEE_AMT = WS_TX_AMT * WS_FEE_PER_ARRAY.WS_FSTD_FEE_PER[WS_LOC-1] / 1000 * WS_TOTAL_DAYS / WS_INT_BAS_DAYS;
                bigD = new BigDecimal(BPCUFCAL.DATA.FEE_AMT);
                BPCUFCAL.DATA.FEE_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            BPCUFCAL.DATA.FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_AMT);
    }
    public void T000_EXCHANGE_CURRENCY() throws IOException,SQLException,Exception {
    }
    public void R000_TRANS_BPCEX_OUT() throws IOException,SQLException,Exception {
        WS_TX_AMT = BPCEX.BUY_AMT;
    }
    public void R000_GET_STD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSTD ";
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FEE_NO);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.ACCT_CENTRE);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CHNL_NO);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.FREE_FMT);
        CEP.TRC(SCCGWA, BPCUFCAL.DATA.CNT_CCY);
        BPCFPARM.INFO.POINTER = BPVFSTD;
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GET_FEE_PARM, BPCFPARM);
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_EXG_CURRENCY, BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-CTA-AMT", LNCIAMT);
        if (LNCIAMT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-FEE-INFO", BPCTFBAS);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUFCAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUFCAL = ");
            CEP.TRC(SCCGWA, BPCUFCAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
