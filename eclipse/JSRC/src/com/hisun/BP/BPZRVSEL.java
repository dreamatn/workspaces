package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRVSEL {
    DBParm BPTVSEL_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRVSEL";
    String K_TBL_VSEL = "BPTVSEL ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRVSEL BPRVSEL = new BPRVSEL();
    SCCGWA SCCGWA;
    BPCRVSEL BPCRVSEL;
    BPRVSEL BPRVSEL1;
    public void MP(SCCGWA SCCGWA, BPCRVSEL BPCRVSEL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRVSEL = BPCRVSEL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRVSEL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRVSEL1 = (BPRVSEL) BPCRVSEL.INFO.POINTER;
        IBS.init(SCCGWA, BPRVSEL);
        IBS.CLONE(SCCGWA, BPRVSEL1, BPRVSEL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRVSEL.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRVSEL.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRVSEL.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRVSEL.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRVSEL.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRVSEL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRVSEL, BPRVSEL1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTVSEL();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVSEL_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTVSEL();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVSEL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTVSEL() throws IOException,SQLException,Exception {
        BPTVSEL_RD = new DBParm();
        BPTVSEL_RD.TableName = "BPTVSEL";
        IBS.READ(SCCGWA, BPRVSEL, BPTVSEL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRVSEL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRVSEL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTVSEL() throws IOException,SQLException,Exception {
        BPTVSEL_RD = new DBParm();
        BPTVSEL_RD.TableName = "BPTVSEL";
        BPTVSEL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRVSEL, BPTVSEL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRVSEL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRVSEL.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_VSEL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVSEL_UPD() throws IOException,SQLException,Exception {
        BPTVSEL_RD = new DBParm();
        BPTVSEL_RD.TableName = "BPTVSEL";
        BPTVSEL_RD.upd = true;
        IBS.READ(SCCGWA, BPRVSEL, BPTVSEL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRVSEL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRVSEL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTVSEL() throws IOException,SQLException,Exception {
        BPTVSEL_RD = new DBParm();
        BPTVSEL_RD.TableName = "BPTVSEL";
        IBS.REWRITE(SCCGWA, BPRVSEL, BPTVSEL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRVSEL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRVSEL = ");
            CEP.TRC(SCCGWA, BPCRVSEL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
