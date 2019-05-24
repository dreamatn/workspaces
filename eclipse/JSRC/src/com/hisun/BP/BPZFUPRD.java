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

public class BPZFUPRD {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_MAINTAIN_NHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    String WS_TXT = " ";
    char WS_DATACHG_FLG = ' ';
    short WS_FEE_NO = 0;
    BPZFUPRD_WS_MSK_VAL WS_MSK_VAL = new BPZFUPRD_WS_MSK_VAL();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFPRD BPVFPRD = new BPVFPRD();
    BPVFPRD BPVOPRD = new BPVFPRD();
    SCCGWA SCCGWA;
    BPCOFPRD BPCOUPRD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFPRD BPCOUPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUPRD = BPCOUPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUPRD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFPRD);
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPVFMSK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B090_CHECK_DATA();
        if (pgmRtn) return;
        if (BPCOUPRD.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUPRD.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUPRD.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUPRD.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUPRD.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFPRD);
