package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSQLDR {
    GDZSQLDR_WS_OUT_INFO WS_OUT_INFO;
    String JIBS_tmp_str[] = new String[10];
    DBParm GDTPLDR_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm DCTIAACR_RD;
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String OUTPUT_FMT = "GD810";
    int PAGE_ROW = 25;
    GDZSQLDR_WS_VARIABLES WS_VARIABLES = new GDZSQLDR_WS_VARIABLES();
    GDZSQLDR_WS_OUT_RECODE WS_OUT_RECODE = new GDZSQLDR_WS_OUT_RECODE();
    GDZSQLDR_WS_COND_FLG WS_COND_FLG = new GDZSQLDR_WS_COND_FLG();
    GDCMSG_ERROR_MSG ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG ERROR_MSG = new TDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    GDCOQLDR GDCOQLDR = new GDCOQLDR();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDVMRAT_WS_DB_VARS WS_DB_VARS = new DDVMRAT_WS_DB_VARS();
    GDRPLDR GDRPLDR = new GDRPLDR();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCPQBNK_DATA_INFO DATA_INFO;
    GDCSQLDR GDCSQLDR;
    public void MP(SCCGWA SCCGWA, GDCSQLDR GDCSQLDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSQLDR = GDCSQLDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSQLDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_10_CHECK_AC_STS();
        if (pgmRtn) return;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_DEPOSIT_PROC();
        if (pgmRtn) return;
    }
    public void B000_10_CHECK_AC_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSQLDR.TXDD_AC);
        CEP.TRC(SCCGWA, GDCSQLDR.TXAC_SEQ);
        if (GDCSQLDR.TXDD_AC.trim().length() > 0 
            && GDCSQLDR.TXAC_SEQ == 0) {
            CEP.TRC(SCCGWA, "1111111111");
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSQLDR.TXDD_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            R000_CHECK_GDKD_PROC();
            if (pgmRtn) return;
            if (DDVMPRD.VAL.TD_FLG == '0' 
                && GDCSQLDR.TXREA_FL == '2') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_GDKD_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "TTTTT");
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = GDCSQLDR.TXDD_AC;
                T000_READ_TDTSMST_01();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_AC_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DD_MST1_REC_NOTFND;
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
        if (GDCSQLDR.TXDD_AC.trim().length() > 0 
            && GDCSQLDR.TXAC_SEQ != 0) {
            CEP.TRC(SCCGWA, "222222222");
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = GDCSQLDR.TXDD_AC;
            CICQACAC.DATA.AGR_SEQ = GDCSQLDR.TXAC_SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_NONEX_CONNECT;
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
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSQLDR.PAGE_NUM);
        CEP.TRC(SCCGWA, GDCSQLDR.PAGE_ROW);
        CEP.TRC(SCCGWA, GDCSQLDR.PAGE_NUM);
        CEP.TRC(SCCGWA, GDCSQLDR.PAGE_ROW);
        CEP.TRC(SCCGWA, GDCSQLDR.TXRSEQ);
        CEP.TRC(SCCGWA, GDCSQLDR.TXDD_AC);
        CEP.TRC(SCCGWA, GDCSQLDR.TXAC_SEQ);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT);
        if (GDCSQLDR.TXRSEQ.trim().length() > 0 
            && GDCSQLDR.TXDD_AC.trim().length() == 0 
            && GDCSQLDR.TXAC_SEQ == 0) {
            WS_VARIABLES.FLG = '1';
        }
        if (GDCSQLDR.TXRSEQ.trim().length() == 0 
            && GDCSQLDR.TXDD_AC.trim().length() > 0) {
            WS_VARIABLES.FLG = '5';
        }
        if (GDCSQLDR.TXDD_AC.trim().length() > 0 
            && GDCSQLDR.TXAC_SEQ != 0) {
            WS_VARIABLES.FLG = '2';
        }
        if (GDCSQLDR.TXRSEQ.trim().length() == 0 
            && GDCSQLDR.CTA_NO.trim().length() > 0) {
            WS_VARIABLES.FLG = '4';
        }
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (GDCSQLDR.PAGE_NUM == 0) {
            WS_VARIABLES.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.CURR_PAGE = GDCSQLDR.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_VARIABLES.LAST_PAGE = 'N';
        if (GDCSQLDR.PAGE_ROW == 0) {
            WS_VARIABLES.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new GDZSQLDR_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (GDCSQLDR.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.GD_PAGE_ROW_INVAILD, GDCSQLDR.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.PAGE_ROW = GDCSQLDR.PAGE_ROW;
                WS_OUT_INFO = new GDZSQLDR_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        WS_VARIABLES.NEXT_START_NUM = ( ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
        CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
    }
    public void B020_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.FLG);
        if (WS_VARIABLES.FLG == '1' 
            || WS_VARIABLES.FLG == '2' 
            || WS_VARIABLES.FLG == '4' 
            || WS_VARIABLES.FLG == '5') {
            B025_PROC_BROWSE();
            if (pgmRtn) return;
        } else {
            if (WS_VARIABLES.FLG == '3') {
                B030_QUERY_DEPOSIT_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B025_PROC_BROWSE() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.FLG == '1') {
            T000_STARTBR_GDTPLDR();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.FLG == '2') {
            T000_STARTBR_GDTPLDR_1();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.FLG == '4') {
            T000_STARTBR_GDTPLDR_2();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.FLG == '5') {
            T000_STARTBR_GDTPLDR_3();
            if (pgmRtn) return;
        }
        T000_READNEXT_GDTPLDR();
        if (pgmRtn) return;
        if (WS_COND_FLG.PLDR_FLG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.PLDR_FLG != 'N') {
            WS_VARIABLES.IDX = 0;
            WS_VARIABLES.TOTAL_NUM = 0;
            while (WS_VARIABLES.IDX != WS_VARIABLES.PAGE_ROW 
                && WS_COND_FLG.PLDR_FLG != 'N') {
                B000_WRITE_OUTPUT();
                if (pgmRtn) return;
                WS_VARIABLES.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
                T000_READNEXT_GDTPLDR();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.PLDR_FLG == 'N') {
                CEP.TRC(SCCGWA, "IF IF");
                R000_GROUP_ALL_AMT();
                if (pgmRtn) return;
                WS_VARIABLES.TOTAL_PAGE = WS_VARIABLES.CURR_PAGE;
                WS_VARIABLES.BAL_CNT = WS_VARIABLES.IDX;
                WS_VARIABLES.TOTAL_NUM = ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW + WS_VARIABLES.BAL_CNT;
                WS_VARIABLES.LAST_PAGE = 'Y';
                WS_VARIABLES.PAGE_ROW = WS_VARIABLES.BAL_CNT;
                WS_OUT_INFO = new GDZSQLDR_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                CEP.TRC(SCCGWA, "IF ELSE");
                R000_GROUP_ALL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                WS_VARIABLES.BAL_CNT = WS_VARIABLES.TOTAL_NUM % WS_VARIABLES.PAGE_ROW;
                WS_VARIABLES.TOTAL_PAGE = (int) ((WS_VARIABLES.TOTAL_NUM - WS_VARIABLES.BAL_CNT) / WS_VARIABLES.PAGE_ROW);
                if (WS_VARIABLES.BAL_CNT != 0) {
                    WS_VARIABLES.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = 0;
            WS_VARIABLES.TOTAL_PAGE = 0;
            WS_VARIABLES.TOTAL_NUM = 0;
            WS_VARIABLES.LAST_PAGE = 'Y';
            WS_VARIABLES.PAGE_ROW = 0;
            WS_OUT_INFO = new GDZSQLDR_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        T000_ENDBR_GDTPLDR();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTALNUM = WS_VARIABLES.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTALNUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        B000_OUTPUT_WRITE();
        if (pgmRtn) return;
    }
    public void B030_QUERY_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        T000_READ_GDTPLDR();
        if (pgmRtn) return;
        if (GDRPLDR.RELAT_STS == 'N') {
            B020_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_VARIABLES.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        if (WS_VARIABLES.FLG == '1' 
            || WS_VARIABLES.FLG == '4') {
            WS_OUT_RECODE.WS_OUT_HEAD.O_RSEQ = GDRPLDR.KEY.RSEQ;
            WS_OUT_INFO.O_RSEQ_L = GDRPLDR.KEY.RSEQ;
        }
        if (WS_VARIABLES.FLG == '2' 
            || WS_VARIABLES.FLG == '3' 
            || WS_VARIABLES.FLG == '5') {
            WS_OUT_INFO.O_RSEQ_L = GDRPLDR.KEY.RSEQ;
        }
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_RSEQ);
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTALNUM = WS_VARIABLES.TOTAL_NUM;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTALNUM);
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        WS_OUT_INFO.O_OTRAMT = GDRPLDR.RELAT_AMT;
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTRAMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        WS_VARIABLES.PLDR_RELAT_AMT = WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTRAMT + WS_VARIABLES.PLDR_RELAT_AMT;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT = WS_VARIABLES.PLDR_RELAT_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.PLDR_RELAT_AMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT);
        if (GDRPLDR.KEY.AC_SEQ == 0) {
            CEP.TRC(SCCGWA, "IF1");
            WS_OUT_INFO.O_OTAC = GDRPLDR.KEY.AC;
            WS_OUT_INFO.O_OTDD_AC = GDRPLDR.KEY.AC;
            WS_OUT_INFO.O_SEQ = GDRPLDR.KEY.AC_SEQ;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTAC);
        } else {
            WS_OUT_INFO.O_OTDD_AC = GDRPLDR.KEY.AC;
            WS_OUT_INFO.O_SEQ = GDRPLDR.KEY.AC_SEQ;
            CEP.TRC(SCCGWA, "ELSE1");
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTDD_AC);
        }
        WS_OUT_INFO.O_CCY = GDRPLDR.CCY;
        if (GDRPLDR.AC_TYP == '0') {
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                WS_OUT_INFO.O_OTDD_AC = GDRPLDR.KEY.AC;
            }
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDRPLDR.KEY.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
            WS_OUT_INFO.O_OTCTYP = DDRCCY.CCY_TYPE;
            WS_OUT_INFO.O_OTPROD_CD = DDRMST.PROD_CODE;
            CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTPROD_CD);
        }
        if (GDRPLDR.AC_TYP == '1' 
            && GDRPLDR.KEY.AC_SEQ == 0) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = GDRPLDR.KEY.AC;
            T000_READ_TDTSMST_01();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
            WS_OUT_INFO.O_OTCTYP = TDRSMST.CCY_TYP;
            WS_OUT_INFO.O_OTPROD_CD = TDRSMST.PROD_CD;
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTPROD_CD);
        }
        if (GDRPLDR.AC_TYP == '1' 
            && GDRPLDR.KEY.AC_SEQ != 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            WS_OUT_INFO.O_OTCTYP = TDRSMST.CCY_TYP;
            WS_OUT_INFO.O_OTPROD_CD = TDRSMST.PROD_CD;
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_OTPROD_CD);
        }
        WS_OUT_INFO.O_OTTYP1 = GDRPLDR.REF_TYP;
        WS_OUT_INFO.O_OTCTA_NO = GDRPLDR.DEAL_CD;
        WS_OUT_INFO.O_OTREF_NO = GDRPLDR.BSREF;
        WS_OUT_INFO.O_OTDT = GDRPLDR.CRT_DATE;
    }
    public void B000_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.PLDR_RELAT_AMT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUT_RECODE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCOQLDR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "GD810";
        SCCFMT.DATA_PTR = GDCOQLDR;
        SCCFMT.DATA_LEN = 6400;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.PLDR_RELAT_AMT);
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 1;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = 1;
        WS_OUT_RECODE.WS_OUT_HEAD.O_RSEQ = GDRPLDR.KEY.RSEQ;
        WS_OUT_INFO.O_RSEQ_L = GDRPLDR.KEY.RSEQ;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = 1;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTALNUM = 1;
        WS_OUT_INFO.O_OTRAMT = GDRPLDR.RELAT_AMT;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT = WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_OTRAMT;
        if (GDRPLDR.KEY.AC_SEQ == 0) {
            CEP.TRC(SCCGWA, "IF");
            WS_OUT_INFO.O_OTAC = GDRPLDR.KEY.AC;
            WS_OUT_INFO.O_SEQ = GDRPLDR.KEY.AC_SEQ;
        } else {
            CEP.TRC(SCCGWA, "ELSE");
            WS_OUT_INFO.O_OTDD_AC = GDRPLDR.KEY.AC;
            WS_OUT_INFO.O_SEQ = GDRPLDR.KEY.AC_SEQ;
        }
        WS_OUT_INFO.O_CCY = GDRPLDR.CCY;
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        if (GDRPLDR.AC_TYP == '0') {
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                WS_OUT_INFO.O_OTDD_AC = GDRPLDR.KEY.AC;
            }
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDRPLDR.KEY.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
            WS_OUT_INFO.O_OTCTYP = DDRCCY.CCY_TYPE;
            WS_OUT_INFO.O_OTPROD_CD = DDRMST.PROD_CODE;
            CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_OTPROD_CD);
        }
        if (GDRPLDR.AC_TYP == '1' 
            && GDRPLDR.KEY.AC_SEQ == 0) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = GDRPLDR.KEY.AC;
            T000_READ_TDTSMST_01();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
            WS_OUT_INFO.O_OTCTYP = TDRSMST.CCY_TYP;
            WS_OUT_INFO.O_OTPROD_CD = TDRSMST.PROD_CD;
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_OTPROD_CD);
        }
        WS_OUT_INFO.O_OTTYP1 = GDRPLDR.REF_TYP;
        WS_OUT_INFO.O_OTCTA_NO = GDRPLDR.DEAL_CD;
        WS_OUT_INFO.O_OTREF_NO = GDRPLDR.BSREF;
        WS_OUT_INFO.O_OTDT = GDRPLDR.CRT_DATE;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_RSEQ);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTALNUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_OTAC);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_OTDD_AC);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_SEQ);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_CCY);
        IBS.init(SCCGWA, GDCOQLDR);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUT_RECODE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCOQLDR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "GD810";
        SCCFMT.DATA_PTR = GDCOQLDR;
        SCCFMT.DATA_LEN = 6400;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.FLG == 1) {
            T000_02_GROUP_AMT_PROC();
            if (pgmRtn) return;
            T000_01_GROUP_REC_PROC();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.FLG == 2) {
            T000_02_GROUP_AMT_PROC_1();
            if (pgmRtn) return;
            T000_01_GROUP_REC_PROC_1();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.FLG == 4) {
            T000_02_GROUP_AMT_PROC_3();
            if (pgmRtn) return;
            T000_01_GROUP_REC_PROC_3();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.FLG == 5) {
            T000_02_GROUP_AMT_PROC_2();
            if (pgmRtn) return;
            T000_01_GROUP_REC_PROC_2();
            if (pgmRtn) return;
        }
        WS_VARIABLES.TOTAL_NUM = WS_DB_VARS.CNT;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT = WS_DB_VARS.ALL_RELAT_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
    }
    public void R000_GROUP_ALL_AMT() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.FLG == '2') {
            T000_02_GROUP_AMT_PROC_1();
            if (pgmRtn) return;
        } else {
            if (WS_VARIABLES.FLG == '5') {
                T000_02_GROUP_AMT_PROC_2();
                if (pgmRtn) return;
            } else {
                T000_02_GROUP_AMT_PROC();
                if (pgmRtn) return;
            }
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT = WS_DB_VARS.ALL_RELAT_AMT;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TAMT);
    }
    public void R000_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSQLDR.TXDD_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void T000_01_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_01_GROUP_REC_PROC_1() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_01_GROUP_REC_PROC_2() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_01_GROUP_REC_PROC_3() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_02_GROUP_AMT_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_02_GROUP_AMT_PROC_1() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCSQLDR.TXDD_AC;
        GDRPLDR.KEY.AC_SEQ = GDCSQLDR.TXAC_SEQ;
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_02_GROUP_AMT_PROC_2() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCSQLDR.TXDD_AC;
        GDRPLDR.KEY.AC_SEQ = GDCSQLDR.TXAC_SEQ;
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_02_GROUP_AMT_PROC_3() throws IOException,SQLException,Exception {
        GDRPLDR.DEAL_CD = GDCSQLDR.CTA_NO;
        GDRPLDR.BSREF = GDCSQLDR.REF_NO;
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        WS_COND_FLG.PLDR_FLG = 'N';
        CEP.TRC(SCCGWA, "11111111");
        GDRPLDR.KEY.RSEQ = GDCSQLDR.TXRSEQ;
        GDRPLDR.RELAT_STS = 'N';
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_BR.rp.order = "AC_TYP,CRT_DATE,RELAT_TIME";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.PLDR_FLG = 'Y';
        } else {
            WS_COND_FLG.PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_IA_ACR_RCD_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.RSEQ = GDCSQLDR.TXRSEQ;
        GDRPLDR.KEY.AC = GDCSQLDR.TXDD_AC;
        GDRPLDR.KEY.AC_SEQ = GDCSQLDR.TXAC_SEQ;
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.READ(SCCGWA, GDRPLDR, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NOTFND");
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_TDTSMST_01() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_GDTPLDR_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        WS_COND_FLG.PLDR_FLG = 'N';
        CEP.TRC(SCCGWA, "11111111");
        GDRPLDR.KEY.AC = GDCSQLDR.TXDD_AC;
        GDRPLDR.KEY.AC_SEQ = GDCSQLDR.TXAC_SEQ;
        GDRPLDR.RELAT_STS = 'N';
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_BR.rp.order = "CRT_DATE,RELAT_TIME";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_STARTBR_GDTPLDR_2() throws IOException,SQLException,Exception {
        WS_COND_FLG.PLDR_FLG = 'N';
        IBS.init(SCCGWA, GDRPLDR);
        CEP.TRC(SCCGWA, "22222222");
        GDRPLDR.DEAL_CD = GDCSQLDR.CTA_NO;
        GDRPLDR.BSREF = GDCSQLDR.REF_NO;
        GDRPLDR.RELAT_STS = 'N';
        CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
        CEP.TRC(SCCGWA, GDRPLDR.BSREF);
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_BR.rp.order = "AC_TYP,CRT_DATE,RELAT_TIME";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_GDTPLDR_3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        WS_COND_FLG.PLDR_FLG = 'N';
        CEP.TRC(SCCGWA, "33333333");
        GDRPLDR.KEY.AC = GDCSQLDR.TXDD_AC;
        GDRPLDR.KEY.AC_SEQ = 0;
        GDRPLDR.RELAT_STS = 'N';
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_BR.rp.order = "CRT_DATE,RELAT_TIME";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
