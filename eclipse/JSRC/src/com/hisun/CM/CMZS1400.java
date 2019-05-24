package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.IB.*;
import com.hisun.DC.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.TD.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS1400 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_CNY = "156";
    String K_OUTPUT_FMT = "CM140";
    String WK_PGM_CIZQACRI = "CI-INQ-ACR-INF      ";
    String WK_PGM_CIZCUST = "CI-INQ-CUST";
    String WK_PGM_DCZUCINF = "DC-U-CARD-INF       ";
    String WK_PGM_DCZURHLD = "DC-UNIT-RHLD        ";
    String WK_PGM_BPZUSBOX = "BP-U-SUB-CBOX       ";
    String WK_PGM_BPZTLVBF = "BP-R-ADW-TLVB       ";
    String WK_PGM_BPZSPLIF = "BP-S-ALC-PVAL-MAINT ";
    String WK_PGM_DDZUDRAC = "DD-UNIT-DRAW-PROC   ";
    String WK_PGM_IBZDRAC = "IB-IBZDRAC          ";
    String WK_PGM_BPZUABOX = "BP-U-ADD-CBOX       ";
    String WK_PGM_DDZUCRAC = "DD-UNIT-DEP-PROC    ";
    String WK_PGM_IBZCRAC = "IB-IBZCRAC          ";
    String WK_PGM_DCZUHLD = "DC-UNIT-HLD";
    String WK_PGM_AIZUUPIA = "AI-U-UPDATE-IA      ";
    String WK_PGM_BPZFFCON = "BP-F-F-CON-CHG-INFO ";
    String WK_PGM_BPZFFTXI = "BP-F-F-TX-INFO      ";
    String WK_PGM_TDZACDRU = "TD-ACDR-UNT";
    CMZS1400_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMZS1400_WS_TEMP_VARIABLE();
    CMZS1400_WS_CUS_INF[] WS_CUS_INF = new CMZS1400_WS_CUS_INF[40];
    CMZS1400_WS_OTH_INF[] WS_OTH_INF = new CMZS1400_WS_OTH_INF[40];
    CMZS1400_WS_INR_AC_INF[] WS_INR_AC_INF = new CMZS1400_WS_INR_AC_INF[40];
    CMZS1400_WS_TD_INF[] WS_TD_INF = new CMZS1400_WS_TD_INF[40];
    CMZS1400_WS_FEE_INF[] WS_FEE_INF = new CMZS1400_WS_FEE_INF[5];
    String WS_CUS_INT_IN_AC = " ";
    String WS_IN_AC = " ";
    String WS_IN_AC_APP = " ";
    char WS_IN_AC_CI_TYP = ' ';
    double WS_IN_AMT = 0;
    String WS_IN_RLT_AC = " ";
    String WS_IN_RLT_ACNM = " ";
    char WS_DEAL_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRI CICQACRI = new CICQACRI();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DCCUCINF DCCUCINF = new DCCUCINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCSPLIF BPCSPLIF = new BPCSPLIF();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    TDCACDRU TDCACDRU = new TDCACDRU();
    CMCO1400 CMCO1400 = new CMCO1400();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    AICPUITM AICPUITM = new AICPUITM();
    CICACCU CICACCU = new CICACCU();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCQVATR BPCQVATR = new BPCQVATR();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCSFHIS BPCSFHIS = new BPCSFHIS();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS1400 CMCS1400;
    public CMZS1400() {
        for (int i=0;i<40;i++) WS_CUS_INF[i] = new CMZS1400_WS_CUS_INF();
        for (int i=0;i<40;i++) WS_OTH_INF[i] = new CMZS1400_WS_OTH_INF();
        for (int i=0;i<40;i++) WS_INR_AC_INF[i] = new CMZS1400_WS_INR_AC_INF();
        for (int i=0;i<40;i++) WS_TD_INF[i] = new CMZS1400_WS_TD_INF();
        for (int i=0;i<5;i++) WS_FEE_INF[i] = new CMZS1400_WS_FEE_INF();
    }
    public void MP(SCCGWA SCCGWA, CMCS1400 CMCS1400) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS1400 = CMCS1400;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZS1400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, CMCS1400.REMARK);
        B100_INPUT_CHECK_PROC();
        B200_CHARGR_AC_APP_PROC();
        B300_INQ_OTH_IFO_PROC();
        B400_APP_CHECK_PROC();
        B500_FEE_AC_CHECK_PROC();
        B600_AC_UPDATE_PROC();
        B700_FEE_PROC();
        B900_OUTPUT_DATA();
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                WS_DEAL_FLG = 'Y';
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ);
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 'D' 
                    && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == ' ' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYPE_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.trim().length() == 0 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY);
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY.trim().length() == 0 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL);
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT == 0 
                    && !CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL);
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ);
                    if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_SEQ_MUST_IN);
                    }
                    WS_CUS_INF[CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ-1].WS_INT_SEQ = WS_TEMP_VARIABLE.WS_I;
                    CEP.TRC(SCCGWA, WS_CUS_INF[CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ-1].WS_INT_SEQ);
                    if (CMCS1400.AC_DATA[CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ-1].CUS_AC.trim().length() == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_SOURCE_INT_AC_IS_NUL);
                    }
                }
            }
        }
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 2; WS_TEMP_VARIABLE.WS_J += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
            CEP.TRC(SCCGWA, CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACSQ);
            CEP.TRC(SCCGWA, CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACNM);
            CEP.TRC(SCCGWA, CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_BKNM);
            if ((CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACSQ != 0) 
                && (CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_AC.trim().length() == 0 
                || CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACNM.trim().length() == 0 
                || CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_BKNM.trim().length() == 0)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_OUTAC_M_INPUT);
            }
        }
        for (WS_TEMP_VARIABLE.WS_K = 1; WS_TEMP_VARIABLE.WS_K <= 5; WS_TEMP_VARIABLE.WS_K += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_K);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FE_CI_NO);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CCY);
            CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_AMT);
            if ((CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ != 0 
                && CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ == 0)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FEEIFO_M_INPUT);
            }
            if ((CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ == 0 
                && CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ != 0)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FEEIFO_M_INPUT);
            }
            if ((CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ != 0 
                && CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ != 0) 
                && (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.trim().length() == 0 
                || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CCY.trim().length() == 0 
                || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_AMT == ' ')) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FEEIFO_M_INPUT);
            }
            if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ != 0) {
                WS_TEMP_VARIABLE.WS_G = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ;
                WS_TEMP_VARIABLE.WS_A = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ;
                if ((WS_TEMP_VARIABLE.WS_G > 40) 
                    || (WS_TEMP_VARIABLE.WS_A > 40)) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_REC_SEQ_ERR);
                }
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FR_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_G-1].CUS_AC;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_IN_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_A-1].CUS_AC;
            }
            if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_AMT != 0 
                && (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.trim().length() == 0 
                || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.charAt(0) == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_INPUT_FEE_CODE);
            }
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_K);
            if ((CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ != 0 
                && CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ != 0)) {
                CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO);
                CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD);
                if (((CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO.trim().length() == 0 
                    || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO.charAt(0) == 0X00) 
                    && (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.trim().length() == 0 
                    || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.charAt(0) == 0X00))) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ACNO_PROD_M_INPUT);
                } else {
                    WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_PROD_CD = " ";
                    if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.trim().length() == 0 
                        || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.charAt(0) == 0X00) {
                        IBS.init(SCCGWA, CICQACRI);
                        CICQACRI.FUNC = 'A';
                        CICQACRI.DATA.AGR_NO = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO;
                        S_CALL_CIZQACRI();
                        WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_PROD_CD = CICQACRI.O_DATA.O_PROD_CD;
                    } else {
                        WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_PROD_CD = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD;
                    }
                }
            }
        }
        if (WS_DEAL_FLG != 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NOT_ALLOW_EMPTY_TRAD);
        }
    }
    public void B200_CHARGR_AC_APP_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP != 'I') {
                    B220_INQ_CHARGE_AC_APP();
                } else {
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "IT";
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM);
            }
        }
    }
    public void B220_INQ_CHARGE_AC_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        S_CALL_CIZQACRI();
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO = CICQACRI.O_DATA.O_AGR_NO;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ENTY_TYP = CICQACRI.O_DATA.O_ENTY_TYP;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_NO = CICQACRI.O_DATA.O_CI_NO;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP = CICQACRI.O_DATA.O_CI_TYP;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_STS = CICQACRI.O_DATA.O_STS;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_STSW = CICQACRI.O_DATA.O_STSW;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_PROD_CD = CICQACRI.O_DATA.O_PROD_CD;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP = CICQACRI.O_DATA.O_CNTRCT_TYP;
        if (CICQACRI.O_DATA.O_FRM_APP_OLD.trim().length() > 0) {
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP_OLD;
        } else {
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
        }
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_SHOW_FLG = CICQACRI.O_DATA.O_SHOW_FLG;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_ENM = CICQACRI.O_DATA.O_AC_ENM;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_SMS_FLG = CICQACRI.O_DATA.O_SMS_FLG;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR = CICQACRI.O_DATA.O_OPN_BR;
        if (!WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY);
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP);
            if ((!CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY.equalsIgnoreCase(K_CNY)) 
                && (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == ' ' 
                || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
            }
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK);
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.trim().length() == 0 
                || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_CD_ERR);
            }
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPCPARMC);
            BPCPRMR.FUNC = ' ';
            BPCPARMC.KEY.TYP = "PARMC";
            if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
            JIBS_tmp_int = BPCPARMC.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
            BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK = "";
            JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK += " ";
            if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
            JIBS_tmp_int = BPCPARMC.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
            BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
            CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_MMO_NOT_EXIST);
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                    CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
                }
            }
            CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC);
            CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM);
            CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
        } else {
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
            JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR = 0;
            else WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR = Integer.parseInt(CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            S000_CALL_AIZPQMIB();
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP);
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD") 
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP.equalsIgnoreCase("033")) {
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "DG";
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD") 
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP.equalsIgnoreCase("033")) {
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "TG";
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        }
    }
    public void B400_APP_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                if ((WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD") 
                    || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TG")) 
                    && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NO_TD_DEPOSIT);
                }
            }
        }
    }
    public void B300_INQ_OTH_IFO_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) 
                && (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ != 0)) {
                WS_TEMP_VARIABLE.WS_M = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACSQ;
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_M);
                if (WS_TEMP_VARIABLE.WS_M > 40) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_REC_SEQ_ERR);
                }
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_M-1].CUS_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_M-1].AC_TYP;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_M-1].WS_CUS_AC_CNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_M-1].CUS_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_M-1].WS_CUS_AC_CNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM = " ";
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OPP_AC_APP = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP;
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_M);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC_TYP);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
            }
        }
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 2; WS_TEMP_VARIABLE.WS_J += 1) {
            if ((CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACSQ != 0 
                && CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACSQ != 0X00)) {
                WS_TEMP_VARIABLE.WS_M = CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACSQ;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_AC = CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_ACNM = CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_ACNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_BKNM = CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_BKNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_BKNO = CMCS1400.OUT_AC_D[WS_TEMP_VARIABLE.WS_J-1].OUT_BKNO;
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_M);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_BKNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_M-1].WS_RLT_BKNO);
            }
        }
    }
    public void B500_FEE_AC_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_K = 1; WS_TEMP_VARIABLE.WS_K <= 5; WS_TEMP_VARIABLE.WS_K += 1) {
            if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ != 0) {
                WS_TEMP_VARIABLE.WS_G = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ;
                WS_TEMP_VARIABLE.WS_A = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_INSQ;
                if ((WS_TEMP_VARIABLE.WS_G > 40) 
                    || (WS_TEMP_VARIABLE.WS_A > 40)) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_REC_SEQ_ERR);
                }
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FR_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_G-1].CUS_AC;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_IN_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_A-1].CUS_AC;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CCY_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_G-1].CCY_TYP;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP = WS_CUS_INF[WS_TEMP_VARIABLE.WS_G-1].WS_CUS_FRM_APP;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CI_TYP = WS_CUS_INF[WS_TEMP_VARIABLE.WS_G-1].WS_CUS_CI_TYP;
                CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP);
                CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CI_TYP);
                if (WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP.equalsIgnoreCase("DD")) {
                    if (WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CI_TYP == '1') {
                        WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_AC_TYP = '5';
                    } else {
                        WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_AC_TYP = '0';
                    }
                } else if (WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP.equalsIgnoreCase("DC")) {
                    WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_AC_TYP = '4';
                } else if (WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP.equalsIgnoreCase("AI")) {
                    WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_AC_TYP = '3';
                } else {
                    WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_AC_TYP = '0';
                }
            }
            if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_AMT != 0 
                && (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.trim().length() == 0 
                || CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.charAt(0) == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_INPUT_FEE_CODE);
            }
        }
    }
    public void B600_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "FORWARDPROCESS");
            B610_AC_UPDATE_PROC();
        } else {
            CEP.TRC(SCCGWA, "CANCELPROCESS");
            B620_AC_UPDATE_PROC();
        }
    }
    public void B610_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                    && (!CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1"))) {
                    B620_CUS_AC_UPDATE_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, "INT PROCESS");
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                    && (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1"))) {
                    B620_CUS_AC_UPDATE_PROC();
                }
            }
        }
    }
    public void B620_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                    && (!CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1")) 
                    && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
                    B620_CUS_AC_UPDATE_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, "INT PROCESS");
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                    && (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1")) 
                    && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
                    B620_CUS_AC_UPDATE_PROC();
                }
            }
        }
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                    && (!CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1")) 
                    && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C') {
                    B620_CUS_AC_UPDATE_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, "INT PROCESS");
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if ((CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                    || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                    && (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(0, 1).equalsIgnoreCase("1")) 
                    && CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C') {
                    B620_CUS_AC_UPDATE_PROC();
                }
            }
        }
    }
    public void B620_CUS_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            B622_DR_DDAC_PROC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")) {
            B623_DR_IBAC_PROC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD")) {
            B624_DR_TDAC_PROC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")
            || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TG")) {
            if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO.trim().length() > 0) {
                B625_DR_GDAC_PROC();
            } else {
                B626_DR_GDAC_PROC();
            }
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")
            || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")) {
            B632_CR_DDAC_PROC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")) {
            B633_CR_IBAC_PROC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")
            || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            B661_INR_AC_UPDATE_RPOC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IT")
            || CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IT")) {
            B662_ITM_AC_UPDATE_RPOC();
        } else {
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B622_DR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW = 'N';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP);
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            DDCUDRAC.BV_TYP = '1';
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUDRAC.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '1') {
                DDCUDRAC.BV_TYP = '2';
            }
        }
        DDCUDRAC.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        DDCUDRAC.CCY_TYPE = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUDRAC.TX_AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.TX_TYPE = 'T';
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "102";
        } else {
            DDCUDRAC.TX_MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            DDCUDRAC.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
            DDCUDRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUDRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUDRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUDRAC.RLT_BANK = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
        } else {
            DDCUDRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUDRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUDRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUDRAC.RLT_BANK = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
        }
        DDCUDRAC.REMARKS = CMCS1400.REMARK;
        S_CALL_DDZUDRAC();
    }
    public void B623_DR_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        IBCPOSTA.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        IBCPOSTA.CCY_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.TX_MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        IBCPOSTA.NARR = CMCS1400.REMARK;
        IBCPOSTA.OTH_AC_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        IBCPOSTA.OTH_CNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        IBCPOSTA.OTH_BR_CNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        if (WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO.trim().length() == 0) IBCPOSTA.OTH_BR = 0;
        else IBCPOSTA.OTH_BR = Integer.parseInt(WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO);
        S_CALL_IBZDRAC();
    }
    public void B624_DR_TDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.OPT = '0';
        TDCACDRU.MAC_CNO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        TDCACDRU.AC_SEQ = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        TDCACDRU.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        TDCACDRU.CCY_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        TDCACDRU.TXN_AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OPP_AC_APP.equalsIgnoreCase("AI") 
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OPP_AC_APP.equalsIgnoreCase("IT")) {
            TDCACDRU.CT_FLG = '1';
        } else {
            TDCACDRU.CT_FLG = '2';
        }
        CEP.TRC(SCCGWA, TDCACDRU.CT_FLG);
        TDCACDRU.OPP_AC_CNO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        TDCACDRU.STL_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        TDCACDRU.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM;
        TDCACDRU.CHK_PSW_FLG = 'N';
        if (WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC.equalsIgnoreCase(WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC)) {
            TDCACDRU.INOUT = '1';
        } else {
            TDCACDRU.INOUT = '2';
        }
        TDCACDRU.PRDMO_CD = "ALL";
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
        CEP.TRC(SCCGWA, TDCACDRU.BUSI_CTLW);
        TDCACDRU.TXN_MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        TDCACDRU.REMARK = CMCS1400.REMARK;
        S_CALL_TDZACDRU();
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT;
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT = TDCACDRU.PAYING_INT;
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT - CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT - WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT;
        CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_PAL_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT);
        if (WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT > CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT) {
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT - CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        }
        if (WS_IN_AMT != 0) {
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ);
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INT_AC_EMPTY);
            }
            CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].TXN_AMT = WS_IN_AMT;
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].TXN_AMT);
        } else {
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ);
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ != 0) {
                CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].DC_FLG = ' ';
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].DC_FLG);
            }
        }
    }
    public void B625_DR_GDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR);
        GDCUMPDR.INPUT.FUNC = 'D';
        GDCUMPDR.INPUT.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        GDCUMPDR.INPUT.CTA_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO;
        GDCUMPDR.INPUT.REF_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO;
        GDCUMPDR.INPUT.SEQ = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        GDCUMPDR.INPUT.AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        GDCUMPDR.OUTPUT.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        GDCUMPDR.INPUT.MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        GDCUMPDR.INPUT.RMK = CMCS1400.REMARK;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            GDCUMPDR.INPUT.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        } else {
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        }
        S000_CALL_GDZUMPDR();
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
        if (GDCUMPDR.OUTPUT.INT != 0) {
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ);
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INT_AC_EMPTY);
            }
            CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].TXN_AMT = GDCUMPDR.OUTPUT.INT;
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].TXN_AMT);
        } else {
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ);
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ != 0) {
                CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].DC_FLG = ' ';
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].DC_FLG);
            }
        }
    }
    public void B626_DR_GDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR);
        GDCUMPDR.INPUT.FUNC = 'D';
        GDCUMPDR.INPUT.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        GDCUMPDR.INPUT.SEQ = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        GDCUMPDR.INPUT.AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        GDCUMPDR.OUTPUT.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        GDCUMPDR.INPUT.MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        GDCUMPDR.INPUT.RMK = CMCS1400.REMARK;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            GDCUMPDR.INPUT.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        } else {
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        }
        S000_CALL_GDZUDBDR();
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
        if (GDCUMPDR.OUTPUT.INT != 0) {
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ);
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INT_AC_EMPTY);
            }
            CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].TXN_AMT = GDCUMPDR.OUTPUT.INT;
            CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].TXN_AMT);
        } else {
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ);
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ != 0) {
                CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].DC_FLG = ' ';
                CEP.TRC(SCCGWA, CMCS1400.AC_DATA[WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INT_SEQ-1].DC_FLG);
            }
        }
    }
    public void B632_CR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP);
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCUCRAC.BV_TYP = '1';
            DDCUCRAC.CARD_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD") 
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")) {
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '1') {
                DDCUCRAC.BV_TYP = '2';
            }
            DDCUCRAC.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        }
        DDCUCRAC.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        DDCUCRAC.CCY_TYPE = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUCRAC.TX_AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            DDCUCRAC.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
            DDCUCRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUCRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUCRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUCRAC.RLT_BANK = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
        } else {
            DDCUCRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUCRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUCRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUCRAC.RLT_BANK = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
        }
        DDCUCRAC.REMARKS = CMCS1400.REMARK;
        S_CALL_DDZUCRAC();
    }
    public void B633_CR_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        IBCPOSTA.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        IBCPOSTA.CCY_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.TX_MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        IBCPOSTA.NARR = CMCS1400.REMARK;
        IBCPOSTA.OTH_AC_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        IBCPOSTA.OTH_CNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        IBCPOSTA.OTH_BR_CNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        if (WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO.trim().length() == 0) IBCPOSTA.OTH_BR = 0;
        else IBCPOSTA.OTH_BR = Integer.parseInt(WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO);
        S_CALL_IBZCRAC();
    }
    public void B640_CR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_IN_AC;
        S_CALL_CIZQACRI();
        if (CICQACRI.O_DATA.O_FRM_APP_OLD.trim().length() > 0) {
            WS_IN_AC_APP = CICQACRI.O_DATA.O_FRM_APP_OLD;
        } else {
            WS_IN_AC_APP = CICQACRI.O_DATA.O_FRM_APP;
        }
        WS_IN_AC_CI_TYP = CICQACRI.O_DATA.O_CI_TYP;
        IBS.init(SCCGWA, DDCUCRAC);
        CEP.TRC(SCCGWA, WS_IN_AC_APP);
        CEP.TRC(SCCGWA, WS_IN_AC_CI_TYP);
        if (WS_IN_AC_APP.equalsIgnoreCase("DC")) {
            DDCUCRAC.BV_TYP = '1';
            DDCUCRAC.CARD_NO = WS_IN_AC;
        } else if (WS_IN_AC_APP.equalsIgnoreCase("DD")) {
            if (WS_IN_AC_CI_TYP == '1') {
                DDCUCRAC.BV_TYP = '2';
            }
            DDCUCRAC.AC = WS_IN_AC;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        DDCUCRAC.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        DDCUCRAC.CCY_TYPE = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUCRAC.TX_AMT = WS_IN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        }
        DDCUCRAC.OTHER_AC = WS_IN_RLT_AC;
        DDCUCRAC.OTHER_AC_NM = WS_IN_RLT_ACNM;
        DDCUCRAC.REMARKS = CMCS1400.REMARK;
        S_CALL_DDZUCRAC();
    }
    public void B660_INR_UPDATE_RPOC() throws IOException,SQLException,Exception {
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '1') {
            B661_INR_AC_UPDATE_RPOC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '2') {
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '3') {
            B661_INR_AC_UPDATE_RPOC();
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '4') {
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '5') {
        } else if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '6') {
        } else {
        }
    }
    public void B661_INR_AC_UPDATE_RPOC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
            AICUUPIA.DATA.EVENT_CODE = "DR";
        } else {
            AICUUPIA.DATA.EVENT_CODE = "CR";
        }
        AICUUPIA.DATA.AC_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        AICUUPIA.DATA.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        AICUUPIA.DATA.AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO;
        AICUUPIA.DATA.THEIR_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        AICUUPIA.DATA.ORI_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        AICUUPIA.DATA.PAY_MAN = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        AICUUPIA.DATA.PAY_BKNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        AICUUPIA.DATA.THEIR_AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICUUPIA.DATA.NARR_CD = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BUS_RMK;
        AICUUPIA.DATA.DESC = CMCS1400.REMARK;
        AICUUPIA.DATA.CI_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INR_CINO;
        AICUUPIA.DATA.PROD_CODE = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INR_PROD;
        S_CALL_AIZUUPIA();
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO = AICUUPIA.DATA.RVS_NO;
        CEP.TRC(SCCGWA, WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO);
    }
    public void B662_ITM_AC_UPDATE_RPOC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPUITM);
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
            AICPUITM.DATA.EVENT_CODE = "DR";
        } else {
            AICPUITM.DATA.EVENT_CODE = "CR";
        }
        AICPUITM.DATA.NARR_CD = "" + CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG;
        JIBS_tmp_int = AICPUITM.DATA.NARR_CD.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) AICPUITM.DATA.NARR_CD = "0" + AICPUITM.DATA.NARR_CD;
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) AICPUITM.DATA.BR_OLD = 0;
        else AICPUITM.DATA.BR_OLD = Integer.parseInt(CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) AICPUITM.DATA.BR_NEW = 0;
        else AICPUITM.DATA.BR_NEW = Integer.parseInt(CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        AICPUITM.DATA.CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(7 - 1, 7 + 3 - 1);
        if (CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        AICPUITM.DATA.ITM_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(10 - 1, 10 + 10 - 1);
        AICPUITM.DATA.CI_NO = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INR_CINO;
        AICPUITM.DATA.PROD_CODE = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INR_PROD;
        AICPUITM.DATA.AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        AICPUITM.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
        AICPUITM.DATA.THEIR_AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICPUITM.DATA.ORI_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        AICPUITM.DATA.PAY_MAN = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM);
        AICPUITM.DATA.PAY_BKNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
        AICPUITM.DATA.DESC = CMCS1400.REMARK;
        S000_CALL_AIZPUITM();
    }
    public void B700_FEE_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD.trim().length() > 0 
                && CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD.charAt(0) != 0X00) {
                B730_FEE_REGS_PROC();
            }
        }
    }
    public void B730_FEE_REGS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFHIS);
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        WS_TEMP_VARIABLE.WS_JRN_SEQ += 1;
        BPRFEHIS.KEY.JRN_SEQ = (short) WS_TEMP_VARIABLE.WS_JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FE_CI_NO);
        if (CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FE_CI_NO.trim().length() > 0 
            && CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FE_CI_NO.charAt(0) != 0X00) {
            BPRFEHIS.TX_CI = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FE_CI_NO;
        } else {
            IBS.init(SCCGWA, CICACCU);
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_FR_AC;
            S000_CALL_CIZACCU();
            BPRFEHIS.TX_CI = CICACCU.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, BPRFEHIS.TX_CI);
        BPRFEHIS.TX_PROD = CICACCU.DATA.PROD_CD;
        BPRFEHIS.FEE_CODE = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD;
        BPRFEHIS.DRCRFLG = 'D';
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.CHG_BR = CICACCU.DATA.OPN_BR;
        CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_FR_AC);
        BPRFEHIS.TX_AC = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_FR_AC;
        BPRFEHIS.CHG_AC = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_FR_AC;
        BPRFEHIS.CHG_AC_TY = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_AC_TYP;
        BPRFEHIS.FEE_CCY = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY;
        BPRFEHIS.FEE_BAS = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPRFEHIS.FEE_AMT = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPRFEHIS.CHG_CCY = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY;
        BPRFEHIS.CHG_BAS = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPRFEHIS.CHG_AMT = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPRFEHIS.ADJ_AMT = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPRFEHIS.VAT_AMT = CMCS1400.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        CEP.TRC(SCCGWA, BPRFEHIS.VAT_AMT);
        BPRFEHIS.CHG_FLG = '0';
        BPRFEHIS.CCY_TYPE = '1';
        BPRFEHIS.REMARK = CMCS1400.REMARK;
        BPRFEHIS.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_STS = 'N';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRFEHIS.TX_STS = 'R';
        }
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        SCCGWA.COMM_AREA.FEE_IND = 'X';
        SCCGWA.COMM_AREA.FEE_DATA_IND = 'X';
        BPCSFHIS.INP_DATA.DATA_LEN = 780;
        CEP.TRC(SCCGWA, BPCSFHIS.INP_DATA.DATA_LEN);
        BPCSFHIS.INP_DATA.DATA_PTR = BPRFEHIS;
        S000_CALL_BPZSFHIS();
    }
    public void B900_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCO1400);
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 40; WS_TEMP_VARIABLE.WS_I += 1) {
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO;
            CMCO1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = CMCS1400.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCO1400;
        SCCFMT.DATA_LEN = 3240;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_CIZQACRI, CICQACRI, true);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_CIZQACRI_OPPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_CIZQACRI, CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
    }
    public void S_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_DDZUDRAC, DDCUDRAC, true);
    }
    public void S_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_DDZUCRAC, DDCUCRAC, true);
    }
    public void S_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_AIZUUPIA, AICUUPIA, true);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_DCZUCINF, DCCUCINF, true);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_BPZFFTXI, BPCFFTXI, true);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_BPZFFCON, BPCFFCON, true);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_IBZDRAC, IBCPOSTA, true);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_IBZCRAC, IBCPOSTA, true);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_TDZACDRU, TDCACDRU, true);
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR, true);
    }
    public void S000_CALL_GDZUDBDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-DBDR-DR", GDCUMPDR, true);
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_BPZQVATR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-VATR-TAX", BPCQVATR);
        if (BPCQVATR.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = "" + BPCQVATR.RC.RC_CODE;
            JIBS_tmp_int = WS_TEMP_VARIABLE.WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_ERR_MSG = "0" + WS_TEMP_VARIABLE.WS_ERR_MSG;
            S_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        CEP.TRC(SCCGWA, BPCPOEWA.RC.RC_CODE);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S000_CALL_BPZSFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-WRITE-FHIS", BPCSFHIS, true);
        CEP.TRC(SCCGWA, BPCSFHIS.RC.RC_CODE);
        if (BPCSFHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSFHIS.RC);
        }
    }
    public void S_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
