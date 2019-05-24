package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMORR {
    DBParm BPTORGR_RD;
    boolean pgmRtn = false;
    String K_TBL_ORGR = "BPTORGR ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_ORGR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGR BPRORGR = new BPRORGR();
    SCCGWA SCCGWA;
    BPCRMORR BPCRMORR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRORGR BPRORGRL;
    public void MP(SCCGWA SCCGWA, BPCRMORR BPCRMORR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMORR = BPCRMORR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMORR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGRL = (BPRORGR) BPCRMORR.INFO.POINTER;
        IBS.init(SCCGWA, BPCRMORR.RC);
        IBS.init(SCCGWA, BPRORGR);
        IBS.CLONE(SCCGWA, BPRORGRL, BPRORGR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMORR.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMORR.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMORR.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMORR.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMORR.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRMORR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGR, BPRORGRL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTORGR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGR_UPD();
        if (pgmRtn) return;
        if (WS_TBL_ORGR_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND, BPCRMORR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRMORR.INFO.CHECK_IND == '1') {
            T000_READ_BPTORGR_REVERSE();
            if (pgmRtn) return;
        } else {
            T000_READ_BPTORGR();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTORGR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTORGR();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTORGR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRORGR);
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        IBS.READ(SCCGWA, BPRORGR, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMORR.RETURN_INFO = 'F';
            WS_TBL_ORGR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMORR.RETURN_INFO = 'N';
            WS_TBL_ORGR_FLAG = 'D';
        } else {
        }
    }
    public void T000_READ_BPTORGR_REVERSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRORGR);
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
            + "AND REL_BR = :BPRORGR.REL_BR";
        IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMORR.RETURN_INFO = 'F';
            WS_TBL_ORGR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMORR.RETURN_INFO = 'N';
            WS_TBL_ORGR_FLAG = 'D';
        } else {
        }
    }
    public void T000_WRITE_BPTORGR() throws IOException,SQLException,Exception {
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        BPTORGR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRORGR, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMORR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRMORR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ORGR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTORGR_UPD() throws IOException,SQLException,Exception {
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        BPTORGR_RD.upd = true;
        IBS.READ(SCCGWA, BPRORGR, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGR_FLAG = 'D';
            BPCRMORR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTORGR() throws IOException,SQLException,Exception {
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        IBS.REWRITE(SCCGWA, BPRORGR, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMORR.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ORGR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BPTORGR() throws IOException,SQLException,Exception {
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        IBS.DELETE(SCCGWA, BPRORGR, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMORR.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ORGR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMORR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMORR = ");
            CEP.TRC(SCCGWA, BPCRMORR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
