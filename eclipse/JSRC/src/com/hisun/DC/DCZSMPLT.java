package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSMPLT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTPRDLT_RD;
    brParm DCTPRDLT_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC053";
    int K_MAX_ROW = 50;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "CARD PRODUCT TRANS LIMIT INFO. MAINTAIN";
    String K_HIS_COPYBOOK = "DCRPRDLT";
    String K_TBL_PRDLT = "DCTPRDLT";
    double K_MAX_AMT = 99999999999999.99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSMPLT_WS_OUT_VAL WS_OUT_VAL = new DCZSMPLT_WS_OUT_VAL();
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPRDLT DCRPRDLT = new DCRPRDLT();
    DCRPRDLT DCRPRDLO = new DCRPRDLT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    String WS_PROD_CD_LOW = " ";
    String WS_PROD_CD_HI = " ";
    char WS_REGN_TYP_LOW = ' ';
    char WS_REGN_TYP_HI = ' ';
    String WS_CHNL_NO_LOW = " ";
    String WS_CHNL_NO_HI = " ";
    String WS_TXN_TYPE_LOW = " ";
    String WS_TXN_TYPE_HI = " ";
    String WS_LMT_CCY_LOW = " ";
    String WS_LMT_CCY_HI = " ";
    char WS_AC_CLASS_LOW = ' ';
    char WS_AC_CLASS_HI = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSMPLT DCCSMPLT;
    public void MP(SCCGWA SCCGWA, DCCSMPLT DCCSMPLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSMPLT = DCCSMPLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSMPLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCRPRDLT);
        IBS.init(SCCGWA, DCRPRDLO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCSMPLT.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSMPLT.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B002_CHECK_PRODCD();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSMPLT.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSMPLT.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSMPLT.FUNC == 'B') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.TXN_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.DLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.MLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.MLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.SYY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.YLY_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.DLY_LMT_VOL);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.SE_LMT_AMT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.START_DT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.START_TM);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.END_DT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.END_TM);
        if (DCCSMPLT.VAL.KEY.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_MUST_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 9);
        }
        if ((DCCSMPLT.VAL.TXN_LMT_AMT > DCCSMPLT.VAL.DLY_LMT_AMT) 
            && DCCSMPLT.VAL.TXN_LMT_AMT != 0 
            && DCCSMPLT.VAL.DLY_LMT_AMT != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TX_LMT_GT_DAILY_LMT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 15);
        }
        if ((DCCSMPLT.VAL.DLY_LMT_AMT > DCCSMPLT.VAL.MLY_LMT_AMT) 
            && DCCSMPLT.VAL.MLY_LMT_AMT != 0 
            && DCCSMPLT.VAL.DLY_LMT_AMT != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DAILY_GT_MONTHLY;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 16);
        }
        if ((DCCSMPLT.VAL.MLY_LMT_AMT > DCCSMPLT.VAL.SYY_LMT_AMT) 
            && DCCSMPLT.VAL.MLY_LMT_AMT != 0 
            && DCCSMPLT.VAL.SYY_LMT_AMT != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MONTHLY_GT_SEMIYEAR;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 18);
        }
        if ((DCCSMPLT.VAL.SYY_LMT_AMT > DCCSMPLT.VAL.YLY_LMT_AMT) 
            && DCCSMPLT.VAL.YLY_LMT_AMT != 0 
            && DCCSMPLT.VAL.SYY_LMT_AMT != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEMIYEAR_GT_YEAR;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 20);
        }
        if ((DCCSMPLT.VAL.START_DT > DCCSMPLT.VAL.END_DT)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_S_TIME_GT_D_TIME;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 20);
        }
        if ((DCCSMPLT.VAL.DLY_LMT_VOL > DCCSMPLT.VAL.MLY_LMT_VOL) 
            && DCCSMPLT.VAL.DLY_LMT_VOL != 0 
            && DCCSMPLT.VAL.MLY_LMT_VOL != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DAYT_GT_MONT_LMT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 16);
        }
    }
    public void B002_CHECK_PRODCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCSMPLT.VAL.KEY.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
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
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        DCRPRDLT.KEY.PROD_CD = DCCSMPLT.VAL.KEY.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCSMPLT.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCSMPLT.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCSMPLT.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCSMPLT.VAL.KEY.LMT_CCY;
        DCRPRDLT.KEY.AC_CLASS = DCCSMPLT.VAL.KEY.AC_CLASS;
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.LMT_CCY);
        CEP.TRC(SCCGWA, DCRPRDLT.KEY.AC_CLASS);
        T000_READ_DCTPRDLT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCZSMPLT_WS2);
        if (WS_TBL_FLAG == 'N') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        CEP.TRC(SCCGWA, DCCSMPLT.VAL);
        DCRPRDLT.KEY.PROD_CD = DCCSMPLT.VAL.KEY.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCSMPLT.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCSMPLT.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCSMPLT.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCSMPLT.VAL.KEY.LMT_CCY;
        DCRPRDLT.KEY.AC_CLASS = DCCSMPLT.VAL.KEY.AC_CLASS;
        if (DCCSMPLT.VAL.TXN_LMT_AMT == 0) {
            DCRPRDLT.TXN_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.TXN_LMT_AMT = DCCSMPLT.VAL.TXN_LMT_AMT;
        }
        if (DCCSMPLT.VAL.DLY_LMT_AMT == 0) {
            DCRPRDLT.DLY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.DLY_LMT_AMT = DCCSMPLT.VAL.DLY_LMT_AMT;
        }
        if (DCCSMPLT.VAL.DLY_LMT_VOL == 0) {
            DCRPRDLT.DLY_LMT_VOL = 99999;
        } else {
            DCRPRDLT.DLY_LMT_VOL = DCCSMPLT.VAL.DLY_LMT_VOL;
        }
        if (DCCSMPLT.VAL.MLY_LMT_VOL == 0) {
            DCRPRDLT.MLY_LMT_VOL = 99999;
        } else {
            DCRPRDLT.MLY_LMT_VOL = DCCSMPLT.VAL.MLY_LMT_VOL;
        }
        if (DCCSMPLT.VAL.MLY_LMT_AMT == 0) {
            DCRPRDLT.MLY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.MLY_LMT_AMT = DCCSMPLT.VAL.MLY_LMT_AMT;
        }
        if (DCCSMPLT.VAL.SE_LMT_AMT == 0) {
            DCRPRDLT.SE_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.SE_LMT_AMT = DCCSMPLT.VAL.SE_LMT_AMT;
        }
        DCRPRDLT.STA_DT = DCCSMPLT.VAL.START_DT;
        DCRPRDLT.STA_TM = DCCSMPLT.VAL.START_TM;
        DCRPRDLT.END_DT = DCCSMPLT.VAL.END_DT;
        DCRPRDLT.END_TM = DCCSMPLT.VAL.END_TM;
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.MLY_LMT_AMT);
        if (DCCSMPLT.VAL.SYY_LMT_AMT == 0) {
            DCRPRDLT.SYY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.SYY_LMT_AMT = DCCSMPLT.VAL.SYY_LMT_AMT;
        }
        CEP.TRC(SCCGWA, DCCSMPLT.VAL.YLY_LMT_AMT);
        if (DCCSMPLT.VAL.YLY_LMT_AMT == 0) {
            DCRPRDLT.YLY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.YLY_LMT_AMT = DCCSMPLT.VAL.YLY_LMT_AMT;
        }
        DCRPRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRPRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTPRDLT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        DCRPRDLT.KEY.PROD_CD = DCCSMPLT.VAL.KEY.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCSMPLT.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCSMPLT.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCSMPLT.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCSMPLT.VAL.KEY.LMT_CCY;
        DCRPRDLT.KEY.AC_CLASS = DCCSMPLT.VAL.KEY.AC_CLASS;
        DCRPRDLT.STA_DT = DCCSMPLT.VAL.START_DT;
        DCRPRDLT.STA_TM = DCCSMPLT.VAL.START_TM;
        DCRPRDLT.END_DT = DCCSMPLT.VAL.END_DT;
        DCRPRDLT.END_TM = DCCSMPLT.VAL.END_TM;
        T000_READ_DCTPRDLT_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCRPRDLT, DCRPRDLO);
        DCRPRDLT.KEY.PROD_CD = DCCSMPLT.VAL.KEY.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCSMPLT.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCSMPLT.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCSMPLT.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCSMPLT.VAL.KEY.LMT_CCY;
        DCRPRDLT.KEY.AC_CLASS = DCCSMPLT.VAL.KEY.AC_CLASS;
        if (DCCSMPLT.VAL.TXN_LMT_AMT == 0) {
            DCRPRDLT.TXN_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.TXN_LMT_AMT = DCCSMPLT.VAL.TXN_LMT_AMT;
        }
        if (DCCSMPLT.VAL.DLY_LMT_AMT == 0) {
            DCRPRDLT.DLY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.DLY_LMT_AMT = DCCSMPLT.VAL.DLY_LMT_AMT;
        }
        if (DCCSMPLT.VAL.DLY_LMT_VOL == 0) {
            DCRPRDLT.DLY_LMT_VOL = 99999;
        } else {
            DCRPRDLT.DLY_LMT_VOL = DCCSMPLT.VAL.DLY_LMT_VOL;
        }
        if (DCCSMPLT.VAL.MLY_LMT_VOL == 0) {
            DCRPRDLT.MLY_LMT_VOL = 99999;
        } else {
            DCRPRDLT.MLY_LMT_VOL = DCCSMPLT.VAL.MLY_LMT_VOL;
        }
        if (DCCSMPLT.VAL.MLY_LMT_AMT == 0) {
            DCRPRDLT.MLY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.MLY_LMT_AMT = DCCSMPLT.VAL.MLY_LMT_AMT;
        }
        if (DCCSMPLT.VAL.SYY_LMT_AMT == 0) {
            DCRPRDLT.SYY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.SYY_LMT_AMT = DCCSMPLT.VAL.SYY_LMT_AMT;
        }
        if (DCCSMPLT.VAL.YLY_LMT_AMT == 0) {
            DCRPRDLT.YLY_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.YLY_LMT_AMT = DCCSMPLT.VAL.YLY_LMT_AMT;
        }
        if (DCCSMPLT.VAL.SE_LMT_AMT == 0) {
            DCRPRDLT.SE_LMT_AMT = K_MAX_AMT;
        } else {
            DCRPRDLT.SE_LMT_AMT = DCCSMPLT.VAL.SE_LMT_AMT;
        }
        DCRPRDLT.STA_DT = DCCSMPLT.VAL.START_DT;
        DCRPRDLT.STA_TM = DCCSMPLT.VAL.START_TM;
        DCRPRDLT.END_DT = DCCSMPLT.VAL.END_DT;
        DCRPRDLT.END_TM = DCCSMPLT.VAL.END_TM;
        DCRPRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTPRDLT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        DCRPRDLT.KEY.PROD_CD = DCCSMPLT.VAL.KEY.PROD_CD;
        DCRPRDLT.KEY.REGN_TYP = DCCSMPLT.VAL.KEY.REGN_TYP;
        DCRPRDLT.KEY.CHNL_NO = DCCSMPLT.VAL.KEY.CHNL_NO;
        DCRPRDLT.KEY.TXN_TYPE = DCCSMPLT.VAL.KEY.TXN_TYPE;
        DCRPRDLT.KEY.LMT_CCY = DCCSMPLT.VAL.KEY.LMT_CCY;
        DCRPRDLT.KEY.AC_CLASS = DCCSMPLT.VAL.KEY.AC_CLASS;
        DCRPRDLT.STA_DT = DCCSMPLT.VAL.START_DT;
        DCRPRDLT.STA_TM = DCCSMPLT.VAL.START_TM;
        DCRPRDLT.END_DT = DCCSMPLT.VAL.END_DT;
        DCRPRDLT.END_TM = DCCSMPLT.VAL.END_TM;
        T000_READ_DCTPRDLT_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DCRPRDLT, DCRPRDLO);
        T000_DELETE_DCTPRDLT();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        if (DCCSMPLT.VAL.KEY.PROD_CD.trim().length() == 0) {
            WS_PROD_CD_LOW = "" + 0X00;
            JIBS_tmp_int = WS_PROD_CD_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_PROD_CD_LOW = "0" + WS_PROD_CD_LOW;
            WS_PROD_CD_HI = "" + 0XFF;
            JIBS_tmp_int = WS_PROD_CD_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_PROD_CD_HI = "0" + WS_PROD_CD_HI;
        } else {
            WS_PROD_CD_LOW = DCCSMPLT.VAL.KEY.PROD_CD;
            WS_PROD_CD_HI = DCCSMPLT.VAL.KEY.PROD_CD;
        }
        if (DCCSMPLT.VAL.KEY.REGN_TYP == ' ') {
            WS_REGN_TYP_LOW = 0X00;
            WS_REGN_TYP_HI = 0XFF;
        } else {
            WS_REGN_TYP_LOW = DCCSMPLT.VAL.KEY.REGN_TYP;
            WS_REGN_TYP_HI = DCCSMPLT.VAL.KEY.REGN_TYP;
        }
        if (DCCSMPLT.VAL.KEY.CHNL_NO.trim().length() == 0) {
            WS_CHNL_NO_LOW = "" + 0X00;
            JIBS_tmp_int = WS_CHNL_NO_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_CHNL_NO_LOW = "0" + WS_CHNL_NO_LOW;
            WS_CHNL_NO_HI = "" + 0XFF;
            JIBS_tmp_int = WS_CHNL_NO_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_CHNL_NO_HI = "0" + WS_CHNL_NO_HI;
        } else {
            WS_CHNL_NO_LOW = DCCSMPLT.VAL.KEY.CHNL_NO;
            WS_CHNL_NO_HI = DCCSMPLT.VAL.KEY.CHNL_NO;
        }
        if (DCCSMPLT.VAL.KEY.TXN_TYPE.trim().length() == 0) {
            WS_TXN_TYPE_LOW = "" + 0X00;
            JIBS_tmp_int = WS_TXN_TYPE_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_TXN_TYPE_LOW = "0" + WS_TXN_TYPE_LOW;
            WS_TXN_TYPE_HI = "" + 0XFF;
            JIBS_tmp_int = WS_TXN_TYPE_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_TXN_TYPE_HI = "0" + WS_TXN_TYPE_HI;
        } else {
            WS_TXN_TYPE_LOW = DCCSMPLT.VAL.KEY.TXN_TYPE;
            WS_TXN_TYPE_HI = DCCSMPLT.VAL.KEY.TXN_TYPE;
        }
        if (DCCSMPLT.VAL.KEY.LMT_CCY.trim().length() == 0) {
            WS_LMT_CCY_LOW = "" + 0X00;
            JIBS_tmp_int = WS_LMT_CCY_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_LMT_CCY_LOW = "0" + WS_LMT_CCY_LOW;
            WS_LMT_CCY_HI = "" + 0XFF;
            JIBS_tmp_int = WS_LMT_CCY_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_LMT_CCY_HI = "0" + WS_LMT_CCY_HI;
        } else {
            WS_LMT_CCY_LOW = DCCSMPLT.VAL.KEY.LMT_CCY;
            WS_LMT_CCY_HI = DCCSMPLT.VAL.KEY.LMT_CCY;
        }
        if (DCCSMPLT.VAL.KEY.AC_CLASS == ' ') {
            WS_AC_CLASS_LOW = 0X00;
            WS_AC_CLASS_HI = 0XFF;
        } else {
            WS_AC_CLASS_LOW = DCCSMPLT.VAL.KEY.AC_CLASS;
            WS_AC_CLASS_HI = DCCSMPLT.VAL.KEY.AC_CLASS;
        }
        T000_STARTBR_DCTPRDLT();
        if (pgmRtn) return;
        T000_READNEXT_DCTPRDLT();
        if (pgmRtn) return;
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B040_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            WS_CNT += 1;
            T000_READNEXT_DCTPRDLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTPRDLT();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 177;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_VAL);
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_PROD_CD = DCRPRDLT.KEY.PROD_CD;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_REGN_TYP = DCRPRDLT.KEY.REGN_TYP;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_CHNL_NO = DCRPRDLT.KEY.CHNL_NO;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_TXN_TYPE = DCRPRDLT.KEY.TXN_TYPE;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_LMT_CCY = DCRPRDLT.KEY.LMT_CCY;
        WS_OUT_VAL.WS_OUT_TXN_LMT_AMT = DCRPRDLT.TXN_LMT_AMT;
        WS_OUT_VAL.WS_OUT_DLY_LMT_AMT = DCRPRDLT.DLY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_DLY_LMT_VOL = DCRPRDLT.DLY_LMT_VOL;
        WS_OUT_VAL.WS_OUT_MLY_LMT_VOL = DCRPRDLT.MLY_LMT_VOL;
        WS_OUT_VAL.WS_OUT_MLY_LMT_AMT = DCRPRDLT.MLY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_SYY_LMT_AMT = DCRPRDLT.SYY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_YLY_LMT_AMT = DCRPRDLT.YLY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_SE_LMT_AMT = DCRPRDLT.SE_LMT_AMT;
        WS_OUT_VAL.WS_OUT_START_DT = DCRPRDLT.STA_DT;
        WS_OUT_VAL.WS_OUT_START_TM = DCRPRDLT.STA_TM;
        WS_OUT_VAL.WS_OUT_END_DT = DCRPRDLT.END_DT;
        WS_OUT_VAL.WS_OUT_END_TM = DCRPRDLT.END_TM;
        WS_OUT_VAL.WS_OUT_UPD_DT = DCRPRDLT.UPDTBL_DATE;
        WS_OUT_VAL.WS_OUT_UPD_TLR = DCRPRDLT.UPDTBL_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_VAL);
        SCCMPAG.DATA_LEN = 177;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 214;
        if (DCCSMPLT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRPRDLT;
        }
        if (DCCSMPLT.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRPRDLO;
        }
        if (DCCSMPLT.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRPRDLO;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRPRDLT;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUT_VAL);
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_PROD_CD = DCRPRDLT.KEY.PROD_CD;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_REGN_TYP = DCRPRDLT.KEY.REGN_TYP;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_CHNL_NO = DCRPRDLT.KEY.CHNL_NO;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_TXN_TYPE = DCRPRDLT.KEY.TXN_TYPE;
        WS_OUT_VAL.WS_OUT_KEY.WS_OUT_LMT_CCY = DCRPRDLT.KEY.LMT_CCY;
        WS_OUT_VAL.WS_OUT_TXN_LMT_AMT = DCRPRDLT.TXN_LMT_AMT;
        WS_OUT_VAL.WS_OUT_DLY_LMT_AMT = DCRPRDLT.DLY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_DLY_LMT_VOL = DCRPRDLT.DLY_LMT_VOL;
        WS_OUT_VAL.WS_OUT_MLY_LMT_VOL = DCRPRDLT.MLY_LMT_VOL;
        WS_OUT_VAL.WS_OUT_MLY_LMT_AMT = DCRPRDLT.MLY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_SYY_LMT_AMT = DCRPRDLT.SYY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_YLY_LMT_AMT = DCRPRDLT.YLY_LMT_AMT;
        WS_OUT_VAL.WS_OUT_SE_LMT_AMT = DCRPRDLT.SE_LMT_AMT;
        WS_OUT_VAL.WS_OUT_START_DT = DCRPRDLT.STA_DT;
        WS_OUT_VAL.WS_OUT_START_TM = DCRPRDLT.STA_TM;
        WS_OUT_VAL.WS_OUT_END_DT = DCRPRDLT.END_DT;
        WS_OUT_VAL.WS_OUT_END_TM = DCRPRDLT.END_TM;
        WS_OUT_VAL.WS_OUT_UPD_DT = DCRPRDLT.UPDTBL_DATE;
        WS_OUT_VAL.WS_OUT_UPD_TLR = DCRPRDLT.UPDTBL_TLR;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_OUT_VAL;
        SCCFMT.DATA_LEN = 177;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        IBS.READ(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        DCTPRDLT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTPRDLT_UPD() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        DCTPRDLT_RD.upd = true;
        IBS.READ(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        DCTPRDLT_RD.col = "TXN_LMT_AMT, DLY_LMT_AMT, DLY_LMT_VOL, MLY_LMT_AMT, MLY_LMT_VOL, SYY_LMT_AMT, YLY_LMT_AMT, SE_LMT_AMT, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPRDLT_RD.where = "PROD_CD = :DCRPRDLT.KEY.PROD_CD "
            + "AND REGN_TYP = :DCRPRDLT.KEY.REGN_TYP "
            + "AND CHNL_NO = :DCRPRDLT.KEY.CHNL_NO "
            + "AND TXN_TYPE = :DCRPRDLT.KEY.TXN_TYPE "
            + "AND LMT_CCY = :DCRPRDLT.KEY.LMT_CCY "
            + "AND AC_CLASS = :DCRPRDLT.KEY.AC_CLASS";
        IBS.REWRITE(SCCGWA, DCRPRDLT, this, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        IBS.DELETE(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_BR.rp = new DBParm();
        DCTPRDLT_BR.rp.TableName = "DCTPRDLT";
        DCTPRDLT_BR.rp.where = "( PROD_CD >= :WS_PROD_CD_LOW ) "
            + "AND ( PROD_CD <= :WS_PROD_CD_HI ) "
            + "AND ( REGN_TYP >= :WS_REGN_TYP_LOW ) "
            + "AND ( REGN_TYP <= :WS_REGN_TYP_HI ) "
            + "AND ( CHNL_NO >= :WS_CHNL_NO_LOW ) "
            + "AND ( CHNL_NO <= :WS_CHNL_NO_HI ) "
            + "AND ( TXN_TYPE >= :WS_TXN_TYPE_LOW ) "
            + "AND ( TXN_TYPE <= :WS_TXN_TYPE_HI ) "
            + "AND ( LMT_CCY >= :WS_LMT_CCY_LOW ) "
            + "AND ( LMT_CCY <= :WS_LMT_CCY_HI ) "
            + "AND ( AC_CLASS >= :WS_AC_CLASS_LOW ) "
            + "AND ( AC_CLASS <= :WS_AC_CLASS_HI )";
        DCTPRDLT_BR.rp.order = "PROD_CD,REGN_TYP,CHNL_NO,TXN_TYPE,LMT_CCY, AC_CLASS";
        IBS.STARTBR(SCCGWA, DCRPRDLT, this, DCTPRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_READNEXT_DCTPRDLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRPRDLT, this, DCTPRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTPRDLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTPRDLT_BR);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
