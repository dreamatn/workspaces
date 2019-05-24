package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZBALB {
    brParm LNTBALH_BR = new brParm();
    brParm LNTPYIF_BR = new brParm();
    brParm LNTPLPI_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_ITEM_CNT = 50;
    char LNZBALB_FILLER1 = ' ';
    short WS_I = 0;
    short WS_JJJ = 0;
    double WS_BAL = 0;
    double WS_TOT_AMT = 0;
    double WS_MTH2_AMT = 0;
    double WS_RATE = 0;
    int WS_RATE_DATE = 0;
    int WS_NEXT_RATE_DATE = 0;
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    int WS_BALH_LAST_DATE = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_BALH_FOUND_FLG = ' ';
    char WS_PYIF_FOUND_FLG = ' ';
    LNRBALH LNRBALH = new LNRBALH();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    int WS_BALB_BEG_DATE = 0;
    int WS_BALB_END_DATE = 0;
    SCCGWA SCCGWA;
    LNCBALB LNCBALB;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA, LNCBALB LNCBALB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCBALB = LNCBALB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZBALB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        LNCBALB.RC.RC_APP = "LN";
        LNCBALB.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.CTNR_NO);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.END_DATE);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.ITEM_CNT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        B300_PLPI_BAL_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCBALB.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCBALB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCBALB.COMM_DATA.BEG_DATE > LNCBALB.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCBALB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        if (LNCBALB.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCBALB.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_I = 0;
        if (LNCBALB.COMM_DATA.CTNR_NO.equalsIgnoreCase("2")) {
            WS_BAL = LNCCONTM.REC_DATA.LN_TOT_AMT;
        } else {
            WS_BAL = 0;
        }
        T00_STARTBR_LNTBALH();
        if (pgmRtn) return;
        T00_READNEXT_LNTBALH();
        if (pgmRtn) return;
        while (WS_I < ( WS_ITEM_CNT - 1 ) 
            && (LNRBALH.KEY.TXN_DT < LNCBALB.COMM_DATA.END_DATE) 
            && WS_FOUND_FLG == 'Y') {
            WS_TOT_AMT = 0;
            WS_MTH2_AMT = 0;
            if ((LNCBALB.COMM_DATA.CTNR_NO.equalsIgnoreCase("2")) 
                && (LNCBALB.COMM_DATA.BEG_DATE == LNCICTLM.REC_DATA.INT_CUT_DT) 
                && (LNRBALH.KEY.TXN_DT > LNCBALB.COMM_DATA.BEG_DATE)) {
                B210_PREPAY_INTMTH2_PROC();
                if (pgmRtn) return;
            }
            if ((LNRBALH.KEY.TXN_DT > LNCBALB.COMM_DATA.BEG_DATE) 
                && (LNRBALH.BAL != WS_BAL)) {
                WS_I += 1;
                if (WS_I == 1) {
                    WS_BEG_DATE = LNCBALB.COMM_DATA.BEG_DATE;
                }
                WS_END_DATE = LNRBALH.KEY.TXN_DT;
                LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
                LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
                LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].BAL = WS_BAL;
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, WS_BAL);
                CEP.TRC(SCCGWA, LNRBALH.BAL);
            }
            if (WS_MTH2_AMT != 0 
                && WS_MTH2_AMT == WS_TOT_AMT) {
            } else {
                WS_BEG_DATE = LNRBALH.KEY.TXN_DT;
                WS_BAL = LNRBALH.BAL;
            }
            T00_READNEXT_LNTBALH();
            if (pgmRtn) return;
        }
        WS_TOT_AMT = 0;
        WS_MTH2_AMT = 0;
        if ((LNCBALB.COMM_DATA.CTNR_NO.equalsIgnoreCase("2")) 
            && (LNCBALB.COMM_DATA.BEG_DATE == LNCICTLM.REC_DATA.INT_CUT_DT) 
            && (LNRBALH.KEY.TXN_DT > LNCBALB.COMM_DATA.BEG_DATE) 
            && WS_FOUND_FLG == 'Y') {
            B210_PREPAY_INTMTH2_PROC();
            if (pgmRtn) return;
        }
        B211_BROWSE_LNTPYIF_MTH3();
        if (pgmRtn) return;
        WS_I += 1;
        if (WS_I == 1) {
            WS_BEG_DATE = LNCBALB.COMM_DATA.BEG_DATE;
        }
        WS_END_DATE = LNCBALB.COMM_DATA.END_DATE;
        LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
        LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
        LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].BAL = WS_BAL;
        T00_ENDBR_LNTBALH();
        if (pgmRtn) return;
        LNCBALB.COMM_DATA.ITEM_CNT = WS_I;
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, WS_BAL);
        CEP.TRC(SCCGWA, LNRBALH.BAL);
    }
    public void B210_PREPAY_INTMTH2_PROC() throws IOException,SQLException,Exception {
        B211_BROWSE_LNTPYIF_MTH2();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        CEP.TRC(SCCGWA, WS_MTH2_AMT);
        if (WS_MTH2_AMT > 0) {
            B212_PRP_INTMTH2_PROC();
            if (pgmRtn) return;
        }
    }
    public void B211_BROWSE_LNTPYIF_MTH2() throws IOException,SQLException,Exception {
        T00_STARTBR_LNTPYIF();
        if (pgmRtn) return;
        T00_READNEXT_LNTPYIF();
        if (pgmRtn) return;
        while (WS_PYIF_FOUND_FLG != 'N') {
            WS_TOT_AMT += LNRPYIF.REIM_PRIN_AMT;
            if (LNRPYIF.REPY_MTH == '2') {
                WS_MTH2_AMT += LNRPYIF.REIM_PRIN_AMT;
            }
            T00_READNEXT_LNTPYIF();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPYIF();
        if (pgmRtn) return;
    }
    public void B212_PRP_INTMTH2_PROC() throws IOException,SQLException,Exception {
        WS_BAL -= WS_MTH2_AMT;
        for (WS_JJJ = 1; WS_JJJ <= WS_I; WS_JJJ += 1) {
            LNCBALB.COMM_DATA.IETM_RATES[WS_JJJ-1].BAL -= WS_MTH2_AMT;
        }
    }
    public void B211_BROWSE_LNTPYIF_MTH3() throws IOException,SQLException,Exception {
        T00_STARTBR_LNTPYIF3();
        if (pgmRtn) return;
        T00_READNEXT_LNTPYIF();
        if (pgmRtn) return;
        while (WS_PYIF_FOUND_FLG != 'N') {
            LNCBALB.COMM_DATA.PYIF_INT_AMT += LNRPYIF.REIM_INT_AMT;
            T00_READNEXT_LNTPYIF();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTPYIF();
        if (pgmRtn) return;
    }
    public void B300_PLPI_BAL_PROC() throws IOException,SQLException,Exception {
        if (LNCBALB.COMM_DATA.CTNR_NO.equalsIgnoreCase("2") 
            && (LNCBALB.COMM_DATA.ITEM_CNT < WS_ITEM_CNT) 
            && (LNCBALB.COMM_DATA.END_DATE > SCCGWA.COMM_AREA.AC_DATE)) {
            B110_GET_BALH_LAST_DATE();
            if (pgmRtn) return;
            B110_GET_PLPI_BAL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.ITEM_CNT);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.IETM_RATES[1-1]);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.IETM_RATES[2-1]);
        CEP.TRC(SCCGWA, LNCBALB.COMM_DATA.IETM_RATES[3-1]);
    }
    public void B110_GET_BALH_LAST_DATE() throws IOException,SQLException,Exception {
        WS_BALH_FOUND_FLG = 'N';
        T00_STARTBR_LNTBALH_1();
        if (pgmRtn) return;
        T00_READNEXT_LNTBALH_1();
        if (pgmRtn) return;
        WS_BALH_LAST_DATE = LNRBALH.KEY.TXN_DT;
        T00_ENDBR_LNTBALH_1();
        if (pgmRtn) return;
    }
    public void B110_GET_PLPI_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BALH_LAST_DATE);
        WS_I = (short) LNCBALB.COMM_DATA.ITEM_CNT;
        WS_BAL = LNCBALB.COMM_DATA.IETM_RATES[LNCBALB.COMM_DATA.ITEM_CNT-1].BAL;
        T00_STARTBR_LNTPLPI();
        if (pgmRtn) return;
        T00_READNEXT_LNTPLPI();
        if (pgmRtn) return;
        while ((WS_I < WS_ITEM_CNT) 
            && (LNRPLPI.DUE_DT < LNCBALB.COMM_DATA.END_DATE) 
            && (WS_BAL != 0) 
            && WS_FOUND_FLG == 'Y') {
            if (LNRPLPI.DUE_DT > LNCBALB.COMM_DATA.IETM_RATES[LNCBALB.COMM_DATA.ITEM_CNT-1].B_DATE) {
                if (WS_I == LNCBALB.COMM_DATA.ITEM_CNT) {
                    WS_BEG_DATE = LNCBALB.COMM_DATA.IETM_RATES[LNCBALB.COMM_DATA.ITEM_CNT-1].B_DATE;
                }
                WS_END_DATE = LNRPLPI.DUE_DT;
                LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
                LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
                LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].BAL = WS_BAL;
                WS_I += 1;
            }
            WS_BEG_DATE = LNRPLPI.DUE_DT;
            if (LNRPLPI.DUE_DT > WS_BALH_LAST_DATE) {
                if (WS_BAL > LNRPLPI.PRIN_AMT) {
                    WS_BAL -= LNRPLPI.PRIN_AMT;
                } else {
                    WS_BAL = 0;
                }
            }
            T00_READNEXT_LNTPLPI();
            if (pgmRtn) return;
        }
        if (WS_I == LNCBALB.COMM_DATA.ITEM_CNT) {
            WS_BEG_DATE = LNCBALB.COMM_DATA.IETM_RATES[LNCBALB.COMM_DATA.ITEM_CNT-1].B_DATE;
        }
        WS_END_DATE = LNCBALB.COMM_DATA.END_DATE;
        LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
        LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
        LNCBALB.COMM_DATA.IETM_RATES[WS_I-1].BAL = WS_BAL;
        T00_ENDBR_LNTPLPI();
        if (pgmRtn) return;
        LNCBALB.COMM_DATA.ITEM_CNT = WS_I;
    }
    public void T00_STARTBR_LNTBALH_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALH);
        LNRBALH.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        if (LNCBALB.COMM_DATA.SUF_NO.trim().length() == 0) LNRBALH.KEY.SUB_CTA_NO = 0;
        else LNRBALH.KEY.SUB_CTA_NO = Integer.parseInt(LNCBALB.COMM_DATA.SUF_NO);
        LNRBALH.KEY.CTNR_NO = 2;
        LNRBALH.KEY.TXN_DT = 0;
        LNTBALH_BR.rp = new DBParm();
        LNTBALH_BR.rp.TableName = "LNTBALH";
        LNTBALH_BR.rp.eqWhere = "CONTRACT_NO";
        LNTBALH_BR.rp.where = "SUB_CTA_NO = :LNRBALH.KEY.SUB_CTA_NO "
            + "AND CTNR_NO = :LNRBALH.KEY.CTNR_NO "
            + "AND TXN_DT >= :LNRBALH.KEY.TXN_DT";
        LNTBALH_BR.rp.order = "TXN_DT DESC";
        IBS.STARTBR(SCCGWA, LNRBALH, this, LNTBALH_BR);
    }
    public void T00_READNEXT_LNTBALH_1() throws IOException,SQLException,Exception {
        WS_BALH_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRBALH, this, LNTBALH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BALH_FOUND_FLG = 'Y';
        } else {
            WS_BALH_FOUND_FLG = 'N';
            LNRBALH.KEY.TXN_DT = LNCCONTM.REC_DATA.START_DATE;
        }
    }
    public void T00_ENDBR_LNTBALH_1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTBALH_BR);
    }
    public void T00_STARTBR_LNTPYIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        LNRPYIF.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        if (LNCBALB.COMM_DATA.SUF_NO.trim().length() == 0) LNRPYIF.KEY.SUB_CTA_NO = 0;
        else LNRPYIF.KEY.SUB_CTA_NO = Integer.parseInt(LNCBALB.COMM_DATA.SUF_NO);
        LNRPYIF.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNRPYIF.KEY.REPY_SEQ = 0;
        LNRPYIF.CUR_REPY_DT = LNRBALH.KEY.TXN_DT;
        LNTPYIF_BR.rp = new DBParm();
        LNTPYIF_BR.rp.TableName = "LNTPYIF";
        LNTPYIF_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,TERM";
        LNTPYIF_BR.rp.where = "REPY_SEQ > :LNRPYIF.KEY.REPY_SEQ "
            + "AND CUR_REPY_DT = :LNRPYIF.CUR_REPY_DT";
        LNTPYIF_BR.rp.order = "REPY_SEQ";
        IBS.STARTBR(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
    }
    public void T00_STARTBR_LNTPYIF3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        LNRPYIF.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        if (LNCBALB.COMM_DATA.SUF_NO.trim().length() == 0) LNRPYIF.KEY.SUB_CTA_NO = 0;
        else LNRPYIF.KEY.SUB_CTA_NO = Integer.parseInt(LNCBALB.COMM_DATA.SUF_NO);
        LNRPYIF.KEY.TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNRPYIF.KEY.REPY_SEQ = 0;
        LNRPYIF.REPY_MTH = '3';
        WS_BALB_BEG_DATE = LNCBALB.COMM_DATA.BEG_DATE;
        WS_BALB_END_DATE = LNCBALB.COMM_DATA.END_DATE;
        LNTPYIF_BR.rp = new DBParm();
        LNTPYIF_BR.rp.TableName = "LNTPYIF";
        LNTPYIF_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,TERM";
        LNTPYIF_BR.rp.where = "REPY_SEQ > :LNRPYIF.KEY.REPY_SEQ "
            + "AND REPY_MTH = :LNRPYIF.REPY_MTH "
            + "AND CUR_REPY_DT >= :WS_BALB_BEG_DATE "
            + "AND CUR_REPY_DT <= :WS_BALB_END_DATE";
        LNTPYIF_BR.rp.order = "REPY_SEQ";
        IBS.STARTBR(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
    }
    public void T00_READNEXT_LNTPYIF() throws IOException,SQLException,Exception {
        WS_PYIF_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PYIF_FOUND_FLG = 'Y';
        } else {
            WS_PYIF_FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTPYIF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPYIF_BR);
    }
    public void T00_STARTBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        if (LNCBALB.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCBALB.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = 'P';
        LNRPLPI.KEY.TERM = 0;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM > :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T00_READNEXT_LNTPLPI() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_ENDBR_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
    }
    public void T00_STARTBR_LNTBALH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALH);
        LNRBALH.KEY.CONTRACT_NO = LNCBALB.COMM_DATA.LN_AC;
        if (LNCBALB.COMM_DATA.SUF_NO.trim().length() == 0) LNRBALH.KEY.SUB_CTA_NO = 0;
        else LNRBALH.KEY.SUB_CTA_NO = Integer.parseInt(LNCBALB.COMM_DATA.SUF_NO);
        if (LNCBALB.COMM_DATA.CTNR_NO.trim().length() == 0) LNRBALH.KEY.CTNR_NO = 0;
        else LNRBALH.KEY.CTNR_NO = Short.parseShort(LNCBALB.COMM_DATA.CTNR_NO);
        LNRBALH.KEY.TXN_DT = 0;
        LNTBALH_BR.rp = new DBParm();
        LNTBALH_BR.rp.TableName = "LNTBALH";
        LNTBALH_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,CTNR_NO";
        LNTBALH_BR.rp.where = "TXN_DT >= :LNRBALH.KEY.TXN_DT";
        LNTBALH_BR.rp.order = "TXN_DT";
        IBS.STARTBR(SCCGWA, LNRBALH, this, LNTBALH_BR);
    }
    public void T00_READNEXT_LNTBALH() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRBALH, this, LNTBALH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_ENDBR_LNTBALH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTBALH_BR);
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBALB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBALB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBALB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCBALB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCBALB=");
            CEP.TRC(SCCGWA, LNCBALB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
