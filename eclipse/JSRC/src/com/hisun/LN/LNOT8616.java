package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8616 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TOT_AMT = 0;
    char WS_END_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCSHSQ LNCSHSQ = new LNCSHSQ();
    SCCGWA SCCGWA;
    LNB0025_AWA_0025 LNB0025_AWA_0025;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8616 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB0025_AWA_0025>");
        LNB0025_AWA_0025 = (LNB0025_AWA_0025) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_HISTORY_ENQUIRY();
        B300_OUTPUT_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.CTA_NO);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.BEG_DTE);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.END_DTE);
        if (LNB0025_AWA_0025.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.CTA_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB0025_AWA_0025.TR_DT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.TR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB0025_AWA_0025.TR_JRN == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.TR_JRN_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_HISTORY_ENQUIRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSHSQ);
        LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO = LNB0025_AWA_0025.CTA_NO;
        LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE = LNB0025_AWA_0025.TR_DT;
        LNCSHSQ.COMM_DATA.TRAN_INFO.TR_JRNNO = LNB0025_AWA_0025.TR_JRN;
        LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE = LNB0025_AWA_0025.TR_VALDT;
        LNCSHSQ.COMM_DATA.TRAN_INFO.TR_RVS = LNB0025_AWA_0025.TR_DEL;
        LNCSHSQ.COMM_DATA.TRAN_INFO.DUE_DT = LNB0025_AWA_0025.D_DUEDT;
        LNCSHSQ.COMM_DATA.TRAN_INFO.PRIN = LNB0025_AWA_0025.D_PRIN;
        LNCSHSQ.COMM_DATA.TRAN_INFO.INT = LNB0025_AWA_0025.D_INT;
        LNCSHSQ.COMM_DATA.TRAN_INFO.PRIN_LC = LNB0025_AWA_0025.D_PLC;
        LNCSHSQ.COMM_DATA.TRAN_INFO.INT_LC = LNB0025_AWA_0025.D_ILC;
        LNCSHSQ.COMM_DATA.TRAN_INFO.PRIN_LC_W = LNB0025_AWA_0025.D_PLC_W;
        LNCSHSQ.COMM_DATA.TRAN_INFO.INT_LC_W = LNB0025_AWA_0025.D_ILC_W;
        LNCSHSQ.COMM_DATA.TRAN_INFO.PREPAY_CHG = LNB0025_AWA_0025.D_PRCHG;
        LNCSHSQ.COMM_DATA.TRAN_INFO.BFC = LNB0025_AWA_0025.D_BFC;
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE);
        CEP.TRC(SCCGWA, LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE);
        LNCSHSQ.FUNC_CODE = 'S';
        S000_CALL_LNZSHSQ();
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZSHSQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-REPAY-HIS-ENQ", LNCSHSQ);
        if (LNCSHSQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSHSQ.RC);
            S000_ERR_MSG_PROC();
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
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
