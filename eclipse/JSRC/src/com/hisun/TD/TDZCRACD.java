package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class TDZCRACD {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTRATE_RD;
    DBParm TDTRACD_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    DBParm BPTOCAC_RD;
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_HQ_BASE_TYP_TY = "F01";
    String K_HQ_BASE_TYP_DD = "A01";
    String K_HQ_TENOR = "D000";
    String K_TD_BASE_TYP = "A02";
    String K_AC_BK = "043";
    int K_SUP_BR = 043999;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_MSGID = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_CHKED = 0;
    short WS_AGE_TMP = 0;
    short WS_NEED_CHK = 0;
    short WS_GRP_NUM = 0;
    short WS_H_L = 0;
    short WS_GRPS_NUM = 0;
    String WS_APP = " ";
    short WS_HOLD_LVL = 0;
    double WS_TWO_POINT = 0;
    short WS_RACD_AGE = 0;
    TDZCRACD_REDEFINES17 REDEFINES17 = new TDZCRACD_REDEFINES17();
    String WS_TERM = " ";
    TDZCRACD_REDEFINES21 REDEFINES21 = new TDZCRACD_REDEFINES21();
    String WS_IRAT_CD = " ";
    double WS_RAT_TMP = 0;
    char WS_RUL_TYP = ' ';
    double WS_PNT_TMP = 0;
    double WS_PCT_TMP = 0;
    double WS_RAT_TMP1 = 0;
    char WS_RUL_TYP1 = ' ';
    double WS_PNT_TMP1 = 0;
    double WS_PCT_TMP1 = 0;
    String WS_TERM2 = " ";
    TDZCRACD_REDEFINES34 REDEFINES34 = new TDZCRACD_REDEFINES34();
    double WS_DD_RAT = 0;
    double WS_RAT = 0;
    double WS_INT_TEMP = 0;
    TDZCRACD_WS_AC_BAS_INFO WS_AC_BAS_INFO = new TDZCRACD_WS_AC_BAS_INFO();
    char WS_GRPS_FLG = ' ';
    char WS_RACD_FLG = ' ';
    char WS_SGRS_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPROCAC BPROCAC = new BPROCAC();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCITAXG BPCITAXG = new BPCITAXG();
    TDCCDS TDCCDS = new TDCCDS();
    TDRINTC TDRINTC = new TDRINTC();
    TDRCALL TDRCALL = new TDRCALL();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCSAVRT TDCSAVRT = new TDCSAVRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCUINTI BPCUINTI = new BPCUINTI();
    TDRAINT TDRAINT = new TDRAINT();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    CICSGRS CICSGRS = new CICSGRS();
    TDRIREV TDRIREV = new TDRIREV();
    TDRDINT TDRDINT = new TDRDINT();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    TDCUIRUL TDCUIRUL = new TDCUIRUL();
    TDRRATE TDRRATE = new TDRRATE();
    TDRRACD TDRRACD = new TDRRACD();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    BPCFX BPCFX = new BPCFX();
    String WS_AC = " ";
    int WS_VAL = 0;
    SCCGWA SCCGWA;
    TDCCRACD TDCCRACD;
    TDCIRULP TDCPIOD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, TDCCRACD TDCCRACD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCRACD = TDCCRACD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZCRACD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        if (TDCCRACD.BAL != 0) {
            WS_AC_BAS_INFO.WS_BAL = TDCCRACD.BAL;
        }
        CEP.TRC(SCCGWA, TDCCRACD.RAT_BAS);
        if (TDCCRACD.TERM.trim().length() > 0) {
            WS_AC_BAS_INFO.WS_AC_TERM = TDCCRACD.TERM;
        }
        if (TDCCRACD.SVR_LVL.trim().length() > 0) {
            WS_AC_BAS_INFO.WS_SVR_LVL = TDCCRACD.SVR_LVL;
        }
        if (TDCCRACD.GRPS_NO.trim().length() > 0) {
            WS_AC_BAS_INFO.WS_GRPS_GRP[1-1].WS_GRPS_NO = TDCCRACD.GRPS_NO;
            WS_GRPS_NUM += 1;
        }
        if (TDCCRACD.BR != 0) {
            WS_AC_BAS_INFO.WS_BR = TDCCRACD.BR;
        }
        if (TDCCRACD.RGN_NO.trim().length() > 0) {
            WS_AC_BAS_INFO.WS_RGN_NO = TDCCRACD.RGN_NO;
        }
        if (TDCCRACD.ASS_LVL.trim().length() > 0) {
            WS_AC_BAS_INFO.WS_ASS_LVL = TDCCRACD.ASS_LVL;
        }
        if (TDCCRACD.CHNL_NO.trim().length() > 0) {
            WS_AC_BAS_INFO.WS_CHNL_NO = TDCCRACD.CHNL_NO;
        }
        if (TDCCRACD.AGE != 0) {
            WS_AC_BAS_INFO.WS_AGE = TDCCRACD.AGE;
        }
        if (TDCCRACD.TODAY != 0) {
            WS_AC_BAS_INFO.WS_TODAY = TDCCRACD.TODAY;
            IBS.CPY2CLS(SCCGWA, WS_AC_BAS_INFO.WS_TODAY+"", WS_AC_BAS_INFO.REDEFINES56);
        } else {
            WS_AC_BAS_INFO.WS_TODAY = SCCGWA.COMM_AREA.AC_DATE;
            IBS.CPY2CLS(SCCGWA, WS_AC_BAS_INFO.WS_TODAY+"", WS_AC_BAS_INFO.REDEFINES56);
        }
        if (TDCCRACD.BIRTH != 0) {
            WS_AC_BAS_INFO.WS_BIRTH = TDCCRACD.BIRTH;
            IBS.CPY2CLS(SCCGWA, WS_AC_BAS_INFO.WS_BIRTH+"", WS_AC_BAS_INFO.REDEFINES52);
            WS_AGE_TMP = (short) (WS_AC_BAS_INFO.REDEFINES56.WS_TODAY_YY - WS_AC_BAS_INFO.REDEFINES52.WS_BIRTH_YY);
            if (WS_AC_BAS_INFO.REDEFINES56.WS_TODAY_MD < WS_AC_BAS_INFO.REDEFINES52.WS_BIRTH_MD) {
                WS_AGE_TMP = (short) (WS_AGE_TMP - 1);
            }
            WS_AC_BAS_INFO.WS_AGE = WS_AGE_TMP;
        }
        if (TDCCRACD.GENDER != ' ') {
            WS_AC_BAS_INFO.WS_GENDER = TDCCRACD.GENDER;
        }
        if (TDCCRACD.RAT_BAS != 0) {
            WS_RAT_TMP = TDCCRACD.RAT_BAS;
            WS_RAT = TDCCRACD.RAT_BAS;
        }
        if (TDCCRACD.RUL_TYP == ' ') {
            TDCCRACD.RUL_TYP = '0';
        }
        if (TDCCRACD.CI_NO.trim().length() == 0 
            || TDCCRACD.FRM_APP.trim().length() == 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDCCRACD.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            TDCCRACD.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            WS_APP = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (pgmRtn) return;
        if (TDCCRACD.RUL_TYP == '0'
            || TDCCRACD.RUL_TYP == 'H') {
            WS_I = 0;
            WS_J = 0;
            WS_K = 0;
            IBS.init(SCCGWA, TDRSMST);
            IBS.init(SCCGWA, DDRCCY);
            IBS.init(SCCGWA, CICCUST);
            IBS.init(SCCGWA, CICSGRS);
            WS_RAT_TMP1 = 0;
            WS_SGRS_FLG = ' ';
            for (WS_I = 1; WS_I <= 10 
                && TDCCRACD.RUL_CD_ALL[WS_I-1].RUL_CD.trim().length() != 0; WS_I += 1) {
                IBS.init(SCCGWA, TDRRATE);
                IBS.init(SCCGWA, TDRRACD);
                TDRRATE.KEY.RUL_CD = TDCCRACD.RUL_CD_ALL[WS_I-1].RUL_CD;
                TDRRATE.KEY.IBS_AC_BK = K_AC_BK;
                T000_READ_TDTRATE();
                if (pgmRtn) return;
                if (TDRRATE.SDT <= WS_AC_BAS_INFO.WS_TODAY 
                    && TDRRATE.DDT >= WS_AC_BAS_INFO.WS_TODAY) {
                    TDRRACD.KEY.RUL_CD = TDRRATE.KEY.RUL_CD;
                    TDRRACD.KEY.IBS_AC_BK = K_AC_BK;
                    WS_J = 1;
                    TDRRACD.KEY.NUM = WS_J;
                    T000_READ_TDTRACD();
                    if (pgmRtn) return;
                    while (WS_J <= 30 
                        && WS_RACD_FLG != 'N') {
                        WS_CHKED = 0;
                        WS_NEED_CHK = 0;
                        R000_COMPARE_RUL();
                        if (pgmRtn) return;
                        if (WS_RAT_TMP1 > WS_RAT) {
                            WS_RAT = WS_RAT_TMP1;
                            WS_RUL_TYP = WS_RUL_TYP1;
                            WS_PNT_TMP = WS_PNT_TMP1;
                            WS_PCT_TMP = WS_PCT_TMP1;
                        }
                        WS_J += 1;
                        TDRRACD.KEY.NUM = WS_J;
                        T000_READ_TDTRACD();
                        if (pgmRtn) return;
                    }
                }
            }
        } else if (TDCCRACD.RUL_TYP == '1'
            || TDCCRACD.RUL_TYP == 'L') {
            WS_I = 0;
            WS_J = 0;
            WS_K = 0;
            IBS.init(SCCGWA, TDRSMST);
            IBS.init(SCCGWA, DDRCCY);
            IBS.init(SCCGWA, CICCUST);
            IBS.init(SCCGWA, CICSGRS);
            WS_RAT_TMP1 = 0;
            WS_SGRS_FLG = ' ';
            for (WS_I = 1; WS_I <= 10 
                && TDCCRACD.RUL_CD_ALL[WS_I-1].RUL_CD.trim().length() != 0; WS_I += 1) {
                IBS.init(SCCGWA, TDRRATE);
                IBS.init(SCCGWA, TDRRACD);
                TDRRATE.KEY.RUL_CD = TDCCRACD.RUL_CD_ALL[WS_I-1].RUL_CD;
                TDRRATE.KEY.IBS_AC_BK = K_AC_BK;
                T000_READ_TDTRATE();
                if (pgmRtn) return;
                if (TDRRATE.SDT <= WS_AC_BAS_INFO.WS_TODAY 
                    && TDRRATE.DDT >= WS_AC_BAS_INFO.WS_TODAY) {
                    TDRRACD.KEY.RUL_CD = TDRRATE.KEY.RUL_CD;
                    TDRRACD.KEY.IBS_AC_BK = K_AC_BK;
                    WS_J = 1;
                    TDRRACD.KEY.NUM = WS_J;
                    T000_READ_TDTRACD();
                    if (pgmRtn) return;
                    if (WS_RACD_FLG == 'Y') {
                        WS_CHKED = 0;
                        WS_NEED_CHK = 0;
                        R000_COMPARE_RUL();
                        if (pgmRtn) return;
                        WS_RAT = WS_RAT_TMP1;
                        WS_RAT = WS_RAT_TMP1;
                        WS_RUL_TYP = WS_RUL_TYP1;
                        WS_PNT_TMP = WS_PNT_TMP1;
                        WS_PCT_TMP = WS_PCT_TMP1;
                        T000_READ_TDTRACD();
                        if (pgmRtn) return;
                        while (WS_J <= 30 
                            && WS_RACD_FLG != 'N') {
                            WS_CHKED = 0;
                            WS_NEED_CHK = 0;
                            R000_COMPARE_RUL();
                            if (pgmRtn) return;
                            if (WS_RAT_TMP1 < WS_RAT) {
                                WS_RAT = WS_RAT_TMP1;
                                WS_RAT = WS_RAT_TMP1;
                                WS_RUL_TYP = WS_RUL_TYP1;
                                WS_PNT_TMP = WS_PNT_TMP1;
                                WS_PCT_TMP = WS_PCT_TMP1;
                            }
                            WS_J += 1;
                            TDRRACD.KEY.NUM = WS_J;
                            T000_READ_TDTRACD();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        } else if (TDCCRACD.RUL_TYP == '2') {
            WS_I = 0;
            WS_J = 0;
            WS_K = 0;
            IBS.init(SCCGWA, TDRSMST);
            IBS.init(SCCGWA, DDRCCY);
            IBS.init(SCCGWA, CICCUST);
            IBS.init(SCCGWA, CICSGRS);
            WS_RAT_TMP1 = 0;
            WS_SGRS_FLG = ' ';
            CEP.TRC(SCCGWA, WS_RAT);
            for (WS_I = 1; WS_I <= 10 
                && TDCCRACD.RUL_CD_ALL[WS_I-1].RUL_CD.trim().length() != 0; WS_I += 1) {
                IBS.init(SCCGWA, TDRRATE);
                IBS.init(SCCGWA, TDRRACD);
                TDRRATE.KEY.RUL_CD = TDCCRACD.RUL_CD_ALL[WS_I-1].RUL_CD;
                TDRRATE.KEY.IBS_AC_BK = K_AC_BK;
                T000_READ_TDTRATE();
                if (pgmRtn) return;
                if (TDRRATE.SDT <= WS_AC_BAS_INFO.WS_TODAY 
                    && TDRRATE.DDT >= WS_AC_BAS_INFO.WS_TODAY) {
                    TDRRACD.KEY.RUL_CD = TDRRATE.KEY.RUL_CD;
                    TDRRACD.KEY.IBS_AC_BK = K_AC_BK;
                    WS_J = 1;
                    TDRRACD.KEY.NUM = WS_J;
                    T000_READ_TDTRACD();
                    if (pgmRtn) return;
                    while (WS_J <= 30 
                        && WS_RACD_FLG != 'N') {
                        WS_CHKED = 0;
                        WS_NEED_CHK = 0;
                        R000_COMPARE_RUL();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, WS_RAT);
                        if (WS_CHKED >= WS_NEED_CHK) {
                            WS_RAT = WS_RAT_TMP1;
                            WS_RAT_TMP = WS_RAT_TMP1;
                        }
                        CEP.TRC(SCCGWA, WS_RAT);
                        CEP.TRC(SCCGWA, "WS-CRACD-RAT");
                        WS_J += 1;
                        TDRRACD.KEY.NUM = WS_J;
                        T000_READ_TDTRACD();
                        if (pgmRtn) return;
                    }
                }
            }
            WS_RUL_TYP = '1';
            WS_PCT_TMP = 0;
            WS_PNT_TMP = WS_RAT - TDCCRACD.RAT_BAS;
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TREAT_TYPE_ERROR);
        }
        TDCCRACD.OUT_INF.SPRD_TYP = WS_RUL_TYP;
        TDCCRACD.OUT_INF.SPRD_PCT = WS_PCT_TMP;
        TDCCRACD.OUT_INF.SPRD_PNT = WS_PNT_TMP;
        TDCCRACD.OUT_INF.RATE = WS_RAT;
        WS_TWO_POINT = TDCCRACD.OUT_INF.RATE;
        bigD = new BigDecimal(WS_TWO_POINT);
        WS_TWO_POINT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        TDCCRACD.OUT_INF.RATE = WS_TWO_POINT;
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (TDCCRACD.ACO_AC.trim().length() == 0 
            && (TDCCRACD.BAL == 0 
            || TDCCRACD.TERM.trim().length() == 0 
            || TDCCRACD.BR == 0 
            || TDCCRACD.CHNL_NO.trim().length() == 0)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT);
        }
        if (TDCCRACD.CI_NO.trim().length() == 0 
            && TDCCRACD.ACO_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NO_MST_IPT);
        }
        if (TDCCRACD.RUL_CD_ALL[1-1].RUL_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_IRUL_CD_MUST_IPT);
        }
        if (TDCCRACD.RUL_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TREAT_TYPE_ERROR);
        }
        if (TDCCRACD.RAT_BAS == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_RAT);
        }
    }
    public void R000_COMPARE_RUL() throws IOException,SQLException,Exception {
        if (TDRRACD.BAL != 0) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_BAL != 0 
                || (DDRCCY.CUS_AC.trim().length() > 0 
                && WS_APP.equalsIgnoreCase("DD")) 
                || (TDRSMST.AC_NO.trim().length() > 0 
                && WS_APP.equalsIgnoreCase("TD"))) {
                if (DDRCCY.CURR_BAL > 0) {
                    WS_AC_BAS_INFO.WS_BAL = DDRCCY.CURR_BAL;
                }
                if (TDRSMST.BAL > 0) {
                    WS_AC_BAS_INFO.WS_BAL = TDRSMST.BAL;
                }
                if (WS_AC_BAS_INFO.WS_BAL > TDRRACD.BAL) {
                    WS_CHKED += 1;
                }
            } else {
                if (WS_APP.equalsIgnoreCase("DD")) {
                    DDRCCY.KEY.AC = TDCCRACD.ACO_AC;
                    T000_READ_DDTCCY();
                    if (pgmRtn) return;
                    WS_AC_BAS_INFO.WS_BAL = DDRCCY.CURR_BAL;
                }
                if (WS_APP.equalsIgnoreCase("TD")) {
                    TDRSMST.KEY.ACO_AC = TDCCRACD.ACO_AC;
                    T000_READ_TDTSMST();
                    if (pgmRtn) return;
                    WS_AC_BAS_INFO.WS_BAL = TDRSMST.BAL;
                }
                if (WS_AC_BAS_INFO.WS_BAL > TDRRACD.BAL) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.TERM.trim().length() > 0) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_AC_TERM.trim().length() > 0) {
                if (WS_AC_BAS_INFO.WS_AC_TERM.equalsIgnoreCase(TDRRACD.TERM)) {
                    WS_CHKED += 1;
                    CEP.TRC(SCCGWA, 1111);
                }
                CEP.TRC(SCCGWA, WS_AC_BAS_INFO.WS_AC_TERM);
                CEP.TRC(SCCGWA, TDRRACD.TERM);
                CEP.TRC(SCCGWA, WS_NEED_CHK);
                CEP.TRC(SCCGWA, WS_CHKED);
            } else {
                TDRSMST.KEY.ACO_AC = TDCCRACD.ACO_AC;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                WS_AC_BAS_INFO.WS_AC_TERM = TDRSMST.TERM;
                if (WS_AC_BAS_INFO.WS_AC_TERM.equalsIgnoreCase(TDRRACD.TERM)) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.SVR_LVL.trim().length() > 0) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_SVR_LVL.trim().length() > 0 
                || CICCUST.O_DATA.O_CI_NO.trim().length() > 0) {
                if (CICCUST.O_DATA.O_CI_NO.trim().length() > 0) {
                    WS_AC_BAS_INFO.WS_SVR_LVL = CICCUST.O_DATA.O_SVR_LVL;
                }
                if (WS_AC_BAS_INFO.WS_SVR_LVL.equalsIgnoreCase(TDRRACD.SVR_LVL)) {
                    WS_CHKED += 1;
                }
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = TDCCRACD.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_AC_BAS_INFO.WS_SVR_LVL = CICCUST.O_DATA.O_SVR_LVL;
                if (WS_AC_BAS_INFO.WS_SVR_LVL.equalsIgnoreCase(TDRRACD.SVR_LVL)) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.GRPS_NO.trim().length() > 0) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_GRPS_GRP[1-1].WS_GRPS_NO.trim().length() == 0) {
                if (WS_SGRS_FLG != 'Y') {
                    IBS.init(SCCGWA, CICSGRS);
                    CICSGRS.DATA.CI_NO = TDCCRACD.CI_NO;
                    S000_CALL_CICSGRS();
                    if (pgmRtn) return;
                }
                WS_GRP_NUM = 1;
                WS_GRPS_FLG = 'N';
                while (WS_GRP_NUM <= 99 
                    && CICSGRS.OUT_DATA[WS_GRP_NUM-1].GRPS_NO.trim().length() != 0) {
                    WS_AC_BAS_INFO.WS_GRPS_GRP[WS_GRP_NUM-1].WS_GRPS_NO = CICSGRS.OUT_DATA[WS_GRP_NUM-1].GRPS_NO;
                    WS_GRP_NUM += 1;
                }
                WS_GRP_NUM = (short) (WS_GRP_NUM - 1);
            }
            for (WS_K = 1; !WS_AC_BAS_INFO.WS_GRPS_GRP[WS_K-1].WS_GRPS_NO.equalsIgnoreCase(TDRRACD.GRPS_NO) 
                && WS_AC_BAS_INFO.WS_GRPS_GRP[WS_K-1].WS_GRPS_NO.trim().length() != 0 
                && WS_K <= WS_GRP_NUM; WS_K += 1) {
                CEP.TRC(SCCGWA, WS_K);
                CEP.TRC(SCCGWA, WS_AC_BAS_INFO.WS_GRPS_GRP[WS_K-1].WS_GRPS_NO);
            }
            if (WS_K <= WS_GRP_NUM) {
                if (WS_AC_BAS_INFO.WS_GRPS_GRP[WS_K-1].WS_GRPS_NO.equalsIgnoreCase(TDRRACD.GRPS_NO)) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.BR != 0) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_BR == 0 
                && (WS_APP.equalsIgnoreCase("DD") 
                && DDRCCY.CUS_AC.trim().length() > 0) 
                || (WS_APP.equalsIgnoreCase("TD") 
                && TDRSMST.AC_NO.trim().length() > 0)) {
                if (DDRCCY.OWNER_BR != 0) {
                    WS_AC_BAS_INFO.WS_BR = DDRCCY.OWNER_BR;
                }
                if (TDRSMST.OWNER_BR != 0) {
                    WS_AC_BAS_INFO.WS_BR = TDRSMST.OWNER_BR;
                }
            }
            if (WS_AC_BAS_INFO.WS_BR != 0) {
                if (WS_AC_BAS_INFO.WS_BR == TDRRACD.BR) {
                    WS_CHKED += 1;
                } else {
                    IBS.init(SCCGWA, BPCPORUP);
                    BPCPORUP.DATA_INFO.BR = WS_AC_BAS_INFO.WS_BR;
                    S000_CALL_BPZPORUP();
                    if (pgmRtn) return;
                    while (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != TDRRACD.BR 
                        && BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_SUP_BR) {
                        BPCPORUP.DATA_INFO.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                        S000_CALL_BPZPORUP();
                        if (pgmRtn) return;
                    }
                    if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR == TDRRACD.BR) {
                        WS_CHKED += 1;
                    }
                }
            } else {
                if (WS_APP.equalsIgnoreCase("DD")) {
                    DDRCCY.KEY.AC = TDCCRACD.ACO_AC;
                    T000_READ_DDTCCY();
                    if (pgmRtn) return;
                    WS_AC_BAS_INFO.WS_BR = DDRCCY.OWNER_BR;
                }
                if (WS_APP.equalsIgnoreCase("TD")) {
                    TDRSMST.KEY.ACO_AC = TDCCRACD.ACO_AC;
                    T000_READ_TDTSMST();
                    if (pgmRtn) return;
                    WS_AC_BAS_INFO.WS_BR = TDRSMST.OWNER_BR;
                }
                if (WS_AC_BAS_INFO.WS_BR == TDRRACD.BR) {
                    WS_CHKED += 1;
                } else {
                    IBS.init(SCCGWA, BPCPORUP);
                    BPCPORUP.DATA_INFO.BR = WS_AC_BAS_INFO.WS_BR;
                    S000_CALL_BPZPORUP();
                    if (pgmRtn) return;
                    while (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != TDRRACD.BR 
                        && BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR != K_SUP_BR) {
                        BPCPORUP.DATA_INFO.BR = BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR;
                        S000_CALL_BPZPORUP();
                        if (pgmRtn) return;
                    }
                    if (BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR == TDRRACD.BR) {
                        WS_CHKED += 1;
                    }
                }
            }
        }
        if (TDRRACD.ASS_LVL.trim().length() > 0 
            && !TDRRACD.ASS_LVL.equalsIgnoreCase("0000000000")) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_ASS_LVL.trim().length() > 0 
                || CICCUST.O_DATA.O_CI_NO.trim().length() > 0) {
                if (CICCUST.O_DATA.O_CI_NO.trim().length() > 0) {
                    if (CICCUST.O_DATA.O_HOLD_LVL.trim().length() == 0) WS_HOLD_LVL = 0;
                    else WS_HOLD_LVL = Short.parseShort(CICCUST.O_DATA.O_HOLD_LVL);
                    WS_H_L = (short) (WS_HOLD_LVL + 1);
                    if (WS_AC_BAS_INFO.WS_ASS_LVL == null) WS_AC_BAS_INFO.WS_ASS_LVL = "";
                    JIBS_tmp_int = WS_AC_BAS_INFO.WS_ASS_LVL.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) WS_AC_BAS_INFO.WS_ASS_LVL += " ";
                    WS_AC_BAS_INFO.WS_ASS_LVL = WS_AC_BAS_INFO.WS_ASS_LVL.substring(0, WS_H_L - 1) + "1" + WS_AC_BAS_INFO.WS_ASS_LVL.substring(WS_H_L + 1 - 1);
                }
                if (WS_AC_BAS_INFO.WS_ASS_LVL.equalsIgnoreCase(TDRRACD.ASS_LVL)) {
                    WS_CHKED += 1;
                }
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = TDCCRACD.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.O_DATA.O_HOLD_LVL.trim().length() == 0) WS_HOLD_LVL = 0;
                else WS_HOLD_LVL = Short.parseShort(CICCUST.O_DATA.O_HOLD_LVL);
                WS_H_L = (short) (WS_HOLD_LVL + 1);
                if (WS_AC_BAS_INFO.WS_ASS_LVL == null) WS_AC_BAS_INFO.WS_ASS_LVL = "";
                JIBS_tmp_int = WS_AC_BAS_INFO.WS_ASS_LVL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) WS_AC_BAS_INFO.WS_ASS_LVL += " ";
                WS_AC_BAS_INFO.WS_ASS_LVL = WS_AC_BAS_INFO.WS_ASS_LVL.substring(0, WS_H_L - 1) + "1" + WS_AC_BAS_INFO.WS_ASS_LVL.substring(WS_H_L + 1 - 1);
                if (WS_AC_BAS_INFO.WS_ASS_LVL.equalsIgnoreCase(TDRRACD.ASS_LVL)) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.CHNL_NO.trim().length() > 0) {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_CHNL_NO.trim().length() > 0 
                || (WS_APP.equalsIgnoreCase("TD") 
                && TDRSMST.AC_NO.trim().length() > 0)) {
                if (TDRSMST.CHNL_NO.trim().length() > 0) {
                    WS_AC_BAS_INFO.WS_CHNL_NO = TDRSMST.CHNL_NO;
                }
                if (WS_AC_BAS_INFO.WS_CHNL_NO.equalsIgnoreCase(TDRRACD.CHNL_NO)) {
                    WS_CHKED += 1;
                }
            } else {
                IBS.init(SCCGWA, BPROCAC);
                BPROCAC.KEY.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                BPROCAC.KEY.ACO_AC = TDCCRACD.ACO_AC;
                T000_READ_BPTOCAC();
                if (pgmRtn) return;
                WS_AC_BAS_INFO.WS_CHNL_NO = BPROCAC.CHNL_NO;
                if (WS_AC_BAS_INFO.WS_CHNL_NO == null) WS_AC_BAS_INFO.WS_CHNL_NO = "";
                JIBS_tmp_int = WS_AC_BAS_INFO.WS_CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) WS_AC_BAS_INFO.WS_CHNL_NO += " ";
                if (TDRRACD.CHNL_NO == null) TDRRACD.CHNL_NO = "";
                JIBS_tmp_int = TDRRACD.CHNL_NO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) TDRRACD.CHNL_NO += " ";
                if (WS_AC_BAS_INFO.WS_CHNL_NO.substring(0, 3).equalsIgnoreCase(TDRRACD.CHNL_NO.substring(0, 3))) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.AGE != 0) {
            WS_NEED_CHK += 1;
            WS_RACD_AGE = TDRRACD.AGE;
            IBS.CPY2CLS(SCCGWA, WS_RACD_AGE+"", REDEFINES17);
            if (WS_AC_BAS_INFO.WS_AGE != 0) {
                if (WS_AC_BAS_INFO.WS_AGE >= REDEFINES17.WS_AGE_B 
                    && WS_AC_BAS_INFO.WS_AGE <= REDEFINES17.WS_AGE_E) {
                    WS_CHKED += 1;
                }
            } else {
                if (CICCUST.O_DATA.O_CI_NO.trim().length() == 0) {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = TDCCRACD.CI_NO;
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                }
                WS_AC_BAS_INFO.WS_BIRTH = CICCUST.O_DATA.O_BIRTH_DT;
                IBS.CPY2CLS(SCCGWA, WS_AC_BAS_INFO.WS_BIRTH+"", WS_AC_BAS_INFO.REDEFINES52);
                if (WS_AC_BAS_INFO.WS_TODAY == 0) {
                    WS_AC_BAS_INFO.WS_TODAY = SCCGWA.COMM_AREA.AC_DATE;
                    IBS.CPY2CLS(SCCGWA, WS_AC_BAS_INFO.WS_TODAY+"", WS_AC_BAS_INFO.REDEFINES56);
                }
                WS_AC_BAS_INFO.WS_AGE = (short) (WS_AC_BAS_INFO.REDEFINES56.WS_TODAY_YY - WS_AC_BAS_INFO.REDEFINES52.WS_BIRTH_YY);
                if (WS_AC_BAS_INFO.REDEFINES56.WS_TODAY_MD < WS_AC_BAS_INFO.REDEFINES52.WS_BIRTH_MD) {
                    WS_AC_BAS_INFO.WS_AGE = (short) (WS_AC_BAS_INFO.WS_AGE - 1);
                }
                if (WS_AC_BAS_INFO.WS_AGE >= REDEFINES17.WS_AGE_B 
                    && WS_AC_BAS_INFO.WS_AGE <= REDEFINES17.WS_AGE_E) {
                    WS_CHKED += 1;
                }
            }
        }
        if (TDRRACD.GENDER != ' ') {
            WS_NEED_CHK += 1;
            if (WS_AC_BAS_INFO.WS_GENDER == ' ') {
                if (CICCUST.O_DATA.O_CI_NO.trim().length() == 0) {
                    IBS.init(SCCGWA, CICCUST);
                    CICCUST.FUNC = 'C';
                    CICCUST.DATA.CI_NO = TDCCRACD.CI_NO;
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                }
                WS_AC_BAS_INFO.WS_GENDER = CICCUST.O_DATA.O_SEX;
            }
            if (WS_AC_BAS_INFO.WS_GENDER == TDRRACD.GENDER) {
                WS_CHKED += 1;
            }
        }
        CEP.TRC(SCCGWA, WS_CHKED);
        CEP.TRC(SCCGWA, WS_NEED_CHK);
        CEP.TRC(SCCGWA, TDRRACD.LC_TYP);
        CEP.TRC(SCCGWA, TDRRACD.FLT_RAT);
        CEP.TRC(SCCGWA, TDRRACD.PCT_S);
        if (WS_CHKED >= WS_NEED_CHK) {
            WS_RUL_TYP1 = TDRRACD.DIF_TYP;
            WS_PNT_TMP1 = TDRRACD.FLT_RAT;
            WS_PCT_TMP1 = TDRRACD.PCT_S;
            if (TDRRACD.LC_TYP == '0') {
                WS_RAT_TMP1 = WS_RAT_TMP * ( 1 + TDRRACD.PCT_S / 100 );
            } else {
                if (TDRRACD.LC_TYP == '1') {
                    WS_RAT_TMP1 = WS_RAT_TMP + TDRRACD.FLT_RAT;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_RAT_TMP1);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZOCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-WORK-DAY", BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CICSGRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-GRS", CICSGRS);
        if (CICSGRS.RC.RC_CODE != 0) {
            IBS.init(SCCGWA, CICSGRS);
        }
        WS_SGRS_FLG = 'Y';
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void T000_READ_TDTRATE() throws IOException,SQLException,Exception {
        TDTRATE_RD = new DBParm();
        TDTRATE_RD.TableName = "TDTRATE";
        IBS.READ(SCCGWA, TDRRATE, TDTRATE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_N_EXSIT_IRUL);
        }
    }
    public void T000_READ_TDTRACD() throws IOException,SQLException,Exception {
        TDTRACD_RD = new DBParm();
        TDTRACD_RD.TableName = "TDTRACD";
        IBS.READ(SCCGWA, TDRRACD, TDTRACD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RACD_FLG = 'Y';
        } else {
            WS_RACD_FLG = 'N';
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_BPTOCAC() throws IOException,SQLException,Exception {
        BPTOCAC_RD = new DBParm();
        BPTOCAC_RD.TableName = "BPTOCAC";
        IBS.READ(SCCGWA, BPROCAC, BPTOCAC_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
