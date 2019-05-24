package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5240 {
    String WS_MSGID = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMPWD DDCSMPWD = new DDCSMPWD();
    SCCGWA SCCGWA;
    DDB5240_AWA_5240 DDB5240_AWA_5240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5240 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5240_AWA_5240>");
        DDB5240_AWA_5240 = (DDB5240_AWA_5240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5240_AWA_5240.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5240_AWA_5240.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5240_AWA_5240.PAY_MTH != '2') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_PAY_MTH_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5240_AWA_5240.FUNC);
        CEP.TRC(SCCGWA, DDB5240_AWA_5240.AC);
        CEP.TRC(SCCGWA, DDB5240_AWA_5240.PAY_MTH);
        IBS.init(SCCGWA, DDCSMPWD);
        DDCSMPWD.FUNC = DDB5240_AWA_5240.FUNC;
        DDCSMPWD.CARD_NO = DDB5240_AWA_5240.CARD_NO;
        DDCSMPWD.AC = DDB5240_AWA_5240.AC;
        DDCSMPWD.AC_CNAME = DDB5240_AWA_5240.AC_CNAME;
        DDCSMPWD.AC_ENAME = DDB5240_AWA_5240.AC_ENAME;
        DDCSMPWD.PSBK_NO = DDB5240_AWA_5240.PSBK_NO;
        DDCSMPWD.PAY_MTH = DDB5240_AWA_5240.PAY_MTH;
        DDCSMPWD.PSWD_OLD = DDB5240_AWA_5240.PSW_OLD;
        DDCSMPWD.PSWD_NEW = DDB5240_AWA_5240.PSW_NEW;
        DDCSMPWD.PSWD_NEW2 = DDB5240_AWA_5240.PSW_NEW2;
        DDCSMPWD.ID_TYP = DDB5240_AWA_5240.ID_TYP;
        DDCSMPWD.ID_NO = DDB5240_AWA_5240.ID_NO;
        DDCSMPWD.LOS_NO = DDB5240_AWA_5240.LOS_NO;
        CEP.TRC(SCCGWA, DDCSMPWD.FUNC);
        CEP.TRC(SCCGWA, DDCSMPWD.AC);
        CEP.TRC(SCCGWA, DDCSMPWD.PAY_MTH);
        S000_CALL_DDZSMPWD();
    }
    public void S000_CALL_DDZSMPWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMPWD", DDCSMPWD);
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
