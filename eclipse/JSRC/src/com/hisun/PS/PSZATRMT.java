package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCAOTH;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCUCNGM;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIMCCY;
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

public class PSZATRMT {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String CPN_U_AIZUUPIA = "AI-U-UPDATE-IA";
    String CPN_U_DDZUDRAC = "DD-UNIT-DRAW-PROC";
    String CPN_U_DDZUCRAC = "DD-UNIT-DEP-PROC";
    String CPN_U_DDZSRCHQ = "DD-S-RCHQ-PROC";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String CPN_U_BPZUCNGM = "BP-U-MAINT-CNGM";
    String CPN_U_BPZQCNGL = "BP-P-INQ-CNGL";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE = "OWCR";
    String K_EVENT_CODE2 = "IWCRRT";
    String K_EVENT_CODE1 = "OWDR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_STS_FLG = ' ';
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICPQIA AICPQIA = new AICPQIA();
    CICQACRI CICQACRI = new CICQACRI();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCAOTH BPCAOTH = new BPCAOTH();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    PSREINF PSREINF = new PSREINF();
    SCCGWA SCCGWA;
    PSCATRMT PSCATRMT;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, PSCATRMT PSCATRMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCATRMT = PSCATRMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZATRMT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (PSCATRMT.OUR_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCATRMT.OTH_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCATRMT.CCY);
        if (PSCATRMT.CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!PSCATRMT.CCY.equalsIgnoreCase("156")) {
            if (PSCATRMT.CASH_ID == ' ') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CASH_ID_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PSCATRMT.AMT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AMT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCATRMT.VOUCH_CD.equalsIgnoreCase("05")) {
            if (PSCATRMT.CERT_NO.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CERT_NO_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B210_AC_TYP_CHEK();
        if (pgmRtn) return;
        if (!PSCATRMT.VOUCH_CD.equalsIgnoreCase("98")) {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                B211_AI_FUAC_TALLY();
                if (pgmRtn) return;
            } else {
                if (PSCATRMT.CD_FLG == 'C') {
                    if (PSCATRMT.VOUCH_CD.equalsIgnoreCase("99")) {
                        B214_DD_DAC_TALLY();
                        if (pgmRtn) return;
                    } else {
                        B213_DD_DAC_TALLY();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (!PSCATRMT.VOUCH_CD.equalsIgnoreCase("98")) {
            B320_PNT_EVT_PROC();
            if (pgmRtn) return;
        } else {
            B330_PNT_EVT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_AC_TYP_CHEK() throws IOException,SQLException,Exception {
        if (PSCATRMT.OUR_ACNO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSCATRMT.OUR_ACNO;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        }
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.TRC(SCCGWA, "ERR AC-TYPE");
            CEP.ERR(SCCGWA, PSCMSG_ERROR_MSG.PS_AC_TYP_NOT_ALLOW);
        }
    }
    public void B211_AI_FUAC_TALLY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = PSCATRMT.OUR_ACNO;
        AICUUPIA.DATA.CCY = PSCATRMT.CCY;
        AICUUPIA.DATA.AMT = PSCATRMT.AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = PSCATRMT.CREV_NO;
        if (PSCATRMT.CD_FLG == 'C') {
            AICUUPIA.DATA.EVENT_CODE = "DR";
        } else {
            AICUUPIA.DATA.EVENT_CODE = "CR";
        }
        AICUUPIA.DATA.THEIR_AC = PSCATRMT.OTH_ACNO;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_AC);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B213_DD_DAC_TALLY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, DDCUDRAC);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUDRAC.AC = PSCATRMT.OUR_ACNO;
        } else {
            DDCUDRAC.CARD_NO = PSCATRMT.OUR_ACNO;
        }
        DDCUDRAC.CCY = PSCATRMT.CCY;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        CEP.TRC(SCCGWA, "BI ZHONG 2");
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = PSCATRMT.AMT;
        CEP.TRC(SCCGWA, PSCATRMT.VOUCH_CD);
        if (PSCATRMT.VOUCH_CD.equalsIgnoreCase("05")) {
            CEP.TRC(SCCGWA, PSCATRMT.CERT_NO);
            DDCUDRAC.CHQ_NO = PSCATRMT.CERT_NO;
            DDCUDRAC.CHQ_ISS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUDRAC.CHQ_TYPE = '5';
        } else if (PSCATRMT.VOUCH_CD.equalsIgnoreCase("06")) {
            CEP.TRC(SCCGWA, PSCATRMT.CERT_NO);
            DDCUDRAC.CHQ_NO = PSCATRMT.CERT_NO;
            DDCUDRAC.CHQ_ISS_DATE = PSCATRMT.QF_DT;
            DDCUDRAC.CHQ_TYPE = '3';
        } else if (PSCATRMT.VOUCH_CD.equalsIgnoreCase("07")) {
            CEP.TRC(SCCGWA, PSCATRMT.CERT_NO);
            DDCUDRAC.CHQ_NO = PSCATRMT.CERT_NO;
            DDCUDRAC.CHQ_ISS_DATE = PSCATRMT.QF_DT;
            DDCUDRAC.CHQ_TYPE = '2';
        }
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.TX_MMO = "A351";
        DDCUDRAC.PAY_PSWD = PSCATRMT.PAY_PSWD;
        CEP.TRC(SCCGWA, "NCB0905001");
        CEP.TRC(SCCGWA, DDCUDRAC.PAY_PSWD);
        CEP.TRC(SCCGWA, PSCATRMT.QF_DT);
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_ISS_DATE);
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B214_DD_DAC_TALLY() throws IOException,SQLException,Exception {
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUDRAC.AC = PSCATRMT.OUR_ACNO;
        } else {
            DDCUDRAC.CARD_NO = PSCATRMT.OUR_ACNO;
        }
        DDCUDRAC.CCY = PSCATRMT.CCY;
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = PSCATRMT.AMT;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_MMO = "A351";
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B320_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCATRMT.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCATRMT.AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        BPCPOEWA.DATA.AC_NO = PSCATRMT.OUR_ACNO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B330_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE2;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCATRMT.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCATRMT.AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        BPCPOEWA.DATA.AC_NO = PSCATRMT.OUR_ACNO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZUDRAC, DDCUDRAC);
    }
