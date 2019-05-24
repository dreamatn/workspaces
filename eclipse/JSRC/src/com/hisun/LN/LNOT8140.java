package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8140 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCLETIF LNCLETIF = new LNCLETIF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSCNTA LNCSCNTA = new LNCSCNTA();
    SCCGWA SCCGWA;
    LNB8140_AWA_8140 LNB8140_AWA_8140;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8140 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8140_AWA_8140>");
        LNB8140_AWA_8140 = (LNB8140_AWA_8140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCLETIF.RC.RC_MMO = "LN";
        LNCLETIF.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FUNC_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8140_AWA_8140.PAPER_NO);
        if (LNB8140_AWA_8140.PAPER_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_FUNC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLETIF);
        LNCLETIF.PAPER_NO = LNB8140_AWA_8140.PAPER_NO;
        LNCLETIF.LN_CCY = LNB8140_AWA_8140.LN_CCY;
        LNCLETIF.STS = LNB8140_AWA_8140.STS;
        LNCLETIF.DUE_DT1 = LNB8140_AWA_8140.DUE_DT1;
        LNCLETIF.DUE_DT2 = LNB8140_AWA_8140.DUE_DT2;
        LNCLETIF.DATA.PAGE_ROW = LNB8140_AWA_8140.PAGE_ROW;
        LNCLETIF.DATA.PAGE_NUM = LNB8140_AWA_8140.PAGE_NUM;
        S000_CALL_LNZLETIF();
    }
    public void S000_CALL_LNZLETIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-QUE-LETIF", LNCLETIF);
        if (LNCLETIF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCLETIF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
