package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4200 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSHSQ LNCSHSQ = new LNCSHSQ();
    SCCGWA SCCGWA;
    LNB4200_AWA_4200 LNB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT4200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4200_AWA_4200>");
        LNB4200_AWA_4200 = (LNB4200_AWA_4200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_HISTORY_ENQUIRY();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB4200_AWA_4200.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB4200_AWA_4200.CTA_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_HISTORY_ENQUIRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSHSQ);
        LNCSHSQ.COMM_INFO.CTA_INFO.CTA_NO = LNB4200_AWA_4200.CTA_NO;
        LNCSHSQ.COMM_INFO.CTA_INFO.BR = LNB4200_AWA_4200.BR;
        LNCSHSQ.COMM_INFO.CTA_INFO.CI_NO = LNB4200_AWA_4200.CI_NO;
        LNCSHSQ.COMM_INFO.CTA_INFO.CI_CNM = LNB4200_AWA_4200.CI_CNM;
        LNCSHSQ.COMM_INFO.CTA_INFO.PROD_CD = LNB4200_AWA_4200.PROD_CD;
        LNCSHSQ.COMM_INFO.CTA_INFO.CCY = LNB4200_AWA_4200.CCY;
        LNCSHSQ.COMM_INFO.CTA_INFO.LON_PRIN = LNB4200_AWA_4200.LN_PRIN;
        LNCSHSQ.COMM_INFO.CTA_INFO.LON_BAL = LNB4200_AWA_4200.LN_BAL;
        LNCSHSQ.COMM_INFO.DATE_INFO.BEG_DTE = LNB4200_AWA_4200.BEG_DT;
        LNCSHSQ.COMM_INFO.DATE_INFO.END_DTE = LNB4200_AWA_4200.END_DT;
        CEP.TRC(SCCGWA, "AWA-PAGE-ROW");
        CEP.TRC(SCCGWA, LNB4200_AWA_4200.PAGE_ROW);
        LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_ROW = LNB4200_AWA_4200.PAGE_ROW;
        LNCSHSQ.COMM_INFO.CTA_INFO.PAGE_NUM = LNB4200_AWA_4200.PAGE_NUM;
        S000_CALL_LNZSHSQ();
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
