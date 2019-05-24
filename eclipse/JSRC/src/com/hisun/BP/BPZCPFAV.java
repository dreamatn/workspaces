package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCPFAV {
    int JIBS_tmp_int;
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    String PGM_BPZCPFAV = "BPZCPFAV";
    String TBL_BPTEXFAV = "BPTEXFAV";
    String CPN_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_S_BPPROD_DEF = "BP-S-MGM-BPPROD";
    short WS_S = 0;
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_R_SPCA = "BP-R-SPCA-M       ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    double K_RATE_MAX = 999999.99999999;
    double K_AMT_MAX = 9999999999999.99;
    String WS_CCY = " ";
    String WS_B_CCY = " ";
    String WS_S_CCY = " ";
    short WS_I = 0;
    int WS_UNIT = 0;
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    String WS_EX_GROUP = " ";
    String WS_SET_TYPE = " ";
    char WS_PART_FLG = ' ';
    char WS_UL_FLG = ' ';
    String WS_SEG_CODE = " ";
    String WS_CHNL_CODE = " ";
    double WS_TX_AMT = 0;
    char WS_FAV_BUY = ' ';
    char WS_FAV_SELL = ' ';
    char WS_FAY_FLG = ' ';
    BPZCPFAV_WS_SP_GROUP[] WS_SP_GROUP = new BPZCPFAV_WS_SP_GROUP[5];
    double WT_CSBUY_SP = 0;
    double WT_CSSELL_SP = 0;
    double WT_FXBUY_SP = 0;
    double WT_FXSELL_SP = 0;
    short WS_DEC_PNT = 0;
    String WS_PNT_C = " ";
    char WS_SP_CCY = ' ';
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFX BPCFX = new BPCFX();
    CICCUST CICCUST = new CICCUST();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCSPROD BPCSPROD = new BPCSPROD();
    BPCGFAV BPCGFAV = new BPCGFAV();
    SCCMSG SCCMSG = new SCCMSG();
    BPRSPCA BPRSPCAB = new BPRSPCA();
    BPRSPCA BPRSPCAS = new BPRSPCA();
    BPRSPCA BPRSPCA = new BPRSPCA();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCTSPCM BPCTSPCM = new BPCTSPCM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT1 = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQCCY BPCQCCYB = new BPCQCCY();
    BPCQCCY BPCQCCYS = new BPCQCCY();
    BPREXRD BPREXRD = new BPREXRD();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPREXFAV BPREXFAV = new BPREXFAV();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    String LK_REC = " ";
    BPCPFAV BPCPFAV;
    public BPZCPFAV() {
        for (int i=0;i<5;i++) WS_SP_GROUP[i] = new BPZCPFAV_WS_SP_GROUP();
    }
    public void MP(SCCGWA SCCGWA, BPCPFAV BPCPFAV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPFAV = BPCPFAV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZCPFAV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            WS_SP_GROUP[WS_I-1].WS_FXBUY_SP = 0;
            WS_SP_GROUP[WS_I-1].WS_FXSELL_SP = 0;
            WS_SP_GROUP[WS_I-1].WS_CSBUY_SP = 0;
            WS_SP_GROUP[WS_I-1].WS_CSSELL_SP = 0;
        }
        WT_FXBUY_SP = 0;
        WT_FXSELL_SP = 0;
        WT_CSBUY_SP = 0;
        WT_CSSELL_SP = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B00_01_CHECK_PROC();
        B00_02_GET_CODE();
        if (WS_FAY_FLG == 'Y') {
            B00_03_GET_CI_INFO();
            B00_04_GET_FAV();
            B00_05_COMPUTE();
            B00_07_OUTPUT();
        } else {
            BPCPFAV.RC.OVER_FLG = 'Y';
        }
    }
    public void B00_01_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT1);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT1.KEY.TYP = "EXRT";
        BPREXRT1.KEY.CD = BPCPFAV.RC.EXRD;
        BPCPRMR.DAT_PTR = BPREXRT1;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
        }
        CEP.TRC(SCCGWA, BPREXRT1.DAT_TXT.BASE_CCY);
        IBS.init(SCCGWA, BPCOIEC);
        if (!BPCPFAV.RC.BUY_CCY.equalsIgnoreCase(BPCPFAV.RC.SELL_CCY)) {
            BPCOIEC.CCY1 = BPCPFAV.RC.BUY_CCY;
            BPCOIEC.CCY2 = BPCPFAV.RC.SELL_CCY;
            BPCOIEC.EXR_TYP = BPCPFAV.RC.EXRD;
            S000_CALL_BPZSIEC();
        } else {
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (BPCPFAV.RC.BUY_CCY == null) BPCPFAV.RC.BUY_CCY = "";
            JIBS_tmp_int = BPCPFAV.RC.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCPFAV.RC.BUY_CCY += " ";
            BPCOIEC.EXR_CODE = BPCPFAV.RC.BUY_CCY + BPCOIEC.EXR_CODE.substring(3);
        }
        CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCPFAV.RC.EXRD;
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        BPREXRD.KEY.CCY = BPCOIEC.EXR_CODE.substring(0, 3);
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (BPCTEXRM.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EXRATE_NOT_DEFINE);
        } else {
            if (BPREXRD.EXR_PNT == ' ') WS_DEC_PNT = 0;
            else WS_DEC_PNT = Short.parseShort(""+BPREXRD.EXR_PNT);
            WS_UNIT = BPREXRD.UNT;
        }
    }
    public void B00_02_GET_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPROD);
        BPCSPROD.FUNC = 'Q';
        BPCSPROD.KEY.PRD_TYPE = BPCPFAV.RC.PRD_TYPE;
        BPCSPROD.DATA_TXT.EFF_DATE = BPCPFAV.RC.EFF_DATE;
        BPCSPROD.DATA_TXT.EXP_DATE = BPCPFAV.RC.EXP_DATE;
        BPCSPROD.DATA_TXT.CONT_TYP = "FX";
        S000_CALL_BPZSPROD();
        WS_EFF_DATE = BPCSPROD.DATA_TXT.EFF_DATE;
        WS_EXP_DATE = BPCSPROD.DATA_TXT.EXP_DATE;
        WS_EX_GROUP = BPCSPROD.DATA_TXT.EX_GROUP;
        WS_SET_TYPE = BPCSPROD.DATA_TXT.SET_TYPE;
        WS_PART_FLG = BPCSPROD.DATA_TXT.PART_FLG;
        WS_UL_FLG = BPCSPROD.DATA_TXT.UL_FLG;
        WS_SP_GROUP[1-1].WS_FAV_CODE = BPCSPROD.DATA_TXT.FAV_CODE1;
        WS_SP_GROUP[2-1].WS_FAV_CODE = BPCSPROD.DATA_TXT.FAV_CODE2;
        WS_SP_GROUP[3-1].WS_FAV_CODE = BPCSPROD.DATA_TXT.FAV_CODE3;
        WS_SP_GROUP[4-1].WS_FAV_CODE = BPCSPROD.DATA_TXT.FAV_CODE4;
        WS_SP_GROUP[5-1].WS_FAV_CODE = BPCSPROD.DATA_TXT.FAV_CODE5;
        WS_FAV_BUY = BPCSPROD.DATA_TXT.FAV_BUY;
        WS_FAV_SELL = BPCSPROD.DATA_TXT.FAV_SELL;
        CEP.TRC(SCCGWA, "=====CALL-BPZSPROD=========");
        CEP.TRC(SCCGWA, WS_EFF_DATE);
        CEP.TRC(SCCGWA, WS_EXP_DATE);
        CEP.TRC(SCCGWA, WS_EX_GROUP);
        CEP.TRC(SCCGWA, WS_SET_TYPE);
        CEP.TRC(SCCGWA, WS_PART_FLG);
        CEP.TRC(SCCGWA, WS_UL_FLG);
        CEP.TRC(SCCGWA, WS_SP_GROUP[1-1].WS_FAV_CODE);
        CEP.TRC(SCCGWA, WS_SP_GROUP[2-1].WS_FAV_CODE);
        CEP.TRC(SCCGWA, WS_SP_GROUP[3-1].WS_FAV_CODE);
        CEP.TRC(SCCGWA, WS_SP_GROUP[4-1].WS_FAV_CODE);
        CEP.TRC(SCCGWA, WS_SP_GROUP[5-1].WS_FAV_CODE);
        CEP.TRC(SCCGWA, WS_FAV_BUY);
        CEP.TRC(SCCGWA, WS_FAV_SELL);
        if (WS_SP_GROUP[1-1].WS_FAV_CODE.trim().length() > 0 
            || WS_SP_GROUP[2-1].WS_FAV_CODE.trim().length() > 0 
            || WS_SP_GROUP[3-1].WS_FAV_CODE.trim().length() > 0 
            || WS_SP_GROUP[4-1].WS_FAV_CODE.trim().length() > 0 
            || WS_SP_GROUP[5-1].WS_FAV_CODE.trim().length() > 0) {
            WS_FAY_FLG = 'Y';
        }
    }
    public void B00_03_GET_CI_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFAV.RC.CI_NO);
        if (BPCPFAV.RC.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "===========LINK CIZCUST============");
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCPFAV.RC.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (CICCUST.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_HOLD_LVL);
                WS_SEG_CODE = CICCUST.O_DATA.O_HOLD_LVL;
            }
        }
        WS_CHNL_CODE = SCCGWA.COMM_AREA.CHNL;
        if (BPCPFAV.RC.BUY_AMT == 0) {
            BPCPFAV.RC.BUY_AMT = BPCPFAV.RC.SELL_AMT * WS_UNIT / BPCPFAV.RC.SYS_EX_RATE;
            bigD = new BigDecimal(BPCPFAV.RC.BUY_AMT);
            BPCPFAV.RC.BUY_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
        if (BPCPFAV.RC.SELL_AMT == 0) {
            BPCPFAV.RC.SELL_AMT = BPCPFAV.RC.BUY_AMT * WS_UNIT / BPCPFAV.RC.SYS_EX_RATE;
            bigD = new BigDecimal(BPCPFAV.RC.SELL_AMT);
            BPCPFAV.RC.SELL_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
    }
    public void B00_04_GET_FAV() throws IOException,SQLException,Exception {
        WS_SP_CCY = 'N';
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            if (WS_SP_GROUP[WS_I-1].WS_FAV_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCGFAV);
                if (WS_SP_GROUP[WS_I-1].WS_FAV_CODE == null) WS_SP_GROUP[WS_I-1].WS_FAV_CODE = "";
                JIBS_tmp_int = WS_SP_GROUP[WS_I-1].WS_FAV_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_SP_GROUP[WS_I-1].WS_FAV_CODE += " ";
                BPCGFAV.INFO.FAV_TYP = WS_SP_GROUP[WS_I-1].WS_FAV_CODE.substring(0, 1).charAt(0);
                if (WS_SP_GROUP[WS_I-1].WS_FAV_CODE == null) WS_SP_GROUP[WS_I-1].WS_FAV_CODE = "";
                JIBS_tmp_int = WS_SP_GROUP[WS_I-1].WS_FAV_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_SP_GROUP[WS_I-1].WS_FAV_CODE += " ";
                if (WS_SP_GROUP[WS_I-1].WS_FAV_CODE.substring(2 - 1, 2 + 4 - 1).trim().length() == 0) BPCGFAV.INFO.SEQ = 0;
                else BPCGFAV.INFO.SEQ = Short.parseShort(WS_SP_GROUP[WS_I-1].WS_FAV_CODE.substring(2 - 1, 2 + 4 - 1));
                BPCGFAV.INFO.CI_NO = BPCPFAV.RC.CI_NO;
                BPCGFAV.INFO.CHNL_NO = WS_CHNL_CODE;
                BPCGFAV.INFO.AC_TYP = WS_SEG_CODE;
                BPCGFAV.INFO.EXEC_FLG = 'N';
                if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                BPCGFAV.INFO.CCY = BPCOIEC.EXR_CODE.substring(0, 3);
                if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                if (BPCPFAV.RC.BUY_CCY.equalsIgnoreCase(BPCOIEC.EXR_CODE.substring(0, 3))) {
                    BPCGFAV.INFO.STR_AMT = BPCPFAV.RC.BUY_AMT;
                } else {
                    BPCGFAV.INFO.STR_AMT = BPCPFAV.RC.SELL_AMT;
                }
                S000_CALL_BPZGFAVD();
                CEP.TRC(SCCGWA, "================= BPZGFAVD 1 ===============");
                CEP.TRC(SCCGWA, WS_SP_GROUP[WS_I-1].WS_FAV_CODE);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.CAL_MTH);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.EXEC_FLG);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.CSBUY_SP01);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.CSSELL_SP01);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.FXBUY_SP01);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.FXSELL_SP01);
                if (BPCGFAV.INFO.EXEC_FLG == 'N') {
                    if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                    JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                    BPCGFAV.INFO.CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
                    if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                    JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                    if (BPCPFAV.RC.BUY_CCY.equalsIgnoreCase(BPCOIEC.EXR_CODE.substring(0, 3))) {
                        BPCGFAV.INFO.STR_AMT = BPCPFAV.RC.SELL_AMT;
                    } else {
                        BPCGFAV.INFO.STR_AMT = BPCPFAV.RC.BUY_AMT;
                    }
                    S000_CALL_BPZGFAVD();
                    CEP.TRC(SCCGWA, "================= BPZGFAVD 2 ===============");
                    CEP.TRC(SCCGWA, WS_SP_GROUP[WS_I-1].WS_FAV_CODE);
                    CEP.TRC(SCCGWA, BPCGFAV.INFO.CAL_MTH);
                    CEP.TRC(SCCGWA, BPCGFAV.INFO.EXEC_FLG);
                    CEP.TRC(SCCGWA, BPCGFAV.INFO.CSBUY_SP01);
                    CEP.TRC(SCCGWA, BPCGFAV.INFO.CSSELL_SP01);
                    CEP.TRC(SCCGWA, BPCGFAV.INFO.FXBUY_SP01);
                    CEP.TRC(SCCGWA, BPCGFAV.INFO.FXSELL_SP01);
                    if (BPCGFAV.INFO.EXEC_FLG == 'N') {
                        BPCGFAV.INFO.CCY = "A";
                        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                        if (BPCPFAV.RC.BUY_CCY.equalsIgnoreCase(BPCOIEC.EXR_CODE.substring(0, 3))) {
                            BPCGFAV.INFO.STR_AMT = BPCPFAV.RC.BUY_AMT;
                        } else {
                            BPCGFAV.INFO.STR_AMT = BPCPFAV.RC.SELL_AMT;
                        }
                        S000_CALL_BPZGFAVD();
                        CEP.TRC(SCCGWA, "================ BPZGFAVD 3 ==============");
                        CEP.TRC(SCCGWA, WS_SP_GROUP[WS_I-1].WS_FAV_CODE);
                        CEP.TRC(SCCGWA, BPCGFAV.INFO.CAL_MTH);
                        CEP.TRC(SCCGWA, BPCGFAV.INFO.EXEC_FLG);
                        CEP.TRC(SCCGWA, BPCGFAV.INFO.CSBUY_SP01);
                        CEP.TRC(SCCGWA, BPCGFAV.INFO.CSSELL_SP01);
                        CEP.TRC(SCCGWA, BPCGFAV.INFO.FXBUY_SP01);
                        CEP.TRC(SCCGWA, BPCGFAV.INFO.FXSELL_SP01);
                    }
                }
                if (BPCGFAV.INFO.EXEC_FLG == 'Y') {
                    JIBS_tmp_str[0] = "" + WS_DEC_PNT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_PNT_C = JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1);
                    if (BPCGFAV.INFO.CAL_MTH == '0') {
                        BPCGFAV.INFO.CSBUY_SP01 = BPCPFAV.RC.SYS_EX_RATE * BPCGFAV.INFO.CSBUY_SP01 / 100;
                        BPCGFAV.INFO.CSSELL_SP01 = BPCPFAV.RC.SYS_EX_RATE * BPCGFAV.INFO.CSSELL_SP01 / 100;
                        BPCGFAV.INFO.FXBUY_SP01 = BPCPFAV.RC.SYS_EX_RATE * BPCGFAV.INFO.FXBUY_SP01 / 100;
                        BPCGFAV.INFO.FXSELL_SP01 = BPCPFAV.RC.SYS_EX_RATE * BPCGFAV.INFO.FXSELL_SP01 / 100;
                    } else {
                        if (WS_PNT_C.equalsIgnoreCase("01")) {
                            BPCGFAV.INFO.CSBUY_SP01 = BPCGFAV.INFO.CSBUY_SP01 / 10;
                            BPCGFAV.INFO.CSSELL_SP01 = BPCGFAV.INFO.CSSELL_SP01 / 10;
                            BPCGFAV.INFO.FXBUY_SP01 = BPCGFAV.INFO.FXBUY_SP01 / 10;
                            BPCGFAV.INFO.FXSELL_SP01 = BPCGFAV.INFO.FXSELL_SP01 / 10;
                        } else if (WS_PNT_C.equalsIgnoreCase("02")) {
                            BPCGFAV.INFO.CSBUY_SP01 = BPCGFAV.INFO.CSBUY_SP01 / 100;
                            BPCGFAV.INFO.CSSELL_SP01 = BPCGFAV.INFO.CSSELL_SP01 / 100;
                            BPCGFAV.INFO.FXBUY_SP01 = BPCGFAV.INFO.FXBUY_SP01 / 100;
                            BPCGFAV.INFO.FXSELL_SP01 = BPCGFAV.INFO.FXSELL_SP01 / 100;
                        } else if (WS_PNT_C.equalsIgnoreCase("03")) {
                            BPCGFAV.INFO.CSBUY_SP01 = BPCGFAV.INFO.CSBUY_SP01 / 1000;
                            BPCGFAV.INFO.CSSELL_SP01 = BPCGFAV.INFO.CSSELL_SP01 / 1000;
                            BPCGFAV.INFO.FXBUY_SP01 = BPCGFAV.INFO.FXBUY_SP01 / 1000;
                            BPCGFAV.INFO.FXSELL_SP01 = BPCGFAV.INFO.FXSELL_SP01 / 1000;
                        } else if (WS_PNT_C.equalsIgnoreCase("04")) {
                            BPCGFAV.INFO.CSBUY_SP01 = BPCGFAV.INFO.CSBUY_SP01 / 10000;
                            BPCGFAV.INFO.CSSELL_SP01 = BPCGFAV.INFO.CSSELL_SP01 / 10000;
                            BPCGFAV.INFO.FXBUY_SP01 = BPCGFAV.INFO.FXBUY_SP01 / 10000;
                            BPCGFAV.INFO.FXSELL_SP01 = BPCGFAV.INFO.FXSELL_SP01 / 10000;
                        }
                    }
                }
                CEP.TRC(SCCGWA, BPCGFAV.INFO.CCY);
                CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, WS_SP_CCY);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.CSBUY_SP01);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.CSSELL_SP01);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.FXBUY_SP01);
                CEP.TRC(SCCGWA, BPCGFAV.INFO.FXSELL_SP01);
                if (BPCGFAV.INFO.CSBUY_SP01 != 0 
                    || BPCGFAV.INFO.CSSELL_SP01 != 0 
                    || BPCGFAV.INFO.FXBUY_SP01 != 0 
                    || BPCGFAV.INFO.FXSELL_SP01 != 0) {
                    if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                    JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                    if (BPCGFAV.INFO.CCY.equalsIgnoreCase(BPCOIEC.EXR_CODE.substring(0, 3)) 
                        || BPCGFAV.INFO.CCY.equalsIgnoreCase("A")) {
                        WS_SP_GROUP[WS_I-1].WS_CSBUY_SP = BPCGFAV.INFO.CSBUY_SP01;
                        WS_SP_GROUP[WS_I-1].WS_CSSELL_SP = BPCGFAV.INFO.CSSELL_SP01;
                        WS_SP_GROUP[WS_I-1].WS_FXBUY_SP = BPCGFAV.INFO.FXBUY_SP01;
                        WS_SP_GROUP[WS_I-1].WS_FXSELL_SP = BPCGFAV.INFO.FXSELL_SP01;
                        WS_SP_CCY = 'Y';
                        BPCPFAV.RC.OVER_FLG = '1';
                    } else {
                        WS_SP_GROUP[WS_I-1].WS_2_CSBUY_SP = BPCGFAV.INFO.CSBUY_SP01;
                        WS_SP_GROUP[WS_I-1].WS_2_CSSELL_SP = BPCGFAV.INFO.CSSELL_SP01;
                        WS_SP_GROUP[WS_I-1].WS_2_FXBUY_SP = BPCGFAV.INFO.FXBUY_SP01;
                        WS_SP_GROUP[WS_I-1].WS_2_FXSELL_SP = BPCGFAV.INFO.FXSELL_SP01;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_SP_CCY);
        if (WS_SP_CCY == 'N') {
            BPCPFAV.RC.OVER_FLG = '2';
            for (WS_I = 1; WS_I <= 5; WS_I += 1) {
                WS_SP_GROUP[WS_I-1].WS_CSBUY_SP = WS_SP_GROUP[WS_I-1].WS_2_CSBUY_SP;
                WS_SP_GROUP[WS_I-1].WS_CSSELL_SP = WS_SP_GROUP[WS_I-1].WS_2_CSSELL_SP;
                WS_SP_GROUP[WS_I-1].WS_FXBUY_SP = WS_SP_GROUP[WS_I-1].WS_2_FXBUY_SP;
                WS_SP_GROUP[WS_I-1].WS_FXSELL_SP = WS_SP_GROUP[WS_I-1].WS_2_FXSELL_SP;
            }
        }
    }
    public void B00_05_COMPUTE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SP_GROUP[1-1].WS_CSBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[1-1].WS_CSSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[1-1].WS_FXBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[1-1].WS_FXSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[2-1].WS_CSBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[2-1].WS_CSSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[2-1].WS_FXBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[2-1].WS_FXSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[3-1].WS_CSBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[3-1].WS_CSSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[3-1].WS_FXBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[3-1].WS_FXSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[4-1].WS_CSBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[4-1].WS_CSSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[4-1].WS_FXBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[4-1].WS_FXSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[5-1].WS_CSBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[5-1].WS_CSSELL_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[5-1].WS_FXBUY_SP);
        CEP.TRC(SCCGWA, WS_SP_GROUP[5-1].WS_FXSELL_SP);
        WS_FND_FLG = 'N';
        for (WS_I = 1; WS_I <= 5 
            && WS_FND_FLG != 'Y'; WS_I += 1) {
            if (WS_SP_GROUP[WS_I-1].WS_CSBUY_SP != 0 
                || WS_SP_GROUP[WS_I-1].WS_CSSELL_SP != 0 
                || WS_SP_GROUP[WS_I-1].WS_FXBUY_SP != 0 
                || WS_SP_GROUP[WS_I-1].WS_FXSELL_SP != 0) {
                WT_CSBUY_SP = WS_SP_GROUP[WS_I-1].WS_CSBUY_SP;
                WT_CSSELL_SP = WS_SP_GROUP[WS_I-1].WS_CSSELL_SP;
                WT_FXBUY_SP = WS_SP_GROUP[WS_I-1].WS_FXBUY_SP;
                WT_FXSELL_SP = WS_SP_GROUP[WS_I-1].WS_FXSELL_SP;
                WS_FND_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, "+++++++++++++++");
        CEP.TRC(SCCGWA, WT_CSBUY_SP);
        CEP.TRC(SCCGWA, WT_CSSELL_SP);
        CEP.TRC(SCCGWA, WT_FXBUY_SP);
        CEP.TRC(SCCGWA, WT_FXSELL_SP);
        while (WS_I <= 5) {
            if (WS_SP_GROUP[WS_I-1].WS_CSBUY_SP != 0 
                || WS_SP_GROUP[WS_I-1].WS_CSSELL_SP != 0 
                || WS_SP_GROUP[WS_I-1].WS_FXBUY_SP != 0 
                || WS_SP_GROUP[WS_I-1].WS_FXSELL_SP != 0) {
                B00_05_01_PROC();
            }
            CEP.TRC(SCCGWA, "##################");
            CEP.TRC(SCCGWA, WT_CSBUY_SP);
            CEP.TRC(SCCGWA, WT_CSSELL_SP);
            CEP.TRC(SCCGWA, WT_FXBUY_SP);
            CEP.TRC(SCCGWA, WT_FXSELL_SP);
            WS_I += 1;
        }
    }
    public void B00_05_01_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        if (WS_FAV_BUY == '0') {
            if (WS_SP_GROUP[WS_I-1].WS_CSBUY_SP > WT_CSBUY_SP) {
                WT_CSBUY_SP = WS_SP_GROUP[WS_I-1].WS_CSBUY_SP;
            }
            if (WS_SP_GROUP[WS_I-1].WS_FXBUY_SP > WT_FXBUY_SP) {
                WT_FXBUY_SP = WS_SP_GROUP[WS_I-1].WS_FXBUY_SP;
            }
        } else if (WS_FAV_BUY == '1') {
            if (WS_SP_GROUP[WS_I-1].WS_CSBUY_SP < WT_CSBUY_SP) {
                WT_CSBUY_SP = WS_SP_GROUP[WS_I-1].WS_CSBUY_SP;
            }
            if (WS_SP_GROUP[WS_I-1].WS_FXBUY_SP < WT_FXBUY_SP) {
                WT_FXBUY_SP = WS_SP_GROUP[WS_I-1].WS_FXBUY_SP;
            }
        } else if (WS_FAV_BUY == '2') {
            WT_CSBUY_SP = WT_CSBUY_SP + WS_SP_GROUP[WS_I-1].WS_CSBUY_SP;
            WT_FXBUY_SP = WT_FXBUY_SP + WS_SP_GROUP[WS_I-1].WS_FXBUY_SP;
        }
        if (WS_FAV_SELL == '0') {
            if (WS_SP_GROUP[WS_I-1].WS_CSSELL_SP > WT_CSSELL_SP) {
                WT_CSSELL_SP = WS_SP_GROUP[WS_I-1].WS_CSSELL_SP;
            }
            if (WS_SP_GROUP[WS_I-1].WS_FXSELL_SP > WT_FXSELL_SP) {
                WT_FXSELL_SP = WS_SP_GROUP[WS_I-1].WS_FXSELL_SP;
            }
        } else if (WS_FAV_SELL == '1') {
            if (WS_SP_GROUP[WS_I-1].WS_CSSELL_SP < WT_CSSELL_SP) {
                WT_CSSELL_SP = WS_SP_GROUP[WS_I-1].WS_CSSELL_SP;
            }
            if (WS_SP_GROUP[WS_I-1].WS_FXSELL_SP < WT_FXSELL_SP) {
                WT_FXSELL_SP = WS_SP_GROUP[WS_I-1].WS_FXSELL_SP;
            }
        } else if (WS_FAV_SELL == '2') {
            WT_CSSELL_SP = WT_CSSELL_SP + WS_SP_GROUP[WS_I-1].WS_CSSELL_SP;
            WT_FXSELL_SP = WT_FXSELL_SP + WS_SP_GROUP[WS_I-1].WS_FXSELL_SP;
        }
    }
    public void B00_07_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "============END=============");
        CEP.TRC(SCCGWA, WT_CSBUY_SP);
        CEP.TRC(SCCGWA, WT_CSSELL_SP);
        CEP.TRC(SCCGWA, WT_FXBUY_SP);
        CEP.TRC(SCCGWA, WT_FXSELL_SP);
        BPCPFAV.RC.CS_BUY_SCP = WT_CSBUY_SP;
        BPCPFAV.RC.CS_SELL_SCP = WT_CSSELL_SP;
        BPCPFAV.RC.FX_BUY_SCP = WT_FXBUY_SP;
        BPCPFAV.RC.FX_SELL_SCP = WT_FXSELL_SP;
    }
    public void B00_02_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = BPCPFAV.RC.EXRD;
        if (BPCPFAV.RC.BUY_AMT != 0) {
            BPCFX.BUY_AMT = BPCPFAV.RC.BUY_AMT;
        } else {
            BPCFX.SELL_AMT = BPCPFAV.RC.SELL_AMT;
        }
        BPCFX.B_CASH_FLG = BPCPFAV.RC.BUY_CS_FLG;
        BPCFX.S_CASH_FLG = BPCPFAV.RC.SELL_CS_FLG;
        BPCFX.BUY_CCY = BPCPFAV.RC.BUY_CCY;
        BPCFX.SELL_CCY = BPCPFAV.RC.SELL_CCY;
        BPCFX.TRN_RATE = BPCPFAV.RC.SYS_EX_RATE;
        S000_CALL_BPZSFX();
        if (BPCPFAV.RC.BUY_AMT != 0) {
            WS_TX_AMT = BPCFX.SELL_AMT;
        } else {
            WS_TX_AMT = BPCFX.BUY_AMT;
        }
    }
    public void S000_CALL_BPZTSPCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_SPCA, BPCTSPCM);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        if (BPCOIEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOIEC.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
    }
    public void S000_CALL_BPZSPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPPROD_DEF, BPCSPROD);
    }
    public void S000_CALL_BPZGFAVD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GET-FAV", BPCGFAV);
    }
    public void S000_CALL_BPZPQBANK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQBNK.RC);
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCQCCY.RC);
        }
        if (BPCQCCY.DATA.EXH_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_NOT_ALLOW_EX);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFX.RC);
        }
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRM, BPCTEXRM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
