package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8310 {
    String WS_ERR_MSG = " ";
    int WS_A = 0;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSMIAC GDCSMIAC = new GDCSMIAC();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDB8310_AWA_8310 GDB8310_AWA_8310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT8310 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8310_AWA_8310>");
        GDB8310_AWA_8310 = (GDB8310_AWA_8310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8310_AWA_8310.AC_CNT[1-1].AC);
        CEP.TRC(SCCGWA, GDB8310_AWA_8310.AC_CNT[1-1].AC_SEQ);
        CEP.TRC(SCCGWA, GDB8310_AWA_8310.AC_CNT[2-1].AC);
        CEP.TRC(SCCGWA, GDB8310_AWA_8310.AC_CNT[2-1].AC_SEQ);
        if (GDB8310_AWA_8310.AC_COUNT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_CNT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB8310_AWA_8310.AC_CNT[1-1].AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSMIAC);
        GDCSMIAC.AC_COUNT = GDB8310_AWA_8310.AC_COUNT;
        for (WS_A = 1; WS_A <= GDB8310_AWA_8310.AC_COUNT 
            && GDB8310_AWA_8310.AC_CNT[WS_A-1].AC.trim().length() != 0; WS_A += 1) {
            GDCSMIAC.AC_INFO[WS_A-1].AC = GDB8310_AWA_8310.AC_CNT[WS_A-1].AC;
            GDCSMIAC.AC_INFO[WS_A-1].AC_SEQ = GDB8310_AWA_8310.AC_CNT[WS_A-1].AC_SEQ;
            CEP.TRC(SCCGWA, WS_A);
            CEP.TRC(SCCGWA, GDB8310_AWA_8310.AC_CNT[WS_A-1].AC);
            CEP.TRC(SCCGWA, GDB8310_AWA_8310.AC_CNT[WS_A-1].AC_SEQ);
        }
        S000_CALL_GDZSMIAC();
    }
    public void S000_CALL_GDZSMIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSMIAC", GDCSMIAC);
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
