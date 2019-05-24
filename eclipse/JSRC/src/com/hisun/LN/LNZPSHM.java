package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPSHM {
    DBParm LNTAPRD_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    char LNZPSHM_FILLER1 = ' ';
    char WS_STOP_ID = ' ';
    int WS_I = 0;
    short WS_LAST_TERM = 0;
    int WS_LAST_VAL_DT = 0;
    short WS_TERM = 0;
    int WS_VAL_DT = 0;
    int WS_DUE_DT = 0;
    int WS_IC_CAL_VAL_DT = 0;
    int WS_IC_CAL_DUE_DT = 0;
    int WS_P_CAL_DUE_DT = 0;
    short WS_PSHM_TERM = 0;
    short WS_PHS_CMP_TERM = 0;
    short WS_PHS_NO = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRCONT LNRCONT = new LNRCONT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCUDRW LNCUDRW = new LNCUDRW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    SCCGWA SCCGWA;
    LNCPSHM LNCPSHM;
    BPRTLT BPRTLT;
    public void MP(SCCGWA SCCGWA, LNCPSHM LNCPSHM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPSHM = LNCPSHM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPSHM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        LNCPSHM.RC.RC_APP = "LN";
        LNCPSHM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCPSHM.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCPSHM.COMM_DATA.PAY_TYP != 'C' 
            && LNCPSHM.COMM_DATA.PAY_TYP != 'P' 
            && LNCPSHM.COMM_DATA.PAY_TYP != 'I') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCPSHM.COMM_DATA.TX_TYP != 'A' 
            && LNCPSHM.COMM_DATA.TX_TYP != 'M' 
            && LNCPSHM.COMM_DATA.TX_TYP != 'D' 
            && LNCPSHM.COMM_DATA.TX_TYP != 'I') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCPSHM.COMM_DATA.TX_TYP == 'A') {
            for (WS_I = 1; WS_I <= LNCPSHM.COMM_DATA.TOTAL_TE; WS_I += 1) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE;
                R00_CHECK_DATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_PRE_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            if (LNCICTLM.REC_DATA.P_CMP_TERM == 1) {
                WS_LAST_VAL_DT = LNRCONT.START_DATE;
            } else {
                WS_TERM = LNCICTLM.REC_DATA.P_CMP_TERM;
                T000_GET_LAST_PLPI();
                if (pgmRtn) return;
                WS_LAST_VAL_DT = LNCPLPIM.REC_DATA.DUE_DT;
            }
            WS_TERM = LNCICTLM.REC_DATA.P_CMP_TERM;
            WS_VAL_DT = WS_LAST_VAL_DT;
            WS_DUE_DT = LNCICTLM.REC_DATA.P_CMP_DUE_DT;
        } else {
            WS_LAST_VAL_DT = LNCICTLM.REC_DATA.IC_CMP_VAL_DT;
            WS_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
            WS_VAL_DT = WS_LAST_VAL_DT;
            WS_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
        }
        WS_LAST_TERM = LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO;
        if (LNCPSHM.COMM_DATA.TX_TYP == 'A') {
            B210_ADD_LNTPLPI_PROC();
            if (pgmRtn) return;
        } else if (LNCPSHM.COMM_DATA.TX_TYP == 'M') {
            B220_UPD_LNTPLPI_PROC();
            if (pgmRtn) return;
        } else if (LNCPSHM.COMM_DATA.TX_TYP == 'D') {
            B230_DEL_LNTPLPI_PROC();
            if (pgmRtn) return;
        } else if (LNCPSHM.COMM_DATA.TX_TYP == 'I') {
            B240_INQ_LNTPLPI_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B400_UPD_ICTL_CMP_DATA();
        if (pgmRtn) return;
    }
    public void B210_ADD_LNTPLPI_PROC() throws IOException,SQLException,Exception {
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            if (LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO != LNCICTLM.REC_DATA.P_CMP_TERM) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_START_TERM, LNCPSHM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO != LNCICTLM.REC_DATA.IC_CMP_TERM) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_START_TERM, LNCPSHM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        for (WS_I = 1; WS_I <= LNCPSHM.COMM_DATA.TOTAL_TE; WS_I += 1) {
            if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO != 0) {
                B210_ADD_LNTPLPI();
                if (pgmRtn) return;
            }
        }
        WS_TERM = WS_LAST_TERM;
        WS_VAL_DT = WS_LAST_VAL_DT;
        if (LNRAPRD.CAL_PERD != 0) {
            B220_CAL_NEXT_DUEDATE();
            if (pgmRtn) return;
        } else {
            WS_DUE_DT = LNRCONT.MAT_DATE;
        }
    }
    public void B210_ADD_LNTPLPI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO);
        if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO != WS_LAST_TERM) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_MAINT_TERM, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '0';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        if (LNCPSHM.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPSHM.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNCPSHM.COMM_DATA.PAY_TYP;
        LNCPLPIM.REC_DATA.KEY.TERM = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO;
        LNCPLPIM.REC_DATA.VAL_DT = WS_LAST_VAL_DT;
        LNCPLPIM.REC_DATA.DUE_DT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE;
        WS_LAST_VAL_DT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE;
        LNCPLPIM.REC_DATA.REC_STS = '0';
        LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_AMT;
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            LNCPLPIM.REC_DATA.PRIN_AMT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_AMT;
        }
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.DUE_DT);
        if ((LNCPLPIM.REC_DATA.VAL_DT < LNRCONT.START_DATE) 
            || (LNCPLPIM.REC_DATA.DUE_DT > LNRCONT.MAT_DATE)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_VALDUE_DT, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCPSHM.COMM_DATA.PAY_TYP != 'P' 
            && LNCPLPIM.REC_DATA.KEY.TERM == LNCICTLM.REC_DATA.IC_CAL_TERM)) {
            WS_IC_CAL_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_IC_CAL_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        }
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P' 
            && LNCPLPIM.REC_DATA.KEY.TERM == LNCICTLM.REC_DATA.P_CAL_TERM) {
            WS_P_CAL_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_LAST_TERM += 1;
        WS_PSHM_TERM += 1;
    }
    public void B220_CAL_NEXT_DUEDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_LAST_VAL_DT;
        if (LNRAPRD.CAL_PERD_UNIT == 'D') {
            SCCCLDT.DAYS = LNRAPRD.CAL_PERD;
        } else {
            SCCCLDT.MTHS = LNRAPRD.CAL_PERD;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_DUE_DT = SCCCLDT.DATE2;
    }
    public void B220_UPD_LNTPLPI_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= LNCPSHM.COMM_DATA.TOTAL_TE; WS_I += 1) {
            if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO != 0) {
                B220_UPD_LNTPLPI();
                if (pgmRtn) return;
            }
        }
    }
    public void B220_UPD_LNTPLPI() throws IOException,SQLException,Exception {
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO >= LNCICTLM.REC_DATA.P_CMP_TERM 
                || LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO < LNCICTLM.REC_DATA.P_CAL_TERM) {
                CEP.TRC(SCCGWA, "PSHM-PAY-TYP = P");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_MAINT_TERM, LNCPSHM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO >= LNCICTLM.REC_DATA.IC_CMP_TERM 
                || LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO < LNCICTLM.REC_DATA.IC_CAL_TERM) {
                CEP.TRC(SCCGWA, "PSHM-TERMNO(WS-I) >= ICTLM-IC-CMP-TERM");
                CEP.TRC(SCCGWA, LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO);
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CMP_TERM);
                CEP.TRC(SCCGWA, WS_LAST_TERM);
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_MAINT_TERM, LNCPSHM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO != WS_LAST_TERM) {
            CEP.TRC(SCCGWA, "PSHM-TERMNO(WS-I) NOT = WS-LAST-TERM");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_MAINT_TERM, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        if (LNCPSHM.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPSHM.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNCPSHM.COMM_DATA.PAY_TYP;
        LNCPLPIM.REC_DATA.KEY.TERM = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        if (LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO == WS_TERM) {
            WS_TERM = LNCPLPIM.REC_DATA.KEY.TERM;
            WS_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        }
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.VAL_DT = WS_LAST_VAL_DT;
        LNCPLPIM.REC_DATA.DUE_DT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE;
        WS_LAST_VAL_DT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE;
        LNCPLPIM.REC_DATA.REC_STS = '0';
        LNCPLPIM.REC_DATA.DUE_REPY_AMT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_AMT;
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            LNCPLPIM.REC_DATA.PRIN_AMT = LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_AMT;
        }
        if ((LNCPLPIM.REC_DATA.VAL_DT < LNRCONT.START_DATE) 
            || (LNCPLPIM.REC_DATA.DUE_DT > LNRCONT.MAT_DATE)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_VALDUE_DT, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCPSHM.COMM_DATA.PAY_TYP != 'P' 
            && LNCPLPIM.REC_DATA.KEY.TERM == LNCICTLM.REC_DATA.IC_CAL_TERM)) {
            WS_IC_CAL_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_IC_CAL_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        }
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P' 
            && LNCPLPIM.REC_DATA.KEY.TERM == LNCICTLM.REC_DATA.P_CAL_TERM) {
            WS_P_CAL_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_LAST_TERM += 1;
    }
    public void B230_DEL_LNTPLPI_PROC() throws IOException,SQLException,Exception {
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            if (LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO >= LNCICTLM.REC_DATA.P_CMP_TERM 
                || LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO < LNCICTLM.REC_DATA.P_CAL_TERM) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_MAINT_TERM, LNCPSHM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO >= LNCICTLM.REC_DATA.IC_CMP_TERM 
                || LNCPSHM.COMM_DATA.TERMINFO[1-1].TERMNO < LNCICTLM.REC_DATA.IC_CAL_TERM) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SCH_MAINT_TERM, LNCPSHM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        WS_STOP_ID = 'N';
        for (WS_I = 1; WS_STOP_ID != 'Y'; WS_I += 1) {
            B230_DEL_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void B230_DEL_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        if (LNCPSHM.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPSHM.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNCPSHM.COMM_DATA.PAY_TYP;
        LNCPLPIM.REC_DATA.KEY.TERM = WS_LAST_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        if (WS_I == 1) {
            WS_TERM = LNCPLPIM.REC_DATA.KEY.TERM;
            WS_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
            WS_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        }
        LNCPLPIM.FUNC = '1';
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_LAST_TERM += 1;
        WS_PSHM_TERM += 1;
        if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
            if (WS_LAST_TERM >= LNCICTLM.REC_DATA.P_CMP_TERM) {
                WS_STOP_ID = 'Y';
            }
        } else {
            if (WS_LAST_TERM >= LNCICTLM.REC_DATA.IC_CMP_TERM) {
                WS_STOP_ID = 'Y';
            }
        }
    }
    public void B240_INQ_LNTPLPI_PROC() throws IOException,SQLException,Exception {
    }
    public void B100_PRE_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_CONT_INF();
        if (pgmRtn) return;
        B100_GET_PPMQ_INF();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B100_GET_CONT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B100_GET_PPMQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        T000_READ_APRD_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        if (LNCPSHM.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPSHM.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_PHS_NO = LNCICTLM.REC_DATA.IC_CMP_PHS_NO;
        WS_IC_CAL_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        WS_IC_CAL_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        WS_P_CAL_DUE_DT = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
    }
    public void T000_GET_LAST_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '3';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        if (LNCPSHM.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPSHM.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNCPSHM.COMM_DATA.PAY_TYP;
        LNCPLPIM.REC_DATA.KEY.TERM = WS_TERM;
        LNCPLPIM.REC_DATA.KEY.TERM -= 1;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B400_UPD_ICTL_CMP_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPSHM.COMM_DATA.LN_AC;
        if (LNCPSHM.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCPSHM.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (LNCPSHM.COMM_DATA.TX_TYP == 'A' 
            || LNCPSHM.COMM_DATA.TX_TYP == 'D') {
            if (LNCPSHM.COMM_DATA.PAY_TYP == 'P') {
                LNCICTLM.REC_DATA.P_CMP_TERM = WS_TERM;
                LNCICTLM.REC_DATA.P_CMP_DUE_DT = WS_DUE_DT;
            } else {
                LNCICTLM.REC_DATA.IC_CMP_TERM = WS_TERM;
                LNCICTLM.REC_DATA.IC_CMP_VAL_DT = WS_VAL_DT;
                LNCICTLM.REC_DATA.IC_CMP_DUE_DT = WS_DUE_DT;
            }
        }
        LNCICTLM.REC_DATA.IC_CMP_PHS_NO = WS_PHS_NO;
        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = WS_IC_CAL_VAL_DT;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_IC_CAL_DUE_DT;
        LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_P_CAL_DUE_DT;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCPSHM.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCPSHM.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCPSHM.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCPSHM.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCPSHM.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCPSHM.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CHECK_DATE() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            LNCPSHM.RC.RC_APP = "SC";
            LNCPSHM.RC.RC_RTNCODE = SCCCKDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCPSHM.RC.RC_APP = "SC";
            LNCPSHM.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCPSHM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCPSHM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCPSHM=");
            CEP.TRC(SCCGWA, LNCPSHM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
