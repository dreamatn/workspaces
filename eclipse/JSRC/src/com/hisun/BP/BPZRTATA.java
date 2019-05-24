package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTATA {
    DBParm BPTTATHA_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTATA";
    String K_TBL_TATHA = "BPTTATHA";
    char WS_TBL_TATHA_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTATHA BPRTATHA = new BPRTATHA();
    SCCGWA SCCGWA;
    BPCRTATA BPCRTATA;
    BPRTATHA BPRTATHL;
    public void MP(SCCGWA SCCGWA, BPCRTATA BPCRTATA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTATA = BPCRTATA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTATA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTATHL = (BPRTATHA) BPCRTATA.INFO.POINTER;
        IBS.init(SCCGWA, BPRTATHA);
        IBS.CLONE(SCCGWA, BPRTATHL, BPRTATHA);
        CEP.TRC(SCCGWA, BPCRTATA.INFO.LEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTATA.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATA.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATA.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATA.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATA.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTATHA, BPRTATHL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTATHA();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTATHA_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTATHA();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTATHA();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTATHA();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTATHA() throws IOException,SQLException,Exception {
        BPTTATHA_RD = new DBParm();
        BPTTATHA_RD.TableName = "BPTTATHA";
        IBS.READ(SCCGWA, BPRTATHA, BPTTATHA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTATA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTATHA() throws IOException,SQLException,Exception {
        BPTTATHA_RD = new DBParm();
        BPTTATHA_RD.TableName = "BPTTATHA";
        IBS.WRITE(SCCGWA, BPRTATHA, BPTTATHA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTATA.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TATHA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTATHA_UPD() throws IOException,SQLException,Exception {
        BPTTATHA_RD = new DBParm();
        BPTTATHA_RD.TableName = "BPTTATHA";
        BPTTATHA_RD.upd = true;
        IBS.READ(SCCGWA, BPRTATHA, BPTTATHA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTATA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTATHA() throws IOException,SQLException,Exception {
        BPTTATHA_RD = new DBParm();
        BPTTATHA_RD.TableName = "BPTTATHA";
        IBS.REWRITE(SCCGWA, BPRTATHA, BPTTATHA_RD);
    }
    public void T000_DELETE_BPTTATHA() throws IOException,SQLException,Exception {
        BPTTATHA_RD = new DBParm();
        BPTTATHA_RD.TableName = "BPTTATHA";
        IBS.DELETE(SCCGWA, BPRTATHA, BPTTATHA_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTATA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTATA = ");
            CEP.TRC(SCCGWA, BPCRTATA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
