package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2128 {
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    String WS_ERR_MSG = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNCSBASC LNCSBASC = new LNCSBASC();
    SCCGWA SCCGWA;
    LNB2128_AWA_2128 LNB2128_AWA_2128;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT2128 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2128_AWA_2128>");
        LNB2128_AWA_2128 = (LNC2128X_AWA_2128) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CALL_SVR_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB2128_AWA_2128.CONT_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSBASC);
        LNCSBASC.CONT_NO = LNB2128_AWA_2128.CONT_NO;
        S000_CALL_LNZSBASC();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSBASC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-INQ-BASIC", LNCSBASC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    Z_RET();
    if (pgmRtn) return;
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
