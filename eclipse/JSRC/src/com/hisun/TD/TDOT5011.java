package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5011 {
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
        CEP.TRC(SCCGWA, "TDOT5011 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5010_AWA_5010>");
        TDB5010_AWA_5010 = (TDB5010_AWA_5010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_ADD_MPRD_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5010_AWA_5010.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDB5010_AWA_5010.EFF_DT == 0) {
            TDB5010_AWA_5010.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (TDB5010_AWA_5010.EXP_DT == 0) {
            if ("99991231".trim().length() == 0) TDB5010_AWA_5010.EXP_DT = 0;
            else TDB5010_AWA_5010.EXP_DT = Integer.parseInt("99991231");
        }
        if (TDB5010_AWA_5010.EFF_DT >= TDB5010_AWA_5010.EXP_DT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_EFFDT);
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= TDB5010_AWA_5010.EXP_DT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
        }
        if (TDB5010_AWA_5010.TYP_DESC.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDB5010_AWA_5010.MTH_DESC.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDB5010_AWA_5010.CR_LMT == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDB5010_AWA_5010.DR_LMT == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDB5010_AWA_5010.MIN_CCYC.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B030_ADD_MPRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMPRD);
        TDCMPRD.PROD_CD_M = TDB5010_AWA_5010.PROD_CDM;
        TDCMPRD.PROD_CD = TDB5010_AWA_5010.PROD_CD;
        TDCMPRD.CDESC = TDB5010_AWA_5010.CDESC;
        TDCMPRD.EFF_DT = TDB5010_AWA_5010.EFF_DT;
        TDCMPRD.EXP_DT = TDB5010_AWA_5010.EXP_DT;
        TDCMPRD.BV_TYP_DESC = TDB5010_AWA_5010.TYP_DESC;
        TDCMPRD.DRAW_MTH_DESC = TDB5010_AWA_5010.MTH_DESC;
        TDCMPRD.CROS_CR_LMT = TDB5010_AWA_5010.CR_LMT;
        TDCMPRD.CROS_DR_LMT = TDB5010_AWA_5010.DR_LMT;
        TDCMPRD.MIN_CCYC = TDB5010_AWA_5010.MIN_CCYC;
        TDCMPRD.FUNC = 'A';
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
