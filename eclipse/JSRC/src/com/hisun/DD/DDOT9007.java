package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT9007 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQINF DDCSQINF = new DDCSQINF();
    SCCGWA SCCGWA;
    DDB9007_AWA_9007 DDB9007_AWA_9007;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT9007 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB9007_AWA_9007>");
        DDB9007_AWA_9007 = (DDB9007_AWA_9007) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB9007_AWA_9007.FUNC);
        CEP.TRC(SCCGWA, DDB9007_AWA_9007.AC_NO);
        CEP.TRC(SCCGWA, DDB9007_AWA_9007.CCY);
        CEP.TRC(SCCGWA, DDB9007_AWA_9007.CCY_TYPE);
        if (DDB9007_AWA_9007.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            WS_FLD_NO = DDB9007_AWA_9007.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB9007_AWA_9007.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB9007_AWA_9007.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQINF);
        DDCSQINF.FUNC = DDB9007_AWA_9007.FUNC;
        DDCSQINF.AC_NO = DDB9007_AWA_9007.AC_NO;
        DDCSQINF.CCY = DDB9007_AWA_9007.CCY;
        DDCSQINF.CCY_TYPE = DDB9007_AWA_9007.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCSQINF.CCY);
        CEP.TRC(SCCGWA, DDCSQINF.CCY_TYPE);
        S000_CALL_DDZSQINF();
    }
    public void S000_CALL_DDZSQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQINF", DDCSQINF);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
