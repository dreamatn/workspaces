package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPAIP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    LNZSPAIP_WS_OUT_INFO WS_OUT_INFO;
    DBParm LNTICTL_RD;
    brParm LNTPLPI_BR = new brParm();
    DBParm LNTPLPI_RD;
    brParm LNTPAIP_BR = new brParm();
    DBParm LNTTMPP_RD;
    boolean pgmRtn = false;
    String FMT_CD_1 = "BPX01";
    String FMT_CD_2 = "LNP13";
    String SCSSCLDT = "SCSSCLDT";
    short PAGE_ROW = 20;
    LNZSPAIP_WS_VARIABLES WS_VARIABLES = new LNZSPAIP_WS_VARIABLES();
    LNZSPAIP_WS_OUT_RECODE WS_OUT_RECODE = new LNZSPAIP_WS_OUT_RECODE();
    LNZSPAIP_WS_COND_FLG WS_COND_FLG = new LNZSPAIP_WS_COND_FLG();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRCONT LNRCONT = new LNRCONT();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRTMPP LNRTMPP = new LNRTMPP();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNRICTL LNRICTL = new LNRICTL();
    LNCOPAIP LNCOPAIP = new LNCOPAIP();
    LNCPAIPB LNCPAIPB = new LNCPAIPB();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCILCM LNCILCM = new LNCILCM();
    LNCRATQ LNCRATQ = new LNCRATQ();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCICUT LNCICUT = new LNCICUT();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQCCY_WS_DB_VARS WS_DB_VARS = new BPCQCCY_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNCSPAIP LNCSPAIP;
    public void MP(SCCGWA SCCGWA, LNCSPAIP LNCSPAIP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPAIP = LNCSPAIP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPAIP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNCOPAIP);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCSPAIP.FUNC == '0' 
            || LNCSPAIP.FUNC == '2') {
            BS1_COMP_INST_AMT();
            if (pgmRtn) return;
        }
        if (LNCSPAIP.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCSPAIP.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCSPAIP.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCSPAIP.FUNC == '3'
            || LNCSPAIP.FUNC == '4') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCSPAIP.FUNC == '5') {
            B07_FUNC_BROWSE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FUNC_CODE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNCSPAIP.FUNC != '0' 
            && LNCSPAIP.FUNC != '1' 
            && LNCSPAIP.FUNC != '2' 
            && LNCSPAIP.FUNC != '3' 
            && LNCSPAIP.FUNC != '4' 
            && LNCSPAIP.FUNC != '5') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FUNC_CODE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPAIP.REC_DATA.KEY.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNCRCONT);
            IBS.init(SCCGWA, LNRCONT);
            LNCRCONT.FUNC = 'I';
            LNRCONT.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
            LNCRCONT.REC_PTR = LNRCONT;
            LNCRCONT.REC_LEN = 1304;
            S000_CALL_LNZRCONT();
            if (pgmRtn) return;
            S000_GET_APRD_INF();
            if (pgmRtn) return;
            if (LNCAPRDM.REC_DATA.PAY_MTH != '4') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_REPAY_TYPE, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        if (LNCSPAIP.FUNC == '0' 
            || LNCSPAIP.FUNC == '2' 
            || LNCSPAIP.FUNC == '1') {
            WS_VARIABLES.TOT_TENORS = 1;
            WS_VARIABLES.SCH_END_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
            CEP.TRC(SCCGWA, WS_VARIABLES.TOT_TENORS);
            CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_TOT_TERM);
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '3';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(73 - 1, 73 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_AC_MGM_FEE_Y, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSPAIP.FUNC == '0') {
                CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.PHS_NO);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_PHS_NO);
            } else {
                if (LNCSPAIP.REC_DATA.KEY.PHS_NO < LNCICTLM.REC_DATA.IC_CMP_PHS_NO) {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, LNCRATQ);
            LNCRATQ.COMM_DATA.LN_AC = LNCSPAIP.REC_DATA.KEY.LN_AC;
            LNCRATQ.COMM_DATA.SUF_NO = "" + LNCSPAIP.REC_DATA.KEY.SUF_NO;
            JIBS_tmp_int = LNCRATQ.COMM_DATA.SUF_NO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) LNCRATQ.COMM_DATA.SUF_NO = "0" + LNCRATQ.COMM_DATA.SUF_NO;
            LNCRATQ.COMM_DATA.RATE_TYPE = 'N';
            LNCRATQ.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_LNZRATQ();
            if (pgmRtn) return;
            WS_VARIABLES.N_RATE = LNCRATQ.COMM_DATA.RATE;
        }
        R100_CALL_LNZPAIPB();
        if (pgmRtn) return;
    }
    public void B11_GET_PHS_TOT_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'S';
        LNRPAIP.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        LNRPAIP.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        LNRPAIP.KEY.PHS_NO = LNCSPAIP.REC_DATA.KEY.PHS_NO;
        WS_VARIABLES.PHS_TOT_TERM = 0;
        WS_VARIABLES.MOD_TOT_TERM = 0;
        LNCRPAIP.REC_LEN = 1352;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNCRPAIP.OPT = 'R';
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.LN_ERR_PAIP_NOTFND) 
            && LNRPAIP.KEY.CONTRACT_NO.equalsIgnoreCase(LNCSPAIP.REC_DATA.KEY.LN_AC)) {
            if (LNCSPAIP.FUNC == '2' 
                && LNCSPAIP.REC_DATA.KEY.SUF_NO == LNRPAIP.KEY.SUB_CTA_NO 
                && LNCSPAIP.REC_DATA.KEY.PHS_NO == LNRPAIP.KEY.PHS_NO) {
                WS_VARIABLES.MOD_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
            }
            WS_VARIABLES.PHS_TOT_TERM += LNRPAIP.PHS_TOT_TERM;
            LNCRPAIP.OPT = 'R';
            S000_CALL_LNZPAIP();
            if (pgmRtn) return;
        }
        LNCRPAIP.OPT = 'E';
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCSPAIP.FUNC == '2') {
            WS_VARIABLES.PHS_TOT_TERM = (short) (WS_VARIABLES.PHS_TOT_TERM - WS_VARIABLES.MOD_TOT_TERM);
            WS_VARIABLES.PHS_TOT_TERM += LNCSPAIP.REC_DATA.PHS_TOT_TERM;
        }
        if (LNCSPAIP.FUNC == '0') {
            WS_VARIABLES.PHS_TOT_TERM += LNCSPAIP.REC_DATA.PHS_TOT_TERM;
        }
    }
    public void B11_GET_CONT_REM_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSLNQ);
        LNCSLNQ.COMM_DATA.LN_AC = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNCSLNQ.COMM_DATA.SUF_NO = "" + LNCSPAIP.REC_DATA.KEY.SUF_NO;
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.SUF_NO = "0" + LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZSLNQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.TOT_BAL);
        CEP.TRC(SCCGWA, LNRCONT.LN_TOT_AMT);
        if (LNRCONT.LN_TOT_AMT >= LNCSLNQ.COMM_DATA.TOT_BAL) {
            WS_VARIABLES.REM_AMT = LNCSLNQ.COMM_DATA.TOT_BAL;
        } else {
            WS_VARIABLES.REM_AMT = LNRCONT.LN_TOT_AMT;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'A';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        LNRPAIP.PHS_REM_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        LNCRPAIP.REC_LEN = 1352;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B09_CLR_FLG();
            if (pgmRtn) return;
        }
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'R';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        LNCRPAIP.REC_LEN = 1352;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRPAIP.FUNC = 'D';
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B03_DELETE_OTHER() throws IOException,SQLException,Exception {
        WS_VARIABLES.J = LNCSPAIP.REC_DATA.KEY.PHS_NO;
        WS_VARIABLES.J += 1;
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.PHS_NO);
        CEP.TRC(SCCGWA, LNCPAIPB.COMM_DATA.PHS_CNT);
        for (WS_VARIABLES.I = WS_VARIABLES.J; WS_VARIABLES.I <= LNCPAIPB.COMM_DATA.PHS_CNT; WS_VARIABLES.I += 1) {
            IBS.init(SCCGWA, LNCRPAIP);
            IBS.init(SCCGWA, LNRPAIP);
            LNRPAIP.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
            CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
            LNRPAIP.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
            LNRPAIP.KEY.PHS_NO = (short) WS_VARIABLES.I;
            CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.LN_AC);
            CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.SUF_NO);
            CEP.TRC(SCCGWA, WS_VARIABLES.I);
            LNCRPAIP.FUNC = 'R';
            LNCRPAIP.REC_LEN = 1352;
            LNCRPAIP.REC_PTR = LNRPAIP;
            S000_CALL_LNZPAIP();
            if (pgmRtn) return;
            if (LNCRPAIP.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            LNCRPAIP.FUNC = 'D';
            S000_CALL_LNZPAIP();
            if (pgmRtn) return;
            if (LNCRPAIP.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'R';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        LNCRPAIP.REC_LEN = 1352;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, LNCRPAIP.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_COMPARE_DATA_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '4';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        LNCAPRDM.FUNC = '2';
        LNCAPRDM.REC_DATA.INST_MTH = LNCSPAIP.REC_DATA.INST_MTH;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        LNCRPAIP.FUNC = 'U';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        B09_CLR_FLG();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'I';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        LNCRPAIP.REC_LEN = 1352;
        LNCRPAIP.REC_PTR = LNRPAIP;
        if (LNCSPAIP.FUNC == '3') {
            S000_CALL_LNZPAIP();
            if (pgmRtn) return;
        } else {
            LNRPAIP.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
            CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
            LNRPAIP.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        }
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R010_OUTPUT_DATA_PROC();
            if (pgmRtn) return;
        }
    }
    public void B07_FUNC_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.WRITE_TMPP_FLG);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCSPAIP.REC_DATA.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATE.CURR_PAGE = (short) LNCSPAIP.REC_DATA.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
        WS_VARIABLES.WS_DATE.LAST_PAGE = 'N';
        if (LNCSPAIP.REC_DATA.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATE.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new LNZSPAIP_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (LNCSPAIP.REC_DATA.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCSPAIP.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) LNCSPAIP.REC_DATA.PAGE_ROW;
                WS_OUT_INFO = new LNZSPAIP_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        WS_VARIABLES.WS_DATE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'S';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        T000_STARTBR_PAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.TOT_AMT = 0;
        B11_GET_CONT_REM_AMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.REM_AMT);
        T000_READNEXT_PAIP();
        if (pgmRtn) return;
        WS_VARIABLES.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        CEP.TRC(SCCGWA, LNCRPAIP.RC);
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPAIP.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.SUF_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_COND_FLG.PLPI_LAST_I_FND_FLG = 'N';
            R000_GET_LAST_PLPI_INFO();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            for (WS_VARIABLES.WS_DATE.IDX = 1; (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW) 
                && !JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.LN_ERR_PAIP_NOTFND) 
                && LNRPAIP.KEY.CONTRACT_NO.equalsIgnoreCase(WS_VARIABLES.CONTRACT_NO) 
                && LNRPAIP.KEY.SUB_CTA_NO == LNCSPAIP.REC_DATA.KEY.SUF_NO; WS_VARIABLES.WS_DATE.IDX += 1) {
                if (LNCSPAIP.REC_DATA.WRITE_TMPP_FLG == 'Y') {
                    R100_MOVE_TMPP_DATA();
                    if (pgmRtn) return;
                }
                R020_OUTPUT_DATA_PROC();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
                T000_READNEXT_PAIP();
                if (pgmRtn) return;
                WS_VARIABLES.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_OUT_INFO = new LNZSPAIP_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_OUT_INFO = new LNZSPAIP_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        LNCSPAIP.REC_DATA.LAST_TERM_VAL_DT = WS_VARIABLES.SCH_STRT_DT;
        LNCSPAIP.REC_DATA.LAST_TERM_DUE_DT = WS_VARIABLES.SCH_END_DT;
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.LAST_TERM_VAL_DT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.LAST_TERM_DUE_DT);
        LNCRPAIP.OPT = 'E';
        T000_ENDBR_PAIP();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TRAN_SEQ = LNCSPAIP.REC_DATA.TRAN_SEQ;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0137220")) {
            R020_OUTPUT_WRITE();
            if (pgmRtn) return;
        }
    }
    public void B08_TOT_SCH_CHECK() throws IOException,SQLException,Exception {
        R100_CALL_LNZPAIPB();
        if (pgmRtn) return;
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= LNCPAIPB.COMM_DATA.PHS_CNT; WS_VARIABLES.I += 1) {
            WS_VARIABLES.TOT_SCH_AMT += LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_PRIN_AMT;
            WS_VARIABLES.TOT_SCH_TERM += LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_TOT_TERM;
        }
        if (WS_VARIABLES.TOT_SCH_AMT > LNRCONT.LN_TOT_AMT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_GE_PAY_P_AMT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B09_CLR_FLG() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(56 - 1, 56 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 56 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(56 + 1 - 1);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCFTLPM);
            BPCFTLPM.OP_CODE = 'D';
            BPCFTLPM.BUSS_TYP = "L1";
            BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
            if ("1".trim().length() == 0) BPCFTLPM.TX_COUNT = 0;
            else BPCFTLPM.TX_COUNT = Short.parseShort("1");
            S000_CALL_BPZFTLPM();
            if (pgmRtn) return;
        }
    }
    public void R000_INPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.LN_AC);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.SUF_NO);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.KEY.PHS_NO);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.INST_MTH);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PERD);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PERD_UNIT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_INST_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_PRIN_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_TOT_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_REM_PRIN_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_CAL_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_CMP_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.CUR_INST_AMT);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.CUR_INST_IRAT);
        LNRPAIP.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNRPAIP.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        LNRPAIP.KEY.PHS_NO = LNCSPAIP.REC_DATA.KEY.PHS_NO;
        LNRPAIP.INST_MTH = LNCSPAIP.REC_DATA.INST_MTH;
        LNRPAIP.PERD = LNCSPAIP.REC_DATA.PERD;
        LNRPAIP.PERD_UNIT = LNCSPAIP.REC_DATA.PERD_UNIT;
        LNRPAIP.PHS_INST_AMT = LNCSPAIP.REC_DATA.PHS_INST_AMT;
        LNRPAIP.PHS_PRIN_AMT = LNCSPAIP.REC_DATA.PHS_PRIN_AMT;
        LNRPAIP.PHS_TOT_TERM = LNCSPAIP.REC_DATA.PHS_TOT_TERM;
        LNRPAIP.PHS_REM_PRIN_AMT = LNCSPAIP.REC_DATA.PHS_REM_PRIN_AMT;
        LNRPAIP.PHS_CAL_TERM = LNCSPAIP.REC_DATA.PHS_CAL_TERM;
        LNRPAIP.PHS_CMP_TERM = LNCSPAIP.REC_DATA.PHS_CMP_TERM;
        LNRPAIP.CUR_INST_AMT = LNCSPAIP.REC_DATA.CUR_INST_AMT;
        LNRPAIP.CUR_INST_IRAT = LNCSPAIP.REC_DATA.CUR_INST_IRAT;
        LNRPAIP.CRT_DATE = LNCSPAIP.REC_DATA.CRT_DATE;
        LNRPAIP.CRT_TLR = LNCSPAIP.REC_DATA.CRT_TLR;
        LNRPAIP.UPDTBL_DATE = LNCSPAIP.REC_DATA.UPDTBL_DATE;
        LNRPAIP.UPDTBL_TLR = LNCSPAIP.REC_DATA.UPDTBL_TLR;
        LNRPAIP.TS = LNCSPAIP.REC_DATA.TS;
        CEP.TRC(SCCGWA, LNRPAIP);
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPAIP.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRPAIP.KEY.PHS_NO);
        CEP.TRC(SCCGWA, LNRPAIP.INST_MTH);
        CEP.TRC(SCCGWA, LNRPAIP.PERD);
        CEP.TRC(SCCGWA, LNRPAIP.PERD_UNIT);
        CEP.TRC(SCCGWA, LNRPAIP.PHS_INST_AMT);
        CEP.TRC(SCCGWA, LNRPAIP.PHS_PRIN_AMT);
        CEP.TRC(SCCGWA, LNRPAIP.PHS_TOT_TERM);
        CEP.TRC(SCCGWA, LNRPAIP.PHS_REM_PRIN_AMT);
        CEP.TRC(SCCGWA, LNRPAIP.PHS_CAL_TERM);
        CEP.TRC(SCCGWA, LNRPAIP.PHS_CMP_TERM);
        CEP.TRC(SCCGWA, LNRPAIP.CUR_INST_AMT);
        CEP.TRC(SCCGWA, LNRPAIP.CUR_INST_IRAT);
    }
    public void R100_MOVE_TMPP_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTMPP);
        LNRTMPP.KEY.TRAN_SEQ = LNCSPAIP.REC_DATA.TRAN_SEQ;
        LNRTMPP.ACTION = 'I';
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        LNRTMPP.KEY.CONTRACT_NO = LNRPAIP.KEY.CONTRACT_NO;
        LNRTMPP.KEY.SUB_CTA_NO = LNRPAIP.KEY.SUB_CTA_NO;
        LNRTMPP.KEY.PHS_NO = LNRPAIP.KEY.PHS_NO;
        LNRTMPP.INST_MTH = LNRPAIP.INST_MTH;
        LNRTMPP.PERD = LNRPAIP.PERD;
        LNRTMPP.PERD_UNIT = LNRPAIP.PERD_UNIT;
        LNRTMPP.PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
        LNRTMPP.PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        LNRTMPP.PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
        LNRTMPP.PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
        LNRTMPP.PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
        LNRTMPP.PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
        LNRTMPP.CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
        LNRTMPP.CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
        LNRTMPP.CRT_DATE = LNRPAIP.CRT_DATE;
        LNRTMPP.CRT_TLR = LNRPAIP.CRT_TLR;
        LNRTMPP.UPDTBL_DATE = LNRPAIP.UPDTBL_DATE;
        LNRTMPP.UPDTBL_TLR = LNRPAIP.UPDTBL_TLR;
        LNRTMPP.TS = LNRPAIP.TS;
        CEP.TRC(SCCGWA, "1477777777");
        CEP.TRC(SCCGWA, LNRTMPP.KEY.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
        T000_WRITE_LNTTMPP();
        if (pgmRtn) return;
    }
    public void R200_GET_PAIPB_DATA() throws IOException,SQLException,Exception {
        LNRPAIP.KEY.CONTRACT_NO = LNCPAIPB.COMM_DATA.LN_AC;
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        if (LNCPAIPB.COMM_DATA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCPAIPB.COMM_DATA.SUF_NO);
        LNRPAIP.KEY.PHS_NO = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_NO;
        LNRPAIP.KEY.PHS_NO -= 1;
        LNRPAIP.INST_MTH = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].INST_MTH;
        LNRPAIP.PERD = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PERD;
        LNRPAIP.PERD_UNIT = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PERD_UNIT;
        LNRPAIP.PHS_INST_AMT = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_INST_AMT;
        LNRPAIP.PHS_PRIN_AMT = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_PRIN_AMT;
        LNRPAIP.PHS_TOT_TERM = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_TOT_TERM;
        LNRPAIP.PHS_REM_PRIN_AMT = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_REM_PRIN_AMT;
        LNRPAIP.PHS_CAL_TERM = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_CAL_TERM;
        LNRPAIP.PHS_CMP_TERM = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].PHS_CMP_TERM;
        LNRPAIP.CUR_INST_AMT = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].CUR_INST_AMT;
        LNRPAIP.CUR_INST_IRAT = LNCPAIPB.COMM_DATA.PHS_DATA[WS_VARIABLES.I-1].CUR_INST_IRAT;
        CEP.TRC(SCCGWA, LNRPAIP);
    }
    public void R010_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCOPAIP);
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        LNCOPAIP.LN_AC = LNRPAIP.KEY.CONTRACT_NO;
        LNCOPAIP.SUF_NO = "" + LNRPAIP.KEY.SUB_CTA_NO;
        JIBS_tmp_int = LNCOPAIP.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCOPAIP.SUF_NO = "0" + LNCOPAIP.SUF_NO;
        LNCOPAIP.PHS_NO = LNRPAIP.KEY.PHS_NO;
        LNCOPAIP.INST_MTH = LNRPAIP.INST_MTH;
        LNCOPAIP.PERD = LNRPAIP.PERD;
        LNCOPAIP.PERD_UNIT = LNRPAIP.PERD_UNIT;
        LNCOPAIP.PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
        LNCOPAIP.PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        LNCOPAIP.PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
        LNCOPAIP.PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
        LNCOPAIP.PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
        LNCOPAIP.PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
        LNCOPAIP.CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
        LNCOPAIP.CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
        WS_VARIABLES.CUR_PHS_TENORS = LNRPAIP.PHS_TOT_TERM;
        R000_GET_TOTAL_INFO();
        if (pgmRtn) return;
        R000_COMP_STRT_END_DT();
        if (pgmRtn) return;
        LNCOPAIP.PHS_STRT_DT = WS_VARIABLES.SCH_STRT_DT;
        LNCOPAIP.PHS_END_DT = WS_VARIABLES.SCH_END_DT;
        IBS.init(SCCGWA, SCCFMT);
        if (LNCSPAIP.FUNC == '3' 
            || LNCSPAIP.FUNC == '4') {
            SCCFMT.FMTID = FMT_CD_1;
        }
        SCCFMT.DATA_PTR = LNCOPAIP;
        SCCFMT.DATA_LEN = 177;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R020_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCOPAIP);
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.O_LN_AC = LNRPAIP.KEY.CONTRACT_NO;
        WS_OUT_INFO.O_SUF_NO = LNRPAIP.KEY.SUB_CTA_NO;
        WS_OUT_INFO.O_PHS_NO = LNRPAIP.KEY.PHS_NO;
        WS_OUT_INFO.O_INST_MTH = LNRPAIP.INST_MTH;
        WS_OUT_INFO.O_PERD = LNRPAIP.PERD;
        WS_OUT_INFO.O_PERD_UNIT = LNRPAIP.PERD_UNIT;
        WS_OUT_INFO.O_PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
        WS_OUT_INFO.O_PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        WS_OUT_INFO.O_PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
        WS_OUT_INFO.O_PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
        WS_OUT_INFO.O_PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
        WS_OUT_INFO.O_PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
        WS_OUT_INFO.O_CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
        WS_OUT_INFO.O_CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
        WS_VARIABLES.CUR_PHS_TENORS = LNRPAIP.PHS_TOT_TERM;
        WS_VARIABLES.TOT_AMT += LNRPAIP.PHS_PRIN_AMT;
        R000_COMP_STRT_END_DT();
        if (pgmRtn) return;
        WS_OUT_INFO.O_PHS_STRT_DT = WS_VARIABLES.SCH_STRT_DT;
        WS_OUT_INFO.O_PHS_END_DT = WS_VARIABLES.SCH_END_DT;
        R000_COMP_TMP_BEG_END_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_LN_AC);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_SUF_NO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_NO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_PRIN_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_REM_PRIN_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_TOT_TERM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_STRT_DT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_END_DT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_INST_MTH);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_INST_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_CUR_INST_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PERD);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PERD_UNIT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_CAL_TERM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_PHS_CMP_TERM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.WS_DATE.IDX-1).O_CUR_INST_IRAT);
        CEP.TRC(SCCGWA, WS_VARIABLES.BEG_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.END_DATE);
    }
    public void R020_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNZ12";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 3768;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_TOTAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'G';
        R000_INPUT_DATA_PROC();
        if (pgmRtn) return;
        LNCRPAIP.REC_LEN = 1352;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIP();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.TOT_TENORS = LNCRPAIP.TOT_TENORS;
        WS_VARIABLES.TOT_PRIN = LNCRPAIP.TOT_PRIN;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_TENORS);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_PRIN);
    }
    public void R000_GET_OTHER_AMT_INTEREST() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.REM_AMT > LNRPAIP.PHS_PRIN_AMT) {
            WS_VARIABLES.REL_TOT_AMT = WS_VARIABLES.REM_AMT - LNRPAIP.PHS_PRIN_AMT;
            WS_VARIABLES.REM_AMT = WS_VARIABLES.REM_AMT - LNRPAIP.PHS_PRIN_AMT;
        } else {
            WS_VARIABLES.REL_TOT_AMT = 0;
        }
        if (LNRPAIP.INST_MTH == '2') {
            WS_VARIABLES.REL_TOT_AMT = WS_VARIABLES.REL_TOT_AMT + LNRPAIP.PHS_REM_PRIN_AMT - LNRPAIP.PHS_INST_AMT * LNRPAIP.PHS_CAL_TERM;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.REL_TOT_AMT);
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = "" + LNCSPAIP.REC_DATA.KEY.SUF_NO;
        JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TERM = LNRPAIP.PHS_TOT_TERM;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.AMT = WS_VARIABLES.REL_TOT_AMT;
        LNCICUT.COMM_DATA.BEG_DATE = WS_VARIABLES.BEG_DATE;
        LNCICUT.COMM_DATA.END_DATE = WS_VARIABLES.END_DATE;
        if (LNCICUT.COMM_DATA.AMT != 0) {
            S000_CALL_LNZICUT();
            if (pgmRtn) return;
        }
    }
    public void R000_COMP_TMP_BEG_END_DATE() throws IOException,SQLException,Exception {
        if (LNRPAIP.KEY.PHS_NO == 1) {
            WS_VARIABLES.BEG_DATE = LNCLOANM.REC_DATA.BRAT_EFF_DT;
            WS_VARIABLES.END_DATE = LNCLOANM.REC_DATA.FST_CAL_DT;
        } else {
            WS_VARIABLES.BEG_DATE = WS_VARIABLES.SCH_STRT_DT;
            IBS.init(SCCGWA, SCCCLDT);
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            SCCCLDT.DATE1 = WS_VARIABLES.BEG_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.END_DATE = SCCCLDT.DATE2;
        }
    }
    public void R000_COMP_STRT_END_DT() throws IOException,SQLException,Exception {
        WS_VARIABLES.PRE_TOT_TENORS = WS_VARIABLES.TOT_TENORS;
        WS_VARIABLES.TOT_TENORS = (short) (WS_VARIABLES.TOT_TENORS + WS_VARIABLES.CUR_PHS_TENORS);
        CEP.TRC(SCCGWA, WS_VARIABLES.PRE_TOT_TENORS);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOT_TENORS);
        if (WS_VARIABLES.PRE_TOT_TENORS > 0) {
            if (WS_VARIABLES.LAST_I_DUE_DT > 0) {
                CEP.TRC(SCCGWA, WS_VARIABLES.LAST_I_DUE_DT);
                WS_VARIABLES.SCH_DT = WS_VARIABLES.LAST_I_DUE_DT;
            } else {
                WS_VARIABLES.SCH_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
                WS_VARIABLES.PRE_TOT_TENORS -= 1;
            }
            WS_VARIABLES.TENORS = WS_VARIABLES.PRE_TOT_TENORS;
            R000_COMP_REL_DT();
            if (pgmRtn) return;
        } else {
            if (WS_VARIABLES.LAST_I_DUE_DT > 0) {
                CEP.TRC(SCCGWA, WS_VARIABLES.LAST_I_DUE_DT);
                WS_VARIABLES.SCH_DT = WS_VARIABLES.LAST_I_DUE_DT;
            } else {
                WS_VARIABLES.SCH_DT = LNRCONT.START_DATE;
            }
        }
        WS_VARIABLES.SCH_STRT_DT = WS_VARIABLES.SCH_DT;
        WS_VARIABLES.SCH_DT = WS_VARIABLES.SCH_STRT_DT;
        CEP.TRC(SCCGWA, WS_VARIABLES.SCH_DT);
        CEP.TRC(SCCGWA, "WANGXIUCAI");
        CEP.TRC(SCCGWA, WS_VARIABLES.SCH_STRT_DT);
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        if (WS_VARIABLES.SCH_STRT_DT == LNRCONT.START_DATE) {
            CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.FST_CAL_DT);
            WS_VARIABLES.SCH_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
            CEP.TRC(SCCGWA, WS_VARIABLES.CUR_PHS_TENORS);
            WS_VARIABLES.CUR_PHS_TENORS -= 1;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.SCH_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.CUR_PHS_TENORS);
        WS_VARIABLES.TENORS = WS_VARIABLES.CUR_PHS_TENORS;
        CEP.TRC(SCCGWA, WS_VARIABLES.TENORS);
        R000_COMP_REL_DT();
        if (pgmRtn) return;
        WS_VARIABLES.SCH_END_DT = WS_VARIABLES.SCH_DT;
        if (WS_VARIABLES.SCH_END_DT > LNRCONT.MAT_DATE) {
            WS_VARIABLES.SCH_END_DT = LNRCONT.MAT_DATE;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.SCH_STRT_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.SCH_END_DT);
    }
    public void R000_COMP_REL_DT() throws IOException,SQLException,Exception {
        while (WS_VARIABLES.TENORS != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, WS_VARIABLES.TENORS);
            CEP.TRC(SCCGWA, LNRPAIP.PERD_UNIT);
            CEP.TRC(SCCGWA, LNRPAIP.PERD);
            if (LNRPAIP.PERD_UNIT == 'M') {
                SCCCLDT.MTHS = (short) (LNRPAIP.PERD * WS_VARIABLES.TENORS);
            }
            if (LNRPAIP.PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNRPAIP.PERD * WS_VARIABLES.TENORS;
            }
            SCCCLDT.DATE1 = WS_VARIABLES.SCH_DT;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.SCH_DT = SCCCLDT.DATE2;
            WS_VARIABLES.TENORS = 0;
        }
    }
    public void T000_COMPARE_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRPAIP.KEY.CONTRACT_NO);
        if (LNCSPAIP.REC_DATA.KEY.LN_AC.equalsIgnoreCase(LNRPAIP.KEY.CONTRACT_NO) 
            && LNCSPAIP.REC_DATA.KEY.SUF_NO == LNRPAIP.KEY.SUB_CTA_NO 
            && LNCSPAIP.REC_DATA.KEY.PHS_NO == LNRPAIP.KEY.PHS_NO 
            && LNCSPAIP.REC_DATA.INST_MTH == LNRPAIP.INST_MTH 
            && LNCSPAIP.REC_DATA.PERD == LNRPAIP.PERD 
            && LNCSPAIP.REC_DATA.PERD_UNIT == LNRPAIP.PERD_UNIT 
            && LNCSPAIP.REC_DATA.PHS_INST_AMT == LNRPAIP.PHS_INST_AMT 
            && LNCSPAIP.REC_DATA.PHS_PRIN_AMT == LNRPAIP.PHS_PRIN_AMT 
            && LNCSPAIP.REC_DATA.PHS_TOT_TERM == LNRPAIP.PHS_TOT_TERM 
            && LNCSPAIP.REC_DATA.PHS_REM_PRIN_AMT == LNRPAIP.PHS_REM_PRIN_AMT 
            && LNCSPAIP.REC_DATA.PHS_CAL_TERM == LNRPAIP.PHS_CAL_TERM 
            && LNCSPAIP.REC_DATA.PHS_CMP_TERM == LNRPAIP.PHS_CMP_TERM 
            && LNCSPAIP.REC_DATA.CUR_INST_AMT == LNRPAIP.CUR_INST_AMT 
            && LNCSPAIP.REC_DATA.CUR_INST_IRAT == LNRPAIP.CUR_INST_IRAT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAIP_NOTCHANGE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void BS1_COMP_INST_AMT() throws IOException,SQLException,Exception {
        if (LNCSPAIP.REC_DATA.PHS_TOT_TERM == 0 
            && LNCSPAIP.REC_DATA.PHS_INST_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPAIP.REC_DATA.PHS_TOT_TERM == 0 
            && LNCSPAIP.REC_DATA.INST_MTH != '1' 
            && LNCSPAIP.REC_DATA.INST_MTH != '2') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPAIP.FUNC == '2' 
            && LNCSPAIP.REC_DATA.PHS_TOT_TERM != 0 
            && LNCSPAIP.REC_DATA.PHS_CAL_TERM >= LNCSPAIP.REC_DATA.PHS_TOT_TERM) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_ALL_CAL_TERM, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNCAPRDM.REC_DATA.INST_MTH = LNCSPAIP.REC_DATA.INST_MTH;
        if (LNCAPRDM.REC_DATA.INST_MTH == '1') {
            WS_VARIABLES.FORM_CODE = "31";
        } else if (LNCAPRDM.REC_DATA.INST_MTH == '2') {
            WS_VARIABLES.FORM_CODE = "34";
        } else if (LNCAPRDM.REC_DATA.INST_MTH == '3') {
            WS_VARIABLES.FORM_CODE = "32";
        }
        BS01_INSTALMENT_PROCESS();
        if (pgmRtn) return;
        if (LNCSPAIP.FUNC == '2' 
            && LNCSPAIP.REC_DATA.PHS_CMP_TERM > LNCSPAIP.REC_DATA.PHS_CAL_TERM) {
            B09_DEL_CMP_TERM();
            if (pgmRtn) return;
        }
    }
    public void BS01_INSTALMENT_PROCESS() throws IOException,SQLException,Exception {
        if (LNCSPAIP.FUNC == '0') {
            WS_VARIABLES.PRIN_AMT = LNCSPAIP.REC_DATA.PHS_PRIN_AMT;
        } else {
            WS_VARIABLES.PRIN_AMT = LNCSPAIP.REC_DATA.PHS_REM_PRIN_AMT;
        }
        B220_GET_QCCY_RND_MTH();
        if (pgmRtn) return;
        if (LNCSPAIP.REC_DATA.PHS_TOT_TERM != 0) {
            BS11_COMP_INSAMT_PROCESS();
            if (pgmRtn) return;
        } else {
            BS12_COMP_TOTTRM_PROCESS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_TOT_TERM);
        CEP.TRC(SCCGWA, LNCSPAIP.REC_DATA.PHS_INST_AMT);
    }
    public void BS11_COMP_INSAMT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        LNCILCM.COMM_DATA.FORM_CODE = WS_VARIABLES.FORM_CODE;
        LNCILCM.COMM_DATA.FUNC_CODE = '1';
        LNCILCM.COMM_DATA.PRIN_AMT = WS_VARIABLES.PRIN_AMT;
        LNCILCM.COMM_DATA.RATE = WS_VARIABLES.N_RATE;
        LNCILCM.COMM_DATA.BASDAYS_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        LNCILCM.COMM_DATA.PERIOD = LNCSPAIP.REC_DATA.PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNCSPAIP.REC_DATA.PERD_UNIT;
        LNCILCM.COMM_DATA.ROUND_MODE = BPCQCCY.DATA.RND_MTH;
        LNCILCM.COMM_DATA.CCY = LNRCONT.CCY;
        LNCILCM.COMM_DATA.TERM = LNCSPAIP.REC_DATA.PHS_TOT_TERM;
        LNCILCM.COMM_DATA.TERM -= LNCSPAIP.REC_DATA.PHS_CAL_TERM;
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.INST_AMT);
        if (LNCSPAIP.FUNC == '0' 
            || LNCSPAIP.REC_DATA.PHS_CAL_TERM == 0) {
            LNCSPAIP.REC_DATA.PHS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
            LNCSPAIP.REC_DATA.CUR_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
        } else {
            LNCSPAIP.REC_DATA.PHS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
            LNCSPAIP.REC_DATA.CUR_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
        }
    }
    public void BS12_COMP_TOTTRM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        LNCILCM.COMM_DATA.FORM_CODE = WS_VARIABLES.FORM_CODE;
        LNCILCM.COMM_DATA.FUNC_CODE = '2';
        LNCILCM.COMM_DATA.PRIN_AMT = WS_VARIABLES.PRIN_AMT;
        LNCILCM.COMM_DATA.RATE = WS_VARIABLES.N_RATE;
        LNCILCM.COMM_DATA.BASDAYS_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        LNCILCM.COMM_DATA.PERIOD = LNCSPAIP.REC_DATA.PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNCSPAIP.REC_DATA.PERD_UNIT;
        LNCILCM.COMM_DATA.ROUND_MODE = BPCQCCY.DATA.RND_MTH;
        LNCILCM.COMM_DATA.CCY = LNRCONT.CCY;
        LNCILCM.COMM_DATA.INST_AMT = LNCSPAIP.REC_DATA.PHS_INST_AMT;
        LNCSPAIP.REC_DATA.CUR_INST_AMT = LNCSPAIP.REC_DATA.PHS_INST_AMT;
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.TERM);
        LNCSPAIP.REC_DATA.PHS_TOT_TERM = (short) (LNCSPAIP.REC_DATA.PHS_CAL_TERM + LNCILCM.COMM_DATA.TERM);
    }
    public void B220_GET_QCCY_RND_MTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNRCONT.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.RND_MTH);
    }
    public void B09_DEL_CMP_TERM() throws IOException,SQLException,Exception {
        WS_COND_FLG.FND_FLG = 'N';
        T00_STARTBR_LNTPLPI();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while (WS_COND_FLG.FND_FLG != 'N') {
            T00_DELETE_LNTPLPI();
            if (pgmRtn) return;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        LNCSPAIP.REC_DATA.PHS_CMP_TERM = LNCSPAIP.REC_DATA.PHS_CAL_TERM;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        LNCICTLM.REC_DATA.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        if (LNCICTLM.REC_DATA.IC_CAL_TERM != 1) {
            if (LNCSPAIP.REC_DATA.PERD != 0 
                && LNCSPAIP.REC_DATA.PERD_UNIT != ' ') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                if (LNCSPAIP.REC_DATA.PERD_UNIT == 'M') {
                    SCCCLDT.MTHS = LNCSPAIP.REC_DATA.PERD;
                } else {
                    SCCCLDT.DAYS = LNCSPAIP.REC_DATA.PERD;
                }
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                LNCICTLM.REC_DATA.IC_CAL_DUE_DT = SCCCLDT.DATE2;
                if (LNRPAIP.PERD_UNIT == 'M') {
                    S000_GET_APRD_INF();
                    if (pgmRtn) return;
                    B035_04_MOD_DAY();
                    if (pgmRtn) return;
                    LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_VARIABLES.OUT_DATE;
                }
            }
        }
        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B035_04_MOD_DAY() throws IOException,SQLException,Exception {
        WS_VARIABLES.YYYYMMDD = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        IBS.CPY2CLS(SCCGWA, WS_VARIABLES.YYYYMMDD+"", WS_VARIABLES.REDEFINES30);
        if (LNCAPRDM.REC_DATA.PAY_DAY < 29) {
            if (LNCAPRDM.REC_DATA.PAY_DAY > 0) {
                WS_VARIABLES.REDEFINES30.DD = LNCAPRDM.REC_DATA.PAY_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        } else {
            if (LNCAPRDM.REC_DATA.PAY_DAY == 29 
                || LNCAPRDM.REC_DATA.PAY_DAY == 30) {
                if (WS_VARIABLES.REDEFINES30.MM == 2) {
                    if (WS_VARIABLES.REDEFINES30.YYYY % 100 == 0) {
                        if (WS_VARIABLES.REDEFINES30.YYYY % 400 == 0) {
                            WS_VARIABLES.REDEFINES30.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES30.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    } else {
                        if (WS_VARIABLES.REDEFINES30.YYYY % 4 == 0) {
                            WS_VARIABLES.REDEFINES30.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES30.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    WS_VARIABLES.REDEFINES30.DD = LNCAPRDM.REC_DATA.PAY_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                    WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (LNCAPRDM.REC_DATA.PAY_DAY == 31) {
                if (WS_VARIABLES.REDEFINES30.MM == 2) {
                    if (WS_VARIABLES.REDEFINES30.YYYY % 100 == 0) {
                        if (WS_VARIABLES.REDEFINES30.YYYY % 400 == 0) {
                            WS_VARIABLES.REDEFINES30.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES30.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    } else {
                        if (WS_VARIABLES.REDEFINES30.YYYY % 4 == 0) {
                            WS_VARIABLES.REDEFINES30.DD = 29;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        } else {
                            WS_VARIABLES.REDEFINES30.DD = 28;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                            WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                        }
                    }
                } else {
                    if (WS_VARIABLES.REDEFINES30.MM == 4 
                        || WS_VARIABLES.REDEFINES30.MM == 6 
                        || WS_VARIABLES.REDEFINES30.MM == 9 
                        || WS_VARIABLES.REDEFINES30.MM == 11) {
                        WS_VARIABLES.REDEFINES30.DD = 30;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        WS_VARIABLES.REDEFINES30.DD = 31;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES30);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
            }
        }
        WS_VARIABLES.OUT_DATE = WS_VARIABLES.YYYYMMDD;
        CEP.TRC(SCCGWA, WS_VARIABLES.OUT_DATE);
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNRICTL.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNRICTL.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.eqWhere = "CONTRACT_NO";
        LNTICTL_RD.where = "SUB_CTA_NO >= :LNRICTL.KEY.SUB_CTA_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ICTL_NOTFND, WS_VARIABLES.WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        LNRPLPI.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNRPLPI.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        LNRPLPI.KEY.REPY_MTH = 'C';
        LNRPLPI.KEY.TERM = LNCSPAIP.REC_DATA.PHS_CAL_TERM;
        LNRPLPI.KEY.TERM += 1;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.upd = true;
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_LAST_PLPI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNRPLPI.KEY.SUB_CTA_NO = LNCSPAIP.REC_DATA.KEY.SUF_NO;
        LNRPLPI.KEY.REPY_MTH = 'I';
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.PLPI_LAST_I_FND_FLG = 'Y';
            CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
            WS_VARIABLES.LAST_I_DUE_DT = LNRPLPI.DUE_DT;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.PLPI_LAST_I_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
    }
    public void T00_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.errhdl = true;
        IBS.DELETE(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void T000_STARTBR_PAIP() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp = new DBParm();
        LNTPAIP_BR.rp.TableName = "LNTPAIP";
        LNTPAIP_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPAIP_BR.rp.errhdl = true;
        LNTPAIP_BR.rp.order = "CONTRACT_NO, SUB_CTA_NO, PHS_NO";
        IBS.STARTBR(SCCGWA, LNRPAIP, LNTPAIP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPAIP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCRPAIP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPAIP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NORMAL, LNCRPAIP.RC);
        } else {
            LNCRPAIP.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, LNCRPAIP.RC);
        }
    }
    public void T000_READNEXT_PAIP() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPAIP.RETURN_INFO = 'E';
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCRPAIP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPAIP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NORMAL, LNCRPAIP.RC);
        } else {
            LNCRPAIP.RETURN_INFO = 'O';
        }
    }
    public void T000_ENDBR_PAIP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAIP_BR);
    }
    public void R100_CALL_LNZPAIPB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPB);
        LNCPAIPB.COMM_DATA.LN_AC = LNCSPAIP.REC_DATA.KEY.LN_AC;
        LNCPAIPB.COMM_DATA.SUF_NO = "" + LNCSPAIP.REC_DATA.KEY.SUF_NO;
        JIBS_tmp_int = LNCPAIPB.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCPAIPB.COMM_DATA.SUF_NO = "0" + LNCPAIPB.COMM_DATA.SUF_NO;
        IBS.CALLCPN(SCCGWA, "LN-FUN-PAIP-BRWQ", LNCPAIPB);
    }
    public void T000_WRITE_LNTTMPP() throws IOException,SQLException,Exception {
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        LNTTMPP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRTMPP, LNTTMPP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_DUPKEY, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_GET_APRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-PAIPL-MAIN", LNCRPAIP);
        CEP.TRC(SCCGWA, LNCRPAIP.RC);
        WS_VARIABLES.CONTRACT_NO = LNCSPAIP.REC_DATA.KEY.LN_AC;
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATE-INQ", LNCRATQ);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRATQ.RC.RC_RTNCODE != 0) {
            LNCSPAIP.RC.RC_APP = LNCRATQ.RC.RC_APP;
            LNCSPAIP.RC.RC_RTNCODE = LNCRATQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZILCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INST-AMT-CMP", LNCILCM);
        if (LNCILCM.RC.RC_RTNCODE != 0) {
            LNCSPAIP.RC.RC_APP = LNCILCM.RC.RC_APP;
            LNCSPAIP.RC.RC_RTNCODE = LNCILCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCSPAIP.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCSPAIP.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCSPAIP.RC.RC_APP = "SC";
            LNCSPAIP.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
        if (LNCCTLPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSPAIP.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPAIP=");
            CEP.TRC(SCCGWA, LNCSPAIP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
