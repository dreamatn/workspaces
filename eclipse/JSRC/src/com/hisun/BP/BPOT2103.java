package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2103 {
    String K_OUTPUT_FMT = "BP114";
    String CPN_P_INQ_IBAC = "BP-P-INQ-IBAC       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2103_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT2103_WS_OUTPUT_DATA();
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQIBA BPCPQIBA = new BPCPQIBA();
    SCCGWA SCCGWA;
    BPB2100_AWA_2100 BPB2100_AWA_2100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2103 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2100_AWA_2100>");
        BPB2100_AWA_2100 = (BPB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_IBAC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_IBAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.C_SWIFT);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INFO[1-1].CCY);
        IBS.init(SCCGWA, BPCPQIBA);
        BPCPQIBA.DATA_INFO.SWIFT = BPB2100_AWA_2100.C_SWIFT;
        BPCPQIBA.DATA_INFO.CCY = BPB2100_AWA_2100.CCY_INFO[1-1].CCY;
        S000_CALL_BPZPQIBA();
        WS_OUTPUT_DATA.WS_IB_AC = BPCPQIBA.DATA_INFO.IB_AC;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_IB_AC);
        CEP.TRC(SCCGWA, BPCPQIBA.DATA_INFO.AC_NAME);
        WS_OUTPUT_DATA.WS_AC_NAME = BPCPQIBA.DATA_INFO.AC_NAME;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 93;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQIBA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_IBAC, BPCPQIBA);
        if (BPCPQIBA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQIBA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
