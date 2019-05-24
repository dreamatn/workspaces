package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5903 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSMPRD DCCSMPRD = new DCCSMPRD();
    SCCGWA SCCGWA;
    DCB5900_AWA_5900 DCB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5903 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5900_AWA_5900>");
        DCB5900_AWA_5900 = (DCB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5900_AWA_5900.PROD_CD);
        CEP.TRC(SCCGWA, DCB5900_AWA_5900.NAME);
        CEP.TRC(SCCGWA, DCB5900_AWA_5900.STR_DATE);
        if (DCB5900_AWA_5900.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5900_AWA_5900.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5900_AWA_5900.STR_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_STR_DATE_LT_CNT_DATE;
            WS_FLD_NO = DCB5900_AWA_5900.STR_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_DELETE_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSMPRD);
        DCCSMPRD.VAL.PROD_CD = DCB5900_AWA_5900.PROD_CD;
        DCCSMPRD.VAL.NAME = DCB5900_AWA_5900.NAME;
        DCCSMPRD.VAL.STR_DATE = DCB5900_AWA_5900.STR_DATE;
        DCCSMPRD.FUNC = 'D';
        S000_CALL_DCZSMPRD();
    }
    public void S000_CALL_DCZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-MOD-PRD", DCCSMPRD);
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
