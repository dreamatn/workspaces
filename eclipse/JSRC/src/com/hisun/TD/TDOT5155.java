package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5155 {
    String WS_ERR_MSG = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSCLOS TDCSCLOS = new TDCSCLOS();
    SCCGWA SCCGWA;
    TDB5155_AWA_5155 TDB5155_AWA_5155;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5155 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5155_AWA_5155>");
        TDB5155_AWA_5155 = (TDB5155_AWA_5155) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5155_AWA_5155.AC);
        CEP.TRC(SCCGWA, TDB5155_AWA_5155.LOS_NO);
        B100_CHECK_INPUT_PROC();
        B200_GET_LIST();
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDB5155_AWA_5155.AC.trim().length() == 0 
            && TDB5155_AWA_5155.LOS_NO.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSCLOS);
        TDCSCLOS.AC = TDB5155_AWA_5155.AC;
        TDCSCLOS.LOS_NO = TDB5155_AWA_5155.LOS_NO;
        TDCSCLOS.BV_NO = TDB5155_AWA_5155.BV_NO;
        S000_CALL_TDZSCLOS();
    }
    public void S000_CALL_TDZSCLOS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PRO-TDZSCLOS", TDCSCLOS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
