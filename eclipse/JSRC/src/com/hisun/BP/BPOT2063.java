package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2063 {
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_BRE_LBI = "BP-S-BRE-LBI";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    BPOT2063_WS_FMT_DATA WS_FMT_DATA = new BPOT2063_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLBI BPCSBLBI = new BPCSBLBI();
    SCCGWA SCCGWA;
    BPB2060_AWA_2060 BPB2060_AWA_2060;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2063 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2060_AWA_2060>");
        BPB2060_AWA_2060 = (BPB2060_AWA_2060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_QUIRE_BRINF();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_QUIRE_BRINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLBI);
        BPCSBLBI.FUNC = 'B';
        BPCSBLBI.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBLBI.BR = BPB2060_AWA_2060.BR;
        BPCSBLBI.CCY = BPB2060_AWA_2060.CCY;
        S000_CALL_BPZSBLBI();
    }
    public void S000_CALL_BPZSBLBI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BRE_LBI;
        SCCCALL.COMMAREA_PTR = BPCSBLBI;
        SCCCALL.ERR_FLDNO = BPB2060_AWA_2060.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSBLBI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBLBI.RC);
            WS_FLD_NO = BPB2060_AWA_2060.BR_NO;
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
