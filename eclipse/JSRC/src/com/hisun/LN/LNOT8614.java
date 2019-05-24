package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8614 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCSAPT LNCSAPT = new LNCSAPT();
    SCCGWA SCCGWA;
    LNB0025_AWA_0025 LNB0025_AWA_0025;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT8614 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB0025_AWA_0025>");
        LNB0025_AWA_0025 = (LNB0025_AWA_0025) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.APT_REF);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.CTA_NO);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.TR_VALDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_REPAY_DETAIL_PRINT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB0025_AWA_0025.APT_REF.trim().length() == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNB0025_AWA_0025.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.CTA_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB0025_AWA_0025.TR_VALDT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.TR_VALDT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_REPAY_DETAIL_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSAPT);
        LNCSAPT.COMM_DATA.CTA_NO = LNB0025_AWA_0025.CTA_NO;
        LNCSAPT.COMM_DATA.TR_VAL_DTE = LNB0025_AWA_0025.TR_VALDT;
        LNCSAPT.COMM_DATA.APT_REF = LNB0025_AWA_0025.APT_REF;
        LNCSAPT.COMM_DATA.SUBS_FLG = LNB0025_AWA_0025.SUBS_FLG;
        LNCSAPT.COMM_DATA.FUNC_CODE = 'B';
        S000_CALL_LNZSAPT();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSAPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-AMT-APPORTION", LNCSAPT);
        if (LNCSAPT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSAPT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
