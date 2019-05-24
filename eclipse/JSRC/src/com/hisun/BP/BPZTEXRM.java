package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZTEXRM {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTEXRM";
    String K_TBL_EXRD = "BPTEXRD ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXRD BPREXRD = new BPREXRD();
    BPREXRD BPREXRX = new BPREXRD();
    SCCGWA SCCGWA;
    BPCTEXRM BPCTEXRM;
    BPREXRD BPREXRD1;
    public void MP(SCCGWA SCCGWA, BPCTEXRM BPCTEXRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTEXRM = BPCTEXRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEXRM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXRD1 = (BPREXRD) BPCTEXRM.POINTER;
        IBS.init(SCCGWA, BPREXRD);
        IBS.CLONE(SCCGWA, BPREXRD1, BPREXRD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HELPME");
        if (BPCTEXRM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXRM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXRM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXRM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXRM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTEXRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "4444");
        IBS.CLONE(SCCGWA, BPREXRD, BPREXRD1);
        CEP.TRC(SCCGWA, "5555");
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTEXRD();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTEXRM.QUERY_OPTION.DBL_CHK_FLG != 'Y') {
            T000_READ_BPTEXRD_UPD();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXRD.KEY);
