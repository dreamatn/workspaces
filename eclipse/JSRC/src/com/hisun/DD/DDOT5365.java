package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5365 {
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSQZFM DDCSQZFM = new DDCSQZFM();
    SCCGWA SCCGWA;
    DDB5365_AWA_5365 DDB5365_AWA_5365;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5365 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5365_AWA_5365>");
        DDB5365_AWA_5365 = (DDB5365_AWA_5365) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "**********INPUT DATA:");
        CEP.TRC(SCCGWA, DDB5365_AWA_5365.AC);
        CEP.TRC(SCCGWA, "*********************");
        if (DDB5365_AWA_5365.AC.trim().length() == 0 
            && (DDB5365_AWA_5365.STR_DT == 0 
            && DDB5365_AWA_5365.STOP_DT == 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQZFM);
        DDCSQZFM.AC = DDB5365_AWA_5365.AC;
        DDCSQZFM.STR_DT = DDB5365_AWA_5365.STR_DT;
        DDCSQZFM.STOP_DT = DDB5365_AWA_5365.STOP_DT;
        S000_CALL_DDZSQZFM();
    }
    public void S000_CALL_DDZSQZFM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQZFM", DDCSQZFM);
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
