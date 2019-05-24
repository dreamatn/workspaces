package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6405 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    short WS_CNT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMINT DDCSMINT = new DDCSMINT();
    SCCGWA SCCGWA;
    DDB6405_AWA_6405 DDB6405_AWA_6405;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT6405 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6405_AWA_6405>");
        DDB6405_AWA_6405 = (DDB6405_AWA_6405) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_MINT_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB6405_AWA_6405.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB6405_AWA_6405.AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_MINT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6405_AWA_6405.FUNC);
        CEP.TRC(SCCGWA, DDB6405_AWA_6405.AC);
        CEP.TRC(SCCGWA, DDB6405_AWA_6405.CCY);
        CEP.TRC(SCCGWA, DDB6405_AWA_6405.CCY_TYP);
        CEP.TRC(SCCGWA, DDB6405_AWA_6405.CONT_FLG);
        CEP.TRC(SCCGWA, DDB6405_AWA_6405.AC2);
        IBS.init(SCCGWA, DDCSMINT);
        DDCSMINT.FUNC = DDB6405_AWA_6405.FUNC;
        DDCSMINT.AC = DDB6405_AWA_6405.AC;
        DDCSMINT.CCY = DDB6405_AWA_6405.CCY;
        DDCSMINT.CCY_TYP = DDB6405_AWA_6405.CCY_TYP;
        DDCSMINT.CINT_FLG = DDB6405_AWA_6405.CONT_FLG;
        DDCSMINT.AC2 = DDB6405_AWA_6405.AC2;
        S000_CALL_DDZSMINT();
    }
    public void S000_CALL_DDZSMINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-MOD-M-INT", DDCSMINT);
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
