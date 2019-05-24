package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3030 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE GLRP INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHGLRP";
    String WS_ERR_MSG = " ";
    String WS_CARD_AC = " ";
    String WS_CI_NM = " ";
    String WS_STL_AC_NM = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    short WS_I = 0;
    double WS_AC_BAL = 0;
    double WS_AVA_BAL = 0;
    double WS_FEE_AMT = 0;
    String WS_TXN_TYPE = " ";
    String WS_CI_NO = " ";
    char WS_FEE_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSQAC DDCSQAC = new DDCSQAC();
    BPCFFCON BPCFFCON = new BPCFFCON();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    CICCUST CICCUST = new CICCUST();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CMCF040 CMCF040 = new CMCF040();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICQACRI CICQACRI = new CICQACRI();
    DCCUATMS DCCUATMS = new DCCUATMS();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCONESF BPCONESF = new BPCONESF();
    BPCTCFEE BPCTCFEE = new BPCTCFEE();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRL CICQACRL = new CICQACRL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    SCCGWA SCCGWA;
    CMCS3030 CMCS3030;
    public void MP(SCCGWA SCCGWA, CMCS3030 CMCS3030) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3030 = CMCS3030;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3030 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCF040);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, DDCIQBAL);
        CEP.TRC(SCCGWA, CMCS3030.BUSI_KND);
        CEP.TRC(SCCGWA, CMCS3030.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3030.CARD_NO);
        CEP.TRC(SCCGWA, CMCS3030.CARD_SEQ);
        CEP.TRC(SCCGWA, CMCS3030.TXN_CCY);
        CEP.TRC(SCCGWA, CMCS3030.TXN_AMT);
        CEP.TRC(SCCGWA, CMCS3030.TXN_PSW);
        CEP.TRC(SCCGWA, CMCS3030.TXN_RMK);
        CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS3030.TRK3_DAT);
        CEP.TRC(SCCGWA, CMCS3030.CVN);
        CEP.TRC(SCCGWA, CMCS3030.CITY_FLG);
        CEP.TRC(SCCGWA, CMCS3030.SLT_AC);
        CEP.TRC(SCCGWA, CMCS3030.SLT_NO);
        CEP.TRC(SCCGWA, CMCS3030.ID_FLG);
        CEP.TRC(SCCGWA, CMCS3030.ID_TYP);
        CEP.TRC(SCCGWA, CMCS3030.ID_NO);
        CEP.TRC(SCCGWA, CMCS3030.NM_FLG);
        CEP.TRC(SCCGWA, CMCS3030.CI_NM);
        CEP.TRC(SCCGWA, CMCS3030.TEL_FLG);
        CEP.TRC(SCCGWA, CMCS3030.TEL_NO);
        CEP.TRC(SCCGWA, CMCS3030.NARRATIVE);
        CEP.TRC(SCCGWA, CMCS3030.STL_DT);
        CEP.TRC(SCCGWA, CMCS3030.RLT_AC);
        CEP.TRC(SCCGWA, CMCS3030.RLT_NM);
        CEP.TRC(SCCGWA, CMCS3030.MERCH_NM);
        CEP.TRC(SCCGWA, CMCS3030.AC_NAME);
        CEP.TRC(SCCGWA, CMCS3030.ID_NO1);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B150_CHK_RMK_CDE();
        if (pgmRtn) return;
        B200_INPUT_AC_CHECK();
        if (pgmRtn) return;
        B500_CARD_TRN_PROC();
        if (pgmRtn) return;
        if (CMCS3030.BUSI_KND == '1' 
            || CMCS3030.BUSI_KND == '2') {
            B600_SYJJM_CHK();
            if (pgmRtn) return;
        }
        B710_SET_FEE_INFO();
        if (pgmRtn) return;
        B720_FEE_CAL_ROC();
        if (pgmRtn) return;
        B800_INQ_BAL_PROC();
        if (pgmRtn) return;
        B900_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMCS3030.BUSI_KND == ' ' 
            || CMCS3030.BUSI_KND == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_KND_ERR, CMCS3030.BUSI_KND);
        }
        if (CMCS3030.TXN_CCY.trim().length() == 0 
            || CMCS3030.TXN_CCY.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR, CMCS3030.TXN_CCY);
        }
        if (CMCS3030.TXN_AMT <= 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
        }
        if (CMCS3030.ID_FLG == 'Y' 
            && ((CMCS3030.ID_TYP.trim().length() == 0 
            || CMCS3030.ID_TYP.charAt(0) == 0X00) 
            || (CMCS3030.ID_NO.trim().length() == 0 
            || CMCS3030.ID_NO.charAt(0) == 0X00))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR);
        }
        if (CMCS3030.NM_FLG == 'Y' 
            && (CMCS3030.CI_NM.trim().length() == 0 
            || CMCS3030.CI_NM.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR, CMCS3030.CI_NM);
        }
        if (CMCS3030.TEL_FLG == 'Y' 
            && (CMCS3030.TEL_NO.trim().length() == 0 
            || CMCS3030.TEL_NO.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TEL_ERR, CMCS3030.TEL_NO);
        }
        if (CMCS3030.BUSI_KND == '0') {
            WS_TXN_TYPE = "02";
        } else if (CMCS3030.BUSI_KND == '1'
            || CMCS3030.BUSI_KND == '2') {
            WS_TXN_TYPE = "01";
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_INPUT_BUSI_TYPE, CMCS3030.BUSI_KND);
        }
    }
    public void B150_CHK_RMK_CDE() throws IOException,SQLException,Exception {
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
        if (CMCS3030.TXN_RMK == null) CMCS3030.TXN_RMK = "";
        JIBS_tmp_int = CMCS3030.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3030.TXN_RMK += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3030.TXN_RMK + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
    public void B200_INPUT_AC_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMCS3030.CARD_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_TEL_NO);
        WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
        WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        WS_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        CEP.TRC(SCCGWA, CMCS3030.ID_NO);
        if (CMCS3030.BUSI_KND == '0' 
            && CMCS3030.ID_NO1.trim().length() > 0 
            && !CMCS3030.ID_NO1.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3030.CITY_FLG);
        CEP.TRC(SCCGWA, CMCS3030.AREA_CD);
        if ((CMCS3030.CITY_FLG == ' ' 
            || CMCS3030.CITY_FLG == 0X00) 
            && (CMCS3030.AREA_CD.trim().length() == 0 
            || CMCS3030.AREA_CD.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AREA_CD_M_INPUT, CMCS3030.AREA_CD);
        }
        if (CMCS3030.CITY_FLG == ' ') {
            R000_CHK_CITY_FLG_BY_AREA_CD();
            if (pgmRtn) return;
        } else if (CMCS3030.CITY_FLG == '0'
            || CMCS3030.CITY_FLG == '1'
            || CMCS3030.CITY_FLG == '2'
            || CMCS3030.CITY_FLG == '3'
            || CMCS3030.CITY_FLG == '4'
            || CMCS3030.CITY_FLG == '5') {
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        R000_CHECK_CITY_FLG_FOR_DWK();
        if (pgmRtn) return;
        if (CMCS3030.TEL_FLG == 'Y' 
            && !CMCS3030.TEL_NO.equalsIgnoreCase(WS_TEL_NO)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TELNO_ERR, WS_TEL_NO);
        }
        if (CMCS3030.NM_FLG == 'Y' 
            && !CMCS3030.CI_NM.equalsIgnoreCase(WS_CI_NM)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CINM_ERR, WS_CI_NM);
        }
        if (CMCS3030.ID_FLG == 'Y' 
            && !CMCS3030.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDTYP_ERR, WS_ID_TYP);
        }
        if (CMCS3030.ID_FLG == 'Y') {
            if (!CMCS3030.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDTYP_ERR);
            }
            if (CMCS3030.ID_TYP.equalsIgnoreCase("10100") 
                && !CMCS3030.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                for (WS_I = 1; WS_I <= 70 
                    && (WS_ID_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0); WS_I += 1) {
                }
                WS_I -= 1;
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (WS_ID_NO.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("X")) {
                    WS_I -= 1;
                }
                if (WS_I < 6) {
                    CEP.TRC(SCCGWA, "IDERR002");
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDNO_ERR, WS_ID_NO);
                }
                WS_I -= 5;
                CEP.TRC(SCCGWA, WS_I);
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (!WS_ID_NO.substring(WS_I - 1, WS_I + 6 - 1).equalsIgnoreCase(CMCS3030.ID_NO)) {
                    CEP.TRC(SCCGWA, "IDERR003");
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDNO_ERR, WS_ID_NO);
                }
            }
        }
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = CMCS3030.SLT_AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_STL_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        IBS.init(SCCGWA, BPCPQORG);
        if (CMCS3030.SLT_AC == null) CMCS3030.SLT_AC = "";
        JIBS_tmp_int = CMCS3030.SLT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3030.SLT_AC += " ";
        if (CMCS3030.SLT_AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(CMCS3030.SLT_AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS3030.CARD_NO;
        R000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_STS_OLD);
        if (CICQACRI.O_DATA.O_CI_TYP == '2' 
            && CICQACRI.O_DATA.O_STS_OLD > '1' 
            && CICQACRI.O_DATA.O_FRM_APP_OLD.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = CMCS3030.CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            WS_CARD_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.TX_TYPE = 'I';
            DDCIMMST.DATA.KEY.AC_NO = WS_CARD_AC;
            CEP.TRC(SCCGWA, WS_CARD_AC);
            CEP.TRC(SCCGWA, DDCIMMST.DATA.KEY.AC_NO);
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.CASH_FLG);
            if ((DDCIMMST.DATA.CASH_FLG == '2' 
                || DDCIMMST.DATA.CASH_FLG == '4') 
                && (CMCS3030.BUSI_KND == '1' 
                || CMCS3030.BUSI_KND == '2')) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CANT_DWKBQ);
            }
            if ((DDCIMMST.DATA.CASH_FLG == '3' 
                || DDCIMMST.DATA.CASH_FLG == '4') 
                && CMCS3030.BUSI_KND == '0') {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CANT_DWKBC);
            }
        }
    }
    public void B500_CARD_TRN_PROC() throws IOException,SQLException,Exception {
        if (CMCS3030.BUSI_KND == '0'
            && CMCS3030.TX_TYP == ' '
            || CMCS3030.BUSI_KND == '1'
            && CMCS3030.TX_TYP == '1'
            || CMCS3030.BUSI_KND == '2'
            && CMCS3030.TX_TYP == '1') {
            B520_AC_TRF_IN_PROC();
            if (pgmRtn) return;
        } else if (CMCS3030.BUSI_KND == '0'
            && CMCS3030.TX_TYP == '1') {
            B510_AC_TRF_OUT_PROC();
            if (pgmRtn) return;
        } else if (CMCS3030.BUSI_KND == '1'
            && CMCS3030.TX_TYP == ' '
            || CMCS3030.BUSI_KND == '2'
            && CMCS3030.TX_TYP == ' ') {
            IBS.init(SCCGWA, DDCSQAC);
            DDCSQAC.INPUT_INFO.FUNC = 'A';
            DDCSQAC.INPUT_INFO.AC_NO = CMCS3030.CARD_NO;
            S000_CALL_DDZSQAC();
            if (pgmRtn) return;
            WS_I = 1;
            while (WS_I <= 100 
                && DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].ACO_NO.trim().length() != 0) {
                if ((CMCS3030.TXN_CCY.equalsIgnoreCase(DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].CCY)) 
                    && (DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE == '4' 
                    || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE == '5' 
                    || DDCSQAC.OUTPUT_DATA.ACAC_INFO[WS_I-1].AC_TYPE == 'B')) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYPE_ERR);
                }
                WS_I += 1;
            }
            B510_AC_TRF_OUT_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TX_TYPE_INP_ERR);
        }
    }
    public void B510_AC_TRF_OUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSTRAC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK3_DAT);
        IBS.init(SCCGWA, DDCSTRAC);
        if (CMCS3030.TRK2_DAT.trim().length() > 0 
                && CMCS3030.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'B';
            CEP.TRC(SCCGWA, "AAAA1");
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, CMCS3030.TXN_PSW);
        } else if (CMCS3030.TRK2_DAT.trim().length() == 0 
                && CMCS3030.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'P';
            if (CMCS3030.BUSI_KND == '1') {
                CMCS3030.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, "AAAA2");
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, CMCS3030.TXN_PSW);
        } else if (CMCS3030.TRK2_DAT.trim().length() > 0 
                && CMCS3030.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'T';
            CEP.TRC(SCCGWA, "CCCCC1");
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, CMCS3030.TXN_PSW);
        } else if (CMCS3030.TRK2_DAT.trim().length() == 0 
                && CMCS3030.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'N';
            if (CMCS3030.BUSI_KND == '1') {
                CMCS3030.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, "DDDD1");
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, CMCS3030.TXN_PSW);
        } else {
            CEP.TRC(SCCGWA, "EEEE1");
            CEP.TRC(SCCGWA, CMCS3030.TRK2_DAT);
            CEP.TRC(SCCGWA, CMCS3030.TXN_PSW);
        }
        DDCSTRAC.FR_CARD = CMCS3030.CARD_NO;
        DDCSTRAC.FR_CCY = CMCS3030.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3030.TXN_AMT;
        DDCSTRAC.FR_AC_CNAME = WS_CI_NM;
        DDCSTRAC.PSWD = CMCS3030.TXN_PSW;
        CEP.TRC(SCCGWA, DDCSTRAC.CHK_PSW);
        CEP.TRC(SCCGWA, "FFFFF1");
        CEP.TRC(SCCGWA, DDCSTRAC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK3_DAT);
        DDCSTRAC.TRK2_DAT = CMCS3030.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = CMCS3030.TRK3_DAT;
        DDCSTRAC.TO_AC = CMCS3030.SLT_AC;
        DDCSTRAC.RLT_AC = CMCS3030.RLT_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3030.RLT_NM;
        if (CMCS3030.SLT_AC == null) CMCS3030.SLT_AC = "";
        JIBS_tmp_int = CMCS3030.SLT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3030.SLT_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3030.SLT_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = BPCPQORG.CHN_NM;
        DDCSTRAC.TO_BV_TYPE = '3';
        DDCSTRAC.TO_CCY = CMCS3030.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3030.TXN_AMT;
        DDCSTRAC.TO_AC_CNAME = WS_STL_AC_NM;
        DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSTRAC.DR_MMO = CMCS3030.TXN_RMK;
        DDCSTRAC.CR_MMO = CMCS3030.TXN_RMK;
        DDCSTRAC.TX_RMK = CMCS3030.NARRATIVE;
        DDCSTRAC.REMARKS = CMCS3030.MERCH_NM;
        if (CMCS3030.CITY_FLG == '0'
            || CMCS3030.CITY_FLG == '1') {
            DDCSTRAC.TXN_REGION = '0';
        } else if (CMCS3030.CITY_FLG == '2') {
            DDCSTRAC.TXN_REGION = '1';
        } else if (CMCS3030.CITY_FLG == '3') {
            DDCSTRAC.TXN_REGION = '2';
        } else if (CMCS3030.CITY_FLG == '4') {
            DDCSTRAC.TXN_REGION = '3';
        } else if (CMCS3030.CITY_FLG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDCSTRAC.TXN_TYPE = "01";
        DDCSTRAC.IN_OUT_FLG = '2';
        if (CMCS3030.TX_TYP == '1') {
            DDCSTRAC.CANCEL_FLG = 'Y';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B520_AC_TRF_IN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        DDCSTRAC.FR_AC = CMCS3030.SLT_AC;
        DDCSTRAC.FR_BV_TYPE = '3';
        DDCSTRAC.FR_CCY = CMCS3030.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3030.TXN_AMT;
        DDCSTRAC.FR_AC_CNAME = WS_STL_AC_NM;
        DDCSTRAC.TO_CARD = CMCS3030.CARD_NO;
        DDCSTRAC.RLT_AC = CMCS3030.RLT_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3030.RLT_NM;
        if (CMCS3030.SLT_AC == null) CMCS3030.SLT_AC = "";
        JIBS_tmp_int = CMCS3030.SLT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3030.SLT_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3030.SLT_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = BPCPQORG.CHN_NM;
        DDCSTRAC.TO_CCY = CMCS3030.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3030.TXN_AMT;
        DDCSTRAC.TO_AC_CNAME = WS_CI_NM;
        DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSTRAC.DR_MMO = CMCS3030.TXN_RMK;
        DDCSTRAC.CR_MMO = CMCS3030.TXN_RMK;
        DDCSTRAC.TX_RMK = CMCS3030.NARRATIVE;
        DDCSTRAC.REMARKS = CMCS3030.MERCH_NM;
        if (CMCS3030.CITY_FLG == '0'
            || CMCS3030.CITY_FLG == '1') {
            DDCSTRAC.TXN_REGION = '0';
        } else if (CMCS3030.CITY_FLG == '2') {
            DDCSTRAC.TXN_REGION = '1';
        } else if (CMCS3030.CITY_FLG == '3') {
            DDCSTRAC.TXN_REGION = '2';
        } else if (CMCS3030.CITY_FLG == '4') {
            DDCSTRAC.TXN_REGION = '3';
        } else if (CMCS3030.CITY_FLG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDCSTRAC.TXN_TYPE = "02";
        DDCSTRAC.IN_OUT_FLG = '2';
        if (CMCS3030.TX_TYP == '1') {
            DDCSTRAC.CANCEL_FLG = 'Y';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B600_SYJJM_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUATMS);
        DCCUATMS.CARD_NO = CMCS3030.CARD_NO;
        DCCUATMS.FUNC = 'A';
        if (CMCS3030.CITY_FLG == '0') {
            DCCUATMS.TXN_TYPE = "01";
        } else if (CMCS3030.CITY_FLG == '1') {
            DCCUATMS.TXN_TYPE = "02";
        } else {
            DCCUATMS.TXN_TYPE = "03";
        }
        DCCUATMS.REGN_TYPE = "02";
        DCCUATMS.TC_FLG = "01";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUATMS.MONTH = JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1);
        DCCUATMS.TXN_AMT = CMCS3030.TXN_AMT;
        S000_CALL_DCZUATMS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CMCS3030.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_TYP == null) DCCUCINF.CARD_TYP = "";
        JIBS_tmp_int = DCCUCINF.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCCUCINF.CARD_TYP += " ";
        if (DCCUCINF.CARD_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.JAN_CNT);
                if (DCCUATMS.OUTPUT.JAN_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.FEB_CNT);
                if (DCCUATMS.OUTPUT.FEB_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.MAR_CNT);
                if (DCCUATMS.OUTPUT.MAR_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.APR_CNT);
                if (DCCUATMS.OUTPUT.APR_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.MAY_CNT);
                if (DCCUATMS.OUTPUT.MAY_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.JUN_CNT);
                if (DCCUATMS.OUTPUT.JUN_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.JUL_CNT);
                if (DCCUATMS.OUTPUT.JUL_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.AUG_CNT);
                if (DCCUATMS.OUTPUT.AUG_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.SEP_CNT);
                if (DCCUATMS.OUTPUT.SEP_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.OCT_CNT);
                if (DCCUATMS.OUTPUT.OCT_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.NOV_CNT);
                if (DCCUATMS.OUTPUT.NOV_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            } else if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")) {
                CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.DEC_CNT);
                if (DCCUATMS.OUTPUT.DEC_CNT == 1) {
                    WS_FEE_FLG = 'Y';
                }
            } else {
            }
        }
        CEP.TRC(SCCGWA, WS_FEE_FLG);
    }
    public void B710_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS3030.CARD_NO;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS3030.CARD_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS3030.TXN_CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B720_FEE_CAL_ROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '1';
        BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        if (CMCS3030.CITY_FLG == '0'
            || CMCS3030.CITY_FLG == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = CMCS3030.CITY_FLG;
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "02";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else if (CMCS3030.CITY_FLG == '2'
            || CMCS3030.CITY_FLG == '3'
            || CMCS3030.CITY_FLG == '4'
            || CMCS3030.CITY_FLG == '5') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "03";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG);
        BPCTCALF.INPUT_AREA.TX_AC = CMCS3030.CARD_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        BPCTCALF.INPUT_AREA.TX_CCY = CMCS3030.TXN_CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = CMCS3030.TXN_AMT;
        BPCTCALF.INPUT_AREA.OTHER_AC = CMCS3030.RLT_AC;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = CICQACRI.O_DATA.O_PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE = CICQACRI.O_DATA.O_PROD_CD;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_PROD_CD);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.OTHER_AC);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B700_FEE_ROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 3 
            && (CMCS3030.FEE_DATA[WS_I-1].FEE_CD.trim().length() != 0 
            && CMCS3030.FEE_DATA[WS_I-1].FEE_CD.charAt(0) != 0X00); WS_I += 1) {
            if (CMCS3030.FEE_DATA[WS_I-1].FEE == 0) {
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = CMCS3030.CARD_NO;
                R000_CALL_CIZQACRI();
                if (pgmRtn) return;
                B710_FEE_CAL_PROC();
                if (pgmRtn) return;
            }
            B720_FEE_CHARGE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B710_FEE_CAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCFEE);
        BPCTCFEE.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTCFEE.INPUT_AREA.PRD_CODE = " ";
        BPCTCFEE.INPUT_AREA.CFEE_CCY = CMCS3030.TXN_CCY;
        BPCTCFEE.INPUT_AREA.BR = CICQACRI.O_DATA.O_OPN_BR;
        BPCTCFEE.INPUT_AREA.CHG_CCY = CMCS3030.TXN_CCY;
        BPCTCFEE.INPUT_AREA.CI_NO = WS_CI_NO;
        BPCTCFEE.INPUT_AREA.FEE_CODE = CMCS3030.FEE_DATA[WS_I-1].FEE_CD;
        BPCTCFEE.INPUT_AREA.AC_NO = CMCS3030.FEE_DATA[WS_I-1].FEE_AC;
        BPCTCFEE.INPUT_AREA.ACC_CNT = 1;
        BPCTCFEE.INPUT_AREA.FEE_AMT = CMCS3030.TXN_AMT;
        S000_CALL_BPZSCFEE();
        if (pgmRtn) return;
        CMCS3030.FEE_DATA[WS_I-1].FEE = BPCTCFEE.OUTPUT_AREA.CHG_AMT;
        CEP.TRC(SCCGWA, BPCTCFEE.OUTPUT_AREA.CHG_AMT);
    }
    public void B720_FEE_CHARGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS3030.FEE_DATA[WS_I-1].FEE_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS3030.TXN_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = CMCS3030.FEE_DATA[WS_I-1].FEE_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '4';
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = CMCS3030.TXN_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = CMCS3030.TXN_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = CMCS3030.FEE_DATA[WS_I-1].FEE_CD;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = CMCS3030.FEE_DATA[WS_I-1].FEE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = CMCS3030.FEE_DATA[WS_I-1].FEE - CMCS3030.FEE_DATA[WS_I-1].FEEC;
        if (WS_FEE_FLG == 'Y') {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = 0;
            BPCFFCON.FEE_INFO.DER_CODE = "A0514";
        } else {
            BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT;
        }
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
        WS_FEE_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT);
    }
    public void B800_INQ_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDCIQBAL);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS3030.CARD_NO;
        R000_INQ_ACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.trim().length() > 0) {
            DDCIQBAL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
        } else {
            DDCIQBAL.DATA.AC = CMCS3030.CARD_NO;
        }
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF040.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CMCF040.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
        CMCF040.CARD_BR = DDCIQBAL.DATA.OWNER_BR;
    }
    public void B900_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CMCF040.TRN_CCY = CMCS3030.TXN_CCY;
        CMCF040.TRN_AMT = CMCS3030.TXN_AMT;
        CMCF040.CARD_NO = CMCS3030.CARD_NO;
        CMCF040.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CMCF040.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
        CMCF040.AC_NM = WS_CI_NM;
        CMCF040.ID_TYP = WS_ID_TYP;
        CMCF040.ID_NO = WS_ID_NO;
        SCCFMT.FMTID = "CM303";
        SCCFMT.DATA_PTR = CMCF040;
        SCCFMT.DATA_LEN = 561;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void R000_INQ_ACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void R000_CHECK_CITY_FLG_FOR_DWK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS3030.CARD_NO;
        R000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCS3030.AC_NAME);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        if (CMCS3030.BUSI_KND == '0' 
            && CMCS3030.AC_NAME.trim().length() > 0 
            && !CMCS3030.AC_NAME.equalsIgnoreCase(CICQACRI.O_DATA.O_AC_CNM)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
        }
        if (CICQACRI.O_DATA.O_CI_TYP == '2') {
            if (CMCS3030.CITY_FLG == '0'
                || CMCS3030.CITY_FLG == '1') {
            } else if (CMCS3030.CITY_FLG == '2'
                || CMCS3030.CITY_FLG == '3'
                || CMCS3030.CITY_FLG == '4'
                || CMCS3030.CITY_FLG == '5') {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DWK_NOT_ALLOWED_FORG);
            } else {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
            }
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF ", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL ", CICQACRL, true);
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        if (CICQACRL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRL.RC);
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST ", DDCIMMST, true);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
        if (DDCIMMST.RC.RC_CODE != 0 
            && DDCIMMST.RC.RC_CODE != DD1401) {
            CEP.ERR(SCCGWA, DDCIMMST.RC);
        }
    }
    public void R000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void R000_CHK_CITY_FLG_BY_AREA_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUBRRC);
        DCCUBRRC.INP_DATA.FUNC = '0';
        DCCUBRRC.INP_DATA.CARD_NO = CMCS3030.CARD_NO;
        S000_CALL_DCZUBRRC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCS3030.CARD_NO);
        CEP.TRC(SCCGWA, DCCUBRRC.OUT_DATA.O_AREA_NO);
        CEP.TRC(SCCGWA, CMCS3030.AREA_CD);
        if (DCCUBRRC.OUT_DATA.O_AREA_NO == CMCS3030.AREA_CD) {
            CMCS3030.CITY_FLG = '0';
        } else {
            CMCS3030.CITY_FLG = '1';
        }
        CEP.TRC(SCCGWA, CMCS3030.CITY_FLG);
    }
    public void S000_CALL_DCZUBRRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUBRRC", DCCUBRRC, true);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        CEP.TRC(SCCGWA, BPCFFCON.RC.RC_CODE);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void S000_CALL_DCZUATMS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-ATMS-INF", DCCUATMS, true);
        CEP.TRC(SCCGWA, DCCUATMS.RC.RC_CODE);
        if (DCCUATMS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUATMS.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_CALL_DDZSQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQAC", DDCSQAC, true);
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
