package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTBRIS {
    DBParm BPTBRIS_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTBRIS";
    String K_TBL_BRIS = "BPTBRIS ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BRIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBRIS BPRBRIS = new BPRBRIS();
    SCCGWA SCCGWA;
    BPRBRIS BPRBRIS1;
    BPCTBRIS BPCTBRIS;
    public void MP(SCCGWA SCCGWA, BPCTBRIS BPCTBRIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTBRIS = BPCTBRIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTBRIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBRIS1 = (BPRBRIS) BPCTBRIS.POINTER;
        IBS.init(SCCGWA, BPCTBRIS.RC);
        IBS.CLONE(SCCGWA, BPRBRIS1, BPRBRIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTBRIS.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBRIS.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBRIS.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBRIS.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBRIS.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTBRIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRBRIS, BPRBRIS);
        CEP.TRC(SCCGWA, BPRBRIS);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBRIS();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBRIS_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBRIS();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
        CEP.TRC(SCCGWA, BPRBRIS.KEY.LMT_CCY);
        T000_READ_BPTBRIS();
        if (pgmRtn) return;
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBRIS();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        IBS.READ(SCCGWA, BPRBRIS, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBRIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTBRIS.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BRIS_NOTFND, BPCTBRIS.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTBRIS.RC);
    }
    public void T000_DELETE_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        IBS.DELETE(SCCGWA, BPRBRIS, BPTBRIS_RD);
    }
    public void T000_WRITE_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBRIS, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBRIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTBRIS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBRIS_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRBRIS, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBRIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTBRIS.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BRIS_NOTFND, BPCTBRIS.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        IBS.REWRITE(SCCGWA, BPRBRIS, BPTBRIS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
