package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1233 {
    String JIBS_tmp_str[] = new String[10];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCCFCTR BPCCFCTR = new BPCCFCTR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1233_AWA_1233 BPB1233_AWA_1233;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1233 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1233_AWA_1233>");
        BPB1233_AWA_1233 = (BPB1233_AWA_1233) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CHANGE_FCTR();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.CTRT_NO);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.ADJ_TYP);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.ADJ_AMT);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.ADJ_DT);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.RTN_TYP);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.RTN_AC);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.RTN_CCY);
        CEP.TRC(SCCGWA, BPB1233_AWA_1233.CCY_TYPE);
    }
    public void B020_CHANGE_FCTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCFCTR);
        BPCCFCTR.DATA.CTRT_NO = BPB1233_AWA_1233.CTRT_NO;
        BPCCFCTR.DATA.ADJ_TYP = BPB1233_AWA_1233.ADJ_TYP;
        BPCCFCTR.DATA.ADJ_AMT = BPB1233_AWA_1233.ADJ_AMT;
        BPCCFCTR.DATA.ADJ_DT = BPB1233_AWA_1233.ADJ_DT;
        BPCCFCTR.DATA.RTN_TYP = BPB1233_AWA_1233.RTN_TYP;
        BPCCFCTR.DATA.RTN_AC = BPB1233_AWA_1233.RTN_AC;
        BPCCFCTR.DATA.RTN_CCY = BPB1233_AWA_1233.RTN_CCY;
        BPCCFCTR.DATA.CCY_TYPE = BPB1233_AWA_1233.CCY_TYPE;
        S000_CALL_BPZCFCTR();
    }
    public void S000_CALL_BPZCFCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CHANGE-FCTR", BPCCFCTR);
        CEP.TRC(SCCGWA, BPCCFCTR.RC);
        if (BPCCFCTR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCFCTR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
