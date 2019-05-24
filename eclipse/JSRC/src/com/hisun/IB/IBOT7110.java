package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7110 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACBRS IBCACBRS = new IBCACBRS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7110_AWA_7110 IBB7110_AWA_7110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7110 return!");
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
        B020_TRANS_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.BR);
        if (IBB7110_AWA_7110.BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
    }
    CEP.TRC(SCCGWA, IBB7110_AWA_7110.STRDATE);
    CEP.TRC(SCCGWA, IBB7110_AWA_7110.ENDDATE);
    if (IBB7110_AWA_7110.STRDATE != 0 
        && IBB7110_AWA_7110.ENDDATE == 0) {
        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EDATE_MUST_INPUT);
    }
    if (IBB7110_AWA_7110.STRDATE == 0 
        && IBB7110_AWA_7110.ENDDATE != 0) {
        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_MUST_INPUT);
    }
    if (IBB7110_AWA_7110.STRDATE != 0 
        && IBB7110_AWA_7110.ENDDATE != 0) {
        if (IBB7110_AWA_7110.STRDATE > IBB7110_AWA_7110.ENDDATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
        }
    }
    public void B020_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACBRS);
        IBCACBRS.POST_CTR = IBB7110_AWA_7110.BR;
        IBCACBRS.PRIM_AC_NO = IBB7110_AWA_7110.PRI_ACNO;
        IBCACBRS.NOSTR_CD = IBB7110_AWA_7110.NOS_CD;
        IBCACBRS.CCY = IBB7110_AWA_7110.CCY;
        IBCACBRS.IN_STS = IBB7110_AWA_7110.IN_STS;
        IBCACBRS.STRDATE = IBB7110_AWA_7110.STRDATE;
        IBCACBRS.ENDDATE = IBB7110_AWA_7110.ENDDATE;
        S000_CALL_IBZACBRS();
    }
    public void S000_CALL_IBZACBRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-BR-SUB", IBCACBRS);
        if (IBCACBRS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACBRS.RC);
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
