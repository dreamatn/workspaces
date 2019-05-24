package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3312 {
    double K_MAX_AMT = 1000;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    double WS_TEMP_AMT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSQTFB DCCSQTFB = new DCCSQTFB();
    SCCGWA SCCGWA;
    DCB3312_AWA_3312 DCB3312_AWA_3312;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3312 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3312_AWA_3312>");
        DCB3312_AWA_3312 = (DCB3312_AWA_3312) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IN");
        CEP.TRC(SCCGWA, DCB3312_AWA_3312.CARD_NO);
        if (DCB3312_AWA_3312.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQTFB);
        DCCSQTFB.CARD_NO = DCB3312_AWA_3312.CARD_NO;
        DCCSQTFB.TXN_AMT = DCB3312_AWA_3312.TXN_AMT;
        DCCSQTFB.TXN_DATE = DCB3312_AWA_3312.TXN_DT;
        DCCSQTFB.TXN_JRN_NO = DCB3312_AWA_3312.TXN_JRN;
        CEP.TRC(SCCGWA, DCCSQTFB.CARD_NO);
        CEP.TRC(SCCGWA, DCCSQTFB.TXN_AMT);
        CEP.TRC(SCCGWA, DCCSQTFB.TXN_DATE);
        CEP.TRC(SCCGWA, DCCSQTFB.TXN_JRN_NO);
        S000_CALL_DCZSQTFB();
    }
    public void S000_CALL_DCZSQTFB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSQTFB", DCCSQTFB);
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
