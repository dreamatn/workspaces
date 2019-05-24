package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTMUB {
    brParm BPTTMUP_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTMUB";
    String K_TBL_TMUP = "BPTTMUP ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTMUP BPRTMUP = new BPRTMUP();
    SCCGWA SCCGWA;
    BPCRTMUB BPCRTMUB;
    BPRTMUP BPRTMSP;
    public void MP(SCCGWA SCCGWA, BPCRTMUB BPCRTMUB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTMUB = BPCRTMUB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTMUB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTMSP = (BPRTMAP) BPCRTMUB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTMUP);
        IBS.CLONE(SCCGWA, BPRTMSP, BPRTMUP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMUB.INFO.FUNC == '1'
            || BPCRTMUB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMUB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTMUB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTMUB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTMUB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTMUP, BPRTMSP);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMUB.INFO.FUNC == '1') {
            T000_STARTBR_BPTTMUP_01();
            if (pgmRtn) return;
        } else if (BPCRTMUB.INFO.FUNC == '2') {
            T000_STARTBR_BPTTMUP_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTMUP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTMUP();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTMUP_01() throws IOException,SQLException,Exception {
        BPTTMUP_BR.rp = new DBParm();
        BPTTMUP_BR.rp.TableName = "BPTTMUP";
        BPTTMUP_BR.rp.where = "TLR_MOV_OUT LIKE :BPRTMUP.KEY.TLR_MOV_OUT "
            + "AND TLR_MOV_IN LIKE :BPRTMUP.KEY.TLR_MOV_IN "
            + "AND MOV_DT >= :BPRTMUP.KEY.MOV_DT";
        IBS.STARTBR(SCCGWA, BPRTMUP, this, BPTTMUP_BR);
    }
    public void T000_STARTBR_BPTTMUP_02() throws IOException,SQLException,Exception {
        BPTTMUP_BR.rp = new DBParm();
        BPTTMUP_BR.rp.TableName = "BPTTMUP";
        IBS.STARTBR(SCCGWA, BPRTMUP, BPTTMUP_BR);
    }
    public void T000_READNEXT_BPTTMUP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTMUP, this, BPTTMUP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTMUB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTMUB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTMUP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTMUP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTMUB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTMUB = ");
            CEP.TRC(SCCGWA, BPCRTMUB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
