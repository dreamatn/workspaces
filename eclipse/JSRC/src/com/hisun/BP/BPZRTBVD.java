package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTBVD {
    DBParm BPTTBVD_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTBVD";
    String K_TBL_TBVD = "BPTTBVD ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTBVD BPRTBVD = new BPRTBVD();
    SCCGWA SCCGWA;
    BPCRTBVD BPCRTBVD;
    BPRTBVD BPRTBVL;
    public void MP(SCCGWA SCCGWA, BPCRTBVD BPCRTBVD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTBVD = BPCRTBVD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTBVD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTBVL = (BPRTBVD) BPCRTBVD.INFO.POINTER;
        IBS.init(SCCGWA, BPRTBVD);
        IBS.CLONE(SCCGWA, BPRTBVL, BPRTBVD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBVD.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBVD.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBVD.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBVD.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBVD.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTBVD.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTBVD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTBVD, BPRTBVL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTBVD();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBVD_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTBVD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBVD();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTBVD();
        if (pgmRtn) return;
    }
    public void T000_DELETE_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        IBS.DELETE(SCCGWA, BPRTBVD, BPTTBVD_RD);
    }
    public void T000_READ_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        IBS.READ(SCCGWA, BPRTBVD, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBVD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBVD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTBVD, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBVD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTBVD.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TBVD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTBVD_UPD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.upd = true;
        IBS.READ(SCCGWA, BPRTBVD, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBVD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBVD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        IBS.REWRITE(SCCGWA, BPRTBVD, BPTTBVD_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTBVD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTBVD = ");
            CEP.TRC(SCCGWA, BPCRTBVD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
