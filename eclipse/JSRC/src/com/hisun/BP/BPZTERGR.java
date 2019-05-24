package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTERGR {
    DBParm BPTERGR_RD;
    brParm BPTERGR_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTERGR = "BPZTERGR";
    String TBL_BPTERGR = "BPTERGR ";
    int WS_REC_LEN = 0;
    int WS_MAX_DATE = 99991231;
    int WS_MAX_TIME = 235959;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRERGR BPRERGR = new BPRERGR();
    SCCGWA SCCGWA;
    BPCRERGR BPCRERGR;
    BPRERGR BPRERGR1;
    public void MP(SCCGWA SCCGWA, BPCRERGR BPCRERGR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRERGR = BPCRERGR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTERGR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRERGR1 = (BPRERGR) BPCRERGR.INFO.POINTER;
        IBS.init(SCCGWA, BPRERGR);
        IBS.CLONE(SCCGWA, BPRERGR1, BPRERGR);
        WS_MAX_DATE = 99991231;
        WS_MAX_TIME = 235959;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRERGR.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '2') {
            B012_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '3') {
            B013_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '4') {
            B014_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '5') {
            B015_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '6') {
            B016_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '7') {
            B017_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '8') {
            B018_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == '9') {
            B019_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRERGR.INFO.FUNC == 'E') {
            B040_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRERGR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRERGR, BPRERGR1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR();
        if (pgmRtn) return;
    }
    public void B011_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_1();
        if (pgmRtn) return;
    }
    public void B012_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_2();
        if (pgmRtn) return;
    }
    public void B013_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_3();
        if (pgmRtn) return;
    }
    public void B014_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_4();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_5();
        if (pgmRtn) return;
    }
    public void B016_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_6();
        if (pgmRtn) return;
    }
    public void B017_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_7();
        if (pgmRtn) return;
    }
    public void B018_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_8();
        if (pgmRtn) return;
    }
    public void B019_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTERGR_9();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTERGR();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTERGR();
        if (pgmRtn) return;
    }
    public void B040_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTERGR();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTERGR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRERGR, this, BPTERGR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRERGR.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRERGR.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTERGR() throws IOException,SQLException,Exception {
        BPTERGR_RD = new DBParm();
        BPTERGR_RD.TableName = "BPTERGR";
        IBS.DELETE(SCCGWA, BPRERGR, BPTERGR_RD);
    }
    public void T000_STARTBR_BPTERGR() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        IBS.STARTBR(SCCGWA, BPRERGR, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_1() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "CCY LIKE :BPRERGR.KEY.CCY "
            + "AND SQN >= :BPRERGR.KEY.SQN "
            + "AND EXP_DT = :WS_MAX_DATE "
            + "AND EXP_TM = :WS_MAX_TIME";
        BPTERGR_BR.rp.order = "SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_2() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "CCY = :BPRERGR.KEY.CCY "
            + "AND EXP_DT = :WS_MAX_DATE "
            + "AND EXP_TM = :WS_MAX_TIME";
        BPTERGR_BR.rp.order = "SQN DESC";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRERGR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRERGR.KEY.SQN);
        CEP.TRC(SCCGWA, WS_MAX_DATE);
        CEP.TRC(SCCGWA, WS_MAX_TIME);
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "CCY LIKE :BPRERGR.KEY.CCY "
            + "AND SQN >= :BPRERGR.KEY.SQN "
            + "AND EXP_DT = :WS_MAX_DATE "
            + "AND EXP_TM = :WS_MAX_TIME";
        BPTERGR_BR.rp.order = "CCY, SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_4() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "CCY = :BPRERGR.KEY.CCY "
            + "AND EXP_DT = :WS_MAX_DATE "
            + "AND EXP_TM = :WS_MAX_TIME";
        BPTERGR_BR.rp.order = "SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_5() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "EXP_DT < :BPRERGR.KEY.EXP_DT";
        BPTERGR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_6() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "BASE_CCY = :BPRERGR.BASE_CCY "
            + "AND BASE_EXR_TYP = :BPRERGR.BASE_EXR_TYP "
            + "AND BASE_FWD_TENOR = :BPRERGR.BASE_FWD_TENOR "
            + "AND CCY = :BPRERGR.BASE_CCY "
            + "AND EXR_TYP = :BPRERGR.BASE_EXR_TYP "
            + "AND FWD_TENOR = :BPRERGR.BASE_FWD_TENOR";
        BPTERGR_BR.rp.order = "CCY,SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_7() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "BASE_CCY = :BPRERGR.BASE_CCY "
            + "AND BASE_EXR_TYP = :BPRERGR.BASE_EXR_TYP "
            + "AND BASE_FWD_TENOR = :BPRERGR.BASE_FWD_TENOR "
            + "AND ( CCY < > :BPRERGR.BASE_CCY "
            + "OR EXR_TYP < > :BPRERGR.BASE_EXR_TYP "
            + "OR FWD_TENOR < > :BPRERGR.BASE_FWD_TENOR )";
        BPTERGR_BR.rp.order = "CCY,EXR_TYP,FWD_TENOR,SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_8() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "CCY = :BPRERGR.KEY.CCY "
            + "AND EXR_TYP = :BPRERGR.EXR_TYP";
        BPTERGR_BR.rp.order = "CCY,EXR_TYP,SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_STARTBR_BPTERGR_9() throws IOException,SQLException,Exception {
        BPTERGR_BR.rp = new DBParm();
        BPTERGR_BR.rp.TableName = "BPTERGR";
        BPTERGR_BR.rp.where = "BASE_EXR_TYP = :BPRERGR.BASE_EXR_TYP "
            + "AND BASE_CCY = :BPRERGR.BASE_CCY "
            + "AND BASE_FWD_TENOR = :BPRERGR.BASE_FWD_TENOR";
        BPTERGR_BR.rp.order = "CCY,SQN";
        IBS.STARTBR(SCCGWA, BPRERGR, this, BPTERGR_BR);
    }
    public void T000_ENDBR_BPTERGR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTERGR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRERGR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRERGR = ");
            CEP.TRC(SCCGWA, BPCRERGR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
