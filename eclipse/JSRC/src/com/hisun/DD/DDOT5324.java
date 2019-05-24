package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5324 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCALL DDCSCALL = new DDCSCALL();
    SCCGWA SCCGWA;
    DDB5320_AWA_5320 DDB5320_AWA_5320;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5324 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5320_AWA_5320>");
        DDB5320_AWA_5320 = (DDB5320_AWA_5320) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5320_AWA_5320.CARD_NO);
        CEP.TRC(SCCGWA, DDB5320_AWA_5320.AC);
        CEP.TRC(SCCGWA, DDB5320_AWA_5320.CCY);
        CEP.TRC(SCCGWA, DDB5320_AWA_5320.CCY_TYPE);
        if (DDB5320_AWA_5320.AC.trim().length() == 0 
            && DDB5320_AWA_5320.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDB5320_AWA_5320.AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5320_AWA_5320.AC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCALL);
        DDCSCALL.CARD_NO = DDB5320_AWA_5320.CARD_NO;
        DDCSCALL.AC = DDB5320_AWA_5320.AC;
        DDCSCALL.CCY = DDB5320_AWA_5320.CCY;
        DDCSCALL.CCY_TYPE = DDB5320_AWA_5320.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCSCALL.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCALL.AC);
        DDCSCALL.FUNC = 'I';
        S000_CALL_DDZSCALL();
    }
    public void S000_CALL_DDZSCALL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCALL", DDCSCALL);
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
