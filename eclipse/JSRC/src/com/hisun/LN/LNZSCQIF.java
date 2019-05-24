package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCQIF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_RC_APP = " ";
    short WS_RC_RTNCODE = 0;
    String WS_ERR_MSG = " ";
    int WS_RCVD_INT_DT = 0;
    int WS_RCVD_PRIN_DT = 0;
    short WS_INT_DAYS = 0;
    short WS_PRIN_DAYS = 0;
    char WS_CAL_END_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCSQUAL LNCSQUAL = new LNCSQUAL();
    LNCQ01 LNCQ01 = new LNCQ01();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSCQIF LNCSCQIF;
    public void MP(SCCGWA SCCGWA, LNCSCQIF LNCSCQIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCQIF = LNCSCQIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        C000_OUTPUT_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCQIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        LNRLOAN.KEY.CONTRACT_NO = LNCSCQIF.CTA_NO;
        LNCRLOAN.FUNC = 'I';
        S000_CALL_LNZRLOAN();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCQIF.CTA_NO);
        IBS.init(SCCGWA, LNCICIQ);
        LNCICIQ.DATA.CONT_NO = LNCSCQIF.CTA_NO;
        LNCICIQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCICIQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICIQ.DATA.SUB_CONT_NO = "0" + LNCICIQ.DATA.SUB_CONT_NO;
        LNCICIQ.FUNC = 'M';
        S000_CALL_LNZICIQ();
        if (pgmRtn) return;
        LNCSCQIF.BOOK_BR = LNCICIQ.DATA.BR;
        LNCSCQIF.CI_NO = LNCICIQ.DATA.CI_NO;
        LNCSCQIF.CI_SNM = LNCICIQ.DATA.CI_SNAME;
        LNCSCQIF.CITY_CD = LNCICIQ.DATA.CITY_CD;
        LNCSCQIF.CI_ENM = LNCICIQ.DATA.CI_ENAME;
        LNCSCQIF.CI_CNM = LNCICIQ.DATA.CI_CNAME;
        LNCSCQIF.PROD_CD = LNCICIQ.DATA.PROD_CD;
        LNCSCQIF.CCY = LNCICIQ.DATA.CCY;
        LNCSCQIF.AMT = LNCICIQ.DATA.AMT;
        LNCSCQIF.OS_BAL = LNCICIQ.DATA.BAL;
        LNCSCQIF.CLDD_START_DT = LNCICIQ.DATA.VAL_DT;
        LNCSCQIF.CLDD_END_DT = LNCICIQ.DATA.DUE_DT;
        LNRICTL.KEY.CONTRACT_NO = LNCSCQIF.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            LNCSCQIF.CLDD_STS = 'Y';
            LNCSCQIF.STOP_ACRU_DT = LNRLOAN.STOP_INT_DATE;
        } else {
            LNCSCQIF.CLDD_STS = 'N';
            LNCSCQIF.STOP_ACRU_DT = 0;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            LNCSCQIF.NACR_STS = 'Y';
        } else {
            LNCSCQIF.NACR_STS = 'N';
        }
        IBS.init(SCCGWA, LNCSPDQ);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSCQIF.CTA_NO;
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "" + 0;
        JIBS_tmp_int = LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ = "0" + LNCSPDQ.COMM_DATA.CTA_INFO.CTA_SEQ;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCSPDQ.FUNC_CODE = 'E';
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        LNCSCQIF.ARR_AMT = LNCSPDQ.ARREAR_DATAS.PIA_AMT;
        LNCSCQIF.ARR_AMT_DAY = LNCSPDQ.ARREAR_DATAS.PIA_DAYS;
        LNCSCQIF.ARR_INT = LNCSPDQ.ARREAR_DATAS.IIA_AMT;
        LNCSCQIF.ARR_INT_DAY = LNCSPDQ.ARREAR_DATAS.IIA_DAYS;
        IBS.init(SCCGWA, LNCSQUAL);
        LNCSQUAL.DATA_AREA.KEY.CONTRACT_NO = LNCSCQIF.CTA_NO;
        LNCSQUAL.FUNC = 'I';
        LNCSCQIF.DFT_STATUS = LNCSQUAL.DATA_AREA.DFT_STATUS;
        LNCSCQIF.CLS = LNCSQUAL.DATA_AREA.CLS;
        LNCSCQIF.CLS_DATE = LNCSQUAL.DATA_AREA.CLS_DATE;
        LNCSCQIF.CLS_REGUL = LNCSQUAL.DATA_AREA.CLS_REGUL;
        LNCSCQIF.CLS_REGUL_DATE = LNCSQUAL.DATA_AREA.CLS_REGUL_DATE;
        LNCSCQIF.CLS_HO = LNCSQUAL.DATA_AREA.CLS_HO;
        LNCSCQIF.CLS_HO_DATE = LNCSQUAL.DATA_AREA.CLS_HO_DATE;
        LNCSCQIF.LGD = LNCSQUAL.DATA_AREA.LGD;
        LNCSCQIF.EXPECTED_LOSS = LNCSQUAL.DATA_AREA.EXPECTED_LOSS;
        LNCSCQIF.COL_IMPAIR_AMT = LNCSQUAL.DATA_AREA.COL_IMPAIR_AMT;
        LNCSCQIF.IND_IMPAIR_AMT = LNCSQUAL.DATA_AREA.IND_IMPAIR_AMT;
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCQ01);
        IBS.init(SCCGWA, SCCFMT);
        LNCQ01.BOOK_BR = LNCSCQIF.BOOK_BR;
        LNCQ01.CI_NO = LNCSCQIF.CI_NO;
        LNCQ01.CI_SNM = LNCSCQIF.CI_SNM;
        LNCQ01.CITY_CD = LNCSCQIF.CITY_CD;
        LNCQ01.PROD_CD = LNCSCQIF.PROD_CD;
        LNCQ01.CI_ENM = LNCSCQIF.CI_ENM;
        LNCQ01.CI_CNM = LNCSCQIF.CI_CNM;
        LNCQ01.FILLER = 0X02;
        LNCQ01.CCY = LNCSCQIF.CCY;
        LNCQ01.AMT = LNCSCQIF.AMT;
        LNCQ01.OS_BAL = LNCSCQIF.OS_BAL;
        LNCQ01.OIC = LNCSCQIF.OIC;
        LNCQ01.BANK_PORTFOLIO = LNCSCQIF.BANK_PORTFOLIO;
        LNCQ01.CLDD_START_DT = LNCSCQIF.CLDD_START_DT;
        LNCQ01.CLDD_END_DT = LNCSCQIF.CLDD_END_DT;
        LNCQ01.ARR_AMT = LNCSCQIF.ARR_AMT;
        LNCQ01.ARR_AMT_DAY = LNCSCQIF.ARR_AMT_DAY;
        LNCQ01.ARR_INT = LNCSCQIF.ARR_INT;
        LNCQ01.ARR_INT_DAY = LNCSCQIF.ARR_INT_DAY;
        LNCQ01.DFT_STATUS = LNCSCQIF.DFT_STATUS;
        LNCQ01.CLS = LNCSCQIF.CLS;
        LNCQ01.CLS_DATE = LNCSCQIF.CLS_DATE;
        LNCQ01.CLS_REGUL = LNCSCQIF.CLS_REGUL;
        LNCQ01.CLS_REGUL_DATE = LNCSCQIF.CLS_REGUL_DATE;
        LNCQ01.CLS_HO = LNCSCQIF.CLS_HO;
        LNCQ01.CLS_HO_DATE = LNCSCQIF.CLS_HO_DATE;
        LNCQ01.LGD = LNCSCQIF.LGD;
        LNCQ01.EXPECTED_LOSS = LNCSCQIF.EXPECTED_LOSS;
        LNCQ01.COL_IMPAIR_AMT = LNCSCQIF.COL_IMPAIR_AMT;
        LNCQ01.IND_IMPAIR_AMT = LNCSCQIF.IND_IMPAIR_AMT;
        LNCQ01.CLDD_STS = LNCSCQIF.CLDD_STS;
        LNCQ01.STOP_ACRU_DT = LNCSCQIF.STOP_ACRU_DT;
        LNCQ01.NACR_STS = LNCSCQIF.NACR_STS;
        LNCQ01.INT_ACCR = LNCICIQ.DATA.INT_ACCR;
        CEP.TRC(SCCGWA, LNCQ01);
        SCCFMT.FMTID = "LNQ01";
        SCCFMT.DATA_PTR = LNCQ01;
        SCCFMT.DATA_LEN = 743;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CAL_ARREARS_DAYS() throws IOException,SQLException,Exception {
        WS_CAL_END_FLAG = 'N';
        B300_STARTBR_LNTRCVD();
        if (pgmRtn) return;
        B300_READNEXT_LNTRCVD();
        if (pgmRtn) return;
        while (LNCRRCVD.RETURN_INFO != 'E' 
            && WS_CAL_END_FLAG != 'Y') {
            if (WS_RCVD_PRIN_DT == 0 
                && LNRRCVD.KEY.AMT_TYP == 'P' 
                && LNRRCVD.REPY_STS != '2') {
                WS_RCVD_PRIN_DT = LNRRCVD.DUE_DT;
            }
            if (WS_RCVD_INT_DT == 0 
                && LNRRCVD.KEY.AMT_TYP == 'I' 
                && LNRRCVD.REPY_STS != '2') {
                WS_RCVD_INT_DT = LNRRCVD.DUE_DT;
            }
            if (WS_RCVD_INT_DT != 0 
                && WS_RCVD_PRIN_DT != 0) {
                WS_CAL_END_FLAG = 'Y';
            }
            B300_READNEXT_LNTRCVD();
            if (pgmRtn) return;
        }
        B300_ENDBR_LNTRCVD();
        if (pgmRtn) return;
        if (WS_RCVD_INT_DT == 0) {
            WS_INT_DAYS = 0;
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_RCVD_INT_DT;
            SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_INT_DAYS = (short) SCCCLDT.DAYS;
        }
        if (WS_RCVD_PRIN_DT == 0) {
            WS_PRIN_DAYS = 0;
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_RCVD_PRIN_DT;
            SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_PRIN_DAYS = (short) SCCCLDT.DAYS;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSQUAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-LNTQUAL", LNCSQUAL, true);
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ, true);
        if (LNCICIQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        IBS.init(SCCGWA, LNCRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSCQIF.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.DUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'N';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void B300_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'R';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void B300_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'E';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 477;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RC.RC_CODE != 0) {
            if (LNCRRCVD.RETURN_INFO == 'N' 
                || LNCRRCVD.RETURN_INFO == 'E') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRLOAN() throws IOException,SQLException,Exception {
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        if (LNCRLOAN.RC.RC_CODE != 0) {
            if (LNCRLOAN.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
