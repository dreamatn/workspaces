package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class LNZSWLDD {
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    String K_HIS_REMARKS = "ACCRUAL DRAWDOWN CONTRACT DRAWDOWN";
    long K_DISP_ZERO = 0;
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCSDDPT LNCSDDPT = new LNCSDDPT();
    LNCSWLAD LNCSDDBR = new LNCSWLAD();
    LNCIPART LNCIPART = new LNCIPART();
    SCCGWA SCCGWA;
    LNCSWLAD LNCSWLDD;
    public void MP(SCCGWA SCCGWA, LNCSWLAD LNCSWLDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSWLDD = LNCSWLDD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSWLDD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSWLDD.RC.RC_APP = "LN";
        LNCSWLDD.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCSWLDD.COMM_DATA.CTA_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
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
        if (LNCSWLDD.COMM_DATA.CTA_NO.trim().length() == 0) {
