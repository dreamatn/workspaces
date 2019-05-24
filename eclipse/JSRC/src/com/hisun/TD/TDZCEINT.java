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

import java.io.IOException;
import java.sql.SQLException;

public class TDZCEINT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm TDTRACD_BR = new brParm();
    DBParm TDTOTHE_RD;
    DBParm TDTCIIN_RD;
    DBParm TDTRATE_RD;
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_YL_TYPE = "06";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_MSGID = " ";
    short WS_DEC = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_BASE_DAYS = 0;
    long WS_AMT_0 = 0;
    double WS_AMT_2 = 0;
    double WS_INT_2 = 0;
    double WS_INT_3 = 0;
    int WS_DATE1 = 0;
    TDZCEINT_REDEFINES13 REDEFINES13 = new TDZCEINT_REDEFINES13();
    int WS_DATE2 = 0;
    TDZCEINT_REDEFINES18 REDEFINES18 = new TDZCEINT_REDEFINES18();
    short WS_YY = 0;
    short WS_MM = 0;
    int WS_DD = 0;
    char WS_RULE = ' ';
    String WS_AC_RUL_CD = " ";
    double WS_RAT = 0;
    String WS_TERM = " ";
    TDZCEINT_REDEFINES29 REDEFINES29 = new TDZCEINT_REDEFINES29();
    String WS_PROD_CD = " ";
    String WS_IRAT_CD = " ";
    char WS_MATCH = ' ';
    TDZCEINT_CP_PROD_CD CP_PROD_CD = new TDZCEINT_CP_PROD_CD();
    String WS_MIN_CCYC = " ";
    double WS_MIN_AMTC = 0;
    double WS_MIN_DRAW_AMT = 0;
    double WS_MIN_LEFT_AMTC = 0;
    double WS_MAX_AMTC = 0;
    short WS_CNT = 0;
    short WS_W = 0;
    String WS_CI_HOLD_LVL = " ";
    char WS_PIOD_TYP = ' ';
    char WS_CCY_FOUND = ' ';
    char WS_PRD_RAT_XZ = ' ';
    double WS_MAX_PNT = 0;
    double WS_MAX_PCT = 0;
    double WS_CEINT_PNT = 0;
    double WS_CEINT_PCT = 0;
    short WS_AGE = 0;
    char WS_SVR_LVL = ' ';
    char WS_ASS_LVL = ' ';
    char WS_SEX = ' ';
    char WS_BIRTH_DT = ' ';
    char WS_GRS_NO = ' ';
    char WS_HOLD_NO = ' ';
    char WS_CIIN_FLG = ' ';
    short WS_TIME = 0;
    short WS_TIM = 0;
    char WS_INT_NEG_FLG = ' ';
    short WS_TI_CHK = 0;
    short WS_TIME2 = 0;
    short WS_TIME3 = 0;
    String WS_HOLD_LVL = " ";
    double WS_RAT_1 = 0;
    double WS_RAT_2 = 0;
    double WS_INT_1 = 0;
    double WS_RAT_4 = 0;
    double WS_INT_4 = 0;
    short WS_GWA_MATH = 0;
    short WS_AGE_MATH = 0;
    short WS_AGE_NUM = 0;
    TDZCEINT_REDEFINES94 REDEFINES94 = new TDZCEINT_REDEFINES94();
    short WS_TIME6 = 0;
    char WS_RUL_CD = ' ';
    short WS_RUL_TIME = 0;
    char WS_RACD_FLG = ' ';
    char WS_RATE_FLG = ' ';
    char WS_FST_FLG = ' ';
    char WS_YH_FLG = ' ';
    char WS_FH_FLG = ' ';
    char WS_RACT_FLG = ' ';
    TDZCEINT_WS_JU_RACD[] WS_JU_RACD = new TDZCEINT_WS_JU_RACD[3];
    double WS_O_PNT = 0;
    double WS_O_PCT = 0;
    double WS_O_RAT = 0;
    String WS_O_RUL_CD = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCCDS TDCCDS = new TDCCDS();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCUIRUL TDCUIRUL = new TDCUIRUL();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    CICKIDD CICKIDD = new CICKIDD();
    TDRCIIN TDRCIIN = new TDRCIIN();
    CICOKIDD CICOKIDD = new CICOKIDD();
    TDCPROD TDCPROD = new TDCPROD();
    CICCUST CICCUST = new CICCUST();
    CICSGRS CICSGRS = new CICSGRS();
    TDRRACD TDRRACD = new TDRRACD();
    TDRRATE TDRRATE = new TDRRATE();
    TDROTHE TDROTHE = new TDROTHE();
    SCCGWA SCCGWA;
    TDCCEINT TDCCEINT;
    TDCPIOD TDCPIOD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public TDZCEINT() {
        for (int i=0;i<3;i++) WS_JU_RACD[i] = new TDZCEINT_WS_JU_RACD();
    }
    public void MP(SCCGWA SCCGWA, TDCCEINT TDCCEINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCEINT = TDCCEINT;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, TDCCEINT.PROD_CD);
        WS_PROD_CD = TDCCEINT.PROD_CD;
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZCEINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        TDCPIOD = (TDCPIOD) TDCCEINT.IRUL_PTR;
        WS_BASE_DAYS = 360;
        if (TDCCEINT.CALR_STD.equalsIgnoreCase("02") 
            || TDCCEINT.CALR_STD.equalsIgnoreCase("2")) {
            WS_BASE_DAYS = 365;
        }
        if (TDCCEINT.CALR_STD.equalsIgnoreCase("03") 
            || TDCCEINT.CALR_STD.equalsIgnoreCase("3")) {
            WS_BASE_DAYS = 366;
        }
        CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_DEC = 2;
        DPCPARMP.AC_TYPE = TDCCEINT.PRDAC_CD;
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
        CEP.TRC(SCCGWA, TDCCEINT.AC_RUL_CD);
        CEP.TRC(SCCGWA, TDCCEINT.INT_STSW.FD_FLG);
        TDCCEINT.XZ_FLG = ' ';
        if (TDCCEINT.CCY.equalsIgnoreCase("156")) {
            WS_AMT_0 = (long) TDCCEINT.TXN_AMT;
            WS_AMT_2 = WS_AMT_0;
        } else {
            WS_AMT_2 = TDCCEINT.TXN_AMT;
        }
        WS_FST_FLG = 'Y';
        WS_CEINT_PNT = TDCCEINT.SPRD_PNT;
        WS_CEINT_PCT = TDCCEINT.SPRD_PCT;
        WS_O_PNT = TDCCEINT.SPRD_PNT;
        WS_O_PCT = TDCCEINT.SPRD_PCT;
        WS_AC_RUL_CD = TDCCEINT.AC_RUL_CD;
        WS_IRAT_CD = TDCCEINT.IRAT_CD;
        TDCCEINT.RACD_FLG = 'N';
        if (TDCCEINT.INT_STSW.FD_FLG == '1' 
            || TDCCEINT.INT_STSW.FD_FLG == '2') {
            WS_CEINT_PNT = 0;
            WS_CEINT_PCT = 0;
            WS_AC_RUL_CD = " ";
            TDCCEINT.RAT = 0;
        }
        if (TDCCEINT.SPRD_PCT > 0) {
            TDCCEINT.XZ_FLG = '1';
        }
        if (TDCCEINT.SPRD_PNT > 0) {
            TDCCEINT.XZ_FLG = '2';
        }
        if (TDCCEINT.RAT > 0) {
            TDCCEINT.XZ_FLG = '4';
        }
        if (TDCCEINT.OPT == '0') {
            R000_GET_RAT_CD();
            if (pgmRtn) return;
            if (TDCCEINT.RAT == 0 
                && WS_CEINT_PNT == 0 
                && WS_CEINT_PCT == 0) {
                if (TDCCEINT.INT_STSW.FD_FLG == '2') {
                } else {
                    B045_CHK_CIIN_RAT_TYP();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, TDCCEINT.AC_RUL_CD);
                if (TDCCEINT.AC_RUL_CD.trim().length() > 0) {
                    B508_RUL_GET_RAT();
                    if (pgmRtn) return;
                } else {
                    if (WS_RUL_CD == 'Y' 
                        && WS_CIIN_FLG != 'B' 
                        && TDCCEINT.INT_STSW.FD_FLG != '2') {
                        B800_PIOD_RUL_RAT();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, TDCCEINT.AC_RUL_CD);
                    } else {
                        CEP.TRC(SCCGWA, WS_CIIN_FLG);
                        CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
                        if (WS_CIIN_FLG == 'B' 
                            && TDCPIOD.ACTI_NO.trim().length() > 0) {
                            IBS.init(SCCGWA, TDROTHE);
                            TDROTHE.KEY.ACTI_NO = TDCPIOD.ACTI_NO;
                            T000_READ_TDTOTHE();
                            if (pgmRtn) return;
                            if (TDROTHE.ACTI_FLG == '0') {
                                TDCCEINT.XZ_FLG = '0';
                                TDCCEINT.RAT_1 = 0;
                                WS_CEINT_PCT = 0;
                                WS_CEINT_PNT = 0;
                                WS_RAT_4 = 0;
                            }
                        }
                        B100_GET_OPEN_INT();
                        if (pgmRtn) return;
                        WS_RAT_1 = TDCCEINT.RAT;
                        WS_INT_1 = WS_INT_3;
                        B810_SEL_FAVO_RAT();
                        if (pgmRtn) return;
                        TDCCEINT.RAT = WS_RAT_4;
                        WS_INT_3 = WS_INT_4;
                        TDCCEINT.AC_RUL_CD = WS_O_RUL_CD;
                    }
                }
            } else {
                B100_GET_OPEN_INT();
                if (pgmRtn) return;
            }
            B900_PROCESS_DEC();
            if (pgmRtn) return;
            TDCCEINT.SPRD_PCT = WS_O_PCT;
            TDCCEINT.SPRD_PNT = WS_O_PNT;
            CEP.TRC(SCCGWA, TDCCEINT.RAT);
            CEP.TRC(SCCGWA, TDCCEINT.INT);
        } else {
            B200_GET_OPEN_ACCU();
            if (pgmRtn) return;
        }
        if (TDCCEINT.XZ_FLG == ' ') {
            CEP.TRC(SCCGWA, "XZ--3");
            TDCCEINT.XZ_FLG = '0';
        }
        CEP.TRC(SCCGWA, TDCCEINT.JU_RACD.OTH_FIL);
    }
    public void B100_GET_OPEN_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        CEP.TRC(SCCGWA, TDCCEINT.RAT_1);
        if (((DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")))
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("027")
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            if (TDCCEINT.RAT == 0 
                || WS_FST_FLG == 'N') {
                R000_GET_TD_RAT();
                if (pgmRtn) return;
                WS_FST_FLG = 'N';
            }
            R000_GET_DEC_PNT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCCINTI);
            CEP.TRC(SCCGWA, WS_AMT_2);
            WS_DATE1 = TDCCEINT.VAL_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
            WS_DATE2 = TDCCEINT.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES18);
            WS_RULE = TDCCEINT.CALD_FLG;
            WS_RAT = TDCCEINT.RAT;
            if (TDCCEINT.RAT_1 != 0) {
                WS_RAT = TDCCEINT.RAT_1;
            }
            R000_COMPUTE_INT_BY_RULE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_INT_3);
        } else if (((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")))) {
            R000_GET_TD_RAT();
            if (pgmRtn) return;
            R000_GET_DEC_PNT();
            if (pgmRtn) return;
            if (TDCCEINT.EXP_DATE > TDCCEINT.VAL_DATE) {
                WS_DATE1 = TDCCEINT.VAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
                WS_DATE2 = TDCCEINT.EXP_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES18);
                WS_RULE = TDCCEINT.CALD_FLG;
                WS_RAT = TDCCEINT.RAT;
                if (TDCCEINT.RAT_1 != 0) {
                    WS_RAT = TDCCEINT.RAT_1;
                }
                R000_COMPUTE_INT_BY_RULE();
                if (pgmRtn) return;
            }
        } else if (((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")))) {
            if (TDCCEINT.TERM.compareTo("Y006") >= 0) {
                WS_TERM = "Y005";
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES29);
            } else {
                WS_TERM = TDCCEINT.TERM;
                IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES29);
                if (REDEFINES29.WS_TERM_TYP == 'Y') {
                    REDEFINES29.WS_TERM_MTHS = (short) (REDEFINES29.WS_TERM_MTHS * 12);
                    REDEFINES29.WS_TERM_TYP = 'M';
                    WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES29);
                }
            }
            R000_GET_TD_RAT();
            if (pgmRtn) return;
            WS_INT_3 = TDCCEINT.CD_AMT * ( REDEFINES29.WS_TERM_MTHS + 1 ) / 2 * REDEFINES29.WS_TERM_MTHS * TDCCEINT.RAT / 100 / 12;
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
            WS_TERM = TDCCEINT.TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES29);
            if (REDEFINES29.WS_TERM_TYP == 'Y') {
                REDEFINES29.WS_TERM_MTHS = (short) (REDEFINES29.WS_TERM_MTHS * 12);
                REDEFINES29.WS_TERM_TYP = 'M';
                WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES29);
            }
            R000_GET_TD_RAT();
            if (pgmRtn) return;
            WS_INT_3 = ( TDCCEINT.CD_AMT + TDCCEINT.TXN_AMT ) / 2 * REDEFINES29.WS_TERM_MTHS * TDCCEINT.RAT / 12;
        } else {
        }
    }
    public void B200_GET_OPEN_ACCU() throws IOException,SQLException,Exception {
        R000_CAL_VE0_DAYS();
        if (pgmRtn) return;
        R000_CAL_VE1_DAYS();
        if (pgmRtn) return;
        TDCCEINT.NACCU = TDCCDS.DAYS * ( TDCCEINT.TXN_AMT - TDCCEINT.CD_AMT * TDCCEINT.LOS_DNUM );
        TDCCEINT.SACCU = SCCCLDT.DAYS * ( TDCCEINT.TXN_AMT - TDCCEINT.CD_AMT * TDCCEINT.LOS_DNUM );
        TDCCEINT.LACCU = SCCCLDT.DAYS * TDCCEINT.CD_AMT * TDCCEINT.LOS_DNUM;
    }
    public void B045_CHK_CIIN_RAT_TYP() throws IOException,SQLException,Exception {
        if (TDCCEINT.RAT == 0 
            && TDCCEINT.AC_RUL_CD.trim().length() == 0 
            && WS_CEINT_PNT == 0 
            && WS_CEINT_PCT == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = TDCCEINT.CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            TDRCIIN.KEY.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            TDRCIIN.KEY.ID_NO = CICCUST.O_DATA.O_ID_NO;
            TDRCIIN.KEY.CI_NAME = CICCUST.O_DATA.O_CI_NM;
            TDRCIIN.KEY.PROD_CD = TDCCEINT.PROD_CD;
            TDRCIIN.KEY.CCY = TDCCEINT.CCY;
            TDRCIIN.KEY.TERM = TDCCEINT.TERM;
            TDRCIIN.KEY.SDT = SCCGWA.COMM_AREA.AC_DATE;
            TDRCIIN.DDT = SCCGWA.COMM_AREA.AC_DATE;
            TDRCIIN.MIN_BAL = TDCCEINT.TXN_AMT;
            CEP.TRC(SCCGWA, TDRCIIN.KEY.ID_TYP);
            CEP.TRC(SCCGWA, TDRCIIN.KEY.ID_NO);
            CEP.TRC(SCCGWA, TDRCIIN.KEY.CI_NAME);
            CEP.TRC(SCCGWA, TDRCIIN.KEY.PROD_CD);
            CEP.TRC(SCCGWA, TDRCIIN.KEY.CCY);
            CEP.TRC(SCCGWA, TDRCIIN.KEY.TERM);
            CEP.TRC(SCCGWA, TDRCIIN.KEY.SDT);
            CEP.TRC(SCCGWA, TDRCIIN.DDT);
            T001_READ_TDTCIIN();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, "111111111111111111");
                CEP.TRC(SCCGWA, TDRCIIN.RAT_TYP);
                CEP.TRC(SCCGWA, TDRCIIN.SPRD_PCT);
                CEP.TRC(SCCGWA, TDRCIIN.DIS_SPRD);
                CEP.TRC(SCCGWA, TDRCIIN.CON_RATE);
                if (TDRCIIN.INT_SEL == '1') {
                    WS_CIIN_FLG = 'B';
                } else {
                    WS_CIIN_FLG = 'W';
                    if (TDRCIIN.RAT_TYP == '1') {
                        CEP.TRC(SCCGWA, "XZ--4");
                        WS_CEINT_PCT = TDRCIIN.SPRD_PCT;
                        WS_MAX_PCT = TDRCIIN.SPRD_PCT;
                        WS_YH_FLG = '1';
                    } else if (TDRCIIN.RAT_TYP == '2') {
                        CEP.TRC(SCCGWA, "XZ--5");
                        WS_CEINT_PNT = TDRCIIN.DIS_SPRD;
                        WS_MAX_PNT = TDRCIIN.DIS_SPRD;
                        WS_YH_FLG = '2';
                    } else if (TDRCIIN.RAT_TYP == '4') {
                        CEP.TRC(SCCGWA, "XZ--6");
                        TDCCEINT.RAT_1 = TDRCIIN.CON_RATE;
                        WS_FST_FLG = 'N';
                        WS_YH_FLG = '4';
                    }
                    B100_GET_OPEN_INT();
                    if (pgmRtn) return;
                    if (TDCCEINT.RAT != 0) {
                        IBS.init(SCCGWA, WS_JU_RACD[3-1]);
                        WS_RAT_1 = TDCCEINT.RAT;
                        WS_INT_1 = WS_INT_3;
                        WS_JU_RACD[3-1].WS_OTH_FIL = "CIIN";
                        if (WS_JU_RACD[3-1].WS_OTH_FIL == null) WS_JU_RACD[3-1].WS_OTH_FIL = "";
                        JIBS_tmp_int = WS_JU_RACD[3-1].WS_OTH_FIL.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) WS_JU_RACD[3-1].WS_OTH_FIL += " ";
                        WS_JU_RACD[3-1].WS_OTH_FIL = "CIIN" + WS_JU_RACD[3-1].WS_OTH_FIL.substring(4);
                        B810_SEL_FAVO_RAT();
                        if (pgmRtn) return;
                    }
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCEINT.INT_STSW);
                    JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 4 - 1) + "Y" + JIBS_tmp_str[0].substring(4 + 1 - 1);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCEINT.INT_STSW);
                    WS_CI_HOLD_LVL = TDRCIIN.HOLD_LVL;
                }
            }
        }
        B065_CHK_PIOD_TYP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
    }
    public void B508_RUL_GET_RAT() throws IOException,SQLException,Exception {
        WS_YH_FLG = '3';
        B066_CHK_RUL();
        if (pgmRtn) return;
        B067_FUND_RUL();
        if (pgmRtn) return;
    }
    public void B065_CHK_PIOD_TYP() throws IOException,SQLException,Exception {
        if (TDCPIOD.ACTI.PCT_S != 0) {
            WS_CEINT_PCT = TDCPIOD.ACTI.PCT_S;
            WS_MAX_PCT = TDCPIOD.ACTI.PCT_S;
            WS_YH_FLG = '1';
        }
        if (TDCPIOD.ACTI.FD_RAT != 0) {
            WS_CEINT_PNT = TDCPIOD.ACTI.FD_RAT;
            WS_MAX_PNT = TDCPIOD.ACTI.FD_RAT;
            WS_YH_FLG = '2';
        }
        if (TDCPIOD.ACTI.HY_RAT != 0) {
            TDCCEINT.RAT_1 = TDCPIOD.ACTI.HY_RAT;
            WS_YH_FLG = '4';
        }
        if (WS_CEINT_PCT != 0 
            || WS_CEINT_PNT != 0 
            || TDCCEINT.RAT_1 != 0) {
            B100_GET_OPEN_INT();
            if (pgmRtn) return;
            WS_MAX_PCT = WS_CEINT_PCT;
            WS_MAX_PNT = WS_CEINT_PNT;
            WS_RAT_1 = TDCCEINT.RAT;
            WS_INT_1 = WS_INT_3;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        WS_RUL_TIME = 0;
        CEP.TRC(SCCGWA, TDCCEINT.CCY);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
        while (WS_RUL_TIME <= 12 
            && !TDCCEINT.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].MIN_CCYC)) {
            WS_RUL_TIME += 1;
            CEP.TRC(SCCGWA, WS_RUL_TIME);
            if (TDCCEINT.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].MIN_CCYC)) {
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD1);
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD1.trim().length() > 0) {
                    WS_RUL_CD = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_RUL_TIME);
        CEP.TRC(SCCGWA, WS_RUL_CD);
    }
    public void B800_PIOD_RUL_RAT() throws IOException,SQLException,Exception {
        B066_CHK_RUL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "-------------2");
        CEP.TRC(SCCGWA, WS_RAT_4);
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD1;
        B067_FUND_RUL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "-------------3");
        CEP.TRC(SCCGWA, WS_RAT_4);
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        B810_SEL_FAVO_RAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "-------------4");
        CEP.TRC(SCCGWA, WS_RAT_4);
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD2.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD2;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD3.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD3;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD4.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD4;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD5.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD5;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD6.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD6;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD7.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD7;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD8.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD8;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD9.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD9;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD10.trim().length() > 0) {
            TDCCEINT.AC_RUL_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RUL_CD.RUL_CD10;
            B067_FUND_RUL();
            if (pgmRtn) return;
            B810_SEL_FAVO_RAT();
            if (pgmRtn) return;
        }
        TDCCEINT.RAT = WS_RAT_4;
        WS_INT_3 = WS_INT_4;
        TDCCEINT.AC_RUL_CD = WS_O_RUL_CD;
    }
    public void B810_SEL_FAVO_RAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].FAVO_FLG);
        CEP.TRC(SCCGWA, WS_RUL_TIME);
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        CEP.TRC(SCCGWA, WS_RAT_4);
        WS_FH_FLG = 'N';
        CEP.TRC(SCCGWA, WS_RAT_1);
        CEP.TRC(SCCGWA, WS_RAT_4);
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].FAVO_FLG != '1') {
            if (WS_RAT_1 > WS_RAT_4) {
                WS_FH_FLG = 'Y';
            }
        } else {
            if (WS_RAT_1 < WS_RAT_4 
                || WS_RAT_4 == 0) {
                WS_FH_FLG = 'Y';
            }
        }
        if (WS_FH_FLG == 'Y') {
            WS_RAT_4 = WS_RAT_1;
            TDCCEINT.RAT = WS_RAT_1;
            WS_INT_4 = WS_INT_1;
            WS_O_RUL_CD = TDCCEINT.AC_RUL_CD;
            WS_O_PNT = WS_MAX_PNT;
            WS_O_PCT = WS_MAX_PCT;
            if (WS_YH_FLG == '1') {
                TDCCEINT.XZ_FLG = '1';
            } else if (WS_YH_FLG == '2') {
                TDCCEINT.XZ_FLG = '2';
            } else if (WS_YH_FLG == '3') {
                TDCCEINT.XZ_FLG = '3';
            } else if (WS_YH_FLG == '4') {
                TDCCEINT.XZ_FLG = '4';
            } else {
                TDCCEINT.XZ_FLG = '0';
            }
            if (WS_YH_FLG != '0') {
                if (WS_RACT_FLG == 'Y') {
                    TDCCEINT.RACD_FLG = 'Y';
                } else {
                    TDCCEINT.RACD_FLG = 'N';
                }
            }
            TDCCEINT.JU_RACD.RACD_BAL = WS_JU_RACD[3-1].WS_RACD_BAL;
            TDCCEINT.JU_RACD.RACD_TERM = WS_JU_RACD[3-1].WS_RACD_TERM;
            TDCCEINT.JU_RACD.RACD_SVR_LVL = WS_JU_RACD[3-1].WS_RACD_SVR_LVL;
            TDCCEINT.JU_RACD.RACD_GRPS_NO = WS_JU_RACD[3-1].WS_RACD_GRPS_NO;
            TDCCEINT.JU_RACD.RACD_BR = WS_JU_RACD[3-1].WS_RACD_BR;
            TDCCEINT.JU_RACD.RACD_ASS_LVL = WS_JU_RACD[3-1].WS_RACD_ASS_LVL;
            TDCCEINT.JU_RACD.RACD_CHNL_NO = WS_JU_RACD[3-1].WS_RACD_CHNL_NO;
            TDCCEINT.JU_RACD.RACD_AGE = WS_JU_RACD[3-1].WS_RACD_AGE;
            TDCCEINT.JU_RACD.RACD_GENDER = WS_JU_RACD[3-1].WS_RACD_GENDER;
            TDCCEINT.JU_RACD.OTH_FIL = WS_JU_RACD[3-1].WS_OTH_FIL;
        }
        WS_RACT_FLG = ' ';
        WS_YH_FLG = ' ';
        WS_MAX_PNT = 0;
        WS_MAX_PCT = 0;
        WS_CEINT_PCT = 0;
        WS_CEINT_PNT = 0;
        IBS.init(SCCGWA, WS_JU_RACD[1-1]);
        IBS.init(SCCGWA, WS_JU_RACD[2-1]);
        IBS.init(SCCGWA, WS_JU_RACD[3-1]);
        TDCCEINT.RAT_1 = 0;
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        CEP.TRC(SCCGWA, WS_O_RUL_CD);
    }
    public void B900_PROCESS_DEC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DEC);
        WS_INT_NEG_FLG = '0';
        CEP.TRC(SCCGWA, WS_INT_3);
        if (WS_INT_3 < 0) {
            WS_INT_3 = WS_INT_3 * ( -1 );
            CEP.TRC(SCCGWA, WS_INT_3);
            WS_INT_NEG_FLG = '1';
        }
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = TDCCEINT.CCY;
        BPCRDAMT.AMT = WS_INT_3;
        S00_CALL_BPZRDAMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        TDCCEINT.INT = BPCRDAMT.RESULT_AMT;
        if (WS_INT_NEG_FLG == '1') {
            CEP.TRC(SCCGWA, "NEG");
            TDCCEINT.INT = TDCCEINT.INT * ( -1 );
        }
        CEP.TRC(SCCGWA, TDCCEINT.INT);
    }
    public void R000_GET_TD_RAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCEINT.RAT_1);
        if (TDCCEINT.RAT_1 == 0) {
            CEP.TRC(SCCGWA, WS_CEINT_PCT);
            if (!IBS.isNumeric(WS_CEINT_PCT+"") 
                || !IBS.isNumeric(WS_CEINT_PNT+"")) {
                IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_IR_DATA_BAD, TDCCEINT.RC_MSG);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCCINTI);
            BPCCINTI.FUNC = 'I';
            CEP.TRC(SCCGWA, TDCCEINT.IRAT_CD);
            if (WS_CEINT_PCT != 0 
                || WS_CEINT_PNT != 0) {
                if (TDCCEINT.IRAT_CD == null) TDCCEINT.IRAT_CD = "";
                JIBS_tmp_int = TDCCEINT.IRAT_CD.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) TDCCEINT.IRAT_CD += " ";
                if (TDCCEINT.IRAT_CD.substring(0, 1).equalsIgnoreCase("C") 
                    || TDCCEINT.IRAT_CD.substring(0, 1).equalsIgnoreCase("D")) {
                    if (TDCCEINT.IRAT_CD == null) TDCCEINT.IRAT_CD = "";
                    JIBS_tmp_int = TDCCEINT.IRAT_CD.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) TDCCEINT.IRAT_CD += " ";
                    TDCCEINT.IRAT_CD = "A" + TDCCEINT.IRAT_CD.substring(1);
                }
            }
            BPCCINTI.BASE_INFO.BASE_TYP = TDCCEINT.IRAT_CD;
            BPCCINTI.BASE_INFO.CCY = TDCCEINT.CCY;
            CEP.TRC(SCCGWA, TDCCEINT.TERM);
            BPCCINTI.BASE_INFO.TENOR = TDCCEINT.TERM;
            CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.OTH_FLG);
            if (TDCPIOD.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_YL_TYPE)) {
                BPCCINTI.BASE_INFO.TENOR = "S000";
            }
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
            BPCCINTI.BASE_INFO.DT = TDCCEINT.VAL_DATE;
            if (TDCCEINT.AC_BR != 0) {
                BPCCINTI.BASE_INFO.BR = TDCCEINT.AC_BR;
            } else {
                BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            }
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
            if (TDCCEINT.RAT_XS == 0) {
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
            }
            TDCCEINT.RAT = BPCCINTI.BASE_INFO.RATE;
            CEP.TRC(SCCGWA, WS_CEINT_PCT);
            CEP.TRC(SCCGWA, TDCCEINT.RAT);
            if (WS_CEINT_PCT != 0) {
                CEP.TRC(SCCGWA, "TEST");
                CEP.TRC(SCCGWA, "XZ--9");
                TDCCEINT.RAT = TDCCEINT.RAT * ( 1 + WS_CEINT_PCT / 100 );
                if (TDCCEINT.RAT < 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_INPUT_RATE_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (WS_CEINT_PNT != 0) {
                    CEP.TRC(SCCGWA, "XZ--10");
                    TDCCEINT.RAT = TDCCEINT.RAT + WS_CEINT_PNT;
                    CEP.TRC(SCCGWA, TDCCEINT.RAT);
                    if (TDCCEINT.RAT < 0) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_INPUT_RATE_ERROR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            TDCCEINT.RAT = TDCCEINT.RAT_1;
            TDCCEINT.RAT_1 = 0;
            CEP.TRC(SCCGWA, "XZ--11");
        }
        TDCCEINT.IRAT_CD = WS_IRAT_CD;
    }
    public void R000_CAL_CLDT_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DATE2 = WS_DATE2;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCEINT.DAYS = SCCCLDT.DAYS;
    }
    public void R000_GET_YMD() throws IOException,SQLException,Exception {
        if (WS_DATE2 < WS_DATE1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT, TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_YY = 0;
        WS_MM = (short) (( REDEFINES18.WS_DATE2_YY - REDEFINES13.WS_DATE1_YY ) * 12 + REDEFINES18.WS_DATE2_MM - REDEFINES13.WS_DATE1_MM);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE2;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (REDEFINES18.WS_DATE2_DD < REDEFINES13.WS_DATE1_DD 
            && REDEFINES18.WS_DATE2_DD < SCCCKDT.MTH_DAYS) {
            WS_MM = (short) (WS_MM - 1);
        }
        if (WS_MM != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_DATE1;
            SCCCLDT.MTHS = WS_MM;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE1 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DATE2 = WS_DATE2;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DD = SCCCLDT.DAYS;
    }
    public void R000_GET_YD() throws IOException,SQLException,Exception {
        if (WS_DATE2 < WS_DATE1) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT, TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_MM = (short) (( REDEFINES18.WS_DATE2_YY - REDEFINES13.WS_DATE1_YY ) * 12 + REDEFINES18.WS_DATE2_MM - REDEFINES13.WS_DATE1_MM);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE2;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
        if (REDEFINES18.WS_DATE2_DD < REDEFINES13.WS_DATE1_DD 
            && REDEFINES18.WS_DATE2_DD < SCCCKDT.MTH_DAYS) {
            WS_MM = (short) (WS_MM - 1);
        }
        WS_YY = (short) (WS_MM / 12);
        WS_MM = (short) (WS_YY * 12);
        if (WS_MM != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_DATE1;
            SCCCLDT.MTHS = WS_MM;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DATE1 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES13);
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DATE2 = WS_DATE2;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DD = SCCCLDT.DAYS;
    }
    public void R000_COMPUTE_INT_BY_RULE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RULE);
        CEP.TRC(SCCGWA, TDCCEINT.RAT_XS);
        if (TDCCEINT.RAT_XS > 0) {
            WS_RAT = TDCCEINT.RAT_XS;
        }
        if (WS_RULE == '1') {
            R000_GET_YMD();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_2 * WS_YY * WS_RAT / 100 + WS_AMT_2 * WS_MM * WS_RAT / 1200 + WS_AMT_2 * WS_DD * WS_RAT / 100 / WS_BASE_DAYS;
        } else if (WS_RULE == '2') {
            R000_GET_YD();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_2 * WS_YY * WS_RAT / 100 + WS_AMT_2 * WS_DD * WS_RAT / 100 / WS_BASE_DAYS;
        } else if (WS_RULE == '3') {
            R000_CAL_CLDT_DAYS();
            if (pgmRtn) return;
            WS_INT_3 = WS_AMT_2 * WS_RAT / 100 * TDCCEINT.DAYS / WS_BASE_DAYS;
        } else {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_COUNT_RULE_ERROR, TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_RULE);
        CEP.TRC(SCCGWA, WS_INT_3);
    }
    public void R000_GET_DEC_PNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = TDCCEINT.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        WS_DEC = BPCQCCY.DATA.DEC_MTH;
    }
    public void R000_GET_RAT_CD() throws IOException,SQLException,Exception {
        WS_RUL_TIME = 0;
        while (WS_RUL_TIME <= 10 
            && !TDCCEINT.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].MIN_CCYC)) {
            WS_RUL_TIME += 1;
            CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].MIN_CCYC);
            CEP.TRC(SCCGWA, WS_RUL_TIME);
            if (TDCCEINT.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].MIN_CCYC)) {
                if (TDCCEINT.IRAT_CD.trim().length() == 0) {
                    TDCCEINT.IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].RAT_CD;
                }
                if (TDCCEINT.CALD_FLG == ' ') {
                    TDCCEINT.CALD_FLG = TDCPIOD.OTH_PRM.CCY_INF[WS_RUL_TIME-1].INT_RUL;
                }
                CEP.TRC(SCCGWA, TDCCEINT.CALD_FLG);
                CEP.TRC(SCCGWA, TDCCEINT.IRAT_CD);
            }
        }
        WS_IRAT_CD = TDCCEINT.IRAT_CD;
    }
    public void R000_CAL_VE0_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCDS);
        TDCCDS.BEG_DATE = TDCCEINT.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = TDCCEINT.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
        S000_CALL_TDZCDS();
        if (pgmRtn) return;
        TDCCEINT.DAYS = TDCCDS.DAYS;
    }
    public void R000_CAL_VE1_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDCCEINT.VAL_DATE;
        SCCCLDT.DATE2 = TDCCEINT.EXP_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        TDCCEINT.DAYS = SCCCLDT.DAYS;
    }
    public void B066_CHK_RUL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        WS_SVR_LVL = 'N';
        WS_SEX = 'N';
        WS_BIRTH_DT = 'N';
        WS_GRS_NO = 'N';
        WS_HOLD_NO = 'N';
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = TDCCEINT.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SVR_LVL);
        if (CICCUST.O_DATA.O_SVR_LVL.trim().length() > 0) {
            WS_SVR_LVL = 'Y';
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SEX);
        if (CICCUST.O_DATA.O_SEX != ' ') {
            WS_SEX = 'Y';
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_BIRTH_DT);
        if (CICCUST.O_DATA.O_BIRTH_DT != 0) {
            WS_BIRTH_DT = 'Y';
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_GWA_MATH = 0;
            else WS_GWA_MATH = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
            JIBS_tmp_str[0] = "" + CICCUST.O_DATA.O_BIRTH_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_AGE_MATH = 0;
            else WS_AGE_MATH = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
            WS_AGE = (short) (WS_GWA_MATH - WS_AGE_MATH);
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_HOLD_LVL);
        if (WS_CI_HOLD_LVL.trim().length() > 0 
            && WS_CI_HOLD_LVL.compareTo(CICCUST.O_DATA.O_HOLD_LVL) > 0) {
            CICCUST.O_DATA.O_HOLD_LVL = WS_CI_HOLD_LVL;
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_HOLD_LVL);
        IBS.init(SCCGWA, CICSGRS);
        CICSGRS.DATA.CI_NO = TDCCEINT.CI_NO;
        S000_CALL_CICSGRS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSGRS.OUT_DATA[1-1].GRPS_NO);
        if (CICSGRS.OUT_DATA[1-1].GRPS_NO.trim().length() > 0) {
            WS_GRS_NO = 'Y';
        }
    }
    public void S000_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_CICSGRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-GRS", CICSGRS);
        if (CICSGRS.RC.RC_CODE != 0) {
            IBS.init(SCCGWA, CICSGRS);
        }
    }
    public void B067_FUND_RUL() throws IOException,SQLException,Exception {
        WS_MAX_PNT = 0;
        WS_MAX_PCT = 0;
        TDRRACD.KEY.RUL_CD = TDCCEINT.AC_RUL_CD;
        CEP.TRC(SCCGWA, "JF00");
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        R000_PERFORM_TDTRACD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "JF000");
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        if (WS_MAX_PCT != 0) {
            WS_CEINT_PCT = WS_MAX_PCT;
            CEP.TRC(SCCGWA, WS_MAX_PCT);
            WS_CEINT_PNT = 0;
            B100_GET_OPEN_INT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "JF01");
            CEP.TRC(SCCGWA, TDCCEINT.RAT);
            WS_RAT_1 = TDCCEINT.RAT;
            WS_INT_1 = WS_INT_3;
        }
        if (WS_MAX_PNT != 0) {
            WS_CEINT_PNT = WS_MAX_PNT;
            CEP.TRC(SCCGWA, WS_MAX_PNT);
            WS_CEINT_PCT = 0;
            B100_GET_OPEN_INT();
            if (pgmRtn) return;
            WS_RAT_2 = TDCCEINT.RAT;
            CEP.TRC(SCCGWA, "JF01");
            CEP.TRC(SCCGWA, TDCCEINT.RAT);
        }
        CEP.TRC(SCCGWA, "------------1");
        CEP.TRC(SCCGWA, WS_RAT_1);
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        CEP.TRC(SCCGWA, WS_RAT_1);
        CEP.TRC(SCCGWA, WS_RAT_2);
        if (WS_RAT_1 > WS_RAT_2) {
            WS_JU_RACD[3-1].WS_RACD_BAL = WS_JU_RACD[1-1].WS_RACD_BAL;
            WS_JU_RACD[3-1].WS_RACD_TERM = WS_JU_RACD[1-1].WS_RACD_TERM;
            WS_JU_RACD[3-1].WS_RACD_SVR_LVL = WS_JU_RACD[1-1].WS_RACD_SVR_LVL;
            WS_JU_RACD[3-1].WS_RACD_GRPS_NO = WS_JU_RACD[1-1].WS_RACD_GRPS_NO;
            WS_JU_RACD[3-1].WS_RACD_BR = WS_JU_RACD[1-1].WS_RACD_BR;
            WS_JU_RACD[3-1].WS_RACD_ASS_LVL = WS_JU_RACD[1-1].WS_RACD_ASS_LVL;
            WS_JU_RACD[3-1].WS_RACD_CHNL_NO = WS_JU_RACD[1-1].WS_RACD_CHNL_NO;
            WS_JU_RACD[3-1].WS_RACD_AGE = WS_JU_RACD[1-1].WS_RACD_AGE;
            WS_JU_RACD[3-1].WS_RACD_GENDER = WS_JU_RACD[1-1].WS_RACD_GENDER;
            WS_YH_FLG = '1';
            WS_MAX_PNT = 0;
            WS_JU_RACD[3-1].WS_OTH_FIL = " ";
            if (TDCPIOD.ACTI_NO.trim().length() > 0) {
                WS_RACT_FLG = 'Y';
            }
        } else {
            if (WS_RAT_2 > 0) {
                WS_RAT_1 = WS_RAT_2;
                WS_INT_1 = WS_INT_3;
                WS_JU_RACD[3-1].WS_RACD_BAL = WS_JU_RACD[2-1].WS_RACD_BAL;
                WS_JU_RACD[3-1].WS_RACD_TERM = WS_JU_RACD[2-1].WS_RACD_TERM;
                WS_JU_RACD[3-1].WS_RACD_SVR_LVL = WS_JU_RACD[2-1].WS_RACD_SVR_LVL;
                WS_JU_RACD[3-1].WS_RACD_GRPS_NO = WS_JU_RACD[2-1].WS_RACD_GRPS_NO;
                WS_JU_RACD[3-1].WS_RACD_BR = WS_JU_RACD[2-1].WS_RACD_BR;
                WS_JU_RACD[3-1].WS_RACD_ASS_LVL = WS_JU_RACD[2-1].WS_RACD_ASS_LVL;
                WS_JU_RACD[3-1].WS_RACD_CHNL_NO = WS_JU_RACD[2-1].WS_RACD_CHNL_NO;
                WS_JU_RACD[3-1].WS_RACD_AGE = WS_JU_RACD[2-1].WS_RACD_AGE;
                WS_JU_RACD[3-1].WS_RACD_GENDER = WS_JU_RACD[2-1].WS_RACD_GENDER;
                WS_YH_FLG = '2';
                WS_MAX_PCT = 0;
                WS_JU_RACD[3-1].WS_OTH_FIL = " ";
                if (TDCPIOD.ACTI_NO.trim().length() > 0) {
                    WS_RACT_FLG = 'Y';
                }
            }
        }
        if (WS_MAX_PCT == 0 
            && WS_MAX_PNT == 0) {
            B100_GET_OPEN_INT();
            if (pgmRtn) return;
            if (WS_YH_FLG != '3') {
                WS_YH_FLG = '0';
            }
            WS_RAT_1 = TDCCEINT.RAT;
            WS_INT_1 = WS_INT_3;
        }
    }
    public void R000_PERFORM_TDTRACD() throws IOException,SQLException,Exception {
        WS_RACD_FLG = 'N';
        CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
        R000_CHECK_RATE_VALUE();
        if (pgmRtn) return;
        if (WS_RATE_FLG == 'Y') {
            TDTRACD_BR.rp = new DBParm();
            TDTRACD_BR.rp.TableName = "TDTRACD";
            TDTRACD_BR.rp.where = "RUL_CD = :TDRRACD.KEY.RUL_CD";
            TDTRACD_BR.rp.order = "NUM";
            IBS.STARTBR(SCCGWA, TDRRACD, this, TDTRACD_BR);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                T003_READNEXT_TDTRACD();
                if (pgmRtn) return;
                if (WS_RACD_FLG == 'Y') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TIPS_IRUL_NOT_EXIST);
                }
                WS_TIME = 0;
                while (WS_TIME <= 30 
                    && WS_RACD_FLG != 'Y') {
                    CEP.TRC(SCCGWA, WS_TIME);
                    B068_SELE_RUL();
                    if (pgmRtn) return;
                    T003_READNEXT_TDTRACD();
                    if (pgmRtn) return;
                }
            }
            T000_ENDBR_TDTRACD();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_RATE_VALUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRRATE);
        TDRRATE.KEY.RUL_CD = TDRRACD.KEY.RUL_CD;
        T000_READ_TDTRATE();
        if (pgmRtn) return;
        if (WS_RATE_FLG == 'Y' 
            && TDRRATE.SDT <= TDCCEINT.VAL_DATE 
            && TDRRATE.DDT > TDCCEINT.VAL_DATE) {
        } else {
            if (WS_AC_RUL_CD.equalsIgnoreCase(TDRRATE.KEY.RUL_CD) 
                && TDCCEINT.INT_STSW.RATC_FLG != '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TIPS_IRUL_NOT_EXIST);
            }
            WS_RATE_FLG = 'N';
        }
    }
    public void T003_READNEXT_TDTRACD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRRACD, this, TDTRACD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TIME += 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RACD_FLG = 'Y';
        } else {
            WS_RACD_FLG = 'Y';
        }
    }
    public void T000_ENDBR_TDTRACD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTRACD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TIME += 1;
        }
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
    }
    public void T000_READ_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        IBS.READ(SCCGWA, TDRCIIN, TDTCIIN_RD);
    }
    public void T000_READ_TDTRATE() throws IOException,SQLException,Exception {
        TDTRATE_RD = new DBParm();
        TDTRATE_RD.TableName = "TDTRATE";
        IBS.READ(SCCGWA, TDRRATE, TDTRATE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RATE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RATE_FLG = 'N';
        } else {
            WS_RATE_FLG = 'N';
        }
    }
    public void T001_READ_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        TDTCIIN_RD.where = "ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND ( PROD_CD = :TDRCIIN.KEY.PROD_CD "
            + "OR PROD_CD = ' ' ) "
            + "AND ( CCY = :TDRCIIN.KEY.CCY "
            + "OR CCY = ' ' ) "
            + "AND ( TERM = :TDRCIIN.KEY.TERM "
            + "OR TERM = ' ' ) "
            + "AND MIN_BAL <= :TDRCIIN.MIN_BAL "
            + "AND SDT <= :TDRCIIN.KEY.SDT "
            + "AND DDT > :TDRCIIN.DDT";
        IBS.READ(SCCGWA, TDRCIIN, this, TDTCIIN_RD);
    }
    public void B068_SELE_RUL() throws IOException,SQLException,Exception {
        WS_TIME6 = 0;
        WS_TI_CHK = 0;
        CEP.TRC(SCCGWA, TDCCEINT.TXN_AMT);
        CEP.TRC(SCCGWA, TDRRACD.BAL);
        if (TDRRACD.BAL != 0) {
            WS_TIME6 += 1;
            if (TDRRACD.BAL <= TDCCEINT.TXN_AMT) {
                WS_TI_CHK += 1;
            }
        }
        if (TDRRACD.TERM.trim().length() > 0) {
            WS_TIME6 += 1;
            if (TDRRACD.TERM.equalsIgnoreCase(TDCCEINT.TERM)) {
                WS_TI_CHK += 1;
            }
        }
        if (TDRRACD.BR != 0) {
            WS_TIME6 += 1;
            if (TDRRACD.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_TI_CHK += 1;
            }
        }
        if (TDRRACD.CHNL_NO.trim().length() > 0) {
            WS_TIME6 += 1;
            if (TDRRACD.CHNL_NO == null) TDRRACD.CHNL_NO = "";
            JIBS_tmp_int = TDRRACD.CHNL_NO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) TDRRACD.CHNL_NO += " ";
            if (TDRRACD.CHNL_NO == null) TDRRACD.CHNL_NO = "";
            JIBS_tmp_int = TDRRACD.CHNL_NO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) TDRRACD.CHNL_NO += " ";
            if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
            if (TDRRACD.CHNL_NO.equalsIgnoreCase(SCCGWA.COMM_AREA.CHNL) 
                || (TDRRACD.CHNL_NO.substring(4 - 1, 4 + 1 - 1).trim().length() == 0 
                && TDRRACD.CHNL_NO.substring(0, 3).equalsIgnoreCase(SCCGWA.COMM_AREA.CHNL.substring(0, 3)))) {
                WS_TI_CHK += 1;
            }
        }
        CEP.TRC(SCCGWA, TDRRACD.GRPS_NO);
        CEP.TRC(SCCGWA, CICSGRS.OUT_DATA[1-1].GRPS_NO);
        if (TDRRACD.GRPS_NO.trim().length() > 0) {
            WS_TIME6 += 1;
            for (WS_TIME2 = 1; WS_TIME2 <= 99 
                && CICSGRS.OUT_DATA[WS_TIME2-1].GRPS_NO.trim().length() != 0; WS_TIME2 += 1) {
                if (TDRRACD.GRPS_NO.equalsIgnoreCase(CICSGRS.OUT_DATA[WS_TIME2-1].GRPS_NO)) {
                    WS_TI_CHK += 1;
                    WS_TIME2 = 100;
                }
            }
        }
        if (TDRRACD.SVR_LVL.trim().length() > 0) {
            WS_TIME6 += 1;
            if (TDRRACD.SVR_LVL.equalsIgnoreCase(CICCUST.O_DATA.O_SVR_LVL)) {
                WS_TI_CHK += 1;
            }
        }
        CEP.TRC(SCCGWA, TDRRACD.ASS_LVL);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_HOLD_LVL);
        if (TDRRACD.ASS_LVL.trim().length() > 0 
            && !TDRRACD.ASS_LVL.equalsIgnoreCase("0000000000")) {
            WS_TIME6 += 1;
            if (TDRRACD.ASS_LVL == null) TDRRACD.ASS_LVL = "";
            JIBS_tmp_int = TDRRACD.ASS_LVL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.ASS_LVL += " ";
            for (WS_TIME2 = 1; WS_TIME2 <= 10 
                && TDRRACD.ASS_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 1 - 1).trim().length() != 0; WS_TIME2 += 1) {
                if (TDRRACD.ASS_LVL == null) TDRRACD.ASS_LVL = "";
                JIBS_tmp_int = TDRRACD.ASS_LVL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.ASS_LVL += " ";
                CEP.TRC(SCCGWA, TDRRACD.ASS_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 2 - 1));
                CEP.TRC(SCCGWA, WS_TIME2);
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_HOLD_LVL);
                if (TDRRACD.ASS_LVL == null) TDRRACD.ASS_LVL = "";
                JIBS_tmp_int = TDRRACD.ASS_LVL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.ASS_LVL += " ";
                if (TDRRACD.ASS_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_TIME3 = (short) (WS_TIME2 - 1);
                    if (WS_HOLD_LVL == null) WS_HOLD_LVL = "";
                    JIBS_tmp_int = WS_HOLD_LVL.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) WS_HOLD_LVL += " ";
                    JIBS_tmp_str[0] = "" + WS_TIME3;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_HOLD_LVL = JIBS_tmp_str[0] + WS_HOLD_LVL.substring(1);
                    if (WS_HOLD_LVL.equalsIgnoreCase(CICCUST.O_DATA.O_HOLD_LVL)) {
                        WS_TI_CHK += 1;
                        WS_TIME2 = 10;
                    }
                }
            }
        }
        if (TDRRACD.GENDER != ' ') {
            WS_TIME6 += 1;
            if (TDRRACD.GENDER == CICCUST.O_DATA.O_SEX) {
                WS_TI_CHK += 1;
            }
        }
        if (TDRRACD.AGE != 0) {
            WS_TIME6 += 1;
            WS_AGE_NUM = TDRRACD.AGE;
            IBS.CPY2CLS(SCCGWA, WS_AGE_NUM+"", REDEFINES94);
            if (REDEFINES94.WS_AGE_B <= WS_AGE 
                && REDEFINES94.WS_AGE_E >= WS_AGE) {
                WS_TI_CHK += 1;
            }
        }
        CEP.TRC(SCCGWA, WS_TIME6);
        CEP.TRC(SCCGWA, WS_TI_CHK);
        if (WS_TI_CHK >= WS_TIME6) {
            CEP.TRC(SCCGWA, TDRRACD.LC_TYP);
            CEP.TRC(SCCGWA, TDRRACD.KEY.NUM);
            if (TDRRACD.LC_TYP == '0') {
                if (TDRRACD.PCT_S > WS_MAX_PCT) {
                    CEP.TRC(SCCGWA, TDRRACD.PCT_S);
                    WS_MAX_PCT = TDRRACD.PCT_S;
                    WS_JU_RACD[1-1].WS_RACD_BAL = TDRRACD.BAL;
                    WS_JU_RACD[1-1].WS_RACD_TERM = TDRRACD.TERM;
                    WS_JU_RACD[1-1].WS_RACD_SVR_LVL = TDRRACD.SVR_LVL;
                    WS_JU_RACD[1-1].WS_RACD_GRPS_NO = TDRRACD.GRPS_NO;
                    WS_JU_RACD[1-1].WS_RACD_BR = TDRRACD.BR;
                    WS_JU_RACD[1-1].WS_RACD_ASS_LVL = TDRRACD.ASS_LVL;
                    WS_JU_RACD[1-1].WS_RACD_CHNL_NO = TDRRACD.CHNL_NO;
                    WS_JU_RACD[1-1].WS_RACD_AGE = TDRRACD.AGE;
                    WS_JU_RACD[1-1].WS_RACD_GENDER = TDRRACD.GENDER;
                }
            } else {
                if (TDRRACD.FLT_RAT > WS_MAX_PNT) {
                    CEP.TRC(SCCGWA, TDRRACD.FLT_RAT);
                    WS_MAX_PNT = TDRRACD.FLT_RAT;
                    WS_JU_RACD[2-1].WS_RACD_BAL = TDRRACD.BAL;
                    WS_JU_RACD[2-1].WS_RACD_TERM = TDRRACD.TERM;
                    WS_JU_RACD[2-1].WS_RACD_SVR_LVL = TDRRACD.SVR_LVL;
                    WS_JU_RACD[2-1].WS_RACD_GRPS_NO = TDRRACD.GRPS_NO;
                    WS_JU_RACD[2-1].WS_RACD_BR = TDRRACD.BR;
                    WS_JU_RACD[2-1].WS_RACD_ASS_LVL = TDRRACD.ASS_LVL;
                    WS_JU_RACD[2-1].WS_RACD_CHNL_NO = TDRRACD.CHNL_NO;
                    WS_JU_RACD[2-1].WS_RACD_AGE = TDRRACD.AGE;
                    WS_JU_RACD[2-1].WS_RACD_GENDER = TDRRACD.GENDER;
                }
            }
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            WS_RC_DISP = SCCCLDT.RC;
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_MSGID = K_SYS_ERR;
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR! " + WS_RC_DISP;
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
    public void S00_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZCDS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-DAYS", TDCCDS);
        if (TDCCDS.RC_MSG.RC != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.RC_MSG);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCEINT.RC_MSG);
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
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        CEP.TRC(SCCGWA, BPCCINTI.RC);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZUIRUL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-IRULP", TDCUIRUL);
        if (TDCUIRUL.RC_MSG.RC != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCUIRUL.RC_MSG);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCCEINT.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDCUIRUL.RTN_MATCH == 'Y') {
            if (TDCUIRUL.IRAT_CD.trim().length() > 0) {
                TDCCEINT.IRAT_CD = TDCUIRUL.IRAT_CD;
            }
            if (TDCUIRUL.SPRD_PNT != 0) {
                WS_CEINT_PNT = TDCUIRUL.SPRD_PNT;
            }
            if (TDCUIRUL.SPRD_PCT != 0) {
                WS_CEINT_PCT = TDCUIRUL.SPRD_PCT;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCCEINT.RC_MSG.RC != 0) {
            CEP.TRC(SCCGWA, "TDCCEINT=");
            CEP.TRC(SCCGWA, TDCCEINT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
