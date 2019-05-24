package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5440 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCPRSD TDCPRSD = new TDCPRSD();
    SCCGWA SCCGWA;
    TDB5440_AWA_5440 TDB5440_AWA_5440;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5440 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5440_AWA_5440>");
        TDB5440_AWA_5440 = (TDB5440_AWA_5440) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5440_AWA_5440.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDB5440_AWA_5440.ACTI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPRSD);
        TDCPRSD.PROD_CD = TDB5440_AWA_5440.PROD_CD;
        TDCPRSD.ACTI_NO = TDB5440_AWA_5440.ACTI_NO;
        TDCPRSD.FUNC = TDB5440_AWA_5440.FUNC;
        TDCPRSD.MAX_RAT = TDB5440_AWA_5440.MAX_RAT;
        TDCPRSD.MIN_RAT = TDB5440_AWA_5440.MIN_RAT;
        TDCPRSD.ZZ_RAT = TDB5440_AWA_5440.ZZ_RAT;
        TDCPRSD.SHX_DT = TDB5440_AWA_5440.SHX_DT;
        TDCPRSD.SHI_DT = TDB5440_AWA_5440.SHI_DT;
        TDCPRSD.MJ_BAL = TDB5440_AWA_5440.MJ_BAL;
        TDCPRSD.SDT = TDB5440_AWA_5440.SDT;
        TDCPRSD.DDT = TDB5440_AWA_5440.DDT;
        S000_CALL_TDZPRSD();
    }
    public void S000_CALL_TDZPRSD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZPRSD", TDCPRSD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
