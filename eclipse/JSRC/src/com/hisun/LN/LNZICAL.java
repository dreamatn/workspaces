package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICAL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BigDecimal bigD;
    DBParm LNTRCVD_RD;
    DBParm LNTPYIF_RD;
    DBParm LNTPLPI_RD;
    brParm LNTRELA_BR = new brParm();
    DBParm LNTSUBS_RD;
    DBParm LNTRELA_RD;
    DBParm LNTBALZ_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    int WS_DATE = 0;
    char WS_REPY_TYPE = ' ';
    short WS_PHS_CMP_TERM = 0;
    short WS_PHS_CAL_TERM = 0;
    short WS_ICTLM_IC_CUR_TERM = 0;
    short WS_ICTLM_IC_CAL_TERM = 0;
    short WS_ICTLM_P_CAL_TERM = 0;
    short WS_ICTLM_P_CUR_TERM = 0;
    short WS_IC_CMP_TERM = 0;
    int WS_IC_CMP_VAL_DT = 0;
    int WS_IC_CMP_DUE_DT = 0;
    int WS_IC_CAL_DUE_DT = 0;
    short WS_IC_TERM = 0;
    int WS_IC_VAL_DT = 0;
    int WS_IC_DUE_DT = 0;
    int WS_IC_DUE_DT_ERR = 0;
    int WS_RCVD_VAL_DT = 0;
    int WS_RCVD_DUE_DT = 0;
    short WS_P_TERM = 0;
    int WS_P_VAL_DT = 0;
    int WS_P_DUE_DT = 0;
    int WS_P_DUE_DT_ERR = 0;
    int WS_SCH_STR_DT = 0;
    double WS_INT_AMT = 0;
    short WS_PHS_NO = 0;
    double WS_REPY_AMT = 0;
    double WS_REM_PRIN_AMT = 0;
    double WS_PHS_PRIN_AMT = 0;
    double WS_P_REC_AMT = 0;
    double WS_I_REC_AMT = 0;
    double WS_I_REC_AMT_FULL = 0;
    double WS_P_OVD_AMT = 0;
    double WS_I_OVD_AMT = 0;
    double WS_TMP_INT = 0;
    double WS_PYD_INT = 0;
    double WS_F_REC_AMT = 0;
    char WS_NO_PLPI_ID = ' ';
    short WS_TERM_CNT = 0;
    short WS_INCR_PERD = 0;
    double WS_INCR_AMT = 0;
    char WS_PLPI_REC_STS = ' ';
    String WS_ERR_MSG = " ";
    short WS_ST_TERM = 0;
    short WS_ED_TERM = 0;
    short WS_PERD = 0;
    char WS_PERD_UNIT = ' ';
    double WS_HRG_AMT = 0;
    int WS_SCH_DUE_DT = 0;
    int WS_DUE_DT = 0;
    int WS_WDAYS = 0;
    short WS_CUR_TERM = 0;
    char WS_AMT_TYP = ' ';
    short WS_RCVD_TERM = 0;
    char WS_LCCM_TYPE = ' ';
    int WS_DAYS1 = 0;
    short WS_BASDAYS = 0;
    long WS_LOW_CCY_INT_AMT = 0;
    double WS_LOW_CCY_INT_AMT1 = 0;
    double WS_LOW_CCY_INT_AMT2 = 0;
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    int WS_CAL_YYYYMMDD = 0;
    LNZICAL_REDEFINES63 REDEFINES63 = new LNZICAL_REDEFINES63();
    short WS_CAL_TERM = 0;
    int WS_I = 0;
    int WS_PART_NO = 0;
    double WS_LOAN_AMT = 0;
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    int WS_IDX = 0;
    double WS_FIRST_INT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_PLPI_FOUND_FLG = ' ';
    char WS_PLPI_FLG = ' ';
    char WS_BALL_FLG = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_SUBS_FLG = ' ';
    char WS_READ_LNTPLPI_FLG = ' ';
    char WS_PYIF_FLG = ' ';
    char WS_BALZ_FOUND_FLG = ' ';
    char WS_CLEAN_FLG = ' ';
    char WS_GRACE_TYP = ' ';
    char WS_DELAY_PAY_FLG = ' ';
    LNRICTL LNRICTL = new LNRICTL();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNCICUT LNCICUT = new LNCICUT();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCSUBP LNCSUBP = new LNCSUBP();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRDISC LNRDISC = new LNRDISC();
    LNRDSBL LNRDSBL = new LNRDSBL();
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCENSCH LNCENSCH = new LNCENSCH();
    LNCCMSCH LNCCMSCH = new LNCCMSCH();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    LNCRDISC LNCRDISC = new LNCRDISC();
    LNCLCCM LNCLCCM = new LNCLCCM();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNCFCPL LNCFCPL = new LNCFCPL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNRRELI LNRRELI = new LNRRELI();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    DCCUDKLN DCCUDKLN = new DCCUDKLN();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    LNCSILW LNCSILW = new LNCSILW();
    CICACCU CICACCU = new CICACCU();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    SCCGWA SCCGWA;
    LNCICAL LNCICAL;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA, LNCICAL LNCICAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICAL = LNCICAL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        LNCICAL.RC.RC_APP = "LN";
        LNCICAL.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.FUNC_TYPE);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
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
        if (LNCICAL.COMM_DATA.FUNC_CODE != 'I' 
            && LNCICAL.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICAL.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B100_GET_APRD_INF();
        if (pgmRtn) return;
        B100_GET_PROD_INF();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            WS_REPY_TYPE = 'C';
            CEP.TRC(SCCGWA, "WS-REPY-TYPE");
            R000_GROUP_LNTPAIP();
            if (pgmRtn) return;
        } else {
            WS_REPY_TYPE = 'I';
        }
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
        B100_GET_CAL_DUEDATE();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UN_DRAWDOWN, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_PROD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        CEP.TRC(SCCGWA, LNCSCKPD.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_ICTLM_IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        WS_ICTLM_IC_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        WS_ICTLM_P_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        WS_ICTLM_P_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
    }
    public void B100_GET_CAL_DUEDATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CMP_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
        WS_P_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
        WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
        if (LNCICTLM.REC_DATA.P_CAL_TERM < LNCICTLM.REC_DATA.P_CMP_TERM) {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '3';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
            LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_DT);
            WS_P_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_P_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
            WS_P_DUE_DT_ERR = LNCPLPIM.REC_DATA.DUE_DT;
        }
        if (LNCICTLM.REC_DATA.IC_CAL_TERM < LNCICTLM.REC_DATA.IC_CMP_TERM) {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '3';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_TYPE;
            LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            WS_IC_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_IC_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
            WS_IC_DUE_DT_ERR = LNCPLPIM.REC_DATA.DUE_DT;
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_DT);
        }
        if (WS_P_DUE_DT_ERR > LNCICTLM.REC_DATA.P_CAL_DUE_DT 
            || WS_IC_DUE_DT_ERR > LNCICTLM.REC_DATA.IC_CAL_DUE_DT) {
            B320_UPD_ICTL_LAST_TERM_ERR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_P_DUE_DT);
        CEP.TRC(SCCGWA, WS_IC_DUE_DT);
    }
    public void B100_CHG_ERR_P_CAL_DUE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '3';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_DT);
        WS_P_DUE_DT_ERR = LNCPLPIM.REC_DATA.DUE_DT;
        if (WS_P_DUE_DT_ERR > LNCICTLM.REC_DATA.P_CAL_DUE_DT) {
            B320_UPD_ICTL_LAST_TERM_ERR();
            if (pgmRtn) return;
        }
    }
    public void B100_CHG_ERR_IC_CAL_DUE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '3';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_TYPE;
        LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_IC_DUE_DT_ERR = LNCPLPIM.REC_DATA.DUE_DT;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_DT);
        if (WS_IC_DUE_DT_ERR > LNCICTLM.REC_DATA.IC_CAL_DUE_DT) {
            B320_UPD_ICTL_LAST_TERM_ERR();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_APRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        LNCICAL.COMM_DATA.PRI_AMT = 0;
        LNCICAL.COMM_DATA.INT_AMT = 0;
        if (LNCICAL.COMM_DATA.FUNC_TYPE == 'P' 
            || LNCICAL.COMM_DATA.FUNC_TYPE == ' ') {
            B202_CHECK_OUTSTANDING_BAL();
            if (pgmRtn) return;
            if (WS_BALL_FLG == 'Y') {
                CEP.TRC(SCCGWA, WS_P_DUE_DT);
                CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CMP_DUE_DT);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_DUE_DT);
                if (LNCAPRDM.REC_DATA.PAY_MTH == '3' 
                    && LNCICTLM.REC_DATA.P_CMP_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE 
                    && LNCICTLM.REC_DATA.P_CMP_DUE_DT != 0 
                    && LNCCONTM.REC_DATA.MAT_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
                    B400_P_CMP_PROC();
                    if (pgmRtn) return;
                    B100_GET_ICTL_INF();
                    if (pgmRtn) return;
                    B100_GET_CAL_DUEDATE();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_P_DUE_DT);
                CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
                CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_DUE_DT);
                CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
                if ((WS_P_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE 
                    && LNCAPRDM.REC_DATA.PAY_MTH != '4') 
                    || (LNCICTLM.REC_DATA.P_CAL_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE 
                    && LNCAPRDM.REC_DATA.PAY_MTH == '3')) {
                    B400_P_CAL_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(29 - 1, 29 + 1 - 1).equalsIgnoreCase("1")) {
            WS_DELAY_PAY_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_DUE_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.FUNC_TYPE);
        if (LNCICAL.COMM_DATA.FUNC_TYPE == 'I' 
            || LNCICAL.COMM_DATA.FUNC_TYPE == ' ') {
            if (LNCICTLM.REC_DATA.IC_CMP_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE 
                && LNCICTLM.REC_DATA.IC_CMP_DUE_DT != 0 
                && LNCAPRDM.REC_DATA.CAL_PERD != 0 
                && LNCCONTM.REC_DATA.MAT_DATE >= SCCGWA.COMM_AREA.AC_DATE 
                && LNCICTLM.REC_DATA.IC_CMP_VAL_DT < LNCCONTM.REC_DATA.MAT_DATE) {
                B200_IC_CMP_PROC_2();
                if (pgmRtn) return;
                B100_GET_ICTL_INF();
                if (pgmRtn) return;
                B100_CHG_ERR_IC_CAL_DUE_DT();
                if (pgmRtn) return;
            }
            if (LNCICTLM.REC_DATA.IC_CAL_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE) {
                B300_IC_CAL_PROC();
                if (pgmRtn) return;
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1") 
                    && LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                    && LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
                    B316_UPDATE_INTA();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B200_IC_CMP_PROC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCFCPL);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNCFCPL.COMM_DATA.PAY_MTH = '4';
        } else {
            LNCFCPL.COMM_DATA.PAY_MTH = '2';
        }
        LNCFCPL.COMM_DATA.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZFCPL();
        if (pgmRtn) return;
    }
    public void B200_IC_CMP_PROC() throws IOException,SQLException,Exception {
        WS_NO_PLPI_ID = 'N';
        WS_IC_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
        WS_IC_VAL_DT = LNCICTLM.REC_DATA.IC_CMP_VAL_DT;
        WS_IC_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
        WS_PHS_NO = LNCICTLM.REC_DATA.IC_CMP_PHS_NO;
        if (WS_IC_DUE_DT <= WS_IC_VAL_DT) {
            CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
            CEP.TRC(SCCGWA, WS_IC_DUE_DT);
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.MAT_DATE);
            if (LNCAPRDM.REC_DATA.PAY_MTH != '4' 
                || WS_IC_DUE_DT < LNCCONTM.REC_DATA.MAT_DATE) {
                B201_COMPUTE_IC_DUE_DT();
                if (pgmRtn) return;
            }
        }
        if (WS_IC_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE) {
            B000_GET_REPY_AMT();
            if (pgmRtn) return;
        }
        while ((WS_IC_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE) 
            && (WS_NO_PLPI_ID != 'Y') 
            && (WS_IC_VAL_DT < LNCCONTM.REC_DATA.MAT_DATE)) {
            B200_IC_CMP_TERM_PROC();
            if (pgmRtn) return;
        }
        B210_UPD_ICTL();
        if (pgmRtn) return;
        if (WS_REPY_TYPE == 'C' 
            && WS_NO_PLPI_ID == 'N') {
            B211_UPD_PAIP_CMP();
            if (pgmRtn) return;
        }
    }
    public void B201_COMPUTE_IC_DUE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_IC_VAL_DT;
        if (WS_REPY_TYPE == 'C') {
            if (WS_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = WS_PERD;
            } else {
                SCCCLDT.MTHS = WS_PERD;
            }
        } else {
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
            } else {
                SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
            }
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        if (SCCCLDT.DATE2 > LNCCONTM.REC_DATA.MAT_DATE) {
            WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
        } else {
            WS_IC_DUE_DT = SCCCLDT.DATE2;
        }
    }
    public void B202_CHECK_OUTSTANDING_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT);
        if (LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT > 0) {
            WS_BALL_FLG = 'Y';
        } else {
            WS_BALL_FLG = 'N';
        }
    }
    public void B203_GET_REPAYED_INTEREST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQRCVI";
        LNCCNEX.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
    }
    public void B200_IC_CMP_TERM_PROC() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.IC_CAL_TERM == WS_IC_TERM) {
            WS_IC_CAL_DUE_DT = WS_IC_DUE_DT;
        }
        if (WS_IC_TERM >= LNCICTLM.REC_DATA.IC_CMP_TERM) {
            B210_CRE_PLPI_REC();
            if (pgmRtn) return;
            B211_GEN_NEXT_CMPDATA();
            if (pgmRtn) return;
        }
    }
    public void B210_CRE_PLPI_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '0';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_TYPE;
        LNCPLPIM.REC_DATA.KEY.TERM = WS_IC_TERM;
        LNCPLPIM.REC_DATA.VAL_DT = WS_IC_VAL_DT;
        LNCPLPIM.REC_DATA.DUE_DT = WS_IC_DUE_DT;
        LNCPLPIM.REC_DATA.REC_STS = '0';
        LNCPLPIM.REC_DATA.DUE_REPY_AMT = WS_REPY_AMT;
        if (LNCPAIPM.REC_DATA.INST_MTH == '2') {
            LNCPLPIM.REC_DATA.PRIN_AMT = WS_REPY_AMT;
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B211_GEN_NEXT_CMPDATA() throws IOException,SQLException,Exception {
        WS_IC_TERM += 1;
        BS06_NEXT_DATA_PROC();
        if (pgmRtn) return;
        if (WS_REPY_TYPE == 'C') {
            WS_PHS_CMP_TERM += 1;
            if (WS_PHS_CMP_TERM == LNCPAIPM.REC_DATA.PHS_TOT_TERM) {
                B211_UPD_PAIP_CMP();
                if (pgmRtn) return;
                WS_PHS_NO += 1;
                BS01_GET_PAIP_INF();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND)) {
                    WS_PHS_NO -= 1;
                }
            }
        }
    }
    public void B300_IC_CAL_PROC() throws IOException,SQLException,Exception {
        WS_NO_PLPI_ID = 'N';
        WS_IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
        WS_IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CMP_VAL_DT;
        WS_IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
        WS_IC_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        WS_IC_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        WS_IC_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        WS_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
        if (WS_REPY_TYPE == 'C') {
            BS01_GET_PAIP_INF();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_IC_DUE_DT);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, WS_IC_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
        CEP.TRC(SCCGWA, WS_NO_PLPI_ID);
        CEP.TRC(SCCGWA, WS_IC_VAL_DT);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.MAT_DATE);
        while ((WS_IC_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE) 
            && (WS_IC_TERM < LNCICTLM.REC_DATA.IC_CMP_TERM) 
            && (WS_NO_PLPI_ID != 'Y') 
            && (WS_IC_VAL_DT < LNCCONTM.REC_DATA.MAT_DATE)) {
            B300_IC_CAL_TERM_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_PHS_CAL_TERM);
            B310_UPDATE_LNTICTL();
            if (pgmRtn) return;
            if (WS_DELAY_PAY_FLG == 'Y' 
                && WS_IC_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE 
                && WS_IC_VAL_DT < LNCCONTM.REC_DATA.MAT_DATE) {
                B311_UPD_PAIP_CAL();
                if (pgmRtn) return;
                B200_IC_CMP_PROC_2();
                if (pgmRtn) return;
                B100_GET_ICTL_INF();
                if (pgmRtn) return;
                B100_CHG_ERR_IC_CAL_DUE_DT();
                if (pgmRtn) return;
                WS_IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
                WS_IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CMP_VAL_DT;
                WS_IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
                WS_IC_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
                WS_IC_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
                WS_IC_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
                WS_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
                WS_CAL_TERM = WS_PHS_CAL_TERM;
                BS01_GET_PAIP_INF();
                if (pgmRtn) return;
                WS_PHS_CAL_TERM = WS_CAL_TERM;
            }
        }
        CEP.TRC(SCCGWA, WS_REPY_TYPE);
        CEP.TRC(SCCGWA, WS_NO_PLPI_ID);
        CEP.TRC(SCCGWA, WS_PHS_CAL_TERM);
        if (WS_REPY_TYPE == 'C' 
            && WS_NO_PLPI_ID == 'N') {
            B311_UPD_PAIP_CAL();
            if (pgmRtn) return;
        }
    }
    public void B300_IC_CAL_TERM_PROC() throws IOException,SQLException,Exception {
        B310_IC_CAL_TERM_PROC();
        if (pgmRtn) return;
        B320_GEN_NEXT_CALDATA();
        if (pgmRtn) return;
    }
    public void B310_IC_CAL_TERM_PROC() throws IOException,SQLException,Exception {
        WS_P_REC_AMT = 0;
        B310_READ_PLPI_UPD();
        if (pgmRtn) return;
        B310_INT_CONT_INQ();
        if (pgmRtn) return;
        B311_GEN_TERM_PIAMT();
        if (pgmRtn) return;
        B312_REWRITE_PLPI_REC();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.SYS_FLG != 'Y') {
            WS_AMT_TYP = 'I';
            WS_RCVD_TERM = WS_IC_TERM;
            WS_SCH_DUE_DT = WS_RCVD_DUE_DT;
            B100_GET_PRIN_DOG_DT();
            if (pgmRtn) return;
        }
        B313_CRE_RCVD_REC();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            B314_P_I_CNTL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B310_INQ_TMP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQTMPI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        WS_TMP_INT = LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT + 0;
        CEP.TRC(SCCGWA, LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_ID);
        CEP.TRC(SCCGWA, WS_TMP_INT);
    }
    public void B310_INQ_PYD_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQPYDI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICNQ();
        if (pgmRtn) return;
        WS_PYD_INT = LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT;
    }
    public void B310_INT_CONT_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQPOST";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICNQ();
        if (pgmRtn) return;
    }
    public void B311_GEN_TERM_PIAMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
        CEP.TRC(SCCGWA, WS_PHS_PRIN_AMT);
        if ((LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL") 
            && WS_IC_TERM == 1) 
            || LNCAPRDM.REC_DATA.PAY_MTH == '0') {
            IBS.init(SCCGWA, LNCICUT);
        } else {
            B3111_INT_CUT_PROC();
            if (pgmRtn) return;
        }
        if (WS_REPY_TYPE == 'C') {
            if (WS_REM_PRIN_AMT != 0) {
                B3110_PAI_PRI_PROC();
                if (pgmRtn) return;
            } else {
                if (WS_PHS_PRIN_AMT == 0) {
                    WS_PHS_CAL_TERM += 1;
                }
                WS_P_REC_AMT = 0;
            }
        } else {
            WS_P_REC_AMT = 0;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRRELI);
            LNRRELI.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            CEP.TRC(SCCGWA, LNRRELI.TERM);
            CEP.TRC(SCCGWA, LNRRELI.TOT_F_AMT);
            CEP.TRC(SCCGWA, LNRRELI.F_REC_AMT);
            WS_F_REC_AMT = ( LNRRELI.TOT_F_AMT - LNRRELI.F_REC_AMT ) / ( LNRRELI.TERM - WS_IC_TERM + 1 );
            bigD = new BigDecimal(WS_F_REC_AMT);
            WS_F_REC_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            CEP.TRC(SCCGWA, WS_F_REC_AMT);
            LNRRELI.F_REC_AMT += WS_F_REC_AMT;
        }
        CEP.TRC(SCCGWA, WS_PHS_CAL_TERM);
    }
    public void B3110_PAI_PRI_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_IC_TERM);
        CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.INST_MTH);
        if (LNCPAIPM.REC_DATA.INST_MTH == '2') {
            WS_P_REC_AMT = WS_REPY_AMT;
        } else {
            WS_P_REC_AMT = WS_REPY_AMT - WS_I_REC_AMT;
        }
        WS_PHS_CAL_TERM += 1;
        if (WS_PHS_CAL_TERM == LNCPAIPM.REC_DATA.PHS_TOT_TERM) {
            WS_P_REC_AMT = WS_REM_PRIN_AMT;
        }
        if (WS_P_REC_AMT < WS_REM_PRIN_AMT) {
            WS_REM_PRIN_AMT -= WS_P_REC_AMT;
        } else {
            WS_P_REC_AMT = WS_REM_PRIN_AMT;
            WS_REM_PRIN_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
    }
    public void B3111_PAI_PRI_PROC() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.INST_MTH == '1') {
            WS_REPY_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
            B3113_PAI_PRI_FST_PROC_A();
            if (pgmRtn) return;
        } else {
            WS_I_REC_AMT_FULL = WS_I_REC_AMT;
        }
        CEP.TRC(SCCGWA, WS_REPY_AMT);
        CEP.TRC(SCCGWA, WS_I_REC_AMT_FULL);
        WS_P_REC_AMT = WS_REPY_AMT - WS_I_REC_AMT_FULL;
    }
    public void B3113_PAI_PRI_FST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.AMT = WS_REM_PRIN_AMT;
        LNCICUT.COMM_DATA.BEG_DATE = WS_IC_VAL_DT;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_IC_VAL_DT;
        if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
            SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
        } else {
            SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        LNCICUT.COMM_DATA.END_DATE = SCCCLDT.DATE2;
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
    }
    public void B3113_PAI_PRI_FST_PROC_A() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("01")
            || LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("04")
            || LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("05")) {
            WS_BASDAYS = 360;
        } else if (LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("02")) {
            WS_BASDAYS = 365;
        } else if (LNCAPRDM.REC_DATA.INT_DBAS_STD.equalsIgnoreCase("03")) {
            WS_BASDAYS = 366;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INTDAY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.PERD_UNIT);
        CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.PERD);
        if (LNCPAIPM.REC_DATA.PERD_UNIT == 'M') {
            WS_DAYS1 = LNCPAIPM.REC_DATA.PERD * 30;
        } else {
            WS_DAYS1 = LNCPAIPM.REC_DATA.PERD;
        }
        CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.PHS_PRIN_AMT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
        CEP.TRC(SCCGWA, WS_DAYS1);
        CEP.TRC(SCCGWA, WS_BASDAYS);
        WS_I_REC_AMT_FULL = WS_REM_PRIN_AMT * LNCICUT.COMM_DATA.RATE * WS_DAYS1 / ( 100 * WS_BASDAYS );
        bigD = new BigDecimal(WS_I_REC_AMT_FULL);
        WS_I_REC_AMT_FULL = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, WS_I_REC_AMT_FULL);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CCY);
        if (!LNCCONTM.REC_DATA.CCY.equalsIgnoreCase("156")) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = LNCCONTM.REC_DATA.CCY;
            S00_CALL_BPZQCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
            if (BPCQCCY.DATA.DEC_MTH == 0) {
                WS_LOW_CCY_INT_AMT = WS_I_REC_AMT_FULL + 0;
                bigD = new BigDecimal(WS_LOW_CCY_INT_AMT);
                WS_LOW_CCY_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_I_REC_AMT_FULL = WS_LOW_CCY_INT_AMT;
            }
            if (BPCQCCY.DATA.DEC_MTH == 1) {
                WS_LOW_CCY_INT_AMT1 = WS_I_REC_AMT_FULL + 0;
                bigD = new BigDecimal(WS_LOW_CCY_INT_AMT1);
                WS_LOW_CCY_INT_AMT1 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_I_REC_AMT_FULL = WS_LOW_CCY_INT_AMT1;
            }
            if (BPCQCCY.DATA.DEC_MTH == 2) {
                WS_LOW_CCY_INT_AMT2 = WS_I_REC_AMT_FULL + 0;
                bigD = new BigDecimal(WS_LOW_CCY_INT_AMT2);
                WS_LOW_CCY_INT_AMT2 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_I_REC_AMT_FULL = WS_LOW_CCY_INT_AMT2;
            }
            CEP.TRC(SCCGWA, WS_I_REC_AMT_FULL);
        }
    }
    public void B3112_PAI_PRI_PROC() throws IOException,SQLException,Exception {
        B3113_CHECK_ERRP_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REPY_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
            B3113_PAI_PRI_FST_PROC_A();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNCICUT);
            LNCICUT.COMM_DATA.FUNC_CODE = 'I';
            LNCICUT.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
            LNCICUT.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
            LNCICUT.COMM_DATA.TYPE = 'I';
            if (LNCAPRDM.REC_DATA.SPEC_LN_FLG == '1' 
                && LNCPAIPM.REC_DATA.INST_MTH == '1') {
                CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
                LNCICUT.COMM_DATA.AMT = WS_REM_PRIN_AMT;
            }
            LNCICUT.COMM_DATA.END_DATE = WS_IC_DUE_DT;
            S000_CALL_LNZICUT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
            WS_I_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
        }
        if (WS_REPY_AMT < WS_I_REC_AMT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INT_GT_INST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B3113_CHECK_ERRP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        WS_PYIF_FLG = ' ';
        LNRPYIF.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRPYIF.KEY.SUB_CTA_NO = 0;
        else LNRPYIF.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNRPYIF.KEY.TERM = WS_IC_TERM;
        T000_STARTBR_LNTPYIF();
        if (pgmRtn) return;
    }
    public void B3111_INT_CUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'U';
        LNCICUT.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.END_DATE = WS_IC_DUE_DT;
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        WS_I_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
        CEP.TRC(SCCGWA, WS_TMP_INT);
        CEP.TRC(SCCGWA, LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        CEP.TRC(SCCGWA, WS_I_REC_AMT);
    }
    public void B3111_INT_CUT_PROC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = 'I';
        LNCICUT.COMM_DATA.END_DATE = WS_IC_DUE_DT;
        LNCICUT.COMM_DATA.AMT = LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT;
        S000_CALL_LNZICUT();
        if (pgmRtn) return;
        WS_I_REC_AMT = LNCICUT.COMM_DATA.INT_AMT;
        CEP.TRC(SCCGWA, WS_TMP_INT);
        CEP.TRC(SCCGWA, LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT);
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
        CEP.TRC(SCCGWA, WS_I_REC_AMT);
    }
    public void B310_READ_PLPI_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_TYPE;
        LNCPLPIM.REC_DATA.KEY.TERM = WS_IC_TERM;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.TERM);
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        if (WS_REPY_TYPE == 'C') {
            CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.INST_MTH);
            if (LNCPAIPM.REC_DATA.INST_MTH == '2') {
                WS_REPY_AMT = LNCPLPIM.REC_DATA.PRIN_AMT;
            } else {
                WS_REPY_AMT = LNCPLPIM.REC_DATA.DUE_REPY_AMT;
            }
        } else {
            WS_REPY_AMT = 0;
        }
        if (LNCPLPIM.REC_DATA.VAL_DT < LNCICTLM.REC_DATA.INT_CUT_DT) {
            WS_IC_VAL_DT = LNCICTLM.REC_DATA.INT_CUT_DT;
        } else {
            WS_IC_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        }
        WS_IC_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        WS_RCVD_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_RCVD_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        WS_PLPI_REC_STS = LNCPLPIM.REC_DATA.REC_STS;
    }
    public void B312_REWRITE_PLPI_REC() throws IOException,SQLException,Exception {
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.REC_STS = '1';
        LNCPLPIM.REC_DATA.PRIN_AMT = WS_P_REC_AMT;
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        CEP.TRC(SCCGWA, WS_I_REC_AMT);
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            LNCPLPIM.REC_DATA.DUE_REPY_AMT = 0;
            LNCPLPIM.REC_DATA.DUE_REPY_AMT = WS_P_REC_AMT + WS_I_REC_AMT;
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B313_CRE_RCVD_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '0';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = WS_REPY_TYPE;
        LNCRCVDM.REC_DATA.KEY.TERM = WS_IC_TERM;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = 0;
        LNCRCVDM.REC_DATA.VAL_DT = WS_RCVD_VAL_DT;
        LNCRCVDM.REC_DATA.DUE_DT = WS_RCVD_DUE_DT;
        CEP.TRC(SCCGWA, WS_PLPI_REC_STS);
        if (WS_PLPI_REC_STS == 'P') {
            LNCRCVDM.REC_DATA.TERM_STS = '1';
        } else {
            LNCRCVDM.REC_DATA.TERM_STS = '0';
        }
        if (WS_PLPI_REC_STS == 'P' 
            || WS_PLPI_REC_STS == 'R') {
            LNCRCVDM.REC_DATA.CLR_TYP = 1;
        } else {
            LNCRCVDM.REC_DATA.CLR_TYP = 0;
        }
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.TERM_STS);
        if (WS_REPY_TYPE == 'I' 
            && LNCAPRDM.REC_DATA.PAY_MTH == '0') {
            LNCRCVDM.REC_DATA.REPY_STS = '2';
            B203_GET_REPAYED_INTEREST();
            if (pgmRtn) return;
            WS_I_REC_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
            LNCRCVDM.REC_DATA.I_PAY_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
            CEP.TRC(SCCGWA, WS_I_REC_AMT);
        } else {
            LNCRCVDM.REC_DATA.REPY_STS = '0';
        }
        LNCRCVDM.REC_DATA.OVD_DT = WS_DUE_DT;
        LNCRCVDM.REC_DATA.P_REC_AMT = WS_P_REC_AMT;
        LNCRCVDM.REC_DATA.F_REC_AMT = WS_F_REC_AMT;
        LNCRCVDM.REC_DATA.O_REC_AMT = 0;
        LNCRCVDM.REC_DATA.L_REC_AMT = 0;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            LNCRCVDM.REC_DATA.REC_FLG = 'N';
        } else {
            LNCRCVDM.REC_DATA.REC_FLG = 'Y';
        }
        LNCRCVDM.REC_DATA.I_REC_AMT = WS_I_REC_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
            && LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
            WS_HRG_AMT = WS_I_REC_AMT * ( LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100 );
            LNCRCVDM.REC_DATA.F_REC_AMT = WS_HRG_AMT;
        }
        CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.RATE);
        LNCRCVDM.REC_DATA.ALL_IN_RATE = LNCICUT.COMM_DATA.RATE;
        if (LNCRCVDM.REC_DATA.P_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.I_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.O_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.L_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.F_REC_AMT == 0) {
            LNCRCVDM.REC_DATA.REPY_STS = '2';
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (WS_REPY_TYPE != 'P' 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
            B315_DKLN_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRCVDM.RC.RC_RTNCODE);
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.TERM);
        LNCICAL.COMM_DATA.PRI_AMT += WS_P_REC_AMT;
        LNCICAL.COMM_DATA.INT_AMT += WS_I_REC_AMT;
        LNCICAL.COMM_DATA.I_BEG_DATE = WS_RCVD_VAL_DT;
        LNCICAL.COMM_DATA.I_END_DATE = WS_RCVD_DUE_DT;
        if (WS_IC_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_P_OVD_AMT += WS_P_REC_AMT;
            WS_I_OVD_AMT += WS_I_REC_AMT;
        }
        B313_SUBSIDIZE_PROCESS();
        if (pgmRtn) return;
    }
    public void B313_SUBSIDIZE_PROCESS() throws IOException,SQLException,Exception {
        B320_JUDGE_SUBS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_SUBS_FLG);
        if (WS_SUBS_FLG == 'Y') {
            B322_LOAN_SUBS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B314_P_I_CNTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "INTCALP";
        LNCCNEV.COMM_DATA.ACM_EVENT = "CAL";
        LNCCNEV.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = WS_IC_DUE_DT;
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        CEP.TRC(SCCGWA, WS_I_REC_AMT);
        if (WS_PLPI_REC_STS != 'P') {
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = WS_P_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_I_REC_AMT;
        } else {
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_P_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT = WS_P_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_I_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT = WS_I_REC_AMT;
        }
        LNCCNEV.COMM_DATA.P_AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT + LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT;
        LNCCNEV.COMM_DATA.I_AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT + LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0138610")) {
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if ((LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) 
            && LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT != 0) {
            R000_SYLN_PROC();
            if (pgmRtn) return;
        }
        if (WS_IDX == 1) {
            LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL);
            CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL);
            CEP.TRC(SCCGWA, WS_FIRST_INT);
            CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[15-1].LOAN_BAL);
            CEP.TRC(SCCGWA, WS_LOAN_AMT);
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = LNCCNEV.COMM_DATA.LN_AC;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            IBS.init(SCCGWA, LNCIGVCY);
            LNCIGVCY.DATA.EVENT_CODE = "IA";
            LNCIGVCY.DATA.AMT_INFO[29-1].AMT = WS_LOAN_AMT;
            LNCIGVCY.DATA.CNTR_TYPE = LNCCONTM.REC_DATA.CONTRACT_TYPE;
            LNCIGVCY.DATA.PROD_CODE_OLD = LNCCONTM.REC_DATA.PROD_CD;
            LNCIGVCY.DATA.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCIGVCY.DATA.SUB_CTA_NO = 0;
            else LNCIGVCY.DATA.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNCIGVCY.DATA.BR_OLD = LNCCONTM.REC_DATA.BOOK_BR;
            LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNCCONTM.REC_DATA.CCY;
            LNCIGVCY.DATA.VALUE_DATE = WS_IC_DUE_DT;
            LNCIGVCY.DATA.CI_NO = CICACCU.DATA.CI_NO;
            LNCIGVCY.DATA.STATUS = LNCICTLM.REC_DATA.CTL_STSW;
        }
    }
    public void B314_01_POST_FIRST_CALTERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICUT);
        LNCICUT.COMM_DATA.FUNC_CODE = 'I';
        LNCICUT.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCICUT.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCICUT.COMM_DATA.TYPE = 'A';
        LNCICUT.COMM_DATA.BEG_DATE = SCCBATH.JPRM.AC_DATE;
        LNCICUT.COMM_DATA.END_DATE = WS_RCVD_DUE_DT;
        LNCICUT.COMM_DATA.INCLUDE_ADJ_FLG = 'N';
        CEP.TRC(SCCGWA, WS_BEG_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (LNCICUT.COMM_DATA.END_DATE > LNCCONTM.REC_DATA.MAT_DATE) {
            LNCICUT.COMM_DATA.END_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.INT_CUT_DT > WS_END_DATE) {
            LNCICUT.COMM_DATA.INT_AMT = 0;
        } else {
            if (LNCICTLM.REC_DATA.INT_CUT_DT <= LNCCONTM.REC_DATA.MAT_DATE 
                && WS_BEG_DATE < LNCCONTM.REC_DATA.MAT_DATE) {
                S000_CALL_LNZICUT();
                if (pgmRtn) return;
            }
        }
        WS_FIRST_INT = LNCICUT.COMM_DATA.INT_AMT;
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_LNZIGVCY() throws IOException,SQLException,Exception {
        LNCIGVCY.DATA.ACCEPT_FLG = 'Y';
        LNCIGVCY.DATA.ACCEPT_DATA.APRD_DATA.APRD_ACCRUAL_TYPE = LNCAPRDM.REC_DATA.ACCRUAL_TYPE;
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCY", LNCIGVCY);
        if (LNCIGVCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGVCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_SYLN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPAMT);
        LNCIPAMT.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
        LNCIPAMT.I_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
        R000_CALL_LNZIPAMT();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10 
            && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
            if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_I-1].PART_NO);
                WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                R000_READUPD_LNTBALZ_PART();
                if (pgmRtn) return;
                LNRBALZ.LOAN_BALL15 = LNRBALZ.LOAN_BALL15 - LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                if (WS_BALZ_FOUND_FLG == 'Y') {
                    R000_REWRITE_LNTBALZ_PART();
                    if (pgmRtn) return;
                } else {
                    R000_WRITE_LNTBALZ_PART();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B317_INIT_TMP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQTMPI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        IBS.init(SCCGWA, LNCCNBU);
        LNCCNBU.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCCNBU.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCCNBU.COMM_DATA.VAL_DATE = LNCICAL.COMM_DATA.VAL_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
        LNCCNBU.COMM_DATA.AMT = 0;
        LNCCNBU.COMM_DATA.AS_IND = 'I';
        LNCCNBU.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNBU();
        if (pgmRtn) return;
    }
    public void B317_INIT_PYD_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQPYDI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICNQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCNBU);
        LNCCNBU.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCCNBU.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCCNBU.COMM_DATA.VAL_DATE = LNCICAL.COMM_DATA.VAL_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
        LNCCNBU.COMM_DATA.AMT = 0;
        LNCCNBU.COMM_DATA.AS_IND = 'I';
        LNCCNBU.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNBU();
        if (pgmRtn) return;
    }
    public void B320_GEN_NEXT_CALDATA() throws IOException,SQLException,Exception {
        BS07_NEXT_DATA_PROC();
        if (pgmRtn) return;
        if (WS_REPY_TYPE == 'C') {
            if (WS_PHS_CAL_TERM == LNCPAIPM.REC_DATA.PHS_TOT_TERM) {
                B311_UPD_PAIP_CAL();
                if (pgmRtn) return;
                WS_PHS_NO += 1;
                BS01_GET_PAIP_INF();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND)) {
                    WS_PHS_NO -= 1;
                }
            }
        }
    }
    public void B315_DKLN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WS-I-REC-AMT =");
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        CEP.TRC(SCCGWA, "WS-P-REC-AMT =");
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        IBS.init(SCCGWA, DCCUDKLN);
        IBS.init(SCCGWA, LNCSIQIF);
        DCCUDKLN.IO_AREA.FUNC = '1';
        DCCUDKLN.IO_AREA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCUDKLN.IO_AREA.JRN_NO = LNCRCVDM.REC_DATA.KEY.TERM;
        DCCUDKLN.IO_AREA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCSIQIF.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZSIQIF();
        if (pgmRtn) return;
        DCCUDKLN.IO_AREA.LN_BAL = LNCSIQIF.BAL;
        DCCUDKLN.IO_AREA.LN_RAT = LNCSIQIF.IN_RATE;
        DCCUDKLN.IO_AREA.INT_AMT = WS_I_REC_AMT;
        S000_CALL_DCZUDKLN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "UDKLN-INC-AMT =");
        CEP.TRC(SCCGWA, DCCUDKLN.IO_AREA.INC_AMT);
        CEP.TRC(SCCGWA, DCCUDKLN.IO_AREA.INC_AMT);
        if (DCCUDKLN.IO_AREA.INC_AMT > WS_I_REC_AMT) {
            DCCUDKLN.IO_AREA.INC_AMT = WS_I_REC_AMT;
        }
        R000_READ_LNTBALZ();
        if (pgmRtn) return;
        WS_P_REC_AMT = WS_P_REC_AMT + DCCUDKLN.IO_AREA.INC_AMT;
        if (WS_P_REC_AMT > LNRBALZ.LOAN_BALL02) {
            WS_P_REC_AMT = LNRBALZ.LOAN_BALL02;
        }
        WS_I_REC_AMT = WS_I_REC_AMT - DCCUDKLN.IO_AREA.INC_AMT;
        LNCRCVDM.REC_DATA.I_REC_AMT = WS_I_REC_AMT;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "WS-I-REC-AMT =");
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        CEP.TRC(SCCGWA, "WS-P-REC-AMT =");
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = 'P';
        LNCRCVDM.REC_DATA.KEY.TERM = WS_IC_TERM;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = 0;
        S000_CALL_LNZRCVDM_1();
        if (pgmRtn) return;
        if (LNCRCVDM.RC.RC_RTNCODE == 1516) {
            LNCRCVDM.FUNC = '4';
            LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNCRCVDM.REC_DATA.KEY.AMT_TYP = 'C';
            LNCRCVDM.REC_DATA.KEY.TERM = WS_IC_TERM;
            LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = 0;
            S000_CALL_LNZRCVDM();
            if (pgmRtn) return;
        }
        LNCRCVDM.FUNC = '2';
        LNCRCVDM.REC_DATA.P_REC_AMT = WS_P_REC_AMT;
    }
    public void B316_UPDATE_INTA() throws IOException,SQLException,Exception {
        R000_READUPD_LNTBALZ_PART();
        if (pgmRtn) return;
        LNRBALZ.LOAN_BALL15 -= DCCUDKLN.IO_AREA.INC_AMT;
        R000_REWRITE_LNTBALZ_PART();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCIGVCY);
        LNCIGVCY.DATA.CNTR_TYPE = "CLDD";
        LNCIGVCY.DATA.PROD_CODE_OLD = LNCCONTM.REC_DATA.PROD_CD;
        LNCIGVCY.DATA.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
        LNCIGVCY.DATA.EVENT_CODE = "IA";
        LNCIGVCY.DATA.BR_OLD = LNCCONTM.REC_DATA.BOOK_BR;
        LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNCCONTM.REC_DATA.CCY;
        LNCIGVCY.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCIGVCY.DATA.CI_NO = CICACCU.DATA.CI_NO;
        LNCIGVCY.DATA.STATUS = LNCICTLM.REC_DATA.CTL_STSW;
        LNCIGVCY.DATA.AMT_INFO[26-1].AMT = DCCUDKLN.IO_AREA.INC_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            LNCIGVCY.DATA.AMT_INFO[30-1].AMT = DCCUDKLN.IO_AREA.INC_AMT;
            LNCIGVCY.DATA.AMT_INFO[26-1].AMT = 0;
        }
        S000_CALL_LNZIGVCY();
        if (pgmRtn) return;
    }
    public void B400_P_CMP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCFCPL);
        LNCFCPL.COMM_DATA.PAY_MTH = '5';
        LNCFCPL.COMM_DATA.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZFCPL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZFCPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CAL-PLPI", LNCFCPL);
        if (LNCFCPL.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCFCPL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B400_GET_SCHT_ST_DT() throws IOException,SQLException,Exception {
        T000_READ_LNTPLPI_NOT_I();
        if (pgmRtn) return;
        if (WS_READ_LNTPLPI_FLG == 'N') {
            WS_SCH_STR_DT = LNCCONTM.REC_DATA.START_DATE;
        } else {
            WS_SCH_STR_DT = LNRPLPI.DUE_DT;
        }
    }
    public void B400_CREAT_P_SCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCENSCH);
        LNCENSCH.COMM_DATA.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
        LNCENSCH.COMM_DATA.SUB_CTA_NO = 0;
        LNCENSCH.COMM_DATA.REPY_TYP = 'P';
        LNCENSCH.COMM_DATA.PFNP_DAT = LNCICTLM.REC_DATA.P_CMP_DUE_DT;
        LNCENSCH.COMM_DATA.PFRE_TERM = LNCAPRDM.REC_DATA.PAYP_PERD;
        LNCENSCH.COMM_DATA.PFRE_TYP = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
        S000_CALL_LNZENSCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCMSCH);
        LNCCMSCH.COMM_DATA.SEQ_NO = LNCENSCH.COMM_DATA.SEQ;
        LNCCMSCH.COMM_DATA.CTA_NO = LNCICAL.COMM_DATA.LN_AC;
        CEP.TRC(SCCGWA, LNCCMSCH.COMM_DATA.SEQ_NO);
        CEP.TRC(SCCGWA, LNCCMSCH.COMM_DATA.CTA_NO);
        S000_CALL_SVR_LNZCMSCH();
        if (pgmRtn) return;
    }
    public void B400_GET_P_CAL_TERM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '3';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.VAL_DT);
        WS_P_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_P_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
    }
    public void B400_P_CAL_PROC() throws IOException,SQLException,Exception {
        WS_P_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        CEP.TRC(SCCGWA, WS_P_DUE_DT);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, WS_P_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CMP_TERM);
        CEP.TRC(SCCGWA, WS_P_DUE_DT);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.MAT_DATE);
        while ((WS_P_DUE_DT <= LNCICAL.COMM_DATA.VAL_DATE) 
            && (WS_P_TERM < LNCICTLM.REC_DATA.P_CMP_TERM) 
            && (WS_P_DUE_DT <= LNCCONTM.REC_DATA.MAT_DATE)) {
            B400_P_CAL_TERM_PROC();
            if (pgmRtn) return;
        }
    }
    public void B400_P_CAL_TERM_PROC() throws IOException,SQLException,Exception {
        B411_UPDATE_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PLPI_FLG);
        if (WS_PLPI_FLG == 'Y') {
            if (LNCCONTM.REC_DATA.SYS_FLG != 'Y') {
                WS_AMT_TYP = 'P';
                WS_RCVD_TERM = WS_P_TERM;
                WS_SCH_DUE_DT = WS_P_DUE_DT;
                B100_GET_PRIN_DOG_DT();
                if (pgmRtn) return;
            }
            B412_CRE_RCVD_REC();
            if (pgmRtn) return;
            B413_P_CNTL_PROC();
            if (pgmRtn) return;
        }
        B420_GEN_NEXT_CALDATA();
        if (pgmRtn) return;
        B430_UPDATE_LNTICTL();
        if (pgmRtn) return;
    }
    public void B400_LDC_UPDATE() throws IOException,SQLException,Exception {
    }
    public void B411_UPDATE_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = WS_P_TERM;
        CEP.TRC(SCCGWA, WS_P_TERM);
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_P_REC_AMT = LNCPLPIM.REC_DATA.PRIN_AMT;
        WS_P_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_P_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        WS_PLPI_REC_STS = LNCPLPIM.REC_DATA.REC_STS;
        if (LNCPLPIM.REC_DATA.REC_STS != '1') {
            WS_PLPI_FLG = 'Y';
        } else {
            WS_PLPI_FLG = 'N';
        }
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.REC_STS = '1';
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TEST1");
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
    }
    public void B100_GET_PRIN_DOG_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CUR_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CUR_TERM);
        if (WS_AMT_TYP == 'P') {
            WS_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        } else {
            WS_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        }
        WS_GRACE_TYP = LNCAPRDM.REC_DATA.GRACE_TYP;
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.GRACE_TYP);
        CEP.TRC(SCCGWA, WS_CUR_TERM);
        CEP.TRC(SCCGWA, WS_RCVD_TERM);
        if (WS_CUR_TERM == WS_RCVD_TERM) {
            if ((WS_AMT_TYP == 'P' 
                && WS_ICTLM_IC_CUR_TERM == WS_ICTLM_IC_CAL_TERM 
                && (WS_SCH_DUE_DT != LNCCONTM.REC_DATA.MAT_DATE)) 
                || (WS_AMT_TYP == 'I' 
                && LNCAPRDM.REC_DATA.PAY_MTH != '4' 
                && WS_ICTLM_P_CUR_TERM == WS_ICTLM_P_CAL_TERM 
                && (WS_SCH_DUE_DT != LNCCONTM.REC_DATA.MAT_DATE)) 
                || (WS_AMT_TYP == 'I' 
                && LNCAPRDM.REC_DATA.PAY_MTH == '4' 
                && (WS_SCH_DUE_DT != LNCCONTM.REC_DATA.MAT_DATE))) {
                if (WS_GRACE_TYP == '1') {
                    WS_CAL_YYYYMMDD = WS_SCH_DUE_DT;
                    IBS.CPY2CLS(SCCGWA, WS_CAL_YYYYMMDD+"", REDEFINES63);
                    R000_CAL_MONTH_END();
                    if (pgmRtn) return;
                    WS_DUE_DT = WS_CAL_YYYYMMDD;
                }
                if (WS_GRACE_TYP == '2') {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = WS_SCH_DUE_DT;
                    SCCCLDT.DAYS = LNCAPRDM.REC_DATA.PRIN_DOG;
                    if (LNCAPRDM.REC_DATA.PRIN_DOG > 0) {
                        S000_CALL_SCSSCLDT();
                        if (pgmRtn) return;
                        WS_DUE_DT = SCCCLDT.DATE2;
                    } else {
                        WS_DUE_DT = WS_SCH_DUE_DT;
                    }
                }
                if (WS_GRACE_TYP == ' ') {
                    WS_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
                }
            } else {
                WS_DUE_DT = WS_SCH_DUE_DT;
            }
        } else {
            WS_DUE_DT = WS_SCH_DUE_DT;
        }
        CEP.TRC(SCCGWA, "CKPD-VCT-FLG");
        CEP.TRC(SCCGWA, LNCSCKPD.VCT_FLG);
        if (LNCSCKPD.VCT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "HOLIDAY CAL");
            CEP.TRC(SCCGWA, WS_DUE_DT);
            IBS.init(SCCGWA, BPCOCLWD);
            if (LNCSCKPD.PROD_MOD == 'R') {
                BPCOCLWD.CAL_CODE = "PN";
            } else {
                BPCOCLWD.CAL_CODE = "CN";
            }
            BPCOCLWD.DATE1 = WS_DUE_DT;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = 1;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1_FLG);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            WS_DUE_DT = BPCOCLWD.DATE2;
        }
    }
    public void R000_CAL_MONTH_END() throws IOException,SQLException,Exception {
        if (REDEFINES63.WS_CAL_MM == 02) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = WS_CAL_YYYYMMDD;
            R000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (SCCCKDT.LEAP_YEAR == 1) {
                REDEFINES63.WS_CAL_DD = 29;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES63);
                WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            } else {
                REDEFINES63.WS_CAL_DD = 28;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES63);
                WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        }
        if (REDEFINES63.WS_CAL_MM == 01 
            || REDEFINES63.WS_CAL_MM == 03 
            || REDEFINES63.WS_CAL_MM == 05 
            || REDEFINES63.WS_CAL_MM == 07 
            || REDEFINES63.WS_CAL_MM == 08 
            || REDEFINES63.WS_CAL_MM == 10 
            || REDEFINES63.WS_CAL_MM == 12) {
            REDEFINES63.WS_CAL_DD = 31;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES63);
            WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (REDEFINES63.WS_CAL_MM == 04 
            || REDEFINES63.WS_CAL_MM == 06 
            || REDEFINES63.WS_CAL_MM == 09 
            || REDEFINES63.WS_CAL_MM == 11) {
            REDEFINES63.WS_CAL_DD = 30;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES63);
            WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
        }
    }
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCICAL.RC.RC_APP = "SC";
            LNCICAL.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B412_CRE_RCVD_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '0';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = 'P';
        LNCRCVDM.REC_DATA.KEY.TERM = WS_P_TERM;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = 0;
        LNCRCVDM.REC_DATA.VAL_DT = WS_P_VAL_DT;
        LNCRCVDM.REC_DATA.DUE_DT = WS_P_DUE_DT;
        if (WS_PLPI_REC_STS == 'P') {
            LNCRCVDM.REC_DATA.TERM_STS = '1';
        } else {
            LNCRCVDM.REC_DATA.TERM_STS = '0';
        }
        if (WS_PLPI_REC_STS == 'P' 
            || WS_PLPI_REC_STS == 'R') {
            LNCRCVDM.REC_DATA.CLR_TYP = 1;
        } else {
            LNCRCVDM.REC_DATA.CLR_TYP = 0;
        }
        LNCRCVDM.REC_DATA.OVD_DT = WS_DUE_DT;
        LNCRCVDM.REC_DATA.REPY_STS = '0';
        LNCRCVDM.REC_DATA.P_REC_AMT = WS_P_REC_AMT;
        LNCRCVDM.REC_DATA.I_REC_AMT = 0;
        LNCRCVDM.REC_DATA.O_REC_AMT = 0;
        LNCRCVDM.REC_DATA.L_REC_AMT = 0;
        LNCRCVDM.REC_DATA.F_REC_AMT = 0;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            LNCRCVDM.REC_DATA.REC_FLG = 'N';
        } else {
            LNCRCVDM.REC_DATA.REC_FLG = 'Y';
        }
        if (LNCRCVDM.REC_DATA.P_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.I_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.O_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.L_REC_AMT == 0 
            && LNCRCVDM.REC_DATA.F_REC_AMT == 0) {
            LNCRCVDM.REC_DATA.REPY_STS = '2';
        }
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        LNCICAL.COMM_DATA.PRI_AMT += WS_P_REC_AMT;
        LNCICAL.COMM_DATA.P_BEG_DATE = WS_RCVD_VAL_DT;
        LNCICAL.COMM_DATA.P_END_DATE = WS_RCVD_DUE_DT;
        if (WS_P_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_P_OVD_AMT += WS_P_REC_AMT;
        }
    }
    public void B413_P_CNTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "INTCALP";
        LNCCNEV.COMM_DATA.ACM_EVENT = "CAL";
        LNCCNEV.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCICAL.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = WS_P_DUE_DT;
        if (WS_PLPI_REC_STS != 'P') {
            LNCCNEV.COMM_DATA.P_AMT = WS_P_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = WS_P_REC_AMT;
        } else {
            LNCCNEV.COMM_DATA.P_AMT = WS_P_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_P_REC_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT = WS_P_REC_AMT;
        }
        CEP.TRC(SCCGWA, WS_PLPI_REC_STS);
        CEP.TRC(SCCGWA, WS_P_REC_AMT);
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0138610")) {
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
        }
    }
    public void B420_GEN_NEXT_CALDATA() throws IOException,SQLException,Exception {
        WS_P_TERM += 1;
        if (WS_P_TERM < LNCICTLM.REC_DATA.P_CMP_TERM) {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '3';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
            LNCPLPIM.REC_DATA.KEY.TERM = WS_P_TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            WS_P_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_P_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        } else {
            WS_P_VAL_DT = WS_P_DUE_DT;
            WS_P_DUE_DT = LNCICTLM.REC_DATA.P_CMP_DUE_DT;
        }
    }
    public void B430_UPDATE_LNTICTL() throws IOException,SQLException,Exception {
        B200_GET_RCVD_TERM_P();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.REC_DATA.P_CAL_TERM = WS_P_TERM;
        if (WS_P_CUR_TERM > 0) {
            LNCICTLM.REC_DATA.P_CUR_TERM = WS_P_CUR_TERM;
        } else {
            LNCICTLM.REC_DATA.P_CUR_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        }
        LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_P_DUE_DT;
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void BS07_NEXT_DATA_PROC() throws IOException,SQLException,Exception {
        WS_IC_TERM += 1;
        if (WS_IC_TERM < WS_IC_CMP_TERM) {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '3';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_TYPE;
            LNCPLPIM.REC_DATA.KEY.TERM = WS_IC_TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            WS_IC_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_IC_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        } else {
            WS_IC_VAL_DT = WS_IC_DUE_DT;
            if (WS_REPY_TYPE == 'C') {
                CEP.TRC(SCCGWA, WS_IC_TERM);
                if (WS_IC_TERM == LNCRPAIP.TOT_TENORS) {
                    WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
                } else {
                    if (WS_PERD != 0 
                        && WS_PERD_UNIT != ' ') {
                        IBS.init(SCCGWA, SCCCLDT);
                        SCCCLDT.DATE1 = WS_IC_VAL_DT;
                        if (WS_PERD_UNIT == 'D') {
                            SCCCLDT.DAYS = WS_PERD;
                        } else {
                            SCCCLDT.MTHS = WS_PERD;
                        }
                        R00_CAL_DATE();
                        if (pgmRtn) return;
                        WS_IC_DUE_DT = SCCCLDT.DATE2;
                    } else {
                        WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
                    }
                }
            } else {
                if (LNCAPRDM.REC_DATA.CAL_PERD != 0 
                    && LNCAPRDM.REC_DATA.CAL_PERD_UNIT != ' ') {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = WS_IC_VAL_DT;
                    if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
                        SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
                    } else {
                        SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
                    }
                    R00_CAL_DATE();
                    if (pgmRtn) return;
                    WS_IC_DUE_DT = SCCCLDT.DATE2;
                } else {
                    WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
                }
            }
        }
        if (WS_IC_DUE_DT > LNCCONTM.REC_DATA.MAT_DATE) {
            WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
        }
    }
    public void BS06_NEXT_DATA_PROC() throws IOException,SQLException,Exception {
        WS_IC_VAL_DT = WS_IC_DUE_DT;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_IC_DUE_DT;
        if (WS_REPY_TYPE == 'C') {
            CEP.TRC(SCCGWA, WS_IC_TERM);
            if (WS_IC_TERM == LNCRPAIP.TOT_TENORS) {
                SCCCLDT.DATE2 = LNCCONTM.REC_DATA.MAT_DATE;
            } else {
                if (WS_PERD_UNIT == 'D') {
                    SCCCLDT.DAYS = WS_PERD;
                } else {
                    SCCCLDT.MTHS = WS_PERD;
                }
                if (WS_PERD != 0) {
                    R00_CAL_DATE();
                    if (pgmRtn) return;
                } else {
                    SCCCLDT.DATE2 = LNCCONTM.REC_DATA.MAT_DATE;
                }
            }
        } else {
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
            } else {
                SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            if (LNCAPRDM.REC_DATA.CAL_PERD != 0) {
                R00_CAL_DATE();
                if (pgmRtn) return;
            } else {
                SCCCLDT.DATE2 = LNCCONTM.REC_DATA.MAT_DATE;
            }
        }
        WS_IC_DUE_DT = SCCCLDT.DATE2;
        if (WS_IC_DUE_DT > LNCCONTM.REC_DATA.MAT_DATE) {
            WS_IC_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
        }
    }
    public void B000_GET_REPY_AMT() throws IOException,SQLException,Exception {
        if (WS_REPY_TYPE == 'C') {
            BS01_GET_PAIP_INF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_INCR_PERD);
            CEP.TRC(SCCGWA, WS_INCR_AMT);
            CEP.TRC(SCCGWA, WS_PHS_CMP_TERM);
            CEP.TRC(SCCGWA, WS_TERM_CNT);
        } else {
            WS_REPY_AMT = 0;
        }
    }
    public void B210_UPD_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.REC_DATA.IC_CMP_TERM = WS_IC_TERM;
        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_IC_VAL_DT;
        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_IC_DUE_DT;
        LNCICTLM.REC_DATA.IC_CMP_PHS_NO = WS_PHS_NO;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_IC_CAL_DUE_DT;
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B211_UPD_PAIP_CMP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '4';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPAIPM.REC_DATA.KEY.PHS_NO = WS_PHS_NO;
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        if (LNCPAIPM.REC_DATA.INST_MTH == '3') {
            LNCPAIPM.REC_DATA.CUR_INST_AMT = WS_REPY_AMT;
        }
        LNCPAIPM.REC_DATA.PHS_CMP_TERM = WS_PHS_CMP_TERM;
        LNCPAIPM.FUNC = '2';
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
    }
    public void B311_UPD_PAIP_CAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '4';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPAIPM.REC_DATA.KEY.PHS_NO = WS_PHS_NO;
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        LNCPAIPM.REC_DATA.PHS_CAL_TERM = WS_PHS_CAL_TERM;
        LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT = WS_REM_PRIN_AMT;
        LNCPAIPM.FUNC = '2';
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
    }
    public void BS01_GET_PAIP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '3';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCPAIPM.REC_DATA.KEY.PHS_NO = WS_PHS_NO;
        S001_CALL_LNZPAIPM();
        if (pgmRtn) return;
        WS_REPY_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
        WS_PHS_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_PRIN_AMT;
        WS_REM_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT;
        WS_PHS_CMP_TERM = LNCPAIPM.REC_DATA.PHS_CMP_TERM;
        WS_PHS_CAL_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
        LNCAPRDM.REC_DATA.INST_MTH = LNCPAIPM.REC_DATA.INST_MTH;
        WS_PERD = LNCPAIPM.REC_DATA.PERD;
        WS_PERD_UNIT = LNCPAIPM.REC_DATA.PERD_UNIT;
    }
    public void B320_UPD_ICTL_LAST_TERM_ERR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (WS_P_DUE_DT_ERR > LNCICTLM.REC_DATA.P_CAL_DUE_DT) {
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_P_DUE_DT_ERR;
        }
        if (WS_IC_DUE_DT_ERR > LNCICTLM.REC_DATA.IC_CAL_DUE_DT) {
            LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_IC_DUE_DT_ERR;
        }
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B310_UPDATE_LNTICTL() throws IOException,SQLException,Exception {
        B200_GET_RCVD_TERM_I();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.REC_DATA.IC_CAL_TERM = WS_IC_TERM;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
            LNCICTLM.REC_DATA.IC_CUR_TERM = 2;
        } else {
            if (WS_IC_CUR_TERM > 0) {
                LNCICTLM.REC_DATA.IC_CUR_TERM = WS_IC_CUR_TERM;
            } else {
                LNCICTLM.REC_DATA.IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            }
        }
        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = WS_IC_VAL_DT;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_IC_DUE_DT;
        LNCICTLM.REC_DATA.IC_CAL_PHS_NO = WS_PHS_NO;
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            LNCICTLM.REC_DATA.INT_CUT_DT = WS_IC_VAL_DT;
        }
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B320_JUDGE_SUBS() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            WS_FOUND_FLG = 'N';
            WS_SUBS_FLG = 'N';
            IBS.init(SCCGWA, LNRRELA);
            LNRRELA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            LNRRELA.KEY.TYPE = 'S';
            T000_READ_LNTRELA();
            if (pgmRtn) return;
            if (WS_FOUND_FLG == 'Y') {
                WS_ST_TERM = LNRRELA.ST_TERM;
                WS_ED_TERM = (short) (LNRRELA.ST_TERM + LNRRELA.TERM - 1);
                CEP.TRC(SCCGWA, "444");
                CEP.TRC(SCCGWA, WS_ST_TERM);
                if (LNCRCVDM.REC_DATA.KEY.TERM >= WS_ST_TERM 
                    && LNCRCVDM.REC_DATA.KEY.TERM <= WS_ED_TERM) {
                    CEP.TRC(SCCGWA, "555");
                    WS_SUBS_FLG = 'Y';
                }
            }
        }
    }
    public void B321_GET_SUBS_ST_ED_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
        T000_READ_LNTSUBS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111");
        if (WS_TABLE_FLG == 'Y') {
            CEP.TRC(SCCGWA, "222");
            WS_ST_TERM = LNRSUBS.ST_TERM;
            WS_ED_TERM = (short) (LNRSUBS.ST_TERM + LNRSUBS.TERM - 1);
        }
        CEP.TRC(SCCGWA, "333");
    }
    public void B322_LOAN_SUBS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.TERM);
        CEP.TRC(SCCGWA, WS_REPY_TYPE);
        IBS.init(SCCGWA, LNCSUBP);
        LNCSUBP.COMM_DATA.FUNC_CODE = 'U';
        LNCSUBP.COMM_DATA.LN_AC = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCSUBP.COMM_DATA.SUF_NO = 0;
        else LNCSUBP.COMM_DATA.SUF_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNCSUBP.COMM_DATA.TERM = LNCRCVDM.REC_DATA.KEY.TERM;
        LNCSUBP.COMM_DATA.AMT_TYP = WS_REPY_TYPE;
        LNCSUBP.COMM_DATA.RAT = LNCICTLM.REC_DATA.CUR_RAT;
        S000_CALL_LNZSUBP();
        if (pgmRtn) return;
    }
    public void B200_GET_RCVD_TERM_P() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNRRCVD.KEY.AMT_TYP = 'P';
        LNRRCVD.REPY_STS = '2';
        T000_READ_LNTRCVD();
        if (pgmRtn) return;
        WS_P_CUR_TERM = LNRRCVD.KEY.TERM;
    }
    public void B200_GET_RCVD_TERM_I() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
            if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
            else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
            LNRRCVD.KEY.AMT_TYP = 'C';
            LNRRCVD.REPY_STS = '2';
            T000_READ_LNTRCVD();
            if (pgmRtn) return;
            WS_IC_CUR_TERM = LNRRCVD.KEY.TERM;
        } else {
            if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
                IBS.init(SCCGWA, LNRRCVD);
                LNRRCVD.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
                if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
                else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
                LNRRCVD.KEY.AMT_TYP = 'I';
                LNRRCVD.REPY_STS = '2';
                T000_READ_LNTRCVD();
                if (pgmRtn) return;
                WS_IC_CUR_TERM = LNRRCVD.KEY.TERM;
            }
        }
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRCVD_RD.where = "AMT_TYP = :LNRRCVD.KEY.AMT_TYP "
            + "AND REPY_STS < > :LNRRCVD.REPY_STS";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
    }
    public void R000_GROUP_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'G';
        LNRPAIP.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        LNRPAIP.KEY.PHS_NO = 999;
        LNCRPAIP.REC_LEN = 200;
        LNCRPAIP.REC_PTR = LNRPAIP;
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRPAIP.TOT_TENORS);
    }
    public void S000_CALL_LNZRDISC() throws IOException,SQLException,Exception {
        LNCRDISC.REC_PTR = LNRDISC;
        LNCRDISC.REC_LEN = 258;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTDISC", LNCRDISC);
        CEP.TRC(SCCGWA, LNCRDISC.RC.RC_CODE);
        if (LNCRDISC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRDISC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCICAL.RC.RC_APP = "SC";
            LNCICAL.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            LNCICAL.RC.RC_APP = BPCOCLWD.RC.RC_MMO;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            LNCICAL.RC.RC_RTNCODE = Short.parseShort(JIBS_tmp_str[0]);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZENSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-ENTRY-SCH", LNCENSCH);
        if (LNCENSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCENSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SVR_LNZCMSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONFIRM-SCHE", LNCCMSCH);
        if (LNCCMSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCMSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSIQIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-INF", LNCSIQIF);
        if (LNCSIQIF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, LNCSIQIF.RC);
        }
    }
    public void S000_CALL_DCZUDKLN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-REG-DCTPFDE", DCCUDKLN);
        if (DCCUDKLN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUDKLN.RC);
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCVDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        if (LNCRCVDM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCVDM_1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        if (LNCRCVDM.RC.RC_RTNCODE != 0 
            && LNCRCVDM.RC.RC_RTNCODE != 1516) {
            LNCICAL.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLPIM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND)) {
                WS_PLPI_FOUND_FLG = 'N';
            } else {
                LNCICAL.RC.RC_APP = LNCPLPIM.RC.RC_APP;
                LNCICAL.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCPAIPM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCPAIPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S001_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND)) {
            WS_NO_PLPI_ID = 'Y';
        } else {
            if (LNCPAIPM.RC.RC_RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCICUT.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCICUT.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCICNQ.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCICNQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSUBP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-SUBS-DUE-PROC", LNCSUBP);
        if (LNCSUBP.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCSUBP.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCSUBP.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCAPRDM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCAPRDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-R-PAIPL-MAIN", LNCRPAIP);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO "
            + "AND TERM = :LNRPYIF.KEY.TERM";
        LNTPYIF_RD.fst = true;
        IBS.READ(SCCGWA, LNRPYIF, this, LNTPYIF_RD);
    }
    public void T000_READ_LNTPLPI_NOT_I() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'I';
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_RD.where = "REPY_MTH < > :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "PLPI FOUND");
            WS_READ_LNTPLPI_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "PLPI NOTFND");
            WS_READ_LNTPLPI_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTRELA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        LNTRELA_BR.rp = new DBParm();
        LNTRELA_BR.rp.TableName = "LNTRELA";
        LNTRELA_BR.rp.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO";
        LNTRELA_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRRELA, this, LNTRELA_BR);
    }
    public void T000_READNEXT_LNTRELA() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRELA, this, LNTRELA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTRELA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRELA_BR);
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_READ_LNTRELA() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        IBS.READ(SCCGWA, LNRRELA, LNTRELA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
        }
    }
    public void R000_READUPD_LNTBALZ_PART() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        LNRBALZ.KEY.SUB_CTA_NO = WS_PART_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        WS_BALZ_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BALZ_FOUND_FLG = 'Y';
        } else {
        }
    }
    public void R000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R000_WRITE_LNTBALZ_PART() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRBALZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.WRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R000_REWRITE_LNTBALZ_PART() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT3 = new SCSSCLDT();
        SCSSCLDT3.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCICAL.RC.RC_APP = "SC";
            LNCICAL.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, LNCICAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
