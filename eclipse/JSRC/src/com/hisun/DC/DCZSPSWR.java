package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPSWR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCITCD_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "DC113";
    double K_MAX_BL = 50000;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_CARD_SEQNO = 0;
    String WS_CARDNO0 = " ";
    char WS_ADSC_TYPE = ' ';
    char WS_CARD_PROD_FLG = ' ';
    double WS_AVL_BAL = 0;
    char WS_BELONG_STS = ' ';
    String WS_HLDR_CINO = " ";
    DCZSPSWR_WS_OUTPUT WS_OUTPUT = new DCZSPSWR_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
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
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCRINRCD DCRINRCD = new DCRINRCD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHCGET DCCHCGET = new DCCHCGET();
    DCCHCGET DCCOCGET = new DCCHCGET();
    DCCHCGET DCCNCGET = new DCCHCGET();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCSPSWR DCCHISTY = new DCCSPSWR();
    DCCUTBAL DCCUTBAL = new DCCUTBAL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPSWR DCCSPSWR;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCSPSWR DCCSPSWR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPSWR = DCCSPSWR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPSWR return!");
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
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_RESET_PASSWD();
        if (pgmRtn) return;
        B040_HISTORY_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B055_ADD_AGENT_INFO();
            if (pgmRtn) return;
        }
        B050_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSPSWR.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSPSWR.FUNC != 'T' 
                && DCCSPSWR.FUNC != 'I') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSPSWR.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPSWR.CARD_TX_PASSWD1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TX_PASSWD_NEEDED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPSWR.FUNC == 'T' 
            && !DCCSPSWR.CARD_TX_PASSWD1.equalsIgnoreCase(DCCSPSWR.CARD_TX_PASSWD2)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SAME_TX_PASSWD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSPSWR.FUNC);
        if (DCCSPSWR.FUNC == 'T') {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSPSWR.CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            WS_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
            if (DCRCDDAT.CARD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (DCRCDDAT.CARD_STS != 'N') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                DCCUCDLP.CARD_NO = DCCSPSWR.CARD_NO;
                DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_DCZUCDLP();
                if (pgmRtn) return;
            } else {
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
            CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
            CEP.TRC(SCCGWA, DCCSPSWR.ID_TYP);
            if (DCRPRDPR.DATA_TXT.PSW_FLG != 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUPPORT_PSW_TRT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        if (SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCSPSWR.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_PROD_FLG);
            if (DCCUCINF.CARD_PROD_FLG == 'S') {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = DCCSPSWR.CARD_NO;
                DDCIQBAL.DATA.CCY = "156";
                DDCIQBAL.DATA.CCY_TYPE = '1';
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                if (DDCIQBAL.DATA.CURR_BAL != 0) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AVL_BAL_NOT_ZERO);
                }
                IBS.init(SCCGWA, CICQCIAC);
                CICQCIAC.FUNC = '3';
                CICQCIAC.DATA.AGR_NO = DCCSPSWR.CARD_NO;
                CICQCIAC.DATA.FRM_APP = "TD";
                S000_CALL_CIZQCIAC();
                if (pgmRtn) return;
                if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO.trim().length() > 0) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EST_TD_BAL);
                }
            }
        }
    }
    public void B030_RESET_PASSWD() throws IOException,SQLException,Exception {
        if (DCCSPSWR.FUNC == 'T') {
            IBS.init(SCCGWA, DCCUPSWM);
            DCCUPSWM.FUNC = 'R';
            DCCUPSWM.OLD_AGR_NO = DCCSPSWR.CARD_NO;
            DCCUPSWM.AGR_NO = DCCSPSWR.CARD_NO;
            if (DCCSPSWR.CARD_NO == null) DCCSPSWR.CARD_NO = "";
            JIBS_tmp_int = DCCSPSWR.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSPSWR.CARD_NO += " ";
            DCCUPSWM.AGR_NO_6 = DCCSPSWR.CARD_NO.substring(14 - 1, 14 + 6 - 1);
            DCCUPSWM.PSW_TYP = 'T';
            DCCUPSWM.CARD_PSW_NEW = DCCSPSWR.CARD_TX_PASSWD1;
            CEP.TRC(SCCGWA, WS_HLDR_CINO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_HLDR_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            CEP.TRC(SCCGWA, DCCSPSWR.ID_TYP);
            CEP.TRC(SCCGWA, DCCSPSWR.ID_NO);
            CEP.TRC(SCCGWA, DCCSPSWR.CI_NM);
            if (DCCSPSWR.ID_TYP.trim().length() == 0) {
                DCCUPSWM.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            } else {
                if (DCCSPSWR.ID_TYP.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE)) {
                    DCCUPSWM.ID_TYP = DCCSPSWR.ID_TYP;
                } else {
                    CEP.ERRC(SCCGWA, DCCMSG_ERROR_MSG.DC_ID_TYP_NOT_MATCH);
                }
            }
            if (DCCSPSWR.ID_NO.trim().length() == 0) {
                DCCUPSWM.ID_NO = CICCUST.O_DATA.O_ID_NO;
            } else {
                if (DCCSPSWR.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) {
                    DCCUPSWM.ID_NO = DCCSPSWR.ID_NO;
                } else {
                    CEP.ERRC(SCCGWA, DCCMSG_ERROR_MSG.DC_ID_NO_NOT_MATCH);
                }
            }
            if (DCCSPSWR.CI_NM.trim().length() == 0) {
                DCCUPSWM.CI_NM = CICCUST.O_DATA.O_CI_NM;
            } else {
                if (DCCSPSWR.CI_NM.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM)) {
                    DCCUPSWM.CI_NM = DCCSPSWR.CI_NM;
                } else {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_NM_NOT_MATCH);
                }
            }
            S000_CALL_DCZUPSWM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUPSWM.O_NEW_PSW);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSPSWR.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        DCRCDDAT.TRAN_PIN_DAT = DCCUPSWM.O_NEW_PSW;
        DCRCDDAT.PIN_ERR_CNT = 0;
        DCRCDDAT.PIN_LCK_DT = 0;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
        DCRCDDAT.PSW_TYP = 'N';
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B040_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHISTY);
        DCCHISTY.FUNC = DCCSPSWR.FUNC;
        DCCHISTY.CARD_NO = DCCSPSWR.CARD_NO;
        DCCHISTY.CI_NM = DCCSPSWR.CI_NM;
        DCCHISTY.ID_TYP = DCCSPSWR.ID_TYP;
        DCCHISTY.ID_NO = DCCSPSWR.ID_NO;
        DCCHISTY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCHISTY.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "RESET PASSWORD";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSPSWR";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSPSWR.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSPSWR.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 575;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCHISTY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSPSWR.CARD_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCSPSWR.ID_TYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCSPSWR.ID_NO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCSPSWR.CI_NM;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 364;
        IBS.FMT(SCCGWA, SCCFMT);
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
        CICSAGEN.OUT_AC = DCCSPSWR.CARD_NO;
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
    public void B061_CHECK_CARD_LOST_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCRINRCD.KEY.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
        CEP.TRC(SCCGWA, DCRINRCD.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRINRCD.KEY.CARD_SEQ);
        T000_READ_DCTINRCD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = DCRINRCD.CRT_DATE;
        SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DCRINRCD.CRT_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DCRINRCD.CRT_DATE);
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        if (SCCCLDT.DAYS < 3) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BYD_TRE_CAN_RPSW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B062_CHECK_CARD_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUTBAL);
        DCCUTBAL.INPUT_DATA.AC = DCCSPSWR.CARD_NO;
        S000_CALL_DCZUTBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUTBAL.OUTPUT_DATA.TOT_BAL);
        if (K_MAX_BL <= DCCUTBAL.OUTPUT_DATA.TOT_BAL) {
            B061_CHECK_CARD_LOST_DAYS();
            if (pgmRtn) return;
        }
    }
    public void H000_INRCD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCRINRCD.KEY.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
        DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRINRCD.KEY.INCD_TYPE = "14";
        DCRINRCD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRINRCD.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        DCRINRCD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRINRCD.CRT_TM = SCCGWA.COMM_AREA.TR_TIME;
        DCRINRCD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTINRCD();
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
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
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
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.TRC(SCCGWA, DCRCITCD.KEY.CARD_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
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
        DCTINRCD_RD.col = "CARD_NO, CARD_SEQ, INCD_TYPE, CRT_DATE, TR_BR";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ "
            + "AND INCD_TYPE = '04'";
        DCTINRCD_RD.fst = true;
        DCTINRCD_RD.order = "CRT_DATE DESC,CRT_TM DESC,TS DESC";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW_L_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUTBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUTBAL", DCCUTBAL);
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
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        IBS.WRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
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
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSPSWR.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_CI_NO.trim().length() > 0) {
            SCCWRMSG.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        }
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
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
            if ((SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'N' 
                || SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'W') 
                && WS_ADSC_TYPE == 'C') {
                S000_SEND_MASSAGE_PROC();
                if (pgmRtn) return;
            }
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
