package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBMOV {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBMOV_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBMOV";
    String K_TBL_BMOV = "BPTBMOV ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBMOV BPRBMOV = new BPRBMOV();
    SCCGWA SCCGWA;
    BPCRBMOV BPCRBMOV;
    BPRBMOV BPRBMOV1;
    public void MP(SCCGWA SCCGWA, BPCRBMOV BPCRBMOV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBMOV = BPCRBMOV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBMOV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBMOV1 = (BPRBMOV) BPCRBMOV.INFO.POINTER;
        IBS.init(SCCGWA, BPRBMOV);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBMOV1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBMOV1, BPRBMOV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBMOV.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBMOV.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBMOV.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBMOV.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBMOV.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBMOV);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBMOV, BPRBMOV1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBMOV();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBMOV_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBMOV();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBMOV();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBMOV() throws IOException,SQLException,Exception {
        BPTBMOV_RD = new DBParm();
        BPTBMOV_RD.TableName = "BPTBMOV";
        IBS.READ(SCCGWA, BPRBMOV, BPTBMOV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTBMOV() throws IOException,SQLException,Exception {
        BPTBMOV_RD = new DBParm();
        BPTBMOV_RD.TableName = "BPTBMOV";
        BPTBMOV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBMOV, BPTBMOV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBMOV.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BMOV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBMOV_UPD() throws IOException,SQLException,Exception {
        BPTBMOV_RD = new DBParm();
        BPTBMOV_RD.TableName = "BPTBMOV";
        BPTBMOV_RD.upd = true;
        IBS.READ(SCCGWA, BPRBMOV, BPTBMOV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBMOV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBMOV.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTBMOV() throws IOException,SQLException,Exception {
        BPTBMOV_RD = new DBParm();
        BPTBMOV_RD.TableName = "BPTBMOV";
        IBS.REWRITE(SCCGWA, BPRBMOV, BPTBMOV_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBMOV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBMOV = ");
            CEP.TRC(SCCGWA, BPCRBMOV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
