package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS0035 {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM110";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    CMZS0035_WS_BATH_PARM WS_BATH_PARM = new CMZS0035_WS_BATH_PARM();
    double WS_DR_TXN_AMT = 0;
    String WS_TS = " ";
    String WS_AC = " ";
    String WS_REL_AC = " ";
    double WS_ZHJ_AVL_BAL = 0;
    double WS_ZHJ_TXN_BAL = 0;
    CMZS0035_WS_OUTPUT WS_OUTPUT = new CMZS0035_WS_OUTPUT();
    char WS_END_FLG = ' ';
    char WS_PAYMENT_DR_ALL_FLG = ' ';
    char WS_ZHJ_REQUIRE_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CMCS8860 CMCS8860 = new CMCS8860();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUTCGQ DCCUTCGQ = new DCCUTCGQ();
    CICQACRL CICQACRL = new CICQACRL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCSBSPO CMCSBSPO;
    SCCBATH SCCBATH;
    CMCS0035 CMCS0035;
    public void MP(SCCGWA SCCGWA, CMCS0035 CMCS0035) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS0035 = CMCS0035;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS0035 return!");
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
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, "BSP0351100START");
        CEP.TRC(SCCGWA, "START-TIME=");
        CEP.TRC(SCCGWA, WS_TS);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B050_CD_DR_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BSP0351100END");
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 2; WS_I += 1) {
            if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
            if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
            if (CMCS0035.AC_DATA[WS_I-1].DC_FLG == 'D' 
                && ((CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
                || (CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")))) {
                if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
                if (CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_PAYMENT_DR_ALL_FLG = 'Y';
                }
                if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
                if (CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ZHJ_REQUIRE_FLG = 'Y';
                }
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = CMCS0035.AC_DATA[WS_I-1].CUS_AC;
                S000_CALL_CIZQACRI();
                if (pgmRtn) return;
                if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                    && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ONLY_SUPPORT_DC_DD);
                }
                IBS.init(SCCGWA, DDCIQBAL);
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CUS_AC);
                DDCIQBAL.DATA.AC = CMCS0035.AC_DATA[WS_I-1].CUS_AC;
                DDCIQBAL.DATA.CCY = CMCS0035.AC_DATA[WS_I-1].CCY;
                DDCIQBAL.DATA.CCY_TYPE = CMCS0035.AC_DATA[WS_I-1].CCY_TYP;
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                if (DDCIQBAL.DATA.AVL_BAL < 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_USEABLE_AMT_ZERO);
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
                if (DDCIQBAL.DATA.CCY_STS_WORD.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, DCCUTCGQ);
                    DCCUTCGQ.AC = CMCS0035.AC_DATA[WS_I-1].CUS_AC;
                    DCCUTCGQ.FUNC = 'I';
                    DCCUTCGQ.MMO = CMCS0035.AC_DATA[WS_I-1].RMK_CD;
                    S000_CALL_DCZUTCGQ();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCCUTCGQ.AVA_AMT);
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                if (DDCIQBAL.DATA.AVL_BAL == 0 
                    && DCCUTCGQ.AVA_AMT == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_USEABLE_AMT_ZERO);
                }
                DDCIQBAL.DATA.AVL_BAL += DCCUTCGQ.AVA_AMT;
                if (DDCIQBAL.DATA.AVL_BAL < CMCS0035.AC_DATA[WS_I-1].TXN_AMT) {
                    WS_DR_TXN_AMT = DDCIQBAL.DATA.AVL_BAL;
                } else {
                    WS_DR_TXN_AMT = CMCS0035.AC_DATA[WS_I-1].TXN_AMT;
                }
                CEP.TRC(SCCGWA, WS_DR_TXN_AMT);
                if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
                JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
                if (CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].TR_STSW);
                    CEP.TRC(SCCGWA, CMCS0035.REQ_SYS);
                    if (CMCS0035.AC_DATA[WS_I-1].LOW_BAL == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_LOW_BAL_M_INPUT);
                    }
                    if (CMCS0035.AC_DATA[WS_I-1].HIGH_BAL == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HIGH_BAL_M_INPUT);
                    }
                    CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].RES_BAL);
                    if (CMCS0035.AC_DATA[WS_I-1].RES_BAL == 0) {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RES_BAL_M_INPUT);
                    }
                    WS_ZHJ_AVL_BAL = 0;
                    WS_ZHJ_AVL_BAL = DDCIQBAL.DATA.AVL_BAL - CMCS0035.AC_DATA[WS_I-1].RES_BAL;
                    CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                    CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].RES_BAL);
                    CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].LOW_BAL);
                    CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].HIGH_BAL);
                    CEP.TRC(SCCGWA, WS_ZHJ_AVL_BAL);
                    if (WS_ZHJ_AVL_BAL >= CMCS0035.AC_DATA[WS_I-1].LOW_BAL) {
                        WS_ZHJ_TXN_BAL = 0;
                        if (WS_ZHJ_AVL_BAL > CMCS0035.AC_DATA[WS_I-1].HIGH_BAL) {
                            WS_ZHJ_TXN_BAL = CMCS0035.AC_DATA[WS_I-1].HIGH_BAL;
                        } else {
                            WS_ZHJ_TXN_BAL = WS_ZHJ_AVL_BAL;
                        }
                        CEP.TRC(SCCGWA, WS_ZHJ_TXN_BAL);
                    } else {
                        CEP.TRC(SCCGWA, WS_ZHJ_AVL_BAL);
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_USEABLE_AMT_ZERO);
                    }
                }
            }
        }
        if (WS_PAYMENT_DR_ALL_FLG == 'Y') {
            for (WS_J = 1; WS_J <= 5; WS_J += 1) {
                if (CMCS0035.MIB_DATA[WS_J-1].I_DC_FLG == 'D' 
                    || CMCS0035.MIB_DATA[WS_J-1].I_DC_FLG == 'C') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_SUPPORT_DR_CR_TRAN);
                }
            }
        }
    }
    public void B050_CD_DR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCS8860);
        for (WS_I = 1; WS_I <= 2; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].DC_FLG);
            CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].AC_TYP);
            CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CUS_AC);
            CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CCY);
            CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CCY_TYP);
            CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].TXN_AMT);
            CEP.TRC(SCCGWA, WS_ZHJ_TXN_BAL);
            if (CMCS0035.AC_DATA[WS_I-1].DC_FLG != ' ') {
                CEP.TRC(SCCGWA, "WS-I");
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, "S0035-DC-FLG(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].DC_FLG);
                CEP.TRC(SCCGWA, "S0035-TR-STSW(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].TR_STSW);
                CEP.TRC(SCCGWA, "S0035-CHK-STSW(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CHK_STSW);
                CEP.TRC(SCCGWA, "S0035-AC-TYP(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].AC_TYP);
                CEP.TRC(SCCGWA, "S0035-CUS-AC(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CUS_AC);
                CEP.TRC(SCCGWA, "S0035-AC-SEQ(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].AC_SEQ);
                CEP.TRC(SCCGWA, "S0035-CCY(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CCY);
                CEP.TRC(SCCGWA, "S0035-CCY-TYP(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].CCY_TYP);
                CEP.TRC(SCCGWA, "S0035-TXN-AMT(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].TXN_AMT);
                CEP.TRC(SCCGWA, "S0035-AC-NM(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].AC_NM);
                CEP.TRC(SCCGWA, "S0035-ID-TYP(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].ID_TYP);
                CEP.TRC(SCCGWA, "S0035-ID-NO(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].ID_NO);
                CEP.TRC(SCCGWA, "S0035-AC-CREV(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].AC_CREV);
                CEP.TRC(SCCGWA, "S0035-RMK-CD(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].RMK_CD);
                CEP.TRC(SCCGWA, "S0035-OPP-AC(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].OPP_AC);
                CEP.TRC(SCCGWA, "S0035-OPP-ANM(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].OPP_ANM);
                CEP.TRC(SCCGWA, "S0035-OPP-BNM(WS-I)");
                CEP.TRC(SCCGWA, CMCS0035.AC_DATA[WS_I-1].OPP_BNM);
            }
            CMCS8860.AC_DATA[WS_I-1].DC_FLG = CMCS0035.AC_DATA[WS_I-1].DC_FLG;
            if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
            CMCS8860.AC_DATA[WS_I-1].TR_CTL = CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(0, 2);
            if (CMCS0035.AC_DATA[WS_I-1].TR_STSW == null) CMCS0035.AC_DATA[WS_I-1].TR_STSW = "";
            JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].TR_STSW.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].TR_STSW += " ";
            if (CMCS0035.AC_DATA[WS_I-1].TR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CMCS8860.AC_DATA[WS_I-1].TR_CTL = "1";
                if (CMCS8860.AC_DATA[WS_I-1].TR_CTL == null) CMCS8860.AC_DATA[WS_I-1].TR_CTL = "";
                JIBS_tmp_int = CMCS8860.AC_DATA[WS_I-1].TR_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8860.AC_DATA[WS_I-1].TR_CTL += " ";
                CMCS8860.AC_DATA[WS_I-1].TR_CTL = CMCS8860.AC_DATA[WS_I-1].TR_CTL.substring(0, 6 - 1) + "1" + CMCS8860.AC_DATA[WS_I-1].TR_CTL.substring(6 + 1 - 1);
            }
            if (CMCS0035.AC_DATA[WS_I-1].CHK_STSW == null) CMCS0035.AC_DATA[WS_I-1].CHK_STSW = "";
            JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].CHK_STSW.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].CHK_STSW += " ";
            if (CMCS0035.AC_DATA[WS_I-1].CHK_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                CMCS8860.AC_DATA[WS_I-1].CHK_CTL = "1";
                if (CMCS8860.AC_DATA[WS_I-1].CHK_CTL == null) CMCS8860.AC_DATA[WS_I-1].CHK_CTL = "";
                JIBS_tmp_int = CMCS8860.AC_DATA[WS_I-1].CHK_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8860.AC_DATA[WS_I-1].CHK_CTL += " ";
                CMCS8860.AC_DATA[WS_I-1].CHK_CTL = CMCS8860.AC_DATA[WS_I-1].CHK_CTL.substring(0, 2 - 1) + "1" + CMCS8860.AC_DATA[WS_I-1].CHK_CTL.substring(2 + 1 - 1);
            }
            if (CMCS0035.AC_DATA[WS_I-1].CHK_STSW == null) CMCS0035.AC_DATA[WS_I-1].CHK_STSW = "";
            JIBS_tmp_int = CMCS0035.AC_DATA[WS_I-1].CHK_STSW.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CMCS0035.AC_DATA[WS_I-1].CHK_STSW += " ";
            if (CMCS0035.AC_DATA[WS_I-1].CHK_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CMCS8860.AC_DATA[WS_I-1].CHK_CTL = "1";
                if (CMCS8860.AC_DATA[WS_I-1].CHK_CTL == null) CMCS8860.AC_DATA[WS_I-1].CHK_CTL = "";
                JIBS_tmp_int = CMCS8860.AC_DATA[WS_I-1].CHK_CTL.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) CMCS8860.AC_DATA[WS_I-1].CHK_CTL += " ";
                CMCS8860.AC_DATA[WS_I-1].CHK_CTL = CMCS8860.AC_DATA[WS_I-1].CHK_CTL.substring(0, 3 - 1) + "1" + CMCS8860.AC_DATA[WS_I-1].CHK_CTL.substring(3 + 1 - 1);
            }
            CMCS8860.AC_DATA[WS_I-1].TRAC_TYP = CMCS0035.AC_DATA[WS_I-1].AC_TYP;
            if (CMCS0035.AC_DATA[WS_I-1].DC_FLG == 'C') {
                WS_AC = CMCS0035.AC_DATA[WS_I-1].CUS_AC;
                B051_GET_ACNO_PROC();
                if (pgmRtn) return;
                CMCS8860.AC_DATA[WS_I-1].CUS_AC = WS_REL_AC;
            } else {
                CMCS8860.AC_DATA[WS_I-1].CUS_AC = CMCS0035.AC_DATA[WS_I-1].CUS_AC;
            }
            CEP.TRC(SCCGWA, CMCS8860.AC_DATA[WS_I-1].CUS_AC);
            CMCS8860.AC_DATA[WS_I-1].AC_SEQ = CMCS0035.AC_DATA[WS_I-1].AC_SEQ;
            CMCS8860.AC_DATA[WS_I-1].CCY = CMCS0035.AC_DATA[WS_I-1].CCY;
            CMCS8860.AC_DATA[WS_I-1].CCY_TYP = CMCS0035.AC_DATA[WS_I-1].CCY_TYP;
            CMCS8860.AC_DATA[WS_I-1].TXN_AMT = CMCS0035.AC_DATA[WS_I-1].TXN_AMT;
            if (WS_PAYMENT_DR_ALL_FLG == 'Y') {
                CMCS8860.AC_DATA[WS_I-1].TXN_AMT = WS_DR_TXN_AMT;
            }
            if (WS_ZHJ_REQUIRE_FLG == 'Y') {
                CMCS8860.AC_DATA[WS_I-1].TXN_AMT = WS_ZHJ_TXN_BAL;
            }
            CEP.TRC(SCCGWA, CMCS8860.AC_DATA[WS_I-1].TXN_AMT);
            CMCS8860.AC_DATA[WS_I-1].AC_NM = CMCS0035.AC_DATA[WS_I-1].AC_NM;
            CMCS8860.AC_DATA[WS_I-1].ID_TYP = CMCS0035.AC_DATA[WS_I-1].ID_TYP;
            CMCS8860.AC_DATA[WS_I-1].ID_NO = CMCS0035.AC_DATA[WS_I-1].ID_NO;
            CMCS8860.AC_DATA[WS_I-1].AC_CREV = CMCS0035.AC_DATA[WS_I-1].AC_CREV;
            CMCS8860.AC_DATA[WS_I-1].RMK_CD = CMCS0035.AC_DATA[WS_I-1].RMK_CD;
            CMCS8860.AC_DATA[WS_I-1].OPP_AC = CMCS0035.AC_DATA[WS_I-1].OPP_AC;
            CMCS8860.AC_DATA[WS_I-1].OPP_ANM = CMCS0035.AC_DATA[WS_I-1].OPP_ANM;
            CMCS8860.AC_DATA[WS_I-1].OPP_BNM = CMCS0035.AC_DATA[WS_I-1].OPP_BNM;
            CMCS8860.AC_DATA[WS_I-1].ERR_PRAC = CMCS0035.AC_DATA[WS_I-1].ERR_PRAC;
        }
        for (WS_J = 1; WS_J <= 5; WS_J += 1) {
            if (CMCS0035.MIB_DATA[WS_J-1].I_DC_FLG != ' ') {
                CEP.TRC(SCCGWA, "S0035-I-DC-FLG(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_DC_FLG);
                CEP.TRC(SCCGWA, "S0035-I-AC-TYP(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_AC_TYP);
                CEP.TRC(SCCGWA, "S0035-INR-AC(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].INR_AC);
                CEP.TRC(SCCGWA, "S0035-INR-CCY(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].INR_CCY);
                CEP.TRC(SCCGWA, "S0035-CREV-NO(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].CREV_NO);
                CEP.TRC(SCCGWA, "S0035-I-TX-AMT(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_TX_AMT);
                CEP.TRC(SCCGWA, "S0035-I-RM-CD(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_RM_CD);
                CEP.TRC(SCCGWA, "S0035-I-OPP-AC(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_OPP_AC);
                CEP.TRC(SCCGWA, "S0035-I-AC-NM(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_AC_NM);
                CEP.TRC(SCCGWA, "S0035-I-BK-NM(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_BK_NM);
                CEP.TRC(SCCGWA, "S0035-INR-CINO(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].INR_CINO);
                CEP.TRC(SCCGWA, "S0035-INR-PROD(WS-J)");
                CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].INR_PROD);
            }
            CMCS8860.MIB_DATA[WS_J-1].INR_DCFL = CMCS0035.MIB_DATA[WS_J-1].I_DC_FLG;
            CMCS8860.MIB_DATA[WS_J-1].INR_ACTP = CMCS0035.MIB_DATA[WS_J-1].I_AC_TYP;
            CMCS8860.MIB_DATA[WS_J-1].INR_AC = CMCS0035.MIB_DATA[WS_J-1].INR_AC;
            CMCS8860.MIB_DATA[WS_J-1].INR_CCY = CMCS0035.MIB_DATA[WS_J-1].INR_CCY;
            CMCS8860.MIB_DATA[WS_J-1].CREV_NO = CMCS0035.MIB_DATA[WS_J-1].CREV_NO;
            CMCS8860.MIB_DATA[WS_J-1].INR_TAMT = CMCS0035.MIB_DATA[WS_J-1].I_TX_AMT;
            CMCS8860.MIB_DATA[WS_J-1].INR_RCD = CMCS0035.MIB_DATA[WS_J-1].I_RM_CD;
            CMCS8860.MIB_DATA[WS_J-1].INR_OAC = CMCS0035.MIB_DATA[WS_J-1].I_OPP_AC;
            CMCS8860.MIB_DATA[WS_J-1].INR_OANM = CMCS0035.MIB_DATA[WS_J-1].I_AC_NM;
            CMCS8860.MIB_DATA[WS_J-1].INR_OBNM = CMCS0035.MIB_DATA[WS_J-1].I_BK_NM;
            CMCS8860.MIB_DATA[WS_J-1].INR_CINO = CMCS0035.MIB_DATA[WS_J-1].INR_CINO;
            CMCS8860.MIB_DATA[WS_J-1].INR_PROD = CMCS0035.MIB_DATA[WS_J-1].INR_PROD;
            if (WS_ZHJ_REQUIRE_FLG == 'Y') {
                CMCS8860.MIB_DATA[WS_J-1].INR_TAMT = WS_ZHJ_TXN_BAL;
            }
            CEP.TRC(SCCGWA, CMCS0035.MIB_DATA[WS_J-1].I_TX_AMT);
            CEP.TRC(SCCGWA, CMCS8860.MIB_DATA[WS_J-1].INR_TAMT);
        }
        CEP.TRC(SCCGWA, "S0035-HLD-NO");
        CEP.TRC(SCCGWA, CMCS0035.HLD_NO);
        CEP.TRC(SCCGWA, "S0035-HLD-TYP");
        CEP.TRC(SCCGWA, CMCS0035.HLD_TYP);
        CEP.TRC(SCCGWA, "S0035-HLD-AMT");
        CEP.TRC(SCCGWA, CMCS0035.HLD_AMT);
        CEP.TRC(SCCGWA, "S0035-VAL-DATE");
        CEP.TRC(SCCGWA, CMCS0035.VAL_DATE);
        CEP.TRC(SCCGWA, "S0035-EXP-DATE");
        CEP.TRC(SCCGWA, CMCS0035.EXP_DATE);
        CEP.TRC(SCCGWA, "S0035-HLD-RSN");
        CEP.TRC(SCCGWA, CMCS0035.HLD_RSN);
        CEP.TRC(SCCGWA, "S0035-SMR");
        CEP.TRC(SCCGWA, CMCS0035.SMR);
        CMCS8860.FIN_PROD = CMCS0035.F_PRO_CD;
        CMCS8860.FIN_AC = CMCS0035.FIN_AC;
        CMCS8860.HLD_NO = CMCS0035.HLD_NO;
        CMCS8860.HLD_TYP = CMCS0035.HLD_TYP;
        CMCS8860.HLD_AMT = CMCS0035.HLD_AMT;
        CMCS8860.VAL_DATE = CMCS0035.VAL_DATE;
        CMCS8860.EXP_DATE = CMCS0035.EXP_DATE;
        CMCS8860.HLD_RSN = CMCS0035.HLD_RSN;
        CMCS8860.REMARK = CMCS0035.SMR;
        S000_CALL_CMCS8860();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_CMCS8860() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-U-CMZS8860", CMCS8860);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S000_CALL_DCZUTCGQ() throws IOException,SQLException,Exception {
        IBS.CALLPGM(SCCGWA, "DCZUTCGQ", DCCUTCGQ, true);
        CEP.TRC(SCCGWA, DCCUTCGQ.RC.RC_CODE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B051_GET_ACNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = WS_AC;
        CICQACRL.DATA.AC_REL = "09";
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            WS_REL_AC = CICQACRL.DATA.AC_NO;
        } else if (CICQACRL.RC.RC_CODE == 1021) {
            WS_REL_AC = CICQACRL.DATA.AC_NO;
        } else if (CICQACRL.RC.RC_CODE == 0) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = WS_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.YW_TYP);
            if (DDCIMMST.DATA.YW_TYP.equalsIgnoreCase("103")) {
                WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
                WS_REL_AC = CICQACRL.DATA.AC_NO;
            }
        } else {
            CEP.ERR(SCCGWA, CICQACRL.RC);
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
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
