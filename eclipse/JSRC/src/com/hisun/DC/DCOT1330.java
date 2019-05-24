package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1330 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSINQC DCCSINQC = new DCCSINQC();
    SCCGWA SCCGWA;
    DCB1330_AWA_1330 DCB1330_AWA_1330;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT1330 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1330_AWA_1330>");
        DCB1330_AWA_1330 = (DCB1330_AWA_1330) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB1330_AWA_1330.CODE);
        CEP.TRC(SCCGWA, DCB1330_AWA_1330.SASB_AC);
        CEP.TRC(SCCGWA, DCB1330_AWA_1330.CARD_NO);
        if (DCB1330_AWA_1330.CODE != 'S' 
            && DCB1330_AWA_1330.CODE != 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (DCB1330_AWA_1330.CODE == 'S' 
            && DCB1330_AWA_1330.SASB_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SASB_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB1330_AWA_1330.CODE == 'C' 
            && DCB1330_AWA_1330.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSINQC);
        DCCSINQC.CODE = DCB1330_AWA_1330.CODE;
        DCCSINQC.INPUT_DATA.SASB_AC = DCB1330_AWA_1330.SASB_AC;
        DCCSINQC.INPUT_DATA.CARD_NO = DCB1330_AWA_1330.CARD_NO;
        DCCSINQC.INPUT_DATA.SASB_VCH_NO = DCB1330_AWA_1330.VCH_NO;
        S000_CALL_DCZSINQC();
    }
    public void S000_CALL_DCZSINQC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSINQC", DCCSINQC);
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
