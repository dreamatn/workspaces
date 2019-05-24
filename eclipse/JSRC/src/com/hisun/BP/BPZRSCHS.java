package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRSCHS {
    DBParm BPTSCHS_RD;
    brParm BPTSCHS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRSCHS";
    int WS_COUNT = 0;
    char WS_TBL_SCHS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSCHS BPRSCHS = new BPRSCHS();
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    SCCGWA SCCGWA;
    BPCRSCHS BPCRSCHS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRSCHS BPRSCHL;
    public void MP(SCCGWA SCCGWA, BPCRSCHS BPCRSCHS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSCHS = BPCRSCHS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRSCHS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSCHL = (BPRSCHS) BPCRSCHS.POINTER;
        IBS.init(SCCGWA, BPRSCHS);
        IBS.CLONE(SCCGWA, BPRSCHL, BPRSCHS);
        BPCRSCHS.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSCHS.FUNC == 'A') {
            B010_RECORD_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'Q') {
            B020_RECORD_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'D') {
            B030_RECORD_DLE_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'U') {
            B040_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'S') {
            B060_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'R') {
            B070_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRSCHS.FUNC == 'E') {
            B080_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSCHS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRSCHS, BPRSCHL);
    }
    public void B010_RECORD_ADD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B020_RECORD_INQ_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B030_RECORD_DLE_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPD_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRSCHS.STR_DATE;
        WS_END_DATE = BPCRSCHS.END_DATE;
        T000_STARTBR_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B070_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRSCHS();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRSCHS();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_RD = new DBParm();
        BPTSCHS_RD.TableName = "BPTSCHS";
        IBS.WRITE(SCCGWA, BPRSCHS, BPTSCHS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRSCHS.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_QUERY_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_RD = new DBParm();
        BPTSCHS_RD.TableName = "BPTSCHS";
        IBS.READ(SCCGWA, BPRSCHS, BPTSCHS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_UPD_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_RD = new DBParm();
        BPTSCHS_RD.TableName = "BPTSCHS";
        BPTSCHS_RD.upd = true;
        IBS.READ(SCCGWA, BPRSCHS, BPTSCHS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_RD = new DBParm();
        BPTSCHS_RD.TableName = "BPTSCHS";
        IBS.REWRITE(SCCGWA, BPRSCHS, BPTSCHS_RD);
    }
    public void T000_DELETE_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_RD = new DBParm();
        BPTSCHS_RD.TableName = "BPTSCHS";
        IBS.DELETE(SCCGWA, BPRSCHS, BPTSCHS_RD);
    }
    public void T000_STARTBR_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_BR.rp = new DBParm();
        BPTSCHS_BR.rp.TableName = "BPTSCHS";
        BPTSCHS_BR.rp.where = "BR = :BPRSCHS.KEY.BR "
            + "AND TX_TLR = :BPRSCHS.TX_TLR "
            + "AND REC_STS = :BPRSCHS.REC_STS "
            + "AND SC_TYPE = :BPRSCHS.SC_TYPE "
            + "AND ( SC_DATE BETWEEN :WS_STR_DATE "
            + "AND :WS_END_DATE )";
        BPTSCHS_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRSCHS, this, BPTSCHS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRSCHS, this, BPTSCHS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSCHS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSCHS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRSCHS() throws IOException,SQLException,Exception {
        BPTSCHS_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTSCHS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSCHS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSCHS = ");
            CEP.TRC(SCCGWA, BPCRSCHS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
