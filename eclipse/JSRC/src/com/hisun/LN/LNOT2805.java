package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2805 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSPPRM LNCSPPRM = new LNCSPPRM();
    SCCGWA SCCGWA;
    LNB2800_AWA_2800 LNB2800_AWA_2800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT2805 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2800_AWA_2800>");
        LNB2800_AWA_2800 = (LNB2800_AWA_2800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, LNCSPPRM);
        LNCSPPRM.RC.RC_MMO = "LN";
        LNCSPPRM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        WS_ERR_FLG = 'N';
        if (LNB2800_AWA_2800.CONT_NO.trim().length() == 0) {
            WS_ERR_FLG = 'Y';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB2800_AWA_2800.CONT_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB2800_AWA_2800.VAL_DT == 0) {
            WS_ERR_FLG = 'Y';
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB2800_AWA_2800.VAL_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_ERR_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B021_INTPUT_PPRP();
    }
    public void B021_INTPUT_PPRP() throws IOException,SQLException,Exception {
        LNCSPPRM.COMM_DATA.CONT_NO = LNB2800_AWA_2800.CONT_NO;
        LNCSPPRM.COMM_DATA.VAL_DT = LNB2800_AWA_2800.VAL_DT;
        LNCSPPRM.FUNC = 'I';
        S000_CALL_LNCSPPRM();
    }
    public void S000_CALL_LNCSPPRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PPRP-MAIN", LNCSPPRM);
        if (LNCSPPRM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPPRM.RC);
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
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
