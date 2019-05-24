package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTBV {
    DBParm BPTTBV_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTBV ";
    String K_TBL_TBV = "BPTTBV  ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTBV BPRTBV = new BPRTBV();
    SCCGWA SCCGWA;
    BPCRTBV BPCRTBV;
    BPRTBV BPRTBL;
    public void MP(SCCGWA SCCGWA, BPCRTBV BPCRTBV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTBV = BPCRTBV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTBV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTBL = (BPRTBV) BPCRTBV.INFO.POINTER;
        IBS.init(SCCGWA, BPRTBV);
        IBS.CLONE(SCCGWA, BPRTBL, BPRTBV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBV.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBV.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBV.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBV.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBV.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTBV.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTBV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRTBV.RC);
        IBS.CLONE(SCCGWA, BPRTBV, BPRTBL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTBV();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBV_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTBV();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBV();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTBV();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTBV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTBV.KEY.TLR);
        BPTTBV_RD = new DBParm();
        BPTTBV_RD.TableName = "BPTTBV";
        IBS.READ(SCCGWA, BPRTBV, BPTTBV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBV.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBV_REC_NOTFND, BPCRTBV.RC);
        } else {
        }
    }
    public void T000_WRITE_BPTTBV() throws IOException,SQLException,Exception {
        BPTTBV_RD = new DBParm();
        BPTTBV_RD.TableName = "BPTTBV";
        BPTTBV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTBV, BPTTBV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTBV.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TBV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTBV_UPD() throws IOException,SQLException,Exception {
        BPTTBV_RD = new DBParm();
        BPTTBV_RD.TableName = "BPTTBV";
        BPTTBV_RD.upd = true;
        IBS.READ(SCCGWA, BPRTBV, BPTTBV_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBV.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBV.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBV_REC_NOTFND, BPCRTBV.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTTBV() throws IOException,SQLException,Exception {
        BPTTBV_RD = new DBParm();
        BPTTBV_RD.TableName = "BPTTBV";
        IBS.REWRITE(SCCGWA, BPRTBV, BPTTBV_RD);
    }
    public void T000_DELETE_BPTTBV() throws IOException,SQLException,Exception {
        BPTTBV_RD = new DBParm();
        BPTTBV_RD.TableName = "BPTTBV";
        IBS.DELETE(SCCGWA, BPRTBV, BPTTBV_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTBV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTBV = ");
            CEP.TRC(SCCGWA, BPCRTBV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
