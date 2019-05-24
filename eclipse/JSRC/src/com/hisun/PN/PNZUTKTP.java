package com.hisun.PN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPCUSBOX;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCRCDDAT;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDRCCY;
import com.hisun.DD.DDRMST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCBKY;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PNZUTKTP {
    boolean pgmRtn = false;
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String CPN_U_SCZCBKY = "SC-CPN-SCZCBKY";
    String K_CNTR_TYPE = "BADR";
    String K_EVENT_CODE = "DR      ";
    String K_BVCD_QGHP = "C00005";
    String K_BVCD_HDHP = "C00098";
    String K_OUTPUT_FMT = "PN009";
    PNZUTKTP_WS_MSGID WS_MSGID = new PNZUTKTP_WS_MSGID();
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_TABLE_FLG = ' ';
    char WS_DFPSW_FLG = ' ';
    char WS_PAY_FLG = ' ';
    char WS_STS = ' ';
    int WS_ISS_BR_CK = 0;
    double WS_ISS_AMT_MSG = 0;
    String WS_AMT1 = " ";
    double WS_STL_AMT = 0;
    String WS_AMT2 = " ";
    double WS_BAL_AMT = 0;
    String WS_AMT3 = " ";
    double WS_ALL_AMT = 0;
    PNZUTKTP_WS_ERR_MSG_ID WS_ERR_MSG_ID = new PNZUTKTP_WS_ERR_MSG_ID();
    String WS_BRAC = " ";
    String WS_ENCRY_NO = " ";
    short WS_ENCRY_LENGTH = 0;
    String WS_ENCRY_NO_LENTH = " ";
    PNZUTKTP_WS_MAC_DA_IN WS_MAC_DA_IN = new PNZUTKTP_WS_MAC_DA_IN();
    PNZUTKTP_WS_MAC_DA_HDSS WS_MAC_DA_HDSS = new PNZUTKTP_WS_MAC_DA_HDSS();
    PNZUTKTP_WS_MAC_DA WS_MAC_DA = new PNZUTKTP_WS_MAC_DA();
    char WS_CI_STS_FLG = ' ';
    char WS_CDDAT_FLG = ' ';
    char WS_DDTMST_FLG = ' ';
    char WS_DDTCCY_FLG = ' ';
    short WS_ACNAME_FLG = 0;
    String WS_ACNAME_IN = " ";
    String WS_ACNAME_AC = " ";
    String WS_APP_AC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCCBKY SCCCBKY = new SCCCBKY();
    CICACCU CICACCU = new CICACCU();
    AICUUPIA AICUUPIA = new AICUUPIA();
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
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUTKTP PNCUTKTP;
    public void MP(SCCGWA SCCGWA, PNCUTKTP PNCUTKTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUTKTP = PNCUTKTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUTKTP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        B201_INPUT_CHK_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B208_BIL_ENCRY_PROC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && PNCUTKTP.DATA.CLR_CHNL == '0') {
            B204_01_AI_PROC();
            if (pgmRtn) return;
        } else {
            B204_CRDR_PROC();
            if (pgmRtn) return;
        }
        B202_PNT_EVT_PROC();
        if (pgmRtn) return;
        B203_PNT_EVT_PROC();
        if (pgmRtn) return;
        B202_DFT_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, "--CANCEL OUT--");
            B205_01_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNCUTKTP.DATA.STL_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CEP.TRC(SCCGWA, PNCUTKTP.DATA.STL_AC);
            CICQACRI.DATA.AGR_NO = PNCUTKTP.DATA.STL_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        }
        if (PNCUTKTP.DATA.FUNC == 'P') {
            if (PNCUTKTP.DATA.STL_FLG == 'T' 
                && PNCUTKTP.DATA.CLR_CHNL == '0') {
                if (PNCUTKTP.DATA.STL_AC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLAC_NOT_IPT, PNCUTKTP.RC);
                    WS_ERR_INFO = "UTKTP-STL-AC=" + PNCUTKTP.DATA.STL_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (PNCUTKTP.DATA.STL_FLG == 'T' 
                && PNCUTKTP.DATA.CLR_CHNL == '0') {
                if (PNCUTKTP.DATA.STL_AC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLAC_NOT_IPT, PNCUTKTP.RC);
                    WS_ERR_INFO = "UTKTP-STL-AC=" + PNCUTKTP.DATA.STL_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, PNRDFT);
        PNRDFT.KEY.BILL_KND = PNCUTKTP.DATA.KND;
        PNRDFT.KEY.BILL_NO = PNCUTKTP.DATA.DRFT_NO;
        CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_KND);
        CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_NO);
        CEP.TRC(SCCGWA, PNCUTKTP.DATA.KND);
        CEP.TRC(SCCGWA, PNCUTKTP.DATA.DRFT_NO);
        T000_READ_PNTDFT_UPD();
        if (pgmRtn) return;
        WS_APP_AC = PNRDFT.APP_AC;
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND, PNCUTKTP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ISS_DT != PNCUTKTP.DATA.ISS_DATE) {
            CEP.TRC(SCCGWA, "CHECK DFT-ISS-DT=UTKTP-ISS-DT");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR, PNCUTKTP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ISS_AMT != PNCUTKTP.DATA.ISS_AMT) {
            CEP.TRC(SCCGWA, "CHECK DFT-ISS-AMT=UTKTP-ISS-AMT");
            CEP.TRC(SCCGWA, PNRDFT.ISS_AMT);
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_EQ, PNCUTKTP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ISS_BR_CK = PNRDFT.ISS_BR;
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM, PNCUTKTP.RC);
            WS_ERR_INFO = "DFT-ISS-BR=" + WS_ISS_BR_CK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.C_T_FLG == 'T' 
            && PNCUTKTP.DATA.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STL_FLG_MUST_T, PNCUTKTP.RC);
            WS_ERR_INFO = "UTKTP-STL-FLG=" + PNRDFT.STL_PAY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUTKTP.DATA.FUNC == 'P') {
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_ACNAME_IN = PNRDFT.APP_NAME;
                WS_ACNAME_AC = PNCUTKTP.DATA.STL_NM;
