package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTROL {
    DBParm BPTTROL_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTROL";
    String K_TBL_TROL = "BPTTROL ";
    char WS_TBL_TROL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTROL BPRTROL = new BPRTROL();
    SCCGWA SCCGWA;
    BPCRTROL BPCRTROL;
    BPRTROL BPRLTROL;
    public void MP(SCCGWA SCCGWA, BPCRTROL BPCRTROL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTROL = BPCRTROL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTROL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLTROL = (BPRTROL) BPCRTROL.INFO.POINTER;
        IBS.init(SCCGWA, BPRTROL);
        IBS.CLONE(SCCGWA, BPRLTROL, BPRTROL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTROL.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTROL.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTROL.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTROL.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTROL.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTROL.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTROL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTROL, BPRLTROL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTROL();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTROL_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTROL();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTROL();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTROL();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTROL() throws IOException,SQLException,Exception {
        BPTTROL_RD = new DBParm();
        BPTTROL_RD.TableName = "BPTTROL";
        IBS.READ(SCCGWA, BPRTROL, BPTTROL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTROL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTROL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTROL() throws IOException,SQLException,Exception {
        BPTTROL_RD = new DBParm();
        BPTTROL_RD.TableName = "BPTTROL";
        BPTTROL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTROL, BPTTROL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTROL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTROL.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TROL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTROL_UPD() throws IOException,SQLException,Exception {
        BPTTROL_RD = new DBParm();
        BPTTROL_RD.TableName = "BPTTROL";
        BPTTROL_RD.upd = true;
        IBS.READ(SCCGWA, BPRTROL, BPTTROL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTROL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTROL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTROL() throws IOException,SQLException,Exception {
        BPTTROL_RD = new DBParm();
        BPTTROL_RD.TableName = "BPTTROL";
        IBS.REWRITE(SCCGWA, BPRTROL, BPTTROL_RD);
    }
    public void T000_DELETE_BPTTROL() throws IOException,SQLException,Exception {
        BPTTROL_RD = new DBParm();
        BPTTROL_RD.TableName = "BPTTROL";
        IBS.DELETE(SCCGWA, BPRTROL, BPTTROL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTROL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTROL = ");
            CEP.TRC(SCCGWA, BPCRTROL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
