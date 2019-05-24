package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5830 {
    String WS_MSGID = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    GDCSINAC GDCSINAC = new GDCSINAC();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    GDB5830_AWA_5830 GDB5830_AWA_5830;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT5830 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5830_AWA_5830>");
        GDB5830_AWA_5830 = (GDB5830_AWA_5830) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_INDATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (GDB5830_AWA_5830.AC_NO.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB5830_AWA_5830.ST_AC.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_INTDD_AC_MST_INPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_INDATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSINAC);
        GDCSINAC.AC_NO = GDB5830_AWA_5830.AC_NO;
        GDCSINAC.AC_SEQ = GDB5830_AWA_5830.AC_SEQ;
        GDCSINAC.ST_AC = GDB5830_AWA_5830.ST_AC;
        CEP.TRC(SCCGWA, GDB5830_AWA_5830.AC_NO);
        CEP.TRC(SCCGWA, GDB5830_AWA_5830.AC_SEQ);
        CEP.TRC(SCCGWA, GDB5830_AWA_5830.ST_AC);
        S000_CALL_GDZSINAC();
    }
    public void S000_CALL_GDZSINAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSINAC", GDCSINAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
