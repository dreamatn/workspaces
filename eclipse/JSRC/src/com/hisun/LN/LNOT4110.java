package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4110 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    char WS_INT_MODE_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCSPREQ LNCSPREQ = new LNCSPREQ();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB4110_AWA_4110 LNB4110_AWA_4110;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT4110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4110_AWA_4110>");
        LNB4110_AWA_4110 = (LNB4110_AWA_4110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_REPAY_ENQY_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB4110_AWA_4110.CTA.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB4110_AWA_4110.CTA_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB4110_AWA_4110.TR_VAL_D == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB4110_AWA_4110.TR_VAL_D_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB4110_AWA_4110.TOT_P_AM == 0 
            && LNB4110_AWA_4110.TOT_AMT == 0 
            && LNB4110_AWA_4110.INT_MODE != '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB4110_AWA_4110.TOT_P_AM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_INT_MODE_FLAG = LNB4110_AWA_4110.INT_MODE;
        if ((WS_INT_MODE_FLAG != '0' 
            && WS_INT_MODE_FLAG != '1' 
            && WS_INT_MODE_FLAG != '2')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_INT_MODE_MUST_INPUT;
            WS_FLD_NO = LNB4110_AWA_4110.INT_MODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB4110_AWA_4110.CTA;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        S000_CALL_LNZSSTBL();
    }
    public void B020_REPAY_ENQY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPREQ);
        LNCSPREQ.FUN_CODE = 'I';
        LNCSPREQ.CTA = LNB4110_AWA_4110.CTA;
        LNCSPREQ.TR_VAL_DTE = LNB4110_AWA_4110.TR_VAL_D;
        LNCSPREQ.TOT_P_AMT = LNB4110_AWA_4110.TOT_P_AM;
        LNCSPREQ.INT_MODE = LNB4110_AWA_4110.INT_MODE;
        LNCSPREQ.PC_RATE = LNB4110_AWA_4110.PC_RATE;
        LNCSPREQ.TOT_AMT = LNB4110_AWA_4110.TOT_AMT;
        CEP.TRC(SCCGWA, LNB4110_AWA_4110.CTA);
        CEP.TRC(SCCGWA, LNB4110_AWA_4110.TR_VAL_D);
        CEP.TRC(SCCGWA, LNB4110_AWA_4110.TOT_P_AM);
        CEP.TRC(SCCGWA, LNB4110_AWA_4110.INT_MODE);
        CEP.TRC(SCCGWA, LNB4110_AWA_4110.PC_RATE);
        CEP.TRC(SCCGWA, LNB4110_AWA_4110.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.TR_VAL_DTE);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_P_AMT);
        S000_CALL_LNZSPREQ();
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSPREQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PREPAY-CALC", LNCSPREQ);
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
