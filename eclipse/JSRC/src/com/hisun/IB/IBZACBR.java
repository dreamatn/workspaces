package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACBR {
    String JIBS_tmp_str[] = new String[10];
    brParm IBTMST_BR = new brParm();
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    short K_Q_MAX_CNT = 5000;
    String K_IBTMST = "IBTMST  ";
    short WS_TS_CNT = 0;
    short WS_COUNT = 0;
    short WS_K = 0;
    String WS_CI_NO = " ";
    String WS_CI_NME = " ";
    IBZACBR_WS_RTN_DATA_A WS_RTN_DATA_A = new IBZACBR_WS_RTN_DATA_A();
    IBZACBR_WS_VARLEN_VAR WS_VARLEN_VAR = new IBZACBR_WS_VARLEN_VAR();
    char WS_TABLE_REC = ' ';
    char WS_INQ_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    IBCQINF IBCQINF = new IBCQINF();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACBR IBCACBR;
    public void MP(SCCGWA SCCGWA, IBCACBR IBCACBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACBR = IBCACBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCACBR.RC.RC_MMO = " ";
        IBCACBR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_AC_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACBR.IN_STS);
        CEP.TRC(SCCGWA, IBCACBR.POST_CTR);
        if (IBCACBR.POST_CTR == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR, IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCACBR.CIFNO);
        if (IBCACBR.CIFNO.trim().length() > 0) {
            B010_01_CHECK_CIFNO();
            if (pgmRtn) return;
        }
    }
    public void B010_01_CHECK_CIFNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = IBCACBR.CIFNO;
        CICCUST.FUNC = 'C';
        S000_LINK_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE == 3011) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_NOEXIST, IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP != '3') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIF_NOFI, IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 358;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        B020_01_BRW_AC_INFO();
        if (pgmRtn) return;
    }
    public void B020_01_BRW_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.POST_CTR = IBCACBR.POST_CTR;
        CEP.TRC(SCCGWA, IBRMST.POST_CTR);
        if (IBCACBR.CORRAC_NO.trim().length() > 0) {
            IBRMST.CORRAC_NO = IBCACBR.CORRAC_NO;
            CEP.TRC(SCCGWA, IBRMST.CORRAC_NO);
            T000_STARTBR_IBTMST1();
            if (pgmRtn) return;
        } else {
            if (IBCACBR.NOS_CD.trim().length() > 0) {
                IBRMST.NOSTRO_CODE = IBCACBR.NOS_CD;
                CEP.TRC(SCCGWA, IBRMST.NOSTRO_CODE);
                T000_STARTBR_IBTMST2();
                if (pgmRtn) return;
            } else {
                if (IBCACBR.CIFNO.trim().length() > 0) {
                    WS_INQ_FLAG = 'C';
                    B020_02_BRW_AC_INFO();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (IBCACBR.CUSTNME.trim().length() > 0) {
                        WS_INQ_FLAG = 'N';
                        B020_02_BRW_AC_INFO();
                        if (pgmRtn) return;
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        if (IBCACBR.CCY.trim().length() > 0) {
                            IBRMST.CCY = IBCACBR.CCY;
                            CEP.TRC(SCCGWA, IBRMST.CCY);
                            T000_STARTBR_IBTMST3();
                            if (pgmRtn) return;
                        } else {
                            T000_STARTBR_IBTMST4();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
        T000_READNEXT_IBTMST();
        if (pgmRtn) return;
        while (WS_TABLE_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = IBRMST.KEY.AC_NO;
            S000_LINK_CIZQACRI();
            if (pgmRtn) return;
            WS_CI_NO = CICQACRI.O_DATA.O_CI_NO;
            WS_CI_NME = CICQACRI.O_DATA.O_AC_CNM;
            CEP.TRC(SCCGWA, IBCACBR.CORRAC_NO);
            CEP.TRC(SCCGWA, IBRMST.CORRAC_NO);
            CEP.TRC(SCCGWA, IBCACBR.NOS_CD);
            CEP.TRC(SCCGWA, IBRMST.NOSTRO_CODE);
            CEP.TRC(SCCGWA, IBCACBR.CIFNO);
            CEP.TRC(SCCGWA, WS_CI_NO);
            CEP.TRC(SCCGWA, IBCACBR.CUSTNME);
            CEP.TRC(SCCGWA, WS_CI_NME);
            CEP.TRC(SCCGWA, IBCACBR.CCY);
            CEP.TRC(SCCGWA, IBRMST.CCY);
            if (WS_TABLE_REC == 'Y' 
                && IBCACBR.IN_STS == ' ') {
                if (!(!(!(IBCACBR.CORRAC_NO.trim().length() > 0) 
                    && (!IBCACBR.CORRAC_NO.equalsIgnoreCase(IBRMST.CORRAC_NO))) 
                    || ((IBCACBR.NOS_CD.trim().length() > 0) 
                    && (!IBCACBR.NOS_CD.equalsIgnoreCase(IBRMST.NOSTRO_CODE))) 
                    || ((IBCACBR.CIFNO.trim().length() > 0) 
                    && (!IBCACBR.CIFNO.equalsIgnoreCase(WS_CI_NO))) 
                    || ((IBCACBR.CUSTNME.trim().length() > 0) 
                    && (!IBCACBR.CUSTNME.equalsIgnoreCase(WS_CI_NME))) 
                    || ((IBCACBR.CCY.trim().length() > 0) 
                    && (!IBCACBR.CCY.equalsIgnoreCase(IBRMST.CCY))))) {
                    R000_01_PROC_OUTPUT();
                    if (pgmRtn) return;
                }
            } else {
                if (WS_TABLE_REC == 'Y' 
                    && IBCACBR.IN_STS != 'C') {
                    if (IBRMST.AC_STS != 'C') {
                        if (!(!(!(IBCACBR.CORRAC_NO.trim().length() > 0) 
                            && (!IBCACBR.CORRAC_NO.equalsIgnoreCase(IBRMST.CORRAC_NO))) 
                            || ((IBCACBR.NOS_CD.trim().length() > 0) 
                            && (!IBCACBR.NOS_CD.equalsIgnoreCase(IBRMST.NOSTRO_CODE))) 
                            || ((IBCACBR.CIFNO.trim().length() > 0) 
                            && (!IBCACBR.CIFNO.equalsIgnoreCase(WS_CI_NO))) 
                            || ((IBCACBR.CUSTNME.trim().length() > 0) 
                            && (!IBCACBR.CUSTNME.equalsIgnoreCase(WS_CI_NME))) 
                            || ((IBCACBR.CCY.trim().length() > 0) 
                            && (!IBCACBR.CCY.equalsIgnoreCase(IBRMST.CCY))))) {
                            R000_01_PROC_OUTPUT();
                            if (pgmRtn) return;
                        }
                    }
                }
                if (WS_TABLE_REC == 'Y' 
                    && IBCACBR.IN_STS == 'C') {
                    if (IBRMST.AC_STS == 'C') {
                        if (!(!(!(IBCACBR.CORRAC_NO.trim().length() > 0) 
                            && (!IBCACBR.CORRAC_NO.equalsIgnoreCase(IBRMST.CORRAC_NO))) 
                            || ((IBCACBR.NOS_CD.trim().length() > 0) 
                            && (!IBCACBR.NOS_CD.equalsIgnoreCase(IBRMST.NOSTRO_CODE))) 
                            || ((IBCACBR.CIFNO.trim().length() > 0) 
                            && (!IBCACBR.CIFNO.equalsIgnoreCase(WS_CI_NO))) 
                            || ((IBCACBR.CUSTNME.trim().length() > 0) 
                            && (!IBCACBR.CUSTNME.equalsIgnoreCase(WS_CI_NME))) 
                            || ((IBCACBR.CCY.trim().length() > 0) 
                            && (!IBCACBR.CCY.equalsIgnoreCase(IBRMST.CCY))))) {
                            R000_01_PROC_OUTPUT();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
            T000_READNEXT_IBTMST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NO_REC, IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_ENDBR_IBTMST();
        if (pgmRtn) return;
    }
    public void B020_02_BRW_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        if (WS_INQ_FLAG == 'C') {
            CICQACRI.FUNC = 'I';
            CICQACRI.DATA.CI_NO = IBCACBR.CIFNO;
        } else {
            CICQACRI.FUNC = 'C';
            CICQACRI.DATA.AC_CNM = IBCACBR.CUSTNME;
        }
        S000_LINK_CIZQACRI();
        if (pgmRtn) return;
        for (WS_K = 1; CICQACRI.AGR_NO_AREA[WS_K-1].AC_NO.trim().length() != 0 
            && WS_K <= 999; WS_K += 1) {
            WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1] = CICQACRI.AGR_NO_AREA[WS_K-1].AC_NO;
        }
        for (WS_K = 1; WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1].trim().length() != 0 
            && WS_K <= 999 
            && SCCMPAG.FUNC != 'E'; WS_K += 1) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1];
            CEP.TRC(SCCGWA, WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1]);
            S000_LINK_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBDD")) {
                R000_PROC_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1];
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.POST_ACT_CTR);
        if (IBCACBR.IN_STS == ' ' 
            && IBCQINF.OUTPUT_DATA.POST_ACT_CTR == IBCACBR.POST_CTR) {
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, IBCACBR.CORRAC_NO);
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CORRAC_NO);
            CEP.TRC(SCCGWA, IBCACBR.CIFNO);
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
            CEP.TRC(SCCGWA, IBCACBR.CUSTNME);
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_CHN_NAME);
            CEP.TRC(SCCGWA, IBCACBR.NOS_CD);
            CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
            CEP.TRC(SCCGWA, IBCACBR.CCY);
            CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
            if (!(!(!(IBCACBR.CORRAC_NO.trim().length() > 0) 
                && (!IBCACBR.CORRAC_NO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CORRAC_NO))) 
                || ((IBCACBR.NOS_CD.trim().length() > 0) 
                && (!IBCACBR.NOS_CD.equalsIgnoreCase(IBCQINF.INPUT_DATA.NOSTRO_CD))) 
                || ((IBCACBR.CIFNO.trim().length() > 0) 
                && (!IBCACBR.CIFNO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CI_NO))) 
                || ((IBCACBR.CUSTNME.trim().length() > 0) 
                && (!IBCACBR.CUSTNME.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.AC_CHN_NAME))) 
                || ((IBCACBR.CCY.trim().length() > 0) 
                && (!IBCACBR.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY))))) {
                R000_02_PROC_OUTPUT();
                if (pgmRtn) return;
            }
        } else {
            if (IBCACBR.IN_STS != 'C') {
                CEP.TRC(SCCGWA, "222");
                if (IBCQINF.OUTPUT_DATA.POST_ACT_CTR == IBCACBR.POST_CTR 
                    && IBCQINF.OUTPUT_DATA.AC_STS != 'C') {
                    CEP.TRC(SCCGWA, "333");
                    CEP.TRC(SCCGWA, IBCACBR.CORRAC_NO);
                    CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CORRAC_NO);
                    CEP.TRC(SCCGWA, IBCACBR.CIFNO);
                    CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
                    CEP.TRC(SCCGWA, IBCACBR.CUSTNME);
                    CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_CHN_NAME);
                    CEP.TRC(SCCGWA, IBCACBR.NOS_CD);
                    CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
                    CEP.TRC(SCCGWA, IBCACBR.CCY);
                    CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
                    if (!(!(!(IBCACBR.CORRAC_NO.trim().length() > 0) 
                        && (!IBCACBR.CORRAC_NO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CORRAC_NO))) 
                        || ((IBCACBR.NOS_CD.trim().length() > 0) 
                        && (!IBCACBR.NOS_CD.equalsIgnoreCase(IBCQINF.INPUT_DATA.NOSTRO_CD))) 
                        || ((IBCACBR.CIFNO.trim().length() > 0) 
                        && (!IBCACBR.CIFNO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CI_NO))) 
                        || ((IBCACBR.CUSTNME.trim().length() > 0) 
                        && (!IBCACBR.CUSTNME.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.AC_CHN_NAME))) 
                        || ((IBCACBR.CCY.trim().length() > 0) 
                        && (!IBCACBR.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY))))) {
                        R000_02_PROC_OUTPUT();
                        if (pgmRtn) return;
                    }
                }
            }
            if (IBCACBR.IN_STS == 'C') {
                CEP.TRC(SCCGWA, "444");
                if (IBCQINF.OUTPUT_DATA.POST_ACT_CTR == IBCACBR.POST_CTR 
                    && IBCQINF.OUTPUT_DATA.AC_STS == 'C') {
                    CEP.TRC(SCCGWA, "555");
                    CEP.TRC(SCCGWA, IBCACBR.CORRAC_NO);
                    CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CORRAC_NO);
                    CEP.TRC(SCCGWA, IBCACBR.CIFNO);
                    CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
                    CEP.TRC(SCCGWA, IBCACBR.CUSTNME);
                    CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_CHN_NAME);
                    CEP.TRC(SCCGWA, IBCACBR.NOS_CD);
                    CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
                    CEP.TRC(SCCGWA, IBCACBR.CCY);
                    CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
                    if (!(!(!(IBCACBR.CORRAC_NO.trim().length() > 0) 
                        && (!IBCACBR.CORRAC_NO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CORRAC_NO))) 
                        || ((IBCACBR.NOS_CD.trim().length() > 0) 
                        && (!IBCACBR.NOS_CD.equalsIgnoreCase(IBCQINF.INPUT_DATA.NOSTRO_CD))) 
                        || ((IBCACBR.CIFNO.trim().length() > 0) 
                        && (!IBCACBR.CIFNO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CI_NO))) 
                        || ((IBCACBR.CUSTNME.trim().length() > 0) 
                        && (!IBCACBR.CUSTNME.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.AC_CHN_NAME))) 
                        || ((IBCACBR.CCY.trim().length() > 0) 
                        && (!IBCACBR.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY))))) {
                        R000_02_PROC_OUTPUT();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R000_01_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBRMST.KEY.AC_NO;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        WS_RTN_DATA_A.WS_R_CIF_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        WS_RTN_DATA_A.WS_R_AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        WS_RTN_DATA_A.WS_R_NOSTRO_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
        WS_RTN_DATA_A.WS_R_CCY = IBCQINF.INPUT_DATA.CCY;
        WS_RTN_DATA_A.WS_R_CUST_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        WS_RTN_DATA_A.WS_R_AVAILBAL = IBCQINF.OUTPUT_DATA.VALUE_BAL;
        WS_RTN_DATA_A.WS_R_AC_STS = IBCQINF.OUTPUT_DATA.AC_STS;
        WS_RTN_DATA_A.WS_R_AC_ATTR = IBCQINF.OUTPUT_DATA.AC_ATTR;
        WS_RTN_DATA_A.WS_R_OPEN_BR = IBCQINF.OUTPUT_DATA.OPEN_BR;
        WS_RTN_DATA_A.WS_R_AC_NATR = IBCQINF.OUTPUT_DATA.AC_NATR;
        WS_RTN_DATA_A.WS_R_PROD_CD = IBCQINF.OUTPUT_DATA.PROD_CD;
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_CIF_NO);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_NATR);
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
        WS_COUNT = (short) (WS_COUNT + 1);
    }
    public void R000_02_PROC_OUTPUT() throws IOException,SQLException,Exception {
        WS_RTN_DATA_A.WS_R_CIF_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        WS_RTN_DATA_A.WS_R_AC_NO = WS_VARLEN_VAR.WS_RTN_AC_NO[WS_K-1];
        WS_RTN_DATA_A.WS_R_NOSTRO_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
        WS_RTN_DATA_A.WS_R_CCY = IBCQINF.INPUT_DATA.CCY;
        WS_RTN_DATA_A.WS_R_CUST_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        WS_RTN_DATA_A.WS_R_AVAILBAL = IBCQINF.OUTPUT_DATA.VALUE_BAL;
        WS_RTN_DATA_A.WS_R_AC_STS = IBCQINF.OUTPUT_DATA.AC_STS;
        WS_RTN_DATA_A.WS_R_AC_ATTR = IBCQINF.OUTPUT_DATA.AC_ATTR;
        WS_RTN_DATA_A.WS_R_OPEN_BR = IBCQINF.OUTPUT_DATA.OPEN_BR;
        WS_RTN_DATA_A.WS_R_AC_NATR = IBCQINF.OUTPUT_DATA.AC_NATR;
        WS_RTN_DATA_A.WS_R_PROD_CD = IBCQINF.OUTPUT_DATA.PROD_CD;
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_NATR);
        CEP.TRC(SCCGWA, WS_RTN_DATA_A.WS_R_CIF_NO);
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
        WS_COUNT = (short) (WS_COUNT + 1);
    }
    public void S000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        if (WS_TS_CNT > K_Q_MAX_CNT) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.OUTOF_TS, IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, "MPAG IN");
        SCCMPAG.FUNC = 'D';
        SCCMPAG.MAX_COL_NO = 358;
        SCCMPAG.DATA_LEN = 358;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = (short) (WS_TS_CNT + 1);
    }
    public void S000_LINK_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0 
            && CICCUST.RC.RC_CODE != 3011) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTMST1() throws IOException,SQLException,Exception {
        IBTMST_BR.rp = new DBParm();
        IBTMST_BR.rp.TableName = "IBTMST";
        IBTMST_BR.rp.where = "CORRAC_NO = :IBRMST.CORRAC_NO "
            + "AND POST_CTR = :IBRMST.POST_CTR";
        IBTMST_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRMST, this, IBTMST_BR);
    }
    public void T000_STARTBR_IBTMST2() throws IOException,SQLException,Exception {
        IBTMST_BR.rp = new DBParm();
        IBTMST_BR.rp.TableName = "IBTMST";
        IBTMST_BR.rp.where = "NOSTRO_CODE = :IBRMST.NOSTRO_CODE "
            + "AND POST_CTR = :IBRMST.POST_CTR";
        IBTMST_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRMST, this, IBTMST_BR);
    }
    public void T000_STARTBR_IBTMST3() throws IOException,SQLException,Exception {
        IBTMST_BR.rp = new DBParm();
        IBTMST_BR.rp.TableName = "IBTMST";
        IBTMST_BR.rp.where = "POST_CTR = :IBRMST.POST_CTR "
            + "AND CCY = :IBRMST.CCY";
        IBTMST_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRMST, this, IBTMST_BR);
    }
    public void T000_STARTBR_IBTMST4() throws IOException,SQLException,Exception {
        IBTMST_BR.rp = new DBParm();
        IBTMST_BR.rp.TableName = "IBTMST";
        IBTMST_BR.rp.where = "POST_CTR = :IBRMST.POST_CTR";
        IBTMST_BR.rp.order = "OPEN_DATE";
        IBS.STARTBR(SCCGWA, IBRMST, this, IBTMST_BR);
    }
    public void T000_READNEXT_IBTMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRMST, this, IBTMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_IBTMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTMST_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
