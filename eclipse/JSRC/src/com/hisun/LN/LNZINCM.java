package com.hisun.LN;

import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZINCM {
    BigDecimal bigD;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char LNZINCM_FILLER1 = ' ';
    short WS_DAY = 0;
    int WS_DAYS = 0;
    short WS_PERIOD_DAYS = 0;
    double WS_RATE = 0;
    double WS_HDL78_AMT = 0;
    double WS_INT78_AMT = 0;
    double WS_R78_INT = 0;
    double WS_INST_AMT = 0;
    double WS_INT_AMT = 0;
    double WS_INT_AMT0 = 0;
    long WS_LOW_CCY_INT_AMT = 0;
    double WS_LOW_CCY_INT_AMT1 = 0;
    double WS_LOW_CCY_INT_AMT2 = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    String WS_FORM_CODE = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    LNCINCM LNCINCM;
    public void MP(SCCGWA SCCGWA, LNCINCM LNCINCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCINCM = LNCINCM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZINCM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCINCM.RC.RC_APP = "LN";
        LNCINCM.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.FORM_CODE);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BASDAYS);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.CCB_DAYS);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BAL);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.RATE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCINCM.COMM_DATA.BEG_DATE == 0 
            || LNCINCM.COMM_DATA.END_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_END_DATE, LNCINCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCINCM.COMM_DATA.BEG_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = LNCINCM.COMM_DATA.BEG_DATE;
            R00_CHECK_DATE();
            if (pgmRtn) return;
        }
        if (LNCINCM.COMM_DATA.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = LNCINCM.COMM_DATA.END_DATE;
            R00_CHECK_DATE();
            if (pgmRtn) return;
        }
        if (LNCINCM.COMM_DATA.BEG_DATE != 0 
            && LNCINCM.COMM_DATA.END_DATE != 0) {
            if (LNCINCM.COMM_DATA.BEG_DATE > LNCINCM.COMM_DATA.END_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCINCM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCINCM.COMM_DATA.CCB_DAYS == 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = LNCINCM.COMM_DATA.BEG_DATE;
                SCCCLDT.DATE2 = LNCINCM.COMM_DATA.END_DATE;
                R00_CAL_DATE();
                if (pgmRtn) return;
                WS_DAYS = SCCCLDT.DAYS;
            } else {
                WS_DAYS = LNCINCM.COMM_DATA.CCB_DAYS;
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_FORM_CODE = LNCINCM.COMM_DATA.FORM_CODE;
        if (WS_FORM_CODE.equalsIgnoreCase("01")) {
            B100_INT_FM01();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("02")) {
            B100_INT_FM02();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("03")) {
            B100_INT_FM03();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("11")) {
            B100_INT_FM11();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("12")) {
            B100_INT_FM12();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CINT_NORM_FORM, LNCINCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_INT_AMT0);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.ROUND_MODE);
        R00_ROUND_PROCESS();
        if (pgmRtn) return;
        LNCINCM.COMM_DATA.INT_AMT = WS_INT_AMT;
        LNCINCM.COMM_DATA.SPE_INT = WS_INT_AMT0;
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.INT_AMT);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.SPE_INT);
    }
    public void R00_ROUND_PROCESS() throws IOException,SQLException,Exception {
        if (LNCINCM.COMM_DATA.ROUND_MODE == 0) {
            WS_LOW_CCY_INT_AMT = WS_INT_AMT0 + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT);
            WS_LOW_CCY_INT_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_INT_AMT = WS_LOW_CCY_INT_AMT;
        }
        if (LNCINCM.COMM_DATA.ROUND_MODE == 1) {
            WS_LOW_CCY_INT_AMT1 = WS_INT_AMT0 + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT1);
            WS_LOW_CCY_INT_AMT1 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_INT_AMT = WS_LOW_CCY_INT_AMT1;
        }
        if (LNCINCM.COMM_DATA.ROUND_MODE == 2) {
            WS_LOW_CCY_INT_AMT2 = WS_INT_AMT0 + 0;
            bigD = new BigDecimal(WS_LOW_CCY_INT_AMT2);
            WS_LOW_CCY_INT_AMT2 = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            WS_INT_AMT = WS_LOW_CCY_INT_AMT2;
        }
    }
    public void B100_INT_FM01() throws IOException,SQLException,Exception {
        WS_INT_AMT0 = LNCINCM.COMM_DATA.BAL * LNCINCM.COMM_DATA.RATE * WS_DAYS / ( 100 * LNCINCM.COMM_DATA.BASDAYS );
        bigD = new BigDecimal(WS_INT_AMT0);
        WS_INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, WS_INT_AMT0);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BAL);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.BASDAYS);
    }
    public void B100_INT_FM02() throws IOException,SQLException,Exception {
        if (LNCINCM.COMM_DATA.PERIOD_UNIT == 'D') {
            WS_INT_AMT0 = LNCINCM.COMM_DATA.BAL * LNCINCM.COMM_DATA.RATE * LNCINCM.COMM_DATA.PERIOD / ( 100 * LNCINCM.COMM_DATA.BASDAYS );
            bigD = new BigDecimal(WS_INT_AMT0);
            WS_INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        } else {
            WS_INT_AMT0 = LNCINCM.COMM_DATA.BAL * LNCINCM.COMM_DATA.RATE * LNCINCM.COMM_DATA.PERIOD / 1200;
            bigD = new BigDecimal(WS_INT_AMT0);
            WS_INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        }
    }
    public void B100_INT_FM03_BAK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCINCM.COMM_DATA.BEG_DATE;
        if (LNCINCM.COMM_DATA.PERIOD_UNIT == 'D') {
            SCCCLDT.DAYS = LNCINCM.COMM_DATA.PERIOD;
        } else {
            SCCCLDT.MTHS = LNCINCM.COMM_DATA.PERIOD;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        SCCCLDT.DAYS = 0;
        SCCCLDT.MTHS = 0;
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_PERIOD_DAYS = (short) SCCCLDT.DAYS;
        B100_INT_FM02();
        if (pgmRtn) return;
        WS_INT_AMT0 = WS_INT_AMT0 * ( WS_DAYS / WS_PERIOD_DAYS );
        bigD = new BigDecimal(WS_INT_AMT0);
        WS_INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void B100_INT_FM03() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCINCM.COMM_DATA.BEG_DATE;
        if (LNCINCM.COMM_DATA.PERIOD_UNIT == 'D') {
            SCCCLDT.DAYS = LNCINCM.COMM_DATA.PERIOD;
        } else {
            SCCCLDT.MTHS = LNCINCM.COMM_DATA.PERIOD;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        if (SCCCLDT.DATE2 == LNCINCM.COMM_DATA.END_DATE) {
            B100_INT_FM02();
            if (pgmRtn) return;
        } else {
            B100_INT_FM01();
            if (pgmRtn) return;
        }
    }
    public void B100_INT_FM11() throws IOException,SQLException,Exception {
        B110_INT_FM11();
        if (pgmRtn) return;
        B120_INT_FM11();
        if (pgmRtn) return;
    }
    public void B120_INT_FM11() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INT_AMT0);
        CEP.TRC(SCCGWA, WS_DAYS);
        CEP.TRC(SCCGWA, LNCINCM.COMM_DATA.TERM_DAYS);
        WS_INT_AMT0 = WS_INT_AMT0 * ( WS_DAYS / LNCINCM.COMM_DATA.TERM_DAYS );
        bigD = new BigDecimal(WS_INT_AMT0);
        WS_INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void B110_INT_FM11() throws IOException,SQLException,Exception {
        WS_RATE = LNCINCM.COMM_DATA.HANDLING_PERC;
        WS_INST_AMT = LNCINCM.COMM_DATA.INST_AMT;
        WS_HDL78_AMT = LNCINCM.COMM_DATA.BAL * WS_RATE / 1200;
        bigD = new BigDecimal(WS_HDL78_AMT);
        WS_HDL78_AMT = bigD.setScale(12, RoundingMode.HALF_UP).doubleValue();
        WS_INT78_AMT = ( WS_INST_AMT - WS_HDL78_AMT ) * LNCINCM.COMM_DATA.TERM - LNCINCM.COMM_DATA.BAL;
        bigD = new BigDecimal(WS_INT78_AMT);
        WS_INT78_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        if (WS_INT78_AMT < 0) {
            WS_INT78_AMT = 0;
            WS_R78_INT = 0;
        } else {
            WS_R78_INT = WS_INT78_AMT * ( LNCINCM.COMM_DATA.TERM - LNCINCM.COMM_DATA.INT_TERM + 1 ) / ( ( LNCINCM.COMM_DATA.TERM + 1 ) * LNCINCM.COMM_DATA.TERM / 2 );
            bigD = new BigDecimal(WS_R78_INT);
            WS_R78_INT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        }
        WS_INT_AMT0 = WS_R78_INT + 0;
        bigD = new BigDecimal(WS_INT_AMT0);
        WS_INT_AMT0 = bigD.setScale(4, RoundingMode.HALF_UP).doubleValue();
    }
    public void B100_INT_FM12() throws IOException,SQLException,Exception {
        WS_INT_AMT0 = LNCINCM.COMM_DATA.BAL * LNCINCM.COMM_DATA.RATE * WS_DAYS / ( 100 * LNCINCM.COMM_DATA.BASDAYS + LNCINCM.COMM_DATA.RATE * WS_DAYS );
        bigD = new BigDecimal(WS_INT_AMT0);
        WS_INT_AMT0 = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void R00_CHECK_DATE() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCINCM.RC.RC_APP = "SC";
            LNCINCM.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCINCM.RC.RC_APP = "SC";
            LNCINCM.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCINCM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCINCM=");
            CEP.TRC(SCCGWA, LNCINCM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
