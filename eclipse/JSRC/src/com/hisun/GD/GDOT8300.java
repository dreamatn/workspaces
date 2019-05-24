package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8300 {
    DBParm DDTMST_RD;
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSQINF GDCSQINF = new GDCSQINF();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    GDB8300_AWA_8300 GDB8300_AWA_8300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT8300 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8300_AWA_8300>");
        GDB8300_AWA_8300 = (GDB8300_AWA_8300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (GDB8300_AWA_8300.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSQINF);
        GDCSQINF.AC = GDB8300_AWA_8300.TXDD_AC;
        GDCSQINF.DR_SEQ = GDB8300_AWA_8300.TXDR_SEQ;
        CEP.TRC(SCCGWA, GDB8300_AWA_8300.TXDD_AC);
        CEP.TRC(SCCGWA, GDB8300_AWA_8300.TXDR_SEQ);
        S000_CALL_GDZSQINF();
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZSQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSQINF", GDCSQINF);
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
