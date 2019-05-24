package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMR;
import com.hisun.DD.DDCIMMST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSHZAC {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIX01";
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    CIZSHZAC_WS_ERR_MSG WS_ERR_MSG = new CIZSHZAC_WS_ERR_MSG();
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    char WS_DD_MST_FLG = ' ';
    char WS_CITACR_FLG = ' ';
    char WS_CITBAS_FLG = ' ';
    char WS_EXIST_HZAC_FLG = ' ';
    char WS_EXIST_HZAC_BR_FLG = ' ';
    char WS_HZAC_BR_FLG = ' ';
    char WS_INAC_BR_FLG = ' ';
    char WS_OCCU_FLG = ' ';
    CIZSHZAC_WS_AC_OUT WS_AC_OUT = new CIZSHZAC_WS_AC_OUT();
    char WS_EXIST_DD_V_STS_FLG = ' ';
    char WS_EXIST_V_FLG = ' ';
    char WS_READ_DD_FLG = ' ';
    char WS_IN_AC_V_STS_FLG = ' ';
    CIZSHZAC_WS_HZAC_LIST WS_HZAC_LIST = new CIZSHZAC_WS_HZAC_LIST();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRACR CIRACR = new CIRACR();
    CIRBAS CIRBAS = new CIRBAS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CIRAGENT CIRAGENT = new CIRAGENT();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICSHZAC CICSHZAC;
    public void MP(SCCGWA SCCGWA, CICSHZAC CICSHZAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSHZAC = CICSHZAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSHZAC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSHZAC.OPT == 'C') {
            B020_STARTBR_RECORD_CI_PROC();
            if (pgmRtn) return;
        } else if (CICSHZAC.OPT == 'A') {
            B030_STARTBR_RECORD_AC_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + CICSHZAC.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_STARTBR_RECORD_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRACR);
        CEP.TRC(SCCGWA, CICSHZAC.CI_NO);
        CEP.TRC(SCCGWA, CICSHZAC.ID_TYP);
        CEP.TRC(SCCGWA, CICSHZAC.ID_NO);
        CEP.TRC(SCCGWA, CICSHZAC.CI_NAME);
        if (CICSHZAC.CI_NO.trim().length() == 0) {
            CIRBAS.ID_TYPE = CICSHZAC.ID_TYP;
            CIRBAS.ID_NO = CICSHZAC.ID_NO;
