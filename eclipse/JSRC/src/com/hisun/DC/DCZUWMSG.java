package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUWMSG {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCZTPCL = "SCZTPCL";
    String PGM_CIZUMSG = "CIZUMSG";
    long WS_JRN_NO = 0;
    char WS_ADSC_TYPE = ' ';
    String WS_ERR_MSG = " ";
    int WS_J = 0;
    String WS_CI_NO = " ";
    String WS_CI_CNM = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCRCPARM DCRCPARM = new DCRCPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCRWSPC SCCRWSPC = new SCCRWSPC();
    CICQACRI CICQACRI = new CICQACRI();
    CICUMSG CICUMSG = new CICUMSG();
    SCCGWA SCCGWA;
    DCCUWMSG DCCUWMSG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCMMSG SCCMMSG;
    public void MP(SCCGWA SCCGWA, DCCUWMSG DCCUWMSG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUWMSG = DCCUWMSG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUWMSG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCMMSG = (SCCMMSG) SCCGWA.COMM_AREA.SC_MMSG_PTR;
        CEP.TRC(SCCGWA, DCCUWMSG.IO_AREA.CARD_NO);
        CEP.TRC(SCCGWA, DCCUWMSG.IO_AREA.CI_NO);
        CEP.TRC(SCCGWA, DCCUWMSG.IO_AREA.PIN_FLG);
        if (DCCUWMSG.IO_AREA.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        B000_MERGE_MOBILE_MSG();
        if (pgmRtn) return;
    }
    public void B000_MERGE_MOBILE_MSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCUWMSG.IO_AREA.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_CI_NO.trim().length() > 0) {
            WS_CI_NO = CICQACRI.O_DATA.O_CI_NO;
        }
        WS_CI_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBS.init(SCCGWA, CICUMSG);
        CICUMSG.FUNC = 'A';
        CICUMSG.DATA[1-1].SERV_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICUMSG.DATA[1-1].AP_TYPE = SCCGWA.COMM_AREA.AP_MMO;
        if (SCCGWA.COMM_AREA.SYS_OPT == 'W') {
            CICUMSG.DATA[1-1].REQ_SYS = "030102";
            CICUMSG.DATA[1-1].AP_CODE = "IAS";
            if (CICUMSG.DATA[1-1].AP_CODE == null) CICUMSG.DATA[1-1].AP_CODE = "";
            JIBS_tmp_int = CICUMSG.DATA[1-1].AP_CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICUMSG.DATA[1-1].AP_CODE += " ";
            CICUMSG.DATA[1-1].AP_CODE = "IAS" + CICUMSG.DATA[1-1].AP_CODE.substring(3);
        } else {
            CICUMSG.DATA[1-1].REQ_SYS = "030101";
            CICUMSG.DATA[1-1].AP_CODE = "IBS";
            if (CICUMSG.DATA[1-1].AP_CODE == null) CICUMSG.DATA[1-1].AP_CODE = "";
            JIBS_tmp_int = CICUMSG.DATA[1-1].AP_CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICUMSG.DATA[1-1].AP_CODE += " ";
            CICUMSG.DATA[1-1].AP_CODE = "IBS" + CICUMSG.DATA[1-1].AP_CODE.substring(3);
        }
        CICUMSG.DATA[1-1].MSG.SYS_NO.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        if (SCCGWA.COMM_AREA.JRN_NO == 0) {
            S000_CALL_SCZGJRN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO = "0" + CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_NO;
        CICUMSG.DATA[1-1].MSG.SYS_NO.JRN_SEQ = 1;
        CICUMSG.DATA[1-1].CI_NO = WS_CI_NO;
        if (DCCUWMSG.IO_AREA.PIN_FLG == 'Y') {
            CICUMSG.DATA[1-1].AP_CODE = "P503";
            if (CICUMSG.DATA[1-1].AP_CODE == null) CICUMSG.DATA[1-1].AP_CODE = "";
            JIBS_tmp_int = CICUMSG.DATA[1-1].AP_CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICUMSG.DATA[1-1].AP_CODE += " ";
            CICUMSG.DATA[1-1].AP_CODE = CICUMSG.DATA[1-1].AP_CODE.substring(0, 4 - 1) + "P503" + CICUMSG.DATA[1-1].AP_CODE.substring(4 + 5 - 1);
        } else {
            CICUMSG.DATA[1-1].AP_CODE = "P502";
            if (CICUMSG.DATA[1-1].AP_CODE == null) CICUMSG.DATA[1-1].AP_CODE = "";
            JIBS_tmp_int = CICUMSG.DATA[1-1].AP_CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) CICUMSG.DATA[1-1].AP_CODE += " ";
            CICUMSG.DATA[1-1].AP_CODE = CICUMSG.DATA[1-1].AP_CODE.substring(0, 4 - 1) + "P502" + CICUMSG.DATA[1-1].AP_CODE.substring(4 + 5 - 1);
        }
        CEP.TRC(SCCGWA, CICUMSG);
        S000_CALL_CIZUMSG();
        if (pgmRtn) return;
        if (CICUMSG.RC.RC_CODE != 0 
            || SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            SCCMMSG.IN_CNT = 0;
            SCCGWA.COMM_AREA.EXCP_FLG = ' ';
        } else {
            B100_WAKE();
            if (pgmRtn) return;
        }
    }
    public void B100_WAKE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
        SCCTPCL.SERV_AREA.TRANS_FLG = '0';
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        if (SCCGWA.COMM_AREA.EXCP_FLG != 'Y') {
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 10700;
            SCCTPCL.INP_AREA.BD_SMS_INFO.BD_BSN_CD = CICUMSG.DATA[1-1].AP_CODE;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            SCCTPCL.INP_AREA.BD_SMS_INFO.BD_ECIF_ID = CICUMSG.DATA[1-1].CI_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            SCCTPCL.INP_AREA.BD_SMS_INFO.BD_MSG_INF = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[1-1].MSG);
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            CEP.TRC(SCCGWA, "S000-CALL-SCZTPCL:");
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.EXCP_FLG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            SCCGWA.COMM_AREA.EXCP_FLG = ' ';
        } else {
            CICUMSG.FUNC = 'M';
            S000_CALL_CIZUMSG();
            if (pgmRtn) return;
            if (CICUMSG.RC.RC_CODE != 0 
                || SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
                SCCGWA.COMM_AREA.EXCP_FLG = ' ';
            }
        }
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZUMSG() throws IOException,SQLException,Exception {
        CIZUMSG CIZUMSG = new CIZUMSG();
        CIZUMSG.MP(SCCGWA, CICUMSG);
        CEP.TRC(SCCGWA, CICUMSG.RC.RC_CODE);
        if (CICUMSG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICUMSG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        SCZTPCL SCZTPCL = new SCZTPCL();
        SCZTPCL.MP(SCCGWA, SCCTPCL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, DCCUWMSG.O_AREA.MSG_ID);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUWMSG.O_AREA.MSG_ID.RC != 0) {
            CEP.TRC(SCCGWA, "DCCUWMSG=");
            CEP.TRC(SCCGWA, DCCUWMSG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
