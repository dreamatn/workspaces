package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFSPEN {
    boolean pgmRtn = false;
    String CPN_F_U_MAIN_FPEN = "BP-F-U-B-Q-FPEN";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPENO BPCFPENO = new BPCFPENO();
    SCCGWA SCCGWA;
    BPCFPEN BPCSPEN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFPEN BPCSPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPEN = BPCSPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSPEN return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        BPCSPEN.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCSPEN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSPEN.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPEN.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPEN.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPEN.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPEN.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZUFPEN();
        if (pgmRtn) return;
        T000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZUFPEN();
        if (pgmRtn) return;
        T000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZUFPEN();
        if (pgmRtn) return;
        T000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "MODIFY CALL BPZUFPEN END.");
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_BPZUFPEN();
        if (pgmRtn) return;
        T000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        BPCSPEN.OPT = 'S';
        CEP.TRC(SCCGWA, "START BROWSE");
        S000_CALL_BPZUFPEN();
        if (pgmRtn) return;
