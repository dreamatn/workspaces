package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5063 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    short WS_CNT = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCMANAC IBCMANAC = new IBCMANAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5060_AWA_5060 IBB5060_AWA_5060;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5063 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5060_AWA_5060>");
        IBB5060_AWA_5060 = (IBB5060_AWA_5060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.AC_NO);
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.NOS_CD);
        if (IBB5060_AWA_5060.AC_NO.trim().length() == 0 
            && IBB5060_AWA_5060.NOS_CD.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, IBB5060_AWA_5060.AC_NO_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.CCY);
        if (IBB5060_AWA_5060.CCY.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.CCY_M, IBB5060_AWA_5060.CCY_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.SIGN);
        if (IBB5060_AWA_5060.SIGN != 'C' 
            && IBB5060_AWA_5060.SIGN != 'D') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.DRCR_SIGN_M, IBB5060_AWA_5060.SIGN_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.AMT);
        if (IBB5060_AWA_5060.AMT == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_M_INPUT, IBB5060_AWA_5060.AMT_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.OTH_TYP);
        if (IBB5060_AWA_5060.OTH_TYP == ' ') {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_ATTR_M, IBB5060_AWA_5060.OTH_TYP_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.OTH_AC);
        if (IBB5060_AWA_5060.OTH_AC.trim().length() == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_NO_M, IBB5060_AWA_5060.OTH_AC_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.BR);
        if (IBB5060_AWA_5060.BR == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR, IBB5060_AWA_5060.BR_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.VAL_DATE);
        if (IBB5060_AWA_5060.VAL_DATE == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.VAL_DATE_M, IBB5060_AWA_5060.VAL_DATE_NO);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCMANAC);
        IBCMANAC.AC_NO = IBB5060_AWA_5060.AC_NO;
        IBCMANAC.NOS_CD = IBB5060_AWA_5060.NOS_CD;
        IBCMANAC.CCY = IBB5060_AWA_5060.CCY;
        IBCMANAC.SIGN = IBB5060_AWA_5060.SIGN;
        IBCMANAC.AMT = IBB5060_AWA_5060.AMT;
        IBCMANAC.OTH_AC_TYP = IBB5060_AWA_5060.OTH_TYP;
        IBCMANAC.OTH_AC_NO = IBB5060_AWA_5060.OTH_AC;
        IBCMANAC.POST_CTR = IBB5060_AWA_5060.BR;
        IBCMANAC.SUSPD_NO = IBB5060_AWA_5060.SUSPD_NO;
        IBCMANAC.VAL_DATE = IBB5060_AWA_5060.VAL_DATE;
        IBCMANAC.REF_NO = IBB5060_AWA_5060.REF;
        IBCMANAC.MMO = IBB5060_AWA_5060.MMO;
        IBCMANAC.RMK = IBB5060_AWA_5060.RMK;
        IBCMANAC.OTH_REF_NO = IBB5060_AWA_5060.OTH_REF;
        IBCMANAC.OTH_MMO = IBB5060_AWA_5060.OTH_MMO;
        IBCMANAC.OTH_RMK = IBB5060_AWA_5060.OTH_RMK;
        S000_CALL_IBZMANIP();
    }
    public void S000_CALL_IBZMANIP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-MANUAL-DRCR-IPT", IBCMANAC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
