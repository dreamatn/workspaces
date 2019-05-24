package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTMUP {
    DBParm BPTTMUP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTMUP";
    String K_TBL_TMUP = "BPTTMUP ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTMUP BPRTMUP = new BPRTMUP();
    SCCGWA SCCGWA;
    BPCRTMUP BPCRTMUP;
    BPRTMUP BPRTMUL;
    public void MP(SCCGWA SCCGWA, BPCRTMUP BPCRTMUP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTMUP = BPCRTMUP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTMUP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTMUL = (BPRTMUP) BPCRTMUP.INFO.POINTER;
        IBS.init(SCCGWA, BPRTMUP);
        IBS.CLONE(SCCGWA, BPRTMUL, BPRTMUP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMUP.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMUP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMUP.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMUP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMUP.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTMUP.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTMUP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTMUP, BPRTMUL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTMUP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTMUP_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTMUP();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTMUP();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTMUP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTMUP() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        IBS.READ(SCCGWA, BPRTMUP, BPTTMUP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMUP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMUP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTMUP() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTMUP, BPTTMUP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMUP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTMUP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMUP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTMUP_UPD() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.upd = true;
        IBS.READ(SCCGWA, BPRTMUP, BPTTMUP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMUP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMUP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTMUP() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        IBS.REWRITE(SCCGWA, BPRTMUP, BPTTMUP_RD);
    }
    public void T000_DELETE_BPTTMUP() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        IBS.DELETE(SCCGWA, BPRTMUP, BPTTMUP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTMUP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTMUP = ");
            CEP.TRC(SCCGWA, BPCRTMUP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
