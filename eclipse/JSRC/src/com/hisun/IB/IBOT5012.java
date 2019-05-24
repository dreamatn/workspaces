package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5012 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
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
        CEP.TRC(SCCGWA, "IBOT5012 return!");
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
        B020_AC_MODIFY_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.ACNO);
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.NOS_CD);
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.CCY);
        if (IBB5010_AWA_5010.ACNO.trim().length() == 0 
            && (IBB5010_AWA_5010.NOS_CD.trim().length() == 0 
            || IBB5010_AWA_5010.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBB5010_AWA_5010.ACNO_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.FCN);
        if (IBB5010_AWA_5010.FCN == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FCN_MUST_INPUT, IBB5010_AWA_5010.FCN_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.CIFNO);
        if (IBB5010_AWA_5010.CIFNO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M, IBB5010_AWA_5010.CIFNO_NO);
        }
    }
    public void B020_AC_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACMT);
        IBCACMT.FUNC = IBB5010_AWA_5010.FCN;
        IBCACMT.CIFNO = IBB5010_AWA_5010.CIFNO;
        IBCACMT.CUSTNME = IBB5010_AWA_5010.CUSTNME;
        IBCACMT.AC_NO = IBB5010_AWA_5010.ACNO;
        IBCACMT.NOS_CD = IBB5010_AWA_5010.NOS_CD;
        IBCACMT.CCY = IBB5010_AWA_5010.CCY;
        IBCACMT.AC_ATTR = IBB5010_AWA_5010.AC_ATTR;
        IBCACMT.AC_NATR = IBB5010_AWA_5010.AC_NATR;
        IBCACMT.CORRAC_NO = IBB5010_AWA_5010.CORRAC;
        IBCACMT.CORRAC_BK = IBB5010_AWA_5010.CORRBK;
        IBCACMT.OD_PAY_AC = IBB5010_AWA_5010.OD_PAYAC;
        IBCACMT.SWR_BR = IBB5010_AWA_5010.SWR_FLG;
        CEP.TRC(SCCGWA, IBCACMT.OD_PAY_AC);
        IBCACMT.OIC_NO = IBB5010_AWA_5010.OIC_NO;
        IBCACMT.RESP_CD = IBB5010_AWA_5010.RESP_CD;
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.SUB_DP);
        IBCACMT.SUB_DP = IBB5010_AWA_5010.SUB_DP;
        CEP.TRC(SCCGWA, IBCACMT.SUB_DP);
        IBCACMT.AUTH_TLR = IBB5010_AWA_5010.AUTH_TLR;
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
