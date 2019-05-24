package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7120 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACFIS IBCACFIS = new IBCACFIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7110_AWA_7110 IBB7110_AWA_7110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB7110_AWA_7110>");
        IBB7110_AWA_7110 = (IBB7110_AWA_7110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_OPEN_AC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.PRI_ACNO);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.NOS_CD);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CCY);
        if (IBB7110_AWA_7110.PRI_ACNO.trim().length() == 0 
            && (IBB7110_AWA_7110.NOS_CD.trim().length() == 0 
            || IBB7110_AWA_7110.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.PROD_CD);
        if (IBB7110_AWA_7110.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PROD_CD_M);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.FUN_ATTR);
        if (IBB7110_AWA_7110.FUN_ATTR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FUND_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.OPEN_BAL);
        if (IBB7110_AWA_7110.OPEN_BAL == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OPEN_BAL_M);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.VAL_DATE);
        if (IBB7110_AWA_7110.VAL_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.VAL_DATE_M);
        } else {
            if (IBB7110_AWA_7110.EXP_DATE == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EXP_DATE_M);
            } else {
                if (IBB7110_AWA_7110.VAL_DATE > IBB7110_AWA_7110.EXP_DATE) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FORWARD_VAL);
                }
            }
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.RATE_MTH);
        if (IBB7110_AWA_7110.RATE_MTH == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.RATE_MTH_M);
        } else {
            if (IBB7110_AWA_7110.RATE_MTH == '1') {
                CEP.TRC(SCCGWA, IBB7110_AWA_7110.INTS_CYC);
                if (IBB7110_AWA_7110.INTS_CYC == ' ') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INTS_CYC_M);
                }
            }
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.RATE);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CALR_STD);
        if (IBB7110_AWA_7110.RATE_MTH != '0') {
            if (IBB7110_AWA_7110.CALR_STD.trim().length() == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CALR_STD_M);
            }
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.OTAC_ATR);
        if (IBB7110_AWA_7110.OTAC_ATR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.OTH_ACNO);
        if (IBB7110_AWA_7110.OTH_ACNO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_NO_M);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.AC_NATR);
        if (IBB7110_AWA_7110.AC_NATR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NATR_M);
        }
    }
    public void B020_OPEN_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACFIS);
        IBCACFIS.FUNC = IBB7110_AWA_7110.FCN_C;
        IBCACFIS.NOSTR_CD = IBB7110_AWA_7110.NOS_CD;
        IBCACFIS.PRIM_AC_NO = IBB7110_AWA_7110.PRI_ACNO;
        IBCACFIS.SEQ_NO = IBB7110_AWA_7110.SEQ_NO;
        IBCACFIS.CUSTNME = IBB7110_AWA_7110.CUSTNME;
        IBCACFIS.PROD_CD = IBB7110_AWA_7110.PROD_CD;
        IBCACFIS.FUND_ATTR = IBB7110_AWA_7110.FUN_ATTR;
        IBCACFIS.AC_NATR = IBB7110_AWA_7110.AC_NATR;
        IBCACFIS.CCY = IBB7110_AWA_7110.CCY;
        IBCACFIS.OPEN_BAL = IBB7110_AWA_7110.OPEN_BAL;
        IBCACFIS.VAL_DATE = IBB7110_AWA_7110.VAL_DATE;
        IBCACFIS.EXP_DATE = IBB7110_AWA_7110.EXP_DATE;
        IBCACFIS.INT_DAYS = IBB7110_AWA_7110.INT_DAYS;
        IBCACFIS.RATE_MTH = IBB7110_AWA_7110.RATE_MTH;
        IBCACFIS.INTS_CYC = IBB7110_AWA_7110.INTS_CYC;
        IBCACFIS.PVAL_DATE = IBB7110_AWA_7110.PVAL_DT;
        IBCACFIS.RATE = IBB7110_AWA_7110.RATE;
        IBCACFIS.ADV_RATE = IBB7110_AWA_7110.ADV_RATE;
        IBCACFIS.OVD_RATE = IBB7110_AWA_7110.OVD_RATE;
        IBCACFIS.EXP_INT = IBB7110_AWA_7110.EXP_INT;
        if (IBB7110_AWA_7110.CALR_STD.equalsIgnoreCase("01")) {
            IBCACFIS.CALR_STD = 360;
        } else if (IBB7110_AWA_7110.CALR_STD.equalsIgnoreCase("02")) {
            IBCACFIS.CALR_STD = 365;
        } else if (IBB7110_AWA_7110.CALR_STD.equalsIgnoreCase("03")) {
            IBCACFIS.CALR_STD = 366;
        }
        IBCACFIS.OTH_AC_ATTR = IBB7110_AWA_7110.OTAC_ATR;
        IBCACFIS.OTH_AC_NO = IBB7110_AWA_7110.OTH_ACNO;
        IBCACFIS.INTS_AC_ATTR = IBB7110_AWA_7110.INT_ACTR;
        IBCACFIS.INTS_AC_NO = IBB7110_AWA_7110.INT_ACNO;
        IBCACFIS.CORR_BK_CD = IBB7110_AWA_7110.COR_BKCD;
        IBCACFIS.CORR_AC_NO = IBB7110_AWA_7110.COR_ACNO;
        IBCACFIS.CORR_DEPR_NO = IBB7110_AWA_7110.COR_DENO;
        IBCACFIS.OPEN_DATE = IBB7110_AWA_7110.OPEN_DT;
        IBCACFIS.BRANCH = IBB7110_AWA_7110.BR;
        IBCACFIS.OIC_NO = IBB7110_AWA_7110.OIC_NO;
        IBCACFIS.RESP_CD = IBB7110_AWA_7110.RESP_CD;
        IBCACFIS.SUB_DP = IBB7110_AWA_7110.SUB_DP;
        IBCACFIS.AC_NO1 = IBB7110_AWA_7110.AC_NO1;
        S000_CALL_IBZACFIS();
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBB7110_AWA_7110.AC_NO1 = IBCACFIS.AC_NO1;
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.AC_NO1);
    }
    public void S000_CALL_IBZACFIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ACFI-SUB", IBCACFIS);
        if (IBCACFIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACFIS.RC);
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
