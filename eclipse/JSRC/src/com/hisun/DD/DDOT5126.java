package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5126 {
    String WS_MSGID = " ";
    short WS_IDX = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSMTDR DDCSMTDR = new DDCSMTDR();
    SCCGWA SCCGWA;
    DDB5126_AWA_5126 DDB5126_AWA_5126;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5126 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5126_AWA_5126>");
        DDB5126_AWA_5126 = (DDB5126_AWA_5126) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5126_AWA_5126.B_DD_OPT == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5126_AWA_5126.B_DD_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5126_AWA_5126.B_DD_CCY.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (!DDB5126_AWA_5126.B_DD_CCY.equalsIgnoreCase("156")) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_BE_RBM;
            S000_ERR_MSG_PROC();
        }
        if (DDB5126_AWA_5126.B_DD_FLG == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5126_AWA_5126.B_DD_OPT);
        CEP.TRC(SCCGWA, DDB5126_AWA_5126.B_DD_AC);
        CEP.TRC(SCCGWA, DDB5126_AWA_5126.B_DD_CCY);
        IBS.init(SCCGWA, DDCSMTDR);
        DDCSMTDR.I_OPT = DDB5126_AWA_5126.B_DD_OPT;
        DDCSMTDR.I_DD_AC = DDB5126_AWA_5126.B_DD_AC;
        DDCSMTDR.I_CCY = DDB5126_AWA_5126.B_DD_CCY;
        DDCSMTDR.I_FLG = DDB5126_AWA_5126.B_DD_FLG;
        DDCSMTDR.I_CI_NO = DDB5126_AWA_5126.B_CI_NO;
        DDCSMTDR.I_CI_CNM = DDB5126_AWA_5126.B_CI_CNM;
        DDCSMTDR.I_CI_ENM = DDB5126_AWA_5126.B_CI_ENM;
        DDCSMTDR.I_BALS = DDB5126_AWA_5126.B_BAL_S;
        DDCSMTDR.I_STR_DT = DDB5126_AWA_5126.B_STR_DT;
        CEP.TRC(SCCGWA, DDCSMTDR.I_STR_DT);
        DDCSMTDR.I_RATETY = DDB5126_AWA_5126.B_RATETY;
        DDCSMTDR.I_TERM = DDB5126_AWA_5126.B_TERM;
        DDCSMTDR.I_INTRAT = DDB5126_AWA_5126.B_INTRAT;
        DDCSMTDR.I_TYP = DDB5126_AWA_5126.B_TYP;
        DDCSMTDR.I_RATEOF = DDB5126_AWA_5126.B_RATEOF;
        DDCSMTDR.I_PCTS = DDB5126_AWA_5126.B_PCTS;
        DDCSMTDR.I_CXRAT = DDB5126_AWA_5126.B_CXRAT;
        DDCSMTDR.I_CRTDT = DDB5126_AWA_5126.B_CRTDT;
        DDCSMTDR.I_VSTRDT = DDB5126_AWA_5126.B_VSTRDT;
        DDCSMTDR.I_ENDDT = DDB5126_AWA_5126.B_ENDDT;
        DDCSMTDR.I_RCVDT = DDB5126_AWA_5126.B_RCVDT;
        DDCSMTDR.I_PROL_NO = DDB5126_AWA_5126.B_PROL_N;
        CEP.TRC(SCCGWA, DDCSMTDR.I_OPT);
        CEP.TRC(SCCGWA, DDCSMTDR.I_DD_AC);
        CEP.TRC(SCCGWA, DDCSMTDR.I_CCY);
        S000_CALL_DDZSMTDR();
    }
    public void S000_CALL_DDZSMTDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMTDR", DDCSMTDR);
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
