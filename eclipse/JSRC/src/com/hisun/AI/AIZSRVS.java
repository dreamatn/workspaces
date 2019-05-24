package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSRVS {
    AICOGRVS_DATA DATA;
    AICOCRVS_DATA DATA;
    int JIBS_tmp_int;
    brParm AITGRVS_BR = new brParm();
    DBParm AITCRVS_RD;
    DBParm AITGRVS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS ";
    String K_HIS_REMARKS = "ITEM RANGE INFORMATION MAINTAIN";
    String WS_ERR_MSG = " ";
    int WS_RCD_SEQ = 0;
    int WS_RD_NUM = 0;
    short WS_RECORD_NUM = 0;
    AIZSRVS_WS_GRVS_INFO WS_GRVS_INFO = new AIZSRVS_WS_GRVS_INFO();
    AIZSRVS_WS_CRVS_INFO WS_CRVS_INFO = new AIZSRVS_WS_CRVS_INFO();
    AIZSRVS_WS_GRVS_OUT_DATA WS_GRVS_OUT_DATA = new AIZSRVS_WS_GRVS_OUT_DATA();
    AIZSRVS_WS_CRVS_OUT_DATA WS_CRVS_OUT_DATA = new AIZSRVS_WS_CRVS_OUT_DATA();
    AIZSRVS_WS_PART WS_PART = new AIZSRVS_WS_PART();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AIRCRVS AIRCRVS = new AIRCRVS();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGRVS AICRGRVS = new AICRGRVS();
    AICRCRVS AICRCRVS = new AICRCRVS();
    AICRGINF AICRGINF = new AICRGINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICCUST CICCUST = new CICCUST();
    AICOGRVS AICOGRVS = new AICOGRVS();
    AICOCRVS AICOCRVS = new AICOCRVS();
    BPCPQVCH BPCPQVCH = new BPCPQVCH();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    double WS_DB_STR_AMT = 0;
    double WS_DB_END_AMT = 0;
    int WS_DB_STR_DATE = 0;
    int WS_DB_END_DATE = 0;
    int WS_COUNT_NO = 0;
    char WS_GRVS_FLG = ' ';
    char WS_CRVS_FLG = ' ';
    SCCGWA SCCGWA;
    AICSRVS AICSRVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, AICSRVS AICSRVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSRVS = AICSRVS;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "XXA000-MAIN-PROC");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSRVS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXA000-INIT-PROC");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        WS_DB_STR_AMT = AICSRVS.STR_AMT;
        WS_DB_END_AMT = AICSRVS.END_AMT;
        WS_DB_STR_DATE = AICSRVS.STR_DATE;
        WS_DB_END_DATE = AICSRVS.END_DATE;
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AIRCRVS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXA000-MAIN-PROC");
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSRVS.FLG == 'G') {
            if (AICSRVS.FUNC == 'Q') {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B020_QUERY_G_PROCESS();
                    if (pgmRtn) return;
                } else {
                    B030_QUERY_G_PROCESS();
                    if (pgmRtn) return;
                }
            } else if (AICSRVS.FUNC == 'B') {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B060_BROWSE_G_PROCESS();
                    if (pgmRtn) return;
                } else {
                    B070_BROWSE_G_PROCESS();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (AICSRVS.FLG == 'C') {
            if (AICSRVS.FUNC == 'Q') {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B020_QUERY_C_PROCESS();
                    if (pgmRtn) return;
                } else {
                    B030_QUERY_C_PROCESS();
                    if (pgmRtn) return;
                }
            } else if (AICSRVS.FUNC == 'B') {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B060_BROWSE_C_PROCESS();
                    if (pgmRtn) return;
                } else {
                    B070_BROWSE_C_PROCESS();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB010-CHECK-INPUT");
        if (AICSRVS.FUNC == 'B') {
            if (AICSRVS.STR_AMT == 0) {
                AICSRVS.STR_AMT = 0;
                if (AICSRVS.END_AMT == 0) {
                    AICSRVS.END_AMT = 999999999999999.99;
                }
            } else {
                if (AICSRVS.END_AMT == 0) {
                    AICSRVS.END_AMT = AICSRVS.STR_AMT;
                }
            }
            if (AICSRVS.STR_DATE == 0) {
                AICSRVS.STR_DATE = 0;
                if (AICSRVS.END_DATE == 0) {
                    AICSRVS.END_DATE = 99999999;
                }
            } else {
                if (AICSRVS.END_DATE == 0) {
                    AICSRVS.END_DATE = AICSRVS.STR_DATE;
                }
            }
            if (AICSRVS.EXP_STDT == 0) {
                AICSRVS.EXP_STDT = 0;
                if (AICSRVS.EXP_ENDT == 0) {
                    AICSRVS.EXP_ENDT = 99999999;
                }
            } else {
                if (AICSRVS.EXP_ENDT == 0) {
                    AICSRVS.EXP_ENDT = AICSRVS.EXP_STDT;
                }
            }
            CEP.TRC(SCCGWA, AICSRVS.CCY);
            if (AICSRVS.CCY.equalsIgnoreCase("999")) {
                AICSRVS.CCY = "   ";
            }
            if (AICSRVS.PAGE_ROW == 0) {
                AICSRVS.PAGE_ROW = 25;
            }
        }
    }
    public void B020_QUERY_G_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-QUERY-G-PROCESS");
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
        IBS.init(SCCGWA, SCCFMT);
        AICRGRVS.INFO.FUNC = 'Q';
        AIRGRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        AIRGRVS.KEY.RVS_SEQ = AICSRVS.RVS_SEQ;
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.SEQ);
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRGRVS.RETURN_INFO);
        if (AICRGRVS.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GRVS_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'Q';
        AIRGINF.KEY.RVS_NO = AICSRVS.RVS_NO;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
        WS_GRVS_OUT_DATA.WS_G_O_GZZL = AIRGINF.RVS_KND;
        SCCFMT.FMTID = "AIX01";
        WS_GRVS_OUT_DATA.WS_G_O_RVS_NO = AIRGRVS.KEY.RVS_NO;
        WS_GRVS_OUT_DATA.WS_G_O_RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
        WS_GRVS_OUT_DATA.WS_G_O_AC = AIRGRVS.AC;
        WS_GRVS_OUT_DATA.WS_G_O_CCY = AIRGRVS.CCY;
        WS_GRVS_OUT_DATA.WS_G_O_STS = AIRGRVS.STS;
        WS_GRVS_OUT_DATA.WS_G_O_TX_DT = AIRGRVS.TX_DT;
        WS_GRVS_OUT_DATA.WS_G_O_AMT = AIRGRVS.AMT;
        WS_GRVS_OUT_DATA.WS_G_O_CI_NO = AIRGRVS.CI_NO;
        WS_GRVS_OUT_DATA.WS_G_O_TR_CODE = AIRGRVS.TR_CODE;
        WS_GRVS_OUT_DATA.WS_G_O_TR_TELLER = AIRGRVS.TR_TELLER;
        WS_GRVS_OUT_DATA.WS_G_O_TR_BR = AIRGRVS.BR;
        WS_GRVS_OUT_DATA.WS_G_O_SET_NO = AIRGRVS.SET_NO;
        WS_GRVS_OUT_DATA.WS_G_O_SET_SEQ = AIRGRVS.SET_SEQ;
        WS_GRVS_OUT_DATA.WS_G_O_APP = AIRGRVS.APP;
        WS_GRVS_OUT_DATA.WS_G_O_CHNL_NO = AIRGRVS.CHNL_NO;
        WS_GRVS_OUT_DATA.WS_G_O_TX_SUP_TLR = AIRGRVS.TX_SUP_TLR;
        WS_GRVS_OUT_DATA.WS_G_O_PART = AIRGRVS.PART;
        WS_GRVS_OUT_DATA.WS_G_O_CPLX = AIRGRVS.PROD_TYPE;
        WS_GRVS_OUT_DATA.WS_G_O_CPMX = AIRGRVS.CNTR_TYPE;
        WS_GRVS_OUT_DATA.WS_G_O_GZKM = AIRGRVS.ITM;
        WS_GRVS_OUT_DATA.WS_G_O_SXH1 = AIRGRVS.SEQ;
        WS_GRVS_OUT_DATA.WS_G_O_HZFM = AIRGRVS.AC_NO;
        WS_GRVS_OUT_DATA.WS_G_O_HSJG = AIRGRVS.BR;
        WS_GRVS_OUT_DATA.WS_G_O_JDBZ = AIRGRVS.SIGN;
        WS_GRVS_OUT_DATA.WS_G_O_KMZH = AIRGRVS.GLMST;
        WS_GRVS_OUT_DATA.WS_G_O_QXRQ = AIRGRVS.VAL_DT;
        WS_GRVS_OUT_DATA.WS_G_O_JYRQ = AIRGRVS.TX_DT;
        WS_GRVS_OUT_DATA.WS_G_O_SJLX = AIRGRVS.EVENT_TYPE;
        WS_GRVS_OUT_DATA.WS_G_O_YWBH = AIRGRVS.REF_NO;
        WS_GRVS_OUT_DATA.WS_G_O_JYJG = AIRGRVS.TR_BR;
        WS_GRVS_OUT_DATA.WS_G_O_PAY_MAN = AIRGRVS.PAY_MAN;
        WS_GRVS_OUT_DATA.WS_G_O_PAY_BR = AIRGRVS.PAY_BR;
        WS_GRVS_OUT_DATA.WS_G_O_BILL_NO = " ";
        WS_GRVS_OUT_DATA.WS_G_O_THEIR_AC = AIRGRVS.THEIR_AC;
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = AIRGINF.AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_GRVS_OUT_DATA.WS_G_O_GZLB = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        CEP.TRC(SCCGWA, WS_GRVS_OUT_DATA.WS_G_O_GZLB);
        CEP.TRC(SCCGWA, WS_GRVS_OUT_DATA.WS_G_O_CI_NO);
        CEP.TRC(SCCGWA, WS_GRVS_OUT_DATA.WS_G_O_TR_CODE);
        CEP.TRC(SCCGWA, WS_GRVS_OUT_DATA.WS_G_O_TR_TELLER);
        CEP.TRC(SCCGWA, WS_GRVS_OUT_DATA.WS_G_O_TR_BR);
        SCCFMT.DATA_PTR = WS_GRVS_OUT_DATA;
        SCCFMT.DATA_LEN = 714;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_QUERY_G_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB030-QUERY-G-PROCESS");
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, AICSRVS.RVS_NO);
        AIRGRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.SEQ);
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'Q';
        AIRGINF.KEY.RVS_NO = AICSRVS.RVS_NO;
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        if (AICRGINF.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GINF_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
        WS_GRVS_OUT_DATA.WS_G_O_GZZL = AIRGINF.RVS_KND;
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = AIRGINF.AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_GRVS_OUT_DATA.WS_G_O_GZLB = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        if (AICSRVS.RVS_SEQ == 0) {
            CEP.TRC(SCCGWA, "SRVS-RVS-SEQ=NULL");
            T000_STARTBR_AITGRVS();
            if (pgmRtn) return;
            B020_03_OUT_PAGE_TITLE();
            if (pgmRtn) return;
            WS_RD_NUM = 0;
            CEP.TRC(SCCGWA, WS_RD_NUM);
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            while (WS_RD_NUM < AICSRVS.PAGE_ROW 
                && AICRGRVS.RETURN_INFO != 'N') {
                WS_RD_NUM = WS_RD_NUM + 1;
                B030_03_OUT_PAGE_DATA();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITGRVS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "SRVS-RVS-SEQ<>NULL");
            IBS.init(SCCGWA, AICRGRVS);
            IBS.init(SCCGWA, SCCFMT);
            AIRGRVS.KEY.RVS_SEQ = AICSRVS.RVS_SEQ;
            AICRGRVS.INFO.FUNC = 'Q';
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
            S000_CALL_AIZRGRVS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRGRVS.RETURN_INFO);
            if (AICRGRVS.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GRVS_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            AICOGRVS.TOTAL_DATA.LAST_FLG = 'Y';
            WS_RD_NUM = 1;
            AICOGRVS.TOTAL_DATA.TOTAL_PAGE = 1;
            AICOGRVS.TOTAL_NUM_DAT.TOTAL_NUM = 1;
            B030_03_OUT_PAGE_DATA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICOGRVS.TOTAL_DATA.LAST_FLG);
        CEP.TRC(SCCGWA, AICOGRVS.OUTPUT_TITLE.CURR_PAGE);
        CEP.TRC(SCCGWA, AICOGRVS.OUTPUT_TITLE.CURR_PAGE_ROW);
        CEP.TRC(SCCGWA, AICOGRVS.TOTAL_DATA.TOTAL_PAGE);
        CEP.TRC(SCCGWA, AICOGRVS.TOTAL_NUM_DAT.TOTAL_NUM);
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            AICOGRVS.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_RD_NUM;
            DATA = new AICOGRVS_DATA();
            AICOGRVS.DATA.add(DATA);
            AICOGRVS.OUTPUT_TITLE.CURR_PAGE = (short) AICSRVS.PAGE_NUM;
            SCCFMT.FMTID = "AIX01";
            SCCFMT.DATA_PTR = AICOGRVS;
            SCCFMT.DATA_LEN = 34217;
            CEP.TRC(SCCGWA, "1222OUT");
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void B020_QUERY_C_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-QUERY-C-PROCESS");
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRCRVS);
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_PART);
        AICRCRVS.INFO.FUNC = 'Q';
        AIRCRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        AIRCRVS.KEY.RVS_SEQ = AICSRVS.RVS_SEQ;
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        if (AICRCRVS.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CRVS_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'Q';
        AIRGINF.KEY.RVS_NO = AICSRVS.RVS_NO;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
        CEP.TRC(SCCGWA, AIRGINF.RVS_KND);
        CEP.TRC(SCCGWA, AIRCRVS.TX_DT);
        CEP.TRC(SCCGWA, AIRCRVS.SET_NO);
        CEP.TRC(SCCGWA, AIRCRVS.SET_SEQ);
        SCCFMT.FMTID = "AIX01";
        R00_QUERY_CMIB();
        if (pgmRtn) return;
        if (AIRCMIB.RVS_TYP == 'C') {
            WS_CRVS_OUT_DATA.WS_C_O_JDBZ = 'D';
        } else {
            WS_CRVS_OUT_DATA.WS_C_O_JDBZ = 'C';
        }
        WS_CRVS_OUT_DATA.WS_C_O_RVS_NO = AIRCRVS.KEY.RVS_NO;
        WS_CRVS_OUT_DATA.WS_C_O_RVS_SEQ = AIRCRVS.KEY.RVS_SEQ;
        WS_CRVS_OUT_DATA.WS_C_O_AC = AIRCRVS.AC;
        WS_CRVS_OUT_DATA.WS_C_O_CCY = AIRCRVS.CCY;
        WS_CRVS_OUT_DATA.WS_C_O_STS = AIRCRVS.STS;
        WS_CRVS_OUT_DATA.WS_C_O_TX_DT = AIRCRVS.TX_DT;
        WS_CRVS_OUT_DATA.WS_C_O_AMT = AIRCRVS.AMT;
        WS_CRVS_OUT_DATA.WS_C_O_CI_NO = AIRCRVS.CI_NO;
        WS_CRVS_OUT_DATA.WS_C_O_TR_CODE = AIRCRVS.TR_CODE;
        WS_CRVS_OUT_DATA.WS_C_O_TR_TELLER = AIRCRVS.TR_TELLER;
        WS_CRVS_OUT_DATA.WS_C_O_TR_BR = AIRCRVS.TR_BR;
        WS_CRVS_OUT_DATA.WS_C_O_SET_NO = AIRCRVS.SET_NO;
        WS_CRVS_OUT_DATA.WS_C_O_SET_SEQ = AIRCRVS.SET_SEQ;
        WS_CRVS_OUT_DATA.WS_C_O_APP = AIRCRVS.APP;
        WS_CRVS_OUT_DATA.WS_C_O_CHNL_NO = AIRCRVS.CHNL_NO;
        WS_CRVS_OUT_DATA.WS_C_O_TX_SUP_TLR = AIRCRVS.TX_SUP_TLR;
        IBS.CPY2CLS(SCCGWA, AIRCRVS.PART, WS_PART);
        WS_CRVS_OUT_DATA.WS_C_O_PART = WS_PART.WS_O_PART;
        WS_CRVS_OUT_DATA.WS_C_O_HSJG = AIRCRVS.BR;
        WS_CRVS_OUT_DATA.WS_C_O_KMZH = AIRCRVS.GLMST;
        WS_CRVS_OUT_DATA.WS_C_O_QXRQ = AIRCRVS.VAL_DT;
        WS_CRVS_OUT_DATA.WS_C_O_JYRQ = AIRCRVS.TX_DT;
        WS_CRVS_OUT_DATA.WS_C_O_XZKM = AIRCRVS.ITM;
        WS_CRVS_OUT_DATA.WS_C_O_SXH1 = AIRCRVS.SEQ;
        WS_CRVS_OUT_DATA.WS_C_O_KMZH = AIRCRVS.GLMST;
        WS_CRVS_OUT_DATA.WS_C_O_JYJG = AIRCRVS.TR_BR;
        WS_CRVS_OUT_DATA.WS_C_O_GZZL = AIRGINF.RVS_KND;
        WS_CRVS_OUT_DATA.WS_C_O_PAY_MAN = AIRCRVS.PAY_MAN;
        WS_CRVS_OUT_DATA.WS_C_O_PAY_BR = AIRCRVS.PAY_BR;
        WS_CRVS_OUT_DATA.WS_C_O_BILL_NO = " ";
        WS_CRVS_OUT_DATA.WS_C_O_THEIR_AC = WS_PART.WS_O_THEIR_AC;
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = AIRGINF.AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_CRVS_OUT_DATA.WS_C_O_GZLB = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_RVS_NO);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_RVS_SEQ);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_AC);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_CCY);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_STS);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_TX_DT);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_JYRQ);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_AMT);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_CI_NO);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_TR_CODE);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_TR_TELLER);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_TR_BR);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_SET_NO);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_SET_SEQ);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_APP);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_CHNL_NO);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_TX_SUP_TLR);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_PART);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_CPLX);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_CPMX);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_GZLB);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_XZKM);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_SXH1);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_GZZL);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_HZFM);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_HSJG);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_JDBZ);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_KMZH);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_QXRQ);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_SJLX);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_YWBH);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_JYJG);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_JYRQ);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_THEIR_AC);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_PAY_MAN);
        CEP.TRC(SCCGWA, WS_CRVS_OUT_DATA.WS_C_O_PAY_BR);
        SCCFMT.DATA_PTR = WS_CRVS_OUT_DATA;
        SCCFMT.DATA_LEN = 714;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_QUERY_C_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB030-QUERY-C-PROCESS");
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRCRVS);
        IBS.init(SCCGWA, SCCFMT);
        AIRCRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRCRVS.SEQ);
        CEP.TRC(SCCGWA, AIRCRVS.SEQ);
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'Q';
        AIRGINF.KEY.RVS_NO = AICSRVS.RVS_NO;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
        WS_GRVS_OUT_DATA.WS_G_O_GZZL = AIRGINF.RVS_KND;
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = AIRGINF.AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_GRVS_OUT_DATA.WS_G_O_GZLB = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        CEP.TRC(SCCGWA, AICSRVS.RVS_SEQ);
        if (AICSRVS.RVS_SEQ == 0) {
            AICRCRVS.INFO.FUNC = 'B';
            AICRCRVS.INFO.OPT = '6';
            IBS.init(SCCGWA, AIRCRVS);
            IBS.init(SCCGWA, AICRCRVS);
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
            B020_04_OUT_PAGE_TITLE();
            if (pgmRtn) return;
            WS_RD_NUM = 0;
            CEP.TRC(SCCGWA, WS_RD_NUM);
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            while (WS_RD_NUM < AICSRVS.PAGE_ROW 
                && AICRCRVS.RETURN_INFO != 'N') {
                WS_RD_NUM = WS_RD_NUM + 1;
                R00_QUERY_CMIB();
                if (pgmRtn) return;
                if (AIRCMIB.RVS_TYP == 'C') {
                    WS_CRVS_OUT_DATA.WS_C_O_JDBZ = 'D';
                } else {
                    WS_CRVS_OUT_DATA.WS_C_O_JDBZ = 'C';
                }
                B030_04_OUT_PAGE_DATA();
                if (pgmRtn) return;
            }
            AICRCRVS.INFO.OPT = 'E';
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, AICSRVS.RVS_SEQ);
            IBS.init(SCCGWA, AIRCRVS);
            IBS.init(SCCGWA, AICRCRVS);
            IBS.init(SCCGWA, SCCFMT);
            AIRCRVS.KEY.RVS_SEQ = AICSRVS.RVS_SEQ;
            AICRCRVS.INFO.FUNC = 'Q';
            CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRCRVS.SEQ);
            CEP.TRC(SCCGWA, AIRCRVS.SEQ);
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRCRVS.RETURN_INFO);
            if (AICRCRVS.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CRVS_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_RD_NUM = 1;
            R00_QUERY_CMIB();
            if (pgmRtn) return;
            if (AIRCMIB.RVS_TYP == 'C') {
                WS_CRVS_OUT_DATA.WS_C_O_JDBZ = 'D';
            } else {
                WS_CRVS_OUT_DATA.WS_C_O_JDBZ = 'C';
            }
            B030_04_OUT_PAGE_DATA();
            if (pgmRtn) return;
        }
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            AICOCRVS.OUTPUT_TITLE.CURR_PAGE_ROW = WS_RD_NUM;
            DATA = new AICOCRVS_DATA();
            AICOCRVS.DATA.add(DATA);
            AICOCRVS.OUTPUT_TITLE.CURR_PAGE = AICSRVS.PAGE_NUM;
            SCCFMT.FMTID = "AIX01";
            SCCFMT.DATA_PTR = AICOCRVS;
            SCCFMT.DATA_LEN = 32822;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R00_QUERY_CMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AIRCRVS.BOOK_FLG;
        AIRCMIB.KEY.BR = AIRCRVS.BR;
        AIRCMIB.KEY.ITM = AIRCRVS.ITM;
        AIRCMIB.KEY.SEQ = AIRCRVS.SEQ;
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        if (AICPCMIB.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AIRCRVS.BOOK_FLG;
            AIRCMIB.KEY.BR = AIRCRVS.BR;
            AIRCMIB.KEY.ITM = AIRCRVS.ITM;
            AIRCMIB.KEY.SEQ = 999999;
            AICPCMIB.POINTER = AIRCMIB;
            AICPCMIB.REC_LEN = 407;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            if (AICPCMIB.RETURN_INFO == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
            }
        }
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC);
        if (AICPCMIB.RETURN_INFO != 'N') {
            if (AICPCMIB.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_BROWSE_G_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB060-BROWSE-G-PROCESS");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 414;
        SCCMPAG.SCR_ROW_CNT = 15;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
        AICRGRVS.INFO.FUNC = 'B';
        AICRGRVS.INFO.OPT = '5';
        AIRGRVS.BR = AICSRVS.BR;
        AIRGRVS.ITM = AICSRVS.ITM;
        AIRGRVS.SEQ = AICSRVS.SEQ;
        AIRGRVS.CCY = AICSRVS.CCY;
        AIRGRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        AIRGRVS.STS = AICSRVS.STS;
        AICRGRVS.INFO.STR_AMT = AICSRVS.STR_AMT;
        AICRGRVS.INFO.END_AMT = AICSRVS.END_AMT;
        AICRGRVS.INFO.STR_DATE = AICSRVS.STR_DATE;
        AICRGRVS.INFO.END_DATE = AICSRVS.END_DATE;
        CEP.TRC(SCCGWA, AIRGRVS.BR);
        CEP.TRC(SCCGWA, AIRGRVS.ITM);
        CEP.TRC(SCCGWA, AIRGRVS.SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.CCY);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.STS);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.STR_AMT);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.END_AMT);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.STR_DATE);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.END_DATE);
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        AICRGRVS.INFO.OPT = 'N';
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        while (AICRGRVS.RETURN_INFO != 'N') {
            WS_GRVS_INFO.WS_G_BR = AIRGRVS.BR;
            WS_GRVS_INFO.WS_G_AC = AIRGRVS.AC;
            WS_GRVS_INFO.WS_G_RVS_NO = AIRGRVS.KEY.RVS_NO;
            WS_GRVS_INFO.WS_G_RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
            WS_GRVS_INFO.WS_G_TX_DT = AIRGRVS.TX_DT;
            WS_GRVS_INFO.WS_G_CCY = AIRGRVS.CCY;
            WS_GRVS_INFO.WS_G_AMT = AIRGRVS.AMT;
            WS_GRVS_INFO.WS_G_STS = AIRGRVS.STS;
            WS_GRVS_INFO.WS_G_CI_NO = AIRGRVS.CI_NO;
            WS_GRVS_INFO.WS_G_TR_TELLER = AIRGRVS.TR_TELLER;
            WS_GRVS_INFO.WS_G_SET_NO = AIRGRVS.SET_NO;
            WS_GRVS_INFO.WS_G_APP = AIRGRVS.APP;
            WS_GRVS_INFO.WS_G_CHNL_NO = AIRGRVS.CHNL_NO;
            CEP.TRC(SCCGWA, AIRGRVS);
            CEP.TRC(SCCGWA, AIRGRVS.AMT);
            CEP.TRC(SCCGWA, AIRGRVS.AMT);
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = AIRGRVS.KEY.RVS_NO;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            WS_GRVS_INFO.WS_G_RVS_EXP = AIRGINF.RVS_EXP;
            WS_GRVS_INFO.WS_G_BAL = AIRGINF.G_BAL;
            if ((AIRGRVS.CI_NO.trim().length() == 0)) {
                WS_GRVS_INFO.WS_G_CI_ENMS = " ";
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_GRVS_INFO.WS_G_CI_ENMS = CICCUST.O_DATA.O_CI_ENM;
            }
            if ((WS_GRVS_INFO.WS_G_RVS_EXP >= AICSRVS.EXP_STDT 
                && WS_GRVS_INFO.WS_G_RVS_EXP <= AICSRVS.EXP_ENDT)) {
                R000_OUTPUT_GRVS_INFO();
                if (pgmRtn) return;
            }
            AICRGRVS.INFO.OPT = 'N';
            S000_CALL_AIZRGRVS();
            if (pgmRtn) return;
        }
        AICRGRVS.INFO.OPT = 'E';
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
    }
    public void B070_BROWSE_G_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB070-BROWSE-G-PROCESS");
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
        AICRGRVS.INFO.FUNC = 'B';
        AICRGRVS.INFO.OPT = '5';
        AIRGRVS.BR = AICSRVS.BR;
        AIRGRVS.ITM = AICSRVS.ITM;
        AIRGRVS.SEQ = AICSRVS.SEQ;
        AIRGRVS.CCY = AICSRVS.CCY;
        AIRGRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        AIRGRVS.STS = AICSRVS.STS;
        AICRGRVS.INFO.STR_AMT = AICSRVS.STR_AMT;
        AICRGRVS.INFO.END_AMT = AICSRVS.END_AMT;
        AICRGRVS.INFO.STR_DATE = AICSRVS.STR_DATE;
        AICRGRVS.INFO.END_DATE = AICSRVS.END_DATE;
        CEP.TRC(SCCGWA, AIRGRVS.BR);
        CEP.TRC(SCCGWA, AIRGRVS.ITM);
        CEP.TRC(SCCGWA, AIRGRVS.SEQ);
        CEP.TRC(SCCGWA, AIRGRVS.CCY);
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGRVS.STS);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.STR_AMT);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.END_AMT);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.STR_DATE);
        CEP.TRC(SCCGWA, AICRGRVS.INFO.END_DATE);
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        AICRGRVS.INFO.OPT = 'N';
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        while (AICRGRVS.RETURN_INFO != 'N') {
            WS_GRVS_INFO.WS_G_BR = AIRGRVS.BR;
            WS_GRVS_INFO.WS_G_AC = AIRGRVS.AC;
            WS_GRVS_INFO.WS_G_RVS_NO = AIRGRVS.KEY.RVS_NO;
            WS_GRVS_INFO.WS_G_RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
            WS_GRVS_INFO.WS_G_TX_DT = AIRGRVS.TX_DT;
            WS_GRVS_INFO.WS_G_CCY = AIRGRVS.CCY;
            WS_GRVS_INFO.WS_G_AMT = AIRGRVS.AMT;
            WS_GRVS_INFO.WS_G_STS = AIRGRVS.STS;
            WS_GRVS_INFO.WS_G_CI_NO = AIRGRVS.CI_NO;
            WS_GRVS_INFO.WS_G_TR_TELLER = AIRGRVS.TR_TELLER;
            WS_GRVS_INFO.WS_G_SET_NO = AIRGRVS.SET_NO;
            WS_GRVS_INFO.WS_G_APP = AIRGRVS.APP;
            WS_GRVS_INFO.WS_G_CHNL_NO = AIRGRVS.CHNL_NO;
            CEP.TRC(SCCGWA, AIRGRVS);
            CEP.TRC(SCCGWA, AIRGRVS.AMT);
            CEP.TRC(SCCGWA, AIRGRVS.AMT);
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = AIRGRVS.KEY.RVS_NO;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            WS_GRVS_INFO.WS_G_RVS_EXP = AIRGINF.RVS_EXP;
            WS_GRVS_INFO.WS_G_BAL = AIRGINF.G_BAL;
            if ((AIRGRVS.CI_NO.trim().length() == 0)) {
                WS_GRVS_INFO.WS_G_CI_ENMS = " ";
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_GRVS_INFO.WS_G_CI_ENMS = CICCUST.O_DATA.O_CI_ENM;
            }
            B020_03_OUT_PAGE_TITLE();
            if (pgmRtn) return;
            WS_RD_NUM = 0;
            CEP.TRC(SCCGWA, WS_RD_NUM);
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            while (WS_RD_NUM < AICSRVS.PAGE_ROW 
                && AICRGRVS.RETURN_INFO != 'N') {
                if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    WS_RD_NUM = WS_RD_NUM + 1;
                }
                if ((WS_GRVS_INFO.WS_G_RVS_EXP >= AICSRVS.EXP_STDT 
                    && WS_GRVS_INFO.WS_G_RVS_EXP <= AICSRVS.EXP_ENDT)) {
                    R000_OUTPUT_GRVS_INFO();
                    if (pgmRtn) return;
                    B020_03_OUT_PAGE_DATA();
                    if (pgmRtn) return;
                }
                AICRGRVS.INFO.OPT = 'N';
                S000_CALL_AIZRGRVS();
                if (pgmRtn) return;
            }
            AICRGRVS.INFO.OPT = 'E';
            S000_CALL_AIZRGRVS();
            if (pgmRtn) return;
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                AICOGRVS.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_RD_NUM;
                DATA = new AICOGRVS_DATA();
                AICOGRVS.DATA.add(DATA);
                AICOGRVS.OUTPUT_TITLE.CURR_PAGE = (short) AICSRVS.PAGE_NUM;
                SCCFMT.FMTID = "AIX01";
                SCCFMT.DATA_PTR = AICOGRVS;
                SCCFMT.DATA_LEN = 34217;
                IBS.FMT(SCCGWA, SCCFMT);
            }
        }
    }
    public void B060_BROWSE_C_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB060-BROWSE-C-PROCESS");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 388;
        SCCMPAG.SCR_ROW_CNT = 15;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRCRVS);
        AICRCRVS.INFO.FUNC = 'B';
        AICRCRVS.INFO.OPT = '2';
        AIRCRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        AIRCRVS.BR = AICSRVS.BR;
        AIRCRVS.ITM = AICSRVS.ITM;
        AIRCRVS.SEQ = AICSRVS.SEQ;
        AIRCRVS.CCY = AICSRVS.CCY;
        AICRCRVS.INFO.STR_AMT = AICSRVS.STR_AMT;
        AICRCRVS.INFO.END_AMT = AICSRVS.END_AMT;
        AICRCRVS.INFO.STR_DATE = AICSRVS.STR_DATE;
        AICRCRVS.INFO.END_DATE = AICSRVS.END_DATE;
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRCRVS.BR);
        CEP.TRC(SCCGWA, AIRCRVS.ITM);
        CEP.TRC(SCCGWA, AIRCRVS.SEQ);
        CEP.TRC(SCCGWA, AIRCRVS.CCY);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.STR_AMT);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.END_AMT);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.STR_DATE);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.END_DATE);
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        AICRCRVS.INFO.OPT = 'N';
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        while (AICRCRVS.RETURN_INFO != 'N') {
            WS_CRVS_INFO.WS_C_BR = AIRCRVS.BR;
            WS_CRVS_INFO.WS_C_AC = AIRCRVS.AC;
            WS_CRVS_INFO.WS_C_RVS_NO = AIRCRVS.KEY.RVS_NO;
            WS_CRVS_INFO.WS_C_RVS_SEQ = AIRCRVS.KEY.RVS_SEQ;
            WS_CRVS_INFO.WS_C_TX_DT = AIRCRVS.TX_DT;
            WS_CRVS_INFO.WS_C_CCY = AIRCRVS.CCY;
            WS_CRVS_INFO.WS_C_AMT = AIRCRVS.AMT;
            WS_CRVS_INFO.WS_C_STS = AIRCRVS.STS;
            WS_CRVS_INFO.WS_C_CI_NO = AIRCRVS.CI_NO;
            WS_CRVS_INFO.WS_C_TR_TELLER = AIRCRVS.TR_TELLER;
            WS_CRVS_INFO.WS_C_SET_NO = AIRCRVS.SET_NO;
            WS_CRVS_INFO.WS_C_APP = AIRCRVS.APP;
            WS_CRVS_INFO.WS_C_CHNL_NO = AIRCRVS.CHNL_NO;
            if ((AIRCRVS.CI_NO.trim().length() == 0)) {
                WS_CRVS_INFO.WS_C_CI_ENMS = " ";
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_CRVS_INFO.WS_C_CI_ENMS = CICCUST.O_DATA.O_CI_ENM;
            }
            R000_OUTPUT_CRVS_INFO();
            if (pgmRtn) return;
            AICRCRVS.INFO.OPT = 'N';
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
        }
        AICRCRVS.INFO.OPT = 'E';
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
    }
    public void B070_BROWSE_C_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB070-BROWSE-C-PROCESS");
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRCRVS);
        AICRCRVS.INFO.FUNC = 'B';
        AICRCRVS.INFO.OPT = '2';
        AIRCRVS.KEY.RVS_NO = AICSRVS.RVS_NO;
        AIRCRVS.BR = AICSRVS.BR;
        AIRCRVS.ITM = AICSRVS.ITM;
        AIRCRVS.SEQ = AICSRVS.SEQ;
        AIRCRVS.CCY = AICSRVS.CCY;
        AICRCRVS.INFO.STR_AMT = AICSRVS.STR_AMT;
        AICRCRVS.INFO.END_AMT = AICSRVS.END_AMT;
        AICRCRVS.INFO.STR_DATE = AICSRVS.STR_DATE;
        AICRCRVS.INFO.END_DATE = AICSRVS.END_DATE;
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRCRVS.BR);
        CEP.TRC(SCCGWA, AIRCRVS.ITM);
        CEP.TRC(SCCGWA, AIRCRVS.SEQ);
        CEP.TRC(SCCGWA, AIRCRVS.CCY);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.STR_AMT);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.END_AMT);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.STR_DATE);
        CEP.TRC(SCCGWA, AICRCRVS.INFO.END_DATE);
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        AICRCRVS.INFO.OPT = 'N';
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        while (AICRCRVS.RETURN_INFO != 'N') {
            WS_CRVS_INFO.WS_C_BR = AIRCRVS.BR;
            WS_CRVS_INFO.WS_C_AC = AIRCRVS.AC;
            WS_CRVS_INFO.WS_C_RVS_NO = AIRCRVS.KEY.RVS_NO;
            WS_CRVS_INFO.WS_C_RVS_SEQ = AIRCRVS.KEY.RVS_SEQ;
            WS_CRVS_INFO.WS_C_TX_DT = AIRCRVS.TX_DT;
            WS_CRVS_INFO.WS_C_CCY = AIRCRVS.CCY;
            WS_CRVS_INFO.WS_C_AMT = AIRCRVS.AMT;
            WS_CRVS_INFO.WS_C_STS = AIRCRVS.STS;
            WS_CRVS_INFO.WS_C_CI_NO = AIRCRVS.CI_NO;
            WS_CRVS_INFO.WS_C_TR_TELLER = AIRCRVS.TR_TELLER;
            WS_CRVS_INFO.WS_C_SET_NO = AIRCRVS.SET_NO;
            WS_CRVS_INFO.WS_C_APP = AIRCRVS.APP;
            WS_CRVS_INFO.WS_C_CHNL_NO = AIRCRVS.CHNL_NO;
            if ((AIRCRVS.CI_NO.trim().length() == 0)) {
                WS_CRVS_INFO.WS_C_CI_ENMS = " ";
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_CRVS_INFO.WS_C_CI_ENMS = CICCUST.O_DATA.O_CI_ENM;
            }
            B020_04_OUT_PAGE_TITLE();
            if (pgmRtn) return;
            WS_RD_NUM = 0;
            CEP.TRC(SCCGWA, WS_RD_NUM);
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            while (WS_RD_NUM < AICSRVS.PAGE_ROW 
                && AICRGRVS.RETURN_INFO != 'N') {
                if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    WS_RD_NUM = WS_RD_NUM + 1;
                }
                R000_OUTPUT_CRVS_INFO();
                if (pgmRtn) return;
                B020_04_OUT_PAGE_DATA();
                if (pgmRtn) return;
                AICRCRVS.INFO.OPT = 'N';
                S000_CALL_AIZRCRVS();
                if (pgmRtn) return;
            }
            AICRCRVS.INFO.OPT = 'E';
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                AICOCRVS.OUTPUT_TITLE.CURR_PAGE_ROW = WS_RD_NUM;
                DATA = new AICOCRVS_DATA();
                AICOCRVS.DATA.add(DATA);
                AICOCRVS.OUTPUT_TITLE.CURR_PAGE = AICSRVS.PAGE_NUM;
                SCCFMT.FMTID = "AIX01";
                SCCFMT.DATA_PTR = AICOCRVS;
                SCCFMT.DATA_LEN = 32822;
                IBS.FMT(SCCGWA, SCCFMT);
            }
        }
    }
    public void B020_03_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-03-OUT-PAGE-TITLE");
        if (AICSRVS.PAGE_NUM == 0) {
            T030_COUNT_AITGRVS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            AICOGRVS.TOTAL_NUM_DAT.TOTAL_NUM = (short) WS_COUNT_NO;
            CEP.TRC(SCCGWA, "WLL11");
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            if (AICSRVS.PAGE_ROW == 0) {
                AICSRVS.PAGE_ROW = 10;
            }
            WS_RECORD_NUM = (short) (AICOGRVS.TOTAL_NUM_DAT.TOTAL_NUM % AICSRVS.PAGE_ROW);
            AICOGRVS.TOTAL_DATA.TOTAL_PAGE = (short) ((AICOGRVS.TOTAL_NUM_DAT.TOTAL_NUM - WS_RECORD_NUM) / AICSRVS.PAGE_ROW);
            CEP.TRC(SCCGWA, "WLL66");
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            CEP.TRC(SCCGWA, "WLL88");
            CEP.TRC(SCCGWA, AICOGRVS.TOTAL_NUM_DAT.TOTAL_NUM);
            CEP.TRC(SCCGWA, AICOGRVS.TOTAL_DATA.TOTAL_PAGE);
            if (WS_RECORD_NUM > 0) {
                AICOGRVS.TOTAL_DATA.TOTAL_PAGE = (short) (AICOGRVS.TOTAL_DATA.TOTAL_PAGE + 1);
            }
        }
        IBS.init(SCCGWA, AICOGRVS.OUTPUT_TITLE);
        AICOGRVS.TOTAL_DATA.LAST_FLG = 'N';
        if (AICOGRVS.TOTAL_DATA.TOTAL_PAGE == AICSRVS.PAGE_NUM) {
            AICOGRVS.TOTAL_DATA.LAST_FLG = 'Y';
        }
        if (AICSRVS.PAGE_NUM > 0) {
            AICSRVS.RCD_SEQ = ( AICSRVS.PAGE_NUM - 1 ) * AICSRVS.PAGE_ROW + 1;
        } else {
            AICSRVS.RCD_SEQ = 1;
        }
        CEP.TRC(SCCGWA, AICSRVS.RCD_SEQ);
        AICRGRVS.RCD_SEQ = AICSRVS.RCD_SEQ;
        T000_READNEXT_AITGRVS_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GRVS-RVS-NO");
    }
    public void B020_03_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-03-OUT-PAGE-DATA");
        DATA.TR_BR = AIRGRVS.BR;
        DATA.AC = AIRGRVS.AC;
        DATA.RVS_NO = AIRGRVS.KEY.RVS_NO;
        DATA.RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
        DATA.TX_DT = AIRGRVS.TX_DT;
        DATA.CCY = AIRGRVS.CCY;
        DATA.AMT = AIRGRVS.AMT;
        DATA.STS = AIRGRVS.STS;
        DATA.CI_NO = AIRGRVS.CI_NO;
        DATA.TR_TELLER = AIRGRVS.TR_TELLER;
        DATA.SET_NO = AIRGRVS.SET_NO;
        DATA.APP = AIRGRVS.APP;
        DATA.CHNL_NO = AIRGRVS.CHNL_NO;
    }
    public void B020_04_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-04-OUT-PAGE-TITLE");
        if (AICSRVS.PAGE_NUM == 0) {
            T000_COUNT_AITCRVS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            AICOCRVS.TOTAL_DATA.TOTAL_NUM = WS_COUNT_NO;
            CEP.TRC(SCCGWA, "WLL11");
            WS_RECORD_NUM = (short) (AICOCRVS.TOTAL_DATA.TOTAL_NUM % AICSRVS.PAGE_ROW);
            AICOCRVS.TOTAL_DATA.TOTAL_PAGE = (int) ((AICOCRVS.TOTAL_DATA.TOTAL_NUM - WS_RECORD_NUM) / AICSRVS.PAGE_ROW);
            CEP.TRC(SCCGWA, "WLL66");
            CEP.TRC(SCCGWA, AICSRVS.PAGE_ROW);
            CEP.TRC(SCCGWA, "WLL88");
            CEP.TRC(SCCGWA, AICOCRVS.TOTAL_DATA.TOTAL_NUM);
            CEP.TRC(SCCGWA, AICOCRVS.TOTAL_DATA.TOTAL_PAGE);
            if (WS_RECORD_NUM > 0) {
                AICOCRVS.TOTAL_DATA.TOTAL_PAGE = AICOCRVS.TOTAL_DATA.TOTAL_PAGE + 1;
            }
        }
        IBS.init(SCCGWA, AICOCRVS.OUTPUT_TITLE);
        AICOCRVS.OUTPUT_TITLE.LAST_FLG = 'N';
        if (AICOCRVS.TOTAL_DATA.TOTAL_PAGE == AICSRVS.PAGE_NUM) {
            AICOCRVS.OUTPUT_TITLE.LAST_FLG = 'Y';
        }
        if (AICSRVS.PAGE_NUM > 0) {
            AICSRVS.RCD_SEQ = ( AICSRVS.PAGE_NUM - 1 ) * AICSRVS.PAGE_ROW + 1;
        } else {
            AICSRVS.RCD_SEQ = 1;
        }
        CEP.TRC(SCCGWA, AICSRVS.RCD_SEQ);
        AICRCRVS.RCD_SEQ = AICSRVS.RCD_SEQ;
        AICRCRVS.INFO.OPT = 'G';
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
    }
    public void B020_04_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-04-OUT-PAGE-DATA");
        DATA.TR_BR = AIRCRVS.BR;
        DATA.AC = AIRCRVS.AC;
        DATA.RVS_NO = AIRCRVS.KEY.RVS_NO;
        DATA.RVS_SEQ = "" + AIRCRVS.KEY.RVS_SEQ;
        JIBS_tmp_int = DATA.RVS_SEQ.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DATA.RVS_SEQ = "0" + DATA.RVS_SEQ;
        DATA.TX_DT = AIRCRVS.TX_DT;
        DATA.CCY = AIRCRVS.CCY;
        DATA.AMT = AIRCRVS.AMT;
        DATA.STS = AIRCRVS.STS;
        DATA.CI_NO = AIRCRVS.CI_NO;
        DATA.TR_TELLER = AIRCRVS.TR_TELLER;
        DATA.SET_NO = AIRCRVS.SET_NO;
        DATA.APP = AIRCRVS.APP;
        DATA.CHNL_NO = AIRCRVS.CHNL_NO;
    }
    public void T000_STARTBR_AITGRVS() throws IOException,SQLException,Exception {
        AITGRVS_BR.rp = new DBParm();
        AITGRVS_BR.rp.TableName = "AITGRVS";
        AITGRVS_BR.rp.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CRVS_FLG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_AITGRVS_FIRST() throws IOException,SQLException,Exception {
        WS_RCD_SEQ = AICRGRVS.RCD_SEQ;
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, AIRGRVS, this, AITGRVS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRVS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGRVS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITGRVS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITGRVS_BR);
    }
    public void T000_COUNT_AITCRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB020-04-OUT-PAGE-DATA");
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.set = "WS-COUNT-NO=COUNT(1)";
        AITCRVS_RD.where = "BR = :AIRCRVS.BR "
            + "AND ITM = :AIRCRVS.ITM "
            + "AND SEQ = :AIRCRVS.SEQ "
            + "AND CCY = :AIRCRVS.CCY "
            + "AND RVS_NO = :AIRCRVS.KEY.RVS_NO "
            + "AND ( AMT BETWEEN :WS_DB_STR_AMT "
            + "AND :WS_DB_END_AMT ) "
            + "AND ( TX_DT BETWEEN :WS_DB_STR_DATE "
            + "AND :WS_DB_END_DATE )";
        IBS.GROUP(SCCGWA, AIRCRVS, this, AITCRVS_RD);
    }
    public void B030_03_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB030-03-OUT-PAGE-DATA");
        CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
        DATA.RVS_NO = AIRGRVS.KEY.RVS_NO;
        DATA.RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
        DATA.AC = AIRGRVS.AC;
        DATA.CCY = AIRGRVS.CCY;
        DATA.STS = AIRGRVS.STS;
        DATA.TX_DT = AIRGRVS.TX_DT;
        DATA.JYRQ = AIRGRVS.TX_DT;
        DATA.AMT = AIRGRVS.AMT;
        DATA.CI_NO = AIRGRVS.CI_NO;
        DATA.TR_CODE = AIRGRVS.TR_CODE;
        DATA.TR_TELLER = AIRGRVS.TR_TELLER;
        DATA.TR_BR = AIRGRVS.TR_BR;
        DATA.SET_NO = AIRGRVS.SET_NO;
        DATA.SET_SEQ = AIRGRVS.SET_SEQ;
        DATA.APP = AIRGRVS.APP;
        DATA.CHNL_NO = AIRGRVS.CHNL_NO;
        DATA.TX_SUP_TLR = AIRGRVS.TX_SUP_TLR;
        DATA.PART = AIRGRVS.PART;
        DATA.CPLX = AIRGRVS.PROD_TYPE;
        DATA.CPMX = AIRGRVS.CNTR_TYPE;
        DATA.GZLB = WS_GRVS_OUT_DATA.WS_G_O_GZLB;
        DATA.GZKM = AIRGRVS.ITM;
        DATA.SXH1 = AIRGRVS.SEQ;
        DATA.GZZL = WS_GRVS_OUT_DATA.WS_G_O_GZZL;
        DATA.HZFM = AIRGRVS.AC_NO;
        DATA.HSJG = AIRGRVS.BR;
        DATA.JDBZ = AIRGRVS.SIGN;
        DATA.KMZH = AIRGRVS.GLMST;
        DATA.QXRQ = AIRGRVS.VAL_DT;
        DATA.SJLX = AIRGRVS.EVENT_TYPE;
        DATA.YWBH = AIRGRVS.REF_NO;
        DATA.JYJG = AIRGRVS.TR_BR;
        DATA.THEIR_AC = AIRGRVS.THEIR_AC;
        DATA.PAY_MAN = AIRGRVS.PAY_MAN;
        DATA.PAY_BR = AIRGRVS.PAY_BR;
        CEP.TRC(SCCGWA, "STARTOUTPUT");
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).RVS_NO);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).RVS_SEQ);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).AC);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).CCY);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).STS);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).TX_DT);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).JYRQ);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).AMT);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).CI_NO);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).TR_CODE);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).TR_TELLER);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).TR_BR);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).SET_NO);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).SET_SEQ);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).APP);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).CHNL_NO);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).TX_SUP_TLR);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).PART);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).CPLX);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).CPMX);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).GZLB);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).GZKM);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).SXH1);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).GZZL);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).HZFM);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).HSJG);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).JDBZ);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).KMZH);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).QXRQ);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).SJLX);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).YWBH);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).JYJG);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).THEIR_AC);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).PAY_MAN);
        CEP.TRC(SCCGWA, AICOGRVS.DATA.get(WS_RD_NUM-1).PAY_BR);
    }
    public void T030_COUNT_AITGRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXT030-COUNT-AITGRVS");
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.set = "WS-COUNT-NO=COUNT(1)";
        AITGRVS_RD.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO";
        IBS.GROUP(SCCGWA, AIRGRVS, this, AITGRVS_RD);
    }
    public void B030_04_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXB030-04-OUT-PAGE-DATA");
        IBS.init(SCCGWA, WS_PART);
        DATA.RVS_NO = AIRCRVS.KEY.RVS_NO;
        DATA.RVS_SEQ = "" + AIRCRVS.KEY.RVS_SEQ;
        JIBS_tmp_int = DATA.RVS_SEQ.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DATA.RVS_SEQ = "0" + DATA.RVS_SEQ;
        DATA.AC = AIRCRVS.AC;
        DATA.CCY = AIRCRVS.CCY;
        DATA.STS = AIRCRVS.STS;
        DATA.TX_DT = AIRCRVS.TX_DT;
        DATA.JYRQ = AIRCRVS.TX_DT;
        DATA.AMT = AIRCRVS.AMT;
        DATA.CI_NO = AIRCRVS.CI_NO;
        DATA.TR_CODE = AIRCRVS.TR_CODE;
        DATA.TR_TELLER = AIRCRVS.TR_TELLER;
        DATA.TR_BR = AIRCRVS.TR_BR;
        DATA.SET_NO = AIRCRVS.SET_NO;
        DATA.SET_SEQ = AIRCRVS.SET_SEQ;
        DATA.APP = AIRCRVS.APP;
        DATA.CHNL_NO = AIRCRVS.CHNL_NO;
        DATA.TX_SUP_TLR = AIRCRVS.TX_SUP_TLR;
        IBS.CPY2CLS(SCCGWA, AIRCRVS.PART, WS_PART);
        DATA.PART = WS_PART.WS_O_PART;
        DATA.CPLX = WS_CRVS_OUT_DATA.WS_C_O_CPLX;
        DATA.CPMX = WS_CRVS_OUT_DATA.WS_C_O_CPMX;
        DATA.GZLB = WS_GRVS_OUT_DATA.WS_G_O_GZLB;
        DATA.XZKM = AIRCRVS.ITM;
        DATA.SXH1 = AIRCRVS.SEQ;
        DATA.GZZL = WS_GRVS_OUT_DATA.WS_G_O_GZZL;
        DATA.HSJG = AIRCRVS.BR;
        DATA.JDBZ = WS_CRVS_OUT_DATA.WS_C_O_JDBZ;
        DATA.KMZH = AIRCRVS.GLMST;
        DATA.QXRQ = AIRCRVS.VAL_DT;
        DATA.SJLX = WS_CRVS_OUT_DATA.WS_C_O_SJLX;
        DATA.YWBH = WS_CRVS_OUT_DATA.WS_C_O_YWBH;
        DATA.JYJG = AIRCRVS.TR_BR;
        DATA.THEIR_AC = AIRCRVS.THEIR_AC;
        DATA.PAY_MAN = AIRCRVS.PAY_MAN;
        DATA.PAY_BR = AIRCRVS.PAY_BR;
    }
    public void T030_COUNT_AITCRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXT000-COUNT-AITCRVS");
        AITCRVS_RD = new DBParm();
        AITCRVS_RD.TableName = "AITCRVS";
        AITCRVS_RD.set = "WS-COUNT-NO=COUNT(1)";
        AITCRVS_RD.where = "RVS_NO = :AIRCRVS.KEY.RVS_NO";
        IBS.GROUP(SCCGWA, AIRCRVS, this, AITCRVS_RD);
    }
    public void R000_OUTPUT_GRVS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXR000-OUTPUT-GRVS-INFO");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_GRVS_INFO);
        SCCMPAG.DATA_LEN = 414;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_CRVS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXR000-OUTPUT-CRVS-INFO");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CRVS_INFO);
        SCCMPAG.DATA_LEN = 388;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZRGRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-CALL-AIZRGRVS");
        AICRGRVS.INFO.POINTER = AIRGRVS;
        AICRGRVS.INFO.REC_LEN = 1113;
        IBS.CALLCPN(SCCGWA, "AI-R-MIAN-GRVS", AICRGRVS);
        if (AICRGRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-CALL-AIZRGINF");
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-CALL-BPZPQVCH");
        CEP.TRC(SCCGWA, BPCPQVCH.DATA.KEY.AC_DATE);
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-VCH", BPCPQVCH);
        CEP.TRC(SCCGWA, BPCPQVCH.RC);
        if (BPCPQVCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQVCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-CALL-AIZPQMIB");
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.CCY);
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-CALL-CIZCUST");
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_AIZRCRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-CALL-AIZRCRVS");
        AICRCRVS.INFO.POINTER = AIRCRVS;
        AICRCRVS.INFO.REC_LEN = 893;
        IBS.CALLCPN(SCCGWA, "AI-R-MIAN-CRVS", AICRCRVS);
        if (AICRCRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXS000-ERR-MSG-PROC");
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
