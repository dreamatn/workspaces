package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZREWAT {
    DBParm BPTEWAT_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZREWAT";
    String K_TBL_EWAT = "BPTEWAT ";
    String WS_TEST_INF = " ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_EWAT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPREWAT BPREWAT = new BPREWAT();
    SCCGWA SCCGWA;
    BPCREWAT BPCREWAT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPREWAT BPREWAT1;
    public void MP(SCCGWA SCCGWA, BPCREWAT BPCREWAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCREWAT = BPCREWAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZREWAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREWAT1 = (BPREWAT) BPCREWAT.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CLONE(SCCGWA, BPREWAT1, BPREWAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCREWAT.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREWAT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREWAT.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREWAT.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREWAT.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCREWAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREWAT, BPREWAT1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTEWAT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEWAT_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEWAT();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTEWAT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEWAT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTEWAT() throws IOException,SQLException,Exception {
        BPTEWAT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEWAT_RD.TableName = "BPTEWAT1";
        else BPTEWAT_RD.TableName = "BPTEWAT2";
        IBS.READ(SCCGWA, BPREWAT, BPTEWAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREWAT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREWAT.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCREWAT.RC);
        } else {
        }
    }
    public void T000_WRITE_BPTEWAT() throws IOException,SQLException,Exception {
        BPTEWAT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEWAT_RD.TableName = "BPTEWAT1";
        else BPTEWAT_RD.TableName = "BPTEWAT2";
        IBS.WRITE(SCCGWA, BPREWAT, BPTEWAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREWAT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCREWAT.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCREWAT.RC);
        } else {
        }
    }
    public void T000_READ_BPTEWAT_UPD() throws IOException,SQLException,Exception {
        BPTEWAT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEWAT_RD.TableName = "BPTEWAT1";
        else BPTEWAT_RD.TableName = "BPTEWAT2";
        BPTEWAT_RD.upd = true;
        IBS.READ(SCCGWA, BPREWAT, BPTEWAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREWAT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREWAT.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCREWAT.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTEWAT() throws IOException,SQLException,Exception {
        BPTEWAT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEWAT_RD.TableName = "BPTEWAT1";
        else BPTEWAT_RD.TableName = "BPTEWAT2";
        IBS.REWRITE(SCCGWA, BPREWAT, BPTEWAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREWAT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREWAT.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCREWAT.RC);
        } else {
        }
    }
    public void T000_DELETE_BPTEWAT() throws IOException,SQLException,Exception {
        BPTEWAT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTEWAT_RD.TableName = "BPTEWAT1";
        else BPTEWAT_RD.TableName = "BPTEWAT2";
        IBS.DELETE(SCCGWA, BPREWAT, BPTEWAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREWAT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREWAT.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCREWAT.RC);
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCREWAT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCREWAT = ");
            CEP.TRC(SCCGWA, BPCREWAT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
