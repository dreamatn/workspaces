package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPRPARM;
import com.hisun.BP.BPRPARP;
import com.hisun.BP.BPRTRT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;

public class LNZFRATE {
    boolean pgmRtn = false;
    int K_BANK_99 = 999999;
    LNZFRATE_WS_MSGID WS_MSGID = new LNZFRATE_WS_MSGID();
    String WS_ERR_INFO = " ";
    double WS_N_RATE = 0;
    double WS_O_RATE = 0;
    double WS_L_RATE = 0;
    double WS_P_RATE = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    LNCFRATE LNCFRATE;
    public void MP(SCCGWA SCCGWA, LNCFRATE LNCFRATE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCFRATE = LNCFRATE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZFRATE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCFRATE.RC.RC_APP = "LN";
        LNCFRATE.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.BOOK_BR);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.BOOK_CCY);
        B100_CAL_NINT_RTN();
        if (pgmRtn) return;
        B200_CAL_OINT_RTN();
        if (pgmRtn) return;
        B300_CAL_LINT_RTN();
        if (pgmRtn) return;
        B400_CAL_PINT_RTN();
        if (pgmRtn) return;
    }
    public void B100_CAL_NINT_RTN() throws IOException,SQLException,Exception {
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CALF_CD.substring(0, 1));
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.FLT_MTH);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_TYP);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_PD);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_VAR);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_PCT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_INT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.IN_RATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        if (LNCFRATE.COMM_DATA.CALF_CD.substring(0, 1).equalsIgnoreCase("N")) {
            if (LNCFRATE.COMM_DATA.RAT_INT == 0 
                && (LNCFRATE.COMM_DATA.RAT_TYP.trim().length() == 0 
                || LNCFRATE.COMM_DATA.RAT_PD.trim().length() == 0)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_IN_RT_TPY_CLAS, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.FLT_MTH == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_NFLT_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((LNCFRATE.COMM_DATA.RAT_VAR == 0 
                && (LNCFRATE.COMM_DATA.FLT_MTH == '1' 
                || LNCFRATE.COMM_DATA.FLT_MTH == '3' 
                || LNCFRATE.COMM_DATA.FLT_MTH == '4'))) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_NVAR_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((LNCFRATE.COMM_DATA.RAT_PCT == 0 
                && (LNCFRATE.COMM_DATA.FLT_MTH == '2' 
                || LNCFRATE.COMM_DATA.FLT_MTH == '3' 
                || LNCFRATE.COMM_DATA.FLT_MTH == '4'))) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_NPCT_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_INT);
            if (LNCFRATE.COMM_DATA.RAT_INT != 0) {
                LNCFRATE.COMM_DATA.IN_RATE = LNCFRATE.COMM_DATA.RAT_INT;
                WS_N_RATE = LNCFRATE.COMM_DATA.RAT_INT;
                CEP.TRC(SCCGWA, WS_N_RATE);
            } else {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.BASE_TYP = LNCFRATE.COMM_DATA.RAT_TYP;
                BPCCINTI.BASE_INFO.TENOR = LNCFRATE.COMM_DATA.RAT_PD;
                BPCCINTI.BASE_INFO.CCY = LNCFRATE.COMM_DATA.BOOK_CCY;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.FUNC = 'I';
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                WS_N_RATE = BPCCINTI.BASE_INFO.RATE;
            }
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.FLT_MTH);
            if (LNCFRATE.COMM_DATA.FLT_MTH == '0') {
            } else if (LNCFRATE.COMM_DATA.FLT_MTH == '1') {
                WS_N_RATE = WS_N_RATE + LNCFRATE.COMM_DATA.RAT_VAR;
                bigD = new BigDecimal(WS_N_RATE);
                WS_N_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            } else if (LNCFRATE.COMM_DATA.FLT_MTH == '2') {
                WS_N_RATE = WS_N_RATE * ( 1 + LNCFRATE.COMM_DATA.RAT_PCT / 100 );
                bigD = new BigDecimal(WS_N_RATE);
                WS_N_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else if (LNCFRATE.COMM_DATA.FLT_MTH == '3') {
                WS_N_RATE = WS_N_RATE * ( 1 + LNCFRATE.COMM_DATA.RAT_PCT / 100 ) + LNCFRATE.COMM_DATA.PEN_SPR;
                bigD = new BigDecimal(WS_N_RATE);
                WS_N_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            } else if (LNCFRATE.COMM_DATA.FLT_MTH == '4') {
                WS_N_RATE = ( WS_N_RATE + LNCFRATE.COMM_DATA.RAT_VAR ) * ( 1 + LNCFRATE.COMM_DATA.RAT_PCT / 100 );
                bigD = new BigDecimal(WS_N_RATE);
                WS_N_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FLT_MTH_VALID, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCFRATE.COMM_DATA.IN_RATE = WS_N_RATE;
        }
    }
    public void B200_CAL_OINT_RTN() throws IOException,SQLException,Exception {
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CALF_CD.substring(2 - 1, 2 + 1 - 1));
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_RRAT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_TYP);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_RATT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_RATC);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_SPR);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_PCT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.PEN_IRAT);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        if (LNCFRATE.COMM_DATA.CALF_CD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("O")) {
            if (LNCFRATE.COMM_DATA.PEN_RRAT != 'A' 
                && LNCFRATE.COMM_DATA.PEN_RRAT != 'F' 
                && LNCFRATE.COMM_DATA.PEN_RRAT != 'T') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_ORRAT_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.PEN_RRAT == 'F' 
                && LNCFRATE.COMM_DATA.PEN_IRAT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.PEN_RRAT == 'T' 
                || LNCFRATE.COMM_DATA.PEN_RRAT == 'A') {
                if (LNCFRATE.COMM_DATA.PEN_RATT.trim().length() == 0 
                    || LNCFRATE.COMM_DATA.PEN_RATC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_OIN_RT_TPY_CLAS, LNCFRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.BASE_TYP = LNCFRATE.COMM_DATA.PEN_RATT;
                BPCCINTI.BASE_INFO.TENOR = LNCFRATE.COMM_DATA.PEN_RATC;
                BPCCINTI.BASE_INFO.CCY = LNCFRATE.COMM_DATA.BOOK_CCY;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.FUNC = 'I';
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                WS_O_RATE = BPCCINTI.BASE_INFO.RATE;
                CEP.TRC(SCCGWA, WS_O_RATE);
                if (LNCFRATE.COMM_DATA.PEN_RRAT == 'A' 
                    || LNCFRATE.COMM_DATA.PEN_RRAT == 'T') {
                    if (LNCFRATE.COMM_DATA.PEN_TYP == '0') {
                    } else if (LNCFRATE.COMM_DATA.PEN_TYP == '1') {
                        WS_O_RATE = WS_O_RATE + LNCFRATE.COMM_DATA.PEN_SPR;
                        bigD = new BigDecimal(WS_O_RATE);
                        WS_O_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                    } else if (LNCFRATE.COMM_DATA.PEN_TYP == '2') {
                        WS_O_RATE = WS_O_RATE * ( 1 + LNCFRATE.COMM_DATA.PEN_PCT / 100 );
                        bigD = new BigDecimal(WS_O_RATE);
                        WS_O_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    } else if (LNCFRATE.COMM_DATA.PEN_TYP == '3') {
                        WS_O_RATE = WS_O_RATE * ( 1 + LNCFRATE.COMM_DATA.PEN_PCT / 100 ) + LNCFRATE.COMM_DATA.PEN_SPR;
                        bigD = new BigDecimal(WS_O_RATE);
                        WS_O_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                    } else if (LNCFRATE.COMM_DATA.PEN_TYP == '4') {
                        WS_O_RATE = ( WS_O_RATE + LNCFRATE.COMM_DATA.PEN_SPR ) * ( 1 + LNCFRATE.COMM_DATA.PEN_PCT / 100 );
                        bigD = new BigDecimal(WS_O_RATE);
                        WS_O_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    } else {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FLT_MTH_VALID, LNCFRATE.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                LNCFRATE.COMM_DATA.PEN_IRAT = WS_O_RATE;
            }
        }
    }
    public void B300_CAL_LINT_RTN() throws IOException,SQLException,Exception {
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CALF_CD.substring(3 - 1, 3 + 1 - 1));
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_USE);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_RTY);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_TYP);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPNDRATT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPNDRATC);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_SPR);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_PCT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPNDRATE);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        if (LNCFRATE.COMM_DATA.CPND_USE == 'Y' 
            && LNCFRATE.COMM_DATA.CALF_CD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("L")) {
            LNCFRATE.COMM_DATA.CPNDRATE = LNCFRATE.COMM_DATA.PEN_IRAT;
        }
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        if (LNCFRATE.COMM_DATA.CALF_CD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("L")) {
            if (LNCFRATE.COMM_DATA.CPND_RTY != 'A' 
                && LNCFRATE.COMM_DATA.CPND_RTY != 'F' 
                && LNCFRATE.COMM_DATA.CPND_RTY != 'T') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_LRRAT_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.CPND_RTY == 'F' 
                && LNCFRATE.COMM_DATA.CPNDRATE == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.CPND_RTY == 'T' 
                || LNCFRATE.COMM_DATA.CPND_RTY == 'A') {
                if (LNCFRATE.COMM_DATA.CPNDRATT.trim().length() == 0 
                    || LNCFRATE.COMM_DATA.CPNDRATC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_OIN_RT_TPY_CLAS, LNCFRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.BASE_TYP = LNCFRATE.COMM_DATA.CPNDRATT;
                BPCCINTI.BASE_INFO.TENOR = LNCFRATE.COMM_DATA.CPNDRATC;
                BPCCINTI.BASE_INFO.CCY = LNCFRATE.COMM_DATA.BOOK_CCY;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.FUNC = 'I';
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                WS_L_RATE = BPCCINTI.BASE_INFO.RATE;
                CEP.TRC(SCCGWA, WS_L_RATE);
                CEP.TRC(SCCGWA, "L1-RATE");
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_RTY);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_TYP);
                if (LNCFRATE.COMM_DATA.CPND_RTY == 'A' 
                    || LNCFRATE.COMM_DATA.CPND_RTY == 'T') {
                    if (LNCFRATE.COMM_DATA.CPND_TYP == '0') {
                    } else if (LNCFRATE.COMM_DATA.CPND_TYP == '1') {
                        WS_L_RATE = WS_L_RATE + LNCFRATE.COMM_DATA.CPND_SPR;
                        bigD = new BigDecimal(WS_L_RATE);
                        WS_L_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                    } else if (LNCFRATE.COMM_DATA.CPND_TYP == '2') {
                        WS_L_RATE = WS_L_RATE * ( 1 + LNCFRATE.COMM_DATA.CPND_PCT / 100 );
                        bigD = new BigDecimal(WS_L_RATE);
                        WS_L_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                        CEP.TRC(SCCGWA, WS_L_RATE);
                        CEP.TRC(SCCGWA, "L2-RATE");
                    } else if (LNCFRATE.COMM_DATA.CPND_TYP == '3') {
                        WS_L_RATE = WS_L_RATE * ( 1 + LNCFRATE.COMM_DATA.CPND_PCT / 100 ) + LNCFRATE.COMM_DATA.CPND_SPR;
                        bigD = new BigDecimal(WS_L_RATE);
                        WS_L_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                    } else if (LNCFRATE.COMM_DATA.CPND_TYP == '4') {
                        WS_O_RATE = ( WS_L_RATE + LNCFRATE.COMM_DATA.CPND_SPR ) * ( 1 + LNCFRATE.COMM_DATA.CPND_PCT / 100 );
                        bigD = new BigDecimal(WS_O_RATE);
                        WS_O_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    } else {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FLT_MTH_VALID, LNCFRATE.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, WS_L_RATE);
                CEP.TRC(SCCGWA, "L3-RATE");
                LNCFRATE.COMM_DATA.CPNDRATE = WS_L_RATE;
            }
        }
    }
    public void B400_CAL_PINT_RTN() throws IOException,SQLException,Exception {
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CALF_CD.substring(4 - 1, 4 + 1 - 1));
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUS_RAT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUSRATT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUS_TYP);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUSRATC);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUSSPR);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUSPCT);
        CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.ABUSIRAT);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        if (LNCFRATE.COMM_DATA.CALF_CD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("P")) {
            if (LNCFRATE.COMM_DATA.ABUS_RAT != 'A' 
                && LNCFRATE.COMM_DATA.ABUS_RAT != 'F' 
                && LNCFRATE.COMM_DATA.ABUS_RAT != 'T') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_PRRAT_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.ABUS_RAT == 'F' 
                && LNCFRATE.COMM_DATA.ABUSIRAT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCFRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCFRATE.COMM_DATA.ABUS_RAT == 'T' 
                || LNCFRATE.COMM_DATA.ABUS_RAT == 'A') {
                if (LNCFRATE.COMM_DATA.ABUSRATT.trim().length() == 0 
                    || LNCFRATE.COMM_DATA.ABUSRATC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_PIN_RT_TPY_CLAS, LNCFRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.BASE_TYP = LNCFRATE.COMM_DATA.ABUSRATT;
                BPCCINTI.BASE_INFO.TENOR = LNCFRATE.COMM_DATA.ABUSRATC;
                BPCCINTI.BASE_INFO.CCY = LNCFRATE.COMM_DATA.BOOK_CCY;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.FUNC = 'I';
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                WS_P_RATE = BPCCINTI.BASE_INFO.RATE;
                CEP.TRC(SCCGWA, WS_P_RATE);
                if (LNCFRATE.COMM_DATA.ABUS_RAT == 'A' 
                    || LNCFRATE.COMM_DATA.ABUS_RAT == 'T') {
                    if (LNCFRATE.COMM_DATA.ABUS_TYP == '0') {
                    } else if (LNCFRATE.COMM_DATA.ABUS_TYP == '1') {
