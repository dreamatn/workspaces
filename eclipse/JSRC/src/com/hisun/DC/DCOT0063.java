package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0063 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSGTCD DCCSGTCD = new DCCSGTCD();
    SCCGWA SCCGWA;
    DCB0063_AWA_0063 DCB0063_AWA_0063;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT0063 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0063_AWA_0063>");
        DCB0063_AWA_0063 = (DCB0063_AWA_0063) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0063_AWA_0063.CARD_NO);
        CEP.TRC(SCCGWA, DCB0063_AWA_0063.CARD_SEQ);
        CEP.TRC(SCCGWA, DCB0063_AWA_0063.CARD_FLG);
        if (DCB0063_AWA_0063.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB0063_AWA_0063.CARD_SEQ == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB0063_AWA_0063.CARD_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_FLG;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSGTCD);
        DCCSGTCD.CARD_NO = DCB0063_AWA_0063.CARD_NO;
        DCCSGTCD.CARD_SEQ = DCB0063_AWA_0063.CARD_SEQ;
        DCCSGTCD.CITIZEN_CRD_FLG = DCB0063_AWA_0063.CARD_FLG;
        DCCSGTCD.ID_TYP = DCB0063_AWA_0063.ID_TYP;
        DCCSGTCD.ID_NO = DCB0063_AWA_0063.ID_NO;
        DCCSGTCD.CI_NAME = DCB0063_AWA_0063.CI_NAME;
        DCCSGTCD.CARD_STS = DCB0063_AWA_0063.CARD_STS;
        DCCSGTCD.TXN_PSW = DCB0063_AWA_0063.CARD_PSW;
        DCCSGTCD.TXN_TLR = DCB0063_AWA_0063.TXN_TLR;
        S000_CALL_DCZSGTCD();
    }
    public void S000_CALL_DCZSGTCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSGTCD", DCCSGTCD);
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
