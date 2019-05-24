package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIOVD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_LN_R_LNZRRCVD = "LN-RSC-LNTRCVD";
    char K_CKPD_INQ = '0';
    int WS_RESP = 0;
    short WS_I = 0;
    short WS_IC_TERM = 0;
    short WS_ICTLM_IC_CUR_TERM = 0;
    short WS_ICTLM_IC_CAL_TERM = 0;
    short WS_ICTLM_P_CAL_TERM = 0;
    short WS_ICTLM_P_CUR_TERM = 0;
    int WS_IC_VAL_DT = 0;
    int WS_IC_DUE_DT = 0;
    double WS_IC_DUE_TODAY = 0;
    short WS_P_TERM = 0;
    int WS_P_VAL_DT = 0;
    int WS_P_DUE_DT = 0;
    double WS_P_DUE_TODAY = 0;
    double WS_TMP_AMT = 0;
    char WS_NORMAL_STS = ' ';
    char WS_OVERDUE_STS = ' ';
    int WS_CNT = 0;
    double WS_PRI_AMT_REPORT = 0;
    double WS_INT_AMT_REPORT = 0;
    int WS_DUE_DT = 0;
    int WS_WDAYS = 0;
    int WS_Q = 0;
    double WS_ROVD_P_AMT = 0;
    double WS_ROVD_I_AMT = 0;
    LNZIOVD_WS_ROVD_P_ID WS_ROVD_P_ID = new LNZIOVD_WS_ROVD_P_ID();
    LNZIOVD_WS_ROVD_I_ID WS_ROVD_I_ID = new LNZIOVD_WS_ROVD_I_ID();
    short WS_CUR_TERM = 0;
    int WS_CAL_YYYYMMDD = 0;
    LNZIOVD_REDEFINES36 REDEFINES36 = new LNZIOVD_REDEFINES36();
    char WS_MSG_FLG1 = ' ';
    String WS_D_CI_NO = " ";
    LNZIOVD_WS_JRN_NO WS_JRN_NO = new LNZIOVD_WS_JRN_NO();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_RCVD_FLG = ' ';
    char WS_STAS_FLG = ' ';
    char WS_RETURN_FLG = ' ';
    char WS_GRACE_TYP = ' ';
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCICUT LNCICUT = new LNCICUT();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCSUBP LNCSUBP = new LNCSUBP();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCIHDCK LNCIHDCK = new LNCIHDCK();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCCNEX LNCCNEX = new LNCCNEX();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    LNCIOVD LNCIOVD;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, LNCIOVD LNCIOVD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIOVD = LNCIOVD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIOVD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        LNCIOVD.RC.RC_APP = "LN";
        LNCIOVD.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, "111111");
        CEP.TRC(SCCGWA, LNCIOVD.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCIOVD.COMM_DATA.VAL_DATE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_DATA();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (!LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            B200_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCIOVD.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCIOVD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B100_GET_CONT_INF();
        if (pgmRtn) return;
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B100_GET_CONT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.REPORT.ACCEPT_FLG == 'Y') {
            LNCCONTM.REC_DATA.CONTRACT_TYPE = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_CONTRACT_TYPE;
            LNCCONTM.REC_DATA.PROD_CD = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_PROD_CD;
            LNCCONTM.REC_DATA.CCY = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_CCY;
            LNCCONTM.REC_DATA.BOOK_BR = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_BOOK_BR;
            LNCCONTM.REC_DATA.SYS_FLG = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_SYS_FLG;
            LNCCONTM.REC_DATA.CTL_FLG = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_CTL_FLG;
            LNCCONTM.REC_DATA.MAT_DATE = LNCIOVD.REPORT.ACCEPT_DATA.CONT_DATA.CONT_MAT_DATE;
        } else {
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        CEP.TRC(SCCGWA, LNCCONTM.RC);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.REPORT.ACCEPT_FLG == 'Y') {
            LNCAPRDM.REC_DATA.PAY_MTH = LNCIOVD.REPORT.ACCEPT_DATA.APRD_DATA.APRD_PAY_MTH;
            LNCAPRDM.REC_DATA.GRACE_TYP = LNCIOVD.REPORT.ACCEPT_DATA.APRD_DATA.APRD_GRACE_TYP;
            S000_CALL_LNZAPRDM();
            if (pgmRtn) return;
        }
        WS_GRACE_TYP = LNCAPRDM.REC_DATA.GRACE_TYP;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '3';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_ICTLM_IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        WS_ICTLM_IC_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        WS_ICTLM_P_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        WS_ICTLM_P_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        WS_MSG_FLG1 = LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).charAt(0);
    }
    public void B100_GET_PRIN_DOG_DT() throws IOException,SQLException,Exception {
        if (LNRRCVD.KEY.AMT_TYP == 'P') {
            WS_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        } else {
            WS_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        }
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        if (WS_CUR_TERM == LNRRCVD.KEY.TERM) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if ((LNRRCVD.KEY.AMT_TYP == 'P' 
                && LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                && (LNRRCVD.DUE_DT != LNCCONTM.REC_DATA.MAT_DATE)) 
                || (LNRRCVD.KEY.AMT_TYP == 'I' 
                && LNCAPRDM.REC_DATA.PAY_MTH != '4' 
                && LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                && (LNRRCVD.DUE_DT != LNCCONTM.REC_DATA.MAT_DATE)) 
                || (LNCAPRDM.REC_DATA.PAY_MTH == '4' 
                && (LNRRCVD.DUE_DT != LNCCONTM.REC_DATA.MAT_DATE))) {
                if (WS_GRACE_TYP == '1') {
                    WS_CAL_YYYYMMDD = LNRRCVD.DUE_DT;
                    IBS.CPY2CLS(SCCGWA, WS_CAL_YYYYMMDD+"", REDEFINES36);
                    R000_CAL_MONTH_END();
                    if (pgmRtn) return;
                    WS_DUE_DT = WS_CAL_YYYYMMDD;
                }
                if (WS_GRACE_TYP == '2') {
                    if (LNCAPRDM.REC_DATA.PRIN_DOG > 0) {
                        IBS.init(SCCGWA, SCCCLDT);
                        SCCCLDT.DATE1 = LNRRCVD.DUE_DT;
                        SCCCLDT.DAYS = LNCAPRDM.REC_DATA.PRIN_DOG;
                        S000_CALL_SCSSCLDT();
                        if (pgmRtn) return;
                        WS_DUE_DT = SCCCLDT.DATE2;
                    } else {
                        WS_DUE_DT = LNRRCVD.DUE_DT;
                    }
                }
                if (WS_GRACE_TYP == ' ') {
                    WS_DUE_DT = LNRRCVD.DUE_DT;
                }
            } else {
                WS_DUE_DT = LNRRCVD.DUE_DT;
            }
        } else {
            WS_DUE_DT = LNRRCVD.DUE_DT;
        }
        if (LNCSCKPD.VCT_FLG == 'Y') {
            IBS.init(SCCGWA, BPCOCLWD);
            if (LNCSCKPD.PROD_MOD == 'R') {
                BPCOCLWD.CAL_CODE = "PN";
            } else {
                BPCOCLWD.CAL_CODE = "CN";
            }
            BPCOCLWD.DATE1 = WS_DUE_DT;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = 1;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            WS_DUE_DT = BPCOCLWD.DATE2;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B203_INQ_DUE_TODAY_AMT();
        if (pgmRtn) return;
        B205_DUE_OVD_PROC();
        if (pgmRtn) return;
        B207_REVD_OVDPI_PROC();
        if (pgmRtn) return;
        B209_UPDATE_OVERDUE_STS();
        if (pgmRtn) return;
    }
    public void B201_INQ_P_DUE_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQOVDP";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
        S000_CALL_LNZICNQ();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, LNCICNQ.COMM_DATA.CNTR[WS_I-1].CNTL_ID);
        }
    }
    public void B201_INQ_IC_DUE_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICNQ);
        LNCICNQ.COMM_DATA.INQ_CODE = "INQOVDI";
        LNCICNQ.COMM_DATA.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
        else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
        S000_CALL_LNZICNQ();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, LNCICNQ.COMM_DATA.CNTR[WS_I-1].CNTL_ID);
        }
    }
    public void B203_INQ_DUE_TODAY_AMT() throws IOException,SQLException,Exception {
        WS_STAS_FLG = 'N';
        WS_IC_DUE_TODAY = 0;
        WS_P_DUE_TODAY = 0;
        if (LNCICTLM.REC_DATA.P_CUR_TERM < LNCICTLM.REC_DATA.P_CAL_TERM) {
            WS_RCVD_FLG = 'N';
            IBS.init(SCCGWA, LNCRRCVD);
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
            if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
            else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
            LNRRCVD.KEY.AMT_TYP = 'P';
            LNRRCVD.KEY.TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
            CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = '3';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'R';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
            if (LNCRRCVD.RC.RC_CODE == 0) {
                B100_GET_PRIN_DOG_DT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_DUE_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            while (WS_RCVD_FLG != 'Y') {
                CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
                CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.P_PAY_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
                if (LNRRCVD.TERM_STS != '1' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && LNRRCVD.REPY_STS != '2') {
                    LNCRRCVD.FUNC = 'R';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    LNCRRCVD.FUNC = 'U';
                    LNRRCVD.OVD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    LNRRCVD.TERM_STS = '1';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                }
                if (LNRRCVD.REPY_STS != '2' 
                    && LNRRCVD.TERM_STS == '1') {
                    WS_STAS_FLG = 'Y';
                }
                if (LNRRCVD.REPY_STS != '2' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    WS_STAS_FLG = 'Y';
                    CEP.TRC(SCCGWA, WS_STAS_FLG);
                    WS_P_DUE_TODAY = WS_P_DUE_TODAY + LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
                    CEP.TRC(SCCGWA, WS_P_DUE_TODAY);
                }
                if (LNRRCVD.TERM_STS != '1' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && LNRRCVD.REPY_STS != '2') {
                    LNCRRCVD.FUNC = 'R';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    LNCRRCVD.FUNC = 'U';
                    LNRRCVD.OVD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    LNRRCVD.TERM_STS = '1';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CAL_TERM);
                if (LNRRCVD.KEY.TERM >= LNCICTLM.REC_DATA.P_CAL_TERM - 1) {
                    WS_RCVD_FLG = 'Y';
                } else {
                    LNCRRCVD.FUNC = 'B';
                    LNCRRCVD.OPT = 'R';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    if (LNRRCVD.DUE_DT > LNRRCVD.OVD_DT) {
                        WS_DUE_DT = LNRRCVD.DUE_DT;
                    } else {
                        WS_DUE_DT = LNRRCVD.OVD_DT;
                    }
                }
            }
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'E';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.IC_CUR_TERM < LNCICTLM.REC_DATA.IC_CAL_TERM) {
            WS_RCVD_FLG = 'N';
            IBS.init(SCCGWA, LNCRRCVD);
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
            if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
            else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                LNRRCVD.KEY.AMT_TYP = 'C';
            } else {
                LNRRCVD.KEY.AMT_TYP = 'I';
            }
            LNRRCVD.KEY.TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
            CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = '3';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'R';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            if (LNCRRCVD.RC.RC_CODE == 0) {
                B100_GET_PRIN_DOG_DT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_DUE_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            while (WS_RCVD_FLG != 'Y') {
                CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
                CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.P_PAY_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_PAY_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.REPY_STS);
                if (LNRRCVD.TERM_STS != '1' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && LNRRCVD.REPY_STS != '2') {
                    LNCRRCVD.FUNC = 'R';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    LNCRRCVD.FUNC = 'U';
                    LNRRCVD.OVD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    LNRRCVD.TERM_STS = '1';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                }
                if (LNRRCVD.REPY_STS != '2' 
                    && LNRRCVD.TERM_STS == '1' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    WS_STAS_FLG = 'Y';
                }
                if (LNRRCVD.REPY_STS != '2' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.TRC(SCCGWA, "A33333");
                    WS_STAS_FLG = 'Y';
                    WS_P_DUE_TODAY = WS_P_DUE_TODAY + LNRRCVD.P_REC_AMT - LNRRCVD.P_PAY_AMT;
                    CEP.TRC(SCCGWA, WS_P_DUE_TODAY);
                    WS_IC_DUE_TODAY = WS_IC_DUE_TODAY + LNRRCVD.I_REC_AMT - LNRRCVD.I_PAY_AMT - LNRRCVD.I_WAV_AMT;
                    CEP.TRC(SCCGWA, WS_IC_DUE_TODAY);
                }
                CEP.TRC(SCCGWA, WS_DUE_DT);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                if (LNRRCVD.TERM_STS != '1' 
                    && WS_DUE_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && LNRRCVD.REPY_STS != '2') {
                    LNCRRCVD.FUNC = 'R';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    LNCRRCVD.FUNC = 'U';
                    LNRRCVD.OVD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    LNRRCVD.TERM_STS = '1';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
                if (LNRRCVD.KEY.TERM >= LNCICTLM.REC_DATA.IC_CAL_TERM - 1) {
                    WS_RCVD_FLG = 'Y';
                } else {
                    LNCRRCVD.FUNC = 'B';
                    LNCRRCVD.OPT = 'R';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    if (LNRRCVD.DUE_DT > LNRRCVD.OVD_DT) {
                        WS_DUE_DT = LNRRCVD.DUE_DT;
                    } else {
                        WS_DUE_DT = LNRRCVD.OVD_DT;
                    }
                }
            }
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'E';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_P_DUE_TODAY);
            CEP.TRC(SCCGWA, WS_IC_DUE_TODAY);
        }
    }
    public void B205_DUE_OVD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        WS_PRI_AMT_REPORT = 0;
        WS_INT_AMT_REPORT = 0;
        LNCCNEV.COMM_DATA.ACM_EVENT = "NO";
        LNCCNEV.COMM_DATA.EVENT_CODE = "DUEOVDP";
        LNCCNEV.COMM_DATA.LN_AC = LNCIOVD.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCIOVD.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCIOVD.COMM_DATA.VAL_DATE;
        B201_INQ_P_DUE_AMT();
        if (pgmRtn) return;
        LNCCNEV.COMM_DATA.P_AMT = WS_P_DUE_TODAY - LNCICNQ.COMM_DATA.CNTR[2-1].CNTL_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = LNCCNEV.COMM_DATA.P_AMT;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
        WS_PRI_AMT_REPORT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT;
        B201_INQ_IC_DUE_AMT();
        if (pgmRtn) return;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_IC_DUE_TODAY - LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_AMT;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
        WS_INT_AMT_REPORT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
        if (LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT < 0) {
            LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = 0;
        }
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        LNCCNEV.COMM_DATA.I_AMT += LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT);
        CEP.TRC(SCCGWA, LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT);
        WS_RETURN_FLG = ' ';
        if (LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT > 0 
            || LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT > 0) {
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
            WS_RETURN_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, "WS-RETURN-FLG");
        CEP.TRC(SCCGWA, WS_RETURN_FLG);
        CEP.TRC(SCCGWA, LNCIOVD.COMM_DATA.LN_AC);
        if (WS_RETURN_FLG == 'Y') {
            IBS.init(SCCGWA, LNCCONTM);
            LNCCONTM.FUNC = '4';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            LNCCONTM.FUNC = '2';
            LNCCONTM.REC_DATA.OVER_TIME = (short) (LNCCONTM.REC_DATA.OVER_TIME + 1);
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.OVER_TIME);
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        }
    }
    public void B207_REVD_OVDPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQOVDP";
        LNCCNEX.COMM_DATA.LN_AC = LNCIOVD.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCIOVD.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ROVD_P_ID);
        WS_ROVD_P_AMT = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQOVDI";
        LNCCNEX.COMM_DATA.LN_AC = LNCIOVD.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCIOVD.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ROVD_I_ID);
        WS_ROVD_I_AMT = LNCCNEX.COMM_DATA.CTNR_DATA[3-1].CTNR_AMT;
        if (WS_ROVD_P_AMT != 0 
            || WS_ROVD_I_AMT != 0) {
            IBS.init(SCCGWA, LNCCNEV);
            LNCCNEV.COMM_DATA.ACM_EVENT = "ON";
            LNCCNEV.COMM_DATA.EVENT_CODE = "DUEOVDP";
            LNCCNEV.COMM_DATA.LN_AC = LNCIOVD.COMM_DATA.LN_AC;
            LNCCNEV.COMM_DATA.SUF_NO = LNCIOVD.COMM_DATA.SUF_NO;
            LNCCNEV.COMM_DATA.VAL_DATE = LNCIOVD.COMM_DATA.VAL_DATE;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_ROVD_P_AMT;
            LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_ROVD_I_AMT;
            LNCCNEV.COMM_DATA.RVS_IND = 'N';
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
        }
    }
    public void B209_UPDATE_OVERDUE_STS() throws IOException,SQLException,Exception {
        WS_NORMAL_STS = '0';
        WS_OVERDUE_STS = '0';
        CEP.TRC(SCCGWA, WS_STAS_FLG);
        if (WS_STAS_FLG == 'N') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (!LNCICTLM.REC_DATA.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_NORMAL_STS = '1';
            }
        } else {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (!LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_OVERDUE_STS = '1';
            }
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(0, 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
        CEP.TRC(SCCGWA, WS_NORMAL_STS);
        CEP.TRC(SCCGWA, WS_OVERDUE_STS);
        if (WS_NORMAL_STS == '1' 
            || WS_OVERDUE_STS == '1') {
            BS02_READUPD_ICTL();
            if (pgmRtn) return;
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            JIBS_tmp_str[0] = "" + WS_NORMAL_STS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCICTLM.REC_DATA.CTL_STSW = JIBS_tmp_str[0] + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            JIBS_tmp_str[0] = "" + WS_OVERDUE_STS;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + JIBS_tmp_str[0] + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
            BS03_REWRITE_ICTL();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            if (WS_MSG_FLG1 == '0' 
                && WS_OVERDUE_STS == '1') {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = LNCIOVD.COMM_DATA.LN_AC;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                WS_D_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
                T000_SEND_MESSAGE();
                if (pgmRtn) return;
            }
        }
        B000_WRITE_OVER_REPORT();
        if (pgmRtn) return;
    }
    public void B000_WRITE_OVER_REPORT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.KEY.CONTRACT_NO);
        IBS.init(SCCGWA, LNCIOVD.REPORT);
        LNCIOVD.REPORT.CTA_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
        LNCIOVD.REPORT.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        LNCIOVD.REPORT.CCY = LNCCONTM.REC_DATA.CCY;
        LNCIOVD.REPORT.P_AMT = WS_PRI_AMT_REPORT;
        LNCIOVD.REPORT.I_AMT = WS_INT_AMT_REPORT;
        if (WS_PRI_AMT_REPORT != 0) {
            LNCIOVD.REPORT.DUE_DT = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
        }
        if (WS_INT_AMT_REPORT != 0) {
            LNCIOVD.REPORT.DUE_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        }
        LNCIOVD.REPORT.BR = LNCCONTM.REC_DATA.BOOK_BR;
        CEP.TRC(SCCGWA, "??????????");
        CEP.TRC(SCCGWA, WS_RETURN_FLG);
        LNCIOVD.REPORT.FLG = WS_RETURN_FLG;
    }
    public void R000_CAL_MONTH_END() throws IOException,SQLException,Exception {
        if (REDEFINES36.WS_CAL_MM == 02) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = WS_CAL_YYYYMMDD;
            R000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            if (SCCCKDT.LEAP_YEAR == 1) {
                REDEFINES36.WS_CAL_DD = 29;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES36);
                WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            } else {
                REDEFINES36.WS_CAL_DD = 28;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES36);
                WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        }
        if (REDEFINES36.WS_CAL_MM == 01 
            || REDEFINES36.WS_CAL_MM == 03 
            || REDEFINES36.WS_CAL_MM == 05 
            || REDEFINES36.WS_CAL_MM == 07 
            || REDEFINES36.WS_CAL_MM == 08 
            || REDEFINES36.WS_CAL_MM == 10 
            || REDEFINES36.WS_CAL_MM == 12) {
            REDEFINES36.WS_CAL_DD = 31;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES36);
            WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (REDEFINES36.WS_CAL_MM == 04 
            || REDEFINES36.WS_CAL_MM == 06 
            || REDEFINES36.WS_CAL_MM == 09 
            || REDEFINES36.WS_CAL_MM == 11) {
            REDEFINES36.WS_CAL_DD = 30;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES36);
            WS_CAL_YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
        }
    }
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCIOVD.RC.RC_APP = "SC";
            LNCIOVD.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void BS02_READUPD_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void BS03_REWRITE_ICTL() throws IOException,SQLException,Exception {
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCAPRDM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCAPRDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCLOANM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCLOANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCICNQ.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCICNQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCVDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        if (LNCRCVDM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTLM-MAINT", LNCCNTLM);
        if (LNCCNTLM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCCNTLM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCCNTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 477;
        IBS.CALLCPN(SCCGWA, CPN_LN_R_LNZRRCVD, LNCRRCVD);
        CEP.TRC(SCCGWA, LNCRRCVD.RETURN_INFO);
        if (LNCRRCVD.RC.RC_CODE != 0) {
            if (LNCRRCVD.RETURN_INFO == 'E') {
                CEP.TRC(SCCGWA, "RRCVD-RC-CODE =");
                CEP.TRC(SCCGWA, LNCRRCVD.RC.RC_CODE);
                WS_RCVD_FLG = 'Y';
            } else {
                LNCIOVD.RC.RC_APP = LNCRRCVD.RC.RC_MMO;
                LNCIOVD.RC.RC_RTNCODE = LNCRRCVD.RC.RC_CODE;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCIOVD.RC.RC_APP = "SC";
            LNCIOVD.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIHDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-HOLDAY-CHK", LNCIHDCK);
        if (LNCIHDCK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIHDCK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCIOVD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCIOVD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_SEND_MESSAGE() throws IOException,SQLException,Exception {
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCWRMSG);
        CEP.TRC(SCCGWA, WS_JRN_NO);
        CEP.TRC(SCCGWA, WS_D_CI_NO);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.SEQ = 1;
        SCCWRMSG.AP_CODE = "32";
        SCCWRMSG.CI_NO = WS_D_CI_NO;
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
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CI-INQ-ACAC";
        SCCCALL.COMMAREA_PTR = CICQACAC;
        SCCCALL.NOFMT_FLG = 'Y';
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
        if (SCCWRMSG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCWRMSG-RC = ");
            CEP.TRC(SCCGWA, SCCWRMSG.RC);
        }
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIOVD.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCIOVD=");
            CEP.TRC(SCCGWA, LNCIOVD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
