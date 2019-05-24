package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5223 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMFLG DDCSMFLG = new DDCSMFLG();
    SCCGWA SCCGWA;
    DDB5223_AWA_5223 DDB5223_AWA_5223;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5223 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5223_AWA_5223>");
        DDB5223_AWA_5223 = (DDB5223_AWA_5223) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5223_AWA_5223.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5223_AWA_5223.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5223_AWA_5223.FUNC);
        CEP.TRC(SCCGWA, DDB5223_AWA_5223.AC);
        CEP.TRC(SCCGWA, DDB5223_AWA_5223.OD_FLG);
        CEP.TRC(SCCGWA, DDB5223_AWA_5223.LK_FLG);
        IBS.init(SCCGWA, DDCSMFLG);
        DDCSMFLG.FUNC = DDB5223_AWA_5223.FUNC;
        DDCSMFLG.AC = DDB5223_AWA_5223.AC;
        DDCSMFLG.OD_FLG = DDB5223_AWA_5223.OD_FLG;
        DDCSMFLG.LK_FLG = DDB5223_AWA_5223.LK_FLG;
        S000_CALL_DDZSMFLG();
    }
    public void S000_CALL_DDZSMFLG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-MOD-STS-FLG", DDCSMFLG);
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
