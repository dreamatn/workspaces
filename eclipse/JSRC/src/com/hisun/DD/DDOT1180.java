package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1180 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSBKVL DDCSBKVL = new DDCSBKVL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB1180_AWA_1180 DDB1180_AWA_1180;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1180 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1180_AWA_1180>");
        DDB1180_AWA_1180 = (DDB1180_AWA_1180) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_BACK_VALUE_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1180_AWA_1180.AC_NO);
        CEP.TRC(SCCGWA, DDB1180_AWA_1180.CCY);
        CEP.TRC(SCCGWA, DDB1180_AWA_1180.AC_DT);
        CEP.TRC(SCCGWA, DDB1180_AWA_1180.VAL_DT);
        CEP.TRC(SCCGWA, DDB1180_AWA_1180.TR_JRN);
    }
    public void B030_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBKVL);
        DDCSBKVL.AC_NO = DDB1180_AWA_1180.AC_NO;
        DDCSBKVL.CCY = DDB1180_AWA_1180.CCY;
        DDCSBKVL.CCY_TYPE = DDB1180_AWA_1180.CCY_TYPE;
        DDCSBKVL.AC_DATE = DDB1180_AWA_1180.AC_DT;
        DDCSBKVL.VALUE_DATE = DDB1180_AWA_1180.VAL_DT;
        DDCSBKVL.TR_JRN = DDB1180_AWA_1180.TR_JRN;
        S000_CALL_DDZSBKVL();
    }
    public void S000_CALL_DDZSBKVL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-BACK-VALUE", DDCSBKVL);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
