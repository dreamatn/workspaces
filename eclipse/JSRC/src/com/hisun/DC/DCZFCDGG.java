package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZFCDGG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTBINPM_RD;
    boolean pgmRtn = false;
    String BSL_RTC_FLAG = "SIT_GN_20150519_V1";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO = " ";
    short WS_LEN = 0;
    short WS_CNT = 0;
    short WS_CARD_NO_CNT = 0;
    short WS_TEMP1 = 0;
    short WS_TEMP2 = 0;
    short WS_BASE = 0;
    short WS_RESIDUE = 0;
    short WS_RESULT = 0;
    String WS_CARD_NO_L = " ";
    DCZFCDGG_WS_CARD_NO_BIN_L WS_CARD_NO_BIN_L = new DCZFCDGG_WS_CARD_NO_BIN_L();
    char WS_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBINPM DCRBINPM = new DCRBINPM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCFCDGG DCCFCDGG;
    public void MP(SCCGWA SCCGWA, DCCFCDGG DCCFCDGG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCFCDGG = DCCFCDGG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZFCDGG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_CARD_NO_L = DCCFCDGG.VAL.CARD_NO;
        IBS.CPY2CLS(SCCGWA, WS_CARD_NO_L, WS_CARD_NO_BIN_L);
        CEP.TRC(SCCGWA, WS_CARD_NO_L);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (DCCFCDGG.VAL.FUNC == 'C') {
            B200_CHECK_DIGIT();
            if (pgmRtn) return;
        } else if (DCCFCDGG.VAL.FUNC == 'G') {
            B300_GEN_DIGIT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCFCDGG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO.substring(0, 6));
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        DCRBINPM.KEY.BIN = DCCFCDGG.VAL.CARD_NO.substring(0, WS_CARD_NO_BIN_L.WS_CARD_BIN_L);
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        if (DCCFCDGG.VAL.CARD_NO.substring(0, 8).equalsIgnoreCase("62326594")) {
            DCRBINPM.KEY.BIN = "62326594";
        }
        T000_QUERY_DCTBINPM();
        if (pgmRtn) return;
        WS_LEN = DCRBINPM.CARD_LEN;
        CEP.TRC(SCCGWA, WS_LEN);
        if (WS_LEN != 16 
            && WS_LEN != 19) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LEN_INVALID, DCCFCDGG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        if (DCCFCDGG.VAL.FUNC == 'C' 
            && (DCCFCDGG.VAL.CARD_NO.trim().length() == 0 
            || DCCFCDGG.VAL.CARD_NO.substring(WS_LEN - 1, WS_LEN + 1 - 1).trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCFCDGG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO.substring(WS_LEN - 1, WS_LEN + 1 - 1));
        CEP.TRC(SCCGWA, WS_LEN);
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        if (DCCFCDGG.VAL.FUNC == 'G' 
            && (DCCFCDGG.VAL.CARD_NO.trim().length() == 0 
            || DCCFCDGG.VAL.CARD_NO.substring(WS_LEN - 1, WS_LEN + 1 - 1).trim().length() > 0)) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCFCDGG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_DIGIT() throws IOException,SQLException,Exception {
        WS_FLAG = 'N';
        CEP.TRC(SCCGWA, WS_LEN);
        for (WS_CNT = WS_LEN; WS_FLAG != 'Y' 
            && WS_CNT != 0; WS_CNT += -1) {
            CEP.TRC(SCCGWA, WS_CNT);
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1));
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            if (DCCFCDGG.VAL.CARD_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() > 0) {
                WS_FLAG = 'Y';
            }
        }
        if (WS_FLAG != 'Y') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_ERROR, DCCFCDGG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        WS_CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, WS_CNT);
        R000_GEN_DIGIT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        CEP.TRC(SCCGWA, WS_CARD_NO);
        if (!WS_CARD_NO.equalsIgnoreCase(DCCFCDGG.VAL.CARD_NO)) {
            DCCFCDGG.VAL.CHK_RST = 'E';
        } else {
            DCCFCDGG.VAL.CHK_RST = 'N';
        }
    }
    public void B300_GEN_DIGIT() throws IOException,SQLException,Exception {
        WS_CARD_NO = DCCFCDGG.VAL.CARD_NO;
        R000_GEN_DIGIT();
        if (pgmRtn) return;
        DCCFCDGG.VAL.CARD_NO_GEN = WS_CARD_NO;
    }
    public void R000_GEN_DIGIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CARD_NO);
        CEP.TRC(SCCGWA, WS_LEN);
        for (WS_CNT = WS_LEN; WS_CNT != 0; WS_CNT += -1) {
            if (WS_CARD_NO == null) WS_CARD_NO = "";
            JIBS_tmp_int = WS_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARD_NO += " ";
            if (WS_CARD_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() > 0 
                || WS_CARD_NO_CNT > 0) {
                if (WS_CARD_NO == null) WS_CARD_NO = "";
                JIBS_tmp_int = WS_CARD_NO.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARD_NO += " ";
                if (!IBS.isNumeric(WS_CARD_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1))) {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_ERROR, DCCFCDGG.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_CARD_NO_CNT = (short) (WS_CARD_NO_CNT + 1);
                    if (WS_CARD_NO == null) WS_CARD_NO = "";
                    JIBS_tmp_int = WS_CARD_NO.length();
                    for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARD_NO += " ";
                    if (WS_CARD_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() == 0) WS_BASE = 0;
                    else WS_BASE = Short.parseShort(WS_CARD_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1));
                }
            }
            CEP.TRC(SCCGWA, WS_BASE);
            CEP.TRC(SCCGWA, WS_CARD_NO_CNT);
            if (WS_CARD_NO_CNT > 0) {
                CEP.TRC(SCCGWA, "BEFORE-DIVIDE");
                CEP.TRC(SCCGWA, WS_RESIDUE);
                WS_TEMP1 = 0;
                WS_TEMP2 = 0;
                WS_TEMP1 = (short) (WS_CARD_NO_CNT / 2);
                CEP.TRC(SCCGWA, WS_TEMP1);
                WS_TEMP2 = (short) (WS_TEMP1 * 2);
                CEP.TRC(SCCGWA, WS_TEMP2);
                WS_RESIDUE = (short) (WS_CARD_NO_CNT - WS_TEMP2);
                CEP.TRC(SCCGWA, "AFTER-DIVIDE");
                CEP.TRC(SCCGWA, WS_RESIDUE);
                if (WS_RESIDUE == 0) {
                    WS_RESULT = (short) (WS_RESULT + WS_BASE);
                } else {
                    WS_TEMP1 = 0;
                    WS_TEMP2 = 0;
                    WS_TEMP2 = (short) (WS_BASE * 2);
                    CEP.TRC(SCCGWA, WS_TEMP2);
                    JIBS_tmp_str[0] = "" + WS_TEMP2;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(0, 1).trim().length() == 0) WS_TEMP1 = 0;
                    else WS_TEMP1 = Short.parseShort(JIBS_tmp_str[0].substring(0, 1));
                    WS_RESULT = (short) (WS_RESULT + WS_TEMP1);
                    JIBS_tmp_str[0] = "" + WS_TEMP2;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).trim().length() == 0) WS_TEMP1 = 0;
                    else WS_TEMP1 = Short.parseShort(JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1));
                    WS_RESULT = (short) (WS_RESULT + WS_TEMP1);
                }
            }
            CEP.TRC(SCCGWA, WS_RESULT);
        }
        CEP.TRC(SCCGWA, "ADD-OVER");
        CEP.TRC(SCCGWA, WS_RESULT);
        WS_TEMP1 = 0;
        JIBS_tmp_str[0] = "" + WS_RESULT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1).trim().length() == 0) WS_TEMP1 = 0;
        else WS_TEMP1 = Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1));
        if (WS_TEMP1 != 0) {
            WS_TEMP1 = (short) (10 - WS_TEMP1);
        }
        WS_CARD_NO_CNT = (short) (WS_CARD_NO_CNT + 1);
        if (WS_CARD_NO == null) WS_CARD_NO = "";
        JIBS_tmp_int = WS_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARD_NO += " ";
        JIBS_tmp_str[0] = "" + WS_TEMP1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CARD_NO = WS_CARD_NO.substring(0, WS_CARD_NO_CNT - 1) + JIBS_tmp_str[0] + WS_CARD_NO.substring(WS_CARD_NO_CNT + 1 - 1);
        CEP.TRC(SCCGWA, WS_CARD_NO);
    }
    public void T000_QUERY_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "CARD_LEN";
        DCTBINPM_RD.where = "BIN = :DCRBINPM.KEY.BIN";
        IBS.READ(SCCGWA, DCRBINPM, this, DCTBINPM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_BINF_NOTFND, DCCFCDGG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCFCDGG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCFCDGG=");
            CEP.TRC(SCCGWA, DCCFCDGG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
