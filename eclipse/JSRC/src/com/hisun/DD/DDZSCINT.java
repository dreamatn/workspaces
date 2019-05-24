package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCINT {
    DBParm DDTACCU_RD;
    brParm DDTACCU_BR = new brParm();
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT1 = "DD120";
    String K_OUTPUT_FMT2 = "DD130";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    int WS_L_NUM = 0;
    int WS_CNT = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    char WS_ACCU_CLS = ' ';
    DDZSCINT_WS_LIST WS_LIST = new DDZSCINT_WS_LIST();
    DDZSCINT_WS_DETAIL WS_DETAIL = new DDZSCINT_WS_DETAIL();
    int WS_O_CNT = 0;
    char WS_ACCU_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRACCU DDRACCU = new DDRACCU();
    CICACCU CICCACCU = new CICACCU();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICQACAC CICQACAC = new CICQACAC();
    double WS_TT_INT = 0;
    SCCGWA SCCGWA;
    DDCSCINT DDCSCINT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCINT DDCSCINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCINT = DDCSCINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCINT.FUNC);
        if (DDCSCINT.FUNC == 'L') {
            B010_LIST_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSCINT.FUNC == 'Q') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSCINT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_LIST_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        R000_CHECK_MST_STUS();
        if (pgmRtn) return;
        B010_10_TRANS_DATA_PRO();
        if (pgmRtn) return;
        B010_50_GET_INT_LIST();
        if (pgmRtn) return;
    }
    public void B010_10_TRANS_DATA_PRO() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        R000_TRANS_PAGE_ROW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ACCU_CLS);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void B010_50_GET_INT_LIST() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTACCU();
        if (pgmRtn) return;
        T000_READNEXT_DDTACCU();
        if (pgmRtn) return;
        while (WS_ACCU_FLG != 'N') {
            WS_L_CNT += 1;
            T000_READNEXT_DDTACCU();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTACCU();
        if (pgmRtn) return;
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            IBS.init(SCCGWA, WS_LIST.WS_PAGE_INF);
            WS_LIST.WS_PAGE_INF.WS_TOTAL_NUM = WS_L_CNT;
            WS_L_NUM = WS_LIST.WS_PAGE_INF.WS_TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((WS_LIST.WS_PAGE_INF.WS_TOTAL_NUM - WS_L_NUM) / WS_P_ROW);
            if (WS_L_NUM == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE = WS_T_PAGE;
                WS_L_ROW = WS_P_ROW;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                CEP.TRC(SCCGWA, WS_L_NUM);
                WS_L_ROW = WS_L_NUM;
                WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE = WS_T_PAGE + 1;
            }
            if (DDCSCINT.PAGE_NUM != 0) {
                if (DDCSCINT.PAGE_NUM >= WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    WS_LIST.WS_PAGE_INF.WS_CURR_PAGE = WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE;
                    WS_LIST.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                    WS_LIST.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    WS_LIST.WS_PAGE_INF.WS_CURR_PAGE = DDCSCINT.PAGE_NUM;
                    WS_LIST.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                    WS_LIST.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
                }
            } else {
                WS_LIST.WS_PAGE_INF.WS_CURR_PAGE = 1;
                if (WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE == 1) {
                    WS_LIST.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                    WS_LIST.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    WS_LIST.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                    WS_LIST.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            WS_P_NUM = WS_LIST.WS_PAGE_INF.WS_CURR_PAGE - 1;
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            WS_END_NUM = WS_LIST.WS_PAGE_INF.WS_CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.WS_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.WS_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            T000_STARTBR_DDTACCU();
            if (pgmRtn) return;
            T000_READNEXT_DDTACCU();
            if (pgmRtn) return;
            for (WS_O_CNT = 1; WS_ACCU_FLG != 'N'; WS_O_CNT += 1) {
                if (WS_O_CNT > WS_STR_NUM 
                    && WS_O_CNT <= WS_END_NUM) {
                    WS_CNT += 1;
                    B010_50_30_OUTPUT_LIST();
                    if (pgmRtn) return;
                }
                T000_READNEXT_DDTACCU();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTACCU();
            if (pgmRtn) return;
        } else {
            WS_LIST.WS_PAGE_INF.WS_TOTAL_NUM = 0;
            WS_LIST.WS_PAGE_INF.WS_TOTAL_PAGE = 0;
            WS_LIST.WS_PAGE_INF.WS_CURR_PAGE = 0;
            WS_LIST.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
            WS_LIST.WS_PAGE_INF.WS_PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_LIST;
        SCCFMT.DATA_LEN = 2247;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B010_50_30_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_CNT-1]);
        WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_AC = DDRACCU.KEY.AC;
        WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_ST_DATE = DDRACCU.KEY.STR_DATE;
        WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_ED_DATE = DDRACCU.KEY.END_DATE;
        WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_POST_DATE = DDRACCU.POST_DATE;
        WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_INT = DDRACCU.INT;
        WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_P_JRNNO = DDRACCU.POST_JRN_NO;
        CEP.TRC(SCCGWA, "LIST INT:");
        CEP.TRC(SCCGWA, DDRACCU.KEY.AC);
        CEP.TRC(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_ST_DATE);
        CEP.TRC(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_ED_DATE);
        CEP.TRC(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_POST_DATE);
        CEP.TRC(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_INT);
        CEP.TRC(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_CNT-1].WS_P_JRNNO);
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B020_15_GET_PAGE2_OUTPUT();
        if (pgmRtn) return;
        B020_30_GET_INT_DETL();
        if (pgmRtn) return;
    }
    public void B020_15_GET_PAGE2_OUTPUT() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        R000_TRANS_PAGE_ROW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ACCU_CLS);
        CEP.TRC(SCCGWA, WS_P_ROW);
        T000_STBR_R_DDTACCU();
        if (pgmRtn) return;
        T000_READNEXT_DDTACCU();
        if (pgmRtn) return;
        while (WS_ACCU_FLG != 'N') {
            WS_L_CNT += 1;
            T000_READNEXT_DDTACCU();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTACCU();
        if (pgmRtn) return;
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            IBS.init(SCCGWA, WS_DETAIL.WS_PAGE_INF2);
            WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_NUM2 = WS_L_CNT;
            WS_L_NUM = WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_NUM2 % WS_P_ROW;
            WS_T_PAGE = (int) ((WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_NUM2 - WS_L_NUM) / WS_P_ROW);
            if (WS_L_NUM == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2 = WS_T_PAGE;
                WS_L_ROW = WS_P_ROW;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                CEP.TRC(SCCGWA, WS_L_NUM);
                WS_L_ROW = WS_L_NUM;
                WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2 = WS_T_PAGE + 1;
            }
            if (DDCSCINT.PAGE_NUM != 0) {
                if (DDCSCINT.PAGE_NUM >= WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2) {
                    CEP.TRC(SCCGWA, ">>>===");
                    WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2 = WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2;
                    WS_DETAIL.WS_PAGE_INF2.WS_LAST_PAGE2 = 'Y';
                    WS_DETAIL.WS_PAGE_INF2.WS_PAGE_ROW2 = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2 = DDCSCINT.PAGE_NUM;
                    WS_DETAIL.WS_PAGE_INF2.WS_LAST_PAGE2 = 'N';
                    WS_DETAIL.WS_PAGE_INF2.WS_PAGE_ROW2 = WS_P_ROW;
                }
            } else {
                WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2 = 1;
                if (WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2 == 1) {
                    WS_DETAIL.WS_PAGE_INF2.WS_LAST_PAGE2 = 'Y';
                    WS_DETAIL.WS_PAGE_INF2.WS_PAGE_ROW2 = WS_L_ROW;
                } else {
                    WS_DETAIL.WS_PAGE_INF2.WS_LAST_PAGE2 = 'N';
                    WS_DETAIL.WS_PAGE_INF2.WS_PAGE_ROW2 = WS_P_ROW;
                }
            }
            WS_P_NUM = WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2 - 1;
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            WS_END_NUM = WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2 * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE2 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_NUM2);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_PAGE_INF2.WS_PAGE_ROW2);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_PAGE_INF2.WS_LAST_PAGE2);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
        } else {
            WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_NUM2 = 0;
            WS_DETAIL.WS_PAGE_INF2.WS_TOTAL_PAGE2 = 0;
            WS_DETAIL.WS_PAGE_INF2.WS_CURR_PAGE2 = 0;
            WS_DETAIL.WS_PAGE_INF2.WS_LAST_PAGE2 = 'Y';
            WS_DETAIL.WS_PAGE_INF2.WS_PAGE_ROW2 = 0;
        }
    }
    public void B020_30_GET_INT_DETL() throws IOException,SQLException,Exception {
        T000_STBR_DDTACCU_FIRST();
        if (pgmRtn) return;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_AC = DDRACCU.KEY.AC;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_TOT_INT = DDRACCU.INT;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_TAX_RATE = DDRACCU.TAX_RATE;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_TAX = DDRACCU.TAX;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_P_JRNNO = DDRACCU.POST_JRN_NO;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_P_DATE = DDRACCU.POST_DATE;
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_PAID_INT = DDRACCU.INT;
        T000_READ_MST_PROC();
        if (pgmRtn) return;
        R000_GET_AC_NAME();
        if (pgmRtn) return;
        R000_GET_AC_BAL();
        if (pgmRtn) return;
        B020_70_GET_CHG_RECORD();
        if (pgmRtn) return;
    }
    public void B020_70_GET_CHG_RECORD() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 1; WS_CNT += 1) {
            B020_70_30_OUTPUT_RECD();
            if (pgmRtn) return;
            WS_CNT += 1;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT2;
        SCCFMT.DATA_PTR = WS_DETAIL;
        SCCFMT.DATA_LEN = 12100;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_70_30_OUTPUT_RECD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1]);
        WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_ST_DATE = DDRACCU.KEY.STR_DATE;
        WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_ED_DATE = DDRACCU.KEY.END_DATE;
        WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_RATE = DDRACCU.RATE;
        WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_TOT = DDRACCU.TOT;
        WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_INT = DDRACCU.INT;
        CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_ST_DATE);
        CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_ED_DATE);
        CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_RATE);
        CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_TOT);
        CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_RECORD[WS_CNT-1].WS_R_INT);
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCINT.AC_NO);
        CEP.TRC(SCCGWA, DDCSCINT.CCY);
        CEP.TRC(SCCGWA, DDCSCINT.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCINT.STR_DATE);
        CEP.TRC(SCCGWA, DDCSCINT.END_DATE);
        CEP.TRC(SCCGWA, DDCSCINT.PAGE_ROW);
        CEP.TRC(SCCGWA, DDCSCINT.PAGE_NUM);
        if (DDCSCINT.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.CCY_TYPE != '1' 
            && DDCSCINT.CCY_TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.STR_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FROM_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.END_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.STR_DATE > SCCGWA.COMM_AREA.TR_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DATE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.END_DATE < DDCSCINT.STR_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DATE_LT_FROM_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINT.STR_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSCINT.STR_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSCINT.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSCINT.END_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_CHK_OUTPUT_AC();
        if (pgmRtn) return;
    }
    public void R000_CHK_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSCINT.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCSCINT.CCY;
        CICQACAC.DATA.CR_FLG = DDCSCINT.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_MST_STUS() throws IOException,SQLException,Exception {
        T000_READ_MST_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_PAGE_ROW() throws IOException,SQLException,Exception {
        if (DDCSCINT.PAGE_ROW == 0) {
            WS_P_ROW = 25;
        } else {
            if (DDCSCINT.PAGE_ROW > 25) {
                WS_P_ROW = 25;
            } else {
                WS_P_ROW = DDCSCINT.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void R000_OUTPUT_PAGE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LIST.WS_PAGE_INF);
        SCCMPAG.DATA_LEN = 22;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_PAGE2_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DETAIL.WS_PAGE_INF2);
        SCCMPAG.DATA_LEN = 550;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCACCU);
        CICCACCU.DATA.AGR_NO = DDCSCINT.AC_NO;
        CICCACCU.DATA.ENTY_TYP = '1';
        CEP.TRC(SCCGWA, CICCACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICCACCU.DATA.AC_CNM.trim().length() == 0) {
            WS_DETAIL.WS_OUTPUT_DTL.WS_D_AC_NM = CICCACCU.DATA.CI_CNM;
        } else {
            WS_DETAIL.WS_OUTPUT_DTL.WS_D_AC_NM = CICCACCU.DATA.AC_CNM;
        }
        CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_DTL.WS_D_AC_NM);
    }
    public void R000_GROUP_ACCU_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = DDCSCINT.AC_NO;
        DDRACCU.KEY.CLS = 'P';
        DDRACCU.INT_STS = 'Y';
        DDTACCU_RD = new DBParm();
        DDTACCU_RD.TableName = "DDTACCU";
        DDTACCU_RD.set = "WS-TT-INT=IFNULL(SUM(INT),0)";
        DDTACCU_RD.where = "AC = :DDRACCU.KEY.AC "
            + "AND CLS = :DDRACCU.KEY.CLS "
            + "AND INT_STS = :DDRACCU.INT_STS";
        IBS.GROUP(SCCGWA, DDRACCU, this, DDTACCU_RD);
        WS_DETAIL.WS_OUTPUT_DTL.WS_D_PAID_INT = WS_TT_INT;
        CEP.TRC(SCCGWA, WS_TT_INT);
    }
    public void R000_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUIBAL);
        BPCUIBAL.INPUT.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCUIBAL.INPUT.JRNNO = DDRACCU.POST_JRN_NO;
        BPCUIBAL.INPUT.JRN_SEQ = 1;
        S000_CALL_BPZUIBAL();
        if (pgmRtn) return;
        if (BPCUIBAL.OUTPUT.AC.equalsIgnoreCase(DDCSCINT.AC_NO)) {
            WS_DETAIL.WS_OUTPUT_DTL.WS_D_BAL = BPCUIBAL.OUTPUT.BAL_E;
            CEP.TRC(SCCGWA, "= SCINT-AC-NO");
            CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_DTL.WS_D_BAL);
        } else {
            IBS.init(SCCGWA, BPCUIBAL);
            BPCUIBAL.INPUT.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCUIBAL.INPUT.JRNNO = DDRACCU.POST_JRN_NO;
            BPCUIBAL.INPUT.JRN_SEQ = 2;
            S000_CALL_BPZUIBAL();
            if (pgmRtn) return;
            WS_DETAIL.WS_OUTPUT_DTL.WS_D_BAL = BPCUIBAL.OUTPUT.BAL_E;
            CEP.TRC(SCCGWA, "NOT = SCINT-AC-NO");
            CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
            CEP.TRC(SCCGWA, WS_DETAIL.WS_OUTPUT_DTL.WS_D_BAL);
        }
    }
    public void T000_STARTBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.KEY.STR_DATE = DDCSCINT.STR_DATE;
        DDRACCU.KEY.END_DATE = DDCSCINT.END_DATE;
        WS_ACCU_FLG = 'N';
        CEP.TRC(SCCGWA, DDRACCU.KEY.AC);
        CEP.TRC(SCCGWA, DDRACCU.KEY.CLS);
        CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
        CEP.TRC(SCCGWA, DDRACCU.KEY.END_DATE);
        DDTACCU_BR.rp = new DBParm();
        DDTACCU_BR.rp.TableName = "DDTACCU";
        DDTACCU_BR.rp.where = "AC = :DDRACCU.KEY.AC "
            + "AND INT_STS = 'Y' "
            + "AND STR_DATE >= :DDRACCU.KEY.STR_DATE "
            + "AND STR_DATE <= :DDRACCU.KEY.END_DATE";
        DDTACCU_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, DDRACCU, this, DDTACCU_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STBR_R_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.KEY.STR_DATE = DDCSCINT.STR_DATE;
        DDRACCU.KEY.END_DATE = DDCSCINT.END_DATE;
        WS_ACCU_FLG = 'N';
        CEP.TRC(SCCGWA, DDRACCU.KEY.AC);
        CEP.TRC(SCCGWA, DDRACCU.KEY.CLS);
        CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
        CEP.TRC(SCCGWA, DDRACCU.KEY.END_DATE);
        DDTACCU_BR.rp = new DBParm();
        DDTACCU_BR.rp.TableName = "DDTACCU";
        DDTACCU_BR.rp.where = "AC = :DDRACCU.KEY.AC "
            + "AND STR_DATE >= :DDRACCU.KEY.STR_DATE "
            + "AND END_DATE <= :DDRACCU.KEY.END_DATE";
        DDTACCU_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, DDRACCU, this, DDTACCU_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STBR_DDTACCU_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.KEY.STR_DATE = DDCSCINT.STR_DATE;
        DDRACCU.KEY.END_DATE = DDCSCINT.END_DATE;
        CEP.TRC(SCCGWA, DDRACCU.KEY.AC);
        CEP.TRC(SCCGWA, DDRACCU.KEY.CLS);
        CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
        CEP.TRC(SCCGWA, DDRACCU.KEY.END_DATE);
        DDTACCU_RD = new DBParm();
        DDTACCU_RD.TableName = "DDTACCU";
        DDTACCU_RD.where = "AC = :DDRACCU.KEY.AC "
            + "AND INT_STS = 'Y' "
            + "AND STR_DATE = :DDRACCU.KEY.STR_DATE "
            + "AND END_DATE = :DDRACCU.KEY.END_DATE";
        DDTACCU_RD.fst = true;
        IBS.READ(SCCGWA, DDRACCU, this, DDTACCU_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRACCU.INT);
        CEP.TRC(SCCGWA, DDRACCU.TAX_RATE);
        CEP.TRC(SCCGWA, DDRACCU.TAX);
        CEP.TRC(SCCGWA, DDRACCU.POST_JRN_NO);
        CEP.TRC(SCCGWA, DDRACCU.POST_DATE);
        CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
        CEP.TRC(SCCGWA, DDRACCU.KEY.END_DATE);
        CEP.TRC(SCCGWA, DDRACCU.RATE);
        CEP.TRC(SCCGWA, DDRACCU.TOT);
        CEP.TRC(SCCGWA, DDRACCU.INT);
    }
    public void T000_READNEXT_DDTACCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRACCU, this, DDTACCU_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACCU_FLG = 'Y';
        } else {
            WS_ACCU_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTACCU_BR);
    }
    public void T000_READ_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCINT.AC_NO;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,AC_TYPE";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICCACCU);
    }
    public void S000_CALL_BPZUIBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-BAL", BPCUIBAL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
