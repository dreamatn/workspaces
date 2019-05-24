package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCPACTY;
import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMR;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIQBAL;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CMZS8840 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_AC_TYP1 = " ";
    int WS_I = 0;
    short WS_R = 0;
    char WS_ID_TYP1 = ' ';
    String WS_ID_NO1 = " ";
    String WS_AC_NM1 = " ";
    int WS_AC_BR1 = 0;
    String WS_AC_BR_NM1 = " ";
    char WS_ID_TYP2 = ' ';
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
    double WS_FEE_AMT = 0;
    String WS_DC_CD_STSW = " ";
    short WS_ACNAME_FLG = 0;
    String WS_ACNAME_IN1 = " ";
    String WS_ACNAME_AC1 = " ";
    String WS_ACNAME_IN2 = " ";
    String WS_ACNAME_AC2 = " ";
    double WS_AMT = 0;
    double WS_FEE = 0;
    String WS_ACO_AC1 = " ";
    int WS_OPN_BR1 = 0;
    double WS_CUR_BAL1 = 0;
    double WS_AVL_BAL1 = 0;
    String WS_ACO_AC2 = " ";
    int WS_OPN_BR2 = 0;
    double WS_CUR_BAL2 = 0;
    double WS_AVL_BAL2 = 0;
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
    CMCF500 CMCF500 = new CMCF500();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCSRHLD DCCSRHLD = new DCCSRHLD();
    CMCSELAY CMCSELAY = new CMCSELAY();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS8840 CMCS8840;
    public void MP(SCCGWA SCCGWA, CMCS8840 CMCS8840) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS8840 = CMCS8840;
        CEP.TRC(SCCGWA);
        A100_INIT_PROC();
        if (pgmRtn) return;
        A200_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS8840 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A100_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF500);
        IBS.init(SCCGWA, DCCSRHLD);
        IBS.init(SCCGWA, CMCSELAY);
        CEP.TRC(SCCGWA, "S8840-AC1:");
        CEP.TRC(SCCGWA, CMCS8840.AC1);
        CEP.TRC(SCCGWA, "S8840-AC2:");
        CEP.TRC(SCCGWA, CMCS8840.AC2);
        CEP.TRC(SCCGWA, CMCS8840.AC_TYP1);
        CEP.TRC(SCCGWA, CMCS8840.AC1);
        CEP.TRC(SCCGWA, CMCS8840.AC_SEQ1);
        CEP.TRC(SCCGWA, CMCS8840.CCY_NO1);
        CEP.TRC(SCCGWA, CMCS8840.CCY_TYP1);
        CEP.TRC(SCCGWA, CMCS8840.ACO_AC1);
        CEP.TRC(SCCGWA, CMCS8840.PSW_FLG);
        CEP.TRC(SCCGWA, CMCS8840.PSW);
        CEP.TRC(SCCGWA, CMCS8840.BV_TYP1);
        CEP.TRC(SCCGWA, CMCS8840.BV_CD1);
        CEP.TRC(SCCGWA, CMCS8840.TRK_FLG);
        CEP.TRC(SCCGWA, CMCS8840.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS8840.TRK3_DAT);
        CEP.TRC(SCCGWA, CMCS8840.NM_FLG1);
        CEP.TRC(SCCGWA, CMCS8840.CI_NM1);
        CEP.TRC(SCCGWA, CMCS8840.ID_FLG1);
        CEP.TRC(SCCGWA, CMCS8840.ID_TYP1);
        CEP.TRC(SCCGWA, CMCS8840.ID_NO1);
        CEP.TRC(SCCGWA, CMCS8840.RMK1);
        CEP.TRC(SCCGWA, CMCS8840.AC_TYP2);
        CEP.TRC(SCCGWA, CMCS8840.AC2);
        CEP.TRC(SCCGWA, CMCS8840.AC_SEQ2);
        CEP.TRC(SCCGWA, CMCS8840.ACO_AC2);
        CEP.TRC(SCCGWA, CMCS8840.BV_TYP2);
        CEP.TRC(SCCGWA, CMCS8840.BV_NO2);
        CEP.TRC(SCCGWA, CMCS8840.NM_FLG2);
        CEP.TRC(SCCGWA, CMCS8840.CI_NM2);
        CEP.TRC(SCCGWA, CMCS8840.ID_FLG2);
        CEP.TRC(SCCGWA, CMCS8840.ID_TYP2);
        CEP.TRC(SCCGWA, CMCS8840.ID_NO2);
        CEP.TRC(SCCGWA, CMCS8840.RMK2);
        CEP.TRC(SCCGWA, CMCS8840.CCY_NO2);
        CEP.TRC(SCCGWA, CMCS8840.CCY_TYP2);
        CEP.TRC(SCCGWA, CMCS8840.AMT);
        CEP.TRC(SCCGWA, CMCS8840.SMNM_FLG);
        CEP.TRC(SCCGWA, CMCS8840.FC_FLG);
        CEP.TRC(SCCGWA, CMCS8840.VAL_DT);
        CEP.TRC(SCCGWA, CMCS8840.DESC);
        CEP.TRC(SCCGWA, CMCS8840.SMR);
        CEP.TRC(SCCGWA, CMCS8840.HLD_NO);
    }
    public void A200_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHG_SYS();
        if (pgmRtn) return;
        B200_INPUT_CHECK();
        if (pgmRtn) return;
        B300_INPUT_CUS_CHECK();
        if (pgmRtn) return;
        B400_TRAN_MAIN_PROC();
        if (pgmRtn) return;
        B500_FEE_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B100_CHG_SYS() throws IOException,SQLException,Exception {
        CMCSELAY.HLD_NO = CMCS8840.HLD_NO;
        CMCSELAY.FUNC = '4';
        S000_CALL_CMZSELAY();
        if (pgmRtn) return;
    }
    public void B200_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMCS8840.AC1.trim().length() == 0 
            || CMCS8840.AC1.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR, "AC1 IS SPACE ");
        }
        if (CMCS8840.AC2.trim().length() == 0 
            || CMCS8840.AC2.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR, "AC2 IS SAPCE ");
        }
        if ((CMCS8840.AC_TYP1 == ' ' 
            || CMCS8840.AC_TYP1 == 0X00) 
            || (CMCS8840.AC_TYP2 == ' ' 
            || CMCS8840.AC_TYP2 == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        if (!CMCS8840.CCY_NO1.equalsIgnoreCase(CMCS8840.CCY_NO2)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR);
        }
        if (CMCS8840.NM_FLG1 == 'Y' 
            && (CMCS8840.CI_NM1.trim().length() == 0 
            || CMCS8840.CI_NM1.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR, "CI-NM1 IS SPACE");
        }
        if (CMCS8840.NM_FLG2 == 'Y' 
            && (CMCS8840.CI_NM2.trim().length() == 0 
            || CMCS8840.CI_NM2.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR, "CI-NM2 IS SPACE");
        }
        if (CMCS8840.PSW_FLG == 'Y' 
            && (CMCS8840.PSW.trim().length() == 0 
            || CMCS8840.PSW.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PSW_ERR);
        }
        if (CMCS8840.ID_FLG1 == 'Y' 
            && ((CMCS8840.ID_NO1.trim().length() == 0 
            || CMCS8840.ID_NO1.charAt(0) == 0X00) 
            || (CMCS8840.ID_TYP1.trim().length() == 0))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR, "ID1 IS SPACE");
        }
        if (CMCS8840.ID_FLG2 == 'Y' 
            && ((CMCS8840.ID_NO2.trim().length() == 0 
            || CMCS8840.ID_NO2.charAt(0) == 0X00) 
            || (CMCS8840.ID_TYP2.trim().length() == 0))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR, "ID2 IS SPACE");
        }
        if (CMCS8840.RMK1.trim().length() == 0 
            || CMCS8840.RMK1.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_ERR);
        }
        if (CMCS8840.RMK2.trim().length() == 0 
            || CMCS8840.RMK2.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_ERR);
        }
        if (CMCS8840.RMK1 == null) CMCS8840.RMK1 = "";
        JIBS_tmp_int = CMCS8840.RMK1.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS8840.RMK1 += " ";
        WS_RMK_CDE = CMCS8840.RMK1.substring(0, 4);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (CMCS8840.RMK2 == null) CMCS8840.RMK2 = "";
        JIBS_tmp_int = CMCS8840.RMK2.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS8840.RMK2 += " ";
        WS_RMK_CDE = CMCS8840.RMK2.substring(0, 4);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (CMCS8840.CCY_NO1.trim().length() == 0) {
            CMCS8840.CCY_NO1 = "156";
        }
        if (CMCS8840.CCY_NO2.trim().length() == 0) {
            CMCS8840.CCY_NO2 = "156";
        }
    }
    public void B300_INPUT_CUS_CHECK() throws IOException,SQLException,Exception {
        B310_INQ_CUS_PROC();
        if (pgmRtn) return;
        B320_CHK_CI_INF();
        if (pgmRtn) return;
        if (WS_AC_FRM_APP1.equalsIgnoreCase("DC")) {
            R_CHK_CARD_STATUS();
            if (pgmRtn) return;
        }
    }
    public void B310_INQ_CUS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        IBS.init(SCCGWA, BPCPQORG);
        CMCSIQAC.I_FUNC = '2';
        CMCSIQAC.I_CUS_AC = CMCS8840.AC1;
        CMCSIQAC.I_CCY = CMCS8840.CCY_NO1;
        CMCSIQAC.I_CCY_TYP = CMCS8840.CCY_TYP1;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_ID_TYP1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_TYP.charAt(0);
        WS_ID_NO1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO;
        WS_AC_NM1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        WS_AC_BR1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR;
        WS_AC_FRM_APP1 = CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR);
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW);
        WS_DC_CD_STSW = CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW;
        BPCPQORG.BR = WS_AC_BR1;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_AC_BR_NM1 = BPCPQORG.CHN_NM;
        IBS.init(SCCGWA, CMCSIQAC);
        IBS.init(SCCGWA, BPCPQORG);
        CMCSIQAC.I_FUNC = '2';
        CMCSIQAC.I_CUS_AC = CMCS8840.AC2;
        CMCSIQAC.I_CCY = CMCS8840.CCY_NO1;
        CMCSIQAC.I_CCY_TYP = CMCS8840.CCY_TYP1;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_ID_TYP2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_TYP.charAt(0);
        WS_ID_NO2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_ID_NO;
        WS_AC_NM2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        WS_AC_BR2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR;
        WS_AC_FRM_APP2 = CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_OPE_BR);
        BPCPQORG.BR = WS_AC_BR2;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_AC_BR_NM2 = BPCPQORG.CHN_NM;
    }
    public void B320_CHK_CI_INF() throws IOException,SQLException,Exception {
        if (CMCS8840.NM_FLG1 == 'Y') {
            WS_ACNAME_IN1 = CMCS8840.CI_NM1;
            WS_ACNAME_AC1 = WS_AC_NM1;
