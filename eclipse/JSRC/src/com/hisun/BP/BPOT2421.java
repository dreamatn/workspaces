package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2421 {
    String CPN_S_QUERY_EX_RATE = "BP-S-QUERY-EX-RATE  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSQCRT BPCSQCRT = new BPCSQCRT();
    SCCGWA SCCGWA;
    BPB2420_AWA_2420 BPB2420_AWA_2420;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2421 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2420_AWA_2420>");
        BPB2420_AWA_2420 = (BPB2420_AWA_2420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_EX_RATE_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPB2420_AWA_2420.F_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BE_LOCAL_CCY;
            WS_FLD_NO = BPB2420_AWA_2420.F_CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_EX_RATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSQCRT);
        BPCSQCRT.CCY = BPB2420_AWA_2420.F_CCY;
        BPCSQCRT.AMT = BPB2420_AWA_2420.F_AMT;
        BPCSQCRT.EX_RATE = BPB2420_AWA_2420.EX_RATE;
        BPCSQCRT.LOC_CCY = BPB2420_AWA_2420.LOC_CCY;
        S000_CALL_BPZSQCRT();
    }
    public void S000_CALL_BPZSQCRT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_QUERY_EX_RATE;
        SCCCALL.COMMAREA_PTR = BPCSQCRT;
        SCCCALL.ERR_FLDNO = BPB2420_AWA_2420.F_CCY_NO;
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
