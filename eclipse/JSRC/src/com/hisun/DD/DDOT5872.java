package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5872 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCURHVS DDCURHVS = new DDCURHVS();
    SCCGWA SCCGWA;
    DDB5872_AWA_5872 DDB5872_AWA_5872;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5872 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5872_AWA_5872>");
        DDB5872_AWA_5872 = (DDB5872_AWA_5872) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5872_AWA_5872.FUNC);
        CEP.TRC(SCCGWA, DDB5872_AWA_5872.VS_AC);
        CEP.TRC(SCCGWA, DDB5872_AWA_5872.CCY);
        CEP.TRC(SCCGWA, DDB5872_AWA_5872.CCY_TYP);
        CEP.TRC(SCCGWA, DDB5872_AWA_5872.HOLD_TYP);
        CEP.TRC(SCCGWA, DDB5872_AWA_5872.HOLD_AMT);
        if (DDB5872_AWA_5872.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            WS_FLD_NO = DDB5872_AWA_5872.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5872_AWA_5872.VS_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAC_MST_IPT;
            WS_FLD_NO = DDB5872_AWA_5872.VS_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5872_AWA_5872.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB5872_AWA_5872.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5872_AWA_5872.CCY_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            WS_FLD_NO = DDB5872_AWA_5872.CCY_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5872_AWA_5872.HOLD_TYP == '2') {
            if (DDB5872_AWA_5872.HOLD_AMT == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HOLD_AMT_M_INPUT;
                WS_FLD_NO = DDB5872_AWA_5872.HOLD_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCURHVS);
        DDCURHVS.INPUT.FUNC = DDB5872_AWA_5872.FUNC;
        DDCURHVS.INPUT.VS_AC = DDB5872_AWA_5872.VS_AC;
        DDCURHVS.INPUT.CCY = DDB5872_AWA_5872.CCY;
        DDCURHVS.INPUT.CCY_TYP = DDB5872_AWA_5872.CCY_TYP;
        DDCURHVS.INPUT.HOLD_TYP = DDB5872_AWA_5872.HOLD_TYP;
        DDCURHVS.INPUT.HOLD_AMT = DDB5872_AWA_5872.HOLD_AMT;
        S000_CALL_DDZURHVS();
    }
    public void S000_CALL_DDZURHVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-VS-HOLD", DDCURHVS);
        CEP.TRC(SCCGWA, DDCURHVS.RC);
        if (DDCURHVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCURHVS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
