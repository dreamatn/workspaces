package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7013 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCACMTT IBCACMTT = new IBCACMTT();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
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
        CEP.TRC(SCCGWA, "IBOT7013 return!");
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
        B020_OPEN_AC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.OPEN_BR);
        if (IBB7010_AWA_7010.OPEN_BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.CIFNO);
        if (IBB7010_AWA_7010.CIFNO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M);
        }
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.NOS_CD);
        if (IBB7010_AWA_7010.NOS_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOS_CD_M);
        }
        if (IBB7010_AWA_7010.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY_M);
        }
    }
    public void B020_OPEN_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACMTT);
        IBCACMTT.FUNC = IBB7010_AWA_7010.FCN;
        IBCACMTT.CIFNO = IBB7010_AWA_7010.CIFNO;
        IBCACMTT.NOSTR_CD = IBB7010_AWA_7010.NOS_CD;
        IBCACMTT.CCY = IBB7010_AWA_7010.CCY;
        IBCACMTT.OPEN_BR = IBB7010_AWA_7010.OPEN_BR;
        IBCACMTT.CUSTNME = IBB7010_AWA_7010.CUSTNME;
        IBCACMTT.AUTH_TLR = IBB7010_AWA_7010.AUTH_TLR;
        S000_CALL_IBZACMTT();
    }
    public void S000_CALL_IBZACMTT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-MAINT-T", IBCACMTT);
        if (IBCACMTT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACMTT.RC);
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
