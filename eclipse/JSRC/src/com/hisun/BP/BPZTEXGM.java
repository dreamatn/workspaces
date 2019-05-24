package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTEXGM {
    DBParm BPTEXG_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTEXGM";
    String K_TBL_EXG = "BPTEXG  ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXG BPREXG = new BPREXG();
    SCCGWA SCCGWA;
    BPCEXGM BPCEXGM;
    BPREXG BPREXG1;
    public void MP(SCCGWA SCCGWA, BPCEXGM BPCEXGM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXGM = BPCEXGM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEXGM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXG1 = (BPREXG) BPCEXGM.INFO.POINTER;
        IBS.init(SCCGWA, BPREXG);
        IBS.CLONE(SCCGWA, BPREXG1, BPREXG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCEXGM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCEXGM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCEXGM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCEXGM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCEXGM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCEXGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXG, BPREXG1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTEXG();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXG_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXG();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTEXG();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEXG();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTEXG() throws IOException,SQLException,Exception {
        BPTEXG_RD = new DBParm();
        BPTEXG_RD.TableName = "BPTEXG";
        IBS.READ(SCCGWA, BPREXG, BPTEXG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCEXGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCEXGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTEXG() throws IOException,SQLException,Exception {
        BPTEXG_RD = new DBParm();
        BPTEXG_RD.TableName = "BPTEXG";
        BPTEXG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPREXG, BPTEXG_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCEXGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCEXGM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_EXG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTEXG_UPD() throws IOException,SQLException,Exception {
        BPTEXG_RD = new DBParm();
        BPTEXG_RD.TableName = "BPTEXG";
        BPTEXG_RD.upd = true;
        IBS.READ(SCCGWA, BPREXG, BPTEXG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCEXGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCEXGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTEXG() throws IOException,SQLException,Exception {
        BPTEXG_RD = new DBParm();
        BPTEXG_RD.TableName = "BPTEXG";
        IBS.REWRITE(SCCGWA, BPREXG, BPTEXG_RD);
    }
    public void T000_DELETE_BPTEXG() throws IOException,SQLException,Exception {
        BPTEXG_RD = new DBParm();
        BPTEXG_RD.TableName = "BPTEXG";
        IBS.DELETE(SCCGWA, BPREXG, BPTEXG_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXGM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCEXGM = ");
            CEP.TRC(SCCGWA, BPCEXGM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
