package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5878 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCUMIQY DCCUMIQY = new DCCUMIQY();
    SCCGWA SCCGWA;
    DCB5878_AWA_5878 DCB5878_AWA_5878;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5878 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5878_AWA_5878>");
        DCB5878_AWA_5878 = (DCB5878_AWA_5878) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5878_AWA_5878.AC);
        CEP.TRC(SCCGWA, DCB5878_AWA_5878.AC_SEQ);
        if (DCB5878_AWA_5878.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            WS_FLD_NO = DCB5878_AWA_5878.AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMIQY);
        DCCUMIQY.I_INFO.AC = DCB5878_AWA_5878.AC;
        DCCUMIQY.I_INFO.AC_SEQ = DCB5878_AWA_5878.AC_SEQ;
        S000_CALL_DCZUMIQY();
    }
    public void S000_CALL_DCZUMIQY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-SMAC-IQY", DCCUMIQY);
        CEP.TRC(SCCGWA, DCCUMIQY.RC.RC_CODE);
        if (DCCUMIQY.RC.RC_CODE != 0) {
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
