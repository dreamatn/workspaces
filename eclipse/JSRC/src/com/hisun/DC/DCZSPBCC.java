package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPBCC {
    int JIBS_tmp_int;
    String JIBS_NumStr;
    DBParm DCTCDORD_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTBINPM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE";
    String CPN_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_DCZUCHCD = "DC-U-CHANG-CARD";
    double K_MAX_BL = 50000;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "DC808";
    String WS_ERR_MSG = " ";
    String WS_NEW_BV_CD = " ";
    int WS_CNT = 0;
    char WS_ADSC_TYPE = ' ';
    String WS_PROD_CD = " ";
    String WS_HOLDER_CINO = " ";
    char WS_BV_TYP = ' ';
    DCZSPBCC_WS_OUTPUT WS_OUTPUT = new DCZSPBCC_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCSPBCC DCCNPBCC = new DCCSPBCC();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCUCHCD DCCUCHCD = new DCCUCHCD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPROCAC BPROCAC = new BPROCAC();
    CICCHDC CICCHDC = new CICCHDC();
    CICSACR CICSACR = new CICSACR();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DDCOPINQ DDCOPINQ = new DDCOPINQ();
    DCCUCSET DCCUCSET = new DCCUCSET();
    CICSACRL CICSACRL = new CICSACRL();
    CICCKOC CICCKOC = new CICCKOC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    DCCSPBCC DCCSPBCC;
    public void MP(SCCGWA SCCGWA, DCCSPBCC DCCSPBCC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPBCC = DCCSPBCC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPBCC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B011_CHECK_CARD_ARREARAGE();
        if (pgmRtn) return;
        B012_CHECK_NEW_CARD_ARQC();
        if (pgmRtn) return;
        B013_MAX_CARD_NUM_CHECK();
        if (pgmRtn) return;
        B020_ACTIVATE_CARD_PROCESS();
        if (pgmRtn) return;
        B024_UPDATE_DDTCCY();
        if (pgmRtn) return;
        B025_NEW_CARD_RELN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B026_REGIST_AGENT_INF();
            if (pgmRtn) return;
        }
        B030_CHANGE_CARD_PROCESS();
        if (pgmRtn) return;
        B035_UPDATE_CARD_INFO();
        if (pgmRtn) return;
        B040_CARD_VOUCHER();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B050_FEE_PROCESS();
            if (pgmRtn) return;
        }
        B060_CANCEL_PASSBOOK_PROCESS();
        if (pgmRtn) return;
        B100_BP_CANCEL_LIST();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B080_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPBCC.PASSBOOK_NO);
        CEP.TRC(SCCGWA, DCCSPBCC.PABK_PASSWD);
        CEP.TRC(SCCGWA, DCCSPBCC.CI_NO);
        CEP.TRC(SCCGWA, DCCSPBCC.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, DCCSPBCC.HOLDER_IDNO);
        CEP.TRC(SCCGWA, DCCSPBCC.HOLDER_NAME);
        CEP.TRC(SCCGWA, DCCSPBCC.NEW_CARD_NO);
        CEP.TRC(SCCGWA, DCCSPBCC.AGENT_FLG);
        if (DCCSPBCC.PASSBOOK_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_MUST_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCSPBCC.PSBK_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_PSBK_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            B014_CHECK_PASSBOOK_STS();
            if (pgmRtn) return;
        }
        if (DCCSPBCC.DB_FREE_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DB_FREE_FLG_MISSING;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCSPBCC.NEW_CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCCSPBCC.AGENT_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AGENT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCSPBCC.NEW_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
        T000_READ_DCTCDORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDORD.CRT_STS);
        if (DCRCDORD.CRT_STS != '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_PROD_CD = DCRCDORD.CARD_PROD;
        WS_NEW_BV_CD = DCRCDORD.BV_CD_NO;
        CEP.TRC(SCCGWA, WS_NEW_BV_CD);
        if (DCRCDORD.APP_TYP.trim().length() == 0) {
            WS_BV_TYP = '2';
        } else {
            WS_BV_TYP = '1';
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSPBCC.NEW_CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_ACTIVED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.TX_TYPE = 'I';
        DDCIMMST.DATA.KEY.AC_NO = DCCSPBCC.PASSBOOK_NO;
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        if (DDCIMMST.DATA.CARD_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALRE_ONE_PASSBOOK;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
        if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
        if (DDCIMMST.DATA.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIMMST.DATA.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALRE_PASSBOOK_LOST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_HOLDER_CINO = DCCSPBCC.CI_NO;
    }
    public void B011_CHECK_CARD_ARREARAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DCCSPBCC.PASSBOOK_NO;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
        if (BPCPFPDT.OUTPUT.MAIN_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PAY_ARREARAGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B012_CHECK_NEW_CARD_ARQC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'C';
        DCCFCDGG.VAL.CARD_NO = DCCSPBCC.NEW_CARD_NO;
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
    }
    public void B013_MAX_CARD_NUM_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKOC);
        CICCKOC.DATA.CI_NO = DCCSPBCC.CI_NO;
        CICCKOC.DATA.OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICCKOC.DATA.PROD_CD = WS_PROD_CD;
        CICCKOC.DATA.AGENT_FLG = DCCSPBCC.AGENT_FLG;
        if (DCCSPBCC.AGENT_FLG == '1') {
            CICCKOC.DATA.AGENT_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICCKOC.DATA.AGENT_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICCKOC.DATA.AGENT_NAME = CICGAGA_AGENT_AREA.CI_NM;
        }
        CEP.TRC(SCCGWA, CICCKOC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKOC.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICCKOC.DATA.PROD_CD);
        S000_CALL_CIZCKOC();
        if (pgmRtn) return;
    }
    public void B014_CHECK_PASSBOOK_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'Q';
        DDCIPSBK.PSBK_NO = DCCSPBCC.PSBK_NO;
        DDCIPSBK.AC = DCCSPBCC.PASSBOOK_NO;
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCOPINQ.PSBK_STS);
        if (DDCOPINQ.PSBK_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSB_HAS_LOST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_ACTIVATE_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCSET);
        DCCUCSET.CARD_TYPE = '0';
        DCCUCSET.CARD_NO = DCCSPBCC.NEW_CARD_NO;
        DCCUCSET.CARD_PROD_CD = WS_PROD_CD;
        DCCUCSET.AC_TYPE = '1';
        DCCUCSET.CARD_CLS_PROD = DCRCDORD.CARD_CLS_CD;
        DCCUCSET.SF_FLG = DCRCDORD.SF_FLG;
        DCCUCSET.CERT_EXP_DATE = DCRCDORD.CERT_EXP_DATE;
        DCCUCSET.BV_CD_NO = WS_NEW_BV_CD;
        DCCUCSET.CARD_TYP = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        DCCUCSET.HOLDER_CINO = WS_HOLDER_CINO;
        DCCUCSET.OWNER_CINO = WS_HOLDER_CINO;
        DCCUCSET.SNAME_TRAN_FLG = 'Y';
        DCCUCSET.DNAME_TRAN_FLG = 'Y';
        DCCUCSET.OUT_DRAW_FLG = 'Y';
        DCCUCSET.LNK_TYP = '1';
        DCCUCSET.PROD_LMT_FLG = 'Y';
        DCCUCSET.CARD_STS = '2';
        DCCUCSET.HOLD_AC_FLG = 'N';
        DCCUCSET.DB_FREE = DCCSPBCC.DB_FREE_FLG;
        S000_CALL_DCZUCSET();
        if (pgmRtn) return;
        DCRCDDAT.KEY.CARD_NO = DCCSPBCC.NEW_CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        DCRCDDAT.EMBS_DT = DCRCDORD.CRT_DT;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.EXC_CARD_TMS = DCRCDORD.KEY.EXC_CARD_TMS;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B024_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'U';
        DDCIMCYY.DATA.AC_OLD = DCCSPBCC.PASSBOOK_NO;
        DDCIMCYY.DATA.AC_NEW = DCCSPBCC.NEW_CARD_NO;
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
    }
    public void B025_NEW_CARD_RELN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = DCCSPBCC.NEW_CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        CICSACR.DATA.STSW = "11011000";
        CICSACR.DATA.CI_NO = DCCSPBCC.CI_NO;
        CICSACR.DATA.PROD_CD = WS_PROD_CD;
        CICSACR.DATA.FRM_APP = "DC";
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.CNTRCT_TYP = "299";
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B026_REGIST_AGENT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCCSPBCC.CI_NO;
        CICSAGEN.OUT_AC = DCCSPBCC.NEW_CARD_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "01";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B030_CHANGE_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCHDC);
        CICCHDC.FUNC = 'C';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD = DCCSPBCC.PASSBOOK_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD = '1';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW = DCCSPBCC.NEW_CARD_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW = '2';
        S000_CALL_CIZCHDC();
        if (pgmRtn) return;
    }
    public void B035_UPDATE_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCSPBCC.NEW_CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        DCRCDORD.CRT_STS = '3';
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B036_OPEN_IC_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCCDQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = WS_PROD_CD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.IC_APP_FLG);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.IC_PROD_CD);
        if (DCCDQPRD.VAL.DATA.IC_APP_FLG == '1' 
            && DCCDQPRD.VAL.DATA.IC_PROD_CD.trim().length() > 0) {
            CEP.TRC(SCCGWA, "IC APP");
            IBS.init(SCCGWA, DDCUOPAC);
            DDCUOPAC.ACO_AC_TYP = '1';
            DDCUOPAC.CI_NO = DCCSPBCC.CI_NO;
            DDCUOPAC.CARD_TYP = '1';
            DDCUOPAC.PROD_CODE = DCCDQPRD.VAL.DATA.IC_PROD_CD;
            DDCUOPAC.AC_TYP = 'A';
            DDCUOPAC.PSBK_FLG = '2';
            DDCUOPAC.CARD_FLG = '1';
            DDCUOPAC.ACNO_FLG = 'B';
            DDCUOPAC.DRAW_MTH = ' ';
            DDCUOPAC.AC = DCCSPBCC.NEW_CARD_NO;
            DDCUOPAC.CCY = "156";
            DDCUOPAC.CCY_TYPE = '1';
            DDCUOPAC.AC_TYPE = '1';
            S000_CALL_DDZUOPAC();
            if (pgmRtn) return;
        }
    }
    public void B040_CARD_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.VB_FLG = '0';
        BPCUBUSE.COUNT_MTH = '1';
        if (DCCSPBCC.NEW_CARD_NO == null) DCCSPBCC.NEW_CARD_NO = "";
        JIBS_tmp_int = DCCSPBCC.NEW_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSPBCC.NEW_CARD_NO += " ";
        BPCUBUSE.BEG_NO = DCCSPBCC.NEW_CARD_NO.substring(0, 18);
        BPCUBUSE.END_NO = DCCSPBCC.NEW_CARD_NO.substring(0, 18);
        BPCUBUSE.BV_CODE = WS_NEW_BV_CD;
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B050_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = DCCSPBCC.NEW_CARD_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = WS_HOLDER_CINO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCCDQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = WS_PROD_CD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.SVR_RSC_CD);
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = WS_HOLDER_CINO;
        BPCTCALF.INPUT_AREA.PROD_CODE = WS_PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = WS_PROD_CD;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        BPCTCALF.INPUT_AREA.TX_AC = DCCSPBCC.NEW_CARD_NO;
        if (DCCDQPRD.VAL.DATA.SVR_RSC_CD.equalsIgnoreCase("220")) {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "05";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        }
        if (DCCDQPRD.VAL.DATA.SVR_RSC_CD.equalsIgnoreCase("120")) {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "04";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        }
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B060_CANCEL_PASSBOOK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'D';
        DDCIPSBK.PSBK_NO = DCCSPBCC.PSBK_NO;
        DDCIPSBK.AC = DCCSPBCC.PASSBOOK_NO;
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_PASSBOOK_NO = DCCSPBCC.PASSBOOK_NO;
        WS_OUTPUT.WS_CI_NO = DCCSPBCC.CI_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCSPBCC.HOLDER_IDTYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCSPBCC.HOLDER_IDNO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCSPBCC.HOLDER_NAME;
        WS_OUTPUT.WS_NEW_CARD_NO = DCCSPBCC.NEW_CARD_NO;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 408;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSPBCC);
        IBS.init(SCCGWA, DCCNPBCC);
        DCCNPBCC.PASSBOOK_NO = DCCSPBCC.PASSBOOK_NO;
        DCCNPBCC.HOLDER_IDTYP = DCCSPBCC.HOLDER_IDTYP;
        DCCNPBCC.HOLDER_IDNO = DCCSPBCC.HOLDER_IDNO;
        DCCNPBCC.HOLDER_NAME = DCCSPBCC.HOLDER_NAME;
        DCCNPBCC.NEW_CARD_NO = DCCSPBCC.NEW_CARD_NO;
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "PASSBOOK CHANGE INTO CARD";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSPBCC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSPBCC.PASSBOOK_NO;
        BPCPNHIS.INFO.CI_NO = DCCSPBCC.CI_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 446;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNPBCC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B100_BP_CANCEL_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        IBS.init(SCCGWA, BPROCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = DCCSPBCC.NEW_CARD_NO;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "21";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.BV_TYP = WS_BV_TYP;
        BPCSOCAC.BV_NO = DCCSPBCC.NEW_CARD_NO;
        BPCSOCAC.ID_TYP = DCCSPBCC.HOLDER_IDTYP;
        BPCSOCAC.ID_NO = DCCSPBCC.HOLDER_IDNO;
        BPCSOCAC.CI_CNM = DCCSPBCC.HOLDER_NAME;
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.CARD_FLG = '2';
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = WS_PROD_CD;
        BPCSOCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-F-CHK-DIGIT-GEN", DCCFCDGG);
        CEP.TRC(SCCGWA, DCCFCDGG.RC.RC_CODE);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        CEP.TRC(SCCGWA, BPCSOCAC.RC.RC_CODE);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCHCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCHCD, DCCUCHCD);
        CEP.TRC(SCCGWA, DCCUCHCD.RC.RC_CODE);
        if (DCCUCHCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCHCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT);
        CEP.TRC(SCCGWA, BPCPFPDT.RC);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
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
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
        if (DDCIPSBK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIPSBK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCKOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-OPEN-CARD", CICCKOC);
        CEP.TRC(SCCGWA, CICCKOC.RC);
        if (CICCKOC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCKOC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC);
    }
    public void T000_READUPD_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.REWRITE(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHANGE-DC ", CICCHDC);
        CEP.TRC(SCCGWA, CICCHDC.RC);
        if (CICCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR ", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCSET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-SETUP", DCCUCSET);
        if (DCCUCSET.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZPQPRD, BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-QUERY-PROD", DCCDQPRD);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSPBCC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
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
