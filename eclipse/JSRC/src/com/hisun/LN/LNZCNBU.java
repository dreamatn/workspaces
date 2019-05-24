package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCNBU {
    String JIBS_tmp_str[] = new String[10];
    brParm LNTBALD_BR = new brParm();
    boolean pgmRtn = false;
    char LNZCNBU_FILLER1 = ' ';
    int WS_I = 0;
    short WS_NO = 0;
    double WS_AMT = 0;
    double WS_S_AMT = 0;
    short WS_STS_NO = 0;
    LNZCNBU_WS_CNTA_KEY WS_CNTA_KEY = new LNZCNBU_WS_CNTA_KEY();
    LNZCNBU_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZCNBU_WS_LOAN_CONT_AREA();
    long WS_BALD_JRN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRBALD LNRBALD = new LNRBALD();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCBALHM LNCBALHM = new LNCBALHM();
    LNCRBALD LNCRBALD = new LNCRBALD();
    SCCGWA SCCGWA;
    LNCCNBU LNCCNBU;
    public void MP(SCCGWA SCCGWA, LNCCNBU LNCCNBU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCNBU = LNCCNBU;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCNBU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCCNBU.RC.RC_APP = "LN";
        LNCCNBU.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCCNBU.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCNBU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_CNTL_INF();
        if (pgmRtn) return;
        B200_CONT_BAL_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_CNTL_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCNBU.COMM_DATA.CNTL_ID);
        IBS.init(SCCGWA, LNCCNTLM);
        LNCCNTLM.FUNC = 'I';
        LNCCNTLM.KEY.TYP = "CNTL";
        LNCCNTLM.KEY.CD = IBS.CLS2CPY(SCCGWA, LNCCNBU.COMM_DATA.CNTL_ID);
        S000_CALL_LNZCNTLM();
        if (pgmRtn) return;
        WS_NO = LNCCNTLM.DATA_TXT.NO;
        CEP.TRC(SCCGWA, WS_NO);
    }
    public void B200_CONT_BAL_PROC() throws IOException,SQLException,Exception {
        B210_CONT_BALL_UPD();
        if (pgmRtn) return;
        if (LNCCNTLM.DATA_TXT.HIST_FLG == 'Y') {
            B220_CONT_BALH_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_CONT_BALL_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '4';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        CEP.TRC(SCCGWA, "=======REWRITE CNBU-BAL=======");
        CEP.TRC(SCCGWA, LNCCNBU.COMM_DATA.AMT);
        CEP.TRC(SCCGWA, "=======CNBU-AS-IND=======");
        CEP.TRC(SCCGWA, LNCCNBU.COMM_DATA.AS_IND);
        LNCBALLM.FUNC = '2';
        CEP.TRC(SCCGWA, LNCCNBU.COMM_DATA.RVS_IND);
        CEP.TRC(SCCGWA, "LNZCNBU DISPLAY NCB000");
        CEP.TRC(SCCGWA, WS_NO);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES16.WS_LOAN_CONT[WS_NO-1].WS_LOAN_BAL);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_S_AMT = LNCCNBU.COMM_DATA.AMT * ( -1 );
        } else {
            WS_S_AMT = LNCCNBU.COMM_DATA.AMT;
        }
        if (LNCCNBU.COMM_DATA.AS_IND == 'A') {
            WS_LOAN_CONT_AREA.REDEFINES16.WS_LOAN_CONT[WS_NO-1].WS_LOAN_BAL += WS_S_AMT;
        } else {
            if (LNCCNBU.COMM_DATA.AS_IND == 'S') {
                WS_LOAN_CONT_AREA.REDEFINES16.WS_LOAN_CONT[WS_NO-1].WS_LOAN_BAL -= WS_S_AMT;
            } else {
                if (LNCCNBU.COMM_DATA.AS_IND == 'I') {
                    WS_LOAN_CONT_AREA.REDEFINES16.WS_LOAN_CONT[WS_NO-1].WS_LOAN_BAL = LNCCNBU.COMM_DATA.AMT;
                    WS_LOAN_CONT_AREA.WS_LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES16);
                }
            }
        }
        CEP.TRC(SCCGWA, LNCCNBU.COMM_DATA.AS_IND);
        CEP.TRC(SCCGWA, WS_S_AMT);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES16.WS_LOAN_CONT[WS_NO-1].WS_LOAN_BAL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], LNCBALLM.REC_DATA.REDEFINES18);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA);
    }
    public void B220_CONT_BALH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALHM);
        LNCBALHM.FUNC = '3';
        LNCBALHM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        LNCBALHM.REC_DATA.KEY.CTNR_NO = WS_NO;
        LNCBALHM.REC_DATA.KEY.TXN_DT = LNCCNBU.COMM_DATA.VAL_DATE;
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALH-MAINT", LNCBALHM);
        if (LNCBALHM.RC.RC_RTNCODE == 0) {
            B221_CONT_BALH_UPD();
            if (pgmRtn) return;
            B223_CONT_BALD_ADD();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALHM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_BALH_NOTFND)) {
                B222_CONT_BALH_ADD();
                if (pgmRtn) return;
                B223_CONT_BALD_ADD();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALHM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B221_CONT_BALH_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALHM);
        LNCBALHM.FUNC = '4';
        LNCBALHM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        LNCBALHM.REC_DATA.KEY.CTNR_NO = WS_NO;
        LNCBALHM.REC_DATA.KEY.TXN_DT = LNCCNBU.COMM_DATA.VAL_DATE;
        S000_CALL_LNZBALHM();
        if (pgmRtn) return;
        if (LNCCNBU.COMM_DATA.AS_IND == LNCBALHM.REC_DATA.ID_FLG) {
            LNCBALHM.REC_DATA.AMT += WS_S_AMT;
        } else {
            LNCBALHM.REC_DATA.AMT -= WS_S_AMT;
        }
        if (LNCCNBU.COMM_DATA.AS_IND == 'A') {
            LNCBALHM.REC_DATA.BAL += WS_S_AMT;
        }
        if (LNCCNBU.COMM_DATA.AS_IND == 'S') {
            LNCBALHM.REC_DATA.BAL -= WS_S_AMT;
        }
        if ((LNCBALHM.REC_DATA.AMT != 0) 
            || (LNCBALHM.REC_DATA.AMT == 0 
            && LNCBALHM.REC_DATA.KEY.TXN_DT == LNCCONTM.REC_DATA.START_DATE)) {
            LNCBALHM.FUNC = '2';
        } else {
            LNCBALHM.FUNC = '1';
        }
        S000_CALL_LNZBALHM();
        if (pgmRtn) return;
    }
    public void B222_CONT_BALH_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALHM);
        LNCBALHM.FUNC = '0';
        LNCBALHM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        LNCBALHM.REC_DATA.KEY.CTNR_NO = WS_NO;
        LNCBALHM.REC_DATA.KEY.TXN_DT = LNCCNBU.COMM_DATA.VAL_DATE;
        LNCBALHM.REC_DATA.ID_FLG = LNCCNBU.COMM_DATA.AS_IND;
        LNCBALHM.REC_DATA.AMT = WS_S_AMT;
        LNCBALHM.REC_DATA.BAL = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[WS_NO-1].LOAN_BAL;
        S000_CALL_LNZBALHM();
        if (pgmRtn) return;
    }
    public void B223_CONT_BALD_ADD() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B300_GET_MAX_JRN_NO();
            if (pgmRtn) return;
            WS_BALD_JRN += 1;
            B225_CONT_BALD_ADD();
            if (pgmRtn) return;
        } else {
            WS_BALD_JRN = SCCGWA.COMM_AREA.JRN_NO;
            B224_ONLLINE_BALD_ADD();
            if (pgmRtn) return;
        }
    }
    public void B224_ONLLINE_BALD_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALD);
        IBS.init(SCCGWA, LNCRBALD);
        LNCRBALD.FUNC = 'I';
        LNRBALD.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNRBALD.KEY.SUB_CTA_NO = 0;
        else LNRBALD.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        LNRBALD.KEY.CTNR_NO = WS_NO;
        LNRBALD.KEY.TXN_AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.KEY.JRN_NO = WS_BALD_JRN;
        LNRBALD.KEY.TXN_VAL_DT = LNCCNBU.COMM_DATA.VAL_DATE;
        LNCRBALD.REC_PTR = LNRBALD;
        LNCRBALD.REC_LEN = 175;
        S000_CALL_LNZRBALD();
        if (pgmRtn) return;
        if (LNCRBALD.RETURN_INFO == 'F') {
            B300_GET_MAX_JRN_NO();
            if (pgmRtn) return;
            WS_BALD_JRN += 1;
            B225_CONT_BALD_ADD();
            if (pgmRtn) return;
        } else {
            B225_CONT_BALD_ADD();
            if (pgmRtn) return;
        }
    }
    public void B225_CONT_BALD_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALD);
        IBS.init(SCCGWA, LNCRBALD);
        LNCRBALD.FUNC = 'A';
        LNRBALD.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNRBALD.KEY.SUB_CTA_NO = 0;
        else LNRBALD.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        LNRBALD.KEY.CTNR_NO = WS_NO;
        LNRBALD.KEY.TXN_AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.KEY.JRN_NO = WS_BALD_JRN;
        LNRBALD.KEY.TXN_VAL_DT = LNCCNBU.COMM_DATA.VAL_DATE;
        LNRBALD.TXN_FLG = LNCCNBU.COMM_DATA.TXN_FLG;
        LNRBALD.ACM_EVCD = LNCCNBU.COMM_DATA.ACM_EVCD;
        LNRBALD.ID_FLG = LNCCNBU.COMM_DATA.AS_IND;
        LNRBALD.AMT = WS_S_AMT;
        LNRBALD.BAL = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[WS_NO-1].LOAN_BAL;
        LNRBALD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRBALD.REC_PTR = LNRBALD;
        LNCRBALD.REC_LEN = 175;
        S000_CALL_LNZRBALD();
        if (pgmRtn) return;
    }
    public void B300_GET_MAX_JRN_NO() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = 'N';
        T00_STARTBR_LNTBALD();
        if (pgmRtn) return;
        T00_READNEXT_LNTBALD();
        if (pgmRtn) return;
        WS_BALD_JRN = LNRBALD.KEY.JRN_NO;
        T00_ENDBR_LNTBALD();
        if (pgmRtn) return;
    }
    public void B230_CONT_ACCT_UPD() throws IOException,SQLException,Exception {
        B230_GET_LOAN_INF();
        if (pgmRtn) return;
        B231_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B230_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
    }
    public void B231_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_STS_NO = 1;
    }
    public void B234_CNTL_ACCT_PROC() throws IOException,SQLException,Exception {
        if (LNCCNTLM.DATA_TXT.CALC_TYP == '0' 
            || LNCCNTLM.DATA_TXT.CALC_TYP == '1') {
            BS01_ACCT_ONL_PROC();
            if (pgmRtn) return;
        } else {
            BS02_ACCT_TWO_PROC();
            if (pgmRtn) return;
        }
    }
    public void BS01_ACCT_ONL_PROC() throws IOException,SQLException,Exception {
    }
    public void BS02_ACCT_TWO_PROC() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZCNTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTL-MAINT", LNCCNTLM);
        if (LNCCNTLM.RC.RC_RTNCODE != 0) {
            LNCCNBU.RC.RC_APP = LNCCNTLM.RC.RC_APP;
            LNCCNBU.RC.RC_RTNCODE = LNCCNTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCCNBU.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCCNBU.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            LNCCNBU.RC.RC_APP = LNCBALLM.RC.RC_APP;
            LNCCNBU.RC.RC_RTNCODE = LNCBALLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALH-MAINT", LNCBALHM);
        if (LNCBALHM.RC.RC_RTNCODE != 0) {
            LNCCNBU.RC.RC_APP = LNCBALHM.RC.RC_APP;
            LNCCNBU.RC.RC_RTNCODE = LNCBALHM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRBALD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRBALD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRBALD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRBALD.KEY.CTNR_NO);
        CEP.TRC(SCCGWA, LNRBALD.KEY.TXN_AC_DT);
        CEP.TRC(SCCGWA, LNRBALD.KEY.JRN_NO);
        CEP.TRC(SCCGWA, LNRBALD.KEY.TXN_VAL_DT);
        CEP.TRC(SCCGWA, LNRBALD.AMT);
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTBALD", LNCRBALD);
        CEP.TRC(SCCGWA, LNCRBALD.RETURN_INFO);
        CEP.TRC(SCCGWA, LNCRBALD.RC);
    }
    public void T00_STARTBR_LNTBALD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALD);
        LNRBALD.KEY.CONTRACT_NO = LNCCNBU.COMM_DATA.LN_AC;
        if (LNCCNBU.COMM_DATA.SUF_NO.trim().length() == 0) LNRBALD.KEY.SUB_CTA_NO = 0;
        else LNRBALD.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNBU.COMM_DATA.SUF_NO);
        LNRBALD.KEY.CTNR_NO = WS_NO;
        LNRBALD.KEY.TXN_AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.KEY.JRN_NO = 0;
        LNRBALD.KEY.TXN_VAL_DT = LNCCNBU.COMM_DATA.VAL_DATE;
        LNTBALD_BR.rp = new DBParm();
        LNTBALD_BR.rp.TableName = "LNTBALD";
        LNTBALD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTBALD_BR.rp.where = "SUB_CTA_NO = :LNRBALD.KEY.SUB_CTA_NO "
            + "AND CTNR_NO = :LNRBALD.KEY.CTNR_NO "
            + "AND TXN_AC_DT = :LNRBALD.KEY.TXN_AC_DT "
            + "AND JRN_NO >= :LNRBALD.KEY.JRN_NO "
            + "AND TXN_VAL_DT = :LNRBALD.KEY.TXN_VAL_DT";
        LNTBALD_BR.rp.order = "JRN_NO DESC";
        IBS.STARTBR(SCCGWA, LNRBALD, this, LNTBALD_BR);
    }
    public void T00_READNEXT_LNTBALD() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRBALD, this, LNTBALD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            LNRBALD.KEY.JRN_NO = 0;
        }
    }
    public void T00_ENDBR_LNTBALD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTBALD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCCNBU=");
            CEP.TRC(SCCGWA, LNCCNBU);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
