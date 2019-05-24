package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2138 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSGUEX LNCSGUEX = new LNCSGUEX();
    SCCGWA SCCGWA;
    LNB2138_AWA_2138 LNB2138_AWA_2138;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT2138 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2138_AWA_2138>");
        LNB2138_AWA_2138 = (LNB2138_AWA_2138) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSGUEX.RC.RC_MMO = "LN";
        LNCSGUEX.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FACILITY_MAINTENANCE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB2138_AWA_2138.CONT_NO);
        if (LNB2138_AWA_2138.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB2138_AWA_2138.CONT_NO_NO;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_FACILITY_MAINTENANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSGUEX);
        LNCSGUEX.DATA.TRCM_NO = LNB2138_AWA_2138.TRCM_NO;
        LNCSGUEX.DATA.TC_CCY = LNB2138_AWA_2138.TC_CCY;
        LNCSGUEX.DATA.CONT_NO = LNB2138_AWA_2138.CONT_NO;
        LNCSGUEX.DATA.CO_CCY = LNB2138_AWA_2138.CO_CCY;
        LNCSGUEX.DATA.CONT_PRIN = LNB2138_AWA_2138.PRIN;
        LNCSGUEX.DATA.EX_RATE = LNB2138_AWA_2138.EX_RATE;
        LNCSGUEX.FUNC = 'T';
        S000_CALL_LNZSGUEX();
    }
    public void S000_CALL_LNZSGUEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-GUEX", LNCSGUEX);
        if (LNCSGUEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSGUEX.RC);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
