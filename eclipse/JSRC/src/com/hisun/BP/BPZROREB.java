package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZROREB {
    brParm BPTORGE_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_ORGE = "BPTORGE ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_ORGE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGE BPRORGE = new BPRORGE();
    SCCGWA SCCGWA;
    BPCROREB BPCROREB;
    BPRORGE BPRORGEL;
    public void MP(SCCGWA SCCGWA, BPCROREB BPCROREB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCROREB = BPCROREB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZROREB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGEL = (BPRORGE) BPCROREB.INFO.POINTER;
        IBS.init(SCCGWA, BPCROREB.RC);
        IBS.CLONE(SCCGWA, BPRORGEL, BPRORGE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCROREB.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCROREB.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCROREB.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCROREB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGE, BPRORGEL);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTORGE();
        if (pgmRtn) return;
        if (WS_TBL_ORGE_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCROREB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTORGE();
        if (pgmRtn) return;
        if (WS_TBL_ORGE_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCROREB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTORGE();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTORGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRORGE.KEY.TX_DATE);
        CEP.TRC(SCCGWA, BPRORGE.KEY.TX_JRN);
        CEP.TRC(SCCGWA, BPRORGE.KEY.TX_SEQ);
        CEP.TRC(SCCGWA, BPRORGE.KEY.INCO_AC);
        CEP.TRC(SCCGWA, BPRORGE.KEY.INCO_ACO_AC);
        if (BPRORGE.KEY.INCO_AC.trim().length() > 0) {
            BPTORGE_BR.rp = new DBParm();
            BPTORGE_BR.rp.TableName = "BPTORGE";
            BPTORGE_BR.rp.where = "TX_DATE = :BPRORGE.KEY.TX_DATE "
                + "AND TX_JRN = :BPRORGE.KEY.TX_JRN "
                + "AND TX_SEQ = :BPRORGE.KEY.TX_SEQ "
                + "AND INCO_AC = :BPRORGE.KEY.INCO_AC";
            IBS.STARTBR(SCCGWA, BPRORGE, this, BPTORGE_BR);
        } else {
            BPTORGE_BR.rp = new DBParm();
            BPTORGE_BR.rp.TableName = "BPTORGE";
            BPTORGE_BR.rp.where = "TX_DATE = :BPRORGE.KEY.TX_DATE "
                + "AND TX_JRN = :BPRORGE.KEY.TX_JRN "
                + "AND TX_SEQ = :BPRORGE.KEY.TX_SEQ "
                + "AND INCO_AC = :BPRORGE.KEY.INCO_AC "
                + "AND INCO_ACO_AC = :BPRORGE.KEY.INCO_ACO_AC";
            IBS.STARTBR(SCCGWA, BPRORGE, this, BPTORGE_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGE_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGE_FLAG = 'D';
        } else {
        }
    }
    public void T000_READNEXT_BPTORGE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGE, this, BPTORGE_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORGE_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_ORGE_FLAG = 'D';
        } else {
        }
    }
    public void T000_ENDBR_BPTORGE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGE_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCROREB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCROREB = ");
            CEP.TRC(SCCGWA, BPCROREB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
