package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPRMR;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.DD.DDCIQBAL;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.GD.GDCUMPDR;
import com.hisun.GD.GDRPLDR;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCACDRU;

public class CMZS0018 {
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
    CMZS0018_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMZS0018_WS_TEMP_VARIABLE();
    CMZS0018_WS_CUS_INF[] WS_CUS_INF = new CMZS0018_WS_CUS_INF[9];
    CMZS0018_WS_OTH_INF[] WS_OTH_INF = new CMZS0018_WS_OTH_INF[9];
    CMZS0018_WS_INR_AC_INF[] WS_INR_AC_INF = new CMZS0018_WS_INR_AC_INF[9];
    CMZS0018_WS_TD_INF[] WS_TD_INF = new CMZS0018_WS_TD_INF[9];
    CMZS0018_WS_EVEN_DATA[] WS_EVEN_DATA = new CMZS0018_WS_EVEN_DATA[4];
    CMZS0018_WS_RATE_DATA WS_RATE_DATA = new CMZS0018_WS_RATE_DATA();
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
    double WS_REPAY_AVA_BAL = 0;
    CMZS0018_WS_TAX_INF[] WS_TAX_INF = new CMZS0018_WS_TAX_INF[4];
    CMZS0018_WS_TAX_INF_REG WS_TAX_INF_REG = new CMZS0018_WS_TAX_INF_REG();
    char WS_PAL_INT_AC_MATCH_FLG = ' ';
    char WS_PAY_OFF_FLG = ' ';
    char WS_AMT_NO_SAME_FLG = ' ';
    char WS_DEAL_FLG = ' ';
    char WS_REPAY_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CMRBSP17 CMRBSP17 = new CMRBSP17();
    AIRGINF AIRGINF = new AIRGINF();
    CMRFINAL CMRFINAL = new CMRFINAL();
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
    AICPQMIB AICPQMIB = new AICPQMIB();
    DCCUTCGQ DCCUTCGQ = new DCCUTCGQ();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBATH SCCBATH;
    CMCS0018 CMCS0018;
    public CMZS0018() {
        for (int i=0;i<9;i++) WS_CUS_INF[i] = new CMZS0018_WS_CUS_INF();
        for (int i=0;i<9;i++) WS_OTH_INF[i] = new CMZS0018_WS_OTH_INF();
        for (int i=0;i<9;i++) WS_INR_AC_INF[i] = new CMZS0018_WS_INR_AC_INF();
        for (int i=0;i<9;i++) WS_TD_INF[i] = new CMZS0018_WS_TD_INF();
        for (int i=0;i<4;i++) WS_EVEN_DATA[i] = new CMZS0018_WS_EVEN_DATA();
        for (int i=0;i<4;i++) WS_TAX_INF[i] = new CMZS0018_WS_TAX_INF();
    }
    public void MP(SCCGWA SCCGWA, CMCS0018 CMCS0018) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS0018 = CMCS0018;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BSP0351200START");
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, "START-TIME=");
        CEP.TRC(SCCGWA, WS_TS);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BSP0351200END");
        CEP.TRC(SCCGWA, "CMZS0018 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
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
        CEP.TRC(SCCGWA, "A000-EVENT-PROC-START");
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT2);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, CMCS0018.RMK);
        CEP.TRC(SCCGWA, "GWA-CHNL:");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, "GWA:-BSP-FLG:");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BSP_FLG);
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
        CEP.TRC(SCCGWA, "B500-EVENT-PROC-START");
        CEP.TRC(SCCGWA, "B100-5");
        CEP.TRC(SCCGWA, WS_EVEN_DATA[1-1]);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT2);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S2);
        B600_CALCULATE_TAX_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B100-6");
        CEP.TRC(SCCGWA, WS_EVEN_DATA[1-1]);
        CEP.TRC(SCCGWA, "B600-EVENT-PROC-START");
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT2);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S2);
        B700_EVENT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B100-EVENT-PROC-START");
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S1);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_PT2);
        CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[1-1].AMT.AMT_S2);
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
                CEP.TRC(SCCGWA, "S0018-TR-STSW");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW);
                CEP.TRC(SCCGWA, "S0018-CUS-AC(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                CEP.TRC(SCCGWA, "S0018-AC-SEQ(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_SEQ);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if ((CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).trim().length() == 0 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).charAt(0) == 0X00) 
                    && (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.trim().length() == 0 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC.charAt(0) == 0X00)) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_ERR);
                }
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                    && (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("2"))) {
                    IBS.init(SCCGWA, CMRFINAL);
                    if (CMCS0018.F_PRO_CD.trim().length() == 0 
                        || CMCS0018.FIN_AC.trim().length() == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FINAL_AC_PRODNO_NEED);
                    }
                    CMRFINAL.KEY.PROD_CD = CMCS0018.F_PRO_CD;
                    CMRFINAL.KEY.CUS_AC = CMCS0018.FIN_AC;
                    T000_READ_CMTFINAL();
                    if (pgmRtn) return;
                    CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV = CMRFINAL.CREV_NO;
                    CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC = CMRFINAL.CR_AC;
                    CEP.TRC(SCCGWA, "FINAL-CREV-NO");
                    CEP.TRC(SCCGWA, CMRFINAL.CREV_NO);
                    CEP.TRC(SCCGWA, "FINAL-CR-AC");
                    CEP.TRC(SCCGWA, CMRFINAL.CR_AC);
                    IBS.init(SCCGWA, AIRGINF);
                    IBS.init(SCCGWA, AICRGINF);
                    AICRGINF.INFO.FUNC = 'Q';
                    AIRGINF.KEY.RVS_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV;
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                    if (AICRGINF.RETURN_INFO == 'N') {
                        WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_CREV_NOT_EXIT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, AIRGINF.G_BAL);
                    if (AIRGINF.G_BAL < CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AVA_BAL_LT_REPAY_BAL);
                    }
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                    JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT = AIRGINF.G_BAL - CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
                        CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                        CEP.TRC(SCCGWA, WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT);
                        CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = AIRGINF.G_BAL;
                    }
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
                CEP.TRC(SCCGWA, "S0018-TXN-AMT(WS-I)");
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                if ((!CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("3")) 
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
            if (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD.equalsIgnoreCase("FTP") 
                || (CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO.trim().length() > 0 
                && CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_NO.charAt(0) != 0X00)) {
                CEP.TRC(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].PRDMO_CD);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS0018.EVE_DATA[WS_TEMP_VARIABLE.WS_J-1].AMT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1]);
                CEP.TRC(SCCGWA, "B100-INPUT-CHECK-PROC-");
                CEP.TRC(SCCGWA, WS_EVEN_DATA[WS_TEMP_VARIABLE.WS_J-1]);
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
                CEP.TRC(SCCGWA, "WS-CUS-AVA-BAL(WS-I)");
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL);
                WS_REPAY_AVA_BAL += WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
                WS_REPAY_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_REPAY_FLG);
        if (WS_REPAY_AVA_BAL <= 0 
            && WS_REPAY_FLG == 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.AC_BAL_ZERO);
        }
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 9; WS_TEMP_VARIABLE.WS_I += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW);
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("3")) {
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL);
                CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL > 0) {
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
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG") 
                            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT != 0) {
                            CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT);
                            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT < WS_REPAY_AMT_N) {
                                WS_REPAY_AMT_N = WS_REPAY_AMT_N - WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT;
                                WS_REPAY_AMT_Y = WS_REPAY_AMT_Y + WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT;
                                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_REPAY_INT_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT;
                                CEP.TRC(SCCGWA, WS_REPAY_AMT_Y);
                                CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                            } else {
                                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT - WS_REPAY_AMT_N;
                                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_REPAY_INT_AMT = WS_REPAY_AMT_N;
                                WS_SURPLUS_INT_AMT_TOT += WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT;
                                CEP.TRC(SCCGWA, WS_SURPLUS_INT_AMT_TOT);
                                WS_PAY_OFF_FLG = 'Y';
                                WS_REPAY_AMT_N = 0;
                                WS_REPAY_AMT_Y = WS_REPAY_AMT_TOT;
                                if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT > 0) {
                                    WS_IN_AC = CMCS0018.INT_AC;
                                    WS_IN_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT;
                                    WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                                    WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
                                    B640_CR_DDAC_PROC();
                                    if (pgmRtn) return;
                                }
                                CEP.TRC(SCCGWA, WS_REPAY_AMT_Y);
                                CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                            }
                        }
                    } else {
                        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI") 
                            && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV.trim().length() > 0) {
                            WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL - WS_REPAY_AMT_N;
                        }
                        CEP.TRC(SCCGWA, WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT);
                        WS_PAY_OFF_FLG = 'Y';
                        if (WS_REPAY_AMT_N != 0) {
                            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI") 
                                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV.trim().length() > 0) {
                                CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL;
                            } else {
                                CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = WS_REPAY_AMT_N;
                            }
                            CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AMT = WS_REPAY_AMT_N;
                            B620_CUS_AC_UPDATE_PROC();
                            if (pgmRtn) return;
                            WS_REPAY_AMT_N = 0;
                            WS_REPAY_AMT_Y = WS_REPAY_AMT_TOT;
                            CEP.TRC(SCCGWA, WS_REPAY_AMT_TOT);
                            CEP.TRC(SCCGWA, WS_REPAY_AMT_Y);
                            CEP.TRC(SCCGWA, WS_REPAY_AMT_N);
                            if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")) {
                                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_INT_AMT;
                            }
                        }
                        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                        if (WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT > 0 
                            && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_SURPLUS_PAL_AMT_TOT += WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT;
                            CEP.TRC(SCCGWA, WS_SURPLUS_PAL_AMT_TOT);
                            WS_IN_AC = CMCS0018.P_IN_AC;
                            WS_IN_AMT = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT;
                            WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
                            B640_CR_DDAC_PROC();
                            if (pgmRtn) return;
                        }
                        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT);
                        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT > 0) {
                            WS_SURPLUS_INT_AMT_TOT += WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT;
                            CEP.TRC(SCCGWA, WS_SURPLUS_INT_AMT_TOT);
                            WS_IN_AC = CMCS0018.INT_AC;
                            WS_IN_AMT = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_GD_SURPLUS_INT_AMT;
                            WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
                            B640_CR_DDAC_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            } else {
                if ((CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                    && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00)) {
                    if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'X' 
                        || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'I' 
                        || CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_TYP == 'N') {
                        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
                            && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_DR_AMT = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
                        }
                        B620_CUS_AC_UPDATE_PROC();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT);
                        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                        if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
                        JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
                        if (WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT > 0 
                            && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D' 
                            && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                            && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_SURPLUS_PAL_AMT_TOT += WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT;
                            CEP.TRC(SCCGWA, WS_SURPLUS_PAL_AMT_TOT);
                            WS_IN_AC = CMCS0018.P_IN_AC;
                            WS_IN_AMT = WS_INR_AC_INF[WS_TEMP_VARIABLE.WS_I-1].WS_INR_SURPLUS_AMT;
                            WS_IN_RLT_AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                            WS_IN_RLT_ACNM = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
                            B640_CR_DDAC_PROC();
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
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW == null) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW += " ";
            if (!CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                S000_CALL_AIZPQMIB();
                if (pgmRtn) return;
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = AICPQMIB.OUTPUT_DATA.CBAL;
                CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
            } else {
                IBS.init(SCCGWA, CMRFINAL);
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                if (CMCS0018.F_PRO_CD.trim().length() == 0 
                    || CMCS0018.FIN_AC.trim().length() == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FINAL_AC_PRODNO_NEED);
                }
                CMRFINAL.KEY.PROD_CD = CMCS0018.F_PRO_CD;
                CMRFINAL.KEY.CUS_AC = CMCS0018.FIN_AC;
                T000_READ_CMTFINAL();
                if (pgmRtn) return;
                CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV = CMRFINAL.CREV_NO;
                CEP.TRC(SCCGWA, "FINAL-CREV-NO");
                CEP.TRC(SCCGWA, CMRFINAL.CREV_NO);
                AICRGINF.INFO.FUNC = 'Q';
                AIRGINF.KEY.RVS_NO = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CREV;
                CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                if (AICRGINF.RETURN_INFO == 'N') {
                    WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_CREV_NOT_EXIT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = AIRGINF.G_BAL;
                CEP.TRC(SCCGWA, AIRGINF.G_BAL);
            }
        } else if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG == 'D'
            && WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")) {
            if (CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO.trim().length() > 0 
                && CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO.trim().length() > 0) {
                IBS.init(SCCGWA, GDRPLDR);
                IBS.init(SCCGWA, GDCRPLDR);
                GDRPLDR.KEY.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                GDCRPLDR.FUNC = 'B';
                GDCRPLDR.OPT = 'C';
                GDCRPLDR.REC_PTR = GDRPLDR;
                GDCRPLDR.REC_LEN = 311;
                S000_CALL_GDZRPLDR();
                if (pgmRtn) return;
                IBS.init(SCCGWA, GDRPLDR);
                GDCRPLDR.FUNC = 'B';
                GDCRPLDR.OPT = 'R';
                S000_CALL_GDZRPLDR();
                if (pgmRtn) return;
                while (GDCRPLDR.RETURN_INFO != 'N') {
                    if (GDRPLDR.DEAL_CD.equalsIgnoreCase(CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CTA_NO) 
                        && GDRPLDR.BSREF.equalsIgnoreCase(CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].REF_NO)) {
                        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = GDRPLDR.RELAT_AMT;
                    }
                    GDCRPLDR.FUNC = 'B';
                    GDCRPLDR.OPT = 'R';
                    S000_CALL_GDZRPLDR();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, GDRPLDR);
                GDCRPLDR.FUNC = 'B';
                GDCRPLDR.OPT = 'E';
                S000_CALL_GDZRPLDR();
                if (pgmRtn) return;
                if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CTANO_NO_INVALID);
                }
            } else {
                IBS.init(SCCGWA, DDCIQBAL);
                CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
                CEP.TRC(SCCGWA, CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC);
                DDCIQBAL.DATA.AC = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CUS_AC;
                DDCIQBAL.DATA.CCY = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_CCY;
                DDCIQBAL.DATA.CCY_TYPE = CMCS0018.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP;
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_BAL = DDCIQBAL.DATA.CURR_BAL;
                WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
            }
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B600_CALCULATE_TAX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REPAY_AMT_Y);
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
                    CEP.TRC(SCCGWA, "ZXJ111");
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL = WS_POINT_AMT;
                    WS_POINT_AMT = 0;
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_AMT_VAL / ( 1 + WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE ) * WS_RATE_DATA.WS_TAX_ARRY[WS_TEMP_VARIABLE.WS_J-1].WS_TAX_RATE;
                    bigD = new BigDecimal(WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL);
                    WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                }
            }
            CEP.TRC(SCCGWA, "ZXJ222");
            CEP.TRC(SCCGWA, "WS-AMT-VAL(1,WS-K)");
            CEP.TRC(SCCGWA, WS_EVEN_DATA[1-1].WS_AMT_ARRY[WS_TEMP_VARIABLE.WS_K-1].WS_AMT_VAL);
        }
        CEP.TRC(SCCGWA, "B600-EVENT-PROC-START-1");
