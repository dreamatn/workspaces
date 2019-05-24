package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class LNZSLMDB {
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCSWLMD LNCSLNMD = new LNCSWLMD();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCICONT LNCICONT = new LNCICONT();
    LNCICRCM LNCICRCM = new LNCICRCM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    SCCGWA SCCGWA;
    LNCSWLMD LNCSLMDB;
    public void MP(SCCGWA SCCGWA, LNCSWLMD LNCSLMDB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLMDB = LNCSLMDB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLMDB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSLMDB.RC.RC_APP = "LN";
        LNCSLMDB.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.FACILITY_NO);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.TRCHCMMT_NO);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.PROD_CD);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.BOOK_BR);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.DOMI_BR);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.CCY);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.PRINICIPAL);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.BAL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_CONT_STATUS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCSLMDB.COMM_DATA.DUE_DT);
        if (LNCSLMDB.COMM_DATA.DUE_DT != 0 
            && LNCSLMDB.COMM_DATA.DUE_DT <= LNCSLMDB.COMM_DATA.VAL_DT) {
