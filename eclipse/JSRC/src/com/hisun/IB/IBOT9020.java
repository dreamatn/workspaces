package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT9020 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCCHKDT IBCCHKDT = new IBCCHKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB9010_AWA_9010 IBB9010_AWA_9010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT9020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB9010_AWA_9010>");
        IBB9010_AWA_9010 = (IBB9010_AWA_9010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB9010_AWA_9010.AC_NO);
        if (IBB9010_AWA_9010.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBB9010_AWA_9010.FCN);
        CEP.TRC(SCCGWA, IBB9010_AWA_9010.SEQ_NO);
        if (IBB9010_AWA_9010.FCN == '2') {
            if (IBB9010_AWA_9010.SEQ_NO == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCCHKDT);
        IBCCHKDT.FUNC = IBB9010_AWA_9010.FCN;
        IBCCHKDT.AC_NO = IBB9010_AWA_9010.AC_NO;
        IBCCHKDT.SEQ_NO = IBB9010_AWA_9010.SEQ_NO;
        IBCCHKDT.SETT_DT = IBB9010_AWA_9010.SETT_DT;
        S000_CALL_IBZCHKDT();
    }
    public void S000_CALL_IBZCHKDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CHECK-INTS-DT", IBCCHKDT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
