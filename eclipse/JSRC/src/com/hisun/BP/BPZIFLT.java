package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIFLT {
    String K_PGM_NAME = "BPZIFLT";
    String CPN_CALL_BPZIFLTN = "BP-INIT-FLT-NRULE   ";
    String CPN_CALL_BPZIFLTT = "BP-INIT-FLT-TRULE   ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCIFLTN BPCIFLTN = new BPCIFLTN();
    BPCIFLTT BPCIFLTT = new BPCIFLTT();
    SCCGWA SCCGWA;
    BPCIFLT BPCIFLT;
    public void MP(SCCGWA SCCGWA, BPCIFLT BPCIFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFLT = BPCIFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZIFLT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NORMAL;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INITIALIZE_NOMAL_DAY();
        B020_INITIALIZE_TYPH_DAY();
    }
    public void B010_INITIALIZE_NOMAL_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFLTN);
        BPCIFLTN.DATE = BPCIFLT.DATE;
        S000_CALL_BPZIFLTN();
    }
    public void B020_INITIALIZE_TYPH_DAY() throws IOException,SQLException,Exception {
        if (BPCIFLTN.TYPH_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIFLTT);
            BPCIFLTT.DATE = BPCIFLT.DATE;
            BPCIFLTT.TYPH_CODE = BPCIFLTN.TYPH_CODE;
            S000_CALL_BPZIFLTT();
        }
    }
    public void S000_CALL_BPZIFLTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZIFLTN, BPCIFLTN);
    }
    public void S000_CALL_BPZIFLTT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZIFLTT, BPCIFLTT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
