package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCDLT {
    int JIBS_tmp_int;
    DCZSCDLT_WS_OUT_INFO WS_OUT_INFO;
    DBParm DCTCDDAT_RD;
    DBParm DCTPRDLT_RD;
    DBParm DCTCRDLT_RD;
    brParm DCTCRDLT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_9 = "DC059";
    int MAX_COL = 99;
    int MAX_ROW = 50;
    int COL_CNT = 18;
    String HIS_REMARK = "CARD PRODUCT TRANS LIMIT INFO. MAINTAIN";
    String HIS_COPYBOOK = "DCRCRDLT";
    String TBL_CRDLT = "DCTCRDLT";
    String TBL_PRDLT = "DCTPRDLT";
    short PAGE_ROW = 25;
    DCZSCDLT_WS_VARIABLES WS_VARIABLES = new DCZSCDLT_WS_VARIABLES();
    DCZSCDLT_WS_DATA WS_DATA = new DCZSCDLT_WS_DATA();
    DCZSCDLT_WS_OUT_RECODE WS_OUT_RECODE = new DCZSCDLT_WS_OUT_RECODE();
    DCZSCDLT_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DCZSCDLT_WS_SQL_VARIABLES();
    DCZSCDLT_WS_SQL_VARIABLES_OTHER WS_SQL_VARIABLES_OTHER = new DCZSCDLT_WS_SQL_VARIABLES_OTHER();
    DCZSCDLT_WS_DB_VARS WS_DB_VARS = new DCZSCDLT_WS_DB_VARS();
    DCZSCDLT_WS_COND_FLG WS_COND_FLG = new DCZSCDLT_WS_COND_FLG();
    DCZSCDLT_WS8 WS8 = new DCZSCDLT_WS8();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMAGT CICMAGT = new CICMAGT();
    CICPDTL CICPDTL = new CICPDTL();
    DCRPRDLT DCRPRDLT = new DCRPRDLT();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    DCCUCDLP_WS_SCDLT_OUT WS_SCDLT_OUT = new DCCUCDLP_WS_SCDLT_OUT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCS1091 DCCS1091;
    public void MP(SCCGWA SCCGWA, DCCS1091 DCCS1091) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS1091 = DCCS1091;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCDLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS8);
        IBS.init(SCCGWA, DCRCRDLT);
        IBS.init(SCCGWA, DCRCRDLT);
        IBS.init(SCCGWA, WS_DATA);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, WS_SCDLT_OUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCS1091.FUNC == '4') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == 'A') {
            B002_CHECK_INPUT();
            if (pgmRtn) return;
            B100_MATN_ADD_PROC();
            if (pgmRtn) return;
            B200_SIGN_ADD_PROC();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == 'D') {
            B300_MATN_DEL_PROC();
            if (pgmRtn) return;
            B400_SIGN_DEL_PROC();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == 'U') {
            B002_CHECK_INPUT();
            if (pgmRtn) return;
            B500_MATN_MOD_PROC();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == 'Q') {
            B600_QUERY_PROC();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == '1') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == 'C') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B020_CREATE2_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == '3') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == '2') {
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B071_SIGN_MAIN_DEL();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1091.FUNC == 'B') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B002_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DC1");
        if (DCCUCINF.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STS != 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ALREADY_LOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_CVN_ERR_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 14);
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DC1");
        if (DCCUCINF.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STS != 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ALREADY_LOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_CVN_ERR_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCS1091.VAL.TXN_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.DLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.MLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.SYY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.YLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.SE_LMT_AMT);
        if (DCCS1091.VAL.TXN_LMT_AMT == 99999999999999.99 
            && DCCS1091.VAL.DLY_LMT_AMT == 99999999999999.99 
            && DCCS1091.VAL.MLY_LMT_AMT == 99999999999999.99 
            && DCCS1091.VAL.SYY_LMT_AMT == 99999999999999.99 
            && DCCS1091.VAL.YLY_LMT_AMT == 99999999999999.99 
            && DCCS1091.VAL.SE_LMT_AMT == 99999999999999.99) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ONE_LMT);
        }
        if (DCCS1091.VAL.TXN_LMT_AMT == 0 
            && DCCS1091.VAL.DLY_LMT_AMT == 0 
            && DCCS1091.VAL.MLY_LMT_AMT == 0 
            && DCCS1091.VAL.SYY_LMT_AMT == 0 
            && DCCS1091.VAL.YLY_LMT_AMT == 0 
            && DCCS1091.VAL.SE_LMT_AMT == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_ONE_LMT);
        }
        CEP.TRC(SCCGWA, "DC2");
        if ((DCCS1091.VAL.TXN_LMT_AMT > DCCS1091.VAL.DLY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_TX_LMT_GT_DAILY_LMT;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 19);
        }
        CEP.TRC(SCCGWA, "DC3");
        if ((DCCS1091.VAL.DLY_LMT_AMT > DCCS1091.VAL.MLY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_DAILY_GT_MONTHLY;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 20);
        }
        CEP.TRC(SCCGWA, "DC4");
        if ((DCCS1091.VAL.MLY_LMT_AMT > DCCS1091.VAL.SYY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MONTHLY_GT_SEMIYEAR;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 22);
        }
        CEP.TRC(SCCGWA, "DC5");
        if ((DCCS1091.VAL.SYY_LMT_AMT > DCCS1091.VAL.YLY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_SEMIYEAR_GT_YEAR;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 24);
        }
        CEP.TRC(SCCGWA, "DC6");
        IBS.init(SCCGWA, DCRPRDLT);
        DCRPRDLT.KEY.PROD_CD = DCCUCINF.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        DCRPRDLT.KEY.AC_CLASS = DCCUCINF.AC_TYP;
        if (DCCUCINF.ADSC_TYPE == 'C') {
            DCRPRDLT.KEY.AC_CLASS = '0';
        }
        CEP.TRC(SCCGWA, "--------PRDLT-PROD-CD---");
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.LMT_CCY);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.AC_CLASS);
        T000_STARTBR_DCTPRDLT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, "GWA-DBIO-FLG 0");
            CEP.TRC(SCCGWA, DCRPRDLT.TXN_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.DLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.DLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCRPRDLT.MLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.SYY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.YLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.SE_LMT_AMT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.TXN_LMT_AMT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.DLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.DLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCCS1091.VAL.MLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.MLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCCS1091.VAL.SYY_LMT_AMT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.YLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.SE_LMT_AMT);
            if (DCCS1091.FUNC == '1' 
                || DCCS1091.FUNC == '3') {
                CEP.TRC(SCCGWA, "PRD-TXN-AMT");
                if ((DCCS1091.VAL.TXN_LMT_AMT > DCRPRDLT.TXN_LMT_AMT)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_TXN_LMT;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 19);
                    }
                }
                CEP.TRC(SCCGWA, "PRD-DXN-AMT");
                if ((DCCS1091.VAL.DLY_LMT_AMT > DCRPRDLT.DLY_LMT_AMT)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_DLY_LMT;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 20);
                    }
                }
                CEP.TRC(SCCGWA, "PRD-DLY-VOL");
                if ((DCCS1091.VAL.DLY_LMT_VOL > DCRPRDLT.DLY_LMT_VOL)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_DLY_VOL;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 21);
                    }
                }
                CEP.TRC(SCCGWA, "PRD-MLY-AMT");
                if ((DCCS1091.VAL.MLY_LMT_AMT > DCRPRDLT.MLY_LMT_AMT)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_MLY_AMT;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 22);
                    }
                }
                CEP.TRC(SCCGWA, "PRD-MLY-VOL");
                if ((DCCS1091.VAL.MLY_LMT_VOL > DCRPRDLT.MLY_LMT_VOL)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_MLY_VOL;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 23);
                    }
                }
                CEP.TRC(SCCGWA, "PRD-SYY-AMT");
                if ((DCCS1091.VAL.SYY_LMT_AMT > DCRPRDLT.SYY_LMT_AMT)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_SYY_LMT;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 24);
                    }
                }
                CEP.TRC(SCCGWA, "PRD-YLY-AMT");
                if ((DCCS1091.VAL.YLY_LMT_AMT > DCRPRDLT.YLY_LMT_AMT)) {
                    if (DCCS1091.FUNC == '3' 
                        && DCCS1091.VAL.SE_LMT_AMT != 99999999999999.99) {
                    } else {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_YLY_LMT;
                        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 25);
                    }
                }
            }
            if (DCCS1091.FUNC == '3' 
                || DCCS1091.FUNC == 'C') {
                CEP.TRC(SCCGWA, "PRD-SE-AMT");
                if ((DCCS1091.VAL.SE_LMT_AMT > DCRPRDLT.SE_LMT_AMT)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PROD_SE_LMT;
                    CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 26);
                }
            }
        }
        CEP.TRC(SCCGWA, "GWA-DBIO-FLG 2");
        CEP.TRC(SCCGWA, DCCS1091.VAL.SE_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.STA_DT);
        CEP.TRC(SCCGWA, DCCS1091.VAL.END_DT);
        if (DCCS1091.FUNC == 'C' 
            || DCCS1091.FUNC == '3') {
            CEP.TRC(SCCGWA, DCCS1091.VAL.STA_DT);
            CEP.TRC(SCCGWA, DCCS1091.VAL.END_DT);
            if (DCCS1091.VAL.STA_DT == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_START_DATE_IS_ZERO;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 27);
            }
            if (DCCS1091.VAL.END_DT == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_END_DATE_IS_ZERO;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 28);
            }
            if (DCCS1091.VAL.STA_DT < SCCGWA.COMM_AREA.AC_DATE 
                && DCCS1091.VAL.STA_DT != WS_SQL_VARIABLES_OTHER.CLIM_DFUS_DT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_S_DATE_LESS_AC_DATE;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 27);
            }
            if (DCCS1091.VAL.END_DT > WS_SQL_VARIABLES_OTHER.CLIM_DFUE_DT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_END_DATE_IS_99991231;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 28);
            }
            CEP.TRC(SCCGWA, "GWA-DBIO-FLG 4");
            if (DCCS1091.VAL.STA_DT >= DCCS1091.VAL.END_DT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_S_TIME_GT_D_TIME;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 28);
            }
        }
        CEP.TRC(SCCGWA, "GWA-DBIO-FLG 5");
        CEP.TRC(SCCGWA, DCCS1091.VAL.DLY_LMT_VOL);
        CEP.TRC(SCCGWA, DCCS1091.VAL.MLY_LMT_VOL);
        if ((DCCS1091.VAL.DLY_LMT_VOL > DCCS1091.VAL.MLY_LMT_VOL)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DAYT_GT_MONT_LMT;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 22);
        }
    }
    public void B100_MATN_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '0';
        CICPDTL.TYPE = "02";
        CICPDTL.LC_OBJ = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.CUS_AC = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.TX_TYPE = "01";
        CICPDTL.LVL = 33;
        CICPDTL.LC_CCY = "156";
        CICPDTL.DAY_STA = SCCGWA.COMM_AREA.AC_DATE;
        CICPDTL.DAY_END = 99991231;
        CICPDTL.TXN_AMT = DCCS1091.VAL.TXN_LMT_AMT;
        CICPDTL.DLY_AMT = DCCS1091.VAL.DLY_LMT_AMT;
        CICPDTL.DLY_VOL = DCCS1091.VAL.DLY_LMT_VOL;
        CICPDTL.MLY_AMT = DCCS1091.VAL.MLY_LMT_AMT;
        CICPDTL.MLY_VOL = DCCS1091.VAL.MLY_LMT_VOL;
        CICPDTL.SYY_AMT = DCCS1091.VAL.SYY_LMT_AMT;
        CICPDTL.YLY_AMT = DCCS1091.VAL.YLY_LMT_AMT;
        CICPDTL.TM_AMT = DCCS1091.VAL.SE_LMT_AMT;
        CICPDTL.SE_AMT = DCCS1091.VAL.SE_LMT_AMT;
        CICPDTL.LMT_AMT1 = 99999999999999.99;
        CICPDTL.LMT_AMT2 = 99999999999999.99;
        CICPDTL.LMT_AMT3 = 99999999999999.99;
        CICPDTL.LMT_AMT4 = 99999999999999.99;
        CICPDTL.BAL_AMT = 99999999999999.99;
        CICPDTL.LMT_STSW = "EEEEEEEEEEEEEEE";
        CICPDTL.TIMES50[1-1].CON_TYP1 = 18;
        CICPDTL.TIMES50[1-1].VAL = "05";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void B200_SIGN_ADD_PROC() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.CHNL_NO.equalsIgnoreCase("10208")) {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'A';
            CICMAGT.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
            CICMAGT.DATA.AGT_TYP = "3001008";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCCS1091.VAL.KEY.CARD_NO;
            CICMAGT.DATA.FRM_APP = "DC";
            CICMAGT.DATA.AGT_LVL = 'A';
            CICMAGT.DATA.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.AGT_STS = 'N';
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
    }
    public void B300_MATN_DEL_PROC() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '2';
        CICPDTL.TYPE = "02";
        CICPDTL.LC_OBJ = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.CUS_AC = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.TX_TYPE = "01";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void B400_SIGN_DEL_PROC() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.CHNL_NO.equalsIgnoreCase("10208")) {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'D';
            CICMAGT.DATA.AGT_TYP = "3001008";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCCS1091.VAL.KEY.CARD_NO;
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
    }
    public void B500_MATN_MOD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '1';
        CICPDTL.TYPE = "02";
        CICPDTL.LC_OBJ = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.CUS_AC = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.TX_TYPE = "01";
        CICPDTL.LVL = 33;
        CICPDTL.LC_CCY = "156";
        CICPDTL.TXN_AMT = DCCS1091.VAL.TXN_LMT_AMT;
        CICPDTL.DLY_AMT = DCCS1091.VAL.DLY_LMT_AMT;
        CICPDTL.DLY_VOL = DCCS1091.VAL.DLY_LMT_VOL;
        CICPDTL.MLY_AMT = DCCS1091.VAL.MLY_LMT_AMT;
        CICPDTL.MLY_VOL = DCCS1091.VAL.MLY_LMT_VOL;
        CICPDTL.SYY_AMT = DCCS1091.VAL.SYY_LMT_AMT;
        CICPDTL.YLY_AMT = DCCS1091.VAL.YLY_LMT_AMT;
        CICPDTL.TM_AMT = DCCS1091.VAL.SE_LMT_AMT;
        CICPDTL.SE_AMT = DCCS1091.VAL.SE_LMT_AMT;
        CICPDTL.LMT_AMT1 = 99999999999999.99;
        CICPDTL.LMT_AMT2 = 99999999999999.99;
        CICPDTL.LMT_AMT3 = 99999999999999.99;
        CICPDTL.LMT_AMT4 = 99999999999999.99;
        CICPDTL.BAL_AMT = 99999999999999.99;
        CICPDTL.LMT_STSW = "EEEEEEEEEEEEEEE";
        CICPDTL.TIMES50[1-1].CON_TYP1 = 18;
        CICPDTL.TIMES50[1-1].VAL = "05";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void B600_QUERY_PROC() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '3';
        CICPDTL.TYPE = "02";
        CICPDTL.LC_OBJ = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.CUS_AC = DCCS1091.VAL.KEY.CARD_NO;
        CICPDTL.TX_TYPE = "01";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void B002_CHECK_CARD_NO() throws IOException,SQLException,Exception {
        if (DCCS1091.FUNC == '2') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_LNK_TYP != '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_SUP_CARD_NOT_PERMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DCCUCDLP.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        CEP.TRC(SCCGWA, DCCS1091.VAL.STA_DT);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY);
        T000_READ_DCTCRDLT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COND_FLG);
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_NO_CARD_LIMT);
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            if (DCCS1091.VAL.KEY.TXN_TYPE.equalsIgnoreCase("05") 
                && DCCUCINF.DOUBLE_FREE_FLG != 'Y') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_OPEN_XESM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCCS1091.VAL.KEY.TXN_TYPE.equalsIgnoreCase("05") 
                && DCCUCINF.DOUBLE_FREE_FLG != 'Y') {
                B025_OPEN_DOUBLE_FREE();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        T000_READ_DCTCRDLT_UPD();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS8.HIS_FLG = 'A';
            CEP.TRC(SCCGWA, DCCS1091.VAL);
            DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
            DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
            DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
            DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
            DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
            DCRCRDLT.TXN_LMT_AMT = DCCS1091.VAL.TXN_LMT_AMT;
            DCRCRDLT.DLY_LMT_AMT = DCCS1091.VAL.DLY_LMT_AMT;
            DCRCRDLT.DLY_LMT_VOL = DCCS1091.VAL.DLY_LMT_VOL;
            DCRCRDLT.MLY_LMT_AMT = DCCS1091.VAL.MLY_LMT_AMT;
            DCRCRDLT.MLY_LMT_VOL = DCCS1091.VAL.MLY_LMT_VOL;
            CEP.TRC(SCCGWA, DCCS1091.VAL.MLY_LMT_AMT);
            DCRCRDLT.SYY_LMT_AMT = DCCS1091.VAL.SYY_LMT_AMT;
            CEP.TRC(SCCGWA, DCCS1091.VAL.YLY_LMT_AMT);
            DCRCRDLT.YLY_LMT_AMT = DCCS1091.VAL.YLY_LMT_AMT;
            DCRCRDLT.SE_LMT_AMT = 99999999999999.99;
            DCRCRDLT.STA_DT = 10101;
            DCRCRDLT.END_DT = 99991231;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, "L");
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, "M");
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            if (WS_COND_FLG.TBL_FLAG == 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B070_SIGN_MAIN_ADD();
            if (pgmRtn) return;
        } else {
            WS8.HIS_FLG = 'M';
            if (DCRCRDLT.SE_LMT_AMT == 99999999999999.99) {
                CEP.ERR(SCCGWA, ERROR_MSG.DC_GE_LMT_ALR_EXIST);
            } else {
                DCRCRDLT.TXN_LMT_AMT = DCCS1091.VAL.TXN_LMT_AMT;
                DCRCRDLT.DLY_LMT_AMT = DCCS1091.VAL.DLY_LMT_AMT;
                DCRCRDLT.DLY_LMT_VOL = DCCS1091.VAL.DLY_LMT_VOL;
                DCRCRDLT.MLY_LMT_AMT = DCCS1091.VAL.MLY_LMT_AMT;
                DCRCRDLT.MLY_LMT_VOL = DCCS1091.VAL.MLY_LMT_VOL;
                CEP.TRC(SCCGWA, DCCS1091.VAL.MLY_LMT_AMT);
                DCRCRDLT.SYY_LMT_AMT = DCCS1091.VAL.SYY_LMT_AMT;
                CEP.TRC(SCCGWA, DCCS1091.VAL.YLY_LMT_AMT);
                DCRCRDLT.YLY_LMT_AMT = DCCS1091.VAL.YLY_LMT_AMT;
                DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, "L");
                DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                CEP.TRC(SCCGWA, "M");
                T000_REWRITE_DCTCRDLT();
                if (pgmRtn) return;
                if (WS_COND_FLG.TBL_FLAG == 'D') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_CREATE2_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        T000_READ_DCTCRDLT_UPD();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            WS8.HIS_FLG = 'M';
            if (DCRCRDLT.SE_LMT_AMT != 99999999999999.99) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_SEC_LMT_ALR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, DCCS1091.VAL);
                DCRCRDLT.SE_LMT_AMT = DCCS1091.VAL.SE_LMT_AMT;
                DCRCRDLT.STA_DT = DCCS1091.VAL.STA_DT;
                DCRCRDLT.END_DT = DCCS1091.VAL.END_DT;
                DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTCRDLT();
                if (pgmRtn) return;
                if (WS_COND_FLG.TBL_FLAG == 'D') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            WS8.HIS_FLG = 'A';
            DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
            DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
            DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
            DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
            DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
            DCRCRDLT.TXN_LMT_AMT = 99999999999999.99;
            DCRCRDLT.DLY_LMT_AMT = 99999999999999.99;
            DCRCRDLT.DLY_LMT_VOL = 99999;
            DCRCRDLT.MLY_LMT_AMT = 99999999999999.99;
            DCRCRDLT.MLY_LMT_VOL = 99999;
            DCRCRDLT.SYY_LMT_AMT = 99999999999999.99;
            DCRCRDLT.YLY_LMT_AMT = 99999999999999.99;
            DCRCRDLT.SE_LMT_AMT = DCCS1091.VAL.SE_LMT_AMT;
            DCRCRDLT.STA_DT = DCCS1091.VAL.STA_DT;
            DCRCRDLT.END_DT = DCCS1091.VAL.END_DT;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            if (WS_COND_FLG.TBL_FLAG == 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B070_SIGN_MAIN_ADD();
            if (pgmRtn) return;
        }
    }
    public void B025_OPEN_DOUBLE_FREE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        DCRCDDAT.DOUBLE_FREE_FLG = 'Y';
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "MODIFY STEVEN");
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY);
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        T000_READ_DCTCRDLT_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_RCDLT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCRCRDLT, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        DCRCRDLT.TXN_LMT_AMT = DCCS1091.VAL.TXN_LMT_AMT;
        DCRCRDLT.DLY_LMT_AMT = DCCS1091.VAL.DLY_LMT_AMT;
        DCRCRDLT.DLY_LMT_VOL = DCCS1091.VAL.DLY_LMT_VOL;
        DCRCRDLT.MLY_LMT_AMT = DCCS1091.VAL.MLY_LMT_AMT;
        DCRCRDLT.MLY_LMT_VOL = DCCS1091.VAL.MLY_LMT_VOL;
        DCRCRDLT.SYY_LMT_AMT = DCCS1091.VAL.SYY_LMT_AMT;
        DCRCRDLT.YLY_LMT_AMT = DCCS1091.VAL.YLY_LMT_AMT;
        DCRCRDLT.SE_LMT_AMT = DCCS1091.VAL.SE_LMT_AMT;
        DCRCRDLT.STA_DT = DCCS1091.VAL.STA_DT;
        DCRCRDLT.END_DT = DCCS1091.VAL.END_DT;
        DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCRDLT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'D') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_STS != 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_ALREADY_LOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_CVN_ERR_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_PIN_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.LMT_CCY);
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        T000_READ_DCTCRDLT_UPD();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_RCDLT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCRCRDLT, DCRCRDLT);
        T000_DELETE_DCTCRDLT();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && DCCS1091.VAL.KEY.TXN_TYPE.equalsIgnoreCase("05")) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
            DCRCDDAT.DOUBLE_FREE_FLG = 'C';
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCDDAT();
            if (pgmRtn) return;
        }
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.LMT_CCY);
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DCCUCDLP.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCRDLT);
        if (DCCS1091.VAL.KEY.CARD_NO.trim().length() == 0) {
            WS_DB_VARS.CARD_NO_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.CARD_NO_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.CARD_NO_LOW = "0" + WS_DB_VARS.CARD_NO_LOW;
            WS_DB_VARS.CARD_NO_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.CARD_NO_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.CARD_NO_HI = "0" + WS_DB_VARS.CARD_NO_HI;
        } else {
            WS_DB_VARS.CARD_NO_LOW = DCCS1091.VAL.KEY.CARD_NO;
            WS_DB_VARS.CARD_NO_HI = DCCS1091.VAL.KEY.CARD_NO;
        }
        if (DCCS1091.VAL.KEY.REGN_TYP == ' ') {
            WS_DB_VARS.REGN_TYP_LOW = 0X00;
            WS_DB_VARS.REGN_TYP_HI = 0XFF;
        } else {
            WS_DB_VARS.REGN_TYP_LOW = DCCS1091.VAL.KEY.REGN_TYP;
            WS_DB_VARS.REGN_TYP_HI = DCCS1091.VAL.KEY.REGN_TYP;
        }
        if (DCCS1091.VAL.KEY.CHNL_NO.trim().length() == 0) {
            WS_DB_VARS.CHNL_NO_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.CHNL_NO_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.CHNL_NO_LOW = "0" + WS_DB_VARS.CHNL_NO_LOW;
            WS_DB_VARS.CHNL_NO_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.CHNL_NO_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.CHNL_NO_HI = "0" + WS_DB_VARS.CHNL_NO_HI;
        } else {
            WS_DB_VARS.CHNL_NO_LOW = DCCS1091.VAL.KEY.CHNL_NO;
            WS_DB_VARS.CHNL_NO_HI = DCCS1091.VAL.KEY.CHNL_NO;
        }
        if (DCCS1091.VAL.KEY.TXN_TYPE.trim().length() == 0) {
            WS_DB_VARS.TXN_TYPE_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.TXN_TYPE_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.TXN_TYPE_LOW = "0" + WS_DB_VARS.TXN_TYPE_LOW;
            WS_DB_VARS.TXN_TYPE_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.TXN_TYPE_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.TXN_TYPE_HI = "0" + WS_DB_VARS.TXN_TYPE_HI;
        } else {
            WS_DB_VARS.TXN_TYPE_LOW = DCCS1091.VAL.KEY.TXN_TYPE;
            WS_DB_VARS.TXN_TYPE_HI = DCCS1091.VAL.KEY.TXN_TYPE;
        }
        if (DCCS1091.VAL.KEY.LMT_CCY.trim().length() == 0) {
            WS_DB_VARS.LMT_CCY_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.LMT_CCY_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.LMT_CCY_LOW = "0" + WS_DB_VARS.LMT_CCY_LOW;
            WS_DB_VARS.LMT_CCY_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.LMT_CCY_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.LMT_CCY_HI = "0" + WS_DB_VARS.LMT_CCY_HI;
        } else {
            WS_DB_VARS.LMT_CCY_LOW = DCCS1091.VAL.KEY.LMT_CCY;
            WS_DB_VARS.LMT_CCY_HI = DCCS1091.VAL.KEY.LMT_CCY;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            T000_STARTBR_DCTCRDLT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCS1091.VAL.KEY.CARD_NO);
            T000_READNEXT_DCTCRDLT();
            if (pgmRtn) return;
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
            WS_VARIABLES.CNT = 0;
            while (WS_COND_FLG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B040_01_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                WS_VARIABLES.CNT += 1;
                T000_READNEXT_DCTCRDLT();
                if (pgmRtn) return;
            }
        } else {
            B040_02_01_OUTPUT_HEAD();
            if (pgmRtn) return;
            T000_STARTBR_DCTCRDLT_W();
            if (pgmRtn) return;
            T000_READNEXT_DCTCRDLT_W();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, WS_COND_FLG.TBL_FLAG);
            if (WS_COND_FLG.TBL_FLAG != 'N') {
                WS_DATA.IDX = 0;
                WS_DATA.TOTAL_NUM = 0;
                CEP.TRC(SCCGWA, WS_DATA.IDX);
                CEP.TRC(SCCGWA, WS_DATA.PAGE_ROW);
                while (WS_DATA.IDX != WS_DATA.PAGE_ROW 
                    && WS_COND_FLG.TBL_FLAG != 'N') {
                    B040_02_02_WRITE_OUTPUT();
                    if (pgmRtn) return;
                    WS_DATA.NEXT_START_NUM += 1;
                    WS_DB_VARS.START_NUM = WS_DATA.NEXT_START_NUM;
                    T000_READNEXT_DCTCRDLT_W();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "-------READ NEXT----");
                }
                CEP.TRC(SCCGWA, WS_COND_FLG.TBL_FLAG);
                if (WS_COND_FLG.TBL_FLAG == 'N') {
                    WS_DATA.TOTAL_PAGE = WS_DATA.CURR_PAGE;
                    WS_DATA.BAL_CNT = WS_DATA.IDX;
                    WS_DATA.TOTAL_NUM = ( WS_DATA.CURR_PAGE - 1 ) * WS_DATA.PAGE_ROW + WS_DATA.BAL_CNT;
                    WS_DATA.LAST_PAGE = 'Y';
                    WS_DATA.PAGE_ROW = (short) WS_DATA.BAL_CNT;
                    WS_OUT_INFO = new DCZSCDLT_WS_OUT_INFO();
                    WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
                } else {
                    B040_02_04_GROUP();
                    if (pgmRtn) return;
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
                WS_OUT_INFO = new DCZSCDLT_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        T000_ENDBR_DCTCRDLT();
        if (pgmRtn) return;
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
            B040_02_03_OUTPUT_WRITE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "=====TEST=====");
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 1644;
        CEP.TRC(SCCGWA, DCCS1091.ROWS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("TLR")) {
            SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        } else {
            if (DCCS1091.ROWS == 0) {
                SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
            } else {
                SCCMPAG.SCR_ROW_CNT = DCCS1091.ROWS;
            }
        }
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SCDLT_OUT);
        WS_SCDLT_OUT.WS_SCDLT_KEY.CARD_NO = DCRCRDLT.KEY.CARD_NO;
        WS_SCDLT_OUT.WS_SCDLT_KEY.REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_SCDLT_OUT.WS_SCDLT_KEY.CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_SCDLT_OUT.WS_SCDLT_KEY.TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_SCDLT_OUT.WS_SCDLT_KEY.LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_SCDLT_OUT.TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_SCDLT_OUT.DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        CEP.TRC(SCCGWA, "====TEST=====");
        CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_VOL);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.DLY_LMT_VOL);
        WS_SCDLT_OUT.DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_SCDLT_OUT.MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_SCDLT_OUT.MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_SCDLT_OUT.SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_SCDLT_OUT.YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_SCDLT_OUT.SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
        WS_SCDLT_OUT.STA_DT = DCRCRDLT.STA_DT;
        WS_SCDLT_OUT.END_DT = DCRCRDLT.END_DT;
        WS_SCDLT_OUT.UPD_DT = DCRCRDLT.UPDTBL_DATE;
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.WS_SCDLT_KEY.LMT_CCY);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.TXN_LMT_AMT);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.DLY_LMT_AMT);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.DLY_LMT_VOL);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.MLY_LMT_AMT);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.MLY_LMT_VOL);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.SYY_LMT_AMT);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.YLY_LMT_AMT);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.SE_LMT_AMT);
        WS_SCDLT_OUT.UPD_TLR = DCRCRDLT.UPDTBL_TLR;
        CEP.TRC(SCCGWA, WS_SCDLT_OUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SCDLT_OUT);
        SCCMPAG.DATA_LEN = 1644;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_01_OUTPUT_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (DCCS1091.PAGE_NUM == 0) {
            WS_DATA.CURR_PAGE = 1;
        } else {
            WS_DATA.CURR_PAGE = (short) DCCS1091.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_DATA.CURR_PAGE;
        WS_DATA.LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, DCCS1091.PAGE_ROW);
        if (DCCS1091.PAGE_ROW == 0) {
            WS_DATA.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new DCZSCDLT_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (DCCS1091.PAGE_ROW > 50) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_DATA.PAGE_ROW = (short) DCCS1091.PAGE_ROW;
                WS_OUT_INFO = new DCZSCDLT_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        CEP.TRC(SCCGWA, WS_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, PAGE_ROW);
        WS_DATA.NEXT_START_NUM = ( ( WS_DATA.CURR_PAGE - 1 ) * WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_DATA.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
    }
    public void B040_02_02_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_DATA.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.WS_0_SCDLT_KEY.O_CARD_NO = DCRCRDLT.KEY.CARD_NO;
        WS_OUT_INFO.WS_0_SCDLT_KEY.O_REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_OUT_INFO.WS_0_SCDLT_KEY.O_CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_OUT_INFO.WS_0_SCDLT_KEY.O_TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_OUT_INFO.WS_0_SCDLT_KEY.O_LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_OUT_INFO.O_TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_OUT_INFO.O_DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        WS_OUT_INFO.O_DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_OUT_INFO.O_MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_OUT_INFO.O_MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_OUT_INFO.O_SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_OUT_INFO.O_YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_OUT_INFO.O_SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
        WS_OUT_INFO.O_STA_DT = DCRCRDLT.STA_DT;
        WS_OUT_INFO.O_END_DT = DCRCRDLT.END_DT;
        WS_OUT_INFO.O_UPD_DT = DCRCRDLT.UPDTBL_DATE;
        WS_OUT_INFO.O_UPD_TLR = DCRCRDLT.UPDTBL_TLR;
        CEP.TRC(SCCGWA, "-----------OUTPUT-----------");
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_DATA.IDX-1));
    }
    public void B040_02_03_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 4369;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_SIGN_MAIN_ADD() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.TXN_TYPE.equalsIgnoreCase("05")) {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'A';
            CICMAGT.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
            CICMAGT.DATA.AGT_TYP = "IBS029";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCCS1091.VAL.KEY.CARD_NO;
            CICMAGT.DATA.FRM_APP = "DC";
            CICMAGT.DATA.AGT_LVL = 'A';
            CICMAGT.DATA.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.AGT_STS = 'N';
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
        if (DCCS1091.VAL.KEY.CHNL_NO.equalsIgnoreCase("10208")) {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'A';
            CICMAGT.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
            CICMAGT.DATA.AGT_TYP = "IBS001";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCCS1091.VAL.KEY.CARD_NO;
            CICMAGT.DATA.FRM_APP = "DC";
            CICMAGT.DATA.AGT_LVL = 'A';
            CICMAGT.DATA.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMAGT.DATA.AGT_STS = 'N';
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
    }
    public void B071_SIGN_MAIN_DEL() throws IOException,SQLException,Exception {
        if (DCCS1091.VAL.KEY.TXN_TYPE.equalsIgnoreCase("05")) {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'D';
            CICMAGT.DATA.AGT_TYP = "IBS029";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCCS1091.VAL.KEY.CARD_NO;
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
        if (DCCS1091.VAL.KEY.CHNL_NO.equalsIgnoreCase("10208")) {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'D';
            CICMAGT.DATA.AGT_TYP = "IBS001";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCCS1091.VAL.KEY.CARD_NO;
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCS1091.VAL.KEY.CARD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS1091.VAL.KEY.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCCUCINF.CARD_OWN_CINO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 942;
        if (DCCS1091.FUNC == '1' 
            || DCCS1091.FUNC == 'C') {
            if (WS8.HIS_FLG == 'A') {
                BPCPNHIS.INFO.TX_TYP = 'A';
                BPCPNHIS.INFO.DATA_FLG = 'Y';
                BPCPNHIS.INFO.NEW_DAT_PT = DCRCRDLT;
            } else if (WS8.HIS_FLG == 'M') {
                BPCPNHIS.INFO.TX_TYP = 'M';
                BPCPNHIS.INFO.OLD_DAT_PT = DCRCRDLT;
                BPCPNHIS.INFO.NEW_DAT_PT = DCRCRDLT;
            }
        }
        if (DCCS1091.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRCRDLT;
        }
        if (DCCS1091.FUNC == '3') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRCRDLT;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRCRDLT;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_SCDLT_OUT);
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.WS_SCDLT_KEY.CARD_NO);
        WS_SCDLT_OUT.WS_SCDLT_KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        WS_SCDLT_OUT.WS_SCDLT_KEY.REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_SCDLT_OUT.WS_SCDLT_KEY.CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_SCDLT_OUT.WS_SCDLT_KEY.TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_SCDLT_OUT.WS_SCDLT_KEY.LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_SCDLT_OUT.TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_SCDLT_OUT.DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        CEP.TRC(SCCGWA, "====TEST1=======");
        CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_VOL);
        WS_SCDLT_OUT.DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_SCDLT_OUT.MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_SCDLT_OUT.MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_SCDLT_OUT.SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_SCDLT_OUT.YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_SCDLT_OUT.SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
        WS_SCDLT_OUT.STA_DT = DCRCRDLT.STA_DT;
        WS_SCDLT_OUT.END_DT = DCRCRDLT.END_DT;
        WS_SCDLT_OUT.UPD_DT = DCRCRDLT.UPDTBL_DATE;
        WS_SCDLT_OUT.UPD_TLR = DCRCRDLT.UPDTBL_TLR;
        CEP.TRC(SCCGWA, WS_SCDLT_OUT);
        SCCFMT.FMTID = OUTPUT_FMT_9;
        CEP.TRC(SCCGWA, "====TEST2=======");
        CEP.TRC(SCCGWA, WS_SCDLT_OUT.DLY_LMT_VOL);
        SCCFMT.DATA_PTR = WS_SCDLT_OUT;
        SCCFMT.DATA_LEN = 1644;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        IBS.READ(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        IBS.READ(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.READ(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_COND_FLG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCRDLT_UPD() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_COND_FLG.TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.DELETE(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_BR.rp = new DBParm();
        DCTCRDLT_BR.rp.TableName = "DCTCRDLT";
        DCTCRDLT_BR.rp.where = "( CARD_NO >= :WS_DB_VARS.CARD_NO_LOW ) "
            + "AND ( CARD_NO <= :WS_DB_VARS.CARD_NO_HI ) "
            + "AND ( REGN_TYP >= :WS_DB_VARS.REGN_TYP_LOW ) "
            + "AND ( REGN_TYP <= :WS_DB_VARS.REGN_TYP_HI ) "
            + "AND ( CHNL_NO >= :WS_DB_VARS.CHNL_NO_LOW ) "
            + "AND ( CHNL_NO <= :WS_DB_VARS.CHNL_NO_HI ) "
            + "AND ( TXN_TYPE >= :WS_DB_VARS.TXN_TYPE_LOW ) "
            + "AND ( TXN_TYPE <= :WS_DB_VARS.TXN_TYPE_HI ) "
            + "AND ( LMT_CCY >= :WS_DB_VARS.LMT_CCY_LOW ) "
            + "AND ( LMT_CCY <= :WS_DB_VARS.LMT_CCY_HI )";
        DCTCRDLT_BR.rp.order = "CARD_NO,REGN_TYP,CHNL_NO,TXN_TYPE,LMT_CCY";
        IBS.STARTBR(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTCRDLT_W() throws IOException,SQLException,Exception {
        DCTCRDLT_BR.rp = new DBParm();
        DCTCRDLT_BR.rp.TableName = "DCTCRDLT";
        DCTCRDLT_BR.rp.where = "( CARD_NO >= :WS_DB_VARS.CARD_NO_LOW ) "
            + "AND ( CARD_NO <= :WS_DB_VARS.CARD_NO_HI ) "
            + "AND ( REGN_TYP >= :WS_DB_VARS.REGN_TYP_LOW ) "
            + "AND ( REGN_TYP <= :WS_DB_VARS.REGN_TYP_HI ) "
            + "AND ( CHNL_NO >= :WS_DB_VARS.CHNL_NO_LOW ) "
            + "AND ( CHNL_NO <= :WS_DB_VARS.CHNL_NO_HI ) "
            + "AND ( TXN_TYPE >= :WS_DB_VARS.TXN_TYPE_LOW ) "
            + "AND ( TXN_TYPE <= :WS_DB_VARS.TXN_TYPE_HI ) "
            + "AND ( LMT_CCY >= :WS_DB_VARS.LMT_CCY_LOW ) "
            + "AND ( LMT_CCY <= :WS_DB_VARS.LMT_CCY_HI )";
        DCTCRDLT_BR.rp.order = "CARD_NO,REGN_TYP,CHNL_NO,TXN_TYPE,LMT_CCY";
        IBS.STARTBR(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_READNEXT_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_READNEXT_DCTCRDLT_W() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCRDLT_BR);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.set = "WS-NUM=COUNT(*)";
        DCTCRDLT_RD.where = "( CARD_NO >= :WS_DB_VARS.CARD_NO_LOW ) "
            + "AND ( CARD_NO <= :WS_DB_VARS.CARD_NO_HI ) "
            + "AND ( REGN_TYP >= :WS_DB_VARS.REGN_TYP_LOW ) "
            + "AND ( REGN_TYP <= :WS_DB_VARS.REGN_TYP_HI ) "
            + "AND ( CHNL_NO >= :WS_DB_VARS.CHNL_NO_LOW ) "
            + "AND ( CHNL_NO <= :WS_DB_VARS.CHNL_NO_HI ) "
            + "AND ( TXN_TYPE >= :WS_DB_VARS.TXN_TYPE_LOW ) "
            + "AND ( TXN_TYPE <= :WS_DB_VARS.TXN_TYPE_HI ) "
            + "AND ( LMT_CCY >= :WS_DB_VARS.LMT_CCY_LOW ) "
            + "AND ( LMT_CCY <= :WS_DB_VARS.LMT_CCY_HI )";
        IBS.GROUP(SCCGWA, DCRCRDLT, this, DCTCRDLT_RD);
        WS_DATA.TOTAL_NUM = WS_DB_VARS.NUM;
        CEP.TRC(SCCGWA, WS_DATA.TOTAL_NUM);
    }
    public void B040_02_04_GROUP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        if (DCCS1091.VAL.KEY.CARD_NO.trim().length() == 0) {
            DCRCRDLT.KEY.CARD_NO = "%%%%%%%%%%%%%%%%%%%";
        } else {
            DCRCRDLT.KEY.CARD_NO = DCCS1091.VAL.KEY.CARD_NO;
        }
        if (DCCS1091.VAL.KEY.REGN_TYP == ' ') {
            DCRCRDLT.KEY.REGN_TYP = ALL.charAt(0);
        } else {
            DCRCRDLT.KEY.REGN_TYP = DCCS1091.VAL.KEY.REGN_TYP;
        }
        if (DCCS1091.VAL.KEY.CHNL_NO.trim().length() == 0) {
            DCRCRDLT.KEY.CHNL_NO = "%%%%%";
        } else {
            DCRCRDLT.KEY.CHNL_NO = DCCS1091.VAL.KEY.CHNL_NO;
        }
        if (DCCS1091.VAL.KEY.TXN_TYPE.trim().length() == 0) {
            DCRCRDLT.KEY.TXN_TYPE = "%%";
        } else {
            DCRCRDLT.KEY.TXN_TYPE = DCCS1091.VAL.KEY.TXN_TYPE;
        }
        if (DCCS1091.VAL.KEY.LMT_CCY.trim().length() == 0) {
            DCRCRDLT.KEY.LMT_CCY = "%%%";
        } else {
            DCRCRDLT.KEY.LMT_CCY = DCCS1091.VAL.KEY.LMT_CCY;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZPDTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PDTL", CICPDTL);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCDLP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-BR", DCCUCDLP);
        if (DCCUCDLP.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCDLP.RC);
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
