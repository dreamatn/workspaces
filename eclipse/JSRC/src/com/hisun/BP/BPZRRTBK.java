package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRRTBK {
    DBParm BPTRTBK_RD;
    brParm BPTRTBK_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTRTBK = "BPTRTBK";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRRTBK BPRRTBK = new BPRRTBK();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCRRTBK BPCRRTBK;
    BPRRTBK BPRRTBKL;
    public void MP(SCCGWA SCCGWA, BPCRRTBK BPCRRTBK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRTBK = BPCRRTBK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRRTBK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTBK.RC);
        BPRRTBKL = (BPRRTBK) BPCRRTBK.INFO.POINTER;
        IBS.init(SCCGWA, BPRRTBK);
        IBS.CLONE(SCCGWA, BPRRTBKL, BPRRTBK);
        CEP.TRC(SCCGWA, BPRRTBK);
        CEP.TRC(SCCGWA, BPCRRTBK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRRTBK.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.FUNC == 'U') {
            B030_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRRTBK.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, BPRRTBK, BPRRTBKL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTRTBK();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRTBK_UPD();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTRTBK();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTRTBK();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTRTBK();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRRTBK.INFO.OPT == 'S') {
            T000_STARTBR_BPTRTBK();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.OPT == 'N') {
            T000_READNEXT_BPTRTBK();
            if (pgmRtn) return;
        } else if (BPCRRTBK.INFO.OPT == 'E') {
            T000_ENDBR_BPTRTBK();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRRTBK.INFO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTRTBK() throws IOException,SQLException,Exception {
        BPTRTBK_RD = new DBParm();
        BPTRTBK_RD.TableName = "BPTRTBK";
        BPTRTBK_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRRTBK, BPTRTBK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTBK.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTBK.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTBK;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTRTBK() throws IOException,SQLException,Exception {
        BPTRTBK_RD = new DBParm();
        BPTRTBK_RD.TableName = "BPTRTBK";
        IBS.WRITE(SCCGWA, BPRRTBK, BPTRTBK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTBK.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRRTBK.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTBK;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTRTBK_UPD() throws IOException,SQLException,Exception {
        BPTRTBK_RD = new DBParm();
        BPTRTBK_RD.TableName = "BPTRTBK";
        BPTRTBK_RD.upd = true;
        IBS.READ(SCCGWA, BPRRTBK, BPTRTBK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTBK.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTBK.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTBK;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTRTBK() throws IOException,SQLException,Exception {
        BPTRTBK_RD = new DBParm();
        BPTRTBK_RD.TableName = "BPTRTBK";
        IBS.REWRITE(SCCGWA, BPRRTBK, BPTRTBK_RD);
    }
    public void T000_DELETE_BPTRTBK() throws IOException,SQLException,Exception {
        BPTRTBK_RD = new DBParm();
        BPTRTBK_RD.TableName = "BPTRTBK";
        IBS.DELETE(SCCGWA, BPRRTBK, BPTRTBK_RD);
    }
    public void T000_STARTBR_BPTRTBK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPRRTBK.KEY.DATE);
        CEP.TRC(SCCGWA, BPRRTBK.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRRTBK.KEY.BATCH_SEQ);
        if (BPRRTBK.KEY.DATE == 0 
            && BPRRTBK.KEY.JRNNO == 0 
            && BPRRTBK.KEY.BATCH_SEQ == 0) {
            BPTRTBK_BR.rp = new DBParm();
            BPTRTBK_BR.rp.TableName = "BPTRTBK";
            IBS.STARTBR(SCCGWA, BPRRTBK, BPTRTBK_BR);
        } else {
            if (BPRRTBK.KEY.DATE != 0 
                && BPRRTBK.KEY.JRNNO == 0 
                && BPRRTBK.KEY.BATCH_SEQ == 0) {
                BPTRTBK_BR.rp = new DBParm();
                BPTRTBK_BR.rp.TableName = "BPTRTBK";
                BPTRTBK_BR.rp.where = "'DATE' = :BPRRTBK.KEY.DATE";
                BPTRTBK_BR.rp.order = "JRNNO DESC";
                IBS.STARTBR(SCCGWA, BPRRTBK, this, BPTRTBK_BR);
            }
            if (BPRRTBK.KEY.DATE != 0 
                && BPRRTBK.RATE_ID.trim().length() > 0 
                && BPRRTBK.FR_DT != 0) {
                BPTRTBK_RD = new DBParm();
                BPTRTBK_RD.TableName = "BPTRTBK";
                BPTRTBK_RD.where = "'DATE' = :BPRRTBK.KEY.DATE "
                    + "AND FR_DT = :BPRRTBK.FR_DT "
                    + "AND RATE_ID = :BPRRTBK.RATE_ID";
                BPTRTBK_RD.fst = true;
                BPTRTBK_RD.order = "JRNNO DESC";
                IBS.READ(SCCGWA, BPRRTBK, this, BPTRTBK_RD);
            }
            if (BPRRTBK.KEY.DATE == 0 
                && BPRRTBK.KEY.JRNNO != 0 
                && BPRRTBK.KEY.BATCH_SEQ == 0) {
                BPTRTBK_BR.rp = new DBParm();
                BPTRTBK_BR.rp.TableName = "BPTRTBK";
                BPTRTBK_BR.rp.where = "JRNNO = :BPRRTBK.KEY.JRNNO";
                BPTRTBK_BR.rp.order = "BATCH_SEQ";
                IBS.STARTBR(SCCGWA, BPRRTBK, this, BPTRTBK_BR);
            }
            if (BPRRTBK.KEY.DATE != 0 
                && BPRRTBK.KEY.JRNNO != 0 
                && BPRRTBK.KEY.BATCH_SEQ == 0) {
                BPTRTBK_BR.rp = new DBParm();
                BPTRTBK_BR.rp.TableName = "BPTRTBK";
                BPTRTBK_BR.rp.where = "'DATE' = :BPRRTBK.KEY.DATE "
                    + "AND JRNNO = :BPRRTBK.KEY.JRNNO";
                IBS.STARTBR(SCCGWA, BPRRTBK, this, BPTRTBK_BR);
            }
            if (BPRRTBK.KEY.DATE != 0 
                && BPRRTBK.KEY.JRNNO != 0 
                && BPRRTBK.KEY.BATCH_SEQ != 0) {
                CEP.TRC(SCCGWA, "ALL ");
                BPTRTBK_BR.rp = new DBParm();
                BPTRTBK_BR.rp.TableName = "BPTRTBK";
                BPTRTBK_BR.rp.where = "'DATE' = :BPRRTBK.KEY.DATE "
                    + "AND JRNNO = :BPRRTBK.KEY.JRNNO "
                    + "AND BATCH_SEQ = :BPRRTBK.KEY.BATCH_SEQ";
                IBS.STARTBR(SCCGWA, BPRRTBK, this, BPTRTBK_BR);
            }
            if (BPRRTBK.KEY.DATE != 0 
                && BPRRTBK.KEY.JRNNO == 0 
                && BPRRTBK.KEY.BATCH_SEQ == 0 
                && BPRRTBK.RATE_ID.trim().length() > 0) {
                BPTRTBK_BR.rp = new DBParm();
                BPTRTBK_BR.rp.TableName = "BPTRTBK";
                BPTRTBK_BR.rp.where = "'DATE' = :BPRRTBK.KEY.DATE "
                    + "AND RATE_ID = :BPRRTBK.RATE_ID";
                BPTRTBK_BR.rp.order = "JRNNO DESC";
                IBS.STARTBR(SCCGWA, BPRRTBK, this, BPTRTBK_BR);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTBK.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTBK.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTBK;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRRTBK.RETURN_INFO);
    }
    public void T000_READNEXT_BPTRTBK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTRTBK_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRRTBK, this, BPTRTBK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRRTBK.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRRTBK.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTRTBK;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTRTBK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRTBK_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRTBK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRRTBK = ");
            CEP.TRC(SCCGWA, BPCRRTBK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
