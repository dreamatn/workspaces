package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSALAR {
    char WS_FILLER = ' ';
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCSFILE BPCSFILE;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, BPCSFILE BPCSFILE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFILE = BPCSFILE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSALAR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B002_CALL_BPZSFILE();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B002_CALL_BPZSFILE() throws IOException,SQLException,Exception {
        BPCSFILE.FILE_TYP = "DDQSALA";
        CEP.TRC(SCCGWA, BPCSFILE);
        S000_CALL_BPZSFILE();
    }
    public void S000_CALL_BPZSFILE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BSP-FILE", BPCSFILE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
