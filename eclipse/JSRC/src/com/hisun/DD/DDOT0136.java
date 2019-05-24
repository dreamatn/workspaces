package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT0136 {
    String CPN_DD_S_QCHQ_PROC = "DD-S-QCHQ-PROC";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSQCHQ DDCSQCHQ = new DDCSQCHQ();
    SCCGWA SCCGWA;
    DDB0136_AWA_0136 DDB0136_AWA_0136;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT0136 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB0136_AWA_0136>");
        DDB0136_AWA_0136 = (DDB0136_AWA_0136) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_QUERY_CHQB_INF_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.AC);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.CHQ_TYP);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.STR_NO);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.END_NO);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.CHQ_STS);
        if ((DDB0136_AWA_0136.STR_NO.trim().length() > 0 
            && DDB0136_AWA_0136.END_NO.trim().length() > 0) 
            && DDB0136_AWA_0136.STR_NO.compareTo(DDB0136_AWA_0136.END_NO) > 0) {
            CEP.TRC(SCCGWA, "STR NO GT END NO");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_LESSTHAN_STR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_QUERY_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQCHQ);
        DDCSQCHQ.AC_NO = DDB0136_AWA_0136.AC;
        DDCSQCHQ.CHQ_TYP = DDB0136_AWA_0136.CHQ_TYP;
        DDCSQCHQ.STR_CHQ_NO = DDB0136_AWA_0136.STR_NO;
        DDCSQCHQ.END_CHQ_NO = DDB0136_AWA_0136.END_NO;
        DDCSQCHQ.CHQ_STS = DDB0136_AWA_0136.CHQ_STS;
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.CHQ_STS);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.CHQ_TYP);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.STR_NO);
        CEP.TRC(SCCGWA, DDB0136_AWA_0136.END_NO);
        CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
        CEP.TRC(SCCGWA, DDCSQCHQ.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
        S000_CALL_DDZSQCHQ();
    }
    public void S000_CALL_DDZSQCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_QCHQ_PROC, DDCSQCHQ);
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
