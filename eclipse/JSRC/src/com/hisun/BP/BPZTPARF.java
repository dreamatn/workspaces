package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTPARF {
    DBParm BPTMPAR_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTPARF";
    String K_TBL_MPAR = "BPTMPAR ";
    char WS_TBL_MPAR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRMPAR BPRMPAR = new BPRMPAR();
    SCCGWA SCCGWA;
    BPCRPARF BPCRPARF;
    BPRMPAR BPRMPAR1;
    public void MP(SCCGWA SCCGWA, BPCRPARF BPCRPARF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPARF = BPCRPARF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTPARF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRMPAR1 = (BPRMPAR) BPCRPARF.POINTER;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRMPAR1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRMPAR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRPARF.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPARF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPARF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPARF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRPARF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTMPAR();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTMPAR_UPD();
        if (pgmRtn) return;
        if (WS_TBL_MPAR_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MPAR_NOTFND, BPCRPARF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRMPAR, BPRMPAR1);
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTMPAR();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTMPAR();
        if (pgmRtn) return;
        if (WS_TBL_MPAR_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MPAR_NOTFND, BPCRPARF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTMPAR() throws IOException,SQLException,Exception {
        BPTMPAR_RD = new DBParm();
        BPTMPAR_RD.TableName = "BPTMPAR";
        IBS.READ(SCCGWA, BPRMPAR, BPTMPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_MPAR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_MPAR_FLAG = 'D';
        } else {
        }
    }
    public void T000_WRITE_BPTMPAR() throws IOException,SQLException,Exception {
        BPTMPAR_RD = new DBParm();
        BPTMPAR_RD.TableName = "BPTMPAR";
        BPTMPAR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRMPAR, BPTMPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRPARF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRPARF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_MPAR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTMPAR_UPD() throws IOException,SQLException,Exception {
        BPTMPAR_RD = new DBParm();
        BPTMPAR_RD.TableName = "BPTMPAR";
        BPTMPAR_RD.upd = true;
        IBS.READ(SCCGWA, BPRMPAR, BPTMPAR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_MPAR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_MPAR_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTMPAR() throws IOException,SQLException,Exception {
        BPTMPAR_RD = new DBParm();
        BPTMPAR_RD.TableName = "BPTMPAR";
        IBS.REWRITE(SCCGWA, BPRMPAR, BPTMPAR_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
