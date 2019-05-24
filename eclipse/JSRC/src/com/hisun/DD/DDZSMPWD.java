package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSMPWD {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "0001";
    String K_HIS_CPB_NM = "DDCHMPWD";
    String K_HIS_RMKS1 = "MODIFY PASSWORD";
    String K_HIS_RMKS2 = "RESET PASSWORD";
    String K_HIS_RMKS3 = "UNLOCK PASSWORD";
    String K_HIS_RMKS4 = "CHECK PASSWORD";
    String K_HIS_RMKS5 = "SET PASSWORD";
    String K_OUTPUT_FMT = "DD064";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DDCOMPWD DDCOMPWD = new DDCOMPWD();
    DDCHMPWD DDCMPAYO = new DDCHMPWD();
    DDCHMPWD DDCMPAYN = new DDCHMPWD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSMPWD DDCSMPWD;
    public void MP(SCCGWA SCCGWA, DDCSMPWD DDCSMPWD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMPWD = DDCSMPWD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSMPWD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_GET_AC_INF_PROC();
        B035_CHK_AC_STS();
        B040_CI_INF_PROC();
        if (DDCSMPWD.FUNC != 'K') {
            B050_CHECK_STS_TBL_PROC();
        }
        B080_MOD_PSW_PROC();
        B090_NFIN_TXN_HIS_PROC();
        B100_OUTPUT_PROC();
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        if (DDCSMPWD.AC.trim().length() > 0) {
            DCCPACTY.INPUT.FUNC = '3';
            DCCPACTY.INPUT.AC = DDCSMPWD.AC;
        } else {
            if (DDCSMPWD.CARD_NO.trim().length() > 0) {
                DCCPACTY.INPUT.FUNC = '2';
                DCCPACTY.INPUT.AC = DDCSMPWD.CARD_NO;
            }
        }
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        DDCSMPWD.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        CEP.TRC(SCCGWA, DDCSMPWD.AC);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMPWD.FUNC);
        if (DDCSMPWD.FUNC != 'P' 
            && DDCSMPWD.FUNC != 'R' 
            && DDCSMPWD.FUNC != 'U' 
            && DDCSMPWD.FUNC != 'A' 
            && DDCSMPWD.FUNC != 'K') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (DDCSMPWD.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSMPWD.FUNC == 'P') {
            if (DDCSMPWD.PSWD_OLD.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_PSW_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSMPWD.FUNC != 'U') {
            if (DDCSMPWD.PSWD_OLD.equalsIgnoreCase(DDCSMPWD.PSWD_NEW)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_NEW_PSW_EQUAL;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSMPWD.AC;
        T000_READ_DDTMST();
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMPWD.FUNC);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1));
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCSMPWD.FUNC == 'P' 
            && DDRMST.AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HAS_BEEN_PLOS;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if ((DDCSMPWD.FUNC == 'P' 
            || DDCSMPWD.FUNC == 'U' 
            || DDCSMPWD.FUNC == 'R') 
            && DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if ((DDCSMPWD.FUNC == 'P' 
            || DDCSMPWD.FUNC == 'U' 
            || DDCSMPWD.FUNC == 'R') 
            && DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR);
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSMPWD.AC;
        S000_CALL_CISOACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSMPWD.ID_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_NO);
        CEP.TRC(SCCGWA, DDCSMPWD.ID_NO);
        if (DDCSMPWD.FUNC == 'R' 
            || DDCSMPWD.FUNC == 'U') {
            if (!DDCSMPWD.ID_TYP.equalsIgnoreCase(CICACCU.DATA.ID_TYPE) 
                || !DDCSMPWD.ID_NO.equalsIgnoreCase(CICACCU.DATA.ID_NO)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NOT_MACTH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
        JIBS_tmp_int = CICACCU.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.STSW + BPCFCSTS.STATUS_WORD.substring(8);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 61 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(61 + 80 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B080_MOD_PSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMPAY);
        if (DDCSMPWD.FUNC == 'P') {
            DDCIMPAY.FUNC = 'P';
        } else if (DDCSMPWD.FUNC == 'R') {
            DDCIMPAY.FUNC = 'R';
        } else if (DDCSMPWD.FUNC == 'U') {
            DDCIMPAY.FUNC = 'U';
        } else if (DDCSMPWD.FUNC == 'A') {
            DDCIMPAY.FUNC = 'A';
        } else if (DDCSMPWD.FUNC == 'K') {
            DDCIMPAY.FUNC = 'K';
        } else {
        }
        CEP.TRC(SCCGWA, DDCSMPWD.CARD_NO);
        DDCIMPAY.CARD_NO = DDCSMPWD.CARD_NO;
        DDCIMPAY.AC = DDCSMPWD.AC;
        DDCIMPAY.ID_TYPE = DDCSMPWD.ID_TYP;
        DDCIMPAY.ID_NO = DDCSMPWD.ID_NO;
        DDCIMPAY.AC_CNAME = DDCSMPWD.AC_CNAME;
        DDCIMPAY.PAY_MTH = DDCSMPWD.PAY_MTH;
        DDCIMPAY.PSWD_OLD = DDCSMPWD.PSWD_OLD;
        DDCIMPAY.PSWD_NEW = DDCSMPWD.PSWD_NEW;
        DDCIMPAY.LOS_NO = DDCSMPWD.LOS_NO;
        S000_CALL_DDZIMPAY();
    }
    public void B060_CHK_LOSS_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (!DDRMST.AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_PSW_LOSS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B090_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCMPAYO);
        DDCMPAYO.PAY_PSWD = DDCSMPWD.PSWD_OLD;
        IBS.init(SCCGWA, DDCMPAYN);
        DDCMPAYN.PAY_PSWD = DDCSMPWD.PSWD_NEW;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCMPAYO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCMPAYN;
        BPCPNHIS.INFO.AC = DDCSMPWD.AC;
        BPCPNHIS.INFO.TX_TOOL = DDCSMPWD.CARD_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.CCY = CICACCU.DATA.CCY;
        if (CICACCU.DATA.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        if (DDCSMPWD.FUNC == 'P') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS1;
            BPCPNHIS.INFO.TX_TYP_CD = "P504";
        }
        if (DDCSMPWD.FUNC == 'R') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS2;
            BPCPNHIS.INFO.TX_TYP_CD = "P505";
        }
        if (DDCSMPWD.FUNC == 'U') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS3;
            BPCPNHIS.INFO.TX_TYP_CD = "P506";
        }
        if (DDCSMPWD.FUNC == 'K') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS4;
        }
        if (DDCSMPWD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS5;
        }
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void B100_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOMPWD);
        DDCOMPWD.FUNC = DDCSMPWD.FUNC;
        DDCOMPWD.CARD_NO = DDCSMPWD.CARD_NO;
        DDCOMPWD.AC = DDCSMPWD.AC;
        DDCOMPWD.AC_CNAME = DDCSMPWD.AC_CNAME;
        DDCOMPWD.AC_ENAME = DDCSMPWD.AC_ENAME;
        DDCOMPWD.PSBK_NO = DDCSMPWD.PSBK_NO;
        DDCOMPWD.PAY_MTH = DDCSMPWD.PAY_MTH;
        DDCOMPWD.PSWD_OLD = DDCSMPWD.PSWD_OLD;
        DDCOMPWD.PSWD_NEW = DDCSMPWD.PSWD_NEW;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOMPWD;
        SCCFMT.DATA_LEN = 639;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
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
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMPWD.FUNC);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
