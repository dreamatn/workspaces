package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSACHK {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    String K_OUTPUT_FMT = "DD575";
    String K_HIS_COPYBOOK_NAME = "DDCSACHK";
    String K_HIS_REMARKS = "AC YEAR CHEAK";
    String WS_ERR_MSG = " ";
    DDZSACHK_WS_OUT_DATA WS_OUT_DATA = new DDZSACHK_WS_OUT_DATA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCSACHK DDCSACHK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSACHK DDCSACHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSACHK = DDCSACHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSACHK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        B020_CHECK_CI_STS_PROC();
        B030_CHECK_AC_STS_PROC();
        B050_UPD_CHK_STS();
        B070_OUTPUT_DATA();
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DDCSACHK.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSACHK.CHK_RLT == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHECK_RLT_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_CI_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSACHK.ACNO;
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.CI_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC;
            S000_ERR_MSG_PROC();
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_IS_CLOSE_STS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_AC_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSACHK.ACNO;
        T000_READ_DDTMST();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BRDP);
        if (BPCPQORG.ATTR == '3') {
            if ((DDRMST.OWNER_BRDP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) 
                && (DDRMST.OWNER_BRDP != BPCPQORG.SUPR_BR)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (DDRMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH);
            }
        }
        CEP.TRC(SCCGWA, DDRMST.OPEN_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_OUT_DATA.WS_OPEN_YEAR = (short) ((DDRMST.OPEN_DATE - DDRMST.OPEN_DATE % 10000) / 10000);
        WS_OUT_DATA.WS_AC_YEAR = (short) ((SCCGWA.COMM_AREA.AC_DATE - SCCGWA.COMM_AREA.AC_DATE % 10000) / 10000);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OPEN_YEAR);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_AC_YEAR);
        if (WS_OUT_DATA.WS_OPEN_YEAR >= WS_OUT_DATA.WS_AC_YEAR) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_AC_NOT_CHK;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'V') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_NOT_CR_AND_NOT_CR;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_ACTV;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("0") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("0") 
            && DDCSACHK.CHK_RLT == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ALREADY_YEAR_CK;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
            && DDCSACHK.CHK_RLT == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_YEAR_CK;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCSACHK.CHK_RLT == 'Y' 
            && DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("0") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("0") 
            || DDCSACHK.CHK_RLT == 'N' 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHECK_RLT_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_UPD_CHK_STS() throws IOException,SQLException,Exception {
        if (DDCSACHK.CHK_RLT == 'Y') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 13 - 1) + "0" + DDRMST.AC_STS_WORD.substring(13 + 1 - 1);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 14 - 1) + "0" + DDRMST.AC_STS_WORD.substring(14 + 1 - 1);
            T000_REWRITE_DDTMST();
            B080_NFIN_TX_HIS_PROC();
        } else {
            if (DDCSACHK.CHK_RLT == 'N') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 13 - 1) + "0" + DDRMST.AC_STS_WORD.substring(13 + 1 - 1);
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 14 - 1) + "1" + DDRMST.AC_STS_WORD.substring(14 + 1 - 1);
                T000_REWRITE_DDTMST();
                B080_NFIN_TX_HIS_PROC();
            }
        }
    }
    public void B070_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_ACNO = DDCSACHK.ACNO;
        WS_OUT_DATA.WS_CHK_RLT = DDCSACHK.CHK_RLT;
        WS_OUT_DATA.WS_RMK = DDCSACHK.RMK;
        WS_OUT_DATA.WS_CN_NAME = DDCSACHK.CN_NAME;
        WS_OUT_DATA.WS_EN_NAME = DDCSACHK.EN_NAME;
        WS_OUT_DATA.WS_CCY = DDCSACHK.CCY;
        WS_OUT_DATA.WS_CCY_TYPE = DDCSACHK.CCY_TYPE;
        WS_OUT_DATA.WS_CHK_YEAR = DDCSACHK.CHK_YEAR;
        WS_OUT_DATA.WS_OLD_STS = DDCSACHK.OLD_STS;
        CEP.TRC(SCCGWA, WS_OUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 677;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHK_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSACHK.ACNO;
        DCCPACTY.INPUT.FUNC = '3';
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
        DDCSACHK.ACNO = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
    }
    public void B080_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSACHK.ACNO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
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
