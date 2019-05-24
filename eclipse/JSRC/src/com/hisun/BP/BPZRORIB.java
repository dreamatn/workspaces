package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRORIB {
    brParm BPTORGI_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_ORGI = "BPTORGI ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_ORGI_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGI BPRORGI = new BPRORGI();
    SCCGWA SCCGWA;
    BPCRORIB BPCRORIB;
    BPRORGI BPRORGIL;
    public void MP(SCCGWA SCCGWA, BPCRORIB BPCRORIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRORIB = BPCRORIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRORIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGIL = (BPRORGI) BPCRORIB.INFO.POINTER;
        IBS.init(SCCGWA, BPCRORIB.RC);
        IBS.CLONE(SCCGWA, BPRORGIL, BPRORGI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRORIB.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRORIB.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRORIB.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRORIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGI, BPRORGIL);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTORGI();
        if (pgmRtn) return;
        if (WS_TBL_ORGI_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRORIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTORGI();
        if (pgmRtn) return;
        if (WS_TBL_ORGI_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRORIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTORGI();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTORGI() throws IOException,SQLException,Exception {
        if (BPRORGI.KEY.AC_DT != 0) {
            BPTORGI_BR.rp = new DBParm();
            BPTORGI_BR.rp.TableName = "BPTORGI";
            BPTORGI_BR.rp.where = "AC_DT = :BPRORGI.KEY.AC_DT";
            IBS.STARTBR(SCCGWA, BPRORGI, this, BPTORGI_BR);
        }
        if (BPRORGI.INCO_DATE != 0 
            && BPRORGI.ORGI_FLG != ' ') {
            BPTORGI_BR.rp = new DBParm();
            BPTORGI_BR.rp.TableName = "BPTORGI";
            BPTORGI_BR.rp.where = "INCO_DATE > :BPRORGI.INCO_DATE "
                + "AND ORGI_FLG = :BPRORGI.ORGI_FLG";
            IBS.STARTBR(SCCGWA, BPRORGI, this, BPTORGI_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
        }
    }
    public void T000_READNEXT_BPTORGI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGI, this, BPTORGI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGI_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGI_FLAG = 'D';
        } else {
        }
    }
    public void T000_ENDBR_BPTORGI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGI_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRORIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRORIB = ");
            CEP.TRC(SCCGWA, BPCRORIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
