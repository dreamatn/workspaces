package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTBOGS {
    brParm BPTORGS_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTBOGS = "BPZTBOGS";
    String TBL_BPTORGS = "BPTORGS ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGS BPRORGS = new BPRORGS();
    SCCGWA SCCGWA;
    BPCRBOGS BPCRBOGS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRBOGS BPCRBOGS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBOGS = BPCRBOGS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTBOGS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBOGS.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOGS.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBOGS.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBOGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGS);
        BPRORGS.KEY.BNK = BPCRBOGS.BNK;
        BPRORGS.KEY.BR = BPCRBOGS.BR;
        BPRORGS.STS = BPCRBOGS.ORG_STS;
        CEP.TRC(SCCGWA, BPRORGS.KEY.BNK);
        CEP.TRC(SCCGWA, BPRORGS.KEY.BR);
        CEP.TRC(SCCGWA, BPRORGS.STS);
        if (BPCRBOGS.ORG_STS == ' ') {
            T000_STARTBR_BPTORGS_ALL();
            if (pgmRtn) return;
        } else {
            BPRORGS.STS = BPCRBOGS.ORG_STS;
            T000_STARTBR_BPTORGS_STS();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTORGS();
        if (pgmRtn) return;
        BPCRBOGS.BR = BPRORGS.KEY.BR;
        BPCRBOGS.ORG_STS = BPRORGS.STS;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTORGS();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTORGS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGS, this, BPTORGS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBOGS.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBOGS.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTORGS_ALL() throws IOException,SQLException,Exception {
        BPTORGS_BR.rp = new DBParm();
        BPTORGS_BR.rp.TableName = "BPTORGS";
        BPTORGS_BR.rp.where = "BNK = :BPRORGS.KEY.BNK";
        IBS.STARTBR(SCCGWA, BPRORGS, this, BPTORGS_BR);
    }
    public void T000_STARTBR_BPTORGS_STS() throws IOException,SQLException,Exception {
        BPTORGS_BR.rp = new DBParm();
        BPTORGS_BR.rp.TableName = "BPTORGS";
        BPTORGS_BR.rp.where = "BNK = :BPRORGS.KEY.BNK "
            + "AND STS = :BPRORGS.STS";
        IBS.STARTBR(SCCGWA, BPRORGS, this, BPTORGS_BR);
    }
    public void T000_ENDBR_BPTORGS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBOGS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRBOGS = ");
            CEP.TRC(SCCGWA, BPCRBOGS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
