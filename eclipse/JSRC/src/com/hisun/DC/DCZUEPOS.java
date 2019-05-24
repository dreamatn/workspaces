package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUEPOS {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DCTDCICH_RD;
    DBParm DCTDCICE_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTICERD_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_MIB_AC_NO = "0434001562187734000000005";
    String WS_ERR_MSG = " ";
    double WS_AMT = 0;
    char WS_CHECK_TXN_DATE = ' ';
    char WS_CHECK_TC_DATE = ' ';
    char WS_CHECK_TXN_NUM = ' ';
    char WS_PROCESS_AC = ' ';
    DCZUEPOS_WS_DATATOTC WS_DATATOTC = new DCZUEPOS_WS_DATATOTC();
    DCZUEPOS_WS_TEMP WS_TEMP = new DCZUEPOS_WS_TEMP();
    double WS_TXN_AMT = 0;
    double WS_AUT_AMT1 = 0;
    double WS_OTHER_AMT1 = 0;
    double WS_FEE = 0;
    DCZUEPOS_WS_YYYYMMDD WS_YYYYMMDD = new DCZUEPOS_WS_YYYYMMDD();
    String WS_CI_NO = " ";
    String WS_EC_ACAC_NO = " ";
    String WS_NEW_CARD_NO = " ";
    char WS_FHIS_FLG = ' ';
    char WS_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQCHDC CICQCHDC = new CICQCHDC();
    DCRDCICE DCRDCICE = new DCRDCICE();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DCRDCICH DCRDCICH = new DCRDCICH();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCUTRF DDCUTRF = new DDCUTRF();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRICERD DCRICERD = new DCRICERD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUEPOS DCCUEPOS;
    public void MP(SCCGWA SCCGWA, DCCUEPOS DCCUEPOS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUEPOS = DCCUEPOS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUEPOS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_CARD_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUEPOS.TXN_CODE);
        if (DCCUEPOS.TXN_CODE.equalsIgnoreCase("300")) {
            B010_PROCESS_TC300();
            if (pgmRtn) return;
        }
        if (DCCUEPOS.TXN_CODE.equalsIgnoreCase("301")) {
            B020_PROCESS_TC301();
            if (pgmRtn) return;
        }
    }
    public void B010_PROCESS_TC300() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUEPOS.TXN_DT);
        CEP.TRC(SCCGWA, DCCUEPOS.CARD_NO);
        CEP.TRC(SCCGWA, DCCUEPOS.APP_DATA);
        CEP.TRC(SCCGWA, DCCUEPOS.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCUEPOS.APP_TXN_NUM);
        CEP.TRC(SCCGWA, DCCUEPOS.TC);
        CEP.TRC(SCCGWA, DCCUEPOS.AUT_AMT);
        CEP.TRC(SCCGWA, DCCUEPOS.OTHER_AMT);
        CEP.TRC(SCCGWA, DCCUEPOS.CNTY_CODE);
        CEP.TRC(SCCGWA, DCCUEPOS.ZD_RLT);
        CEP.TRC(SCCGWA, DCCUEPOS.TXN_CCY);
        CEP.TRC(SCCGWA, DCCUEPOS.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCUEPOS.UNKNOWN_NUM);
        CEP.TRC(SCCGWA, DCCUEPOS.APP_EXC_FLG);
        CEP.TRC(SCCGWA, DCCUEPOS.TXN_AMT);
        IBS.init(SCCGWA, SCCCLDT);
        WS_YYYYMMDD.WS_YY = 20;
        if (DCCUEPOS.TXN_DT.trim().length() == 0) WS_YYYYMMDD.WS_YYMMDD = 0;
        else WS_YYYYMMDD.WS_YYMMDD = Integer.parseInt(DCCUEPOS.TXN_DT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_YYYYMMDD);
        SCCCLDT.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
        SCCCLDT.DAYS = 31;
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCCLDT.DATE2 < SCCGWA.COMM_AREA.AC_DATE) {
            WS_CHECK_TXN_DATE = 'N';
        }
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '2';
        if (DCCUEPOS.CARD_NO == null) DCCUEPOS.CARD_NO = "";
        JIBS_tmp_int = DCCUEPOS.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCUEPOS.CARD_NO += " ";
        if (DCCUEPOS.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCUEPOS.CARD_NO == null) DCCUEPOS.CARD_NO = "";
            JIBS_tmp_int = DCCUEPOS.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCUEPOS.CARD_NO += " ";
            SCCTSSC.COMM_AREA_2.2_DESIGNID = DCCUEPOS.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        } else {
            if (DCCUEPOS.CARD_NO == null) DCCUEPOS.CARD_NO = "";
            JIBS_tmp_int = DCCUEPOS.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCUEPOS.CARD_NO += " ";
            SCCTSSC.COMM_AREA_2.2_DESIGNID = DCCUEPOS.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        }
        SCCTSSC.COMM_AREA_2.2_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        if (DCCUEPOS.APP_DATA == null) DCCUEPOS.APP_DATA = "";
        JIBS_tmp_int = DCCUEPOS.APP_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUEPOS.APP_DATA += " ";
        if (DCCUEPOS.APP_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_2.2_KEYMODEL_ID = "MDK-Ac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        }
        if (DCCUEPOS.APP_DATA == null) DCCUEPOS.APP_DATA = "";
        JIBS_tmp_int = DCCUEPOS.APP_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUEPOS.APP_DATA += " ";
        if (DCCUEPOS.APP_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_2.2_KEYMODEL_ID = "RMDKAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        }
        SCCTSSC.COMM_AREA_2.2_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        if (DCCUEPOS.CARD_NO == null) DCCUEPOS.CARD_NO = "";
        JIBS_tmp_int = DCCUEPOS.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCUEPOS.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCUEPOS.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        if (DCCUEPOS.CARD_SEQ == null) DCCUEPOS.CARD_SEQ = "";
        JIBS_tmp_int = DCCUEPOS.CARD_SEQ.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DCCUEPOS.CARD_SEQ += " ";
        if (DCCUEPOS.CARD_SEQ.substring(2 - 1, 2 + 2 - 1).trim().length() == 0) WS_TEMP.WS_TEMP_CARD_SEQ = 0;
        else WS_TEMP.WS_TEMP_CARD_SEQ = Short.parseShort(DCCUEPOS.CARD_SEQ.substring(2 - 1, 2 + 2 - 1));
        SCCTSSC.COMM_AREA_2.2_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        SCCTSSC.COMM_AREA_2.2_SKGENE = DCCUEPOS.APP_TXN_NUM;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        WS_DATATOTC.WS_AUT_AMT = DCCUEPOS.AUT_AMT;
        WS_DATATOTC.WS_OTHER_AMT = DCCUEPOS.OTHER_AMT;
        WS_DATATOTC.WS_CNTY_CODE = DCCUEPOS.CNTY_CODE;
        WS_DATATOTC.WS_ZD_RLT = DCCUEPOS.ZD_RLT;
        WS_DATATOTC.WS_TXN_CCY = DCCUEPOS.TXN_CCY;
        if (DCCUEPOS.TXN_DT.trim().length() == 0) WS_DATATOTC.WS_TXN_DT = 0;
        else WS_DATATOTC.WS_TXN_DT = Integer.parseInt(DCCUEPOS.TXN_DT);
        WS_DATATOTC.WS_TXN_TYPE = DCCUEPOS.TXN_TYPE;
        WS_DATATOTC.WS_UNKNOWN_NUM = DCCUEPOS.UNKNOWN_NUM;
        WS_DATATOTC.WS_APP_EXC_FLG = DCCUEPOS.APP_EXC_FLG;
        WS_DATATOTC.WS_APP_TXN_NUM = DCCUEPOS.APP_TXN_NUM;
        if (DCCUEPOS.APP_DATA == null) DCCUEPOS.APP_DATA = "";
        JIBS_tmp_int = DCCUEPOS.APP_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUEPOS.APP_DATA += " ";
        WS_DATATOTC.WS_APP_DATA = DCCUEPOS.APP_DATA.substring(7 - 1, 7 + 8 - 1);
        SCCTSSC.COMM_AREA_2.2_DATATOTC = IBS.CLS2CPY(SCCGWA, WS_DATATOTC);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        SCCTSSC.COMM_AREA_2.2_TC = DCCUEPOS.TC;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_2);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.RC);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_2.2_O_FLG);
        if (SCCTSSC.COMM_AREA_2.2_O_FLG == 'F' 
            || (SCCTSSC.RC != 0)) {
            WS_CHECK_TC_DATE = 'N';
        }
        IBS.init(SCCGWA, DCRDCICH);
        DCRDCICH.CARD_NO = DCCUEPOS.CARD_NO;
        DCRDCICH.APP_TXN_NUM = DCCUEPOS.APP_TXN_NUM;
        T000_READ_DCTDCICH();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            WS_CHECK_TXN_NUM = 'N';
        }
        WS_TXN_AMT = DCCUEPOS.TXN_AMT / 100;
        WS_AUT_AMT1 = DCCUEPOS.AUT_AMT / 100;
        WS_OTHER_AMT1 = DCCUEPOS.OTHER_AMT / 100;
        CEP.TRC(SCCGWA, WS_TXN_AMT);
        IBS.init(SCCGWA, DCRICERD);
        DCRICERD.KEY.CARD_NO = DCCUEPOS.CARD_NO;
        DCRICERD.NEXT_PROCESS_STS = '0';
        T000_READUPD_DCTICERD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y' 
            && DCCUEPOS.APP_TXN_NUM.compareTo(DCRICERD.APP_COMPUTE_NUM) < 0) {
            DCRICERD.CARD_AMT = DCRICERD.CARD_AMT - WS_TXN_AMT;
            DCRICERD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRICERD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTICERD();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUEPOS.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        if (DCRCDDAT.CARD_STS == 'C') {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = DCCUEPOS.CARD_NO;
            CICQCHDC.DATA.O_ENTY_TYP = '2';
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQCHDC.DATA.N_AGR_NO);
            if (CICQCHDC.DATA.N_AGR_NO.trim().length() > 0) {
                WS_NEW_CARD_NO = CICQCHDC.DATA.N_AGR_NO;
            }
            IBS.init(SCCGWA, DCRINRCD);
            DCRINRCD.KEY.CARD_NO = DCCUEPOS.CARD_NO;
            if ("1".trim().length() == 0) DCRINRCD.KEY.CARD_SEQ = 0;
            else DCRINRCD.KEY.CARD_SEQ = Short.parseShort("1");
            T000_READUPD_DCTINRCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRINRCD.KEY.INCD_TYPE);
            CEP.TRC(SCCGWA, DCRINRCD.NEW_CARD_NO);
            CEP.TRC(SCCGWA, DCRINRCD.PEND_AMT);
            if (WS_TBL_FLAG == 'Y' 
                && DCRINRCD.PEND_AMT > 0) {
                DCRINRCD.PEND_AMT = DCRINRCD.PEND_AMT - WS_TXN_AMT;
                DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTINRCD();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_CI_NO);
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = K_MIB_AC_NO;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NEW_CARD_NO);
        IBS.init(SCCGWA, DDCUTRF);
        if (WS_NEW_CARD_NO.trim().length() == 0) {
            DDCUTRF.FR_AC = DCCUEPOS.CARD_NO;
        } else {
            DDCUTRF.FR_AC = WS_NEW_CARD_NO;
        }
        DDCUTRF.FR_APP = "DD";
        DDCUTRF.TO_AC = K_MIB_AC_NO;
        DDCUTRF.RLT_AC = K_MIB_AC_NO;
        DDCUTRF.TO_APP = "AI";
        DDCUTRF.TO_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        DDCUTRF.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        DDCUTRF.CI_NO = WS_CI_NO;
        DDCUTRF.CCY = "156";
        DDCUTRF.CCY_TYPE = ' ';
        DDCUTRF.TX_AMT = WS_TXN_AMT;
        DDCUTRF.TX_MMO = "G111";
        DDCUTRF.RLT_CCY = "156";
        DDCUTRF.SMS_FLG = 'N';
        S000_CALL_DDZUTRF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUTRF.RC.RC_CODE);
        if (DDCUTRF.RC.RC_CODE != 0) {
            WS_PROCESS_AC = 'N';
        }
        if (WS_CHECK_TXN_DATE == 'N' 
            || WS_CHECK_TC_DATE == 'N' 
            || WS_CHECK_TXN_NUM == 'N' 
            || WS_PROCESS_AC == 'N') {
            IBS.init(SCCGWA, DCRDCICE);
            DCRDCICE.KEY.CLEAR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCICE.KEY.BATCH_NO = DCCUEPOS.BATCH_NO;
            DCRDCICE.KEY.BATCH_SN = DCCUEPOS.BATCH_SN;
            DCRDCICE.TXN_CODE = DCCUEPOS.TXN_CODE;
            DCRDCICE.CARD_NO = DCCUEPOS.CARD_NO;
            DCRDCICE.TXN_AMT = WS_TXN_AMT;
            DCRDCICE.TXN_CCY = DCCUEPOS.TXN_CCY;
            if (DCCUEPOS.TXN_DT.trim().length() == 0) DCRDCICE.TXN_DT = 0;
            else DCRDCICE.TXN_DT = Integer.parseInt(DCCUEPOS.TXN_DT);
            if (WS_PROCESS_AC == 'N') {
                DCRDCICE.EXACT_CHARGE_AMT = 0;
            } else {
                DCRDCICE.EXACT_CHARGE_AMT = WS_TXN_AMT;
            }
            if (WS_CHECK_TXN_DATE == 'N') {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = "2" + DCRDCICE.TXN_STS.substring(1);
            } else {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = "1" + DCRDCICE.TXN_STS.substring(1);
            }
            if (WS_CHECK_TC_DATE == 'N') {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 2 - 1) + "2" + DCRDCICE.TXN_STS.substring(2 + 1 - 1);
            } else {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 2 - 1) + "1" + DCRDCICE.TXN_STS.substring(2 + 1 - 1);
            }
            if (WS_CHECK_TXN_NUM == 'N') {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 3 - 1) + "2" + DCRDCICE.TXN_STS.substring(3 + 1 - 1);
            } else {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 3 - 1) + "1" + DCRDCICE.TXN_STS.substring(3 + 1 - 1);
            }
            if (WS_PROCESS_AC == 'N') {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 4 - 1) + "2" + DCRDCICE.TXN_STS.substring(4 + 1 - 1);
            } else {
                if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
                JIBS_tmp_int = DCRDCICE.TXN_STS.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
                DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 4 - 1) + "1" + DCRDCICE.TXN_STS.substring(4 + 1 - 1);
            }
            DCRDCICE.LAST_STS = '0';
            DCRDCICE.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCICE.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTDCICE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDCICH);
        DCRDCICH.KEY.CLEAR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICH.KEY.BATCH_NO = DCCUEPOS.BATCH_NO;
        DCRDCICH.KEY.BATCH_SN = DCCUEPOS.BATCH_SN;
        DCRDCICH.TXN_CODE = DCCUEPOS.TXN_CODE;
        DCRDCICH.CARD_NO = DCCUEPOS.CARD_NO;
        DCRDCICH.TXN_AMT = WS_TXN_AMT;
        DCRDCICH.TXN_CCY = DCCUEPOS.TXN_CCY;
        DCRDCICH.TXN_TIME = DCCUEPOS.TXN_TIME;
        DCRDCICH.SYS_FOLLOW_NO = DCCUEPOS.SYS_FOLLOW_NO;
        DCRDCICH.DL_BR_NO = DCCUEPOS.DL_BR_NO;
        DCRDCICH.FS_BR_NO = DCCUEPOS.FS_BR_NO;
        DCRDCICH.MARKET_TYP = DCCUEPOS.MARKET_TYP;
        DCRDCICH.SKJZD_NO = DCCUEPOS.SKJZD_NO;
        DCRDCICH.SKF_NO = DCCUEPOS.SKF_NO;
        DCRDCICH.SINGLE_DOUBLE_FLG = DCCUEPOS.SINGLE_DB_FLG;
        DCRDCICH.CUPS_SERIAL = DCCUEPOS.CUPS_SERIAL;
        DCRDCICH.RECEIVE_BR = DCCUEPOS.RECEIVE_BR;
        DCRDCICH.ISSUE_BR = DCCUEPOS.ISSUE_BR;
        DCRDCICH.CUPS_NOTIFY = DCCUEPOS.CUPS_NOTIFY;
        DCRDCICH.CHNL_NO = DCCUEPOS.CHNL_NO;
        DCRDCICH.FEATURE = DCCUEPOS.FEATURE;
        DCRDCICH.CROSS_BORDER_FLAG = DCCUEPOS.CROSS_FLAG;
        DCRDCICH.APP_TC = DCCUEPOS.TC;
        DCRDCICH.SER_MTH = DCCUEPOS.SER_MTH;
        if (DCCUEPOS.CARD_SEQ.trim().length() == 0) DCRDCICH.CARD_SEQ = 0;
        else DCRDCICH.CARD_SEQ = Short.parseShort(DCCUEPOS.CARD_SEQ);
        DCRDCICH.RW_STS = DCCUEPOS.RW_STS;
        DCRDCICH.CARD_TYP = DCCUEPOS.CARD_TYP;
        DCRDCICH.ZD_ABILITY = DCCUEPOS.ZD_ABILITY;
        DCRDCICH.ZD_RLT = DCCUEPOS.ZD_RLT;
        DCRDCICH.UNKNOWN_NUM = DCCUEPOS.UNKNOWN_NUM;
        DCRDCICH.JK_SEQ = DCCUEPOS.JK_SEQ;
        DCRDCICH.APP_DATA = DCCUEPOS.APP_DATA;
        DCRDCICH.APP_TXN_NUM = DCCUEPOS.APP_TXN_NUM;
        DCRDCICH.APP_EXC_FLG = DCCUEPOS.APP_EXC_FLG;
        if (DCCUEPOS.TXN_DT.trim().length() == 0) DCRDCICH.TXN_DT = 0;
        else DCRDCICH.TXN_DT = Integer.parseInt(DCCUEPOS.TXN_DT);
        DCRDCICH.CNTY_CODE = DCCUEPOS.CNTY_CODE;
        DCRDCICH.TXN_TYPE = DCCUEPOS.TXN_TYPE;
        DCRDCICH.AUT_AMT = WS_AUT_AMT1;
        DCRDCICH.TXN_CCY = DCCUEPOS.TXN_CCY;
        DCRDCICH.TC_RLT = DCCUEPOS.TC_RLT;
        if (DCCUEPOS.EXP_DATE.trim().length() == 0) DCRDCICH.EXP_DATE = 0;
        else DCRDCICH.EXP_DATE = Integer.parseInt(DCCUEPOS.EXP_DATE);
        DCRDCICH.TC_INF = DCCUEPOS.TC_INF;
        DCRDCICH.OTHER_AMT = WS_OTHER_AMT1;
        DCRDCICH.HLD_RLT = DCCUEPOS.HLD_RLT;
        DCRDCICH.ZD_TYPE = DCCUEPOS.ZD_TYPE;
        if (DCCUEPOS.APP_VER_NO.trim().length() == 0) DCRDCICH.APP_VER_NO = 0;
        else DCRDCICH.APP_VER_NO = Short.parseShort(DCCUEPOS.APP_VER_NO);
        DCRDCICH.TXN_SEQ = DCCUEPOS.TXN_SEQ;
        DCRDCICH.SKF_ADDR = DCCUEPOS.SKF_ADDR;
        DCRDCICH.INS_NM = DCCUEPOS.INS_NM;
        if (WS_PROCESS_AC == 'N') {
            DCRDCICH.REAL_AMT = 0;
        } else {
            DCRDCICH.REAL_AMT = WS_TXN_AMT;
        }
        if (WS_CHECK_TXN_DATE == 'N') {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = "2" + DCRDCICH.RESL_STS.substring(1);
        } else {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = "1" + DCRDCICH.RESL_STS.substring(1);
        }
        if (WS_CHECK_TC_DATE == 'N') {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 2 - 1) + "2" + DCRDCICH.RESL_STS.substring(2 + 1 - 1);
        } else {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 2 - 1) + "1" + DCRDCICH.RESL_STS.substring(2 + 1 - 1);
        }
        if (WS_CHECK_TXN_NUM == 'N') {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 3 - 1) + "2" + DCRDCICH.RESL_STS.substring(3 + 1 - 1);
        } else {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 3 - 1) + "1" + DCRDCICH.RESL_STS.substring(3 + 1 - 1);
        }
        if (WS_PROCESS_AC == 'N') {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 4 - 1) + "2" + DCRDCICH.RESL_STS.substring(4 + 1 - 1);
        } else {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 4 - 1) + "1" + DCRDCICH.RESL_STS.substring(4 + 1 - 1);
        }
        DCRDCICH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICH();
        if (pgmRtn) return;
    }
    public void B020_PROCESS_TC301() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUEPOS.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        if (DCRCDDAT.CARD_STS == 'C') {
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.DATA.O_AGR_NO = DCCUEPOS.CARD_NO;
            CICQCHDC.DATA.O_ENTY_TYP = '2';
            CICQCHDC.FUNC = 'W';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQCHDC.DATA.N_AGR_NO);
            if (CICQCHDC.DATA.N_AGR_NO.trim().length() > 0) {
                WS_NEW_CARD_NO = CICQCHDC.DATA.N_AGR_NO;
            }
        }
        CEP.TRC(SCCGWA, WS_CI_NO);
        WS_TXN_AMT = DCCUEPOS.TXN_AMT / 100;
        WS_AUT_AMT1 = DCCUEPOS.AUT_AMT / 100;
        WS_OTHER_AMT1 = DCCUEPOS.OTHER_AMT / 100;
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = K_MIB_AC_NO;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCUTRF);
        DDCUTRF.FR_AC = K_MIB_AC_NO;
        DDCUTRF.FR_APP = "AI";
        DDCUTRF.FR_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        if (WS_NEW_CARD_NO.trim().length() == 0) {
            DDCUTRF.TO_AC = DCCUEPOS.CARD_NO;
            DDCUTRF.RLT_AC = DCCUEPOS.CARD_NO;
        } else {
            DDCUTRF.TO_AC = WS_NEW_CARD_NO;
            DDCUTRF.RLT_AC = WS_NEW_CARD_NO;
        }
        DDCUTRF.TO_APP = "DD";
        DDCUTRF.CI_NO = WS_CI_NO;
        DDCUTRF.CCY = "156";
        DDCUTRF.CCY_TYPE = '1';
        DDCUTRF.TX_AMT = WS_TXN_AMT;
        DDCUTRF.TX_MMO = "G112";
        DDCUTRF.RLT_CCY = "156";
        S000_CALL_DDZUTRF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUTRF.RC.RC_CODE);
        if (DDCUTRF.RC.RC_CODE != 0) {
            WS_PROCESS_AC = 'N';
        }
        if (WS_PROCESS_AC == 'N') {
            IBS.init(SCCGWA, DCRDCICE);
            DCRDCICE.KEY.CLEAR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCICE.KEY.BATCH_NO = DCCUEPOS.BATCH_NO;
            DCRDCICE.KEY.BATCH_SN = DCCUEPOS.BATCH_SN;
            DCRDCICE.TXN_CODE = DCCUEPOS.TXN_CODE;
            DCRDCICE.CARD_NO = DCCUEPOS.CARD_NO;
            DCRDCICE.TXN_AMT = WS_TXN_AMT;
            DCRDCICE.TXN_CCY = DCCUEPOS.TXN_CCY;
            if (DCCUEPOS.TXN_DT.trim().length() == 0) DCRDCICE.TXN_DT = 0;
            else DCRDCICE.TXN_DT = Integer.parseInt(DCCUEPOS.TXN_DT);
            DCRDCICE.EXACT_CHARGE_AMT = 0;
            if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
            JIBS_tmp_int = DCRDCICE.TXN_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
            DCRDCICE.TXN_STS = "0" + DCRDCICE.TXN_STS.substring(1);
            if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
            JIBS_tmp_int = DCRDCICE.TXN_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
            DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 2 - 1) + "0" + DCRDCICE.TXN_STS.substring(2 + 1 - 1);
            if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
            JIBS_tmp_int = DCRDCICE.TXN_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
            DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 3 - 1) + "0" + DCRDCICE.TXN_STS.substring(3 + 1 - 1);
            if (DCRDCICE.TXN_STS == null) DCRDCICE.TXN_STS = "";
            JIBS_tmp_int = DCRDCICE.TXN_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICE.TXN_STS += " ";
            DCRDCICE.TXN_STS = DCRDCICE.TXN_STS.substring(0, 4 - 1) + "2" + DCRDCICE.TXN_STS.substring(4 + 1 - 1);
            DCRDCICE.LAST_STS = '0';
            DCRDCICE.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCICE.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTDCICE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDCICH);
        DCRDCICH.KEY.CLEAR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICH.KEY.BATCH_NO = DCCUEPOS.BATCH_NO;
        DCRDCICH.KEY.BATCH_SN = DCCUEPOS.BATCH_SN;
        DCRDCICH.TXN_CODE = DCCUEPOS.TXN_CODE;
        DCRDCICH.CARD_NO = DCCUEPOS.CARD_NO;
        DCRDCICH.TXN_AMT = WS_TXN_AMT;
        DCRDCICH.TXN_CCY = DCCUEPOS.TXN_CCY;
        DCRDCICH.TXN_TIME = DCCUEPOS.TXN_TIME;
        DCRDCICH.SYS_FOLLOW_NO = DCCUEPOS.SYS_FOLLOW_NO;
        DCRDCICH.DL_BR_NO = DCCUEPOS.DL_BR_NO;
        DCRDCICH.FS_BR_NO = DCCUEPOS.FS_BR_NO;
        DCRDCICH.MARKET_TYP = DCCUEPOS.MARKET_TYP;
        DCRDCICH.SKJZD_NO = DCCUEPOS.SKJZD_NO;
        DCRDCICH.SKF_NO = DCCUEPOS.SKF_NO;
        DCRDCICH.SKF_ADDR = DCCUEPOS.SKF_ADDR;
        DCRDCICH.SINGLE_DOUBLE_FLG = DCCUEPOS.SINGLE_DB_FLG;
        DCRDCICH.CUPS_SERIAL = DCCUEPOS.CUPS_SERIAL;
        DCRDCICH.RECEIVE_BR = DCCUEPOS.RECEIVE_BR;
        DCRDCICH.ISSUE_BR = DCCUEPOS.ISSUE_BR;
        DCRDCICH.CUPS_NOTIFY = DCCUEPOS.CUPS_NOTIFY;
        DCRDCICH.CHNL_NO = DCCUEPOS.CHNL_NO;
        DCRDCICH.FEATURE = DCCUEPOS.FEATURE;
        DCRDCICH.CROSS_BORDER_FLAG = DCCUEPOS.CROSS_FLAG;
        DCRDCICH.APP_TC = DCCUEPOS.TC;
        DCRDCICH.SER_MTH = DCCUEPOS.SER_MTH;
        if (DCCUEPOS.CARD_SEQ.trim().length() == 0) DCRDCICH.CARD_SEQ = 0;
        else DCRDCICH.CARD_SEQ = Short.parseShort(DCCUEPOS.CARD_SEQ);
        DCRDCICH.RW_STS = DCCUEPOS.RW_STS;
        DCRDCICH.CARD_TYP = DCCUEPOS.CARD_TYP;
        DCRDCICH.ZD_ABILITY = DCCUEPOS.ZD_ABILITY;
        DCRDCICH.ZD_RLT = DCCUEPOS.ZD_RLT;
        DCRDCICH.UNKNOWN_NUM = DCCUEPOS.UNKNOWN_NUM;
        DCRDCICH.JK_SEQ = DCCUEPOS.JK_SEQ;
        DCRDCICH.APP_DATA = DCCUEPOS.APP_DATA;
        DCRDCICH.APP_TXN_NUM = DCCUEPOS.APP_TXN_NUM;
        DCRDCICH.APP_EXC_FLG = DCCUEPOS.APP_EXC_FLG;
        if (DCCUEPOS.TXN_DT.trim().length() == 0) DCRDCICH.TXN_DT = 0;
        else DCRDCICH.TXN_DT = Integer.parseInt(DCCUEPOS.TXN_DT);
        DCRDCICH.CNTY_CODE = DCCUEPOS.CNTY_CODE;
        DCRDCICH.TXN_TYPE = DCCUEPOS.TXN_TYPE;
        DCRDCICH.AUT_AMT = WS_AUT_AMT1;
        DCRDCICH.TXN_CCY = DCCUEPOS.TXN_CCY;
        DCRDCICH.TC_RLT = DCCUEPOS.TC_RLT;
        if (DCCUEPOS.EXP_DATE.trim().length() == 0) DCRDCICH.EXP_DATE = 0;
        else DCRDCICH.EXP_DATE = Integer.parseInt(DCCUEPOS.EXP_DATE);
        DCRDCICH.TC_INF = DCCUEPOS.TC_INF;
        DCRDCICH.OTHER_AMT = WS_OTHER_AMT1;
        DCRDCICH.HLD_RLT = DCCUEPOS.HLD_RLT;
        DCRDCICH.ZD_TYPE = DCCUEPOS.ZD_TYPE;
        DCRDCICH.INS_NM = DCCUEPOS.INS_NM;
        if (DCCUEPOS.APP_VER_NO.trim().length() == 0) DCRDCICH.APP_VER_NO = 0;
        else DCRDCICH.APP_VER_NO = Short.parseShort(DCCUEPOS.APP_VER_NO);
        DCRDCICH.TXN_SEQ = DCCUEPOS.TXN_SEQ;
        if (WS_PROCESS_AC == 'N') {
            DCRDCICH.REAL_AMT = 0;
        } else {
            DCRDCICH.REAL_AMT = WS_TXN_AMT;
        }
        if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
        JIBS_tmp_int = DCRDCICH.RESL_STS.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
        DCRDCICH.RESL_STS = "0" + DCRDCICH.RESL_STS.substring(1);
        if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
        JIBS_tmp_int = DCRDCICH.RESL_STS.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
        DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 2 - 1) + "0" + DCRDCICH.RESL_STS.substring(2 + 1 - 1);
        if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
        JIBS_tmp_int = DCRDCICH.RESL_STS.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
        DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 3 - 1) + "0" + DCRDCICH.RESL_STS.substring(3 + 1 - 1);
        if (WS_PROCESS_AC == 'N') {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 4 - 1) + "2" + DCRDCICH.RESL_STS.substring(4 + 1 - 1);
        } else {
            if (DCRDCICH.RESL_STS == null) DCRDCICH.RESL_STS = "";
            JIBS_tmp_int = DCRDCICH.RESL_STS.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) DCRDCICH.RESL_STS += " ";
            DCRDCICH.RESL_STS = DCRDCICH.RESL_STS.substring(0, 4 - 1) + "1" + DCRDCICH.RESL_STS.substring(4 + 1 - 1);
        }
        DCRDCICH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICH();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
    }
    public void S000_CALL_SCZTSSC() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_DDZUTRF() throws IOException,SQLException,Exception {
        DDZUTRF DDZUTRF = new DDZUTRF();
        DDZUTRF.MP(SCCGWA, DDCUTRF);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
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
    public void T000_READ_DCTDCICH() throws IOException,SQLException,Exception {
        DCTDCICH_RD = new DBParm();
        DCTDCICH_RD.TableName = "DCTDCICH";
        DCTDCICH_RD.where = "CARD_NO = :DCRDCICH.CARD_NO "
            + "AND APP_TXN_NUM = :DCRDCICH.APP_TXN_NUM";
        DCTDCICH_RD.fst = true;
        IBS.READ(SCCGWA, DCRDCICH, this, DCTDCICH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTDCICE() throws IOException,SQLException,Exception {
        DCTDCICE_RD = new DBParm();
        DCTDCICE_RD.TableName = "DCTDCICE";
        DCTDCICE_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRDCICE, DCTDCICE_RD);
    }
    public void T000_READUPD_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ "
            + "AND INCD_TYPE = '11'";
        DCTINRCD_RD.upd = true;
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        IBS.REWRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
    }
    public void T000_READUPD_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        DCTICERD_RD.where = "CARD_NO = :DCRICERD.KEY.CARD_NO "
            + "AND NEXT_PROCESS_STS = :DCRICERD.NEXT_PROCESS_STS";
        DCTICERD_RD.upd = true;
        IBS.READ(SCCGWA, DCRICERD, this, DCTICERD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTICERD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTICERD() throws IOException,SQLException,Exception {
        DCTICERD_RD = new DBParm();
        DCTICERD_RD.TableName = "DCTICERD";
        IBS.REWRITE(SCCGWA, DCRICERD, DCTICERD_RD);
    }
    public void T000_WRITE_DCTDCICH() throws IOException,SQLException,Exception {
        DCTDCICH_RD = new DBParm();
        DCTDCICH_RD.TableName = "DCTDCICH";
        DCTDCICH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRDCICH, DCTDCICH_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS   ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
