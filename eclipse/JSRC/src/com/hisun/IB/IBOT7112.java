package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7112 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACMTS IBCACMTS = new IBCACMTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7110_AWA_7110 IBB7110_AWA_7110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7112 return!");
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
        B020_MOD_AC_PROC();
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
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.AC_NATR);
        if (IBB7110_AWA_7110.AC_NATR == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NATR_M);
        }
    }
    public void B020_MOD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACMTS);
        IBCACMTS.FUNC = IBB7110_AWA_7110.FCN_C;
        IBCACMTS.NOSTR_CD = IBB7110_AWA_7110.NOS_CD;
        IBCACMTS.CCY = IBB7110_AWA_7110.CCY;
        IBCACMTS.PRIM_AC_NO = IBB7110_AWA_7110.PRI_ACNO;
        IBCACMTS.SEQ_NO = IBB7110_AWA_7110.SEQ_NO;
        IBCACMTS.AC_NATR = IBB7110_AWA_7110.AC_NATR;
        IBCACMTS.EXP_DATE = IBB7110_AWA_7110.EXP_DATE;
        IBCACMTS.INT_DAYS = IBB7110_AWA_7110.INT_DAYS;
        IBCACMTS.RATE = IBB7110_AWA_7110.RATE;
        IBCACMTS.ADV_RATE = IBB7110_AWA_7110.ADV_RATE;
        IBCACMTS.OVD_RATE = IBB7110_AWA_7110.OVD_RATE;
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CALR_STD);
        if (IBB7110_AWA_7110.CALR_STD.equalsIgnoreCase("01")) {
            IBCACMTS.CALR_STD = 360;
        } else if (IBB7110_AWA_7110.CALR_STD.equalsIgnoreCase("02")) {
            IBCACMTS.CALR_STD = 365;
        } else if (IBB7110_AWA_7110.CALR_STD.equalsIgnoreCase("03")) {
            IBCACMTS.CALR_STD = 366;
        }
        CEP.TRC(SCCGWA, IBCACMTS.CALR_STD);
        IBCACMTS.CORR_BK_CD = IBB7110_AWA_7110.COR_BKCD;
        IBCACMTS.CORR_AC_NO = IBB7110_AWA_7110.COR_ACNO;
        IBCACMTS.CORR_DEPR_NO = IBB7110_AWA_7110.COR_DENO;
        IBCACMTS.OIC_NO = IBB7110_AWA_7110.OIC_NO;
        IBCACMTS.RESP_CD = IBB7110_AWA_7110.RESP_CD;
        IBCACMTS.SUB_DP = IBB7110_AWA_7110.SUB_DP;
        IBCACMTS.AUTH_TLR = IBB7110_AWA_7110.AUTH_TLR;
        S000_CALL_IBZACMTS();
    }
    public void S000_CALL_IBZACMTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ACMT-SUB", IBCACMTS);
        if (IBCACMTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACMTS.RC);
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
