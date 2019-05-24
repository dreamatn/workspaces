package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1147 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP061";
    String CPN_FFAV_MAINTAIN = "BP-F-S-MAINTAIN-FFAV";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    String WS_TXT = " ";
    double WS_AMT = 0;
    short WS_CNT = 0;
    short WS_INPT_CNT = 0;
    short WS_J = 0;
    short WS_I = 0;
    String WS_FAV_TYP = " ";
    char WS_COLL_FLG = ' ';
    char WS_FAV_KND = ' ';
    char WS_CAL_MTH = ' ';
    char WS_CAL_CYC = ' ';
    char WS_COL_TYPE = ' ';
    char WS_ARG_SPL = ' ';
    char WS_ADJ_TYP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFFAV BPCOFFAV = new BPCOFFAV();
    CICMCGRP CICMCGRP = new CICMCGRP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB1147_AWA_1147 BPB1147_AWA_1147;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1147 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1147_AWA_1147>");
        BPB1147_AWA_1147 = (BPB1147_AWA_1147) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPDATE_FAVINF_PARM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B010_01_CHECK_BAS_INFO();
        B010_02_CHECK_FAV_INFO();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_03_CHECK_DIF_CN();
        }
    }
    public void B010_01_CHECK_BAS_INFO() throws IOException,SQLException,Exception {
        WS_FAV_TYP = BPB1147_AWA_1147.FAV_TYP;
        CEP.TRC(SCCGWA, WS_FAV_TYP);
        if ((!WS_FAV_TYP.equalsIgnoreCase("11") 
            && !WS_FAV_TYP.equalsIgnoreCase("12") 
            && !WS_FAV_TYP.equalsIgnoreCase("13") 
            && !WS_FAV_TYP.equalsIgnoreCase("14") 
            && !WS_FAV_TYP.equalsIgnoreCase("15") 
            && !WS_FAV_TYP.equalsIgnoreCase("21") 
            && !WS_FAV_TYP.equalsIgnoreCase("31") 
            && !WS_FAV_TYP.equalsIgnoreCase("32") 
            && !WS_FAV_TYP.equalsIgnoreCase("41") 
            && !WS_FAV_TYP.equalsIgnoreCase("42") 
            && !WS_FAV_TYP.equalsIgnoreCase("51") 
            && !WS_FAV_TYP.equalsIgnoreCase("52") 
            && !WS_FAV_TYP.equalsIgnoreCase("00") 
            && !WS_FAV_TYP.equalsIgnoreCase("60") 
            && !WS_FAV_TYP.equalsIgnoreCase("71") 
            && !WS_FAV_TYP.equalsIgnoreCase("80"))) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAV_TYP_ERR;
            WS_FLD_NO = BPB1147_AWA_1147.FAV_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1147_AWA_1147.FST_DATE);
        if (BPB1147_AWA_1147.FST_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1147_AWA_1147.FST_DATE;
            S000_CALL_SCSSCKDT();
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1147_AWA_1147.FST_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1147_AWA_1147.FED_DATE);
        if (BPB1147_AWA_1147.FED_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1147_AWA_1147.FED_DATE;
            S000_CALL_SCSSCKDT();
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                WS_FLD_NO = BPB1147_AWA_1147.FED_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1147_AWA_1147.FED_DATE == 0) {
            BPB1147_AWA_1147.FED_DATE = 99991231;
        } else {
            if (BPB1147_AWA_1147.FED_DATE < BPB1147_AWA_1147.FST_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
                WS_FLD_NO = BPB1147_AWA_1147.FED_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1147_AWA_1147.FMAX_AMT);
        CEP.TRC(SCCGWA, BPB1147_AWA_1147.FMAX_PCT);
        if (BPB1147_AWA_1147.FMAX_PCT > 100) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
            WS_FLD_NO = BPB1147_AWA_1147.FMAX_PCT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_02_CHECK_FAV_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1147_AWA_1147.FAV_TYP);
        if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("11")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("12")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("13")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("14")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("15")) {
            WS_AMT = 0;
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                if (BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT != 0) {
                    WS_INPT_CNT += 1;
                    if (BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT < WS_AMT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_AMT_ERR;
                        WS_FLD_NO = BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_PCT1 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_PCT1_NO;
                    S000_ERR_MSG_PROC();
                }
                WS_AMT = BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT;
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF1[1-1].STR_AMT_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("21")) {
            WS_CNT = 0;
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                if (BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT != 0) {
                    WS_INPT_CNT += 1;
                    if (BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT <= WS_CNT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_CNT_ERR;
                        WS_FLD_NO = BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB1147_AWA_1147.FEE_INF2[WS_J-1].FAV_PCT2 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_PCT1_NO;
                    S000_ERR_MSG_PROC();
                }
                WS_CNT = BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT;
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF2[1-1].STR_CNT_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("31")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                if (BPB1147_AWA_1147.FEE_INF3[WS_J-1].STR_TM != 0) {
                    WS_INPT_CNT += 1;
                }
                if (BPB1147_AWA_1147.FEE_INF3[WS_J-1].STR_TM > BPB1147_AWA_1147.FEE_INF3[WS_J-1].END_TM) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF3[WS_J-1].END_TM_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB1147_AWA_1147.FEE_INF3[WS_J-1].FAV_PCT3 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF3[WS_J-1].FAV_PCT3_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = (short) BPB1147_AWA_1147.FEE_INF3[1-1].STR_TM;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("41")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF5[WS_J-1].AC_LVL);
                if (BPB1147_AWA_1147.FEE_INF4[WS_J-1].CI_LVL.trim().length() > 0) {
                    WS_INPT_CNT += 1;
                }
                if (BPB1147_AWA_1147.FEE_INF5[WS_J-1].FAV_PCT5 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF5[WS_J-1].FAV_PCT5_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF5[1-1].AC_LVL_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("51")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                if (BPB1147_AWA_1147.FEE_INF7[WS_J-1].CI_GRP.trim().length() > 0) {
                    WS_INPT_CNT += 1;
                }
                if (BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_PCT7 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_PCT7_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB1147_AWA_1147.FEE_INF7[WS_J-1].CI_GRP.trim().length() > 0) {
                    IBS.init(SCCGWA, CICMCGRP);
                    CICMCGRP.FUNC = 'I';
                    CICMCGRP.INPUT_DATA.GRPS_NO = BPB1147_AWA_1147.FEE_INF7[WS_J-1].CI_GRP;
                    S000_LINK_CIZMCGRP();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF7[1-1].CI_GRP_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("52")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                if (BPB1147_AWA_1147.FEE_INF8[WS_J-1].CI_SEG.trim().length() > 0) {
                    WS_INPT_CNT += 1;
                }
                if (BPB1147_AWA_1147.FEE_INF8[WS_J-1].FAV_PCT8 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF8[WS_J-1].FAV_PCT8_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF8[1-1].CI_SEG_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("32")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF1[1-1].STR_AMT);
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF8[10-1].FAV_PCT8);
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF9[WS_J-1].PROD_CD);
                if (BPB1147_AWA_1147.FEE_INF9[WS_J-1].PROD_CD.trim().length() > 0) {
                    WS_INPT_CNT += 1;
                }
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_PCT9);
                if (BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_PCT9 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_PCT9_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF8[1-1].CI_SEG_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("60")) {
            if (BPB1147_AWA_1147.DIF_VAL.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FIELD_MUSTINPUT;
                WS_FLD_NO = BPB1147_AWA_1147.DIF_VAL_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("71")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                if (!BPB1147_AWA_1147.FEE_INF6[WS_J-1].STS_WORD.equalsIgnoreCase("0")) {
                    WS_INPT_CNT += 1;
                }
                if (BPB1147_AWA_1147.FEE_INF6[WS_J-1].FAV_PCT6 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF6[WS_J-1].FAV_PCT6_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF6[1-1].STS_WORD_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("80")) {
            WS_INPT_CNT = 0;
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF1[1-1].STR_AMT);
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF9[10-1].FAV_PCT9);
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_IN10[WS_J-1].BR);
                if (BPB1147_AWA_1147.FEE_IN10[WS_J-1].BR != ' ') {
                    WS_INPT_CNT += 1;
                }
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_IN10[WS_J-1].FA_PCT10);
                if (BPB1147_AWA_1147.FEE_IN10[WS_J-1].FA_PCT10 > 100) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_NOT_OVER_HUN;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_IN10[WS_J-1].FA_PCT10_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_INPT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPT_ONE;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF8[1-1].CI_SEG_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
        }
        WS_AMT = 0;
        WS_CNT = 0;
        WS_I = 0;
        for (WS_J = 1; WS_J <= 10; WS_J += 1) {
            if (BPB1147_AWA_1147.FEE_INF3[WS_J-1].STR_TM > BPB1147_AWA_1147.FEE_INF3[WS_J-1].END_TM) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF3[WS_J-1].END_TM_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT <= WS_AMT 
                && BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_AMT_ERR;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT <= WS_CNT 
                && BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_CNT_ERR;
                WS_FLD_NO = BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT_NO;
                S000_ERR_MSG_PROC();
            }
            WS_I = (short) (WS_J - 1);
            if (WS_I > 0) {
                if (BPB1147_AWA_1147.FEE_INF3[WS_J-1].STR_TM < BPB1147_AWA_1147.FEE_INF3[WS_I-1].END_TM 
                    && BPB1147_AWA_1147.FEE_INF3[WS_J-1].STR_TM != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR;
                    WS_FLD_NO = BPB1147_AWA_1147.FEE_INF3[WS_J-1].END_TM_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_AMT = BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT;
            WS_CNT = BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT;
            CEP.TRC(SCCGWA, "CHECK WITH MAX AMT");
            CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_AMT9);
            CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_PCT9);
            if (BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_AMT1 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF2[WS_J-1].FAV_AMT2 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF3[WS_J-1].FAV_AMT3 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF4[WS_J-1].FAV_AMT4 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF5[WS_J-1].FAV_AMT5 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF6[WS_J-1].FAV_AMT6 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_AMT7 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF8[WS_J-1].FAV_AMT8 > BPB1147_AWA_1147.FMAX_AMT 
                || BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_AMT9 > BPB1147_AWA_1147.FMAX_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_GT_UP_AMT;
                S000_ERR_MSG_PROC();
            }
            if (BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_PCT1 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF2[WS_J-1].FAV_PCT2 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF3[WS_J-1].FAV_PCT3 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF4[WS_J-1].FAV_PCT4 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF5[WS_J-1].FAV_PCT5 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF6[WS_J-1].FAV_PCT6 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_PCT7 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF8[WS_J-1].FAV_PCT8 > BPB1147_AWA_1147.FMAX_PCT 
                || BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_PCT9 > BPB1147_AWA_1147.FMAX_PCT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_PER_GT_UP_PER;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_03_CHECK_DIF_CN() throws IOException,SQLException,Exception {
        WS_ADJ_TYP = BPB1147_AWA_1147.ADJ_TYP;
        if ((WS_ADJ_TYP != '0' 
            && WS_ADJ_TYP != '1' 
            && WS_ADJ_TYP != '2')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADJ_TYP_ERROR;
            WS_FLD_NO = BPB1147_AWA_1147.ADJ_TYP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_UPDATE_FAVINF_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFFAV);
        BPCOFFAV.FUNC = 'U';
        BPCOFFAV.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSFAV();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPB1147_AWA_1147.FAV_CD, BPCOFFAV.KEY.PRFR_CODE);
        BPCOFFAV.VAL.DESC = BPB1147_AWA_1147.DESC;
        BPCOFFAV.VAL.FAV_TYPE = BPB1147_AWA_1147.FAV_TYP;
        BPCOFFAV.DATE.EFF_DATE = BPB1147_AWA_1147.FST_DATE;
        BPCOFFAV.DATE.EXP_DATE = BPB1147_AWA_1147.FED_DATE;
        BPCOFFAV.VAL.FAV_PERIOD = BPB1147_AWA_1147.FAV_PRED;
        BPCOFFAV.VAL.MAX_FAV_AMT = BPB1147_AWA_1147.FMAX_AMT;
        BPCOFFAV.VAL.MAX_FAV_PCT = BPB1147_AWA_1147.FMAX_PCT;
        BPCOFFAV.VAL.FAV_COLL = BPB1147_AWA_1147.COL_FLG;
        BPCOFFAV.VAL.FAV_CPNT = BPB1147_AWA_1147.COL_CPN;
        BPCOFFAV.VAL.CAL_SOURCE = BPB1147_AWA_1147.CAL_MTH;
        BPCOFFAV.VAL.CAL_CYC = BPB1147_AWA_1147.COM_CYC;
        BPCOFFAV.VAL.COLL_TYPE = BPB1147_AWA_1147.COL_TYP;
        BPCOFFAV.VAL.CYC_NUM = BPB1147_AWA_1147.CYC_NUM;
        BPCOFFAV.VAL.FAV_SPLT_FLG = BPB1147_AWA_1147.SPL_FLG;
        BPCOFFAV.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFFAV.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            BPCOFFAV.VAL.ADJ_TYP = BPB1147_AWA_1147.ADJ_TYP;
            if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("60")) {
                BPCOFFAV.VAL.DIF_VAL = BPB1147_AWA_1147.DIF_VAL;
            }
        }
        if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("11")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("12")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("13")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("14")
            || BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("15")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].STR_AMT = BPB1147_AWA_1147.FEE_INF1[WS_J-1].STR_AMT;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_AMT1;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF1[WS_J-1].FAV_PCT1;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("21")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].STR_CNT = BPB1147_AWA_1147.FEE_INF2[WS_J-1].STR_CNT;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF2[WS_J-1].FAV_AMT2;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF2[WS_J-1].FAV_PCT2;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("31")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].STR_TIME = BPB1147_AWA_1147.FEE_INF3[WS_J-1].STR_TM;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].END_TIME = BPB1147_AWA_1147.FEE_INF3[WS_J-1].END_TM;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF3[WS_J-1].FAV_AMT3;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF3[WS_J-1].FAV_PCT3;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("41")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].CI_LEVEL = BPB1147_AWA_1147.FEE_INF4[WS_J-1].CI_LVL;
                CEP.TRC(SCCGWA, BPB1147_AWA_1147.FEE_INF4[WS_J-1].CI_LVL);
                CEP.TRC(SCCGWA, BPCOFFAV.VAL.FAV_DATA[WS_J-1].CI_LEVEL);
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_CNT = BPB1147_AWA_1147.FEE_INF4[WS_J-1].FAV_CNT4;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF4[WS_J-1].FAV_AMT4;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF4[WS_J-1].FAV_PCT4;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("51")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].GROUP = BPB1147_AWA_1147.FEE_INF7[WS_J-1].CI_GRP;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_CNT = BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_CNT7;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_AMT7;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF7[WS_J-1].FAV_PCT7;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("52")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].SEGMENT = BPB1147_AWA_1147.FEE_INF8[WS_J-1].CI_SEG;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF8[WS_J-1].FAV_AMT8;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF8[WS_J-1].FAV_PCT8;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("32")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].PROD_CODE = BPB1147_AWA_1147.FEE_INF9[WS_J-1].PROD_CD;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_CNT = BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_CNT9;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_AMT9;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF9[WS_J-1].FAV_PCT9;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("71")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].STATUS = BPB1147_AWA_1147.FEE_INF6[WS_J-1].STS_WORD;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_INF6[WS_J-1].FAV_AMT6;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_INF6[WS_J-1].FAV_PCT6;
            }
        } else if (BPB1147_AWA_1147.FAV_TYP.equalsIgnoreCase("80")) {
            for (WS_J = 1; WS_J <= 10; WS_J += 1) {
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].BR = BPB1147_AWA_1147.FEE_IN10[WS_J-1].BR;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_CNT = BPB1147_AWA_1147.FEE_IN10[WS_J-1].FA_CNT10;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_AMT = BPB1147_AWA_1147.FEE_IN10[WS_J-1].FA_AMT10;
                BPCOFFAV.VAL.FAV_DATA[WS_J-1].FAV_PCT = BPB1147_AWA_1147.FEE_IN10[WS_J-1].FA_PCT10;
            }
        } else {
        }
    }
    public void S000_CALL_BPZFSFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_FFAV_MAINTAIN, BPCOFFAV);
    }
    public void S000_LINK_CIZMCGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMCGRP", CICMCGRP);
        if (CICMCGRP.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMCGRP.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}