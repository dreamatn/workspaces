package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4720 {
    String K_CMP_DATE_CHARACTER = "BP-S-DATE-CHARACTER";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_TYPE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIQDN BPCSIQDN = new BPCSIQDN();
    SCCGWA SCCGWA;
    BPB4700_AWA_4700 BPB4700_AWA_4700;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4720 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4700_AWA_4700>");
        BPB4700_AWA_4700 = (BPB4700_AWA_4700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_TYPE = BPB4700_AWA_4700.TYPE;
        if ((WS_TYPE != 'A' 
            && WS_TYPE != 'B' 
            && WS_TYPE != 'I')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TYPE_INPUT_ERROR;
            WS_FLD_NO = BPB4700_AWA_4700.TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIQDN);
        BPCSIQDN.TYPE = BPB4700_AWA_4700.TYPE;
        BPCSIQDN.BR = BPB4700_AWA_4700.BRNO;
        BPCSIQDN.REG_CD = BPB4700_AWA_4700.REG_CD;
        BPCSIQDN.STR_DATE = BPB4700_AWA_4700.STR_DATE;
        BPCSIQDN.END_DATE = BPB4700_AWA_4700.END_DATE;
        BPCSIQDN.FUNC = 'B';
        BPCSIQDN.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSIQDN();
    }
    public void S000_CALL_BPZSIQDN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_DATE_CHARACTER;
        SCCCALL.COMMAREA_PTR = BPCSIQDN;
        SCCCALL.ERR_FLDNO = BPB4700_AWA_4700.TYPE_NO;
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
