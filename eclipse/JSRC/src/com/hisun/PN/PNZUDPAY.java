package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUDPAY {
    int JIBS_tmp_int;
    DBParm PNTDFT_RD;
    DBParm PNTDFPSW_RD;
    DBParm DDTMST_RD;
    DBParm DCTCDDAT_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_U_ADD_CBOX = "BP-U-SUB-CBOX";
    String CPN_P_AIZUUPIA = "AI-U-UPDATE-IA";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    char K_ERROR = 'E';
    String K_CNTR_TYPE = "BADR";
    String K_EVENT_CODE = "DR      ";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String K_BVCD_QGHP = "C00005";
    String K_BVCD_HDHP = "C00098";
    String K_OUTPUT_FMT = "PN009";
    PNZUDPAY_WS_MSGID WS_MSGID = new PNZUDPAY_WS_MSGID();
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_ISS_BR = 0;
    short WS_ENCRY_LENGTH = 0;
    PNZUDPAY_WS_ERR_MSG_ID WS_ERR_MSG_ID = new PNZUDPAY_WS_ERR_MSG_ID();
    String WS_ENCRY_NO = " ";
    String WS_ENCRY_NO_LENTH = " ";
    PNZUDPAY_WS_MAC_DA_HDSS WS_MAC_DA_HDSS = new PNZUDPAY_WS_MAC_DA_HDSS();
    PNZUDPAY_WS_MAC_DA_IN WS_MAC_DA_IN = new PNZUDPAY_WS_MAC_DA_IN();
    PNZUDPAY_WS_MAC_DA WS_MAC_DA = new PNZUDPAY_WS_MAC_DA();
    double WS_AMT = 0;
    String WS_STR = " ";
    double WS_STL_AMT = 0;
    String WS_AMT2 = " ";
    double WS_BAL_AMT = 0;
    String WS_AMT3 = " ";
    String WS_BRAC = " ";
    double WS_ALL_AMT = 0;
    char WS_TABLE_FLG = ' ';
    char WS_DFPSW_FLG = ' ';
    char WS_PAY_FLG = ' ';
    char WS_CI_STS_FLG = ' ';
    char WS_CDDAT_FLG = ' ';
    char WS_DDTMST_FLG = ' ';
    char WS_DDTCCY_FLG = ' ';
    char WS_GZ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCCBKY SCCCBKY = new SCCCBKY();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    CICACCU CICACCU = new CICACCU();
    AICPQIA AICPQIA = new AICPQIA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICQACRI CICQACRI = new CICQACRI();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    DDRMST DDRMST = new DDRMST();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICCUST CICCUST = new CICCUST();
    CICQACAC CICQACAC = new CICQACAC();
    DDRCCY DDRCCY = new DDRCCY();
    AICUPRVS AICUPRVS = new AICUPRVS();
    PNRDFT PNRDFT = new PNRDFT();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    PNCUDPAY PNCUDPAY;
    public void MP(SCCGWA SCCGWA, PNCUDPAY PNCUDPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUDPAY = PNCUDPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUDPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_DRAFT_PAY_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNCUDPAY.STL_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNCUDPAY.RC);
            WS_ERR_INFO = "UDPAY-STL-FLG=" + PNCUDPAY.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (PNCUDPAY.STL_FLG == 'T' 
                && PNCUDPAY.CLR_CHNL == '0' 
                && PNCUDPAY.LHD_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LHDAC_NOT_IPT, PNCUDPAY.RC);
                WS_ERR_INFO = "UDPAY-LHD-AC=" + PNCUDPAY.LHD_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUDPAY.CLR_CHNL == '1' 
            && PNCUDPAY.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_MUST_T, PNCUDPAY.RC);
            WS_ERR_INFO = "UDPAY-CLR-CHNL=" + PNCUDPAY.CLR_CHNL + ",UDPAY-STL-FLG=" + PNCUDPAY.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUDPAY.CLR_CHNL == '0' 
            || PNCUDPAY.CLR_CHNL == '1') {
            if (PNCUDPAY.LHD_NM.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LHDNM_NOT_IPT, PNCUDPAY.RC);
                WS_ERR_INFO = "UDPAY-CLR-CHNL=" + PNCUDPAY.CLR_CHNL + ",UDPAY-LHD-NM=" + PNCUDPAY.LHD_NM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_DRAFT_PAY_PROC() throws IOException,SQLException,Exception {
        B210_CHECK_INPUT_COMM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B250_CHECK_ENCRY_NO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && PNCUDPAY.CLR_CHNL == '0') {
            B220_01_AI_PROC();
            if (pgmRtn) return;
        } else {
            if (PNCUDPAY.FUNC == 'T') {
            } else {
                CEP.TRC(SCCGWA, "DA SHI JIE");
                B220_POST_DEBIT();
                if (pgmRtn) return;
            }
        }
        B231_POST_CHEK();
        if (pgmRtn) return;
        B230_POST_CREDIT();
        if (pgmRtn) return;
        B240_DFT_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, "--CANCEL OUT--");
            B260_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_CHECK_INPUT_COMM() throws IOException,SQLException,Exception {
        PNRDFT.KEY.BILL_KND = PNCUDPAY.KND;
        PNRDFT.KEY.BILL_NO = PNCUDPAY.DRFT_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("HPS")) {
            CEP.TRC(SCCGWA, "HPS CHNL");
            T000_READ_PNTDFT_DRFT_UPD();
            if (pgmRtn) return;
        } else {
            T000_READ_PNTDFT_UPD();
            if (pgmRtn) return;
        }
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND, PNCUDPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ISS_DT != PNCUDPAY.ISS_DATE) {
            CEP.TRC(SCCGWA, "CHECK DFT-ISS-DT=UDPAY-ISS-DT");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR, PNCUDPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRDFT.ISS_AMT);
        CEP.TRC(SCCGWA, PNCUDPAY.ISS_AMT);
        if (PNRDFT.ISS_AMT != PNCUDPAY.ISS_AMT) {
            CEP.TRC(SCCGWA, "CHECK DFT-ISS-AMT=UDPAY-ISS-AMT");
            CEP.TRC(SCCGWA, PNRDFT.ISS_AMT);
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_EQ, PNCUDPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUDPAY.STL_AMT > PNRDFT.ISS_AMT) {
            WS_AMT = PNRDFT.ISS_AMT;
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLAMT_GR_ISSAMT, PNCUDPAY.RC);
            WS_ERR_INFO = "UDPAY-STL-AMT= " + WS_STR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_ALL_AMT = PNCUDPAY.STL_AMT + PNCUDPAY.BAL_AMT;
        CEP.TRC(SCCGWA, WS_ALL_AMT);
        if (WS_ALL_AMT != PNRDFT.ISS_AMT) {
            WS_STL_AMT = PNCUDPAY.STL_AMT;
            WS_BAL_AMT = PNCUDPAY.BAL_AMT;
            WS_AMT = PNRDFT.ISS_AMT;
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_BALAMT_NOT_COMM, PNCUDPAY.RC);
            WS_ERR_INFO = "UDPAY-STL-AMT=" + WS_AMT2 + ",UDPAY-BAL-AMT=" + WS_AMT3 + ",DFT-ISS-AMT=" + WS_STR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUDPAY.LHD_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PNCUDPAY.LHD_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
        }
        if (PNRDFT.TRN_FLG == '1') {
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                if (!PNCUDPAY.LHD_NM.equalsIgnoreCase(PNRDFT.PAYEE_NAME) 
                    && (PNCUDPAY.CLR_CHNL == '0' 
                    || PNCUDPAY.CLR_CHNL == '2')) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CUST_INF_NOTSM1, PNCUDPAY.RC);
                    WS_ERR_INFO = "DFT-PAYEE-NAME=" + PNRDFT.PAYEE_NAME + ",UDPAY-LHD-NM=" + PNCUDPAY.LHD_NM;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, PNRDFT.STS);
        if (PNRDFT.STS != '1' 
            && PNRDFT.STS != '2' 
            && PNRDFT.STS != '6') {
            if (PNRDFT.STS == '3') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_LOSE);
            }
            if (PNRDFT.STS == '4') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_STOP);
            }
            if (PNRDFT.STS == '5') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CHANGE);
            }
            if (PNRDFT.STS == '7') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ROLL);
            }
            if (PNRDFT.STS == '8') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_REFUNDMENT);
            }
            if (PNRDFT.STS == '9') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CANCEL);
            }
            if (PNRDFT.STS != '3' 
                && PNRDFT.STS != '4' 
                && PNRDFT.STS != '5' 
                && PNRDFT.STS != '7' 
                && PNRDFT.STS != '8' 
                && PNRDFT.STS != '9') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ERR);
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (PNRDFT.STS == '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ISS);
                }
                CEP.TRC(SCCGWA, PNRDFT.LAST_STS);
                if (PNRDFT.STS == '6' 
                    && PNRDFT.LAST_STS != '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CLR_LAST_NOT_ISS);
                }
            } else {
                if (PNRDFT.STS == '2') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_PAY);
                }
                if (PNRDFT.STS == '6') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CLR);
                }
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
            if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031600")) {
                if (PNRDFT.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_NOT_ALLOW_CANCEL, PNCUDPAY.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                if (!PNRDFT.CLR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CANCEL_TLR_MUST_EX, PNCUDPAY.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (PNRDFT.C_T_FLG == 'T' 
            && PNCUDPAY.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_MUST_T, PNCUDPAY.RC);
            WS_ERR_INFO = "DFT-C-T-FLG=" + PNRDFT.C_T_FLG + ",UDPAY-STL-FLG=" + PNCUDPAY.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ODUE_FLG == '1' 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != PNRDFT.ISS_BR) {
            WS_ISS_BR = PNRDFT.ISS_BR;
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CLRBR_NOT_COMM, PNCUDPAY.RC);
            WS_ERR_INFO = "DFT-ISS-BR=" + WS_ISS_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ODUE_FLG == '0' 
            && PNRDFT.IO_FLG == 'O') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IO_FLG_NOT_PAY_NML, PNCUDPAY.RC);
            WS_ERR_INFO = "DFT-IO-FLG=" + PNRDFT.IO_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B220_01_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = PNCUDPAY.LHD_AC;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
        AICUUPIA.DATA.CCY = PNRDFT.ISS_CCY;
        AICUUPIA.DATA.AMT = PNCUDPAY.STL_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.PAY_MAN = PNCUDPAY.LHD_NM;
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B220_POST_DEBIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XIAO SHI JIE ");
        if (PNCUDPAY.STL_FLG == 'C') {
            CEP.TRC(SCCGWA, "XIAN JIN 20180612 ");
            IBS.init(SCCGWA, BPCUSBOX);
            BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUSBOX.CCY = PNRDFT.ISS_CCY;
            BPCUSBOX.AMT = PNCUDPAY.STL_AMT;
            BPCUSBOX.OPP_AC = PNCUDPAY.LHD_AC;
            BPCUSBOX.OPP_ACNM = PNCUDPAY.LHD_NM;
            BPCUSBOX.CI_NO = " ";
            BPCUSBOX.ID_TYP = " ";
            BPCUSBOX.IDNO = " ";
            BPCUSBOX.AGT_IDTYP = " ";
            BPCUSBOX.AGT_IDNO = " ";
            BPCUSBOX.AGT_NAME = " ";
            S000_CALL_BPZUSBOX();
            if (pgmRtn) return;
        } else {
            if (PNCUDPAY.STL_FLG == 'T' 
                && PNCUDPAY.CLR_CHNL == '0') {
                CEP.TRC(SCCGWA, "ZHUAN ZHANG , HANG NEI");
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.AC = PNCUDPAY.LHD_AC;
                DDCUCRAC.CCY = PNRDFT.ISS_CCY;
                DDCUCRAC.CCY_TYPE = '1';
                DDCUCRAC.TX_AMT = PNCUDPAY.STL_AMT;
                DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, "RI QI");
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                DDCUCRAC.OTHER_AC = " ";
                DDCUCRAC.OTHER_CCY = " ";
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.BANK_CR_FLG = 'Y';
                DDCUCRAC.TX_MMO = "A312";
                S000_CALL_DDZUCRAC();
                if (pgmRtn) return;
            }
            if (PNCUDPAY.CLR_CHNL == '2') {
                CEP.TRC(SCCGWA, "WAI WEI");
                IBS.init(SCCGWA, BPCPOEWA);
                BPCPOEWA.DATA.CNTR_TYPE = PNCUDPAY.PRDMO_CD;
                BPCPOEWA.DATA.EVENT_CODE = PNCUDPAY.EVENT_CD;
                BPCPOEWA.DATA.BR_OLD = PNCUDPAY.ACCT_BR;
                BPCPOEWA.DATA.BR_NEW = PNCUDPAY.ACCT_BR;
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRDFT.ISS_CCY;
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNCUDPAY.STL_AMT;
                BPCPOEWA.DATA.PROD_CODE = PNCUDPAY.PROD_CD;
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
            }
        }
    }
    public void B230_POST_CREDIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        if (PNRDFT.IO_FLG == 'O') {
            BPCPOEWA.DATA.BR_OLD = 173001;
            BPCPOEWA.DATA.BR_NEW = 173001;
        } else {
            BPCPOEWA.DATA.BR_OLD = PNRDFT.ISS_BR;
            BPCPOEWA.DATA.BR_NEW = PNRDFT.ISS_BR;
        }
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRDFT.ISS_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNRDFT.ISS_AMT;
        if (PNCUDPAY.STL_FLG == 'C' 
            || (PNCUDPAY.STL_FLG == 'T' 
            && WS_GZ_FLG == 'Y')) {
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_STS);
            CEP.TRC(SCCGWA, "GUA ZHANG 20180112");
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[3-1].AMT = PNCUDPAY.BAL_AMT;
            CEP.TRC(SCCGWA, PNCUDPAY.BAL_AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        } else {
            CEP.TRC(SCCGWA, "BU GUAN 111");
            BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = PNCUDPAY.BAL_AMT;
            CEP.TRC(SCCGWA, PNCUDPAY.BAL_AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        }
        BPCPOEWA.DATA.PROD_CODE = PNRDFT.PROD_CD;
        BPCPOEWA.DATA.AC_NO = PNRDFT.KEY.BILL_NO;
        if (WS_GZ_FLG == 'Y') {
            CEP.TRC(SCCGWA, "WS-YW GUA ZHANG");
            BPCPOEWA.DATA.RVS_NO = AICUPRVS.DATA.RVS_NO;
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PNCUDPAY.BAL_AMT);
    }
    public void B231_POST_CHEK() throws IOException,SQLException,Exception {
        if (PNCUDPAY.BAL_AMT > 0) {
            if (PNRDFT.PAY_TYPE == 'T') {
                CEP.TRC(SCCGWA, "ZHUAN ZHANG SHENG YU");
                if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                    && PNRDFT.STS == '2')) {
                    if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                        CEP.TRC(SCCGWA, "IIII");
                        CEP.TRC(SCCGWA, "CI-STS-BIN");
                        B230_02_CI_STS();
                        if (pgmRtn) return;
                        if (WS_CI_STS_FLG == 'N') {
                            IBS.init(SCCGWA, CICQACAC);
                            CICQACAC.FUNC = 'R';
                            CICQACAC.DATA.CCY_ACAC = PNRDFT.ISS_CCY;
                            CICQACAC.DATA.CR_FLG = '1';
                            CICQACAC.DATA.AGR_NO = PNRDFT.APP_AC;
                            S000_CALL_CIZQACAC();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
                            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
                            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
                            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
                            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
                            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                                CEP.TRC(SCCGWA, "ZHAGNG HU LEI DD");
                                IBS.init(SCCGWA, DDRCCY);
                                DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                                T000_READ_DDTCCY();
                                if (pgmRtn) return;
                            }
                            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                                CEP.TRC(SCCGWA, "-----DFT-APP-AC=DD-----");
                                IBS.init(SCCGWA, DDRMST);
                                DDRMST.KEY.CUS_AC = PNRDFT.APP_AC;
                                T000_READ_DDTMST();
                                if (pgmRtn) return;
                                CEP.TRC(SCCGWA, DDRMST.AC_STS);
                                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                if (WS_DDTMST_FLG == 'N' 
                                    || DDRMST.AC_STS != 'N' 
                                    || DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
                                    || DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                                    || DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                                    || DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
                                    || WS_DDTCCY_FLG == 'N' 
                                    || DDRCCY.STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                                    || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                                    CEP.TRC(SCCGWA, DDRMST.AC_STS);
                                    CEP.TRC(SCCGWA, "1234567");
                                    B230_01_POST_CREDIT_GUAZHANG();
                                    if (pgmRtn) return;
                                } else {
                                    CEP.TRC(SCCGWA, 333555666777);
                                    CEP.TRC(SCCGWA, "7654321");
                                    IBS.init(SCCGWA, DDCUCRAC);
                                    DDCUCRAC.AC = PNRDFT.APP_AC;
                                    DDCUCRAC.CCY = PNRDFT.ISS_CCY;
                                    DDCUCRAC.TX_AMT = PNCUDPAY.BAL_AMT;
                                    DDCUCRAC.TX_MMO = "A312";
                                    DDCUCRAC.CCY_TYPE = '1';
                                    DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                    DDCUCRAC.OTHER_AC = " ";
                                    DDCUCRAC.OTHER_CCY = " ";
                                    DDCUCRAC.TX_TYPE = 'T';
                                    DDCUCRAC.BANK_CR_FLG = 'Y';
                                    S000_CALL_DDZUCRAC();
                                    if (pgmRtn) return;
                                    WS_PAY_FLG = 'N';
                                }
                            }
                            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                                CEP.TRC(SCCGWA, "-----DFT-APP-AC=DC-----");
                                IBS.init(SCCGWA, DCRCDDAT);
                                DCRCDDAT.KEY.CARD_NO = PNRDFT.APP_AC;
                                T000_READ_TBL_DCTCDDAT();
                                if (pgmRtn) return;
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                if (WS_CDDAT_FLG == 'N' 
                                    || DCRCDDAT.CARD_STS != 'N' 
                                    || DDRCCY.STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                                    || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                                    CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
                                    B230_01_POST_CREDIT_GUAZHANG();
                                    if (pgmRtn) return;
                                } else {
                                    CEP.TRC(SCCGWA, "8888888");
                                    IBS.init(SCCGWA, DDCUCRAC);
                                    DDCUCRAC.AC = PNRDFT.APP_AC;
                                    DDCUCRAC.CCY = PNRDFT.ISS_CCY;
                                    DDCUCRAC.TX_AMT = PNCUDPAY.BAL_AMT;
                                    DDCUCRAC.TX_MMO = "A312";
                                    DDCUCRAC.CCY_TYPE = '1';
                                    DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                    DDCUCRAC.OTHER_AC = " ";
                                    DDCUCRAC.OTHER_CCY = " ";
                                    DDCUCRAC.TX_TYPE = 'T';
                                    DDCUCRAC.BANK_CR_FLG = 'Y';
                                    S000_CALL_DDZUCRAC();
                                    if (pgmRtn) return;
                                    WS_PAY_FLG = 'N';
                                }
                            }
                            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                                CEP.TRC(SCCGWA, "---AI---");
                                B230_01_POST_CREDIT_GUAZHANG();
                                if (pgmRtn) return;
                            }
                        }
                    } else {
                        CEP.TRC(SCCGWA, "TUI HUI ZHANGH HAO");
                        IBS.init(SCCGWA, DDCUCRAC);
                        DDCUCRAC.AC = PNRDFT.APP_AC;
                        DDCUCRAC.CCY = PNRDFT.ISS_CCY;
                        DDCUCRAC.TX_AMT = PNCUDPAY.BAL_AMT;
                        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DDCUCRAC.OTHER_AC = " ";
                        DDCUCRAC.OTHER_CCY = " ";
                        DDCUCRAC.TX_TYPE = 'T';
                        DDCUCRAC.BANK_CR_FLG = 'Y';
                        DDCUCRAC.TX_MMO = "A312";
                        S000_CALL_DDZUCRAC();
                        if (pgmRtn) return;
                        WS_PAY_FLG = 'N';
                        CEP.TRC(SCCGWA, "WAN LE ");
                    }
                } else {
                }
            } else {
                B230_01_POST_CREDIT_GUAZHANG();
                if (pgmRtn) return;
            }
        }
        if (PNCUDPAY.CLR_CHNL == '1') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.CD.BUSI_KND = "BPGZ";
            AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICPQIA.CCY = PNRDFT.ISS_CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.AC);
            WS_BRAC = AICPQIA.AC;
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = WS_BRAC;
            AICUUPIA.DATA.CCY = PNRDFT.ISS_CCY;
            AICUUPIA.DATA.AMT = PNCUDPAY.STL_AMT;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.PAY_MAN = PNCUDPAY.LHD_NM;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B230_01_POST_CREDIT_GUAZHANG() throws IOException,SQLException,Exception {
        WS_GZ_FLG = 'Y';
        WS_PAY_FLG = 'Y';
        CEP.TRC(SCCGWA, AICUPRVS.DATA.RVS_NO);
    }
    public void B240_DFT_MST_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            PNRDFT.LAST_STS = PNRDFT.STS;
            PNRDFT.STS = '1';
            PNRDFT.STL_PAY = ' ';
            PNRDFT.LHD_AC = " ";
            PNRDFT.LHD_NAME = " ";
            PNRDFT.STL_CHNL = " ";
            PNRDFT.STL_OPT = ' ';
            PNRDFT.STL_AMT = 0;
            PNRDFT.BAL_AMT = 0;
            PNRDFT.CLR_DATE = 0;
            PNRDFT.CLR_BR = 0;
            PNRDFT.CLR_TLR = " ";
            PNRDFT.CR_AC = " ";
        } else {
            PNRDFT.LAST_STS = PNRDFT.STS;
            CEP.TRC(SCCGWA, PNCUDPAY.BAL_AMT);
            if (PNCUDPAY.BAL_AMT > 0) {
                CEP.TRC(SCCGWA, "XIAN JIN  ");
                if (PNRDFT.PAY_TYPE == 'C') {
                    CEP.TRC(SCCGWA, "JIE FU");
                    PNRDFT.STS = '2';
                }
                if (PNRDFT.PAY_TYPE == 'T') {
                    CEP.TRC(SCCGWA, "ZHUAN ZHANG");
                    CEP.TRC(SCCGWA, WS_PAY_FLG);
                    if (WS_PAY_FLG == 'Y') {
                        CEP.TRC(SCCGWA, "SHEN QING REN ZHANG HU");
                        PNRDFT.STS = '2';
                    } else {
                        CEP.TRC(SCCGWA, "SHOU KUAN REN ZHANG HU");
                        PNRDFT.STS = '6';
                        PNRDFT.CR_AC = PNRDFT.APP_AC;
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "HE XIAO");
                PNRDFT.STS = '6';
            }
            PNRDFT.STL_PAY = PNCUDPAY.STL_FLG;
            PNRDFT.STL_OPT = PNCUDPAY.CLR_CHNL;
            CEP.TRC(SCCGWA, PNCUDPAY.CLR_CHNL);
            PNRDFT.LHD_AC = PNCUDPAY.LHD_AC;
            PNRDFT.STL_AMT = PNCUDPAY.STL_AMT;
            PNRDFT.BAL_AMT = PNCUDPAY.BAL_AMT;
            PNRDFT.LHD_NAME = PNCUDPAY.LHD_NM;
            PNRDFT.STL_CHNL = SCCGWA.COMM_AREA.CHNL;
            PNRDFT.CLR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRDFT.CLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            PNRDFT.CLR_TLR = SCCGWA.COMM_AREA.TL_ID;
            PNRDFT.CREV_NO = AICUPRVS.DATA.RVS_NO;
        }
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, PNRDFT.CREV_NO);
        CEP.TRC(SCCGWA, PNRDFT.RVS_SEQ);
        CEP.TRC(SCCGWA, PNRDFT.STS);
        T000_REWRITE_PNTDFT();
        if (pgmRtn) return;
    }
    public void B260_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOTCEL);
        PNCOTCEL.KND = PNCUDPAY.KND.charAt(0);
        PNCOTCEL.CC_NO = PNCUDPAY.DRFT_NO;
        PNCOTCEL.STS = PNRDFT.STS;
        CEP.TRC(SCCGWA, PNCOTCEL.KND);
        CEP.TRC(SCCGWA, PNCOTCEL.CC_NO);
        CEP.TRC(SCCGWA, PNCOTCEL.STS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCOTCEL;
        SCCFMT.DATA_LEN = 18;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B230_02_CI_STS() throws IOException,SQLException,Exception {
        if (PNRDFT.APP_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PNRDFT.APP_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_STS);
            if (CICQACRI.O_DATA.O_STS != '0') {
                CEP.TRC(SCCGWA, "CI-STS-ERROR");
                B230_01_POST_CREDIT_GUAZHANG();
                if (pgmRtn) return;
                WS_CI_STS_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "CI-STS-NOMAL");
                WS_CI_STS_FLG = 'N';
            }
        }
        if (WS_CI_STS_FLG == 'N' 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = PNRDFT.APP_AC;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW.substring(0, 45));
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || CICCUST.O_DATA.O_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || CICCUST.O_DATA.O_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1") 
                || CICCUST.O_DATA.O_STSW.substring(43 - 1, 43 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "--CUST-O-STSW ERR--");
                WS_CI_STS_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "--CUST-O-STSW NOMAL--");
                WS_CI_STS_FLG = 'N';
            }
        }
    }
    public void B250_CHECK_ENCRY_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = PNCUDPAY.KND;
        PNRDFPSW.KEY.BILL_NO = PNCUDPAY.DRFT_NO;
        CEP.TRC(SCCGWA, PNRDFPSW.KEY.BILL_NO);
        CEP.TRC(SCCGWA, PNRDFPSW.KEY.BILL_KND);
        T000_READ_PNTDFPSW();
        if (pgmRtn) return;
        if (WS_DFPSW_FLG == 'Y') {
            CEP.TRC(SCCGWA, PNRDFPSW.ENCRY_NO);
            if (!PNCUDPAY.ENCRY_NO.equalsIgnoreCase(PNRDFPSW.ENCRY_NO)) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NO_ERR);
            }
        } else {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNTDFPSW_NOT_FND);
        }
    }
    public void B260_BP_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.ACO_AC = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.TX_TOOL = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.REF_NO = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '5';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        if (PNCUDPAY.KND.equalsIgnoreCase("C00005")) {
            BPCPFHIS.DATA.BV_CODE = K_BVCD_QGHP;
        } else {
            BPCPFHIS.DATA.BV_CODE = K_BVCD_HDHP;
        }
        if (PNCUDPAY.DRFT_NO == null) PNCUDPAY.DRFT_NO = "";
        JIBS_tmp_int = PNCUDPAY.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDPAY.DRFT_NO += " ";
        BPCPFHIS.DATA.HEAD_NO = PNCUDPAY.DRFT_NO.substring(0, 8);
        if (PNCUDPAY.DRFT_NO == null) PNCUDPAY.DRFT_NO = "";
        JIBS_tmp_int = PNCUDPAY.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDPAY.DRFT_NO += " ";
        BPCPFHIS.DATA.BV_NO = PNCUDPAY.DRFT_NO.substring(9 - 1, 9 + 8 - 1);
        BPCPFHIS.DATA.TX_CCY = PNRDFT.ISS_CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = PNRDFT.C_T_FLG;
        BPCPFHIS.DATA.TX_AMT = PNRDFT.ISS_AMT;
        BPCPFHIS.DATA.TX_MMO = "A312";
        BPCPFHIS.DATA.PROD_CD = PNRDFT.PROD_CD;
        BPCPFHIS.DATA.OTH_AC = PNCUDPAY.LHD_AC;
        BPCPFHIS.DATA.CI_NO = PNCUDPAY.DFT_CINO;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_PNTDFT_UPD() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.upd = true;
        IBS.READ(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTDFT_DRFT_UPD() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.where = "BILL_NO = :PNRDFT.KEY.BILL_NO";
        PNTDFT_RD.upd = true;
        IBS.READ(SCCGWA, PNRDFT, this, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTDFPSW() throws IOException,SQLException,Exception {
        null.col = "ENCRY_NO";
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        IBS.READ(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DFPSW_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DFPSW_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFPSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.REWRITE(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTMST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTMST_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TBL_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDDAT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CDDAT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUDPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void S000_CALL_DCZIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUCRAC_UR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.ERR_MSG_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUDPAY.RC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.ERR_MSG_ID);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_ADD_CBOX, BPCUSBOX);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUDPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_AIZUUPIA, AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUDPAY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PNCUDPAY.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_ERR_INFO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (PNCUDPAY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, PNCUDPAY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
