package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCUCINF;
import com.hisun.DC.DCRCDDAT;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDRMST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PSZRTCRP {
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE = "OWCRR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    char WS_TABLE_FLG = ' ';
    char WS_AC_STS = ' ';
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
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    DDRMST DDRMST = new DDRMST();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PSCRTCRP PSCRTCRP;
    public void MP(SCCGWA SCCGWA, PSCRTCRP PSCRTCRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCRTCRP = PSCRTCRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZRTCRP return!");
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
        B300_PNT_EVT_PROC();
        if (pgmRtn) return;
        if (WS_AC_STS == 'Y') {
            B310_DD_CAC_TALLY();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B401_WRITE_PSTOBLL();
            if (pgmRtn) return;
            B400_RWRITE_PSTIBLL();
            if (pgmRtn) return;
        } else {
            B400_WRITE_PSTOBLL();
            if (pgmRtn) return;
            B400_WRITE_PSTIBLL();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCRTCRP.EXG_RT_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.EXG_RT_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.OUR_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_M_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.OUR_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.OUR_ACNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.CASH_ID == ' ') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CASH_ID_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.EXG_AMT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AMT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.EXG_VOUCH_CD.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_VOUCH_CD_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.EXG_VOUCH_CD.equalsIgnoreCase("05")) {
            if (PSCRTCRP.EXG_CERT_NO.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CERT_NO_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PSCRTCRP.OTH_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REC_BR_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.OTH_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.OTH_ACNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.RET_RSN_CD.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REF_REASON_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCRTCRP.RET_RSN_CD.equalsIgnoreCase("99")) {
            if (PSCRTCRP.RET_RSN_DSC.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CL_REASON_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PSCRTCRP.TX_JRNNO == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_JRN_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCRTCRP.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCRTCRP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCRTCRP.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCRTCRP.EXG_RT_TMS;
        PSROBLL.KEY.EXG_JRN_NO = PSCRTCRP.TX_JRNNO;
        CEP.TRC(SCCGWA, PSCRTCRP.BK_NO);
        CEP.TRC(SCCGWA, PSCRTCRP.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCRTCRP.EXG_RT_DT);
        CEP.TRC(SCCGWA, PSCRTCRP.EXG_RT_TMS);
        CEP.TRC(SCCGWA, PSCRTCRP.TX_JRNNO);
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, PSROBLL.EXG_REC_STS);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (PSROBLL.EXG_REC_STS != '1') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TD_STS_MST_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (PSROBLL.EXG_REC_STS != '4') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TD_STS_MST_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCRTCRP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCRTCRP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCRTCRP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        T000_READ_PSTEINF();
        if (pgmRtn) return;
        if (!PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRT_NOT_ALLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCRTCRP.EXG_RT_DT);
        CEP.TRC(SCCGWA, PSREINF.EXG_DT);
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCRTCRP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCRTCRP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCRTCRP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCRTCRP.OTH_EXG_NO;
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
        PSRPBIN.KEY.EXG_BK_NO = PSCRTCRP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCRTCRP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCRTCRP.EXG_CCY;
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
        if (PSCRTCRP.EXG_RT_TMS > PSRPBIN.EXG_NB) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_OUR_AC_CHEK() throws IOException,SQLException,Exception {
        if (PSCRTCRP.OUR_ACNO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSCRTCRP.OUR_ACNO;
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
        CEP.TRC(SCCGWA, PSCRTCRP.OUR_ACNO);
        CEP.TRC(SCCGWA, "ZHANG HU LEI XING ");
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, "WO SHI SHUI ");
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.TX_TYPE = 'I';
            DDCIMMST.DATA.KEY.AC_NO = PSCRTCRP.OUR_ACNO;
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "NI SHI WO ");
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
