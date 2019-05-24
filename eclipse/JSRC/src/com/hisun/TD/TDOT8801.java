package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT8801 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMPRD TDCMPRD = new TDCMPRD();
    TDCIFQFX TDCIFQFX = new TDCIFQFX();
    SCCGWA SCCGWA;
    TDB8801_AWA_8801 TDB8801_AWA_8801;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT8801 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB8801_AWA_8801>");
        TDB8801_AWA_8801 = (TDB8801_AWA_8801) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_CALL_TDZFQFX();
    }
    public void B030_CALL_TDZFQFX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCIFQFX);
        TDCIFQFX.FUNC = TDB8801_AWA_8801.FUNC;
        TDCIFQFX.AC_NO = TDB8801_AWA_8801.AC_NO;
        TDCIFQFX.CI_NO = TDB8801_AWA_8801.CI_NO;
        S000_CALL_TDZFQFX();
    }
    public void S000_CALL_TDZFQFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TST-TDZFQFX", TDCIFQFX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
