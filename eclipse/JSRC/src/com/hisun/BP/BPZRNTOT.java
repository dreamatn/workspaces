package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRNTOT {
    DBParm BPTNTOT_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRNTOT";
    String K_TBL_NTOT = "BPTNTOT ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRNTOT BPRNTOT = new BPRNTOT();
    SCCGWA SCCGWA;
    BPCRNTOT BPCRNTOT;
    BPRNTOT BPRNTOTL;
    public void MP(SCCGWA SCCGWA, BPCRNTOT BPCRNTOT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRNTOT = BPCRNTOT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRNTOT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRNTOTL = (BPRNTOT) BPCRNTOT.INFO.POINTER;
        IBS.init(SCCGWA, BPRNTOT);
        IBS.CLONE(SCCGWA, BPRNTOTL, BPRNTOT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRNTOT.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRNTOT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRNTOT.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRNTOT.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRNTOT.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRNTOT.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRNTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRNTOT, BPRNTOTL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTNTOT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTNTOT_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTNTOT();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTNTOT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTNTOT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTNTOT() throws IOException,SQLException,Exception {
        BPTNTOT_RD = new DBParm();
        BPTNTOT_RD.TableName = "BPTNTOT";
        IBS.READ(SCCGWA, BPRNTOT, BPTNTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRNTOT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRNTOT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTNTOT() throws IOException,SQLException,Exception {
        BPTNTOT_RD = new DBParm();
        BPTNTOT_RD.TableName = "BPTNTOT";
        BPTNTOT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRNTOT, BPTNTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRNTOT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRNTOT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NTOT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTNTOT_UPD() throws IOException,SQLException,Exception {
        BPTNTOT_RD = new DBParm();
        BPTNTOT_RD.TableName = "BPTNTOT";
        BPTNTOT_RD.upd = true;
        IBS.READ(SCCGWA, BPRNTOT, BPTNTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRNTOT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRNTOT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTNTOT() throws IOException,SQLException,Exception {
        BPTNTOT_RD = new DBParm();
        BPTNTOT_RD.TableName = "BPTNTOT";
        IBS.DELETE(SCCGWA, BPRNTOT, BPTNTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRNTOT.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_REWRITE_BPTNTOT() throws IOException,SQLException,Exception {
        BPTNTOT_RD = new DBParm();
        BPTNTOT_RD.TableName = "BPTNTOT";
        IBS.REWRITE(SCCGWA, BPRNTOT, BPTNTOT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRNTOT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRNTOT = ");
            CEP.TRC(SCCGWA, BPCRNTOT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
