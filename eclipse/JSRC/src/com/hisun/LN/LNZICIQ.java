package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICIQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAGRE_RD;
    brParm LNTSETL_BR = new brParm();
    brParm LNTEXTN_BR = new brParm();
    brParm LNTRCVD_BR = new brParm();
    DBParm LNTBALZ_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_CLDD = "CLDD";
    String K_CLGU = "CLGU";
    String K_CLDL = "CLDL";
    char K_CKPD_INQ = '0';
    String WS_ERR_MSG = " ";
    String WS_OUTPUT_FMT = " ";
    int WS_I = 0;
    int WS_II = 0;
    double WS_TOT_BAL = 0;
    int WS_RCVD_DUE_DT = 0;
    int WS_TMP_FST_DATE = 0;
    int WS_DT = 0;
    int WS_CNT = 0;
    int WS_CNTS = 0;
    double WS_O_CUT_AMT = 0;
    double WS_L_CUT_AMT = 0;
    LNZICIQ_WS_OUT_DATA WS_OUT_DATA = new LNZICIQ_WS_OUT_DATA();
    LNZICIQ_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZICIQ_WS_LOAN_CONT_AREA();
    LNZICIQ_WS_DUE_DT_OUT WS_DUE_DT_OUT = new LNZICIQ_WS_DUE_DT_OUT();
    char WS_END_FILE = ' ';
    char WS_NOTFND_FLAG = ' ';
    char WS_CONT_FLAG = ' ';
    char WS_RCVD_FLG = ' ';
    char WS_ERR_FLAG = ' ';
    char WS_RCVD_FLG2 = ' ';
    LNCQ61 LNCQ61 = new LNCQ61();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCRGMST LNCRGMST = new LNCRGMST();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCRDARL LNCRDARL = new LNCRDARL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCRCMEQ LNCRCMEQ = new LNCRCMEQ();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCSLNQ LNCSLNQ = new LNCSLNQ();
    LNCILDFS LNCILDFS = new LNCILDFS();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCICSTS LNCICSTS = new LNCICSTS();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    LNCXP08 LNCXP08 = new LNCXP08();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCRWOFF LNCRWOFF = new LNCRWOFF();
    LNREXTN LNREXTN = new LNREXTN();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    LNCICIQ LNCICIQ;
    public void MP(SCCGWA SCCGWA, LNCICIQ LNCICIQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICIQ = LNCICIQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICIQ.RC.RC_MMO = "LN";
        LNCICIQ.RC.RC_CODE = 0;
        IBS.init(SCCGWA, LNCSLNQ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICIQ.FUNC);
        CEP.TRC(SCCGWA, LNCICIQ.ICQ_OPT);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.COMM_NO);
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CONT_NO);
        if (LNCICIQ.ICQ_OPT == 'P') {
            B100_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B200_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AFTER  CALL PPMQ 1");
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNCICIQ.DATA.PROD_CD;
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        if (LNCICIQ.FUNC == 'N') {
            IBS.init(SCCGWA, LNCQ61);
            IBS.init(SCCGWA, SCCFMT);
            LNCQ61.PRODMO = LNCSCKPD.PRODMO;
            LNCQ61.HOL_FLG = LNCSCKPD.VCT_FLG;
            LNCQ61.PAY_LEVL = LNCSCKPD.PAY_LEVL;
            SCCFMT.FMTID = "LNQ61";
            SCCFMT.DATA_PTR = LNCQ61;
            SCCFMT.DATA_LEN = 2312;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        B010_CONT_RECORD_PROC();
        if (pgmRtn) return;
        if (WS_CONT_FLAG == 'Y') {
            B005_GET_LOAN_BASEINF();
            if (pgmRtn) return;
            B010_NESTING_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            if (WS_OUT_DATA.WS_COMM_NO.trim().length() > 0) {
                B010_NESTING_CMMTINF_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCICIQ.DATA.CNTR_NO.trim().length() > 0 
            && LNCICIQ.DATA.DRWSEQ != 0 
            && LNCICIQ.DATA.CONT_NO.trim().length() == 0) {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.DRAW_NO = LNCICIQ.DATA.CNTR_NO;
            LNRAGRE.PAPER_NO = "" + LNCICIQ.DATA.DRWSEQ;
            JIBS_tmp_int = LNRAGRE.PAPER_NO.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNRAGRE.PAPER_NO = "0" + LNRAGRE.PAPER_NO;
            LNRAGRE.CTA_FROM = '0';
            T000_READ_LNTAGRE();
            if (pgmRtn) return;
            LNCICIQ.DATA.CONT_NO = LNRAGRE.KEY.CONTRACT_NO;
        }
        if (LNCICIQ.DATA.CONT_NO.trim().length() == 0 
            && LNCICIQ.DATA.COMM_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CONT_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_COMM_NO = LNCICIQ.DATA.COMM_NO;
        WS_OUT_DATA.WS_CONT_NO = LNCICIQ.DATA.CONT_NO;
        if (LNCICIQ.DATA.SUB_CONT_NO.trim().length() == 0) WS_OUT_DATA.WS_SUB_CONT_NO = 0;
        else WS_OUT_DATA.WS_SUB_CONT_NO = Integer.parseInt(LNCICIQ.DATA.SUB_CONT_NO);
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
        LNCRCONT.FUNC = 'I';
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B010_NESTING_CMMTINF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCMMT);
        IBS.init(SCCGWA, LNCRCMMT);
        LNRCMMT.KEY.CONTRACT_NO = WS_OUT_DATA.WS_COMM_NO;
        LNCRCMMT.FUNC = 'I';
        S000_CALL_LNZRCMMT();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_PROD_CD = LNRCMMT.PROD_CD;
        WS_OUT_DATA.WS_DOMI_BR = LNRCMMT.DOMI_BR;
        WS_OUT_DATA.WS_TRAN_DTE = LNRCMMT.UPDTBL_DATE;
        WS_OUT_DATA.WS_REMARK1 = LNRCMMT.REMARK1;
        WS_OUT_DATA.WS_RTIO_VAL_DATE = LNRCMMT.RTIO_VAL_DATE;
        WS_OUT_DATA.WS_RTIO_VAL_DATE_NO = LNRCMMT.RTIO_VAL_DATE_NO;
        WS_OUT_DATA.WS_REVOLVING_FLG = LNRCMMT.REVOLVING_FLAG;
        WS_OUT_DATA.WS_JBR = LNRCMMT.BOOK_BR;
        WS_OUT_DATA.WS_BR = LNRCMMT.DOMI_BR;
        WS_OUT_DATA.WS_CCY = LNRCMMT.CTA_CCY;
        WS_OUT_DATA.WS_AMT = LNRCMMT.CTA_AMT;
        if (WS_OUT_DATA.WS_EXTEND_EXP_DATE == 0) {
            WS_OUT_DATA.WS_EXP_DATE = WS_OUT_DATA.WS_DUE_DT;
        } else {
            WS_OUT_DATA.WS_EXP_DATE = WS_OUT_DATA.WS_EXTEND_EXP_DATE;
        }
        if (LNRCMMT.STATUS == null) LNRCMMT.STATUS = "";
        JIBS_tmp_int = LNRCMMT.STATUS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNRCMMT.STATUS += " ";
        if (LNRCMMT.STATUS.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUT_DATA.WS_STS = 'M';
        } else {
            if (LNRCMMT.STATUS == null) LNRCMMT.STATUS = "";
            JIBS_tmp_int = LNRCMMT.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) LNRCMMT.STATUS += " ";
            if ((LNRCMMT.STATUS.substring(0, 1).equalsIgnoreCase("1"))) {
                WS_OUT_DATA.WS_STS = 'A';
            } else {
                if (LNRCMMT.STATUS == null) LNRCMMT.STATUS = "";
                JIBS_tmp_int = LNRCMMT.STATUS.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) LNRCMMT.STATUS += " ";
                if ((LNRCMMT.STATUS.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
                    WS_OUT_DATA.WS_STS = 'B';
                } else {
                    if (LNRCMMT.STATUS == null) LNRCMMT.STATUS = "";
                    JIBS_tmp_int = LNRCMMT.STATUS.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) LNRCMMT.STATUS += " ";
                    if (LNRCMMT.STATUS.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_OUT_DATA.WS_STS = 'P';
                    } else {
                        WS_OUT_DATA.WS_STS = 'A';
                    }
                }
            }
        }
        B011_GET_CI_INF();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_PRINCIPAL = LNRCMMT.CTA_AMT;
        WS_OUT_DATA.WS_PRIN_EQU = LNRCMMT.CTA_AMT;
        WS_TOT_BAL = WS_OUT_DATA.WS_OS_BAL;
        WS_OUT_DATA.WS_EQU_COM_AMT = WS_OUT_DATA.WS_OS_BAL;
        if (LNCICIQ.FUNC == 'N') {
            WS_OUTPUT_FMT = "LNQ61";
            B300_OUTPUT_PROCESS();
            if (pgmRtn) return;
            SCCFMT.FMTID = WS_OUTPUT_FMT;
            SCCFMT.DATA_PTR = LNCQ61;
            SCCFMT.DATA_LEN = 2312;
            IBS.FMT(SCCGWA, SCCFMT);
        }
        if (LNCICIQ.FUNC == 'M') {
            B310_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_NESTING_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_FATHER_CONTRACT = LNRCONT.FATHER_CONTRACT;
        WS_OUT_DATA.WS_FATHER_CONTRACT = LNRCONT.FATHER_CONTRACT;
        WS_OUT_DATA.WS_RTIO_VAL_DATE = LNRCONT.ORI_MAT_DATE;
        WS_OUT_DATA.WS_CONTRACT_TYPE = LNRCONT.CONTRACT_TYPE;
        WS_OUT_DATA.WS_DOMI_BR = LNRCONT.DOMI_BR;
        WS_OUT_DATA.WS_TRAN_DTE = LNRCONT.TRAN_DTE;
        WS_OUT_DATA.WS_TRAN_JRN = LNRCONT.TRAN_JRN;
        CEP.TRC(SCCGWA, "AAAAAAA");
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD) 
            || LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDL)) {
            IBS.init(SCCGWA, LNRICTL);
            IBS.init(SCCGWA, LNCRICTL);
            LNRICTL.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
            LNRICTL.KEY.SUB_CTA_NO = WS_OUT_DATA.WS_SUB_CONT_NO;
            LNCRICTL.FUNC = 'I';
            if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD) 
                || LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDL)) {
                S000_CALL_LNZRICTL();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
            LNCICSTS.COMM_DATA.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
            LNCICSTS.COMM_DATA.CTL_STSW = LNRICTL.CTL_STSW;
            S000_CALL_LNZICSTS();
            if (pgmRtn) return;
            WS_OUT_DATA.WS_STS = LNCICSTS.COMM_DATA.STS;
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_STS);
            if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD)) {
                IBS.init(SCCGWA, LNRLOAN);
                IBS.init(SCCGWA, LNCRLOAN);
                LNRLOAN.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
                LNCRLOAN.FUNC = 'I';
                S000_CALL_LNZRLOAN();
                if (pgmRtn) return;
                WS_OUT_DATA.WS_CONT_NO = LNRLOAN.KEY.CONTRACT_NO;
                WS_OUT_DATA.WS_ST_PAY_AMT = LNRLOAN.AUTO_AMT;
                WS_OUT_DATA.WS_BRAT_EFF_DT = LNRLOAN.BRAT_EFF_DT;
                WS_OUT_DATA.WS_ORAT_EFF_DT = LNRLOAN.ORAT_EFF_DT;
                WS_OUT_DATA.WS_LRAT_EFF_DT = LNRLOAN.LRAT_EFF_DT;
            }
        }
        WS_OUT_DATA.WS_PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CI_NO);
        WS_OUT_DATA.WS_JBR = LNRCONT.BOOK_BR;
        WS_OUT_DATA.WS_BR = LNRCONT.DOMI_BR;
        WS_OUT_DATA.WS_CCY = LNRCONT.CCY;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CCY);
        WS_OUT_DATA.WS_AMT = LNRCONT.LN_TOT_AMT;
        WS_OUT_DATA.WS_TOT_AMT = LNRCONT.ORIG_TOT_AMT;
        WS_OUT_DATA.WS_VAL_DT = LNRCONT.START_DATE;
        WS_OUT_DATA.WS_DUE_DT = LNRCONT.MAT_DATE;
        WS_OUT_DATA.WS_EXTEND_EXP_DATE = LNRCONT.MAT_DATE;
        WS_OUT_DATA.WS_OPEN_BK = "" + LNRCONT.DOMI_BR;
        JIBS_tmp_int = WS_OUT_DATA.WS_OPEN_BK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_OUT_DATA.WS_OPEN_BK = "0" + WS_OUT_DATA.WS_OPEN_BK;
        CEP.TRC(SCCGWA, LNRCONT.CONTRACT_TYPE);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_BRAT_EFF_DT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_BRAT_EFF_DT);
        if (WS_OUT_DATA.WS_EXTEND_EXP_DATE == 0) {
            WS_OUT_DATA.WS_EXP_DATE = WS_OUT_DATA.WS_DUE_DT;
        } else {
            WS_OUT_DATA.WS_EXP_DATE = WS_OUT_DATA.WS_EXTEND_EXP_DATE;
        }
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_EXP_DATE);
        B011_GET_CI_INF();
        if (pgmRtn) return;
        if (LNCICIQ.ICQ_OPT == 'R' 
            || LNCICIQ.ICQ_OPT == 'R') {
            WS_RCVD_FLG = 'N';
            WS_RCVD_DUE_DT = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, LNCRRCVD);
            IBS.init(SCCGWA, LNRRCVD);
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CONT_NO);
            LNRRCVD.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
            LNRRCVD.KEY.SUB_CTA_NO = WS_OUT_DATA.WS_SUB_CONT_NO;
            LNRRCVD.TERM_STS = '1';
            LNRRCVD.DUE_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'N';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'R';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            while (WS_RCVD_FLG != 'Y') {
                if (LNRRCVD.REPY_STS != '2') {
                    WS_RCVD_DUE_DT = LNRRCVD.DUE_DT;
                    WS_OUT_DATA.WS_OC_REC = LNRRCVD.L_REC_AMT;
                    WS_OUT_DATA.WS_OC_PAID = LNRRCVD.L_PAY_AMT;
                    WS_RCVD_FLG = 'Y';
                }
                S000_CALL_LNZRRCVD();
                if (pgmRtn) return;
            }
            LNCRRCVD.FUNC = 'B';
            LNCRRCVD.OPT = 'E';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_RCVD_DUE_DT;
            SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
            R00_CAL_DATE();
            if (pgmRtn) return;
            LNCILDFS.INPUT_AREA.CI_BREACH_TYP = '2';
            LNCILDFS.INPUT_AREA.CI_BREACH_TYP = '0';
            if (SCCCLDT.DAYS < 90) {
                LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP = '0';
            }
            if (SCCCLDT.DAYS >= 90 
                && SCCCLDT.DAYS < 180) {
                LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP = '1';
            }
            if (SCCCLDT.DAYS >= 180) {
                LNCILDFS.INPUT_AREA.CTA_OVERDUE_TYP = '2';
            }
            S000_CALL_LNZILDFS();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0138865") 
            && !LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && LNRCONT.MAT_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, LNCSSCHE);
            WS_OUT_DATA.WS_CU_INT = 0;
            WS_OUT_DATA.WS_CU_PRIN = 0;
            WS_OUT_DATA.WS_CU_DATE = 0;
            LNCSSCHE.DATA_AREA.CONTRACT_NO = LNCICIQ.DATA.CONT_NO;
            LNCSSCHE.CUR_FLG = 'Y';
            S000_CALL_LNZSSCHE();
            if (pgmRtn) return;
            for (WS_II = 1; WS_II <= 2; WS_II += 1) {
                if (WS_OUT_DATA.WS_CU_DATE == 0) {
                    WS_OUT_DATA.WS_CU_DATE = LNCSSCHE.OUT_INFO[WS_II-1].O_DUE_DATE;
                } else {
                    if (LNCSSCHE.OUT_INFO[WS_II-1].O_PAY_TYP == 'I') {
                        WS_OUT_DATA.WS_CU_DATE = LNCSSCHE.OUT_INFO[WS_II-1].O_DUE_DATE;
                    }
                }
                WS_OUT_DATA.WS_CU_PRIN += LNCSSCHE.OUT_INFO[WS_II-1].O_PAY_PRIN;
                WS_OUT_DATA.WS_CU_INT += LNCSSCHE.OUT_INFO[WS_II-1].O_PAY_INT;
                CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_II-1].O_PAY_TYP);
                CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_II-1].O_DUE_DATE);
                CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_II-1].O_PAY_PRIN);
                CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_II-1].O_PAY_INT);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CU_INT);
            }
        }
        B012_GET_SETLINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "YE0000");
        WS_OUT_DATA.WS_EQU_COM_AMT = WS_TOT_BAL;
        WS_OUT_DATA.WS_EQU_COM_AMT = LNCSLNQ.COMM_DATA.TOT_BAL;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_COM_AMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_PAYED_AMT);
        CEP.TRC(SCCGWA, WS_TOT_BAL);
        CEP.TRC(SCCGWA, "CMMT");
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD) 
            || LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDL)) {
            IBS.init(SCCGWA, LNRICTL);
            IBS.init(SCCGWA, LNCRICTL);
            LNRICTL.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
            LNRICTL.KEY.SUB_CTA_NO = WS_OUT_DATA.WS_SUB_CONT_NO;
            LNCRICTL.FUNC = 'I';
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1));
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1") 
                && LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD)) {
                WS_OUT_DATA.WS_STOP_INT_DATE = LNRLOAN.STOP_VAL_DT;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDL)) {
                    IBS.init(SCCGWA, LNRRCVD);
                    IBS.init(SCCGWA, LNCRRCVD);
                    WS_OUT_DATA.WS_CAC_DT = LNRCONT.LAST_F_VAL_DATE;
                    LNRRCVD.KEY.TERM = LNRICTL.P_CUR_TERM;
                    LNRRCVD.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
                    LNRRCVD.KEY.SUB_CTA_NO = 0;
                    LNRRCVD.KEY.AMT_TYP = 'P';
                    LNRRCVD.KEY.SUBS_PROJ_NO = 0;
                    LNCRRCVD.FUNC = 'I';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    WS_OUT_DATA.WS_ODUE_DT = LNRRCVD.OVD_DT;
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_ODUE_DT);
                } else {
                    IBS.init(SCCGWA, LNRRCVD);
                    IBS.init(SCCGWA, LNCRRCVD);
                    WS_OUT_DATA.WS_CAC_DT = LNRCONT.LAST_F_VAL_DATE;
                    LNRRCVD.KEY.TERM = LNRICTL.IC_CUR_TERM;
                    LNRRCVD.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
                    LNRRCVD.KEY.SUB_CTA_NO = 0;
                    LNRRCVD.KEY.AMT_TYP = 'I';
                    LNRRCVD.KEY.SUBS_PROJ_NO = 0;
                    LNCRRCVD.FUNC = 'I';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    WS_DUE_DT_OUT.WS_DUE_DT_1 = LNRRCVD.OVD_DT;
                    LNRRCVD.KEY.AMT_TYP = 'C';
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    WS_DUE_DT_OUT.WS_DUE_DT_2 = LNRRCVD.OVD_DT;
                    LNRRCVD.KEY.AMT_TYP = 'P';
                    LNRRCVD.KEY.TERM = LNRICTL.P_CUR_TERM;
                    S000_CALL_LNZRRCVD();
                    if (pgmRtn) return;
                    WS_DUE_DT_OUT.WS_DUE_DT_3 = LNRRCVD.OVD_DT;
                    CEP.TRC(SCCGWA, WS_DUE_DT_OUT.WS_DUE_DT_1);
                    CEP.TRC(SCCGWA, WS_DUE_DT_OUT.WS_DUE_DT_2);
                    CEP.TRC(SCCGWA, WS_DUE_DT_OUT.WS_DUE_DT_3);
                    if (WS_DUE_DT_OUT.WS_DUE_DT_1 < WS_DUE_DT_OUT.WS_DUE_DT_2 
                        && WS_DUE_DT_OUT.WS_DUE_DT_1 != 0) {
                        WS_DUE_DT_OUT.WS_DUE_DT_2 = WS_DUE_DT_OUT.WS_DUE_DT_1;
                    }
                    if (WS_DUE_DT_OUT.WS_DUE_DT_2 == 0) {
                        WS_DUE_DT_OUT.WS_DUE_DT_2 = WS_DUE_DT_OUT.WS_DUE_DT_1;
                    }
                    CEP.TRC(SCCGWA, WS_DUE_DT_OUT.WS_DUE_DT_2);
                    if (WS_DUE_DT_OUT.WS_DUE_DT_2 < WS_DUE_DT_OUT.WS_DUE_DT_3 
                        && WS_DUE_DT_OUT.WS_DUE_DT_2 != 0) {
                        WS_DUE_DT_OUT.WS_DUE_DT_3 = WS_DUE_DT_OUT.WS_DUE_DT_2;
                    }
                    if (WS_DUE_DT_OUT.WS_DUE_DT_3 == 0) {
                        WS_DUE_DT_OUT.WS_DUE_DT_3 = WS_DUE_DT_OUT.WS_DUE_DT_2;
                    }
                    CEP.TRC(SCCGWA, WS_DUE_DT_OUT.WS_DUE_DT_3);
                    WS_OUT_DATA.WS_ODUE_DT = WS_DUE_DT_OUT.WS_DUE_DT_3;
                }
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, LNRWOFF);
                IBS.init(SCCGWA, LNCRWOFF);
                LNRWOFF.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
                LNRWOFF.KEY.TYPE = '2';
                LNCRWOFF.FUNC = 'I';
                S000_CALL_LNZRWOFF();
                if (pgmRtn) return;
                WS_OUT_DATA.WS_CAN_DATE = LNRWOFF.CAN_DATE;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, LNRWOFF);
                IBS.init(SCCGWA, LNCRWOFF);
                LNRWOFF.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
                LNRWOFF.KEY.TYPE = '1';
                LNCRWOFF.FUNC = 'I';
                S000_CALL_LNZRWOFF();
                if (pgmRtn) return;
                WS_OUT_DATA.WS_CAN_DATE = LNRWOFF.CAN_DATE;
            }
            WS_OUT_DATA.WS_ODUE_RAT = LNRICTL.CUR_PO_RAT;
            WS_OUT_DATA.WS_ADD_ON_RATE = LNRICTL.CUR_RAT;
        }
        WS_OUT_DATA.WS_P_CMP_TERM = LNRICTL.P_CMP_TERM;
        WS_OUT_DATA.WS_IC_CMP_TERM = LNRICTL.IC_CMP_TERM;
        WS_OUT_DATA.WS_P_CMP_TERM = (short) (WS_OUT_DATA.WS_P_CMP_TERM - 1);
        WS_OUT_DATA.WS_IC_CMP_TERM = (short) (WS_OUT_DATA.WS_IC_CMP_TERM - 1);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_P_CMP_TERM);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_IC_CMP_TERM);
        WS_OUT_DATA.WS_I_VAL_DT = LNRICTL.IC_CAL_VAL_DT;
        CEP.TRC(SCCGWA, LNCICIQ.DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNRCONT.CONTRACT_TYPE);
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_PAY_MTH = LNCAPRDM.REC_DATA.BPAY_MTH;
        WS_OUT_DATA.WS_PAY_MTH2 = LNCAPRDM.REC_DATA.PAY_MTH;
        CEP.TRC(SCCGWA, "LYYYYYY");
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_PAY_MTH);
        WS_OUT_DATA.WS_PDOG_FLG = LNCAPRDM.REC_DATA.PRIN_DOG_MTH;
        WS_OUT_DATA.WS_GDA_DB_SEQ = LNCAPRDM.REC_DATA.GDA_DB_SEQ;
        WS_OUT_DATA.WS_CAL_PERD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        WS_OUT_DATA.WS_CAL_PERD = LNCAPRDM.REC_DATA.CAL_PERD;
        if (LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
            WS_OUT_DATA.WS_CHG_DB_FLG = '2';
        } else {
            WS_OUT_DATA.WS_CHG_DB_FLG = LNCAPRDM.REC_DATA.CHG_DB_FLG;
        }
        WS_OUT_DATA.WS_HAND_CHG_R = LNCAPRDM.REC_DATA.HAND_CHG_RATE;
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
        T000_STARTBR_LNTEXTN();
        if (pgmRtn) return;
        T000_READNEXT_LNTEXTN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_OUT_DATA.WS_EEDATE = LNREXTN.KEY.VAL_DT;
            WS_OUT_DATA.WS_EEAMT = LNREXTN.EXT_AMT;
        }
        T000_ENDBR_LNTEXTN();
        if (pgmRtn) return;
        if (WS_OUT_DATA.WS_PAY_MTH == '0') {
            IBS.init(SCCGWA, LNRBALZ);
            LNRBALZ.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
            T000_READ_BALZ();
            if (pgmRtn) return;
            WS_OUT_DATA.WS_RAT_AMT = LNRBALZ.LOAN_BALL25;
        }
        WS_OUT_DATA.WS_PERD_FLG = LNCAPRDM.REC_DATA.PAY_DD_TYPE;
        CEP.TRC(SCCGWA, "TEST");
        B014_GET_CNTR_NO();
        if (pgmRtn) return;
        B015_GET_ALL_INT();
        if (pgmRtn) return;
        B016_GET_REAL_TIME_INT();
        if (pgmRtn) return;
        if (LNCICIQ.FUNC == 'N') {
            WS_OUTPUT_FMT = "LNQ61";
            B300_OUTPUT_PROCESS();
            if (pgmRtn) return;
            SCCFMT.FMTID = WS_OUTPUT_FMT;
            SCCFMT.DATA_PTR = LNCQ61;
            SCCFMT.DATA_LEN = 2312;
            IBS.FMT(SCCGWA, SCCFMT);
            CEP.TRC(SCCGWA, LNCQ61.BR);
        }
        if (LNCICIQ.FUNC == 'M') {
            B310_OUTPUT_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCICIQ.DATA.COM_AMT);
            CEP.TRC(SCCGWA, LNCICIQ.DATA.EQU_COM_AMT);
            CEP.TRC(SCCGWA, LNCICIQ.DATA.PAYED_AMT);
        }
    }
    public void B011_GET_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_OUT_DATA.WS_CONT_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ATTR);
        WS_OUT_DATA.WS_CI_NO = CICACCU.DATA.CI_NO;
        WS_OUT_DATA.WS_CI_TYP = CICACCU.DATA.CI_TYP;
        WS_OUT_DATA.WS_CI_ATTR = CICACCU.DATA.CI_ATTR;
        WS_OUT_DATA.WS_CITY_CD = "TMP CITY";
        WS_OUT_DATA.WS_CI_CNAME = CICACCU.DATA.CI_CNM;
    }
    public void B012_GET_SETLINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = WS_OUT_DATA.WS_CONT_NO;
        WS_CNTS = 1;
        T000_STARTBR_LNTSETL();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            for (WS_CNT = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_CNTS <= 5 
                && WS_CNT <= 50000; WS_CNT += 1) {
                CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
                CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
                CEP.TRC(SCCGWA, LNRSETL.AC);
                if (LNRSETL.KEY.SETTLE_TYPE == '1') {
                    CEP.TRC(SCCGWA, LNRSETL.AC);
                    WS_OUT_DATA.WS_PAY_DD_AC = LNRSETL.AC;
                    WS_OUT_DATA.WS_PAY_SETTLE_METHOD = LNRSETL.AC_TYP;
                }
                CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
                CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
                CEP.TRC(SCCGWA, LNRSETL.AC);
                if (LNRSETL.KEY.SETTLE_TYPE == '2') {
                    WS_OUT_DATA.WS_REC_DD_AC = LNRSETL.AC;
                    WS_OUT_DATA.WS_REC_SETTLE_METHOD = LNRSETL.AC_TYP;
                }
                if (LNRSETL.KEY.SETTLE_TYPE == '4') {
                    WS_OUT_DATA.WS_FUND_CTA_NO = LNRSETL.AC;
                }
                if (LNRSETL.KEY.SETTLE_TYPE == 'B') {
                    WS_OUT_DATA.WS_RAT_CTA_NO = LNRSETL.AC;
                }
                if (LNRSETL.MWHD_AC_FLG == 'N' 
                    && LNRSETL.KEY.SETTLE_TYPE == '2') {
                    WS_OUT_DATA.WS_AC_DATA[1-1].WS_REC_STL_METH = LNRSETL.KEY.SETTLE_TYPE;
                    WS_OUT_DATA.WS_AC_DATA[1-1].WS_REC_STL_DDAC = LNRSETL.AC;
                    WS_OUT_DATA.WS_AC_DATA[1-1].WS_REC_AC_TYP = LNRSETL.AC_TYP;
                    WS_OUT_DATA.WS_AC_DATA[1-1].WS_PAY_CCY = LNRSETL.KEY.CCY;
                }
                if (LNRSETL.MWHD_AC_FLG == 'Y') {
                    WS_OUT_DATA.WS_AC_DATA[WS_CNTS-1].WS_REC_STL_METH = LNRSETL.KEY.SETTLE_TYPE;
                    WS_OUT_DATA.WS_AC_DATA[WS_CNTS-1].WS_REC_STL_DDAC = LNRSETL.AC;
                    WS_OUT_DATA.WS_AC_DATA[WS_CNTS-1].WS_REC_AC_TYP = LNRSETL.AC_TYP;
                    WS_OUT_DATA.WS_AC_DATA[WS_CNTS-1].WS_PAY_CCY = LNRSETL.KEY.CCY;
                    WS_CNTS += 1;
                }
                T000_READNEXT_LNTSETL();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
    }
    public void B014_GET_CNTR_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCICIQ.DATA.CONT_NO;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, LNRAGRE.PAPER_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_OUT_DATA.WS_ACC_CNT = LNRAGRE.PAPER_NO;
            WS_OUT_DATA.WS_CNTR_NO = LNRAGRE.DRAW_NO;
        }
    }
    public void B015_GET_ALL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCICIQ.DATA.CONT_NO;
        if (LNCICIQ.DATA.SUB_CONT_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCICIQ.DATA.SUB_CONT_NO);
        T000_STARTBR_LNTRCVD();
        if (pgmRtn) return;
        T000_READNEX_LNTRCVD();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_O_REC_LC = 0;
        WS_OUT_DATA.WS_O_ACC_LC = 0;
        WS_OUT_DATA.WS_O_REC_OC = 0;
        WS_OUT_DATA.WS_O_ACC_OC = 0;
        while (WS_RCVD_FLG2 != 'N') {
            if (LNRRCVD.REPY_STS != '2') {
                WS_OUT_DATA.WS_O_RCV_INT = 0;
                WS_OUT_DATA.WS_O_RCV_LC = 0;
                WS_OUT_DATA.WS_O_RCV_RINT = 0;
                WS_O_CUT_AMT = 0;
                WS_L_CUT_AMT = 0;
                WS_OUT_DATA.WS_O_RCV_INT += LNRRCVD.I_REC_AMT;
                WS_OUT_DATA.WS_O_REC_LC += LNRRCVD.O_LST_CAL_AMT;
                WS_O_CUT_AMT = LNRRCVD.O_PAY_AMT + LNRRCVD.O_WAV_AMT;
                if (WS_O_CUT_AMT > LNRRCVD.O_LST_CAL_AMT) {
                    WS_O_CUT_AMT = WS_O_CUT_AMT - LNRRCVD.O_LST_CAL_AMT;
                    WS_OUT_DATA.WS_O_REC_LC = 0;
                } else {
                    WS_OUT_DATA.WS_O_REC_LC -= LNRRCVD.O_PAY_AMT;
                    WS_OUT_DATA.WS_O_REC_LC -= LNRRCVD.O_WAV_AMT;
                    WS_O_CUT_AMT = 0;
                }
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_REC_LC);
                WS_OUT_DATA.WS_O_REC_OC += LNRRCVD.L_LST_CAL_AMT;
                WS_L_CUT_AMT = LNRRCVD.L_PAY_AMT + LNRRCVD.L_WAV_AMT;
                if (WS_L_CUT_AMT > LNRRCVD.L_LST_CAL_AMT) {
                    WS_L_CUT_AMT = WS_L_CUT_AMT - LNRRCVD.L_LST_CAL_AMT;
                    WS_OUT_DATA.WS_O_REC_OC = 0;
                } else {
                    WS_OUT_DATA.WS_O_REC_OC -= LNRRCVD.L_PAY_AMT;
                    WS_OUT_DATA.WS_O_REC_OC -= LNRRCVD.L_WAV_AMT;
                    WS_L_CUT_AMT = 0;
                }
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_REC_OC);
            }
            T000_READNEX_LNTRCVD();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTRCVD();
        if (pgmRtn) return;
    }
    public void B016_GET_REAL_TIME_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCICIQ.DATA.CONT_NO;
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "" + 0;
        JIBS_tmp_int = LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "0" + LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCSPDQ.FUNC_CODE = 'E';
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TR_VAL_DATE);
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_O_ACC_INT = LNCSPDQ.COMM_DATA.CTA_INFO.D_I_AMT;
        WS_OUT_DATA.WS_O_ACC_OC = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT - WS_OUT_DATA.WS_O_REC_OC;
        WS_OUT_DATA.WS_O_ACC_LC = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT - WS_OUT_DATA.WS_O_REC_LC;
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.CTA_INFO.D_I_AMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_ACC_INT);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_REC_OC);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_ACC_OC);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_REC_LC);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_ACC_LC);
    }
    public void B005_GET_LOAN_BASEINF() throws IOException,SQLException,Exception {
        LNCSLNQ.COMM_DATA.LN_AC = LNCICIQ.DATA.CONT_NO;
        LNCSLNQ.COMM_DATA.SUF_NO = LNCICIQ.DATA.SUB_CONT_NO;
        S000_CALL_LNZSLNQ();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_OS_BAL = LNCSLNQ.COMM_DATA.TOT_BAL;
        WS_OUT_DATA.WS_ODUE_AMT = LNCSLNQ.COMM_DATA.O_BAL;
        WS_OUT_DATA.WS_PRINCIPAL = LNCSLNQ.COMM_DATA.PRIN;
        WS_OUT_DATA.WS_PRIN_EQU = LNCSLNQ.COMM_DATA.PRIN_EQU;
        WS_OUT_DATA.WS_INT_REC = LNCSLNQ.COMM_DATA.DUEINT;
        WS_OUT_DATA.WS_INT_PAID = LNCSLNQ.COMM_DATA.RCVINT;
        WS_OUT_DATA.WS_LC_REC = LNCSLNQ.COMM_DATA.DUELC;
        WS_OUT_DATA.WS_LC_PAID = LNCSLNQ.COMM_DATA.RCVLC;
        WS_OUT_DATA.WS_LI_REC = LNCSLNQ.COMM_DATA.DUELI;
        WS_OUT_DATA.WS_LI_PAID = LNCSLNQ.COMM_DATA.RCVLI;
        WS_OUT_DATA.WS_ACCRUAL_TYPE = LNCSLNQ.COMM_DATA.ACCRUAL_TYPE;
        WS_OUT_DATA.WS_WAV_LC_FLG = LNCSLNQ.COMM_DATA.WAV_LC_FLG;
        WS_OUT_DATA.WS_INST_MAX_TERM = LNCSLNQ.COMM_DATA.INST_MAX_TERM;
        WS_OUT_DATA.WS_INT_DAY_BAS = LNCSLNQ.COMM_DATA.INT_DAY_BAS;
        WS_OUT_DATA.WS_CI_NO = LNCSLNQ.COMM_DATA.CI_NO;
        CEP.TRC(SCCGWA, LNCSLNQ.COMM_DATA.DUEINT);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCQ61);
        IBS.init(SCCGWA, SCCFMT);
        LNCQ61.COMM_NO = WS_OUT_DATA.WS_FATHER_CONTRACT;
        LNCQ61.RTIO_VAL_DATE = WS_OUT_DATA.WS_RTIO_VAL_DATE;
        LNCQ61.RTIO_VAL_DATE_NO = WS_OUT_DATA.WS_RTIO_VAL_DATE_NO;
        LNCQ61.CONTRACT_TYPE = WS_OUT_DATA.WS_CONTRACT_TYPE;
        LNCQ61.DOMI_BR = WS_OUT_DATA.WS_DOMI_BR;
        LNCQ61.EXCHANGE_RATE = WS_OUT_DATA.WS_EXCHANGE_RATE;
        LNCQ61.EQUIVALENT = WS_OUT_DATA.WS_EQUIVALENT;
        LNCQ61.TRAN_DTE = WS_OUT_DATA.WS_TRAN_DTE;
        LNCQ61.TRAN_JRN = WS_OUT_DATA.WS_TRAN_JRN;
        LNCQ61.REMARK1 = WS_OUT_DATA.WS_REMARK1;
        LNCQ61.CONT_NO = WS_OUT_DATA.WS_CONT_NO;
        LNCQ61.SUB_CONT_NO = "" + WS_OUT_DATA.WS_SUB_CONT_NO;
        JIBS_tmp_int = LNCQ61.SUB_CONT_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCQ61.SUB_CONT_NO = "0" + LNCQ61.SUB_CONT_NO;
        LNCQ61.STS = WS_OUT_DATA.WS_STS;
        LNCQ61.PROD_CD = WS_OUT_DATA.WS_PROD_CD;
        LNCQ61.CI_ATTR = WS_OUT_DATA.WS_CI_ATTR;
        LNCQ61.CI_ATTR = WS_OUT_DATA.WS_CI_TYP;
        LNCQ61.CI_NO = WS_OUT_DATA.WS_CI_NO;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_BR);
        LNCQ61.BR = WS_OUT_DATA.WS_BR;
        LNCQ61.JBR = WS_OUT_DATA.WS_JBR;
        LNCQ61.CCY = WS_OUT_DATA.WS_CCY;
        LNCQ61.AMT = WS_OUT_DATA.WS_AMT;
        LNCQ61.TOT_AMT = WS_OUT_DATA.WS_TOT_AMT;
        LNCQ61.VAL_DT = WS_OUT_DATA.WS_VAL_DT;
        LNCQ61.DUE_DT = WS_OUT_DATA.WS_DUE_DT;
        LNCQ61.EXTEND_REASON = WS_OUT_DATA.WS_EXTEND_REASON;
        LNCQ61.OPEN_BK = WS_OUT_DATA.WS_OPEN_BK;
        LNCQ61.FST_FLT_DT = WS_OUT_DATA.WS_FST_FLT_DT;
        LNCQ61.BRAT_EFF_DT = WS_OUT_DATA.WS_BRAT_EFF_DT;
        LNCQ61.ORAT_EFF_DT = WS_OUT_DATA.WS_ORAT_EFF_DT;
        LNCQ61.LRAT_EFF_DT = WS_OUT_DATA.WS_LRAT_EFF_DT;
        LNCQ61.CNTR_NO = WS_OUT_DATA.WS_CNTR_NO;
        LNCQ61.EQU_COM_AMT = WS_OUT_DATA.WS_EQU_COM_AMT;
        LNCQ61.EQUIVALENT = WS_OUT_DATA.WS_EQU_COM_AMT;
        LNCQ61.PAY_SETTLE_METHOD = WS_OUT_DATA.WS_PAY_SETTLE_METHOD;
        LNCQ61.PAY_DD_AC = WS_OUT_DATA.WS_PAY_DD_AC;
        LNCQ61.REC_SETTLE_METHOD = WS_OUT_DATA.WS_REC_SETTLE_METHOD;
        LNCQ61.REC_DD_AC = WS_OUT_DATA.WS_REC_DD_AC;
        LNCQ61.CI_SNAME = WS_OUT_DATA.WS_CI_SNAME;
        LNCQ61.CITY_CD = WS_OUT_DATA.WS_CITY_CD;
        LNCQ61.CI_CNAME = WS_OUT_DATA.WS_CI_CNAME;
        CEP.TRC(SCCGWA, LNCQ61.CI_CNAME);
        LNCQ61.PRINCIPAL = WS_OUT_DATA.WS_PRINCIPAL;
        LNCQ61.TOT_AMT = WS_OUT_DATA.WS_PRINCIPAL;
        LNCQ61.PRIN_EQU = WS_OUT_DATA.WS_PRIN_EQU;
        LNCQ61.INT_REC = WS_OUT_DATA.WS_INT_REC;
        LNCQ61.REC_INT = WS_OUT_DATA.WS_INT_REC;
        LNCQ61.REC_LC = WS_OUT_DATA.WS_O_REC_LC;
        LNCQ61.ACC_LC = WS_OUT_DATA.WS_O_ACC_LC;
        LNCQ61.REC_OC = WS_OUT_DATA.WS_O_REC_OC;
        LNCQ61.ACC_OC = WS_OUT_DATA.WS_O_ACC_OC;
        LNCQ61.CU_PRIN = WS_OUT_DATA.WS_CU_PRIN;
        LNCQ61.CU_INT = WS_OUT_DATA.WS_CU_INT;
        LNCQ61.CU_DATE = WS_OUT_DATA.WS_CU_DATE;
        LNCQ61.ACC_INT = WS_OUT_DATA.WS_O_ACC_INT;
        CEP.TRC(SCCGWA, LNCQ61.ACC_INT);
        LNCQ61.INT_PAID = WS_OUT_DATA.WS_INT_PAID;
        LNCQ61.LC_REC = WS_OUT_DATA.WS_LC_REC;
        LNCQ61.LC_PAID = WS_OUT_DATA.WS_LC_PAID;
        LNCQ61.OC_REC = WS_OUT_DATA.WS_LI_REC;
        LNCQ61.OC_PAID = WS_OUT_DATA.WS_LI_PAID;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUT_DATA.WS_OBS_INT = 0;
            WS_OUT_DATA.WS_OFS_INT = WS_OUT_DATA.WS_INT_REC + WS_OUT_DATA.WS_LC_REC + WS_OUT_DATA.WS_LI_REC;
        } else {
            WS_OUT_DATA.WS_OBS_INT = WS_OUT_DATA.WS_INT_REC + WS_OUT_DATA.WS_LC_REC;
            WS_OUT_DATA.WS_OFS_INT = WS_OUT_DATA.WS_LI_REC;
        }
        LNCQ61.OBS_INT = WS_OUT_DATA.WS_OBS_INT;
        LNCQ61.OFS_INT = WS_OUT_DATA.WS_OFS_INT;
        LNCQ61.ACCRUAL_TYPE = WS_OUT_DATA.WS_ACCRUAL_TYPE;
        LNCQ61.WAV_LC_FLG = WS_OUT_DATA.WS_WAV_LC_FLG;
        LNCQ61.INST_MAX_TERM = WS_OUT_DATA.WS_INST_MAX_TERM;
        LNCQ61.INT_DAY_BAS = WS_OUT_DATA.WS_INT_DAY_BAS;
        LNCQ61.BAL = WS_OUT_DATA.WS_OS_BAL;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            LNCQ61.NA_BAL = WS_OUT_DATA.WS_OS_BAL;
        }
        LNCQ61.EXP_DATE = WS_OUT_DATA.WS_EXP_DATE;
        LNCQ61.P_CMP_TERM = WS_OUT_DATA.WS_P_CMP_TERM;
        LNCQ61.IC_CMP_TERM = WS_OUT_DATA.WS_IC_CMP_TERM;
        LNCQ61.ADD_ON_RATE = WS_OUT_DATA.WS_ADD_ON_RATE;
        LNCQ61.INT_ACCR = LNCSLNQ.COMM_DATA.INT_ACCR;
        LNCQ61.I_VAL_DT = WS_OUT_DATA.WS_I_VAL_DT;
        LNCQ61.REVOLVING_FLG = WS_OUT_DATA.WS_REVOLVING_FLG;
        LNCQ61.DRWSEQ = WS_OUT_DATA.WS_ACC_CNT;
        LNCQ61.PDOG_FLG = WS_OUT_DATA.WS_PDOG_FLG;
        LNCQ61.PAY_MTH = WS_OUT_DATA.WS_PAY_MTH;
        CEP.TRC(SCCGWA, "LLLLLLL");
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_PAY_MTH);
        LNCQ61.PERD_FLG = WS_OUT_DATA.WS_PERD_FLG;
        LNCQ61.STOP_INT_DATE = WS_OUT_DATA.WS_STOP_INT_DATE;
        LNCQ61.ST_PAY_FLG = WS_OUT_DATA.WS_ST_PAY_FLG;
        LNCQ61.ST_PAY_AMT = WS_OUT_DATA.WS_ST_PAY_AMT;
        LNCQ61.ODUE_RAT = WS_OUT_DATA.WS_ODUE_RAT;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_ODUE_DT);
        LNCQ61.ODUE_DT = WS_OUT_DATA.WS_ODUE_DT;
        LNCQ61.CAN_DATE = WS_OUT_DATA.WS_CAN_DATE;
        LNCQ61.FUND_CTA_NO = WS_OUT_DATA.WS_FUND_CTA_NO;
        LNCQ61.RAT_CTA_NO = WS_OUT_DATA.WS_RAT_CTA_NO;
        LNCQ61.RAT_AMT = WS_OUT_DATA.WS_RAT_AMT;
        LNCQ61.EEDATE = WS_OUT_DATA.WS_EEDATE;
        LNCQ61.EEAMT = WS_OUT_DATA.WS_EEAMT;
        LNCQ61.ODUE_AMT = WS_OUT_DATA.WS_ODUE_AMT;
        LNCQ61.CAC_DT = WS_OUT_DATA.WS_CAC_DT;
        LNCQ61.GDA_DB_SEQ = WS_OUT_DATA.WS_GDA_DB_SEQ;
        CEP.TRC(SCCGWA, LNCQ61.GDA_DB_SEQ);
        CEP.TRC(SCCGWA, LNCQ61.FUND_CTA_NO);
        CEP.TRC(SCCGWA, LNCQ61.SYS_FLG);
        LNCQ61.CAL_PERD = WS_OUT_DATA.WS_CAL_PERD;
        LNCQ61.CAL_PERD_UNIT = WS_OUT_DATA.WS_CAL_PERD_UNIT;
        LNCQ61.PAY_DD_PERD = WS_OUT_DATA.WS_PAY_DD_PERD;
        LNCQ61.FLT_MTH = WS_OUT_DATA.WS_FLT_MTH;
        LNCQ61.FLT_PERD_UNIT = WS_OUT_DATA.WS_FLT_PERD_UNIT;
        LNCQ61.FLT_PERD = WS_OUT_DATA.WS_FLT_PERD;
        CEP.TRC(SCCGWA, LNCQ61.DRWSEQ);
        LNCQ61.PRP_FEERT = LNCAPRDM.REC_DATA.HAND_CHG_RATE;
        LNCQ61.MULT_SETTLE = WS_OUT_DATA.WS_MWHD_AC_FLG;
        LNCQ61.WHD_RUL = WS_OUT_DATA.WS_WHD_RUL;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            LNCQ61.AC_DATA[WS_CNT-1].REC_STL_METH = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_STL_METH;
            LNCQ61.AC_DATA[WS_CNT-1].REC_STL_DDAC = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_STL_DDAC;
            LNCQ61.AC_DATA[WS_CNT-1].REC_AC_TYP = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_AC_TYP;
            LNCQ61.AC_DATA[WS_CNT-1].REC_AC_FLG = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_AC_FLG;
            LNCQ61.AC_DATA[WS_CNT-1].PAY_CCY = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_PAY_CCY;
            LNCQ61.CHG_DB_FLG = WS_OUT_DATA.WS_CHG_DB_FLG;
            LNCQ61.HAND_CHG_R = WS_OUT_DATA.WS_HAND_CHG_R;
        }
        C000_TRC_OUTPUT();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICIQ);
        LNCICIQ.DATA.COMM_NO = WS_OUT_DATA.WS_FATHER_CONTRACT;
        LNCICIQ.DATA.CI_NO = WS_OUT_DATA.WS_CI_NO;
        LNCICIQ.DATA.COMM_NO = WS_OUT_DATA.WS_FATHER_CONTRACT;
        LNCICIQ.DATA.RTIO_VAL_DATE = WS_OUT_DATA.WS_RTIO_VAL_DATE;
        LNCICIQ.DATA.RTIO_VAL_DATE_NO = WS_OUT_DATA.WS_RTIO_VAL_DATE_NO;
        LNCICIQ.DATA.CONTRACT_TYPE = WS_OUT_DATA.WS_CONTRACT_TYPE;
        LNCICIQ.DATA.DOMI_BR = WS_OUT_DATA.WS_DOMI_BR;
        LNCICIQ.DATA.EXCHANGE_RATE = WS_OUT_DATA.WS_EXCHANGE_RATE;
        LNCICIQ.DATA.EQUIVALENT = WS_OUT_DATA.WS_EQUIVALENT;
        LNCICIQ.DATA.TRAN_DTE = WS_OUT_DATA.WS_TRAN_DTE;
        LNCICIQ.DATA.TRAN_JRN = WS_OUT_DATA.WS_TRAN_JRN;
        LNCICIQ.DATA.REMARK1 = WS_OUT_DATA.WS_REMARK1;
        LNCICIQ.DATA.CONT_NO = WS_OUT_DATA.WS_CONT_NO;
        LNCICIQ.DATA.SUB_CONT_NO = "" + WS_OUT_DATA.WS_SUB_CONT_NO;
        JIBS_tmp_int = LNCICIQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICIQ.DATA.SUB_CONT_NO = "0" + LNCICIQ.DATA.SUB_CONT_NO;
        LNCICIQ.DATA.STS = WS_OUT_DATA.WS_STS;
        LNCICIQ.DATA.PROD_CD = WS_OUT_DATA.WS_PROD_CD;
        LNCICIQ.DATA.CI_ATTR = WS_OUT_DATA.WS_CI_ATTR;
        LNCICIQ.DATA.CI_NO = WS_OUT_DATA.WS_CI_NO;
        LNCICIQ.DATA.BR = WS_OUT_DATA.WS_BR;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_BR);
        LNCICIQ.DATA.CCY = WS_OUT_DATA.WS_CCY;
        LNCICIQ.DATA.AMT = WS_OUT_DATA.WS_AMT;
        LNCICIQ.DATA.TOT_AMT = WS_OUT_DATA.WS_TOT_AMT;
        LNCICIQ.DATA.VAL_DT = WS_OUT_DATA.WS_VAL_DT;
        LNCICIQ.DATA.DUE_DT = WS_OUT_DATA.WS_DUE_DT;
        LNCICIQ.DATA.EXTEND_REASON = WS_OUT_DATA.WS_EXTEND_REASON;
        LNCICIQ.DATA.OPEN_BK = WS_OUT_DATA.WS_OPEN_BK;
        LNCICIQ.DATA.FST_FLT_DT = WS_OUT_DATA.WS_FST_FLT_DT;
        LNCICIQ.DATA.BRAT_EFF_DT = WS_OUT_DATA.WS_BRAT_EFF_DT;
        LNCICIQ.DATA.ORAT_EFF_DT = WS_OUT_DATA.WS_ORAT_EFF_DT;
        LNCICIQ.DATA.LRAT_EFF_DT = WS_OUT_DATA.WS_LRAT_EFF_DT;
        LNCICIQ.DATA.CNTR_NO = WS_OUT_DATA.WS_CNTR_NO;
        LNCICIQ.DATA.EQU_COM_AMT = WS_OUT_DATA.WS_EQU_COM_AMT;
        LNCICIQ.DATA.EQUIVALENT = WS_OUT_DATA.WS_EQU_COM_AMT;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            LNCICIQ.DATA.AC_DATA[WS_CNT-1].REC_STL_METH = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_STL_METH;
            LNCICIQ.DATA.AC_DATA[WS_CNT-1].REC_STL_DDAC = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_STL_DDAC;
            LNCICIQ.DATA.AC_DATA[WS_CNT-1].REC_AC_TYP = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_AC_TYP;
            LNCICIQ.DATA.AC_DATA[WS_CNT-1].REC_AC_FLG = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_REC_AC_FLG;
            LNCICIQ.DATA.AC_DATA[WS_CNT-1].PAY_CCY = WS_OUT_DATA.WS_AC_DATA[WS_CNT-1].WS_PAY_CCY;
        }
        LNCICIQ.DATA.CI_SNAME = WS_OUT_DATA.WS_CI_SNAME;
        LNCICIQ.DATA.CITY_CD = WS_OUT_DATA.WS_CITY_CD;
        LNCICIQ.DATA.CI_CNAME = WS_OUT_DATA.WS_CI_CNAME;
        LNCICIQ.DATA.PRINCIPAL = WS_OUT_DATA.WS_PRINCIPAL;
        LNCICIQ.DATA.PRIN_EQU = WS_OUT_DATA.WS_PRIN_EQU;
        LNCICIQ.DATA.INT_REC = WS_OUT_DATA.WS_INT_REC;
        LNCICIQ.DATA.INT_PAID = WS_OUT_DATA.WS_INT_PAID;
        LNCICIQ.DATA.LC_REC = WS_OUT_DATA.WS_LC_REC;
        LNCICIQ.DATA.LC_PAID = WS_OUT_DATA.WS_LC_PAID;
        LNCICIQ.DATA.LI_REC = WS_OUT_DATA.WS_LI_REC;
        LNCICIQ.DATA.LI_PAID = WS_OUT_DATA.WS_LI_PAID;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICIQ.DATA.OBS_INT = 0;
            LNCICIQ.DATA.OFS_INT = WS_OUT_DATA.WS_INT_REC + WS_OUT_DATA.WS_LC_REC + WS_OUT_DATA.WS_LI_REC;
        } else {
            LNCICIQ.DATA.OBS_INT = WS_OUT_DATA.WS_INT_REC + WS_OUT_DATA.WS_LC_REC;
            LNCICIQ.DATA.OFS_INT = WS_OUT_DATA.WS_LI_REC;
        }
        LNCICIQ.DATA.WAV_LC_FLG = WS_OUT_DATA.WS_WAV_LC_FLG;
        LNCICIQ.DATA.INST_MAX_TERM = WS_OUT_DATA.WS_INST_MAX_TERM;
        LNCICIQ.DATA.INT_DAY_BAS = WS_OUT_DATA.WS_INT_DAY_BAS;
        LNCICIQ.DATA.BAL = WS_OUT_DATA.WS_OS_BAL;
        LNCICIQ.DATA.EXP_DATE = WS_OUT_DATA.WS_EXP_DATE;
        LNCICIQ.DATA.P_CMP_TERM = WS_OUT_DATA.WS_P_CMP_TERM;
        LNCICIQ.DATA.IC_CMP_TERM = WS_OUT_DATA.WS_IC_CMP_TERM;
        LNCICIQ.DATA.ADD_ON_RATE = WS_OUT_DATA.WS_ADD_ON_RATE;
        LNCICIQ.DATA.PAY_MTH = WS_OUT_DATA.WS_PAY_MTH2;
        LNCICIQ.DATA.CAL_PERD_UNIT = WS_OUT_DATA.WS_CAL_PERD_UNIT;
        LNCICIQ.DATA.CAL_PERD = WS_OUT_DATA.WS_CAL_PERD;
        C000_ICIQ_OUTPUT();
        if (pgmRtn) return;
    }
    public void C000_ICIQ_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICIQ.DATA.PAY_MTH);
    }
    public void C000_TRC_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCQ61.JBR);
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRLOAN() throws IOException,SQLException,Exception {
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        CEP.TRC(SCCGWA, LNCRLOAN.FUNC);
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        if (LNCRLOAN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE == 0) {
            WS_CONT_FLAG = 'Y';
        } else {
            WS_CONT_FLAG = 'N';
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        CEP.TRC(SCCGWA, LNCRSETL.RC.RC_CODE);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LOAN-INQUIRY", LNCSLNQ);
        if (LNCSLNQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZILDFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GET-L-DEFS", LNCILDFS);
        if (LNCILDFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCILDFS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 477;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RC.RC_CODE != 0) {
            if (LNCRRCVD.RETURN_INFO == 'N' 
                || LNCRRCVD.RETURN_INFO == 'E') {
                WS_RCVD_FLG = 'Y';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZRCMMT1() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1235;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        CEP.TRC(SCCGWA, LNCRCMMT.RC);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRWOFF() throws IOException,SQLException,Exception {
        LNCRWOFF.REC_PTR = LNRWOFF;
        LNCRWOFF.REC_LEN = 275;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTWOFF", LNCRWOFF);
        if (LNCRWOFF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRWOFF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            JIBS_tmp_str[0] = "SC";
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            JIBS_tmp_str[1] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 3 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(3 + 4 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_STARTBR_LNTSETL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void T000_STARTBR_LNTEXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        LNTEXTN_BR.rp = new DBParm();
        LNTEXTN_BR.rp.TableName = "LNTEXTN";
        LNTEXTN_BR.rp.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND STATUS = '1' "
            + "AND EXT_FLG < > '3'";
        LNTEXTN_BR.rp.order = "VAL_DT DESC";
        IBS.STARTBR(SCCGWA, LNREXTN, this, LNTEXTN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNREXTN, this, LNTEXTN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTEXTN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTEXTN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.order = "VAL_DT";
        IBS.STARTBR(SCCGWA, LNRRCVD, LNTRCVD_BR);
    }
    public void T000_READNEX_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RCVD_FLG2 = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO "
            + "AND PAPER_NO = :LNRAGRE.PAPER_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAGRE_BY_CONT_NO() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICIQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-GET-ICTL-STS", LNCICSTS);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICIQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCICIQ=");
            CEP.TRC(SCCGWA, LNCICIQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
