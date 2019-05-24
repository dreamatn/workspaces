package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSICOT {
    DBParm LNTAGRE_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRCVD_RD;
    brParm LNTSETL_BR = new brParm();
    DBParm LNTPAIP_RD;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTTRAN_RD;
    DBParm LNTPLPI_RD;
    boolean pgmRtn = false;
    int PAGE_ROW = 20;
    String FMT_ID = "LN806";
    LNZSICOT_WS_VARIABLES WS_VARIABLES = new LNZSICOT_WS_VARIABLES();
    LNZSICOT_WS_OUT_RECODE WS_OUT_RECODE = new LNZSICOT_WS_OUT_RECODE();
    LNZSICOT_WS_COND_FLG WS_COND_FLG = new LNZSICOT_WS_COND_FLG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNRSETL LNRSETL = new LNRSETL();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCBALLM_WS_DB_VARS WS_DB_VARS = new LNCBALLM_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCAWAC SCCAWAC;
    LNCSICOT LNCSICOT;
    public void MP(SCCGWA SCCGWA, LNCSICOT LNCSICOT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSICOT = LNCSICOT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSICOT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        LNCSICOT.RC.RC_APP = "LN";
        LNCSICOT.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSICOT.FUN == 'N') {
            B100_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSICOT.FUN == 'Y') {
            B200_GET_CONT_FLG();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B100_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        CEP.TRC(SCCGWA, LNCSICOT.PAGE_NUM);
        if (LNCSICOT.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATA.CURR_PAGE = (short) LNCSICOT.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_VARIABLES.WS_DATA.LAST_PAGE = 'N';
        if (LNCSICOT.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATA.PAGE_ROW = (short) PAGE_ROW;
        } else {
            if (LNCSICOT.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCSICOT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) LNCSICOT.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.CURR_PAGE);
        WS_VARIABLES.WS_DATA.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        WS_VARIABLES.CNT_LIN = WS_VARIABLES.WS_DATA.NEXT_START_NUM - 1;
        WS_VARIABLES.WS_DATA.TOTAL_NUM = 0;
        IBS.init(SCCGWA, LNRSETL);
        LNCSICOT.CON_FLG = 'Y';
        WS_OUT_RECODE.WS_OUT_HEAD.O_ALL_CONSTS = 'Y';
        LNRSETL.AC = LNCSICOT.DD_AC;
        T000_STARTBR_LNTSETL_ATTR();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL_ATTR();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATA.IDX = 1;
        while (WS_COND_FLG.REC_SETL_FLG != 'N') {
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1]);
            T000_READ_LNTCONT_ATTR();
            if (pgmRtn) return;
            T000_READ_LNTICTL_ATTR();
            if (pgmRtn) return;
            if (WS_COND_FLG.REC_CONT_FLG == 'Y' 
                && WS_VARIABLES.WS_DATA.IDX <= WS_VARIABLES.WS_DATA.PAGE_ROW 
                && WS_VARIABLES.WS_DATA.TOTAL_NUM >= WS_VARIABLES.CNT_LIN 
                && !LNRSETL.KEY.CONTRACT_NO.equalsIgnoreCase(WS_VARIABLES.CONT)) {
                R000_GET_BAL();
                if (pgmRtn) return;
                T000_READ_LNTAPRD_ATTR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
                T000_READ_LNTPAIP_ATTR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "001");
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO = LNRSETL.KEY.CONTRACT_NO;
                IBS.init(SCCGWA, LNRAGRE);
                LNRAGRE.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
                LNTAGRE_RD = new DBParm();
                LNTAGRE_RD.TableName = "LNTAGRE";
                IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONT_NO = LNRAGRE.PAPER_NO;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_DRAW_SEQ = LNRAGRE.DRAW_NO;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CCY = LNRCONT.CCY;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROD_CD = LNRCONT.PROD_CD;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_P_AMT = LNRCONT.LN_TOT_AMT;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_B_AMT = WS_VARIABLES.TOT_BAL;
                if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
                    IBS.init(SCCGWA, LNCCNEX);
                    LNCCNEX.COMM_DATA.INQ_CODE = "INQNORP";
                    LNCCNEX.COMM_DATA.LN_AC = LNRCONT.KEY.CONTRACT_NO;
                    LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
                    JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
                    S000_CALL_LNZCNEX();
                    if (pgmRtn) return;
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_N_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
                    CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
                    CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_N_BAL);
                }
                if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
                    IBS.init(SCCGWA, LNCCNEX);
                    LNCCNEX.COMM_DATA.INQ_CODE = "INQOVDP";
                    LNCCNEX.COMM_DATA.LN_AC = LNRCONT.KEY.CONTRACT_NO;
                    LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
                    JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
                    for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
                    S000_CALL_LNZCNEX();
                    if (pgmRtn) return;
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_O_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
                }
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_START_DT = LNRCONT.START_DATE;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_END_DT = LNRCONT.MAT_DATE;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CURR_AMT_S = LNRICTL.CUR_RAT;
                T001_READ_LNTTRAN();
                if (pgmRtn) return;
                WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_F_DT = LNRTRAN.TR_VAL_DTE;
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONSTS = '1';
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONSTS = '2';
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONSTS = '3';
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONSTS = '4';
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if ((!LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    && !LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1"))) {
                    LNCSICOT.CON_FLG = 'N';
                    WS_OUT_RECODE.WS_OUT_HEAD.O_ALL_CONSTS = 'N';
                } else {
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONSTS = '5';
                }
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (LNRAPRD.PAY_MTH == '4' 
                    && JIBS_tmp_str[1].equalsIgnoreCase("0138060")) {
                    B300_CALL_SVR_PROCESS();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, LNRPLPI);
                    LNRPLPI.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
                    LNRPLPI.KEY.REPY_MTH = 'C';
                    WS_DB_VARS.DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_READ_LNTPLPI();
                    if (pgmRtn) return;
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CURR_AMT = LNRPLPI.DUE_REPY_AMT;
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_EX_TERM = (short) (LNRPAIP.PHS_TOT_TERM - LNRPAIP.PHS_CAL_TERM);
                    IBS.init(SCCGWA, LNRRCVD);
                    LNRRCVD.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
                    null.set = "WS-QG-TERM=COUNT(*)";
                    LNTRCVD_RD = new DBParm();
                    LNTRCVD_RD.TableName = "LNTRCVD";
                    LNTRCVD_RD.eqWhere = "CONTRACT_NO";
                    LNTRCVD_RD.where = "SUB_CTA_NO = 0 "
                        + "AND AMT_TYP = 'C' "
                        + "AND TERM_STS < > '0' "
                        + "AND REPY_STS < > '2'";
                    IBS.GROUP(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_QG_TERM = (short) (WS_DB_VARS.QG_TERM);
                    WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_QG_AMT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_AMT;
                }
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CTA_NO);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CCY);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_PROD_CD);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_P_AMT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_B_AMT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_N_BAL);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_N_BAL);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_START_DT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_END_DT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CURR_AMT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CURR_AMT_S);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_QG_TERM);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_QG_AMT);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_EX_TERM);
                CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.WS_DATA.IDX-1].O_CONSTS);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.NEXT_START_NUM);
                WS_VARIABLES.WS_DATA.IDX += 1;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (!LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && !LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                LNCSICOT.CON_FLG = 'N';
                WS_OUT_RECODE.WS_OUT_HEAD.O_ALL_CONSTS = 'N';
            }
            if (WS_COND_FLG.REC_CONT_FLG == 'Y' 
                && !LNRSETL.KEY.CONTRACT_NO.equalsIgnoreCase(WS_VARIABLES.CONT)) {
                WS_VARIABLES.WS_DATA.TOTAL_NUM += 1;
            }
            WS_VARIABLES.CONT = LNRSETL.KEY.CONTRACT_NO;
            T000_READNEXT_LNTSETL_ATTR();
            if (pgmRtn) return;
        }
        WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
        WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATA.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
        if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
            WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
        }
        if (WS_VARIABLES.WS_DATA.CURR_PAGE < WS_VARIABLES.WS_DATA.TOTAL_PAGE) {
            WS_VARIABLES.WS_DATA.LAST_PAGE = 'N';
        } else {
            WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATA.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = (short) (WS_VARIABLES.WS_DATA.IDX - 1);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        R000_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B200_GET_CONT_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNCSICOT.CON_FLG = 'Y';
        LNRSETL.AC = LNCSICOT.DD_AC;
        T000_STARTBR_LNTSETL_ATTR();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL_ATTR();
        if (pgmRtn) return;
        while (WS_COND_FLG.REC_SETL_FLG != 'N' 
            && LNCSICOT.CON_FLG != 'N') {
            T000_READ_LNTCONT_ATTR();
            if (pgmRtn) return;
            if (WS_COND_FLG.REC_CONT_FLG == 'Y') {
                CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
                T000_READ_LNTICTL_ATTR();
                if (pgmRtn) return;
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                } else {
                    LNCSICOT.CON_FLG = 'N';
                }
            }
            T000_READNEXT_LNTSETL_ATTR();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSICOT.CON_FLG);
    }
    public void R000_GET_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQRCVP";
        LNCCNEX.COMM_DATA.LN_AC = LNRCONT.KEY.CONTRACT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
        WS_VARIABLES.TOT_BAL = LNRCONT.LN_TOT_AMT - LNCCNEX.COMM_DATA.INQ_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_BAL);
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            LNCCNEX.RC.RC_APP = LNCBALLM.RC.RC_APP;
            LNCCNEX.RC.RC_RTNCODE = LNCBALLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.col = "CONTRACT_NO";
        LNTSETL_BR.rp.where = "AC = :LNRSETL.AC "
            + "AND SETTLE_TYPE = '2'";
        LNTSETL_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_SETL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_SETL_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void T000_READ_LNTPAIP_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        LNRPAIP.KEY.PHS_NO = LNRICTL.IC_CAL_PHS_NO;
        T000_READ_LNTPAIP();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_PAIP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_PAIP_FLG = 'N';
        }
    }
    public void T000_READ_LNTCONT_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_CONT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_CONT_FLG = 'N';
        }
    }
    public void T000_READ_LNTICTL_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_ICTL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_ICTL_FLG = 'N';
        }
    }
    public void T000_READ_LNTAPRD_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_COND_FLG.REC_APRD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_COND_FLG.REC_APRD_FLG = 'N';
        }
    }
    public void T001_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.PAY_AC1 = LNCSICOT.DD_AC;
        LNRTRAN.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.eqWhere = "CONTRACT_NO,PAY_AC1";
        LNTTRAN_RD.where = "TR_CODE IN ( 4000 , 4100 ) "
            + "AND TXN_TYP = 'T' "
            + "AND TRAN_STS = 'N'";
        LNTTRAN_RD.fst = true;
        LNTTRAN_RD.order = "TR_VAL_DTE DESC";
        IBS.READ(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
    }
    public void T000_READ_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,REPY_MTH";
        LNTPLPI_RD.where = "VAL_DT <= :WS_DB_VARS.DT "
            + "AND DUE_DT > :WS_DB_VARS.DT";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void R000_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 6295;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNRSETL.KEY.CONTRACT_NO;
        LNCSPDQ.FUNC_CODE = 'E';
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ);
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSICOT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSICOT=");
            CEP.TRC(SCCGWA, LNCSICOT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
