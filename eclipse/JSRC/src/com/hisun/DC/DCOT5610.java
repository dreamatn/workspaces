package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5610 {
    char K_AC_MTH = '0';
    char K_CARD_MTH = '1';
    String CDC_M_COMPA_DR_PLAN = "DC-M-COMPA-DR-PLAN";
    String WS_MSG_ID = " ";
    char WS_PROC_STS = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUMCID DCCUMCID = new DCCUMCID();
    SCCGWA SCCGWA;
    DCB5610_AWA_5610 DCB5610_AWA_5610;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5610 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5610_AWA_5610>");
        DCB5610_AWA_5610 = (DCB5610_AWA_5610) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCB5610_AWA_5610.AC_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        WS_PROC_STS = DCB5610_AWA_5610.PROC_STS;
        CEP.TRC(SCCGWA, WS_PROC_STS);
        if ((WS_PROC_STS == '1' 
            || WS_PROC_STS == '2' 
            || WS_PROC_STS == '3')) {
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_PROC_STS_INV;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMCID);
        DCCUMCID.IO_AREA.FUNC_M = 'B';
        DCCUMCID.IO_AREA.AC_NO = DCB5610_AWA_5610.AC_NO;
        DCCUMCID.IO_AREA.PROC_STS = DCB5610_AWA_5610.PROC_STS;
        DCCUMCID.IO_AREA.OVR_NO = DCB5610_AWA_5610.OVR_NO;
        S000_CALL_DCZUMCID();
    }
    public void S000_CALL_DCZUMCID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDC_M_COMPA_DR_PLAN, DCCUMCID);
        if (DCCUMCID.O_AREA.MSG_ID.RC == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUMCID.O_AREA.MSG_ID);
            S000_ERR_MSG_PROC();
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
