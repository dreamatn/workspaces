package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZTTQPM {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTTQPM";
    String K_TBL_TQPM = "BPTTQP  ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQP BPRTQPX = new BPRTQP();
    int WS_GWA_AC_DATE = 0;
    SCCGWA SCCGWA;
    BPCTTQPM BPCTTQPM;
    BPRTQP BPRTQQ;
    public void MP(SCCGWA SCCGWA, BPCTTQPM BPCTTQPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTTQPM = BPCTTQPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTTQPM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTQQ = (BPRTQP) BPCTTQPM.POINTER;
        IBS.init(SCCGWA, BPRTQP);
        IBS.CLONE(SCCGWA, BPRTQQ, BPRTQP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTTQPM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTQPM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTQPM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTQPM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTQPM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTTQPM.INFO.FUNC == 'E') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTTQPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTQP, BPRTQQ);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTQP();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTTQPM.QUERY_OPTION.DBL_CHK_FLG == '1') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTQP.KEY);
