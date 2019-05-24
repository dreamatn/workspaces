package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZTBFVH {
    boolean pgmRtn = false;
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCTBFVH BPCTBFVH;
    BPRVCHT BPRVCHTR;
    public void MP(SCCGWA SCCGWA, BPCTBFVH BPCTBFVH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTBFVH = BPCTBFVH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTBFVH return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVCHTR = (BPRVCHT) BPCTBFVH.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTBFVH.INFO.FUNC == 'O') {
            S000_OPEN_FILE();
            if (pgmRtn) return;
        }
