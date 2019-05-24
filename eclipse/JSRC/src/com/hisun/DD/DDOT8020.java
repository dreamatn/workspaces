package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8020 {
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSIBAL DDCSIBAL = new DDCSIBAL();
    SCCGWA SCCGWA;
    DDB8020_AWA_8020 DDB8020_AWA_8020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT8020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8020_AWA_8020>");
        DDB8020_AWA_8020 = (DDB8020_AWA_8020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "**********INPUT DATA:");
        CEP.TRC(SCCGWA, DDB8020_AWA_8020.AC_NO);
        CEP.TRC(SCCGWA, DDB8020_AWA_8020.CCY);
        CEP.TRC(SCCGWA, DDB8020_AWA_8020.CCY_TYPE);
        CEP.TRC(SCCGWA, "*********************");
        if (DDB8020_AWA_8020.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8020_AWA_8020.CCY.equalsIgnoreCase("156")) {
            DDB8020_AWA_8020.CCY_TYPE = '1';
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSIBAL);
        DDCSIBAL.AC_NO = DDB8020_AWA_8020.AC_NO;
        DDCSIBAL.CCY = DDB8020_AWA_8020.CCY;
        DDCSIBAL.CCY_TYPE = DDB8020_AWA_8020.CCY_TYPE;
        S000_CALL_DDZSIBAL();
    }
    public void S000_CALL_DDZSIBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSIBAL", DDCSIBAL);
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
