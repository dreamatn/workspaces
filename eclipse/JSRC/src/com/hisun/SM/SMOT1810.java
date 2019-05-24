package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP30;
import com.hisun.BP.BPRIPT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCWOUT;
import com.hisun.SC.SCRPRMT;

public class SMOT1810 {
    boolean pgmRtn = false;
    SMOT1810_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1810_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    SMOT1810_WS_TS_CNTL WS_TS_CNTL = new SMOT1810_WS_TS_CNTL();
    SMOT1810_WS_SPE_OUT WS_SPE_OUT = new SMOT1810_WS_SPE_OUT();
    SMOT1810_WS_REC_OUT WS_REC_OUT = new SMOT1810_WS_REC_OUT();
    BPCXP30 BPCXP30 = new BPCXP30();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRIPT BPRIPTD = new BPRIPT();
    SCCGWA SCCGWA;
    SMB1800_AWA_1800 SMB1800_AWA_1800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS_TSQOUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1810 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1800_AWA_1800>");
        SMB1800_AWA_1800 = (SMB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "BP";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS_TSQOUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB1800_AWA_1800);
        if (SMB1800_AWA_1800.FUNC != 'A') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
