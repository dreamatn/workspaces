package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZUBKVL {
    boolean pgmRtn = false;
    String K_MMO_DD = "DD";
    short K_AC_STF_SET = 97;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_CCY_CNY = "156";
    short K_SEVEN_DAYS = 7;
    short WS_I = 0;
    short WS_J = 0;
    double WS_BAL_1 = 0;
    double WS_BAL_2 = 0;
    double WS_INT_BAL = 0;
    double WS_DEP_SPC_INT = 0;
    double WS_LST_INT_BAL = 0;
    double WS_INT = 0;
    double WS_ADJ_INT = 0;
    double WS_LST_ADJ_INT = 0;
    double WS_SPC_ADJ_INT = 0;
    double WS_INT_PUBLIC = 0;
    long WS_INT_TMP0 = 0;
    double WS_INT_TMP1 = 0;
    double WS_INT_TMP2 = 0;
    double WS_INT_TMP3 = 0;
    int WS_DAYS = 0;
    int WS_CAL_DAYS = 0;
    short WS_BASE_DAYS = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_FR_DATE = 0;
    int WS_TO_DATE = 0;
    double WS_TOT_DD = 0;
    double WS_TOT_ONE = 0;
    double WS_TOT_SEVEN = 0;
    double WS_TOT_CALL = 0;
    double WS_ONE_RATE = 0;
    double WS_DD_RATE = 0;
    double WS_SEVEN_RATE = 0;
    double WS_CALL_INT = 0;
    double WS_DD_INT = 0;
    int WS_BALH_END_DT_O = 0;
    double WS_TX_AMT = 0;
    long WS_TMP_AMT = 0;
    String WS_ERR_MSG = " ";
    DDZUBKVL_WS_TIR_INF WS_TIR_INF = new DDZUBKVL_WS_TIR_INF();
    char WS_EOF_DDTACCU_FLG = ' ';
    char WS_EOF_DDTBALH_FLG = ' ';
    char WS_FOUND_DDTINTB = ' ';
    char WS_DO_FLAG = ' ';
    char WS_SKIP_FLAG = ' ';
    char WS_SKIP_I_FLAG = ' ';
    char WS_SKIP_II_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRMST DDRMST = new DDRMST();
    DDRINTB DDRINTB = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    DDRACCH DDRACCH = new DDRACCH();
    DDRBALH DDRBALH = new DDRBALH();
    DDRBACK DDRBACK = new DDRBACK();
    DDRCCY DDRCCY = new DDRCCY();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCPCALL DDCPCALL = new DDCPCALL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDCITIRR DDCITIRR = new DDCITIRR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCRWBSP SCCRWBSP = new SCCRWBSP();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICACCU CICCACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBKPO SCCBKPO;
    SCCBATH SCCBATH;
    DDCUBKVL DDCUBKVL;
    public void MP(SCCGWA SCCGWA, DDCUBKVL DDCUBKVL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUBKVL = DDCUBKVL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUBKVL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDRACCU);
        IBS.init(SCCGWA, DDRACCH);
        IBS.init(SCCGWA, DDRBALH);
        IBS.init(SCCGWA, DDRINTB);
        IBS.init(SCCGWA, DDRBACK);
        IBS.init(SCCGWA, DDRCCY);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, " MAIN PROC");
        CEP.TRC(SCCGWA, " GWA-AC-DATE = ");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, " BATH-NEXT-AC-DATB = ");
        CEP.TRC(SCCGWA, SCCBATH.JPRM.NEXT_AC_DATB);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        R000_GET_ACO_AC();
        if (pgmRtn) return;
        T00_READ_DDTCCY();
        if (pgmRtn) return;
        R000_GET_PRD_INF_PROC();
        if (pgmRtn) return;
        T00_SELECT_DDTINTB();
        if (pgmRtn) return;
        T00_READ_DDTBACK();
        if (pgmRtn) return;
        DDRACCH.KEY.AC = DDCUBKVL.AC_NO;
        DDRACCH.KEY.CCY = DDCUBKVL.CCY;
        DDRACCH.KEY.CCY_TYPE = DDCUBKVL.CCY_TYPE;
        DDRACCH.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRACCH.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        WS_TX_AMT = DDRBACK.AMT;
        if (DDCUBKVL.CCY.equalsIgnoreCase(K_CCY_CNY)) {
            WS_TMP_AMT = (long) WS_TX_AMT;
            WS_TX_AMT = WS_TMP_AMT;
        }
        CEP.TRC(SCCGWA, WS_TX_AMT);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCUBKVL.CCY;
        S00_LINK_BPCQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("01")) {
            WS_BASE_DAYS = 360;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("02")) {
            WS_BASE_DAYS = 365;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("03")) {
            WS_BASE_DAYS = 366;
        }
        CEP.TRC(SCCGWA, WS_BASE_DAYS);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, "IN ONE INT POST PERIOD");
        B050_UPDATE_DDTBALH();
        if (pgmRtn) return;
        if (DDRINTB.STRT_TOT_DATE <= DDCUBKVL.VALUE_DATE) {
            B030_UPDATE_DDTINTB();
            if (pgmRtn) return;
        } else {
            B010_UPDATE_DDTACCU();
            if (pgmRtn) return;
            B030_UPDATE_DDTINTB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            CEP.TRC(SCCGWA, WS_SPC_ADJ_INT);
            DDRINTB.LST_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            WS_INT_PUBLIC = WS_ADJ_INT;
            R000_STARDARD_AMT_PROC();
            if (pgmRtn) return;
            WS_ADJ_INT = WS_INT_PUBLIC;
            WS_INT_PUBLIC = WS_SPC_ADJ_INT;
            R000_STARDARD_AMT_PROC();
            if (pgmRtn) return;
            WS_SPC_ADJ_INT = WS_INT_PUBLIC;
            CEP.TRC(SCCGWA, "WS-ADJ-INT = ");
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            CEP.TRC(SCCGWA, "WS-SPC-ADJ-INT = ");
            CEP.TRC(SCCGWA, WS_SPC_ADJ_INT);
            if (WS_SPC_ADJ_INT != 0) {
                DDRINTB.DEP_SPC_ADJ_INT += WS_SPC_ADJ_INT;
            }
            if (WS_ADJ_INT != 0) {
                DDRINTB.DEP_ADJ_INT += WS_ADJ_INT;
            }
            CEP.TRC(SCCGWA, "INTB-DEP-SPC-ADJ-INT = ");
            CEP.TRC(SCCGWA, DDRINTB.DEP_SPC_ADJ_INT);
            CEP.TRC(SCCGWA, "INTB-DEP-ADJ-INT = ");
            CEP.TRC(SCCGWA, DDRINTB.DEP_ADJ_INT);
        }
        if (WS_FOUND_DDTINTB == 'Y') {
            T000_UPDATE_DDTINTB();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUBKVL.AC_NO);
        CEP.TRC(SCCGWA, DDCUBKVL.CCY);
        CEP.TRC(SCCGWA, DDCUBKVL.AC_DATE);
        CEP.TRC(SCCGWA, DDCUBKVL.VALUE_DATE);
        CEP.TRC(SCCGWA, DDCUBKVL.TR_JRN);
        if (DDCUBKVL.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUBKVL.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUBKVL.AC_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUBKVL.VALUE_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_UPDATE_DDTACCU() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-UPDATE-DDTACCU START");
        T000_STARTBR_DDTACCU();
        if (pgmRtn) return;
        T000_READNEXT_DDTACCU();
        if (pgmRtn) return;
        while (WS_EOF_DDTACCU_FLG != 'Y' 
            && DDRACCU.KEY.END_DATE > DDCUBKVL.VALUE_DATE) {
            if (DDRACCU.KEY.STR_DATE < DDRACCU.KEY.END_DATE) {
                if (DDCUBKVL.VALUE_DATE > DDRACCU.KEY.STR_DATE) {
                    WS_FR_DATE = DDCUBKVL.VALUE_DATE;
                } else {
                    WS_FR_DATE = DDRACCU.KEY.STR_DATE;
                }
                WS_TO_DATE = DDRACCU.KEY.END_DATE;
                if (DDRACCU.INT_STS == 'N') {
                    B010_01_UNSTL_INT_PROC();
                    if (pgmRtn) return;
                } else {
                    B010_03_STL_INT_PROC();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_DDTACCU();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B010-UPDATE-DDTACCU START");
    }
    public void B010_01_UNSTL_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-01-UNSTL-INT-PROC START");
        if (DDRACCU.CAL_INT_MTH == '1') {
            B010_01_3_ACCU_INT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "B010-01-UNSTL-INT-PROC END");
    }
    public void B010_01_3_ACCU_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-01-3-ACCU-INT START");
        CEP.TRC(SCCGWA, "ACCU INT PROC");
        WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT = 0;
        if (DDRACCU.TIR_TYPE == 'A') {
            CEP.TRC(SCCGWA, "TIR BY AMT");
            CEP.TRC(SCCGWA, "ACCU-TYPE:");
            CEP.TRC(SCCGWA, DDRACCU.KEY.TYPE);
            CEP.TRC(SCCGWA, "ACCU-RATE:");
            CEP.TRC(SCCGWA, DDRACCU.RATE);
            CEP.TRC(SCCGWA, "ACCU-STR-DATE:");
            CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
            CEP.TRC(SCCGWA, "ACCU-END-DATE:");
            CEP.TRC(SCCGWA, DDRACCU.KEY.END_DATE);
            CEP.TRC(SCCGWA, "ACCU-TOT:");
            CEP.TRC(SCCGWA, DDRACCU.TOT);
            CEP.TRC(SCCGWA, "ACCU-INT-STS:");
            CEP.TRC(SCCGWA, DDRACCU.INT_STS);
            if (DDRACCU.KEY.TYPE == ' ') WS_I = 0;
            else WS_I = Short.parseShort(""+DDRACCU.KEY.TYPE);
            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT = DDRACCU.TIR_AMT;
            if (DDVMPRD.VAL.CAL_DINT_METH == '1') {
                WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_RATE = DDRACCU.RATE;
            }
            WS_TIR_INF.WS_AGSP_FLG = DDRACCU.AGSP_FLG;
            WS_TIR_INF.WS_TIR_TYPE = DDRACCU.TIR_TYPE;
            WS_TIR_INF.WS_CAL_INT_MTH = DDRACCU.CAL_INT_MTH;
            WS_TIR_INF.WS_TAX_RATE = DDRACCU.TAX_RATE;
            WS_TIR_INF.WS_INT_STS = DDRACCU.INT_STS;
            WS_FR_DATE = DDRACCU.KEY.STR_DATE;
            WS_TO_DATE = DDRACCU.KEY.END_DATE;
            T000_STRBR_BALH_BY_DATE();
            if (pgmRtn) return;
            T000_READNEXT_BALH_BY_DATE();
            if (pgmRtn) return;
            while (WS_EOF_DDTBALH_FLG != 'Y' 
                && DDRBALH.END_DATE >= WS_FR_DATE) {
                if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                    WS_STR_DATE = WS_FR_DATE;
                } else {
                    WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                }
                if (DDRBALH.END_DATE < WS_TO_DATE) {
                    WS_END_DATE = DDRBALH.END_DATE;
                } else {
                    WS_END_DATE = WS_TO_DATE;
                }
                R000_GET_INT_DAYS();
                if (pgmRtn) return;
                if (DDRACCU.AGSP_FLG == 'A') {
                    if (WS_I == 1) {
                        if (DDRBALH.BAL <= DDRACCU.TIR_AMT) {
                            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRACCU.TIR_AMT 
                            && DDRBALH.BAL > WS_TIR_INF.WS_TIR_DETAIL[WS_I - 1-1].WS_TIR_AMT) {
                            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    }
                } else {
                    WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT = DDRACCU.TIR_AMT;
                    if (DDRBALH.BAL <= DDRACCU.TIR_AMT) {
                        if (WS_I == 1) {
                            WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                        if (WS_I > 1) {
                            if (DDRBALH.BAL > WS_TIR_INF.WS_TIR_DETAIL[WS_I - 1-1].WS_TIR_AMT) {
                                WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + ( DDRBALH.BAL - WS_TIR_INF.WS_TIR_DETAIL[WS_I - 1-1].WS_TIR_AMT ) * WS_DAYS;
                            }
                        }
                    } else {
                        WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT * WS_DAYS;
                    }
                }
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            T000_ENDBR_BALH_BY_DATE();
            if (pgmRtn) return;
            DDRACCU.TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT;
            DDRACCU.INT = DDRACCU.TOT * WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_RATE / WS_BASE_DAYS / 100;
            DDRACCU.AFT_ADJ_INT = DDRACCU.INT;
            DDRACCU.TAX = DDRACCU.INT * DDRACCU.TAX_RATE / 100;
            T000_UPDATE_DDTACCU();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "TIR BY CLS OR GRP OR OTHER EXCEPT AMT");
            CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
            CEP.TRC(SCCGWA, DDCUBKVL.VALUE_DATE);
            if (DDRACCU.KEY.STR_DATE > DDCUBKVL.VALUE_DATE) {
                WS_STR_DATE = DDRACCU.KEY.STR_DATE;
            } else {
                WS_STR_DATE = DDCUBKVL.VALUE_DATE;
            }
            WS_END_DATE = DDRACCU.KEY.END_DATE;
            CEP.TRC(SCCGWA, WS_STR_DATE);
            CEP.TRC(SCCGWA, WS_END_DATE);
            R000_GET_INT_DAYS();
            if (pgmRtn) return;
            DDRACCU.TOT = DDRACCU.TOT + WS_TX_AMT * WS_DAYS;
            DDRACCU.INT = DDRACCU.TOT * DDRACCU.RATE / WS_BASE_DAYS / 100;
            DDRACCU.AFT_ADJ_INT = DDRACCU.INT;
            DDRACCU.TAX = DDRACCU.INT * DDRACCU.TAX_RATE / 100;
            T000_UPDATE_DDTACCU();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "B010-01-3-ACCU-INT END");
    }
    public void B010_03_STL_INT_PROC() throws IOException,SQLException,Exception {
        if (DDRACCU.CAL_INT_MTH == '2') {
            B010_03_1_BALH_INT();
            if (pgmRtn) return;
        } else {
            B010_03_3_ACCU_INT();
            if (pgmRtn) return;
        }
    }
    public void B010_03_1_BALH_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-03-1-BALH-INT START");
        CEP.TRC(SCCGWA, "BALH INT PROC");
        CEP.TRC(SCCGWA, DDRACCU.KEY.TYPE);
        CEP.TRC(SCCGWA, DDRACCU.RATE);
        CEP.TRC(SCCGWA, DDRACCU.TOT);
        CEP.TRC(SCCGWA, DDRACCU.INT_STS);
        WS_TIR_INF.WS_AGSP_FLG = DDRACCU.AGSP_FLG;
        WS_TIR_INF.WS_TIR_TYPE = DDRACCU.TIR_TYPE;
        WS_TIR_INF.WS_CAL_INT_MTH = DDRACCU.CAL_INT_MTH;
        WS_TIR_INF.WS_TAX_RATE = DDRACCU.TAX_RATE;
        WS_TIR_INF.WS_INT_STS = DDRACCU.INT_STS;
        WS_FR_DATE = DDRACCU.KEY.STR_DATE;
        WS_TO_DATE = DDRACCU.KEY.END_DATE;
        if (DDRMST.CI_TYP == '1') {
            R000_CALL_INT_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "B010-03-1-BALH-INT END");
    }
    public void B010_03_3_ACCU_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B010-03-3-ACCU-INT START");
        CEP.TRC(SCCGWA, "ACCU INT PROC");
        WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT = 0;
        if (DDRACCU.TIR_TYPE == 'A') {
            CEP.TRC(SCCGWA, "TIR BY AMT");
            if (DDRACCU.KEY.TYPE == ' ') WS_I = 0;
            else WS_I = Short.parseShort(""+DDRACCU.KEY.TYPE);
            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT = DDRACCU.TIR_AMT;
            if (DDVMPRD.VAL.CAL_DINT_METH == '1') {
                WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_RATE = DDRACCU.RATE;
            }
            WS_TIR_INF.WS_AGSP_FLG = DDRACCU.AGSP_FLG;
            WS_TIR_INF.WS_TIR_TYPE = DDRACCU.TIR_TYPE;
            WS_TIR_INF.WS_CAL_INT_MTH = DDRACCU.CAL_INT_MTH;
            WS_TIR_INF.WS_TAX_RATE = DDRACCU.TAX_RATE;
            WS_TIR_INF.WS_INT_STS = DDRACCU.INT_STS;
            WS_FR_DATE = DDRACCU.KEY.STR_DATE;
            WS_TO_DATE = DDRACCU.KEY.END_DATE;
            T000_STRBR_BALH_BY_DATE();
            if (pgmRtn) return;
            T000_READNEXT_BALH_BY_DATE();
            if (pgmRtn) return;
            while (WS_EOF_DDTBALH_FLG != 'Y' 
                && DDRBALH.END_DATE >= WS_FR_DATE) {
                if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                    WS_STR_DATE = WS_FR_DATE;
                } else {
                    WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                }
                if (DDRBALH.END_DATE < WS_TO_DATE) {
                    WS_END_DATE = DDRBALH.END_DATE;
                } else {
                    WS_END_DATE = WS_TO_DATE;
                }
                R000_GET_INT_DAYS();
                if (pgmRtn) return;
                if (DDRACCU.AGSP_FLG == 'A') {
                    if (WS_I == 1) {
                        if (DDRBALH.BAL <= DDRACCU.TIR_AMT) {
                            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRACCU.TIR_AMT 
                            && DDRBALH.BAL > WS_TIR_INF.WS_TIR_DETAIL[WS_I - 1-1].WS_TIR_AMT) {
                            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    }
                } else {
                    WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT = DDRACCU.TIR_AMT;
                    if (DDRBALH.BAL <= DDRACCU.TIR_AMT) {
                        if (WS_I == 1) {
                            WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                        if (WS_I > 1) {
                            if (DDRBALH.BAL > WS_TIR_INF.WS_TIR_DETAIL[WS_I - 1-1].WS_TIR_AMT) {
                                WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + ( DDRBALH.BAL - WS_TIR_INF.WS_TIR_DETAIL[WS_I - 1-1].WS_TIR_AMT ) * WS_DAYS;
                            }
                        }
                    } else {
                        WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT + WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT * WS_DAYS;
                    }
                    CEP.TRC(SCCGWA, "WS-TIR-TOT(WS-I) = ");
                    CEP.TRC(SCCGWA, WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT);
                    CEP.TRC(SCCGWA, "WS-I = ");
                    CEP.TRC(SCCGWA, WS_I);
                }
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            T000_ENDBR_BALH_BY_DATE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "WS-TIR-TOT(WS-I) = ");
            CEP.TRC(SCCGWA, WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT);
            WS_INT = WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_TOT * WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_RATE / WS_BASE_DAYS / 100;
            CEP.TRC(SCCGWA, "WS-INT = ");
            CEP.TRC(SCCGWA, WS_INT);
            CEP.TRC(SCCGWA, "ACCU-AFT-ADJ-INT = ");
            CEP.TRC(SCCGWA, DDRACCU.AFT_ADJ_INT);
            CEP.TRC(SCCGWA, "WS-ADJ-INT = ");
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            CEP.TRC(SCCGWA, "ACCU-INT = ");
            CEP.TRC(SCCGWA, DDRACCU.INT);
            WS_ADJ_INT = WS_ADJ_INT + WS_INT - DDRACCU.AFT_ADJ_INT;
            CEP.TRC(SCCGWA, "WS-ADJ-INT = ");
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            DDRACCU.AFT_ADJ_INT = WS_INT;
            DDRACCU.TAX = DDRACCU.AFT_ADJ_INT * DDRACCU.TAX_RATE / 100;
            T000_UPDATE_DDTACCU();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "TIR BY CLS OR GRP OR OTHER EXCEPT AMT");
            CEP.TRC(SCCGWA, "ACCU-STR-DATE:");
            CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
            CEP.TRC(SCCGWA, "BKVL-VALUE-DATE:");
            CEP.TRC(SCCGWA, DDCUBKVL.VALUE_DATE);
            if (DDRACCU.KEY.TYPE == '1') {
                if (DDRACCU.KEY.STR_DATE > DDCUBKVL.VALUE_DATE) {
                    WS_STR_DATE = DDRACCU.KEY.STR_DATE;
                } else {
                    WS_STR_DATE = DDCUBKVL.VALUE_DATE;
                }
                WS_END_DATE = DDRACCU.KEY.END_DATE;
                CEP.TRC(SCCGWA, "WS-STR-DATE:");
                CEP.TRC(SCCGWA, WS_STR_DATE);
                CEP.TRC(SCCGWA, "WS-END-DATE:");
                CEP.TRC(SCCGWA, WS_END_DATE);
                R000_GET_INT_DAYS();
                if (pgmRtn) return;
                WS_INT = WS_TX_AMT * WS_DAYS * DDRACCU.RATE / WS_BASE_DAYS / 100;
                CEP.TRC(SCCGWA, "WS-INT:");
                CEP.TRC(SCCGWA, WS_INT);
                DDRACCU.AFT_ADJ_INT = DDRACCU.AFT_ADJ_INT + WS_INT;
                CEP.TRC(SCCGWA, "ACCU-AFT-ADJ-INT:");
                CEP.TRC(SCCGWA, DDRACCU.AFT_ADJ_INT);
                CEP.TRC(SCCGWA, "WS-ADJ-INT:");
                CEP.TRC(SCCGWA, WS_ADJ_INT);
                WS_ADJ_INT += WS_INT;
                CEP.TRC(SCCGWA, "WS-ADJ-INT:");
                CEP.TRC(SCCGWA, WS_ADJ_INT);
                DDRACCU.TAX = DDRACCU.AFT_ADJ_INT * DDRACCU.TAX_RATE / 100;
                T000_UPDATE_DDTACCU();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "B010-03-3-ACCU-INT END");
    }
    public void B030_UPDATE_DDTINTB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-UPDATE-DDTINTB START");
        T000_READUPD_DDTINTB();
        if (pgmRtn) return;
        if (WS_FOUND_DDTINTB == 'Y') {
            CEP.TRC(SCCGWA, "INTB EXIT");
            if (DDRINTB.CAL_INT_MTH == '1') {
                CEP.TRC(SCCGWA, DDRINTB.LST_TOT_DATE);
                CEP.TRC(SCCGWA, DDCUBKVL.VALUE_DATE);
                if (DDRINTB.LST_TOT_DATE != DDCUBKVL.VALUE_DATE) {
                    B030_03_ACCU_INT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, "B030-UPDATE-DDTINTB END");
    }
    public void B030_03_ACCU_INT_PROC() throws IOException,SQLException,Exception {
        WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT = 0;
        WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT = 0;
        CEP.TRC(SCCGWA, "B030-03-ACCU-INT-PROC START ");
        CEP.TRC(SCCGWA, "INTB - ACCU INT PROC");
        CEP.TRC(SCCGWA, DDRINTB.TIR_TYPE);
        if (DDRINTB.TIR_TYPE == 'A') {
            WS_FR_DATE = DDRINTB.STRT_TOT_DATE;
            WS_TO_DATE = DDRINTB.END_TOT_DATE;
            if (DDRINTB.TIR_AMT1 > 0) {
                T000_STRBR_BALH_BY_DATE();
                if (pgmRtn) return;
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
                while (WS_EOF_DDTBALH_FLG != 'Y' 
                    && DDRBALH.END_DATE > WS_FR_DATE) {
                    if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                        WS_STR_DATE = WS_FR_DATE;
                    } else {
                        WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                    }
                    if (DDRBALH.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        WS_END_DATE = DDRBALH.END_DATE;
                    }
                    R000_GET_INT_DAYS();
                    if (pgmRtn) return;
                    if (DDRINTB.AGSP_FLG == 'A') {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT1) {
                            WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT1) {
                            WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        } else {
                            WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT + DDRINTB.TIR_AMT1 * WS_DAYS;
                        }
                    }
                    T000_READNEXT_BALH_BY_DATE();
                    if (pgmRtn) return;
                }
                T000_ENDBR_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            WS_FR_DATE = DDRINTB.STRT_TOT_DATE;
            WS_TO_DATE = DDRINTB.END_TOT_DATE;
            if (DDRINTB.TIR_AMT2 > 0) {
                T000_STRBR_BALH_BY_DATE();
                if (pgmRtn) return;
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
                while (WS_EOF_DDTBALH_FLG != 'Y' 
                    && DDRBALH.END_DATE > WS_FR_DATE) {
                    if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                        WS_STR_DATE = WS_FR_DATE;
                    } else {
                        WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                    }
                    if (DDRBALH.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        WS_END_DATE = DDRBALH.END_DATE;
                    }
                    R000_GET_INT_DAYS();
                    if (pgmRtn) return;
                    if (DDRINTB.AGSP_FLG == 'A') {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT2 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT1) {
                            WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT2 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT1) {
                            WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT + ( DDRBALH.BAL - DDRINTB.TIR_AMT1 ) * WS_DAYS;
                        }
                        if (DDRBALH.BAL > DDRINTB.TIR_AMT2) {
                            WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT + DDRINTB.TIR_AMT2 * WS_DAYS;
                        }
                    }
                    T000_READNEXT_BALH_BY_DATE();
                    if (pgmRtn) return;
                }
                T000_ENDBR_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            WS_FR_DATE = DDRINTB.STRT_TOT_DATE;
            WS_TO_DATE = DDRINTB.END_TOT_DATE;
            if (DDRINTB.TIR_AMT3 > 0) {
                T000_STRBR_BALH_BY_DATE();
                if (pgmRtn) return;
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
                while (WS_EOF_DDTBALH_FLG != 'Y' 
                    && DDRBALH.END_DATE > WS_FR_DATE) {
                    if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                        WS_STR_DATE = WS_FR_DATE;
                    } else {
                        WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                    }
                    if (DDRBALH.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        WS_END_DATE = DDRBALH.END_DATE;
                    }
                    R000_GET_INT_DAYS();
                    if (pgmRtn) return;
                    if (DDRINTB.AGSP_FLG == 'A') {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT3 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT2) {
                            WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT3 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT2) {
                            WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT + ( DDRBALH.BAL - DDRINTB.TIR_AMT2 ) * WS_DAYS;
                        }
                        if (DDRBALH.BAL > DDRINTB.TIR_AMT3) {
                            WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT + DDRINTB.TIR_AMT3 * WS_DAYS;
                        }
                    }
                    T000_READNEXT_BALH_BY_DATE();
                    if (pgmRtn) return;
                }
                T000_ENDBR_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            WS_FR_DATE = DDRINTB.STRT_TOT_DATE;
            WS_TO_DATE = DDRINTB.END_TOT_DATE;
            if (DDRINTB.TIR_AMT4 > 0) {
                T000_STRBR_BALH_BY_DATE();
                if (pgmRtn) return;
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
                while (WS_EOF_DDTBALH_FLG != 'Y' 
                    && DDRBALH.END_DATE > WS_FR_DATE) {
                    if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                        WS_STR_DATE = WS_FR_DATE;
                    } else {
                        WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                    }
                    if (DDRBALH.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        WS_END_DATE = DDRBALH.END_DATE;
                    }
                    R000_GET_INT_DAYS();
                    if (pgmRtn) return;
                    if (DDRINTB.AGSP_FLG == 'A') {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT4 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT3) {
                            WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT4 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT3) {
                            WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT + ( DDRBALH.BAL - DDRINTB.TIR_AMT3 ) * WS_DAYS;
                        }
                        if (DDRBALH.BAL > DDRINTB.TIR_AMT4) {
                            WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT + DDRINTB.TIR_AMT4 * WS_DAYS;
                        }
                    }
                    T000_READNEXT_BALH_BY_DATE();
                    if (pgmRtn) return;
                }
                T000_ENDBR_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            WS_FR_DATE = DDRINTB.STRT_TOT_DATE;
            WS_TO_DATE = DDRINTB.END_TOT_DATE;
            if (DDRINTB.TIR_AMT5 > 0) {
                T000_STRBR_BALH_BY_DATE();
                if (pgmRtn) return;
                T000_READNEXT_BALH_BY_DATE();
                if (pgmRtn) return;
                while (WS_EOF_DDTBALH_FLG != 'Y' 
                    && DDRBALH.END_DATE > WS_FR_DATE) {
                    if (DDRBALH.KEY.STR_DATE < WS_FR_DATE) {
                        WS_STR_DATE = WS_FR_DATE;
                    } else {
                        WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                    }
                    if (DDRBALH.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        WS_END_DATE = DDRBALH.END_DATE;
                    }
                    R000_GET_INT_DAYS();
                    if (pgmRtn) return;
                    if (DDRINTB.AGSP_FLG == 'A') {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT5 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT4) {
                            WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT + DDRBALH.BAL * WS_DAYS;
                        }
                    } else {
                        if (DDRBALH.BAL <= DDRINTB.TIR_AMT5 
                            && DDRBALH.BAL > DDRINTB.TIR_AMT4) {
                            WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT = WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT + ( DDRBALH.BAL - DDRINTB.TIR_AMT4 ) * WS_DAYS;
                        }
                    }
                    T000_READNEXT_BALH_BY_DATE();
                    if (pgmRtn) return;
                }
                T000_ENDBR_BALH_BY_DATE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "TIR BY AMT");
            DDRINTB.DEP_TOT1 = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_TOT;
            DDRINTB.DEP_TOT2 = WS_TIR_INF.WS_TIR_DETAIL[2-1].WS_TIR_TOT;
            DDRINTB.DEP_TOT3 = WS_TIR_INF.WS_TIR_DETAIL[3-1].WS_TIR_TOT;
            DDRINTB.DEP_TOT4 = WS_TIR_INF.WS_TIR_DETAIL[4-1].WS_TIR_TOT;
            DDRINTB.DEP_TOT5 = WS_TIR_INF.WS_TIR_DETAIL[5-1].WS_TIR_TOT;
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            WS_LST_ADJ_INT = DDRINTB.DEP_ADJ_INT;
        } else {
            CEP.TRC(SCCGWA, "TIR BY CLS OR GRP OR OTHER EXCEPT AMT");
            CEP.TRC(SCCGWA, DDRINTB.STRT_TOT_DATE);
            CEP.TRC(SCCGWA, DDRINTB.LST_TOT_DATE);
            CEP.TRC(SCCGWA, DDCUBKVL.VALUE_DATE);
            if (DDRINTB.STRT_TOT_DATE > DDCUBKVL.VALUE_DATE) {
                WS_STR_DATE = DDRINTB.STRT_TOT_DATE;
            } else {
                WS_STR_DATE = DDCUBKVL.VALUE_DATE;
            }
            if (DDRINTB.LST_TOT_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                WS_END_DATE = DDRINTB.LST_TOT_DATE;
            }
            if (WS_STR_DATE == WS_END_DATE) {
                WS_INT_BAL = DDRCCY.LAST_DAYEND_BAL;
                WS_STR_DATE = DDRINTB.LST_TOT_DATE;
                DDRINTB.LST_TOT_DATE = DDCUBKVL.VALUE_DATE;
            } else {
                WS_INT_BAL = WS_TX_AMT;
            }
            if (DDCUBKVL.CCY.equalsIgnoreCase(K_CCY_CNY)) {
                WS_TMP_AMT = (long) WS_INT_BAL;
                WS_INT_BAL = WS_TMP_AMT;
            }
            CEP.TRC(SCCGWA, WS_INT_BAL);
            CEP.TRC(SCCGWA, WS_STR_DATE);
            CEP.TRC(SCCGWA, WS_END_DATE);
            CEP.TRC(SCCGWA, DDRINTB.LST_TOT_DATE);
            R000_GET_INT_DAYS();
            if (pgmRtn) return;
            DDRINTB.DEP_TOT1 = DDRINTB.DEP_TOT1 + WS_INT_BAL * WS_DAYS;
            CEP.TRC(SCCGWA, DDRINTB.DEP_TOT1);
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            CEP.TRC(SCCGWA, WS_SPC_ADJ_INT);
            CEP.TRC(SCCGWA, WS_BASE_DAYS);
            WS_LST_ADJ_INT = 0;
            CEP.TRC(SCCGWA, "WS-ADJ-INT=");
            CEP.TRC(SCCGWA, WS_ADJ_INT);
            CEP.TRC(SCCGWA, "INTB-DEP-ADJ-INT = ");
            CEP.TRC(SCCGWA, DDRINTB.DEP_ADJ_INT);
        }
        CEP.TRC(SCCGWA, "B030-03-ACCU-INT-PROC END ");
    }
    public void B050_UPDATE_DDTBALH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B050-UPDATE-DDTBALH START");
        WS_FR_DATE = DDCUBKVL.VALUE_DATE;
        WS_TO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_STRBR_BALH_BY_DATE();
        if (pgmRtn) return;
        T000_READNEXT_BALH_BY_DATE();
        if (pgmRtn) return;
        while (WS_EOF_DDTBALH_FLG != 'Y' 
            && DDRBALH.END_DATE >= DDCUBKVL.VALUE_DATE) {
            WS_SKIP_FLAG = 'N';
            if (DDRBALH.KEY.STR_DATE < DDCUBKVL.VALUE_DATE) {
                CEP.TRC(SCCGWA, DDRBALH.BAL);
                WS_STR_DATE = DDCUBKVL.VALUE_DATE;
                WS_END_DATE = DDRBALH.END_DATE;
                DDRBALH.END_DATE = WS_STR_DATE;
                WS_LST_INT_BAL = DDRBALH.BAL;
                CEP.TRC(SCCGWA, WS_END_DATE);
                CEP.TRC(SCCGWA, WS_STR_DATE);
                T000_UPDATE_DDTBALH();
                if (pgmRtn) return;
                DDRBALH.KEY.STR_DATE = WS_STR_DATE;
                DDRBALH.END_DATE = WS_END_DATE;
                DDRBALH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRBALH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRBALH.BAL = DDRBALH.BAL + DDRBACK.AMT;
                if (CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR.equalsIgnoreCase(K_CCY_CNY)) {
                    WS_TMP_AMT = (long) DDRBALH.BAL;
                    DDRBALH.BAL = WS_TMP_AMT;
                    WS_INT_BAL = WS_TMP_AMT;
                }
                CEP.TRC(SCCGWA, DDRBALH.END_DATE);
                CEP.TRC(SCCGWA, DDRBALH.KEY.STR_DATE);
                CEP.TRC(SCCGWA, DDRBALH.BAL);
                T000_WRITE_DDTBALH();
                if (pgmRtn) return;
                WS_SKIP_FLAG = 'Y';
            }
            if (DDRBALH.KEY.STR_DATE >= DDCUBKVL.VALUE_DATE 
                && WS_SKIP_FLAG == 'N') {
                CEP.TRC(SCCGWA, DDRBALH.END_DATE);
                CEP.TRC(SCCGWA, DDRBALH.KEY.STR_DATE);
                WS_STR_DATE = DDRBALH.KEY.STR_DATE;
                WS_LST_INT_BAL = DDRBALH.BAL;
                DDRBALH.BAL = DDRBALH.BAL + DDRBACK.AMT;
                if (CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR.equalsIgnoreCase(K_CCY_CNY)) {
                    WS_TMP_AMT = (long) DDRBALH.BAL;
                    DDRBALH.BAL = WS_TMP_AMT;
                    WS_INT_BAL = WS_TMP_AMT;
                }
                CEP.TRC(SCCGWA, DDRBALH.BAL);
                T000_UPDATE_DDTBALH();
                if (pgmRtn) return;
            }
            T000_READNEXT_BALH_BY_DATE();
            if (pgmRtn) return;
        }
        T000_ENDBR_BALH_BY_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B050-UPDATE-DDTBALH END ");
    }
    public void B210_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B210-GEN-VCH-PROC START ");
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "IA";
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUBKVL.CCY;
        if (BPCPOEWA.DATA.FILLER == null) BPCPOEWA.DATA.FILLER = "";
        JIBS_tmp_int = BPCPOEWA.DATA.FILLER.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCPOEWA.DATA.FILLER += " ";
        JIBS_tmp_str[0] = "" + DDCUBKVL.CCY_TYPE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPOEWA.DATA.FILLER = JIBS_tmp_str[0] + BPCPOEWA.DATA.FILLER.substring(1);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPCPOEWA.DATA.VALUE_DATE = DDCUBKVL.VALUE_DATE;
        } else {
            BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        if (WS_ADJ_INT > 0) {
            BPCPOEWA.DATA.AMT_INFO[4-1].AMT = WS_ADJ_INT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[14-1].AMT = 0 - WS_ADJ_INT;
        }
        if (WS_SPC_ADJ_INT > 0) {
            BPCPOEWA.DATA.AMT_INFO[10-1].AMT = WS_SPC_ADJ_INT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[16-1].AMT = 0 - WS_SPC_ADJ_INT;
        }
        BPCPOEWA.DATA.CI_NO = CICCACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, "POEWA-AMT(2) = ");
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        CEP.TRC(SCCGWA, "POEWA-AMT(13) = ");
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[13-1].AMT);
        CEP.TRC(SCCGWA, "POEWA-AMT(4) = ");
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
        CEP.TRC(SCCGWA, "POEWA-AMT(14) = ");
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[14-1].AMT);
        CEP.TRC(SCCGWA, "POEWA-AMT(10) = ");
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[10-1].AMT);
        CEP.TRC(SCCGWA, "POEWA-AMT(16) = ");
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[16-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.REF_NO);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B210-GEN-VCH-PROC END ");
    }
    public void R000_GET_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCUBKVL.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCUBKVL.CCY;
        CICQACAC.DATA.CR_FLG = DDCUBKVL.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void R000_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCUBKVL.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDVMRAT");
        CEP.TRC(SCCGWA, DDVMRAT);
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_AMT = DDVMRAT.VAL.TIER[WS_I-1].TAMT;
            CEP.TRC(SCCGWA, "MRAT-TAMT(WS-I)=");
            CEP.TRC(SCCGWA, DDVMRAT.VAL.TIER[WS_I-1].TAMT);
            CEP.TRC(SCCGWA, "MRAT-TGRP(WS-I)=");
            CEP.TRC(SCCGWA, DDVMRAT.VAL.TIER[WS_I-1].TGRP);
            CEP.TRC(SCCGWA, "MRAT-TCLS(WS-I)=");
            CEP.TRC(SCCGWA, DDVMRAT.VAL.TIER[WS_I-1].TCLS);
            if (DDVMRAT.VAL.TIER[WS_I-1].TAMT != 0 
                || DDVMRAT.VAL.TIER[WS_I-1].TGRP.trim().length() > 0 
                || DDVMRAT.VAL.TIER[WS_I-1].TCLS.trim().length() > 0) {
                IBS.init(SCCGWA, DDCITIRR);
                for (WS_J = 1; WS_J <= 5; WS_J += 1) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVMRAT.VAL.TIER[WS_I-1].TIER_IR[WS_J-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCITIRR.RBASE_INFO[WS_J-1]);
                }
                DDCITIRR.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                DDCITIRR.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
                DDCITIRR.RUL_CD = DDVMRAT.VAL.TIER[WS_I-1].TGRP;
                DDCITIRR.CCY = DDVMRAT.KEY.CCY;
                DDCITIRR.TYPE = DDVMRAT.VAL.TIER[WS_I-1].SPR_TYPE;
                DDCITIRR.HL_FLAG = DDVMRAT.VAL.TIER[WS_I-1].HL_FLG;
                DDCITIRR.MAX_RATE = DDVMRAT.VAL.TIER[WS_I-1].MAX_RATE;
                DDCITIRR.MIN_RATE = DDVMRAT.VAL.TIER[WS_I-1].MIN_RATE;
                DDCITIRR.FIX_RATE = DDVMRAT.VAL.TIER[WS_I-1].FIX_RATE;
                DDCITIRR.BR = DDRMST.OWNER_BRDP;
                if ((DDCITIRR.RBASE_INFO[1-1].BASE.trim().length() > 0) 
                    || (DDCITIRR.FIX_RATE != 0)) {
                    S000_CALL_DDZITIRR();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DDCITIRR.COMPUTED_RATE);
                WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_RATE = DDCITIRR.COMPUTED_RATE;
            } else {
                WS_TIR_INF.WS_TIR_DETAIL[WS_I-1].WS_TIR_RATE = 0;
            }
        }
        WS_DD_RATE = WS_TIR_INF.WS_TIR_DETAIL[1-1].WS_TIR_RATE;
        CEP.TRC(SCCGWA, "WS-DD-RATE=");
        CEP.TRC(SCCGWA, WS_DD_RATE);
    }
    public void R000_GET_INT_DAYS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "R000-GET-INT-DAYS START ");
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_STR_DATE;
        SCCCLDT.DATE2 = WS_END_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, "R000-GET-INT-DAYS END ");
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCACCU);
        CICCACCU.DATA.AGR_NO = DDCUBKVL.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void R000_STARDARD_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, WS_INT_PUBLIC);
        if (WS_INT_PUBLIC == 0) {
            WS_INT_PUBLIC = 0;
        } else {
            if (BPCQCCY.DATA.DEC_MTH == 0) {
                WS_INT_TMP0 = WS_INT_PUBLIC;
                bigD = new BigDecimal(WS_INT_TMP0);
                WS_INT_TMP0 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_INT_PUBLIC = WS_INT_TMP0;
            } else if (BPCQCCY.DATA.DEC_MTH == 1) {
                WS_INT_TMP1 = WS_INT_PUBLIC;
                bigD = new BigDecimal(WS_INT_TMP1);
                WS_INT_TMP1 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_INT_PUBLIC = WS_INT_TMP1;
            } else if (BPCQCCY.DATA.DEC_MTH == 2) {
                WS_INT_TMP2 = WS_INT_PUBLIC;
                bigD = new BigDecimal(WS_INT_TMP2);
                WS_INT_TMP2 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_INT_PUBLIC = WS_INT_TMP2;
            } else if (BPCQCCY.DATA.DEC_MTH == 3) {
                WS_INT_TMP3 = WS_INT_PUBLIC;
                bigD = new BigDecimal(WS_INT_TMP3);
                WS_INT_TMP3 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                WS_INT_PUBLIC = WS_INT_TMP3;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_INT_PUBLIC);
    }
    public void R000_CALL_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "R000-CALL-INT-PROC START ");
        WS_CALL_INT = 0;
        WS_DD_INT = 0;
        WS_DEP_SPC_INT = 0;
        WS_TOT_CALL = 0;
        WS_TOT_DD = 0;
        WS_DAYS = 0;
        WS_TOT_ONE = 0;
        WS_TOT_SEVEN = 0;
        WS_CAL_DAYS = 0;
        WS_EOF_DDTBALH_FLG = 'N';
        WS_DAYS = 0;
        CEP.TRC(SCCGWA, WS_FR_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        WS_STR_DATE = WS_FR_DATE;
        WS_END_DATE = WS_TO_DATE;
        R000_GET_RATE_PARM_PROC();
        if (pgmRtn) return;
        T000_STRBR_BALH_BY_DATE();
        if (pgmRtn) return;
        T000_READNEXT_BALH_BY_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        for (WS_I = 1; WS_EOF_DDTBALH_FLG != 'Y' 
            && DDRBALH.END_DATE > WS_FR_DATE; WS_I += 1) {
            CEP.TRC(SCCGWA, DDRBALH.BAL);
            if (DDRBALH.KEY.STR_DATE > WS_FR_DATE) {
                WS_STR_DATE = DDRBALH.KEY.STR_DATE;
            } else {
                WS_STR_DATE = WS_FR_DATE;
            }
            if (DDRBALH.END_DATE < WS_TO_DATE) {
                WS_END_DATE = DDRBALH.END_DATE;
            }
            R000_GET_INT_DAYS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CAL_DAYS);
            CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT.MIN_AMT);
            CEP.TRC(SCCGWA, WS_TOT_ONE);
            CEP.TRC(SCCGWA, WS_TOT_SEVEN);
            CEP.TRC(SCCGWA, WS_TOT_CALL);
            if (DDRBALH.BAL >= DDCPCALL.DATA_TXT.MIN_AMT) {
                WS_CAL_DAYS = WS_CAL_DAYS + WS_DAYS;
                CEP.TRC(SCCGWA, WS_DAYS);
                WS_TOT_CALL = WS_TOT_CALL + DDRBALH.BAL * WS_DAYS;
                CEP.TRC(SCCGWA, DDRBALH.BAL);
                CEP.TRC(SCCGWA, WS_CAL_DAYS);
                CEP.TRC(SCCGWA, WS_TOT_CALL);
            } else {
                CEP.TRC(SCCGWA, WS_TOT_DD);
                CEP.TRC(SCCGWA, WS_TOT_CALL);
                CEP.TRC(SCCGWA, DDRBALH.BAL);
                CEP.TRC(SCCGWA, WS_CAL_DAYS);
                CEP.TRC(SCCGWA, WS_DAYS);
                WS_TOT_DD = WS_TOT_DD + DDRBALH.BAL * WS_DAYS;
                if (WS_CAL_DAYS < K_SEVEN_DAYS) {
                    WS_TOT_ONE = WS_TOT_ONE + WS_TOT_CALL;
                    CEP.TRC(SCCGWA, WS_TOT_ONE);
                } else {
                    WS_TOT_SEVEN = WS_TOT_SEVEN + WS_TOT_CALL;
                    CEP.TRC(SCCGWA, WS_TOT_SEVEN);
                }
                WS_CAL_DAYS = 0;
                WS_TOT_CALL = 0;
            }
            T000_READNEXT_BALH_BY_DATE();
            if (pgmRtn) return;
        }
        T000_ENDBR_BALH_BY_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CAL_DAYS);
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, WS_TOT_CALL);
        if (WS_CAL_DAYS != 0 
            && WS_TOT_CALL != 0) {
            if (WS_CAL_DAYS < K_SEVEN_DAYS) {
                WS_TOT_ONE = WS_TOT_ONE + WS_TOT_CALL;
            } else {
                WS_TOT_SEVEN = WS_TOT_SEVEN + WS_TOT_CALL;
            }
        }
        CEP.TRC(SCCGWA, WS_TOT_DD);
        CEP.TRC(SCCGWA, WS_TOT_ONE);
        CEP.TRC(SCCGWA, WS_TOT_SEVEN);
        CEP.TRC(SCCGWA, WS_ONE_RATE);
        CEP.TRC(SCCGWA, WS_DD_RATE);
        CEP.TRC(SCCGWA, WS_SEVEN_RATE);
        WS_TOT_CALL = 0;
        WS_DAYS = 0;
        WS_CALL_INT = ( WS_TOT_ONE * WS_ONE_RATE + WS_TOT_SEVEN * WS_SEVEN_RATE ) / WS_BASE_DAYS / 100;
        WS_DD_INT = WS_TOT_DD * WS_DD_RATE / WS_BASE_DAYS / 100;
        WS_INT_PUBLIC = WS_DD_INT;
        R000_STARDARD_AMT_PROC();
        if (pgmRtn) return;
        WS_DD_INT = WS_INT_PUBLIC;
        WS_INT_PUBLIC = WS_CALL_INT;
        R000_STARDARD_AMT_PROC();
        if (pgmRtn) return;
        WS_CALL_INT = WS_INT_PUBLIC;
        WS_DEP_SPC_INT = WS_DD_INT + WS_CALL_INT;
        WS_SPC_ADJ_INT = WS_DEP_SPC_INT - DDRACCU.AFT_ADJ_INT + WS_SPC_ADJ_INT;
        WS_INT_PUBLIC = WS_SPC_ADJ_INT;
        R000_STARDARD_AMT_PROC();
        if (pgmRtn) return;
        WS_SPC_ADJ_INT = WS_INT_PUBLIC;
        DDRACCU.AFT_ADJ_INT = WS_DEP_SPC_INT;
        T000_UPDATE_DDTACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DEP_SPC_INT);
        CEP.TRC(SCCGWA, WS_SPC_ADJ_INT);
        CEP.TRC(SCCGWA, WS_CALL_INT);
        CEP.TRC(SCCGWA, WS_DD_INT);
        CEP.TRC(SCCGWA, "R000-CALL-INT-PROC END ");
    }
    public void R000_GET_RATE_PARM_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL");
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, DDCPCALL);
        BPRPRMT.KEY.TYP = "PDD09";
        BPRPRMT.KEY.CD = "DDCALL";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCPCALL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCPCALL.DATA_TXT);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT);
        CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT.MIN_AMT);
        CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT.RATE_TYP1);
        CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT.TERM1);
        CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT.RATE_TYP2);
        CEP.TRC(SCCGWA, DDCPCALL.DATA_TXT.TERM2);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = DDRMST.OWNER_BRDP;
        BPCCINTI.BASE_INFO.BASE_TYP = DDCPCALL.DATA_TXT.RATE_TYP1;
        BPCCINTI.BASE_INFO.TENOR = DDCPCALL.DATA_TXT.TERM1;
        BPCCINTI.BASE_INFO.CCY = K_CCY_CNY;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        WS_ONE_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
        CEP.TRC(SCCGWA, WS_ONE_RATE);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = DDRMST.OWNER_BRDP;
        BPCCINTI.BASE_INFO.BASE_TYP = DDCPCALL.DATA_TXT.RATE_TYP2;
        BPCCINTI.BASE_INFO.TENOR = DDCPCALL.DATA_TXT.TERM2;
        BPCCINTI.BASE_INFO.CCY = K_CCY_CNY;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        WS_SEVEN_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
        CEP.TRC(SCCGWA, WS_SEVEN_RATE);
    }
    public void T00_READ_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (DDRCCY.CINT_FLG == 'N') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_DDTBACK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.KEY.AC_DATE = DDCUBKVL.AC_DATE;
        DDRBACK.KEY.VALUE_DATE = DDCUBKVL.VALUE_DATE;
        DDRBACK.KEY.TR_JRN = DDCUBKVL.TR_JRN;
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        IBS.READ(SCCGWA, DDRBACK, DDTBACK_RD);
    }
    public void T00_SELECT_DDTINTB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRINTB.KEY.TYPE = 'D';
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_DDTINTB = 'Y';
        } else {
            WS_FOUND_DDTINTB = 'N';
        }
    }
    public void T000_READUPD_DDTINTB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRINTB.KEY.TYPE = 'D';
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.upd = true;
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_DDTINTB = 'Y';
        } else {
            WS_FOUND_DDTINTB = 'N';
        }
    }
    public void T000_WRITE_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.WRITE(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void T000_UPDATE_DDTINTB() throws IOException,SQLException,Exception {
        DDRINTB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.REWRITE(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void T000_STARTBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTACCU_BR.rp = new DBParm();
        DDTACCU_BR.rp.TableName = "DDTACCU";
        DDTACCU_BR.rp.where = "AC = :DDRACCU.KEY.AC";
        DDTACCU_BR.rp.upd = true;
        DDTACCU_BR.rp.order = "END_DATE DESC, TYPE";
        IBS.STARTBR(SCCGWA, DDRACCU, this, DDTACCU_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRACCU);
    }
    public void T000_READNEXT_DDTACCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRACCU, this, DDTACCU_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRACCU);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_DDTACCU_FLG = 'N';
        } else {
            WS_EOF_DDTACCU_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_EOF_DDTACCU_FLG);
    }
    public void T000_ENDBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTACCU_BR);
    }
    public void T000_UPDATE_DDTACCU() throws IOException,SQLException,Exception {
        DDRACCU.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRACCU.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTACCU_RD = new DBParm();
        DDTACCU_RD.TableName = "DDTACCU";
        IBS.REWRITE(SCCGWA, DDRACCU, DDTACCU_RD);
    }
    public void T000_STRBR_BALH_BY_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBALH);
        DDRBALH.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBALH.KEY.STR_DATE = WS_FR_DATE;
        DDRBALH.END_DATE = WS_TO_DATE;
        DDTBALH_BR.rp = new DBParm();
        DDTBALH_BR.rp.TableName = "DDTBALH";
        DDTBALH_BR.rp.where = "AC = :DDRBALH.KEY.AC "
            + "AND STR_DATE <= :DDRBALH.END_DATE "
            + "AND END_DATE > :DDRBALH.KEY.STR_DATE";
        DDTBALH_BR.rp.upd = true;
        DDTBALH_BR.rp.order = "END_DATE DESC";
        IBS.STARTBR(SCCGWA, DDRBALH, this, DDTBALH_BR);
    }
    public void T000_READNEXT_BALH_BY_DATE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRBALH, this, DDTBALH_BR);
        CEP.TRC(SCCGWA, DDRBALH.KEY.STR_DATE);
        CEP.TRC(SCCGWA, DDRBALH.END_DATE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_DDTBALH_FLG = 'N';
        } else {
            WS_EOF_DDTBALH_FLG = 'Y';
        }
    }
    public void T000_ENDBR_BALH_BY_DATE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTBALH_BR);
    }
    public void T000_UPDATE_DDTBALH() throws IOException,SQLException,Exception {
        DDRBALH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRBALH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTBALH_RD = new DBParm();
        DDTBALH_RD.TableName = "DDTBALH";
        IBS.REWRITE(SCCGWA, DDRBALH, DDTBALH_RD);
    }
    public void T000_WRITE_DDTBALH() throws IOException,SQLException,Exception {
        DDTBALH_RD = new DBParm();
        DDTBALH_RD.TableName = "DDTBALH";
        IBS.WRITE(SCCGWA, DDRBALH, DDTBALH_RD);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_LINK_BPCQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZITIRR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-COMP-TIRR-AMT", DDCITIRR);
        if (DDCITIRR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCITIRR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICCACCU);
        CEP.TRC(SCCGWA, CICCACCU.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICCACCU.DATA.CI_NO);
        if (CICCACCU.RC.RC_CODE != 0) {
