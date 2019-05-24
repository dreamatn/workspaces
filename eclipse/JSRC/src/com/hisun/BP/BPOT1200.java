package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPOT1200 {
    boolean pgmRtn = false;
    String CPN_S_INQ_HIST = "BP-S-INQ-HIST";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT1200_WS_TS_QUEUE WS_TS_QUEUE = new BPOT1200_WS_TS_QUEUE();
    char WS_FHIST_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRFFHIS BPRFFHIS = new BPRFFHIS();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    SCCGWA SCCGWA;
    BPC1200 BPC1200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1200 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1200 = new BPC1200();
        IBS.init(SCCGWA, BPC1200);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1200);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_HIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC1200.TX_CODE);
        CEP.TRC(SCCGWA, BPC1200.CHG_AC);
        CEP.TRC(SCCGWA, BPC1200.AC_DT);
        CEP.TRC(SCCGWA, BPC1200.JRN_NO);
        if (BPC1200.AC_DT == 0) {
            BPC1200.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPC1200.TX_CODE.trim().length() == 0 
            && BPC1200.CHG_AC.trim().length() == 0 
            && BPC1200.JRN_NO == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND;
