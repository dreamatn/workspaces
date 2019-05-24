package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRGRPL {
    DBParm BPTGRPL_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRGRPL";
    String K_TBL_GRPL = "BPTGRPL ";
    char WS_TBL_GRPL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRGRPL BPRGRPL = new BPRGRPL();
    SCCGWA SCCGWA;
    BPCRGRPL BPCRGRPL;
    BPRGRPL BPRGRPL1;
    public void MP(SCCGWA SCCGWA, BPCRGRPL BPCRGRPL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRGRPL = BPCRGRPL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRGRPL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRGRPL1 = (BPRGRPL) BPCRGRPL.INFO.POINTER;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.CLONE(SCCGWA, BPRGRPL1, BPRGRPL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRGRPL.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPL.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPL.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPL.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPL.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRGRPL.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRGRPL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRGRPL, BPRGRPL1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTGRPL();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTGRPL_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTGRPL();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTGRPL();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTGRPL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTGRPL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRGRPL);
        BPTGRPL_RD = new DBParm();
        BPTGRPL_RD.TableName = "BPTGRPL";
        IBS.READ(SCCGWA, BPRGRPL, BPTGRPL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRPL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTGRPL() throws IOException,SQLException,Exception {
        BPTGRPL_RD = new DBParm();
        BPTGRPL_RD.TableName = "BPTGRPL";
        BPTGRPL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRGRPL, BPTGRPL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRGRPL.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_GRPL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTGRPL_UPD() throws IOException,SQLException,Exception {
        BPTGRPL_RD = new DBParm();
        BPTGRPL_RD.TableName = "BPTGRPL";
        BPTGRPL_RD.upd = true;
        IBS.READ(SCCGWA, BPRGRPL, BPTGRPL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRPL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTGRPL() throws IOException,SQLException,Exception {
        BPTGRPL_RD = new DBParm();
        BPTGRPL_RD.TableName = "BPTGRPL";
        IBS.REWRITE(SCCGWA, BPRGRPL, BPTGRPL_RD);
    }
    public void T000_DELETE_BPTGRPL() throws IOException,SQLException,Exception {
        BPTGRPL_RD = new DBParm();
        BPTGRPL_RD.TableName = "BPTGRPL";
        IBS.DELETE(SCCGWA, BPRGRPL, BPTGRPL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRGRPL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRGRPL = ");
            CEP.TRC(SCCGWA, BPCRGRPL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
