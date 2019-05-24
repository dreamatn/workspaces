package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4310 {
    String WS_ERR_MSG = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSPPRM LNCSPPRM = new LNCSPPRM();
    SCCGWA SCCGWA;
    LNB4310_AWA_4310 LNB4310_AWA_4310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT4310 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4310_AWA_4310>");
        LNB4310_AWA_4310 = (LNB4310_AWA_4310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, LNCSPPRM);
        LNCSPPRM.RC.RC_MMO = "LN";
        LNCSPPRM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNB4310_AWA_4310.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B021_INTPUT_PPRP();
    }
    public void B021_INTPUT_PPRP() throws IOException,SQLException,Exception {
        if (LNB4310_AWA_4310.FCN_CD == 'A') {
            LNCSPPRM.FUNC = 'A';
        } else if (LNB4310_AWA_4310.FCN_CD == 'D') {
            LNCSPPRM.FUNC = 'D';
        } else if (LNB4310_AWA_4310.FCN_CD == 'M') {
            LNCSPPRM.FUNC = 'M';
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, LNB4310_AWA_4310.VAL_DT);
        if (LNB4310_AWA_4310.VAL_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DATE;
            S000_ERR_MSG_PROC();
        }
        LNCSPPRM.COMM_DATA.CONT_NO = LNB4310_AWA_4310.CONT_NO;
        LNCSPPRM.COMM_DATA.VAL_DT = LNB4310_AWA_4310.VAL_DT;
        LNCSPPRM.COMM_DATA.CI_NO = LNB4310_AWA_4310.CI_NO;
        LNCSPPRM.COMM_DATA.CI_CNM = LNB4310_AWA_4310.CI_CNM;
        LNCSPPRM.COMM_DATA.PROD_CD = LNB4310_AWA_4310.PROD_CD;
        LNCSPPRM.COMM_DATA.CCY = LNB4310_AWA_4310.CCY;
        LNCSPPRM.COMM_DATA.LN_PRIN = LNB4310_AWA_4310.LN_PRIN;
        LNCSPPRM.COMM_DATA.LN_BAL = LNB4310_AWA_4310.LN_BAL;
        LNCSPPRM.COMM_DATA.PAY_PRIN = LNB4310_AWA_4310.PAY_PRIN;
        LNCSPPRM.COMM_DATA.INT_MODE = LNB4310_AWA_4310.INT_MODE;
        LNCSPPRM.COMM_DATA.PAY_INT = LNB4310_AWA_4310.PAY_INT;
        LNCSPPRM.COMM_DATA.PC_RATE = LNB4310_AWA_4310.PC_RATE;
        LNCSPPRM.COMM_DATA.PC_AMT = LNB4310_AWA_4310.PC_AMT;
        LNCSPPRM.COMM_DATA.PAY_AMT = LNB4310_AWA_4310.PAY_AMT;
        LNCSPPRM.COMM_DATA.HRG_AMT = LNB4310_AWA_4310.HRG_AMT;
        LNCSPPRM.COMM_DATA.ADJ_FLG = LNB4310_AWA_4310.ADJ_FLG;
        LNCSPPRM.COMM_DATA.SUB_TERM = LNB4310_AWA_4310.SUB_TERM;
        LNCSPPRM.COMM_DATA.STATUS = '1';
        LNCSPPRM.COMM_DATA.REMARK = LNB4310_AWA_4310.REMARK;
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
