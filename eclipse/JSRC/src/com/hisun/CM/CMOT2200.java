package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT2200 {
    String WS_MSGID = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    CMCSQVCH CMCSQVCH = new CMCSQVCH();
    SCCGWA SCCGWA;
    CMB2200_AWA_2200 CMB2200_AWA_2200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT2200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB2200_AWA_2200>");
        CMB2200_AWA_2200 = (CMB2200_AWA_2200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMB2200_AWA_2200.AC.trim().length() == 0) {
            WS_MSGID = CMCMSG_ERROR_MSG.CM_ERR_INPUT_AC;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMB2200_AWA_2200.AC);
        IBS.init(SCCGWA, CMCSQVCH);
        CMCSQVCH.AC = CMB2200_AWA_2200.AC;
        CEP.TRC(SCCGWA, CMCSQVCH.AC);
        S000_CALL_CMZSQVCH();
    }
    public void S000_CALL_CMZSQVCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-CMZSQVCH", CMCSQVCH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
