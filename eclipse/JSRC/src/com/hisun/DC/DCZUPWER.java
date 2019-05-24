package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUPWER {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String PGM_CIZUMSG = "CIZUMSG";
    String PGM_SCZTPCL = "SCZTPCL";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    long WS_JRN_NO = 0;
    char WS_ADSC_TYPE = ' ';
    String WS_ERR_MSG = " ";
    int WS_PIN_ERR_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCRCPARM DCRCPARM = new DCRCPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCRWSPC SCCRWSPC = new SCCRWSPC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICQACRI CICQACRI = new CICQACRI();
    DCCUWMSG DCCUWMSG = new DCCUWMSG();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CICUMSG CICUMSG = new CICUMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    DCCUPWER DCCUPWER;
    public void MP(SCCGWA SCCGWA, DCCUPWER DCCUPWER) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUPWER = DCCUPWER;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PIN_ERR_CNT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, "999999999999");
        if ((DCCUPWER.PWPASS_FLG == 'N' 
            && WS_PIN_ERR_CNT >= DCRCPARM.DATA_TXT.PSW_LOCK_NUM) 
            || DCCUPWER.PWPASS_FLG == 'V') {
            S000_SEND_MESSAGE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DCZUPWER return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        WS_PIN_ERR_CNT = 0;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (DCCUPWER.CARD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCUPWER.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUPWER.PWPASS_FLG);
        if (DCCUPWER.PWPASS_FLG == 'Y') {
            B010_PWPASS_PROC();
            if (pgmRtn) return;
        } else if (DCCUPWER.PWPASS_FLG == 'N') {
            B020_PWNOPASS_PROC();
            if (pgmRtn) return;
        } else if (DCCUPWER.PWPASS_FLG == 'V') {
            B030_CVN_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUPWER.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_PWPASS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUPWER.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.PIN_ERR_CNT != 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCUPWER.CARD_NO;
            T000_READ_DCTCDDAT_UPDATE();
            if (pgmRtn) return;
            DCRCDDAT.PIN_ERR_CNT = 0;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        }
    }
    public void B020_PWNOPASS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCPARM);
        IBS.init(SCCGWA, BPCPRMR);
        DCRCPARM.KEY.TYP = "DCPRM";
        BPCPRMR.FUNC = ' ';
        DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
        IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        BPCPRMR.DAT_PTR = DCRCPARM;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCUPWER.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUPWER.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT_UPDATE();
        if (pgmRtn) return;
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
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        DCCUPRCD.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR);
        WS_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        CEP.TRC(SCCGWA, "22222222222");
        CEP.TRC(SCCGWA, DCRCDDAT.PIN_LCK_DT);
        CEP.TRC(SCCGWA, DCRCDDAT.PIN_ERR_CNT);
        if ((SCCGWA.COMM_AREA.AC_DATE > DCRCDDAT.PIN_LCK_DT) 
            && (DCRCDDAT.PIN_ERR_CNT > 0)) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCUPWER.CARD_NO;
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
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
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCUPWER.CARD_NO;
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
        }
        WS_PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT;
        WS_PIN_ERR_CNT = WS_PIN_ERR_CNT + 1;
        CEP.TRC(SCCGWA, "PIN ERROR --------");
        CEP.TRC(SCCGWA, WS_PIN_ERR_CNT);
        CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.PSW_LOCK_NUM);
        if (WS_PIN_ERR_CNT >= DCRCPARM.DATA_TXT.PSW_LOCK_NUM) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
        }
        DCRCDDAT.PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT + 1;
        DCRCDDAT.PIN_LCK_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void S000_SEND_MESSAGE() throws IOException,SQLException,Exception {
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCRCDDAT.KEY.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICUMSG);
        CICUMSG.FUNC = 'A';
        CICUMSG.DATA[1-1].SERV_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICUMSG.DATA[1-1].MSG.SYS_NO.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CICUMSG.DATA[1-1].REQ_SYS = SCCGWA.COMM_AREA.REQ_SYSTEM;
        CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO = "0" + CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO;
        CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_SEQ = 1;
        if (CICQACRI.O_DATA.O_CI_NO.trim().length() > 0) {
            CICUMSG.DATA[1-1].CI_NO = CICQACRI.O_DATA.O_CI_NO;
        }
        if (DCCUPWER.PWPASS_FLG == 'N') {
            CICUMSG.DATA[1-1].AP_CODE = "371";
        } else {
            CICUMSG.DATA[1-1].AP_CODE = "486";
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) CICUMSG.DATA[1-1].MSG.YEAR = 0;
        else CICUMSG.DATA[1-1].MSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) CICUMSG.DATA[1-1].MSG.MONTH = 0;
        else CICUMSG.DATA[1-1].MSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) CICUMSG.DATA[1-1].MSG.DAY = 0;
        else CICUMSG.DATA[1-1].MSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        if (DCRCDDAT.KEY.CARD_NO == null) DCRCDDAT.KEY.CARD_NO = "";
        JIBS_tmp_int = DCRCDDAT.KEY.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCRCDDAT.KEY.CARD_NO += " ";
        CICUMSG.DATA[1-1].MSG.ACL4 = DCRCDDAT.KEY.CARD_NO.substring(16 - 1, 16 + 4 - 1);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) CICUMSG.DATA[1-1].MSG.HOUR = 0;
        else CICUMSG.DATA[1-1].MSG.HOUR = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) CICUMSG.DATA[1-1].MSG.MINUTE = 0;
        else CICUMSG.DATA[1-1].MSG.MINUTE = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) CICUMSG.DATA[1-1].MSG.SECOND = 0;
        else CICUMSG.DATA[1-1].MSG.SECOND = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        IBS.CALLCPN(SCCGWA, "CI-MAIN-MMSG", CICUMSG);
        if (CICUMSG.RC.RC_CODE != 0 
            || SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            SCCGWA.COMM_AREA.EXCP_FLG = ' ';
        } else {
            B100_WAKE();
            if (pgmRtn) return;
        }
    }
    public void B100_WAKE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
        SCCTPCL.SERV_AREA.SERV_CODE = "6001200000701";
        SCCTPCL.SERV_AREA.TRANS_FLG = '0';
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 10700;
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_SMS_TYP = '2';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_TPL_FLG = '1';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_TPL_TYP = '1';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_TPL_NO = CICUMSG.DATA[1-1].AP_CODE;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_SMS_VAL = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[1-1].MSG);
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        CEP.TRC(SCCGWA, CICUMSG.DATA[1-1].MSG);
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_SIGN_FLG = '0';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        SCCTPCL.INP_AREA.SN_SMS_NOTICE.SN_TEL_NO = CICUMSG.DATA[1-1].MSG.TEL_NO;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_SMS_INFO.BD_SEND_TO_IDS);
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.EXCP_FLG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            SCCGWA.COMM_AREA.EXCP_FLG = ' ';
        } else {
            CICUMSG.FUNC = 'M';
            IBS.CALLCPN(SCCGWA, "CI-MAIN-MMSG", CICUMSG);
            if (CICUMSG.RC.RC_CODE != 0 
                || SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
                SCCGWA.COMM_AREA.EXCP_FLG = ' ';
            }
        }
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void B030_CVN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUPWER.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT_UPDATE();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 8 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(8 + 1 - 1);
        DCRCDDAT.PIN_LCK_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUWMSG);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCCUPWER.CARD_NO);
        CEP.TRC(SCCGWA, "21231321321");
        CEP.TRC(SCCGWA, WS_ADSC_TYPE);
        CEP.TRC(SCCGWA, DCCUPWER.CARD_NO);
        DCCUWMSG.IO_AREA.CARD_NO = DCCUPWER.CARD_NO;
        if (DCCUPWER.PWPASS_FLG == 'N') {
            CEP.TRC(SCCGWA, WS_PIN_ERR_CNT);
            CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.PSW_LOCK_NUM);
            if (WS_PIN_ERR_CNT >= DCRCPARM.DATA_TXT.PSW_LOCK_NUM) {
                DCCUWMSG.IO_AREA.PIN_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "333333333333333333333333333333333");
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCUPWER.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO, ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, JOIN_CUS_FLG, ANNUAL_FEE_FREE, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, MOVE_FLG, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUPWER.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT_UPDATE() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO, ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, JOIN_CUS_FLG, ANNUAL_FEE_FREE, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, MOVE_FLG, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUPWER.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, JOIN_CUS_FLG, ANNUAL_FEE_FREE, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, MOVE_FLG, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDDAT_RD.where = "CARD_NO = :DCRCDDAT.KEY.CARD_NO";
        IBS.REWRITE(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
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
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_SCOWRTTDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCOWRTTDQ-WRTQ", SCCWRMSG);
    }
    public void S000_CALL_DCZUWMSG() throws IOException,SQLException,Exception {
        DCZUWMSG DCZUWMSG = new DCZUWMSG();
        DCZUWMSG.MP(SCCGWA, DCCUWMSG);
        CEP.TRC(SCCGWA, DCCUWMSG.O_AREA.MSG_ID.RC);
        if (DCCUWMSG.O_AREA.MSG_ID.RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUWMSG.O_AREA.MSG_ID);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUPWER.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUPWER=");
            CEP.TRC(SCCGWA, DCCUPWER);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
