package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8925 {
    char WS_SS = ' ';
    double WS_ST = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCXQ72 LNCXQ72 = new LNCXQ72();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSCMRT LNCSCMRT = new LNCSCMRT();
    SCCGWA SCCGWA;
    LNB8925_AWA_8925 LNB8925_AWA_8925;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8925 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8925_AWA_8925>");
        LNB8925_AWA_8925 = (LNB8925_AWA_8925) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8925_AWA_8925.COMP_MTH);
        CEP.TRC(SCCGWA, LNB8925_AWA_8925.RATE1);
        CEP.TRC(SCCGWA, LNB8925_AWA_8925.RATE2);
        CEP.TRC(SCCGWA, LNB8925_AWA_8925.RATE3);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCMRT);
        LNCSCMRT.COMP_MTH = LNB8925_AWA_8925.COMP_MTH;
        LNCSCMRT.RATE1 = LNB8925_AWA_8925.RATE1;
        LNCSCMRT.RATE2 = LNB8925_AWA_8925.RATE2;
        LNCSCMRT.RATE3 = LNB8925_AWA_8925.RATE3;
        S000_CALL_LNZSCMRT();
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCXQ72);
        IBS.init(SCCGWA, SCCFMT);
        LNCXQ72.COMP_MTH = LNCSCMRT.COMP_MTH;
        LNCXQ72.RATE1 = LNCSCMRT.RATE1;
        LNCXQ72.RATE2 = LNCSCMRT.RATE2;
        LNCXQ72.RATE3 = LNCSCMRT.RATE3;
        LNCXQ72.ALL_RATE = LNCSCMRT.ALL_RATE;
        CEP.TRC(SCCGWA, LNCXQ72);
        CEP.TRC(SCCGWA, LNCXQ72.RATE1);
        CEP.TRC(SCCGWA, LNCXQ72.RATE2);
        CEP.TRC(SCCGWA, LNCXQ72.RATE3);
        CEP.TRC(SCCGWA, LNCXQ72.ALL_RATE);
        SCCFMT.FMTID = "LNQ72";
        SCCFMT.DATA_PTR = LNCXQ72;
        SCCFMT.DATA_LEN = 53;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZSCMRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CMRT", LNCSCMRT);
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
