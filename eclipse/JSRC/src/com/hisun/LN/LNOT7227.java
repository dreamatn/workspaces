package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCIDAY;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCQCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNOT7227 {
    boolean pgmRtn = false;
    String K_CPN_LN_S_TMPPM_MAIN = "LN-S-TMPP-MAIN";
    String PGM_SCSSCLDT = "SCSSCLDT";
    LNOT7227_WS_ERR_MSG WS_ERR_MSG = new LNOT7227_WS_ERR_MSG();
    short WS_K = 0;
    double WS_REM_PRIN1 = 0;
    double WS_REPAID_AMT = 0;
    short WS_FLD_NO = 0;
    double WS_N_RATE = 0;
    String WS_FORM_CODE = " ";
    short WS_PHS_TOT_TERM = 0;
    double WS_PHS_TOT_AMT = 0;
    short WS_MOD_TOT_TERM = 0;
    double WS_MOD_PRIN_AMT = 0;
    double WS_MOD_ALL_AMT = 0;
    double WS_MOD_ALL_TERM = 0;
    double WS_REL_TOT_AMT = 0;
    int WS_FST_END_DT = 0;
    short WS_TOT_TENORS = 0;
    short WS_START_TERM = 0;
    short WS_START_TERM_END = 0;
    int WS_RMK_DATE = 0;
    int WS_DATE = 0;
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    char WS_FOUND_FLG = ' ';
    short WS_IDX = 0;
    LNOT7227_WS_LOAN_BAL_ITEM[] WS_LOAN_BAL_ITEM = new LNOT7227_WS_LOAN_BAL_ITEM[99];
    char WS_FUNC = ' ';
    LNOT7227_WS_NESTING_DATA WS_NESTING_DATA = new LNOT7227_WS_NESTING_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRTMPP LNRTMPP = new LNRTMPP();
    LNCRTMPP LNCRTMPP = new LNCRTMPP();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRATQ LNCRATQ = new LNCRATQ();
    LNCILCM LNCILCM = new LNCILCM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCINCM LNCINCM = new LNCINCM();
    BPCIDAY BPCIDAY = new BPCIDAY();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNCICUT LNCICUT = new LNCICUT();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNCTMPPB LNCTMPPB = new LNCTMPPB();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    LNB7220_AWA_7220 LNB7220_AWA_7220;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public LNOT7227() {
        for (int i=0;i<99;i++) WS_LOAN_BAL_ITEM[i] = new LNOT7227_WS_LOAN_BAL_ITEM();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT7227 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7220_AWA_7220>");
        LNB7220_AWA_7220 = (LNB7220_AWA_7220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_NESTING_DATA);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.LN_AC);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.TOT_TERM);
        R100_CALL_LNZTMPPB();
        if (pgmRtn) return;
        for (WS_K = 1; WS_K <= LNB7220_AWA_7220.PHS_NO; WS_K += 1) {
            CEP.TRC(SCCGWA, WS_K);
            CEP.TRC(SCCGWA, LNB7220_AWA_7220.PHS_NO);
            CEP.TRC(SCCGWA, LNCTMPPB.COMM_DATA.PHS_DATA[WS_K-1].ACTION);
            if (LNCTMPPB.COMM_DATA.PHS_DATA[WS_K-1].ACTION == 'D') {
                LNB7220_AWA_7220.PHS_NO += 1;
            }
        }
        if (LNB7220_AWA_7220.LN_AC.equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, LNB7220_AWA_7220.LN_AC);
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB7220_AWA_7220.LN_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB7220_AWA_7220.PRIN_AMT == 0) {
            CEP.TRC(SCCGWA, LNB7220_AWA_7220.PRIN_AMT);
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB7220_AWA_7220.PRIN_AMT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB7220_AWA_7220.TOT_TERM == 0) {
            CEP.TRC(SCCGWA, LNB7220_AWA_7220.TOT_TERM);
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB7220_AWA_7220.TOT_TERM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCPPMQ);
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_MODULE_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_COMMIT_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_CONTRACT_PARM = 'Y';
        LNCPPMQ.KEY.KEY_VALUE.LEVEL = 'D';
        LNCPPMQ.KEY.KEY_METH = 'C';
        LNCPPMQ.KEY.KEY_VALUE.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        S000_CALL_LNZPPMQ();
        if (pgmRtn) return;
        if (LNCPPMQ.DATA_CONT_SPC.CONT_PAY_MTH == '4' 
            || LNCPPMQ.DATA_CONT_SPC.CONT_PAY_MTH == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPAY_TYPE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSLNQ);
        LNCSLNQ.COMM_DATA.LN_AC = LNB7220_AWA_7220.LN_AC;
        LNCSLNQ.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCSLNQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSLNQ.COMM_DATA.SUF_NO = "0" + LNCSLNQ.COMM_DATA.SUF_NO;
        S000_CALL_LNZSLNQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.TOT_BAL);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.PHS_NO);
        if (LNB7220_AWA_7220.PHS_NO != 0) {
            WS_REPAID_AMT = LNCTMPPB.COMM_DATA.PHS_DATA[LNB7220_AWA_7220.PHS_NO-1].PHS_PRIN_AMT - LNCTMPPB.COMM_DATA.PHS_DATA[LNB7220_AWA_7220.PHS_NO-1].PHS_REM_PRIN_AMT;
            if (WS_REPAID_AMT >= LNB7220_AWA_7220.PRIN_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ERR_PRIN_LESS_THAN_REM, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_REM_PRIN1 = LNB7220_AWA_7220.PRIN_AMT - WS_REPAID_AMT;
        } else {
            WS_REM_PRIN1 = LNB7220_AWA_7220.PRIN_AMT;
        }
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.PRIN_AMT);
        CEP.TRC(SCCGWA, WS_REM_PRIN1);
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.TOT_BAL);
        WS_NESTING_DATA.WS_REM_PRIN = WS_REM_PRIN1;
        CEP.TRC(SCCGWA, WS_NESTING_DATA.WS_REM_PRIN);
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        B110_GET_PHS_TOT_TERM_AMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_START_TERM);
        B110_COMP_MAX_TERM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PHS_TOT_TERM);
        CEP.TRC(SCCGWA, WS_TOT_TENORS);
        if (WS_PHS_TOT_TERM > WS_TOT_TENORS) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ERR_PHS_TOT_CNT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRATQ);
        LNCRATQ.COMM_DATA.LN_AC = LNB7220_AWA_7220.LN_AC;
        LNCRATQ.COMM_DATA.SUF_NO = "" + LNB7220_AWA_7220.SUF_NO;
        JIBS_tmp_int = LNCRATQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCRATQ.COMM_DATA.SUF_NO = "0" + LNCRATQ.COMM_DATA.SUF_NO;
        LNCRATQ.COMM_DATA.RATE_TYPE = 'N';
        LNCRATQ.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_LNZRATQ();
        if (pgmRtn) return;
        WS_N_RATE = LNCRATQ.COMM_DATA.RATE;
    }
    public void B110_COMP_MAX_TERM() throws IOException,SQLException,Exception {
        WS_TOT_TENORS = 1;
        WS_FST_END_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
        while (WS_FST_END_DT < LNRCONT.MAT_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            WS_TOT_TENORS += 1;
            if (WS_START_TERM == WS_TOT_TENORS) {
                WS_BEG_DATE = WS_FST_END_DT;
            }
            if (WS_START_TERM_END == WS_TOT_TENORS) {
                WS_END_DATE = WS_FST_END_DT;
            }
            WS_DATE = WS_FST_END_DT;
            B111_GET_DATE();
            if (pgmRtn) return;
            WS_FST_END_DT = WS_DATE;
        }
    }
    public void B111_GET_DATE() throws IOException,SQLException,Exception {
        if (LNCPPMQ.DATA_CONT_SPC.CONT_CAL_UNIT == 'D') {
            SCCCLDT.MTHS = LNCPPMQ.DATA_CONT_SPC.CONT_CAL_PERD;
        }
        if (LNCPPMQ.DATA_CONT_SPC.CONT_CAL_UNIT == 'D') {
            SCCCLDT.DAYS = LNCPPMQ.DATA_CONT_SPC.CONT_CAL_PERD;
        }
        SCCCLDT.DATE1 = WS_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DATE = SCCCLDT.DATE2;
    }
    public void B110_GET_PHS_TOT_TERM_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRTMPP);
        IBS.init(SCCGWA, LNRTMPP);
        LNCRTMPP.FUNC = 'B';
        LNCRTMPP.OPT = 'S';
        LNRTMPP.KEY.TRAN_SEQ = LNB7220_AWA_7220.TRAN_SEQ;
        LNRTMPP.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        LNRTMPP.KEY.SUB_CTA_NO = LNB7220_AWA_7220.SUF_NO;
        LNRTMPP.KEY.PHS_NO = LNB7220_AWA_7220.PHS_NO;
        WS_PHS_TOT_TERM = 0;
        WS_MOD_TOT_TERM = 0;
        WS_MOD_ALL_AMT = 0;
        WS_MOD_ALL_TERM = 0;
        LNCRTMPP.REC_LEN = 208;
        LNCRTMPP.REC_PTR = LNRTMPP;
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        LNCRTMPP.OPT = 'R';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRTMPP.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND) 
            && LNRTMPP.KEY.CONTRACT_NO.equalsIgnoreCase(LNB7220_AWA_7220.LN_AC)) {
            if (LNRTMPP.ACTION != 'D') {
                if (LNB7220_AWA_7220.PHS_NO > LNRTMPP.KEY.PHS_NO) {
                    WS_MOD_ALL_AMT += LNRTMPP.PHS_PRIN_AMT;
                    WS_MOD_ALL_TERM += LNRTMPP.PHS_TOT_TERM;
                }
                WS_PHS_TOT_AMT += LNRTMPP.PHS_PRIN_AMT;
                WS_PHS_TOT_TERM += LNRTMPP.PHS_TOT_TERM;
            }
            LNCRTMPP.OPT = 'R';
            S000_CALL_LNZTMPPL();
            if (pgmRtn) return;
        }
        LNCRTMPP.OPT = 'E';
        S000_CALL_LNZTMPPL();
        if (pgmRtn) return;
        WS_START_TERM_END = (short) (WS_START_TERM + 1);
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (LNB7220_AWA_7220.INST_MTH == '1') {
            WS_FORM_CODE = "31";
        } else if (LNB7220_AWA_7220.INST_MTH == '2') {
            WS_FORM_CODE = "34";
        } else if (LNB7220_AWA_7220.INST_MTH == '3') {
            WS_FORM_CODE = "32";
        }
        B210_COMP_INSAMT_PROCESS();
        if (pgmRtn) return;
        WS_NESTING_DATA.WS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.INST_AMT);
        B210_GET_BEG_END_DATE();
        if (pgmRtn) return;
        if (LNB7220_AWA_7220.INST_MTH == '2') {
            if (LNB7220_AWA_7220.PRIN_AMT >= WS_NESTING_DATA.WS_REM_PRIN) {
                WS_REL_TOT_AMT = WS_REL_TOT_AMT + WS_NESTING_DATA.WS_REM_PRIN - WS_NESTING_DATA.WS_INST_AMT * LNB7220_AWA_7220.CAL_TERM;
            } else {
                WS_REL_TOT_AMT = WS_REL_TOT_AMT + LNB7220_AWA_7220.PRIN_AMT - WS_NESTING_DATA.WS_INST_AMT * LNB7220_AWA_7220.CAL_TERM;
            }
            CEP.TRC(SCCGWA, WS_REL_TOT_AMT);
        }
        CEP.TRC(SCCGWA, WS_REL_TOT_AMT);
        CEP.TRC(SCCGWA, WS_BEG_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
        SCCFMT.FMTID = "LNP18";
        SCCFMT.DATA_PTR = WS_NESTING_DATA;
        SCCFMT.DATA_LEN = 22077;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_COMP_INSAMT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        LNCILCM.COMM_DATA.FORM_CODE = WS_FORM_CODE;
        LNCILCM.COMM_DATA.FUNC_CODE = '1';
        if (LNB7220_AWA_7220.PRIN_AMT > WS_NESTING_DATA.WS_REM_PRIN) {
            LNCILCM.COMM_DATA.PRIN_AMT = WS_NESTING_DATA.WS_REM_PRIN;
        } else {
            LNCILCM.COMM_DATA.PRIN_AMT = LNB7220_AWA_7220.PRIN_AMT;
        }
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.PRIN_AMT);
        LNCILCM.COMM_DATA.RATE = WS_N_RATE;
        LNCILCM.COMM_DATA.BASDAYS_STD = LNCPPMQ.DATA_CONT_SPC.CONT_INT_DBAS_STD;
        LNCILCM.COMM_DATA.PERIOD = LNCPPMQ.DATA_CONT_SPC.CONT_CAL_PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNCPPMQ.DATA_CONT_SPC.CONT_CAL_UNIT;
        LNCILCM.COMM_DATA.ROUND_MODE = LNCPPMQ.DATA_TXT.RND_MTH;
        LNCILCM.COMM_DATA.CCY = LNRCONT.CCY;
        LNCILCM.COMM_DATA.TERM = LNB7220_AWA_7220.TOT_TERM;
        LNCILCM.COMM_DATA.TERM -= LNB7220_AWA_7220.CAL_TERM;
        CEP.TRC(SCCGWA, WS_N_RATE);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.TOT_TERM);
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.CAL_TERM);
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_CAL_PERD);
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_CAL_UNIT);
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
    }
    public void B210_GET_BEG_END_DATE() throws IOException,SQLException,Exception {
        if (WS_START_TERM == 1) {
            WS_BEG_DATE = LNCLOANM.REC_DATA.BRAT_EFF_DT;
            WS_END_DATE = LNCLOANM.REC_DATA.FST_CAL_DT;
            if (WS_END_DATE < LNRCONT.MAT_DATE) {
            } else {
                WS_END_DATE = LNRCONT.MAT_DATE;
            }
        } else {
            if (WS_END_DATE > LNRCONT.MAT_DATE) {
                WS_END_DATE = LNRCONT.MAT_DATE;
            }
        }
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        WS_NESTING_DATA.WS_LN_AC = LNB7220_AWA_7220.LN_AC;
        WS_NESTING_DATA.WS_SUF_NO = LNB7220_AWA_7220.SUF_NO;
        WS_NESTING_DATA.WS_PHS_NO = LNB7220_AWA_7220.PHS_NO;
        WS_NESTING_DATA.WS_PRIN_AMT = LNB7220_AWA_7220.PRIN_AMT;
        WS_NESTING_DATA.WS_TOT_TERM = LNB7220_AWA_7220.TOT_TERM;
        WS_NESTING_DATA.WS_INST_MTH = LNB7220_AWA_7220.INST_MTH;
        WS_NESTING_DATA.WS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
        WS_NESTING_DATA.WS_NEXT_INST_AMT = WS_NESTING_DATA.WS_INST_AMT;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNRICTL.KEY.CONTRACT_NO = LNB7220_AWA_7220.LN_AC;
        LNRICTL.KEY.SUB_CTA_NO = LNB7220_AWA_7220.SUF_NO;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.eqWhere = "CONTRACT_NO";
        LNTICTL_RD.where = "SUB_CTA_NO >= :LNRICTL.KEY.SUB_CTA_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ICTL_NOTFND, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_ERR_MSG.WS_MSG_AP = LNCRCONT.RC.RC_MMO;
            WS_ERR_MSG.WS_MSG_CODE = LNCRCONT.RC.RC_CODE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG.WS_MSG_AP = LNCPPMQ.RC.RC_APP;
            WS_ERR_MSG.WS_MSG_CODE = LNCPPMQ.RC.RC_RTNCODE;
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
            WS_ERR_MSG.WS_MSG_AP = LNCRATQ.RC.RC_APP;
            WS_ERR_MSG.WS_MSG_CODE = LNCRATQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZILCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INST-AMT-CMP", LNCILCM);
        if (LNCILCM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG.WS_MSG_AP = LNCILCM.RC.RC_APP;
            WS_ERR_MSG.WS_MSG_CODE = LNCILCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTMPPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-TMPP-MAIN", LNCRTMPP);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG.WS_MSG_AP = "SC";
            WS_ERR_MSG.WS_MSG_CODE = SCCCLDT.RC;
