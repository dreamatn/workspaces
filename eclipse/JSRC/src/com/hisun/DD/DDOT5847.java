package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5847 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    short WS_CNT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSZFJZ DDCSZFJZ = new DDCSZFJZ();
    SCCGWA SCCGWA;
    DDB5847_AWA_5847 DDB5847_AWA_5847;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5847 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5847_AWA_5847>");
        DDB5847_AWA_5847 = (DDB5847_AWA_5847) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_MINT_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5847_AWA_5847.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_MINT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5847_AWA_5847.FUNC);
        CEP.TRC(SCCGWA, DDB5847_AWA_5847.AC);
        CEP.TRC(SCCGWA, DDB5847_AWA_5847.CCY);
        CEP.TRC(SCCGWA, DDB5847_AWA_5847.CCY_TYPE);
        IBS.init(SCCGWA, DDCSZFJZ);
        DDCSZFJZ.FUNC = DDB5847_AWA_5847.FUNC;
        DDCSZFJZ.DATA.HLD_NO = DDB5847_AWA_5847.HLD_NO;
        DDCSZFJZ.DATA.AC = DDB5847_AWA_5847.AC;
        DDCSZFJZ.DATA.AC_SEQ = DDB5847_AWA_5847.AC_SEQ;
        DDCSZFJZ.DATA.ACO_AC = DDB5847_AWA_5847.ACO_AC;
        DDCSZFJZ.DATA.CCY = DDB5847_AWA_5847.CCY;
        DDCSZFJZ.DATA.CCY_TYPE = DDB5847_AWA_5847.CCY_TYPE;
        DDCSZFJZ.DATA.EXP_DT = DDB5847_AWA_5847.EXP_DT;
        DDCSZFJZ.DATA.RSN = DDB5847_AWA_5847.RSN;
        DDCSZFJZ.DATA.RMK = DDB5847_AWA_5847.RMK;
        S000_CALL_DDZSZFJZ();
    }
    public void S000_CALL_DDZSZFJZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-M-DDZSZFJZ", DDCSZFJZ);
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
