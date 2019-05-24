package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5850 {
    String CPN_U_DDZUGPRS = "DD-U-GPRS-MAINTAIN";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUGPRS DDCUGPRS = new DDCUGPRS();
    SCCGWA SCCGWA;
    DDB5850_AWA_5850 DDB5850_AWA_5850;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5850 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5850_AWA_5850>");
        DDB5850_AWA_5850 = (DDB5850_AWA_5850) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_CALL_CPN_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DDB5850_AWA_5850.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, DDB5850_AWA_5850.FUNC_NO);
        }
        if (DDB5850_AWA_5850.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, DDB5850_AWA_5850.AC_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR);
        }
    }
    public void B200_CALL_CPN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUGPRS);
        DDCUGPRS.FUNC = DDB5850_AWA_5850.FUNC;
        DDCUGPRS.AC = DDB5850_AWA_5850.AC;
        DDCUGPRS.UP_AC = DDB5850_AWA_5850.UP_AC;
        DDCUGPRS.CCY = DDB5850_AWA_5850.CCY;
        DDCUGPRS.CTRL_BAL = DDB5850_AWA_5850.CTRL_BAL;
        DDCUGPRS.AUT_BAL = DDB5850_AWA_5850.AUT_BAL;
        DDCUGPRS.POOL_TYP = DDB5850_AWA_5850.POOL_TYP;
        DDCUGPRS.CTRL_MTH = DDB5850_AWA_5850.CTRL_MTH;
        DDCUGPRS.POOL_MTH = DDB5850_AWA_5850.POOL_MTH;
        DDCUGPRS.PAY_MTH = DDB5850_AWA_5850.PAY_MTH;
        S000_CALL_DDZUGPRS();
    }
    public void S000_CALL_DDZUGPRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZUGPRS, DDCUGPRS);
        if (DDCUGPRS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGPRS.RC);
            S000_ERR_MSG_PROC();
        }
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
