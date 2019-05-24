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

import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS1200 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String K_CNY = "156";
    String K_OUTPUT_FMT = "CM120";
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
    CMZS1200_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMZS1200_WS_TEMP_VARIABLE();
    CMZS1200_WS_CUS_INF[] WS_CUS_INF = new CMZS1200_WS_CUS_INF[5];
    CMZS1200_WS_OTH_INF[] WS_OTH_INF = new CMZS1200_WS_OTH_INF[5];
    CMZS1200_WS_INR_AC_INF[] WS_INR_AC_INF = new CMZS1200_WS_INR_AC_INF[5];
    CMZS1200_WS_TD_INF[] WS_TD_INF = new CMZS1200_WS_TD_INF[5];
    CMZS1200_WS_FEE_INF[] WS_FEE_INF = new CMZS1200_WS_FEE_INF[5];
    CMZS1200_WS_EVEN_DATA[] WS_EVEN_DATA = new CMZS1200_WS_EVEN_DATA[5];
    String WS_IN_AC = " ";
    String WS_IN_AC_APP = " ";
    char WS_IN_AC_CI_TYP = ' ';
    double WS_IN_AMT = 0;
    String WS_IN_RLT_AC = " ";
    String WS_IN_RLT_ACNM = " ";
    String WS_TS = " ";
    CMZS1200_WS_TAX_INF_REG WS_TAX_INF_REG = new CMZS1200_WS_TAX_INF_REG();
    CMZS1200_WS_TAX_INF[] WS_TAX_INF = new CMZS1200_WS_TAX_INF[5];
    char WS_ENTY_TYP1 = ' ';
    char WS_ENTY_TYP2 = ' ';
    short WS_ACNAME_CHKFLG = 0;
    CMZS1200_WS_MATCH_FLG[] WS_MATCH_FLG = new CMZS1200_WS_MATCH_FLG[5];
    char WS_DEAL_FLG = ' ';
    char WS_TAX_FLG = ' ';
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
    CMCO1200 CMCO1200 = new CMCO1200();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICPUITM AICPUITM = new AICPUITM();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCCURHLD DCCURHLD = new DCCURHLD();
    CICCUST CICCUST = new CICCUST();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CICQACAC CICQACAC = new CICQACAC();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    TDCACE TDCACE = new TDCACE();
    BPCSFHIS BPCSFHIS = new BPCSFHIS();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    CICACCU CICACCU = new CICACCU();
    BPCRZZS BPCRZZS = new BPCRZZS();
    BPCFX BPCFX = new BPCFX();
    DDCUTRVS DDCUTRVS = new DDCUTRVS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS1200 CMCS1200;
    public CMZS1200() {
        for (int i=0;i<5;i++) WS_CUS_INF[i] = new CMZS1200_WS_CUS_INF();
        for (int i=0;i<5;i++) WS_OTH_INF[i] = new CMZS1200_WS_OTH_INF();
        for (int i=0;i<5;i++) WS_INR_AC_INF[i] = new CMZS1200_WS_INR_AC_INF();
        for (int i=0;i<5;i++) WS_TD_INF[i] = new CMZS1200_WS_TD_INF();
        for (int i=0;i<5;i++) WS_FEE_INF[i] = new CMZS1200_WS_FEE_INF();
        for (int i=0;i<5;i++) WS_EVEN_DATA[i] = new CMZS1200_WS_EVEN_DATA();
        for (int i=0;i<5;i++) WS_TAX_INF[i] = new CMZS1200_WS_TAX_INF();
        for (int i=0;i<5;i++) WS_MATCH_FLG[i] = new CMZS1200_WS_MATCH_FLG();
    }
    public void MP(SCCGWA SCCGWA, CMCS1200 CMCS1200) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS1200 = CMCS1200;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZS1200 return!");
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
        CEP.TRC(SCCGWA, CMCS1200.RMK);
        B100_INPUT_CHECK_PROC();
        B200_CHARGR_AC_APP_PROC();
        B300_APP_CHECK_PROC();
        B400_INQ_OTH_IFO_PROC();
        B600_AC_UPDATE_PROC();
        B700_EVENT_PROC();
        B750_REGISTER_TAX_PROC();
        B800_FEE_PROC();
        B900_OUTPUT_DATA();
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                WS_DEAL_FLG = 'Y';
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 'D' 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP != 'X') {
                    WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP;
                    if ((WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP != 'X' 
                        && WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP != 'I' 
                        && WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP != 'N')) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_INP_ERR);
                    }
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC);
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC.equalsIgnoreCase(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC)) {
                    WS_MATCH_FLG[WS_TEMP_VARIABLE.WS_I-1].WS_PAL_INT_AC_MATCH_FLG = 'Y';
                } else {
                    WS_MATCH_FLG[WS_TEMP_VARIABLE.WS_I-1].WS_PAL_INT_AC_MATCH_FLG = 'N';
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("2")) {
                    if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                    JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).charAt(0);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_AMT);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_TAX_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, WS_TAX_FLG);
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(0, 1).equalsIgnoreCase("1") 
                    && (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PSW.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PSW.charAt(0) == 0X00)) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PSW_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    && (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM.charAt(0) == 0X00)) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
                }
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                    && ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_TYP.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_TYP.charAt(0) == 0X00) 
                    || (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_NO.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_NO.charAt(0) == 0X00))) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR);
                }
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    && ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK2_DAT.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK2_DAT.charAt(0) == 0X00) 
                    || (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK3_DAT.trim().length() == 0 
                    || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK3_DAT.charAt(0) == 0X00))) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRK_ERR);
                }
            }
        }
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 5; WS_TEMP_VARIABLE.WS_J += 1) {
            if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO.trim().length() > 0 
                && CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO.charAt(0) != 0X00) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].AMT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1]);
                WS_DEAL_FLG = 'Y';
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDGL);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CBR_OLD);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CBR_NEW);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CCY_EVE);
                if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD.trim().length() == 0 
                    && CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PRDMO_CD_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD);
                if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD.trim().length() == 0 
                    && CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_EVENT_CD_ERR);
                }
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CCY_EVE);
                if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CCY_EVE.trim().length() == 0 
                    && CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CCY_EVE.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
                }
                for (WS_TEMP_VARIABLE.WS_G = 1; WS_TEMP_VARIABLE.WS_G <= 20; WS_TEMP_VARIABLE.WS_G += 1) {
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_G);
                    CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO);
                    CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL);
                }
            }
        }
        if (WS_DEAL_FLG != 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NOT_ALLOW_EMPTY_TRAD);
        }
        for (WS_TEMP_VARIABLE.WS_K = 1; WS_TEMP_VARIABLE.WS_K <= 5; WS_TEMP_VARIABLE.WS_K += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_K);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACSQ);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACCY);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CTYP);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CCY);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_AMT);
            CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_BAMT);
            if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.trim().length() > 0 
                && CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.charAt(0) != 0X00) {
                CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO);
                CEP.TRC(SCCGWA, CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD);
                if (((CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO.trim().length() == 0 
                    || CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO.charAt(0) == 0X00) 
                    && (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.trim().length() == 0 
                    || CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.charAt(0) == 0X00))) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ACNO_PROD_M_INPUT);
                } else {
                    WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_PROD_CD = " ";
                    if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.trim().length() == 0 
                        || CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD.charAt(0) == 0X00) {
                        IBS.init(SCCGWA, CICQACRI);
                        CICQACRI.FUNC = 'A';
                        CICQACRI.DATA.AGR_NO = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_ACNO;
                        S_CALL_CIZQACRI();
                        WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_PROD_CD = CICQACRI.O_DATA.O_PROD_CD;
                    } else {
                        WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_PROD_CD = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_PROD;
                    }
                }
            }
            if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC.trim().length() > 0) {
                JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
                JIBS_date = new Date();
                WS_TS = JIBS_sdf.format(JIBS_date);
                CEP.TRC(SCCGWA, WS_TS);
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC;
                S_CALL_CIZQACRI();
                JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
                JIBS_date = new Date();
                WS_TS = JIBS_sdf.format(JIBS_date);
                CEP.TRC(SCCGWA, WS_TS);
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CI_NO = CICQACRI.O_DATA.O_CI_NO;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CI_TYP = CICQACRI.O_DATA.O_CI_TYP;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
                WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_OPEN_BR = CICQACRI.O_DATA.O_OPN_BR;
                CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_FRM_APP);
                CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_CI_TYP);
                CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_OPEN_BR);
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
                    if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC == null) CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC = "";
                    JIBS_tmp_int = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC += " ";
                    if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC.substring(0, 6).trim().length() == 0) WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_OPEN_BR = 0;
                    else WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_OPEN_BR = Integer.parseInt(CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CAC.substring(0, 6));
                } else {
                    WS_FEE_INF[WS_TEMP_VARIABLE.WS_K-1].WS_FEE_AC_TYP = '0';
                }
            }
            if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_AMT != 0 
                && (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.trim().length() == 0 
                || CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_K-1].FEE_CD.charAt(0) == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_INPUT_FEE_CODE);
            }
        }
    }
    public void B200_CHARGR_AC_APP_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP != 'I') {
                    B220_INQ_CHARGE_AC_APP();
                    B260_CHECK_CUS_AC_INF();
                } else {
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "IT";
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
            }
        }
    }
    public void B220_INQ_CHARGE_AC_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
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
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_NO;
            S_CALL_CIZCUST();
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_NO);
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_TYP);
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC);
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP);
            if ((!CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC.equalsIgnoreCase(K_CNY)) 
                && (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == ' ' 
                || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
            }
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD);
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0 
                || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.charAt(0) == 0X00) {
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
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD += " ";
            if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
            JIBS_tmp_int = BPCPARMC.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
            BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
            if (!CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC.trim().length() == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_OPP_AC_MUST_IN);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM.trim().length() == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_OPP_AC_NAME_MUST_IN);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNM);
            }
        } else {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            S000_CALL_AIZPQMIB();
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD") 
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP.equalsIgnoreCase("033")) {
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "DG";
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD") 
            && (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP.equalsIgnoreCase("033") 
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CNTRCT_TYP.equalsIgnoreCase("048"))) {
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "TG";
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        }
    }
    public void B260_CHECK_CUS_AC_INF() throws IOException,SQLException,Exception {
        WS_ACNAME_CHKFLG = 0;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ACNAME_CHKFLG = 1;
        } else {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM.trim().length() > 0) {
                if (!WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
                    R000_CHK_CHNL();
                }
            }
        }
        if ((WS_ACNAME_CHKFLG == 1) 
            && (!WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM.equalsIgnoreCase(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM))) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM);
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
        }
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
            && ((!WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_NO.equalsIgnoreCase(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_NO)) 
            || !WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_TYP.equalsIgnoreCase(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_TYP))) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_NO);
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_NO);
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_ID_TYP);
            CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ID_TYP);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR);
        }
    }
    public void B300_APP_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                if ((WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD") 
                    || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TG")) 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NO_TD_DEPOSIT);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL);
                if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD")) {
                    if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                    JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                    if (!CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("2")) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DEPOSIT_MUST_INPUT);
                    }
                    IBS.init(SCCGWA, TDCACE);
                    CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                    CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ);
                    CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_NO);
                    TDCACE.PAGE_INF.AC_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                    TDCACE.PAGE_INF.I_AC_SEQ = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
                    TDCACE.PAGE_INF.I_BV_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_NO;
                    R000_INQ_TD();
                    CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_STS);
                    CEP.TRC(SCCGWA, TDCACE.DATA[01-1].ACO_STSW);
                    WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TD_AVA_BAL = TDCACE.DATA[01-1].KY_BAL;
                    CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TD_AVA_BAL);
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI") 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO.trim().length() == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CREV_M_IMPUT);
                    }
                    IBS.init(SCCGWA, AIRGINF);
                    IBS.init(SCCGWA, AICRGINF);
                    AICRGINF.INFO.FUNC = 'Q';
                    AIRGINF.KEY.RVS_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO;
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    S000_CALL_AIZRGINF();
                    CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                    if (AICRGINF.RETURN_INFO == 'N') {
                        WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_CREV_NOT_EXIT;
                        S_ERR_MSG_PROC();
                    }
                    WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = AIRGINF.G_BAL;
                    CEP.TRC(SCCGWA, AIRGINF.G_BAL);
                    CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                    if (WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL < CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AVA_BAL_LT_REPAY_BAL);
                    }
                }
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ);
                CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO);
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.FUNC = 'A';
                    CICQACRI.DATA.AGR_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                    S_CALL_CIZQACRI();
                    WS_ENTY_TYP1 = CICQACRI.O_DATA.O_ENTY_TYP;
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.FUNC = 'A';
                    CICQACRI.DATA.AGR_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
                    S_CALL_CIZQACRI();
                    WS_ENTY_TYP2 = CICQACRI.O_DATA.O_ENTY_TYP;
                    if (WS_ENTY_TYP1 != '4' 
                        || WS_ENTY_TYP2 != '4') {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYPE_ERROR);
                    }
                }
            }
        }
    }
    public void B400_INQ_OTH_IFO_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNM;
                if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO.trim().length() == 0) WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO = 0;
                else WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO = Integer.parseInt(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO);
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC_TYP);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO);
            }
        }
    }
    public void B600_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
                if ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                    if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                        || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                        || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') {
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG == '2') {
                            B610_AC_UPDATE_PRE_PROC();
                        }
                        B620_CUS_AC_UPDATE_PROC();
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG == '1') {
                            B640_AC_UPDATE_AFT_PROC();
                        }
                    } else {
                        B660_INR_UPDATE_RPOC();
                    }
                }
            }
        } else {
            for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
                if ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                    if ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                        || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                        || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                        && (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D')) {
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG == '1') {
                            B640_AC_UPDATE_AFT_PROC();
                        }
                        B620_CUS_AC_UPDATE_PROC();
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG == '2') {
                            B610_AC_UPDATE_PRE_PROC();
                        }
                    }
                }
            }
            for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
                if ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                    && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                    if ((CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                        || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                        || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') 
                        && (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C')) {
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG == '1') {
                            B640_AC_UPDATE_AFT_PROC();
                        }
                        B620_CUS_AC_UPDATE_PROC();
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_HLD_FLG == '2') {
                            B610_AC_UPDATE_PRE_PROC();
                        }
                    }
                }
            }
        }
    }
    public void B610_AC_UPDATE_PRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_NO;
        DCCURHLD.DATA.RHLD_TYP = '2';
        DCCURHLD.DATA.RAMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DCCURHLD.DATA.TX_TYP_CD = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        S_CALL_DCZURHLD();
    }
    public void B640_AC_UPDATE_AFT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.HLD_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_NO;
        DCCUHLD.DATA.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        DCCUHLD.DATA.SEQ = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        DCCUHLD.DATA.CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DCCUHLD.DATA.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_AMT;
        DCCUHLD.DATA.TX_TYP_CD = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_AMT);
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S_CALL_DCZUHLD();
    }
    public void B620_CUS_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                B627_DR_DDZUTRVS();
            } else {
                B622_DR_DDAC_PROC();
            }
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")) {
            B623_DR_IBAC_PROC();
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD")) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("2")) {
                B624_DR_TDAC_PROC();
            }
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")
            || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TG")) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO.trim().length() > 0) {
                B625_DR_GDAC_PROC();
            } else {
                B626_DR_GDAC_PROC();
            }
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")
            || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                B634_CR_DDZUTRVS();
            } else {
                B632_CR_DDAC_PROC();
            }
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")) {
            B633_CR_IBAC_PROC();
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")
            || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            B661_INR_AC_UPDATE_RPOC();
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IT")
            || CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IT")) {
            B662_ITM_AC_UPDATE_RPOC();
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B622_DR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW = 'N';
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(0, 1).equalsIgnoreCase("1")) {
            DDCUDRAC.CHK_PSW_FLG = 'Y';
            DDCUDRAC.CHK_PSW = 'P';
            DDCUDRAC.PSWD = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PSW;
        } else {
            DDCUDRAC.CHK_PSW_FLG = 'N';
        }
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            DDCUDRAC.TRK_DATE2 = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK2_DAT;
            DDCUDRAC.TRK_DATE3 = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK3_DAT;
        }
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP);
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            DDCUDRAC.BV_TYP = '1';
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUDRAC.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '1') {
                DDCUDRAC.BV_TYP = '2';
            }
        }
        DDCUDRAC.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        DDCUDRAC.CCY_TYPE = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUDRAC.TX_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.TX_TYPE = 'T';
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "102";
        } else {
            DDCUDRAC.TX_MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        }
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHQ_TYP);
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_NO);
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].SIGN_DT);
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PSW);
        DDCUDRAC.CHQ_TYPE = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHQ_TYP.charAt(0);
        DDCUDRAC.CHQ_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_NO;
        DDCUDRAC.CHQ_ISS_DATE = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].SIGN_DT;
        DDCUDRAC.PAY_PSWD = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PSW;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_NO.trim().length() > 0) {
            DDCUDRAC.BV_TYP = '4';
        }
        CEP.TRC(SCCGWA, DDCUDRAC.BV_TYP);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            DDCUDRAC.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            DDCUDRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUDRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUDRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUDRAC.RLT_BANK = "" + WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
            JIBS_tmp_int = DDCUDRAC.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.RLT_BANK = "0" + DDCUDRAC.RLT_BANK;
        }
        DDCUDRAC.REMARKS = CMCS1200.RMK;
        S_CALL_DDZUDRAC();
    }
    public void B623_DR_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        IBCPOSTA.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        IBCPOSTA.CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.TX_MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        IBCPOSTA.NARR = CMCS1200.RMK;
        IBCPOSTA.OTH_AC_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
        IBCPOSTA.OTH_CNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
        IBCPOSTA.OTH_BR_CNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNM;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO.trim().length() == 0) IBCPOSTA.OTH_BR = 0;
        else IBCPOSTA.OTH_BR = Integer.parseInt(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO);
        S_CALL_IBZDRAC();
    }
    public void B624_DR_TDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.OPT = '0';
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 3 - 1) + "3" + TDCACDRU.BUSI_CTLW.substring(3 + 1 - 1);
        TDCACDRU.MAC_CNO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        TDCACDRU.AC_SEQ = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        TDCACDRU.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        TDCACDRU.CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        TDCACDRU.TXN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TD_AVA_BAL;
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
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(0, 1).equalsIgnoreCase("1")) {
            TDCACDRU.DRAW_INF = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PSW;
        } else {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 7 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(7 + 1 - 1);
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        CEP.TRC(SCCGWA, TDCACDRU.BUSI_CTLW.substring(7 - 1, 7 + 1 - 1));
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_INF);
        TDCACDRU.AC_TRK2 = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK2_DAT;
        TDCACDRU.AC_TRK3 = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRK3_DAT;
        TDCACDRU.TXN_MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        TDCACDRU.REMARK = CMCS1200.RMK;
        S_CALL_TDZACDRU();
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
        if (TDCACDRU.DRAW_TOT_AMT < CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AVA_BAL_LT_REPAY_BAL);
        }
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT;
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT = TDCACDRU.PAYING_INT;
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT - CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT - WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT;
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_PAL_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT);
        CEP.TRC(SCCGWA, WS_MATCH_FLG[WS_TEMP_VARIABLE.WS_I-1].WS_PAL_INT_AC_MATCH_FLG);
        if (WS_MATCH_FLG[WS_TEMP_VARIABLE.WS_I-1].WS_PAL_INT_AC_MATCH_FLG == 'Y' 
            && WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT > CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PAL_AC_NOT_INPUT);
            }
            WS_IN_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC;
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT - CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
            WS_IN_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
        }
        if (WS_MATCH_FLG[WS_TEMP_VARIABLE.WS_I-1].WS_PAL_INT_AC_MATCH_FLG == 'N' 
            && WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT != 0) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PAL_AC_NOT_INPUT);
            }
            WS_IN_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC;
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT;
            WS_IN_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
        }
        if (WS_MATCH_FLG[WS_TEMP_VARIABLE.WS_I-1].WS_PAL_INT_AC_MATCH_FLG == 'N' 
            && WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT != 0) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INT_AC_NOT_INPUT);
            }
            WS_IN_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC;
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT;
            WS_IN_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
        }
    }
    public void B625_DR_GDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR);
        GDCUMPDR.INPUT.FUNC = 'D';
        GDCUMPDR.INPUT.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        GDCUMPDR.INPUT.CTA_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO;
        GDCUMPDR.INPUT.REF_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO;
        GDCUMPDR.INPUT.SEQ = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        GDCUMPDR.INPUT.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        GDCUMPDR.OUTPUT.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        GDCUMPDR.INPUT.MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        GDCUMPDR.INPUT.RMK = CMCS1200.RMK;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            GDCUMPDR.INPUT.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            GDCUMPDR.INPUT.RLT_BANK = "" + WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
            JIBS_tmp_int = GDCUMPDR.INPUT.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) GDCUMPDR.INPUT.RLT_BANK = "0" + GDCUMPDR.INPUT.RLT_BANK;
        }
        S000_CALL_GDZUMPDR();
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC);
        if (GDCUMPDR.OUTPUT.INT != 0) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC.trim().length() > 0) {
                WS_IN_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC;
            } else {
                WS_IN_AC = GDCUMPDR.OUTPUT.STAC;
            }
            CEP.TRC(SCCGWA, WS_IN_AC);
            WS_IN_AMT = GDCUMPDR.OUTPUT.INT;
            WS_IN_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            if (WS_IN_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INT_AC_EMPTY);
            }
            B640_CR_DDAC_PROC();
        }
    }
    public void B626_DR_GDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR);
        GDCUMPDR.INPUT.FUNC = 'D';
        GDCUMPDR.INPUT.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        GDCUMPDR.INPUT.SEQ = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        GDCUMPDR.INPUT.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        GDCUMPDR.OUTPUT.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        GDCUMPDR.INPUT.MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        GDCUMPDR.INPUT.RMK = CMCS1200.RMK;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            GDCUMPDR.INPUT.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            GDCUMPDR.INPUT.RLT_BANK = "" + WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
            JIBS_tmp_int = GDCUMPDR.INPUT.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) GDCUMPDR.INPUT.RLT_BANK = "0" + GDCUMPDR.INPUT.RLT_BANK;
        }
        S000_CALL_GDZUDBDR();
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
        CEP.TRC(SCCGWA, CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC);
        if (GDCUMPDR.OUTPUT.INT != 0) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC.trim().length() > 0) {
                WS_IN_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC;
            } else {
                WS_IN_AC = GDCUMPDR.OUTPUT.STAC;
            }
            CEP.TRC(SCCGWA, WS_IN_AC);
            WS_IN_AMT = GDCUMPDR.OUTPUT.INT;
            WS_IN_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            if (WS_IN_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INT_AC_EMPTY);
            }
            B640_CR_DDAC_PROC();
        }
    }
    public void B627_DR_DDZUTRVS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUTRVS);
        DDCUTRVS.VS_DRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        DDCUTRVS.VS_DRNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM;
        DDCUTRVS.TXCCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        DDCUTRVS.CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUTRVS.VS_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUTRVS.VS_SPAMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUTRVS.VS_OPT = '1';
        DDCUTRVS.VS_STLT = '3';
        DDCUTRVS.VS_CRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
        DDCUTRVS.VS_CRNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
        DDCUTRVS.MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        DDCUTRVS.REMARK = CMCS1200.RMK;
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("3")) {
            DDCUTRVS.VS_SPFLG = '1';
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("4")) {
            DDCUTRVS.VS_SPFLG = '2';
            DDCUTRVS.OLD_CRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        }
        S_CALL_DDZUTRVS();
    }
    public void B632_CR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP);
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCUCRAC.BV_TYP = '1';
            DDCUCRAC.CARD_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD") 
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")) {
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '1') {
                DDCUCRAC.BV_TYP = '2';
            }
            DDCUCRAC.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        }
        DDCUCRAC.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        DDCUCRAC.CCY_TYPE = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUCRAC.TX_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_CODE.trim().length() == 0) DDCUCRAC.BV_VTYP = 0;
        else DDCUCRAC.BV_VTYP = Short.parseShort(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_CODE);
        DDCUCRAC.BV_VNO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].BV_NO;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (CICQACRI.RC.RC_CODE == 0) {
            DDCUCRAC.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            DDCUCRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUCRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUCRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUCRAC.RLT_BANK = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO;
        }
        DDCUCRAC.REMARKS = CMCS1200.RMK;
        S_CALL_DDZUCRAC();
    }
    public void B633_CR_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        IBCPOSTA.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        IBCPOSTA.CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.TX_MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        IBCPOSTA.NARR = CMCS1200.RMK;
        IBCPOSTA.OTH_AC_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
        IBCPOSTA.OTH_CNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
        IBCPOSTA.OTH_BR_CNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNM;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO.trim().length() == 0) IBCPOSTA.OTH_BR = 0;
        else IBCPOSTA.OTH_BR = Integer.parseInt(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNO);
        S_CALL_IBZCRAC();
    }
    public void B634_CR_DDZUTRVS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUTRVS);
        DDCUTRVS.VS_CRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        DDCUTRVS.VS_CRNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM;
        DDCUTRVS.TXCCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        DDCUTRVS.CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUTRVS.VS_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUTRVS.VS_SPAMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUTRVS.VS_OPT = '1';
        DDCUTRVS.VS_STLT = '3';
        DDCUTRVS.VS_DRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
        DDCUTRVS.VS_DRNM = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
        DDCUTRVS.MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        DDCUTRVS.REMARK = CMCS1200.RMK;
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("3")) {
            DDCUTRVS.VS_SPFLG = '1';
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
            JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("4")) {
            DDCUTRVS.VS_SPFLG = '2';
            DDCUTRVS.OLD_CRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        }
        S_CALL_DDZUTRVS();
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
        DDCUCRAC.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        DDCUCRAC.CCY_TYPE = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUCRAC.TX_AMT = WS_IN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        }
        DDCUCRAC.OTHER_AC = WS_IN_RLT_AC;
        DDCUCRAC.OTHER_AC_NM = WS_IN_RLT_ACNM;
        DDCUCRAC.RLT_BANK = "" + WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.RLT_BANK = "0" + DDCUCRAC.RLT_BANK;
        DDCUCRAC.REMARKS = CMCS1200.RMK;
        S_CALL_DDZUCRAC();
    }
    public void B660_INR_UPDATE_RPOC() throws IOException,SQLException,Exception {
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '1') {
            B661_INR_AC_UPDATE_RPOC();
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '2') {
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '3') {
            B661_INR_AC_UPDATE_RPOC();
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '4') {
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '5') {
        } else if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '6') {
        } else {
        }
    }
    public void B661_INR_AC_UPDATE_RPOC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
            AICUUPIA.DATA.EVENT_CODE = "DR";
        } else {
            AICUUPIA.DATA.EVENT_CODE = "CR";
        }
        AICUUPIA.DATA.AC_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        AICUUPIA.DATA.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
            && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            AICUUPIA.DATA.AMT = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
        } else {
            AICUUPIA.DATA.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO;
        AICUUPIA.DATA.THEIR_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        AICUUPIA.DATA.ORI_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        AICUUPIA.DATA.PAY_MAN = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        AICUUPIA.DATA.PAY_BKNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        AICUUPIA.DATA.PAY_BR = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        AICUUPIA.DATA.THEIR_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICUUPIA.DATA.NARR_CD = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        AICUUPIA.DATA.DESC = CMCS1200.RMK;
        S_CALL_AIZUUPIA();
        WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO = AICUUPIA.DATA.RVS_NO;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
            && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL - CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        }
        CEP.TRC(SCCGWA, WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT);
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL += " ";
        if (WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT > 0 
            && CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PAL_AC_NOT_INPUT);
            }
            WS_IN_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC;
            WS_IN_AMT = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT;
            WS_IN_RLT_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
        }
    }
    public void B662_ITM_AC_UPDATE_RPOC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPUITM);
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
            AICPUITM.DATA.EVENT_CODE = "DR";
        } else {
            AICPUITM.DATA.EVENT_CODE = "CR";
        }
        AICPUITM.DATA.NARR_CD = "" + CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG;
        JIBS_tmp_int = AICPUITM.DATA.NARR_CD.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) AICPUITM.DATA.NARR_CD = "0" + AICPUITM.DATA.NARR_CD;
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) AICPUITM.DATA.BR_OLD = 0;
        else AICPUITM.DATA.BR_OLD = Integer.parseInt(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) AICPUITM.DATA.BR_NEW = 0;
        else AICPUITM.DATA.BR_NEW = Integer.parseInt(CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        AICPUITM.DATA.CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(7 - 1, 7 + 3 - 1);
        if (CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        AICPUITM.DATA.ITM_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(10 - 1, 10 + 10 - 1);
        AICPUITM.DATA.AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        AICPUITM.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_CCY = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
        AICPUITM.DATA.THEIR_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICPUITM.DATA.CI_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INR_CINO;
        AICPUITM.DATA.PROD_CODE = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INR_PROD;
        AICPUITM.DATA.ORI_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        AICPUITM.DATA.PAY_MAN = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM);
        AICPUITM.DATA.PAY_BKNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        AICPUITM.DATA.PAY_BR = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNO;
        CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
        AICPUITM.DATA.DESC = CMCS1200.RMK;
        S000_CALL_AIZPUITM();
    }
    public void B700_EVENT_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO.trim().length() > 0 
                && CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO.charAt(0) != 0X00) {
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVEN_CD);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_OLD);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW);
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].AMT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1]);
                IBS.init(SCCGWA, BPCPOEWA);
                BPCPOEWA.DATA.CNTR_TYPE = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
                BPCPOEWA.DATA.PROD_CODE = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO;
                BPCPOEWA.DATA.EVENT_CODE = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVEN_CD;
                BPCPOEWA.DATA.BR_OLD = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_OLD;
                BPCPOEWA.DATA.BR_NEW = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW;
                BPCPOEWA.DATA.BR_3 = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW;
                BPCPOEWA.DATA.BR_4 = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW;
                BPCPOEWA.DATA.BR_5 = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW;
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_EVE;
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCPOEWA.DATA.DESC = CMCS1200.RMK;
                for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 20; WS_TEMP_VARIABLE.WS_J += 1) {
                    if ((!IBS.isNumeric(WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO+"")) 
                        || (!IBS.isNumeric(WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL+""))) {
                        WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_MUST_INP_NUM;
                        S_ERR_MSG_PROC();
                    }
                    if (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL != 0 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO != 0) {
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                        WS_TEMP_VARIABLE.WS_N = WS_TEMP_VARIABLE.WS_J - 1;
                        for (WS_TEMP_VARIABLE.WS_M = 1; WS_TEMP_VARIABLE.WS_M <= WS_TEMP_VARIABLE.WS_N; WS_TEMP_VARIABLE.WS_M += 1) {
                            if (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_NO) {
                                CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                                CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_NO);
                                WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL += WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_VAL;
                                WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_NO = 0;
                                WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_VAL = 0;
                            }
                        }
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                    }
                }
                for (WS_TEMP_VARIABLE.WS_G = 1; WS_TEMP_VARIABLE.WS_G <= 20; WS_TEMP_VARIABLE.WS_G += 1) {
                    if (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL != 0 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO != 0) {
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_G);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL);
                        WS_TEMP_VARIABLE.WS_K = WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO;
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_K);
                        BPCPOEWA.DATA.AMT_INFO[WS_TEMP_VARIABLE.WS_K-1].AMT = WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL;
                    }
                }
                CEP.TRC(SCCGWA, CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDGL);
                BPCPOEWA.DATA.OTH = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDGL;
                BPCPOEWA.DATA.AC_NO = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                BPCPOEWA.DATA.AC_NO_REL = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO_REL);
                S000_CALL_BPZPOEWA();
            }
        }
    }
    public void B750_REGISTER_TAX_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES") 
                || CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDD") 
                || CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDP")) {
                for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 20; WS_TEMP_VARIABLE.WS_J += 1) {
                    if ((CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES") 
                        && (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 7 
                        || WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 8) 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL != 0) 
                        || ((CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDD") 
                        || CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDP")) 
                        && (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 9 
                        || WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 10 
                        || WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 34) 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL != 0)) {
                        WS_TAX_INF_REG.WS_TAX_PROD_CD_REG = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO;
                        WS_TAX_INF_REG.WS_TAX_CCY_REG = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_EVE;
                        WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT += WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL;
                    }
                }
                if ((!WS_TAX_INF_REG.WS_TAX_CCY_REG.equalsIgnoreCase("156") 
                    && WS_TAX_INF_REG.WS_TAX_CCY_REG.trim().length() > 0) 
                    && WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT != 0) {
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_PROD_CD = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_EVE;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_OLD;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW;
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                    BPCFX.EXR_TYPE = "MDR";
                    BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPCFX.BUY_CCY = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY;
                    BPCFX.BUY_AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    BPCFX.SELL_CCY = "156";
                    S000_LINK_BPZSFX();
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156 = BPCFX.SELL_AMT;
                    CEP.TRC(SCCGWA, WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156);
                    IBS.init(SCCGWA, BPCPOEWA);
                    BPCPOEWA.DATA.CNTR_TYPE = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
                    BPCPOEWA.DATA.PROD_CODE = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_PROD_CD;
                    BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPCPOEWA.DATA.EVENT_CODE = "WBJZ";
                    BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY;
                    BPCPOEWA.DATA.BR_OLD = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD;
                    BPCPOEWA.DATA.BR_NEW = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW;
                    if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES")) {
                        BPCPOEWA.DATA.AMT_INFO[7-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    }
                    BPCPOEWA.DATA.AC_NO = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    BPCPOEWA.DATA.AC_NO_REL = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    S000_CALL_BPZPOEWA();
                    IBS.init(SCCGWA, AICPUITM);
                    AICPUITM.DATA.EVENT_CODE = "CR";
                    AICPUITM.DATA.NARR_CD = "C";
                    AICPUITM.DATA.BR_OLD = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD;
                    AICPUITM.DATA.BR_NEW = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW;
                    AICPUITM.DATA.CCY = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY;
                    AICPUITM.DATA.ITM_NO = "30020801";
                    AICPUITM.DATA.AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_AIZPUITM();
                    IBS.init(SCCGWA, AICPUITM);
                    AICPUITM.DATA.EVENT_CODE = "DR";
                    AICPUITM.DATA.NARR_CD = "D";
                    AICPUITM.DATA.BR_OLD = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD;
                    AICPUITM.DATA.BR_NEW = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW;
                    AICPUITM.DATA.CCY = "156";
                    AICPUITM.DATA.ITM_NO = "30020801";
                    AICPUITM.DATA.AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156;
                    AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    S000_CALL_AIZPUITM();
                    IBS.init(SCCGWA, BPCPOEWA);
                    BPCPOEWA.DATA.CNTR_TYPE = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
                    BPCPOEWA.DATA.PROD_CODE = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_PROD_CD;
                    BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPCPOEWA.DATA.EVENT_CODE = "WBJZ";
                    BPCPOEWA.DATA.CCY_INFO[1-1].CCY = "156";
                    BPCPOEWA.DATA.BR_OLD = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD;
                    BPCPOEWA.DATA.BR_NEW = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW;
                    if (CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES")) {
                        BPCPOEWA.DATA.AMT_INFO[13-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[43-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156;
                    }
                    BPCPOEWA.DATA.AC_NO = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    BPCPOEWA.DATA.AC_NO_REL = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    S000_CALL_BPZPOEWA();
                }
            }
        }
        if (WS_TAX_FLG == 'Y') {
            IBS.init(SCCGWA, BPCRZZS);
            BPCRZZS.FUNC = 'A';
            BPCRZZS.DATA.PRDT_CODE = WS_TAX_INF_REG.WS_TAX_PROD_CD_REG;
            BPCRZZS.DATA.CCY = WS_TAX_INF_REG.WS_TAX_CCY_REG;
        }
    }
    public void B800_FEE_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD.trim().length() > 0 
                && CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD.charAt(0) != 0X00) {
                if (CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT == 0) {
                    B810_FEE_CACULATE_PROC();
                }
                B820_FEE_DRAW_PROC();
            }
        }
    }
    public void B810_FEE_CACULATE_PROC() throws IOException,SQLException,Exception {
    }
    public void B820_FEE_DRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_AC_TYP);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_AC_TYP;
        if (BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '0' 
            || BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '3') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CAC;
        }
        if (BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '4' 
            || BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '5') {
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CAC;
        }
        BPCFFTXI.TX_DATA.SVR_CD = "0351200";
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY;
        S_CALL_BPZFFTXI();
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].TO_ACCT_CEN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_AC_TYP;
        BPCFFCON.FEE_INFO.PROD_CODE1 = WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_PROD_CD;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CAC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT - CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_BAMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT;
        S_CALL_BPZFFCON();
        WS_FEE_INF[WS_TEMP_VARIABLE.WS_I-1].WS_FEE_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT;
    }
    public void B900_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCO1200);
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_AC;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CREV_NO = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_NO = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].HLD_NO;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RES_PAL = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].PAL_INAC;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].INT_INAC;
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ERR_PRFL = ' ';
            CMCO1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ERR_PRAC = CMCS1200.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].ERR_PRAC;
        }
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
            CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO;
            CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVEN_CD = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVEN_CD;
            CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDGL = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDGL;
            CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CALC_BR = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CBR_NEW;
            CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_EVE = CMCS1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_EVE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCO1200.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].AMT);
        }
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 5; WS_TEMP_VARIABLE.WS_I += 1) {
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CAC = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CAC;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_ACSQ = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_ACSQ;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_ACCY = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_ACCY;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CTYP = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CTYP;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CD;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_CCY;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_AMT;
            CMCO1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_BAMT = CMCS1200.FEE_DATA[WS_TEMP_VARIABLE.WS_I-1].FEE_BAMT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCO1200;
        SCCFMT.DATA_LEN = 3923;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHK_CHNL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "CKCHL";
        BPCPARMC.KEY.CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPCPRMR.DAT_PTR = BPCPARMC;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                WS_ACNAME_CHKFLG = 1;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
        }
    }
    public void S_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_CIZQACRI, CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
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
    public void S_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_IBZDRAC, IBCPOSTA, true);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S_CALL_DDZUTRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-VSTRN-PROC", DDCUTRVS, true);
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
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        CEP.TRC(SCCGWA, BPCPOEWA.RC.RC_CODE);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR, true);
    }
    public void S000_CALL_GDZUDBDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-DBDR-DR", GDCUMPDR, true);
    }
    public void S_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD, true);
    }
    public void S_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD, true);
    }
    public void S_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
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
    public void S000_CALL_BPZSFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-WRITE-FHIS", BPCSFHIS, true);
        CEP.TRC(SCCGWA, BPCSFHIS.RC.RC_CODE);
        if (BPCSFHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSFHIS.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_BPZFFCON, BPCFFCON, true);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S_ERR_MSG_PROC();
        }
    }
    public void R000_INQ_TD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_BPZRZZS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-CREAT-AIZZS", BPCRZZS);
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S_ERR_MSG_PROC();
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
