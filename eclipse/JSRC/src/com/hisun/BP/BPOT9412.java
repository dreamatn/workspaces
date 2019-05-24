package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9412 {
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    SCCSTAR SCCSTAR = new SCCSTAR();
    BPCSBSP BPCSBSP = new BPCSBSP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9412 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBSP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MOVE_PROCEDURE_NAME();
    }
    public void B010_MOVE_PROCEDURE_NAME() throws IOException,SQLException,Exception {
        BPCSBSP.AP_PROC = "BPPONL01";
        S000_CALL_BPZSBSP();
    }
    public void S000_CALL_BPZSBSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BPZSBSP");
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "BPZSBSP";
        WS_STAR_COMM.STAR_DATA = BPCSBSP;
        IBS.START(SCCGWA, WS_STAR_COMM, true);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
