package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5136 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSRATE DDCSRATE = new DDCSRATE();
    SCCGWA SCCGWA;
    DDB5136_AWA_5136 DDB5136_AWA_5136;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5136 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5136_AWA_5136>");
        DDB5136_AWA_5136 = (DDB5136_AWA_5136) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB5136_AWA_5136.FLOAT_TP == '1' 
            && DDB5136_AWA_5136.F_SPRD == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4024;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5136_AWA_5136.FLOAT_TP == '2' 
            && DDB5136_AWA_5136.F_PCNT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4025;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5136_AWA_5136.FLOAT_TP != ' ' 
            && DDB5136_AWA_5136.PMT_RATE != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4027;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5136_AWA_5136.RATE_TYP.trim().length() == 0 
            && DDB5136_AWA_5136.PMT_RATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4026;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSRATE);
        DDCSRATE.RATE_TYP = DDB5136_AWA_5136.RATE_TYP;
        DDCSRATE.RAT_TERM = DDB5136_AWA_5136.RAT_TERM;
        DDCSRATE.FLOAT_TP = DDB5136_AWA_5136.FLOAT_TP;
        DDCSRATE.F_SPRD = DDB5136_AWA_5136.F_SPRD;
        DDCSRATE.F_PCNT = DDB5136_AWA_5136.F_PCNT;
        DDCSRATE.CCY = DDB5136_AWA_5136.CCY;
        CEP.TRC(SCCGWA, DDCSRATE.RATE_TYP);
        CEP.TRC(SCCGWA, DDCSRATE.RAT_TERM);
        CEP.TRC(SCCGWA, DDCSRATE.FLOAT_TP);
        CEP.TRC(SCCGWA, DDCSRATE.F_SPRD);
        CEP.TRC(SCCGWA, DDCSRATE.F_PCNT);
        CEP.TRC(SCCGWA, DDCSRATE.CCY);
        S000_CALL_DDZSRATE();
    }
    public void S000_CALL_DDZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-RATE", DDCSRATE);
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
