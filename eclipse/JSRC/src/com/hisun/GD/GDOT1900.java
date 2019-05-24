package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1900 {
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSSTAC GDCSSTAC = new GDCSSTAC();
    SCCGWA SCCGWA;
    GDB1900_AWA_1900 GDB1900_AWA_1900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1900_AWA_1900>");
        GDB1900_AWA_1900 = (GDB1900_AWA_1900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (GDB1900_AWA_1900.TYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUNC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1900_AWA_1900.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSSTAC);
        GDCSSTAC.TYP = GDB1900_AWA_1900.TYP;
        GDCSSTAC.AC = GDB1900_AWA_1900.AC;
        GDCSSTAC.AC_SEQ = GDB1900_AWA_1900.AC_SEQ;
        GDCSSTAC.ST_AC = GDB1900_AWA_1900.ST_AC;
        GDCSSTAC.CUT_AC = GDB1900_AWA_1900.CUT_AC;
        CEP.TRC(SCCGWA, GDCSSTAC.AC);
        CEP.TRC(SCCGWA, GDCSSTAC.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSSTAC.ST_AC);
        CEP.TRC(SCCGWA, GDCSSTAC.TYP);
        CEP.TRC(SCCGWA, GDCSSTAC.CUT_AC);
        S000_CALL_GDZSSTAC();
    }
    public void S000_CALL_GDZSSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSSTAC", GDCSSTAC);
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
