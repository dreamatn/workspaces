package com.hisun.DD;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCCZM {
    DBParm DDTCCZM_RD;
    brParm DDTCCZM_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD542";
    String WS_OPEN_BV = " ";
    String WS_REF_NO = " ";
    short WS_COUNT = 0;
    short WS_CURR_ROW = 0;
    short WS_TOT_PAGE = 0;
    int WS_I = 0;
    int WS_I2 = 0;
    short WS_STR_I = 0;
    short WS_END_I = 0;
    String WS_MSGID = " ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_FLAG = ' ';
    char WS_DDTCCZM_REC = ' ';
    char WS_DDTZMAC_REC = ' ';
    DDZSCCZM_WS_FMT WS_FMT = new DDZSCCZM_WS_FMT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRCCZM DDRCCZM = new DDRCCZM();
    SCCGWA SCCGWA;
    DDCSCCZM DDCSCCZM;
    public void MP(SCCGWA SCCGWA, DDCSCCZM DDCSCCZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCCZM = DDCSCCZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCCZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_REF_NO = DDCSCCZM.INPUT_DATA.IZM_REF_NO;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_GET_CCZM_INFO();
        if (pgmRtn) return;
        B070_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDCSCCZM.INPUT_DATA.IZM_REF_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCCZM.INPUT_DATA.IZM_PAGE_NUM == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCCZM.INPUT_DATA.IZM_PAGE_ROW == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_CCZM_INFO() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCCZM();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCZM();
        if (pgmRtn) return;
        B031_SAVE_HEAD_INFO();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I2 != WS_FMT.WS_CCZM.WS_OZM_CURR_ROW) {
            WS_I += 1;
            CEP.TRC(SCCGWA, WS_I);
            if (WS_I > WS_STR_I 
                && WS_I <= WS_END_I) {
                WS_I2 += 1;
                B032_SAVE_BV_INFO();
                if (pgmRtn) return;
            }
            T000_READNEXT_DDTCCZM();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCZM();
        if (pgmRtn) return;
    }
    public void B031_SAVE_HEAD_INFO() throws IOException,SQLException,Exception {
        WS_FMT.WS_CCZM.WS_OZM_REF_NO = DDRCCZM.KEY.REF_NO;
        WS_FMT.WS_CCZM.WS_OZM_BAL_TYPE = DDRCCZM.BAL_TYPE;
        WS_FMT.WS_CCZM.WS_OZM_BAL_DATE = DDRCCZM.BAL_DATE;
        WS_FMT.WS_CCZM.WS_OZM_BAL_EXPD = DDRCCZM.BAL_EXPD;
        WS_FMT.WS_CCZM.WS_OZM_OPEN_CNT = DDRCCZM.OPEN_CNT;
        B040_GET_PAGE_INFO();
        if (pgmRtn) return;
        WS_FMT.WS_CCZM.WS_OZM_NUM = WS_COUNT;
        WS_FMT.WS_CCZM.WS_OZM_CURR_PAGE = DDCSCCZM.INPUT_DATA.IZM_PAGE_NUM;
        if (WS_CURR_ROW == 0) {
            WS_FMT.WS_CCZM.WS_OZM_TOT_PAGE = WS_TOT_PAGE;
        } else {
            WS_TOT_PAGE += 1;
            WS_FMT.WS_CCZM.WS_OZM_TOT_PAGE = WS_TOT_PAGE;
        }
        if (WS_TOT_PAGE == DDCSCCZM.INPUT_DATA.IZM_PAGE_NUM) {
            WS_FMT.WS_CCZM.WS_OZM_LAST_IND = 'Y';
            if (WS_CURR_ROW == 0) {
                WS_FMT.WS_CCZM.WS_OZM_CURR_ROW = 10;
            } else {
                WS_FMT.WS_CCZM.WS_OZM_CURR_ROW = WS_CURR_ROW;
            }
        } else {
            WS_FMT.WS_CCZM.WS_OZM_LAST_IND = 'N';
            WS_FMT.WS_CCZM.WS_OZM_CURR_ROW = 10;
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_TOT_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_LAST_IND);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_CURR_ROW);
        WS_STR_I = (short) (( WS_FMT.WS_CCZM.WS_OZM_CURR_PAGE - 1 ) * DDCSCCZM.INPUT_DATA.IZM_PAGE_ROW);
        WS_END_I = (short) (WS_STR_I + WS_FMT.WS_CCZM.WS_OZM_CURR_ROW);
        CEP.TRC(SCCGWA, WS_STR_I);
        CEP.TRC(SCCGWA, WS_END_I);
    }
    public void B040_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        T000_GET_COUNT_DDTCCZM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
        CEP.TRC(SCCGWA, DDCSCCZM.INPUT_DATA.IZM_PAGE_ROW);
        WS_CURR_ROW = (short) (WS_COUNT % DDCSCCZM.INPUT_DATA.IZM_PAGE_ROW);
        WS_TOT_PAGE = (short) ((WS_COUNT - WS_CURR_ROW) / DDCSCCZM.INPUT_DATA.IZM_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_TOT_PAGE);
        CEP.TRC(SCCGWA, WS_CURR_ROW);
    }
    public void B032_SAVE_BV_INFO() throws IOException,SQLException,Exception {
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_OPEN_BV = DDRCCZM.KEY.OPEN_BV;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_STS = DDRCCZM.STS;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_CI_NO = DDRCCZM.CI_NO;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_TOTAL_OPEN_AMT = DDRCCZM.TOTAL_OPEN_AMT;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_CH_TLE = DDRCCZM.CH_TLE;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_EN_TLE = DDRCCZM.EN_TLE;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_EN_NAME = DDRCCZM.EN_NAME;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_BANK_NO = DDRCCZM.BANK_NO;
        WS_FMT.WS_AC_LIST[WS_I2-1].WS_OZM_CRT_DATE = DDRCCZM.CRT_DATE;
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_TOT_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_LAST_IND);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_CURR_ROW);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_REF_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_BAL_TYPE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_BAL_DATE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_BAL_EXPD);
        CEP.TRC(SCCGWA, WS_FMT.WS_CCZM.WS_OZM_OPEN_CNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 8290;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.READ(SCCGWA, DDRCCZM, DDTCCZM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCZM_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "REF_NO = :WS_REF_NO";
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
    }
    public void T000_READNEXT_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCZM_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCZM_BR);
    }
    public void T000_GET_COUNT_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.set = "WS-COUNT=COUNT(*)";
        DDTCCZM_RD.where = "REF_NO = :WS_REF_NO";
        IBS.GROUP(SCCGWA, DDRCCZM, this, DDTCCZM_RD);
        CEP.TRC(SCCGWA, WS_COUNT);
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
