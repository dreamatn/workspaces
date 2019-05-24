package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTPARB {
    brParm BPTMPAR_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTPARB";
    String K_TBL_MPAR = "BPTMPAR ";
    char WS_TBL_MPAR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRMPAR BPRMPAR = new BPRMPAR();
    SCCGWA SCCGWA;
    BPCRPARB BPCRPARB;
    BPRMPAR BPRMPAR1;
    public void MP(SCCGWA SCCGWA, BPCRPARB BPCRPARB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPARB = BPCRPARB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTPARB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRMPAR1 = (BPRMPAR) BPCRPARB.POINTER;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRMPAR1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRMPAR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRPARB.INFO.FUNC == 'B') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPARB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPARB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRPARB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTMPAR();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTMPAR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRMPAR, BPRMPAR1);
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTMPAR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTMPAR() throws IOException,SQLException,Exception {
        BPTMPAR_BR.rp = new DBParm();
        BPTMPAR_BR.rp.TableName = "BPTMPAR";
        BPTMPAR_BR.rp.where = "MOV_DT = :BPRMPAR.KEY.MOV_DT "
            + "AND CONF_NO = :BPRMPAR.KEY.CONF_NO "
            + "AND CASH_TYP = :BPRMPAR.KEY.CASH_TYP "
            + "AND CCY = :BPRMPAR.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRMPAR, this, BPTMPAR_BR);
    }
    public void T000_READNEXT_BPTMPAR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRMPAR, this, BPTMPAR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRPARB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRPARB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTMPAR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTMPAR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRPARB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRPARB = ");
            CEP.TRC(SCCGWA, BPCRPARB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
