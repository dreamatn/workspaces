package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPLAJ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BigDecimal bigD;
    DBParm LNTPLPI_RD;
    DBParm LNTPAIP_RD;
    brParm LNTPLPI_BR = new brParm();
    brParm LNTSCHT_BR = new brParm();
    DBParm LNTBALZ_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    char K_NEXT_TERM = '2';
    int K_CNT = 1;
    String K_REMARK = "EXTN ADJUST PLPI (ADD)";
    char LNZPLAJ_FILLER1 = ' ';
    int WS_I = 0;
    int WS_J = 0;
    char WS_TYPE = ' ';
    short WS_TERM = 0;
    short WS_TOT_UNDUE_TERM = 0;
    short WS_FIRST_UNDUE_TERM = 0;
    short WS_P_CAL_TERM = 0;
    int WS_IC_CMP_VAL_DT = 0;
    String WS_FORM_CODE = " ";
    short WS_INST_TERM = 0;
    double WS_INST_AMT = 0;
    double WS_PRIN_AMT = 0;
    double WS_TERM_PRIN_AMT = 0;
    double WS_PAY_P_AMT = 0;
    double WS_TERM_TOT_PRIN_AMT = 0;
    double WS_PRIN_INT = 0;
    double WS_BAL_INT = 0;
    double WS_P_AMT = 0;
    double WS_PAY_N_BAL = 0;
    double WS_NRO_BAL = 0;
    double WS_REM_PRIN_AMT = 0;
    int WS_IC_VAL_DT = 0;
    int WS_IC_DUE_DT = 0;
    int WS_LAST_TX_SEQ = 0;
    int WS_OLD_DUE_DT = 0;
    short WS_PHS_TOT_TERM = 0;
    short WS_PHS_TOT_TERM2 = 0;
    short WS_CAL_PERD = 0;
    char WS_CAL_UNIT = ' ';
    short WS_PAY_PERD = 0;
    char WS_PAY_UNIT = ' ';
    long WS_SEQ = 0;
    int WS_START_DATE = 0;
    int WS_MAT_DATE = 0;
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_CAL_PAY_DAY = 0;
    int WS_YYYYMMDD = 0;
    short WS_PAIP_TERM = 0;
    char WS_CAL_FST_FLG = ' ';
    int WS_CAL_FST_DT = 0;
    int WS_FST_PAYP_DT = 0;
    short WS_PHS_NO = 0;
    short WS_PHS_NO2 = 0;
    short WS_PHS_ALL = 0;
    int WS_DAYS = 0;
    int WS_LAST_DT = 0;
    long WS_SEQ_NO = 0;
    char WS_INST_MTH = ' ';
    char WS_PAY_MTH = ' ';
    char WS_SPEC_LN_FLG = ' ';
    short WS_CAL_TERM = 0;
    short WS_CUR_TERM = 0;
    short WS_IC_CMP_TERM = 0;
    short WS_IC_CAL_TERM = 0;
    short WS_P_CMP_TERM = 0;
    short WS_PHS_CMP_TERM = 0;
    int WS_IC_CAL_VAL_DT = 0;
    int WS_IC_CAL_DUE_DT = 0;
    int WS_NEW_P_CAL_DUE_DT = 0;
    int WS_P_CAL_DUE_DT = 0;
    int WS_P_CHANGE_DT = 0;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    int WS_CAL_DATE = 0;
    int WS_FIRST_S_DATE = 0;
    int WS_FIRST_E_DATE = 0;
    char WS_REPY_MTH = ' ';
    short WS_CHANGE_TERM = 0;
    short WS_PLPI_TERM = 0;
    short WS_PHS_CAL_TERM = 0;
    short WS_EXTN_TOT_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    short WS_P_CUR_TERM = 0;
    double WS_REM_AMT = 0;
    int WS_BEG_DT = 0;
    int WS_TEMP_VAL_DT = 0;
    int WS_TEMP_DUE_DT = 0;
    double WS_TEMP_PRIN = 0;
    short WS_TOT_DEL_TERM = 0;
    short WS_NEW_TOT_TERM = 0;
    short WS_TEMP_TERM = 0;
    int WS_NEW_MAT_DATE = 0;
    int WS_OLD_MAT_DATE = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FND_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_ADJ_FLG = ' ';
    char WS_DELPI_FLG = ' ';
    char WS_SCHT_FLAG = ' ';
    char WS_ICTL_FLG = ' ';
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNRICTL LNRICTL = new LNRICTL();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCILCM LNCILCM = new LNCILCM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCENSCH LNCENSCH = new LNCENSCH();
    LNCCMSCH LNCCMSCH = new LNCCMSCH();
    LNCMTSCH LNCMTSCH = new LNCMTSCH();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCSPAIP LNCSPAIP = new LNCSPAIP();
    LNCSCALT LNCSCALT = new LNCSCALT();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCMSG SCCMSG = new SCCMSG();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    LNRBALZ LNRBALZ = new LNRBALZ();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    LNCPLAJ LNCPLAJ;
    public void MP(SCCGWA SCCGWA, LNCPLAJ LNCPLAJ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPLAJ = LNCPLAJ;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPLAJ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCPLAJ.RC.RC_APP = "LN";
        LNCPLAJ.RC.RC_RTNCODE = 0;
        WS_OLD_DUE_DT = LNCPLAJ.COMM_DATA.TR_VAL_DATE;
        WS_PAY_P_AMT = LNCPLAJ.COMM_DATA.PAY_P_AMT;
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.TR_VAL_DATE);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.PAY_P_AMT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B200_GET_PPMQ_INF();
        if (pgmRtn) return;
        B300_MAIN_PROC();
        if (pgmRtn) return;
        B400_SEND_MESSAGE_CHECK();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCPLAJ.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCPLAJ.COMM_DATA.SYN_FLG == '3' 
            && LNCPLAJ.COMM_DATA.FIX_TERM <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_START_DATE = LNCCONTM.REC_DATA.START_DATE;
        WS_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        WS_OLD_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        CEP.TRC(SCCGWA, WS_MAT_DATE);
        B110_READ_LNTICTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            B120_READ_LNTLOAN();
            if (pgmRtn) return;
        }
    }
    public void B110_READ_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_IC_CAL_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        WS_IC_CAL_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        WS_NEW_P_CAL_DUE_DT = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
        WS_IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
        WS_IC_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        WS_P_CMP_TERM = LNCICTLM.REC_DATA.P_CMP_TERM;
        WS_P_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        WS_IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        WS_P_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        CEP.TRC(SCCGWA, WS_IC_CMP_TERM);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UN_DRAWDOWN, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B120_READ_LNTLOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        WS_CAL_FST_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
        WS_FST_PAYP_DT = LNCLOANM.REC_DATA.FST_PAYP_DT;
    }
    public void B200_GET_PPMQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        WS_CAL_FST_FLG = LNCAPRDM.REC_DATA.FST_PAY_FLG;
        WS_SPEC_LN_FLG = LNCAPRDM.REC_DATA.SPEC_LN_FLG;
        LNCPLAJ.COMM_DATA.CONT_PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        WS_PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        LNCPLAJ.COMM_DATA.CONT_INST_MTH = LNCAPRDM.REC_DATA.INST_MTH;
        WS_CAL_PERD = LNCAPRDM.REC_DATA.CAL_PERD;
        WS_CAL_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        WS_PAY_PERD = LNCAPRDM.REC_DATA.PAYP_PERD;
        WS_PAY_UNIT = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
        WS_CAL_PAY_DAY = LNCAPRDM.REC_DATA.PAY_DAY;
        if (WS_PAY_MTH == '4') {
            WS_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            WS_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        } else {
            WS_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
            WS_P_CAL_DUE_DT = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
            WS_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        }
    }
    public void B300_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCPLAJ.COMM_DATA.ADJ_IND != 'X') {
            B310_INQ_PAY_PRIN();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
                T000_STARTBR_LNTPAIP();
                if (pgmRtn) return;
                WS_PHS_NO = LNRPAIP.KEY.PHS_NO;
                WS_CNT = LNRPAIP.KEY.PHS_NO;
                WS_PHS_ALL = LNRPAIP.KEY.PHS_NO;
                WS_PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
                WS_PHS_TOT_TERM2 = LNRPAIP.PHS_TOT_TERM;
                WS_CAL_PERD = LNRPAIP.PERD;
                WS_CAL_UNIT = LNRPAIP.PERD_UNIT;
                WS_PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
            } else {
                T000_STARTBR_LNTPAIP();
                if (pgmRtn) return;
                WS_PHS_ALL = LNRPAIP.KEY.PHS_NO;
                if (WS_PHS_ALL > 1 
                    && LNCPLAJ.COMM_DATA.ADJ_IND == 'P') {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.PLEASE_CHANGED_PAY_SCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, WS_IC_CAL_DUE_DT);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'R' 
                && (JIBS_tmp_str[1].equalsIgnoreCase("0139801") 
                || JIBS_tmp_str[1].equalsIgnoreCase("0134070") 
                || JIBS_tmp_str[1].equalsIgnoreCase("0134072")) 
                && WS_IC_CAL_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            } else {
                B330_PAIP_LOAN_PROCESS();
                if (pgmRtn) return;
            }
        } else {
            B320_COMM_LOAN_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B400_SEND_MESSAGE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        WS_NEW_MAT_DATE = 0;
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_NEW_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        CEP.TRC(SCCGWA, WS_OLD_MAT_DATE);
        CEP.TRC(SCCGWA, WS_NEW_MAT_DATE);
        if (WS_OLD_MAT_DATE != WS_NEW_MAT_DATE) {
            B410_SEND_MESSAGE();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B410_SEND_MESSAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNCPLAJ.COMM_DATA.LN_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.AC = LNCPLAJ.COMM_DATA.LN_AC;
        SCCWRMSG.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        SCCWRMSG.AP_CODE = "26";
        JIBS_tmp_str[0] = "" + WS_NEW_MAT_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_NEW_MAT_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_NEW_MAT_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        CEP.TRC(SCCGWA, SCCWRMSG.AC);
        CEP.TRC(SCCGWA, SCCWRMSG.CI_NO);
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void B320_COMM_LOAN_PROCESS() throws IOException,SQLException,Exception {
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'P') {
            B320_COMM_PLPI_ADJUST();
            if (pgmRtn) return;
        } else if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            B322_COMM_PLPI_EXTADJ();
            if (pgmRtn) return;
    }
    public void B320_COMM_PLPI_ADJUST() throws IOException,SQLException,Exception {
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '1' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '2') {
            B321_P_PLPI_PRPADJ();
            if (pgmRtn) return;
            B360_ICTL_UPD_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '3') {
            B324_PLPI_EARLY_REPAY();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '5') {
            B390_AUTO_CHANGE_PAY_SCH();
            if (pgmRtn) return;
        }
    }
    public void B390_AUTO_CHANGE_PAY_SCH() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B392_COMP_UNDUE_DEL_TERM();
            if (pgmRtn) return;
        } else {
            B392_MERGE_ONE_TERM();
            if (pgmRtn) return;
        }
    }
    public void B392_MERGE_ONE_TERM() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        WS_ICTL_FLG = 'N';
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
        T00_STARTBR_LNTPLPI_6();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        if (WS_FND_FLG == 'Y') {
            WS_ICTL_FLG = 'Y';
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '4';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNRPLPI.KEY.CONTRACT_NO;
            LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = LNRPLPI.KEY.SUB_CTA_NO;
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNRPLPI.KEY.REPY_MTH;
            LNCPLPIM.REC_DATA.KEY.TERM = LNRPLPI.KEY.TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            LNCPLPIM.FUNC = '2';
            IBS.init(SCCGWA, LNRBALZ);
            LNRBALZ.KEY.CONTRACT_NO = LNRPLPI.KEY.CONTRACT_NO;
            LNRBALZ.KEY.SUB_CTA_NO = 0;
            T000_READ_LNTBALZ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL02);
            LNCPLPIM.REC_DATA.PRIN_AMT = LNRBALZ.LOAN_BALL02 + LNCPLAJ.COMM_DATA.PAY_P_AMT;
            LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPLPIM.REC_DATA.PRIN_AMT;
            LNCPLPIM.REC_DATA.DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
        }
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            T00_DELETE_LNTPLPI();
            if (pgmRtn) return;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        if (WS_ICTL_FLG == 'Y') {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            CEP.TRC(SCCGWA, " ");
            LNCICTLM.REC_DATA.P_CMP_TERM = (short) (1 + LNCICTLM.REC_DATA.P_CAL_TERM);
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B392_COMP_UNDUE_DEL_TERM() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
        WS_REM_AMT = LNCPLAJ.COMM_DATA.PAY_P_AMT;
        T00_STARTBR_LNTPLPI_6();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        if (WS_FND_FLG == 'Y') {
            WS_BEG_DT = LNRPLPI.VAL_DT;
        } else {
            WS_BEG_DT = LNCCONTM.REC_DATA.START_DATE;
        }
        while (WS_FND_FLG != 'N') {
            WS_TOT_UNDUE_TERM += 1;
            if (WS_REM_AMT >= 0) {
                WS_REM_AMT = WS_REM_AMT - LNRPLPI.PRIN_AMT;
                CEP.TRC(SCCGWA, WS_REM_AMT);
                if (WS_REM_AMT >= 0) {
                    CEP.TRC(SCCGWA, " ");
                    T00_DELETE_LNTPLPI();
                    if (pgmRtn) return;
                    WS_TOT_DEL_TERM += 1;
                }
            }
            if (WS_REM_AMT < 0) {
                WS_NEW_TOT_TERM += 1;
                WS_TEMP_VAL_DT = LNRPLPI.VAL_DT;
                WS_TEMP_PRIN = LNRPLPI.PRIN_AMT;
                if (WS_NEW_TOT_TERM == 1) {
                    WS_TEMP_VAL_DT = WS_BEG_DT;
                    WS_TEMP_PRIN = 0 - WS_REM_AMT;
                    WS_TEMP_DUE_DT = LNRPLPI.DUE_DT;
                }
                B394_WRITE_LNTSCHT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, " ");
                T00_DELETE_LNTPLPI();
                if (pgmRtn) return;
            }
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_UNDUE_TERM);
        CEP.TRC(SCCGWA, WS_TOT_DEL_TERM);
        CEP.TRC(SCCGWA, WS_NEW_TOT_TERM);
        if (WS_REM_AMT == 0) {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '0';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
            LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
            LNCPLPIM.REC_DATA.VAL_DT = WS_BEG_DT;
            LNCPLPIM.REC_DATA.DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
            LNCPLPIM.REC_DATA.REC_STS = 'N';
            LNCPLPIM.REC_DATA.DUE_REPY_AMT = 0;
            LNCPLPIM.REC_DATA.PRIN_AMT = 0;
            LNCPLPIM.REC_DATA.REMARK = "EARLY REPAY";
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            CEP.TRC(SCCGWA, " ");
            LNCICTLM.REC_DATA.P_CMP_TERM = (short) (LNCICTLM.REC_DATA.P_CAL_TERM + 1);
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = LNCCONTM.REC_DATA.MAT_DATE;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
        if (WS_NEW_TOT_TERM > 0) {
            B396_REBUILD_PLPI_INFO();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            CEP.TRC(SCCGWA, " ");
            LNCICTLM.REC_DATA.P_CMP_TERM = (short) (WS_NEW_TOT_TERM + LNCICTLM.REC_DATA.P_CAL_TERM);
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_TEMP_DUE_DT;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B394_WRITE_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'A';
        LNRSCHT.KEY.TRAN_SEQ = SCCGWA.COMM_AREA.JRN_NO;
        if (LNCPLAJ.COMM_DATA.LN_AC.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.LN_AC);
        LNRSCHT.KEY.SUB_CTA_NO = "" + 0;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = 'P';
        LNRSCHT.KEY.TERM = LNRPLPI.KEY.TERM;
        LNRSCHT.VAL_DTE = WS_TEMP_VAL_DT;
        LNRSCHT.DUE_DTE = LNRPLPI.DUE_DT;
        LNRSCHT.AMOUNT = WS_TEMP_PRIN;
        LNRSCHT.ACTION = 'A';
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void B396_REBUILD_PLPI_INFO() throws IOException,SQLException,Exception {
        WS_SCHT_FLAG = 'N';
        T000_STARTBR_LNTSCHT();
        if (pgmRtn) return;
        T000_READNEXT_LNTSCHT();
        if (pgmRtn) return;
        WS_TEMP_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        while (WS_SCHT_FLAG != 'Y') {
            B398_MOVE_SCHT_PLPI();
            if (pgmRtn) return;
            WS_TEMP_TERM += 1;
            T000_READNEXT_LNTSCHT();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTSCHT();
        if (pgmRtn) return;
    }
    public void B398_MOVE_SCHT_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '0';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = WS_TEMP_TERM;
        LNCPLPIM.REC_DATA.VAL_DT = LNRSCHT.VAL_DTE;
        LNCPLPIM.REC_DATA.DUE_DT = LNRSCHT.DUE_DTE;
        LNCPLPIM.REC_DATA.REC_STS = 'N';
        LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNRSCHT.AMOUNT;
        LNCPLPIM.REC_DATA.PRIN_AMT = LNRSCHT.AMOUNT;
        LNCPLPIM.REC_DATA.REMARK = "EARLY REPAY";
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B321_FIRST_PLPI_ADJUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        if (LNCICTLM.REC_DATA.P_CMP_TERM > LNCICTLM.REC_DATA.P_CAL_TERM) {
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            LNCPLPIM.FUNC = '2';
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'P') {
                LNCPLPIM.REC_DATA.PRIN_AMT -= LNCPLAJ.COMM_DATA.PAY_P_AMT;
            } else {
                LNCPLPIM.REC_DATA.PRIN_AMT += LNCPLAJ.COMM_DATA.PAY_P_AMT;
            }
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
        }
    }
    public void B322_LAST_PLPI_ADJUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = LNCICTLM.REC_DATA.P_CMP_TERM;
        LNCPLPIM.REC_DATA.KEY.TERM -= 1;
        if (LNCPLPIM.REC_DATA.KEY.TERM > 0 
            && LNCICTLM.REC_DATA.P_CMP_TERM > LNCICTLM.REC_DATA.P_CAL_TERM) {
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            LNCPLPIM.FUNC = '2';
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'P') {
                LNCPLPIM.REC_DATA.PRIN_AMT -= LNCPLAJ.COMM_DATA.PAY_P_AMT;
            } else {
                LNCPLPIM.REC_DATA.PRIN_AMT += LNCPLAJ.COMM_DATA.PAY_P_AMT;
            }
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
        }
    }
    public void B410_SPECIAL_LOAN_PROC() throws IOException,SQLException,Exception {
        B410_SPECIAL_P_PROC();
        if (pgmRtn) return;
        if (WS_CAL_PERD != 0 
            && WS_CAL_UNIT != ' ') {
            B370_PAY_MTH_I_PLAJ();
            if (pgmRtn) return;
        } else {
            B411_SPECIAL_I_PROC();
            if (pgmRtn) return;
        }
    }
    public void B321_P_PLPI_PRPADJ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = 1;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        LNCPLPIM.FUNC = '2';
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNCPLPIM.REC_DATA.PRIN_AMT -= LNCPLAJ.COMM_DATA.PAY_P_AMT;
            LNCPLPIM.REC_DATA.DUE_REPY_AMT -= LNCPLAJ.COMM_DATA.PAY_P_AMT;
        } else {
            LNCPLPIM.REC_DATA.PRIN_AMT += LNCPLAJ.COMM_DATA.PAY_P_AMT;
            LNCPLPIM.REC_DATA.DUE_REPY_AMT += LNCPLAJ.COMM_DATA.PAY_P_AMT;
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B324_PLPI_EARLY_REPAY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PRIN_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_REM_PRIN_AMT = WS_PRIN_AMT;
            CEP.TRC(SCCGWA, "REPAY CANCEL YES");
        }
        if (LNCPLAJ.COMM_DATA.SYN_FLG == '1') {
            B325_COMP_UNDUE_TERM();
            if (pgmRtn) return;
            if (WS_TOT_UNDUE_TERM != 0) {
                B326_UPDATE_UNDUE_TERM();
                if (pgmRtn) return;
                B360_ICTL_UPD_PROCESS();
                if (pgmRtn) return;
            }
        } else if (LNCPLAJ.COMM_DATA.SYN_FLG == '2') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (LNCPLAJ.COMM_DATA.OLD_DUE_DT != 0) {
                    WS_MAT_DATE = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
                    R000_UPDT_CONT_MAT_DATE();
                    if (pgmRtn) return;
                }
            } else {
                B327_UPDATE_TERM_DESC();
                if (pgmRtn) return;
                R000_UPDT_CONT_MAT_DATE();
                if (pgmRtn) return;
                R000_READ_UPDATE_ICTL();
                if (pgmRtn) return;
                R000_DEL_PLPI_DESC();
                if (pgmRtn) return;
                B361_ICTL_UPD_PROCESS();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYN_FLAG_MISSING, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B325_COMP_UNDUE_TERM() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        T00_STARTBR_LNTPLPI_1();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            WS_TOT_UNDUE_TERM += 1;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
    }
    public void B326_UPDATE_UNDUE_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_NRO_BAL);
        CEP.TRC(SCCGWA, WS_PAY_P_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_NRO_BAL = WS_NRO_BAL + WS_PAY_P_AMT;
        } else {
            WS_NRO_BAL = WS_NRO_BAL - WS_PAY_P_AMT;
        }
        WS_TERM_PRIN_AMT = WS_NRO_BAL / WS_TOT_UNDUE_TERM;
        bigD = new BigDecimal(WS_TERM_PRIN_AMT);
        WS_TERM_PRIN_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        WS_P_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        CEP.TRC(SCCGWA, WS_TOT_UNDUE_TERM);
        for (WS_J = 1; WS_J <= WS_TOT_UNDUE_TERM; WS_J += 1) {
            CEP.TRC(SCCGWA, WS_J);
            if (WS_J == WS_TOT_UNDUE_TERM) {
                WS_TERM_PRIN_AMT = WS_NRO_BAL - WS_TERM_TOT_PRIN_AMT;
            }
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '4';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
            LNCPLPIM.REC_DATA.KEY.TERM = WS_P_CAL_TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            LNCPLPIM.FUNC = '2';
            LNCPLPIM.REC_DATA.PRIN_AMT = WS_TERM_PRIN_AMT;
            LNCPLPIM.REC_DATA.DUE_REPY_AMT = WS_TERM_PRIN_AMT;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            WS_P_CAL_TERM += 1;
            WS_TERM_TOT_PRIN_AMT += WS_TERM_PRIN_AMT;
        }
    }
    public void B327_UPDATE_TERM_DESC() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        WS_ADJ_FLG = 'N';
        T00_STARTBR_LNTPLPI_2();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N' 
            && WS_ADJ_FLG != 'Y') {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '4';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNRPLPI.KEY.CONTRACT_NO;
            LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = LNRPLPI.KEY.SUB_CTA_NO;
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNRPLPI.KEY.REPY_MTH;
            LNCPLPIM.REC_DATA.KEY.TERM = LNRPLPI.KEY.TERM;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            if (WS_PAY_P_AMT > LNRPLPI.PRIN_AMT) {
                WS_PAY_P_AMT -= LNRPLPI.PRIN_AMT;
                LNCPLPIM.REC_DATA.PRIN_AMT = 0;
                LNCPLPIM.REC_DATA.DUE_REPY_AMT = 0;
            } else {
                LNCPLPIM.REC_DATA.PRIN_AMT -= WS_PAY_P_AMT;
                LNCPLPIM.REC_DATA.DUE_REPY_AMT -= WS_PAY_P_AMT;
                WS_ADJ_FLG = 'Y';
                WS_MAT_DATE = LNRPLPI.DUE_DT;
            }
            LNCPLPIM.FUNC = '2';
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        if (WS_MAT_DATE < LNCICTLM.REC_DATA.P_CAL_DUE_DT) {
            WS_MAT_DATE = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
        }
    }
    public void B322_COMM_PLPI_EXTADJ() throws IOException,SQLException,Exception {
        if (WS_PAY_MTH == '1' 
            || WS_PAY_MTH == '2') {
            B322_PI_PLPI_EXTADJ();
            if (pgmRtn) return;
        }
        if (WS_PAY_MTH == '3') {
            B322_PI_PLPI_RENEWALADJ();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '5') {
            B410_SPECIAL_LOAN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B322_PI_PLPI_EXTADJ() throws IOException,SQLException,Exception {
        WS_REPY_MTH = ' ';
        WS_REPY_MTH = 'P';
        if (LNCCONTM.REC_DATA.CTL_FLG == 'Y') {
            B322_PI_UPDPLPI_PROC();
            if (pgmRtn) return;
        } else {
            if (LNCPLAJ.COMM_DATA.PAY_P_AMT != 0) {
                B322_PI_ADDPLPI_PROC();
                if (pgmRtn) return;
            } else {
                B322_PI_UPDPLPI_PROC();
                if (pgmRtn) return;
            }
        }
        WS_REPY_MTH = ' ';
        WS_REPY_MTH = 'I';
        if (WS_PAY_MTH == '1' 
            && LNCPLAJ.COMM_DATA.ADJ_IND != 'N') {
            if (LNCCONTM.REC_DATA.CTL_FLG == 'Y') {
                B322_PI_UPDPLPI_PROC();
                if (pgmRtn) return;
            } else {
                if (LNCPLAJ.COMM_DATA.PAY_P_AMT != 0) {
                    B322_PI_ADDPLPI_PROC();
                    if (pgmRtn) return;
                } else {
                    B322_PI_UPDPLPI_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        B322_PI_ICTL_UPDATE();
        if (pgmRtn) return;
        if (WS_PAY_MTH == '2') {
            B370_PAY_MTH_I_PLAJ();
            if (pgmRtn) return;
        }
    }
    public void B322_PI_UPDPLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_MTH;
        if (LNCCONTM.REC_DATA.CTL_FLG == 'Y') {
            LNCPLPIM.REC_DATA.KEY.TERM = 1;
        } else {
            T000_READ_FIRST_LNTPLPI();
            if (pgmRtn) return;
            LNCPLPIM.REC_DATA.KEY.TERM = LNRPLPI.KEY.TERM;
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.DUE_DT = WS_MAT_DATE;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B322_PI_ADDPLPI_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        T000_READ_FIRST_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, LNCPLPIM);
            LNCPLPIM.FUNC = '0';
            LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_MTH;
            LNCPLPIM.REC_DATA.KEY.TERM = (short) (LNRPLPI.KEY.TERM + 1);
            LNCPLPIM.REC_DATA.VAL_DT = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
            LNCPLPIM.REC_DATA.DUE_DT = WS_MAT_DATE;
            LNCPLPIM.REC_DATA.REC_STS = '0';
            if (WS_REPY_MTH == 'P') {
                LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPLAJ.COMM_DATA.PAY_P_AMT;
                LNCPLPIM.REC_DATA.PRIN_AMT = LNCPLAJ.COMM_DATA.PAY_P_AMT;
            }
            LNCPLPIM.REC_DATA.REMARK = K_REMARK;
            S000_CALL_LNZPLPIM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.TERM);
        } else {
            B322_PI_DELPLPI_PROC();
            if (pgmRtn) return;
        }
    }
    public void B322_PI_DELPLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '1';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_MTH;
        LNCPLPIM.REC_DATA.KEY.TERM = LNRPLPI.KEY.TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B322_PI_ICTL_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCCONTM.REC_DATA.CTL_FLG == 'Y') {
            LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_MAT_DATE;
            LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_MAT_DATE;
            LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_MAT_DATE;
        } else {
            if (LNCPLAJ.COMM_DATA.PAY_P_AMT != 0) {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    LNCICTLM.REC_DATA.P_CMP_TERM += 1;
                    if (WS_PAY_MTH == '1') {
                        LNCICTLM.REC_DATA.IC_CMP_TERM += 1;
                        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
                        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_MAT_DATE;
                        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_MAT_DATE;
                        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_MAT_DATE;
                    }
                } else {
                    LNCICTLM.REC_DATA.P_CMP_TERM = (short) (LNCICTLM.REC_DATA.P_CMP_TERM - 1);
                    if (WS_PAY_MTH == '1') {
                        LNCICTLM.REC_DATA.IC_CMP_TERM = (short) (LNCICTLM.REC_DATA.IC_CMP_TERM - 1);
                        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_MAT_DATE;
                        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_MAT_DATE;
                        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_MAT_DATE;
                    }
                }
            } else {
                if (WS_PAY_MTH == '1') {
                    LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_MAT_DATE;
                    LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_MAT_DATE;
                    LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_MAT_DATE;
                }
            }
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B322_PI_PLPI_RENEWALADJ() throws IOException,SQLException,Exception {
        B380_PAY_MTH_P_PLAJ();
        if (pgmRtn) return;
        B370_PAY_MTH_I_PLAJ();
        if (pgmRtn) return;
    }
    public void B329_READ_PAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '3';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCICTLM.REC_DATA.KEY.CONTRACT_NO;
        LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = LNCICTLM.REC_DATA.KEY.SUB_CTA_NO;
        LNCPAIPM.REC_DATA.KEY.PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
        CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.KEY.PHS_NO);
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        if (LNCPAIPM.REC_DATA.PHS_PRIN_AMT < LNCCONTM.REC_DATA.LN_TOT_AMT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.PLEASE_CHANGED_PAY_SCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B330_PAIP_LOAN_PROCESS() throws IOException,SQLException,Exception {
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X' 
            && WS_PHS_NO > 1) {
            B350_DEL_OLDPLPI_PROCESS();
            if (pgmRtn) return;
            B340_PAIP_UPD_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_PHS_NO);
            if (LNCICTLM.REC_DATA.IC_CAL_PHS_NO > WS_PHS_ALL) {
                WS_PHS_NO2 = WS_PHS_ALL;
            } else {
                WS_PHS_NO2 = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
            }
            CEP.TRC(SCCGWA, WS_PHS_NO2);
            CEP.TRC(SCCGWA, WS_PHS_ALL);
            while (WS_PHS_NO2 <= WS_PHS_ALL) {
                B340_PAIP_UPD_PROCESS();
                if (pgmRtn) return;
                WS_PHS_NO2 += 1;
            }
            if (LNCPLAJ.COMM_DATA.ADJ_IND != 'X') {
                B360_ICTL_UPD_PROCESS();
                if (pgmRtn) return;
            }
            B350_DEL_OLDPLPI_PROCESS();
            if (pgmRtn) return;
        }
        LNCPLAJ.COMM_DATA.INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
        LNCPLAJ.COMM_DATA.INST_TERM = LNCILCM.COMM_DATA.TERM;
    }
    public void B310_INQ_PAY_PRIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCPLAJ.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCPLAJ.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_PAY_N_BAL = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        WS_NRO_BAL = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        CEP.TRC(SCCGWA, WS_PAY_N_BAL);
        if (WS_PAY_N_BAL == 0 
            && LNCPLAJ.COMM_DATA.ADJ_IND != 'X' 
            && LNCPLAJ.COMM_DATA.ADJ_IND != 'R' 
            && LNCPLAJ.COMM_DATA.ADJ_IND != 'P' 
            && LNCPLAJ.COMM_DATA.ADJ_IND != 'S') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GE_PAY_P_AMT, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B340_PAIP_UPD_PROCESS() throws IOException,SQLException,Exception {
        B341_READ_PAIP_UPDATE();
        if (pgmRtn) return;
        B342_INSTALMENT_PROCESS();
        if (pgmRtn) return;
        B343_UPD_PAIP_RECORD();
        if (pgmRtn) return;
    }
    public void B341_READ_PAIP_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '4';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        CEP.TRC(SCCGWA, WS_PHS_NO);
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            LNCPAIPM.REC_DATA.KEY.PHS_NO = WS_PHS_NO;
        } else {
            LNCPAIPM.REC_DATA.KEY.PHS_NO = WS_PHS_NO2;
        }
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.OLD_DUE_DT);
        if (LNCPLAJ.COMM_DATA.OLD_DUE_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_REM_PRIN_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
        } else {
            WS_REM_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT;
        }
        CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
        WS_PHS_TOT_TERM = LNCPAIPM.REC_DATA.PHS_TOT_TERM;
        WS_INST_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
        WS_CAL_PERD = LNCPAIPM.REC_DATA.PERD;
        WS_CAL_UNIT = LNCPAIPM.REC_DATA.PERD_UNIT;
        WS_INST_MTH = LNCPAIPM.REC_DATA.INST_MTH;
        WS_PHS_CAL_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
    }
    public void B342_INSTALMENT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INST_MTH);
        if (WS_INST_MTH == '1') {
            WS_FORM_CODE = "31";
        } else if (WS_INST_MTH == '2') {
            WS_FORM_CODE = "34";
        } else if (WS_INST_MTH == '3') {
            WS_FORM_CODE = "32";
        }
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'R' 
            || LNCPLAJ.COMM_DATA.ADJ_IND == 'S') {
            WS_P_AMT = 0;
        } else {
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'P') {
                WS_P_AMT = LNCPLAJ.COMM_DATA.PAY_P_AMT;
            } else {
                if (LNCPLAJ.COMM_DATA.ADJ_IND != 'X') {
                    WS_P_AMT = LNCPLAJ.COMM_DATA.PAY_P_AMT * ( -1 );
                }
            }
        }
        CEP.TRC(SCCGWA, WS_P_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_PRIN_AMT = WS_PAY_N_BAL + WS_P_AMT;
        } else {
            WS_PRIN_AMT = WS_PAY_N_BAL - WS_P_AMT;
        }
        CEP.TRC(SCCGWA, "AAAAAAAA");
        CEP.TRC(SCCGWA, WS_PAY_N_BAL);
        CEP.TRC(SCCGWA, WS_PRIN_AMT);
        CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
        if (WS_PRIN_AMT < WS_REM_PRIN_AMT 
            && LNCPLAJ.COMM_DATA.ADJ_IND != 'X') {
            WS_REM_PRIN_AMT = WS_PRIN_AMT;
        }
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            B400_GET_TERM_NUM();
            if (pgmRtn) return;
        }
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'P') {
            B342_PAIP_EARLY_REPAY();
            if (pgmRtn) return;
        } else {
            if (LNCPLAJ.COMM_DATA.ADJ_IND != 'X') {
                WS_INST_TERM = (short) (LNCPAIPM.REC_DATA.PHS_TOT_TERM - LNCPAIPM.REC_DATA.PHS_CAL_TERM);
            }
            CEP.TRC(SCCGWA, WS_INST_TERM);
            WS_INST_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
            if (LNCPLAJ.COMM_DATA.ADJ_IND != 'S' 
                && LNCPLAJ.COMM_DATA.SYN_FLG != '2') {
                B342_COMP_INSAMT_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B342_PAIP_EARLY_REPAY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PRIN_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_REM_PRIN_AMT = WS_PRIN_AMT;
            if (LNCPLAJ.COMM_DATA.SYN_FLG == ' ') {
                LNCPLAJ.COMM_DATA.SYN_FLG = '1';
            }
            CEP.TRC(SCCGWA, "REPAY CANCEL YES");
        }
        if (LNCPLAJ.COMM_DATA.SYN_FLG == '1') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_INST_TERM = WS_PHS_TOT_TERM;
            } else {
                WS_INST_TERM = (short) (WS_PHS_TOT_TERM - LNCPAIPM.REC_DATA.PHS_CAL_TERM);
            }
            CEP.TRC(SCCGWA, WS_INST_TERM);
            CEP.TRC(SCCGWA, WS_INST_AMT);
            CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
            B342_COMP_INSAMT_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_INST_AMT);
            CEP.TRC(SCCGWA, WS_INST_TERM);
        } else if (LNCPLAJ.COMM_DATA.SYN_FLG == '2') {
            CEP.TRC(SCCGWA, WS_INST_TERM);
            CEP.TRC(SCCGWA, WS_INST_AMT);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B342_01_UPDT_CONT_MAT_DATE();
                if (pgmRtn) return;
                B342_COMP_TOTTRM_PROCESS();
                if (pgmRtn) return;
            } else {
                B342_COMP_TOTTRM_PROCESS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_INST_TERM);
                CEP.TRC(SCCGWA, WS_INST_AMT);
                if (WS_INST_TERM == 1) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CANNOT_SUOQI;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                B342_01_UPDT_CONT_MAT_DATE();
                if (pgmRtn) return;
            }
        } else if (LNCPLAJ.COMM_DATA.SYN_FLG == '3') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B342_01_UPDT_CONT_MAT_DATE();
                if (pgmRtn) return;
                B344_FIX_CANCEL_PROCESS();
                if (pgmRtn) return;
            } else {
                B344_PLAJ_FIX_PROCESS();
                if (pgmRtn) return;
                B342_01_UPDT_CONT_MAT_DATE();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYN_FLAG_MISSING, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B344_FIX_CANCEL_PROCESS() throws IOException,SQLException,Exception {
        WS_INST_TERM = (short) (WS_PHS_TOT_TERM + LNCPLAJ.COMM_DATA.FIX_TERM);
        B342_COMP_INSAMT_PROCESS();
        if (pgmRtn) return;
    }
    public void B344_PLAJ_FIX_PROCESS() throws IOException,SQLException,Exception {
        WS_INST_TERM = (short) (WS_PHS_TOT_TERM - LNCPAIPM.REC_DATA.PHS_CAL_TERM);
        CEP.TRC(SCCGWA, WS_PHS_TOT_TERM);
        CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.PHS_CAL_TERM);
        CEP.TRC(SCCGWA, WS_INST_TERM);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.FIX_TERM);
        if (WS_INST_TERM < LNCPLAJ.COMM_DATA.FIX_TERM) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FIX_TERM_BIG, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_INST_TERM = (short) (WS_INST_TERM - LNCPLAJ.COMM_DATA.FIX_TERM);
        CEP.TRC(SCCGWA, WS_INST_TERM);
        CEP.TRC(SCCGWA, WS_INST_AMT);
        CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
        B342_COMP_INSAMT_PROCESS();
        if (pgmRtn) return;
    }
    public void B342_01_UPDT_CONT_MAT_DATE() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (LNCPLAJ.COMM_DATA.OLD_DUE_DT != 0) {
                WS_MAT_DATE = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
            } else {
            }
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_VAL_DT);
            SCCCLDT.DATE1 = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                SCCCLDT.MTHS = (short) (LNCAPRDM.REC_DATA.CAL_PERD * WS_INST_TERM);
            } else {
                SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD * WS_INST_TERM;
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_MAT_DATE = SCCCLDT.DATE2;
            if (LNCCONTM.REC_DATA.MAT_DATE < WS_MAT_DATE) {
                WS_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
            }
        }
        R000_UPDT_CONT_MAT_DATE();
        if (pgmRtn) return;
    }
    public void R000_UPDT_CONT_MAT_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        LNCCONTM.REC_DATA.MAT_DATE = WS_MAT_DATE;
        LNCCONTM.FUNC = '2';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B342_COMP_INSAMT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        IBS.init(SCCGWA, BPCQCCY);
        LNCILCM.COMM_DATA.FORM_CODE = WS_FORM_CODE;
        LNCILCM.COMM_DATA.FUNC_CODE = '1';
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            CEP.TRC(SCCGWA, WS_REM_PRIN_AMT);
            CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.PAY_P_AMT);
            LNCILCM.COMM_DATA.PRIN_AMT = WS_REM_PRIN_AMT;
        } else {
            LNCILCM.COMM_DATA.PRIN_AMT = WS_REM_PRIN_AMT;
        }
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.PRIN_AMT);
        LNCILCM.COMM_DATA.RATE = LNCICTLM.REC_DATA.CUR_RAT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(15 - 1, 15 + 1 - 1).equalsIgnoreCase("1")) {
            LNCILCM.COMM_DATA.RATE = LNCICTLM.REC_DATA.CUR_FO_RAT;
        }
        LNCILCM.COMM_DATA.PERIOD = WS_CAL_PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = WS_CAL_UNIT;
        LNCILCM.COMM_DATA.INCR_PERIOD = 0;
        LNCILCM.COMM_DATA.INCR_AMT = 0;
        LNCILCM.COMM_DATA.HANDLING_PERC = 0;
        BPCQCCY.DATA.CCY = LNCCONTM.REC_DATA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        LNCILCM.COMM_DATA.ROUND_MODE = BPCQCCY.DATA.RND_MTH;
        LNCILCM.COMM_DATA.CCY = LNCCONTM.REC_DATA.CCY;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNCILCM.COMM_DATA.TERM = (short) (WS_INST_TERM - WS_PHS_CAL_TERM);
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
                LNCILCM.COMM_DATA.TERM = (short) (LNCILCM.COMM_DATA.TERM + 1);
            }
        } else {
            LNCILCM.COMM_DATA.TERM = WS_INST_TERM;
            if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
                LNCILCM.COMM_DATA.TERM = (short) (LNCILCM.COMM_DATA.TERM + 1);
            }
        }
        CEP.TRC(SCCGWA, "LIUQ");
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.PRIN_AMT);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.TERM);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.ROUND_MODE);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.FORM_CODE);
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        WS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.INST_AMT);
        CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.RATE);
    }
    public void B342_COMP_TOTTRM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        LNCILCM.COMM_DATA.FORM_CODE = WS_FORM_CODE;
        LNCILCM.COMM_DATA.FUNC_CODE = '2';
        LNCILCM.COMM_DATA.PRIN_AMT = WS_REM_PRIN_AMT;
        LNCILCM.COMM_DATA.RATE = LNCICTLM.REC_DATA.CUR_RAT;
        LNCILCM.COMM_DATA.PERIOD = LNCAPRDM.REC_DATA.CAL_PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        LNCILCM.COMM_DATA.INCR_PERIOD = 0;
        LNCILCM.COMM_DATA.INCR_AMT = 0;
        LNCILCM.COMM_DATA.HANDLING_PERC = 0;
        LNCILCM.COMM_DATA.ROUND_MODE = '2';
        LNCILCM.COMM_DATA.CCY = LNCCONTM.REC_DATA.CCY;
        LNCILCM.COMM_DATA.INST_AMT = WS_INST_AMT;
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        WS_INST_TERM = LNCILCM.COMM_DATA.TERM;
    }
    public void B343_UPD_PAIP_RECORD() throws IOException,SQLException,Exception {
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                LNCPAIPM.FUNC = '2';
                if (WS_SPEC_LN_FLG != '1') {
                    LNCPAIPM.REC_DATA.PHS_TOT_TERM = (short) (LNCPAIPM.REC_DATA.PHS_CAL_TERM + WS_PAIP_TERM + 1);
                } else {
                    WS_EXTN_TOT_TERM = (short) (WS_PHS_CAL_TERM + WS_PAIP_TERM + 1);
                    CEP.TRC(SCCGWA, WS_EXTN_TOT_TERM);
                    CEP.TRC(SCCGWA, WS_PHS_TOT_TERM);
                    if (WS_EXTN_TOT_TERM > WS_PHS_TOT_TERM) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALLOON_LOAN, LNCPLAJ.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
                LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT + LNCPLAJ.COMM_DATA.PAY_P_AMT;
                LNCPAIPM.REC_DATA.PHS_CMP_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
                LNCPAIPM.REC_DATA.CUR_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
                LNCPAIPM.REC_DATA.CUR_INST_IRAT = LNCILCM.COMM_DATA.RATE;
                S000_CALL_LNZPAIPM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.INST_AMT);
                CEP.TRC(SCCGWA, LNCILCM.COMM_DATA.RATE);
            }
        } else {
            CEP.TRC(SCCGWA, WS_INST_TERM);
            LNCPAIPM.FUNC = '2';
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (LNCPLAJ.COMM_DATA.SYN_FLG == '1' 
                    || LNCPLAJ.COMM_DATA.SYN_FLG == '2') {
                    LNCPAIPM.REC_DATA.PHS_TOT_TERM = (short) (WS_INST_TERM + LNCPAIPM.REC_DATA.PHS_CAL_TERM);
                } else {
                    LNCPAIPM.REC_DATA.PHS_TOT_TERM = (short) (WS_INST_TERM);
                }
            } else {
                LNCPAIPM.REC_DATA.PHS_TOT_TERM = (short) (WS_INST_TERM + LNCPAIPM.REC_DATA.PHS_CAL_TERM);
            }
            LNCPAIPM.REC_DATA.PHS_CMP_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
            if (LNCPLAJ.COMM_DATA.ADJ_IND != 'S' 
                && LNCPLAJ.COMM_DATA.SYN_FLG != '2') {
                LNCPAIPM.REC_DATA.CUR_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
                LNCPAIPM.REC_DATA.CUR_INST_IRAT = LNCILCM.COMM_DATA.RATE;
            }
            LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT = WS_REM_PRIN_AMT;
            S000_CALL_LNZPAIPM();
            if (pgmRtn) return;
        }
    }
    public void B350_DEL_OLDPLPI_PROCESS() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.IC_CMP_TERM > LNCICTLM.REC_DATA.IC_CAL_TERM) {
            B351_DEL_OLDPLN_PROCESS();
            if (pgmRtn) return;
        }
        if (WS_DELPI_FLG == 'Y') {
            B352_UOD_DELICTL_PROCESS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_IC_CAL_DUE_DT);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.OLD_DUE_DT);
        if (LNCPLAJ.COMM_DATA.ADJ_IND == 'X' 
            && WS_IC_CAL_DUE_DT == LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
            CEP.TRC(SCCGWA, "YE000");
            if (WS_PHS_NO > 1) {
                B351_DEL_OLDPLN_PROCESS();
                if (pgmRtn) return;
                B353_GET_VAL_DATE_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
            if (WS_FIRST_E_DATE != 0) {
                LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_FIRST_E_DATE;
                LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_FIRST_E_DATE;
            }
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_PHS_NO);
            LNCICTLM.REC_DATA.IC_CMP_PHS_NO = (short) (LNCICTLM.REC_DATA.IC_CMP_PHS_NO - 1);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_PHS_NO);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B351_DEL_OLDPLN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PHS_NO);
        if (WS_PHS_NO > 1 
            && LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            WS_FND_FLG = 'N';
            T00_STARTBR_LNTPLPI();
            if (pgmRtn) return;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            while (WS_FND_FLG != 'N' 
                && WS_PHS_CMP_TERM != 0 
                && LNRPLPI.KEY.TERM >= LNCICTLM.REC_DATA.IC_CAL_TERM) {
                T00_DELETE_LNTPLPI();
                if (pgmRtn) return;
                WS_DELPI_FLG = 'Y';
                WS_TERM = 0;
                WS_STR_DT = 0;
                WS_END_DT = 0;
                WS_PHS_CMP_TERM = (short) (WS_PHS_CMP_TERM - 1);
                T00_READNEXT_LNTPLPI();
                if (pgmRtn) return;
                WS_TERM = LNRPLPI.KEY.TERM;
                WS_STR_DT = LNRPLPI.VAL_DT;
                WS_END_DT = LNRPLPI.DUE_DT;
            }
            if (WS_DELPI_FLG != 'Y') {
                WS_TERM = LNRPLPI.KEY.TERM;
                WS_STR_DT = LNRPLPI.VAL_DT;
                WS_END_DT = LNRPLPI.DUE_DT;
            }
            T00_ENDBR_LNTPLPI();
            if (pgmRtn) return;
        } else {
            WS_FND_FLG = 'N';
            T00_STARTBR_LNTPLPI();
            if (pgmRtn) return;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            while (WS_FND_FLG != 'N') {
                T00_DELETE_LNTPLPI();
                if (pgmRtn) return;
                WS_DELPI_FLG = 'Y';
                T00_READNEXT_LNTPLPI();
                if (pgmRtn) return;
            }
            T00_ENDBR_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void B352_UOD_DELICTL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        CEP.TRC(SCCGWA, WS_PHS_TOT_TERM2);
        CEP.TRC(SCCGWA, WS_PHS_CMP_TERM);
        if (WS_PHS_NO > 1 
            && LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            B353_GET_VAL_DATE_PROC();
            if (pgmRtn) return;
            if (WS_DELPI_FLG == 'Y') {
                CEP.TRC(SCCGWA, WS_TERM);
                LNCICTLM.REC_DATA.IC_CMP_TERM = (short) (WS_TERM + 1);
            }
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_PHS_NO);
            CEP.TRC(SCCGWA, WS_PHS_NO);
            if (LNCICTLM.REC_DATA.IC_CMP_PHS_NO > WS_PHS_NO) {
                LNCICTLM.REC_DATA.IC_CMP_PHS_NO = WS_PHS_NO;
            }
            if (LNCICTLM.REC_DATA.IC_CMP_DUE_DT == LNCICTLM.REC_DATA.IC_CAL_DUE_DT 
                || LNCICTLM.REC_DATA.IC_CAL_DUE_DT > WS_MAT_DATE) {
                LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_FIRST_E_DATE;
            }
            LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_FIRST_S_DATE;
            LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_FIRST_E_DATE;
            CEP.TRC(SCCGWA, WS_PHS_CMP_TERM);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_PHS_NO);
        } else {
            LNCICTLM.REC_DATA.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            LNCICTLM.REC_DATA.IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
            LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
            LNCICTLM.REC_DATA.IC_CMP_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
            CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B353_GET_VAL_DATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.OLD_DUE_DT);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_CAL_PERD);
        CEP.TRC(SCCGWA, WS_CAL_UNIT);
        IBS.init(SCCGWA, SCCCLDT);
        WS_FIRST_S_DATE = 0;
        WS_FIRST_E_DATE = 0;
        SCCCLDT.DATE1 = WS_END_DT;
        WS_FIRST_S_DATE = WS_END_DT;
        if (LNCPLAJ.COMM_DATA.OLD_DUE_DT < SCCGWA.COMM_AREA.AC_DATE) {
            SCCCLDT.DATE1 = WS_STR_DT;
            WS_FIRST_S_DATE = WS_STR_DT;
        }
        if (WS_CAL_UNIT == 'D') {
            SCCCLDT.DAYS = WS_CAL_PERD;
        }
        if (WS_CAL_UNIT == 'M') {
            SCCCLDT.MTHS = WS_CAL_PERD;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        SCCCLDT.DATE1 = SCCCLDT.DATE2;
        SCCCLDT.DATE2 = 0;
        while (SCCCLDT.DATE1 < SCCGWA.COMM_AREA.AC_DATE) {
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            SCCCLDT.DATE1 = SCCCLDT.DATE2;
            SCCCLDT.DATE2 = 0;
        }
        WS_FIRST_E_DATE = SCCCLDT.DATE1;
        if (WS_FIRST_E_DATE > WS_MAT_DATE) {
            WS_FIRST_E_DATE = WS_MAT_DATE;
        }
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
    }
    public void B360_ICTL_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 56 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(56 + 1 - 1);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B361_ICTL_UPD_PROCESS() throws IOException,SQLException,Exception {
        LNCICTLM.FUNC = '2';
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 56 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(56 + 1 - 1);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B370_PAY_MTH_I_PLAJ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_IC_CMP_TERM);
        IBS.init(SCCGWA, LNRPLPI);
        LNCPLPIM.FUNC = '3';
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'I';
        LNRPLPI.KEY.TERM = (short) (WS_IC_CMP_TERM - 1);
        T000_READ_LNTPLPI_BY_KEY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        WS_TERM = 0;
        WS_STR_DT = 0;
        WS_END_DT = 0;
        WS_TERM = LNRPLPI.KEY.TERM;
        WS_STR_DT = LNRPLPI.VAL_DT;
        WS_END_DT = LNRPLPI.DUE_DT;
        WS_REPY_MTH = 'I';
        if (WS_MAT_DATE > LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
            B460_CUT_PLPI_PROC();
            if (pgmRtn) return;
        } else {
            B461_CUT_PLPI_CANCEL_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "0000000");
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        if (LNRPLPI.DUE_DT < WS_MAT_DATE 
            && LNRPLPI.DUE_DT != 0) {
            B371_PAY_MTH_I_PLAJ();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                IBS.init(SCCGWA, LNCICTLM);
                LNCICTLM.FUNC = '4';
                LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
                if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
                else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
                LNCICTLM.FUNC = '2';
                CEP.TRC(SCCGWA, "UPDATE I  ICTL-------------------");
                LNCICTLM.REC_DATA.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
                LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_MAT_DATE;
                LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_MAT_DATE;
                LNCICTLM.REC_DATA.IC_CAL_VAL_DT = WS_MAT_DATE;
                LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_MAT_DATE;
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
            }
        }
    }
    public void B460_CUT_PLPI_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_REPY_MTH;
        T00_STARTBR_LNTPLPI_3();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        WS_DEL_FLG = ' ';
        if (WS_REPY_MTH == 'I') {
            while (WS_FND_FLG != 'N' 
                && LNRPLPI.DUE_DT > WS_IC_CAL_VAL_DT) {
                CEP.TRC(SCCGWA, "I DEL 00000000");
                WS_DEL_FLG = 'Y';
                T00_DELETE_LNTPLPI();
                if (pgmRtn) return;
                WS_PLPI_TERM = LNRPLPI.KEY.TERM;
                T00_READNEXT_LNTPLPI();
                if (pgmRtn) return;
            }
            WS_STR_DT = LNRPLPI.VAL_DT;
            WS_END_DT = LNRPLPI.DUE_DT;
            CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
            CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
            CEP.TRC(SCCGWA, WS_DEL_FLG);
        } else {
            while (WS_FND_FLG != 'N' 
                && LNRPLPI.DUE_DT > WS_END_DT) {
                CEP.TRC(SCCGWA, "P DEL 00000000");
                WS_DEL_FLG = 'Y';
                T00_DELETE_LNTPLPI();
                if (pgmRtn) return;
                WS_PLPI_TERM = LNRPLPI.KEY.TERM;
                T00_READNEXT_LNTPLPI();
                if (pgmRtn) return;
            }
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PLPI_TERM);
    }
    public void B461_CUT_PLPI_CANCEL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        CEP.TRC(SCCGWA, WS_IC_CAL_VAL_DT);
        CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        CEP.TRC(SCCGWA, WS_MAT_DATE);
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_REPY_MTH;
        T00_STARTBR_LNTPLPI_3();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N' 
            && LNRPLPI.DUE_DT > WS_IC_CAL_VAL_DT) {
            CEP.TRC(SCCGWA, "I DEL 00000000");
            WS_DEL_FLG = 'Y';
            T00_DELETE_LNTPLPI();
            if (pgmRtn) return;
            WS_PLPI_TERM = LNRPLPI.KEY.TERM;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            WS_STR_DT = LNRPLPI.VAL_DT;
            WS_END_DT = LNRPLPI.DUE_DT;
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_PLPI_TERM);
    }
    public void B371_PAY_MTH_I_PLAJ() throws IOException,SQLException,Exception {
        B420_GET_TERM_PORC();
        if (pgmRtn) return;
        if (LNCSCALT.OUTPUT.INST_TERM == 1) {
            WS_FIRST_S_DATE = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
            WS_FIRST_E_DATE = WS_MAT_DATE;
        } else {
            if (WS_MAT_DATE > LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                B470_GET_FIRST_DTAE();
                if (pgmRtn) return;
            } else {
                B471_GET_CANCEL_FIRST_DTAE();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_FIRST_S_DATE = WS_STR_DT;
            WS_FIRST_E_DATE = WS_END_DT;
        }
        CEP.TRC(SCCGWA, WS_FIRST_S_DATE);
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
        B462_AFTER_CUT_PLPI_U_ICTL();
        if (pgmRtn) return;
    }
    public void B471_GET_CANCEL_FIRST_DTAE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.OLD_DUE_DT);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_CAL_TERM);
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        CEP.TRC(SCCGWA, WS_PLPI_TERM);
        CEP.TRC(SCCGWA, WS_IC_CMP_TERM);
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        if (WS_REPY_MTH == 'I') {
            if (WS_PLPI_TERM != 0) {
                WS_CHANGE_TERM = WS_PLPI_TERM;
            } else {
                WS_CHANGE_TERM = WS_IC_CAL_TERM;
            }
        } else {
            WS_CHANGE_TERM = WS_CAL_TERM;
        }
        CEP.TRC(SCCGWA, WS_CHANGE_TERM);
        if (WS_CHANGE_TERM > 1) {
            CEP.TRC(SCCGWA, "11111");
            if (WS_END_DT == LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                if (WS_DEL_FLG == 'Y') {
                    WS_FIRST_S_DATE = WS_STR_DT;
                } else {
                    WS_FIRST_S_DATE = WS_END_DT;
                }
                SCCCLDT.DATE1 = WS_STR_DT;
                if (WS_PAY_PERD > 1) {
                    WS_PAY_PERD = (short) (WS_PAY_PERD + 1);
                }
                if (WS_CAL_PERD > 1) {
                    WS_CAL_PERD = (short) (WS_CAL_PERD + 1);
                }
            } else {
                SCCCLDT.DATE1 = WS_END_DT;
                WS_FIRST_S_DATE = WS_END_DT;
            }
            CEP.TRC(SCCGWA, WS_REPY_MTH);
            if (WS_REPY_MTH == 'P') {
                if (WS_PAY_UNIT == 'D') {
                    SCCCLDT.DAYS = WS_PAY_PERD;
                }
                if (WS_PAY_UNIT == 'M') {
                    SCCCLDT.MTHS = WS_PAY_PERD;
                }
            } else {
                if (WS_CAL_UNIT == 'D') {
                    SCCCLDT.DAYS = WS_CAL_PERD;
                }
                if (WS_CAL_UNIT == 'M') {
                    SCCCLDT.MTHS = WS_CAL_PERD;
                }
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_FIRST_E_DATE = SCCCLDT.DATE2;
        } else {
            CEP.TRC(SCCGWA, "22222");
            WS_FIRST_S_DATE = WS_START_DATE;
            if (WS_REPY_MTH == 'P') {
                WS_FIRST_E_DATE = WS_FST_PAYP_DT;
            } else {
                WS_FIRST_E_DATE = WS_CAL_FST_DT;
            }
        }
        if (WS_FIRST_E_DATE > WS_MAT_DATE) {
            WS_FIRST_E_DATE = WS_MAT_DATE;
        }
        CEP.TRC(SCCGWA, WS_FIRST_S_DATE);
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
    }
    public void B470_GET_FIRST_DTAE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.OLD_DUE_DT);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_CAL_TERM);
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        CEP.TRC(SCCGWA, WS_PLPI_TERM);
        CEP.TRC(SCCGWA, WS_IC_CMP_TERM);
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, WS_REPY_MTH);
        if (WS_REPY_MTH == 'I') {
            if (WS_PLPI_TERM != 0) {
                WS_CHANGE_TERM = WS_PLPI_TERM;
            } else {
                WS_CHANGE_TERM = WS_IC_CAL_TERM;
            }
        } else {
            WS_CHANGE_TERM = WS_CAL_TERM;
        }
        CEP.TRC(SCCGWA, WS_CHANGE_TERM);
        if (WS_CHANGE_TERM > 1) {
            CEP.TRC(SCCGWA, "11111");
            if (WS_END_DT == LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                if (WS_DEL_FLG == 'Y') {
                    WS_FIRST_S_DATE = WS_STR_DT;
                } else {
                    WS_FIRST_S_DATE = WS_END_DT;
                }
                SCCCLDT.DATE1 = WS_STR_DT;
                if (WS_PAY_PERD > 1) {
                    WS_PAY_PERD = (short) (WS_PAY_PERD + 1);
                }
                if (WS_CAL_PERD > 1) {
                    WS_CAL_PERD = (short) (WS_CAL_PERD + 1);
                }
            } else {
                SCCCLDT.DATE1 = WS_END_DT;
                WS_FIRST_S_DATE = WS_END_DT;
            }
            CEP.TRC(SCCGWA, WS_REPY_MTH);
            if (WS_REPY_MTH == 'P') {
                if (WS_PAY_UNIT == 'D') {
                    SCCCLDT.DAYS = WS_PAY_PERD;
                }
                if (WS_PAY_UNIT == 'M') {
                    SCCCLDT.MTHS = WS_PAY_PERD;
                }
            } else {
                if (WS_CAL_UNIT == 'D') {
                    SCCCLDT.DAYS = WS_CAL_PERD;
                }
                if (WS_CAL_UNIT == 'M') {
                    SCCCLDT.MTHS = WS_CAL_PERD;
                }
            }
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            SCCCLDT.DATE1 = SCCCLDT.DATE2;
            SCCCLDT.DATE2 = 0;
            while (SCCCLDT.DATE1 <= SCCGWA.COMM_AREA.AC_DATE) {
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                SCCCLDT.DATE1 = SCCCLDT.DATE2;
                SCCCLDT.DATE2 = 0;
            }
            WS_FIRST_E_DATE = SCCCLDT.DATE1;
        } else {
            CEP.TRC(SCCGWA, "22222");
            WS_FIRST_S_DATE = WS_START_DATE;
            if (WS_REPY_MTH == 'P') {
                WS_FIRST_E_DATE = WS_FST_PAYP_DT;
            } else {
                WS_FIRST_E_DATE = WS_CAL_FST_DT;
            }
        }
        if (WS_FIRST_E_DATE > WS_MAT_DATE) {
            WS_FIRST_E_DATE = WS_MAT_DATE;
        }
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
    }
    public void B462_AFTER_CUT_PLPI_U_ICTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PLPI_TERM);
        CEP.TRC(SCCGWA, WS_FIRST_S_DATE);
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (WS_REPY_MTH == 'I') {
            CEP.TRC(SCCGWA, "UPDATE I  ICTL-------------------");
            LNCICTLM.REC_DATA.IC_CMP_TERM = WS_IC_CAL_TERM;
            LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_FIRST_S_DATE;
            LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_FIRST_E_DATE;
            if (LNCICTLM.REC_DATA.IC_CAL_DUE_DT == LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                LNCICTLM.REC_DATA.IC_CAL_VAL_DT = WS_FIRST_S_DATE;
                LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_FIRST_E_DATE;
            }
        } else {
            CEP.TRC(SCCGWA, "UPDATE P  ICTL-------------------");
            LNCICTLM.REC_DATA.P_CMP_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
            LNCICTLM.REC_DATA.P_CMP_DUE_DT = WS_FIRST_E_DATE;
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_FIRST_E_DATE;
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B380_PAY_MTH_P_PLAJ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNCPLPIM.FUNC = '3';
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.KEY.TERM = (short) (WS_CAL_TERM - 1);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
        T000_READ_LNTPLPI_BY_KEY();
        if (pgmRtn) return;
        WS_TERM = 0;
        WS_STR_DT = 0;
        WS_END_DT = 0;
        WS_TERM = LNRPLPI.KEY.TERM;
        WS_STR_DT = LNRPLPI.VAL_DT;
        WS_END_DT = LNRPLPI.DUE_DT;
        WS_REPY_MTH = 'P';
        B460_CUT_PLPI_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        if (LNRPLPI.DUE_DT != WS_MAT_DATE) {
            B381_PAY_MTH_P_PLAJ();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                IBS.init(SCCGWA, LNCICTLM);
                LNCICTLM.FUNC = '4';
                LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
                if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
                else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
                LNCICTLM.FUNC = '2';
                CEP.TRC(SCCGWA, "UPDATE P  ICTL-------------------");
                LNCICTLM.REC_DATA.P_CMP_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
                LNCICTLM.REC_DATA.P_CMP_DUE_DT = WS_MAT_DATE;
                LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_MAT_DATE;
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
            }
        }
    }
    public void B381_PAY_MTH_P_PLAJ() throws IOException,SQLException,Exception {
        B420_GET_TERM_PORC();
        if (pgmRtn) return;
        if (LNCSCALT.OUTPUT.INST_TERM == 1) {
            WS_FIRST_S_DATE = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
            WS_FIRST_E_DATE = WS_MAT_DATE;
        } else {
            if (WS_MAT_DATE > LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                B470_GET_FIRST_DTAE();
                if (pgmRtn) return;
            } else {
                B471_GET_CANCEL_FIRST_DTAE();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_FIRST_S_DATE = WS_STR_DT;
            WS_FIRST_E_DATE = WS_END_DT;
        }
        CEP.TRC(SCCGWA, WS_FIRST_S_DATE);
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
        B462_AFTER_CUT_PLPI_U_ICTL();
        if (pgmRtn) return;
    }
    public void B420_GET_TERM_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCALT);
        LNCSCALT.INPUT.VAL_DT = WS_START_DATE;
        LNCSCALT.INPUT.DUE_DT = WS_MAT_DATE;
        LNCSCALT.INPUT.PHS_FLG = 'N';
        LNCSCALT.INPUT.PHS_NUM = 0;
        if (WS_REPY_MTH == 'P') {
            LNCSCALT.INPUT.CAL_PERD_UNIT = WS_PAY_UNIT;
            LNCSCALT.INPUT.CAL_PERD = WS_PAY_PERD;
        } else {
            LNCSCALT.INPUT.CAL_PERD_UNIT = WS_CAL_UNIT;
            LNCSCALT.INPUT.CAL_PERD = WS_CAL_PERD;
        }
        LNCSCALT.INPUT.CAL_PAY_DAY = (short) WS_CAL_PAY_DAY;
        LNCSCALT.INPUT.CAL_FST_FLG = WS_CAL_FST_FLG;
        LNCSCALT.INPUT.REPY_MTH = WS_PAY_MTH;
        S000_CALL_LNZSCALT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSCALT.OUTPUT.INST_TERM);
    }
    public void B400_GET_TERM_NUM() throws IOException,SQLException,Exception {
        if (WS_CNT > K_CNT) {
            B430_GET_PHS_DATE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, WS_LAST_DT);
            LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
            else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            LNRPLPI.KEY.REPY_MTH = 'C';
            WS_REPY_MTH = 'C';
            LNRPLPI.KEY.TERM = (short) (WS_CAL_TERM - 1);
            T000_READ_LNTPLPI_BY_KEY();
            if (pgmRtn) return;
            WS_TERM = 0;
            WS_STR_DT = 0;
            WS_END_DT = 0;
            if (WS_FND_FLG == 'Y') {
                WS_TERM = LNRPLPI.KEY.TERM;
                WS_STR_DT = LNRPLPI.VAL_DT;
                WS_END_DT = LNRPLPI.DUE_DT;
            } else {
                WS_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
                WS_STR_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
                WS_END_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
            }
            if (WS_MAT_DATE > LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                B470_GET_FIRST_DTAE();
                if (pgmRtn) return;
            } else {
                B471_GET_CANCEL_FIRST_DTAE();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_FIRST_E_DATE = WS_STR_DT;
        }
        CEP.TRC(SCCGWA, WS_FIRST_E_DATE);
        CEP.TRC(SCCGWA, WS_LAST_DT);
        IBS.init(SCCGWA, LNCSCALT);
        if (WS_CNT > K_CNT 
            && WS_FIRST_E_DATE < WS_LAST_DT) {
            LNCSCALT.INPUT.VAL_DT = WS_LAST_DT;
        } else {
            LNCSCALT.INPUT.VAL_DT = WS_FIRST_E_DATE;
        }
        CEP.TRC(SCCGWA, LNCSCALT.INPUT.VAL_DT);
        LNCSCALT.INPUT.DUE_DT = WS_MAT_DATE;
        LNCSCALT.INPUT.PHS_FLG = 'N';
        LNCSCALT.INPUT.PHS_NUM = 0;
        LNCSCALT.INPUT.CAL_PERD_UNIT = WS_CAL_UNIT;
        LNCSCALT.INPUT.CAL_PERD = WS_CAL_PERD;
        LNCSCALT.INPUT.CAL_PAY_DAY = (short) WS_CAL_PAY_DAY;
        LNCSCALT.INPUT.CAL_FST_FLG = WS_CAL_FST_FLG;
        LNCSCALT.INPUT.REPY_MTH = WS_PAY_MTH;
        S000_CALL_LNZSCALT();
        if (pgmRtn) return;
        WS_PAIP_TERM = (short) LNCSCALT.OUTPUT.INST_TERM;
        WS_INST_TERM = (short) LNCSCALT.OUTPUT.INST_TERM;
        CEP.TRC(SCCGWA, WS_PAIP_TERM);
        CEP.TRC(SCCGWA, WS_INST_TERM);
    }
    public void B410_SPECIAL_P_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "YE000");
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.DUE_DT = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        T000_READ_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
            SCCCLDT.DATE2 = WS_MAT_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DAYS = SCCCLDT.DAYS;
            CEP.TRC(SCCGWA, WS_DAYS);
            T00_STARTBR_LNTPLPI_5();
            if (pgmRtn) return;
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
            while (WS_FND_FLG != 'N') {
                T00_READUP_LNTPLPI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
                CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DAYS = WS_DAYS;
                SCCCLDT.DATE1 = LNRPLPI.VAL_DT;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                if (LNRPLPI.KEY.TERM != 1) {
                    LNRPLPI.VAL_DT = SCCCLDT.DATE2;
                }
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                CEP.TRC(SCCGWA, WS_IC_CAL_VAL_DT);
                CEP.TRC(SCCGWA, LNRPLPI.VAL_DT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DAYS = WS_DAYS;
                SCCCLDT.DATE1 = LNRPLPI.DUE_DT;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                LNRPLPI.DUE_DT = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                CEP.TRC(SCCGWA, WS_IC_CAL_VAL_DT);
                T00_REWRITE_LNTPLPI();
                if (pgmRtn) return;
                T00_READNEXT_LNTPLPI();
                if (pgmRtn) return;
            }
            T00_ENDBR_LNTPLPI();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
            if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "UPDATE ICTL-------------------");
            CEP.TRC(SCCGWA, WS_MAT_DATE);
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_MAT_DATE;
            LNCICTLM.REC_DATA.P_CMP_DUE_DT = WS_MAT_DATE;
            LNCICTLM.FUNC = '2';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B411_SPECIAL_I_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'I';
        LNRPLPI.DUE_DT = LNCPLAJ.COMM_DATA.OLD_DUE_DT;
        T00_READUP_LNTPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LNRPLPI.REC_STS != '1') {
                LNRPLPI.DUE_DT = WS_MAT_DATE;
                T00_REWRITE_LNTPLPI();
                if (pgmRtn) return;
            }
        }
    }
    public void B430_GET_PHS_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "LNINST";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        WS_SEQ_NO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, WS_SEQ_NO);
        IBS.init(SCCGWA, LNCSPAIP);
        LNCSPAIP.REC_DATA.KEY.LN_AC = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCSPAIP.REC_DATA.KEY.SUF_NO = 0;
        else LNCSPAIP.REC_DATA.KEY.SUF_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNCSPAIP.FUNC = '5';
        LNCSPAIP.REC_DATA.TRAN_SEQ = WS_SEQ_NO;
        LNCSPAIP.REC_DATA.WRITE_TMPP_FLG = 'Y';
        S000_CALL_LNZSPAIP();
        if (pgmRtn) return;
        WS_LAST_DT = LNCSPAIP.REC_DATA.LAST_TERM_VAL_DT;
        if (WS_MAT_DATE <= WS_LAST_DT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REDATE_ERROR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B450_GET_CHANGE_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_TERM);
        if (WS_CAL_TERM == 1) {
            WS_P_CHANGE_DT = WS_START_DATE;
        } else {
            if (LNRPLPI.DUE_DT == LNCPLAJ.COMM_DATA.OLD_DUE_DT) {
                WS_P_CHANGE_DT = LNRPLPI.VAL_DT;
            } else {
                WS_P_CHANGE_DT = LNRPLPI.DUE_DT;
            }
        }
        CEP.TRC(SCCGWA, WS_P_CHANGE_DT);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_P_CHANGE_DT;
        if (WS_PAY_UNIT == 'D') {
            SCCCLDT.DAYS = WS_PAY_PERD;
        }
        if (WS_PAY_UNIT == 'M') {
            SCCCLDT.MTHS = WS_PAY_PERD;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        SCCCLDT.DATE1 = SCCCLDT.DATE2;
        SCCCLDT.DATE2 = 0;
        while (SCCCLDT.DATE1 <= SCCGWA.COMM_AREA.AC_DATE) {
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            SCCCLDT.DATE1 = SCCCLDT.DATE2;
            SCCCLDT.DATE2 = 0;
        }
        if (SCCCLDT.DATE1 > WS_MAT_DATE) {
            WS_P_CHANGE_DT = WS_MAT_DATE;
        } else {
            WS_P_CHANGE_DT = SCCCLDT.DATE1;
        }
        CEP.TRC(SCCGWA, WS_P_CHANGE_DT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCPLAJ.RC.RC_APP = "SC";
            LNCPLAJ.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_UPDATE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void R000_DEL_PLPI_DESC() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        T00_STARTBR_LNTPLPI_4();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N' 
            && LNRPLPI.DUE_DT > WS_MAT_DATE) {
            CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
            CEP.TRC(SCCGWA, LNRPLPI.REC_STS);
            if (LNRPLPI.REC_STS != '1') {
                T00_DELETE_LNTPLPI();
                if (pgmRtn) return;
            }
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        if (LNRPLPI.KEY.REPY_MTH == 'P') {
            LNCICTLM.REC_DATA.P_CMP_TERM = (short) (LNRPLPI.KEY.TERM + 1);
        } else {
            LNCICTLM.REC_DATA.IC_CMP_TERM = (short) (LNRPLPI.KEY.TERM + 1);
        }
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        if (WS_FND_FLG == 'Y' 
            && LNRPLPI.DUE_DT >= WS_MAT_DATE) {
            if (LNRPLPI.KEY.REPY_MTH == 'P') {
                LNCICTLM.REC_DATA.P_CMP_TERM = (short) (LNRPLPI.KEY.TERM + 1);
            } else {
                LNCICTLM.REC_DATA.IC_CMP_TERM = (short) (LNRPLPI.KEY.TERM + 1);
            }
        }
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
    }
    public void T000_READ_FIRST_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = WS_REPY_MTH;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "TERM DESC";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPAIP.KEY.SUB_CTA_NO = 0;
        else LNRPAIP.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.eqWhere = "CONTRACT_NO, SUB_CTA_NO";
        LNTPAIP_RD.fst = true;
        LNTPAIP_RD.order = "PHS_NO DESC";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_BR.rp.order = "TERM DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI_3() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.upd = true;
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_BR.rp.order = "TERM DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI_4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.DUE_DT = WS_MAT_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.upd = true;
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "DUE_DT >= :LNRPLPI.DUE_DT";
        LNTPLPI_BR.rp.order = "DUE_DT DESC,REPY_MTH DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI_5() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.REC_STS = '1';
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_BR.rp.where = "REC_STS < > :LNRPLPI.REC_STS";
        LNTPLPI_BR.rp.order = "TERM DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI_6() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.upd = true;
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        LNRPLPI.KEY.CONTRACT_NO = LNCPLAJ.COMM_DATA.LN_AC;
        if (LNCPLAJ.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'C';
        CEP.TRC(SCCGWA, WS_PHS_NO);
        CEP.TRC(SCCGWA, WS_PHS_CMP_TERM);
        if (WS_PHS_NO > 1 
            && LNCPLAJ.COMM_DATA.ADJ_IND == 'X') {
            CEP.TRC(SCCGWA, "YE1111111");
            LNTPLPI_BR.rp = new DBParm();
            LNTPLPI_BR.rp.TableName = "LNTPLPI";
            LNTPLPI_BR.rp.upd = true;
            LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
            LNTPLPI_BR.rp.order = "TERM DESC";
            IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
            CEP.TRC(SCCGWA, "YE2222222");
        } else {
            CEP.TRC(SCCGWA, "33333");
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
            LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
            if (WS_PAY_MTH != '4') {
                LNRPLPI.KEY.REPY_MTH = 'P';
                LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
            }
            LNTPLPI_BR.rp = new DBParm();
            LNTPLPI_BR.rp.TableName = "LNTPLPI";
            LNTPLPI_BR.rp.upd = true;
            LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
            LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
            IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_FND_FLG);
    }
    public void T00_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
            CEP.TRC(SCCGWA, "NORMAL000");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
            CEP.TRC(SCCGWA, "NOTFND000");
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_FND_FLG);
    }
    public void T00_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
    }
    public void T00_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.DELETE(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void T00_REWRITE_LNTPLPI() throws IOException,SQLException,Exception {
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.REWRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        LNTPLPI_RD.where = "DUE_DT = :LNRPLPI.DUE_DT";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void T000_READ_LNTPLPI_BY_KEY() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        WS_FND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        }
    }
    public void T00_READUP_LNTPLPI() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.upd = true;
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void T000_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = SCCGWA.COMM_AREA.JRN_NO;
        if (LNCPLAJ.COMM_DATA.LN_AC.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCPLAJ.COMM_DATA.LN_AC);
        LNRSCHT.KEY.SUB_CTA_NO = "" + 0;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = 'P';
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE";
        LNTSCHT_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SCHT_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TYPE);
    }
    public void T00_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSCHT_BR);
    }
    public void T000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND, LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCPAIPM.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCPAIPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZILCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INST-AMT-CMP", LNCILCM);
        if (LNCILCM.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCILCM.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCILCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        CEP.TRC(SCCGWA, LNCAPRDM.RC.RC_RTNCODE);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            LNCPLAJ.RC.RC_APP = LNCAPRDM.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCAPRDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            LNCPLAJ.RC.RC_APP = "SC";
            LNCPLAJ.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
        SCSSCKDT3.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCPLAJ.RC.RC_APP = "SC";
            LNCPLAJ.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZENSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-ENTRY-SCH", LNCENSCH);
        if (LNCENSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCENSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCMSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONFIRM-SCHE", LNCCMSCH);
        if (LNCENSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCMSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZMTSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-MAINTIAN-SCHT", LNCMTSCH);
        if (LNCMTSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCMTSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPAIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PAIPM-MAIN", LNCSPAIP);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPAIPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCALT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CAL-TERM", LNCSCALT);
        if (LNCSCALT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, LNCSCALT.RC.RC_APP);
            LNCPLAJ.RC.RC_APP = LNCSCALT.RC.RC_APP;
            LNCPLAJ.RC.RC_RTNCODE = LNCSCALT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
        if (LNCRSCHT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSCHT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCPLAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        if (LNCPLAJ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCPLAJ=");
            CEP.TRC(SCCGWA, LNCPLAJ);
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
