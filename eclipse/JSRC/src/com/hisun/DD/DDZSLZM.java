package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSLZM {
    DBParm DDTCCZM_RD;
    brParm DDTCCZM_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    brParm DDTZMAC_BR = new brParm();
    DBParm DDTZMAC_RD;
    String K_OUTPUT_FMT_A = "DD154";
    String WS_Q_CI_NO = " ";
    int WS_Q_OPEN_DT = 0;
    String WS_Q_OPEN_BV = " ";
    String WS_REF_NO = " ";
    int WS_TR_BRANCH = 0;
    int WS_Q_START_DT = 0;
    int WS_Q_END_DT = 0;
    String WS_Q_BV_CODE = " ";
    char WS_STSW = ' ';
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    short WS_NUM1 = 0;
    int WS_L_CNT = 0;
    String WS_REF_NO1 = " ";
    char WS_FLAG = ' ';
    char WS_DDTZMAC_REC = ' ';
    int WS_P_ROW = 0;
    int WS_L_ROW = 0;
    int WS_T_PAGE = 0;
    int WS_P_NUM = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    DDZSLZM_WS_FMT WS_FMT = new DDZSLZM_WS_FMT();
    char WS_DDTCCZM_FLAG = ' ';
    char WS_REF_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DDRCCZM DDRCCZM = new DDRCCZM();
    DDRZMAC DDRZMAC = new DDRZMAC();
    DCCURHLD DCCURHLD = new DCCURHLD();
    SCCGWA SCCGWA;
    DDCSLZM DDCSLZM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSLZM DDCSLZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLZM = DDCSLZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSLZM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCSLZM.INPUT_DATA.IZM_STS == 'Q') {
            CEP.TRC(SCCGWA, "---CANCEL CCZM INQ---");
            B300_GET_CCZM_INF_PROC();
        } else {
            CEP.TRC(SCCGWA, "---INQ CCZM INQ---");
            B400_GET_CCZM_INF_PROC();
        }
    }
    public void B300_GET_CCZM_INF_PROC() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        R000_TRANS_PAGE_ROW();
        CEP.TRC(SCCGWA, WS_P_ROW);
        IBS.init(SCCGWA, DDRCCZM);
        WS_Q_CI_NO = DDCSLZM.INPUT_DATA.IZM_CI_NO;
        WS_Q_OPEN_DT = DDCSLZM.INPUT_DATA.IZM_OPEN_DT;
        WS_Q_OPEN_BV = DDCSLZM.INPUT_DATA.IZM_OPEN_BV;
        if (DDCSLZM.INPUT_DATA.IZM_START_DT != 0) {
            WS_Q_START_DT = DDCSLZM.INPUT_DATA.IZM_START_DT;
        }
        if (DDCSLZM.INPUT_DATA.IZM_END_DT != 0) {
            WS_Q_END_DT = DDCSLZM.INPUT_DATA.IZM_END_DT;
        }
        if (WS_Q_OPEN_BV.trim().length() > 0) {
            T000_READ_DDTCCZM();
            CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
            WS_REF_NO = DDRCCZM.KEY.REF_NO;
            CEP.TRC(SCCGWA, WS_REF_NO);
            if (WS_Q_OPEN_DT != 0 
                || WS_Q_END_DT != 0) {
                S000_STARTBR_DDTCCZM_BV_DT();
            } else {
                S000_STARTBR_DDTCCZM_BV();
            }
        } else {
            if (WS_Q_CI_NO.trim().length() > 0) {
                if (WS_Q_OPEN_DT != 0 
                    || WS_Q_END_DT != 0) {
                    S000_STARTBR_DDTCCZM_CI_DT();
                } else {
                    S000_STARTBR_DDTCCZM();
                }
            } else {
                CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_STS);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                if (WS_Q_OPEN_DT != 0 
                    || WS_Q_END_DT != 0) {
                    S000_STARTBR_DDTCCZM_ORG_DT();
                } else {
                    S000_STARTBR_DDTCCZM_ORG();
                }
            }
        }
        T000_READNEXT_DDTCCZM();
        WS_REF_REC_FLG = 'D';
        while (WS_DDTCCZM_FLAG != 'N') {
            CEP.TRC(SCCGWA, "FIND BUG4");
            CEP.TRC(SCCGWA, WS_REF_REC_FLG);
            WS_REF_NO1 = DDRCCZM.KEY.REF_NO;
            if (WS_REF_REC_FLG == 'D') {
                if (DDRCCZM.STS == 'N' 
                    || DDRCCZM.STS == 'R') {
                    WS_L_CNT += 1;
                }
                CEP.TRC(SCCGWA, WS_L_CNT);
            }
            T000_READNEXT_DDTCCZM();
            if (!DDRCCZM.KEY.REF_NO.equalsIgnoreCase(WS_REF_NO1)) {
                WS_REF_REC_FLG = 'D';
            } else {
                WS_REF_REC_FLG = 'S';
            }
        }
        CEP.TRC(SCCGWA, WS_L_CNT);
        T000_ENDBR_DDTCCZM();
        CEP.TRC(SCCGWA, "FIND BUG3");
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (WS_L_CNT != 0) {
            WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = WS_T_PAGE;
                WS_L_ROW = WS_P_ROW;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = WS_T_PAGE + 1;
                CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            }
            CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_PAGE_NUM);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM == 0) {
                DDCSLZM.INPUT_DATA.IZM_PAGE_NUM += 1;
                CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_PAGE_NUM);
            }
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM != 0) {
                if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM >= WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE;
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = DDCSLZM.INPUT_DATA.IZM_PAGE_NUM;
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM == 0) {
                WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = 1;
                if (WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE == 1) {
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            WS_P_NUM = WS_FMT.WS_PAGE_INF.WS_CURR_PAGE - 1;
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            WS_END_NUM = WS_FMT.WS_PAGE_INF.WS_CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            IBS.init(SCCGWA, DDRCCZM);
            WS_Q_CI_NO = DDCSLZM.INPUT_DATA.IZM_CI_NO;
            CEP.TRC(SCCGWA, WS_Q_CI_NO);
            CEP.TRC(SCCGWA, WS_Q_OPEN_BV);
            if (WS_Q_OPEN_BV.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_Q_OPEN_DT);
                if (WS_Q_OPEN_DT != 0 
                    || WS_Q_END_DT != 0) {
                    T000_STARTBR_DDTCCZM_BV_DT1();
                } else {
                    T000_STARTBR_DDTCCZM_BV1();
                }
            } else {
                if (WS_Q_CI_NO.trim().length() > 0) {
                    if (WS_Q_OPEN_DT != 0 
                        || WS_Q_END_DT != 0) {
                        T000_STARTBR_DDTCCZM_CI_DT1();
                    } else {
                        T000_STARTBR_DDTCCZM1();
                    }
                } else {
                    CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_STS);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                    if (WS_Q_OPEN_DT != 0 
                        || WS_Q_END_DT != 0) {
                        S000_STARTBR_DDTCCZM_ORG_DT1();
                    } else {
                        T000_STARTBR_DDTCCZM_ORG1();
                    }
                }
            }
            T000_READNEXT_DDTCCZM();
            WS_REF_REC_FLG = 'D';
            while (WS_DDTCCZM_FLAG != 'N') {
                CEP.TRC(SCCGWA, WS_NUM1);
                CEP.TRC(SCCGWA, WS_STR_NUM);
                CEP.TRC(SCCGWA, WS_END_NUM);
                CEP.TRC(SCCGWA, WS_REF_REC_FLG);
                WS_REF_NO1 = DDRCCZM.KEY.REF_NO;
                if (WS_REF_REC_FLG == 'D') {
                    WS_NUM1 = (short) (WS_NUM1 + 1);
                    if (WS_NUM1 > WS_STR_NUM 
                        && WS_NUM1 <= WS_END_NUM) {
                        CEP.TRC(SCCGWA, "FIND BUG1");
                        CEP.TRC(SCCGWA, WS_CNT);
                        WS_CNT = WS_CNT + 1;
                        B400_10_10_OUTPUT_LIST();
                    }
                }
                T000_READNEXT_DDTCCZM();
                if (!DDRCCZM.KEY.REF_NO.equalsIgnoreCase(WS_REF_NO1)) {
                    WS_REF_REC_FLG = 'D';
                } else {
                    WS_REF_REC_FLG = 'S';
                }
            }
            T000_ENDBR_DDTCCZM();
        } else {
            WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM = 0;
            WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = 0;
            WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = 0;
            WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
            WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 8992;
        CEP.TRC(SCCGWA, "FIND BUG2");
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B400_GET_CCZM_INF_PROC() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        R000_TRANS_PAGE_ROW();
        CEP.TRC(SCCGWA, WS_P_ROW);
        IBS.init(SCCGWA, DDRCCZM);
        WS_Q_CI_NO = DDCSLZM.INPUT_DATA.IZM_CI_NO;
        WS_Q_OPEN_DT = DDCSLZM.INPUT_DATA.IZM_OPEN_DT;
        WS_Q_OPEN_BV = DDCSLZM.INPUT_DATA.IZM_OPEN_BV;
        if (DDCSLZM.INPUT_DATA.IZM_START_DT != 0) {
            WS_Q_START_DT = DDCSLZM.INPUT_DATA.IZM_START_DT;
        }
        if (DDCSLZM.INPUT_DATA.IZM_END_DT != 0) {
            WS_Q_END_DT = DDCSLZM.INPUT_DATA.IZM_END_DT;
        }
        CEP.TRC(SCCGWA, WS_Q_CI_NO);
        CEP.TRC(SCCGWA, WS_Q_OPEN_DT);
        CEP.TRC(SCCGWA, WS_Q_OPEN_BV);
        CEP.TRC(SCCGWA, WS_Q_START_DT);
        CEP.TRC(SCCGWA, WS_Q_END_DT);
        if (WS_Q_OPEN_BV.trim().length() > 0) {
            T000_READ_DDTCCZM();
            CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
            WS_REF_NO = DDRCCZM.KEY.REF_NO;
            CEP.TRC(SCCGWA, WS_REF_NO);
            if (WS_Q_OPEN_DT != 0 
                || WS_Q_END_DT != 0) {
                S000_STARTBR_DDTCCZM_BV_DT();
            } else {
                S000_STARTBR_DDTCCZM_BV();
            }
        } else {
            if (WS_Q_CI_NO.trim().length() > 0) {
                if (WS_Q_OPEN_DT != 0 
                    || WS_Q_END_DT != 0) {
                    S000_STARTBR_DDTCCZM_CI_DT();
                } else {
                    S000_STARTBR_DDTCCZM();
                }
            } else {
                CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_STS);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                if (WS_Q_OPEN_DT != 0 
                    || WS_Q_END_DT != 0) {
                    S000_STARTBR_DDTCCZM_ORG_DT();
                } else {
                    S000_STARTBR_DDTCCZM_ORG();
                }
            }
        }
        T000_READNEXT_DDTCCZM();
        WS_REF_REC_FLG = 'D';
        while (WS_DDTCCZM_FLAG != 'N') {
            CEP.TRC(SCCGWA, "FIND BUG4");
            CEP.TRC(SCCGWA, WS_REF_REC_FLG);
            WS_REF_NO1 = DDRCCZM.KEY.REF_NO;
            if ((DDRCCZM.BAL_TYPE == '2' 
                && SCCGWA.COMM_AREA.AC_DATE > DDRCCZM.BAL_DATE)) {
                S000_REWRITE_DDTCCZM();
                S000_UPDATE_DDTZMAC();
            }
            if (WS_REF_REC_FLG == 'D') {
                if (DDRCCZM.STS == DDCSLZM.INPUT_DATA.IZM_STS 
                    || DDCSLZM.INPUT_DATA.IZM_STS == ' ') {
                    WS_L_CNT += 1;
                }
                CEP.TRC(SCCGWA, WS_L_CNT);
            }
            T000_READNEXT_DDTCCZM();
            if (!DDRCCZM.KEY.REF_NO.equalsIgnoreCase(WS_REF_NO1)) {
                WS_REF_REC_FLG = 'D';
            } else {
                WS_REF_REC_FLG = 'S';
            }
        }
        CEP.TRC(SCCGWA, WS_L_CNT);
        T000_ENDBR_DDTCCZM();
        CEP.TRC(SCCGWA, "FIND BUG3");
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (WS_L_CNT != 0) {
            WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = WS_T_PAGE;
                WS_L_ROW = WS_P_ROW;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = WS_T_PAGE + 1;
                CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            }
            CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_PAGE_NUM);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM == 0) {
                DDCSLZM.INPUT_DATA.IZM_PAGE_NUM += 1;
                CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_PAGE_NUM);
            }
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM != 0) {
                if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM >= WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE;
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = DDCSLZM.INPUT_DATA.IZM_PAGE_NUM;
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_NUM == 0) {
                WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = 1;
                if (WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE == 1) {
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                    WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            WS_P_NUM = WS_FMT.WS_PAGE_INF.WS_CURR_PAGE - 1;
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            WS_END_NUM = WS_FMT.WS_PAGE_INF.WS_CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_INF.WS_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            IBS.init(SCCGWA, DDRCCZM);
            WS_Q_CI_NO = DDCSLZM.INPUT_DATA.IZM_CI_NO;
            CEP.TRC(SCCGWA, WS_Q_CI_NO);
            CEP.TRC(SCCGWA, WS_Q_OPEN_BV);
            if (WS_Q_OPEN_BV.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_Q_OPEN_DT);
                if (WS_Q_OPEN_DT != 0 
                    || WS_Q_END_DT != 0) {
                    T000_STARTBR_DDTCCZM_BV_DT();
                } else {
                    T000_STARTBR_DDTCCZM_BV();
                }
            } else {
                if (WS_Q_CI_NO.trim().length() > 0) {
                    if (WS_Q_OPEN_DT != 0 
                        || WS_Q_END_DT != 0) {
                        T000_STARTBR_DDTCCZM_CI_DT();
                    } else {
                        T000_STARTBR_DDTCCZM();
                    }
                } else {
                    CEP.TRC(SCCGWA, DDCSLZM.INPUT_DATA.IZM_STS);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                    if (WS_Q_OPEN_DT != 0 
                        || WS_Q_END_DT != 0) {
                        S000_STARTBR_DDTCCZM_ORG_DT();
                    } else {
                        T000_STARTBR_DDTCCZM_ORG();
                    }
                }
            }
            T000_READNEXT_DDTCCZM();
            WS_REF_REC_FLG = 'D';
            while (WS_DDTCCZM_FLAG != 'N') {
                CEP.TRC(SCCGWA, WS_NUM1);
                CEP.TRC(SCCGWA, WS_STR_NUM);
                CEP.TRC(SCCGWA, WS_END_NUM);
                CEP.TRC(SCCGWA, WS_REF_REC_FLG);
                WS_REF_NO1 = DDRCCZM.KEY.REF_NO;
                if (WS_REF_REC_FLG == 'D') {
                    WS_NUM1 = (short) (WS_NUM1 + 1);
                    if (WS_NUM1 > WS_STR_NUM 
                        && WS_NUM1 <= WS_END_NUM) {
                        CEP.TRC(SCCGWA, "FIND BUG1");
                        CEP.TRC(SCCGWA, WS_CNT);
                        WS_CNT = WS_CNT + 1;
                        B400_10_10_OUTPUT_LIST();
                    }
                }
                T000_READNEXT_DDTCCZM();
                if (!DDRCCZM.KEY.REF_NO.equalsIgnoreCase(WS_REF_NO1)) {
                    WS_REF_REC_FLG = 'D';
                } else {
                    WS_REF_REC_FLG = 'S';
                }
            }
            T000_ENDBR_DDTCCZM();
        } else {
            WS_FMT.WS_PAGE_INF.WS_TOTAL_NUM = 0;
            WS_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = 0;
            WS_FMT.WS_PAGE_INF.WS_CURR_PAGE = 0;
            WS_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
            WS_FMT.WS_PAGE_INF.WS_PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 8992;
        CEP.TRC(SCCGWA, "FIND BUG2");
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B400_10_10_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1]);
        WS_FMT.WS_DATA[WS_CNT-1].WS_BV_NO = DDRCCZM.KEY.OPEN_BV;
        WS_FMT.WS_DATA[WS_CNT-1].WS_OPEN_DT = DDRCCZM.CRT_DATE;
        WS_FMT.WS_DATA[WS_CNT-1].WS_CI_NO = DDRCCZM.CI_NO;
        WS_FMT.WS_DATA[WS_CNT-1].WS_OPEN_CNT = DDRCCZM.OPEN_CNT;
        WS_FMT.WS_DATA[WS_CNT-1].WS_CH_TLE = DDRCCZM.CH_TLE;
        WS_FMT.WS_DATA[WS_CNT-1].WS_EN_TLE = DDRCCZM.EN_TLE;
        WS_FMT.WS_DATA[WS_CNT-1].WS_EN_NAME = DDRCCZM.EN_NAME;
        WS_FMT.WS_DATA[WS_CNT-1].WS_BAL_TYPE = DDRCCZM.BAL_TYPE;
        WS_FMT.WS_DATA[WS_CNT-1].WS_BAL_DATE = DDRCCZM.BAL_DATE;
        WS_FMT.WS_DATA[WS_CNT-1].WS_BAL_EXPD = DDRCCZM.BAL_EXPD;
        WS_FMT.WS_DATA[WS_CNT-1].WS_TS = DDRCCZM.TS;
        WS_FMT.WS_DATA[WS_CNT-1].WS_CCZM_OPEN_BV = DDRCCZM.KEY.OPEN_BV_CODE;
        WS_FMT.WS_DATA[WS_CNT-1].WS_CCZM_REF_NO = DDRCCZM.KEY.REF_NO;
        WS_FMT.WS_DATA[WS_CNT-1].WS_CCZM_TOTAL_OPEN_AMT = DDRCCZM.TOTAL_OPEN_AMT;
        WS_FMT.WS_DATA[WS_CNT-1].WS_OPEN_BR = DDRCCZM.BANK_NO;
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_BV_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_OPEN_DT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_CI_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_OPEN_CNT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_CH_TLE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_EN_TLE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_EN_NAME);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_BAL_TYPE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_BAL_DATE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_BAL_EXPD);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_TS);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_CCZM_OPEN_BV);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_CCZM_REF_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_CCZM_TOTAL_OPEN_AMT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_CNT-1].WS_OPEN_BR);
    }
    public void R000_TRANS_PAGE_ROW() throws IOException,SQLException,Exception {
        if (DDCSLZM.INPUT_DATA.IZM_PAGE_ROW == 0) {
            WS_P_ROW = 10;
        } else {
            if (DDCSLZM.INPUT_DATA.IZM_PAGE_ROW > 10) {
                WS_P_ROW = 10;
            } else {
                WS_P_ROW = DDCSLZM.INPUT_DATA.IZM_PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void T000_READ_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.where = "OPEN_BV_CODE = 'C00008' "
            + "AND OPEN_BV = :WS_Q_OPEN_BV";
        IBS.READ(SCCGWA, DDRCCZM, this, DDTCCZM_RD);
    }
    public void T000_STARTBR_DDTCCZM1() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "CI_NO = :WS_Q_CI_NO "
            + "AND ( STS = 'N' "
            + "OR STS = 'R' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "CI_NO = :WS_Q_CI_NO "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_CI_DT() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "CI_NO = :WS_Q_CI_NO "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) ) "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_CI_DT1() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "CI_NO = :WS_Q_CI_NO "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) ) "
            + "AND ( STS = 'N' "
            + "OR STS = 'R' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_BV() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = 'C00008' "
            + "AND REF_NO = :WS_REF_NO "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_BV1() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = 'C00008' "
            + "AND REF_NO = :WS_REF_NO "
            + "AND ( STS = 'N' "
            + "OR STS = 'R' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_BV_DT() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = 'C00008' "
            + "AND REF_NO = :WS_REF_NO "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) ) "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_BV_DT1() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = 'C00008' "
            + "AND REF_NO = :WS_REF_NO "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) ) "
            + "AND ( STS = 'N' "
            + "OR STS = 'R' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "CI_NO = :WS_Q_CI_NO";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM_CI_DT() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "CI_NO = :WS_Q_CI_NO "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) )";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM_BV() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = 'C00008' "
            + "AND REF_NO = :WS_REF_NO";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM_BV_DT() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = 'C00008' "
            + "AND REF_NO = :WS_REF_NO "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) )";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM_ORG() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0111540")) {
            WS_Q_BV_CODE = "C00008";
        }
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        CEP.TRC(SCCGWA, WS_Q_BV_CODE);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = :WS_Q_BV_CODE "
            + "AND BANK_NO = :WS_TR_BRANCH "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM_ORG_DT() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        WS_Q_BV_CODE = "C00008";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0111540")) {
            WS_STSW = '0';
        } else {
            WS_STSW = '1';
        }
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        CEP.TRC(SCCGWA, WS_Q_BV_CODE);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = :WS_Q_BV_CODE "
            + "AND BANK_NO = :WS_TR_BRANCH "
            + "AND SUBSTR ( STSW , 1 , 1 ) = :WS_STSW "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) ) "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void S000_STARTBR_DDTCCZM_ORG_DT1() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        WS_Q_BV_CODE = "C00008";
        WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        CEP.TRC(SCCGWA, WS_Q_BV_CODE);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = :WS_Q_BV_CODE "
            + "AND BANK_NO = :WS_TR_BRANCH "
            + "AND ( CRT_DATE = :WS_Q_OPEN_DT "
            + "OR ( CRT_DATE >= :WS_Q_START_DT "
            + "AND CRT_DATE <= :WS_Q_END_DT ) ) "
            + "AND ( STS = 'N' "
            + "OR STS = 'R' )";
        DDTCCZM_BR.rp.upd = true;
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_ORG() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0111540")) {
            WS_Q_BV_CODE = "C00008";
        }
        DDRCCZM.STS = DDCSLZM.INPUT_DATA.IZM_STS;
        WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        CEP.TRC(SCCGWA, WS_Q_BV_CODE);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = :WS_Q_BV_CODE "
            + "AND BANK_NO = :WS_TR_BRANCH "
            + "AND ( STS = :DDRCCZM.STS "
            + "OR :DDRCCZM.STS = ' ' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DDTCCZM_ORG1() throws IOException,SQLException,Exception {
        WS_DDTCCZM_FLAG = 'N';
        DDRCCZM.BAL_EXPD = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0111540")) {
            WS_Q_BV_CODE = "C00008";
        }
        WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DDRCCZM.STS);
        CEP.TRC(SCCGWA, WS_Q_BV_CODE);
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "OPEN_BV_CODE = :WS_Q_BV_CODE "
            + "AND BANK_NO = :WS_TR_BRANCH "
            + "AND ( STS = 'N' "
            + "OR STS = 'R' )";
        DDTCCZM_BR.rp.order = "CRT_DATE, OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_READNEXT_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_FLAG = 'Y';
        } else {
            WS_DDTCCZM_FLAG = 'N';
        }
    }
    public void T000_ENDBR_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCZM_BR);
    }
    public void S000_REWRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDRCCZM.STS = 'C';
        DDRCCZM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.REWRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
    }
    public void S000_UPDATE_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMAC);
        DDRZMAC.KEY.REF_NO = DDRCCZM.KEY.REF_NO;
        T000_STARTBR_DDTZMAC_UPD();
        WS_FLAG = ' ';
        while (WS_FLAG != 'N') {
            T000_READNEXT_DDTZMAC();
            if (WS_DDTZMAC_REC == 'Y') {
                DDRZMAC.HLD_STS = 'C';
                S000_REWRITE_DDTZMAC();
            } else {
                WS_FLAG = 'N';
            }
        }
        T000_ENDBR_DDTZMAC();
    }
    public void S000_RHLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = DDRZMAC.HLD_NO;
        DCCURHLD.DATA.AC = DDRZMAC.AC;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.HLD_TYP = '2';
        DCCURHLD.DATA.SPR_TYP = '2';
        DCCURHLD.DATA.RAMT = DDRZMAC.OPEN_AMT;
        DCCURHLD.DATA.RSN = "���֤���ⶳ";
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void T000_STARTBR_DDTZMAC_UPD() throws IOException,SQLException,Exception {
        DDTZMAC_BR.rp = new DBParm();
        DDTZMAC_BR.rp.TableName = "DDTZMAC";
        DDTZMAC_BR.rp.where = "REF_NO = :DDRCCZM.KEY.REF_NO";
        DDTZMAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTZMAC_BR);
    }
    public void S000_REWRITE_DDTZMAC() throws IOException,SQLException,Exception {
        DDRZMAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRZMAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        IBS.REWRITE(SCCGWA, DDRZMAC, DDTZMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
