package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCACJ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_DCZUCACJ = "DC-U-CARD-AC-JOIN";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_RMK = "CARD AC JOIN/DISJOIN";
    String K_OUTPUT_FMT = "DC102";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSCACJ_WS_OUTPUT WS_OUTPUT = new DCZSCACJ_WS_OUTPUT();
    int WS_OPEN_CARD_BR = 0;
    String WS_CARD_CI_NO = " ";
    int WS_CARD_BRANCH_BR = 0;
    int WS_AC_BR = 0;
    String WS_PRIM_CARD_NO = " ";
    char WS_RECORD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCUCACJ DCCUCACJ = new DCCUCACJ();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAACR DCRIAACR = new DCRIAACR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRL CICQACRL = new CICQACRL();
    CICSACRL CICSACRL = new CICSACRL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCACJ DCCSCACJ;
    public void MP(SCCGWA SCCGWA, DCCSCACJ DCCSCACJ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCACJ = DCCSCACJ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCACJ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B011_PERSONAL_LIMIT_CHECK();
        if (pgmRtn) return;
        if (DCCSCACJ.FUNC_CODE == 'J') {
            B013_CI_CHECK();
            if (pgmRtn) return;
            B012_AC_CHECK();
            if (pgmRtn) return;
            B014_SUP_CARD_CHECK();
            if (pgmRtn) return;
            B020_CARD_AC_JOIN();
            if (pgmRtn) return;
        } else if (DCCSCACJ.FUNC_CODE == 'D') {
            B030_CARD_AC_CANCEL();
            if (pgmRtn) return;
        } else if (DCCSCACJ.FUNC_CODE == 'U') {
            B040_CARD_AC_UPDATE();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B050_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCACJ.FUNC_CODE);
        if (DCCSCACJ.FUNC_CODE != 'J' 
            && DCCSCACJ.FUNC_CODE != 'D' 
            && DCCSCACJ.FUNC_CODE != 'U') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCACJ.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        WS_CARD_CI_NO = DCRCDDAT.CARD_OWN_CINO;
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, DCRCDDAT.ISSU_BR);
        BPCPQORG.BR = DCRCDDAT.ISSU_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_CARD_BRANCH_BR = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, WS_CARD_BRANCH_BR);
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSCACJ.AC_TYPE != '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_NOT_DD);
        }
    }
    public void B011_PERSONAL_LIMIT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        if (DCRPRDPR.DATA_TXT.JOIN_CUS == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_UNION_CARD_NOT_LINK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_P_CARD_NOT_LINK_MANG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRPRDPR.DATA_TXT.AC_HANG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_HANG_AC);
        }
    }
    public void B012_AC_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = DCCSCACJ.AC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        if (DDCIMMST.DATA.AC_STS == 'V') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED);
        }
        if (DDCIMMST.DATA.AC_STS == 'C') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DD_AC_MST_ALR_CLS);
        }
        if (DDCIMMST.DATA.AC_STS != 'N' 
            && DDCIMMST.DATA.AC_STS != 'O') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_STS_ABNORMAL);
        }
        if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
        if (DDCIMMST.DATA.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER);
        }
        if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
        if (DDCIMMST.DATA.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSE_AND_REDEMP);
        }
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = DCCSCACJ.AC_NO;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_STS_ABNORMAL);
        }
    }
    public void B013_CI_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DCCSCACJ.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CARD_CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (!WS_CARD_CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CA_AC_NOT_SAME_PER);
        }
    }
    public void B014_SUP_CARD_CHECK() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_LNK_TYP == '2') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
            CICQACRL.DATA.AC_REL = "03";
            CICQACRL.FUNC = 'I';
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            WS_PRIM_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.AC_NO = WS_PRIM_CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            CICQACRL.DATA.REL_AC_NO = DCCSCACJ.AC_NO;
            CICQACRL.FUNC = 'I';
            S000_CALL_CIZQACRL_F();
            if (pgmRtn) return;
            if (WS_RECORD_FLG == 'N') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_NOT_ALLOWED);
            }
        }
    }
    public void B015_CHECK_PSW() throws IOException,SQLException,Exception {
    }
    public void B020_CARD_AC_JOIN() throws IOException,SQLException,Exception {
        if (DCCSCACJ.CLR_FLG == 'Y') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            CICQACRL.FUNC = 'I';
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
            CICSACRL.DATA.AC_REL = "04";
            CICSACRL.DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
            CICSACRL.DATA.DEFAULT = '0';
            CICSACRL.FUNC = 'M';
            S000_CALL_CIZSACRL();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
        CICSACRL.DATA.AC_REL = "04";
        CICSACRL.DATA.REL_AC_NO = DCCSCACJ.AC_NO;
        if (DCCSCACJ.CLR_FLG == 'Y') {
            CICSACRL.DATA.DEFAULT = '1';
        } else {
            CICSACRL.DATA.DEFAULT = '0';
        }
        CICSACRL.FUNC = 'A';
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
    }
    public void B030_CARD_AC_CANCEL() throws IOException,SQLException,Exception {
        B031_CHECK_ACCOUNT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_LNK_TYP == '1') {
            B032_CHECK_SUP_CARD_LNK_AC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
        CICSACRL.DATA.AC_REL = "04";
        CICSACRL.DATA.REL_AC_NO = DCCSCACJ.AC_NO;
        CICSACRL.FUNC = 'D';
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
    }
    public void B031_CHECK_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
        CICQACRL.DATA.AC_REL = "04";
        CICQACRL.DATA.REL_AC_NO = DCCSCACJ.AC_NO;
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_CTL == null) CICQACRL.O_DATA.O_REL_CTL = "";
        JIBS_tmp_int = CICQACRL.O_DATA.O_REL_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICQACRL.O_DATA.O_REL_CTL += " ";
        if (CICQACRL.O_DATA.O_REL_CTL.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_NO_RLS_DFT_AC);
        }
    }
    public void B032_CHECK_SUP_CARD_LNK_AC() throws IOException,SQLException,Exception {
    }
    public void B040_CARD_AC_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
        CICQACRL.DATA.AC_REL = "04";
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
        CICSACRL.DATA.AC_REL = "04";
        CICSACRL.DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        CICSACRL.DATA.DEFAULT = '0';
        CICSACRL.FUNC = 'M';
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.DATA.AC_NO = DCCSCACJ.CARD_NO;
        CICSACRL.DATA.AC_REL = "04";
        CICSACRL.DATA.REL_AC_NO = DCCSCACJ.AC_NO;
        CICSACRL.DATA.DEFAULT = '1';
        CICSACRL.FUNC = 'M';
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
    }
    public void B050_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_FUNC_CODE = DCCSCACJ.FUNC_CODE;
        WS_OUTPUT.WS_CARD_NO = DCCSCACJ.CARD_NO;
        WS_OUTPUT.WS_AC_TYP = DCCSCACJ.AC_TYPE;
        WS_OUTPUT.WS_AC_NO = DCCSCACJ.AC_NO;
        WS_OUTPUT.WS_AC_CCY = DCCSCACJ.AC_CCY;
        WS_OUTPUT.WS_CLR_FLG = DCCSCACJ.CLR_FLG;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 73;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSCACJ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSCACJ.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSCACJ.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 95;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCSCACJ;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCACJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCACJ, DCCUCACJ);
        if (DCCUCACJ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCACJ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR, DCCUCACJ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL_F() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            if (CICQACRL.RC.RC_CODE == CI8054) {
                WS_RECORD_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOACCU", CICACCU);
    }
    if (CICACCU.RC.RC_CODE != 0) {
        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_STS,PROD_CD,CARD_LNK_TYP,CARD_OWN_CINO, CARD_STSW,ISSU_BR";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
