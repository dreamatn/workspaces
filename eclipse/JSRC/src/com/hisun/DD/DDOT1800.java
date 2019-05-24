package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1800 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQCAC DDCSQCAC = new DDCSQCAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DDB1800_AWA_1800 DDB1800_AWA_1800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1800 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1800_AWA_1800>");
        DDB1800_AWA_1800 = (DDB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ACNO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.DR_CARD);
        if (DDB1800_AWA_1800.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1800_AWA_1800.ACNO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQCAC);
        DDCSQCAC.DR_CARD = DDB1800_AWA_1800.DR_CARD;
        DDCSQCAC.AC_NO = DDB1800_AWA_1800.ACNO;
        DDCSQCAC.AC_CNM = DDB1800_AWA_1800.AC_CNM;
        DDCSQCAC.AC_ENM = DDB1800_AWA_1800.AC_ENM;
        DDCSQCAC.PSBK_NO = DDB1800_AWA_1800.PSBK_NO;
        S000_CALL_DDZSQCAC();
    }
    public void S000_CALL_DDZSQCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-INQ-CLS-AC", DDCSQCAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
