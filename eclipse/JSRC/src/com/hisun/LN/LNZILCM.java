package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCQCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class LNZILCM {
    boolean pgmRtn = false;
    char LNZILCM_FILLER1 = ' ';
    short WS_U = 0;
    short WS_V = 0;
    short WS_IDX = 0;
    double WS_RATE = 0;
    double WS_RATE1 = 0;
    double WS_INST_AMT = 0;
    double WS_INT_AMT3 = 0;
    double WS_TOT_AMT = 0;
    long WS_LOW_CCY_INT_AMT = 0;
    double WS_LOW_CCY_INT_AMT1 = 0;
    double WS_LOW_CCY_INT_AMT2 = 0;
    short WS_INT_BASDAYS = 0;
    short WS_RMDR = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    String WS_FORM_CODE = " ";
    char WS_FUNC_CODE = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    LNCILCM LNCILCM;
    public void MP(SCCGWA SCCGWA, LNCILCM LNCILCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCILCM = LNCILCM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZILCM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCILCM.RC.RC_APP = "LN";
        LNCILCM.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.FORM_CODE);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.PRIN_AMT);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.BASDAYS_STD);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.PERIOD);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.PERIOD_UNIT);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.ROUND_MODE);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.CCY);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.TERM);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCILCM.COMM_DATA.PRIN_AMT == 0 
            && LNCILCM.COMM_DATA.TERM == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_P_TERM_MUST_INPUT, LNCILCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCILCM.COMM_DATA.INST_AMT == 0 
            && LNCILCM.COMM_DATA.TERM == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INSAM_TERM_MUST_INP, LNCILCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_FORM_CODE = LNCILCM.COMM_DATA.FORM_CODE;
        WS_FUNC_CODE = LNCILCM.COMM_DATA.FUNC_CODE;
        if (LNCILCM.COMM_DATA.TERM == 0 
            && !WS_FORM_CODE.equalsIgnoreCase("31") 
            && !WS_FORM_CODE.equalsIgnoreCase("34")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INST_OPTION, LNCILCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCILCM.COMM_DATA.BASDAYS_STD.equalsIgnoreCase("2")) {
            WS_INT_BASDAYS = 365;
        } else if (LNCILCM.COMM_DATA.BASDAYS_STD.equalsIgnoreCase("3")) {
            WS_INT_BASDAYS = 366;
        } else {
            WS_INT_BASDAYS = 360;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_FORM_CODE.equalsIgnoreCase("31")) {
            B100_INST_GENERAL();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("32")) {
            B200_INST_RULE78();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("33")) {
            B300_INST_INCRES();
            if (pgmRtn) return;
        } else if (WS_FORM_CODE.equalsIgnoreCase("34")) {
            B400_INST_EQUPRI();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INST_FORM_CODE, LNCILCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_INST_GENERAL() throws IOException,SQLException,Exception {
        if (WS_FUNC_CODE == '1') {
            B110_COMPUTE_INST();
            if (pgmRtn) return;
        } else if (WS_FUNC_CODE == '2') {
            B120_COMPUTE_TERM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INST_OPTION, LNCILCM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B110_COMPUTE_INST() throws IOException,SQLException,Exception {
        if (LNCILCM.COMM_DATA.RATE == 0) {
            WS_TOT_AMT = LNCILCM.COMM_DATA.PRIN_AMT / LNCILCM.COMM_DATA.TERM;
            bigD = new BigDecimal(WS_TOT_AMT);
            WS_TOT_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        } else {
            B110_COMP_TERM_RATE();
            if (pgmRtn) return;
