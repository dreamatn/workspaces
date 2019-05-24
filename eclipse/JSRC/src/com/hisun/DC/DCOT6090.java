package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT6090 {
    char K_CARD_MTH = '1';
    String CDD_M_ANDY_AC_INFO = "DC-M-ANDY-AC-INFO";
    String K_OUTPUT_FMT = "DC090";
    DCOT6090_WS_FMT WS_FMT = new DCOT6090_WS_FMT();
    String WS_MSG_ID = " ";
    char K_ERROR = 'E';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUANDY DCCUANDY = new DCCUANDY();
    SCCGWA SCCGWA;
    DCB6090_AWA_6090 DCB6090_AWA_6090;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT6090 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB6090_AWA_6090>");
        DCB6090_AWA_6090 = (DCB6090_AWA_6090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ADD_CON_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB6090_AWA_6090.AC.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_ADD_CON_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUANDY);
        DCCUANDY.AC = DCB6090_AWA_6090.AC;
        S000_CALL_DCZUANDY();
        WS_FMT.WS_IN_AC_TYPE = DCCUANDY.IN_AC_TYPE;
        WS_FMT.WS_OUT_AC = DCCUANDY.OUT_AC;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 33;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUANDY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_M_ANDY_AC_INFO, DCCUANDY);
        if (DCCUANDY.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUANDY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSG_ID);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
