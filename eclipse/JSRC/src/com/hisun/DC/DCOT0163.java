package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0163 {
    String CPN_DCZSSWCA = "DC-S-DCZSSWCA";
    String K_OUTPUT_FMT = "DC163";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSSWCA DCCSSWCA = new DCCSSWCA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB0163_AWA_0163 DCB0163_AWA_0163;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0163 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0163_AWA_0163>");
        DCB0163_AWA_0163 = (DCB0163_AWA_0163) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0163_AWA_0163.FUNC);
        CEP.TRC(SCCGWA, DCB0163_AWA_0163.CARD_NO);
        if (DCB0163_AWA_0163.FUNC != 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSSWCA);
        DCCSSWCA.FUNC_CODE = DCB0163_AWA_0163.FUNC;
        DCCSSWCA.CARD_NO = DCB0163_AWA_0163.CARD_NO;
        DCCSSWCA.ID_TYPE = DCB0163_AWA_0163.ID_TYPE;
        DCCSSWCA.ID_NO = DCB0163_AWA_0163.ID_NO;
        S000_CALL_DCZSSWCA();
    }
    public void S000_CALL_DCZSSWCA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZSSWCA, DCCSSWCA);
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
