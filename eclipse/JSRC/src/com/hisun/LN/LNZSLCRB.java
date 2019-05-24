package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class LNZSLCRB {
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    short K_PROD_CLS_DK = 02;
    int K_DUE_DT_MAX = 99991231;
    char K_REPY_MTH_INST = '4';
    String K_PPMQ_PROD_CLS_ADV = "P015";
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCSWLAD LNCSLNCR = new LNCSWLAD();
    LNCSSETL LNCSSETL = new LNCSSETL();
    LNCICRCM LNCICRCM = new LNCICRCM();
    SCCGWA SCCGWA;
    LNCSWLAD LNCSLCRB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, LNCSWLAD LNCSLCRB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLCRB = LNCSLCRB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLCRB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSLCRB.RC.RC_APP = "LN";
        LNCSLCRB.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCSLCRB.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCSLCRB.COMM_DATA.PROD_CD);
        CEP.TRC(SCCGWA, LNCSLCRB.COMM_DATA.BOOK_BR);
        CEP.TRC(SCCGWA, LNCSLCRB.COMM_DATA.CCY);
        CEP.TRC(SCCGWA, LNCSLCRB.COMM_DATA.PRINCIPAL);
        CEP.TRC(SCCGWA, LNCSLCRB.COMM_DATA.REPY_MTH);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSLCRB.COMM_DATA.CTA_NO.trim().length() > 0) {
            B000_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B000_CHECK();
            if (pgmRtn) return;
            B000_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        B110_CHECK_PROD_DATA_PROC();
        if (pgmRtn) return;
        if (LNCSLCRB.COMM_DATA.DUE_DT == 0) {
            if (LNCSLCRB.COMM_DATA.REPY_MTH == K_REPY_MTH_INST) {
