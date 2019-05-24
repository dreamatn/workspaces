package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5857 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUEAXH DCCUEAXH = new DCCUEAXH();
    SCCGWA SCCGWA;
    DCB5857_AWA_5857 DCB5857_AWA_5857;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5857 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5857_AWA_5857>");
        DCB5857_AWA_5857 = (DCB5857_AWA_5857) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CLOSE_ELECTRON_AC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCB5857_AWA_5857.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            WS_FLD_NO = DCB5857_AWA_5857.CARD_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5857_AWA_5857.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            WS_FLD_NO = DCB5857_AWA_5857.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5857_AWA_5857.CCY_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            WS_FLD_NO = DCB5857_AWA_5857.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5857_AWA_5857.TRF_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_FLG_M_INPUT;
            WS_FLD_NO = DCB5857_AWA_5857.TRF_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CLOSE_ELECTRON_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUEAXH);
        DCCUEAXH.CARD_NO = DCB5857_AWA_5857.CARD_NO;
        DCCUEAXH.CCY = DCB5857_AWA_5857.CCY;
        DCCUEAXH.CCY_TYPE = DCB5857_AWA_5857.CCY_TYPE;
        DCCUEAXH.TRF_FLG = DCB5857_AWA_5857.TRF_FLG;
        DCCUEAXH.MMO = DCB5857_AWA_5857.MMO;
        DCCUEAXH.RMK = DCB5857_AWA_5857.RMK;
        S000_CALL_DCZUEAXH();
    }
    public void S000_CALL_DCZUEAXH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-M-EAC-CLOSE", DCCUEAXH);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
