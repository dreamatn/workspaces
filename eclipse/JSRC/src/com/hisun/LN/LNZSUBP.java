package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSUBP {
    BigDecimal bigD;
    int JIBS_tmp_int;
    brParm LNTRELA_BR = new brParm();
    DBParm LNTSUBS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_INT_DB_30E = "05";
    short WS_DAYS = 0;
    char WS_REPY_TYPE = ' ';
    short WS_PHS_CAL_TERM = 0;
    short WS_TERM = 0;
    short WS_TOT_TERM = 0;
    short WS_IC_TERM = 0;
    int WS_LAST_DUE_DT = 0;
    int WS_P_DUE_DT = 0;
    double WS_I_DIFF_AMT = 0;
    double WS_I_PECT_AMT = 0;
    double WS_P_PECT_AMT = 0;
    double WS_REM_PRIN_AMT = 0;
    double WS_P_REC_AMT = 0;
    double WS_I_REC_AMT = 0;
    double WS_INST_AMT = 0;
    double WS_PNST_AMT = 0;
    int WS_INTBAS_DAYS = 0;
    double WS_SUBS_RAT = 0;
    char WS_AMT_TYP = ' ';
    double WS_SUBS_PCT = 0;
    double WS_FIXED_INT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    BPCIDAY BPCIDAY = new BPCIDAY();
    SCCGWA SCCGWA;
    LNCSUBP LNCSUBP;
    public void MP(SCCGWA SCCGWA, LNCSUBP LNCSUBP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSUBP = LNCSUBP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSUBP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSUBP.RC.RC_APP = "LN";
        LNCSUBP.RC.RC_RTNCODE = 0;
        LNCSUBP.COMM_DATA.P_AMT = 0;
        LNCSUBP.COMM_DATA.I_AMT = 0;
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.AMT_TYP);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_DATA();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCSUBP.COMM_DATA.FUNC_CODE != 'I' 
            && LNCSUBP.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSUBP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSUBP.COMM_DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSUBP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSUBP.COMM_DATA.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSUBP.COMM_DATA.LN_AC;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCSUBP.COMM_DATA.SUF_NO;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        T00_STARTBR_LNTRELA();
        if (pgmRtn) return;
        T00_READNEXT_LNTRELA();
        if (pgmRtn) return;
        while (WS_FOUND_FLG == 'Y') {
            LNCSUBP.COMM_DATA.PROJ_NO = LNRRELA.PROJ_NO;
            CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.PROJ_NO);
            B210_SUBS_MAIN_PROCESS();
            if (pgmRtn) return;
            T00_READNEXT_LNTRELA();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTRELA();
        if (pgmRtn) return;
    }
    public void B210_SUBS_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B211_READ_SUBSPRJ_INF();
        if (pgmRtn) return;
        WS_TOT_TERM = (short) (LNRRELA.ST_TERM + LNRRELA.TERM - 1);
        CEP.TRC(SCCGWA, LNRSUBS.ST_TERM);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, WS_TOT_TERM);
        if (LNRRELA.ST_TERM <= LNCSUBP.COMM_DATA.TERM 
            && LNCSUBP.COMM_DATA.TERM <= WS_TOT_TERM) {
            if (LNCSUBP.COMM_DATA.TOT_P_AMT == 0 
                && LNCSUBP.COMM_DATA.TOT_I_AMT == 0) {
                B213_READ_CUS_RCVD();
                if (pgmRtn) return;
            } else {
                WS_P_REC_AMT = LNCSUBP.COMM_DATA.TOT_P_AMT;
                WS_I_REC_AMT = LNCSUBP.COMM_DATA.TOT_I_AMT;
            }
            C000_SUBSIDY_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.FUNC_CODE);
            if (LNCSUBP.COMM_DATA.FUNC_CODE == 'U') {
                B215_ADD_DEV_SUBS_RCVD();
                if (pgmRtn) return;
                B216_UPDATE_CUS_RCVD();
                if (pgmRtn) return;
            }
        }
    }
    public void B211_READ_SUBSPRJ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
        T000_READ_LNTSUBS();
        if (pgmRtn) return;
    }
    public void B213_READ_CUS_RCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '3';
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCSUBP.COMM_DATA.AMT_TYP);
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCSUBP.COMM_DATA.LN_AC;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = LNCSUBP.COMM_DATA.SUF_NO;
        LNCRCVDM.REC_DATA.KEY.TERM = LNCSUBP.COMM_DATA.TERM;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNCSUBP.COMM_DATA.AMT_TYP;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = 0;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        LNCSUBP.COMM_DATA.BEG_DATE = LNCRCVDM.REC_DATA.VAL_DT;
        LNCSUBP.COMM_DATA.END_DATE = LNCRCVDM.REC_DATA.DUE_DT;
        WS_P_REC_AMT = LNCRCVDM.REC_DATA.P_REC_AMT;
        LNCSUBP.COMM_DATA.TOT_P_AMT = LNCRCVDM.REC_DATA.P_REC_AMT;
        WS_I_REC_AMT = LNCRCVDM.REC_DATA.I_REC_AMT;
        LNCSUBP.COMM_DATA.TOT_I_AMT = LNCRCVDM.REC_DATA.I_REC_AMT;
    }
    public void C000_CAL_INT_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIDAY);
        BPCIDAY.I_CALR_STD = K_INT_DB_30E;
        BPCIDAY.I_BEGIN_DATE = LNCSUBP.COMM_DATA.BEG_DATE;
        BPCIDAY.I_END_DATE = LNCSUBP.COMM_DATA.END_DATE;
        S000_CALL_BPZCIDAY();
        if (pgmRtn) return;
        WS_DAYS = BPCIDAY.OUTPUT.O_STD_DAYS;
        WS_INTBAS_DAYS = BPCIDAY.OUTPUT.O_LEAP_DAYS;
        WS_INTBAS_DAYS += BPCIDAY.OUTPUT.O_ORD_DAYS;
    }
    public void C000_SUBSIDY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSUBS.TYP);
        if (LNRSUBS.TYP == '1') {
            C300_SUBSIDY_PROCESS();
            if (pgmRtn) return;
        } else if (LNRSUBS.TYP == '2') {
        } else if (LNRSUBS.TYP == '3') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSUBP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C300_SUBSIDY_PROCESS() throws IOException,SQLException,Exception {
        WS_AMT_TYP = LNRSUBS.AMT_TYP;
        WS_SUBS_PCT = LNRSUBS.SUBS_PCT;
        WS_SUBS_RAT = LNRSUBS.SUBS_RAT;
        WS_FIXED_INT = LNRSUBS.FIXED_AMT;
        if (LNRSUBS.SUBS_MTH == '1') {
            B214_SUBP_MTH1();
            if (pgmRtn) return;
        } else if (LNRSUBS.SUBS_MTH == '2') {
            B214_SUBP_MTH2();
            if (pgmRtn) return;
        } else if (LNRSUBS.SUBS_MTH == '3') {
            B214_SUBP_MTH3();
            if (pgmRtn) return;
        } else if (LNRSUBS.SUBS_MTH == '4') {
            B214_SUBP_MTH4();
            if (pgmRtn) return;
        } else if (LNRSUBS.SUBS_MTH == '5') {
            B214_SUBP_MTH5();
            if (pgmRtn) return;
        } else if (LNRSUBS.SUBS_MTH == '6') {
            B214_SUBP_MTH6();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSUBP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B214_SUBP_MTH1() throws IOException,SQLException,Exception {
        LNCSUBP.COMM_DATA.P_AMT = WS_P_REC_AMT;
        LNCSUBP.COMM_DATA.I_AMT = WS_I_REC_AMT;
        WS_P_REC_AMT = 0;
        WS_I_REC_AMT = 0;
    }
    public void B214_SUBP_MTH2() throws IOException,SQLException,Exception {
        WS_I_DIFF_AMT = WS_I_REC_AMT * WS_SUBS_RAT / LNCSUBP.COMM_DATA.RAT;
        bigD = new BigDecimal(WS_I_DIFF_AMT);
        WS_I_DIFF_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        if (WS_I_DIFF_AMT < WS_I_REC_AMT) {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_DIFF_AMT;
            WS_I_REC_AMT -= WS_I_DIFF_AMT;
        } else {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_REC_AMT;
            WS_I_REC_AMT = 0;
        }
    }
    public void B2S1_COMP_DEV_DIFFINT() throws IOException,SQLException,Exception {
        B2S4_INQ_SCH_BAL();
        if (pgmRtn) return;
        WS_I_DIFF_AMT = LNCCNEX.COMM_DATA.INQ_AMT * WS_SUBS_RAT * WS_INTBAS_DAYS / ( 100 * WS_DAYS );
        bigD = new BigDecimal(WS_I_DIFF_AMT);
        WS_I_DIFF_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void B214_SUBP_MTH3() throws IOException,SQLException,Exception {
        B2S2_COMP_DEV_PECTINT();
        if (pgmRtn) return;
        if (WS_I_PECT_AMT < WS_I_REC_AMT) {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_PECT_AMT;
            WS_I_REC_AMT -= WS_I_PECT_AMT;
        } else {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_REC_AMT;
            WS_I_REC_AMT = 0;
        }
        if (WS_AMT_TYP == '2') {
            if (WS_P_PECT_AMT < WS_P_REC_AMT) {
                LNCSUBP.COMM_DATA.P_AMT = WS_P_PECT_AMT;
                WS_P_REC_AMT -= WS_P_PECT_AMT;
            } else {
                LNCSUBP.COMM_DATA.P_AMT = WS_P_REC_AMT;
                WS_P_REC_AMT = 0;
            }
        }
    }
    public void B2S2_COMP_DEV_PECTINT() throws IOException,SQLException,Exception {
        if (WS_AMT_TYP == '1') {
            B2S4_INQ_SCH_BAL();
            if (pgmRtn) return;
            WS_I_PECT_AMT = LNCCNEX.COMM_DATA.INQ_AMT * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_I_PECT_AMT);
            WS_I_PECT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else if (WS_AMT_TYP == '2') {
            WS_INST_AMT += WS_I_REC_AMT;
            WS_PNST_AMT += WS_P_REC_AMT;
            WS_I_PECT_AMT = WS_INST_AMT * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_I_PECT_AMT);
            WS_I_PECT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            WS_P_PECT_AMT = WS_PNST_AMT * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_P_PECT_AMT);
            WS_P_PECT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else if (WS_AMT_TYP == '3') {
            WS_I_PECT_AMT = WS_I_REC_AMT * WS_SUBS_PCT / 100;
            bigD = new BigDecimal(WS_I_PECT_AMT);
            WS_I_PECT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSUBP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B214_SUBP_MTH4() throws IOException,SQLException,Exception {
        if (WS_FIXED_INT < WS_I_REC_AMT) {
            LNCSUBP.COMM_DATA.I_AMT = WS_FIXED_INT;
            WS_I_REC_AMT -= WS_FIXED_INT;
        } else {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_REC_AMT;
            WS_I_REC_AMT = 0;
        }
    }
    public void B214_SUBP_MTH5() throws IOException,SQLException,Exception {
        if (WS_FIXED_INT < WS_I_REC_AMT) {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_REC_AMT - WS_FIXED_INT;
            WS_I_REC_AMT = WS_FIXED_INT;
        } else {
            LNCSUBP.COMM_DATA.I_AMT = 0;
        }
    }
    public void B214_SUBP_MTH6() throws IOException,SQLException,Exception {
        WS_I_DIFF_AMT = WS_I_REC_AMT * WS_SUBS_RAT / LNCSUBP.COMM_DATA.RAT;
        bigD = new BigDecimal(WS_I_DIFF_AMT);
        WS_I_DIFF_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        if (WS_I_DIFF_AMT < WS_I_REC_AMT) {
            LNCSUBP.COMM_DATA.I_AMT = WS_I_REC_AMT - WS_I_DIFF_AMT;
            WS_I_REC_AMT = WS_I_DIFF_AMT;
        } else {
            LNCSUBP.COMM_DATA.I_AMT = 0;
        }
    }
    public void B2S3_COMP_DEV_DIFFINT() throws IOException,SQLException,Exception {
        B2S4_INQ_SCH_BAL();
        if (pgmRtn) return;
        WS_I_DIFF_AMT = LNCCNEX.COMM_DATA.INQ_AMT * WS_SUBS_RAT * WS_INTBAS_DAYS / ( 100 * WS_DAYS );
        bigD = new BigDecimal(WS_I_DIFF_AMT);
        WS_I_DIFF_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
    public void B2S4_INQ_SCH_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSUBP.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = "" + LNCSUBP.COMM_DATA.SUF_NO;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void B215_ADD_DEV_SUBS_RCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '0';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCSUBP.COMM_DATA.LN_AC;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = LNCSUBP.COMM_DATA.SUF_NO;
        LNCRCVDM.REC_DATA.KEY.TERM = LNCSUBP.COMM_DATA.TERM;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNCSUBP.COMM_DATA.AMT_TYP;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = LNRRELA.PROJ_NO;
        LNCRCVDM.REC_DATA.VAL_DT = LNCSUBP.COMM_DATA.BEG_DATE;
        LNCRCVDM.REC_DATA.DUE_DT = LNCSUBP.COMM_DATA.END_DATE;
        LNCRCVDM.REC_DATA.TERM_STS = '0';
        LNCRCVDM.REC_DATA.REPY_STS = '0';
        LNCRCVDM.REC_DATA.P_REC_AMT = LNCSUBP.COMM_DATA.P_AMT;
        LNCRCVDM.REC_DATA.I_REC_AMT = LNCSUBP.COMM_DATA.I_AMT;
        LNCRCVDM.REC_DATA.F_REC_AMT = 0;
        LNCRCVDM.REC_DATA.O_REC_AMT = 0;
        LNCRCVDM.REC_DATA.L_REC_AMT = 0;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
    }
    public void B216_UPDATE_CUS_RCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCSUBP.COMM_DATA.LN_AC;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = LNCSUBP.COMM_DATA.SUF_NO;
        LNCRCVDM.REC_DATA.KEY.TERM = LNCSUBP.COMM_DATA.TERM;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNCSUBP.COMM_DATA.AMT_TYP;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = 0;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        LNCRCVDM.FUNC = '2';
        LNCRCVDM.REC_DATA.P_REC_AMT = WS_P_REC_AMT;
        LNCRCVDM.REC_DATA.I_REC_AMT = WS_I_REC_AMT;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
    }
    public void T00_STARTBR_LNTRELA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSUBP.COMM_DATA.LN_AC;
        LNRRELA.PROJ_NO = 0;
        LNRRELA.KEY.TYPE = 'S';
        LNTRELA_BR.rp = new DBParm();
        LNTRELA_BR.rp.TableName = "LNTRELA";
        LNTRELA_BR.rp.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE "
            + "AND PROJ_NO > :LNRRELA.PROJ_NO";
        LNTRELA_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRRELA, this, LNTRELA_BR);
    }
    public void T00_READNEXT_LNTRELA() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRELA, this, LNTRELA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_ENDBR_LNTRELA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRELA_BR);
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            LNCSUBP.RC.RC_APP = LNCLOANM.RC.RC_APP;
            LNCSUBP.RC.RC_RTNCODE = LNCLOANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCSUBP.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCSUBP.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCVDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        if (LNCRCVDM.RC.RC_RTNCODE != 0) {
            LNCSUBP.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCSUBP.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCSUBP.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCSUBP.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCSUBP.RC.RC_APP = "SC";
            LNCSUBP.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND, LNCSUBP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZCIDAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CALC-INT-DAYS", BPCIDAY);
        if (BPCIDAY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSUBP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSUBP.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, LNCSUBP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
