package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTEXPS {
    brParm BPTEXRP_BR = new brParm();
    boolean pgmRtn = false;
    int WS_REC_LEN = 0;
    char WS_INPUT = '0';
    char WS_CALC = '1';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXRP BPREXRP = new BPREXRP();
    SCCGWA SCCGWA;
    BPCREXPS BPCREXPS;
    BPREXRP BPREXRP1;
    public void MP(SCCGWA SCCGWA, BPCREXPS BPCREXPS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCREXPS = BPCREXPS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEXPS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXRP1 = (BPREXRP) BPCREXPS.INFO.POINTER;
        IBS.init(SCCGWA, BPREXRP);
        IBS.CLONE(SCCGWA, BPREXRP1, BPREXRP);
        WS_INPUT = '0';
        WS_CALC = '1';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCREXPS.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC_1();
            if (pgmRtn) return;
        } else if (BPCREXPS.INFO.FUNC == '6') {
            B017_STARTBR_RECORD_PROC_8();
            if (pgmRtn) return;
        } else if (BPCREXPS.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCREXPS.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCREXPS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXRP, BPREXRP1);
    }
    public void B011_STARTBR_RECORD_PROC_1() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRP1();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTEXRP1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXRP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRP.KEY.CCY);
        CEP.TRC(SCCGWA, BPREXRP.KEY.BR);
        if (BPREXRP.KEY.BR == 0) {
            CEP.TRC(SCCGWA, "DEVHZ01");
            BPTEXRP_BR.rp = new DBParm();
            BPTEXRP_BR.rp.TableName = "BPTEXRP";
            BPTEXRP_BR.rp.where = "EXR_TYP LIKE :BPREXRP.KEY.EXR_TYP "
                + "AND CCY LIKE :BPREXRP.KEY.CCY";
            BPTEXRP_BR.rp.order = "BR,EXR_TYP,CCY";
            IBS.STARTBR(SCCGWA, BPREXRP, this, BPTEXRP_BR);
        } else {
            CEP.TRC(SCCGWA, "DEVHZ02");
            BPTEXRP_BR.rp = new DBParm();
            BPTEXRP_BR.rp.TableName = "BPTEXRP";
            BPTEXRP_BR.rp.where = "BR = :BPREXRP.KEY.BR "
                + "AND EXR_TYP LIKE:EXRP_EXR_TYP "
                + "AND CCY LIKE:EXRP_CCY";
            BPTEXRP_BR.rp.order = "BR,EXR_TYP,CCY";
            IBS.STARTBR(SCCGWA, BPREXRP, this, BPTEXRP_BR);
        }
        CEP.TRC(SCCGWA, "DEVHZ187");
    }
    public void B017_STARTBR_RECORD_PROC_8() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXRP8();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTEXRP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTEXRP();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTEXRP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPREXRP, this, BPTEXRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCREXPS.INFO.RTN_INFO = 'Y';
            CEP.TRC(SCCGWA, BPREXRP.KEY.EXR_TYP);
            CEP.TRC(SCCGWA, BPREXRP.KEY.CCY);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCREXPS.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTEXRP8() throws IOException,SQLException,Exception {
        BPTEXRP_BR.rp = new DBParm();
        BPTEXRP_BR.rp.TableName = "BPTEXRP";
        BPTEXRP_BR.rp.where = "BR = :BPREXRP.KEY.BR "
            + "AND EXR_TYP = :BPREXRP.KEY.EXR_TYP "
            + "AND CCY = :BPREXRP.KEY.CCY";
        BPTEXRP_BR.rp.order = "EXR_TYP DESC";
        IBS.STARTBR(SCCGWA, BPREXRP, this, BPTEXRP_BR);
    }
    public void T000_ENDBR_BPTEXRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTEXRP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCREXPS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCREXPS = ");
            CEP.TRC(SCCGWA, BPCREXPS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
