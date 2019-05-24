package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZRRULB {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRRULE";
    String K_TBL_RULE = "BPTRULE ";
    char WS_TBL_RULE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRULE BPRRULE = new BPRRULE();
    SCCGWA SCCGWA;
    BPCRRULB BPCRRULB;
    BPCRULA BPCRULA;
    public void MP(SCCGWA SCCGWA, BPCRRULB BPCRRULB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRULB = BPCRRULB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRRULB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRULA = (BPCRULA) BPCRRULB.INFO.POINTER;
