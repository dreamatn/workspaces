package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRORGI {
    DBParm BPTORGI_RD;
    boolean pgmRtn = false;
    String CPN_R_MGM_ORGI = "BP-R-MGM-ORGI       ";
    String K_PGM_NAME = "BPZRORGI";
    String K_TBL_ORGI = "BPTORGI ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGI BPRORGI = new BPRORGI();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRORGI BPCRORGI;
    BPRORGI BPRORGIL;
    public void MP(SCCGWA SCCGWA, BPCRORGI BPCRORGI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRORGI = BPCRORGI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRORGI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGIL = (BPRORGI) BPCRORGI.INFO.POINTER;
        IBS.init(SCCGWA, BPRORGI);
        IBS.CLONE(SCCGWA, BPRORGIL, BPRORGI);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRORGI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRORGI.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGI.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGI.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGI.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGI.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRORGI.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRORGI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGI, BPRORGIL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTORGI();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGI_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTORGI();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGI();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTORGI();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        IBS.READ(SCCGWA, BPRORGI, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGI.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGI.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        BPTORGI_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRORGI, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGI.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRORGI.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ORGI;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTORGI_UPD() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        BPTORGI_RD.upd = true;
        IBS.READ(SCCGWA, BPRORGI, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGI.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGI.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        IBS.DELETE(SCCGWA, BPRORGI, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGI.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_REWRITE_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        IBS.REWRITE(SCCGWA, BPRORGI, BPTORGI_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRORGI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRORGI = ");
            CEP.TRC(SCCGWA, BPCRORGI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
