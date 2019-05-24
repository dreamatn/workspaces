package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

import java.io.IOException;
import java.sql.SQLException;

public class TDZBPU05 {
    DBParm TDTBSP05_RD;
    boolean pgmRtn = false;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    TDRBSP05 TDRBSP05 = new TDRBSP05();
    SCCGWA SCCGWA;
    SCCRWBSP SCCRWBSP;
    TDRBSP05 TDRBSP15;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZBPU05 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        TDRBSP15 = (PSRBSP01) SCCRWBSP.INFO.PTR;
        IBS.init(SCCGWA, TDRBSP05);
        CEP.TRC(SCCGWA, SCCRWBSP.INFO.LEN);
        IBS.CLONE(SCCGWA, TDRBSP15, TDRBSP05);
        SCCRWBSP.RC.RC_MMO = "TD";
        SCCRWBSP.RC.RC_CODE = 0;
        SCCRWBSP.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBSP05.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, TDRBSP05.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, TDRBSP05.KEY.BAT_NO);
        if (SCCRWBSP.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, TDRBSP05, TDRBSP15);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BSP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BSP();
        if (pgmRtn) return;
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        TDTBSP05_RD = new DBParm();
        TDTBSP05_RD.TableName = "TDTBSP05";
        IBS.READ(SCCGWA, TDRBSP05, TDTBSP05_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BSP_REC_NOTFND, SCCRWBSP.RC);
            SCCRWBSP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        TDTBSP05_RD = new DBParm();
        TDTBSP05_RD.TableName = "TDTBSP05";
        IBS.WRITE(SCCGWA, TDRBSP05, TDTBSP05_RD);
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        TDTBSP05_RD = new DBParm();
        TDTBSP05_RD.TableName = "TDTBSP05";
        TDTBSP05_RD.upd = true;
        IBS.READ(SCCGWA, TDRBSP05, TDTBSP05_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BSP_REC_NOTFND, SCCRWBSP.RC);
            SCCRWBSP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        TDTBSP05_RD = new DBParm();
        TDTBSP05_RD.TableName = "TDTBSP05";
        IBS.REWRITE(SCCGWA, TDRBSP05, TDTBSP05_RD);
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        TDTBSP05_RD = new DBParm();
        TDTBSP05_RD.TableName = "TDTBSP05";
        IBS.DELETE(SCCGWA, TDRBSP05, TDTBSP05_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_BSP_REC_NOTFND, SCCRWBSP.RC);
            SCCRWBSP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
