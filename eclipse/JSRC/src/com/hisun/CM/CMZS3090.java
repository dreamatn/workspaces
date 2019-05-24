package com.hisun.CM;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.TD.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3090 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM390";
    String WK_YL = "YL";
    String WK_SZ = "SZ";
    String WS_ERR_MSG = " ";
    int WS_AC_BR = 0;
    String WS_AC_NM = " ";
    String WS_HLD_NOTEMP = " ";
    String WS_HLD_DATE = " ";
    String WS_CUS_AC = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCURHLD DCCURHLD = new DCCURHLD();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUHLD DCCUHLD = new DCCUHLD();
    CMCO3090 CMCO3090 = new CMCO3090();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CICQACAC CICQACAC = new CICQACAC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    TDCACE TDCACE = new TDCACE();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3090 CMCS3090;
    public void MP(SCCGWA SCCGWA, CMCS3090 CMCS3090) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3090 = CMCS3090;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3090 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CHECK_CUS_AC_INPUT_INF();
        if (pgmRtn) return;
        B300_RHLD_DEDUCT_HOLD_PROC();
        if (pgmRtn) return;
        B400_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3090.CHK_CTL);
        CEP.TRC(SCCGWA, CMCS3090.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS3090.TRK3_DAT);
        if (CMCS3090.HLD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_INP_ERR);
        }
        if (CMCS3090.TXN_AMT == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
        }
        if (CMCS3090.INR_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_INR_AC_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3090.TX_TYP);
        if (CMCS3090.TX_TYP != '1' 
            && CMCS3090.TX_TYP != '2') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TX_TYPE_INP_ERR);
        }
        if (CMCS3090.TX_TYP == '1') {
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && CMCS3090.HLD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_INP_ERR);
            }
            if (CMCS3090.CUS_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_INP_ERR);
            }
            if (CMCS3090.TXN_AMT == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_AMT_INP_ERR);
            }
        }
        if (CMCS3090.TX_TYP == '2') {
            if (CMCS3090.HLD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_INP_ERR);
            }
        }
        if (CMCS3090.TXN_RGN == ' ') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CEP.TRC(SCCGWA, CMCS3090.HLD_DT);
        if (((SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070500")) 
            || (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070501")) 
            || (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070502")) 
            || (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070504"))) 
            && (CMCS3090.HLD_DT == 0)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_DATE_ERR);
        }
        WS_HLD_DATE = "" + CMCS3090.HLD_DT;
        JIBS_tmp_int = WS_HLD_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_HLD_DATE = "0" + WS_HLD_DATE;
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070504")) {
            WS_HLD_NOTEMP = WK_YL + WS_HLD_DATE + CMCS3090.HLD_NO;
        } else if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070500")
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070501")
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070502")) {
            WS_HLD_NOTEMP = WK_SZ + WS_HLD_DATE + CMCS3090.HLD_NO;
        } else {
            WS_HLD_NOTEMP = CMCS3090.HLD_NO;
        }
    }
    public void B200_CHECK_CUS_AC_INPUT_INF() throws IOException,SQLException,Exception {
        if (CMCS3090.CUS_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_ERR);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = CMCS3090.CUS_AC;
        CICQACRL.DATA.AC_REL = "04";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        WS_CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        if (CMCS3090.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
        }
        if (CMCS3090.CCY.equalsIgnoreCase("156")) {
            CMCS3090.CCY_TYP = '1';
        }
        if (CMCS3090.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3090.CCY);
        CEP.TRC(SCCGWA, CMCS3090.CCY_TYP);
        if ((!CMCS3090.CCY.equalsIgnoreCase("156")) 
            && (CMCS3090.CCY_TYP == ' ' 
            || CMCS3090.CCY_TYP == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
        }
        if (CMCS3090.RMK_CD.trim().length() == 0) {
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
        if (CMCS3090.RMK_CD == null) CMCS3090.RMK_CD = "";
        JIBS_tmp_int = CMCS3090.RMK_CD.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3090.RMK_CD += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3090.RMK_CD.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
        if (CMCS3090.RMK_CD2.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_CD2_ERR);
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (CMCS3090.RMK_CD2 == null) CMCS3090.RMK_CD2 = "";
        JIBS_tmp_int = CMCS3090.RMK_CD2.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3090.RMK_CD2 += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3090.RMK_CD2.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
        CEP.TRC(SCCGWA, CMCS3090.CHK_CTL);
        JIBS_tmp_str[0] = "" + CMCS3090.CHK_CTL;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 1).equalsIgnoreCase("1") 
            && (CMCS3090.PSW.trim().length() == 0 
            || CMCS3090.PSW.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PSW_ERR);
        }
        JIBS_tmp_str[0] = "" + CMCS3090.CHK_CTL;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 1).equalsIgnoreCase("2") 
            && ((CMCS3090.TRK2_DAT.trim().length() == 0 
            || CMCS3090.TRK2_DAT.charAt(0) == 0X00) 
            && (CMCS3090.TRK3_DAT.trim().length() == 0 
            || CMCS3090.TRK3_DAT.charAt(0) == 0X00))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRK_ERR);
        }
        JIBS_tmp_str[0] = "" + CMCS3090.CHK_CTL;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 1).equalsIgnoreCase("3") 
            && (CMCS3090.PSW.trim().length() == 0 
            || CMCS3090.PSW.charAt(0) == 0X00) 
            && ((CMCS3090.TRK2_DAT.trim().length() == 0 
            || CMCS3090.TRK2_DAT.charAt(0) == 0X00) 
            && (CMCS3090.TRK3_DAT.trim().length() == 0 
            || CMCS3090.TRK3_DAT.charAt(0) == 0X00))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRK_ERR);
        }
    }
    public void B300_RHLD_DEDUCT_HOLD_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B300_01_CHK_CARD_STS();
            if (pgmRtn) return;
            if (CMCS3090.TX_TYP == '1') {
                B300_03_URHLD_PROC();
                if (pgmRtn) return;
                B300_04_DD_STRAC_D_PROC();
                if (pgmRtn) return;
            } else if (CMCS3090.TX_TYP == '2') {
                B300_05_DD_STRAC_C_PROC();
                if (pgmRtn) return;
                B300_06_HOLD_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (CMCS3090.TX_TYP == '1') {
                B300_04_DD_STRAC_D_PROC();
                if (pgmRtn) return;
                B300_03_URHLD_PROC();
                if (pgmRtn) return;
            } else if (CMCS3090.TX_TYP == '2') {
                B300_06_HOLD_PROC();
                if (pgmRtn) return;
                B300_05_DD_STRAC_C_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_01_CHK_CARD_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '1';
        CMCSIQAC.I_CUS_AC = CMCS3090.CUS_AC;
        CMCSIQAC.I_CCY = CMCS3090.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3090.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            B300_CHK_CARD_STS();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B300_CHK_CARD_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3090.CUS_AC);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CMCS3090.CUS_AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        if (DCCUCINF.CARD_STS == 'C') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS_C_ERR);
        }
        if (DCCUCINF.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS1_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS2_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS14_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS8_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (CMCS3090.PSW.trim().length() > 0 
            && DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS9_ERR);
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DCCUCINF.CARD_NO;
        CICQACAC.DATA.CCY_ACAC = CMCS3090.CCY;
        CICQACAC.DATA.CR_FLG = CMCS3090.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = DCCUCINF.CARD_NO;
            DDCIQBAL.DATA.CCY = CMCS3090.CCY;
            DDCIQBAL.DATA.CCY_TYPE = CMCS3090.CCY_TYP;
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS);
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
            if (DDCIQBAL.DATA.CCY_STS != 'N') {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS8_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS9_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS16_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS17_ERR);
            }
        }
    }
    public void B300_03_URHLD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3090.RMK_CD);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = WS_HLD_NOTEMP;
        DCCURHLD.DATA.RMK = CMCS3090.NARATVE;
        DCCURHLD.DATA.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.TX_TYP_CD = CMCS3090.RMK_CD;
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCURHLD.DATA.TX_TYP_CD);
        if ((WS_CUS_AC.trim().length() > 0) 
            && (!WS_CUS_AC.equalsIgnoreCase(DCCURHLD.DATA.AC))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR);
        }
    }
    public void B300_04_DD_STRAC_D_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3090.RLT_AC);
        CEP.TRC(SCCGWA, CMCS3090.CHN_TYP);
        CEP.TRC(SCCGWA, CMCS3090.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3090.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3090.CUS_AC);
        CEP.TRC(SCCGWA, CMCS3090.CCY);
        CEP.TRC(SCCGWA, CMCS3090.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3090.PSW);
        CEP.TRC(SCCGWA, CMCS3090.TXN_AMT);
        CEP.TRC(SCCGWA, CMCS3090.INR_AC);
        CEP.TRC(SCCGWA, CMCS3090.OPP_ACNM);
        CEP.TRC(SCCGWA, CMCS3090.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3090.RMK_CD2);
        IBS.init(SCCGWA, DDCSTRAC);
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3090.CUS_AC;
        CMCSIQAC.I_CCY = CMCS3090.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3090.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_AC_BR = CMCSIQAC.OUT_INF.DD_INF.DD_ACO_OWN_BR;
        WS_AC_NM = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.FR_AC = CMCS3090.CUS_AC;
            DDCSTRAC.FR_BV_TYPE = '2';
        }
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.FR_CARD = CMCS3090.CUS_AC;
            DDCSTRAC.FR_BV_TYPE = '1';
        }
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '1';
        CMCSIQAC.I_CUS_AC = CMCS3090.INR_AC;
        CMCSIQAC.I_CCY = CMCS3090.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3090.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        if (CMCS3090.INR_AC == null) CMCS3090.INR_AC = "";
        JIBS_tmp_int = CMCS3090.INR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3090.INR_AC += " ";
        if (CMCS3090.INR_AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(CMCS3090.INR_AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.TO_BV_TYPE = '1';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.TO_BV_TYPE = '2';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("AI")) {
            DDCSTRAC.TO_BV_TYPE = '3';
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FRM_APP_ERR);
        }
        DDCSTRAC.FR_CCY = CMCS3090.CCY;
        DDCSTRAC.TO_CCY = CMCS3090.CCY;
        DDCSTRAC.FR_CCY_TYPE = CMCS3090.CCY_TYP;
        DDCSTRAC.PSWD = CMCS3090.PSW;
        DDCSTRAC.TRK2_DAT = CMCS3090.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = CMCS3090.TRK3_DAT;
        DDCSTRAC.FR_AMT = CMCS3090.TXN_AMT;
        DDCSTRAC.TO_AC = CMCS3090.INR_AC;
        DDCSTRAC.RLT_AC = CMCS3090.RLT_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3090.OPP_ACNM;
        if (CMCS3090.INR_AC == null) CMCS3090.INR_AC = "";
        JIBS_tmp_int = CMCS3090.INR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3090.INR_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3090.INR_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = BPCPQORG.CHN_NM;
        DDCSTRAC.CR_MMO = CMCS3090.RMK_CD;
        DDCSTRAC.DR_MMO = CMCS3090.RMK_CD2;
        DDCSTRAC.REMARKS = CMCS3090.MERCH_NM;
        DDCSTRAC.TX_RMK = CMCS3090.NARATVE;
        DDCSTRAC.CHK_LMT_FLG = '4';
        CEP.TRC(SCCGWA, DDCSTRAC.CHK_LMT_FLG);
        CEP.TRC(SCCGWA, DDCSTRAC.DR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK3_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_CNAME);
        CEP.TRC(SCCGWA, CMCS3090.CHK_CTL);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC_NAME);
        if (CMCS3090.CHK_CTL == '1') {
            DDCSTRAC.CHK_PSW = 'P';
        } else if (CMCS3090.CHK_CTL == '2') {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3090.CHK_CTL == '3') {
            DDCSTRAC.CHK_PSW = 'B';
        } else {
            DDCSTRAC.CHK_PSW_FLG = 'N';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B300_05_DD_STRAC_C_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3090.RLT_AC);
        CEP.TRC(SCCGWA, CMCS3090.CHN_TYP);
        CEP.TRC(SCCGWA, CMCS3090.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3090.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3090.CUS_AC);
        CEP.TRC(SCCGWA, CMCS3090.CCY);
        CEP.TRC(SCCGWA, CMCS3090.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3090.PSW);
        CEP.TRC(SCCGWA, CMCS3090.TXN_AMT);
        CEP.TRC(SCCGWA, CMCS3090.INR_AC);
        CEP.TRC(SCCGWA, CMCS3090.OPP_ACNM);
        CEP.TRC(SCCGWA, CMCS3090.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3090.RMK_CD2);
        CEP.TRC(SCCGWA, CMCS3090.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS3090.TRK3_DAT);
        IBS.init(SCCGWA, DDCSTRAC);
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3090.CUS_AC;
        CMCSIQAC.I_CCY = CMCS3090.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3090.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_AC_BR = CMCSIQAC.OUT_INF.DD_INF.DD_ACO_OWN_BR;
        WS_AC_NM = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.TO_AC = CMCS3090.CUS_AC;
            DDCSTRAC.TO_BV_TYPE = '2';
        }
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.TO_CARD = CMCS3090.CUS_AC;
            DDCSTRAC.TO_BV_TYPE = '1';
        }
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '1';
        CMCSIQAC.I_CUS_AC = CMCS3090.INR_AC;
        CMCSIQAC.I_CCY = CMCS3090.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3090.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        if (CMCS3090.INR_AC == null) CMCS3090.INR_AC = "";
        JIBS_tmp_int = CMCS3090.INR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3090.INR_AC += " ";
        if (CMCS3090.INR_AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(CMCS3090.INR_AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.FR_BV_TYPE = '1';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.FR_BV_TYPE = '2';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("AI")) {
            DDCSTRAC.FR_BV_TYPE = '3';
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FRM_APP_ERR);
        }
        DDCSTRAC.FR_CCY = CMCS3090.CCY;
        DDCSTRAC.TO_CCY = CMCS3090.CCY;
        DDCSTRAC.TO_CCY_TYPE = CMCS3090.CCY_TYP;
        DDCSTRAC.PSWD = CMCS3090.PSW;
        DDCSTRAC.TRK2_DAT = CMCS3090.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = CMCS3090.TRK3_DAT;
        DDCSTRAC.FR_AMT = CMCS3090.TXN_AMT;
        DDCSTRAC.FR_AC = CMCS3090.INR_AC;
        DDCSTRAC.RLT_AC = CMCS3090.RLT_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3090.OPP_ACNM;
        if (CMCS3090.INR_AC == null) CMCS3090.INR_AC = "";
        JIBS_tmp_int = CMCS3090.INR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3090.INR_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3090.INR_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = BPCPQORG.CHN_NM;
        DDCSTRAC.DR_MMO = CMCS3090.RMK_CD;
        DDCSTRAC.CR_MMO = CMCS3090.RMK_CD2;
        DDCSTRAC.REMARKS = CMCS3090.MERCH_NM;
        DDCSTRAC.TX_RMK = CMCS3090.NARATVE;
        DDCSTRAC.CHK_LMT_FLG = '4';
        CEP.TRC(SCCGWA, DDCSTRAC.CHK_LMT_FLG);
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.DR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC_NAME);
        if (CMCS3090.CHK_CTL == '1') {
            DDCSTRAC.CHK_PSW = 'P';
        } else if (CMCS3090.CHK_CTL == '2') {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3090.CHK_CTL == '3') {
            DDCSTRAC.CHK_PSW = 'B';
        } else {
            DDCSTRAC.CHK_PSW_FLG = 'N';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B300_06_HOLD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3090.CUS_AC);
        CEP.TRC(SCCGWA, CMCS3090.CHN_TYP);
        CEP.TRC(SCCGWA, CMCS3090.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3090.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3090.CUS_AC);
        CEP.TRC(SCCGWA, CMCS3090.CCY);
        CEP.TRC(SCCGWA, CMCS3090.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3090.CHK_CTL);
        CEP.TRC(SCCGWA, CMCS3090.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS3090.TRK3_DAT);
        CEP.TRC(SCCGWA, CMCS3090.PSW);
        CEP.TRC(SCCGWA, CMCS3090.TXN_AMT);
        CEP.TRC(SCCGWA, CMCS3090.INR_AC);
        CEP.TRC(SCCGWA, CMCS3090.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3090.OPP_ACNM);
        CEP.TRC(SCCGWA, CMCS3090.OPP_BKNM);
        CEP.TRC(SCCGWA, CMCS3090.NARATVE);
        CEP.TRC(SCCGWA, CMCS3090.MERCH_NM);
        CEP.TRC(SCCGWA, CMCS3090.HLD_AMT);
        DCCUHLD.DATA.HLD_NO = WS_HLD_NOTEMP;
        if (WS_CUS_AC.trim().length() > 0) {
            DCCUHLD.DATA.AC = WS_CUS_AC;
        } else {
            DCCUHLD.DATA.AC = CMCS3090.CUS_AC;
        }
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.PRE_SIC_FLG = 'Y';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.CCY = CMCS3090.CCY;
        DCCUHLD.DATA.CCY_TYP = CMCS3090.CCY_TYP;
        DCCUHLD.DATA.AMT = CMCS3090.TXN_AMT;
        if (CMCS3090.HLD_AMT != 0) {
            DCCUHLD.DATA.AMT = CMCS3090.HLD_AMT;
        }
        DCCUHLD.DATA.RMK = CMCS3090.NARATVE;
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUHLD.DATA.TX_TYP_CD = CMCS3090.RMK_CD;
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUHLD.DATA.TX_TYP_CD);
    }
    public void B400_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = CMCS3090.CUS_AC;
        DDCIQBAL.DATA.CCY = CMCS3090.CCY;
        DDCIQBAL.DATA.CCY_TYPE = CMCS3090.CCY_TYP;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCO3090.CCY = DDCIQBAL.DATA.CCY;
        CMCO3090.CCY_TYP = DDCIQBAL.DATA.CCY_TYPE;
        CMCO3090.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
        CMCO3090.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        IBS.init(SCCGWA, CMCO3090);
        CMCO3090.HLD_NO = WS_HLD_NOTEMP;
        CMCO3090.CUS_AC = CMCS3090.CUS_AC;
        CMCO3090.CCY = CMCS3090.CCY;
        CMCO3090.CCY_TYP = CMCS3090.CCY_TYP;
        CMCO3090.TAMT = CMCS3090.TXN_AMT;
        CMCO3090.AC_BR = WS_AC_BR;
        CMCO3090.AC_NM = WS_AC_NM;
        CMCO3090.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CMCO3090.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
        CMCO3090.TEL_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_TEL_NO;
        CEP.TRC(SCCGWA, CMCO3090.CUS_AC);
        CEP.TRC(SCCGWA, CMCO3090.TAMT);
        CEP.TRC(SCCGWA, CMCO3090.AC_BR);
        CEP.TRC(SCCGWA, CMCO3090.AC_NM);
        CEP.TRC(SCCGWA, CMCO3090.AC_BAL);
        CEP.TRC(SCCGWA, CMCO3090.AVA_BAL);
        CEP.TRC(SCCGWA, CMCO3090.TEL_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCO3090;
        SCCFMT.DATA_LEN = 380;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD, true);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL ", CICQACRL, true);
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF ", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
