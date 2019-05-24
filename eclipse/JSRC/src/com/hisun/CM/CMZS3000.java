package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFFCAL;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUSBOX;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIQBAL;
import com.hisun.DD.DDCSQAC;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CMZS3000 {
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE GLRP INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHGLRP";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    String WS_CARD_NO = " ";
    int WS_CARD_SEQ = 0;
    String WS_AC = " ";
    String WS_AC_NM = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    int WS_CARD_BR = 0;
    String WS_TRN_CCY = " ";
    double WS_TRN_AMT = 0;
    double WS_AC_BAL = 0;
    double WS_AVA_BAL = 0;
    String WS_SMR = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRRPT AIRRPT = new AIRRPT();
    AICHGLRP AICNGLRP = new AICHGLRP();
    AICHGLRP AICOGLRP = new AICHGLRP();
    DDCSQAC DDCSQAC = new DDCSQAC();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICRGLRP AICRGLRP = new AICRGLRP();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    CICCUST CICCUST = new CICCUST();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CMCF001 CMCF001 = new CMCF001();
    CICQACAC CICQACAC = new CICQACAC();
    SCRCWA SCRCWA = new SCRCWA();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    CICQACRI CICQACRI = new CICQACRI();
    DCCSARQC DCCSARQC = new DCCSARQC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3000 CMCS3000;
    public void MP(SCCGWA SCCGWA, CMCS3000 CMCS3000) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3000 = CMCS3000;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3000 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF001);
        IBS.init(SCCGWA, DDCUDRAC);
        IBS.init(SCCGWA, DDCUCRAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CI_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CMCS3000.ARQC_DATA);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301") 
            && (CMCS3000.ARQC_DATA.trim().length() > 0 
            && CMCS3000.ARQC_DATA.charAt(0) != 0X00)) {
            B250_INPUT_ARQC_CHECK();
            if (pgmRtn) return;
        }
        B400_TRN_PROC();
        if (pgmRtn) return;
        B410_SET_FEE_INFO();
        if (pgmRtn) return;
        B420_FEE_CAL_ROC();
        if (pgmRtn) return;
        B500_CARD_INF();
        if (pgmRtn) return;
        B600_INQ_BAL();
        if (pgmRtn) return;
        B700_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMCS3000.BUSI_KND == ' ') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_KND_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.TXN_CCY.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CCY_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.TXN_AMT == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AMT_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.ID_FLG == 'Y' 
            && (CMCS3000.ID_TYP.trim().length() == 0 
            || CMCS3000.ID_NO.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.NM_FLG == 'Y' 
            && CMCS3000.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.TEL_FLG == 'Y' 
            && CMCS3000.TEL_NO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TEL_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.TXN_RMK.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_RMK_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, CMCS3000.CITY_FLG);
        if (CMCS3000.CITY_FLG == ' ') {
            R000_CHK_CITY_FLG_BY_AREA_CD();
            if (pgmRtn) return;
        } else if (CMCS3000.CITY_FLG == '0'
            || CMCS3000.CITY_FLG == '1'
            || CMCS3000.CITY_FLG == '2'
            || CMCS3000.CITY_FLG == '3'
            || CMCS3000.CITY_FLG == '4'
            || CMCS3000.CITY_FLG == '5') {
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (CMCS3000.TXN_RMK == null) CMCS3000.TXN_RMK = "";
        JIBS_tmp_int = CMCS3000.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3000.TXN_RMK += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3000.TXN_RMK.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
    public void B200_CI_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMCS3000.CARD_NO;
        R000_CALL_CICCUST();
        if (pgmRtn) return;
        CMCF001.AC_NM = CICCUST.O_DATA.O_CI_NM;
        CMCF001.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        CMCF001.ID_NO = CICCUST.O_DATA.O_ID_NO;
        CMCF001.TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        if (CMCS3000.TEL_FLG == 'Y' 
            && !CMCS3000.TEL_NO.equalsIgnoreCase(WS_TEL_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TELNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.NM_FLG == 'Y' 
            && !CMCS3000.CI_NM.equalsIgnoreCase(CMCF001.AC_NM)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CINM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.ID_FLG == 'Y' 
            && !CMCS3000.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDTYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3000.ID_FLG == 'Y' 
            && !CMCS3000.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B250_INPUT_ARQC_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3000.CARD_NO);
        CEP.TRC(SCCGWA, CMCS3000.CARD_SEQ2);
        CEP.TRC(SCCGWA, CMCS3000.ARQC);
        CEP.TRC(SCCGWA, CMCS3000.ARQC_DATA);
        CEP.TRC(SCCGWA, CMCS3000.ISSUE_DATA);
        CEP.TRC(SCCGWA, CMCS3000.VERIFY_RLT);
        IBS.init(SCCGWA, DCCSARQC);
        DCCSARQC.CARD_NO = CMCS3000.CARD_NO;
        DCCSARQC.CARD_SEQ = CMCS3000.CARD_SEQ2;
        DCCSARQC.CARD_ARQC = CMCS3000.ARQC;
        DCCSARQC.CARD_ARQC_DATA = CMCS3000.ARQC_DATA;
        DCCSARQC.ISSUE_DATA = CMCS3000.ISSUE_DATA;
        DCCSARQC.VERIFY_RLT = CMCS3000.VERIFY_RLT;
        S000_CALL_DCZSARQC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARPC);
    }
    public void B400_TRN_PROC() throws IOException,SQLException,Exception {
        if ((CMCS3000.BUSI_KND == '1' 
            || CMCS3000.BUSI_KND == '2') 
            && CMCS3000.TX_TYP == ' ') {
            IBS.init(SCCGWA, DDCSQAC);
            DDCSQAC.INPUT_INFO.FUNC = 'A';
            DDCSQAC.INPUT_INFO.AC_NO = CMCS3000.CARD_NO;
            S000_CALL_DDZSQAC();
            if (pgmRtn) return;
            WS_I = 1;
            while (WS_I <= 100 
                && DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].ACO_NO.trim().length() != 0) {
                if ((CMCS3000.TXN_CCY.equalsIgnoreCase(DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].CCY)) 
                    && (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE == '4' 
                    || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE == '5' 
                    || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE == 'B')) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYPE_ERR);
                }
                WS_I += 1;
            }
            R000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            R000_CALL_BPCUSBOX();
            if (pgmRtn) return;
        }
        if (CMCS3000.BUSI_KND == '0' 
            && CMCS3000.TX_TYP == '1') {
            R000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            R000_CALL_BPCUSBOX();
            if (pgmRtn) return;
        }
        if ((CMCS3000.BUSI_KND == '0' 
            && CMCS3000.TX_TYP == ' ') 
            || (CMCS3000.BUSI_KND == '1' 
            && CMCS3000.TX_TYP == '1') 
            || (CMCS3000.BUSI_KND == '2' 
            && CMCS3000.TX_TYP == '1')) {
            R000_CALL_DDZUCRAC();
            if (pgmRtn) return;
            R000_CALL_BPCUABOX();
            if (pgmRtn) return;
        }
    }
    public void B410_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS3000.CARD_NO;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS3000.CARD_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS3000.TXN_CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B420_FEE_CAL_ROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG);
        if (CMCS3000.CITY_FLG == '0'
            || CMCS3000.CITY_FLG == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = CMCS3000.CITY_FLG;
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
