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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSPCMA {
    BigDecimal bigD;
    brParm GDTPLDR_BR = new brParm();
    DBParm DDTCCY_RD;
    DBParm GDTPLDR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD570";
    String WS_ERR_MSG = " ";
    GDZSPCMA_WS_PLDR_DETAIL WS_PLDR_DETAIL = new GDZSPCMA_WS_PLDR_DETAIL();
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
    GDZSPCMA_WS_TXCTA_NO WS_TXCTA_NO = new GDZSPCMA_WS_TXCTA_NO();
    double WS_TX_AMT = 0;
    double WS_TX_SAMT = 0;
    double WS_NEW_RELA_AMT = 0;
    double WS_OLD_RELA_AMT = 0;
    double WS_PALT_AMT = 0;
    double WS_LAST_AMT = 0;
    String WS_AC = " ";
    String WS1_RSEQ = " ";
    String WS1_AC = " ";
    char WS1_AC_TYPE = ' ';
    int WS1_AC_SEQ = 0;
    String WS1_CCY = " ";
    char WS1_CCY_TYPE = ' ';
    double WS1_TX_AMT = 0;
    String WS1_SYS_NO = " ";
    String WS1_CTA_NO = " ";
    String WS1_REF_NO = " ";
    String WS_LAST_BSREF = " ";
    char WS_PLDR_FLG = ' ';
    char WS_FULL_PCTS_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRPLDR GDRPLDR1 = new GDRPLDR();
    GDRPLDR GDRPLDR2 = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    CICACCU CICACCU = new CICACCU();
    GDCOPCMA GDCOPCMA = new GDCOPCMA();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    CICQACAC CICQACAC = new CICQACAC();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    GDCSADDM GDCSADDM = new GDCSADDM();
    DDRCCY DDRCCY = new DDRCCY();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    int WS_CNT = 0;
    int WS_VAL_DATE = 0;
    int WS_START_NUM = 0;
    double WS_ALL_RELAT_AMT = 0;
    SCCGWA SCCGWA;
    GDCSPCMA GDCSPCMA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSPCMA GDCSPCMA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSPCMA = GDCSPCMA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSPCMA return!");
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
        B015_CHECK_RLDR_REC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.AC);
        if (GDCSPCMA.VAL.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_RLDR_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.AC);
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.SYS_NO);
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.CNTR_NO);
        IBS.init(SCCGWA, GDCRPLDR);
        IBS.init(SCCGWA, GDRPLDR);
        WS_TX_SAMT = 0;
        T000_STARTBR_GDTPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR.MFG_DATE);
        T000_READNEXT_GDTPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        if (GDRPLDR.RELAT_STS == 'R') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INACTIVE_RSEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_PLDR_FLG == 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        if (!GDRPLDR.CCY.equalsIgnoreCase(GDCSPCMA.VAL.CCY)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
        if (!GDRPLDR.DEAL_CD.equalsIgnoreCase(GDCSPCMA.VAL.CNTR_NO)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, GDRPLDR, GDRPLDR1);
        while (WS_PLDR_FLG != 'N') {
            WS_LAST_BSREF = GDRPLDR.BSREF;
            CEP.TRC(SCCGWA, GDRPLDR.BSREF);
            CEP.TRC(SCCGWA, GDRPLDR);
            IBS.init(SCCGWA, GDRPLDR);
            T000_READNEXT_GDTPLDR();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, GDRPLDR, GDRPLDR2);
            CEP.TRC(SCCGWA, GDRPLDR);
            CEP.TRC(SCCGWA, GDRPLDR1.BSREF);
            CEP.TRC(SCCGWA, GDRPLDR2.BSREF);
            CEP.TRC(SCCGWA, WS_LAST_BSREF);
            IBS.CLONE(SCCGWA, GDRPLDR1, GDRPLDR);
            if (!GDRPLDR2.BSREF.equalsIgnoreCase(GDRPLDR1.BSREF)) {
                B020_10_CALC_NEW_RELA_AMT();
                if (pgmRtn) return;
                B020_20_CALC_AMT_PROC();
                if (pgmRtn) return;
            } else {
                if (WS_PLDR_FLG == 'N') {
                    B020_20_CALC_AMT_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.CLONE(SCCGWA, GDRPLDR2, GDRPLDR1);
        }
        T000_ENDBR_GDTPLDR();
        if (pgmRtn) return;
        if (WS_TX_SAMT != GDCSPCMA.VAL.TX_SAMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NEW_AMT_NOT_EQ_INAMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_10_CALC_NEW_RELA_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.PALT_AMT);
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.NEW_PCT);
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.TX_SAMT);
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.OLD_PCT);
        WS_OLD_RELA_AMT = 0;
        WS_NEW_RELA_AMT = 0;
        WS_PALT_AMT = 0;
        WS_OLD_RELA_AMT = GDRPLDR.RELAT_AMT;
        WS_PALT_AMT = GDRPLDR.PALT_AMT;
        if (GDCSPCMA.VAL.OLD_PCT == 100.00) {
            WS_FULL_PCTS_FLG = 'Y';
        }
        if (WS_FULL_PCTS_FLG == 'Y') {
            T000_GROUP_GDTPLDR();
            if (pgmRtn) return;
            WS_OLD_RELA_AMT = WS_ALL_RELAT_AMT;
            WS_TX_AMT = WS_PALT_AMT - WS_OLD_RELA_AMT;
        } else {
            WS_TX_AMT = ( WS_PALT_AMT * GDCSPCMA.VAL.NEW_PCT / 100 );
            bigD = new BigDecimal(WS_TX_AMT);
            WS_TX_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        CEP.TRC(SCCGWA, WS_PALT_AMT);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        WS1_RSEQ = GDRPLDR.KEY.RSEQ;
        WS1_AC = GDRPLDR.KEY.AC;
        WS1_AC_TYPE = GDRPLDR.AC_TYP;
        WS1_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        WS1_CCY = GDRPLDR.CCY;
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            WS1_CCY_TYPE = '1';
        } else {
            WS1_CCY_TYPE = '2';
        }
        WS1_TX_AMT = WS_TX_AMT;
        WS1_SYS_NO = GDRPLDR.SYS_NO;
        WS1_CTA_NO = GDRPLDR.DEAL_CD;
        WS1_REF_NO = GDRPLDR.BSREF;
    }
    public void B020_20_CALC_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TX_SAMT);
        if (WS_PLDR_FLG == 'N') {
            WS_LAST_AMT = GDCSPCMA.VAL.TX_SAMT - WS_TX_SAMT;
            WS_TX_AMT = WS_LAST_AMT;
        }
        WS_TX_SAMT = WS_TX_SAMT + WS_TX_AMT;
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, WS_LAST_AMT);
        CEP.TRC(SCCGWA, WS_TX_SAMT);
        IBS.init(SCCGWA, GDCSADDM);
        GDCSADDM.VAL.RSEQ = WS1_RSEQ;
        GDCSADDM.VAL.AC = GDCSPCMA.VAL.AC;
        GDCSADDM.VAL.AC_TYP = GDCSPCMA.VAL.AC_TYP;
        GDCSADDM.VAL.AC_SEQ = GDCSPCMA.VAL.AC_SEQ;
        GDCSADDM.VAL.CCY = GDCSPCMA.VAL.CCY;
        GDCSADDM.VAL.CCY_TYP = GDCSPCMA.VAL.CCY_TYP;
        GDCSADDM.VAL.CTA_NO = WS1_CTA_NO;
        GDCSADDM.VAL.REF_NO = WS1_REF_NO;
        GDCSADDM.VAL.ADD_AMT = WS_TX_AMT;
        GDCSADDM.VAL.RMK = GDCSPCMA.VAL.RMK;
        GDCSADDM.VAL.SYS_NO = WS1_SYS_NO;
        S000_CALL_GDZSADDM();
        if (pgmRtn) return;
    }
    public void B020_30_CALC_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "FIRST/LAST RECODE");
        CEP.TRC(SCCGWA, WS_TX_SAMT);
        if (WS_PLDR_FLG == 'N') {
            WS_LAST_AMT = GDCSPCMA.VAL.TX_SAMT - WS_TX_SAMT;
            WS_TX_AMT = WS_LAST_AMT;
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDRPLDR.KEY.AC;
        DDRCCY.CCY = GDRPLDR.CCY;
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            DDRCCY.CCY_TYPE = '1';
        } else {
            DDRCCY.CCY_TYPE = '2';
        }
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDRPLDR.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSPCMA.VAL.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        IBS.init(SCCGWA, GDCOPCMA);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        GDCOPCMA.AC = GDCSPCMA.VAL.AC;
        if (GDCSPCMA.VAL.AC_NM.trim().length() == 0) {
            GDCOPCMA.AC_NM = CICACCU.DATA.AC_CNM;
        } else {
            GDCOPCMA.AC_NM = GDCSPCMA.VAL.AC_NM;
        }
        GDCOPCMA.AC_SEQ = GDCSPCMA.VAL.AC_SEQ;
        GDCOPCMA.CCY = GDCSPCMA.VAL.CCY;
        GDCOPCMA.CCY_TYP = GDCUMPLD.CCY_TYPE;
        GDCOPCMA.CNTR_NO = GDCSPCMA.VAL.CNTR_NO;
        GDCOPCMA.OLD_PCT = GDCSPCMA.VAL.OLD_PCT;
        GDCOPCMA.NEW_PCT = GDCSPCMA.VAL.NEW_PCT;
        GDCOPCMA.TX_SAMT = GDCSPCMA.VAL.TX_SAMT;
        GDCOPCMA.RMK = GDCSPCMA.VAL.RMK;
        SCCFMT.DATA_PTR = GDCOPCMA;
        SCCFMT.DATA_LEN = 471;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSPCMA.VAL.CNTR_NO);
        IBS.init(SCCGWA, GDRPLDR);
        CEP.TRC(SCCGWA, "11111111");
        GDRPLDR.DEAL_CD = GDCSPCMA.VAL.CNTR_NO;
        GDRPLDR.RELAT_STS = 'N';
        GDRPLDR.MFG_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS "
            + "AND MFG_DATE > :GDRPLDR.MFG_DATE";
        GDTPLDR_BR.rp.order = "AC_TYP,BSREF,CRT_DATE";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
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
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.RSEQ = GDRPLDR.KEY.RSEQ;
        GDRPLDR.RELAT_STS = 'N';
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
    }
    public void S000_CALL_GDZSADDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSADDM", GDCSADDM, true);
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
