package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRGRPB {
    brParm BPTGRP_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRGRPB";
    String K_TBL_GRP = "BPTGRP  ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRGRP BPRGRP = new BPRGRP();
    SCCGWA SCCGWA;
    BPCRGRPB BPCRGRPB;
    BPRGRP BPRGRP1;
    public void MP(SCCGWA SCCGWA, BPCRGRPB BPCRGRPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRGRPB = BPCRGRPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRGRPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRGRP1 = (BPRGRP) BPCRGRPB.INFO.POINTER;
        IBS.init(SCCGWA, BPRGRP);
        IBS.CLONE(SCCGWA, BPRGRP1, BPRGRP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRGRPB.INFO.FUNC == '1'
            || BPCRGRPB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGRPB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRGRPB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRGRPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRGRP, BPRGRP1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRGRPB.INFO.FUNC == '1') {
            T000_STARTBR_BPTGRP_01();
            if (pgmRtn) return;
        } else if (BPCRGRPB.INFO.FUNC == '2') {
            T000_STARTBR_BPTGRP_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTGRP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTGRP();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTGRP_01() throws IOException,SQLException,Exception {
        BPTGRP_BR.rp = new DBParm();
        BPTGRP_BR.rp.TableName = "BPTGRP";
        BPTGRP_BR.rp.where = "ROLE_CD >= :BPRGRP.KEY.ROLE_CD";
        BPTGRP_BR.rp.order = "ROLE_CD";
        IBS.STARTBR(SCCGWA, BPRGRP, this, BPTGRP_BR);
    }
    public void T000_STARTBR_BPTGRP_02() throws IOException,SQLException,Exception {
        BPTGRP_BR.rp = new DBParm();
        BPTGRP_BR.rp.TableName = "BPTGRP";
        BPTGRP_BR.rp.where = "ROLE_CD = :BPRGRP.KEY.ROLE_CD";
        IBS.STARTBR(SCCGWA, BPRGRP, this, BPTGRP_BR);
    }
    public void T000_READNEXT_BPTGRP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRGRP, this, BPTGRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGRPB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGRPB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTGRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTGRP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRGRPB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRGRPB = ");
            CEP.TRC(SCCGWA, BPCRGRPB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
