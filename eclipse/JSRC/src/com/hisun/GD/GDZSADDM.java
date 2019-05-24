package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSADDM {
    brParm TDTSMST_BR = new brParm();
    DBParm GDTPLDR_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD520";
    String WS_ERR_MSG = " ";
    GDZSADDM_WS_PLDR_DETAIL WS_PLDR_DETAIL = new GDZSADDM_WS_PLDR_DETAIL();
    double WS_AVA_BAL = 0;
    double WS_AVL_BAL = 0;
    double WS_PLDR_AMT = 0;
    double WS_NOT_INT_BAL = 0;
    double WS_AVA_RELAT_AMT = 0;
    double WS_HOLD_AMT_BY_LAW = 0;
    double WS_PLDR_AMT_TOT = 0;
    short WS_PLDR_CNT = 0;
    short WS_IDX = 0;
    int WS_TMP_DATE = 0;
    char WS_TMP_FLG = ' ';
    String WS_PLDR_NO = " ";
    double WS_RL_AMT = 0;
    double WS_AVL_RELAT_AMT = 0;
    double WS_AVL_RELAT_AMT2 = 0;
    double WS_RELAT_AMT2 = 0;
    double WS_BAL = 0;
    double WS_AVL_KDBAL = 0;
    GDZSADDM_WS_TXCTA_NO WS_TXCTA_NO = new GDZSADDM_WS_TXCTA_NO();
    int WS_AC_SEQ = 0;
    int WS_AC_SEQ_1 = 0;
    double WS_RELAT_AMT = 0;
    double WS_ADD_AMT = 0;
    double WS_NEED_PLDR_AMT = 0;
    double WS_AVAL_BAL = 0;
    String WS_RSEQ = " ";
    char WS_PLDR_FLG = ' ';
    char WS_HLD_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_UPD_AVA_BAL_FLG = ' ';
    char WS_UPD_PLDR_AMT_FLG = ' ';
    char WS_UPD_NOT_INT_BAL_FLG = ' ';
    char WS_CHK_TOT_AMT_FLG = ' ';
    char WS_CHK_AVA_AMT_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_READ_TWICE = ' ';
    char WS_GDKD_PLDR = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    CICACCU CICACCU = new CICACCU();
    GDCOADDM GDCOADDM = new GDCOADDM();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    int WS_CNT = 0;
    SCCGWA SCCGWA;
    GDCSADDM GDCSADDM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSADDM GDCSADDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSADDM = GDCSADDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSADDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            B015_CHECK_GDKD_PROC();
            if (pgmRtn) return;
        }
        if (DDVMPRD.VAL.TD_FLG == '0') {
            B025_GDKD_PLDR_PROC();
            if (pgmRtn) return;
        } else {
            B010_10_CHECK_RLDR_REC();
            if (pgmRtn) return;
        }
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.RSEQ);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC_SEQ);
        if (GDCSADDM.VAL.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSADDM.VAL.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
    }
    public void B010_10_CHECK_RLDR_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSADDM.VAL.RSEQ);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.SYS_NO);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.CTA_NO);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.REF_NO);
        IBS.init(SCCGWA, GDCRPLDR);
        IBS.init(SCCGWA, GDRPLDR);
        if (GDCSADDM.VAL.RSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDCSADDM.VAL.RSEQ;
        } else {
            GDRPLDR.DEAL_CD = GDCSADDM.VAL.CTA_NO;
            GDRPLDR.BSREF = GDCSADDM.VAL.REF_NO;
        }
        GDRPLDR.KEY.AC = GDCSADDM.VAL.AC;
        GDRPLDR.KEY.AC_SEQ = GDCSADDM.VAL.AC_SEQ;
        if (GDCSADDM.VAL.RSEQ.trim().length() > 0) {
            T000_READ_BY_KEY_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                if (!GDRPLDR.DEAL_CD.equalsIgnoreCase(GDCSADDM.VAL.CTA_NO) 
                    || !GDRPLDR.BSREF.equalsIgnoreCase(GDCSADDM.VAL.REF_NO)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            T000_READ_BY_CTA_GDTPLDR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        WS_RELAT_AMT2 = GDRPLDR.RELAT_AMT;
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (GDCSADDM.VAL.AC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (GDCSADDM.VAL.AC_TYP == ' ') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (GDCSADDM.VAL.CTA_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, GDRPLDR);
            GDRPLDR.DEAL_CD = GDCSADDM.VAL.CTA_NO;
            GDRPLDR.BSREF = GDCSADDM.VAL.REF_NO;
            WS_GDKD_PLDR = 'Y';
            T000_READ_GDTPLDR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                T000_GROUP_GDTPLDR();
                if (pgmRtn) return;
                if (WS_CNT != 0) {
                    CEP.TRC(SCCGWA, "DEAL-CD");
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                GDRPLDR.SYS_NO = GDCSADDM.VAL.SYS_NO;
                GDRPLDR.KEY.RSEQ = GDCSADDM.VAL.RSEQ;
                GDRPLDR.MFG_DATE = 0;
                GDRPLDR.DEAL_CD = GDCSADDM.VAL.CTA_NO;
                GDRPLDR.BSREF = GDCSADDM.VAL.REF_NO;
            } else {
                if (GDCSADDM.VAL.RSEQ.trim().length() > 0 
                    && !GDCSADDM.VAL.RSEQ.equalsIgnoreCase(GDRPLDR.KEY.RSEQ)) {
                    CEP.TRC(SCCGWA, GDCSADDM.VAL.RSEQ);
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    GDCSADDM.VAL.RSEQ = GDRPLDR.KEY.RSEQ;
                }
            }
            CEP.TRC(SCCGWA, GDRPLDR.CCY);
            if (!GDRPLDR.CCY.equalsIgnoreCase(GDCSADDM.VAL.CCY) 
                && GDRPLDR.CCY.trim().length() > 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_ADD_AMT = GDCSADDM.VAL.ADD_AMT;
            WS_AC_SEQ_1 = GDCSADDM.VAL.AC_SEQ;
            B025_CREATE_PLDR_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, GDRPLDR.CCY);
            if (!GDRPLDR.CCY.equalsIgnoreCase(GDCSADDM.VAL.CCY)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!GDRPLDR.BSREF.equalsIgnoreCase(GDCSADDM.VAL.REF_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_REF_NO_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            if (!GDRPLDR.DEAL_CD.equalsIgnoreCase(GDCSADDM.VAL.CTA_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            GDCSADDM.VAL.RSEQ = GDRPLDR.KEY.RSEQ;
            WS_ADD_AMT = GDCSADDM.VAL.ADD_AMT;
            WS_AC_SEQ_1 = GDCSADDM.VAL.AC_SEQ;
            WS_RSEQ = GDCSADDM.VAL.RSEQ;
            B020_ADD_PLDR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSADDM.VAL.AC;
        DDRCCY.CCY = GDCSADDM.VAL.CCY;
        DDRCCY.CCY_TYPE = GDCSADDM.VAL.CCY_TYP;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSADDM.VAL.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B020_ADD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_SEQ_1);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC);
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'A';
        GDCUMPLD.RSEQ = WS_RSEQ;
        GDCUMPLD.AC = GDCSADDM.VAL.AC;
        GDCUMPLD.AC_TYPE = GDCSADDM.VAL.AC_TYP;
        GDCUMPLD.AC_SEQ = WS_AC_SEQ_1;
        GDCUMPLD.CCY = GDRPLDR.CCY;
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            GDCUMPLD.CCY_TYPE = '1';
        } else {
            GDCUMPLD.CCY_TYPE = '2';
        }
        GDCUMPLD.TX_AMT = WS_ADD_AMT;
        GDCUMPLD.SYS_NO = GDCSADDM.VAL.SYS_NO;
        GDCUMPLD.CTA_NO = GDCSADDM.VAL.CTA_NO;
        GDCUMPLD.REF_NO = GDCSADDM.VAL.REF_NO;
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B025_CREATE_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_SEQ_1);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.AC);
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'C';
        GDCUMPLD.AC = GDCSADDM.VAL.AC;
        GDCUMPLD.AC_TYPE = GDCSADDM.VAL.AC_TYP;
        GDCUMPLD.AC_SEQ = WS_AC_SEQ_1;
        GDCUMPLD.CCY = GDCSADDM.VAL.CCY;
        if (GDCSADDM.VAL.CCY.equalsIgnoreCase("156")) {
            GDCUMPLD.CCY_TYPE = '1';
        } else {
            GDCUMPLD.CCY_TYPE = '2';
        }
        GDCUMPLD.SYS_NO = GDRPLDR.SYS_NO;
        GDCUMPLD.RSEQ = GDRPLDR.KEY.RSEQ;
        GDCUMPLD.TX_AMT = WS_ADD_AMT;
        GDCUMPLD.EXP_DATE = GDRPLDR.MFG_DATE;
        GDCUMPLD.CTA_NO = GDRPLDR.DEAL_CD;
        GDCUMPLD.REF_NO = GDRPLDR.BSREF;
        GDCUMPLD.BUSI_TYPE = GDRPLDR.REF_TYP;
        GDCUMPLD.PN_AMT = GDRPLDR.PALT_AMT;
        GDCUMPLD.MMO = "A420";
        if (WS_GDKD_PLDR == 'Y') {
            GDCUMPLD.KDGD_FLG = 'Y';
        }
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        WS_AVAL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        CEP.TRC(SCCGWA, WS_AVAL_BAL);
        CEP.TRC(SCCGWA, GDCSADDM.VAL.ADD_AMT);
        if (WS_AVAL_BAL < GDCSADDM.VAL.ADD_AMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NEED_PLDR_AMT = GDCSADDM.VAL.ADD_AMT;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = GDCSADDM.VAL.AC;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N' 
            && WS_NEED_PLDR_AMT != 0) {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_AVL_KDBAL = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            if (WS_AVL_KDBAL > WS_NEED_PLDR_AMT) {
                WS_RELAT_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELAT_AMT = WS_AVL_KDBAL;
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
            WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            IBS.init(SCCGWA, GDRPLDR);
            GDRPLDR.KEY.AC = GDCSADDM.VAL.AC;
            GDRPLDR.KEY.AC_SEQ = WS_AC_SEQ;
            GDRPLDR.DEAL_CD = GDCSADDM.VAL.CTA_NO;
            GDRPLDR.BSREF = GDCSADDM.VAL.REF_NO;
            T000_READ_BY_CTA_GDTPLDR();
            if (pgmRtn) return;
            if (WS_PLDR_FLG == 'N') {
                CEP.TRC(SCCGWA, "ADD NEW REC");
                WS_ADD_AMT = WS_RELAT_AMT;
                WS_AC_SEQ_1 = WS_AC_SEQ;
                WS_GDKD_PLDR = 'Y';
                IBS.init(SCCGWA, GDRPLDR);
                GDRPLDR.DEAL_CD = GDCSADDM.VAL.CTA_NO;
                GDRPLDR.BSREF = GDCSADDM.VAL.REF_NO;
                T000_READ_GDTPLDR();
                if (pgmRtn) return;
                B025_CREATE_PLDR_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "MOD RELAT AMT");
                WS_ADD_AMT = WS_RELAT_AMT;
                WS_AC_SEQ_1 = WS_AC_SEQ;
                WS_RSEQ = GDRPLDR.KEY.RSEQ;
                B020_ADD_PLDR_PROC();
                if (pgmRtn) return;
            }
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELAT_AMT;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELAT_AMT);
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCSADDM.VAL.ADD_AMT;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSADDM.VAL.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        GDCSADDM.VAL.BS_AMT = WS_RELAT_AMT2 + GDCSADDM.VAL.ADD_AMT;
        WS_AVA_RELAT_AMT = GDCUMPLD.AVA_RAMT - GDCSADDM.VAL.ADD_AMT;
        IBS.init(SCCGWA, GDCOADDM);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        GDCOADDM.VAL.CI_NO = CICACCU.DATA.CI_NO;
        GDCOADDM.VAL.CI_NM = CICACCU.DATA.CI_CNM;
        GDCOADDM.VAL.AC = GDCSADDM.VAL.AC;
        if (GDCSADDM.VAL.AC_NM.trim().length() == 0) {
            GDCOADDM.VAL.AC_NM = CICACCU.DATA.AC_CNM;
        } else {
            GDCOADDM.VAL.AC_NM = GDCSADDM.VAL.AC_NM;
        }
        GDCOADDM.VAL.AC_SEQ = GDCSADDM.VAL.AC_SEQ;
        GDCOADDM.VAL.PROD_CD = GDCUMPLD.PROD_CD;
        GDCOADDM.VAL.CCY = GDCSADDM.VAL.CCY;
        GDCOADDM.VAL.CCY_TYP = GDCUMPLD.CCY_TYPE;
        GDCOADDM.VAL.BAL = GDCUMPLD.BAL;
        GDCOADDM.VAL.AVL_AMT = WS_AVA_RELAT_AMT;
        GDCOADDM.VAL.BS_AMT = GDCSADDM.VAL.BS_AMT;
        GDCOADDM.VAL.SYS_NO = GDCSADDM.VAL.SYS_NO;
        GDCOADDM.VAL.CTA_NO = GDCSADDM.VAL.CTA_NO;
        GDCOADDM.VAL.REF_NO = GDCSADDM.VAL.REF_NO;
        GDCOADDM.VAL.RL_AMT = WS_RELAT_AMT2;
        GDCOADDM.VAL.ADD_AMT = GDCSADDM.VAL.ADD_AMT;
        GDCOADDM.VAL.RMK = GDCSADDM.VAL.RMK;
        SCCFMT.DATA_PTR = GDCOADDM;
        SCCFMT.DATA_LEN = 834;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ( BAL _ HBAL_ GUAR_BAL ) > 0 "
            + "AND ACO_STS = '0'";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.order = "VAL_DATE,ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_READ_BY_KEY_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = 'N'";
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_READ_BY_CTA_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
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
