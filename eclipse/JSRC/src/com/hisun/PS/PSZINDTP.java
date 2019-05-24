package com.hisun.PS;

import com.hisun.PN.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZINDTP {
    DBParm PNTBCC_RD;
    DBParm PNTDFT_RD;
    DBParm PSTIBLL_RD;
    DBParm PSTEINF_RD;
    DBParm PSTPBIN_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String K_TBL_BCC = "PSTIBLL";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String CPN_U_AIZUUPIA = "AI-U-UPDATE-IA";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE = "IWDR";
    String K_EVENT_CODE2 = "IWDRR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    String WS_BRAC = " ";
    char WS_TABLE_FLG = ' ';
    char WS_EXG_REC_STS = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    DDCIMCHQ DDCIMCHQ = new DDCIMCHQ();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    PNCUCCLR PNCUCCLR = new PNCUCCLR();
    PNCUDPAY PNCUDPAY = new PNCUDPAY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICUUPIA AICUUPIA = new AICUUPIA();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFT PNRDFT = new PNRDFT();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    String WK_INSNM = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCINDTP PSCINDTP;
    public void MP(SCCGWA SCCGWA, PSCINDTP PSCINDTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCINDTP = PSCINDTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZINDTP return!");
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
        CEP.TRC(SCCGWA, PSCINDTP.EXG_VOUCH_CD);
        B210_CHECK_OUR_AC();
        if (pgmRtn) return;
        if (PSCINDTP.RTN_FLG == '0') {
            B220_CR_DD_AICR();
            if (pgmRtn) return;
            B300_DR_BPZPOEWA();
            if (pgmRtn) return;
        } else {
            B301_THD_BPZPOEWA();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B410_WRITE_PSTIBLL();
            if (pgmRtn) return;
        } else {
            B400_WRITE_PSTIBLL();
            if (pgmRtn) return;
        }
        B401_BP_NFHIS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCINDTP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TR_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TR_TMS_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCINDTP.EXG_VOUCH_CD);
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("01")) {
            CEP.TRC(SCCGWA, PSCINDTP.OUR_ACNO);
            if (PSCINDTP.OUR_ACNO.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_AC_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, PSCINDTP.OUR_ACNM);
            if (PSCINDTP.OUR_ACNM.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_NM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PSCINDTP.OTH_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REC_BR_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.PBKA_EX_INSNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_DFHM_TMS_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCINDTP.OTH_ACNO);
        if (PSCINDTP.OTH_ACNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!PSCINDTP.EXG_CCY.equalsIgnoreCase("156")) {
            if (PSCINDTP.CASH_ID == ' ') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_NT_CHG_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PSCINDTP.EXG_AMT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AMT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_AMT <= 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AMT_NOT_ZERO_PROC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_VOUCH_CD.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_VOUCH_CD_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCINDTP.EXG_VOUCH_CD);
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("99") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("01") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("02") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("03") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("04") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("05")) {
        } else {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_KND_NO_COMM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_CERT_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CERT_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.CERT_DT == 0 
            && !PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("99")) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CERT_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXP_DT == 0 
            && !PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("99")) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXP_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.RTN_FLG == '1') {
            if (PSCINDTP.RET_RSN_CD.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REF_REASON_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PSCINDTP.RET_RSN_CD.equalsIgnoreCase("99")) {
            if (PSCINDTP.RET_RSN_DSC.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CL_REASON_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCINDTP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCINDTP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCINDTP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        T000_READ_PSTEINF();
        if (pgmRtn) return;
        if (!PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRT_NOT_ALLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCINDTP.EXG_DT);
        CEP.TRC(SCCGWA, PSREINF.EXG_DT);
        if (PSCINDTP.EXG_DT != PSREINF.EXG_DT) {
            CEP.TRC(SCCGWA, PSCINDTP.EXG_DT);
            CEP.TRC(SCCGWA, PSREINF.EXG_DT);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRTDT_NOT_EQUAL_EXDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCINDTP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCINDTP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCINDTP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCINDTP.OTH_EXG_NO;
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_NO);
        T100_READ_PSTPBIN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, 11111111);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_OTH_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCINDTP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCINDTP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCINDTP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSREINF.EXG_NO;
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        T100_READ_PSTPBIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PSRPBIN.PBKA_EX_INSNM);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, 7777777777);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_M_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_TMS > PSRPBIN.EXG_NB) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, PNRBCC);
            PNRBCC.KEY.BILL_KND = "C00001";
            PNRBCC.KEY.BILL_NO = PSCINDTP.EXG_CERT_NO;
            PNTBCC_RD = new DBParm();
            PNTBCC_RD.TableName = "PNTBCC";
            IBS.READ(SCCGWA, PNRBCC, PNTBCC_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                CEP.TRC(SCCGWA, 0);
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PNT_NOT_FND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, 2222222222);
                if (PNRBCC.STS != '1' 
                    && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_STS_NOT_NML;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("03") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, PNRDFT);
            if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("03")) {
                PNRDFT.KEY.BILL_KND = "C00005";
            } else {
                PNRDFT.KEY.BILL_KND = "C00098";
            }
            PNRDFT.KEY.BILL_NO = PSCINDTP.EXG_CERT_NO;
            PNTDFT_RD = new DBParm();
            PNTDFT_RD.TableName = "PNTDFT";
            IBS.READ(SCCGWA, PNRDFT, PNTDFT_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                CEP.TRC(SCCGWA, 0);
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PNT_NOT_FND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, 2222222222);
                if (PNRDFT.STS != '1' 
                    && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_STS_NOT_NML;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B210_CHECK_OUR_AC() throws IOException,SQLException,Exception {
        if (PSCINDTP.OUR_ACNO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSCINDTP.OUR_ACNO;
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
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, PNCUCCLR);
            PNCUCCLR.KND = "C00001";
            PNCUCCLR.CC_NO = PSCINDTP.EXG_CERT_NO;
            PNCUCCLR.ISS_AMT = PSCINDTP.EXG_AMT;
            PNCUCCLR.ISS_DATE = PSCINDTP.CERT_DT;
            PNCUCCLR.ENCRY_NO = PSCINDTP.ENCRY_NO;
            PNCUCCLR.LHD_AC = PSCINDTP.OTH_ACNO;
            PNCUCCLR.LHD_NM = PSCINDTP.OTH_ACNM;
            PNCUCCLR.CLR_CHNL = '0';
            PNCUCCLR.STL_FLG = 'T';
            PNCUCCLR.ENCRY_NO = PSCINDTP.ENCRY_NO;
            PNCUCCLR.FUNC = 'T';
            S000_CALL_PNZUCCLR();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("03") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, PNCUDPAY);
            PNCUDPAY.LHD_AC = PSCINDTP.OTH_ACNO;
            PNCUDPAY.LHD_NM = PSCINDTP.OTH_ACNM;
            PNCUDPAY.DRFT_NO = PSCINDTP.EXG_CERT_NO;
            if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("03")) {
                PNCUDPAY.KND = "C00005";
            } else {
                PNCUDPAY.KND = "C00098";
            }
            PNCUDPAY.ENCRY_NO = PSCINDTP.ENCRY_NO;
            PNCUDPAY.STL_FLG = 'T';
            PNCUDPAY.CLR_CHNL = '0';
            PNCUDPAY.ISS_AMT = PSCINDTP.ISS_AMT;
            PNCUDPAY.BAL_AMT = PSCINDTP.M_AMT;
            PNCUDPAY.STL_AMT = PSCINDTP.EXG_AMT;
            PNCUDPAY.ENCRY_NO = PSCINDTP.ENCRY_NO;
            PNCUDPAY.ISS_DATE = PSCINDTP.CERT_DT;
            PNCUDPAY.FUNC = 'T';
            PNCUDPAY.ENCRY_NO = PSCINDTP.ENCRY_NO;
            S000_CALL_PNZUDPAY();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("01") 
            || PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("05")) {
            IBS.init(SCCGWA, DDCUDRAC);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                DDCUDRAC.CARD_NO = PSCINDTP.OUR_ACNO;
            } else {
                DDCUDRAC.AC = PSCINDTP.OUR_ACNO;
            }
            DDCUDRAC.CCY = PSCINDTP.EXG_CCY;
            DDCUDRAC.CCY_TYPE = PSCINDTP.CASH_ID;
            DDCUDRAC.CHQ_NO = PSCINDTP.EXG_CERT_NO;
            DDCUDRAC.CHQ_ISS_DATE = PSCINDTP.CERT_DT;
            CEP.TRC(SCCGWA, DDCUDRAC.CHQ_ISS_DATE);
            DDCUDRAC.TX_AMT = PSCINDTP.EXG_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("01")) {
                DDCUDRAC.CHQ_TYPE = '2';
            } else {
                DDCUDRAC.CHQ_TYPE = '3';
            }
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUDRAC.BANK_DR_FLG = 'Y';
            DDCUDRAC.TX_MMO = "A352";
            DDCUDRAC.PAY_PSWD = PSCINDTP.PAY_PSWD;
            DDCUDRAC.TX_TYPE = 'T';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        if (PSCINDTP.EXG_VOUCH_CD.equalsIgnoreCase("99")) {
            CEP.TRC(SCCGWA, "PING ZHENG  99");
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                CEP.TRC(SCCGWA, "PING ZHENG  AI99");
                B211_AI_FUAC_TALLY();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DDCUDRAC);
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                    CEP.TRC(SCCGWA, "PING ZHENG  DC99");
                    DDCUDRAC.CARD_NO = PSCINDTP.OUR_ACNO;
                } else {
                    CEP.TRC(SCCGWA, "PING ZHENG  DD99");
                    DDCUDRAC.AC = PSCINDTP.OUR_ACNO;
                }
                DDCUDRAC.CCY = PSCINDTP.EXG_CCY;
                DDCUDRAC.CCY_TYPE = PSCINDTP.CASH_ID;
                DDCUDRAC.TX_AMT = PSCINDTP.EXG_AMT;
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCUDRAC.BANK_DR_FLG = 'Y';
                DDCUDRAC.TX_MMO = "A352";
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.CHK_PSW_FLG = 'N';
                S000_CALL_DDZUDRAC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_DR_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCINDTP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCINDTP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B301_THD_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE2;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCINDTP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCINDTP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B400_WRITE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCINDTP.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCINDTP.EXG_AREA_NO;
        PSRIBLL.KEY.EXG_DT = PSCINDTP.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSCINDTP.EXG_TMS;
        PSRIBLL.KEY.EXG_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        PSRIBLL.OUR_EXG_NO = PSREINF.EXG_NO;
        PSRIBLL.EXG_CCY = PSCINDTP.EXG_CCY;
        PSRIBLL.OTH_EXG_NO = PSCINDTP.OTH_EXG_NO;
        PSRIBLL.EXG_DC = 'D';
        PSRIBLL.EXG_VOUCH_CD = PSCINDTP.EXG_VOUCH_CD;
        PSRIBLL.EXG_CERT_NO = PSCINDTP.EXG_CERT_NO;
        PSRIBLL.EXG_REC_STS = PSCINDTP.RTN_FLG;
        PSRIBLL.EXG_AMT = PSCINDTP.EXG_AMT;
        PSRIBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSRIBLL.OUR_ACNO = PSCINDTP.OUR_ACNO;
        PSRIBLL.OUR_ACNM = PSCINDTP.OUR_ACNM;
        if (PSCINDTP.PBKA_EX_INSNM.trim().length() == 0) PSRIBLL.OTH_BKNO = 0;
        else PSRIBLL.OTH_BKNO = Long.parseLong(PSCINDTP.PBKA_EX_INSNM);
        PSRIBLL.OTH_ACNO = PSCINDTP.OTH_ACNO;
        PSRIBLL.OTH_ACNM = PSCINDTP.OTH_ACNM;
        PSRIBLL.RMK = PSCINDTP.RMK;
        PSRIBLL.RET_RSN_CD = PSCINDTP.RET_RSN_CD;
        if (PSCINDTP.RTN_FLG == '1') {
            PSRIBLL.RET_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        }
        PSRIBLL.RET_RSN_DSC = PSCINDTP.RET_RSN_DSC;
        PSRIBLL.STR_CHNL = SCCGWA.COMM_AREA.CHNL;
        PSRIBLL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRIBLL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRIBLL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRIBLL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRIBLL.ISS_BKNO = PSCINDTP.ISS_BKNO;
        PSRIBLL.CERT_DT = PSCINDTP.CERT_DT;
        PSRIBLL.EXP_DT = PSCINDTP.EXP_DT;
        CEP.TRC(SCCGWA, PSCINDTP.ISS_BKNO);
        CEP.TRC(SCCGWA, PSCINDTP.CERT_DT);
        CEP.TRC(SCCGWA, PSCINDTP.EXP_DT);
        T000_WRITE_PSTIBLL();
        if (pgmRtn) return;
    }
    public void B410_WRITE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCINDTP.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCINDTP.EXG_AREA_NO;
        PSRIBLL.KEY.EXG_DT = PSCINDTP.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSCINDTP.EXG_TMS;
        PSRIBLL.KEY.EXG_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        T000_READ_PSTIBLL_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_OBLL_REC_EXIST;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSROBLL.EXG_REC_STS);
        if (PSROBLL.EXG_REC_STS == '2') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_REC_STS_EXIST;
            WS_ERR_INFO = "iBLL-EXG-REC-STS=" + PSRIBLL.EXG_REC_STS;
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSRIBLL.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_UP_DATE_EXIST;
            Z_RET();
            if (pgmRtn) return;
        }
        if (!PSRIBLL.CRT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_ISS_TLR_EXIST;
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
        BPCPNHIS.INFO.REF_NO = PSCINDTP.EXG_CERT_NO;
        BPCPNHIS.INFO.TX_RMK = PSCINDTP.RMK;
        BPCPNHIS.INFO.NEW_DAT_PT = PSCINDTP;
        BPCPNHIS.INFO.FMT_ID = "PSCINDTP";
        BPCPNHIS.INFO.FMT_ID_LEN = 1154;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        if (PSCINDTP.RTN_FLG == '1') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CANCEL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void B211_AI_FUAC_TALLY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = PSCINDTP.OUR_ACNO;
        AICUUPIA.DATA.CCY = PSCINDTP.EXG_CCY;
        AICUUPIA.DATA.AMT = PSCINDTP.EXG_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSCINDTP.CD_FLG = 'D';
        if (PSCINDTP.CD_FLG == 'D') {
            AICUUPIA.DATA.EVENT_CODE = "DR";
        } else {
            AICUUPIA.DATA.EVENT_CODE = "CR";
        }
        AICUUPIA.DATA.THEIR_AC = PSCINDTP.OTH_ACNO;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_AC);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
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
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            Z_RET();
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
    public void S000_CALL_PNZUCCLR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-CLR-PNT", PNCUCCLR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_PNZUDPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-DRAFT-PAY", PNCUDPAY);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_AIZUUPIA, AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, 0);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
    }
    public void T100_READ_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        IBS.READ(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
