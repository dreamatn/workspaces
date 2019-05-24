package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT6040 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQBAL IBCQBAL = new IBCQBAL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB6010_AWA_6010 IBB6010_AWA_6010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT6040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB6010_AWA_6010>");
        IBB6010_AWA_6010 = (IBB6010_AWA_6010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB6010_AWA_6010.POST_CTR);
        if (IBB6010_AWA_6010.POST_CTR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBB6010_AWA_6010.ACNO);
        CEP.TRC(SCCGWA, IBB6010_AWA_6010.NOS_CD);
        CEP.TRC(SCCGWA, IBB6010_AWA_6010.CCY);
        if (IBB6010_AWA_6010.ACNO.trim().length() == 0 
            && (IBB6010_AWA_6010.NOS_CD.trim().length() == 0 
            || IBB6010_AWA_6010.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
    }
    public void B020_TRANS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQBAL);
        IBCQBAL.POST_CTR = IBB6010_AWA_6010.POST_CTR;
        IBCQBAL.AC_NO = IBB6010_AWA_6010.ACNO;
        IBCQBAL.NOSTR_CD = IBB6010_AWA_6010.NOS_CD;
        IBCQBAL.CCY = IBB6010_AWA_6010.CCY;
        IBCQBAL.STR_DATE = IBB6010_AWA_6010.STR_DATE;
        CEP.TRC(SCCGWA, IBCQBAL.STR_DATE);
        IBCQBAL.END_DATE = IBB6010_AWA_6010.END_DATE;
        CEP.TRC(SCCGWA, IBCQBAL.END_DATE);
        S000_CALL_IBZQBAL();
    }
    public void S000_CALL_IBZQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-BAL-INQ", IBCQBAL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
