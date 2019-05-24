package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5450 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCPRSDL TDCPRSDL = new TDCPRSDL();
    SCCGWA SCCGWA;
    TDB5450_AWA_5450 TDB5450_AWA_5450;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5450 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5450_AWA_5450>");
        TDB5450_AWA_5450 = (TDB5450_AWA_5450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5450_AWA_5450.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, WS_MSGID);
        }
        if (TDB5450_AWA_5450.ACTI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPRSDL);
        TDCPRSDL.PROD_CD = TDB5450_AWA_5450.PROD_CD;
        TDCPRSDL.ACTI_NO = TDB5450_AWA_5450.ACTI_NO;
        TDCPRSDL.MAX_RAT = TDB5450_AWA_5450.MAX_RAT;
        TDCPRSDL.MIN_RAT = TDB5450_AWA_5450.MIN_RAT;
        TDCPRSDL.ZZ_RAT = TDB5450_AWA_5450.ZZ_RAT;
        TDCPRSDL.SHX_DT = TDB5450_AWA_5450.SHX_DT;
        TDCPRSDL.SHI_DT = TDB5450_AWA_5450.SHI_DT;
        TDCPRSDL.MJ_BAL = TDB5450_AWA_5450.MJ_BAL;
        TDCPRSDL.SDT = TDB5450_AWA_5450.SDT;
        TDCPRSDL.DDT = TDB5450_AWA_5450.DDT;
        S000_CALL_TDZPRSDL();
    }
    public void S000_CALL_TDZPRSDL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZPRSDL", TDCPRSDL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
