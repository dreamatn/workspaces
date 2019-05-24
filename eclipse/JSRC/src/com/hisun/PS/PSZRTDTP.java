package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZRTDTP {
    DBParm PSTOBLL_RD;
    DBParm PSTIBLL_RD;
    DBParm PSTEINF_RD;
    DBParm PSTPBIN_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_EXG_BK_NO = "001";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String K_CNTR_TYPE = "LOCL";
    String K_EVENT_CODE = "OWDRR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    char WS_TABLE_FLG = ' ';
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
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    String WK_INSNM = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCRTDTP PSCRTDTP;
    public void MP(SCCGWA SCCGWA, PSCRTDTP PSCRTDTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCRTDTP = PSCRTDTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZRTDTP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_CHECK_TRAN_DATA();
        B221_PNT_EVT_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B301_WRITE_PSTOBLL();
            B400_RWRITE_PSTIBLL();
        } else {
            B300_WRITE_PSTOBLL();
            B400_WRITE_PSTIBLL();
        }
        B400_BP_NHIS();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCRTDTP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, PSCRTDTP.EXG_RT_DT);
        if (PSCRTDTP.EXG_RT_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_DT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.EXG_RT_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.OUR_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_M_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.OUR_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.OUR_ACNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ORD_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.CASH_ID == ' ') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CASH_ID_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.EXG_AMT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AMT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.EXG_VOUCH_CD.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_VOUCH_CD_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.EXG_CERT_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CERT_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.OTH_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REC_BR_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.OTH_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.OTH_ACNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.RET_RSN_CD.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REF_REASON_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.RET_RSN_CD.equalsIgnoreCase("99")) {
            if (PSCRTDTP.RET_RSN_DSC.trim().length() == 0) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CL_REASON_MST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (PSCRTDTP.TX_JRNNO == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_JRN_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCRTDTP.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCRTDTP.EXG_RT_TMS;
        PSROBLL.KEY.EXG_JRN_NO = PSCRTDTP.TX_JRNNO;
        CEP.TRC(SCCGWA, PSCRTDTP.BK_NO);
        CEP.TRC(SCCGWA, PSCRTDTP.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCRTDTP.EXG_RT_DT);
        CEP.TRC(SCCGWA, PSCRTDTP.EXG_RT_TMS);
        CEP.TRC(SCCGWA, PSCRTDTP.TX_JRNNO);
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, PSROBLL.EXG_REC_STS);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (PSROBLL.EXG_REC_STS != '2') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_JL_STS_MST_ERR;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (PSROBLL.EXG_REC_STS != '4') {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_JL_STS_MST_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCRTDTP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        T000_READ_PSTEINF();
        if (!PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TRT_NOT_ALLOW;
            S000_ERR_MSG_PROC();
        }
        WK_INSNM = PSCRTDTP.PBKA_EX_INSNM;
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSRPBIN.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_CCY = PSCRTDTP.EXG_CCY;
        PSRPBIN.KEY.EXG_NO = PSCRTDTP.OTH_EXG_NO;
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_NO);
        T100_READ_PSTPBIN();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, 11111111);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_OTH_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCRTDTP.EXG_RT_TMS > PSRPBIN.EXG_NB) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_R_TMS_OVR_MAX;
            S000_ERR_MSG_PROC();
        }
    }
    public void B221_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCRTDTP.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCRTDTP.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        S000_CALL_BPZPOEWA();
    }
    public void B300_WRITE_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCRTDTP.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCRTDTP.EXG_RT_TMS;
        PSROBLL.EXG_CERT_NO = PSCRTDTP.EXG_CERT_NO;
        PSROBLL.KEY.EXG_JRN_NO = PSCRTDTP.TX_JRNNO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, "GENG XIN ");
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        PSTOBLL_RD.upd = true;
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, 0);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
        CEP.TRC(SCCGWA, "JIN  E ");
        if (PSROBLL.EXG_AMT != PSCRTDTP.EXG_AMT) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXG_AMT_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_DT);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_TMS);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_JRN_NO);
        CEP.TRC(SCCGWA, PSROBLL.EXG_AMT);
        CEP.TRC(SCCGWA, PSROBLL.EXG_CERT_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_EXG_REC_STS = '4';
        PSROBLL.EXG_REC_STS = WS_EXG_REC_STS;
        PSROBLL.RET_RSN_CD = PSCRTDTP.RET_RSN_CD;
        PSROBLL.RET_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        PSROBLL.RET_RSN_DSC = PSCRTDTP.RET_RSN_DSC;
        PSROBLL.RMK = PSCRTDTP.RMK;
        T000_REWRITE_PSTOBLL();
    }
    public void B301_WRITE_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCRTDTP.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCRTDTP.EXG_RT_TMS;
        PSROBLL.EXG_CERT_NO = PSCRTDTP.EXG_CERT_NO;
        PSROBLL.KEY.EXG_JRN_NO = PSCRTDTP.TX_JRNNO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        PSTOBLL_RD.upd = true;
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, 0);
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, 2222222222);
        }
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_DT);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_TMS);
        CEP.TRC(SCCGWA, PSROBLL.KEY.EXG_JRN_NO);
        CEP.TRC(SCCGWA, PSROBLL.EXG_AMT);
        CEP.TRC(SCCGWA, PSROBLL.EXG_CERT_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_EXG_REC_STS = '2';
        PSROBLL.EXG_REC_STS = WS_EXG_REC_STS;
        T000_REWRITE_PSTOBLL();
    }
    public void B400_RWRITE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_JRN_NO = PSCRTDTP.TX_JRNNO;
        CEP.TRC(SCCGWA, PSCRTDTP.TX_JRNNO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        PSTIBLL_RD = new DBParm();
        PSTIBLL_RD.TableName = "PSTIBLL";
        PSTIBLL_RD.where = "EXG_JRN_NO = :PSRIBLL.KEY.EXG_JRN_NO";
        PSTIBLL_RD.upd = true;
        IBS.READ(SCCGWA, PSRIBLL, this, PSTIBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, "READ PSTIBLL NOT FOUND");
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, "READ PSTIBLL END");
        }
        CEP.TRC(SCCGWA, PSRIBLL.KEY.EXG_JRN_NO);
        PSRIBLL.EXG_REC_STS = '2';
        T000_DELETE_PSTIBLL();
    }
    public void B400_BP_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = PSCRTDTP.OUR_ACNO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = PSCRTDTP.EXG_CERT_NO;
        BPCPNHIS.INFO.NEW_DAT_PT = PSCRTDTP;
        BPCPNHIS.INFO.FMT_ID = "PSB1400";
        BPCPNHIS.INFO.FMT_ID_LEN = 1132;
        S000_CALL_BPZPNHIS();
    }
    public void B400_WRITE_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCRTDTP.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCRTDTP.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCRTDTP.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        T000_READ_PSTEINF();
        PSRIBLL.KEY.EXG_DT = PSREINF.EXG_DT;
        PSRIBLL.KEY.EXG_TMS = PSREINF.EXG_TMS;
        PSRIBLL.KEY.EXG_JRN_NO = PSCRTDTP.TX_JRNNO;
        PSRIBLL.OUR_EXG_NO = PSCRTDTP.OUR_EXG_NO;
        PSRIBLL.EXG_CCY = PSCRTDTP.EXG_CCY;
        PSRIBLL.OTH_EXG_NO = PSCRTDTP.OTH_EXG_NO;
        PSRIBLL.EXG_DC = 'D';
        PSRIBLL.EXG_VOUCH_CD = "98";
        PSRIBLL.EXG_CERT_NO = PSCRTDTP.EXG_CERT_NO;
        PSRIBLL.EXG_AMT = PSCRTDTP.EXG_AMT;
        PSRIBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSRIBLL.OUR_ACNO = PSCRTDTP.OUR_ACNO;
        PSRIBLL.OUR_ACNM = PSCRTDTP.OUR_ACNM;
        if (PSCRTDTP.PBKA_EX_INSNM.trim().length() == 0) PSRIBLL.OTH_BKNO = 0;
        else PSRIBLL.OTH_BKNO = Long.parseLong(PSCRTDTP.PBKA_EX_INSNM);
        PSRIBLL.OTH_ACNO = PSCRTDTP.OTH_ACNO;
        PSRIBLL.OTH_ACNM = PSCRTDTP.OTH_ACNM;
        PSRIBLL.RMK = PSCRTDTP.RMK;
        PSRIBLL.RET_RSN_CD = PSCRTDTP.RET_RSN_CD;
        PSRIBLL.RET_RSN_DSC = PSCRTDTP.RET_RSN_DSC;
        PSRIBLL.STR_CHNL = SCCGWA.COMM_AREA.CHNL;
        PSRIBLL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRIBLL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRIBLL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PSRIBLL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PSRIBLL.ISS_BKNO = PSCRTDTP.ISS_BKNO;
        PSRIBLL.CERT_DT = PSCRTDTP.CERT_DT;
        PSRIBLL.EXP_DT = PSCRTDTP.EXP_DT;
        T000_WRITE_PSTIBLL();
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
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
    public void T000_REWRITE_PSTOBLL() throws IOException,SQLException,Exception {
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.REWRITE(SCCGWA, PSROBLL, PSTOBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_OBLL_REC_EXIST;
            CEP.TRC(SCCGWA, WS_TABLE_FLG);
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTOBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
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
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_DELETE_PSTIBLL() throws IOException,SQLException,Exception {
        PSTIBLL_RD = new DBParm();
        PSTIBLL_RD.TableName = "PSTIBLL";
        IBS.DELETE(SCCGWA, PSRIBLL, PSTIBLL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
            CEP.TRC(SCCGWA, WS_TABLE_FLG);
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTIBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
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
