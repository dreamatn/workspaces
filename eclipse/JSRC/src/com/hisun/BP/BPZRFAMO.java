package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZRFAMO {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFAMO";
    String K_TBL_FARM = "BPTFAMO ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFAMO BPRFAMO = new BPRFAMO();
    SCCGWA SCCGWA;
    BPCRFAMO BPCRFAMO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFAMO BPRFAMO1;
    public void MP(SCCGWA SCCGWA, BPCRFAMO BPCRFAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFAMO = BPCRFAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFAMO return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
