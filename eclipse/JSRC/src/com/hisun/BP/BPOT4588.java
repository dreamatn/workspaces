package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4588 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_RGND_MAINTAIN = "BP-S-RGND-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSRGND BPCSRGND = new BPCSRGND();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4588 return!");
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
        B020_QUERY_RGND_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_RGND_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRGND);
        BPCSRGND.FUNC = 'I';
        BPCSRGND.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSRGND();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.I_FUNC);
        BPCSRGND.FUNC_CODE = BPB4580_AWA_4580.I_FUNC;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.BNK);
        BPCSRGND.BNK = BPB4580_AWA_4580.BNK;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.RGN_TYPE);
        BPCSRGND.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.RGN_SEQ);
        BPCSRGND.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.RGN_UNIT);
        BPCSRGND.UNIT = BPB4580_AWA_4580.RGN_UNIT;
        if (BPCSRGND.FUNC_CODE == 'A') {
            BPCSRGND.BNK = " ";
            BPCSRGND.RGN_SEQ = 0;
            CEP.TRC(SCCGWA, BPCSRGND.BNK);
        }
    }
    public void S000_CALL_BPZSRGND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_RGND_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSRGND;
        SCCCALL.ERR_FLDNO = BPB4580_AWA_4580.BNK_NO;
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
