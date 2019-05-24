package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5947 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSFQUE DCCSFQUE = new DCCSFQUE();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DCB5947_AWA_5947 DCB5947_AWA_5947;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5947 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5947_AWA_5947>");
        DCB5947_AWA_5947 = (DCB5947_AWA_5947) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5947_AWA_5947.AC_NO);
        CEP.TRC(SCCGWA, DCB5947_AWA_5947.AC_NM);
        CEP.TRC(SCCGWA, DCB5947_AWA_5947.PRY_DATE);
        CEP.TRC(SCCGWA, DCB5947_AWA_5947.PAGE_ROW);
        CEP.TRC(SCCGWA, DCB5947_AWA_5947.PAGE_NUM);
        if (DCB5947_AWA_5947.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            WS_FLD_NO = DCB5947_AWA_5947.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5947_AWA_5947.PRY_DATE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRY_DATE_M_INPUT;
            WS_FLD_NO = DCB5947_AWA_5947.PRY_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_QUE_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSFQUE);
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DCB5947_AWA_5947.AC_NO;
        DCCPACTY.INPUT.FUNC = '1';
        S000_CALL_DCZPACTY();
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
            DCCSFQUE.AC_NO = DCB5947_AWA_5947.AC_NO;
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                DCCSFQUE.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            }
        }
        DCCSFQUE.AC_NM = DCB5947_AWA_5947.AC_NM;
        DCCSFQUE.PRY_DATE = DCB5947_AWA_5947.PRY_DATE;
        DCCSFQUE.PAGE_ROW = DCB5947_AWA_5947.PAGE_ROW;
        DCCSFQUE.PAGE_NUM = DCB5947_AWA_5947.PAGE_NUM;
        S000_CALL_DCZSFQUE();
    }
    public void S000_CALL_DCZSFQUE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-ZF-QUE", DCCSFQUE);
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
