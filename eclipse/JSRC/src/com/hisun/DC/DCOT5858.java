package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5858 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSELAC DCCSELAC = new DCCSELAC();
    SCCGWA SCCGWA;
    DCB5858_AWA_5858 DCB5858_AWA_5858;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5858 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5858_AWA_5858>");
        DCB5858_AWA_5858 = (DCB5858_AWA_5858) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_OPEN_ELECTRON_AC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCB5858_AWA_5858.OPEN_TYP != '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            WS_FLD_NO = DCB5858_AWA_5858.OPEN_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5858_AWA_5858.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_COD_MUST_INPUT;
            WS_FLD_NO = DCB5858_AWA_5858.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5858_AWA_5858.CARD_BIN.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.BIN_MUST_INPUT;
            WS_FLD_NO = DCB5858_AWA_5858.CARD_BIN_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5858_AWA_5858.M_CD_TY == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_TYPE;
            WS_FLD_NO = DCB5858_AWA_5858.M_CD_TY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5858_AWA_5858.CARD_ID.trim().length() == 0 
            && DCB5858_AWA_5858.CUST_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_NO_M_INPUT;
            WS_FLD_NO = DCB5858_AWA_5858.CUST_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5858_AWA_5858.OPEN_BR == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
            WS_FLD_NO = DCB5858_AWA_5858.OPEN_BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_OPEN_ELECTRON_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSELAC);
        DCCSELAC.OPEN_TYPE = DCB5858_AWA_5858.OPEN_TYP;
        DCCSELAC.CARD_PRD_CODE = DCB5858_AWA_5858.PROD_CD;
        DCCSELAC.CARD_BIN = DCB5858_AWA_5858.CARD_BIN;
        DCCSELAC.MAIN_CARD_TYP = DCB5858_AWA_5858.M_CD_TY;
        DCCSELAC.CI_NO = DCB5858_AWA_5858.CUST_NO;
        DCCSELAC.CARD_ID = DCB5858_AWA_5858.CARD_ID;
        DCCSELAC.OPEN_BR = DCB5858_AWA_5858.OPEN_BR;
        S000_CALL_DCZSELAC();
    }
    public void S000_CALL_DCZSELAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-OPEN-ELE-AC", DCCSELAC);
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
