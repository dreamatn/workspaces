package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSASEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZSASEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSASEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCX01 BPCX01 = new BPCX01();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCUASEQ BPCUASEQ = new BPCUASEQ();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSASEQ BPCSASEQ;
    public void MP(SCCGWA SCCGWA, BPCSASEQ BPCSASEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSASEQ = BPCSASEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSASEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_CODE();
        if (pgmRtn) return;
        B005_ADD_PTR_PROC();
        if (pgmRtn) return;
        B014_OUTPUT_FOR_PRINT();
        if (pgmRtn) return;
    }
    public void B001_CHECK_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = BPCSASEQ.SEQ_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCSASEQ.SEQ_CODE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
    }
    public void B005_ADD_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUASEQ);
        BPCUASEQ.SEQ_NO = 0;
        BPCUASEQ.OLD_SEQ_NO = 0;
        BPCUASEQ.SEQ_TYPE = BPCSASEQ.SEQ_TYPE;
        BPCUASEQ.SEQ_CODE = BPCSASEQ.SEQ_CODE;
        BPCUASEQ.SEQ_DESC = BPCSASEQ.SEQ_DESC;
        BPCUASEQ.FIRST_NUM = BPCSASEQ.FIRST_NUM;
        BPCUASEQ.INIT_ZERO = BPCSASEQ.INIT_ZERO;
        BPCUASEQ.SKIP_FLG = BPCSASEQ.SKIP_FLG;
        BPCUASEQ.OBL_FLG = BPCSASEQ.OBL_FLG;
        BPCUASEQ.VIP_FLG = BPCSASEQ.VIP_FLG;
        BPCUASEQ.VAL_DATE = BPCSASEQ.VAL_DATE;
        BPCUASEQ.AC_DATE = BPCSASEQ.AC_DATE;
        BPCUASEQ.MAX_SEQ_NO = BPCSASEQ.MAX_SEQ_NO;
        if (BPCSASEQ.MAX_SEQ_NO == 0) {
            BPCUASEQ.MAX_SEQ_NO = 999999999999999;
        }
        BPCUASEQ.MAX_FLG = BPCSASEQ.MAX_FLG;
        BPCUASEQ.WARN_SEQ_NO = BPCSASEQ.WARN_SEQ_NO;
        if (BPCSASEQ.WARN_SEQ_NO == 0) {
            BPCUASEQ.WARN_SEQ_NO = 999999999999999;
        }
        BPCUASEQ.BR = BPCSASEQ.BR;
        BPCUASEQ.DP = BPCSASEQ.DP;
        BPCUASEQ.TLR = BPCSASEQ.TLR;
        BPCUASEQ.AC_DATE = BPCSASEQ.AC_DATE;
        BPCUASEQ.STEP_NUM = BPCSASEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCSASEQ.STEP_NUM);
        S000_CALL_BPZUASEQ();
        if (pgmRtn) return;
    }
    public void B014_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCX01);
        IBS.init(SCCGWA, SCCFMT);
        BPCX01.TYPE = BPCSASEQ.SEQ_TYPE;
        BPCX01.NAME = BPCSASEQ.SEQ_CODE;
        BPCX01.DESC = BPCSASEQ.SEQ_DESC;
        BPCX01.SEQ_NO = BPCSASEQ.SEQ_NO;
        BPCX01.FIRST_NUM = BPCSASEQ.FIRST_NUM;
        BPCX01.INIT_ZERO = BPCSASEQ.INIT_ZERO;
        BPCX01.SKIP_FLG = BPCSASEQ.SKIP_FLG;
        BPCX01.OBL_FLG = BPCSASEQ.OBL_FLG;
        BPCX01.MAX_SEQ_NO = BPCSASEQ.MAX_SEQ_NO;
        BPCX01.MAX_FLG = BPCSASEQ.MAX_FLG;
        BPCX01.WARN_SEQ_NO = BPCSASEQ.WARN_SEQ_NO;
        BPCX01.LAST_UPD_TLR = BPCSASEQ.LAST_UPD_TLR;
        BPCX01.LAST_UPD_DATE = BPCSASEQ.LAST_UPD_DATE;
        BPCX01.STEP_NUM = BPCSASEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCX01.STEP_NUM);
        CEP.TRC(SCCGWA, BPCX01.SEQ_NO);
        CEP.TRC(SCCGWA, BPCX01.FIRST_NUM);
        CEP.TRC(SCCGWA, BPCX01.MAX_SEQ_NO);
        SCCFMT.FMTID = "BP002";
        SCCFMT.DATA_PTR = BPCX01;
        SCCFMT.DATA_LEN = 220;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUASEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-SEQ", BPCUASEQ);
        if (BPCUASEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUASEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSASEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSASEQ.RC);
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
