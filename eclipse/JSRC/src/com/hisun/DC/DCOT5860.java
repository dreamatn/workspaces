package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5860 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSQFAC DCCSQFAC = new DCCSQFAC();
    SCCGWA SCCGWA;
    DCB5860_AWA_5860 DCB5860_AWA_5860;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5860 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5860_AWA_5860>");
        DCB5860_AWA_5860 = (DCB5860_AWA_5860) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PARM_DATA_TR();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5860_AWA_5860.FUNC);
        if (DCB5860_AWA_5860.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5860_AWA_5860.FUNC != '1' 
            && DCB5860_AWA_5860.FUNC != '2' 
            && DCB5860_AWA_5860.FUNC != '3' 
            && DCB5860_AWA_5860.FUNC != '4') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.FUNC_FALSE;
            S000_ERR_MSG_PROC();
        }
        if (DCB5860_AWA_5860.FREE_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FREE_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCB5860_AWA_5860.FUNC == '2' 
            || DCB5860_AWA_5860.FUNC == '3' 
            || DCB5860_AWA_5860.FUNC == '4') {
            if (DCB5860_AWA_5860.STD_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_STD_AC_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCB5860_AWA_5860.FUNC == '4') {
            if (DCB5860_AWA_5860.FREE_TYP.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FREE_TYPE_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_PARM_DATA_TR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQFAC);
        DCCSQFAC.FUNC = DCB5860_AWA_5860.FUNC;
        DCCSQFAC.STD_AC = DCB5860_AWA_5860.STD_AC;
        DCCSQFAC.FREE_AC = DCB5860_AWA_5860.FREE_AC;
        DCCSQFAC.FREE_TYPE = DCB5860_AWA_5860.FREE_TYP;
        CEP.TRC(SCCGWA, DCCSQFAC.FUNC);
        CEP.TRC(SCCGWA, DCCSQFAC.STD_AC);
        CEP.TRC(SCCGWA, DCCSQFAC.FREE_AC);
        CEP.TRC(SCCGWA, DCCSQFAC.FREE_TYPE);
        S000_CALL_DCZSQFAC();
    }
    public void S000_CALL_DCZSQFAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-QFAC-PROC", DCCSQFAC);
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
