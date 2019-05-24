package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7410 {
    String K_OUT_FMT = "DC741";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCOT7410_WS_OUTPUT WS_OUTPUT = new DCOT7410_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    SCCGWA SCCGWA;
    DCB7410_AWA_7410 DCB7410_AWA_7410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7410 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7410_AWA_7410>");
        DCB7410_AWA_7410 = (DCB7410_AWA_7410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B200_TRANS_DATA_PROC();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7410_AWA_7410);
        CEP.TRC(SCCGWA, DCB7410_AWA_7410.AC);
        if (DCB7410_AWA_7410.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIQHLD);
        DCCIQHLD.INP_DATA.AC = DCB7410_AWA_7410.AC;
        S000_CALL_DCZIQHLD();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIQHLD.OUT_DATA.LAW_AC);
        CEP.TRC(SCCGWA, DCCIQHLD.OUT_DATA.BNK_AC);
        CEP.TRC(SCCGWA, "AAAA1");
        CEP.TRC(SCCGWA, DCCIQHLD.OUT_DATA.LAW_AMT);
        CEP.TRC(SCCGWA, DCCIQHLD.OUT_DATA.BNK_AMT);
        WS_OUTPUT.WS_LAW_AC = DCCIQHLD.OUT_DATA.LAW_AC;
        WS_OUTPUT.WS_BNK_AC = DCCIQHLD.OUT_DATA.BNK_AC;
        WS_OUTPUT.WS_LAW_AMT = DCCIQHLD.OUT_DATA.LAW_AMT;
        WS_OUTPUT.WS_BNK_AMT = DCCIQHLD.OUT_DATA.BNK_AMT;
        CEP.TRC(SCCGWA, "AAAA2");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 4;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "AAAA3");
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD, true);
        CEP.TRC(SCCGWA, "AAAA4");
        if (DCCIQHLD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIQHLD.RC);
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
