package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5750 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCSACHK DDCSACHK = new DDCSACHK();
    DDCSBCHK DDCSBCHK = new DDCSBCHK();
    SCCGWA SCCGWA;
    DDB5750_AWA_5750 DDB5750_AWA_5750;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5750 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5750_AWA_5750>");
        DDB5750_AWA_5750 = (DDB5750_AWA_5750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B020_GET_AC_TYP_PROC();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5750_AWA_5750.ACNO);
        CEP.TRC(SCCGWA, DDB5750_AWA_5750.CHK_RLT);
        if (DDB5750_AWA_5750.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5750_AWA_5750.CHK_RLT == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHK_RLT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_GET_AC_TYP_PROC() throws IOException,SQLException,Exception {
        if (DDB5750_AWA_5750.ACNO.trim().length() > 0) {
            B030_TRANS_MAIN_PROC();
        }
    }
    public void B030_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSACHK);
        DDCSACHK.ACNO = DDB5750_AWA_5750.ACNO;
        DDCSACHK.CHK_RLT = DDB5750_AWA_5750.CHK_RLT;
        DDCSACHK.RMK = DDB5750_AWA_5750.RMK;
        DDCSACHK.CN_NAME = DDB5750_AWA_5750.CN_NAME;
        DDCSACHK.EN_NAME = DDB5750_AWA_5750.EN_NAME;
        DDCSACHK.CCY = DDB5750_AWA_5750.CCY;
        DDCSACHK.CCY_TYPE = DDB5750_AWA_5750.CCY_TYPE;
        DDCSACHK.CHK_YEAR = DDB5750_AWA_5750.CHK_YEAR;
        DDCSACHK.OLD_STS = DDB5750_AWA_5750.OLD_STS;
        S000_CALL_DDZSACHK();
    }
    public void B050_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBCHK);
        DDCSBCHK.ACNO = DDB5750_AWA_5750.ACNO;
        DDCSBCHK.CHK_RLT = DDB5750_AWA_5750.CHK_RLT;
        DDCSBCHK.RMK = DDB5750_AWA_5750.RMK;
        CEP.TRC(SCCGWA, DDB5750_AWA_5750.ACNO);
        CEP.TRC(SCCGWA, DDCSBCHK.ACNO);
        S000_CALL_DDZSBCHK();
    }
    public void S000_CALL_DDZSACHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSACHK", DDCSACHK);
    }
    public void S000_CALL_DDZSBCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSBCHK", DDCSBCHK);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        CEP.TRC(SCCGWA, "000000000000");
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
