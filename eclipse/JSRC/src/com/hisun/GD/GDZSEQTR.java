package com.hisun.GD;

import com.hisun.TD.*;
import com.hisun.DD.*;
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

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSEQTR {
    String JIBS_tmp_str[] = new String[10];
    DBParm GDTTRAN_RD;
    brParm GDTTRAN_BR = new brParm();
    DBParm GDTPLDR_RD;
    DBParm GDTSTAC_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "GD142";
    int SDT = 20140414;
    int EDT = 20991231;
    GDZSEQTR_WS_VARIABLES WS_VARIABLES = new GDZSEQTR_WS_VARIABLES();
    GDZSEQTR_WS_LIST WS_LIST = new GDZSEQTR_WS_LIST();
    GDZSEQTR_WS_COND_FLG WS_COND_FLG = new GDZSEQTR_WS_COND_FLG();
    GDCMSG_ERROR_MSG ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCRMST DDCRMST = new DDCRMST();
    CICQACAC CICQACAC = new CICQACAC();
    GDCOEQTR GDCOEQTR = new GDCOEQTR();
    GDRTRAN GDRTRAN = new GDRTRAN();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRSTAC GDRSTAC = new GDRSTAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI_WS_DB_VARS WS_DB_VARS = new CICQACRI_WS_DB_VARS();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDCSEQTR GDCSEQTR;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, GDCSEQTR GDCSEQTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSEQTR = GDCSEQTR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSEQTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_ENQUIRY_CONDITION();
        if (pgmRtn) return;
        B020_TRANS_DATA_PRO();
        if (pgmRtn) return;
        B030_GET_PAGE_INF();
        if (pgmRtn) return;
        B060_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_ENQUIRY_CONDITION() throws IOException,SQLException,Exception {
        if (GDCSEQTR.EDT == 0) {
            GDCSEQTR.EDT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, WS_DB_VARS.STR_DT);
        CEP.TRC(SCCGWA, WS_DB_VARS.END_DT);
    }
    public void B020_TRANS_DATA_PRO() throws IOException,SQLException,Exception {
        if (GDCSEQTR.PAGE_ROW == 0) {
            WS_VARIABLES.P_ROW = 25;
        } else {
            WS_VARIABLES.P_ROW = GDCSEQTR.PAGE_ROW;
        }
        if (GDCSEQTR.PAGE_NUM == 0) {
            GDCSEQTR.PAGE_NUM = 1;
        }
        WS_DB_VARS.L_CNT = 0;
        WS_DB_VARS.READ_REC = ( ( GDCSEQTR.PAGE_NUM - 1 ) * WS_VARIABLES.P_ROW ) + 1;
        CEP.TRC(SCCGWA, WS_DB_VARS.READ_REC);
        CEP.TRC(SCCGWA, WS_VARIABLES.P_ROW);
        CEP.TRC(SCCGWA, GDCSEQTR.PAGE_NUM);
    }
    public void B030_GET_PAGE_INF() throws IOException,SQLException,Exception {
        if (GDCSEQTR.REF_NO.trim().length() > 0 
            && GDCSEQTR.CTA_NO.trim().length() == 0) {
            T000_STARTBR_BY_REFNO();
            if (pgmRtn) return;
            T000_READNEXT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
            while (WS_COND_FLG.RL_FLAG != 'N' 
                && WS_VARIABLES.CNT < WS_VARIABLES.P_ROW) {
                WS_VARIABLES.CNT += 1;
                B050_OUTPUT_LIST();
                if (pgmRtn) return;
                WS_DB_VARS.READ_REC += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_PROC();
            if (pgmRtn) return;
            T000_GROUP_BY_REFNO();
            if (pgmRtn) return;
        } else {
            if (GDCSEQTR.CTA_NO.trim().length() > 0 
                && GDCSEQTR.REF_NO.trim().length() == 0) {
                T000_STARTBR_BY_CTADT();
                if (pgmRtn) return;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
                while (WS_COND_FLG.RL_FLAG != 'N' 
                    && WS_VARIABLES.CNT < WS_VARIABLES.P_ROW) {
                    WS_VARIABLES.CNT += 1;
                    B050_OUTPUT_LIST();
                    if (pgmRtn) return;
                    WS_DB_VARS.READ_REC += 1;
                    T000_READNEXT_PROC();
                    if (pgmRtn) return;
                }
                T000_ENDBR_PROC();
                if (pgmRtn) return;
                T000_GROUP_BY_CTADT();
                if (pgmRtn) return;
            } else {
                if (GDCSEQTR.CTA_NO.trim().length() > 0 
                    && GDCSEQTR.REF_NO.trim().length() > 0) {
                    T000_READ_GDTTRAN();
                    if (pgmRtn) return;
                    WS_VARIABLES.CNT += 1;
                    B050_OUTPUT_LIST();
                    if (pgmRtn) return;
                    T000_GROUP_BY_REFNO();
                    if (pgmRtn) return;
                }
            }
        }
        if (GDCSEQTR.CTA_NO.trim().length() == 0 
            && GDCSEQTR.REF_NO.trim().length() == 0) {
            T000_STARTBR_BY_DATE();
            if (pgmRtn) return;
            T000_READNEXT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
            while (WS_COND_FLG.RL_FLAG != 'N' 
                && WS_VARIABLES.CNT < WS_VARIABLES.P_ROW) {
                WS_VARIABLES.CNT += 1;
                B050_OUTPUT_LIST();
                if (pgmRtn) return;
                WS_DB_VARS.READ_REC += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_PROC();
            if (pgmRtn) return;
            T000_GROUP_BY_DATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
        if (WS_DB_VARS.L_CNT != 0) {
            IBS.init(SCCGWA, WS_LIST.WS_PAGE_INF);
            WS_LIST.WS_PAGE_INF.TOTAL_NUM = WS_DB_VARS.L_CNT;
            WS_VARIABLES.L_ROW = WS_LIST.WS_PAGE_INF.TOTAL_NUM % WS_VARIABLES.P_ROW;
            WS_VARIABLES.T_PAGE = (int) ((WS_LIST.WS_PAGE_INF.TOTAL_NUM - WS_VARIABLES.L_ROW) / WS_VARIABLES.P_ROW);
            if (WS_VARIABLES.L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_LIST.WS_PAGE_INF.TOTAL_PAGE = WS_VARIABLES.T_PAGE;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                WS_LIST.WS_PAGE_INF.TOTAL_PAGE = WS_VARIABLES.T_PAGE + 1;
            }
            if (GDCSEQTR.PAGE_NUM >= WS_LIST.WS_PAGE_INF.TOTAL_PAGE) {
                CEP.TRC(SCCGWA, ">>>>>>>");
                CEP.TRC(SCCGWA, "PAGE NUM LARGE");
                WS_LIST.WS_PAGE_INF.CURR_PAGE = WS_LIST.WS_PAGE_INF.TOTAL_PAGE;
                WS_LIST.WS_PAGE_INF.LAST_PAGE = 'Y';
                CEP.TRC(SCCGWA, WS_VARIABLES.L_ROW);
                if (WS_VARIABLES.CNT == 0) {
                    WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.P_ROW;
                } else {
                    WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.CNT;
                }
                CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.PAGE_ROW);
            } else {
                CEP.TRC(SCCGWA, "<<<<<<");
                WS_LIST.WS_PAGE_INF.CURR_PAGE = GDCSEQTR.PAGE_NUM;
                WS_LIST.WS_PAGE_INF.LAST_PAGE = 'N';
                WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.P_ROW;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.P_ROW);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.CURR_PAGE);
            CEP.TRC(SCCGWA, WS_VARIABLES.L_ROW);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_VARIABLES.STR_NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.END_NUM);
            CEP.TRC(SCCGWA, "PAGE INFO:");
            CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
            CEP.TRC(SCCGWA, WS_VARIABLES.T_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.CURR_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.PAGE_ROW);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.SUM_AMT);
        } else {
            WS_LIST.WS_PAGE_INF.TOTAL_NUM = 0;
            WS_LIST.WS_PAGE_INF.TOTAL_PAGE = 0;
            WS_LIST.WS_PAGE_INF.CURR_PAGE = 0;
            WS_LIST.WS_PAGE_INF.LAST_PAGE = 'Y';
            WS_LIST.WS_PAGE_INF.PAGE_ROW = 0;
        }
        T000_GROUP_ADV_AMT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DB_VARS.ALL_ADV_AMT);
        WS_LIST.WS_PAGE_INF.SUM_AMT = WS_DB_VARS.ALL_ADV_AMT;
    }
    public void B050_OUTPUT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.PAGE_ROW);
        CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
        IBS.init(SCCGWA, WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1]);
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].DT = GDRTRAN.KEY.TR_DATE;
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].ADV_BR = GDRTRAN.ADV_BR;
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].CTA_NO = GDRTRAN.KEY.DEAL_CD;
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].REF_NO = GDRTRAN.KEY.BSREF;
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].ADV_AMT = GDRTRAN.ADV_AMT;
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].LACK_AMT = GDRTRAN.LACK_AMT;
        WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].ADV_AC = GDRTRAN.ADV_AC;
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.DEAL_CD = GDRTRAN.KEY.DEAL_CD;
        GDRPLDR.BSREF = GDRTRAN.KEY.BSREF;
        T000_READ_GDTPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        if (WS_COND_FLG.PLDR_FLG == 'Y') {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDRPLDR.KEY.AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                B015_CHECK_GDKD_PROC();
                if (pgmRtn) return;
                if (DDVMPRD.VAL.TD_FLG == '0') {
                    GDRPLDR.KEY.AC_SEQ = 0;
                }
            }
            IBS.init(SCCGWA, GDRSTAC);
            GDRSTAC.KEY.AC = GDRPLDR.KEY.AC;
            GDRSTAC.KEY.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
            T000_READ_GDTSTAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
            WS_LIST.WS_OUTPUT_LST[WS_VARIABLES.CNT-1].ST_AC = GDRSTAC.ST_AC;
        }
        CEP.TRC(SCCGWA, "LIST INT:");
        CEP.TRC(SCCGWA, GDRTRAN.KEY.DEAL_CD);
        CEP.TRC(SCCGWA, GDRTRAN.KEY.BSREF);
        CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRTRAN.ADV_BR);
        CEP.TRC(SCCGWA, GDRTRAN.ADV_AMT);
        CEP.TRC(SCCGWA, GDRTRAN.LACK_AMT);
        CEP.TRC(SCCGWA, GDRTRAN.ADV_AC);
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
    }
    public void B060_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOEQTR);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_LIST);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCOEQTR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCOEQTR;
        SCCFMT.DATA_LEN = 5125;
        CEP.TRC(SCCGWA, WS_LIST);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDRPLDR.KEY.AC;
        DDRCCY.CCY = GDRPLDR.CCY;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
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
    public void T000_READ_GDTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.BSREF = GDCSEQTR.REF_NO;
        GDRTRAN.KEY.DEAL_CD = GDCSEQTR.CTA_NO;
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.where = "BSREF = :GDRTRAN.KEY.BSREF "
            + "AND DEAL_CD = :GDRTRAN.KEY.DEAL_CD";
        IBS.READ(SCCGWA, GDRTRAN, this, GDTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_STARTBR_BY_REFNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.BSREF = GDCSEQTR.REF_NO;
        CEP.TRC(SCCGWA, GDCSEQTR.REF_NO);
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.where = "BSREF = :GDRTRAN.KEY.BSREF";
        GDTTRAN_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_GROUP_BY_REFNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.BSREF = GDCSEQTR.REF_NO;
        CEP.TRC(SCCGWA, GDCSEQTR.REF_NO);
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.set = "WS-L-CNT=COUNT(*)";
        GDTTRAN_RD.where = "BSREF = :GDRTRAN.KEY.BSREF";
        IBS.GROUP(SCCGWA, GDRTRAN, this, GDTTRAN_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_STARTBR_BY_CTADT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.DEAL_CD = GDCSEQTR.CTA_NO;
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD";
        GDTTRAN_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_GROUP_BY_CTADT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.DEAL_CD = GDCSEQTR.CTA_NO;
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.set = "WS-L-CNT=COUNT(*)";
        GDTTRAN_RD.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD";
        IBS.GROUP(SCCGWA, GDRTRAN, this, GDTTRAN_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_STARTBR_BY_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        WS_DB_VARS.STR_DT = GDCSEQTR.SDT;
        WS_DB_VARS.END_DT = GDCSEQTR.EDT;
        GDRTRAN.ADV_BR = GDCSEQTR.ADV_BR;
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.where = "TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT "
            + "AND ADV_BR = :GDRTRAN.ADV_BR";
        GDTTRAN_BR.rp.order = "TR_DATE";
        IBS.STARTBR(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_GROUP_BY_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        WS_DB_VARS.STR_DT = GDCSEQTR.SDT;
        WS_DB_VARS.END_DT = GDCSEQTR.EDT;
        GDRTRAN.ADV_BR = GDCSEQTR.ADV_BR;
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.set = "WS-L-CNT=COUNT(*)";
        GDTTRAN_RD.where = "TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT "
            + "AND ADV_BR = :GDRTRAN.ADV_BR";
        IBS.GROUP(SCCGWA, GDRTRAN, this, GDTTRAN_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_GROUP_ADV_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.DEAL_CD = GDCSEQTR.CTA_NO;
        GDRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, GDRTRAN.KEY.DEAL_CD);
        CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD "
            + "AND TR_DATE = :GDRTRAN.KEY.TR_DATE";
        GDTTRAN_RD.set = "WS-ALL-ADV-AMT=IFNULL(SUM(ADV_AMT),0)";
        IBS.GROUP(SCCGWA, GDRTRAN, this, GDTTRAN_RD);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DB_VARS.READ_REC);
        IBS.READNEXT(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTTRAN_BR);
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "BSREF = :GDRPLDR.BSREF "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.PLDR_FLG = 'N';
        } else {
            WS_COND_FLG.PLDR_FLG = 'Y';
        }
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ";
        GDTSTAC_RD.fst = true;
        IBS.READ(SCCGWA, GDRSTAC, this, GDTSTAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
