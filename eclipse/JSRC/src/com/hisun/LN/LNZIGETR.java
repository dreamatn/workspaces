package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIGETR {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    short WS_TRANCHE_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    LNCIGETR LNCIGETR;
    public void MP(SCCGWA SCCGWA, LNCIGETR LNCIGETR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIGETR = LNCIGETR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIGETR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCIGETR.RC.RC_MMO = "LN";
        LNCIGETR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B030_GENERATE_TRCD_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGETR.INPUT_INFO.FACILITY_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_FACT_NO, LNCIGETR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GENERATE_TRCD_PROC() throws IOException,SQLException,Exception {
        LNCIGETR.OUTPOUT_INFO.TRANCHE_NO = LNCIGETR.INPUT_INFO.FACILITY_NO;
        CEP.TRC(SCCGWA, "----------------------------------");
        CEP.TRC(SCCGWA, LNCIGETR.OUTPOUT_INFO.TRANCHE_NO);
        CEP.TRC(SCCGWA, WS_TRANCHE_NO);
        JIBS_tmp_str[0] = "" + WS_TRANCHE_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (LNCIGETR.OUTPOUT_INFO.TRANCHE_NO == null) LNCIGETR.OUTPOUT_INFO.TRANCHE_NO = "";
        JIBS_tmp_int = LNCIGETR.OUTPOUT_INFO.TRANCHE_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNCIGETR.OUTPOUT_INFO.TRANCHE_NO += " ";
        LNCIGETR.OUTPOUT_INFO.TRANCHE_NO = LNCIGETR.OUTPOUT_INFO.TRANCHE_NO.substring(0, 12 - 1) + JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1) + LNCIGETR.OUTPOUT_INFO.TRANCHE_NO.substring(12 + 1 - 1);
        CEP.TRC(SCCGWA, LNCIGETR.OUTPOUT_INFO.TRANCHE_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
