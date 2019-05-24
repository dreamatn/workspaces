package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTSCI {
    brParm BPTTLSC_BR = new brParm();
    boolean pgmRtn = false;
    int WS_COUNT = 0;
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTLSC BPRTLSC = new BPRTLSC();
    SCCGWA SCCGWA;
    BPCRTSCI BPCRTSCI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLSC BPRLTLSC;
    public void MP(SCCGWA SCCGWA, BPCRTSCI BPCRTSCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTSCI = BPCRTSCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTSCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLTLSC = (BPRTLSC) BPCRTSCI.POINTER;
        IBS.init(SCCGWA, BPRTLSC);
        IBS.CLONE(SCCGWA, BPRLTLSC, BPRTLSC);
        BPCRTSCI.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTSCI.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTSCI.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTSCI.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLSC, BPRLTLSC);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPRTLSC();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPRTLSC();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp = new DBParm();
        BPTTLSC_BR.rp.TableName = "BPTTLSC";
        BPTTLSC_BR.rp.where = "BR >= :BPRTLSC.KEY.BR "
            + "AND UPD_TLR LIKE :BPRTLSC.UPD_TLR "
            + "AND CODE_NO LIKE :BPRTLSC.KEY.CODE_NO "
            + "AND SC_TYPE LIKE :BPRTLSC.SC_TYPE "
            + "AND SC_STS LIKE :BPRTLSC.SC_STS";
        BPTTLSC_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTSCI.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTSCI.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLSC, this, BPTTLSC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTSCI.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTSCI.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPRTLSC() throws IOException,SQLException,Exception {
        BPTTLSC_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTLSC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTSCI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTSCI = ");
            CEP.TRC(SCCGWA, BPCRTSCI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
