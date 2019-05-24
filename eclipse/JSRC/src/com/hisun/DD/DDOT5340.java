package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5340 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMFEE DDCSMFEE = new DDCSMFEE();
    SCCGWA SCCGWA;
    DDB5340_AWA_5340 DDB5340_AWA_5340;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5340 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5340_AWA_5340>");
        DDB5340_AWA_5340 = (DDB5340_AWA_5340) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_CARD_NO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5340_AWA_5340.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5340_AWA_5340.OLD_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_CARD_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.FUNC);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.CI_NO);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.OLD_AC);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.NEW_AC);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.FLG);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.FREE_YAR);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.STR_DATE);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.END_DATE);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.FREE_CDE);
        CEP.TRC(SCCGWA, DDB5340_AWA_5340.FREE_RSN);
        IBS.init(SCCGWA, DDCSMFEE);
        DDCSMFEE.FUNC = DDB5340_AWA_5340.FUNC;
        DDCSMFEE.CI_NO = DDB5340_AWA_5340.CI_NO;
        DDCSMFEE.AC = DDB5340_AWA_5340.OLD_AC;
        DDCSMFEE.NEW_AC = DDB5340_AWA_5340.NEW_AC;
        DDCSMFEE.FLG = DDB5340_AWA_5340.FLG;
        DDCSMFEE.FREE_YAR = DDB5340_AWA_5340.FREE_YAR;
        DDCSMFEE.STR_DATE = DDB5340_AWA_5340.STR_DATE;
        DDCSMFEE.END_DATE = DDB5340_AWA_5340.END_DATE;
        DDCSMFEE.FREE_CDE = DDB5340_AWA_5340.FREE_CDE;
        DDCSMFEE.FREE_RSN = DDB5340_AWA_5340.FREE_RSN;
        S000_CALL_DDZSMFEE();
    }
    public void S000_CALL_DDZSMFEE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-MOD-M-FEE", DDCSMFEE);
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
