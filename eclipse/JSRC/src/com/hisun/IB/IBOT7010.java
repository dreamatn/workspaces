package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7010 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACBRT IBCACBRT = new IBCACBRT();
    CICCUST CICCUST = new CICCUST();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7010_AWA_7010 IBB7010_AWA_7010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB7010_AWA_7010>");
        IBB7010_AWA_7010 = (IBB7010_AWA_7010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.OPEN_BR);
        if (IBB7010_AWA_7010.OPEN_BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
    }
    public void B020_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACBRT);
        IBCACBRT.CI_NO = IBB7010_AWA_7010.CIFNO;
        IBCACBRT.NOSTR_CD = IBB7010_AWA_7010.NOS_CD;
        IBCACBRT.OPEN_BR = IBB7010_AWA_7010.OPEN_BR;
        IBCACBRT.CUSTNME = IBB7010_AWA_7010.CUSTNME;
        IBCACBRT.CCY = IBB7010_AWA_7010.CCY;
        IBCACBRT.IN_STS = IBB7010_AWA_7010.IN_STS;
        S000_CALL_IBZACBRT();
    }
    public void S000_CALL_IBZACBRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-BR-TD", IBCACBRT);
        if (IBCACBRT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACBRT.RC);
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
