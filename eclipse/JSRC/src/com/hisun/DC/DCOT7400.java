package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7400 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSSHLD DCCSSHLD = new DCCSSHLD();
    SCCGWA SCCGWA;
    DCB7400_AWA_7400 DCB7400_AWA_7400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7400 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7400_AWA_7400>");
        DCB7400_AWA_7400 = (DCB7400_AWA_7400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7400_AWA_7400.REQ_JRNO);
        if (DCB7400_AWA_7400.REQ_JRNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_REQ_JRNNO_MUST_INPUT;
            WS_FLD_NO = DCB7400_AWA_7400.REQ_JRNO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSSHLD);
        DCCSSHLD.DATA.REQ_JRNO = DCB7400_AWA_7400.REQ_JRNO;
        S000_CALL_DCZSSHLD();
    }
    public void S000_CALL_DCZSSHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-INQ-HLD", DCCSSHLD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
