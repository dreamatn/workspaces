package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZGRATE {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBG60   ";
    double WS_TMP_RATE = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCORATE IBCORATE = new IBCORATE();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCGRATE IBCGRATE;
    public void MP(SCCGWA SCCGWA, IBCGRATE IBCGRATE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCGRATE = IBCGRATE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZGRATE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_RATE_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCGRATE.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCGRATE.RATE_TERM);
        if (IBCGRATE.RATE_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.RATE_TYPE_M);
        }
        if (IBCGRATE.RATE_TERM.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.RATE_TERM_M);
        }
    }
    public void B020_GET_RATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = IBCGRATE.BR;
        BPCCINTI.BASE_INFO.CCY = IBCGRATE.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = IBCGRATE.RATE_TYPE;
        BPCCINTI.BASE_INFO.TENOR = IBCGRATE.RATE_TERM;
        BPCCINTI.BASE_INFO.DT = IBCGRATE.IN_DATE;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
        if (IBCGRATE.RATE_SPREAD != 0) {
            WS_TMP_RATE = BPCCINTI.BASE_INFO.RATE + IBCGRATE.RATE_SPREAD;
        } else {
            WS_TMP_RATE = BPCCINTI.BASE_INFO.RATE * ( 1 + IBCGRATE.RATE_PCT / 100 );
        }
        CEP.TRC(SCCGWA, WS_TMP_RATE);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCORATE);
        IBCORATE.RATE = WS_TMP_RATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCORATE;
        SCCFMT.DATA_LEN = 12;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
