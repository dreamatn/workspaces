package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUSBOX;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIQBAL;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CMZS1700 {
    boolean pgmRtn = false;
    String K_CNY = "156";
    String K_OUTPUT_FMT = "CM170";
    String WK_PGM_CIZQACRI = "CI-INQ-ACR-INF      ";
    String WK_PGM_CIZCUST = "CI-INQ-CUST";
    String WK_PGM_DCZUCINF = "DC-U-CARD-INF       ";
    String WK_PGM_BPZUSBOX = "BP-U-SUB-CBOX       ";
    String WK_PGM_DDZUDRAC = "DD-UNIT-DRAW-PROC   ";
    String WK_PGM_IBZDRAC = "IB-IBZDRAC          ";
    String WK_PGM_BPZUABOX = "BP-U-ADD-CBOX       ";
    String WK_PGM_DDZUCRAC = "DD-UNIT-DEP-PROC    ";
    String WK_PGM_IBZCRAC = "IB-IBZCRAC          ";
    String WK_PGM_AIZUUPIA = "AI-U-UPDATE-IA      ";
    String WK_PGM_DDZIQBAL = "DD-I-INQ-CCY-BAL";
    CMZS1700_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMZS1700_WS_TEMP_VARIABLE();
    CMZS1700_WS_CUS_INF[] WS_CUS_INF = new CMZS1700_WS_CUS_INF[2];
    CMZS1700_WS_CASH_INF WS_CASH_INF = new CMZS1700_WS_CASH_INF();
    String WS_TS = " ";
    short WS_ACNAME_FLG = 0;
    String WS_ACNAME_IN = " ";
    String WS_ACNAME_AC = " ";
    CMZS1700_WS_TSS WS_TSS = new CMZS1700_WS_TSS();
    char WS_DEAL_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    CICQACRI CICQACRI = new CICQACRI();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DCCUCINF DCCUCINF = new DCCUCINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    CICCUST CICCUST = new CICCUST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CMCO1700 CMCO1700 = new CMCO1700();
    AICPUITM AICPUITM = new AICPUITM();
    DDCUINVS DDCUINVS = new DDCUINVS();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CICQACRL CICQACRL = new CICQACRL();
    CMRDELAY CMRDELAY = new CMRDELAY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS1700 CMCS1700;
    public CMZS1700() {
        for (int i=0;i<2;i++) WS_CUS_INF[i] = new CMZS1700_WS_CUS_INF();
    }
    public void MP(SCCGWA SCCGWA, CMCS1700 CMCS1700) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS1700 = CMCS1700;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS1700 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, CMCS1700.REMARK);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        B100_INPUT_CHECK_PROC();
        if (pgmRtn) return;
        B300_CUS_AC_CHECK_PROC();
        if (pgmRtn) return;
        B600_AC_UPDATE_PROC();
        if (pgmRtn) return;
        B700_INQ_ACO_REC_PROC();
        if (pgmRtn) return;
        B900_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        if ((CMCS1700.AC_DATA[1-1].DC_FLG != 'D' 
            && CMCS1700.AC_DATA[1-1].DC_FLG != 'C' 
            && CMCS1700.AC_DATA[1-1].DC_FLG != ' ') 
            || (CMCS1700.AC_DATA[2-1].DC_FLG != 'D' 
            && CMCS1700.AC_DATA[2-1].DC_FLG != 'C' 
            && CMCS1700.AC_DATA[2-1].DC_FLG != ' ')) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DC_FLG_ERR);
        }
    }
    public void B300_CUS_AC_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 2; WS_TEMP_VARIABLE.WS_I += 1) {
            if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != ' ' 
                && CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG != 0X00) {
                WS_DEAL_FLG = 'Y';
                if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT > 0) {
                    CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT = 0 - CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_I);
                CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
                CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRAC_TYP);
                if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRAC_TYP == 'X') {
                    B310_CHECK_CUS_AC_INPUT_INF();
                    if (pgmRtn) return;
                    B320_INQ_CUS_INF();
                    if (pgmRtn) return;
                    B330_INQ_CUS_AC_INF();
                    if (pgmRtn) return;
                    B340_CHECK_CUS_AC_INF();
                    if (pgmRtn) return;
                }
                if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRAC_TYP == 'C') {
                    WS_CASH_INF.WS_CASH_ACO_FLG = 'Y';
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "CS";
                    WS_CASH_INF.WS_CASH_DC_FLG = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG;
                    WS_CASH_INF.WS_CASH_TXN_AMT = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT;
                    WS_CASH_INF.WS_CASH_CCY = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY;
                    WS_CASH_INF.WS_CASH_OPP_AC = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_AC;
                    WS_CASH_INF.WS_CASH_OPP_ANM = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].OPP_ANM;
                }
                if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TRAC_TYP == 'I') {
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AGR_NO = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC;
                    WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP = "IT";
                }
            }
        }
        if (WS_DEAL_FLG != 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NOT_ALLOW_EMPTY_TRAD);
        }
    }
    public void B310_CHECK_CUS_AC_INPUT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].DC_FLG);
        CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC);
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC.trim().length() == 0 
            || CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_ERR);
        }
        CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY);
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY.trim().length() == 0 
            || CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
        }
        CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL);
        CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM);
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(0, 1).equalsIgnoreCase("1") 
            && (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM.trim().length() == 0 
            || CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
        }
        CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT);
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].TXN_AMT == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
        }
    }
    public void B320_INQ_CUS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICCUST);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC;
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
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_SMS_FLG = CICQACRI.O_DATA.O_SMS_FLG;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
        WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OPN_BR = CICQACRI.O_DATA.O_OPN_BR;
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
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_CI_NO);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP_OLD);
        CEP.TRC(SCCGWA, WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM);
        if (!WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")) {
            if ((!CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY.equalsIgnoreCase(K_CNY)) 
                && (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == ' ' 
                || CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CCY_TYP == 0X00)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
            }
            CEP.TRC(SCCGWA, CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD);
            if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.trim().length() > 0 
                && CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.charAt(0) != 0X00) {
                IBS.init(SCCGWA, BPCPRMR);
                IBS.init(SCCGWA, BPCPARMC);
                BPCPRMR.FUNC = ' ';
                BPCPARMC.KEY.TYP = "PARMC";
                if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
                JIBS_tmp_int = BPCPARMC.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
                BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
                if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD == null) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD = "";
                JIBS_tmp_int = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD += " ";
                if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
                JIBS_tmp_int = BPCPARMC.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
                BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].RMK_CD.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 4 - 1);
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
            CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP);
        } else {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM = AICPQMIB.OUTPUT_DATA.CHS_NM;
            if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC == null) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC = "";
            JIBS_tmp_int = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC += " ";
            if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC.substring(0, 6).trim().length() == 0) WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OWN_BR = 0;
            else WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_OWN_BR = Integer.parseInt(CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC.substring(0, 6));
        }
    }
    public void B330_INQ_CUS_AC_INF() throws IOException,SQLException,Exception {
        if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DC")) {
            B331_INQ_CARD_INF();
            if (pgmRtn) return;
        } else if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DD")) {
            B332_INQ_DDAC_INF();
            if (pgmRtn) return;
        } else if (WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("GD")
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TD")
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("IB")
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("CS")
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("AI")
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("DG")
            || WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_FRM_APP.equalsIgnoreCase("TG")) {
        }
    }
    public void B331_INQ_CARD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC;
        S_CALL_DCZUCINF();
        if (pgmRtn) return;
    }
    public void B332_INQ_DDAC_INF() throws IOException,SQLException,Exception {
    }
    public void B340_CHECK_CUS_AC_INF() throws IOException,SQLException,Exception {
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL == null) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL = "";
        JIBS_tmp_int = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL += " ";
        if (CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].CHK_CTL.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ACNAME_IN = CMCS1700.AC_DATA[WS_TEMP_VARIABLE.WS_I-1].AC_NM;
            WS_ACNAME_AC = WS_CUS_INF[WS_TEMP_VARIABLE.WS_I-1].WS_CUS_AC_CNM;
