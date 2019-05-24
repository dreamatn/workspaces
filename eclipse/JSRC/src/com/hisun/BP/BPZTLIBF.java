package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTLIBF {
    DBParm BPTCLIB_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTLIBF";
    String K_TBL_CLIB = "BPTCLIB ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_CLIB_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCCGWA SCCGWA;
    BPRCLIB BPRCLIB1;
    BPCTLIBF BPCTLIBF;
    public void MP(SCCGWA SCCGWA, BPCTLIBF BPCTLIBF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTLIBF = BPCTLIBF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTLIBF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCLIB1 = (BPRCLIB) BPCTLIBF.POINTER;
        IBS.init(SCCGWA, BPCTLIBF.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCLIB1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCLIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTLIBF.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLIBF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLIBF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLIBF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLIBF.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTLIBF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCLIB, BPRCLIB1);
        CEP.TRC(SCCGWA, BPRCLIB);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCLIB_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
        T000_READ_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTCLIB();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        IBS.READ(SCCGWA, BPRCLIB, BPTCLIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLIBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLIBF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLIB_NOTFND, BPCTLIBF.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTLIBF.RC);
    }
    public void T000_DELETE_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        IBS.DELETE(SCCGWA, BPRCLIB, BPTCLIB_RD);
    }
    public void T000_WRITE_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        BPTCLIB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCLIB, BPTCLIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLIBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTLIBF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CLIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCLIB_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        BPTCLIB_RD.upd = true;
        IBS.READ(SCCGWA, BPRCLIB, BPTCLIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLIBF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLIBF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLIB_NOTFND, BPCTLIBF.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        IBS.REWRITE(SCCGWA, BPRCLIB, BPTCLIB_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTLIBF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTLIBF = ");
            CEP.TRC(SCCGWA, BPCTLIBF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
