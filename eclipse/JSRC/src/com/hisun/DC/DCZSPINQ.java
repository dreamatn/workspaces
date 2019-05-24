package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPINQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DCTPDBIN_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    DCZSPINQ_WS_SEQ1_MAX WS_SEQ1_MAX = new DCZSPINQ_WS_SEQ1_MAX();
    DCZSPINQ_WS_SEQ1_MIN WS_SEQ1_MIN = new DCZSPINQ_WS_SEQ1_MIN();
    DCZSPINQ_WS_BRW_OUTPUT WS_BRW_OUTPUT = new DCZSPINQ_WS_BRW_OUTPUT();
    String WS_PROD_CD = " ";
    String WS_BIN = " ";
    String WS_CIFNO = " ";
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRPDBIN DCRPDBIN = new DCRPDBIN();
    String WS_PROD_CD_LOW = " ";
    String WS_PROD_CD_HI = " ";
    String WS_CARD_BIN_LOW = " ";
    String WS_CARD_BIN_HI = " ";
    String WS_CARD_SEG1_MIN = " ";
    String WS_CARD_SEG1_MAX = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPINQ DCCSPINQ;
    public void MP(SCCGWA SCCGWA, DCCSPINQ DCCSPINQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPINQ = DCCSPINQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPINQ return!");
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
        B020_GET_PLAN_RECORD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_PLAN_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPDBIN);
        CEP.TRC(SCCGWA, DCCSPINQ.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCSPINQ.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSPINQ.BR);
        if (DCCSPINQ.CARD_PROD_CD.trim().length() > 0) {
            WS_PROD_CD_LOW = DCCSPINQ.CARD_PROD_CD;
            WS_PROD_CD_HI = DCCSPINQ.CARD_PROD_CD;
        } else {
            WS_PROD_CD_LOW = "" + 0X00;
            JIBS_tmp_int = WS_PROD_CD_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_PROD_CD_LOW = "0" + WS_PROD_CD_LOW;
            WS_PROD_CD_HI = "" + 0XFF;
            JIBS_tmp_int = WS_PROD_CD_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_PROD_CD_HI = "0" + WS_PROD_CD_HI;
        }
        if (DCCSPINQ.CARD_BIN.trim().length() > 0) {
            WS_CARD_BIN_LOW = DCCSPINQ.CARD_BIN;
            WS_CARD_BIN_HI = DCCSPINQ.CARD_BIN;
        } else {
            WS_CARD_BIN_LOW = "" + 0X00;
            JIBS_tmp_int = WS_CARD_BIN_LOW.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_CARD_BIN_LOW = "0" + WS_CARD_BIN_LOW;
            WS_CARD_BIN_HI = "" + 0XFF;
            JIBS_tmp_int = WS_CARD_BIN_HI.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_CARD_BIN_HI = "0" + WS_CARD_BIN_HI;
        }
        if (DCCSPINQ.BR == ' ') {
            T000_STARTBR_DCTPDBIN1();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = "" + DCCSPINQ.BR;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<9-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(2 - 1, 2 + 2 - 1).trim().length() == 0) WS_SEQ1_MAX.WS_BR1 = 0;
            else WS_SEQ1_MAX.WS_BR1 = Short.parseShort(JIBS_tmp_str[0].substring(2 - 1, 2 + 2 - 1));
            if (JIBS_tmp_str[0].substring(2 - 1, 2 + 2 - 1).trim().length() == 0) WS_SEQ1_MIN.WS_BR2 = 0;
            else WS_SEQ1_MIN.WS_BR2 = Short.parseShort(JIBS_tmp_str[0].substring(2 - 1, 2 + 2 - 1));
            WS_SEQ1_MIN.WS_ZK2 = 0;
            WS_SEQ1_MAX.WS_ZK1 = 99;
            WS_CARD_SEG1_MAX = IBS.CLS2CPY(SCCGWA, WS_SEQ1_MAX);
            WS_CARD_SEG1_MIN = IBS.CLS2CPY(SCCGWA, WS_SEQ1_MIN);
            CEP.TRC(SCCGWA, WS_SEQ1_MAX.WS_BR1);
            CEP.TRC(SCCGWA, WS_SEQ1_MAX.WS_BR1);
            CEP.TRC(SCCGWA, WS_SEQ1_MAX);
            CEP.TRC(SCCGWA, WS_CARD_SEG1_MAX);
            T000_STARTBR_DCTPDBIN2();
            if (pgmRtn) return;
        }
        T000_READNEXT_DCTPDBIN();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, DCCSPINQ.FUNC);
            if (DCCSPINQ.FUNC == 'B') {
                B022_OUTPUT_DETAIL();
                if (pgmRtn) return;
            } else if (DCCSPINQ.FUNC == 'Q') {
                if (!DCRPDBIN.KEY.CARD_PROD_CD.equalsIgnoreCase(WS_PROD_CD) 
                    || !DCRPDBIN.KEY.CARD_BIN.equalsIgnoreCase(WS_BIN)) {
                    B022_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                    WS_PROD_CD = DCRPDBIN.KEY.CARD_PROD_CD;
                    WS_BIN = DCRPDBIN.KEY.CARD_BIN;
                }
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCSPINQ.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_DCTPDBIN();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTPDBIN();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 146;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_CARD_PROD_CD = DCRPDBIN.KEY.CARD_PROD_CD;
        WS_BRW_OUTPUT.WS_BRW_CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
        WS_BRW_OUTPUT.WS_BRW_CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
        WS_BRW_OUTPUT.WS_BRW_SEQ = DCRPDBIN.KEY.SEQ;
        WS_BRW_OUTPUT.WS_BRW_CARD_SEG1_RULE = DCRPDBIN.CARD_SEG1_RUL;
        WS_BRW_OUTPUT.WS_BRW_S_SEQ = DCRPDBIN.S_SEQ;
        WS_BRW_OUTPUT.WS_BRW_E_SEQ = DCRPDBIN.E_SEQ;
        WS_BRW_OUTPUT.WS_BRW_LAST_UPD_TLR = DCRPDBIN.UPDTBL_TLR;
        WS_BRW_OUTPUT.WS_BRW_LAST_UPD_DT = DCRPDBIN.UPDTBL_DATE;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRPDBIN.KEY.CARD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        WS_BRW_OUTPUT.WS_BRW_CARD_PROD_NAME = BPCPQPRD.PRDT_NAME;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 146;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTPDBIN1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        DCTPDBIN_BR.rp = new DBParm();
        DCTPDBIN_BR.rp.TableName = "DCTPDBIN";
        DCTPDBIN_BR.rp.where = "( CARD_PROD_CD >= :WS_PROD_CD_LOW ) "
            + "AND ( CARD_PROD_CD <= :WS_PROD_CD_HI ) "
            + "AND ( CARD_BIN >= :WS_CARD_BIN_LOW ) "
            + "AND ( CARD_BIN <= :WS_CARD_BIN_HI )";
        IBS.STARTBR(SCCGWA, DCRPDBIN, this, DCTPDBIN_BR);
    }
    public void T000_STARTBR_DCTPDBIN2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        DCTPDBIN_BR.rp = new DBParm();
        DCTPDBIN_BR.rp.TableName = "DCTPDBIN";
        DCTPDBIN_BR.rp.where = "( CARD_PROD_CD >= :WS_PROD_CD_LOW ) "
            + "AND ( CARD_PROD_CD <= :WS_PROD_CD_HI ) "
            + "AND ( CARD_BIN >= :WS_CARD_BIN_LOW ) "
            + "AND ( CARD_BIN <= :WS_CARD_BIN_HI ) "
            + "AND ( CARD_SEG1 >= :WS_CARD_SEG1_MIN ) "
            + "AND ( CARD_SEG1 <= :WS_CARD_SEG1_MAX )";
        IBS.STARTBR(SCCGWA, DCRPDBIN, this, DCTPDBIN_BR);
    }
    public void T000_READNEXT_DCTPDBIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRPDBIN, this, DCTPDBIN_BR);
    }
    public void T000_ENDBR_DCTPDBIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTPDBIN_BR);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
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
