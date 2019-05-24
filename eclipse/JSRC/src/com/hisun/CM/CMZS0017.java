package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.IB.*;
import com.hisun.TD.*;
import com.hisun.BP.*;
import com.hisun.GD.*;
import com.hisun.DC.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS0017 {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BigDecimal bigD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM018";
    String K_CNY = "156";
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
    CMZS0017_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMZS0017_WS_TEMP_VARIABLE();
    CMZS0017_WS_CUS_INF[] WS_CUS_INF = new CMZS0017_WS_CUS_INF[9];
    CMZS0017_WS_OTH_INF[] WS_OTH_INF = new CMZS0017_WS_OTH_INF[9];
    CMZS0017_WS_INR_AC_INF[] WS_INR_AC_INF = new CMZS0017_WS_INR_AC_INF[9];
    CMZS0017_WS_TD_INF[] WS_TD_INF = new CMZS0017_WS_TD_INF[9];
    CMZS0017_WS_EVEN_DATA[] WS_EVEN_DATA = new CMZS0017_WS_EVEN_DATA[4];
    CMZS0017_WS_RATE_DATA WS_RATE_DATA = new CMZS0017_WS_RATE_DATA();
    String WS_IN_AC = " ";
    String WS_IN_AC_APP = " ";
    char WS_IN_AC_CI_TYP = ' ';
    double WS_IN_AMT = 0;
    String WS_IN_RLT_AC = " ";
    String WS_IN_RLT_ACNM = " ";
    double WS_REPAY_AMT_TOT = 0;
    double WS_REPAY_AMT_Y = 0;
    double WS_REPAY_AMT_N = 0;
    double WS_POINT_AMT = 0;
    double WS_SURPLUS_PAL_AMT_TOT = 0;
    double WS_SURPLUS_INT_AMT_TOT = 0;
    String WS_TS = " ";
    char WS_AMT_SOURCE_FLG = ' ';
    CMZS0017_WS_TAX_INF[] WS_TAX_INF = new CMZS0017_WS_TAX_INF[4];
    CMZS0017_WS_TAX_INF_REG WS_TAX_INF_REG = new CMZS0017_WS_TAX_INF_REG();
    char WS_PAL_INT_AC_MATCH_FLG = ' ';
    char WS_PAY_OFF_FLG = ' ';
    char WS_AMT_NO_SAME_FLG = ' ';
    char WS_DEAL_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CMRBSP17 CMRBSP17 = new CMRBSP17();
    AIRGINF AIRGINF = new AIRGINF();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CMCO0018 CMCO0018 = new CMCO0018();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICQACRI CICQACRI = new CICQACRI();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICRGINF AICRGINF = new AICRGINF();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    AICPUITM AICPUITM = new AICPUITM();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUTCGQ DCCUTCGQ = new DCCUTCGQ();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBATH SCCBATH;
    CMCS0018 CMCS0018;
    public CMZS0017() {
        for (int i=0;i<9;i++) WS_CUS_INF[i] = new CMZS0017_WS_CUS_INF();
        for (int i=0;i<9;i++) WS_OTH_INF[i] = new CMZS0017_WS_OTH_INF();
        for (int i=0;i<9;i++) WS_INR_AC_INF[i] = new CMZS0017_WS_INR_AC_INF();
        for (int i=0;i<9;i++) WS_TD_INF[i] = new CMZS0017_WS_TD_INF();
        for (int i=0;i<4;i++) WS_EVEN_DATA[i] = new CMZS0017_WS_EVEN_DATA();
        for (int i=0;i<4;i++) WS_TAX_INF[i] = new CMZS0017_WS_TAX_INF();
    }
    public void MP(SCCGWA SCCGWA, CMCS0018 CMCS0018) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS0018 = CMCS0018;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BSP0350017START");
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, "START-TIME=");
        CEP.TRC(SCCGWA, WS_TS);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BSP0350017END");
        CEP.TRC(SCCGWA, "CMZS0017 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, "222");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "333");
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        CEP.TRC(SCCGWA, "444");
        CEP.TRC(SCCGWA, "666");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, CMCS0018.RMK);
        B100_INPUT_CHECK_PROC();
        if (pgmRtn) return;
        B200_CHARGR_AC_APP_PROC();
        if (pgmRtn) return;
        B300_APP_CHECK_PROC();
        if (pgmRtn) return;
        B400_INQ_OTH_IFO_PROC();
        if (pgmRtn) return;
        B500_AC_REPAYMENT_PROC();
        if (pgmRtn) return;
        B600_CALCULATE_TAX_PROC();
        if (pgmRtn) return;
        B700_AC_UPDATE_PROC();
        if (pgmRtn) return;
        B800_EVENT_PROC();
        if (pgmRtn) return;
        B850_REGISTER_TAX_PROC();
        if (pgmRtn) return;
        B900_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                WS_DEAL_FLG = 'Y';
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                CEP.TRC(SCCGWA, "WS-I");
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, "S0018-DC-FLG(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 'D' 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_ERR);
                }
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
                CEP.TRC(SCCGWA, "S0018-AC-TYP(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP != 'X') {
                    WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP;
                    if ((WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP != 'X' 
                        && WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP != 'I' 
                        && WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_ACTP != 'N')) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_INP_ERR);
                    }
                }
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                CEP.TRC(SCCGWA, "S0018-CUS-AC(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                CEP.TRC(SCCGWA, "S0018-AC-SEQ(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.trim().length() == 0 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_ERR);
                }
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY);
                CEP.TRC(SCCGWA, "S0018-AC-CCY(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY.trim().length() == 0 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
                }
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                CEP.TRC(SCCGWA, "S0018-TR-STSW");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW);
                CEP.TRC(SCCGWA, "S0018-TXN-AMT(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if ((!CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("3")) 
                    && (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).trim().length() == 0) 
                    && (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT == 0)) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
                }
                CEP.TRC(SCCGWA, "S0018-AC-CREV(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV);
                CEP.TRC(SCCGWA, "S0018-CTA-NO(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO);
                CEP.TRC(SCCGWA, "S0018-REF-NO(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO);
                CEP.TRC(SCCGWA, "S0018-RMK-CD(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD);
                CEP.TRC(SCCGWA, "S0018-OPP-AC(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC);
                CEP.TRC(SCCGWA, "S0018-OPP-ACNM(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM);
                CEP.TRC(SCCGWA, "S0018-OPP-BKNM(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNM);
                CEP.TRC(SCCGWA, CMCS0018.P_IN_AC);
                CEP.TRC(SCCGWA, CMCS0018.INT_AC);
                CEP.TRC(SCCGWA, "S0018-P-IN-AC");
                CEP.TRC(SCCGWA, CMCS0018.P_IN_AC);
                CEP.TRC(SCCGWA, "S0018-INT-AC");
                CEP.TRC(SCCGWA, CMCS0018.INT_AC);
                if (CMCS0018.P_IN_AC.equalsIgnoreCase(CMCS0018.INT_AC)) {
                    WS_PAL_INT_AC_MATCH_FLG = 'Y';
                } else {
                    WS_PAL_INT_AC_MATCH_FLG = 'N';
                }
            }
        }
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 4; WS_TEMP_VARIABLE.WS_J += 1) {
            if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO.trim().length() > 0 
                && CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO.charAt(0) != 0X00) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].AMT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1]);
                WS_DEAL_FLG = 'Y';
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDGL_CD);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CA_ABBR);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVE_CCY);
                CEP.TRC(SCCGWA, "WS-J");
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                CEP.TRC(SCCGWA, "S0018-PRDMO-CD(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD);
                CEP.TRC(SCCGWA, "S0018-PRDMO-NO(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO);
                CEP.TRC(SCCGWA, "S0018-EVEN-CD(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD);
                CEP.TRC(SCCGWA, "S0018-PRDGL-CD(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDGL_CD);
                CEP.TRC(SCCGWA, "S0018-CA-ABBR(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].CA_ABBR);
                CEP.TRC(SCCGWA, "S0018-EVE-CCY(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVE_CCY);
                CEP.TRC(SCCGWA, "S0018-LOAN-AC(WS-J)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].LOAN_AC);
                if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD.trim().length() == 0 
                    && CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PRDMO_CD_ERR);
                }
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD);
                if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD.trim().length() == 0 
                    && CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVEN_CD.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_EVENT_CD_ERR);
                }
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVE_CCY);
                if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVE_CCY.trim().length() == 0 
                    && CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].EVE_CCY.charAt(0) == 0X00) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
                }
                for (WS_TEMP_VARIABLE.WS_G = 1; WS_TEMP_VARIABLE.WS_G <= 30; WS_TEMP_VARIABLE.WS_G += 1) {
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_G);
                    CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO);
                    CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL);
                    CEP.TRC(SCCGWA, "WS-G");
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_G);
                    CEP.TRC(SCCGWA, "WS-AMT-NO(WS-J,WS-G)");
                    CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO);
                    CEP.TRC(SCCGWA, "WS-AMT-VAL(WS-J,WS-G)");
                    CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL);
                }
            }
        }
        if (WS_DEAL_FLG != 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NOT_ALLOW_EMPTY_TRAD);
        }
    }
    public void B200_CHARGR_AC_APP_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP != 'I') {
                    B220_INQ_CHARGE_AC_APP();
                    if (pgmRtn) return;
                } else {
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "IT";
                }
                CEP.TRC(SCCGWA, "WS-I");
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, "WS-CUS-AGR-NO(WS-I)");
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO);
                CEP.TRC(SCCGWA, "WS-CUS-FRM-APP(WS-I)");
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
            }
        }
    }
    public void B220_INQ_CHARGE_AC_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        S_CALL_CIZQACRI();
        if (pgmRtn) return;
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
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR = "0" + WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR;
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC") 
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '2') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO;
            CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if (CICQACRL.RC.RC_CODE == 8054) {
            } else if (CICQACRL.RC.RC_CODE == 0 
                    && (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04"))) {
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
                CEP.ERR(SCCGWA, CICQACRL.RC);
            }
        }
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
        if (!WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY);
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP);
            if ((!CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY.equalsIgnoreCase(K_CNY)) 
                && (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == ' ' 
                || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
            }
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD);
            CEP.TRC(SCCGWA, "S0018-RMK-CD(WS-I)");
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD);
            if ((CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0 
                || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.charAt(0) == 0X00)) {
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
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD = "";
            JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD += " ";
            if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
            JIBS_tmp_int = BPCPARMC.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
            BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
        }
    }
    public void B300_APP_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
                if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD") 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NO_TD_DEPOSIT);
                }
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ);
            }
        }
    }
    public void B400_INQ_OTH_IFO_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC_TYP = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ACNM;
                WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_BKNM;
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC_TYP);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM);
                CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
            }
        }
    }
    public void B500_AC_REPAYMENT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].PRDMO_CD);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].PRDMO_NO);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].EVEN_CD);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].PRDGL_CD);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].CA_ABBR);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].EVE_CCY);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].LOAN_AC);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].PRDMO_CD);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].PRDMO_NO);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].EVEN_CD);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].PRDGL_CD);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].CA_ABBR);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].EVE_CCY);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[2-1].LOAN_AC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EVEN_DATA[1-1]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS0018.TAX_RATE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RATE_DATA);
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 10; WS_TEMP_VARIABLE.WS_J += 1) {
            WS_REPAY_AMT_TOT = WS_REPAY_AMT_TOT + WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL;
            WS_REPAY_AMT_N = WS_REPAY_AMT_TOT;
            CEP.TRC(SCCGWA, WS_REPAY_AMT_TOT);
            CEP.TRC(SCCGWA, "WS-REPAY-AMT-TOT");
            CEP.TRC(SCCGWA, WS_REPAY_AMT_TOT);
            if (WS_REPAY_AMT_TOT > 0) {
                WS_PAY_OFF_FLG = 'N';
            }
        }
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW);
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0") 
                || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).trim().length() == 0) {
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("3")) {
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                    CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AC);
                    B510_INQ_AC_BAL_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL);
                    if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL <= 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.AC_BAL_ZERO);
                    }
                    CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                    if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL < WS_REPAY_AMT_N) {
                        WS_PAY_OFF_FLG = 'N';
                        CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
                        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
                        B620_CUS_AC_UPDATE_PROC();
                        if (pgmRtn) return;
                        WS_REPAY_AMT_N = WS_REPAY_AMT_N - WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
                        WS_REPAY_AMT_Y = WS_REPAY_AMT_Y + WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
                        CEP.TRC(SCCGWA, WS_REPAY_AMT_TOT);
                        CEP.TRC(SCCGWA, WS_REPAY_AMT_Y);
                        CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                    } else {
                        WS_PAY_OFF_FLG = 'Y';
                        if (WS_REPAY_AMT_N != 0) {
                            CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = WS_REPAY_AMT_N;
                            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AMT = WS_REPAY_AMT_N;
                            B620_CUS_AC_UPDATE_PROC();
                            if (pgmRtn) return;
                            WS_REPAY_AMT_N = 0;
                            WS_REPAY_AMT_Y = WS_REPAY_AMT_TOT;
                            CEP.TRC(SCCGWA, WS_REPAY_AMT_TOT);
                            CEP.TRC(SCCGWA, WS_REPAY_AMT_Y);
                            CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                        }
                    }
                } else {
                    if ((CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                        && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') {
                            B620_CUS_AC_UPDATE_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
    }
    public void B510_INQ_AC_BAL_PROC() throws IOException,SQLException,Exception {
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIQBAL);
            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
            DDCIQBAL.DATA.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '2' 
                && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
                DDCIQBAL.DATA.AC = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_REL_AC;
            }
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC);
            DDCIQBAL.DATA.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
            DDCIQBAL.DATA.CCY_TYPE = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "WS-I");
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
            CEP.TRC(SCCGWA, "IQBAL-CURR-BAL");
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
            CEP.TRC(SCCGWA, "IQBAL-AVL-BAL");
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
            if (DDCIQBAL.DATA.AVL_BAL < 0) {
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = 0;
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if ((DDCIQBAL.DATA.CCY_STS_WORD.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIQBAL.DATA.CCY_STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIQBAL.DATA.CCY_STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) 
                && DDCIQBAL.DATA.AVL_BAL >= 0) {
                IBS.init(SCCGWA, DCCUTCGQ);
                DCCUTCGQ.AC = DDCIQBAL.DATA.AC;
                DCCUTCGQ.FUNC = 'I';
                DCCUTCGQ.MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
                S000_CALL_DCZUTCGQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUTCGQ.AVA_AMT);
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL += DCCUTCGQ.AVA_AMT;
            }
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B600_CALCULATE_TAX_PROC() throws IOException,SQLException,Exception {
        WS_POINT_AMT = WS_REPAY_AMT_Y;
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 10; WS_TEMP_VARIABLE.WS_J += 1) {
            CEP.TRC(SCCGWA, WS_PAY_OFF_FLG);
            CEP.TRC(SCCGWA, WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE);
            CEP.TRC(SCCGWA, "WS-TAX-RATE(WS-J)");
            CEP.TRC(SCCGWA, WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE);
            if (WS_PAY_OFF_FLG == 'Y') {
                WS_TEMP_VARIABLE.WS_K = WS_TEMP_VARIABLE.WS_J + 20;
                WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL / ( 1 + WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE ) * WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE;
                bigD = new BigDecimal(WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL);
                WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            } else {
                CEP.TRC(SCCGWA, WS_POINT_AMT);
                WS_TEMP_VARIABLE.WS_K = WS_TEMP_VARIABLE.WS_J + 20;
                CEP.TRC(SCCGWA, WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                if (WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL < WS_POINT_AMT) {
                    WS_POINT_AMT = WS_POINT_AMT - WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL;
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL / ( 1 + WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE ) * WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE;
                    bigD = new BigDecimal(WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL);
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                } else {
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL = WS_POINT_AMT;
                    WS_POINT_AMT = 0;
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL / ( 1 + WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE ) * WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE;
                    bigD = new BigDecimal(WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL);
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                }
            }
            CEP.TRC(SCCGWA, "WS-AMT-VAL(1,WS-K)");
            CEP.TRC(SCCGWA, WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EVEN_DATA[1-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCS0018.EVE_DATA[1-1].AMT);
    }
    public void B700_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
            if (!CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0") 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).trim().length() > 0) {
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("2") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("3") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("4") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("5") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("6") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("7") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("8") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("9")) {
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                    JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).trim().length() == 0) WS_TEMP_VARIABLE.WS_A = 0;
                    else WS_TEMP_VARIABLE.WS_A = Short.parseShort(CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1));
                    CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_A-1].WS_AMT_VAL;
                    CEP.TRC(SCCGWA, "WS-A");
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_A);
                    CEP.TRC(SCCGWA, "WS-I");
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                    CEP.TRC(SCCGWA, "S0018-TXN-AMT(WS-I)");
                    CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT != 0) {
                        B620_CUS_AC_UPDATE_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("A")) {
                    CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = WS_REPAY_AMT_Y;
                    CEP.TRC(SCCGWA, "S0018-TXN-AMT(WS-I)");
                    CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT != 0) {
                        B620_CUS_AC_UPDATE_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                WS_AMT_SOURCE_FLG = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).charAt(0);
                if ((WS_AMT_SOURCE_FLG != '1' 
                    && WS_AMT_SOURCE_FLG != '2' 
                    && WS_AMT_SOURCE_FLG != '3' 
                    && WS_AMT_SOURCE_FLG != '4' 
                    && WS_AMT_SOURCE_FLG != '5' 
                    && WS_AMT_SOURCE_FLG != '6' 
                    && WS_AMT_SOURCE_FLG != '7' 
                    && WS_AMT_SOURCE_FLG != '8' 
                    && WS_AMT_SOURCE_FLG != '9' 
                    && WS_AMT_SOURCE_FLG != 'A')) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_MONEY_SOURCE_ERROR);
                }
            }
        }
    }
    public void B620_CUS_AC_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            B622_DR_DDAC_PROC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")) {
            B623_DR_IBAC_PROC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD")) {
            B624_DR_TDAC_PROC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TG")) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO.trim().length() > 0) {
                B625_DR_GDAC_PROC();
                if (pgmRtn) return;
            } else {
                B626_DR_GDAC_PROC();
                if (pgmRtn) return;
            }
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            B632_CR_DDAC_PROC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")) {
            B633_CR_IBAC_PROC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            B661_INR_AC_UPDATE_RPOC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'C'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IT")
            || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IT")) {
            B662_DC_ITM_AC_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B622_DR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW = 'N';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            DDCUDRAC.BV_TYP = '1';
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUDRAC.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '1') {
                DDCUDRAC.BV_TYP = '2';
            }
        }
        DDCUDRAC.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        DDCUDRAC.CCY_TYPE = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUDRAC.TX_AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.TX_TYPE = 'T';
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "102";
        } else {
            DDCUDRAC.TX_MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (pgmRtn) return;
        if (CICQACRI.RC.RC_CODE == 0) {
            DDCUDRAC.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            DDCUDRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUDRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUDRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUDRAC.RLT_BANK = "706660500";
        }
        DDCUDRAC.REMARKS = CMCS0018.RMK;
        S_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B623_DR_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        IBCPOSTA.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        IBCPOSTA.CCY_TYP = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.TX_MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        IBCPOSTA.NARR = CMCS0018.RMK;
        S_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B624_DR_TDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.OPT = '1';
        TDCACDRU.MAC_CNO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        TDCACDRU.AC_SEQ = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        TDCACDRU.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        TDCACDRU.CCY_TYP = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        TDCACDRU.TXN_AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
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
        TDCACDRU.TXN_MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        TDCACDRU.REMARK = CMCS0018.RMK;
        S000_CALL_TDZACDRU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT;
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT = TDCACDRU.PAYING_INT;
        WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT - CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT - WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT;
        CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_PAL_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT);
        CEP.TRC(SCCGWA, WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT);
        CEP.TRC(SCCGWA, WS_PAL_INT_AC_MATCH_FLG);
        if (WS_PAL_INT_AC_MATCH_FLG == 'Y' 
            && WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT > CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT) {
            WS_IN_AC = CMCS0018.P_IN_AC;
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_TOT_AMT - CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
            WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
            if (pgmRtn) return;
        }
        if (WS_PAL_INT_AC_MATCH_FLG == 'N' 
            && WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT != 0) {
            WS_IN_AC = CMCS0018.P_IN_AC;
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RES_PAL_AMT;
            WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
            if (pgmRtn) return;
        }
        if (WS_PAL_INT_AC_MATCH_FLG == 'N' 
            && WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT != 0) {
            WS_IN_AC = CMCS0018.INT_AC;
            WS_IN_AMT = WS_TD_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DRAW_INT_AMT;
            WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
            B640_CR_DDAC_PROC();
            if (pgmRtn) return;
        }
    }
    public void B625_DR_GDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR);
        GDCUMPDR.INPUT.FUNC = 'D';
        GDCUMPDR.INPUT.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        GDCUMPDR.INPUT.CTA_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO;
        GDCUMPDR.INPUT.REF_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO;
        GDCUMPDR.INPUT.SEQ = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        GDCUMPDR.INPUT.AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        GDCUMPDR.OUTPUT.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        GDCUMPDR.INPUT.MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        GDCUMPDR.INPUT.RMK = CMCS0018.RMK;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (pgmRtn) return;
        if (CICQACRI.RC.RC_CODE == 0) {
            GDCUMPDR.INPUT.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        }
        S000_CALL_GDZUMPDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT = GDCUMPDR.OUTPUT.INT;
    }
    public void B626_DR_GDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPDR);
        GDCUMPDR.INPUT.FUNC = 'D';
        GDCUMPDR.INPUT.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        GDCUMPDR.INPUT.SEQ = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ;
        GDCUMPDR.INPUT.AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        GDCUMPDR.OUTPUT.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        GDCUMPDR.INPUT.MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        GDCUMPDR.INPUT.RMK = CMCS0018.RMK;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (pgmRtn) return;
        if (CICQACRI.RC.RC_CODE == 0) {
            GDCUMPDR.INPUT.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            GDCUMPDR.INPUT.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            GDCUMPDR.INPUT.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            GDCUMPDR.INPUT.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        }
        S000_CALL_GDZUDBDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.INT);
        CEP.TRC(SCCGWA, GDCUMPDR.OUTPUT.STAC);
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT = GDCUMPDR.OUTPUT.INT;
    }
    public void B632_CR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCUCRAC.CARD_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            DDCUCRAC.BV_TYP = '1';
        }
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUCRAC.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_TYP == '1') {
                DDCUCRAC.BV_TYP = '2';
            }
        }
        DDCUCRAC.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        DDCUCRAC.CCY_TYPE = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUCRAC.TX_AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        S_CALL_CIZQACRI_OPPAC();
        if (pgmRtn) return;
        if (CICQACRI.RC.RC_CODE == 0) {
            DDCUCRAC.OTHER_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        } else {
            DDCUCRAC.RLT_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
            DDCUCRAC.RLT_AC_NAME = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
            DDCUCRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
            DDCUCRAC.RLT_BANK = "706660500";
        }
        DDCUCRAC.REMARKS = CMCS0018.RMK;
        S_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B633_CR_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        IBCPOSTA.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        IBCPOSTA.CCY_TYP = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.TX_MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        IBCPOSTA.NARR = CMCS0018.RMK;
        S_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B640_CR_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_IN_AC;
        S_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP_OLD.trim().length() > 0) {
            WS_IN_AC_APP = CICQACRI.O_DATA.O_FRM_APP_OLD;
        } else {
            WS_IN_AC_APP = CICQACRI.O_DATA.O_FRM_APP;
        }
        WS_IN_AC_CI_TYP = CICQACRI.O_DATA.O_CI_TYP;
        IBS.init(SCCGWA, DDCUCRAC);
        if (WS_IN_AC_APP.equalsIgnoreCase("DC")) {
            DDCUCRAC.CARD_NO = WS_IN_AC;
            DDCUCRAC.BV_TYP = '1';
        } else if (WS_IN_AC_APP.equalsIgnoreCase("DD")) {
            if (WS_IN_AC_CI_TYP == '1') {
                DDCUCRAC.BV_TYP = '2';
            }
            DDCUCRAC.AC = WS_IN_AC;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        DDCUCRAC.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        DDCUCRAC.CCY_TYPE = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
        DDCUCRAC.TX_AMT = WS_IN_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        }
        DDCUCRAC.RLT_AC = WS_IN_RLT_AC;
        DDCUCRAC.RLT_AC_NAME = WS_IN_RLT_ACNM;
        DDCUCRAC.RLT_BK_NM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        DDCUCRAC.REMARKS = CMCS0018.RMK;
        S_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B660_INR_UPDATE_RPOC() throws IOException,SQLException,Exception {
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '1') {
            B661_INR_AC_UPDATE_RPOC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '2') {
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '3') {
            B661_INR_AC_UPDATE_RPOC();
            if (pgmRtn) return;
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '4') {
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '5') {
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == '6') {
        } else {
        }
    }
    public void B661_INR_AC_UPDATE_RPOC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
            AICUUPIA.DATA.EVENT_CODE = "DR";
        } else {
            AICUUPIA.DATA.EVENT_CODE = "CR";
        }
        AICUUPIA.DATA.AC_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
        AICUUPIA.DATA.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        AICUUPIA.DATA.AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV;
        AICUUPIA.DATA.THEIR_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        AICUUPIA.DATA.ORI_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        AICUUPIA.DATA.PAY_MAN = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        AICUUPIA.DATA.PAY_BKNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        AICUUPIA.DATA.THEIR_AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICUUPIA.DATA.NARR_CD = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        AICUUPIA.DATA.DESC = CMCS0018.RMK;
        S_CALL_AIZUUPIA();
        if (pgmRtn) return;
        WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO = AICUUPIA.DATA.RVS_NO;
    }
    public void B662_DC_ITM_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPUITM);
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D') {
            AICPUITM.DATA.EVENT_CODE = "DR";
        } else {
            AICPUITM.DATA.EVENT_CODE = "CR";
        }
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) AICPUITM.DATA.BR_OLD = 0;
        else AICPUITM.DATA.BR_OLD = Integer.parseInt(CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6).trim().length() == 0) AICPUITM.DATA.BR_NEW = 0;
        else AICPUITM.DATA.BR_NEW = Integer.parseInt(CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(0, 6));
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        AICPUITM.DATA.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(7 - 1, 7 + 3 - 1);
        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = "";
        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC += " ";
        AICPUITM.DATA.ITM_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.substring(10 - 1, 10 + 10 - 1);
        AICPUITM.DATA.AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_OTH_AC;
        AICPUITM.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
        AICPUITM.DATA.THEIR_AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
        AICPUITM.DATA.ORI_AC = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_AC;
        AICPUITM.DATA.PAY_MAN = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM;
        CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_ACNM);
        AICPUITM.DATA.PAY_BKNM = WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM;
        AICPUITM.DATA.NARR_CD = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD;
        CEP.TRC(SCCGWA, WS_OTH_INF[WS_TEMP_VARIABLE.WS_I-1].WS_RLT_BKNM);
        AICPUITM.DATA.DESC = CMCS0018.RMK;
        S000_CALL_AIZPUITM();
        if (pgmRtn) return;
    }
    public void B800_EVENT_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 4; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO.trim().length() > 0 
                && CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO.charAt(0) != 0X00) {
                CEP.TRC(SCCGWA, "S0018-PRDMO-NO(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO);
                CEP.TRC(SCCGWA, "S0018-CA-ABBR(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO);
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].AMT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1]);
                IBS.init(SCCGWA, BPCPOEWA);
                BPCPOEWA.DATA.CNTR_TYPE = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
                BPCPOEWA.DATA.PROD_CODE = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO;
                BPCPOEWA.DATA.EVENT_CODE = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVEN_CD;
                BPCPOEWA.DATA.BR_OLD = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                BPCPOEWA.DATA.BR_NEW = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                BPCPOEWA.DATA.BR_3 = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                BPCPOEWA.DATA.BR_4 = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                BPCPOEWA.DATA.BR_5 = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVE_CCY;
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCPOEWA.DATA.POST_NARR = CMCS0018.RMK;
                for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 30; WS_TEMP_VARIABLE.WS_J += 1) {
                    if ((!IBS.isNumeric(WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO+"")) 
                        || (!IBS.isNumeric(WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL+""))) {
                        WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_MUST_INP_NUM;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL != 0 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO != 0) {
                        CEP.TRC(SCCGWA, "WS-J");
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                        CEP.TRC(SCCGWA, "WS-AMT-NO(WS-I,WS-J)");
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, "WS-AMT-VAL(WS-I,WS-J)");
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_J);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                        WS_TEMP_VARIABLE.WS_N = WS_TEMP_VARIABLE.WS_J - 1;
                        for (WS_TEMP_VARIABLE.WS_M = 1; WS_TEMP_VARIABLE.WS_M <= WS_TEMP_VARIABLE.WS_N; WS_TEMP_VARIABLE.WS_M += 1) {
                            if (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_NO) {
                                CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                                CEP.TRC(SCCGWA, "WS-AMT-NO(WS-I,WS-J)");
                                CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                                WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL += WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_VAL;
                                WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_NO = 0;
                                WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_M-1].WS_AMT_VAL = 0;
                            }
                        }
                        CEP.TRC(SCCGWA, "WS-AMT-NO(WS-I,WS-J)");
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, "WS-AMT-VAL(WS-I,WS-J)");
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL);
                    }
                }
                for (WS_TEMP_VARIABLE.WS_G = 1; WS_TEMP_VARIABLE.WS_G <= 30; WS_TEMP_VARIABLE.WS_G += 1) {
                    if (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL != 0 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO != 0) {
                        CEP.TRC(SCCGWA, "WS-G");
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_G);
                        CEP.TRC(SCCGWA, "WS-AMT-NO(WS-I,WS-G)");
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, "WS-AMT-VAL(WS-I,WS-G)");
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL);
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_G);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO);
                        CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL);
                        WS_TEMP_VARIABLE.WS_K = WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_NO;
                        CEP.TRC(SCCGWA, "WS-K");
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_K);
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_K);
                        BPCPOEWA.DATA.AMT_INFO[WS_TEMP_VARIABLE.WS_K-1].AMT = WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_G-1].WS_AMT_VAL;
                    }
                }
                BPCPOEWA.DATA.OTH = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDGL_CD;
                BPCPOEWA.DATA.AC_NO = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
            }
        }
    }
    public void B850_REGISTER_TAX_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 4; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES") 
                || CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDD") 
                || CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDP")) {
                for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= 30; WS_TEMP_VARIABLE.WS_J += 1) {
                    if ((CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES") 
                        && (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 7 
                        || WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 8) 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL != 0) 
                        || ((CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDD") 
                        || CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("CLDP")) 
                        && (WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 9 
                        || WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 10 
                        || WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_NO == 34) 
                        && WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL != 0)) {
                        WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT += WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_I-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL;
                        WS_TAX_INF_REG.WS_TAX_CCY_REG = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVE_CCY;
                    }
                }
                if ((!WS_TAX_INF_REG.WS_TAX_CCY_REG.equalsIgnoreCase("156") 
                    && WS_TAX_INF_REG.WS_TAX_CCY_REG.trim().length() > 0) 
                    && WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT != 0) {
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_PROD_CD = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_NO;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].EVE_CCY;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].CA_ABBR;
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                    BPCFX.EXR_TYPE = "MDR";
                    BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPCFX.BUY_CCY = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY;
                    BPCFX.BUY_AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    BPCFX.SELL_CCY = "156";
                    S000_LINK_BPZSFX();
                    if (pgmRtn) return;
                    WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156 = BPCFX.SELL_AMT;
                    CEP.TRC(SCCGWA, WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156);
                    IBS.init(SCCGWA, BPCPOEWA);
                    BPCPOEWA.DATA.CNTR_TYPE = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
                    BPCPOEWA.DATA.PROD_CODE = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_PROD_CD;
                    BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPCPOEWA.DATA.EVENT_CODE = "WBJZ";
                    BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_CCY;
                    BPCPOEWA.DATA.BR_OLD = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD;
                    BPCPOEWA.DATA.BR_NEW = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW;
                    if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES")) {
                        BPCPOEWA.DATA.AMT_INFO[7-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT;
                    }
                    BPCPOEWA.DATA.AC_NO = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    BPCPOEWA.DATA.AC_NO_REL = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    S000_CALL_BPZPOEWA();
                    if (pgmRtn) return;
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
                    if (pgmRtn) return;
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
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCPOEWA);
                    BPCPOEWA.DATA.CNTR_TYPE = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD;
                    BPCPOEWA.DATA.PROD_CODE = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_PROD_CD;
                    BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPCPOEWA.DATA.EVENT_CODE = "WBJZ";
                    BPCPOEWA.DATA.CCY_INFO[1-1].CCY = "156";
                    BPCPOEWA.DATA.BR_OLD = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_OLD;
                    BPCPOEWA.DATA.BR_NEW = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_BR_NEW;
                    if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].PRDMO_CD.equalsIgnoreCase("FEES")) {
                        BPCPOEWA.DATA.AMT_INFO[13-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156;
                    } else {
                        BPCPOEWA.DATA.AMT_INFO[43-1].AMT = WS_TAX_INF[WS_TEMP_VARIABLE.WS_I-1].WS_TAX_AMT_156;
                    }
                    BPCPOEWA.DATA.AC_NO = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    BPCPOEWA.DATA.AC_NO_REL = CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_I-1].LOAN_AC;
                    S000_CALL_BPZPOEWA();
                    if (pgmRtn) return;
                }
            }
        }
    public void B900_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCO0018);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS0018);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCO0018);
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 4; WS_TEMP_VARIABLE.WS_I += 1) {
            CMCO0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
            CMCO0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_CREV_NO;
            CMCO0018.RET_AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_AC = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AC;
            CMCO0018.RET_AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_TXN_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AMT;
            CMCO0018.RET_AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_INT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT;
            CMCS0018.RET_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_TXN = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_REPAY_INT_AMT;
            CEP.TRC(SCCGWA, "O0018-RET-AC(WS-I)");
            CEP.TRC(SCCGWA, CMCO0018.RET_AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_AC);
            CEP.TRC(SCCGWA, "O0018-RET-TXN-AMT(WS-I)");
            CEP.TRC(SCCGWA, CMCO0018.RET_AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_TXN_AMT);
            CEP.TRC(SCCGWA, "O0018-RET-INT(WS-I)");
            CEP.TRC(SCCGWA, CMCO0018.RET_AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RET_INT);
        }
        CMCO0018.BAL_IN_AMT = WS_SURPLUS_PAL_AMT_TOT;
        CMCO0018.INT_IN_AMT = WS_SURPLUS_INT_AMT_TOT;
        CEP.TRC(SCCGWA, "O0018-BAL-IN-AMT");
        CEP.TRC(SCCGWA, CMCO0018.BAL_IN_AMT);
        CEP.TRC(SCCGWA, "O0018-INT-IN-AMT");
        CEP.TRC(SCCGWA, CMCO0018.INT_IN_AMT);
        CMCO0018.RET_ERR_CREV = " ";
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCO0018;
        SCCFMT.DATA_LEN = 9521;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_CIZQACRI, CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
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
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_IBZDRAC, IBCPOSTA, true);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_IBZCRAC, IBCPOSTA, true);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_TDZACDRU, TDCACDRU, true);
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR, true);
    }
    public void S000_CALL_GDZUDBDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-DBDR-DR", GDCUMPDR, true);
    }
    public void S000_CALL_DCZUTCGQ() throws IOException,SQLException,Exception {
        IBS.CALLPGM(SCCGWA, "DCZUTCGQ", DCCUTCGQ, true);
        CEP.TRC(SCCGWA, DCCUTCGQ.RC.RC_CODE);
    }
    public void S_CALL_CIZQACRI_OPPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WK_PGM_CIZQACRI, CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
