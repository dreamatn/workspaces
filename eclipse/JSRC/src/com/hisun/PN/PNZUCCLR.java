package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUCCLR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm PNTDFPSW_RD;
    DBParm PNTBCC_RD;
    boolean pgmRtn = false;
    String CPN_U_DDZUCRAC = "DD-UNIT-DEP-PROC";
    String CPN_U_BPZUSBOX = "BP-U-SUB-CBOX";
    String CPN_P_AIZUUPIA = "AI-U-UPDATE-IA";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String K_CNTR_TYPE = "CACH";
    String K_EVENT_CODE = "DR      ";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String K_BVCD_BP1 = "056";
    String K_OUTPUT_FMT = "PN009";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_ENCRY_NO = " ";
    int WS_ISS_BR_MSG = 0;
    PNZUCCLR_WS_MAC_DA WS_MAC_DA = new PNZUCCLR_WS_MAC_DA();
    String WS_BRAC = " ";
    char WS_TABLE_FLG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICPIAEV AICPIAEV = new AICPIAEV();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCHMPW SCCHMPW = new SCCHMPW();
    AICPQIA AICPQIA = new AICPQIA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICQACRI CICQACRI = new CICQACRI();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUCCLR PNCUCCLR;
    public void MP(SCCGWA SCCGWA, PNCUCCLR PNCUCCLR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUCCLR = PNCUCCLR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUCCLR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.CNAP_NO);
        CEP.TRC(SCCGWA, BPCPORUP);
        CEP.TRC(SCCGWA, PNCUCCLR.KND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B101_PNT_INPUT_CHK();
        if (pgmRtn) return;
        B102_PNT_COMM_CHK();
        if (pgmRtn) return;
        B205_BIL_ENCRY_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PNCUCCLR.FUNC);
        B206_PNT_EVT_PROC();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && PNCUCCLR.CLR_CHNL == '0') {
            B207_01_AI_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, PNCUCCLR.FUNC);
            if (PNCUCCLR.FUNC == 'T') {
            } else {
                B207_CR_DEAL_PROC();
                if (pgmRtn) return;
            }
        }
        B220_BCC_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, "--CANCEL OUT--");
            B230_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B101_PNT_INPUT_CHK() throws IOException,SQLException,Exception {
        if (PNCUCCLR.STL_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNCUCCLR.RC);
            WS_ERR_INFO = "UCCLR-STL-FLG=" + PNCUCCLR.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (PNCUCCLR.STL_FLG == 'T' 
                && PNCUCCLR.CLR_CHNL == '0' 
                && PNCUCCLR.LHD_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LHDAC_NOT_IPT, PNCUCCLR.RC);
                WS_ERR_INFO = "UCCLR-LHD-AC=" + PNCUCCLR.LHD_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((PNCUCCLR.CLR_CHNL == '0' 
            || PNCUCCLR.CLR_CHNL == '1') 
            && PNCUCCLR.LHD_NM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LHDNM_NOT_IPT, PNCUCCLR.RC);
            WS_ERR_INFO = "UCCLR-LHD-NM=" + PNCUCCLR.LHD_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUCCLR.CLR_CHNL == '1' 
            && PNCUCCLR.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_MUST_T, PNCUCCLR.RC);
            WS_ERR_INFO = "UCCLR-CLR-CHNL=" + PNCUCCLR.CLR_CHNL + ",UCCLR-STL-FLG=" + PNCUCCLR.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B102_PNT_COMM_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRBCC);
        CEP.TRC(SCCGWA, PNCUCCLR.KND);
        CEP.TRC(SCCGWA, PNCUCCLR.CC_NO);
        PNRBCC.KEY.BILL_KND = PNCUCCLR.KND;
        PNRBCC.KEY.BILL_NO = PNCUCCLR.CC_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("MID")) {
            CEP.TRC(SCCGWA, "MID CHNL");
            T000_READ_PNTBCC_CCNO_UPD();
            if (pgmRtn) return;
        } else {
            T000_READ_PNTBCC_UPD();
            if (pgmRtn) return;
        }
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND, PNCUCCLR.RC);
            WS_ERR_INFO = "UCCLR-CC-NO=" + PNCUCCLR.CC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUCCLR.CLR_CHNL == '2' 
            && PNCUCCLR.LHD_NM.trim().length() == 0) {
            PNCUCCLR.LHD_NM = PNRBCC.PAYEE_ACNM;
        }
        CEP.TRC(SCCGWA, PNRBCC.AMT);
        CEP.TRC(SCCGWA, PNCUCCLR.ISS_AMT);
        if (PNRBCC.AMT != PNCUCCLR.ISS_AMT) {
            CEP.TRC(SCCGWA, "CHECK BCC-AMT=UCCLR-ISSAMT");
            CEP.TRC(SCCGWA, PNRBCC.AMT);
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_EQ, PNCUCCLR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRBCC.ISS_DT != PNCUCCLR.ISS_DATE) {
            CEP.TRC(SCCGWA, "CHECK BCC-ISS-DT=UCCLR-ISS-DT");
            CEP.TRC(SCCGWA, PNRBCC.AMT);
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR, PNCUCCLR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRBCC.STS);
        if (PNRBCC.STS != '1' 
            && PNRBCC.STS != '2') {
            if (PNRBCC.STS == '3') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_LOSE);
            }
            if (PNRBCC.STS == '4') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_STOP);
            }
            if (PNRBCC.STS == '5') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CHANGE);
            }
            if (PNRBCC.STS == '6') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ROLL);
            }
            if (PNRBCC.STS == '7') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_REFUNDMENT);
            }
            if (PNRBCC.STS == '8') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CANCEL);
            }
            if (PNRBCC.STS != '3' 
                && PNRBCC.STS != '4' 
                && PNRBCC.STS != '5' 
                && PNRBCC.STS != '6' 
                && PNRBCC.STS != '7' 
                && PNRBCC.STS != '8') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ERR);
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (PNRBCC.STS == '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ISS);
                }
            } else {
                if (PNRBCC.STS == '2') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_PAY);
                }
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
            if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031600")) {
                if (PNRBCC.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_NOT_ALLOW_CANCEL, PNCUCCLR.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                if (!PNRBCC.CLR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CANCEL_TLR_MUST_EX, PNCUCCLR.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (PNRBCC.ODUE_FLG == '1') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != PNRBCC.ISS_BR) {
                WS_ISS_BR_MSG = PNRBCC.ISS_BR;
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CLRBR_NOT_COMM, PNCUCCLR.RC);
                WS_ERR_INFO = "BCC-ISS-BR=" + WS_ISS_BR_MSG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNRBCC.C_T_FLG == 'T' 
            && PNCUCCLR.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_MUST_T, PNCUCCLR.RC);
            WS_ERR_INFO = "UCCLR-STL-FLG=" + PNCUCCLR.STL_FLG + ",BCC-C-T-FLG=" + PNRBCC.C_T_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CEP.TRC(SCCGWA, PNCUCCLR.LHD_AC);
        CICQACRI.DATA.AGR_NO = PNCUCCLR.LHD_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            if (PNRBCC.TRN_FLG == '1' 
                && !PNRBCC.PAYEE_ACNM.equalsIgnoreCase(PNCUCCLR.LHD_NM) 
                && (PNCUCCLR.CLR_CHNL == '0' 
                || PNCUCCLR.CLR_CHNL == '2')) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CUST_INF_NOTSM1, PNCUCCLR.RC);
                WS_ERR_INFO = "UCCLR-LHD-NM=" + PNCUCCLR.LHD_NM + ",BCC-PAYEE-ACNM=" + PNRBCC.PAYEE_ACNM + ",BCC-TRN-FLG=" + PNRBCC.TRN_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B220_BCC_MST_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            PNRBCC.LAST_STS = PNRBCC.STS;
            PNRBCC.STS = '1';
            PNRBCC.STL_TYPE = ' ';
            PNRBCC.LHD_AC = " ";
            PNRBCC.LHD_ACNM = " ";
            PNRBCC.STL_CHNL = " ";
            PNRBCC.CLR_BR = 0;
            PNRBCC.CLR_TLR = " ";
            PNRBCC.STL_OPT = ' ';
            PNRBCC.CLR_DATE = 0;
        } else {
            PNRBCC.LAST_STS = PNRBCC.STS;
            PNRBCC.STS = '2';
            PNRBCC.STL_TYPE = PNCUCCLR.STL_FLG;
            PNRBCC.LHD_AC = PNCUCCLR.LHD_AC;
            PNRBCC.LHD_ACNM = PNCUCCLR.LHD_NM;
            PNRBCC.STL_OPT = PNCUCCLR.CLR_CHNL;
            PNRBCC.STL_CHNL = SCCGWA.COMM_AREA.CHNL;
            PNRBCC.CLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            PNRBCC.CLR_TLR = SCCGWA.COMM_AREA.TL_ID;
            PNRBCC.CLR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTBCC();
        if (pgmRtn) return;
    }
    public void B230_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOTCEL);
        PNCOTCEL.KND = PNCUCCLR.KND.charAt(0);
        PNCOTCEL.CC_NO = PNCUCCLR.CC_NO;
        PNCOTCEL.STS = PNRBCC.STS;
        CEP.TRC(SCCGWA, PNCOTCEL.KND);
        CEP.TRC(SCCGWA, PNCOTCEL.CC_NO);
        CEP.TRC(SCCGWA, PNCOTCEL.STS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCOTCEL;
        SCCFMT.DATA_LEN = 18;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B206_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = PNRBCC.ISS_BR;
        BPCPOEWA.DATA.BR_NEW = PNRBCC.ISS_BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRBCC.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNRBCC.AMT;
        BPCPOEWA.DATA.PROD_CODE = PNRBCC.PRD_CD;
        BPCPOEWA.DATA.AC_NO = PNRBCC.KEY.BILL_NO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B207_01_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = PNCUCCLR.LHD_AC;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
        AICUUPIA.DATA.CCY = PNRBCC.CCY;
        AICUUPIA.DATA.AMT = PNRBCC.AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.PAY_MAN = PNCUCCLR.LHD_NM;
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B207_CR_DEAL_PROC() throws IOException,SQLException,Exception {
        if (PNCUCCLR.STL_FLG == 'T' 
            && PNCUCCLR.CLR_CHNL == '0') {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = PNCUCCLR.LHD_AC;
            CEP.TRC(SCCGWA, DDCUCRAC.AC);
            DDCUCRAC.CCY = PNRBCC.CCY;
            DDCUCRAC.CCY_TYPE = '1';
            DDCUCRAC.TX_AMT = PNRBCC.AMT;
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.BANK_CR_FLG = 'Y';
            DDCUCRAC.TX_MMO = "A311";
            CEP.TRC(SCCGWA, DDCUCRAC.CCY_TYPE);
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (PNCUCCLR.STL_FLG == 'C') {
            IBS.init(SCCGWA, BPCUSBOX);
            BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUSBOX.CCY = PNRBCC.CCY;
            BPCUSBOX.AMT = PNRBCC.AMT;
            BPCUSBOX.OPP_AC = PNCUCCLR.LHD_AC;
            BPCUSBOX.OPP_ACNM = PNCUCCLR.LHD_NM;
            BPCUSBOX.CI_NO = " ";
            BPCUSBOX.ID_TYP = " ";
            BPCUSBOX.IDNO = " ";
            BPCUSBOX.AGT_IDTYP = " ";
            BPCUSBOX.AGT_IDNO = " ";
            BPCUSBOX.AGT_NAME = " ";
            S000_CALL_BPZUSBOX();
            if (pgmRtn) return;
        }
        if (PNCUCCLR.CLR_CHNL == '1') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.CD.BUSI_KND = "BPGZ";
            AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICPQIA.CCY = PNRBCC.CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.AC);
            WS_BRAC = AICPQIA.AC;
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = WS_BRAC;
            AICUUPIA.DATA.CCY = PNRBCC.CCY;
            AICUUPIA.DATA.AMT = PNRBCC.AMT;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.PAY_MAN = PNCUCCLR.LHD_NM;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        if (PNCUCCLR.CLR_CHNL == '2') {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAAAA");
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = PNCUCCLR.PRDMO_CD;
            BPCPOEWA.DATA.EVENT_CODE = PNCUCCLR.EVENT_CD;
            BPCPOEWA.DATA.BR_OLD = PNCUCCLR.ACCT_BR;
            BPCPOEWA.DATA.BR_NEW = PNCUCCLR.ACCT_BR;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRBCC.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNRBCC.AMT;
            BPCPOEWA.DATA.PROD_CODE = PNCUCCLR.PROD_CD;
            CEP.TRC(SCCGWA, PNCUCCLR.PRDMO_CD);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
            CEP.TRC(SCCGWA, PNCUCCLR.PROD_CD);
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void B230_BP_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = PNRBCC.KEY.BILL_NO;
        BPCPFHIS.DATA.ACO_AC = PNRBCC.KEY.BILL_NO;
        BPCPFHIS.DATA.TX_TOOL = PNRBCC.KEY.BILL_NO;
        BPCPFHIS.DATA.REF_NO = PNRBCC.KEY.BILL_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '5';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.BV_CODE = K_BVCD_BP1;
        if (PNCUCCLR.CC_NO == null) PNCUCCLR.CC_NO = "";
        JIBS_tmp_int = PNCUCCLR.CC_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCCLR.CC_NO += " ";
        BPCPFHIS.DATA.HEAD_NO = PNCUCCLR.CC_NO.substring(0, 8);
        if (PNCUCCLR.CC_NO == null) PNCUCCLR.CC_NO = "";
        JIBS_tmp_int = PNCUCCLR.CC_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCCLR.CC_NO += " ";
        BPCPFHIS.DATA.BV_NO = PNCUCCLR.CC_NO.substring(9 - 1, 9 + 8 - 1);
        BPCPFHIS.DATA.TX_CCY = PNRBCC.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = PNRBCC.C_T_FLG;
        BPCPFHIS.DATA.TX_AMT = PNRBCC.AMT;
        BPCPFHIS.DATA.TX_MMO = "A311";
        BPCPFHIS.DATA.PROD_CD = PNRBCC.PRD_CD;
        BPCPFHIS.DATA.OTH_AC = PNCUCCLR.LHD_AC;
        BPCPFHIS.DATA.CI_NO = PNCUCCLR.BCC_CINO;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B205_BIL_ENCRY_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, PNRDFPSW);
            PNRDFPSW.KEY.BILL_KND = "C00001";
            PNRDFPSW.KEY.BILL_NO = PNCUCCLR.CC_NO;
            T000_READ_PNTDFPSW();
            if (pgmRtn) return;
            if (WS_TABLE_FLG == 'Y') {
                CEP.TRC(SCCGWA, PNRDFPSW.ENCRY_NO);
                WS_ENCRY_NO = PNRDFPSW.ENCRY_NO;
            } else {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNTDFPSW_NOT_FND);
            }
            if (!PNCUCCLR.ENCRY_NO.equalsIgnoreCase(WS_ENCRY_NO)) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NO_ERR, PNCUCCLR.RC);
                WS_ERR_INFO = "UCCLR-ENCRY-NO=" + PNCUCCLR.ENCRY_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZUCRAC, DDCUCRAC);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZUSBOX, BPCUSBOX);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUCCLR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUCCLR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTDFPSW() throws IOException,SQLException,Exception {
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        IBS.READ(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFPSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUCCLR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_AIZUUPIA, AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUCCLR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void T000_REWRITE_PNTBCC() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        IBS.REWRITE(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTBCC_UPD() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        PNTBCC_RD.upd = true;
        IBS.READ(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTBCC_CCNO_UPD() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        PNTBCC_RD.where = "BILL_NO = :PNRBCC.KEY.BILL_NO";
        PNTBCC_RD.upd = true;
        IBS.READ(SCCGWA, PNRBCC, this, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PNCUCCLR.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_ERR_INFO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (PNCUCCLR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, PNCUCCLR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
