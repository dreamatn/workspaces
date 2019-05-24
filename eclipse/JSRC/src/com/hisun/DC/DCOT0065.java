package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0065 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSGTDC DCCSGTDC = new DCCSGTDC();
    SCCGWA SCCGWA;
    DCB0065_AWA_0065 DCB0065_AWA_0065;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT0065 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0065_AWA_0065>");
        DCB0065_AWA_0065 = (DCB0065_AWA_0065) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0065_AWA_0065.BV_NO);
        CEP.TRC(SCCGWA, DCB0065_AWA_0065.NUM);
        if (DCB0065_AWA_0065.BV_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_STA_BV_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB0065_AWA_0065.NUM == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BV_NO_NUM_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSGTDC);
        DCCSGTDC.INPUT.START_BV_NO = DCB0065_AWA_0065.BV_NO;
        DCCSGTDC.INPUT.NUM = DCB0065_AWA_0065.NUM;
        DCCSGTDC.INPUT.AGENT_IDTYP = DCB0065_AWA_0065.AG_IDTYP;
        DCCSGTDC.INPUT.AGENT_IDNO = DCB0065_AWA_0065.AG_IDNO;
        DCCSGTDC.INPUT.AGENT_NAME = DCB0065_AWA_0065.AG_NAME;
        S000_CALL_DCZSGTDC();
    }
    public void S000_CALL_DCZSGTDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSGTDC", DCCSGTDC);
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
