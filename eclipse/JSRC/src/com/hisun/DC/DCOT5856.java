package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5856 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSIEAC DCCSIEAC = new DCCSIEAC();
    SCCGWA SCCGWA;
    DCB5856_AWA_5856 DCB5856_AWA_5856;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5856 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5856_AWA_5856>");
        DCB5856_AWA_5856 = (DCB5856_AWA_5856) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5856_AWA_5856.FLG);
        CEP.TRC(SCCGWA, DCB5856_AWA_5856.CARD_ID1);
        CEP.TRC(SCCGWA, DCB5856_AWA_5856.CARD_ID2);
        if (DCB5856_AWA_5856.FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FLG_MUST_INPUT;
            WS_FLD_NO = DCB5856_AWA_5856.FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5856_AWA_5856.FLG == '1') {
            if (DCB5856_AWA_5856.CARD_ID1.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ID1_MUST_INPUT;
                WS_FLD_NO = DCB5856_AWA_5856.CARD_ID1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (DCB5856_AWA_5856.FLG == '2') {
            if (DCB5856_AWA_5856.CARD_ID2.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ID2_MUST_INPUT;
                WS_FLD_NO = DCB5856_AWA_5856.CARD_ID2_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSIEAC);
        DCCSIEAC.INPUT.FLG = DCB5856_AWA_5856.FLG;
        DCCSIEAC.INPUT.CARD_ID1 = DCB5856_AWA_5856.CARD_ID1;
        DCCSIEAC.INPUT.CARD_ID2 = DCB5856_AWA_5856.CARD_ID2;
        S000_CALL_DCZSIEAC();
    }
    public void S000_CALL_DCZSIEAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-SIE-AC", DCCSIEAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
