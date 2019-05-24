package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSECQC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    DBParm DCTDCICT_RD;
    boolean pgmRtn = false;
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_DCZPQPRD = "DC-P-QUERY-PROD";
    String CPN_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "DC330";
    SCCSTAR SCCSTAR = new SCCSTAR();
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_PROD_CD = " ";
    String WS_HOLDER_CINO = " ";
    String WS_REL_DR_CARD = " ";
    String WS_ACAC_NO = " ";
    String WS_CARD_NO = " ";
    char WS_PSW_TYP = ' ';
    String WS_TRAN_PIN_DAT = " ";
    int WS_PIN_ERR_CNT = 0;
    String WS_PRIM_CARD_NO = " ";
    DCZSECQC_WS_TEMP WS_TEMP = new DCZSECQC_WS_TEMP();
    DCZSECQC_WS_OUTPUT WS_OUTPUT = new DCZSECQC_WS_OUTPUT();
    String WS_ARPC_F = " ";
    DCZSECQC_WS_DATA_TO_MAC WS_DATA_TO_MAC = new DCZSECQC_WS_DATA_TO_MAC();
    DCZSECQC_WS_DATA_TO_MAC2 WS_DATA_TO_MAC2 = new DCZSECQC_WS_DATA_TO_MAC2();
    DCZSECQC_WS_JOB WS_JOB = new DCZSECQC_WS_JOB();
    double WS_CARD_BALANCE = 0;
    char WS_OPEN_IC_FLG = ' ';
    String WS_CARD_PROD_CD = " ";
    String WS_CI_NO = " ";
    String WS_CI_NM = " ";
    String WS_STL_AC_NM = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRDCICT DCRDCICT = new DCRDCICT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCUABOX BPCUABOX = new BPCUABOX();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCSARQC DCCSARQC = new DCCSARQC();
    DCCSWJOB DCCSWJOB = new DCCSWJOB();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DCRCPARM DCRCPARM = new DCRCPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICCUST CICCUST = new CICCUST();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DCCF303 DCCF303 = new DCCF303();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DCCSECQC DCCSECQC;
    public void MP(SCCGWA SCCGWA, DCCSECQC DCCSECQC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSECQC = DCCSECQC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSECQC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCSECQC.IO_AREA.QC_TYP == '0' 
            || DCCSECQC.IO_AREA.QC_TYP == '1') {
            B020_CHECK_PSW();
            if (pgmRtn) return;
        }
        if (WS_OPEN_IC_FLG == 'Y') {
            B025_OPEN_IC_AC();
            if (pgmRtn) return;
        }
        B030_GET_CARD_ACAC();
        if (pgmRtn) return;
        B040_AMT_CHANGE_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "NCBCFX B060 STATR ");
        B060_SCRIPT_WRITE_CARD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "NCBCFX B060 STATR ");
        B050_ADD_DCTDCICT_INFO();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.CARD_NO);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.QC_TYP);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.CHIP_BAL_AMT);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.QC_AMT);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.UNASSIGN_CRD);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.PSW);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.FEE);
        CEP.TRC(SCCGWA, DCCSECQC.SLT_AC);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSECQC.IO_AREA.CARD_NO;
        WS_REL_DR_CARD = DCCSECQC.IO_AREA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            if (DCRCDDAT.CARD_LNK_TYP == '2') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LNK_CARD_ECAC_FBD);
            }
            CEP.TRC(SCCGWA, DCRCDDAT.EXP_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            B012_CHECK_CARD_STSW();
            if (pgmRtn) return;
            if (DCRCDDAT.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CRT_EXPIRE;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRCDDAT.CARD_LNK_TYP == '2') {
                WS_PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
            }
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCSECQC.IO_AREA.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_PROD_FLG);
            if (DCCUCINF.CARD_PROD_FLG == 'S') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_CITZEN_CRD;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            } else if (DCCUCINF.CARD_PROD_FLG == 'Y') {
                CICQACRL.DATA.AC_NO = DCCSECQC.IO_AREA.CARD_NO;
                CICQACRL.DATA.AC_REL = "03";
                CICQACRL.FUNC = 'I';
                S000_CALL_CIZQACRL();
                if (pgmRtn) return;
                WS_REL_DR_CARD = CICQACRL.O_DATA.O_REL_AC_NO;
                CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
                CEP.TRC(SCCGWA, WS_REL_DR_CARD);
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = WS_REL_DR_CARD;
                T000_READ_DCTCDDAT();
                if (pgmRtn) return;
                B012_CHECK_CARD_STSW();
                if (pgmRtn) return;
            }
            WS_PSW_TYP = DCRCDDAT.PSW_TYP;
            WS_TRAN_PIN_DAT = DCRCDDAT.TRAN_PIN_DAT;
            WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            WS_CARD_PROD_CD = DCRCDDAT.PROD_CD;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("0")) {
                WS_OPEN_IC_FLG = 'Y';
            }
        }
        if (DCCSECQC.IO_AREA.QC_TYP == '0' 
            && WS_PRIM_CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = WS_PRIM_CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            B012_CHECK_CARD_STSW();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.QC_TYP);
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.UNASSIGN_CRD);
        if (DCCSECQC.IO_AREA.QC_TYP == '1') {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSECQC.IO_AREA.UNASSIGN_CRD;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            WS_TRAN_PIN_DAT = DCRCDDAT.TRAN_PIN_DAT;
            WS_PSW_TYP = DCRCDDAT.PSW_TYP;
            B012_CHECK_CARD_STSW();
            if (pgmRtn) return;
        }
        if (DCCSECQC.IO_AREA.ARQC_DATA == null) DCCSECQC.IO_AREA.ARQC_DATA = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.ARQC_DATA += " ";
        if (!DCCSECQC.COUNT_NUM.equalsIgnoreCase(DCCSECQC.IO_AREA.ARQC_DATA.substring(63 - 1, 63 + 4 - 1))) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ARQC_NOT_MATCH);
        }
    }
    public void B012_CHECK_CARD_STSW() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            if (DCRCDDAT.CARD_STS != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PASSWD_LOSS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_CHECK_PSW() throws IOException,SQLException,Exception {
        if (DCCSECQC.IO_AREA.QC_TYP == '0') {
            WS_CARD_NO = WS_REL_DR_CARD;
        } else {
            WS_CARD_NO = DCCSECQC.IO_AREA.UNASSIGN_CRD;
        }
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.FUNC_CODE = 'P';
        DCCPCDCK.CARD_PSW = DCCSECQC.IO_AREA.PSW;
        DCCPCDCK.CARD_NO = WS_CARD_NO;
        S000_CALL_DCZPCDCK();
        if (pgmRtn) return;
    }
    public void B025_OPEN_IC_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_CARD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCCDQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = WS_CARD_PROD_CD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.IC_PROD_CD);
        IBS.init(SCCGWA, DDCUOPAC);
        DDCUOPAC.ACO_AC_TYP = '1';
        DDCUOPAC.CI_NO = WS_CI_NO;
        DDCUOPAC.CARD_TYP = '1';
        DDCUOPAC.PROD_CODE = DCCDQPRD.VAL.DATA.IC_PROD_CD;
        DDCUOPAC.AC_TYP = 'A';
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '1';
        DDCUOPAC.ACNO_FLG = 'B';
        DDCUOPAC.DRAW_MTH = ' ';
        DDCUOPAC.AC = DCCSECQC.IO_AREA.CARD_NO;
        DDCUOPAC.CCY = "156";
        DDCUOPAC.CCY_TYPE = '1';
        DDCUOPAC.AC_TYPE = '2';
        S000_CALL_DDZUOPAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSECQC.IO_AREA.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 16 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(16 + 1 - 1);
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_GET_CARD_ACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'I';
        CICQACAC.DATA.AGR_NO = DCCSECQC.IO_AREA.CARD_NO;
        CICQACAC.DATA.AID = "003";
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B040_AMT_CHANGE_PROCESS() throws IOException,SQLException,Exception {
        if (DCCSECQC.IO_AREA.QC_TYP == '2') {
            if (DCCSECQC.SLT_AC.trim().length() == 0) {
                IBS.init(SCCGWA, BPCUABOX);
                BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUABOX.CCY = "156";
                BPCUABOX.AMT = DCCSECQC.IO_AREA.QC_AMT;
                CEP.TRC(SCCGWA, BPCUABOX.CCY);
                CEP.TRC(SCCGWA, BPCUABOX.AMT);
                BPCUABOX.OPP_AC = WS_ACAC_NO;
                S000_CALL_BPZUABOX();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.AC = DCCSECQC.IO_AREA.CARD_NO;
                DDCUCRAC.CCY = "156";
                DDCUCRAC.TX_AMT = DCCSECQC.IO_AREA.QC_AMT;
                DDCUCRAC.AID = "003";
                DDCUCRAC.SMS_FLG = 'N';
                DDCUCRAC.TX_TYPE = 'C';
                S000_CALL_DDZUCRAC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, DDCUDRAC);
            if (DCCSECQC.IO_AREA.QC_TYP == '0') {
                DDCUDRAC.AC = WS_REL_DR_CARD;
            } else {
                DDCUDRAC.AC = DCCSECQC.IO_AREA.UNASSIGN_CRD;
            }
            DDCUDRAC.CCY = "156";
            DDCUDRAC.TX_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCUDRAC.OTHER_CCY = "156";
            DDCUDRAC.OTHER_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
            DDCUCRAC.TX_TYPE = 'T';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUCRAC.AID = "003";
            DDCUCRAC.SMS_FLG = 'N';
            DDCUCRAC.TX_TYPE = 'T';
            if (DCCSECQC.IO_AREA.QC_TYP == '0') {
                CEP.TRC(SCCGWA, WS_REL_DR_CARD);
                DDCUCRAC.OTHER_AC = WS_REL_DR_CARD;
            } else {
                CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.UNASSIGN_CRD);
                DDCUCRAC.OTHER_AC = DCCSECQC.IO_AREA.UNASSIGN_CRD;
            }
            CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AC);
            DDCUCRAC.OTHER_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUCRAC.OTHER_CCY = "156";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (DCCSECQC.IO_AREA.QC_TYP == '2' 
            && DCCSECQC.SLT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = DCCSECQC.SLT_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_STL_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = DCCSECQC.IO_AREA.CARD_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
            IBS.init(SCCGWA, DDCSTRAC);
            DDCSTRAC.FR_AC = DCCSECQC.SLT_AC;
            DDCSTRAC.FR_BV_TYPE = '3';
            DDCSTRAC.FR_CCY = "156";
            DDCSTRAC.FR_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCSTRAC.FR_AC_CNAME = WS_STL_AC_NM;
            DDCSTRAC.TO_CARD = DCCSECQC.IO_AREA.CARD_NO;
            DDCSTRAC.RLT_AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCSTRAC.TO_CCY = "156";
            DDCSTRAC.TO_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCSTRAC.TO_AC_CNAME = WS_CI_NM;
            DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCSTRAC.TXN_REGION = '0';
            DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCSTRAC.TXN_TYPE = "02";
            DDCSTRAC.IN_OUT_FLG = '2';
            S000_CALL_DDZSTRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCUDRAC.CCY = "156";
            DDCUDRAC.TX_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCUDRAC.OTHER_CCY = "156";
            DDCUDRAC.OTHER_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
            DDCUCRAC.TX_TYPE = 'T';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUCRAC.AID = "003";
            DDCUCRAC.SMS_FLG = 'N';
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.OTHER_AC = DCCSECQC.IO_AREA.CARD_NO;
            DDCUCRAC.OTHER_AMT = DCCSECQC.IO_AREA.QC_AMT;
            DDCUCRAC.OTHER_CCY = "156";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B050_ADD_DCTDCICT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.CARD_NO = DCCSECQC.IO_AREA.CARD_NO;
        DCRDCICT.TXN_TYP = DCCSECQC.IO_AREA.QC_TYP;
        DCRDCICT.TXN_AMT = DCCSECQC.IO_AREA.QC_AMT;
        DCRDCICT.BEF_TXN_AMT = DCCSECQC.IO_AREA.CHIP_BAL_AMT;
        DCRDCICT.TXN_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        DCRDCICT.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRDCICT.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.COUNT_CNT = DCCSECQC.COUNT_NUM;
        DCRDCICT.TO_AC_NO = WS_REL_DR_CARD;
        DCRDCICT.WRITE_CARD_STS = '1';
        DCRDCICT.TXN_STS = '0';
        DCRDCICT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B060_SCRIPT_WRITE_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = 'C';
        if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
        if (DCCSECQC.IO_AREA.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
            JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
            SCCTSSC.COMM_AREA_C.C_DESIGNID = DCCSECQC.IO_AREA.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        } else {
            if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
            JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
            SCCTSSC.COMM_AREA_C.C_DESIGNID = DCCSECQC.IO_AREA.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        SCCTSSC.COMM_AREA_C.C_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_C.C_KEYMODEL_ID = "MDK-Ac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_C.C_KEYMODEL_ID = "RMDKAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        }
        SCCTSSC.COMM_AREA_C.C_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSECQC.IO_AREA.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSECQC.CARD_SEQ;
        SCCTSSC.COMM_AREA_C.C_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        if (DCCSECQC.IO_AREA.ARQC_DATA == null) DCCSECQC.IO_AREA.ARQC_DATA = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_C.C_SKGENE = DCCSECQC.IO_AREA.ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCCTSSC.COMM_AREA_C.C_DATATO_ARQC = DCCSECQC.IO_AREA.ARQC_DATA;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCCTSSC.COMM_AREA_C.C_ARQC = DCCSECQC.IO_AREA.ARQC;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        SCCTSSC.COMM_AREA_C.C_ARC = "00";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_C);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_C.C_O_ARPC);
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            WS_OUTPUT.WS_ARPC = SCCTSSC.COMM_AREA_C.C_O_ARPC;
        }
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            if (SCCTSSC.COMM_AREA_C.C_O_ARPC == null) SCCTSSC.COMM_AREA_C.C_O_ARPC = "";
            JIBS_tmp_int = SCCTSSC.COMM_AREA_C.C_O_ARPC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_C.C_O_ARPC += " ";
            WS_OUTPUT.WS_ARPC = SCCTSSC.COMM_AREA_C.C_O_ARPC.substring(0, 16);
        }
        WS_OUTPUT.WS_AUT_RC = "3030";
        WS_ARPC_F = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        CEP.TRC(SCCGWA, WS_OUTPUT);
        B080_CHECK_CARD_BALANCE();
        if (pgmRtn) return;
        B090_ADD_BALANCE();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF303);
        DCCF303.TRD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCF303.TRD_SEQNO = SCCGWA.COMM_AREA.JRN_NO;
        DCCF303.QC_AMT = DCCSECQC.IO_AREA.QC_AMT;
        DCCF303.CHIP_BAL_AMT = DCCSECQC.IO_AREA.QC_AMT + DCCSECQC.IO_AREA.CHIP_BAL_AMT;
        DCCF303.CARD_NO = DCCSECQC.IO_AREA.CARD_NO;
        DCCF303.QC_TYP = DCCSECQC.IO_AREA.QC_TYP;
        CEP.TRC(SCCGWA, DCCSECQC.IO_AREA.QC_TYP);
        DCCF303.JOB = IBS.CLS2CPY(SCCGWA, WS_JOB);
        DCCF303.ARPC = WS_ARPC_F;
        CEP.TRC(SCCGWA, DCCF303.JOB);
        CEP.TRC(SCCGWA, DCCF303.ARPC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF303;
        SCCFMT.DATA_LEN = 2894;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B080_CHECK_CARD_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '4';
        if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
        if (DCCSECQC.IO_AREA.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
            JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSECQC.IO_AREA.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        } else {
            if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
            JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSECQC.IO_AREA.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "MDK-Mac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "RMDKMAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSECQC.IO_AREA.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSECQC.CARD_SEQ;
        SCCTSSC.COMM_AREA_4.4_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSECQC.IO_AREA.ARQC_DATA == null) DCCSECQC.IO_AREA.ARQC_DATA = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_4.4_SKGENE = DCCSECQC.IO_AREA.ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        SCCTSSC.COMM_AREA_4.4_IV = "0000000000000000";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        WS_DATA_TO_MAC2.WS_DATA21 = SCCTSSC.COMM_AREA_4.4_SKGENE;
        JIBS_tmp_str[0] = "" + DCCSECQC.IO_AREA.CHIP_BAL_AMT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_DATA_TO_MAC2.WS_DATA22 = JIBS_tmp_str[0].substring(7 - 1, 7 + 10 - 1);
        WS_DATA_TO_MAC2.WS_DATA23 = "00";
        SCCTSSC.COMM_AREA_4.4_DATATO_MAC = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_MAC2);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_4.4_O_MAC);
        CEP.TRC(SCCGWA, DCCSECQC.ISSUE_DATA);
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (SCCTSSC.COMM_AREA_4.4_O_MAC == null) SCCTSSC.COMM_AREA_4.4_O_MAC = "";
        JIBS_tmp_int = SCCTSSC.COMM_AREA_4.4_O_MAC.length();
        for (int i=0;i<2048-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_4.4_O_MAC += " ";
        if (!DCCSECQC.ISSUE_DATA.substring(31 - 1, 31 + 8 - 1).equalsIgnoreCase(SCCTSSC.COMM_AREA_4.4_O_MAC.substring(0, 8))) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BALANCE_WRONG);
        }
    }
    public void B090_ADD_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '4';
        if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
        if (DCCSECQC.IO_AREA.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
            JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSECQC.IO_AREA.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        } else {
            if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
            JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSECQC.IO_AREA.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "MDK-Mac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        if (DCCSECQC.ISSUE_DATA == null) DCCSECQC.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSECQC.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSECQC.ISSUE_DATA += " ";
        if (DCCSECQC.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "RMDKMAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSECQC.IO_AREA.CARD_NO == null) DCCSECQC.IO_AREA.CARD_NO = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSECQC.IO_AREA.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSECQC.CARD_SEQ;
        SCCTSSC.COMM_AREA_4.4_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSECQC.IO_AREA.ARQC_DATA == null) DCCSECQC.IO_AREA.ARQC_DATA = "";
        JIBS_tmp_int = DCCSECQC.IO_AREA.ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSECQC.IO_AREA.ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_4.4_SKGENE = DCCSECQC.IO_AREA.ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        SCCTSSC.COMM_AREA_4.4_IV = "0000000000000000";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        WS_DATA_TO_MAC.WS_DATA1 = "04DA9F790A";
        WS_DATA_TO_MAC.WS_DATA2 = SCCTSSC.COMM_AREA_4.4_SKGENE;
        WS_DATA_TO_MAC.WS_DATA3 = DCCSECQC.IO_AREA.ARQC;
        WS_CARD_BALANCE = DCCSECQC.IO_AREA.CHIP_BAL_AMT + DCCSECQC.IO_AREA.QC_AMT;
        CEP.TRC(SCCGWA, WS_CARD_BALANCE);
        if (WS_CARD_BALANCE > 1000) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_QC_OVER_MAX_AMT);
        }
        JIBS_tmp_str[0] = "" + WS_CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_DATA_TO_MAC.WS_DATA4 = JIBS_tmp_str[0].substring(5 - 1, 5 + 12 - 1);
        SCCTSSC.COMM_AREA_4.4_DATATO_MAC = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_MAC);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_4.4_O_MAC);
        WS_JOB.WS_TAG21 = "72";
        WS_JOB.WS_LEN21 = "19";
        WS_JOB.WS_TAG22 = "9F18";
        WS_JOB.WS_LEN22 = "04";
        WS_JOB.WS_ISSU_DATA2 = "00000001";
        WS_JOB.WS_TAG23 = "86";
        WS_JOB.WS_LEN23 = "10";
        WS_JOB.WS_TAG24 = "04DA9F790A";
        JIBS_tmp_str[0] = "" + WS_CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_JOB.WS_AMT = JIBS_tmp_str[0].substring(5 - 1, 5 + 12 - 1);
        if (SCCTSSC.COMM_AREA_4.4_O_MAC == null) SCCTSSC.COMM_AREA_4.4_O_MAC = "";
        JIBS_tmp_int = SCCTSSC.COMM_AREA_4.4_O_MAC.length();
        for (int i=0;i<2048-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_4.4_O_MAC += " ";
        WS_JOB.WS_MAC2 = SCCTSSC.COMM_AREA_4.4_O_MAC.substring(0, 8);
        WS_JOB.WS_LE2 = "00";
        CEP.TRC(SCCGWA, WS_JOB);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZTSSC() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        CEP.TRC(SCCGWA, SCCTSSC.RC);
        if (SCCTSSC.RC != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ERROR_FROM_ENP);
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZPQPRD, DCCDQPRD);
        CEP.TRC(SCCGWA, DCCDQPRD.RC);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            CEP.TRC(SCCGWA, "KIA IS HERE");
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "KIA IS HERE2");
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZPQPRD, BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void T000_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.WRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
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
