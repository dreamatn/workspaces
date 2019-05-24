package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5062 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCMANIN IBCMANIN = new IBCMANIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5060_AWA_5060 IBB5060_AWA_5060;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5062 return!");
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
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.AC_DATE);
        if (IBB5060_AWA_5060.AC_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_DATE_M, IBB5060_AWA_5060.AC_DATE_NO);
        }
        CEP.TRC(SCCGWA, IBB5060_AWA_5060.JRNNO);
        if (IBB5060_AWA_5060.JRNNO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.JRNNO_M, IBB5060_AWA_5060.JRNNO_NO);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCMANIN);
        IBCMANIN.AC_DATE = IBB5060_AWA_5060.AC_DATE;
        IBCMANIN.JRN_NO = IBB5060_AWA_5060.JRNNO;
        S000_CALL_IBZMANIN();
    }
    public void S000_CALL_IBZMANIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-MANUAL-DRCR-INQ", IBCMANIN);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
