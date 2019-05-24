package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1274 {
    String CPN_S_BPZFFCLT = "BP-F-S-FEE-COLLECT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFCLT BPCSFCLT = new BPCSFCLT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1270_AWA_1270 BPB1270_AWA_1270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1274 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1270_AWA_1270>");
        BPB1270_AWA_1270 = (BPB1270_AWA_1270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFCLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DEL_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFCLT);
        BPCSFCLT.CLT_TYPE = BPB1270_AWA_1270.CLT_TYPE;
        BPCSFCLT.SGN_TYPE = BPB1270_AWA_1270.SGN_TYPE;
        BPCSFCLT.SGN_CINO = BPB1270_AWA_1270.SGN_CINO;
        BPCSFCLT.SGN_AC = BPB1270_AWA_1270.SGN_AC;
        BPCSFCLT.EFF_DATE = BPB1270_AWA_1270.EFF_DATE;
        CEP.TRC(SCCGWA, BPCSFCLT.EFF_DATE);
        BPCSFCLT.FUNC = 'D';
        S00_CALL_BPZFFCLT();
    }
    public void S00_CALL_BPZFFCLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFCLT, BPCSFCLT);
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