package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5401 {
    String K_LOC_CCY = "CNY";
    String CPN_DD_S_BCHQ_PROC = "DD-S-BCHQ-PROC";
    String WS_ERR_MSG = " ";
    short WS_RET = 0;
    short WS_RMDR = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSBCHQ DDCSBCHQ = new DDCSBCHQ();
    SCCGWA SCCGWA;
    DDB5401_AWA_5401 DDB5401_AWA_5401;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5401 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5401_AWA_5401>");
        DDB5401_AWA_5401 = (DDB5401_AWA_5401) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_QUERY_CHQB_INF_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.AC);
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.CHQ_BVCD);
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.CHQ_NO);
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.ISU_DATE);
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.USE_DATE);
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.PASSWORD);
        CEP.TRC(SCCGWA, DDB5401_AWA_5401.AMT);
        if (DDB5401_AWA_5401.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_QUERY_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBCHQ);
        DDCSBCHQ.AC_NO = DDB5401_AWA_5401.AC;
        DDCSBCHQ.CHQ_BV_CD = DDB5401_AWA_5401.CHQ_BVCD;
        DDCSBCHQ.CHQ_NO = DDB5401_AWA_5401.CHQ_NO;
        DDCSBCHQ.ISU_DATE = DDB5401_AWA_5401.ISU_DATE;
        DDCSBCHQ.USE_DATE = DDB5401_AWA_5401.USE_DATE;
        DDCSBCHQ.PSW = DDB5401_AWA_5401.PASSWORD;
        DDCSBCHQ.AMT = DDB5401_AWA_5401.AMT;
        S000_CALL_DDZSBCHQ();
    }
    public void S000_CALL_DDZSBCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_BCHQ_PROC, DDCSBCHQ);
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
