package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCUIBAL;
import com.hisun.BP.BPRPPM;
import com.hisun.BP.BPRPRMT;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCSCINM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCIGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTCA;
import com.hisun.SC.SCZBGWA;
import com.hisun.SC.SCZIGWA;
import com.hisun.TC.TCCTIA;
import com.hisun.TC.TCCTOA;

public class CIZSMESS {
    boolean pgmRtn = false;
    SCCTCA SCCTCA = new SCCTCA();
    SCCIGWA SCCIGWA = new SCCIGWA();
    TCCTOA TCCTOA = new TCCTOA();
    TCCTIA TCCTIA = new TCCTIA();
    String K_PARM_WHITE = "MESW";
    String K_PARM_BLACK = "MESB";
    String K_MMO = "CI";
    String K_BAT = "BAT";
    String K_BSP = "BSP";
    String K_CCY_CNY = "156";
    String K_CCY_HKD = "344";
    String K_CCY_USD = "840";
    String K_TEL_NO = "9999999999999";
    char K_CI_PEOP = '1';
    char K_CI_FIN = '2';
    char K_CI_BANK = '3';
    char K_N_TRANS = '1';
    char K_R_TRANS = '2';
    char K_REVERSAL = '9';
    String PGM_SCZIGWA = "SCZIGWA ";
    String PGM_SCZBGWA = "SCZBGWA ";
    String SMS_AGT_TYP = "141200002010";
    String SMS_TEMPID_01 = "0115240";
    String SMS_TEMPID_02 = "0115540";
    String SMS_TEMPID_03 = "0119000";
    String SMS_TEMPID_04 = "0260122";
    String SMS_TEMPID_05 = "0260132";
    String SMS_TEMPID_06 = "0261080";
    String SMS_TEMPID_07 = "0263010";
    String SMS_TEMPID_08 = "0263019";
    String SMS_TEMPID_09 = "0263020";
    String SMS_TEMPID_10 = "0263029";
    String SMS_TEMPID_11 = "0263030";
    String SMS_TEMPID_12 = "0263071";
    String SMS_TEMPID_13 = "0263080";
    String SMS_TEMPID_14 = "0263089";
    String SMS_TEMPID_15 = "0263090";
    String SMS_TEMPID_16 = "0263101";
    String SMS_TEMPID_17 = "0261081";
    String SMS_TEMPID_18 = "0261082";
    String SMS_TEMPID_19 = "0352300";
    String SMS_TEMPID_20 = "0358040";
    String SMS_TEMPID_21 = "0263040";
    String SMS_TEMPID_22 = "0263050";
    String SMS_P_AC_SIGN = "21";
    String SMS_C_AC_SIGN = "31";
    String SMS_HOS_AC_SIGN = "25";
    String SMS_P_PW_SIGN = "23";
    String SMS_P_IQ_SIGN = "24";
    char SMS_AC_ENTY = '1';
    char SMS_CARD_ENTY = '2';
    char SMS_VA_ENTY = '3';
    String CPN_PARM_MT = "BP-PARM-READ";
    String K_HIS_RMK = "SEND MESSAGE";
    String K_HIS_CPY = "CICSMESS";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    CIZSMESS_WS_MESS_WHITE WS_MESS_WHITE = new CIZSMESS_WS_MESS_WHITE();
    CIZSMESS_WS_MESS_BLACK WS_MESS_BLACK = new CIZSMESS_WS_MESS_BLACK();
    String WS_MESS_TEMP = " ";
    short WS_POS = 0;
    short WS_TMP_CNT = 0;
    short WS_MMO_CNT = 0;
    short WS_AMT_CNT = 0;
    String WS_AC_NO = " ";
    String WS_MMO = " ";
    String WS_TEL_NO = " ";
    String WS_CI_NO = " ";
    String WS_F_CINO = " ";
    String WS_CI_OIC = " ";
    String WS_SERVE = " ";
    short WS_I = 0;
    String WS_CARD_HOLDER = " ";
    String WS_SIGN_AGT_NO = " ";
    String WS_SIGN_SMSID = " ";
    double WS_SIGN_CAMT = 0;
    double WS_SIGN_DAMT = 0;
    char WS_ENTY_TYP = ' ';
    String WS_AC_CNM = " ";
    String WS_AC_CNM1 = " ";
    String WS_TCARD_NO = " ";
    CIZSMESS_WS_ERR_RC WS_ERR_RC = new CIZSMESS_WS_ERR_RC();
    int WS_SEND_BR = 0;
    String WS_CHG_AC = " ";
    String WS_CHGS_AC = " ";
    char WS_CHG_TYPE = ' ';
    String WS_FREE_AC = " ";
    String WS_OTH_BANK_TEL = " ";
    String WS_CARD_HLD_CINO = " ";
    char WS_SEND_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_CHG_ACLAST_FLG = ' ';
    char WS_SIGN_CONT_FLG = ' ';
    char WS_PRMR_W_FLG = ' ';
    char WS_PRMR_B_FLG = ' ';
    char WS_PEOP_COMP_FLG = ' ';
    char WS_SIGN_CONTRACT_2301 = ' ';
    char WS_SIGN_CONTRACT_2302 = ' ';
    char WS_SIGN_CONTRACT_2303 = ' ';
    char WS_SIGN_CONTRACT_2401 = ' ';
    char WS_SIGN_CONTRACT_2402 = ' ';
    char WS_SIGN_CISMS_FLG = ' ';
    char WS_SMS_VA_FLG = ' ';
    char WS_BAS_FLG = ' ';
    char WS_ACR_FLG = ' ';
    char WS_NAM_FLG = ' ';
    char WS_REMARK_FLG = ' ';
    char WS_WS_KJ_CONT_FLG = ' ';
    char WS_CNT_FLG = ' ';
    char WS_ENTYTYP_FLG = ' ';
    char WS_ENTYTYPS_FLG = ' ';
    char WS_DDZIMMST_AC_FLG = ' ';
    char WS_CHG_ENTYNO_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPPM BPRPPM = new BPRPPM();
    CIRRELN CIRRELN = new CIRRELN();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRNAM CIRNAM = new CIRNAM();
    CIRCNT CIRCNT = new CIRCNT();
    DCCILNKR DCCILNKR = new DCCILNKR();
    CICSSMS CICSSMS = new CICSSMS();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    char DFHCOMMAREA = ' ';
    CICSMESS CICSMESS;
    SCCGWA SCCGWA;
    public void MP(SCCGWA SCCGWA, CICSMESS CICSMESS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMESS = CICSMESS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMESS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CICSMESS = (CICSMESS) DFHCOMMAREA;
        WS_ERR_MSG = " ";
        IBS.init(SCCGWA, CICSMESS.OUPT_INFO);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, SCCTCA);
        IBS.init(SCCGWA, TCCTOA);
        IBS.init(SCCGWA, TCCTIA);
        SCCTCA.TOA_PTR = TCCTOA;
        SCCTCA.TIA_PTR = TCCTIA;
        TCCTIA.SERV_INPUT_HEAD.SERV_CALL_AREA.TR_BANK = CICSMESS.IPUT_INFO.BK;
        CICSMESS.RC.RC_MMO = K_MMO;
        CICSMESS.RC.RC_CODE = 0;
        A01_GEN_GWA();
        if (pgmRtn) return;
    }
    public void A01_GEN_GWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCIGWA);
        SCZIGWA SCZIGWA1 = new SCZIGWA();
        SCZIGWA1.MP(SCCGWA, SCCIGWA);
        CIZSMESS_WL5 = SCCIGWA.GWA_PTR;
        SCCTCA.GWA_PTR = SCCIGWA.GWA_PTR;
        CEP.TRC(SCCGWA, SCCTCA);
        SCZBGWA SCZBGWA2 = new SCZBGWA();
        SCZBGWA2.MP(SCCGWA, SCCTCA);
        CIZSMESS_WL5 = SCCTCA.GWA_PTR;
        SCCGWA.COMM_AREA.TERM_ID = "123456789012";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_SEND_FLG = 'Y';
        CEP.TRC(SCCGWA, "MESS INPUT");
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.DATE);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.JRNNO);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.SEQ_NO);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.BK);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.SEND_BR);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.TIME);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.APPCORD);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.MMO);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CHNL_NO);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AC_NO1);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CARD_NO1);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AC_NO2);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CARD_NO2);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AC_NO3);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CARD_NO3);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CCY1);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AMT1);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CCY2);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AMT2);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CCY3);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AMT3);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.CCY4);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AMT4);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AC_TYPE);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.DC_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.ACPA_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.SUCFAI_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AUTOREP_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.FORDER_LMT_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.FEE_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AC_OPEN_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.PASSWORD_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.ADJUST_AC_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.LN_AC_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.INT_UNLOAD_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.OTH_FLG);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.NUM1);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.NUM2);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.NUM3);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.DATE1);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.DATE2);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.BP_SEQ);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.AC_NAME);
        CEP.TRC(SCCGWA, CICSMESS.IPUT_INFO.ACPA_FLG);
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B002_GET_ACCU_INFO();
        if (pgmRtn) return;
        B003_GET_MESS_WHITE();
        if (pgmRtn) return;
        if (WS_SEND_FLG == 'Y') {
            if (WS_PRMR_W_FLG == 'Y') {
                B004_WHITE_PRMR_PROC();
                if (pgmRtn) return;
            } else {
                B005_ALL_LOGIC_PROC();
                if (pgmRtn) return;
            }
            B007_OUTPUT_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_SEND_FLG);
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CICSMESS.IPUT_INFO.AC_NO1.trim().length() == 0 
            && CICSMESS.IPUT_INFO.CARD_NO1.trim().length() == 0) {
            WS_SEND_FLG = 'N';
            IBS.CPY2CLS(SCCGWA, "ME0001", CICSMESS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CICSMESS.IPUT_INFO.ACPA_FLG == 'Y') {
            if (CICSMESS.IPUT_INFO.CCY1.trim().length() == 0 
                || CICSMESS.IPUT_INFO.DC_FLG == ' ') {
                WS_SEND_FLG = 'N';
                IBS.CPY2CLS(SCCGWA, "ME0002", CICSMESS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (CICSMESS.IPUT_INFO.AC_NO1.equalsIgnoreCase(CICSMESS.IPUT_INFO.CARD_NO1)) {
            CICSMESS.IPUT_INFO.CARD_NO1 = " ";
        }
    }
    public void B002_GET_ACCU_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        if (CICSMESS.IPUT_INFO.CARD_NO1.trim().length() > 0) {
            CIRACR.KEY.AGR_NO = CICSMESS.IPUT_INFO.CARD_NO1;
            WS_ENTYTYP_FLG = '2';
        } else {
            if (CICSMESS.IPUT_INFO.AC_NO1.trim().length() > 0) {
                CIRACR.KEY.AGR_NO = CICSMESS.IPUT_INFO.AC_NO1;
                WS_ENTYTYP_FLG = '1';
            }
        }
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (CIRACR.ENTY_TYP != WS_ENTYTYP_FLG) {
            WS_CHG_TYPE = CIRACR.ENTY_TYP;
        }
        CEP.TRC(SCCGWA, WS_CHG_TYPE);
        if (WS_ACR_FLG == 'N') {
            IBS.init(SCCGWA, DCCPACTY);
            if (WS_ENTYTYP_FLG == '2') {
                DCCPACTY.INPUT.FUNC = '2';
            }
            if (WS_ENTYTYP_FLG == '1') {
                DCCPACTY.INPUT.FUNC = '3';
            }
            DCCPACTY.INPUT.AC = CIRACR.KEY.AGR_NO;
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            if (DCCPACTY.RC.RC_CODE == 0) {
                if (WS_ENTYTYP_FLG == '2') {
                    WS_CHG_AC = DCCPACTY.OUTPUT.N_CARD_NO;
                    CICSMESS.IPUT_INFO.CARD_NO1 = DCCPACTY.OUTPUT.N_CARD_NO;
                }
                if (WS_ENTYTYP_FLG == '1') {
                    WS_CHG_AC = DCCPACTY.OUTPUT.STD_AC;
                    WS_DDZIMMST_AC_FLG = 'Y';
                }
                IBS.init(SCCGWA, CIRACR);
                CIRACR.KEY.AGR_NO = WS_CHG_AC;
                T000_READ_CITACR();
                if (pgmRtn) return;
                if (WS_ACR_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, "ME0011", CICSMESS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                if (CICSMESS.IPUT_INFO.LN_AC_FLG != '1') {
                    IBS.init(SCCGWA, DCCPACTY);
                    DCCPACTY.INPUT.FUNC = '3';
                    DCCPACTY.INPUT.AC = CIRACR.KEY.AGR_NO;
                    S000_CALL_DCZPACTY();
                    if (pgmRtn) return;
                    if (DCCPACTY.RC.RC_CODE == 0) {
                        WS_CHG_AC = DCCPACTY.OUTPUT.STD_AC;
                        WS_DDZIMMST_AC_FLG = 'Y';
                        IBS.init(SCCGWA, CIRACR);
                        CIRACR.KEY.AGR_NO = WS_CHG_AC;
                        T000_READ_CITACR();
                        if (pgmRtn) return;
                        if (WS_ACR_FLG == 'N') {
                            IBS.CPY2CLS(SCCGWA, "ME0036", CICSMESS.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        } else {
                            WS_CHG_TYPE = CIRACR.ENTY_TYP;
                            WS_FREE_AC = CICSMESS.IPUT_INFO.CARD_NO1;
                            CEP.TRC(SCCGWA, WS_FREE_AC);
                            CICSMESS.IPUT_INFO.CARD_NO1 = " ";
                        }
                    } else {
                        IBS.CPY2CLS(SCCGWA, "ME0037", CICSMESS.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else {
                    IBS.CPY2CLS(SCCGWA, "ME0012", CICSMESS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_ENTYTYP_FLG == '2') {
            WS_SEND_BR = CIRACR.OPN_BR;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        WS_F_CINO = CIRACR.CI_NO;
        WS_CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (WS_BAS_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, "ME0013", CICSMESS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        if (CIRBAS.CI_TYP == K_CI_PEOP) {
            WS_PEOP_COMP_FLG = 'P';
            if ((CICSMESS.IPUT_INFO.CARD_NO1.trim().length() > 0 
                && WS_CHG_TYPE == ' ' 
                && CICSMESS.IPUT_INFO.AC_OPEN_FLG == 'O') 
                || CICSMESS.IPUT_INFO.MMO.equalsIgnoreCase("X21")) {
                CEP.TRC(SCCGWA, "V16A01");
                IBS.init(SCCGWA, DCCUCINF);
                if (WS_CHG_AC.trim().length() > 0) {
                    DCCUCINF.CARD_NO = WS_CHG_AC;
                } else {
                    DCCUCINF.CARD_NO = CICSMESS.IPUT_INFO.CARD_NO1;
                }
                S000_CALL_DCZUCINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_TCARD_NO);
                WS_OTH_BANK_TEL = DCCUCINF.CARD_TEL_NO;
                CEP.TRC(SCCGWA, DCCUCINF.CARD_TEL_NO);
                CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CINO);
                if (DCCUCINF.CARD_HLDR_CINO.trim().length() > 0) {
                    WS_CARD_HLD_CINO = DCCUCINF.CARD_HLDR_CINO;
                }
            }
        }
        if (CIRBAS.CI_TYP == K_CI_FIN 
            || CIRBAS.CI_TYP == K_CI_BANK) {
            WS_PEOP_COMP_FLG = 'C';
