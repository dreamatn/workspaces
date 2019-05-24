package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7610 {
    String CPN_DC_S_QCCY_PROC = "DC-S-QCCY-PROC";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSQCCY DCCSQCCY = new DCCSQCCY();
    SCCGWA SCCGWA;
    DCB7610_AWA_7610 DCB7610_AWA_7610;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT7610 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7610_AWA_7610>");
        DCB7610_AWA_7610 = (DCB7610_AWA_7610) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_QUERY_DEPOSIT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7610_AWA_7610.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB7610_AWA_7610.PAGE_ROW);
        if (DCB7610_AWA_7610.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCB7610_AWA_7610.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB7610_AWA_7610.PAGE_ROW);
    }
    public void B020_QUERY_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQCCY);
        DCCSQCCY.AC_NO = DCB7610_AWA_7610.AC_NO;
        DCCSQCCY.PAGE_NUM = DCB7610_AWA_7610.PAGE_NUM;
        DCCSQCCY.PAGE_ROW = DCB7610_AWA_7610.PAGE_ROW;
        CEP.TRC(SCCGWA, DCCSQCCY.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSQCCY.PAGE_ROW);
        S000_CALL_DCZSQCCY();
    }
    public void S000_CALL_DCZSQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DC_S_QCCY_PROC, DCCSQCCY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
