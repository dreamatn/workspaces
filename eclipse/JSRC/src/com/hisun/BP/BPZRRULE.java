package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZRRULE {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRRULE";
    String K_TBL_RULE = "BPTRULE ";
    int WS_REC_COUNT = 0;
    char WS_TBL_RULE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRULE BPRRULE = new BPRRULE();
    SCCGWA SCCGWA;
    BPCRRULE BPCRRULE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRULA BPCRULA;
    public void MP(SCCGWA SCCGWA, BPCRRULE BPCRRULE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRULE = BPCRRULE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "END   OF BPZRRULE...");
        CEP.TRC(SCCGWA, "BPZRRULE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRULA = (BPCRULA) BPCRRULE.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
