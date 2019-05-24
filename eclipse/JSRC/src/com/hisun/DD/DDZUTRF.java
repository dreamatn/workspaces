package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUTRF {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String WS_AC = " ";
    char WS_DATA_OVERFLOW_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCCY DDRCCY = new DDRCCY();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUTRF DDCUTRF;
    public void MP(SCCGWA SCCGWA, DDCUTRF DDCUTRF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUTRF = DDCUTRF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUTRF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B040_CALL_DR_CR_UNT();
        if (pgmRtn) return;
        if (DDCUTRF.FR_APP.equalsIgnoreCase("AI")) {
            B020_CALL_AI_DR_UNT();
            if (pgmRtn) return;
        } else {
            B030_CALL_AI_CR_UNT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRF.FR_AC);
        CEP.TRC(SCCGWA, DDCUTRF.FR_APP);
        CEP.TRC(SCCGWA, DDCUTRF.TO_AC);
        CEP.TRC(SCCGWA, DDCUTRF.TO_APP);
        CEP.TRC(SCCGWA, DDCUTRF.CCY);
        CEP.TRC(SCCGWA, DDCUTRF.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUTRF.TX_AMT);
        CEP.TRC(SCCGWA, DDCUTRF.VAL_DATE);
        if (DDCUTRF.FR_AC.trim().length() == 0 
            || DDCUTRF.TO_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUTRF.FR_APP.trim().length() == 0 
            || DDCUTRF.TO_APP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_FRM_APP_ERR, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUTRF.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUTRF.TX_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_INPUT, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUTRF.VAL_DATE == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                DDCUTRF.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                DDCUTRF.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
        }
        CEP.TRC(SCCGWA, DDCUTRF.VAL_DATE);
    }
    public void B020_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDCUTRF.FR_AC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = DDCUTRF.VAL_DATE;
        AICUUPIA.DATA.AMT = DDCUTRF.TX_AMT;
        AICUUPIA.DATA.CCY = DDCUTRF.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.RVS_NO = DDCUTRF.FR_CREV_NO;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.THEIR_AC = DDCUTRF.TO_AC;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = DDCUTRF.CCY;
        AICUUPIA.DATA.THEIR_AMT = DDCUTRF.TX_AMT;
        AICUUPIA.DATA.PAY_MAN = DDCUTRF.TO_AC_NM;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        AICUUPIA.DATA.PAY_BR = DDRCCY.OWNER_BRDP;
        AICUUPIA.DATA.POST_NARR = DDCUTRF.REMARKS;
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        DDCUTRF.FR_CREV_NO = AICUUPIA.DATA.RVS_NO;
    }
    public void B030_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDCUTRF.TO_AC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = DDCUTRF.VAL_DATE;
        AICUUPIA.DATA.AMT = DDCUTRF.TX_AMT;
        AICUUPIA.DATA.CCY = DDCUTRF.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.RVS_NO = DDCUTRF.TO_CREV_NO;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.THEIR_AC = DDCUTRF.FR_AC;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = DDCUTRF.CCY;
        AICUUPIA.DATA.THEIR_AMT = DDCUTRF.TX_AMT;
        AICUUPIA.DATA.PAY_MAN = DDCUTRF.FR_AC_NM;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        AICUUPIA.DATA.PAY_BR = DDRCCY.OWNER_BRDP;
        AICUUPIA.DATA.POST_NARR = DDCUTRF.REMARKS;
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        DDCUTRF.TO_CREV_NO = AICUUPIA.DATA.RVS_NO;
    }
    public void B040_CALL_DR_CR_UNT() throws IOException,SQLException,Exception {
        if (!DDCUTRF.FR_APP.equalsIgnoreCase("AI")) {
            WS_AC = DDCUTRF.FR_AC;
        } else {
            WS_AC = DDCUTRF.TO_AC;
        }
        B041_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B042_UPD_BAL_PROC();
        if (pgmRtn) return;
        if (DDCUTRF.FHIS_FLG == 'N') {
        } else {
            B043_FIN_TXN_HIS_PROC();
            if (pgmRtn) return;
        }
        B044_GEN_VCH_PROC();
        if (pgmRtn) return;
    }
    public void B041_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_AC;
        DDRCCY.CCY = DDCUTRF.CCY;
        DDRCCY.CCY_TYPE = DDCUTRF.CCY_TYPE;
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (DDRCCY.STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_CLEARED, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        if (DDCUTRF.TX_AMT > DDRCCY.CURR_BAL 
            && DDCUTRF.FR_APP.equalsIgnoreCase("DD")) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B042_UPD_BAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDRCCY.LAST_BAL_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    DDRCCY.LAST_BAL += DDCUTRF.TX_AMT;
                } else {
                    DDRCCY.LAST_BAL -= DDCUTRF.TX_AMT;
                }
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.LAST_DATE);
        if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            DDRCCY.LAST_DRW_CAMT = DDRCCY.DRW_CAMT;
            DDRCCY.LAST_DEP_CAMT = DDRCCY.DEP_CAMT;
            DDRCCY.LAST_DRW_TAMT = DDRCCY.DRW_TAMT;
            DDRCCY.LAST_DEP_TAMT = DDRCCY.DEP_TAMT;
            DDRCCY.LASDAY_TOT_DR_AMT = DDRCCY.DAY_TOT_DR_AMT;
            DDRCCY.LASDAY_TOT_CR_AMT = DDRCCY.DAY_TOT_CR_AMT;
            DDRCCY.DRW_CAMT = 0;
            DDRCCY.DEP_CAMT = 0;
            DDRCCY.DRW_TAMT = 0;
            DDRCCY.DEP_TAMT = 0;
            DDRCCY.DAY_TOT_DR_AMT = 0;
            DDRCCY.DAY_TOT_CR_AMT = 0;
        }
        DDRCCY.DEP_TAMT += DDCUTRF.TX_AMT;
        DDRCCY.DAY_TOT_CR_AMT += DDCUTRF.TX_AMT;
        if (DDCUTRF.FR_APP.equalsIgnoreCase("AI")) {
            DDRCCY.CURR_BAL = DDRCCY.CURR_BAL + DDCUTRF.TX_AMT;
        } else {
            DDRCCY.CURR_BAL = DDRCCY.CURR_BAL - DDCUTRF.TX_AMT;
        }
        if (WS_DATA_OVERFLOW_FLAG == 'Y') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_OVERFLOW, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B043_FIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        if (!DDCUTRF.FR_APP.equalsIgnoreCase("AI")) {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
            BPCPFHIS.DATA.OTH_AC = DDCUTRF.TO_AC;
        } else {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
            BPCPFHIS.DATA.OTH_AC = DDCUTRF.FR_AC;
        }
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.SUMUP_FLG = '1';
        BPCPFHIS.DATA.AC = DDRCCY.CUS_AC;
        BPCPFHIS.DATA.TX_TOOL = DDRCCY.CUS_AC;
        BPCPFHIS.DATA.ACO_AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TOOL);
        BPCPFHIS.DATA.TX_CCY = DDCUTRF.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUTRF.CCY_TYPE;
        BPCPFHIS.DATA.TX_VAL_DT = DDCUTRF.VAL_DATE;
        BPCPFHIS.DATA.TX_AMT = DDCUTRF.TX_AMT;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.CI_NO = DDCUTRF.CI_NO;
        BPCPFHIS.DATA.VAL_BAL = DDRCCY.CURR_BAL;
        BPCPFHIS.DATA.VAL_BAL_CCY = DDCUTRF.CCY;
        BPCPFHIS.DATA.TX_MMO = DDCUTRF.TX_MMO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_MMO);
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        BPCPFHIS.DATA.NARRATIVE = DDCUTRF.NARRATIVE;
        BPCPFHIS.DATA.REMARK = DDCUTRF.REMARKS;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.NARRATIVE);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REMARK);
        BPCPFHIS.DATA.RLT_AC = DDCUTRF.RLT_AC;
        BPCPFHIS.DATA.RLT_AC_NAME = DDCUTRF.RLT_AC_NAME;
        BPCPFHIS.DATA.RLT_BANK = DDCUTRF.RLT_BANK;
        BPCPFHIS.DATA.RLT_BK_NM = DDCUTRF.RLT_BK_NM;
        BPCPFHIS.DATA.RLT_REF_NO = DDCUTRF.RLT_REF_NO;
        BPCPFHIS.DATA.RLT_CCY = DDCUTRF.RLT_CCY;
        BPCPFHIS.DATA.SMS_FLG = DDCUTRF.SMS_FLG;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BK_NM);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_REF_NO);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_CCY);
        IBS.init(SCCGWA, DDRFHIS);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = DDRCCY.CUS_AC;
        DDRFHIS.TX_TYPE = 'N';
        DDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        DDRFHIS.TX_AMT = DDCUTRF.TX_AMT;
        DDRFHIS.CCY = DDCUTRF.CCY;
        if (!DDCUTRF.FR_APP.equalsIgnoreCase("AI")) {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        } else {
            BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        }
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.REF = DDCUTRF.REMARKS;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = DDRCCY.CURR_BAL;
        DDRFHIS.CI_NO = DDCUTRF.CI_NO;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.FMT_LEN);
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B044_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCUTRF.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCUTRF.CCY);
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        if (!DDCUTRF.FR_APP.equalsIgnoreCase("AI")) {
            BPCPOEWA.DATA.EVENT_CODE = "DR";
            BPCPOEWA.DATA.AC_NO_REL = DDCUTRF.TO_AC;
            BPCPOEWA.DATA.THEIR_AC = DDCUTRF.TO_AC;
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
            BPCPOEWA.DATA.AC_NO_REL = DDCUTRF.FR_AC;
            BPCPOEWA.DATA.THEIR_AC = DDCUTRF.FR_AC;
        }
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUTRF.CCY;
        BPCPOEWA.DATA.VALUE_DATE = DDCUTRF.VAL_DATE;
        if (DDCUTRF.TX_AMT < 0) {
            DDCUTRF.TX_AMT = DDCUTRF.TX_AMT * -1;
            BPCPOEWA.DATA.AMT_INFO[11-1].AMT = DDCUTRF.TX_AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DDCUTRF.TX_AMT;
        }
        BPCPOEWA.DATA.CI_NO = DDCUTRF.CI_NO;
        BPCPOEWA.DATA.DESC = DDCUTRF.REMARKS;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[11-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY2_REC_NOTFND, DDCUTRF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
