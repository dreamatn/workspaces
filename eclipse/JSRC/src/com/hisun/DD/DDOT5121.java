package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5121 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMTDF DDCSMTDF = new DDCSMTDF();
    SCCGWA SCCGWA;
    DDB5121_AWA_5121 DDB5121_AWA_5121;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5121 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5121_AWA_5121>");
        DDB5121_AWA_5121 = (DDB5121_AWA_5121) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5121_AWA_5121.I_DD_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5121_AWA_5121.I_CCY.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5121_AWA_5121.I_FLG == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5121_AWA_5121.I_DD_AC);
        CEP.TRC(SCCGWA, DDB5121_AWA_5121.I_CCY);
        IBS.init(SCCGWA, DDCSMTDF);
        DDCSMTDF.OPT = '4';
        DDCSMTDF.AC = DDB5121_AWA_5121.I_DD_AC;
        DDCSMTDF.CCY = DDB5121_AWA_5121.I_CCY;
        DDCSMTDF.FLG = DDB5121_AWA_5121.I_FLG;
        CEP.TRC(SCCGWA, DDB5121_AWA_5121.I_DD_AC);
        CEP.TRC(SCCGWA, DDCSMTDF.OPT);
        CEP.TRC(SCCGWA, DDCSMTDF.AC);
        CEP.TRC(SCCGWA, DDCSMTDF.CCY);
        S000_CALL_DDZSMTDF();
    }
    public void S000_CALL_DDZSMTDF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMTDF", DDCSMTDF);
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
