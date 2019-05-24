package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;

public class LNZPAYSM {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    LNZPAYSM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZPAYSM_WS_TEMP_VARIABLE();
    char WS_P_FLG = ' ';
    char WS_I_FLG = ' ';
    char WS_O_FLG = ' ';
    char WS_L_FLG = ' ';
    char WS_F_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCRPSQ LNCRPSQ = new LNCRPSQ();
    LNRPAYSQ LNRPAYSQ = new LNRPAYSQ();
    SCCGWA SCCGWA;
    LNCPAYSM LNCPAYSM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCPAYSM LNCPAYSM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPAYSM = LNCPAYSM;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "LNZPAYSM:A000-INIT-PROCESS START!... ");
        CEP.TRC(SCCGWA, LNCPAYSM);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPAYSM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, LNRPAYSQ);
        LNCRPSQ.RC.RC_MMO = "LN";
        LNCRPSQ.RC.RC_CODE = 0;
