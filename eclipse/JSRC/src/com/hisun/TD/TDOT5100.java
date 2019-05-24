package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5100 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMPRD TDCMPRD = new TDCMPRD();
    TDCMACO TDCMACO = new TDCMACO();
    SCCGWA SCCGWA;
    TDB5100_AWA_5100 TDB5100_AWA_5100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5100_AWA_5100>");
        TDB5100_AWA_5100 = (TDB5100_AWA_5100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_CALL_TDTMACO();
    }
    public void B030_CALL_TDTMACO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMACO);
        S000_CALL_TDZMACO();
    }
    public void S000_CALL_TDZMACO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TZ-R-WRIT-NHISD", TDCMACO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
