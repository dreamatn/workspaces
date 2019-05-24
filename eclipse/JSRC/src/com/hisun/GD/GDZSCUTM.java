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

public class GDZSCUTM {
    DBParm GDTPLDR_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD530";
    String K_CHNL_TLR = "100100";
    String WS_ERR_MSG = " ";
    GDZSCUTM_WS_PLDR_DETAIL WS_PLDR_DETAIL = new GDZSCUTM_WS_PLDR_DETAIL();
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
    double WS_BAL = 0;
    GDZSCUTM_WS_TXCTA_NO WS_TXCTA_NO = new GDZSCUTM_WS_TXCTA_NO();
    double WS_NEED_PLDR_AMT = 0;
    double WS_RELSE_AMT = 0;
    double WS_CUT_AMT = 0;
    int WS_AC_SEQ = 0;
    String WS_RSEQ = " ";
    char WS_PLDR_FLG = ' ';
    char WS_HLD_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_UPD_AVA_BAL_FLG = ' ';
    char WS_UPD_PLDR_AMT_FLG = ' ';
    char WS_UPD_NOT_INT_BAL_FLG = ' ';
    char WS_CHK_TOT_AMT_FLG = ' ';
    char WS_CHK_AVA_AMT_FLG = ' ';
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
    GDCOCUTM GDCOCUTM = new GDCOCUTM();
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
    double WS_ALL_RELAT_AMT = 0;
    SCCGWA SCCGWA;
    GDCSCUTM GDCSCUTM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSCUTM GDCSCUTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSCUTM = GDCSCUTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSCUTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSCUTM.VAL.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            B015_CHECK_GDKD_PROC();
            if (pgmRtn) return;
        }
        if (DDVMPRD.VAL.TD_FLG == '0') {
            B025_GDKD_PLDR_PROC();
            if (pgmRtn) return;
        } else {
            B010_CHECK_DATA_VALIDITY();
            if (pgmRtn) return;
            WS_AC_SEQ = GDCSCUTM.VAL.AC_SEQ;
            if (GDCSCUTM.VAL.RSEQ.trim().length() > 0) {
                WS_RSEQ = GDCSCUTM.VAL.RSEQ;
            }
            WS_CUT_AMT = GDCSCUTM.VAL.CUT_AMT;
            CEP.TRC(SCCGWA, GDCSCUTM.VAL.CUT_AMT);
            B020_RELEASE_PLDR_PROC();
            if (pgmRtn) return;
        }
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        B010_10_CHECK_RLDR_REC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        if (GDCRPLDR.RETURN_INFO == 'N' 
            || GDRPLDR.RELAT_STS == 'R') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INACTIVE_RSEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.RSEQ);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC_SEQ);
        if (GDCSCUTM.VAL.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC_TYP);
        if (GDCSCUTM.VAL.AC_TYP == ' ') {
            GDCSCUTM.VAL.AC_TYP = GDRPLDR.AC_TYP;
        }
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC_TYP);
    }
    public void B010_10_CHECK_RLDR_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.RSEQ);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.SYS_NO);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.CTA_NO);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.REF_NO);
        IBS.init(SCCGWA, GDCRPLDR);
        IBS.init(SCCGWA, GDRPLDR);
        if (GDCSCUTM.VAL.RSEQ.trim().length() > 0) {
            GDCRPLDR.FUNC = 'I';
            GDRPLDR.KEY.RSEQ = GDCSCUTM.VAL.RSEQ;
            GDRPLDR.KEY.AC = GDCSCUTM.VAL.AC;
            GDRPLDR.KEY.AC_SEQ = GDCSCUTM.VAL.AC_SEQ;
        } else {
            GDCRPLDR.FUNC = 'C';
            GDRPLDR.SYS_NO = GDCSCUTM.VAL.SYS_NO;
            GDRPLDR.DEAL_CD = GDCSCUTM.VAL.CTA_NO;
            GDRPLDR.BSREF = GDCSCUTM.VAL.REF_NO;
            GDRPLDR.KEY.AC = GDCSCUTM.VAL.AC;
            GDRPLDR.KEY.AC_SEQ = GDCSCUTM.VAL.AC_SEQ;
        }
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.CCY);
        CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.SYS_NO);
        WS_RSEQ = GDRPLDR.KEY.RSEQ;
        if (GDRPLDR.SYS_NO.equalsIgnoreCase(K_CHNL_TLR) 
            && !GDRPLDR.SYS_NO.equalsIgnoreCase(GDCSCUTM.VAL.SYS_NO)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANL_INVAILD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!GDRPLDR.CCY.equalsIgnoreCase(GDCSCUTM.VAL.CCY)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!GDRPLDR.BSREF.equalsIgnoreCase(GDCSCUTM.VAL.REF_NO)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
        if (!GDRPLDR.DEAL_CD.equalsIgnoreCase(GDCSCUTM.VAL.CTA_NO)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.AC);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSCUTM.VAL.AC;
        DDRCCY.CCY = GDCSCUTM.VAL.CCY;
        DDRCCY.CCY_TYPE = GDCSCUTM.VAL.CCY_TYP;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSCUTM.VAL.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B020_RELEASE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPLD);
        CEP.TRC(SCCGWA, WS_CUT_AMT);
        GDCUMPLD.FUNC = 'R';
        GDCUMPLD.AC = GDCSCUTM.VAL.AC;
        GDCUMPLD.AC_TYPE = GDCSCUTM.VAL.AC_TYP;
        GDCUMPLD.AC_SEQ = WS_AC_SEQ;
        GDCUMPLD.CCY = GDCSCUTM.VAL.CCY;
        if (GDCSCUTM.VAL.CCY.equalsIgnoreCase("156")) {
            GDCUMPLD.CCY_TYPE = '1';
        } else {
            GDCUMPLD.CCY_TYPE = GDCSCUTM.VAL.CCY_TYP;
        }
        GDCUMPLD.RSEQ = WS_RSEQ;
        GDCUMPLD.TX_AMT = WS_CUT_AMT;
        GDCUMPLD.SYS_NO = GDCSCUTM.VAL.SYS_NO;
        GDCUMPLD.CTA_NO = GDCSCUTM.VAL.CTA_NO;
        GDCUMPLD.REF_NO = GDCSCUTM.VAL.REF_NO;
        GDCUMPLD.MMO = "A421";
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B020_DELETE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'D';
        GDCUMPLD.RSEQ = GDRPLDR.KEY.RSEQ;
        GDCUMPLD.AC = GDRPLDR.KEY.AC;
        GDCUMPLD.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        T000_GROUP_GDTPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.CUT_AMT);
        if (WS_ALL_RELAT_AMT < GDCSCUTM.VAL.CUT_AMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TAMT_MORETHAN_RAMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NEED_PLDR_AMT = GDCSCUTM.VAL.CUT_AMT;
        GDRPLDR.KEY.AC = GDCSCUTM.VAL.AC;
        GDRPLDR.DEAL_CD = GDCSCUTM.VAL.CTA_NO;
        GDRPLDR.BSREF = GDCSCUTM.VAL.REF_NO;
        T000_STARTBR_GDTPLDR();
        if (pgmRtn) return;
        T000_READNEXT_GDTPLDR();
        if (pgmRtn) return;
        while (WS_PLDR_FLG != 'N' 
            && WS_NEED_PLDR_AMT != 0) {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
            CEP.TRC(SCCGWA, GDCSCUTM.VAL.SYS_NO);
            if (GDRPLDR.SYS_NO.equalsIgnoreCase(K_CHNL_TLR) 
                && !GDRPLDR.SYS_NO.equalsIgnoreCase(GDCSCUTM.VAL.SYS_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANL_INVAILD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (GDRPLDR.RELAT_AMT > WS_NEED_PLDR_AMT) {
                WS_RELSE_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELSE_AMT = GDRPLDR.RELAT_AMT;
            }
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
            if (WS_RELSE_AMT != GDRPLDR.RELAT_AMT) {
                WS_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
                WS_RSEQ = GDRPLDR.KEY.RSEQ;
                WS_CUT_AMT = WS_RELSE_AMT;
                B020_RELEASE_PLDR_PROC();
                if (pgmRtn) return;
            } else {
                B020_DELETE_PLDR_PROC();
                if (pgmRtn) return;
            }
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELSE_AMT;
            T000_READNEXT_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
        }
        T000_ENDBR_GDTPLDR();
        if (pgmRtn) return;
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - GDCSCUTM.VAL.CUT_AMT;
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, GDCSCUTM.VAL.CUT_AMT);
        if (DDRCCY.MARGIN_BAL < 0) {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSCUTM.VAL.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        IBS.init(SCCGWA, GDCOCUTM);
        SCCFMT.FMTID = "GD530";
        CEP.TRC(SCCGWA, "......GD530");
        GDCOCUTM.VAL.CI_NO = CICACCU.DATA.CI_NO;
        GDCOCUTM.VAL.CI_NM = CICACCU.DATA.CI_CNM;
        GDCOCUTM.VAL.AC = GDCSCUTM.VAL.AC;
        if (GDCSCUTM.VAL.AC_NM.trim().length() == 0) {
            GDCOCUTM.VAL.AC_NM = CICACCU.DATA.AC_CNM;
        } else {
            GDCOCUTM.VAL.AC_NM = GDCSCUTM.VAL.AC_NM;
        }
        GDCOCUTM.VAL.AC_SEQ = GDCSCUTM.VAL.AC_SEQ;
        GDCOCUTM.VAL.PROD_CD = GDCUMPLD.PROD_CD;
        GDCOCUTM.VAL.CCY = GDCSCUTM.VAL.CCY;
        GDCOCUTM.VAL.CCY_TYP = GDCUMPLD.CCY_TYPE;
        GDCOCUTM.VAL.BAL = GDCUMPLD.BAL;
        GDCOCUTM.VAL.CTA_NO = GDCSCUTM.VAL.CTA_NO;
        GDCOCUTM.VAL.REF_NO = GDCSCUTM.VAL.REF_NO;
        GDCOCUTM.VAL.RL_AMT = GDCUMPLD.TOT_RAMT;
        GDCOCUTM.VAL.CUT_AMT = GDCSCUTM.VAL.CUT_AMT;
        GDCOCUTM.VAL.RMK = GDCSCUTM.VAL.RMK;
        SCCFMT.DATA_PTR = GDCOCUTM;
        SCCFMT.DATA_LEN = 796;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCSCUTM.VAL.AC;
        GDRPLDR.KEY.AC_SEQ = GDCSCUTM.VAL.AC_SEQ;
        GDRPLDR.RELAT_STS = 'N';
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = 'N' "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC_SEQ DESC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLDR_FLG = 'Y';
        } else {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
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
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
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
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
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
