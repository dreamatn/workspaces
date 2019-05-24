package com.hisun.BP;

import com.hisun.SC.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUIFAV {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_F_F_CAL_FAV = "BP-F-F-CAL-FAV      ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String K_PARM_TYPE = "FCOM ";
    char WS_EMP_RECORD = ' ';
    BPZUIFAV_WS_CAL_INFO WS_CAL_INFO = new BPZUIFAV_WS_CAL_INFO();
    double WS_FAV_AMT_FINA = 0;
    double WS_FEE_AMT = 0;
    String WS_RGN_CODE = " ";
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
    BPVFFAV BPVFFAV = new BPVFFAV();
    BPCTCFAV BPCTCFAV = new BPCTCFAV();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCUIFAV BPCUIFAV;
    public void MP(SCCGWA SCCGWA, BPCUIFAV BPCUIFAV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUIFAV = BPCUIFAV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUIFAV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPCTCFAV);
        CEP.TRC(SCCGWA, BPCUIFAV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_GET_FEE_BASIC_INFO();
        if (pgmRtn) return;
        B020_GET_FCOM_INFO();
        if (pgmRtn) return;
        if (WS_TBL_FCOM_FLAG == 'Y') {
            B030_CAL_INTEGRATE_FAV_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
            if (BPCUIFAV.INPUT_AREA.URG_RMT_FLG == 'Y' 
                && (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0 
                || BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0)) {
                B050_CAL_INTEGRATE_FAV_URG_CN();
                if (pgmRtn) return;
            } else {
                B040_CAL_INT_BY_SELECT_CN();
                if (pgmRtn) return;
            }
        }
        B090_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIFAV.INPUT_AREA.FEE_CODE);
        if (BPCUIFAV.INPUT_AREA.FEE_CODE.trim().length() == 0) {
            BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
            BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_FCOM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        BPVFCOM.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPVFCOM.KEY.REG_CODE = WS_RGN_CODE;
        BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = BPCUIFAV.INPUT_AREA.FREE_FMT;
        R000_GET_FCOM_INFO();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
            BPVFCOM.KEY.REG_CODE = WS_RGN_CODE;
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = BPCUIFAV.INPUT_AREA.FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
            BPVFCOM.KEY.FREE_FMT = BPCUIFAV.INPUT_AREA.FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = BPCUIFAV.INPUT_AREA.FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        WS_TBL_FCOM_FLAG = 'Y';
        if (BPCFPARM.RETURN_INFO == 'N') {
            BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
            WS_TBL_FCOM_FLAG = 'N';
        }
    }
    public void B030_CAL_INTEGRATE_FAV_CN() throws IOException,SQLException,Exception {
        B030_01_CHECK_FAV_CODE_DATE();
        if (pgmRtn) return;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 9; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE);
            if (BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPVFFAV);
                BPCFPARM.INFO.TYPE = "FFAV ";
                BPVFFAV.KEY.PRFR_CODE = BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE;
                CEP.TRC(SCCGWA, BPVFFAV.KEY.PRFR_CODE);
                BPCFPARM.INFO.POINTER = BPVFFAV;
                BPCFPARM.INFO.FUNC = '3';
                S000_CALL_BPZFPARM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCFPARM.RETURN_INFO);
                CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_TYPE);
                CEP.TRC(SCCGWA, BPCUIFAV.INPUT_AREA.FUNC_CODE);
                if (BPCFPARM.RETURN_INFO == 'F' 
                    && BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase(BPCUIFAV.INPUT_AREA.FUNC_CODE)) {
                    IBS.init(SCCGWA, BPCTCFAV);
                    BPCTCFAV.INPUT_AREA.FAV_CODE = BPVFCOM.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_CODE;
                    BPCTCFAV.INPUT_AREA.FEE_CODE = BPCUIFAV.INPUT_AREA.FEE_CODE;
                    BPCTCFAV.INPUT_AREA.PRD_CODE = BPCUIFAV.INPUT_AREA.PROD_CODE;
                    BPCTCFAV.INPUT_AREA.FAV_CCY = BPCUIFAV.INPUT_AREA.FAV_CCY;
                    BPCTCFAV.INPUT_AREA.TX_AC = BPCUIFAV.INPUT_AREA.TX_AC;
                    BPCTCFAV.INPUT_AREA.TX_CI = BPCUIFAV.INPUT_AREA.TX_CI;
                    BPCTCFAV.INPUT_AREA.TX_AMT = BPCUIFAV.INPUT_AREA.TX_AMT;
                    BPCTCFAV.INPUT_AREA.TX_AMT_OTHER = BPCUIFAV.INPUT_AREA.TX_AMT_OTHER;
                    BPCTCFAV.INPUT_AREA.TX_CNT = BPCUIFAV.INPUT_AREA.TX_CNT;
                    if (BPCUIFAV.INPUT_AREA.URG_RMT_FLG == 'Y') {
                        BPCTCFAV.INPUT_AREA.URG_RMT_FLG = BPCUIFAV.INPUT_AREA.URG_RMT_FLG;
                    }
                    S000_CALL_BPZFCFAV();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCTCFAV);
                    if (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0 
                        || BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0) {
                        WS_CAL_INFO.WS_CNT_FAV += 1;
                        if (BPCTCFAV.OUTPUT_AREA.FAV_AMT != 0) {
                            WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_AMT = BPCTCFAV.OUTPUT_AREA.FAV_AMT;
                        }
                        if (BPCTCFAV.OUTPUT_AREA.FAV_PCT != 0) {
                            WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_FAV_PCT = BPCTCFAV.OUTPUT_AREA.FAV_PCT;
                        }
                        WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT_FAV-1].WS_ADJ_TYP = BPCTCFAV.OUTPUT_AREA.ADJ_TYP;
                    }
                }
            }
        }
    }
    public void B030_01_CHECK_FAV_CODE_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFCOM.EXP_DATE);
        if (BPVFCOM.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
            || BPVFCOM.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
            Z_RET();
            if (pgmRtn) return;
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
        BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        WS_CAL_INFO.WS_LAS_AMT = 99999999999999.99;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else {
                }
                if (WS_CAL_INFO.WS_NOW_AMT < WS_CAL_INFO.WS_LAS_AMT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
                    BPCUIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_LAS_AMT);
                CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
            } else {
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE * ( 1 + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE * ( 1 - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.TX_AMT * WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100;
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                if (WS_CAL_INFO.WS_NOW_AMT < WS_CAL_INFO.WS_LAS_AMT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
                    BPCUIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
            }
        }
    }
    public void B040_10_CAL_INT_FAV_BY_L_CN() throws IOException,SQLException,Exception {
        BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        WS_CAL_INFO.WS_LAS_AMT = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else {
                }
                if (WS_CAL_INFO.WS_NOW_AMT > WS_CAL_INFO.WS_LAS_AMT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
                    BPCUIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
            } else {
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE * ( 1 + WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE * ( 1 - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100 );
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                    WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.TX_AMT * WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT / 100;
                    bigD = new BigDecimal(WS_CAL_INFO.WS_NOW_AMT);
                    WS_CAL_INFO.WS_NOW_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                }
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
                if (WS_CAL_INFO.WS_NOW_AMT > WS_CAL_INFO.WS_LAS_AMT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
                    BPCUIFAV.OUTPUT_AREA.ADJ_TYP = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP;
                    WS_CAL_INFO.WS_LAS_AMT = WS_CAL_INFO.WS_NOW_AMT;
                }
            }
        }
    }
    public void B040_15_CAL_INT_FAV_BY_P_CN() throws IOException,SQLException,Exception {
        BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = BPCUIFAV.OUTPUT_AREA.FAV_AMT - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                }
            } else {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0') {
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                } else if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = BPCUIFAV.OUTPUT_AREA.FAV_PCT - WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                }
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP != '2') {
                    WS_FAV_AMT_FINA = BPCUIFAV.INPUT_AREA.BASIC_FEE * BPCUIFAV.OUTPUT_AREA.FAV_PCT / 100;
                    bigD = new BigDecimal(WS_FAV_AMT_FINA);
                    WS_FAV_AMT_FINA = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
            }
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '0' 
                || WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '1') {
                WS_COMPARE_AMT_FLAG = 'Y';
            }
        }
        BPCUIFAV.OUTPUT_AREA.FAV_AMT += WS_FAV_AMT_FINA;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        CEP.TRC(SCCGWA, BPCUIFAV.INPUT_AREA.BASIC_FEE);
        CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, WS_COMPARE_AMT_FLAG);
        if (WS_COMPARE_AMT_FLAG == 'Y') {
            WS_CAL_INFO.WS_NOW_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE + BPCUIFAV.OUTPUT_AREA.FAV_AMT;
        }
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_NOW_AMT);
        if (BPCUIFAV.OUTPUT_AREA.FAV_AMT > 0) {
            BPCUIFAV.OUTPUT_AREA.ADJ_TYP = '0';
        } else {
            if (BPCUIFAV.OUTPUT_AREA.FAV_AMT < 0 - BPCUIFAV.INPUT_AREA.BASIC_FEE) {
                BPCUIFAV.OUTPUT_AREA.FAV_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE;
            } else {
                BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0 - BPCUIFAV.OUTPUT_AREA.FAV_AMT;
            }
            BPCUIFAV.OUTPUT_AREA.ADJ_TYP = '1';
        }
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_ADJ_TYP == '2') {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                    if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > WS_CAL_INFO.WS_NOW_AMT) {
                        BPCUIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                        BPCUIFAV.OUTPUT_AREA.ADJ_TYP = '2';
                    }
                } else {
                    if (BPCUIFAV.INPUT_AREA.TX_AMT * null / 100 > WS_CAL_INFO.WS_NOW_AMT) {
                        BPCUIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                        BPCUIFAV.OUTPUT_AREA.ADJ_TYP = '2';
                    }
                }
            }
        }
    }
    public void B040_05_CAL_INTEGRATE_FAV_BY_H() throws IOException,SQLException,Exception {
        BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_PCT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > BPCUIFAV.OUTPUT_AREA.FAV_AMT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                }
            } else {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT > BPCUIFAV.OUTPUT_AREA.FAV_PCT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                }
            }
        }
        WS_FAV_AMT_FINA = BPCUIFAV.INPUT_AREA.BASIC_FEE * BPCUIFAV.OUTPUT_AREA.FAV_PCT / 100;
        CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, WS_FAV_AMT_FINA);
        if (BPCUIFAV.OUTPUT_AREA.FAV_AMT > WS_FAV_AMT_FINA) {
            BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        } else {
            BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
        }
    }
    public void B040_10_CAL_INTEGRATE_FAV_BY_L() throws IOException,SQLException,Exception {
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT < BPCUIFAV.OUTPUT_AREA.FAV_AMT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
                }
            } else {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT < BPCUIFAV.OUTPUT_AREA.FAV_PCT) {
                    BPCUIFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
                }
            }
        }
        WS_FAV_AMT_FINA = BPCUIFAV.INPUT_AREA.BASIC_FEE * BPCUIFAV.OUTPUT_AREA.FAV_PCT / 100;
        CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, WS_FAV_AMT_FINA);
        if (BPCUIFAV.OUTPUT_AREA.FAV_AMT > WS_FAV_AMT_FINA) {
            BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCUIFAV.OUTPUT_AREA.FAV_AMT = WS_FAV_AMT_FINA;
        } else {
            BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        }
    }
    public void B040_15_CAL_INTEGRATE_FAV_BY_P() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_FAV);
        BPCUIFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= WS_CAL_INFO.WS_CNT_FAV; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT);
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT);
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_PCT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT > 0) {
                BPCUIFAV.OUTPUT_AREA.FAV_AMT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_AMT;
            } else {
                BPCUIFAV.OUTPUT_AREA.FAV_PCT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_FAV_PCT;
            }
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
            CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_PCT);
        }
        WS_FAV_AMT_FINA = BPCUIFAV.INPUT_AREA.BASIC_FEE * BPCUIFAV.OUTPUT_AREA.FAV_PCT / 100;
        CEP.TRC(SCCGWA, BPCUIFAV.INPUT_AREA.BASIC_FEE);
        CEP.TRC(SCCGWA, WS_FAV_AMT_FINA);
        CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA.FAV_AMT);
        BPCUIFAV.OUTPUT_AREA.FAV_AMT += WS_FAV_AMT_FINA;
        BPCUIFAV.OUTPUT_AREA.FAV_PCT = 0;
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
        BPCUIFAV.OUTPUT_AREA.ADJ_TYP = '0';
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUIFAV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B015_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPCUIFAV.INPUT_AREA.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        if (BPCTFBAS.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND, BPCUIFAV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFBAS.REG_TYPE.equalsIgnoreCase("00") 
            || BPRFBAS.REG_TYPE.trim().length() == 0) {
            WS_RGN_CODE = " ";
        } else {
            if (BPRFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                WS_RGN_CODE = "" + BPCPQORG.BRANCH_BR;
                JIBS_tmp_int = WS_RGN_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_RGN_CODE = "0" + WS_RGN_CODE;
            }
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_COM_FEE_INFO, BPCTFBAS);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
        if (BPCUIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            if (BPCUIFAV.OUTPUT_AREA.ADJ_TYP != '2') {
                WS_FEE_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE * BPCUIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_FEE_AMT);
                WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                WS_FEE_AMT = BPCUIFAV.INPUT_AREA.TX_AMT * BPCUIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_FEE_AMT);
                WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            WS_FEE_AMT = BPCUIFAV.OUTPUT_AREA.FAV_AMT;
        }
        if (BPCUIFAV.OUTPUT_AREA.ADJ_TYP == '0') {
            BPCUIFAV.OUTPUT_AREA.FEE_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE + WS_FEE_AMT;
        } else if (BPCUIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
            BPCUIFAV.OUTPUT_AREA.FEE_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE - WS_FEE_AMT;
        } else if (BPCUIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
            BPCUIFAV.OUTPUT_AREA.FEE_AMT = WS_FEE_AMT;
        }
        if (BPCUIFAV.OUTPUT_AREA.FAV_PCT == 0 
            && BPCUIFAV.OUTPUT_AREA.FAV_AMT == 0) {
            BPCUIFAV.OUTPUT_AREA.FEE_AMT = BPCUIFAV.INPUT_AREA.BASIC_FEE;
        }
        if (BPCUIFAV.OUTPUT_AREA.FEE_AMT < 0) {
            BPCUIFAV.OUTPUT_AREA.FEE_AMT = 0;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIFAV.OUTPUT_AREA);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUIFAV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCUIFAV = ");
            CEP.TRC(SCCGWA, BPCUIFAV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
