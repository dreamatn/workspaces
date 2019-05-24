package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5984 {
    double K_ALL_9 = 99999999999999.99;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_LOCAL_CCY = "CNY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_POS = 0;
    DDOT5984_WS_BASERATE WS_BASERATE = new DDOT5984_WS_BASERATE();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMRAT DDCSMRAT = new DDCSMRAT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5981_AWA_5981 DDB5981_AWA_5981;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5984 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5981_AWA_5981>");
        DDB5981_AWA_5981 = (DDB5981_AWA_5981) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.PARM_CD);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.CCY);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.CCY_TYP);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.MIN_AMT);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.INT_TYP);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.AGSP_FLG);
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TGRP);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TCLS);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD1);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR1);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD2);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR2);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD3);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR3);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD4);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR4);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD5);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR5);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE);
            CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].FIX_RATE);
        }
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.EFFDAT);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.EXPDAT);
        if (DDB5981_AWA_5981.PARM_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5981_AWA_5981.PARM_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5981_AWA_5981.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5981_AWA_5981.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5981_AWA_5981.INT_TYP != 'N' 
            && DDB5981_AWA_5981.INT_TYP != 'A' 
            && DDB5981_AWA_5981.INT_TYP != 'M' 
            && DDB5981_AWA_5981.INT_TYP != 'Q' 
            && DDB5981_AWA_5981.INT_TYP != 'Y' 
            && DDB5981_AWA_5981.INT_TYP != 'G' 
            && DDB5981_AWA_5981.INT_TYP != 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INT_TYP_INVALID;
            WS_FLD_NO = DDB5981_AWA_5981.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB5981_AWA_5981.INT_TYP == 'A' 
            || DDB5981_AWA_5981.INT_TYP == 'M' 
            || DDB5981_AWA_5981.INT_TYP == 'Q' 
            || DDB5981_AWA_5981.INT_TYP == 'Y') 
            && DDB5981_AWA_5981.AGSP_FLG != 'A' 
            && DDB5981_AWA_5981.AGSP_FLG != 'S') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AGSP_FLG_INVALID;
            WS_FLD_NO = DDB5981_AWA_5981.AGSP_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((DDB5981_AWA_5981.INT_TYP == 'A' 
            || DDB5981_AWA_5981.INT_TYP == 'M' 
            || DDB5981_AWA_5981.INT_TYP == 'Q' 
            || DDB5981_AWA_5981.INT_TYP == 'Y') 
            && DDB5981_AWA_5981.TIER_AMT[1-1].TAMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FST_TIER_AMT_M_INPUT;
            WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[1-1].TAMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5981_AWA_5981.INT_TYP == 'G' 
            && DDB5981_AWA_5981.TIER_AMT[1-1].TGRP.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FST_TIER_GRP_M_INPUT;
            WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[1-1].TGRP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5981_AWA_5981.INT_TYP == 'C' 
            && DDB5981_AWA_5981.TIER_AMT[1-1].TCLS.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FST_TIER_CLS_M_INPUT;
            WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[1-1].TCLS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        for (WS_CNT = 1; WS_CNT <= 5 
            && (DDB5981_AWA_5981.INT_TYP != 'A' 
            || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT != 0) 
            && (DDB5981_AWA_5981.INT_TYP != 'M' 
            || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT != 0) 
            && (DDB5981_AWA_5981.INT_TYP != 'Q' 
            || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT != 0) 
            && (DDB5981_AWA_5981.INT_TYP != 'Y' 
            || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT != 0) 
            && (DDB5981_AWA_5981.INT_TYP != 'G' 
            || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TGRP.trim().length() != 0) 
            && (DDB5981_AWA_5981.INT_TYP != 'C' 
            || !DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TCLS.equalsIgnoreCase("0")); WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT > 1) {
                WS_POS = (short) (WS_CNT - 1);
                if ((DDB5981_AWA_5981.INT_TYP == 'A' 
                    || DDB5981_AWA_5981.INT_TYP == 'M' 
                    || DDB5981_AWA_5981.INT_TYP == 'Q' 
                    || DDB5981_AWA_5981.INT_TYP == 'Y') 
                    && DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT <= DDB5981_AWA_5981.TIER_AMT[WS_POS-1].TAMT) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_LEVEL_INVALID;
                    WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT != 0 
                || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TGRP.trim().length() > 0 
                || DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TCLS.trim().length() > 0) {
                CEP.TRC(SCCGWA, "TIERED AMOUNT NOT EQUAL ZERO");
                if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].FIX_RATE != 0) {
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD1.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT1 != 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR1 != 0)) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD2.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT2 != 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR2 != 0)) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD3.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT3 != 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR3 != 0)) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD4.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT5 != 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR4 != 0)) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD5.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT5 != 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR5 != 0)) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP != ' ') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_SPRD_TYP_CANOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG != ' ') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HL_FLG_CAN_NOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE != 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MAX_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE != 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MIN_RAT_CANNOT_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                } else {
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1.trim().length() == 0) 
                        && (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2.trim().length() == 0) 
                        && (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3.trim().length() == 0) 
                        && (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4.trim().length() == 0) 
                        && (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5.trim().length() == 0)) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_M_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD1.trim().length() > 0)) {
                        WS_BASERATE.WS_CCY_TYPE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1;
                        WS_BASERATE.WS_TENOR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD1;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1_NO;
                        R000_CHECK_BASE_RATE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD2.trim().length() > 0)) {
                        WS_BASERATE.WS_CCY_TYPE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2;
                        WS_BASERATE.WS_TENOR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD2;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2_NO;
                        R000_CHECK_BASE_RATE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD3.trim().length() > 0)) {
                        WS_BASERATE.WS_CCY_TYPE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3;
                        WS_BASERATE.WS_TENOR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD3;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3_NO;
                        R000_CHECK_BASE_RATE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD4.trim().length() > 0)) {
                        WS_BASERATE.WS_CCY_TYPE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4;
                        WS_BASERATE.WS_TENOR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD4;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4_NO;
                        R000_CHECK_BASE_RATE();
                    }
                    if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5.trim().length() > 0) 
                        || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD5.trim().length() > 0)) {
                        WS_BASERATE.WS_CCY_TYPE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5;
                        WS_BASERATE.WS_TENOR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD5;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5_NO;
                        R000_CHECK_BASE_RATE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP != '1' 
                        && DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP != '2' 
                        && DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP != '3' 
                        && DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP != ' ') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TIR_TYP_INVALID;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG == ' ') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HL_FLG_MUST_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE == 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MAX_RATE_MUST_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE == 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MIN_RATE_MUST_INPUT;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                    if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE < DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_RATE_RANGE_INVALID;
                        WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 5) {
            if ((DDB5981_AWA_5981.INT_TYP == 'A' 
                || DDB5981_AWA_5981.INT_TYP == 'M' 
                || DDB5981_AWA_5981.INT_TYP == 'Q' 
                || DDB5981_AWA_5981.INT_TYP == 'Y') 
                && DDB5981_AWA_5981.TIER_AMT[5-1].TAMT != K_ALL_9) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TOP_LVL_AMT_INVALID;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[5-1].TAMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if ((WS_CNT > 1) 
            && (WS_CNT < 5)) {
            WS_POS = (short) (WS_CNT - 1);
            if ((DDB5981_AWA_5981.INT_TYP == 'A' 
                || DDB5981_AWA_5981.INT_TYP == 'M' 
                || DDB5981_AWA_5981.INT_TYP == 'Q' 
                || DDB5981_AWA_5981.INT_TYP == 'Y') 
                && DDB5981_AWA_5981.TIER_AMT[WS_POS-1].TAMT != K_ALL_9) {
                CEP.TRC(SCCGWA, K_ALL_9);
                CEP.TRC(SCCGWA, DDB5981_AWA_5981.TIER_AMT[WS_POS-1].TAMT);
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TOP_LVL_AMT_INVALID;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_POS-1].TAMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        while (WS_CNT <= 5) {
            if (DDB5981_AWA_5981.INT_TYP == 'G' 
                && DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TGRP.trim().length() > 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TGRP_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TGRP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5981_AWA_5981.INT_TYP == 'C' 
                && DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TCLS.trim().length() > 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TCLS_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TCLS_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD1.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT1 != 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR1 != 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD2.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT2 != 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR2 != 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD3.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT3 != 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR3 != 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD4.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT4 != 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR4 != 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if ((DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD5.trim().length() > 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT5 != 0) 
                || (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR5 != 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAS_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP != ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_SPRD_TYP_CANOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG != ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HL_FLG_CAN_NOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MAX_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MIN_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].FIX_RATE != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FIX_RAT_CANNOT_INPUT;
                WS_FLD_NO = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].FIX_RATE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            WS_CNT = (short) (WS_CNT + 1);
        }
    }
    public void B020_ADD_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMRAT);
        DDCSMRAT.KEY.PARM_CODE = DDB5981_AWA_5981.PARM_CD;
        DDCSMRAT.KEY.CCY = DDB5981_AWA_5981.CCY;
        DDCSMRAT.VAL.MIN_AMT = DDB5981_AWA_5981.MIN_AMT;
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.EFFDAT);
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.EXPDAT);
        DDCSMRAT.VAL.TIER_TYPE = DDB5981_AWA_5981.INT_TYP;
        DDCSMRAT.VAL.AMT_TIER_TYPE = DDB5981_AWA_5981.AGSP_FLG;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            DDCSMRAT.VAL.TIER[WS_CNT-1].TAMT = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TAMT;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TGRP = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TGRP;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TCLS = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TCLS;
            DDCSMRAT.VAL.TIER[WS_CNT-1].SPR_TYPE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIR_TYP;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[1-1].INT_RBAS = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS1;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[1-1].INT_RCD = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD1;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[1-1].TIER_SPR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR1;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[1-1].TIER_SPR_PCT = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT1;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[2-1].INT_RBAS = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS2;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[2-1].INT_RCD = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD2;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[2-1].TIER_SPR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR2;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[2-1].TIER_SPR_PCT = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT2;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[3-1].INT_RBAS = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS3;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[3-1].INT_RCD = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD3;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[3-1].TIER_SPR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR3;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[3-1].TIER_SPR_PCT = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT3;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[4-1].INT_RBAS = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS4;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[4-1].INT_RCD = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD4;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[4-1].TIER_SPR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR4;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[4-1].TIER_SPR_PCT = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT4;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[5-1].INT_RBAS = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRBAS5;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[5-1].INT_RCD = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].INTRCD5;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[5-1].TIER_SPR = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].TIERSPR5;
            DDCSMRAT.VAL.TIER[WS_CNT-1].TIER_IR[5-1].TIER_SPR_PCT = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].SPRPCT5;
            DDCSMRAT.VAL.TIER[WS_CNT-1].HL_FLG = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].HL_FLG;
            DDCSMRAT.VAL.TIER[WS_CNT-1].MAX_RATE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MAX_RATE;
            DDCSMRAT.VAL.TIER[WS_CNT-1].MIN_RATE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].MIN_RATE;
            DDCSMRAT.VAL.TIER[WS_CNT-1].FIX_RATE = DDB5981_AWA_5981.TIER_AMT[WS_CNT-1].FIX_RATE;
            CEP.TRC(SCCGWA, DDCSMRAT.VAL.TIER[WS_CNT-1].FIX_RATE);
        }
        DDCSMRAT.VAL.OD_INT_RBAS = DDB5981_AWA_5981.OD_RBSE;
        DDCSMRAT.VAL.OD_INT_RCD = DDB5981_AWA_5981.OD_RCD;
        DDCSMRAT.VAL.OD_SPR = DDB5981_AWA_5981.OD_SPRD;
        DDCSMRAT.VAL.OD_PCT = DDB5981_AWA_5981.OD_PCT;
        DDCSMRAT.VAL.OD_RATE = DDB5981_AWA_5981.OD_RATE;
        DDCSMRAT.VAL.UOD_INT_RBAS = DDB5981_AWA_5981.UOD_RBSE;
        DDCSMRAT.VAL.UOD_INT_RCD = DDB5981_AWA_5981.UOD_RCD;
        DDCSMRAT.VAL.UOD_SPR = DDB5981_AWA_5981.UOD_SPRD;
        DDCSMRAT.VAL.UOD_PCT = DDB5981_AWA_5981.UOD_PCT;
        DDCSMRAT.VAL.UOD_RATE = DDB5981_AWA_5981.UOD_RATE;
        DDCSMRAT.VAL.TOD_INT_RBAS = DDB5981_AWA_5981.TOD_RBSE;
        DDCSMRAT.VAL.TOD_INT_RCD = DDB5981_AWA_5981.TOD_RCD;
        DDCSMRAT.VAL.TOD_SPR = DDB5981_AWA_5981.TOD_SPRD;
        DDCSMRAT.VAL.TOD_PCT = DDB5981_AWA_5981.TOD_PCT;
        DDCSMRAT.VAL.TOD_RATE = DDB5981_AWA_5981.TOD_RATE;
        DDCSMRAT.FUNC = 'A';
        S000_CALL_DDZSMRAT();
    }
    public void R000_CHECK_BASE_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5981_AWA_5981.CCY);
        CEP.TRC(SCCGWA, WS_BASERATE.WS_CCY_TYPE);
        CEP.TRC(SCCGWA, WS_BASERATE.WS_TENOR);
    }
    public void S000_CALL_DDZSMRAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MAIN-MRAT", DDCSMRAT);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
