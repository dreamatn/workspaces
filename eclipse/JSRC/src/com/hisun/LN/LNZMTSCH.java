package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZMTSCH {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAPRD_RD;
    DBParm LNTAGRE_RD;
    boolean pgmRtn = false;
    char K_TRUE_ID = '1';
    char K_ADD = 'A';
    char K_INQUIRY = 'I';
    char K_DELETE = 'D';
    char K_MODIFY = 'M';
    char K_UPDATE = 'U';
    char K_CANCEL = 'C';
    char K_PRINCIPAL = 'P';
    char K_INTEREST = 'I';
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNZMTSCH_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZMTSCH_WS_TEMP_VARIABLE();
    LNZMTSCH_WS_MSG_INFO WS_MSG_INFO = new LNZMTSCH_WS_MSG_INFO();
    String WS_TRANCHE_NO = " ";
    int WS_SUB_CTA_NO = 0;
    int WS_RATQ_VAL_DT = 0;
    int WS_NEXT_VAL_DT = 0;
    int WS_NEXT_DUE_DT = 0;
    int WS_TEMP_VAL_DT = 0;
    int WS_TEMP_DUE_DT = 0;
    char WS_TEMP_RPY_MTH = ' ';
    int WS_LOAN_DUE_DT = 0;
    short WS_TERM_NO = 0;
    short WS_MTSPB_TERM_NO = 0;
    int WS_MTSPB_VAL_DT = 0;
    int WS_MTSPB_DUE_DT = 0;
    short WS_TERM_P = 0;
    short WS_TERM_I = 0;
    short WS_LAST_TERM_NO = 0;
    short WS_CUR_TERM_NO = 0;
    short WS_NEXT_TERM_NO = 0;
    short WS_UPD_NEXT_TERM = 0;
    char WS_READ_LNTAPRD_FLG = ' ';
    LNZMTSCH_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNZMTSCH_WS_OUTPUT_LIST();
    int WS_LAST_P_DUE_DT = 0;
    String WS_CALR_STD = " ";
    short WS_INT_BAS = 0;
    int WS_CLDT_DAYS = 0;
    double WS_BALL_LOAN_BAL = 0;
    double WS_I_SCH_AMOUNT = 0;
    int WS_I = 0;
    int WS_CLDT_DATE1 = 0;
    int WS_CLDT_DATE2 = 0;
    int WS_SCHT_CRT_DATE = 0;
    String WS_SCHT_CRT_TELR = " ";
    char WS_DATA_OK_FLAGE = ' ';
    char WS_INSERT_FLAGE = ' ';
    char WS_BREAK_FLAGE = ' ';
    char WS_ACTION = ' ';
    char WS_HOLIDAY_O_FLG = ' ';
    char WS_CMMT_EXIST_FLG = ' ';
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRCONT LNRCONT = new LNRCONT();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCOSCH1 LNCOSCH1 = new LNCOSCH1();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    LNCMTSPB LNCMTSPB = new LNCMTSPB();
    LNCRATQ LNCRATQ = new LNCRATQ();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRBALL LNRBALL = new LNRBALL();
    LNCRBALL LNCRBALL = new LNCRBALL();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCMTSCH LNCMTSCH;
    public void MP(SCCGWA SCCGWA, LNCMTSCH LNCMTSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCMTSCH = LNCMTSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZMTSCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_DATA();
        if (pgmRtn) return;
        B105_GET_ORG_DATA();
        if (pgmRtn) return;
        B108_LOGIC_CHK_PROC();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.REPY_TYP);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.LIST_TYP);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.AMOUNT);
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_LOAN_DUE_DT = LNRCONT.MAT_DATE;
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_REPAY_LGU_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.FUNC == 'M') {
            R000_GET_SCHT_VAL_DTE();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.FUNC == 'D') {
            R000_GET_SCHT_DUE_DTE();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.LIST_TYP <= SPACE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5716;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.FUNC != 'D' 
            && LNCMTSCH.COMM_DATA.LIST_TYP == K_PRINCIPAL 
            && LNCMTSCH.COMM_DATA.AMOUNT <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5717;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if ((LNCMTSCH.COMM_DATA.REPY_TYP > SPACE) 
            && (LNCMTSCH.COMM_DATA.REPY_TYP != LNCMTSCH.COMM_DATA.LIST_TYP)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5732;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE != 3200) {
            if (LNCMTSCH.COMM_DATA.DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.DUE_DT);
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DUE_DT_GT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B105_GET_ORG_DATA() throws IOException,SQLException,Exception {
        R000_GET_LOAN_INFO();
        if (pgmRtn) return;
        R000_GET_ICTL_INFO();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1") 
            && LNCMTSCH.COMM_DATA.LIST_TYP == 'I') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_SUBS_CANNOT_MTSCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_APRD_INFO();
        if (pgmRtn) return;
    }
    public void B108_LOGIC_CHK_PROC() throws IOException,SQLException,Exception {
        B111_GET_MIN_TERM_NO();
        if (pgmRtn) return;
        if (LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M') {
            B110_CHECK_FOR_AM();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE != 3200 
            && SCCGWA.COMM_AREA.TR_ID.TR_CODE != 3100) {
            if (LNCMTSCH.COMM_DATA.FUNC == 'D') {
                if (LNCMTSCH.COMM_DATA.DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    WS_DATA_OK_FLAGE = 'N';
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5728;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
                if (LNCMTSCH.COMM_DATA.DUE_DT < LNCMTSCH.COMM_DATA.VAL_DT) {
                    WS_DATA_OK_FLAGE = 'N';
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5744;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B110_CHECK_FOR_AM() throws IOException,SQLException,Exception {
        if (LNCMTSCH.COMM_DATA.LIST_TYP == K_INTEREST) {
            WS_CALR_STD = LNRAPRD.INT_DBAS_STD;
            CEP.TRC(SCCGWA, LNRAPRD.INT_DBAS_STD);
            CEP.TRC(SCCGWA, WS_CALR_STD);
            if (WS_CALR_STD.equalsIgnoreCase("1")
                || WS_CALR_STD.equalsIgnoreCase("4")
                || WS_CALR_STD.equalsIgnoreCase("5")) {
                WS_INT_BAS = 360;
            } else if (WS_CALR_STD.equalsIgnoreCase("2")) {
                WS_INT_BAS = 365;
            } else if (WS_CALR_STD.equalsIgnoreCase("3")) {
                WS_INT_BAS = 366;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID INTEREST RATE(" + WS_CALR_STD + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
        WS_DATA_OK_FLAGE = 'Y';
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase(K_TRUE_ID+"")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_HAS_DELETION;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRCONT.CONTRACT_TYPE);
        if ((LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M' 
            || LNCMTSCH.COMM_DATA.FUNC == 'D') 
            && LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_REPAY_LGU_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.FUNC == 'A') {
            LNCMTSCH.COMM_DATA.VAL_DT = 0;
            B130_GET_FOR_ADD_LNTSCHT();
            if (pgmRtn) return;
            LNCMTSCH.COMM_DATA.VAL_DT = WS_NEXT_VAL_DT;
            B231_04_STARTBR_FIRST_LNTSCHT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRSCHT.DUE_DTE);
            CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.DUE_DT);
            if (LNCRSCHT.RETURN_INFO == 'N' 
                || LNRSCHT.DUE_DTE > LNCMTSCH.COMM_DATA.DUE_DT) {
                B120_GET_MAX_TERM_NO();
                if (pgmRtn) return;
            } else {
                WS_CUR_TERM_NO = LNRSCHT.KEY.TERM;
            }
            if (LNCMTSCH.COMM_DATA.TERM != WS_CUR_TERM_NO) {
                CEP.TRC(SCCGWA, "TERM NO MUST CHANGE");
                LNCMTSCH.COMM_DATA.TERM = WS_CUR_TERM_NO;
            }
        }
        if (LNCMTSCH.COMM_DATA.FUNC == 'M') {
            B130_GET_FOR_UPD_LNTSCHT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CUR_TERM_NO);
        CEP.TRC(SCCGWA, WS_NEXT_TERM_NO);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, WS_NEXT_VAL_DT);
        CEP.TRC(SCCGWA, WS_NEXT_DUE_DT);
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        CEP.TRC(SCCGWA, LNRSCHT.DUE_DTE);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, WS_LOAN_DUE_DT);
        if (LNCMTSCH.COMM_DATA.DUE_DT > WS_LOAN_DUE_DT) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5706;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRSCHT.DUE_DTE);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.VAL_DT);
        if (WS_NEXT_DUE_DT > 0 
            && LNCMTSCH.COMM_DATA.DUE_DT > WS_NEXT_DUE_DT) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5745;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.DUE_DT == LNRSCHT.DUE_DTE 
            && LNCMTSCH.COMM_DATA.FUNC == 'A') {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN1736;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNCMTSCH.COMM_DATA.DUE_DT <= LNCMTSCH.COMM_DATA.VAL_DT) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5721;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNRICTL.INT_CUT_DT);
        CEP.TRC(SCCGWA, LNRCONT.LAST_F_VAL_DATE);
        if (LNCMTSCH.COMM_DATA.DUE_DT <= LNRCONT.LAST_F_VAL_DATE) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5730;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M') 
            && LNCMTSCH.COMM_DATA.REPY_TYP == 'I' 
            && LNCMTSCH.COMM_DATA.DUE_DT <= LNRICTL.INT_CUT_DT) {
            CEP.TRC(SCCGWA, "I");
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5723;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_P_DUE_DT();
        if (pgmRtn) return;
        if ((LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M') 
            && LNCMTSCH.COMM_DATA.REPY_TYP == 'P' 
            && LNCMTSCH.COMM_DATA.DUE_DT <= WS_LAST_P_DUE_DT) {
            CEP.TRC(SCCGWA, "P");
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5724;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M') 
            && LNCMTSCH.COMM_DATA.LIST_TYP == K_PRINCIPAL 
            && LNCMTSCH.COMM_DATA.DUE_DT <= LNRICTL.INT_CUT_DT) {
            CEP.TRC(SCCGWA, "I");
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5723;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if ((LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M') 
            && (LNRAPRD.PAY_MTH == '1' 
            || LNRAPRD.PAY_MTH == '2') 
            && LNCMTSCH.COMM_DATA.LIST_TYP == K_PRINCIPAL 
            && (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CMS") 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("RCS") 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("NCMS")) 
            && LNCMTSCH.COMM_DATA.DUE_DT != WS_LOAN_DUE_DT) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PRINCIPAL_DUE_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (LNRAPRD.PAY_MTH == '4' 
            && (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CMS") 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("RCS") 
            && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("NCMS")) 
            && (LNCMTSCH.COMM_DATA.FUNC == 'A' 
            || LNCMTSCH.COMM_DATA.FUNC == 'M' 
            || LNCMTSCH.COMM_DATA.FUNC == 'D')) {
            WS_DATA_OK_FLAGE = 'N';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INSTALMENT_PI;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRAPRD.PAY_MTH == '0' 
            && LNCMTSCH.COMM_DATA.FUNC == 'M') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CANT_CHG_SCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B120_GET_MAX_TERM_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'G';
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MAX);
        if (LNCRSCHT.TERM_MAX > 0) {
            CEP.TRC(SCCGWA, "TERM MAX");
            WS_CUR_TERM_NO = (short) (LNCRSCHT.TERM_MAX + 1);
        } else {
            IBS.init(SCCGWA, LNCRSCHT);
            IBS.init(SCCGWA, LNRSCHT);
            LNCRSCHT.FUNC = 'M';
            LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
            LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
            LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
            LNCRSCHT.REC_PTR = LNRSCHT;
            LNCRSCHT.REC_LEN = 356;
            S000_CALL_SRC_LNZRSCHT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
            if (LNCRSCHT.TERM_MIN > 0) {
                CEP.TRC(SCCGWA, "TERM MIN");
                WS_CUR_TERM_NO = LNCRSCHT.TERM_MIN;
            } else {
                CEP.TRC(SCCGWA, "TERM ZERO");
                CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.LIST_TYP);
                if (LNCMTSCH.COMM_DATA.LIST_TYP == K_INTEREST) {
                    WS_CUR_TERM_NO = WS_TERM_I;
                } else {
                    WS_CUR_TERM_NO = WS_TERM_P;
                }
            }
        }
    }
    public void B111_GET_MIN_TERM_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'E';
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TYPE = K_INTEREST;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
        WS_TERM_I = LNCRSCHT.TERM_MIN;
        if (WS_TERM_I < 1) {
            WS_TERM_I = LNRICTL.IC_CMP_TERM;
            WS_TERM_I = LNRICTL.IC_CAL_TERM;
            CEP.TRC(SCCGWA, LNRICTL.IC_CMP_TERM);
        }
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CMS") 
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("RCS") 
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("NCMS")) {
            WS_TERM_I = LNRICTL.IC_CAL_TERM;
            WS_TERM_I = LNRICTL.IC_CAL_TERM;
            CEP.TRC(SCCGWA, LNRICTL.IC_CMP_TERM);
        }
        CEP.TRC(SCCGWA, WS_TERM_I);
        CEP.TRC(SCCGWA, WS_TEMP_VAL_DT);
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'E';
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TYPE = K_PRINCIPAL;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRSCHT.TERM_MIN);
        WS_TERM_P = LNCRSCHT.TERM_MIN;
        if (WS_TERM_P < 1) {
            WS_TERM_P = LNRICTL.P_CMP_TERM;
            WS_TERM_P = LNRICTL.P_CAL_TERM;
            CEP.TRC(SCCGWA, LNRICTL.P_CMP_TERM);
        }
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CMS") 
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("RCS") 
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("NCMS")) {
            WS_TERM_P = LNRICTL.P_CAL_TERM;
            CEP.TRC(SCCGWA, LNRICTL.P_CAL_TERM);
        }
        CEP.TRC(SCCGWA, WS_TERM_P);
        CEP.TRC(SCCGWA, WS_TEMP_VAL_DT);
    }
    public void B130_GET_FOR_ADD_LNTSCHT() throws IOException,SQLException,Exception {
        WS_INSERT_FLAGE = 'N';
        WS_BREAK_FLAGE = 'N';
        WS_TEMP_DUE_DT = LNCMTSCH.COMM_DATA.DUE_DT;
        B231_02_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        B222_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        while (LNCRSCHT.RETURN_INFO != 'E' 
            && WS_BREAK_FLAGE != 'Y') {
            if (LNRSCHT.DUE_DTE == LNCMTSCH.COMM_DATA.DUE_DT) {
                WS_CUR_TERM_NO = LNRSCHT.KEY.TERM;
                WS_NEXT_VAL_DT = LNRSCHT.DUE_DTE;
                B222_READNEXT_LNTSCHT();
                if (pgmRtn) return;
            } else {
                WS_INSERT_FLAGE = 'Y';
                WS_NEXT_TERM_NO = LNRSCHT.KEY.TERM;
                WS_NEXT_VAL_DT = LNRSCHT.VAL_DTE;
                WS_NEXT_DUE_DT = LNRSCHT.DUE_DTE;
                WS_BREAK_FLAGE = 'Y';
            }
        }
        B223_ENDBR_LNTSCHT();
        if (pgmRtn) return;
        if (WS_INSERT_FLAGE != 'Y') {
            B231_03_STARTBR_LNTSCHT();
            if (pgmRtn) return;
            B222_READNEXT_LNTSCHT();
            if (pgmRtn) return;
            if (LNCRSCHT.RETURN_INFO == 'F') {
                WS_NEXT_VAL_DT = LNRSCHT.DUE_DTE;
            }
            B223_ENDBR_LNTSCHT();
            if (pgmRtn) return;
        }
        if (WS_NEXT_VAL_DT == 0) {
            if (LNCMTSCH.COMM_DATA.LIST_TYP == K_INTEREST) {
                WS_TERM_NO = WS_TERM_I;
                R000_GET_LAST_DUE_DT_FROM_PLPI();
                if (pgmRtn) return;
            } else {
                WS_TERM_NO = WS_TERM_P;
                R000_GET_LAST_DUE_DT_FROM_PLPI();
                if (pgmRtn) return;
            }
        }
    }
    public void B130_GET_FOR_UPD_LNTSCHT() throws IOException,SQLException,Exception {
        WS_TEMP_DUE_DT = LNCMTSCH.COMM_DATA.VAL_DT + 1;
        B231_02_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        B222_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        WS_CUR_TERM_NO = LNRSCHT.KEY.TERM;
        WS_NEXT_VAL_DT = LNRSCHT.VAL_DTE;
        CEP.TRC(SCCGWA, LNRSCHT);
        B222_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        if (LNCRSCHT.RETURN_INFO == 'F') {
            WS_NEXT_TERM_NO = LNRSCHT.KEY.TERM;
            WS_NEXT_DUE_DT = LNRSCHT.DUE_DTE;
        }
        CEP.TRC(SCCGWA, LNRSCHT);
        B223_ENDBR_LNTSCHT();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        R000_GET_ALL_IN_RATE();
        if (pgmRtn) return;
        B215_GET_LNTBALL();
        if (pgmRtn) return;
        if (LNCMTSCH.COMM_DATA.FUNC == 'A') {
            B210_WRITE_LNTSCHT();
            if (pgmRtn) return;
        } else if (LNCMTSCH.COMM_DATA.FUNC == 'M') {
            B240_UPDATE_LNTSCHT();
            if (pgmRtn) return;
        } else if (LNCMTSCH.COMM_DATA.FUNC == 'D') {
            B220_DELETE_LNTSCHT();
            if (pgmRtn) return;
        } else {
        }
        CEP.TRC(SCCGWA, WS_TERM_P);
        CEP.TRC(SCCGWA, WS_TERM_I);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE != 3200 
            && SCCGWA.COMM_AREA.TR_ID.TR_CODE != 3100) {
            B230_REFRESH_LIST();
            if (pgmRtn) return;
        }
    }
    public void B210_WRITE_LNTSCHT() throws IOException,SQLException,Exception {
        B211_READUP_LNTSCHT();
        if (pgmRtn) return;
        if (LNCRSCHT.RETURN_INFO == 'N') {
            B213_ADD_LNTSCHT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5718;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B211_READUP_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'R';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = WS_CUR_TERM_NO;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B211_02_READUP_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'R';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = WS_NEXT_TERM_NO;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B211_03_READUP_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'R';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B212_UPD_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'U';
        if (LNRSCHT.ACTION == K_INQUIRY 
            || LNRSCHT.ACTION == K_DELETE) {
            LNRSCHT.ACTION = K_UPDATE;
        }
        if (LNRSCHT.ACTION == K_ADD 
            || LNRSCHT.ACTION == K_CANCEL) {
            LNRSCHT.ACTION = K_MODIFY;
        }
        if (LNCMTSCH.COMM_DATA.VAL_DT > 0) {
            LNRSCHT.VAL_DTE = LNCMTSCH.COMM_DATA.VAL_DT;
        }
        LNRSCHT.DUE_DTE = LNCMTSCH.COMM_DATA.DUE_DT;
        LNRSCHT.ALL_IN_RATE = LNCMTSCH.COMM_DATA.RATE;
        LNRSCHT.AMOUNT = LNCMTSCH.COMM_DATA.AMOUNT;
        LNRSCHT.REMARK = LNCMTSCH.COMM_DATA.REMARK;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_ACTION = 'M';
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
    }
    public void B212_02_UPD_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'U';
        if (LNRSCHT.ACTION == K_INQUIRY 
            || LNRSCHT.ACTION == K_DELETE) {
            LNRSCHT.ACTION = K_UPDATE;
        }
        if (LNRSCHT.ACTION == K_ADD 
            || LNRSCHT.ACTION == K_CANCEL) {
            LNRSCHT.ACTION = K_MODIFY;
        }
        LNRSCHT.VAL_DTE = LNCMTSCH.COMM_DATA.DUE_DT;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_ACTION = 'M';
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
    }
    public void B213_ADD_LNTSCHT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CUR_TERM_NO);
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'A';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = WS_CUR_TERM_NO;
        LNRSCHT.ACTION = K_ADD;
        LNRSCHT.VAL_DTE = LNCMTSCH.COMM_DATA.VAL_DT;
        LNRSCHT.DUE_DTE = LNCMTSCH.COMM_DATA.DUE_DT;
        LNRSCHT.ALL_IN_RATE = LNCMTSCH.COMM_DATA.RATE;
        CEP.TRC(SCCGWA, LNRSCHT.ALL_IN_RATE);
        LNRSCHT.AMOUNT = LNCMTSCH.COMM_DATA.AMOUNT;
        LNRSCHT.REMARK = LNCMTSCH.COMM_DATA.REMARK;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_ACTION = 'A';
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
    }
    public void B214_READ_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'I';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = WS_TERM_NO;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B214_01_READ_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'I';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = WS_TEMP_RPY_MTH;
        LNRSCHT.KEY.TERM = WS_TERM_NO;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B220_DELETE_LNTSCHT() throws IOException,SQLException,Exception {
        B221_STARTBR_LNTSCHT_UPDATE();
        if (pgmRtn) return;
        B222_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        while (LNCRSCHT.RETURN_INFO != 'E') {
            B224_DELETE_LNTSCHT();
            if (pgmRtn) return;
            B222_READNEXT_LNTSCHT();
            if (pgmRtn) return;
        }
        B223_ENDBR_LNTSCHT();
        if (pgmRtn) return;
    }
    public void B215_GET_LNTBALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALL);
        IBS.init(SCCGWA, LNCRBALL);
        LNRBALL.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNCRBALL.FUNC = 'I';
        LNCRBALL.REC_PTR = LNRBALL;
        LNCRBALL.REC_LEN = 98;
        S000_CALL_LNZRBALL();
        if (pgmRtn) return;
    }
    public void B221_STARTBR_LNTSCHT_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'K';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = LNCMTSCH.COMM_DATA.TERM;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B222_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'R';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B223_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'E';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B224_DELETE_LNTSCHT() throws IOException,SQLException,Exception {
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
        LNCRSCHT.FUNC = 'D';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B225_UPDATE_STATUS_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'U';
        if (LNRSCHT.ACTION == K_INQUIRY 
            || LNRSCHT.ACTION == K_UPDATE) {
            LNRSCHT.ACTION = K_DELETE;
        } else {
            LNRSCHT.ACTION = K_CANCEL;
        }
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_ACTION = 'M';
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
    }
    public void B225_UPDATE_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'U';
        if (LNRSCHT.ACTION == K_INQUIRY) {
            LNRSCHT.ACTION = K_UPDATE;
        }
        if (LNRSCHT.ACTION == K_ADD) {
            LNRSCHT.ACTION = K_MODIFY;
        }
        LNRSCHT.VAL_DTE = WS_TEMP_VAL_DT;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_TEMP_VAL_DT = LNRSCHT.DUE_DTE;
        WS_ACTION = 'M';
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
    }
    public void B230_REFRESH_LIST() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B231_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        B222_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        while (LNCRSCHT.RETURN_INFO != 'E') {
            R000_TRANS_DATA_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B222_READNEXT_LNTSCHT();
            if (pgmRtn) return;
        }
        B223_ENDBR_LNTSCHT();
        if (pgmRtn) return;
    }
    public void B231_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'A';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.REPY_TYP;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B231_02_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'G';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.DUE_DTE = WS_TEMP_DUE_DT;
        CEP.TRC(SCCGWA, WS_TEMP_DUE_DT);
        CEP.TRC(SCCGWA, LNRSCHT.KEY);
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B231_03_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'C';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.DUE_DTE = LNCMTSCH.COMM_DATA.DUE_DT;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B231_04_STARTBR_FIRST_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'D';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.DUE_DTE = LNCMTSCH.COMM_DATA.DUE_DT;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B240_UPDATE_LNTSCHT() throws IOException,SQLException,Exception {
        B211_READUP_LNTSCHT();
        if (pgmRtn) return;
        WS_SCHT_CRT_DATE = LNRSCHT.CRT_DATE;
        WS_SCHT_CRT_TELR = LNRSCHT.CRT_TELR;
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'U';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = WS_CUR_TERM_NO;
        LNRSCHT.ACTION = K_MODIFY;
        LNRSCHT.VAL_DTE = LNCMTSCH.COMM_DATA.VAL_DT;
        LNRSCHT.DUE_DTE = LNCMTSCH.COMM_DATA.DUE_DT;
        LNRSCHT.ALL_IN_RATE = LNCMTSCH.COMM_DATA.RATE;
        LNRSCHT.AMOUNT = LNCMTSCH.COMM_DATA.AMOUNT;
        LNRSCHT.REMARK = LNCMTSCH.COMM_DATA.REMARK;
        LNRSCHT.CRT_DATE = WS_SCHT_CRT_DATE;
        LNRSCHT.CRT_TELR = WS_SCHT_CRT_TELR;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        WS_ACTION = 'M';
        WS_MTSPB_TERM_NO = LNRSCHT.KEY.TERM;
        WS_MTSPB_VAL_DT = LNRSCHT.VAL_DTE;
        WS_MTSPB_DUE_DT = LNRSCHT.DUE_DTE;
    }
    public void S000_CALL_LNZRBALL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTBALL", LNCRBALL);
        if (LNCRBALL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRBALL.RC);
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = Short.parseShort(JIBS_tmp_str[0]);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCRBALL.RETURN_INFO);
        CEP.TRC(SCCGWA, LNCRBALL.RC);
    }
    public void R000_HANDLE_PART_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCMTSPB);
        LNCMTSPB.COMM_DATA.FUNC_CD = '1';
        LNCMTSPB.COMM_DATA.ACTION = WS_ACTION;
        LNCMTSPB.COMM_DATA.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNCMTSPB.COMM_DATA.CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNCMTSPB.COMM_DATA.SUB_CTA_NO = 0;
        LNCMTSPB.COMM_DATA.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNCMTSPB.COMM_DATA.TERM_NO = WS_MTSPB_TERM_NO;
        LNCMTSPB.COMM_DATA.VAL_DT = WS_MTSPB_VAL_DT;
        LNCMTSPB.COMM_DATA.DUE_DT = WS_MTSPB_DUE_DT;
        S000_CALL_LNZMTSPB();
        if (pgmRtn) return;
    }
    public void R000_GET_ALL_IN_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATQ);
        LNCRATQ.COMM_DATA.LN_AC = LNCMTSCH.COMM_DATA.CTA_NO;
        LNCRATQ.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCRATQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCRATQ.COMM_DATA.SUF_NO = "0" + LNCRATQ.COMM_DATA.SUF_NO;
        LNCRATQ.COMM_DATA.RATE_TYPE = 'N';
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = LNCMTSCH.COMM_DATA.DUE_DT;
        SCCCLDT.DAYS = -1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        LNCRATQ.COMM_DATA.VAL_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.VAL_DATE);
        if (LNCRATQ.COMM_DATA.VAL_DATE > 0) {
            S000_CALL_FUNC_LNZRATQ();
            if (pgmRtn) return;
            LNCMTSCH.COMM_DATA.RATE = LNCRATQ.COMM_DATA.RATE;
        }
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        R000_TRANSFER_OUTPUT_DATA();
        if (pgmRtn) return;
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_LIST);
        SCCMPAG.DATA_LEN = 169;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_REPY_MTH);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_TERM_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_VAL_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_DUE_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_ALL_IN_RAT);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_REPY_AMT);
        CEP.TRC(SCCGWA, WS_OUTPUT_LIST.WS_LST_RMK);
    }
    public void R000_TRANSFER_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_LIST);
        WS_OUTPUT_LIST.WS_LST_REPY_MTH = LNRSCHT.KEY.TYPE;
        WS_OUTPUT_LIST.WS_LST_TERM_NO = LNRSCHT.KEY.TERM;
        if (LNRSCHT.KEY.TYPE == K_PRINCIPAL 
            && LNRSCHT.KEY.CONTRACT_NO == 0) {
            WS_OUTPUT_LIST.WS_LST_TERM_NO = WS_TERM_P;
            WS_TERM_P = (short) (WS_TERM_P + 1);
        }
        if (LNRSCHT.KEY.TYPE == K_INTEREST 
            && LNRSCHT.KEY.CONTRACT_NO == 0) {
            WS_OUTPUT_LIST.WS_LST_TERM_NO = WS_TERM_I;
            WS_TERM_I = (short) (WS_TERM_I + 1);
        }
        WS_OUTPUT_LIST.WS_LST_VAL_DT = LNRSCHT.VAL_DTE;
        WS_OUTPUT_LIST.WS_LST_DUE_DT = LNRSCHT.DUE_DTE;
        WS_OUTPUT_LIST.WS_LST_ALL_IN_RAT = LNRSCHT.ALL_IN_RATE;
        WS_OUTPUT_LIST.WS_LST_REPY_AMT = LNRSCHT.AMOUNT;
        WS_OUTPUT_LIST.WS_LST_RMK = LNRSCHT.REMARK;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 169;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        LNCOSCH1.DATA.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, LNCOSCH1.DATA);
        SCCMPAG.DATA_LEN = 13;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_LAST_DUE_DT_FROM_PLPI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM_NO);
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'I';
        LNRPLPI.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = LNCMTSCH.COMM_DATA.LIST_TYP;
        WS_TERM_NO = (short) (WS_TERM_NO - 1);
        LNRPLPI.KEY.TERM = WS_TERM_NO;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
        if (LNCRPLPI.RETURN_INFO == 'N') {
            WS_NEXT_VAL_DT = LNRCONT.START_DATE;
            CEP.TRC(SCCGWA, "NOT FOUND SCH IN LNTPLPI");
        } else {
            WS_NEXT_VAL_DT = LNRPLPI.DUE_DT;
        }
    }
    public void R000_GET_P_DUE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'I';
        LNRPLPI.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.KEY.TERM = LNRICTL.P_CUR_TERM;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
        if (LNCRPLPI.RETURN_INFO == 'N') {
            WS_LAST_P_DUE_DT = LNRCONT.START_DATE;
            CEP.TRC(SCCGWA, "NOT FOUND SCH IN LNTPLPI");
        } else {
            WS_LAST_P_DUE_DT = LNRPLPI.VAL_DT;
        }
        CEP.TRC(SCCGWA, WS_LAST_P_DUE_DT);
    }
    public void R000_CAL_DATE1_DATE2_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        R000_CAL_DAYS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CLDT_DAYS);
        WS_I = 2;
        WS_BALL_LOAN_BAL = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[WS_I-1].LOAN_BAL;
        CEP.TRC(SCCGWA, LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[WS_I-1].LOAN_BAL);
        CEP.TRC(SCCGWA, WS_BALL_LOAN_BAL);
        CEP.TRC(SCCGWA, WS_CLDT_DAYS);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_INT_BAS);
        if (LNCMTSCH.COMM_DATA.LIST_TYP == K_INTEREST) {
            WS_I_SCH_AMOUNT = ( WS_BALL_LOAN_BAL * WS_CLDT_DAYS * LNCMTSCH.COMM_DATA.RATE ) / ( WS_INT_BAS * 100 );
        }
    }
    public void R000_CAL_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, WS_CLDT_DATE1);
        CEP.TRC(SCCGWA, WS_CLDT_DATE2);
        SCCCLDT.DATE1 = WS_CLDT_DATE1;
        SCCCLDT.DATE2 = WS_CLDT_DATE2;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_CLDT_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, WS_CLDT_DAYS);
        if (WS_CLDT_DAYS < 0) {
            WS_CLDT_DAYS = 0;
            CEP.TRC(SCCGWA, WS_CLDT_DAYS);
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_LNTAPRD_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_LNTAPRD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAPRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAGRE_BY_CONT_NO() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCMTSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCMTSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1235;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        if (LNCRCMMT.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCOCLWD.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_ICTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        S000_CALL_SRC_LNZRICTL();
        if (pgmRtn) return;
    }
    public void R000_GET_LOAN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        IBS.init(SCCGWA, LNCRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        S000_CALL_SRC_LNZRLOAN();
        if (pgmRtn) return;
    }
    public void R000_GET_APRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        if (WS_READ_LNTAPRD_FLG == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_SCHT_VAL_DTE() throws IOException,SQLException,Exception {
        if (LNCMTSCH.COMM_DATA.TERM == 1) {
            LNCMTSCH.COMM_DATA.VAL_DT = LNRCONT.START_DATE;
        } else {
            IBS.init(SCCGWA, LNCRSCHT);
            IBS.init(SCCGWA, LNRSCHT);
            LNCRSCHT.FUNC = 'I';
            LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
            LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
            LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
            LNRSCHT.KEY.TERM = LNCMTSCH.COMM_DATA.TERM;
            LNCRSCHT.REC_PTR = LNRSCHT;
            LNCRSCHT.REC_LEN = 356;
            S000_CALL_SRC_LNZRSCHT();
            if (pgmRtn) return;
            LNCMTSCH.COMM_DATA.VAL_DT = LNRSCHT.VAL_DTE;
        }
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.LIST_TYP);
        CEP.TRC(SCCGWA, WS_LAST_TERM_NO);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.VAL_DT);
    }
    public void R000_GET_SCHT_DUE_DTE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'I';
        LNRSCHT.KEY.SUB_CTA_NO = LNCMTSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSCH.COMM_DATA.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = LNCMTSCH.COMM_DATA.LIST_TYP;
        LNRSCHT.KEY.TERM = LNCMTSCH.COMM_DATA.TERM;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
        LNCMTSCH.COMM_DATA.DUE_DT = LNRSCHT.DUE_DTE;
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.LIST_TYP);
        CEP.TRC(SCCGWA, LNCMTSCH.COMM_DATA.TERM);
    }
    public void S000_CALL_LNZMTSPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-MAINTAIN-SCH", LNCMTSPB);
    }
    public void S000_CALL_FUNC_LNZRATQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATH-INQ", LNCRATQ);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = "SC";
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCCLDT.RC;
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
        if (LNCRSCHT.RC.RC_CODE != 0) {
            if (LNCMTSCH.COMM_DATA.FUNC == 'A') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRSCHT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        if (LNCRLOAN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            if (LNCRPLPI.RETURN_INFO == 'N' 
                || LNCRPLPI.RETURN_INFO == 'E') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCMTSCH.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCMTSCH=");
            CEP.TRC(SCCGWA, LNCMTSCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
