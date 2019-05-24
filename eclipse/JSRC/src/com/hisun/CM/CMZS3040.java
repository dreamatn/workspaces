package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQMIB;
import com.hisun.BP.BPCFFCAL;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCONESF;
import com.hisun.BP.BPCPARMC;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCTCFEE;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.DD.DDCIQBAL;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CMZS3040 {
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE GLRP INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHGLRP";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_SYS_ERR = "SC6001";
    String WS_ERR_MSG = " ";
    String WS_CI_NM = " ";
    String WS_STL_AC_NM = " ";
    String WS_CI_NO = " ";
    String WS_PROD_NO = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    short WS_I = 0;
    double WS_AC_BAL = 0;
    double WS_AVA_BAL = 0;
    double WS_FEE_AMT = 0;
    String WS_TXN_TYPE = " ";
    char WS_FEE_FLG = ' ';
    char WS_CMTDELAY_FLG = ' ';
    CMZS3040_WS_TRIG_DATE WS_TRIG_DATE = new CMZS3040_WS_TRIG_DATE();
    int WS_TRIG_DATE_INT = 0;
    CMZS3040_WS_TRIG_TIME WS_TRIG_TIME = new CMZS3040_WS_TRIG_TIME();
    int WS_DAY_CNT = 0;
    String WS_TS = " ";
    int WS_TRIG_TIME_INT = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    char WS_TRF_TYP = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFFCON BPCFFCON = new BPCFFCON();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    BPCTCALF BPCTCALF = new BPCTCALF();
    CMRDELAY CMRDELAY = new CMRDELAY();
    TCRLOGR TCRLOGR = new TCRLOGR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICCUST CICCUST = new CICCUST();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CMCF040 CMCF040 = new CMCF040();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCTCFEE BPCTCFEE = new BPCTCFEE();
    BPCONESF BPCONESF = new BPCONESF();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    DCCUATMS DCCUATMS = new DCCUATMS();
    SCCGWA SCCGWA;
    SCCBINF SCCBINF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3040 CMCS3040;
    public void MP(SCCGWA SCCGWA, CMCS3040 CMCS3040) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3040 = CMCS3040;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3040 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF040);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, DDCIQBAL);
        CEP.TRC(SCCGWA, CMCS3040.BUSI_KND);
        CEP.TRC(SCCGWA, CMCS3040.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3040.CARD_NO);
        CEP.TRC(SCCGWA, CMCS3040.CARD_SEQ);
        CEP.TRC(SCCGWA, CMCS3040.OPP_AC);
        CEP.TRC(SCCGWA, CMCS3040.TXN_CCY);
        CEP.TRC(SCCGWA, CMCS3040.TXN_AMT);
        CEP.TRC(SCCGWA, CMCS3040.TXN_PSW);
        CEP.TRC(SCCGWA, CMCS3040.TXN_RMK);
        CEP.TRC(SCCGWA, CMCS3040.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS3040.TRK3_DAT);
        CEP.TRC(SCCGWA, CMCS3040.CVN);
        CEP.TRC(SCCGWA, CMCS3040.CITY_FLG);
        CEP.TRC(SCCGWA, CMCS3040.SLT_AC);
        CEP.TRC(SCCGWA, CMCS3040.SLT_NO);
        CEP.TRC(SCCGWA, CMCS3040.ID_FLG);
        CEP.TRC(SCCGWA, CMCS3040.ID_TYP);
        CEP.TRC(SCCGWA, CMCS3040.ID_NO);
        CEP.TRC(SCCGWA, CMCS3040.NM_FLG);
        CEP.TRC(SCCGWA, CMCS3040.CI_NM);
        CEP.TRC(SCCGWA, CMCS3040.TEL_FLG);
        CEP.TRC(SCCGWA, CMCS3040.TEL_NO);
        CEP.TRC(SCCGWA, CMCS3040.SMR);
        CEP.TRC(SCCGWA, CMCS3040.STL_DT);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_CHNL_JRN);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_CHNL_DATE);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_CHNL_TIME);
        CEP.TRC(SCCGWA, GWA_SC_AREA.CHNL_TR_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_IN_USE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_CHNL2);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CLEAR_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B150_CHK_RMK_CDE();
        if (pgmRtn) return;
        B200_INPUT_AC_CHECK();
        if (pgmRtn) return;
        B300_CARD_LIMIT_PROC();
        if (pgmRtn) return;
        B500_CARD_TRN_PROC();
        if (pgmRtn) return;
        if ((CMCS3040.BUSI_KND == '0' 
            && CMCS3040.TX_TYP == ' ') 
            || (CMCS3040.BUSI_KND == '4' 
            && CMCS3040.TX_TYP == ' ')) {
            B710_SET_FEE_INFO();
            if (pgmRtn) return;
            B720_FEE_CAL_ROC();
            if (pgmRtn) return;
            if (CMCS3040.TXN_RMK.equalsIgnoreCase("A340")) {
                B730_WRITE_DELAY_BOOK_A340();
                if (pgmRtn) return;
            }
        }
        B800_INQ_BAL_PROC();
        if (pgmRtn) return;
        B900_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMCS3040.BUSI_KND == ' ' 
            || CMCS3040.BUSI_KND == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_KND_ERR, CMCS3040.BUSI_KND);
        }
        if (CMCS3040.TXN_CCY.trim().length() == 0 
            || CMCS3040.TXN_CCY.charAt(0) == 0X00) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_ERR, CMCS3040.TXN_CCY);
        }
        if (CMCS3040.TXN_AMT <= 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
        }
        if (CMCS3040.ID_FLG == 'Y' 
            && ((CMCS3040.ID_TYP.trim().length() == 0 
            || CMCS3040.ID_TYP.charAt(0) == 0X00) 
            || (CMCS3040.ID_NO.trim().length() == 0 
            || CMCS3040.ID_NO.charAt(0) == 0X00))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ID_ERR);
        }
        if (CMCS3040.NM_FLG == 'Y' 
            && (CMCS3040.CI_NM.trim().length() == 0 
            || CMCS3040.CI_NM.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR, CMCS3040.CI_NM);
        }
        if (CMCS3040.TEL_FLG == 'Y' 
            && (CMCS3040.TEL_NO.trim().length() == 0 
            || CMCS3040.TEL_NO.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TEL_ERR, CMCS3040.TEL_NO);
        }
        if (CMCS3040.BUSI_KND == '0'
            || CMCS3040.BUSI_KND == '4') {
            WS_TXN_TYPE = "05";
        } else if (CMCS3040.BUSI_KND == '1') {
            WS_TXN_TYPE = "04";
        } else if (CMCS3040.BUSI_KND == '2') {
            WS_TXN_TYPE = "11";
        } else if (CMCS3040.BUSI_KND == '3') {
            WS_TXN_TYPE = "12";
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_INPUT_BUSI_TYPE, CMCS3040.BUSI_KND);
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
        if (CMCS3040.TXN_RMK == null) CMCS3040.TXN_RMK = "";
        JIBS_tmp_int = CMCS3040.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3040.TXN_RMK += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3040.TXN_RMK + BPCPARMC.KEY.CD.substring(6 + 4 - 1);
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
        CICCUST.DATA.AGR_NO = CMCS3040.CARD_NO;
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
        WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, CMCS3040.CITY_FLG);
        CEP.TRC(SCCGWA, CMCS3040.AREA_CD);
        if ((CMCS3040.CITY_FLG == ' ' 
            || CMCS3040.CITY_FLG == 0X00) 
            && (CMCS3040.AREA_CD.trim().length() == 0 
            || CMCS3040.AREA_CD.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AREA_CD_M_INPUT, CMCS3040.AREA_CD);
        }
        if (CMCS3040.CITY_FLG == ' ') {
            R000_CHK_CITY_FLG_BY_AREA_CD();
            if (pgmRtn) return;
        } else if (CMCS3040.CITY_FLG == '0'
            || CMCS3040.CITY_FLG == '1'
            || CMCS3040.CITY_FLG == '2'
            || CMCS3040.CITY_FLG == '3'
            || CMCS3040.CITY_FLG == '4'
            || CMCS3040.CITY_FLG == '5') {
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        R000_CHECK_CITY_FLG_FOR_DWK();
        if (pgmRtn) return;
        if (CMCS3040.TEL_FLG == 'Y' 
            && !CMCS3040.TEL_NO.equalsIgnoreCase(WS_TEL_NO)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TELNO_ERR, WS_TEL_NO);
        }
        CEP.TRC(SCCGWA, CMCS3040.CI_NM);
        CEP.TRC(SCCGWA, WS_CI_NM);
        if (CMCS3040.NM_FLG == 'Y' 
            && !CMCS3040.CI_NM.equalsIgnoreCase(WS_CI_NM)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CINM_ERR, WS_CI_NM);
        }
        if (CMCS3040.ID_FLG == 'Y' 
            && !CMCS3040.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDTYP_ERR, WS_ID_TYP);
        }
        if (CMCS3040.ID_FLG == 'Y') {
            if (!CMCS3040.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
                CEP.TRC(SCCGWA, "IDERR001");
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDTYP_ERR);
            }
            CEP.TRC(SCCGWA, WS_ID_NO);
            if (CMCS3040.ID_TYP.equalsIgnoreCase("10100") 
                && !CMCS3040.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
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
                if (!WS_ID_NO.substring(WS_I - 1, WS_I + 6 - 1).equalsIgnoreCase(CMCS3040.ID_NO)) {
                    CEP.TRC(SCCGWA, "IDERR003");
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDNO_ERR, WS_ID_NO);
                }
            }
        }
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = CMCS3040.SLT_AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_STL_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
    }
    public void B300_CARD_LIMIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUATMS);
        DCCUATMS.CARD_NO = CMCS3040.CARD_NO;
        DCCUATMS.FUNC = 'A';
        if (CMCS3040.CITY_FLG == '0') {
            DCCUATMS.TXN_TYPE = "01";
        } else if (CMCS3040.CITY_FLG == '1') {
            DCCUATMS.TXN_TYPE = "02";
        } else {
            DCCUATMS.TXN_TYPE = "03";
        }
        DCCUATMS.REGN_TYPE = "02";
        DCCUATMS.TC_FLG = "02";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUATMS.MONTH = JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1);
        DCCUATMS.TXN_AMT = CMCS3040.TXN_AMT;
        S000_CALL_DCZUATMS();
        if (pgmRtn) return;
    }
    public void B500_CARD_TRN_PROC() throws IOException,SQLException,Exception {
        if (CMCS3040.BUSI_KND == '0'
            && CMCS3040.TX_TYP == ' '
            || CMCS3040.BUSI_KND == '4'
            && CMCS3040.TX_TYP == ' '
            || CMCS3040.BUSI_KND == '3'
            && CMCS3040.TX_TYP == ' '
            || CMCS3040.BUSI_KND == '1'
            && CMCS3040.TX_TYP == '1'
            || CMCS3040.BUSI_KND == '2'
            && CMCS3040.TX_TYP == '1') {
            B510_AC_TRF_OUT_PROC();
            if (pgmRtn) return;
        } else if (CMCS3040.BUSI_KND == '0'
            && CMCS3040.TX_TYP == '1'
            || CMCS3040.BUSI_KND == '4'
            && CMCS3040.TX_TYP == '1'
            || CMCS3040.BUSI_KND == '3'
            && CMCS3040.TX_TYP == '1'
            || CMCS3040.BUSI_KND == '1'
            && CMCS3040.TX_TYP == ' '
            || CMCS3040.BUSI_KND == '2'
            && CMCS3040.TX_TYP == ' ') {
            B520_AC_TRF_IN_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TX_TYPE_INP_ERR);
        }
    }
    public void B510_AC_TRF_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        if (CMCS3040.TRK2_DAT.trim().length() > 0 
                && CMCS3040.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'B';
        } else if (CMCS3040.TRK2_DAT.trim().length() == 0 
                && CMCS3040.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'P';
            if (CMCS3040.BUSI_KND == '0') {
                CMCS3040.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3040.TRK2_DAT);
        } else if (CMCS3040.TRK2_DAT.trim().length() > 0 
                && CMCS3040.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3040.TRK2_DAT.trim().length() == 0 
                && CMCS3040.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'N';
            if (CMCS3040.BUSI_KND == '0') {
                CMCS3040.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3040.TRK2_DAT);
        } else {
        }
        DDCSTRAC.FR_CARD = CMCS3040.CARD_NO;
        DDCSTRAC.FR_CCY = CMCS3040.TXN_CCY;
        if (CMCS3040.TXN_CCY.equalsIgnoreCase("156")) {
            DDCSTRAC.FR_CCY_TYPE = '1';
            DDCSTRAC.TO_CCY_TYPE = '1';
        }
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY_TYPE);
        DDCSTRAC.FR_AMT = CMCS3040.TXN_AMT;
        DDCSTRAC.FR_AC_CNAME = WS_CI_NM;
        DDCSTRAC.PSWD = CMCS3040.TXN_PSW;
        DDCSTRAC.TRK2_DAT = CMCS3040.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = CMCS3040.TRK3_DAT;
        DDCSTRAC.TO_AC = CMCS3040.SLT_AC;
        DDCSTRAC.RLT_AC = CMCS3040.OPP_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3040.OPP_NM;
        DDCSTRAC.RLT_BANK = "" + CMCS3040.OPP_BR;
        JIBS_tmp_int = DDCSTRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCSTRAC.RLT_BANK = "0" + DDCSTRAC.RLT_BANK;
        DDCSTRAC.RLT_BK_NM = CMCS3040.OPP_BRNM;
        DDCSTRAC.TO_BV_TYPE = '3';
        DDCSTRAC.TO_CCY = CMCS3040.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3040.TXN_AMT;
        DDCSTRAC.TO_AC_CNAME = WS_STL_AC_NM;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCSTRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDCSTRAC.DR_MMO = CMCS3040.TXN_RMK;
        DDCSTRAC.CR_MMO = CMCS3040.TXN_RMK;
        DDCSTRAC.TX_RMK = CMCS3040.SMR;
        DDCSTRAC.REMARKS = CMCS3040.SMR;
        if (CMCS3040.CITY_FLG == '0'
            || CMCS3040.CITY_FLG == '1') {
            DDCSTRAC.TXN_REGION = '0';
        } else if (CMCS3040.CITY_FLG == '2') {
            DDCSTRAC.TXN_REGION = '1';
        } else if (CMCS3040.CITY_FLG == '3') {
            DDCSTRAC.TXN_REGION = '2';
        } else if (CMCS3040.CITY_FLG == '4') {
            DDCSTRAC.TXN_REGION = '3';
        } else if (CMCS3040.CITY_FLG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDCSTRAC.TXN_TYPE = "04";
        DDCSTRAC.IN_OUT_FLG = '1';
        DDCSTRAC.SNAME_FLG = 'N';
        DDCSTRAC.DNAME_FLG = 'Y';
        if (CMCS3040.TX_TYP == '1') {
            DDCSTRAC.CANCEL_FLG = 'Y';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B520_AC_TRF_IN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        DDCSTRAC.FR_AC = CMCS3040.SLT_AC;
        DDCSTRAC.FR_BV_TYPE = '3';
        DDCSTRAC.FR_CCY = CMCS3040.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3040.TXN_AMT;
        DDCSTRAC.FR_AC_CNAME = WS_STL_AC_NM;
        DDCSTRAC.TO_CARD = CMCS3040.CARD_NO;
        DDCSTRAC.RLT_AC = CMCS3040.CARD_NO;
        DDCSTRAC.TO_CCY = CMCS3040.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3040.TXN_AMT;
        DDCSTRAC.TO_AC_CNAME = WS_CI_NM;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCSTRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDCSTRAC.DR_MMO = CMCS3040.TXN_RMK;
        DDCSTRAC.CR_MMO = CMCS3040.TXN_RMK;
        DDCSTRAC.TX_RMK = CMCS3040.SMR;
        DDCSTRAC.REMARKS = CMCS3040.SMR;
        if (CMCS3040.CITY_FLG == '0'
            || CMCS3040.CITY_FLG == '1') {
            DDCSTRAC.TXN_REGION = '0';
        } else if (CMCS3040.CITY_FLG == '2') {
            DDCSTRAC.TXN_REGION = '1';
        } else if (CMCS3040.CITY_FLG == '3') {
            DDCSTRAC.TXN_REGION = '2';
        } else if (CMCS3040.CITY_FLG == '4') {
            DDCSTRAC.TXN_REGION = '3';
        } else if (CMCS3040.CITY_FLG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDCSTRAC.TXN_TYPE = "04";
        DDCSTRAC.IN_OUT_FLG = '2';
        DDCSTRAC.SNAME_FLG = 'N';
        DDCSTRAC.DNAME_FLG = 'Y';
        if (CMCS3040.TX_TYP == '1') {
            DDCSTRAC.CANCEL_FLG = 'Y';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B710_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS3040.CARD_NO;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS3040.CARD_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS3040.TXN_CCY;
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
        if (CMCS3040.CITY_FLG == '0'
            || CMCS3040.CITY_FLG == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = CMCS3040.CITY_FLG;
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "02";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else if (CMCS3040.CITY_FLG == '2'
            || CMCS3040.CITY_FLG == '3'
            || CMCS3040.CITY_FLG == '4'
            || CMCS3040.CITY_FLG == '5') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "03";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG);
        BPCTCALF.INPUT_AREA.TX_AC = CMCS3040.CARD_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        BPCTCALF.INPUT_AREA.TX_CCY = CMCS3040.TXN_CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = CMCS3040.TXN_AMT;
        BPCTCALF.INPUT_AREA.OTHER_AC = CMCS3040.OPP_AC;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = CICQACRI.O_DATA.O_PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE = CICQACRI.O_DATA.O_PROD_CD;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_PROD_CD);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.OTHER_AC);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_BAS);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].DER_AMT);
    }
    public void B730_WRITE_DELAY_BOOK_A340() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B740_WRITE_DELAY_BOOK();
            if (pgmRtn) return;
        } else {
            B750_READ_TCTLOGR();
            if (pgmRtn) return;
            B760_UPDATE_DELAY_BOOK();
            if (pgmRtn) return;
            B770_WRITE_DELAY_BOOK_REVERSE();
            if (pgmRtn) return;
        }
    }
    public void B740_WRITE_DELAY_BOOK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRDELAY);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_SYS_DATE);
        CEP.TRC(SCCGWA, CMCS3040.STL_DT);
        if ((GWA_SC_AREA.REQ_SYS_DATE != CMCS3040.STL_DT) 
            || (GWA_SC_AREA.REQ_SYS_DATE == ' ' 
            || GWA_SC_AREA.REQ_SYS_DATE == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_STL_DT_REQ_DT_ERR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_SYS_DATE);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_SYS_JRN);
        CMRDELAY.REQ_SYS = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRDELAY.REQ_SYS_DATE = GWA_SC_AREA.REQ_SYS_DATE;
        CMRDELAY.REQ_SYS_JRN = GWA_SC_AREA.REQ_SYS_JRN;
        T000_READ_CMTDELAY();
        if (pgmRtn) return;
        if (WS_CMTDELAY_FLG == 'Y') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DELAY_FOUND_ERR);
        }
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_DATE+"", WS_TRIG_DATE);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_TIME+"", WS_TRIG_TIME);
        WS_TRF_TYP = '3';
        if (WS_TRF_TYP == '1') {
            WS_TRIG_TIME.WS_TRIG_MIN += 5;
            if (WS_TRIG_TIME.WS_TRIG_MIN > 60) {
                WS_TRIG_TIME.WS_TRIG_MIN -= 60;
                WS_TRIG_TIME.WS_TRIG_HOUR += 1;
                if (WS_TRIG_TIME.WS_TRIG_HOUR > 24) {
                    WS_TRIG_TIME.WS_TRIG_HOUR -= 24;
                    WS_DAY_CNT += 1;
                }
            }
        } else if (WS_TRF_TYP == '3') {
            WS_DAY_CNT += 1;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRF_TYP_ERR);
        }
        CEP.TRC(SCCGWA, WS_DAY_CNT);
        if (WS_DAY_CNT >= 1) {
            IBS.init(SCCGWA, SCCCLDT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TRIG_DATE);
            SCCCLDT.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
            SCCCLDT.DAYS = 1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, SCCCLDT.DATE2+"", WS_TRIG_DATE);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TRIG_DATE);
        WS_TRIG_DATE_INT = Integer.parseInt(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TRIG_TIME);
        WS_TRIG_TIME_INT = Integer.parseInt(JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, WS_TRIG_DATE);
        CEP.TRC(SCCGWA, WS_TRIG_TIME);
        IBS.init(SCCGWA, CMRDELAY);
        CMRDELAY.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CMRDELAY.REQ_SYS = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CMRDELAY.REQ_SYS_DATE = GWA_SC_AREA.REQ_SYS_DATE;
        CMRDELAY.REQ_SYS_JRN = GWA_SC_AREA.REQ_SYS_JRN;
        CMRDELAY.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        CMRDELAY.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        CMRDELAY.TRF_TYP = '3';
        CMRDELAY.FR_CHNL = SCCGWA.COMM_AREA.CHNL;
        CMRDELAY.TR_STS = '0';
        CMRDELAY.TRIG_DATE = WS_TRIG_DATE_INT;
        CMRDELAY.TRIG_TIME = WS_TRIG_TIME_INT;
        CMRDELAY.HLD_NO = " ";
        if (DDCSTRAC.FR_CARD.trim().length() > 0) {
            CMRDELAY.TRO_AC = DDCSTRAC.FR_CARD;
        } else {
            CMRDELAY.TRO_AC = DDCSTRAC.FR_AC;
        }
        CMRDELAY.TRI_AC = DDCSTRAC.RLT_AC;
        CMRDELAY.CCY = DDCSTRAC.FR_CCY;
        CMRDELAY.CCY_TYP = DDCSTRAC.FR_CCY_TYPE;
        CMRDELAY.TRF_AMT = DDCSTRAC.FR_AMT;
        CMRDELAY.FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT;
        CMRDELAY.TRF_AC_DATE = 0;
        CMRDELAY.TRF_JRN_NO = 0;
        CMRDELAY.TRF_DATE = 0;
        CMRDELAY.TRF_TIME = 0;
        CMRDELAY.RET_CODE = " ";
        CMRDELAY.RET_MSG = " ";
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        if (GWA_SC_AREA.REQ_CHNL_JRN == null) GWA_SC_AREA.REQ_CHNL_JRN = "";
        JIBS_tmp_int = GWA_SC_AREA.REQ_CHNL_JRN.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) GWA_SC_AREA.REQ_CHNL_JRN += " ";
        CMRDELAY.BLOB_TRIG_DATA = GWA_SC_AREA.REQ_CHNL_JRN + CMRDELAY.BLOB_TRIG_DATA.substring(32);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        JIBS_tmp_str[0] = "" + GWA_SC_AREA.REQ_CHNL_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 33 - 1) + JIBS_tmp_str[0] + CMRDELAY.BLOB_TRIG_DATA.substring(33 + 8 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        JIBS_tmp_str[0] = "" + GWA_SC_AREA.REQ_CHNL_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 41 - 1) + JIBS_tmp_str[0] + CMRDELAY.BLOB_TRIG_DATA.substring(41 + 6 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        JIBS_tmp_str[0] = "" + CMCS3040.CITY_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 47 - 1) + JIBS_tmp_str[0] + CMRDELAY.BLOB_TRIG_DATA.substring(47 + 1 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        if (CMCS3040.OPP_AC == null) CMCS3040.OPP_AC = "";
        JIBS_tmp_int = CMCS3040.OPP_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3040.OPP_AC += " ";
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 48 - 1) + CMCS3040.OPP_AC + CMRDELAY.BLOB_TRIG_DATA.substring(48 + 32 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 80 - 1) + WS_CI_NO + CMRDELAY.BLOB_TRIG_DATA.substring(80 + 12 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        if (CMCS3040.TXN_RMK == null) CMCS3040.TXN_RMK = "";
        JIBS_tmp_int = CMCS3040.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3040.TXN_RMK += " ";
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 92 - 1) + CMCS3040.TXN_RMK + CMRDELAY.BLOB_TRIG_DATA.substring(92 + 4 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        if (DDCSTRAC.RLT_AC_NAME == null) DDCSTRAC.RLT_AC_NAME = "";
        JIBS_tmp_int = DDCSTRAC.RLT_AC_NAME.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) DDCSTRAC.RLT_AC_NAME += " ";
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 96 - 1) + DDCSTRAC.RLT_AC_NAME + CMRDELAY.BLOB_TRIG_DATA.substring(96 + 252 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRDELAY.BLOB_TRIG_DATA = CMRDELAY.BLOB_TRIG_DATA.substring(0, 4087 - 1) + JIBS_tmp_str[0] + CMRDELAY.BLOB_TRIG_DATA.substring(4087 + 6 - 1);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(0, 32));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(33 - 1, 33 + 8 - 1));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(41 - 1, 41 + 6 - 1));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(47 - 1, 47 + 1 - 1));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(48 - 1, 48 + 32 - 1));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(80 - 1, 80 + 12 - 1));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(92 - 1, 92 + 4 - 1));
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(96 - 1, 96 + 252 - 1));
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC_NAME);
        if (CMRDELAY.BLOB_TRIG_DATA == null) CMRDELAY.BLOB_TRIG_DATA = "";
        JIBS_tmp_int = CMRDELAY.BLOB_TRIG_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) CMRDELAY.BLOB_TRIG_DATA += " ";
        CEP.TRC(SCCGWA, CMRDELAY.BLOB_TRIG_DATA.substring(4087 - 1, 4087 + 6 - 1));
        CMRDELAY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMRDELAY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRDELAY.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, CMCS3040.CITY_FLG);
        CEP.TRC(SCCGWA, CMRDELAY.KEY.AC_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.KEY.JRN_NO);
        CEP.TRC(SCCGWA, CMRDELAY.REQ_SYS);
        CEP.TRC(SCCGWA, CMRDELAY.REQ_SYS_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.REQ_SYS_JRN);
        CEP.TRC(SCCGWA, CMRDELAY.TR_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.TR_TIME);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_TYP);
        CEP.TRC(SCCGWA, CMRDELAY.FR_CHNL);
        CEP.TRC(SCCGWA, CMRDELAY.TR_STS);
        CEP.TRC(SCCGWA, CMRDELAY.TRIG_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.TRIG_TIME);
        CEP.TRC(SCCGWA, CMRDELAY.HLD_NO);
        CEP.TRC(SCCGWA, CMRDELAY.TRO_AC);
        CEP.TRC(SCCGWA, CMRDELAY.TRI_AC);
        CEP.TRC(SCCGWA, CMRDELAY.CCY);
        CEP.TRC(SCCGWA, CMRDELAY.CCY_TYP);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_AMT);
        CEP.TRC(SCCGWA, CMRDELAY.FEE_AMT);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_AC_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_JRN_NO);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_DATE);
        CEP.TRC(SCCGWA, CMRDELAY.TRF_TIME);
        CEP.TRC(SCCGWA, CMRDELAY.RET_CODE);
        CEP.TRC(SCCGWA, CMRDELAY.RET_MSG);
