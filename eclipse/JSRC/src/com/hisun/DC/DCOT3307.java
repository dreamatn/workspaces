package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3307 {
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
    DCCSQCGT DCCSQCGT = new DCCSQCGT();
    SCCGWA SCCGWA;
    DCB3307_AWA_3307 DCB3307_AWA_3307;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3307 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3307_AWA_3307>");
        DCB3307_AWA_3307 = (DCB3307_AWA_3307) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IN");
        CEP.TRC(SCCGWA, DCB3307_AWA_3307.CARD_NO);
        CEP.TRC(SCCGWA, DCB3307_AWA_3307.CHIP_BAL);
        CEP.TRC(SCCGWA, DCB3307_AWA_3307.TXN_DATE);
        CEP.TRC(SCCGWA, DCB3307_AWA_3307.TXN_JRNO);
        if (DCB3307_AWA_3307.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQCGT);
        DCCSQCGT.IO_AREA.CARD_NO = DCB3307_AWA_3307.CARD_NO;
        DCCSQCGT.IO_AREA.CHIP_BAL_AMT = DCB3307_AWA_3307.CHIP_BAL;
        DCCSQCGT.IO_AREA.TXN_DATE = DCB3307_AWA_3307.TXN_DATE;
        DCCSQCGT.IO_AREA.TXN_JRNO = DCB3307_AWA_3307.TXN_JRNO;
        S000_CALL_DCZSQCGT();
    }
    public void S000_CALL_DCZSQCGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSQCGT", DCCSQCGT);
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
