package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFLSH {
    DBParm BPTFLSH_RD;
    brParm BPTFLSH_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFLSH";
    String K_TBL_FLSH = "BPTFLSH ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FLSH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFLSH BPRFLSH = new BPRFLSH();
    SCCGWA SCCGWA;
    BPRFLSH BPRFLSH1;
    BPCRFLSH BPCRFLSH;
    public void MP(SCCGWA SCCGWA, BPCRFLSH BPCRFLSH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFLSH = BPCRFLSH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFLSH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFLSH1 = (BPRFLSH) BPCRFLSH.POINTER;
        IBS.init(SCCGWA, BPCRFLSH.RC);
        CEP.TRC(SCCGWA, BPCRFLSH.LEN);
        IBS.CLONE(SCCGWA, BPRFLSH1, BPRFLSH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFLSH.INFO.FUNC == 'I') {
            B100_INQUIRY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'A') {
            B200_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'R') {
            B300_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'U') {
            B400_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'P') {
            B500_STARTBR_PLBOX_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'X') {
            B600_STARTBR_RECV_TYPE_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'Y') {
            B700_STARTBR_PAY_TYPE_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'N') {
            B800_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFLSH.INFO.FUNC == 'E') {
            B900_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFLSH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFLSH, BPRFLSH1);
    }
    public void B100_INQUIRY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFLSH();
        if (pgmRtn) return;
    }
    public void B200_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFLSH();
        if (pgmRtn) return;
    }
    public void B300_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFLSH_UPD();
        if (pgmRtn) return;
    }
    public void B400_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFLSH();
        if (pgmRtn) return;
    }
    public void B500_STARTBR_PLBOX_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PLBOX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFLSH.RETURN_INFO);
    }
    public void B600_STARTBR_RECV_TYPE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_RECV_TYPE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFLSH.RETURN_INFO);
    }
    public void B700_STARTBR_PAY_TYPE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PAY_TYPE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFLSH.RETURN_INFO);
    }
    public void B800_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFLSH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFLSH.RETURN_INFO);
    }
    public void B900_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFLSH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFLSH.RETURN_INFO);
    }
    public void T000_READ_BPTFLSH() throws IOException,SQLException,Exception {
        BPTFLSH_RD = new DBParm();
        BPTFLSH_RD.TableName = "BPTFLSH";
        IBS.READ(SCCGWA, BPRFLSH, BPTFLSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSH.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLSH_NOTFND, BPCRFLSH.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCRFLSH.RC);
    }
    public void T000_WRITE_BPTFLSH() throws IOException,SQLException,Exception {
        BPTFLSH_RD = new DBParm();
        BPTFLSH_RD.TableName = "BPTFLSH";
        BPTFLSH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFLSH, BPTFLSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFLSH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FLSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFLSH_UPD() throws IOException,SQLException,Exception {
        BPTFLSH_RD = new DBParm();
        BPTFLSH_RD.TableName = "BPTFLSH";
        BPTFLSH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFLSH, BPTFLSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSH.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLSH_NOTFND, BPCRFLSH.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTFLSH() throws IOException,SQLException,Exception {
        BPTFLSH_RD = new DBParm();
        BPTFLSH_RD.TableName = "BPTFLSH";
        IBS.REWRITE(SCCGWA, BPRFLSH, BPTFLSH_RD);
    }
    public void T000_STARTBR_PLBOX() throws IOException,SQLException,Exception {
        BPTFLSH_BR.rp = new DBParm();
        BPTFLSH_BR.rp.TableName = "BPTFLSH";
        BPTFLSH_BR.rp.where = "( DATE >= :BPRFLSH.KEY.DATE "
            + "AND BR = :BPRFLSH.BR "
            + "AND REC_PBNO = :BPRFLSH.REC_PBNO ) "
            + "OR ( DATE >= :BPRFLSH.KEY.DATE "
            + "AND BR = :BPRFLSH.BR "
            + "AND PAY_PBNO = :BPRFLSH.PAY_PBNO )";
        BPTFLSH_BR.rp.errhdl = true;
        BPTFLSH_BR.rp.order = "DATE,CAP_NO,JRNNO";
        IBS.STARTBR(SCCGWA, BPRFLSH, this, BPTFLSH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_RECV_TYPE() throws IOException,SQLException,Exception {
        BPTFLSH_BR.rp = new DBParm();
        BPTFLSH_BR.rp.TableName = "BPTFLSH";
        BPTFLSH_BR.rp.where = "DATE >= :BPRFLSH.KEY.DATE "
            + "AND BR = :BPRFLSH.BR "
            + "AND FLS_TYP = :BPRFLSH.FLS_TYP "
            + "AND STS = :BPRFLSH.STS "
            + "AND REC_PBNO = :BPRFLSH.REC_PBNO";
        BPTFLSH_BR.rp.errhdl = true;
        BPTFLSH_BR.rp.order = "DATE,CAP_NO,JRNNO";
        IBS.STARTBR(SCCGWA, BPRFLSH, this, BPTFLSH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_PAY_TYPE() throws IOException,SQLException,Exception {
        BPTFLSH_BR.rp = new DBParm();
        BPTFLSH_BR.rp.TableName = "BPTFLSH";
        BPTFLSH_BR.rp.where = "DATE >= :BPRFLSH.KEY.DATE "
            + "AND BR = :BPRFLSH.BR "
            + "AND FLS_TYP = :BPRFLSH.FLS_TYP "
            + "AND STS = :BPRFLSH.STS "
            + "AND PAY_PBNO = :BPRFLSH.PAY_PBNO";
        BPTFLSH_BR.rp.errhdl = true;
        BPTFLSH_BR.rp.order = "DATE,CAP_NO,JRNNO";
        IBS.STARTBR(SCCGWA, BPRFLSH, this, BPTFLSH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFLSH() throws IOException,SQLException,Exception {
        BPTFLSH_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRFLSH, this, BPTFLSH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFLSH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFLSH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFLSH() throws IOException,SQLException,Exception {
        BPTFLSH_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTFLSH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFLSH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFLSH = ");
            CEP.TRC(SCCGWA, BPCRFLSH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
