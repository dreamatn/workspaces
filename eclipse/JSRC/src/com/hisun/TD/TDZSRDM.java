package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSRDM {
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD500";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    short K_CCY_CNT = 12;
    String WS_MSGID = " ";
    TDZSRDM_CP_PROD_CD CP_PROD_CD = new TDZSRDM_CP_PROD_CD();
    short WS_I = 0;
    short WS_J = 0;
    short WS_CNT = 0;
    short WS_C_T = 0;
    TDZSRDM_WS_CCY_INFO WS_CCY_INFO = new TDZSRDM_WS_CCY_INFO();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDCQPRD TDCQPRD = new TDCQPRD();
    TDCQPRD TDCQPRD1 = new TDCQPRD();
    TDC5000 TDC5000 = new TDC5000();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCPRDP TDCPRDPO = new TDCPRDP();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    TDCSRDM TDCSRDM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSRDM TDCSRDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSRDM = TDCSRDM;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "TDZSRDM");
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSRDM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSRDM);
        CEP.TRC(SCCGWA, TDCSRDM.FUNC);
        if (TDCSRDM.FUNC == 'A') {
            B300_OUTPUT_INF();
        } else if (TDCSRDM.FUNC == 'D'
            || TDCSRDM.FUNC == 'M'
            || TDCSRDM.FUNC == 'I'
            || TDCSRDM.FUNC == '1'
            || TDCSRDM.FUNC == 'Q') {
            B140_INQ_PRD_PARM();
            B300_OUTPUT_INF();
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
        if (TDCSRDM.FUNC == 'A') {
            SCCSUBS.AP_CODE = 12;
            SCCSUBS.TR_CODE = 5005;
            S000_SET_SUBS_TRN();
        } else if (TDCSRDM.FUNC == 'D') {
            SCCSUBS.AP_CODE = 12;
            SCCSUBS.TR_CODE = 5002;
            S000_SET_SUBS_TRN();
        } else if (TDCSRDM.FUNC == 'M') {
            SCCSUBS.AP_CODE = 12;
            SCCSUBS.TR_CODE = 5003;
            S000_SET_SUBS_TRN();
        } else if (TDCSRDM.FUNC == 'I'
            || TDCSRDM.FUNC == '1'
            || TDCSRDM.FUNC == 'Q') {
            SCCSUBS.AP_CODE = 12;
            SCCSUBS.TR_CODE = 5004;
            S000_SET_SUBS_TRN();
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
        S000_SET_SUBS_TRN();
    }
    public void B140_INQ_PRD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPROD);
        TDCQPMP.FUNC = TDCSRDM.FUNC;
        TDCQPMP.PROD_CD = TDCSRDM.PROD_CD;
        TDCQPMP.PROD_CD_M = TDCSRDM.PROD_CD_M;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        CEP.TRC(SCCGWA, "AA");
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        CEP.TRC(SCCGWA, "BB");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        CEP.TRC(SCCGWA, "CC");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        CEP.TRC(SCCGWA, "DD");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        CEP.TRC(SCCGWA, "EE");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        CEP.TRC(SCCGWA, "FF");
        CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM);
        CEP.TRC(SCCGWA, TDCPRDP.INT_PRM);
        CEP.TRC(SCCGWA, TDCPRDP.EXP_PRM);
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM);
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.NO_NOTIFY_FLG);
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.NO_NOTIFY_FLG);
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        if (TDCSRDM.FUNC == 'I' 
            || TDCSRDM.FUNC == 'Q') {
            IBS.init(SCCGWA, TDCQPRD);
            IBS.init(SCCGWA, TDCQPRD1);
            TDCQPRD.FUNC = TDCSRDM.FUNC;
            TDCQPRD.PROD_CD = TDCSRDM.PROD_CD;
            TDCQPRD.DESC = TDCPROD.DESC;
            TDCQPRD.CDESC = TDCPROD.CDESC;
            TDCQPRD.EFF_DT = TDCPROD.EFF_DT;
            TDCQPRD.EXP_DT = TDCPROD.EXP_DT;
            TDCQPRD.PRDMO_CD = TDCPRDP.PRDMO_CD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.TXN_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPRD.TXN_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.INT_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPRD.INT_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.EXP_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPRD.EXP_PRM);
            TDCQPRD.EXP_PRM.NENEW_RUL_CD = TDCPRDP.EXP_PRM.NENEW_RUL_CD;
            CEP.TRC(SCCGWA, TDCPRDP.EXP_PRM.NENEW_RUL_CD);
            CEP.TRC(SCCGWA, "------JIFEITEST---1");
            IBS.init(SCCGWA, WS_CCY_INFO);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1]);
            WS_CNT = K_CCY_CNT;
            for (WS_I = 1; WS_I <= 24 
                && TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.trim().length() != 0; WS_I += 1) {
                if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("156")) {
                    WS_CCY_INFO.WS_CCY_INF[1-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("344")) {
                    WS_CCY_INFO.WS_CCY_INF[2-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("840")) {
                    WS_CCY_INFO.WS_CCY_INF[3-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("978")) {
                    WS_CCY_INFO.WS_CCY_INF[4-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("392")) {
                    WS_CCY_INFO.WS_CCY_INF[5-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("826")) {
                    WS_CCY_INFO.WS_CCY_INF[6-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("446")) {
                    WS_CCY_INFO.WS_CCY_INF[7-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("124")) {
                    WS_CCY_INFO.WS_CCY_INF[8-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("756")) {
                    WS_CCY_INFO.WS_CCY_INF[9-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("702")) {
                    WS_CCY_INFO.WS_CCY_INF[10-1].WS_CCY_NUM = WS_I;
                } else {
                    WS_CNT += 1;
                    WS_CCY_INFO.WS_CCY_INF[WS_CNT-1].WS_CCY_NUM = WS_I;
                }
            }
            WS_J = 1;
            for (WS_I = 1; WS_I <= 24; WS_I += 1) {
                if (WS_CCY_INFO.WS_CCY_INF[WS_I-1].WS_CCY_NUM != 0) {
                    WS_CNT = WS_CCY_INFO.WS_CCY_INF[WS_I-1].WS_CCY_NUM;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPRD.OTH_PRM.CCY_INF[WS_J-1]);
                    WS_J += 1;
                }
            }
            WS_C_T = 1;
            for (WS_I = 1; WS_I <= 24 
                && TDCQPRD.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.trim().length() != 0; WS_I += 1) {
                if (TDCQPRD.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.trim().length() > 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCQPRD.OTH_PRM.CCY_INF[WS_I-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM.CCY_INF[WS_C_T-1]);
                    WS_C_T += 1;
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPRD.OTH_PRM);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.MIN_TERM);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.MAX_TERM);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.MID_FLG);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.CCY_INF[1-1].FD_TOP_IRAT_CD);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.CCY_INF[1-1].FD_TOP_PCT);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.CCY_INF[1-1].EXC_LMT_PROC);
            CEP.TRC(SCCGWA, TDCQPRD.OTH_PRM.CCY_INF[2-1].MIN_CCYC);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_PRD_FMT;
            SCCFMT.DATA_PTR = TDCQPRD;
            SCCFMT.DATA_LEN = 7624;
            IBS.FMT(SCCGWA, SCCFMT);
        } else {
            IBS.init(SCCGWA, TDC5000);
            TDC5000.FUNC = TDCSRDM.FUNC;
            TDC5000.PROD_CD = TDCSRDM.PROD_CD;
            TDC5000.DESC = TDCPROD.DESC;
            TDC5000.CDESC = TDCPROD.CDESC;
            TDC5000.EFF_DT = TDCPROD.EFF_DT;
            TDC5000.EXP_DT = TDCPROD.EXP_DT;
            TDC5000.PRDMO_CD = TDCPRDP.PRDMO_CD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.TXN_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDC5000.TXN_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.INT_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDC5000.INT_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.EXP_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDC5000.EXP_PRM);
            CEP.TRC(SCCGWA, "------JIFEITEST---2");
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1]);
            TDC5000.OTH_PRM.PRD_TYP = TDCPRDP.OTH_PRM.PRD_TYP;
            TDC5000.OTH_PRM.MAX_NUM = TDCPRDP.OTH_PRM.MAX_NUM;
            TDC5000.OTH_PRM.PAY_GRCE = TDCPRDP.OTH_PRM.PAY_GRCE;
            TDC5000.OTH_PRM.PAY_PERD = TDCPRDP.OTH_PRM.PAY_PERD;
            TDC5000.OTH_PRM.PLAN_FLG = TDCPRDP.OTH_PRM.PLAN_FLG;
            TDC5000.OTH_PRM.MIN_TERM = TDCPRDP.OTH_PRM.MIN_TERM;
            TDC5000.OTH_PRM.MAX_TERM = TDCPRDP.OTH_PRM.MAX_TERM;
            TDC5000.OTH_PRM.INT_PRD1 = TDCPRDP.OTH_PRM.INT_PRD1;
            TDC5000.OTH_PRM.INT_PRD2 = TDCPRDP.OTH_PRM.INT_PRD2;
            TDC5000.OTH_PRM.INT_PRD3 = TDCPRDP.OTH_PRM.INT_PRD3;
            TDC5000.OTH_PRM.COMP_FLG = TDCPRDP.OTH_PRM.COMP_FLG;
            TDC5000.OTH_PRM.INTP_FLG = TDCPRDP.OTH_PRM.INTP_FLG;
            TDC5000.OTH_PRM.PENA_FLG = TDCPRDP.OTH_PRM.PENA_FLG;
            TDC5000.OTH_PRM.UNIT_DAY = TDCPRDP.OTH_PRM.UNIT_DAY;
            TDC5000.OTH_PRM.INT_PERD = TDCPRDP.OTH_PRM.INT_PERD;
            TDC5000.OTH_PRM.MAX_GRCE = TDCPRDP.OTH_PRM.MAX_GRCE;
            TDC5000.OTH_PRM.CCY_TYP = TDCPRDP.OTH_PRM.CCY_TYP;
            TDC5000.OTH_PRM.DOCU_FLG = TDCPRDP.OTH_PRM.DOCU_FLG;
            CEP.TRC(SCCGWA, "------JIFEITEST---3");
            WS_CNT = K_CCY_CNT;
            for (WS_I = 1; WS_I <= 24 
                && TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.trim().length() != 0; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC);
                if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("156")) {
                    WS_CCY_INFO.WS_CCY_INF[1-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("344")) {
                    WS_CCY_INFO.WS_CCY_INF[2-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("840")) {
                    WS_CCY_INFO.WS_CCY_INF[3-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("978")) {
                    WS_CCY_INFO.WS_CCY_INF[4-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("392")) {
                    WS_CCY_INFO.WS_CCY_INF[5-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("826")) {
                    WS_CCY_INFO.WS_CCY_INF[6-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("446")) {
                    WS_CCY_INFO.WS_CCY_INF[7-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("124")) {
                    WS_CCY_INFO.WS_CCY_INF[8-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("756")) {
                    WS_CCY_INFO.WS_CCY_INF[9-1].WS_CCY_NUM = WS_I;
                } else if (TDCPRDP.OTH_PRM.CCY_INF[WS_I-1].MIN_CCYC.equalsIgnoreCase("702")) {
                    WS_CCY_INFO.WS_CCY_INF[10-1].WS_CCY_NUM = WS_I;
                } else {
                    WS_CNT += 1;
                    WS_CCY_INFO.WS_CCY_INF[WS_CNT-1].WS_CCY_NUM = WS_I;
                }
            }
            CEP.TRC(SCCGWA, "------JIFEITEST---4");
            CEP.TRC(SCCGWA, WS_I);
            WS_J = 1;
            for (WS_I = 1; WS_I <= 24; WS_I += 1) {
                if (WS_CCY_INFO.WS_CCY_INF[WS_I-1].WS_CCY_NUM != 0) {
                    WS_CNT = WS_CCY_INFO.WS_CCY_INF[WS_I-1].WS_CCY_NUM;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDC5000.OTH_PRM.CCY_INF[WS_J-1]);
                    WS_J += 1;
                }
            }
            CEP.TRC(SCCGWA, TDC5000.OTH_PRM.MIN_TERM);
            CEP.TRC(SCCGWA, TDC5000.OTH_PRM.MAX_TERM);
            CEP.TRC(SCCGWA, TDC5000.OTH_PRM.MID_FLG);
            CEP.TRC(SCCGWA, TDC5000.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
            CEP.TRC(SCCGWA, TDC5000.OTH_PRM.CCY_INF[2-1].MIN_CCYC);
            TDC5000.OTH_PRM.BAL_FLG = TDCPRDP.OTH_PRM.BAL_FLG;
            TDC5000.OTH_PRM.MID_FLG = TDCPRDP.OTH_PRM.MID_FLG;
            TDC5000.OTH_PRM.DELA_FLG = TDCPRDP.OTH_PRM.DELA_FLG;
            TDC5000.OTH_PRM.CPRA_TYP = TDCPRDP.OTH_PRM.CPRA_TYP;
            TDC5000.OTH_PRM.ACTI_FLG = TDCPRDP.OTH_PRM.ACTI_FLG;
            TDC5000.OTH_PRM.FRZ_FLG = TDCPRDP.OTH_PRM.FRZ_FLG;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_PRD_FMT;
            SCCFMT.DATA_PTR = TDC5000;
            SCCFMT.DATA_LEN = 7333;
            CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
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
