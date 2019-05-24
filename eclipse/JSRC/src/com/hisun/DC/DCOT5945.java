package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5945 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSHQUE DCCSHQUE = new DCCSHQUE();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DCB5945_AWA_5945 DCB5945_AWA_5945;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5945 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5945_AWA_5945>");
        DCB5945_AWA_5945 = (DCB5945_AWA_5945) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5945_AWA_5945.OVR_NO);
        CEP.TRC(SCCGWA, DCB5945_AWA_5945.AC_NO);
        CEP.TRC(SCCGWA, DCB5945_AWA_5945.PAGE_ROW);
        CEP.TRC(SCCGWA, DCB5945_AWA_5945.PAGE_NUM);
        if (DCB5945_AWA_5945.OVR_NO.trim().length() == 0 
            && DCB5945_AWA_5945.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ONE_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5945_AWA_5945.OVR_NO.trim().length() > 0 
            && DCB5945_AWA_5945.AC_NO.trim().length() > 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ONE_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSHQUE);
        DCCSHQUE.OVR_NO = DCB5945_AWA_5945.OVR_NO;
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DCB5945_AWA_5945.AC_NO;
        DCCPACTY.INPUT.FUNC = '1';
        S000_CALL_DCZPACTY();
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
            DCCSHQUE.AC_NO = DCB5945_AWA_5945.AC_NO;
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                DCCSHQUE.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            }
        }
        DCCSHQUE.PAGE_ROW = DCB5945_AWA_5945.PAGE_ROW;
        DCCSHQUE.PAGE_NUM = DCB5945_AWA_5945.PAGE_NUM;
        S000_CALL_DCZSHQUE();
    }
    public void S000_CALL_DCZSHQUE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SH-QUE", DCCSHQUE);
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
