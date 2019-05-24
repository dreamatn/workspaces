package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSLKLT {
    int JIBS_tmp_int;
    DCZSLKLT_WS_OUT_INFO WS_OUT_INFO;
    DBParm DCTCDDAT_RD;
    DBParm DCTPRDLT_RD;
    DBParm DCTCRDLT_RD;
    brParm DCTCRDLT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_9 = "DC119";
    int MAX_COL = 99;
    int MAX_ROW = 50;
    int COL_CNT = 8;
    String HIS_REMARK = "CARD-LINK CARD INFO. MAINTAIN";
    String HIS_COPYBOOK = "DCRCRDLT";
    String TBL_CRDLT = "DCTCRDLT";
    String TBL_PRDLT = "DCTPRDLT";
    short PAGE_ROW = 25;
    DCZSLKLT_WS_VARIABLES WS_VARIABLES = new DCZSLKLT_WS_VARIABLES();
    DCZSLKLT_WS_DATA WS_DATA = new DCZSLKLT_WS_DATA();
    DCZSLKLT_WS_OUT_RECODE WS_OUT_RECODE = new DCZSLKLT_WS_OUT_RECODE();
    DCZSLKLT_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DCZSLKLT_WS_SQL_VARIABLES();
    DCZSLKLT_WS_SQL_VARIABLES_OTHER WS_SQL_VARIABLES_OTHER = new DCZSLKLT_WS_SQL_VARIABLES_OTHER();
    DCZSLKLT_WS_DB_VARS WS_DB_VARS = new DCZSLKLT_WS_DB_VARS();
    DCZSLKLT_WS_COND_FLG WS_COND_FLG = new DCZSLKLT_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCRPRDLT DCRPRDLT = new DCRPRDLT();
    DCRPRDLT_WS_SLKLT_OUT WS_SLKLT_OUT = new DCRPRDLT_WS_SLKLT_OUT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCS1191 DCCS1191;
    public void MP(SCCGWA SCCGWA, DCCS1191 DCCS1191) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS1191 = DCCS1191;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSLKLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, DCRCRDLT);
        IBS.init(SCCGWA, DCRCRDLT);
        IBS.init(SCCGWA, WS_DATA);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        CEP.TRC(SCCGWA, DCCS1191);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS1191.VAL.STA_DT);
        CEP.TRC(SCCGWA, DCCS1191.VAL.END_DT);
        CEP.TRC(SCCGWA, DCCS1191.VAL.UPD_DT);
        CEP.TRC(SCCGWA, DCCS1191.VAL.UPD_TLR);
        if (DCCS1191.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1191.FUNC == 'A') {
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1191.FUNC == 'U') {
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1191.FUNC == 'D') {
            B002_CHECK_CARD_NO();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCS1191.FUNC == 'B') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCS1191.VAL.CARD_LNK_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 9);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DC2");
        if ((DCCS1191.VAL.TXN_LMT_AMT > DCCS1191.VAL.DLY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_TX_LMT_GT_DAILY_LMT;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 15);
        }
        CEP.TRC(SCCGWA, "DC3");
        if ((DCCS1191.VAL.DLY_LMT_AMT > DCCS1191.VAL.MLY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_DAILY_GT_MONTHLY;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 16);
        }
        CEP.TRC(SCCGWA, "DC4");
        CEP.TRC(SCCGWA, DCCS1191.VAL.MLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1191.VAL.SYY_LMT_AMT);
        if ((DCCS1191.VAL.MLY_LMT_AMT > DCCS1191.VAL.SYY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MONTHLY_GT_SEMIYEAR;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 18);
        }
        CEP.TRC(SCCGWA, "DC5");
        if ((DCCS1191.VAL.SYY_LMT_AMT > DCCS1191.VAL.YLY_LMT_AMT)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_SEMIYEAR_GT_YEAR;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 20);
        }
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        CEP.TRC(SCCGWA, "--------CRDLT------");
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.LMT_CCY);
        T000_READ_DCTCRDLT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, "GWA-DBIO-FLG 0");
            CEP.TRC(SCCGWA, DCRCRDLT.TXN_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCRCRDLT.MLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.SYY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.YLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.SE_LMT_AMT);
            WS_VARIABLES.PRIM_SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
            if ((DCCS1191.VAL.TXN_LMT_AMT > DCRCRDLT.TXN_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 15);
            }
            if ((DCCS1191.VAL.DLY_LMT_AMT > DCRCRDLT.DLY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 16);
            }
            if ((DCCS1191.VAL.DLY_LMT_VOL > DCRCRDLT.DLY_LMT_VOL)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 17);
            }
            if ((DCCS1191.VAL.MLY_LMT_AMT > DCRCRDLT.MLY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 18);
            }
            if ((DCCS1191.VAL.SYY_LMT_AMT > DCRCRDLT.SYY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 20);
            }
            if ((DCCS1191.VAL.YLY_LMT_AMT > DCRCRDLT.YLY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 21);
            }
            if ((DCCS1191.VAL.SE_LMT_AMT > DCRCRDLT.SE_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_LMT_GR_PRIM_CARD_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 22);
            }
        }
        IBS.init(SCCGWA, DCRPRDLT);
        DCRPRDLT.KEY.PROD_CD = DCRCDDAT.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        CEP.TRC(SCCGWA, "--------PRDLT-PROD-CD---");
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.LMT_CCY);
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
            if ((DCCS1191.VAL.TXN_LMT_AMT > DCRPRDLT.TXN_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 15);
            }
            if ((DCCS1191.VAL.DLY_LMT_AMT > DCRPRDLT.DLY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 16);
            }
            if ((DCCS1191.VAL.DLY_LMT_VOL > DCRPRDLT.DLY_LMT_VOL)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 17);
            }
            if ((DCCS1191.VAL.MLY_LMT_AMT > DCRPRDLT.MLY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 18);
            }
            if ((DCCS1191.VAL.SYY_LMT_AMT > DCRPRDLT.SYY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 20);
            }
            if ((DCCS1191.VAL.YLY_LMT_AMT > DCRPRDLT.YLY_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 21);
            }
            if ((DCCS1191.VAL.SE_LMT_AMT > DCRPRDLT.SE_LMT_AMT)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.CARD_GT_PRODUCT_LMT;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 22);
            }
        }
        CEP.TRC(SCCGWA, "GWA-DBIO-FLG 2");
        CEP.TRC(SCCGWA, DCCS1191.VAL.SE_LMT_AMT);
        CEP.TRC(SCCGWA, DCCS1191.VAL.STA_DT);
        CEP.TRC(SCCGWA, DCCS1191.VAL.END_DT);
        if (DCCS1191.VAL.STA_DT == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_START_DATE_IS_ZERO;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 23);
        }
        if (DCCS1191.VAL.END_DT == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_END_DATE_IS_ZERO;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 24);
        }
        if (DCCS1191.FUNC == 'A') {
            if (DCCS1191.VAL.STA_DT < SCCGWA.COMM_AREA.AC_DATE 
                && DCCS1191.VAL.STA_DT != WS_SQL_VARIABLES_OTHER.CLIM_DFUS_DT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_S_DATE_LESS_AC_DATE;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 23);
            }
            if (DCCS1191.VAL.END_DT > WS_SQL_VARIABLES_OTHER.CLIM_DFUE_DT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_END_DATE_IS_99991231;
                CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 24);
            }
        }
        CEP.TRC(SCCGWA, "GWA-DBIO-FLG 4");
        if (DCCS1191.VAL.STA_DT > DCCS1191.VAL.END_DT) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_S_TIME_GT_D_TIME;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 24);
        }
        CEP.TRC(SCCGWA, "GWA-DBIO-FLG 5");
        CEP.TRC(SCCGWA, DCCS1191.VAL.DLY_LMT_VOL);
        CEP.TRC(SCCGWA, DCCS1191.VAL.MLY_LMT_VOL);
        if ((DCCS1191.VAL.DLY_LMT_VOL > DCCS1191.VAL.MLY_LMT_VOL)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DAYT_GT_MONT_LMT;
            CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, 18);
        }
    }
    public void B002_CHECK_CARD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        B011_GET_PRIM_CARD_NO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
        if (DCRCDDAT.CARD_LNK_TYP != '2') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_LNK_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "====KK===");
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.CARD_NO);
        if (!CICQACRL.O_DATA.O_REL_AC_NO.equalsIgnoreCase(DCCS1191.VAL.KEY.CARD_NO)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_PRIM_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_LNK_TYP != '2') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_LNK_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        CEP.TRC(SCCGWA, DCCS1191.VAL.STA_DT);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY);
        T000_READ_DCTCRDLT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COND_FLG);
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("IBS")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_NO_CARD_LIMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        T000_READ_DCTCRDLT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_GE_LMT_ALR_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DCRCRDLT.TXN_LMT_AMT = DCCS1191.VAL.TXN_LMT_AMT;
            DCRCRDLT.DLY_LMT_AMT = DCCS1191.VAL.DLY_LMT_AMT;
            DCRCRDLT.DLY_LMT_VOL = DCCS1191.VAL.DLY_LMT_VOL;
            DCRCRDLT.MLY_LMT_AMT = DCCS1191.VAL.MLY_LMT_AMT;
            DCRCRDLT.MLY_LMT_VOL = DCCS1191.VAL.MLY_LMT_VOL;
            CEP.TRC(SCCGWA, DCCS1191.VAL.MLY_LMT_AMT);
            DCRCRDLT.SYY_LMT_AMT = DCCS1191.VAL.SYY_LMT_AMT;
            CEP.TRC(SCCGWA, DCCS1191.VAL.YLY_LMT_AMT);
            DCRCRDLT.YLY_LMT_AMT = DCCS1191.VAL.YLY_LMT_AMT;
            if (DCCS1191.VAL.SE_LMT_AMT == ' ' 
                || DCCS1191.VAL.SE_LMT_AMT == 0) {
                if (WS_VARIABLES.PRIM_SE_LMT_AMT != 0) {
                    DCRCRDLT.SE_LMT_AMT = WS_VARIABLES.PRIM_SE_LMT_AMT;
                } else {
                    DCRCRDLT.SE_LMT_AMT = 99999999999999.99;
                }
            } else {
                DCRCRDLT.SE_LMT_AMT = DCCS1191.VAL.SE_LMT_AMT;
            }
            DCRCRDLT.STA_DT = DCCS1191.VAL.STA_DT;
            DCRCRDLT.END_DT = DCCS1191.VAL.END_DT;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, "L");
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, "M");
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            if (WS_COND_FLG.TBL_FLAG == 'D') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DUPKEY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "MODIFY STEVEN");
        CEP.TRC(SCCGWA, DCCS1191.VAL.KEY);
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
        T000_READ_DCTCRDLT_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_NO_CARD_LIMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCRCRDLT, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        DCRCRDLT.TXN_LMT_AMT = DCCS1191.VAL.TXN_LMT_AMT;
        DCRCRDLT.DLY_LMT_AMT = DCCS1191.VAL.DLY_LMT_AMT;
        DCRCRDLT.DLY_LMT_VOL = DCCS1191.VAL.DLY_LMT_VOL;
        DCRCRDLT.MLY_LMT_AMT = DCCS1191.VAL.MLY_LMT_AMT;
        DCRCRDLT.MLY_LMT_VOL = DCCS1191.VAL.MLY_LMT_VOL;
        DCRCRDLT.SYY_LMT_AMT = DCCS1191.VAL.SYY_LMT_AMT;
        DCRCRDLT.YLY_LMT_AMT = DCCS1191.VAL.YLY_LMT_AMT;
        DCRCRDLT.SE_LMT_AMT = DCCS1191.VAL.SE_LMT_AMT;
        DCRCRDLT.STA_DT = DCCS1191.VAL.STA_DT;
        DCRCRDLT.END_DT = DCCS1191.VAL.END_DT;
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
        CEP.TRC(SCCGWA, "DELETE STEVEN");
        CEP.TRC(SCCGWA, DCCS1191.VAL.KEY);
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CARD_NO);
        T000_READ_DCTCRDLT_UPD();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_NO_CARD_LIMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCRCRDLT, DCRCRDLT);
        T000_DELETE_DCTCRDLT();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
        DCCS1191.VAL.CARD_LNK_NO = DCCS1191.VAL.KEY.CARD_NO;
        B011_GET_PRIM_CARD_NO();
        if (pgmRtn) return;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_LNK_TYP != '2') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_LNK_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.KEY.CARD_NO;
        CEP.TRC(SCCGWA, "N000");
        CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CARD_NO);
        WS_DB_VARS.CARD_NO_LOW = DCCS1191.VAL.KEY.CARD_NO;
        WS_DB_VARS.CARD_NO_HI = DCCS1191.VAL.KEY.CARD_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.PRIM_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        if (DCCS1191.VAL.KEY.REGN_TYP == ' ') {
            CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.REGN_TYP);
            WS_DB_VARS.REGN_TYP_LOW = 0X00;
            WS_DB_VARS.REGN_TYP_HI = 0XFF;
        } else {
            WS_DB_VARS.REGN_TYP_LOW = DCCS1191.VAL.KEY.REGN_TYP;
            WS_DB_VARS.REGN_TYP_HI = DCCS1191.VAL.KEY.REGN_TYP;
        }
        if (DCCS1191.VAL.KEY.CHNL_NO.trim().length() == 0 
            || DCCS1191.VAL.KEY.CHNL_NO.equalsIgnoreCase("ALL")) {
            CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.CHNL_NO);
            WS_DB_VARS.CHNL_NO_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.CHNL_NO_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.CHNL_NO_LOW = "0" + WS_DB_VARS.CHNL_NO_LOW;
            WS_DB_VARS.CHNL_NO_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.CHNL_NO_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.CHNL_NO_HI = "0" + WS_DB_VARS.CHNL_NO_HI;
        } else {
            WS_DB_VARS.CHNL_NO_LOW = DCCS1191.VAL.KEY.CHNL_NO;
            WS_DB_VARS.CHNL_NO_HI = DCCS1191.VAL.KEY.CHNL_NO;
        }
        if (DCCS1191.VAL.KEY.TXN_TYPE.trim().length() == 0) {
            CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.TXN_TYPE);
            WS_DB_VARS.TXN_TYPE_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.TXN_TYPE_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.TXN_TYPE_LOW = "0" + WS_DB_VARS.TXN_TYPE_LOW;
            WS_DB_VARS.TXN_TYPE_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.TXN_TYPE_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.TXN_TYPE_HI = "0" + WS_DB_VARS.TXN_TYPE_HI;
        } else {
            WS_DB_VARS.TXN_TYPE_LOW = DCCS1191.VAL.KEY.TXN_TYPE;
            WS_DB_VARS.TXN_TYPE_HI = DCCS1191.VAL.KEY.TXN_TYPE;
        }
        if (DCCS1191.VAL.KEY.LMT_CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, DCCS1191.VAL.KEY.LMT_CCY);
            WS_DB_VARS.LMT_CCY_LOW = "" + 0X00;
            JIBS_tmp_int = WS_DB_VARS.LMT_CCY_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.LMT_CCY_LOW = "0" + WS_DB_VARS.LMT_CCY_LOW;
            WS_DB_VARS.LMT_CCY_HI = "" + 0XFF;
            JIBS_tmp_int = WS_DB_VARS.LMT_CCY_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_DB_VARS.LMT_CCY_HI = "0" + WS_DB_VARS.LMT_CCY_HI;
        } else {
            WS_DB_VARS.LMT_CCY_LOW = DCCS1191.VAL.KEY.LMT_CCY;
            WS_DB_VARS.LMT_CCY_HI = DCCS1191.VAL.KEY.LMT_CCY;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            CEP.TRC(SCCGWA, DCRCRDLT.KEY.CARD_NO);
            T000_STARTBR_DCTCRDLT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
            T000_READNEXT_DCTCRDLT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "==N1==");
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
        }
        T000_ENDBR_DCTCRDLT();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 1381;
        CEP.TRC(SCCGWA, DCCS1191.ROWS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("TLR")) {
            SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        } else {
            if (DCCS1191.ROWS == 0) {
                SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
            } else {
                SCCMPAG.SCR_ROW_CNT = DCCS1191.ROWS;
            }
        }
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SLKLT_OUT);
        WS_SLKLT_OUT.WS_SLKLT_KEY.CARD_NO = DCRCRDLT.KEY.CARD_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.PRIM_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_SLKLT_OUT.WS_SLKLT_KEY.CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_SLKLT_OUT.WS_SLKLT_KEY.LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_SLKLT_OUT.TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_SLKLT_OUT.DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        WS_SLKLT_OUT.DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_SLKLT_OUT.MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_SLKLT_OUT.MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_SLKLT_OUT.SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_SLKLT_OUT.YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_SLKLT_OUT.SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
        WS_SLKLT_OUT.STA_DT = DCRCRDLT.STA_DT;
        WS_SLKLT_OUT.STA_TM = DCRCRDLT.STA_TM;
        WS_SLKLT_OUT.END_DT = DCRCRDLT.END_DT;
        WS_SLKLT_OUT.END_TM = DCRCRDLT.END_TM;
        WS_SLKLT_OUT.UPD_DT = DCRCRDLT.UPDTBL_DATE;
        WS_SLKLT_OUT.UPD_TLR = DCRCRDLT.UPDTBL_TLR;
        CEP.TRC(SCCGWA, WS_SLKLT_OUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SLKLT_OUT);
        SCCMPAG.DATA_LEN = 1381;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_01_OUTPUT_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (DCCS1191.PAGE_NUM == 0) {
            WS_DATA.CURR_PAGE = 1;
        } else {
            WS_DATA.CURR_PAGE = (short) DCCS1191.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_DATA.CURR_PAGE;
        WS_DATA.LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, DCCS1191.PAGE_ROW);
        if (DCCS1191.PAGE_ROW == 0) {
            WS_DATA.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new DCZSLKLT_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (DCCS1191.PAGE_ROW > 50) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_DATA.PAGE_ROW = (short) DCCS1191.PAGE_ROW;
                WS_OUT_INFO = new DCZSLKLT_WS_OUT_INFO();
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
        WS_OUT_INFO.WS_0_SLKLT_KEY.O_CARD_NO = DCRCRDLT.KEY.CARD_NO;
        WS_OUT_INFO.WS_0_SLKLT_KEY.O_REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_OUT_INFO.WS_0_SLKLT_KEY.O_CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_OUT_INFO.WS_0_SLKLT_KEY.O_TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_OUT_INFO.WS_0_SLKLT_KEY.O_LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
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
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS1191.VAL.CARD_LNK_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 942;
        if (DCCS1191.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRCRDLT;
        }
        if (DCCS1191.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRCRDLT;
        }
        if (DCCS1191.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRCRDLT;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRCRDLT;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_SLKLT_OUT);
        B011_GET_PRIM_CARD_NO();
        if (pgmRtn) return;
        WS_SLKLT_OUT.WS_SLKLT_KEY.CARD_NO = DCRCRDLT.KEY.CARD_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.PRIM_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_SLKLT_OUT.WS_SLKLT_KEY.CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_SLKLT_OUT.WS_SLKLT_KEY.TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_SLKLT_OUT.WS_SLKLT_KEY.LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_SLKLT_OUT.TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_SLKLT_OUT.DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        WS_SLKLT_OUT.DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_SLKLT_OUT.MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_SLKLT_OUT.MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_SLKLT_OUT.SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_SLKLT_OUT.YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_SLKLT_OUT.SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
        WS_SLKLT_OUT.STA_DT = DCRCRDLT.STA_DT;
        WS_SLKLT_OUT.END_DT = DCRCRDLT.END_DT;
        WS_SLKLT_OUT.UPD_DT = DCRCRDLT.UPDTBL_DATE;
        WS_SLKLT_OUT.UPD_TLR = DCRCRDLT.UPDTBL_TLR;
        CEP.TRC(SCCGWA, WS_SLKLT_OUT);
        SCCFMT.FMTID = OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_SLKLT_OUT;
        SCCFMT.DATA_LEN = 1381;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B011_GET_PRIM_CARD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CEP.TRC(SCCGWA, DCCS1191.VAL.CARD_LNK_NO);
        CICQACRL.DATA.AC_NO = DCCS1191.VAL.CARD_LNK_NO;
        CICQACRL.DATA.AC_REL = "03";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        WS_SLKLT_OUT.WS_SLKLT_KEY.PRIM_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        DCTPRDLT_RD.col = "PROD_CD, REGN_TYP, CHNL_NO, TXN_TYPE, LMT_CCY, STA_DT, END_DT, TXN_LMT_AMT, DLY_LMT_AMT, DLY_LMT_VOL, MLY_LMT_AMT, MLY_LMT_VOL, SYY_LMT_AMT, YLY_LMT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
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
        DCTPRDLT_RD.col = "PROD_CD, REGN_TYP, CHNL_NO, TXN_TYPE, LMT_CCY, STA_DT, END_DT, TXN_LMT_AMT, DLY_LMT_AMT, DLY_LMT_VOL, MLY_LMT_AMT, MLY_LMT_VOL, SYY_LMT_AMT, YLY_LMT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPRDLT_RD.where = "PROD_CD = :DCRPRDLT.KEY.PROD_CD "
            + "AND REGN_TYP = :DCRPRDLT.KEY.REGN_TYP "
            + "AND CHNL_NO = :DCRPRDLT.KEY.CHNL_NO "
            + "AND TXN_TYPE = :DCRPRDLT.KEY.TXN_TYPE "
            + "AND LMT_CCY = :DCRPRDLT.KEY.LMT_CCY";
        DCTPRDLT_RD.fst = true;
        IBS.READ(SCCGWA, DCRPRDLT, this, DCTPRDLT_RD);
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
        DCTCRDLT_RD.col = "TXN_LMT_AMT, DLY_LMT_AMT, DLY_LMT_VOL, MLY_LMT_AMT, MLY_LMT_VOL, SYY_LMT_AMT, YLY_LMT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCRDLT_RD.where = "CARD_NO = :DCRCRDLT.KEY.CARD_NO "
            + "AND REGN_TYP = :DCRCRDLT.KEY.REGN_TYP "
            + "AND CHNL_NO = :DCRCRDLT.KEY.CHNL_NO "
            + "AND TXN_TYPE = :DCRCRDLT.KEY.TXN_TYPE "
            + "AND LMT_CCY = :DCRCRDLT.KEY.LMT_CCY "
            + "AND STA_DT = :DCRCRDLT.STA_DT "
            + "AND END_DT = :DCRCRDLT.END_DT";
        DCTCRDLT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRCRDLT, this, DCTCRDLT_RD);
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
        if (DCCS1191.VAL.CARD_LNK_NO.trim().length() == 0) {
            DCRCRDLT.KEY.CARD_NO = "%%%%%%%%%%%%%%%%%%%";
        } else {
            DCRCRDLT.KEY.CARD_NO = DCCS1191.VAL.CARD_LNK_NO;
        }
        if (DCCS1191.VAL.KEY.REGN_TYP == ' ') {
            DCRCRDLT.KEY.REGN_TYP = ALL.charAt(0);
        } else {
            DCRCRDLT.KEY.REGN_TYP = DCCS1191.VAL.KEY.REGN_TYP;
        }
        if (DCCS1191.VAL.KEY.CHNL_NO.trim().length() == 0) {
            DCRCRDLT.KEY.CHNL_NO = "%%%%%";
        } else {
            DCRCRDLT.KEY.CHNL_NO = DCCS1191.VAL.KEY.CHNL_NO;
        }
        if (DCCS1191.VAL.KEY.TXN_TYPE.trim().length() == 0) {
            DCRCRDLT.KEY.TXN_TYPE = "%%";
        } else {
            DCRCRDLT.KEY.TXN_TYPE = DCCS1191.VAL.KEY.TXN_TYPE;
        }
        if (DCCS1191.VAL.KEY.LMT_CCY.trim().length() == 0) {
            DCRCRDLT.KEY.LMT_CCY = "%%%";
        } else {
            DCRCRDLT.KEY.LMT_CCY = DCCS1191.VAL.KEY.LMT_CCY;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
