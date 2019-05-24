package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS1500 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_AC_TYP1 = " ";
    int WS_I = 0;
    short WS_R = 0;
    String WS_ID_TYP1 = " ";
    String WS_ID_NO1 = " ";
    String WS_AC_NM1 = " ";
    int WS_AC_BR1 = 0;
    String WS_AC_BR_NM1 = " ";
    String WS_ID_TYP2 = " ";
    String WS_ID_NO2 = " ";
    String WS_AC_NM2 = " ";
    int WS_AC_BR2 = 0;
    String WS_AC_BR_NM2 = " ";
    String WS_CARD_NO = " ";
    String WS_ACAC_NO = " ";
    double WS_CURR_BAL = 0;
    double WS_ACL_BAL = 0;
    double WS_CURR_BAL2 = 0;
    double WS_ACL_BAL2 = 0;
    String WS_AC_FRM_APP1 = " ";
    String WS_AC_FRM_APP2 = " ";
    String WS_RMK_CDE = " ";
    char WS_DC_TYP = ' ';
    double WS_FEE_AMT = 0;
    String WS_CI_NO = " ";
    char WS_CITY_FLG = ' ';
    char WS_FEE_FLG = ' ';
    String WS_CHNL_TYPE = " ";
    short WS_ACNAME_CHKFLG = 0;
    String WS_BAS_PROD_CD = " ";
    double WS_AMT = 0;
    double WS_FEE = 0;
    String WS_ACO_AC1 = " ";
    int WS_OPN_BR1 = 0;
    double WS_CUR_BAL1 = 0;
    double WS_AVL_BAL1 = 0;
    String WS_CI_NO1 = " ";
    String WS_ACO_AC2 = " ";
    int WS_OPN_BR2 = 0;
    double WS_CUR_BAL2 = 0;
    double WS_AVL_BAL2 = 0;
    String WS_CI_NO2 = " ";
    char WS_DELAY_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPACTY BPCPACTY = new BPCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCUINQV DDCUINQV = new DDCUINQV();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CMCF150 CMCF150 = new CMCF150();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCTCFEE BPCTCFEE = new BPCTCFEE();
    CMCSELAY CMCSELAY = new CMCSELAY();
    CMCS8840 CMCS8840 = new CMCS8840();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    DCCSARQC DCCSARQC = new DCCSARQC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS1500 CMCS1500;
    public void MP(SCCGWA SCCGWA, CMCS1500 CMCS1500) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS1500 = CMCS1500;
        CEP.TRC(SCCGWA);
        A100_INIT_PROC();
        if (pgmRtn) return;
        A200_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS1500 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A100_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF150);
        WS_DELAY_FLG = 'N';
        CEP.TRC(SCCGWA, CMCS1500.AC_TYP1);
        CEP.TRC(SCCGWA, CMCS1500.AC1);
        CEP.TRC(SCCGWA, CMCS1500.AC_SEQ1);
        CEP.TRC(SCCGWA, CMCS1500.CCY_NO1);
        CEP.TRC(SCCGWA, CMCS1500.CCY_TYP1);
        CEP.TRC(SCCGWA, CMCS1500.ACO_AC1);
        CEP.TRC(SCCGWA, CMCS1500.PSW_FLG);
        CEP.TRC(SCCGWA, CMCS1500.PSW);
        CEP.TRC(SCCGWA, CMCS1500.BV_TYP1);
        CEP.TRC(SCCGWA, CMCS1500.BV_CD1);
        CEP.TRC(SCCGWA, CMCS1500.TRK_FLG);
        CEP.TRC(SCCGWA, CMCS1500.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS1500.TRK3_DAT);
        CEP.TRC(SCCGWA, CMCS1500.NM_FLG1);
        CEP.TRC(SCCGWA, CMCS1500.CI_NM1);
        CEP.TRC(SCCGWA, CMCS1500.ID_FLG1);
        CEP.TRC(SCCGWA, CMCS1500.ID_TYP1);
        CEP.TRC(SCCGWA, CMCS1500.ID_NO1);
        CEP.TRC(SCCGWA, CMCS1500.RMK1);
        CEP.TRC(SCCGWA, CMCS1500.AC_TYP2);
        CEP.TRC(SCCGWA, CMCS1500.AC2);
        CEP.TRC(SCCGWA, CMCS1500.AC_SEQ2);
        CEP.TRC(SCCGWA, CMCS1500.ACO_AC2);
        CEP.TRC(SCCGWA, CMCS1500.BV_TYP2);
        CEP.TRC(SCCGWA, CMCS1500.BV_NO2);
        CEP.TRC(SCCGWA, CMCS1500.NM_FLG2);
        CEP.TRC(SCCGWA, CMCS1500.CI_NM2);
        CEP.TRC(SCCGWA, CMCS1500.ID_FLG2);
        CEP.TRC(SCCGWA, CMCS1500.ID_TYP2);
        CEP.TRC(SCCGWA, CMCS1500.ID_NO2);
        CEP.TRC(SCCGWA, CMCS1500.RMK2);
        CEP.TRC(SCCGWA, CMCS1500.CCY_NO2);
        CEP.TRC(SCCGWA, CMCS1500.CCY_TYP2);
        CEP.TRC(SCCGWA, CMCS1500.AMT);
        CEP.TRC(SCCGWA, CMCS1500.SMNM_FLG);
        CEP.TRC(SCCGWA, CMCS1500.FC_FLG);
        CEP.TRC(SCCGWA, CMCS1500.VAL_DT);
        CEP.TRC(SCCGWA, CMCS1500.DESC_64);
        CEP.TRC(SCCGWA, CMCS1500.RMK_100);
    }
    public void A200_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK();
        if (pgmRtn) return;
        B200_INPUT_CUS_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CMCS1500.ARQC_DATA);
        if (WS_AC_FRM_APP1.equalsIgnoreCase("DC") 
            && WS_AC_FRM_APP2.equalsIgnoreCase("DC") 
            && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301") 
            && (CMCS1500.ARQC_DATA.trim().length() > 0 
            && CMCS1500.ARQC_DATA.charAt(0) != 0X00)) {
            B250_INPUT_ARQC_CHECK();
            if (pgmRtn) return;
        }
        B300_TRAN_MAIN_PROC();
        if (pgmRtn) return;
        B700_FEE_PROC();
        if (pgmRtn) return;
        if (WS_DELAY_FLG == 'Y') {
            B800_TRF_DELAY_PROC();
            if (pgmRtn) return;
        }
        B900_OUTPUT_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMCS1500.AC1.trim().length() == 0 
            || CMCS1500.AC1.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR, "AC1 IS SPACE ");
        }
        if (CMCS1500.AC2.trim().length() == 0 
            || CMCS1500.AC2.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR, "AC2 IS SAPCE ");
        }
        if ((CMCS1500.AC_TYP1 == ' ' 
            || CMCS1500.AC_TYP1 == 0X00) 
            || (CMCS1500.AC_TYP2 == ' ' 
            || CMCS1500.AC_TYP2 == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        if ((CMCS1500.CCY_NO1.trim().length() > 0 
            && CMCS1500.CCY_NO1.charAt(0) != 0X00) 
            && (CMCS1500.CCY_NO2.trim().length() > 0 
            && CMCS1500.CCY_NO2.charAt(0) != 0X00) 
            && (!CMCS1500.CCY_NO1.equalsIgnoreCase(CMCS1500.CCY_NO2))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
        }
        if (CMCS1500.NM_FLG1 == 'Y' 
            && (CMCS1500.CI_NM1.trim().length() == 0 
            || CMCS1500.CI_NM1.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR, "CI-NM1 IS SPACE");
        }
        if (CMCS1500.NM_FLG2 == 'Y' 
            && (CMCS1500.CI_NM2.trim().length() == 0 
            || CMCS1500.CI_NM2.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR, "CI-NM2 IS SPACE");
        }
        if (CMCS1500.PSW_FLG == 'Y' 
            && (CMCS1500.PSW.trim().length() == 0 
            || CMCS1500.PSW.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PSW_ERR);
        }
        if (CMCS1500.ID_FLG1 == 'Y' 
            && ((CMCS1500.ID_NO1.trim().length() == 0 
            || CMCS1500.ID_NO1.charAt(0) == 0X00) 
            || (CMCS1500.ID_TYP1.trim().length() == 0))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR, "ID1 IS SPACE");
        }
        if (CMCS1500.ID_FLG2 == 'Y' 
            && ((CMCS1500.ID_NO2.trim().length() == 0 
            || CMCS1500.ID_NO2.charAt(0) == 0X00) 
            || (CMCS1500.ID_TYP2.trim().length() == 0))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR, "ID2 IS SPACE");
        }
        if (CMCS1500.RMK1.trim().length() == 0 
            || CMCS1500.RMK1.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_ERR);
        }
        if (CMCS1500.RMK2.trim().length() == 0 
            || CMCS1500.RMK2.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_ERR);
        }
        if (CMCS1500.RMK1 == null) CMCS1500.RMK1 = "";
        JIBS_tmp_int = CMCS1500.RMK1.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS1500.RMK1 += " ";
        WS_RMK_CDE = CMCS1500.RMK1.substring(0, 5);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (CMCS1500.RMK2 == null) CMCS1500.RMK2 = "";
        JIBS_tmp_int = CMCS1500.RMK2.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS1500.RMK2 += " ";
        WS_RMK_CDE = CMCS1500.RMK2.substring(0, 5);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if ((CMCS1500.CCY_NO1.trim().length() == 0 
            || CMCS1500.CCY_NO1.charAt(0) == 0X00) 
            && (CMCS1500.CCY_NO2.trim().length() > 0 
            && CMCS1500.CCY_NO2.charAt(0) != 0X00)) {
            CMCS1500.CCY_NO1 = CMCS1500.CCY_NO2;
        }
        if ((CMCS1500.CCY_NO2.trim().length() == 0 
            || CMCS1500.CCY_NO2.charAt(0) == 0X00) 
            && (CMCS1500.CCY_NO1.trim().length() > 0 
            && CMCS1500.CCY_NO1.charAt(0) != 0X00)) {
            CMCS1500.CCY_NO2 = CMCS1500.CCY_NO1;
        }
        if (CMCS1500.CCY_NO1.trim().length() == 0 
            || CMCS1500.CCY_NO1.charAt(0) == 0X00) {
            CMCS1500.CCY_NO1 = "156";
        }
        if (CMCS1500.CCY_NO2.trim().length() == 0 
            || CMCS1500.CCY_NO2.charAt(0) == 0X00) {
            CMCS1500.CCY_NO2 = "156";
        }
    }
    public void B200_INPUT_CUS_CHECK() throws IOException,SQLException,Exception {
        B210_INQ_CUS_PROC();
        if (pgmRtn) return;
        B220_CHK_CI_INF();
        if (pgmRtn) return;
    }
    public void B210_INQ_CUS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        IBS.init(SCCGWA, BPCPQORG);
        CMCSIQAC.I_FUNC = '2';
        CMCSIQAC.I_CUS_AC = CMCS1500.AC1;
        CMCSIQAC.I_CCY = CMCS1500.CCY_NO1;
        CMCSIQAC.I_CCY_TYP = CMCS1500.CCY_TYP1;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_ID_TYP1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_TYP;
        WS_ID_NO1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO;
        WS_AC_NM1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        WS_AC_BR1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR;
        WS_AC_FRM_APP1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP;
        WS_CI_NO1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_NO;
        WS_BAS_PROD_CD = CMCSIQAC.OUT_INF.BAS_INF.BAS_PROD_CD;
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_ADD_INF.BAS_DWK_FLG == '1') {
            WS_DC_TYP = 'N';
        }
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR);
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_NO);
        BPCPQORG.BR = WS_AC_BR1;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_AC_BR_NM1 = BPCPQORG.CHN_NM;
        IBS.init(SCCGWA, CMCSIQAC);
        IBS.init(SCCGWA, BPCPQORG);
        CMCSIQAC.I_FUNC = '2';
        CMCSIQAC.I_CUS_AC = CMCS1500.AC2;
        CMCSIQAC.I_CCY = CMCS1500.CCY_NO1;
        CMCSIQAC.I_CCY_TYP = CMCS1500.CCY_TYP1;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_ID_TYP2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_TYP;
        WS_ID_NO2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO;
        WS_AC_NM2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        WS_AC_BR2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR;
        WS_AC_FRM_APP2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP;
        WS_CI_NO2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_NO;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR);
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_NO);
        BPCPQORG.BR = WS_AC_BR2;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_AC_BR_NM2 = BPCPQORG.CHN_NM;
    }
    public void B220_CHK_CI_INF() throws IOException,SQLException,Exception {
        WS_ACNAME_CHKFLG = 0;
        if (CMCS1500.NM_FLG1 == 'Y') {
            WS_ACNAME_CHKFLG = 1;
        } else {
            if (CMCS1500.CI_NM1.trim().length() > 0) {
                if (!WS_AC_FRM_APP1.equalsIgnoreCase("AI")) {
                    R000_CHK_CHNL();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_ACNAME_CHKFLG);
        if (WS_ACNAME_CHKFLG == 1 
            && !CMCS1500.CI_NM1.equalsIgnoreCase(WS_AC_NM1)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM1_ERR, CMCS1500.CI_NM1);
        }
        WS_ACNAME_CHKFLG = 0;
        if (CMCS1500.NM_FLG2 == 'Y') {
            WS_ACNAME_CHKFLG = 1;
        } else {
            if (CMCS1500.CI_NM2.trim().length() > 0) {
                if (!WS_AC_FRM_APP2.equalsIgnoreCase("AI")) {
                    R000_CHK_CHNL();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_ACNAME_CHKFLG);
        if (WS_ACNAME_CHKFLG == 1 
            && !CMCS1500.CI_NM2.equalsIgnoreCase(WS_AC_NM2)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM2_ERR, CMCS1500.CI_NM2);
        }
        CEP.TRC(SCCGWA, CMCS1500.ID_NO1);
        CEP.TRC(SCCGWA, CMCS1500.ID_TYP1);
        CEP.TRC(SCCGWA, WS_ID_NO1);
        CEP.TRC(SCCGWA, WS_ID_TYP1);
        CEP.TRC(SCCGWA, CMCS1500.ID_NO2);
        CEP.TRC(SCCGWA, CMCS1500.ID_TYP2);
        CEP.TRC(SCCGWA, WS_ID_NO2);
        CEP.TRC(SCCGWA, WS_ID_TYP2);
        if (CMCS1500.ID_FLG1 == 'Y' 
            && (!CMCS1500.ID_NO1.equalsIgnoreCase(WS_ID_NO1) 
            || !CMCS1500.ID_TYP1.equalsIgnoreCase(WS_ID_TYP1))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID1_ERR);
        }
        if (CMCS1500.ID_FLG2 == 'Y' 
            && (!CMCS1500.ID_NO2.equalsIgnoreCase(WS_ID_NO2) 
            || !CMCS1500.ID_TYP2.equalsIgnoreCase(WS_ID_TYP2))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID2_ERR);
        }
        if (CMCS1500.SMNM_FLG == 'Y' 
            && (!WS_AC_NM1.equalsIgnoreCase(WS_AC_NM2))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR);
        }
        CEP.TRC(SCCGWA, WS_CI_NO1);
        CEP.TRC(SCCGWA, WS_CI_NO2);
        WS_CHNL_TYPE = SCCGWA.COMM_AREA.CHNL;
        if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2) 
            && (WS_CHNL_TYPE.equalsIgnoreCase("10301") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10302") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10303") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10304") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10305") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10306") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10307") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10308") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10309") 
            || WS_CHNL_TYPE.equalsIgnoreCase("10311") 
            || WS_CHNL_TYPE.equalsIgnoreCase("30201")) 
            && WS_DC_TYP != 'N') {
            WS_DELAY_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_DELAY_FLG);
    }
    public void B250_INPUT_ARQC_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS1500.AC1);
        CEP.TRC(SCCGWA, CMCS1500.CARD_SEQ);
        CEP.TRC(SCCGWA, CMCS1500.ARQC);
        CEP.TRC(SCCGWA, CMCS1500.ARQC_DATA);
        CEP.TRC(SCCGWA, CMCS1500.ISSUE_DATA);
        CEP.TRC(SCCGWA, CMCS1500.VERIFY_RLT);
        IBS.init(SCCGWA, DCCSARQC);
        DCCSARQC.CARD_NO = CMCS1500.AC1;
        DCCSARQC.CARD_SEQ = CMCS1500.CARD_SEQ;
        DCCSARQC.CARD_ARQC = CMCS1500.ARQC;
        DCCSARQC.CARD_ARQC_DATA = CMCS1500.ARQC_DATA;
        DCCSARQC.ISSUE_DATA = CMCS1500.ISSUE_DATA;
        DCCSARQC.VERIFY_RLT = CMCS1500.VERIFY_RLT;
        S000_CALL_DCZSARQC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARPC);
    }
    public void B300_TRAN_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_DELAY_FLG == 'Y') {
            B400_YCZZ_PRE_CHECK_PROC();
            if (pgmRtn) return;
        } else {
            B310_DR_PROC();
            if (pgmRtn) return;
            B320_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B310_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        if (CMCS1500.PSW_FLG == 'Y' 
            && CMCS1500.TRK_FLG != 'Y') {
            DDCUDRAC.CHK_PSW = 'P';
        }
        if (CMCS1500.TRK_FLG == 'Y' 
            && CMCS1500.PSW_FLG != 'Y') {
            DDCUDRAC.CHK_PSW = 'T';
        }
        if (CMCS1500.PSW_FLG == 'Y' 
            && CMCS1500.TRK_FLG == 'Y') {
            DDCUDRAC.CHK_PSW = 'B';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301") 
            && CMCS1500.TRK2_DAT.trim().length() == 0) {
            CMCS1500.TRK2_DAT = "0000000000000000000000000000000000000000";
        }
        CEP.TRC(SCCGWA, CMCS1500.TRK2_DAT);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CCY_TYPE = CMCS1500.CCY_TYP1;
        DDCUDRAC.AC = CMCS1500.AC1;
        DDCUDRAC.CCY = CMCS1500.CCY_NO1;
        DDCUDRAC.TX_AMT = CMCS1500.AMT;
        DDCUDRAC.TX_MMO = CMCS1500.RMK1;
        DDCUDRAC.PSWD = CMCS1500.PSW;
        DDCUDRAC.TRK_DATE2 = CMCS1500.TRK2_DAT;
        DDCUDRAC.TRK_DATE3 = CMCS1500.TRK3_DAT;
        if (WS_AC_FRM_APP1.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = CMCS1500.AC1;
        }
        DDCUDRAC.OTHER_AC = CMCS1500.AC2;
        DDCUDRAC.OTHER_CCY = CMCS1500.CCY_NO2;
        DDCUDRAC.OTHER_AMT = CMCS1500.AMT;
        DDCUDRAC.OTHER_AC_NM = WS_AC_NM2;
        DDCUDRAC.OTHER_BR = "" + WS_AC_BR2;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = WS_AC_BR_NM2;
        DDCUDRAC.RLT_AC = CMCS1500.AC2;
        DDCUDRAC.RLT_CCY = CMCS1500.CCY_NO2;
        DDCUDRAC.RLT_AC_NAME = WS_AC_NM2;
        DDCUDRAC.RLT_BANK = "" + WS_AC_BR2;
        JIBS_tmp_int = DDCUDRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.RLT_BANK = "0" + DDCUDRAC.RLT_BANK;
        DDCUDRAC.RLT_BK_NM = WS_AC_BR_NM2;
        DDCUDRAC.NARRATIVE = CMCS1500.DESC_64;
        DDCUDRAC.REMARKS = CMCS1500.RMK_100;
        if (WS_AC_FRM_APP2.equalsIgnoreCase("DC")) {
            DDCUDRAC.OTH_TX_TOOL = CMCS1500.AC2;
            DDCUDRAC.RLT_TX_TOOL = CMCS1500.AC2;
        }
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B320_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.CCY_TYPE = '1';
        if (CMCS1500.CCY_TYP1 == '2') {
            DDCUCRAC.CCY_TYPE = '2';
        }
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = CMCS1500.AC2;
        DDCUCRAC.CCY_TYPE = CMCS1500.CCY_TYP2;
        DDCUCRAC.CCY = CMCS1500.CCY_NO2;
        DDCUCRAC.TX_AMT = CMCS1500.AMT;
        DDCUCRAC.TX_MMO = CMCS1500.RMK2;
        if (WS_AC_FRM_APP2.equalsIgnoreCase("DC")) {
            DDCUCRAC.CARD_NO = CMCS1500.AC2;
        }
        DDCUCRAC.OTHER_AC = CMCS1500.AC1;
        DDCUCRAC.OTHER_CCY = CMCS1500.CCY_NO1;
        DDCUCRAC.OTHER_AMT = CMCS1500.AMT;
        DDCUCRAC.OTHER_AC_NM = WS_AC_NM1;
        DDCUCRAC.OTHER_BR = "" + WS_AC_BR1;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = WS_AC_BR_NM1;
        DDCUCRAC.RLT_AC = CMCS1500.AC1;
        DDCUCRAC.RLT_CCY = CMCS1500.CCY_NO1;
        DDCUCRAC.RLT_AC_NAME = WS_AC_NM1;
        DDCUCRAC.RLT_BK_NM = WS_AC_BR_NM1;
        DDCUCRAC.RLT_BANK = "" + WS_AC_BR1;
        JIBS_tmp_int = DDCUCRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.RLT_BANK = "0" + DDCUCRAC.RLT_BANK;
        DDCUCRAC.NARRATIVE = CMCS1500.DESC_64;
        DDCUCRAC.REMARKS = CMCS1500.RMK_100;
        if (WS_AC_FRM_APP1.equalsIgnoreCase("DC")) {
            DDCUCRAC.OTH_TX_TOOL = CMCS1500.AC1;
            DDCUCRAC.RLT_TX_TOOL = CMCS1500.AC1;
        }
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B400_YCZZ_PRE_CHECK_PROC() throws IOException,SQLException,Exception {
        if (WS_AC_FRM_APP1.equalsIgnoreCase("DC")) {
            B410_YCZZ_PRE_CHECK_DC_PROC();
            if (pgmRtn) return;
        } else if (WS_AC_FRM_APP1.equalsIgnoreCase("DD")) {
            B420_YCZZ_PRE_CHECK_DD_PROC();
            if (pgmRtn) return;
        } else {
        }
        if (WS_AC_FRM_APP2.equalsIgnoreCase("DC")) {
            R_CHK_CARD2_LIMIT();
            if (pgmRtn) return;
        }
    }
    public void B410_YCZZ_PRE_CHECK_DC_PROC() throws IOException,SQLException,Exception {
        if (CMCS1500.PSW_FLG == 'Y' 
            || CMCS1500.TRK_FLG == 'Y') {
            R_CHK_DC_PSWD();
            if (pgmRtn) return;
        }
        R_CHK_CARD_LIMIT();
        if (pgmRtn) return;
    }
    public void B420_YCZZ_PRE_CHECK_DD_PROC() throws IOException,SQLException,Exception {
        if (CMCS1500.PSW_FLG == 'Y') {
            R_CHK_DD_PSWD();
            if (pgmRtn) return;
        }
    }
    public void B700_FEE_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 3 
            && (CMCS1500.FEE_DATA[WS_I-1].FEE_CD.trim().length() != 0 
            && CMCS1500.FEE_DATA[WS_I-1].FEE_CD.charAt(0) != 0X00); WS_I += 1) {
            if (CMCS1500.FEE_DATA[WS_I-1].FEE_AMT == 0) {
                B710_FEE_CAL_PROC();
                if (pgmRtn) return;
            }
            WS_FEE_AMT += CMCS1500.FEE_DATA[WS_I-1].FEE_AMT;
            if (WS_DELAY_FLG == 'N') {
                B720_FEE_CHARGE_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CMCS1500.FEE_DATA[1-1].FEE_CD);
        CEP.TRC(SCCGWA, CMCS1500.FEE_DATA[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, CMCS1500.FEE_DATA[1-1].FEE_AC);
        CEP.TRC(SCCGWA, WS_AC_FRM_APP1);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (WS_AC_FRM_APP1.equalsIgnoreCase("DC") 
            && WS_AC_FRM_APP2.equalsIgnoreCase("DC") 
            && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301")) {
            for (WS_I = 1; WS_I <= 3 
                && (CMCS1500.FEE_DATA[WS_I-1].FEE_CD.trim().length() <= 0 
                || CMCS1500.FEE_DATA[WS_I-1].FEE_CD.charAt(0) == 0X00); WS_I += 1) {
                B730_SET_FEE_INFO();
                if (pgmRtn) return;
                B740_FEE_CAL_ROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B730_SET_FEE_INFO() throws IOException,SQLException,Exception {
        R000_CHK_CITY_FLG_BY_AREA_CD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS1500.AC1;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS1500.AC1;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS1500.CCY_NO1;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B740_FEE_CAL_ROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG);
        CEP.TRC(SCCGWA, WS_CITY_FLG);
        if (WS_CITY_FLG == '0'
            || WS_CITY_FLG == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = WS_CITY_FLG;
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "02";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG);
        BPCTCALF.INPUT_AREA.TX_AC = CMCS1500.AC1;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        BPCTCALF.INPUT_AREA.TX_CCY = CMCS1500.CCY_NO1;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = CMCS1500.AMT;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = WS_BAS_PROD_CD;
        CEP.TRC(SCCGWA, WS_BAS_PROD_CD);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.OTHER_AC);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B710_FEE_CAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCFEE);
        BPCTCFEE.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTCFEE.INPUT_AREA.PRD_CODE = " ";
        BPCTCFEE.INPUT_AREA.CFEE_CCY = CMCS1500.CCY_NO1;
        BPCTCFEE.INPUT_AREA.CHG_CCY = CMCS1500.CCY_NO1;
        BPCTCFEE.INPUT_AREA.CI_NO = WS_CI_NO;
        BPCTCFEE.INPUT_AREA.FEE_CODE = CMCS1500.FEE_DATA[WS_I-1].FEE_CD;
        BPCTCFEE.INPUT_AREA.AC_NO = CMCS1500.FEE_DATA[WS_I-1].FEE_AC;
        BPCTCFEE.INPUT_AREA.ACC_CNT = 1;
        BPCTCFEE.INPUT_AREA.FEE_AMT = CMCS1500.AMT;
        S000_CALL_BPZSCFEE();
        if (pgmRtn) return;
        CMCS1500.FEE_DATA[WS_I-1].FEE_AMT = BPCTCFEE.OUTPUT_AREA.CHG_AMT;
        CEP.TRC(SCCGWA, BPCTCFEE.OUTPUT_AREA.CHG_AMT);
    }
    public void B720_FEE_CHARGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '1';
        CMCSIQAC.I_CUS_AC = CMCS1500.FEE_DATA[WS_I-1].FEE_AC;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS1500.FEE_DATA[WS_I-1].FEE_AC;
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS1500.FEE_DATA[WS_I-1].FEE_AC;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS1500.CCY_NO1;
        BPCFFTXI.TX_DATA.CCY_TYPE = CMCS1500.CCY_TYP1;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = CMCS1500.FEE_DATA[WS_I-1].FEE_AC;
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '4';
        } else {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '0';
        }
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = CMCS1500.CCY_NO1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = CMCS1500.CCY_NO1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = CMCS1500.FEE_DATA[WS_I-1].FEE_CD;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = CMCS1500.FEE_DATA[WS_I-1].FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = CMCS1500.FEE_DATA[WS_I-1].FEE_AMT - CMCS1500.FEE_DATA[WS_I-1].FEEC_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT;
        BPCFFCON.FEE_INFO.PROD_CODE1 = WS_BAS_PROD_CD;
        CEP.TRC(SCCGWA, WS_BAS_PROD_CD);
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B800_TRF_DELAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        if (DCCUHLD.DATA.HLD_NO == null) DCCUHLD.DATA.HLD_NO = "";
        JIBS_tmp_int = DCCUHLD.DATA.HLD_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCCUHLD.DATA.HLD_NO += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUHLD.DATA.HLD_NO = JIBS_tmp_str[0] + DCCUHLD.DATA.HLD_NO.substring(8);
        if (DCCUHLD.DATA.HLD_NO == null) DCCUHLD.DATA.HLD_NO = "";
        JIBS_tmp_int = DCCUHLD.DATA.HLD_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCCUHLD.DATA.HLD_NO += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUHLD.DATA.HLD_NO = DCCUHLD.DATA.HLD_NO.substring(0, 9 - 1) + JIBS_tmp_str[0] + DCCUHLD.DATA.HLD_NO.substring(9 + 12 - 1);
        DCCUHLD.DATA.AC = CMCS1500.AC1;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.HLD_CLS = '8';
        DCCUHLD.DATA.CCY = CMCS1500.CCY_NO1;
        DCCUHLD.DATA.CCY_TYP = CMCS1500.CCY_TYP1;
        DCCUHLD.DATA.AMT = CMCS1500.AMT;
        DCCUHLD.DATA.AMT += WS_FEE_AMT;
        DCCUHLD.DATA.RSN = "TRF DELAY";
        DCCUHLD.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCUHLD.DATA.RMK = CMCS1500.RMK_100;
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUHLD.DATA.TX_TYP_CD = CMCS1500.RMK1;
        DCCUHLD.DATA.PSWD = CMCS1500.PSW;
        DCCUHLD.DATA.TRK_DAT2 = CMCS1500.TRK2_DAT;
        DCCUHLD.DATA.TRK_DAT3 = CMCS1500.TRK3_DAT;
        DCCUHLD.DATA.CHK_OPT = '4';
        if (CMCS1500.PSW_FLG == 'Y') {
            DCCUHLD.DATA.CHK_OPT = '1';
        }
        if (CMCS1500.TRK_FLG == 'Y') {
            DCCUHLD.DATA.CHK_OPT = '2';
        }
        if (CMCS1500.PSW_FLG == 'Y' 
            && CMCS1500.TRK_FLG == 'Y') {
            DCCUHLD.DATA.CHK_OPT = '3';
        }
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CMCSELAY);
        IBS.init(SCCGWA, CMCS8840);
        CMCSELAY.FUNC = '1';
        CMCSELAY.TRF_TYP = '3';
        CMCSELAY.HLD_NO = DCCUHLD.DATA.HLD_NO;
        CMCSELAY.TRO_AC = CMCS1500.AC1;
        CMCSELAY.TRI_AC = CMCS1500.AC2;
        CMCSELAY.CCY = CMCS1500.CCY_NO1;
        CMCSELAY.CCY_TYP = CMCS1500.CCY_TYP1;
        CMCSELAY.TRF_AMT = CMCS1500.AMT;
        CMCSELAY.FEE_AMT = WS_FEE_AMT;
        CMCS8840.AC_TYP1 = CMCS1500.AC_TYP1;
        CMCS8840.AC1 = CMCS1500.AC1;
        CMCS8840.AC_SEQ1 = CMCS1500.AC_SEQ1;
        CMCS8840.CCY_NO1 = CMCS1500.CCY_NO1;
        CMCS8840.CCY_TYP1 = CMCS1500.CCY_TYP1;
        CMCS8840.ACO_AC1 = CMCS1500.ACO_AC1;
        CMCS8840.BV_TYP1 = CMCS1500.BV_TYP1;
        CMCS8840.BV_CD1 = CMCS1500.BV_CD1;
        CMCS8840.NM_FLG1 = CMCS1500.NM_FLG1;
        CMCS8840.CI_NM1 = CMCS1500.CI_NM1;
        CMCS8840.ID_FLG1 = CMCS1500.ID_FLG1;
        CMCS8840.ID_TYP1 = CMCS1500.ID_TYP1;
        CMCS8840.ID_NO1 = CMCS1500.ID_NO1;
        CMCS8840.RMK1 = CMCS1500.RMK1;
        CMCS8840.AC_TYP2 = CMCS1500.AC_TYP2;
        CMCS8840.AC2 = CMCS1500.AC2;
        CMCS8840.AC_SEQ2 = CMCS1500.AC_SEQ2;
        CMCS8840.ACO_AC2 = CMCS1500.ACO_AC2;
        CMCS8840.BV_TYP2 = CMCS1500.BV_TYP2;
        CMCS8840.BV_NO2 = CMCS1500.BV_NO2;
        CMCS8840.NM_FLG2 = CMCS1500.NM_FLG2;
        CMCS8840.CI_NM2 = CMCS1500.CI_NM2;
        CMCS8840.ID_FLG2 = CMCS1500.ID_FLG2;
        CMCS8840.ID_TYP2 = CMCS1500.ID_TYP2;
        CMCS8840.ID_NO2 = CMCS1500.ID_NO2;
        CMCS8840.RMK2 = CMCS1500.RMK2;
        CMCS8840.CCY_NO2 = CMCS1500.CCY_NO2;
        CMCS8840.CCY_TYP2 = CMCS1500.CCY_TYP2;
        CMCS8840.AMT = CMCS1500.AMT;
        CMCS8840.SMNM_FLG = CMCS1500.SMNM_FLG;
        CMCS8840.FC_FLG = CMCS1500.FC_FLG;
        CMCS8840.VAL_DT = CMCS1500.VAL_DT;
        CMCS8840.HLD_NO = DCCUHLD.DATA.HLD_NO;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS1500.FEE_DATA[1-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCS8840.FEE_DATA[1-1]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS1500.FEE_DATA[2-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCS8840.FEE_DATA[2-1]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCS1500.FEE_DATA[3-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCS8840.FEE_DATA[3-1]);
        CMCS8840.DESC = CMCS1500.DESC_64;
        CMCS8840.SMR = CMCS1500.RMK_100;
        CMCSELAY.TRIG_DATA = IBS.CLS2CPY(SCCGWA, CMCS8840);
        S000_CALL_CMZSELAY();
        if (pgmRtn) return;
    }
    public void B900_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCF150);
        IBS.init(SCCGWA, SCCFMT);
        B910_INQ_AC_BAL();
        if (pgmRtn) return;
        CMCF150.ACO_AC1 = CMCS1500.AC1;
        CMCF150.ACO_AC2 = CMCS1500.AC2;
        CMCF150.OPN_BR1 = WS_AC_BR1;
        CMCF150.OPN_BR2 = WS_AC_BR2;
        CMCF150.AMT = CMCS1500.AMT;
        CMCF150.FEE = WS_FEE_AMT;
        CMCF150.ARPC = DCCSARQC.CARD_ARPC;
        CEP.TRC(SCCGWA, CMCF150.ARPC);
        if (WS_DELAY_FLG == 'Y') {
            CMCF150.TRF_TYP = '3';
        }
        SCCFMT.FMTID = "CM150";
        SCCFMT.DATA_PTR = CMCF150;
        SCCFMT.DATA_LEN = 171;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B910_INQ_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDCIQBAL);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS1500.AC1;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.trim().length() > 0) {
            DDCIQBAL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
        } else {
            DDCIQBAL.DATA.AC = CMCS1500.AC1;
        }
        DDCIQBAL.DATA.CCY = CMCS1500.CCY_NO1;
        DDCIQBAL.DATA.CCY_TYPE = CMCS1500.CCY_TYP1;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF150.CUR_BAL1 = DDCIQBAL.DATA.CURR_BAL;
        CMCF150.AVL_BAL1 = DDCIQBAL.DATA.AVL_BAL;
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDCIQBAL);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS1500.AC2;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.trim().length() > 0) {
            DDCIQBAL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
        } else {
            DDCIQBAL.DATA.AC = CMCS1500.AC2;
        }
        DDCIQBAL.DATA.CCY = CMCS1500.CCY_NO2;
        DDCIQBAL.DATA.CCY_TYPE = CMCS1500.CCY_TYP2;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF150.CUR_BAL2 = DDCIQBAL.DATA.CURR_BAL;
        CMCF150.AVL_BAL2 = DDCIQBAL.DATA.AVL_BAL;
    }
    public void R_CHK_CARD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = CMCS1500.AC1;
        DCCPFTCK.FR_AC_NO = CMCS1500.AC1;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "04";
        DCCPFTCK.VAL.TXN_CCY = CMCS1500.CCY_NO1;
        DCCPFTCK.VAL.TXN_AMT = CMCS1500.AMT;
        DCCPFTCK.VAL.SNAME_TRF_FLG = 'N';
        DCCPFTCK.VAL.DNAME_TRF_FLG = 'Y';
        DCCPFTCK.FUNCTION_CODE = 'B';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301") 
            && CMCS1500.TRK2_DAT.trim().length() == 0) {
            CMCS1500.TRK2_DAT = "0000000000000000000000000000000000000000";
        }
        CEP.TRC(SCCGWA, CMCS1500.TRK2_DAT);
        CEP.TRC(SCCGWA, DCCPFTCK.TRK2_DAT);
        DCCPFTCK.TRK2_DAT = CMCS1500.TRK2_DAT;
        DCCPFTCK.TRK3_DAT = CMCS1500.TRK3_DAT;
        DCCPFTCK.TXN_MMO = CMCS1500.RMK1;
        S000_CALL_DCZPFTCK();
        if (pgmRtn) return;
    }
    public void R_CHK_CARD2_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = CMCS1500.AC2;
        DCCPFTCK.TO_AC_NO = CMCS1500.AC2;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "02";
        DCCPFTCK.VAL.TXN_CCY = CMCS1500.CCY_NO1;
        DCCPFTCK.VAL.TXN_AMT = CMCS1500.AMT;
        DCCPFTCK.VAL.SNAME_TRF_FLG = 'N';
        DCCPFTCK.VAL.DNAME_TRF_FLG = 'Y';
        DCCPFTCK.FUNCTION_CODE = 'S';
        DCCPFTCK.TXN_MMO = CMCS1500.RMK2;
        S000_CALL_DCZPFTCK();
        if (pgmRtn) return;
    }
    public void R_CHK_DC_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.FUNC_CODE = 'P';
        if (CMCS1500.PSW_FLG == 'Y' 
            && CMCS1500.TRK_FLG != 'Y') {
            DCCPCDCK.FUNC_CODE = 'P';
        }
        if (CMCS1500.TRK_FLG == 'Y' 
            && CMCS1500.PSW_FLG != 'Y') {
            DCCPCDCK.FUNC_CODE = 'T';
        }
        if (CMCS1500.PSW_FLG == 'Y' 
            && CMCS1500.TRK_FLG == 'Y') {
            DCCPCDCK.FUNC_CODE = 'B';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301") 
            && CMCS1500.TRK2_DAT.trim().length() == 0) {
            CMCS1500.TRK2_DAT = "0000000000000000000000000000000000000000";
        }
        CEP.TRC(SCCGWA, CMCS1500.TRK2_DAT);
        DCCPCDCK.CARD_NO = CMCS1500.AC1;
        DCCPCDCK.CARD_PSW = CMCS1500.PSW;
        DCCPCDCK.TRK2_DAT = CMCS1500.TRK2_DAT;
        DCCPCDCK.TRK3_DAT = CMCS1500.TRK3_DAT;
        S000_CALL_DCZPCDCK();
        if (pgmRtn) return;
    }
    public void R_CHK_DD_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMPAY);
        DDCIMPAY.FUNC = 'K';
        DDCIMPAY.PAY_MTH = '1';
        DDCIMPAY.AC = CMCS1500.AC1;
        DDCIMPAY.PSWD_OLD = CMCS1500.PSW;
        S000_CALL_DDZIMPAY();
        if (pgmRtn) return;
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
    public void R000_CHK_CITY_FLG_BY_AREA_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUBRRC);
        WS_CITY_FLG = ' ';
        DCCUBRRC.INP_DATA.FUNC = '2';
        DCCUBRRC.INP_DATA.CARD_NO = CMCS1500.AC1;
        DCCUBRRC.INP_DATA.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_DCZUBRRC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCS1500.AC1);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DCCUBRRC.OUT_DATA.O_CITY_FLG);
        WS_CITY_FLG = DCCUBRRC.OUT_DATA.O_CITY_FLG;
        CEP.TRC(SCCGWA, WS_CITY_FLG);
    }
    public void S000_CALL_DCZUBRRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUBRRC", DCCUBRRC, true);
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK, true);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY, true);
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC, true);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK ", DCCPFTCK, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void S000_CALL_DCZSARQC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-ARQC-CHECK", DCCSARQC);
    }
    public void S000_CALL_BPZSCFEE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-F-C-FEE", BPCTCFEE);
        CEP.TRC(SCCGWA, BPCTCFEE.RC.RC_CODE);
        if (BPCTCFEE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCFEE.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        CEP.TRC(SCCGWA, BPCFFCON.RC.RC_CODE);
        if (BPCFFCON.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        if (WS_RMK_CDE == null) WS_RMK_CDE = "";
        JIBS_tmp_int = WS_RMK_CDE.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) WS_RMK_CDE += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + WS_RMK_CDE + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
    }
    public void S000_CALL_CMZSELAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-SVR-CMZSELAY", CMCSELAY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
