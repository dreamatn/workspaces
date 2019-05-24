package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.IB.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSBAAC {
    int JIBS_tmp_int;
    brParm GDTPLDR_BR = new brParm();
    DBParm GDTPLDR_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTMST_RD;
    DBParm GDTHIS_RD;
    DBParm DDTCCY_RD;
    DBParm GDTSTAC_RD;
    String K_LOC_CCY = "156";
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String K_OUTPUT_FMT = "DD880";
    String K_GUAOUT = "GUAOUT";
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    String WS_M_AC = " ";
    double WS_AMOUNT = 0;
    double WS_RAMT = 0;
    double WS_BACK_AMT = 0;
    double WS_MARGIN_INT = 0;
    double WS_PAYING_INT = 0;
    double WS_DR_SEC_DRAMT = 0;
    double WS_DR_SEC_DRAMT_I = 0;
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_INT_AC = " ";
    String WS_INT_AC1 = " ";
    String WS_EQ_BKID = " ";
    char WS_MPRD_TD_FLG = ' ';
    String WS_CZDQ_GD_AC = " ";
    int WS_CZDQ_GD_AC_SEQ = 0;
    String WS_CZDQ_GD_AC_TYP = " ";
    String WS_IB_AC1 = " ";
    String WS_IB_AC2 = " ";
    String WS_DD_AC1 = " ";
    String WS_DD_AC2 = " ";
    double WS_NEED_PLDR_AMT = 0;
    double WS_RELSE_AMT = 0;
    double WS_VAL_RBAL = 0;
    String WS_TXINT_AC = " ";
    String WS_DR_AC = " ";
    int WS_DR_SEQ = 0;
    String WS_OTHER_AC = " ";
    String WS_DR_AC_TYP = " ";
    char WS_DR_GD_FLG = ' ';
    String WS_DR_AC_YW_TYP = " ";
    double WS_DR_AC_AMT = 0;
    double WS_DR_AC_RELAT_AMT = 0;
    double WS_DR_AC_INT = 0;
    String WS_DR_AC_INT_AC = " ";
    char WS_PLDR_FLG = ' ';
    char WS_SUCCESS_RELEASE = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCRMST DDCRMST = new DDCRMST();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    GDCOTRAC GDCOTRAC = new GDCOTRAC();
    CICACCU CICACCU = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    GDCSCLDD GDCSCLDD = new GDCSCLDD();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    AICPQIA AICPQIA = new AICPQIA();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    TDCACE TDCACE = new TDCACE();
    AICPQMIB AICPQMIB = new AICPQMIB();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    IBCQRAC IBCQRAC = new IBCQRAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    double WS_ALL_RELAT_AMT = 0;
    double WS_KDGD_RELAT_AMT = 0;
    double WS_DR_SEC_PLDR_AMT = 0;
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    DDRMST DDRMST = new DDRMST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDCSTRAC GDCSTRAC;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, GDCSTRAC GDCSTRAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSTRAC = GDCSTRAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZSBAAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, "1;");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_PRE_CHECK();
        B020_PROCESS_DR_SECTION();
        if (WS_SUCCESS_RELEASE == 'Y') {
            B030_02_DEP_DD_PROC();
        }
        if (GDCSTRAC.TXALLO_F == '1') {
            B050_PROCESS_CZDQ();
        }
        B060_OUTPUT_DATA();
    }
    public void B010_PRE_CHECK() throws IOException,SQLException,Exception {
        WS_DR_SEC_DRAMT_I = GDCSTRAC.TXAMT;
    }
    public void B020_PROCESS_DR_SECTION() throws IOException,SQLException,Exception {
        T000_STARTBR_GDTPLDR();
        T000_READNEXT_GDTPLDR();
        if (WS_PLDR_FLG == 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_DR_AC = GDRPLDR.KEY.AC;
        WS_DR_SEQ = GDRPLDR.KEY.AC_SEQ;
        B021_INQUIRE_AC_APP();
        B022_CHECK_STS_AND_RESET_AC_AMT();
        if (WS_MPRD_TD_FLG == '0') {
            B024_RELEASE_PLDR();
            R000_CHECK_AVAIL_AMT();
            if (WS_DR_SEC_DRAMT > 0 
                && WS_DR_SEC_PLDR_AMT > 0) {
                B023_PROCESS_DR_AC();
            }
        } else {
            while (WS_PLDR_FLG != 'N') {
                WS_DR_AC = GDRPLDR.KEY.AC;
                WS_DR_SEQ = GDRPLDR.KEY.AC_SEQ;
                B021_INQUIRE_AC_APP();
                WS_DR_SEC_PLDR_AMT = GDRPLDR.RELAT_AMT;
                B024_RELEASE_PLDR();
                R000_CHECK_AVAIL_AMT();
                if (WS_DR_SEC_DRAMT > 0 
                    && WS_DR_SEC_PLDR_AMT > 0) {
                    B023_PROCESS_DR_AC();
                }
                T000_READNEXT_GDTPLDR();
            }
        }
        T000_ENDBR_GDTPLDR();
    }
    public void B021_INQUIRE_AC_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_DR_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, WS_DR_AC);
        if (CICQACRI.RC.RC_CODE == 0) {
            WS_DR_AC_TYP = CICQACRI.O_DATA.O_FRM_APP;
        }
        if (WS_DR_AC_TYP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = WS_DR_AC;
            T000_READ_DDTMST();
            CEP.TRC(SCCGWA, DDRMST.YW_TYP);
            WS_DR_AC_YW_TYP = DDRMST.YW_TYP;
            B015_CHECK_GDKD_PROC();
        } else if (WS_DR_AC_TYP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = WS_DR_AC;
            T000_READ_TDTCMST();
            CEP.TRC(SCCGWA, TDRCMST.REF_TYP);
            WS_DR_AC_YW_TYP = TDRCMST.REF_TYP;
        } else {
        }
        WS_DR_GD_FLG = 'N';
        if ((WS_DR_AC_YW_TYP.equalsIgnoreCase("01") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("02") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("03") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("04") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("07") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("09") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("10") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("15") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("16") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("17") 
            || WS_DR_AC_YW_TYP.equalsIgnoreCase("18"))) {
            WS_DR_GD_FLG = 'Y';
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_DR_AC;
        DDRCCY.CCY = GDCSTRAC.TXCCY;
        DDRCCY.CCY_TYPE = GDCSTRAC.TXTYP;
        T000_READUP_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSTRAC.TXCCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSTRAC.TXCCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        WS_MPRD_TD_FLG = DDVMPRD.VAL.TD_FLG;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B022_CHECK_STS_AND_RESET_AC_AMT() throws IOException,SQLException,Exception {
        if (WS_DR_GD_FLG == 'Y') {
            GDRPLDR.DEAL_CD = GDCSTRAC.TXCTA_NO;
            GDRPLDR.BSREF = GDCSTRAC.TXREF_NO;
            GDRPLDR.RELAT_STS = 'N';
            T000_GROUP_AMT_GDTPLDR();
            CEP.TRC(SCCGWA, WS_DR_SEC_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_DR_SEC_DRAMT_I);
            if (WS_DR_SEC_PLDR_AMT != 0) {
                if (WS_DR_SEC_PLDR_AMT != WS_DR_SEC_DRAMT_I) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void R000_CHECK_AVAIL_AMT() throws IOException,SQLException,Exception {
        if (WS_DR_AC_TYP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = WS_DR_AC;
            DDRCCY.CCY = GDCSTRAC.TXCCY;
            DDRCCY.CCY_TYPE = GDCSTRAC.TXTYP;
            T000_READUP_DDTCCY();
            WS_DR_SEC_DRAMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL;
        } else if (WS_DR_AC_TYP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = WS_DR_AC;
            CICQACAC.DATA.AGR_SEQ = WS_DR_SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_TDTSMST_BY_ACOAC();
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            WS_DR_SEC_DRAMT = TDRSMST.BAL - TDRSMST.HBAL;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_DR_AC_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B023_PROCESS_DR_AC() throws IOException,SQLException,Exception {
        if (WS_DR_AC_TYP.equalsIgnoreCase("DD")) {
            B023_01_PROCESS_DD();
        } else if (WS_DR_AC_TYP.equalsIgnoreCase("TD")) {
            B023_02_PROCESS_TD();
        } else {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B023_01_PROCESS_DD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = WS_DR_AC;
        DDCUDRAC.CCY = GDCSTRAC.TXCCY;
        DDCUDRAC.CCY_TYPE = GDCSTRAC.TXTYP;
        DDCUDRAC.TX_AMT = GDCSTRAC.TXAMT;
        DDCUDRAC.OTHER_AC = GDCSTRAC.TXTDD_AC;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.REMARKS = GDCSTRAC.TXSMR;
        if (GDCSTRAC.TXMMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "A019";
        } else {
            DDCUDRAC.TX_MMO = GDCSTRAC.TXMMO;
        }
        S000_CALL_DDZUDRAC();
        WS_PAYING_INT = DDCUDRAC.MARGIN_INT;
        if (WS_PAYING_INT > 0) {
            WS_DR_SEQ = 0;
            R000_GET_INT_AC();
            R000_CR_INT_PROC();
        }
    }
    public void B023_02_PROCESS_TD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACDRU);
        TDCACDRU.INRT_MTH = '2';
        TDCACDRU.MAC_CNO = WS_DR_AC;
        TDCACDRU.AC_SEQ = WS_DR_SEQ;
        TDCACDRU.CCY = GDCSTRAC.TXCCY;
        TDCACDRU.CCY_TYP = GDCSTRAC.TXTYP;
        TDCACDRU.TXN_AMT = WS_DR_SEC_PLDR_AMT;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
        CEP.TRC(SCCGWA, TDCACDRU.OPT);
        CEP.TRC(SCCGWA, TDCACDRU.BAL);
        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
        TDCACDRU.PRDMO_CD = "ALL";
        TDCACDRU.OPP_AC_CNO = GDCSTRAC.TXTDD_AC;
        if (GDCSTRAC.TXSTLT == '3') {
            TDCACDRU.CT_FLG = '2';
        } else {
            TDCACDRU.CT_FLG = GDCSTRAC.TXSTLT;
        }
        if (GDCSTRAC.TXMMO.trim().length() == 0) {
            TDCACDRU.TXN_MMO = "A019";
        } else {
            TDCACDRU.TXN_MMO = GDCSTRAC.TXMMO;
        }
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        if (TDRSMST.BAL > WS_DR_SEC_PLDR_AMT) {
            TDCACDRU.OPT = '8';
        } else {
            TDCACDRU.OPT = '9';
        }
        S000_CALL_TDZACDRU();
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        WS_PAYING_INT = TDCACDRU.PAYING_INT;
        if (WS_PAYING_INT > 0) {
            R000_GET_INT_AC();
            R000_CR_INT_PROC();
        }
    }
    public void R000_CR_INT_PROC() throws IOException,SQLException,Exception {
        if ((WS_TXINT_AC.trim().length() > 0) 
            || GDCSTRAC.INT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            if (GDCSTRAC.INT_AC.trim().length() > 0) {
                DDCUCRAC.AC = GDCSTRAC.INT_AC;
            } else {
                DDCUCRAC.AC = WS_TXINT_AC;
            }
            DDCUCRAC.CCY = GDCSTRAC.TXCCY;
            DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
            DDCUCRAC.TX_AMT = WS_PAYING_INT;
            DDCUCRAC.OTHER_AC = WS_DR_AC;
            WS_OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
            DDCUCRAC.REMARKS = GDCSTRAC.TXSMR;
            DDCUCRAC.TX_MMO = "A001";
            S000_CALL_DDZUCRAC();
        }
    }
    public void B024_RELEASE_PLDR() throws IOException,SQLException,Exception {
        if (WS_DR_AC_TYP.equalsIgnoreCase("DD") 
                && WS_MPRD_TD_FLG != '0') {
            B024_01_RELEASE_DD_PLDR();
        } else if (WS_DR_AC_TYP.equalsIgnoreCase("TD")) {
            B024_02_RELEASE_TD_PLDR();
        } else if (WS_DR_AC_TYP.equalsIgnoreCase("DD") 
                && WS_MPRD_TD_FLG == '0') {
            B024_03_RELEASE_ALL_PLDR();
        }
    }
    public void B024_01_RELEASE_DD_PLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.AC = GDRPLDR.KEY.AC;
        S000_CALL_GDZSRLSR();
        WS_SUCCESS_RELEASE = 'Y';
    }
    public void B024_02_RELEASE_TD_PLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.AC = GDRPLDR.KEY.AC;
        GDCSRLSR.VAL.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        S000_CALL_GDZSRLSR();
        WS_SUCCESS_RELEASE = 'Y';
    }
    public void B024_03_RELEASE_ALL_PLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.AC = GDRPLDR.KEY.AC;
        GDCSRLSR.VAL.CTA_NO = GDRPLDR.DEAL_CD;
        GDCSRLSR.VAL.REF_NO = GDRPLDR.BSREF;
        S000_CALL_GDZSRLSR();
        WS_SUCCESS_RELEASE = 'Y';
    }
    public void B030_02_DEP_DD_PROC() throws IOException,SQLException,Exception {
        if (GDCSTRAC.TXSTLT == '3' 
            && GDCSTRAC.TXAMT > 0) {
            CEP.TRC(SCCGWA, "PAY STRAC-TXSTLT = 3");
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSTRAC.TXTDD_AC;
            T000_READ_DDTMST();
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDRMST.AC_TYPE == 'N') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DEPOSIT_AC_INVALID;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = GDCSTRAC.TXTDD_AC;
            DDCUCRAC.CCY = GDCSTRAC.TXCCY;
            DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
            DDCUCRAC.TX_AMT = GDCSTRAC.TXAMT;
            DDCUCRAC.OTHER_AC = WS_DR_AC;
            WS_OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
            if (GDCSTRAC.TXMMO.trim().length() > 0) {
                DDCUCRAC.TX_MMO = GDCSTRAC.TXMMO;
            } else {
                DDCUCRAC.TX_MMO = "A001";
            }
            DDCUCRAC.REMARKS = GDCSTRAC.TXSMR;
            S000_CALL_DDZUCRAC();
        }
    }
    public void B050_PROCESS_CZDQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = GDCSTRAC.TXALLO_B;
        S000_CALL_BPZPQORG();
        WS_EQ_BKID = BPCPQORG.VIL_TYP;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "04";
        S000_CALL_IBZQRAC();
        WS_IB_AC1 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "03";
        S000_CALL_IBZQRAC();
        WS_IB_AC2 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.AC = WS_IB_AC2;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = GDCSTRAC.TXPN_AMT;
        IBCPOSTA.OTH_AC_NO = WS_IB_AC1;
        WS_OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZCRAC();
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.AC = WS_IB_AC1;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = GDCSTRAC.TXPN_AMT;
        IBCPOSTA.OTH_AC_NO = WS_IB_AC2;
        WS_OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZDRAC();
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "01";
        S000_CALL_IBZQRAC();
        WS_DD_AC1 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "02";
        S000_CALL_IBZQRAC();
        WS_DD_AC2 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'C';
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = WS_DD_AC1;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = GDCSTRAC.TXPN_AMT;
        DDCUCRAC.OTHER_AC = WS_DD_AC2;
        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.REMARKS = GDCSTRAC.TXSMR;
        DDCUCRAC.TX_MMO = "A001";
        S000_CALL_DDZUCRAC();
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = WS_DD_AC2;
        DDCUDRAC.CCY = "156";
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = GDCSTRAC.TXPN_AMT;
        DDCUDRAC.OTHER_AC = WS_DD_AC1;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.REMARKS = GDCSTRAC.TXSMR;
        DDCUDRAC.TX_MMO = "A019";
        S000_CALL_DDZUDRAC();
    }
    public void R000_GET_INT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = WS_DR_AC;
        GDRSTAC.KEY.AC_SEQ = WS_DR_SEQ;
        T000_READ_GDTSTAC();
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC);
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        if (GDRSTAC.INT_AC.trim().length() > 0) {
            WS_TXINT_AC = GDRSTAC.INT_AC;
        } else {
            WS_TXINT_AC = GDRSTAC.ST_AC;
        }
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        CEP.TRC(SCCGWA, WS_TXINT_AC);
        if (WS_TXINT_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INTAC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B170_02_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTHER_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, BPCPQORG);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_OTHER_AC;
            S000_CALL_AIZPQMIB();
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
    }
    public void B060_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOTRAC);
        GDCOTRAC.TXFUCTYP = GDCSTRAC.TXFUCTYP;
        GDCOTRAC.CI_NO = CICACCU.DATA.CI_NO;
        GDCOTRAC.CI_CNM = CICACCU.DATA.CI_CNM;
        GDCOTRAC.TXRSEQ = GDCSTRAC.TXRSEQ;
        GDCOTRAC.TXAC = WS_DR_AC;
        GDCOTRAC.TXTMAC = GDRPLDR.KEY.AC;
        GDCOTRAC.TXSEQ = GDRPLDR.KEY.AC_SEQ;
        GDCOTRAC.TXCCY = GDCSTRAC.TXCCY;
        GDCOTRAC.TXTYP = GDCSTRAC.TXTYP;
        GDCOTRAC.TXCTA_NO = GDCSTRAC.TXCTA_NO;
        GDCOTRAC.TXREF_NO = GDCSTRAC.TXREF_NO;
        GDCOTRAC.RELAT_AMT = WS_DR_AC_RELAT_AMT;
        GDCOTRAC.TXAMT = GDCSTRAC.TXAMT;
        CEP.TRC(SCCGWA, GDCOTRAC.RELAT_AMT);
        CEP.TRC(SCCGWA, GDCOTRAC.TXAMT);
        GDCOTRAC.TXTDD_AC = GDCSTRAC.TXTDD_AC;
        CEP.TRC(SCCGWA, GDCSTRAC.INT_AC);
        GDCOTRAC.TXMMO = GDCSTRAC.TXMMO;
        GDCOTRAC.TXSMR = GDCSTRAC.TXSMR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCOTRAC;
        SCCFMT.DATA_LEN = 856;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        WS_PLDR_FLG = 'Y';
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.DEAL_CD = GDCSTRAC.TXCTA_NO;
        GDRPLDR.BSREF = GDCSTRAC.TXREF_NO;
        GDRPLDR.KEY.RSEQ = GDCSTRAC.TXRSEQ;
        if (GDRPLDR.KEY.RSEQ.trim().length() == 0) {
            GDTPLDR_BR.rp = new DBParm();
            GDTPLDR_BR.rp.TableName = "GDTPLDR";
            GDTPLDR_BR.rp.where = "RELAT_STS = 'N' "
                + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
                + "AND BSREF = :GDRPLDR.BSREF";
            GDTPLDR_BR.rp.upd = true;
            GDTPLDR_BR.rp.order = "AC,AC_SEQ DESC";
            IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        } else {
            GDTPLDR_BR.rp = new DBParm();
            GDTPLDR_BR.rp.TableName = "GDTPLDR";
            GDTPLDR_BR.rp.where = "RELAT_STS = 'N' "
                + "AND RSEQ = :GDRPLDR.KEY.RSEQ";
            GDTPLDR_BR.rp.upd = true;
            GDTPLDR_BR.rp.order = "AC,AC_SEQ DESC";
            IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLDR_FLG = 'Y';
        } else {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_GROUP_AMT_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-DR-SEC-PLDR-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZSCLDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSCLDD", GDCSCLDD);
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAAAAAAAA");
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        CEP.TRC(SCCGWA, "BBBBBBBBBB");
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        if (GDCSTRAC.TXRSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDCSTRAC.TXRSEQ;
        } else {
            GDRPLDR.DEAL_CD = GDCSTRAC.TXCTA_NO;
            GDRPLDR.BSREF = GDCSTRAC.TXREF_NO;
        }
        if (GDCSTRAC.TXAC.trim().length() == 0) {
            GDRPLDR.KEY.AC = GDCSTRAC.TXTMAC;
            GDRPLDR.KEY.AC_SEQ = GDCSTRAC.TXSEQ;
        } else {
            GDRPLDR.KEY.AC = GDCSTRAC.TXAC;
        }
        CEP.TRC(SCCGWA, GDCSTRAC.TXSEQ);
        CEP.TRC(SCCGWA, GDCSTRAC.TXRSEQ);
        CEP.TRC(SCCGWA, GDCSTRAC.TXCTA_NO);
        CEP.TRC(SCCGWA, GDCSTRAC.TXREF_NO);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        if (GDCSTRAC.TXRSEQ.trim().length() > 0) {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.upd = true;
            IBS.READ(SCCGWA, GDRPLDR, GDTPLDR_RD);
        } else {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
                + "AND BSREF = :GDRPLDR.BSREF "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
            GDTPLDR_RD.upd = true;
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        if (GDCSTRAC.TXAC.trim().length() == 0) {
            GDRPLDR.KEY.AC = GDCSTRAC.TXTMAC;
            GDRPLDR.KEY.AC_SEQ = GDCSTRAC.TXSEQ;
        } else {
            GDRPLDR.KEY.AC = GDCSTRAC.TXAC;
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_GROUP_KD_GDTPLDR() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCSTRAC.TXAC;
        GDRPLDR.KEY.AC_SEQ = GDCSTRAC.TXSEQ;
        GDRPLDR.RELAT_STS = 'N';
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-KDGD-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_REWRITE_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.REWRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
    }
    public void T000_WRITE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READUP_TDTSMST_BY_ACOAC() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ";
        IBS.READ(SCCGWA, GDRSTAC, this, GDTSTAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR, true);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_IBZQRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-INQ-RELA-AC", IBCQRAC, true);
        CEP.TRC(SCCGWA, IBCQRAC.RC);
        if (IBCQRAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQRAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA, true);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZDRAC", IBCPOSTA, true);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
