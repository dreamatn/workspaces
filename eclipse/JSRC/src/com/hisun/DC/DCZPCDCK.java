package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZPCDCK {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    DBParm DCTBINPM_RD;
    String BSL_RTC_FLAG = "SIT_GN_20150507_V2";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN";
    SCCSTAR SCCSTAR = new SCCSTAR();
    String WS_ERR_MSG = " ";
    String WS_DT_CHAR4 = " ";
    int WS_CRT_DT = 0;
    short WS_CARD_LEN = 0;
    short WS_POINT = 0;
    int WS_PIN_ERR_CNT = 0;
    char WS_IC_FLG = ' ';
    char WS_PSW_CHECK_FLG = ' ';
    DCZPCDCK_WS_PW_SPCBINDL WS_PW_SPCBINDL = new DCZPCDCK_WS_PW_SPCBINDL();
    int WS_PW_SPCBINDL_FN5 = 0;
    int WS_PW_DIVIDE_R1 = 0;
    int WS_PW_DIVIDE_R2 = 0;
    short WS_PW_DIVIDE_REM1 = 0;
    DCZPCDCK_WS_TRK_PW_SPCBINDL WS_TRK_PW_SPCBINDL = new DCZPCDCK_WS_TRK_PW_SPCBINDL();
    int WS_PW_COMPARE = 0;
    String WS_PW_CHAR = " ";
    String WS_OUT_PW = " ";
    char WS_PSW_FLG = ' ';
    String WS_SERVE_CD = " ";
    int WS_EXPIRE_DT = 0;
    String WS_CVN1 = " ";
    String WS_CVN2 = " ";
    String WS_CVN3 = " ";
    String WS_MAG3 = " ";
    String WS_CARD_NO_TEMP = " ";
    char WS_FIND_FLG = ' ';
    char WS_SF_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCHMPW SCCHMPW = new SCCHMPW();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCUPWER DCCUPWER = new DCCUPWER();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCPARM DCRCPARM = new DCRCPARM();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACRI CICQACRI = new CICQACRI();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICQCHDC CICQCHDC = new CICQCHDC();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    SCCGWA SCCGWA;
    DCCPCDCK DCCPCDCK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCPCDCK DCCPCDCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCPCDCK = DCCPCDCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZPCDCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_COMMON_CHECK();
        DCCPCDCK.FUNC_CODE = 'N';
        if (DCCPCDCK.FUNC_CODE == 'P') {
            B200_CHECK_PASSWORD();
        } else if (DCCPCDCK.FUNC_CODE == 'T') {
            B300_CHECK_TRKDAT();
        } else if (DCCPCDCK.FUNC_CODE == 'B') {
            B200_CHECK_PASSWORD();
            B300_CHECK_TRKDAT();
        } else if (DCCPCDCK.FUNC_CODE == 'N') {
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_COMMON_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPCDCK.FUNC_CODE);
        CEP.TRC(SCCGWA, DCCPCDCK.CARD_NO);
        CEP.TRC(SCCGWA, DCCPCDCK.CARD_PSW);
        CEP.TRC(SCCGWA, DCCPCDCK.TRK2_DAT);
        CEP.TRC(SCCGWA, DCCPCDCK.TRK3_DAT);
        CEP.TRC(SCCGWA, DCCPCDCK.CARD_CVN2);
        if (DCCPCDCK.FUNC_CODE == 'P' 
            || DCCPCDCK.FUNC_CODE == 'B') {
            if (DCCPCDCK.CARD_PSW.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCPCDCK.FUNC_CODE == 'T' 
            || DCCPCDCK.FUNC_CODE == 'B') {
            if (DCCPCDCK.TRK2_DAT.trim().length() == 0 
                && DCCPCDCK.TRK3_DAT.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRK_DAT_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCPCDCK.CARD_NO;
        S000_CALL_DCZUCINF();
        CEP.TRC(SCCGWA, DCCUCINF.PSW_FLG);
        CEP.TRC(SCCGWA, DCCPCDCK.FUNC_CODE);
        if (DCCUCINF.PSW_FLG != 'Y' 
            && (DCCPCDCK.FUNC_CODE == 'P' 
            || DCCPCDCK.FUNC_CODE == 'B')) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUPPORT_PSW_TRT;
            S000_ERR_MSG_PROC();
        }
        S001_CHECK_STS();
        if (DCCPCDCK.CARD_CVN2.trim().length() > 0) {
            S000_CHECK_CVN2();
        }
        if (DCCPCDCK.TRK3_DAT == null) DCCPCDCK.TRK3_DAT = "";
        JIBS_tmp_int = DCCPCDCK.TRK3_DAT.length();
        for (int i=0;i<107-JIBS_tmp_int;i++) DCCPCDCK.TRK3_DAT += " ";
        if (DCCPCDCK.TRK3_DAT.substring(0, 4).equalsIgnoreCase("@@IC")) {
            WS_IC_FLG = 'Y';
        }
    }
    public void B200_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        WS_PSW_CHECK_FLG = 'Y';
        S000_CHECK_PASSWORD();
        if (WS_PSW_CHECK_FLG == 'N') {
            IBS.init(SCCGWA, DCRCPARM);
            IBS.init(SCCGWA, BPCPRMR);
            DCRCPARM.KEY.TYP = "DCPRM";
            BPCPRMR.FUNC = ' ';
            DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
            IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
            BPCPRMR.DAT_PTR = DCRCPARM;
            S000_CALL_BPZPRMR();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.PSW_LOCK_NUM);
            WS_PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT + 1;
            if (WS_PIN_ERR_CNT == DCRCPARM.DATA_TXT.PSW_LOCK_NUM) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSWD_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B300_CHECK_TRKDAT() throws IOException,SQLException,Exception {
        R000_GET_CARD_LENGTH();
        WS_CARD_LEN = DCRBINPM.CARD_LEN;
        if (DCCPCDCK.TRK2_DAT.trim().length() > 0) {
            IBS.init(SCCGWA, SCCTSSC);
            SCCTSSC.FUNC = '8';
            CEP.TRC(SCCGWA, DCCUCINF.MOVE_FLG);
            CEP.TRC(SCCGWA, WS_SF_FLG);
            if (DCCUCINF.MOVE_FLG == 'Y') {
                SCCTSSC.COMM_AREA_8.8_KEYMODEL_ID = "CBS.OLD.cvk1";
            } else {
                if (WS_SF_FLG == '1') {
                    SCCTSSC.COMM_AREA_8.8_KEYMODEL_ID = "CBS.OLD.cvk1";
                } else {
                    if (WS_SF_FLG == '3') {
                        SCCTSSC.COMM_AREA_8.8_KEYMODEL_ID = "CBS.NCB.cvk1";
                    }
                }
            }
            SCCTSSC.COMM_AREA_8.8_ACCNO = DCCPCDCK.CARD_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_8);
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            SCCTSSC.COMM_AREA_8.8_EXPIRES = DCCPCDCK.TRK2_DAT.substring(21 - 1, 21 + 4 - 1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_8);
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            SCCTSSC.COMM_AREA_8.8_SERVICE_CODE = DCCPCDCK.TRK2_DAT.substring(25 - 1, 25 + 3 - 1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_8);
            S000_CALL_SCZTSSC();
            WS_CVN1 = SCCTSSC.COMM_AREA_8.8_O_CVV;
            IBS.init(SCCGWA, SCCTSSC);
            SCCTSSC.FUNC = '8';
            if (DCCUCINF.MOVE_FLG == 'Y') {
                SCCTSSC.COMM_AREA_8.8_KEYMODEL_ID = "CBS.OLD.cvk2";
            } else {
                if (WS_SF_FLG == '1') {
                    SCCTSSC.COMM_AREA_8.8_KEYMODEL_ID = "CBS.OLD.cvk2";
                } else {
                    if (WS_SF_FLG == '3') {
                        SCCTSSC.COMM_AREA_8.8_KEYMODEL_ID = "CBS.NCB.cvk2";
                    }
                }
            }
            SCCTSSC.COMM_AREA_8.8_ACCNO = DCCPCDCK.CARD_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_8);
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            SCCTSSC.COMM_AREA_8.8_EXPIRES = DCCPCDCK.TRK2_DAT.substring(21 - 1, 21 + 4 - 1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_8);
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            SCCTSSC.COMM_AREA_8.8_SERVICE_CODE = DCCPCDCK.TRK2_DAT.substring(25 - 1, 25 + 3 - 1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_8);
            S000_CALL_SCZTSSC();
            WS_CVN2 = SCCTSSC.COMM_AREA_8.8_O_CVV;
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            CEP.TRC(SCCGWA, DCCPCDCK.TRK2_DAT.substring(28 - 1, 28 + 3 - 1));
            CEP.TRC(SCCGWA, WS_CVN1);
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            CEP.TRC(SCCGWA, DCCPCDCK.TRK2_DAT.substring(31 - 1, 31 + 3 - 1));
            CEP.TRC(SCCGWA, WS_CVN2);
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW.substring(17 - 1, 17 + 1 - 1));
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            if (!DCCPCDCK.TRK2_DAT.substring(28 - 1, 28 + 3 - 1).equalsIgnoreCase(WS_CVN1)) {
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCPCDCK.CARD_NO;
                DCCUPWER.PWPASS_FLG = 'V';
                S000_PASSWORD_SPCDEAL();
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CVN_NOT_MATCH);
            }
        }
        if (DCCPCDCK.TRK3_DAT.trim().length() > 0) {
        }
    }
    public void S000_CHECK_CVN() throws IOException,SQLException,Exception {
        R000_GET_CARD_DT();
        IBS.init(SCCGWA, SCCHMPW);
        CEP.TRC(SCCGWA, DCCPCDCK.CARD_NO);
        SCCHMPW.INP_DATA.SERV_ID = "E133";
        SCCHMPW.INP_DATA.NEW_AC = DCCPCDCK.CARD_NO;
        SCCHMPW.INP_DATA.OLD_AC = DCCPCDCK.CARD_NO;
        WS_CRT_DT = DCRCDORD.CRT_DT;
        JIBS_tmp_str[0] = "" + WS_CRT_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        SCCHMPW.INP_DATA.CARD_DATE = JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1);
        WS_POINT = (short) (WS_CARD_LEN + 74);
        CEP.TRC(SCCGWA, WS_POINT);
        if (DCCPCDCK.TRK3_DAT.trim().length() > 0) {
            if (DCCPCDCK.TRK3_DAT == null) DCCPCDCK.TRK3_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK3_DAT.length();
            for (int i=0;i<107-JIBS_tmp_int;i++) DCCPCDCK.TRK3_DAT += " ";
            SCCHMPW.INP_DATA.SVR_ID = DCCPCDCK.TRK3_DAT.substring(WS_POINT - 1, WS_POINT + 3 - 1);
        }
        WS_POINT = (short) (WS_CARD_LEN + 6);
        CEP.TRC(SCCGWA, WS_POINT);
        if (DCCPCDCK.TRK2_DAT.trim().length() > 0) {
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            SCCHMPW.INP_DATA.SVR_ID = DCCPCDCK.TRK2_DAT.substring(WS_POINT - 1, WS_POINT + 3 - 1);
        }
        CEP.TRC(SCCGWA, DCCUCINF.ADSC_TYPE);
        SCCHMPW.INP_DATA.AC_FLG = '0';
        S000_CALL_SCZHMPW();
        WS_POINT = (short) (WS_CARD_LEN + 9);
        CEP.TRC(SCCGWA, WS_POINT);
        if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
        JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
        CEP.TRC(SCCGWA, DCCPCDCK.TRK2_DAT.substring(WS_POINT - 1, WS_POINT + 3 - 1));
        if (SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW == null) SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW = "";
        JIBS_tmp_int = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW += " ";
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW.substring(0, 3));
        if (DCCPCDCK.TRK2_DAT.trim().length() > 0) {
            if (DCCPCDCK.TRK2_DAT == null) DCCPCDCK.TRK2_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK2_DAT.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) DCCPCDCK.TRK2_DAT += " ";
            if (SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW == null) SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW = "";
            JIBS_tmp_int = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW += " ";
            if (!DCCPCDCK.TRK2_DAT.substring(WS_POINT - 1, WS_POINT + 3 - 1).equalsIgnoreCase(SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW.substring(0, 3))) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CVN_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCPCDCK.TRK3_DAT.trim().length() > 0 
            && (WS_IC_FLG != 'Y') 
            && (DCCPCDCK.CHK_S_TRK2_FLG != 'Y')) {
            WS_POINT = (short) (WS_CARD_LEN + 82);
            CEP.TRC(SCCGWA, WS_POINT);
            if (DCCPCDCK.TRK3_DAT == null) DCCPCDCK.TRK3_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK3_DAT.length();
            for (int i=0;i<107-JIBS_tmp_int;i++) DCCPCDCK.TRK3_DAT += " ";
            CEP.TRC(SCCGWA, DCCPCDCK.TRK3_DAT.substring(WS_POINT - 1, WS_POINT + 3 - 1));
            if (DCCPCDCK.TRK3_DAT == null) DCCPCDCK.TRK3_DAT = "";
            JIBS_tmp_int = DCCPCDCK.TRK3_DAT.length();
            for (int i=0;i<107-JIBS_tmp_int;i++) DCCPCDCK.TRK3_DAT += " ";
            if (SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW == null) SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW = "";
            JIBS_tmp_int = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW += " ";
            if (!DCCPCDCK.TRK3_DAT.substring(WS_POINT - 1, WS_POINT + 3 - 1).equalsIgnoreCase(SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW.substring(0, 3))) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CVN_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CHECK_CVN2() throws IOException,SQLException,Exception {
        R000_GET_CARD_DT();
        WS_EXPIRE_DT = DCRCDORD.CERT_EXP_DATE;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDORD.CARD_PROD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        } else {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRCDORD.CARD_PROD;
        }
        S000_CALL_DCZUPRCD();
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
            CEP.TRC(SCCGWA, DCRPRDPR);
        }
        WS_SERVE_CD = DCRPRDPR.DATA_TXT.SR_RC_CD;
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '9';
        if (DCCUCINF.MOVE_FLG == 'Y') {
            SCCTSSC.COMM_AREA_9.9_KEYMODEL_ID = "CBS.OLD.cvk2";
        } else {
            if (DCRCDORD.SF_FLG == '1') {
                SCCTSSC.COMM_AREA_9.9_KEYMODEL_ID = "CBS.OLD.cvk2";
            } else {
                if (DCRCDORD.SF_FLG == '3') {
                    SCCTSSC.COMM_AREA_9.9_KEYMODEL_ID = "CBS.NCB.cvk2";
                }
            }
        }
        SCCTSSC.COMM_AREA_9.9_ACCNO = DCCPCDCK.CARD_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_9);
        JIBS_tmp_str[0] = "" + WS_EXPIRE_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        SCCTSSC.COMM_AREA_9.9_EXPIRES = JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_9);
        SCCTSSC.COMM_AREA_9.9_SERVICE_CODE = WS_SERVE_CD;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_9);
        SCCTSSC.COMM_AREA_9.9_CVV = DCCPCDCK.CARD_CVN2;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_9);
        S000_CALL_SCZTSSC();
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_9.9_O_FLG);
        if (SCCTSSC.COMM_AREA_9.9_O_FLG == 'F') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CVN_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_GET_CARD_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCPCDCK.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
        T000_STARTBR_DCTCDORD_FIRST();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCRCDORD.CRT_DT);
    }
    public void R000_GET_CARD_LENGTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        if (DCCPCDCK.CARD_NO == null) DCCPCDCK.CARD_NO = "";
        JIBS_tmp_int = DCCPCDCK.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCK.CARD_NO += " ";
        if (DCCPCDCK.CARD_NO.substring(0, 4).equalsIgnoreCase("9111")) {
            if (DCCPCDCK.CARD_NO == null) DCCPCDCK.CARD_NO = "";
            JIBS_tmp_int = DCCPCDCK.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCK.CARD_NO += " ";
            DCRBINPM.KEY.BIN = DCCPCDCK.CARD_NO.substring(0, 4);
        } else {
            if (DCCPCDCK.CARD_NO == null) DCCPCDCK.CARD_NO = "";
            JIBS_tmp_int = DCCPCDCK.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCK.CARD_NO += " ";
            DCRBINPM.KEY.BIN = DCCPCDCK.CARD_NO.substring(0, 6);
        }
        if (DCCPCDCK.CARD_NO == null) DCCPCDCK.CARD_NO = "";
        JIBS_tmp_int = DCCPCDCK.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCK.CARD_NO += " ";
        if (DCCPCDCK.CARD_NO.substring(0, 6).equalsIgnoreCase("623265")) {
            if (DCCPCDCK.CARD_NO == null) DCCPCDCK.CARD_NO = "";
            JIBS_tmp_int = DCCPCDCK.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCK.CARD_NO += " ";
            DCRBINPM.KEY.BIN = DCCPCDCK.CARD_NO.substring(0, 8);
        }
        T000_READ_DCTBINPM();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCRBINPM.CARD_LEN);
    }
    public void S000_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCPCDCK.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT();
        WS_CARD_NO_TEMP = DCCPCDCK.CARD_NO;
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("1203010101") 
            && DCRCDDAT.TRAN_PIN_DAT.trim().length() == 0) {
            WS_FIND_FLG = 'N';
            while (WS_FIND_FLG != 'Y') {
                IBS.init(SCCGWA, CICQCHDC);
                CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO_TEMP;
                CICQCHDC.DATA.N_ENTY_TYP = '2';
                CICQCHDC.FUNC = 'O';
                S000_CALL_CIZQCHDC();
                CEP.TRC(SCCGWA, CICQCHDC.DATA.O_AGR_NO);
                if (CICQCHDC.DATA.O_AGR_NO.trim().length() == 0) {
                    WS_FIND_FLG = 'Y';
                } else {
                    WS_CARD_NO_TEMP = CICQCHDC.DATA.O_AGR_NO;
                    IBS.init(SCCGWA, DCRCDDAT);
                    DCRCDDAT.KEY.CARD_NO = WS_CARD_NO_TEMP;
                    T000_READ_DCTCDDAT();
                    if (DCRCDDAT.TRAN_PIN_DAT.trim().length() > 0) {
                        WS_FIND_FLG = 'Y';
                    }
                }
            }
            IBS.init(SCCGWA, SCCTSSC);
            SCCTSSC.FUNC = 'A';
            SCCTSSC.COMM_AREA_A.A_SRC_ACCNO = DCCPCDCK.CARD_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            SCCTSSC.COMM_AREA_A.A_DS_ACCNO = WS_CARD_NO_TEMP;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            SCCTSSC.COMM_AREA_A.A_SRC_PINBLOCK = DCCPCDCK.CARD_PSW;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            SCCTSSC.COMM_AREA_A.A_WPNUM = 1;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            SCCTSSC.COMM_AREA_A.A_WPLEN = 6;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            if (WS_CARD_NO_TEMP == null) WS_CARD_NO_TEMP = "";
            JIBS_tmp_int = WS_CARD_NO_TEMP.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARD_NO_TEMP += " ";
            SCCTSSC.COMM_AREA_A.A_WPSTR = WS_CARD_NO_TEMP.substring(14 - 1, 14 + 6 - 1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            S000_CALL_SCZTSSC();
            CEP.TRC(SCCGWA, DCRCDDAT.TRAN_PIN_DAT);
            CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET);
            if (SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET.equalsIgnoreCase(DCRCDDAT.TRAN_PIN_DAT)) {
                IBS.init(SCCGWA, SCCTSSC);
                SCCTSSC.FUNC = 'A';
                SCCTSSC.COMM_AREA_A.A_SRC_ACCNO = DCCPCDCK.CARD_NO;
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
                SCCTSSC.COMM_AREA_A.A_DS_ACCNO = DCCPCDCK.CARD_NO;
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
                SCCTSSC.COMM_AREA_A.A_SRC_PINBLOCK = DCCPCDCK.CARD_PSW;
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
                SCCTSSC.COMM_AREA_A.A_WPNUM = 1;
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
                SCCTSSC.COMM_AREA_A.A_WPLEN = 6;
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
                if (DCCPCDCK.CARD_NO == null) DCCPCDCK.CARD_NO = "";
                JIBS_tmp_int = DCCPCDCK.CARD_NO.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCPCDCK.CARD_NO += " ";
                SCCTSSC.COMM_AREA_A.A_WPSTR = DCCPCDCK.CARD_NO.substring(14 - 1, 14 + 6 - 1);
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
                S000_CALL_SCZTSSC();
                CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET);
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCCPCDCK.CARD_NO;
                CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
                T000_READ_DCTCDDAT_UPDATE();
                DCRCDDAT.TRAN_PIN_DAT = SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET;
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTCDDAT();
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCPCDCK.CARD_NO;
                DCCUPWER.PWPASS_FLG = 'Y';
                S000_PASSWORD_SPCDEAL();
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            } else {
                WS_PSW_CHECK_FLG = 'N';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCPCDCK.CARD_NO;
                DCCUPWER.TOR_SYSID = SCCGWA.COMM_AREA.TOR_SYSID;
                DCCUPWER.PWPASS_FLG = 'N';
                S000_PASSWORD_SPCDEAL();
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            }
        } else {
            WS_PSW_FLG = DCRCDDAT.PSW_TYP;
            IBS.init(SCCGWA, SCCTSSC);
            SCCTSSC.FUNC = '1';
            if (WS_PSW_FLG == 'O') {
                SCCTSSC.COMM_AREA_1.1_DST_ALG_MODE = '0';
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
            } else {
                SCCTSSC.COMM_AREA_1.1_DST_ALG_MODE = '1';
                SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
            }
            SCCTSSC.COMM_AREA_1.1_SRC_ACCOUNT = DCCPCDCK.CARD_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
            SCCTSSC.COMM_AREA_1.1_SRC_PIN_BLOCK = DCCPCDCK.CARD_PSW;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
            SCCTSSC.COMM_AREA_1.1_ENC_MODE = "01";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
            S000_CALL_SCZTSSC();
            if (WS_PSW_FLG == 'O') {
                WS_OUT_PW = SCCTSSC.COMM_AREA_1.1_O_OLD_PIN;
            } else {
                WS_OUT_PW = SCCTSSC.COMM_AREA_1.1_O_NEW_PIN;
            }
            CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_1.1_O_OLD_PIN);
            CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_1.1_O_NEW_PIN);
            CEP.TRC(SCCGWA, DCRCDDAT.TRAN_PIN_DAT);
            if (WS_OUT_PW.equalsIgnoreCase(DCRCDDAT.TRAN_PIN_DAT)) {
                if (WS_PSW_FLG == 'O') {
                    IBS.init(SCCGWA, DCRCDDAT);
                    DCRCDDAT.KEY.CARD_NO = DCCPCDCK.CARD_NO;
                    T000_READ_DCTCDDAT_UPDATE();
                    DCRCDDAT.PSW_TYP = 'N';
                    DCRCDDAT.TRAN_PIN_DAT = SCCTSSC.COMM_AREA_1.1_O_NEW_PIN;
                    DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DCTCDDAT();
                }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCPCDCK.CARD_NO;
                DCCUPWER.PWPASS_FLG = 'Y';
                S000_PASSWORD_SPCDEAL();
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            } else {
                WS_PSW_CHECK_FLG = 'N';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCPCDCK.CARD_NO;
                DCCUPWER.TOR_SYSID = SCCGWA.COMM_AREA.TOR_SYSID;
                DCCUPWER.PWPASS_FLG = 'N';
                S000_PASSWORD_SPCDEAL();
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            }
        }
    }
    public void S000_CHECK_PASSWORD_SPCBINDL() throws IOException,SQLException,Exception {
        if (DCCPCDCK.TRK3_DAT.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_CHECK_PSW;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCHMPW);
        if (DCCPCDCK.OLD_CARD_NO.trim().length() == 0) {
            SCCHMPW.INP_DATA.OLD_AC = DCCPCDCK.CARD_NO;
        } else {
            SCCHMPW.INP_DATA.OLD_AC = DCCPCDCK.OLD_CARD_NO;
        }
        SCCHMPW.INP_DATA.NEW_AC = DCCPCDCK.CARD_NO;
        SCCHMPW.INP_DATA.PIN_DA = DCCPCDCK.CARD_PSW;
        if (DCCUCINF.ADSC_TYPE == 'C') {
            SCCHMPW.INP_DATA.AC_FLG = '1';
        } else {
            SCCHMPW.INP_DATA.AC_FLG = '0';
        }
        SCCHMPW.INP_DATA.SERV_ID = "E141";
        S000_CALL_SCZHMPW();
        IBS.CPY2CLS(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW, WS_PW_SPCBINDL);
        if (DCCPCDCK.TRK3_DAT == null) DCCPCDCK.TRK3_DAT = "";
        JIBS_tmp_int = DCCPCDCK.TRK3_DAT.length();
        for (int i=0;i<107-JIBS_tmp_int;i++) DCCPCDCK.TRK3_DAT += " ";
        IBS.CPY2CLS(SCCGWA, DCCPCDCK.TRK3_DAT.substring(43 - 1, 43 + 6 - 1), WS_TRK_PW_SPCBINDL);
        WS_PW_DIVIDE_R1 = ( WS_PW_SPCBINDL.PW_SPCBINDL_FN5.PW_SPCBINDL_N1 * 6 ) + ( WS_PW_SPCBINDL.PW_SPCBINDL_FN5.PW_SPCBINDL_N2 * 5 ) + ( WS_PW_SPCBINDL.PW_SPCBINDL_FN5.PW_SPCBINDL_N3 * 4 ) + ( WS_PW_SPCBINDL.PW_SPCBINDL_FN5.PW_SPCBINDL_N4 * 3 ) + ( WS_PW_SPCBINDL.PW_SPCBINDL_FN5.PW_SPCBINDL_N5 * 2 ) + ( WS_PW_SPCBINDL.PW_SPCBINDL_N6 );
        WS_PW_DIVIDE_REM1 = (short) (WS_PW_DIVIDE_R1 % 10);
        WS_PW_DIVIDE_R2 = (int) ((WS_PW_DIVIDE_R1 - WS_PW_DIVIDE_REM1) / 10);
        if (WS_PW_DIVIDE_REM1 != 0) {
            WS_PW_DIVIDE_REM1 = (short) (10 - WS_PW_DIVIDE_REM1);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_PW_SPCBINDL.PW_SPCBINDL_FN5);
        WS_PW_SPCBINDL_FN5 = Integer.parseInt(JIBS_tmp_str[0]);
        WS_PW_COMPARE = WS_PW_SPCBINDL_FN5 - WS_TRK_PW_SPCBINDL.TRK_PW_SPCBINDL_L5;
        if (WS_PW_COMPARE < 0) {
            WS_PW_COMPARE = WS_PW_COMPARE + 100000;
        }
        WS_PW_CHAR = "" + WS_PW_COMPARE;
        JIBS_tmp_int = WS_PW_CHAR.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) WS_PW_CHAR = "0" + WS_PW_CHAR;
        if (DCRCDDAT.TRAN_PIN_DAT == null) DCRCDDAT.TRAN_PIN_DAT = "";
        JIBS_tmp_int = DCRCDDAT.TRAN_PIN_DAT.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCRCDDAT.TRAN_PIN_DAT += " ";
        if (WS_PW_DIVIDE_REM1 == WS_TRK_PW_SPCBINDL.TRK_PW_SPCBINDL_F1 
            && DCRCDDAT.TRAN_PIN_DAT.substring(5 - 1, 5 + 5 - 1).equalsIgnoreCase(WS_PW_CHAR)) {
            WS_PSW_CHECK_FLG = 'Y';
            IBS.init(SCCGWA, SCCHMPW);
            if (DCCPCDCK.OLD_CARD_NO.trim().length() == 0) {
                SCCHMPW.INP_DATA.OLD_AC = DCCPCDCK.CARD_NO;
            } else {
                SCCHMPW.INP_DATA.OLD_AC = DCCPCDCK.OLD_CARD_NO;
            }
            SCCHMPW.INP_DATA.NEW_AC = DCCPCDCK.CARD_NO;
            SCCHMPW.INP_DATA.PIN_DA = DCCPCDCK.CARD_PSW;
            if (DCCUCINF.ADSC_TYPE == 'C') {
                SCCHMPW.INP_DATA.AC_FLG = '1';
            } else {
                SCCHMPW.INP_DATA.AC_FLG = '0';
            }
            SCCHMPW.INP_DATA.SERV_ID = "E142";
            S000_CALL_SCZHMPW();
            DCRCDDAT.KEY.CARD_NO = DCCPCDCK.CARD_NO;
            T000_READ_DCTCDDAT_UPDATE();
            DCRCDDAT.PIN_ERR_CNT = 0;
            DCRCDDAT.TRAN_PIN_DAT = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCDDAT();
        } else {
            WS_PSW_CHECK_FLG = 'N';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            IBS.init(SCCGWA, DCCUPWER);
            DCCUPWER.CARD_NO = DCCPCDCK.CARD_NO;
            DCCUPWER.TOR_SYSID = SCCGWA.COMM_AREA.TOR_SYSID;
            DCCUPWER.PWPASS_FLG = 'N';
            S000_PASSWORD_SPCDEAL();
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
        }
    }
    public void S001_CHECK_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCPCDCK.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT_UPDATE();
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.AC_DATE > DCRCDDAT.PIN_LCK_DT) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 3 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(3 + 1 - 1);
            DCRCDDAT.PIN_ERR_CNT = 0;
            DCRCDDAT.PIN_LCK_DT = 0;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCDDAT();
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCPCDCK.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT();
        WS_SF_FLG = DCRCDDAT.SF_FLG;
        if (DCCPCDCK.CARD_PSW.trim().length() > 0) {
            if (DCRCDDAT.TRAN_PIN_DAT.trim().length() == 0 
                && !DCRCDDAT.PROD_CD.equalsIgnoreCase("1203010101")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_SET_PSW;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.TRAN_PIN_DAT.trim().length() == 0 
                && DCRCDDAT.PROD_CD.equalsIgnoreCase("1203010101") 
                && (DCRCDDAT.CARD_STS == '2' 
                || DCRCDDAT.CARD_STS == '3')) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_SET_PSW;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PASSWD_LOSS;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCPCDCK.TRK2_DAT.trim().length() > 0 
            || DCCPCDCK.TRK3_DAT.trim().length() > 0) {
            if (DCRCDDAT.CARD_STS != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_PASSWORD_SPCDEAL() throws IOException,SQLException,Exception {
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "DCZUPWER";
        WS_STAR_COMM.STAR_DATA = DCCUPWER;
        IBS.START(SCCGWA, WS_STAR_COMM, false);
        CEP.TRC(SCCGWA, DCCUPWER.PSWLOCK_FLG);
        if (DCCUPWER.RC.RC_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "KKKKKK");
        CEP.TRC(SCCGWA, DCCUPWER.PSWLOCK_FLG);
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DCTCDORD_FIRST() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DCTCDDAT_UPDATE() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_STARTBR_DCTCDORD_FIRST() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO";
        DCTCDORD_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_CALL_SCZTSSC() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        if (SCCTSSC.RC != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ERROR_FROM_ENP;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        CIZQCHDC CIZQCHDC = new CIZQCHDC();
        CIZQCHDC.MP(SCCGWA, CICQCHDC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            SCCBINF.ERR_NAME = "BPZPRMM";
            SCCBINF.OTHER_INFO = "CALL BPZPRMM ERROR";
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD      ", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if ((SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("009040") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001024") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001014"))) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_NOT_SIX_LEN;
            S000_ERR_MSG_PROC();
        } else {
            if (SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001020")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IDCVN_NOT_MATCH;
                S000_ERR_MSG_PROC();
            } else {
                if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
                    WS_ERR_MSG = SCCHMPW.OUT_INFO.ERR_CODE;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
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
