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

public class GDZSTRAC {
    int JIBS_tmp_int;
    brParm GDTPLDR_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm GDTPLDR_RD;
    DBParm GDTHIS_RD;
    DBParm GDTSTAC_RD;
    String K_LOC_CCY = "156";
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String K_OUTPUT_FMT = "DD880";
    String K_GUAOUT = "GUAOUT";
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    double WS_TX_AMT_LEFT = 0;
    double WS_TX_AMT_TMP = 0;
    String WS_M_AC = " ";
    double WS_AMOUNT = 0;
    double WS_RAMT = 0;
    double WS_BACK_AMT = 0;
    double WS_MARGIN_INT = 0;
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_INT_AC = " ";
    String WS_INT_AC1 = " ";
    String WS_EQ_BKID = " ";
    String WS_IB_AC1 = " ";
    String WS_IB_AC2 = " ";
    String WS_DD_AC1 = " ";
    String WS_DD_AC2 = " ";
    String WS_OTHER_AC = " ";
    double WS_NEED_PLDR_AMT = 0;
    double WS_RELSE_AMT = 0;
    double WS_VAL_RBAL = 0;
    char WS_PLDR_FLG = ' ';
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
    AICPQIA AICPQIA = new AICPQIA();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    TDCACE TDCACE = new TDCACE();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    IBCQRAC IBCQRAC = new IBCQRAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    double WS_ALL_RELAT_AMT = 0;
    double WS_KDGD_RELAT_AMT = 0;
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
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
        CEP.TRC(SCCGWA, "GDZSTRAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (GDCSTRAC.TXFUCTYP == '1') {
            B010_DEPOSIT_TRANSFER_PAY_PROC();
        } else if (GDCSTRAC.TXFUCTYP == '3') {
            B020_DEPOSIT_TRANSFER_BACK_PRO();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCSTRAC.TXFUCTYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSTRAC.TXRSEQ);
        if (GDCSTRAC.TXFUCTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUNC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSTRAC.TXRSEQ.trim().length() == 0 
            && GDCSTRAC.TXCTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSCL_BOTH_SPACE;
            S000_ERR_MSG_PROC();
        }
        if (GDCSTRAC.TXAC.trim().length() > 0 
            && GDCSTRAC.TXSEQ == 0) {
            WS_AC = GDCSTRAC.TXAC;
        }
        if (GDCSTRAC.TXAC.trim().length() > 0 
            && GDCSTRAC.TXSEQ != 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_SEQ_NO_NEED_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSTRAC.TXAC.trim().length() > 0 
            && GDCSTRAC.TXTMAC.trim().length() > 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_ONLY_ONE;
            S000_ERR_MSG_PROC();
        }
        if (GDCSTRAC.TXTMAC.trim().length() > 0 
            && GDCSTRAC.TXSEQ == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_SEQ_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSTRAC.TXTMAC.trim().length() > 0 
            && GDCSTRAC.TXSEQ != 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = GDCSTRAC.TXTMAC;
            CICQACAC.DATA.AGR_SEQ = GDCSTRAC.TXSEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            WS_M_AC = GDCSTRAC.TXTMAC;
        }
        if (GDCSTRAC.INT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSTRAC.INT_AC;
            WS_INT_AC1 = GDCSTRAC.INT_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            WS_INT_AC = CICQACRI.O_DATA.O_AGR_NO;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = WS_INT_AC;
            S000_CALL_CIZACCU();
            WS_CI_NO1 = CICACCU.DATA.CI_NO;
            IBS.init(SCCGWA, CICACCU);
            if ((GDCSTRAC.TXTMAC.trim().length() > 0) 
                && (GDCSTRAC.TXSEQ != 0)) {
                CICACCU.DATA.AGR_NO = GDCSTRAC.TXTMAC;
            } else {
                if (GDCSTRAC.TXAC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = GDCSTRAC.TXAC;
                }
            }
            S000_CALL_CIZACCU();
            WS_CI_NO2 = CICACCU.DATA.CI_NO;
            CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = WS_AC;
                T000_READUP_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
                if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2)) {
                    if (!GDCSTRAC.INT_AC.equalsIgnoreCase(TDRSMST.OPEN_DR_AC)) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
    }
    public void B010_DEPOSIT_TRANSFER_PAY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111111111111111");
        if (GDCSTRAC.TXAC.trim().length() == 0 
            && GDCSTRAC.TXTMAC.trim().length() == 0) {
            IBS.init(SCCGWA, GDRPLDR);
            WS_PLDR_FLG = ' ';
            GDRPLDR.KEY.RSEQ = GDCSTRAC.TXRSEQ;
            GDRPLDR.DEAL_CD = GDCSTRAC.TXCTA_NO;
            GDRPLDR.BSREF = GDCSTRAC.TXREF_NO;
            WS_TX_AMT_LEFT = GDCSTRAC.TXAMT;
            if (GDRPLDR.KEY.RSEQ.trim().length() > 0) {
                T000_STARTBR_GDTPLDR_NOAC();
            } else {
                T000_STARTBR_GDTPLDR_NOAC1();
            }
            T000_READNEXT_GDTPLDR();
            while (WS_PLDR_FLG != 'N' 
                && WS_TX_AMT_LEFT != 0) {
                if (GDRPLDR.KEY.AC_SEQ != 0) {
                    GDCSTRAC.TXTMAC = GDRPLDR.KEY.AC;
                    GDCSTRAC.TXSEQ = GDRPLDR.KEY.AC_SEQ;
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.AGR_NO = GDCSTRAC.TXTMAC;
                    CICQACAC.DATA.AGR_SEQ = GDCSTRAC.TXSEQ;
                    CICQACAC.FUNC = 'R';
                    S000_CALL_CIZQACAC();
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                    WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                    WS_M_AC = GDCSTRAC.TXTMAC;
                } else {
                    GDCSTRAC.TXAC = GDRPLDR.KEY.AC;
                    WS_AC = GDRPLDR.KEY.AC;
                }
                GDCSTRAC.TXCCY = GDRPLDR.CCY;
                GDCSTRAC.TXTYP = GDRPLDR.CCY_TYP;
                R000_DEBIT_AC_PROC1();
                R000_PLDR_RLS_PROC();
                T000_READNEXT_GDTPLDR();
                WS_TX_AMT_LEFT = WS_TX_AMT_LEFT - WS_TX_AMT_TMP;
            }
            T000_ENDBR_GDTPLDR();
        } else {
            R000_DEBIT_AC_PROC();
            R000_PLDR_RLS_PROC();
        }
        R000_CREDIT_AC_PROC();
    }
    public void R000_DEBIT_AC_PROC1() throws IOException,SQLException,Exception {
        if (GDCSTRAC.TXSEQ == 0) {
            B015_CHECK_GDKD_PROC();
            if (DDVMPRD.VAL.TD_FLG == '0') {
                B025_GDKD_PLDR_PROC();
                WS_TX_AMT_LEFT = WS_NEED_PLDR_AMT;
            }
        }
        if (DDVMPRD.VAL.TD_FLG != '0') {
            T000_READUP_GDTPLDR();
            GDCOTRAC.RELAT_AMT = GDRPLDR.RELAT_AMT;
            if (WS_TX_AMT_LEFT > GDRPLDR.RELAT_AMT) {
                WS_TX_AMT_TMP = GDRPLDR.RELAT_AMT;
            } else {
                WS_TX_AMT_TMP = WS_TX_AMT_LEFT;
            }
            if (GDRPLDR.AC_TYP == '0') {
                DDCRMST.FUNC = 'I';
                DDRMST.KEY.CUS_AC = WS_AC;
                DDCRMST.REC_PTR = DDRMST;
                DDCRMST.REC_LEN = 425;
                S000_CALL_DDZRMST();
                T000_READUP_DDTCCY();
                CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HOLD;
                    S000_ERR_MSG_PROC();
                }
            }
            if (GDRPLDR.AC_TYP == '1') {
                T000_READUP_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.STSW);
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, "222222222222222");
            if (GDRPLDR.AC_TYP == '0') {
                IBS.init(SCCGWA, DDCUDRAC);
                if (GDCSTRAC.TXEINT_F == '0') {
                    DDCUDRAC.CLEAR_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
                CEP.TRC(SCCGWA, WS_AC);
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.GD_WITHDR_FLG = 'Y';
                DDCUDRAC.AC = WS_AC;
                DDCUDRAC.CCY = GDCSTRAC.TXCCY;
                DDCUDRAC.CCY_TYPE = GDCSTRAC.TXTYP;
                DDCUDRAC.TX_AMT = WS_TX_AMT_TMP;
                DDCUDRAC.OTHER_AC = GDCSTRAC.TXTDD_AC;
                WS_OTHER_AC = DDCUDRAC.OTHER_AC;
                if (WS_OTHER_AC.trim().length() > 0) {
                    B170_02_GET_RLT_BR_INFO();
                    DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
                    DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
                    JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
                    DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
                }
                DDCUDRAC.REMARKS = GDCSTRAC.TXSMR;
                T000_READUP_DDTCCY();
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - WS_TX_AMT_TMP;
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, WS_TX_AMT_TMP);
                T000_REWRITE_DDTCCY();
                if (GDCSTRAC.TXMMO.trim().length() == 0) {
                    DDCUDRAC.TX_MMO = "A019";
                } else {
                    DDCUDRAC.TX_MMO = GDCSTRAC.TXMMO;
                }
                S000_CALL_DDZUDRAC();
                WS_MARGIN_INT += DDCUDRAC.MARGIN_INT;
                CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
            }
            if (GDRPLDR.AC_TYP == '1') {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.INRT_MTH = '2';
                TDCACDRU.AC_SEQ = GDCSTRAC.TXSEQ;
                TDCACDRU.MAC_CNO = GDCSTRAC.TXTMAC;
                CEP.TRC(SCCGWA, GDCSTRAC.TXTMAC);
                TDCACDRU.CCY = GDCSTRAC.TXCCY;
                TDCACDRU.CCY_TYP = GDCSTRAC.TXTYP;
                TDCACDRU.TXN_AMT = WS_TX_AMT_TMP;
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                CEP.TRC(SCCGWA, TDCACDRU.OPT);
                CEP.TRC(SCCGWA, WS_TX_AMT_TMP);
                CEP.TRC(SCCGWA, TDCACDRU.BAL);
                CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                TDCACDRU.PRDMO_CD = "ALL";
                if (GDCSTRAC.TXMMO.trim().length() == 0) {
                    TDCACDRU.TXN_MMO = "A019";
                } else {
                    TDCACDRU.TXN_MMO = GDCSTRAC.TXMMO;
                }
                TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - WS_TX_AMT_TMP;
                if (TDRSMST.BAL > WS_TX_AMT_TMP) {
                    TDCACDRU.OPT = '8';
                } else {
                    TDCACDRU.OPT = '9';
                }
                T000_REWRITE_TDTSMST();
                S000_CALL_TDZACDRU();
                if (TDCACDRU.INT_AC.trim().length() > 0) {
                    WS_INT_AC1 = TDCACDRU.INT_AC;
                }
                CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                if (TDCACDRU.PAYING_INT != 0) {
                    B028_CR_INTAC_PROC();
                    if (GDCSTRAC.INT_AC.trim().length() == 0) {
                        IBS.init(SCCGWA, AICPQIA);
                        AICPQIA.CD.AC_TYP = "2";
                        AICPQIA.CD.BUSI_KND = K_GUAOUT;
                        AICPQIA.BR = TDRSMST.OWNER_BR;
                        AICPQIA.CCY = GDCSTRAC.TXCCY;
                        AICPQIA.SIGN = 'C';
                        S000_CALL_AIZPQIA();
                        CEP.TRC(SCCGWA, AICPQIA.AC);
                        IBS.init(SCCGWA, AICUUPIA);
                        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                        AICUUPIA.DATA.RVS_SEQ = 0;
                        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
                        AICUUPIA.DATA.CCY = GDCSTRAC.TXCCY;
                        AICUUPIA.DATA.EVENT_CODE = "CR";
                        AICUUPIA.DATA.POST_NARR = " ";
                        AICUUPIA.DATA.RVS_NO = " ";
                        AICUUPIA.DATA.EVENT_CODE = "CR";
                        S000_CALL_AIZUUPIA();
                    }
                    if ((GDCSTRAC.INT_AC.trim().length() > 0) 
                        || (TDCACDRU.INT_AC.trim().length() > 0)) {
                        IBS.init(SCCGWA, DDCUCRAC);
                        DDCUCRAC.TX_TYPE = 'T';
                        DDCUCRAC.AC = WS_INT_AC1;
                        DDCUCRAC.CCY = GDCSTRAC.TXCCY;
                        DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
                        DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
                        DDCUCRAC.OTHER_AC = GDCSTRAC.TXTMAC;
                        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
                        B170_02_GET_RLT_BR_INFO();
                        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
                        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
                        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
                        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
                        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
                        DDCUCRAC.TX_MMO = "A001";
                        S000_CALL_DDZUCRAC();
                    }
                }
            }
        }
    }
    public void R000_DEBIT_AC_PROC() throws IOException,SQLException,Exception {
        if (GDCSTRAC.TXSEQ == 0) {
            B015_CHECK_GDKD_PROC();
            if (DDVMPRD.VAL.TD_FLG == '0') {
                B025_GDKD_PLDR_PROC();
            }
        }
        if (DDVMPRD.VAL.TD_FLG != '0') {
            T000_READUP_GDTPLDR();
            if (!GDRPLDR.RELAT_CHNL_CD.equalsIgnoreCase(SCCGWA.COMM_AREA.REQ_SYSTEM) 
                && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("030500")) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANNEL_ERR;
                S000_ERR_MSG_PROC();
            }
            GDCOTRAC.RELAT_AMT = GDRPLDR.RELAT_AMT;
            if (GDRPLDR.RELAT_STS == 'R') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STS_ALREADY_RELEASE;
                S000_ERR_MSG_PROC();
            }
            if (GDRPLDR.RELAT_STS == 'N') {
                GDRPLDR.RELAT_STS = 'N';
                CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
                T000_GROUP_GDTPLDR();
                CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
                WS_RAMT = WS_ALL_RELAT_AMT;
            }
            CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            if (GDCSTRAC.TXAMT > GDRPLDR.RELAT_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                S000_ERR_MSG_PROC();
            }
            if (GDRPLDR.AC_TYP == '0') {
                DDCRMST.FUNC = 'I';
                DDRMST.KEY.CUS_AC = WS_AC;
                DDCRMST.REC_PTR = DDRMST;
                DDCRMST.REC_LEN = 425;
                S000_CALL_DDZRMST();
                T000_READUP_DDTCCY();
                CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HOLD;
                    S000_ERR_MSG_PROC();
                }
            }
            if (GDRPLDR.AC_TYP == '1') {
                T000_READUP_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.STSW);
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
                    S000_ERR_MSG_PROC();
                }
                WS_AMOUNT = 0;
                WS_AMOUNT = TDRSMST.BAL - TDRSMST.HBAL;
                CEP.TRC(SCCGWA, WS_AMOUNT);
                CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
                CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
                if (WS_AMOUNT < GDCSTRAC.TXAMT) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, GDRPLDR.REF_TYP);
                WS_AMOUNT = 0;
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                CEP.TRC(SCCGWA, TDRSMST.HBAL);
                CEP.TRC(SCCGWA, WS_RAMT);
                WS_AMOUNT = ( TDRSMST.BAL - TDRSMST.HBAL );
                CEP.TRC(SCCGWA, WS_AMOUNT);
                CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
                if (WS_AMOUNT < GDCSTRAC.TXAMT) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, "222222222222222");
            if (GDRPLDR.AC_TYP == '0') {
                WS_AMOUNT = 0;
                CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
                CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
                WS_AMOUNT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
                CEP.TRC(SCCGWA, WS_AMOUNT);
                CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
                if (WS_AMOUNT < GDCSTRAC.TXAMT) {
                    CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN ");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                    S000_ERR_MSG_PROC();
                }
                WS_AMOUNT = 0;
                WS_AMOUNT = ( DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL );
                if (WS_AMOUNT < GDCSTRAC.TXAMT) {
                    CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN AVL");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, DDCUDRAC);
                if (GDCSTRAC.TXEINT_F == '0') {
                    DDCUDRAC.CLEAR_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
                CEP.TRC(SCCGWA, WS_AC);
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.GD_WITHDR_FLG = 'Y';
                DDCUDRAC.AC = WS_AC;
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
                T000_READUP_DDTCCY();
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - GDCSTRAC.TXAMT;
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
                if (DDRCCY.MARGIN_BAL < 0) {
                    S000_ERR_MSG_PROC();
                }
                T000_REWRITE_DDTCCY();
                if (GDCSTRAC.TXMMO.trim().length() == 0) {
                    DDCUDRAC.TX_MMO = "A019";
                } else {
                    DDCUDRAC.TX_MMO = GDCSTRAC.TXMMO;
                }
                S000_CALL_DDZUDRAC();
                WS_MARGIN_INT = DDCUDRAC.MARGIN_INT;
                CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
            }
            if (GDRPLDR.AC_TYP == '1') {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.INRT_MTH = '2';
                TDCACDRU.AC_SEQ = GDCSTRAC.TXSEQ;
                TDCACDRU.MAC_CNO = GDCSTRAC.TXTMAC;
                CEP.TRC(SCCGWA, GDCSTRAC.TXTMAC);
                TDCACDRU.CCY = GDCSTRAC.TXCCY;
                TDCACDRU.CCY_TYP = GDCSTRAC.TXTYP;
                TDCACDRU.TXN_AMT = GDCSTRAC.TXAMT;
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                CEP.TRC(SCCGWA, TDCACDRU.OPT);
                CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
                CEP.TRC(SCCGWA, TDCACDRU.BAL);
                CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                TDCACDRU.PRDMO_CD = "ALL";
                if (GDCSTRAC.TXMMO.trim().length() == 0) {
                    TDCACDRU.TXN_MMO = "A019";
                } else {
                    TDCACDRU.TXN_MMO = GDCSTRAC.TXMMO;
                }
                TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - GDCSTRAC.TXAMT;
                if (TDRSMST.BAL > GDCSTRAC.TXAMT) {
                    TDCACDRU.OPT = '8';
                } else {
                    TDCACDRU.OPT = '9';
                }
                T000_REWRITE_TDTSMST();
                S000_CALL_TDZACDRU();
                if (TDCACDRU.INT_AC.trim().length() > 0) {
                    WS_INT_AC1 = TDCACDRU.INT_AC;
                }
                CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                if (TDCACDRU.PAYING_INT != 0) {
                    B028_CR_INTAC_PROC();
                    if (GDCSTRAC.INT_AC.trim().length() == 0) {
                        IBS.init(SCCGWA, AICPQIA);
                        AICPQIA.CD.AC_TYP = "2";
                        AICPQIA.CD.BUSI_KND = K_GUAOUT;
                        AICPQIA.BR = TDRSMST.OWNER_BR;
                        AICPQIA.CCY = GDCSTRAC.TXCCY;
                        AICPQIA.SIGN = 'C';
                        S000_CALL_AIZPQIA();
                        CEP.TRC(SCCGWA, AICPQIA.AC);
                        IBS.init(SCCGWA, AICUUPIA);
                        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                        AICUUPIA.DATA.RVS_SEQ = 0;
                        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
                        AICUUPIA.DATA.CCY = GDCSTRAC.TXCCY;
                        AICUUPIA.DATA.EVENT_CODE = "CR";
                        AICUUPIA.DATA.POST_NARR = " ";
                        AICUUPIA.DATA.RVS_NO = " ";
                        AICUUPIA.DATA.EVENT_CODE = "CR";
                        S000_CALL_AIZUUPIA();
                    }
                    if ((GDCSTRAC.INT_AC.trim().length() > 0) 
                        || (TDCACDRU.INT_AC.trim().length() > 0)) {
                        IBS.init(SCCGWA, DDCUCRAC);
                        DDCUCRAC.TX_TYPE = 'T';
                        DDCUCRAC.AC = WS_INT_AC1;
                        DDCUCRAC.CCY = GDCSTRAC.TXCCY;
                        DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
                        DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
                        DDCUCRAC.OTHER_AC = GDCSTRAC.TXTMAC;
                        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
                        B170_02_GET_RLT_BR_INFO();
                        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
                        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
                        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
                        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
                        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
                        DDCUCRAC.TX_MMO = "A001";
                        S000_CALL_DDZUCRAC();
                    }
                }
            }
        }
    }
    public void R000_CREDIT_AC_PROC() throws IOException,SQLException,Exception {
        if (GDCSTRAC.TXSTLT == '3') {
            CEP.TRC(SCCGWA, "PAY STRAC-TXSTLT = 3");
            WS_AC = GDCSTRAC.TXTDD_AC;
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSTRAC.TXTDD_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
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
            if (WS_M_AC.trim().length() == 0) {
                DDCUCRAC.OTHER_AC = GDCSTRAC.TXAC;
            } else {
                DDCUCRAC.OTHER_AC = WS_M_AC;
            }
            WS_OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
            DDCUCRAC.TX_MMO = "A001";
            S000_CALL_DDZUCRAC();
        }
        if (GDCSTRAC.TXSTLT == '1') {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = GDCSTRAC.TXTDD_AC;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = GDCSTRAC.TXAMT;
            AICUUPIA.DATA.CCY = GDCSTRAC.TXCCY;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.POST_NARR = " ";
            if (GDCSTRAC.TXSEQ != 0) {
                AICUUPIA.DATA.PROD_CODE = "CDP00605";
            } else {
                AICUUPIA.DATA.PROD_CODE = "CDP00581";
            }
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.CCY);
            S000_CALL_AIZUUPIA();
        }
        if (GDCSTRAC.TXINT_F == '1' 
            && WS_MARGIN_INT > 0 
            && DDVMPRD.VAL.TD_FLG != '0') {
            B028_CR_INTAC_PROC();
            B025_GDKD_INTCR_PROC();
        }
        if (GDCSTRAC.CANL_FLG == '1') {
            B026_CANCLE_AC_PROC();
        }
    }
    public void R000_PLDR_RLS_PROC() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.TD_FLG != '0') {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT - GDCSTRAC.TXAMT;
            if (GDRPLDR.RELAT_AMT == 0) {
                GDRPLDR.RELAT_STS = 'R';
            }
            GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_GDTPLDR();
            GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
            GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
            GDRHIS.RSEQ = GDCSTRAC.TXRSEQ;
            if (GDCSTRAC.TXTMAC.trim().length() > 0 
                && GDCSTRAC.TXSEQ != 0) {
                GDRHIS.AC = GDCSTRAC.TXTMAC;
                GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
            } else {
                GDRHIS.AC = GDCSTRAC.TXAC;
            }
            GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
            GDRHIS.FUNC = '7';
            GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
            GDRHIS.BSREF = GDRPLDR.BSREF;
            GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDRHIS.TR_AMT = GDCSTRAC.TXAMT;
            GDRHIS.TR_AC = GDCSTRAC.TXTDD_AC;
            GDRHIS.CAN_FLG = 'N';
            GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_GDTHIS();
        }
    }
    public void B020_DEPOSIT_TRANSFER_BACK_PRO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "3333333333333333");
        if (GDCSTRAC.TXSEQ == 0) {
            B015_CHECK_GDKD_PROC();
            if (DDVMPRD.VAL.TD_FLG == '0') {
                B025_GDKD_PLDR_PROC();
            }
        }
        if (DDVMPRD.VAL.TD_FLG != '0') {
            T000_READUP_GDTPLDR();
            if (!GDRPLDR.RELAT_CHNL_CD.equalsIgnoreCase(SCCGWA.COMM_AREA.REQ_SYSTEM) 
                && !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("030500")) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANNEL_ERR;
                S000_ERR_MSG_PROC();
            }
            if (GDRPLDR.RELAT_AMT != GDCSTRAC.TXAMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
            WS_BACK_AMT = GDRPLDR.RELAT_AMT;
            if (GDRPLDR.RELAT_STS == 'R') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STS_ALREADY_RELEASE;
                S000_ERR_MSG_PROC();
            }
            if (GDRPLDR.RELAT_STS == 'N') {
                GDRPLDR.RELAT_STS = 'N';
                CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
                CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
                T000_GROUP_GDTPLDR();
                CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
                WS_RAMT = WS_ALL_RELAT_AMT;
            }
            if (GDCSTRAC.TXAMT > GDRPLDR.RELAT_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                S000_ERR_MSG_PROC();
            }
            if (GDRPLDR.AC_TYP == '0') {
                DDCRMST.FUNC = 'I';
                DDRMST.KEY.CUS_AC = WS_AC;
                DDCRMST.REC_PTR = DDRMST;
                DDCRMST.REC_LEN = 425;
                S000_CALL_DDZRMST();
                T000_READUP_DDTCCY();
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HOLD;
                    S000_ERR_MSG_PROC();
                }
            }
            if (GDRPLDR.AC_TYP == '1') {
                T000_READUP_TDTSMST();
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
                    S000_ERR_MSG_PROC();
                }
                WS_AMOUNT = 0;
                WS_AMOUNT = TDRSMST.BAL - TDRSMST.HBAL;
                if (WS_AMOUNT < WS_BACK_AMT) {
                    CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN ");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                    S000_ERR_MSG_PROC();
                }
                WS_AMOUNT = 0;
                WS_AMOUNT = ( TDRSMST.BAL - TDRSMST.HBAL );
                CEP.TRC(SCCGWA, WS_AMOUNT);
                CEP.TRC(SCCGWA, WS_BACK_AMT);
                if (WS_AMOUNT < WS_BACK_AMT) {
                    CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN AVL");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, "444444444444444444");
        if (GDRPLDR.AC_TYP == '0' 
            && DDVMPRD.VAL.TD_FLG != '0') {
            T000_READ_DDTCCY();
            WS_AMOUNT = 0;
            WS_AMOUNT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
            CEP.TRC(SCCGWA, WS_AMOUNT);
            CEP.TRC(SCCGWA, WS_BACK_AMT);
            if (WS_AMOUNT < WS_BACK_AMT 
                || WS_BACK_AMT > DDRCCY.MARGIN_BAL) {
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, WS_AC);
            CEP.TRC(SCCGWA, WS_BACK_AMT);
            if (WS_BACK_AMT != 0) {
                IBS.init(SCCGWA, DDCUDRAC);
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.GD_WITHDR_FLG = 'Y';
                DDCUDRAC.AC = GDRPLDR.KEY.AC;
                DDCUDRAC.CCY = GDRPLDR.CCY;
                DDCUDRAC.CCY_TYPE = GDCSTRAC.TXTYP;
                DDCUDRAC.TX_AMT = WS_BACK_AMT;
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
                    DDCUDRAC.TX_MMO = "A401";
                } else {
                    DDCUDRAC.TX_MMO = GDCSTRAC.TXMMO;
                }
                S000_CALL_DDZUDRAC();
                T000_READUP_DDTCCY();
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - GDRPLDR.RELAT_AMT;
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
                if (DDRCCY.MARGIN_BAL < 0) {
                    S000_ERR_MSG_PROC();
                }
                T000_REWRITE_DDTCCY();
            }
        }
        if (GDRPLDR.AC_TYP == '1' 
            && GDRPLDR.RELAT_AMT != 0) {
            IBS.init(SCCGWA, TDCACDRU);
            TDCACDRU.AC_SEQ = GDCSTRAC.TXSEQ;
            TDCACDRU.MAC_CNO = WS_M_AC;
            TDCACDRU.CCY = GDRPLDR.CCY;
            TDCACDRU.CCY_TYP = GDCSTRAC.TXTYP;
            TDCACDRU.TXN_AMT = GDRPLDR.RELAT_AMT;
            TDCACDRU.PRDMO_CD = "ALL";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
            if (GDCSTRAC.TXMMO.trim().length() == 0) {
                TDCACDRU.TXN_MMO = "A019";
            } else {
                TDCACDRU.TXN_MMO = GDCSTRAC.TXMMO;
            }
            TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - GDRPLDR.RELAT_AMT;
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            CEP.TRC(SCCGWA, WS_BACK_AMT);
            if (TDRSMST.BAL > GDRPLDR.RELAT_AMT) {
                TDCACDRU.OPT = '8';
            } else {
                TDCACDRU.OPT = '9';
            }
            if (GDCSTRAC.CANL_FLG != ' ' 
                && GDCSTRAC.TXINT_F != ' ') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INT_CAN_INVAILD;
                S000_ERR_MSG_PROC();
            }
            B028_CR_INTAC_PROC();
            if (GDCSTRAC.CANL_FLG == '1') {
                TDCACDRU.OPT = '9';
            } else {
                TDCACDRU.OPT = '8';
            }
            T000_REWRITE_TDTSMST();
            S000_CALL_TDZACDRU();
            if (TDCACDRU.INT_AC.trim().length() > 0) {
                WS_INT_AC1 = TDCACDRU.INT_AC;
            }
            if (TDCACDRU.PAYING_INT != 0) {
                if (GDCSTRAC.INT_AC.trim().length() == 0) {
                    IBS.init(SCCGWA, AICPQIA);
                    AICPQIA.CD.AC_TYP = "2";
                    AICPQIA.CD.BUSI_KND = K_GUAOUT;
                    AICPQIA.BR = TDRSMST.OWNER_BR;
                    AICPQIA.CCY = GDCSTRAC.TXCCY;
                    AICPQIA.SIGN = 'C';
                    S000_CALL_AIZPQIA();
                    CEP.TRC(SCCGWA, AICPQIA.AC);
                    IBS.init(SCCGWA, AICUUPIA);
                    AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                    AICUUPIA.DATA.RVS_SEQ = 0;
                    AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
                    AICUUPIA.DATA.CCY = GDCSTRAC.TXCCY;
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    AICUUPIA.DATA.POST_NARR = " ";
                    AICUUPIA.DATA.RVS_NO = " ";
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    S000_CALL_AIZUUPIA();
                }
                if ((GDCSTRAC.INT_AC.trim().length() > 0) 
                    || (TDCACDRU.INT_AC.trim().length() > 0)) {
                    IBS.init(SCCGWA, DDCUCRAC);
                    DDCUCRAC.TX_TYPE = 'T';
                    DDCUCRAC.AC = GDCSTRAC.INT_AC;
                    DDCUCRAC.CCY = GDCSTRAC.TXCCY;
                    DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
                    DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
                    DDCUCRAC.OTHER_AC = GDCSTRAC.TXTDD_AC;
                    DDCUCRAC.TX_MMO = "A001";
                    S000_CALL_DDZUCRAC();
                }
            }
        }
        CEP.TRC(SCCGWA, "CR AC START");
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        if (GDCSTRAC.TXSTLT == '3' 
            && GDCSTRAC.TXAMT != 0) {
            CEP.TRC(SCCGWA, "BACK STRAC-TXSTLT = 3");
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSTRAC.TXTDD_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDRMST.AC_TYPE == 'N') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DEPOSIT_AC_INVALID;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = GDCSTRAC.TXTDD_AC;
            DDCUCRAC.CCY = GDRPLDR.CCY;
            DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
            DDCUCRAC.TX_AMT = GDCSTRAC.TXAMT;
            DDCUCRAC.OTHER_AC = GDCSTRAC.TXTDD_AC;
            DDCUCRAC.TX_MMO = "A001";
            S000_CALL_DDZUCRAC();
        }
        if (GDCSTRAC.TXSTLT == '1' 
            && GDRPLDR.RELAT_AMT != 0) {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = GDCSTRAC.TXTDD_AC;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = GDRPLDR.RELAT_AMT;
            AICUUPIA.DATA.CCY = GDRPLDR.CCY;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.POST_NARR = " ";
            AICUUPIA.DATA.RVS_NO = " ";
            S000_CALL_AIZUUPIA();
        }
        if (DDVMPRD.VAL.TD_FLG != '0') {
            GDRPLDR.RELAT_AMT = 0;
            GDRPLDR.RELAT_STS = 'R';
            T000_REWRITE_GDTPLDR();
            GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
            GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
            GDRHIS.RSEQ = GDCSTRAC.TXRSEQ;
            if (GDCSTRAC.TXTMAC.trim().length() > 0 
                && GDCSTRAC.TXSEQ != 0) {
                GDRHIS.AC = GDCSTRAC.TXTMAC;
                GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
            } else {
                GDRHIS.AC = GDCSTRAC.TXAC;
            }
            GDRHIS.FUNC = '8';
            GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
            GDRHIS.BSREF = GDRPLDR.BSREF;
            GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDRHIS.TR_AMT = WS_BACK_AMT;
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            GDRHIS.TR_AC = GDCSTRAC.TXTDD_AC;
            GDRHIS.CAN_FLG = 'N';
            GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_GDTHIS();
        }
        if (GDCSTRAC.TXALLO_F == '1') {
            B025_ALLO_IB_PROC();
        }
        if (GDCSTRAC.TXINT_F == '1' 
            && WS_MARGIN_INT > 0 
            && DDVMPRD.VAL.TD_FLG != '0') {
            B028_CR_INTAC_PROC();
            B025_GDKD_INTCR_PROC();
        }
        if (GDCSTRAC.CANL_FLG == '1') {
            B026_CANCLE_AC_PROC();
        }
    }
    public void B025_ALLO_IB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSTRAC.TXAC;
        S000_CALL_CIZQACRI();
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = GDCSTRAC.TXAC;
            DDCSCINM.INPUT_DATA.FUNC = '2';
            S000_CALL_DDZSCINM();
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = DDCSCINM.OUTPUT_DATA.OWNER_BR;
            S000_CALL_BPZPQORG();
            WS_EQ_BKID = BPCPQORG.VIL_TYP;
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = GDCSTRAC.TXAC;
                TDCACE.PAGE_INF.I_AC_SEQ = GDCSTRAC.TXSEQ;
                DDCSCINM.INPUT_DATA.FUNC = '2';
                S000_CALL_TDZACE();
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = TDCACE.DATA[1-1].CHE_BR;
                S000_CALL_BPZPQORG();
                WS_EQ_BKID = BPCPQORG.VIL_TYP;
            }
        }
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
        IBCPOSTA.AC = WS_IB_AC2;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = GDCSTRAC.TXPN_AMT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = WS_IB_AC1;
        WS_OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZCRAC();
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = WS_IB_AC1;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = GDCSTRAC.TXPN_AMT;
        IBCPOSTA.ENTRY_FLG = '1';
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
    public void B026_CANCLE_AC_PROC() throws IOException,SQLException,Exception {
        if (GDRPLDR.AC_TYP == '0') {
            IBS.init(SCCGWA, GDCSCLDD);
            GDCSCLDD.DRAC = GDCSTRAC.TXAC;
            GDCSCLDD.CCY = GDCSTRAC.TXCCY;
            GDCSCLDD.CCY_TYP = GDCSTRAC.TXTYP;
            GDCSCLDD.STLT = GDCSTRAC.TXSTLT;
            GDCSCLDD.CRAC = GDCSTRAC.RMN_AC;
            GDCSCLDD.MMO = GDCSTRAC.TXMMO;
            GDCSCLDD.RMK = GDCSTRAC.TXSMR;
            S000_CALL_GDZSCLDD();
        } else {
            if (GDRPLDR.AC_TYP == '1') {
                IBS.init(SCCGWA, TDCACDRU);
                TDCACDRU.BV_TYP = '0';
                TDCACDRU.MAC_CNO = GDCSTRAC.TXTMAC;
                TDCACDRU.AC_SEQ = GDCSTRAC.TXSEQ;
                TDCACDRU.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    TDCACDRU.CCY_TYP = '1';
                } else {
                    TDCACDRU.CCY_TYP = '2';
                }
                TDCACDRU.TXN_AMT = GDCSTRAC.TXAMT;
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.DRAW_MTH = '4';
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                TDCACDRU.TXN_MMO = "A019";
                TDCACDRU.OPP_AC_CNO = GDCSTRAC.RMN_AC;
                TDCACDRU.CT_FLG = GDCSTRAC.TXSTLT;
                TDCACDRU.OPT = '9';
                S000_CALL_TDZACDRU();
            }
        }
    }
    public void B028_CR_INTAC_PROC() throws IOException,SQLException,Exception {
        if (GDRPLDR.AC_TYP == '0') {
            CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
            if (DDVMPRD.VAL.TD_FLG == '0') {
                IBS.init(SCCGWA, GDRSTAC);
                GDRSTAC.KEY.AC = GDRPLDR.KEY.AC;
                T000_READ_GDTSTAC();
                CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
                CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
                if (GDRSTAC.INT_AC.trim().length() == 0) {
                    GDCSTRAC.INT_AC = GDRSTAC.ST_AC;
                } else {
                    GDCSTRAC.INT_AC = GDRSTAC.INT_AC;
                }
                if (GDCSTRAC.INT_AC.trim().length() == 0) {
                    GDCSTRAC.INT_AC = GDRPLDR.KEY.AC;
                }
            }
        } else {
            if (GDRPLDR.AC_TYP == '1') {
                if (GDCSTRAC.INT_AC.trim().length() == 0) {
                    IBS.init(SCCGWA, GDRSTAC);
                    GDRSTAC.KEY.AC = GDRPLDR.KEY.AC;
                    GDRSTAC.KEY.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
                    T000_READ_GDTSTAC();
                    CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
                    CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
                    if (GDRSTAC.INT_AC.trim().length() == 0) {
                        GDCSTRAC.INT_AC = GDRSTAC.ST_AC;
                    } else {
                        GDCSTRAC.INT_AC = GDRSTAC.INT_AC;
                    }
                }
            }
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSTRAC.TXAC);
        CEP.TRC(SCCGWA, GDCSTRAC.TXCCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSTRAC.TXAC;
        DDRCCY.CCY = GDCSTRAC.TXCCY;
        DDRCCY.CCY_TYPE = GDCSTRAC.TXTYP;
        T000_READUP_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSTRAC.TXCCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSTRAC.TXCCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        T000_GROUP_KD_GDTPLDR();
        CEP.TRC(SCCGWA, WS_KDGD_RELAT_AMT);
        CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
        if (WS_KDGD_RELAT_AMT < GDCSTRAC.TXAMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
            S000_ERR_MSG_PROC();
        }
        WS_NEED_PLDR_AMT = GDCSTRAC.TXAMT;
        GDRPLDR.KEY.AC = GDCSTRAC.TXAC;
        GDRPLDR.DEAL_CD = GDCSTRAC.TXCTA_NO;
        GDRPLDR.BSREF = GDCSTRAC.TXREF_NO;
        T000_STARTBR_GDTPLDR();
        T000_READNEXT_GDTPLDR();
        while (WS_PLDR_FLG != 'N') {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            if (GDRPLDR.RELAT_AMT > WS_NEED_PLDR_AMT) {
                WS_RELSE_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELSE_AMT = GDRPLDR.RELAT_AMT;
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'S';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_TDTSMST();
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
            if (GDCSTRAC.TXFUCTYP == '1') {
                B020_RELEASE_GDKD_PROC();
            } else {
                B020_BACK_PLDR_PROC();
            }
            TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - WS_RELSE_AMT;
            T000_REWRITE_TDTSMST();
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELSE_AMT;
            T000_READNEXT_GDTPLDR();
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
        }
        T000_ENDBR_GDTPLDR();
        if (GDCSTRAC.TXAMT > 0) {
            B020_GDKD_DRPLDR_PROC();
        }
        if (WS_MARGIN_INT > 0) {
            B028_CR_INTAC_PROC();
            B025_GDKD_INTCR_PROC();
        }
    }
    public void B020_RELEASE_GDKD_PROC() throws IOException,SQLException,Exception {
        GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT - WS_RELSE_AMT;
        if (GDRPLDR.RELAT_AMT == 0) {
            GDRPLDR.RELAT_STS = 'R';
        }
        GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_GDTPLDR();
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDCSTRAC.TXRSEQ;
        if (GDCSTRAC.TXTMAC.trim().length() > 0 
            && GDCSTRAC.TXSEQ != 0) {
            GDRHIS.AC = GDCSTRAC.TXTMAC;
            GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
        } else {
            GDRHIS.AC = GDCSTRAC.TXAC;
        }
        GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
        GDRHIS.FUNC = '7';
        GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
        GDRHIS.BSREF = GDRPLDR.BSREF;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = WS_RELSE_AMT;
        GDRHIS.TR_AC = GDCSTRAC.TXTDD_AC;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_GDTHIS();
    }
    public void B020_BACK_PLDR_PROC() throws IOException,SQLException,Exception {
        GDRPLDR.RELAT_AMT = 0;
        GDRPLDR.RELAT_STS = 'R';
        GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_GDTPLDR();
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDCSTRAC.TXRSEQ;
        if (GDCSTRAC.TXTMAC.trim().length() > 0 
            && GDCSTRAC.TXSEQ != 0) {
            GDRHIS.AC = GDCSTRAC.TXTMAC;
            GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
        } else {
            GDRHIS.AC = GDCSTRAC.TXAC;
        }
        GDRHIS.AC_SEQ = GDCSTRAC.TXSEQ;
        GDRHIS.FUNC = '8';
        GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
        GDRHIS.BSREF = GDRPLDR.BSREF;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = WS_RELSE_AMT;
        GDRHIS.TR_AC = GDCSTRAC.TXTDD_AC;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_GDTHIS();
    }
    public void B020_GDKD_DRPLDR_PROC() throws IOException,SQLException,Exception {
        WS_AC = GDCSTRAC.TXAC;
        T000_READUP_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        WS_VAL_RBAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        if (WS_VAL_RBAL < WS_RELSE_AMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            S000_ERR_MSG_PROC();
        }
        WS_AMOUNT = 0;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        WS_AMOUNT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, WS_AMOUNT);
        CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        if (WS_AMOUNT < GDCSTRAC.TXAMT) {
            CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN ");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
            S000_ERR_MSG_PROC();
        }
        WS_AMOUNT = 0;
        WS_AMOUNT = ( DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL );
        if (WS_AMOUNT < GDCSTRAC.TXAMT) {
            CEP.TRC(SCCGWA, "STRAC-TXAMT LARGER THAN AVL");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GDCSTRAC.TXAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCSTRAC.TXAC;
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
        T000_READUP_DDTCCY();
        WS_MARGIN_INT = DDCUDRAC.MARGIN_INT;
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - GDCSTRAC.TXAMT;
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, GDCSTRAC.TXAMT);
        if (DDRCCY.MARGIN_BAL < 0) {
            S000_ERR_MSG_PROC();
        }
        T000_REWRITE_DDTCCY();
    }
    public void B025_GDKD_INTCR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = GDCSTRAC.INT_AC;
        DDCUCRAC.CCY = GDCSTRAC.TXCCY;
        if (GDCSTRAC.TXCCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = GDCSTRAC.TXTYP;
        }
        DDCUCRAC.TX_AMT = WS_MARGIN_INT;
        DDCUCRAC.OTHER_AC = GDCSTRAC.TXAC;
        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.REMARKS = GDCSTRAC.TXSMR;
        DDCUCRAC.TX_MMO = "S101";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUCRAC();
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOTRAC);
        GDCOTRAC.TXFUCTYP = GDCSTRAC.TXFUCTYP;
        GDCOTRAC.CI_NO = CICACCU.DATA.CI_NO;
        GDCOTRAC.CI_CNM = CICACCU.DATA.CI_CNM;
        GDCOTRAC.TXRSEQ = GDCSTRAC.TXRSEQ;
        GDCOTRAC.TXAC = GDCSTRAC.TXAC;
        GDCOTRAC.TXTMAC = WS_M_AC;
        GDCOTRAC.TXSEQ = GDCSTRAC.TXSEQ;
        GDCOTRAC.TXCCY = GDCSTRAC.TXCCY;
        GDCOTRAC.TXTYP = GDCSTRAC.TXTYP;
        GDCOTRAC.TXSEQ = GDCSTRAC.TXSEQ;
        GDCOTRAC.TXCTA_NO = GDCSTRAC.TXCTA_NO;
        GDCOTRAC.TXREF_NO = GDCSTRAC.TXREF_NO;
        GDCOTRAC.RELAT_AMT = GDRPLDR.RELAT_AMT;
        if (GDCSTRAC.TXFUCTYP == '3') {
            GDCOTRAC.TXAMT = WS_BACK_AMT;
        } else if (GDCSTRAC.TXFUCTYP == '1') {
            GDCOTRAC.TXAMT = GDCSTRAC.TXAMT;
        }
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
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = 'N' "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC_SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_GDTPLDR_NOAC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RELAT_STS = 'N' "
            + "AND RSEQ = :GDRPLDR.KEY.RSEQ";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC_SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_STARTBR_GDTPLDR_NOAC1() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RELAT_STS = 'N' "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC_SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
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
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSTRAC.TXCCY);
        CEP.TRC(SCCGWA, GDCSTRAC.TXTYP);
        CEP.TRC(SCCGWA, GDCSTRAC.TXAC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSTRAC.TXAC;
        DDRCCY.CCY = GDCSTRAC.TXCCY;
        DDRCCY.CCY_TYPE = GDCSTRAC.TXTYP;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
        CEP.TRC(SCCGWA, WS_AC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_AC;
        DDRCCY.CCY = GDCSTRAC.TXCCY;
        DDRCCY.CCY_TYPE = GDCSTRAC.TXTYP;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
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
