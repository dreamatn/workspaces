package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBCAP {
    int JIBS_tmp_int;
    brParm DCTCDAPP_BR = new brParm();
    DBParm DCTCDORD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    String K_TBL_CDAPP = "DCTCDAPP";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSBCAP_WS_BRW_OUTPUT WS_BRW_OUTPUT = new DCZSBCAP_WS_BRW_OUTPUT();
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRGST BPCPRGST = new BPCPRGST();
    DCRCDAPP DCRCDAPP = new DCRCDAPP();
    DCRCDORD DCRCDORD = new DCRCDORD();
    int WS_BR_LOW = 0;
    int WS_BR_HIGH = 0;
    int WS_S_DT = 0;
    int WS_E_DT = 0;
    String WS_APP_BAT_NO_LOW = " ";
    String WS_APP_BAT_NO_HIGH = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSBCAP DCCSBCAP;
    public void MP(SCCGWA SCCGWA, DCCSBCAP DCCSBCAP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBCAP = DCCSBCAP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBCAP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSER_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDAPP);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.PROD_CD);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.CARD_CLS_CD);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.APP_BR);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.APP_S_DT);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.APP_E_DT);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.APP_BAT_NO);
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.APP_STS);
        if (DCCSBCAP.VAL.PROD_CD.trim().length() == 0) {
            DCRCDAPP.PROD_CD = "%%%%%%%%%%";
        } else {
            DCRCDAPP.PROD_CD = DCCSBCAP.VAL.PROD_CD;
        }
        if (DCCSBCAP.VAL.CARD_CLS_CD.trim().length() == 0) {
            DCRCDAPP.CARD_CLS_CD = "%%%%%%%%%%";
        } else {
            DCRCDAPP.CARD_CLS_CD = DCCSBCAP.VAL.CARD_CLS_CD;
        }
        if (DCCSBCAP.VAL.APP_BR == 0) {
            WS_BR_LOW = 0;
            WS_BR_HIGH = 999999999;
        } else {
            WS_BR_LOW = DCCSBCAP.VAL.APP_BR;
            WS_BR_HIGH = DCCSBCAP.VAL.APP_BR;
        }
        if (DCCSBCAP.VAL.APP_S_DT == 0) {
            WS_S_DT = 101;
        } else {
            WS_S_DT = DCCSBCAP.VAL.APP_S_DT;
        }
        if (DCCSBCAP.VAL.APP_E_DT == 0) {
            WS_E_DT = 99991231;
        } else {
            WS_E_DT = DCCSBCAP.VAL.APP_E_DT;
        }
        if (DCCSBCAP.VAL.APP_BAT_NO.trim().length() == 0) {
            WS_APP_BAT_NO_LOW = "" + 0X00;
            JIBS_tmp_int = WS_APP_BAT_NO_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_APP_BAT_NO_LOW = "0" + WS_APP_BAT_NO_LOW;
            WS_APP_BAT_NO_HIGH = "" + 0XFF;
            JIBS_tmp_int = WS_APP_BAT_NO_HIGH.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_APP_BAT_NO_HIGH = "0" + WS_APP_BAT_NO_HIGH;
        } else {
            WS_APP_BAT_NO_LOW = DCCSBCAP.VAL.APP_BAT_NO;
            WS_APP_BAT_NO_HIGH = DCCSBCAP.VAL.APP_BAT_NO;
        }
        DCRCDAPP.APP_STS = DCCSBCAP.VAL.APP_STS;
        CEP.TRC(SCCGWA, DCRCDAPP.KEY.APP_BAT_NO);
        CEP.TRC(SCCGWA, WS_BR_LOW);
        CEP.TRC(SCCGWA, WS_BR_HIGH);
        CEP.TRC(SCCGWA, DCRCDAPP.PROD_CD);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_CLS_CD);
        CEP.TRC(SCCGWA, DCRCDAPP.APP_STS);
        T000_STARTBR_DCTCDAPP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSBCAP.VAL.PROD_CD);
        T000_READNEXT_DCTCDAPP();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (WS_RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (DCCSBCAP.VAL.APP_BR == 0) {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPRGST.BR2 = DCRCDAPP.APP_BR;
                S000_CALL_BPZPRGST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                CEP.TRC(SCCGWA, DCRCDAPP.APP_BR);
                if (BPCPRGST.LVL_RELATION == 'A' 
                    || (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == DCRCDAPP.APP_BR)) {
                    B022_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                }
            } else {
                B022_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            T000_READNEXT_DCTCDAPP();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCDAPP();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 230;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        CEP.TRC(SCCGWA, DCRCDAPP.KEY.APP_BAT_NO);
        CEP.TRC(SCCGWA, DCRCDAPP.PROD_CD);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_BIN);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_SEG);
        CEP.TRC(SCCGWA, DCRCDAPP.CARD_CLS_CD);
        CEP.TRC(SCCGWA, DCRCDAPP.APP_NUM);
        CEP.TRC(SCCGWA, DCRCDAPP.APP_BR);
        CEP.TRC(SCCGWA, DCRCDAPP.APP_TLR);
        CEP.TRC(SCCGWA, DCRCDAPP.APP_DT);
        CEP.TRC(SCCGWA, DCRCDAPP.APP_STS);
        WS_BRW_OUTPUT.WS_BRW_APP_BAT_NO = DCRCDAPP.KEY.APP_BAT_NO;
        WS_BRW_OUTPUT.WS_BRW_CARD_PROD_CD = DCRCDAPP.PROD_CD;
        WS_BRW_OUTPUT.WS_BRW_CARD_CLS_CD = DCRCDAPP.CARD_CLS_CD;
        WS_BRW_OUTPUT.WS_BRW_CARD_BIN = DCRCDAPP.CARD_BIN;
        WS_BRW_OUTPUT.WS_BRW_CARD_SEG = DCRCDAPP.CARD_SEG;
        WS_BRW_OUTPUT.WS_BRW_SF_FLG = DCRCDAPP.SF_FLG;
        WS_BRW_OUTPUT.WS_BRW_BV_CD_NO = DCRCDAPP.BV_CD_NO;
        WS_BRW_OUTPUT.WS_BRW_APP_NUM = DCRCDAPP.APP_NUM;
        WS_BRW_OUTPUT.WS_BRW_APP_STS = DCRCDAPP.APP_STS;
        WS_BRW_OUTPUT.WS_BRW_APP_BR = DCRCDAPP.APP_BR;
        WS_BRW_OUTPUT.WS_BRW_APP_TLR = DCRCDAPP.APP_TLR;
        WS_BRW_OUTPUT.WS_BRW_APP_DT = DCRCDAPP.APP_DT;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDAPP.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        WS_BRW_OUTPUT.WS_BRW_CARD_PROD_NAME = BPCPQPRD.PRDT_NAME;
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.APP_BAT_NO = DCRCDAPP.KEY.APP_BAT_NO;
        CEP.TRC(SCCGWA, DCRCDORD.APP_BAT_NO);
        T000_READ_DCTCDORD_FIRST_1();
        if (pgmRtn) return;
        WS_BRW_OUTPUT.WS_BEGIN_CARD_NO = DCRCDORD.KEY.CARD_NO;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BEGIN_CARD_NO);
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.APP_BAT_NO = DCRCDAPP.KEY.APP_BAT_NO;
        CEP.TRC(SCCGWA, DCRCDORD.APP_BAT_NO);
        T000_READ_DCTCDORD_FIRST_2();
        if (pgmRtn) return;
        WS_BRW_OUTPUT.WS_END_CARD_NO = DCRCDORD.KEY.CARD_NO;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_END_CARD_NO);
        if (DCRCDAPP.APP_STS != '0') {
            WS_BRW_OUTPUT.WS_CHECK_TLR = DCRCDAPP.UPDTBL_TLR;
            WS_BRW_OUTPUT.WS_CHECK_DT = DCRCDAPP.UPDTBL_DATE;
        }
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 230;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTCDAPP() throws IOException,SQLException,Exception {
        DCTCDAPP_BR.rp = new DBParm();
        DCTCDAPP_BR.rp.TableName = "DCTCDAPP";
        DCTCDAPP_BR.rp.col = "APP_BAT_NO, PROD_CD, CARD_CLS_CD, CARD_BIN, CARD_SEG, BV_CD_NO, SF_FLG, APP_TYP, APP_NUM, APP_STS, APP_DT, APP_TLR, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDAPP_BR.rp.where = "( APP_BAT_NO >= :WS_APP_BAT_NO_LOW "
            + "AND APP_BAT_NO <= :WS_APP_BAT_NO_HIGH ) "
            + "AND PROD_CD LIKE :DCRCDAPP.PROD_CD "
            + "AND CARD_CLS_CD LIKE :DCRCDAPP.CARD_CLS_CD "
            + "AND APP_STS = :DCRCDAPP.APP_STS "
            + "AND APP_BR >= :WS_BR_LOW "
            + "AND APP_BR <= :WS_BR_HIGH "
            + "AND APP_DT >= :WS_S_DT "
            + "AND APP_DT <= :WS_E_DT";
        DCTCDAPP_BR.rp.order = "APP_BAT_NO";
        IBS.STARTBR(SCCGWA, DCRCDAPP, this, DCTCDAPP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDAPP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_READNEXT_DCTCDAPP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDAPP, this, DCTCDAPP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDAPP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTCDAPP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDAPP_BR);
    }
    public void T000_READ_DCTCDORD_FIRST_1() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "APP_BAT_NO = :DCRCDORD.APP_BAT_NO";
        DCTCDORD_RD.fst = true;
        DCTCDORD_RD.order = "CARD_NO";
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_READ_DCTCDORD_FIRST_2() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "APP_BAT_NO = :DCRCDORD.APP_BAT_NO";
        DCTCDORD_RD.fst = true;
        DCTCDORD_RD.order = "CARD_NO DESC";
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
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
