package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZSBSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSBSEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCUBSEQ BPCUBSEQ = new BPCUBSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSBSEQ BPCSBSEQ;
    public void MP(SCCGWA SCCGWA, BPCSBSEQ BPCSBSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBSEQ = BPCSBSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B007_BRW_PTR_PROC();
        if (pgmRtn) return;
    }
    public void B007_BRW_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBSEQ);
        BPCUBSEQ.SEQ_TYPE = BPCSBSEQ.SEQ_TYPE;
        BPCUBSEQ.SEQ_CODE = BPCSBSEQ.SEQ_CODE;
        S000_CALL_BPZUBSEQ();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUBSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BRW-SEQ", BPCUBSEQ);
        if (BPCUBSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUBSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSBSEQ.RC);
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
