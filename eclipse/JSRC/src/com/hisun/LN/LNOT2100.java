package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2100 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAGRE_RD;
    boolean pgmRtn = false;
    char K_OUR_BANK = '0';
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_OUTPUT_FMT = "LN210";
    char K_RATE_N = 'N';
    char K_RATE_O = 'O';
    char K_RATE_L = 'L';
    char K_RATE_P = 'P';
    char K_CKPD_INQ = '0';
    LNOT2100_WS_ERR_MSG WS_ERR_MSG = new LNOT2100_WS_ERR_MSG();
    char WS_REPY_MTH = ' ';
    char WS_PAYI_PER = ' ';
    char WS_PYP_CIRC = ' ';
    short WS_I = 0;
    short WS_II = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    char WS_BANK_TYPE = ' ';
    String WS_SETL_AC = " ";
    String WS_SETL_AC_TYPE = " ";
    LNOT2100_WS_IA_AC WS_IA_AC = new LNOT2100_WS_IA_AC();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    CICCUST CICCUST = new CICCUST();
    LNCFFSDT LNCFFSDT = new LNCFFSDT();
    AICPQIA AICPQIA = new AICPQIA();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    AICPQITM AICPQITM = new AICPQITM();
    LNCSSYND LNCSSYND = new LNCSSYND();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICQACRI CICQACRI = new CICQACRI();
    LNRAGRE LNRAGRE = new LNRAGRE();
    SCCGWA SCCGWA;
    LNB2100_AWA_2100 LNB2100_AWA_2100;
    LNCFRATE LNCFRATE;
    public void MP(SCCGWA SCCGWA, LNCFRATE LNCFRATE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCFRATE = LNCFRATE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT2100 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2100_AWA_2100>");
        LNB2100_AWA_2100 = (LNB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B100_CHECK_INPUT_DATA();
            if (pgmRtn) return;
        }
        B200_MAKE_LOAN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNB2100_AWA_2100.DRAW_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_CMMT_MUST_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.DRAW_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DRAW_NO_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.PAPER_NO = LNB2100_AWA_2100.PAPER_NO;
        LNRAGRE.DRAW_NO = LNB2100_AWA_2100.DRAW_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.BOOK_BR == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.DOMI_BR == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CINO_MUST_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CI_NO = LNB2100_AWA_2100.CI_NO;
        LNCSSTBL.S_CODE = "2100";
        S000_CALL_LNZSSTBL();
        if (pgmRtn) return;
        R000_CHECK_PROD_INF();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.START_DT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_START_DT_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.VAL_DT_LARGE_AC_DT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.MATU_DT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_MATU_DT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.START_DT >= LNB2100_AWA_2100.MATU_DT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EPDT_GRT_STDT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_CCY, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNB2100_AWA_2100.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.CONT_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_M_GREATER_THAN_Z, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_J = 1; WS_J <= 5; WS_J += 1) {
            if (LNB2100_AWA_2100.FEE_INF[WS_J-1].FEE_CODE.trim().length() > 0 
                || LNB2100_AWA_2100.FEE_INF[WS_J-1].FEE_AMT != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_YT_CANNOT_FEE, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2100_AWA_2100.FEE_ACT.trim().length() > 0 
            || LNB2100_AWA_2100.FEE_AC.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_YT_CANNOT_FEE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.REPY_MTH == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_REP_MTH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_REPY_MTH = LNB2100_AWA_2100.REPY_MTH;
        if ((WS_REPY_MTH != '0' 
            && WS_REPY_MTH != '1' 
            && WS_REPY_MTH != '2' 
            && WS_REPY_MTH != '3' 
            && WS_REPY_MTH != '4' 
            && WS_REPY_MTH != '5' 
            && WS_REPY_MTH != '6')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_MTH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_REPY_MTH == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CANNOT_B_LOAN, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_REPY_MTH == '4' 
            && LNB2100_AWA_2100.PHS_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PHS_FLG_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.PHS_FLG != ' ' 
            && LNB2100_AWA_2100.PHS_FLG != 'Y' 
            && LNB2100_AWA_2100.PHS_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PHS_FLG_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.PHS_FLG == 'Y' 
            && (LNB2100_AWA_2100.PHS_NUM < 1 
            || LNB2100_AWA_2100.PHS_NUM > 5)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PHS_NUM_MUST_1TO5, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.INST_MTH != ' ' 
            && LNB2100_AWA_2100.INST_MTH != '1' 
            && LNB2100_AWA_2100.INST_MTH != '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INST_MTH_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.PHS_FLG != 'Y' 
            && LNB2100_AWA_2100.SP_LNFLG != ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SP_LNFLG_CANNOT_I, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.SP_LNFLG != ' ' 
            && LNB2100_AWA_2100.SP_LNFLG != '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SP_LNFLG_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_CHECK_PERD_INF();
        if (pgmRtn) return;
        R000_CHECK_AC_INF();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.ATO_DFLG == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ATO_DFLG_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.ATO_DFLG != 'Y' 
            && LNB2100_AWA_2100.ATO_DFLG != 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ATO_DFLG_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.GRA_TYP != ' ') {
            if (LNB2100_AWA_2100.GRA_TYP != '2') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_GRA_TYP_VAL_ERR, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.GRA_TYP == '2' 
                && LNB2100_AWA_2100.GRA_DAYA == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_GRA_DAYA_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.P_GRA_MT == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_P_GRA_MT_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.C_GRA_MT == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_C_GRA_MT_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.P_GRA_MT != '1' 
                && LNB2100_AWA_2100.P_GRA_MT != '2' 
                && LNB2100_AWA_2100.P_GRA_MT != '3' 
                && LNB2100_AWA_2100.P_GRA_MT != '4') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GRA_I_T_VAL, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.C_GRA_MT != '1' 
                && LNB2100_AWA_2100.C_GRA_MT != '2' 
                && LNB2100_AWA_2100.C_GRA_MT != '3' 
                && LNB2100_AWA_2100.C_GRA_MT != '4') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GRA_I_T_VAL, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2100_AWA_2100.INT_D_BA.trim().length() == 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = LNB2100_AWA_2100.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            LNB2100_AWA_2100.INT_D_BA = BPCQCCY.DATA.CALR_STD;
        }
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.INT_D_BA);
        if (!LNB2100_AWA_2100.INT_D_BA.equalsIgnoreCase("01") 
            && !LNB2100_AWA_2100.INT_D_BA.equalsIgnoreCase("02") 
            && !LNB2100_AWA_2100.INT_D_BA.equalsIgnoreCase("03") 
            && !LNB2100_AWA_2100.INT_D_BA.equalsIgnoreCase("04") 
            && !LNB2100_AWA_2100.INT_D_BA.equalsIgnoreCase("05")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_D_BA, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_CAL_LOAN_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCFRATE);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        JIBS_tmp_str[0] = "" + K_RATE_N;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        LNCFRATE.COMM_DATA.CALF_CD = JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(1);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        JIBS_tmp_str[0] = "" + K_RATE_O;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        LNCFRATE.COMM_DATA.CALF_CD = LNCFRATE.COMM_DATA.CALF_CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(2 + 1 - 1);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        JIBS_tmp_str[0] = "" + K_RATE_L;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        LNCFRATE.COMM_DATA.CALF_CD = LNCFRATE.COMM_DATA.CALF_CD.substring(0, 3 - 1) + JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(3 + 1 - 1);
        if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
        JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
        JIBS_tmp_str[0] = "" + K_RATE_P;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        LNCFRATE.COMM_DATA.CALF_CD = LNCFRATE.COMM_DATA.CALF_CD.substring(0, 4 - 1) + JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(4 + 1 - 1);
        LNCFRATE.COMM_DATA.BOOK_BR = LNB2100_AWA_2100.BOOK_BR;
        LNCFRATE.COMM_DATA.BOOK_CCY = LNB2100_AWA_2100.CCY;
        LNCFRATE.COMM_DATA.RAT_TYP = LNB2100_AWA_2100.RAT_TYP;
        LNCFRATE.COMM_DATA.RAT_PD = LNB2100_AWA_2100.RAT_PD;
        LNCFRATE.COMM_DATA.RAT_VAR = LNB2100_AWA_2100.RAT_VAR;
        LNCFRATE.COMM_DATA.RAT_PCT = LNB2100_AWA_2100.RAT_PCT;
        LNCFRATE.COMM_DATA.RAT_INT = LNB2100_AWA_2100.RAT_INT;
        LNCFRATE.COMM_DATA.IN_RATE = LNB2100_AWA_2100.IN_RATE;
        LNCFRATE.COMM_DATA.PEN_RRAT = LNB2100_AWA_2100.PEN_RRAT;
        LNCFRATE.COMM_DATA.PEN_TYP = LNB2100_AWA_2100.PEN_TYP;
        LNCFRATE.COMM_DATA.PEN_RATT = LNB2100_AWA_2100.PEN_RATT;
        LNCFRATE.COMM_DATA.PEN_RATC = LNB2100_AWA_2100.PEN_RATC;
        LNCFRATE.COMM_DATA.PEN_SPR = LNB2100_AWA_2100.PEN_SPR;
        LNCFRATE.COMM_DATA.PEN_PCT = LNB2100_AWA_2100.PEN_PCT;
        LNCFRATE.COMM_DATA.PEN_IRAT = LNB2100_AWA_2100.PEN_IRAT;
        LNCFRATE.COMM_DATA.INT_MTH = LNB2100_AWA_2100.INT_MTH;
        LNCFRATE.COMM_DATA.CPND_USE = LNB2100_AWA_2100.CPND_USE;
        LNCFRATE.COMM_DATA.CPND_RTY = LNB2100_AWA_2100.CPND_RTY;
        LNCFRATE.COMM_DATA.CPND_TYP = LNB2100_AWA_2100.CPND_TYP;
        LNCFRATE.COMM_DATA.CPNDRATT = LNB2100_AWA_2100.CPNDRATT;
        LNCFRATE.COMM_DATA.CPNDRATC = LNB2100_AWA_2100.CPNDRATC;
        LNCFRATE.COMM_DATA.CPND_SPR = LNB2100_AWA_2100.CPND_SPR;
        LNCFRATE.COMM_DATA.CPND_PCT = LNB2100_AWA_2100.CPND_PCT;
        LNCFRATE.COMM_DATA.CPNDRATE = LNB2100_AWA_2100.CPNDRATE;
        LNCFRATE.COMM_DATA.ABUS_RAT = LNB2100_AWA_2100.ABUS_RAT;
        LNCFRATE.COMM_DATA.ABUSRATT = "" + LNB2100_AWA_2100.ABUSRATT;
        JIBS_tmp_int = LNCFRATE.COMM_DATA.ABUSRATT.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.ABUSRATT = "0" + LNCFRATE.COMM_DATA.ABUSRATT;
        LNCFRATE.COMM_DATA.ABUS_TYP = LNB2100_AWA_2100.ABUS_TYP.charAt(0);
        LNCFRATE.COMM_DATA.ABUSRATC = LNB2100_AWA_2100.ABUSRATC;
        LNCFRATE.COMM_DATA.ABUSSPR = LNB2100_AWA_2100.ABUSSPR;
        LNCFRATE.COMM_DATA.ABUSPCT = LNB2100_AWA_2100.ABUSPCT;
        LNCFRATE.COMM_DATA.ABUSIRAT = LNB2100_AWA_2100.ABUSIRAT;
        LNB2100_AWA_2100.IN_RATE = LNCFRATE.COMM_DATA.IN_RATE;
        LNB2100_AWA_2100.PEN_IRAT = LNCFRATE.COMM_DATA.PEN_IRAT;
        LNB2100_AWA_2100.CPNDRATE = LNCFRATE.COMM_DATA.CPNDRATE;
        LNB2100_AWA_2100.ABUSIRAT = LNCFRATE.COMM_DATA.ABUSIRAT;
    }
    public void B200_MAKE_LOAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSYND);
        LNCSSYND.DATA.PAPER_NO = LNB2100_AWA_2100.PAPER_NO;
        LNCSSYND.DATA.DRAW_NO = LNB2100_AWA_2100.DRAW_NO;
        LNCSSYND.DATA.BOOK_BR = LNB2100_AWA_2100.BOOK_BR;
        LNCSSYND.DATA.DOMI_BR = LNB2100_AWA_2100.DOMI_BR;
        LNCSSYND.DATA.CI_NO = LNB2100_AWA_2100.CI_NO;
        LNCSSYND.DATA.PROD_TYP = LNB2100_AWA_2100.PROD_TYP;
        LNCSSYND.DATA.START_DT = LNB2100_AWA_2100.START_DT;
        LNCSSYND.DATA.MATU_DT = LNB2100_AWA_2100.MATU_DT;
        LNCSSYND.DATA.CCY_TYP = LNB2100_AWA_2100.CCY_TYP;
        LNCSSYND.DATA.CCY = LNB2100_AWA_2100.CCY;
        LNCSSYND.DATA.CONT_AMT = LNB2100_AWA_2100.CONT_AMT;
        LNCSSYND.DATA.RAT_MTH = LNB2100_AWA_2100.RAT_MTH;
        LNCSSYND.DATA.FLPD_UN = LNB2100_AWA_2100.FLPD_UN;
        LNCSSYND.DATA.INT_FLPD = LNB2100_AWA_2100.INT_FLPD;
        LNCSSYND.DATA.FLT_IPER = LNB2100_AWA_2100.FLT_IPER;
        LNCSSYND.DATA.FLT_ADJF = LNB2100_AWA_2100.FLT_ADJF;
        LNCSSYND.DATA.FLT_FLG = LNB2100_AWA_2100.FLT_FLG;
        LNCSSYND.DATA.FLT_DAY = LNB2100_AWA_2100.FLT_DAY;
        LNCSSYND.DATA.FST_DAY = LNB2100_AWA_2100.FST_DAY;
        LNCSSYND.DATA.FLT_MTH = LNB2100_AWA_2100.FLT_MTH;
        LNCSSYND.DATA.RAT_TYP = LNB2100_AWA_2100.RAT_TYP;
        LNCSSYND.DATA.RAT_PD = LNB2100_AWA_2100.RAT_PD;
        LNCSSYND.DATA.RAT_VAR = LNB2100_AWA_2100.RAT_VAR;
        LNCSSYND.DATA.RAT_PCT = LNB2100_AWA_2100.RAT_PCT;
        LNCSSYND.DATA.RAT_INT = LNB2100_AWA_2100.RAT_INT;
        LNCSSYND.DATA.IN_RATE = LNB2100_AWA_2100.IN_RATE;
        LNCSSYND.DATA.PEN_RRAT = LNB2100_AWA_2100.PEN_RRAT;
        LNCSSYND.DATA.PEN_TYP = LNB2100_AWA_2100.PEN_TYP;
        LNCSSYND.DATA.PEN_RATT = LNB2100_AWA_2100.PEN_RATT;
        LNCSSYND.DATA.PEN_RATC = LNB2100_AWA_2100.PEN_RATC;
        LNCSSYND.DATA.PEN_SPR = LNB2100_AWA_2100.PEN_SPR;
        LNCSSYND.DATA.PEN_PCT = LNB2100_AWA_2100.PEN_PCT;
        LNCSSYND.DATA.PEN_IRAT = LNB2100_AWA_2100.PEN_IRAT;
        LNB2100_AWA_2100.INT_MTH = 'Y';
        LNCSSYND.DATA.INT_MTH = LNB2100_AWA_2100.INT_MTH;
        LNCSSYND.DATA.CPND_USE = LNB2100_AWA_2100.CPND_USE;
        LNCSSYND.DATA.CPND_RTY = LNB2100_AWA_2100.CPND_RTY;
        LNCSSYND.DATA.CPND_TYP = LNB2100_AWA_2100.CPND_TYP;
        LNCSSYND.DATA.CPNDRATT = LNB2100_AWA_2100.CPNDRATT;
        LNCSSYND.DATA.CPNDRATC = LNB2100_AWA_2100.CPNDRATC;
        LNCSSYND.DATA.CPND_SPR = LNB2100_AWA_2100.CPND_SPR;
        LNCSSYND.DATA.CPND_PCT = LNB2100_AWA_2100.CPND_PCT;
        LNCSSYND.DATA.CPNDRATE = LNB2100_AWA_2100.CPNDRATE;
        LNCSSYND.DATA.ABUS_RAT = LNB2100_AWA_2100.ABUS_RAT;
        LNCSSYND.DATA.ABUSRATT = LNB2100_AWA_2100.ABUSRATT;
        LNCSSYND.DATA.ABUS_TYP = LNB2100_AWA_2100.ABUS_TYP;
        LNCSSYND.DATA.ABUSRATC = LNB2100_AWA_2100.ABUSRATC;
        LNCSSYND.DATA.ABUSSPR = LNB2100_AWA_2100.ABUSSPR;
        LNCSSYND.DATA.ABUSPCT = LNB2100_AWA_2100.ABUSPCT;
        LNCSSYND.DATA.ABUSIRAT = LNB2100_AWA_2100.ABUSIRAT;
        LNCSSYND.DATA.REPY_MTH = LNB2100_AWA_2100.REPY_MTH;
        LNCSSYND.DATA.INST_MTH = LNB2100_AWA_2100.INST_MTH;
        LNCSSYND.DATA.PAYI_PER = LNB2100_AWA_2100.PAYI_PER;
        LNCSSYND.DATA.CAL_PERU = LNB2100_AWA_2100.CAL_PERU;
        LNCSSYND.DATA.CAL_PERD = LNB2100_AWA_2100.CAL_PERD;
        LNCSSYND.DATA.PAY_DTYP = LNB2100_AWA_2100.PAY_DTYP;
        LNCSSYND.DATA.CAL_DAY = LNB2100_AWA_2100.CAL_DAY;
        LNCSSYND.DATA.CAL_FSDT = LNB2100_AWA_2100.CAL_FSDT;
        LNCSSYND.DATA.PYP_CIRC = LNB2100_AWA_2100.PYP_CIRC;
        LNCSSYND.DATA.PYP_PERU = LNB2100_AWA_2100.PYP_PERU;
        LNCSSYND.DATA.PYP_PERD = LNB2100_AWA_2100.PYP_PERD;
        LNCSSYND.DATA.SP_LNFLG = LNB2100_AWA_2100.SP_LNFLG;
        LNCSSYND.DATA.PHS_FLG = LNB2100_AWA_2100.PHS_FLG;
        LNCSSYND.DATA.PHS_NUM = LNB2100_AWA_2100.PHS_NUM;
        for (WS_IDX = 1; WS_IDX <= LNB2100_AWA_2100.PHS_NUM; WS_IDX += 1) {
            LNCSSYND.DATA.PHS_INF[WS_IDX-1].PHS_TTRM = LNB2100_AWA_2100.PHS_INF[WS_IDX-1].PHS_TTRM;
            LNCSSYND.DATA.PHS_INF[WS_IDX-1].PHS_PERU = LNB2100_AWA_2100.PHS_INF[WS_IDX-1].PHS_PERU;
            LNCSSYND.DATA.PHS_INF[WS_IDX-1].PHS_PERD = LNB2100_AWA_2100.PHS_INF[WS_IDX-1].PHS_PERD;
            LNCSSYND.DATA.PHS_INF[WS_IDX-1].PHS_PAMT = LNB2100_AWA_2100.PHS_INF[WS_IDX-1].PHS_PAMT;
            LNCSSYND.DATA.PHS_INF[WS_IDX-1].PHS_ISMT = LNB2100_AWA_2100.PHS_INF[WS_IDX-1].PHS_ISMT;
        }
        LNCSSYND.DATA.OLC_PERU = LNB2100_AWA_2100.OLC_PERU;
        LNCSSYND.DATA.OLC_PERD = LNB2100_AWA_2100.OLC_PERD;
        LNCSSYND.DATA.DRAW_ACT = LNB2100_AWA_2100.DRAW_ACT;
        LNCSSYND.DATA.DRAW_AC = LNB2100_AWA_2100.DRAW_AC;
        LNCSSYND.DATA.DW_BK_TP = LNB2100_AWA_2100.DW_BK_TP;
        LNCSSYND.DATA.DRAW_SEQ = LNB2100_AWA_2100.DRAW_SEQ;
        LNCSSYND.DATA.PAY_AC_T = LNB2100_AWA_2100.PAY_AC_T;
        LNCSSYND.DATA.PAY_AC = LNB2100_AWA_2100.PAY_AC;
        LNCSSYND.DATA.PA_BK_TP = LNB2100_AWA_2100.PA_BK_TP;
        LNCSSYND.DATA.FS_CLMTH = LNB2100_AWA_2100.FS_CLMTH;
        LNCSSYND.DATA.FS_CLAMT = LNB2100_AWA_2100.FS_CLAMT;
        LNCSSYND.DATA.PAYI_ACT = LNB2100_AWA_2100.PAYI_ACT;
        LNCSSYND.DATA.PAYI_AC = LNB2100_AWA_2100.PAYI_AC;
        LNCSSYND.DATA.PAYI_SEQ = LNB2100_AWA_2100.PAYI_SEQ;
        LNCSSYND.DATA.ATO_DFLG = LNB2100_AWA_2100.ATO_DFLG;
        LNCSSYND.DATA.ATO_AMT = LNB2100_AWA_2100.ATO_AMT;
        LNCSSYND.DATA.ATO_ACT = LNB2100_AWA_2100.ATO_ACT;
        LNCSSYND.DATA.ATO_AC = LNB2100_AWA_2100.ATO_AC;
        LNCSSYND.DATA.GRA_TYP = LNB2100_AWA_2100.GRA_TYP;
        LNCSSYND.DATA.GRA_DAYA = LNB2100_AWA_2100.GRA_DAYA;
        LNCSSYND.DATA.P_GRA_MT = LNB2100_AWA_2100.P_GRA_MT;
        LNCSSYND.DATA.C_GRA_MT = LNB2100_AWA_2100.C_GRA_MT;
        LNCSSYND.DATA.INT_D_BA = LNB2100_AWA_2100.INT_D_BA;
        LNCSSYND.DATA.PSEQ_CD = LNB2100_AWA_2100.PSEQ_CD;
        LNCSSYND.DATA.PREP_CHR = LNB2100_AWA_2100.PREP_CHR;
        LNCSSYND.DATA.HAND_CHR = LNB2100_AWA_2100.HAND_CHR;
        LNCSSYND.DATA.HAND_CHM = LNB2100_AWA_2100.HAND_CHM;
        LNCSSYND.DATA.GUAPREF = LNB2100_AWA_2100.GUAPREF;
        LNCSSYND.DATA.GUARPSEQ = LNB2100_AWA_2100.GUARPSEQ;
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            if (LNB2100_AWA_2100.FEE_INF[WS_IDX-1].FEE_CODE.trim().length() > 0) {
                LNCSSYND.DATA.FEE_INF[WS_IDX-1].FEE_CODE = LNB2100_AWA_2100.FEE_INF[WS_IDX-1].FEE_CODE;
                LNCSSYND.DATA.FEE_INF[WS_IDX-1].FEE_AMT = LNB2100_AWA_2100.FEE_INF[WS_IDX-1].FEE_AMT;
            }
        }
        LNCSSYND.DATA.FEE_ACT = LNB2100_AWA_2100.FEE_ACT;
        LNCSSYND.DATA.FEE_AC = LNB2100_AWA_2100.FEE_AC;
        LNCSSYND.DATA.TR_MMO = LNB2100_AWA_2100.TR_MMO;
        LNCSSYND.DATA.REMARK = LNB2100_AWA_2100.REMARK;
        LNCSSYND.DATA.CUSTM1 = LNB2100_AWA_2100.CUSTM1;
        LNCSSYND.DATA.CUSTM2 = LNB2100_AWA_2100.CUSTM2;
        LNCSSYND.DATA.CUSTM3 = LNB2100_AWA_2100.CUSTM3;
        LNCSSYND.DATA.BAR_CD = LNB2100_AWA_2100.BAR_CD;
        LNCSSYND.DATA.UNIT_CD = LNB2100_AWA_2100.UNIT_CD;
        LNCSSYND.DATA.SYN_TYP = LNB2100_AWA_2100.SYN_TYP;
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[1-1].PART_NO);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[2-1].PART_NO);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[3-1].PART_NO);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[4-1].PART_NO);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[1-1].PART_PCT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[2-1].PART_PCT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[3-1].PART_PCT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[4-1].PART_PCT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[1-1].PART_AMT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[2-1].PART_AMT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[3-1].PART_AMT);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PART_INF[4-1].PART_AMT);
        for (WS_IDX = 1; WS_IDX <= 10; WS_IDX += 1) {
            if (LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_NO != 0) {
                LNCSSYND.DATA.PART_INF[WS_IDX-1].PART_NO = LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_NO;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].BR = LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_BR;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].PART_NM = LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_NM;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].LOC_BANK = LNB2100_AWA_2100.PART_INF[WS_IDX-1].LOC_BANK;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].ADJ_FLG = LNB2100_AWA_2100.PART_INF[WS_IDX-1].ADJ_BANK;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].REL_TYPE = LNB2100_AWA_2100.PART_INF[WS_IDX-1].REL_TYPE;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].YHS_FLG = LNB2100_AWA_2100.PART_INF[WS_IDX-1].YHS_FLG;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].YHS_AC = LNB2100_AWA_2100.PART_INF[WS_IDX-1].YHS_AC;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].PART_PCT = LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_PCT;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].PART_AMT = LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_AMT;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].PART_ACT = LNB2100_AWA_2100.PART_INF[WS_IDX-1].P_AC_TYP;
                LNCSSYND.DATA.PART_INF[WS_IDX-1].PART_AC = LNB2100_AWA_2100.PART_INF[WS_IDX-1].PART_AC;
            }
        }
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[1-1].PART_NO);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[2-1].PART_NO);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[3-1].PART_NO);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[4-1].PART_NO);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[1-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[2-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[3-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[4-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[1-1].PART_AMT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[2-1].PART_AMT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[3-1].PART_AMT);
        CEP.TRC(SCCGWA, LNCSSYND.DATA.PART_INF[4-1].PART_AMT);
        S000_CALL_LNZSSYND();
        if (pgmRtn) return;
        LNB2100_AWA_2100.CONT_NO = LNCSSYND.DATA.CONT_NO;
    }
    public void B110_GET_AC_INFO() throws IOException,SQLException,Exception {
        if (WS_BANK_TYPE == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_SETL_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                WS_SETL_AC_TYPE = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_SETL_AC_TYPE = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_SETL_AC_TYPE = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_SETL_AC_TYPE = K_IA_AC;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BK_TP_M_OUR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_PROD_INF() throws IOException,SQLException,Exception {
        if (LNB2100_AWA_2100.PROD_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNB2100_AWA_2100.PROD_TYP;
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PROD_TYP);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.SYN_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SYN_TYP_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.SYN_TYP != 'I' 
            && LNB2100_AWA_2100.SYN_TYP != 'O') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SYN_TYP_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = LNB2100_AWA_2100.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSCKPD.PROD_MOD);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if ((CICCUST.O_DATA.O_CI_TYP == '1' 
            && LNCSCKPD.PROD_MOD != 'R') 
            || (CICCUST.O_DATA.O_CI_TYP == '2' 
            && LNCSCKPD.PROD_MOD != 'C') 
            || (CICCUST.O_DATA.O_CI_TYP == '3' 
            && LNCSCKPD.PROD_MOD != 'G')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PRODMOD_NOTEQ_CITYPE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_PERD_INF() throws IOException,SQLException,Exception {
        if (LNB2100_AWA_2100.PAYI_PER == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAYI_PER_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PAYI_PER = LNB2100_AWA_2100.PAYI_PER;
        if ((WS_PAYI_PER != '1' 
            && WS_PAYI_PER != '2' 
            && WS_PAYI_PER != '3' 
            && WS_PAYI_PER != '4' 
            && WS_PAYI_PER != '5' 
            && WS_PAYI_PER != '6' 
            && WS_PAYI_PER != '7' 
            && WS_PAYI_PER != '8')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAYI_PER_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_REPY_MTH == '4') {
            LNB2100_AWA_2100.PYP_CIRC = WS_PAYI_PER;
        }
        if ((WS_REPY_MTH == '0' 
            || WS_REPY_MTH == '1') 
            && WS_PAYI_PER != '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAYI_PER_MUST_ONCE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_PAYI_PER == '8' 
            && (LNB2100_AWA_2100.CAL_PERU == ' ' 
            || LNB2100_AWA_2100.CAL_PERD == 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_PERD_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.CAL_PERU);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.CAL_PERD);
        if (WS_PAYI_PER != '8' 
            && (LNB2100_AWA_2100.CAL_PERU != ' ' 
            || LNB2100_AWA_2100.CAL_PERD != 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PERD_CANNOT_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((WS_REPY_MTH == '0' 
            || WS_REPY_MTH == '1' 
            || WS_REPY_MTH == '2' 
            || WS_REPY_MTH == '3' 
            || WS_REPY_MTH == '6')) {
            if (LNB2100_AWA_2100.PYP_CIRC == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYP_CIRC_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_PYP_CIRC = LNB2100_AWA_2100.PYP_CIRC;
            if ((WS_PYP_CIRC != '1' 
                && WS_PYP_CIRC != '2' 
                && WS_PYP_CIRC != '3' 
                && WS_PYP_CIRC != '4' 
                && WS_PYP_CIRC != '5' 
                && WS_PYP_CIRC != '6' 
                && WS_PYP_CIRC != '7' 
                && WS_PYP_CIRC != '8')) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYP_CIRC_ERR, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((WS_REPY_MTH == '0' 
            || WS_REPY_MTH == '1' 
            || WS_REPY_MTH == '2' 
            || WS_REPY_MTH == '6') 
            && WS_PYP_CIRC != '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYP_CIRC_MUST_ONCE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_PYP_CIRC == '8' 
            && (LNB2100_AWA_2100.PYP_PERU == ' ' 
            || LNB2100_AWA_2100.PYP_PERD == 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYP_PERD_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PYP_PERU);
        CEP.TRC(SCCGWA, LNB2100_AWA_2100.PYP_PERD);
        if (WS_PYP_CIRC != '8' 
            && (LNB2100_AWA_2100.PYP_PERU != ' ' 
            || LNB2100_AWA_2100.PYP_PERD != 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PERD_CANNOT_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCFFSDT);
        LNCFFSDT.FUNC = 'R';
        LNCFFSDT.PAYI_PERD = WS_PAYI_PER;
        LNCFFSDT.PYP_CIRC = WS_PYP_CIRC;
        S000_CALL_LNZFFSDT();
        if (pgmRtn) return;
        if (WS_PAYI_PER != '8') {
            LNB2100_AWA_2100.CAL_PERD = LNCFFSDT.CAL_PERD;
            LNB2100_AWA_2100.CAL_PERU = LNCFFSDT.CAL_PERU;
        }
        if (WS_PYP_CIRC != '8') {
            LNB2100_AWA_2100.PYP_PERD = LNCFFSDT.PYP_PERD;
            LNB2100_AWA_2100.PYP_PERU = LNCFFSDT.PYP_PERU;
        }
        if (WS_REPY_MTH == '3') {
            WS_II = (short) (LNB2100_AWA_2100.PYP_PERD % LNB2100_AWA_2100.CAL_PERD);
            WS_I = (short) ((LNB2100_AWA_2100.PYP_PERD - WS_II) / LNB2100_AWA_2100.CAL_PERD);
            if (WS_II != 0 
                || WS_I <= 1 
                || LNB2100_AWA_2100.PYP_PERU != LNB2100_AWA_2100.CAL_PERU) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPY_PRIN_ERR, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2100_AWA_2100.PAY_DTYP != ' ' 
            && LNB2100_AWA_2100.PAY_DTYP != '1' 
            && LNB2100_AWA_2100.PAY_DTYP != '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAY_DTYP_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.CAL_DAY != 0 
            && LNB2100_AWA_2100.PAY_DTYP == ' ') {
            LNB2100_AWA_2100.PAY_DTYP = '2';
        }
        if (LNB2100_AWA_2100.PAY_DTYP == '1') {
            if (WS_PAYI_PER != '4') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAYI_PER_M_MONTH, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.CAL_FSDT != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FST_DT_NALOW, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = "" + LNB2100_AWA_2100.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNB2100_AWA_2100.CAL_DAY = 0;
            else LNB2100_AWA_2100.CAL_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        if (LNB2100_AWA_2100.PAY_DTYP == '2') {
            if (LNB2100_AWA_2100.CAL_DAY == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_DAY_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2100_AWA_2100.CAL_PERU == 'M' 
                && (LNB2100_AWA_2100.CAL_DAY < 1 
                || LNB2100_AWA_2100.CAL_DAY > 31) 
                || LNB2100_AWA_2100.CAL_PERU == 'D' 
                && (LNB2100_AWA_2100.CAL_DAY < 1 
                || LNB2100_AWA_2100.CAL_DAY > 7)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_DAY_VAL_ERR, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((WS_REPY_MTH == '0' 
            || WS_REPY_MTH == '1')) {
            LNB2100_AWA_2100.CAL_FSDT = LNB2100_AWA_2100.MATU_DT;
        }
        if (LNB2100_AWA_2100.CAL_FSDT != 0 
            && LNB2100_AWA_2100.CAL_DAY != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = LNB2100_AWA_2100.CAL_FSDT;
            R000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCKDT.MTH_DAYS);
            if (LNB2100_AWA_2100.CAL_PERU == 'M') {
                JIBS_tmp_str[0] = "" + LNB2100_AWA_2100.CAL_FSDT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (LNB2100_AWA_2100.CAL_DAY <= SCCCKDT.MTH_DAYS 
                    && !JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase(LNB2100_AWA_2100.CAL_DAY+"")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FSDT_CALDY_NM, WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                JIBS_tmp_str[0] = "" + LNB2100_AWA_2100.CAL_FSDT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (LNB2100_AWA_2100.CAL_DAY > SCCCKDT.MTH_DAYS 
                    && !JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase(SCCCKDT.MTH_DAYS+"")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FSDT_CALDY_NM, WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (LNB2100_AWA_2100.CAL_PERU == 'D' 
                    && SCCCKDT.WEEK_NO != LNB2100_AWA_2100.CAL_DAY) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FSDT_CALDY_NM, WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if ((WS_REPY_MTH == '2' 
            || WS_REPY_MTH == '3' 
            || WS_REPY_MTH == '4' 
            || WS_REPY_MTH == '6') 
            && LNB2100_AWA_2100.CAL_FSDT == 0) {
            IBS.init(SCCGWA, LNCFFSDT);
            LNCFFSDT.FUNC = 'C';
            LNCFFSDT.REPAY_MTH = LNB2100_AWA_2100.REPY_MTH;
            LNCFFSDT.PAYI_PERD = LNB2100_AWA_2100.PAYI_PER;
            LNCFFSDT.START_DT = LNB2100_AWA_2100.START_DT;
            LNCFFSDT.MATU_DT = LNB2100_AWA_2100.MATU_DT;
            LNCFFSDT.PAY_DTYP = LNB2100_AWA_2100.PAY_DTYP;
            LNCFFSDT.CAL_DAY = LNB2100_AWA_2100.CAL_DAY;
            LNCFFSDT.PYP_CIRC = LNB2100_AWA_2100.PYP_CIRC;
            LNCFFSDT.CAL_PERD = LNB2100_AWA_2100.CAL_PERD;
            LNCFFSDT.CAL_PERU = LNB2100_AWA_2100.CAL_PERU;
            LNCFFSDT.CAL_FSDT = LNB2100_AWA_2100.CAL_FSDT;
            LNCFFSDT.PYP_PERU = LNB2100_AWA_2100.PYP_PERU;
            LNCFFSDT.PYP_PERD = LNB2100_AWA_2100.PYP_PERD;
            S000_CALL_LNZFFSDT();
            if (pgmRtn) return;
            if (LNCFFSDT.CAL_FSDT < SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_END_LES_ACDT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                LNB2100_AWA_2100.CAL_FSDT = LNCFFSDT.CAL_FSDT;
            }
        }
        if (LNB2100_AWA_2100.PAY_DTYP == ' ' 
            && LNB2100_AWA_2100.CAL_DAY == 0) {
            LNB2100_AWA_2100.PAY_DTYP = '2';
            JIBS_tmp_str[0] = "" + LNB2100_AWA_2100.CAL_FSDT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNB2100_AWA_2100.CAL_DAY = 0;
            else LNB2100_AWA_2100.CAL_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
    }
    public void R000_CHECK_AC_INF() throws IOException,SQLException,Exception {
        if (LNB2100_AWA_2100.DRAW_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DRAW_AC_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.PAY_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.E_REC_AC_MUST_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.DW_BK_TP == ' ' 
            || LNB2100_AWA_2100.PA_BK_TP == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PA_BK_TP_I, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_BANK_TYPE = LNB2100_AWA_2100.DW_BK_TP;
        WS_SETL_AC = LNB2100_AWA_2100.DRAW_AC;
        B110_GET_AC_INFO();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.DRAW_ACT.trim().length() > 0 
            && !LNB2100_AWA_2100.DRAW_ACT.equalsIgnoreCase(WS_SETL_AC_TYPE)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_AND_TYP_NMATCH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNB2100_AWA_2100.DRAW_ACT = WS_SETL_AC_TYPE;
        LNB2100_AWA_2100.DRAW_AC = WS_SETL_AC;
        WS_BANK_TYPE = LNB2100_AWA_2100.PA_BK_TP;
        WS_SETL_AC = LNB2100_AWA_2100.PAY_AC;
        B110_GET_AC_INFO();
        if (pgmRtn) return;
        if (LNB2100_AWA_2100.PAY_AC_T.trim().length() > 0 
            && !LNB2100_AWA_2100.PAY_AC_T.equalsIgnoreCase(WS_SETL_AC_TYPE)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_AND_TYP_NMATCH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNB2100_AWA_2100.PAY_AC_T = WS_SETL_AC_TYPE;
        LNB2100_AWA_2100.PAY_AC = WS_SETL_AC;
        if (LNB2100_AWA_2100.DRAW_ACT.equalsIgnoreCase(K_DD_AC) 
            && LNB2100_AWA_2100.DW_BK_TP == K_OUR_BANK) {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CI_NO = LNB2100_AWA_2100.CI_NO;
            LNCSSTBL.DRAW_AC = LNB2100_AWA_2100.DRAW_AC;
            LNCSSTBL.S_CODE = "2151";
            S000_CALL_LNZSSTBL();
            if (pgmRtn) return;
        }
        if (LNB2100_AWA_2100.PAY_AC_T.equalsIgnoreCase(K_DD_AC) 
            && LNB2100_AWA_2100.PA_BK_TP == K_OUR_BANK) {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CI_NO = LNB2100_AWA_2100.CI_NO;
            LNCSSTBL.DRAW_AC = LNB2100_AWA_2100.PAY_AC;
            S000_CALL_LNZSSTBL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = LNB2100_AWA_2100.PAY_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
            if (DDCIMMST.DATA.AC_TYPE == 'M') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN0353, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = LNB2100_AWA_2100.PAY_AC;
            DDCIMCCY.DATA[1-1].CCY = LNB2100_AWA_2100.CCY;
            DDCIMCCY.DATA[1-1].CCY_TYPE = LNB2100_AWA_2100.CCY_TYP;
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
            if (!DDCIMCCY.DATA[1-1].CCY.equalsIgnoreCase(LNB2100_AWA_2100.CCY)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CCY_NOT_MATCH, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2100_AWA_2100.DRAW_AC.equalsIgnoreCase(K_IA_AC) 
            && LNB2100_AWA_2100.DW_BK_TP == K_OUR_BANK) {
            IBS.CPY2CLS(SCCGWA, LNB2100_AWA_2100.DRAW_AC, WS_IA_AC);
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
            AICPQITM.INPUT_DATA.NO = WS_IA_AC.WS_IA_AC_KEMU;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IA_AC_OUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2100_AWA_2100.PAY_AC_T.equalsIgnoreCase(K_IA_AC) 
            && LNB2100_AWA_2100.PA_BK_TP == K_OUR_BANK) {
            IBS.CPY2CLS(SCCGWA, LNB2100_AWA_2100.PAY_AC, WS_IA_AC);
            if (!WS_IA_AC.WS_IA_AC_CCY.equalsIgnoreCase(LNB2100_AWA_2100.CCY)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CCY_NOT_MATCH, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
            AICPQITM.INPUT_DATA.NO = WS_IA_AC.WS_IA_AC_KEMU;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IA_AC_OUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZFFSDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CAL-FSDT", LNCFFSDT);
        if (LNCFFSDT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCFFSDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZFRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-F-RATE-CAL", LNCFRATE);
        if (LNCFRATE.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCFRATE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZSSYND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-SYN-DRAWDOWN", LNCSSYND);
        if (LNCSSYND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSYND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_APP = "SC";
            WS_ERR_MSG.WS_ERR_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.eqWhere = "PAPER_NO,DRAW_NO";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_DRAW_NO_EXIST, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
