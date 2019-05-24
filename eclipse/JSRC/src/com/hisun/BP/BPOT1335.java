package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1335 {
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
        CEP.TRC(SCCGWA, "BPOT1335 return!");
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
        B020_MODIFY_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.FUNC_CODE = 'U';
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        BPCSHSEQ.AC_NO = BPB1310_AWA_1310.AC_NO;
        BPCSHSEQ.AC_OFFICER = BPB1310_AWA_1310.AC_OFC;
        BPCSHSEQ.REMARK = BPB1310_AWA_1310.REMARK;
        CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSHSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPCSHSEQ.REMARK);
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
