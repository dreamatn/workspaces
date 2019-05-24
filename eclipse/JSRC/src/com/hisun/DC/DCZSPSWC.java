package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPSWC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "DC112";
    String K_TBL_CDDAT = "DCTCDDAT";
    SCCSTAR SCCSTAR = new SCCSTAR();
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_CARD_SEQNO = 0;
    String WS_CARDNO0 = " ";
    char WS_ADSC_TYPE = ' ';
    String WS_TOR_SYSID = " ";
    char WS_JRN_IN_USE = ' ';
    char WS_PSW_TYP = ' ';
    String WS_TRAN_PIN_DAT = " ";
    int WS_PIN_ERR_CNT = 0;
    DCZSPSWC_WS_OUTPUT WS_OUTPUT = new DCZSPSWC_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
    char WS_ARQC_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHCGET DCCHCGET = new DCCHCGET();
    DCCHCGET DCCOCGET = new DCCHCGET();
    DCCHCGET DCCNCGET = new DCCHCGET();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRI CICQACRI = new CICQACRI();
    DCCUPWER DCCUPWER = new DCCUPWER();
    DCRCPARM DCRCPARM = new DCRCPARM();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    CICCUST CICCUST = new CICCUST();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    DCCSARQC DCCSARQC = new DCCSARQC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPSWC DCCSPSWC;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCSPSWC DCCSPSWC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPSWC = DCCSPSWC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPSWC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_MMO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DCCSPSWC.ID_TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TOR_SYSID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_IN_USE);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_PASSWD();
        if (pgmRtn) return;
        B030_CHANGE_PASSWD();
        if (pgmRtn) return;
        if (WS_ARQC_FLAG == 'Y') {
            B035_CHECK_ARQC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B055_ADD_AGENT_INFO();
            if (pgmRtn) return;
        }
        B040_HISTORY_PROCESS();
        if (pgmRtn) return;
        B050_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT BEG -----");
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_NO);
        CEP.TRC(SCCGWA, DCCSPSWC.ID_TYP);
        CEP.TRC(SCCGWA, DCCSPSWC.ID_NO);
        CEP.TRC(SCCGWA, DCCSPSWC.CI_NM);
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_OLD_PASSWD);
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_PASSWD1);
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_PASSWD2);
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCSPSWC.ARQC);
        CEP.TRC(SCCGWA, DCCSPSWC.ARQC_DATA);
        CEP.TRC(SCCGWA, DCCSPSWC.ISSUE_DATA);
        CEP.TRC(SCCGWA, DCCSPSWC.VERIFY_RLT);
        CEP.TRC(SCCGWA, "INPUT END -----");
        if (DCCSPSWC.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSARQC.CARD_ARQC_DATA.trim().length() > 0) {
            WS_ARQC_FLAG = 'Y';
        }
        if (WS_ARQC_FLAG == 'Y') {
            if (DCCSARQC.CARD_SEQ == ' ') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_SEQ);
            }
            if (DCCSARQC.CARD_ARQC.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ARQC);
            }
            if (DCCSARQC.ISSUE_DATA.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ISSU_DATA);
            }
            if (DCCSARQC.VERIFY_RLT.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_VERF_RLT);
            }
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSPSWC.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PSW_TYP = DCRCDDAT.PSW_TYP;
        WS_TRAN_PIN_DAT = DCRCDDAT.TRAN_PIN_DAT;
        if (DCCSPSWC.ID_TYP.trim().length() == 0 
            && DCCSPSWC.ID_NO.trim().length() == 0 
            && DCCSPSWC.CI_NM.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            DCCSPSWC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            DCCSPSWC.ID_NO = CICCUST.O_DATA.O_ID_NO;
            DCCSPSWC.CI_NM = CICCUST.O_DATA.O_CI_NM;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
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
        if (DCRCDDAT.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PASSWD_LOSS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        WS_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_TOR_SYSID = SCCGWA.COMM_AREA.TOR_SYSID;
        WS_JRN_IN_USE = SCCGWA.COMM_AREA.JRN_IN_USE;
        if (DCRPRDPR.DATA_TXT.PSW_FLG != 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUPPORT_PSW_TRT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPSWC.CARD_PASSWD1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_PASSWD_MODIFIED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DCCSPSWC.CARD_PASSWD1.equalsIgnoreCase(DCCSPSWC.CARD_PASSWD2)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SAME_TX_PASSWD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPSWC.CARD_PASSWD1.trim().length() > 0 
            && DCCSPSWC.CARD_OLD_PASSWD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NEED_OLD_TX_PASSWD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DCCUCDLP.CARD_NO = DCCSPSWC.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_PASSWD() throws IOException,SQLException,Exception {
        if (DCCSPSWC.FUNC == 'T') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_PSW = DCCSPSWC.CARD_OLD_PASSWD;
            DCCPCDCK.CARD_NO = DCCSPSWC.CARD_NO;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (DCCSPSWC.FUNC == 'I') {
            IBS.init(SCCGWA, DCCUPSWM);
            DCCUPSWM.FUNC = 'C';
            DCCUPSWM.PSW_TYP = 'Q';
            DCCUPSWM.CARD_PSW_OLD = DCCSPSWC.CARD_OLD_PASSWD;
            S000_CALL_DCZUPSWM();
            if (pgmRtn) return;
        }
    }
    public void B030_CHANGE_PASSWD() throws IOException,SQLException,Exception {
        if (DCCSPSWC.FUNC == 'T') {
            IBS.init(SCCGWA, DCCUPSWM);
            DCCUPSWM.FUNC = 'R';
            DCCUPSWM.OLD_AGR_NO = DCCSPSWC.CARD_NO;
            DCCUPSWM.AGR_NO = DCCSPSWC.CARD_NO;
            DCCUPSWM.PSW_TYP = 'T';
            DCCUPSWM.CARD_PSW_NEW = DCCSPSWC.CARD_PASSWD1;
            DCCUPSWM.ID_TYP = DCCSPSWC.ID_TYP;
            DCCUPSWM.ID_NO = DCCSPSWC.ID_NO;
            DCCUPSWM.CI_NM = DCCSPSWC.CI_NM;
            if (DCCSPSWC.CARD_NO == null) DCCSPSWC.CARD_NO = "";
            JIBS_tmp_int = DCCSPSWC.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSPSWC.CARD_NO += " ";
            DCCUPSWM.AGR_NO_6 = DCCSPSWC.CARD_NO.substring(14 - 1, 14 + 6 - 1);
            S000_CALL_DCZUPSWM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUPSWM.O_NEW_PSW);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSPSWC.CARD_NO;
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
            DCRCDDAT.TRAN_PIN_DAT = DCCUPSWM.O_NEW_PSW;
            DCRCDDAT.PIN_ERR_CNT = 0;
            DCRCDDAT.PIN_LCK_DT = 0;
            DCRCDDAT.PSW_TYP = 'N';
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        }
        if (DCCSPSWC.FUNC == 'I') {
            IBS.init(SCCGWA, DCCUPSWM);
            DCCUPSWM.FUNC = 'M';
            DCCUPSWM.PSW_TYP = 'Q';
            DCCUPSWM.CARD_PSW_OLD = DCCSPSWC.CARD_OLD_PASSWD;
            DCCUPSWM.CARD_PSW_NEW = DCCSPSWC.CARD_PASSWD1;
            S000_CALL_DCZUPSWM();
            if (pgmRtn) return;
        }
    }
    public void B035_CHECK_ARQC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_NO);
        CEP.TRC(SCCGWA, DCCSPSWC.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCSPSWC.ARQC);
        CEP.TRC(SCCGWA, DCCSPSWC.ARQC_DATA);
        CEP.TRC(SCCGWA, DCCSPSWC.ISSUE_DATA);
        CEP.TRC(SCCGWA, DCCSPSWC.VERIFY_RLT);
        IBS.init(SCCGWA, DCCSARQC);
        DCCSARQC.CARD_NO = DCCSPSWC.CARD_NO;
        DCCSARQC.CARD_SEQ = DCCSPSWC.CARD_SEQ;
        DCCSARQC.CARD_ARQC = DCCSPSWC.ARQC;
        DCCSARQC.CARD_ARQC_DATA = DCCSPSWC.ARQC_DATA;
        DCCSARQC.ISSUE_DATA = DCCSPSWC.ISSUE_DATA;
        DCCSARQC.VERIFY_RLT = DCCSPSWC.VERIFY_RLT;
        S000_CALL_DCZSARQC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSARQC.CARD_ARPC);
    }
    public void B040_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOCGET);
        IBS.init(SCCGWA, DCCNCGET);
        DCCOCGET.CARD_NO = DCCSPSWC.CARD_NO;
        DCCOCGET.CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        DCCOCGET.CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        DCCOCGET.CARD_STS = DCRCDDAT.CARD_STS;
        DCCOCGET.CARD_STSW = DCRCDDAT.CARD_STSW;
        DCCOCGET.ISSU_DT = DCRCDDAT.ISSU_DT;
        DCCOCGET.LAST_TXN_DT = DCRCDDAT.LAST_TXN_DT;
        DCCOCGET.LAST_DATE = DCRCDDAT.UPDTBL_DATE;
        DCCOCGET.LAST_USER = DCRCDDAT.UPDTBL_TLR;
        IBS.CLONE(SCCGWA, DCCOCGET, DCCNCGET);
        DCCNCGET.CARD_NO = DCCSPSWC.CARD_NO;
        DCCNCGET.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNCGET.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B055_ADD_AGENT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        CICSAGEN.OUT_AC = DCCSPSWC.CARD_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_DCZSARQC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-ARQC-CHECK", DCCSARQC);
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE HISTORY FLW-----");
        CEP.TRC(SCCGWA, DCCOCGET);
        CEP.TRC(SCCGWA, DCCNCGET);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "CHANGE CARD PASSWORD";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHCGET";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSPSWC.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSPSWC.CARD_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "P504";
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 142;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCOCGET;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNCGET;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSPSWC.CARD_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCSPSWC.ID_TYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCSPSWC.ID_NO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCSPSWC.CI_NM;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        WS_OUTPUT.WS_CARD_ARPC = DCCSARQC.CARD_ARPC;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CARD_ARPC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 384;
        IBS.FMT(SCCGWA, SCCFMT);
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
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_PASSWORD_SPCDEAL() throws IOException,SQLException,Exception {
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "DCZUPWER";
        WS_STAR_COMM.STAR_DATA = DCCUPWER;
        IBS.START(SCCGWA, WS_STAR_COMM, false);
        CEP.TRC(SCCGWA, DCCUPWER.PSWLOCK_FLG);
        if (DCCUPWER.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
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
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSPSWC.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_CI_NO.trim().length() > 0) {
            SCCWRMSG.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        }
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.AP_CODE = "359";
        if (DCCSPSWC.CARD_NO == null) DCCSPSWC.CARD_NO = "";
        JIBS_tmp_int = DCCSPSWC.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSPSWC.CARD_NO += " ";
        SCCWRMSG.ACL4 = DCCSPSWC.CARD_NO.substring(16 - 1, 16 + 4 - 1);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) SCCWRMSG.HOUR = 0;
        else SCCWRMSG.HOUR = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) SCCWRMSG.MINUTE = 0;
        else SCCWRMSG.MINUTE = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.SECOND = 0;
        else SCCWRMSG.SECOND = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'N' 
            || SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'W') {
            S000_CALL_SCCWRMSG();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
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
        if (SCCGWA.COMM_AREA.EXCP_FLG != 'Y' 
            && SCCCALL.RET_FLG != 'Y') {
            S000_SEND_MASSAGE_PROC();
            if (pgmRtn) return;
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
