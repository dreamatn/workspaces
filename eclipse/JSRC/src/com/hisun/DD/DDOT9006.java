package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT9006 {
    String K_FMT_P_INF = "DD906";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSLCAC DDCSLCAC = new DDCSLCAC();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDB9006_AWA_9006 DDB9006_AWA_9006;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT9006 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB9006_AWA_9006>");
        DDB9006_AWA_9006 = (DDB9006_AWA_9006) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_OUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB9006_AWA_9006.CI_NO);
        CEP.TRC(SCCGWA, DDB9006_AWA_9006.AC_NO);
        CEP.TRC(SCCGWA, DDB9006_AWA_9006.START_DT);
        CEP.TRC(SCCGWA, DDB9006_AWA_9006.END_DT);
        if (DDB9006_AWA_9006.CI_NO.trim().length() == 0 
            && DDB9006_AWA_9006.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_AC_MUST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLCAC);
        DDCSLCAC.CI_NO = DDB9006_AWA_9006.CI_NO;
        DDCSLCAC.AC_NO = DDB9006_AWA_9006.AC_NO;
        DDCSLCAC.START_DT = DDB9006_AWA_9006.START_DT;
        DDCSLCAC.END_DT = DDB9006_AWA_9006.END_DT;
        DDCSLCAC.PAGE_ROW = DDB9006_AWA_9006.PAGE_ROW;
        DDCSLCAC.PAGE_NUM = DDB9006_AWA_9006.PAGE_NUM;
        S000_CALL_DDZSLCAC();
    }
    public void S000_CALL_DDZSLCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSLCAC", DDCSLCAC);
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
