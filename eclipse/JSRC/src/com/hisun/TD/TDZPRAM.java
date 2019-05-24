package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPRAM {
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTOTHE_RD;
    brParm TDTOTHE_BR = new brParm();
    boolean pgmRtn = false;
    int WS_XSDT1 = 0;
    int WS_XSDT2 = 0;
    int WS_XSDT3 = 0;
    int WS_XSDT4 = 0;
    String WS_SQL_SMK = " ";
    String K_I_OUTPUT_FMT = "TD542";
    int K_SCR_ROW_NO = 4;
    String WS_MSGID = " ";
    int WS_NUM = 0;
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    int WS_NUM1 = 0;
    int WS_NUM2 = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    short WS_CNT = 0;
    char WS_CCY_FOUND = ' ';
    TDZPRAM_CP_PROD_CD CP_PROD_CD = new TDZPRAM_CP_PROD_CD();
    TDZPRAM_WS_FMT WS_FMT = new TDZPRAM_WS_FMT();
    TDZPRAM_WS_TEMP WS_TEMP = new TDZPRAM_WS_TEMP();
    char WS_TBL_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDROTHE TDROTHE = new TDROTHE();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCBINF SCCBINF = new SCCBINF();
    SCCGWA SCCGWA;
    TDCPRAM TDCPRAM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, TDCPRAM TDCPRAM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPRAM = TDCPRAM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZPRAM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, TDROTHE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B210_QUERY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDROTHE.PROD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            CEP.TRC(SCCGWA, TDCPRAM.PROD_CD);
            BPCPQPRD.PRDT_CODE = TDCPRAM.PROD_CD;
            CEP.TRC(SCCGWA, BPCPQPRD);
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            CEP.TRC(SCCGWA, "-START-");
            IBS.init(SCCGWA, TDCQPMP);
            IBS.init(SCCGWA, TDCPRDP);
            TDCQPMP.FUNC = 'I';
            TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
            TDCQPMP.DAT_PTR = TDCPROD;
            S000_CALL_TDZQPMP();
            if (pgmRtn) return;
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
    }
    public void B210_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCPRAM.PROD_CD;
        TDROTHE.KEY.ACTI_NO = TDCPRAM.ACTI_NO;
        WS_XSDT1 = TDCPRAM.SHX_DT;
        WS_XSDT2 = TDCPRAM.SHI_DT;
        WS_XSDT3 = TDCPRAM.SDT;
        WS_XSDT4 = TDCPRAM.DDT;
        if (TDCPRAM.ACTI_NO.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'O';
        } else {
            if (TDCPRAM.PROD_CD.trim().length() > 0) {
                WS_TEMP.WS_BROWSE_FLG = 'P';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR);
            }
        }
        if (WS_XSDT1 == 0) {
            if ("00000000".trim().length() == 0) WS_XSDT1 = 0;
            else WS_XSDT1 = Integer.parseInt("00000000");
            TDROTHE.STR_DATE = WS_XSDT1;
        }
        if (WS_XSDT2 == 0) {
            if ("99991231".trim().length() == 0) WS_XSDT2 = 0;
            else WS_XSDT2 = Integer.parseInt("99991231");
            TDROTHE.END_DATE = WS_XSDT2;
        }
        if (WS_XSDT3 == 0) {
            if ("00000000".trim().length() == 0) WS_XSDT3 = 0;
            else WS_XSDT3 = Integer.parseInt("00000000");
        }
        if (WS_XSDT4 == 0) {
            if ("99991231".trim().length() == 0) WS_XSDT4 = 0;
            else WS_XSDT4 = Integer.parseInt("99991231");
        }
        CEP.TRC(SCCGWA, WS_XSDT1);
        CEP.TRC(SCCGWA, WS_XSDT2);
        CEP.TRC(SCCGWA, WS_XSDT3);
        CEP.TRC(SCCGWA, WS_XSDT4);
        CEP.TRC(SCCGWA, WS_TEMP.WS_BROWSE_FLG);
        CEP.TRC(SCCGWA, TDCPRAM.HD_FLG);
        TDROTHE.ACTI_FLG = TDCPRAM.HD_FLG;
        B000_PAGE_COM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCPRAM.PROD_CD;
        TDROTHE.KEY.ACTI_NO = TDCPRAM.ACTI_NO;
        WS_XSDT1 = TDCPRAM.SHX_DT;
        WS_XSDT2 = TDCPRAM.SHI_DT;
        WS_XSDT3 = TDCPRAM.SDT;
        WS_XSDT4 = TDCPRAM.DDT;
        if (TDCPRAM.ACTI_NO.trim().length() > 0) {
            WS_TEMP.WS_BROWSE_FLG = 'O';
        } else {
            if (TDCPRAM.PROD_CD.trim().length() > 0) {
                WS_TEMP.WS_BROWSE_FLG = 'P';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR);
            }
        }
        if (WS_XSDT1 == 0) {
            if ("00000000".trim().length() == 0) WS_XSDT1 = 0;
            else WS_XSDT1 = Integer.parseInt("00000000");
            TDROTHE.STR_DATE = WS_XSDT1;
        }
        if (WS_XSDT2 == 0) {
            if ("99991231".trim().length() == 0) WS_XSDT2 = 0;
            else WS_XSDT2 = Integer.parseInt("99991231");
            TDROTHE.END_DATE = WS_XSDT2;
        }
        if (WS_XSDT3 == 0) {
            if ("00000000".trim().length() == 0) WS_XSDT3 = 0;
            else WS_XSDT3 = Integer.parseInt("00000000");
        }
        if (WS_XSDT4 == 0) {
            if ("99991231".trim().length() == 0) WS_XSDT4 = 0;
            else WS_XSDT4 = Integer.parseInt("99991231");
        }
        CEP.TRC(SCCGWA, WS_XSDT1);
        CEP.TRC(SCCGWA, WS_XSDT2);
        CEP.TRC(SCCGWA, WS_XSDT3);
        CEP.TRC(SCCGWA, WS_XSDT4);
        CEP.TRC(SCCGWA, WS_TEMP.WS_BROWSE_FLG);
        CEP.TRC(SCCGWA, TDCPRAM.HD_FLG);
        TDROTHE.ACTI_FLG = TDCPRAM.HD_FLG;
        T000_STARTBR_TDTOTHE();
        if (pgmRtn) return;
        T000_READNEXT_TDTOTHE_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        WS_NUM1 = 0;
        WS_NUM2 = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_TBL_FLAG != 'N' 
            && WS_NUM1 <= WS_END_NUM) {
            WS_NUM1 = WS_NUM1 + 1;
            if (WS_NUM1 > WS_STR_NUM 
                && WS_NUM1 <= WS_END_NUM) {
                WS_NUM2 += 1;
                WS_FMT.WS_PROD_CD = TDROTHE.PROD_CD;
                CEP.TRC(SCCGWA, "AAA");
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ACTI_NO = TDROTHE.KEY.ACTI_NO;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CCY = TDROTHE.CCY;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_HD_FLG = TDROTHE.ACTI_FLG;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_BR = TDROTHE.BR;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_SHX_DT = TDROTHE.STR_DATE;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_SHI_DT = TDROTHE.END_DATE;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_SDT = TDROTHE.SDT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_DDT = TDROTHE.DDT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_QC_BAL = TDROTHE.MIN_BAL;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_MAX_BAL = TDROTHE.MAX_BAL;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_MIN_RAT = TDROTHE.MIN_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_MAX_RAT = TDROTHE.MAX_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_XZ_FLG = TDROTHE.RAT_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_PCT_S = TDROTHE.PCT_S;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_FD_RAT = TDROTHE.FLT_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_RUL_CD = TDROTHE.RUL_CD;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_HY_RAT = TDROTHE.CONT_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_YX_TYP = TDROTHE.AFF_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ZQ_TYP = TDROTHE.INT_PERD;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_FX_TERM = TDROTHE.CD_PERD;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ZR_TYP = TDROTHE.TRAN_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_SH_TYP = TDROTHE.REDE_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ZY_TYP = TDROTHE.PLED_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_TQ_TYP = TDROTHE.EARLY_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_TQ_RAT = TDROTHE.PRV_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_WF_NO = TDROTHE.DOCU_NO;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_YQ_TYP = TDROTHE.LATE_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_YQ_RAT = TDROTHE.OVE_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_SY_TYP = TDROTHE.RES_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_SY_RAT = TDROTHE.DUE_RAT;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CHNL_NO = TDROTHE.CHNL_NO;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_MD_TYP = TDROTHE.LST_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_MDQX_TYP = TDROTHE.LSTDT_TYP;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CL_FLG = TDROTHE.SET_FLG;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CQ_TERM = TDROTHE.TERM;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_GRPS_NO = TDROTHE.GRPS_NO;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ADD_AMT = TDROTHE.ADD_BAL;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CDESC = TDROTHE.ACTI_DESC;
                CEP.TRC(SCCGWA, TDROTHE.ADD_BAL);
                if (TDROTHE.ADD_BAL == 0 
                    || TDROTHE.MIN_BAL == 0 
                    || TDROTHE.ACTI_DESC.trim().length() == 0) {
                    R000_GET_ADDAMT();
                    if (pgmRtn) return;
                }
                B010_CHECK_INPUT();
                if (pgmRtn) return;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_PART_NU = TDCPRDP.TXN_PRM.PART_NUM;
                CEP.TRC(SCCGWA, "**********************");
                CEP.TRC(SCCGWA, WS_NUM1);
                CEP.TRC(SCCGWA, WS_NUM2);
                CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_LST_CTL = TDROTHE.LST_CTL;
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_RMK = TDROTHE.RMK;
            }
            T000_READNEXT_TDTOTHE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "000");
        T000_ENDBR_TDTOTHE();
        if (pgmRtn) return;
        if (WS_L_CNT == 0) {
            WS_FMT.WS_TOTAL_NUM = 0;
            WS_FMT.WS_TOTAL_PAGE = 0;
            WS_FMT.WS_CURR_PAGE = 0;
            WS_FMT.WS_LAST_PAGE = 'Y';
            WS_FMT.WS_PAGE_ROW = 0;
        }
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void R000_GET_ADDAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDROTHE.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPRDP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        if (pgmRtn) return;
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_CCY_FOUND = ' ';
        CEP.TRC(SCCGWA, WS_CCY_FOUND);
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDROTHE.CCY)) {
                if (TDROTHE.ADD_BAL == 0) {
                    WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ADD_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                }
                if (TDROTHE.MIN_BAL == 0) {
                    WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_QC_BAL = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                }
                WS_CCY_FOUND = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_CCY_FOUND);
        CEP.TRC(SCCGWA, TDROTHE.CCY);
        CEP.TRC(SCCGWA, WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_ADD_AMT);
        if (WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CDESC.trim().length() == 0) {
            WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CDESC = TDCPROD.CDESC;
            if (TDCPROD.CDESC.trim().length() == 0) {
                WS_FMT.WS_OUT_INFO[WS_NUM2-1].WS_CDESC = TDCPROD.DESC;
            }
        }
        CEP.TRC(SCCGWA, TDCPROD.CDESC);
        CEP.TRC(SCCGWA, TDCPROD.DESC);
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_I_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 2762;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B000_PAGE_COM() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        R000_TRANS_PAGE_ROW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_P_ROW);
        T000_STARTBR_TDTOTHE();
        if (pgmRtn) return;
        T000_READNEXT_TDTOTHE_FIRST();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            WS_L_CNT += 1;
            T000_READNEXT_TDTOTHE();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTOTHE();
        if (pgmRtn) return;
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            WS_FMT.WS_TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = WS_FMT.WS_TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((WS_FMT.WS_TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_FMT.WS_TOTAL_PAGE = WS_T_PAGE;
                if (WS_L_CNT != 0) {
                    WS_L_ROW = WS_P_ROW;
                }
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                WS_FMT.WS_TOTAL_PAGE = WS_T_PAGE + 1;
            }
            if (TDCPRAM.PAGE_NUM != 0) {
                if (TDCPRAM.PAGE_NUM >= WS_FMT.WS_TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    WS_FMT.WS_CURR_PAGE = WS_FMT.WS_TOTAL_PAGE;
                    WS_FMT.WS_LAST_PAGE = 'Y';
                    WS_FMT.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    WS_FMT.WS_CURR_PAGE = TDCPRAM.PAGE_NUM;
                    WS_FMT.WS_LAST_PAGE = 'N';
                    WS_FMT.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, TDCPRAM.PAGE_NUM);
            if (TDCPRAM.PAGE_NUM == 0) {
                WS_FMT.WS_CURR_PAGE = 1;
                if (WS_FMT.WS_TOTAL_PAGE == 1) {
                    WS_FMT.WS_LAST_PAGE = 'Y';
                    WS_FMT.WS_PAGE_ROW = WS_L_ROW;
                } else {
                    WS_FMT.WS_LAST_PAGE = 'N';
                    WS_FMT.WS_PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE);
            WS_P_NUM = WS_FMT.WS_CURR_PAGE - 1;
            CEP.TRC(SCCGWA, WS_P_NUM);
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_STR_NUM);
            WS_END_NUM = WS_FMT.WS_CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_END_NUM);
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_FMT.WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_FMT.WS_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
        }
    }
    public void R000_TRANS_PAGE_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.PAGE_ROW);
        if (TDCPRAM.PAGE_ROW == 0) {
            WS_P_ROW = 5;
        } else {
            if (TDCPRAM.PAGE_ROW > 5) {
                WS_P_ROW = 5;
            } else {
                WS_P_ROW = TDCPRAM.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, TDCPRAM.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC.RC_CODE);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTOTHE() throws IOException,SQLException,Exception {
        if (WS_TEMP.WS_BROWSE_FLG == 'P') {
            CEP.TRC(SCCGWA, TDCPRAM.PROD_CD);
            CEP.TRC(SCCGWA, TDCPRAM.HD_FLG);
            CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
            CEP.TRC(SCCGWA, TDROTHE.ACTI_FLG);
            CEP.TRC(SCCGWA, WS_XSDT1);
            CEP.TRC(SCCGWA, WS_XSDT2);
            if (TDCPRAM.FUNC == 'I') {
                TDTOTHE_BR.rp = new DBParm();
                TDTOTHE_BR.rp.TableName = "TDTOTHE";
                TDTOTHE_BR.rp.where = "PROD_CD = :TDROTHE.PROD_CD "
                    + "AND ACTI_FLG = :TDROTHE.ACTI_FLG "
                    + "AND ( STR_DATE >= :WS_XSDT1 "
                    + "AND STR_DATE <= :WS_XSDT2 ) "
                    + "AND ( END_DATE >= :WS_XSDT1 "
                    + "AND END_DATE <= :WS_XSDT2 ) "
                    + "AND ACTI_NO >= :TDROTHE.KEY.ACTI_NO";
                TDTOTHE_BR.rp.order = "ACTI_NO";
                IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
            } else {
                TDTOTHE_BR.rp = new DBParm();
                TDTOTHE_BR.rp.TableName = "TDTOTHE";
                TDTOTHE_BR.rp.where = "PROD_CD = :TDROTHE.PROD_CD "
                    + "AND ACTI_FLG = :TDROTHE.ACTI_FLG "
                    + "AND STR_DATE <= :WS_XSDT1 "
                    + "AND END_DATE > :WS_XSDT1";
                TDTOTHE_BR.rp.order = "ACTI_NO";
                IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
            }
        } else if (WS_TEMP.WS_BROWSE_FLG == 'O') {
            CEP.TRC(SCCGWA, TDCPRAM.ACTI_NO);
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            CEP.TRC(SCCGWA, "WS-NO");
            TDTOTHE_BR.rp = new DBParm();
            TDTOTHE_BR.rp.TableName = "TDTOTHE";
            TDTOTHE_BR.rp.where = "ACTI_NO = :TDROTHE.KEY.ACTI_NO";
            IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-------STARTBR FOUND------");
            WS_TBL_FLAG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTOTHE_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "TABLE-FOUND");
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "-------TABLE NOT FOUND------");
            WS_TBL_FLAG = 'N';
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        } else {
            CEP.TRC(SCCGWA, "TABLE-OTHER");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTOTHE_BR);
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
