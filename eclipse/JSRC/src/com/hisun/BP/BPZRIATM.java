package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRIATM {
    DBParm BPTIRAT_RD;
    brParm BPTIRAT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRIATM";
    String K_TBL_IRAT = "BPTIRAT ";
    int WS_REC_LEN = 0;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_IRAT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRIRAT BPRIRAT = new BPRIRAT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCRIATM BPCRIATM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRIRAT BPRIRAT1;
    public void MP(SCCGWA SCCGWA, BPCRIATM BPCRIATM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRIATM = BPCRIATM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRIATM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRIRAT1 = (BPRIRAT) BPCRIATM.INFO.POINTER;
        IBS.init(SCCGWA, BPRIRAT);
        IBS.CLONE(SCCGWA, BPRIRAT1, BPRIRAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRIATM.INFO.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.FUNC == 'B') {
            B040_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.FUNC == 'R') {
            B060_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRIRAT, BPRIRAT1);
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTIRAT();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTIRAT();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTIRAT();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRIATM.INFO.OPT_1 == 'S') {
            T000_STARTBR_BPTIRAT();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.OPT_1 == 'N') {
            T000_READNEXT_BPTIRAT();
            if (pgmRtn) return;
        } else if (BPCRIATM.INFO.OPT_1 == 'E') {
            T000_ENDBR_BPTIRAT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRIATM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTIRAT();
        if (pgmRtn) return;
    }
    public void B060_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTIRAT_UPD();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTIRAT() throws IOException,SQLException,Exception {
        BPTIRAT_RD = new DBParm();
        BPTIRAT_RD.TableName = "BPTIRAT";
        BPTIRAT_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRIRAT, BPTIRAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRIATM.RETURN_INFO = 'F';
            BPCRIATM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRIATM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRIATM.RC);
        } else {
        }
    }
    public void T000_DELETE_BPTIRAT() throws IOException,SQLException,Exception {
        BPTIRAT_RD = new DBParm();
        BPTIRAT_RD.TableName = "BPTIRAT";
        IBS.DELETE(SCCGWA, BPRIRAT, BPTIRAT_RD);
    }
    public void T000_STARTBR_BPTIRAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRIRAT.KEY.BR);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.TENOR);
        BPTIRAT_BR.rp = new DBParm();
        BPTIRAT_BR.rp.TableName = "BPTIRAT";
        BPTIRAT_BR.rp.where = "BR = :BPRIRAT.KEY.BR "
            + "AND CCY LIKE :BPRIRAT.KEY.CCY "
            + "AND BASE_TYP LIKE :BPRIRAT.KEY.BASE_TYP "
            + "AND TENOR LIKE :BPRIRAT.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRIRAT, this, BPTIRAT_BR);
    }
    public void T000_READNEXT_BPTIRAT() throws IOException,SQLException,Exception {
        BPTIRAT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRIRAT, this, BPTIRAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRIATM.RETURN_INFO = 'F';
            BPCRIATM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRIATM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTIRAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTIRAT_BR);
    }
    public void T000_WRITE_BPTIRAT() throws IOException,SQLException,Exception {
        BPTIRAT_RD = new DBParm();
        BPTIRAT_RD.TableName = "BPTIRAT";
        BPTIRAT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRIRAT, BPTIRAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRIATM.RETURN_INFO = 'F';
            BPCRIATM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRIATM.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCRIATM.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_IRAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_READ_BPTIRAT_UPD() throws IOException,SQLException,Exception {
        BPTIRAT_RD = new DBParm();
        BPTIRAT_RD.TableName = "BPTIRAT";
        BPTIRAT_RD.upd = true;
        IBS.READ(SCCGWA, BPRIRAT, BPTIRAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRIATM.RETURN_INFO = 'F';
            BPCRIATM.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRIATM.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRIATM.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTIRAT() throws IOException,SQLException,Exception {
        BPTIRAT_RD = new DBParm();
        BPTIRAT_RD.TableName = "BPTIRAT";
        IBS.REWRITE(SCCGWA, BPRIRAT, BPTIRAT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRIATM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRIATM = ");
            CEP.TRC(SCCGWA, BPCRIATM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
