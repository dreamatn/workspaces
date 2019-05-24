package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5614 {
    String CDC_M_COMPA_DR_PLAN = "DC-M-COMPA-DR-PLAN";
    String WS_MSG_ID = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUMCID DCCUMCID = new DCCUMCID();
    SCCGWA SCCGWA;
    DCB5614_AWA_5614 DCB5614_AWA_5614;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5614 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5614_AWA_5614>");
        DCB5614_AWA_5614 = (DCB5614_AWA_5614) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "MJL TEST :");
        CEP.TRC(SCCGWA, DCB5614_AWA_5614.AC_NO);
        CEP.TRC(SCCGWA, DCB5614_AWA_5614.OVR_NO);
        CEP.TRC(SCCGWA, DCB5614_AWA_5614.PROC_STS);
        if (DCB5614_AWA_5614.AC_NO.trim().length() == 0 
            && DCB5614_AWA_5614.OVR_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_AC;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMCID);
        DCCUMCID.IO_AREA.FUNC_M = 'D';
        DCCUMCID.IO_AREA.OVR_NO = DCB5614_AWA_5614.OVR_NO;
        DCCUMCID.IO_AREA.AC_NO = DCB5614_AWA_5614.AC_NO;
        DCCUMCID.IO_AREA.PROC_STS = DCB5614_AWA_5614.PROC_STS;
        S000_CALL_DCZUMCID();
    }
    public void S000_CALL_DCZUMCID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDC_M_COMPA_DR_PLAN, DCCUMCID, true);
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
