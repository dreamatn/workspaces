package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTCHIS {
    DBParm BPTCHIS_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTCHIS";
    String K_TBL_CHIS = "BPTCHIS ";
    char WS_TBL_CHIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCHIS BPRCHIS = new BPRCHIS();
    SCCGWA SCCGWA;
    BPRCHIS BPRCHIS1;
    BPCRCHIS BPCRCHIS;
    public void MP(SCCGWA SCCGWA, BPCRCHIS BPCRCHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCHIS = BPCRCHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTCHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCHIS1 = (BPRCHIS) BPCRCHIS.POINTER;
        IBS.init(SCCGWA, BPRCHIS);
        IBS.CLONE(SCCGWA, BPRCHIS1, BPRCHIS);
        CEP.TRC(SCCGWA, BPRCHIS);
        CEP.TRC(SCCGWA, BPCRCHIS.LEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCHIS.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCHIS.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCHIS.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCHIS.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCHIS, BPRCHIS1);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCHIS();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPRCHIS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_CHIS_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHIS_NOTFND, BPCRCHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCHIS();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCHIS();
        if (pgmRtn) return;
        if (WS_TBL_CHIS_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHIS_NOTFND, BPCRCHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCHIS() throws IOException,SQLException,Exception {
        BPTCHIS_RD = new DBParm();
        BPTCHIS_RD.TableName = "BPTCHIS";
        IBS.READ(SCCGWA, BPRCHIS, BPTCHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_CHIS_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_CHIS_FLAG = 'D';
        } else {
        }
    }
    public void T000_WRITE_BPTCHIS() throws IOException,SQLException,Exception {
        BPTCHIS_RD = new DBParm();
        BPTCHIS_RD.TableName = "BPTCHIS";
        BPTCHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCHIS, BPTCHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCHIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRCHIS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPRCHIS_UPD() throws IOException,SQLException,Exception {
        BPTCHIS_RD = new DBParm();
        BPTCHIS_RD.TableName = "BPTCHIS";
        BPTCHIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRCHIS, BPTCHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_CHIS_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_CHIS_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTCHIS() throws IOException,SQLException,Exception {
        BPTCHIS_RD = new DBParm();
        BPTCHIS_RD.TableName = "BPTCHIS";
        IBS.REWRITE(SCCGWA, BPRCHIS, BPTCHIS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRCHIS = ");
            CEP.TRC(SCCGWA, BPCRCHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
