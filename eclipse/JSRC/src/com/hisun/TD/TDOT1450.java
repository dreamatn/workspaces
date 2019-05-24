package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1450 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCCHGD TDCCHGD = new TDCCHGD();
    SCCGWA SCCGWA;
    TDB1450_AWA_1450 TDB1450_AWA_1450;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1450 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1450_AWA_1450>");
        TDB1450_AWA_1450 = (TDB1450_AWA_1450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB1450_AWA_1450.AC.trim().length() > 0) {
            if (TDB1450_AWA_1450.SEQ == 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ;
                S000_ERR_MSG_PROC();
            }
        }
        if (TDB1450_AWA_1450.TERM.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (TDB1450_AWA_1450.EXP_DATE == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1450_AWA_1450.AC);
        CEP.TRC(SCCGWA, TDB1450_AWA_1450.SEQ);
        CEP.TRC(SCCGWA, TDB1450_AWA_1450.TD_AC);
        CEP.TRC(SCCGWA, TDB1450_AWA_1450.TERM);
        CEP.TRC(SCCGWA, TDB1450_AWA_1450.EXP_DATE);
        IBS.init(SCCGWA, TDCCHGD);
        TDCCHGD.AC = TDB1450_AWA_1450.AC;
        TDCCHGD.SEQ = TDB1450_AWA_1450.SEQ;
        TDCCHGD.TD_AC = TDB1450_AWA_1450.TD_AC;
        TDCCHGD.TERM = TDB1450_AWA_1450.TERM;
        TDCCHGD.EXP_DATE = TDB1450_AWA_1450.EXP_DATE;
        S000_CALL_TDZCHGD();
    }
    public void S000_CALL_TDZCHGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZCHGD", TDCCHGD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
