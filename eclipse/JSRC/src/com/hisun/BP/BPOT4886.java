package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4886 {
    String CPN_S_RULE = "BP-S-MGM-RULE    ";
    String CPN_R_PRMR = "BP-PARM-READ     ";
    String CPN_SC_MSG_INQ = "SC-MSG-INQ       ";
    String K_PARM_TYPE = "MSG  ";
    String K_OUTPUT_FMT = "BP538";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCIMSG SCCIMSG = new SCCIMSG();
    BPCOMSG1 BPCOMSG1 = new BPCOMSG1();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4886_AWA_4886 BPB4886_AWA_4886;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4886 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4886_AWA_4886>");
        BPB4886_AWA_4886 = (BPB4886_AWA_4886) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GET_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCIMSG);
        SCCIMSG.ID = BPB4886_AWA_4886.MSG_CODE;
        S000_CALL_SCZIMSG();
        CEP.TRC(SCCGWA, SCCIMSG);
        IBS.init(SCCGWA, BPCOMSG1);
        BPCOMSG1.MSG_CODE = SCCIMSG.ID;
        BPCOMSG1.MSG_DESC = SCCIMSG.TXT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOMSG1;
        SCCFMT.DATA_LEN = 66;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZIMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SC_MSG_INQ, SCCIMSG);
        if (SCCIMSG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCIMSG.RC);
            WS_FLD_NO = BPB4886_AWA_4886.MSG_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_R_PRMR, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            WS_FLD_NO = BPB4886_AWA_4886.MSG_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
