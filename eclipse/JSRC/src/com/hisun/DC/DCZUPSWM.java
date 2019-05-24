package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICSSCH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSTAR;
import com.hisun.SC.SCCTSSC;
import com.hisun.SC.SCCWRMSG;

public class DCZUPSWM {
    boolean pgmRtn = false;
    String BSL_RTC_FLAG = "SIT_GN_20150507_V2";
    SCCSTAR SCCSTAR = new SCCSTAR();
    String K_PGM_NAME = "DCZUPSWM";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    DCZUPSWM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new DCZUPSWM_WS_TEMP_VARIABLE();
    int WS_J = 0;
    int WS_K = 0;
    int WS_PIN_ERR_CNT = 0;
    short WS_PSW_LEN = 0;
    char WS_ADSC_TYPE = ' ';
    String WS_TOR_SYSID = " ";
    char WS_JRN_IN_USE = ' ';
    String WS_OUT_PW = " ";
    char WS_PSW_FLG = ' ';
    char WS_PSW_TYP = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCALL SCCCALL = new SCCCALL();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    SCCHMPW SCCHMPW = new SCCHMPW();
    DCRCPARM DCRCPARM = new DCRCPARM();
    DCCUPWER DCCUPWER = new DCCUPWER();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICCUST CICCUST = new CICCUST();
    CICSSCH CICSSCH = new CICSSCH();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCCS9991 DCCS9991 = new DCCS9991();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    SCCGWA SCCGWA;
    DCCUPSWM DCCUPSWM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCUPSWM DCCUPSWM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUPSWM = DCCUPSWM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUPSWM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        DCCUPSWM.PSW_TYP = 'T';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSW_LEN = 32;
        CEP.TRC(SCCGWA, WS_PSW_LEN);
        B100_COMMON_CHECK();
        if (pgmRtn) return;
        if (DCCUPSWM.FUNC == 'C') {
            B200_CHECK_PASSWORD();
            if (pgmRtn) return;
        } else if (DCCUPSWM.FUNC == 'R') {
            B300_RESET_PASSWORD();
            if (pgmRtn) return;
        } else if (DCCUPSWM.FUNC == 'M') {
            B400_MODIFY_PASSWORD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUPSWM.RC);
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_COMMON_CHECK() throws IOException,SQLException,Exception {
        if ((DCCUPSWM.PSW_TYP != 'T' 
            && DCCUPSWM.PSW_TYP != 'Q')) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PASS_TYPE_ERROR, DCCUPSWM.RC);
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PASS_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUPSWM.FUNC == 'C' 
            || DCCUPSWM.FUNC == 'M') {
            if (DCCUPSWM.CARD_PSW_OLD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_PASSWORD, DCCUPSWM.RC);
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_PASSWORD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCUPSWM.FUNC == 'M' 
            || DCCUPSWM.FUNC == 'R') {
            if (DCCUPSWM.CARD_PSW_NEW.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_NEW_PSW, DCCUPSWM.RC);
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_NEW_PSW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCCUPSWM.CARD_PSW_OLD);
        CEP.TRC(SCCGWA, DCCUPSWM.CARD_PSW_NEW);
        if (DCCUPSWM.FUNC == 'M') {
            if (DCCUPSWM.CARD_PSW_OLD.equalsIgnoreCase(DCCUPSWM.CARD_PSW_NEW)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_CANNOT_BE_SAME);
            }
        }
        CEP.TRC(SCCGWA, DCCUPSWM.PSW_TYP);
        if (DCCUPSWM.FUNC == 'R') {
            if (DCCUPSWM.PSW_TYP == 'T' 
                && (DCCUPSWM.ID_TYP.trim().length() == 0 
                || DCCUPSWM.ID_NO.trim().length() == 0)) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR, DCCUPSWM.RC);
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        S000_CHECK_PASSWORD();
        if (pgmRtn) return;
    }
    public void B300_RESET_PASSWORD() throws IOException,SQLException,Exception {
        S000_RESET_PASSWORD();
        if (pgmRtn) return;
    }
    public void B400_MODIFY_PASSWORD() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DCCUPSWM.CARD_PSW_NEW = DCCUPSWM.CARD_PSW_OLD;
        } else {
            S000_CHECK_PASSWORD();
            if (pgmRtn) return;
        }
    }
    public void B500_RELEASE_PASSWORD() throws IOException,SQLException,Exception {
        WS_PSW_TYP = 'T';
        DCCUPSWM.CHK_RLT = 'Y';
        S000_CHECK_PASSWORD();
        if (pgmRtn) return;
        if (DCCUPSWM.CHK_RLT == 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSWD_NOT_MATCH, DCCUPSWM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT_UPDATE();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 9 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(9 + 1 - 1);
        DCRCDDAT.PIN_ERR_CNT = 0;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void S000_RESET_PASSWORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUPSWM.OLD_AGR_NO);
        CEP.TRC(SCCGWA, DCCUPSWM.AGR_NO);
        CEP.TRC(SCCGWA, DCCUPSWM.CARD_PSW_NEW);
        CEP.TRC(SCCGWA, DCCUPSWM.ID_TYP);
        CEP.TRC(SCCGWA, DCCUPSWM.ID_NO);
        CEP.TRC(SCCGWA, DCCUPSWM.CI_NM);
        CEP.TRC(SCCGWA, DCCUPSWM.AGR_NO_6);
        IBS.init(SCCGWA, DCCS9991);
        DCCS9991.OLD_AGR_NO = DCCUPSWM.OLD_AGR_NO;
        DCCS9991.NEW_AGR_NO = DCCUPSWM.AGR_NO;
        DCCS9991.AGR_NO_6 = DCCUPSWM.AGR_NO_6;
        DCCS9991.ID_TYPE = DCCUPSWM.ID_TYP;
        DCCS9991.ID_NO = DCCUPSWM.ID_NO;
        DCCS9991.CI_NM = DCCUPSWM.CI_NM;
        DCCS9991.PSWD_NO = DCCUPSWM.CARD_PSW_NEW;
        S000_CALL_DCZUPWCK();
        if (pgmRtn) return;
        DCCUPSWM.O_NEW_PSW = DCCS9991.O_PINOFFSET;
    }
    public void S000_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '1';
        if (DCCUPSWM.PSW_FLG == 'O') {
            SCCTSSC.COMM_AREA_1.1_DST_ALG_MODE = '0';
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        } else {
            SCCTSSC.COMM_AREA_1.1_DST_ALG_MODE = '1';
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        }
        CEP.TRC(SCCGWA, DCCUPSWM.OLD_AGR_NO);
        if (DCCUPSWM.OLD_AGR_NO == null) DCCUPSWM.OLD_AGR_NO = "";
        JIBS_tmp_int = DCCUPSWM.OLD_AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCUPSWM.OLD_AGR_NO += " ";
        CEP.TRC(SCCGWA, DCCUPSWM.OLD_AGR_NO.substring(0, 2));
        if (DCCUPSWM.OLD_AGR_NO == null) DCCUPSWM.OLD_AGR_NO = "";
        JIBS_tmp_int = DCCUPSWM.OLD_AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCUPSWM.OLD_AGR_NO += " ";
        if (DCCUPSWM.OLD_AGR_NO.substring(0, 2).equalsIgnoreCase("FT")) {
            SCCTSSC.COMM_AREA_1.1_SRC_ACCOUNT = DCCUPSWM.OLD_AGR_NO.substring(4-1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        } else {
            SCCTSSC.COMM_AREA_1.1_SRC_ACCOUNT = DCCUPSWM.OLD_AGR_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        }
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_1.1_SRC_ACCOUNT);
        SCCTSSC.COMM_AREA_1.1_SRC_PIN_BLOCK = DCCUPSWM.CARD_PSW_OLD;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        SCCTSSC.COMM_AREA_1.1_ENC_MODE = "01";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        DCCUPSWM.O_OLD_PSW = SCCTSSC.COMM_AREA_1.1_O_OLD_PIN;
        DCCUPSWM.O_NEW_PSW = SCCTSSC.COMM_AREA_1.1_O_NEW_PIN;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_1.1_O_OLD_PIN);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_1.1_O_NEW_PIN);
    }
    public void S001_CHECK_STS_PSWNULL() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE, DCCUPSWM.RC);
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.TRAN_PIN_DAT.trim().length() == 0 
            && DCRCDDAT.CARD_STS == 'N') {
            if (DCCUPSWM.CARD_PSW_OLD.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW, DCCUPSWM.RC);
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUPSWM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI", CICSSCH);
        CEP.TRC(SCCGWA, CICSSCH.RC.RC_CODE);
        if (CICSSCH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUPSWM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUPSWM.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
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
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUPSWM.RC);
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT_UPDATE() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUPSWM.RC);
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, JOIN_CUS_FLG, ANNUAL_FEE_FREE, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, MOVE_FLG, PSW_TYP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDDAT_RD.where = "CARD_NO = :DCRCDDAT.KEY.CARD_NO";
        IBS.REWRITE(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void S000_CALL_SCZRAND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-RANDOM-VALUE     ", SCCRAND);
        CEP.TRC(SCCGWA, SCCRAND.RC.RC_CODE);
