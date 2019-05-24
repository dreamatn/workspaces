package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPMAJ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    DBParm LNTAGRE_RD;
    brParm LNTEXTN_BR = new brParm();
    brParm LNTPPRP_BR = new brParm();
    brParm LNTRCVD_BR = new brParm();
    DBParm LNTPLPI_RD;
    DBParm LNTTRAN_RD;
    boolean pgmRtn = false;
    String SCSSCLDT = "SCSSCLDT";
    String SCSSCKDT = "SCSSCKDT";
    char REPY_INT_ONE = '1';
    char REPY_INT_TWO = '2';
    char REPY_INT_THR = '3';
    char REPY_INT_FOR = '4';
    char REPY_INT_FIV = '5';
    char REPY_INT_SIX = '6';
    char REPY_INT_SEV = '7';
    char REPY_INT_ENG = '8';
    char MONTH = 'M';
    char DAY = 'D';
    short CAL_PERD = 2;
    short ONE_WEEK = 7;
    short TWO_WEEK = 14;
    short ONE_MONTH = 1;
    short ONE_SEASON = 3;
    short HALF_YEAR = 6;
    short ONE_YEAR = 12;
    String HIS_RMKS = "REPAYMENT METHOD ADJUST";
    LNZSPMAJ_WS_VARIABLES WS_VARIABLES = new LNZSPMAJ_WS_VARIABLES();
    LNZSPMAJ_WS_OUT_RECODE WS_OUT_RECODE = new LNZSPMAJ_WS_OUT_RECODE();
    LNZSPMAJ_WS_COND_FLG WS_COND_FLG = new LNZSPMAJ_WS_COND_FLG();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNRCONT LNRCONT = new LNRCONT();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCFFSDT LNCFFSDT = new LNCFFSDT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCILCM LNCILCM = new LNCILCM();
    LNCHPMAJ LNCHPMAJ = new LNCHPMAJ();
    LNCHPMAJ LNCHPMAJ = new LNCHPMAJ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCRATQ LNCRATQ = new LNCRATQ();
    LNCSCALT LNCSCALT = new LNCSCALT();
    LNCCMSCH LNCCMSCH = new LNCCMSCH();
    LNCSCPAI LNCSCPAI = new LNCSCPAI();
    LNRPPRP LNRPPRP = new LNRPPRP();
    LNREXTN LNREXTN = new LNREXTN();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRTRAN LNRTRAN = new LNRTRAN();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    LNCSPMAJ LNCSPMAJ;
    public void MP(SCCGWA SCCGWA, LNCSPMAJ LNCSPMAJ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPMAJ = LNCSPMAJ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPMAJ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == 'A') {
            WS_VARIABLES.A_B_RP_M = LNCSPMAJ.COMM_DATA.A_B_RP_M;
            LNCSPMAJ.COMM_DATA.A_B_RP_M = '2';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_B_RP_M);
        if ((LNCSPMAJ.COMM_DATA.A_P_TYP == '1' 
            && (LNCSPMAJ.COMM_DATA.A_B_RP_M != '0' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '1' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '2')) 
            || (LNCSPMAJ.COMM_DATA.A_P_TYP != '1' 
            && (LNCSPMAJ.COMM_DATA.A_B_RP_M == '0' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '1' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '2'))) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_P_NOT_MAC, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCSPMAJ.COMM_DATA.A_I_TYP == '1' 
            && (LNCSPMAJ.COMM_DATA.A_B_RP_M != '0' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '1')) 
            || (LNCSPMAJ.COMM_DATA.A_I_TYP != '1' 
            && (LNCSPMAJ.COMM_DATA.A_B_RP_M == '0' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '1'))) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_I_NOT_MAC, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '6' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '7') {
            if (LNCSPMAJ.COMM_DATA.TRAN_SEQ == 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MST_IP_SEQ, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (LNCSPMAJ.COMM_DATA.TRAN_SEQ != 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_CANT_IP_SEQ, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCSPMAJ.COMM_DATA.CTA_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R000_GET_CONT_INFO();
            if (pgmRtn) return;
            if (LNRCONT.MAT_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_LN1738, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_GET_EXTN_INFO();
        if (pgmRtn) return;
        R000_GET_PPRP_INFO();
        if (pgmRtn) return;
        R000_GET_LOAN_INFO();
        if (pgmRtn) return;
        R000_GET_ICTL_INFO();
        if (pgmRtn) return;
        R000_GET_AGRE_INFO();
        if (pgmRtn) return;
        R000_GET_APRD_INFO();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNCAPRDM.REC_DATA.PAY_MTH != LNCSPMAJ.COMM_DATA.A_B_RP_M) {
                if ((LNCAPRDM.REC_DATA.PAY_MTH == '4' 
                    && LNCSPMAJ.COMM_DATA.A_B_RP_M == '5') 
                    || (LNCAPRDM.REC_DATA.PAY_MTH == '5' 
                    && LNCSPMAJ.COMM_DATA.A_B_RP_M == '6')) {
                } else {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_REPY_MTH_N, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (LNCAPRDM.REC_DATA.PAY_MTH == '5') {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_REPY_MTH_N, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        WS_VARIABLES.OLD_APRD_PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        WS_VARIABLES.OLD_APRD_BPAY_MTH = LNCAPRDM.REC_DATA.BPAY_MTH;
        if (LNCAPRDM.REC_DATA.BPAY_MTH != '7') {
            IBS.init(SCCGWA, LNCHPMAJ);
            LNCHPMAJ.COMM_DATA.CTA_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            LNCHPMAJ.COMM_DATA.RP_M = LNCAPRDM.REC_DATA.PAY_MTH;
            LNCHPMAJ.COMM_DATA.P_TYP = LNCAPRDM.REC_DATA.BPAYP_PERD;
            LNCHPMAJ.COMM_DATA.P_PERD = LNCAPRDM.REC_DATA.PAYP_PERD;
            LNCHPMAJ.COMM_DATA.P_UNIT = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
            LNCHPMAJ.COMM_DATA.I_TYP = LNCAPRDM.REC_DATA.BCAL_PERD;
            LNCHPMAJ.COMM_DATA.I_PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            LNCHPMAJ.COMM_DATA.I_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
            LNCHPMAJ.COMM_DATA.INT_ED = LNCLOANM.REC_DATA.FST_CAL_DT;
            LNCHPMAJ.COMM_DATA.O_PERD = LNCAPRDM.REC_DATA.OCAL_PERD;
            LNCHPMAJ.COMM_DATA.O_UNIT = LNCAPRDM.REC_DATA.OCAL_PERD_UNIT;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_CLOSED, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(29 - 1, 29 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_DELAY_CONNOT_AJ, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(73 - 1, 73 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_AC_MGM_FEE_Y, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = "" + LNCSPMAJ.COMM_DATA.A_INT_ED;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).compareTo("31") > 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_APPINTED_DAY, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCSPMAJ.COMM_DATA.A_INT_ED+"", WS_VARIABLES.WS_0_A_INT_ED);
        }
        CEP.TRC(SCCGWA, "A000002");
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_B_RP_M);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
        if (LNCSPMAJ.COMM_DATA.A_INT_ED == 0) {
            if (WS_VARIABLES.OLD_APRD_PAY_MTH == '1' 
                && LNCSPMAJ.COMM_DATA.A_B_RP_M == '7') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_ED_MST_INPT, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1') {
                    LNCSPMAJ.COMM_DATA.A_INT_ED = LNRCONT.MAT_DATE;
                } else {
                    LNCSPMAJ.COMM_DATA.A_INT_ED = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
                }
            }
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_VARIABLES.OLD_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        CEP.TRC(SCCGWA, "11");
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_PAY_DD_TYPE);
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_INT_ED);
        CEP.TRC(SCCGWA, "22");
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_PAY_MTH);
        CEP.TRC(SCCGWA, LNCSPMAJ.COMM_DATA.A_B_RP_M);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_C_N_CHANGE_SCH, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRAGRE.CTA_FROM == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_DK_C_N_CHG_SCH, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M != '0' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '1' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '2' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '3' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '4' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '5' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '6' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '7' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '8' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '9') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RP_MTH_I_ERR, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_P_TYP != ' ' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '1' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '2' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '3' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '4' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '5' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '6' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '7' 
            && LNCSPMAJ.COMM_DATA.A_P_TYP != '8') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_P_RP_TYP_I_ERR, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_I_TYP != ' ' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '1' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '2' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '3' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '4' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '5' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '6' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '7' 
            && LNCSPMAJ.COMM_DATA.A_I_TYP != '8') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_I_RP_TYP_I_ERR, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_BPAY_MTH);
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_P_GRACE_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        if (LNCAPRDM.REC_DATA.BPAY_MTH == '3' 
            && LNCAPRDM.REC_DATA.P_GRACE_TERM >= LNCICTLM.REC_DATA.IC_CAL_TERM) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_B_PAY_MTH, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_COND_FLG.TYP_FLG = 'P';
        R000_GET_PERD_UNIT();
        if (pgmRtn) return;
        WS_COND_FLG.TYP_FLG = 'I';
        R000_GET_PERD_UNIT();
        if (pgmRtn) return;
        if ((LNCAPRDM.REC_DATA.PAY_MTH == '4' 
            && LNCAPRDM.REC_DATA.INST_MTH == '2' 
            && LNCAPRDM.REC_DATA.P_GRACE_TERM > 0) 
            || (LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            && LNCSPMAJ.COMM_DATA.A_P_GRA > 0)) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_CNT_AJ_5_WTH_GRA, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') {
            WS_VARIABLES.P_PERD = WS_VARIABLES.I_PERD;
            WS_VARIABLES.P_UNIT = WS_VARIABLES.I_UNIT;
        }
        if ((WS_VARIABLES.I_PERD == 0 
            || WS_VARIABLES.I_UNIT == ' ') 
            && (LNCSPMAJ.COMM_DATA.A_B_RP_M == '2' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '3' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9')) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_I_PER_UNT_M_I, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '3') {
            if (WS_VARIABLES.P_PERD == 0 
                || WS_VARIABLES.P_UNIT == ' ') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_A_PAY_MTH1, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.P_UNIT != WS_VARIABLES.I_UNIT) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_P_EQU_I, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.YU_SHU = (short) (WS_VARIABLES.I_PERD % WS_VARIABLES.P_PERD);
            WS_VARIABLES.BEI_SHU = (short) ((WS_VARIABLES.I_PERD - WS_VARIABLES.YU_SHU) / WS_VARIABLES.P_PERD);
            CEP.TRC(SCCGWA, WS_VARIABLES.I_PERD);
            CEP.TRC(SCCGWA, WS_VARIABLES.P_PERD);
            CEP.TRC(SCCGWA, WS_VARIABLES.BEI_SHU);
            CEP.TRC(SCCGWA, WS_VARIABLES.YU_SHU);
            if (WS_VARIABLES.YU_SHU != 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_P_MUL_I, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.BEI_SHU == 1) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_P_CANT_EQU_I, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') 
            && LNCSPMAJ.COMM_DATA.A_B_PERD == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_I_B_PERD, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_INT_ED <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ICAL_DT_GT_AC_DT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_CORE_REPAY_MTH();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCFFSDT.REPAY_MTH != '4' 
            && (LNCICTLM.REC_DATA.CTL_STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1"))) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_SUBS_FUND_M_4, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCFFSDT.REPAY_MTH == '4' 
            && (LNCSPMAJ.COMM_DATA.A_P_TYP != ' ' 
            || LNCSPMAJ.COMM_DATA.A_P_PERD != 0 
            || LNCSPMAJ.COMM_DATA.A_P_UNIT != ' ' 
            || LNCSPMAJ.COMM_DATA.A_P_GRA != 0)) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CANT_INPUT_P, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_STARTBR_LNTRCVD_PROC();
        if (pgmRtn) return;
        T000_READNEXT_LNTRCVD_PROC();
        if (pgmRtn) return;
        T000_ENDBR_LNTRCVD_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.RCVD_FLG == 'Y' 
            && ((LNCAPRDM.REC_DATA.PAY_MTH == '4' 
            && LNCFFSDT.REPAY_MTH != '4') 
            || (LNCAPRDM.REC_DATA.PAY_MTH != '4' 
            && LNCFFSDT.REPAY_MTH == '4'))) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAY_MTH_CANT_CHG, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.I_ALD_CAL_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1' 
            && LNCSPMAJ.COMM_DATA.A_INT_ED != LNRCONT.MAT_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MST_EQ_MAT_DT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M != '1' 
            && LNCSPMAJ.COMM_DATA.A_INT_ED >= LNRCONT.MAT_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MST_LES_MAT_DT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPMAJ.COMM_DATA.A_P_GRA != 0 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '3') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_GRA_CANT_INP, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCSPMAJ.COMM_DATA.A_B_RP_M != '8' 
            && LNCSPMAJ.COMM_DATA.A_B_RP_M != '9') 
            && LNCSPMAJ.COMM_DATA.A_B_PERD != 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_CANT_I_B_PERD, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.BPAY_MTH == '5' 
            && LNCAPRDM.REC_DATA.P_GRACE_TERM != 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_1656, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B000_WTRITE_HIS_PROC();
        if (pgmRtn) return;
        B200_WTRITE_LNTTRAN();
        if (pgmRtn) return;
        B210_UPDATE_LNTAPRD();
        if (pgmRtn) return;
        B220_UPDATE_LNTICTL();
        if (pgmRtn) return;
        B230_UPDATE_LNTLOAN();
        if (pgmRtn) return;
        B300_UPDATE_SCHEDULE();
        if (pgmRtn) return;
        B400_SEND_MESSAGE_CHECK();
        if (pgmRtn) return;
    }
    public void B000_WTRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCHPMAJ);
        LNCHPMAJ.COMM_DATA.CTA_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNCHPMAJ.COMM_DATA.RP_M = WS_VARIABLES.A_B_RP_M;
        LNCHPMAJ.COMM_DATA.P_TYP = LNCSPMAJ.COMM_DATA.A_P_TYP;
        LNCHPMAJ.COMM_DATA.P_PERD = LNCSPMAJ.COMM_DATA.A_P_PERD;
        LNCHPMAJ.COMM_DATA.P_UNIT = LNCSPMAJ.COMM_DATA.A_P_UNIT;
        LNCHPMAJ.COMM_DATA.I_TYP = LNCSPMAJ.COMM_DATA.A_I_TYP;
        LNCHPMAJ.COMM_DATA.I_PERD = LNCSPMAJ.COMM_DATA.A_I_PERD;
        LNCHPMAJ.COMM_DATA.I_UNIT = LNCSPMAJ.COMM_DATA.A_I_UNIT;
        LNCHPMAJ.COMM_DATA.INT_ED = LNCSPMAJ.COMM_DATA.A_INT_ED;
        LNCHPMAJ.COMM_DATA.B_PERD = LNCSPMAJ.COMM_DATA.A_B_PERD;
        LNCHPMAJ.COMM_DATA.O_TYP = LNCSPMAJ.COMM_DATA.A_O_TYP;
        LNCHPMAJ.COMM_DATA.O_PERD = LNCSPMAJ.COMM_DATA.A_O_PERD;
        LNCHPMAJ.COMM_DATA.O_UNIT = LNCSPMAJ.COMM_DATA.A_O_UNIT;
        LNCHPMAJ.COMM_DATA.TRAN_SEQ = LNCSPMAJ.COMM_DATA.TRAN_SEQ;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.REF_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        BPCPNHIS.INFO.AC = LNCSPMAJ.COMM_DATA.CTA_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_RMKS;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "LNCHPMAJ";
        BPCPNHIS.INFO.FMT_ID_LEN = 75;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCHPMAJ;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCHPMAJ;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B200_WTRITE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.TRAN_STS = 'N';
        LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNRTRAN.TR_DATA.trim().length() += 75;
        LNRTRAN.TR_DATA = IBS.CLS2CPY(SCCGWA, LNCHPMAJ) + IBS.CLS2CPY(SCCGWA, LNCHPMAJ);
        LNRTRAN.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRTRAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRTRAN);
        T000_WRITE_LNTTRAN();
        if (pgmRtn) return;
    }
    public void B210_UPDATE_LNTAPRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '4';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        WS_VARIABLES.LAST_PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        LNCAPRDM.FUNC = '2';
        LNCAPRDM.REC_DATA.BPAY_MTH = WS_VARIABLES.A_B_RP_M;
        LNCAPRDM.REC_DATA.PAY_MTH = LNCFFSDT.REPAY_MTH;
        LNCAPRDM.REC_DATA.INST_MTH = LNCFFSDT.INST_MTH;
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') {
            LNCAPRDM.REC_DATA.BPAYP_PERD = LNCSPMAJ.COMM_DATA.A_I_TYP;
        } else {
            LNCAPRDM.REC_DATA.BPAYP_PERD = LNCSPMAJ.COMM_DATA.A_P_TYP;
        }
        LNCAPRDM.REC_DATA.P_GRACE_TERM = LNCSPMAJ.COMM_DATA.A_P_GRA;
        LNCAPRDM.REC_DATA.BCAL_PERD = LNCSPMAJ.COMM_DATA.A_I_TYP;
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M != '7') {
            LNCAPRDM.REC_DATA.CAL_PERD = WS_VARIABLES.I_PERD;
            LNCAPRDM.REC_DATA.CAL_PERD_UNIT = WS_VARIABLES.I_UNIT;
            LNCAPRDM.REC_DATA.PAYP_PERD = WS_VARIABLES.P_PERD;
            LNCAPRDM.REC_DATA.PAYP_PERD_UNIT = WS_VARIABLES.P_UNIT;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_0_A_INT_ED);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            LNCAPRDM.REC_DATA.PAY_DD_TYPE = '2';
        }
        R000_GET_PAY_DAY();
        if (pgmRtn) return;
        LNCAPRDM.REC_DATA.PAY_DAY = WS_VARIABLES.WS_A_INT_ED.A_INT_ED_DD;
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') {
            LNCAPRDM.REC_DATA.SPEC_LN_FLG = '1';
        } else {
            LNCAPRDM.REC_DATA.SPEC_LN_FLG = ' ';
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '2') {
            LNCAPRDM.REC_DATA.BPAYP_PERD = '1';
            LNCAPRDM.REC_DATA.PAYP_PERD = 0;
            LNCAPRDM.REC_DATA.PAYP_PERD_UNIT = ' ';
        }
        R000_GET_ODUE_PERD_UNIT();
        if (pgmRtn) return;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B220_UPDATE_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCFFSDT.REPAY_MTH == '1' 
            || LNCFFSDT.REPAY_MTH == '2') {
            LNCICTLM.REC_DATA.P_CAL_DUE_DT = LNRCONT.MAT_DATE;
            LNCICTLM.REC_DATA.P_CMP_DUE_DT = LNRCONT.MAT_DATE;
        }
        if (LNCFFSDT.REPAY_MTH == '3') {
            R000_GET_P_CAL_DUE_DT();
            if (pgmRtn) return;
            if (WS_VARIABLES.P_CAL_DUE_DT > SCCGWA.COMM_AREA.AC_DATE) {
                LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_VARIABLES.P_CAL_DUE_DT;
                LNCICTLM.REC_DATA.P_CMP_DUE_DT = WS_VARIABLES.P_CAL_DUE_DT;
                CEP.TRC(SCCGWA, WS_VARIABLES.P_CAL_DUE_DT);
                LNCICTLM.REC_DATA.P_CAL_DUE_DT = LNCSPMAJ.COMM_DATA.A_INT_ED;
                LNCICTLM.REC_DATA.P_CMP_DUE_DT = LNCSPMAJ.COMM_DATA.A_INT_ED;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_PCAL_DT_GT_AC_DT, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "A00001");
        CEP.TRC(SCCGWA, LNCFFSDT.REPAY_MTH);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_DUE_DT);
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = LNCSPMAJ.COMM_DATA.A_INT_ED;
        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNCSPMAJ.COMM_DATA.A_INT_ED;
        if (LNCFFSDT.REPAY_MTH == '4') {
            LNCICTLM.REC_DATA.P_CMP_DUE_DT = LNCSPMAJ.COMM_DATA.A_INT_ED;
        }
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        LNCICTLM.REC_DATA.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        if (WS_VARIABLES.LAST_PAY_MTH > '4') {
            LNCICTLM.REC_DATA.P_CMP_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        } else {
            LNCICTLM.REC_DATA.P_CMP_TERM = (short) (LNCICTLM.REC_DATA.P_CAL_TERM + 1);
        }
        LNCICTLM.REC_DATA.IC_CMP_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
        CEP.TRC(SCCGWA, "222");
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B400_SEND_MESSAGE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        WS_VARIABLES.NEW_MAT_DATE = 0;
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_VARIABLES.NEW_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        CEP.TRC(SCCGWA, WS_VARIABLES.OLD_MAT_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.NEW_MAT_DATE);
        if (WS_VARIABLES.OLD_MAT_DATE != WS_VARIABLES.NEW_MAT_DATE) {
            B410_SEND_MESSAGE();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B410_SEND_MESSAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.AC = LNCSPMAJ.COMM_DATA.CTA_NO;
        SCCWRMSG.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        SCCWRMSG.AP_CODE = "26";
        JIBS_tmp_str[0] = "" + WS_VARIABLES.NEW_MAT_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_VARIABLES.NEW_MAT_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_VARIABLES.NEW_MAT_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        CEP.TRC(SCCGWA, SCCWRMSG.AC);
        CEP.TRC(SCCGWA, SCCWRMSG.CI_NO);
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void B230_UPDATE_LNTLOAN() throws IOException,SQLException,Exception {
        if (LNCLOANM.REC_DATA.FST_PAYP_DT > SCCGWA.COMM_AREA.AC_DATE 
            || LNCLOANM.REC_DATA.FST_CAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '4';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
            LNCLOANM.FUNC = '2';
            if (LNCLOANM.REC_DATA.FST_PAYP_DT > SCCGWA.COMM_AREA.AC_DATE 
                || LNCLOANM.REC_DATA.FST_PAYP_DT == 0) {
                LNCLOANM.REC_DATA.FST_PAYP_DT = WS_VARIABLES.P_CAL_DUE_DT;
            }
            if (LNCLOANM.REC_DATA.FST_CAL_DT > SCCGWA.COMM_AREA.AC_DATE 
                || LNCLOANM.REC_DATA.FST_CAL_DT == 0) {
                LNCLOANM.REC_DATA.FST_CAL_DT = LNCSPMAJ.COMM_DATA.A_INT_ED;
            }
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B300_UPDATE_SCHEDULE() throws IOException,SQLException,Exception {
        B310_DELETE_LNTPLPI();
        if (pgmRtn) return;
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '2' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '3' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '6' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '7' 
            && WS_VARIABLES.OLD_APRD_PAY_MTH == '4') {
            B320_DELETE_LNTPAIP();
            if (pgmRtn) return;
        }
        R000_GET_APRD_INFO();
        if (pgmRtn) return;
        R000_GET_ICTL_INFO();
        if (pgmRtn) return;
        R000_GET_CONT_INFO();
        if (pgmRtn) return;
        B330_ADD_LNTPLPI();
        if (pgmRtn) return;
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') {
            if (WS_VARIABLES.OLD_APRD_PAY_MTH != '4') {
                B340_ADD_LNTPAIP();
                if (pgmRtn) return;
            } else {
                B340_UPDATE_LNTPAIP();
                if (pgmRtn) return;
                if (WS_VARIABLES.OLD_APRD_BPAY_MTH == '7') {
                    B320_02_DELETE_LNTPAIP();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '6') {
            IBS.init(SCCGWA, WS_OUT_RECODE);
            IBS.init(SCCGWA, SCCFMT);
            WS_OUT_RECODE.TRAN_SEQ = LNCSPMAJ.COMM_DATA.TRAN_SEQ;
            WS_OUT_RECODE.CTA_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            SCCFMT.FMTID = "LNP31";
            SCCFMT.DATA_PTR = WS_OUT_RECODE;
            SCCFMT.DATA_LEN = 44;
            IBS.FMT(SCCGWA, SCCFMT);
        }
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '7') {
            IBS.init(SCCGWA, LNCSCPAI);
            LNCSCPAI.REC_DATA.TRAN_SEQ = LNCSPMAJ.COMM_DATA.TRAN_SEQ;
            LNCSCPAI.REC_DATA.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            S000_CALL_SVR_LNZSCPAI();
            if (pgmRtn) return;
            IBS.init(SCCGWA, WS_OUT_RECODE);
            IBS.init(SCCGWA, SCCFMT);
            WS_OUT_RECODE.TRAN_SEQ = LNCSPMAJ.COMM_DATA.TRAN_SEQ;
            WS_OUT_RECODE.CTA_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            SCCFMT.FMTID = "LNP33";
            SCCFMT.DATA_PTR = WS_OUT_RECODE;
            SCCFMT.DATA_LEN = 44;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void B310_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        WS_VARIABLES.REPY_TYP = 'I';
        WS_VARIABLES.INPUT_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
        if (pgmRtn) return;
        WS_VARIABLES.REPY_TYP = 'P';
        WS_VARIABLES.INPUT_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
        if (pgmRtn) return;
        WS_VARIABLES.REPY_TYP = 'C';
        WS_VARIABLES.INPUT_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
        if (pgmRtn) return;
    }
    public void B320_DELETE_LNTPAIP() throws IOException,SQLException,Exception {
        WS_VARIABLES.PHS_NO = 1;
        R000_DEL_PAIP_UNTIL_NF();
        if (pgmRtn) return;
    }
    public void B320_02_DELETE_LNTPAIP() throws IOException,SQLException,Exception {
        WS_VARIABLES.PHS_NO = 2;
        R000_DEL_PAIP_UNTIL_NF();
        if (pgmRtn) return;
    }
    public void B330_ADD_LNTPLPI() throws IOException,SQLException,Exception {
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '2' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '3') {
            WS_VARIABLES.P_CMP_TERM = LNCICTLM.REC_DATA.P_CMP_TERM;
            WS_VARIABLES.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
            WS_VARIABLES.IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CMP_VAL_DT;
            WS_VARIABLES.IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
            if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '2') {
                R000_GET_LOAN_BAL();
                if (pgmRtn) return;
                R000_GET_LAST_PLPI_INFO();
                if (pgmRtn) return;
                if (LNRPLPI.DUE_DT == 0) {
                    WS_VARIABLES.VAL_DT = LNRCONT.START_DATE;
                } else {
                    WS_VARIABLES.VAL_DT = LNRPLPI.DUE_DT;
                }
                IBS.init(SCCGWA, LNCRPLPI);
                IBS.init(SCCGWA, LNRPLPI);
                LNCRPLPI.FUNC = 'A';
                LNRPLPI.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
                LNRPLPI.KEY.SUB_CTA_NO = 0;
                LNRPLPI.KEY.REPY_MTH = 'P';
                LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
                LNRPLPI.VAL_DT = WS_VARIABLES.VAL_DT;
                LNRPLPI.DUE_DT = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
                JIBS_NumStr = "" + 0;
                LNRPLPI.REC_STS = JIBS_NumStr.charAt(0);
                LNRPLPI.DUE_REPY_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
                LNRPLPI.PRIN_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
                S000_CALL_LNZRPLPI();
                if (pgmRtn) return;
                WS_VARIABLES.P_CMP_TERM += 1;
            }
            if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '1') {
                IBS.init(SCCGWA, LNCRPLPI);
                IBS.init(SCCGWA, LNRPLPI);
                LNCRPLPI.FUNC = 'A';
                LNRPLPI.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
                LNRPLPI.KEY.SUB_CTA_NO = 0;
                LNRPLPI.KEY.REPY_MTH = 'I';
                LNRPLPI.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
                LNRPLPI.VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
                LNRPLPI.DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
                JIBS_NumStr = "" + 0;
                LNRPLPI.REC_STS = JIBS_NumStr.charAt(0);
                LNRPLPI.DUE_REPY_AMT = 0;
                LNRPLPI.PRIN_AMT = 0;
                S000_CALL_LNZRPLPI();
                if (pgmRtn) return;
                WS_VARIABLES.IC_CMP_TERM += 1;
                if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '2' 
                    || LNCSPMAJ.COMM_DATA.A_B_RP_M == '3') {
                    WS_VARIABLES.IC_CMP_VAL_DT = WS_VARIABLES.IC_CMP_DUE_DT;
                    IBS.init(SCCGWA, SCCCLDT);
                    if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                        SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
                    } else {
                        SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
                    }
                    SCCCLDT.DATE1 = WS_VARIABLES.IC_CMP_DUE_DT;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    WS_VARIABLES.IC_CMP_DUE_DT = SCCCLDT.DATE2;
                }
            }
            B321_UPD_ICTL_AFT_ADD_PLPI();
            if (pgmRtn) return;
        }
    }
    public void B340_ADD_LNTPAIP() throws IOException,SQLException,Exception {
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') {
            R000_GET_LOAN_BAL();
            if (pgmRtn) return;
            R000_GET_TOT_TERM();
            if (pgmRtn) return;
            R000_GET_RAT_INFO();
            if (pgmRtn) return;
            R000_COMP_INST_AMT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCRPAIP);
            IBS.init(SCCGWA, LNRPAIP);
            LNCRPAIP.FUNC = 'A';
            LNRPAIP.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            LNRPAIP.KEY.SUB_CTA_NO = 0;
            LNRPAIP.KEY.PHS_NO = 1;
            LNRPAIP.INST_MTH = LNCFFSDT.INST_MTH;
            LNRPAIP.PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            LNRPAIP.PERD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
            LNRPAIP.PHS_INST_AMT = WS_VARIABLES.PHS_INST_AMT;
            LNRPAIP.PHS_PRIN_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
            LNRPAIP.PHS_TOT_TERM = (short) (WS_VARIABLES.TOT_TERM + LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
            LNRPAIP.PHS_REM_PRIN_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
            LNRPAIP.PHS_CAL_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
            LNRPAIP.PHS_CMP_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
            LNRPAIP.CUR_INST_AMT = WS_VARIABLES.PHS_INST_AMT;
            LNRPAIP.CUR_INST_IRAT = WS_VARIABLES.N_RATE;
            S000_CALL_LNZPAIPL();
            if (pgmRtn) return;
        }
    }
    public void B340_UPDATE_LNTPAIP() throws IOException,SQLException,Exception {
        if (LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
            || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9') {
            R000_GET_LOAN_BAL();
            if (pgmRtn) return;
            R000_GET_TOT_TERM();
            if (pgmRtn) return;
            R000_GET_RAT_INFO();
            if (pgmRtn) return;
            R000_COMP_INST_AMT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCRPAIP);
            IBS.init(SCCGWA, LNRPAIP);
            LNRPAIP.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
            LNRPAIP.KEY.SUB_CTA_NO = 0;
            LNRPAIP.KEY.PHS_NO = 1;
            LNCRPAIP.FUNC = 'R';
            WS_COND_FLG.PAIP_FLG = 'Y';
            S000_CALL_LNZPAIPL();
            if (pgmRtn) return;
            if (WS_COND_FLG.PAIP_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAIP_NOTFND, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            LNRPAIP.INST_MTH = LNCFFSDT.INST_MTH;
            LNRPAIP.PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            LNRPAIP.PERD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
            LNRPAIP.PHS_INST_AMT = WS_VARIABLES.PHS_INST_AMT;
            if (WS_VARIABLES.OLD_APRD_BPAY_MTH == '7') {
                LNRPAIP.PHS_PRIN_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
            }
            LNRPAIP.PHS_TOT_TERM = (short) (WS_VARIABLES.TOT_TERM + LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
            LNRPAIP.PHS_REM_PRIN_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
            LNRPAIP.PHS_CAL_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
            LNRPAIP.PHS_CMP_TERM = LNRPAIP.PHS_CAL_TERM;
            LNRPAIP.CUR_INST_AMT = WS_VARIABLES.PHS_INST_AMT;
            LNRPAIP.CUR_INST_IRAT = WS_VARIABLES.N_RATE;
            LNCRPAIP.FUNC = 'U';
            S000_CALL_LNZPAIPL();
            if (pgmRtn) return;
        }
    }
    public void B321_UPD_ICTL_AFT_ADD_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        LNCICTLM.REC_DATA.P_CMP_TERM = WS_VARIABLES.P_CMP_TERM;
        LNCICTLM.REC_DATA.IC_CMP_TERM = WS_VARIABLES.IC_CMP_TERM;
        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_VARIABLES.IC_CMP_VAL_DT;
        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_VARIABLES.IC_CMP_DUE_DT;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void R000_GET_RAT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATQ);
        LNCRATQ.COMM_DATA.LN_AC = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNCRATQ.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCRATQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCRATQ.COMM_DATA.SUF_NO = "0" + LNCRATQ.COMM_DATA.SUF_NO;
        LNCRATQ.COMM_DATA.RATE_TYPE = 'N';
        LNCRATQ.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_LNZRATQ();
        if (pgmRtn) return;
        WS_VARIABLES.N_RATE = LNCRATQ.COMM_DATA.RATE;
    }
    public void R000_COMP_INST_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCILCM);
        if (LNCAPRDM.REC_DATA.INST_MTH == '1') {
            LNCILCM.COMM_DATA.FORM_CODE = "31";
        } else if (LNCAPRDM.REC_DATA.INST_MTH == '2') {
            LNCILCM.COMM_DATA.FORM_CODE = "34";
        }
        LNCILCM.COMM_DATA.FUNC_CODE = '1';
        LNCILCM.COMM_DATA.PRIN_AMT = WS_VARIABLES.WS_LOAN_CONT_AREA.REDEFINES63.WS_LOAN_CONT[2-1].LOAN_BAL;
        LNCILCM.COMM_DATA.RATE = WS_VARIABLES.N_RATE;
        LNCILCM.COMM_DATA.BASDAYS_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        LNCILCM.COMM_DATA.PERIOD = LNCAPRDM.REC_DATA.CAL_PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        LNCILCM.COMM_DATA.CCY = LNRCONT.CCY;
        LNCILCM.COMM_DATA.TERM = WS_VARIABLES.TOT_TERM;
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        WS_VARIABLES.PHS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
    }
    public void R000_GET_TOT_TERM() throws IOException,SQLException,Exception {
        WS_VARIABLES.TOT_TERM = 0;
        WS_VARIABLES.DATE = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        WS_VARIABLES.DATE1 = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        while (WS_VARIABLES.DATE < LNRCONT.MAT_DATE) {
            WS_VARIABLES.TOT_TERM += 1;
            IBS.init(SCCGWA, SCCCLDT);
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                SCCCLDT.MTHS = LNCAPRDM.REC_DATA.CAL_PERD;
            } else {
                SCCCLDT.DAYS = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            SCCCLDT.DATE1 = WS_VARIABLES.DATE;
            WS_VARIABLES.DATE1 = WS_VARIABLES.DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.DATE = SCCCLDT.DATE2;
        }
        if (LNCAPRDM.REC_DATA.FST_PAY_FLG == '2') {
            if (LNCAPRDM.REC_DATA.CAL_PERD_UNIT == 'M') {
                IBS.CPY2CLS(SCCGWA, WS_VARIABLES.DATE1+"", WS_VARIABLES.WS_LAST_DT);
                WS_VARIABLES.MAT_DT = LNRCONT.MAT_DATE;
                IBS.CPY2CLS(SCCGWA, WS_VARIABLES.MAT_DT+"", WS_VARIABLES.WS_END_DT);
                if (WS_VARIABLES.WS_LAST_DT.LAST_DT_YYYYMM != WS_VARIABLES.WS_END_DT.END_DT_YYYYMM) {
                    WS_VARIABLES.TOT_TERM += 1;
                }
            } else {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = WS_VARIABLES.DATE1;
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                if (SCCCKDT.WEEK_NO == 7) {
                    WS_VARIABLES.DAYS = 6;
                } else {
                    WS_VARIABLES.DAYS = 6 - SCCCKDT.WEEK_NO;
                }
                if (WS_VARIABLES.DAYS == 0) {
                    WS_VARIABLES.DATE2 = WS_VARIABLES.DATE1;
                } else {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DAYS = WS_VARIABLES.DAYS;
                    SCCCLDT.DATE1 = WS_VARIABLES.DATE1;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    WS_VARIABLES.DATE2 = SCCCLDT.DATE2;
                }
                if (WS_VARIABLES.DATE2 < LNRCONT.MAT_DATE) {
                    WS_VARIABLES.TOT_TERM += 1;
                }
            }
        } else {
            WS_VARIABLES.TOT_TERM += 1;
        }
        if (LNCSPMAJ.COMM_DATA.A_B_PERD != 0) {
            if (LNCSPMAJ.COMM_DATA.A_B_PERD > WS_VARIABLES.TOT_TERM) {
                WS_VARIABLES.TOT_TERM = LNCSPMAJ.COMM_DATA.A_B_PERD;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MST_GT_TOTT, LNCSPMAJ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_LAST_PLPI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        T000_STARTBR_BY_DUE_DT_PROC();
        if (pgmRtn) return;
    }
    public void R000_GET_LOAN_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_LOAN_CONT_AREA);
    }
    public void R000_DEL_PLPI_UNTIL_NF() throws IOException,SQLException,Exception {
        WS_COND_FLG.PLPI_END_FLG = 'N';
        S000_CALL_LNZRPLPI_DELETE();
        if (pgmRtn) return;
        while (WS_COND_FLG.PLPI_END_FLG != 'Y') {
            S000_CALL_LNZRPLPI_DELETE();
            if (pgmRtn) return;
        }
    }
    public void R000_DEL_PAIP_UNTIL_NF() throws IOException,SQLException,Exception {
        WS_COND_FLG.PAIP_FLG = 'Y';
        S000_CALL_LNZRPAIP_DELETE();
        if (pgmRtn) return;
        while (WS_COND_FLG.PAIP_FLG != 'N') {
            S000_CALL_LNZRPAIP_DELETE();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_EXTN_INFO() throws IOException,SQLException,Exception {
        T000_STARTBR_LNTEXTN_PROC();
        if (pgmRtn) return;
        T000_READNEXT_LNTEXTN_PROC();
        if (pgmRtn) return;
        T000_ENDBR_LNTEXTN_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.EXTN_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_EXTN_CANT_AJ, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_PPRP_INFO() throws IOException,SQLException,Exception {
        T000_STARTBR_LNTPPRP_PROC();
        if (pgmRtn) return;
        T000_READNEXT_LNTPPRP_PROC();
        if (pgmRtn) return;
        T000_ENDBR_LNTPPRP_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.PPRP_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PPRP_CANT_AJ, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CONT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void R000_GET_LOAN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
    }
    public void R000_GET_ICTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void R000_GET_AGRE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
    }
    public void R000_GET_APRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void R000_GET_PPMQ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
    }
    public void R000_GET_CORE_REPAY_MTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCFFSDT);
        LNCFFSDT.FUNC = 'R';
        LNCFFSDT.BREP_MTH = LNCSPMAJ.COMM_DATA.A_B_RP_M;
        LNCFFSDT.PAYI_PERD = LNCSPMAJ.COMM_DATA.A_I_TYP;
        S000_CALL_LNZFFSDT();
        if (pgmRtn) return;
    }
    public void R000_GET_PAY_DAY() throws IOException,SQLException,Exception {
        if (LNCSPMAJ.COMM_DATA.A_I_TYP == '2' 
            || LNCSPMAJ.COMM_DATA.A_I_TYP == '3') {
            R000_GET_WEEK_NO();
            if (pgmRtn) return;
            WS_VARIABLES.WS_A_INT_ED.A_INT_ED_DD = SCCCKDT.WEEK_NO;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCSPMAJ.COMM_DATA.A_INT_ED+"", WS_VARIABLES.WS_A_INT_ED);
        }
    }
    public void R000_GET_WEEK_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = LNCSPMAJ.COMM_DATA.A_INT_ED;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
    }
    public void R000_GET_PERD_UNIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_PERD_UNIT);
        if (WS_COND_FLG.TYP_FLG == 'P') {
            WS_VARIABLES.WS_PERD_UNIT.TYP = LNCSPMAJ.COMM_DATA.A_P_TYP;
        } else {
            WS_VARIABLES.WS_PERD_UNIT.TYP = LNCSPMAJ.COMM_DATA.A_I_TYP;
        }
        if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_ONE) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = 0;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = ' ';
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_TWO) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = ONE_WEEK;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = DAY;
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_THR) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = TWO_WEEK;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = DAY;
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_FOR) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = ONE_MONTH;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = MONTH;
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_FIV) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = ONE_SEASON;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = MONTH;
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_SIX) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = HALF_YEAR;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = MONTH;
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_SEV) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = ONE_YEAR;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = MONTH;
        } else if (WS_VARIABLES.WS_PERD_UNIT.TYP == REPY_INT_ENG) {
            if (WS_COND_FLG.TYP_FLG == 'P') {
                WS_VARIABLES.WS_PERD_UNIT.PERD = LNCSPMAJ.COMM_DATA.A_P_PERD;
                WS_VARIABLES.WS_PERD_UNIT.UNIT = LNCSPMAJ.COMM_DATA.A_P_UNIT;
            } else {
                WS_VARIABLES.WS_PERD_UNIT.PERD = LNCSPMAJ.COMM_DATA.A_I_PERD;
                WS_VARIABLES.WS_PERD_UNIT.UNIT = LNCSPMAJ.COMM_DATA.A_I_UNIT;
            }
        } else {
            WS_VARIABLES.WS_PERD_UNIT.PERD = 0;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = ' ';
        }
        if (WS_COND_FLG.TYP_FLG == 'P') {
            if (WS_VARIABLES.WS_PERD_UNIT.PERD == 0) {
                WS_VARIABLES.WS_PERD_UNIT.PERD = LNCAPRDM.REC_DATA.PAYP_PERD;
            }
            if (WS_VARIABLES.WS_PERD_UNIT.UNIT == ' ') {
                WS_VARIABLES.WS_PERD_UNIT.UNIT = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
            }
            WS_VARIABLES.P_PERD = WS_VARIABLES.WS_PERD_UNIT.PERD;
            WS_VARIABLES.P_UNIT = WS_VARIABLES.WS_PERD_UNIT.UNIT;
        } else {
            if (WS_VARIABLES.WS_PERD_UNIT.PERD == 0 
                && (LNCSPMAJ.COMM_DATA.A_B_RP_M == '2' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '3' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9')) {
                WS_VARIABLES.WS_PERD_UNIT.PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            }
            if (WS_VARIABLES.WS_PERD_UNIT.UNIT == ' ' 
                && (LNCSPMAJ.COMM_DATA.A_B_RP_M == '2' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '3' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '4' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '5' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '8' 
                || LNCSPMAJ.COMM_DATA.A_B_RP_M == '9')) {
                WS_VARIABLES.WS_PERD_UNIT.UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
            }
            WS_VARIABLES.I_PERD = WS_VARIABLES.WS_PERD_UNIT.PERD;
            WS_VARIABLES.I_UNIT = WS_VARIABLES.WS_PERD_UNIT.UNIT;
        }
    }
    public void R000_GET_ODUE_PERD_UNIT() throws IOException,SQLException,Exception {
        if (LNCSPMAJ.COMM_DATA.A_O_TYP == '1') {
            R000_GET_DEFAULT_PERD_UNIT();
            if (pgmRtn) return;
            LNCAPRDM.REC_DATA.OCAL_PERD = WS_VARIABLES.WS_PERD_UNIT.PERD;
            LNCAPRDM.REC_DATA.OCAL_PERD_UNIT = WS_VARIABLES.WS_PERD_UNIT.UNIT;
        } else if (LNCSPMAJ.COMM_DATA.A_O_TYP == '2') {
            LNCAPRDM.REC_DATA.OCAL_PERD = LNCSPMAJ.COMM_DATA.A_O_PERD;
            LNCAPRDM.REC_DATA.OCAL_PERD_UNIT = LNCSPMAJ.COMM_DATA.A_O_UNIT;
        } else if (LNCSPMAJ.COMM_DATA.A_O_TYP == '3') {
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_O_CAL_FALSE, LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_DEFAULT_PERD_UNIT() throws IOException,SQLException,Exception {
        if (LNCSCKPD.PROD_MOD == 'R' 
            && (LNCAPRDM.REC_DATA.BCAL_PERD == '2' 
            || LNCAPRDM.REC_DATA.BCAL_PERD == '3')) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        }
        if (LNCSCKPD.PROD_MOD == 'R' 
            && (LNCAPRDM.REC_DATA.BCAL_PERD == '1' 
            || LNCAPRDM.REC_DATA.BCAL_PERD == '4' 
            || LNCAPRDM.REC_DATA.BCAL_PERD == '5' 
            || LNCAPRDM.REC_DATA.BCAL_PERD == '6' 
            || LNCAPRDM.REC_DATA.BCAL_PERD == '7')) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = 1;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = 'M';
        }
        if (LNCSCKPD.PROD_MOD == 'C') {
            WS_VARIABLES.WS_PERD_UNIT.PERD = LNCAPRDM.REC_DATA.CAL_PERD;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        }
        if (LNCSCKPD.PROD_MOD == 'C' 
            && (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            || LNCAPRDM.REC_DATA.PAY_MTH == '1')) {
            WS_VARIABLES.WS_PERD_UNIT.PERD = 0;
            WS_VARIABLES.WS_PERD_UNIT.UNIT = 'D';
        }
    }
    public void R000_GET_P_CAL_DUE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        WS_VARIABLES.CAL_PERD = (short) (WS_VARIABLES.P_PERD - WS_VARIABLES.I_PERD);
        CEP.TRC(SCCGWA, WS_VARIABLES.P_PERD);
        CEP.TRC(SCCGWA, WS_VARIABLES.I_PERD);
        CEP.TRC(SCCGWA, WS_VARIABLES.CAL_PERD);
        if (WS_VARIABLES.I_UNIT == 'M') {
            SCCCLDT.MTHS = WS_VARIABLES.CAL_PERD;
        } else {
            SCCCLDT.DAYS = WS_VARIABLES.CAL_PERD;
        }
        SCCCLDT.DATE1 = LNCSPMAJ.COMM_DATA.A_INT_ED;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_VARIABLES.DATE = SCCCLDT.DATE2;
        if (LNCSPMAJ.COMM_DATA.A_P_GRA > 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
            SCCCLDT.MTHS = LNCSPMAJ.COMM_DATA.A_P_GRA;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_VARIABLES.GRC_DUE_DATE = SCCCLDT.DATE2;
            while (WS_VARIABLES.DATE < WS_VARIABLES.GRC_DUE_DATE) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_VARIABLES.DATE;
                if (WS_VARIABLES.P_UNIT == 'M') {
                    SCCCLDT.MTHS = WS_VARIABLES.P_PERD;
                } else {
                    SCCCLDT.DAYS = WS_VARIABLES.P_PERD;
                }
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_VARIABLES.DATE = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                CEP.TRC(SCCGWA, WS_VARIABLES.DATE);
            }
        }
        if (LNCAPRDM.REC_DATA.PAY_DAY < 28) {
            CEP.TRC(SCCGWA, "LX2");
            JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_VARIABLES.DATE;
            JIBS_tmp_str[1] = "" + LNCAPRDM.REC_DATA.PAY_DAY;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
            WS_VARIABLES.DATE = Integer.parseInt(JIBS_NumStr);
        } else {
            JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
            JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("02")) {
                CEP.TRC(SCCGWA, "LX3");
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = WS_VARIABLES.DATE;
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                if (SCCCKDT.LEAP_YEAR == 1) {
                    JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_VARIABLES.DATE;
                    JIBS_tmp_str[1] = "" + 29;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_VARIABLES.DATE = Integer.parseInt(JIBS_NumStr);
                } else {
                    JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_VARIABLES.DATE;
                    JIBS_tmp_str[1] = "" + 28;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_VARIABLES.DATE = Integer.parseInt(JIBS_NumStr);
                }
            } else {
                CEP.TRC(SCCGWA, "LX4");
                if (LNCAPRDM.REC_DATA.PAY_DAY <= 30) {
                    JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
                    JIBS_f0 = "";
                    for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + WS_VARIABLES.DATE;
                    JIBS_tmp_str[1] = "" + LNCAPRDM.REC_DATA.PAY_DAY;
                    JIBS_tmp_int = JIBS_tmp_str[1].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                    JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                    WS_VARIABLES.DATE = Integer.parseInt(JIBS_NumStr);
                } else {
                    CEP.TRC(SCCGWA, "LX5");
                    JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("04") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("06") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("09") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11")) {
                        JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_VARIABLES.DATE;
                        JIBS_tmp_str[1] = "" + 30;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                        WS_VARIABLES.DATE = Integer.parseInt(JIBS_NumStr);
                    } else {
                        JIBS_tmp_str[0] = "" + WS_VARIABLES.DATE;
                        JIBS_f0 = "";
                        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                        JIBS_NumStr = JIBS_f0 + WS_VARIABLES.DATE;
                        JIBS_tmp_str[1] = "" + 31;
                        JIBS_tmp_int = JIBS_tmp_str[1].length();
                        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(7 + 2 - 1);
                        WS_VARIABLES.DATE = Integer.parseInt(JIBS_NumStr);
                    }
                }
            }
        }
        WS_VARIABLES.P_CAL_DUE_DT = WS_VARIABLES.DATE;
    }
    public void S000_CALL_SVR_LNZCMSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONFIRM-SCHE", LNCCMSCH, true);
        if (LNCCMSCH.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCMSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SVR_LNZSCPAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-COM-PAIP", LNCSCPAI);
        if (LNCSCPAI.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSCPAI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
    }
    public void S000_CALL_LNZSCALT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CAL-TERM", LNCSCALT);
        if (LNCSCALT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSCALT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
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
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCILCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZILCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INST-AMT-CMP", LNCILCM);
        if (LNCILCM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCILCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRPLPI_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'R';
        LNRPLPI.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_VARIABLES.REPY_TYP;
        LNRPLPI.KEY.TERM = WS_VARIABLES.INPUT_TERM;
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
        if (LNCRPLPI.RC.RC_CODE == 0) {
            LNCRPLPI.FUNC = 'D';
            S000_CALL_LNZRPLPI();
            if (pgmRtn) return;
        }
        if (LNCRPLPI.RC.RC_CODE == 0) {
            WS_VARIABLES.INPUT_TERM += 1;
        }
    }
    public void S000_CALL_LNZRPAIP_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNCRPAIP.FUNC = 'R';
        LNRPAIP.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        LNRPAIP.KEY.PHS_NO = WS_VARIABLES.PHS_NO;
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
        if (LNCRPAIP.RC.RC_CODE == 0) {
            LNCRPAIP.FUNC = 'D';
            S000_CALL_LNZPAIPL();
            if (pgmRtn) return;
        }
        if (LNCRPAIP.RC.RC_CODE == 0) {
            WS_VARIABLES.PHS_NO += 1;
        }
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            if (LNCRPLPI.RETURN_INFO == 'N') {
                WS_COND_FLG.PLPI_END_FLG = 'Y';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPL() throws IOException,SQLException,Exception {
        LNCRPAIP.REC_PTR = LNRPAIP;
        LNCRPAIP.REC_LEN = 200;
        IBS.CALLCPN(SCCGWA, "LN-R-PAIPL-MAIN", LNCRPAIP);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.LN_ERR_PAIP_NOTFND)) {
                WS_COND_FLG.PAIP_FLG = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZFFSDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CAL-FSDT", LNCFFSDT);
        if (LNCFFSDT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCFFSDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPMAJ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            LNCSPMAJ.RC.RC_APP = LNCPPMQ.RC.RC_APP;
            LNCSPMAJ.RC.RC_RTNCODE = LNCPPMQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCSPMAJ.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCSPMAJ.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCSPMAJ.RC.RC_APP = "SC";
            LNCSPMAJ.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCSPMAJ.RC.RC_APP = "SC";
            LNCSPMAJ.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_AGRE_NOTFND, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTEXTN_PROC() throws IOException,SQLException,Exception {
        WS_COND_FLG.EXTN_FLG = 'Y';
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNREXTN.KEY.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.KEY.STATUS = '1';
        LNTEXTN_BR.rp = new DBParm();
        LNTEXTN_BR.rp.TableName = "LNTEXTN";
        LNTEXTN_BR.rp.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND STATUS = :LNREXTN.KEY.STATUS "
            + "AND VAL_DT >= :LNREXTN.KEY.VAL_DT";
        IBS.STARTBR(SCCGWA, LNREXTN, this, LNTEXTN_BR);
    }
    public void T000_READNEXT_LNTEXTN_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNREXTN, this, LNTEXTN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.EXTN_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTEXTN_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTEXTN_BR);
    }
    public void T000_STARTBR_LNTPPRP_PROC() throws IOException,SQLException,Exception {
        WS_COND_FLG.PPRP_FLG = 'Y';
        IBS.init(SCCGWA, LNRPPRP);
        LNRPPRP.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNRPPRP.KEY.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRPPRP.STATUS = '1';
        LNTPPRP_BR.rp = new DBParm();
        LNTPPRP_BR.rp.TableName = "LNTPPRP";
        LNTPPRP_BR.rp.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND STATUS = :LNRPPRP.STATUS "
            + "AND VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        IBS.STARTBR(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
    }
    public void T000_READNEXT_LNTPPRP_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.PPRP_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTPPRP_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPPRP_BR);
    }
    public void T000_STARTBR_LNTRCVD_PROC() throws IOException,SQLException,Exception {
        WS_COND_FLG.RCVD_FLG = 'Y';
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSPMAJ.COMM_DATA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.REPY_STS = '2';
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRCVD_BR.rp.where = "REPY_STS < > :LNRRCVD.REPY_STS";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_READNEXT_LNTRCVD_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RCVD_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTRCVD_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_DUE_DT_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_RD.where = "REPY_MTH IN ( 'P' , 'C' )";
        LNTPLPI_RD.fst = true;
        LNTPLPI_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
    }
    public void T000_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.WRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
        if (LNCSPMAJ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPMAJ=");
            CEP.TRC(SCCGWA, LNCSPMAJ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
