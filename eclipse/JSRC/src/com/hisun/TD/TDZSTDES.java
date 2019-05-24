package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSTDES {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTOTHE_RD;
    boolean pgmRtn = false;
    String K_PRDP_TYP = "PRDPR";
    String WS_ERR_MSG = " ";
    String WS_REL_AC = " ";
    String WS_ERR_INFO = " ";
    TDZSTDES_CP_PROD_CD CP_PROD_CD = new TDZSTDES_CP_PROD_CD();
    short WS_COUNT1 = 0;
    TDZSTDES_WS_TDRFHIS WS_TDRFHIS = new TDZSTDES_WS_TDRFHIS();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    TDRCALL TDRCALL = new TDRCALL();
    TDRSMST TDRSMST = new TDRSMST();
    TDROTHE TDROTHE = new TDROTHE();
    TDCHTDES TDCNTZMS = new TDCHTDES();
    TDCHTDES TDCOTZMS = new TDCHTDES();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    TDRCMST TDRCMST = new TDRCMST();
    TDRBVT TDRBVT = new TDRBVT();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    TDRFHIS TDRFHIS = new TDRFHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    TDB1370_AWA_1370 TDB1370_AWA_1370;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSTDES return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1370_AWA_1370>");
        TDB1370_AWA_1370 = (TDB1370_AWA_1370) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1370_AWA_1370.STDES_AC);
        B010_UPD_CALL_STS();
        if (pgmRtn) return;
    }
    public void B010_UPD_CALL_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDB1370_AWA_1370.STDES_AC;
        T000_READUP_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDRSMST.AC_NO;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP == ' ') {
            TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
        } else {
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
        }
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        T000_READ_TDTBVT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.ACTI_NO);
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
        T000_READ_TDTOTHE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.BUSI_NO);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = TDRSMST.BUSI_NO;
        DCCURHLD.DATA.RHLD_TYP = '2';
        DCCURHLD.DATA.RAMT = TDRSMST.BAL;
        DCCURHLD.DATA.HLD_TYP = '2';
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
        if (TDRCMST.CI_TYP == '1') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = TDRCMST.KEY.AC_NO;
            CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
            CICQACRL.DATA.AC_REL = "13";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            CEP.TRC(SCCGWA, WS_REL_AC);
        }
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = TDRSMST.OPEN_DR_AC;
        if (TDRCMST.CI_TYP == '1') {
            DDCSCINM.INPUT_DATA.AC_NO = WS_REL_AC;
        }
        DDCSCINM.INPUT_DATA.FUNC = '2';
        DDCSCINM.INPUT_DATA.CCY = TDRSMST.CCY;
        DDCSCINM.INPUT_DATA.CCY_TYP = TDRSMST.CCY_TYP;
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDROTHE.SET_FLG);
        if (TDROTHE.SET_FLG == '0') {
            B230_CALL_DD_DR_UNT();
            if (pgmRtn) return;
            TDRSMST.ACO_STS = '0';
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
            if (TDRCMST.CI_TYP == '1') {
                TDRCMST.STS = '0';
                TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_TDTCMST();
                if (pgmRtn) return;
            }
            B260_WRT_VCH_EVENT();
            if (pgmRtn) return;
            B270_WRT_FHIS();
            if (pgmRtn) return;
        } else {
            TDRSMST.ACO_STS = '1';
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
            if (TDRCMST.CI_TYP == '1') {
                TDRCMST.STS = '1';
                TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_TDTCMST();
                if (pgmRtn) return;
            }
        }
        R000_TRANS_OTZMS_DATA();
        if (pgmRtn) return;
        R000_TRANS_NTZMS_DATA();
        if (pgmRtn) return;
        B030_WRITE_NHIS();
        if (pgmRtn) return;
    }
    public void B030_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "TDBSP09";
        BPCPNHIS.INFO.FMT_ID_LEN = 48;
        BPCPNHIS.INFO.TX_RMK = "AUTO MOD CALL STS";
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = TDCOTZMS;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCNTZMS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B260_WRT_VCH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_PRDP_TYP;
        CP_PROD_CD.PROD_ACC_CENT = 999999999;
        CP_PROD_CD.PROD_PRDT_CODE = BPCPQPRD.PARM_CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, CP_PROD_CD);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP);
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.DESC = "D09";
        BPCPOEWA.DATA.BR_OLD = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.BR_NEW = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDRSMST.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = TDRSMST.BAL;
        BPCPOEWA.DATA.VALUE_DATE = TDRSMST.VAL_DATE;
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO_REL = TDRSMST.OPEN_DR_AC;
        BPCPOEWA.DATA.PORT = TDRBVT.BV_CD;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B230_CALL_DD_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = TDRSMST.OPEN_DR_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDRSMST.OPEN_DR_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = TDRSMST.OPEN_DR_AC;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        DDCUDRAC.ID_TYPE = CICACCU.DATA.ID_TYPE;
        DDCUDRAC.ID_NO = CICACCU.DATA.ID_NO;
        DDCUDRAC.PSBK_NO = DDCSCINM.OUTPUT_DATA.PSBK_NO;
        DDCUDRAC.CCY = TDRSMST.CCY;
        DDCUDRAC.CCY_TYPE = TDRSMST.CCY_TYP;
        DDCUDRAC.TX_AMT = TDRSMST.BAL;
        DDCUDRAC.OTHER_AC = TDRSMST.AC_NO;
        DDCUDRAC.OTH_TX_TOOL = TDRSMST.AC_NO;
        DDCUDRAC.OTHER_CCY = TDRSMST.CCY;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW = 'N';
        DDCUDRAC.TX_MMO = "A005";
        DDCUDRAC.CHK_PSW_FLG = 'N';
        CEP.TRC(SCCGWA, DDCUDRAC.PCHQ_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.CHK_PSW);
        DDCUDRAC.BANK_DR_FLG = 'N';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "YSY111");
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
    }
    public void B270_WRT_FHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        BPCPFHIS.DATA.AC = TDRSMST.AC_NO;
        BPCPFHIS.DATA.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCPFHIS.DATA.OTH_AC = TDRSMST.OPEN_DR_AC;
        BPCPFHIS.DATA.OTH_TX_TOOL = TDRSMST.OPEN_DR_AC;
        BPCPFHIS.DATA.BV_NO = TDRBVT.BV_NO;
        BPCPFHIS.DATA.BV_CODE = TDRBVT.BV_CD;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
        BPCPFHIS.DATA.TX_VAL_DT = TDRSMST.VAL_DATE;
        BPCPFHIS.DATA.TX_CCY = TDRSMST.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = TDRSMST.CCY_TYP;
        CEP.TRC(SCCGWA, "PHIS");
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCPFHIS.DATA.TX_AMT = TDRSMST.BAL;
        BPCPFHIS.DATA.TX_MMO = "A005";
        BPCPFHIS.DATA.PROD_CD = TDRSMST.PROD_CD;
        WS_TDRFHIS.WS_FHIS_POST_CD = TDRBVT.BV_CD;
        WS_TDRFHIS.WS_FHIS_SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        WS_TDRFHIS.WS_FHIS_TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_TDRFHIS.WS_FHIS_DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        WS_TDRFHIS.WS_FHIS_AC_NAME = CICACCU.DATA.AC_CNM;
        WS_TDRFHIS.WS_FHIS_INSTR_MTH = TDRSMST.INSTR_MTH;
        WS_COUNT1 = 0;
        WS_COUNT1 = 276;
        CEP.TRC(SCCGWA, WS_COUNT1);
        IBS.init(SCCGWA, TDRFHIS);
        TDRFHIS.POST_CD = TDRBVT.BV_CD;
        TDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        TDRFHIS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRFHIS.DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        TDRFHIS.AC_NAME = CICACCU.DATA.AC_CNM;
        TDRFHIS.INSTR_MTH = TDRSMST.INSTR_MTH;
        CEP.TRC(SCCGWA, TDRFHIS.DATA_FIELD_TEXT);
        if (TDRFHIS.DATA_FIELD_TEXT.trim().length() == 0) {
            BPCPFHIS.DATA.FMT_LEN = WS_COUNT1;
        } else {
            BPCPFHIS.DATA.FMT_LEN = 1276;
        }
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, TDRFHIS);
        if (TDRSMST.PROD_CD.equalsIgnoreCase("CDP00580")) {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        CEP.TRC(SCCGWA, "WWWWW");
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("0")) {
            BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        } else {
            BPCPFHIS.DATA.DISPLAY_IND = 'N';
        }
        BPCPFHIS.DATA.RLT_AC = TDRSMST.OPEN_DR_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDRSMST.OPEN_DR_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_CNM;
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_ENM;
        }
        BPCPFHIS.DATA.RLT_BANK = "" + CICACCU.DATA.OPN_BR;
        JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_OTZMS_DATA() throws IOException,SQLException,Exception {
        TDCOTZMS.AC = TDB1370_AWA_1370.STDES_AC;
        TDCOTZMS.UPDTBL_TLR = TDRCALL.UPD_TLT;
        TDCOTZMS.UPDTBL_DATE = TDRCALL.UPD_DATE;
    }
    public void R000_TRANS_NTZMS_DATA() throws IOException,SQLException,Exception {
        TDCNTZMS.AC = TDB1370_AWA_1370.STDES_AC;
        TDCNTZMS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDCNTZMS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
