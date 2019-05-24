package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3309 {
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
    DCCSQCRV DCCSQCRV = new DCCSQCRV();
    SCCGWA SCCGWA;
    DCB3303_AWA_3303 DCB3303_AWA_3303;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3309 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3303_AWA_3303>");
        DCB3303_AWA_3303 = (DCB3303_AWA_3303) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.CARD_NO);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.QC_TYP);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.QC_AMT);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.UNAN_CRD);
        CEP.TRC(SCCGWA, DCB3303_AWA_3303.CLEAR_AC);
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQCRV);
        DCCSQCRV.IO_AREA.CARD_NO = DCB3303_AWA_3303.CARD_NO;
        DCCSQCRV.IO_AREA.QC_TYP = DCB3303_AWA_3303.QC_TYP;
        DCCSQCRV.IO_AREA.REVERSAL_AMT = DCB3303_AWA_3303.QC_AMT;
        DCCSQCRV.IO_AREA.UNASSIGN_CRD = DCB3303_AWA_3303.UNAN_CRD;
        DCCSQCRV.IO_AREA.SLT_AC = DCB3303_AWA_3303.CLEAR_AC;
        S000_CALL_DCZSQCRV();
    }
    public void S000_CALL_DCZSQCRV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSQCRV", DCCSQCRV);
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
