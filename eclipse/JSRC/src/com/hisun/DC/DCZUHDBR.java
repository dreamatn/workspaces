package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUHDBR {
    DCZUHDBR_WS_OUT_INFO WS_OUT_INFO;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DCTCTCDC_RD;
    DBParm DCTCITCD_RD;
    brParm DCTCDDAT_BR = new brParm();
    boolean pgmRtn = false;
    short PAGE_ROW = 25;
    int MAX_ROW = 99;
    int COL_CNT = 56;
    DCZUHDBR_WS_VARIABLES WS_VARIABLES = new DCZUHDBR_WS_VARIABLES();
    DCZUHDBR_WS_DATA WS_DATA = new DCZUHDBR_WS_DATA();
    DCZUHDBR_WS_OUT_RECODE WS_OUT_RECODE = new DCZUHDBR_WS_OUT_RECODE();
    DCZUHDBR_WS_COND_FLG WS_COND_FLG = new DCZUHDBR_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAACR DCRIAACR = new DCRIAACR();
    CICCUST CICCUST = new CICCUST();
    CICSSCH CICSSCH = new CICSSCH();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICKIDD CICKIDD = new CICKIDD();
    DCRCITCD DCRCITCD = new DCRCITCD();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    CICQCHDC CICQCHDC = new CICQCHDC();
    CICQCHDC_WS_DB_VARS WS_DB_VARS = new CICQCHDC_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCUHDBR DCCUHDBR;
    public void MP(SCCGWA SCCGWA, DCCUHDBR DCCUHDBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUHDBR = DCCUHDBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUHDBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DATA);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B001_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCUHDBR.QUERY_TYP == '1') {
            B050_BROWSE_INFO_FILE();
            if (pgmRtn) return;
        } else if (DCCUHDBR.QUERY_TYP == '2') {
            B020_CARDHOLDER_PROCESS();
            if (pgmRtn) return;
            B050_BROWSE_INFO_FILE();
            if (pgmRtn) return;
        } else if (DCCUHDBR.QUERY_TYP == '3') {
            B030_CARDOWNER_PROCESS();
            if (pgmRtn) return;
            B050_BROWSE_INFO_FILE();
            if (pgmRtn) return;
        } else if (DCCUHDBR.QUERY_TYP == '5') {
            B060_E_CARD_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUHDBR.QUERY_TYP);
        CEP.TRC(SCCGWA, DCCUHDBR.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, DCCUHDBR.CARD_ADSC_TYP);
        CEP.TRC(SCCGWA, DCCUHDBR.CARD_HLDR_IDTYP);
        CEP.TRC(SCCGWA, DCCUHDBR.CARD_HLDR_CINM);
        CEP.TRC(SCCGWA, DCCUHDBR.CARD_HLDR_IDNO);
        if (DCCUHDBR.QUERY_TYP == '1') {
            if (DCCUHDBR.CARD_HLDR_IDTYP.trim().length() == 0 
                || DCCUHDBR.CARD_HLDR_IDNO.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
            if (DCCUHDBR.CARD_HLDR_CINO.trim().length() == 0) {
                DCCUHDBR.CARD_HLDR_CINO = CICCUST.O_DATA.O_CI_NO;
            }
        } else if (DCCUHDBR.QUERY_TYP == '2') {
            if (DCCUHDBR.CARD_HLDR_CINO.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CINO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (DCCUHDBR.QUERY_TYP == '3') {
            if (DCCUHDBR.CARD_OWN_CINO.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CINO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (DCCUHDBR.QUERY_TYP == '5') {
            if (DCCUHDBR.E_CARD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_E_CARD);
            }
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHDBR.QUERY_TYP != '5') {
            if (DCCUHDBR.CARD_LNK_TYP == ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_LNK_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUHDBR.CARD_ADSC_TYP == ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CARDHOLDER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCCUHDBR.CARD_HLDR_CINO;
        WS_VARIABLES.CARD_HLDR_CINO0 = DCCUHDBR.CARD_HLDR_CINO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.CARD_HLDR_IDNO0 = CICCUST.O_DATA.O_ID_NO;
        WS_VARIABLES.CARD_HLDR_IDTYP0 = CICCUST.O_DATA.O_ID_TYPE;
        WS_VARIABLES.CARD_HLDR_CNM0 = CICCUST.O_DATA.O_CI_NM;
        WS_VARIABLES.CARD_HLDR_ENM0 = CICCUST.O_DATA.O_CI_ENM;
        CEP.TRC(SCCGWA, WS_VARIABLES.CARD_HLDR_IDNO0);
    }
    public void B030_CARDOWNER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCCUHDBR.CARD_OWN_CINO;
        WS_VARIABLES.CARD_OWN_CINO0 = DCCUHDBR.CARD_OWN_CINO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        WS_VARIABLES.CARD_OWN_IDTYP0 = CICCUST.O_DATA.O_ID_TYPE;
        WS_VARIABLES.CARD_OWN_IDNO0 = CICCUST.O_DATA.O_ID_NO;
        WS_VARIABLES.CARD_OWN_CNM0 = CICCUST.O_DATA.O_CI_NM;
        WS_VARIABLES.CARD_OWN_ENM0 = CICCUST.O_DATA.O_CI_ENM;
        CEP.TRC(SCCGWA, WS_VARIABLES.CARD_OWN_IDNO0);
    }
    public void B050_BROWSE_INFO_FILE() throws IOException,SQLException,Exception {
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B051_OUTPUT_TITLE();
            if (pgmRtn) return;
        } else {
            B054_OUTPUT_HEAD();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        if (DCCUHDBR.CARD_LNK_TYP != '0') {
            WS_DB_VARS.LNK_TYP_HI = DCCUHDBR.CARD_LNK_TYP;
            WS_DB_VARS.LNK_TYP_LOW = DCCUHDBR.CARD_LNK_TYP;
        } else {
            WS_DB_VARS.LNK_TYP_LOW = 0X00;
            WS_DB_VARS.LNK_TYP_HI = 0XFF;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
        if (DCCUHDBR.QUERY_TYP == '1') {
            DCRCDDAT.CARD_HLDR_CINO = DCCUHDBR.CARD_HLDR_CINO;
            DCRCDDAT.CARD_OWN_CINO = DCCUHDBR.CARD_HLDR_CINO;
            T004_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
        } else if (DCCUHDBR.QUERY_TYP == '2') {
            DCRCDDAT.CARD_HLDR_CINO = WS_VARIABLES.CARD_HLDR_CINO0;
            T002_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
        } else if (DCCUHDBR.QUERY_TYP == '3') {
            DCRCDDAT.CARD_OWN_CINO = DCCUHDBR.CARD_OWN_CINO;
            T003_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                B052_CHECK_CARDTYPE();
                if (pgmRtn) return;
                T000_READNEXT_DCTCDDAT();
                if (pgmRtn) return;
            }
        } else {
            T000_READNEXT_DCTCDDAT_W();
            if (pgmRtn) return;
            B061_PAGE_PROCESS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        T000_ENDBR_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "-----------------ENDBR--------------");
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_DATA.TOTAL_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DATA.TOTAL_NUM;
            WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_DATA.LAST_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_DATA.PAGE_ROW;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
            B056_OUTPUT_WRITE();
            if (pgmRtn) return;
        }
    }
    public void B060_E_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        CEP.TRC(SCCGWA, DCCUHDBR.E_CARD_NO);
        T000_READ_DCTCDDAT_EC();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B051_OUTPUT_TITLE();
            if (pgmRtn) return;
        } else {
            B054_OUTPUT_HEAD();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B053_OUTPUT_DETAIL();
            if (pgmRtn) return;
        } else {
            B055_WRITE_OUTPUT();
            if (pgmRtn) return;
        }
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_DATA.TOTAL_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_DATA.TOTAL_NUM;
            WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_DATA.LAST_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_DATA.PAGE_ROW;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
            B056_OUTPUT_WRITE();
            if (pgmRtn) return;
        }
    }
    public void B061_PAGE_PROCESS() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            WS_DATA.IDX = 0;
            WS_DATA.TOTAL_NUM = 0;
            while (WS_DATA.IDX != WS_DATA.PAGE_ROW 
                && WS_COND_FLG.TBL_FLAG != 'N') {
                B052_CHECK_CARDTYPE();
                if (pgmRtn) return;
                WS_DATA.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_DATA.NEXT_START_NUM;
                T000_READNEXT_DCTCDDAT_W();
                if (pgmRtn) return;
            }
            if (WS_COND_FLG.TBL_FLAG == 'N') {
                CEP.TRC(SCCGWA, "--------------T--------");
                WS_DATA.TOTAL_PAGE = WS_DATA.CURR_PAGE;
                WS_DATA.BAL_CNT = WS_DATA.IDX;
                WS_DATA.TOTAL_NUM = ( WS_DATA.CURR_PAGE - 1 ) * WS_DATA.PAGE_ROW + WS_DATA.BAL_CNT;
                WS_DATA.LAST_PAGE = 'Y';
                WS_DATA.PAGE_ROW = (short) WS_DATA.BAL_CNT;
                WS_OUT_INFO = new DCZUHDBR_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                CEP.TRC(SCCGWA, "--------------G--------");
                R000_GROUP_ALL();
                if (pgmRtn) return;
                WS_DATA.BAL_CNT = WS_DATA.TOTAL_NUM % WS_DATA.PAGE_ROW;
                WS_DATA.TOTAL_PAGE = (short) ((WS_DATA.TOTAL_NUM - WS_DATA.BAL_CNT) / WS_DATA.PAGE_ROW);
                if (WS_DATA.BAL_CNT != 0) {
                    WS_DATA.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_DATA.TOTAL_PAGE = 1;
            WS_DATA.TOTAL_NUM = 0;
            WS_DATA.LAST_PAGE = 'Y';
            WS_DATA.PAGE_ROW = 0;
            WS_OUT_INFO = new DCZUHDBR_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
    }
    public void B051_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 971;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B052_CHECK_CARDTYPE() throws IOException,SQLException,Exception {
        if (DCCUHDBR.CARD_ADSC_TYP == '0') {
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                B053_OUTPUT_DETAIL();
                if (pgmRtn) return;
            } else {
                B055_WRITE_OUTPUT();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCRPRDPR);
            IBS.init(SCCGWA, DCCUPRCD);
            DCCUPRCD.TX_TYPE = 'I';
            DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
            S000_CALL_DCZUPRCD();
            if (pgmRtn) return;
            if (DCCUPRCD.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, DCCUPRCD.RC);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
            }
            CEP.TRC(SCCGWA, DCRPRDPR);
            CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
            CEP.TRC(SCCGWA, DCCUHDBR.CARD_ADSC_TYP);
            if (DCCUHDBR.CARD_ADSC_TYP == '1' 
                && DCRPRDPR.DATA_TXT.ADSC_TYP == 'C') {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B053_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                } else {
                    B055_WRITE_OUTPUT();
                    if (pgmRtn) return;
                }
            }
            if (DCCUHDBR.CARD_ADSC_TYP == '2' 
                && DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    B053_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                } else {
                    B055_WRITE_OUTPUT();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B053_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_OUTPUT);
        WS_VARIABLES.WS_OUTPUT.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        IBS.init(SCCGWA, DCRCTCDC);
        DCRCTCDC.NEW_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        T000_READ_DCTCTCDC();
        if (pgmRtn) return;
        if ((WS_COND_FLG.TBL_FLAG == 'Y' 
            && DCRCDDAT.CARD_STS != 'N') 
            || (!DCRCDDAT.CARD_OWN_CINO.equalsIgnoreCase(DCRCDDAT.CARD_HLDR_CINO)) 
            || (DCRCDDAT.CARD_STS == 'C')) {
        } else {
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = WS_VARIABLES.WS_OUTPUT.CARD_NO;
            DDCIQBAL.DATA.CCY = "156";
            DDCIQBAL.DATA.CCY_TYPE = '1';
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            WS_VARIABLES.WS_OUTPUT.BALANCE_AMT = DDCIQBAL.DATA.AVL_BAL;
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.BALANCE_AMT);
        }
        WS_VARIABLES.WS_OUTPUT.CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        WS_VARIABLES.WS_OUTPUT.PROD_CD = DCRCDDAT.PROD_CD;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.PROD_CD);
        if (DCCUHDBR.QUERY_TYP != '3') {
            WS_VARIABLES.WS_OUTPUT.CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
            if (DCRCDDAT.CARD_OWN_CINO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'C';
                CICCUST.DATA.CI_NO = DCRCDDAT.CARD_OWN_CINO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.RC.RC_CODE != 0) {
                    WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_OUTPUT.CARD_OWN_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
                WS_VARIABLES.WS_OUTPUT.CARD_OWN_IDNO = CICCUST.O_DATA.O_ID_NO;
                WS_VARIABLES.WS_OUTPUT.CARD_OWN_CNM = CICCUST.O_DATA.O_CI_NM;
            }
        } else {
            WS_VARIABLES.WS_OUTPUT.CARD_OWN_CINO = WS_VARIABLES.CARD_OWN_CINO0;
            WS_VARIABLES.WS_OUTPUT.CARD_OWN_IDTYP = WS_VARIABLES.CARD_OWN_IDTYP0;
            WS_VARIABLES.WS_OUTPUT.CARD_OWN_IDNO = WS_VARIABLES.CARD_OWN_IDNO0;
            WS_VARIABLES.WS_OUTPUT.CARD_OWN_CNM = WS_VARIABLES.CARD_OWN_CNM0;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_OWN_CINO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_OWN_IDTYP);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_OWN_IDNO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_OWN_CNM);
        if (DCCUHDBR.QUERY_TYP != '2') {
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
            WS_VARIABLES.WS_OUTPUT.CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
            if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'C';
                CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.RC.RC_CODE != 0) {
                    WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_VARIABLES.WS_OUTPUT.CARD_HLDR_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
                WS_VARIABLES.WS_OUTPUT.CARD_HLDR_IDNO = CICCUST.O_DATA.O_ID_NO;
                WS_VARIABLES.WS_OUTPUT.CARD_HLDR_CNM = CICCUST.O_DATA.O_CI_NM;
            }
        } else {
            WS_VARIABLES.WS_OUTPUT.CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
            WS_VARIABLES.WS_OUTPUT.CARD_HLDR_IDTYP = WS_VARIABLES.CARD_HLDR_IDTYP0;
            WS_VARIABLES.WS_OUTPUT.CARD_HLDR_IDNO = WS_VARIABLES.CARD_HLDR_IDNO0;
            WS_VARIABLES.WS_OUTPUT.CARD_HLDR_CNM = WS_VARIABLES.CARD_HLDR_CNM0;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_HLDR_CINO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_HLDR_IDTYP);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_HLDR_IDNO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CARD_HLDR_CNM);
        WS_VARIABLES.WS_OUTPUT.CARD_MEDI = DCRCDDAT.CARD_MEDI;
        WS_VARIABLES.WS_OUTPUT.CARD_STS = DCRCDDAT.CARD_STS;
        WS_VARIABLES.WS_OUTPUT.CARD_STSW = DCRCDDAT.CARD_STSW;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_OUTPUT.CRD_LST_SW_FLG = 'Y';
        } else {
            WS_VARIABLES.WS_OUTPUT.CRD_LST_SW_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.CRD_LST_SW_FLG);
        WS_VARIABLES.WS_OUTPUT.ACNO_TYPE = DCRCDDAT.ACNO_TYPE;
        WS_VARIABLES.WS_OUTPUT.EXC_CARD_TMS = DCRCDDAT.EXC_CARD_TMS;
        WS_VARIABLES.WS_OUTPUT.CARD_CLS_PROD = DCRCDDAT.CARD_CLS_PROD;
        WS_VARIABLES.WS_OUTPUT.PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
        WS_VARIABLES.WS_OUTPUT.HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        WS_VARIABLES.WS_OUTPUT.PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        WS_VARIABLES.WS_OUTPUT.SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        WS_VARIABLES.WS_OUTPUT.DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        WS_VARIABLES.WS_OUTPUT.DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        WS_VARIABLES.WS_OUTPUT.ISSU_BR = DCRCDDAT.ISSU_BR;
        WS_VARIABLES.WS_OUTPUT.CLT_BR = DCRCDDAT.CLT_BR;
        WS_VARIABLES.WS_OUTPUT.EMBS_DT = DCRCDDAT.EMBS_DT;
        WS_VARIABLES.WS_OUTPUT.ISSU_DT = DCRCDDAT.ISSU_DT;
        WS_VARIABLES.WS_OUTPUT.EXP_DT = DCRCDDAT.EXP_DT;
        WS_VARIABLES.WS_OUTPUT.CLO_DT = DCRCDDAT.CLO_DT;
        WS_VARIABLES.WS_OUTPUT.DOUBLE_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        WS_VARIABLES.WS_OUTPUT.JOIN_CUS_FLG = DCRCDDAT.JOIN_CUS_FLG;
        WS_VARIABLES.WS_OUTPUT.ANNUAL_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_OUTPUT.ANNUAL_FEE_FREE = 'Y';
        }
        WS_VARIABLES.WS_OUTPUT.PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT;
        WS_VARIABLES.WS_OUTPUT.LAST_TXN_DT = DCRCDDAT.LAST_TXN_DT;
        WS_VARIABLES.WS_OUTPUT.UPD_DT = DCRCDDAT.UPDTBL_DATE;
        WS_VARIABLES.WS_OUTPUT.UPD_TLR = DCRCDDAT.UPDTBL_TLR;
        WS_VARIABLES.WS_OUTPUT.E_CARD_NO = DCRCDDAT.E_CARD_NO;
        IBS.init(SCCGWA, CICQCHDC);
        CICQCHDC.DATA.N_AGR_NO = WS_VARIABLES.WS_OUTPUT.CARD_NO;
        CICQCHDC.FUNC = 'O';
        CICQCHDC.DATA.N_ENTY_TYP = '2';
        S000_CALL_CIZQCHDC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_OUTPUT.OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT.OLD_CARD_NO);
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_OUTPUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_OUTPUT);
        SCCMPAG.DATA_LEN = 971;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B054_OUTPUT_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        CEP.TRC(SCCGWA, DCCUHDBR.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCUHDBR.PAGE_ROW);
        if (DCCUHDBR.PAGE_NUM == 0) {
            WS_DATA.CURR_PAGE = 1;
        } else {
            WS_DATA.CURR_PAGE = (short) DCCUHDBR.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_DATA.CURR_PAGE;
        WS_DATA.LAST_PAGE = 'N';
        if (DCCUHDBR.PAGE_ROW == 0) {
            WS_DATA.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new DCZUHDBR_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (DCCUHDBR.PAGE_ROW > PAGE_ROW) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_DATA.PAGE_ROW = (short) DCCUHDBR.PAGE_ROW;
                WS_OUT_INFO = new DCZUHDBR_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        CEP.TRC(SCCGWA, WS_DATA.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_DATA.PAGE_ROW);
        WS_DATA.NEXT_START_NUM = ( ( WS_DATA.CURR_PAGE - 1 ) * WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_DATA.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
    }
    public void B055_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_DATA.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.O_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        WS_OUT_INFO.O_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        if (DCCUHDBR.QUERY_TYP != '2') {
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
            WS_OUT_INFO.O_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
            if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'C';
                CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.RC.RC_CODE != 0) {
                    WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_OUT_INFO.O_HLDR_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
                WS_OUT_INFO.O_HLDR_IDNO = CICCUST.O_DATA.O_ID_NO;
                WS_OUT_INFO.O_HLDR_CNM = CICCUST.O_DATA.O_CI_NM;
            }
        } else {
            WS_OUT_INFO.O_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
            WS_OUT_INFO.O_HLDR_IDTYP = WS_VARIABLES.CARD_HLDR_IDTYP0;
            WS_OUT_INFO.O_HLDR_IDNO = WS_VARIABLES.CARD_HLDR_IDNO0;
            WS_OUT_INFO.O_HLDR_CNM = WS_VARIABLES.CARD_HLDR_CNM0;
        }
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_HLDR_CINO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_HLDR_IDTYP);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_HLDR_IDNO);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_HLDR_CNM);
        WS_OUT_INFO.O_CARD_MEDI = DCRCDDAT.CARD_MEDI;
        WS_OUT_INFO.O_CARD_STS = DCRCDDAT.CARD_STS;
        WS_OUT_INFO.O_CARD_STSW = DCRCDDAT.CARD_STSW;
        if (WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW == null) WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW = "";
        JIBS_tmp_int = WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW += " ";
        if (WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW == null) WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW = "";
        JIBS_tmp_int = WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW += " ";
        if (WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW == null) WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW = "";
        JIBS_tmp_int = WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW += " ";
        if ((WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) 
            || (WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
            || (WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_OUT_INFO.O_LST_SW_FLG = 'Y';
        } else {
            WS_OUT_INFO.O_LST_SW_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1).O_LST_SW_FLG);
        WS_OUT_INFO.O_ACNO_TYPE = DCRCDDAT.ACNO_TYPE;
        WS_OUT_INFO.O_EXC_CARD_TMS = DCRCDDAT.EXC_CARD_TMS;
        WS_OUT_INFO.O_PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
        WS_OUT_INFO.O_HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        WS_OUT_INFO.O_PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        WS_OUT_INFO.O_S_N_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        WS_OUT_INFO.O_D_N_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        WS_OUT_INFO.O_DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        WS_OUT_INFO.O_DOUBLE_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        WS_OUT_INFO.O_JOIN_CUS_FLG = DCRCDDAT.JOIN_CUS_FLG;
        WS_OUT_INFO.O_ANNUAL_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUT_INFO.O_ANNUAL_FEE_FREE = 'Y';
        }
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
    }
    public void B056_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DC312";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 11469;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        if (DCCUHDBR.CARD_LNK_TYP != '0') {
            WS_DB_VARS.LNK_TYP_HI = DCCUHDBR.CARD_LNK_TYP;
            WS_DB_VARS.LNK_TYP_LOW = DCCUHDBR.CARD_LNK_TYP;
        } else {
            WS_DB_VARS.LNK_TYP_LOW = 0X00;
            WS_DB_VARS.LNK_TYP_HI = 0XFF;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
        if (DCCUHDBR.QUERY_TYP == '1' 
            && WS_VARIABLES.CARD_HLDR_CINO0.trim().length() == 0) {
            WS_DB_VARS.CDDAT_HOLDER_IDTYPE = DCCUHDBR.CARD_HLDR_IDTYP;
            WS_DB_VARS.CDDAT_HOLDER_IDNO = DCCUHDBR.CARD_HLDR_IDNO;
            CEP.TRC(SCCGWA, WS_DB_VARS.CDDAT_HOLDER_IDTYPE);
            CEP.TRC(SCCGWA, WS_DB_VARS.CDDAT_HOLDER_IDNO);
            R000_GROUP_1();
            if (pgmRtn) return;
        }
        if (DCCUHDBR.QUERY_TYP == '2' 
            || WS_VARIABLES.CARD_HLDR_CINO0.trim().length() > 0) {
            DCRCDDAT.CARD_HLDR_CINO = DCCUHDBR.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
            R000_GROUP_2();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
        if (DCCUHDBR.QUERY_TYP == '3') {
            DCRCDDAT.CARD_OWN_CINO = DCCUHDBR.CARD_OWN_CINO;
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
            R000_GROUP_3();
            if (pgmRtn) return;
        }
    }
    public void R000_GROUP_1() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-NUM=COUNT(*)";
        DCTCDDAT_RD.where = "( HOLDER_IDTYPE = :WS_DB_VARS.CDDAT_HOLDER_IDTYPE ) "
            + "AND ( HOLDER_IDNO = :WS_DB_VARS.CDDAT_HOLDER_IDNO ) "
            + "AND ( CARD_LNK_TYP >= :WS_DB_VARS.LNK_TYP_LOW ) "
            + "AND ( CARD_LNK_TYP <= :WS_DB_VARS.LNK_TYP_HI )";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        WS_DATA.TOTAL_NUM = WS_DB_VARS.NUM;
        CEP.TRC(SCCGWA, WS_DATA.TOTAL_NUM);
    }
    public void R000_GROUP_2() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-NUM=COUNT(*)";
        DCTCDDAT_RD.where = "( CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO ) "
            + "AND ( CARD_LNK_TYP >= :WS_DB_VARS.LNK_TYP_LOW ) "
            + "AND ( CARD_LNK_TYP <= :WS_DB_VARS.LNK_TYP_HI )";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        WS_DATA.TOTAL_NUM = WS_DB_VARS.NUM;
        CEP.TRC(SCCGWA, WS_DATA.TOTAL_NUM);
    }
    public void R000_GROUP_3() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-NUM=COUNT(*)";
        DCTCDDAT_RD.where = "( CARD_OWN_CINO = :DCRCDDAT.CARD_OWN_CINO ) "
            + "AND ( CARD_LNK_TYP >= :WS_DB_VARS.LNK_TYP_LOW ) "
            + "AND ( CARD_LNK_TYP <= :WS_DB_VARS.LNK_TYP_HI )";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        WS_DATA.TOTAL_NUM = WS_DB_VARS.NUM;
        CEP.TRC(SCCGWA, WS_DATA.TOTAL_NUM);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.where = "NEW_CARD_NO = :DCRCTCDC.NEW_CARD_NO";
        IBS.READ(SCCGWA, DCRCTCDC, this, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT_EC() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "E_CARD_NO = :DCCUHDBR.E_CARD_NO";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_EXIST);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_SYS_ERR);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.col = "CARD_NO";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T002_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "( CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO ) "
            + "AND ( CARD_LNK_TYP >= :WS_DB_VARS.LNK_TYP_LOW ) "
            + "AND ( CARD_LNK_TYP <= :WS_DB_VARS.LNK_TYP_HI )";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T003_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "( CARD_OWN_CINO = :DCRCDDAT.CARD_OWN_CINO ) "
            + "AND ( CARD_LNK_TYP >= :WS_DB_VARS.LNK_TYP_LOW ) "
            + "AND ( CARD_LNK_TYP <= :WS_DB_VARS.LNK_TYP_HI )";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T004_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "( CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO ) "
            + "AND ( CARD_LNK_TYP >= :WS_DB_VARS.LNK_TYP_LOW ) "
            + "AND ( CARD_LNK_TYP <= :WS_DB_VARS.LNK_TYP_HI )";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTCDDAT_W() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_PROD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI", CICSSCH);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
        }
    }
    public void S000_CALL_CIZKIDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHK-ID", CICKIDD);
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
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
