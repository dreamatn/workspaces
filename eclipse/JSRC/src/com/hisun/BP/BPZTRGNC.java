package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTRGNC {
    DBParm BPTRGNC_RD;
    String K_PGM_NAME = "BPZTRGNC";
    String K_TBL_RGNC = "BPTRGNC ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_RGNC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCTRGNC BPCTRGNC;
    BPRRGNC BPRRGNC;
    public void MP(SCCGWA SCCGWA, BPCTRGNC BPCTRGNC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTRGNC = BPCTRGNC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZTRGNC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRRGNC = (BPRRGNC) BPCTRGNC.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRGNC();
    }
    public void T000_READ_BPTRGNC() throws IOException,SQLException,Exception {
        BPTRGNC_RD = new DBParm();
        BPTRGNC_RD.TableName = "BPTRGNC";
        IBS.READ(SCCGWA, BPRRGNC, BPTRGNC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTRGNC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTRGNC.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTRGNC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTRGNC = ");
            CEP.TRC(SCCGWA, BPCTRGNC);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
