package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT4510 {
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQTOT DDCSQTOT = new DDCSQTOT();
    SCCGWA SCCGWA;
    DDB4510_AWA_4510 DDB4510_AWA_4510;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT4510 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB4510_AWA_4510>");
        DDB4510_AWA_4510 = (DDB4510_AWA_4510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B030_TRANS_MAIN_PROC();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB4510_AWA_4510.AC_NO);
        CEP.TRC(SCCGWA, DDB4510_AWA_4510.CCY);
        CEP.TRC(SCCGWA, DDB4510_AWA_4510.CCY_TYP);
        CEP.TRC(SCCGWA, DDB4510_AWA_4510.DATE);
        if (DDB4510_AWA_4510.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB4510_AWA_4510.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB4510_AWA_4510.CCY_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB4510_AWA_4510.DATE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TR_DATE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQTOT);
        DDCSQTOT.AC_NO = DDB4510_AWA_4510.AC_NO;
        DDCSQTOT.CCY = DDB4510_AWA_4510.CCY;
        DDCSQTOT.CCY_TYP = DDB4510_AWA_4510.CCY_TYP;
        DDCSQTOT.DATE = DDB4510_AWA_4510.DATE;
        S000_CALL_DDZSQTOT();
    }
    public void S000_CALL_DDZSQTOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQTOT", DDCSQTOT);
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
