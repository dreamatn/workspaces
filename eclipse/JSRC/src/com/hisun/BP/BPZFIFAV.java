package com.hisun.BP;

import com.hisun.SC.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFIFAV {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_F_CAL_FAV = "BP-F-F-CAL-FAV      ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String K_PARM_TYPE = "FCOM ";
    char WS_EMP_RECORD = ' ';
    BPZFIFAV_WS_CAL_INFO WS_CAL_INFO = new BPZFIFAV_WS_CAL_INFO();
    double WS_FAV_AMT_FINA = 0;
    char WS_TBL_FCOM_FLAG = ' ';
    char WS_GET_AGGRAGATE = ' ';
    char WS_COMPARE_AMT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFCOM BPVFCOM = new BPVFCOM();
    BPCTCFAV BPCTCFAV = new BPCTCFAV();
    SCCGWA SCCGWA;
    BPCTIFAV BPCTIFAV;
    public void MP(SCCGWA SCCGWA, BPCTIFAV BPCTIFAV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTIFAV = BPCTIFAV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFIFAV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPCTCFAV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_FCOM_INFO();
        if (pgmRtn) return;
        if (WS_TBL_FCOM_FLAG == 'Y') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B030_CAL_INTEGRATE_FAV_CN();
                if (pgmRtn) return;
                if (BPCTIFAV.INPUT_AREA.URG_RMT_FLG == 'Y' 
                    && (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0 
                    || BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0)) {
                    B050_CAL_INTEGRATE_FAV_URG_CN();
                    if (pgmRtn) return;
                } else {
                    B040_CAL_INT_BY_SELECT_CN();
                    if (pgmRtn) return;
                }
            } else {
                B030_CAL_INTEGRATE_FAV();
                if (pgmRtn) return;
                B040_CAL_INTEGRATE_BY_SELECT();
                if (pgmRtn) return;
            }
        }
        B090_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.FEE_CODE);
        if (BPCTIFAV.INPUT_AREA.FEE_CODE.trim().length() == 0) {
            BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
            BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_FCOM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        BPVFCOM.KEY.FEE_CODE = BPCTIFAV.INPUT_AREA.FEE_CODE;
        BPVFCOM.KEY.REG_CODE = BPCTIFAV.INPUT_AREA.REGION_CODE;
        BPVFCOM.KEY.CHN_NO = BPCTIFAV.INPUT_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = BPCTIFAV.INPUT_AREA.FREE_FMT;
        R000_GET_FCOM_INFO();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPCTIFAV.INPUT_AREA.FEE_CODE;
            BPVFCOM.KEY.REG_CODE = BPCTIFAV.INPUT_AREA.REGION_CODE;
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = BPCTIFAV.INPUT_AREA.FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPCTIFAV.INPUT_AREA.FEE_CODE;
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = BPCTIFAV.INPUT_AREA.CHNL;
            BPVFCOM.KEY.FREE_FMT = BPCTIFAV.INPUT_AREA.FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPCTIFAV.INPUT_AREA.FEE_CODE;
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = BPCTIFAV.INPUT_AREA.FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        WS_TBL_FCOM_FLAG = 'Y';
        if (BPCFPARM.RETURN_INFO == 'N') {
            BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
            WS_TBL_FCOM_FLAG = 'N';
        }
    }
    public void B030_CAL_INTEGRATE_FAV() throws IOException,SQLException,Exception {
        B030_01_CHECK_FAV_CODE_DATE();
        if (pgmRtn) return;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 9; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE);
            if (BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCTCFAV);
                BPCTCFAV.INPUT_AREA.FAV_CODE = BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE;
                BPCTCFAV.INPUT_AREA.PRD_CODE = BPCTIFAV.INPUT_AREA.PROD_CODE;
                BPCTCFAV.INPUT_AREA.FAV_CCY = BPCTIFAV.INPUT_AREA.FAV_CCY;
                BPCTCFAV.INPUT_AREA.TX_AC = BPCTIFAV.INPUT_AREA.TX_AC;
                BPCTCFAV.INPUT_AREA.TX_CI = BPCTIFAV.INPUT_AREA.TX_CI;
                CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.TX_AMT);
                CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.BASIC_FEE);
                BPCTCFAV.INPUT_AREA.TX_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE;
                BPCTCFAV.INPUT_AREA.TX_AMT_OTHER = BPCTIFAV.INPUT_AREA.TX_AMT_OTHER;
                BPCTCFAV.INPUT_AREA.TX_CNT = BPCTIFAV.INPUT_AREA.TX_CNT;
                S000_CALL_BPZFCFAV();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCTCFAV.OUTPUT_AREA.FAV_AMT);
                CEP.TRC(SCCGWA, BPCTCFAV.OUTPUT_AREA.FAV_PCT);
                if (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0 
                    || BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0) {
                    WS_CAL_INFO.WS_CNT_FAV += 1;
                    CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
                    if (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0) {
                        WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_AMT = BPCTCFAV.OUTPUT_AREA.FAV_AMT;
                        BPCTIFAV.OUTPUT_AREA.FAV_AMT = BPCTCFAV.OUTPUT_AREA.FAV_AMT;
                    }
                    if (BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0) {
                        WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_PCT = BPCTCFAV.OUTPUT_AREA.FAV_PCT;
                        BPCTIFAV.OUTPUT_AREA.FAV_PCT = BPCTCFAV.OUTPUT_AREA.FAV_PCT;
                    }
                    CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_AMT);
                    CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_PCT);
                }
            }
        }
    }
    public void B030_CAL_INTEGRATE_FAV_CN() throws IOException,SQLException,Exception {
        B030_01_CHECK_FAV_CODE_DATE();
        if (pgmRtn) return;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 9; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE);
            if (BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCTCFAV);
                BPCTCFAV.INPUT_AREA.FAV_CODE = BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE;
                CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.REGION_CODE);
                if (BPCTIFAV.INPUT_AREA.REGION_CODE.trim().length() == 0) BPCTCFAV.INPUT_AREA.BR = 0;
                else BPCTCFAV.INPUT_AREA.BR = Integer.parseInt(BPCTIFAV.INPUT_AREA.REGION_CODE);
                BPCTCFAV.INPUT_AREA.FEE_CODE = BPCTIFAV.INPUT_AREA.FEE_CODE;
                BPCTCFAV.INPUT_AREA.PRD_CODE = BPCTIFAV.INPUT_AREA.PROD_CODE;
                BPCTCFAV.INPUT_AREA.FAV_CCY = BPCTIFAV.INPUT_AREA.FAV_CCY;
                BPCTCFAV.INPUT_AREA.TX_AC = BPCTIFAV.INPUT_AREA.TX_AC;
                BPCTCFAV.INPUT_AREA.TX_CI = BPCTIFAV.INPUT_AREA.TX_CI;
                CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.TX_AMT);
                CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.BASIC_FEE);
                BPCTCFAV.INPUT_AREA.TX_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE;
                BPCTCFAV.INPUT_AREA.TX_AMT_OTHER = BPCTIFAV.INPUT_AREA.TX_AMT_OTHER;
                BPCTCFAV.INPUT_AREA.TX_CNT = BPCTIFAV.INPUT_AREA.TX_CNT;
                if (BPCTIFAV.INPUT_AREA.URG_RMT_FLG == 'Y') {
                    BPCTCFAV.INPUT_AREA.URG_RMT_FLG = BPCTIFAV.INPUT_AREA.URG_RMT_FLG;
                }
                S000_CALL_BPZFCFAV();
                if (pgmRtn) return;
                if (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0 
                    || BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0) {
                    WS_CAL_INFO.WS_CNT_FAV += 1;
                    CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
                    if (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0) {
                        WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_AMT = BPCTCFAV.OUTPUT_AREA.FAV_AMT;
                        BPCTIFAV.OUTPUT_AREA.FAV_AMT = BPCTCFAV.OUTPUT_AREA.FAV_AMT;
                    }
                    if (BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0) {
                        WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_PCT = BPCTCFAV.OUTPUT_AREA.FAV_PCT;
                        BPCTIFAV.OUTPUT_AREA.FAV_PCT = BPCTCFAV.OUTPUT_AREA.FAV_PCT;
                    }
                    WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_ADJ_TYP = BPCTCFAV.OUTPUT_AREA.ADJ_TYP;
                }
            }
        }
    }
    public void B030_01_CHECK_FAV_CODE_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFCOM.EXP_DATE);
        if (BPVFCOM.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
            || BPVFCOM.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_05_CAL_FAV_BY_AMT() throws IOException,SQLException,Exception {
    }
    public void B040_CAL_INTEGRATE_BY_SELECT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFCOM.VAL.FAV_SELECT);
        if (BPVFCOM.VAL.FAV_SELECT == '0') {
            B040_05_CAL_INTEGRATE_FAV_BY_H();
            if (pgmRtn) return;
        } else if (BPVFCOM.VAL.FAV_SELECT == '1') {
            B040_10_CAL_INTEGRATE_FAV_BY_L();
            if (pgmRtn) return;
        } else if (BPVFCOM.VAL.FAV_SELECT == '2') {
            B040_15_CAL_INTEGRATE_FAV_BY_P();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_CAL_INT_BY_SELECT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFCOM.VAL.FAV_SELECT);
        if (BPVFCOM.VAL.FAV_SELECT == '0') {
            B040_05_CAL_INT_FAV_BY_H_CN();
            if (pgmRtn) return;
        } else if (BPVFCOM.VAL.FAV_SELECT == '1') {
            B040_10_CAL_INT_FAV_BY_L_CN();
            if (pgmRtn) return;
        } else if (BPVFCOM.VAL.FAV_SELECT == '2') {
            B040_15_CAL_INT_FAV_BY_P_CN();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_05_CAL_INT_FAV_BY_H_CN() throws IOException,SQLException,Exception {
        BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        WS_CAL_INFO.WS_LAS_AMT = 99999999999999.99;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else {
                }
                if (WS_CAL_INFO.WS_NOW_AMT < WS_CAL_INFO.WS_LAS_AMT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
                    BPCTIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_LAS_AMT);
                CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
            } else {
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE * ( 1 + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE * ( 1 - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.TX_AMT * WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100;
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                if (WS_CAL_INFO.WS_NOW_AMT < WS_CAL_INFO.WS_LAS_AMT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
                    BPCTIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
            }
        }
    }
    public void B040_10_CAL_INT_FAV_BY_L_CN() throws IOException,SQLException,Exception {
        BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        WS_CAL_INFO.WS_LAS_AMT = 0;
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        CEP.TRC(SCCGWA, "HANZHEN1");
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, "BGN-TRC");
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP);
            CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.BASIC_FEE);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
            CEP.TRC(SCCGWA, "END-TRC");
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    CEP.TRC(SCCGWA, "INCREASE");
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    CEP.TRC(SCCGWA, "DECREASE");
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    CEP.TRC(SCCGWA, "ADJUST TO");
                    WS_CAL_INFO.WS_NOW_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else {
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_LAS_AMT);
                if (WS_CAL_INFO.WS_NOW_AMT > WS_CAL_INFO.WS_LAS_AMT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                } else {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE;
                }
                BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
                BPCTIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
            } else {
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    CEP.TRC(SCCGWA, "INCREASE");
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE * ( 1 + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE * ( 1 - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.TX_AMT * WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100;
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                if (WS_CAL_INFO.WS_NOW_AMT > WS_CAL_INFO.WS_LAS_AMT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
                    BPCTIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
            }
        }
        CEP.TRC(SCCGWA, "END-PERFORM");
    }
    public void B040_15_CAL_INT_FAV_BY_P_CN() throws IOException,SQLException,Exception {
        BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = BPCTIFAV.OUTPUT_AREA.FAV_AMT - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                }
            } else {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT = BPCTIFAV.OUTPUT_AREA.FAV_PCT - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                }
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP != '2') {
                    WS_FAV_AMT_FINA = BPCTIFAV.INPUT_AREA.BASIC_FEE * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                    bigD = new BigDecimal(WS_FAV_AMT_FINA);
                    WS_FAV_AMT_FINA = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
            }
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0' 
                || WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                WS_COMPARE_AMT_FLAG = 'Y';
            }
        }
        BPCTIFAV.OUTPUT_AREA.FAV_AMT += WS_FAV_AMT_FINA;
        BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.BASIC_FEE);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, WS_COMPARE_AMT_FLAG);
        if (WS_COMPARE_AMT_FLAG == 'Y') {
            WS_CAL_INFO.WS_NOW_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE + BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        }
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
        if (BPCTIFAV.OUTPUT_AREA.FAV_AMT > 0) {
            BPCTIFAV.OUTPUT_AREA.ADJ_TYP = '0';
        } else {
            if (BPCTIFAV.OUTPUT_AREA.FAV_AMT < 0 - BPCTIFAV.INPUT_AREA.BASIC_FEE) {
                BPCTIFAV.OUTPUT_AREA.FAV_AMT = BPCTIFAV.INPUT_AREA.BASIC_FEE;
            } else {
                BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0 - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
            }
            BPCTIFAV.OUTPUT_AREA.ADJ_TYP = '1';
        }
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                    if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > WS_CAL_INFO.WS_NOW_AMT) {
                        BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                        BPCTIFAV.OUTPUT_AREA.ADJ_TYP = '2';
                    }
                } else {
                    if (BPCTIFAV.INPUT_AREA.TX_AMT * null / 100 > WS_CAL_INFO.WS_NOW_AMT) {
                        BPCTIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                        BPCTIFAV.OUTPUT_AREA.ADJ_TYP = '2';
                    }
                }
            }
        }
    }
    public void B040_05_CAL_INTEGRATE_FAV_BY_H() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > BPCTIFAV.OUTPUT_AREA.FAV_AMT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                }
            } else {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT > BPCTIFAV.OUTPUT_AREA.FAV_PCT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                }
            }
        }
        WS_FAV_AMT_FINA = BPCTIFAV.INPUT_AREA.BASIC_FEE * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, WS_FAV_AMT_FINA);
        if (BPCTIFAV.OUTPUT_AREA.FAV_AMT > WS_FAV_AMT_FINA) {
            BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        } else {
            BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
        }
    }
    public void B040_10_CAL_INTEGRATE_FAV_BY_L() throws IOException,SQLException,Exception {
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT < BPCTIFAV.OUTPUT_AREA.FAV_AMT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                }
            } else {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT < BPCTIFAV.OUTPUT_AREA.FAV_PCT) {
                    BPCTIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                }
            }
        }
        WS_FAV_AMT_FINA = BPCTIFAV.INPUT_AREA.BASIC_FEE * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, WS_FAV_AMT_FINA);
        if (BPCTIFAV.OUTPUT_AREA.FAV_AMT > WS_FAV_AMT_FINA) {
            BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_FAV_AMT_FINA;
        } else {
            BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        }
    }
    public void B040_15_CAL_INTEGRATE_FAV_BY_P() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        BPCTIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                BPCTIFAV.OUTPUT_AREA.FAV_AMT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
            } else {
                BPCTIFAV.OUTPUT_AREA.FAV_PCT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
            }
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        }
        WS_FAV_AMT_FINA = BPCTIFAV.INPUT_AREA.BASIC_FEE * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
        CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.BASIC_FEE);
        CEP.TRC(SCCGWA, WS_FAV_AMT_FINA);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        BPCTIFAV.OUTPUT_AREA.FAV_AMT += WS_FAV_AMT_FINA;
        BPCTIFAV.OUTPUT_AREA.FAV_PCT = 0;
    }
    public void B050_CAL_INTEGRATE_FAV_URG_CN() throws IOException,SQLException,Exception {
        if (BPVFCOM.VAL.FAV_SELECT == '0') {
            B040_05_CAL_INT_FAV_BY_H_CN();
            if (pgmRtn) return;
        } else if (BPVFCOM.VAL.FAV_SELECT == '1') {
            B040_10_CAL_INT_FAV_BY_L_CN();
            if (pgmRtn) return;
        } else {
        }
        BPCTIFAV.OUTPUT_AREA.ADJ_TYP = '0';
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
    }
    public void R000_GET_FCOM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        WS_TBL_FCOM_FLAG = 'Y';
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPCFPARM.INFO.POINTER = BPVFCOM;
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_PARM_MAIN, BPCFPARM);
    }
    public void S000_CALL_BPZFCFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FAV, BPCTCFAV);
        if (BPCTCFAV.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCFAV.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCTIFAV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTIFAV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCTIFAV = ");
            CEP.TRC(SCCGWA, BPCTIFAV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
