package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8400 {
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSBRAC GDCSBRAC = new GDCSBRAC();
    SCCGWA SCCGWA;
    GDB8400_AWA_8400 GDB8400_AWA_8400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT8400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8400_AWA_8400>");
        GDB8400_AWA_8400 = (GDB8400_AWA_8400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (GDB8400_AWA_8400.PAGE_ROW == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PAGE_ROW_M_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDB8400_AWA_8400.PAGE_ROW > 20) {
            GDB8400_AWA_8400.PAGE_ROW = 20;
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NO_MORTHAN_VAL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSBRAC);
        GDCSBRAC.AC = GDB8400_AWA_8400.TXDD_AC;
        GDCSBRAC.PAGE_ROW = GDB8400_AWA_8400.PAGE_ROW;
        GDCSBRAC.PAGE_NUM = GDB8400_AWA_8400.PAGE_NUM;
        CEP.TRC(SCCGWA, GDB8400_AWA_8400.TXDD_AC);
        CEP.TRC(SCCGWA, GDB8400_AWA_8400.PAGE_ROW);
        CEP.TRC(SCCGWA, GDB8400_AWA_8400.PAGE_NUM);
        S000_CALL_GDZSBRAC();
    }
    public void S000_CALL_GDZSBRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSBRAC", GDCSBRAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
