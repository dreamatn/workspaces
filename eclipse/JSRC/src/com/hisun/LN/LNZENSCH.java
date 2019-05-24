package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZENSCH {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    LNZENSCH_WS_LIST WS_LIST;
    BigDecimal bigD;
    DBParm LNTSCHT_RD;
    String JIBS_NumStr;
    String JIBS_f0;
    brParm LNTPLPI_BR = new brParm();
    brParm LNTSCHT_BR = new brParm();
    DBParm LNTAPRD_RD;
    DBParm LNTPLPI_RD;
    boolean pgmRtn = false;
    char RATE_TYP_NORMAL = 'N';
    char PRINCIPAL = 'P';
    char INTEREST = 'I';
    char ADD = 'A';
    char MONTH = 'M';
    short MAX_TERM = 360;
    short MAX_CNT = 99;
    String SCSSCLDT = "SCSSCLDT";
    short PAGE_ROW = 20;
    LNZENSCH_WS_VARIABLES WS_VARIABLES = new LNZENSCH_WS_VARIABLES();
    LNZENSCH_WS_OUT_RECODE WS_OUT_RECODE = new LNZENSCH_WS_OUT_RECODE();
    LNZENSCH_WS_TEMP_LIST WS_TEMP_LIST = new LNZENSCH_WS_TEMP_LIST();
    LNZENSCH_WS_TEMP_VARS WS_TEMP_VARS = new LNZENSCH_WS_TEMP_VARS();
    LNZENSCH_WS_COND_FLG WS_COND_FLG = new LNZENSCH_WS_COND_FLG();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNCRATQ LNCRATQ = new LNCRATQ();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCSPRIN LNCSPRIN = new LNCSPRIN();
    LNCQRSCH LNCQRSCH = new LNCQRSCH();
    LNCIHDCK LNCIHDCK = new LNCIHDCK();
    LNCIPART LNCIPART = new LNCIPART();
    LNCIPARA LNCIPARA = new LNCIPARA();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    LNCSCALT LNCSCALT = new LNCSCALT();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCCLNQ_WS_DB_VARS WS_DB_VARS = new LNCCLNQ_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 AWA_5210;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    LNCENSCH LNCENSCH;
    public void MP(SCCGWA SCCGWA, LNCENSCH LNCENSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCENSCH = LNCENSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZENSCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        WS_TEMP_LIST.CNT = 0;
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_TEMP_VARS);
        WS_VARIABLES.SUB_CTA_NO = 0;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B000_NEST_RECORD();
        if (pgmRtn) return;
        B000_CONVERT_PERD_UNIT();
        if (pgmRtn) return;
        if (LNCENSCH.COMM_DATA.CTA_NO.compareTo(SPACE) > 0) {
            B130_GET_ORG_DATA();
            if (pgmRtn) return;
        }
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B150_LOGIC_CHK_PROC();
        if (pgmRtn) return;
        B200_MAIN_PROCESS_SCHE();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0133020")) {
            B300_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B000_NEST_RECORD() throws IOException,SQLException,Exception {
        B211_GET_CONTRACT_REC();
        if (pgmRtn) return;
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_REPAY_LGU_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNCCLNQ.FUNC = '1';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        LNCENSCH.COMM_DATA.PRIN_AMT = LNCCLNQ.DATA.PRIN;
        LNCENSCH.COMM_DATA.OS_BAL = LNCCLNQ.DATA.TOT_BAL;
        LNCENSCH.COMM_DATA.CI_S_NAM = LNCCLNQ.DATA.CI_SNAME;
        LNCENSCH.COMM_DATA.PRD_TYP = LNCCLNQ.DATA.PROD_CD;
        LNCENSCH.COMM_DATA.CCY = LNCCLNQ.DATA.CCY;
    }
    public void B000_CONVERT_PERD_UNIT() throws IOException,SQLException,Exception {
        if (LNCENSCH.COMM_DATA.PFRE_TERM > 0 
            && LNCENSCH.COMM_DATA.PFRE_TYP != ' ') {
            if (LNCENSCH.COMM_DATA.PFRE_TYP == 'D'
                || LNCENSCH.COMM_DATA.PFRE_TYP == 'M') {
            } else if (LNCENSCH.COMM_DATA.PFRE_TYP == 'W') {
                LNCENSCH.COMM_DATA.PFRE_TERM = (short) (LNCENSCH.COMM_DATA.PFRE_TERM * 7);
                LNCENSCH.COMM_DATA.PFRE_TYP = 'D';
            } else if (LNCENSCH.COMM_DATA.PFRE_TYP == 'Y') {
                LNCENSCH.COMM_DATA.PFRE_TERM = (short) (LNCENSCH.COMM_DATA.PFRE_TERM * 12);
                LNCENSCH.COMM_DATA.PFRE_TYP = 'M';
            } else {
                LNCENSCH.COMM_DATA.PFRE_TERM = 0;
                LNCENSCH.COMM_DATA.PFRE_TYP = ' ';
            }
        }
        if (LNCENSCH.COMM_DATA.IFRE_TERM > 0 
            && LNCENSCH.COMM_DATA.IFRE_TYP != ' ') {
            if (LNCENSCH.COMM_DATA.IFRE_TYP == 'D'
                || LNCENSCH.COMM_DATA.IFRE_TYP == 'M') {
            } else if (LNCENSCH.COMM_DATA.IFRE_TYP == 'W') {
                LNCENSCH.COMM_DATA.IFRE_TERM = (short) (LNCENSCH.COMM_DATA.IFRE_TERM * 7);
                LNCENSCH.COMM_DATA.IFRE_TYP = 'D';
            } else if (LNCENSCH.COMM_DATA.IFRE_TYP == 'Y') {
                LNCENSCH.COMM_DATA.IFRE_TERM = (short) (LNCENSCH.COMM_DATA.IFRE_TERM * 12);
                LNCENSCH.COMM_DATA.IFRE_TYP = 'M';
            } else {
                LNCENSCH.COMM_DATA.IFRE_TERM = 0;
                LNCENSCH.COMM_DATA.IFRE_TYP = ' ';
            }
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((LNCENSCH.COMM_DATA.IFNP_DAT <= 0) 
            && (LNCENSCH.COMM_DATA.PFNP_DAT <= 0) 
            && (LNCENSCH.COMM_DATA.REPY_TYP <= SPACE)) {
            WS_COND_FLG.RUN_STEP_FLAG = '1';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == 'P') 
            && (LNCENSCH.COMM_DATA.PFNP_DAT <= 0) 
            && (LNCENSCH.COMM_DATA.IFNP_DAT <= 0)) {
            WS_COND_FLG.RUN_STEP_FLAG = '2';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == 'P') 
            && (LNCENSCH.COMM_DATA.PFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.REPY_AMT <= 0)) {
            WS_COND_FLG.RUN_STEP_FLAG = '3';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == 'P') 
            && (LNCENSCH.COMM_DATA.PFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.REPY_AMT > 0)) {
            WS_COND_FLG.RUN_STEP_FLAG = '4';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == 'I') 
            && (LNCENSCH.COMM_DATA.IFNP_DAT <= 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TERM <= 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TYP <= SPACE)) {
            WS_COND_FLG.RUN_STEP_FLAG = '5';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == 'I') 
            && (LNCENSCH.COMM_DATA.IFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TYP > SPACE)) {
            WS_COND_FLG.RUN_STEP_FLAG = '6';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.IFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.PFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.PFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.REPY_AMT <= 0)) {
            WS_COND_FLG.RUN_STEP_FLAG = '7';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.IFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.PFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.PFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.REPY_AMT > 0)) {
            WS_COND_FLG.RUN_STEP_FLAG = '8';
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == 'P') 
            && (LNCENSCH.COMM_DATA.PFNP_DAT == 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TERM == 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.REPY_AMT > 0)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5701;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.IFNP_DAT > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.PFRE_TERM == 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.PFNP_DAT == 0) 
            && (LNCENSCH.COMM_DATA.REPY_AMT == 0)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5701;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCENSCH.COMM_DATA.REPY_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.IFNP_DAT == 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TERM == 0) 
            && (LNCENSCH.COMM_DATA.IFRE_TYP == ' ') 
            && (LNCENSCH.COMM_DATA.PFRE_TERM > 0) 
            && (LNCENSCH.COMM_DATA.PFRE_TYP > SPACE) 
            && (LNCENSCH.COMM_DATA.PFNP_DAT > 0)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5701;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.RUN_STEP_FLAG == ' ') {
            WS_COND_FLG.RUN_STEP_FLAG = 'X';
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B130_GET_ORG_DATA() throws IOException,SQLException,Exception {
        B100_GET_APRD_INF();
        if (pgmRtn) return;
        if (WS_VARIABLES.TRANCHE_NO.trim().length() > 0) {
        } else {
            WS_VARIABLES.CNTY_CD_INF = " ";
            WS_VARIABLES.CMMT_DUE_DATE = LNRCONT.MAT_DATE;
        }
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 16580;
        S000_CALL_SRC_LNZRICTL();
        if (pgmRtn) return;
        if (LNCRICTL.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5702;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRLOAN);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 13500;
        S000_CALL_SRC_LNZRLOAN();
        if (pgmRtn) return;
    }
    public void B150_LOGIC_CHK_PROC() throws IOException,SQLException,Exception {
        if (LNRAPRD.PAY_MTH == '4') {
            CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_REPAY_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCENSCH.COMM_DATA.CTA_NO.compareTo(SPACE) > 0) {
            WS_VARIABLES.P_CMP_TERM = 0;
            if (LNRICTL.P_CMP_TERM > 0) {
                WS_VARIABLES.P_CMP_TERM = LNRICTL.P_CMP_TERM;
            }
            if (LNRICTL.IC_CMP_TERM > 0) {
                WS_VARIABLES.I_CMP_TERM = LNRICTL.IC_CMP_TERM;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1));
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_HAS_DELETION;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(73 - 1, 73 + 1 - 1).equalsIgnoreCase("1")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_AC_MGM_FEE_Y;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_VARIABLES.LOAN_DUE_DATE = LNRCONT.MAT_DATE;
        if (LNRAPRD.PAY_MTH == '0' 
            || LNRAPRD.PAY_MTH == '1' 
            || LNRAPRD.PAY_MTH == '2') {
            if ((LNCENSCH.COMM_DATA.PFNP_DAT > 0) 
                && (LNCENSCH.COMM_DATA.PFNP_DAT != LNRCONT.MAT_DATE)) {
                CEP.TRC(SCCGWA, "AAAAA");
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5706;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (LNCENSCH.COMM_DATA.PFNP_DAT > LNRCONT.MAT_DATE) {
                CEP.TRC(SCCGWA, "CCCCC");
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5706;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNRAPRD.PAY_MTH == '0' 
            || LNRAPRD.PAY_MTH == '1') {
            if ((LNCENSCH.COMM_DATA.IFNP_DAT > 0) 
                && (LNCENSCH.COMM_DATA.IFNP_DAT != LNRCONT.MAT_DATE)) {
                CEP.TRC(SCCGWA, "BBBBB");
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5706;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (LNCENSCH.COMM_DATA.IFNP_DAT > LNRCONT.MAT_DATE) {
                CEP.TRC(SCCGWA, "DDDDD");
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5706;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNRICTL.IC_CMP_TERM < 2) {
            WS_VARIABLES.I_START_DATE = LNRCONT.START_DATE;
        }
        if (LNRICTL.P_CMP_TERM < 2) {
            WS_VARIABLES.P_START_DATE = LNRCONT.START_DATE;
        }
        if (LNRAPRD.PAY_MTH == '1' 
            || LNRAPRD.PAY_MTH == '2') {
            if (LNRCONT.MAT_DATE <= 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5703;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNRAPRD.PAY_MTH == '6') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5702;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (((LNCENSCH.COMM_DATA.REPY_TYP != ' ') 
            && (LNRAPRD.PAY_MTH == '1'))) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5704;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.RUN_STEP_FLAG == '6' 
            && (LNCENSCH.COMM_DATA.REPY_TYP <= SPACE) 
            && (LNCENSCH.COMM_DATA.IFNP_DAT <= LNRICTL.IC_CAL_VAL_DT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5707;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (((LNCENSCH.COMM_DATA.PFNP_DAT > 0 
            && LNCENSCH.COMM_DATA.PFNP_DAT < SCCGWA.COMM_AREA.AC_DATE) 
            || (LNCENSCH.COMM_DATA.IFNP_DAT > 0 
            && LNCENSCH.COMM_DATA.IFNP_DAT < SCCGWA.COMM_AREA.AC_DATE)) 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5731;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_APRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        T000_READ_APRD_PROC();
        if (pgmRtn) return;
        WS_TEMP_VARS.CAL_PAY_DAY = LNRAPRD.PAY_DAY;
        WS_TEMP_VARS.CAL_FST_FLG = LNRAPRD.FST_PAY_FLG;
    }
    public void B200_MAIN_PROCESS_SCHE() throws IOException,SQLException,Exception {
        if (LNCENSCH.COMM_DATA.SEQ != 0) {
            WS_VARIABLES.SEQ_NO = LNCENSCH.COMM_DATA.SEQ;
        } else {
            WS_VARIABLES.SEQ_NO = SCCGWA.COMM_AREA.JRN_NO;
        }
        B210_GET_EXIST_SCHE();
        if (pgmRtn) return;
        B200_10_BUILD_NEW_SCHE();
        if (pgmRtn) return;
    }
    public void B200_10_BUILD_NEW_SCHE() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.RUN_STEP_FLAG == '1' 
            || WS_COND_FLG.RUN_STEP_FLAG == '2') {
            R000_RECHOOSE_STEP_NO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.RUN_STEP_FLAG);
        if (WS_COND_FLG.RUN_STEP_FLAG == '3') {
            B220_PRE_CHECK_DATA_P();
            if (pgmRtn) return;
            B230_BUILD_PSCHE_WITHOUT_AMT();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == '4') {
            B220_PRE_CHECK_DATA_P();
            if (pgmRtn) return;
            B240_BUILD_PSCHE_FIX_AMT();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == '6') {
            B220_PRE_CHECK_DATA_I();
            if (pgmRtn) return;
            B260_BUILD_INT_SCHE();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == '7') {
            B220_PRE_CHECK_DATA_P();
            if (pgmRtn) return;
            B230_BUILD_PSCHE_WITHOUT_AMT();
            if (pgmRtn) return;
            B220_PRE_CHECK_DATA_I();
            if (pgmRtn) return;
            B260_BUILD_INT_SCHE();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == '8') {
            B220_PRE_CHECK_DATA_P();
            if (pgmRtn) return;
            B240_BUILD_PSCHE_FIX_AMT();
            if (pgmRtn) return;
            B220_PRE_CHECK_DATA_I();
            if (pgmRtn) return;
            B260_BUILD_INT_SCHE();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == 'B') {
            B220_PRE_CHECK_DATA_I();
            if (pgmRtn) return;
            B260_BUILD_INT_SCHE();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == 'X') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (WS_COND_FLG.RUN_STEP_FLAG == '1'
            || WS_COND_FLG.RUN_STEP_FLAG == '2'
            || WS_COND_FLG.RUN_STEP_FLAG == '5'
            || WS_COND_FLG.RUN_STEP_FLAG == '9'
            || WS_COND_FLG.RUN_STEP_FLAG == 'A') {
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_INPUT_ERROR;
            WS_VARIABLES.ERR_INFO = "INCORRECT SETUP STEP";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_RECHOOSE_STEP_NO() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TRANCHE_NO.trim().length() > 0) {
            if (LNRICTL.P_CMP_TERM == 1) {
                WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_TERM_VAR = LNRICTL.P_CMP_TERM;
                WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_VAL_DT = LNRCONT.START_DATE;
                WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_TOT_AMT = 0;
                if (WS_COND_FLG.RUN_STEP_FLAG == '1' 
                    || WS_COND_FLG.RUN_STEP_FLAG == '2') {
                    WS_COND_FLG.RUN_STEP_FLAG = 'A';
                }
                if (WS_COND_FLG.RUN_STEP_FLAG == '9') {
                    WS_COND_FLG.RUN_STEP_FLAG = 'B';
                }
            }
        }
    }
    public void B210_GET_EXIST_SCHE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCQRSCH);
        LNCQRSCH.COMM_DATA.SEQ_NO = WS_VARIABLES.SEQ_NO;
        LNCQRSCH.COMM_DATA.CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNCQRSCH.COMM_DATA.SUF_NO = "" + WS_VARIABLES.SUB_CTA_NO;
        JIBS_tmp_int = LNCQRSCH.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCQRSCH.COMM_DATA.SUF_NO = "0" + LNCQRSCH.COMM_DATA.SUF_NO;
        LNCQRSCH.COMM_DATA.REPY_TYP = LNCENSCH.COMM_DATA.REPY_TYP;
        LNCQRSCH.COMM_DATA.BANK_NAM = " ";
        LNCQRSCH.COMM_DATA.BK_CNTY = " ";
        S000_CALL_FUN_LNZQRSCH();
        if (pgmRtn) return;
    }
    public void B211_GET_CONTRACT_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 13699;
        S000_CALL_SRC_LNZRCONT();
        if (pgmRtn) return;
        WS_VARIABLES.TRANCHE_NO = LNRCONT.FATHER_CONTRACT;
        WS_TEMP_VARS.START_DATE = LNRCONT.START_DATE;
        WS_TEMP_VARS.MAT_DATE = LNRCONT.MAT_DATE;
    }
    public void B212_GET_CNTY_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCMMT);
        IBS.init(SCCGWA, LNRCMMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.TRANCHE_NO);
        LNCRCMMT.FUNC = 'I';
        LNRCMMT.KEY.CONTRACT_NO = WS_VARIABLES.TRANCHE_NO;
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 10210;
        S000_CALL_SRC_LNZRCMMT();
        if (pgmRtn) return;
        WS_VARIABLES.CNTY_CD_INF = LNRCMMT.REF_CTY_CODE_INF;
        IBS.CPY2CLS(SCCGWA, WS_VARIABLES.CNTY_CD_INF, WS_VARIABLES.REDEFINES28);
    }
    public void B216_GET_ALL_IN_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATQ);
        LNCRATQ.COMM_DATA.LN_AC = LNCENSCH.COMM_DATA.CTA_NO;
        LNCRATQ.COMM_DATA.SUF_NO = "" + LNCENSCH.COMM_DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCRATQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCRATQ.COMM_DATA.SUF_NO = "0" + LNCRATQ.COMM_DATA.SUF_NO;
        LNCRATQ.COMM_DATA.RATE_TYPE = RATE_TYP_NORMAL;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_VARIABLES.RATQ_VAL_DT;
        SCCCLDT.DAYS = -1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        LNCRATQ.COMM_DATA.VAL_DATE = SCCCLDT.DATE2;
        S000_CALL_FUNC_LNZRATQ();
        if (pgmRtn) return;
    }
    public void B217_WRITE_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'A';
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNRSCHT.KEY.SUB_CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TYPE = WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE;
        LNRSCHT.KEY.TERM = WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM;
        LNRSCHT.ACTION = WS_VARIABLES.WS_TEMP_VAR.TEMP_ACT;
        LNRSCHT.VAL_DTE = WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT;
        LNRSCHT.DUE_DTE = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
        LNRSCHT.ALL_IN_RATE = WS_VARIABLES.WS_TEMP_VAR.TEMP_RATE;
        LNRSCHT.AMOUNT = WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT;
        LNRSCHT.REMARK = WS_VARIABLES.WS_TEMP_VAR.TEMP_RMK;
        LNRSCHT.ACTION = ADD;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 20819;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        if (WS_VARIABLES.TRANCHE_NO.trim().length() > 0) {
            B217_99_WRITE_PART_LNTSCHT();
            if (pgmRtn) return;
        }
    }
    public void B218_WRITE_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'A';
        LNRPLPI.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE;
        LNRPLPI.KEY.TERM = WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM;
        LNRPLPI.VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT;
        LNRPLPI.DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
        LNRPLPI.DUE_REPY_AMT = WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT;
        LNRPLPI.PRIN_AMT = WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT;
        LNRPLPI.REMARK = WS_VARIABLES.WS_TEMP_VAR.TEMP_RMK;
        LNRPLPI.REC_STS = '0';
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 18227;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B219_GET_MAX_TERM_PLPI() throws IOException,SQLException,Exception {
        WS_VARIABLES.P_MAX_TERM = 0;
        WS_VARIABLES.I_MAX_TERM = 0;
        WS_VARIABLES.I_CMP_TERM = 0;
        WS_VARIABLES.P_CMP_TERM = 0;
        WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE = INTEREST;
        R000_GET_MAX_TERM_NO();
        if (pgmRtn) return;
        WS_VARIABLES.I_MAX_TERM = LNCRPLPI.TERM_MAX;
        WS_VARIABLES.I_CMP_VAL_DT = LNCRPLPI.MAX_DUE_DT;
        WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE = PRINCIPAL;
        R000_GET_MAX_TERM_NO();
        if (pgmRtn) return;
        WS_VARIABLES.P_MAX_TERM = LNCRPLPI.TERM_MAX;
        WS_VARIABLES.P_CMP_VAL_DT = LNCRPLPI.MAX_DUE_DT;
        WS_VARIABLES.I_CMP_TERM = (short) (WS_VARIABLES.I_MAX_TERM + 1);
        WS_VARIABLES.P_CMP_TERM = (short) (WS_VARIABLES.P_MAX_TERM + 1);
    }
    public void B220_READUP_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = WS_VARIABLES.SUB_CTA_NO;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 16580;
        S000_CALL_SRC_LNZRICTL();
        if (pgmRtn) return;
    }
    public void B221_REWRITE_LNTICTL() throws IOException,SQLException,Exception {
        LNCRICTL.FUNC = 'U';
        if (WS_VARIABLES.I_CMP_TERM > 0) {
            LNRICTL.IC_CMP_TERM = WS_VARIABLES.I_CMP_TERM;
        }
        if (WS_VARIABLES.P_CMP_TERM > 0) {
            LNRICTL.P_CMP_TERM = WS_VARIABLES.P_CMP_TERM;
        }
        if (WS_VARIABLES.I_CMP_VAL_DT > 0) {
            LNRICTL.IC_CMP_VAL_DT = WS_VARIABLES.I_CMP_VAL_DT;
            LNRICTL.IC_CMP_DUE_DT = WS_VARIABLES.I_CMP_VAL_DT;
            if (LNRAPRD.CAL_PERD != 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_VARIABLES.I_CMP_VAL_DT;
                if (LNRAPRD.CAL_PERD_UNIT == 'D') {
                    SCCCLDT.DAYS = LNRAPRD.CAL_PERD;
                } else {
                    SCCCLDT.MTHS = LNRAPRD.CAL_PERD;
                }
                R00_CAL_DATE();
                if (pgmRtn) return;
                if (SCCCLDT.DATE2 < LNRCONT.MAT_DATE) {
                    LNRICTL.IC_CMP_DUE_DT = SCCCLDT.DATE2;
                } else {
                    LNRICTL.IC_CMP_DUE_DT = LNRCONT.MAT_DATE;
                }
            }
        }
        if (WS_VARIABLES.P_CMP_VAL_DT > 0 
            && LNRAPRD.PAYP_PERD != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_VARIABLES.P_CMP_VAL_DT;
            if (LNRAPRD.PAYP_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNRAPRD.PAYP_PERD;
            } else {
                SCCCLDT.MTHS = LNRAPRD.PAYP_PERD;
            }
            R00_CAL_DATE();
            if (pgmRtn) return;
            if (SCCCLDT.DATE2 < LNRCONT.MAT_DATE) {
                LNRICTL.P_CMP_DUE_DT = SCCCLDT.DATE2;
            } else {
                LNRICTL.P_CMP_DUE_DT = LNRCONT.MAT_DATE;
            }
        }
        if (LNRICTL.IC_CAL_TERM == WS_VARIABLES.I_CAL_TERM) {
            LNRICTL.IC_CAL_VAL_DT = WS_VARIABLES.I_CAL_VAL_DT;
            LNRICTL.IC_CAL_DUE_DT = WS_VARIABLES.I_CAL_DUE_DT;
            if (LNRICTL.IC_CAL_DUE_DT > LNRCONT.MAT_DATE) {
                LNRICTL.IC_CAL_DUE_DT = LNRCONT.MAT_DATE;
            }
        }
        if (LNRICTL.P_CAL_TERM == WS_VARIABLES.P_CAL_TERM) {
            LNRICTL.P_CAL_DUE_DT = WS_VARIABLES.P_CAL_DUE_DT;
            if (LNRICTL.P_CAL_DUE_DT > LNRCONT.MAT_DATE) {
                LNRICTL.P_CAL_DUE_DT = LNRCONT.MAT_DATE;
            }
        }
        if (WS_VARIABLES.I_CMP_TERM == 1) {
            LNRICTL.IC_CAL_VAL_DT = LNRCONT.START_DATE;
            LNRICTL.IC_CMP_VAL_DT = LNRCONT.START_DATE;
            LNRICTL.IC_CMP_DUE_DT = LNRLOAN.FST_CAL_DT;
            LNRICTL.IC_CAL_DUE_DT = 0;
            LNRICTL.IC_CAL_TERM = 1;
        }
        if (WS_VARIABLES.P_CMP_TERM == 1) {
            LNRICTL.P_CAL_DUE_DT = 0;
            LNRICTL.P_CAL_TERM = 1;
        }
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 16580;
        S000_CALL_SRC_LNZRICTL();
        if (pgmRtn) return;
    }
    public void R000_GET_MAX_TERM_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'G';
        LNRPLPI.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 18227;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_VARIABLES.ERR_MSG = "" + SCCCLDT.RC;
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG = "0" + WS_VARIABLES.ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B217_99_WRITE_PART_LNTSCHT() throws IOException,SQLException,Exception {
        if (LNCIPART.DATA.CNT > 0) {
            LNCIPARA.DATA.TOT_AMT = LNRSCHT.AMOUNT;
            CEP.TRC(SCCGWA, LNRSCHT.AMOUNT);
            R000_DISB_PART_AMT();
            if (pgmRtn) return;
            for (WS_VARIABLES.II = 1; WS_VARIABLES.II <= LNCIPARA.DATA.CNT 
                && LNCIPARA.GROUP.get(WS_VARIABLES.II-1).SEQ_NO != 0 
                && WS_VARIABLES.II <= MAX_CNT; WS_VARIABLES.II += 1) {
                LNRSCHT.KEY.CONTRACT_NO = LNCIPARA.GROUP.get(WS_VARIABLES.II-1).SEQ_NO;
                LNRSCHT.AMOUNT = LNCIPARA.GROUP.get(WS_VARIABLES.II-1).DISB_AMT;
                LNCRSCHT.FUNC = 'A';
                LNCRSCHT.REC_PTR = LNRSCHT;
                LNCRSCHT.REC_LEN = 20819;
                S000_CALL_SRC_LNZRSCHT();
                if (pgmRtn) return;
            }
        }
        WS_VARIABLES.SUB_CTA_NO = 0;
    }
    public void B220_PRE_CHECK_DATA_P() throws IOException,SQLException,Exception {
        if (LNCENSCH.COMM_DATA.REPY_TYP <= SPACE) {
            LNCENSCH.COMM_DATA.REPY_TYP = PRINCIPAL;
        } else {
            if (LNCENSCH.COMM_DATA.REPY_TYP == INTEREST) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B220_01_GET_LAST_DUE_DATE_P();
        if (pgmRtn) return;
        if (WS_COND_FLG.EXIST_SCHE_FLAG == 'Y') {
            WS_VARIABLES.INPUT_TERM = (short) (WS_VARIABLES.P_CMP_TERM - 1);
            WS_VARIABLES.P_START_DATE = LNRPLPI.DUE_DT;
            B220_02_GET_TOT_IPLN_AMT();
            if (pgmRtn) return;
            if (WS_VARIABLES.P_TOT_IPLN_AMT > LNRCONT.LN_TOT_AMT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5709;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.OS_IPLN_AMT = LNRCONT.LN_TOT_AMT - WS_VARIABLES.P_TOT_IPLN_AMT;
            }
        } else {
            WS_VARIABLES.P_START_DATE = LNRCONT.START_DATE;
            WS_VARIABLES.OS_IPLN_AMT = LNRCONT.LN_TOT_AMT;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.OS_IPLN_AMT);
        if (LNCENSCH.COMM_DATA.OS_BAL < WS_VARIABLES.OS_IPLN_AMT) {
            WS_VARIABLES.OS_IPLN_AMT = LNCENSCH.COMM_DATA.OS_BAL;
        }
        if (LNCENSCH.COMM_DATA.PFNP_DAT <= WS_VARIABLES.P_START_DATE) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5724;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCENSCH.COMM_DATA.REPY_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.OS_IPLN_AMT);
        if (LNCENSCH.COMM_DATA.REPY_AMT > WS_VARIABLES.OS_IPLN_AMT) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5710;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B220_PRE_CHECK_DATA_I() throws IOException,SQLException,Exception {
        LNCENSCH.COMM_DATA.REPY_TYP = INTEREST;
        if (WS_VARIABLES.I_CMP_TERM > 1) {
            WS_VARIABLES.INPUT_TERM = (short) (WS_VARIABLES.I_CMP_TERM - 1);
            B220_01_GET_LAST_DUE_DATE();
            if (pgmRtn) return;
            WS_VARIABLES.I_START_DATE = LNRPLPI.DUE_DT;
            WS_COND_FLG.EXIST_SCHE_FLAG = 'Y';
        }
        if (LNCENSCH.COMM_DATA.IFNP_DAT <= WS_VARIABLES.I_START_DATE) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5738;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B220_01_GET_LAST_DUE_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'I';
        LNRPLPI.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = LNCENSCH.COMM_DATA.REPY_TYP;
        LNRPLPI.KEY.TERM = WS_VARIABLES.INPUT_TERM;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 18227;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B220_01_GET_LAST_DUE_DATE_P() throws IOException,SQLException,Exception {
        T000_STARTBR_LNTPLPI_P();
        if (pgmRtn) return;
        T000_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        T000_ENDBR_LNTPLPI();
        if (pgmRtn) return;
    }
    public void B220_02_GET_TOT_IPLN_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPRIN);
        LNCSPRIN.COMM_DATA.CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNCSPRIN.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCSPRIN.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSPRIN.COMM_DATA.SUF_NO = "0" + LNCSPRIN.COMM_DATA.SUF_NO;
        LNCSPRIN.COMM_DATA.REPY_TYP = LNCENSCH.COMM_DATA.REPY_TYP;
        S000_CALL_FUNC_LNZSPRIN();
        if (pgmRtn) return;
        WS_VARIABLES.P_TOT_IPLN_AMT = LNCSPRIN.COMM_DATA.P_TOT_IPLN_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.P_TOT_IPLN_AMT);
    }
    public void B230_BUILD_PSCHE_WITHOUT_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VAR);
        WS_VARIABLES.TOT_TERM = 1;
        WS_TEMP_LIST.CNT = 1;
        WS_LIST = new LNZENSCH_WS_LIST();
        WS_TEMP_LIST.WS_LIST.add(WS_LIST);
        WS_VARIABLES.NEXT_VAL_DT = LNCENSCH.COMM_DATA.PFNP_DAT;
        WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = LNCENSCH.COMM_DATA.PFNP_DAT;
        WS_VARIABLES.STANDARD_DATE = LNCENSCH.COMM_DATA.PFNP_DAT;
        WS_VARIABLES.TERM_UNIT = LNCENSCH.COMM_DATA.PFRE_TYP;
        WS_LIST.WS_LIST_DATA.LIST_DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
        WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
        while (WS_COND_FLG.PRO_END_FLAG != 'Y' 
            && (WS_TEMP_LIST.CNT <= MAX_TERM) 
            && (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT < WS_VARIABLES.LOAN_DUE_DATE)) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_VARIABLES.NEXT_VAL_DT;
            if (LNCENSCH.COMM_DATA.PFRE_TYP == 'M') {
                SCCCLDT.MTHS = LNCENSCH.COMM_DATA.PFRE_TERM;
            }
            if (LNCENSCH.COMM_DATA.PFRE_TYP == 'D') {
                SCCCLDT.DAYS = LNCENSCH.COMM_DATA.PFRE_TERM;
            }
            S00_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.IN_DATE = SCCCLDT.DATE2;
            R000_ADJUST_DUE_DATE();
            if (pgmRtn) return;
            WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.OUT_DATE;
            WS_VARIABLES.NEXT_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT >= WS_VARIABLES.LOAN_DUE_DATE) {
                CEP.TRC(SCCGWA, " WILL BE BREAKED");
                WS_COND_FLG.PRO_END_FLAG = 'Y';
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
            }
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT != WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT) {
                WS_VARIABLES.TOT_TERM = (short) (WS_VARIABLES.TOT_TERM + 1);
                WS_LIST = new LNZENSCH_WS_LIST();
                WS_TEMP_LIST.WS_LIST.add(WS_LIST);
                WS_TEMP_LIST.CNT = (short) (WS_TEMP_LIST.CNT + 1);
                WS_LIST.WS_LIST_DATA.LIST_DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
                WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            }
        }
        WS_TEMP_LIST.CNT = 1;
        WS_LIST = new LNZENSCH_WS_LIST();
        WS_TEMP_LIST.WS_LIST.add(WS_LIST);
        while (WS_TEMP_LIST.WS_LIST.get(WS_TEMP_LIST.CNT-1).WS_LIST_DATA.LIST_DUE_DT > 0 
            && WS_TEMP_LIST.CNT <= MAX_TERM) {
            WS_LIST = new LNZENSCH_WS_LIST();
            WS_TEMP_LIST.WS_LIST.add(WS_LIST);
            WS_TEMP_LIST.CNT = (short) (WS_TEMP_LIST.CNT + 1);
        }
        B231_GET_TOT_TERM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            WS_COND_FLG.REPY_MTH_FLG = 'P';
            B231_GET_PLPI_MIN_TERM();
            if (pgmRtn) return;
            WS_VARIABLES.LFT_TO_CMP_TERM = LNCSCALT.OUTPUT.INST_TERM + LNRPLPI.KEY.TERM - LNRICTL.P_CMP_TERM;
        } else {
            WS_VARIABLES.LFT_TO_CMP_TERM = LNCSCALT.OUTPUT.INST_TERM;
        }
        if (WS_VARIABLES.LFT_TO_CMP_TERM > 0) {
            B230_CAL_TERM_PRIN_AMT();
            if (pgmRtn) return;
        }
        WS_COND_FLG.PRO_END_FLAG = 'N';
        WS_COND_FLG.FIRST_REC_FLAG = 'Y';
        WS_TEMP_LIST.CNT = 0;
        WS_TEMP_LIST.CNT = 1;
        WS_LIST = new LNZENSCH_WS_LIST();
        WS_TEMP_LIST.WS_LIST.add(WS_LIST);
        WS_TEMP_VARS.TERM_CNT = 0;
        while (WS_COND_FLG.PRO_END_FLAG != 'Y') {
            WS_TEMP_VARS.TERM_CNT += 1;
            if (WS_COND_FLG.FIRST_REC_FLAG == 'Y') {
                WS_VARIABLES.RATQ_VAL_DT = LNCENSCH.COMM_DATA.PFNP_DAT;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.P_START_DATE;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_TEMP_LIST.WS_LIST.get(WS_TEMP_LIST.CNT-1).WS_LIST_DATA.LIST_DUE_DT;
                WS_LIST = new LNZENSCH_WS_LIST();
                WS_TEMP_LIST.WS_LIST.add(WS_LIST);
                WS_TEMP_LIST.CNT = (short) (WS_TEMP_LIST.CNT + 1);
            } else {
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.NEXT_VAL_DT;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_TEMP_LIST.WS_LIST.get(WS_TEMP_LIST.CNT-1).WS_LIST_DATA.LIST_DUE_DT;
                WS_LIST = new LNZENSCH_WS_LIST();
                WS_TEMP_LIST.WS_LIST.add(WS_LIST);
                WS_TEMP_LIST.CNT = (short) (WS_TEMP_LIST.CNT + 1);
            }
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT >= WS_VARIABLES.LOAN_DUE_DATE) {
                WS_COND_FLG.PRO_END_FLAG = 'Y';
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
            }
            WS_VARIABLES.NEW_TERM_NO = (short) (WS_VARIABLES.NEW_TERM_NO + 1);
            WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM = WS_VARIABLES.NEW_TERM_NO;
            if (WS_COND_FLG.FIRST_REC_FLAG == 'Y') {
                WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM = WS_VARIABLES.P_CMP_TERM;
                WS_VARIABLES.NEW_TERM_NO = WS_VARIABLES.P_CMP_TERM;
            }
            WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT = WS_VARIABLES.REPY_AMT;
            if (WS_TEMP_VARS.TERM_CNT > WS_VARIABLES.LFT_TO_CMP_TERM 
                && WS_VARIABLES.LFT_TO_CMP_TERM == 0 
                && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                WS_VARIABLES.LFT_TO_CMP_TERM = 1;
                B230_CAL_TERM_PRIN_AMT();
                if (pgmRtn) return;
            }
            if (WS_TEMP_VARS.TERM_CNT == WS_VARIABLES.LFT_TO_CMP_TERM) {
                WS_COND_FLG.PRO_END_FLAG = 'Y';
                WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT = WS_VARIABLES.LAST_TERM_AMT;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
            }
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT <= 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_LN5717;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCENSCH.COMM_DATA.PFRE_TYP == 'M') {
                B035_04_MOD_DAY();
                if (pgmRtn) return;
            }
            WS_VARIABLES.RATQ_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            B216_GET_ALL_IN_RATE();
            if (pgmRtn) return;
            WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE = PRINCIPAL;
            WS_VARIABLES.WS_TEMP_VAR.TEMP_ACT = ADD;
            WS_VARIABLES.WS_TEMP_VAR.TEMP_RATE = LNCRATQ.COMM_DATA.RATE;
            WS_VARIABLES.WS_TEMP_VAR.TEMP_RMK = " ";
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                B218_WRITE_LNTPLPI();
                if (pgmRtn) return;
                B219_GET_MAX_TERM_PLPI();
                if (pgmRtn) return;
                B220_READUP_LNTICTL();
                if (pgmRtn) return;
                B221_REWRITE_LNTICTL();
                if (pgmRtn) return;
            } else {
                B217_WRITE_LNTSCHT();
                if (pgmRtn) return;
            }
            WS_COND_FLG.FIRST_REC_FLAG = 'N';
            WS_VARIABLES.NEXT_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
        }
    }
    public void B231_GET_TOT_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCALT);
        if (LNCENSCH.COMM_DATA.REPY_TYP == 'P') {
            WS_TEMP_VARS.PAY_PERD = LNCENSCH.COMM_DATA.PFRE_TERM;
            WS_TEMP_VARS.PAY_UNIT = LNCENSCH.COMM_DATA.PFRE_TYP;
            LNCSCALT.INPUT.CAL_FST_DT = LNCENSCH.COMM_DATA.PFNP_DAT;
            LNCSCALT.INPUT.VAL_DT = WS_VARIABLES.P_START_DATE;
        }
        if (LNCENSCH.COMM_DATA.REPY_TYP == 'I') {
            WS_TEMP_VARS.PAY_PERD = LNCENSCH.COMM_DATA.IFRE_TERM;
            WS_TEMP_VARS.PAY_UNIT = LNCENSCH.COMM_DATA.IFRE_TYP;
            LNCSCALT.INPUT.CAL_FST_DT = LNCENSCH.COMM_DATA.IFNP_DAT;
            LNCSCALT.INPUT.VAL_DT = LNRICTL.IC_CMP_VAL_DT;
        }
        LNCSCALT.INPUT.DUE_DT = WS_TEMP_VARS.MAT_DATE;
        LNCSCALT.INPUT.PHS_FLG = 'N';
        LNCSCALT.INPUT.PHS_NUM = 0;
        LNCSCALT.INPUT.CAL_PERD_UNIT = WS_TEMP_VARS.PAY_UNIT;
        LNCSCALT.INPUT.CAL_PERD = WS_TEMP_VARS.PAY_PERD;
        LNCSCALT.INPUT.CAL_PAY_DAY = WS_TEMP_VARS.CAL_PAY_DAY;
        LNCSCALT.INPUT.CAL_FST_FLG = WS_TEMP_VARS.CAL_FST_FLG;
        LNCSCALT.INPUT.REPY_MTH = LNRAPRD.PAY_MTH;
        if (LNRCONT.PROD_CD == null) LNRCONT.PROD_CD = "";
        JIBS_tmp_int = LNRCONT.PROD_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) LNRCONT.PROD_CD += " ";
        LNCSCALT.INPUT.PROD_MOD = LNRCONT.PROD_CD.substring(0, 1).charAt(0);
        S000_CALL_LNZSCALT();
        if (pgmRtn) return;
    }
    public void B231_GET_PLPI_MIN_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        if (WS_COND_FLG.REPY_MTH_FLG == 'I') {
            LNRPLPI.KEY.REPY_MTH = 'I';
        } else {
            LNRPLPI.KEY.REPY_MTH = 'P';
        }
        T000_READ_PLPI_PROC();
        if (pgmRtn) return;
    }
    public void B240_BUILD_PSCHE_FIX_AMT() throws IOException,SQLException,Exception {
        WS_COND_FLG.FIRST_REC_FLAG = 'Y';
        WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = 0;
        WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = 0;
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VAR);
        WS_VARIABLES.STANDARD_DATE = LNCENSCH.COMM_DATA.PFNP_DAT;
        WS_VARIABLES.TERM_UNIT = LNCENSCH.COMM_DATA.PFRE_TYP;
        while (WS_COND_FLG.PRO_END_FLAG != 'Y' 
            && (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT < WS_VARIABLES.LOAN_DUE_DATE)) {
            if (WS_COND_FLG.FIRST_REC_FLAG == 'Y') {
                WS_VARIABLES.RATQ_VAL_DT = LNCENSCH.COMM_DATA.PFNP_DAT;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.P_START_DATE;
                WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = WS_VARIABLES.P_START_DATE;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = LNCENSCH.COMM_DATA.PFNP_DAT;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_VARIABLES.NEXT_VAL_DT;
                if (LNCENSCH.COMM_DATA.PFRE_TYP == 'M') {
                    SCCCLDT.MTHS = LNCENSCH.COMM_DATA.PFRE_TERM;
                }
                if (LNCENSCH.COMM_DATA.PFRE_TYP == 'D') {
                    SCCCLDT.DAYS = LNCENSCH.COMM_DATA.PFRE_TERM;
                }
                S00_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_VARIABLES.IN_DATE = SCCCLDT.DATE2;
                R000_ADJUST_DUE_DATE();
                if (pgmRtn) return;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.OUT_DATE;
                if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT >= WS_VARIABLES.LOAN_DUE_DATE) {
                    WS_COND_FLG.PRO_END_FLAG = 'Y';
                    WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
                }
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.NEXT_VAL_DT;
            }
            WS_VARIABLES.NEXT_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            WS_VARIABLES.RATQ_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            B216_GET_ALL_IN_RATE();
            if (pgmRtn) return;
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT >= WS_VARIABLES.LOAN_DUE_DATE) {
                WS_COND_FLG.PRO_END_FLAG = 'Y';
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
            }
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT != WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT) {
                WS_VARIABLES.NEW_TERM_NO = (short) (WS_VARIABLES.NEW_TERM_NO + 1);
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM = WS_VARIABLES.NEW_TERM_NO;
                if (WS_COND_FLG.FIRST_REC_FLAG == 'Y') {
                    WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM = WS_VARIABLES.P_CMP_TERM;
                    WS_VARIABLES.NEW_TERM_NO = WS_VARIABLES.P_CMP_TERM;
                }
                if (WS_VARIABLES.OS_IPLN_AMT > LNCENSCH.COMM_DATA.REPY_AMT 
                    && (WS_COND_FLG.PRO_END_FLAG != 'Y')) {
                    WS_VARIABLES.OS_IPLN_AMT = WS_VARIABLES.OS_IPLN_AMT - LNCENSCH.COMM_DATA.REPY_AMT;
                    WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT = LNCENSCH.COMM_DATA.REPY_AMT;
                } else {
                    WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT = WS_VARIABLES.OS_IPLN_AMT;
                    WS_COND_FLG.PRO_END_FLAG = 'Y';
                }
                if (WS_COND_FLG.PRO_END_FLAG == 'Y') {
                    WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
                }
                WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE = PRINCIPAL;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_ACT = ADD;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_RATE = LNCRATQ.COMM_DATA.RATE;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_RMK = " ";
                if (LNCENSCH.COMM_DATA.PFRE_TYP == 'M') {
                    B035_04_MOD_DAY();
                    if (pgmRtn) return;
                }
                if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    B218_WRITE_LNTPLPI();
                    if (pgmRtn) return;
                    B219_GET_MAX_TERM_PLPI();
                    if (pgmRtn) return;
                    B220_READUP_LNTICTL();
                    if (pgmRtn) return;
                    B221_REWRITE_LNTICTL();
                    if (pgmRtn) return;
                } else {
                    B217_WRITE_LNTSCHT();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            }
            WS_COND_FLG.FIRST_REC_FLAG = 'N';
        }
    }
    public void B260_BUILD_INT_SCHE() throws IOException,SQLException,Exception {
        WS_COND_FLG.FIRST_REC_FLAG = 'Y';
        WS_COND_FLG.PRO_END_FLAG = 'N';
        WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = 0;
        WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = 0;
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VAR);
        WS_VARIABLES.STANDARD_DATE = LNCENSCH.COMM_DATA.IFNP_DAT;
        WS_VARIABLES.TERM_UNIT = LNCENSCH.COMM_DATA.IFRE_TYP;
        B231_GET_TOT_TERM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            WS_COND_FLG.REPY_MTH_FLG = 'I';
            B231_GET_PLPI_MIN_TERM();
            if (pgmRtn) return;
            WS_VARIABLES.LFT_TO_CMP_TERM = LNCSCALT.OUTPUT.INST_TERM + LNRPLPI.KEY.TERM - LNRICTL.IC_CMP_TERM;
        } else {
            WS_VARIABLES.LFT_TO_CMP_TERM = LNCSCALT.OUTPUT.INST_TERM;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.LFT_TO_CMP_TERM);
        WS_TEMP_VARS.TERM_CNT = 0;
        while (WS_COND_FLG.PRO_END_FLAG != 'Y' 
            && (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT < WS_VARIABLES.LOAN_DUE_DATE)) {
            WS_TEMP_VARS.TERM_CNT += 1;
            if (WS_COND_FLG.FIRST_REC_FLAG == 'Y') {
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.I_START_DATE;
                WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = WS_VARIABLES.I_START_DATE;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = LNCENSCH.COMM_DATA.IFNP_DAT;
                WS_VARIABLES.NEW_TERM_NO = WS_VARIABLES.I_CMP_TERM;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_VARIABLES.NEXT_VAL_DT;
                if (LNCENSCH.COMM_DATA.IFRE_TYP == 'M') {
                    SCCCLDT.MTHS = LNCENSCH.COMM_DATA.IFRE_TERM;
                }
                if (LNCENSCH.COMM_DATA.IFRE_TYP == 'D') {
                    SCCCLDT.DAYS = LNCENSCH.COMM_DATA.IFRE_TERM;
                }
                S00_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_VARIABLES.IN_DATE = SCCCLDT.DATE2;
                R000_ADJUST_DUE_DATE();
                if (pgmRtn) return;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.OUT_DATE;
            }
            WS_VARIABLES.NEXT_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            if (WS_TEMP_VARS.TERM_CNT == WS_VARIABLES.LFT_TO_CMP_TERM) {
                CEP.TRC(SCCGWA, WS_TEMP_VARS.TERM_CNT);
                WS_COND_FLG.PRO_END_FLAG = 'Y';
                WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.LOAN_DUE_DATE;
            }
            if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT != WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT) {
                WS_VARIABLES.NEW_TERM_NO = (short) (WS_VARIABLES.NEW_TERM_NO + 1);
                WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM = WS_VARIABLES.NEW_TERM_NO;
                if (WS_COND_FLG.FIRST_REC_FLAG == 'Y') {
                    WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM = WS_VARIABLES.I_CMP_TERM;
                    WS_VARIABLES.NEW_TERM_NO = WS_VARIABLES.I_CMP_TERM;
                }
                WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT;
                CEP.TRC(SCCGWA, LNCENSCH.COMM_DATA.IFRE_TYP);
                if (LNCENSCH.COMM_DATA.IFRE_TYP == 'M') {
                    B035_04_MOD_DAY();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.RATQ_VAL_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
                B216_GET_ALL_IN_RATE();
                if (pgmRtn) return;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_TYPE = INTEREST;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_ACT = ADD;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_RATE = LNCRATQ.COMM_DATA.RATE;
                WS_VARIABLES.WS_TEMP_VAR.TEMP_RMK = " ";
                WS_VARIABLES.WS_TEMP_VAR.TEMP_AMT = 0;
                if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    B218_WRITE_LNTPLPI();
                    if (pgmRtn) return;
                    B219_GET_MAX_TERM_PLPI();
                    if (pgmRtn) return;
                    B220_READUP_LNTICTL();
                    if (pgmRtn) return;
                    B221_REWRITE_LNTICTL();
                    if (pgmRtn) return;
                } else {
                    B217_WRITE_LNTSCHT();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_TEMP_VAR.PREV_TEMP_DUE_DT = WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT;
            }
            WS_COND_FLG.FIRST_REC_FLAG = 'N';
        }
    }
    public void B270_04_GENERATE_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'A';
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNRSCHT.KEY.SUB_CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TERM = WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_TERM_VAR;
        LNRSCHT.ACTION = ADD;
        LNRSCHT.VAL_DTE = WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_VAL_DT;
        B216_GET_ALL_IN_RATE();
        if (pgmRtn) return;
        LNRSCHT.ALL_IN_RATE = LNCRATQ.COMM_DATA.RATE;
        if (WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_TOT_AMT >= LNRCMMT.CTA_AMT) {
            WS_VARIABLES.HIGH_AMT1 = LNCENSCH.COMM_DATA.PRIN_AMT - WS_VARIABLES.TOT_PRIN_AMT;
        }
        LNRSCHT.AMOUNT = WS_VARIABLES.HIGH_AMT1 + 0;
        bigD = new BigDecimal(LNRSCHT.AMOUNT);
        LNRSCHT.AMOUNT = bigD.setScale(4, RoundingMode.HALF_UP).doubleValue();
        WS_VARIABLES.TOT_PRIN_AMT = WS_VARIABLES.TOT_PRIN_AMT + LNRSCHT.AMOUNT;
        LNRSCHT.ACTION = ADD;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 20819;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_SCHT_VAR_AREA.SCHT_TERM_VAR += 1;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B400_GET_MIN_TERM_NO();
        if (pgmRtn) return;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCENSCH.COMM_DATA.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATA.CURR_PAGE = (short) LNCENSCH.COMM_DATA.PAGE_NUM;
        }
        WS_VARIABLES.WS_DATA.LAST_PAGE = 'N';
        if (LNCENSCH.COMM_DATA.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATA.PAGE_ROW = PAGE_ROW;
        } else {
            if (LNCENSCH.COMM_DATA.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCENSCH.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) LNCENSCH.COMM_DATA.PAGE_ROW;
            }
        }
        WS_VARIABLES.WS_DATA.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
        B300_01_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        B300_02_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        if (LNCRSCHT.RETURN_INFO != 'E') {
            for (WS_VARIABLES.IDX = 1; (WS_VARIABLES.IDX <= WS_VARIABLES.WS_DATA.PAGE_ROW) 
                && LNCRSCHT.RETURN_INFO != 'E'; WS_VARIABLES.IDX += 1) {
                R000_TRANS_DATA_MPAGE_OUTPUT();
                if (pgmRtn) return;
                B300_02_READNEXT_LNTSCHT();
                if (pgmRtn) return;
            }
            if (LNCRSCHT.RETURN_INFO == 'E') {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.IDX - 1;
                WS_DB_VARS.TOTAL_NUM = ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW + WS_VARIABLES.WS_DATA.BAL_CNT;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) WS_VARIABLES.WS_DATA.BAL_CNT;
            } else {
                T100_GET_TOTAL_LNTSCHT();
                if (pgmRtn) return;
                if (WS_VARIABLES.WS_DATA.PAGE_ROW == 0) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_PAGE_ROW;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_DATA.BAL_CNT = WS_DB_VARS.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_DB_VARS.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
            WS_DB_VARS.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
        }
        B300_03_ENDBR_LNTSCHT();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DB_VARS.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
        WS_OUT_RECODE.WS_OUT_HEAD.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNCENSCH.COMM_DATA.SEQ = WS_VARIABLES.SEQ_NO;
        R000_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B300_01_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.RUN_STEP_FLAG == '7' 
            || WS_COND_FLG.RUN_STEP_FLAG == '8') {
            LNCENSCH.COMM_DATA.REPY_TYP = ' ';
        }
        IBS.init(SCCGWA, LNRSCHT);
        IBS.init(SCCGWA, LNCRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'H';
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNRSCHT.KEY.SUB_CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TYPE = LNCENSCH.COMM_DATA.REPY_TYP;
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_BY_KEY_SHOW();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_KEY_SHOW2();
            if (pgmRtn) return;
        }
    }
    public void B300_02_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'R';
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B300_03_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'E';
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B035_04_MOD_DAY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.LOAN_DUE_DATE);
        if (WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT < WS_VARIABLES.LOAN_DUE_DATE) {
            IBS.CPY2CLS(SCCGWA, WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT+"", WS_VARIABLES.WS_YYYYMMDD);
            CEP.TRC(SCCGWA, WS_TEMP_VARS.CAL_PAY_DAY);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_YYYYMMDD.MM);
            if (WS_TEMP_VARS.CAL_PAY_DAY < 29) {
                if (WS_TEMP_VARS.CAL_PAY_DAY > 0) {
                    WS_VARIABLES.WS_YYYYMMDD.DD = WS_TEMP_VARS.CAL_PAY_DAY;
                }
            } else {
                if (WS_TEMP_VARS.CAL_PAY_DAY == 29 
                    || WS_TEMP_VARS.CAL_PAY_DAY == 30) {
                    if (WS_VARIABLES.WS_YYYYMMDD.MM == 2) {
                        if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 100 == 0) {
                            if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 400 == 0) {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 29;
                            } else {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 28;
                            }
                        } else {
                            if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 4 == 0) {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 29;
                            } else {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 28;
                            }
                        }
                    } else {
                        WS_VARIABLES.WS_YYYYMMDD.DD = WS_TEMP_VARS.CAL_PAY_DAY;
                    }
                }
                if (WS_TEMP_VARS.CAL_PAY_DAY == 31) {
                    if (WS_VARIABLES.WS_YYYYMMDD.MM == 2) {
                        if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 100 == 0) {
                            if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 400 == 0) {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 29;
                            } else {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 28;
                            }
                        } else {
                            if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 4 == 0) {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 29;
                            } else {
                                WS_VARIABLES.WS_YYYYMMDD.DD = 28;
                            }
                        }
                    } else {
                        if (WS_VARIABLES.WS_YYYYMMDD.MM == 4 
                            || WS_VARIABLES.WS_YYYYMMDD.MM == 6 
                            || WS_VARIABLES.WS_YYYYMMDD.MM == 9 
                            || WS_VARIABLES.WS_YYYYMMDD.MM == 11) {
                            WS_VARIABLES.WS_YYYYMMDD.DD = 30;
                        } else {
                            WS_VARIABLES.WS_YYYYMMDD.DD = 31;
                        }
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_YYYYMMDD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_YYYYMMDD);
            WS_VARIABLES.OUT_DATE = Integer.parseInt(JIBS_tmp_str[0]);
            if (WS_VARIABLES.OUT_DATE >= WS_VARIABLES.LOAN_DUE_DATE) {
                WS_VARIABLES.OUT_DATE = WS_VARIABLES.LOAN_DUE_DATE;
            }
            if (LNCENSCH.COMM_DATA.REPY_TYP == 'I') {
                if (WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM == LNRICTL.IC_CAL_TERM) {
                    WS_VARIABLES.OUT_DATE = LNRICTL.IC_CAL_DUE_DT;
                }
            }
            if (LNCENSCH.COMM_DATA.REPY_TYP == 'P') {
                if (WS_VARIABLES.WS_TEMP_VAR.TEMP_TERM == LNRICTL.P_CAL_TERM) {
                    WS_VARIABLES.OUT_DATE = LNRICTL.P_CAL_DUE_DT;
                }
            }
            WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT = WS_VARIABLES.OUT_DATE;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VAR.TEMP_DUE_DT);
    }
    public void T100_GET_TOTAL_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.SUB_CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        if (LNCENSCH.COMM_DATA.REPY_TYP == ' ') {
            LNTSCHT_RD = new DBParm();
            LNTSCHT_RD.TableName = "LNTSCHT";
            LNTSCHT_RD.set = "WS-TOTAL-NUM=COUNT(*)";
            LNTSCHT_RD.where = "CONTRACT_NO = :LNRSCHT.KEY.SUB_CTA_NO "
                + "AND TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ";
            IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        } else {
            LNRSCHT.KEY.TYPE = LNCENSCH.COMM_DATA.REPY_TYP;
            LNTSCHT_RD = new DBParm();
            LNTSCHT_RD.TableName = "LNTSCHT";
            LNTSCHT_RD.set = "WS-TOTAL-NUM=COUNT(*)";
            LNTSCHT_RD.where = "CONTRACT_NO = :LNRSCHT.KEY.SUB_CTA_NO "
                + "AND TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
                + "AND TYPE = :LNRSCHT.KEY.TYPE";
            IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        }
    }
    public void B400_GET_MIN_TERM_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'E';
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNRSCHT.KEY.SUB_CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TYPE = INTEREST;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 20819;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
        WS_VARIABLES.TERM_I = LNCRSCHT.TERM_MIN;
        if (WS_VARIABLES.TERM_I < 1) {
            WS_VARIABLES.TERM_I = LNRICTL.IC_CMP_TERM;
            CEP.TRC(SCCGWA, LNRICTL.IC_CMP_TERM);
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.TERM_I);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT);
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'E';
        LNRSCHT.KEY.TRAN_SEQ = WS_VARIABLES.SEQ_NO;
        LNRSCHT.KEY.SUB_CTA_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TYPE = PRINCIPAL;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 20819;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
        WS_VARIABLES.TERM_P = LNCRSCHT.TERM_MIN;
        if (WS_VARIABLES.TERM_P < 1) {
            WS_VARIABLES.TERM_P = LNRICTL.P_CMP_TERM;
            CEP.TRC(SCCGWA, LNRICTL.P_CMP_TERM);
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.TERM_P);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VAR.TEMP_VAL_DT);
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANSFER_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANSFER_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1]);
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_REPY_MTH = LNRSCHT.KEY.TYPE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_TERM_NO = LNRSCHT.KEY.TERM;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_VAL_DT = LNRSCHT.VAL_DTE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_DUE_DT = LNRSCHT.DUE_DTE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_ALL_IN_RAT = LNRSCHT.ALL_IN_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_REPY_AMT = LNRSCHT.AMOUNT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_VARIABLES.IDX-1].O_RMK = LNRSCHT.REMARK;
        WS_VARIABLES.WS_DATA.NEXT_START_NUM += 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 0;
    }
    public void R000_GET_PART_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.RC);
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'C';
        LNCIPART.DATA.CONTRACT_NO = WS_VARIABLES.TRANCHE_NO;
        CEP.TRC(SCCGWA, LNCIPART.DATA.CONTRACT_NO);
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
    }
    public void R000_DISB_PART_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPARA.RC);
        IBS.init(SCCGWA, LNCIPARA.DATA);
        LNCIPARA.DATA.FUNC = 'R';
        LNCIPARA.DATA.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        CEP.TRC(SCCGWA, LNCIPARA.DATA.CONTRACT_NO);
        S000_CALL_LNZIPARA();
        if (pgmRtn) return;
    }
    public void R000_ADJUST_DUE_DATE() throws IOException,SQLException,Exception {
        WS_COND_FLG.MONTH_END_FLAG = ' ';
        IBS.CPY2CLS(SCCGWA, WS_VARIABLES.STANDARD_DATE+"", WS_VARIABLES.WS_YYYYMMDD);
        IBS.CPY2CLS(SCCGWA, WS_VARIABLES.IN_DATE+"", WS_VARIABLES.WS_IN_YYYYMMDD);
        WS_VARIABLES.OUT_DATE = WS_VARIABLES.IN_DATE;
        if (WS_VARIABLES.WS_YYYYMMDD.DD > 28) {
            if ((WS_VARIABLES.WS_YYYYMMDD.MM == 1 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 3 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 5 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 7 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 8 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 10 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 12)) {
                WS_VARIABLES.MONTH_END = 31;
                if (WS_VARIABLES.WS_YYYYMMDD.DD == WS_VARIABLES.MONTH_END) {
                    WS_COND_FLG.MONTH_END_FLAG = 'Y';
                }
            } else if ((WS_VARIABLES.WS_YYYYMMDD.MM == 4 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 6 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 9 
                    || WS_VARIABLES.WS_YYYYMMDD.MM == 11)) {
                WS_VARIABLES.MONTH_END = 30;
                if (WS_VARIABLES.WS_YYYYMMDD.DD == WS_VARIABLES.MONTH_END) {
                    WS_COND_FLG.MONTH_END_FLAG = 'Y';
                }
            } else if (WS_VARIABLES.WS_YYYYMMDD.MM == 2) {
                if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 100 == 0) {
                    if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 400 == 0) {
                        WS_VARIABLES.MONTH_END = 29;
                    } else {
                        WS_VARIABLES.MONTH_END = 28;
                    }
                } else {
                    if (WS_VARIABLES.WS_YYYYMMDD.YYYY % 4 == 0) {
                        WS_VARIABLES.MONTH_END = 29;
                    } else {
                        WS_VARIABLES.MONTH_END = 28;
                    }
                }
                if (WS_VARIABLES.WS_YYYYMMDD.DD == WS_VARIABLES.MONTH_END) {
                    WS_COND_FLG.MONTH_END_FLAG = 'Y';
                }
            } else {
                CEP.TRC(SCCGWA, "WWW --1--INCORRECT MONTH NUMBER");
                Z_RET();
                if (pgmRtn) return;
            }
            if ((WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 1 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 3 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 5 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 7 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 8 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 10 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 12)) {
                WS_VARIABLES.IN_MONTH_END = 31;
            } else if ((WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 4 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 6 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 9 
                    || WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 11)) {
                WS_VARIABLES.IN_MONTH_END = 30;
            } else if (WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 2) {
                if (WS_VARIABLES.WS_IN_YYYYMMDD.IN_YYYY % 100 == 0) {
                    if (WS_VARIABLES.WS_IN_YYYYMMDD.IN_YYYY % 400 == 0) {
                        WS_VARIABLES.IN_MONTH_END = 29;
                    } else {
                        WS_VARIABLES.IN_MONTH_END = 28;
                    }
                } else {
                    if (WS_VARIABLES.WS_IN_YYYYMMDD.IN_YYYY % 4 == 0) {
                        WS_VARIABLES.IN_MONTH_END = 29;
                    } else {
                        WS_VARIABLES.IN_MONTH_END = 28;
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "WWW --2--INCORRECT MONTH NUMBER");
                Z_RET();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.TERM_UNIT == MONTH) {
                if (WS_COND_FLG.MONTH_END_FLAG == 'Y') {
                    JIBS_tmp_str[0] = "" + WS_VARIABLES.OUT_DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_VARIABLES.OUT_DATE;
                    JIBS_tmp_str[1] = "" + WS_VARIABLES.IN_MONTH_END;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_VARIABLES.OUT_DATE = Integer.parseInt(JIBS_NumStr);
                } else {
                    if (WS_VARIABLES.WS_IN_YYYYMMDD.IN_MM == 2) {
                        JIBS_tmp_str[0] = "" + WS_VARIABLES.OUT_DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_VARIABLES.OUT_DATE;
                        JIBS_tmp_str[1] = "" + WS_VARIABLES.IN_MONTH_END;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                        WS_VARIABLES.OUT_DATE = Integer.parseInt(JIBS_NumStr);
                    } else {
                        JIBS_tmp_str[0] = "" + WS_VARIABLES.OUT_DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_VARIABLES.OUT_DATE;
                        JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_YYYYMMDD.DD;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                        WS_VARIABLES.OUT_DATE = Integer.parseInt(JIBS_NumStr);
                    }
                }
            }
        }
    }
    public void R000_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "LNZ10";
            SCCFMT.DATA_PTR = WS_OUT_RECODE;
            SCCFMT.DATA_LEN = 5006;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void T000_STARTBR_LNTPLPI_P() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCENSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'I';
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPLPI_BR.rp.where = "SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH < > :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        WS_COND_FLG.EXIST_SCHE_FLAG = ' ';
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.EXIST_SCHE_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.EXIST_SCHE_FLAG = 'N';
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_KEY_SHOW() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND TYPE >= :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_SHOW2() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        WS_DB_VARS.TOT_CNT = 0;
        WS_DB_VARS.TOT_SUM = 0;
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        LNCRSCHT.TOT_CNT = WS_DB_VARS.TOT_CNT;
        LNCRSCHT.TOT_SUM = WS_DB_VARS.TOT_SUM;
        CEP.TRC(SCCGWA, LNCRSCHT.TOT_CNT);
        CEP.TRC(SCCGWA, LNCRSCHT.TOT_SUM);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRSCHT.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSCHT_BR);
    }
    public void T000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_APRD_NOTFND, LNCENSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PLPI_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO";
        LNTPLPI_RD.where = "SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "TERM";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRPLPI.KEY.TERM = 1;
        }
    }
    public void S00_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
    }
    public void S000_CALL_SRC_LNZRICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            if (LNCRICTL.RETURN_INFO != 'N') {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRCMMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        if (LNCRCMMT.RC.RC_CODE != 0) {
            if (LNCRCMMT.RETURN_INFO != 'N') {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            if (LNCRCONT.RETURN_INFO != 'N') {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        CEP.TRC(SCCGWA, LNCRLOAN.RC);
        if (LNCRLOAN.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.LN_PLPI_NOTFND)) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
        if (LNCRSCHT.RC.RC_CODE != 0) {
            if (LNCRSCHT.RETURN_INFO == 'N' 
                || LNCRSCHT.RETURN_INFO == 'E') {
            } else {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRSCHT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_FUNC_LNZSPRIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-SUM-PRIN", LNCSPRIN);
        if (LNCSPRIN.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPRIN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FUNC_LNZRATQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATH-INQ", LNCRATQ);
    }
    public void S000_CALL_FUNC_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-WORK-DAY", BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FUNC_LNZIHDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-HOLDAY-CHK", LNCIHDCK);
        if (LNCIHDCK.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIHDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FUN_LNZQRSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-U-INQ-REPAY-SCHE", LNCQRSCH);
        if (LNCQRSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCQRSCH.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.LN_PLPI_NOTFND)) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCQRSCH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S00_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT3 = new SCSSCLDT();
        SCSSCLDT3.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_AP = "SC";
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_CODE = SCCCLDT.RC;
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPARA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PARTI-AMT-DISB", LNCIPARA);
        if (LNCIPARA.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPARA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B230_CAL_TERM_PRIN_AMT() throws IOException,SQLException,Exception {
        WS_VARIABLES.ORIGN_REPY_AMT = WS_VARIABLES.OS_IPLN_AMT / WS_VARIABLES.LFT_TO_CMP_TERM;
        bigD = new BigDecimal(WS_VARIABLES.ORIGN_REPY_AMT);
        WS_VARIABLES.ORIGN_REPY_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCENSCH.COMM_DATA.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCQCCY.DATA.DEC_MTH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) {
            WS_VARIABLES.LOW_CCY_INT_AMT = WS_VARIABLES.ORIGN_REPY_AMT + 0;
            bigD = new BigDecimal(WS_VARIABLES.LOW_CCY_INT_AMT);
            WS_VARIABLES.LOW_CCY_INT_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            WS_VARIABLES.REPY_AMT = (double)WS_VARIABLES.LOW_CCY_INT_AMT;
        }
        JIBS_tmp_str[0] = "" + BPCQCCY.DATA.DEC_MTH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.LOW_CCY_INT_AMT1 = WS_VARIABLES.ORIGN_REPY_AMT + 0;
            bigD = new BigDecimal(WS_VARIABLES.LOW_CCY_INT_AMT1);
            WS_VARIABLES.LOW_CCY_INT_AMT1 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            WS_VARIABLES.REPY_AMT = WS_VARIABLES.LOW_CCY_INT_AMT1;
        }
        JIBS_tmp_str[0] = "" + BPCQCCY.DATA.DEC_MTH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("2")) {
            WS_VARIABLES.LOW_CCY_INT_AMT2 = WS_VARIABLES.ORIGN_REPY_AMT + 0;
            bigD = new BigDecimal(WS_VARIABLES.LOW_CCY_INT_AMT2);
            WS_VARIABLES.LOW_CCY_INT_AMT2 = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            WS_VARIABLES.REPY_AMT = WS_VARIABLES.LOW_CCY_INT_AMT2;
        }
        if (WS_VARIABLES.REPY_AMT <= 0) {
            CEP.TRC(SCCGWA, "WWW CHECK LOW CCY FAILED");
            WS_VARIABLES.REPY_AMT = WS_VARIABLES.ORIGN_REPY_AMT;
        }
        WS_VARIABLES.LAST_TERM_AMT = WS_VARIABLES.OS_IPLN_AMT - ( WS_VARIABLES.REPY_AMT * WS_VARIABLES.LFT_TO_CMP_TERM ) + WS_VARIABLES.REPY_AMT;
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCALT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CAL-TERM", LNCSCALT);
        if (LNCSCALT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSCALT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCENSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.ERR_INFO, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_VARIABLES.ERR_MSG, LNCENSCH.RC);
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.ERR_INFO);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCENSCH.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCENSCH=");
            CEP.TRC(SCCGWA, LNCENSCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
