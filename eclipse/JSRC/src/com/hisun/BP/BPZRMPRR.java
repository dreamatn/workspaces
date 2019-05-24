package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMPRR {
    DBParm BPTPARR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_LEN = 0;
    BPRPARR BPRPARR = new BPRPARR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRMPRR BPCRMPRR;
    BPRPARR BPRPARRL;
    public void MP(SCCGWA SCCGWA, BPCRMPRR BPCRMPRR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMPRR = BPCRMPRR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMPRR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRPARRL = (BPRPARR) BPCRMPRR.PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMPRR.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRR.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRR.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRR.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRR.FUNC == 'Q') {
            B050_INQURE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRMPRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPARR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPTPARR();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPARR();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPARR();
        if (pgmRtn) return;
    }
    public void B050_INQURE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPARR();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTPARR() throws IOException,SQLException,Exception {
        WS_LEN = 62;
        IBS.CLONE(SCCGWA, BPRPARRL, BPRPARR);
        BPTPARR_RD = new DBParm();
        BPTPARR_RD.TableName = "BPTPARR";
        IBS.WRITE(SCCGWA, BPRPARR, BPTPARR_RD);
        BPCRMPRR.RETURN_INFO = 'F';
    }
    public void T000_READUPD_BPTPARR() throws IOException,SQLException,Exception {
        WS_LEN = 16;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARRL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARR.KEY);
        BPTPARR_RD = new DBParm();
        BPTPARR_RD.TableName = "BPTPARR";
        BPTPARR_RD.upd = true;
        IBS.READ(SCCGWA, BPRPARR, BPTPARR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRR.RETURN_INFO = 'N';
        } else {
            BPCRMPRR.RETURN_INFO = 'F';
            WS_LEN = 62;
            IBS.CLONE(SCCGWA, BPRPARR, BPRPARRL);
        }
    }
    public void T000_REWRITE_BPTPARR() throws IOException,SQLException,Exception {
        WS_LEN = 62;
        IBS.CLONE(SCCGWA, BPRPARRL, BPRPARR);
        BPTPARR_RD = new DBParm();
        BPTPARR_RD.TableName = "BPTPARR";
        IBS.REWRITE(SCCGWA, BPRPARR, BPTPARR_RD);
        BPCRMPRR.RETURN_INFO = 'F';
    }
    public void T000_DELETE_BPTPARR() throws IOException,SQLException,Exception {
        WS_LEN = 16;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARRL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARR.KEY);
        BPTPARR_RD = new DBParm();
        BPTPARR_RD.TableName = "BPTPARR";
        IBS.DELETE(SCCGWA, BPRPARR, BPTPARR_RD);
        BPCRMPRR.RETURN_INFO = 'F';
    }
    public void T000_READ_BPTPARR() throws IOException,SQLException,Exception {
        WS_LEN = 16;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPARRL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPARR.KEY);
        BPTPARR_RD = new DBParm();
        BPTPARR_RD.TableName = "BPTPARR";
        IBS.READ(SCCGWA, BPRPARR, BPTPARR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRR.RETURN_INFO = 'N';
        } else {
            BPCRMPRR.RETURN_INFO = 'F';
            WS_LEN = 62;
            IBS.CLONE(SCCGWA, BPRPARR, BPRPARRL);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMPRR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMPRR = ");
            CEP.TRC(SCCGWA, BPCRMPRR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
