package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBUSE {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBUSE_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBUSE";
    String K_TBL_BUSE = "BPTBUSE ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBUSE BPRBUSE = new BPRBUSE();
    SCCGWA SCCGWA;
    BPCRBUSE BPCRBUSE;
    BPRBUSE BPRBUSE1;
    public void MP(SCCGWA SCCGWA, BPCRBUSE BPCRBUSE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBUSE = BPCRBUSE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBUSE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBUSE1 = (BPRBUSE) BPCRBUSE.INFO.POINTER;
        IBS.init(SCCGWA, BPRBUSE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBUSE1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBUSE1, BPRBUSE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBUSE.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBUSE.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBUSE.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBUSE.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBUSE.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBUSE.INFO.FUNC == 'B') {
            B050_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBUSE.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBUSE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBUSE);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBUSE, BPRBUSE1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBUSE();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBUSE_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBUSE();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBUSE();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBUSE();
        if (pgmRtn) return;
    }
    public void B050_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READBR_BPTBUSE();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBUSE() throws IOException,SQLException,Exception {
        BPTBUSE_RD = new DBParm();
        BPTBUSE_RD.TableName = "BPTBUSE";
        IBS.READ(SCCGWA, BPRBUSE, BPTBUSE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBUSE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBUSE.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READBR_BPTBUSE() throws IOException,SQLException,Exception {
        BPTBUSE_RD = new DBParm();
        BPTBUSE_RD.TableName = "BPTBUSE";
        BPTBUSE_RD.where = "BR = :BPRBUSE.KEY.BR "
            + "AND BV_CODE = :BPRBUSE.KEY.BV_CODE "
            + "AND HEAD_NO = :BPRBUSE.KEY.HEAD_NO "
            + "AND END_NO = :BPRBUSE.KEY.END_NO "
            + "AND BEG_NO = :BPRBUSE.KEY.BEG_NO";
        IBS.READ(SCCGWA, BPRBUSE, this, BPTBUSE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBUSE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBUSE.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTBUSE() throws IOException,SQLException,Exception {
        BPTBUSE_RD = new DBParm();
        BPTBUSE_RD.TableName = "BPTBUSE";
        BPTBUSE_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBUSE, BPTBUSE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBUSE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBUSE.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BUSE;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBUSE_UPD() throws IOException,SQLException,Exception {
        BPTBUSE_RD = new DBParm();
        BPTBUSE_RD.TableName = "BPTBUSE";
        BPTBUSE_RD.upd = true;
        IBS.READ(SCCGWA, BPRBUSE, BPTBUSE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBUSE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBUSE.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTBUSE() throws IOException,SQLException,Exception {
        BPTBUSE_RD = new DBParm();
        BPTBUSE_RD.TableName = "BPTBUSE";
        IBS.REWRITE(SCCGWA, BPRBUSE, BPTBUSE_RD);
    }
    public void T000_DELETE_BPTBUSE() throws IOException,SQLException,Exception {
        BPTBUSE_RD = new DBParm();
        BPTBUSE_RD.TableName = "BPTBUSE";
        IBS.DELETE(SCCGWA, BPRBUSE, BPTBUSE_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBUSE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBUSE = ");
            CEP.TRC(SCCGWA, BPCRBUSE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
