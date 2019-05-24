package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTLBIF {
    DBParm BPTCLBI_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTLBIF";
    String K_TBL_CLBI = "BPTCLBI ";
    char WS_TBL_CLBI_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLBI BPRCLBI = new BPRCLBI();
    SCCGWA SCCGWA;
    BPRCLBI BPRCLBI1;
    BPCTLBIF BPCTLBIF;
    public void MP(SCCGWA SCCGWA, BPCTLBIF BPCTLBIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTLBIF = BPCTLBIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTLBIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCLBI1 = (BPRCLBI) BPCTLBIF.POINTER;
        IBS.init(SCCGWA, BPRCLBI);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCLBI1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCLBI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTLBIF.INFO.FUNC == 'A') {
            B100_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIF.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTLBIF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCLBI, BPRCLBI1);
    }
    public void B100_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCLBI_UPD();
        if (pgmRtn) return;
        if (WS_TBL_CLBI_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLBI_NOTFND, BPCTLBIF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCLBI();
        if (pgmRtn) return;
        if (WS_TBL_CLBI_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLBI_NOTFND, BPCTLBIF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTCLBI();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        IBS.READ(SCCGWA, BPRCLBI, BPTCLBI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_CLBI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_CLBI_FLAG = 'D';
        } else {
        }
    }
    public void T000_WRITE_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        BPTCLBI_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCLBI, BPTCLBI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLBIF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTLBIF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CLBI;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCLBI_UPD() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        BPTCLBI_RD.upd = true;
        IBS.READ(SCCGWA, BPRCLBI, BPTCLBI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_CLBI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_CLBI_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        IBS.REWRITE(SCCGWA, BPRCLBI, BPTCLBI_RD);
    }
    public void T000_DELETE_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        IBS.DELETE(SCCGWA, BPRCLBI, BPTCLBI_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTLBIF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTLBIF = ");
            CEP.TRC(SCCGWA, BPCTLBIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
