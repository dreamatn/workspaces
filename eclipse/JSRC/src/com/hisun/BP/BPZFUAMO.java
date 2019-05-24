package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZFUAMO {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFAMO BPVFAMO = new BPVFAMO();
    BPVFAMO BPVHAMO = new BPVFAMO();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCOFAMO BPCOUAMO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFAMO BPCOUAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUAMO = BPCOUAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUAMO return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPVHAMO);
        IBS.init(SCCGWA, BPVFAMO);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUAMO.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAMO.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAMO.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAMO.FUNC == 'B') {
            B040_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAMO.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFAMO);
        BPVFAMO.KEY.AMORT_CODE = BPCOUAMO.KEY.AMORT_CODE;
        CEP.TRC(SCCGWA, BPVFAMO.KEY.AMORT_CODE);
        BPCFPARM.INFO.POINTER = BPVFAMO;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FAMO ";
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFAMO.KEY);
