package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1625 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCDPK DDCSCDPK = new DDCSCDPK();
    SCCGWA SCCGWA;
    DDB1625_AWA_1625 DDB1625_AWA_1625;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1625 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1625_AWA_1625>");
        DDB1625_AWA_1625 = (DDB1625_AWA_1625) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1625_AWA_1625.AC_NO);
        CEP.TRC(SCCGWA, DDB1625_AWA_1625.ID_TYP);
        CEP.TRC(SCCGWA, DDB1625_AWA_1625.ID_NO);
        if (DDB1625_AWA_1625.AC_NO.trim().length() == 0 
            && DDB1625_AWA_1625.ID_TYP.trim().length() == 0 
            && DDB1625_AWA_1625.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CAN_NOT_ALL_SPACE);
            S000_ERR_MSG_PROC();
        }
        if ((DDB1625_AWA_1625.ID_TYP.trim().length() == 0 
            && DDB1625_AWA_1625.ID_NO.trim().length() > 0) 
            || (DDB1625_AWA_1625.ID_TYP.trim().length() > 0 
            && DDB1625_AWA_1625.ID_NO.trim().length() == 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCDPK);
        DDCSCDPK.AC_NO = DDB1625_AWA_1625.AC_NO;
        DDCSCDPK.ID_TYP = DDB1625_AWA_1625.ID_TYP;
        DDCSCDPK.ID_NO = DDB1625_AWA_1625.ID_NO;
        S000_CALL_DDZSCDPK();
    }
    public void S000_CALL_DDZSCDPK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCDPK", DDCSCDPK);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
