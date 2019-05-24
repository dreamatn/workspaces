package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5013 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    short WS_CNT = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACMT IBCACMT = new IBCACMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5010_AWA_5010 IBB5010_AWA_5010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5013 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5010_AWA_5010>");
        IBB5010_AWA_5010 = (IBB5010_AWA_5010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B010_01_CHECK_INPUT();
        B010_02_CHECK_INPUT();
        B010_03_CHECK_INPUT();
    }
    public void B010_01_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.FCN);
        if (IBB5010_AWA_5010.FCN == ' ') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.FCN_MUST_INPUT, IBB5010_AWA_5010.FCN_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.CIFNO);
        if (IBB5010_AWA_5010.CIFNO.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M, IBB5010_AWA_5010.CIFNO_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.NOS_CD);
        if (IBB5010_AWA_5010.NOS_CD.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.NOS_CD_M, IBB5010_AWA_5010.NOS_CD_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.CCY);
        if (IBB5010_AWA_5010.CCY.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.CCY_M, IBB5010_AWA_5010.CCY_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.PROD_CD);
        if (IBB5010_AWA_5010.PROD_CD.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.PROD_CD_M, IBB5010_AWA_5010.PROD_CD_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.POST_CTR);
        if (IBB5010_AWA_5010.POST_CTR == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR, IBB5010_AWA_5010.POST_CTR_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.CORRAC);
        if (IBB5010_AWA_5010.CORRAC.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_M, IBB5010_AWA_5010.CORRAC_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.EFFDATE);
        if (IBB5010_AWA_5010.EFFDATE == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.EFFDATE_M, IBB5010_AWA_5010.EFFDATE_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.AC_ATTR);
        if (IBB5010_AWA_5010.AC_ATTR == ' ') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.AC_TYPE, IBB5010_AWA_5010.AC_ATTR_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.PRV_FLAG);
        if (IBB5010_AWA_5010.PRV_FLAG == ' ') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.PRV_FLAG_M, IBB5010_AWA_5010.PRV_FLAG_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.RAT_FLAG);
        if (IBB5010_AWA_5010.RAT_FLAG == ' ') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.RATE_FLAG_M, IBB5010_AWA_5010.RAT_FLAG_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.AC_NATR);
        if (IBB5010_AWA_5010.AC_NATR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NATR_M);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B010_02_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (IBB5010_AWA_5010.RAT_FLAG == 'Y') {
            CEP.TRC(SCCGWA, IBB5010_AWA_5010.RAT_MTH);
            if (IBB5010_AWA_5010.RAT_MTH == '1') {
                CEP.TRC(SCCGWA, IBB5010_AWA_5010.RATE);
            } else if (IBB5010_AWA_5010.RAT_MTH == '2') {
                CEP.TRC(SCCGWA, IBB5010_AWA_5010.RAT_TYPE);
                CEP.TRC(SCCGWA, IBB5010_AWA_5010.RAT_TERM);
                if (IBB5010_AWA_5010.RAT_TYPE.trim().length() == 0) {
                    WS_CNT = (short) (WS_CNT + 1);
                    CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.RATE_TYPE_M, IBB5010_AWA_5010.RAT_TYPE_NO);
                }
                if (IBB5010_AWA_5010.RAT_TERM.trim().length() == 0) {
                    WS_CNT = (short) (WS_CNT + 1);
                    CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.RATE_TERM_M, IBB5010_AWA_5010.RAT_TERM_NO);
                }
            } else {
                WS_CNT = (short) (WS_CNT + 1);
                CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.RATE_MTH_M, IBB5010_AWA_5010.RAT_MTH_NO);
            }
            CEP.TRC(SCCGWA, IBB5010_AWA_5010.CALR_STD);
            if (IBB5010_AWA_5010.CALR_STD.trim().length() == 0) {
                WS_CNT = (short) (WS_CNT + 1);
                CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.CALR_STD_M, IBB5010_AWA_5010.CALR_STD_NO);
            }
            CEP.TRC(SCCGWA, IBB5010_AWA_5010.INTS_CYC);
            if (IBB5010_AWA_5010.INTS_CYC == ' ') {
                WS_CNT = (short) (WS_CNT + 1);
                CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.INTS_CYC_M, IBB5010_AWA_5010.INTS_CYC_NO);
            } else {
                CEP.TRC(SCCGWA, IBB5010_AWA_5010.INTS_MM);
                if ((IBB5010_AWA_5010.INTS_CYC != '4' 
                    && IBB5010_AWA_5010.INTS_CYC != '5') 
                    && IBB5010_AWA_5010.INTS_MM == 0) {
                    WS_CNT = (short) (WS_CNT + 1);
                    CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_MM_M, IBB5010_AWA_5010.INTS_MM_NO);
                }
                CEP.TRC(SCCGWA, IBB5010_AWA_5010.INTS_DD);
                if (IBB5010_AWA_5010.INTS_CYC != '5' 
                    && IBB5010_AWA_5010.INTS_DD == 0) {
                    WS_CNT = (short) (WS_CNT + 1);
                    CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_DD_M, IBB5010_AWA_5010.INTS_DD_NO);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B010_03_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACMT);
        IBCACMT.FUNC = IBB5010_AWA_5010.FCN;
        IBCACMT.CIFNO = IBB5010_AWA_5010.CIFNO;
        IBCACMT.NOS_CD = IBB5010_AWA_5010.NOS_CD;
        IBCACMT.CCY = IBB5010_AWA_5010.CCY;
        IBCACMT.CUSTNME = IBB5010_AWA_5010.CUSTNME;
        IBCACMT.PROD_CD = IBB5010_AWA_5010.PROD_CD;
        IBCACMT.POST_CTR = IBB5010_AWA_5010.POST_CTR;
        IBCACMT.OIC_NO = IBB5010_AWA_5010.OIC_NO;
        IBCACMT.RESP_CD = IBB5010_AWA_5010.RESP_CD;
        IBCACMT.SUB_DP = IBB5010_AWA_5010.SUB_DP;
        IBCACMT.AC_ATTR = IBB5010_AWA_5010.AC_ATTR;
        IBCACMT.FUND_ATTR = IBB5010_AWA_5010.FUD_ATTR;
        IBCACMT.AC_NATR = IBB5010_AWA_5010.AC_NATR;
        IBCACMT.CORRAC_NO = IBB5010_AWA_5010.CORRAC;
        IBCACMT.CORRAC_BK = IBB5010_AWA_5010.CORRBK;
        IBCACMT.EFFDATE = IBB5010_AWA_5010.EFFDATE;
        IBCACMT.PRV_FLAG = IBB5010_AWA_5010.PRV_FLAG;
        IBCACMT.RATE_FLAG = IBB5010_AWA_5010.RAT_FLAG;
        IBCACMT.RATE_MTH = IBB5010_AWA_5010.RAT_MTH;
        IBCACMT.RATE_TYPE = IBB5010_AWA_5010.RAT_TYPE;
        IBCACMT.RATE_TERM = IBB5010_AWA_5010.RAT_TERM;
        IBCACMT.RATE_PCT = IBB5010_AWA_5010.RATE_PCT;
        IBCACMT.RATE_SPREAD = IBB5010_AWA_5010.SPREAD;
        IBCACMT.RATE = IBB5010_AWA_5010.RATE;
        if (IBB5010_AWA_5010.CALR_STD.equalsIgnoreCase("01")) {
            IBCACMT.CALR_STD = K_360_DAYS;
        } else if (IBB5010_AWA_5010.CALR_STD.equalsIgnoreCase("02")) {
            IBCACMT.CALR_STD = K_365_DAYS;
        } else if (IBB5010_AWA_5010.CALR_STD.equalsIgnoreCase("03")) {
            IBCACMT.CALR_STD = K_366_DAYS;
        }
        IBCACMT.INTS_CYC = IBB5010_AWA_5010.INTS_CYC;
        IBCACMT.INTS_DT_MM = IBB5010_AWA_5010.INTS_MM;
        IBCACMT.INTS_DT_DD = IBB5010_AWA_5010.INTS_DD;
        IBCACMT.OD_PAY_AC = IBB5010_AWA_5010.OD_PAYAC;
        IBCACMT.OD_RATE = IBB5010_AWA_5010.OD_RATE;
        IBCACMT.OD_INTS_CYC = IBB5010_AWA_5010.OD_S_CYC;
        IBCACMT.OD_FLAG = IBB5010_AWA_5010.OD_FLAG;
        IBCACMT.OD_RATE_FLAG = IBB5010_AWA_5010.OD_R_FLG;
        IBCACMT.OIC_NO = IBB5010_AWA_5010.OIC_NO;
        IBCACMT.RESP_CD = IBB5010_AWA_5010.RESP_CD;
        IBCACMT.SUB_DP = IBB5010_AWA_5010.SUB_DP;
        IBCACMT.AUTH_TLR = IBB5010_AWA_5010.AUTH_TLR;
        IBCACMT.SWR_BR = IBB5010_AWA_5010.SWR_FLG;
        IBCACMT.AC_USE = IBB5010_AWA_5010.AC_USE;
        IBCACMT.RMK = IBB5010_AWA_5010.RMK;
        S000_CALL_IBZACMT();
    }
    public void S000_CALL_IBZACMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-MAINT", IBCACMT);
        if (IBCACMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACMT.RC);
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
