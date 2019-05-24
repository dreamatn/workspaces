package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZINCRP {
    DBParm PSTIBLL_RD;
    DBParm PSTEINF_RD;
    DBParm PSTPBIN_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String K_TBL_BCC = "PSTIBLL";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE1 = "IWCR";
    String K_EVENT_CODE2 = "IWCRR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    String WS_BRAC = " ";
    char WS_TABLE_FLG = ' ';
    char WS_EXG_REC_STS = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSRCHQ DDCSRCHQ = new DDCSRCHQ();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    CICQACRI CICQACRI = new CICQACRI();
    CICACCU CICACCU = new CICACCU();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCINCRP PSCINCRP;
    public void MP(SCCGWA SCCGWA, PSCINCRP PSCINCRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCINCRP = PSCINCRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZINCRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_CHECK_TRAN_DATA();
        if (pgmRtn) return;
        B210_CHECK_OUR_AC();
        if (pgmRtn) return;
        if (PSCINCRP.RTN_FLG == '0') {
            B220_CR_DD_AICR();
            if (pgmRtn) return;
            B300_DR_BPZPOEWA();
            if (pgmRtn) return;
        } else {
            B301_THC_BPZPOEWA();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B301_OBLL_MST_PROC();
            if (pgmRtn) return;
        } else {
            B400_WRITE_PSTIBLL();
            if (pgmRtn) return;
        }
        B401_BP_NFHIS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCINCRP.EXG_AREA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_DT == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TR_DT_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_TMS == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TR_TMS_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.OUR_ACNO.trim().length() == 0) {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BF_AC_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.OUR_ACNM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BF_NM_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.OTH_EXG_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_REC_BR_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.PBKA_EX_INSNM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_DFHM_TMS_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.OTH_ACNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ORD_AC_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.OTH_ACNM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ORD_NM_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.CASH_ID == ' ') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_NT_CHG_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_AMT_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_AMT_NOT_ZERO_PROC, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.RTN_FLG == '1') {
            if (PSCINCRP.RET_RSN_CD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_REF_REASON_MST_INPUT, PSCINCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (PSCINCRP.RET_RSN_CD.equalsIgnoreCase("99")) {
            if (PSCINCRP.RET_RSN_DSC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_CL_REASON_MST_INPUT, PSCINCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCINCRP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCINCRP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCINCRP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        T000_READ_PSTEINF();
        if (pgmRtn) return;
        if (!PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TRT_NOT_ALLOW, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCINCRP.EXG_DT);
        CEP.TRC(SCCGWA, PSREINF.EXG_DT);
        if (PSCINCRP.EXG_DT != PSREINF.EXG_DT) {
            CEP.TRC(SCCGWA, PSCINCRP.EXG_DT);
            CEP.TRC(SCCGWA, PSREINF.EXG_DT);
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TRTDT_NOT_EQUAL_EXDT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_TMS != PSREINF.EXG_TMS) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TMS_EXTMS_ERR, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCINCRP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCINCRP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCINCRP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCINCRP.OTH_EXG_NO;
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_NO);
        CEP.TRC(SCCGWA, PSRPBIN.EXG_NB);
        T100_READ_PSTPBIN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, 11111111);
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_EX_NO_OTH_MST_INPUT, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCINCRP.EXG_TMS > PSRPBIN.EXG_NB) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = PSCINCRP.OUR_ACNO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        CEP.TRC(SCCGWA, PSCINCRP.OUR_ACNM);
    }
    public void B210_CHECK_OUR_AC() throws IOException,SQLException,Exception {
        if (PSCINCRP.OUR_ACNO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSCINCRP.OUR_ACNO;
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
    public void B220_CR_DD_AICR() throws IOException,SQLException,Exception {
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIMMST);
            CEP.TRC(SCCGWA, PSCINCRP.OUR_ACNO);
            DDCIMMST.DATA.KEY.AC_NO = PSCINCRP.OUR_ACNO;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            B220_DD_DR_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = PSCINCRP.OUR_ACNO;
            CEP.TRC(SCCGWA, PSCINCRP.OUR_ACNO);
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            B220_DD_DR_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = PSCINCRP.OUR_ACNO;
            CEP.TRC(SCCGWA, PSCINCRP.OUR_ACNO);
            AICUUPIA.DATA.CCY = PSCINCRP.EXG_CCY;
            AICUUPIA.DATA.AMT = PSCINCRP.EXG_AMT;
            CEP.TRC(SCCGWA, PSCINCRP.EXG_AMT);
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.PAY_MAN = PSCINCRP.OTH_ACNM;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
            CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B220_DD_DR_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = PSCINCRP.OUR_ACNO;
        CEP.TRC(SCCGWA, DDCUCRAC.AC);
        DDCUCRAC.CCY = PSCINCRP.EXG_CCY;
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = PSCINCRP.EXG_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.OTHER_AC = " ";
        DDCUCRAC.OTHER_CCY = " ";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_MMO = "A353";
        CEP.TRC(SCCGWA, DDCUCRAC.CCY_TYPE);
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B300_DR_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE1;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCINCRP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCINCRP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B301_THC_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE2;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCINCRP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCINCRP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B400_WRITE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCINCRP.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCINCRP.EXG_AREA_NO;
        PSRIBLL.KEY.EXG_DT = PSCINCRP.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSCINCRP.EXG_TMS;
        PSRIBLL.KEY.EXG_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        PSRIBLL.OUR_EXG_NO = PSREINF.EXG_NO;
        PSRIBLL.EXG_CCY = PSCINCRP.EXG_CCY;
        PSRIBLL.OTH_EXG_NO = PSCINCRP.OTH_EXG_NO;
        PSRIBLL.EXG_DC = 'C';
        PSRIBLL.EXG_VOUCH_CD = PSCINCRP.EXG_VOUCH_CD;
        PSRIBLL.EXG_CERT_NO = PSCINCRP.EXG_CERT_NO;
        PSRIBLL.EXG_REC_STS = PSCINCRP.RTN_FLG;
        PSRIBLL.EXG_AMT = PSCINCRP.EXG_AMT;
        PSRIBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSRIBLL.OUR_ACNO = PSCINCRP.OUR_ACNO;
        PSRIBLL.OUR_ACNM = PSCINCRP.OUR_ACNM;
        PSRIBLL.PBKA_EX_INSNM = PSCINCRP.PBKA_EX_INSNM;
        PSRIBLL.OTH_ACNO = PSCINCRP.OTH_ACNO;
        PSRIBLL.OTH_ACNM = PSCINCRP.OTH_ACNM;
        PSRIBLL.RMK = PSCINCRP.RMK;
        PSRIBLL.RET_RSN_CD = PSCINCRP.RET_RSN_CD;
        CEP.TRC(SCCGWA, PSCINCRP.RET_RSN_CD);
        CEP.TRC(SCCGWA, PSRIBLL.RET_RSN_CD);
        PSRIBLL.RET_RSN_DSC = PSCINCRP.RET_RSN_DSC;
        PSRIBLL.STR_CHNL = SCCGWA.COMM_AREA.CHNL;
        PSRIBLL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRIBLL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRIBLL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRIBLL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_PSTIBLL();
        if (pgmRtn) return;
    }
    public void B301_OBLL_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCINCRP.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCINCRP.EXG_AREA_NO;
        PSRIBLL.KEY.EXG_DT = PSCINCRP.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSCINCRP.EXG_TMS;
        PSRIBLL.KEY.EXG_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        T000_READ_PSTIBLL_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_OBLL_REC_EXIST, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSROBLL.EXG_REC_STS);
        if (PSROBLL.EXG_REC_STS == '2') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ERR_REC_STS_EXIST, PSCINCRP.RC);
            WS_ERR_INFO = "iBLL-EXG-REC-STS=" + PSRIBLL.EXG_REC_STS;
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSRIBLL.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ERR_UP_DATE_EXIST, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!PSRIBLL.CRT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ERR_ISS_TLR_EXIST, PSCINCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        PSRIBLL.EXG_REC_STS = '2';
        PSRIBLL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRIBLL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PSTIBLL();
        if (pgmRtn) return;
    }
    public void B401_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = " ";
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = PSCINCRP.EXG_CERT_NO;
        BPCPNHIS.INFO.TX_RMK = PSCINCRP.RMK;
        BPCPNHIS.INFO.NEW_DAT_PT = PSCINCRP;
        BPCPNHIS.INFO.FMT_ID = "PSCINCRP";
        BPCPNHIS.INFO.FMT_ID_LEN = 1051;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        if (PSCINCRP.RTN_FLG == '1') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CANCEL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_REWRITE_PSTIBLL() throws IOException,SQLException,Exception {
        PSTIBLL_RD = new DBParm();
        PSTIBLL_RD.TableName = "PSTIBLL";
        PSTIBLL_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, PSRIBLL, PSTIBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BCC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PSTIBLL_UPD() throws IOException,SQLException,Exception {
        PSTIBLL_RD = new DBParm();
        PSTIBLL_RD.TableName = "PSTIBLL";
        PSTIBLL_RD.upd = true;
        IBS.READ(SCCGWA, PSRIBLL, PSTIBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BCC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_MBKA_NOT_FIND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTEINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_PSTIBLL() throws IOException,SQLException,Exception {
        PSTIBLL_RD = new DBParm();
        PSTIBLL_RD.TableName = "PSTIBLL";
        IBS.WRITE(SCCGWA, PSRIBLL, PSTIBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
            CEP.TRC(SCCGWA, WS_TABLE_FLG);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
            CEP.TRC(SCCGWA, WS_TABLE_FLG);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTIBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T100_READ_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        IBS.READ(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
