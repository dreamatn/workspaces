package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUOPVS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTVSABI_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String WS_VS_AC = " ";
    String WS_VS_AC_1 = " ";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CBP_GEN_ACNO = "BP-GENERTE-ACNO";
    String CDC_INT_SPAC = "DC-I-INT-SPAC";
    String CDD_U_BPZPNHIS = "BP-REC-NHIS";
    String K_CCY_CNY = "156";
    String K_REB_SEQ = "DDVS";
    String K_SEQ_TYPE = "SEQ";
    String WS_STD_PROD = "9530000016";
    String K_OUTPUT_FMT = "DD860";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    int WS_VS_SEQ = 0;
    String WS_CI_NO = " ";
    String WS_PROD_CD = " ";
    char WS_TBL_FLAG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    BPCCGAC BPCCGAC = new BPCCGAC();
    DCCIPACI DCCIPACI = new DCCIPACI();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACRL CICSACRL = new CICSACRL();
    CICSACR CICSACR = new CICSACR();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    DDCOOPVC DDCOOPVC = new DDCOOPVC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRVSABI DDRVSABI = new DDRVSABI();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DCRHLD DCRHLD = new DCRHLD();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DDCUOPVS DDCUOPVS;
    public void MP(SCCGWA SCCGWA, DDCUOPVS DDCUOPVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUOPVS = DDCUOPVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUOPVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INFO();
        B020_10_GET_VAC_NO();
        B030_UPD_REF();
        B025_CREATE_ACO_NO();
        B040_ADD_VAC_T();
        B050_BP_NFHIS();
        B060_SET_RES();
    }
    public void B010_CHECK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCUOPVS.PARENT_AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUOPVS.PARENT_AC;
        T000_READ_DDTMST();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.OWNER_BR);
        if (DDRCCY.OWNER_BR != DDCUOPVS.OP_BR) {
            DDCUOPVS.OP_BR = DDRCCY.OWNER_BR;
        }
        CEP.TRC(SCCGWA, DDCUOPVS.OP_BR);
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_AC_STS = DDRMST.AC_STS;
        CEP.TRC(SCCGWA, WS_AC_STS);
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
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_FR_NOT_ALL;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.HOLD_BAL > 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MO_FR_NOT_ALL;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'D') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'M') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_HAND;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'V') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'O') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, DDCUOPVS.OP_BR);
        if (DDCUOPVS.CCY.equalsIgnoreCase(K_CCY_CNY)) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INPUT_CCY_NOT_CNY;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC.equalsIgnoreCase(K_CCY_CNY)) {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_MUST_RMB;
            S000_ERR_MSG_PROC();
        }
        if (DDCUOPVS.CCY_TYP == '1') {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUOPVS.PARENT_AC;
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
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        if (CICACCU.DATA.CI_TYP == '2') {
        } else {
        }
    }
    public void B020_10_GET_VAC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVSABI);
        if (DDCUOPVS.PARENT_AC == null) DDCUOPVS.PARENT_AC = "";
        JIBS_tmp_int = DDCUOPVS.PARENT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPVS.PARENT_AC += " ";
        if (DDCUOPVS.PARENT_AC.substring(15 - 1, 15 + 1 - 1).trim().length() == 0) {
            if (DDCUOPVS.PARENT_AC == null) DDCUOPVS.PARENT_AC = "";
            JIBS_tmp_int = DDCUOPVS.PARENT_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPVS.PARENT_AC += " ";
            WS_VS_AC_1 = DDCUOPVS.PARENT_AC.substring(0, 14);
        } else {
            if (WS_VS_AC == null) WS_VS_AC = "";
            JIBS_tmp_int = WS_VS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
            WS_VS_AC = "53" + WS_VS_AC.substring(2);
            if (DDCUOPVS.PARENT_AC == null) DDCUOPVS.PARENT_AC = "";
            JIBS_tmp_int = DDCUOPVS.PARENT_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPVS.PARENT_AC += " ";
            if (WS_VS_AC == null) WS_VS_AC = "";
            JIBS_tmp_int = WS_VS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
            WS_VS_AC = WS_VS_AC.substring(0, 3 - 1) + DDCUOPVS.PARENT_AC.substring(6 - 1, 6 + 4 - 1) + WS_VS_AC.substring(3 + 4 - 1);
            if (DDCUOPVS.PARENT_AC == null) DDCUOPVS.PARENT_AC = "";
            JIBS_tmp_int = DDCUOPVS.PARENT_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPVS.PARENT_AC += " ";
            if (WS_VS_AC == null) WS_VS_AC = "";
            JIBS_tmp_int = WS_VS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
            WS_VS_AC = WS_VS_AC.substring(0, 7 - 1) + DDCUOPVS.PARENT_AC.substring(15 - 1, 15 + 8 - 1) + WS_VS_AC.substring(7 + 8 - 1);
            if (WS_VS_AC == null) WS_VS_AC = "";
            JIBS_tmp_int = WS_VS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
            WS_VS_AC_1 = WS_VS_AC.substring(0, 14);
        }
        CEP.TRC(SCCGWA, WS_VS_AC_1);
        T000_READ_SEQ_DDTVSABI();
        if (WS_VS_AC == null) WS_VS_AC = "";
        JIBS_tmp_int = WS_VS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
        if (WS_VS_AC_1 == null) WS_VS_AC_1 = "";
        JIBS_tmp_int = WS_VS_AC_1.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) WS_VS_AC_1 += " ";
        WS_VS_AC = WS_VS_AC_1 + WS_VS_AC.substring(14);
        if (DDRVSABI.KEY.VS_AC == null) DDRVSABI.KEY.VS_AC = "";
        JIBS_tmp_int = DDRVSABI.KEY.VS_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDRVSABI.KEY.VS_AC += " ";
        if (DDRVSABI.KEY.VS_AC.substring(15 - 1, 15 + 6 - 1).trim().length() == 0) WS_VS_SEQ = 0;
        else WS_VS_SEQ = Integer.parseInt(DDRVSABI.KEY.VS_AC.substring(15 - 1, 15 + 6 - 1));
        CEP.TRC(SCCGWA, WS_VS_SEQ);
        if (WS_VS_SEQ != 0) {
            WS_VS_SEQ += 1;
            if (WS_VS_AC == null) WS_VS_AC = "";
            JIBS_tmp_int = WS_VS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
            JIBS_tmp_str[0] = "" + WS_VS_SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_VS_AC = WS_VS_AC.substring(0, 15 - 1) + JIBS_tmp_str[0] + WS_VS_AC.substring(15 + 6 - 1);
        } else {
            if (WS_VS_AC == null) WS_VS_AC = "";
            JIBS_tmp_int = WS_VS_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_VS_AC += " ";
            WS_VS_AC = WS_VS_AC.substring(0, 15 - 1) + "000001" + WS_VS_AC.substring(15 + 6 - 1);
        }
        CEP.TRC(SCCGWA, WS_VS_AC);
    }
    public void B025_CREATE_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VS_AC);
        IBS.init(SCCGWA, DDCUOPAC);
        DDCUOPAC.CI_NO = CICACCU.DATA.CI_NO;
        if (DDCUOPVS.PROD_CD.trim().length() > 0) {
            DDCUOPAC.PROD_CODE = DDCUOPVS.PROD_CD;
        } else {
            DDCUOPAC.PROD_CODE = WS_STD_PROD;
        }
        DDCUOPAC.CCY = "156";
        DDCUOPAC.CCY_TYPE = '1';
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '2';
        DDCUOPAC.DRAW_MTH = '5';
        DDCUOPAC.AC = WS_VS_AC;
        DDCUOPAC.ACNO_FLG = 'B';
        DDCUOPAC.ACO_AC_TYP = '5';
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUOPVS.PARENT_AC;
        T000_READ_DDTMST();
        DDCUOPAC.CUS_MGR = " ";
        DDCUOPAC.REG_CENT = DDRMST.AC_OIC_CODE;
        DDCUOPAC.SUB_BIZ = DDRMST.SUB_DP;
        S000_CALL_DDZUOPAC();
    }
    public void B030_UPD_REF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = WS_VS_AC;
        CICSACR.DATA.ENTY_TYP = '4';
        CICSACR.DATA.CI_NO = WS_CI_NO;
        CICSACR.DATA.STSW = "00000000";
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 4 - 1) + "1" + CICSACR.DATA.STSW.substring(4 + 1 - 1);
        if (DDCUOPVS.PROD_CD.trim().length() > 0) {
            CICSACR.DATA.PROD_CD = DDCUOPVS.PROD_CD;
        } else {
            CICSACR.DATA.PROD_CD = WS_STD_PROD;
        }
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.CCY = DDCUOPVS.CCY;
        CICSACR.DATA.SHOW_FLG = 'N';
        CICSACR.DATA.AC_CNM = DDCUOPVS.VS_AC_NAME;
        CICSACR.DATA.OPN_BR = DDCUOPVS.OP_BR;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'A';
        CICSACRL.DATA.AC_NO = WS_VS_AC;
        CICSACRL.DATA.AC_REL = "01";
        CICSACRL.DATA.REL_AC_NO = DDCUOPVS.PARENT_AC;
        CICSACRL.DATA.DEFAULT = '1';
        S000_CALL_CIZSACRL();
    }
    public void B040_ADD_VAC_T() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVSABI);
        DDRVSABI.KEY.VS_AC = WS_VS_AC;
        DDRVSABI.KEY.CCY = DDCUOPVS.CCY;
        DDRVSABI.KEY.CCY_TYP = DDCUOPVS.CCY_TYP;
        DDRVSABI.AC_STS = 'N';
        DDRVSABI.VS_AC_NAME = DDCUOPVS.VS_AC_NAME;
        DDRVSABI.VS_CON_NAME = DDCUOPVS.VS_CON_NAME;
        DDRVSABI.VS_CON_TEL = DDCUOPVS.VS_CON_TEL;
        DDRVSABI.VS_CON_ADDR = DDCUOPVS.VS_CON_ADDR;
        DDRVSABI.VS_IDTYP = DDCUOPVS.VS_IDTYP;
        DDRVSABI.VS_IDNO = DDCUOPVS.VS_IDNO;
        DDRVSABI.REMARK = DDCUOPVS.REMARK;
        DDRVSABI.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRVSABI.CLOSE_DT = 0;
        DDRVSABI.VS_INTAC = DDCUOPVS.VS_INTAC;
        DDRVSABI.VS_MMO = DDCUOPVS.VS_MMO;
        DDRVSABI.VS_FLG = DDCUOPVS.VS_DRFLG;
        DDRVSABI.VS_SYSNO = DDCUOPVS.VS_SYSNO;
        DDRVSABI.VS_CHNLNO = DDCUOPVS.CHNLNO;
        DDRVSABI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVSABI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVSABI.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRVSABI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTVSABI();
    }
    public void B050_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = CICQACRI.O_DATA.O_AGR_NO;
        BPCPNHIS.INFO.TX_TOOL = CICQACRI.O_DATA.O_AGR_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = DDCUOPVS.REMARK;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCUOPVS;
        BPCPNHIS.INFO.FMT_ID = "DDZUOPVS";
        BPCPNHIS.INFO.FMT_ID_LEN = 1102;
        S000_CALL_BPZPNHIS();
    }
    public void B060_SET_RES() throws IOException,SQLException,Exception {
        DDCUOPVS.VS_AC = DDRVSABI.KEY.VS_AC;
        IBS.init(SCCGWA, DDCOOPVC);
        DDCOOPVC.PARENT_AC = DDCUOPVS.PARENT_AC;
        DDCOOPVC.CCY = DDRVSABI.KEY.CCY;
        DDCOOPVC.CCY_TYP = DDRVSABI.KEY.CCY_TYP;
        DDCOOPVC.VS_AC_NAME = DDRVSABI.VS_AC_NAME;
        DDCOOPVC.VS_CON_NAME = DDRVSABI.VS_CON_NAME;
        DDCOOPVC.VS_CON_TEL = DDRVSABI.VS_CON_TEL;
        DDCOOPVC.VS_CON_ADDR = DDRVSABI.VS_CON_ADDR;
        DDCOOPVC.IDTYP = DDRVSABI.VS_IDTYP;
        DDCOOPVC.IDNO = DDRVSABI.VS_IDNO;
        DDCOOPVC.MMO = DDRVSABI.VS_MMO;
        DDCOOPVC.VS_DRFLG = DDRVSABI.VS_FLG;
        DDCOOPVC.SYSNO = DDRVSABI.VS_SYSNO;
        DDCOOPVC.REMARK = DDRVSABI.REMARK;
        DDCOOPVC.CHNLNO = DDCUOPVS.CHNLNO;
        DDCOOPVC.VS_INTAC = DDCUOPVS.VS_INTAC;
        DDCOOPVC.VS_AC = DDRVSABI.KEY.VS_AC;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOOPVC;
        SCCFMT.DATA_LEN = 1345;
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
    public void T000_READ_SEQ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        DDTVSABI_RD.where = "SUBSTR ( VS_AC , 1 , 14 ) = :WS_VS_AC_1";
        DDTVSABI_RD.fst = true;
        DDTVSABI_RD.order = "VS_AC DESC";
        IBS.READ(SCCGWA, DDRVSABI, this, DDTVSABI_RD);
        CEP.TRC(SCCGWA, DDRVSABI.KEY.VS_AC);
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
    public void T000_WRITE_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        IBS.WRITE(SCCGWA, DDRVSABI, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE DDTVSABI ERROR!";
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
    public void T000_READ_DDTCCY_01() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
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
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICACCU.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICQACRI.RC.RC_MMO;
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
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICSACRL.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICSACR.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CBP_GEN_ACNO, BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUOPVS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCUOPVS=");
            CEP.TRC(SCCGWA, DDCUOPVS);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
