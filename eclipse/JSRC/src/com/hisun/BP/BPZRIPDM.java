package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZRIPDM {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRIPDM";
    String K_TBL_IRPD = "BPTIRPD ";
    int WS_REC_LEN = 0;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_IRPD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRIRPD BPRIRPD = new BPRIRPD();
    SCCGWA SCCGWA;
    BPCRIPDM BPCRIPDM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRIRPD BPRLRPD;
    public void MP(SCCGWA SCCGWA, BPCRIPDM BPCRIPDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRIPDM = BPCRIPDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRIPDM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
