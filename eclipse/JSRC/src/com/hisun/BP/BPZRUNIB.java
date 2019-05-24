package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRUNIB {
    brParm BPTUNIT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRUNIB";
    String K_TBL_UNIT = "BPTUNIT ";
    char WS_TBL_UNIT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRUNIT BPRUNIT = new BPRUNIT();
    SCCGWA SCCGWA;
    BPCRUNIB BPCRUNIB;
    BPRUNIT BPRUNIT1;
    public void MP(SCCGWA SCCGWA, BPCRUNIB BPCRUNIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRUNIB = BPCRUNIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRUNIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRUNIT1 = (BPRUNIT) BPCRUNIB.INFO.POINTER;
        IBS.CLONE(SCCGWA, BPRUNIT1, BPRUNIT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRUNIB);
        CEP.TRC(SCCGWA, BPRUNIT);
        if (BPCRUNIB.INFO.FUNC == '1') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRUNIB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRUNIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCRUNIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRUNIT, BPRUNIT1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTUNIT();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTUNIT();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTUNIT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTUNIT() throws IOException,SQLException,Exception {
        BPTUNIT_BR.rp = new DBParm();
        BPTUNIT_BR.rp.TableName = "BPTUNIT";
        BPTUNIT_BR.rp.where = "TX_CODE = :BPRUNIT.KEY.TX_CODE "
            + "AND CHNL_NO = :BPRUNIT.KEY.CHNL_NO "
            + "AND SERV_CODE = :BPRUNIT.KEY.SERV_CODE";
        BPTUNIT_BR.rp.order = "TX_CODE,CHNL_NO,SERV_CODE,UNIT_NO";
        IBS.STARTBR(SCCGWA, BPRUNIT, this, BPTUNIT_BR);
    }
    public void T000_READNEXT_BPTUNIT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRUNIT, this, BPTUNIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRUNIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRUNIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTUNIT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTUNIT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRUNIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRUNIB = ");
            CEP.TRC(SCCGWA, BPCRUNIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
