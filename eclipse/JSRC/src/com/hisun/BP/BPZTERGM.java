package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTERGM {
    DBParm BPTERGR_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTERGM";
    String K_TBL_ERGR = "BPTERGR ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRERGR BPRERGR = new BPRERGR();
    SCCGWA SCCGWA;
    BPCTERGM BPCTERGM;
    BPRERGR BPRERGR1;
    public void MP(SCCGWA SCCGWA, BPCTERGM BPCTERGM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTERGM = BPCTERGM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTERGM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRERGR1 = (BPRERGR) BPCTERGM.INFO.POINTER;
        IBS.init(SCCGWA, BPRERGR);
        IBS.CLONE(SCCGWA, BPRERGR1, BPRERGR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTERGM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTERGM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTERGM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTERGM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTERGM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTERGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRERGR, BPRERGR1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTERGR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRERGR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRERGR.KEY.HDAY_FLG);
        CEP.TRC(SCCGWA, BPRERGR.KEY.SQN);
        CEP.TRC(SCCGWA, BPRERGR.KEY.EXP_DT);
        CEP.TRC(SCCGWA, BPRERGR.KEY.EXP_TM);
        T000_READ_BPTERGR_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTERGR();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTERGR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTERGR();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTERGR() throws IOException,SQLException,Exception {
        BPTERGR_RD = new DBParm();
        BPTERGR_RD.TableName = "BPTERGR";
        IBS.READ(SCCGWA, BPRERGR, BPTERGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTERGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTERGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTERGR() throws IOException,SQLException,Exception {
        BPTERGR_RD = new DBParm();
        BPTERGR_RD.TableName = "BPTERGR";
        BPTERGR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRERGR, BPTERGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTERGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTERGM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ERGR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTERGR_UPD() throws IOException,SQLException,Exception {
        BPTERGR_RD = new DBParm();
        BPTERGR_RD.TableName = "BPTERGR";
        BPTERGR_RD.upd = true;
        IBS.READ(SCCGWA, BPRERGR, BPTERGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTERGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTERGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTERGR() throws IOException,SQLException,Exception {
        BPTERGR_RD = new DBParm();
        BPTERGR_RD.TableName = "BPTERGR";
        IBS.REWRITE(SCCGWA, BPRERGR, BPTERGR_RD);
    }
    public void T000_DELETE_BPTERGR() throws IOException,SQLException,Exception {
        BPTERGR_RD = new DBParm();
        BPTERGR_RD.TableName = "BPTERGR";
        IBS.DELETE(SCCGWA, BPRERGR, BPTERGR_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTERGM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTERGM = ");
            CEP.TRC(SCCGWA, BPCTERGM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
