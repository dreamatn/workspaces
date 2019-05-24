package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHPAR {
    DBParm BPTHPAR_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRHPAR";
    String K_TBL_HPAR = "BPTHPAR ";
    char WS_TBL_HPAR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRHPAR BPRHPAR = new BPRHPAR();
    SCCGWA SCCGWA;
    BPRHPAR BPRHPAR1;
    BPCRHPAR BPCRHPAR;
    public void MP(SCCGWA SCCGWA, BPCRHPAR BPCRHPAR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHPAR = BPCRHPAR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHPAR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRHPAR1 = (BPRHPAR) BPCRHPAR.POINTER;
        IBS.init(SCCGWA, BPRHPAR);
        IBS.CLONE(SCCGWA, BPRHPAR1, BPRHPAR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRHPAR.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHPAR.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHPAR.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHPAR.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRHPAR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRHPAR, BPRHPAR1);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPRHPAR();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPRHPAR_UPD();
        if (pgmRtn) return;
        if (WS_TBL_HPAR_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHIS_NOTFND, BPCRHPAR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPRHPAR();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPRHPAR();
        if (pgmRtn) return;
        if (WS_TBL_HPAR_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHIS_NOTFND, BPCRHPAR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPRHPAR() throws IOException,SQLException,Exception {
        BPTHPAR_RD = new DBParm();
        BPTHPAR_RD.TableName = "BPTHPAR";
        IBS.READ(SCCGWA, BPRHPAR, BPTHPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_HPAR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_HPAR_FLAG = 'D';
        } else {
        }
    }
    public void T000_WRITE_BPRHPAR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRHPAR);
        BPTHPAR_RD = new DBParm();
        BPTHPAR_RD.TableName = "BPTHPAR";
        BPTHPAR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRHPAR, BPTHPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHPAR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRHPAR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_HPAR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPRHPAR_UPD() throws IOException,SQLException,Exception {
        BPTHPAR_RD = new DBParm();
        BPTHPAR_RD.TableName = "BPTHPAR";
        BPTHPAR_RD.upd = true;
        IBS.READ(SCCGWA, BPRHPAR, BPTHPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_HPAR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_HPAR_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPRHPAR() throws IOException,SQLException,Exception {
        BPTHPAR_RD = new DBParm();
        BPTHPAR_RD.TableName = "BPTHPAR";
        IBS.REWRITE(SCCGWA, BPRHPAR, BPTHPAR_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHPAR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHPAR = ");
            CEP.TRC(SCCGWA, BPCRHPAR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
