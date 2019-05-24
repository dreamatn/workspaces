package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.CI.*;
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
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSRLSR {
    DBParm GDTPLDR_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm DDTCCY_RD;
    DBParm TDTSMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD540";
    String K_CHNL_TLR = "100100";
    String WS_ERR_MSG = " ";
    double WS_RELSE_AMT = 0;
    String WS_RL_SEQ = " ";
    String WS_AC = " ";
    int WS_AC_SEQ = 0;
    String WS_CTA_NO = " ";
    String WS_REF_NO = " ";
    char WS_RHIS_FLG = ' ';
    char WS_PLDR_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCORLSR GDCORLSR = new GDCORLSR();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICACCU CICACCU = new CICACCU();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    double WS_ALL_RELAT_AMT = 0;
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    GDCSRLSR GDCSRLSR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSRLSR GDCSRLSR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSRLSR = GDCSRLSR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSRLSR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (GDCSRLSR.VAL.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSRLSR.VAL.AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CCY);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = GDCSRLSR.VAL.AC;
                DDRCCY.CCY = CICQACRI.O_DATA.O_CCY;
                if (CICQACRI.O_DATA.O_CCY.equalsIgnoreCase("156")) {
                    DDRCCY.CCY_TYPE = '1';
                } else {
                    DDRCCY.CCY_TYPE = '2';
                }
                T000_READUPD_DDTCCY();
                if (pgmRtn) return;
                if (DDRCCY.STS == 'C') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLO_INT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDRSMST);
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.DATA.AGR_NO = GDCSRLSR.VAL.AC;
                CICQACAC.DATA.AGR_SEQ = GDCSRLSR.VAL.AC_SEQ;
                CICQACAC.FUNC = 'R';
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                B010_20_CHECK_RLDR_REC();
                if (pgmRtn) return;
                T000_READ_AC_GDTPLDR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
                CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
                if (TDRSMST.ACO_STS == '1') {
                    if (GDRPLDR.RELAT_AMT != 0) {
                        WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                B015_CHECK_GDKD_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDVMPRD.VAL.TD_FLG == '0') {
            B025_GDKD_PLDR_PROC();
            if (pgmRtn) return;
        } else {
            B010_CHECK_DATA_VALIDITY();
            if (pgmRtn) return;
            B020_DELETE_PLDR_PROC();
            if (pgmRtn) return;
        }
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        B010_20_CHECK_RLDR_REC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.RL_SEQ);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.AC);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.AC_SEQ);
        if (GDCSRLSR.VAL.RL_SEQ.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_RL_SEQ = GDCSRLSR.VAL.RL_SEQ;
        WS_AC = GDCSRLSR.VAL.AC;
        WS_AC_SEQ = GDCSRLSR.VAL.AC_SEQ;
        WS_CTA_NO = GDCSRLSR.VAL.CTA_NO;
        WS_REF_NO = GDCSRLSR.VAL.REF_NO;
    }
    public void B010_20_CHECK_RLDR_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.RL_SEQ);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.AC);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.AC_SEQ);
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        if (GDCSRLSR.VAL.RL_SEQ.trim().length() > 0) {
            GDCRPLDR.FUNC = 'I';
            GDRPLDR.KEY.RSEQ = GDCSRLSR.VAL.RL_SEQ;
            GDRPLDR.KEY.AC = GDCSRLSR.VAL.AC;
            GDRPLDR.KEY.AC_SEQ = GDCSRLSR.VAL.AC_SEQ;
        } else {
            GDCRPLDR.FUNC = 'C';
            GDRPLDR.DEAL_CD = GDCSRLSR.VAL.CTA_NO;
            GDRPLDR.BSREF = GDCSRLSR.VAL.REF_NO;
            CEP.TRC(SCCGWA, GDCSRLSR.VAL.CTA_NO);
            CEP.TRC(SCCGWA, GDCSRLSR.VAL.REF_NO);
            GDRPLDR.KEY.AC = GDCSRLSR.VAL.AC;
            GDRPLDR.KEY.AC_SEQ = GDCSRLSR.VAL.AC_SEQ;
        }
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        if (GDCSRLSR.VAL.RL_SEQ.trim().length() == 0) {
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            T000_READ_GDTPLDR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.RL_SEQ);
        CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
        if (GDRPLDR.SYS_NO.equalsIgnoreCase(K_CHNL_TLR) 
            && !GDRPLDR.SYS_NO.equalsIgnoreCase(GDCSRLSR.VAL.SYS_NO)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANL_INVAILD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSRLSR.VAL.RL_SEQ.trim().length() == 0) {
            GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        }
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.AC);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSRLSR.VAL.AC;
        DDRCCY.CCY = CICQACRI.O_DATA.O_CCY;
        if (CICQACRI.O_DATA.O_CCY.equalsIgnoreCase("156")) {
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
        DDCIQPRD.INPUT_DATA.CCY = GDCSRLSR.VAL.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSRLSR.VAL.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B020_DELETE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'D';
        GDCUMPLD.RSEQ = WS_RL_SEQ;
        GDCUMPLD.AC = WS_AC;
        GDCUMPLD.AC_SEQ = WS_AC_SEQ;
        GDCUMPLD.CTA_NO = WS_CTA_NO;
        GDCUMPLD.REF_NO = WS_REF_NO;
        CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.CTA_NO);
        CEP.TRC(SCCGWA, GDCUMPLD.REF_NO);
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
        GDRPLDR.KEY.AC = GDCSRLSR.VAL.AC;
        GDRPLDR.DEAL_CD = GDCSRLSR.VAL.CTA_NO;
        GDRPLDR.BSREF = GDCSRLSR.VAL.REF_NO;
        T000_STARTBR_GDTPLDR();
        if (pgmRtn) return;
        T000_READNEXT_GDTPLDR();
        if (pgmRtn) return;
        while (WS_PLDR_FLG != 'N') {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
            if (GDRPLDR.SYS_NO.equalsIgnoreCase(K_CHNL_TLR) 
                && !GDRPLDR.SYS_NO.equalsIgnoreCase(GDCSRLSR.VAL.SYS_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANL_INVAILD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_RL_SEQ = GDRPLDR.KEY.RSEQ;
            WS_AC = GDRPLDR.KEY.AC;
            WS_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
            B020_DELETE_PLDR_PROC();
            if (pgmRtn) return;
            WS_RELSE_AMT = WS_RELSE_AMT + GDRPLDR.RELAT_AMT;
            T000_READNEXT_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_RELSE_AMT);
        }
        T000_ENDBR_GDTPLDR();
        if (pgmRtn) return;
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - WS_RELSE_AMT;
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
        if (DDRCCY.MARGIN_BAL < 0) {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCUMPLD.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY_TYPE);
        CEP.TRC(SCCGWA, GDCUMPLD.TOT_RAMT);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_TYPE);
        CEP.TRC(SCCGWA, GDCUMPLD.CTA_NO);
        CEP.TRC(SCCGWA, GDCUMPLD.REF_NO);
        IBS.init(SCCGWA, GDCORLSR);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        if (DDVMPRD.VAL.TD_FLG == '0') {
            GDCORLSR.VAL.BAL = WS_RELSE_AMT;
        } else {
            GDCORLSR.VAL.BAL = GDCUMPLD.TOT_RAMT;
        }
        if (GDCSRLSR.VAL.AC.trim().length() > 0) {
            GDCORLSR.VAL.AC = GDCUMPLD.AC;
            if (GDCSRLSR.VAL.CI_NM.trim().length() == 0) {
                GDCORLSR.VAL.CI_NM = CICACCU.DATA.AC_CNM;
            } else {
                GDCORLSR.VAL.CI_NM = GDCSRLSR.VAL.CI_NM;
            }
            GDCORLSR.VAL.AC_SEQ = GDCUMPLD.AC_SEQ;
            GDCORLSR.VAL.CCY = GDCUMPLD.CCY;
            GDCORLSR.VAL.CCY_TYP = GDCUMPLD.CCY_TYPE;
            GDCORLSR.VAL.AC_TYP = GDCUMPLD.AC_TYPE;
        } else {
            GDCORLSR.VAL.CTA_NO = GDCUMPLD.CTA_NO;
            GDCORLSR.VAL.REF_NO = GDCUMPLD.REF_NO;
            GDCORLSR.VAL.RMK = GDCSRLSR.VAL.RMK;
        }
        SCCFMT.DATA_PTR = GDCORLSR;
        SCCFMT.DATA_LEN = 496;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCSRLSR.VAL.AC;
        GDRPLDR.KEY.AC_SEQ = GDCSRLSR.VAL.AC_SEQ;
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
            WS_PLDR_FLG = 'Y';
        } else {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "RSEQ =");
            CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AC_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.RSEQ = GDCSRLSR.VAL.RL_SEQ;
        GDRPLDR.KEY.AC = GDCSRLSR.VAL.AC;
        GDRPLDR.KEY.AC_SEQ = GDCSRLSR.VAL.AC_SEQ;
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.READ(SCCGWA, GDRPLDR, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "RSEQ =");
            CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
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
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ";
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            WS_RHIS_FLG = 'N';
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
