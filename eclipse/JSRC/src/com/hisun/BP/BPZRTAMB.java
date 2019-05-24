package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTAMB {
    brParm BPTTAMT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTAMT";
    String K_TBL_TAMT = "BPTTAMT ";
    char WS_TBL_TAMT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTAMT BPRTAMT = new BPRTAMT();
    SCCGWA SCCGWA;
    BPCRTAMB BPCRTAMB;
    BPRTAMT BPRTAML;
    public void MP(SCCGWA SCCGWA, BPCRTAMB BPCRTAMB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTAMB = BPCRTAMB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTAMB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTAML = (BPRTAMT) BPCRTAMB.INFO.POINTER;
        IBS.CLONE(SCCGWA, BPRTAML, BPRTAMT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRTAMB);
        CEP.TRC(SCCGWA, BPRTAMT);
        if (BPCRTAMB.INFO.FUNC == '1') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTAMB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTAMB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCRTAMB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTAMT, BPRTAML);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTAMT();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTAMT();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTAMT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTAMT() throws IOException,SQLException,Exception {
        BPTTAMT_BR.rp = new DBParm();
        BPTTAMT_BR.rp.TableName = "BPTTAMT";
        BPTTAMT_BR.rp.where = "TAMT_APP = :BPRTAMT.KEY.TAMT_APP";
        BPTTAMT_BR.rp.order = "TAMT_APP,TAMT_NO";
        IBS.STARTBR(SCCGWA, BPRTAMT, this, BPTTAMT_BR);
    }
    public void T000_READNEXT_BPTTAMT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTAMT, this, BPTTAMT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTAMB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTAMB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTAMT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTAMT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTAMB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTAMB = ");
            CEP.TRC(SCCGWA, BPCRTAMB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
