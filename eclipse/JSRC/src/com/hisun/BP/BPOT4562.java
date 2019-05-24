package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4562 {
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_RGNC_MT = "BP-S-RGNC-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4562_WS_TEMP_KEY WS_TEMP_KEY = new BPOT4562_WS_TEMP_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSRGCM BPCSRGCM = new BPCSRGCM();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4562 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4580_AWA_4580>");
        BPB4580_AWA_4580 = (BPB4580_AWA_4580) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_RGNC_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.BNK_CD);
        IBS.CPY2CLS(SCCGWA, BPB4580_AWA_4580.BNK_CD, WS_TEMP_KEY);
        CEP.TRC(SCCGWA, WS_TEMP_KEY.WS_RGN_BNK);
        CEP.TRC(SCCGWA, WS_TEMP_KEY.WS_RGN_TYPE);
        BPB4580_AWA_4580.BNK = WS_TEMP_KEY.WS_RGN_BNK;
        BPB4580_AWA_4580.RGN_TYPE = WS_TEMP_KEY.WS_RGN_TYPE;
        BPB4580_AWA_4580.RGN_SEQ = WS_TEMP_KEY.WS_RGN_SEQ;
    }
    public void B020_QUERY_RGNC_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRGCM);
        BPCSRGCM.FUNC = 'I';
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.BNK);
        BPCSRGCM.BNK = BPB4580_AWA_4580.BNK;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.RGN_TYPE);
        BPCSRGCM.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCSRGCM.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        BPCSRGCM.FUNC_CODE = BPB4580_AWA_4580.I_FUNC;
        BPCSRGCM.EFF_DATE = BPB4580_AWA_4580.EFF_DT;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.EFF_DT);
        BPCSRGCM.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSRGCM();
    }
    public void S000_CALL_BPZSRGCM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_RGNC_MT;
        SCCCALL.COMMAREA_PTR = BPCSRGCM;
        SCCCALL.ERR_FLDNO = BPB4580_AWA_4580.BNK_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}