package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1305 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSHSEQ BPCSHSEQ = new BPCSHSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1305 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1310_AWA_1310.TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB1310_AWA_1310.TYPE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1310_AWA_1310.CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB1310_AWA_1310.CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1310_AWA_1310.SEQ_NO == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            WS_FLD_NO = BPB1310_AWA_1310.SEQ_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.FUNC_CODE = 'I';
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSHSEQ.SEQ_NO);
        S00_CALL_BPZSHSEQ();
    }
    public void S00_CALL_BPZSHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-HSEQ-MAINT", BPCSHSEQ);
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
