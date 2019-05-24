package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT9010 {
    String K_FMT_P_INF = "DD910";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSQAC DDCSQAC = new DDCSQAC();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDB9010_AWA_9010 DDB9010_AWA_9010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT9010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB9010_AWA_9010>");
        DDB9010_AWA_9010 = (DDB9010_AWA_9010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_OUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB9010_AWA_9010.FUNC);
        CEP.TRC(SCCGWA, DDB9010_AWA_9010.AC_NO);
        if (DDB9010_AWA_9010.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            WS_FLD_NO = DDB9010_AWA_9010.FUNC_NO;
            S000_ERR_MSG_PROC_FLDNO();
        }
        if (DDB9010_AWA_9010.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQAC);
        DDCSQAC.INPUT_INFO.FUNC = DDB9010_AWA_9010.FUNC;
        DDCSQAC.INPUT_INFO.AC_NO = DDB9010_AWA_9010.AC_NO;
        DDCSQAC.PAGE_ROW = DDB9010_AWA_9010.PAGE_ROW;
        DDCSQAC.PAGE_NUM = DDB9010_AWA_9010.PAGE_NUM;
        S000_CALL_DDZSQAC();
    }
    public void S000_CALL_DDZSQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQAC", DDCSQAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_FLDNO() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
