package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6100 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSSMNA DDCSSMNA = new DDCSSMNA();
    SCCGWA SCCGWA;
    DDB6100_AWA_6100 DDB6100_AWA_6100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT6100 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6100_AWA_6100>");
        DDB6100_AWA_6100 = (DDB6100_AWA_6100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6100_AWA_6100.AC);
        CEP.TRC(SCCGWA, DDB6100_AWA_6100.ID_TYPE);
        CEP.TRC(SCCGWA, DDB6100_AWA_6100.ID_NO);
        CEP.TRC(SCCGWA, DDB6100_AWA_6100.FLG);
        if (DDB6100_AWA_6100.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDB6100_AWA_6100.AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB6100_AWA_6100.AC);
            S000_ERR_MSG_PROC();
        }
        if (DDB6100_AWA_6100.ID_TYPE.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYP_M_INPUT;
            if (DDB6100_AWA_6100.ID_TYPE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB6100_AWA_6100.ID_TYPE);
            S000_ERR_MSG_PROC();
        }
        if (DDB6100_AWA_6100.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NO_M_INPUT;
            if (DDB6100_AWA_6100.ID_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB6100_AWA_6100.ID_NO);
            S000_ERR_MSG_PROC();
        }
        if (DDB6100_AWA_6100.FLG == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MONI_FLG_M_INPUT;
            if (DDB6100_AWA_6100.FLG == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDB6100_AWA_6100.FLG);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSSMNA);
        DDCSSMNA.AC = DDB6100_AWA_6100.AC;
        DDCSSMNA.ID_TYPE = DDB6100_AWA_6100.ID_TYPE;
        DDCSSMNA.ID_NO = DDB6100_AWA_6100.ID_NO;
        DDCSSMNA.FLG = DDB6100_AWA_6100.FLG;
        S000_CALL_DDZSSMNA();
    }
    public void S000_CALL_DDZSSMNA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSSMNA", DDCSSMNA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
