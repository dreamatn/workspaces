package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSMPAY {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5230";
    String K_HIS_CPB_NM = "DDCHMPAY";
    String K_HIS_RMKS = "MODIFY PAY METHOD";
    String K_OUTPUT_FMT = "DD063";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    short WS_NEW_LINE = 0;
    short WS_OLD_LINE = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DDCOMPAY DDCOMPAY = new DDCOMPAY();
    DDCHMPAY DDCMPAYO = new DDCHMPAY();
    DDCHMPAY DDCMPAYN = new DDCHMPAY();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICACCU CICACCU = new CICACCU();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSMPAY DDCSMPAY;
    public void MP(SCCGWA SCCGWA, DDCSMPAY DDCSMPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMPAY = DDCSMPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSMPAY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_GET_AC_INF_PROC();
        B035_CHK_AC();
        B040_CI_INF_PROC();
        B050_CHECK_STS_TBL_PROC();
        B070_CHK_PSBK_PROC();
        B080_MOD_MTH_PROC();
        B090_NFIN_TXN_HIS_PROC();
        B100_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSMPAY.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSMPAY.PAY_MTH_NEW != '1' 
            && DDCSMPAY.PAY_MTH_NEW != '2' 
            && DDCSMPAY.PAY_MTH_NEW != '3' 
            && DDCSMPAY.PAY_MTH_NEW != '4' 
            && DDCSMPAY.PAY_MTH_NEW != '5') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (DDCSMPAY.PAY_MTH_NEW == '3') {
            if (DDCSMPAY.ID_TYPE.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYP_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (DDCSMPAY.ID_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSMPAY.PAY_MTH_OLD == '1' 
            && DDCSMPAY.PSWD_O.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_PSW_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSMPAY.AC;
        T000_READUPD_DDTMST();
    }
    public void B035_CHK_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDCSMPAY.CRN);
        CEP.TRC(SCCGWA, DDCSMPAY.DRN);
        CEP.TRC(SCCGWA, DDCSMPAY.OIC_NO);
        CEP.TRC(SCCGWA, DDCSMPAY.PAY_MTH_OLD);
        CEP.TRC(SCCGWA, DDCSMPAY.PAY_MTH_NEW);
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (DDRMST.CROS_DR_FLG == '2' 
            && DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ON_OPBR_CAN_MOD_ACIF);
        }
        if (DDCSMPAY.PAY_MTH_OLD == DDCSMPAY.PAY_MTH_NEW 
            && DDCSMPAY.DRN == DDRMST.CROS_DR_FLG 
            && DDCSMPAY.CRN == DDRMST.CROS_CR_FLG) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE);
        }
        DDRMST.CROS_CR_FLG = DDCSMPAY.CRN;
        DDRMST.CROS_DR_FLG = DDCSMPAY.DRN;
        DDRMST.AC_OIC_NO = DDCSMPAY.OIC_NO;
        T000_REWRITE_DDTMST();
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSMPAY.AC;
        S000_CALL_CISOACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(100);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD.substring(0, 99) + BPCFCSTS.STATUS_WORD.substring(101 + 199 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B070_CHK_PSBK_PROC() throws IOException,SQLException,Exception {
        if (DDCSMPAY.PAY_MTH_OLD == '1' 
            || DDCSMPAY.PAY_MTH_OLD == '4') {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.CARD_NO = DDCSMPAY.CARD_NO;
            DDCIMPAY.AC = DDCSMPAY.AC;
            DDCIMPAY.FUNC = 'C';
            DDCIMPAY.PSWD_OLD = DDCSMPAY.PSWD_O;
            S000_CALL_DDZIMPAY();
        }
    }
    public void B080_MOD_MTH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMPAY.PAY_MTH_OLD);
        CEP.TRC(SCCGWA, DDCSMPAY.PAY_MTH_NEW);
        if (DDCSMPAY.PAY_MTH_OLD != DDCSMPAY.PAY_MTH_NEW) {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.CARD_NO = DDCSMPAY.CARD_NO;
            DDCIMPAY.AC = DDCSMPAY.AC;
            DDCIMPAY.FUNC = 'M';
            DDCIMPAY.PAY_MTH = DDCSMPAY.PAY_MTH_NEW;
            DDCIMPAY.PSWD_OLD = DDCSMPAY.PSWD_O;
            DDCIMPAY.PSWD_NEW = DDCSMPAY.PSWD1;
            DDCIMPAY.AC_CNAME = CICACCU.DATA.CI_CNM;
            DDCIMPAY.ID_TYPE = DDCSMPAY.ID_TYPE;
            if (DDCSMPAY.ID_TYPE.trim().length() == 0) {
                DDCIMPAY.ID_TYPE = CICACCU.DATA.ID_TYPE;
            }
            DDCIMPAY.ID_NO = DDCSMPAY.ID_NO;
            if (DDCSMPAY.ID_NO.trim().length() == 0) {
                DDCIMPAY.ID_NO = CICACCU.DATA.ID_NO;
            }
            DDCIMPAY.SIGN_NO = DDCSMPAY.SIGN_NO;
            CEP.TRC(SCCGWA, DDCIMPAY.ID_TYPE);
            CEP.TRC(SCCGWA, DDCIMPAY.ID_NO);
            CEP.TRC(SCCGWA, DDCIMPAY.PAY_MTH);
            S000_CALL_DDZIMPAY();
        }
    }
    public void B090_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCMPAYO);
        DDCMPAYO.PAY_MTH = DDCSMPAY.PAY_MTH_OLD;
        IBS.init(SCCGWA, DDCMPAYN);
        DDCMPAYN.PAY_MTH = DDCSMPAY.PAY_MTH_NEW;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCMPAYO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCMPAYN;
        BPCPNHIS.INFO.AC = DDCSMPAY.AC;
        BPCPNHIS.INFO.CCY = CICACCU.DATA.CCY;
        if (CICACCU.DATA.CCY.equalsIgnoreCase("156") 
            && CICACCU.DATA.CCY.trim().length() > 0) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.TX_TOOL = DDCSMPAY.CARD_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P160";
        S000_CALL_BPZPNHIS();
    }
    public void B100_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOMPAY);
        DDCOMPAY.CARD_NO = DDCSMPAY.CARD_NO;
        DDCOMPAY.AC = DCCPACTY.INPUT.AC;
        DDCOMPAY.AC_CNAME = DDCSMPAY.AC_CNAME;
        DDCOMPAY.AC_ENAME = DDCSMPAY.AC_ENAME;
        DDCOMPAY.PSBK_NO = DDCSMPAY.PSBK_NO;
        DDCOMPAY.PAY_MTH_OLD = DDCSMPAY.PAY_MTH_OLD;
        DDCOMPAY.PAY_MTH_NEW = DDCSMPAY.PAY_MTH_NEW;
        DDCOMPAY.CIPW_FLG = DDCSMPAY.CIPW_FLG;
        DDCOMPAY.UCIP_FLG = DDCSMPAY.UCIP_FLG;
        DDCOMPAY.PSWD = DDCSMPAY.PSWD1;
        DDCOMPAY.ID_TYPE = DDCSMPAY.ID_TYPE;
        DDCOMPAY.ID_NO = DDCSMPAY.ID_NO;
        DDCOMPAY.SIGN_NO = DDCSMPAY.SIGN_NO;
        DDCOMPAY.CRN = DDCSMPAY.CRN;
        DDCOMPAY.DRN = DDCSMPAY.DRN;
        DDCOMPAY.OIC_NO = DDCSMPAY.OIC_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOMPAY;
        SCCFMT.DATA_LEN = 711;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHK_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSMPAY.AC;
        DCCPACTY.INPUT.FUNC = '3';
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
        DDCSMPAY.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        CEP.TRC(SCCGWA, DDCSMPAY.AC);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_TYPE,AC_STS,AC_STS_WORD,CROS_DR_FLG";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
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
