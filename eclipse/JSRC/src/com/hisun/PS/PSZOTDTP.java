package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCAOTH;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCUCNGM;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCUCINF;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCIMMST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PSZOTDTP {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "PS120";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String K_TBL_BCC = "PSTOBLL";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE = "OWDR";
    String K_EVENT_CODE2 = "IWDRRT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    PSZOTDTP_WS_FMT WS_FMT = new PSZOTDTP_WS_FMT();
    char WS_TABLE_FLG = ' ';
    char WS_STS_FLG = ' ';
    char WS_EXG_REC_STS = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSRCHQ DDCSRCHQ = new DDCSRCHQ();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCAOTH BPCAOTH = new BPCAOTH();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    char WK_DC = ' ';
    String WK_CERT_NO = " ";
    String WK_INSNM = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PSCOTDTP PSCOTDTP;
    public void MP(SCCGWA SCCGWA, PSCOTDTP PSCOTDTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCOTDTP = PSCOTDTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZOTDTP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_CHECK_TRAN_DATA();
        if (pgmRtn) return;
        B210_OUR_AC_CHEK();
        if (pgmRtn) return;
        if (!PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("98")) {
            B220_ACC_CONCT();
            if (pgmRtn) return;
        } else {
            B330_PNT_EVT_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B301_OBLL_MST_PROC();
            if (pgmRtn) return;
        } else {
            B300_WRITE_PSTOBLL();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCOTDTP.EXG_AREA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.EXG_DT == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_R_DT_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCOTDTP.EXG_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (PSCOTDTP.EXG_DT < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_EXG_AC_EXIT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.EXG_TMS == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_R_TMS_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCOTDTP.SHL_EXG_DT);
        if (PSCOTDTP.SHL_EXG_DT == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_EX_DT_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.SHL_EXG_TMS == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_EX_TMS_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.OTH_EXG_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_REC_BR_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.OTH_ACNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ORD_AC_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.OTH_ACNM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ORD_NM_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.EXG_CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!PSCOTDTP.EXG_CCY.equalsIgnoreCase("156")) {
            if (PSCOTDTP.CASH_ID == ' ') {
                IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_NT_CHG_MST_INPUT, PSCOTDTP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (PSCOTDTP.EXG_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_AMT_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.EXG_VOUCH_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_VOUCH_CD_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("05")) {
                IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_VOUCH_NO5_MST_INPUT, PSCOTDTP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("03") 
            || PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("04")) {
            if (PSCOTDTP.ISS_AMT == 0) {
                IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ISS_AMT_MST_INPUT, PSCOTDTP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("03") 
            || PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("04")) {
            if (PSCOTDTP.M_AMT < 0) {
                IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_M_AMT_MST_INPUT, PSCOTDTP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (PSCOTDTP.EXG_CERT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_CERT_NO_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.OUR_ACNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BF_AC_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.OUR_ACNM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BF_NM_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCOTDTP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCOTDTP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCOTDTP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        T000_READ_PSTEINF();
        if (pgmRtn) return;
        if (!PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TRT_NOT_ALLOW, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.EXG_DT != PSREINF.EXG_DT) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_TRTDT_NOT_EQUAL_EXDT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCOTDTP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCOTDTP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCOTDTP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCOTDTP.OTH_EXG_NO;
        CEP.TRC(SCCGWA, PSCOTDTP.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCOTDTP.EXG_CCY);
        CEP.TRC(SCCGWA, PSCOTDTP.OTH_EXG_NO);
        WK_INSNM = PSCOTDTP.PBKA_EX_INSNM;
        T000_READ_PSTPBIN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, 11111111);
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_EX_NO_OTH_MST_INPUT, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSCOTDTP.EXG_TMS > PSRPBIN.EXG_NB) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_OUR_AC_CHEK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSCOTDTP.OUR_ACNO);
        if (PSCOTDTP.OUR_ACNO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSCOTDTP.OUR_ACNO;
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
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.TX_TYPE = 'I';
            DDCIMMST.DATA.KEY.AC_NO = PSCOTDTP.OUR_ACNO;
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD);
            if (DDCIMMST.DATA.AC_TYPE == '2') {
                IBS.init(SCCGWA, DDCIMCCY);
                DDCIMCCY.DATA[1-1].AC = PSCOTDTP.OUR_ACNO;
                DDCIMCCY.DATA[1-1].CCY = PSCOTDTP.EXG_CCY;
                DDCIMCCY.DATA[1-1].CCY_TYPE = PSCOTDTP.CASH_ID;
                S000_CALL_DDZIMCCY();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
                if (!DDCIMCCY.DATA[1-1].CCY.equalsIgnoreCase(PSCOTDTP.EXG_CCY)) {
                    WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRAN_AC_CCY_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDCIMCCY.DATA[1-1].STS == 'C') {
                    WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_NO_OUR_ACNO_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B220_ACC_CONCT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCOTDTP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCOTDTP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B330_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE2;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCOTDTP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCOTDTP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B300_WRITE_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCOTDTP.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCOTDTP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCOTDTP.EXG_DT;
        PSROBLL.KEY.EXG_TMS = PSCOTDTP.EXG_TMS;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        PSROBLL.KEY.EXG_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        PSROBLL.OUR_EXG_NO = PSREINF.EXG_NO;
        PSROBLL.EXG_CCY = PSCOTDTP.EXG_CCY;
        PSROBLL.OTH_EXG_NO = PSCOTDTP.OTH_EXG_NO;
        PSROBLL.EXG_DC = PSCOTDTP.DC;
        PSROBLL.EXG_VOUCH_CD = PSCOTDTP.EXG_VOUCH_CD;
        PSROBLL.EXG_CERT_NO = PSCOTDTP.EXG_CERT_NO;
        PSROBLL.ISS_AMT = PSCOTDTP.ISS_AMT;
        if (PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("98")) {
            WS_EXG_REC_STS = '1';
        } else {
            WS_EXG_REC_STS = '2';
        }
        PSROBLL.EXG_REC_STS = WS_EXG_REC_STS;
        PSROBLL.EXG_AMT = PSCOTDTP.EXG_AMT;
        PSROBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSROBLL.OUR_ACNO = PSCOTDTP.OUR_ACNO;
        PSROBLL.OUR_ACNM = PSCOTDTP.OUR_ACNM;
        PSROBLL.OTH_ACNO = PSCOTDTP.OTH_ACNO;
        PSROBLL.OTH_ACNM = PSCOTDTP.OTH_ACNM;
        PSROBLL.CASH_ID = PSCOTDTP.CASH_ID;
        if (!PSCOTDTP.EXG_VOUCH_CD.equalsIgnoreCase("98")) {
            PSROBLL.SHL_EXG_DT = PSCOTDTP.SHL_EXG_DT;
            PSROBLL.SHL_EXG_TMS = PSCOTDTP.SHL_EXG_TMS;
            PSROBLL.EXG_REPT_FLG = '0';
        }
        PSROBLL.RMK = PSCOTDTP.RMK;
        PSROBLL.STR_CHNL = SCCGWA.COMM_AREA.CHNL;
        PSROBLL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSROBLL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSROBLL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSROBLL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSROBLL.ISS_BKNO = PSCOTDTP.ISS_BKNO;
        PSROBLL.CERT_DT = PSCOTDTP.CERT_DT;
        PSROBLL.EXP_DT = PSCOTDTP.EXP_DT;
        PSROBLL.ENCRY_NO = PSCOTDTP.ENCRY_NO;
        CEP.TRC(SCCGWA, "99999");
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_DT);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_TMS);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_JRN_NO);
        CEP.TRC(SCCGWA, PSROBLL.OUR_EXG_NO);
        CEP.TRC(SCCGWA, PSROBLL.EXG_CCY);
        CEP.TRC(SCCGWA, PSROBLL.OTH_EXG_NO);
        CEP.TRC(SCCGWA, PSROBLL.EXG_DC);
        CEP.TRC(SCCGWA, PSROBLL.EXG_VOUCH_CD);
        CEP.TRC(SCCGWA, PSROBLL.EXG_CERT_NO);
        CEP.TRC(SCCGWA, PSROBLL.ISS_AMT);
        CEP.TRC(SCCGWA, PSROBLL.EXG_REC_STS);
        CEP.TRC(SCCGWA, PSROBLL.EXG_AMT);
        CEP.TRC(SCCGWA, PSROBLL.EXG_TX_BR);
        CEP.TRC(SCCGWA, PSROBLL.OUR_ACNO);
        CEP.TRC(SCCGWA, PSROBLL.OUR_ACNM);
        CEP.TRC(SCCGWA, PSROBLL.OTH_ACNO);
        CEP.TRC(SCCGWA, PSROBLL.OTH_ACNM);
        CEP.TRC(SCCGWA, PSROBLL.CREV_NO);
        CEP.TRC(SCCGWA, PSROBLL.CASH_ID);
        CEP.TRC(SCCGWA, PSROBLL.SHL_EXG_DT);
        CEP.TRC(SCCGWA, PSROBLL.SHL_EXG_TMS);
        CEP.TRC(SCCGWA, PSROBLL.EXG_REPT_FLG);
        CEP.TRC(SCCGWA, PSROBLL.RMK);
        CEP.TRC(SCCGWA, PSROBLL.STR_CHNL);
        CEP.TRC(SCCGWA, PSROBLL.CRT_DATE);
        CEP.TRC(SCCGWA, PSROBLL.CRT_TLR);
        CEP.TRC(SCCGWA, PSROBLL.UPDTBL_DATE);
        CEP.TRC(SCCGWA, PSROBLL.UPDTBL_TLR);
        CEP.TRC(SCCGWA, PSROBLL.ISS_BKNO);
        CEP.TRC(SCCGWA, PSROBLL.CERT_DT);
        CEP.TRC(SCCGWA, PSROBLL.EXP_DT);
        CEP.TRC(SCCGWA, PSROBLL.CREV_NO);
        CEP.TRC(SCCGWA, PSROBLL.ENCRY_NO);
        T000_WRITE_PSTOBLL();
        if (pgmRtn) return;
    }
    public void B301_OBLL_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCOTDTP.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCOTDTP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCOTDTP.EXG_DT;
        PSROBLL.KEY.EXG_TMS = PSCOTDTP.EXG_TMS;
        PSROBLL.KEY.EXG_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        T000_READ_PSTOBLL_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_OBLL_REC_EXIST, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSROBLL.EXG_REC_STS);
        if (PSROBLL.EXG_REC_STS != '2') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ERR_REC_STS_EXIST, PSCOTDTP.RC);
            WS_ERR_INFO = "OBLL-EXG-REC-STS=" + PSROBLL.EXG_REC_STS;
            Z_RET();
            if (pgmRtn) return;
        }
        if (PSROBLL.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ERR_UP_DATE_EXIST, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!PSROBLL.CRT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_ERR_ISS_TLR_EXIST, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        PSROBLL.EXG_REC_STS = '3';
        PSROBLL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSROBLL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PSTOBLL();
        if (pgmRtn) return;
    }
    public void B310_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, BPCQCNGL);
            IBS.init(SCCGWA, BPCAOTH);
            BPCQCNGL.DAT.INPUT.CNTR_TYPE = K_CNTR_TYPE;
            BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 10;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAOTH;
            S000_CALL_BPZQCNGL();
            if (pgmRtn) return;
        }
    }
    public void B311_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.KEY.AC = PSCOTDTP.OUR_ACNO;
            BPCUCNGM.KEY.CNTR_TYPE = K_CNTR_TYPE;
            BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
            BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
            BPCUCNGM.FUNC = 'A';
            S000_CALL_BPZUCNGM();
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
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_RGSA_REC_EXIST, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
    }
    public void T000_READ_PSTPBIN() throws IOException,SQLException,Exception {
        PSTPBIN_RD = new DBParm();
        PSTPBIN_RD.TableName = "PSTPBIN";
        IBS.READ(SCCGWA, PSRPBIN, PSTPBIN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_WRITE_PSTOBLL() throws IOException,SQLException,Exception {
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.WRITE(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_RGSA_REC_EXIST, PSCOTDTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PSTOBLL_UPD() throws IOException,SQLException,Exception {
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        PSTOBLL_RD.upd = true;
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
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
    public void T000_REWRITE_PSTOBLL() throws IOException,SQLException,Exception {
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        PSTOBLL_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, PSROBLL, PSTOBLL_RD);
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
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
