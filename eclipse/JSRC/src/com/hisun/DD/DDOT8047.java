package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8047 {
    DBParm DDTMST_RD;
    String WS_ERR_MSG = " ";
    String WS_STD_AC = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    DDCSQVFA DDCSQVFA = new DDCSQVFA();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDB8047_AWA_8047 DDB8047_AWA_8047;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT8047 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8047_AWA_8047>");
        DDB8047_AWA_8047 = (DDB8047_AWA_8047) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_STD_AC_INFO();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8047_AWA_8047.AC_NO);
        CEP.TRC(SCCGWA, DDB8047_AWA_8047.CCY);
        CEP.TRC(SCCGWA, DDB8047_AWA_8047.CCY_TYP);
        if (DDB8047_AWA_8047.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_STD_AC_INFO() throws IOException,SQLException,Exception {
        WS_STD_AC = DDB8047_AWA_8047.AC_NO;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = WS_STD_AC;
        T000_READ_DDTMST();
        if (DDRMST.AC_TYPE == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_GDWR_NOT_ALLOW_INQ;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQIFA);
        DDCSQIFA.INPUT_DATA.AC_NO = WS_STD_AC;
        DDCSQIFA.INPUT_DATA.CCY = DDB8047_AWA_8047.CCY;
        DDCSQIFA.INPUT_DATA.CCY_TYPE = DDB8047_AWA_8047.CCY_TYP;
        DDCSQIFA.INPUT_DATA.AC_OLD = DDB8047_AWA_8047.AC_NO;
        S000_CALL_DDZSQIFA();
    }
    public void B050_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQVFA);
        DDCSQVFA.INPUT_DATA.AC_NO = DDB8047_AWA_8047.AC_NO;
        S000_CALL_DDZSQVFA();
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSQIFA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-IFA", DDCSQIFA);
    }
    public void S000_CALL_DDZSQVFA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQVFA", DDCSQVFA);
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
