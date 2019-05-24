package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4059 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT4059_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT4059_WS_TEMP_VARIABLE();
    LNOT4059_WS_MSG_INFO WS_MSG_INFO = new LNOT4059_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCSWTFR LNCSWTFR = new LNCSWTFR();
    SCCGWA SCCGWA;
    LNB0027_AWA_0027 LNB0027_AWA_0027;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT4059 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB0027_AWA_0027>");
        LNB0027_AWA_0027 = (LNB0027_AWA_0027) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB0027_AWA_0027.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB0027_AWA_0027.VAL_DT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB0027_AWA_0027.VAL_DT);
        CEP.TRC(SCCGWA, LNB0027_AWA_0027.CTA_NO);
        IBS.init(SCCGWA, LNCSWTFR);
        LNCSWTFR.CTA_NO = LNB0027_AWA_0027.CTA_NO;
        LNCSWTFR.BR = LNB0027_AWA_0027.BR;
        LNCSWTFR.CI_NO = LNB0027_AWA_0027.CI_NO;
        LNCSWTFR.CI_ENMS = LNB0027_AWA_0027.CI_ENMS;
        LNCSWTFR.CI_CNM = LNB0027_AWA_0027.CI_CNM;
        LNCSWTFR.CITY_CD = LNB0027_AWA_0027.CITY_CD;
        LNCSWTFR.PROD_CD = LNB0027_AWA_0027.PROD_CD;
        LNCSWTFR.CCY = LNB0027_AWA_0027.CCY;
        LNCSWTFR.LON_PRIN = LNB0027_AWA_0027.PRIN;
        LNCSWTFR.LON_BAL = LNB0027_AWA_0027.OS_BAL;
        LNCSWTFR.VAL_DTE = LNB0027_AWA_0027.VAL_DT;
        LNCSWTFR.INT_ACCR = LNB0027_AWA_0027.INT_ACCR;
        LNCSWTFR.PRIN_ARER = LNB0027_AWA_0027.PRIN_ARR;
        LNCSWTFR.INT_ARER = LNB0027_AWA_0027.INT_ARRE;
        LNCSWTFR.PRIN_LC = LNB0027_AWA_0027.PRIN_LC;
        LNCSWTFR.INT_LC = LNB0027_AWA_0027.INT_LC;
        LNCSWTFR.TOT_AMT = LNB0027_AWA_0027.WO_AMT;
        LNCSWTFR.RMK1 = LNB0027_AWA_0027.RMK1;
        LNCSWTFR.RMK2 = LNB0027_AWA_0027.RMK2;
        LNCSWTFR.RMK3 = LNB0027_AWA_0027.RMK3;
        S000_CALL_LNZSWTFR();
    }
    public void S000_CALL_LNZSWTFR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-LN-WRTOFF-RVS", LNCSWTFR);
        if (LNCSWTFR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSWTFR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
