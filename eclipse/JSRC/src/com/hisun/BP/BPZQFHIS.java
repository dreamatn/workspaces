package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZQFHIS {
    boolean pgmRtn = false;
    String CPN_R_INQ_FHIST = "BP-R-INQ-FHIST";
    int WS_CNT = 0;
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFHISQ BPCFHISQ;
    public void MP(SCCGWA SCCGWA, BPCFHISQ BPCFHISQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFHISQ = BPCFHISQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQFHIS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCIFHIS);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCFHISQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQUIRE_PROCESS();
        if (pgmRtn) return;
        B030_OUTPUT_PROCESS();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFHISQ.INPUT.AC_DT);
        CEP.TRC(SCCGWA, BPCFHISQ.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCFHISQ.INPUT.JRN_SEQ);
        if (BPCFHISQ.INPUT.AC_DT == 0 
            || BPCFHISQ.INPUT.JRNNO == 0 
            || BPCFHISQ.INPUT.JRN_SEQ == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFHISQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCIFHIS);
        BPRFHIST.KEY.AC_DT = BPCFHISQ.INPUT.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCFHISQ.INPUT.JRNNO;
        BPRFHIST.KEY.JRN_SEQ = BPCFHISQ.INPUT.JRN_SEQ;
        BPCIFHIS.INPUT.JRNNO = BPCFHISQ.INPUT.JRNNO;
        BPCIFHIS.INPUT.FUNC = '5';
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        CEP.TRC(SCCGWA, BPRFHIST);
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        BPCFHISQ.OUTPUT.VCHNO = BPRFHIST.VCHNO;
        BPCFHISQ.OUTPUT.AC = BPRFHIST.KEY.AC;
        BPCFHISQ.OUTPUT.REF_NO = BPRFHIST.REF_NO;
        BPCFHISQ.OUTPUT.TX_TOOL = BPRFHIST.TX_TOOL;
        BPCFHISQ.OUTPUT.RLT_AC = BPRFHIST.RLT_AC;
        BPCFHISQ.OUTPUT.RLT_REF_NO = BPRFHIST.RLT_REF_NO;
        BPCFHISQ.OUTPUT.APP_MMO = BPRFHIST.APP_MMO;
        BPCFHISQ.OUTPUT.TX_CD = BPRFHIST.TX_CD;
        BPCFHISQ.OUTPUT.TX_MMO = BPRFHIST.TX_MMO;
        BPCFHISQ.OUTPUT.TX_STS = BPRFHIST.TX_STS;
        BPCFHISQ.OUTPUT.TX_DT = BPRFHIST.TX_DT;
        BPCFHISQ.OUTPUT.TX_TM = BPRFHIST.TX_TM;
        BPCFHISQ.OUTPUT.TX_VAL_DT = BPRFHIST.TX_VAL_DT;
        BPCFHISQ.OUTPUT.TX_REV_DT = BPRFHIST.TX_REV_DT;
        BPCFHISQ.OUTPUT.PRINT_FLG = BPRFHIST.PRINT_FLG;
        BPCFHISQ.OUTPUT.TX_CCY = BPRFHIST.TX_CCY;
        BPCFHISQ.OUTPUT.TX_AMT = BPRFHIST.TX_AMT;
        BPCFHISQ.OUTPUT.SUMUP_FLG = BPRFHIST.SUMUP_FLG;
        BPCFHISQ.OUTPUT.DRCRFLG = BPRFHIST.DRCRFLG;
        BPCFHISQ.OUTPUT.PROD_CD = BPRFHIST.PROD_CD;
        BPCFHISQ.OUTPUT.CI_NO = BPRFHIST.CI_NO;
        BPCFHISQ.OUTPUT.TX_BR = BPRFHIST.TX_BR;
        BPCFHISQ.OUTPUT.TX_DP = BPRFHIST.TX_DP;
