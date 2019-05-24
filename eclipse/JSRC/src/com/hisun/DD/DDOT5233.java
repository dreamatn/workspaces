package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5233 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSBFJQ DDCSBFJQ = new DDCSBFJQ();
    SCCGWA SCCGWA;
    DDB5233_AWA_5233 DDB5233_AWA_5233;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5233 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5233_AWA_5233>");
        DDB5233_AWA_5233 = (DDB5233_AWA_5233) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        B100_CHECK_INPUT_DATA();
        CEP.TRC(SCCGWA, "2222");
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SFSAFASF");
        CEP.TRC(SCCGWA, DDB5233_AWA_5233.FUNC);
        if (DDB5233_AWA_5233.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4011;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBFJQ);
        DDCSBFJQ.FUNC = DDB5233_AWA_5233.FUNC;
        DDCSBFJQ.TYPE = DDB5233_AWA_5233.TYP;
        DDCSBFJQ.CTL_TYPE = DDB5233_AWA_5233.CTL_TYPE;
        DDCSBFJQ.AC_TYPE = DDB5233_AWA_5233.AC_TYPE;
        DDCSBFJQ.BANK_NO = DDB5233_AWA_5233.BANK_NO;
        DDCSBFJQ.CI_NO = DDB5233_AWA_5233.CI_NO;
        DDCSBFJQ.OTH_AC = DDB5233_AWA_5233.OTH_AC;
        DDCSBFJQ.S_LMT = DDB5233_AWA_5233.S_LMT;
        DDCSBFJQ.DAMT_LMT = DDB5233_AWA_5233.DAMT_LMT;
        DDCSBFJQ.DCNT_LMT = DDB5233_AWA_5233.DCNT_LMT;
        DDCSBFJQ.MAMT_LMT = DDB5233_AWA_5233.MAMT_LMT;
        DDCSBFJQ.MCNT_LMT = DDB5233_AWA_5233.MCNT_LMT;
        DDCSBFJQ.BASE_LMT = DDB5233_AWA_5233.BASE_LMT;
        S000_CALL_DDZSBFJQ();
    }
    public void S000_CALL_DDZSBFJQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSBFJQ", DDCSBFJQ);
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
