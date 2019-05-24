package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4502 {
    String K_OUTPUT_FMT = "BPX01";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4502_WS_TEMP_BANK WS_TEMP_BANK = new BPOT4502_WS_TEMP_BANK();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBNKM BPCSBNKM = new BPCSBNKM();
    SCCGWA SCCGWA;
    BPB4500_AWA_4500 BPB4500_AWA_4500;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4502 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4500_AWA_4500>");
        BPB4500_AWA_4500 = (BPB4500_AWA_4500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_BANK_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4500_AWA_4500.BNK_CD);
        IBS.CPY2CLS(SCCGWA, BPB4500_AWA_4500.BNK_CD, WS_TEMP_BANK);
        BPB4500_AWA_4500.BNK = WS_TEMP_BANK.WS_BANK_CODE;
    }
    public void B020_QUERY_BANK_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBNKM);
        BPCSBNKM.FUNC = 'I';
        BPCSBNKM.BNK = BPB4500_AWA_4500.BNK;
        BPCSBNKM.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBNKM.FUNC_CODE = BPB4500_AWA_4500.I_FUNC;
        BPCSBNKM.EFF_DT = BPB4500_AWA_4500.EFF_DT;
        S000_CALL_BPZSBNKM();
    }
    public void S000_CALL_BPZSBNKM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_BANK_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSBNKM;
        SCCCALL.ERR_FLDNO = BPB4500_AWA_4500.BNK_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
