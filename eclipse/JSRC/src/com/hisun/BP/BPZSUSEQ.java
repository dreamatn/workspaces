package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSUSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZSUSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSUSEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCX01 BPCX01 = new BPCX01();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCUUSEQ BPCUUSEQ = new BPCUUSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSUSEQ BPCSUSEQ;
    public void MP(SCCGWA SCCGWA, BPCSUSEQ BPCSUSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSUSEQ = BPCSUSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSUSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B003_UPD_PTR_PROC();
        if (pgmRtn) return;
        B014_OUTPUT_FOR_PRINT();
        if (pgmRtn) return;
    }
    public void B003_UPD_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUUSEQ);
        BPCUUSEQ.SEQ_TYPE = BPCSUSEQ.SEQ_TYPE;
        BPCUUSEQ.SEQ_CODE = BPCSUSEQ.SEQ_CODE;
        BPCUUSEQ.SEQ_DESC = BPCSUSEQ.SEQ_DESC;
        BPCUUSEQ.FIRST_NUM = BPCSUSEQ.FIRST_NUM;
        BPCUUSEQ.INIT_ZERO = BPCSUSEQ.INIT_ZERO;
        BPCUUSEQ.SKIP_FLG = BPCSUSEQ.SKIP_FLG;
        BPCUUSEQ.OBL_FLG = BPCSUSEQ.OBL_FLG;
        BPCUUSEQ.VIP_FLG = BPCSUSEQ.VIP_FLG;
        BPCUUSEQ.VAL_DATE = BPCSUSEQ.VAL_DATE;
        BPCUUSEQ.MAX_SEQ_NO = BPCSUSEQ.MAX_SEQ_NO;
        BPCUUSEQ.MAX_FLG = BPCSUSEQ.MAX_FLG;
        BPCUUSEQ.WARN_SEQ_NO = BPCSUSEQ.WARN_SEQ_NO;
        BPCUUSEQ.BR = BPCSUSEQ.BR;
        BPCUUSEQ.DP = BPCSUSEQ.DP;
        CEP.TRC(SCCGWA, BPCSUSEQ.STEP_NUM);
        BPCUUSEQ.STEP_NUM = BPCSUSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCUUSEQ.STEP_NUM);
        S000_CALL_BPZUUSEQ();
        if (pgmRtn) return;
    }
    public void B014_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCX01);
        IBS.init(SCCGWA, SCCFMT);
        BPCX01.TYPE = BPCUUSEQ.SEQ_TYPE;
        BPCX01.NAME = BPCUUSEQ.SEQ_CODE;
        BPCX01.DESC = BPCUUSEQ.SEQ_DESC;
        BPCX01.SEQ_NO = BPCUUSEQ.SEQ_NO;
        BPCX01.FIRST_NUM = BPCUUSEQ.FIRST_NUM;
        BPCX01.INIT_ZERO = BPCUUSEQ.INIT_ZERO;
        BPCX01.SKIP_FLG = BPCUUSEQ.SKIP_FLG;
        BPCX01.OBL_FLG = BPCUUSEQ.OBL_FLG;
        BPCX01.MAX_SEQ_NO = BPCUUSEQ.MAX_SEQ_NO;
        BPCX01.MAX_FLG = BPCUUSEQ.MAX_FLG;
        BPCX01.WARN_SEQ_NO = BPCUUSEQ.WARN_SEQ_NO;
        BPCX01.LAST_UPD_TLR = BPCUUSEQ.LAST_UPD_TLR;
        BPCX01.LAST_UPD_DATE = BPCUUSEQ.LAST_UPD_DATE;
        BPCX01.STEP_NUM = BPCUUSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCUUSEQ.STEP_NUM);
        CEP.TRC(SCCGWA, BPCX01.STEP_NUM);
        CEP.TRC(SCCGWA, BPCX01.SEQ_NO);
        CEP.TRC(SCCGWA, BPCX01.FIRST_NUM);
        CEP.TRC(SCCGWA, BPCX01.MAX_SEQ_NO);
        SCCFMT.FMTID = "BP002";
        SCCFMT.DATA_PTR = BPCX01;
        SCCFMT.DATA_LEN = 220;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUUSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-UPD-SEQ", BPCUUSEQ);
        if (BPCUUSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUUSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSUSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
