package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1650 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMARK DDCSMARK = new DDCSMARK();
    SCCGWA SCCGWA;
    DDB1650_AWA_1650 DDB1650_AWA_1650;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1650 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1650_AWA_1650>");
        DDB1650_AWA_1650 = (DDB1650_AWA_1650) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1650_AWA_1650.AC);
        CEP.TRC(SCCGWA, DDB1650_AWA_1650.CCY);
        CEP.TRC(SCCGWA, DDB1650_AWA_1650.CCY_TYPE);
        if (DDB1650_AWA_1650.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1650_AWA_1650.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1650_AWA_1650.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB1650_AWA_1650.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1650_AWA_1650.CCY_TYPE != '1' 
            && DDB1650_AWA_1650.CCY_TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            WS_FLD_NO = DDB1650_AWA_1650.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMARK);
        DDCSMARK.BR = DDB1650_AWA_1650.BR;
        DDCSMARK.AC = DDB1650_AWA_1650.AC;
        DDCSMARK.AC_NM = DDB1650_AWA_1650.AC_NM;
        DDCSMARK.CCY = DDB1650_AWA_1650.CCY;
        DDCSMARK.CCY_TYPE = DDB1650_AWA_1650.CCY_TYPE;
        DDCSMARK.AUTO_FLG = DDB1650_AWA_1650.AUTO_FLG;
        DDCSMARK.OPT_FLG = DDB1650_AWA_1650.OPT_FLG;
        S000_CALL_DDZSMARK();
    }
    public void S000_CALL_DDZSMARK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSMARK", DDCSMARK);
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
