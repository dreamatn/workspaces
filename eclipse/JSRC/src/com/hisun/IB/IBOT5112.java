package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5112 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCRATMT IBCRATMT = new IBCRATMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5110_AWA_5110 IBB5110_AWA_5110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5112 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5110_AWA_5110>");
        IBB5110_AWA_5110 = (IBB5110_AWA_5110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5110_AWA_5110.AC_NO);
        CEP.TRC(SCCGWA, IBB5110_AWA_5110.NOST_CD);
        CEP.TRC(SCCGWA, IBB5110_AWA_5110.CCY);
        if (IBB5110_AWA_5110.AC_NO.trim().length() == 0 
            && (IBB5110_AWA_5110.NOST_CD.trim().length() == 0 
            || IBB5110_AWA_5110.CCY.trim().length() == 0)) {
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBB5110_AWA_5110.AC_NO_NO);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCRATMT);
        IBCRATMT.AC_NO = IBB5110_AWA_5110.AC_NO;
        IBCRATMT.NOS_CD = IBB5110_AWA_5110.NOST_CD;
        IBCRATMT.CCY = IBB5110_AWA_5110.CCY;
        IBCRATMT.EFFDATE = IBB5110_AWA_5110.EFFDATE;
        IBCRATMT.RATE_MTH = IBB5110_AWA_5110.RATE_MTH;
        IBCRATMT.RATE_TYPE = IBB5110_AWA_5110.RATE_TYP;
        IBCRATMT.RATE_TERM = IBB5110_AWA_5110.RATE_TER;
        IBCRATMT.RATE_PCT = IBB5110_AWA_5110.RATE_PCT;
        IBCRATMT.RATE_SPREAD = IBB5110_AWA_5110.RATE_SPR;
        if (IBB5110_AWA_5110.CALR_STD.equalsIgnoreCase("01")) {
            IBCRATMT.CALR_STD = K_360_DAYS;
        } else if (IBB5110_AWA_5110.CALR_STD.equalsIgnoreCase("02")) {
            IBCRATMT.CALR_STD = K_365_DAYS;
        } else if (IBB5110_AWA_5110.CALR_STD.equalsIgnoreCase("03")) {
            IBCRATMT.CALR_STD = K_366_DAYS;
        }
        IBCRATMT.RATE = IBB5110_AWA_5110.RATE;
        S000_CALL_IBZRATMT();
    }
    public void S000_CALL_IBZRATMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-INT-RATE-MAINT", IBCRATMT);
        if (IBCRATMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRATMT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
