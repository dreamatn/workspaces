package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2800 {
    String WS_ERR_MSG = " ";
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
        CEP.TRC(SCCGWA, "LNOT2800 return!");
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
        if (LNB2800_AWA_2800.VAL_DT == 0) {
            LNCSPPRM.COMM_DATA.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            LNCSPPRM.COMM_DATA.VAL_DT = LNB2800_AWA_2800.VAL_DT;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B021_INTPUT_PPRP();
    }
    public void B021_INTPUT_PPRP() throws IOException,SQLException,Exception {
        LNCSPPRM.COMM_DATA.BOOK_BR = LNB2800_AWA_2800.BOOK_BR;
        LNCSPPRM.COMM_DATA.CONT_NO = LNB2800_AWA_2800.CONT_NO;
        LNCSPPRM.COMM_DATA.PAGE_NUM = LNB2800_AWA_2800.PAGE_NUM;
        LNCSPPRM.COMM_DATA.PAGE_ROW = LNB2800_AWA_2800.PAGE_ROW;
        LNCSPPRM.FUNC = 'B';
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
