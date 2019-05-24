package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMCHQ {
    int JIBS_tmp_int;
    brParm DDTCHQ_BR = new brParm();
    brParm DDTSTOP_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTSTOP_RD;
    DBParm DDTSCHQ_RD;
    DBParm DDTCHQ_RD;
    DBParm DDTPAID_RD;
    String K_CMP_INQ_JRN = "SC-JOURNAL-MAINTAIN";
    String WS_ERR_MSG = " ";
    String WS_END_NO = " ";
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_CHQ_END_CHQ_NO = 0;
    long WS_CHQ_NO = 0;
    long WS_STR_NO = 0;
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    long WS_IMCHQ_STR_CHQ_NO = 0;
    long WS_IMCHQ_END_CHQ_NO = 0;
    int WS_CHQ_CNT = 0;
    String WS_CHQ_STS = " ";
    int WS_CHQ_ISSU_DATE = 0;
    int WS_CNT = 0;
    short WS_STR_POS = 0;
    short WS_CUR_POS = 0;
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    int WS_LOST_EFF_DATE = 0;
    double WS_TOT_AMT = 0;
    DDZIMCHQ_WS_STR_CHQM_NO WS_STR_CHQM_NO = new DDZIMCHQ_WS_STR_CHQM_NO();
    DDZIMCHQ_WS_END_CHQM_NO WS_END_CHQM_NO = new DDZIMCHQ_WS_END_CHQM_NO();
    DDZIMCHQ_WS_CHQM_NO WS_CHQM_NO = new DDZIMCHQ_WS_CHQM_NO();
    DDZIMCHQ_WS_CHQM_NO_L WS_CHQM_NO_L = new DDZIMCHQ_WS_CHQM_NO_L();
    DDZIMCHQ_WS_CHQM_NO_SL WS_CHQM_NO_SL = new DDZIMCHQ_WS_CHQM_NO_SL();
    DDZIMCHQ_WS_CHQM_NO_EL WS_CHQM_NO_EL = new DDZIMCHQ_WS_CHQM_NO_EL();
    int WS_LEN = 0;
    int WS_LEE = 0;
    int WS_TMP_DT = 0;
    char WS_CHQ_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_READ_STOP_FLG = ' ';
    char WS_SCHQ_FLG = ' ';
    String WS_ERR_INFO = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCHSTOP DDCSTOPO = new DDCHSTOP();
    DDCHSTOP DDCSTOPN = new DDCHSTOP();
    SCCPJRN SCCPJRN = new SCCPJRN();
    SCRJRN SCRJRN = new SCRJRN();
    CICQACAC CICQACAC = new CICQACAC();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    DDCPCHQ DDCPCHQ = new DDCPCHQ();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICCUST CICCUST = new CICCUST();
    DDRMST DDRMST = new DDRMST();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRSCHQ DDRSCHQ = new DDRSCHQ();
    DDRCCY DDRCCY = new DDRCCY();
    DDRSTOP DDRSTOP = new DDRSTOP();
    DDRPAID DDRPAID = new DDRPAID();
    SCCGWA SCCGWA;
    DDCIMCHQ DDCIMCHQ;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCIMCHQ DDCIMCHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMCHQ = DDCIMCHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZIMCHQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, DDCIMCHQ.DATA.STR_CHQ_NO, WS_STR_CHQM_NO);
        IBS.CPY2CLS(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO, WS_END_CHQM_NO);
        IBS.CPY2CLS(SCCGWA, DDCIMCHQ.DATA.CHQ_NO, WS_CHQM_NO);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCIMCHQ.FUNC == 'I') {
            B010_INQ_CHQ_PROC();
        } else if (DDCIMCHQ.FUNC == 'C') {
            B020_CHK_CHQ_PROC();
        } else if (DDCIMCHQ.FUNC == 'S') {
            B030_SIGN_CHQ_PROC();
        } else if (DDCIMCHQ.FUNC == 'T') {
            B050_STOP_CHQ_PROC();
            B060_CRT_STOP_PROC();
        } else if (DDCIMCHQ.FUNC == 'R') {
            B070_RELEASE_STOP_PROC();
            B080_RELEASE_CHQ_PROC();
        } else if (DDCIMCHQ.FUNC == 'P') {
            B090_PAY_CHQ_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIMCHQ.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_INQ_CHQ_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRCHQ.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCIMCHQ.DATA.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCIMCHQ.DATA.END_CHQ_NO;
        T000_READ_DDTCHQ();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            R000_TRANS_DATA_OUT();
        }
    }
    public void B020_CHK_CHQ_PROC() throws IOException,SQLException,Exception {
        if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() > 0) {
            WS_LEN = 0;
            WS_LEN = DDCIMCHQ.DATA.STR_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_LEN);
            if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, WS_LEN))) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_ERR);
            }
        }
        if (DDCIMCHQ.DATA.END_CHQ_NO.trim().length() > 0) {
            WS_LEE = 0;
            WS_LEE = DDCIMCHQ.DATA.END_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_LEE);
            if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCIMCHQ.DATA.END_CHQ_NO.substring(0, WS_LEE))) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_ERR);
            }
        }
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.AC);
        if (DDCIMCHQ.DATA.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CCY);
        if (DDCIMCHQ.DATA.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_NO);
        if (DDCIMCHQ.DATA.CHQ_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_MUST_INP);
        }
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.ISSU_DATE);
        if (DDCIMCHQ.DATA.ISSU_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_ISSU_DT_M_INPUT);
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B020_01_CHK_CHQ_DATE();
            }
        }
        R000_UPD_CHQ_PROC();
        CEP.TRC(SCCGWA, WS_CHQ_ISSU_DATE);
        if (DDCIMCHQ.DATA.ISSU_DATE < WS_CHQ_ISSU_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SIGN_DT_LESS_ISSU_DT);
        }
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_TYP);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.CHQ_TYP);
        if (DDCIMCHQ.DATA.CHQ_TYP != ' ' 
            && DDCIMCHQ.DATA.CHQ_TYP != DDRCHQ.KEY.CHQ_TYP) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_CHQ_TYPE);
        }
    }
    public void B020_01_CHK_CHQ_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCIMCHQ.DATA.ISSU_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ISSDT_GT_ACDATE);
        }
        R000_GET_CHQ_VALID_DT_PARM();
        if (DDCIMCHQ.DATA.ISSU_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = DDCIMCHQ.DATA.AC;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            IBS.init(SCCGWA, BPCOCLWD);
            CEP.TRC(SCCGWA, "BPCOCLWD1");
            if (CICCUST.O_DATA.O_CI_TYP != '1') {
                CEP.TRC(SCCGWA, "1111111111");
                BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
            } else {
                CEP.TRC(SCCGWA, "2222222222");
                BPCOCLWD.CAL_CODE = BPCRBANK.CALD_PUB;
            }
            CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
            BPCOCLWD.DATE1 = DDCIMCHQ.DATA.ISSU_DATE;
            CEP.TRC(SCCGWA, DDCPCHQ.DATA_TXT.DAYS);
            BPCOCLWD.DAYS = DDCPCHQ.DATA_TXT.DAYS;
            S000_CALL_BPZPCLWD();
            WS_TMP_DT = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, WS_TMP_DT);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_FLG);
            if (BPCOCLWD.DATE2 < SCCGWA.COMM_AREA.AC_DATE 
                && BPCOCLWD.DATE2_FLG != 'H') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_OVER_TEN_DAY);
            }
            if (BPCOCLWD.DATE2 < SCCGWA.COMM_AREA.AC_DATE 
                && BPCOCLWD.DATE2_FLG == 'H') {
                IBS.init(SCCGWA, BPCOCLWD);
                if (CICCUST.O_DATA.O_CI_TYP != '1') {
                    BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
                } else {
                    BPCOCLWD.CAL_CODE = BPCRBANK.CALD_PUB;
                }
                BPCOCLWD.DATE1 = WS_TMP_DT;
                BPCOCLWD.WDAYS = 1;
                S000_CALL_BPZPCLWD();
                CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
                if (BPCOCLWD.DATE2 < SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_OVER_TEN_DAY);
                }
            }
        }
    }
    public void B030_SIGN_CHQ_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        B030_10_CHECK_CHQ_NO_PROC();
        if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() == 0) WS_STR_CHQ_NO = 0;
        else WS_STR_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.STR_CHQ_NO);
        if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() == 0) WS_IMCHQ_STR_CHQ_NO = 0;
        else WS_IMCHQ_STR_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.STR_CHQ_NO);
        WS_END_CHQ_NO = WS_IMCHQ_STR_CHQ_NO + 49;
        if (WS_END_CHQ_NO > DDCIMCHQ.DATA.END_CHQ_NO) {
            if (DDCIMCHQ.DATA.END_CHQ_NO.trim().length() == 0) WS_END_CHQ_NO = 0;
            else WS_END_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.END_CHQ_NO);
        }
        B030_30_CRT_CHQ_PROC();
        while (WS_END_CHQ_NO < DDCIMCHQ.DATA.END_CHQ_NO) {
            WS_STR_CHQ_NO = WS_END_CHQ_NO + 1;
            WS_END_CHQ_NO = WS_STR_CHQ_NO + 49;
            if (WS_END_CHQ_NO > DDCIMCHQ.DATA.END_CHQ_NO) {
                if (DDCIMCHQ.DATA.END_CHQ_NO.trim().length() == 0) WS_END_CHQ_NO = 0;
                else WS_END_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.END_CHQ_NO);
            }
            B030_30_CRT_CHQ_PROC();
        }
    }
    public void B030_10_CHECK_CHQ_NO_PROC() throws IOException,SQLException,Exception {
        T000_STBR_AC_CCY_CHQ_PROC();
        T000_READNEXT_PROC();
        while (WS_CHQ_FLG != 'N') {
            if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() == 0) WS_CHQ_NO = 0;
            else WS_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.STR_CHQ_NO);
            while (WS_CHQ_NO <= DDCIMCHQ.DATA.END_CHQ_NO) {
                if ((WS_CHQ_NO >= DDRCHQ.KEY.STR_CHQ_NO) 
                    && (WS_CHQ_NO <= DDRCHQ.KEY.END_CHQ_NO)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_NO_REPEAT;
                    S000_ERR_MSG_PROC();
                }
                WS_CHQ_NO = WS_CHQ_NO + 1;
            }
            T000_READNEXT_PROC();
        }
        T000_ENDBR_PROC();
    }
    public void B030_30_CRT_CHQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRCHQ.CCY = DDCIMCHQ.DATA.CCY;
        DDRCHQ.CCY_TYPE = DDCIMCHQ.DATA.CCY_TYPE;
        DDRCHQ.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = "" + WS_STR_CHQ_NO;
        JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO = "0" + DDRCHQ.KEY.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = "" + WS_END_CHQ_NO;
        JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO = "0" + DDRCHQ.KEY.END_CHQ_NO;
        DDRCHQ.CHQ_STS = "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN";
        DDRCHQ.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_DDTCHQ();
    }
    public void B050_STOP_CHQ_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRCHQ.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCIMCHQ.DATA.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCIMCHQ.DATA.END_CHQ_NO;
        if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() == 0) WS_CHQ_NO = 0;
        else WS_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.STR_CHQ_NO);
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        if (DDRCHQ.KEY.STR_CHQ_NO.trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
        else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO);
        if (DDRCHQ.KEY.END_CHQ_NO.trim().length() == 0) WS_CHQ_END_CHQ_NO = 0;
        else WS_CHQ_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO);
        if (DDCIMCHQ.DATA.END_CHQ_NO.trim().length() == 0) WS_IMCHQ_END_CHQ_NO = 0;
        else WS_IMCHQ_END_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.END_CHQ_NO);
        if (WS_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        while (WS_CHQ_FLG != 'N' 
            && WS_CHQ_NO <= DDCIMCHQ.DATA.END_CHQ_NO) {
            if (WS_CHQ_NO >= DDRCHQ.KEY.STR_CHQ_NO 
                && WS_CHQ_NO <= DDRCHQ.KEY.END_CHQ_NO) {
                WS_STR_POS = (short) (WS_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
                CEP.TRC(SCCGWA, WS_STR_POS);
                if (DDCIMCHQ.DATA.END_CHQ_NO.compareTo(DDRCHQ.KEY.END_CHQ_NO) < 0) {
                    WS_CHQ_CNT = (int) (WS_IMCHQ_END_CHQ_NO - WS_CHQ_NO + 1);
                } else {
                    WS_CHQ_CNT = (int) (WS_CHQ_END_CHQ_NO - WS_CHQ_NO + 1);
                }
                CEP.TRC(SCCGWA, WS_CHQ_CNT);
                WS_CHQ_STS = DDRCHQ.CHQ_STS;
                for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                    WS_CUR_POS = (short) (WS_STR_POS + WS_CNT - 1);
                    CEP.TRC(SCCGWA, WS_CUR_POS);
                    CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS);
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    if (!DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("Y")) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_INVALID;
                        S000_ERR_MSG_PROC();
                    } else {
                        if (DDCIMCHQ.DATA.LOST_TYPE == 'U') {
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CUR_POS - 1) + "3" + WS_CHQ_STS.substring(WS_CUR_POS + 1 - 1);
                        } else {
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CUR_POS - 1) + "4" + WS_CHQ_STS.substring(WS_CUR_POS + 1 - 1);
                        }
                    }
                }
                CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS);
                if (WS_LOST_EFF_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                    T000_READUP_DDTCHQ();
                    DDRCHQ.CHQ_STS = WS_CHQ_STS;
                    T000_REWRITE_DDTCHQ();
                }
            }
            WS_CHQ_NO = WS_CHQ_NO + WS_CHQ_CNT;
            CEP.TRC(SCCGWA, WS_CHQ_NO);
            T000_READNEXT_DDTCHQ();
        }
        CEP.TRC(SCCGWA, WS_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        if (WS_CHQ_NO <= DDCIMCHQ.DATA.END_CHQ_NO) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        T000_ENDBR_DDTCHQ();
    }
    public void B060_CRT_STOP_PROC() throws IOException,SQLException,Exception {
        B060_10_STP_BY_CHQ_CHK_PROC();
        B060_30_CRT_STOP_REC_PROC();
    }
    public void B060_10_STP_BY_CHQ_CHK_PROC() throws IOException,SQLException,Exception {
        T000_STBR_STOP_BY_CHQ_PROC();
        T000_GET_NEXT_STOP_PROC();
        while (WS_STOP_FLG != 'N') {
            if (DDRSTOP.LOST_EXP_DATE != 0 
                && DDRSTOP.LOST_EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            } else {
                if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() == 0) WS_CHQ_NO = 0;
                else WS_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.STR_CHQ_NO);
                while (WS_CHQ_NO <= DDCIMCHQ.DATA.END_CHQ_NO) {
                    if (DDRSTOP.VCA_FLG == 'N') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_ALREADY_STOPED;
                        S000_ERR_MSG_PROC();
                    }
                    WS_CHQ_NO = WS_CHQ_NO + 1;
                }
            }
            T000_GET_NEXT_STOP_PROC();
        }
        T000_END_BRW_STOP_PROC();
    }
    public void B060_30_CRT_STOP_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSTOP);
        DDRSTOP.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRSTOP.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        T000_READUP_DDTSTOP();
        if (DDCIMCHQ.DATA.LOST_TYPE == 'U') {
            DDRSTOP.UNW_LOST_DATE = DDCIMCHQ.DATA.UNW_LOST_DATE;
            DDRSTOP.W_LOST_DATE = 0;
            DDRSTOP.LOST_EXP_DATE = DDRSTOP.UNW_LOST_DATE + 3;
        } else {
            DDRSTOP.UNW_LOST_DATE = 0;
            DDRSTOP.W_LOST_DATE = DDCIMCHQ.DATA.W_LOST_DATE;
            DDRSTOP.LOST_EXP_DATE = 99991231;
        }
        DDRSTOP.VCA_FLG = 'N';
        CEP.TRC(SCCGWA, DDRSTOP.KEY.AC);
        CEP.TRC(SCCGWA, DDRSTOP.UNW_LOST_DATE);
        CEP.TRC(SCCGWA, DDRSTOP.W_LOST_DATE);
        CEP.TRC(SCCGWA, DDRSTOP.LOST_EXP_DATE);
        if (WS_READ_STOP_FLG == 'N') {
            T000_WRITE_DDTSTOP();
        } else {
            T000_REWRITE_DDTSTOP();
        }
    }
    public void B070_RELEASE_STOP_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, DDRSTOP);
        DDRSTOP.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRSTOP.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        T000_READUP_DDTSTOP();
        if (WS_READ_STOP_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STOP_CHQ_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCSTOPO);
        DDCSTOPO.DEL_FLG = DDRSTOP.VCA_FLG;
        if (DDCIMCHQ.DATA.LOST_TYPE == 'U') {
            WS_EFF_DATE = DDRSTOP.UNW_LOST_DATE;
        } else {
            WS_EFF_DATE = DDRSTOP.W_LOST_DATE;
        }
        if (WS_EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STOP_REC_INACTIVE;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRSTOP.VCA_FLG);
        if (DDRSTOP.VCA_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STP_REC_ALREADY_RLS;
            S000_ERR_MSG_PROC();
        }
        DDRSTOP.VCA_FLG = 'Y';
        T000_REWRITE_DDTSTOP();
        IBS.init(SCCGWA, DDCSTOPN);
        DDCSTOPN.DEL_FLG = DDRSTOP.VCA_FLG;
    }
    public void B080_RELEASE_CHQ_PROC() throws IOException,SQLException,Exception {
        R000_UPD_CHQ_PROC();
    }
    public void B090_PAY_CHQ_PROC() throws IOException,SQLException,Exception {
        WS_LEN = 0;
        WS_LEN = DDCIMCHQ.DATA.STR_CHQ_NO.trim().length();
        CEP.TRC(SCCGWA, WS_LEN);
        if (DDCIMCHQ.DATA.STR_CHQ_NO.trim().length() > 0) {
            if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, WS_LEN))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCIMCHQ.DATA.END_CHQ_NO.trim().length() > 0) {
            WS_LEE = 0;
            WS_LEE = DDCIMCHQ.DATA.STR_CHQ_NO.trim().length();
            if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCIMCHQ.DATA.END_CHQ_NO.substring(0, WS_LEE))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        R000_CHECK_INPUT_DATA();
        R000_UPD_CHQ_PROC();
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.ISSU_DATE);
        CEP.TRC(SCCGWA, WS_CHQ_ISSU_DATE);
        if (DDCIMCHQ.DATA.ISSU_DATE < WS_CHQ_ISSU_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_SIGN_DT_LESS_ISSU_DT;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, DDRPAID);
            DDRPAID.KEY.AC = DDCIMCHQ.DATA.AC;
            DDRPAID.KEY.CHQ_NO = DDCIMCHQ.DATA.CHQ_NO;
            CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_TYP);
            CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_NO);
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && WS_LEN == 8 
                && DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() > 0) {
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRPAID.KEY.CHQ_NO == null) DDRPAID.KEY.CHQ_NO = "";
                JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO += " ";
                DDRPAID.KEY.CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8) + DDRPAID.KEY.CHQ_NO.substring(8);
                if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
                JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
                if (DDRPAID.KEY.CHQ_NO == null) DDRPAID.KEY.CHQ_NO = "";
                JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO += " ";
                DDRPAID.KEY.CHQ_NO = DDRPAID.KEY.CHQ_NO.substring(0, 9 - 1) + DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, 8) + DDRPAID.KEY.CHQ_NO.substring(9 + 8 - 1);
            }
            DDRPAID.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
            DDRPAID.AMT = DDCIMCHQ.DATA.PAID_AMT;
            DDRPAID.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRPAID.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDRPAID.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
            DDRPAID.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDRPAID.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            DDRPAID.TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRPAID.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRPAID.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRPAID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRPAID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DDTPAID();
        } else {
            IBS.init(SCCGWA, DDRPAID);
            DDRPAID.KEY.AC = DDCIMCHQ.DATA.AC;
            DDRPAID.KEY.CHQ_NO = DDCIMCHQ.DATA.CHQ_NO;
            DDRPAID.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
            CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_NO);
            CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_TYP);
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (!SCRJRN.CHNL.equalsIgnoreCase("TRM") 
                && WS_LEN == 8 
                && DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() > 0) {
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRPAID.KEY.CHQ_NO == null) DDRPAID.KEY.CHQ_NO = "";
                JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO += " ";
                DDRPAID.KEY.CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8) + DDRPAID.KEY.CHQ_NO.substring(8);
                if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
                JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
                if (DDRPAID.KEY.CHQ_NO == null) DDRPAID.KEY.CHQ_NO = "";
                JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO += " ";
                DDRPAID.KEY.CHQ_NO = DDRPAID.KEY.CHQ_NO.substring(0, 9 - 1) + DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, 8) + DDRPAID.KEY.CHQ_NO.substring(9 + 8 - 1);
            }
            T000_READUP_DDTPAID();
        }
        if (DDCIMCHQ.DATA.CHQ_TYP == '1' 
            || DDCIMCHQ.DATA.CHQ_TYP == '2' 
            || DDCIMCHQ.DATA.CHQ_TYP == '3') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.ACAC_NO = DDCIMCHQ.DATA.AC;
            CICQACAC.FUNC = 'A';
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR);
            IBS.init(SCCGWA, BPCFFTXI);
            BPCFFTXI.TX_DATA.AUH_FLG = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
            BPCFFTXI.TX_DATA.CCY_TYPE = '1';
            BPCFFTXI.TX_DATA.SVR_CD = "0115403";
            BPCFFTXI.TX_DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_BPZFFTXI();
            IBS.init(SCCGWA, BPCTCALF);
            BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
            BPCTCALF.INPUT_AREA.TX_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            BPCTCALF.INPUT_AREA.TX_CCY = "156";
            BPCTCALF.INPUT_AREA.TX_CNT = 1;
            BPCTCALF.INPUT_AREA.TX_CI = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            if (DDCIMCHQ.DATA.CHQ_TYP == '1') {
                BPCTCALF.INPUT_AREA.BVF_CODE = "C00002";
            }
            if (DDCIMCHQ.DATA.CHQ_TYP == '2') {
                BPCTCALF.INPUT_AREA.BVF_CODE = "C00003";
            }
            if (DDCIMCHQ.DATA.CHQ_TYP == '3') {
                BPCTCALF.INPUT_AREA.BVF_CODE = "C00004";
            }
            BPCTCALF.INPUT_AREA.PROD_CODE = CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR;
            BPCTCALF.INPUT_AREA.PROD_CODE1 = CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR;
            BPCTCALF.INPUT_AREA.SVR_CD = "0117777";
            S000_CALL_BPZFCALF();
        }
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIMCHQ.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCIMCHQ.DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCIMCHQ.FUNC == 'P') {
            if (DDCIMCHQ.DATA.CHQ_NO.equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCIMCHQ.FUNC == 'I' 
            || DDCIMCHQ.FUNC == 'S' 
            || DDCIMCHQ.FUNC == 'T' 
            || DDCIMCHQ.FUNC == 'R') {
            if (DDCIMCHQ.DATA.STR_CHQ_NO.equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (DDCIMCHQ.DATA.END_CHQ_NO.equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_CHQ_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_UPD_CHQ_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
        JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
        if (DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_NO = 0;
        else WS_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, WS_LEN));
        if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
        JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
        if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
        else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN));
        if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
        JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
        if (DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_END_CHQ_NO = 0;
        else WS_CHQ_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_LEN));
        if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
        JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
        if (DDCIMCHQ.DATA.END_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_IMCHQ_END_CHQ_NO = 0;
        else WS_IMCHQ_END_CHQ_NO = Long.parseLong(DDCIMCHQ.DATA.END_CHQ_NO.substring(0, WS_LEN));
        CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_END_CHQ_NO);
        IBS.CPY2CLS(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO, WS_CHQM_NO_SL);
        IBS.CPY2CLS(SCCGWA, DDRCHQ.KEY.END_CHQ_NO, WS_CHQM_NO_EL);
        if (WS_CHQ_FLG == 'N') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                R000_CANCEL_JRNNO();
                CEP.TRC(SCCGWA, SCRJRN.CHNL);
            }
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, "DDTCHQ1");
        }
        CEP.TRC(SCCGWA, "XXX");
        CEP.TRC(SCCGWA, WS_CHQ_NO);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_END_CHQ_NO);
        if (!DDRCHQ.CCY.equalsIgnoreCase(DDCIMCHQ.DATA.CCY) 
            || DDRCHQ.CCY_TYPE != DDCIMCHQ.DATA.CCY_TYPE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_CCY_IS_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
        while (WS_CHQ_FLG != 'N' 
            && WS_CHQ_NO <= WS_IMCHQ_END_CHQ_NO) {
            CEP.TRC(SCCGWA, "UNTIL");
            if ((WS_CHQ_NO >= WS_CHQ_STR_CHQ_NO) 
                && (WS_CHQ_NO <= WS_CHQ_END_CHQ_NO)) {
                CEP.TRC(SCCGWA, "XXX2");
                DDCIMCHQ.DATA.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
                CEP.TRC(SCCGWA, WS_CHQ_NO);
                CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
                WS_STR_POS = (short) (WS_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
                CEP.TRC(SCCGWA, WS_STR_POS);
                if (DDCIMCHQ.DATA.END_CHQ_NO.compareTo(DDRCHQ.KEY.END_CHQ_NO) < 0) {
                    CEP.TRC(SCCGWA, WS_LEN);
                    CEP.TRC(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO);
                    CEP.TRC(SCCGWA, WS_IMCHQ_END_CHQ_NO);
                    CEP.TRC(SCCGWA, WS_CHQ_NO);
                    WS_CHQ_CNT = (int) (WS_IMCHQ_END_CHQ_NO - WS_CHQ_NO + 1);
                    CEP.TRC(SCCGWA, WS_IMCHQ_END_CHQ_NO);
                } else {
                    CEP.TRC(SCCGWA, "ELSE");
                    WS_CHQ_CNT = (int) (WS_CHQ_END_CHQ_NO - WS_CHQ_NO + 1);
                }
                CEP.TRC(SCCGWA, "WUYIPING1");
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, WS_CHQ_CNT);
                CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS);
                CEP.TRC(SCCGWA, "GSQ");
                CEP.TRC(SCCGWA, DDCIMCHQ.FUNC);
                CEP.TRC(SCCGWA, WS_CHQ_CNT);
                CEP.TRC(SCCGWA, WS_CNT);
                WS_CHQ_STS = DDRCHQ.CHQ_STS;
                for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                    CEP.TRC(SCCGWA, "WUYIPING4");
                    CEP.TRC(SCCGWA, WS_STR_POS);
                    CEP.TRC(SCCGWA, WS_CNT);
                    WS_CUR_POS = (short) (WS_STR_POS + WS_CNT - 1);
                    if (DDCIMCHQ.FUNC == 'R') {
                        if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                        JIBS_tmp_int = WS_CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                        if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                        JIBS_tmp_int = WS_CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                        if (!WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("3") 
                            && !WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("4")) {
                            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_INVALID;
                            S000_ERR_MSG_PROC();
                        } else {
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CUR_POS - 1) + "1" + WS_CHQ_STS.substring(WS_CUR_POS + 1 - 1);
                        }
                    }
                    CEP.TRC(SCCGWA, WS_CHQ_STS);
                    CEP.TRC(SCCGWA, WS_CUR_POS);
                    if (DDCIMCHQ.FUNC == 'P') {
                        if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                        JIBS_tmp_int = WS_CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                        if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                        JIBS_tmp_int = WS_CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                        if (!WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("1") 
                            && !WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("6") 
                            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("2")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_CHQ_PAIDED;
                                S000_ERR_MSG_PROC();
                            }
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("3") 
                                || WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("4")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LOSS;
                                S000_ERR_MSG_PROC();
                            }
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("5")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_CANCEL;
                                S000_ERR_MSG_PROC();
                            }
                        }
                        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("2")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_CHQ_PAIDED;
                                S000_ERR_MSG_PROC();
                            }
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CUR_POS - 1) + "2" + WS_CHQ_STS.substring(WS_CUR_POS + 1 - 1);
                            CEP.TRC(SCCGWA, "STS=2");
                        } else {
                            R000_UPD_STOP_PROC();
                        }
                    }
                    if (DDCIMCHQ.FUNC == 'C') {
                        if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                        JIBS_tmp_int = WS_CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                        if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                        JIBS_tmp_int = WS_CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                        if (!WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("1") 
                            && !WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("6") 
                            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("2")) {
                                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PAY_CHQ_PAIDED);
                            }
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("3") 
                                || WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("4")) {
                                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_LOSS);
                            }
                            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
                            JIBS_tmp_int = WS_CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
                            if (WS_CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("5")) {
                                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_CANCEL);
                            }
                        }
                    }
                }
                if ((DDCIMCHQ.FUNC == 'R' 
                    && WS_EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                    && DDRSTOP.LOST_EXP_DATE >= SCCGWA.COMM_AREA.AC_DATE) 
                    || DDCIMCHQ.FUNC == 'P') {
                    T000_READUP_DDTCHQ();
                    DDRCHQ.CHQ_STS = WS_CHQ_STS;
                    T000_REWRITE_DDTCHQ();
                }
                WS_CHQ_ISSU_DATE = DDRCHQ.CRT_DATE;
            }
            CEP.TRC(SCCGWA, WS_CHQ_NO);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            WS_CHQ_NO = WS_CHQ_NO + WS_CHQ_CNT;
            T000_READNEXT_DDTCHQ();
        }
        CEP.TRC(SCCGWA, WS_CHQ_NO);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO);
        if (WS_CHQ_NO <= WS_IMCHQ_END_CHQ_NO) {
            if (DDCIMCHQ.FUNC == 'R') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_RLS_CHQ_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (DDCIMCHQ.FUNC == 'P') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_CHQ_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        T000_ENDBR_DDTCHQ();
    }
    public void R000_UPD_STOP_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "UPDSTOP");
        IBS.init(SCCGWA, DDRSTOP);
        DDRSTOP.KEY.AC = DDRCHQ.KEY.AC;
        DDRSTOP.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO);
        T000_READ_DDTSTOP();
        if (WS_READ_STOP_FLG == 'Y') {
            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
            JIBS_tmp_int = WS_CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
            WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CUR_POS - 1) + "6" + WS_CHQ_STS.substring(WS_CUR_POS + 1 - 1);
        } else {
            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
            JIBS_tmp_int = WS_CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
            WS_CHQ_STS = WS_CHQ_STS.substring(0, WS_CUR_POS - 1) + "1" + WS_CHQ_STS.substring(WS_CUR_POS + 1 - 1);
        }
    }
    public void R000_GET_CHQ_VALID_DT_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPCHQ);
        DDCPCHQ.KEY.TYP = "PDD04";
        DDCPCHQ.KEY.CD = "001";
        BPCPRMR.DAT_PTR = DDCPCHQ;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, BPCPRMR);
        CEP.TRC(SCCGWA, DDCPCHQ);
        CEP.TRC(SCCGWA, DDCPCHQ.DATA_TXT.DAYS);
        DDCPCHQ.DATA_TXT.DAYS = (short) (DDCPCHQ.DATA_TXT.DAYS + 10);
    }
    public void R000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        DDCIMCHQ.DATA.AC = DDRCHQ.KEY.AC;
        DDCIMCHQ.DATA.STR_CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO;
        DDCIMCHQ.DATA.END_CHQ_NO = DDRCHQ.KEY.END_CHQ_NO;
        DDCIMCHQ.DATA.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
        DDCIMCHQ.DATA.CCY = DDRCHQ.CCY;
        DDCIMCHQ.DATA.CCY_TYPE = DDRCHQ.CCY_TYPE;
        DDCIMCHQ.DATA.CHQ_STS = DDRCHQ.CHQ_STS;
    }
    public void R000_CANCEL_JRNNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPJRN);
        IBS.init(SCCGWA, SCRJRN);
        SCRJRN.AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        SCRJRN.KEY.JRN_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        SCCPJRN.FUNC = '1';
        CEP.TRC(SCCGWA, SCRJRN.AC_DATE);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        S000_CALL_SCZPJRN();
        CEP.TRC(SCCGWA, SCRJRN.CANCEL_IND);
    }
    public void S000_CALL_SCZPJRN() throws IOException,SQLException,Exception {
        SCCPJRN.DATA_PTR = SCRJRN;
        SCCPJRN.DATA_LEN = 687;
        CEP.TRC(SCCGWA, "CALL SCZPJRN");
        IBS.CALLCPN(SCCGWA, K_CMP_INQ_JRN, SCCPJRN);
        CEP.TRC(SCCGWA, SCCPJRN.RC);
        if (SCCPJRN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCPJRN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            WS_ERR_INFO = "TYPE = " + DDCPCHQ.KEY.TYP + ",CODE = " + DDCPCHQ.KEY.CD;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOCLWD.RC);
        }
    }
    public void T000_STBR_AC_CCY_CHQ_PROC() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRCHQ.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCIMCHQ.DATA.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCIMCHQ.DATA.END_CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void T000_STBR_STOP_BY_CHQ_PROC() throws IOException,SQLException,Exception {
        WS_STOP_FLG = 'N';
        IBS.init(SCCGWA, DDRSTOP);
        DDRSTOP.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRSTOP.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        DDTSTOP_BR.rp = new DBParm();
        DDTSTOP_BR.rp.TableName = "DDTSTOP";
        IBS.STARTBR(SCCGWA, DDRSTOP, DDTSTOP_BR);
    }
    public void T000_GET_NEXT_STOP_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRSTOP, this, DDTSTOP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STOP_FLG = 'Y';
        } else {
            WS_STOP_FLG = 'N';
        }
    }
    public void T000_END_BRW_STOP_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTSTOP_BR);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIMCHQ.DATA.AC;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS_WORD";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTSTOP() throws IOException,SQLException,Exception {
        DDTSTOP_RD = new DBParm();
        DDTSTOP_RD.TableName = "DDTSTOP";
        DDTSTOP_RD.where = "AC = :DDRSTOP.KEY.AC "
            + "AND CHQ_TYP = :DDRSTOP.CHQ_TYP "
            + "AND VCA_STS = '6' "
            + "AND VCA_FLG = 'Y'";
        DDTSTOP_RD.upd = true;
        IBS.READ(SCCGWA, DDRSTOP, this, DDTSTOP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_STOP_FLG = 'Y';
        } else {
            WS_READ_STOP_FLG = 'N';
        }
    }
    public void T000_READ_DDTSCHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSCHQ);
        DDRSCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRSCHQ.KEY.TYP = '2';
        DDRSCHQ.KEY.CHQ_NO = DDCIMCHQ.DATA.CHQ_NO;
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.col = "CHQ_STS,AMT";
        IBS.READ(SCCGWA, DDRSCHQ, DDTSCHQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SCHQ_FLG = 'Y';
        } else {
            WS_SCHQ_FLG = 'N';
            if (WS_LEN == 8) {
                CEP.TRC(SCCGWA, "WUYIPING TEST1");
                DDRSCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
                DDRSCHQ.KEY.TYP = '2';
                DDRSCHQ.KEY.CHQ_NO = DDCIMCHQ.DATA.CHQ_NO;
                DDTSCHQ_RD = new DBParm();
                DDTSCHQ_RD.TableName = "DDTSCHQ";
                DDTSCHQ_RD.col = "CHQ_STS,AMT";
                DDTSCHQ_RD.where = "AC = :DDRSCHQ.KEY.AC "
                    + "AND TYP = :DDRSCHQ.KEY.TYP "
                    + "AND SUBSTR ( CHQ_NO , 9 , 8 ) = :DDRSCHQ.KEY.CHQ_NO";
                IBS.READ(SCCGWA, DDRSCHQ, this, DDTSCHQ_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_SCHQ_FLG = 'Y';
                } else {
                    WS_SCHQ_FLG = 'N';
                }
            }
            if (WS_LEN == 16) {
                CEP.TRC(SCCGWA, "WUYIPING TEST2");
                DDRSCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
                DDRSCHQ.KEY.TYP = '2';
                DDRSCHQ.KEY.CHQ_NO = DDCIMCHQ.DATA.CHQ_NO;
                DDTSCHQ_RD = new DBParm();
                DDTSCHQ_RD.TableName = "DDTSCHQ";
                DDTSCHQ_RD.col = "CHQ_STS,AMT";
                DDTSCHQ_RD.where = "AC = :DDRSCHQ.KEY.AC "
                    + "AND TYP = :DDRSCHQ.KEY.TYP "
                    + "AND SUBSTR ( CHQ_NO , 1 , 8 ) = SUBSTR ( :DDRSCHQ.KEY.CHQ_NO , 9 , 8 )";
                IBS.READ(SCCGWA, DDRSCHQ, this, DDTSCHQ_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_SCHQ_FLG = 'Y';
                } else {
                    WS_SCHQ_FLG = 'N';
                }
            }
        }
    }
    public void T000_READ_DDTSCHQ1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSCHQ);
        DDRSCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRSCHQ.KEY.TYP = '1';
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.col = "CHQ_STS,AMT";
        DDTSCHQ_RD.where = "AC = :DDRSCHQ.KEY.AC "
            + "AND TYP = :DDRSCHQ.KEY.TYP";
        IBS.READ(SCCGWA, DDRSCHQ, this, DDTSCHQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SCHQ_FLG = 'Y';
        } else {
            WS_SCHQ_FLG = 'N';
        }
    }
    public void T000_WRITE_DDTCHQ() throws IOException,SQLException,Exception {
        DDRCHQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        DDTCHQ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCHQ, DDTCHQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_DDTPAID() throws IOException,SQLException,Exception {
        DDTPAID_RD = new DBParm();
        DDTPAID_RD.TableName = "DDTPAID";
        DDTPAID_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRPAID, DDTPAID_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAID_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_DDTPAID() throws IOException,SQLException,Exception {
        DDTPAID_RD = new DBParm();
        DDTPAID_RD.TableName = "DDTPAID";
        DDTPAID_RD.upd = true;
        IBS.READ(SCCGWA, DDRPAID, DDTPAID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DDTPAID_RD = new DBParm();
            DDTPAID_RD.TableName = "DDTPAID";
            IBS.DELETE(SCCGWA, DDRPAID, DDTPAID_RD);
        }
    }
    public void T000_READUP_DDTCHQ() throws IOException,SQLException,Exception {
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        DDTCHQ_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQ, DDTCHQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTCHQ() throws IOException,SQLException,Exception {
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        DDTCHQ_RD.upd = true;
        IBS.READ(SCCGWA, DDRCHQ, DDTCHQ_RD);
    }
    public void T000_REWRITE_DDTCHQ() throws IOException,SQLException,Exception {
        DDRCHQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCHQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCHQ_RD = new DBParm();
        DDTCHQ_RD.TableName = "DDTCHQ";
        IBS.REWRITE(SCCGWA, DDRCHQ, DDTCHQ_RD);
    }
    public void T000_WRITE_DDTSTOP() throws IOException,SQLException,Exception {
        DDTSTOP_RD = new DBParm();
        DDTSTOP_RD.TableName = "DDTSTOP";
        DDTSTOP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRSTOP, DDTSTOP_RD);
    }
    public void T000_READUP_DDTSTOP() throws IOException,SQLException,Exception {
        WS_READ_STOP_FLG = 'N';
        DDTSTOP_RD = new DBParm();
        DDTSTOP_RD.TableName = "DDTSTOP";
        DDTSTOP_RD.upd = true;
        IBS.READ(SCCGWA, DDRSTOP, DDTSTOP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_STOP_FLG = 'Y';
        } else {
            WS_READ_STOP_FLG = 'N';
        }
    }
    public void T000_REWRITE_DDTSTOP() throws IOException,SQLException,Exception {
        DDTSTOP_RD = new DBParm();
        DDTSTOP_RD.TableName = "DDTSTOP";
        IBS.REWRITE(SCCGWA, DDRSTOP, DDTSTOP_RD);
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRCHQ.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCIMCHQ.DATA.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCIMCHQ.DATA.END_CHQ_NO;
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_STARTBR_DDTCHQ1() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCIMCHQ.DATA.AC;
        DDRCHQ.KEY.CHQ_TYP = DDCIMCHQ.DATA.CHQ_TYP;
        if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
        JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
        if (DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, 8).trim().length() == 0) {
            if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            DDRCHQ.KEY.STR_CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO.substring(0, 9 - 1) + DDCIMCHQ.DATA.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1) + DDRCHQ.KEY.STR_CHQ_NO.substring(9 + 8 - 1);
        }
        if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
        JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
        if (DDCIMCHQ.DATA.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) {
            if (DDCIMCHQ.DATA.STR_CHQ_NO == null) DDCIMCHQ.DATA.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            DDRCHQ.KEY.STR_CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO.substring(0, 9 - 1) + DDCIMCHQ.DATA.STR_CHQ_NO.substring(0, 8) + DDRCHQ.KEY.STR_CHQ_NO.substring(9 + 8 - 1);
        }
        if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
        JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
        if (DDCIMCHQ.DATA.END_CHQ_NO.substring(0, 8).trim().length() == 0) {
            if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
            if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
            DDRCHQ.KEY.END_CHQ_NO = DDRCHQ.KEY.END_CHQ_NO.substring(0, 9 - 1) + DDCIMCHQ.DATA.END_CHQ_NO.substring(9 - 1, 9 + 8 - 1) + DDRCHQ.KEY.END_CHQ_NO.substring(9 + 8 - 1);
        }
        if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
        JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
        if (DDCIMCHQ.DATA.END_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) {
            if (DDCIMCHQ.DATA.END_CHQ_NO == null) DDCIMCHQ.DATA.END_CHQ_NO = "";
            JIBS_tmp_int = DDCIMCHQ.DATA.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCIMCHQ.DATA.END_CHQ_NO += " ";
            if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
            DDRCHQ.KEY.END_CHQ_NO = DDRCHQ.KEY.END_CHQ_NO.substring(0, 9 - 1) + DDCIMCHQ.DATA.END_CHQ_NO.substring(0, 8) + DDRCHQ.KEY.END_CHQ_NO.substring(9 + 8 - 1);
        }
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCIMCHQ.DATA.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP "
            + "AND SUBSTR ( STR_CHQ_NO , 9 , 8 ) <= SUBSTR ( :DDRCHQ.KEY.END_CHQ_NO , 9 , 8 ) "
            + "AND SUBSTR ( END_CHQ_NO , 9 , 8 ) >= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 9 , 8 )";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        BPZFFTXI BPZFFTXI = new BPZFFTXI();
        BPZFFTXI.MP(SCCGWA, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        BPZFCALF BPZFCALF = new BPZFCALF();
        BPZFCALF.MP(SCCGWA, BPCTCALF);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
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
