package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.IB.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZEODEL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY       ";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    int WS_IDX = 0;
    String WS_MSG_TXT = " ";
    short WS_ERROR_FIELD_NO = 0;
    String WS_ERROR_FIELD = " ";
    char WS_STOP = ' ';
    int WS_1_DATE = 0;
    AIZEODEL_REDEFINES10 REDEFINES10 = new AIZEODEL_REDEFINES10();
    int WS_2_DATE = 0;
    AIZEODEL_REDEFINES14 REDEFINES14 = new AIZEODEL_REDEFINES14();
    short WS_I = 0;
    String WS_PROD_CD = " ";
    double WS_T_AMT = 0;
    String WS_LOAN_TYPE = " ";
    AIZEODEL_WS_INTERNAL_AC WS_INTERNAL_AC = new AIZEODEL_WS_INTERNAL_AC();
    AIZEODEL_WS_DD_RC WS_DD_RC = new AIZEODEL_WS_DD_RC();
    AIZEODEL_WS_TMP_DATA WS_TMP_DATA = new AIZEODEL_WS_TMP_DATA();
    AIZEODEL_WS_ODE_DATA WS_ODE_DATA = new AIZEODEL_WS_ODE_DATA();
    AIZEODEL_WS_BATHNO WS_BATHNO = new AIZEODEL_WS_BATHNO();
    AIZEODEL_WS_BAT_PARM WS_BAT_PARM = new AIZEODEL_WS_BAT_PARM();
    int WS_TR_BRANCH = 0;
    char WS_TEL_BRAN_FLG = ' ';
    char WS_ITM_FLG = ' ';
    double WS_AMT = 0;
    char WS_BOOK_FND_FLG = ' ';
    char WS_DESC_CN_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDCICKCD DDCICKCD = new DDCICKCD();
    AICOCKOP AICOCKOP = new AICOCKOP();
    AICCKPO AICCKPO = new AICCKPO();
    BPCEX BPCEX = new BPCEX();
    SCCIMSG SCCIMSG = new SCCIMSG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUPRVS AICUPRVS = new AICUPRVS();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRMIB AIRMIB = new AIRMIB();
    AICRMIB AICRMIB = new AICRMIB();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    SCCGWA SCCGWA;
    BPCEXCHK BPCEXCHK;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA, BPCEXCHK BPCEXCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXCHK = BPCEXCHK;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZEODEL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        IBS.init(SCCGWA, BPCEXCHK.RC);
        BPCEXCHK.DATA_FLG = ' ';
        IBS.CPY2CLS(SCCGWA, BPCEXCHK.EXCEL_BATNO, WS_BATHNO);
        CEP.TRC(SCCGWA, SCCBATH.PARM);
        IBS.CPY2CLS(SCCGWA, SCCBATH.PARM, WS_BAT_PARM);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCEXCHK.DATA_FLG = '1';
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_MAP_INPUT_DATA();
        if (pgmRtn) return;
        B300_CHK_DATA_VALID();
        if (pgmRtn) return;
        B400_CHK_BR();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN CHECK HOL DATA");
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_TYPE);
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA.substring(153 - 1, 153 + 8 - 1));
        if (!BPCEXCHK.EXCEL_TYPE.equalsIgnoreCase("03") 
            && !BPCEXCHK.EXCEL_TYPE.equalsIgnoreCase("X3")) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_UP_NOT_ODE_ENT, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXCHK.EXCEL_DATA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAP_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCEXCHK.EXCEL_DATA, WS_ODE_DATA);
        if (WS_ODE_DATA.WS_ENTRY_TYPE == 'I') {
            if (WS_ODE_DATA.WS_AC.trim().length() == 0) {
                if (WS_ODE_DATA.WS_BCH == ' ' 
                    || WS_ODE_DATA.WS_CUR.trim().length() == 0 
                    || WS_ODE_DATA.WS_ITM_NO.trim().length() == 0 
                    || WS_ODE_DATA.WS_BR_SEQ == 0) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AC_MUST_INPUT, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "IA ACCOUNT";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, WS_INTERNAL_AC);
                WS_INTERNAL_AC.WS_INTERNAL_BR = WS_ODE_DATA.WS_BCH;
                WS_INTERNAL_AC.WS_INTERNAL_CCY = WS_ODE_DATA.WS_CUR;
                WS_INTERNAL_AC.WS_INTERNAL_ITM = WS_ODE_DATA.WS_ITM_NO;
                WS_INTERNAL_AC.WS_INTERNAL_SEQ = WS_ODE_DATA.WS_BR_SEQ;
                WS_ODE_DATA.WS_AC = IBS.CLS2CPY(SCCGWA, WS_INTERNAL_AC);
            } else {
                if (WS_ODE_DATA.WS_AC == null) WS_ODE_DATA.WS_AC = "";
                JIBS_tmp_int = WS_ODE_DATA.WS_AC.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) WS_ODE_DATA.WS_AC += " ";
                if (WS_ODE_DATA.WS_AC.substring(23 - 1, 23 + 1 - 1).trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.NOT_INVALID_ACCOUNT, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "IA ACCOUNT";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                IBS.CPY2CLS(SCCGWA, WS_ODE_DATA.WS_AC, WS_INTERNAL_AC);
            }
        }
        CEP.TRC(SCCGWA, WS_ODE_DATA);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_GL_BOOK);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_VALUE_DATE);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ENTRY_TYPE);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_BCH);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_CUR);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ITM_NO);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_AC);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_AC);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ACTION_CODE);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_RB_FLG);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_AMOUNT);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_REV_NO);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_NARR_CD);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_TRN_DESC);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_TRN_DESC);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_REF_NO);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_PROD_TYPE);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD1);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD2);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD3);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD4);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD5);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD6);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD7);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD8);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD9);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ANS_CD10);
        if (WS_ODE_DATA.WS_BCH == ' ') {
            WS_ODE_DATA.WS_BCH = 0;
        } else {
            B210_BRANCH_CHECKING();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_ITM_NO.trim().length() == 0) {
            WS_ODE_DATA.WS_ITM_NO = "" + 0;
            JIBS_tmp_int = WS_ODE_DATA.WS_ITM_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_ODE_DATA.WS_ITM_NO = "0" + WS_ODE_DATA.WS_ITM_NO;
        }
    }
    public void B210_BRANCH_CHECKING() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == ' ') {
            S00_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            WS_TR_BRANCH = BPCFTLRQ.INFO.TLR_BR;
        } else {
            WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (WS_TR_BRANCH != WS_ODE_DATA.WS_BCH) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = WS_TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.LVL == '2') {
                WS_TEL_BRAN_FLG = 'Y';
            } else if (BPCPQORG.LVL == '4') {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPRGST.BR1 = BPCPQORG.BR;
                BPCPRGST.BR2 = WS_ODE_DATA.WS_BCH;
                S00_CALL_BPZPRGST();
                if (pgmRtn) return;
                if (BPCPRGST.FLAG == 'Y' 
                    && (BPCPRGST.LVL_RELATION == 'A' 
                    || BPCPRGST.LVL_RELATION == 'B')) {
                } else {
                    WS_TEL_BRAN_FLG = 'Y';
                }
            } else if (BPCPQORG.LVL == '6') {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPRGST.BR1 = BPCPQORG.BR;
                BPCPRGST.BR2 = WS_ODE_DATA.WS_BCH;
                S00_CALL_BPZPRGST();
                if (pgmRtn) return;
                if (BPCPRGST.BRANCH_FLG == 'N') {
                    WS_TEL_BRAN_FLG = 'Y';
                }
            } else if (BPCPQORG.LVL == '9') {
            } else {
                WS_TEL_BRAN_FLG = 'Y';
            }
        }
        if (WS_TEL_BRAN_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_TEL_CANNOT_MAIN_GRP, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "BRANCH ERROR";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void B300_CHK_DATA_VALID() throws IOException,SQLException,Exception {
        if (WS_ODE_DATA.WS_VALUE_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_VAL_DATE_ERR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_REV_NO.trim().length() > 0) {
            IBS.init(SCCGWA, AIRGINF);
            IBS.init(SCCGWA, AICRGINF);
            AICRGINF.INFO.FUNC = 'Q';
            AIRGINF.KEY.RVS_NO = WS_ODE_DATA.WS_REV_NO;
            S000_CALL_AIZRGINF();
            if (pgmRtn) return;
            if (AICRGINF.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "REV NO";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
        if ((WS_ODE_DATA.WS_ENTRY_TYPE != 'G' 
            && WS_ODE_DATA.WS_ENTRY_TYPE != 'D' 
            && WS_ODE_DATA.WS_ENTRY_TYPE != 'N' 
            && WS_ODE_DATA.WS_ENTRY_TYPE != 'I')) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_ENTRY_INPUT_ERR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "ENTRY TYPE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_GL_BOOK.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.FUNC = 'B';
            S000_CALL_BPZQBKPM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCQBKPM.CNT);
            WS_BOOK_FND_FLG = 'N';
            for (WS_IDX = 1; WS_IDX <= BPCQBKPM.CNT 
                && WS_BOOK_FND_FLG != 'Y'; WS_IDX += 1) {
                if (BPCQBKPM.DATA[WS_IDX-1].BOOK_FLG.equalsIgnoreCase(WS_ODE_DATA.WS_GL_BOOK) 
                    && BPCQBKPM.DATA[WS_IDX-1].STS == 'A') {
                    WS_BOOK_FND_FLG = 'Y';
                }
            }
            if (WS_BOOK_FND_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.GL_BOOK_NOT_VAL, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "GL BOOK";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
        if (WS_ODE_DATA.WS_TRN_DESC.trim().length() > 0) {
            for (WS_I = 1; WS_I <= 120; WS_I += 1) {
                if (WS_ODE_DATA.WS_TRN_DESC == null) WS_ODE_DATA.WS_TRN_DESC = "";
                JIBS_tmp_int = WS_ODE_DATA.WS_TRN_DESC.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) WS_ODE_DATA.WS_TRN_DESC += " ";
                if (WS_ODE_DATA.WS_TRN_DESC.charAt(WS_I - 1) == 0X0E) {
                    WS_DESC_CN_FLG = 'S';
                }
                if (WS_ODE_DATA.WS_TRN_DESC == null) WS_ODE_DATA.WS_TRN_DESC = "";
                JIBS_tmp_int = WS_ODE_DATA.WS_TRN_DESC.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) WS_ODE_DATA.WS_TRN_DESC += " ";
                if (WS_ODE_DATA.WS_TRN_DESC.charAt(WS_I - 1) == 0X0F) {
                    if (WS_DESC_CN_FLG == 'S') {
                        WS_DESC_CN_FLG = 'E';
                    } else {
                        WS_DESC_CN_FLG = 'R';
                    }
                }
                if (WS_ODE_DATA.WS_TRN_DESC == null) WS_ODE_DATA.WS_TRN_DESC = "";
                JIBS_tmp_int = WS_ODE_DATA.WS_TRN_DESC.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) WS_ODE_DATA.WS_TRN_DESC += " ";
                if (WS_I > 36 
                    && WS_ODE_DATA.WS_ENTRY_TYPE == 'D' 
                    && WS_ODE_DATA.WS_TRN_DESC.substring(WS_I - 1, WS_I + 1 - 1).trim().length() > 0) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DESC_EX_DD_LIMIT, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "TRANSACTION DESCRIPTION";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
            }
            if (WS_DESC_CN_FLG == 'S' 
                || WS_DESC_CN_FLG == 'R') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "TRANSACTION DESCRIPTION";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        } else {
            WS_ODE_DATA.WS_TRN_DESC = WS_ODE_DATA.WS_NARR_CD + WS_ODE_DATA.WS_REF_NO;
        }
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ENTRY_TYPE);
        if (WS_ODE_DATA.WS_ENTRY_TYPE == 'G') {
            if (WS_ODE_DATA.WS_AC.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AC_CANN_INPUT, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "DD/IB ACCOUNT";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
            if (WS_ODE_DATA.WS_ITM_NO.compareTo(0) <= 0) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_INFO_NOTFND, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "GL A/C NO.";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, AICCKPO);
                AICCKPO.CHANNEL_INFO.GL_BOOK_FLG = WS_ODE_DATA.WS_GL_BOOK;
                AICCKPO.CHANNEL_INFO.BR = WS_ODE_DATA.WS_BCH;
                AICCKPO.CHANNEL_INFO.ITM_NO = WS_ODE_DATA.WS_ITM_NO;
                AICCKPO.CHANNEL_INFO.DRCRFLG = WS_ODE_DATA.WS_ACTION_CODE;
                AICCKPO.CHANNEL_INFO.CUR = WS_ODE_DATA.WS_CUR;
                AICCKPO.CHANNEL_INFO.VAL_DATE = WS_ODE_DATA.WS_VALUE_DATE;
                S000_CALL_AIZPCKPO();
                if (pgmRtn) return;
            }
        } else {
            if (WS_ODE_DATA.WS_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AC_MUST_INPUT, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "DD/IB ACCOUNT";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_PROD_TYPE);
        if (WS_ODE_DATA.WS_PROD_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = WS_ODE_DATA.WS_PROD_TYPE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_AC.trim().length() > 0) {
            if (WS_ODE_DATA.WS_ENTRY_TYPE == 'D') {
                IBS.init(SCCGWA, DDCICKCD);
                if (WS_ODE_DATA.WS_ACTION_CODE == 'C') {
                    DDCICKCD.INPUT_DATA.FUNC = '1';
                } else {
                    DDCICKCD.INPUT_DATA.FUNC = '2';
                }
                if (WS_ODE_DATA.WS_RB_FLG == 'R') {
                    DDCICKCD.INPUT_DATA.TX_AMT = WS_AMT;
                }
                DDCICKCD.INPUT_DATA.AC_NO = WS_ODE_DATA.WS_AC;
                DDCICKCD.INPUT_DATA.CCY = WS_ODE_DATA.WS_CUR;
                DDCICKCD.INPUT_DATA.HOLD_M_FLG = 'N';
                S000_CALL_DDZICKCD();
                if (pgmRtn) return;
            }
            if (WS_ODE_DATA.WS_ENTRY_TYPE == 'N') {
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.NOSTRO_CD = WS_ODE_DATA.WS_AC;
                IBCQINF.INPUT_DATA.CCY = WS_ODE_DATA.WS_CUR;
                S000_CALL_IBZQINF();
                if (pgmRtn) return;
            }
            if (WS_ODE_DATA.WS_ENTRY_TYPE == 'I') {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.AC = WS_ODE_DATA.WS_AC;
                AICPQMIB.INPUT_DATA.CCY = WS_ODE_DATA.WS_CUR;
                S000_CALL_AIZPQMIB();
                if (pgmRtn) return;
                if (AICPQMIB.OUTPUT_DATA.STS != 'N' 
                    && AICPQMIB.OUTPUT_DATA.STS != 'S') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_NOT_NORMAL, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "MIB STS";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                if (WS_ODE_DATA.WS_CUR.trim().length() > 0 
                    && !WS_INTERNAL_AC.WS_INTERNAL_CCY.equalsIgnoreCase(WS_ODE_DATA.WS_CUR)) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.M_CHK_CCY, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "IA CURRECY";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                if (AICPQMIB.OUTPUT_DATA.CARD_FLG == 'Y') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_8971, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "CARD FLG";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                if (AICPQMIB.OUTPUT_DATA.MANUAL_FLG != 'Y') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AC_NOT_MANUL_POST, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "IN AC";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                if (AICPQMIB.OUTPUT_DATA.STS == 'C' 
                    || AICPQMIB.OUTPUT_DATA.STS == 'H') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.IN_AC_STS_UNINVALID, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "IN AC";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                if (AICPQMIB.OUTPUT_DATA.AMT_DIR != 'B' 
                    && AICPQMIB.OUTPUT_DATA.AMT_DIR != WS_ODE_DATA.WS_ACTION_CODE) {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DIR_NOT_ALLOW, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "DRCRFLG";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                if (WS_ODE_DATA.WS_RVS_TYPE == ' ') {
                    WS_ODE_DATA.WS_RVS_TYPE = AICPQMIB.OUTPUT_DATA.RVS_TYP;
                }
                if (WS_ODE_DATA.WS_RVS_TYPE == 'C' 
                    || WS_ODE_DATA.WS_RVS_TYPE == 'D') {
                    if (WS_ODE_DATA.WS_RVS_FLG != '1' 
                        && WS_ODE_DATA.WS_RVS_FLG != '2') {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.RVS_KND_WRONG, BPCEXCHK.RC);
                        BPCEXCHK.DATA_FLG = '0';
                        S000_GET_ERRORMSG();
                        if (pgmRtn) return;
                        WS_ERROR_FIELD = "RVS FLG";
                        S000_TRAN_DATA();
                        if (pgmRtn) return;
                    }
                } else {
                    if (WS_ODE_DATA.WS_RVS_FLG == '1' 
                        || WS_ODE_DATA.WS_RVS_FLG == '2') {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_NO_ALLOW, BPCEXCHK.RC);
                        BPCEXCHK.DATA_FLG = '0';
                        S000_GET_ERRORMSG();
                        if (pgmRtn) return;
                        WS_ERROR_FIELD = "RVS FLG";
                        S000_TRAN_DATA();
                        if (pgmRtn) return;
                    }
                }
                if (WS_ODE_DATA.WS_RVS_FLG == '1') {
                    if (WS_ODE_DATA.WS_RVS_TYPE != WS_ODE_DATA.WS_ACTION_CODE 
                        && WS_ODE_DATA.WS_RB_FLG == 'B') {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DRCR_NOT_EQ_RVS_TYP, BPCEXCHK.RC);
                        BPCEXCHK.DATA_FLG = '0';
                        S000_GET_ERRORMSG();
                        if (pgmRtn) return;
                        WS_ERROR_FIELD = "RVS TYP";
                        S000_TRAN_DATA();
                        if (pgmRtn) return;
                    }
                    if (WS_ODE_DATA.WS_RVS_TYPE == WS_ODE_DATA.WS_ACTION_CODE 
                        && WS_ODE_DATA.WS_RB_FLG == 'R') {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP2_FOR_RED, BPCEXCHK.RC);
                        BPCEXCHK.DATA_FLG = '0';
                        S000_GET_ERRORMSG();
                        if (pgmRtn) return;
                        WS_ERROR_FIELD = "RVS TYP";
                        S000_TRAN_DATA();
                        if (pgmRtn) return;
                    }
                }
                if (WS_ODE_DATA.WS_RVS_FLG == '2') {
                    if (WS_ODE_DATA.WS_RVS_TYPE == WS_ODE_DATA.WS_ACTION_CODE 
                        && WS_ODE_DATA.WS_RB_FLG == 'B') {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DRCR_EQ_RVS_TYP, BPCEXCHK.RC);
                        BPCEXCHK.DATA_FLG = '0';
                        S000_GET_ERRORMSG();
                        if (pgmRtn) return;
                        WS_ERROR_FIELD = "RVS TYP";
                        S000_TRAN_DATA();
                        if (pgmRtn) return;
                    }
                    if (WS_ODE_DATA.WS_RVS_TYPE != WS_ODE_DATA.WS_ACTION_CODE 
                        && WS_ODE_DATA.WS_RB_FLG == 'R') {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP1_FOR_RED, BPCEXCHK.RC);
                        BPCEXCHK.DATA_FLG = '0';
                        S000_GET_ERRORMSG();
                        if (pgmRtn) return;
                        WS_ERROR_FIELD = "RVS TYP";
                        S000_TRAN_DATA();
                        if (pgmRtn) return;
                    }
                }
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.NO = WS_INTERNAL_AC.WS_INTERNAL_ITM;
                AICPQITM.INPUT_DATA.BOOK_FLG = WS_ODE_DATA.WS_GL_BOOK;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                if (AICPQITM.OUTPUT_DATA.STS != 'A' 
                    && AICPQITM.OUTPUT_DATA.STS != 'S') {
                    IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE, BPCEXCHK.RC);
                    BPCEXCHK.DATA_FLG = '0';
                    S000_GET_ERRORMSG();
                    if (pgmRtn) return;
                    WS_ERROR_FIELD = "ITM STS";
                    S000_TRAN_DATA();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "2015042211");
            }
        }
        CEP.TRC(SCCGWA, "2015042212");
        CEP.TRC(SCCGWA, "2015042212");
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_AMOUNT);
        if (WS_ODE_DATA.WS_AMOUNT.trim().length() == 0) WS_T_AMT = 0;
        else WS_T_AMT = Double.parseDouble(WS_ODE_DATA.WS_AMOUNT);
        CEP.TRC(SCCGWA, WS_T_AMT);
        if (WS_ODE_DATA.WS_RB_FLG == 'R' 
            && WS_T_AMT > 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.RED_FLG_AMT_NEG, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "AMOUNT";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_RB_FLG != 'R' 
            && WS_T_AMT < 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.N_RED_FLG_AMT_POSTIVE, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "AMOUNT";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_BCH != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = WS_ODE_DATA.WS_BCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.ATTR != '2') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "ACCOUNTING CENTRE";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        } else {
            if (WS_ODE_DATA.WS_ENTRY_TYPE == 'G') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "ACCOUNTING CENTRE";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
        if (WS_ODE_DATA.WS_CUR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = WS_ODE_DATA.WS_CUR;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_NARR_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = "MMO";
            BPCOQPCD.INPUT_DATA.CODE = WS_ODE_DATA.WS_NARR_CD;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
        }
        if (WS_ODE_DATA.WS_VALUE_DATE != 0) {
            IBS.init(SCCGWA, AICOCKOP);
            AICOCKOP.VAL_DATE = WS_ODE_DATA.WS_VALUE_DATE;
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                AICOCKOP.ONL_ACDT = WS_BAT_PARM.WS_BAT_ACDT;
                CEP.TRC(SCCGWA, AICOCKOP.ONL_ACDT);
                CEP.TRC(SCCGWA, WS_BAT_PARM);
            }
            S000_CALL_AIZPCKOP();
            if (pgmRtn) return;
        } else {
            WS_ODE_DATA.WS_VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "PRODUCT TYPE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void B400_CHK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI1);
        AIRPAI1.KEY.TYP = "PAI01";
        AIRPAI1.KEY.REDEFINES6.CODE = "FMD";
        AIRPAI1.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI1.KEY.REDEFINES6);
        BPCPRMR.DAT_PTR = AIRPAI1;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRPAI1.DATA_TXT.DATA_INF.BR);
        CEP.TRC(SCCGWA, WS_BATHNO.WS_BN_BR);
        CEP.TRC(SCCGWA, WS_ODE_DATA.WS_ENTRY_TYPE);
        if (WS_BATHNO.WS_BN_BR == AIRPAI1.DATA_TXT.DATA_INF.BR) {
            if (WS_ODE_DATA.WS_ENTRY_TYPE != 'I') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_FINBR_CANT_MAKE_DDIB, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "BR ERROR";
                S000_TRAN_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "BR ERROR";
            S000_TRAN_DATA();
            if (pgmRtn) return;
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_BPZPQBAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BKAI", BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "VALUE DATE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZICKCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-CR-DR-CHK", DDCICKCD);
        for (WS_I = 1; WS_I <= DDCICKCD.OUTPUT_DATA.RTN_CNT; WS_I += 1) {
            if (DDCICKCD.OUTPUT_DATA.RC[WS_I-1].RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCICKCD.OUTPUT_DATA.RC[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DD_RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DD_RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                S000_GET_ERRORMSG();
                if (pgmRtn) return;
                WS_ERROR_FIELD = "DD/IB ACCOUNT";
                S000_TRAN_DATA();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DD_RC);
                JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_DD_RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase("DD1803") 
                    || JIBS_tmp_str[2].equalsIgnoreCase("DD1811")) {
                    WS_I = DDCICKCD.OUTPUT_DATA.RTN_CNT;
                }
            }
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "CURRENCY";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        if (BPCQCCY.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, "BP0180", BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "CURRENCY NOTFND";
            CEP.TRC(SCCGWA, WS_ERROR_FIELD);
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "NARRATIVE CODE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "ACCOUNTING CENTRE";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "REV NO";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPCKPO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-GL-POST", AICCKPO);
        if (AICCKPO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICCKPO.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "GL A/C NO.";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICCKPO.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("AI8808")) {
            WS_ITM_FLG = 'N';
        } else {
            WS_ITM_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_ITM_FLG);
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "ACCOUNT/CURRENCY";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "ACCOUNT/CURRENCY";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
        if (AICUPRVS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUPRVS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "REV NO";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
        CEP.TRC(SCCGWA, AICRMIB.RC);
        if (AICRMIB.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "AC NO";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "GL BOOK";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "FTLRQ  ";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            S000_GET_ERRORMSG();
            if (pgmRtn) return;
            WS_ERROR_FIELD = "PRGST  ";
            S000_TRAN_DATA();
            if (pgmRtn) return;
        }
    }
    public void S000_GET_ERRORMSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCIMSG);
        SCCIMSG.ID = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
        S000_CALL_SCZIMSG();
        if (pgmRtn) return;
        if (WS_MSG_TXT == null) WS_MSG_TXT = "";
        JIBS_tmp_int = WS_MSG_TXT.length();
        for (int i=0;i<88-JIBS_tmp_int;i++) WS_MSG_TXT += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
        WS_MSG_TXT = JIBS_tmp_str[0] + WS_MSG_TXT.substring(8);
        if (WS_MSG_TXT == null) WS_MSG_TXT = "";
        JIBS_tmp_int = WS_MSG_TXT.length();
        for (int i=0;i<88-JIBS_tmp_int;i++) WS_MSG_TXT += " ";
        if (SCCIMSG.TXT == null) SCCIMSG.TXT = "";
        JIBS_tmp_int = SCCIMSG.TXT.length();
        for (int i=0;i<88-JIBS_tmp_int;i++) SCCIMSG.TXT += " ";
        WS_MSG_TXT = WS_MSG_TXT.substring(0, 9 - 1) + SCCIMSG.TXT + WS_MSG_TXT.substring(9 + SCCIMSG.TXT.length() - 1);
    }
    public void S000_CALL_SCZIMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-MSG-INQ", SCCIMSG);
    }
    public void S000_TRAN_DATA() throws IOException,SQLException,Exception {
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXCHK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCEXCHK = ");
            CEP.TRC(SCCGWA, BPCEXCHK);
        }
    } //FROM #ENDIF
        if (BPCEXCHK.DATA_FLG == '0') {
            BPCEXCHK.RC.ERR_INF = "AC=" + WS_ODE_DATA.WS_AC + ",CCY=" + WS_ODE_DATA.WS_CUR + ",ERROR FIELD IS " + WS_ERROR_FIELD;
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
