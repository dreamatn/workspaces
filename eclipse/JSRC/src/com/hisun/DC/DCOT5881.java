package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5881 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCUPIQY DCCUPIQY = new DCCUPIQY();
    SCCGWA SCCGWA;
    DCB5881_AWA_5881 DCB5881_AWA_5881;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5881 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5881_AWA_5881>");
        DCB5881_AWA_5881 = (DCB5881_AWA_5881) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5881_AWA_5881.AC);
        CEP.TRC(SCCGWA, DCB5881_AWA_5881.PAGE_ROW);
        CEP.TRC(SCCGWA, DCB5881_AWA_5881.PAGE_NUM);
        if (DCB5881_AWA_5881.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            WS_FLD_NO = DCB5881_AWA_5881.AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPIQY);
        DCCUPIQY.I_INFO.AC = DCB5881_AWA_5881.AC;
        DCCUPIQY.I_INFO.PAGE_ROW = DCB5881_AWA_5881.PAGE_ROW;
        DCCUPIQY.I_INFO.PAGE_NUM = DCB5881_AWA_5881.PAGE_NUM;
        S000_CALL_DCZUPIQY();
    }
    public void S000_CALL_DCZUPIQY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-SPAC-IQY", DCCUPIQY);
        CEP.TRC(SCCGWA, DCCUPIQY.RC.RC_CODE);
        if (DCCUPIQY.RC.RC_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_INF_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
