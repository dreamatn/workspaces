package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1313 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_INT = 0;
    long WS_SEQ_NO_TMP = 0;
    int WS_CODE_INT = 0;
    BPOT1313_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT1313_WS_OUTPUT_DATA();
    char WS_CTL_LEN = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSHSEQ BPCSHSEQ = new BPCSHSEQ();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1313 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B040_DEL_RSVD_REC_PROC();
    }
    public void B040_DEL_RSVD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        if (BPB1310_AWA_1310.ITMS[1-1].ACNO == null) BPB1310_AWA_1310.ITMS[1-1].ACNO = "";
        JIBS_tmp_int = BPB1310_AWA_1310.ITMS[1-1].ACNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPB1310_AWA_1310.ITMS[1-1].ACNO += " ";
        BPCSHSEQ.CI_NO = BPB1310_AWA_1310.ITMS[1-1].ACNO.substring(0, 8);
        BPCSHSEQ.AC_NO = BPB1310_AWA_1310.ITMS[1-1].ACNO;
        BPCSHSEQ.USED_FLAG = 'N';
        BPCSHSEQ.FUNC_CODE = 'D';
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYPE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.SEQ_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.ITMS[1-1].ACNO);
        CEP.TRC(SCCGWA, BPCSHSEQ.USED_FLAG);
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_NO);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.AC_OFC);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.REMARK);
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
