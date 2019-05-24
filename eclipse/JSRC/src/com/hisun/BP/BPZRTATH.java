package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTATH {
    DBParm BPTTATH_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTATH";
    String K_TBL_TATH = "BPTTATH ";
    char WS_TBL_TATH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTATH BPRTATH = new BPRTATH();
    SCCGWA SCCGWA;
    BPCRTATH BPCRTATH;
    BPRTATH BPRTATL;
    public void MP(SCCGWA SCCGWA, BPCRTATH BPCRTATH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTATH = BPCRTATH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTATH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTATL = (BPRTATH) BPCRTATH.INFO.POINTER;
        IBS.init(SCCGWA, BPRTATH);
        IBS.CLONE(SCCGWA, BPRTATL, BPRTATH);
        CEP.TRC(SCCGWA, BPCRTATH.INFO.LEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTATH.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATH.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATH.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATH.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATH.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTATH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTATH, BPRTATL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTATH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTATH_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTATH();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTATH();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTATH();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTATH() throws IOException,SQLException,Exception {
        BPTTATH_RD = new DBParm();
        BPTTATH_RD.TableName = "BPTTATH";
        IBS.READ(SCCGWA, BPRTATH, BPTTATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTATH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTATH() throws IOException,SQLException,Exception {
        BPTTATH_RD = new DBParm();
        BPTTATH_RD.TableName = "BPTTATH";
        IBS.WRITE(SCCGWA, BPRTATH, BPTTATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTATH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TATH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTATH_UPD() throws IOException,SQLException,Exception {
        BPTTATH_RD = new DBParm();
        BPTTATH_RD.TableName = "BPTTATH";
        BPTTATH_RD.upd = true;
        IBS.READ(SCCGWA, BPRTATH, BPTTATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTATH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTATH() throws IOException,SQLException,Exception {
        BPTTATH_RD = new DBParm();
        BPTTATH_RD.TableName = "BPTTATH";
        IBS.REWRITE(SCCGWA, BPRTATH, BPTTATH_RD);
    }
    public void T000_DELETE_BPTTATH() throws IOException,SQLException,Exception {
        BPTTATH_RD = new DBParm();
        BPTTATH_RD.TableName = "BPTTATH";
        IBS.DELETE(SCCGWA, BPRTATH, BPTTATH_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTATH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTATH = ");
            CEP.TRC(SCCGWA, BPCRTATH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
