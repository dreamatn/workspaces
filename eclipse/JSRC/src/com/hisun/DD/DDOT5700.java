package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5700 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSSTDT DDCSSTDT = new DDCSSTDT();
    SCCGWA SCCGWA;
    DDB5700_AWA_5700 DDB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5700 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5700_AWA_5700>");
        DDB5700_AWA_5700 = (DDB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B030_TRANS_MAIN_PROC();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSSTDT);
        DDCSSTDT.FUNC = DDB5700_AWA_5700.FUNC;
        DDCSSTDT.BR = DDB5700_AWA_5700.BR;
        DDCSSTDT.CHK_YEAR = DDB5700_AWA_5700.CHK_YEAR;
        DDCSSTDT.STR_DATE = DDB5700_AWA_5700.STR_DATE;
        DDCSSTDT.END_DATE = DDB5700_AWA_5700.END_DATE;
        DDCSSTDT.RMK = DDB5700_AWA_5700.RMK;
        S000_CALL_DDZSSTDT();
    }
    public void S000_CALL_DDZSSTDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSSTDT", DDCSSTDT);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
