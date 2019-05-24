package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRGRP {
    DBParm BPTGRP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRGRP ";
    String K_TBL_GRP = "BPTGRP  ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRGRP BPRGRP = new BPRGRP();
    SCCGWA SCCGWA;
    BPCRGRP BPCRGRP;
    BPRGRP BPRGRP1;
    public void MP(SCCGWA SCCGWA, BPCRGRP BPCRGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRGRP = BPCRGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRGRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRGRP1 = (BPRGRP) BPCRGRP.INFO.POINTER;
        IBS.init(SCCGWA, BPRGRP);
        IBS.CLONE(SCCGWA, BPRGRP1, BPRGRP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRGRP.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRP.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRP.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRGRP.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRGRP, BPRGRP1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTGRP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTGRP_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTGRP();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTGRP();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTGRP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTGRP() throws IOException,SQLException,Exception {
        BPTGRP_RD = new DBParm();
        BPTGRP_RD.TableName = "BPTGRP";
        IBS.READ(SCCGWA, BPRGRP, BPTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTGRP() throws IOException,SQLException,Exception {
        BPTGRP_RD = new DBParm();
        BPTGRP_RD.TableName = "BPTGRP";
        BPTGRP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRGRP, BPTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRGRP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_GRP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTGRP_UPD() throws IOException,SQLException,Exception {
        BPTGRP_RD = new DBParm();
        BPTGRP_RD.TableName = "BPTGRP";
        BPTGRP_RD.upd = true;
        IBS.READ(SCCGWA, BPRGRP, BPTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTGRP() throws IOException,SQLException,Exception {
        BPTGRP_RD = new DBParm();
        BPTGRP_RD.TableName = "BPTGRP";
        IBS.REWRITE(SCCGWA, BPRGRP, BPTGRP_RD);
    }
    public void T000_DELETE_BPTGRP() throws IOException,SQLException,Exception {
        BPTGRP_RD = new DBParm();
        BPTGRP_RD.TableName = "BPTGRP";
        IBS.DELETE(SCCGWA, BPRGRP, BPTGRP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRGRP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRGRP = ");
            CEP.TRC(SCCGWA, BPCRGRP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
