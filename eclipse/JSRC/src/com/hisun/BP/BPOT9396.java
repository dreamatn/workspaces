package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9396 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    SCCGWA SCCGWA;
    BPB4930_AWA_4930 BPB4930_AWA_4930;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9396 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4930_AWA_4930>");
        BPB4930_AWA_4930 = (BPB4930_AWA_4930) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.TLR);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.PSW);
        if (BPB4930_AWA_4930.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4930_AWA_4930.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4930_AWA_4930.PSW.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PASSWORD_MUST_INPUT;
            WS_FLD_NO = BPB4930_AWA_4930.PSW_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB4930_AWA_4930.TLR;
        S000_CALL_BPZFTLRQ();
        IBS.init(SCCGWA, BPCFKPSW);
        BPCFKPSW.OPT = 'C';
        BPCFKPSW.TLR = BPB4930_AWA_4930.TLR;
        BPCFKPSW.OLD_PSW = BPB4930_AWA_4930.PSW;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFKPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-K-PSW-MAINTAIN", BPCFKPSW);
        CEP.TRC(SCCGWA, BPCFKPSW.RC);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
