package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT1500 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_AC_TYP1 = " ";
    int WS_I = 0;
    short WS_R = 0;
    char WS_ID_TYP1 = ' ';
    String WS_ID_NO1 = " ";
    String WS_CI_NM1 = " ";
    char WS_ID_TYP2 = ' ';
    String WS_ID_NO2 = " ";
    String WS_CI_NM2 = " ";
    String WS_CARD_NO = " ";
    String WS_ACAC_NO = " ";
    double WS_CURR_BAL = 0;
    double WS_ACL_BAL = 0;
    double WS_CURR_BAL2 = 0;
    double WS_ACL_BAL2 = 0;
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
    BPCTCALF BPCTCALF = new BPCTCALF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMB1500_AWA_1500 CMB1500_AWA_1500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A100_INIT_PROC();
        if (pgmRtn) return;
        A200_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT1500 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A100_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB1500_AWA_1500>");
        CMB1500_AWA_1500 = (CMB1500_AWA_1500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF500);
    }
    public void A200_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK();
        if (pgmRtn) return;
        B300_TRAN_MAIN_PROC();
        if (pgmRtn) return;
        B400_OUTPUT_DATA();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMB1500_AWA_1500.AC1.trim().length() == 0 
            || CMB1500_AWA_1500.AC2.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.AC_TYP1 == ' ' 
            || CMB1500_AWA_1500.AC_TYP2 == ' ') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_TYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (!CMB1500_AWA_1500.CCY_NO1.equalsIgnoreCase(CMB1500_AWA_1500.CCY_NO2)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CCY_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.CCY_NO1.equalsIgnoreCase("0")) {
            CMB1500_AWA_1500.CCY_NO1 = "156";
        }
        if (CMB1500_AWA_1500.CCY_NO2.equalsIgnoreCase("0")) {
            CMB1500_AWA_1500.CCY_NO2 = "156";
        }
        if (CMB1500_AWA_1500.CCY_TYP1 == ' ') {
            CMB1500_AWA_1500.CCY_TYP1 = '1';
        }
        if (CMB1500_AWA_1500.CCY_TYP2 == ' ') {
            CMB1500_AWA_1500.CCY_TYP2 = '1';
        }
        R000_BPZPACTY_PROC();
        if (pgmRtn) return;
        WS_AC_TYP1 = BPCPACTY.INPUT_DATA.AC;
        if (WS_AC_TYP1.equalsIgnoreCase("26") 
            && (CMB1500_AWA_1500.TRK2_DAT.trim().length() == 0 
            || CMB1500_AWA_1500.TRK3_DAT.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TRK_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.NM_FLG1 == 'Y' 
            && CMB1500_AWA_1500.CI_NM1.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.NM_FLG2 == 'Y' 
            && CMB1500_AWA_1500.CI_NM2.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.PSW_FLG == 'Y' 
            && CMB1500_AWA_1500.PSW.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_PSW_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.ID_FLG1 == 'Y' 
            && (CMB1500_AWA_1500.ID_NO1.trim().length() == 0 
            || CMB1500_AWA_1500.ID_TYP1.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.ID_FLG2 == 'Y' 
            && (CMB1500_AWA_1500.ID_NO2.trim().length() == 0 
            || CMB1500_AWA_1500.ID_TYP2.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.RMK1.trim().length() == 0) {
            CMB1500_AWA_1500.RMK1 = "102";
        }
        if (CMB1500_AWA_1500.RMK2.trim().length() == 0) {
            CMB1500_AWA_1500.RMK2 = "102";
        }
    }
    public void B300_TRAN_MAIN_PROC() throws IOException,SQLException,Exception {
        B110_INQ_ACO_PROC();
        if (pgmRtn) return;
        B120_CHK_CI_INF();
        if (pgmRtn) return;
        B130_DR_PROC();
        if (pgmRtn) return;
        B140_CR_PROC();
        if (pgmRtn) return;
        B160_BAL();
        if (pgmRtn) return;
    }
    public void B110_INQ_ACO_PROC() throws IOException,SQLException,Exception {
        if (CMB1500_AWA_1500.ACO_AC1.trim().length() == 0) {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = CMB1500_AWA_1500.AC1;
            CICQACAC.DATA.AGR_SEQ = CMB1500_AWA_1500.AC_SEQ1;
            CICQACAC.DATA.CCY_ACAC = CMB1500_AWA_1500.CCY_NO1;
            CICQACAC.DATA.CR_FLG = CMB1500_AWA_1500.CCY_TYP1;
            R100_CALL_CICQACAC();
            if (pgmRtn) return;
            CMCF500.ACO_AC1 = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CMCF500.OPN_BR1 = CICQACAC.O_DATA.O_ACR_DATA.O_OPN_BR_ACR;
        }
        if (CMB1500_AWA_1500.ACO_AC2.trim().length() == 0) {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = CMB1500_AWA_1500.AC2;
            CICQACAC.DATA.AGR_SEQ = CMB1500_AWA_1500.AC_SEQ2;
            CICQACAC.DATA.CCY_ACAC = CMB1500_AWA_1500.CCY_NO2;
            CICQACAC.DATA.CR_FLG = CMB1500_AWA_1500.CCY_TYP2;
            R100_CALL_CICQACAC();
            if (pgmRtn) return;
            CMCF500.ACO_AC2 = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CMCF500.OPN_BR2 = CICQACAC.O_DATA.O_ACR_DATA.O_OPN_BR_ACR;
        }
    }
    public void B120_CHK_CI_INF() throws IOException,SQLException,Exception {
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMB1500_AWA_1500.AC1;
        R200_CALL_CICCUST();
        if (pgmRtn) return;
        WS_ID_TYP1 = CICCUST.O_DATA.O_ID_TYPE.charAt(0);
        WS_ID_NO1 = CICCUST.O_DATA.O_ID_NO;
        WS_CI_NM1 = CICCUST.O_DATA.O_CI_NM;
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMB1500_AWA_1500.AC2;
        R200_CALL_CICCUST();
        if (pgmRtn) return;
        WS_ID_TYP2 = CICCUST.O_DATA.O_ID_TYPE.charAt(0);
        WS_ID_NO2 = CICCUST.O_DATA.O_ID_NO;
        WS_CI_NM2 = CICCUST.O_DATA.O_CI_NM;
        if (CMB1500_AWA_1500.NM_FLG1 == 'Y' 
            && !CMB1500_AWA_1500.CI_NM1.equalsIgnoreCase(WS_CI_NM1)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM1_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.NM_FLG2 == 'Y' 
            && !CMB1500_AWA_1500.CI_NM2.equalsIgnoreCase(WS_CI_NM2)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM2_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.ID_FLG1 == 'Y' 
            && (!CMB1500_AWA_1500.ID_NO1.equalsIgnoreCase(WS_ID_NO1) 
            || !CMB1500_AWA_1500.ID_TYP1.equalsIgnoreCase(WS_ID_TYP1+""))) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID1_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMB1500_AWA_1500.ID_FLG2 == 'Y' 
            && (!CMB1500_AWA_1500.ID_NO2.equalsIgnoreCase(WS_ID_NO2) 
            || !CMB1500_AWA_1500.ID_TYP2.equalsIgnoreCase(WS_ID_TYP2+""))) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID2_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B130_DR_PROC() throws IOException,SQLException,Exception {
        if (CMB1500_AWA_1500.FC_FLG == 'Y') {
            DDCUDRAC.CLEAR_FLG = 'Y';
        }
        if (CMB1500_AWA_1500.PSW_FLG == 'Y' 
            && (CMB1500_AWA_1500.TRK_FLG == ' ' 
            || CMB1500_AWA_1500.TRK_FLG == 'N')) {
            DDCUDRAC.CHK_PSW = 'P';
        }
        if (CMB1500_AWA_1500.TRK_FLG == 'Y' 
            && (CMB1500_AWA_1500.PSW_FLG == 'N' 
            || CMB1500_AWA_1500.PSW_FLG == ' ')) {
            DDCUDRAC.CHK_PSW = 'T';
        }
        if (CMB1500_AWA_1500.PSW_FLG == 'Y' 
            && CMB1500_AWA_1500.PSW_FLG == 'Y') {
            DDCUDRAC.CHK_PSW = 'B';
        }
        if (CMB1500_AWA_1500.CCY_TYP1 == '1') {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = '2';
        }
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.AC = CMB1500_AWA_1500.AC1;
        DDCUDRAC.CCY = CMB1500_AWA_1500.CCY_NO1;
        DDCUDRAC.TX_AMT = CMB1500_AWA_1500.AMT;
        DDCUDRAC.TX_MMO = CMB1500_AWA_1500.RMK1;
        DDCUDRAC.OTHER_AC = CMB1500_AWA_1500.AC2;
        DDCUDRAC.OTHER_CCY = CMB1500_AWA_1500.CCY_NO2;
        DDCUDRAC.OTHER_AMT = CMB1500_AWA_1500.AMT;
        DDCUDRAC.RLT_AC = CMB1500_AWA_1500.AC2;
        DDCUDRAC.RLT_CCY = CMB1500_AWA_1500.CCY_NO2;
        DDCUDRAC.RLT_AC_NAME = CMB1500_AWA_1500.CI_NM2;
        R300_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B140_CR_PROC() throws IOException,SQLException,Exception {
        if (CMB1500_AWA_1500.CCY_TYP2 == '1') {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = CMB1500_AWA_1500.AC2;
        DDCUCRAC.CCY = CMB1500_AWA_1500.CCY_NO2;
        DDCUCRAC.TX_AMT = CMB1500_AWA_1500.AMT;
        DDCUCRAC.TX_MMO = CMB1500_AWA_1500.RMK2;
        DDCUDRAC.OTHER_AC = CMB1500_AWA_1500.AC1;
        DDCUDRAC.OTHER_CCY = CMB1500_AWA_1500.CCY_NO1;
        DDCUDRAC.OTHER_AMT = CMB1500_AWA_1500.AMT;
        DDCUDRAC.RLT_AC = CMB1500_AWA_1500.AC1;
        DDCUDRAC.RLT_CCY = CMB1500_AWA_1500.CCY_NO1;
        DDCUDRAC.RLT_AC_NAME = CMB1500_AWA_1500.CI_NM1;
        R400_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B150_FEE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '2';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMB1500_AWA_1500.CCY_NO1;
        BPCFFTXI.TX_DATA.CCY_TYPE = CMB1500_AWA_1500.CCY_TYP1;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMB1500_AWA_1500.AC1;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_AC = CMB1500_AWA_1500.AC1;
        BPCTCALF.INPUT_AREA.TX_CCY = CMB1500_AWA_1500.CCY_NO1;
        BPCTCALF.INPUT_AREA.TX_CNT = 3;
    }
    public void B160_BAL() throws IOException,SQLException,Exception {
        DDCIQBAL.DATA.AC = CMB1500_AWA_1500.ACO_AC1;
        R600_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF500.CUR_BAL1 = DDCIQBAL.DATA.CURR_BAL;
        CMCF500.AVL_BAL1 = DDCIQBAL.DATA.AVL_BAL;
        DDCIQBAL.DATA.AC = CMB1500_AWA_1500.ACO_AC2;
        R600_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF500.CUR_BAL2 = DDCIQBAL.DATA.CURR_BAL;
        CMCF500.AVL_BAL2 = DDCIQBAL.DATA.AVL_BAL;
    }
    public void B400_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        CMCF500.AMT = CMB1500_AWA_1500.AMT;
        SCCFMT.FMTID = "CM150";
        SCCFMT.DATA_PTR = CMCF500;
        SCCFMT.DATA_LEN = 150;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_BPZPACTY_PROC() throws IOException,SQLException,Exception {
        BPCPACTY.INPUT_DATA.AC = CMB1500_AWA_1500.AC1;
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-AC-TYPE", BPCPACTY);
    }
    public void R100_CALL_CICQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void R200_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    public void R300_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void R400_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void R600_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
