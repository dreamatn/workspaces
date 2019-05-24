package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFLSA {
    DBParm BPTFLSA_RD;
    brParm BPTFLSA_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFLSA";
    String K_TBL_FLSA = "BPTFLSA ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FLSA_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFLSA BPRFLSA = new BPRFLSA();
    SCCGWA SCCGWA;
    BPRFLSA BPRFLSA1;
    BPCRFLSA BPCRFLSA;
    public void MP(SCCGWA SCCGWA, BPCRFLSA BPCRFLSA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFLSA = BPCRFLSA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFLSA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFLSA1 = (BPRFLSA) BPCRFLSA.POINTER;
        IBS.init(SCCGWA, BPCRFLSA.RC);
        IBS.CLONE(SCCGWA, BPRFLSA1, BPRFLSA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRFLSA.INFO.FUNC);
        if (BPCRFLSA.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSA.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSA.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSA.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSA.INFO.FUNC == 'P') {
            B500_STARTBR_PLBOX_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSA.INFO.FUNC == 'N') {
            B600_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSA.INFO.FUNC == 'E') {
            B700_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFLSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFLSA, BPRFLSA1);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFLSA();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFLSA_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFLSA();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFLSA();
        if (pgmRtn) return;
    }
    public void B500_STARTBR_PLBOX_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PLBOX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFLSA.RETURN_INFO);
    }
    public void B600_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFLSA();
        if (pgmRtn) return;
    }
    public void B700_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFLSA();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTFLSA() throws IOException,SQLException,Exception {
        BPTFLSA_RD = new DBParm();
        BPTFLSA_RD.TableName = "BPTFLSA";
        IBS.READ(SCCGWA, BPRFLSA, BPTFLSA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSA.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLSA_NOTFND, BPCRFLSA.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCRFLSA.RC);
    }
    public void T000_WRITE_BPTFLSA() throws IOException,SQLException,Exception {
        BPTFLSA_RD = new DBParm();
        BPTFLSA_RD.TableName = "BPTFLSA";
        BPTFLSA_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFLSA, BPTFLSA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFLSA.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FLSA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFLSA_UPD() throws IOException,SQLException,Exception {
        BPTFLSA_RD = new DBParm();
        BPTFLSA_RD.TableName = "BPTFLSA";
        BPTFLSA_RD.upd = true;
        IBS.READ(SCCGWA, BPRFLSA, BPTFLSA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSA.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLSA_NOTFND, BPCRFLSA.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTFLSA() throws IOException,SQLException,Exception {
        BPTFLSA_RD = new DBParm();
        BPTFLSA_RD.TableName = "BPTFLSA";
        IBS.REWRITE(SCCGWA, BPRFLSA, BPTFLSA_RD);
    }
    public void T000_STARTBR_PLBOX() throws IOException,SQLException,Exception {
        BPTFLSA_BR.rp = new DBParm();
        BPTFLSA_BR.rp.TableName = "BPTFLSA";
        BPTFLSA_BR.rp.where = "BR = :BPRFLSA.KEY.BR "
            + "AND PLBOX_NO = :BPRFLSA.KEY.PLBOX_NO "
            + "AND FLS_NUM > :BPRFLSA.FLS_NUM";
        BPTFLSA_BR.rp.errhdl = true;
        BPTFLSA_BR.rp.order = "FLS_CCY,FLS_VAL,FLS_VER";
        IBS.STARTBR(SCCGWA, BPRFLSA, this, BPTFLSA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFLSA() throws IOException,SQLException,Exception {
        BPTFLSA_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRFLSA, this, BPTFLSA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFLSA() throws IOException,SQLException,Exception {
        BPTFLSA_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTFLSA_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFLSA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFLSA = ");
            CEP.TRC(SCCGWA, BPCRFLSA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
