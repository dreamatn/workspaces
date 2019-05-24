package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRGRPA {
    DBParm BPTGRPLA_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRGRPA";
    String K_TBL_GRPLA = "BPTGRPLA";
    char WS_TBL_GRPLA_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRGRPLA BPRGRPLA = new BPRGRPLA();
    SCCGWA SCCGWA;
    BPCRGRPA BPCRGRPA;
    BPRGRPLA BPRLRPLA;
    public void MP(SCCGWA SCCGWA, BPCRGRPA BPCRGRPA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRGRPA = BPCRGRPA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRGRPA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLRPLA = (BPRGRPLA) BPCRGRPA.INFO.POINTER;
        IBS.init(SCCGWA, BPRGRPLA);
        IBS.CLONE(SCCGWA, BPRLRPLA, BPRGRPLA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRGRPLA.KEY.ASS_TYP);
        CEP.TRC(SCCGWA, BPRGRPLA.KEY.ASS_ID);
        CEP.TRC(SCCGWA, BPRGRPLA.KEY.ATH_TYP);
        if (BPCRGRPA.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPA.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPA.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPA.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPA.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRGRPA.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRGRPA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRGRPLA, BPRLRPLA);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTGRPLA();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTGRPLA_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTGRPLA();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTGRPLA();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTGRPLA();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTGRPLA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "RRRRRRRR");
        CEP.TRC(SCCGWA, BPRGRPLA.KEY.ASS_TYP);
        CEP.TRC(SCCGWA, BPRGRPLA.KEY.ASS_ID);
        CEP.TRC(SCCGWA, BPRGRPLA.KEY.ATH_TYP);
        BPTGRPLA_RD = new DBParm();
        BPTGRPLA_RD.TableName = "BPTGRPLA";
        IBS.READ(SCCGWA, BPRGRPLA, BPTGRPLA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRPA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTGRPLA() throws IOException,SQLException,Exception {
        BPTGRPLA_RD = new DBParm();
        BPTGRPLA_RD.TableName = "BPTGRPLA";
        BPTGRPLA_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRGRPLA, BPTGRPLA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRGRPA.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_GRPLA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTGRPLA_UPD() throws IOException,SQLException,Exception {
        BPTGRPLA_RD = new DBParm();
        BPTGRPLA_RD.TableName = "BPTGRPLA";
        BPTGRPLA_RD.upd = true;
        IBS.READ(SCCGWA, BPRGRPLA, BPTGRPLA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRPA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTGRPLA() throws IOException,SQLException,Exception {
        BPTGRPLA_RD = new DBParm();
        BPTGRPLA_RD.TableName = "BPTGRPLA";
        IBS.REWRITE(SCCGWA, BPRGRPLA, BPTGRPLA_RD);
    }
    public void T000_DELETE_BPTGRPLA() throws IOException,SQLException,Exception {
        BPTGRPLA_RD = new DBParm();
        BPTGRPLA_RD.TableName = "BPTGRPLA";
        IBS.DELETE(SCCGWA, BPRGRPLA, BPTGRPLA_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRGRPA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRGRPA = ");
            CEP.TRC(SCCGWA, BPCRGRPA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
