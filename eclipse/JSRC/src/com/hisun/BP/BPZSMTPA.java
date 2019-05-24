package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMTPA {
    DBParm BPTMTPA_RD;
    boolean pgmRtn = false;
    String K_TBL_MTPA = "BPTMTPA ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_MTPA_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRMTPA BPRMTPA = new BPRMTPA();
    SCCGWA SCCGWA;
    BPCSMTPA BPCSMTPA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRMTPA BPRMTPA1;
    public void MP(SCCGWA SCCGWA, BPCSMTPA BPCSMTPA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMTPA = BPCSMTPA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMTPA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRMTPA1 = (BPRMTPA) BPCSMTPA.INFO.POINTER;
        IBS.init(SCCGWA, BPCSMTPA.RC);
        IBS.init(SCCGWA, BPRMTPA);
        IBS.CLONE(SCCGWA, BPRMTPA1, BPRMTPA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSMTPA.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSMTPA.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSMTPA.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSMTPA.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSMTPA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRMTPA, BPRMTPA1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEV3");
        T000_WRITE_BPTMTPA();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTMTPA_UPD();
        if (pgmRtn) return;
        if (WS_TBL_MTPA_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSMTPA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTMTPA();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTMTPA();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTMTPA() throws IOException,SQLException,Exception {
        BPTMTPA_RD = new DBParm();
        BPTMTPA_RD.TableName = "BPTMTPA";
        BPTMTPA_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRMTPA, BPTMTPA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSMTPA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR101);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_MTPA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTMTPA_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRMTPA.KEY.MT_BR);
        CEP.TRC(SCCGWA, BPRMTPA.KEY.APP_FLG);
        BPTMTPA_RD = new DBParm();
        BPTMTPA_RD.TableName = "BPTMTPA";
        BPTMTPA_RD.where = "MT_BR = :BPRMTPA.KEY.MT_BR "
            + "AND APP_FLG = :BPRMTPA.KEY.APP_FLG";
        BPTMTPA_RD.upd = true;
        IBS.READ(SCCGWA, BPRMTPA, this, BPTMTPA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_MTPA_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_MTPA_FLAG = 'D';
            BPCSMTPA.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTMTPA() throws IOException,SQLException,Exception {
        BPTMTPA_RD = new DBParm();
        BPTMTPA_RD.TableName = "BPTMTPA";
        IBS.REWRITE(SCCGWA, BPRMTPA, BPTMTPA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSMTPA.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_MTPA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BPTMTPA() throws IOException,SQLException,Exception {
        BPTMTPA_RD = new DBParm();
        BPTMTPA_RD.TableName = "BPTMTPA";
        IBS.DELETE(SCCGWA, BPRMTPA, BPTMTPA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSMTPA.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_MTPA;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSMTPA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCSMTPA = ");
            CEP.TRC(SCCGWA, BPCSMTPA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
