package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBSFLT {
    String JIBS_tmp_str[] = new String[10];
    String CPN_CALL_BPZIFLT = "BP-INIT-FLT-STS     ";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCIFLT BPCIFLT = new BPCIFLT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    SCCGWA SCCGWA;
    BPCOUFLT BPCOUFLT;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCOUFLT BPCOUFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUFLT = BPCOUFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZBSFLT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCOUFLT.FUNC);
        CEP.TRC(SCCGWA, BPCOUFLT.DATE);
        if (BPCOUFLT.FUNC == 'U') {
            BPCIFLT.DATE = BPCOUFLT.DATE;
        } else {
            BPCIFLT.DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCIFLT.DATE);
        S000_CALL_BPZIFLT();
    }
    public void S000_CALL_BPZIFLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZIFLT, BPCIFLT);
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
