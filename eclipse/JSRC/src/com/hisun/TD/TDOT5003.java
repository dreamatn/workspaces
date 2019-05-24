package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5003 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD500";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String K_TIRUL_TYP = "TIRUL";
    String WS_MSGID = " ";
    TDOT5003_WS_TERM1 WS_TERM1 = new TDOT5003_WS_TERM1();
    TDOT5003_WS_TERM2 WS_TERM2 = new TDOT5003_WS_TERM2();
    TDOT5003_WS_TERM3 WS_TERM3 = new TDOT5003_WS_TERM3();
    TDOT5003_WS_TERM4 WS_TERM4 = new TDOT5003_WS_TERM4();
    TDOT5003_WS_TERM5 WS_TERM5 = new TDOT5003_WS_TERM5();
    TDOT5003_WS_TERM6 WS_TERM6 = new TDOT5003_WS_TERM6();
    short WS_I = 0;
    TDOT5003_WS_IRAT_INFO WS_IRAT_INFO = new TDOT5003_WS_IRAT_INFO();
    TDOT5003_CP_PROD_CD CP_PROD_CD = new TDOT5003_CP_PROD_CD();
    short WS_CNT = 0;
    short WS_CNT2 = 0;
    char WS_CCY_FOUND_FLG = ' ';
    String WS_INT_RUL_CD = " ";
    String WS_MIN_CCY = " ";
    String WS_TENOR = " ";
    short WS_X = 0;
    TDOT5003_WS_TERM[] WS_TERM = new TDOT5003_WS_TERM[6];
    short WS_IDX = 0;
    TDOT5003_WDS_CCYS WDS_CCYS = new TDOT5003_WDS_CCYS();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSRDMA TDCSRDMA = new TDCSRDMA();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCQPOD TDCQPOD = new TDCQPOD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCIRULP TDCIRULP = new TDCIRULP();
    TDCPRDP TDCPRDPO = new TDCPRDP();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCGWA SCCGWA;
    TDCPROD TDCPROD;
    public TDOT5003() {
        for (int i=0;i<6;i++) WS_TERM[i] = new TDOT5003_WS_TERM();
    }
    public void MP(SCCGWA SCCGWA, TDCPROD TDCPROD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPROD = TDCPROD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        B120_MOD_PRD_PARM();
        B220_WRT_NHIS_M();
        B300_OUTPUT_INF();
        CEP.TRC(SCCGWA, "TDOT5003 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCPROD.PRD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDCPROD.EFF_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_EFF_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (TDCPROD.EXP_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
            S000_ERR_MSG_PROC();
        }
        if (TDCPROD.PROD_DATA.TXN_PRM.CASH_FLG != 'Y' 
            && TDCPROD.PROD_DATA.TXN_PRM.CASH_FLG != 'N') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_IPT;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 1; WS_I <= 12; WS_I += 1) {
            if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM = "";
            JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM += " ";
            if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                IBS.CPY2CLS(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM1, WS_TERM1);
                IBS.CPY2CLS(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM2, WS_TERM2);
                IBS.CPY2CLS(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM3, WS_TERM3);
                IBS.CPY2CLS(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM4, WS_TERM4);
                IBS.CPY2CLS(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM5, WS_TERM5);
                IBS.CPY2CLS(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM6, WS_TERM6);
                if (!TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM1.equalsIgnoreCase(WS_TERM1.WS_TYPE1+"") 
                    || !TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM2.equalsIgnoreCase(WS_TERM2.WS_TYPE2+"") 
                    || !TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM3.equalsIgnoreCase(WS_TERM3.WS_TYPE3+"") 
                    || !TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM4.equalsIgnoreCase(WS_TERM4.WS_TYPE4+"") 
                    || !TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM5.equalsIgnoreCase(WS_TERM5.WS_TYPE5+"") 
                    || !TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM6.equalsIgnoreCase(WS_TERM6.WS_TYPE6+"")) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_NONSTANDARD;
                    S000_ERR_MSG_PROC();
                }
                if (WS_TERM1.WS_TYPE1 != ' ' 
                    && !IBS.isNumeric(WS_TERM1.WS_NUM1+"") 
                    || WS_TERM2.WS_TYPE2 != ' ' 
                    && !IBS.isNumeric(WS_TERM2.WS_NUM2+"") 
                    || WS_TERM3.WS_TYPE3 != ' ' 
                    && !IBS.isNumeric(WS_TERM3.WS_NUM3+"") 
                    || WS_TERM4.WS_TYPE4 != ' ' 
                    && !IBS.isNumeric(WS_TERM4.WS_NUM4+"") 
                    || WS_TERM5.WS_TYPE5 != ' ' 
                    && !IBS.isNumeric(WS_TERM5.WS_NUM5+"") 
                    || WS_TERM6.WS_TYPE6 != ' ' 
                    && !IBS.isNumeric(WS_TERM6.WS_NUM6+"")) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_NUM_NONSTANDARD;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        B011_CHECK_INPUT();
        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP == 'L') {
            if (!TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_CCYC.equalsIgnoreCase("156")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_L_CCY_M_RMB;
                S000_ERR_MSG_PROC();
            }
        } else if ((TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP == 'F' 
                || TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP == 'M' 
                || TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP == 'A')) {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        for (WS_CNT = 1; WS_CNT <= 12; WS_CNT += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
            if (JIBS_tmp_str[0].trim().length() > 0 
                && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.trim().length() > 0) {
                CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
                WS_INT_RUL_CD = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
                B090_CHECK_IRU();
            }
        }
    }
    public void B090_CHECK_IRU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_TIRUL_TYP;
        BPRPRMT.KEY.CD = WS_INT_RUL_CD;
        BPCPRMM.EFF_DT = TDCPROD.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
        if (TDCIRULP.SPRD_OPT == 'A') {
        } else {
            B091_CHECK_CCY();
        }
    }
    public void B091_CHECK_CCY() throws IOException,SQLException,Exception {
        WS_CCY_FOUND_FLG = 'N';
        for (WS_CNT2 = 1; WS_CNT2 <= 12 
            && WS_CCY_FOUND_FLG != 'Y'; WS_CNT2 += 1) {
            if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCIRULP.CCY.CCYS[WS_CNT2-1].SPT_CCY)) {
                WS_CCY_FOUND_FLG = 'Y';
            }
        }
        if (WS_CCY_FOUND_FLG == 'N') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_SUP_IRUL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B110_CHECK_CCY() throws IOException,SQLException,Exception {
        if (!TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_CCYC.equalsIgnoreCase("156")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_L_CCY_M_RMB;
            S000_ERR_MSG_PROC();
        }
    }
    public void B120_CHECK_CCY() throws IOException,SQLException,Exception {
        if ((TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP == 'F' 
            || TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP == 'M') 
            && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_CCYC.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_F_CCY_NEED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPROD.PRDO_CDM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (TDCPROD.EXP_DT <= TDCPROD.EFF_DT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_EXPDT_LESS_EFFDT;
            S000_ERR_MSG_PROC();
        }
        if (TDCPROD.PRDO_CDM.equalsIgnoreCase("TLZ")) {
            for (WS_X = 1; WS_X <= 12; WS_X += 1) {
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        for (WS_X = 1; WS_X <= 12; WS_X += 1) {
            if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
            JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
            if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1 == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1 = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1 += " ";
                WS_TERM[1-1].WS_TERM_FIRST = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1.substring(0, 1).charAt(0);
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2 == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2 = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2 += " ";
                WS_TERM[2-1].WS_TERM_FIRST = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2.substring(0, 1).charAt(0);
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3 == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3 = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3 += " ";
                WS_TERM[3-1].WS_TERM_FIRST = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3.substring(0, 1).charAt(0);
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4 == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4 = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4 += " ";
                WS_TERM[4-1].WS_TERM_FIRST = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4.substring(0, 1).charAt(0);
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5 == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5 = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5 += " ";
                WS_TERM[5-1].WS_TERM_FIRST = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5.substring(0, 1).charAt(0);
                if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6 == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6 = "";
                JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6 += " ";
                WS_TERM[6-1].WS_TERM_FIRST = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6.substring(0, 1).charAt(0);
                if (WS_TERM[1-1].WS_TERM_FIRST != TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1 
                        && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "TEST1");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_STAND_TERM_ERROR;
                    S000_ERR_MSG_PROC();
                } else if (WS_TERM[2-1].WS_TERM_FIRST != TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2 
                        && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "TEST2");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_STAND_TERM_ERROR;
                    S000_ERR_MSG_PROC();
                } else if (WS_TERM[3-1].WS_TERM_FIRST != TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3 
                        && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "TEST3");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_STAND_TERM_ERROR;
                    S000_ERR_MSG_PROC();
                } else if (WS_TERM[4-1].WS_TERM_FIRST != TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4 
                        && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "TEST4");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_STAND_TERM_ERROR;
                    S000_ERR_MSG_PROC();
                } else if (WS_TERM[5-1].WS_TERM_FIRST != TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5 
                        && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "TEST5");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_STAND_TERM_ERROR;
                    S000_ERR_MSG_PROC();
                } else if (WS_TERM[6-1].WS_TERM_FIRST != TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6 
                        && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "TEST6");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_STAND_TERM_ERROR;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, TDCPROD.PRD_CD);
        for (WS_X = 1; WS_X <= 12 
            && TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].MIN_CCYC.trim().length() != 0; WS_X += 1) {
            WS_MIN_CCY = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].MIN_CCYC;
            WS_IRAT_INFO.WS_IRAT_CD = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].RAT_CD;
            if (WS_IRAT_INFO.WS_IRAT_CD.trim().length() > 0) {
                if (TDCPROD.PROD_DATA.TXN_PRM.OTH_FLG.equalsIgnoreCase("06")) {
                    WS_TENOR = "S000";
                    R000_GET_RAT();
                } else {
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "D001";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "D007";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "M001";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "M003";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "M006";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "Y001";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "Y002";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "Y003";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_TENOR = "Y005";
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y")) {
                        if (TDCPROD.PRD_CD.equalsIgnoreCase("RDP00005")) {
                            WS_TENOR = "Y005";
                        } else {
                            WS_TENOR = "Y006";
                        }
                        R000_GET_RAT();
                    }
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                    JIBS_tmp_int = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                    if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1.trim().length() > 0) {
                            WS_TENOR = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1;
                            R000_GET_RAT();
                        }
                        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2.trim().length() > 0) {
                            WS_TENOR = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2;
                            R000_GET_RAT();
                        }
                        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3.trim().length() > 0) {
                            WS_TENOR = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3;
                            R000_GET_RAT();
                        }
                        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4.trim().length() > 0) {
                            WS_TENOR = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4;
                            R000_GET_RAT();
                        }
                        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5.trim().length() > 0) {
                            WS_TENOR = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5;
                            R000_GET_RAT();
                        }
                        if (TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6.trim().length() > 0) {
                            WS_TENOR = TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6;
                            R000_GET_RAT();
                        }
                    }
                }
            }
        }
    }
    public void B120_MOD_PRD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = K_PRDP_TYP;
        CP_PROD_CD.PROD_ACC_CENT = 999999999;
        CP_PROD_CD.PROD_PRDT_CODE = TDCPROD.PRD_CD;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, CP_PROD_CD);
        BPCPRMM.EFF_DT = TDCPROD.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDPO);
        R000_MOVE_TO_PARM();
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, TDCPRDPO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && TDCPROD.DESC.equalsIgnoreCase(BPRPRMT.DESC) 
            && TDCPROD.CDESC.equalsIgnoreCase(BPRPRMT.CDESC) 
            && TDCPROD.EXP_DT == BPCPRMM.EXP_DT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_NO_MOD;
            S000_ERR_MSG_PROC();
        }
        BPCPRMM.FUNC = '2';
        BPRPRMT.DESC = TDCPROD.DESC;
        BPRPRMT.CDESC = TDCPROD.CDESC;
        BPCPRMM.EXP_DT = TDCPROD.EXP_DT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
    }
    public void B220_WRT_NHIS_M() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.REF_NO = TDCPROD.PRD_CD;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.OLD_DAT_PT = TDCPRDPO;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCPRDP;
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPOD);
        TDCQPOD.FUNC = 'M';
        TDCQPOD.PROD_CD = TDCPROD.PRD_CD;
        TDCQPOD.DESC = BPRPRMT.DESC;
        TDCQPOD.CDESC = BPRPRMT.CDESC;
        TDCQPOD.EFF_DT = BPCPRMM.EFF_DT;
        TDCQPOD.EXP_DT = BPCPRMM.EXP_DT;
        TDCQPOD.PRDMO_CD = TDCPRDP.PRDMO_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.OTH_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.FUNC);
        CEP.TRC(SCCGWA, TDCQPOD.PROD_CD);
        CEP.TRC(SCCGWA, TDCQPOD.DESC);
        CEP.TRC(SCCGWA, TDCQPOD.CDESC);
        CEP.TRC(SCCGWA, TDCQPOD.EFF_DT);
        CEP.TRC(SCCGWA, TDCQPOD.EXP_DT);
        CEP.TRC(SCCGWA, TDCQPOD.PRDMO_CD);
        CEP.TRC(SCCGWA, TDCQPOD.TXN_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.INT_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.EXP_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.OTH_PRM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRD_FMT;
        SCCFMT.DATA_PTR = TDCQPOD;
        SCCFMT.DATA_LEN = 5726;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MOVE_TO_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPRDP);
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
    }
    public void R000_GET_RAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
        BPCCINTI.BASE_INFO.CCY = WS_MIN_CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = WS_IRAT_INFO.WS_IRAT_CD;
        BPCCINTI.BASE_INFO.TENOR = WS_TENOR;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        S000_CALL_BPZCINTI();
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
