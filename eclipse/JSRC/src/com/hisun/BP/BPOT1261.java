package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1261 {
    String CPN_S_BPZFFPDT = "BP-F-S-FE-UNCHG-DTL";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFPDT BPCSFPDT = new BPCSFPDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1260_AWA_1260 BPB1260_AWA_1260;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1261 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1260_AWA_1260>");
        BPB1260_AWA_1260 = (BPB1260_AWA_1260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFPDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFPDT);
        BPCSFPDT.FDT_TYP = BPB1260_AWA_1260.FDT_TYP;
        BPCSFPDT.TRT_DT = BPB1260_AWA_1260.TRT_DT;
        BPCSFPDT.JRN_NO = BPB1260_AWA_1260.JRN_NO;
        BPCSFPDT.JRN_SEQ = BPB1260_AWA_1260.JRN_SEQ;
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.TRT_DT);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.JRN_NO);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.JRN_SEQ);
        BPCSFPDT.FUNC = 'I';
        S00_CALL_BPZFFPDT();
    }
    public void S00_CALL_BPZFFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFPDT, BPCSFPDT);
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
