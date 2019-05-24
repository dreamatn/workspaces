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

public class GDZSMPRL {
    int JIBS_tmp_int;
    DBParm GDTPLDR_RD;
    DBParm DDTMST_RD;
    DBParm TDTCMST_RD;
    DBParm DDTCCY_RD;
    DBParm TDTSMST_RD;
    brParm TDTSMST_BR = new brParm();
    brParm GDTPLDR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD510";
    String WS_ERR_MSG = " ";
    double WS_AVA_RELAT_AMT = 0;
    int WS_AC_SEQ = 0;
    double WS_RELAT_AMT = 0;
    double WS_NEED_PLDR_AMT = 0;
    double WS_AVAL_BAL = 0;
    String WS_RSEQ = " ";
    double WS_AVL_SMST_AMT = 0;
    double WS_RL_AMT = 0;
    double WS_AVL_BAL = 0;
    double WS_NEED_RL_AMT = 0;
    double WS_AVL_RAMT = 0;
    char WS_PLDR_FLG = ' ';
    char WS_PLDR_CTA_FLG = ' ';
    char WS_PLDR_FLG_1 = ' ';
    char WS_SMST_FLG = ' ';
    char WS_READ_TWICE = ' ';
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
    GDCOMPRL GDCOMPRL = new GDCOMPRL();
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
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    double WS_ALL_RELAT_AMT = 0;
    double WS_KDGD_RELAT_AMT = 0;
    DDRMST DDRMST = new DDRMST();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDCSMPRL GDCSMPRL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSMPRL GDCSMPRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSMPRL = GDCSMPRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSMPRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.BUSI_TYP);
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            B015_CHECK_GDKD_PROC();
            if (pgmRtn) return;
        }
        B015_01_CHECK_GDKD_PROC();
        if (pgmRtn) return;
        if (DDVMPRD.VAL.TD_FLG != '0') {
            B040_CHECK_PLDR_PROC();
            if (pgmRtn) return;
        }
        if (WS_PLDR_FLG == 'N' 
            && DDVMPRD.VAL.TD_FLG != '0') {
            B020_CREATE_PLDR_PROC();
            if (pgmRtn) return;
        }
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC);
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC_SEQ);
        if (GDCSMPRL.KEY.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSMPRL.VAL.AC_TYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSMPRL.VAL.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSMPRL.VAL.BUSI_TYP.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSMPRL.KEY.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        R000_CHECK_DRAC_STATUS();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSMPRL.KEY.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.YW_TYP);
            if (!GDCSMPRL.VAL.BUSI_TYP.equalsIgnoreCase(DDRMST.YW_TYP)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYP_NOT_MTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = GDCSMPRL.KEY.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRCMST.REF_TYP);
            if (!GDCSMPRL.VAL.BUSI_TYP.equalsIgnoreCase(TDRCMST.REF_TYP)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYP_NOT_MTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSMPRL.KEY.AC;
        DDRCCY.CCY = GDCSMPRL.VAL.CCY;
        DDRCCY.CCY_TYPE = GDCSMPRL.VAL.CCY_TYP;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSMPRL.VAL.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B015_01_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.TD_FLG == '0') {
            B040_CHECK_KDPLDR_PROC();
            if (pgmRtn) return;
            B025_GDKD_PLDR_PROC();
            if (pgmRtn) return;
        } else {
            WS_AC_SEQ = GDCSMPRL.KEY.AC_SEQ;
            WS_RELAT_AMT = GDCSMPRL.VAL.RL_AMT;
            WS_RSEQ = GDCSMPRL.KEY.RSEQ;
        }
    }
    public void B020_CREATE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'C';
        GDCUMPLD.AC = GDCSMPRL.KEY.AC;
        GDCUMPLD.AC_TYPE = GDCSMPRL.VAL.AC_TYP;
        GDCUMPLD.AC_SEQ = WS_AC_SEQ;
        GDCUMPLD.CCY = GDCSMPRL.VAL.CCY;
        if (GDCSMPRL.VAL.CCY.equalsIgnoreCase("156")) {
            GDCUMPLD.CCY_TYPE = '1';
        } else {
            GDCUMPLD.CCY_TYPE = '2';
        }
        GDCUMPLD.SYS_NO = GDCSMPRL.VAL.SYS_NO;
        GDCUMPLD.RSEQ = WS_RSEQ;
        GDCUMPLD.TX_AMT = GDCSMPRL.VAL.RL_AMT;
        GDCUMPLD.TX_AMT = WS_RELAT_AMT;
        GDCUMPLD.EXP_DATE = GDCSMPRL.VAL.EXP_DT;
        GDCUMPLD.CTA_NO = GDCSMPRL.VAL.CTA_NO;
        GDCUMPLD.REF_NO = GDCSMPRL.VAL.REF_NO;
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.BUSI_TYP);
        GDCUMPLD.BUSI_TYPE = GDCSMPRL.VAL.BUSI_TYP;
        GDCUMPLD.PN_AMT = GDCSMPRL.VAL.PN_AMT;
        GDCUMPLD.MMO = "P618";
        GDCUMPLD.RELAT_WAY = GDCSMPRL.VAL.RELAT_WAY;
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        WS_AVAL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        CEP.TRC(SCCGWA, WS_AVAL_BAL);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.RL_AMT);
        if (WS_AVAL_BAL < GDCSMPRL.VAL.RL_AMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NEED_PLDR_AMT = GDCSMPRL.VAL.RL_AMT;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = GDCSMPRL.KEY.AC;
        T000_STARTBR_TDTSMST_1();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        if (WS_SMST_FLG == 'N' 
            || WS_NEED_PLDR_AMT == 0) {
            B020_CREATE_PLDR_PROC();
            if (pgmRtn) return;
        }
        while (WS_SMST_FLG != 'N' 
            && WS_NEED_PLDR_AMT != 0) {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_AVL_RAMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            CEP.TRC(SCCGWA, WS_AVL_RAMT);
            if (WS_AVL_RAMT > WS_NEED_PLDR_AMT) {
                WS_RELAT_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELAT_AMT = WS_AVL_RAMT;
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
            WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            if (WS_READ_TWICE == 'Y') {
                WS_RSEQ = GDCUMPLD.O_RSEQ;
            }
            CEP.TRC(SCCGWA, WS_RSEQ);
            B020_CREATE_PLDR_PROC();
            if (pgmRtn) return;
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELAT_AMT;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            WS_READ_TWICE = 'Y';
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELAT_AMT);
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.RL_AMT);
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCSMPRL.VAL.RL_AMT;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSMPRL.KEY.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        WS_AVA_RELAT_AMT = GDCUMPLD.AVA_RAMT - GDCSMPRL.VAL.RL_AMT;
        IBS.init(SCCGWA, GDCOMPRL);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        GDCOMPRL.VAL.CI_NO = CICACCU.DATA.CI_NO;
        GDCOMPRL.VAL.CI_NM = CICACCU.DATA.CI_CNM;
        GDCOMPRL.VAL.AC = GDCSMPRL.KEY.AC;
        if (GDCSMPRL.VAL.AC_NM.trim().length() == 0) {
            GDCOMPRL.VAL.AC_NM = CICACCU.DATA.AC_CNM;
        } else {
            GDCOMPRL.VAL.AC_NM = GDCSMPRL.VAL.AC_NM;
        }
        GDCOMPRL.VAL.AC_SEQ = GDCSMPRL.KEY.AC_SEQ;
        GDCOMPRL.VAL.PROD_CD = GDCUMPLD.PROD_CD;
        GDCOMPRL.VAL.CCY = GDCSMPRL.VAL.CCY;
        GDCOMPRL.VAL.CCY_TYP = GDCUMPLD.CCY_TYPE;
        GDCOMPRL.VAL.BAL = GDCUMPLD.BAL;
        CEP.TRC(SCCGWA, GDCOMPRL.VAL.BAL);
        GDCOMPRL.VAL.AVL_AMT = WS_AVA_RELAT_AMT;
        CEP.TRC(SCCGWA, GDCOMPRL.VAL.AVL_AMT);
        GDCOMPRL.VAL.CTA_NO = GDCSMPRL.VAL.CTA_NO;
        GDCOMPRL.VAL.REF_NO = GDCSMPRL.VAL.REF_NO;
        GDCOMPRL.VAL.BUSI_TYP = GDCSMPRL.VAL.BUSI_TYP;
        GDCOMPRL.VAL.RL_AMT = GDCSMPRL.VAL.RL_AMT;
        GDCOMPRL.VAL.EXP_DT = GDCSMPRL.VAL.EXP_DT;
        GDCOMPRL.VAL.RMK = GDCSMPRL.VAL.RMK;
        GDCOMPRL.VAL.RSEQ = GDCUMPLD.RSEQ;
        GDCOMPRL.VAL.PN_AMT = GDCSMPRL.VAL.PN_AMT;
        SCCFMT.DATA_PTR = GDCOMPRL;
        SCCFMT.DATA_LEN = 860;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_CHECK_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        GDRPLDR.DEAL_CD = GDCSMPRL.VAL.CTA_NO;
        GDRPLDR.BSREF = GDCSMPRL.VAL.REF_NO;
        GDRPLDR.KEY.AC = GDCSMPRL.KEY.AC;
        GDRPLDR.KEY.AC_SEQ = GDCSMPRL.KEY.AC_SEQ;
        T000_READ_CTA_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, "CHECK PLDR RECORD");
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.RL_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.PN_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.PALT_AMT);
        if (WS_PLDR_FLG == 'Y' 
            && GDRPLDR.RELAT_STS == 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SAME_RECORD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_CTA_REC_PROC_CTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        if (!GDRPLDR.CCY.equalsIgnoreCase(GDCSMPRL.VAL.CCY) 
            && WS_PLDR_CTA_FLG == 'Y') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_PLDR_CTA_FLG == 'Y') {
            WS_RSEQ = GDRPLDR.KEY.RSEQ;
        }
    }
    public void B040_CHECK_KDPLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        GDRPLDR.DEAL_CD = GDCSMPRL.VAL.CTA_NO;
        GDRPLDR.BSREF = GDCSMPRL.VAL.REF_NO;
        GDRPLDR.KEY.AC = GDCSMPRL.KEY.AC;
        T000_READ_KD_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        T000_GROUP_AMT_GDTPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_KDGD_RELAT_AMT);
        CEP.TRC(SCCGWA, "CHECK PLDR RECORD");
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.RL_AMT);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.PN_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.PALT_AMT);
        if (WS_PLDR_FLG == 'Y' 
            && GDRPLDR.RELAT_STS == 'N') {
            if (WS_KDGD_RELAT_AMT != GDCSMPRL.VAL.RL_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NEW_NE_OLD_AMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (GDRPLDR.PALT_AMT != GDCSMPRL.VAL.PN_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PNAMT_NEW_NE_OLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "SAME PLDR RECORD");
            if (WS_KDGD_RELAT_AMT == GDCSMPRL.VAL.RL_AMT 
                && GDRPLDR.PALT_AMT == GDCSMPRL.VAL.PN_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SAME_RECORD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_UPD_PLDR_REC_PROC() throws IOException,SQLException,Exception {
        GDRPLDR.RELAT_STS = 'N';
        GDRPLDR.RELAT_AMT = WS_RL_AMT;
        GDRPLDR.PALT_AMT = GDCSMPRL.VAL.PN_AMT;
        GDRPLDR.RELAT_TIME = SCCGWA.COMM_AREA.TR_TIME;
        GDRPLDR.RELAT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        GDRPLDR.RELAT_CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRPLDR.RELS_DATE = 0;
        GDRPLDR.RELS_TIME = 0;
        GDRPLDR.RELS_BR = 0;
        GDRPLDR.RELS_CHNL_CD = " ";
        GDRPLDR.RELS_USR = " ";
        GDRPLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_UPD_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        WS_NEED_PLDR_AMT = 0;
        WS_RELAT_AMT = 0;
        WS_NEED_PLDR_AMT = WS_NEED_RL_AMT;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = GDCSMPRL.KEY.AC;
        T000_STARTBR_TDTSMST_1();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N' 
            && WS_NEED_PLDR_AMT != 0) {
            CEP.TRC(SCCGWA, "PLDR GDKD RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            if (TDRSMST.BAL > WS_NEED_PLDR_AMT) {
                WS_RELAT_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELAT_AMT = TDRSMST.BAL;
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
            WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_RSEQ = GDRPLDR.KEY.RSEQ;
            B020_CREATE_PLDR_PROC();
            if (pgmRtn) return;
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELAT_AMT;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELAT_AMT);
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        B020_CREATE_PLDR_PROC();
        if (pgmRtn) return;
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCSMPRL.VAL.RL_AMT;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void R000_UPD_MARG_BAL_PROC() throws IOException,SQLException,Exception {
        if (GDCSMPRL.KEY.AC_SEQ == 0) {
            R000_UPD_DDTCCY_PROC();
            if (pgmRtn) return;
        } else {
            R000_UPD_TDTSMST_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_UPD_DDTCCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.CCY);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.CCY_TYP);
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDRCCY);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSMPRL.KEY.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDRCCY.CUS_AC = GDCSMPRL.KEY.AC;
        DDRCCY.CCY = GDCSMPRL.VAL.CCY;
        DDRCCY.CCY_TYPE = GDCSMPRL.VAL.CCY_TYP;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCSMPRL.VAL.RL_AMT;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void R000_UPD_TDTSMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSMPRL.KEY.AC;
        CICQACAC.DATA.AGR_SEQ = GDCSMPRL.KEY.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUPD_TDTSMST();
        if (pgmRtn) return;
        TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL + GDCSMPRL.VAL.RL_AMT;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_TDTSMST();
        if (pgmRtn) return;
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDCRHIS.FUNC = 'A';
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDRPLDR.KEY.RSEQ;
        GDRHIS.AC = GDCSMPRL.KEY.AC;
        GDRHIS.AC_SEQ = GDCSMPRL.KEY.AC_SEQ;
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.KEY.SEQ);
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        GDRHIS.FUNC = '3';
        GDRHIS.DEAL_CD = GDCSMPRL.VAL.CTA_NO;
        GDRHIS.BSREF = GDCSMPRL.VAL.REF_NO;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCSMPRL.VAL.RL_AMT;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
    }
    public void R000_WRITE_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = GDCSMPRL.KEY.AC;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = GDRPLDR.KEY.RSEQ;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = GDCSMPRL.VAL.RMK;
        BPCPNHIS.INFO.TX_TYP_CD = "P618";
        CEP.TRC(SCCGWA, "ADD");
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.NEW_DAT_PT = GDCUMPLD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DRAC_STATUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC);
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.CCY);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = GDCSMPRL.KEY.AC;
            DDRCCY.CCY = GDCSMPRL.VAL.CCY;
            DDRCCY.CCY_TYPE = GDCSMPRL.VAL.CCY_TYP;
            T000_READUPD_DDTCCY();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_INNER_HOLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_SIFA_HOLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDCSMPRL.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDCSMPRL.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUPD_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_FRZ);
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_TMP_HLD);
            }
            if (TDRSMST.ACO_STS == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2);
            }
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
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
    public void T000_GROUP_AMT_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.set = "WS-KDGD-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CTA_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.upd = true;
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void T000_READ_CTA_REC_PROC_CTA() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.upd = true;
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_CTA_FLG = 'N';
        } else {
            WS_PLDR_CTA_FLG = 'Y';
        }
    }
    public void T000_READ_KD_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.REWRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
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
    public void T000_READUPD_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        TDTSMST_RD.fst = true;
        TDTSMST_RD.order = "VAL_DATE,ACO_AC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
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
    public void T000_STARTBR_TDTSMST_1() throws IOException,SQLException,Exception {
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
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_BR.rp.upd = true;
        GDTPLDR_BR.rp.order = "AC_SEQ";
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
            WS_PLDR_FLG_1 = 'Y';
        } else {
            WS_PLDR_FLG_1 = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
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
