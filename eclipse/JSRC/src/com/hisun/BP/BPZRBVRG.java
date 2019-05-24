package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBVRG {
    DBParm BPTBVRG_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBVRG";
    String K_TBL_BVRG = "BPTBVRG ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_BVRG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBVRG BPRBVRG = new BPRBVRG();
    SCCGWA SCCGWA;
    BPRBVRG BPRBVRG1;
    BPCTBVRG BPCTBVRG;
    public void MP(SCCGWA SCCGWA, BPCTBVRG BPCTBVRG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTBVRG = BPCTBVRG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBVRG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBVRG1 = (BPRBVRG) BPCTBVRG.POINTER;
        IBS.init(SCCGWA, BPCTBVRG.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBVRG1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRBVRG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTBVRG.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBVRG.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBVRG.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBVRG.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTBVRG.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTBVRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRBVRG, BPRBVRG1);
        CEP.TRC(SCCGWA, BPRBVRG);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBVRG();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBVRG_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBVRG();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        CEP.TRC(SCCGWA, BPRBVRG.KEY.BR);
        T000_READ_BPTBVRG();
        if (pgmRtn) return;
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBVRG();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBVRG() throws IOException,SQLException,Exception {
        BPTBVRG_RD = new DBParm();
        BPTBVRG_RD.TableName = "BPTBVRG";
        IBS.READ(SCCGWA, BPRBVRG, BPTBVRG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBVRG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTBVRG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BVRG_NOTFND, BPCTBVRG.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTBVRG.RC);
    }
    public void T000_DELETE_BPTBVRG() throws IOException,SQLException,Exception {
        BPTBVRG_RD = new DBParm();
        BPTBVRG_RD.TableName = "BPTBVRG";
        IBS.DELETE(SCCGWA, BPRBVRG, BPTBVRG_RD);
    }
    public void T000_WRITE_BPTBVRG() throws IOException,SQLException,Exception {
        BPTBVRG_RD = new DBParm();
        BPTBVRG_RD.TableName = "BPTBVRG";
        BPTBVRG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBVRG, BPTBVRG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBVRG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTBVRG.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BVRG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBVRG_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRBVRG.KEY.BR);
        BPTBVRG_RD = new DBParm();
        BPTBVRG_RD.TableName = "BPTBVRG";
        BPTBVRG_RD.upd = true;
        IBS.READ(SCCGWA, BPRBVRG, BPTBVRG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBVRG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTBVRG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BVRG_NOTFND, BPCTBVRG.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTBVRG() throws IOException,SQLException,Exception {
        BPTBVRG_RD = new DBParm();
        BPTBVRG_RD.TableName = "BPTBVRG";
        IBS.REWRITE(SCCGWA, BPRBVRG, BPTBVRG_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTBVRG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCTBVRG = ");
            CEP.TRC(SCCGWA, BPCTBVRG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
