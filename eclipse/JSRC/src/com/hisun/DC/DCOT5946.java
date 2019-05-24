package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5946 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCMXQUE DCCMXQUE = new DCCMXQUE();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DCB5946_AWA_5946 DCB5946_AWA_5946;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5946 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5946_AWA_5946>");
        DCB5946_AWA_5946 = (DCB5946_AWA_5946) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5946_AWA_5946.AC_NO);
        CEP.TRC(SCCGWA, DCB5946_AWA_5946.AC_NM);
        CEP.TRC(SCCGWA, DCB5946_AWA_5946.MCT_DATE);
        CEP.TRC(SCCGWA, DCB5946_AWA_5946.PAGE_ROW);
        CEP.TRC(SCCGWA, DCB5946_AWA_5946.PAGE_NUM);
        if (DCB5946_AWA_5946.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            WS_FLD_NO = DCB5946_AWA_5946.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5946_AWA_5946.MCT_DATE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MCT_DATE_M_INPUT;
            WS_FLD_NO = DCB5946_AWA_5946.MCT_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_QUE_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCMXQUE);
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DCB5946_AWA_5946.AC_NO;
        DCCPACTY.INPUT.FUNC = '1';
        S000_CALL_DCZPACTY();
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
            DCCMXQUE.AC_NO = DCB5946_AWA_5946.AC_NO;
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                DCCMXQUE.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            }
        }
        DCCMXQUE.AC_NM = DCB5946_AWA_5946.AC_NM;
        DCCMXQUE.MCT_DATE = DCB5946_AWA_5946.MCT_DATE;
        DCCMXQUE.PAGE_ROW = DCB5946_AWA_5946.PAGE_ROW;
        DCCMXQUE.PAGE_NUM = DCB5946_AWA_5946.PAGE_NUM;
        S000_CALL_DCZMXQUE();
    }
    public void S000_CALL_DCZMXQUE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-R-MX-QUE", DCCMXQUE);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
