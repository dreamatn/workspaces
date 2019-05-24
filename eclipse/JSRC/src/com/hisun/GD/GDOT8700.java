package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8700 {
    String JIBS_tmp_str[] = new String[10];
    brParm GDTSTAC_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_ERR_MSG = " ";
    String WS_SP_CI_NO = " ";
    String WS_ACAC_NO = " ";
    int WS_I = 0;
    GDOT8700_WS_OUT_RECODE WS_OUT_RECODE = new GDOT8700_WS_OUT_RECODE();
    GDOT8700_WS_CIAC_D WS_CIAC_D = new GDOT8700_WS_CIAC_D();
    char WS_STAC_FLAG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICSOEC CICSOEC = new CICSOEC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    GDB8700_AWA_8700 GDB8700_AWA_8700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDOT8700 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8700_AWA_8700>");
        GDB8700_AWA_8700 = (GDB8700_AWA_8700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        B020_SEL_STS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8700_AWA_8700.SEL_STS);
        if (GDB8700_AWA_8700.SEL_STS == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUN_FLG_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GDB8700_AWA_8700.CI_NO);
        if (GDB8700_AWA_8700.SEL_STS == '1') {
            if (GDB8700_AWA_8700.CI_NO.trim().length() == 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_CI_M_I;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, GDB8700_AWA_8700.AC_NO);
        if (GDB8700_AWA_8700.SEL_STS == '2') {
            if (GDB8700_AWA_8700.AC_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_SEL_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8700_AWA_8700.SEL_STS);
        if (GDB8700_AWA_8700.SEL_STS == 1) {
            B030_BY_CI_NO();
            if (pgmRtn) return;
        } else if (GDB8700_AWA_8700.SEL_STS == 2) {
            B040_BY_AC_NO();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUN_FLG_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_BY_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8700_AWA_8700.CI_NO);
        IBS.init(SCCGWA, CICSOEC);
        CICSOEC.DATA.CI_NO = GDB8700_AWA_8700.CI_NO;
        CICSOEC.DATA.READ_ONLY_FLG = 'Y';
        S000_CALL_CIZSOEC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
        if (CICSOEC.DATA.SPECIAL_CI_NO.trim().length() > 0) {
            WS_SP_CI_NO = CICSOEC.DATA.SPECIAL_CI_NO;
            IBS.init(SCCGWA, CICQCIAC);
            WS_I = 1;
            while (CICQCIAC.DATA.LAST_FLG != 'Y') {
                CICQCIAC.DATA.CI_NO = WS_SP_CI_NO;
                CICQCIAC.FUNC = '2';
                S000_CALL_CIZQCIAC();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.DATA.ACR_AREA);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CIAC_D);
                CEP.TRC(SCCGWA, WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO);
                WS_ACAC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
                CEP.TRC(SCCGWA, WS_ACAC_NO);
                while (WS_ACAC_NO.trim().length() != 0) {
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.DATA.ACAC_NO = WS_ACAC_NO;
                    CICQACAC.FUNC = 'A';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
                    WS_OUT_RECODE.WS_AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                    WS_OUT_RECODE.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                    WS_OUT_RECODE.WS_AC_TYP = CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR;
                    WS_OUT_RECODE.WS_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
                    R100_OUT_PROC();
                    if (pgmRtn) return;
                    WS_I += 1;
                    WS_ACAC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
                }
            }
        }
    }
    public void B040_BY_AC_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8700_AWA_8700.AC_NO);
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.ST_AC = GDB8700_AWA_8700.AC_NO;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        T000_STARTBR_GDTSTAC_GD();
        if (pgmRtn) return;
        T000_READNEXT_GDTSTAC_GD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC);
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC_SEQ);
        while (WS_STAC_FLAG != 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = GDRSTAC.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRSTAC.KEY.AC_SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            WS_OUT_RECODE.WS_AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_OUT_RECODE.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_OUT_RECODE.WS_AC_TYP = CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR;
            WS_OUT_RECODE.WS_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            R100_OUT_PROC();
            if (pgmRtn) return;
            T000_READNEXT_GDTSTAC_GD();
            if (pgmRtn) return;
        }
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R100_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_RECODE);
        SCCMPAG.DATA_LEN = 52;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_GDTSTAC_GD() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "ST_AC = :GDRSTAC.ST_AC";
        GDTSTAC_BR.rp.order = "AC";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_READNEXT_GDTSTAC_GD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STAC_FLAG = 'F';
        } else {
            WS_STAC_FLAG = 'N';
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
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
