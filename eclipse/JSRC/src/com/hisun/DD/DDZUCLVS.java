package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUCLVS {
    int JIBS_tmp_int;
    DBParm DDTVSABI_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CDC_CAN_SPAC = "DC-I-CAN-SPAC";
    String CDD_U_BPZPNHIS = "BP-REC-NHIS";
    String K_CCY_CNY = "156";
    String K_OUTPUT_FMT_D = "DD850";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    String WS_VS_AC = " ";
    String WS_CI_NO = " ";
    String WS_ACO_AC = " ";
    char WS_CCY_STS = ' ';
    char WS_TBL_FLAG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCIPACI DCCIPACI = new DCCIPACI();
    CICACCU CICACCU = new CICACCU();
    CICSACRL CICSACRL = new CICSACRL();
    CICSACR CICSACR = new CICSACR();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCOCLVS DDCOCLVS = new DDCOCLVS();
    CICSACAC CICSACAC = new CICSACAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRVSABI DDRVSABI = new DDRVSABI();
    DDRMST DDRMST = new DDRMST();
    DCRSPAC DCRSPAC = new DCRSPAC();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DDCUCLVS DDCUCLVS;
    public void MP(SCCGWA SCCGWA, DDCUCLVS DDCUCLVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCLVS = DDCUCLVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUCLVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INFO();
        B020_UPD_REF();
        B030_UPD_VAC_T();
        B035_UPD_BP_PROC();
        B040_BP_NFHIS();
        B050_OUTPUT_PROC();
    }
    public void B010_CHECK_INFO() throws IOException,SQLException,Exception {
        if (DDCUCLVS.CCY.equalsIgnoreCase(K_CCY_CNY)) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_CCY_NOT_CNY;
            S000_ERR_MSG_PROC();
        }
        if (DDCUCLVS.CCY_TYP == '1') {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRVSABI);
        DDRVSABI.KEY.VS_AC = DDCUCLVS.VS_AC;
        DDRVSABI.KEY.CCY = DDCUCLVS.CCY;
        DDRVSABI.KEY.CCY_TYP = DDCUCLVS.CCY_TYP;
        T000_READ_DDTVSABI();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRVSABI.AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DDCUCLVS.VS_AC;
        CICQACRL.DATA.AC_REL = "01";
        S000_CALL_CIZQACRL();
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        T000_READ_DDTMST();
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_FREEZE;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUCLVS.VS_AC;
        DDRCCY.CCY = DDCUCLVS.CCY;
        DDRCCY.CCY_TYPE = DDCUCLVS.CCY_TYP;
        T000_READ_DDTCCY_AC();
        WS_CCY_STS = DDRCCY.STS;
        CEP.TRC(SCCGWA, WS_CCY_STS);
        WS_ACO_AC = DDRCCY.KEY.AC;
        DDRCCY.STS = 'C';
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "1" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
        T000_REWRITE_DDTCCY();
        if (DDRCCY.CURR_BAL != 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_BAL_GR_ZERO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDCUCLVS.OP_BR);
        CEP.TRC(SCCGWA, DDRCCY.OWNER_BR);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZACCU();
        WS_CI_NO = CICACCU.DATA.CI_NO;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCZM_CI_FBID;
            S000_ERR_MSG_PROC();
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = CICMSG_ERROR_MSG.CI_IS_CLOSE_STS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_UPD_REF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'D';
        CICSACRL.DATA.AC_NO = DDCUCLVS.VS_AC;
        CICSACRL.DATA.AC_REL = "01";
        CICSACRL.DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZSACRL();
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CICSACAC.DATA.ACAC_NO = WS_ACO_AC;
        S000_CALL_CIZSACAC();
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = DDCUCLVS.VS_AC;
        CICSACR.DATA.ENTY_TYP = '4';
        CICSACR.DATA.CI_NO = WS_CI_NO;
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.CCY = DDCUCLVS.CCY;
        CICSACR.DATA.OPN_BR = DDCUCLVS.OP_BR;
        S000_CALL_CIZSACR();
    }
    public void B030_UPD_VAC_T() throws IOException,SQLException,Exception {
        DDRVSABI.AC_STS = 'C';
        DDRVSABI.REMARK = DDCUCLVS.REMARK;
        DDRVSABI.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRVSABI.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRVSABI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTVSABI();
    }
    public void B035_UPD_BP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = DDCUCLVS.VS_AC;
        BPCSOCAC.ACO_AC = DDRCCY.KEY.AC;
        BPCSOCAC.CLOSE_AC_STS = WS_CCY_STS;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZSOCAC();
    }
    public void B040_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = DDCUCLVS.VS_AC;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = DDCUCLVS.REMARK;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCUCLVS;
        BPCPNHIS.INFO.FMT_ID = "DDZUCLVS";
        BPCPNHIS.INFO.FMT_ID_LEN = 168;
        S000_CALL_BPZPNHIS();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCLVS);
        DDCOCLVS.VS_AC = DDCUCLVS.VS_AC;
        DDCOCLVS.CCY = DDCUCLVS.CCY;
        DDCOCLVS.CCY_TYP = DDCUCLVS.CCY_TYP;
        DDCOCLVS.REMARK = DDCUCLVS.REMARK;
        DDCOCLVS.DELDT = SCCGWA.COMM_AREA.AC_DATE;
        DDCOCLVS.OPTLR = SCCGWA.COMM_AREA.TL_ID;
        DDCOCLVS.PRTLR = SCCGWA.COMM_AREA.SUP1_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_D;
        SCCFMT.DATA_PTR = DDCOCLVS;
        SCCFMT.DATA_LEN = 1191;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        DDTVSABI_RD.upd = true;
        IBS.READ(SCCGWA, DDRVSABI, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        IBS.REWRITE(SCCGWA, DDRVSABI, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "REWRITE TABLE DDTVSABI ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY_AC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
