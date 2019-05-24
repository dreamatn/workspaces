package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1121 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP055";
    char K_LOCAL_TYPE = '0';
    String K_LOCAL_CCY = "156";
    String K_ABROAD_CCY = "840";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_FSTD_MAINTAIN = "BP-F-S-MAINTAIN-FSTD";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY    ";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_NO = 0;
    short WS_NO1 = 0;
    char WS_FEE_EXG_TYP = ' ';
    double WS_UP_AMT = 0;
    double WS_DWN_AMT = 0;
    double WS_MIN_AMT = 0;
    double WS_MAX_AMT = 0;
    String WS_BUY_CCY = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFSTD BPCOFSTD = new BPCOFSTD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    BPCFX BPCFX = new BPCFX();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB1121_AWA_1121 BPB1121_AWA_1121;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1121 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1121_AWA_1121>");
        BPB1121_AWA_1121 = (BPB1121_AWA_1121) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCQCCY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            B020_UPDATE_STD_RATE_CN();
        } else {
            B010_CHECK_INPUT();
            B020_UPDATE_STD_RATE();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1121_AWA_1121.FEE_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CD_MUST_INPUT;
            WS_FLD_NO = BPB1121_AWA_1121.FEE_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1121_AWA_1121.REF_CCY.trim().length() > 0) {
            BPCQCCY.DATA.CCY = BPB1121_AWA_1121.REF_CCY;
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                CEP.TRC(SCCGWA, BPCQCCY.RC);
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                WS_FLD_NO = BPB1121_AWA_1121.REF_CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REF_CCY_MUST_INPUT;
            WS_FLD_NO = BPB1121_AWA_1121.REF_CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB1121_AWA_1121.EFF_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1121_AWA_1121.EFF_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB1121_AWA_1121.EXP_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1121_AWA_1121.EXP_DATE;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPB1121_AWA_1121.EFF_DATE);
        if (BPB1121_AWA_1121.EXP_DATE <= BPB1121_AWA_1121.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1121_AWA_1121.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1121_AWA_1121.FEE_CCY.trim().length() > 0) {
            BPCQCCY.DATA.CCY = BPB1121_AWA_1121.FEE_CCY;
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                WS_FLD_NO = BPB1121_AWA_1121.FEE_CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if ((BPB1121_AWA_1121.MIN_AMT != 0) 
            && (BPB1121_AWA_1121.MAX_AMT != 0) 
            && (BPB1121_AWA_1121.MIN_AMT > BPB1121_AWA_1121.MAX_AMT)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MIN_AMT_GT_MAX_AMT;
            WS_FLD_NO = BPB1121_AWA_1121.MIN_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB1121_AWA_1121.CLA_TYP != '0') 
            && (BPB1121_AWA_1121.CLA_TYP != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_TYPE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.CLA_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB1121_AWA_1121.AGG_TYP != '0') 
            && (BPB1121_AWA_1121.AGG_TYP != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AGGR_TYPE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.AGG_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1121_AWA_1121.CLA_TYP == '0') {
            if ((BPB1121_AWA_1121.RAT_INFO[1-1].UP_AMT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INPUT;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[1-1].UP_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT1 = 2; ((WS_CNT1 <= 10) 
                && (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT != 0)); WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT < BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INC;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 99999999999999.99) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT = WS_CNT1; WS_CNT <= 10; WS_CNT += 1) {
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_CANNOT_INPUT;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        } else {
            if ((BPB1121_AWA_1121.RAT_INFO[1-1].UP_CNT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_MUST_INPUT;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[1-1].UP_CNT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT1 = 2; ((WS_CNT1 <= 10) 
                && (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT != 0)); WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT < BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_MUST_INC;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 9999) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_ERR;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT = WS_CNT1; WS_CNT <= 10; WS_CNT += 1) {
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_CANNOT_INPUT;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_NO = 0;
        for (WS_CNT = 1; (WS_CNT <= 10) 
            && ((BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 0) 
            || (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 0)); WS_CNT += 1) {
            WS_NO = (short) (WS_NO + 1);
        }
        CEP.TRC(SCCGWA, WS_NO);
        R000_CHECK_RESULT_PROC();
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB1121_AWA_1121.FEE_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CD_MUST_INPUT;
            WS_FLD_NO = BPB1121_AWA_1121.FEE_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1121_AWA_1121.REF_CCY.trim().length() > 0) {
            BPCQCCY.DATA.CCY = BPB1121_AWA_1121.REF_CCY;
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                CEP.TRC(SCCGWA, BPCQCCY.RC);
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                WS_FLD_NO = BPB1121_AWA_1121.REF_CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REF_CCY_MUST_INPUT;
            WS_FLD_NO = BPB1121_AWA_1121.REF_CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB1121_AWA_1121.EFF_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1121_AWA_1121.EFF_DATE;
        SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
        SCSSCKDT3.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, CPN_FSTD_MAINTAIN);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB1121_AWA_1121.EXP_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1121_AWA_1121.EXP_DATE;
        SCSSCKDT SCSSCKDT4 = new SCSSCKDT();
        SCSSCKDT4.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, CPN_FSTD_MAINTAIN);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1121_AWA_1121.EXP_DATE <= BPB1121_AWA_1121.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1121_AWA_1121.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1121_AWA_1121.FEE_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB1121_AWA_1121.FEE_CCY;
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                WS_FLD_NO = BPB1121_AWA_1121.FEE_CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if ((BPB1121_AWA_1121.MIN_AMT != 0) 
            && (BPB1121_AWA_1121.MAX_AMT != 0) 
            && (BPB1121_AWA_1121.MIN_AMT > BPB1121_AWA_1121.MAX_AMT)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MIN_AMT_GT_MAX_AMT;
            WS_FLD_NO = BPB1121_AWA_1121.MIN_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_MIN_AMT = BPB1121_AWA_1121.MIN_AMT;
        WS_MAX_AMT = BPB1121_AWA_1121.MAX_AMT;
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'I';
        BPCOFBAS.KEY.FEE_CODE = BPB1121_AWA_1121.FEE_CD;
        S000_CALL_BPZFSBAS();
        WS_FEE_EXG_TYP = BPCOFBAS.VAL.FEE_EXG_TYP;
        WS_UP_AMT = BPCOFBAS.VAL.UP_AMT;
        WS_DWN_AMT = BPCOFBAS.VAL.DWN_AMT;
        if (WS_FEE_EXG_TYP == K_LOCAL_TYPE) {
            WS_BUY_CCY = K_LOCAL_CCY;
        } else {
            WS_BUY_CCY = K_ABROAD_CCY;
        }
        IBS.init(SCCGWA, BPCFX);
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = BPCOFBAS.VAL.RATE_GROUP;
        if ((BPB1121_AWA_1121.MIN_AMT != 0) 
            && (!BPB1121_AWA_1121.FEE_CCY.equalsIgnoreCase(WS_BUY_CCY))) {
            CEP.TRC(SCCGWA, "CHECK AABB MIN AMT");
            BPCFX.BUY_CCY = WS_BUY_CCY;
            BPCFX.SELL_CCY = BPB1121_AWA_1121.FEE_CCY;
            BPCFX.SELL_AMT = BPB1121_AWA_1121.MIN_AMT;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            WS_MIN_AMT = BPCFX.BUY_AMT;
        }
        if ((BPB1121_AWA_1121.MAX_AMT != 0) 
            && (!BPB1121_AWA_1121.FEE_CCY.equalsIgnoreCase(WS_BUY_CCY))) {
            CEP.TRC(SCCGWA, "CHECK BBCC MAX AMT");
            BPCFX.BUY_AMT = 0;
            BPCFX.BUY_CCY = WS_BUY_CCY;
            BPCFX.SELL_CCY = BPB1121_AWA_1121.FEE_CCY;
            BPCFX.SELL_AMT = BPB1121_AWA_1121.MAX_AMT;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            WS_MAX_AMT = BPCFX.BUY_AMT;
        }
        CEP.TRC(SCCGWA, WS_MAX_AMT);
        CEP.TRC(SCCGWA, WS_MIN_AMT);
        CEP.TRC(SCCGWA, WS_UP_AMT);
        CEP.TRC(SCCGWA, WS_DWN_AMT);
        if ((BPB1121_AWA_1121.MIN_AMT != 0) 
            || (BPB1121_AWA_1121.MAX_AMT != 0)) {
            if (WS_MAX_AMT > WS_UP_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MAX_AMT_OVER_UP_AMT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (WS_MIN_AMT < WS_DWN_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MIN_AMT_LESS_DW_AMT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB1121_AWA_1121.CLA_TYP);
        if ((BPB1121_AWA_1121.CLA_TYP != '0') 
            && (BPB1121_AWA_1121.CLA_TYP != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_TYPE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.CLA_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB1121_AWA_1121.AGG_TYP != '0') 
            && (BPB1121_AWA_1121.AGG_TYP != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AGGR_TYPE_ERR;
            WS_FLD_NO = BPB1121_AWA_1121.AGG_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        for (WS_CNT = 1; WS_CNT <= 10 
            && ((BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 0) 
            || (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 0)); WS_CNT += 1) {
            if ((BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].AGG_MTH != '0') 
                && (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].AGG_MTH != '1') 
                && (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].AGG_MTH != '2')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRICE_MTH_ERR;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].AGG_MTH_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB1121_AWA_1121.CLA_TYP == '0') {
            if ((BPB1121_AWA_1121.RAT_INFO[1-1].UP_AMT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INPUT;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[1-1].UP_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, BPB1121_AWA_1121.CLA_TYP);
            for (WS_CNT1 = 2; ((WS_CNT1 <= 10) 
                && (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT != 0)); WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT < BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INC;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT);
            if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 99999999999999.99) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT = WS_CNT1; WS_CNT <= 10; WS_CNT += 1) {
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_CANNOT_INPUT;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        } else {
            if ((BPB1121_AWA_1121.RAT_INFO[1-1].UP_CNT == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_MUST_INPUT;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[1-1].UP_CNT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT1 = 2; ((WS_CNT1 <= 10) 
                && (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT != 0)); WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT < BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_MUST_INC;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT);
            if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 9999) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_ERR;
                WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            for (WS_CNT = WS_CNT1; WS_CNT <= 10; WS_CNT += 1) {
                if (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_CNT_CANNOT_INPUT;
                    WS_FLD_NO = BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        WS_NO = 0;
        for (WS_CNT = 1; (WS_CNT <= 10) 
            && ((BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_AMT != 0) 
            || (BPB1121_AWA_1121.RAT_INFO[WS_CNT-1].UP_CNT != 0)); WS_CNT += 1) {
            WS_NO = (short) (WS_NO + 1);
        }
        CEP.TRC(SCCGWA, CPN_FSTD_MAINTAIN);
        R000_CHECK_RESULT_PROC();
        CEP.TRC(SCCGWA, CPN_FSTD_MAINTAIN);
    }
    public void B020_UPDATE_STD_RATE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSTD);
        BPCOFSTD.FUNC = 'U';
        BPCOFSTD.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER_CN();
        S000_CALL_BPZFSSTD();
    }
    public void R000_TRANS_DATA_PARAMETER_CN() throws IOException,SQLException,Exception {
        BPCOFSTD.FEE_CD = BPB1121_AWA_1121.FEE_CD;
        BPCOFSTD.KEY.REG_CODE = BPB1121_AWA_1121.ORG_CD;
        BPCOFSTD.KEY.CHN_NO = BPB1121_AWA_1121.CHNL_NO;
        BPCOFSTD.KEY.REF_CCY = BPB1121_AWA_1121.REF_CCY;
        BPCOFSTD.VAL.START_AMT = BPB1121_AWA_1121.SAT_AMT;
        BPCOFSTD.VAL.FIX_AMT = BPB1121_AWA_1121.FIX_AMT;
        BPCOFSTD.VAL.FEE_CCY = BPB1121_AWA_1121.FEE_CCY;
        BPCOFSTD.VAL.MIN_AMT = BPB1121_AWA_1121.MIN_AMT;
        BPCOFSTD.VAL.MAX_AMT = BPB1121_AWA_1121.MAX_AMT;
        BPCOFSTD.VAL.CAL_TYPE = BPB1121_AWA_1121.CLA_TYP;
        BPCOFSTD.VAL.AGGR_TYPE = BPB1121_AWA_1121.AGG_TYP;
        BPCOFSTD.DATE.EFF_DATE = BPB1121_AWA_1121.EFF_DATE;
        BPCOFSTD.DATE.EXP_DATE = BPB1121_AWA_1121.EXP_DATE;
        for (WS_CNT1 = 1; WS_CNT1 <= 10; WS_CNT1 += 1) {
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].AGG_MTH = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].AGG_MTH;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].FEE_AMT;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].FEE_PER;
        }
        BPCOFSTD.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFSTD.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B020_UPDATE_STD_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSTD);
        BPCOFSTD.FUNC = 'U';
        BPCOFSTD.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSSTD();
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFSTD.FEE_CD = BPB1121_AWA_1121.FEE_CD;
        BPCOFSTD.KEY.REG_CODE = BPB1121_AWA_1121.ORG_CD;
        BPCOFSTD.KEY.CHN_NO = BPB1121_AWA_1121.CHNL_NO;
        BPCOFSTD.KEY.REF_CCY = BPB1121_AWA_1121.REF_CCY;
        BPCOFSTD.VAL.START_AMT = BPB1121_AWA_1121.SAT_AMT;
        BPCOFSTD.VAL.FEE_CCY = BPB1121_AWA_1121.FEE_CCY;
        BPCOFSTD.VAL.MIN_AMT = BPB1121_AWA_1121.MIN_AMT;
        BPCOFSTD.VAL.MAX_AMT = BPB1121_AWA_1121.MAX_AMT;
        BPCOFSTD.VAL.CAL_TYPE = BPB1121_AWA_1121.CLA_TYP;
        BPCOFSTD.VAL.AGGR_TYPE = BPB1121_AWA_1121.AGG_TYP;
        BPCOFSTD.DATE.EFF_DATE = BPB1121_AWA_1121.EFF_DATE;
        BPCOFSTD.DATE.EXP_DATE = BPB1121_AWA_1121.EXP_DATE;
        for (WS_CNT1 = 1; WS_CNT1 <= 10; WS_CNT1 += 1) {
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_AMT = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_AMT;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].UP_CNT = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].UP_CNT;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_AMT = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].FEE_AMT;
            BPCOFSTD.VAL.FEE_DATA[WS_CNT1-1].FEE_PER = BPB1121_AWA_1121.RAT_INFO[WS_CNT1-1].FEE_PER;
        }
        BPCOFSTD.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFSTD.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSSTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FSTD_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFSTD;
        SCCCALL.ERR_FLDNO = BPB1121_AWA_1121.FEE_CD;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFSBAS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-S-MAINTAIN-FBAS";
        SCCCALL.COMMAREA_PTR = BPCOFBAS;
        SCCCALL.ERR_FLDNO = BPB1121_AWA_1121.FEE_CD;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
