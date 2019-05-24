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

public class GDZSEQRL {
    GDZSEQRL_WS_OUTPUT_LST WS_OUTPUT_LST;
    GDCOEQRL_OUTPUT_LST OUTPUT_LST;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    brParm GDTHIS_BR = new brParm();
    DBParm GDTHIS_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "GD820";
    int SDT = 20140414;
    int EDT = 20991231;
    GDZSEQRL_WS_VARIABLES WS_VARIABLES = new GDZSEQRL_WS_VARIABLES();
    GDZSEQRL_WS_LIST WS_LIST = new GDZSEQRL_WS_LIST();
    GDZSEQRL_WS_COND_FLG WS_COND_FLG = new GDZSEQRL_WS_COND_FLG();
    GDCMSG_ERROR_MSG ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    CICQACAC CICQACAC = new CICQACAC();
    GDCOEQRL GDCOEQRL = new GDCOEQRL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDVMRAT_WS_DB_VARS WS_DB_VARS = new DDVMRAT_WS_DB_VARS();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    GDCSEQRL GDCSEQRL;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, GDCSEQRL GDCSEQRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSEQRL = GDCSEQRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSEQRL return!");
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
        CEP.TRC(SCCGWA, GDCSEQRL.RSEQ);
    }
    public void B010_CHECK_ENQUIRY_CONDITION() throws IOException,SQLException,Exception {
        if (GDCSEQRL.EDT == 0) {
            GDCSEQRL.EDT = EDT;
        }
        WS_DB_VARS.STR_DT = GDCSEQRL.SDT;
        WS_DB_VARS.END_DT = GDCSEQRL.EDT;
        CEP.TRC(SCCGWA, WS_DB_VARS.STR_DT);
        CEP.TRC(SCCGWA, WS_DB_VARS.END_DT);
        if (GDCSEQRL.AC.trim().length() > 0 
            && GDCSEQRL.AC_SEQ == 0) {
            CEP.TRC(SCCGWA, "1111111111");
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSEQRL.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            B065_CHECK_GDKD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
            if (DDVMPRD.VAL.TD_FLG == '0') {
                GDCSEQRL.TXREA_FL = '3';
            }
            CEP.TRC(SCCGWA, GDCSEQRL.TXREA_FL);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "TTTTT");
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = GDCSEQRL.AC;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DD_MST1_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_AC_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                        CEP.TRC(SCCGWA, "NOT GD AC");
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_NOT_GD_AC;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "DDDDDDD");
                CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
                if (DDRMST.AC_TYPE != 'N') {
                    CEP.TRC(SCCGWA, "NOT GD AC");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_NOT_GD_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (GDCSEQRL.AC.trim().length() > 0 
            && GDCSEQRL.AC_SEQ != 0) {
            CEP.TRC(SCCGWA, "222222222");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDCSEQRL.AC;
            CICQACAC.DATA.AGR_SEQ = GDCSEQRL.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            } else {
                if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                    CEP.TRC(SCCGWA, "NOT GD AC");
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_NOT_GD_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_TRANS_DATA_PRO() throws IOException,SQLException,Exception {
        if (GDCSEQRL.PAGE_ROW == 0) {
            WS_VARIABLES.P_ROW = 25;
            WS_OUTPUT_LST = new GDZSEQRL_WS_OUTPUT_LST();
            WS_LIST.WS_OUTPUT_LST.add(WS_OUTPUT_LST);
        } else {
            WS_VARIABLES.P_ROW = GDCSEQRL.PAGE_ROW;
            WS_OUTPUT_LST = new GDZSEQRL_WS_OUTPUT_LST();
            WS_LIST.WS_OUTPUT_LST.add(WS_OUTPUT_LST);
        }
        if (GDCSEQRL.PAGE_NUM == 0) {
            GDCSEQRL.PAGE_NUM = 1;
        }
        WS_DB_VARS.L_CNT = 0;
        WS_DB_VARS.READ_REC = ( ( GDCSEQRL.PAGE_NUM - 1 ) * WS_VARIABLES.P_ROW ) + 1;
        CEP.TRC(SCCGWA, WS_DB_VARS.READ_REC);
        CEP.TRC(SCCGWA, WS_VARIABLES.P_ROW);
        CEP.TRC(SCCGWA, GDCSEQRL.PAGE_NUM);
    }
    public void B030_GET_PAGE_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
        if (GDCSEQRL.RSEQ.trim().length() > 0 
            && GDCSEQRL.AC.trim().length() > 0) {
            T000_STARTBR_BY_RSEQAC();
            if (pgmRtn) return;
            T000_READNEXT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDCSEQRL.SDT);
            CEP.TRC(SCCGWA, GDCSEQRL.EDT);
            CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
            while (WS_COND_FLG.RL_FLAG != 'N' 
                && WS_VARIABLES.CNT < WS_VARIABLES.P_ROW) {
                CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
                WS_VARIABLES.CNT += 1;
                B050_OUTPUT_LIST();
                if (pgmRtn) return;
                WS_DB_VARS.READ_REC += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_PROC();
            if (pgmRtn) return;
            T000_GROUP_BY_RSEQAC();
            if (pgmRtn) return;
        } else {
            if (GDCSEQRL.CTA_NO.trim().length() > 0) {
                T000_STARTBR_BY_CTADT();
                if (pgmRtn) return;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, GDCSEQRL.SDT);
                CEP.TRC(SCCGWA, GDCSEQRL.EDT);
                CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
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
                if (GDCSEQRL.AC.trim().length() > 0 
                    && GDCSEQRL.TXREA_FL != '3') {
                    T000_STARTBR_BY_SEQAC();
                    if (pgmRtn) return;
                    T000_READNEXT_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, GDCSEQRL.SDT);
                    CEP.TRC(SCCGWA, GDCSEQRL.EDT);
                    CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
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
                    T000_GROUP_BY_SEQAC();
                    if (pgmRtn) return;
                } else {
                    if (GDCSEQRL.AC.trim().length() > 0 
                        && GDCSEQRL.TXREA_FL == '3') {
                        T000_STARTBR_BY_KDAC();
                        if (pgmRtn) return;
                        T000_READNEXT_PROC();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, GDCSEQRL.SDT);
                        CEP.TRC(SCCGWA, GDCSEQRL.EDT);
                        CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
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
                        T000_GROUP_BY_KDAC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (GDCSEQRL.RSEQ.trim().length() > 0 
            && GDCSEQRL.AC.trim().length() == 0) {
            T000_STARTBR_BY_RSEQ();
            if (pgmRtn) return;
            T000_READNEXT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDCSEQRL.SDT);
            CEP.TRC(SCCGWA, GDCSEQRL.EDT);
            CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
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
            T000_GROUP_BY_RSEQ();
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
            if (GDCSEQRL.PAGE_NUM >= WS_LIST.WS_PAGE_INF.TOTAL_PAGE) {
                CEP.TRC(SCCGWA, ">>>>>>>");
                CEP.TRC(SCCGWA, "PAGE NUM LARGE");
                WS_LIST.WS_PAGE_INF.CURR_PAGE = WS_LIST.WS_PAGE_INF.TOTAL_PAGE;
                WS_LIST.WS_PAGE_INF.LAST_PAGE = 'Y';
                CEP.TRC(SCCGWA, WS_VARIABLES.L_ROW);
                if (WS_VARIABLES.CNT == 0) {
                    WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.P_ROW;
                    OUTPUT_LST = new GDCOEQRL_OUTPUT_LST();
                    GDCOEQRL.OUTPUT_LST.add(OUTPUT_LST);
                } else {
                    WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.CNT;
                    OUTPUT_LST = new GDCOEQRL_OUTPUT_LST();
                    GDCOEQRL.OUTPUT_LST.add(OUTPUT_LST);
                }
                CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.PAGE_ROW);
            } else {
                CEP.TRC(SCCGWA, "<<<<<<");
                WS_LIST.WS_PAGE_INF.CURR_PAGE = GDCSEQRL.PAGE_NUM;
                WS_LIST.WS_PAGE_INF.LAST_PAGE = 'N';
                WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.P_ROW;
                OUTPUT_LST = new GDCOEQRL_OUTPUT_LST();
                GDCOEQRL.OUTPUT_LST.add(OUTPUT_LST);
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
        } else {
            WS_LIST.WS_PAGE_INF.TOTAL_NUM = 0;
            WS_LIST.WS_PAGE_INF.TOTAL_PAGE = 0;
            WS_LIST.WS_PAGE_INF.CURR_PAGE = 0;
            WS_LIST.WS_PAGE_INF.LAST_PAGE = 'Y';
            WS_LIST.WS_PAGE_INF.PAGE_ROW = 0;
            OUTPUT_LST = new GDCOEQRL_OUTPUT_LST();
            GDCOEQRL.OUTPUT_LST.add(OUTPUT_LST);
        }
    }
    public void B040_GET_INT_LIST1() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_RSEQAC();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        while (WS_COND_FLG.RL_FLAG != 'N') {
            if (GDRHIS.UPDTBL_DATE >= GDCSEQRL.SDT 
                && GDRHIS.UPDTBL_DATE <= GDCSEQRL.EDT) {
                WS_VARIABLES.I += 1;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.I);
            CEP.TRC(SCCGWA, WS_VARIABLES.STR_NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.END_NUM);
            if (WS_VARIABLES.I >= WS_VARIABLES.STR_NUM 
                && WS_VARIABLES.I <= WS_VARIABLES.END_NUM) {
                WS_VARIABLES.CNT += 1;
                B050_OUTPUT_LIST();
                if (pgmRtn) return;
            }
            T000_READNEXT_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B040_GET_INT_LIST2() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_SEQAC();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        while (WS_COND_FLG.RL_FLAG != 'N') {
            if (GDRHIS.UPDTBL_DATE >= GDCSEQRL.SDT 
                && GDRHIS.UPDTBL_DATE <= GDCSEQRL.EDT) {
                WS_VARIABLES.I += 1;
                CEP.TRC(SCCGWA, WS_VARIABLES.I);
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.STR_NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.END_NUM);
            if (WS_VARIABLES.I >= WS_VARIABLES.STR_NUM 
                && WS_VARIABLES.I <= WS_VARIABLES.END_NUM) {
                WS_VARIABLES.CNT += 1;
                B050_OUTPUT_LIST();
                if (pgmRtn) return;
            }
            T000_READNEXT_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B040_GET_INT_LIST3() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_RSEQ();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        while (WS_COND_FLG.RL_FLAG != 'N') {
            if (GDRHIS.UPDTBL_DATE >= GDCSEQRL.SDT 
                && GDRHIS.UPDTBL_DATE <= GDCSEQRL.EDT) {
                WS_VARIABLES.I += 1;
                CEP.TRC(SCCGWA, WS_VARIABLES.I);
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.STR_NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.END_NUM);
            if (WS_VARIABLES.I >= WS_VARIABLES.STR_NUM 
                && WS_VARIABLES.I <= WS_VARIABLES.END_NUM) {
                WS_VARIABLES.CNT += 1;
                B050_OUTPUT_LIST();
                if (pgmRtn) return;
            }
            T000_READNEXT_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.PAGE_ROW);
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
        IBS.init(SCCGWA, WS_OUTPUT_LST);
        WS_OUTPUT_LST.DT = GDRHIS.KEY.TR_DATE;
        WS_OUTPUT_LST.JRNNO = GDRHIS.KEY.JRNNO;
        WS_OUTPUT_LST.RSEQ = GDRHIS.RSEQ;
        WS_OUTPUT_LST.OTHER_AC = GDRHIS.TR_AC;
        if (GDRHIS.AC_SEQ == 0) {
            WS_OUTPUT_LST.TD_AC = GDRHIS.AC;
            WS_VARIABLES.AC = GDRHIS.AC;
        } else {
            WS_OUTPUT_LST.TD_AC = GDRHIS.AC;
            WS_OUTPUT_LST.AC_SEQ = GDRHIS.AC_SEQ;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRHIS.AC;
            CICQACAC.DATA.AGR_SEQ = GDRHIS.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_OUTPUT_LST.DD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_VARIABLES.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        R000_GET_TDTSMST();
        if (pgmRtn) return;
        WS_OUTPUT_LST.CTA_NO = GDRHIS.DEAL_CD;
        WS_OUTPUT_LST.REF_NO = GDRHIS.BSREF;
        WS_OUTPUT_LST.TYP1 = GDRHIS.FUNC;
        WS_OUTPUT_LST.AMT = GDRHIS.TR_AMT;
        WS_OUTPUT_LST.FLG = GDRHIS.CAN_FLG;
        WS_OUTPUT_LST.CHL = GDRHIS.CHNL_CD;
        WS_OUTPUT_LST.C_DT = GDRHIS.UPDTBL_TLR;
        if (GDRHIS.TS == null) GDRHIS.TS = "";
        JIBS_tmp_int = GDRHIS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) GDRHIS.TS += " ";
        WS_VARIABLES.WS_TM1.HOUR = GDRHIS.TS.substring(12 - 1, 12 + 2 - 1);
        if (GDRHIS.TS == null) GDRHIS.TS = "";
        JIBS_tmp_int = GDRHIS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) GDRHIS.TS += " ";
        WS_VARIABLES.WS_TM1.MINU = GDRHIS.TS.substring(15 - 1, 15 + 2 - 1);
        if (GDRHIS.TS == null) GDRHIS.TS = "";
        JIBS_tmp_int = GDRHIS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) GDRHIS.TS += " ";
        WS_VARIABLES.WS_TM1.SECD = GDRHIS.TS.substring(18 - 1, 18 + 2 - 1);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TM1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TM1);
        WS_OUTPUT_LST.TM = Integer.parseInt(JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, WS_LIST.WS_OUTPUT_LST.get(WS_VARIABLES.CNT-1).TM);
        CEP.TRC(SCCGWA, "LIST INT:");
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, GDRHIS.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.DEAL_CD);
        CEP.TRC(SCCGWA, GDRHIS.BSREF);
        CEP.TRC(SCCGWA, GDRHIS.FUNC);
        CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
        CEP.TRC(SCCGWA, GDRHIS.CAN_FLG);
        CEP.TRC(SCCGWA, GDRHIS.CHNL_CD);
        CEP.TRC(SCCGWA, GDRHIS.UPDTBL_TLR);
        CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
        CEP.TRC(SCCGWA, GDRHIS.TR_AC);
    }
    public void B060_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOEQRL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_LIST);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCOEQRL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCOEQRL;
        SCCFMT.DATA_LEN = 6675;
        CEP.TRC(SCCGWA, WS_LIST);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B065_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSEQRL.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSEQRL.AC;
        DDRCCY.CCY = CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR;
        DDRCCY.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CCY_ACR);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void R000_GET_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_VARIABLES.AC;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, TDRSMST.TERM);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_OUTPUT_LST.TERM = TDRSMST.TERM;
            WS_OUTPUT_LST.EXP_DT = TDRSMST.EXP_DATE;
        }
    }
    public void T000_STARTBR_BY_RSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.RSEQ = GDCSEQRL.RSEQ;
        CEP.TRC(SCCGWA, GDCSEQRL.RSEQ);
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "RSEQ = :GDRHIS.RSEQ "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        GDTHIS_BR.rp.order = "TR_DATE,JRNNO";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
    }
    public void T000_GROUP_BY_RSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.RSEQ = GDCSEQRL.RSEQ;
        CEP.TRC(SCCGWA, GDCSEQRL.RSEQ);
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.set = "WS-L-CNT=COUNT(*)";
        GDTHIS_RD.where = "RSEQ = :GDRHIS.RSEQ "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        IBS.GROUP(SCCGWA, GDRHIS, this, GDTHIS_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_STARTBR_BY_RSEQAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.RSEQ = GDCSEQRL.RSEQ;
        GDRHIS.AC = GDCSEQRL.AC;
        CEP.TRC(SCCGWA, GDCSEQRL.RSEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC);
        if (GDCSEQRL.AC_SEQ != 0) {
            GDRHIS.AC_SEQ = GDCSEQRL.AC_SEQ;
            CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
            CEP.TRC(SCCGWA, "RSEQ+AC+SEQ");
            GDTHIS_BR.rp = new DBParm();
            GDTHIS_BR.rp.TableName = "GDTHIS";
            GDTHIS_BR.rp.where = "RSEQ = :GDRHIS.RSEQ "
                + "AND AC = :GDRHIS.AC "
                + "AND AC_SEQ = :GDRHIS.AC_SEQ "
                + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
                + "AND TR_DATE <= :WS_DB_VARS.END_DT";
            GDTHIS_BR.rp.order = "TR_DATE,JRNNO";
            IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        } else {
            CEP.TRC(SCCGWA, "RSEQ+AC");
            GDTHIS_BR.rp = new DBParm();
            GDTHIS_BR.rp.TableName = "GDTHIS";
            GDTHIS_BR.rp.where = "RSEQ = :GDRHIS.RSEQ "
                + "AND AC = :GDRHIS.AC "
                + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
                + "AND TR_DATE <= :WS_DB_VARS.END_DT";
            GDTHIS_BR.rp.order = "TR_DATE,JRNNO";
            IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_GROUP_BY_RSEQAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.RSEQ = GDCSEQRL.RSEQ;
        GDRHIS.AC = GDCSEQRL.AC;
        CEP.TRC(SCCGWA, GDCSEQRL.RSEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC);
        if (GDCSEQRL.AC_SEQ != 0) {
            GDRHIS.AC_SEQ = GDCSEQRL.AC_SEQ;
            CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
            CEP.TRC(SCCGWA, "RSEQ+AC+SEQ");
            GDTHIS_RD = new DBParm();
            GDTHIS_RD.TableName = "GDTHIS";
            GDTHIS_RD.set = "WS-L-CNT=COUNT(*)";
            GDTHIS_RD.where = "RSEQ = :GDRHIS.RSEQ "
                + "AND AC = :GDRHIS.AC "
                + "AND AC_SEQ = :GDRHIS.AC_SEQ "
                + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
                + "AND TR_DATE <= :WS_DB_VARS.END_DT";
            IBS.GROUP(SCCGWA, GDRHIS, this, GDTHIS_RD);
        } else {
            CEP.TRC(SCCGWA, "RSEQ+AC");
            GDTHIS_RD = new DBParm();
            GDTHIS_RD.TableName = "GDTHIS";
            GDTHIS_RD.set = "WS-L-CNT=COUNT(*)";
            GDTHIS_RD.where = "RSEQ = :GDRHIS.RSEQ "
                + "AND AC = :GDRHIS.AC "
                + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
                + "AND TR_DATE <= :WS_DB_VARS.END_DT";
            IBS.GROUP(SCCGWA, GDRHIS, this, GDTHIS_RD);
        }
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_STARTBR_BY_SEQAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.AC_SEQ = GDCSEQRL.AC_SEQ;
        GDRHIS.AC = GDCSEQRL.AC;
        CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC);
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "AC_SEQ = :GDRHIS.AC_SEQ "
            + "AND AC = :GDRHIS.AC "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        GDTHIS_BR.rp.order = "TR_DATE,JRNNO";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
    }
    public void T000_STARTBR_BY_KDAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.AC = GDCSEQRL.AC;
        CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC);
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "AC = :GDRHIS.AC "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        GDTHIS_BR.rp.order = "TR_DATE,JRNNO";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, GDRHIS.UPDTBL_DATE);
    }
    public void T000_GROUP_BY_SEQAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.AC_SEQ = GDCSEQRL.AC_SEQ;
        GDRHIS.AC = GDCSEQRL.AC;
        CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC);
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.set = "WS-L-CNT=COUNT(*)";
        GDTHIS_RD.where = "AC_SEQ = :GDRHIS.AC_SEQ "
            + "AND AC = :GDRHIS.AC "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        IBS.GROUP(SCCGWA, GDRHIS, this, GDTHIS_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_GROUP_BY_KDAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.AC = GDCSEQRL.AC;
        CEP.TRC(SCCGWA, GDCSEQRL.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSEQRL.AC);
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.set = "WS-L-CNT=COUNT(*)";
        GDTHIS_RD.where = "AC = :GDRHIS.AC "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        IBS.GROUP(SCCGWA, GDRHIS, this, GDTHIS_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_STARTBR_BY_CTADT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.DEAL_CD = GDCSEQRL.CTA_NO;
        GDRHIS.BSREF = GDCSEQRL.REF_NO;
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "DEAL_CD = :GDRHIS.DEAL_CD "
            + "AND BSREF = :GDRHIS.BSREF "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        GDTHIS_BR.rp.order = "TR_DATE,JRNNO";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
        }
    }
    public void T000_GROUP_BY_CTADT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.DEAL_CD = GDCSEQRL.CTA_NO;
        GDRHIS.BSREF = GDCSEQRL.REF_NO;
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.set = "WS-L-CNT=COUNT(*)";
        GDTHIS_RD.where = "DEAL_CD = :GDRHIS.DEAL_CD "
            + "AND BSREF = :GDRHIS.BSREF "
            + "AND TR_DATE >= :WS_DB_VARS.STR_DT "
            + "AND TR_DATE <= :WS_DB_VARS.END_DT";
        IBS.GROUP(SCCGWA, GDRHIS, this, GDTHIS_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.L_CNT);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DB_VARS.READ_REC);
        IBS.READNEXT(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.RL_FLAG = 'N';
        } else {
            WS_COND_FLG.RL_FLAG = 'Y';
            CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTHIS_BR);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
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
