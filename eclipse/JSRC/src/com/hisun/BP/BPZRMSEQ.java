package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMSEQ {
    DBParm BPTSEQ_RD;
    boolean pgmRtn = false;
    BPZRMSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZRMSEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSEQ BPRSEQ = new BPRSEQ();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRMSEQ BPCRMSEQ;
    BPRSEQ BPRSEQL;
    public void MP(SCCGWA SCCGWA, BPCRMSEQ BPCRMSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMSEQ = BPCRMSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSEQL = (BPRSEQ) BPCRMSEQ.PTR;
        IBS.init(SCCGWA, BPRSEQ);
        IBS.CLONE(SCCGWA, BPRSEQL, BPRSEQ);
        IBS.init(SCCGWA, BPCRMSEQ.RC);
        CEP.TRC(SCCGWA, BPCRMSEQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
        CEP.TRC(SCCGWA, BPRSEQ.DESC);
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPRSEQ.OLD_SEQ_NO);
        if (BPCRMSEQ.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMSEQ.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMSEQ.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMSEQ.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMSEQ.FUNC == 'Q') {
            B050_INQURE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRMSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRSEQ, BPRSEQL);
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTSEQ();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPTSEQ();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTSEQ();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTSEQ();
        if (pgmRtn) return;
    }
    public void B050_INQURE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSEQ();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTSEQ() throws IOException,SQLException,Exception {
        BPTSEQ_RD = new DBParm();
        BPTSEQ_RD.TableName = "BPTSEQ";
        BPTSEQ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRSEQ, BPTSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRMSEQ.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTSEQ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_BPTSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
        BPTSEQ_RD = new DBParm();
        BPTSEQ_RD.TableName = "BPTSEQ";
        BPTSEQ_RD.upd = true;
        IBS.READ(SCCGWA, BPRSEQ, BPTSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMSEQ.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, BPCRMSEQ.RETURN_INFO);
    }
    public void T000_REWRITE_BPTSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        BPTSEQ_RD = new DBParm();
        BPTSEQ_RD.TableName = "BPTSEQ";
        IBS.REWRITE(SCCGWA, BPRSEQ, BPTSEQ_RD);
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
    }
    public void T000_DELETE_BPTSEQ() throws IOException,SQLException,Exception {
        BPTSEQ_RD = new DBParm();
        BPTSEQ_RD.TableName = "BPTSEQ";
        IBS.DELETE(SCCGWA, BPRSEQ, BPTSEQ_RD);
    }
    public void T000_READ_BPTSEQ() throws IOException,SQLException,Exception {
        BPTSEQ_RD = new DBParm();
        BPTSEQ_RD.TableName = "BPTSEQ";
        IBS.READ(SCCGWA, BPRSEQ, BPTSEQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMSEQ.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMSEQ.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMSEQ = ");
            CEP.TRC(SCCGWA, BPCRMSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
