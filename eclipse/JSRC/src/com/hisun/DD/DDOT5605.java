package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5605 {
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSKBID DDCSKBID = new DDCSKBID();
    SCCGWA SCCGWA;
    DDB5605_AWA_5605 DDB5605_AWA_5605;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5605 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5605_AWA_5605>");
        DDB5605_AWA_5605 = (DDB5605_AWA_5605) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5605_AWA_5605.REF_NO);
        if (DDB5605_AWA_5605.REF_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT);
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSKBID);
        DDCSKBID.DATA.REF_NO = DDB5605_AWA_5605.REF_NO;
        DDCSKBID.DATA.CHG_NO = DDB5605_AWA_5605.CHG_NO;
        DDCSKBID.DATA.SPR_NM = DDB5605_AWA_5605.SPR_NM;
        DDCSKBID.DATA.NEW_DT = DDB5605_AWA_5605.NEW_DT;
        DDCSKBID.DATA.RMK = DDB5605_AWA_5605.RMK;
        DDCSKBID.DATA.CHG_BR = DDB5605_AWA_5605.CHG_BR;
        DDCSKBID.DATA.LAW_NM1 = DDB5605_AWA_5605.LAW_NM1;
        DDCSKBID.DATA.LAW_NO1 = DDB5605_AWA_5605.LAW_NO1;
        DDCSKBID.DATA.LAW_NM2 = DDB5605_AWA_5605.LAW_NM2;
        DDCSKBID.DATA.LAW_NO2 = DDB5605_AWA_5605.LAW_NO2;
        S000_CALL_DDZSKBID();
    }
    public void S000_CALL_DDZSKBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSKBID", DDCSKBID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
