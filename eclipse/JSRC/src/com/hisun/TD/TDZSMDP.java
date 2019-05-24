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

public class TDZSMDP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTPMP_RD;
    brParm TDTDOCU_BR = new brParm();
    DBParm TDTPMPD_RD;
    brParm TDTPMPD_BR = new brParm();
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD500";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String K_TIRUL_TYP = "TIRUL";
    String WS_MSGID = " ";
    TDZSMDP_WS_TERM1 WS_TERM1 = new TDZSMDP_WS_TERM1();
    TDZSMDP_WS_TERM2 WS_TERM2 = new TDZSMDP_WS_TERM2();
    TDZSMDP_WS_TERM3 WS_TERM3 = new TDZSMDP_WS_TERM3();
    TDZSMDP_WS_TERM4 WS_TERM4 = new TDZSMDP_WS_TERM4();
    TDZSMDP_WS_TERM5 WS_TERM5 = new TDZSMDP_WS_TERM5();
    TDZSMDP_WS_TERM6 WS_TERM6 = new TDZSMDP_WS_TERM6();
    short WS_I = 0;
    short WS_K = 0;
    TDZSMDP_WS_IRAT_INFO WS_IRAT_INFO = new TDZSMDP_WS_IRAT_INFO();
    TDZSMDP_CP_PROD_CD CP_PROD_CD = new TDZSMDP_CP_PROD_CD();
    short WS_CNT = 0;
    short WS_CNT2 = 0;
    char WS_CCY_FOUND_FLG = ' ';
    char WS_DOCU_FLG = ' ';
    String WS_INT_RUL_CD = " ";
    String WS_MIN_CCY = " ";
    String WS_TENOR = " ";
    short WS_X = 0;
    TDZSMDP_WS_TERM[] WS_TERM = new TDZSMDP_WS_TERM[6];
    short WS_IDX = 0;
    TDZSMDP_WDS_CCYS WDS_CCYS = new TDZSMDP_WDS_CCYS();
    short WS_PMPD_SEQ = 0;
    String WS_IBS_AC_BK = " ";
    String WS_LOC_CCY = " ";
    short WS_TERM_I = 0;
    String WS_TERM_R = " ";
    String WS_TERM1_R = " ";
    String WS_TERM2_R = " ";
    String WS_TERM3_R = " ";
    String WS_TERM4_R = " ";
    String WS_TERM5_R = " ";
    String WS_TERM6_R = " ";
    String WS_DOCU_R = " ";
    String WS_BASE_TYP_R = " ";
    short WS_A_POS = 0;
    short WS_M_POS = 0;
    short WS_A_CCY_A = 0;
    String WS_E_INFOMATION = " ";
    TDZSMDP_WS_CCY_A[] WS_CCY_A = new TDZSMDP_WS_CCY_A[25];
    TDZSMDP_WS_CCY_M[] WS_CCY_M = new TDZSMDP_WS_CCY_M[10];
    char WS_PMPD_FLG = ' ';
    char WS_PMP_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSRDMA TDCSRDMA = new TDCSRDMA();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCQPOD TDCQPOD = new TDCQPOD();
    BPCRCCY BPCRCCY = new BPCRCCY();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCIRULP TDCIRULP = new TDCIRULP();
    TDCPRDP TDCPRDPO = new TDCPRDP();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDRPMP TDRPMP = new TDRPMP();
    TDRPMPD TDRPMPD = new TDRPMPD();
    SCCGWA SCCGWA;
    TDCAMPRD TDCAMPRD;
    public TDZSMDP() {
        for (int i=0;i<6;i++) WS_TERM[i] = new TDZSMDP_WS_TERM();
        for (int i=0;i<25;i++) WS_CCY_A[i] = new TDZSMDP_WS_CCY_A();
        for (int i=0;i<10;i++) WS_CCY_M[i] = new TDZSMDP_WS_CCY_M();
    }
    public void MP(SCCGWA SCCGWA, TDCAMPRD TDCAMPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCAMPRD = TDCAMPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        B120_MOD_PRD_PARM();
        B220_WRT_NHIS_M();
        B300_OUTPUT_INF();
        CEP.TRC(SCCGWA, "TDZSMDP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.BAL_FLG);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.MID_FLG);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.DELA_FLG);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CPRA_TYP);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.ACTI_FLG);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.FRZ_FLG);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.BAL_FLG);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.NO_NOTIFY_FLG);
        B010_CHECK_INPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCAMPRD.EFF_DT);
        CEP.TRC(SCCGWA, TDCAMPRD.PRD_CD);
        CEP.TRC(SCCGWA, TDCAMPRD.EXP_DT);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.BAL_FLG);
        if (TDCAMPRD.PRD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDCAMPRD.EFF_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_EFF_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (TDCAMPRD.EXP_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
            S000_ERR_MSG_PROC();
        }
        if (TDCAMPRD.PROD_DATA.TXN_PRM.CASH_FLG != 'Y' 
            && TDCAMPRD.PROD_DATA.TXN_PRM.CASH_FLG != 'N') {
            CEP.TRC(SCCGWA, "111");
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_IPT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_AMTC);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[2-1].MIN_AMTC);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[3-1].MIN_AMTC);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[1-1].FST_AMTC);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[2-1].FST_AMTC);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[3-1].FST_AMTC);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MDRW_AMT);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[2-1].MDRW_AMT);
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[3-1].MDRW_AMT);
        R000_GET_ALL_CCY();
        for (WS_I = 1; WS_I <= 10 
            && TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.trim().length() != 0; WS_I += 1) {
            WS_CCY_M[WS_I-1].WS_CCY_M_T = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
            for (WS_TERM_I = 1; WS_TERM_I <= 10; WS_TERM_I += 1) {
                if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM == null) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM = "";
                JIBS_tmp_int = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM += " ";
                if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.substring(WS_TERM_I - 1, WS_TERM_I + 1 - 1).equalsIgnoreCase("1") 
                    || TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.substring(WS_TERM_I - 1, WS_TERM_I + 1 - 1).equalsIgnoreCase("Y")) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                    if (WS_TERM_I == 1) {
                        BPCCINTI.BASE_INFO.TENOR = "D001";
                    } else if (WS_TERM_I == 2) {
                        BPCCINTI.BASE_INFO.TENOR = "D007";
                    } else if (WS_TERM_I == 3) {
                        BPCCINTI.BASE_INFO.TENOR = "M001";
                    } else if (WS_TERM_I == 4) {
                        BPCCINTI.BASE_INFO.TENOR = "M003";
                    } else if (WS_TERM_I == 5) {
                        BPCCINTI.BASE_INFO.TENOR = "M006";
                    } else if (WS_TERM_I == 6) {
                        BPCCINTI.BASE_INFO.TENOR = "Y001";
                    } else if (WS_TERM_I == 7) {
                        BPCCINTI.BASE_INFO.TENOR = "Y002";
                    } else if (WS_TERM_I == 8) {
                        BPCCINTI.BASE_INFO.TENOR = "Y003";
                    } else if (WS_TERM_I == 9) {
                        BPCCINTI.BASE_INFO.TENOR = "Y005";
                    } else if (WS_TERM_I == 10) {
                        BPCCINTI.BASE_INFO.TENOR = "Y006";
                    } else {
                    }
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    S000_CALL_BPZCINTI();
                }
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase(TDCAMPRD.PROD_DATA.OTH_PRM.REF_CCY)) {
                WS_BASE_TYP_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                WS_TERM_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM;
                WS_TERM1_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM1;
                WS_TERM2_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM2;
                WS_TERM3_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM3;
                WS_TERM4_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM4;
                WS_TERM5_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM5;
                WS_TERM6_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM6;
                WS_DOCU_R = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].DOCU_NO;
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].DOCU_NO.trim().length() > 0) {
                IBS.init(SCCGWA, TDRDOCU);
                TDRDOCU.KEY.DOCU_NO = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].DOCU_NO;
                WS_DOCU_FLG = 'Y';
                T000_STARTBR_TDTDOCU();
                T000_READNEXT_TDTDOCU();
                while (WS_DOCU_FLG != 'N') {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.BASE_TYP = TDRDOCU.IRAT_CD;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    if (TDRDOCU.REF_TERM.trim().length() > 0) {
                        BPCCINTI.BASE_INFO.TENOR = TDRDOCU.REF_TERM;
                    } else {
                        BPCCINTI.BASE_INFO.TENOR = TDRDOCU.KEY.DOCU_TERM;
                    }
                    S000_CALL_BPZCINTI();
                    T000_READNEXT_TDTDOCU();
                }
                T000_ENDBR_TDTDOCU();
            }
        }
        WS_I = 1;
        for (WS_I = 1; WS_I <= 10 
            && TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.trim().length() != 0; WS_I += 1) {
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM == null) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM = "";
            JIBS_tmp_int = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM += " ";
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                CEP.TRC(SCCGWA, "222");
                IBS.CPY2CLS(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM1, WS_TERM1);
                IBS.CPY2CLS(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM2, WS_TERM2);
                IBS.CPY2CLS(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM3, WS_TERM3);
                IBS.CPY2CLS(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM4, WS_TERM4);
                IBS.CPY2CLS(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM5, WS_TERM5);
                IBS.CPY2CLS(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM6, WS_TERM6);
                CEP.TRC(SCCGWA, WS_TERM1);
                CEP.TRC(SCCGWA, WS_TERM2);
                CEP.TRC(SCCGWA, WS_TERM3);
                CEP.TRC(SCCGWA, WS_TERM4);
                CEP.TRC(SCCGWA, WS_TERM5);
                CEP.TRC(SCCGWA, WS_TERM1.WS_TYPE1);
                CEP.TRC(SCCGWA, WS_TERM2.WS_TYPE2);
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
                    CEP.TRC(SCCGWA, "444");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_NUM_NONSTANDARD;
                    S000_ERR_MSG_PROC();
                }
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM1.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = 999999;
                BPCCINTI.BASE_INFO.TENOR = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM1;
                S000_CALL_BPZCINTI();
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM2.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = 999999;
                BPCCINTI.BASE_INFO.TENOR = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM2;
                S000_CALL_BPZCINTI();
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM3.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = 999999;
                BPCCINTI.BASE_INFO.TENOR = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM3;
                S000_CALL_BPZCINTI();
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM4.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = 999999;
                BPCCINTI.BASE_INFO.TENOR = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM4;
                S000_CALL_BPZCINTI();
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM5.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = 999999;
                BPCCINTI.BASE_INFO.TENOR = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM5;
                S000_CALL_BPZCINTI();
            }
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM6.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.BASE_INFO.CCY = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC;
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.BASE_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = 999999;
                BPCCINTI.BASE_INFO.TENOR = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM6;
                S000_CALL_BPZCINTI();
            }
        }
        B011_CHECK_INPUT();
        for (WS_A_CCY_A = 1; WS_A_CCY_A <= WS_A_POS; WS_A_CCY_A += 1) {
            for (WS_M_POS = 1; WS_M_POS < WS_I 
                && !WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL.equalsIgnoreCase(WS_CCY_M[WS_M_POS-1].WS_CCY_M_T); WS_M_POS += 1) {
            }
            if (WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL.equalsIgnoreCase(WS_CCY_M[WS_M_POS-1].WS_CCY_M_T)) {
            } else {
                for (WS_TERM_I = 1; WS_TERM_I <= 10; WS_TERM_I += 1) {
                    if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM == null) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM = "";
                    JIBS_tmp_int = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM += " ";
                    if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.substring(WS_TERM_I - 1, WS_TERM_I + 1 - 1).equalsIgnoreCase("1") 
                        || TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_I-1].TERM.substring(WS_TERM_I - 1, WS_TERM_I + 1 - 1).equalsIgnoreCase("Y")) {
                        IBS.init(SCCGWA, BPCCINTI);
                        BPCCINTI.FUNC = 'I';
                        BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                        BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                        if (WS_TERM_I == 1) {
                            BPCCINTI.BASE_INFO.TENOR = "D001";
                        } else if (WS_TERM_I == 2) {
                            BPCCINTI.BASE_INFO.TENOR = "D007";
                        } else if (WS_TERM_I == 3) {
                            BPCCINTI.BASE_INFO.TENOR = "M001";
                        } else if (WS_TERM_I == 4) {
                            BPCCINTI.BASE_INFO.TENOR = "M003";
                        } else if (WS_TERM_I == 5) {
                            BPCCINTI.BASE_INFO.TENOR = "M006";
                        } else if (WS_TERM_I == 6) {
                            BPCCINTI.BASE_INFO.TENOR = "Y001";
                        } else if (WS_TERM_I == 7) {
                            BPCCINTI.BASE_INFO.TENOR = "Y002";
                        } else if (WS_TERM_I == 8) {
                            BPCCINTI.BASE_INFO.TENOR = "Y003";
                        } else if (WS_TERM_I == 9) {
                            BPCCINTI.BASE_INFO.TENOR = "Y005";
                        } else if (WS_TERM_I == 10) {
                            BPCCINTI.BASE_INFO.TENOR = "Y006";
                        } else {
                        }
                        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPCCINTI.BASE_INFO.BR = 999999;
                        S000_CALL_BPZCINTI();
                    }
                }
                if (WS_TERM1_R.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                    BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                    BPCCINTI.BASE_INFO.TENOR = WS_TERM1_R;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_BPZCINTI();
                }
                if (WS_TERM2_R.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                    BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                    BPCCINTI.BASE_INFO.TENOR = WS_TERM2_R;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_BPZCINTI();
                }
                if (WS_TERM3_R.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                    BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                    BPCCINTI.BASE_INFO.TENOR = WS_TERM3_R;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_BPZCINTI();
                }
                if (WS_TERM4_R.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                    BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                    BPCCINTI.BASE_INFO.TENOR = WS_TERM4_R;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_BPZCINTI();
                }
                if (WS_TERM5_R.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                    BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                    BPCCINTI.BASE_INFO.TENOR = WS_TERM5_R;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_BPZCINTI();
                }
                if (WS_TERM6_R.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                    BPCCINTI.BASE_INFO.BASE_TYP = WS_BASE_TYP_R;
                    BPCCINTI.BASE_INFO.TENOR = WS_TERM6_R;
                    BPCCINTI.BASE_INFO.BR = 999999;
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_BPZCINTI();
                }
                if (WS_DOCU_R.trim().length() > 0) {
                    IBS.init(SCCGWA, TDRDOCU);
                    TDRDOCU.KEY.DOCU_NO = WS_DOCU_R;
                    WS_DOCU_FLG = 'Y';
                    T000_STARTBR_TDTDOCU();
                    T000_READNEXT_TDTDOCU();
                    while (WS_DOCU_FLG != 'N') {
                        IBS.init(SCCGWA, BPCCINTI);
                        BPCCINTI.BASE_INFO.CCY = WS_CCY_A[WS_A_CCY_A-1].WS_CCY_ALL;
                        BPCCINTI.FUNC = 'I';
                        BPCCINTI.BASE_INFO.BASE_TYP = TDRDOCU.IRAT_CD;
                        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPCCINTI.BASE_INFO.BR = 999999;
                        if (TDRDOCU.REF_TERM.trim().length() > 0) {
                            BPCCINTI.BASE_INFO.TENOR = TDRDOCU.REF_TERM;
                        } else {
                            BPCCINTI.BASE_INFO.TENOR = TDRDOCU.KEY.DOCU_TERM;
                        }
                        S000_CALL_BPZCINTI();
                        T000_READNEXT_TDTDOCU();
                    }
                    T000_ENDBR_TDTDOCU();
                }
            }
        }
        CEP.TRC(SCCGWA, "555");
        if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'L') {
            if (!TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_CCYC.equalsIgnoreCase("156")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_L_CCY_M_RMB;
                S000_ERR_MSG_PROC();
            }
        } else if ((TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'F' 
                || TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'M' 
                || TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'A')) {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            CEP.TRC(SCCGWA, "666");
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, "777");
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
            if (JIBS_tmp_str[0].trim().length() > 0 
                && TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.trim().length() > 0) {
                CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
                WS_INT_RUL_CD = IBS.CLS2CPY(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
            }
        }
    }
    public void B091_CHECK_CCY() throws IOException,SQLException,Exception {
        WS_CCY_FOUND_FLG = 'N';
        for (WS_CNT2 = 1; WS_CNT2 <= 12 
            && WS_CCY_FOUND_FLG != 'Y'; WS_CNT2 += 1) {
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCIRULP.CCY.CCYS[WS_CNT2-1].SPT_CCY)) {
                WS_CCY_FOUND_FLG = 'Y';
            }
        }
        if (WS_CCY_FOUND_FLG == 'N') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_SUP_IRUL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B110_CHECK_CCY() throws IOException,SQLException,Exception {
        if (!TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_CCYC.equalsIgnoreCase("156")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_L_CCY_M_RMB;
            S000_ERR_MSG_PROC();
        }
    }
    public void B120_CHECK_CCY() throws IOException,SQLException,Exception {
        if ((TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'F' 
            || TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'M') 
            && TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[1-1].MIN_CCYC.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_F_CCY_NEED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCAMPRD.PRDO_CDM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (TDCAMPRD.EXP_DT <= TDCAMPRD.EFF_DT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_EXPDT_LESS_EFFDT;
            S000_ERR_MSG_PROC();
        }
        if (TDCAMPRD.PRDO_CDM.equalsIgnoreCase("TLZ")) {
            CEP.TRC(SCCGWA, "1111");
            for (WS_X = 1; WS_X <= 10; WS_X += 1) {
                if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM == null) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM = "";
                JIBS_tmp_int = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM += " ";
                if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                    if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM1.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM2.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM3.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM4.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM5.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_X-1].TERM6.equalsIgnoreCase("D")) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_TLZ_TERM_ERROR;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, "2222");
    }
    public void B120_MOD_PRD_PARM() throws IOException,SQLException,Exception {
        R000_MOVE_TO_PARM();
        B130_REWRITE_TDTPMP();
        B140_REWRITE_TDTPMPD();
    }
    public void B130_REWRITE_TDTPMP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPMP);
        TDRPMP.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMP.KEY.PRD_CD = TDCAMPRD.PRD_CD;
        T000_READUP_TDTPMP();
        if (WS_PMP_FLG == 'N') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PMP_REC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, TDRPMP);
        TDRPMP.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMP.KEY.PRD_CD = TDCAMPRD.PRD_CD;
        TDRPMP.SDT = TDCAMPRD.EFF_DT;
        TDRPMP.DDT = TDCAMPRD.EXP_DT;
        TDRPMP.EDESC = TDCAMPRD.DESC;
        TDRPMP.CDESC = TDCAMPRD.CDESC;
        TDRPMP.PRDMO_CD = TDCAMPRD.PRD_CDM;
        TDRPMP.PART_NUM = TDCAMPRD.PROD_DATA.TXN_PRM.PART_NUM;
        TDRPMP.CASH_FLG = TDCAMPRD.PROD_DATA.TXN_PRM.CASH_FLG;
        TDRPMP.OTH_FLG = TDCAMPRD.PROD_DATA.TXN_PRM.OTH_FLG;
        TDRPMP.BV_TYP = TDCAMPRD.PROD_DATA.TXN_PRM.BV_TYP;
        TDRPMP.DRAW_MTH = TDCAMPRD.PROD_DATA.TXN_PRM.DRAW_MTH;
        TDRPMP.CR_LMT = TDCAMPRD.PROD_DATA.TXN_PRM.CR_LMT;
        TDRPMP.DR_LMT = TDCAMPRD.PROD_DATA.TXN_PRM.DR_LMT;
        TDRPMP.STA_LMT = TDCAMPRD.PROD_DATA.TXN_PRM.STA_LMT;
        TDRPMP.RSID_LMT = TDCAMPRD.PROD_DATA.TXN_PRM.RSID_LMT;
        TDRPMP.CUST_CTL = TDCAMPRD.PROD_DATA.TXN_PRM.CUST_CTL;
        TDRPMP.NON_CTL = TDCAMPRD.PROD_DATA.TXN_PRM.NON_CTL;
        TDRPMP.BUD_FLG = TDCAMPRD.PROD_DATA.INT_PRM.BUD_FLG;
        TDRPMP.BUD_PERD = TDCAMPRD.PROD_DATA.INT_PRM.BUD_PERD;
        TDRPMP.BUD_DATE = TDCAMPRD.PROD_DATA.INT_PRM.BUD_DATE;
        TDRPMP.IRAT_TYP = TDCAMPRD.PROD_DATA.INT_PRM.IRAT_TYP;
        TDRPMP.RAT_INX = TDCAMPRD.PROD_DATA.INT_PRM.RAT_INX;
        TDRPMP.NORM_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.NORM_TYP;
        TDRPMP.ERLY_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.ERLY_TYP;
        TDRPMP.LATE_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.LATE_TYP;
        TDRPMP.RES_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.RES_TYP;
        TDRPMP.DOCU_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.DOCU_TYP;
        TDRPMP.TAX_CD = TDCAMPRD.PROD_DATA.EXP_PRM.TAX_CD;
        TDRPMP.TAX_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.TAX_TYP;
        TDRPMP.SPRD_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.SPRD_FLG;
        TDRPMP.TWAV_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.TWAV_FLG;
        TDRPMP.DUE_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.DUE_FLG;
        TDRPMP.OFD_TYP = TDCAMPRD.PROD_DATA.EXP_PRM.OFD_TYP;
        TDRPMP.HLID_RUL = TDCAMPRD.PROD_DATA.EXP_PRM.HLID_RUL;
        TDRPMP.INR_MTH = TDCAMPRD.PROD_DATA.EXP_PRM.INR_MTH;
        TDRPMP.RNEW_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.RNEW_FLG;
        TDRPMP.NENEW_RUL_CD = TDCAMPRD.PROD_DATA.EXP_PRM.NENEW_RUL_CD;
        TDRPMP.LMT_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.LMT_FLG;
        TDRPMP.ONTM_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.ONTM_FLG;
        TDRPMP.OPTM_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.OPTM_FLG;
        TDRPMP.MDF_FLG = TDCAMPRD.PROD_DATA.EXP_PRM.MDF_FLG;
        TDRPMP.PRD_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.PRD_TYP;
        TDRPMP.MAX_NUM = TDCAMPRD.PROD_DATA.OTH_PRM.MAX_NUM;
        TDRPMP.PAY_GRCE = TDCAMPRD.PROD_DATA.OTH_PRM.PAY_GRCE;
        TDRPMP.PAY_PERD = TDCAMPRD.PROD_DATA.OTH_PRM.PAY_PERD;
        TDRPMP.PLAN_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.PLAN_FLG;
        TDRPMP.MIN_TERM = TDCAMPRD.PROD_DATA.OTH_PRM.MIN_TERM;
        TDRPMP.MAX_TERM = TDCAMPRD.PROD_DATA.OTH_PRM.MAX_TERM;
        TDRPMP.INT_PRD1 = TDCAMPRD.PROD_DATA.OTH_PRM.INT_PRD1;
        TDRPMP.INT_PRD2 = TDCAMPRD.PROD_DATA.OTH_PRM.INT_PRD2;
        TDRPMP.INT_PRD3 = TDCAMPRD.PROD_DATA.OTH_PRM.INT_PRD3;
        TDRPMP.COMP_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.COMP_FLG;
        TDRPMP.INTP_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.INTP_FLG;
        TDRPMP.PENA_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.PENA_FLG;
        TDRPMP.UNIT_DAY = TDCAMPRD.PROD_DATA.OTH_PRM.UNIT_DAY;
        TDRPMP.INT_PERD = TDCAMPRD.PROD_DATA.OTH_PRM.INT_PERD;
        TDRPMP.MAX_GRCE = TDCAMPRD.PROD_DATA.OTH_PRM.MAX_GRCE;
        TDRPMP.CCY_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP;
        TDRPMP.DOCU_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.DOCU_FLG;
        TDRPMP.REF_CCY = TDCAMPRD.PROD_DATA.OTH_PRM.REF_CCY;
        CEP.TRC(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM.NO_NOTIFY_FLG);
        TDRPMP.NO_NOTIFY_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.NO_NOTIFY_FLG;
        TDRPMP.BAL_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.BAL_FLG;
        TDRPMP.MID_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.MID_FLG;
        TDRPMP.DELA_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.DELA_FLG;
        TDRPMP.CPRA_TYP = TDCAMPRD.PROD_DATA.OTH_PRM.CPRA_TYP;
        TDRPMP.ACTI_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.ACTI_FLG;
        TDRPMP.FRZ_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.FRZ_FLG;
        TDRPMP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPMP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPMP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_REWRITE_TDTPMP();
    }
    public void B140_REWRITE_TDTPMPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPMPD);
        TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMPD.KEY.PRD_CD = TDCAMPRD.PRD_CD;
        T000_DELETE_TDTPMPD();
        if (TDRPMP.CCY_TYP == 'M') {
            for (WS_K = 1; WS_K <= 16; WS_K += 1) {
                if (TDCAMPRD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_K-1].AVA_CCY.trim().length() > 0) {
                    WS_PMPD_SEQ += +1;
                    TDRPMPD.KEY.SEQ = WS_PMPD_SEQ;
                    TDRPMPD.KEY.PRD_CD = TDCAMPRD.PRD_CD;
                    TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                    TDRPMPD.KEY.PRM_TYP = 'A';
                    TDRPMPD.AVA_CCY = TDCAMPRD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_K-1].AVA_CCY;
                    TDRPMPD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    TDRPMPD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRPMPD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                    T000_WRITE_TDTPMPD();
                }
            }
        }
        WS_PMPD_SEQ = 0;
        for (WS_K = 1; WS_K <= 10; WS_K += 1) {
            if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC.trim().length() > 0) {
                IBS.init(SCCGWA, TDRPMPD);
                WS_PMPD_SEQ += +1;
                TDRPMPD.KEY.SEQ = WS_PMPD_SEQ;
                TDRPMPD.KEY.PRD_CD = TDCAMPRD.PRD_CD;
                TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                TDRPMPD.KEY.PRM_TYP = 'M';
                TDRPMPD.MIN_CCYC = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC;
                TDRPMPD.ADD_AMTC = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].ADD_AMTC;
                TDRPMPD.FAVO_FLG = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FAVO_FLG;
                TDRPMPD.MIN_AMTC = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_AMTC;
                TDRPMPD.FST_AMTC = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FST_AMTC;
                TDRPMPD.MDRW_AMT = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MDRW_AMT;
                TDRPMPD.MLET_AMT = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MLET_AMT;
                TDRPMPD.MAX_AMT = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MAX_AMT;
                TDRPMPD.RUL_CD1 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD1;
                TDRPMPD.RUL_CD2 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD2;
                TDRPMPD.RUL_CD3 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD3;
                TDRPMPD.RUL_CD4 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD4;
                TDRPMPD.RUL_CD5 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD5;
                TDRPMPD.RUL_CD6 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD6;
                TDRPMPD.RUL_CD7 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD7;
                TDRPMPD.RUL_CD8 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD8;
                TDRPMPD.RUL_CD9 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD9;
                TDRPMPD.RUL_CD10 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD10;
                TDRPMPD.TERM = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM;
                TDRPMPD.TERM_T1 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T1;
                TDRPMPD.TERM1 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM1;
                TDRPMPD.TERM_T2 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T2;
                TDRPMPD.TERM2 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM2;
                TDRPMPD.TERM_T3 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T3;
                TDRPMPD.TERM3 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM3;
                TDRPMPD.TERM_T4 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T4;
                TDRPMPD.TERM4 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM4;
                TDRPMPD.TERM_T5 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T5;
                TDRPMPD.TERM5 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM5;
                TDRPMPD.TERM_T6 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T6;
                TDRPMPD.TERM6 = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM6;
                TDRPMPD.INT_RUL = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].INT_RUL;
                TDRPMPD.RAT_CD = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RAT_CD;
                TDRPMPD.OFRAT_CD = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].OFRAT_CD;
                TDRPMPD.SEG_MTH = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].SEG_MTH;
                TDRPMPD.NERAT_CD = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].NERAT_CD;
                TDRPMPD.PRRAT_CD = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].PRRAT_CD;
                TDRPMPD.DOCU_NO = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].DOCU_NO;
                TDRPMPD.FD_TOP_IRAT_CD = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FD_TOP_IRAT_CD;
                TDRPMPD.FD_TOP_PCT = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FD_TOP_PCT;
                TDRPMPD.EXC_LMT_PROC = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].EXC_LMT_PROC;
                TDRPMPD.AUTH_LEL = TDCAMPRD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].AUTH_LEL;
                TDRPMPD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRPMPD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRPMPD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_WRITE_TDTPMPD();
            }
        }
    }
    public void B220_WRT_NHIS_M() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.REF_NO = TDCAMPRD.PRD_CD;
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
        TDCQPOD.PROD_CD = TDCAMPRD.PRD_CD;
        TDCQPOD.DESC = TDCAMPRD.DESC;
        TDCQPOD.CDESC = TDCAMPRD.CDESC;
        TDCQPOD.EFF_DT = TDCAMPRD.EFF_DT;
        TDCQPOD.EXP_DT = TDCAMPRD.EXP_DT;
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
        TDCPRDP.PRDMO_CD = TDCAMPRD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCAMPRD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCAMPRD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCAMPRD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCAMPRD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
    }
    public void R000_GET_ALL_CCY() throws IOException,SQLException,Exception {
        if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'A') {
            IBS.init(SCCGWA, BPCRCCY);
            BPCRCCY.OP_FUNC = 'S';
            S000_CALL_BPZRCCY();
            BPCRCCY.OP_FUNC = 'R';
            S000_CALL_BPZRCCY();
            WS_A_POS = 1;
            while (BPCRCCY.RC.RTNCODE == 0) {
                CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
                if (BPCRCCY.DATA.CASH_FLG == '0') {
                    WS_CCY_A[WS_A_POS-1].WS_CCY_ALL = BPCRCCY.DATA.CCY;
                }
                WS_A_POS += 1;
                BPCRCCY.OP_FUNC = 'R';
                S000_CALL_BPZRCCY();
            }
            BPCRCCY.OP_FUNC = 'E';
            WS_A_POS = (short) (WS_A_POS - 1);
        } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'M') {
            WS_A_POS = 1;
            while (WS_A_POS <= 16 
                && TDCAMPRD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_A_POS-1].AVA_CCY.trim().length() != 0) {
                WS_CCY_A[WS_A_POS-1].WS_CCY_ALL = TDCAMPRD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_A_POS-1].AVA_CCY;
                WS_A_POS += 1;
            }
            WS_A_POS = (short) (WS_A_POS - 1);
        } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'L') {
            IBS.init(SCCGWA, BPCPQBNK);
            BPCPQBNK.DATA_INFO.BNK = "043";
            S000_CALL_BPZPQBNK();
            WS_CCY_A[1-1].WS_CCY_ALL = BPCPQBNK.DATA_INFO.LOC_CCY1;
            WS_A_POS = 1;
        } else if (TDCAMPRD.PROD_DATA.OTH_PRM.CCY_TYP == 'F') {
            IBS.init(SCCGWA, BPCPQBNK);
            BPCPQBNK.DATA_INFO.BNK = "043";
            S000_CALL_BPZPQBNK();
            WS_LOC_CCY = BPCPQBNK.DATA_INFO.LOC_CCY1;
            IBS.init(SCCGWA, BPCRCCY);
            BPCRCCY.OP_FUNC = 'S';
            S000_CALL_BPZRCCY();
            BPCRCCY.OP_FUNC = 'R';
            S000_CALL_BPZRCCY();
            WS_A_POS = 1;
            while (BPCRCCY.RC.RTNCODE == 0) {
                CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
                if (!BPCRCCY.DATA.CCY.equalsIgnoreCase(WS_LOC_CCY)) {
                    if (BPCRCCY.DATA.CASH_FLG == '0') {
                        WS_CCY_A[WS_A_POS-1].WS_CCY_ALL = BPCRCCY.DATA.CCY;
                    }
                    WS_A_POS += 1;
                }
                BPCRCCY.OP_FUNC = 'R';
                S000_CALL_BPZRCCY();
            }
            BPCRCCY.OP_FUNC = 'E';
            WS_A_POS = (short) (WS_A_POS - 1);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT);
        }
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-RESOURCE-CCY", BPCRCCY);
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK", BPCPQBNK);
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_E_INFOMATION = BPCCINTI.BASE_INFO.CCY + BPCCINTI.BASE_INFO.BASE_TYP + BPCCINTI.BASE_INFO.TENOR;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_E_INFOMATION);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void T000_READUP_TDTPMP() throws IOException,SQLException,Exception {
        TDTPMP_RD = new DBParm();
        TDTPMP_RD.TableName = "TDTPMP";
        TDTPMP_RD.where = "IBS_AC_BK = :TDRPMP.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMP.KEY.PRD_CD";
        TDTPMP_RD.upd = true;
        IBS.READ(SCCGWA, TDRPMP, this, TDTPMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PMP_FLG = 'N';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
    }
    public void T000_STARTBR_TDTDOCU() throws IOException,SQLException,Exception {
        TDTDOCU_BR.rp = new DBParm();
        TDTDOCU_BR.rp.TableName = "TDTDOCU";
        TDTDOCU_BR.rp.where = "DOCU_NO = :TDRDOCU.KEY.DOCU_NO";
        TDTDOCU_BR.rp.order = "DOCU_TERM";
        IBS.STARTBR(SCCGWA, TDRDOCU, this, TDTDOCU_BR);
    }
    public void T000_READNEXT_TDTDOCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRDOCU, this, TDTDOCU_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DOCU_FLG = 'Y';
        } else {
            WS_DOCU_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTDOCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTDOCU_BR);
    }
    public void T000_REWRITE_TDTPMP() throws IOException,SQLException,Exception {
        TDTPMP_RD = new DBParm();
        TDTPMP_RD.TableName = "TDTPMP";
        IBS.REWRITE(SCCGWA, TDRPMP, TDTPMP_RD);
    }
    public void T000_WRITE_TDTPMPD() throws IOException,SQLException,Exception {
        TDTPMPD_RD = new DBParm();
        TDTPMPD_RD.TableName = "TDTPMPD";
        IBS.WRITE(SCCGWA, TDRPMPD, TDTPMPD_RD);
    }
    public void T000_STARTBR_TDTPMPD() throws IOException,SQLException,Exception {
        TDTPMPD_BR.rp = new DBParm();
        TDTPMPD_BR.rp.TableName = "TDTPMPD";
        TDTPMPD_BR.rp.where = "IBS_AC_BK = :TDRPMPD.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMPD.KEY.PRD_CD";
        TDTPMPD_BR.rp.upd = true;
        TDTPMPD_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, TDRPMPD, this, TDTPMPD_BR);
    }
    public void T000_READNEXT_TDTPMPD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPMPD, this, TDTPMPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMPD_FLG = 'Y';
        } else {
            WS_PMPD_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTPMPD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPMPD_BR);
    }
    public void T000_DELETE_TDTPMPD() throws IOException,SQLException,Exception {
        TDTPMPD_RD = new DBParm();
        TDTPMPD_RD.TableName = "TDTPMPD";
        TDTPMPD_RD.where = "IBS_AC_BK = :TDRPMPD.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMPD.KEY.PRD_CD";
        IBS.DELETE(SCCGWA, TDRPMPD, this, TDTPMPD_RD);
        WS_PMPD_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMPD_FLG = 'Y';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
