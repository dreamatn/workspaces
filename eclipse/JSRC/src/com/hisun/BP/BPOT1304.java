package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1304 {
    int JIBS_tmp_int;
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
        CEP.TRC(SCCGWA, "BPOT1304 return!");
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
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_TYPE);
        if (BPB1310_AWA_1310.CI_NO == null) BPB1310_AWA_1310.CI_NO = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPB1310_AWA_1310.CI_NO += " ";
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NO.substring(0, 2));
        if (BPB1310_AWA_1310.CI_NO == null) BPB1310_AWA_1310.CI_NO = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPB1310_AWA_1310.CI_NO += " ";
        if (BPB1310_AWA_1310.CI_NO == null) BPB1310_AWA_1310.CI_NO = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPB1310_AWA_1310.CI_NO += " ";
        if ((BPB1310_AWA_1310.CI_TYPE == 'P' 
            && !BPB1310_AWA_1310.CI_NO.substring(0, 2).equalsIgnoreCase("91")) 
            || (BPB1310_AWA_1310.CI_TYPE == 'C' 
            && !BPB1310_AWA_1310.CI_NO.substring(0, 2).equalsIgnoreCase("96"))) {
            CEP.TRC(SCCGWA, "11111111111");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CREATE_SEQUENCE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.FUNC_CODE = 'U';
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        BPCSHSEQ.CI_NO = BPB1310_AWA_1310.CI_NO;
        BPCSHSEQ.CI_TYPE = BPB1310_AWA_1310.CI_TYPE;
        BPCSHSEQ.CI_NAME = BPB1310_AWA_1310.CI_NAME;
        BPCSHSEQ.AC_OFFICER = BPB1310_AWA_1310.AC_OFC;
        BPCSHSEQ.AC_ACCT = BPB1310_AWA_1310.ACCT;
        BPCSHSEQ.REMARK = BPB1310_AWA_1310.REMARK;
        CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSHSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_NAME);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_ACCT);
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
