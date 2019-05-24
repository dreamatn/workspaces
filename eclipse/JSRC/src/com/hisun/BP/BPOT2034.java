package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2034 {
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_IBAC_MT = "BP-S-IBAC-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOLIBM BPCOLIBM = new BPCOLIBM();
    BPCSIBNA BPCSIBNA = new BPCSIBNA();
    BPCSIBAC BPCSIBAC = new BPCSIBAC();
    SCCGWA SCCGWA;
    BPB2030_AWA_2030 BPB2030_AWA_2030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2034 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2030_AWA_2030>");
        BPB2030_AWA_2030 = (BPB2030_AWA_2030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2030_AWA_2030.CCY);
        CEP.TRC(SCCGWA, BPB2030_AWA_2030.SWIFT);
        CEP.TRC(SCCGWA, BPB2030_AWA_2030.EFF_DT);
        B010_CHECK_INPUT();
        B020_QUERY_IBNAME_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_IBNAME_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIBNA);
        BPCSIBAC.FUNC = 'I';
        BPCSIBAC.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSIBAC();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSIBAC.CCY = BPB2030_AWA_2030.CCY;
        BPCSIBAC.SWIFT = BPB2030_AWA_2030.SWIFT;
        BPCSIBAC.EFF_DATE = BPB2030_AWA_2030.EFF_DT;
    }
    public void S000_CALL_BPZSIBAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_IBAC_MT;
        SCCCALL.COMMAREA_PTR = BPCSIBAC;
        SCCCALL.ERR_FLDNO = BPB2030_AWA_2030.CCY_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
