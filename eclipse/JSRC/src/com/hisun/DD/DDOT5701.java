package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5701 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSSTDT DDCSSTDT = new DDCSSTDT();
    SCCGWA SCCGWA;
    DDB5700_AWA_5700 DDB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5701 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5700_AWA_5700>");
        DDB5700_AWA_5700 = (DDB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5700_AWA_5700.FUNC);
        CEP.TRC(SCCGWA, DDB5700_AWA_5700.BR);
        CEP.TRC(SCCGWA, DDB5700_AWA_5700.CHK_YEAR);
        if (DDB5700_AWA_5700.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            if (DDB5700_AWA_5700.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDB5700_AWA_5700.FUNC);
            S000_ERR_MSG_PROC();
        }
        if (DDB5700_AWA_5700.FUNC == 'C' 
            && DDB5700_AWA_5700.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSSTDT);
        DDCSSTDT.FUNC = DDB5700_AWA_5700.FUNC;
        DDCSSTDT.BR = DDB5700_AWA_5700.BR;
        DDCSSTDT.CHK_YEAR = DDB5700_AWA_5700.CHK_YEAR;
        DDCSSTDT.AC_NO = DDB5700_AWA_5700.AC_NO;
        S000_CALL_DDZSSTDT();
    }
    public void S000_CALL_DDZSSTDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSSTDT", DDCSSTDT);
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
