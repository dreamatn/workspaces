package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5010 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMPRD TDCMPRD = new TDCMPRD();
    SCCGWA SCCGWA;
    TDB5010_AWA_5010 TDB5010_AWA_5010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5010_AWA_5010>");
        TDB5010_AWA_5010 = (TDB5010_AWA_5010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_QUERY_MPRD_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5010_AWA_5010.FUNC != 'I' 
            && TDB5010_AWA_5010.FUNC != 'A' 
            && TDB5010_AWA_5010.FUNC != 'M') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FUNC_ERR);
        }
        if (TDB5010_AWA_5010.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B030_QUERY_MPRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMPRD);
        TDCMPRD.FUNC = TDB5010_AWA_5010.FUNC;
        TDCMPRD.PROD_CD_M = TDB5010_AWA_5010.PROD_CDM;
        TDCMPRD.PROD_CD = TDB5010_AWA_5010.PROD_CD;
        TDCMPRD.FUNC = 'I';
        S000_CALL_TDZMPRD();
    }
    public void S000_CALL_TDZMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MPRD-MAINT", TDCMPRD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
