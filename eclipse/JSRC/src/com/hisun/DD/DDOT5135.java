package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5135 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDDTD DDCSDDTD = new DDCSDDTD();
    SCCGWA SCCGWA;
    DDB5130_AWA_5130 DDB5130_AWA_5130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5135 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5130_AWA_5130>");
        DDB5130_AWA_5130 = (DDB5130_AWA_5130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5130_AWA_5130.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4011;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.PROL_NO.trim().length() == 0 
            || DDB5130_AWA_5130.CON_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4020;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5130_AWA_5130.W_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4028;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDDTD);
        DDCSDDTD.FUNC = DDB5130_AWA_5130.FUNC;
        DDCSDDTD.AC = DDB5130_AWA_5130.AC;
        DDCSDDTD.PROL_NO = DDB5130_AWA_5130.PROL_NO;
        DDCSDDTD.CON_NO = DDB5130_AWA_5130.CON_NO;
        DDCSDDTD.AC_CNM = DDB5130_AWA_5130.AC_CNM;
        DDCSDDTD.AC_ENM = DDB5130_AWA_5130.AC_ENM;
        DDCSDDTD.CCY = DDB5130_AWA_5130.CCY;
        DDCSDDTD.CCY_TYPE = DDB5130_AWA_5130.CCY_TYPE;
        DDCSDDTD.PROL_AMT = DDB5130_AWA_5130.PROL_AMT;
        DDCSDDTD.PROL_BAL = DDB5130_AWA_5130.PROL_BAL;
        DDCSDDTD.TENOR = DDB5130_AWA_5130.TENOR;
        DDCSDDTD.VAL_DATE = DDB5130_AWA_5130.VAL_DATE;
        DDCSDDTD.MAT_DATE = DDB5130_AWA_5130.MAT_DATE;
        DDCSDDTD.RATE = DDB5130_AWA_5130.RATE;
        DDCSDDTD.BRK_RATE = DDB5130_AWA_5130.BRK_RATE;
        DDCSDDTD.B_INT = DDB5130_AWA_5130.B_INT;
        DDCSDDTD.W_AMT = DDB5130_AWA_5130.W_AMT;
        DDCSDDTD.REMARKS = DDB5130_AWA_5130.REMARKS;
        S000_CALL_DDZSDDTD();
    }
    public void S000_CALL_DDZSDDTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDTD", DDCSDDTD);
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
