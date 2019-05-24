package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZQCHEK {
    boolean pgmRtn = false;
    String K_PARM_TYPE = "HCHKC";
    String K_PGM_NAME = "BPZQCHEK";
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCQCHEK BPCQCHEK;
    public void MP(SCCGWA SCCGWA, BPCQCHEK BPCQCHEK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQCHEK = BPCQCHEK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQCHEK return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCQCHEK.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_CHECK_CODE();
        if (pgmRtn) return;
    }
    public void B010_QUERY_CHECK_CODE() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_PARM_TYPE;
        BPRPRMT.KEY.CD = BPCQCHEK.CHECK_CODE;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCHEK.DATA);
