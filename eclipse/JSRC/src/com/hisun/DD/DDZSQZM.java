package com.hisun.DD;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQZM {
    DBParm DDTCCZM_RD;
    brParm DDTZMAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD155";
    String WS_OPEN_BV = " ";
    String WS_REF_NO = " ";
    String WS_MSGID = " ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_FLAG = ' ';
    char WS_DDTCCZM_REC = ' ';
    char WS_DDTZMAC_REC = ' ';
    DDZSQZM_WS_FMT WS_FMT = new DDZSQZM_WS_FMT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRCCZM DDRCCZM = new DDRCCZM();
    DDRZMAC DDRZMAC = new DDRZMAC();
    SCCGWA SCCGWA;
    DDCSQZM DDCSQZM;
    public void MP(SCCGWA SCCGWA, DDCSQZM DDCSQZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQZM = DDCSQZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_GET_CCZM();
        if (pgmRtn) return;
        B050_GET_ZMAC();
        if (pgmRtn) return;
        B070_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_GET_CCZM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCZM);
        DDRCCZM.KEY.OPEN_BV_CODE = "C00008";
        DDRCCZM.KEY.OPEN_BV = DDCSQZM.INPUT_DATA.IZM_OPEN_BV;
        DDRCCZM.KEY.REF_NO = DDCSQZM.INPUT_DATA.IZM_REF_NO;
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV_CODE);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
        T000_READ_DDTCCZM();
        if (pgmRtn) return;
        if (WS_DDTCCZM_REC == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_BV_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_FMT.WS_CCZM.WS_OZM_OPEN_BV = DDRCCZM.KEY.OPEN_BV;
            WS_FMT.WS_CCZM.WS_OZM_OPEN_DT = DDRCCZM.CRT_DATE;
            WS_FMT.WS_CCZM.WS_OZM_CI_NO = DDRCCZM.CI_NO;
            WS_FMT.WS_CCZM.WS_OZM_OPEN_CNT = DDRCCZM.OPEN_CNT;
            WS_FMT.WS_CCZM.WS_OZM_CH_TLE = DDRCCZM.CH_TLE;
            WS_FMT.WS_CCZM.WS_OZM_EN_TLE = DDRCCZM.EN_TLE;
            WS_FMT.WS_CCZM.WS_OZM_EN_NAME = DDRCCZM.EN_NAME;
            WS_FMT.WS_CCZM.WS_OZM_BAL_TYPE = DDRCCZM.BAL_TYPE;
            WS_FMT.WS_CCZM.WS_OZM_BAL_DATE = DDRCCZM.BAL_DATE;
            WS_FMT.WS_CCZM.WS_OZM_BAL_EXPD = DDRCCZM.BAL_EXPD;
            WS_FMT.WS_CCZM.WS_OZM_TS = DDRCCZM.TS;
        }
    }
    public void B050_GET_ZMAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMAC);
        WS_CNT = 0;
        WS_REF_NO = DDRCCZM.KEY.REF_NO;
        T000_STARTBR_DDTZMAC();
        if (pgmRtn) return;
        WS_FLAG = ' ';
        while (WS_FLAG != 'N') {
            T000_READNEXT_DDTZMAC();
            if (pgmRtn) return;
            if (WS_DDTZMAC_REC == 'Y') {
                WS_CNT = WS_CNT + 1;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_AC = DDRZMAC.AC;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_AC_SEQ = DDRZMAC.AC_SEQ;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_AC_BV_CD = DDRZMAC.AC_BV_CD;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_AC_BV_NO = DDRZMAC.AC_BV_NO;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_HLD_STS = DDRZMAC.HLD_STS;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_HLD_NO = DDRZMAC.HLD_NO;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_CCY = DDRZMAC.CCY;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_CCY_TYPE = DDRZMAC.CCY_TYPE;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_OPEN_AMT = DDRZMAC.OPEN_AMT;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_AC_TYP = DDRZMAC.AC_TYPE;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_VAL_DATE = DDRZMAC.VAL_DATE;
                WS_FMT.WS_AC_LIST[WS_CNT-1].WS_OZM_EXP_DATE = DDRZMAC.EXP_DATE;
            } else {
                WS_FLAG = 'N';
            }
        }
        WS_FMT.WS_CCZM.WS_OZM_AC_CNT = WS_CNT;
        T000_ENDBR_DDTZMAC();
        if (pgmRtn) return;
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 2083;
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
    public void T000_STARTBR_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_BR.rp = new DBParm();
        DDTZMAC_BR.rp.TableName = "DDTZMAC";
        DDTZMAC_BR.rp.where = "REF_NO = :WS_REF_NO";
        DDTZMAC_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTZMAC_BR);
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
