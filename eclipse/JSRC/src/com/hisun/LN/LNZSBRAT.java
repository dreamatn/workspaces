package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSBRAT {
    LNZSBRAT_WS_OUT_N_DATA WS_OUT_N_DATA;
    LNZSBRAT_WS_OUT_O_DATA WS_OUT_O_DATA;
    BigDecimal bigD;
    DBParm LNTRATN_RD;
    DBParm LNTRATL_RD;
    brParm LNTRATN_BR = new brParm();
    brParm LNTRATL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short Q_MAX_CNT = 1000;
    LNZSBRAT_WS_VARIABLES WS_VARIABLES = new LNZSBRAT_WS_VARIABLES();
    LNZSBRAT_WS_OUT_RECODE_N WS_OUT_RECODE_N = new LNZSBRAT_WS_OUT_RECODE_N();
    LNZSBRAT_WS_OUT_RECODE_O WS_OUT_RECODE_O = new LNZSBRAT_WS_OUT_RECODE_O();
    LNZSBRAT_WS_PAGE_INFO WS_PAGE_INFO = new LNZSBRAT_WS_PAGE_INFO();
    LNZSBRAT_WS_COND_FLG WS_COND_FLG = new LNZSBRAT_WS_COND_FLG();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCCINTI_WS_DB_VARS WS_DB_VARS = new BPCCINTI_WS_DB_VARS();
    int RTCD = 0;
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT BPRTRT;
    LNCSBRAT LNCSBRAT;
    public void MP(SCCGWA SCCGWA, LNCSBRAT LNCSBRAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSBRAT = LNCSBRAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSBRAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, WS_DB_VARS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSBRAT.FUNC);
        CEP.TRC(SCCGWA, LNCSBRAT.INT_TYP);
        CEP.TRC(SCCGWA, LNCSBRAT.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCSBRAT.ACTV_DT);
        CEP.TRC(SCCGWA, LNCSBRAT.SEQ_NO);
        CEP.TRC(SCCGWA, LNCSBRAT.PAGE_ROW);
        if (LNCSBRAT.INT_TYP == 'N') {
            WS_PAGE_INFO.IDX = 0;
            B000_COMPUTE_PAGE_INFO();
            if (pgmRtn) return;
            B101_STARTBR_LNTRATN();
            if (pgmRtn) return;
            B102_READNEXT_LNTRATN();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                    && WS_PAGE_INFO.IDX < WS_PAGE_INFO.PAGE_ROW) {
                    WS_PAGE_INFO.IDX += 1;
                    WS_PAGE_INFO.NEXT_START_NUM += 1;
                    WS_DB_VARS.START_NUM = WS_PAGE_INFO.NEXT_START_NUM;
                    IBS.init(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, ));
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_LN_AC = LNRRATN.KEY.CONTRACT_NO;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_ACTV_DT = LNRRATN.KEY.ACTV_DT;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_RATE_FLG = 'N';
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_COMP_MTH = LNRRATN.COMPARISON_METHOD;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_MAX_RATE = LNRRATN.MAX_RATE;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_MIN_RATE = LNRRATN.MIN_RATE;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_PREMIUM_RATE = LNRRATN.PREMIUM_RATE;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_N_DATA.OUT_N_ADD_ON_RATE = LNRRATN.ADD_ON_RATE;
                    WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    if (LNRRATN.KEY.ACTV_DT > SCCGWA.COMM_AREA.AC_DATE 
                        && LNRRATN.INT_RATE_TYPE1.trim().length() > 0 
                        && LNRRATN.INT_RATE_CLAS1.trim().length() > 0) {
                        B010_GET_CONT_INF();
                        if (pgmRtn) return;
                        R000_GET_RTTYPE_RATE();
                        if (pgmRtn) return;
                        R000_GET_FWD_RATE();
                        if (pgmRtn) return;
                    } else {
                        WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                        WS_OUT_N_DATA.OUT_N_COST_RATE = LNRRATN.COST_OF_FUND_RATE;
                        WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                        WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
                        WS_OUT_N_DATA.OUT_N_ALL_IN_RATE = LNRRATN.ALL_IN_RATE;
                        WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
                    }
                    B102_READNEXT_LNTRATN();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_LN_AC);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_ACTV_DT);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_RATE_FLG);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_COMP_MTH);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_MAX_RATE);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_COST_RATE);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_MIN_RATE);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_PREMIUM_RATE);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_ADD_ON_RATE);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_ALL_IN_RATE);
                }
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_PAGE_INFO.TOTAL_PAGE = WS_PAGE_INFO.CURR_PAGE;
                    WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.IDX;
                    WS_DB_VARS.TOTAL_NUM = ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW + WS_PAGE_INFO.BAL_CNT;
                    WS_PAGE_INFO.LAST_PAGE = 'Y';
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                    CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
                    CEP.TRC(SCCGWA, "111111111111111111111111");
                } else {
                    B001_GET_TOTTAL_NUM_RATN();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, LNCSBRAT.PAGE_ROW);
                    CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
                    WS_PAGE_INFO.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
                    WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
                    if (WS_PAGE_INFO.BAL_CNT != 0) {
                        WS_PAGE_INFO.TOTAL_PAGE += 1;
                    }
                    CEP.TRC(SCCGWA, "222222222222222222222222");
                }
            } else {
                WS_PAGE_INFO.TOTAL_PAGE = 1;
                WS_DB_VARS.TOTAL_NUM = 0;
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                WS_PAGE_INFO.PAGE_ROW = 0;
                CEP.TRC(SCCGWA, "3333333333333333333333333");
            }
            WS_VARIABLES.TS_REC = " ";
            WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_TOTAL_PAGE_N = WS_PAGE_INFO.TOTAL_PAGE;
            WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_TOTAL_NUM_N = WS_DB_VARS.TOTAL_NUM;
            WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_LAST_PAGE_N = WS_PAGE_INFO.LAST_PAGE;
            WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_CURR_PAGE_ROW_N = WS_PAGE_INFO.PAGE_ROW;
            WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_CURR_PAGE_N = WS_PAGE_INFO.CURR_PAGE;
            WS_VARIABLES.TS_REC = IBS.CLS2CPY(SCCGWA, WS_OUT_RECODE_N);
            CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_TOTAL_PAGE_N);
            CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_TOTAL_NUM_N);
            CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_LAST_PAGE_N);
            CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_CURR_PAGE_ROW_N);
            CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N.O_CURR_PAGE_N);
            B025_OUT_RECORD_N();
            if (pgmRtn) return;
            B103_ENDBR_LNTRATN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N);
        } else {
            WS_PAGE_INFO.IDX = 0;
            B000_COMPUTE_PAGE_INFO();
            if (pgmRtn) return;
            B201_STARTBR_LNTRATL();
            if (pgmRtn) return;
            B202_READNEXT_LNTRATL();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && LNCSBRAT.INT_TYP == 'L') {
                B203_ENDBR_LNTRATL();
                if (pgmRtn) return;
                LNCSBRAT.INT_TYP = 'O';
                CEP.TRC(SCCGWA, "TEST");
                B201_STARTBR_LNTRATL();
                if (pgmRtn) return;
            }
            B202_READNEXT_LNTRATL_B();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                    && WS_PAGE_INFO.IDX < WS_PAGE_INFO.PAGE_ROW) {
                    WS_PAGE_INFO.IDX += 1;
                    WS_PAGE_INFO.NEXT_START_NUM += 1;
                    WS_DB_VARS.START_NUM = WS_PAGE_INFO.NEXT_START_NUM;
                    IBS.init(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, ));
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_LN_AC = LNRRATL.KEY.CONTRACT_NO;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_INT_TYP = LNRRATL.KEY.OVD_KND;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_PRAT_FLG = LNRRATL.INT_CHRG_MTH;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_ACTV_DT = LNRRATL.KEY.ACTV_DT;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_IRAT_CD = LNRRATL.RATE_TYPE;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_IRATCLS = LNRRATL.RATE_CLAS;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_RAT_PCT = LNRRATL.DIF_IRAT_PER;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_PRAT_DIF = LNRRATL.DIF_IRAT_PNT;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    WS_OUT_O_DATA = WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1);
                    WS_OUT_O_DATA.OUT_O_PEN_RAT = LNRRATL.EFF_RAT;
                    WS_OUT_RECODE_O.WS_OUT_O_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_O_DATA);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_LN_AC);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_INT_TYP);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_PRAT_FLG);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_IRAT_CD);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_IRATCLS);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_RAT_PCT);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_PRAT_DIF);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_PEN_RAT);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_O_DATA.get(WS_PAGE_INFO.IDX-1).OUT_O_PRAT_DIF);
                    B202_READNEXT_LNTRATL_B();
                    if (pgmRtn) return;
                }
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_PAGE_INFO.TOTAL_PAGE = WS_PAGE_INFO.CURR_PAGE;
                    WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.IDX;
                    WS_DB_VARS.TOTAL_NUM = ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW + WS_PAGE_INFO.BAL_CNT;
                    WS_PAGE_INFO.LAST_PAGE = 'Y';
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                } else {
                    B001_GET_TOTTAL_NUM_RATL();
                    if (pgmRtn) return;
                    WS_PAGE_INFO.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
                    WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
                    if (WS_PAGE_INFO.BAL_CNT != 0) {
                        WS_PAGE_INFO.TOTAL_PAGE += 1;
                    }
                }
            } else {
                WS_PAGE_INFO.TOTAL_PAGE = 1;
                WS_DB_VARS.TOTAL_NUM = 0;
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                WS_PAGE_INFO.PAGE_ROW = 0;
            }
            WS_VARIABLES.TS_REC = " ";
            WS_OUT_RECODE_O.WS_OUT_HEAD_O.O_TOTAL_PAGE_O = WS_PAGE_INFO.TOTAL_PAGE;
            WS_OUT_RECODE_O.WS_OUT_HEAD_O.O_TOTAL_NUM_O = WS_DB_VARS.TOTAL_NUM;
            WS_OUT_RECODE_O.WS_OUT_HEAD_O.O_LAST_PAGE_O = WS_PAGE_INFO.LAST_PAGE;
            WS_OUT_RECODE_O.WS_OUT_HEAD_O.O_CURR_PAGE_ROW_O = WS_PAGE_INFO.PAGE_ROW;
            WS_OUT_RECODE_O.WS_OUT_HEAD_O.O_CURR_PAGE_O = WS_PAGE_INFO.CURR_PAGE;
            WS_VARIABLES.TS_REC = IBS.CLS2CPY(SCCGWA, WS_OUT_RECODE_O);
            B025_OUT_RECORD_O();
            if (pgmRtn) return;
            B203_ENDBR_LNTRATL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_OUT_RECODE_O.WS_OUT_HEAD_O);
        }
    }
    public void R000_GET_RTTYPE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CCY);
        BPCCINTI.BASE_INFO.CCY = LNCCONTM.REC_DATA.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = LNRRATN.INT_RATE_TYPE1;
        BPCCINTI.BASE_INFO.TENOR = LNRRATN.INT_RATE_CLAS1;
        BPCCINTI.FUNC = 'I';
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        WS_VARIABLES.RATE = BPCCINTI.BASE_INFO.RATE;
        CEP.TRC(SCCGWA, WS_VARIABLES.RATE);
    }
    public void R000_GET_FWD_RATE() throws IOException,SQLException,Exception {
        if (LNRRATN.VARIATION_METHOD == '0') {
            WS_VARIABLES.RATE = WS_VARIABLES.RATE;
        }
        if (LNRRATN.VARIATION_METHOD == '1') {
            WS_VARIABLES.RATE = WS_VARIABLES.RATE + LNRRATN.RATE_VARIATION1;
        }
        if (LNRRATN.VARIATION_METHOD == '2') {
            CEP.TRC(SCCGWA, LNRRATN.RATE_PECT1);
            WS_VARIABLES.RATE = WS_VARIABLES.RATE * ( 1 + LNRRATN.RATE_PECT1 / 100 );
            bigD = new BigDecimal(WS_VARIABLES.RATE);
            WS_VARIABLES.RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        if (LNRRATN.VARIATION_METHOD == '3') {
            CEP.TRC(SCCGWA, LNRRATN.RATE_VARIATION1);
            CEP.TRC(SCCGWA, LNRRATN.RATE_PECT1);
            WS_VARIABLES.RATE = WS_VARIABLES.RATE * ( 1 + LNRRATN.RATE_PECT1 / 100 ) + LNRRATN.RATE_VARIATION1;
            bigD = new BigDecimal(WS_VARIABLES.RATE);
            WS_VARIABLES.RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
        if (LNRRATN.VARIATION_METHOD == '4') {
            CEP.TRC(SCCGWA, LNRRATN.RATE_VARIATION1);
            CEP.TRC(SCCGWA, LNRRATN.RATE_PECT1);
            WS_VARIABLES.RATE = ( WS_VARIABLES.RATE + LNRRATN.RATE_VARIATION1 ) * ( 1 + LNRRATN.RATE_PECT1 / 100 );
            bigD = new BigDecimal(WS_VARIABLES.RATE);
            WS_VARIABLES.RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        WS_OUT_N_DATA = WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1);
        WS_OUT_N_DATA.OUT_N_COST_RATE = WS_VARIABLES.RATE;
        WS_OUT_RECODE_N.WS_OUT_N_DATA.set(WS_PAGE_INFO.IDX-1, WS_OUT_N_DATA);
        WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_ALL_IN_RATE = LNRRATN.ADD_ON_RATE + WS_OUT_RECODE_N.WS_OUT_N_DATA.get(WS_PAGE_INFO.IDX-1).OUT_N_COST_RATE;
    }
    public void B000_COMPUTE_PAGE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE_O.WS_OUT_HEAD_O);
        IBS.init(SCCGWA, WS_OUT_RECODE_N.WS_OUT_HEAD_N);
        if (LNCSBRAT.PAGE_NUM == 0) {
            WS_PAGE_INFO.CURR_PAGE = 1;
        } else {
            WS_PAGE_INFO.CURR_PAGE = (short) LNCSBRAT.PAGE_NUM;
        }
        WS_PAGE_INFO.LAST_PAGE = 'N';
        WS_PAGE_INFO.PAGE_ROW = LNCSBRAT.PAGE_ROW;
        if (LNCSBRAT.PAGE_ROW == 0) {
            WS_PAGE_INFO.PAGE_ROW = 25;
        }
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_PAGE_INFO.NEXT_START_NUM;
    }
    public void B001_GET_TOTTAL_NUM_RATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.set = "WS-TOTAL-NUM=COUNT(*)";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO";
        IBS.GROUP(SCCGWA, LNRRATN, this, LNTRATN_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.TOTAL_NUM);
        CEP.TRC(SCCGWA, LNRRATN.KEY.CONTRACT_NO);
    }
    public void B001_GET_TOTTAL_NUM_RATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.set = "WS-TOTAL-NUM=COUNT(*)";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND";
        IBS.GROUP(SCCGWA, LNRRATL, this, LNTRATL_RD);
    }
    public void B010_GET_CONT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNRRATN.KEY.CONTRACT_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B101_STARTBR_LNTRATN() throws IOException,SQLException,Exception {
        LNRRATN.KEY.CONTRACT_NO = LNCSBRAT.CONTRACT_NO;
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO";
        LNTRATN_BR.rp.order = "CONTRACT_NO, ACTV_DT";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_RATN_NOTFND;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "STARTBR LNTRATN NORMAL");
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B102_READNEXT_LNTRATN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRATN, this, LNTRATN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1'
            || SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B103_ENDBR_LNTRATN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B201_STARTBR_LNTRATL() throws IOException,SQLException,Exception {
        LNRRATL.KEY.CONTRACT_NO = LNCSBRAT.CONTRACT_NO;
        LNRRATL.KEY.OVD_KND = LNCSBRAT.INT_TYP;
        LNTRATL_BR.rp = new DBParm();
        LNTRATL_BR.rp.TableName = "LNTRATL";
        LNTRATL_BR.rp.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND";
        LNTRATL_BR.rp.order = "CONTRACT_NO, OVD_KND, ACTV_DT";
        IBS.STARTBR(SCCGWA, LNRRATL, this, LNTRATL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_RATL_NOTFND;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B202_READNEXT_LNTRATL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRATL, this, LNTRATL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1'
            || SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B202_READNEXT_LNTRATL_B() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRATL, this, LNTRATL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1'
            || SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B203_ENDBR_LNTRATL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_ERR_SYS_ERR;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
    }
    public void B025_OUT_RECORD_N() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.TS_REC);
        SCCFMT.FMTID = "LNN01";
        WS_VARIABLES.LEN = 2844;
        WS_VARIABLES.TS_CNT += 1;
        if (WS_VARIABLES.TS_CNT <= Q_MAX_CNT) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.TS_REC = " ";
            WS_VARIABLES.TS_REC = "TO BE CONTINUED";
            WS_VARIABLES.LEN = 2844;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_COND_FLG.EOF_FLG = 'Y';
        }
    }
    public void B025_OUT_RECORD_O() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.TS_REC);
        SCCFMT.FMTID = "LNN02";
        WS_VARIABLES.LEN = 2325;
        WS_VARIABLES.TS_CNT += 1;
        if (WS_VARIABLES.TS_CNT <= Q_MAX_CNT) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.TS_REC = " ";
            WS_VARIABLES.TS_REC = "TO BE CONTINUED";
            WS_VARIABLES.LEN = 2325;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_COND_FLG.EOF_FLG = 'Y';
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TS_CNT > Q_MAX_CNT) {
            WS_VARIABLES.WS_TEMP_VARIABLE.MSGID = ERROR_MSG.LN_REC_NUM_EXCEED;
            CEP.ERR(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.MSGID);
        }
        SCCFMT.DATA_LEN = WS_VARIABLES.LEN;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
