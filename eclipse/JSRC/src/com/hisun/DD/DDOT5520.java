package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5520 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSCHPL DDCSCHPL = new DDCSCHPL();
    SCCGWA SCCGWA;
    DDB5520_AWA_5520 DDB5520_AWA_5520;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5520 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5520_AWA_5520>");
        DDB5520_AWA_5520 = (DDB5520_AWA_5520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5520_AWA_5520.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5520_AWA_5520.NPAGENO == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5520_AWA_5520.NLINENO == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.AC);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.CNAME);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.ENAME);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.OPAGENO);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.OLINENO);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.NPAGENO);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.NLINENO);
        CEP.TRC(SCCGWA, DDB5520_AWA_5520.REMARK);
        IBS.init(SCCGWA, DDCSCHPL);
        DDCSCHPL.AC = DDB5520_AWA_5520.AC;
        DDCSCHPL.AC_CNAME = DDB5520_AWA_5520.CNAME;
        DDCSCHPL.AC_ENAME = DDB5520_AWA_5520.ENAME;
        DDCSCHPL.OPSBK_PAGE = DDB5520_AWA_5520.OPAGENO;
        DDCSCHPL.OPSBK_LINE = DDB5520_AWA_5520.OLINENO;
        DDCSCHPL.PSBK_PAGE = DDB5520_AWA_5520.NPAGENO;
        DDCSCHPL.PSBK_LINE = DDB5520_AWA_5520.NLINENO;
        S000_CALL_DDZSCHPL();
    }
    public void S000_CALL_DDZSCHPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSCHPL", DDCSCHPL);
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
