package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1060 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSACNO DCCSACNO = new DCCSACNO();
    SCCGWA SCCGWA;
    DCB1060_AWA_1060 DCB1060_AWA_1060;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT1060 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1060_AWA_1060>");
        DCB1060_AWA_1060 = (DCB1060_AWA_1060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB1060_AWA_1060.CARDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB1060_AWA_1060.FUNC != 'B') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSACNO);
        DCCSACNO.CARD_NO = DCB1060_AWA_1060.CARDNO;
        DCCSACNO.FUNC = DCB1060_AWA_1060.FUNC;
        DCCSACNO.PAGE_ROW = DCB1060_AWA_1060.PAGE_ROW;
        DCCSACNO.PAGE_NUM = DCB1060_AWA_1060.PAGE_NUM;
        S000_CALL_DCZSACNO();
    }
    public void S000_CALL_DCZSACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSACNO", DCCSACNO);
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
