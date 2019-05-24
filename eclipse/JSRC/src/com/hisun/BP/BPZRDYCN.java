package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDYCN {
    DBParm BPTDYCNT_RD;
    boolean pgmRtn = false;
    String TBL_BPTDYCNT = "BPTDYCNT";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRDYCNT BPRDYCNT = new BPRDYCNT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCRDYCN BPCRDYCN;
    BPRDYCNT BPRDYCN1;
    public void MP(SCCGWA SCCGWA, BPCRDYCN BPCRDYCN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDYCN = BPCRDYCN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRDYCN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDYCN.RC);
        BPRDYCN1 = (BPRDYCNT) BPCRDYCN.INFO.POINTER;
        IBS.init(SCCGWA, BPRDYCNT);
        IBS.CLONE(SCCGWA, BPRDYCN1, BPRDYCNT);
        CEP.TRC(SCCGWA, BPRDYCNT);
        CEP.TRC(SCCGWA, BPCRDYCN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRDYCN.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDYCN.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDYCN.INFO.FUNC == 'U') {
            B030_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDYCN.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRDYCN.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRDYCN.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, BPRDYCNT, BPRDYCN1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDYCNT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDYCNT_UPD();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDYCNT();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDYCNT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDYCNT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTDYCNT() throws IOException,SQLException,Exception {
        BPTDYCNT_RD = new DBParm();
        BPTDYCNT_RD.TableName = "BPTDYCNT";
        IBS.READ(SCCGWA, BPRDYCNT, BPTDYCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDYCN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDYCN.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDYCNT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTDYCNT() throws IOException,SQLException,Exception {
        BPTDYCNT_RD = new DBParm();
        BPTDYCNT_RD.TableName = "BPTDYCNT";
        IBS.WRITE(SCCGWA, BPRDYCNT, BPTDYCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDYCN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRDYCN.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDYCNT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTDYCNT_UPD() throws IOException,SQLException,Exception {
        BPTDYCNT_RD = new DBParm();
        BPTDYCNT_RD.TableName = "BPTDYCNT";
        BPTDYCNT_RD.upd = true;
        IBS.READ(SCCGWA, BPRDYCNT, BPTDYCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRDYCN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRDYCN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDYCNT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTDYCNT() throws IOException,SQLException,Exception {
        BPTDYCNT_RD = new DBParm();
        BPTDYCNT_RD.TableName = "BPTDYCNT";
        IBS.REWRITE(SCCGWA, BPRDYCNT, BPTDYCNT_RD);
    }
    public void T000_DELETE_BPTDYCNT() throws IOException,SQLException,Exception {
        BPTDYCNT_RD = new DBParm();
        BPTDYCNT_RD.TableName = "BPTDYCNT";
        IBS.DELETE(SCCGWA, BPRDYCNT, BPTDYCNT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRDYCN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRDYCN = ");
            CEP.TRC(SCCGWA, BPCRDYCN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
