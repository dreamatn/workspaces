package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSDSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZSDSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSDSEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCX01 BPCX01 = new BPCX01();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCUDSEQ BPCUDSEQ = new BPCUDSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSDSEQ BPCSDSEQ;
    public void MP(SCCGWA SCCGWA, BPCSDSEQ BPCSDSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSDSEQ = BPCSDSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSDSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B006_DEL_PTR_PROC();
        if (pgmRtn) return;
        B014_OUTPUT_FOR_PRINT();
        if (pgmRtn) return;
    }
    public void B006_DEL_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUDSEQ);
        BPCUDSEQ.SEQ_TYPE = BPCSDSEQ.SEQ_TYPE;
        BPCUDSEQ.SEQ_CODE = BPCSDSEQ.SEQ_CODE;
        S000_CALL_BPZUDSEQ();
        if (pgmRtn) return;
    }
    public void B014_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCX01);
        IBS.init(SCCGWA, SCCFMT);
        BPCX01.TYPE = BPCUDSEQ.SEQ_TYPE;
        BPCX01.NAME = BPCUDSEQ.SEQ_CODE;
        BPCX01.DESC = BPCUDSEQ.SEQ_DESC;
        BPCX01.SEQ_NO = BPCUDSEQ.SEQ_NO;
        BPCX01.FIRST_NUM = BPCUDSEQ.FIRST_NUM;
        BPCX01.INIT_ZERO = BPCUDSEQ.INIT_ZERO;
        BPCX01.SKIP_FLG = BPCUDSEQ.SKIP_FLG;
        BPCX01.OBL_FLG = BPCUDSEQ.OBL_FLG;
        BPCX01.MAX_SEQ_NO = BPCUDSEQ.MAX_SEQ_NO;
        BPCX01.MAX_FLG = BPCUDSEQ.MAX_FLG;
        BPCX01.WARN_SEQ_NO = BPCUDSEQ.WARN_SEQ_NO;
        BPCX01.LAST_UPD_TLR = BPCUDSEQ.LAST_UPD_TLR;
        BPCX01.LAST_UPD_DATE = BPCUDSEQ.LAST_UPD_DATE;
        BPCX01.STEP_NUM = BPCUDSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCX01.STEP_NUM);
        CEP.TRC(SCCGWA, BPCX01.SEQ_NO);
        CEP.TRC(SCCGWA, BPCX01.FIRST_NUM);
        CEP.TRC(SCCGWA, BPCX01.MAX_SEQ_NO);
        SCCFMT.FMTID = "BP002";
        SCCFMT.DATA_PTR = BPCX01;
        SCCFMT.DATA_LEN = 220;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUDSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-DEL-SEQ", BPCUDSEQ);
        if (BPCUDSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUDSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSDSEQ.RC);
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
