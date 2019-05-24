package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSZHCX {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCITCD_RD;
    DBParm DCTTXTOT_RD;
    DBParm DCTCDDAT_RD;
    brParm DCTCDDAT_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC310";
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I_CNT = 0;
    String WS_CARD_NO = " ";
    String WS_TEMP_CARD_NO = " ";
    char WS_FUNC = ' ';
    int WS_I_AC = 0;
    int WS_P_ROW = 0;
    int WS_L_ROW = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    DCZSZHCX_WS_OUTPUT_FMT WS_OUTPUT_FMT = new DCZSZHCX_WS_OUTPUT_FMT();
    char WS_CARD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRTXTOT DCRTXTOT = new DCRTXTOT();
    CICQCHDC CICQCHDC = new CICQCHDC();
    CICQACRL CICQACRL = new CICQACRL();
    CICQCIAC CICQCIAC = new CICQCIAC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCF310 DCCF310 = new DCCF310();
    String WS_TABLE_CDNO = " ";
    String WS_TABLE_CCY = " ";
    int WS_L_CNT = 0;
    char WS_CD_STS = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSZHCX DCCSZHCX;
    public void MP(SCCGWA SCCGWA, DCCSZHCX DCCSZHCX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSZHCX = DCCSZHCX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSZHCX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCF310);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (WS_FUNC == 'B') {
            CEP.TRC(SCCGWA, "CI-NO");
            B022_CI_QUERY_PROC();
            if (pgmRtn) return;
            B030_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CARD-NO");
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = WS_CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == DCCSZHCX.CARD_STS 
                || DCCSZHCX.CARD_STS == ' ') {
                B020_CAL_OUTDRAW_TOTAMT();
                if (pgmRtn) return;
                B025_GET_CARD_INFO();
                if (pgmRtn) return;
            } else {
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM = 0;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = 0;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = 0;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = 0;
            }
            B030_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSZHCX.CARD_NO);
        CEP.TRC(SCCGWA, DCCSZHCX.CI_NO);
        if (DCCSZHCX.CARD_NO.trim().length() == 0 
            && DCCSZHCX.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSZHCX.CARD_NO.trim().length() > 0) {
            WS_FUNC = 'A';
            WS_CARD_NO = DCCSZHCX.CARD_NO;
        } else {
            WS_FUNC = 'B';
        }
    }
    public void B021_CI_PROC() throws IOException,SQLException,Exception {
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.DATA.CI_NO = DCCSZHCX.CI_NO;
        CICQCIAC.FUNC = '1';
        CICQCIAC.DATA.FRM_APP = "DC";
        WS_I_AC = 1;
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        while (WS_I_AC <= 100 
            && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I_AC-1].ENTY_NO.trim().length() != 0) {
            if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I_AC-1].ENTY_TYP == '2' 
                || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I_AC-1].ENTY_TYP == '5' 
                || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I_AC-1].ENTY_TYP == '6' 
                || CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I_AC-1].ENTY_TYP == '7') {
                WS_CARD_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I_AC-1].ENTY_NO;
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = WS_CARD_NO;
                S000_CALL_DCZUCINF();
                if (pgmRtn) return;
                if (DCCUCINF.CARD_STS != 'C') {
                    B020_CAL_OUTDRAW_TOTAMT();
                    if (pgmRtn) return;
                    B025_GET_CARD_INFO();
                    if (pgmRtn) return;
                    B030_DATA_OUTPUT_MPAG();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, DCCF310);
                }
            }
            WS_I_AC += 1;
        }
    }
    public void B022_CI_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.CARD_HLDR_CINO = DCCSZHCX.CI_NO;
        DCRCDDAT.CARD_STS = DCCSZHCX.CARD_STS;
        T000_GROUP_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (DCCSZHCX.PAGE_ROW == 0) {
            WS_P_ROW = 10;
        } else {
            WS_P_ROW = DCCSZHCX.PAGE_ROW;
        }
        if (WS_L_CNT > 0) {
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM % WS_P_ROW;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = (int) ((WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                WS_L_ROW = WS_P_ROW;
            } else {
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE += 1;
            }
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, DCCSZHCX.PAGE_NUM);
            if (DCCSZHCX.PAGE_NUM == 0) {
                DCCSZHCX.PAGE_NUM += 1;
                CEP.TRC(SCCGWA, DCCSZHCX.PAGE_NUM);
            }
            if (DCCSZHCX.PAGE_NUM >= WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE) {
                CEP.TRC(SCCGWA, ">>>===");
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
            } else {
                CEP.TRC(SCCGWA, "<<<<<<");
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = DCCSZHCX.PAGE_NUM;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
            }
            WS_STR_NUM = ( WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE - 1 ) * WS_P_ROW;
            WS_END_NUM = WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            T000_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_I_CNT <= WS_END_NUM) {
                if (WS_I_CNT > WS_STR_NUM 
                    && WS_I_CNT <= WS_END_NUM) {
                    WS_CNT += 1;
                    WS_CARD_NO = DCRCDDAT.KEY.CARD_NO;
                    IBS.init(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1]);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_O_CARD_NO = DCRCDDAT.KEY.CARD_NO;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_BV_CD_NO = DCRCDDAT.BV_CD_NO;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_CARD_MEDI = DCRCDDAT.CARD_MEDI;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_CLS_PROD_CD = DCRCDDAT.CARD_CLS_PROD;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ISSU_DT = DCRCDDAT.ISSU_DT;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ISSU_BR = DCRCDDAT.ISSU_BR;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_PAY_MTH = 'P';
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_CROS_DR_FLG = '1';
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_CARD_STS = DCRCDDAT.CARD_STS;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_CARD_STSW = DCRCDDAT.CARD_STSW;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_EXP_DT = DCRCDDAT.EXP_DT;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DOUBLE_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ANNUAL_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_CARD_TYP = DCRCDDAT.CARD_TYP;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ECARD_NO = DCRCDDAT.E_CARD_NO;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ANU_FEE_FREE = DCRCDDAT.ANU_FEE_FREE;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
                    IBS.init(SCCGWA, BPCPOCAC);
                    BPCPOCAC.INFO.FUNC = 'I';
                    BPCPOCAC.DATA_INFO.AC = WS_CARD_NO;
                    S000_CALL_BPZPOCAC();
                    if (pgmRtn) return;
                    if (BPCPOCAC.DATA_INFO.OTH_ID_TYP.trim().length() == 0 
                        && BPCPOCAC.DATA_INFO.OTH_ID_NO.trim().length() == 0 
                        && BPCPOCAC.DATA_INFO.OTH_PRT_NM.trim().length() == 0) {
                        WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_AGEN_FLG = 'N';
                    } else {
                        WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_AGEN_FLG = 'Y';
                    }
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ISSU_TLR = BPCPOCAC.DATA_INFO.OPEN_TLR;
                    IBS.init(SCCGWA, CICQCHDC);
                    CICQCHDC.FUNC = 'O';
                    CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
                    CICQCHDC.DATA.N_ENTY_TYP = '2';
                    S000_CALL_CIZQCHDC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQCHDC.DATA.O_AGR_NO);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_OLD_CARD = CICQCHDC.DATA.O_AGR_NO;
                    IBS.init(SCCGWA, CICQACRL);
                    CICQACRL.FUNC = 'I';
                    CICQACRL.DATA.REL_AC_NO = WS_CARD_NO;
                    CICQACRL.DATA.AC_REL = "12";
                    S000_CALL_CIZQACRL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
                    if (CICQACRL.O_DATA.O_AC_NO.trim().length() == 0) {
                        WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ALIGN_FLG = 'N';
                    } else {
                        WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_ALIGN_FLG = 'Y';
                    }
                    B020_CAL_OUTDRAW_TOTAMT();
                    if (pgmRtn) return;
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_OUT_TOT_AMT = DCRTXTOT.YLY_TOT_AMT;
                }
                T000_READNEXT_DCTCDDAT();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
        } else {
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM = 0;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = 0;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = 0;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = 0;
        }
    }
    public void B020_CAL_OUTDRAW_TOTAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = WS_CARD_NO;
        DCRTXTOT.KEY.REGN_TYP = '1';
        DCRTXTOT.KEY.CHNL_NO = "10301";
        DCRTXTOT.KEY.TXN_TYPE = "01";
        DCRTXTOT.KEY.LMT_CCY = "156";
        T000_READ_DCTTXTOT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRTXTOT.YLY_TOT_AMT);
    }
    public void B025_GET_CARD_INFO() throws IOException,SQLException,Exception {
        DCCF310.F310FMT.CARD_NO = DCCUCINF.CARD_NO;
        DCCF310.F310FMT.BV_CD_NO = DCCUCINF.BV_CD_NO;
        DCCF310.F310FMT.CARD_MEDI = DCCUCINF.CARD_MEDI;
        DCCF310.F310FMT.CLS_PROD_CD = DCCUCINF.CLS_PROD_CD;
        DCCF310.F310FMT.PAY_MTH = 'P';
        DCCF310.F310FMT.CROS_DR_FLG = '1';
        DCCF310.F310FMT.CARD_STS = DCCUCINF.CARD_STS;
        DCCF310.F310FMT.CARD_STSW = DCCUCINF.CARD_STSW;
        DCCF310.F310FMT.DOUBLE_FREE_FLG = DCCUCINF.DOUBLE_FREE_FLG;
        DCCF310.F310FMT.ANNUAL_FEE_FREE = DCCUCINF.ANNUAL_FEE_FREE;
        DCCF310.F310FMT.CARD_TYP = DCCUCINF.CARD_TYP;
        DCCF310.F310FMT.OPEN_CHNL = DCCUCINF.OPEN_CHNL;
        DCCF310.F310FMT.ANU_FEE_FREE = DCCUCINF.ANU_FEE_FREE;
        CEP.TRC(SCCGWA, DCCUCINF.ANU_FEE_FREE);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ANU_FEE_FREE);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_TYP);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CARD_TYP);
        IBS.init(SCCGWA, BPCPOCAC);
        BPCPOCAC.INFO.FUNC = 'I';
        BPCPOCAC.DATA_INFO.AC = WS_CARD_NO;
        S000_CALL_BPZPOCAC();
        if (pgmRtn) return;
        if (DCCUCINF.ADSC_TYPE == 'P') {
            if (BPCPOCAC.DATA_INFO.OTH_ID_TYP.trim().length() == 0 
                && BPCPOCAC.DATA_INFO.OTH_ID_NO.trim().length() == 0 
                && BPCPOCAC.DATA_INFO.OTH_PRT_NM.trim().length() == 0) {
                DCCF310.F310FMT.AGEN_FLG = 'N';
            } else {
                DCCF310.F310FMT.AGEN_FLG = 'Y';
            }
        } else {
            DCCF310.F310FMT.AGEN_FLG = 'N';
        }
        CEP.TRC(SCCGWA, DCCF310.F310FMT.AGEN_FLG);
        DCCF310.F310FMT.ISSU_BR = DCCUCINF.ISSU_BR;
        DCCF310.F310FMT.OWN_BR = DCCUCINF.ISSU_BR;
        DCCF310.F310FMT.ISSU_DT = DCCUCINF.ISSU_DT;
        DCCF310.F310FMT.ISSU_TLR = BPCPOCAC.DATA_INFO.OPEN_TLR;
        DCCF310.F310FMT.EXP_DT = DCCUCINF.EXP_DT;
        DCCF310.F310FMT.ECARD_NO = DCCUCINF.E_CARD_NO;
        if (DCCUCINF.CARD_PROD_FLG == 'S') {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = DCCSZHCX.CARD_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            DCCF310.F310FMT.ECARD_NO = DCRCITCD.E_CARD_NO;
            if (DCRCITCD.STS == '1') {
                DCCF310.F310FMT.OWN_BR = DCRCITCD.OWN_BR;
            }
        }
        IBS.init(SCCGWA, CICQCHDC);
        CICQCHDC.FUNC = 'O';
        CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
        CICQCHDC.DATA.N_ENTY_TYP = '2';
        S000_CALL_CIZQCHDC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQCHDC.DATA.O_AGR_NO);
        DCCF310.F310FMT.OLD_CARD = CICQCHDC.DATA.O_AGR_NO;
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = WS_CARD_NO;
        CICQACRL.DATA.AC_REL = "12";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.O_DATA.O_AC_NO.trim().length() == 0) {
            DCCF310.F310FMT.ALIGN_FLG = 'N';
        } else {
            DCCF310.F310FMT.ALIGN_FLG = 'Y';
        }
        DCCF310.F310FMT.OUT_TOT_AMT = DCRTXTOT.YLY_TOT_AMT;
        IBS.init(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[1-1]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCF310);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_OUTPUT_FMT.WS_OUT_DATA[1-1]);
        WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM = 1;
        WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = 1;
        WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = 1;
        WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
        WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = 1;
        CEP.TRC(SCCGWA, DCCF310);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CARD_NO);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.BV_CD_NO);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CARD_MEDI);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CLS_PROD_CD);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ISSU_DT);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ISSU_BR);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ISSU_TLR);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.PAY_MTH);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CARD_STS);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CARD_STSW);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.EXP_DT);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.DOUBLE_FREE_FLG);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ANNUAL_FEE_FREE);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.CARD_TYP);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ALIGN_FLG);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.AGEN_FLG);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.ECARD_NO);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.OWN_BR);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.OLD_CARD);
        CEP.TRC(SCCGWA, DCCF310.F310FMT.OPEN_CHNL);
    }
    public void B030_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 2392;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_DATA_OUTPUT_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DCCF310.F310FMT);
        SCCMPAG.DATA_LEN = 237;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF       ", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC, true);
        CEP.TRC(SCCGWA, CICQCHDC.RC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL, true);
        CEP.TRC(SCCGWA, CICQACRL.RC);
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR ", CICQCIAC, true);
        CEP.TRC(SCCGWA, CICQCIAC.RC);
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-OCAC-INFO", BPCPOCAC, true);
        CEP.TRC(SCCGWA, BPCPOCAC.RC.RC_CODE);
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DCTTXTOT() throws IOException,SQLException,Exception {
        DCTTXTOT_RD = new DBParm();
        DCTTXTOT_RD.TableName = "DCTTXTOT";
        IBS.READ(SCCGWA, DCRTXTOT, DCTTXTOT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_GROUP_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-L-CNT=COUNT(*)";
        DCTCDDAT_RD.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND ACNO_TYPE < > ' ' "
            + "AND ( CARD_STS = :DCRCDDAT.CARD_STS "
            + "OR :DCRCDDAT.CARD_STS = ' ' )";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND ACNO_TYPE < > ' ' "
            + "AND ( CARD_STS = :DCRCDDAT.CARD_STS "
            + "OR :DCRCDDAT.CARD_STS = ' ' )";
        DCTCDDAT_BR.rp.order = "CARD_HLDR_CINO";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_I_CNT += 1;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
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
