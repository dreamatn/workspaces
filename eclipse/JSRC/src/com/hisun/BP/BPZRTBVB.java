package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTBVB {
    brParm BPTTBV_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTBVB";
    String K_TBL_TBV = "BPTTBV  ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTBV BPRTBV = new BPRTBV();
    SCCGWA SCCGWA;
    BPCRTBVB BPCRTBVB;
    BPRTBV BPRTBL;
    public void MP(SCCGWA SCCGWA, BPCRTBVB BPCRTBVB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTBVB = BPCRTBVB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTBVB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTBL = (BPRTBV) BPCRTBVB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTBV);
        IBS.CLONE(SCCGWA, BPRTBL, BPRTBV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBVB.INFO.FUNC == '1') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBVB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBVB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTBVB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTBVB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTBV, BPRTBL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBVB.INFO.FUNC == '1') {
            T000_STARTBR_BPTTBV_01();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTBV();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTBV();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTBV_01() throws IOException,SQLException,Exception {
        BPTTBV_BR.rp = new DBParm();
        BPTTBV_BR.rp.TableName = "BPTTBV";
        IBS.STARTBR(SCCGWA, BPRTBV, BPTTBV_BR);
    }
    public void T000_READNEXT_BPTTBV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTBV, this, BPTTBV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTBV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTBV_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTBVB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTBVB = ");
            CEP.TRC(SCCGWA, BPCRTBVB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
