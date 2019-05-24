package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCAL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPYIF_RD;
    brParm LNTINTD_BR = new brParm();
    DBParm LNTINTD_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTBALZ_RD;
    DBParm LNTRELA_RD;
    boolean pgmRtn = false;
    short WS_I = 0;
    int WS_DATE = 0;
    char WS_REPY_TYPE = ' ';
    short WS_PHS_CAL_TERM = 0;
    short WS_IC_TERM = 0;
    int WS_IC_VAL_DT = 0;
    int WS_IC_DUE_DT = 0;
    short WS_P_TERM = 0;
    int WS_P_VAL_DT = 0;
    int WS_P_DUE_DT = 0;
    double WS_INT_AMT = 0;
    short WS_PHS_NO = 0;
    char WS_REPY_STS = ' ';
    double WS_REPY_AMT = 0;
    double WS_P_NOR_AMT = 0;
    double WS_I_NOR_AMT = 0;
    double WS_P_OVD_AMT = 0;
    double WS_I_OVD_AMT = 0;
    double WS_P_NOR_AMT1 = 0;
    double WS_I_NOR_AMT1 = 0;
    double WS_P_OVD_AMT1 = 0;
    double WS_I_OVD_AMT1 = 0;
    int WS_RCVD_DUE_DT = 0;
    double WS_REM_PRIN_AMT = 0;
    double WS_DUE_PRI_AMT = 0;
    double WS_DUE_INT_AMT = 0;
    LNZRCAL_WS_IRUL_KEY WS_IRUL_KEY = new LNZRCAL_WS_IRUL_KEY();
    int WS_SUBS_PROJ_NO = 0;
    LNZRCAL_WS_LAST_PREPAY_INFO WS_LAST_PREPAY_INFO = new LNZRCAL_WS_LAST_PREPAY_INFO();
    short WS_ST_TERM = 0;
    short WS_ED_TERM = 0;
    int WS_PART_NO = 0;
    int WS_VAL_DT = 0;
    int WS_DUE_DT = 0;
    int WS_TOT_CNT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FND_FLG = ' ';
    char WS_INTD_FLG = ' ';
    char WS_BALZ_FOUND_FLG = ' ';
    String WS_FORM_CODE = " ";
    char WS_DELETE_RCVD = ' ';
    char WS_SUBS_FLG = ' ';
    LNRINTD LNRINTD = new LNRINTD();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNCRINTD LNCRINTD = new LNCRINTD();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNCIRULB LNCIRULB = new LNCIRULB();
    LNCHGRD LNCHGRD = new LNCHGRD();
    LNCICUT LNCICUT = new LNCICUT();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCICNQ LNCICNQ = new LNCICNQ();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNRRELA LNRRELA = new LNRRELA();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    SCCGWA SCCGWA;
    LNCRCAL LNCRCAL;
    public void MP(SCCGWA SCCGWA, LNCRCAL LNCRCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCAL = LNCRCAL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCRCAL.RC.RC_APP = "LN";
        LNCRCAL.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.VAL_DATE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_DATA();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCRCAL.COMM_DATA.FUNC_CODE != 'I' 
            && LNCRCAL.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRCAL.COMM_DATA.LN_AC.equalsIgnoreCase("0") 
            || LNCRCAL.COMM_DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B110_GET_LOAN_INF();
        if (pgmRtn) return;
        B130_GET_PPMQ_INF();
        if (pgmRtn) return;
        B140_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B110_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCRCAL.COMM_DATA.VAL_DATE < LNCCONTM.REC_DATA.START_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_STDT, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '3';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B130_GET_PPMQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
        T000_READ_APRD_PROC();
        if (pgmRtn) return;
        if (LNRAPRD.PAY_MTH == '4') {
            WS_REPY_TYPE = 'C';
        } else {
            WS_REPY_TYPE = 'I';
        }
        CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
        CEP.TRC(SCCGWA, WS_REPY_TYPE);
    }
    public void B140_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_P_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        WS_P_TERM -= 1;
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDAYP";
        LNCCNEX.COMM_DATA.LN_AC = LNCRCAL.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCRCAL.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_DUE_PRI_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDAYI";
        LNCCNEX.COMM_DATA.LN_AC = LNCRCAL.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCRCAL.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_DUE_INT_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        LNCRCAL.COMM_DATA.PRI_AMT = 0;
        LNCRCAL.COMM_DATA.INT_AMT = 0;
        WS_DELETE_RCVD = 'N';
        if (LNCRCAL.COMM_DATA.FUNC_TYPE == 'I' 
            || LNCRCAL.COMM_DATA.FUNC_TYPE == ' ') {
            B201_IC_REV_CAL();
            if (pgmRtn) return;
        }
        if (LNCRCAL.COMM_DATA.FUNC_TYPE == 'P' 
            || LNCRCAL.COMM_DATA.FUNC_TYPE == ' ') {
            B203_P_REV_CAL();
            if (pgmRtn) return;
        }
    }
    public void B201_IC_REV_CAL() throws IOException,SQLException,Exception {
        WS_P_NOR_AMT1 = 0;
        WS_I_NOR_AMT1 = 0;
        WS_P_OVD_AMT1 = 0;
        WS_I_OVD_AMT1 = 0;
        WS_LAST_PREPAY_INFO.WS_LAST_PREPAY_DT = 0;
        WS_LAST_PREPAY_INFO.WS_LAST_TMP_AMT = 0;
        if (LNCICTLM.REC_DATA.IC_CAL_TERM > 1 
            && LNCICTLM.REC_DATA.IC_CAL_VAL_DT > LNCRCAL.COMM_DATA.VAL_DATE) {
            B300_IC_REV_CAL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B203_P_REV_CAL() throws IOException,SQLException,Exception {
        WS_P_NOR_AMT1 = 0;
        WS_I_NOR_AMT1 = 0;
        WS_P_OVD_AMT1 = 0;
        WS_I_OVD_AMT1 = 0;
        if (LNCICTLM.REC_DATA.P_CAL_TERM > 1) {
            B401_GET_PLPI_INF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_P_DUE_DT);
            CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.VAL_DATE);
            CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.CTL_TYPE);
            if (WS_P_DUE_DT > LNCRCAL.COMM_DATA.VAL_DATE 
                || (WS_P_DUE_DT == LNCRCAL.COMM_DATA.VAL_DATE 
                && LNCLOANM.REC_DATA.CTL_TYPE != ' ')) {
                B400_P_REV_CAL_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_IC_REV_CAL_PROC() throws IOException,SQLException,Exception {
        WS_P_NOR_AMT1 = 0;
        WS_IC_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        WS_IC_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        WS_IC_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        WS_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
        CEP.TRC(SCCGWA, WS_REPY_TYPE);
        if (WS_REPY_TYPE == 'C') {
            B001_GET_PAIP_INF();
            if (pgmRtn) return;
        }
        while ((WS_IC_VAL_DT > LNCRCAL.COMM_DATA.VAL_DATE) 
            && (LNCICTLM.REC_DATA.IC_CAL_TERM != 1)) {
            B300_IC_CAL_TERM_PROC();
            if (pgmRtn) return;
        }
        if (WS_REPY_TYPE == 'C') {
            B311_UPD_PAIP_CAL();
            if (pgmRtn) return;
        }
    }
    public void B300_IC_CAL_TERM_PROC() throws IOException,SQLException,Exception {
        B312_DEL_IC_RCVD_REC();
        if (pgmRtn) return;
        B311_UPD_PLPI_PROC();
        if (pgmRtn) return;
        B314_GEN_NEXT_CALDATA();
        if (pgmRtn) return;
        B310_UPDATE_LNTICTL();
        if (pgmRtn) return;
        if (LNRAPRD.PAY_MTH != '0') {
            B313_INT_CNTL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B311_UPD_PLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = WS_REPY_TYPE;
        LNCPLPIM.REC_DATA.KEY.TERM = WS_IC_TERM;
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNCPLPIM.REC_DATA.KEY.TERM);
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_IC_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_IC_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        WS_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, WS_VAL_DT);
        CEP.TRC(SCCGWA, WS_DUE_DT);
        LNCPLPIM.FUNC = '2';
        if (WS_I_OVD_AMT1 == 0) {
            LNCPLPIM.REC_DATA.REC_STS = 'R';
        } else {
            LNCPLPIM.REC_DATA.REC_STS = 'P';
        }
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B312_DEL_IC_RCVD_REC() throws IOException,SQLException,Exception {
        WS_SUBS_PROJ_NO = 0;
        WS_I_OVD_AMT1 = 0;
        WS_P_NOR_AMT1 = 0;
        WS_I_NOR_AMT1 = 0;
        WS_P_OVD_AMT1 = 0;
        WS_IC_TERM -= 1;
        B312_DEL_IC_RCVD_PROC();
        if (pgmRtn) return;
        B312_2_DEL_INTD_REC();
        if (pgmRtn) return;
    }
    public void B312_DEL_IC_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = WS_REPY_TYPE;
        LNCRCVDM.REC_DATA.KEY.TERM = WS_IC_TERM;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = WS_SUBS_PROJ_NO;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        WS_RCVD_DUE_DT = LNCRCVDM.REC_DATA.DUE_DT;
        WS_REPY_STS = LNCRCVDM.REC_DATA.REPY_STS;
        if (LNRAPRD.PAY_MTH != '0') {
            if (WS_REPY_STS != '0') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_STS, LNCRCAL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCRCVDM.REC_DATA.P_REC_AMT <= WS_DUE_PRI_AMT) {
            WS_P_NOR_AMT1 = LNCRCVDM.REC_DATA.P_REC_AMT;
            WS_DUE_PRI_AMT -= LNCRCVDM.REC_DATA.P_REC_AMT;
        } else {
            WS_P_OVD_AMT1 = LNCRCVDM.REC_DATA.P_REC_AMT;
        }
        if (LNCRCVDM.REC_DATA.I_REC_AMT <= WS_DUE_INT_AMT) {
            WS_I_NOR_AMT1 = LNCRCVDM.REC_DATA.I_REC_AMT;
            WS_DUE_INT_AMT -= LNCRCVDM.REC_DATA.I_REC_AMT;
        } else {
            WS_I_OVD_AMT1 = LNCRCVDM.REC_DATA.I_REC_AMT;
        }
        LNCRCAL.COMM_DATA.INT_AMT += LNCRCVDM.REC_DATA.I_REC_AMT;
        LNCRCAL.COMM_DATA.PRI_AMT += LNCRCVDM.REC_DATA.P_REC_AMT;
        WS_REM_PRIN_AMT += LNCRCVDM.REC_DATA.P_REC_AMT;
        LNCRCVDM.FUNC = '1';
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        B312_DEL_RCVD_SUBS_PROC();
        if (pgmRtn) return;
        WS_DELETE_RCVD = 'Y';
    }
    public void B312_DEL_RCVD_SUBS_PROC() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            WS_FND_FLG = 'N';
            IBS.init(SCCGWA, LNRRELA);
            LNRRELA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
            LNRRELA.KEY.TYPE = 'S';
            T000_READ_LNTRELA();
            if (pgmRtn) return;
            WS_SUBS_FLG = 'N';
            WS_ST_TERM = LNRRELA.ST_TERM;
            WS_ED_TERM = (short) (LNRRELA.ST_TERM + LNRRELA.TERM - 1);
            if (LNCRCVDM.REC_DATA.KEY.TERM >= WS_ST_TERM 
                && LNCRCVDM.REC_DATA.KEY.TERM <= WS_ED_TERM) {
                WS_SUBS_FLG = 'Y';
            }
            if (WS_FND_FLG == 'Y' 
                && WS_SUBS_FLG == 'Y') {
                LNCRCVDM.FUNC = '4';
                LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
                if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
                else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
                LNCRCVDM.REC_DATA.KEY.AMT_TYP = WS_REPY_TYPE;
                LNCRCVDM.REC_DATA.KEY.TERM = WS_IC_TERM;
                LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = LNRRELA.PROJ_NO;
                S000_CALL_LNZRCVDM();
                if (pgmRtn) return;
                if (LNCRCVDM.REC_DATA.REPY_STS != '0') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_STS, LNCRCAL.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCRCVDM.REC_DATA.P_REC_AMT <= WS_DUE_PRI_AMT) {
                    WS_P_NOR_AMT1 += LNCRCVDM.REC_DATA.P_REC_AMT;
                    WS_DUE_PRI_AMT -= LNCRCVDM.REC_DATA.P_REC_AMT;
                } else {
                    WS_P_OVD_AMT1 += LNCRCVDM.REC_DATA.P_REC_AMT;
                }
                if (LNCRCVDM.REC_DATA.I_REC_AMT <= WS_DUE_INT_AMT) {
                    WS_I_NOR_AMT1 += LNCRCVDM.REC_DATA.I_REC_AMT;
                    WS_DUE_INT_AMT -= LNCRCVDM.REC_DATA.I_REC_AMT;
                } else {
                    WS_I_OVD_AMT1 += LNCRCVDM.REC_DATA.I_REC_AMT;
                }
                LNCRCAL.COMM_DATA.INT_AMT += LNCRCVDM.REC_DATA.I_REC_AMT;
                LNCRCAL.COMM_DATA.PRI_AMT += LNCRCVDM.REC_DATA.P_REC_AMT;
                WS_REM_PRIN_AMT += LNCRCVDM.REC_DATA.P_REC_AMT;
                LNCRCVDM.FUNC = '1';
                S000_CALL_LNZRCVDM();
                if (pgmRtn) return;
            }
        }
    }
    public void B3S1_INST_REVS_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.I_REC_AMT);
        CEP.TRC(SCCGWA, WS_DUE_INT_AMT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0138610")) {
        } else {
            if (LNRAPRD.PAY_MTH == '4') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INST_REVS_OVDINT, LNCRCAL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_TOT_LNTPYIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        WS_TOT_CNT = 0;
        LNRPYIF.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRPYIF.KEY.SUB_CTA_NO = 0;
        else LNRPYIF.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.set = "WS-TOT-CNT=COUNT(*)";
        LNTPYIF_RD.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO "
            + "AND CUR_REPY_DT > :WS_VAL_DT "
            + "AND CUR_REPY_DT <= :WS_DUE_DT";
        IBS.GROUP(SCCGWA, LNRPYIF, this, LNTPYIF_RD);
    }
    public void B312_2_DEL_INTD_REC() throws IOException,SQLException,Exception {
        WS_FND_FLG = 'N';
        T001_STARTBR_LNTINTD();
        if (pgmRtn) return;
        T002_READNEXT_LNTINTD();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            T00_DELETE_LNTINTD();
            if (pgmRtn) return;
            T002_READNEXT_LNTINTD();
            if (pgmRtn) return;
        }
        T003_ENDBR_LNTINTD();
        if (pgmRtn) return;
    }
    public void T001_STARTBR_LNTINTD() throws IOException,SQLException,Exception {
        LNRINTD.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNRINTD.KEY.SUB_CTA_NO = 0;
        else LNRINTD.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNRINTD.KEY.REPY_MTH = WS_REPY_TYPE;
        LNRINTD.KEY.TERM = WS_IC_TERM;
        LNRINTD.KEY.INT_TYP = 'N';
        LNTINTD_BR.rp = new DBParm();
        LNTINTD_BR.rp.TableName = "LNTINTD";
        LNTINTD_BR.rp.upd = true;
        LNTINTD_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH, TERM, INT_TYP";
        IBS.STARTBR(SCCGWA, LNRINTD, LNTINTD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T002_READNEXT_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRINTD, this, LNTINTD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T003_ENDBR_LNTINTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTD_BR);
    }
    public void T00_DELETE_LNTINTD() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        LNTINTD_RD.errhdl = true;
        IBS.DELETE(SCCGWA, LNRINTD, LNTINTD_RD);
    }
    public void B313_INT_CNTL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_P_NOR_AMT1);
        CEP.TRC(SCCGWA, WS_P_OVD_AMT1);
        CEP.TRC(SCCGWA, WS_I_NOR_AMT1);
        CEP.TRC(SCCGWA, WS_I_OVD_AMT1);
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "INTCALR";
        LNCCNEV.COMM_DATA.LN_AC = LNCRCAL.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCRCAL.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = WS_RCVD_DUE_DT;
        LNCCNEV.COMM_DATA.P_AMT = WS_P_NOR_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = WS_P_NOR_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_P_OVD_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT = WS_P_OVD_AMT1;
        LNCCNEV.COMM_DATA.P_AMT += WS_P_OVD_AMT1;
        LNCCNEV.COMM_DATA.I_AMT = WS_I_NOR_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_I_NOR_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_I_OVD_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT = WS_I_OVD_AMT1;
        LNCCNEV.COMM_DATA.I_AMT += WS_I_OVD_AMT1;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0138610")) {
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if ((LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) 
            && LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT != 0) {
            R000_SYLN_PROC();
            if (pgmRtn) return;
        }
        if (WS_LAST_PREPAY_INFO.WS_LAST_TMP_AMT != 0) {
            IBS.init(SCCGWA, LNCICNQ);
            LNCICNQ.COMM_DATA.INQ_CODE = "INQTMPI";
            LNCICNQ.COMM_DATA.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
            if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICNQ.COMM_DATA.SUB_CTA_NO = 0;
            else LNCICNQ.COMM_DATA.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
            S000_CALL_LNZICNQ();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCCNBU);
            LNCCNBU.COMM_DATA.LN_AC = LNCRCAL.COMM_DATA.LN_AC;
            LNCCNBU.COMM_DATA.SUF_NO = LNCRCAL.COMM_DATA.SUF_NO;
            LNCCNBU.COMM_DATA.VAL_DATE = WS_LAST_PREPAY_INFO.WS_LAST_PREPAY_DT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICNQ.COMM_DATA.CNTR[1-1].CNTL_ID);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
            LNCCNBU.COMM_DATA.AMT = WS_LAST_PREPAY_INFO.WS_LAST_TMP_AMT;
            LNCCNBU.COMM_DATA.AS_IND = 'I';
            LNCCNBU.COMM_DATA.RVS_IND = 'N';
            LNCCNBU.COMM_DATA.TXN_FLG = 'N';
            LNCCNBU.COMM_DATA.ACM_EVCD = " ";
            S000_CALL_LNZCNBU();
            if (pgmRtn) return;
        }
    }
    public void R000_SYLN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPAMT);
        LNCIPAMT.CTA_NO = LNCRCAL.COMM_DATA.LN_AC;
        LNCIPAMT.I_AMT = LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT;
        R000_CALL_LNZIPAMT();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10 
            && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
            if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_I-1].PART_NO);
                WS_PART_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                R000_READUPD_LNTBALZ_PART();
                if (pgmRtn) return;
                LNRBALZ.LOAN_BALL15 = LNRBALZ.LOAN_BALL15 + LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                if (WS_BALZ_FOUND_FLG == 'Y') {
                    R000_REWRITE_LNTBALZ_PART();
                    if (pgmRtn) return;
                } else {
                    R000_WRITE_LNTBALZ_PART();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B314_GEN_NEXT_CALDATA() throws IOException,SQLException,Exception {
        LNCICTLM.REC_DATA.IC_CAL_TERM = WS_IC_TERM;
        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = WS_IC_VAL_DT;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_IC_DUE_DT;
        LNCICTLM.REC_DATA.IC_CAL_PHS_NO = WS_PHS_NO;
        if (WS_REPY_TYPE == 'C') {
            CEP.TRC(SCCGWA, "WS11111");
            CEP.TRC(SCCGWA, WS_PHS_NO);
            if (WS_PHS_CAL_TERM == 0) {
                B311_UPD_PAIP_CAL();
                if (pgmRtn) return;
                if (WS_PHS_NO > 1) {
                    WS_PHS_NO -= 1;
                    CEP.TRC(SCCGWA, "WS22222");
                    CEP.TRC(SCCGWA, WS_PHS_NO);
                    B001_GET_PAIP_INF();
                    if (pgmRtn) return;
                }
            }
            WS_PHS_CAL_TERM -= 1;
        }
    }
    public void B000_GET_REPY_AMT() throws IOException,SQLException,Exception {
        if (WS_REPY_TYPE == 'C') {
            B001_GET_PAIP_INF();
            if (pgmRtn) return;
        } else {
            WS_REPY_AMT = 0;
        }
    }
    public void B001_GET_PAIP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '4';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNCPAIPM.REC_DATA.KEY.PHS_NO = WS_PHS_NO;
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRCAL.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, WS_PHS_NO);
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        WS_REPY_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
        WS_REM_PRIN_AMT = LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT;
        WS_PHS_CAL_TERM = LNCPAIPM.REC_DATA.PHS_CAL_TERM;
    }
    public void BS02_READUPD_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B003_REWRITE_ICTL() throws IOException,SQLException,Exception {
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B311_UPD_PAIP_CAL() throws IOException,SQLException,Exception {
        LNCPAIPM.FUNC = '2';
        LNCPAIPM.REC_DATA.PHS_CAL_TERM = WS_PHS_CAL_TERM;
        LNCPAIPM.REC_DATA.PHS_REM_PRIN_AMT = WS_REM_PRIN_AMT;
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
    }
    public void B310_UPDATE_LNTICTL() throws IOException,SQLException,Exception {
        BS02_READUPD_ICTL();
        if (pgmRtn) return;
        LNCICTLM.REC_DATA.IC_CAL_TERM = WS_IC_TERM;
        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = WS_IC_VAL_DT;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = WS_IC_DUE_DT;
        LNCICTLM.REC_DATA.IC_CAL_PHS_NO = WS_PHS_NO;
        if (WS_LAST_PREPAY_INFO.WS_LAST_PREPAY_DT != 0) {
            LNCICTLM.REC_DATA.INT_CUT_DT = WS_LAST_PREPAY_INFO.WS_LAST_PREPAY_DT;
        } else {
            LNCICTLM.REC_DATA.INT_CUT_DT = WS_IC_VAL_DT;
        }
        B003_REWRITE_ICTL();
        if (pgmRtn) return;
    }
    public void B400_P_REV_CAL_PROC() throws IOException,SQLException,Exception {
        while ((WS_P_DUE_DT > LNCRCAL.COMM_DATA.VAL_DATE 
            || LNCLOANM.REC_DATA.CTL_TYPE != ' ') 
            && (LNCICTLM.REC_DATA.P_CAL_TERM != 1)) {
            B410_P_CAL_TERM_PROC();
            if (pgmRtn) return;
        }
    }
    public void B401_GET_PLPI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '3';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = WS_P_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_P_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_P_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
    }
    public void B410_P_CAL_TERM_PROC() throws IOException,SQLException,Exception {
        B411_UPD_PLPI_PROC();
        if (pgmRtn) return;
        B412_DEL_P_RCVD_REC();
        if (pgmRtn) return;
        B414_GEN_NEXT_CALDATA();
        if (pgmRtn) return;
        B420_UPDATE_LNTICTL();
        if (pgmRtn) return;
        B413_PRI_CNTL_PROC();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.P_CAL_TERM > 1) {
            B401_GET_PLPI_INF();
            if (pgmRtn) return;
        }
    }
    public void B411_UPD_PLPI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '4';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = 'P';
        LNCPLPIM.REC_DATA.KEY.TERM = WS_P_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        WS_P_VAL_DT = LNCPLPIM.REC_DATA.VAL_DT;
        WS_P_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
        LNCPLPIM.FUNC = '2';
        LNCPLPIM.REC_DATA.REC_STS = 'R';
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
    }
    public void B412_DEL_P_RCVD_REC() throws IOException,SQLException,Exception {
        WS_P_NOR_AMT1 = 0;
        WS_I_NOR_AMT1 = 0;
        WS_P_OVD_AMT1 = 0;
        WS_I_OVD_AMT1 = 0;
        IBS.init(SCCGWA, LNCRCVDM);
        LNCRCVDM.FUNC = '4';
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        if (LNCRCAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRCAL.COMM_DATA.SUF_NO);
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = 'P';
        LNCRCVDM.REC_DATA.KEY.TERM = WS_P_TERM;
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
        WS_RCVD_DUE_DT = LNCRCVDM.REC_DATA.DUE_DT;
        WS_REPY_STS = LNCRCVDM.REC_DATA.REPY_STS;
        if (WS_REPY_STS != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_STS, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRCVDM.REC_DATA.P_REC_AMT <= WS_DUE_PRI_AMT) {
            WS_P_NOR_AMT1 = LNCRCVDM.REC_DATA.P_REC_AMT;
            WS_DUE_PRI_AMT -= LNCRCVDM.REC_DATA.P_REC_AMT;
        } else {
            WS_P_OVD_AMT1 = LNCRCVDM.REC_DATA.P_REC_AMT;
        }
        LNCRCAL.COMM_DATA.PRI_AMT += LNCRCVDM.REC_DATA.P_REC_AMT;
        LNCRCVDM.FUNC = '1';
        S000_CALL_LNZRCVDM();
        if (pgmRtn) return;
    }
    public void B413_PRI_CNTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "INTCALR";
        LNCCNEV.COMM_DATA.LN_AC = LNCRCAL.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCRCAL.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = WS_RCVD_DUE_DT;
        LNCCNEV.COMM_DATA.P_AMT = WS_P_NOR_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = WS_P_NOR_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_P_OVD_AMT1;
        LNCCNEV.COMM_DATA.IETM_AMTS[3-1].PRI_AMT = WS_P_OVD_AMT1;
        LNCCNEV.COMM_DATA.P_AMT += WS_P_OVD_AMT1;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0138610")) {
            S000_CALL_LNZCNEV();
            if (pgmRtn) return;
        }
    }
    public void B414_GEN_NEXT_CALDATA() throws IOException,SQLException,Exception {
        LNCICTLM.REC_DATA.P_CAL_TERM = WS_P_TERM;
        LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_P_DUE_DT;
    }
    public void B420_UPDATE_LNTICTL() throws IOException,SQLException,Exception {
        BS02_READUPD_ICTL();
        if (pgmRtn) return;
        LNCICTLM.REC_DATA.P_CAL_TERM = WS_P_TERM;
        LNCICTLM.REC_DATA.P_CAL_DUE_DT = WS_P_DUE_DT;
        B003_REWRITE_ICTL();
        if (pgmRtn) return;
        WS_P_TERM -= 1;
    }
    public void T000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCVDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RCVD-MAINT", LNCRCVDM);
        if (LNCRCVDM.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCRCVDM.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCRCVDM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCPAIPM.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCPAIPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CONT-INQ", LNCICNQ);
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCICNQ.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCICNQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCRCAL.RC.RC_APP = LNCCNEX.RC.RC_APP;
            LNCRCAL.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_READUPD_LNTBALZ_PART() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCRCAL.COMM_DATA.LN_AC;
        LNRBALZ.KEY.SUB_CTA_NO = WS_PART_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        WS_BALZ_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BALZ_FOUND_FLG = 'Y';
        } else {
        }
    }
    public void R000_WRITE_LNTBALZ_PART() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRBALZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.WRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R000_REWRITE_LNTBALZ_PART() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R000_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRELA() throws IOException,SQLException,Exception {
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        IBS.READ(SCCGWA, LNRRELA, LNTRELA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else {
            WS_FND_FLG = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCRCAL=");
            CEP.TRC(SCCGWA, LNCRCAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
