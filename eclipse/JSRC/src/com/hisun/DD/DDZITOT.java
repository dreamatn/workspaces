package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCITAXG;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCCRACD;

public class DDZITOT {
    boolean pgmRtn = false;
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_INQ_CCY = "BP-INQUIRE-CCY";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_RAT_PRM_TYP = "PDRAT";
    String K_CCY_CNY = "156";
    int K_HEAD_BR = 101000;
    short K_SEVEN_DAYS = 7;
    double K_MAX_AMT = 99999999999999.99;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_IDX = 0;
    short WS_A = 0;
    short WS_IDX_A = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_II = 0;
    double WS_INT_BAL = 0;
    double WS_NOR_BAL = 0;
    double WS_HLD_BAL = 0;
    double WS_HLD_AMT = 0;
    double WS_TIR_1_HLD = 0;
    double WS_HLD = 0;
    long WS_TMP_AMT = 0;
    double WS_TOT_BAL = 0;
    double WS_CALL_INT = 0;
    double WS_DD_INT = 0;
    double WS_DEP_SPC_INT = 0;
    double WS_TOT_CALL = 0;
    double WS_TOT_DD = 0;
    short WS_DAYS = 0;
    short WS_CAL_DAYS = 0;
    short WS_ADP_DAYS = 0;
    short WS_ADP_MTHS = 0;
    double WS_TOT_ONE = 0;
    double WS_TOT_SEVEN = 0;
    short WS_TOT_DAYS = 0;
    double WS_TOT = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_FR_DATE = 0;
    int WS_TO_DATE = 0;
    double WS_DD_RATE = 0;
    double WS_ONE_RATE = 0;
    double WS_SEVEN_RATE = 0;
    double WS_BAS_RATE = 0;
    char WS_HL_FLG = ' ';
    String WS_RUL_CD = " ";
    long WS_INT_TMP0 = 0;
    double WS_INT_TMP1 = 0;
    double WS_INT_TMP2 = 0;
    double WS_INT_TMP3 = 0;
    double WS_INT_PUBLIC = 0;
    String WS_NRA_TAX_TYP = " ";
    DDZITOT_WS_CCY_RATE_KEY WS_CCY_RATE_KEY = new DDZITOT_WS_CCY_RATE_KEY();
    DDZITOT_WS_MPRD_KEY WS_MPRD_KEY = new DDZITOT_WS_MPRD_KEY();
    String WS_HOLD_CODE = " ";
    DDZITOT_WS_AC_RATE_DATA WS_AC_RATE_DATA = new DDZITOT_WS_AC_RATE_DATA();
    DDZITOT_WS_TEMP_RATE WS_TEMP_RATE = new DDZITOT_WS_TEMP_RATE();
    DDZITOT_WS_INT_AMT WS_INT_AMT = new DDZITOT_WS_INT_AMT();
    DDZITOT_WS_ACCU_DATA WS_ACCU_DATA = new DDZITOT_WS_ACCU_DATA();
    char WS_FOUND_FLAG = ' ';
    char WS_MSTR_FLG = ' ';
    char WS_EOF_CCY_FLG = ' ';
    char WS_EOF_ACCU_FLG = ' ';
    char WS_EOF_BALH_FLG = ' ';
    char WS_EOF_ARAH_FLG = ' ';
    char WS_EOF_HLD_FLG = ' ';
    char WS_HLD_OVER_FLG = ' ';
    char WS_ADD_UPD_REC_FLG = ' ';
    char WS_OD_REC_FLG = ' ';
    char WS_SKIP_FLG = ' ';
    char WS_SKIP_II_FLG = ' ';
    char WS_SKIP_III_FLG = ' ';
    char WS_HLD_SKIP_FLG = ' ';
    char WS_AC_DEP_RATE_FLG = ' ';
    char WS_AC_OD_RATE_FLG = ' ';
    char WS_ADP_CON_FLG = ' ';
    char WS_ADMN_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRCCYT DDRCCYT = new DDRCCYT();
    DDRADMN DDRADMN = new DDRADMN();
    DDRINTB DDRINTB = new DDRINTB();
    DDRINTB DDRINTO = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    DDRARAH DDRARAH = new DDRARAH();
    DDRBALH DDRBALH = new DDRBALH();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DDCITIRR DDCITIRR = new DDCITIRR();
    DDCPCALL DDCPCALL = new DDCPCALL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCITAXG BPCITAXG = new BPCITAXG();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    TDCCRACD TDCCRACD = new TDCCRACD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCITOT DDCITOT;
    public void MP(SCCGWA SCCGWA, DDCITOT DDCITOT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCITOT = DDCITOT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZITOT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCITOT.CCY.trim().length() == 0) {
            WS_EOF_CCY_FLG = 'N';
            T000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            while (WS_EOF_CCY_FLG != 'Y') {
                DDCITOT.CCY = DDRCCY.CCY;
                DDCITOT.CCY_TYPE = DDRCCY.CCY_TYPE;
                DDCITOT.CURR_BAL = DDRCCY.LAST_DAYEND_BAL;
                DDCITOT.NINT_BAL = DDRCCY.NOT_INT_BAL;
                B005_PROCESS_ONE_CCY();
                if (pgmRtn) return;
                T000_READNEXT_DDTCCY();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTCCY();
            if (pgmRtn) return;
        } else {
            B005_PROCESS_ONE_CCY();
            if (pgmRtn) return;
        }
    }
    public void B005_PROCESS_ONE_CCY() throws IOException,SQLException,Exception {
        B010_GET_ACO_AC_PROC();
        if (pgmRtn) return;
        B015_GET_PROD_INF_PROC();
        if (pgmRtn) return;
        B020_GET_RATE_INF_PROC();
        if (pgmRtn) return;
        B040_COMP_INT_PROC();
        if (pgmRtn) return;
        B050_UPD_DDTINTB();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDCITOT.AC);
        CEP.TRC(SCCGWA, DDCITOT.CCY);
        CEP.TRC(SCCGWA, DDCITOT.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCITOT.CURR_BAL);
        CEP.TRC(SCCGWA, DDCITOT.NINT_BAL);
        if (DDCITOT.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_ACO_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCITOT.AC;
        CICQACAC.DATA.CCY_ACAC = DDCITOT.CCY;
        CICQACAC.DATA.CR_FLG = DDCITOT.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_NRA_TAX_TYP);
        WS_NRA_TAX_TYP = CICCUST.O_DATA.O_NRA_TAX_TYP;
    }
    public void B015_GET_PROD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDCITOT.PROD_CD;
        DDCIQPRD.INPUT_DATA.CCY = DDCITOT.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void B020_GET_RATE_INF_PROC() throws IOException,SQLException,Exception {
        R000_READUPD_O_DDTINTB();
        if (pgmRtn) return;
        T000_READ_UPDATE_DDTINTB();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "INTB REC NOTFOUND");
            WS_ADD_UPD_REC_FLG = 'A';
            IBS.init(SCCGWA, DDRINTB);
            DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDRINTB.KEY.TYPE = 'D';
            DDRINTB.STRT_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRINTB.LST_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRINTB.LST_DPOST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRINTB.END_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            if (DDRINTB.TIR_RATE1 > 0) {
                DDCITOT.OUTPUT_INFO.ACCU_DEP_INT = DDRINTB.DEP_ACCU_INT;
                DDCITOT.OUTPUT_INFO.ACCU_INT = DDRINTB.DEP_ACCU_INT;
                DDCITOT.OUTPUT_INFO.ACCU_DEP_INT += DDRINTB.DEP_ADJ_INT;
                DDCITOT.OUTPUT_INFO.ACCU_SPC_INT = DDRINTB.DEP_SPC_INT;
                DDCITOT.OUTPUT_INFO.ACCU_SPC_INT += DDRINTB.DEP_SPC_ADJ_INT;
            } else {
                DDCITOT.OUTPUT_INFO.ACCU_SPC_INT = DDRINTB.DEP_ACCU_INT;
                DDCITOT.OUTPUT_INFO.ACCU_SPC_INT += DDRINTB.DEP_ADJ_INT;
                DDCITOT.OUTPUT_INFO.ACCU_DEP_INT = DDRINTB.DEP_SPC_INT;
                DDCITOT.OUTPUT_INFO.ACCU_INT = DDRINTB.DEP_SPC_INT;
                DDCITOT.OUTPUT_INFO.ACCU_DEP_INT += DDRINTB.DEP_SPC_ADJ_INT;
            }
            WS_AC_RATE_DATA.WS_TIR_TYPE = DDRINTB.TIR_TYPE;
            WS_AC_RATE_DATA.WS_AGSP_FLG = DDRINTB.AGSP_FLG;
            WS_ADD_UPD_REC_FLG = 'U';
            if (DDRINTB.LST_TOT_DATE >= SCCGWA.COMM_AREA.AC_DATE 
                && DDCITOT.FUNC == 'A') {
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DDCITOT.OUTPUT_INFO.ACCU_DEP_INT);
            CEP.TRC(SCCGWA, DDCITOT.OUTPUT_INFO.ACCU_SPC_INT);
            CEP.TRC(SCCGWA, DDRINTB.TIR_RATE1);
        }
        B020_1_GET_MSTR_RATE();
        if (pgmRtn) return;
        B020_2_GET_PROD_RATE();
        if (pgmRtn) return;
        if (DDVMPRD.VAL.TAX_RATE_BASE.trim().length() > 0) {
            CEP.TRC(SCCGWA, DDVMPRD.VAL.TAX_RATE_BASE);
            IBS.init(SCCGWA, BPCITAXG);
            BPCITAXG.TAX_TYP = DDVMPRD.VAL.TAX_RATE_BASE;
            BPCITAXG.VAL_TYP = '0';
            BPCITAXG.CCY = K_CCY_CNY;
            BPCITAXG.BR = K_HEAD_BR;
            BPCITAXG.ST_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCITAXG.EN_DT = SCCGWA.COMM_AREA.AC_DATE;
            WS_AC_RATE_DATA.WS_TAX_RATE = BPCITAXG.OUTPUT.TAXR_GROUP[1-1].TAX_VAL;
            CEP.TRC(SCCGWA, BPCITAXG.OUTPUT.TAXR_GROUP[1-1].TAX_VAL);
        }
        if (WS_NRA_TAX_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPCITAXG);
            BPCITAXG.TAX_TYP = WS_NRA_TAX_TYP;
            BPCITAXG.VAL_TYP = '1';
            BPCITAXG.CCY = DDCITOT.CCY;
            BPCITAXG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCITAXG.ST_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCITAXG.EN_DT = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_BPZITAXG();
            if (pgmRtn) return;
            WS_AC_RATE_DATA.WS_NRA_RATE = BPCITAXG.OUTPUT.TAXR_GROUP[1-1].TAX_VAL;
        }
        CEP.TRC(SCCGWA, "123");
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCITOT.CCY;
        S000_CALL_BPCQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("01")) {
            WS_AC_RATE_DATA.WS_BASE_DAYS = 360;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("02")) {
            WS_AC_RATE_DATA.WS_BASE_DAYS = 365;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("03")) {
            WS_AC_RATE_DATA.WS_BASE_DAYS = 366;
        }
        WS_AC_RATE_DATA.WS_PROD_BASE_DAYS = WS_AC_RATE_DATA.WS_BASE_DAYS;
        CEP.TRC(SCCGWA, WS_AC_RATE_DATA.WS_BASE_DAYS);
        CEP.TRC(SCCGWA, DDCITOT.SPR_PCT);
        CEP.TRC(SCCGWA, DDCITOT.SPR);
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            CEP.TRC(SCCGWA, WS_II);
            if (DDCITOT.SPR_PCT != 0) {
                WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[WS_IDX-1].WS_TIR_RATE = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[WS_IDX-1].WS_TIR_RATE + WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[WS_IDX-1].WS_TIR_RATE * DDCITOT.SPR_PCT / 100;
            } else {
                CEP.TRC(SCCGWA, "ELSE");
                if (DDCITOT.SPR != 0) {
                    CEP.TRC(SCCGWA, "IF2");
                    WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[WS_IDX-1].WS_TIR_RATE = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[WS_IDX-1].WS_TIR_RATE + DDCITOT.SPR;
                }
            }
            CEP.TRC(SCCGWA, WS_II);
        }
        if (WS_ADD_UPD_REC_FLG == 'A') {
            B40_1_2_UPD_INTB_RATE();
            if (pgmRtn) return;
        }
        if (WS_OD_REC_FLG == 'A' 
            && WS_AC_OD_RATE_FLG == 'Y') {
            DDRINTO.CAL_INT_MTH = '1';
            DDRINTO.TIR_TYPE = 'N';
            DDRINTO.TIR_AMT1 = K_MAX_AMT;
            DDRINTO.TIR_RATE1 = WS_AC_RATE_DATA.WS_OD_RATE;
        }
        CEP.TRC(SCCGWA, DDRINTO);
        CEP.TRC(SCCGWA, WS_AC_RATE_DATA.WS_OD_RATE);
        CEP.TRC(SCCGWA, DDRINTO.TIR_AMT1);
    }
    public void B40_1_2_UPD_INTB_RATE() throws IOException,SQLException,Exception {
        DDRINTB.CAL_INT_MTH = WS_ACCU_DATA.WS_CAL_INT_MTH;
        DDRINTB.TIR_TYPE = WS_AC_RATE_DATA.WS_TIR_TYPE;
        DDRINTB.AGSP_FLG = WS_AC_RATE_DATA.WS_AGSP_FLG;
        DDRINTB.TIR_RATE1 = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[1-1].WS_TIR_RATE;
        DDRINTB.TIR_RATE2 = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[2-1].WS_TIR_RATE;
        DDRINTB.TIR_RATE3 = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[3-1].WS_TIR_RATE;
        DDRINTB.TIR_RATE4 = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[4-1].WS_TIR_RATE;
        DDRINTB.TIR_RATE5 = WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[5-1].WS_TIR_RATE;
        DDRINTB.STRT_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.TAX_RATE = WS_AC_RATE_DATA.WS_TAX_RATE;
        DDRINTB.DEPR_CHA_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.TAX_RATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B020_1_GET_MSTR_RATE() throws IOException,SQLException,Exception {
        WS_AC_DEP_RATE_FLG = 'N';
        WS_AC_OD_RATE_FLG = 'N';
        WS_ADP_CON_FLG = 'N';
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.ADP_EXPDATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'Y') {
            if (SCCGWA.COMM_AREA.AC_DATE >= DDRMSTR.KEY.ADP_STRDATE 
                && (SCCGWA.COMM_AREA.AC_DATE <= DDRMSTR.ADP_EXPDATE 
                || DDRMSTR.ADP_EXPDATE == 0) 
                && DDVMPRD.VAL.CAL_DINT_METH != '3') {
                CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
                CEP.TRC(SCCGWA, DDRMSTR.ADP_NO);
                if (DDRMSTR.ADP_NO.trim().length() > 0) {
                    WS_ADP_CON_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, WS_AC_RATE_DATA.WS_TIR_TYPE);
                if (WS_AC_RATE_DATA.WS_TIR_TYPE == ' ') {
                    WS_AC_RATE_DATA.WS_TIR_TYPE = DDRMSTR.TIR_TYPE;
                    WS_AC_RATE_DATA.WS_AGSP_FLG = DDRMSTR.AGSP_FLG;
                }
                CEP.TRC(SCCGWA, DDRMSTR.KEY.ADP_TYPE);
                WS_ACCU_DATA.WS_CAL_INT_MTH = '1';
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("2")) {
                    CEP.TRC(SCCGWA, DDRMSTR.TIR_AMT11);
                    CEP.TRC(SCCGWA, DDRMSTR.TIR_AMT12);
                    WS_AC_DEP_RATE_FLG = 'Y';
                    WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[1-1].WS_TIR_AMT = DDRMSTR.TIR_AMT11;
                    WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[2-1].WS_TIR_AMT = DDRMSTR.TIR_AMT12;
                    WS_TEMP_RATE.WS_RBAS = DDRMSTR.TIR_RBAS12;
                    WS_TEMP_RATE.WS_RCD = DDRMSTR.TIR_RCD12;
                    WS_TEMP_RATE.WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
                    WS_TEMP_RATE.WS_SPR = DDRMSTR.TIR_SPR12;
                    WS_TEMP_RATE.WS_SPR_PCT = DDRMSTR.TIR_SPR_PCT12;
                    WS_RUL_CD = DDRMSTR.TIR_GRP12;
                    if (DDRMSTR.TIR_FIX_RATE12 != 0) {
                        WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[2-1].WS_TIR_RATE = DDRMSTR.TIR_FIX_RATE12;
                    } else {
                        R000_GET_RATE_PROC();
                        if (pgmRtn) return;
                        WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[2-1].WS_TIR_RATE = WS_TEMP_RATE.WS_RATE;
                    }
                    CEP.TRC(SCCGWA, WS_AC_RATE_DATA.WS_TIR_INF.WS_TIR_DATA[2-1].WS_TIR_RATE);
                }
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("3")) {
                    WS_ACCU_DATA.WS_CAL_INT_MTH = '2';
                }
                if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("1") 
                    && DDRMSTR.TIR_TYPE == 'P') {
                    WS_ACCU_DATA.WS_CAL_INT_MTH = '2';
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = DDRMSTR.KEY.ADP_STRDATE;
                    if (DDRMSTR.ADP_EXPDATE < SCCGWA.COMM_AREA.AC_DATE) {
                        SCCCLDT.DATE2 = DDRMSTR.ADP_EXPDATE;
                    } else {
                        SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                    }
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    WS_ADP_DAYS = (short) SCCCLDT.DAYS;
                    WS_ADP_MTHS = SCCCLDT.MTHS;
                    CEP.TRC(SCCGWA, WS_ADP_DAYS);
                    CEP.TRC(SCCGWA, "WS-ADP-DAYS = ");
                    CEP.TRC(SCCGWA, WS_ADP_DAYS);
                    CEP.TRC(SCCGWA, WS_ADP_MTHS);
                    CEP.TRC(SCCGWA, "WS-ADP-MTHS = ");
                    CEP.TRC(SCCGWA, WS_ADP_MTHS);
                }
            }
            CEP.TRC(SCCGWA, "TEST001");
            if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("8")) {
                WS_AC_OD_RATE_FLG = 'Y';
                if (DDRMSTR.OD_RBAS.trim().length() > 0) {
                    IBS.init(SCCGWA, WS_TEMP_RATE);
                    WS_TEMP_RATE.WS_RBAS = DDRMSTR.OD_RBAS;
                    WS_TEMP_RATE.WS_RCD = DDRMSTR.OD_RCD;
                    WS_TEMP_RATE.WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
                    WS_TEMP_RATE.WS_SPR = DDRMSTR.OD_SPR;
                    WS_TEMP_RATE.WS_SPR_PCT = DDRMSTR.OD_SPR_PCT;
                    WS_RUL_CD = " ";
                    R000_GET_RATE_PROC();
                    if (pgmRtn) return;
                    WS_AC_RATE_DATA.WS_OD_RATE = WS_TEMP_RATE.WS_RATE;
                }
                if (DDRMSTR.UOD_RBAS.trim().length() > 0) {
                    IBS.init(SCCGWA, WS_TEMP_RATE);
                    WS_TEMP_RATE.WS_RBAS = DDRMSTR.UOD_RBAS;
                    WS_TEMP_RATE.WS_RCD = DDRMSTR.UOD_RCD;
                    WS_TEMP_RATE.WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
                    WS_TEMP_RATE.WS_SPR = DDRMSTR.UOD_SPR;
                    WS_TEMP_RATE.WS_SPR_PCT = DDRMSTR.UOD_SPR_PCT;
                    WS_RUL_CD = " ";
                    R000_GET_RATE_PROC();
                    if (pgmRtn) return;
                    WS_AC_RATE_DATA.WS_UOD_RATE = WS_TEMP_RATE.WS_RATE;
                }
                if (DDRMSTR.TOD_RBAS.trim().length() > 0) {
                    IBS.init(SCCGWA, WS_TEMP_RATE);
                    WS_TEMP_RATE.WS_RBAS = DDRMSTR.TOD_RBAS;
                    WS_TEMP_RATE.WS_RCD = DDRMSTR.TOD_RCD;
                    WS_TEMP_RATE.WS_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
                    WS_TEMP_RATE.WS_SPR = DDRMSTR.TOD_SPR;
                    WS_TEMP_RATE.WS_SPR_PCT = DDRMSTR.TOD_SPR_PCT;
                    WS_RUL_CD = " ";
                    R000_GET_RATE_PROC();
                    if (pgmRtn) return;
                    WS_AC_RATE_DATA.WS_TOD_RATE = WS_TEMP_RATE.WS_RATE;
                }
            }
        }
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.ADP_EXPDATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_DDTADMN();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'Y') {
            WS_AC_OD_RATE_FLG = 'Y';
            WS_AC_RATE_DATA.WS_OD_RATE = DDRADMN.OD_INT_RAT;
            if (DDRADMN.OD_INT_RAT_FLT_TYP == '1') {
                WS_AC_RATE_DATA.WS_OD_RATE = WS_AC_RATE_DATA.WS_OD_RATE + DDRADMN.OD_INT_RAT_VAR;
            } else if (DDRADMN.OD_INT_RAT_FLT_TYP == '2') {
                WS_AC_RATE_DATA.WS_OD_RATE = WS_AC_RATE_DATA.WS_OD_RATE + WS_AC_RATE_DATA.WS_OD_RATE * DDRADMN.OD_INT_RAT_PCT / 100;
            } else if (DDRADMN.OD_INT_RAT_FLT_TYP == '3') {
                WS_AC_RATE_DATA.WS_OD_RATE = WS_AC_RATE_DATA.WS_OD_RATE + WS_AC_RATE_DATA.WS_OD_RATE * DDRADMN.OD_INT_RAT_PCT / 100 + DDRADMN.OD_INT_RAT_VAR;
            } else if (DDRADMN.OD_INT_RAT_FLT_TYP == '4') {
