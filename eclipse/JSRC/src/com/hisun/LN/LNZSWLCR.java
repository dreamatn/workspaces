package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class LNZSWLCR {
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    String K_HIS_REMARKS = "ACCRUAL DRAWDOWN CONTRACT CREATE";
    String K_CPY_LNCHWLCR = "LNCHWLCR";
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCSWLAD LNCSLCRB = new LNCSWLAD();
    LNCIPART LNCIPART = new LNCIPART();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNCSWLAD LNCSWLCR;
    public void MP(SCCGWA SCCGWA, LNCSWLAD LNCSWLCR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSWLCR = LNCSWLCR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSWLCR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSWLCR.RC.RC_APP = "LN";
        LNCSWLCR.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.ACCRUAL_TYPE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "POCPOCPOCPOC");
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.CCY);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.W_I_FLG);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.W_I_PERU);
        CEP.TRC(SCCGWA, LNCSWLCR.COMM_DATA.W_I_PERD);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
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
        if (LNCSWLCR.COMM_DATA.BOOK_BR == 0) {
