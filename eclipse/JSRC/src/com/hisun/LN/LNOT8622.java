package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8622 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_INT_MODE_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCSPREQ LNCSPREQ = new LNCSPREQ();
    SCCGWA SCCGWA;
    LNB0024_AWA_0024 LNB0024_AWA_0024;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8622 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB0024_AWA_0024>");
        LNB0024_AWA_0024 = (LNB0024_AWA_0024) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, LNB0024_AWA_0024.INT_MODE);
        CEP.TRC(SCCGWA, LNB0024_AWA_0024.PC_RATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_REPAY_ENQY_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB0024_AWA_0024.CTA.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0024_AWA_0024.CTA_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB0024_AWA_0024.TR_VAL_D == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0024_AWA_0024.TR_VAL_D_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_INT_MODE_FLAG = LNB0024_AWA_0024.INT_MODE;
        if ((WS_INT_MODE_FLAG != '0' 
            && WS_INT_MODE_FLAG != '1' 
            && WS_INT_MODE_FLAG != '2')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0024_AWA_0024.INT_MODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_REPAY_ENQY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPREQ);
        LNCSPREQ.FUN_CODE = 'I';
        LNCSPREQ.CTA = LNB0024_AWA_0024.CTA;
        LNCSPREQ.TR_VAL_DTE = LNB0024_AWA_0024.TR_VAL_D;
        LNCSPREQ.TOT_P_AMT = LNB0024_AWA_0024.TOT_P_AM;
        LNCSPREQ.INT_MODE = LNB0024_AWA_0024.INT_MODE;
        LNCSPREQ.PC_RATE = LNB0024_AWA_0024.PC_RATE;
        if (LNB0024_AWA_0024.PC_RATE == 0) {
            LNCSPREQ.PC_AMT = LNB0024_AWA_0024.PC_AMT;
        }
        LNCSPREQ.SUB_TRAN = 'Y';
        S000_CALL_LNZSPREQ();
    }
    public void S000_CALL_LNZSPREQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PREPAY-CALC", LNCSPREQ);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
