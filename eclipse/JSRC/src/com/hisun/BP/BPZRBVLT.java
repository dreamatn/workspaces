package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBVLT {
    DBParm BPTBVLT_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBVLT";
    String K_TBL_BVLT = "BPTBVLT ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BVLT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBVLT BPRBVLT = new BPRBVLT();
    SCCGWA SCCGWA;
    BPRBVLT BPRBVLT1;
    BPCRBVLT BPCRBVLT;
    public void MP(SCCGWA SCCGWA, BPCRBVLT BPCRBVLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBVLT = BPCRBVLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBVLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBVLT1 = (BPRBVLT) BPCRBVLT.POINTER;
        IBS.init(SCCGWA, BPCRBVLT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBVLT1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRBVLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBVLT.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLT.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLT.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLT.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBVLT.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBVLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRBVLT, BPRBVLT1);
        CEP.TRC(SCCGWA, BPRBVLT);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBVLT();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBVLT_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBVLT();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BR);
        T000_READ_BPTBVLT();
        if (pgmRtn) return;
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBVLT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        IBS.READ(SCCGWA, BPRBVLT, BPTBVLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLT.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BVLT_NOTFND, BPCRBVLT.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCRBVLT.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCRBVLT.RC);
    }
    public void T000_DELETE_BPTBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        IBS.DELETE(SCCGWA, BPRBVLT, BPTBVLT_RD);
    }
    public void T000_WRITE_BPTBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        BPTBVLT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBVLT, BPTBVLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBVLT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BVLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBVLT_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BR);
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        BPTBVLT_RD.upd = true;
        IBS.READ(SCCGWA, BPRBVLT, BPTBVLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBVLT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBVLT.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BVLT_NOTFND, BPCRBVLT.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        IBS.REWRITE(SCCGWA, BPRBVLT, BPTBVLT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBVLT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBVLT = ");
            CEP.TRC(SCCGWA, BPCRBVLT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
