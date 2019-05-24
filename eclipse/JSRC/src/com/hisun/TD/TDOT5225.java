package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5225 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCQAC TDCQAC = new TDCQAC();
    SCCGWA SCCGWA;
    TDB5225_AWA_5225 TDB5225_AWA_5225;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5225 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5225_AWA_5225>");
        TDB5225_AWA_5225 = (TDB5225_AWA_5225) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5225_AWA_5225.SRCH_FLG == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_SRCH_FIG_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQAC);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.CI_NO);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.AC_NO);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.CI_TYP);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.STS);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.SRCH_FLG);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.BV_TYP);
        CEP.TRC(SCCGWA, TDB5225_AWA_5225.CCY);
        TDCQAC.CI_NO = TDB5225_AWA_5225.CI_NO;
        TDCQAC.AC_NO = TDB5225_AWA_5225.AC_NO;
        TDCQAC.CI_TYP = TDB5225_AWA_5225.CI_TYP;
        TDCQAC.STS = TDB5225_AWA_5225.STS;
        TDCQAC.SRCH_FLG = TDB5225_AWA_5225.SRCH_FLG;
        TDCQAC.BV_TYP = TDB5225_AWA_5225.BV_TYP;
        TDCQAC.CCY = TDB5225_AWA_5225.CCY;
        TDCQAC.SDT = TDB5225_AWA_5225.SDT;
        TDCQAC.DDT = TDB5225_AWA_5225.DDT;
        S000_CALL_TDZQAC();
    }
    public void S000_CALL_TDZQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-Q-AC-I", TDCQAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
