package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRNTOB {
    brParm BPTNTOT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRNTOB";
    String K_TBL_NTOT = "BPTNTOT ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRNTOT BPRNTOT = new BPRNTOT();
    SCCGWA SCCGWA;
    BPCRNTOB BPCRNTOB;
    BPRNTOT BPRNTOTL;
    public void MP(SCCGWA SCCGWA, BPCRNTOB BPCRNTOB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRNTOB = BPCRNTOB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRNTOB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRNTOTL = (BPRNTOT) BPCRNTOB.INFO.POINTER;
        IBS.init(SCCGWA, BPRNTOT);
        IBS.CLONE(SCCGWA, BPRNTOTL, BPRNTOT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRNTOB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRNTOB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRNTOB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRNTOB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRNTOB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRNTOT, BPRNTOTL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRNTOB.INFO.FUNC == '2') {
            T000_STARTBR_BPTNTOT_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTNTOT();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTNTOT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTNTOT_02() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRNTOT);
        BPTNTOT_BR.rp = new DBParm();
        BPTNTOT_BR.rp.TableName = "BPTNTOT";
        BPTNTOT_BR.rp.where = "BR = :BPRNTOT.KEY.BR "
            + "AND BV_CODE = :BPRNTOT.KEY.BV_CODE "
            + "AND VALUE = :BPRNTOT.KEY.VALUE "
            + "AND HEAD_NO = :BPRNTOT.KEY.HEAD_NO "
            + "AND END_NO >= :BPRNTOT.BEG_NO "
            + "AND BEG_NO <= :BPRNTOT.KEY.END_NO "
            + "AND BEG_NO <= END_NO";
        IBS.STARTBR(SCCGWA, BPRNTOT, this, BPTNTOT_BR);
    }
    public void T000_READNEXT_BPTNTOT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRNTOT, this, BPTNTOT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRNTOB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRNTOB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTNTOT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTNTOT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRNTOB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRNTOB = ");
            CEP.TRC(SCCGWA, BPCRNTOB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
