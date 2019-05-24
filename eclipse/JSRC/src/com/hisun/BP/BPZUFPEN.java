package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZUFPEN {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_FPEN = "BP-F-Z-Q-B-FPEN";
    String WS_ERR_MSG = " ";
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCZFPEN BPCZFPEN = new BPCZFPEN();
    BPVFPEN BPVFPEN = new BPVFPEN();
    BPVFPEN BPVFPENO = new BPVFPEN();
    SCCGWA SCCGWA;
    BPCFPEN BPCUPEN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFPEN BPCUPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUPEN = BPCUPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUFPEN return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFPEN);
        IBS.init(SCCGWA, BPVFPENO);
        IBS.init(SCCGWA, BPCZFPEN);
        IBS.init(SCCGWA, BPCUPEN.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUPEN.FUNC);
        if (BPCUPEN.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUPEN.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUPEN.FUNC == 'U') {
            B030_01_COMPARE_DATA_PROCESS();
            if (pgmRtn) return;
            B030_UPDATE_DATA_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUPEN.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUPEN.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFPEN);
        CEP.TRC(SCCGWA, BPCUPEN.KEY);
