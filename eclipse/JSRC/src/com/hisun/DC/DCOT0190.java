package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0190 {
    brParm DCTINRCD_BR = new brParm();
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_DCZSSWLW = "DC-S-DCZSSWLW";
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_RMK = "ORAL REPORTING OF LOSE";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    DCOT0190_WS_CUST_INFO WS_CUST_INFO = new DCOT0190_WS_CUST_INFO();
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCOT0190_WS_OUTPUT WS_OUTPUT = new DCOT0190_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICCUST CICCUST = new CICCUST();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    int WS_DB_START_DATE = 0;
    int WS_DB_END_DATE = 0;
    String WS_DB_INCD_TYPE = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB0190_AWA_0190 DCB0190_AWA_0190;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT0190 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0190_AWA_0190>");
        DCB0190_AWA_0190 = (DCB0190_AWA_0190) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CRD_INCD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0190_AWA_0190.START_DT);
        CEP.TRC(SCCGWA, DCB0190_AWA_0190.END_DT);
        CEP.TRC(SCCGWA, DCB0190_AWA_0190.INCD_TYP);
        CEP.TRC(SCCGWA, DCB0190_AWA_0190.CARD_NO);
        if (DCB0190_AWA_0190.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.KEY.CARD_NO = DCB0190_AWA_0190.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B020_GET_CRD_INCD() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_OWN_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_OWN_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
        WS_CUST_INFO.WS_OWN_CUST_INFO.WS_OWN_CNM = CICCUST.O_DATA.O_CI_NM;
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_CUST_INFO.WS_HLDR_CUST_INFO.WS_HLDR_CNM = CICCUST.O_DATA.O_CI_NM;
        }
        DCRINRCD.KEY.CARD_NO = DCB0190_AWA_0190.CARD_NO;
        if (DCB0190_AWA_0190.START_DT > DCB0190_AWA_0190.END_DT 
            && DCB0190_AWA_0190.END_DT != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_START_END_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCB0190_AWA_0190.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_STR_DATE_LT_CNT_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_DB_START_DATE = DCB0190_AWA_0190.START_DT;
        if (DCB0190_AWA_0190.START_DT == 0) {
            WS_DB_START_DATE = 0;
        }
        WS_DB_END_DATE = DCB0190_AWA_0190.END_DT;
        if (DCB0190_AWA_0190.END_DT == 0) {
            WS_DB_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (WS_DB_END_DATE > 99991231) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_END_DATE_IS_99991231;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCB0190_AWA_0190.INCD_TYP == ' ') {
            WS_DB_INCD_TYPE = "%%";
        } else if (DCB0190_AWA_0190.INCD_TYP == '1') {
            WS_DB_INCD_TYPE = "01";
        } else if (DCB0190_AWA_0190.INCD_TYP == '2') {
            WS_DB_INCD_TYPE = "02";
        } else if (DCB0190_AWA_0190.INCD_TYP == '3') {
            WS_DB_INCD_TYPE = "04";
        } else {
        }
        T000_STARTBR_DCTINRCD();
        if (pgmRtn) return;
        T000_READNEXT_DCTINRCD();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B022_OUTPUT_DETAIL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            T000_READNEXT_DCTINRCD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTINRCD();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 590;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        if (DCRINRCD.KEY.INCD_TYPE.equalsIgnoreCase("01")) {
            WS_OUTPUT.WS_INCD_TYP = '1';
        } else if (DCRINRCD.KEY.INCD_TYPE.equalsIgnoreCase("02")) {
            WS_OUTPUT.WS_INCD_TYP = '2';
        } else if (DCRINRCD.KEY.INCD_TYPE.equalsIgnoreCase("04")) {
            WS_OUTPUT.WS_INCD_TYP = '3';
        } else {
        }
        WS_OUTPUT.WS_INCD_DATE = DCRINRCD.CRT_DATE;
        WS_OUTPUT.WS_INCD_TR_JRNNO = DCRINRCD.KEY.TR_JRNNO;
        WS_OUTPUT.WS_INCD_TIME = DCRINRCD.CRT_TM;
        WS_OUTPUT.WS_INCD_BR = DCRINRCD.TR_BR;
        WS_OUTPUT.WS_INCD_CARD_NO = DCRINRCD.KEY.CARD_NO;
        WS_OUTPUT.WS_CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        WS_OUTPUT.WS_CARD_HLDR_NM = WS_CUST_INFO.WS_HLDR_CUST_INFO.WS_HLDR_CNM;
        WS_OUTPUT.WS_CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        WS_OUTPUT.WS_CARD_OWN_NM = WS_CUST_INFO.WS_OWN_CUST_INFO.WS_OWN_CNM;
        WS_OUTPUT.WS_INCD_TLR = DCRINRCD.CRT_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 590;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTINRCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRINRCD.KEY.CARD_NO);
        CEP.TRC(SCCGWA, WS_DB_START_DATE);
        CEP.TRC(SCCGWA, WS_DB_END_DATE);
        CEP.TRC(SCCGWA, WS_DB_INCD_TYPE);
        DCTINRCD_BR.rp = new DBParm();
        DCTINRCD_BR.rp.TableName = "DCTINRCD";
        DCTINRCD_BR.rp.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CRT_DATE >= :WS_DB_START_DATE "
            + "AND CRT_DATE <= :WS_DB_END_DATE "
            + "AND INCD_TYPE LIKE :WS_DB_INCD_TYPE "
            + "AND INCD_TYPE IN ( '01' , '02' , '04' )";
        DCTINRCD_BR.rp.order = "CRT_DATE DESC,CRT_TM DESC";
        IBS.STARTBR(SCCGWA, DCRINRCD, this, DCTINRCD_BR);
    }
    public void T000_READNEXT_DCTINRCD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRINRCD, this, DCTINRCD_BR);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTINRCD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTINRCD_BR);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO, ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_TYP, CARD_STSW, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
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
