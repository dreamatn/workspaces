package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5220 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCACE TDCACE = new TDCACE();
    SCCGWA SCCGWA;
    TDB5220_AWA_5220 TDB5220_AWA_5220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5220 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5220_AWA_5220>");
        TDB5220_AWA_5220 = (TDB5220_AWA_5220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5220_AWA_5220.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACE);
        TDCACE.FMT_FLG = 'Y';
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.BV_TYP);
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.AC_NO);
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.AC_SEQ);
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.BV_NO);
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.AC_TYP);
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.AC_STS);
        TDCACE.PAGE_INF.I_BV_TYP = TDB5220_AWA_5220.BV_TYP;
        TDCACE.PAGE_INF.AC_NO = TDB5220_AWA_5220.AC_NO;
        TDCACE.PAGE_INF.I_AC_SEQ = TDB5220_AWA_5220.AC_SEQ;
        TDCACE.PAGE_INF.I_BV_NO = TDB5220_AWA_5220.BV_NO;
        TDCACE.PAGE_INF.QCD = TDB5220_AWA_5220.AC_TYP;
        TDCACE.DATA[1-1].ACO_STS = TDB5220_AWA_5220.AC_STS;
        TDCACE.PAGE_INF.CCY_I = TDB5220_AWA_5220.CCY_I;
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.PAGE_ROW);
        CEP.TRC(SCCGWA, TDB5220_AWA_5220.PAGE_NUM);
        TDCACE.PAGE_INF.PAGE_ROW = TDB5220_AWA_5220.PAGE_ROW;
        TDCACE.PAGE_INF.PAGE_NUM = TDB5220_AWA_5220.PAGE_NUM;
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
        S000_CALL_TDZACE();
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE);
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
