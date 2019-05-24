package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTTQPS {
    DBParm BPTTQP_RD;
    brParm BPTTQP_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTTQPS = "BPZTTQPS";
    String TBL_BPTTQP = "BPTTQP ";
    int WS_REC_LEN = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_STR_TIME = 0;
    int WS_END_TIME = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTQP BPRTQP = new BPRTQP();
    SCCGWA SCCGWA;
    BPCRTQPS BPCRTQPS;
    BPRTQP BPRTQQ;
    public void MP(SCCGWA SCCGWA, BPCRTQPS BPCRTQPS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTQPS = BPCRTQPS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTTQPS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTQQ = (BPRTQP) BPCRTQPS.INFO.POINTER;
        IBS.init(SCCGWA, BPRTQP);
        WS_STR_DATE = 0;
        WS_STR_TIME = 0;
        IBS.CLONE(SCCGWA, BPRTQQ, BPRTQP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTQPS.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '2') {
            B012_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '3') {
            B013_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '4') {
            B014_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '5') {
            B015_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '6') {
            B016_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '7') {
            B017_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '8') {
            B018_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == '9') {
            B019_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == 'U') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPS.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTQPS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTQP, BPRTQQ);
    }
    public void B011_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTQP.KEY.BR == 0) {
            T000_STARTBR_BPTTQP11();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQP12();
            if (pgmRtn) return;
        }
    }
    public void B012_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTQP.KEY.BR == 0) {
            T000_STARTBR_BPTTQP21();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQP22();
            if (pgmRtn) return;
        }
    }
    public void B013_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTQP.KEY.BR == 0) {
            T000_STARTBR_BPTTQP31();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQP32();
            if (pgmRtn) return;
        }
    }
    public void B014_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPRTQP.KEY.BR == 0) {
            T000_STARTBR_BPTTQP41();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQP42();
            if (pgmRtn) return;
        }
    }
    public void B015_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQP5();
        if (pgmRtn) return;
    }
    public void B016_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQP6();
        if (pgmRtn) return;
    }
    public void B017_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQP7();
        if (pgmRtn) return;
    }
    public void B018_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPS.STR_DATE;
        WS_STR_TIME = BPCRTQPS.STR_TIME;
        T000_STARTBR_BPTTQP8();
        if (pgmRtn) return;
    }
    public void B019_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPS.STR_DATE;
        WS_STR_TIME = BPCRTQPS.STR_TIME;
        T000_STARTBR_BPTTQP9();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTQP();
        if (pgmRtn) return;
    }
    public void B040_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTQP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTQP();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTTQP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CORR_CCY);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_STR_TIME);
        IBS.READNEXT(SCCGWA, BPRTQP, this, BPTTQP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTQPS.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTQPS.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTQP() throws IOException,SQLException,Exception {
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        IBS.REWRITE(SCCGWA, BPRTQP, BPTTQP_RD);
    }
    public void T000_STARTBR_BPTTQP11() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP LIKE :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR LIKE :BPRTQP.KEY.FWD_TENOR "
            + "AND CCY LIKE :BPRTQP.KEY.CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP12() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP LIKE :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR LIKE :BPRTQP.KEY.FWD_TENOR "
            + "AND BR = :BPRTQP.KEY.BR "
            + "AND CCY LIKE :BPRTQP.KEY.CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP21() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "CCY LIKE :BPRTQP.KEY.CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP22() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "BR = :BPRTQP.KEY.BR "
            + "AND CCY LIKE :BPRTQP.KEY.CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP31() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP LIKE :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR LIKE :BPRTQP.KEY.FWD_TENOR "
            + "AND CCY LIKE :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP32() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP LIKE :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR LIKE :BPRTQP.KEY.FWD_TENOR "
            + "AND BR = :BPRTQP.KEY.BR "
            + "AND CCY LIKE :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP41() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "CCY LIKE :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP42() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "BR = :BPRTQP.KEY.BR "
            + "AND CCY LIKE :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP5() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.order = "EXR_TYP";
        IBS.STARTBR(SCCGWA, BPRTQP, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP6() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPRTQP.KEY.FWD_TENOR "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP7() throws IOException,SQLException,Exception {
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPRTQP.KEY.FWD_TENOR "
            + "AND BR < > 0 "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP8() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CORR_CCY);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_STR_TIME);
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPRTQP.KEY.FWD_TENOR "
            + "AND ( BR = :BPRTQP.KEY.BR "
            + "OR BR = 0 ) "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.order = "EXR_TYP,FWD_TENOR,BR,CCY, CORR_CCY,EFF_DT DESC,EFF_TM DESC";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP9() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CORR_CCY);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_STR_TIME);
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPRTQP.KEY.FWD_TENOR "
            + "AND ( BR = :BPRTQP.KEY.BR "
            + "OR BR = 0 ) "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        BPTTQP_BR.rp.order = "EXR_TYP,FWD_TENOR,BR,CCY, CORR_CCY,EFF_DT ,EFF_TM";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_ENDBR_BPTTQP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTQP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTQPS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTQPS = ");
            CEP.TRC(SCCGWA, BPCRTQPS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
