package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8821 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSBRFT DDCSBRFT = new DDCSBRFT();
    SCCGWA SCCGWA;
    DDB8821_AWA_8821 DDB8821_AWA_8821;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT8821 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8821_AWA_8821>");
        DDB8821_AWA_8821 = (DDB8821_AWA_8821) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8821_AWA_8821.FUNC);
        CEP.TRC(SCCGWA, DDB8821_AWA_8821.CUS_AC);
        CEP.TRC(SCCGWA, DDB8821_AWA_8821.CCY);
        CEP.TRC(SCCGWA, DDB8821_AWA_8821.CCY_TYP);
        CEP.TRC(SCCGWA, DDB8821_AWA_8821.CI_NO);
        CEP.TRC(SCCGWA, DDB8821_AWA_8821.SEQ);
        if (DDB8821_AWA_8821.SEQ == 0) {
            DDB8821_AWA_8821.SEQ = 1;
        }
        if (DDB8821_AWA_8821.FUNC == 'B') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBRFT);
        DDCSBRFT.FUNC = DDB8821_AWA_8821.FUNC;
        DDCSBRFT.CUS_AC = DDB8821_AWA_8821.CUS_AC;
        DDCSBRFT.CCY = DDB8821_AWA_8821.CCY;
        DDCSBRFT.CCY_TYP = DDB8821_AWA_8821.CCY_TYP;
        DDCSBRFT.CI_NO = DDB8821_AWA_8821.CI_NO;
        DDCSBRFT.CI_CNM = DDB8821_AWA_8821.CI_CNM;
        DDCSBRFT.SEQ = DDB8821_AWA_8821.SEQ;
        S000_CALL_DDZSBRFT();
    }
    public void S000_CALL_DDZSBRFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSBRFT", DDCSBRFT);
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
